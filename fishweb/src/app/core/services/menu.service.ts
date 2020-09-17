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
    this.breadcrumbStr = value.indexOf(";") != -1 ? value.slice(0, value.indexOf(";")) : value;
    let links = this.breadcrumbStr.slice(1).split('/');
    this.breadcrumbMenu.length = 0;
    this.setBreadcrumb(links, 0, this.menu);
    this.itemSource.next(this.breadcrumbMenu);
  }

  setBreadcrumb(links, index, menu) {
    for (let menuItem of menu) {
      if (!objectUtil.isBlank(menuItem.route) &&
        links[index] == (menuItem.route.lastIndexOf('/')!=-1?menuItem.route.slice(menuItem.route.lastIndexOf('/')+1): menuItem.route)) {

        if (menuItem.type == "router") {
          this.breadcrumbMenu.push({
            title: menuItem.title,
            type: "router",
            route: menuItem.route,
            children: this.addBreadcrumb(menu, menuItem.title)
          })
        } else if (menuItem.type == "link") {
          this.breadcrumbMenu.push({
            title: menuItem.title,
            type: "link",
            link: menuItem.link,
            children: this.addBreadcrumb(menu, menuItem.title)
          })
        } else if (menuItem.type == "sub") {
          this.breadcrumbMenu.push({
            title: menuItem.title,
            type: "sub",
            route: menuItem.route,
            children: this.addBreadcrumb(menu, menuItem.title)
          })
        }
        if (links.length > index && objectUtil.isArray(menuItem.children) && menuItem.children.length > 0) {
          this.setBreadcrumb(links, index + 1, menuItem.children);
        }

      }
    }
  }

  addBreadcrumb(menu, currenttitle?) {
    let tem = []
    for (let menuItem of menu) {
      if (menuItem.type == "router") {
        if (currenttitle && currenttitle != menuItem.title) {
          tem.push({
            title: menuItem.title,
            type: "router",
            route: menuItem.route,
          })
        }

      } else if (menuItem.type == "link") {
        tem.push({
          title: menuItem.title,
          type: "link",
          link: menuItem.link,
        })
      } else if (menuItem.type == "sub") {
        if (currenttitle && currenttitle != menuItem.title) {
          tem.push({
            title: menuItem.title,
            type: "sub",
            children: this.addBreadcrumb(menuItem.children, menuItem.title)
          })
        }
      }
    }
    return tem;
  }
}
