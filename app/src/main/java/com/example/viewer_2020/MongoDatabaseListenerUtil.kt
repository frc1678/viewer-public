/*
* MongoDatabaseListenerUtil.kt
* viewer
*
* Created on 1/26/2020
* Copyright 2020 Citrus Circuits. All rights reserved.
*/

package com.example.viewer_2020

import com.google.gson.Gson
import com.mongodb.client.model.Filters
import com.mongodb.client.model.Projections
import com.mongodb.stitch.android.core.Stitch
import com.mongodb.stitch.android.core.StitchAppClient
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient
import com.mongodb.stitch.core.auth.providers.anonymous.AnonymousCredential
import com.mongodb.stitch.core.services.mongodb.remote.RemoteFindOptions
import org.bson.Document

//Class whose usage is to communicate with MongoDB, essentially making it the middle man
//between all database-app communication.
class MongoDatabaseListenerUtil {
    private var stitchClient: StitchAppClient = Stitch.getDefaultAppClient()
    private var mongoClient: RemoteMongoClient = stitchClient.getServiceClient(RemoteMongoClient.factory, Constants.MONGO_ATLAS)
    private var collection = mongoClient.getDatabase(Constants.DATABASE_NAME).getCollection(Constants.COLLECTION_NAME)

    //Loads the competition document of the given TBA event whose value is set in Constants/TBA_EVENT_KEY.
    fun getCompetitionDocument(callback: Callback<DatabaseReference.CompetitionObject>) {
        stitchClient.auth.loginWithCredential(AnonymousCredential()).continueWithTask {
            collection.findOne(
                Filters.eq(
                    "tba_event_key",
                    Constants.TBA_EVENT_KEY
                ), RemoteFindOptions().projection(Projections.fields(
                    Projections.include(Constants.FIELDS_TO_BE_DISPLAYED), Projections.excludeId()))
            ).addOnSuccessListener {
                //If the following is true, the correct competition from MongoDB is accessed.
                callback.execute(Gson().fromJson(Document(it).toJson(),
                    DatabaseReference.CompetitionObject::class.java))
            }
        }
    }

    //Interface to access the CompetitionObject in an asynchronous function.
    //Meant to be overridden on initial class usage.
    interface Callback<T> {
        fun execute(response: T)
    }
}