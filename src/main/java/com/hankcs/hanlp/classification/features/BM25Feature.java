package com.hankcs.hanlp.classification.features;

import com.hankcs.hanlp.dictionary.stopword.CoreStopWordDictionary;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.summary.BM25;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @User krisjin
 * @date 2020/9/10
 */
public class BM25Feature {
    private static String defaultSentenceSep = "[，,。:：“”？?！!；;]";

    public static double sim(String query, String doc) {
        List<List<String>> docList = buildSingleDoc(doc);
        BM25 bm25 = new BM25(docList);

        List<Term> termList = StandardTokenizer.segment(query);
        List<String> terms = new ArrayList<String>();
        for (Term term : termList) {

            terms.add(term.word);
        }

        double[] score = bm25.simAll(terms);
        return sum(score);
    }

    private static List<List<String>> buildSingleDoc(String doc) {
        List<String> docList = splitSentence(doc, defaultSentenceSep);
        return convertSentenceListToDocument(docList);
    }


    /**
     * 将句子列表转化为文档
     *
     * @param sentenceList
     * @return
     */
    private static List<List<String>> convertSentenceListToDocument(List<String> sentenceList) {
        List<List<String>> docs = new ArrayList<List<String>>(sentenceList.size());
        for (String sentence : sentenceList) {
            List<Term> termList = StandardTokenizer.segment(sentence.toCharArray());
            List<String> wordList = new LinkedList<String>();
            for (Term term : termList) {
                if (CoreStopWordDictionary.shouldInclude(term)) {
                    wordList.add(term.word);
                }
            }
            docs.add(wordList);
        }
        return docs;
    }


    /**
     * 将文章分割为句子
     *
     * @param document          待分割的文档
     * @param sentenceSeparator 句子分隔符，正则表达式，如：   [。:？?！!；;]
     * @return
     */
    private static List<String> splitSentence(String document, String sentenceSeparator) {
        List<String> sentences = new ArrayList<String>();
        for (String line : document.split("[\r\n]")) {
            line = line.trim();
            if (line.length() == 0) continue;
            for (String sent : line.split(sentenceSeparator))        // [，,。:：“”？?！!；;]
            {
                sent = sent.trim();
                if (sent.length() == 0) continue;
                sentences.add(sent);
            }
        }
        return sentences;
    }

    private static double sum(double[] scores) {
        double total = 0d;
        for (double d : scores) {
            total += d;
        }
        return total;
    }

}
