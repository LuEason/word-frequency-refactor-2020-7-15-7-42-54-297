import java.util.*;

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
                List<WordInfo> wordInfos = getSortedWordInfos(sentence);
                return spliceResult(wordInfos);
            } catch (Exception e) {
                return CALCULATE_ERROR;
            }
        }
    }

    private List<WordInfo> getSortedWordInfos(String sentence) {
        List<String> words = Arrays.asList(sentence.split(SPACE_PATTERN));
        List<WordInfo> wordInfos = new ArrayList<>();
        for (String uniqueWord : new HashSet<>(words)) {
            int count = (int) words.stream().filter(uniqueWord::equals).count();
            wordInfos.add(new WordInfo(uniqueWord, count));
        }
        wordInfos.sort((firstWordInfo, secondWordInfo) -> secondWordInfo.getWordCount() - firstWordInfo.getWordCount());
        return wordInfos;
    }

    private String spliceResult(List<WordInfo> wordInfos) {
        StringJoiner joiner = new StringJoiner(NEW_LINE_DELIMITER);
        for (WordInfo wordInfo : wordInfos) {
            String oneLine = wordInfo.getWord() + SPACE +wordInfo.getWordCount();
            joiner.add(oneLine);
        }
        return joiner.toString();
    }

}
