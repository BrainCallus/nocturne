/*
 * Copyright by Mike Mirzayanov
 */
package bloggy.captions.dao;

import bloggy.captions.model.Caption;

import java.util.List;

public interface CaptionDao {
    String shaHex(String s);
    Caption find(String shortcutSha1, String locale);
    void insert(Caption caption);
}
