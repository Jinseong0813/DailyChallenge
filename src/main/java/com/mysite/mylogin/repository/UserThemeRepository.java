package com.mysite.mylogin.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.mysite.mylogin.entity.UserThemeEntity;
import com.mysite.mylogin.entity.UserEntity;
import com.mysite.mylogin.entity.ThemeEntity;

import java.util.Optional;

public interface UserThemeRepository extends JpaRepository<UserThemeEntity, Long> {
    Optional<UserThemeEntity> findByUserAndTheme(UserEntity user, ThemeEntity theme);
}
