/*
* MongoDatabaseListenerUtil.kt
* viewer
*
* Created on 1/26/2020
* Copyright 2020 Citrus Circuits. All rights reserved.
*/

package com.example.viewer_2020

import com.google.gson.Gson
import com.mongodb.stitch.android.core.Stitch
import com.mongodb.stitch.android.core.StitchAppClient
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient
import org.bson.Document

//Class whose usage is to communicate with MongoDB, essentially making it the middle man
//between all database-app communication.
class MongoDatabaseListenerUtil {
    private var stitchClient: StitchAppClient = Stitch.getDefaultAppClient()
    private var mongoClient: RemoteMongoClient = stitchClient.getServiceClient(RemoteMongoClient.factory, Constants.MONGO_ATLAS)
    private var collection = mongoClient.getDatabase(Constants.DATABASE_NAME).getCollection(Constants.COMPETITIONS)

    //Loads the competition document of the given TBA event whose value is set in Constants/TBA_EVENT_KEY.
    fun getCompetitionDocument(callback: Callback<DatabaseReference.CompetitionObject>) {
        collection.find().into(mutableListOf()).addOnSuccessListener {
            for (competition in it) {

                //If the following is true, the correct competition from MongoDB is accessed.
                if (Document(competition)["tba_event_key"].toString() == Constants.TBA_EVENT_KEY) {
                    callback.execute(Gson().fromJson(Document(competition).toJson(),
                        DatabaseReference.CompetitionObject::class.java))
                }
            }
        }
    }

    //Interface to access the CompetitionObject in an asynchronous function.
    //Meant to be overridden on initial class usage.
    interface Callback<T> {
        fun execute(response: T)
    }
}