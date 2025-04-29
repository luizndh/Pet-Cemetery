import { Component } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { AuthService } from '../auth.service';
import { LocalStorageService } from '../../shared/service/local-storage.service';
import { Router } from '@angular/router';
import { AlertComponent } from "../../shared/component/alert/alert.component";

@Component({
  selector: 'app-cadastro',
  imports: [ReactiveFormsModule, AlertComponent],
  templateUrl: './cadastro.component.html',
  styleUrl: './cadastro.component.css'
})
export class CadastroComponent {
    cadastroForm: FormGroup;

  constructor(private fb: FormBuilder, private service: AuthService, private tokenService: LocalStorageService, private router: Router) {
    this.cadastroForm = this.fb.group({
        nome: ['', [Validators.required, Validators.minLength(3)]],
        email: ['', [Validators.required, Validators.email]],
        senha: ['', [Validators.required, Validators.minLength(5)]],
        senhaRepetida: ['', [Validators.required, Validators.minLength(5)]],
        cpf: ['', [Validators.required, Validators.pattern(/^\d{11}$/)]],
        cep: ['', [Validators.required, Validators.pattern(/^\d{8}$/)]],
        rua: ['', [Validators.required]],
        numero: ['', [Validators.required]],
        complemento: [''],
        telefone: ['', [Validators.required, Validators.pattern(/^\d{10,11}$/)]]
    });
  }

  onSubmit() {
    this.service.cadastro(this.cadastroForm.value).subscribe((response: any) => {
        this.tokenService.setToken(response.token);
        this.router.navigate(['/home']).then(() => window.location.reload());
    })
  }
}
