import java.util.List;

public class Day6 {

    Utils utils = new Utils();


    String check = "abcdefghijklmnopqrstuvwxyz";


    public void go() {
        List<String> input = utils.readfile("input6.txt");


        int t = 1;
        int score = 0;

        String yes = input.get(0);
        while (t < input.size()){
            String line = input.get(t);
            if (line.equals("")){
                System.out.println(yes.length() + yes);
                score = score + yes.length();
                if (t<input.size()-1) {
                    yes = input.get(t+1);
                } else {
                    yes = "";
                }
                t++;
            } else {
                for (int c = 0 ; c< yes.length();c++){
                    if (!line.contains(yes.substring(c, c+1))){
                        yes = yes.replace(yes.substring(c,c+1),"");
                    }
                }
            }

            t++;
        }
        System.out.println(yes.length() + yes);

        System.out.println("Score " + score);

    }

}
