package com.zeta.randomImageService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.zeta.dto.ImageDTO;
import com.zeta.randomImageDAO.RandomImageDAO;

@Service
public class RandomImageService {

	@Autowired
	RandomImageDAO randomImageDAO;
	
	public byte[] checkId(int id){
		return randomImageDAO.checkId(id);
	}
	
	public int insertImageInDB(int id,byte[] image){
		return randomImageDAO.insertImageInDB(id, image);
	}
	
	public List<ImageDTO> fetchAllImage(){
		
		return randomImageDAO.fetchAllImage();
	}
}
