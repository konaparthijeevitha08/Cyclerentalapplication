import { Component } from '@angular/core';
import { FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { NzButtonModule } from 'ng-zorro-antd/button';
import { NzFormModule } from 'ng-zorro-antd/form';
import { NzSpinModule } from 'ng-zorro-antd/spin';
import { AuthService } from '../../services/auth/auth.service';
import { StorageService } from '../../services/storage/storage.service';
import { Router } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';
import { CommonModule } from '@angular/common';
@Component({
  selector: 'app-login',
  standalone: true,
  imports: [NzSpinModule,NzButtonModule,NzFormModule,ReactiveFormsModule,CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  isSpinning: boolean = false;
  loginForm!: FormGroup

  constructor(private fb:FormBuilder,
    private authService: AuthService,
    private router : Router,
    private message : NzMessageService
  ) { }

  ngOnInit() {
      this.loginForm = this.fb.group({
          email: [null, [Validators.email, Validators.required]],
          password: [null, [Validators.required]]
      })
  }

  login() {
    this.authService.login(this.loginForm.value).subscribe((res) => {
      console.log(res);
  
      if (res.userId != null) {
        const user = {
          id: res.userId,
          role: res.userRole
        };
  
        StorageService.saveUser(user);
        StorageService.saveToken(res.jwt);
  
        if (StorageService.isAdminLoggedIn()) {
          this.router.navigateByUrl("/admin/dashboard");
        } else if (StorageService.isCustomerLoggedIn()) {
          this.router.navigateByUrl("/customer/dashboard");
        } else {
          this.message.error("Bad credentials", { nzDuration: 50000 });
        }
      }
    });
  }

}
