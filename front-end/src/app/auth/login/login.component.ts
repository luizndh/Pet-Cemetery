import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Router, RouterLink } from '@angular/router';
import { AuthService } from '../auth.service';
import { LocalStorageService } from '../../shared/service/local-storage.service';
import { AlertComponent } from "../../shared/component/alert/alert.component";
import { ErrorMessageComponent } from '../../shared/component/error-message/error-message.component';

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
                // atualiza o estado da nav-bar, para que o usuário logado apareça
                this.router.navigate(['/home']).then(() => window.location.reload());
            },
            (error) => {
                console.log(error)
                this.errorMessage = error.error?.mensagem || 'Ocorreu um erro ao tentar fazer login.';
            }
        )
    }
}
