import java.util.List;

public class Day3 {

    Utils utils = new Utils();

    public void go(){

        List<String> input = utils.readfile("input3.txt");


        long count = getCount(input, 1, 1)
                  * getCount(input, 3, 1)
                  * getCount(input, 5, 1)
                  * getCount(input, 7, 1)
                  * getCount(input, 1, 2);

        System.out.println("Aantal : " + count);
    }

    private long getCount(List<String> input, int xStep, int yStep) {
        long count = 0;
        int y= 0;
        int x = 0 ;
        while (y + yStep  < input.size()){
            y = y + yStep;
            x = (x + xStep)% input.get(0).length();
            if (input.get(y).charAt(x) == '#'){
                count ++;
            }

        }
        return count;
    }

}
