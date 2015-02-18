package file_dict_createur;

/**
 The Perceptron Algorithm
 By Dr Noureddin Sadawi
 Please my youtube videos on perceptron for things to make sense!
 
 Code adapted from:
 https://github.com/RichardKnop/ansi-c-perceptron
*/  

import java.util.*;
import java.util.Map.Entry;
import java.io.*;
import java.text.*;
import java.math.*;

class Perceptron
{
  static int MAX_ITER = 100;
  static double LEARNING_RATE = 0.1;           
  //static int NUM_INSTANCES = 100;
  static int theta = 0;  
  
  
  public static void perceptron( Set<String> GLOBO_DICT, Map<File, int[] > training_perceptron_input, Map<File, int[] > test_perceptron_input)
  {
	  
		 //number of features, number of x, y, z
		 int size_of_globo_dict = GLOBO_DICT.size();
		 
		 //number of instances
		 int NUM_INSTANCES = training_perceptron_input.size();
	  
	  
		    //three variables (features) they enumerate by
		    //features, xyz, i also do that
			double[] a00 = new double [NUM_INSTANCES];
		    double[] a01 = new double [NUM_INSTANCES];
			double[] a02 = new double [NUM_INSTANCES];    
		    double[] a03 = new double [NUM_INSTANCES];
		    double[] a04 = new double [NUM_INSTANCES];
		    double[] a05 = new double [NUM_INSTANCES];
		    double[] a06 = new double [NUM_INSTANCES];
		    double[] a07 = new double [NUM_INSTANCES];
		    double[] a08 = new double [NUM_INSTANCES];
		    double[] a09 = new double [NUM_INSTANCES];
		    double[] a10 = new double [NUM_INSTANCES];
		    double[] a11 = new double [NUM_INSTANCES];
		    double[] a12 = new double [NUM_INSTANCES];
		    double[] a13 = new double [NUM_INSTANCES];
		    double[] a14 = new double [NUM_INSTANCES];
		    double[] a15 = new double [NUM_INSTANCES];
		    double[] a16 = new double [NUM_INSTANCES];
		    double[] a17 = new double [NUM_INSTANCES];
		    double[] a18 = new double [NUM_INSTANCES];
		    double[] a19 = new double [NUM_INSTANCES];
		    double[] a20 = new double [NUM_INSTANCES];
		    double[] a21 = new double [NUM_INSTANCES];
		    double[] a22 = new double [NUM_INSTANCES];
		    double[] a23 = new double [NUM_INSTANCES];
		    double[] a24 = new double [NUM_INSTANCES];
		    double[] a25 = new double [NUM_INSTANCES];
		    double[] a26 = new double [NUM_INSTANCES];
		    double[] a27 = new double [NUM_INSTANCES];
		    double[] a28 = new double [NUM_INSTANCES];
		    double[] a29 = new double [NUM_INSTANCES];
		    double[] a30 = new double [NUM_INSTANCES];
    
    
	//2d array for features
	int x = 0;
	int[][] feature_matrix = new int[ training_perceptron_input.size() ][ GLOBO_DICT.size() ];
	String[][] output_label = new String[ training_perceptron_input.size() ][ 2 ];

	for(Entry<File, int[]> entry : training_perceptron_input.entrySet())
	{
		int[] container =entry.getValue();

		for(int j = 0; j < 4; j++)
		{
			feature_matrix[x][j] = container[j];
			        
			output_label[x][1] = entry.getKey().toString(); 
		}
			x++;
	}
    
    
    
    int[] outputs = new int [NUM_INSTANCES];
    
    for(int g = 0; g < NUM_INSTANCES; g++)
    {
    	a00[g] = feature_matrix[g][ 0];
        a01[g] = feature_matrix[g][ 1];
    	a02[g] = feature_matrix[g][ 2];
        a03[g] = feature_matrix[g][ 3];
        a04[g] = feature_matrix[g][ 4];
        a05[g] = feature_matrix[g][ 5];
        a06[g] = feature_matrix[g][ 6];
        a07[g] = feature_matrix[g][ 7];
        a08[g] = feature_matrix[g][ 8];
        a09[g] = feature_matrix[g][ 9];
        a10[g] = feature_matrix[g][10];
        a11[g] = feature_matrix[g][11];
        a12[g] = feature_matrix[g][12];
        a13[g] = feature_matrix[g][13];
        a14[g] = feature_matrix[g][14];
        a15[g] = feature_matrix[g][15];
        a16[g] = feature_matrix[g][16];
        a17[g] = feature_matrix[g][17];
        a18[g] = feature_matrix[g][18];
        a19[g] = feature_matrix[g][19];
        a20[g] = feature_matrix[g][20];
        a21[g] = feature_matrix[g][21];
        a22[g] = feature_matrix[g][22];
        a23[g] = feature_matrix[g][23];
        a24[g] = feature_matrix[g][24];
        a25[g] = feature_matrix[g][25];
        a26[g] = feature_matrix[g][26];
        a27[g] = feature_matrix[g][27];
        a28[g] = feature_matrix[g][28];
        a29[g] = feature_matrix[g][29];
        a30[g] = feature_matrix[g][30];
        if(output_label[g][1].equals( "/home/matthias/Workbench/SUTD/ISTD_50.570/assignments/practice_data/data/train/atheism/a_0.txt" ) )
        {
        	outputs[g] = 1;
        }
        else
        {
        	outputs[g] = 0;
        }
    }
    
    
    
//    //fifty random points of class 1
//    for(int i = 0; i < NUM_INSTANCES/2; i++){
//        x[i] = randomNumber(5 , 10);
//        y[i] = randomNumber(4 , 8); 
//        z[i] = randomNumber(2 , 9);        
//        outputs[i] = 1;         
//       System.out.println(x[i]+"\t"+y[i]+"\t"+z[i]+"\t"+outputs[i]);
//    }
//    
//    //fifty random points of class 0
//    for(int i = 50; i < NUM_INSTANCES; i++){
//        x[i] = randomNumber(-1 , 3);
//        y[i] = randomNumber(-4 , 2);   
//        z[i] = randomNumber(-3 , 5);       
//        outputs[i] = 0;        
//       System.out.println(x[i]+"\t"+y[i]+"\t"+z[i]+"\t"+outputs[i]);
//    }
    
    
    
    
    double[] weights = new double[ GLOBO_DICT.size() + 1];// 32 for input variables and one for bias
    double localError, globalError;
    int i, p, iteration, output;
    
    weights[ 0] = randomNumber(0,1);// w1
    weights[ 1] = randomNumber(0,1);// w2
    weights[ 2] = randomNumber(0,1);// w3
    weights[ 3] = randomNumber(0,1);// w4
    weights[ 4] = randomNumber(0,1);// w5
    weights[ 5] = randomNumber(0,1);// w6
    weights[ 6] = randomNumber(0,1);// w7
    weights[ 7] = randomNumber(0,1);// w8
    weights[ 8] = randomNumber(0,1);// w9
    weights[ 9] = randomNumber(0,1);// w10
    weights[10] = randomNumber(0,1);// w11
    weights[11] = randomNumber(0,1);// w12
    weights[12] = randomNumber(0,1);// w13
    weights[13] = randomNumber(0,1);// w14
    weights[14] = randomNumber(0,1);// w15
    weights[15] = randomNumber(0,1);// w16
    weights[16] = randomNumber(0,1);// w17
    weights[17] = randomNumber(0,1);// w18
    weights[18] = randomNumber(0,1);// w19
    weights[19] = randomNumber(0,1);// w20
    weights[20] = randomNumber(0,1);// w21
    weights[21] = randomNumber(0,1);// w22
    weights[22] = randomNumber(0,1);// w23
    weights[23] = randomNumber(0,1);// w24
    weights[24] = randomNumber(0,1);// w25
    weights[25] = randomNumber(0,1);// w26
    weights[26] = randomNumber(0,1);// w27
    weights[27] = randomNumber(0,1);// w28
    weights[28] = randomNumber(0,1);// w29
    weights[29] = randomNumber(0,1);// w30
    weights[30] = randomNumber(0,1);// w31
    weights[31] = randomNumber(0,1);// this is the bias
    
    
         
    iteration = 0;
    do {
        iteration++;
        globalError = 0;
        //loop through all instances (complete one epoch)
        for (p = 0; p < NUM_INSTANCES; p++) 
        {
            // calculate predicted class
            output = calculateOutput(theta,
            						 weights, 
            						 a00[p], 
            						 a01[p], 
            						 a02[p],
            						 a03[p],
            						 a04[p],
            						 a05[p],
            						 a06[p],
            						 a07[p],
            						 a08[p],
            						 a09[p],
            						 a10[p],
            						 a11[p],
            						 a12[p],
            						 a13[p],
            						 a14[p],
            						 a15[p],
            						 a16[p],
            						 a17[p],
            						 a18[p],
            						 a19[p],
            						 a20[p],
            						 a21[p],
            						 a22[p],
            						 a23[p],
            						 a24[p],
            						 a25[p],
            						 a26[p],
            						 a27[p],
            						 a28[p],
            						 a29[p],
            						 a30[p]);
	    // difference between predicted and actual class values
            localError = outputs[p] - output;
            //update weights and bias
            weights[ 0] += LEARNING_RATE * localError * a00[p];
            weights[ 1] += LEARNING_RATE * localError * a01[p];
            weights[ 2] += LEARNING_RATE * localError * a02[p];
            weights[ 3] += LEARNING_RATE * localError * a03[p];
            weights[ 4] += LEARNING_RATE * localError * a04[p];
            weights[ 5] += LEARNING_RATE * localError * a05[p];
            weights[ 6] += LEARNING_RATE * localError * a06[p];
            weights[ 7] += LEARNING_RATE * localError * a07[p];
            weights[ 8] += LEARNING_RATE * localError * a08[p];
            weights[ 9] += LEARNING_RATE * localError * a09[p];
            weights[10] += LEARNING_RATE * localError * a10[p];
            weights[11] += LEARNING_RATE * localError * a11[p];
            weights[12] += LEARNING_RATE * localError * a12[p];
            weights[13] += LEARNING_RATE * localError * a13[p];
            weights[14] += LEARNING_RATE * localError * a14[p];
            weights[15] += LEARNING_RATE * localError * a15[p];
            weights[16] += LEARNING_RATE * localError * a16[p];
            weights[17] += LEARNING_RATE * localError * a17[p];
            weights[18] += LEARNING_RATE * localError * a18[p];
            weights[19] += LEARNING_RATE * localError * a19[p];
            weights[20] += LEARNING_RATE * localError * a20[p];
            weights[21] += LEARNING_RATE * localError * a21[p];
            weights[22] += LEARNING_RATE * localError * a22[p];
            weights[23] += LEARNING_RATE * localError * a23[p];
            weights[24] += LEARNING_RATE * localError * a24[p];
            weights[25] += LEARNING_RATE * localError * a25[p];
            weights[26] += LEARNING_RATE * localError * a26[p];
            weights[27] += LEARNING_RATE * localError * a27[p];
            weights[28] += LEARNING_RATE * localError * a28[p];
            weights[29] += LEARNING_RATE * localError * a29[p];
            weights[30] += LEARNING_RATE * localError * a30[p];
            weights[31] += LEARNING_RATE * localError;
            
            
            
            //summation of squared error (error value for all instances)
            globalError += (localError*localError);
        }

     /* Root Mean Squared Error */
     System.out.println("Iteration "+iteration+" : RMSE = "+Math.sqrt(globalError/NUM_INSTANCES));
    } 
    while (globalError != 0 && iteration <= MAX_ITER);
    
    System.out.println("\n=======\nDecision boundary equation:");
    
    System.out.println(
    		 " a00 *" + 
		     weights[ 0] + 
		     " a01 *" + 
		     weights[ 1] + 
		     " a02 *" + 
		     weights[ 2] + 
		     " a03 *" + 
		     weights[ 3] + 
		     " a04 *" + 
		     weights[ 4] + 
		     " a05 *" + 
		     weights[ 5] + 
		     " a06 *" + 
		     weights[ 6] + 
		     " a07 *" + 
		     weights[ 7] + 
		     " a08 *" + 
		     weights[ 8] + 
		     " a09 *" + 
		     weights[ 9] + 
		     " a10 *" + 
		     weights[10] + 
		     " a11 *" + 
		     weights[11] + 
		     " a12 *" + 
		     weights[12] + 
		     " a13 *" + 
		     weights[13] + 
		     " a14 *" + 
		     weights[14] + 
		     " a15 *" + 
		     weights[15] + 
		     " a16 *" + 
		     weights[16] + 
		     " a17 *" + 
		     weights[17] + 
		     " a18 *" +
		     weights[18] + 
		     " a19 *" + 
		     weights[19] + 
		     " a20 *" + 
		     weights[20] + 
		     " a21 *" + 
		     weights[21] + 
		     " a22 *" + 
		     weights[22] + 
		     " a23 *" + 
		     weights[23] + 
		     " a24 *" + 
		     weights[24] + 
		     " a25 *" +
		     weights[25] + 
		     " a26 *" + 
		     weights[26] + 
		     " a27 *" + 
		     weights[27] + 
		     " a28 *" + 
		     weights[28] + 
		     " a29 *" + 
		     weights[29] + 
		     " a30 *" +
		     weights[30] +
		     " bias: " +
		     weights[31]);
    
    //generate 10 new random points and check their classes
    //notice the range of -10 and 10 means the new point could be of class 1 or 0
    //-10 to 10 covers all the ranges we used in generating the 50 classes of 1's and 0's above
    //for(int j = 0; j < 10; j++){
      //double x1 = randomNumber(-10 , 10);
      //double y1 = randomNumber(-10 , 10);   
      //double z1 = randomNumber(-10 , 10); 
      
      
      
  	//2d array for features
  	int x_TEST = 0;
  	int[][] feature_matrix_TEST = new int[ test_perceptron_input.size() ][ GLOBO_DICT.size() ];

  	for(Entry<File, int[]> entry : test_perceptron_input.entrySet())
  	{
  		int[] container =entry.getValue();

  		for(int jj = 0; jj < 4; jj++)
  		{
  			feature_matrix_TEST[x_TEST][jj] = container[jj];
  			        
  		}
  			x_TEST++;
  	}
      
      
  	
    //three variables (features) they enumerate by
    //features, xyz, i also do that
	double[] z00 = new double [NUM_INSTANCES];
    double[] z01 = new double [NUM_INSTANCES];
	double[] z02 = new double [NUM_INSTANCES];    
    double[] z03 = new double [NUM_INSTANCES];
    double[] z04 = new double [NUM_INSTANCES];
    double[] z05 = new double [NUM_INSTANCES];
    double[] z06 = new double [NUM_INSTANCES];
    double[] z07 = new double [NUM_INSTANCES];
    double[] z08 = new double [NUM_INSTANCES];
    double[] z09 = new double [NUM_INSTANCES];
    double[] z10 = new double [NUM_INSTANCES];
    double[] z11 = new double [NUM_INSTANCES];
    double[] z12 = new double [NUM_INSTANCES];
    double[] z13 = new double [NUM_INSTANCES];
    double[] z14 = new double [NUM_INSTANCES];
    double[] z15 = new double [NUM_INSTANCES];
    double[] z16 = new double [NUM_INSTANCES];
    double[] z17 = new double [NUM_INSTANCES];
    double[] z18 = new double [NUM_INSTANCES];
    double[] z19 = new double [NUM_INSTANCES];
    double[] z20 = new double [NUM_INSTANCES];
    double[] z21 = new double [NUM_INSTANCES];
    double[] z22 = new double [NUM_INSTANCES];
    double[] z23 = new double [NUM_INSTANCES];
    double[] z24 = new double [NUM_INSTANCES];
    double[] z25 = new double [NUM_INSTANCES];
    double[] z26 = new double [NUM_INSTANCES];
    double[] z27 = new double [NUM_INSTANCES];
    double[] z28 = new double [NUM_INSTANCES];
    double[] z29 = new double [NUM_INSTANCES];
    double[] z30 = new double [NUM_INSTANCES];
  	
  	
  	
     
     for(int g = 0; g < NUM_INSTANCES; g++)
     {
     	 z00[g] = feature_matrix_TEST[g][ 0];
         z01[g] = feature_matrix_TEST[g][ 1];
     	 z02[g] = feature_matrix_TEST[g][ 2];
         z03[g] = feature_matrix_TEST[g][ 3];
         z04[g] = feature_matrix_TEST[g][ 4];
         z05[g] = feature_matrix_TEST[g][ 5];
         z06[g] = feature_matrix_TEST[g][ 6];
         z07[g] = feature_matrix_TEST[g][ 7];
         z08[g] = feature_matrix_TEST[g][ 8];
         z09[g] = feature_matrix_TEST[g][ 9];
         z10[g] = feature_matrix_TEST[g][10];
         z11[g] = feature_matrix_TEST[g][11];
         z12[g] = feature_matrix_TEST[g][12];
         z13[g] = feature_matrix_TEST[g][13];
         z14[g] = feature_matrix_TEST[g][14];
         z15[g] = feature_matrix_TEST[g][15];
         z16[g] = feature_matrix_TEST[g][16];
         z17[g] = feature_matrix_TEST[g][17];
         z18[g] = feature_matrix_TEST[g][18];
         z19[g] = feature_matrix_TEST[g][19];
         z20[g] = feature_matrix_TEST[g][20];
         z21[g] = feature_matrix_TEST[g][21];
         z22[g] = feature_matrix_TEST[g][22];
         z23[g] = feature_matrix_TEST[g][23];
         z24[g] = feature_matrix_TEST[g][24];
         z25[g] = feature_matrix_TEST[g][25];
         z26[g] = feature_matrix_TEST[g][26];
         z27[g] = feature_matrix_TEST[g][27];
         z28[g] = feature_matrix_TEST[g][28];
         z29[g] = feature_matrix_TEST[g][29];
         z30[g] = feature_matrix_TEST[g][30];
     }
     
     
      
      
      int output_TEST;
      // calculate predicted class TEST
      output_TEST = calculateOutput(theta,
      						 weights, 
      						 z00[2], 
      						 z01[2], 
      						 z02[2],
      						 z03[2],
      						 z04[2],
      						 z05[2],
      						 z06[2],
      						 z07[2],
      						 z08[2],
      						 z09[2],
      						 z10[2],
      						 z11[2],
      						 z12[2],
      						 z13[2],
      						 z14[2],
      						 z15[2],
      						 z16[2],
      						 z17[2],
      						 z18[2],
      						 z19[2],
      						 z20[2],
      						 z21[2],
      						 z22[2],
      						 z23[2],
      						 z24[2],
      						 z25[2],
      						 z26[2],
      						 z27[2],
      						 z28[2],
      						 z29[2],
      						 z30[2]);
      
      
      
      System.out.println("\n=======\nTEST Point:");
      
      
      
      System.out.println(
				         "z00[0]:" + z00[0] + 
				         "z01[0]:" + z01[0] +  
				         "z02[0]:" + z02[0] + 
				         "z03[0]:" + z03[0] + 
				         "z04[0]:" + z04[0] + 
				         "z05[0]:" + z05[0] + 
				         "z06[0]:" + z06[0] + 
				         "z07[0]:" + z07[0] + 
				         "z08[0]:" + z08[0] + 
				         "z09[0]:" + z09[0] + 
				         "z10[0]:" + z10[0] + 
				         "z11[0]:" + z11[0] + 
				         "z12[0]:" + z12[0] + 
				         "z13[0]:" + z13[0] + 
				         "z14[0]:" + z14[0] + 
				         "z15[0]:" + z15[0] + 
				         "z16[0]:" + z16[0] + 
				         "z17[0]:" + z17[0] + 
				         "z18[0]:" + z18[0] + 
				         "z19[0]:" + z19[0] + 
				         "z20[0]:" + z20[0] + 
				         "z21[0]:" + z21[0] + 
				         "z22[0]:" + z22[0] + 
				         "z23[0]:" + z23[0] + 
				         "z24[0]:" + z24[0] + 
				         "z25[0]:" + z25[0] + 
				         "z26[0]:" + z26[0] + 
				         "z27[0]:" + z27[0] + 
				         "z28[0]:" + z28[0] + 
				         "z29[0]:" + z29[0] + 
				         "z30[0]:" + z30[0]
				        );
      
      
      System.out.println("class = "+output_TEST);
    }
  //end main  
  
