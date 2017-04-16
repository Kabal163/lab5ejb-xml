package ru.wow.models;

import org.hibernate.annotations.DynamicUpdate;
import ru.wow.ejb.interfaces.impls.WeaponBean;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name="weapon")
@DynamicUpdate
@XmlRootElement
public class Weapon extends Model {

    public Weapon() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "weapon_id")
    private long id;

    @Column(name = "weapon_name", nullable = false)
    private String name;

    @Column(name = "weapon_level")
    private int level;

    @Column(name = "weapon_power")
    private int power;

    @Column(name = "weapon_parry")
    private int parry;

    @Column(name = "weapon_price")
    private int price;

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setParry(int parry) {
        this.parry = parry;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public int getPower() {
        return power;
    }

    public int getParry() {
        return parry;
    }

    public int getPrice() {
        return price;
    }

}
