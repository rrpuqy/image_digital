package com.example.digitalimage.common;

import lombok.Getter;

@Getter
public enum Rank {
    LV0(0,0),LV1(1,150),LV2(2,300),LV3(3,500),LV4(4,800),LV5(5,1000),LV6(6,1400);


    private void setLevel(Integer level) {
        this.level = level;
    }

    private Integer level;
    private Integer exp;
    Rank(Integer level,Integer exp){
        this.level = level;
        this.exp = exp;
    }
}
