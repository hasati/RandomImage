package com.zeta.randomImage;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.zeta.dto.ImageDTO;
import com.zeta.randomImageService.RandomImageService;


@RestController
	
public class RandomImageController {

	@Autowired
	RandomImageService randomImageService;
	
	@RequestMapping(value = "randomImageGenerate/{id}", method = RequestMethod.GET,produces = MediaType.IMAGE_JPEG_VALUE)
	public byte[] insertDriverDetails(@PathVariable("id") int id){
		byte[] image=randomImageService.checkId(id);		
		if(image!=null){
			return image;
		}
		final String uri = "https://picsum.photos/200/300.jpg";
		HttpHeaders headers = new HttpHeaders();       
	    RestTemplate restTemplate = new RestTemplate();
	    HttpEntity<String> entity = new HttpEntity<>(headers);
	    ResponseEntity<byte[]> result = restTemplate.exchange(uri, HttpMethod.GET,entity,  byte[].class);
	    byte body[] = result.getBody();
	    randomImageService.insertImageInDB(id,body);
	    return body;	
	}
	
	@RequestMapping(value="/images",method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
	public List<ImageDTO> fetchAllImage(){
		
		return randomImageService.fetchAllImage();
	}
}
