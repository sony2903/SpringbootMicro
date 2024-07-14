package com.microservices.services.seeds;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.microservices.services.model.Mst_Subject;
import com.microservices.services.repo.SubjectRepository;
import com.microservices.services.service.SubjectService;

import org.springframework.beans.factory.annotation.Autowired;

@Component
public class SubjectSeeder implements CommandLineRunner {

    @Autowired
    SubjectRepository repo;
    SubjectService service;

    @Override
    public void run(String... args) throws Exception {
        // Check if data already exists to prevent duplicate seeding
        if (repo.count() == 0) {
            // Seed data
            Mst_Subject data1 = new Mst_Subject();
            data1.setId(repo.subjectNextVal());
            data1.setSubject_name("Sejarah");
            
            Mst_Subject data2 = new Mst_Subject();
            data2.setId(repo.subjectNextVal());
            data2.setSubject_name("Matematika");

            repo.save(data1);
            repo.save(data2);

            System.out.println("Seeding Subject.");
        } else {
            System.out.println("Subject already seeded.");
        }
    }
}
