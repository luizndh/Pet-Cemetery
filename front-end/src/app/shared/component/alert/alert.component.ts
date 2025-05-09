import { NgIf } from '@angular/common';
import { Component, Input } from '@angular/core';
import { FormGroup, FormsModule } from '@angular/forms';

@Component({
  selector: 'app-alert',
  imports: [NgIf, FormsModule],
  styleUrls: ['./alert.component.css'],
  templateUrl: './alert.component.html'
})
export class AlertComponent {
    @Input() form!: FormGroup;

    // Retorna o primeiro campo inválido
    firstInvalidField(): string | null {
      const invalidField = Object.keys(this.form.controls).find(
        (field) => this.form.get(field)?.invalid && this.form.get(field)?.touched
      );
      return invalidField || null;
    }
}
