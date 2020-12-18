import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Day9 {


    Utils utils = new Utils();

    int preambleSize = 25;

    public void go() {
        List<String> input = utils.readfile("input9.txt");


        List<BigInteger> inputList = new ArrayList<>();
        input.forEach(s ->
               inputList.add(new BigInteger(s))
               );

        List<BigInteger> preambe;

        int pointer = preambleSize;
        boolean valid = true;
        while(pointer < inputList.size() && valid){
            pointer++;
            preambe = inputList.subList(pointer-preambleSize,pointer);
            valid = validInput(inputList.get(pointer), preambe);
        }
        System.out.println("First invalid" + input.get(pointer));
    }

    public void go2() {
        List<String> input = utils.readfile("input9.txt");


        List<BigInteger> inputList = new ArrayList<>();
        input.forEach(s ->
                inputList.add(new BigInteger(s))
        );

        List<BigInteger> preambe;

        int pointer = preambleSize;
        boolean valid = true;
        while(pointer < inputList.size() && valid){
            pointer++;
            preambe = inputList.subList(pointer-preambleSize,pointer);
            valid = validInput(inputList.get(pointer), preambe);
        }
        System.out.println("First invalid" + input.get(pointer));

        List<BigInteger> result = findKey(inputList.get(pointer), inputList);

        BigInteger min = result.get(0);
        BigInteger max = result.get(0);
        for(BigInteger s : result){
            min =min.min(s);
            max = max.max(s);
        };

        System.out.println("Min: " + min + "Max: "+ max);
        System.out.println("TOTAAL "  + min.add(max));



    }


    public List<BigInteger> findKey(BigInteger key, List<BigInteger> inputList){
        int pointer = 0;
        int start = 0;
        BigInteger sum;
        while (pointer< inputList.size()){
            start = pointer;
            int compareTo = key.compareTo(inputList.get(pointer));
            sum = BigInteger.valueOf(0);
            while (compareTo >0){
                sum = sum.add(inputList.get(pointer));
                compareTo = key.compareTo(sum);
                pointer ++;
            }
            if (compareTo == 0){
                return inputList.subList(start, pointer);
            }
            pointer = start +1;
        }
        return null;
    }

    public boolean validInput(BigInteger p, List<BigInteger> inputList){
        for (int t1= 0; t1< inputList.size()-1; t1++){
            for (int t2 =t1 +1;t2<inputList.size();t2++){
                if (inputList.get(t1).add(inputList.get(t2)).equals(p)){
                    return true;
                }
            }
        }
        return false;
    }
}
