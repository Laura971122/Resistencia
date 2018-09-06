package com.laugracianool.resistencia;

import android.support.v7.app.AppCompatActivity;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import static java.lang.Math.pow;


public class MainActivity extends AppCompatActivity {
    private Spinner Color1, Color2, Color3, Color4;
    private TextView Resultado;
    private Button Calcular;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calcular=findViewById(R.id.Calcular);
        Color1=findViewById(R.id.Color1);
        Color2=findViewById(R.id.Color2);
        Color3=findViewById(R.id.Color3);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,
                R.array.Colores1, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        Color1.setAdapter(adapter);
        Color2.setAdapter(adapter);
        Color3.setAdapter(adapter);
        Color4=findViewById(R.id.Color4);


        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.Colores2, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Color4.setAdapter(adapter2);

        Calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {

                ShowDialog("Resultado",BuildNumber(Color1.getSelectedItemPosition(),Color2.getSelectedItemPosition(),
                        Color3.getSelectedItemPosition())+" "+Tolerancia (Color4.getSelectedItemPosition()));
            }
        });
    }

    private void ShowDialog(String Title, String Caption)
    {
        new AlertDialog.Builder(this)
                .setTitle(Title)
                .setMessage(Caption)
                .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

                    }
                })
                .setIcon(android.R.drawable.ic_menu_info_details)
                .show();
    }

    private String Tolerancia (int Value)
    {
        if (Value == 0)
            return "+5% tolerancia";

            return "+10% tolerancia";
    }

    private String BuildNumber(int Value1, int Value2, int Value3)
    {
        String fin;
        fin = Integer.toString(Value1) + Integer.toString(Value2);
        double Total = Integer.parseInt(fin)*pow(10,Value3);

        if (Total/1000 >= 1 && Total/1e3 < 1000 )
        {
            return (String.valueOf(Total / 1e3));
        }

        if (Total/1e6 >= 1)
        {
            return (String.valueOf(Total / 1e6));
        }else{

            return (String.valueOf(Total));
        }

    }


}


