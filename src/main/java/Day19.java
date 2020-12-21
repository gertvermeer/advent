import Day16.Utils;
import Day17.Grid;
import Day17.Grid4D;
import com.sun.javafx.image.IntPixelGetter;
import org.w3c.dom.ls.LSOutput;

import javax.security.auth.callback.CallbackHandler;
import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Day19 {


    Utils utils = new Utils();
    HashMap<Integer, List<List<Integer>>> rulesMap = new HashMap<>();
    HashMap<Integer, Character> valueMap = new HashMap<>();

    List<String>  pathList = new ArrayList<>();

    public void go() {
        List<String> input = utils.readfile("input19.txt");

        List<String> validStrings = new ArrayList<>();

        int pointer = 0;

        while (pointer < input.size() & input.get(pointer).contains(":")) {

            Integer ruleno = Integer.parseInt(input.get(pointer).replaceAll(":.*", ""));
            String rule = input.get(pointer).replaceAll(".*: ", "");

            if (rule.contains("\"")){
                valueMap.put(ruleno, rule.charAt(1));

            } else {

                rulesMap.put(ruleno, parseRule(rule));
            }
            pointer++;
        }

        int numberValid = 0;

        while(input.get(pointer).equals("")){
            pointer++;
        }

        while (pointer < input.size()) {
            String result = itRuleList(input.get(pointer),0);
            System.out.println(pathList);
            if (result.length() == input.get(pointer).length() && checkAllPlus(result)){
                numberValid++;

            }
            System.out.println(input.get(pointer));
            System.out.println(result);
            System.out.println();
            pointer ++;
        }


        System.out.println("Number of valid: " + numberValid);

    }

    public String itRuleList(String checked, Integer rule){
        pathList = new ArrayList<>();
        String check = checkStringonRule(checked,rule,"");
        while (checkAllPlus(check.substring(0,check.length()-1)) && check.contains("f")){
            check = checkStringonRule(checked,rule,"");
            System.out.println(check);
        }



        return "";
    }


    public List<List<Integer>> parseRule(String value){
        int starpointer= 0;
        int pointer = 0;
        List<List<Integer>> result = new ArrayList<>();

        while (pointer < value.length()){
            while (pointer<value.length() && value.charAt(pointer)!='|'){
                pointer ++;
            }
            String []ruleArray = value.substring(starpointer, pointer).split(" ");
            List ruleList = new ArrayList();
            for (String rule : ruleArray){
                ruleList.add(Integer.parseInt(rule));
            }
            result.add(ruleList);
            pointer ++;
            pointer ++;
            starpointer = pointer;
        }
        return result;
    }


    public String checkStringonRule(String checked, Integer rule, String path){

        //System.out.println(checked + "rule:"  + rule );

        if (checkAllPlus(checked)){
            return checked + "fall";
        }
        if(valueMap.containsKey(rule)){
            return checkValidCharacter(checked, rule);
        }

        List<List<Integer>> ruleListList = rulesMap.get(rule);
        String checkStart = checked;

        for (List<Integer> ruleList : ruleListList){
            checked = checkStart;

            if (ruleList.size() > 1){
                path = path + (rule+ ":");
            }
            for (Integer rulec: ruleList){
                checked = checkStringonRule(checked, rulec, path);
               if (checked.contains("f")){
                    break;
                }
            }

            if (!checked.contains("f")) {
                    return checked;
            }

        }

        pathList.add(path);
        return checked;

    }

    private String checkValidCharacter(String checked, Integer rule) {
        Character ruleChar = valueMap.get(rule);
        Integer index =0;
        while (checked.charAt(index)== '+' && (index< checked.length())){
            index++;
        }
 //       System.out.println(checked);

        if (ruleChar.equals(checked.charAt(index))) {
            checked = checked.substring(0, index) + '+' + checked.substring(index + 1) ;
        } else {
            return checked + "fvalidcharacter";
        }
        return checked;
    }


    public Integer numberPlus(String value){

        Integer result = 0;
        for(Character c: value.toCharArray()){
            result++;
        }
        return result;
    }

    public boolean checkAllPlus(String value){

        boolean result = true;
        for(Character c: value.toCharArray()){
            result = result && c.equals('+');
        }
        return result;
    }


}

