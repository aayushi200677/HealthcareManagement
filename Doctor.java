package hospital;

public class Doctor {
    private int id;
    private String name;
    private String specialization;
    private int experience;

    // Constructor
    public Doctor(int id, String name, String specialization, int experience) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.experience = experience;
    }

    // Getters and Setters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getSpecialization() { return specialization; }
    public int getExperience() { return experience; }

    public void setId(int id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    public void setExperience(int experience) { this.experience = experience; }

    // toString for easy printing
    @Override
    public String toString() {
        return id + " | " + name + " | " + specialization + " | " + experience;
    }
}
