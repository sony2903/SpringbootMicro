package com.microservices.services.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.microservices.services.model.Mst_User;
import com.microservices.services.repo.UserRepository;

@Service
public class UserService {
	@Autowired
    UserRepository repo;

    public Mst_User save(Mst_User req) {
        return repo.save(req);
    }

    public Mst_User get(Long Id) {
        return repo.findById(Id).orElse(null);
    }

    public Mst_User softDelete(String code){
        Mst_User data = this.findByCode(code);
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

    public Long userNextVal(){
        return repo.userNextVal();
    }

    public Mst_User findByCode(String code) {
        return repo.findByCode(code);
    }
    
    public Page<Mst_User> Pagination(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        return repo.findAll(pageRequest);
    }

    // public List<Mst_User> pagination(int page, int size) {
    //     int offset = page * size;
    //     Sort sort = Sort.by("id").ascending(); // Replace "id" with your sort field
    //     return repo.findByActive_flg(1, offset, size, sort);
    // }

}
