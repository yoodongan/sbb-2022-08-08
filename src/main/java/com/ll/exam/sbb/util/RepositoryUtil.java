package com.ll.exam.sbb.util;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface RepositoryUtil {

    @Transactional
    @Modifying
    @Query(
            value = "SET FOREIGN_KEY_CHECKS = 0",
            nativeQuery = true
    )
    void disableForeignKeyCheck();

    @Transactional
    @Modifying
    @Query(
            value = "SET FOREIGN_KEY_CHECKS = 1",
            nativeQuery = true
    )
    void enableForeignKeyCheck();

    void truncate();  // 추상메서드.

    default void truncateTable() {     // 디폴트 메서드. 구상메서드임.
        disableForeignKeyCheck();
        truncate();   // 이거는 RepositoryUtil 을 상속받는 AnswerRepository, QuestionRepository 에서 오버라이딩해서 만든다.
        enableForeignKeyCheck();
    }

}
