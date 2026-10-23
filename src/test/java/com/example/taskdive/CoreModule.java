package com.example.taskdive;
import java.util.*;


//提高代码的可维护性和可扩展性，将基本功能独立成一个模块
public interface CoreModule {
    // 查询所有运动员的基本信息
    void displayAllPlayersInfo();
    // 获取contestName项目中决赛结果并输出
    void displayResults(String contestName);
    // 给定比赛项目和比赛类型，并将相应结果封装到集合 List 中返回
    List<Contest> displayResults(String contestName, int type);
    // 获取 contestName 项目中的所有比赛结果并输出
    void displayDetailedResults(String contestName);
}
