package com.example.taskdive;
import java.util.*;

// 每个运动员单个项目单场比赛结果，实现基本 getter、setter、constructor、toString方法
public class Contest {
    private String totalPoints;
    private int rank;
    private String fullName;
    private List<String> dives;

    public Contest(){}

    public Contest(String totalPoints, int rank, String fullName, List<String> dives){
        this.totalPoints = totalPoints;
        this.rank = rank;
        this.fullName = fullName;
        this.dives = dives;
    }
}
