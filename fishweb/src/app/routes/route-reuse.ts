import { RouteReuseStrategy, DetachedRouteHandle, ActivatedRoute, ActivatedRouteSnapshot } from '@angular/router';
import { Subject, Observable } from 'rxjs';

export class RouteMsg {
    constructor(public type: string, public url: string) {}
}

export class AppReuseStrategy implements RouteReuseStrategy{
    public static handlers: { [key: string]: DetachedRouteHandle } = {};
    public static routeText$ = new Subject<RouteMsg>();

    public static getRouteText(): Observable<RouteMsg> {
        return AppReuseStrategy.routeText$.asObservable();
    }

    shouldDetach(route: ActivatedRouteSnapshot): boolean {
        console.log('shouldDetach======>', route);
        if (!route.data.keep) {
            return false;
        }
        AppReuseStrategy.routeText$.next(new RouteMsg('detach', route['_routerState'].url));
        return true;
    }

    store(route: ActivatedRouteSnapshot, handle: DetachedRouteHandle): void {
        console.log('store======>', route, handle);
        AppReuseStrategy.handlers[this.getRouteUrl(route)] = handle;
    }

    shouldAttach(route: ActivatedRouteSnapshot): boolean {
        console.log('shouldAttach======>', route);
        return (route.data.keepParent || !route.routeConfig.children && !route.routeConfig.loadChildren) && 
          !!AppReuseStrategy.handlers[this.getRouteUrl(route)];
    }

    retrieve(route: ActivatedRouteSnapshot): DetachedRouteHandle {
        console.log('retrieve======>', route);
        if ((!route.data.keepParent && (route.routeConfig.children || route.routeConfig.loadChildren)) || !AppReuseStrategy.handlers[this.getRouteUrl(route)]) {
            return null;
        }
        AppReuseStrategy.routeText$.next(new RouteMsg('attach', route['_routerState'].url));
        return AppReuseStrategy.handlers[this.getRouteUrl(route)];
    }

    shouldReuseRoute(future: ActivatedRouteSnapshot, curr: ActivatedRouteSnapshot): boolean {
        console.log('shouldReuseRoute======>');
        return (!curr.data.keepParent || !future.data.keepParent) && 
              (future.routeConfig === curr.routeConfig && JSON.stringify(future.params) === JSON.stringify(curr.params));
    }

    getRouteUrl(route: ActivatedRouteSnapshot) {
        const path = route['_routerState'].url.replace(/\//g, '_');
        return path;
    }
}

//在对应组件订阅该对象
//此时组件不再重新初始化，以前放在Init和Destroy钩子里做的事情可能需要考虑找个时机来做，可以使rxjs订阅来做，修改策略代码，增加subject，
// AppReuseStrategy. getRouteText().subscrib(res => {
//     if(res.res === this.url) {
//       if(res.type === 'detach') {
//         // 组件切换出
//       } else {
//         // 组件恢复时
//       }
//     }
//   });