package com.example.maris.appmaryjencontact;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class ContactoAdapter extends RecyclerView.Adapter<ContactoAdapter.ContactoViewHolder>{

    private ArrayList<Contacto> contacto;
    private Context context;
    private static boolean favorito = false;

    public ContactoAdapter(ArrayList<Contacto> contacto, Context context){
        this.contacto=contacto;
        this.context=context;
    }

    @Override
    public ContactoAdapter.ContactoViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardviewcontact,parent,false);
        return (new ContactoViewHolder(view));
    }

    public void onBindViewHolder(final ContactoViewHolder holder, final int position){

        holder.imagen_de_contacto.setImageResource(contacto.get(position).getImagencontacto());
        holder.nombre.setText((contacto.get(position).getNombre()));

        if (contacto.get(position).isLista_favoritos()){
            holder.boton_favorito.setImageResource(R.drawable.fav_mar);
        }
        else {
            holder.boton_favorito.setImageResource(R.drawable.fav);
        }

        holder.boton_favorito.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Listafavorito(position)){
                    holder.boton_favorito.setImageResource(R.drawable.fav_mar);
                    ((MainActivity)context).agregar_favorito(contacto.get(position));
                }
                else {
                    holder.boton_favorito.setImageResource(R.drawable.fav);
                    ((MainActivity)context).eliminar_favorito(contacto.get(position).getNombre());
                }
            }
        });

    }

    public int getItemCount(){
        return contacto.size();
    }

    public class ContactoViewHolder extends RecyclerView.ViewHolder{

        CardView cardView;
        CircleImageView imagen_de_contacto;
        TextView nombre;
        ImageButton boton_informacion;
        ImageButton boton_favorito;

        public ContactoViewHolder(View itemView){
            super(itemView);

            cardView=itemView.findViewById(R.id.card_view);
            imagen_de_contacto=itemView.findViewById(R.id.foto);
            nombre=itemView.findViewById(R.id.nombrecontacto);
            boton_informacion=itemView.findViewById(R.id.btn_informacion);
            boton_favorito=itemView.findViewById(R.id.btn_favorito);

        }

    }

    public boolean Listafavorito(int posicion){
        contacto.get(posicion).setLista_favoritos(!contacto.get(posicion).isLista_favoritos());
        return contacto.get(posicion).isLista_favoritos();
    }

    public void setT(){
        favorito = true;
    }

    public void setF(){
        favorito = false;
    }

    public boolean Addfavorito(){
        return favorito;
    }


}