 /**
  * returns a random double value within a given range
  * @param min the minimum value of the required range (int)
  * @param max the maximum value of the required range (int)
  * @return a random double value between min and max
  */ 
 public static double randomNumber(int min , int max) {
     DecimalFormat df = new DecimalFormat("#.####");
     double d = min + Math.random() * (max - min);
     String s = df.format(d);
     double x = Double.parseDouble(s);
     return x;
 }

 /**
  * returns either 1 or 0 using a threshold function
  * theta is 0range
  * @param theta an integer value for the threshold
  * @param weights[] the array of weights
  * @param x the x input value
  * @param y the y input value
  * @param z the z input value
  * @return 1 or 0
  */ 
 static int calculateOutput(int theta, 
		 					double weights[], 
		 					double a00, 
		 					double a01,
		 					double a02,
		 					double a03,
		 					double a04,
		 					double a05,
		 					double a06,
		 					double a07,
		 					double a08,
		 					double a09,
		 					double a10,
		 					double a11,
		 					double a12,
		 					double a13,
		 					double a14,
		 					double a15,
		 					double a16,
		 					double a17,
		 					double a18,
		 					double a19,
		 					double a20,
		 					double a21,
		 					double a22,
		 					double a23,
		 					double a24,
		 					double a25,
		 					double a26,
		 					double a27,
		 					double a28,
		 					double a29,
		 					double a30)
 {
    double sum = a00 * 
    		     weights[ 0] + 
    		     a01 * 
    		     weights[ 1] + 
    		     a02 * 
    		     weights[ 2] + 
    		     a03 * 
    		     weights[ 3] + 
    		     a04 * 
    		     weights[ 4] + 
    		     a05 * 
    		     weights[ 5] + 
    		     a06 * 
    		     weights[ 6] + 
    		     a07 * 
    		     weights[ 7] + 
    		     a08 * 
    		     weights[ 8] + 
    		     a09 * 
    		     weights[ 9] + 
    		     a10 * 
    		     weights[10] + 
    		     a11 * 
    		     weights[11] + 
    		     a12 * 
    		     weights[12] + 
    		     a13 * 
    		     weights[13] + 
    		     a14 * 
    		     weights[14] + 
    		     a15 * 
    		     weights[15] + 
    		     a16 * 
    		     weights[16] + 
    		     a17 * 
    		     weights[17] + 
    		     a18 * 
    		     weights[18] + 
    		     a19 * 
    		     weights[19] + 
    		     a20 * 
    		     weights[20] + 
    		     a21 * 
    		     weights[21] + 
    		     a22 * 
    		     weights[22] + 
    		     a23 * 
    		     weights[23] + 
    		     a24 * 
    		     weights[24] + 
    		     a25 *
    		     weights[25] + 
    		     a26 * 
    		     weights[26] + 
    		     a27 * 
    		     weights[27] + 
    		     a28 * 
    		     weights[28] + 
    		     a29 * 
    		     weights[29] + 
    		     a30 *
    		     weights[30] +  
    		     weights[31];
    
    return (sum >= theta) ? 1 : 0;
 }
     
}

























