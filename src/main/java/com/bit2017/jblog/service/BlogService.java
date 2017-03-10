package com.bit2017.jblog.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.bit2017.jblog.exception.GalleryUploadException;
import com.bit2017.jblog.repository.BlogDao;
import com.bit2017.jblog.vo.BlogVo;
import com.bit2017.jblog.vo.PostVo;

@Service
public class BlogService {
	private static final String SAVE_PATH = "/upload";
	public static final String BASE_URL = "/list/";
	
	@Autowired
	private BlogDao blogDao;

	public List<BlogVo> getList() {
		List<BlogVo> list = blogDao.getList();
		return list;
	}
	
	public BlogVo getInfo(String blogId) {
		return blogDao.getInfo(blogId);
	}

	public boolean write(PostVo postVo) {
		return blogDao.write(postVo) == 1;
		
	}
/*	
	public PostVo view(String userId) {
		
		return blogDao.view(userId);
	}*/
	
	private Long maxPost(String blogId){
		return blogDao.maxPost(blogId);
	}
	
	public PostVo postView(String blogId, Long pno, Long cno) {
		Long pvo = null;
		if(pno == null){
			if (cno == null){
				pvo = maxPost(blogId);
			}else{
				Map<String,	Object> map = new HashMap<>();
				map.put("blogId", blogId);
				map.put("cno", cno);
				pvo = catemaxPost(map);
			}
				
			return blogDao.view(pvo);

		}else{
			PostVo plist = blogDao.view(pno);
			return plist;
		}
	}
	
	private Long catemaxPost(Map<String, Object> map) {
		return blogDao.catemaxPost(map);
	}

	public List<PostVo> cateListView(Long cno) {
		if(cno == null ) {
			List<PostVo> clist = blogDao.postList();
			System.out.println(clist);
			return clist;
			
		}else{
			List<PostVo> list = blogDao.cateListView(cno);
			System.out.println(list);
			return list;
		}
	}


	public void restore(BlogVo blogVo, MultipartFile multipartFile) throws GalleryUploadException {
		try {
			if (multipartFile.isEmpty() == true) {
				throw new GalleryUploadException( "MultipartFile is Empty" );
			}
			
			String orgFile = multipartFile.getOriginalFilename();
			String fileExtName = orgFile.substring( orgFile.lastIndexOf('.') + 1, orgFile.length() );
			String saveFile = generateSaveFileName( fileExtName );
			// 파일 저장
			writeFile(multipartFile, saveFile);
			// DB에 저장
			blogVo.setLogo(saveFile);
		} catch (IOException ex) {
			//1.log 남기기
			//2.runtime exception 전환 
			throw new GalleryUploadException( "save file uploded" );
		}
		blogDao.update(blogVo);
	}
	
	
	private void writeFile(MultipartFile multipartFile, String saveFileName) throws IOException {
		byte[] fileData = multipartFile.getBytes();
		FileOutputStream fos = new FileOutputStream( SAVE_PATH + "/" + saveFileName );
		fos.write( fileData );
		fos.close();
	}
	
	private String generateSaveFileName(String extName) {
		String fileName = "";
		Calendar calendar = Calendar.getInstance();

		fileName += calendar.get(Calendar.YEAR);
		fileName += calendar.get(Calendar.MONTH);
		fileName += calendar.get(Calendar.DATE);
		fileName += calendar.get(Calendar.HOUR);
		fileName += calendar.get(Calendar.MINUTE);
		fileName += calendar.get(Calendar.SECOND);
		fileName += calendar.get(Calendar.MILLISECOND);
		fileName += ("." + extName);

		return fileName;
	}


}
