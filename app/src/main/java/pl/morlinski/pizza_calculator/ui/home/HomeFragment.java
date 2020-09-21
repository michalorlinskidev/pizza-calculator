package pl.morlinski.pizza_calculator.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;

import pl.morlinski.pizza_calculator.R;

import static java.lang.Math.PI;
import static java.lang.Math.pow;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);

        final TextView pizza1Diameter = root.findViewById(R.id.pizza1Diameter);
        final TextView pizza1Price = root.findViewById(R.id.pizza1Price);
        final TextView pizza2Diameter = root.findViewById(R.id.pizza2Diameter);
        final TextView pizza2Price = root.findViewById(R.id.pizza2Price);
        final TextView result = root.findViewById(R.id.result);
        final Button pizzaCalc = root.findViewById(R.id.pizzaCalc);

        pizzaCalc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double pizza1DiameterValue = Float.parseFloat(pizza1Diameter.getText().toString());
                double pizza2DiameterValue = Float.parseFloat(pizza2Diameter.getText().toString());
                double pizza1PriceValue = Float.parseFloat(pizza1Price.getText().toString());
                double pizza2PriceValue = Float.parseFloat(pizza2Price.getText().toString());

                double pizza1Area = PI * pow(pizza1DiameterValue / 2.0, 2);
                double pizza2Area = PI * pow(pizza2DiameterValue / 2.0, 2);

                double pizza1PricePer1cm_2 = pizza1PriceValue / pizza1Area;
                double pizza2PricePer1cm_2 = pizza2PriceValue / pizza2Area;

                result.setText("Cena pizzy (na 1cm^2): " + pizza1PricePer1cm_2 + " vs " + pizza2PricePer1cm_2);
            }
        });
        return root;
    }
}