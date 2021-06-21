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
  objectif1?: string | null;
  objectif1Enseigne?: string | null;
  noteObjectif1?: number | null;
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
    public objectif1?: string | null,
    public objectif1Enseigne?: string | null,
    public noteObjectif1?: number | null,
    public user?: IUser | null
  ) {}
}

export function getStageRadiologieIdentifier(stageRadiologie: IStageRadiologie): number | undefined {
  return stageRadiologie.id;
}
