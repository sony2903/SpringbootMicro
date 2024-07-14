package com.microservices.services.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.microservices.services.model.Mst_Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Mst_Subject, Long> {

    @Query(value = "select nextval('mst_subject_seq')", nativeQuery = true)
    public Long subjectNextVal();

    @Query(value = "SELECT e FROM Mst_Subject e WHERE e.id = ?1 AND e.active_flg = 1")
    public Mst_Subject findByIdSubject(Long id);

    // List<Mst_User> findByActive_flg(int active_flg, int offset, int limit, Sort sort);

}
