package com.care.am.service.pet;

import org.springframework.web.multipart.MultipartFile;

public interface petFileService {

	public String IMAGE_REPO = "D:\\fin\\project\\workspace\\mn_project\\src\\main\\webapp\\resources\\img";
	public String saveFile(MultipartFile image_file_name);
	public void deleteImage(String fileName); 
}
