import org.junit.Assert;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.util.Objects;
import java.util.Scanner;

import static org.junit.Assert.*;


public class TailTest {
    String testValues[][];
    String results[][];

    @org.junit.Before
    public void setUp() throws Exception {
        testValues = new String[][]
                {
                        //"tail -c 3 -o output.txt".split(" "),
                        "tail -n 3 -c 3 -o output.txt 1.txt 2.txt 3.txt".split(" "),
                        "tail -c 3 -o output.txt 1.txt 2.txt 3.txt".split(" "),
                        "tail -n 3 -o output.txt 1.txt 2.txt 3.txt".split(" "),
                        "tail -o output.txt 1.txt 2.txt 3.txt".split(" "),
                        "-c 3 1.txt 2.txt 3.txt".split(" "),
                        "-n 3 1.txt 2.txt 3.txt".split(" "),
                };
        results = new String[][]{
                //new String[]{"ld!"},
                new String[]{"You set -n and -c flags at the same time!"},
                new String[]{
                        "1.txt",
                        "ый.",
                        "2.txt",
                        "ем.",
                        "3.txt",
                        "оры"},
                new String[]{
                        "1.txt",
                        "Ты песен Грузии печальной:" ,
                        "Напоминают мне оне",
                        "Другую жизнь и берег дальный.",
                        "2.txt",
                        "В душе моей угасла не совсем;",
                        "Но пусть она вас больше не тревожит;",
                        "Я не хочу печалить вас ничем.",
                        "3.txt",
                        "Еще ты дремлешь, друг прелестный –",
                        "Пора, красавица, проснись;",
                        "Открой сомкнуты негой взоры"
                        },
                new String[]{
                        "1.txt",
                        "Не пой, красавица, при мне",
                        "Ты песен Грузии печальной:" ,
                        "Напоминают мне оне",
                        "Другую жизнь и берег дальный.",
                        "2.txt",
                        "Я вас любил: любовь еще, быть может ",
                        "В душе моей угасла не совсем;",
                        "Но пусть она вас больше не тревожит;",
                        "Я не хочу печалить вас ничем.",
                        "3.txt",
                        "Мороз и солнце; день чудесный!",
                        "Еще ты дремлешь, друг прелестный –",
                        "Пора, красавица, проснись;",
                        "Открой сомкнуты негой взоры"
                },


                new String[]{"Unknown command!"},
                new String[]{"Unknown command!"}
        };
    }

    @Test
    public void getLines() throws Exception {
        for (int i = 0; i < testValues.length; i++) {
            try {
                Params p = new Params(testValues[i]);
                Tail tail = new Tail(p);
                tail.GetLines();
//              (проверка консоли)
//                if(p.inFileNames.size()==0) {
//                    String data = "Hello,World!";
//                    InputStream stdin = System.in;
//                    try {
//                        System.setIn(new ByteArrayInputStream(data.getBytes()));
//                        Scanner scanner = new Scanner(System.in);
//                        System.out.println(scanner.nextLine());
//                    } finally {
//                        System.setIn(stdin);
//                    }
//                }
                BufferedReader reader = new BufferedReader(new FileReader(p.outFile));
                String line = "";
                int j = 0;
                while ((line = reader.readLine()) != null) {
                    Assert.assertEquals(results[i][j++], line);
                }


            } catch (Exception ex) {
                Assert.assertEquals(results[i][0], ex.getMessage());
            }
        }
    }

}