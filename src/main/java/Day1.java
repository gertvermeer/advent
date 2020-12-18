import Day16.Utils;


import java.util.ArrayList;
import java.util.List;

public class Day1 {


    Utils utils = new Utils();
    List<Integer> inputList = new ArrayList<>();

    public void go() {
        List<String> input = utils.readfile("input1.txt");

        input.forEach(s -> inputList.add(Integer.parseInt(s)));
        System.out.println(findCombi());

    }

    public void go2() {
        List<String> input = utils.readfile("input1.txt");

        input.forEach(s -> inputList.add(Integer.parseInt(s)));
        System.out.println(findCombi2());

    }

    public int findCombi(){
        for (int t1 = 0 ; t1< inputList.size(); t1++){
            for (int t2 = t1+1 ; t2 <inputList.size(); t2++){
                int p = inputList.get(t1) + inputList.get(t2);
                if (p == 2020){
                    return inputList.get(t1)* inputList.get(t2);
                }
            }
        }
        return 0;
    }

    public int findCombi2(){
        for (int t1 = 0 ; t1< inputList.size(); t1++){
            for (int t2 = t1+1 ; t2 <inputList.size(); t2++){
                for (int t3 = t2+1 ; t3 <inputList.size(); t3++) {
                    int p = inputList.get(t1) + inputList.get(t2)+ inputList.get(t3);
                    if (p == 2020) {
                        return inputList.get(t1) * inputList.get(t2) * inputList.get(t3);
                    }
                }
            }
        }
        return 0;
    }

}
