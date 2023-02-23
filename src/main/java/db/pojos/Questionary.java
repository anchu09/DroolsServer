package db.pojos;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import db.pojos.Energy;
import db.pojos.Experience;
import db.pojos.AnxietyLevel;
import db.pojos.Emotion;



public class Questionary implements Serializable{
	private Emotion emotion; //pregunta 1
	private int muscle_tension; //pregunta 2
	private int chest_pressure; //pregunta 3
	private int breathing_difficulty; //pregunta 4
	private int shaky; //pregunta 5
	private int appetite_weight; //pregunta 6
	private int sleep_pattern; //pregunta 7
	private int nervousness_tension; //pregunta 8
	private int concentration; //pregunta 9
	private int motivation; //pregunta 10
	private int panic_fear; //pregunta 11
	private int mood; //pregunta 12
	private int stressful_events; //pregunta 13
	private int recurring_thoughts; //pregunta 14
	private boolean trauma; //pregunta 15
	private int social_interaction; //pregunta 16
	private int relationships; //pregunta 17
	private int anxiety_level; //pregunta 18
	private boolean treatment; //pregunta 19
	private boolean control; //pregunta 20
	private int duration; //pregunta 21
	private AnxietyLevel anxiety_experience; //pregunta 22
	//despues
	private Emotion emotion_2; //pregunta a
	private int sleep_improvement; //pregunta b
	private int muscle_tension_change; //pregunta c
	private int anxiety_level_2; //pregunta d
	private int relaxation; //pregunta e
	private boolean listen_more_songs; //pregunta f
	private Experience song_experience ; //pregunta g
	private int relaxation_ability_change; //pregunta h
	private int concentration_improvement; //pregunta i
	private int panic_reduction; //pregunta j
	private Energy energy_level ; //pregunta k
	private int well_being_change; //pregunta l
	private boolean try_different_genres ; //pregunta m
	private int song_satisfaction; //pregunta n
	private int stress_coping_change; //pregunta o
	private int confidence_change; //pregunta p
	private Emotion emotional_state; //pregunta q
	private int enjoyment_change; //pregunta r
	private int anxiety_perception_change; //pregunta s
	private boolean recommendations; //pregunta t
	
	
	
	
	
	
	public Questionary(Emotion emotion, int muscle_tension, int chest_pressure, int breathing_difficulty, int shaky,
			int appetite_weight, int sleep_pattern, int nervousness_tension, int concentration, int motivation,
			int panic_fear, int mood, int stressful_events, int recurring_thoughts, boolean trauma,
			int social_interaction, int relationships, int anxiety_level, boolean treatment, boolean control,
			int duration, AnxietyLevel anxiety_experience, Emotion emotion_2, int sleep_improvement,
			int muscle_tension_change, int anxiety_level_2, int relaxation, boolean listen_more_songs,
			Experience song_experience, int relaxation_ability_change, int concentration_improvement,
			int panic_reduction, Energy energy_level, int well_being_change, boolean try_different_genres,
			int song_satisfaction, int stress_coping_change, int confidence_change, Emotion emotional_state,
			int enjoyment_change, int anxiety_perception_change, boolean recommendations) {
		super();
		this.emotion = emotion;
		this.muscle_tension = muscle_tension;
		this.chest_pressure = chest_pressure;
		this.breathing_difficulty = breathing_difficulty;
		this.shaky = shaky;
		this.appetite_weight = appetite_weight;
		this.sleep_pattern = sleep_pattern;
		this.nervousness_tension = nervousness_tension;
		this.concentration = concentration;
		this.motivation = motivation;
		this.panic_fear = panic_fear;
		this.mood = mood;
		this.stressful_events = stressful_events;
		this.recurring_thoughts = recurring_thoughts;
		this.trauma = trauma;
		this.social_interaction = social_interaction;
		this.relationships = relationships;
		this.anxiety_level = anxiety_level;
		this.treatment = treatment;
		this.control = control;
		this.duration = duration;
		this.anxiety_experience = anxiety_experience;
		
		//segunda parte
		this.emotion_2 = emotion_2;
		this.sleep_improvement = sleep_improvement;
		this.muscle_tension_change = muscle_tension_change;
		this.anxiety_level_2 = anxiety_level_2;
		this.relaxation = relaxation;
		this.listen_more_songs = listen_more_songs;
		this.song_experience = song_experience;
		this.relaxation_ability_change = relaxation_ability_change;
		this.concentration_improvement = concentration_improvement;
		this.panic_reduction = panic_reduction;
		this.energy_level = energy_level;
		this.well_being_change = well_being_change;
		this.try_different_genres = try_different_genres;
		this.song_satisfaction = song_satisfaction;
		this.stress_coping_change = stress_coping_change;
		this.confidence_change = confidence_change;
		this.emotional_state = emotional_state;
		this.enjoyment_change = enjoyment_change;
		this.anxiety_perception_change = anxiety_perception_change;
		this.recommendations = recommendations;
	}
	
