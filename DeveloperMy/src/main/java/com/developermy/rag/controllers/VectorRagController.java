package com.developermy.rag.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developermy.rag.models.LoadResponse;
import com.developermy.rag.models.QueryRequest;
import com.developermy.rag.models.QueryResponse;
import com.developermy.rag.models.QueryResult;
import com.developermy.rag.services.QdrantVectorService;

import lombok.extern.slf4j.Slf4j;

//http//::localhost:8080/developerme/api/rag/load
@RestController
@RequestMapping("/api/rag/")
@Slf4j
public class VectorRagController {

    private final QdrantVectorService qdrantService;

    public VectorRagController(QdrantVectorService qdrantService	) {
        this.qdrantService = qdrantService;
    }

    //http://localhost:8080/developerme/api/rag/load
    @PostMapping("/load-file")
    public ResponseEntity<LoadResponse> loadVectorDatabase() {
        return ResponseEntity.ok(qdrantService.loadDataFromCsv2());
    }

    @PostMapping("/query")
    public ResponseEntity<QueryResponse> queryRag(@RequestBody QueryRequest request) {
        log.info("Recibida solicitud de consulta RAG: {}", request.query());
        return ResponseEntity.ok(qdrantService.queryRaggRPC(request));
    }
    
}
