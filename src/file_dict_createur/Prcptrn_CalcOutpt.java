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
