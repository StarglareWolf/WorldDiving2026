package com.example.taskdive;

public class OutputClass {

    public static void OutputAthlete(Athlete athlete)
    {
        String sex = (athlete.getGender() == 0) ? "Male" : "Female";
        athlete.setCountryName((athlete.getCountryName()  == null) ?
                Athlete.CountryMap.get(athlete.getCountryCode()) : athlete.getCountryName());
        System.out.println("Full Name: " + athlete.getPreferredLastName() + " " + athlete.getPreferredFirstName());
        System.out.println("Gender: " + sex);
        System.out.println("Country: " + athlete.getCountryName());
        System.out.println("----");
    }

    public static void OutputRankPoint(RankPoint rankPoint)
    {
        System.out.println("Rank: " + rankPoint.getDiveRank());
        for(Athlete athlete: rankPoint.getAthletes())
        {
            OutputAthlete(athlete);
        }
        System.out.print("DivePoints: ");
        int total = rankPoint.getDivePoints().size();
        if(rankPoint.getCarryOverPoints() != null)
        {
            System.out.print(rankPoint.getCarryOverPoints() + " + ");
        }
        for(int i = 0;i < total; i++)
        {
            System.out.print(rankPoint.getDivePoints().get(i));
            if(i != total - 1)
                System.out.print(" + ");
            else
                System.out.print(" = ");
        }
        System.out.println(rankPoint.getTotalPoints());
        System.out.println("###########");
    }

    public static void OutputPhase(Phase phase)
    {
        System.out.println("Phase: " + phase.getPhaseName());
        for(RankPoint point: phase.getRankPoint())
        {
           OutputRankPoint(point);
        }
    }

    public static void OutputDisciplines(Disciplines disciplines)
    {
        System.out.println("------------DisciplineName: " + disciplines.getDisciplineName() + "------------");
        for(Phase phase: disciplines.getPhases())
        {
            OutputPhase(phase);
        }
    }
}
