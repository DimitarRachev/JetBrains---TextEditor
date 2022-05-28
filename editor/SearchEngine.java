package editor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchEngine {
    private String text;
    private String searchFor;
    private Boolean isRegex;
    private int[] lastFind;

    public SearchEngine(String text, String searchFor, Boolean isRegex) {
        this.text = text;
        this.searchFor = searchFor;
        this.isRegex = isRegex;
    }


    public int[] findFirst() {
        if (text == null || searchFor == null) {
            return null;
        }
        if (isRegex) {
            lastFind = searchWithRegex(0);
        } else {
            lastFind = searchPlainText(0);
        }
        return lastFind;
    }

    private int[] searchWithRegex(int start) {
        Pattern pattern = Pattern.compile(searchFor);
        Matcher matcher = pattern.matcher(text);
        matcher.region(start, text.length());

        if (!matcher.find()) {
            return null;
        }
        return new int[]{matcher.start(), matcher.end() - matcher.start()};
    }

    private int[] searchPlainText(int start) {
        int index = text.indexOf(searchFor, start);
        return new int[]{index, searchFor.length()};
    }

    public int[] findNext(String text, String searchFor, Boolean isRegex) {
        if (areChanged(text, searchFor, isRegex)) return findFirst();
        if (isRegex) {
            lastFind = searchWithRegex(lastFind[0] + lastFind[1]);
        } else {
            lastFind = searchPlainText(lastFind[0] + lastFind[1]);
        }
        return lastFind;
    }

    private boolean areChanged(String text, String searchFor, Boolean isRegex) {
        if (!text.equals(this.text) || !searchFor.equals(this.searchFor) || isRegex != this.isRegex) {
            this.text = text;
            this.searchFor = searchFor;
            this.isRegex = isRegex;
            return true;
        }
        return false;
    }

    public int[] findPrevious(String text, String searchFor, Boolean isRegex) {
        if (areChanged(text, searchFor, isRegex)) return findFirst();

        if (isRegex) {
            lastFind = getPreviousWithRegEx(lastFind[0]);
        } else {
            lastFind = getPreviousPlainText(lastFind[0]);
        }

        return lastFind;
    }

    private int[] getPreviousWithRegEx(int lastIndex) {
        Pattern pattern = Pattern.compile(searchFor);
        Matcher matcher = pattern.matcher(text);
        matcher.region(0, lastIndex);

        int start = -1;
        int end = -1;
        while (matcher.find()){
            start = matcher.start();
            end = matcher.end();;
        }
        if (start == -1) {
            return null;
        } else {
            return new int[]{start, end - start};
        }


    }

    private int[] getPreviousPlainText(int lastIndex) {
        String subString = text.substring(0, lastIndex);
        int index = subString.lastIndexOf(searchFor);
        return new int[]{index, searchFor.length()};
    }
}
