package com.example.studyapplication.data.datasource.remote

import com.example.studyapplication.network.Conn

interface NaverRemoteDataSource {
    fun getMovieList(title : String, conn : Conn)
    fun getBlogList(title : String, conn : Conn)
    fun getImageList(title : String, conn : Conn)
}