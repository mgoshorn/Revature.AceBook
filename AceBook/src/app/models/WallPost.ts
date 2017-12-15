import {User } from './User';
export class WallPost {
    user: User;
    posttime: Array<Number>;
    content: String;
    comments: Array<Comment>;
    commentField: string;
    wallPostId: Number;
}
