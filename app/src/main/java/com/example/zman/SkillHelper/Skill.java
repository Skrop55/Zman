package com.example.zman.SkillHelper;

public class Skill {
    int xp;
    String type, level;
    long id;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getXp() {
        return xp;
    }

    public void setXp(int xp) {
        this.xp = xp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public Skill(int xp, String level, String type) {
        this.xp = xp;
        this.level = level;
        this.type = type;
    }
    Level i = Level.LOW;
}

enum Level {
    LOW,
    MEDIUM,
    HIGH
}
