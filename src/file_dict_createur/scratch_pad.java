package file_dict_createur;

public class scratch_pad {

}


//package file_dict_createur;
//
//
///**
//The Perceptron Algorithm
//By Dr Noureddin Sadawi
//Please my youtube videos on perceptron for things to make sense!
//
//Code adapted from:
//https://github.com/RichardKnop/ansi-c-perceptron
//*/  
//
//import java.util.*;
//import java.util.Map.Entry;
//import java.io.*;
//import java.text.*;
//import java.math.*;
//
//
//
//class Perceptron
//{
// static int MAX_ITER = 100;
// static double LEARNING_RATE = 0.1;           
// //static int NUM_INSTANCES;
// static int theta = 0;  
// 
// public static void perceptron( Set<String> GLOBO_DICT, Map<File, int[] > perceptron_input )
// {
//	 
//	 //number of features, number of x, y, z
//	 int size_of_globo_dict = GLOBO_DICT.size();
//	 
//	 //number of instances
//	 int NUM_INSTANCES = perceptron_input.size();
//			 
//	//2d array for features
//	int i = 0;
//	int[][] feature_matrix = new int[ perceptron_input.size() ][ GLOBO_DICT.size() ];
//	String[][] output_label = new String[ perceptron_input.size() ][ 2 ];
//
//	for(Entry<File, int[]> entry : perceptron_input.entrySet())
//	{
//		int[] container =entry.getValue();
//
//		for(int j = 0; j < 4; j++)
//		{
//			feature_matrix[i][j] = container[j];
//			        
//			output_label[i][1] = entry.getKey().toString(); 
//		}
//			i++;
//	}
//	   
//   
//   double[] weights = new double[ GLOBO_DICT.size() + 1 ];// GLOBO_DICT.size() for input variables and one for bias
//   double localError, globalError;
//   int iteration, output;
//   
//   //initialize weights with random numbers
//   for(int x = 0; x <= GLOBO_DICT.size(); x++)
//   {
//	   weights[x] = randomNumber(0,1);
//   }
//           
//   iteration = 0;
//   do 
//   {
//       iteration++;
//       globalError = 0;
//       //loop through all instances (complete one epoch)
//       for (int p = 0; p < perceptron_input.size(); p++) 
//       {
//           
//           for(int y = 0; y < GLOBO_DICT.size(); y++)
//           {
//        	// calculate predicted class
//            output = calculateOutput( theta, weights, feature_matrix[p][y] );
//          
//           
//           if(output_label[p] == XXX)
//           {
//            localError = output_label[p] - output;
//           }
//           
//           // difference between predicted and actual class values
//           localError = outputs[p] - output;
//           //update weights and bias
//           weights[0] += LEARNING_RATE * localError * x[p];
//           weights[1] += LEARNING_RATE * localError * y[p];
//           weights[2] += LEARNING_RATE * localError * z[p];
//           weights[3] += LEARNING_RATE * localError;
//           
//           //I need to make this part more dynamic
//           
//           
//           
//           //summation of squared error (error value for all instances)
//           globalError += (localError*localError);
//           }    
//       }
//
// /* Root Mean Squared Error */
//    System.out.println("Iteration "+iteration+" : RMSE = "+Math.sqrt(globalError/NUM_INSTANCES));
//   } while (globalError != 0 && iteration<=MAX_ITER);
//   
//   System.out.println("\n=======\nDecision boundary equation:");
//   System.out.println(weights[0] +"*x + "+weights[1]+"*y +  "+weights[2]+"*z + "+weights[3]+" = 0");
//   
//   //generate 10 new random points and check their classes
//   //notice the range of -10 and 10 means the new point could be of class 1 or 0
//   //-10 to 10 covers all the ranges we used in generating the 50 classes of 1's and 0's above
//   for(int j = 0; j < 10; j++){
//     double x1 = randomNumber(-10 , 10);
//     double y1 = randomNumber(-10 , 10);   
//     double z1 = randomNumber(-10 , 10); 
//   
//     output = calculateOutput(theta,weights, x1, y1, z1);
//     System.out.println("\n=======\nNew Random Point:");
//     System.out.println("x = "+x1+",y = "+y1+ ",z = "+z1);
//     System.out.println("class = "+output);
//   }
// }//end main  
// 
///**
// * returns a random double value within a given range
// * @param min the minimum value of the required range (int)
// * @param max the maximum value of the required range (int)
// * @return a random double value between min and max
// */ 
//public static double randomNumber(int min , int max) {
//    DecimalFormat df = new DecimalFormat("#.####");
//    double d = min + Math.random() * (max - min);
//    String s = df.format(d);
//    double x = Double.parseDouble(s);
//    return x;
//}
//
///**
// * returns either 1 or 0 using a threshold function
// * theta is 0range
// * @param theta an integer value for the threshold
// * @param weights[] the array of weights
// * @param x the x input value
// * @param y the y input value
// * @param z the z input value
// * @return 1 or 0
// */ 
//static int calculateOutput(int theta, double weights[], int[] feature_matrix)
//{
//   double sum = x * weights[0] + y * weights[1] + z * weights[2] + weights[3];
//   return (sum >= theta) ? 1 : 0;
//}
//    
//
//
//
//
//
//}
//
//
//

