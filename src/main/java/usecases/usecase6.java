package usecases;

import java.io.IOException;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;


import db.pojos.*;

public class usecase6 {

public static void main(String args[]) throws IOException {
    	
	//in this usecase6 5 rules are going to be tested. Go to package rul6 and open rule6.drl file. Kiesession: usecase6
	//In total, the questionnaire has 44 attributes. 
	//To facilitate the test and the location of the attributes, the attributes that are filled in the constructor DO NOT SERVE.
	//The tested attributes will then be filled with setters. The tested rules are from nº26 to nº30. 
	
	//The attributes that are going to be tested are:
	
 
	//int stressful_events    
	//int stress_coping_change 
	//boolean trauma 
	//int chest_pressure    
	//int shaky    
	//int muscle_tension_change   

		
	//the values are:
	 
	//int stressful_events=5
	//int stress_coping_change=5 
	//boolean trauma=false
	//int chest_pressure=1  
	//int shaky=2    
	//int muscle_tension_change=1   

	
	//With these values the rules that SHOULD be triggered are:
	
	//Rule26 ==> Listen to relaxing music to help manage stressful and worrying situations in the future
	//Rule30 ==> We recomend you to go to a therapist to talk about the stresfull situations

	
	
    	Questionary q = new Questionary(Emotion.SAD, 4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, true, 2, 2, 2, 
    			false, true, 2, null, null, 2, 2, 2, 2, true, null, 2, 2, 2, null, 2, true, 2, 2, 2, null, 2, 2, true, null);
    	System.out.println(q.toString());
    	
    	q.setStressful_events(5);
    	q.setStress_coping_change(5);
    	q.setTrauma(false);
    	q.setChest_pressure(1);
    	q.setShaky(2);
    	q.setMuscle_tension_change(1);
    	
    	try {
    	    // load up the knowledge base
    	    KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
    		KieSession kSession = kContainer.newKieSession("usecase6");
    		

    	    kSession.insert(q);
    	    kSession.fireAllRules();
    	
    	    
    	    
    	    kSession.dispose();
    	    System.out.println(q.toString());
    	} catch (Throwable t) {
    	    t.printStackTrace();
    	}
    	
    
    }

}