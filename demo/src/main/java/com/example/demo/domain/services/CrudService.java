package com.example.demo.domain.services;

import com.example.demo.domain.objects.DemoUser;
import com.example.demo.domain.repositories.RdsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CrudService {
    private final RdsRepository rds;
    
    public void rdsCrud() {
        int rowsAffected_create = rds.create(new DemoUser(1, "demouser1", "demouser1@localhost", "male", LocalDate.parse("1010-01-01")));
        log.info(Integer.toString(rowsAffected_create));    // 1

        Optional<DemoUser> demoUser = rds.read(1);
        log.info(demoUser.toString());
        // Optional[DemoUser(userid=1, username=demouser1, email=demouser1@localhost, gender=male, birthday=1010-01-01)]

        List<DemoUser> demoUsers = rds.readAll();
        log.info(demoUsers.toString());
        // [DemoUser(userid=0, username=demouser0, email=demouser0@localhost, gender=male, birthday=0001-01-01),
        //  DemoUser(userid=1, username=demouser1, email=demouser1@localhost, gender=male, birthday=1010-01-01)]

        int rowsAffected_update = rds.update(new DemoUser(1, "demouser2", "demouser2@localhost", "female", LocalDate.parse("2020-02-02")));
        log.info(Integer.toString(rowsAffected_update));   // 1

        int rowAffected_delete = rds.delete(1);
        log.info(Integer.toString(rowAffected_delete));   // 1
    }
}
