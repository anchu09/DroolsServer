package usecases;

import java.io.IOException;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import db.pojos.*;

public class usecase2 {

	public static void main(String args[]) throws IOException {

		// in this usecase2 5 rules are going to be tested. Go to package rul2 and open
		// rules2.drl file. Kiesession: usecase2
		// In total, the questionnaire has 44 attributes.
		// To facilitate the test and the location of the attributes, the attributes
		// that are filled in the constructor DO NOT SERVE.
		// The tested attributes will be filled with prefixed values using setters. The
		// tested rules are from nº5 to nº10.

		// The attributes that are going to be tested are:

		// boolean try_different_genres
		// int enjoyment_change
		// int well_being_change
		// int stress_coping_change
		// int confidence_change
		// boolean listen_more_songs
		// int relaxation
		// int concentration_improvement

		// the values are:
		// boolean try_different_genres= true
		// int enjoyment_change: 5
		// int well_being_change: 1
		// int stress_coping_change: 2
		// int confidence_change: 4
		// boolean listen_more_songs: true
		// int relaxation: 4
		// int concentration_improvement: 5

		// With these values the rules that SHOULD be triggered are:

		// Rule6 ==> Experiment with different genres of music to see if there is any
		// difference in your emotional response
		// Rule9 ==> Keep listening to relaxing music to help control your anxiety
		// Rule10 ==> Keep listening to relaxing music to improve your ability to
		// concentrate on tasks

		Questionary q = new Questionary(Emotion.SAD, 4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, true, 2, 2, 2, false, true,
				2, null, null, 2, 2, 2, 2, true, null, 2, 2, 2, null, 2, true, 2, 2, 2, null, 2, 2, true, null);
		System.out.println(q.toString());

		q.setTry_different_genres(true);
		q.setEnjoyment_change(5);
		q.setWell_being_change(1);
		q.setStress_coping_change(2);
		q.setConfidence_change(4);
		q.setListen_more_songs(true);
		q.setRelaxation(4);
		q.setConcentration_improvement(5);

		try {
			// load up the knowledge base
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("usecase2");

			kSession.insert(q);
			kSession.fireAllRules();

			kSession.dispose();
			System.out.println(q.toString());
		} catch (Throwable t) {
			t.printStackTrace();
		}

	}

}