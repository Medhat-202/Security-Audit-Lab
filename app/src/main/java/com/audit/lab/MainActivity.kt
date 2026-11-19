package com.audit.lab

import android.app.Activity
import android.os.Bundle
import android.graphics.Typeface
import android.view.Gravity
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import java.security.SecureRandom

class MainActivity : Activity() {

    private val random = SecureRandom()
    private lateinit var resultText: TextView
    private var roundNumber = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val layout = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER
            setPadding(24, 32, 24, 32)
            setBackgroundColor(0xFF101827.toInt())
        }

        val title = TextView(this).apply {
            text = "SECURITY AUDIT LAB"
            textSize = 25f
            setTypeface(null, Typeface.BOLD)
            setTextColor(0xFFFFFFFF.toInt())
            gravity = Gravity.CENTER
        }

        val subtitle = TextView(this).apply {
            text = "مختبر تحليل ومحاكاة أنظمة الجولات"
            textSize = 16f
            setTextColor(0xFFB0BEC5.toInt())
            gravity = Gravity.CENTER
        }

        resultText = TextView(this).apply {
            text = "جاهز لبدء الاختبار التجريبي"
            textSize = 17f
            setTextColor(0xFFFFFFFF.toInt())
            gravity = Gravity.CENTER
            setPadding(10, 35, 10, 35)
        }

        val sharedButton = Button(this).apply {
            text = "محاكاة خادمين بجولة مشتركة"
            setOnClickListener {
                simulateSharedRound()
            }
        }

        val independentButton = Button(this).apply {
            text = "محاكاة خادمين بجولات مستقلة"
            setOnClickListener {
                simulateIndependentRounds()
            }
        }

        val resetButton = Button(this).apply {
            text = "إعادة الاختبار"
            setOnClickListener {
                roundNumber = 0
                resultText.text = "تمت إعادة الاختبار"
            }
        }

        layout.addView(title)
        layout.addView(subtitle)
        layout.addView(resultText)
        layout.addView(sharedButton)
        layout.addView(independentButton)
        layout.addView(resetButton)

        setContentView(layout)
    }

    private fun generateMultiplier(): Double {
        return 1.0 + random.nextInt(900) / 100.0
    }

    private fun simulateSharedRound() {
        roundNumber++

        val roundId = "ROUND-$roundNumber"
        val multiplier = generateMultiplier()

        resultText.text = """
            نموذج: جولات مشتركة

            رقم الجولة: $roundId

            الخادم الأول - مصر:
            ${multiplier}x

            الخادم الثاني - دولي:
            ${multiplier}x

            النتيجة:
            متطابقة

            --------------------

            هذه محاكاة محلية وليست بيانات حقيقية.
        """.trimIndent()
    }

    private fun simulateIndependentRounds() {
        roundNumber++

        val firstMultiplier = generateMultiplier()
        val secondMultiplier = generateMultiplier()

        resultText.text = """
            نموذج: جولات مستقلة

            رقم الجولة: $roundNumber

            الخادم الأول - مصر:
            ${firstMultiplier}x

            الخادم الثاني - دولي:
            ${secondMultiplier}x

            النتيجة:
            ${if (firstMultiplier == secondMultiplier) "متطابقة" else "مختلفة"}

            --------------------

            هذه محاكاة محلية وليست بيانات حقيقية.
        """.trimIndent()
    }
}
