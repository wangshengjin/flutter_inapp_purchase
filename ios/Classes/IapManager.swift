/**
 * @file IapManager.swift
 * @description 处理 iOS 原生订阅管理弹窗的 Swift 助手类
 */

import Foundation
import StoreKit

@objc public class IapManager: NSObject {
    @objc public static let shared = IapManager()

    /**
     * 拉起 iOS 15+ 的原生订阅管理弹窗
     * @param completion 跳转结果回调
     */
    @objc public func showManageSubscriptions(completion: @escaping (Bool) -> Void) {
        if #available(iOS 15.0, *) {
            Task { @MainActor in
                // 获取当前的 WindowScene，这是 iOS 15+ API 的要求
                let windowScene = UIApplication.shared.connectedScenes
                    .filter { $0.activationState == .foregroundActive }
                    .compactMap { $0 as? UIWindowScene }
                    .first

                if let windowScene = windowScene {
                    do {
                        try await AppStore.showManageSubscriptions(in: windowScene)
                        completion(true)
                    } catch {
                        // 如果弹窗拉起失败，回调 false 以便 ObjC 端回退到 URL 方案
                        completion(false)
                    }
                } else {
                    completion(false)
                }
            }
        } else {
            completion(false)
        }
    }
}
