package utils.patterns.visitor;

import controller.godsRules.GodsDefinitions;
import message.VisitorClientMessageInterface;
import message.VisitorServerMessageInterface;

/**
 * Interface used to implement the visitor pattern, this is the core part of the selective gods effect
 */
public interface Visitor extends
        GodsDefinitions,
        VisitorClientMessageInterface,
        VisitorServerMessageInterface
{}
