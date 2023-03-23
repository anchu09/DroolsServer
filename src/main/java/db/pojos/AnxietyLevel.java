package db.pojos;

public enum AnxietyLevel {
	NO_ANXIETY("NO_ANXIETY"), MILD_ANXIETY("MILD_ANXIETY"), MODERATE_ANXIETY("MODERATE_ANXIETY"),
	SEVERE_ANXIETY("SEVERE_ANXIETY");

	private String level;

	AnxietyLevel(String level) {
		this.level = level;
	}

	public String getLevel() {
		return this.level;
	}
}
