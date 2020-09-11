package com.hankcs.hanlp.classification.features;

import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

import java.util.List;

/**
 * @User krisjin
 * @date 2020/9/10
 */
public class TitleFeature {

    public static int termHit(String query, String title) {
        int hit = 0;
        if (query == null || title == null) return hit;
        int idx = title.indexOf(query);
        if (idx != -1) {
            hit = 1;
            return hit;
        } else {
            List<Term> termList = StandardTokenizer.segment(query);
            for (Term term : termList) {
                String word = term.word;
                if (title.indexOf(word) != -1) {
                    hit = 1;
                    break;
                }
            }
        }
        return hit;
    }

}
