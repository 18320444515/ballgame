package zty.unity.ballgame.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

/**
 * @author tianyi
 * @date 2018-12-25 13:34
 */
@Entity
@Data
public class Player {

    @Id
    @GeneratedValue
    private int id;

    @NotBlank
    private String name;

    @NotBlank
    private String password;
}
