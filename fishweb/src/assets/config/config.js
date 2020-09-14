let regUrl = /https?:\/\/[^\/]*\/?/i;
let url = window.location.href;
let localUrl = regUrl.exec(url)[0];
localUrl = 'http://localhost:8090'

export const commonConfig = {
    url: (() => localUrl)(),
    microService: {
        BASE_DATA_SERVICE: '/base-data-service', // 基础数据
    }
}