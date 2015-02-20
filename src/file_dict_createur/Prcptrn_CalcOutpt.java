package file_dict_createur;

public class Prcptrn_CalcOutpt 
{
    /**
     * returns either 1 or 0 using a threshold function
     * theta is 0range
     * @param theta an integer value for the threshold
     * @param weights the array of weights
     * @param a the array of inputs
     * @return 1 or 0
     */
    static int calculateOutput(int theta, double[] weights, double[][] a, int index)
    {
        double sum = 0;
        int i;
        for (i = 0; i < a.length; i++) 
        {
            sum += weights[i] * a[i][index];
        }
        sum += weights[i];
        return (sum >= theta) ? 1 : 0;
    }
}
