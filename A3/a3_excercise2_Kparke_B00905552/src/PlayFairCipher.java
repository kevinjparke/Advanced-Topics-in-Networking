public class PlayFairCipher {
    public static final PlayFairCipher instance = null;

    public char[][] generateKeyMatrix(String key){
        TextTransformer tf = new TextTransformer();
        char[][] matrix = new char[5][5];
        int row = 5;
        int col = 5;
        String keyTransformed = tf.removeSubsequentDuplicateChar(key);
        String keyWithAlphabet = tf.padWithAlphabet(keyTransformed);

        int index = 0;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrix[i][j] = keyWithAlphabet.charAt(index);
                index++;
            }
        }
        return matrix;
    }

    /**
     * https://www.geeksforgeeks.org/java-program-to-enode-a-message-using-playfair-cipher/
     * @param plainText
     * @param key
     * @return
     */
    public String encipher(String plainText, char[][] key){
        TextTransformer tf = new TextTransformer();
        String transformedPlainText = tf.removeSpecialCharacters(plainText).toUpperCase();
        StringBuilder cipherText = new StringBuilder();
        String[] plainTextPairs = tf.createPairs(transformedPlainText);

        for(int i = 0; i < plainTextPairs.length; i++) {
            //Find row and col of pair
            int[] ch1 = getCharPosition(plainTextPairs[i].charAt(0), key);
            int[] ch2 = getCharPosition(plainTextPairs[i].charAt(1), key);

            //Check if Characters are in the same row
            if (ch1[0] == ch2[0]) {
                ch1[1] = (ch1[1] + 1) % 5;
                ch2[1] = (ch2[1] + 1) % 5;
            } else if(ch1[1] == ch2[1]) {
                ch1[0] = (ch1[0] + 1) % 5;
                ch2[0] = (ch2[0] + 1) % 5;
            } else {
                int temp = ch1[1];
                ch1[1] = ch2[1];
                ch2[1] = temp;
            }
            cipherText.append(String.valueOf(key[ch1[0]][ch1[1]]) + String.valueOf(key[ch2[0]][ch2[1]]));
        }
        return cipherText.toString();
    }

    /**
     * https://www.geeksforgeeks.org/java-program-to-enode-a-message-using-playfair-cipher/
     * @param cipherText
     * @param key
     * @return
     */
    public String decipher(String cipherText, char[][] key) {
        TextTransformer tf = new TextTransformer();
        StringBuilder plainText = new StringBuilder();
        String[] cipherTextPairs = tf.createPairs(cipherText);

        for(int i = 0; i < cipherTextPairs.length; i++) {
            //Find row and col of pair
            int[] ch1 = getCharPosition(cipherTextPairs[i].charAt(0), key);
            int[] ch2 = getCharPosition(cipherTextPairs[i].charAt(1), key);

            //Check if Characters are in the same row
            if (ch1[0] == ch2[0]) {
                ch1[1] = (ch1[1] - 1 + 5) % 5;
                ch2[1] = (ch2[1] - 1 + 5) % 5;
            } else if(ch1[1] == ch2[1]) {
                ch1[0] = (ch1[0] - 1 + 5) % 5;
                ch2[0] = (ch2[0] - 1 + 5) % 5;
            } else {
                int temp = ch1[1];
                ch1[1] = ch2[1];
                ch2[1] = temp;
            }
            plainText.append(String.valueOf(key[ch1[0]][ch1[1]]) + String.valueOf(key[ch2[0]][ch2[1]]));
        }
        return plainText.toString();
    }

    public int[] getCharPosition(char ch, char[][] key) {
        int[] keyPosition = new int[2];
        for (int row = 0; row < 5; row++) {
            for(int col = 0; col < 5; col++) {
                if (key[row][col] == ch) {
                    keyPosition[0] = row;
                    keyPosition[1] = col;
                    break;
                }
            }
        }
        return keyPosition;
    }

    public static PlayFairCipher getInstance() {
        if (instance == null) {
            return new PlayFairCipher();
        }
        return instance;
    }
}
