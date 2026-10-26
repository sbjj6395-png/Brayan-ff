package com.example.mthgamingpanel

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.graphics.drawable.GradientDrawable
import android.view.Gravity
import android.view.View
import android.widget.*

class MainActivity : Activity() {
    private val bg = Color.rgb(8, 13, 24)
    private val panel = Color.rgb(17, 25, 42)
    private val blue = Color.rgb(41, 182, 246)
    private val muted = Color.rgb(148, 163, 184)
    private lateinit var status: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = bg
        window.navigationBarColor = bg

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(18), dp(18), dp(18), dp(18))
            setBackgroundColor(bg)
        }
        val header = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL
            gravity = Gravity.CENTER_VERTICAL
            setPadding(dp(16), dp(14), dp(16), dp(14))
            background = rounded(panel, 18, blue, 1)
        }
        val logo = TextView(this).apply {
            text = "MTH\nTEAM"
            setTextColor(blue)
            textSize = 20f
            typeface = Typeface.DEFAULT_BOLD
            gravity = Gravity.CENTER
        }
        header.addView(logo, LinearLayout.LayoutParams(dp(68), dp(54)))
        val titleBox = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; setPadding(dp(12), 0, 0, 0) }
        titleBox.addView(TextView(this).apply {
            text = "GAMING PANEL"
            setTextColor(Color.WHITE); textSize = 18f; typeface = Typeface.DEFAULT_BOLD
        })
        titleBox.addView(TextView(this).apply {
            text = "PRACTICE TOOL  •  v1.0"
            setTextColor(muted); textSize = 11f
        })
        header.addView(titleBox, LinearLayout.LayoutParams(0, -2, 1f))
        val pill = TextView(this).apply {
            text = "● READY"
            setTextColor(Color.rgb(105, 240, 174)); textSize = 10f
            background = rounded(Color.rgb(20, 55, 52), 30, Color.TRANSPARENT, 0)
            setPadding(dp(10), dp(7), dp(10), dp(7))
        }
        header.addView(pill)
        root.addView(header)

        val scroll = ScrollView(this).apply { isFillViewport = true }
        val content = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL; setPadding(0, dp(16), 0, dp(10)) }
        content.addView(sectionTitle("PRACTICE SETTINGS"))
        content.addView(toggleRow("Aim Trainer", "Muestra una rutina de práctica dentro de esta app", "aim", false))
        content.addView(toggleRow("Target Markers", "Activa marcadores de objetivos de demostración", "markers", true))
        content.addView(toggleRow("FPS Counter", "Muestra un contador de rendimiento simulado", "fps", false))
        content.addView(toggleRow("Tap Feedback", "Vibración visual al tocar los controles", "feedback", true))

        content.addView(sectionTitle("PERSONALIZATION"))
        val colorCard = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            background = rounded(panel, 14, Color.rgb(35, 48, 70), 1)
            setPadding(dp(16), dp(14), dp(16), dp(14))
        }
        colorCard.addView(TextView(this).apply {
            text = "Crosshair color"
            setTextColor(Color.WHITE); textSize = 15f; typeface = Typeface.DEFAULT_BOLD
        })
        colorCard.addView(TextView(this).apply {
            text = "Elige un color de referencia para la interfaz de práctica."
            setTextColor(muted); textSize = 12f; setPadding(0, dp(4), 0, dp(12))
        })
        val colors = LinearLayout(this).apply { orientation = LinearLayout.HORIZONTAL; gravity = Gravity.CENTER_VERTICAL }
        listOf("Cyan" to blue, "Green" to Color.rgb(105, 240, 174), "Purple" to Color.rgb(179, 136, 255), "Red" to Color.rgb(255, 99, 132)).forEach { (name, color) ->
            val b = TextView(this).apply {
                text = "● $name"; textSize = 11f; setTextColor(color)
                background = rounded(Color.rgb(27, 38, 58), 24, color, 1)
                setPadding(dp(9), dp(8), dp(9), dp(8))
                setOnClickListener { status.text = "Color seleccionado: $name"; status.setTextColor(color) }
            }
            val lp = LinearLayout.LayoutParams(0, -2, 1f); lp.marginEnd = dp(5); colors.addView(b, lp)
        }
        colorCard.addView(colors)
        content.addView(colorCard)

        val preview = TextView(this).apply {
            text = "▶  OPEN PRACTICE PREVIEW"
            gravity = Gravity.CENTER
            textSize = 14f; typeface = Typeface.DEFAULT_BOLD; setTextColor(Color.BLACK)
            background = rounded(blue, 14, blue, 1)
            setPadding(dp(12), dp(16), dp(12), dp(16))
            setOnClickListener { showPracticePreview() }
        }
        val previewLp = LinearLayout.LayoutParams(-1, -2); previewLp.topMargin = dp(16)
        content.addView(preview, previewLp)
        status = TextView(this).apply {
            text = "Listo. Los cambios afectan solo a esta app de demostración."
            setTextColor(muted); textSize = 12f; gravity = Gravity.CENTER
            setPadding(dp(8), dp(14), dp(8), dp(4))
        }
        content.addView(status)
        val footer = TextView(this).apply {
            text = "MTH GAMING PANEL  •  STANDALONE DEMO"
            setTextColor(Color.rgb(75, 91, 115)); textSize = 10f; gravity = Gravity.CENTER
            setPadding(0, dp(12), 0, dp(4))
        }
        content.addView(footer)
        scroll.addView(content)
        root.addView(scroll, LinearLayout.LayoutParams(-1, 0, 1f))
        setContentView(root)
    }

    private fun sectionTitle(text: String) = TextView(this).apply {
        this.text = text; setTextColor(blue); textSize = 11f; typeface = Typeface.DEFAULT_BOLD
        setPadding(dp(3), dp(8), 0, dp(10))
    }

    private fun toggleRow(title: String, subtitle: String, key: String, initial: Boolean): View {
        val row = LinearLayout(this).apply {
            orientation = LinearLayout.HORIZONTAL; gravity = Gravity.CENTER_VERTICAL
            background = rounded(panel, 14, Color.rgb(35, 48, 70), 1)
            setPadding(dp(15), dp(13), dp(12), dp(13))
        }
        val labels = LinearLayout(this).apply { orientation = LinearLayout.VERTICAL }
        labels.addView(TextView(this).apply {
            text = title; setTextColor(Color.WHITE); textSize = 15f; typeface = Typeface.DEFAULT_BOLD
        })
        labels.addView(TextView(this).apply {
            text = subtitle; setTextColor(muted); textSize = 11f; setPadding(0, dp(4), dp(6), 0)
        })
        row.addView(labels, LinearLayout.LayoutParams(0, -2, 1f))
        val toggle = Switch(this).apply {
            isChecked = initial
            setOnCheckedChangeListener { _, checked ->
                status.text = "$title: ${if (checked) "ACTIVADO" else "DESACTIVADO"}"
                status.setTextColor(if (checked) blue else muted)
            }
        }
        row.addView(toggle)
        val lp = LinearLayout.LayoutParams(-1, -2); lp.bottomMargin = dp(8)
        row.layoutParams = lp
        return row
    }

    private fun showPracticePreview() {
        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL; gravity = Gravity.CENTER
            setPadding(dp(24), dp(24), dp(24), dp(24)); background = rounded(panel, 18, blue, 1)
        }
        layout.addView(TextView(this).apply {
            text = "◎"; textSize = 72f; setTextColor(blue); gravity = Gravity.CENTER
        })
        layout.addView(TextView(this).apply {
            text = "PRACTICE MODE"; textSize = 20f; typeface = Typeface.DEFAULT_BOLD
            setTextColor(Color.WHITE); gravity = Gravity.CENTER
        })
        layout.addView(TextView(this).apply {
            text = "Demo de interfaz independiente.\nToca el objetivo para practicar."
            textSize = 13f; setTextColor(muted); gravity = Gravity.CENTER; setPadding(0, dp(8), 0, dp(18))
        })
        val target = TextView(this).apply {
            text = "＋\nTARGET"; textSize = 22f; gravity = Gravity.CENTER; setTextColor(Color.BLACK)
            background = rounded(blue, 100, blue, 2); setPadding(dp(18), dp(18), dp(18), dp(18))
            setOnClickListener { text = "✓\nHIT!"; status.text = "¡Buen tiro! Objetivo de práctica alcanzado." }
        }
        layout.addView(target, LinearLayout.LayoutParams(dp(120), dp(120)))
        val dialog = android.app.AlertDialog.Builder(this).setView(layout).setPositiveButton("Cerrar", null).create()
        dialog.show()
    }

    private fun rounded(color: Int, radius: Int, stroke: Int, strokeWidth: Int): GradientDrawable = GradientDrawable().apply {
        setColor(color); cornerRadius = dp(radius).toFloat()
        if (strokeWidth > 0) setStroke(dp(strokeWidth), stroke)
    }
    private fun dp(v: Int): Int = (v * resources.displayMetrics.density).toInt()
}
