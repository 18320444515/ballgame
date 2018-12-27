package zty.unity.ballgame.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zty.unity.ballgame.entity.Player;

import java.util.Collection;

/**
 * @author tianyi
 * @date 2018-12-25 13:36
 */
public interface PlayerRepository extends JpaRepository<Player,Integer> {
    public Collection<Player> findByName(String name);
}
