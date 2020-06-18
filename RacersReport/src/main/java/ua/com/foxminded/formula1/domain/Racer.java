package ua.com.foxminded.formula1.domain;

import java.time.Duration;
import java.util.Objects;

public class Racer implements Comparable<Racer> {
   private final String name;

   private final String team;

   private final Duration duration;

   private Racer(Builder builder) {
      this.name = builder.name;
      this.team = builder.team;
      this.duration = builder.duration;
   }

   public String getName() {
      return name;
   }

   public String getTeam() {
      return team;
   }

   public Duration getDuration() {
      return duration;
   }

   public static Builder builder() {
      return new Builder();
   }
   
   @Override
   public int compareTo(Racer racer) {
      return duration.compareTo(racer.duration);
   }

   @Override
   public String toString() {
      return name + " " + team;
   }

   @Override
   public boolean equals(Object obj) {
      if (obj == this) {
         return true;
      }
      if (obj == null || obj.getClass() != this.getClass()) {
         return false;
      }

      Racer racer = (Racer)obj;
      
      return Objects.equals(name, racer.name) && 
             Objects.equals(team, racer.team) && 
             Objects.equals(duration,racer.duration);
   }
   
   @Override
   public int hashCode() { 
     return Objects.hash(name, team, duration);
   }

   public static class Builder {
      private String name;

      private String team;

      private Duration duration;

      private Builder() {
      }

      public Builder withName(String name) {
         this.name = name;
         return this;
      }

      public Builder withTeam(String team) {
         this.team = team;
         return this;
      }

      public Builder withDuration(Duration duration) {
         this.duration = duration;
         return this;
      }

      public Racer build() {
         return new Racer(this);
      }
   }
}
