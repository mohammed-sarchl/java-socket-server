import java.io.Serializable;
import java.util.Date;

public class ParagraphRequest implements Serializable {
	private String paragraph;
	private Date timestamp;
	private int lamportTime;

	public ParagraphRequest(String paragraph, Date timestamp, int lamportTime) {
		this.paragraph = paragraph;
		this.timestamp = timestamp;
		this.lamportTime = lamportTime;
	}

	public String getParagraph() {
		return paragraph;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public int getLamportTime() {
		return lamportTime;
	}
}
