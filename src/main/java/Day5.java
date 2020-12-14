import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Day5 {



    Utils utils = new Utils();
    Boolean [] checklist = new  Boolean[1000];

    public void go() {

        for (int t = 0 ; t<1000;t++){
            checklist[t] =false;
        }

        int highestSeat = 0;
        List<String> input = utils.readfile("input5.txt");

        for (int t=0; t <input.size(); t++){
            highestSeat = Math.max(highestSeat, analyse(input.get(t )));
        }

        System.out.println("Lege stoel:"  + findEmpty());
        System.out.println("Higehest Seat:" + highestSeat);
    }

    private int analyse(String line) {

        String binString = line.substring(0,7).replace("F","0").replace("B", "1");
        int row = Integer.parseInt(binString,2);

        binString = line.substring(7,10).replace("L","0").replace("R", "1");
        int column = Integer.parseInt(binString,2);
        int result = row *8 +column;

        checklist[result] = true;

        System.out.println(row +" - " + column + " - " + result );
        return  row*8+column;
    }


    private int  findEmpty(){

        int t =40;
        while (true){
            if (!checklist[t]){
                return  t;
            }
            t++;
        }

    }


}
