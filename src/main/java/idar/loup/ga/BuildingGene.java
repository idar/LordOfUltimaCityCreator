package idar.loup.ga;

import idar.loup.game.BuildingCode;
import org.jgap.*;

public class BuildingGene extends BaseGene implements Gene, java.io.Serializable{
    private BuildingCode building;

    public BuildingGene(Configuration a_configuration, BuildingCode building) throws InvalidConfigurationException {
        super(a_configuration);
        this.building = building;
    }

    @Override
    protected Object getInternalValue() {
        return building.value();
    }

    @Override
    protected Gene newGeneInternal() {
        try {
            return new BuildingGene(this.getConfiguration(),building);
        } catch (InvalidConfigurationException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public void setAllele(Object a_newValue) {
        building = BuildingCode.fromValue((String) a_newValue);
    }

    @Override
    public String getPersistentRepresentation() throws UnsupportedOperationException {
          return building.value();
    }

    @Override
    public void setValueFromPersistentRepresentation(String a_representation) throws UnsupportedOperationException, UnsupportedRepresentationException {
        setAllele(a_representation);
    }

    @Override
    public void setToRandomValue(RandomGenerator a_numberGenerator) {
        if(building.isUntouchable()) return;
        building = BuildingCode.get(a_numberGenerator);
    }

    @Override
    public void applyMutation(int index, double a_percentage) {
        setToRandomValue(this.getConfiguration().getRandomGenerator());
    }

    @Override
    public int compareTo(Object o) {
        return building.value().compareTo((String) ((BuildingGene)o).getInternalValue());
    }

    public BuildingCode getBuildingCode() {
        return building;
    }
}
