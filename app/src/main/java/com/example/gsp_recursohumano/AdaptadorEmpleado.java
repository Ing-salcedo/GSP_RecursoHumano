package com.example.gsp_recursohumano;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AdaptadorEmpleado extends RecyclerView.Adapter<AdaptadorEmpleado.EmpleadoViewHolder> {

    private ArrayList<Empleado> empleados;
    private OnEmpleadoClickListener clickListener;

    public AdaptadorEmpleado(ArrayList<Empleado> empleados, OnEmpleadoClickListener clickListener){
        this.empleados = empleados;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public EmpleadoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_empleado,parent,false);
       return new EmpleadoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull EmpleadoViewHolder holder, int position) {
        Empleado e = empleados.get(position);
        holder.cedula.setText(e.getCedula());
        holder.nombre.setText(e.getNombre());
        holder.apellido.setText(e.getApellido());
        holder.vinculacion.setText(e.getVinculacion());
        holder.DiruOfi.setText(e.getDiruofi());
        holder.celular.setText(e.getCelular());
        holder.correo.setText(e.getCorreo());
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickListener.onEmpleadoClick(e);
            }
        });
    }

    @Override
    public int getItemCount() {
        return empleados.size();
    }

    public static class EmpleadoViewHolder extends RecyclerView.ViewHolder {
        private CircleImageView foto;
        private TextView cedula, nombre, apellido, vinculacion, DiruOfi, celular, correo;
        private View v;

        public EmpleadoViewHolder(View itemView){
            super(itemView);
            v = itemView;
            foto = v.findViewById(R.id.imgFotoItem);
            cedula = v.findViewById(R.id.lblCedulaItem);
            nombre = v.findViewById(R.id.lblNombreItem);
            apellido = v.findViewById(R.id.lblApellidoItem);
            vinculacion = v.findViewById(R.id.lblVinculacionItem);
            DiruOfi = v.findViewById(R.id.lblDiruOfiItem);
            celular = v.findViewById(R.id.lblCelularItem);
            correo = v.findViewById(R.id.lblCorreoItem);
        }
    }
    public interface OnEmpleadoClickListener{
        void onEmpleadoClick(Empleado e);
    }
}
