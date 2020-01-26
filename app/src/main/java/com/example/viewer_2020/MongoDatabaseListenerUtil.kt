/*
* MongoDatabaseListenerUtil.kt
* viewer
*
* Created on 1/26/2020
* Copyright 2020 Citrus Circuits. All rights reserved.
*/

package com.example.viewer_2020

import org.bson.Document
import com.mongodb.client.model.Filters
import com.mongodb.stitch.android.core.Stitch
import com.mongodb.stitch.android.core.StitchAppClient
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient
import com.mongodb.stitch.core.auth.providers.anonymous.AnonymousCredential

//Class whose usage is to communicate with MongoDB, essentially making it the middle man
//between all database-app communication.
class MongoDatabaseListenerUtil {
    var stitchClient: StitchAppClient = Stitch.getDefaultAppClient()
    var mongoClient: RemoteMongoClient = stitchClient.getServiceClient(RemoteMongoClient.factory, Constants.MONGO_ATLAS)
    var collection = mongoClient.getDatabase(Constants.DATABASE_NAME).getCollection(Constants.COMPETITIONS)

    //Returns the competition document of the given TBA event whose value is set in Constants/TBA_EVENT_KEY.
    //After calling this function, you use it as follows: 'getCompetitionDocument().get(____)' to access values by key.
    fun getCompetitionDocument(): Document {
        var competitionDocument: Document? = null
        stitchClient.auth.loginWithCredential(AnonymousCredential()).continueWithTask {
            val competition = collection.findOne(
                Filters.eq(
                    "tba_event_key",
                    Constants.TBA_EVENT_KEY
                )
            )
            //This is the only instance where competitionDocument gets rewritten, so if the competition
            //cannot be accessed, the competitionDocument will return null when called.
            competition.addOnSuccessListener {
                competitionDocument = competition.result
            }
        }
    return competitionDocument!!
    }
}