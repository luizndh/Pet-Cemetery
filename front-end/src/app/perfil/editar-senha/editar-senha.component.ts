import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators, ReactiveFormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { LocalStorageService } from '../../shared/service/local-storage.service';
import { PerfilService } from '../perfil.service';

@Component({
    selector: 'app-editar-senha',
    imports: [ReactiveFormsModule],
    templateUrl: './editar-senha.component.html',
    styleUrl: './editar-senha.component.css'
})
export class EditarSenhaComponent {

    trocarSenhaForm: FormGroup;

    constructor(private fb: FormBuilder, private router: Router, private tokenService: LocalStorageService, private perfilService: PerfilService) {
        this.trocarSenhaForm = this.fb.group({
            senhaAtual: ['', [Validators.required, Validators.minLength(5)]],
            novaSenha: ['', [Validators.required, Validators.minLength(5)]],
            confirmarSenha: ['', [Validators.required, Validators.minLength(5)]]
        });
    }

    onSubmit() {
        if (this.trocarSenhaForm.valid) {
            this.perfilService.trocarSenha(this.trocarSenhaForm.value).subscribe(
                () => {
                    this.tokenService.removeToken();
                    this.router.navigate(['/']);
                }
            );
        }
    }
}