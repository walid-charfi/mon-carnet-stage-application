import * as dayjs from 'dayjs';
import { IUser } from 'app/entities/user/user.model';
import { AnneeEtude } from 'app/entities/enumerations/annee-etude.model';
import { Hopital } from 'app/entities/enumerations/hopital.model';

export interface IStageRadiologie {
  id?: number;
  anneeEtude?: AnneeEtude;
  dateDebut?: dayjs.Dayjs | null;
  dateFin?: dayjs.Dayjs | null;
  hopital?: Hopital;
  chefService?: string | null;
  semestre?: string | null;
  groupe?: string | null;
  specialite?: string | null;
  objectif1?: string | null;
  objectif1Enseigne?: string | null;
  isObjectif1Evalue?: boolean | null;
  noteObjectif1?: number | null;
  evaluationObjectifParObjectif?: number | null;
  evaluationRedactionObservation?: number | null;
  evaluationElaborationResume?: number | null;
  evaluationAttitudeAssiduite?: number | null;
  evaludationValidationGarde?: number | null;
  evaluationPeriodique?: number | null;
  eCOSM?: number | null;
  total?: number | null;
  isAbsenceSup10pc?: boolean | null;
  isValide?: boolean | null;
  dureeStageComplEnSemaine?: number | null;
  dateDebutStageCompl?: dayjs.Dayjs | null;
  dateFinStageCompl?: dayjs.Dayjs | null;
  isStageComplValide?: boolean | null;
  user?: IUser | null;
}

export class StageRadiologie implements IStageRadiologie {
  constructor(
    public id?: number,
    public anneeEtude?: AnneeEtude,
    public dateDebut?: dayjs.Dayjs | null,
    public dateFin?: dayjs.Dayjs | null,
    public hopital?: Hopital,
    public chefService?: string | null,
    public semestre?: string | null,
    public groupe?: string | null,
    public specialite?: string | null,
    public objectif1?: string | null,
    public objectif1Enseigne?: string | null,
    public isObjectif1Evalue?: boolean | null,
    public noteObjectif1?: number | null,
    public evaluationObjectifParObjectif?: number | null,
    public evaluationRedactionObservation?: number | null,
    public evaluationElaborationResume?: number | null,
    public evaluationAttitudeAssiduite?: number | null,
    public evaludationValidationGarde?: number | null,
    public evaluationPeriodique?: number | null,
    public eCOSM?: number | null,
    public total?: number | null,
    public isAbsenceSup10pc?: boolean | null,
    public isValide?: boolean | null,
    public dureeStageComplEnSemaine?: number | null,
    public dateDebutStageCompl?: dayjs.Dayjs | null,
    public dateFinStageCompl?: dayjs.Dayjs | null,
    public isStageComplValide?: boolean | null,
    public user?: IUser | null
  ) {
    this.isObjectif1Evalue = this.isObjectif1Evalue ?? false;
    this.isAbsenceSup10pc = this.isAbsenceSup10pc ?? false;
    this.isValide = this.isValide ?? false;
    this.isStageComplValide = this.isStageComplValide ?? false;
  }
}

export function getStageRadiologieIdentifier(stageRadiologie: IStageRadiologie): number | undefined {
  return stageRadiologie.id;
}
