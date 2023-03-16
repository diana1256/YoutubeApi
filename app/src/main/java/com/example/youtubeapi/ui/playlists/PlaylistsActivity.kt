package com.example.youtubeapi.ui.playlists

import android.content.Intent
import android.view.LayoutInflater
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.youtubeapi.core.network.result.Status
import com.example.youtubeapi.core.network.ui.BaseActivity
import com.example.youtubeapi.data.local.entity.remote.model.InfoPlaylists
import com.example.youtubeapi.databinding.PlaylistsMainBinding
import com.example.youtubeapi.data.local.entity.remote.model.ItemsItem
import com.example.youtubeapi.ui.playlists.detail.ItemPlaylistsActivity
import com.example.youtubeapi.util.Connectivity
import com.example.youtubeapi.utils.isNetworkConnected
import com.example.youtubeapi.utils.showToast

class PlaylistsActivity : BaseActivity<PlaylistsViewModel, PlaylistsMainBinding>() {

    private lateinit var adapterPlaylist : AdapterPlaylist
    private var playlistData = listOf<ItemsItem>()
    private val registerForActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {}

    override val viewModel: PlaylistsViewModel by lazy {
        ViewModelProvider(this)[PlaylistsViewModel::class.java]
    }
    override fun inflateViewBinding(inflater: LayoutInflater): PlaylistsMainBinding {
        return PlaylistsMainBinding.inflate(layoutInflater)
    }

    override fun initViewModel() {
        viewModel.loading.observe(this){
            binding.progressCircular.isVisible = it
        }
        viewModel.playlists().observe(this){
            adapterPlaylist = AdapterPlaylist(it,this::itemClick)
            when(it.status){
                Status.SUCCESS -> {
                    binding.recyclerMain.adapter = adapterPlaylist
                    viewModel.loading.postValue(false)
                }
                Status.LOADING -> {
                    viewModel.loading.postValue(true)
            }
                Status.ERROR ->{
                    viewModel.loading.postValue(true)
                    showToast(it.message.toString())
                }
            }
        }
    }

    override fun initListener() {
        binding.noInternet.btnTryAgain.setOnClickListener{
            Toast.makeText(this, "No connection!!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun itemClick(itemsItem: ItemsItem) {
        val i = Intent(this, ItemPlaylistsActivity::class.java)
            i.putExtra(DATA,itemsItem.id)
            registerForActivity.launch(i)
        }

        override fun checkInternet() {
            super.checkInternet()
            val connectivity = Connectivity(application)
            connectivity.observe(this) {
                if (!it) {
                    binding.noInternet.root.isVisible = true

                    binding.noInternet.btnTryAgain.setOnClickListener {
                        if (!isNetworkConnected()) {
                            showToast("No Internet")
                        } else {
                            binding.noInternet.root.isVisible = false
                        }
                    }
                } else {
                    initViewModel()
                }
            }
        }

    companion object{
        const val DATA = "data"
    }
}