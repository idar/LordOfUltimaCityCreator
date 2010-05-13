package idar.loup;

import org.jgap.*;

public class BuildingGene extends BaseGene implements Gene, java.io.Serializable{

    private BuildingCode sign;

    private BuildingCode initialSign;

    public BuildingGene(Configuration a_configuration) throws InvalidConfigurationException {
        this(a_configuration, "#");
    }

    public BuildingGene(Configuration configuration,String initialSign) throws InvalidConfigurationException {
        super(configuration);
        this.initialSign = BuildingCode.fromValue(initialSign);
        sign = this.initialSign;
    }

    @Override
    protected Object getInternalValue() {
        return sign.value();
    }

    @Override
    protected Gene newGeneInternal() {
        try {
            return new BuildingGene(getConfiguration(),"-");
        } catch (InvalidConfigurationException e) {
            throw new IllegalStateException(e.getMessage());           
        }
    }

    public void setAllele(Object o) {
        setAlleleWithoutRestrictions(BuildingCode.fromValue((String) o ));
    }

    private void setAlleleWithoutRestrictions(BuildingCode buildingCode) {
        sign = buildingCode;
    }

    public void setAllele(BuildingCode o) {
        if(initialSign.isUntouchable() || o.isUntouchable()) return;
        if(initialSign.isResource()){
            if((o.isUntouchable() || o.isResource() && !o.equals(initialSign)))return;
        }
        sign = o;
    }

    public String getPersistentRepresentation() throws UnsupportedOperationException {
        return sign.value()+initialSign.value();
    }

    public void setValueFromPersistentRepresentation(String s) throws UnsupportedOperationException, UnsupportedRepresentationException {
        sign=BuildingCode.fromValue(String.valueOf(s.charAt(0)));
        initialSign=BuildingCode.fromValue(String.valueOf(s.charAt(1)));
    }

    public void setToRandomValue(RandomGenerator randomGenerator) {
        setAllele(BuildingCode.get(randomGenerator));
    }

    public void applyMutation(int i, double v) {
        setAllele(BuildingCode.get(getConfiguration().getRandomGenerator()).value());

    }

    public int compareTo(Object o) {
        return sign.compareTo(((BuildingGene)o).getSign());
    }

    public BuildingCode getSign() {
        return sign;
    }

    @Override
    public boolean equals(Object a_other) {
        return sign.equals(((BuildingGene)a_other).getSign());
    }

    @Override
     public String toString() {
        return sign.value();
    }
}
