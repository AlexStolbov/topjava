package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExceed;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> mealList = Arrays.asList(
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,10,0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,13,0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 30,20,0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,10,0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,13,0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2015, Month.MAY, 31,20,0), "Ужин", 510)
        );
        List<UserMealWithExceed> res = getFilteredWithExceeded(mealList, LocalTime.of(7, 0), LocalTime.of(12,0), 2000);
        List<UserMealWithExceed> res2 = getFilteredWithExceededStream(mealList, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
    }

    public static List<UserMealWithExceed>  getFilteredWithExceeded(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> summCalories = new HashMap<>();
        for (UserMeal userMeal : mealList) {
            LocalDate mealDate = userMeal.getDateTime().toLocalDate();
            summCalories.put(mealDate, summCalories.getOrDefault(mealDate, 0) + userMeal.getCalories());
        }
        List<UserMealWithExceed> res = new ArrayList<>();
        for (UserMeal userMeal : mealList) {
            if (TimeUtil.isBetween(userMeal.getDateTime().toLocalTime(), startTime, endTime)) {
                res.add(new UserMealWithExceed(userMeal.getDateTime(),
                        userMeal.getDescription(),
                        userMeal.getCalories(),
                        summCalories.get(userMeal.getDateTime().toLocalDate()) > caloriesPerDay));
            }
        }
        return res;
    }

    public static List<UserMealWithExceed> getFilteredWithExceededStream(List<UserMeal> mealList, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        Map<LocalDate, Integer> summCalories = mealList.stream().collect(Collectors.groupingBy(um -> um.getDateTime().toLocalDate(), Collectors.summingInt(UserMeal::getCalories)));
        return mealList.stream()
                .filter(um -> um.getDateTime().toLocalTime().isAfter(startTime.minusMinutes(1)) && um.getDateTime().toLocalTime().isBefore(endTime.plusMinutes(1)))
                .map(um -> new UserMealWithExceed(um.getDateTime(),
                        um.getDescription(),
                        um.getCalories(),
                        summCalories.get(um.getDateTime().toLocalDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

}
