package file_dict_createur;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class Data_FileDict 
{

	@SuppressWarnings("unchecked")
	public static void create_file_dict( File file, 
										 String[] words, 
										 Map<String, ArrayList<String> > fileDict ) throws IOException
	{	
		
		String key_file_loke = file.getPath()
								   .toString()
								   .replaceAll("/[^/]*$", "")
							       .replaceAll("/home/matthias/Workbench/SUTD/ISTD_50.570/assignments/practice_data/data/train/", "")
							       .replaceAll("/home/matthias/Workbench/SUTD/ISTD_50.570/assignments/practice_data/data/test/", "");
		
		if ( ! fileDict.containsKey( key_file_loke ) )
		{
			@SuppressWarnings("rawtypes")
			ArrayList document_words = new ArrayList<String>();
			
			String word;
			
			for (int i = 0; i < words.length; i++) 
			{
				word = words[i];
		    
				document_words.add(word);
			}
			
			fileDict.put( key_file_loke , document_words);
		}
	}
}











//			//truncate file
//			String label = file.toString()
//							 .replaceAll("/[^/]*$", "")
//							 .replaceAll("/home/matthias/Workbench/SUTD/ISTD_50.570/assignments/practice_data/data/train/", "")
//							 .replaceAll("/home/matthias/Workbench/SUTD/ISTD_50.570/assignments/practice_data/data/test/", "");
