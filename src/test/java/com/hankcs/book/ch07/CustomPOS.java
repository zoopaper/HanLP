/*
 * <author>Han He</author>
 * <email>me@hankcs.com</email>
 * <create-date>2018-07-05 4:03 PM</create-date>
 *
 * <copyright file="CustomPOS.java">
 * Copyright (c) 2018, Han He. All Rights Reserved, http://www.hankcs.com/
 * This source is subject to Han He. Please contact Han He for more information.
 * </copyright>
 */
package com.hankcs.book.ch07;

import com.hankcs.hanlp.dictionary.CustomDictionary;
import com.hankcs.hanlp.model.perceptron.PerceptronLexicalAnalyzer;

import java.io.IOException;

/**
 * 《自然语言处理入门》7.4 自定义词性
 *
 * @author hankcs
 * @see <a href="https://bbs.hankcs.com/">讨论答疑</a>
 */
public class CustomPOS {
    public static void main(String[] args) throws IOException {
        CustomDictionary.insert("苹果", "手机品牌 1");
        CustomDictionary.insert("iPhone X", "手机型号 1");
        PerceptronLexicalAnalyzer analyzer = new PerceptronLexicalAnalyzer();
        analyzer.enableCustomDictionaryForcing(true);
        System.out.println(analyzer.analyze("你们苹果iPhone X保修吗？"));
        System.out.println(analyzer.analyze("多吃苹果有益健康"));
    }
}
