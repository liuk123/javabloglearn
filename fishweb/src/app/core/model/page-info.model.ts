import { CommonModel } from './common.model';

export class PageInfo<T> extends CommonModel {

    [key: string]: any;

    constructor(
        public pageNum?: number, // 当前页码
        public pageSize?: number, // 每页行数
        public pages?: number, // 总页数
        public total?: number, // 总行数
        public list?: Array<T>, // 实际数据集合

        public resultCode?: string,
        public resultMsg?: string,
        public loading?: boolean,
        public expandForm?: boolean,
        public selectedData?: T,
    ) {
        super();
        this.pageNum = pageNum || 1;
        this.pageSize = pageSize || 10;
        this.pages = pages || 0;
        this.list = list || [];
        this.loading = true;
        this.expandForm = false;
    }

    reset() {
        this.pageNum = 1;
        this.pageSize = 10;
        this.pages = 0;
        this.list = [];
    }

    get offset(): number {
        return this.pageNum - 1;
    }

    set offset(offset: number) {
        this.pageNum = offset + 1;
    }
}