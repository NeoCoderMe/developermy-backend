package com.developermy.rag.services;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;

import com.developermy.rag.models.LoadResponse;
import com.developermy.rag.models.QueryRequest;
import com.developermy.rag.models.QueryResponse;
import com.developermy.rag.models.QueryResult;
import com.google.common.util.concurrent.ListenableFuture;

import ai.djl.inference.Predictor;
import ai.djl.repository.zoo.Criteria;
import ai.djl.repository.zoo.ZooModel;
import io.qdrant.client.QdrantClient;
import io.qdrant.client.grpc.Collections.Distance;
import io.qdrant.client.grpc.Collections.VectorParams;
import io.qdrant.client.grpc.JsonWithInt.Value;
import io.qdrant.client.grpc.Points.DeletePoints;
import io.qdrant.client.grpc.Points.Filter;
import io.qdrant.client.grpc.Points.PointId;
import io.qdrant.client.grpc.Points.PointStruct;
import io.qdrant.client.grpc.Points.PointsSelector;
import io.qdrant.client.grpc.Points.ScoredPoint;
import io.qdrant.client.grpc.Points.SearchPoints;
import io.qdrant.client.grpc.Points.UpdateResult;
import io.qdrant.client.grpc.Points.Vector;
import io.qdrant.client.grpc.Points.Vectors;
import io.qdrant.client.grpc.Points.WithPayloadSelector;
import lombok.extern.slf4j.Slf4j;



@Service
@Slf4j
public class QdrantVectorService {

    private final QdrantClient qdrantClient;
    private ZooModel<?, ?> model;
    private Predictor<?, ?> predictor;
    private static final String COLLECTION_NAME = "neo_rag_vectors";
    private static final int VECTOR_SIZE = 384; // Tamaño del vector para all-MiniLM-L6-v2

    public QdrantVectorService(QdrantClient qdrantClient) {
        this.qdrantClient = qdrantClient;
        initializeModel();
        initializeQdrantCollection2();
    }

    private void initializeModel() {
        try {
            Criteria<?, ?> criteria = Criteria.builder()
                    .setTypes(String.class, float[].class)
                    .optModelUrls("djl://ai.djl.huggingface.pytorch/sentence-transformers/all-MiniLM-L6-v2")
                    .optProgress(null)
                    .build();

            model = criteria.loadModel();
            predictor = model.newPredictor();
            log.info("Embeddings model initialized successfully");  
        } catch (Exception e) {
        	log.error("Error initializing the embeddings model", e);
        }
    }
    
    private void initializeQdrantCollection2() {
        try {
            List<String> collections = qdrantClient
                .listCollectionsAsync()      
                .get(15, TimeUnit.SECONDS);    

            boolean exists = collections.contains(COLLECTION_NAME);

            if (!exists) {
                qdrantClient
                    .createCollectionAsync(
                        COLLECTION_NAME,
                        VectorParams.newBuilder()
                            .setSize(VECTOR_SIZE)
                            .setDistance(Distance.Cosine)
                            .build()
                    )
                    .get(15, TimeUnit.SECONDS);

                log.info("Qdrant collection created successfully");
            } else {
            	log.info("Qdrant collection already exists");
            }

        } catch (Exception e) {
        	log.error("Error initializing Qdrant collection", e);
        }
    }
     

