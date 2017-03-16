package com.sohu.mrd.hotword.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Created by yonghongli on 2017/3/15.
 */
public class WordFilter {
    public static HashSet<String> sexWords = new HashSet<String>();
    static {
        loadSexWord();
    }

    public static void loadSexWord() {
        InputStream sexWordStream = WordFilter.class
                .getResourceAsStream("/library/default.dic");
        BufferedReader br  = new BufferedReader(new InputStreamReader(sexWordStream));
        String line =null;
        try {
            while ((line = br.readLine()) != null) {
                String sexWord=line;
                sexWords.add(sexWord);
            }
        }catch (Exception e){

        }
    }

    public static Map<String,Integer> filterSex(Map<String, Integer> h) {
        Map<String,Integer> temp = h;
        for (String hotword:h.keySet()){
            List<String> words = SegmentKit.segmentSentence(hotword);
            for(String word:words){
                if(sexWords.contains(word)){
                    temp.remove(hotword);
                    break;
                }
            }

        }
        return temp;
    }

}
