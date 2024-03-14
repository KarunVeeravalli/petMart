package com.clayfin;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.clayfin.enums.ERole;
import com.clayfin.model.Role;
import com.clayfin.repository.RoleRepository;

@Component
public class Comp implements CommandLineRunner {

     @Autowired
     RoleRepository eRepo;

     @Override
     public void run(String... args) throws Exception {
    	 System.out.println("INSIDE THE COMMANDlINE rUNNNER");
    	 System.out.println(eRepo.findAll().size());
    	 if(eRepo.findAll().size()==0) {
           eRepo.saveAll(Arrays.asList(
                      new Role((long) 1001,ERole.ROLE_USER),
                      new Role((long) 1002,ERole.ROLE_MANAGER),
                      new Role((long) 1003,ERole.ROLE_ADMIN))
            ); 

          System.out.println("----------All Data saved into Database----------------------");
          }	 
	 }

}