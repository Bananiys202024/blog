package com.web.blog.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import com.web.blog.entity.Gallery;
import com.web.blog.entity.Goals;

public class Convertor {

	public static Gallery convertGoalsToGalleryClass(Goals goals) {
		
		Gallery gallery = new Gallery();
		gallery.setDescription(goals.getDescription());
		gallery.setId(goals.getId());
		gallery.setImage(goals.getImage());
		gallery.setReachedd(new Date());
		gallery.setTitle(goals.getTitle());
		
		return gallery;
	}
	
	public static LinkedHashMap convertListToTableOfKeyAndValueMap(ArrayList<String> collection) {
	
		Map map = new LinkedHashMap();
		
		for(int i=0;i<collection.size()-1;i++) {
			
			if(i%2==0)
			{
			map.put(collection.get(i),collection.get(i+1));	
			}
		
		}
		
		return (LinkedHashMap) map;
	}

}
