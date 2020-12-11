import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;


public class Day2 {

    @Getter
    @Setter
    class Line{

        int min;
        int max;
        char field;
        String checkString;
    }


    Utils utils = new Utils();

    public void go(){
        List<String> input = utils.readfile("input2.txt");
        AtomicInteger count = new AtomicInteger();
        input.forEach( line -> {
                    String [] elements=  line.split(" ");
                    String [] fromTo = elements[0].split("-");
                    char letter = elements[1].charAt(0);
                    String search = elements[2];
                    if (validate2(Integer.parseInt(fromTo[0])-1, Integer.parseInt(fromTo[1])-1, letter, search)){
                        count.getAndIncrement();
                    } else {
                        System.out.println("invalid" + fromTo[0]+ "-" +  fromTo[1] + "-" +  letter + "-" +  search);
                    }
                });
        System.out.println("Number valid " + count);
    }

    public boolean validate(long min, long max, char check, String search ){
        long count = search.chars().filter(ch -> ch == check).count();

        return count >= min && count <= max;
    }

    public boolean validate2(int min, int max, char check, String search ){
        int count = 0;
        System.out.println("prrr" + search.length()+ check);
        if (search.length() < min){
            return false;
        }
        if (search.charAt(min) == check){
            count ++;
        }

        if (search.length() > max){
            if (search.charAt(max) == check){
                count ++;
            }
        }



       return count == 1;
    }

}
