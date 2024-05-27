public class CompatibleUser {
    public final String name;
    public final int sharedInterestsCount;
    public final int sharedCharacteristicsCount;

    public CompatibleUser(String name, int sharedInterestsCount, int sharedCharacteristicsCount) {
        this.name = name;
        this.sharedInterestsCount = sharedInterestsCount;
        this.sharedCharacteristicsCount = sharedCharacteristicsCount;
    }

    @Override
    public String toString() {
        return name.toUpperCase() + "\n  Cantidad de intereses compartidos = " + sharedInterestsCount +
                "\n  Cantidad de caracteristicas compartidas = " + sharedCharacteristicsCount
                + "\n <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3 <3";
    }
}