package com.abisayo.cloudspace_scophy

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.addCallback

class OpenNewTicket : Fragment() {
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_open_new_ticket, container, false)

        // Initialize WebView
        webView = view.findViewById(R.id.web)

        // Configure WebView
        webView.loadUrl("https://cloudspace.scophyweb.com//?ng=tickets/admin/create")
        webView.settings.javaScriptEnabled = true
        webView.webViewClient = WebViewClient()
        webView.settings.setSupportZoom(true)

        // Handle back press inside fragment
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            if (webView.canGoBack()) {
                webView.goBack()
            } else {
                requireActivity().finish()
            }
        }

        return view
    }

}