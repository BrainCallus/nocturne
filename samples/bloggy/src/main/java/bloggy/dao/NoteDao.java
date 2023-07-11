package bloggy.dao;

import bloggy.model.Entity;
import bloggy.model.Note;

import java.util.List;

public interface NoteDao<T extends Note, OwnT extends Entity> {
    T find(long id);

    List<T> findBy(OwnT entity);

    List<T> findAll();

    void insert(T note);

    void update(T note);

}
