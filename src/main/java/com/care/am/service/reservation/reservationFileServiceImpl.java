package com.care.am.service.reservation;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class reservationFileServiceImpl implements reservationFileService{
	
public String saveFile(MultipartFile image_file_name) {
		
		SimpleDateFormat fo = new SimpleDateFormat("yyMMdd-");
		String sysFileName = fo.format(new Date());
		
		sysFileName = sysFileName + image_file_name.getOriginalFilename();
		File saveFile = new File(IMAGE_REPO + "/" + sysFileName);
		try {
			image_file_name.transferTo(saveFile);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sysFileName;
	}
	
	public void deleteImage(String fileName) {
		File file = new File(IMAGE_REPO + "/" + fileName);
		file.delete();
	}
}
