import { Routes } from '@angular/router';
import { HomeComponent } from '../app/components/home/home.component';
import { LoginComponent } from '../app/components/login/login.component';
import { UserComponent } from '../app/components/home/user/user.component';
import { ProfileComponent } from '../app/components/profile/profile.component';
import { FriendComponent } from '../app/components/home/friend/friend.component';
import { Profile } from 'selenium-webdriver/firefox';

export const appRoutes: Routes = [
    {
      path: 'login',
      component: LoginComponent,
      pathMatch: 'full'
    },
    {
      path: 'profile',
      component: ProfileComponent,
      pathMatch: 'full',
      children: [
        {
          path: 'user',
          component: UserComponent,
          pathMatch: 'full',
        },
        {
            path: 'profile',
            component: ProfileComponent,
            pathMatch: 'full',
        },
        {
            path: 'friend',
            component: FriendComponent,
            pathMatch: 'full',
        }
      ]
    },
    {
      path: '**',
      pathMatch: 'full',
      redirectTo: '/login'
    }
  ];
