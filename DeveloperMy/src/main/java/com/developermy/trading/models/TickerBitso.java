package com.developermy.trading.models;


import java.util.Date;

import lombok.Data;

@Data
public class TickerBitso {

    protected Double mLast;
    protected Double mHigh;
    protected Double mLow;
    protected Double mVwap;
    protected Double mVolume;
    protected Double mBid;
    protected Double mAsk;
    protected Date mCreatedAt;
    protected Double change24;
     
}
