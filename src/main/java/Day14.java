

import java.io.ObjectInputValidation;
import java.math.BigInteger;
import java.sql.SQLOutput;
import java.util.*;

public class Day14 {

    Utils utils = new Utils();

    public void go() {
        List<String> input = utils.readfile("input14.txt");

        HashMap<Integer, BigInteger> memory = new HashMap<>();


        int t = -1;
        String mask = "";
        while (t < input.size() - 1) {
            t++;
            if (input.get(t).contains("mask")) {
                mask = input.get(t).replace("mask = ", "");
                continue;
            }

            Integer key = Integer.parseInt(input.get(t).replaceAll(".*\\[", "").replaceAll("\\].*", ""));

            long value = Long.parseLong(input.get(t).replaceAll("mem.* = ", ""));
            String binValue = "0000000000000000000000000000000000000000000" + Long.toBinaryString(value);
            binValue = binValue.substring(binValue.length() - 36, binValue.length());

            int i = 1;
            for (char c : mask.toCharArray()) {
                if (c != 'X') {
                    // System.out.println(binValue +"v");
                    binValue = binValue.substring(0, i - 1) + String.valueOf(c) + binValue.substring(i, binValue.length());
                    // System.out.println(binValue + "n");
                }
                i++;
            }


            BigInteger res = getBigIntFromBinString(binValue);
            memory.put(key, res);

            System.out.println(binValue);


        }

        BigInteger sum = BigInteger.valueOf(0);
        Iterator it = memory.entrySet().iterator();

        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " - " + pair.getValue());
            BigInteger a = (BigInteger) pair.getValue();
            sum = sum.add(a);
            it.remove();
        }


        System.out.println("total " + sum);

    }

    public void go2() {

        List<String> input = utils.readfile("input14.txt");

        HashMap<BigInteger, BigInteger> memory = new HashMap<>();

        BigInteger keyBi = BigInteger.valueOf(0);

        int t = -1;
        String mask = "";
        while (t < input.size() - 1) {
            t++;
            if (input.get(t).contains("mask")) {
                mask = input.get(t).replace("mask = ", "");
                continue;
            }
            Long key = Long.valueOf(Integer.parseInt(input.get(t).replaceAll(".*\\[", "").replaceAll("\\].*", "")));
            keyBi = BigInteger.valueOf(key);

            //long value = Long.parseLong(input.get(t).replaceAll("mem.* = ", ""));
            String binValue = "0000000000000000000000000000000000000000000" + Long.toBinaryString(key);
            binValue = binValue.substring(binValue.length() - 36, binValue.length());

            int i = 1;
            List<Integer> xList = new ArrayList<>();
            for (char c : mask.toCharArray()) {
                if (c != 'X') {
                    if(c!='0'){
                        binValue = binValue.substring(0, i - 1) + String.valueOf(c) + binValue.substring(i, binValue.length());
                    }
                } else {
                    xList.add(i);
                }
                i++;
            }


            generateKeys(memory, binValue, xList, keyBi);

            //System.out.println(binValue);

        }

        BigInteger sum = BigInteger.valueOf(0);
        Iterator it = memory.entrySet().iterator();

        System.out.println(memory.size());

        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " - " + pair.getValue());
            BigInteger a = (BigInteger) pair.getValue();
            sum = sum.add(a);
            it.remove();
        }


        System.out.println("total " + sum);

    }

    private void generateKeys(HashMap<BigInteger, BigInteger> memory, String binValue, List<Integer> xList, BigInteger res) {
        BigInteger keyBi = BigInteger.valueOf(0);
        if (xList.size() == 1){
            String binValue0 = binValue.substring(0, xList.get(0) - 1) + "0" + binValue.substring(xList.get(0), binValue.length());
            keyBi = getBigIntFromBinString(binValue0);
            memory.put(keyBi, res);
            binValue0 = binValue.substring(0, xList.get(0) - 1) + "1" + binValue.substring(xList.get(0), binValue.length());
            keyBi = getBigIntFromBinString(binValue0);
            memory.put(keyBi, res);
            return;
        } else {
            String binValue0 = binValue.substring(0, xList.get(0) - 1) + "0" + binValue.substring(xList.get(0), binValue.length());
            generateKeys(memory, binValue0, xList.subList(1,xList.size()), res);
            binValue0 =  binValue.substring(0, xList.get(0) - 1) + "1" + binValue.substring(xList.get(0), binValue.length());
            generateKeys(memory, binValue0, xList.subList(1,xList.size()), res);
        }

    }

    private BigInteger getBigIntFromBinString(String binValue) {
        BigInteger bi = BigInteger.valueOf(1);
        BigInteger res = BigInteger.valueOf(0);
        for (int tc = binValue.length(); tc > 0; tc--) {
            if (binValue.substring(tc - 1, tc).equals("1")) {
                res = res.add(bi);
            }

            bi = bi.multiply(BigInteger.valueOf(2));
        }
        return res;
    }
}
