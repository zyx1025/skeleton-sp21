package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing aListNoResizing = new AListNoResizing<>();
        BuggyAList buggyAList = new BuggyAList<>();

        int[] addNumbers = new int[]{4,5,6};
        for (int num:addNumbers){
            aListNoResizing.addLast(num);
            buggyAList.addLast(num);
        }

        int n = 3;
        for (int i = 0; i < n; i++) {
            assertEquals(aListNoResizing.removeLast(),buggyAList.removeLast());
        }
    }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();

        int N = 500;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 2);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                System.out.println("size: " + size);
            }
        }
    }
}
