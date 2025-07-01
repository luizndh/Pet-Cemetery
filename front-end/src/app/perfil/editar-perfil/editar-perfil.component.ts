import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { PerfilService } from '../perfil.service';
import { Router } from '@angular/router';
import { AlertComponent } from "../../shared/component/alert/alert.component";
import { DadosPerfil } from '../model/dados-perfil.model';

@Component({
  selector: 'app-editar-perfil',
  imports: [ReactiveFormsModule, AlertComponent],
  templateUrl: './editar-perfil.component.html'
})
export class EditarPerfilComponent implements OnInit {
  editarPerfilForm: FormGroup;

  constructor(
    private fb: FormBuilder,
    private perfilService: PerfilService,
    private router: Router
  ) {
    this.editarPerfilForm = this.fb.group({
      nome: ['', [Validators.required, Validators.minLength(3)]],
      email: [{ value: '', disabled: true }, [Validators.required, Validators.email]],
      cpf: [{ value: '', disabled: true }, [Validators.required, Validators.pattern(/^\d{11}$/)]],
      cep: ['', [Validators.required, Validators.pattern(/^\d{8}$/)]],
      rua: ['', [Validators.required]],
      numero: ['', [Validators.required]],
      complemento: [''],
      telefone: ['', [Validators.required, Validators.pattern(/^\d{10,11}$/)]]
    });
  }

  ngOnInit(): void {
    this.perfilService.getPerfil().subscribe((perfil: DadosPerfil) => {
      this.editarPerfilForm.patchValue({
        nome: perfil.nome,
        email: perfil.email,
        cpf: perfil.cpf,
        cep: perfil.cep,
        rua: perfil.rua,
        numero: perfil.numero,
        complemento: perfil.complemento,
        telefone: perfil.telefone
      });
    });
  }

  onSubmit() {
    if (this.editarPerfilForm.valid) {
      const formValue = this.editarPerfilForm.value;
      this.perfilService.editarPerfil(formValue).subscribe(() => {
        this.router.navigate(['/perfil']);
      });
    }
  }
}