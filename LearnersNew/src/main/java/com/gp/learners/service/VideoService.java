package com.gp.learners.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gp.learners.entities.Video;
import com.gp.learners.repositories.VideoRepository;

@Service
public class VideoService {

	@Autowired
	VideoRepository videoRepository;

	@Autowired
	VideoService videoService;

	// getVideoList
	public List<Video> getVideoList() {
		System.out.println("In Video Repo");
		List<Video> videoList = videoRepository.findAll();
//		Video video = videoList.get(0);
//		System.out.println(video.getDescription());
		return videoList;
	}

	// get Video Details
	public Video getVideoDetails(Integer videoId) {
		if (videoId != null) {
			if (videoRepository.existsById(videoId)) {
				return videoRepository.getVideoById(videoId);
			}
		}
		return new Video();
	}

	// Add Video
	public String addVideo(Video video) {

		Video result = videoRepository.save(video);
		if (result != null)
			return "success";
		else
			return "notsuccess";
	}

	// delete video
	public String deleteVideo(Integer videoId) {
		if (videoId != null) {
			if (videoRepository.existsById(videoId)) {
				videoRepository.deleteById(videoId);
				return "success";
			}
		}
		return "error";
	}

	// update Video Details
	public Video updateVideo(Video video) {
		if (videoRepository.existsById(video.getVideoId())) {
			return videoRepository.save(video);
		}

		return new Video();
	}

}