package pl.morlinski.pizza_calculator;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View root = findViewById(android.R.id.content).getRootView();

        Pizza pizza1 = new Pizza();
        Pizza pizza2 = new Pizza();

        PizzaView pizza1View = new PizzaView(root, R.id.pizza1SizeSmall, R.id.pizza1SizeMiddle, R.id.pizza1SizeBig, R.id.pizza1SizeCustom,
                R.id.pizza1CountOne, R.id.pizza1CountTwo, R.id.pizza1CountThree, R.id.pizza1CountFour, R.id.pizza1CountFive, R.id.pizza1CountCustom, R.id.pizza1Price, R.id.pizza1SizeMiddle, R.id.pizza1CountTwo, pizza1);

        PizzaView pizza2View = new PizzaView(root, R.id.pizza2SizeSmall, R.id.pizza2SizeMiddle, R.id.pizza2SizeBig, R.id.pizza2SizeCustom,
                R.id.pizza2CountOne, R.id.pizza2CountTwo, R.id.pizza2CountThree, R.id.pizza2CountFour, R.id.pizza2CountFive, R.id.pizza2CountCustom, R.id.pizza2Price, R.id.pizza2SizeBig, R.id.pizza2CountOne, pizza2);

        final FloatingActionButton pizzaCalc = root.findViewById(R.id.pizzaCalc);
        pizzaCalc.setOnClickListener(v -> {
            if (!pizza1.isSet() || !pizza2.isSet()) {
                showMessageDialog("Za mało danych do wyliczeń.");
                return;
            }

            final AlertDialog.Builder builder = new AlertDialog.Builder(root.getContext());
            LayoutInflater inflater = LayoutInflater.from(root.getContext());
            View dialogView = inflater.inflate(R.layout.result_alert_dialog, null);

            TextView result1Price = dialogView.findViewById(R.id.result1Price);
            TextView result2Price = dialogView.findViewById(R.id.result2Price);
            TextView result1Size = dialogView.findViewById(R.id.result1Size);
            TextView result2Size = dialogView.findViewById(R.id.result2Size);
            TextView result1 = dialogView.findViewById(R.id.result1);
            TextView result2 = dialogView.findViewById(R.id.result2);

            result1Price.setText(String.format("%1$.02f zł", pizza1.pricePer_1cm_2()));
            result2Price.setText(String.format("%1$.02f zł", pizza2.pricePer_1cm_2()));
            result1Size.setText(String.format("%1$.02f cm^2", pizza1.area()));
            result2Size.setText(String.format("%1$.02f cm^2", pizza2.area()));

            boolean selectPizza1 = pizza1.pricePer_1cm_2() < pizza2.pricePer_1cm_2();

            result1.setText(selectPizza1 ? "KUP" : "NIE KUPUJ");
            result2.setText(selectPizza1 ? "NIE KUPUJ" : "KUP");

            builder.setTitle("Podsumowanie")
                    .setView(dialogView)
                    .setPositiveButton("Zamknij", (d, w) -> {
                    })
                    .show();

        });

    }

    private void showMessageDialog(String text) {
        AlertDialog info = new AlertDialog.Builder(this)
                .setTitle("Podsumowanie")
                .setMessage(text)
                .setPositiveButton("Zamknij", (dialog, which) -> {
                })
                .show();
    }

}