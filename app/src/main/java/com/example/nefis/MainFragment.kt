package com.example.nefis

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.leanback.app.BrowseSupportFragment
import androidx.leanback.widget.ArrayObjectAdapter
import androidx.leanback.widget.HeaderItem
import androidx.leanback.widget.ListRow
import androidx.leanback.widget.ListRowPresenter
import androidx.leanback.widget.OnItemViewClickedListener
import androidx.leanback.widget.OnItemViewSelectedListener
import androidx.leanback.widget.Presenter
import androidx.leanback.widget.Row
import androidx.leanback.widget.RowPresenter
import androidx.leanback.app.BackgroundManager
import androidx.core.content.ContextCompat
import android.util.DisplayMetrics
import androidx.leanback.widget.BaseGridView

class MainFragment : BrowseSupportFragment() {

    private lateinit var backgroundManager: BackgroundManager
    private lateinit var metrics: DisplayMetrics

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        prepareBackgroundManager()
        setupEventListeners()
        
        title = "Music Legends TV"

        val rowsAdapter = ArrayObjectAdapter(CustomListRowPresenter())

        // =================================================================
        // 1. TOKIO HOTEL (5 Álbumes distintos)
        // =================================================================
        val listaTokio = listOf(
            Video("Monsoon", "Album: Schrei (2005)", R.mipmap.tokio1, "El debut que conquistó el mundo.", R.raw.hotel1),
            Video("Ready, Set, Go!", "Album: Zimmer 483 (2007)", R.mipmap.tokio2, "La consolidación del rock alemán.", R.raw.hotel2),
            Video("Automatic", "Album: Humanoid (2009)", R.mipmap.tokio3, "Un giro futurista y electrónico.", R.raw.hotel3),
            Video("Love Who Loves You Back", "Album: Kings of Suburbia (2014)", R.mipmap.tokio4, "Pop alternativo y maduro.", R.raw.hotel4),
            Video("Something New", "Album: Dream Machine (2017)", R.mipmap.tokio5, "Sonidos de sintetizador oníricos.", R.raw.hotel5)
        )
        rowsAdapter.add(crearFila(1, "Tokio Hotel Discografía", listaTokio))


        // =================================================================
        // 2. MICHAEL JACKSON (5 Eras Legendarias)
        // =================================================================
        val listaMJ = listOf(
            Video("Don't Stop 'Til You Get Enough", "Album: Off the Wall (1979)", R.mipmap.michael1, "El inicio de la leyenda en solitario.", R.raw.jackson1),
            Video("Billie Jean", "Album: Thriller (1982)", R.mipmap.michael2, "El álbum más vendido de la historia.", R.raw.jackson2),
            Video("Smooth Criminal", "Album: Bad (1987)", R.mipmap.michael3, "El paso antigravedad.", R.raw.jackson3),
            Video("Black or White", "Album: Dangerous (1991)", R.mipmap.michael4, "Rompiendo barreras raciales.", R.raw.jackson4),
            Video("You Rock My World", "Album: Invincible (2001)", R.mipmap.michael5, "Su último álbum de estudio.", R.raw.jackson5)
        )
        rowsAdapter.add(crearFila(2, "Michael Jackson Hits", listaMJ))


        // =================================================================
        // 3. LANA DEL REY (5 Álbumes Melancólicos)
        // =================================================================
        val listaLana = listOf(
            Video("Video Games", "Album: Born to Die (2012)", R.mipmap.lana1, "El nacimiento del indie pop vintage.", R.raw.rey1),
            Video("Ride", "Album: Paradise (2012)", R.mipmap.lana2, "Libertad en la carretera.", R.raw.rey2),
            Video("West Coast", "Album: Ultraviolence (2014)", R.mipmap.lana3, "Guitarras psicodélicas y oscuras.", R.raw.rey3),
            Video("High by the Beach", "Album: Honeymoon (2015)", R.mipmap.lana4, "Trap suave y brisa marina.", R.raw.rey4),
            Video("Love", "Album: Lust for Life (2017)", R.mipmap.lana5, "Una oda a la juventud y el amor.", R.raw.rey5)
        )
        rowsAdapter.add(crearFila(3, "Lana del Rey Vibes", listaLana))


