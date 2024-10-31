package ch.martinelli.demo.jooq.data.repository;

import ch.martinelli.demo.jooq.TestConfiguration;
import ch.martinelli.demo.repository.db.tables.records.ClubRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@Import(TestConfiguration.class)
@SpringBootTest
class ClubRepositoryTest {

    @Autowired
    private ClubRepository clubRepository;

    @Test
    void find_by_id() {
        Optional<ClubRecord> optionalClub = clubRepository.findById(1L);

        assertThat(optionalClub).isPresent();
        assertThat(optionalClub.get().getId()).isEqualTo(1L);
    }
}