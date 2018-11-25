package com.hibernate.mappings.jpa.repository;

import java.util.Arrays;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.hibernate.mappings.jpa.EntityA;
import com.hibernate.mappings.jpa.EntityB;

/**
 * This simple setup class will run during the bootstrap process of Spring and
 * will create some setup data <br>
 * The main focus here is creating some Music artist, Samples and Samples'
 * matches
 */
@Component
public class PerfectMatchSetup implements ApplicationListener<ContextRefreshedEvent> {

    // Only for setup purposes
    private boolean setupDone;

    
    @Autowired
    private EntityARepository entityARepository;
    
    @Autowired
    private EntityBRepository entityBRepository;

    /*
     * (non-Javadoc)
     *
     * @see
     * org.springframework.context.ApplicationListener#onApplicationEvent(org.
     * springframework.context.ApplicationEvent)
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent arg0) {

        if (!setupDone) {
        	entityARepository.deleteAll();
        	entityBRepository.deleteAll();
        	
            createContent();
        }

    }


    /**
     * Create Music data in the DB
     */
    private void createContent() {
    	EntityB bOne = new EntityB();
    	bOne.setStrB("bOne");
    	
    	EntityB bTwo = new EntityB();
    	bTwo.setStrB("BTwo");
    	
    	EntityA aOne = new EntityA();
    	
    	aOne.setStrA("aOne");
    	aOne.setEntityBList(Arrays.asList(bOne, bTwo));
    	
    	bOne.setRefEntityA(aOne);
    	bTwo.setRefEntityA(aOne);
    	
    	if(Objects.nonNull(this.entityARepository)) {
    		entityARepository.save(aOne);
    	}
    	
    	if(Objects.nonNull(this.entityBRepository)) {
    		entityBRepository.save(bOne);
    		entityBRepository.save(bTwo);
    	}
    	
    	
    }
}