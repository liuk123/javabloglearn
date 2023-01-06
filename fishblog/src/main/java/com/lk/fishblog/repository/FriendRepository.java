package com.lk.fishblog.repository;
import com.lk.fishblog.model.Friend;
import com.lk.fishblog.model.Link;

import java.util.List;

public interface FriendRepository extends BaseRepository<Friend, Long> {
    List<Friend> findAllByOrderBySortAsc();
}
