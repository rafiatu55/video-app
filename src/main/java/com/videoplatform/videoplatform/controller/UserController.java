package com.videoplatform.videoplatform.controller;

import com.videoplatform.videoplatform.entity.Video;
import com.videoplatform.videoplatform.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private VideoService videoService;

    @GetMapping("")
    public String userHome(Model model) {
        List<Video> videos = videoService.getAllVideos();
        model.addAttribute("videos", videos);
        return "userHome";
    }

    @GetMapping("/video/{id}")
    public String showVideoPage(@PathVariable Integer id, Model model) {
        Video video = videoService.getVideo(id);
        Video previousVideo = videoService.getPreviousVideo(id);
        Video nextVideo = videoService.getNextVideo(id);

        model.addAttribute("video", video);
        model.addAttribute("previousVideo", previousVideo);
        model.addAttribute("nextVideo", nextVideo);

        return "videoPage";
    }
}
