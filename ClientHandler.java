import java.io.*;
import java.net.*;
import java.util.Date;

public class ClientHandler extends Thread {
	private Socket socket;
	private TextService textService;
	private LamportClock clock;

	public ClientHandler(Socket connection, TextService textService, LamportClock clock) {
		this.socket = connection;
		this.textService = textService;
		this.clock = clock;
	}

	public void run() {
		try {
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			Object request = ois.readObject();

			if (request instanceof ParagraphRequest) {
				ParagraphRequest pReq = (ParagraphRequest) request;

				// Receive event
				int receiveTime = clock.receive(pReq.getLamportTime());
				System.out.println("Received at " + pReq.getTimestamp()
						+ " | message clock = " + pReq.getLamportTime()
						+ " | server clock = " + receiveTime);

				// Internal event: capitalize the paragraph
				String modified = textService.capitalizeEachWord(pReq.getParagraph());
				int processTime = clock.tick();
				System.out.println("Capitalized paragraph | server clock = " + processTime);

				// Send event
				int sendTime = clock.tick();
				ParagraphResponse response = new ParagraphResponse(modified, new Date(), sendTime);
				System.out.println("Sending reply | server clock = " + sendTime);

				ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
				oos.writeObject(response);
				oos.flush();
			}
			socket.close();
		} catch (Exception ex) {
			System.err.println(ex);
		}
	}
}
