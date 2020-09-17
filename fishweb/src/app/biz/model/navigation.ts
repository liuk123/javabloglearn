

export interface Navigation {
  title: string;
  desc: string;
  data: NavigationItem[];
}

export interface NavigationItem {
  name: string;
  desc: string;
  url?: string;
  route?: string;
  ico: string;
  type: 'link'|'router';
}
