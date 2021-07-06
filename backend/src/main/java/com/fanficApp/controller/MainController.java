package com.fanficApp.controller;

import com.fanficApp.dto.FanficDto;
import com.fanficApp.service.FanficService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Controller
public class MainController {

    @GetMapping(value = "/{[path:[^\\.]*}")
    public String redirectApi() {
        return "forward:/";
    }

    /*@Autowired
    FanficService fanficService;

    @GetMapping("/")
    public String mainPage(Model model) {
        Map<String, List<FanficDto>> content = new HashMap<>();
        content.put("recently", fanficService.findAll(0, Sort.by("addedDate"), 4).toList());
        content.put("recommended", fanficService.findAll(0, Sort.unsorted(), 4).toList());
        model.addAttribute("fragment", "gallery")
                .addAttribute("title", "Home")
                .addAttribute("content", content);
        return "index";
    }

    @GetMapping(value = {"/all","/all/{page}"})
    public String allFanfics(@PathVariable Optional<Integer> page, Model model) {
        Page<FanficDto> ffPage = fanficService.findAll(page.orElse(0), Sort.by("addedDate"), 8);
        model.addAttribute("fragment", "page")
                .addAttribute("title", "All fanfics")
                .addAttribute("content", ffPage);
        return "index";
    }

    @GetMapping("/fanfic/{id}/details")
    public String fanficDetails(@PathVariable long id, Model model) {
        model.addAttribute("fragment", "fanfic");
        return "index";
    }

    @GetMapping("/edit")
    public String editor(Model model) {
        model.addAttribute("fragment", "editor");
        return "index";
    }*/

}
