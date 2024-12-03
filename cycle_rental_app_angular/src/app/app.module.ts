import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { registerLocaleData } from '@angular/common';


import en from '@angular/common/locales/en';

// NG ZORRO IMPORTS
import { NZ_I18N, en_US } from 'ng-zorro-antd/i18n';


// Import your components
import { AppComponent } from './app.component';
import { LoginComponent } from './auth/components/login/login.component';
import { SignupComponent } from './auth/components/signup/signup.component';
import { AppRoutingModule } from './app-routing.module';
import { NgZorroImportsModule } from './NgZorroImportsModule';

// Registering English Locale
registerLocaleData(en);

@NgModule({
  declarations: [

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    BrowserAnimationsModule,
    NgZorroImportsModule
    
  ],
  providers: [
    { provide: NZ_I18N, useValue: en_US },
    
  ],
  bootstrap: [], // Bootstrap your main AppComponent
})
export class AppModule {}


