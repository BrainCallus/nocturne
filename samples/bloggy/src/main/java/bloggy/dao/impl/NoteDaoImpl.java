package bloggy.dao.impl;

import bloggy.dao.NoteDao;
import bloggy.model.Entity;
import bloggy.model.Note;
import com.google.inject.Singleton;

import java.util.List;

@Singleton
public class NoteDaoImpl<T extends Note, OwnT extends Entity> extends ApplicationDaoImpl<T> implements NoteDao<T, OwnT> {
    @Override
    public T find(long id) {
        return super.find(id);
    }

    @Override
    public List<T> findBy(OwnT entity) {
        return super.findBy(getEntityClass(entity) + "Id=? AND NOT deleted ORDER BY updateTime DESC", entity.getId());
    }

    @Override
    public List<T> findAll() {
        return findBy("NOT deleted ORDER BY updateTime DESC");
    }

    private String getEntityClass(OwnT entity) {
        return entity.getClass().getSimpleName().toLowerCase();
    }

}
