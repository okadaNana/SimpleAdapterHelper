package com.owen.demo.adapter.model;

import java.util.ArrayList;
import java.util.List;

public class DataSource {

    public static List<BeanPerson> generateData() {
        List<BeanPerson> personList = new ArrayList<>();
        personList.add(new BeanPerson("Owen", "Developer"));
        personList.add(new BeanPerson("慕课网", "好网站"));
        personList.add(new BeanPerson("Github", "同性交友"));
        personList.add(new BeanPerson("Android", "iOS"));
        personList.add(new BeanPerson("MacBookPro", "好贵的笔记本"));
        personList.add(new BeanPerson("Amazon", "亚马逊"));
        personList.add(new BeanPerson("微软", "诺基亚"));
        personList.add(new BeanPerson("小米手机", "发烧"));
        personList.add(new BeanPerson("电视台", "CCTV"));
        personList.add(new BeanPerson("锤子", "罗胖子"));
        personList.add(new BeanPerson("游戏手柄", "XBoxOne"));
        personList.add(new BeanPerson("守望屁股", "OverWatch"));
        personList.add(new BeanPerson("热水壶", "Midea"));
        personList.add(new BeanPerson("C++", "好厉害的语言"));

        return personList;
    }
}
