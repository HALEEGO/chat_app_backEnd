package com.example.hackathonex.constant;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
public class Returns {
    private StringBuilder returnURL;
    private Map<String,String> returnValue;

    public Returns(){
        returnURL = new StringBuilder();
        returnValue = new HashMap<>();
    }
    public Returns(String url){
        returnURL = new StringBuilder(url);
        returnValue = new HashMap<>();
    }

    public Returns append(String url){
        returnURL.append(url);
        return this;
    }
    public Returns put(String key, String value){
        returnValue.put(key,value);
        return this;

    }
    public Returns remove(String key){
        this.returnValue.remove(key);
        return this;
    }
}
