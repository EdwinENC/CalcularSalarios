package com.example.trabajocuadrante;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.snackbar.BaseTransientBottomBar;
import com.google.android.material.snackbar.Snackbar;

public class MainActivity extends AppCompatActivity {
    EditText edt_x1= null  ;
    EditText edt_x2= null  ;
    EditText edt_y1= null  ;
    EditText edt_y2= null  ;
    private Button btn_punto_medio=null;
    Button btn_pendiente=null;
    Button btn_cuadrante=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         edt_x1=   findViewById(R.id.edt_x1);
         edt_x2=   findViewById(R.id.edt_x2);
         edt_y1=   findViewById(R.id.edt_y1);
         edt_y2=   findViewById(R.id.edt_y2);
         btn_punto_medio= findViewById(R.id.punto_medio);
         btn_pendiente=findViewById(R.id.pendiente);
         btn_cuadrante=findViewById(R.id.cuadrante);

         btn_punto_medio.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                double x_m = cacular_punto_medio_x();
                 double y_m = cacular_punto_medio_y();
                 mostrar_resultado("El punto medio es: X="+x_m+"  Y=:"+y_m);
             }
         });

         btn_pendiente.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 double m= calcular_pendiente();
                 mostrar_resultado("La pendiente  es ="+ m);
             }
         });
         btn_cuadrante.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {

                 int y=Integer.parseInt( edt_y1.getText().toString());
                 int x=Integer.parseInt(edt_x1.getText().toString());

                int cuadrante1 = calcular_cuadrante(x,y);
                mostrar_resultado("el primer punto (x1,y1) esta en el cuadrante "+cuadrante1);

                  y=Integer.parseInt( edt_y2.getText().toString());
                  x=Integer.parseInt(edt_x2.getText().toString());

                 int cuadrante2 = calcular_cuadrante(x,y);
                 mostrar_resultado("el segundo punto (x2,y2) esta en el cuadrante "+cuadrante2);
             }
         });
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.aleatorio:



              Snackbar.make( findViewById(R.id.hola), "aleatorio",Snackbar.LENGTH_SHORT).show();
                poner_numeros_aleatorios();
                return true;
            case R.id.distancia:
                double distancia = calcular_distancia();
                mostrar_resultado("la distacia en los puntos es ="+ distancia);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }


    private void poner_numeros_aleatorios() {

        edt_x1.setText(crear_numero_aleatorios());
        edt_x2.setText(crear_numero_aleatorios());
        edt_y1.setText(crear_numero_aleatorios());
        edt_y2.setText(crear_numero_aleatorios());
    }

    private String crear_numero_aleatorios() {
        int entero=(int) Math.floor(Math.random()*10);
        if(Math.random()<0.5){
        entero*=-1;
             }
        return String.valueOf(entero);
    }




        private void  mostrar_resultado(String mensaje){
            AlertDialog.Builder builder = new AlertDialog.Builder(this);


            builder.setMessage(mensaje).setTitle("Resultado");


            AlertDialog dialog = builder.create();
            dialog.show();

        }

    private double cacular_punto_medio_x(){
        int x1=Integer.parseInt(edt_x1.getText().toString());
        int x2=Integer.parseInt(edt_x2.getText().toString());
        double x_m= (double)(x1+x2)/2;
        return x_m;

    }

    private double cacular_punto_medio_y(){
        int y1=Integer.parseInt( edt_y1.getText().toString());
        int y2=Integer.parseInt( edt_y2.getText().toString());
        double y_m= (double) (y1+y2)/2;
        return y_m;
    }
    private  double calcular_pendiente(){
        int y1=Integer.parseInt( edt_y1.getText().toString());
        int y2=Integer.parseInt( edt_y2.getText().toString());
        int x1=Integer.parseInt(edt_x1.getText().toString());
        int x2=Integer.parseInt(edt_x2.getText().toString());

        int m_y=y2-y1;
        int m_x=x2-x1;

        double m=(double)m_y/m_x;
        return m;

    }
    private double calcular_distancia(){
        int y1=Integer.parseInt( edt_y1.getText().toString());
        int y2=Integer.parseInt( edt_y2.getText().toString());
        int x1=Integer.parseInt(edt_x1.getText().toString());
        int x2=Integer.parseInt(edt_x2.getText().toString());
        int m_y=y2-y1;
        int m_x=x2-x1;
        double numero_x =(double) Math.pow(m_x,2);
        double numero_y= (double)Math.pow(m_y,2);
        double suma_cuadrados=(double) numero_x+numero_y;
        double distancia=(double)Math.sqrt(suma_cuadrados);

        return distancia;


    }

    private  int calcular_cuadrante(int x , int y ){
        int cuadrante=0;
        if(x<0 && y>0){
            cuadrante= 1;
        }
        if(x>0 && y>0){
            cuadrante= 2;
        }
        if(x<0 && y<0){
            cuadrante= 4;
        }
        if(x>0 && y<0){
            cuadrante= 3;
        }
         return cuadrante;
    }
}