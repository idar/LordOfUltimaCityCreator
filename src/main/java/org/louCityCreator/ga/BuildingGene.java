package org.louCityCreator.ga;

import org.louCityCreator.game.BuildingCode;
import org.jgap.*;

public class BuildingGene extends BaseGene implements Gene, java.io.Serializable {
    private BuildingCode building;
    private BuildingCode initialBuilding;

    public BuildingGene(Configuration a_configuration, BuildingCode building) throws InvalidConfigurationException {
        super(a_configuration);
        this.building = building;
        this.initialBuilding = building;
    }

    @Override
    protected Object getInternalValue() {
        return building.value();
    }

    @Override
    protected Gene newGeneInternal() {
        try {
            return new BuildingGene(this.getConfiguration(), BuildingCode.get(this.getConfiguration().getRandomGenerator(), initialBuilding));
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
        if (building.isUntouchable()) return;
        setToValue(BuildingCode.get(a_numberGenerator, initialBuilding));
    }

    @Override
    public void applyMutation(int index, double a_percentage) {
        setToRandomValue(this.getConfiguration().getRandomGenerator());
    }

    public void setToValue(BuildingCode value){
        if(building.isUntouchable() || value.isUntouchable()) return;
        if(value.isResource() && initialBuilding.isResource() && value != initialBuilding ) return;
        building = value;
        
    }
    @Override
    public int compareTo(Object o) {
        return building.value().compareTo((String) ((BuildingGene) o).getInternalValue());
    }

    public BuildingCode getInitialBuilding() {
        return initialBuilding;
    }

    public BuildingCode getBuilding() {
        return building;
    }
}
