package file_dict_createur;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;


//this is currently not being used. 
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
public class Data_FileDict 
{

	@SuppressWarnings("unchecked")
	public static void create_file_dict( File file, 
										 String[] words, 
										 String line,
										 Map<String, ArrayList<String> > fileDict ) throws IOException
	{	
		
		String key_file_loke = file.getPath()
								   .toString()
								   .replaceAll("/[^/]*$", "")
							       .replaceAll("/home/matthias/Workbench/SUTD/ISTD_50.570/assignments/practice_data/data/train/", "")
							       .replaceAll("/home/matthias/Workbench/SUTD/ISTD_50.570/assignments/practice_data/data/test/", "");
		//this should be something about the file being finished
		//not just storing the key, because this way it only stores the first line
		//
		//if ( ! fileDict.containsKey( key_file_loke ) )
		while(line != null)
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
