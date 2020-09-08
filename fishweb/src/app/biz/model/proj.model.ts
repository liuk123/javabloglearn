export class ProjModel{
    constructor(
        public projName?: string,
        public projCode?: string,
        public amount?: string,
        public projTime?: string,
        public projType?: string,
    ){
        if(!projType) this.projType = ProjModel.projTypeDefaultValue;
    }
    
    static projTypeDefaultValue = "3"
    static projTypeOption = [
        {code: '1', name: '项目类型1'},
        {code: '2', name: '项目类型2'},
        {code: '3', name: '项目类型3'}
    ]
}