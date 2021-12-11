package com.lk.fishblog.repository;
import com.lk.fishblog.model.User;
import com.lk.fishblog.model.UserGroup;
import org.springframework.data.jpa.repository.EntityGraph;

import java.util.List;

public interface UserGroupRepository extends BaseRepository<UserGroup, Long> {
    @EntityGraph(value = "UserGroupEntity")
    List<UserGroup> findUserGroupsByUserList(User user);
}