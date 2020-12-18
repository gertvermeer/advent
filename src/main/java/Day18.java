import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Day18 {

    Utils utils = new Utils();

    public void go() {

        List<String> input = utils.readfile("input18.txt");

        int pointer = 0;
        BigInteger sum = BigInteger.valueOf(0);
        while (pointer < input.size()){
            String cal = input.get(pointer);
            cal = cal.replaceAll("\\s+","");
            while(cal.contains("(")){
                int deepest = deepestBracket(cal);
                int closing = findClosing(cal,deepest);
                String innerCal = cal.substring(deepest+1, closing);
                String resultbewteenbarcket = calculateNormal(innerCal).toString();
                cal = cal.replace(cal.substring(deepest,closing+1), resultbewteenbarcket);
                System.out.println(cal);
            }
            BigInteger result = calculateNormal(cal);
            sum = sum.add(result);
            System.out.println(input.get(pointer));
            System.out.println(sum);
            System.out.println(result);
            pointer++ ;
        }
        System.out.println("SUM IS: " + sum);
    }


    public void go2() {

        List<String> input = utils.readfile("input18.txt");

        int pointer = 0;
        BigInteger sum = BigInteger.valueOf(0);
        while (pointer < input.size()){
            String cal = input.get(pointer);
            cal = cal.replaceAll("\\s+","");
            while(cal.contains("(")){
                int deepest = deepestBracket(cal);
                int closing = findClosing(cal,deepest);
                String innerCal = cal.substring(deepest+1, closing);
                String resultbewteenbarcket = calculateNormal2(innerCal).toString();
                cal = cal.replace(cal.substring(deepest,closing+1), resultbewteenbarcket);
                System.out.println(cal);
            }
            BigInteger result = calculateNormal2(cal);
            sum = sum.add(result);
            System.out.println(input.get(pointer));
            System.out.println(sum);
            System.out.println(result);
            pointer++ ;
        }
        System.out.println("SUM IS: " + sum);
    }


    public int findClosing (String input, int p){
        Character c = input.charAt(p);
        while ((p< input.length()) && (!c.equals(')'))){
            p++;
            c = input.charAt(p);
        }
        return p;
    }


    public int deepestBracket(String input){
        int p = 0;
        int deepestPos = 0;
        int deepestLev = 0;
        int level = 0;
        while (p< input.length()){
            Character c = input.charAt(p);
            if (c.equals('(')){
                level = level + 1;
                if (level> deepestLev){
                    deepestLev = level;
                    deepestPos = p;
                }

            }
            if (c.equals(')')){
                level--;
            }
            p++;
        }
        return deepestPos;
    }


    public BigInteger calculateNormal2(String input){
        BigInteger sum = BigInteger.valueOf(0);
        int p = 0;
        input = input.replaceAll("\\s+","");
        List<BigInteger>  number = new ArrayList<>();
        List<Character> operators = new ArrayList<>();
        while (p<input.length()){
            Character c = input.charAt(p);
            if (!Character.isDigit(c)){
                operators.add(c);
                p++;
            } else {
                Integer ps = p;
                while (p< input.length() && Character.isDigit(input.charAt(p))){
                    p++;
                }
                number.add(BigInteger.valueOf(Integer.parseInt(input.substring(ps,p))));
            }
        }


        BigInteger temp = BigInteger.valueOf(0);
        while (number.size()>1){

            if (operators.contains('+')){
                p= operators.indexOf('+');
            } else {
                p=0;
            }

            switch (operators.get(p)){
                case '*':
                    temp = number.get(p).multiply(number.get(p+1));
                    break;
                case '+':
                    temp = number.get(p).add(number.get(p+1));
                    break;
            }
            number.add(p,temp);
            number.remove(p+1);
            number.remove(p+1);
            operators.remove(p);
            p++;
        }
        return  number.get(0);

    }




    public BigInteger calculateNormal(String input){
        BigInteger sum = BigInteger.valueOf(0);
        int p = 0;
        input = input.replaceAll("\\s+","");
        List<Integer>  number = new ArrayList<>();
        List<Character> operators = new ArrayList<>();
        while (p<input.length()){
            Character c = input.charAt(p);
            if (!Character.isDigit(c)){
                operators.add(c);
                p++;
            } else {
                Integer ps = p;
                while (p< input.length() && Character.isDigit(input.charAt(p))){
                    p++;
                }
                number.add(Integer.parseInt(input.substring(ps,p)));
            }
        }

        p = 0;
        sum = BigInteger.valueOf(number.get(p));
        p++;
        while (p< number.size()){

            switch (operators.get(p-1)){

                case '*':
                    sum = sum.multiply(BigInteger.valueOf(number.get(p)));
                    break;
                case '+':
                        sum = sum.add(BigInteger.valueOf(number.get(p)));
                        break;
                case '-':
                        sum = sum.subtract(BigInteger.valueOf(number.get(p)));

            }
            p++;
        }
        return  sum;

    }


}
