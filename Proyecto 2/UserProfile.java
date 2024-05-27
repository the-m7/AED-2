import java.util.List;

public class UserProfile {
    public String name;
    public int age;
    public String region;
    public List<String> interests;
    public List<String> characteristics;

    public UserProfile(String name, int age, String region, List<String> interests, List<String> characteristics) {
        this.name = name;
        this.age = age;
        this.region = region;
        this.interests = interests;
        this.characteristics = characteristics;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<String> getInterests() {
        return interests;
    }

    public void setInterests(List<String> interests) {
        this.interests = interests;
    }

    public List<String> getCharacteristics() {
        return characteristics;
    }

    public void setCharacteristics(List<String> characteristics) {
        this.characteristics = characteristics;
    }
}
