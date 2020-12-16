package Day16;






import java.sql.SQLOutput;
import java.util.ArrayList;

import java.util.Arrays;
import java.util.List;


public class Day16 {


    Utils utils = new Utils();


    public void go() {

        List<String> input = utils.readfile("input16.txt");
        List<Rule> rulesList = new ArrayList<>();

        int pointer = 0;
        while (input.get(pointer).contains(":")) {

            String name = input.get(pointer).replaceAll(":.*", "");
            String numberParse = input.get(pointer).replaceAll(".*: ", "");
            String numberSet1 = numberParse.replaceAll(" or.*", "");
            String numberSet2 = numberParse.replaceAll(".*or ", "");
            int from1 = Integer.parseInt(numberSet1.replaceAll("-.*", ""));
            int from2 = Integer.parseInt(numberSet2.replaceAll("-.*", ""));
            int to1 = Integer.parseInt(numberSet1.replaceAll(".*-", ""));
            int to2 = Integer.parseInt(numberSet2.replaceAll(".*-", ""));
            rulesList.add(new Rule(name, from1, to1, from2, to2, null));
            System.out.println(name + "-" + from1 + "-" + to1 + "-" + from2 + "-" + to2);
            pointer++;
        }

        while (!input.get(pointer).contains("nearby")) {
            pointer++;
        }

        pointer++;

        long checksum = 0;

        while (pointer < input.size()) {

            System.out.println(input.get(pointer));

            List<String> ticketStringList = Arrays.asList(input.get(pointer).split(","));
            List<Integer> ticketList = new ArrayList<>();
            ticketStringList.forEach(s -> ticketList.add(Integer.parseInt(s)));


            boolean test = false;
            int index = 0;
            for (int ticket : ticketList) {
                test = false;
                for (Rule rule : rulesList) {
                    test = test || (ticket >= rule.getFrom1() && ticket <= rule.getTo1());
                    test = test || (ticket >= rule.getFrom2() && ticket <= rule.getTo2());
                }
                if (!test) {
                    System.out.println("error" + ticket);
                    checksum = checksum + ticket;
                }
                index++;
            }

            pointer++;
        }

        System.out.println("Checksum: " + checksum);

    }


    public void go2() {

        List<String> input = utils.readfile("input16.txt");
        List<Rule> rulesList = new ArrayList<>();

        int pointer = 0;
        while (input.get(pointer).contains(":")) {

            String name = input.get(pointer).replaceAll(":.*", "");
            String numberParse = input.get(pointer).replaceAll(".*: ", "");
            String numberSet1 = numberParse.replaceAll(" or.*", "");
            String numberSet2 = numberParse.replaceAll(".*or ", "");
            int from1 = Integer.parseInt(numberSet1.replaceAll("-.*", ""));
            int from2 = Integer.parseInt(numberSet2.replaceAll("-.*", ""));
            int to1 = Integer.parseInt(numberSet1.replaceAll(".*-", ""));
            int to2 = Integer.parseInt(numberSet2.replaceAll(".*-", ""));
            rulesList.add(new Rule(name, from1, to1, from2, to2,null));
            System.out.println(name + "-" + from1 + "-" + to1 + "-" + from2 + "-" + to2);
            pointer++;
        }

        List<Integer> checkList = new ArrayList<>();
        for (int t = 0;t <pointer;t++){
            checkList.add(t);
        }

        rulesList.forEach(s -> s.setPosPosition(new ArrayList<>(checkList)));

        // Parsing mu ticket
        while (!input.get(pointer).contains("your ticket")) {
            pointer ++;
        }
        pointer ++;
        List<String> ticketStringList = Arrays.asList(input.get(pointer).split(","));
        List<Integer> myTicket = new ArrayList<>();
        ticketStringList.forEach(s -> myTicket.add(Integer.parseInt(s)));
        System.out.println("My ticket: " + myTicket);


        while (!input.get(pointer).contains("nearby")) {
            pointer++;
        }

        pointer++;

        while (pointer < input.size()) {

            ticketStringList = Arrays.asList(input.get(pointer).split(","));
            List<Integer> ticket = new ArrayList<>();
            ticketStringList.forEach(s -> ticket.add(Integer.parseInt(s)));


            // Check if ticket complies to at least on value
            if (!validTicket(ticket, rulesList)){
                pointer ++;
                continue;
            }

            // Check if ticket does not comply to rule -> delete pos
            int index= 0;
            for (int item : ticket) {
                int finalIndex = index;
                rulesList.forEach(rule -> {
                    if (!rule.complies(item)) {
                        rule.removepos(finalIndex);
                    }
                    ;
                });
                index++;
            }
            pointer++;
        }


        cleanRules(rulesList);
        long resultProduct = 1;
        for(int t = 0 ;t< myTicket.size();t++){
            if(rulesList.get(t).getName().contains("departure")){
                int index = rulesList.get(t).posPosition.get(0);
                resultProduct = resultProduct * myTicket.get(index);
                System.out.println("resultP: " + resultProduct);
            }


        }

        System.out.println("FINAL RESULT:" + resultProduct);

    }

    private boolean validTicket(List<Integer> ticket, List<Rule> ruleList) {
        boolean result = true;
        for (Integer item : ticket){
            boolean validItem = false;
            for(Rule rule : ruleList){
               validItem = validItem || rule.complies(item);
            }
            result = result && validItem;
        }
        return  result;
    }

    private void cleanRules(List<Rule> rulesList) {
        while (rulesList.stream().filter(s -> s.posPosition.size() >1).count() >0) {
            int p = 0;
            while (p < rulesList.size()){
                if (rulesList.get(p).getPosPosition().size() == 1){
                  int uniquePos = rulesList.get(p).posPosition.get(0);
                  rulesList.forEach(r -> r.removepos(uniquePos));
                }
                p++;
            }
        }
    }

}
