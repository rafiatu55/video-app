package com.videoplatform.videoplatform.service;

import com.videoplatform.videoplatform.entity.Video;
import com.videoplatform.videoplatform.exception.ResourceNotFoundException;
import com.videoplatform.videoplatform.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class VideoService {

    private static final Logger logger = LoggerFactory.getLogger(VideoService.class);

    @Autowired
    private S3Client s3Client;

    @Autowired
    private VideoRepository videoRepository;

    @Value("${aws.s3.bucket.name}")
    private String bucketName;

    public String uploadVideo(MultipartFile file, String title, String description) throws IOException {
        logger.info("Starting video upload process");

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        logger.info("Generated file name: {}", fileName);

        try {
            s3Client.putObject(PutObjectRequest.builder()
                            .bucket(bucketName)
                            .key(fileName)
                            .build(),
                    RequestBody.fromBytes(file.getBytes()));
            logger.info("File uploaded to S3");

            String videoUrl = s3Client.utilities().getUrl(builder -> builder.bucket(bucketName).key(fileName)).toExternalForm();
            logger.info("Generated video URL: {}", videoUrl);

            Video video = new Video();
            video.setTitle(title);
            video.setDescription(description);
            video.setVideoUrl(videoUrl);
            video.setFileSize(file.getSize());
            videoRepository.save(video);
            logger.info("Video metadata saved to database");

            return videoUrl;
        } catch (Exception e) {
            logger.error("Error during video upload process", e);
            throw new IOException("Error uploading video", e);
        }
    }

    public Video getVideo(Integer id) {
        return videoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Video not found"));
    }

    public List<Video> getAllVideos() {
        return videoRepository.findAll();
    }

    public Video getPreviousVideo(Integer id) {
        List<Video> videos = getAllVideos();
        for (int i = 1; i < videos.size(); i++) {
            if (videos.get(i).getId().equals(id)) {
                return videos.get(i - 1);
            }
        }
        return null;
    }

    public Video getNextVideo(Integer id) {
        List<Video> videos = getAllVideos();
        for (int i = 0; i < videos.size() - 1; i++) {
            if (videos.get(i).getId().equals(id)) {
                return videos.get(i + 1);
            }
        }
        return null;
    }

    public void updateVideo(Integer id, String title, String description) {
        Video video = videoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Video not found"));
        video.setTitle(title);
        video.setDescription(description);
        videoRepository.save(video);
    }

}