package com.care.am.service.medi;

import org.springframework.web.multipart.MultipartFile;

public interface mediFileService {
	
	public String IMAGE_REPO = "D:/mn_project";
	
	public String saveFile(MultipartFile image_file_name);
	public void deleteImage(String fileName); 
	public String getMessage(String msg, String url);
}