	@Override
	public String toString() {
		return "Questionary [emotion=" + emotion + ", muscle_tension=" + muscle_tension + ", chest_pressure="
				+ chest_pressure + ", breathing_difficulty=" + breathing_difficulty + ", shaky=" + shaky
				+ ", appetite_weight=" + appetite_weight + ", sleep_pattern=" + sleep_pattern + ", nervousness_tension="
				+ nervousness_tension + ", concentration=" + concentration + ", motivation=" + motivation
				+ ", panic_fear=" + panic_fear + ", mood=" + mood + ", stressful_events=" + stressful_events
				+ ", recurring_thoughts=" + recurring_thoughts + ", trauma=" + trauma + ", social_interaction="
				+ social_interaction + ", relationships=" + relationships + ", anxiety_level=" + anxiety_level
				+ ", treatment=" + treatment + ", control=" + control + ", duration=" + duration
				+ ", anxiety_experience=" + anxiety_experience + ", emotion_2=" + emotion_2 + ", sleep_improvement="
				+ sleep_improvement + ", muscle_tension_change=" + muscle_tension_change + ", anxiety_level_2="
				+ anxiety_level_2 + ", relaxation=" + relaxation + ", listen_more_songs=" + listen_more_songs
				+ ", song_experience=" + song_experience + ", relaxation_ability_change=" + relaxation_ability_change
				+ ", concentration_improvement=" + concentration_improvement + ", panic_reduction=" + panic_reduction
				+ ", energy_level=" + energy_level + ", well_being_change=" + well_being_change
				+ ", try_different_genres=" + try_different_genres + ", song_satisfaction=" + song_satisfaction
				+ ", stress_coping_change=" + stress_coping_change + ", confidence_change=" + confidence_change
				+ ", emotional_state=" + emotional_state + ", enjoyment_change=" + enjoyment_change
				+ ", anxiety_perception_change=" + anxiety_perception_change + ", recommendations=" + recommendations
				+ "]";
	}

	
	
	public Emotion getEmotion() {
		return emotion;
	}

	public void setEmotion(Emotion emotion) {
		this.emotion = emotion;
	}

	public int getMuscle_tension() {
		return muscle_tension;
	}

	public void setMuscle_tension(int muscle_tension) {
		this.muscle_tension = muscle_tension;
	}

	public int getChest_pressure() {
		return chest_pressure;
	}

	public void setChest_pressure(int chest_pressure) {
		this.chest_pressure = chest_pressure;
	}

	public int getBreathing_difficulty() {
		return breathing_difficulty;
	}

	public void setBreathing_difficulty(int breathing_difficulty) {
		this.breathing_difficulty = breathing_difficulty;
	}

	public int getShaky() {
		return shaky;
	}

	public void setShaky(int shaky) {
		this.shaky = shaky;
	}

	public int getAppetite_weight() {
		return appetite_weight;
	}

	public void setAppetite_weight(int appetite_weight) {
		this.appetite_weight = appetite_weight;
	}

	public int getSleep_pattern() {
		return sleep_pattern;
	}

	public void setSleep_pattern(int sleep_pattern) {
		this.sleep_pattern = sleep_pattern;
	}

	public int getNervousness_tension() {
		return nervousness_tension;
	}

	public void setNervousness_tension(int nervousness_tension) {
		this.nervousness_tension = nervousness_tension;
	}

	public int getConcentration() {
		return concentration;
	}

	public void setConcentration(int concentration) {
		this.concentration = concentration;
	}

	public int getMotivation() {
		return motivation;
	}

	public void setMotivation(int motivation) {
		this.motivation = motivation;
	}

	public int getPanic_fear() {
		return panic_fear;
	}

	public void setPanic_fear(int panic_fear) {
		this.panic_fear = panic_fear;
	}

	public int getMood() {
		return mood;
	}

	public void setMood(int mood) {
		this.mood = mood;
	}

	public int getStressful_events() {
		return stressful_events;
	}

	public void setStressful_events(int stressful_events) {
		this.stressful_events = stressful_events;
	}

	public int getRecurring_thoughts() {
		return recurring_thoughts;
	}

	public void setRecurring_thoughts(int recurring_thoughts) {
		this.recurring_thoughts = recurring_thoughts;
	}

	public boolean isTrauma() {
		return trauma;
	}

	public void setTrauma(boolean trauma) {
		this.trauma = trauma;
	}

