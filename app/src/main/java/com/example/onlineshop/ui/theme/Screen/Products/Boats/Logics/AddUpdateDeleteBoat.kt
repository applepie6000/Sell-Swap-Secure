package com.example.onlineshop.ui.theme.Screen.Products.Boats.Logics

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavController
import com.example.onlineshop.Model.SignInModels.Upload
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage

class AddUpdateDeleteBoat(var navController: NavController, var context: Context) {
    var authRepository: AddUpdateDeleteBoat
    var progress: ProgressDialog

    init {
        authRepository = AddUpdateDeleteBoat(navController, context)
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
    }


    fun UploadBoats(name: String, quantity: String, price: String, filePath: Uri) {
        val uploadId = System.currentTimeMillis().toString()
        val storageRef = FirebaseStorage.getInstance().getReference()
            .child("Boats/$uploadId")
        progress.show()
        storageRef.putFile(filePath).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                // Save data to db
                storageRef.downloadUrl.addOnSuccessListener {
                    var imageUrl = it.toString()
                    var upload = Upload(name, quantity, price, imageUrl, uploadId)
                    var databaseRef = FirebaseDatabase.getInstance().getReference()
                        .child("Boats/$uploadId")
                    databaseRef.setValue(upload).addOnCompleteListener {
                        if (it.isSuccessful) {
                            Toast.makeText(this.context, "Success", Toast.LENGTH_SHORT).show()
                        } else {
                            Toast.makeText(this.context, "Error", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            } else {
                Toast.makeText(this.context, "Upload error", Toast.LENGTH_SHORT).show()
            }
        }
    }


    fun AllBoats(
        upload: MutableState<Upload>,
        uploads: SnapshotStateList<Upload>
    ): SnapshotStateList<Upload> {
        progress.show()
        var ref = FirebaseDatabase.getInstance().getReference()
            .child("Boats")
        ref.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                uploads.clear()
                for (snap in snapshot.children) {
                    var retrievedUpload = snap.getValue(Upload::class.java)
                    upload.value = retrievedUpload!!
                    uploads.add(retrievedUpload)
                }
                progress.dismiss()
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(context, "DB locked", Toast.LENGTH_SHORT).show()
            }
        })
        return uploads
    }

    fun deleteBoat(uploadId:String){
        var ref = FirebaseDatabase.getInstance().getReference()
            .child("Boats/$uploadId")
        ref.removeValue()
        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }
}