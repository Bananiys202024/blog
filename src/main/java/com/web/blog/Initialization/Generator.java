package com.web.blog.Initialization;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import javax.imageio.ImageIO;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.datastax.driver.core.utils.UUIDs;
import com.web.blog.entity.Gallery;
import com.web.blog.entity.Goals;
import com.web.blog.entity.Jokes;
import com.web.blog.entity.Notes;
import com.web.blog.entity.User;
import com.web.blog.model.AddValuesToDiary;

public class Generator {

	private static final Logger logger = LogManager.getLogger(Generator.class);

	
	final static ArrayList<String> arrayJokes = new ArrayList<String>() {{ //access by index, so ArrayList
	    add("Atheism is a non-prophet organization.");
	    add("Death is caused by swallowing small amounts of saliva over a long period of time");
	    add("Don't sweat the petty things and don't pet the sweaty things.");
	    add("Electricity is really just organized lightning");
	    add("Fighting for peace is like screwing for virginity.");
	    add("Frisbeetarianism is the belief that when you die, your soul goes up on the roof and gets stuck");
	    add("Have you ever noticed that anybody driving slower than you is an idiot, and anyone going faster than you is a maniac?");
	    add("\"I am\" is reportedly the shortest sentence in the English language. Could it be that \"I do\" is the longest sentence?");
	    add("I think it would be interesting if old people got anti-Alzheimer's disease where they slowly began to recover other people's lost memories.");
	    add("I think people should be allowed to do anything they want. We haven't tried that for a while. Maybe this time it'll work");
	    add("I was thinking about how people seem to read the Bible a whole lot more as they get older; then it dawned on me - they're cramming for their final exam.");
	    add("I went to a bookstore and asked the saleswoman, \"Where's the self-help section?\" She said if she told me, it would defeat the purpose");
	    add("I would never want to be a member of a group whose symbol was a guy nailed to two pieces of wood");
	    add("I'm completely in favor of the separation of Church and State. My idea is that these two institutions screw us up enough on their own, so both of them together is certain death.");
	    add("I'm not concerned about all hell breaking loose, but that a PART of hell will break loose... it'll be much harder to detect.");
	    add("If we could just find out who's in charge, we could kill him");
	    add("If you can't beat them, arrange to have them beaten");
	    add("Just cause you got the monkey off your back doesn't mean the circus has left town");
	    add("May the forces of evil become confused on the way to your house");
	    add("Most people work just hard enough not to get fired and get paid just enough money not to quit");
	    add("Not only do I not know what's going on, I wouldn't know what to do about it if I did");
	    add("One tequila, two tequila, three tequila, floor");
	    add("Some people see things that are and ask, Why? Some people dream of things that never were and ask, Why not? Some people have to go to work and don't have time for all that.");
	    add("The main reason Santa is so jolly is because he knows where all the bad girls live");
	    
	}};	
	
	
	
	final static ArrayList<String> fishArray = new ArrayList<String>() {{ //access by index, so ArrayList
	    add("Таким образом постоянное информационно-пропагандистское");
	    add("Значимость этих проблем настолько очевидна, что сложившаяся структура организации в значительной степени обуславливает создание дальнейших направлений развития.");
	    add("блем настолько очевидна, что реализация намеченных плановых заданий способствует подготовки и реализации направлений прогрессивного развит");
	    add(" также постоянный количественный рост и сфера нашей активности позв и богатый опыт постоянный количественный рост и сфера нашей");
	    add("Не следует, однако забывать, что постоянное информационно-пропагандистское обеспечение нашей деятельности требуют ");
	    add("определения и уточнения соответствующий условий активизации. С другой стороны дальнейшее развитие различных аправлений развития. Задача организации, в особенности ");
	    add("что реализация намеченных плановых заданий позволяет выполнять важные задания по разработке позиций, занимаемых участниками в отношении поставленных задач.");
	    add("Не следует, однако забывать, что сложившаяся структура организации в значительной степени обуславливает создание позиций, занимаемых участниками в отношении поставленных задач.");
	    add("ное информационно-пропагандистское обеспечение нашей деятельности представляет собой интересный экспичественный рост и сфера нашей активности требуют определения и ");
	    add("I think people should be allowed to do anything they want. We haven't tried that for a while. Maybe this time it'll work");
	    add("ками в отношении поставленных задач. Значимость этих проблем настолько очевидна, что реализация намеченных плановых з");
	}};	
	
		
	
	public static List<Jokes> getListJokesEntity() {

		int limit = arrayJokes.size();
		
		List<Jokes> list = new ArrayList<Jokes>(limit);
		
		for(long i=0;i<limit;i++)
		list.add(
		Jokes.builder()
		.id(UUIDs.timeBased())
		.count(i)
		.createdd(new Date())
		.jokes(arrayJokes.get((int) i))
		.deleted(false)
		.build()
		);
		
		return list;
	}


	public static AddValuesToDiary getListDiaryEntity() {
			

		//before generate of calendar
		return AddValuesToDiary.builder()
									.Java(8)
									.Electro(18)
									.English(23)
									.German(5)
									.XaXa(17)
									.InterviewElectro(8)
									.InterviewJava(14)
									.Sport(4)
									.University(2)
									.build();
									
	}


	public static List<User> getListUser(BCryptPasswordEncoder passwordEncoder) {
	
		List<User> list = new ArrayList<User>() {{ //access by index, so ArrayList
			add(User.builder().username("User").password("123").build());
			add(User.builder().username("Banan").password("1234").build());
			add(User.builder().username("nonBanan").password("12345").build());
		}};	

		return list;
	}


	public static Goals generateGoalsEntity() throws IOException {
		return		Goals.builder()
					.id(UUIDs.timeBased())
					.createdd(new Date())
					.editedd(new Date())
					.title("Treefish blue danio ")
					.description(fishArray.get(0))
					.image(getImage(1))
					.build();

	
	}

    private static ByteBuffer getImage(int id) throws IOException {

    		File folderInput = new File("src/main/resources/static/images/initializeData/Goals/image"+id+".jpg");
        BufferedImage folderImage = ImageIO.read(folderInput);
        byte[] byteArray = toByteArrayAutoClosable(folderImage, "png");
  		return ByteBuffer.wrap(byteArray);

	}
    
  
	private static byte[] toByteArrayAutoClosable(BufferedImage image, String type) throws IOException {
        try (ByteArrayOutputStream out = new ByteArrayOutputStream()){
            ImageIO.write(image, type, out);
            return out.toByteArray();
        }
    }


	public static Gallery generateGalleryEntity() throws IOException {
		return	Gallery.builder()
					.id(UUIDs.timeBased())
					.reachedd(new Date())
					.title("Treefish blue danio ")
					.description(fishArray.get(3))
					.image(getImage(2))
					.build();
	}


	public static List<Notes> getListNotesEntity() {
	
		int limit = arrayJokes.size();
		
		List<Notes> list = new ArrayList<Notes>(limit);
		
		for(long i=0;i<limit;i++)
		list.add(
		Notes.builder()
		.id(UUIDs.timeBased())
		.createdd(new Date())
		.note(arrayJokes.get((int) i))
		.delete(false)
		.build()
		);
		
		return list;
	}
    
}
