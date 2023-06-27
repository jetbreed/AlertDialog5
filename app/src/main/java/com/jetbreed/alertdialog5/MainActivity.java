package com.jetbreed.alertdialog5;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI widgets button and
        Button bOpenAlertDialog = findViewById(R.id.openAlertDialogButton);
        final TextView tvSelectedItemsPreview =
                findViewById(R.id.selectedItemPreview);

        // initialise the list items for the alert dialog
        final String[] listItems = new String[]{"C", "C++", "JAVA", "PYTHON"};
        final boolean[] checkedItems = new boolean[listItems.length];

        // copy the items from the main list to the selected item
        // list for the preview
        // if the item is checked then only the item should be
        // displayed for the user
        final List<String> selectedItems = Arrays.asList(listItems);

        // handle the Open Alert Dialog button
        bOpenAlertDialog.setOnClickListener(v -> {
            // initially set the null for the text preview
            tvSelectedItemsPreview.setText(null);

            // initialise the alert dialog builder
            AlertDialog.Builder builder =
                    new AlertDialog.Builder(MainActivity.this);

            // set the title for the alert dialog
            builder.setTitle("Choose Items");

            // set the icon for the alert dialog
            builder.setIcon(R.drawable.image_logo);

            // now this is the function which sets the alert dialog
            // for multiple item selection ready
            builder.setMultiChoiceItems(listItems, checkedItems,
                    (dialog, which, isChecked) -> {
                checkedItems[which] = isChecked;
                String currentItem = selectedItems.get(which);
            });

            // alert dialog shouldn't be cancellable
            builder.setCancelable(false);

            // handle the positive button of the dialog
            builder.setPositiveButton("Done", (dialog, which) -> {
                for (int i = 0; i < checkedItems.length; i++) {
                    if (checkedItems[i]) {
                        tvSelectedItemsPreview
                                .setText(String.format("%s%s, ",
                                        tvSelectedItemsPreview.getText(),
                                        selectedItems.get(i)));
                    }
                }
            });

            // handle the negative button of the alert dialog
            builder.setNegativeButton("CANCEL", (dialog, which) -> {});

            // handle the neutral button of the dialog to clear the
            // selected items boolean checkedItem
            builder.setNeutralButton("CLEAR ALL", (dialog, which) -> {
                Arrays.fill(checkedItems, false);
            });

            // create the builder
            builder.create();

            // create the alert dialog with the alert dialog builder instance
            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        });
    }
}
