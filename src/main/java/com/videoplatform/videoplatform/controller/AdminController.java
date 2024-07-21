package com.videoplatform.videoplatform.controller;

import com.videoplatform.videoplatform.entity.Video;
import com.videoplatform.videoplatform.service.VideoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private VideoService videoService;

    @GetMapping
    public String adminHome(Model model) {
        List<Video> videos = videoService.getAllVideos();
        model.addAttribute("videos", videos);
        return "adminHome";
    }

    @GetMapping("/upload")
    public String showUploadForm() {
        return "uploadVideo";
    }

    @PostMapping("/upload")
    public String uploadVideo(@RequestParam("title") String title,
                              @RequestParam("description") String description,
                              @RequestParam("videoFile") MultipartFile videoFile,
                              RedirectAttributes redirectAttributes) {
        try {
            videoService.uploadVideo(videoFile, title, description);
            redirectAttributes.addFlashAttribute("message", "Video uploaded successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Failed to upload video: " + e.getMessage());
        }
        return "redirect:/admin/upload";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") Integer id, Model model) {
        Video video = videoService.getVideo(id);
        model.addAttribute("video", video);
        return "editVideo";
    }

    @PostMapping("/edit/{id}")
    public String editVideo(@PathVariable("id") Integer id,
                            @RequestParam("title") String title,
                            @RequestParam("description") String description,
                            RedirectAttributes redirectAttributes) {
        try {
            videoService.updateVideo(id, title, description);
            redirectAttributes.addFlashAttribute("message", "Video updated successfully!");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("message", "Failed to update video: " + e.getMessage());
        }
        return "redirect:/admin";
    }

}