        // =================================================================
        // 4. AVICII (5 Grandes Éxitos/Álbumes)
        // =================================================================
        val listaAvicii = listOf(
            Video("Levels", "Single Debut (2011)", R.mipmap.avicii1, "El track que cambió el EDM para siempre.", R.raw.avicii1),
            Video("Wake Me Up", "Album: True (2013)", R.mipmap.avicii2, "House mezclado con Country.", R.raw.avicii2),
            Video("The Nights", "EP: The Days / Nights (2014)", R.mipmap.avicii3, "Vive una vida que recordarás.", R.raw.avicii3),
            Video("Waiting For Love", "Album: Stories (2015)", R.mipmap.avicii4, "Narrativa visual y sonora.", R.raw.avicii4),
            Video("SOS", "Album: Tim (2019)", R.mipmap.avicii5, "Álbum póstumo lleno de emoción.", R.raw.avicci5)
        )
        rowsAdapter.add(crearFila(4, "Avicii Legacy", listaAvicii))


        // =================================================================
        // 5. ARIANA GRANDE (5 Eras Pop)
        // =================================================================
        val listaAriana = listOf(
            Video("The Way", "Album: Yours Truly (2013)", R.mipmap.ariana1, "R&B dulce de los 90s.", R.raw.grande1),
            Video("Break Free", "Album: My Everything (2014)", R.mipmap.ariana2, "Explosión pop galáctica.", R.raw.grande2),
            Video("Side to Side", "Album: Dangerous Woman (2016)", R.mipmap.ariana3, "Una Ariana más atrevida.", R.raw.grande3),
            Video("No Tears Left to Cry", "Album: Sweetener (2018)", R.mipmap.ariana4, "Sanando a través de la música.", R.raw.grande4),
            Video("Thank U, Next", "Album: Thank U, Next (2019)", R.mipmap.ariana5, "El himno de la superación personal.", R.raw.grande5)
        )
        rowsAdapter.add(crearFila(5, "Ariana Grande Pop", listaAriana))


        // Configuración final
        adapter = rowsAdapter

        onItemViewClickedListener = OnItemViewClickedListener { _, video, _, _ ->
            val intent = Intent(requireContext(), PlayActivity::class.java).apply {
                putExtra(PlayActivity.MOVIE_EXTRA, video as Video)
            }
            startActivity(intent)
        }
    }

    private fun prepareBackgroundManager() {
        backgroundManager = BackgroundManager.getInstance(requireActivity())
        backgroundManager.attach(requireActivity().window)
        metrics = DisplayMetrics()
        requireActivity().windowManager.defaultDisplay.getMetrics(metrics)
    }

    private fun setupEventListeners() {
        onItemViewSelectedListener = OnItemViewSelectedListener { itemViewHolder, item, rowViewHolder, row ->
            if (item is Video) {
                updateBackground(item.image)
            }
        }
    }

    private fun updateBackground(drawableId: Int) {
        backgroundManager.drawable = ContextCompat.getDrawable(requireContext(), drawableId)
    }

    // --- Función Auxiliar ---
    private fun crearFila(id: Long, titulo: String, videos: List<Video>): ListRow {
        val cardPresenter = Card()
        val listRowAdapter = ArrayObjectAdapter(cardPresenter)
        listRowAdapter.addAll(0, videos)

        val header = HeaderItem(id, titulo)
        return ListRow(header, listRowAdapter)
    }
    }

    // --- Custom Presenter for Centering ---
    class CustomListRowPresenter : ListRowPresenter() {
        override fun onBindRowViewHolder(holder: RowPresenter.ViewHolder, item: Any) {
            super.onBindRowViewHolder(holder, item)
            val vh = holder as ListRowPresenter.ViewHolder
            val gridView = vh.gridView
            gridView.windowAlignment = BaseGridView.WINDOW_ALIGN_NO_EDGE
            gridView.windowAlignmentOffsetPercent = 50f
            gridView.itemAlignmentOffsetPercent = 50f
        }
    }