	public int getSocial_interaction() {
		return social_interaction;
	}

	public void setSocial_interaction(int social_interaction) {
		this.social_interaction = social_interaction;
	}

	public int getRelationships() {
		return relationships;
	}

	public void setRelationships(int relationships) {
		this.relationships = relationships;
	}

	public int getAnxiety_level() {
		return anxiety_level;
	}

	public void setAnxiety_level(int anxiety_level) {
		this.anxiety_level = anxiety_level;
	}

	public boolean isTreatment() {
		return treatment;
	}

	public void setTreatment(boolean treatment) {
		this.treatment = treatment;
	}

	public boolean isControl() {
		return control;
	}

	public void setControl(boolean control) {
		this.control = control;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public AnxietyLevel getAnxiety_experience() {
		return anxiety_experience;
	}

	public void setAnxiety_experience(AnxietyLevel anxiety_experience) {
		this.anxiety_experience = anxiety_experience;
	}

	public Emotion getEmotion_2() {
		return emotion_2;
	}
	public void setEmotion_2(Emotion emotion_2) {
		this.emotion_2 = emotion_2;
	}
	public int getSleep_improvement() {
		return sleep_improvement;
	}
	public void setSleep_improvement(int sleep_improvement) {
		this.sleep_improvement = sleep_improvement;
	}
	public int getMuscle_tension_change() {
		return muscle_tension_change;
	}
	public void setMuscle_tension_change(int muscle_tension_change) {
		this.muscle_tension_change = muscle_tension_change;
	}
	public int getAnxiety_level_2() {
		return anxiety_level_2;
	}
	public void setAnxiety_level_2(int anxiety_level_2) {
		this.anxiety_level_2 = anxiety_level_2;
	}
	public int getRelaxation() {
		return relaxation;
	}
	public void setRelaxation(int relaxation) {
		this.relaxation = relaxation;
	}
	public boolean isListen_more_songs() {
		return listen_more_songs;
	}
	public void setListen_more_songs(boolean listen_more_songs) {
		this.listen_more_songs = listen_more_songs;
	}
	public Experience getSong_experience() {
		return song_experience;
	}
	public void setSong_experience(Experience song_experience) {
		this.song_experience = song_experience;
	}
	public int getRelaxation_ability_change() {
		return relaxation_ability_change;
	}
	public void setRelaxation_ability_change(int relaxation_ability_change) {
		this.relaxation_ability_change = relaxation_ability_change;
	}
	public int getConcentration_improvement() {
		return concentration_improvement;
	}
	public void setConcentration_improvement(int concentration_improvement) {
		this.concentration_improvement = concentration_improvement;
	}
	public int getPanic_reduction() {
		return panic_reduction;
	}
	public void setPanic_reduction(int panic_reduction) {
		this.panic_reduction = panic_reduction;
	}
	public Energy getEnergy_level() {
		return energy_level;
	}
	public void setEnergy_level(Energy energy_level) {
		this.energy_level = energy_level;
	}
	public int getWell_being_change() {
		return well_being_change;
	}
	public void setWell_being_change(int well_being_change) {
		this.well_being_change = well_being_change;
	}
	public boolean isTry_different_genres() {
		return try_different_genres;
	}
	public void setTry_different_genres(boolean try_different_genres) {
		this.try_different_genres = try_different_genres;
	}
	public int getSong_satisfaction() {
		return song_satisfaction;
	}
	public void setSong_satisfaction(int song_satisfaction) {
		this.song_satisfaction = song_satisfaction;
	}
	public int getStress_coping_change() {
		return stress_coping_change;
	}
	public void setStress_coping_change(int stress_coping_change) {
		this.stress_coping_change = stress_coping_change;
	}
	public int getConfidence_change() {
		return confidence_change;
	}
	public void setConfidence_change(int confidence_change) {
		this.confidence_change = confidence_change;
	}
	public Emotion getEmotional_state() {
		return emotional_state;
	}
	public void setEmotional_state(Emotion emotional_state) {
		this.emotional_state = emotional_state;
	}
	public int getEnjoyment_change() {
		return enjoyment_change;
	}
	public void setEnjoyment_change(int enjoyment_change) {
		this.enjoyment_change = enjoyment_change;
	}
	public int getAnxiety_perception_change() {
		return anxiety_perception_change;
	}
	public void setAnxiety_perception_change(int anxiety_perception_change) {
		this.anxiety_perception_change = anxiety_perception_change;
	}
	public boolean isRecommendations() {
		return recommendations;
	}
	public void setRecommendations(boolean recommendations) {
		this.recommendations = recommendations;
	}
	
	
	
	
	
	
}
