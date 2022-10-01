package com.example.testtaskwithoutbd.service;

import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class MagicSquareService {
    public List<List<Integer>> generateMagicSquare(List<List<Integer>> initialSquare) throws NumberFormatException {
        Integer diff1 = 0, diff2 = 0, diff3 = 0, diff4 = 0, mean = 0;
        List<Integer> supportSquare = new LinkedList<>();
        List<List<Integer>> magicSquare = new LinkedList<>();
        for(int i = 0; i < initialSquare.size(); ++i){
            magicSquare.add(new LinkedList<>());
            for(int j = 0; j < initialSquare.get(i).size(); ++j){
                magicSquare.get(i).add(0);
                supportSquare.add(initialSquare.get(i).get(j));
            }
        }
        Collections.sort(supportSquare);
        for(int i = 1; i < 10; ++i){
            mean += supportSquare.get(i - 1) - i;
            supportSquare.set(i - 1, i);
        }
        mean = mean / 9;
        magicSquare.get(0).set(0, supportSquare.get(1));
        magicSquare.get(0).set(1, supportSquare.get(6));
        magicSquare.get(0).set(2, supportSquare.get(5));

        magicSquare.get(1).set(0, supportSquare.get(8));
        magicSquare.get(1).set(1, supportSquare.get(4));
        magicSquare.get(1).set(2, supportSquare.get(0));

        magicSquare.get(2).set(0, supportSquare.get(3));
        magicSquare.get(2).set(1, supportSquare.get(2));
        magicSquare.get(2).set(2, supportSquare.get(7));

        for(int i = 0; i < initialSquare.size(); ++ i){
            for(int j = 0; j < initialSquare.get(i).size(); ++j){
                diff1 += Math.abs(initialSquare.get(i).get(j) - magicSquare.get(i).get(j));
                diff2 += Math.abs(initialSquare.get(i).get(j) - magicSquare.get(initialSquare.size() - j - 1).get(i));
                diff3 += Math.abs(initialSquare.get(i).get(j) - magicSquare.get(initialSquare.size() - i - 1).get(initialSquare.size() - j - 1));
                diff4 += Math.abs(initialSquare.get(i).get(j) - magicSquare.get(j).get(initialSquare.size() - i - 1));
            }
        }
        List<List<Integer>> copyMagicSquare = new ArrayList<>();
        for(int i = 0; i < magicSquare.size(); ++i){
            copyMagicSquare.add(new LinkedList<>());
            for(int j = 0; j < magicSquare.get(i).size(); ++j){
                copyMagicSquare.get(i).add(magicSquare.get(i).get(j).intValue() + mean);
            }
        }
        if(diff1 <= diff2 && diff1 <= diff3 && diff1 <= diff4){
            magicSquare.add(new LinkedList<>(Arrays.asList(diff1 - mean * 9)));
            return magicSquare;
        }else if(diff2 <= diff1 && diff2 <= diff3 && diff2 <= diff4){
            for(int i = 0; i < magicSquare.size(); ++i){
                for(int j = 0; j < magicSquare.get(i).size(); ++j){
                    magicSquare.get(i).set(j, copyMagicSquare.get(magicSquare.size() - j - 1).get(i));
                }
            }
            magicSquare.add(new LinkedList<>(Arrays.asList(diff2 - mean * 9)));
            return magicSquare;
        } else if (diff3 <= diff1 && diff3 <= diff2 && diff3 <= diff4) {
            for(int i = 0; i < magicSquare.size(); ++i){
                for(int j = 0; j < magicSquare.get(i).size(); ++j){
                    magicSquare.get(i).set(j, copyMagicSquare.get(j).get(magicSquare.size() - i - 1));
                }
            }
            magicSquare.add(new LinkedList<>(Arrays.asList(diff3 - mean * 9)));
            return magicSquare;
        }else{
            for(int i = 0; i < magicSquare.size(); ++i){
                for(int j = 0; j < magicSquare.get(i).size(); ++j){
                    magicSquare.get(i).set(j, copyMagicSquare.get(magicSquare.size() - i - 1).get(magicSquare.size() - j - 1));
                }
            }
            magicSquare.add(new LinkedList<>(Arrays.asList(diff4 - mean * 9)));
            return magicSquare;
        }
    }
}
