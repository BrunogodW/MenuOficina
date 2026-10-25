package com.example.menuoficina

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.widget.PopupMenu
import androidx.fragment.app.Fragment
import com.example.menuoficina.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Configuração do PopupMenu no botão
        binding.btnTriggerPopup.setOnClickListener { v ->
            showPopupMenu(v, "Geral - Botão")
        }

        // Configuração do PopupMenu ao pressionar e segurar no Card (Long Click)
        binding.cardPopupMenuDemo.setOnLongClickListener { v ->
            showPopupMenu(v, "Geral - Pressionar e Segurar")
            true
        }
    }

    private fun showPopupMenu(anchorView: View, origem: String) {
        val popup = PopupMenu(requireContext(), anchorView)
        popup.menuInflater.inflate(R.menu.item_popup_menu, popup.menu)

        popup.setOnMenuItemClickListener { menuItem ->
            when (menuItem.itemId) {
                R.id.popup_detalhes -> {
                    Toast.makeText(
                        requireContext(),
                        "[$origem] Detalhes selecionados",
                        Toast.LENGTH_SHORT
                    ).show()
                    true
                }
                R.id.popup_editar -> {
                    Toast.makeText(
                        requireContext(),
                        "[$origem] Editar selecionado",
                        Toast.LENGTH_SHORT
                    ).show()
                    true
                }
                R.id.popup_compartilhar -> {
                    Toast.makeText(
                        requireContext(),
                        "[$origem] Compartilhar selecionado",
                        Toast.LENGTH_SHORT
                    ).show()
                    true
                }
                R.id.popup_excluir -> {
                    Toast.makeText(
                        requireContext(),
                        "[$origem] Excluir selecionado",
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
