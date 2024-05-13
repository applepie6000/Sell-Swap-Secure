package com.example.onlineshop.ui.theme.Screen.Products.Phone.Logic

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

class AddDeleteUpdatePhones(var navController: NavController, var context: Context) {

    var authRepository: AddDeleteUpdatePhones
    var progress: ProgressDialog

    init {
        authRepository = AddDeleteUpdatePhones(navController, context)
        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
    }


    fun UploadPhones(name: String, quantity: String, price: String, filePath: Uri) {
        val PhoneId = System.currentTimeMillis().toString()
        val storageRef = FirebaseStorage.getInstance().getReference()
            .child("Phones/$PhoneId")
        progress.show()
        storageRef.putFile(filePath).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                // Save data to db
                storageRef.downloadUrl.addOnSuccessListener {
                    var imageUrl = it.toString()
                    var upload = Upload(name, quantity, price, imageUrl, PhoneId)
                    var databaseRef = FirebaseDatabase.getInstance().getReference()
                        .child("Phones/$PhoneId")
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


    fun AllPhones(
        upload: MutableState<Upload>,
        uploads: SnapshotStateList<Upload>
    ): SnapshotStateList<Upload> {
        progress.show()
        var ref = FirebaseDatabase.getInstance().getReference()
            .child("Phones")
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

    fun deletePhone(uploadId:String){
        var ref = FirebaseDatabase.getInstance().getReference()
            .child("Phones/$uploadId")
        ref.removeValue()
        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }
}