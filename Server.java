import java.io.*;
import java.net.*;

public class Server {
	public static void main(String... args) {
		try {
			ServerSocket server = new ServerSocket(8080);
			TextService textService = new TextServiceImpl();
			while (true) {
				System.out.println("Waiting for a client");
				Socket client = server.accept();
				ClientHandler handler = new ClientHandler(client, textService);
				handler.start();
			}
		} catch (IOException ex) {
			System.err.println(ex);
		}
	}
}