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
										 Map<File, ArrayList<String> > fileDict ) throws IOException
	{	
		
		if (!fileDict.containsKey(file))
		{
			@SuppressWarnings("rawtypes")
			ArrayList document_words = new ArrayList<String>();
			
			String word;
			
			for (int i = 0; i < words.length; i++) 
			{
				word = words[i];
		    
				document_words.add(word);
			}
			fileDict.put(file, document_words);
		}
	}
}











//			//truncate file
//			String label = file.toString()
//							 .replaceAll("/[^/]*$", "")
//							 .replaceAll("/home/matthias/Workbench/SUTD/ISTD_50.570/assignments/practice_data/data/train/", "")
//							 .replaceAll("/home/matthias/Workbench/SUTD/ISTD_50.570/assignments/practice_data/data/test/", "");
