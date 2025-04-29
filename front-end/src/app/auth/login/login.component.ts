import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { Login } from '../model/login.model';
import { AuthService } from '../auth.service';
import { LocalStorageService } from '../../shared/service/local-storage.service';
import { AlertComponent } from "../../shared/component/alert/alert.component";

@Component({
  selector: 'app-login',
  imports: [RouterLink, ReactiveFormsModule, AlertComponent],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
    loginForm: FormGroup;

    constructor(private fb: FormBuilder, private authService: AuthService, private tokenService: LocalStorageService, private router: Router) {
        this.loginForm = this.fb.group({
            email: ['', [Validators.required, Validators.email]],
            senha: ['', [Validators.required, Validators.minLength(5)]]
        });
    }

    onSubmit() {
        this.authService.login(this.loginForm.value).subscribe(
            (response) => {
                console.log(response)
                this.tokenService.setToken(response as string);
                this.router.navigate(['/home']);
            }
        )
    }
}
