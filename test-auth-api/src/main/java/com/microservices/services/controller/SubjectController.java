package com.microservices.services.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties.Jwt;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.microservices.services.config.JwtUtil;
import com.microservices.services.config.SecurityConfig;
import com.microservices.services.model.Mst_Subject;
import com.microservices.services.model.Mst_User;
import com.microservices.services.model.ResponseMdl;
import com.microservices.services.model.Mst_User.UserRole;
import com.microservices.services.model.Request.CreateSubjectRequest;
import com.microservices.services.model.Request.SignModel;
import com.microservices.services.model.Request.SubjectPaginationRequest;
import com.microservices.services.model.Response.CustomPage;
import com.microservices.services.model.Response.SubjectPaginationResponse;
import com.microservices.services.service.SigninService;
import com.microservices.services.service.SubjectService;

import org.springframework.security.core.userdetails.UserDetails;

import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequestMapping("/api/subject")
public class SubjectController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private SigninService signinService;

    @Autowired
    private SubjectService subjectService;

    @Autowired
    private SecurityConfig sc;

    @PostMapping("create")
    public ResponseEntity<?> createSubject(@Validated @RequestBody CreateSubjectRequest requestBody, HttpServletRequest requestHttp) throws Exception {
        
        ResponseMdl res = new ResponseMdl();
        // Check if user exists
        try {
            String user_code = jwtUtil.extractUsername(requestHttp.getHeader("Authorization").substring(7));
            Mst_User user = signinService.find(user_code);
            if(user.getRole().equals(UserRole.STUDENT)){
                res.setCode("400");
                res.setMessage("Student cant create subject");
                return ResponseEntity.status(400).body(res);
            }
            subjectService.create(requestBody);
            res.setMessage("Subject Created");
            return ResponseEntity.ok(res);
            
        } catch (Exception e) {
            // TODO: handle exception
            res.setMessage(e.getLocalizedMessage());
        }
        return ResponseEntity.ok(res);
    }

    @GetMapping("all")
    public ResponseEntity<?> GetSubjects(@RequestParam(value = "size") int size, @RequestParam(value = "page") int page, HttpServletRequest requestHttp) throws Exception {
        
        SubjectPaginationResponse res = new SubjectPaginationResponse();
        // Check if user exists
        try {
            String user_code = jwtUtil.extractUsername(requestHttp.getHeader("Authorization").substring(7));
            Mst_User user = signinService.find(user_code);
            if(user.getRole().equals(UserRole.STUDENT)){
                res.setCode("400");
                res.setMessage("Student cant create subject");
                return ResponseEntity.status(400).body(res);
            }
            SubjectPaginationRequest req = new SubjectPaginationRequest();
            req.setPage(page);
            req.setSize(size);
            CustomPage<Mst_Subject> data = subjectService.pagination(req);
            res.setMessage(ResponseMdl.SUCCESS);
            res.setData(data);
            return ResponseEntity.ok(res);
            
        } catch (Exception e) {
            // TODO: handle exception
            res.setMessage(e.getLocalizedMessage());
        }
        return ResponseEntity.ok(res);
    }

    // @PostMapping("auth/register")
    // public ResponseEntity<?> registerUser(@Validated @RequestBody CreateUserRequest req) throws Exception {
        
    //     ResponseMdl res = new ResponseMdl();
    //     // Check if user exists
    //     Mst_User user = signinService.find(req.getUser_code());
    //     if (user != null) {
            
    //         res.setMessage("User Code already use");
    //         return ResponseEntity.badRequest().body(res);
    //     }

    //     try {
    //         req.setPassword(sc.passwordEncoder().encode(req.getPassword()));
    //         signinService.create(req);
    //         res.setMessage("Register Succeed");

    //         return ResponseEntity.ok(res);
            
    //     } catch (Exception e) {
    //         // TODO: handle exception
    //         res.setMessage(e.getLocalizedMessage());
    //         return ResponseEntity.badRequest().body(res);

    //     }
    // }
}

