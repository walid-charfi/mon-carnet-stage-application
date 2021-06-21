import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'stage-radiologie',
        data: { pageTitle: 'StageRadiologies' },
        loadChildren: () => import('./stage-radiologie/stage-radiologie.module').then(m => m.StageRadiologieModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class EntityRoutingModule {}
