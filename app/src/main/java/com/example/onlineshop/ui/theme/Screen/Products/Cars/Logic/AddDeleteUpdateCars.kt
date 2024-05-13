package com.example.onlineshop.ui.theme.Screen.Products.Cars.Logic

import android.app.ProgressDialog
import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.navigation.NavController
import com.example.onlineshop.Model.SignInModels.Upload
import com.example.onlineshop.Navigation.ROUTE_LOGIN
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage


class AddDeleteUpdateCars(var navController: NavController, var context: Context) {

    var authRepository: AddDeleteUpdateCars
    var progress: ProgressDialog

    init {
        authRepository = AddDeleteUpdateCars(navController, context)

        progress = ProgressDialog(context)
        progress.setTitle("Loading")
        progress.setMessage("Please wait...")
    }


    fun UploadCars(name: String, quantity: String, price: String, filePath: Uri) {
        val PhoneId = System.currentTimeMillis().toString()
        val storageRef = FirebaseStorage.getInstance().getReference()
            .child("Cars/$PhoneId")
        progress.show()
        storageRef.putFile(filePath).addOnCompleteListener {
            progress.dismiss()
            if (it.isSuccessful) {
                // Save data to db
                storageRef.downloadUrl.addOnSuccessListener {
                    var imageUrl = it.toString()
                    var upload = Upload(name, quantity, price, imageUrl, PhoneId)
                    var databaseRef = FirebaseDatabase.getInstance().getReference()
                        .child("Cars/$PhoneId")
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


    fun AllCars(
        upload: MutableState<Upload>,
        uploads: SnapshotStateList<Upload>
    ): SnapshotStateList<Upload> {
        progress.show()
        var ref = FirebaseDatabase.getInstance().getReference()
            .child("Cars")
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

    fun deleteCar(uploadId:String){
        var ref = FirebaseDatabase.getInstance().getReference()
            .child("Phones/$uploadId")
        ref.removeValue()
        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
    }
}