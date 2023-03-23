package usecases;

import java.io.IOException;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import db.pojos.*;

public class usecase1 {

	public static void main(String args[]) throws IOException {

		// in this usecase1 5 rules are going to be tested. Go to package rul1 and open
		// rulep.drl file. Kiesession: usecase1
		// In total, the questionnaire has 44 attributes.
		// To facilitate the test and the location of the attributes, the attributes
		// that are filled in the constructor DO NOT SERVE.
		// The tested attributes will be filled with prefixed values using setters. The
		// tested rules are from nº1 to nº5.
		// The attributes that are going to be tested are:

		// Emotion emotion_2
		// int muscle_tension_change
		// int anxiety_level_2
		// int relaxation
		// int sleep_improvement
		// boolean recommendations
		// int panic_reduction

		// Rule2 and rule5 are contradictory due to the attribute relaxation. For
		// example: if relaxation>3, rule2 COULD be triggered,
		// but then rule5 will never be fired. If relaxation<3, rule2 will never be
		// fired but rule 5 COULD be triggered if also panic_reduction<3.

		// the values are:
		// Emotion emotion_2: Emotion.HAPPY
		// int muscle_tension_change:5
		// int anxiety_level_2: 1
		// int relaxation: 2
		// int sleep_improvement: 4
		// boolean recommendations: true
		// int panic_reduction: 2

		// With these values the rules that SHOULD be triggered are:

		// Rule1 because emotion.HAPPY ==> We recommend you listening to relaxing music
		// to reduce the muscle tension and physical discomfort
		// Rule3 due to anxiety_level_2<3 and sleep_improvement>3 ==> Try progressive
		// muscle relaxation techniques to reduce physical discomfort.
		// Rule4 due to recommendations=true ==> We recommend you to listening to songs
		// of the same artist or similar genres

		Questionary q = new Questionary(Emotion.SAD, 4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, true, 2, 2, 2, false, true,
				2, null, null, 2, 2, 2, 2, true, null, 2, 2, 2, null, 2, true, 2, 2, 2, null, 2, 2, true, null);
		System.out.println(q.toString());

		q.setEmotion_2(Emotion.HAPPY);
		q.setMuscle_tension_change(5);
		q.setAnxiety_level_2(1);
		q.setRelaxation(2);
		q.setSleep_improvement(4);
		q.setRecommendations(true);
		q.setPanic_reduction(2);
		try {
			// load up the knowledge base
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("usecase1");

			kSession.insert(q);
			kSession.fireAllRules();

			kSession.dispose();
			System.out.println(q.toString());
		} catch (Throwable t) {
			t.printStackTrace();
		}

	}

}
