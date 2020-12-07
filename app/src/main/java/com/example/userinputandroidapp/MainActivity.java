package com.example.userinputandroidapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.text.NumberFormat;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }




    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {

        CheckBox whippedCream = (CheckBox) findViewById(R.id.cream_checkBox);
        boolean haswhippedCream = whippedCream.isChecked();

        CheckBox hasChoclateBox = (CheckBox) findViewById(R.id.choclate_checkBox);
        boolean haschoclate = hasChoclateBox.isChecked();

        EditText nameText = (EditText) findViewById(R.id.nameTextView);
        String name = nameText.getText().toString();

        int price = 0;

        if(haswhippedCream && haschoclate){
            price = (quantity * 5 ) + (quantity * 3);
        }else if(haswhippedCream){
            price =(quantity * 5 ) + (quantity * 1);
        }else if(haschoclate){
             price =(quantity * 5 ) + (quantity * 2);
        }else{
            price = quantity * 5;
        }

        String priceMessage = createOrderSummary(name,price,haswhippedCream,haschoclate);

        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("*/*");
        intent.putExtra(Intent.EXTRA_SUBJECT, "Coffee Order from : " + name);
        intent.putExtra(Intent.EXTRA_TEXT, priceMessage);
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }



    }


    /**
     * This method displays the given quantity value on the screen.
     */

//    private int calculatePrice(){
//
//        int price = quantity * 5;
//                return price;
//    }

    private void display(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }



    public void increment(View view) {
        if(quantity < 100){
        quantity += 1;
        display(quantity);
        }else return;


    }
    public void decrement(View view) {
        if(quantity > 1){
            quantity -= 1;
            display(quantity);
        }else return;


    }


    private String  createOrderSummary(String namep,int price,boolean haswhipped,boolean haschoco){
      String priceMessage = getString(R.string.name_text,namep) ;
      priceMessage += getString(R.string.add_whip_cream) + haswhipped;
        priceMessage += getString(R.string.add_chocolate) + haschoco;
      priceMessage = priceMessage + getString(R.string.quantity) + quantity;
      priceMessage = priceMessage + getString(R.string.total) + price;
      priceMessage = priceMessage + getString(R.string.thank_you);
      return  priceMessage;
    }
}