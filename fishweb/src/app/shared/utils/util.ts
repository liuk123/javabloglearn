import { Injectable }   from '@angular/core';

@Injectable()
export class UtilService {
  constructor() { }
  
  getColors(n){
    let r = 0;
    let colors = [];
    for(let i=0; i<n; i++){
      r-=Math.PI*2/-n
      colors.push(
        '#'+ (
               1<<24|
              Math.cos(r)*127+128<<16 |
              Math.cos(r+Math.PI*2/3)*127+128<<8 |
              Math.cos(r+Math.PI*4/3)*127+128).toString(16).slice(1)
      )
    }
    return colors;
  }
}