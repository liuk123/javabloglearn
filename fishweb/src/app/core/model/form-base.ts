export class FormBase<T> {
    value: T;
    key: string;
    label: string;
    controlType: ControlType;
    type?: string;
    options?: { key: string, value: string }[];
    placeHolder?: string;
    disabled?: boolean;
    valide?: any[];

    constructor(options: {
        value?: T,
        key?: string,
        label?: string,
        valide?: any[],
        controlType?: ControlType,
        type?: string,
        placeHolder?: string,
        disabled?: boolean
    } = {}) {
        this.value = options.value;
        this.key = options.key || '';
        this.label = options.label || '';
        this.valide = options.valide||[];
        this.controlType = options.controlType || 'textbox';
        this.type= options.type||'text',
        this.placeHolder = options.placeHolder||'',
        this.disabled = options.disabled||false
    }
}
type ControlType = 'textbox'|'dropdown'|'rangePicker';

// export class TextboxInput extends FormBase<string> {
//     controlType: ControlType = 'textbox';
//     type: string;

//     constructor(options: {} = {}) {
//         super(options);
//         this.type = options['type'] || '';
//     }
// }
// export class DropdownInput extends FormBase<string> {
//     controlType:ControlType = 'dropdown';
//     options: {key: string, value: string}[] = [];

//     constructor(options: {} = {}) {
//         super(options);
//         this.options = options['options'] || [];
//     }
// }