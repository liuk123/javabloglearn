let regUrl = /https?:\/\/[^\/]*\/?/i;
let url = window.location.href;
let localUrl = regUrl.exec(url)[0].slice(0, -1);
// localUrl = 'http://localhost:4200'

export const commonConfig = {
    url: (() => localUrl)()
}