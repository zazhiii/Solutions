
import java.io.*;
import java.util.*;
public class Main {
    static Read rd = new Read();
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static long inf = (long)2e9, INF = (long)2e18;
    public static void main(String[] args) throws IOException {
        int T = 1;
        // T = rd.nextInt();
        while(T --> 0) solve();
        pw.flush();
        pw.close();
    }
    static public void solve() throws IOException{//
         
    }
}
class Read{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StreamTokenizer st = new StreamTokenizer(br);
    Scanner sc = new Scanner(br);
    public int nextInt() throws IOException {
        st.nextToken();
        return (int)st.nval;
    }
    public long nextLong() throws IOException {
        st.nextToken();
        return (long)st.nval;
    }
    public double nextDouble()throws Exception{
		st.nextToken();
		return (double)st.nval;
	}
    public String nextLine() throws IOException{
        return br.readLine();
    }
    public String next() throws IOException {
        return sc.next();
    }
}