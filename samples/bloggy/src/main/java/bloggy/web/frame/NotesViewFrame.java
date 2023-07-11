package bloggy.web.frame;

import bloggy.dao.NoteDao;
import bloggy.model.Entity;
import bloggy.model.Note;
import com.google.inject.Inject;

import java.util.List;

public abstract class NotesViewFrame<T extends Note, D extends NoteDao<T, E>, E extends Entity> extends ApplicationFrame {

    @Inject
    protected D noteDao;

    protected E notesEntity;
    private String className;

    public void setNotesEntity(E notesEntity) {
        this.notesEntity = notesEntity;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public void action() {
        putNotes(notesEntity == null ? noteDao.findAll() : noteDao.findBy(notesEntity));
    }


    private void putNotes(List<T> notes) {
        for (T note : notes) {
            parseNote(note);
        }
        putNoteIds(notes);
    }

    protected abstract void parseNote(T note);

    private void putNoteIds(List<T> notes) {
        put(className + "Properties", getProperties(notes));
    }

    protected abstract List<?> getProperties(List<T> notes);


}
