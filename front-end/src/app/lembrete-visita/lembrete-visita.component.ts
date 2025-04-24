import { Component } from '@angular/core';
import { LembreteVisitaService } from './lembrete-visita.service';

@Component({
  selector: 'app-lembrete-visita',
  templateUrl: './lembrete-visita.component.html',
  styleUrls: ['./lembrete-visita.component.css'],
})
export class LembreteVisitaComponent {
  selectedDate: string | null = null;
  weather: { temp: number; description: string; humidity: number } | null = null;

  constructor(private service: LembreteVisitaService) {}

  async onDateChange(event: Event) {
    const input = event.target as HTMLInputElement;
    this.selectedDate = input.value;

    if (this.selectedDate) {
        this.service.recuperaDadosMeteorologicos().subscribe(response => {
            // Filtrar a previsão para a data selecionada
        const forecast = response.data.list.find((item: any) =>
            item.dt_txt.startsWith(this.selectedDate)
          );

          if (forecast) {
            this.weather = {
              temp: forecast.main.temp,
              description: forecast.weather[0].description,
              humidity: forecast.main.humidity,
            };
          } else {
            this.weather = null;
            alert('Nenhuma previsão disponível para esta data.');
          }
        })
    }
  }
}