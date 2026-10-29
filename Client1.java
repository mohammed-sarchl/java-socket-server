import java.io.*;
import java.net.*;
import java.util.Date;
import java.util.Scanner;

public class Client1 {
	public static void main(String... args) throws Exception {
		LamportClock clock = new LamportClock(); // one clock for the client process

		Scanner scanner = new Scanner(System.in);
		System.out.println("Type a paragraph:");
		String paragraph = scanner.nextLine();

		Socket toServer = new Socket("127.0.0.1", 8080);

		// Send event
		int sendTime = clock.tick();
		ObjectOutputStream oos = new ObjectOutputStream(toServer.getOutputStream());
		oos.writeObject(new ParagraphRequest(paragraph, new Date(), sendTime));
		oos.flush();
		System.out.println("Sent paragraph | client clock = " + sendTime);

		ObjectInputStream ois = new ObjectInputStream(toServer.getInputStream());
		ParagraphResponse response = (ParagraphResponse) ois.readObject();

		// Receive event
		int receiveTime = clock.receive(response.getLamportTime());
		System.out.println("Server replied at " + response.getTimestamp()
				+ " | message clock = " + response.getLamportTime()
				+ " | client clock = " + receiveTime + ":");
		System.out.println(response.getParagraph());

		toServer.close();
	}
}
