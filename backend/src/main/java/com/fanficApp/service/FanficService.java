package com.fanficApp.service;

import com.fanficApp.dto.FanficDto;
import com.fanficApp.entity.Fanfic;
import com.fanficApp.entity.Image;
import com.fanficApp.entity.Tag;
import com.fanficApp.entity.User;
import com.fanficApp.repository.FanficRepo;
import com.fanficApp.repository.ImageRepo;
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
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class FanficService {

    @Autowired
    FanficRepo fanficRepo;
    @Autowired
    UserService userService;
    @Autowired
    TagRepo tagRepo;
    @Autowired
    ImageRepo imageRepo;

    public FanficDto saveFanfic(FanficDto fanficDto) {
        fanficDto.setId(null);
        Fanfic fanfic = convertToEntity(fanficDto);
        fanfic.setUser((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        if(fanficDto.getImgId() != null) {
            Optional<Image> img = imageRepo.findById(fanficDto.getImgId());
            img.ifPresent(fanfic::setImage);
        }
        return convertToDto(fanficRepo.save(fanfic));
    }

    public FanficDto updateFanfic(FanficDto fanficDto) throws Exception {

        Fanfic fanfic = fanficRepo.findById(fanficDto.getId()).orElse(null);
        if(fanfic == null) throw new Exception("Not found.");

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if(!fanfic.getUser().getUsername().equals(user.getUsername())
                && !user.getRoles().stream().anyMatch(i -> i.getName().equals("ROLE_ADMIN")))
            throw new Exception("Access denied.");

        return convertToDto(fanficRepo.save(fanfic));
    }

    public boolean deleteById(Long id) {
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

    public FanficDto findById(Long id) {
        Fanfic fanfic = fanficRepo.findById(id).orElse(null);
        return fanfic == null ? null : convertToDto(fanfic);
    }

    public Page<FanficDto> findAll(int page, Sort sort, int limit) {
        return fanficRepo
                .findAll(PageRequest.of(page, limit, sort))
                .map(this::convertToDto);
    }

    public Page<FanficDto> findAll(String username, int page, Sort sort, int limit) {
        return fanficRepo.findByUserId(
                ((User) userService.loadUserByUsername(username)).getId(),
                (Pageable) PageRequest.of(page, limit, sort))
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
        System.out.println("Fanfic user set: "+fanfic.getUser());
        fanfic.setFandom(fanficDto.getFandom());

        List<Tag> tags = new ArrayList<>();
        if(fanficDto.getTags() != null) fanficDto.getTags().forEach(i -> tags.add(tagRepo.findByName(i).orElse(new Tag(i))));
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
                fanfic.getImage(),
                fanfic.getChapterList(),
                fanfic.getAddedDate(),
                fanfic.getTags()
        );
    }

}
