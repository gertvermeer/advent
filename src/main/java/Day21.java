import Day16.Utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day21 {


    Utils utils = new Utils();


    public void go() {
        List<String> input = utils.readfile("input21.txt");
        HashMap<String,List<String>> codeHmap = new HashMap<>();


        // Fill datastructure


        Integer pointer = 0;
        while (pointer < input.size()){
            String ingredients = input.get(pointer).replaceAll(".*contains ","").replaceAll("\\)","");
            System.out.println(ingredients);
            List<String> ingredientsList = Arrays.asList(ingredients.split(","));

            String codewords = input.get(pointer).replaceAll("\\(.*","");
            List<String> codewordList = Arrays.asList(codewords.split(" "));

            for (String code : codewordList){

                if (codeHmap.containsKey(code)){
                    List <String> common = new ArrayList<>();
                    List <String> poss  = codeHmap.get(code);
                    poss.forEach(s -> {
                        if (ingredientsList.contains(s)){
                            common.add(s);
                        }
                    });
                    codeHmap.put(code, common);
                } else {
                    codeHmap.put(code, ingredientsList);
                }
            }
            pointer++;
        }


        System.out.println("prrrr");



    }

}
