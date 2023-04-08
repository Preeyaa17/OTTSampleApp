package com.example.ownottapp.repository

import android.util.Log
import com.example.ownottapp.model.Configuration
import com.example.ownottapp.model.ResponseContent
import com.example.ownottapp.network.INetworkAPI
import com.example.ownottapp.util.StringConstants
import io.reactivex.Observable

class RemoteService {

    companion object {
        val TAG = RemoteService::class.java.name
        private val networkAPI: INetworkAPI = INetworkAPI.Factory.create()
    }

    fun getPopularMovies(): Observable<ResponseContent> {
        val result =
            networkAPI.getPopularMovies(1, StringConstants.languageUS, StringConstants.API_KEY)
        Log.d(TAG, result.toString())
        return result
    }

    fun getUpcomingMovies(): Observable<ResponseContent> {
        val result =
            networkAPI.getUpComingMovies(1, StringConstants.languageUS, StringConstants.API_KEY)
        Log.d(TAG, result.toString())
        return result
    }

    fun getLatestMovies(): Observable<ResponseContent> {
        val result =
            networkAPI.getLatestMovies(1, StringConstants.languageUS, StringConstants.API_KEY)
        Log.d(TAG, result.toString())
        return result
    }

    fun getTopRatedMovies(): Observable<ResponseContent> {
        val result =
            networkAPI.getTopRatedMovies(1, StringConstants.languageUS, StringConstants.API_KEY)
        Log.d(TAG, result.toString())
        return result
    }

    fun getNowPlayingMovies(): Observable<ResponseContent> {
        val result =
            networkAPI.getNowPlayingMovies(1, StringConstants.languageUS, StringConstants.API_KEY)
        Log.d(TAG, result.toString())
        return result
    }

    fun getConfiguration(): Observable<Configuration> {
        val response = networkAPI.getConfigurations(StringConstants.API_KEY)
        return response
    }

    fun getPopularTvShows(): Observable<ResponseContent> {
        val result =
            networkAPI.getPopularTvShows(1, StringConstants.languageUS, StringConstants.API_KEY)
        return result
    }

    fun getOnAirTvShows(): Observable<ResponseContent> {
        val result =
            networkAPI.getOnAirTvShows(1, StringConstants.languageUS, StringConstants.API_KEY)
        return result
    }

    fun getAiringTodayTvShows(): Observable<ResponseContent> {
        val result =
            networkAPI.getAiringTodayTvShows(1, StringConstants.languageUS, StringConstants.API_KEY)
        return result
    }

    fun getLatestTvShows(): Observable<ResponseContent> {
        val result =
            networkAPI.getLatestTvShows(1, StringConstants.languageUS, StringConstants.API_KEY)
        return result
    }

}