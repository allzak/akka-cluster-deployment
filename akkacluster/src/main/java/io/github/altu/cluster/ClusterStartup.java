package io.github.altu.cluster;

import akka.actor.ActorSystem;
import akka.actor.Props;
import akka.cluster.Cluster;
import akka.cluster.http.management.ClusterHttpManagement;


import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

/**
 * Created by Oleksandr_Zakharov on 7/4/2017.
 */
public class ClusterStartup {

    public static void main(String[] args) {
        if (args.length == 0)
            startup(new String[]{"2551"});
        else
            startup(new String[]{System.getProperty("akka.port")});
    }

    public static void startup(String[] ports) {
        for (String port : ports) {
            // Override the configuration of the port
            Config config = ConfigFactory.parseString(
                    "akka.remote.netty.tcp.port=" + port).withFallback(
                    ConfigFactory.load());

            // Create an Akka system
            ActorSystem system = ActorSystem.create("AkkaCluster", config);

            Cluster cluster = Cluster.get(system);
            ClusterHttpManagement.create(cluster).start();

            // Create an actor that handles cluster domain events
            system.actorOf(Props.create(ClusterLifecycleMembersActor.class),
                    "clusterListener");
        }
    }
}
