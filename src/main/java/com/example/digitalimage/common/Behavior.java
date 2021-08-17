package com.example.digitalimage.common;

import lombok.Getter;

@Getter
public enum Behavior {
    LOOK(1),
    LIKE(2),
    COLLECT(3);

    private Integer value;

    Behavior(Integer data){
        this.value = data;
    }
}
