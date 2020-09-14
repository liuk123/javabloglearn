import {Injectable} from '@angular/core';
import {HttpHeaders} from '@angular/common/http';
import {
  EnvironmentEnumModel,
  RunningEnvioment
} from '../model/interface/initialization';

@Injectable({
  providedIn: 'root'
})
export class CommonConfigService {

  constructor() {
  }

  /**
   * 统一的sessionStroage 中的key
   */
  getSessionStroageKey() {
    const SESSION_STROAGE_KEY = {
      loginUser: 'login_user',
      headerUser: 'header_user',
      purchaseSourceFindingProject: 'purchase-source-finding-project',
      runningEnvironment: 'running-environment',
    };
    return SESSION_STROAGE_KEY;
  }

  isProdEnvironment() {
    const RunningEnvioment = this.getRunningEnviroment();
    return RunningEnvioment.environmentEnum === EnvironmentEnumModel.PRODUCTION || 
            RunningEnvioment.environmentEnum === EnvironmentEnumModel.DEPLOY;
  }

  /**
   * 默认header信息
   * PRODUCTION、DEPLOY时不返回万能串
   */
  getDefaultHeader() {
    const headerJson = this.getDefaultHeaderJson();
    const headers = new HttpHeaders(headerJson);
    return headers;
  }

  getMasterKeyHeader(){
    const headerJson = this.getMasterKeyJson();
    const headers = new HttpHeaders(headerJson);
    return headers;
  }

  /**
   * 默认header信息
   * PRODUCTION、DEPLOY时不返回万能串
   */
  getDefaultHeaderJson() {
    if (this.isProdEnvironment()) {
      return {'Authorization': 'allrole'};
    }
    return this.getMasterKeyJson();
  }
  getMasterKeyJson(){
    const headers = {
      'Authorization': 'bearer eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyRW50aXR5Ijp7InVzZXJJZCI6IjEyNDMzOTI5NzM3NjgwMDc2OCIsInBhc3N3b3JkIjoiIiwidXNlckNvZGUiOiJqd3RUZXN0IiwidXNlclJlYWxOYW1lIjoibG9naW50ZXN0IiwidXNlclN0YXRlIjoiMCIsImNyZWF0ZVRpbWUiOm51bGwsImNyZWF0b3IiOm51bGwsImxhc3RNb2RpZnlUaW1lIjpudWxsLCJsYXN0TW9kaWZpZXIiOm51bGwsInZhbGlkVGltZSI6bnVsbCwiZXhwaXJlVGltZSI6bnVsbCwibG9ja1RvIjpudWxsLCJwaG9uZSI6IjE1NTU1NTU1NTU1IiwiZW1haWwiOiJsb2dpbnRlc3RAbGcuY29tIiwicHN3RXhwdGltZSI6bnVsbCwibm90ZSI6bnVsbCwiYXV0aG9yaXRpZXMiOlt7ImF1dGhvcml0eSI6IlJPTEVfVVNFUiJ9XSwiZW5hYmxlZCI6dHJ1ZSwidXNlcm5hbWUiOiJqd3RUZXN0IiwiYWRtaW4iOmZhbHNlLCJhY2NvdW50Tm9uRXhwaXJlZCI6dHJ1ZSwiY3JlZGVudGlhbHNOb25FeHBpcmVkIjp0cnVlLCJhY2NvdW50Tm9uTG9ja2VkIjp0cnVlfSwidXNlcl9uYW1lIjoiand0VGVzdCIsInNjb3BlIjpbImFsbCJdLCJleHAiOjE4NDc2ODY4MzMsImF1dGhvcml0aWVzIjpbIlJPTEVfVVNFUiJdLCJqdGkiOiJiMzdkMTc1Ny01NmUxLTQzMTAtYmYxNC1mNjZmZmE1M2Q0MjMiLCJjbGllbnRfaWQiOiI5MDAxMDAxMDEwIn0.D-fkT7yWtpXw_LkE4_6-EuMYI2-AYsYttDcl0RNgdyFNM0V9s9I0-ihH53hsS-X1NhYvgfLKQSRmlZZFDUrpAWuh_eIVw5YmY686u-GqN8LwpAInd5ECUGPNQ3-yb4W-rIhopNPOZONYRH8f12eAly-BE-8tFZhDei0SuW-k9OVHyqfu7LRPwPUl4DLbSrLxvit3-_SQ40syUkCWRPPZBB_wAIzHfXOZxvRUBjXjRS78D1xTFPiNolq-UukOcQbzNw00c7IE-k9r9geu1SXUm1i4XDVWFpl2Xq921nzyRldvf7vDmMpUDdat7jnOEVsE2kOkF3LwljphH2ErpSygqg',
      'user': this.getHeaderUserStr() || 'none'
    };
    return headers;
  }

  /**
   * 设置运行环境
   * @param RunningEnvioment
   */
  setRunningEnviroment(RunningEnvioment: RunningEnvioment) {
    if (RunningEnvioment) {
      sessionStorage.setItem(this.getSessionStroageKey().runningEnvironment, JSON.stringify(RunningEnvioment));
    }
  }

  /**
   * 获取运行环境
   */
  getRunningEnviroment(): RunningEnvioment {
    const str = sessionStorage.getItem(this.getSessionStroageKey().runningEnvironment);
    if (str) {
      return JSON.parse(str);
    }
    return null;
  }

  /**
   * 存放登录用户信息
   * @param loginUser：登录用户
   */
  setLoginUser(loginUser) {
    if (loginUser) {
      sessionStorage.setItem(this.getSessionStroageKey().loginUser, JSON.stringify(loginUser));
    }
  }

  /**
   * 获取登录用户信息
   */
  getLoginUser(){
    if (!this.isProdEnvironment()) {
      return this.getHeaderUser();
    }
    const loginUserStr = sessionStorage.getItem(this.getSessionStroageKey().loginUser);
    if (loginUserStr) {
      return JSON.parse(loginUserStr);
    } else {
      return null;
    }
  }

  /**
   * 存放模拟用户信息
   * @param headerUser：登录用户
   */
  setHeaderUser(headerUser) {
    if (headerUser) {
      sessionStorage.setItem(this.getSessionStroageKey().headerUser, JSON.stringify(headerUser));
    }
  }

  /**
   * 获取模拟用户信息
   */
  getHeaderUserStr() {
    const headerUserStr = sessionStorage.getItem(this.getSessionStroageKey().headerUser);
    return headerUserStr;
  }

  /**
   * 获取模拟用户信息
   */
  getHeaderUser() {
    if (this.isProdEnvironment()) {
      const userStr = this.getHeaderUserStr();
      if (userStr) {
        return JSON.parse(userStr);
      }
      return null;
    }
    return null;
  }


  /**
   * 界面样式相关配置
   */
  getPageStyleConfig() {
    return {
      common: {
        nzSize: 'small'
      },
      modal: {
        small: {
          width: '450px',
          height: '260px',
          overflow: 'auto'
        },
        middle: {
          width: '560px',
          height: '450px',
          overflow: 'auto'
        },
        large: {
          width: '1000px',
          height: '480px',
          overflow: 'auto'
        },
      }
    };
  }

  /**
   * 清空缓存
   */
  clear() {
    sessionStorage.clear();
  }
}
