package db.pojos;

public enum Emotion {
	HAPPY("HAPPY"), SAD("SAD"), ANXIOUS("ANXIOUS"), ANGRY("ANGRY");

	private String emotion;

	Emotion(String emotion) {
		this.emotion = emotion;
	}

	public String getEmotion() {
		return this.emotion;
	}
}