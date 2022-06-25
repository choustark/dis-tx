package com.chou.common_module.constant;

import lombok.Getter;

/**
 * @author Axel
 * @version 1.0
 * @className TrueOrFalse
 * @description 布尔常量
 * @date 2022/4/4 18:18
 */

@Getter
public enum  TrueOrFalse {
    TRUE(1,"TRUE"),
    FALSE(0,"FALSE");

    private final Integer value;
    private final String name;


    TrueOrFalse(int i, String aTrue) {
        this.value = i;
        this.name = aTrue;
    }
}
