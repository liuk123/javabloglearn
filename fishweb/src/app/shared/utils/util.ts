import { Injectable } from '@angular/core';

@Injectable()
export class UtilService {
  constructor() { }

  /**
   * 获取颜色的数组
   * @param n number
   */
  getColors(n) {
    let r = 0;
    let colors = [];
    for (let i = 0; i < n; i++) {
      r -= Math.PI * 2 / -n
      colors.push(
        '#' + (
          1 << 24 |
          Math.cos(r) * 127 + 128 << 16 |
          Math.cos(r + Math.PI * 2 / 3) * 127 + 128 << 8 |
          Math.cos(r + Math.PI * 4 / 3) * 127 + 128).toString(16).slice(1)
      )
    }
    return colors;
  }

  /**
   * 一个对象数组分成三分
   * @param data []
   * @param columns [[],[],[]]
   */
  columnsArr = (data: any[], columns) => {
    return data.reduce((columns, item) => {
      let minH = columns[0].reduce((s, v) => s += v.data.length + 2, 0)
      let n = 0

      for (let j = 1; j < columns.length; j++) {
        let jh = columns[j].reduce((s, v) => s += v.data.length + 2, 0)
        if (minH > jh) {
          minH = jh
          n = j
        }
      }
      columns[n].push(item)
      return columns
    }, columns)
  }

  /**
   * 防抖 多次触发后只执行一次
   * @param callback 
   * @param time 
   */
  debounce(callback, time = 800) {
    let timer = null;
    return function(...args) {
      clearTimeout(timer);
      timer = setTimeout(()=>{callback.apply(this, args)}, time);
    }
  }

  /**
   * 节流 多次触发，n秒内只执行一次，稀释函数的执行频率
   * @param callback 
   * @param time 
   */
  throttle(callback, time = 800) {
    let flag = true;
    return (...args) => {
      if (!flag) return;
      flag = false;
      setTimeout(() => {
        callback.apply(this, args);
        flag = true;
      }, time);
    }
  }
}