package com.example.taskdive;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.*;
import java.util.*;


public class AthleteSearch
{
    public static void main(String[] args) throws IOException
    {
        ObjectMapper mapper = new ObjectMapper();

     List<Country>countries = mapper.readValue(
             new File("Athlete&Contest/Athlete/athletes.json"),
             new TypeReference<>() {
             }
     );
      for(Country country : countries)
      {
          System.out.println("=== 国家: " + country.getCountryName() + " ===");
          if (country.getParticipations() != null) {
              for (Athlete athlete : country.getParticipations()) {
                  athlete.setCountryName(country.getCountryName());
                  athlete.setCountryCode(country.getCountryCode());
                  System.out.println(athlete);
              }
          }
      }
    }
}
