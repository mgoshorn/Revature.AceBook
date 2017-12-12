import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule} from '@angular/common/http';


import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { HomeComponent } from '../app/components/home/home.component';
import { UserComponent } from '../app/components/home/user/user.component';
import { ProfileComponent } from '../app/components/profile/profile.component';
import { FriendComponent } from '../app/components/home/friend/friend.component';
import { FriendListComponent } from './components/friend/friend-list/friend-list.component';
import { PostComponent } from './components/post/post.component';
import { PostListComponent } from './components/post/post-list/post-list.component';
import { SignupComponent } from './components/signup/signup.component';
import { ProfileWallComponent } from './components/profile-wall/profile-wall.component';
import { WallNewPostComponent } from './components/wall-new-post/wall-new-post.component';
import { WallPostsComponent } from './components/wall-posts/wall-posts.component';

import { LoginService } from '../app/services/login.service';
import { SignupService } from './services/signup.service';

import { ProfileWallResolver } from './services/profile-wall-resolver.service';

import { appRoutes } from './app.routes';








@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    HomeComponent,
    ProfileComponent,
    FriendComponent,
    UserComponent,
    PostComponent,
    PostListComponent,
    FriendListComponent,
    SignupComponent,
    ProfileWallComponent,
    WallNewPostComponent,
    WallPostsComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(appRoutes, { useHash: true }),
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [LoginService, SignupService, ProfileWallResolver],
  bootstrap: [AppComponent]
})
export class AppModule { }
