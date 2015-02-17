package file_dict_createur;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
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
	
	//input to perceptron. final struc.
	static Map<File, int[] > perceptron_input = new HashMap<>();
	
	
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
		
		
		
		perceptron_data_struc_generateur( GLOBO_DICT, fileDict, perceptron_input );
		
		
		//print the data struc				
		//for (String s : GLOBO_DICT)
			//System.out.println( s );
		
		
		//print the output
		for (Map.Entry<File, int[]> entry : perceptron_input.entrySet()) 
		{
			System.out.println(entry.getKey() + ", " + Arrays.toString(entry.getValue()));
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
	
	public static void perceptron_data_struc_generateur(Set<String> GLOBO_DICT, 
													    Map<File, ArrayList<String> > fileDict,
													    Map<File, int[] > perceptron_input)
	{
		//create a new entry in the array list 'perceptron_input'
		//with the key as the file name from fileDict
			//create a new array which is the length of GLOBO_DICT
			//iterate through the indicies of GLOBO_DICT
				//for all words in globo dict, if that word appears in fileDict,
				//increment the perceptron_input index that corresponds to that
				//word in GLOBO_DICT by the number of times that word appears in fileDict
		
		//so i can get the index later
		List<String> GLOBO_DICT_list = new ArrayList<>(GLOBO_DICT);
		
		for (Map.Entry<File, ArrayList<String>> entry : fileDict.entrySet()) 
		{
		    int[] cross_czech = new int[GLOBO_DICT_list.size()];
		    //initialize to zero
		    Arrays.fill(cross_czech, 0);

		    for (String s : GLOBO_DICT_list)
		    {
		    	
		        for(String st : entry.getValue()) 
		        {
		        	if( st.equals(s) )
		        	{
		        		cross_czech[ GLOBO_DICT_list.indexOf( s ) ] = cross_czech[ GLOBO_DICT_list.indexOf( s ) ] +1;
		        	}
		        }
		    }
		    perceptron_input.put( entry.getKey() , cross_czech);	
		}
	}
}











