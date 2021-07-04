package com.fanficApp.service;

import com.fanficApp.dto.ChapterDto;
import com.fanficApp.entity.Chapter;
import com.fanficApp.entity.Fanfic;
import com.fanficApp.entity.User;
import com.fanficApp.repository.ChapterRepo;
import com.fanficApp.repository.FanficRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ChapterService {

    @Autowired
    ChapterRepo chapterRepo;
    @Autowired
    FanficRepo fanficRepo;

    public ChapterDto findByFanficIdAndNumber(Long ffId, Integer number) {
        Chapter chapter = chapterRepo.findByFanficIdAndNumber(ffId, number).orElse(null);
        if(chapter == null) return null;
        return convertToDto(chapter);
    }

    public List<ChapterDto> findByFanficId(Long ffId) {
        return chapterRepo.findByFanficId(ffId).stream().map(this::convertToDto).collect(Collectors.toList());
    }

    public boolean saveChapter(ChapterDto chapterDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Fanfic fanfic = fanficRepo.findById(chapterDto.getFanficId()).orElse(null);
        if(fanfic == null
                || !user.getUsername().equals(fanfic.getUser().getUsername())
                    && !user.getRoles().stream().anyMatch(i -> i.getName().equals("ROLE_ADMIN"))) {
            return false;
        }
        Chapter chapter = convertToEntity(chapterDto);
        chapterRepo.save(chapter);
        return true;
    }

    public boolean deleteByFanficIdAndNumber(Long ffId, Integer number) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Fanfic fanfic = fanficRepo.findById(ffId).orElse(null);
        if(fanfic == null) return true;
        if(!user.getUsername().equals(fanfic.getUser().getUsername()) && !user.getRoles().stream().anyMatch(i -> i.getName().equals("ROLE_ADMIN"))) {
            return false;
        }
        chapterRepo.deleteByFanficIdAndNumber(ffId, number);
        return true;
    }

    private Chapter convertToEntity(ChapterDto chapterDto) {
        Chapter chapter = chapterRepo.findByFanficIdAndNumber(chapterDto.getFanficId(), chapterDto.getNumber()).orElse(new Chapter());
        chapter.setFanfic(fanficRepo.findById(chapterDto.getFanficId()).orElse(null));
        chapter.setNumber(chapterDto.getNumber());
        chapter.setTitle(chapterDto.getTitle());
        chapter.setText(chapterDto.getText());
        return chapter;
    }

    private ChapterDto convertToDto(Chapter chapter) {
        return new ChapterDto(
                chapter.getFanfic().getId(),
                chapter.getFanfic().getTitle(),
                chapter.getNumber(),
                chapter.getTitle(),
                chapter.getText()
        );
    }
}
