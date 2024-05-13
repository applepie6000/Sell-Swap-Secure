package com.example.onlineshop.Model.SignInModels

import android.location.Location

class User {
    var firstName: String = ""
    var lastName:  String = ""
    var age: String = ""
    var gender: String = ""
    var location: String = ""
    var email: String = ""
    var password: String = ""
    var confirmPassword: String = ""
    var id:String=""

    constructor(
        firstName: String
                , lastName: String
                , age:String
                , gender: String
                , location: String
                , email: String
                , password: String
                , confirmPassword: String,
        id:String
    ) {
        this.firstName=firstName
        this.lastName=lastName
        this.age=age
        this.gender=gender
        this.location=location
        this.email=email
        this.password=password
        this.confirmPassword=confirmPassword
        this.id=id

    }
    constructor()

}