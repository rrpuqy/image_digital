package com.example.digitalimage.common;

public class Utils {
    public static Integer getLevel(Integer exp){
        System.out.println(Rank.LV5.getExp());
        if(exp >= Rank.LV0.getExp() && exp<Rank.LV1.getExp())
            return 0;
        else if(exp >= Rank.LV1.getExp() && exp<Rank.LV2.getExp())
            return 1;
        else if(exp >= Rank.LV2.getExp() && exp<Rank.LV3.getExp())
            return 2;
        else if(exp >= Rank.LV3.getExp() && exp<Rank.LV4.getExp())
            return 3;
        else if(exp >= Rank.LV4.getExp() && exp<Rank.LV5.getExp())
            return 4;
        else if(exp >= Rank.LV5.getExp() && exp<Rank.LV6.getExp())
            return 5;
        else
            return 6;
    }
}
