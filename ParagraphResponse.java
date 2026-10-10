import java.io.Serializable;
import java.util.Date;

public class ParagraphResponse implements Serializable {
	private String paragraph;
	private Date timestamp;

	public ParagraphResponse(String paragraph, Date timestamp) {
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