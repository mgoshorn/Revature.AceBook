import { Routes } from '@angular/router';
import { LoginComponent } from '../app/components/login/login.component';
import { ProfileComponent } from '../app/components/profile/profile.component';
<<<<<<< HEAD
import { Profile } from 'selenium-webdriver/firefox';
=======
import { FriendComponent } from '../app/components/home/friend/friend.component';
>>>>>>> fd3bb8dd58af7872d8c4c2f0264b68bdba6d0bc2
import { SignupComponent } from './components/signup/signup.component';
import { WallData } from './models/wall-data';
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
