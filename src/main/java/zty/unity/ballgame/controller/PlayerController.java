package zty.unity.ballgame.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zty.unity.ballgame.DTO.CommonDTO;
import zty.unity.ballgame.entity.Player;
import zty.unity.ballgame.repository.PlayerRepository;
import zty.unity.ballgame.utils.CommonDTOUtil;

import java.util.Collection;

/**
 * @author tianyi
 * @date 2018-12-25 13:37
 * Basic API of player.
 */
@RestController
@RequestMapping("/player")
public class PlayerController {

    @Autowired
    private PlayerRepository playerRepository;

    @PostMapping("/add")
    public CommonDTO register(Player player){
        return CommonDTOUtil.success(playerRepository.save(player));
    }

    @PostMapping("/one")
    public CommonDTO one(Player player){
        return CommonDTOUtil.success(playerRepository.findById(player.getId()).get());
    }

    @PostMapping("/login")
    public CommonDTO login(Player player){
        Collection<Player> resultset = playerRepository.findByName(player.getName());
        if (resultset.iterator().hasNext()){
            Player find = resultset.iterator().next();
            if (find.getPassword().equals(player.getPassword())){
                return CommonDTOUtil.success(find);
            }else {
                return CommonDTOUtil.error(444,"密码输入错误，请重输");
            }
        }else {
            return CommonDTOUtil.error(404,"账号不存在，请注册");
        }
    }

}
