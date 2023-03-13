package com.example.youtubeapi.ui.playlists

import android.view.LayoutInflater
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.youtubeapi.base.BaseActivity
import com.example.youtubeapi.databinding.ActivityItemPlaylistsBinding



class ItemPlaylistsActivity : BaseActivity<PlaylistsViewModel,ActivityItemPlaylistsBinding>() {

   override val viewModel: PlaylistsViewModel by lazy {
        ViewModelProvider(this)[PlaylistsViewModel::class.java]
    }
    override fun inflateViewBinding(inflater: LayoutInflater): ActivityItemPlaylistsBinding{
        return ActivityItemPlaylistsBinding.inflate(layoutInflater)
    }

    override fun initView() {
      val  aa =intent.getStringExtra(PlaylistsActivity.KEY)
        Toast.makeText(this, "$aa", Toast.LENGTH_SHORT).show()
    }
}