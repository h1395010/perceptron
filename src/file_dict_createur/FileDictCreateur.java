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

public class FileDictCreateur 
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
			iterateDirectory( directory , globo_dict_fixed, training_fileDict ); //that training dict doesn't need to be here
			// ^ NEEDS REFACTORING!!!
			
			if(cycle == 3)
				globo_dict_fixed = true;
		}
		
		
		//cycle through again to populate the file dicts
		for(int cycle = 0; cycle <= 3; cycle++)
		{
			String general_data_partition = PATH + categories[cycle];
					
			File directory = new File( general_data_partition );
			iterateDirectory( directory , globo_dict_fixed, training_fileDict );
					
		}
		
		//get feature vectors for the training data
		perceptron_data_struc_generateur( GLOBO_DICT, training_fileDict, training_perceptron_input );


		
		
		
		//cycle through all categories once to populate the global dict
		for(int cycle = 0; cycle <= 3; cycle++)
		{
			//get the test data
			String test_path = "/home/matthias/Workbench/SUTD/ISTD_50.570/assignments/practice_data/data/test";
			File test_dict = new File( test_path + categories[cycle]);
			iterateDirectory( test_dict , globo_dict_fixed, test_fileDict );
		}
		
		//get feature vectors for the TEST data
		perceptron_data_struc_generateur( GLOBO_DICT, test_fileDict, test_perceptron_input );

		
		
		
		for (Map.Entry<File, int[]> entry : test_perceptron_input.entrySet()) 
		{
			System.out.println(entry.getKey() + ", " + Arrays.toString(entry.getValue()));
		}
		
		
		
		///AHHAHAHAHH!!!!!!
		Perceptron.perceptron( GLOBO_DICT, training_perceptron_input, test_perceptron_input );
		
		
		
		
		
	}
	
	
	
	private static void iterateDirectory(File directory, boolean globo_dict_fixed, Map<File, ArrayList<String> > fileDict ) throws IOException 
	{
	    for (File file : directory.listFiles()) 
	    {
	        if (file.isDirectory()) 
	        {
	            iterateDirectory(directory, globo_dict_fixed, fileDict );
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
	    				create_file_dict( file, words, fileDict );
	    			}
	    		}
	        }
	    }
	}
	
	@SuppressWarnings("unchecked")
	public static void create_file_dict( File file, String[] words, Map<File, ArrayList<String> > fileDict ) throws IOException
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




////print statements
//System.out.println( "GLOBO_DICT.size() " + GLOBO_DICT.size() );
//
////print
//System.out.println("perceptron_input.size() " + perceptron_input.size() );
//
////print debugging
//for (Map.Entry<File, int[]> entry : perceptron_input.entrySet()) 
//{
//	System.out.println( "features: " + Arrays.toString(entry.getValue()));
//}













