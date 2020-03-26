package message.client;

import message.MessageInterface;
import message.VisitableClientMessageInterface;

/**
 * Abstract class for a generic message from server to client
 */
public abstract class ClientMessage implements MessageInterface, VisitableClientMessageInterface {
}
