package in.xnnyygn.xraft.server;

import in.xnnyygn.xraft.rpc.EmbeddedChannel;
import in.xnnyygn.xraft.rpc.Router;
import in.xnnyygn.xraft.serverstate.LoggingServerStateListener;
import in.xnnyygn.xraft.serverstate.ServerStateMachine;

public class ServerBuilder {

    private final String serverId;
    private final ServerGroup serverGroup;
    private ServerStore serverStore = new ServerStore();

    public ServerBuilder(String serverId, ServerGroup serverGroup) {
        this.serverId = serverId;
        this.serverGroup = serverGroup;
    }

    public Server build() {
        ServerId selfServerId = new ServerId(this.serverId);
        Router rpcRouter = new Router(this.serverGroup, selfServerId);
        ServerStateMachine serverStateMachine = new ServerStateMachine(this.serverGroup, selfServerId, this.serverStore, rpcRouter);
        serverStateMachine.addServerStateListener(new LoggingServerStateListener(selfServerId));
        Server server = new Server(selfServerId, serverStateMachine, new EmbeddedChannel(selfServerId, serverStateMachine));
        serverGroup.add(server);
        return server;
    }

}