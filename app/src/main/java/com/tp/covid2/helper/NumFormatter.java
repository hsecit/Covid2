package com.tp.covid2.helper;

public class NumFormatter {
    String number;
    public StringBuilder str;
    public StringBuilder str_origin;
    public NumFormatter(String number) {
        this.number = number;
        str = new StringBuilder(number);
        str_origin = new StringBuilder(number);
    }
    public String formatNum(){
        int inc = 0;
        str.reverse();
        str_origin.reverse();



        for(int i=1;i<=str.length();i++){
            if(i%3==0){
                str_origin.insert(i+inc, " ");
                inc++;
            }
        }
        if (str_origin.charAt(str_origin.length()-1) == '.'){
            str_origin.deleteCharAt(str_origin.length()-1);
        }


        return str_origin.reverse().toString();
    }


}
