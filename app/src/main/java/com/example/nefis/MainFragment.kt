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

class MainFragment : BrowseSupportFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        title = "Tokio Hotel Discography"

        val rowsAdapter = ArrayObjectAdapter(ListRowPresenter())

        // --- ÁLBUM 1: Schrei (2005) - El debut ---
        val albumSchrei = listOf(
            Video("Durch den Monsun", "Schrei", R.mipmap.schrei, "El éxito mundial", R.raw.moonso),
            Video("Schrei", "Grita", R.mipmap.schrei, "Canción principal", R.raw.schrei),
            Video("Rette Mich", "Balada", R.mipmap.schrei, "Sálvame", R.raw.rtm),
            Video("Der letzte Tag", "Rock", R.mipmap.schrei, "El último día", R.raw.dlt),
            Video("Freunde bleiben", "Amistad", R.mipmap.schrei, "Quedemos como amigos", R.raw.fb)
        )
        rowsAdapter.add(crearFila(1, "Álbum: Schrei (2005)", albumSchrei))

        // --- ÁLBUM 2: Zimmer 483 (2007) ---
        val albumZimmer = listOf(
            Video("Übers Ende der Welt", "Ready, Set, Go!", R.mipmap.zimmer483, "Hacia el fin del mundo", R.raw.uedw),
            Video("Spring nicht", "Don't Jump", R.mipmap.zimmer483, "No saltes", R.raw.sn),
            Video("An deiner Seite", "By Your Side", R.mipmap.zimmer483, "A tu lado", R.raw.ads),
            Video("Heilig", "Sacred", R.mipmap.zimmer483, "Sagrado", R.raw.heilig),
            Video("1000 Meere", "Oceans", R.mipmap.zimmer483, "Mil mares", R.raw.oc)
        )
        rowsAdapter.add(crearFila(2, "Álbum: Zimmer 483 (2007)", albumZimmer))

        // --- ÁLBUM 3: Humanoid (2009) - Era Sci-Fi ---
        val albumHumanoid = listOf(
            Video("Automatic", "Auto", R.mipmap.humanoid, "Sonido robótico", R.raw.auto),
            Video("World Behind My Wall", "Mundo", R.mipmap.humanoid, "Detrás de mi pared", R.raw.wbmw),
            Video("Darkside of the Sun", "Live", R.mipmap.humanoid, "Lado oscuro del sol", R.raw.dots),
            Video("Noise", "Ruido", R.mipmap.humanoid, "Grita fuerte", R.raw.noise),
            Video("Alien", "Espacio", R.mipmap.humanoid, "Sentirse extraño", R.raw.alien)
        )
        rowsAdapter.add(crearFila(3, "Álbum: Humanoid (2009)", albumHumanoid))

        // --- ÁLBUM 4: Kings of Suburbia (2014) - Cambio a Pop/Elec ---
        val albumKings = listOf(
            Video("Love Who Loves You Back", "Love", R.mipmap.kos, "Ama a quien te ama", R.raw.lwlyb),
            Video("Girl Got a Gun", "Bang", R.mipmap.kos, "Video polémico", R.raw.ggag),
            Video("Run, Run, Run", "Balada", R.mipmap.kos, "Corre, corre", R.raw.run),
            Video("Stormy Weather", "Clima", R.mipmap.kos, "Tormenta", R.raw.sw),
            Video("The Heart Get No Sleep", "Fiesta", R.mipmap.kos, "Sin dormir", R.raw.thgns)
        )
        rowsAdapter.add(crearFila(4, "Álbum: Kings of Suburbia (2014)", albumKings))

        // --- ÁLBUM 5: Dream Machine (2017) ---
        val albumDream = listOf(
            Video("Something New", "Intro", R.mipmap.dm, "Algo nuevo", R.raw.son),
            Video("What If", "Duda", R.mipmap.dm, "Y si...", R.raw.wi),
            Video("Boy Don't Cry", "Dance", R.mipmap.dm, "Los chicos no lloran", R.raw.bdc),
            Video("Easy", "Chill", R.mipmap.dm, "Fácil", R.raw.easy),
            Video("Elysa", "Triste", R.mipmap.dm, "Canción emotiva", R.raw.elysa)
        )
        rowsAdapter.add(crearFila(5, "Álbum: Dream Machine (2017)", albumDream))

        adapter = rowsAdapter

        onItemViewClickedListener = OnItemViewClickedListener { _, video, _, _ ->
            val intent = Intent(requireContext(), PlayActivity::class.java).apply {
                putExtra(PlayActivity.MOVIE_EXTRA, video as Video)
            }
            startActivity(intent)
        }
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