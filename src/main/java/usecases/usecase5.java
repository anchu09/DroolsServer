package usecases;

import java.io.IOException;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import db.pojos.*;

public class usecase5 {

	public static void main(String args[]) throws IOException {

		// in this usecase5 5 rules are going to be tested. Go to package rul5 and open
		// rule5.drl file. Kiesession: usecase5
		// In total, the questionnaire has 44 attributes.
		// To facilitate the test and the location of the attributes, the attributes
		// that are filled in the constructor DO NOT SERVE.
		// The tested attributes will be filled with prefixed values using setters. The
		// tested rules are from nº21 to nº25.

		// The attributes that are going to be tested are:

		// AnxietyLevel anxiety_experience
		// Emotion emotional_state
		// int breathing_difficulty
		// int panic_reduction
		// int appetite_weight
		// int muscle_tension_change
		// int nervousness_tension
		// int relaxation
		// int motivation
		// int enjoyment_change

		// the values are:

		// AnxietyLevel anxiety_experience= AnxietyLevel.MODERATE_ANXIETY
		// Emotion emotional_state=Emotion.HAPPY
		// int breathing_difficulty=2
		// int panic_reduction=5
		// int appetite_weight=2
		// int muscle_tension_change=3
		// int nervousness_tension=4
		// int relaxation=5
		// int motivation=5
		// int enjoyment_change=5

		// With these values the rules that SHOULD be triggered are:

		// Rule21 ==> Congratulations!!! you have improve your anxiety
		// Rule24 ==> Congratulations!!! Keep listening to music
		// Rule25 ==> Listen to some music when you are feeling unmotivated

		Questionary q = new Questionary(Emotion.SAD, 4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, true, 2, 2, 2, false, true,
				2, null, null, 2, 2, 2, 2, true, null, 2, 2, 2, null, 2, true, 2, 2, 2, null, 2, 2, true, null);
		System.out.println(q.toString());

		q.setEmotional_state(Emotion.HAPPY);
		q.setAnxiety_experience(AnxietyLevel.MODERATE_ANXIETY);
		q.setBreathing_difficulty(2);
		q.setPanic_reduction(5);
		q.setAppetite_weight(2);
		q.setMuscle_tension_change(3);
		q.setNervousness_tension(4);
		q.setRelaxation(5);
		q.setMotivation(5);
		q.setEnjoyment_change(5);

		try {
			// load up the knowledge base
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("usecase5");

			kSession.insert(q);
			kSession.fireAllRules();

			kSession.dispose();
			System.out.println(q.toString());
		} catch (Throwable t) {
			t.printStackTrace();
		}

	}

}