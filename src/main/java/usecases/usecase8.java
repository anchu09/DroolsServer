package usecases;

import java.io.IOException;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;


import db.pojos.*;

public class usecase8 {

public static void main(String args[]) throws IOException {
    	
	//in this usecase8, 8 rules are going to be tested. Go to package rul8 and open rule8.drl file. Kiesession: usecase8
	//In total, the questionnaire has 44 attributes. 
	//To facilitate the test and the location of the attributes, the attributes that are filled in the constructor DO NOT SERVE.
	//The tested attributes will  be filled with prefixed values using setters. The tested rules are from nº36 to nº43. 
	
	//The attributes that are going to be tested are:
	
 
	//Energy energy_level
	//boolean treatment   
	//int sleep_pattern       
	//int song_satisfaction 
	//int social_interaction  
	//int confidence_change  
	//int appetite_weight  
	//int relationships 
	//int durat 


		
	//the values are:
	 
	//Energy energy_level=Energy.MORE_ENERGIZED
	//boolean treatment=false 
	//int sleep_pattern=2  
	//int song_satisfaction=1
	//int social_interaction=5
	//int confidence_change=5 
	//int appetite_weight=4  
	//int relationships=3 
	//int durat=2 
 

	
	//With these values the rules that SHOULD be triggered are:
	
	//Rule36 ==> Keep listening to the same music genre regularly
	//Rule37 ==> Try new types of music
	//Rule39 ==> Try listening to another type of music
	//Rule41 ==> We recommend you to go to a nutritionist
	


	
	
    	Questionary q = new Questionary(Emotion.SAD, 4, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, 2, true, 2, 2, 2, 
    			false, true, 2, null, null, 2, 2, 2, 2, true, null, 2, 2, 2, null, 2, true, 2, 2, 2, null, 2, 2, true, null);
    	System.out.println(q.toString());
    	
    	q.setEnergy_level(Energy.MORE_ENERGIZED);
    	q.setTreatment(false);
    	q.setSleep_pattern(2);
    	q.setSong_satisfaction(1);
    	q.setSocial_interaction(5);
    	q.setConfidence_change(5);
    	q.setAppetite_weight(4);
    	q.setRelationships(3);
    	q.setdurat(2);
    	
    	try {
    	    // load up the knowledge base
    	    KieServices ks = KieServices.Factory.get();
    	    KieContainer kContainer = ks.getKieClasspathContainer();
    		KieSession kSession = kContainer.newKieSession("usecase8");
    		

    	    kSession.insert(q);
    	    kSession.fireAllRules();
    	
    	    
    	    
    	    kSession.dispose();
    	    System.out.println(q.toString());
    	} catch (Throwable t) {
    	    t.printStackTrace();
    	}
    	
    
    }

}