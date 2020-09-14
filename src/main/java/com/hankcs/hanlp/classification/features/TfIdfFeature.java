package com.hankcs.hanlp.classification.features;

import com.hankcs.hanlp.mining.word.TfIdfCounter;

import java.util.Map;

/**
 * @User krisjin
 * @Date 2020/9/12
 */
public class TfIdfFeature {

    /**
     * @param docMap
     * @return
     */
    public Map<Object, Map<String, Double>> getTfIdf(Map<Object, String> docMap) {
        TfIdfCounter counter = new TfIdfCounter();
        for (Map.Entry<Object, String> entry : docMap.entrySet()) {
            counter.add(entry.getKey(), entry.getValue());
        }

        Map<Object, Map<String, Double>> tfidf = counter.compute();

        return tfidf;
    }

}
