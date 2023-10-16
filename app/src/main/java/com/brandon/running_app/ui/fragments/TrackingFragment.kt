package com.brandon.running_app.ui.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.brandon.running_app.R
import com.brandon.running_app.other.Constants.ACTION_START_OR_RESUME_SERVICE
import com.brandon.running_app.services.TrackingService
import com.brandon.running_app.ui.viewmodels.MainViewModel
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.MapView
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint  // 안드로이드 구성 요소 내부에 injection을 하는 경우 선언되어야 함
class TrackingFragment : Fragment(R.layout.fragment_statistics) {

    private val viewModel : MainViewModel by viewModels()  // dagger Hilt will inject this
    private var map: GoogleMap? = null
    lateinit var mapView: MapView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tracking, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val rootView = getView()
        if (rootView != null){
            val btnToggleRun = rootView.findViewById<Button>(R.id.btnToggleRun)
            btnToggleRun.setOnClickListener{
                sendCommandToService(ACTION_START_OR_RESUME_SERVICE)
            }

            mapView = rootView.findViewById<MapView>(R.id.mapView)
            mapView.onCreate(savedInstanceState)
            mapView.getMapAsync {
                map = it
            }
        }
    }

    private fun sendCommandToService(action: String) {
        Intent(requireContext(), TrackingService::class.java).also {
            it.action = action
            requireContext().startService(it)
        }
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    // onDestroy()는 왜인지 작동 안 됨

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }


}