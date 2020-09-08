import { BrowserModule } from '@angular/platform-browser';

import { APP_INITIALIZER, LOCALE_ID, NgModule } from '@angular/core';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { AppComponent } from './app.component';
import { SharedModule } from './shared/shared.module';
import { RoutesModule } from './routes/routes.module';
import { CoreModule } from './core/core.module';
import { ThemeModule } from './theme/theme.module';
import { commonConfig } from '../assets/config/config.js';

// #region default language
// 参考：https://ng-alain.com/docs/i18n
import { default as ngLang } from '@angular/common/locales/zh';
import { NZ_DATE_LOCALE, NZ_I18N, zh_CN as zorroLang } from 'ng-zorro-antd/i18n';
import { zhCN as dateLang } from 'date-fns/locale';
const LANG = {
  abbr: 'zh',
  ng: ngLang,
  zorro: zorroLang,
  date: dateLang,
};
// register angular
import { registerLocaleData } from '@angular/common';
registerLocaleData(LANG.ng, LANG.abbr);
const LANG_PROVIDES = [
  { provide: LOCALE_ID, useValue: LANG.abbr },
  { provide: NZ_I18N, useValue: LANG.zorro },
  { provide: NZ_DATE_LOCALE, useValue: LANG.date },
];
// #endregion

// #region i18n services
import { I18NService } from './core';
import { TranslateLoader, TranslateModule } from '@ngx-translate/core';
import { TranslateHttpLoader } from '@ngx-translate/http-loader';

// 加载i18n语言文件
export function I18nHttpLoaderFactory(http: HttpClient) {
  return new TranslateHttpLoader(http, `assets/tmp/i18n/`, '.json');
}
const I18NSERVICE_MODULES = [
  TranslateModule.forRoot({
    loader: {
      provide: TranslateLoader,
      useFactory: I18nHttpLoaderFactory,
      deps: [HttpClient],
    },
  }),
];

const I18NSERVICE_PROVIDES = [{ provide: "I18N_TOKEN", useClass: I18NService, multi: false }];
// #endregion

// #region Http Interceptors

import { HTTP_INTERCEPTORS, HttpClient } from '@angular/common/http';
import { StartupService, DefaultInterceptor } from './core';
import { CacheInterceptor } from './core/net/cache.interceptor';
import { RouteReuseStrategy } from '@angular/router';
import { AppReuseStrategy } from './routes/route-reuse';

const INTERCEPTOR_PROVIDES = [
  { provide: HTTP_INTERCEPTORS, useClass: DefaultInterceptor, multi: true },
  { provide: HTTP_INTERCEPTORS, useClass: CacheInterceptor, multi: true },
];
// #endregion

// #region Startup Service
export function StartupServiceFactory(startupService: StartupService) {
  return () => startupService.load();
}
const APPINIT_PROVIDES = [
  StartupService,
  {
    provide: APP_INITIALIZER,
    useFactory: StartupServiceFactory,
    deps: [StartupService],
    multi: true,
  },
];
// #endregion

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,

    SharedModule,
    CoreModule,
    ThemeModule,
    RoutesModule,

    I18NSERVICE_MODULES
  ],
  providers: [
    ...INTERCEPTOR_PROVIDES,
    ...APPINIT_PROVIDES,
    ...I18NSERVICE_PROVIDES,
    { provide:'CONFIG', useValue: commonConfig },

    { provide: RouteReuseStrategy, useClass:  AppReuseStrategy},
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
