package com.gp.learners.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.gp.learners.entities.Video;
import com.gp.learners.repositories.VideoRepository;
import com.gp.learners.service.VideoService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", maxAge = 3600)
public class VideoController {

	@Autowired
	VideoService videoService;

	@Autowired
	VideoRepository videoRepository;

	@GetMapping("/videos")
	public List<Video> getVideoList() {
		System.out.println("In Video Controller");
		return videoService.getVideoList();

	}

	// get Specific Video data
	@GetMapping("video/{videoId}")
	public ResponseEntity<Video> getVideo(@PathVariable("videoId") Integer videoId) {
		System.out.println("In Video Controller VmoreDetails");
		Video video = videoService.getVideoDetails(videoId);
		if (video.getVideoId() != null) {
			return ResponseEntity.ok(video);
		}
		return ResponseEntity.noContent().build();
	}

	// save video
	@PostMapping("/video/add")

	public Video saveVideo(@RequestBody Video video) {
		System.out.println("In video controller Adding method");
		return videoRepository.save(video);
	}

	// delete Video
	@DeleteMapping("video/{videoId}")
	public ResponseEntity<Void> deleteVideo(@PathVariable("videoId") Integer videoId) {
		System.out.println("In video controller delete method");

		String reply = videoService.deleteVideo(videoId);
		if (reply.equals("success")) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.notFound().build();
	}

	// update video Details
	@PutMapping("video/update")
	public ResponseEntity<Video> updateVideo(@Valid @RequestBody Video object) {
		Video video = videoService.updateVideo(object);
		if (video.getVideoId() != null) {
			return ResponseEntity.ok(video);
		}
		return ResponseEntity.notFound().build();
	}

}