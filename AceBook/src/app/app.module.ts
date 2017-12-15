import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule} from '@angular/common/http';


import { AppComponent } from './app.component';
import { LoginComponent } from './components/login/login.component';
import { ProfileComponent } from '../app/components/profile/profile.component';
import { FriendComponent } from '../app/components/friend/friend.component';
import { FriendRequestComponent } from './components/friend/friend-request/friend-request.component';
import { FriendListComponent } from './components/friend/friend-list/friend-list.component';
import { SignupComponent } from './components/signup/signup.component';
import { ProfileWallComponent } from './components/profile-wall/profile-wall.component';
import { WallNewPostComponent } from './components/wall-new-post/wall-new-post.component';
import { WallPostsComponent } from './components/wall-posts/wall-posts.component';

import { LoginService } from '../app/services/login.service';
import { SignupService } from './services/signup.service';
import { FriendService } from './services/friend.service';
import { FriendRequestService } from './services/friend-request.service';


import { appRoutes } from './app.routes';
import { StorageService } from './services/storage.service';
import { WallPostService } from './services/wall-post.service';

import { FeedComponent } from './components/feed/feed.component';
import { ProfileImageUploadService } from './services/profile-image-upload.service';
import { NavbarComponent } from './components/navbar/navbar.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    ProfileComponent,
    SignupComponent,
    ProfileWallComponent,
    WallNewPostComponent,
    WallPostsComponent,
    FriendComponent,
    FeedComponent,
    FriendListComponent,
    FriendRequestComponent,
    NavbarComponent
  ],
  imports: [
    BrowserModule,
    RouterModule.forRoot(appRoutes, { useHash: true }),
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [LoginService, SignupService, FriendRequestService, StorageService, WallPostService,
    FriendService, ProfileImageUploadService ],

  bootstrap: [AppComponent]
})
export class AppModule { }
