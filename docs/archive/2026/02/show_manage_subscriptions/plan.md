# 技术方案 - 引入 Swift 支持 iOS 原生订阅管理弹窗

## 1. 技术路线 (Technical Route)
由于 StoreKit 2 的 `showManageSubscriptions` 仅在 Swift 中可用，我们将采取 **ObjC & Swift 混编** 的方式：
1. **新建 Swift Helper**: `IapManager.swift`，使用 `@objc` 暴露给 ObjC。
2. **实现异步调用**: 在 Swift 中使用 `Task` 和 `@MainActor` 调用 StoreKit 2 API。
3. **ObjC 调用**: 在 `FlutterInappPurchasePlugin.m` 中通过 `#import <flutter_inapp_purchase/flutter_inapp_purchase-Swift.h>` 引入并调用。

## 2. 修改点 (Changes)

### 2.1 新增 `ios/Classes/IapManager.swift`
实现一个 Swift 类 `IapManager`，包含 `showManageSubscriptionsWithCompletion:` 方法。

### 2.2 修改 `ios/Classes/FlutterInappPurchasePlugin.m`
- 引入 Swift 生成的头文件。
- 修改 `showManageSubscriptions:` 方法逻辑：
  - 判断 iOS 版本。
  - >= 15.0 时，调用 `[[IapManager shared] showManageSubscriptionsWithCompletion:...]`。
  - 若调用失败或版本低于 15.0，回退到 URL `https://apps.apple.com/account/subscriptions` 方案。

## 3. 代码细节
### Swift 实现
```swift
@objc public class IapManager: NSObject {
    @objc public static let shared = IapManager()
    @objc public func showManageSubscriptions(completion: @escaping (Bool) -> Void) {
        // iOS 15+ StoreKit 2 逻辑
    }
}
```

### ObjC 调用
```objectivec
#import "flutter_inapp_purchase-Swift.h"
// ...
if (@available(iOS 15.0, *)) {
    [[IapManager shared] showManageSubscriptionsWithCompletion:^(BOOL success) { ... }];
}
```

## 4. 验证计划 (Verification Plan)
- 检查文件生成位置是否正确。
- 语法纠错。
- 归档任务。
