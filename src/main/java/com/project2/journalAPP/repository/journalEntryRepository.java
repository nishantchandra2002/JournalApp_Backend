package com.project2.journalAPP.repository;
import com.project2.journalAPP.entity.journalEntry;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface journalEntryRepository extends MongoRepository<journalEntry, ObjectId>{
}
