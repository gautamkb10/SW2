import java.util.Comparator;

import components.map.Map;
import components.map.Map.Pair;
import components.map.Map1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;
import components.sortingmachine.SortingMachine;
import components.sortingmachine.SortingMachine1L;

/**
 * A Java program that generates a tag cloud from a given input text.
 *
 * @author Keshab Gautam and Tej Patel
 */
public final class TagCloudGenerator {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private TagCloudGenerator() {
        // no code needed here
    }

    /**
     * Alphabetizes the words in our Map.
     */
    private static class ALPHABETIZE
            implements Comparator<Map.Pair<String, Integer>> {

        @Override
        public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {

            return o1.key().compareToIgnoreCase(o2.key());
        }
    }

    /**
     * Sorts the map by largest count of words to smallest.
     */
    private static class COUNTSORT
            implements Comparator<Map.Pair<String, Integer>> {

        @Override
        public int compare(Pair<String, Integer> o1, Pair<String, Integer> o2) {

            return o2.value().compareTo(o1.value());
        }
    }

    /**
     * Sorts {@code allWordsMap} into a SortingMachine according to
     * {@code countSort}.
     *
     * @param allWordsMap
     *            Map containing all words with their occurrences.
     *
     * @param numWordsWanted
     *            Number of words wanted by the user.
     *
     * @param countSort
     *            Comparator that sorts numbers in a descending order
     *
     * @param wordSort
     *            Comparator that sorts words alphabetically
     *
     * @return SortingMachine with alphabetically most frequent words sorted.
     *
     */

    private static SortingMachine<Map.Pair<String, Integer>> sortFrequentWords(
            Map<String, Integer> allWordsMap, int numWordsWanted,
            Comparator<Map.Pair<String, Integer>> countSort,
            Comparator<Map.Pair<String, Integer>> wordSort) {

        SortingMachine<Map.Pair<String, Integer>> occurrenceSorted;
        occurrenceSorted = new SortingMachine1L<>(countSort);

        SortingMachine<Map.Pair<String, Integer>> wordSorted = new SortingMachine1L<>(
                wordSort);

        for (Map.Pair<String, Integer> pair : allWordsMap) {

            occurrenceSorted.add(pair);
        }

        occurrenceSorted.changeToExtractionMode();

        for (int i = 0; i < numWordsWanted; i++) {

            wordSorted.add(occurrenceSorted.removeFirst());
        }

        wordSorted.changeToExtractionMode();

        return wordSorted;

    }

    /**
     * adds the separator characters and counts the number of time a word
     * appears in the user inputed text file.
     *
     * @param wordMap
     *            map of words from the user file
     * @param in
     *            text file provided by the user
     * @requires size of inputtedWord to be greater than or equal to zero
     * @ensure all the words get inputed from user text file to inputtedWord
     */
    private static void countWord(Map<String, Integer> wordMap,
            SimpleReader in) {
        Set<Character> separators = new Set1L<>();
        separators.add(' ');
        separators.add('.');
        separators.add('-');
        separators.add('*');
        separators.add('_');
        separators.add('(');
        separators.add(')');
        separators.add('+');
        separators.add('=');
        separators.add('^');
        separators.add('~');
        separators.add('`');
        separators.add(',');
        separators.add('/');
        separators.add(':');
        separators.add(';');
        separators.add('\'');
        separators.add('?');
        separators.add('!');
        separators.add('0');
        separators.add('1');
        separators.add('2');
        separators.add('3');
        separators.add('4');
        separators.add('5');
        separators.add('6');
        separators.add('7');
        separators.add('8');
        separators.add('9');
        separators.add(']');
        separators.add('[');
        separators.add('}');
        separators.add('{');

        while (!in.atEOS()) {
            String words = in.nextLine();
            int index = 0;
            while (index < words.length()) {
                String word = wordSeparator(words, index, separators)
                        .toLowerCase();
                if (!separators.contains(word.charAt(0))) {
                    if (wordMap.hasKey(word)) {
                        wordMap.replaceValue(word, wordMap.value(word) + 1);
                    } else {
                        wordMap.add(word, 1);
                    }
                }
                index += word.length();
            }
        }
    }

