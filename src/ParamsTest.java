import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by LaneBoy on 30.05.1.txt7.
 */
public class ParamsTest {
    String testValues[][];
    String results[];

    @org.junit.Before
    public void setUp() throws Exception {
        testValues = new String[][]
                {
                        "tail -n 3 -c 3 -o output.txt 1.txt 2.txt 3.txt".split(" "),
                        "tail -c 3 -o output.txt 1.txt 2.txt 3.txt".split(" "),
                        "tail -n 3 -o output.txt 1.txt 2.txt 3.txt".split(" "),
                        "tail -o output.txt 1.txt 2.txt 3.txt".split(" "),
                        "tail 1.txt 2.txt 3.txt".split(" "),
                        "tail -o output.txt".split(" "),
                        "tail".split(" "),
                        "tail -c 3 1.txt 2.txt 3.txt".split(" "),
                        "tail -n 3 1.txt 2.txt 3.txt".split(" "),
                        "-c 3 1.txt 2.txt 3.txt".split(" "),
                        "-n 3 1.txt 2.txt 3.txt".split(" "),
                };
        results = new String[]{
                "You set -n and -c flags at the same time!",
                "c:3 n:-1 out:output.txt in: 1.txt  2.txt  3.txt ",
                "c:-1 n:3 out:output.txt in: 1.txt  2.txt  3.txt ",
                "c:-1 n:-1 out:output.txt in: 1.txt  2.txt  3.txt ",
                "c:-1 n:-1 out: in: 1.txt  2.txt  3.txt ",
                "c:-1 n:-1 out:output.txt in:",
                "c:-1 n:-1 out: in:",
                "c:3 n:-1 out: in: 1.txt  2.txt  3.txt ",
                "c:-1 n:3 out: in: 1.txt  2.txt  3.txt ",
                "Unknown command!",
                "Unknown command!"
        };
    }


    @org.junit.After
    public void tearDown() throws Exception {

    }

    @org.junit.Test
    public void ParamsTest() {
        for (int i = 0; i < testValues.length; i++) {
            try {
                Params p = new Params(testValues[i]);
                Assert.assertEquals(p.ToStr(), results[i]);

            } catch (Exception ex) {
                Assert.assertEquals(ex.getMessage(), results[i]);
            }
        }
    }

}