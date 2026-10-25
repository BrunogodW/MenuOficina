package com.example.menuoficina

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.menuoficina.databinding.ItemServicoBinding

class ServicoAdapter(
    private val items: List<ServicoModel>,
    private val onOptionClicked: (view: View, servico: ServicoModel) -> Unit
) : RecyclerView.Adapter<ServicoAdapter.ServicoViewHolder>() {

    class ServicoViewHolder(val binding: ItemServicoBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServicoViewHolder {
        val binding = ItemServicoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return ServicoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ServicoViewHolder, position: Int) {
        val servico = items[position]
        with(holder.binding) {
            tvNomeServico.text = servico.nome
            tvVeiculo.text = root.context.getString(R.string.label_veiculo, servico.veiculo)
            tvStatus.text = servico.status
            tvPreco.text = servico.preco

            // Abrir PopupMenu ao tocar no botão de opções (3 pontos)
            btnMenuOptions.setOnClickListener { view ->
                onOptionClicked(view, servico)
            }

            // Abrir PopupMenu ao pressionar e segurar (long click) no card inteiro
            cardServico.setOnLongClickListener { view ->
                onOptionClicked(view, servico)
                true
            }

            // Ação de clique no card
            cardServico.setOnClickListener {
                onOptionClicked(btnMenuOptions, servico)
            }
        }
    }

    override fun getItemCount(): Int = items.size
}
