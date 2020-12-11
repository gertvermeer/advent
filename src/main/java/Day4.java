import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Day4 {

    Utils utils = new Utils();

    public void go() {

        int valid = 0;
        List<String> checkList  = Arrays.asList( "byr", "iyr", "eyr", "hgt", "hcl", "ecl",  "pid");

        List<String> input = utils.readfile("input4final.txt");

        int row = 0;
        while (row < input.size()){
            String validateString = input.get(row);
            if (validateString.equals("")){
                if (checkList.size() == 0){
                    valid ++;
                }
                checkList = Arrays.asList( "byr", "iyr", "eyr", "hgt", "hcl", "ecl",  "pid");
            } else {

                checkList = checkList.stream().filter(s -> !validateString.contains(s)).collect(Collectors.toList());
            }
            row++;

        }
        System.out.println("total valid: " + valid);
    }
}
