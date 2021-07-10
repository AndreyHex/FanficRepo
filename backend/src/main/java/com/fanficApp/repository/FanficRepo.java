package com.fanficApp.repository;


import com.fanficApp.entity.Fanfic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;



public interface FanficRepo extends JpaRepository<Fanfic, Long> {

    Page<Fanfic> findByUserId(Long id, Pageable pageable);

    Fanfic save(Fanfic fanfic);

}
