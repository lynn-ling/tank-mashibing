package com.company;

//定义一个FireStrategy接口，策略
public interface FireStrategy {
    //开火的时候要把Tank传进来，否则不直到子弹在什么位置上
    void fire(Tank t);
}
