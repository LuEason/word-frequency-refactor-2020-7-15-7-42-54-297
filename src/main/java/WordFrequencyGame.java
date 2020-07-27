import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.StringJoiner;

public class WordFrequencyGame {

    private static final String SPACE_PATTERN = "\\s+";
    private static final String NEW_LINE_DELIMITER = "\n";
    private static final String SPACE = " ";
    private static final String CALCULATE_ERROR = "Calculate Error";

    public String getResult(String sentence) {


        if (sentence.split(SPACE_PATTERN).length==1) {
            return sentence + " 1";
        } else {

            try {

                String[] words = sentence.split(SPACE_PATTERN);

                List<WordInfo> wordInfos = new ArrayList<>();
                for (String word : words) {
                    WordInfo wordInfo = new WordInfo(word, 1);
                    wordInfos.add(wordInfo);
                }

                Map<String, List<WordInfo>> wordInfoMap =getListMap(wordInfos);

                List<WordInfo> list = new ArrayList<>();
                for (Map.Entry<String, List<WordInfo>> entry : wordInfoMap.entrySet()) {
                    WordInfo input = new WordInfo(entry.getKey(), entry.getValue().size());
                    list.add(input);
                }
                wordInfos = list;

                wordInfos.sort((firstWordInfo, secondWordInfo) -> secondWordInfo.getWordCount() - firstWordInfo.getWordCount());

                StringJoiner joiner = new StringJoiner(NEW_LINE_DELIMITER);
                for (WordInfo w : wordInfos) {
                    String s = w.getWord() + SPACE +w.getWordCount();
                    joiner.add(s);
                }
                return joiner.toString();
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private Map<String, List<WordInfo>> getListMap(List<WordInfo> inputList) {
        Map<String, List<WordInfo>> map = new HashMap<>();
        for (WordInfo input : inputList){
            if (!map.containsKey(input.getWord())) {
                ArrayList arr = new ArrayList<>();
                arr.add(input);
                map.put(input.getWord(), arr);
            }
            else {
                map.get(input.getWord()).add(input);
            }
        }
        return map;
    }
}
