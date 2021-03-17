package com.example.mycalculatrice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView Screen;
    private Button ac,fact,back,neuf,huit,sept,six,cinq,quatre,trois,deux,un,zero,div,mult,moins,plus,point,ans,egal;
    private String input,Answer;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // identification des boutons d'actions
        Screen=findViewById(R.id.screen);
        ac=findViewById(R.id.ac);
        fact=findViewById(R.id.fact);
        back=findViewById(R.id.back);
        neuf=findViewById(R.id.neuf);
        huit=findViewById(R.id.huit);
        sept=findViewById(R.id.sept);
        six=findViewById(R.id.six);
        cinq=findViewById(R.id.cinq);
        quatre=findViewById(R.id.quatre);
        trois=findViewById(R.id.trois);
        deux=findViewById(R.id.deux);
        un=findViewById(R.id.un);
        zero=findViewById(R.id.zero);
        div=findViewById(R.id.div);
        mult=findViewById(R.id.mult);
        moins=findViewById(R.id.moins);
        plus=findViewById(R.id.plus);
        point=findViewById(R.id.point);
        ans=findViewById(R.id.ans);
        egal=findViewById(R.id.egal);
    }
// Ajout des lecteurs de boutons d'action
    public void ButtonClick(View view){
        Button button = (Button) view;
        String data = button.getText().toString();
        switch (data){
            case "AC":
                input="";
                break;
            case "Ans":
                input+="Answer";
                break;
            case "x!":
                Solve();
                input+="x!";
                break;
            case "=":
                Solve();
                Answer=input;
                break;
            case "←":
                String newText = input.substring(0,input.length()-1);
                input=newText;
                break;
            default:
                if(input==null){
                    input="";
                }
                if (data.equals("+") || data.equals("-") || data.equals("/") || data.equals("*")){
                    Solve();
                }
                input+=data;
        }
        Screen.setText(input);
    }
    private void Solve(){
        if ( input.split("/").length==2){
                String number[]=input.split("/");
                if (number[1]=="0") {
                    input = "Erreur";
                }
                try {
                    double div = Double.parseDouble(number[0]) / Double.parseDouble(number[1]);
                    input = div+"";
                } catch (Exception e){

                }
        } else if   ( input.split("\\*").length==2) {
                String number[] = input.split("\\*");
                try {
                    double mult = Double.parseDouble(number[0]) * Double.parseDouble(number[1]);
                    input = mult+"";
                } catch (Exception e) {

                }

        }else if ( input.split("\\+").length==2) {
            String number[] = input.split("\\+");
            try {
                double plus = Double.parseDouble(number[0]) + Double.parseDouble(number[1]);
                input = plus+"";
            } catch (Exception e) {

            }

        } else if ( input.split("-").length>1) {
            String number[] = input.split("-");
            // pour la soustraction des nombres négatifs
            if (number[0] == "" && number.length == 2) {
                number[0] = 0 + "";
            }
            try {
                double moins = 0;
                if (number.length==2){
                    moins = Double.parseDouble(number[0]) - Double.parseDouble(number[1]);
                } else if (number.length == 3) {
                    moins = Double.parseDouble(number[1]) - Double.parseDouble(number[2]);
                }
                input = moins + "";
            } catch (Exception e) {

            }

        } else if ( input.split("-").length==2) {
            String number[] = input.split("-");
            // pour la soustraction des nombres négatifs
            if (number[0] == "" && number.length == 2) {
                number[0] = 0 + "";
            }
            try {
                double moins = Double.parseDouble(number[0]) - Double.parseDouble(number[1]);
                input = moins + "";
            } catch (Exception e) {

            }

        } else if ( input.indexOf("x!")>0) {
            double number = Double.parseDouble(input.substring(0, input.indexOf("x!")));
            try {
                if (number < 0) {
                    input = "Erreur";
                }
                if (number == 0) {
                    input = "1";
                }
                if (number < 20) {
                    double fact = 1;
                    for (int i = 1; i <= number; i++) {
                        fact = fact * i;
                    }
                    input = fact+"";
                }
                if (number >= 20) {
                    input = "Erreur";

                }

            } catch (Exception e) {

            }
        }

        // Pour enlever le dernier nombre .0 pour les résultats en integer
        String n[] = input.split("\\.");
        if (n.length>1){
            if (n[1].equals("0")){
                input=n[0];
            }
        }
        Screen.setText(input);
    }



}