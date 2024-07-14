package com.microservices.services.seeds;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.microservices.services.model.Mst_User;
import com.microservices.services.model.Mst_User.UserRole;
import com.microservices.services.repo.UserRepository;
// import com.microservices.services.service.PasswordEncoder;

import org.springframework.beans.factory.annotation.Autowired;

@Component
public class UserSeeder implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;
    
    // @Autowired
    // private PasswordEncoder securityService;

    @Override
    public void run(String... args) throws Exception {
        // Check if data already exists to prevent duplicate seeding
        // if (userRepository.count() == 0) {
        //     // Seed data
        //     Mst_User data1 = new Mst_User();
        //     data1.setId(userRepository.userNextVal());
        //     data1.setUser_code("E001");
        //     data1.setName("Guru S");
        //     data1.setRole(UserRole.TEACHER);
        //     data1.setPassword(securityService.encodePassword("password123"));
        //     data1.setActive_flg(1);
            
        //     Mst_User data2 = new Mst_User();
        //     data2.setId(userRepository.userNextVal());
        //     data2.setName("Murid S");
        //     data2.setUser_code("E002");
        //     data2.setRole(UserRole.STUDENT);
        //     data2.setActive_flg(1);;
        //     data2.setPassword(securityService.encodePassword("password123"));
            
        //     userRepository.save(data1);
        //     userRepository.save(data2);
            
        //     System.out.println("Seeding User.");
        // } else {
        //     System.out.println("User` already seeded.");
        // }
    }
}
