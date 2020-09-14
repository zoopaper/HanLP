package com.hankcs.hanlp.dictionary;

import com.hankcs.hanlp.classification.features.TfIdfFeature;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @User krisjin
 * @Date 2020/9/12
 */
public class TfIdfFeatureTest {

    @Test
    public void termTfIdf() {
        TfIdfFeature tfIdfFeature = new TfIdfFeature();
        Map<Object, String> docMap = new HashMap<>();
        docMap.put("13", "女排北京奥运会夺冠");
        docMap.put("14", "中国队女排夺北京奥运会金牌重返巅峰，观众欢呼女排女排女排！");
        docMap.put("15", "经过此次芯片“卡脖子”，尽快打造可自控的芯片供应链已成为华为必需的选项。“美国对华为的打压或将加剧，长远来看，华为需要建立一条‘去美国化’的芯片供应链，否则脖子永远捏在别人手里");

        Map<Object, Map<String, Double>> tfidf = tfIdfFeature.getTfIdf(docMap);
    }

}
