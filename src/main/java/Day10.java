import java.util.ArrayList;
import java.util.List;

public class Day10 {
    Utils utils = new Utils();

    int preambleSize = 25;

    public void go() {
        List<String> input = utils.readfile("input10.txt");

        List<Integer> adapterList = new ArrayList<>();
        input.forEach(s ->
                adapterList.add(Integer.parseInt(s)));

        List<Integer>  result = adapterList(0, 5, new ArrayList<>(), adapterList);

        result.forEach(s -> System.out.println(s));

    }


    public List<Integer> adapterList(int from, int target, List<Integer> usedAdapterList, List<Integer> leftAdapterList) {

        int numberOfAdapters = usedAdapterList.size() + leftAdapterList.size();

        if (leftAdapterList.size() == 0) {
            int lastAdapter = usedAdapterList.get(usedAdapterList.size() - 1);
            if (lastAdapter >= target-3 && lastAdapter <= target) {
                return usedAdapterList;
            } else {
                usedAdapterList.add(-1);
                return usedAdapterList;
            }
        }

        List<Integer> nextAdapter = new ArrayList<>();
        for (Integer i = 1; i < 4; i++) {
            nextAdapter.add(from + i);
        }

        for (Integer possibleAdap : nextAdapter) {

            if (leftAdapterList.contains(possibleAdap)) {
                from = possibleAdap;
                leftAdapterList.remove(possibleAdap);
                usedAdapterList.add(possibleAdap);
                List<Integer> result = adapterList(from, target, usedAdapterList, leftAdapterList);
                if(result.size() == numberOfAdapters){
                    return result;
                }
                if(result.get(result.size()-1)==-1){
                    result.remove(-1);
                }
                leftAdapterList.add(possibleAdap);
                usedAdapterList.remove(possibleAdap);
            }

        }
        return leftAdapterList;
    }



}
