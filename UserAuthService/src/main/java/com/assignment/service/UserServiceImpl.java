package com.assignment.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.assignment.exception.UserNotRegisteredException;
import com.assignment.model.User;
import com.assignment.repo.UserRepository;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;

@Service
@Transactional(propagation = Propagation.SUPPORTS, isolation = Isolation.SERIALIZABLE, readOnly = true)
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;

	@Autowired
	EurekaClient userClient;

	@Value("${file.uploader.service.id}")
	String folderUploaderServiceId;

	@Value("${file.downloader.service.id}")
	String folderDownloaderServiceId;

	private RestTemplate restTemplate;

	@Override
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public User createUser(User user) throws UserNotRegisteredException {
		User newUser = userRepo.save(user);
		if (newUser.getUserId() != null && newUser.getUserId() > 0)
			return newUser;
		throw new UserNotRegisteredException("User registration failed");
	}

	@Override
	public boolean isUserValid(String username, String password) {
		if (userRepo.findByUsernameAndPassword(username, password) != null)
			return true;
		return false;
	}

	@Override
	public String uploadFile(MultipartFile file) {
		InstanceInfo uploaderServiceInfo = userClient.getNextServerFromEureka(folderUploaderServiceId, false);
		restTemplate = new RestTemplate();
		HttpEntity<MultiValueMap<String, Object>> fileEntity = null;
		try {
			fileEntity = prepareRequest(file);
		} catch (IOException e) {
			e.printStackTrace();
		}
		ResponseEntity<Object> response = restTemplate.exchange(getUrlForUploading(uploaderServiceInfo), HttpMethod.POST, fileEntity,
				Object.class);
		return (String) response.getBody();
	}

	private HttpEntity<MultiValueMap<String, Object>> prepareRequest(MultipartFile file) throws IOException {
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("file", new ByteArrayResource(file.getBytes()));
		body.add("filename", file.getOriginalFilename());
		List<MediaType> acceptableMediaTypes = new ArrayList<MediaType>();
		acceptableMediaTypes.add(MediaType.MULTIPART_FORM_DATA);

		HttpHeaders headers = new HttpHeaders();
		headers.setAccept(acceptableMediaTypes);

		HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<MultiValueMap<String, Object>>(body, headers);
		return entity;
	}

	private String getUrlForUploading(InstanceInfo instanceInfo) {
		return "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/upload";
	}
	
	private String getUrlForDownloading(InstanceInfo instanceInfo) {
		return "http://" + instanceInfo.getIPAddr() + ":" + instanceInfo.getPort() + "/download";
	}

	@Override
	public Resource downloadFile(String fileName) {
		InstanceInfo downloaderServiceInfo = userClient.getNextServerFromEureka(folderDownloaderServiceId, false);
		restTemplate = new RestTemplate();
		ResponseEntity<Resource> response = restTemplate.getForEntity(getUrlForDownloading(downloaderServiceInfo), Resource.class);
		return response.getBody();
	}

}
