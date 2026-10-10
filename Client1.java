import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.Scanner;

public class Client1 {
	public static void main(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Type a paragraph:");
		String paragraph = scanner.nextLine();

		Socket toServer = new Socket("127.0.0.1", 8080);
		ObjectOutputStream oos = new ObjectOutputStream(toServer.getOutputStream());
		oos.writeObject(new ParagraphRequest(paragraph, new Date()));
		oos.flush();

		ObjectInputStream ois = new ObjectInputStream(toServer.getInputStream());
		ParagraphResponse response = (ParagraphResponse) ois.readObject();

		System.out.println("Server replied at " + response.getTimestamp() + ":");
		System.out.println(response.getParagraph());

		toServer.close();
	}
}