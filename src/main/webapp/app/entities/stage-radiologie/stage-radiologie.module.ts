import { NgModule } from '@angular/core';

import { SharedModule } from 'app/shared/shared.module';
import { StageRadiologieComponent } from './list/stage-radiologie.component';
import { StageRadiologieDetailComponent } from './detail/stage-radiologie-detail.component';
import { StageRadiologieUpdateComponent } from './update/stage-radiologie-update.component';
import { StageRadiologieDeleteDialogComponent } from './delete/stage-radiologie-delete-dialog.component';
import { StageRadiologieRoutingModule } from './route/stage-radiologie-routing.module';

@NgModule({
  imports: [SharedModule, StageRadiologieRoutingModule],
  declarations: [
    StageRadiologieComponent,
    StageRadiologieDetailComponent,
    StageRadiologieUpdateComponent,
    StageRadiologieDeleteDialogComponent,
  ],
  entryComponents: [StageRadiologieDeleteDialogComponent],
})
export class StageRadiologieModule {}
