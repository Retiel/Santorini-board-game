package it.polimi.ingsw.PSP33.controller.godsRules;

import it.polimi.ingsw.PSP33.controller.godsRules.interfaces.*;


/**
 * Interface define to be a point of connection to the Visitor pattern in the utils folder.
 *
 *     To imporve mopdularity every single god is defined seperatebly and added to the list below, every definition has to be uder interfaces folder
 *     for a better readability.
 *
 *     Every definition needs a visit method with an input parameter which is the class were you implement the visit under the implementation folder.
 *
 *     *) Here the structure:
 *     --> godsRules
 *          --> implementation (all implemented definition here)
 *          --> interfaces (all definition here)
 *
 *     To integrate the implementation add the definition in the list below.
 * */
public interface GodsDefinitions extends
        AtlasDefinition,
        ArtemisDefinition,
        ApolloDefinition,
        AthenaDefinition,
        DemeterDefinition,
        HephaestusDefinition,
        HermesDefinition,
        MinotaurDefinition,
        PrometheusDefinition{}
