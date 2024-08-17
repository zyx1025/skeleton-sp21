package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeAList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeAListConstruction();
    }

    public static void timeAListConstruction() {
        int time = 1000;
        AList<Integer> nsList = new AList<>();
        AList<Double> timeList = new AList<>();
        AList<Integer> operationNumList = new AList<>();
        for (int i = 1; i <= 8; i++) {
            AList aList = new AList<>();
            nsList.addLast(time);
            int op = 0;

            Stopwatch sw = new Stopwatch();
            for (int j = 0; j < time; j++) {
                aList.addLast(1);
                op++;
            }
            double timeInSeconds = sw.elapsedTime();
            timeList.addLast(timeInSeconds);
            operationNumList.addLast(op);
            time *= 2;
        }

        printTimingTable(nsList,timeList,operationNumList);


    }
}
