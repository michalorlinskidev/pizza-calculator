package pl.morlinski.pizza_calculator;

import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

import static java.lang.Integer.parseInt;

public class PizzaView {
    private final Map<Button, Integer> size;
    private final Map<Button, Integer> quantity;
    private final TextView price;
    private final Pizza pizza;

    public PizzaView(View root,
                     int sizeSmall, int sizeMiddle, int sizeBig, int sizeCustom,
                     int quantityOne, int quantityTwo, int quantityThree, int quantityFour, int quantityFive, int quantityCustom,
                     int priceId,
                     int sizeSelect, int quantitySelect,
                     Pizza pizza) {
        this.pizza = pizza;
        size = new HashMap<>();
        size.put(root.findViewById(sizeSmall), 25);
        size.put(root.findViewById(sizeMiddle), 30);
        size.put(root.findViewById(sizeBig), 35);
        size.put(root.findViewById(sizeCustom), 0);
        buttonOnClick(size, (Pizza p, Integer i) -> {
            p.setDiameter(i);
        });
        root.findViewById(sizeSelect).callOnClick();

        quantity = new HashMap<>();
        quantity.put(root.findViewById(quantityOne), 1);
        quantity.put(root.findViewById(quantityTwo), 2);
        quantity.put(root.findViewById(quantityThree), 3);
        quantity.put(root.findViewById(quantityFour), 4);
        quantity.put(root.findViewById(quantityFive), 5);
        quantity.put(root.findViewById(quantityCustom), 0);
        buttonOnClick(quantity, (Pizza p, Integer i) -> {
            p.setQuantity(i);
        });
        root.findViewById(quantitySelect).callOnClick();

        price = root.findViewById(priceId);
        priceSetListener(price);
    }

    private void priceSetListener(TextView price) {
        price.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                Integer priceValue = null;
                try {
                    priceValue = parseInt(price.getText().toString());
                } catch (NumberFormatException ex) {
                    System.out.println(ex);
                }

                pizza.setPrice(priceValue);
            }
        });
    }

    private void buttonOnClick(final Map<Button, Integer> map, BiConsumer<Pizza, Integer> setter) {
        for (Map.Entry<Button, Integer> e : map.entrySet()) {
            e.getKey().setOnClickListener(v -> {
                for (Button i : map.keySet()) {
                    i.setBackgroundColor(Color.rgb(215, 215, 215));
                }
                v.setBackgroundColor(Color.GRAY);
                setter.accept(pizza, e.getValue());
            });
        }
    }

}
