package red.lisgar.contactos.adaptadores;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import red.lisgar.contactos.R;
import red.lisgar.contactos.RegistrarContactoActivity;
import red.lisgar.contactos.modelo.Contacto;

public class ListaContactosAdapter extends RecyclerView.Adapter<ListaContactosAdapter.ContactoViewHolder> {
    ArrayList<Contacto> ListaContactos;

    public ListaContactosAdapter(ArrayList<Contacto> ListaContactos) {
        this.ListaContactos = ListaContactos;
    }

    @NonNull
    @Override
    public ContactoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_listcontacto, null, false);
        return new ContactoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactoViewHolder holder, int position) {
        holder.viewNombre.setText(ListaContactos.get(position).getNombre());
        holder.viewTelefono.setText(ListaContactos.get(position).getTelefono());
        holder.viewCorreo.setText(ListaContactos.get(position).getCorreo_electronico());
    }

    @Override
    public int getItemCount() {
        return ListaContactos.size();
    }

    public class ContactoViewHolder extends RecyclerView.ViewHolder {
        TextView viewNombre, viewTelefono, viewCorreo;

        public ContactoViewHolder(@NonNull View itemView) {
            super(itemView);
            viewNombre = itemView.findViewById(R.id.ViewNombre);
            viewTelefono = itemView.findViewById(R.id.ViewTelefono);
            viewCorreo = itemView.findViewById(R.id.ViewCorreo);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Context context = view.getContext();
                    Intent intent = new Intent(context, RegistrarContactoActivity.class);
                    intent.putExtra("ID", ListaContactos.get(getAdapterPosition()).getId());
                    context.startActivity(intent);
                }
            });
        }
    }
}
