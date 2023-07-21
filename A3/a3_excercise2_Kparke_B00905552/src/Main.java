public class Main {
    public static void main(String[] args) { new Main().run(); }

    private void run(){
        char[][] key = PlayFairCipher.getInstance().generateKeyMatrix("RAYQUAZA");
        String cipherText = PlayFairCipher.getInstance().encipher("POKEMON TOWER DEFENSE\n" +
                "YOUR MISSION IN THIS FUN STRATEGY TOWER DEFENSE GAME IS TO HELP PROFESSOR OAK\n" +
                "TO STOP ATTACKS OF WILD RATTATA. SET OUT ON YOUR OWN POKEMON JOURNEY, TO\n" +
                "CATCH AND TRAIN ALL POKEMON AND TRY TO SOLVE THE MYSTERY BEHIND THESE ATTACKS.\n" +
                "YOU MUST PLACE POKEMON CHARACTERS STRATEGICALLY ON THE BATTLEFIELD SO THAT\n" +
                "THEY STOP ALL WAVES OF ENEMY ATTACKER\n" +
                "DURING THE BATTLE YOU WILL LEVEL UP AND EVOLVE YOUR POKEMON. YOU CAN ALSO\n" +
                "CAPTURE OTHER POKEMON DURING THE BATTLE AND ADD THEM TO YOUR TEAM. USE YOUR\n" +
                "MOUSE TO PLAY THE GAME.\n" +
                "GOOD LUCK", key);
        String plainText = PlayFairCipher.getInstance().decipher(cipherText, key);
        System.out.println("Cipher text: " + cipherText);
        System.out.println("Plain text after encipher function: " + plainText);
    }

    private void printMatrix(char[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }
}