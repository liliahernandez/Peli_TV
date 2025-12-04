package com.example.nefis

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import android.net.Uri
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.MediaController
import android.widget.TextView
import android.widget.VideoView

class PlayActivity : FragmentActivity() {

    companion object {
        const val MOVIE_EXTRA = "extra:movie"
    }

    private lateinit var videoView: VideoView
    private lateinit var infoPanel: View
    private lateinit var videoTitle: TextView
    private lateinit var videoSubtitle: TextView
    private lateinit var videoDescription: TextView
    private val handler = Handler(Looper.getMainLooper())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        // Inicializar vistas
        videoView = findViewById(R.id.videoView)
        infoPanel = findViewById(R.id.infoPanel)
        videoTitle = findViewById(R.id.videoTitle)
        videoSubtitle = findViewById(R.id.videoSubtitle)
        videoDescription = findViewById(R.id.videoDescription)

        // Obtener datos del video
        val video: Video? = intent.getParcelableExtra(MOVIE_EXTRA)

        if (video != null) {
            // Configurar información del video
            videoTitle.text = video.title
            videoSubtitle.text = video.subtitle
            videoDescription.text = video.description

            // Configurar reproducción
            val path = "android.resource://$packageName/${video.video}"
            val uri = Uri.parse(path)

            val mediaController = MediaController(this)
            mediaController.setAnchorView(videoView)
            videoView.setMediaController(mediaController)

            videoView.setVideoURI(uri)
            videoView.requestFocus()
            videoView.start()

            // Auto-ocultar el panel de información después de 5 segundos
            handler.postDelayed({
                hideInfoPanel()
            }, 5000)

            // Mostrar panel al tocar la pantalla
            videoView.setOnClickListener {
                toggleInfoPanel()
            }
        }
    }

    private fun hideInfoPanel() {
        infoPanel.animate()
            .alpha(0f)
            .translationY(infoPanel.height.toFloat())
            .setDuration(300)
            .withEndAction {
                infoPanel.visibility = View.GONE
            }
            .start()
    }

    private fun showInfoPanel() {
        infoPanel.visibility = View.VISIBLE
        infoPanel.animate()
            .alpha(1f)
            .translationY(0f)
            .setDuration(300)
            .start()

        // Auto-ocultar después de 5 segundos
        handler.removeCallbacksAndMessages(null)
        handler.postDelayed({
            hideInfoPanel()
        }, 5000)
    }

    private fun toggleInfoPanel() {
        if (infoPanel.visibility == View.VISIBLE) {
            hideInfoPanel()
        } else {
            showInfoPanel()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        handler.removeCallbacksAndMessages(null)
    }
}