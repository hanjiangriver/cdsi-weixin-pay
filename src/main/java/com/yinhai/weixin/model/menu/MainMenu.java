package com.yinhai.weixin.model.menu;

import java.util.Arrays;

/**
 * 菜单
 * Created by 张汉江 on 2018/3/4
 */
public class MainMenu {
    private Button[] button;

    public Button[] getButton() {
        return button;
    }

    public void setButton(Button[] button) {
        this.button = button;
    }

    @Override
    public String toString() {
        return "Menu{" +
                "button=" + Arrays.toString(button) +
                '}';
    }
}
