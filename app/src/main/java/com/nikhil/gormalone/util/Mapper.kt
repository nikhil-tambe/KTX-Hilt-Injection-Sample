package com.nikhil.gormalone.util

interface Mapper<NetworkEntity, DBEntity, Model> {

    // For Saving response to Local DB
    fun networkEntityToDBEntity(networkEntity: NetworkEntity): DBEntity

    // For Uploads
    fun dbEntityToNetworkEntity(dbEntity: DBEntity): NetworkEntity

    // For showing Local Database data in UI
    fun dbEntityToModel(dbEntity: DBEntity): Model

}