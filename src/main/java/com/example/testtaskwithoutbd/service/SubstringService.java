package com.example.testtaskwithoutbd.service;

import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class SubstringService {

    public List<String> substrings(List<String> substrings, List<String> sourceStrings){
        List<String> resultSubstrings = new LinkedList<>();
        for(String sub: substrings){
            for(String source: sourceStrings){
                if(source.contains(sub)){
                    resultSubstrings.add(sub);
                    resultSubstrings.add(" ");
                    break;
                }
            }
        }
        return resultSubstrings;
    }

    private String processString(List<String> list){
        StringBuilder stringBuilder = new StringBuilder();
        for(String s: list){
            stringBuilder.append(s);
        }
        return stringBuilder.toString();
    }
}