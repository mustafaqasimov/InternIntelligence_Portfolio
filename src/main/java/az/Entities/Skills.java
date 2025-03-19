package az.Entities;

public class Skills {
    private int id;
    private String skill_name;

    public Skills(String skill_name) {
        this.skill_name = skill_name;
    }

    public Skills(int id, String skill_name) {
        this.id = id;
        this.skill_name = skill_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSkill_name() {
        return skill_name;
    }

    public void setSkill_name(String skill_name) {
        this.skill_name = skill_name;
    }
}
