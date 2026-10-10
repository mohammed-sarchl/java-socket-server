import java.io.*;
import java.net.*;
import java.util.Date;

public class ClientHandler extends Thread {
	private Socket socket;
	private TextService textService;

	public ClientHandler(Socket connection, TextService textService) {
		this.socket = connection;
		this.textService = textService;
	}

	public void run() {
		try {
			ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
			Object request = ois.readObject();

			if (request instanceof ParagraphRequest) {
				ParagraphRequest pReq = (ParagraphRequest) request;
				System.out.println("Received at " + pReq.getTimestamp());

				String modified = textService.capitalizeEachWord(pReq.getParagraph());
				ParagraphResponse response = new ParagraphResponse(modified, new Date());

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