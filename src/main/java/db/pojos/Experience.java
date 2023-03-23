package db.pojos;

public enum Experience {
	I_DO_NOT_CARE("I_DO_NOT_CARE"), GOOD("GOOD"), BORING("BORING"), HORRIBLE("HORRIBLE");

	private String experience;

	Experience(String experience) {
		this.experience = experience;
	}

	public String getExperience() {
		return this.experience;
	}
}
