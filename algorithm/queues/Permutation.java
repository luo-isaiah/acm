import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.StdRandom;

public class Permutation {
    public static void main(String[] args) {
        RandomizedQueue<String> rq = new RandomizedQueue<String>();
        int k = Integer.parseInt(args[0]);
        while (!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        }
        while (k > 0) {
            StdOut.println(rq.dequeue());
            k--;
        }

//        StringBuilder builder = new StringBuilder();
//        int cnt = 0;
//        while (!StdIn.isEmpty()) {
//            builder.append(StdIn.readString()).append(' ');
//            cnt++;
//        }
//        if (builder.length() > 0) builder.deleteCharAt(builder.length() - 1);
//
//        builder.indexOf(" ");
//        String[] strs = builder.toString().split(" "); // splits converted str into a String array
//        int[] indices = StdRandom.permutation(cnt, k);
//
//        for (int i = 0; i < k; ++i) rq.enqueue(strs[indices[i]]);
//        for (String s : rq) StdOut.println(s);
    }
}
