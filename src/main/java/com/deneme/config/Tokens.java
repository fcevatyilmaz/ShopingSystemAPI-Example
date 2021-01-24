package com.deneme.config;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class Tokens {

    public Map<Object, Object> getTokensMap() {
        return tokensMap;
    }


    private Map<Object,Object> tokensMap= new HashMap<Object,Object>(); //Key token - value userId


}
