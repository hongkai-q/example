package com.quyin.example

import android.graphics.Rect
import android.os.Bundle
import android.webkit.WebSettings
import android.webkit.WebView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_main)

		enableEdgeToEdge()
		ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
			val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
			v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
			val rect = Rect()
			v.getWindowVisibleDisplayFrame(rect)
			v.layoutParams.height = rect.height() + systemBars.bottom + systemBars.top
			v.requestLayout()
			insets
		}

		val webView = findViewById<WebView>(R.id.webview)
		webView.settings.apply {
//			javaScriptEnabled = true
			javaScriptCanOpenWindowsAutomatically = true
			setSupportZoom(true)
			cacheMode = WebSettings.LOAD_NO_CACHE
			builtInZoomControls = true
			domStorageEnabled = true
			useWideViewPort = true
			loadWithOverviewMode = true
		}
		webView.loadUrl("file:///android_asset/test.html")
	}
}