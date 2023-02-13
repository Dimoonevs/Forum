import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminMessageComponent } from './componenet/admin-message/admin-message.component';
import { AdminUserComponent } from './componenet/admin-user/admin-user.component';
import { CreateThemeComponent } from './componenet/create-theme/create-theme.component';
import { LoginComponent } from "./componenet/login/LoginComponent";
import { MessageSendComponent } from './componenet/message/message-send/message-send.component';
import { OverviewPageComponent } from './componenet/overview-page/overview-page.component';
import { RegistrationComponent } from './componenet/registration/registration.component';
import { ThemeInfoComponent } from './componenet/theme-info/theme-info.component';
import { AdminGuard } from './shared/classes/admin.guard';
import { AuthGuard } from './shared/classes/auth.guard';
import { AdminLayoutComponent } from './shared/layouts/admin-layout/admin-layout.component';
import { AuthLayoutComponent } from './shared/layouts/auth-layout/auth-layout.component';
import { CommunicationLayoutComponent } from'./shared/layouts/communication-layout/communication-layout.component';

const routes: Routes = [

  {path: '', component: AuthLayoutComponent, children:
    [
      {path:"", redirectTo:'/login', pathMatch:"full"},
      {path:"login", component: LoginComponent},
      {path:"reg", component: RegistrationComponent},
      {path:"message", component:MessageSendComponent}
    ]
  },{
    path: '', component: CommunicationLayoutComponent, canActivate:[AuthGuard], children: [
      {path: 'overview', component: OverviewPageComponent},
      {path: 'create_theme', component: CreateThemeComponent},
      {path: "theme/:id", component: ThemeInfoComponent}
    ]
  },
  {
    path: 'admin', component: AdminLayoutComponent, canActivate:[AdminGuard], children: [
      // {path: '', redirectTo:"/users", pathMatch:'full'},
      {path: 'users', component: AdminUserComponent},
      {path: 'message', component: AdminMessageComponent}
    ]
  }
  
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
