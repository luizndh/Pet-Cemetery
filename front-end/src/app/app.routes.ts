import { Routes } from '@angular/router';
import { PaginaInicialComponent } from './pagina-inicial/pagina-inicial.component';
import { LoginComponent } from './auth/login/login.component';
import { CadastroComponent } from './auth/cadastro/cadastro.component';
import { QuemSomosComponent } from './info-navbar/quem-somos/quem-somos.component';
import { PlanosComponent } from './info-navbar/planos/planos.component';
import { ContatoComponent } from './info-navbar/contato/contato.component';
import { HomeComponent } from './home/home.component';
import { PaginaDoacaoComponent } from './doacoes/pagina-doacao/pagina-doacao.component';
import { MapaJazigoComponent } from './compra/mapa-jazigo/mapa-jazigo.component';
import { LembreteVisitaComponent } from './lembrete-visita/lembrete-visita.component';
import { ExibirPerfilComponent } from './perfil/exibir-perfil/exibir-perfil.component';
import { EditarPerfilComponent } from './perfil/editar-perfil/editar-perfil.component';
import { EditarSenhaComponent } from './perfil/editar-senha/editar-senha.component';
import { AgendarReuniaoComponent } from './reuniao/agendar-reuniao/agendar-reuniao.component';
import { PaginaHorarioComponent } from './info-navbar/horario-funcionamento/pagina-horario/pagina-horario.component';
import { PainelAdminComponent } from './admin/painel-admin/painel-admin.component';
import { AuthGuard } from './shared/guard/auth-guard';
import { AlterarHorarioFuncionamentoComponent } from './admin/alterar-horario-funcionamento/alterar-horario-funcionamento.component';
import { VisualizarReunioesComponent } from './admin/reuniao/visualizar-reunioes/visualizar-reunioes.component';
import { PainelRelatoriosComponent } from './admin/relatorios/painel-relatorios/painel-relatorios.component';
import { RelatorioOcupacaoComponent } from './admin/relatorios/relatorio-ocupacao/relatorio-ocupacao.component';
import { RelatorioExumacoesComponent } from './admin/relatorios/relatorio-exumacoes/relatorio-exumacoes.component';
import { RelatorioEnterrosComponent } from './admin/relatorios/relatorio-enterros/relatorio-enterros.component';
import { RelatorioInadimplentesComponent } from './admin/relatorios/relatorio-inadimplentes/relatorio-inadimplentes.component';


export const routes: Routes = [
    {
        path: '',
        component: PaginaInicialComponent
    },
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path: 'cadastro',
        component: CadastroComponent
    },
    {
        path: 'quem-somos',
        component: QuemSomosComponent
    },
    {
        path: 'planos',
        component: PlanosComponent
    },
    {
        path: 'contato',
        component: ContatoComponent
    },
    {
        path: 'horario-funcionamento',
        component: PaginaHorarioComponent
    },
    {
        path: 'home',
        component: HomeComponent,
        canActivate: [AuthGuard],
        data: { role: 'CLIENTE' }
    },
    {
        path: 'home/doacao',
        component: PaginaDoacaoComponent,
        canActivate: [AuthGuard],
        data: { role: 'CLIENTE' }
    },
    {
        path: 'home/mapa-jazigos',
        component: MapaJazigoComponent,
        canActivate: [AuthGuard],
        data: { role: 'CLIENTE' }

    },
    {
        path: 'home/lembrete-visita',
        component: LembreteVisitaComponent,
        canActivate: [AuthGuard],
        data: { role: 'CLIENTE' }
    },
    {
        path: 'home/perfil',
        component: ExibirPerfilComponent,
        canActivate: [AuthGuard],
        data: { role: 'CLIENTE' }
    },
    {
        path: 'home/perfil/editar',
        component: EditarPerfilComponent,
        canActivate: [AuthGuard],
        data: { role: 'CLIENTE' }
    },
    {
        path: 'home/perfil/editar-senha',
        component: EditarSenhaComponent,
        canActivate: [AuthGuard],
        data: { role: 'CLIENTE' }
    },
    {
        path: 'home/reuniao',
        component: AgendarReuniaoComponent,
        canActivate: [AuthGuard],
        data: { role: 'CLIENTE' }
    },
    {
        path: 'admin/home',
        component: PainelAdminComponent,
        canActivate: [AuthGuard],
        data: { role: 'ADMIN' }
    },
    {
        path: 'admin/home/alterar-horario',
        component: AlterarHorarioFuncionamentoComponent,
        canActivate: [AuthGuard],
        data: { role: 'ADMIN' }
    },
    {
        path: 'admin/home/mapa-jazigos',
        component: MapaJazigoComponent,
        canActivate: [AuthGuard],
        data: { role: 'ADMIN' }
    },
    {
        path: 'admin/home/reunioes',
        component: VisualizarReunioesComponent,
        canActivate: [AuthGuard],
        data: { role: 'ADMIN' }
    },
    {
        path: 'admin/home/relatorios',
        component: PainelRelatoriosComponent,
        canActivate: [AuthGuard],
        data: { role: 'ADMIN' }
    },

    {
        path: 'admin/home/relatorios/ocupacao',
        component: RelatorioOcupacaoComponent,
        canActivate: [AuthGuard],
        data: { role: 'ADMIN' }
    },
    {
        path: 'admin/home/relatorios/exumacoes',
        component: RelatorioExumacoesComponent,
        canActivate: [AuthGuard],
        data: { role: 'ADMIN' }
    },
    {
        path: 'admin/home/relatorios/enterros',
        component: RelatorioEnterrosComponent,
        canActivate: [AuthGuard],
        data: { role: 'ADMIN' }
    },
    {
        path: 'admin/home/relatorios/inadimplentes',
        component: RelatorioInadimplentesComponent,
        canActivate: [AuthGuard],
        data: { role: 'ADMIN' }
    },



    {
        path: '**',
        redirectTo: '',
        pathMatch: 'full'
    }

];
