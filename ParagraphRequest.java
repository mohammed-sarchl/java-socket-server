import java.io.Serializable;
import java.util.Date;

public class ParagraphRequest implements Serializable {
	private String paragraph;
	private Date timestamp;

	public ParagraphRequest(String paragraph, Date timestamp) {
		this.paragraph = paragraph;
		this.timestamp = timestamp;
	}

	public String getParagraph() {
		return paragraph;
	}

	public Date getTimestamp() {
		return timestamp;
	}
}