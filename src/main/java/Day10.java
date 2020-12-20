import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class Day10 {
    Utils utils = new Utils();

    int preambleSize = 25;

    public void go() {
        List<String> input = utils.readfile("input10.txt");

        List<Integer> adapterList = new ArrayList<>();
        input.forEach(s ->
                adapterList.add(Integer.parseInt(s)));

        Integer[] yolts = new Integer[10];
        yolts[1]=0;
        yolts[2] =0;
        yolts[3]=1;



        List<Integer>  result = adapterList(0, new ArrayList<>(), adapterList, yolts);

        result.forEach(s -> System.out.println(s));

        int answer = yolts[1]*yolts[3];
        System.out.println("Answer" + answer);

        System.out.printf("Yolts"+ yolts[1] + "    3YOlts:" +yolts[3]);
    }



    public void go2() {
        List<String> input = utils.readfile("input10.txt");

        List<Integer> adapterList = new ArrayList<>();
        input.forEach(s ->
                adapterList.add(Integer.parseInt(s)));

        Collections.sort(adapterList);

        adapterList.forEach(s -> System.out.println(s));

        Integer max = 0;
        for (Integer s : adapterList){
            max = Integer.max(s,max);
        }

        ArrayList<Integer> unique = new ArrayList<>();
        int sp = 0;
        int lastvalue =0;
        for(Integer check : adapterList){

            if (check == lastvalue +3){
                unique.add(sp);
            }
            sp ++;
            lastvalue = check;
        }

        unique.add(adapterList.size());


        BigInteger total = BigInteger.valueOf(1);

        sp = 0;
        int ep = unique.get(0);
        int pointer = 0;
        while (pointer<unique.size()){
            int startAdapter;
            if (sp == 0){
                startAdapter = 0;
            } else {
                startAdapter = adapterList.get(sp);
            }
            int targetAdapter;
            targetAdapter = adapterList.get(ep-1);
            List<Integer> searchList = adapterList.subList(sp,ep);
            BigInteger sub = adapterPos(startAdapter, targetAdapter, new ArrayList<>(), searchList);
            if(sub.equals(BigInteger.valueOf(0))){
                System.out.println(" prrrrr"  + startAdapter + "target:" +  targetAdapter);
            }
            total = total.multiply(sub);
            sp = unique.get(pointer);
            if (pointer == unique.size()-1){
                ep = adapterList.get(adapterList.size()-1);
            } else {
                ep = unique.get(pointer + 1);
            }
            pointer ++;

        }


        BigInteger  result = adapterPos(77, max,new ArrayList<>(), adapterList);

        System.out.println("Answer" + total);

    }



    public BigInteger adapterPos (int from, int target,List<Integer> usedAdapterList, List<Integer> leftAdapterList) {

        if (leftAdapterList.size() == 1){
            return BigInteger.valueOf(1);
        }

        BigInteger sum = BigInteger.valueOf(0);

        if (usedAdapterList.size()>0 && usedAdapterList.get(usedAdapterList.size()-1) == target) {
            return BigInteger.valueOf(1);

        }
        if (leftAdapterList.size()==0){
            return BigInteger.valueOf(0);
        }

        Integer fromStart = from;
        for (Integer i = 1; i < 4; i++) {
            Integer possibleAdap = fromStart + i;
            if (leftAdapterList.contains(possibleAdap)) {
                from = possibleAdap;
                leftAdapterList.remove(Integer.valueOf(possibleAdap));
                usedAdapterList.add(possibleAdap);
                sum = sum.add(adapterPos(from, target, usedAdapterList, leftAdapterList));
                leftAdapterList.add(possibleAdap);
                usedAdapterList.remove(Integer.valueOf(possibleAdap));
            }

        }
        return sum;
    }



    public List<Integer> adapterList(int from,  List<Integer> usedAdapterList, List<Integer> leftAdapterList, Integer[]yolts) {

        int numberOfAdapters = usedAdapterList.size() + leftAdapterList.size();

        if (leftAdapterList.size() == 0) {
                return usedAdapterList;
        }

        Integer fromStart = from;
        for (Integer i = 1; i < 4; i++) {
            Integer possibleAdap = fromStart + i;
            if (leftAdapterList.contains(possibleAdap)) {
                yolts[i] = yolts[i]+1;
                from = possibleAdap;
                leftAdapterList.remove(Integer.valueOf(possibleAdap));
                usedAdapterList.add(possibleAdap);
                List<Integer> result = adapterList(from, usedAdapterList, leftAdapterList, yolts);
                if(result.size() == numberOfAdapters){
                    return result;
                }
                yolts[i]=yolts[i]-1;
                leftAdapterList.add(possibleAdap);
                usedAdapterList.remove(Integer.valueOf(possibleAdap));
            }

        }
        return leftAdapterList;
    }



}
