package com.fanficApp.repository;

import com.fanficApp.entity.Chapter;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ChapterRepo extends JpaRepository<Chapter, Integer> {

    List<Chapter> findByFanficId(Long id);

    List<Chapter> findByFanficIdOrderByNumberAsc(Long id);

    Optional<Chapter> findByFanficIdAndNumber(Long id, Integer number);

    void deleteByFanficIdAndNumber(Long id, Integer number);

}
