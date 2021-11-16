package ar.uba.fi.tdp.wifi_share.model;

import ar.uba.fi.tdp.wifi_share.views_controller.networksFinder.controllers.AccessPoints;

public interface NetworkSolver {

    public void loadNetworks();

    public AccessPoints solve();
}
