package sample;

import com.sun.org.apache.xpath.internal.objects.XString;

public abstract class ReturnLargestNumner {



    public static double returnLargest(double num1, double num2, double num3, double num4, double num5){

        double[] numberArray = {num1,num2,num3,num4,num5,};
        double max = numberArray[0];
        for(int i = 1; i < numberArray.length; i++){
            if(numberArray[i] > numberArray[i]){
                max = numberArray[i];
            }

        }

        return max;

    }
    public static void main(String[] args) {
        System.out.println(returnLargest(1031,30,100,2,20));


    }
}
