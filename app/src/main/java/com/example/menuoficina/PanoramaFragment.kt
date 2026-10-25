package com.example.menuoficina

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.menuoficina.databinding.FragmentPanoramaBinding
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class PanoramaFragment : Fragment() {

    private var _binding: FragmentPanoramaBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPanoramaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnExplainConcepts.setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Conceitos do Navigation Component")
                .setMessage(
                    "O Navigation Component é a solução oficial da Google para gerenciar " +
                    "navegação em apps Android modernos.\n\n" +
                    "• NavHostFragment: Funciona como container da tela atual.\n" +
                    "• NavController: Executa chamadas como navController.navigate(R.id.action_home_to_details).\n" +
                    "• Safe Args: Passagem de dados type-safe com classes geradas automaticamente.\n\n" +
                    "O Navigation Drawer complementa a estrutura adicionando um menu lateral para " +
                    "destinos adicionais ou de apoio."
                )
                .setPositiveButton("Compreendido", null)
                .show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
