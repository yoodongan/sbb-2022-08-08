package com.ll.exam.sbb;

import com.ll.exam.sbb.util.RepositoryUtil;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import javax.transaction.Transactional;

public interface AnswerRepository extends JpaRepository<Answer, Integer>, RepositoryUtil {

    @Transactional
    @Modifying
    @Query(value = "truncate answer", nativeQuery = true)
    void truncate();





}
