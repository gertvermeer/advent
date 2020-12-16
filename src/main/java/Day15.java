import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day15 {

    Utils utils = new Utils();

    HashMap<Integer, Integer> memory = new HashMap<>();


    public void go2(){

        List<String> input = utils.readfile("input15.txt");
        List<String> inputList = Arrays.asList(input.get(0).split(","));
        int turn = Integer.parseInt(inputList.get(inputList.size()-1));
        inputList = inputList.subList(0, inputList.size()-1);
        int pointer = 1;
        for (String number : inputList){
            System.out.println("Move:" + pointer + "result: " + number );
            memory.put(Integer.parseInt(number),pointer);
            pointer++;
        }

        int nextTurn = 0;
        while (pointer < 30000001){
            System.out.println("Move:" + pointer + "result: " + turn);
            if(memory.containsKey(turn)){
                int lastMove = memory.get(turn);
                nextTurn = pointer - lastMove;
            } else {
                nextTurn = 0;
            }
            memory.put(turn, pointer);
            turn = nextTurn;
            pointer++;

        }


    }



    private int findPrevious(Integer searchValue, List<Integer> series) {
        int index=series.size()-1;

        boolean test = (series.get(index) == searchValue);
        while (!test && index >0){
            index--;
            test = (series.get(index) == searchValue);
            //System.out.println(index);
        }

        if (index == 0 && !(series.get(0)== searchValue)){
            return 0;
        }
        return series.size() - index;
    }
}
