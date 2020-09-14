/*
 * <summary></summary>
 * <author>He Han</author>
 * <email>hankcs.cn@gmail.com</email>
 * <create-date>2014/12/7 21:13</create-date>
 *
 * <copyright file="DemoAll.java" company="上海林原信息科技有限公司">
 * Copyright (c) 2003-2014, 上海林原信息科技有限公司. All Right Reserved, http://www.linrunsoft.com/
 * This source is subject to the LinrunSpace License. Please contact 上海林原信息科技有限公司 to get more information.
 * </copyright>
 */
package com.hankcs.demo;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.stopword.CoreStopWordDictionary;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;

import java.util.ArrayList;
import java.util.List;

/**
 * 第一个Demo，惊鸿一瞥
 *
 * @author hankcs
 */
public class DemoAtFirstSight {
    public static void main(String[] args) {
        System.out.println("首次编译运行时，HanLP会自动构建词典缓存，请稍候……");
//        HanLP.Config.enableDebug();         // 为了避免你等得无聊，开启调试模式说点什么:-)


        List<String> terms = new ArrayList<String>();
        List<Term> termList = StandardTokenizer.segment("点点app");
        for (Term term : termList) {
            if (CoreStopWordDictionary.shouldInclude(term)) {
                terms.add(term.word);
            }
        }
        System.out.println(terms);
//        System.out.println(HanLP.segment("@所有人 帮我助力，我马上拿3○元，谢...\t"));
    }
}
