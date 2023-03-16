package com.example.youtubeapi.ui.playlists.detail

import android.view.LayoutInflater
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.youtubeapi.R
import com.example.youtubeapi.core.network.result.Status
import com.example.youtubeapi.core.network.ui.BaseActivity
import com.example.youtubeapi.data.local.entity.remote.model.InfoPlaylists
import com.example.youtubeapi.databinding.ActivityItemPlaylistsBinding
import com.example.youtubeapi.ui.playlists.PlaylistsActivity
import com.example.youtubeapi.utils.showToast


class ItemPlaylistsActivity : BaseActivity<ItemViewModel, ActivityItemPlaylistsBinding>() {

    private lateinit var adapterPlaylist : AdapterItem
   override val viewModel: ItemViewModel by lazy {
        ViewModelProvider(this)[ItemViewModel::class.java]
    }
    override fun inflateViewBinding(inflater: LayoutInflater): ActivityItemPlaylistsBinding{
        return ActivityItemPlaylistsBinding.inflate(layoutInflater)
    }


    override fun initViewModel() {
        viewModel.loading.observe(this){
            binding.progressCircular.isVisible = it
      }
            adapterPlaylist = AdapterItem()
        val  aa =intent.getStringExtra(PlaylistsActivity.DATA)
        Toast.makeText(this, "$aa", Toast.LENGTH_SHORT).show()
            viewModel.playlists(aa.toString()).observe(this) {
                    it.data?.items?.let { it1 -> adapterPlaylist.setItems(it1) }
                    when (it.status) {
                        Status.SUCCESS -> {
                            binding.rvPlaylistItem.adapter = adapterPlaylist
                            viewModel.loading.postValue(false)
                        }
                        Status.LOADING -> {
                            viewModel.loading.postValue(true)
                        }
                        Status.ERROR -> {
                            viewModel.loading.postValue(true)
                            showToast(it.message.toString())
                        }
                    }
                }
        }
    }

