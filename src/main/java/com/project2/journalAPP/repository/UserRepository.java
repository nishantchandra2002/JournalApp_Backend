package com.project2.journalAPP.repository;
import com.project2.journalAPP.entity.User;
import com.project2.journalAPP.entity.journalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, ObjectId>{
    User findByUserName(String username);
}
