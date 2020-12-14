import com.sun.xml.internal.ws.api.model.wsdl.WSDLOutput;

import java.io.ObjectInputValidation;
import java.sql.SQLOutput;
import java.util.HashMap;
import java.util.List;

public class Day14 {

    Utils utils = new Utils();

    public void go() {
        List<String> input = utils.readfile("input14.txt");

        HashMap<Integer,Long> memory = new HashMap<>();


        int t=-1;
        String mask ="";
        while (t<input.size()-1){
            t++;
            if (input.get(t).contains("mask")){
                mask = input.get(t).replace("mask = ","");
                continue;
            }

            Integer key = Integer.parseInt(input.get(t).replaceAll(".*\\[","").replaceAll("\\].*",""));

            long value = Long.parseLong(input.get(t).replaceAll("mem.* = ",""));
            String binValue =  "0000000000000000000000000000000000000000000"+ Long.toBinaryString(value);
            binValue = binValue.substring(binValue.length()-36, binValue.length());

            int i = 1;
            for (char c : mask.toCharArray()){
                if (c !='X') {
                   // System.out.println(binValue +"v");
                    binValue = binValue.substring(0, i-1) + String.valueOf(c) + binValue.substring(i, binValue.length());
                    // System.out.println(binValue + "n");
                }
                i++;
            }


            int bi = 1;
            Long res = 0L;
            for (int tc = binValue.length(); tc>0; tc-- ){
                res = res + (Long.parseLong(binValue.substring(tc-1, tc) ) * bi);
                bi = bi *2;
            }
            memory.put(key, res);

            if (res < 0){
                System.out.println(res);
                System.out.printf(binValue);
            }

            System.out.println(binValue);


        }

        final long[] sum = {0};
        memory.forEach((k,v) -> {
            System.out.println(k + "-" +v);
            sum[0] = sum[0] + v;
        });
        System.out.println("total "+ sum[0]);

    }

}
