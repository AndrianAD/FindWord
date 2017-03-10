import javax.swing.*;
import java.util.Scanner;

public class Cod extends JFrame{

    public Cod() {
    }

    public int function(Scanner scan,String text) {
        int c = 0;
        String[] masiv = new String[200];
        String str = null;
        while (scan.hasNext()) {
            c++;
            str = scan.next();
            //  System.out.println(str);
            masiv[c] = str;

        }
        return ChekWord(c, masiv, text);
    }

    private int ChekWord(int c, String[] masiv, String text) {
        int count = 0;
        for (int i = 1; (i <= c); i++) {
            String inFileWord = masiv[i].toLowerCase();
            String toFindWord = text.toLowerCase();

            if (inFileWord.equals(toFindWord)) {
                count++;
            }
        }
        if (count == 0) {
            System.out.println("Нет совпадений");
        } else {
            System.out.println("Есть совпадения");
            System.out.println(count);

        }

        return count;
    }
}




