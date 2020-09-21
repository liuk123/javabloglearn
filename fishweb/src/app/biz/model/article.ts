import { User } from 'src/app/core/model/user.model';

export class ArtList{
  constructor(
    public id: string,
    public title: string,
    public desc: string,
    public author: User,
    public imgUrl: string,
    public content: string,
    public tag: string[],
  ){}
}