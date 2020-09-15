import { Injectable } from '@angular/core';

@Injectable()
export class UtilService {
  constructor() { }

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
  //把一个对象数组分成三分
  // columns:Navigation[][] = [[],[],[]]
  columnsArr = (data: any[], columns) => {
    columns = data.reduce((columns, item) => {
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
}