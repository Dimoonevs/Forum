import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginComponent } from "./componenet/login/LoginComponent";
import { AuthLayoutComponent } from './shared/layouts/auth-layout/auth-layout.component';
import { CommunicationLayoutComponent } from './shared/layouts/communication-layout/communication-layout.component';
import { RegistrationComponent } from './componenet/registration/registration.component';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { TokenInterceptor } from './shared/classes/token.interceptor';
import { OverviewPageComponent } from './componenet/overview-page/overview-page.component';
import { CreateThemeComponent } from './componenet/create-theme/create-theme.component';
import { ThemeInfoComponent } from './componenet/theme-info/theme-info.component';
import { AdminLayoutComponent } from './shared/layouts/admin-layout/admin-layout.component';
import { AdminUserComponent } from './componenet/admin-user/admin-user.component';
import { AdminMessageComponent } from './componenet/admin-message/admin-message.component';
import { MessageSendComponent } from './componenet/message/message-send/message-send.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    AuthLayoutComponent,
    CommunicationLayoutComponent,
    RegistrationComponent,
    OverviewPageComponent,
    CreateThemeComponent,
    ThemeInfoComponent,
    AdminLayoutComponent,
    AdminUserComponent,
    AdminMessageComponent,
    MessageSendComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    HttpClientModule
  ],
  providers: [
    {
      provide: HTTP_INTERCEPTORS,
      multi: true,
      useClass: TokenInterceptor
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
