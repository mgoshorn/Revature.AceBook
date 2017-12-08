import { Routes } from '@angular/router';
import { HomeComponent } from '../app/components/home/home.component';
import { LoginComponent } from '../app/components/login/login.component';
import { UserComponent } from '../app/components/home/user/user.component';
import { ProfileComponent } from '../app/components/home/profile/profile.component';
import { FriendComponent } from '../app/components/home/friend/friend.component';
import { Profile } from 'selenium-webdriver/firefox';

export const appRoutes: Routes = [
    {
      path: 'login',
      component: LoginComponent
    },
    {
      path: 'home',
      component: HomeComponent,
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
