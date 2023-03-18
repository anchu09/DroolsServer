package usecases;

import java.io.IOException;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;


import db.pojos.*;

public class usecase3 {

public static void main(String args[]) throws IOException {
    	
	//in this usecase3 5 rules are going to be tested. Go to package rul3 and open rule3.drl file. Kiesession: usecase3
	//In total, the questionnaire has 44 attributes. 
	//To facilitate the test and the location of the attributes, the attributes that are filled in the constructor DO NOT SERVE.
	//The tested attributes will  be filled with prefixed values using setters.  The tested rules are from nº11 to nº15. 
	
	//The attributes that are going to be tested are:
	
	//int relaxation 
	//int muscle_tension_change 
	//Emotion emotional_state 
	//int chest_pressure 
	//int breathing_difficulty 
	//int shaky 

		
	//the values are:

	//int relaxation: 5
	//int muscle_tension_change: 1
	//Emotion emotional_state: Emotion.SAD
	//int chest_pressure: 4
	//int breathing_difficulty: 4
	//int shaky: 5
	
	//With these values the rules that SHOULD be triggered are:
	
	//Rule12 ==> Listen to relaxing music to improve your general emotional state
	//Rule13 ==> Try listening to music to reduce the feeling of chest pressure and muscle tension
	//Rule14 ==> Listen to music to help improve your breathing
	//Rule15 ==> Listen to music to help reduce tremors
	
	
    	Questionary q = new Questionary(Emotion.SAD, 4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, true, 2, 2, 2, 
    			false, true, 2, null, null, 2, 2, 2, 2, true, null, 2, 2, 2, null, 2, true, 2, 2, 2, null, 2, 2, true, null);
    	System.out.println(q.toString());
    	
    	q.setRelaxation(5);
    	q.setMuscle_tension_change(1);
    	q.setEmotional_state(Emotion.SAD);
    	q.setChest_pressure(4);
    	q.setBreathing_difficulty(4);
    	q.setShaky(5);
    	
    	try {
    	    // load up the knowledge base
    	    KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
    		KieSession kSession = kContainer.newKieSession("usecase3");
    		

    	    kSession.insert(q);
    	    kSession.fireAllRules();
    	
    	    
    	    
    	    kSession.dispose();
    	    System.out.println(q.toString());
    	} catch (Throwable t) {
    	    t.printStackTrace();
    	}
    	
    
    }

}