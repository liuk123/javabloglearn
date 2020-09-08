export interface Tag {
  color: string; //颜色
  value: string; //消息数量
}

export interface ChildrenItem {
  title: string;
  type: MenuType;
  disabled: boolean;
  selected: boolean;
  open?: boolean;
  route?: string;
  link?: string;
  badge?: Tag;
  children?: ChildrenItem[];
}
export interface Menu {
 
  title: string;
  type: MenuType;
  icon: string;
  disabled: boolean;
  selected: boolean;
  open?: boolean;
  route?: string;
  link?: string;
  badge?: Tag;
  children?: ChildrenItem[];
}

export interface BreadcrumbMenu {
  title: string;
  type?: MenuType;
  icon?: string;
  link?: string;
  route?: string;
  children?: any;
}

export type MenuType = 'link' | 'router' | 'sub' ;