package com.care.am.service.medi;

import org.springframework.web.multipart.MultipartFile;

public interface mediFileService {
	
	public String IMAGE_REPO = "D:/mn_project/image";
	//D:\fin\project\workspace\mn_project\src\main\webapp\resources\img
	//D:/mn_project/image
	public String saveFile(MultipartFile image_file_name);
	public void deleteImage(String fileName); 
	public String getMessage(String msg, String url);
}
