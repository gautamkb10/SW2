import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Scanner;

import components.map.Map;
import components.map.Map1L;
import components.queue.Queue;
import components.queue.Queue1L;
import components.set.Set;
import components.set.Set1L;
import components.simplereader.SimpleReader;
import components.simplereader.SimpleReader1L;
import components.simplewriter.SimpleWriter;
import components.simplewriter.SimpleWriter1L;

/**
 * A Java program that counts word occurrences in a given input file and outputs
 * an HTML document with a table of the words and counts listed in alphabetical
 * order.
 *
 * @author Keshab Gautam
 */
public final class WordCounter {

    /**
     * Default constructor--private to prevent instantiation.
     */
    private WordCounter() {
        // no code needed here
    }

    /**
     * Compares two strings in order to alphabetize a queue to follow one of the
     * requirements.
     */
    private static class ALPHABETIZE implements Comparator<String> {

        @Override
        public int compare(String str1, String str2) {
            return str1.compareToIgnoreCase(str2);
        }
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

        //Queue<String> listOfWords = new Queue1L<>();
        while (!in.atEOS()) {
            String words = in.nextLine();
            int index = 0;
            while (index < words.length()) {
                String word = wordSeparator(words, index, separators);
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
     * Main method.
     *
     * @param args
     *            the command line arguments; unused here
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        SimpleWriter out = new SimpleWriter1L();
        //get an input file name from the user
        out.print("Please enter an input file name: ");
        String inputFileName = in.nextLine();
        //get an output file name from the user
        out.print("Please enter an output file name: ");
        String outputFileName = in.nextLine();

        SimpleReader input = new SimpleReader1L(inputFileName);
        SimpleWriter output = new SimpleWriter1L(outputFileName);

        Map<String, Integer> wordMap = new Map1L<String, Integer>();
        Queue<String> listOfWords = new Queue1L<>();
        countWord(wordMap, input);
        for (Map.Pair<String, Integer> wordList : wordMap) {
            listOfWords.enqueue(wordList.key());
        }

        Comparator<String> t = new ALPHABETIZE();
        listOfWords.sort(t);

        output.println("<html>");
        output.println("<head>");
        output.println("<title>Words counted in " + inputFileName + "</title>");
        output.println("</head>");
        output.println("<body>");
        output.println("<h2>Words counted in " + inputFileName + "</h2>");
        output.println("<hr />");
        output.println(
                "<table border = \"1\"><tr><th>Words</th><th>Counts</th></tr>");
        while (listOfWords.length() > 0) {
            String words = listOfWords.dequeue();
            output.println("<tr>");
            output.println("<td>" + words + "</td>");
            output.println("<td>" + wordMap.value(words) + "</td>");
            output.println("</tr>");
        }
        output.println("</table>");
        output.println("</body>");
        output.println("</html>");

        out.close();
        in.close();
    }

}