//
//
//
////2d array for features
//int i = 0;
//int[][] feature_matrix = new int[ perceptron_input.size() ][ GLOBO_DICT.size() ];
//String[][] output_label = new String[ perceptron_input.size() ][ 2 ];
//
//for(Entry<File, int[]> entry : perceptron_input.entrySet())
//{
//    int[] container =entry.getValue();
//
//    for(int j = 0; j < 4; j++)
//    {
//    	feature_matrix[i][j] = container[j];
//        
//        output_label[i][1] = entry.getKey().toString(); 
//    }
//    i++;
//}
//
//
////number of instances
// int NUM_INSTANCES = perceptron_input.size();
// 
// System.out.println("size is: " + GLOBO_DICT.size() ); 
//
//
////three variables (features) they enumerate by
////features, xyz, i also do that
//double[] a00 = new double [NUM_INSTANCES];
//double[] a01 = new double [NUM_INSTANCES];
//double[] a02 = new double [NUM_INSTANCES];    
//double[] a03 = new double [NUM_INSTANCES];
//double[] a04 = new double [NUM_INSTANCES];
//double[] a05 = new double [NUM_INSTANCES];
//double[] a06 = new double [NUM_INSTANCES];
//double[] a07 = new double [NUM_INSTANCES];
//double[] a08 = new double [NUM_INSTANCES];
//double[] a09 = new double [NUM_INSTANCES];
//double[] a10 = new double [NUM_INSTANCES];
//double[] a11 = new double [NUM_INSTANCES];
//double[] a12 = new double [NUM_INSTANCES];
//double[] a13 = new double [NUM_INSTANCES];
//double[] a14 = new double [NUM_INSTANCES];
//double[] a15 = new double [NUM_INSTANCES];
//double[] a16 = new double [NUM_INSTANCES];
//double[] a17 = new double [NUM_INSTANCES];
//double[] a18 = new double [NUM_INSTANCES];
//double[] a19 = new double [NUM_INSTANCES];
//double[] a20 = new double [NUM_INSTANCES];
//double[] a21 = new double [NUM_INSTANCES];
//double[] a22 = new double [NUM_INSTANCES];
//double[] a23 = new double [NUM_INSTANCES];
//double[] a24 = new double [NUM_INSTANCES];
//double[] a25 = new double [NUM_INSTANCES];
//double[] a26 = new double [NUM_INSTANCES];
//double[] a27 = new double [NUM_INSTANCES];
//double[] a28 = new double [NUM_INSTANCES];
//double[] a29 = new double [NUM_INSTANCES];
//double[] a30 = new double [NUM_INSTANCES];
//
//
//int[] outputs = new int [NUM_INSTANCES];
//
//for(int g = 0; g < NUM_INSTANCES; g++)
//{
//	a00[g] = feature_matrix[g][ 0];
//    a01[g] = feature_matrix[g][ 1];
//	a02[g] = feature_matrix[g][ 2];
//    a03[g] = feature_matrix[g][ 3];
//    a04[g] = feature_matrix[g][ 4];
//    a05[g] = feature_matrix[g][ 5];
//    a06[g] = feature_matrix[g][ 6];
//    a07[g] = feature_matrix[g][ 7];
//    a08[g] = feature_matrix[g][ 8];
//    a09[g] = feature_matrix[g][ 9];
//    a10[g] = feature_matrix[g][10];
//    a11[g] = feature_matrix[g][11];
//    a12[g] = feature_matrix[g][12];
//    a13[g] = feature_matrix[g][13];
//    a14[g] = feature_matrix[g][14];
//    a15[g] = feature_matrix[g][15];
//    a16[g] = feature_matrix[g][16];
//    a17[g] = feature_matrix[g][17];
//    a18[g] = feature_matrix[g][18];
//    a19[g] = feature_matrix[g][19];
//    a20[g] = feature_matrix[g][20];
//    a21[g] = feature_matrix[g][21];
//    a22[g] = feature_matrix[g][22];
//    a23[g] = feature_matrix[g][23];
//    a24[g] = feature_matrix[g][24];
//    a25[g] = feature_matrix[g][25];
//    a26[g] = feature_matrix[g][26];
//    a27[g] = feature_matrix[g][27];
//    a28[g] = feature_matrix[g][28];
//    a29[g] = feature_matrix[g][29];
//    a30[g] = feature_matrix[g][30];
//    if(output_label[g][1].equals( "/home/matthias/Workbench/SUTD/ISTD_50.570/assignments/practice_data/data/train/atheism/a_0.txt" ) )
//    {
//    	outputs[g] = 1;
//    }
//    else
//    {
//    	outputs[g] = 0;
//    }
//}
//
//
//for(int g = 0; g < NUM_INSTANCES; g++)
//{
//	System.out.print( a00[g] );
//    System.out.print( a01[g] );
//    System.out.print( a02[g] );
//    System.out.print( a03[g] );
//    System.out.print( a04[g] );
//    System.out.print( a05[g] );
//    System.out.print( a06[g] );
//    System.out.print( a07[g] );
//    System.out.print( a08[g] );
//    System.out.print( a09[g] );
//    System.out.print( a10[g] );
//    System.out.print( a11[g] );
//    System.out.print( a12[g] );
//    System.out.print( a13[g] );
//    System.out.print( a14[g] );
//    System.out.print( a15[g] );
//    System.out.print( a16[g] );
//    System.out.print( a17[g] );
//    System.out.print( a18[g] );
//    System.out.print( a19[g] );
//    System.out.print( a20[g] );
//    System.out.print( a21[g] );
//    System.out.print( a22[g] );
//    System.out.print( a23[g] );
//    System.out.print( a24[g] );
//    System.out.print( a25[g] );
//    System.out.print( a26[g] );
//    System.out.print( a27[g] );
//    System.out.print( a28[g] );
//    System.out.print( a29[g] );
//    System.out.print( a30[g] );
//    System.out.print("label: " + outputs[g] );
//    System.out.println();
//
//}







////print it
//for (int ii = 0; ii < perceptron_input.size(); ii++) 
//{
//    for (int jj = 0; jj < GLOBO_DICT.size(); jj++) 
//    {
//        System.out.print(feature_matrix[ii][jj] + " ");
//    }
//    System.out.print("\n");
//    System.out.println("output_label: " + output_label[ii][1]);
//}









