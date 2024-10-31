package ch.martinelli.demo.jooq.data.repository;

import ch.martinelli.demo.repository.db.tables.Athlete;
import ch.martinelli.demo.repository.db.tables.records.AthleteRecord;
import org.jooq.DSLContext;
import org.springframework.stereotype.Repository;

import static ch.martinelli.demo.repository.db.tables.Athlete.ATHLETE;

@Repository
public class AthleteRepository extends JooqRepository<Athlete, AthleteRecord, Long> {

    public AthleteRepository(DSLContext dslContext) {
        super(dslContext, ATHLETE);
    }
}