import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { UserRouteAccessService } from 'app/core/auth/user-route-access.service';
import { StageRadiologieComponent } from '../list/stage-radiologie.component';
import { StageRadiologieDetailComponent } from '../detail/stage-radiologie-detail.component';
import { StageRadiologieUpdateComponent } from '../update/stage-radiologie-update.component';
import { StageRadiologieRoutingResolveService } from './stage-radiologie-routing-resolve.service';

const stageRadiologieRoute: Routes = [
  {
    path: '',
    component: StageRadiologieComponent,
    data: {
      defaultSort: 'id,asc',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: StageRadiologieDetailComponent,
    resolve: {
      stageRadiologie: StageRadiologieRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: StageRadiologieUpdateComponent,
    resolve: {
      stageRadiologie: StageRadiologieRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: StageRadiologieUpdateComponent,
    resolve: {
      stageRadiologie: StageRadiologieRoutingResolveService,
    },
    canActivate: [UserRouteAccessService],
  },
];

@NgModule({
  imports: [RouterModule.forChild(stageRadiologieRoute)],
  exports: [RouterModule],
})
export class StageRadiologieRoutingModule {}
