package com.care.am.service.medi;

import org.springframework.web.multipart.MultipartFile;

public interface mediFileService {
	
	public String IMAGE_REPO = "C:\\mei\\MN-workspace\\mn_project\\src\\main\\webapp\\resources\\img";
	public String saveFile(MultipartFile image_file_name);
	public void deleteImage(String fileName); 
}
