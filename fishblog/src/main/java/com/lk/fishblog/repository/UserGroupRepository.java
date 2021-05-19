package com.lk.fishblog.repository;
import com.lk.fishblog.model.User;
import com.lk.fishblog.model.UserGroup;

import java.util.List;

public interface UserGroupRepository extends BaseRepository<UserGroup, Long> {
    List<UserGroup> findUserGroupsByUserList(User user);
}