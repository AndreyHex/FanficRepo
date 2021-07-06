package com.fanficApp.repository;


import com.fanficApp.entity.Fanfic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import java.awt.print.Pageable;


public interface FanficRepo extends JpaRepository<Fanfic, Long> {

    Page<Fanfic> findByUserId(Long id, PageRequest pageRequest);

    Fanfic save(Fanfic fanfic);

}
