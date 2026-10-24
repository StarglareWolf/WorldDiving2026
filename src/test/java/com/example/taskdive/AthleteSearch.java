package com.example.taskdive;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.*;


public class AthleteSearch {
    private static void CountryOrder(List<Country> countries) {
        int total = countries.size();
        for (int i = 0; i < total; i++) {
            for (int j = i + 1; j < total; j++) {
                if (countries.get(i).getCountryName().compareTo(countries.get(j).getCountryName()) > 0) {
                    String temp = countries.get(i).getCountryName();
                    countries.get(i).setCountryName(countries.get(j).getCountryName());
                    countries.get(j).setCountryName(temp);
                }
            }
        }
    }

    private static void AthleteOrder(List<Athlete> athletes)
    {
        int total = athletes.size();
        for (int i = 0; i < total; i++) {
            for (int j = i + 1; j < total; j++) {
                if (athletes.get(i).getPreferredFirstName().compareTo(athletes.get(j).getPreferredFirstName()) > 0) {
                    String temp = athletes.get(i).getPreferredFirstName();
                    athletes.get(i).setPreferredFirstName(athletes.get(j).getPreferredFirstName());
                    athletes.get(j).setPreferredFirstName(temp);
                }
            }
        }
    }

    public static void main(String[] args) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();

     List<Country>countries = mapper.readValue(
             new File("Athlete&Contest/Athlete/athletes.json"),
             new TypeReference<>() {
             }
     );

     CountryOrder(countries);

      for(Country country : countries)
      {
          System.out.println("=== 国家: " + country.getCountryName() + " ===");
          if (country.getParticipations() != null) {
              AthleteOrder(country.getParticipations());
              for (Athlete athlete : country.getParticipations()) {
                  athlete.setCountryName(country.getCountryName());
                  athlete.setCountryCode(country.getCountryCode());
//                  System.out.println(athlete);
                  athlete.OutputAthlete();
              }
          }
      }
    }
}
