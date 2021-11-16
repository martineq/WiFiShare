package ar.uba.fi.tdp.wifi_share.model;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class NetworksDatabase {

    private Map<String,Network> networkMap;

    private static NetworksDatabase networksDatabase;

    private NetworksDatabase() {
        networkMap = new HashMap<String,Network>();
    };

    public static boolean createDatabase() {
        if ( NetworksDatabase.networksDatabase == null) {
            NetworksDatabase.networksDatabase = new NetworksDatabase();
            FakeAccessPointGenerator apGenerator = new FakeAccessPointGenerator(networksDatabase);
            apGenerator.generateDefaultsNetworks();
            return true;
        }
        else return false;
    }

    public static NetworksDatabase accessDatabase(){
        if ( NetworksDatabase.networksDatabase == null)
            NetworksDatabase.networksDatabase = new NetworksDatabase();

        return NetworksDatabase.networksDatabase;
    }

    public void add(Network network) {
        networkMap.put(network.getMac(), network);
    }

    public void remove(Network network) {
        networkMap.remove(network.getMac());
    }

    public Iterator<Map.Entry<String, Network>> getNetworkIterator(){
        return networkMap.entrySet().iterator();
    }

    public void update(Network oldNetwork, Network newNetwork) {
        String oldNetworkMAC = oldNetwork.getMac();
        Network modifiedNetwork = oldNetwork.update(newNetwork);

        if ( modifiedNetwork.getMac().equals( oldNetworkMAC ) == false )
            networkMap.remove(oldNetworkMAC);

        networkMap.put( modifiedNetwork.getMac(), modifiedNetwork );
    }

    public boolean isNetworkAvailable(String networkMAC) {
        return networkMap.containsKey(networkMAC);
    }

    public Network findNetwork(String mac) {
        return networkMap.get(mac);
    }
}
