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

import com.microservices.services.model.CustomPage;
import com.microservices.services.model.Mst_Subject;
import com.microservices.services.model.ResponseMdl;
import com.microservices.services.model.ResponseMdlPagination;
import com.microservices.services.service.SubjectService;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/subject")
public class SubjectController {

    @Autowired
    SubjectService service;

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
public Mst_Subject createSubject(@RequestBody Mst_Subject emp) {
    ResponseMdl response = new ResponseMdl();
    boolean isId = emp.getId() == null;
    if (emp.getId() == null) emp.setId(service.subjectNextVal());
    System.out.println(emp);
    Mst_Subject result = service.save(emp);
    return result;
}

    @GetMapping("")
    public Mst_Subject getByCode(@RequestParam Long code) {
        Mst_Subject Data = service.get(code);
        ResponseMdl response = new ResponseMdl();
        return Data;
    }

    @GetMapping("/all")
    public CustomPage<Mst_Subject> getAll(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Mst_Subject> data = service.Pagination(page, size);
        CustomPage<Mst_Subject> customPage = new CustomPage<>();
        customPage.setTotalPages(data.getTotalPages());
        customPage.setTotalElements(data.getTotalElements());
        customPage.setNumber(data.getNumber());
        customPage.setSize(data.getSize());
        customPage.setContent(data.getContent());
        return customPage;
    }

    @DeleteMapping("")
    public Mst_Subject deleteByCode(@RequestParam Long code) {
        Mst_Subject data = service.softDelete(code);
        return data;
    }
    
}
