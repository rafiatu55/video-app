package com.videoplatform.videoplatform.controller;

import com.videoplatform.videoplatform.entity.Video;
import com.videoplatform.videoplatform.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class VideoController {

    @Autowired
    private VideoService videoService;

    @GetMapping("/admin/video/{id}")
    public String getVideo(@PathVariable Integer id, Model model) {
        Video video = videoService.getVideo(id);
        model.addAttribute("video", video);

        Video previousVideo = videoService.getPreviousVideo(id);
        model.addAttribute("previousVideo", previousVideo);

        Video nextVideo = videoService.getNextVideo(id);
        model.addAttribute("nextVideo", nextVideo);

        return "videoPage";
    }
}