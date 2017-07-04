# akka-cluster-deployment
Deployment tool to create akka cluster, to add new nodes, deploy actors

Start cluster:

**java -jar akka-cluster-${version}.jar [Options]**

Options:

 -akka.port - default port 2551;
 -Dakka.cluster.seed-nodes.0=akka.tcp://ClusterSystem@host1:2552;
