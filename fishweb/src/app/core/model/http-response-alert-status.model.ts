/**
 * http请求响应后，是否在http-util中统一提醒
 */
export enum HttpResponseAlertStatus {
  NONE=0, // 不作统一消息提醒
  ALL= 1, // 无论成功失败都消息提醒
  SUCCESS= 2, // 仅成功时消息提醒
  FAIL= 3, // 仅失败时消息提醒
}