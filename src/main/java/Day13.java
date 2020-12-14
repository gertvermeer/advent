import java.util.ArrayList;
import java.util.List;

public class Day13 {


    Utils utils = new Utils();

    public void go() {
        List<String> input = utils.readfile("input13.txt");

        int arrivalTime = Integer.parseInt(input.get(0));

        String[] lines= input.get(1).split(",");

        int minres = 999999999;
        int result = 0;

        for (String lineString : lines){
            if (lineString.equals("x")) continue;

            int line = Integer.parseInt(lineString);
            int rest = line - arrivalTime%line;
            if (rest < minres){
                minres = rest;
                result = rest * line;
            }
            System.out.println(rest);
        }
        System.out.println("Result is :" +  result);

    }

    public void go2(){
        List<String> input = utils.readfile("input13.txt");
        String[] lines= input.get(1).split(",");
        List<Integer> lineList = new ArrayList<>();
        List<Integer> pos = new ArrayList<>();
        int i = 0;

        int maxLine = 0;
        int maxInterval =0;

        for (String line: lines){
            if (line.equals("x")){
                i++;
                continue;
            }

            int intLine = Integer.parseInt(line);
            if (intLine > maxLine){
                maxLine = intLine;
                maxInterval = i;
            }

            lineList.add(intLine);
            pos.add(i);
            i++;
        }


        boolean found =false;
        long time = maxLine - maxInterval;
        while (!found) {

            boolean correctDepart = true;
            int check = 0;
            while(correctDepart && check < lineList.size()){
                long wait = (time+pos.get(check))%lineList.get(check);
                correctDepart = wait == 0;
                check ++;
            }
            if (correctDepart){
                System.out.println("correct depart " + time);
                found = true;
            }
            time = time + maxLine;
           // System.out.println(time);
        }




    }
}