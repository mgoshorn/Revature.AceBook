import { Routes } from '@angular/router';
import { HomeComponent } from '../app/components/home/home.component';
import { LoginComponent } from '../app/components/login/login.component';
import { UserComponent } from '../app/components/home/user/user.component';
import { ProfileComponent } from '../app/components/profile/profile.component';
import { FriendComponent } from '../app/components/home/friend/friend.component';
import { Profile } from 'selenium-webdriver/firefox';
import { SignupComponent } from './components/signup/signup.component';

export const appRoutes: Routes = [
    {
      path: 'login',
      component: LoginComponent
    },
    {
      path: 'signup',
      component: SignupComponent
    },
    {
      path: 'profile',
      component: ProfileComponent,
      children: [
        {
          path: 'user',
          component: UserComponent
        },
        {
            path: 'profile',
            component: ProfileComponent
        },
        {
            path: 'friend',
            component: FriendComponent
        }
      ]
    },
    {
      path: '**',
      pathMatch: 'full',
      redirectTo: '/login'
    }
  ];
