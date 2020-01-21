package com.codeblin.objectpreference

class OutOfScopeClass {
    private val user: UserStoreModel = UserStoreModel()

    fun getUserFromElsewhere(): User = user.get()
}