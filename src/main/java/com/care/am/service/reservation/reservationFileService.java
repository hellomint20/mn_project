package com.care.am.service.reservation;

import org.springframework.web.multipart.MultipartFile;

public interface reservationFileService {
	public String IMAGE_REPO = "D:\\\\mn_project\\\\image";
	public String saveFile(MultipartFile image_file_name);
	public void deleteImage(String fileName); 
}
