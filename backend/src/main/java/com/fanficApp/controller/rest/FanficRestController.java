package com.fanficApp.controller.rest;

import com.fanficApp.dto.FanficDto;
import com.fanficApp.service.FanficService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fanfics")
public class FanficRestController {

    @Autowired
    FanficService fanficService;

    @GetMapping("/by/user/{name}/page/{page}")
    public ResponseEntity<?> getFanficsByUsername(@PathVariable String name, @PathVariable int page) {
        Page<FanficDto> fanficDtos = fanficService.findByUsername(name, page, Sort.unsorted());
        if(fanficDtos.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found.");
        return ResponseEntity.ok(fanficDtos);
    }

    @GetMapping
    public  ResponseEntity<?> getAllFanfics() {
        List<FanficDto> ffList = fanficService.findAll();
        if(ffList.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found.");
        return ResponseEntity.ok(ffList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFanficById(@PathVariable Long id) {
        FanficDto fanficDto = fanficService.findById(id);
        if(fanficDto == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found.");
        return ResponseEntity.ok(fanficDto);
    }

    @GetMapping("/page/{p}")
    public ResponseEntity<?> getPage(@PathVariable int p) {
        Page<FanficDto> page = fanficService.findAll(p, Sort.unsorted(), 8);
        if(page.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Not found.");
        return ResponseEntity.ok(page);
    }

    @PutMapping
    public ResponseEntity<?> createFanfic(@RequestBody FanficDto fanficDto) {
        if(fanficService.saveFanfic(fanficDto)) {
            return ResponseEntity.status(HttpStatus.CREATED).body("Created.");
        } else return ResponseEntity.badRequest().body("Error.");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        if(fanficService.deleteById(id)) return ResponseEntity.ok("Done");
        return ResponseEntity.badRequest().body("Access denied.");
    }

}
