package bloggy.web.frame;

import bloggy.dao.UserDao;
import bloggy.model.Note;
import bloggy.model.User;
import com.google.inject.Inject;

public class NoteViewFrame<T extends Note> extends ApplicationFrame {

    @Inject
    private UserDao userDao;

    @Inject
    private UserpicFrame userpicFrame;

    private boolean shortMode;
    private T note;

    public void setShortMode(boolean shortMode) {
        this.shortMode = shortMode;
    }

    public void setNote(T note) {
        this.note = note;
    }

    @Override
    public void action() {
        put("shortMode", shortMode);
        put(note.getClass().getSimpleName().toLowerCase(), note);
        User noteUser = userDao.find(note.getUserId());
        put(note.getClass().getSimpleName().toLowerCase() + "User", noteUser);
        userpicFrame.setUserpicUser(noteUser);
        parse("userpicFrame", userpicFrame);
    }
}
