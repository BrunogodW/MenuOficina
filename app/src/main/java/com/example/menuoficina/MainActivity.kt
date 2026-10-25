package com.example.menuoficina

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.Fragment
import com.example.menuoficina.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)

        // Aplicar Insets de Edge-to-Edge para Toolbar e BottomNavigationView
        ViewCompat.setOnApplyWindowInsetsListener(binding.main) { _, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            binding.appBarLayout.setPadding(systemBars.left, systemBars.top, systemBars.right, 0)
            binding.bottomNavigation.setPadding(systemBars.left, 0, systemBars.right, systemBars.bottom)
            insets
        }

        // Configuração da App Bar / Toolbar Options Menu via MenuProvider (Modo Moderno)
        addMenuProvider(object : MenuProvider {
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.toolbar_options_menu, menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                return when (menuItem.itemId) {
                    R.id.action_search -> {
                        Toast.makeText(this@MainActivity, "Ação da App Bar: Pesquisar", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.action_filter -> {
                        Toast.makeText(this@MainActivity, "Ação da App Bar: Filtrar", Toast.LENGTH_SHORT).show()
                        true
                    }
                    R.id.action_about -> {
                        Toast.makeText(this@MainActivity, "Oficina Auto Control v1.0", Toast.LENGTH_LONG).show()
                        true
                    }
                    else -> false
                }
            }
        })

        // Configuração do BottomNavigationView e troca de Fragment via setOnItemSelectedListener
        binding.bottomNavigation.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.nav_home -> {
                    replaceFragment(HomeFragment(), "Início")
                    true
                }
                R.id.nav_servicos -> {
                    replaceFragment(ServicosFragment(), "Serviços da Oficina")
                    true
                }
                R.id.nav_panorama -> {
                    replaceFragment(PanoramaFragment(), "Panorama de Navegação")
                    true
                }
                else -> false
            }
        }

        // Carregar o fragment inicial ao abrir o app
        if (savedInstanceState == null) {
            binding.bottomNavigation.selectedItemId = R.id.nav_home
        }
    }

    private fun replaceFragment(fragment: Fragment, title: String) {
        supportActionBar?.title = title
        supportFragmentManager.beginTransaction()
            .setCustomAnimations(
                android.R.anim.fade_in,
                android.R.anim.fade_out
            )
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
