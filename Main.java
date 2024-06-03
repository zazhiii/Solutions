
import java.io.*;
import java.util.*;
public class Main {
    static Scanner sc = new Scanner(System.in);
    static Read rd = new Read();
    static PrintWriter pw = new PrintWriter(new BufferedWriter(new OutputStreamWriter(System.out)));
    public static void main(String[] args) throws IOException {
        
    }
}
class Read{
    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    StreamTokenizer st = new StreamTokenizer(br);
    public int nextInt() throws IOException {
        st.nextToken();
        return (int)st.nval;
    }
    public int nextLong() throws IOException {
        st.nextToken();
        return (int)st.nval;
    }
    public String nextLine() throws IOException{
        return br.readLine();
    }
}