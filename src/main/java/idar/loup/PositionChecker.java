package idar.loup;

import org.jgap.Gene;
import org.jgap.IChromosome;
import org.jgap.IGeneConstraintChecker;

public class PositionChecker implements IGeneConstraintChecker{
    @Override
    public boolean verify(Gene a_gene, Object a_alleleValue, IChromosome a_chromosome, int a_geneIndex) {
        if(a_gene.getAllele().equals("W"))return false;
        return true;
    }
}
