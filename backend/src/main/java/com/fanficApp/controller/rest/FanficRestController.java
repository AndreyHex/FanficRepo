package com.fanficApp.controller.rest;

import com.fanficApp.dto.FanficDto;
import com.fanficApp.dto.response.Message;
import com.fanficApp.dto.response.Error;
import com.fanficApp.entity.Fanfic;
import com.fanficApp.service.FanficService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/api/fanfics")
public class FanficRestController {

    @Autowired
    FanficService fanficService;

    @GetMapping
    public  ResponseEntity<?> getAllFanfics(@RequestParam Optional<Integer> page,
                                            @RequestParam Optional<Integer> limit,
                                            @RequestParam(name = "sorted_by") Optional<String> sortedBy,
                                            @RequestParam Optional<String> username) {
        Sort sort;
        if(sortedBy.orElse("").equals("date")) sort = Sort.by("addedDate");
        else sort = Sort.unsorted();
        Page<FanficDto> ffList;

        if(username.isEmpty()) {
            ffList = fanficService.findAll(
                    page.orElse(0),
                    sort,
                    limit.orElse(6));
        } else {
            ffList = fanficService.findAll(
                    username.orElse(""),
                    page.orElse(0),
                    sort,
                    limit.orElse(6));
        }

        if(ffList.isEmpty()) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error("Not found."));
        return ResponseEntity.ok(ffList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFanficById(@PathVariable Long id) {
        FanficDto fanficDto = fanficService.findById(id);
        if(fanficDto == null) return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new Error("Not found."));
        return ResponseEntity.ok(fanficDto);
    }

    @PostMapping
    public ResponseEntity<?> createFanfic(@RequestBody FanficDto fanficDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(fanficService.saveFanfic(fanficDto));
    }

    @PutMapping
    public ResponseEntity<?> updateFanfic(@RequestBody FanficDto fanficDto) {
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(fanficService.updateFanfic(fanficDto));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new Error(e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteById(@PathVariable Long id) {
        if(fanficService.deleteById(id)) return ResponseEntity.status(HttpStatus.ACCEPTED).body(new Message("Done"));
        return ResponseEntity.badRequest().body(new Error("Access denied."));
    }

}
