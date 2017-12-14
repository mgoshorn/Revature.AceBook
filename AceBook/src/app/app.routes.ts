import { Routes } from '@angular/router';
import { HomeComponent } from '../app/components/home/home.component';
import { LoginComponent } from '../app/components/login/login.component';
import { UserComponent } from '../app/components/home/user/user.component';
import { ProfileComponent } from '../app/components/profile/profile.component';
import { FriendComponent } from '../app/components/home/friend/friend.component';
import { Profile } from 'selenium-webdriver/firefox';
import { SignupComponent } from './components/signup/signup.component';
import { WallData } from './models/wall-data';
import { ProfileWallResolver } from './services/profile-wall-resolver.service';
import { ConversationComponent } from './components/conversation/conversation.component';
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
    path: '**',
    pathMatch: 'full',
    redirectTo: '/login'
  }
];
