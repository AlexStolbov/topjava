package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.storage.Storage;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static org.slf4j.LoggerFactory.getLogger;

public class MealEditServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static String EDIT_USER = "jsp/mealEdit.jsp";
    private static String LIST_USER = "jsp/mealsAction.jsp";
    private final Storage meals = MealsUtil.getTestStorage();
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MM/dd/yyyy 'at' hh:mm a");

    @Override
    public void init() throws ServletException {
        super.init();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = LIST_USER;
        String action = request.getParameter("action");

        switch (action) {
            case "delete": {
                meals.delete(request.getParameter("mealUuid"));
            }
            case "listMeal": {
                request.setAttribute("meals", MealsUtil.getWithExcess(meals.getAllSorted(), 2000));
                request.setAttribute("formatter", FORMATTER);
                break;
            }
            case "edit": {
                forward = EDIT_USER;
                Meal meal = meals.get(request.getParameter("mealUuid"));
                request.setAttribute("meal", meal);
                break;
            }
            case "insert": {
                forward = EDIT_USER;
                Meal meal = new Meal(LocalDateTime.MIN, "", 0);
                request.setAttribute("meal", meal);
                break;
            }
        }

        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        String buttonName = request.getParameter("buttonName");
        if (buttonName.equals("save")) {
            String uuid = request.getParameter("uuid");
            String description = request.getParameter("description");
            String datetime = request.getParameter("datetime");
            String calories = request.getParameter("calories");
            Meal meal = new Meal(uuid, LocalDateTime.parse(datetime), description, Integer.parseInt(calories));
            meals.update(meal);
        }
        request.setAttribute("meals", MealsUtil.getWithExcess(meals.getAllSorted(), 2000));
        request.setAttribute("formatter", FORMATTER);
        RequestDispatcher view = request.getRequestDispatcher(LIST_USER);
        view.forward(request, response);
    }
}
