package gh2;

import deque.Deque;
import deque.LinkedListDeque;

public class GuitarString {
    /**
     * Constants. Do not change. In case you're curious, the keyword final
     * means the values cannot be changed at runtime. We'll discuss this and
     * other topics in lecture on Friday.
     */
    private static final int SR = 44100;      // Sampling Rate
    private static final double DECAY = .996; // energy decay factor

    /* Buffer for storing sound data. */
    private Deque<Double> buffer;
    private int initialSize;

    /* Create a guitar string of the given frequency.  */
    public GuitarString(double frequency) {
        buffer = new LinkedListDeque<>();
        this.initialSize = (int) Math.round(SR / frequency);
    }

    /* Pluck the guitar string by replacing the buffer with white noise. */
    public void pluck() {
        for (int i = 0; i < initialSize; i++) {
            double r = Math.random() - 0.5;
            buffer.addLast(r);
        }
    }

    /* Advance the simulation one time step by performing one iteration of
     * the Karplus-Strong algorithm.
     */
    public void tic() {
        double firstNum = buffer.removeFirst();
        double newNum = (firstNum + sample()) * DECAY;
        buffer.addLast(newNum);
    }

    /* Return the double at the front of the buffer. */
    public double sample() {
        return buffer.get(0);
    }
}