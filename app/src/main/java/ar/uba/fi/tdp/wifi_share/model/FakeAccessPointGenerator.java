package ar.uba.fi.tdp.wifi_share.model;

public class FakeAccessPointGenerator {

    private NetworksDatabase networksDatabase;

    public FakeAccessPointGenerator(NetworksDatabase networksDatabase) {
        this.networksDatabase = networksDatabase;
    }

    public void generateDefaultsNetworks() {
        Network network = new Network("FIUBA", "AA");
        Site site = new Site(-34.612612612612615, -58.36618916538853,"Av. Paseo Colon", 850);
        site.setCity("Buenos Aires");
        network.setSite(site);
        network.setRanking(3);
        networksDatabase.add(network);

        network = new Network("FIUBA", "BB");
        site = new Site(-34.5883387, -58.398337, "Av. Las Heras",2214);
        site.setCity("Buenos Aires");
        network.setSite(site);
        network.setRanking(1);
        networksDatabase.add(network);

        network = new Network("ROCHESTER Hotels","CC");
        site = new Site(-34.601911111, -58.378177778, "Esmeralda", 542);
        site.setCity("Buenos Aires");
        network.setSite(site);
        network.setRanking(4);
        network.setSponsorshipStatus(Network.Sponsorship.UNSPONSORED);
        networksDatabase.add(network);

        network = new Network("Urban Station Palermo Hollywood","DD");
        site = new Site(-34.587104, -58.4565517, "Bonpland", 1823);
        site.setCity("Buenos Aires");
        network.setSite(site);
        network.setRanking(3);
        network.setSponsorshipStatus(Network.Sponsorship.SPONSORED);
        networksDatabase.add(network);

        network = new Network("Barba roja","EE");
        site = new Site(-34.6142114, -58.3739888, "Defensa", 549);
        site.setCity("Buenos Aires");
        network.setSite(site);
        network.setRanking(2);
        network.setSponsorshipStatus(Network.Sponsorship.SPONSORED);
        networksDatabase.add(network);

        network = new Network("The Oldest Resto Bar","FF");
        site = new Site(-34.5756965, -58.4619697, "Elcano", 3410);
        site.setCity("Buenos Aires");
        network.setSite(site);
        network.setRanking(5);
        network.setSponsorshipStatus(Network.Sponsorship.SPONSORED);
        networksDatabase.add(network);

        network = new Network("Buena Birra","GG");
        site = new Site(-34.5721056, -58.457658, "Zapiola", 1353);
        site.setCity("Buenos Aires");
        network.setSite(site);
        network.setRanking(2);
        network.setSponsorshipStatus(Network.Sponsorship.UNSPONSORED);
        networksDatabase.add(network);

        network = new Network("Cervelar","HH");
        site = new Site(-34.5923428, -58.4780952, "Elcano", 3385);
        site.setCity("Buenos Aires");
        network.setSite(site);
        network.setRanking(3);
        network.setSponsorshipStatus(Network.Sponsorship.SPONSORED);
        networksDatabase.add(network);

        network = new Network("Xperia L - Martin","b4:52:7d:f7:70:bb");
        site = new Site(-34.612612612612615, -58.36618916538853,"", Site.INVALID_ADDRESS_NUMBER);
        site.setCity("Buenos Aires");
        network.setPassword("XperiaMartin");
        network.setSite(site);
        network.setRanking(3);
        network.setSponsorshipStatus(Network.Sponsorship.SPONSORED);
        networksDatabase.add(network);

        network = new Network("Tito","LL");
        site = new Site(-34.612612612612615, -58.36618916538853,"", Site.INVALID_ADDRESS_NUMBER);
        site.setCity("Buenos Aires");
        network.setPassword("coco");
        network.setSite(site);
        network.setRanking(3);
        network.setSponsorshipStatus(Network.Sponsorship.SPONSORED);
        networksDatabase.add(network);
    }
}
