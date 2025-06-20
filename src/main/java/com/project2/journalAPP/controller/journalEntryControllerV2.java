package com.project2.journalAPP.controller;
import com.project2.journalAPP.Services.UserService;
import com.project2.journalAPP.Services.journalEntryService;
import com.project2.journalAPP.entity.User;
import com.project2.journalAPP.entity.journalEntry;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/journal")
public class journalEntryControllerV2 {
    @Autowired
    private journalEntryService JournalEntryService;
    @Autowired
    private UserService userService;


    @GetMapping("/{userName}")
    public ResponseEntity<?>getAllJournalEntriesOfUser(@PathVariable String userName){
        User user =userService.findByUserName(userName);
        List<journalEntry>all=user.getJournalEntries();
        if(all!=null && !all.isEmpty()){
            return new ResponseEntity<>(all,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);

    }
    @PostMapping("/{userName}")
    public ResponseEntity<journalEntry>createEntry(@RequestBody journalEntry myEntry,@PathVariable String userName){
        try {

            JournalEntryService.saveEntry(myEntry,userName);
            return new ResponseEntity<>(myEntry,HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        }

    }
    @GetMapping("/id/{myId}")
    public ResponseEntity<journalEntry> getJournalEntryById(@PathVariable ObjectId myId){
       Optional<journalEntry>jo=JournalEntryService.findById(myId);
       if(jo.isPresent()){
           return new ResponseEntity<>(jo.get(), HttpStatus.OK);
       }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @DeleteMapping("/id/{userName}/{myId}")
    public ResponseEntity<?> deletejournalEntryById(@PathVariable ObjectId myId,@PathVariable String userName){
        JournalEntryService.deleteById(myId,userName);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);


    }
    @PutMapping("/id/{userName}/{myId}")
    public ResponseEntity<?> updateJournalEntryById(
            @PathVariable ObjectId myId,
            @PathVariable String userName,
            @RequestBody journalEntry newEntry)
    {
        journalEntry old=JournalEntryService.findById(myId).orElse(null);
        if(old!=null){
            old.setTittle(newEntry.getTittle()!=null && !newEntry.getTittle().equals("")?newEntry.getTittle():old.getTittle());
            old.setContent(newEntry.getContent()!=null && !newEntry.getContent().equals("")?newEntry.getContent():old.getContent());
            JournalEntryService.saveEntry(old);
            return new ResponseEntity<>(old,HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
