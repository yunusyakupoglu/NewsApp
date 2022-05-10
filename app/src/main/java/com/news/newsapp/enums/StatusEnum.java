package com.news.newsapp.enums;

public enum StatusEnum {
    ACTIVE(1),
    PASSIVE(2);

    int statusType = 0;
    StatusEnum(int status){
        statusType=status;
    }
    public int getValue(){
        return statusType;
    }

}
