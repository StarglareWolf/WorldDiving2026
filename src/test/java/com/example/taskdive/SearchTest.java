package com.example.taskdive;
import java.util.*;

public class SearchTest {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        List<Disciplines>Discipline = new ArrayList<>();

        System.out.println("athlete\nDiscipline:");
        System.out.println("""
                Men's 10m Platform
                Men's 3m Springboard
                Women's 10m Platform
                Women's 3m Springboard""");
        System.out.println("""
                Men's 10m Synchronised
                Mixed 3m & 10m Team
                Men's 3m Synchronised
                Women's 10m Synchronised
                Women's 3m Synchronised""");

        System.out.print("Please enter the task you want to search: ");

        String SearchName = input.nextLine();
        SearchName = ProjectMatcher.matchDiscipline(SearchName);
        if (SearchName != null) {
            if (SearchName.equals(ProjectMatcher.getProjectName(0))) {
                AthleteSearch.SearchFor();
            }
             else
             {
                GroupSearch.SearchFor(Discipline);
                SingleSearch.SearchFor(Discipline);
                for (Disciplines discipline : Discipline) {
                    if (discipline.getDisciplineName().equals(SearchName)) {
                        OutputClass.OutputDisciplines(discipline);
                    }
                }
            }
        }
    }
}
