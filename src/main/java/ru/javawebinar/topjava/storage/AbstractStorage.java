package ru.javawebinar.topjava.storage;

import ru.javawebinar.topjava.model.Meal;

import java.util.Collections;
import java.util.List;
import java.util.logging.Logger;

public abstract class AbstractStorage<SK> implements Storage {

    private static final Logger LOG = Logger.getLogger(AbstractStorage.class.getName());

    @Override
    public void update(Meal meal) {
        LOG.info("Update " + meal);
        SK searchKey = getExistedSearchKey(meal.getUuid());
        doUpdate(searchKey, meal);
    }

    @Override
    public void save(Meal meal) {
        LOG.info("Save " + meal);
        SK searchKey = getNotExistedSearchKey(meal.getUuid());
        doSave(meal, searchKey);
    }

    @Override
    public Meal get(String uuid) {
        LOG.info("Get " + uuid);
        SK searchKey = getExistedSearchKey(uuid);
        return doGet(searchKey);
    }

    @Override
    public void delete(String uuid) {
        LOG.info("Delete " + uuid);
        SK searchKey = getExistedSearchKey(uuid);
        doDelete(searchKey);
    }

    @Override
    public List<Meal> getAllSorted() {
        LOG.info("getAllSorted");
        List<Meal> result = getCopyAll();
        Collections.sort(result);
        return result;
    }

    protected abstract void doUpdate(SK searchKey, Meal meal);

    protected abstract void doSave(Meal meal, SK existPosition);

    protected abstract Meal doGet(SK existPosition);

    protected abstract void doDelete(SK existPosition);

    protected abstract SK getSearchKey(String uuid);

    protected abstract boolean isExist(SK searchKey);

    protected abstract List<Meal> getCopyAll();

    private SK getExistedSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (!isExist(searchKey)) {
            LOG.warning("Meal " + uuid + " not exist");
        }
        return searchKey;
    }

    private SK getNotExistedSearchKey(String uuid) {
        SK searchKey = getSearchKey(uuid);
        if (isExist(searchKey)) {
            LOG.warning("Meal " + uuid + " already exist");
        }
        return searchKey;
    }

}
