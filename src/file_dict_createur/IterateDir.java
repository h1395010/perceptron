package file_dict_createur;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class IterateDir 
{
	public static void iterateDirectory( File directory, 
									     Map<String, ArrayList<String> > fileDict,
									     Set<String> GLOBO_DICT) throws IOException 
	{
		ArrayList<String> document_words_on_line = new ArrayList<String>();
		
		String directory_key = null;
		
	    for (File file : directory.listFiles()) 
	    {  
    		String line; 
    		BufferedReader br = new BufferedReader(new FileReader( file ));
    		
    		
    		//ArrayList<String> document_words_on_line = new ArrayList<String>();
    		
    		while((line = br.readLine()) != null) 
    		{
    			String[] words = line.split(" ");//those are your words

    				String word;
    				
    				for (int i = 0; i < words.length; i++) 
    				{
    					word = words[i];
    			    
    					document_words_on_line.add(word);
    				}
    				
    			}
    			
    		
    		//does it make sense for the key to be here?
    		directory_key = file.getPath()
	                   				   .toString()
	                                   .replaceAll("/[^/]*$", "")
                                       .replaceAll("/home/matthias/Workbench/SUTD/ISTD_50.570/assignments/practice_data/data/train/", "")
                                       .replaceAll("/home/matthias/Workbench/SUTD/ISTD_50.570/assignments/practice_data/data/test/", "");

	    }
		fileDict.put( directory_key , document_words_on_line );
	    
	}
}