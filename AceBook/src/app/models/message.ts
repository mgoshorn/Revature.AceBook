import { User } from './User';

export class Message {
    msgID: number;
    conversationID: number;
    content: string;
    sender: User;
    posttime: Array<Number>;
}
