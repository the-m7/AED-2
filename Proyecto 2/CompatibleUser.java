public class CompatibleUser {
    public String name;
    public int sharedInterestsCount;
    public int sharedCharacteristicsCount;
    public UserProfile userProfile;

    public CompatibleUser(String name, int sharedInterestsCount, int sharedCharacteristicsCount,
            UserProfile userProfile) {
        this.name = name;
        this.sharedInterestsCount = sharedInterestsCount;
        this.sharedCharacteristicsCount = sharedCharacteristicsCount;
        this.userProfile = userProfile;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSharedInterestsCount() {
        return sharedInterestsCount;
    }

    public void setSharedInterestsCount(int sharedInterestsCount) {
        this.sharedInterestsCount = sharedInterestsCount;
    }

    public int getSharedCharacteristicsCount() {
        return sharedCharacteristicsCount;
    }

    public void setSharedCharacteristicsCount(int sharedCharacteristicsCount) {
        this.sharedCharacteristicsCount = sharedCharacteristicsCount;
    }

    public UserProfile getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(UserProfile userProfile) {
        this.userProfile = userProfile;
    }
}