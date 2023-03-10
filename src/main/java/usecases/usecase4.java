package usecases;

import java.io.IOException;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;


import db.pojos.*;

public class usecase4 {

public static void main(String args[]) throws IOException {
    	
	//in this usecase3 5 rules are going to be tested. Go to package rul4 and open rule4.drl file. Kiesession: usecase4
	//In total, the questionnaire has 44 attributes. 
	//To facilitate the test and the location of the attributes, the attributes that are filled in the constructor DO NOT SERVE.
	//The tested attributes will then be filled with setters. The tested rules are from nº16 to nº20. 
	
	//The attributes that are going to be tested are:
	
	//int nervousness_tension  
	//int relaxation  
	//int panic_reduction  
	//int panic_fear  
	//int concentration_improvement  
	//int concentration  
	//int stressful_events 
	//int stress_coping_change 
	//int anxiety_level 

		
	//the values are:
	//int nervousness_tension=5
	//int relaxation=3
	//int panic_reduction=2  
	//int panic_fear=5
	//int concentration_improvement=4
	//int concentration=1  
	//int stressful_events=2
	//int stress_coping_change=1
	//int anxiety_level=1

	
	//With these values the rules that SHOULD be triggered are:
	
	//Rule18 ==> Listen to music to help reduce feelings of panic or irrational fear

	
	
    	Questionary q = new Questionary(Emotion.SAD, 4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, true, 2, 2, 2, 
    			false, true, 2, null, null, 2, 2, 2, 2, true, null, 2, 2, 2, null, 2, true, 2, 2, 2, null, 2, 2, true, null);
    	System.out.println(q.toString());
    	
    	q.setNervousness_tension(5);
    	q.setRelaxation(3);
    	q.setPanic_reduction(2);
    	q.setPanic_fear(5);
    	q.setConcentration_improvement(4);
    	q.setConcentration(1);
    	q.setStressful_events(2);
    	q.setStress_coping_change(1);
    	q.setAnxiety_level(1);
    	
    	try {
    	    // load up the knowledge base
    	    KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
    		KieSession kSession = kContainer.newKieSession("usecase4");
    		

    	    kSession.insert(q);
    	    kSession.fireAllRules();
    	
    	    
    	    
    	    kSession.dispose();
    	    System.out.println(q.toString());
    	} catch (Throwable t) {
    	    t.printStackTrace();
    	}
    	
    
    }

}