package com.example.realmdemo

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

class Student : RealmObject {
    @PrimaryKey
    var id : String = ""
    var name :String = ""
    var marks : Int = 0

}