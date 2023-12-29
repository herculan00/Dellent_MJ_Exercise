package com.example;

import java.math.BigInteger;
import java.util.Objects;

public class Utiles {


    private static BigInteger  labSeq(String index){

        int number;


        try {
            number = Integer.parseInt(index);

        }catch (Exception e) {
            return BigInteger.ONE.negate();
        }

        if (number<0){
            return BigInteger.ONE.negate();
        }

        if (number >3){

            if(MyCache.getCache().asMap().containsKey(index)){


                BigInteger x= new BigInteger(Objects.requireNonNull(MyCache.getCache().getIfPresent(index)));

               return x;
            }

            if (MyCache.gethCash().containsKey(index)) {
                return MyCache.gethCash().get(index);
            }
            BigInteger result = (labSeq(String.valueOf(number-4)).add(labSeq(String.valueOf(number-3))));


            MyCache.getCache().put(index,String.valueOf(result));
            MyCache.gethCash().put(index,result);
            return result;
        }

        if (number == 0 || number == 2){
            return BigInteger.ZERO;
        }

        if (number == 1 || number == 3){
            return BigInteger.ONE;
        }


        return BigInteger.ONE.negate();

    }

    public static void fillCache(){
        for (int i = 0; i < 10001; i++) {
            labSeq(String.valueOf(i));
        }
    }

    public static BigInteger getLabSeq(String index) {
       return labSeq(index);
    }


}
