package message.server;

import message.MessageInterface;
import message.VisitableServerMessageInterface;

/**
 * Abstract class for a generic message from client to server
 */
public abstract class ServerMessage implements MessageInterface, VisitableServerMessageInterface {
}
