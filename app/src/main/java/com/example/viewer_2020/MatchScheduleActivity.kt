package com.example.viewer_2020

import android.os.Bundle
import android.util.Log
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.tasks.Continuation
import com.google.android.gms.tasks.Task
import com.mongodb.stitch.android.core.Stitch
import com.mongodb.stitch.android.core.StitchAppClient
import com.mongodb.stitch.android.core.auth.StitchUser
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoClient
import com.mongodb.stitch.android.services.mongodb.remote.RemoteMongoCollection
import com.mongodb.stitch.core.auth.providers.anonymous.AnonymousCredential
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.mongodb.BasicDBObject
import com.mongodb.DBObject
import com.mongodb.client.model.Filters.eq
import org.bson.BsonDocument
import org.bson.Document
import org.bson.conversions.Bson


class MatchScheduleActivity : AppCompatActivity() {
    var stitchClient: StitchAppClient = Stitch.getDefaultAppClient()
    var mongoClient: RemoteMongoClient = stitchClient.getServiceClient(RemoteMongoClient.factory, "mongodb-atlas")
    var collection = mongoClient.getDatabase("scouting_system_cloud").getCollection("competitions")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.match_schedule)

        stitchClient.getAuth().loginWithCredential(AnonymousCredential()
        ).continueWithTask(object:Continuation<StitchUser, Task<Void>> {
            @Throws(Exception::class)
            override fun then(@NonNull task: Task<StitchUser>): Task<Void>? {
                val tbaEventKey = "2019cafr"
                val competitionDocument = collection.findOne(eq("tba_event_key", tbaEventKey))
                competitionDocument.addOnSuccessListener {
                    val result = competitionDocument.getResult()
                    Log.i("RESULT", result.toString())
                }
                return null
            }
        })
    }
}