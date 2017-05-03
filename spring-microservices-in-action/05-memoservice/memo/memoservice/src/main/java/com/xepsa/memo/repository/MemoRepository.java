package com.xepsa.memo.repository;

import com.xepsa.memo.model.Memo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemoRepository extends CrudRepository<Memo, String> {

    public List<Memo> findByUserId(String userId);

    public Memo findById(String id);

    public List<Memo> findByUserIdAndTitle(String userId, String name);

}
