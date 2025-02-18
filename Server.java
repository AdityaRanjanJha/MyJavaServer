import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Server {
    public static void main(String[] args) {
        int port = 5000;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server is listening on port " + port);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("New client connected");

                // Get the input from the client (HTTP request)
                BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                String line;
                while ((line = reader.readLine()) != null && !line.isEmpty()) {
                    System.out.println(line);  // Print the HTTP request to the console
                }

                // Send HTTP response
                OutputStream output = socket.getOutputStream();
                PrintWriter writer = new PrintWriter(output, true);
                writer.println("HTTP/1.1 200 OK");
                writer.println("Content-Type: text/html");
                writer.println();
                writer.println("<html>");
                writer.println("<head><title>Hello from Java Server</title></head>");
                writer.println("<body><h1>Welcome to the Java HTTP Server!</h1>");
                writer.println("<p>This page is served by a Java server running inside a Docker container.</p>");
                writer.println("</body>");
                writer.println("</html>");

                socket.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}