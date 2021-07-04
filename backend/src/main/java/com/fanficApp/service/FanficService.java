package com.fanficApp.service;

import com.fanficApp.dto.FanficDto;
import com.fanficApp.entity.Fanfic;
import com.fanficApp.entity.Tag;
import com.fanficApp.entity.User;
import com.fanficApp.repository.FanficRepo;
import com.fanficApp.repository.TagRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FanficService {

    @Autowired
    FanficRepo fanficRepo;
    @Autowired
    UserService userService;
    @Autowired
    TagRepo tagRepo;

    public boolean saveFanfic(FanficDto fanficDto) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Fanfic fanfic = convertToEntity(fanficDto);
        if(fanficDto.getId() != null
                && !fanfic.getUser().getUsername().equals(user.getUsername())
                && !user.getRoles().stream().anyMatch(i -> i.getName().equals("ROLE_ADMIN"))) {
            return false;
        }
        fanficRepo.save(fanfic);
        return true;
    }

    public boolean deleteById(Long id){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Fanfic fanfic = fanficRepo.findById(id).orElse(null);
        if(fanfic == null) return true;
        if(!fanfic.getUser().getUsername().equals(user.getUsername())
                && !user.getRoles().stream().anyMatch(i -> i.getName().equals("ROLE_ADMIN"))) {
            return false;
        }
        fanficRepo.deleteById(id);
        return true;
    }

    public Page<FanficDto> findByUsername(String name, int page, Sort sort) {
        return fanficRepo.findByUserId(
                        ((User) userService.loadUserByUsername(name)).getId(),
                        PageRequest.of(page, 8, sort))
                .map(this::convertToDto);
    }

    public FanficDto findById(Long id) {
        Fanfic fanfic = fanficRepo.findById(id).orElse(null);
        return fanfic == null ? null : convertToDto(fanfic);
    }

    public Page<FanficDto> findAll(int page, Sort sort, int size) {
        return fanficRepo
                .findAll(PageRequest.of(page, size, sort))
                .map(this::convertToDto);
    }

    public List<FanficDto> findAll() {
        return fanficRepo
                .findAll().stream().map(this::convertToDto)
                .collect(Collectors.toList());
    }

    private Fanfic convertToEntity(FanficDto fanficDto) {
        Fanfic fanfic = new Fanfic();
        if (fanficDto.getId() != null) fanfic = fanficRepo.findById(fanficDto.getId()).orElse(new Fanfic());
        else fanfic.setAddedDate(new Date());
        fanfic.setTitle(fanficDto.getTitle());
        fanfic.setDescription(fanficDto.getDescription());
        if(fanfic.getUser() == null) fanfic.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        fanfic.setFandom(fanficDto.getFandom());
        fanfic.setId(fanficDto.getId());
        List<Tag> tags = new ArrayList<>();
        fanficDto.getTags().forEach(i -> tags.add(tagRepo.findByName(i).orElse(new Tag(i))));
        fanfic.setTags(tags);
        return fanfic;
    }

    private FanficDto convertToDto(Fanfic fanfic) {
        return new FanficDto(
                fanfic.getUser().getUsername(),
                fanfic.getId(),
                fanfic.getTitle(),
                fanfic.getDescription(),
                fanfic.getFandom(),
                fanfic.getChapterList(),
                fanfic.getAddedDate(),
                new ArrayList<>(fanfic.getTags().stream().map(Tag::getName).collect(Collectors.toList()))
        );
    }

}
