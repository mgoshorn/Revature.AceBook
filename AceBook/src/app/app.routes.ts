import { Routes } from '@angular/router';
import { LoginComponent } from '../app/components/login/login.component';
import { ProfileComponent } from '../app/components/profile/profile.component';
import { FriendComponent } from '../app/components/friend/friend.component';
import { SignupComponent } from './components/signup/signup.component';
import { WallData } from './models/wall-data';
import { ConversationComponent } from './components/conversation/conversation.component';
import { FeedComponent } from './components/feed/feed.component';
export const appRoutes: Routes = [
  {
    path: 'login',
    component: LoginComponent,
    pathMatch: 'full'
  },
  {
    path: 'signup',
    component: SignupComponent
  },
  {
    path: 'profile/:id',
    component: ProfileComponent,
  },
  {
    path: 'message/:id',
    component: ConversationComponent
  },
  {
    path: 'feed',
    component: FeedComponent,
    pathMatch: 'full'
  },
  {
    path: '**',
    pathMatch: 'full',
    redirectTo: '/login'
  }
];
