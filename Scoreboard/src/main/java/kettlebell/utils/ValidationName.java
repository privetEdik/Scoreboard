package kettlebell.utils;

public class ValidationName {
	private static final String Matches = "[А-ЯA-Z]{1}[a-zа-я]{0,10}";

	public static boolean isNotValidName(String name) {
		if (name == null || name.isBlank() || !name.replaceFirst(Matches, "").isBlank()) {
			return true;
		} else
			return false;

	}
}
