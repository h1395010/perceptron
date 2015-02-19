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

	    		while ((line = br.readLine()) != null) 
	    		{
	    			String[] words = line.split(" ");//those are your words

	    			if(globo_dict_fixed == false)
	    			{
	    				Data_GloboPop.populate_globo_dict( words, GLOBO_DICT );
	    			}
	    			else
	    			{
	    				Data_FileDict.create_file_dict( file, words, fileDict );
	    			}
	    		}
	        }
	    }
	}
}
