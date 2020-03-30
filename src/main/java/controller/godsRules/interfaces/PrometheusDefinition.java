package controller.godsRules.interfaces;

import controller.godsRules.implementation.Prometheus;

public interface PrometheusDefinition {

    /**
     * Method that define Prometheus effect
     *
     * @param god class implementation
     */
    void visit(Prometheus god);

}
