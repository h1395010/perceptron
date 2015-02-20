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

class Perceptron {
    static final int MAX_ITER = 100;
    static final double LEARNING_RATE = 0.1;
    static final int THETA = 0;
    
    static final String FILEPATH = "atheism";
    //static final String FILEPATH = "sports";
    

    
    static int test_output;

    public static void perceptron(Set<String> globoDict,
        Map<String, int[]> trainingPerceptronInput,
        Map<String, int[]> testPerceptronInput)
    {
        final int globoDictSize = globoDict.size(); // number of features (x, y, z)

        // weights total 32 (31 for input variables and one for bias)
        double[] weights = new double[globoDictSize + 1];
        for (int i = 0; i < weights.length; i++) 
        {
            weights[i] = Math.floor(Math.random() * 10000) / 10000;
        }

        int inputSize = trainingPerceptronInput.size();
        int[] outputs = new int[inputSize];
        final double[][] a = initializeOutput(trainingPerceptronInput, globoDictSize, outputs);

        double globalError;
        int iteration = 0;
        do 
        {
            iteration++;
            globalError = 0;
            // loop through all instances (complete one epoch)
            for (int p = 0; p < inputSize; p++) 
            {
                // calculate predicted class
                int output = Prcptrn_CalcOutpt.calculateOutput(THETA, weights, a, p);
                // difference between predicted and actual class values
                double localError = outputs[p] - output;
                int i;
                for (i = 0; i < a.length; i++) {
                    weights[i] += LEARNING_RATE * localError * a[i][p];
                }
                weights[i] += LEARNING_RATE * localError;

                // summation of squared error (error value for all instances)
                globalError += localError * localError;
            }

            /* Root Mean Squared Error */
            System.out.println("Iteration "
                + iteration + " : RMSE = " + Math.sqrt(globalError
                    / inputSize));
        } 
        while (globalError != 0 && iteration <= MAX_ITER);

        System.out.println("\n=======\nDecision boundary equation:");
        int i;
        for (i = 0; i < a.length; i++) 
        {
            //System.out.print(" a");
            //if (i < 10) System.out.print(0);
            //System.out.println(i + " *" + weights[i]);
        }
        System.out.println(" bias: " + weights[i]);

        inputSize = testPerceptronInput.size();
        outputs = new int[inputSize];
        double[][] z = initializeOutput(testPerceptronInput, globoDictSize, outputs);

        // calculate predicted class TEST
        //if you change up the input, you've got to find a different test
        //here is the issue

        

        //is it safe to globo this?
        test_output = Prcptrn_CalcOutpt.calculateOutput(THETA, weights, z, 0);
        

//        System.out.println("\n=======\nTEST Point:");
//        for (i = 0; i < z.length; i++) 
//        {
//            System.out.println(" z");
//            if (i < 10) System.out.print(0);
//            System.out.println(i + "[0]" + z[i][0]);
//        }
//        System.out.println();
      //is it safe to globo this?
        System.out.println("class = " + test_output);
    }

    static double[][] initializeOutput( Map<String, int[]> perceptronInput, 
    							        int size, 
    							        int[] outputs)
    {
        final int inputSize = perceptronInput.size();
        final double[][] a = new double[size][inputSize];

        // 2d array for features
        int[][] feature_matrix = new int[inputSize][size];
        String[] output_label = new String[inputSize];
        int x = 0;
        for (Entry<String, int[]> entry : perceptronInput.entrySet()) 
        {
            int[] container = entry.getValue();

            for (int j = 0; j < container.length; j++) 
            {
                feature_matrix[x][j] = container[j];
                output_label[x] = String.valueOf(entry.getKey());
            }
            x++;
        }

        for (x = 0; x < inputSize; x++) 
        {
            for (int i = 0; i < a.length; i++) 
            {
                a[i][x] = feature_matrix[x][i];
            }
            outputs[x] = output_label[x].equals(FILEPATH) ? 1 : 0;
        }

        return a;
    }


}























