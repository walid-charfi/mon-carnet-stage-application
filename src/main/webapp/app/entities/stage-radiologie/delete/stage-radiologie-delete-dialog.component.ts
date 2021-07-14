import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';

import { IStageRadiologie } from '../stage-radiologie.model';
import { StageRadiologieService } from '../service/stage-radiologie.service';

@Component({
  templateUrl: './stage-radiologie-delete-dialog.component.html',
})
export class StageRadiologieDeleteDialogComponent {
  stageRadiologie?: IStageRadiologie;

  constructor(protected stageRadiologieService: StageRadiologieService, public activeModal: NgbActiveModal) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.stageRadiologieService.delete(id).subscribe(() => {
      this.activeModal.close('deleted');
    });
  }
}
