package com.example.zman.Backend;

public class IsCheck {
    private String str;

    public IsCheck(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    public void setStr(String str) {
        this.str = str;
    }

    public String checkEmail() {
        String msg = ""; // toast
        String fixStr = ""; // what needs to be changed
        boolean ok = true;

        for (char i = 'a'; i < 'z'; i++)
            fixStr += i;


        for (char J = '@'; J < 'Z'; J++) // check A-Z but also @
            fixStr += J;


        for ( int K = 0; K < 9; K++) // size of pass less than 10
            fixStr += K;


        fixStr += ".";


        if (!(this.str.indexOf('@') >= 0)) {
            msg += "Email must have @" + "\n";
        }
        if (!(this.str.indexOf('@') == this.str.lastIndexOf('@'))) {
            msg += "Email has more than one @" + "\n";
        }

        int r = 0;
        while (r < this.str.length() && ok) {
            if (fixStr.indexOf(this.str.charAt(r)) < 0) {
                ok = false;
                msg += "invalid char in Email, " + fixStr + "\n";
            }
            r++;
        }

        return msg;
    }
}
