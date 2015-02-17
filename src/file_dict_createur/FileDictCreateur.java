package file_dict_createur;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class FileDictCreateur 
{
	static String PATH = "/home/matthias/Workbench/SUTD/ISTD_50.570/assignments/practice_data/data/train";
	
	//the global list of all words across all articles
	static Set<String> GLOBO_DICT = new HashSet<String>();
	
	//is the globo dict full?
	static boolean globo_dict_fixed = false;
	
	// hash map of all the words contained in individual files
	static Map<File, ArrayList<String> > fileDict = new HashMap<>();
	
	
	@SuppressWarnings("rawtypes")
	public static void main(String[] args) throws IOException 
	{
		//each of the diferent categories
		String[] categories = { "/atheism", "/politics", "/science", "/sports"};
		
		//cycle through all categories once to populate the global dict
		for(int cycle = 0; cycle <= 3; cycle++)
		{
			String general_data_partition = PATH + categories[cycle];
			
			File directory = new File( general_data_partition );
			iterateDirectory( directory , globo_dict_fixed);
			
			if(cycle == 3)
				globo_dict_fixed = true;
		}
		
		
		//cycle through again to populate the file dicts
		for(int cycle = 0; cycle <= 3; cycle++)
		{
			String general_data_partition = PATH + categories[cycle];
					
			File directory = new File( general_data_partition );
			iterateDirectory( directory , globo_dict_fixed);
					
		}
		
		
		//print the data struc				
		for (String s : GLOBO_DICT)
			System.out.println( s );
		
		
		//print the output
		for (Map.Entry entry : fileDict.entrySet()) 
		{
		    System.out.println(entry.getKey() + ", " + entry.getValue());
		}
	}
	
	
	
	private static void iterateDirectory(File directory, boolean globo_dict_fixed) throws IOException 
	{
	    for (File file : directory.listFiles()) 
	    {
	        if (file.isDirectory()) 
	        {
	            iterateDirectory(directory, globo_dict_fixed);
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
	    				populate_globo_dict( words );
	    			}
	    			else
	    			{
	    				create_file_dict( file, words );
	    			}
	    		}
	        }
	    }
	}
	
	@SuppressWarnings("unchecked")
	public static void create_file_dict( File file, String[] words ) throws IOException
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
	
	public static void populate_globo_dict( String[] words ) throws IOException
	{
		String word;
		
		for (int i = 0; i < words.length; i++) 
		{
			word = words[i];
			if (!GLOBO_DICT.contains(word))
			{
				GLOBO_DICT.add(word);
			}
		}	
	}
	
	
	
	
}













