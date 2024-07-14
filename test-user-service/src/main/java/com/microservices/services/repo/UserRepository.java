package com.microservices.services.repo;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.microservices.services.model.Mst_User;

@Repository
public interface UserRepository extends JpaRepository<Mst_User, Long> {

    @Query(value = "select nextval('mst_user_seq')", nativeQuery = true)
    public Long userNextVal();

    @Query("SELECT e FROM Mst_User e WHERE e.user_code = ?1 AND e.active_flg = 1")
    public Mst_User findByCode(String Code);

    // List<Mst_User> findByActive_flg(int active_flg, int offset, int limit, Sort sort);

}
