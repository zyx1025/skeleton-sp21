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
        BuggyAList<Integer> buggyAList = new BuggyAList<>();
        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);

                L.addLast(randVal);
                buggyAList.addLast(randVal);

                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                assertEquals(L.size(),buggyAList.size());
            }else if (operationNumber == 2){
                //getLast
                int last1 = -1;
                int last2 = -1;
                if(L.size() > 0){
                    last1 = L.getLast();
                }
                if ( buggyAList.size()> 0 ){
                    last2 = buggyAList.getLast();
                }
                if (last1!=-1 && last2!=-1){
                    assertEquals(last1,last2);
                }
            }else{
                //removeLast
                if(L.size() > 0){
                    L.removeLast();
                }
                if(buggyAList.size() > 0){
                    buggyAList.removeLast();
                }
            }
        }
    }
}
