package ass;

import org.junit.Test;

import java.math.BigInteger;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyTest {
   @Test
    public void test(){
       System.out.println(myAtoi("words and 987"));
   }
    public static int myAtoi(String str) {

        char[] op=str.trim().toCharArray();
        StringBuilder stringBuilder=new StringBuilder();
        for(int i=0;i<op.length;i++){
            if((op[i]>='0'&&op[i]<='9')||op[i]=='-'){
                stringBuilder.append(op[i]);
            }
        }
        String s=stringBuilder.toString();
        if(s.length()==0||s==null){
            return 0;
        }
        if(!((s.charAt(0)>='0'&&s.charAt(0)<='9')||s.charAt(0)=='-')){
            return 0;
        }
        if(s.charAt(0)=='-'){
            try{
                return Integer.valueOf(s);
            }catch(NumberFormatException e){
                return Integer.MIN_VALUE;
            }
        }
        else{
            try{
                return Integer.valueOf(s);
            }catch(NumberFormatException e){
                return Integer.MAX_VALUE;
            }
        }
    }
}

