package com.fanficApp.repository;

import com.fanficApp.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepo extends JpaRepository<Tag, Integer> {
    public Optional<Tag> findByName(String name);
}
