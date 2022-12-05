package com.br.cadastros;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class PessoaRVAdapter extends ListAdapter<PessoaModal, PessoaRVAdapter.ViewHolder>{
    private OnItemClickListener listener;

    PessoaRVAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<PessoaModal> DIFF_CALLBACK = new DiffUtil.ItemCallback<PessoaModal>() {
        @Override
        public boolean areItemsTheSame(PessoaModal oldItem, PessoaModal newItem) {
            return oldItem.getId() == newItem.getId();
        }

        @Override
        public boolean areContentsTheSame(PessoaModal oldItem, PessoaModal newItem) {
            return oldItem.getPessoaName().equals(newItem.getPessoaName());
        }
    };
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pessoa_rv_item, parent, false);
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PessoaModal model = getPessoaAt(position);
        holder.pessoaNameTV.setText(model.getPessoaName());
    }

    public PessoaModal getPessoaAt(int position) {
        return getItem(position);
    }
    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView pessoaNameTV;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            pessoaNameTV = itemView.findViewById(R.id.idTVNomePessoa);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position !=  RecyclerView.NO_POSITION) {
                        listener.onItemClick(getItem(position));
                    }
                }
            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(PessoaModal model);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

}
