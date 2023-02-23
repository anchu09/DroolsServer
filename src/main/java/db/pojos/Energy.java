package db.pojos;

public enum Energy {
    LESS_ENERGIZED("LESS_ENERGIZED"),
    NO_CHANGE("NO_CHANGE"),
    SLIGHTLY_ENERGIZED("SLIGHTLY_ENERGIZED"),
    MORE_ENERGIZED("MORE_ENERGIZED");

    private String energy;

    Energy(String energy) {
        this.energy = energy;
    }

    public String getEnergy() {
        return this.energy;
    }
}
