public class Main {
    public static void main(String[] args) { new Main().run(); }

    private void run() {
        final int PLAINTEXT = 0b11001100;
        int[] roundKeys = { 0b10101010, 0b01100110, 0b11001100, 0b00110011 };
        final int IV = 0b10101010;
        final int ROUNDS = 4;

        encipherCBC(PLAINTEXT, roundKeys, IV, ROUNDS);
        encipherOFB(PLAINTEXT, roundKeys, IV, ROUNDS);
    }

    private String encipherCBC(int plaintext, int[] roundKeys, int iv, int numberOfRounds) {
        String ciphertext = "";
        int temp = iv;
        System.out.println("----- Encipher CBC Mode -----\n\n");
        for(int i = 0; i < numberOfRounds; i++) {
            System.out.println("----- Round " + (i + 1) + "-----");
            System.out.println("Plaintext: " + Integer.toBinaryString(plaintext));
            System.out.println("Temp: " + Integer.toBinaryString(temp));
            //XOR plaintext with iv
            int b1 = plaintext ^ temp;
            System.out.println("Plaintext XOR temp: " + Integer.toBinaryString(b1));

            //XOR b1 with round key
            int blockCipherEncryption = b1 ^ roundKeys[i];
            System.out.println("Block Cipher Encryption: " + Integer.toBinaryString(b1) + " XOR round key: " + Integer.toBinaryString(roundKeys[i]) + " = " + Integer.toBinaryString(blockCipherEncryption));

            //Circular left shift by 2
            int result = ((blockCipherEncryption << 2) | (blockCipherEncryption >>> (8 - 2))) & 0xFF;
            System.out.println("Result of CLS by 2 positions: " + Integer.toBinaryString(result));

            //Change IV in loop to ciphertext.
            temp = result;

            //Add to current cipher block
            ciphertext += String.format("%8s", Integer.toBinaryString(result)).replace(' ', '0');

            //Print after each round
            System.out.println("Round " + (i + 1) + " Cipher Text = " + ciphertext + "\n");
        }
        return ciphertext;
    }

    private String encipherOFB(int plaintext, int[] roundKeys, int iv, int numberOfRounds) {
        String ciphertext = "";
        int temp = iv;
        System.out.println("\n----- Encipher OFB Mode -----\n");
        for(int i = 0; i < numberOfRounds; i++) {
            System.out.println("----- Round " + (i + 1) + " -----");
            System.out.println("Plaintext: " + Integer.toBinaryString(plaintext));
            System.out.println("Temp: " + Integer.toBinaryString(temp));
            System.out.println("Round Key: " + Integer.toBinaryString(roundKeys[i]));

            //XOR plaintext with Round Key
            int step1 = temp ^ roundKeys[i];
            System.out.println("Temp XOR roundKey: " + Integer.toBinaryString(step1));

            //Circular left shift by 2
            int b1 = ((step1 << 2) | (step1 >>> (8 - 2))) & 0xFF;
            System.out.println("B1 (Result of CLS by 2 positions): " + Integer.toBinaryString(b1));

            //Change IV in loop to ciphertext.
            temp = b1;

            //XOR b1 with Plaintext
            int roundCipherText = b1 ^ plaintext;
            System.out.println("B1 XOR plaintext: " + Integer.toBinaryString(roundCipherText));

            //Add to current cipher block
            ciphertext += String.format("%8s", Integer.toBinaryString(roundCipherText)).replace(' ', '0');

            //Print after each round
            System.out.println("Round " + (i + 1) + " Cipher Text = " + ciphertext);
            System.out.println();
            System.out.println();
        }
        return ciphertext;
    }
}