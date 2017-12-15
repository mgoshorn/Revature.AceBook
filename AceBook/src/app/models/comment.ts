import { User } from './User';
import { WallPost } from './WallPost';
export class Comment {
    author: User;
    posttime: Array<Number>;
    content: String;
}
