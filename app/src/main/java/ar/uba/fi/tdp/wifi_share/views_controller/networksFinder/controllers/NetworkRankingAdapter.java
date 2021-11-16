package ar.uba.fi.tdp.wifi_share.views_controller.networksFinder.controllers;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import ar.uba.fi.tdp.wifi_share.R;
import ar.uba.fi.tdp.wifi_share.model.Network;

public class NetworkRankingAdapter extends ArrayAdapter<Network> {

    private final Context context;
    private final Network[] networks;
    private int sponsoredNetworksQuantity;

    public NetworkRankingAdapter(Context context, Network[] networks, int sponsoredNetworksQuantity) {
        super(context, -1, networks);
        this.context = context;
        this.networks = networks;
        this.sponsoredNetworksQuantity = sponsoredNetworksQuantity;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View rowView = inflater.inflate(R.layout.row_layout, parent, false);
        TextView networkName = (TextView) rowView.findViewById(R.id.label);
        TextView networkRating = (TextView) rowView.findViewById(R.id.networkRating);
        ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);

        networkName.setText(networks[position].getName());
        networkRating.setText(networks[position].getRanking().toString());

        LinearLayout networkBox = (LinearLayout) rowView.findViewById(R.id.networkBox);

        if ( networks[position].getSponsorshipStatus() == Network.Sponsorship.SPONSORED && position < sponsoredNetworksQuantity)
            networkBox.setBackgroundColor(Color.parseColor("#e1eaf3"));

        if ( networks[position].getRanking() <= 0 ) {
            networkRating.setVisibility(View.INVISIBLE);
            imageView.setImageResource(R.drawable.gray_shadow_star);
        }

        else
            imageView.setImageResource(R.drawable.shadow_star);

        return rowView;
    }
}
