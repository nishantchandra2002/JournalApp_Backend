package com.project2.journalAPP.Services;

import com.project2.journalAPP.entity.User;
import com.project2.journalAPP.entity.journalEntry;
import com.project2.journalAPP.repository.journalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class journalEntryService {
    @Autowired
    private journalEntryRepository JournalEntryRepository;
    @Autowired
    private UserService userService;
    @Transactional
    public void saveEntry(journalEntry JournalEntry, String userName){
        JournalEntry.setDate(LocalDateTime.now());
        User user =userService.findByUserName(userName);
        journalEntry saved = JournalEntryRepository.save(JournalEntry);
        user.getJournalEntries().add(saved);
        userService.saveEntry(user);
    }
    public void saveEntry(journalEntry JournalEntry){
        JournalEntryRepository.save(JournalEntry);
    }
    public List<journalEntry> getAll(){
        return JournalEntryRepository.findAll();
    }
    public Optional<journalEntry> findById(ObjectId id){
        return JournalEntryRepository.findById(id);

    }
    public void deleteById(ObjectId id, String userName){
        User user =userService.findByUserName(userName);
        user.getJournalEntries().removeIf(x -> x.getId().equals(id));
        userService.saveEntry(user);
        JournalEntryRepository.deleteById(id);
    }
}
