package it.polimi.ingsw.PSP33.controller.godsRules.interfaces;

import it.polimi.ingsw.PSP33.controller.godsRules.implementation.Prometheus;

public interface PrometheusDefinition {

    /**
     * Method that define Prometheus effect
     *
     * @param god class implementation
     */
    void visit(Prometheus god);

}
