import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class HttpClient {

    public static void main(String[] args) throws Exception {
        String host = "www.nbrb.by";
        String path = "/api/exrates/currencies/145";
        InetAddress addr = InetAddress.getByName(host);
        int port = 80;
        Socket socket = new Socket(addr, port);
        boolean autoflush = true;
        PrintWriter out = new PrintWriter(socket.getOutputStream(), autoflush);
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        out.println("GET " + path + " HTTP/1.1");
        out.println("Host: "+host+":"+port);
        out.println("Connection: Close");
        out.println();

        // read the response
        boolean loop = true;
        StringBuilder sb = new StringBuilder(8096);
        while (loop) {
            if (in.ready()) {
                int i = 0;
                while (i != -1) {
                    i = in.read();
                    sb.append((char) i);
                }
                loop = false;
            }
        }
        System.out.println(sb.toString());
        socket.close();
    }
}