    /**
     * This method finds and returns the first word starting at the given
     * position in the given text.
     *
     * @param text
     *            the string to get the word
     * @param position
     *            Starting position to check the word
     * @param separators
     *            set of the separators
     * @requires the position is not empty and position is less than the length
     *           of the text
     * @return the first word in the starting position
     */
    private static String wordSeparator(String text, int position,
            Set<Character> separators) {
        assert text != null : "Violation of: text is not null";
        assert separators != null : "Violation of: separators is not null";
        assert 0 <= position : "Violation of: 0 <= position";
        assert position < text.length() : "Violation of: position < |text|";

        char firstWord = text.charAt(position);
        String nextWord = "";
        int i = position + 1;
        nextWord = nextWord + firstWord;
        if (!separators.contains(firstWord)) {
            while (i < text.length() && !separators.contains(firstWord)) {
                firstWord = text.charAt(i);
                if (!separators.contains(firstWord)) {
                    String x = "" + firstWord;
                    nextWord = nextWord.concat(x);

                }
                i++;
            }
        } else {
            while (i < text.length() && separators.contains(firstWord)) {
                firstWord = text.charAt(i);
                if (separators.contains(firstWord)) {
                    String x = "" + firstWord;
                    nextWord = nextWord.concat(x);
                }
                i++;
            }
        }
        return nextWord;
    }

    /**
     * Output of the header.
     *
     * @param output
     *            output
     * @param numOfWord
     *            the number of words to be included in the generated tag cloud
     * @param inputFile
     *            the name of an input file from the user
     */
    public static void header(SimpleWriter output, int numOfWord,
            String inputFile) {
        output.println("<html>");
        output.println("<head>");
        output.println("<title>Top " + numOfWord + " words in " + inputFile
                + "</title>");
        output.println("<link href=\"data/tagcloud.css\" rel=\"stylesheet\""
                + "type=\"text/css\">");
        output.println("</head>");
        output.println("<body>");
        output.println(
                "<h2>Top " + numOfWord + " words in " + inputFile + "</h2>");
        output.println("<hr>");
        output.println("<div class =\"cdiv\">");
        output.println("<p class =\"cbox\">");
    }

    /**
     * End the output.
     *
     * @param output
     *            output
     */
    public static void end(SimpleWriter output) {
        output.println("</p>");
        output.println("</div>");
        output.println("</body>");
        output.println("</html>");

    }

    /**
     * Sorts the number of words and prints out in the output file.
     *
     * @param wordMap
     *            map of words from the user file
     * @param sortedWords
     *            sorted words
     * @param output
     *            output file to display the words
     */
    private static void font(Map<String, Integer> wordMap,
            SortingMachine<Map.Pair<String, Integer>> sortedWords,
            SimpleWriter output) {

        Map.Pair<String, Integer> wordPair = wordMap.removeAny();

        int max = wordPair.value();
        int min = wordPair.value();
        final int fontMin = 11;
        final int fontMax = 48;

        while (wordMap.size() > 0) {

            wordPair = wordMap.removeAny();

            if (wordPair.value() > max) {
                max = wordPair.value();
            } else if (wordPair.value() < min) {
                min = wordPair.value();
            }
        }

        int fontRange = (max - min) / (fontMax - fontMin);

        while (sortedWords.size() > 0) {

            Map.Pair<String, Integer> pair = sortedWords.removeFirst();

            int fontSize = (pair.value() / fontRange) + fontMin;

            output.println("<span style=\"cursor:default\" class=\"f" + fontSize
                    + "\" title=\"count: " + pair.value() + "\">" + pair.key()
                    + "</span>");
        }

    }

    /**
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        SimpleWriter out = new SimpleWriter1L();
        SimpleReader in = new SimpleReader1L();
        //get an input file name from the user
        out.print("Please enter an input file name: ");
        String inputFileName = in.nextLine();
        //get an output file name from the user
        out.print("Please enter an output file name: ");
        String outputFileName = in.nextLine();

        SimpleReader input = new SimpleReader1L(inputFileName);
        SimpleWriter output = new SimpleWriter1L(outputFileName);

        Comparator<Map.Pair<String, Integer>> countSort = new ALPHABETIZE();
        Comparator<Map.Pair<String, Integer>> wordSort = new COUNTSORT();

        Map<String, Integer> wordMap = new Map1L<>();

        countWord(wordMap, input);

        out.print(
                "Please enter the number of words in the generated tag cloud: ");
        int numberOfWord = in.nextInteger();
        while (wordMap.size() < numberOfWord) {
            out.println("Your input file does not contain that many words.");
            out.print(
                    "Please enter the number of words in the generated tag cloud: ");
            numberOfWord = in.nextInteger();
        }

        SortingMachine<Map.Pair<String, Integer>> sortedWords = sortFrequentWords(
                wordMap, numberOfWord, wordSort, countSort);

        header(output, numberOfWord, inputFileName);
        font(wordMap, sortedWords, output);
        end(output);

        input.close();
        output.close();
        out.close();
        in.close();
    }

}
