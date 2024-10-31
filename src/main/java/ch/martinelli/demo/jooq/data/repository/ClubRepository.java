package ch.martinelli.demo.jooq.data.repository;

import ch.martinelli.demo.repository.db.tables.Club;
import ch.martinelli.demo.repository.db.tables.records.ClubRecord;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import static ch.martinelli.demo.repository.db.tables.Club.CLUB;

@Repository
public class ClubRepository extends JooqRepository<Club, ClubRecord, Long> {

    public ClubRepository(DSLContext dslContext) {
        super(dslContext, CLUB);
    }
}