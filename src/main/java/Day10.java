import java.util.ArrayList;
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

        Integer[] yolts = new Integer[4];

        List<Integer>  result = adapterList(0, new ArrayList<>(), adapterList, yolts);

        result.forEach(s -> System.out.println(s));

        System.out.printf("Yolts", yolts);
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
