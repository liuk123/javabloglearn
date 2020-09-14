import { objectUtil } from "prime-jsutils"

export enum defaultMessage {
  "操作出现错误！" =-1,
  "操作失败！"=0,
  "操作成功！"=1,
  "操作成功，即将进行一下步操作！"=2
};

export class Result {

  [key: string]: any;

  constructor(public resultCode?: number,
              public resultMsg?: string,
              public data?: any
  ) {}

  static init(obj: any): Result {
    const model = new Result();
    if (typeof obj === "string") {
      model.data = obj;
    } else if (obj instanceof Result) {
      return obj;
    } else if (objectUtil.isObject(obj)) {
      Object.keys(obj).forEach(key => {
        if (key in model) {
          model[key] = obj[key];
        }
      });
    }
    return model;
  }

  /**
   * 创建失败时的对象
   * @param message：失败提醒信息
   */
  static error(message: string): Result {
    const model = new Result();
    model.resultCode = 0;
    model.resultMsg = message;
    return model;
  }

  /**
   * 创建成功时的对象
   * @param data：参数、数据
   */
  static success(data: any): Result {
    const model = new Result();
    model.resultCode = 1;
    model.data = data;
    return model;
  }

  /**
   * 是否成功返回
   * @param data
   */
  static isSuccess(data: any): boolean {
    return data && data.isSuccess && data.isSuccess();
  }

  /**
   * 是否返回了失败消息
   * @param data
   */
  static isError(data: any): boolean {
    return data && data.isError && data.isError();
  }

  /**
   * 是否成功返回数组数据
   * @param data
   */
  static isArray(data: any): boolean {
    return data && data.isSuccess && data.isSuccess() && objectUtil.isArray(data.data);
  }

  /**
   * 非空数组
   * @param data：需要验证的数据
   */
  static isNotEmptyArray(data: any): boolean {
    return this.isArray(data) && data.data.length > 0;
  }

  /**
   * 是否成功返回非空对象
   * @param data
   */
  static isNotEmptyObject(data: any): boolean {
    return data &&
      data.isSuccess && 
      data.isSuccess() &&
      objectUtil.isObject(data.data)&& 
      !objectUtil.isEmptyObject(data.data);
  }

  /**
   * 判断是否成功
   */
  isSuccess() {
    return this.resultCode === 1 || this.resultCode === 2;
  }
  /**
   * 判断是否失败
   */
  isError(){
    return this.resultCode === 0 || this.resultCode === -1
  }

  /**
   * 获取提醒消息
   */
  getMessage() {
    if (this.resultMsg) {
      return this.resultMsg;
    } else {
      const message = defaultMessage[this.resultCode];
      if (message) {
        return message;
      } else {
        return "未知的操作结果！";
      }
    }
  }
}
