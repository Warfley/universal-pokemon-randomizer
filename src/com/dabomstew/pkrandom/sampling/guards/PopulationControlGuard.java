package com.dabomstew.pkrandom.sampling.guards;

import com.dabomstew.pkrandom.pokemon.Pokemon;

public final class PopulationControlGuard extends DiversityGuard<Pokemon> {

    private double degree = 10;

    public double getDegree() {
        return degree;
    }

    public void setDegree(double degree) {
        this.degree = degree;
    }

    @Override
    public void updateLastSample(Pokemon pkmn) {
        // We want to consider whole evolution chains, otherwise 3 step evos
        // would be preferred
        super.updateLastSample(pkmn.getEvolutionRepresentant());
    }

    @Override
    protected double computeWeight(Pokemon obj) {
        // So of course we need to compute the weight for the evo chain
        // ^10 to increase punishment for often sampled pokemon highly
        return Math.pow(super.computeWeight(obj.getEvolutionRepresentant()), degree);
    }

}
