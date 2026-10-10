public class TextServiceImpl implements TextService {

	public String capitalizeEachWord(String text) {
		StringBuilder result = new StringBuilder();
		boolean startOfWord = true;

		for (char c : text.toCharArray()) {
			if (Character.isWhitespace(c)) {
				startOfWord = true;
				result.append(c);
			} else if (startOfWord) {
				result.append(Character.toUpperCase(c));
				startOfWord = false;
			} else {
				result.append(c);
			}
		}
		return result.toString();
	}
}