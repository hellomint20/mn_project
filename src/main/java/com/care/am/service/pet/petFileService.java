package com.care.am.service.pet;

import org.springframework.web.multipart.MultipartFile;

public interface petFileService {

	public String IMAGE_REPO = "C:\\Users\\alswl\\Desktop\\프로젝트\\mn_project\\src\\main\\webapp\\resources\\img";
	public String saveFile(MultipartFile image_file_name);
	public void deleteImage(String fileName); 
	public String getMessage(String msg, String url);
}
