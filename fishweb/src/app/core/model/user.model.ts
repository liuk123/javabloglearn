export class User{
  constructor(
    public id?: string,
    public username?: string,
    public password?: string,
    public role?: number,
    public phone?: number,
  ){}
}