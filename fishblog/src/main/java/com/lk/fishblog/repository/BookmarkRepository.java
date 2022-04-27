package com.lk.fishblog.repository;
import com.lk.fishblog.model.Bookmark;
import com.lk.fishblog.model.Nav;

import java.util.List;

public interface BookmarkRepository extends BaseRepository<Bookmark, Long> {
    List<Bookmark> findByBookmarkCategory_IdIn(List<Long> ids);
}
