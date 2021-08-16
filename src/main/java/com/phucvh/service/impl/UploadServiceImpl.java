package com.phucvh.service.impl;

import java.io.File;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.phucvh.service.UploadService;

@Service
public class UploadServiceImpl implements UploadService {
	@Autowired
	ServletContext app;

	public File save(MultipartFile file,String folder) {
		File dir = new File(app.getRealPath("/assets/"+ folder));
		if(!dir.exists()) {
			dir.mkdirs();
		}
		String s = System.currentTimeMillis() + file.getOriginalFilename();
		String name = Integer.toHexString(s.hashCode()) + s.substring(s.lastIndexOf("."));
		try {
			File savedFile= new File(dir,name);
			file.transferTo(savedFile);
			System.out.println(savedFile.getAbsolutePath());
			return savedFile;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
//	public File save(MultipartFile file, String folder) {
//		  String s = System.currentTimeMillis() + file.getOriginalFilename();
//		  String name = Integer.toHexString(s.hashCode()) + s.substring(s.lastIndexOf("."));
//		  Path path=this.getPath(folder, name);
//		  System.out.println(path);
//		  try {
//		   File savedFile=path.toFile();
//		   System.out.println(savedFile);
//		   file.transferTo(savedFile);
//		   System.out.println("Path image: "+savedFile.getAbsolutePath());
//		   return savedFile;
//		  } catch (Exception e) {
//		   System.out.println("Loi implement: "+e);
//		   throw new RuntimeException(e);
//		  }
//	}
//
//		 // chuyển đổi đường dẫn
//	private Path getPath(String folder, String filename) {
//		  File dir = Paths.get(app.getRealPath("/assets/"), folder).toFile();
//		  if (!dir.exists()) {
//		   dir.mkdirs();
//		  }
//		  return Paths.get(dir.getAbsolutePath(), filename);
//	}

}
