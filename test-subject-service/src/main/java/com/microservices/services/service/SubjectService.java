package com.microservices.services.service;

import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.microservices.services.model.Mst_Subject;
import com.microservices.services.repo.SubjectRepository;

@Service
public class SubjectService {
	@Autowired
    SubjectRepository repo;

    public Mst_Subject save(Mst_Subject req) {
        return repo.save(req);
    }

    public Mst_Subject get(Long Id) {
        return repo.findByIdSubject(Id);
    }

    public Mst_Subject softDelete(Long code){
        Mst_Subject data = repo.findById(code).orElse(null);
        if(data != null && data.getDelete_flag() == 0){
            data.setDelete_flag(1);
            data.setDelete_date(new Date());
            data.setActive_flg(0);
            repo.save(data);
            return data;
        } else {
            return null;
        }
    }

    public Long subjectNextVal(){
        return repo.subjectNextVal();
    }
    
    public Page<Mst_Subject> Pagination(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return repo.findAll(pageRequest);
    }

}
