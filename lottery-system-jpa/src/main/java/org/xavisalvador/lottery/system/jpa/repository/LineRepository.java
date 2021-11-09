package org.xavisalvador.lottery.system.jpa.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.xavisalvador.lottery.system.jpa.entity.Line;

@Repository
public interface LineRepository extends JpaRepository<Line, Long> {
}
