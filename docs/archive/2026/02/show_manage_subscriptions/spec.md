# 功能说明书 - iOS 订阅管理原生弹窗实现

## 1. 简述 (Summary)
在 `FlutterInappPurchasePlugin` 中通过引入 Swift 代码，支持 iOS 15+ 的原生订阅管理弹窗效果，提升用户体验。

## 2. 背景 (Context)
当前的实现是通过跳转系统 URL 来管理订阅，虽然兼容性好，但会离开当前 App。iOS 15 引入了原生弹窗 API，可以在不离开 App 的情况下完成订阅管理。

## 3. 需求详情 (Requirements)
- 引入 Swift Helper 类，调用 StoreKit 2 的 `AppStore.showManageSubscriptions(in:)`。
- iOS 15.0+：拉起系统原生半屏管理弹窗。
- iOS 15.0 以下：回退至现有的 URL 跳转方案。
- 确保 Objective-C 能够无缝调用 Swift 方法。

## 4. 边界情况 (Boundary Conditions)
- 非 iOS 环境的处理。
- 低于 iOS 15 版本的环境处理。
- 无法获取当前活跃 WindowScene 时的异常处理。
