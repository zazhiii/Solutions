
import java.io.*;
import java.util.*;
public class Main {
// public class Main {
    
    static public void solve() throws IOException{

    }
    static Scanner sc = new Scanner(System.in);
    static Read rd = new Read();
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    static int inf = (int)2e9;
    static long INF = (long)2e18;
    public static void main(String[] args) throws IOException {
        int T = 1;
        // T = rd.nextInt();
        // T = sc.nextInt();
        while(T --> 0) solve();
        pw.flush();
        pw.close();
    }

    public static int lower_bound(int a[], int l, int r, int t){
        int ans = r + 1;
        while(l <= r){
            int m = (l + r) >>> 1;
            if(a[m] >= t){ans = m; r = m - 1;}
            else l = m + 1;
        }
        return ans;
    }
    public static int upper_bound(int a[], int l, int r, int t){
        int ans = r + 1;
        while(l <= r){
            int m = (l + r) >>> 1;
            if(a[m] > t){ans = m; r = m - 1;}
            else l = m + 1;
        }
        return ans;
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