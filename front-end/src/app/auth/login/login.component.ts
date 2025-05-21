import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../auth.service';
import { LocalStorageService } from '../../shared/service/local-storage.service';
import { AlertComponent } from "../../shared/component/alert/alert.component";
import { ErrorMessageComponent } from '../../shared/component/error-message/error-message.component';
import { jwtDecode } from 'jwt-decode';

@Component({
  selector: 'app-login',
  imports: [RouterLink, ReactiveFormsModule, AlertComponent, ErrorMessageComponent],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {
    loginForm: FormGroup;
    errorMessage: string | null = null;

    constructor(private fb: FormBuilder, private authService: AuthService, private tokenService: LocalStorageService, private router: Router) {
        this.loginForm = this.fb.group({
            email: [''],
            senha: ['']
        });
    }

    onSubmit() {
        this.authService.login(this.loginForm.value).subscribe(
            (response: any) => {
                this.tokenService.setToken(response.token);

                // Decodifica o token para pegar as roles
                const decoded: any = jwtDecode(response.token);

                if (decoded.roles && decoded.roles.some((role: any) => role.authority === "CLIENTE")) {
                    this.router.navigate(['/home']);
                } else if (decoded.roles && decoded.roles.some((role: any) => role.authority === "ADMIN")) {
                    this.router.navigate(['/admin/home']);
                }
                else {
                    this.router.navigate(['/']);
                }
            },
            (error) => {
                console.log(error)
                this.errorMessage = error.error?.mensagem || 'Ocorreu um erro ao tentar fazer login.';
            }
        )
    }
}
