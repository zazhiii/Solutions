import java.io.*;
import java.util.*;
public class Main {
// public class Main {

    static public void solve() throws IOException{
        

    }
    public static void main(String[] args) throws IOException {
        int T = 1;
        // T = rd.nextInt();
        while(T --> 0) solve();
        pw.flush();pw.close();
    }

    static Read rd = new Read();
    static PrintWriter pw = new PrintWriter(System.out);
    static int inf = (int)2e9;
    static long INF = (long)2e18;

    /* 第一个大于等于t的值的位置 */
    public static int lower_bound(int a[], int l, int r, int t){
        int ans = r + 1;
        while(l <= r){
            int m = (l + r) >>> 1;
            if(a[m] >= t){ans = m; r = m - 1;}
            else l = m + 1;
        }
        return ans;
    }
    /* 第一个大于t的值的位置 */
    public static int upper_bound(int a[], int l, int r, int t){
        int ans = r + 1;
        while(l <= r){
            int m = (l + r) >>> 1;
            if(a[m] > t){ans = m; r = m - 1;}
            else l = m + 1;
        }
        return ans;
    }
    /* 下一个排列 */
    public static boolean nextPermutation(int a[]){
        int n = a.length, i = n - 2;
        while(i >= 0 && a[i] > a[i + 1]) i --;
        if(i < 0) return false;
        int k = i + 1;
        while(k < n && a[k] > a[i]) k ++;
        {int t = a[i]; a[i] = a[k - 1]; a[k - 1] = t;}// swap(a[i], a[k - 1])
        Arrays.sort(a, i + 1, n);
        return true;
    }
    
}

class Read{
    StringTokenizer st;
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    String next(){
        while (st == null || !st.hasMoreElements()){
            try {st = new StringTokenizer(br.readLine());}
            catch (IOException e){e.printStackTrace();}
        }
        return st.nextToken();
    }
    int nextInt() {return Integer.parseInt(next());}
    long nextLong() {return Long.parseLong(next());}
    double nextDouble() {return Double.parseDouble(next());}
    String nextLine() {
        String str = "";
        try {str = br.readLine();} 
        catch (IOException e) {e.printStackTrace();}
        return str;
    }

}