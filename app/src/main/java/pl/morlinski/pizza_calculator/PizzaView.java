package pl.morlinski.pizza_calculator;

import android.app.AlertDialog;
import android.content.Context;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
            if (i == 0) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(root.getContext());
                LayoutInflater inflater = LayoutInflater.from(root.getContext());
                View dialogView = inflater.inflate(R.layout.input_alert_dialog, null);

                EditText sizeView = dialogView.findViewById(R.id.input_alert_dialog);
                sizeView.setText(Integer.toString(pizza.getDiameter()));

                builder.setTitle("Ustaw średnicę pizzy:")
                        .setView(dialogView)
                        .setPositiveButton("Ustaw", (d, w) -> {
                            try {
                                int sizeValue = parseInt(sizeView.getText().toString());
                                if (sizeValue <= 0) {
                                    throw new IllegalStateException();
                                }
                                p.setDiameter(sizeValue);
                            } catch (NumberFormatException | IllegalStateException ex) {
                                System.out.println(ex);
                                showMessageDialog("Błędnie wprowadzona dana. Poprzednia wartość została przywrócona.", root.getContext());
                            }
                        })
                        .setNegativeButton("Anuluj", (d, w) -> {
                        })
                        .show();
            } else {
                p.setDiameter(i);
            }
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
            if (i == 0) {
                final AlertDialog.Builder builder = new AlertDialog.Builder(root.getContext());
                LayoutInflater inflater = LayoutInflater.from(root.getContext());
                View dialogView = inflater.inflate(R.layout.input_alert_dialog, null);

                EditText sizeView = dialogView.findViewById(R.id.input_alert_dialog);
                sizeView.setText(Integer.toString(pizza.getQuantity()));

                builder.setTitle("Ustaw liczbę pizzy:")
                        .setView(dialogView)
                        .setPositiveButton("Ustaw", (d, w) -> {
                            try {
                                int quantityValue = parseInt(sizeView.getText().toString());
                                if (quantityValue <= 0) {
                                    throw new IllegalStateException();
                                }
                                p.setQuantity(quantityValue);
                            } catch (NumberFormatException | IllegalStateException ex) {
                                System.out.println(ex);
                                showMessageDialog("Błędnie wprowadzona dana. Poprzednia wartość została przywrócona.", root.getContext());
                            }
                        })
                        .setNegativeButton("Anuluj", (d, w) -> {
                        })
                        .show();
            } else {
                p.setQuantity(i);
            }
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

    private void showMessageDialog(String text, Context context) {
        new AlertDialog.Builder(context)
                .setTitle("Informacja")
                .setMessage(text)
                .setPositiveButton("Zamknij", (dialog, which) -> {
                })
                .show();
    }

}
