package com.fanficApp.controller.rest;

import com.fanficApp.dto.ChapterDto;
import com.fanficApp.dto.response.Error;
import com.fanficApp.service.ChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fanfics")
public class ChapterRestController {

    @Autowired
    ChapterService chapterService;

    @PutMapping("/{ffId}/chapters")
    public ResponseEntity<?> createChapter(@PathVariable Long ffId, @RequestBody List<ChapterDto> chapterDtoList) {
        try {
            return ResponseEntity.ok(chapterService.saveChapter(ffId, chapterDtoList));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new Error(e.getMessage()));
        }
    }

    @DeleteMapping("/{ffId}/chapters/{number}")
    public ResponseEntity<?> deleteByFanficIdAndNumber(@PathVariable Long ffId, @PathVariable Integer number) {
        if(chapterService.deleteByFanficIdAndNumber(ffId, number)) return ResponseEntity.ok("Done");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Access denied.");
    }

    @GetMapping("/{ffId}/chapters")
    public ResponseEntity<?> getChaptersByFanficId(@PathVariable Long ffId) {
        List<ChapterDto> chapters = chapterService.findByFanficId(ffId);
        if(chapters.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found.");
        return ResponseEntity.ok(chapters);
    }

    @GetMapping("/{ffId}/chapters/{number}")
    public ResponseEntity<?> getChapter(@PathVariable Long ffId, @PathVariable Integer number) {
        ChapterDto chapterDto = chapterService.findByFanficIdAndNumber(ffId, number);
        if(chapterDto == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found.");
        return ResponseEntity.ok(chapterDto);
    }

}