    public LoadResponse loadDataFromCsv2() {
        int recordsProcessed = 0;
        int recordsSuccess = 0;
        String filename = "neoDummy.csv"; 
        
        try {
            if (filename == null || filename.isBlank()) {
            	return LoadResponse.error("Filename cannot be empty");  
            }
            
            ClassPathResource resource = new ClassPathResource(filename);
            
            
            clearCollection(filename);
            
            
            try (Reader reader = new InputStreamReader(resource.getInputStream(), StandardCharsets.UTF_8);
                 CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT  .withTrim())) {

                List<PointStruct> points = new ArrayList<>();
//                long pointId = 1; 

                for (CSVRecord record : csvParser) {
                    recordsProcessed++;
                    try {
                    	String question = record.get(0);  
                        String answer = record.get(1);   

                        float[] embedding = generateEmbedding(question);
                        
                        if (embedding == null || embedding.length == 0) {
                            continue;
                        }
                        
                        
                        Vector embeddingVector = convertFloatArrayToVector(embedding);
                        Vectors pointVectors = Vectors.newBuilder() .setVector(embeddingVector) .build();
                        
                        PointStruct point = PointStruct.newBuilder().setId(PointId.newBuilder().setUuid(UUID.randomUUID().toString()).build()).setVectors(pointVectors)  
                            .putPayload("question", Value.newBuilder().setStringValue(question).build())
                            .putPayload("answer", Value.newBuilder().setStringValue(answer).build()) .build();

                        points.add(point);
                        recordsSuccess++;
                        
                        if (points.size() >= 100) {
                        	uploadPointsToQdrant(points);
                            points.clear();
                        }
                    } catch (Exception e) {
                    	log.error("Error processing record {}: {}", recordsProcessed, e.getMessage());
                    }
                }
                
                if (!points.isEmpty()) {
                	uploadPointsToQdrant(points);
                }
            }
            
            return LoadResponse.success(recordsProcessed, recordsSuccess);
            
        } catch (IOException e) { 
        	return LoadResponse.error("Error loading file: " + e.getMessage());
        }
    }
     
    
    public void clearCollection(String collectionName) {
        try {
        	Filter filter = Filter.newBuilder().build();

        	PointsSelector selector = PointsSelector.newBuilder() .setFilter(filter) .build();

        	DeletePoints deleteRequest = DeletePoints.newBuilder()
        	        .setCollectionName(COLLECTION_NAME)
        	        .setWait(true)
        	        .setPoints(selector)   
        	        .build();
        	 
            ListenableFuture<UpdateResult> future = qdrantClient.deleteAsync(deleteRequest);
            UpdateResult result = future.get();  

            log.info("Collection '{}' cleaned. Points removed: {}", collectionName, result.getOperationId());;

        } catch (Exception e) {
            log.error("Error cleaning collection '{}': {}", collectionName, e.getMessage());
            throw new RuntimeException("Error cleaning Qdrant collection", e);
        }    
    }
    
    
    public void uploadPointsToQdrant(List<PointStruct> points) {
        try {
            qdrantClient.upsertAsync(COLLECTION_NAME, points).get();

            log.info("Successfully uploaded {} points to Qdrant.", points.size());
        } catch (InterruptedException | ExecutionException e) {
            Thread.currentThread().interrupt(); // Restablecer estado de interrupción si es necesario
            log.error("Error al cargar puntos en Qdrant: {}", e.getMessage(), e);
            throw new RuntimeException("Error al cargar puntos en Qdrant", e);
        }
    }
    
    public QueryResponse queryRaggRPC(QueryRequest request) {
        if (request == null || request.query() == null || request.query().isBlank()) {
            return QueryResponse.error("La consulta no puede estar vacía");
        }

        try {
            String query = request.query();

            float[] queryEmbedding = generateEmbedding(query);

//            Vector vector = convertFloatArrayToVector(queryEmbedding);
// 
            SearchPoints searchRequest2 = SearchPoints.newBuilder()
                    .setCollectionName(COLLECTION_NAME)
                    .addAllVector(toList(queryEmbedding))  
                    .setLimit(4)
                    .setWithPayload( WithPayloadSelector.newBuilder() .setEnable(true) .build())
                    .build();
            
            List<ScoredPoint> results = qdrantClient.searchAsync(searchRequest2).get();  

            List<QueryResult> queryResults = new ArrayList<>();

            for (ScoredPoint result : results) {
                Map<String, Value> payload = result.getPayloadMap();
                String question = payload.get("question").getStringValue();
                String answer = payload.get("answer").getStringValue();
                double score = result.getScore();

                queryResults.add(new QueryResult(question, answer, score));
            }

            return QueryResponse.success(query, queryResults);

        } catch (Exception e) {
            return QueryResponse.error("Error al procesar la consulta: " + e.getMessage());
        }
    }

    private float[] generateEmbedding(String text) throws Exception {
        if (predictor == null) {
            throw new IllegalStateException("El modelo de embeddings no está inicializado");
        }
        
        @SuppressWarnings("unchecked")
        Predictor<String, float[]> typedPredictor = (Predictor<String, float[]>) predictor;
        return typedPredictor.predict(text);
    }
    
    private Vector convertFloatArrayToVector(float[] array) {
        List<Float> vectorData = new ArrayList<>(array.length);
        for (float v : array) {
            vectorData.add(v);
        }
        return Vector.newBuilder()
            .addAllData(vectorData)
            .build();
    }

    private List<Float> toList(float[] array) {
        List<Float> list = new ArrayList<>(array.length);
        for (float v : array) {
            list.add(v);
        }
        return list;
    }
}