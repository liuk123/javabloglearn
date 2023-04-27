package com.lk.fishblog.repository;
import com.lk.fishblog.model.Article;
import com.lk.fishblog.model.Bookmark;
import com.lk.fishblog.model.Nav;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BookmarkRepository extends BaseRepository<Bookmark, Long> {
//    List<Bookmark> findByBookmarkCategory_IdIn(List<Long> ids);
    Page<Bookmark> findByBookmarkCategory_IdIn(List<Long> ids, Pageable pageable);
}
