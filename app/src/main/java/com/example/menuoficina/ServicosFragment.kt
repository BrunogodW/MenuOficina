package com.example.menuoficina

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.example.menuoficina.databinding.FragmentServicosBinding

class ServicosFragment : Fragment() {

    private var _binding: FragmentServicosBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentServicosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val listaServicos = listOf(
            ServicoModel(1, "Troca de Óleo e Filtro", "VW Gol 1.6 - ABC-1234", "Concluído", "R$ 250,00"),
            ServicoModel(2, "Alinhamento 3D e Balanceamento", "Honda Civic 2.0 - XYZ-5678", "Em Andamento", "R$ 180,00"),
            ServicoModel(3, "Revisão do Sistema de Freios", "Toyota Corolla - KKL-9012", "Aguardando Peça", "R$ 420,00"),
            ServicoModel(4, "Diagnóstico de Injeção Eletrônica", "Chevrolet Onix 1.0 - MNO-3456", "Em Andamento", "R$ 300,00"),
            ServicoModel(5, "Higienização do Ar Condicionado", "Fiat Uno 1.0 - QRS-7890", "Concluído", "R$ 150,00")
        )

        val adapter = ServicoAdapter(listaServicos) { anchorView, servico ->
            exibirPopupMenuServico(anchorView, servico)
        }

        binding.recyclerViewServicos.adapter = adapter
    }

    private fun exibirPopupMenuServico(anchorView: View, servico: ServicoModel) {
        val popup = PopupMenu(requireContext(), anchorView)
        popup.menuInflater.inflate(R.menu.item_popup_menu, popup.menu)

        popup.setOnMenuItemClickListener { item ->
            when (item.itemId) {
                R.id.popup_detalhes -> {
                    Toast.makeText(
                        requireContext(),
                        "Detalhes do serviço: ${servico.nome} (${servico.veiculo})",
                        Toast.LENGTH_SHORT
                    ).show()
                    true
                }
                R.id.popup_editar -> {
                    Toast.makeText(
                        requireContext(),
                        "Editar OS #${servico.id}: ${servico.nome}",
                        Toast.LENGTH_SHORT
                    ).show()
                    true
                }
                R.id.popup_compartilhar -> {
                    Toast.makeText(
                        requireContext(),
                        "Compartilhando ordem de serviço #${servico.id}",
                        Toast.LENGTH_SHORT
                    ).show()
                    true
                }
                R.id.popup_excluir -> {
                    Toast.makeText(
                        requireContext(),
                        "Excluir ordem de serviço #${servico.id}",
                        Toast.LENGTH_SHORT
                    ).show()
                    true
                }
                else -> false
            }
        }
        popup.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
