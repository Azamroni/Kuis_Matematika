package com.example.ronnygep.kuismatematika;

import android.app.Activity;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

class hasil{

    int benar,salah;

    public hasil(int benar, int salah)
    {
        this.benar=benar;
        this.salah=salah;

    }

    public int getbenar()
    {
        return benar;
    }
    public int getSalah()
    {
        return salah;
    }
};

public class MainActivity extends AppCompatActivity {

    List<hasil> hasilfix = new ArrayList<hasil>();
    private SharedPreference sharedPreference;
    Activity context=this;
    int benar=0,salah=0,akhir=0;
    String Operator;
    EditText angka1;
    EditText angka2;
    EditText jawaban;
    EditText oper;
    TextView hasilbenar;
    TextView hasilsalah;
    TextView hasilmu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        angka1=(EditText)findViewById(R.id.angka1);
        angka2=(EditText)findViewById(R.id.angka2);
        oper=(EditText)findViewById(R.id.operator);
        jawaban=(EditText)findViewById(R.id.jawaban);
        hasilbenar=(TextView)findViewById(R.id.jawaban_benar);
        hasilsalah=(TextView)findViewById(R.id.jawaban_salah);
        hasilmu=(TextView)findViewById(R.id.statusjawaban);
        sharedPreference=new SharedPreference();
        hasilfix=sharedPreference.getValue(context);
        akhir=getakhir(hasilfix);
        benar=hasilfix.get(akhir).getbenar();
        salah=hasilfix.get(akhir).getSalah();
        String bnr=Integer.toString(benar);
        String slh= Integer.toString(salah);
        hasilbenar.setText(bnr);
        hasilsalah.setText(slh);

    }

    public void isidata(Integer benar, Integer salah)
    {
        hasilfix.add(new hasil(benar,salah));
    }

    public void cekjawaban(View view) {
        hasilfix=sharedPreference.getValue(context);
        int hasil,akhir=0;
        akhir=getakhir(hasilfix);
        benar=hasilfix.get(akhir).getbenar();
        salah=hasilfix.get(akhir).getSalah();
        int ang1=Integer.parseInt(angka1.getText().toString());
        int ang2=Integer.parseInt(angka2.getText().toString());
        if(oper.getText().toString().equals("+"))
        {
            hasil=ang1+ang2;
        }
        else if(oper.getText().toString().equals("-"))
        {
            hasil=ang1-ang2;
        }
        else if(oper.getText().toString().equals("*"))
        {
            hasil=ang1*ang2;
        }
        else {
            hasil=ang1/ang2;
        }
        //benar=hasilfix.get(akhir).getbenar();
        //salah=hasilfix.get(akhir).getSalah();
        String h = Integer.toString(hasil);
        if(jawaban.getText().toString().equals(h))
        {
            benar=benar+1;
            String bnr=Integer.toString(benar);
            String slh= Integer.toString(salah);
            hasilmu.setText("Jawaban Anda Benar");
            Toast.makeText(this,"Jawaban Anda Benar",Toast.LENGTH_SHORT).show();
            //Set Jumlah benar
            hasilbenar.setText(bnr);
            hasilsalah.setText(slh);
        }
        else
        {
            salah=salah+1;
            String bnr=Integer.toString(benar);
            String slh= Integer.toString(salah);
            hasilmu.setText("Jawaban Anda Salah");
            Toast.makeText(this,"Jawaban Anda Salah",Toast.LENGTH_SHORT).show();
            //Set Jumlah benar
            hasilbenar.setText(bnr);
            hasilsalah.setText(slh);
        }

        isidata(benar,salah);
        sharedPreference.save(context,hasilfix);

    }

    int getakhir(List l)
    {int hasil=0;
        for(int a=0;a<l.size();a++)
        {
            hasil++;
        }
        // karena array ddimulai dari indeks 0
        return hasil-1;
    }


    public void random(View view) {

        int a,b;
        Operator=operator();
        oper.setText(Operator);
        a=(int)(Math.random()*100+1);
        b=(int)(Math.random()*a+1);
        angka1.setText(Integer.toString(a));
        angka2.setText(Integer.toString(b));
    }
    public String operator(){
        String opr="s";
        int a=(int)(Math.random()*4+1);
        switch (a){
            case 1:opr="+"; break;
            case 2:opr="-"; break;
            case 3:opr="x"; break;
            case 4:opr="/"; break;
        }
        return opr;
    }

}
