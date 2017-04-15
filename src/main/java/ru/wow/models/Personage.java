package ru.wow.models;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

@Entity
@Table(name = "personage")
@DynamicUpdate
@XmlRootElement
public class Personage extends Model {

    public Personage() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "personage_id")
    private long id;

    @Column(name = "personage_nickname")
    private String nickname;

    @Column(name = "personage_level")
    private int level;

    @Column(name = "personage_power")
    private int power;

    @Column(name = "personage_protection")
    private int protection;

    @Column(name = "personage_parry")
    private int parry;

    @Column(name = "personage_speed")
    private int speed;

    @Column(name = "personage_race")
    private String race;

    @ManyToOne
    @JoinColumn(name = "equipment_id", nullable=true)
    private Equipment equipment;

    @ManyToOne
    @JoinColumn(name = "weapon_id", nullable=true)
    private Weapon weapon;

    public void setId(long id) {
        this.id = id;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public void setPower(int power) {
        this.power = power;
    }

    public void setProtection(int protection) {
        this.protection = protection;
    }

    public void setParry(int parry) {
        this.parry = parry;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public long getId() {
        return id;
    }

    public String getNickname() {
        return nickname;
    }

    public int getLevel() {
        return level;
    }

    public int getPower() {
        return power;
    }

    public int getProtection() {
        return protection;
    }

    public int getParry() {
        return parry;
    }

    public int getSpeed() {
        return speed;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public String getRace() {
        return race;
    }

}
