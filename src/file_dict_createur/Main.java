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
import java.util.Map.Entry;
import java.util.Set;

public class Main 
{
	static String PATH = "/home/matthias/Workbench/SUTD/ISTD_50.570/assignments/practice_data/data/train";
	
	//the global list of all words across all articles
	static Set<String> GLOBO_DICT = new HashSet<String>();
	
	//is the globo dict full?
	static boolean globo_dict_fixed = false;
	
	// hash map of all the words contained in individual files
	static Map<File, ArrayList<String> > training_fileDict = new HashMap<>();
	
	static Map<File, ArrayList<String> > test_fileDict = new HashMap<>();
	
	//input to perceptron. final struc.
	static Map<File, int[] > training_perceptron_input = new HashMap<>();
	
	static Map<File, int[] > test_perceptron_input = new HashMap<>();
	
	
	public static void main(String[] args) throws IOException 
	{
		//each of the diferent categories
		String[] categories = { "/atheism", "/politics", "/science", "/sports"};
		
		//cycle through all categories once to populate the global dict
		for(int cycle = 0; cycle <= 3; cycle++)
		{
			String general_data_partition = PATH + categories[cycle];
			
			File directory = new File( general_data_partition );
			Data_IterateDir.iterateDirectory( directory , globo_dict_fixed, training_fileDict, GLOBO_DICT  ); //that training dict doesn't need to be here
			// ^ NEEDS REFACTORING!!!
			
			if(cycle == 3)
				globo_dict_fixed = true;
		}
		
		
		//cycle through again to populate the file dicts
		for(int cycle = 0; cycle <= 3; cycle++)
		{
			String general_data_partition = PATH + categories[cycle];
					
			File directory = new File( general_data_partition );
			Data_IterateDir.iterateDirectory( directory , globo_dict_fixed, training_fileDict, GLOBO_DICT  );
					
		}
		
		//get feature vectors for the training data
		Data_PrcptrnStrucGen.perceptron_data_struc_generateur( GLOBO_DICT, training_fileDict, training_perceptron_input );


		
		
		
		//cycle through all categories once to populate the global dict
		for(int cycle = 0; cycle <= 3; cycle++)
		{
			//get the test data
			String test_path = "/home/matthias/Workbench/SUTD/ISTD_50.570/assignments/practice_data/data/test";
			File test_dict = new File( test_path + categories[cycle]);
			Data_IterateDir.iterateDirectory( test_dict , globo_dict_fixed, test_fileDict, GLOBO_DICT  );
		}
		
		//get feature vectors for the TEST data
		Data_PrcptrnStrucGen.perceptron_data_struc_generateur( GLOBO_DICT, test_fileDict, test_perceptron_input );

		
		
		
		for (Map.Entry<File, int[]> entry : test_perceptron_input.entrySet()) 
		{
			System.out.println(entry.getKey() + ", " + Arrays.toString(entry.getValue()));
		}
		
		
		
		///AHHAHAHAHH!!!!!!
		Perceptron.perceptron( GLOBO_DICT, training_perceptron_input, test_perceptron_input );
			
	}
	
}