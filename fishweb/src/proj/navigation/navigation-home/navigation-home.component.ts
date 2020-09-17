import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Navigation } from 'src/app/biz/model/navigation';
import { UtilService } from 'src/app/shared';

@Component({
  selector: 'app-navigation-home',
  templateUrl: './navigation-home.component.html',
  styleUrls: ['./navigation-home.component.less']
})
export class NavigationHomeComponent implements OnInit {

  navs:Navigation[] = []
  bannerUrl:string = 'https://tvax4.sinaimg.cn/large/6f8a2832gy1gdkralzwfoj21e00xc13g.jpg';

  searchBoxValue: string = '';
  get searchValue(){
    return encodeURIComponent(this.searchBoxValue)
  }
  set searchValue(v){
    this.searchBoxValue = v
  }
  searchUriData= searchUriData;

  constructor(
    private http: HttpClient
    ) { }

  ngOnInit(): void {
    this.http.get('assets/data/navigation.json').subscribe((res:Navigation[])=>{
      this.navs = res;
    })
  }

  search(searchUri:string,indexUri:string = ''){
    if(this.searchValue){
      window.open(searchUri + this.searchValue, '_blank')
    }else{
      window.open(indexUri, '_blank')
    }
  }

}


const searchUriData=[
  {
    name:'百度',
    searchUri:'https://www.baidu.com/s?wd=',
    indexUri:'https://www.baidu.com',
    type:'get',
    content:[
      {
        name:'谷歌',
        searchUri:'https://www.google.com/search?q=',
        indexUri:'https://www.google.com',
        type:'get'
      },
      {
        name:'必应',
        searchUri:'https://cn.bing.com/search?q=',
        indexUri:'https://cn.bing.com',
        type:'get'
      }
    ]
  },
  {
    name:'视频',
    searchUri:'https://so.iqiyi.com/so/q_',
    indexUri:'https://iqiyi.com',
    type:'get',
    width:'136px',
    content:[
      {
        name:'爱奇艺',
        searchUri:'https://so.iqiyi.com/so/q_',
        indexUri:'https://iqiyi.com',
        type:'get',
      },
      {
        name:'腾讯',
        searchUri:'https://v.qq.com/x/search/?q=',
        indexUri:'https://v.qq.com',
        type:'get'
      },
      {
        name:'优酷',
        searchUri:'https://so.youku.com/search_video/q_',
        indexUri:'https://youku.com',
        type:'get'
      },
      {
        name:'芒果',
        searchUri:'https://so.mgtv.com/so/k-',
        indexUri:'https://mgtv.com',
        type:'get'
      },
      {
        name:'bilibili',
        searchUri:'https://search.bilibili.com/all?keyword=',
        indexUri:'https://bilibili.com',
        type:'get'
      },
      {
        name:'acfun',
        searchUri:'http://www.acfun.cn/search/?#query=',
        indexUri:'http://www.acfun.cn/',
        type:'get'
      }
    ]
  },
  {
    name:'音乐',
    searchUri:'http://music.ifkdy.com/?type=netease&name=',
    indexUri:'http://music.ifkdy.com/?type=netease',
    type:'get',
    content:[
      {
        name:'网易',
        searchUri:'https://music.163.com/#/search/m/?s=',
        indexUri:'https://music.163.com',
        type:'get'
      },
      {
        name:'QQ',
        searchUri:'https://y.qq.com/portal/search.html#w=',
        indexUri:'https://y.qq.com',
        type:'get'
      }
    ]
  },
  {
    name:'知乎',
    searchUri:'https://www.zhihu.com/search?q=',
    indexUri:'https://www.zhihu.com',
    type:'get',
    content:[
      {
        name:'简书',
        searchUri:'https://www.jianshu.com/search?q=',
        indexUri:'https://www.jianshu.com',
        type:'get'
      },
      {
        name:'豆瓣',
        searchUri:'https://www.douban.com/search?q=',
        indexUri:'https://www.douban.com',
        type:'get'
      }
    ]
  },
  {
    name:'翻译',
    searchUri:'https://fanyi.baidu.com/?aldtype=16047#zh/en/',
    indexUri:'https://fanyi.baidu.com',
    type:'get',
    content:[
      {
        name:'英中',
        searchUri:'https://translate.google.cn/#view=home&op=translate&sl=en&tl=zh-CN&text=',
        indexUri:'https://translate.google.cn',
        type:'get'
      },
      {
        name:'中英',
        searchUri:'https://translate.google.cn/#view=home&op=translate&sl=zh-CN&tl=en&text=',
        indexUri:'https://translate.google.cn',
        type:'get'
      }
    ]
  },
  {
    name:'图片',
    searchUri:'https://image.baidu.com/search/index?tn=baiduimage&word=',
    indexUri:'https://image.baidu.com',
    type:'get',
    content:[
      {
        name:'谷歌',
        searchUri:'https://www.google.com/search?tbm=isch&q=',
        indexUri:'https://www.google.com/search?tbm=isch',
        type:'get'
      },
      {
        name:'必应',
        searchUri:'https://cn.bing.com/images/search?FORM=HDRSC2&q=',
        indexUri:'https://cn.bing.com/images/search?FORM=HDRSC2',
        type:'get'
      },
      {
        name:'花瓣',
        searchUri:'https://huaban.com/search/?q=',
        indexUri:'https://huaban.com/',
        type:'get'
      }
    ]
  },
  {
    name:'资源',
    searchUri:'http://www.pansoso.com/zh/',
    indexUri:'http://www.pansoso.com',
    type:'get',
    content:[
      {
        name:'磁力猫',
        searchUri:'https://www.cilimao.cc/search?word=',
        indexUri:'https://www.cilimao.cc',
        type:'get'
      },
      {
        name:'磁力站',
        searchUri:'http://oabt004.com/index?k=',
        indexUri:'http://oabt004.com/index',
        type:'get'
      },
      {
        name:'谷歌镜像',
        searchUri:'https://g.netis.io/search?q=',
        indexUri:'https://g.netis.io',
        type:'get'
      }
    ]
  },
  {
    name:'软件',
    searchUri:'https://pc.qq.com/search.html#!keyword=',
    indexUri:'https://pc.qq.com',
    type:'get',
    content:[
      {
        name:'谷歌商店',
        searchUri:'https://play.google.com/store/search?q=',
        indexUri:'https://play.google.com',
        type:'get'
      }
    ]
  },
]
