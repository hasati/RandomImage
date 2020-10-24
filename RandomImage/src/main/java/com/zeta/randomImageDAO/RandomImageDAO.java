package com.zeta.randomImageDAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.zeta.dto.ImageDTO;

@Repository
public class RandomImageDAO {

	@Autowired
	private JdbcTemplate jdbc;
	
	public byte[] checkId(int id){
		final String checkId = "SELECT id,image FROM Random_Image where id="+id;
		List<ImageDTO> ids=jdbc.query(checkId, new BeanPropertyRowMapper<ImageDTO>(ImageDTO.class));
		if(ids.size()>0){
			return ids.get(0).getImage();
		}
		return null;
	}
	
	public int insertImageInDB(int id,byte[] image){
		final String insertQuery = "INSERT INTO Random_Image(id,image) VALUES(?,?)";
		System.out.println(insertQuery);
		int count=jdbc.update(insertQuery,new Object[] {
				 id,
				 image});
		return count;
	}
	
	public List<ImageDTO> fetchAllImage(){
		final String checkId = "SELECT id,image FROM Random_Image";
		List<ImageDTO> imageDTO=jdbc.query(checkId, new BeanPropertyRowMapper<ImageDTO>(ImageDTO.class));
		return imageDTO;
	}
}
