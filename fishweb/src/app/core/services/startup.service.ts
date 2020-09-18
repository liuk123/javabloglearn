import { Injectable, Injector, Inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { zip } from 'rxjs';
import { catchError } from 'rxjs/operators';
import { MenuService } from './menu.service';
import { I18NService } from '../i18n/i18n.service';
import { TranslateService } from '@ngx-translate/core';
import { CommonService } from './common.service';

@Injectable()
export class StartupService {
  constructor(
    private menuService: MenuService,
    private commonService: CommonService,
    private http: HttpClient,
    private translate: TranslateService,
    @Inject('CONFIG') private config,
    @Inject("I18N_TOKEN") private i18n: I18NService,) {}

  load(): Promise<any> {
    return new Promise((resolve, reject) => {
      zip(
        this.http.get(`assets/tmp/i18n/${this.i18n.defaultLang}.json`), 
        this.http.get('assets/data/menu.json'),
        this.http.get(`${this.config.url}/user/currentUser`),
      ).pipe(
        // 接收其他拦截器后产生的异常消息
        catchError(([langData, appData, userData]) => {
          resolve(null);
          return [langData, appData, userData];
        }),
      )
      .subscribe(
        ([langData, appData, userData]) => {
          this.menuService.menu = appData.menu;
          
          // setting language data
          this.translate.setTranslation(this.i18n.defaultLang, langData);
          this.translate.setDefaultLang(this.i18n.defaultLang);

          //user
          console.log(userData);
          this.commonService.userSource.next(userData.data)
        },
        () => {},
        () => {
          resolve(null);
        }
      )
        
    });
  }
}
