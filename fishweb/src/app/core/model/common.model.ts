import { objectUtil } from 'prime-jsutils';

export class CommonModel {

  constructor() {
  }

  clone(value) {
    return Object.assign(
      Object.create(Object.getPrototypeOf(value)), value);
  }

  deepClone(value){
    return Object.assign(
      Object.create(Object.getPrototypeOf(value)), objectUtil.clone(value));
  }
  
  delNullAndTrim(value){
    return Object.assign(
      Object.create(Object.getPrototypeOf(value)), objectUtil.delNullAndTrim(value));
  }
}
