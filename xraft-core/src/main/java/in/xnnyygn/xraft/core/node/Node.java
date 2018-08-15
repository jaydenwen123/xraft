package in.xnnyygn.xraft.core.node;

import in.xnnyygn.xraft.core.log.StateMachine;
import in.xnnyygn.xraft.core.log.TaskReference;

public interface Node {

    void registerStateMachine(StateMachine stateMachine);

    RoleNameAndLeaderId getRoleNameAndLeaderId();

    RoleState getRoleState();

    void addNodeRoleListener(NodeRoleListener listener);

    void start();

    void appendLog(byte[] commandBytes);

    TaskReference addNode(NodeEndpoint newNodeEndpoint);

    TaskReference removeNode(NodeId id);

    void stop() throws InterruptedException;

}
