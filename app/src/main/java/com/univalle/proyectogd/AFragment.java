package com.univalle.proyectogd;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link AFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public AFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AFragment newInstance(String param1, String param2) {
        AFragment fragment = new AFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);

        }



    }
    Button calcular, imprimir;
    EditText porcionPapas, pollo;
    TextView Resultado;
    Double UnidadPapas, UnidadPollo, Res1, Res2, Res;
    String val;
    private EditText et1;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_a, container, false);
        Resultado = (TextView) view.findViewById(R.id.txtResultado);
        calcular = (Button) view.findViewById(R.id.btnCalcular);
        porcionPapas = (EditText) view.findViewById(R.id.PorcionPapas);
        pollo = (EditText) view.findViewById(R.id.Pollo);

        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    String auxPapas = porcionPapas.getText().toString();
                    UnidadPapas = Double.parseDouble(auxPapas);
                }catch (NumberFormatException nfe){}
                try{
                    String auxPollo = pollo.getText().toString();
                    UnidadPollo = Double.parseDouble(auxPollo);
                }catch (NumberFormatException nfe){}

                Res1 = (UnidadPapas * 5);
                Res2 = (UnidadPollo * 12);
                Res = Res1 + Res2;
                val = String.valueOf(Res);
                Resultado.setText("Total a pagar: " + val + "Bs");
            }
        });

        imprimir = (Button) view.findViewById(R.id.btnGuardar);
        imprimir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                grabar(Resultado);
            }
        });
        return view;
    }
    public void grabar(View view) {
        String nomarchivo = Resultado.getText().toString();

        try {
            File tarjeta = Environment.getExternalStorageDirectory();

            //Toast.makeText(this,tarjeta.getAbsolutePath(),Toast.LENGTH_LONG).show();
            File file = new File(tarjeta.getAbsolutePath(), nomarchivo);
            OutputStreamWriter osw = new OutputStreamWriter(
                    new FileOutputStream(file));
            osw.write(Resultado.toString());
            osw.flush();
            osw.close();
            Toast.makeText(getActivity(), "Los datos fueron grabados correctamente...", Toast.LENGTH_SHORT).show();
            Resultado.setText("");
        } catch (IOException ioe) {
            Toast.makeText(getActivity(), "No se pudo grabar...", Toast.LENGTH_SHORT).show();
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
