package com.example.taskdive;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.*;

public class SingleSearch {
    public static void main(String[] args)
    {
        ObjectMapper mapper = new ObjectMapper();
        List<Disciplines>Discipline = new ArrayList<>();

        System.out.println("Men's 10m Platform\nMen's 3m Springboard" +
                "\nWomen's 10m Platform\nWomen's 3m Springboard");
        System.out.print("Please enter the Discipline Name: ");
        Scanner input = new Scanner(System.in);
        String SearchName = input.nextLine();

        SearchName = ProjectMatcher.matchDiscipline(SearchName);

        for(File file : new File("Athlete&Contest/Contest/Single").listFiles())   //遍历所有文件数据
        {
            List<Phase>Phases = new ArrayList<>();
            try {
                Disciplines parts = mapper.readValue(file,
                        new TypeReference<>() {
                        }
                );
                JsonNode root = mapper.readTree(file);

                if(parts.getDisciplineName().equals(SearchName))
                {
                    parts.setPhases(Phases);                                    // 赛事的各个阶段


                    JsonNode Heats = root.path("Heats");
                    for (JsonNode item : Heats)      // 赛事阶段处理
                    {
                        Phase phase = new Phase();
                        List<RankPoint> RankPoints = new ArrayList<>();

                        phase.setRankPoint(RankPoints);

                        phase.setHeatName(item.path("Name").asText());         //设置赛事阶段名称
                        phase.setPhaseName(item.path("PhaseName").asText());

                        JsonNode Result = item.path("Results");

                        for (JsonNode item2 : Result) {
                            List<Athlete> Athle = new ArrayList<>();
                            RankPoint rankPoint = mapper.treeToValue(item2, RankPoint.class);
                            rankPoint.setAthletes(Athle);
                            Athlete athlete = mapper.treeToValue(item2, Athlete.class);
                            List<String> DivePoints = new ArrayList<>();
                            rankPoint.setDivePoints(DivePoints);
                            JsonNode Dive = item2.path("Dives");
                            for (JsonNode item3 : Dive)        //装入各跳数据
                            {
                                rankPoint.addDivePoint(item3.path("DivePoints").asText());
                            }
                            rankPoint.addAthlete(athlete);           //装入运动员数据
                            phase.addRankPoint(rankPoint);
                        }

                        parts.addPhase(phase);             //赛程增加
                    }

                    Discipline.add(parts);
                }
               }
            catch (Exception e) {
                e.printStackTrace();
            }

        }

        for(Disciplines disciplines : Discipline)                   //打印数据
        {
            OutputClass.OutputDisciplines(disciplines);
        }
    }
}
