package com.example.nefis

import android.graphics.Color
import android.view.Gravity
import android.view.ViewGroup
import android.widget.TextView
import androidx.leanback.widget.ImageCardView
import androidx.leanback.widget.Presenter

class Card : Presenter() {

    override fun onCreateViewHolder(parent: ViewGroup): ViewHolder {
        val card = ImageCardView(parent.context)
        card.isFocusable = true
        card.isFocusableInTouchMode = true
        return ViewHolder(card)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, item: Any?) {
        val video = item as Video
        val cardView = viewHolder.view as ImageCardView

        // Configuramos textos iniciales
        cardView.titleText = video.title
        cardView.contentText = video.subtitle

        // Dimensiones tipo cine
        cardView.setMainImageDimensions(313, 176)
        cardView.setBackgroundResource(video.image)

        // Configuración visual de la tarjeta
        cardView.cardType = ImageCardView.CARD_TYPE_INFO_UNDER_WITH_EXTRA
        cardView.infoVisibility = ImageCardView.CARD_REGION_VISIBLE_SELECTED

        // Estilizar los textos (centrado y color)
        val titleView = cardView.findViewById<TextView>(androidx.leanback.R.id.title_text)
        val contentView = cardView.findViewById<TextView>(androidx.leanback.R.id.content_text)

        titleView?.gravity = Gravity.CENTER
        titleView?.setTextColor(Color.WHITE)

        contentView?.gravity = Gravity.CENTER
        contentView?.maxLines = 5
        contentView?.setTextColor(Color.WHITE)

        // Animación y cambio dinámico al seleccionar
        cardView.setOnFocusChangeListener { v, hasFocus ->
            if (hasFocus) {
                // Efecto Zoom IN
                v.animate().scaleX(1.15f).scaleY(1.15f).duration = 200

                // Mostrar descripción larga y fondo Magenta Vibrante
                cardView.infoVisibility = ImageCardView.CARD_REGION_VISIBLE_ALWAYS
                cardView.contentText = video.description
                cardView.setInfoAreaBackgroundColor(Color.parseColor("#FFD500F9")) // Magenta vibrante
            } else {
                // Efecto Zoom OUT
                v.animate().scaleX(1.0f).scaleY(1.0f).duration = 200

                // Volver a mostrar solo el subtítulo y fondo gris oscuro
                cardView.infoVisibility = ImageCardView.CARD_REGION_VISIBLE_SELECTED
                cardView.contentText = video.subtitle
                cardView.setInfoAreaBackgroundColor(Color.parseColor("#FF1F1F1F")) // Gris oscuro elegante
            }
        }
    }

    override fun onUnbindViewHolder(viewHolder: ViewHolder) {
        // Limpiar si es necesario
    }
}