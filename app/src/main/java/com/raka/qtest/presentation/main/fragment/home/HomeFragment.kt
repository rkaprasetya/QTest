package com.raka.qtest.presentation.main.fragment.home

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.BindingAdapter
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.raka.qtest.R
import com.raka.qtest.databinding.FragmentHomeBinding
import com.raka.qtest.di.component.DaggerHomeFragmentComponent
import com.raka.qtest.di.component.HomeFragmentComponent
import com.raka.qtest.di.module.HomeFragmentModule
import com.raka.qtest.presentation.adapter.CategoryAdapter
import com.raka.qtest.presentation.adapter.ProductListAdapter
import javax.inject.Inject

class HomeFragment : Fragment() {
    init {
        instance = this
    }

    @Inject
    lateinit var viewModel: HomeViewModel
    lateinit var component: HomeFragmentComponent

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentHomeBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        val view: View = binding.root
        setupDagger()
        binding.viewModel = viewModel
        viewModel.bound()
        return view
    }

    private fun setupDagger() {
        component =
            DaggerHomeFragmentComponent.builder().homeFragmentModule(HomeFragmentModule(activity!!))
                .build()
        component.inject(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        viewModel.unBound()
    }

    companion object {
        @JvmStatic
        @BindingAdapter("adapter")
        fun bindList(recyclerView: RecyclerView, viewModel: HomeViewModel) {
            val adapter = ProductListAdapter(viewModel.productsList, applicationContext())
            adapter.onItemClickListener = { viewModel.onProductClicked(it) }
            adapter.onLikedListener = { viewModel.onLikeClicked(it) }
            addItemDecoration(recyclerView)
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
            recyclerView.adapter = adapter
        }
        private var instance: HomeFragment? = null
        fun applicationContext() : Context {
            return instance!!.context!!
        }
        private fun addItemDecoration(recyclerView: RecyclerView){
            recyclerView.addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    DividerItemDecoration.VERTICAL
                )
            )
        }
    }
}