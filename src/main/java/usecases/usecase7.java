package usecases;

import java.io.IOException;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import db.pojos.*;

public class usecase7 {

	public static void main(String args[]) throws IOException {

		// in this usecase7 5 rules are going to be tested. Go to package rul7 and open
		// rule7.drl file. Kiesession: usecase7
		// In total, the questionnaire has 44 attributes.
		// To facilitate the test and the location of the attributes, the attributes
		// that are filled in the constructor DO NOT SERVE.
		// The tested attributes will be filled with prefixed values using setters. The
		// tested rules are from nº31 to nº35.

		// The attributes that are going to be tested are:

		// int concentration
		// int concentration_improvement
		// int durat
		// int panic_fear
		// int panic_reduction
		// int mood
		// int recurring_thoughts
		// boolean treatment
		// boolean control

		// the values are:

		// int concentration=3
		// int concentration_improvement=1
		// int durat=2
		// int panic_fear=5
		// int panic_reduction=3
		// int mood=1
		// int recurring_thoughts=4
		// boolean treatment=false
		// boolean control=true

		// With these values the rules that SHOULD be triggered are:

		// Rule32 ==> Try other techniques such as breathing excercises, yoga or
		// meditation
		// Rule34 ==> Try practicing mindfulness techniques for focusing on the present
		// moment

		Questionary q = new Questionary(Emotion.SAD, 4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, true, 2, 2, 2, false, true,
				2, null, null, 2, 2, 2, 2, true, null, 2, 2, 2, null, 2, true, 2, 2, 2, null, 2, 2, true, null);
		System.out.println(q.toString());

		q.setConcentration(3);
		q.setConcentration_improvement(1);
		q.setdurat(2);
		q.setPanic_fear(5);
		q.setPanic_reduction(3);
		q.setMood(1);
		q.setRecurring_thoughts(4);
		q.setTreatment(false);
		q.setControl(true);

		try {
			// load up the knowledge base
			KieServices ks = KieServices.Factory.get();
			KieContainer kContainer = ks.getKieClasspathContainer();
			KieSession kSession = kContainer.newKieSession("usecase7");

			kSession.insert(q);
			kSession.fireAllRules();

			kSession.dispose();
			System.out.println(q.toString());
		} catch (Throwable t) {
			t.printStackTrace();
		}

	}

}