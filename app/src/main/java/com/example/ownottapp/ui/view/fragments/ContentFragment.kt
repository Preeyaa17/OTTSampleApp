package com.example.ownottapp.ui.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ownottapp.model.ContentRow
import com.example.ownottapp.ui.adapters.MainListAdapter
import com.example.ownottapp.util.AppSessionManager
import com.example.ownottapp.util.StringConstants
import com.example.ownottapp.viewmodel.ContentGridViewModel
import com.example.ownottsampleapp.R
import com.example.ownottsampleapp.databinding.ActivityVideoPlayerBinding
import com.example.ownottsampleapp.databinding.ContentGridMainBinding


class ContentFragment : Fragment() {

    private lateinit var binding: ContentGridMainBinding

    private var contentGridViewModel: ContentGridViewModel? = null
    private var adapter: MainListAdapter? = null
    private var mainList: ArrayList<ContentRow>? = null
    var section: String? = null




    companion object {
        val TAG = ContentFragment::class.java.name
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        section = arguments?.getString("section")
        Log.d(TAG, "content section:{$section}")
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
//        val view = activity?.layoutInflater?.inflate(R.layout.content_grid_main, null)
//        return view

        binding = ContentGridMainBinding.inflate(inflater, container, false)
        return binding.root


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainList = ArrayList()
        adapter = MainListAdapter(mainList!!)
        binding.parentRecyclerView.adapter = adapter
        binding.parentRecyclerView.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)

        contentGridViewModel = ViewModelProvider.AndroidViewModelFactory(requireActivity().application)
            .create(ContentGridViewModel::class.java)

        contentGridViewModel?.getError()?.observe(requireActivity(), Observer {
            if (it) {
                binding.errorTextId.visibility = View.VISIBLE
                binding.errorTextId.text =
                    "Not able to show content.\nTry again!!\n\n<Looks like service is down>"
            }
        })

        val configuaration = AppSessionManager(requireActivity().applicationContext).fetchConfiguration()
        if (configuaration == null) {
            contentGridViewModel?.loadConfigurations()
        } else {
            Log.d(TAG, "Configurations already available")
            StringConstants.imageBaseURL =
                configuaration.images.base_url + "/" + configuaration.images.poster_sizes[2]
            loadContent()
        }

        contentGridViewModel?.getConfigurationLiveData()?.observe(requireActivity(), Observer {
            binding.errorTextId.visibility = View.GONE
            Log.d(TAG, "Received configurations(Image base url):{${it.images.base_url}}")
            StringConstants.imageBaseURL = it.images.base_url + "/" + it.images.poster_sizes[2]
            AppSessionManager(requireActivity().applicationContext).saveConfiguration(it)
        })

        contentGridViewModel?.getContentLiveData()?.observe(requireActivity(), Observer {
            binding.errorTextId.visibility = View.GONE
            addContentRow(it)
        })
    }

    private fun loadContent() {
        if (section.equals("movies")) {
            section?.let { contentGridViewModel?.loadContent(it, "Now playing") }
            section?.let { contentGridViewModel?.loadContent(it, "Upcoming") }
            section?.let { contentGridViewModel?.loadContent(it, "Latest") }
            section?.let { contentGridViewModel?.loadContent(it, "Popular") }
            section?.let { contentGridViewModel?.loadContent(it, "Top rated") }
        } else if (section.equals("tvshows")) {
            section?.let { contentGridViewModel?.loadContent(it, "Airing today") }
            section?.let { contentGridViewModel?.loadContent(it, "On the air") }
            section?.let { contentGridViewModel?.loadContent(it, "Popular") }
            section?.let { contentGridViewModel?.loadContent(it, "Latest") }
            section?.let { contentGridViewModel?.loadContent(it, "Top rated") }

        }
    }

    private fun addContentRow(it: ContentRow) {
        mainList?.add(it)
        adapter?.notifyDataSetChanged()
    }
}