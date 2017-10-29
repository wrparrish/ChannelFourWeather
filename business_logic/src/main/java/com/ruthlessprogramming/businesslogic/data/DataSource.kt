package com.ruthlessprogramming.businesslogic.data

import io.reactivex.Observable

/**
 * Created by billyparrish on 10/28/17.
 */

interface DataSource<in K, V> {

    fun getAll(): Observable<List<V>>

    fun get(key: K): Observable<V>

    fun put(key: K, value: V): Observable<V>

    fun remove(key: K): Observable<V>

}
