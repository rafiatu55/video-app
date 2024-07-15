package com.videoplatform.videoplatform.controller;

import com.videoplatform.videoplatform.service.VideoService;
import com.videoplatform.videoplatform.entity.Video;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/view")
public class ThymeleafController {

    private final VideoService videoService;

    @Autowired
    public ThymeleafController(VideoService videoService) {
        this.videoService = videoService;
    }

    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("videos", videoService.getAllVideos());
        return "home";
    }

    @GetMapping("/video/{id}")
    public String videoPage(@PathVariable Integer id, Model model) {
        Video video = videoService.getVideo(id);
        model.addAttribute("video", video);
        model.addAttribute("previousVideo", videoService.getPreviousVideo(id));
        model.addAttribute("nextVideo", videoService.getNextVideo(id));
        return "video";
    }

    @GetMapping("/upload")
    public String uploadPage() {
        return "upload";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/signup")
    public String signupPage() {
        return "signup";
    }

    @GetMapping("/password/reset")
    public String passwordResetPage() {
        return "password-reset";
    }

    @GetMapping("/password/reset/confirm")
    public String passwordResetConfirmPage() {
        return "password-reset-confirm";
    }

    @GetMapping("/verify")
    public String verifyPage() {
        return "verify";
    }
}