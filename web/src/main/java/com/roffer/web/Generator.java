package com.roffer.web;

import com.roffer.common.utils.CodeUtils;

/**
 * @author Dulongfei
 * @description 描述
 * @date 2022/4/11 10:29
 *
 */
public class Generator {
    public static void main(String[] args) {
        new CodeUtils(
                Generator.class.getResourceAsStream("/mybatiesplus-config.yml"))
                .doGenerator();
    }
}
