package com.yetland.bbclient

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle

/**
 *@author YETLAND
 *@date 2018/12/12 15:38
 */
class MainDialog(context: Context) : AlertDialog(context) {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_int)
    }
}