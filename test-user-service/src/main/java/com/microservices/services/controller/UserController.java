package com.microservices.services.controller;

import java.sql.SQLException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.microservices.services.model.Mst_User;
import com.microservices.services.model.ResponseMdl;
import com.microservices.services.model.ResponseMdlPagination;
// import com.microservices.services.service.PasswordEncoder;
import com.microservices.services.service.UserService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService service;
    // @Autowired
    // private PasswordEncoder pe;

    @RequestMapping("/")
    public String index() {
        log.trace("A TRACE Message");
        log.debug("A DEBUG Message");
        log.info("An INFO Message");
        log.warn("A WARN Message");
        log.error("An ERROR Message");

        return "Howdy! Check out the Logs to see the output...";
    }

    // Create
    @PostMapping("")
public Mst_User createUser(@RequestBody Mst_User emp) {
        if (emp.getId() == null) emp.setId(service.userNextVal());
        Mst_User result = service.save(emp);
        return result;
}

    @GetMapping("")
    public Mst_User getByCode(@RequestParam String code) {
        Mst_User employee = service.findByCode(code);
        return employee;
    }

    @GetMapping("/all")
    public Page<Mst_User> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Mst_User> res = service.Pagination(page, size);
        return res;
    }

    @DeleteMapping("")
    public Mst_User deleteByCode(@RequestParam String code) {
        Mst_User res = service.softDelete(code);
        ResponseMdl response = new ResponseMdl();
        return res;
    }
    
}
