package ru.javawebinar.topjava.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;
import java.util.UUID;

public class Meal implements Comparable<Meal> {
    private final LocalDateTime dateTime;

    private final String description;

    private final int calories;

    private final String uuid;

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(UUID.randomUUID().toString(), dateTime, description, calories);
    }

    public Meal(String uuid, LocalDateTime dateTime, String description, int calories) {
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.uuid = uuid;
    }

    public String getUuid() {
        return uuid;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    @Override
    public int compareTo(Meal o) {
        int comp = this.dateTime.compareTo(o.dateTime);
        return comp != 0 ? comp : uuid.compareTo(o.getUuid());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Meal meal = (Meal) o;
        return uuid.equals(meal.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
