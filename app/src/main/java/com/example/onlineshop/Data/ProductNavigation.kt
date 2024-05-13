//package com.example.onlineshop.Data
//
//import android.app.ProgressDialog
//import android.content.Context
//import android.net.Uri
//import android.widget.Toast
//import androidx.compose.runtime.MutableState
//import androidx.compose.runtime.snapshots.SnapshotStateList
//import androidx.navigation.NavController
//import com.example.onlineshop.Navigation.ROUTE_LOGIN
//import com.google.firebase.database.DataSnapshot
//import com.google.firebase.database.DatabaseError
//import com.google.firebase.database.FirebaseDatabase
//import com.google.firebase.database.ValueEventListener
//import com.example.onlineshop.Model.SignInModels.Upload
//import com.google.firebase.storage.FirebaseStorage
//
//class ProductNavigation ( var navController: NavController, var context: Context) {
//    var authRepository: AuthViewModel
//    var progress: ProgressDialog
//
//    init {
//        authRepository = AuthViewModel(navController, context)
//        if (!authRepository.isloggedin()) {
//            navController.navigate(ROUTE_LOGIN)
//        }
//        progress = ProgressDialog(context)
//        progress.setTitle("Loading")
//        progress.setMessage("Please wait...")
//    }
//
////    fun saveProduct(productName: String, productQuantity: String, productPrice: String) {
////        var id = System.currentTimeMillis().toString()
////        var productData = Product(productName, productQuantity, productPrice, id)
////        var productRef = FirebaseDatabase.getInstance().getReference()
////            .child("Money/$id")
////        progress.show()
////        productRef.setValue(productData).addOnCompleteListener {
////            progress.dismiss()
////            if (it.isSuccessful) {
////                Toast.makeText(context, "Saving successful", Toast.LENGTH_SHORT).show()
////            } else {
////                Toast.makeText(context, "ERROR: ${it.exception!!.message}", Toast.LENGTH_SHORT)
////                    .show()
////            }
////        }
////    }
//
//
////
////    fun viewProducts(
////        user: MutableState<Products>,
////        users: SnapshotStateList<Products>
////    ): SnapshotStateList<Products> {
////        val ref = FirebaseDatabase.getInstance().getReference().child("Users")
////
////        //progress.show()
////        ref.addValueEventListener(object : ValueEventListener {
////            override fun onDataChange(snapshot: DataSnapshot) {
////                //progress.dismiss()
////                users.clear()
////                for (snap in snapshot.children) {
////                    val value = snap.getValue(Products::class.java)
////                    user.value = value!!
////                    users.add(value)
////
////                }
////            }
////            override fun onCancelled(error: DatabaseError) {
////                Toast.makeText(context, error.message, Toast.LENGTH_SHORT).show()
////            }
////        })
////        return users
////    }
////
////    fun deleteProduct(id: String) {
////        var delRef = FirebaseDatabase.getInstance().getReference()
////            .child("Products/$id")
////        progress.show()
////        delRef.removeValue().addOnCompleteListener {
////            progress.dismiss()
////            if (it.isSuccessful) {
////                Toast.makeText(context, "Product deleted", Toast.LENGTH_SHORT).show()
////            } else {
////                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
////            }
////        }
////    }
//
////    fun updateProduct(ProductName: String, ProductPrice: String, ProductDescription: String, id: String) {
////        var updateRef = FirebaseDatabase.getInstance().getReference()
////            .child("Products/$id")
////        progress.show()
////        var updateData = Products(ProductName, ProductPrice, ProductDescription, id)
////        updateRef.setValue(updateData).addOnCompleteListener {
////            progress.dismiss()
////            if (it.isSuccessful) {
////                Toast.makeText(context, "Update successful", Toast.LENGTH_SHORT).show()
////            } else {
////                Toast.makeText(context, it.exception!!.message, Toast.LENGTH_SHORT).show()
////            }
////        }
////    }
//
//
//    fun uploadProduct(name: String, quantity: String, price: String, filePath: Uri) {
//        val uploadId = System.currentTimeMillis().toString()
//        val storageRef = FirebaseStorage.getInstance().getReference()
//            .child("Uploads/$uploadId")
//        progress.show()
//        storageRef.putFile(filePath).addOnCompleteListener {
//            progress.dismiss()
//            if (it.isSuccessful) {
//                // Save data to db
//                storageRef.downloadUrl.addOnSuccessListener {
//                    var imageUrl = it.toString()
//                    var upload = Upload(name, quantity, price, imageUrl, uploadId)
//                    var databaseRef = FirebaseDatabase.getInstance().getReference()
//                        .child("Uploads/$uploadId")
//                    databaseRef.setValue(upload).addOnCompleteListener {
//                        if (it.isSuccessful) {
//                            Toast.makeText(this.context, "Success", Toast.LENGTH_SHORT).show()
//                        } else {
//                            Toast.makeText(this.context, "Error", Toast.LENGTH_SHORT).show()
//                        }
//                    }
//                }
//            } else {
//                Toast.makeText(this.context, "Upload error", Toast.LENGTH_SHORT).show()
//            }
//        }
//    }
//
//
//    fun allProducts(
//        upload: MutableState<Upload>,
//        uploads: SnapshotStateList<Upload>
//    ): SnapshotStateList<Upload> {
//        progress.show()
//        var ref = FirebaseDatabase.getInstance().getReference()
//            .child("Uploads")
//        ref.addValueEventListener(object : ValueEventListener {
//            override fun onDataChange(snapshot: DataSnapshot) {
//                uploads.clear()
//                for (snap in snapshot.children) {
//                    var retrievedUpload = snap.getValue(Upload::class.java)
//                    upload.value = retrievedUpload!!
//                    uploads.add(retrievedUpload)
//                }
//                progress.dismiss()
//            }
//
//            override fun onCancelled(error: DatabaseError) {
//                Toast.makeText(context, "DB locked", Toast.LENGTH_SHORT).show()
//            }
//        })
//        return uploads
//    }
//
//    fun deleteUpload(uploadId:String){
//        var ref = FirebaseDatabase.getInstance().getReference()
//            .child("Uploads/$uploadId")
//        ref.removeValue()
//        Toast.makeText(context, "Success", Toast.LENGTH_SHORT).show()
//    }
//
//}