package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.*;

public class MapStorage extends AbstractStorage<String> {
    protected final Map<String, Meal> storage = Collections.synchronizedMap(new HashMap<>());

    @Override
    public List<Meal> getCopyAll() {
        return new ArrayList<>(storage.values());
    }

    @Override
    public int size() {
        return storage.size();
    }

    @Override
    public void clear() {
        storage.clear();
    }

    @Override
    protected void doUpdate(String searchKey, Meal meal) {
        storage.put(searchKey, meal);
    }

    @Override
    protected void doSave(Meal meal, String existPosition) {
        storage.put(meal.getUuid(), meal);
    }

    @Override
    protected Meal doGet(String existPosition) {
        return storage.get(existPosition);
    }

    @Override
    protected void doDelete(String existElement) {
        storage.remove(existElement);
    }

    @Override
    protected String getSearchKey(String uuid) {
        return uuid;
    }

    @Override
    protected boolean isExist(String searchKey) {
        return storage.containsKey(searchKey);
    }
}
