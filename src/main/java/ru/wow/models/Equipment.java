package ru.wow.models;

import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;


@Entity
@Table(name = "equipment")
@DynamicUpdate
@XmlRootElement
public class Equipment extends Model {

    public Equipment() {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipment_id")
    private long id;

    @Column(name = "equipment_name")
    private String name;

    @Column(name = "equipment_level")
    private int level;

    @Column(name = "equipment_protection")
    private int protection;

    @Column(name = "equipment_parry")
    private int parry;

    @Column(name = "equipment_speed")
    private int speed;

    @Column(name = "equipment_price")
    private int price;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "equipment")
    private List<Personage> equipmentOwners;

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLevel(int level) {
        this.level = level;
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

    public int getProtection() {
        return protection;
    }

    public int getParry() {
        return parry;
    }

    public int getSpeed() {
        return speed;
    }

    public int getPrice() {
        return price;
    }
}
