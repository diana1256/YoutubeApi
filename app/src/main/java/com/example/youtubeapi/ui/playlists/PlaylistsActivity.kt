package com.example.youtubeapi.ui.playlists

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import com.example.youtubeapi.base.BaseActivity
import com.example.youtubeapi.databinding.PlaylistsMainBinding
import com.example.youtubeapi.model.InfoPlaylists
import com.example.youtubeapi.model.ItemsItem

class PlaylistsActivity : BaseActivity<PlaylistsViewModel,PlaylistsMainBinding>() {

    private lateinit var adapterPlaylist : AdapterPlaylist
    private val registerForActivity =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {}

    override val viewModel: PlaylistsViewModel by lazy {
        ViewModelProvider(this)[PlaylistsViewModel::class.java]
    }
    override fun inflateViewBinding(inflater: LayoutInflater): PlaylistsMainBinding {
        return PlaylistsMainBinding.inflate(layoutInflater)
    }

    override fun initView() {
        viewModel.playlists().observe(this) {
            adapterPlaylist = AdapterPlaylist(it,this::itemClick)
            binding.recyclerMain.adapter = adapterPlaylist
            onCheck()
        }
    }

    override fun initListener() {
        binding.noInternet.btnTryAgain.setOnClickListener{
            Toast.makeText(this, "No connection!!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun itemClick(item: ItemsItem) {
       val i = Intent(this, ItemPlaylistsActivity::class.java).apply {
         putExtra(KEY,item.id)
       }
       registerForActivity.launch(i)
    }
    private fun onCheck(){
        if (Connectivity().isInternetAvailable(this)){
            binding.noInternet.root.visibility = View.GONE
        }
        else{
            binding.noInternet.root.visibility = View.VISIBLE
        }
    }
    companion object{
    const val KEY = "id"
    }
}