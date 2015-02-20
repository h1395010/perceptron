package file_dict_createur;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Set;

public class Data_IterateDir 
{
	@SuppressWarnings("unchecked")
	public static void iterateDirectory( File directory, 
									     boolean globo_dict_fixed, 
									     Map<String, ArrayList<String> > fileDict,
									     Set<String> GLOBO_DICT) throws IOException 
	{
	    for (File file : directory.listFiles()) 
	    {
	        if (file.isDirectory()) 
	        {
	            iterateDirectory(directory, globo_dict_fixed, fileDict, GLOBO_DICT );
	        } 
	        else 
	        {   
	    		String line; 
	    		BufferedReader br = new BufferedReader(new FileReader( file ));
	    		
	    		
	    		ArrayList<String> document_words_on_line = new ArrayList<String>();
	    		
	    		while((line = br.readLine()) != null) 
	    		{
	    			String[] words = line.split(" ");//those are your words

	    			if(globo_dict_fixed == false)
	    			{
	    				Data_GloboPop.populate_globo_dict( words, GLOBO_DICT );
	    			}
	    			else
	    			{
	    				//this should be something about the file being finished
	    				//not just storing the key, because this way it only stores the first line
	    				//
	    				//if it doesn't contain the key, it needs the key label, that's the first step
	    				//
	    				//make an array list of all the words
	    				String word;
	    				
	    				for (int i = 0; i < words.length; i++) 
	    				{
	    					word = words[i];
	    			    
	    					document_words_on_line.add(word);
	    				}
	    				
	    			}
	    			
	    		}
	    		//does it make sense for the key to be here?
				String key_file_loke = file.getPath()
		                   				   .toString()
		                                   .replaceAll("/[^/]*$", "")
	                                       .replaceAll("/home/matthias/Workbench/SUTD/ISTD_50.570/assignments/practice_data/data/train/", "")
	                                       .replaceAll("/home/matthias/Workbench/SUTD/ISTD_50.570/assignments/practice_data/data/test/", "");
	    		//this should be here, meaning that the line is null and the file is over
				//if ( ! fileDict.containsKey( key_file_loke ) )
				fileDict.put( key_file_loke , document_words_on_line );
	        }
	    }
	}
}
