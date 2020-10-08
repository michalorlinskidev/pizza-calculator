package pl.morlinski.pizza_calculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

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

        final Button pizzaCalc = root.findViewById(R.id.pizzaCalc);
        final TextView pizzaCalcResult = root.findViewById(R.id.pizzaCalcResult);
        pizzaCalc.setOnClickListener(v -> {
            if (!pizza1.isSet() || !pizza2.isSet()) {
                pizzaCalcResult.setText("Za mało danych do wyliczeń.");
                return;
            }

            pizzaCalcResult.setText(String.format("Bardziej opłaca Ci się opcja numer: %1$s. Cena pizzy (na 1cm^2): %2$.02f zł vs %3$.02f zł. Powierzchnia: %4$.02f cm^2 vs %5$.02f cm^2",
                    (pizza1.pricePer_1cm_2() >= pizza2.pricePer_1cm_2() ? "2" : "1"),
                    pizza1.pricePer_1cm_2(), pizza2.pricePer_1cm_2(),
                    pizza1.area(), pizza2.area()
            ));
        });

    }

}