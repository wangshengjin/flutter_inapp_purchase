package com.dooboolab.flutterinapppurchase

import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import android.content.Context
import io.flutter.plugin.common.MethodChannel
import io.flutter.embedding.engine.plugins.FlutterPlugin.FlutterPluginBinding
import io.flutter.plugin.common.BinaryMessenger
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.PluginRegistry.Registrar
import android.content.pm.PackageManager.NameNotFoundException

/** FlutterInappPurchasePlugin  */
class FlutterInappPurchasePlugin2 : FlutterPlugin, ActivityAware {
    private var androidInappPurchasePlugin: AndroidInappPurchasePlugin? = null
    private var amazonInappPurchasePlugin: AmazonInappPurchasePlugin? = null
    private var channel: MethodChannel? = null
    override fun onAttachedToEngine(binding: FlutterPluginBinding) {
       
    }

    private fun onAttached(context: Context, messenger: BinaryMessenger) {
        
    }

    override fun onDetachedFromEngine(binding: FlutterPluginBinding) {
       
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        println("not allow inappPurchase in android")
    }

    override fun onDetachedFromActivity() {
        
    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
        
    }

    override fun onDetachedFromActivityForConfigChanges() {
        
    }

    private fun setAndroidInappPurchasePlugin(androidInappPurchasePlugin: AndroidInappPurchasePlugin) {
       
    }

    private fun setAmazonInappPurchasePlugin(amazonInappPurchasePlugin: AmazonInappPurchasePlugin) {
        
    }

    companion object {
        private var isAndroid = false
        private var isAmazon = false

        fun getStore(): String {
           return if (!isAndroid && !isAmazon) "none" else if (isAndroid) "play_store" else "amazon"
        }

        fun registerWith(registrar: Registrar) {

        }

        private fun isPackageInstalled(ctx: Context, packageName: String): Boolean {
            return try {
                ctx.packageManager.getPackageInfo(packageName, 0)
                true
            } catch (e: NameNotFoundException) {
                false
            }
        }

        fun isAppInstalledFrom(ctx: Context, installer: String?): Boolean {
            val installerPackageName = ctx.packageManager.getInstallerPackageName(ctx.packageName)
            return installer != null && installerPackageName != null && installerPackageName.contains(
                installer
            )
        }
    }
}