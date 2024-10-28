import java.io.File;
import java.io.IOException;

public class PhotoMagicDeluxe{

    static String base64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/";
    public static Picture transform(Picture pic, String password, int tap) {
        String lfsr = convert(password);
        return PhotoMagic.transform(pic, new LFSR(lfsr, tap));
    }

    private static String convert(String password){
        String fullBinary = "";
        for (int i = 0; i < password.length(); i++) {
            char charAt = password.charAt(i);
            //System.out.print(charAt + " ");

            int intToConvert = base64.indexOf(charAt);
            //System.out.print(intToConvert + " => ");

            String temp = String.format("%6s", Integer.toBinaryString(intToConvert)).replaceAll(" ", "0");
            //System.out.println(temp);

            fullBinary += temp;
        }

        return fullBinary;
    }

    public static void main(String[] args) {
        /*String temp = convert("OPENSESAME");

        System.out.println(temp);

        if(temp.equals("001110001111000100001101010010000100010010000000001100000100")){
            System.out.println("DID IT BABYYY");
        }
        else{
            System.out.println("sorry pookie :(");
        }

         */
        Picture decrypted = transform(new Picture("mystery.png"), "OPENSESAME", 58);
        decrypted.show();

        File file3 = new File("notReallyAMysteryAnymoreIsIt.png");
        try {
            file3.createNewFile();
            decrypted.save(file3);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }
}