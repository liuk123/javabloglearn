import { Injectable } from '@angular/core';
import { objectUtil } from 'prime-jsutils';
import { Menu, BreadcrumbMenu } from '../model/menu.model';
import { Subject } from 'rxjs';

const replaceObj = {
  // state: 'id',
}

@Injectable({
  providedIn: 'root',
})
export class MenuService {

  private _menu: Menu[] = [];
  get menu() {
    return this._menu;
  }
  set menu(v) {
    this._menu = objectUtil.replaceObjKey(v, replaceObj);
  }

  breadcrumbStr: string;
  breadcrumbMenu: BreadcrumbMenu[] = [];

  private itemSource = new Subject<BreadcrumbMenu[]>();
  routerEvent = this.itemSource.asObservable();

  /**
   * 
   * @param value 改变面包屑导航
   */
  setTitle(value) {
    this.breadcrumbStr = value;
    let links = this.breadcrumbStr.slice(1).split('/');
    this.breadcrumbMenu.length = 0;
    this.setBreadcrumb(links, 0, this.menu);
    this.menu = this.setMenuOpen(this.menu, this.breadcrumbMenu);
    this.itemSource.next(this.breadcrumbMenu);
  }

  setBreadcrumb(links, index, menu) {
    for (let menuItem of menu) {
      if (!objectUtil.isBlank(menuItem.route) && links[index] == menuItem.route.slice(menuItem.route.lastIndexOf('/')+1)) {

        if (menuItem.type == "router") {
          this.breadcrumbMenu.push({
            title: menuItem.title,
            type: "router",
            route: menuItem.route,
          })
        } else if (menuItem.type == "link") {
          this.breadcrumbMenu.push({
            title: menuItem.title,
            type: "link",
            link: menuItem.link,
          })
        } else if (menuItem.type == "sub") {
          this.breadcrumbMenu.push({
            title: menuItem.title,
            type: "sub",
            route: menuItem.route,
            children: this.addBreadcrumb(menuItem.children)
          })
        }
        if (links.length > index && objectUtil.isArray(menuItem.children) && menuItem.children.length > 0) {
          this.setBreadcrumb(links, index + 1, menuItem.children);
        }

      }
    }
  }

  addBreadcrumb(menu){
    let tem = []
    for(let menuItem of menu){
      if (menuItem.type == "router") {
        tem.push({
          title: menuItem.title,
          route: menuItem.route,
        })
      } else if (menuItem.type == "link") {
        tem.push({
          title: menuItem.title,
          link: menuItem.link,
        })
      } else if (menuItem.type == "sub") {
        tem.push({
          title: menuItem.title,
          children: this.addBreadcrumb(menuItem.children)
        })
      }
    }
    return tem;
  }

  setMenuOpen(menu,breadcrumbMenu){
    if(objectUtil.isObject(menu) && menu.type == 'sub'){
      if(breadcrumbMenu.map(v=>{if(v.route) return v.route}).includes(menu.route.slice(menu.route.lastIndexOf('/')))){
        menu.open = true;
      }else{
        menu.open = false;
      }
      if(menu.children){
        this.setMenuOpen(menu.children, breadcrumbMenu);
      }
    }else if(objectUtil.isArray(menu)){
      menu.forEach(v=>{
        this.setMenuOpen(v, breadcrumbMenu);
      })
    }
    return menu;
  }

}
