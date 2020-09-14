package com.hankcs.hanlp.summary;

import com.hankcs.hanlp.classification.features.BM25Feature;
import com.hankcs.hanlp.dictionary.stopword.CoreStopWordDictionary;
import com.hankcs.hanlp.seg.common.Term;
import com.hankcs.hanlp.tokenizer.StandardTokenizer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @User krisjin
 * @date 2020/9/10
 */
public class BM25Test {


    @Test
    public void test() {
        String default_sentence_separator = "[，,。:：“”？?！!；;]";
        String str = "首个大考来自当年让张勇在阿里巴巴站稳脚跟的双十一。2019年的这个夜晚没有了马云，但张勇治下的巨头电商还是交出了2684亿的惊人数字，将其他对手远远甩在身后。逍遥子乐得逍遥，甚至将最后的高光时刻留给了手下头号人物，天猫、淘宝总裁蒋凡。一个月后，张勇再次站在舞台中央。阿里巴巴赴港二次上市时，马云依然没有露面，仅仅发来了祝福视频。在港交所的舞台，张勇说，“香港，我们回来了。”";
        String str2 = "这两个关键节点，张勇都交出了完美答卷。顺势而动，2019年年底张勇以全员信的形式宣布新一轮的组织架构变革，向其直接汇报权限的高管有所减少，蒋凡、戴珊作为集团代表，拥有对部分业务群分管的更高权限。此次组织架构调整深深打上了张勇的个人烙印，他在阿里巴巴新时代提出的“商业操作系统”成为调整重点。";
        List<String> docList = new ArrayList<String>();
        docList.add(str);
        docList.add(str2);
        docList.add(str2);
        docList.add(str2);
        docList.add(str2);
        docList.add(str);
        docList.add(str);
        docList.add(str);
        docList.add(str);
        docList.add(str);
        docList.add(str);
        docList.add(str);
        docList.add(str);
        docList.add(str);

        String term = "巨头";

        List<List<String>> docs = convertSentenceListToDocument(docList);

        BM25 bm25 = new BM25(docs);
        List<String> q = new ArrayList<String>();
        q.add(term);
        double scores = bm25.sim(q, 0);
        System.err.println(scores);
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


    @Test
    public void bm25FeatureTest() {
        String str = "7月21日，渤海海况恶劣，至少发生3起沉船事故，10余名船员危在旦夕。危急时刻，中国海油渤海油田再次行动起来，紧急调配救援力量救起10名遇险人员。"
            + "21日一早，一阵急促的铃声，在渤海石油管理局总值班室骤然响起。这是天津海上搜救中心打来的电话。正在值班的作业协调部主管邬礼凯心里“咯噔”一下——天津海上搜救中心称，"
            + "在“海洋石油932”平台西南方7海里处，一艘货轮遇险、处于倾覆边缘，4名船员命悬一线。时间就是生命！邬礼凯立即组织海上救援力量，立即驰奔事故发生地点。"
            + "“滨海264”船接到任务单后，仅一个小时便抵达事故现场。此时，货船已完全倾覆。“滨海264”立刻开展救援工作，仅25分钟便将4人全部救出。";
        String query = "4名船员命悬一线";

        double score = BM25Feature.sim(query, str);
        System.out.println(score);
    }

}
