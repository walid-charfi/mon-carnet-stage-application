package com.fm.mcs.repository;

import com.fm.mcs.domain.StageRadiologie;
import java.util.List;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data SQL repository for the StageRadiologie entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StageRadiologieRepository extends JpaRepository<StageRadiologie, Long> {
    @Query("select stageRadiologie from StageRadiologie stageRadiologie where stageRadiologie.user.login = ?#{principal.username}")
    List<StageRadiologie> findByUserIsCurrentUser();
}
