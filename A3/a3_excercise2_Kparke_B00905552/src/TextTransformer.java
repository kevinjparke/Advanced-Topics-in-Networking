public class TextTransformer {
    final private char PADDING = 'X';
    final private char SPLITTING = 'Q';

    //Default C'tor
    public TextTransformer() {}

    public String removeSubsequentDuplicateChar(String text) {
        StringBuilder result = new StringBuilder();
        result.append(text.charAt(0));
        for (int i = 1; i < text.length(); i++){
            if (result.toString().contains(String.valueOf(text.charAt(i)))){
                //Continue
            } else {
                result.append(text.charAt(i));
            }
        }
        return result.toString();
    }

    public String padWithAlphabet(String text) {
        StringBuilder result = new StringBuilder();
        char letter = 'A';
        result.append(text);

        while (result.length() < 25) {
            if (letter == 'J') {
                letter++;
                continue;
            }
            if (result.toString().contains(String.valueOf(letter))) {
                letter++;
            } else {
                result.append(letter);
                letter++;
            }
        }
        return result.toString();
    }

    public String[] createPairs(String text) {
        int size = (int) Math.ceil(text.length() / 2.0);
        String[] pairs = new String[size];

        int index = 0;
        for (int i = 0; i < text.length(); i += 2) {
            if (i == text.length() - 1) {
                pairs[index] = text.charAt(i) + "Q";
            } else if (text.charAt(i) == text.charAt(i + 1)) {
                pairs[index] = text.charAt(i) + "X";
                i--;
            } else {
                pairs[index] = text.substring(i, i + 2);
            }
            // Check if index is within bounds before incrementing
            if (index < size - 1) {
                index++;
            }
        }
        return pairs;
    }

    public String removeSpecialCharacters(String text) {
        return text.replaceAll("[\\s,.\n]", "");
    }

    public void printPairs(String[] pairsArray) {
        for (String element : pairsArray) {
            System.out.println(element);
        }
    }
}
