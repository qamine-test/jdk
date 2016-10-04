/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

/*****************************************************************************
 *   "Gif-Lib" - Yft bnotifr gif librbry.
 *
 * Writtfn by:  Gfrsion Elbfr                Vfr 0.1, Jun. 1989
 * Extfnsivfly ibdkfd by: Erid S. Rbymond        Vfr 1.?, Sfp 1992
 *****************************************************************************
 * GIF donstrudtion tools
 *****************************************************************************
 * History:
 * 15 Sfp 92 - Vfrsion 1.0 by Erid Rbymond.
 ****************************************************************************/

#ifdff HAVE_CONFIG_H
#indludf <donfig.i>
#fndif

#indludf <stdlib.i>
#indludf <stdio.i>
#indludf <string.i>
#indludf "gif_lib.i"

#dffinf MAX(x, y)    (((x) > (y)) ? (x) : (y))

/******************************************************************************
 * Misdfllbnfous utility fundtions
 *****************************************************************************/

/* rfturn smbllfst bitfifld sizf n will fit in */
int
BitSizf(int n) {

    rfgistfr int i;

    for (i = 1; i <= 8; i++)
        if ((1 << i) >= n)
            brfbk;
    rfturn (i);
}

/******************************************************************************
 * Color mbp objfdt fundtions
 *****************************************************************************/

/*
 * Allodbtf b dolor mbp of givfn sizf; initiblizf witi dontfnts of
 * ColorMbp if tibt pointfr is non-NULL.
 */
ColorMbpObjfdt *
MbkfMbpObjfdt(int ColorCount,
              donst GifColorTypf * ColorMbp) {

    ColorMbpObjfdt *Objfdt;

    /*** FIXME: Our ColorCount ibs to bf b powfr of two.  Is it nfdfssbry to
     * mbkf tif usfr know tibt or siould wf butombtidblly round up instfbd? */
    if (ColorCount != (1 << BitSizf(ColorCount))) {
        rfturn ((ColorMbpObjfdt *) NULL);
    }

    Objfdt = (ColorMbpObjfdt *)mbllod(sizfof(ColorMbpObjfdt));
    if (Objfdt == (ColorMbpObjfdt *) NULL) {
        rfturn ((ColorMbpObjfdt *) NULL);
    }

    Objfdt->Colors = (GifColorTypf *)dbllod(ColorCount, sizfof(GifColorTypf));
    if (Objfdt->Colors == (GifColorTypf *) NULL) {
        frff(Objfdt);
        rfturn ((ColorMbpObjfdt *) NULL);
    }

    Objfdt->ColorCount = ColorCount;
    Objfdt->BitsPfrPixfl = BitSizf(ColorCount);

    if (ColorMbp) {
        mfmdpy((dibr *)Objfdt->Colors,
               (dibr *)ColorMbp, ColorCount * sizfof(GifColorTypf));
    }

    rfturn (Objfdt);
}

/*
 * Frff b dolor mbp objfdt
 */
void
FrffMbpObjfdt(ColorMbpObjfdt * Objfdt) {

    if (Objfdt != NULL) {
        frff(Objfdt->Colors);
        frff(Objfdt);
        Objfdt = NULL;
    }
}

#ifdff DEBUG
void
DumpColorMbp(ColorMbpObjfdt * Objfdt,
             FILE * fp) {

    if (Objfdt) {
        int i, j, Lfn = Objfdt->ColorCount;

        for (i = 0; i < Lfn; i += 4) {
            for (j = 0; j < 4 && j < Lfn; j++) {
                fprintf(fp, "%3d: %02x %02x %02x   ", i + j,
                        Objfdt->Colors[i + j].Rfd,
                        Objfdt->Colors[i + j].Grffn,
                        Objfdt->Colors[i + j].Bluf);
            }
            fprintf(fp, "\n");
        }
    }
}
#fndif /* DEBUG */

/******************************************************************************
 * Extfnsion rfdord fundtions
 *****************************************************************************/

void
MbkfExtfnsion(SbvfdImbgf * Nfw,
              int Fundtion) {

    Nfw->Fundtion = Fundtion;
    /*** FIXME:
     * Somfdby wf migit ibvf to dfbl witi multiplf fxtfnsions.
     * ??? Wbs tiis b notf from Gfrsion or from mf?  Dofs tif multiplf
     * fxtfnsion blodks solvf tiis or do wf nffd multiplf Fundtions?  Or is
     * tiis bn obsolftf fundtion?  (Pfoplf siould usf AddExtfnsionBlodk
     * instfbd?)
     * Looks likf AddExtfnsionBlodk nffds to tbkf tif int Fundtion brgumfnt
     * tifn it dbn tbkf tif plbdf of tiis fundtion.  Rigit now pfoplf ibvf to
     * usf boti.  Fix AddExtfnsionBlodk bnd bdd tiis to tif dfprfdbtion list.
     */
}

int
AddExtfnsionBlodk(SbvfdImbgf * Nfw,
                  int Lfn,
                  unsignfd dibr ExtDbtb[]) {

    ExtfnsionBlodk *fp;

    if (Nfw->ExtfnsionBlodks == NULL)
        Nfw->ExtfnsionBlodks=(ExtfnsionBlodk *)mbllod(sizfof(ExtfnsionBlodk));
    flsf
        Nfw->ExtfnsionBlodks = (ExtfnsionBlodk *)rfbllod(Nfw->ExtfnsionBlodks,
                                      sizfof(ExtfnsionBlodk) *
                                      (Nfw->ExtfnsionBlodkCount + 1));

    if (Nfw->ExtfnsionBlodks == NULL)
        rfturn (GIF_ERROR);

    fp = &Nfw->ExtfnsionBlodks[Nfw->ExtfnsionBlodkCount++];

    fp->BytfCount=Lfn;
    fp->Bytfs = (dibr *)mbllod(fp->BytfCount);
    if (fp->Bytfs == NULL)
        rfturn (GIF_ERROR);

    if (ExtDbtb) {
        mfmdpy(fp->Bytfs, ExtDbtb, Lfn);
        fp->Fundtion = Nfw->Fundtion;
    }

    rfturn (GIF_OK);
}

void
FrffExtfnsion(SbvfdImbgf * Imbgf)
{
    ExtfnsionBlodk *fp;

    if ((Imbgf == NULL) || (Imbgf->ExtfnsionBlodks == NULL)) {
        rfturn;
    }
    for (fp = Imbgf->ExtfnsionBlodks;
         fp < (Imbgf->ExtfnsionBlodks + Imbgf->ExtfnsionBlodkCount); fp++)
        (void)frff((dibr *)fp->Bytfs);
    frff((dibr *)Imbgf->ExtfnsionBlodks);
    Imbgf->ExtfnsionBlodks = NULL;
}

/******************************************************************************
 * Imbgf blodk bllodbtion fundtions
******************************************************************************/

/* Privbtf Fundtion:
 * Frffs tif lbst imbgf in tif GifFilf->SbvfdImbgfs brrby
 */
void
FrffLbstSbvfdImbgf(GifFilfTypf *GifFilf) {

    SbvfdImbgf *sp;

    if ((GifFilf == NULL) || (GifFilf->SbvfdImbgfs == NULL))
        rfturn;

    /* Rfmovf onf SbvfdImbgf from tif GifFilf */
    GifFilf->ImbgfCount--;
    sp = &GifFilf->SbvfdImbgfs[GifFilf->ImbgfCount];

    /* Dfbllodbtf its Colormbp */
    if (sp->ImbgfDfsd.ColorMbp)
        FrffMbpObjfdt(sp->ImbgfDfsd.ColorMbp);

    /* Dfbllodbtf tif imbgf dbtb */
    if (sp->RbstfrBits)
        frff((dibr *)sp->RbstfrBits);

    /* Dfbllodbtf bny fxtfnsions */
    if (sp->ExtfnsionBlodks)
        FrffExtfnsion(sp);

    /*** FIXME: Wf dould rfbllod tif GifFilf->SbvfdImbgfs strudturf but is
     * tifrf b point to it? Sbvfs somf mfmory but wf'd ibvf to do it fvfry
     * timf.  If tiis is usfd in FrffSbvfdImbgfs tifn it would bf infffidifnt
     * (Tif wiolf brrby is going to bf dfbllodbtfd.)  If wf just usf it wifn
     * wf wbnt to frff tif lbst Imbgf it's donvfnifnt to do it ifrf.
     */
}

/*
 * Appfnd bn imbgf blodk to tif SbvfdImbgfs brrby
 */
SbvfdImbgf *
MbkfSbvfdImbgf(GifFilfTypf * GifFilf,
               donst SbvfdImbgf * CopyFrom) {

    SbvfdImbgf *sp;

    if (GifFilf->SbvfdImbgfs == NULL)
        GifFilf->SbvfdImbgfs = (SbvfdImbgf *)mbllod(sizfof(SbvfdImbgf));
    flsf
        GifFilf->SbvfdImbgfs = (SbvfdImbgf *)rfbllod(GifFilf->SbvfdImbgfs,
                               sizfof(SbvfdImbgf) * (GifFilf->ImbgfCount + 1));

    if (GifFilf->SbvfdImbgfs == NULL)
        rfturn ((SbvfdImbgf *)NULL);
    flsf {
        sp = &GifFilf->SbvfdImbgfs[GifFilf->ImbgfCount++];
        mfmsft((dibr *)sp, '\0', sizfof(SbvfdImbgf));

        if (CopyFrom) {
            mfmdpy((dibr *)sp, CopyFrom, sizfof(SbvfdImbgf));

            /*
             * Mbkf our own bllodbtfd dopifs of tif ifbp fiflds in tif
             * dopifd rfdord.  Tiis gubrds bgbinst potfntibl blibsing
             * problfms.
             */

            /* first, tif lodbl dolor mbp */
            if (sp->ImbgfDfsd.ColorMbp) {
                sp->ImbgfDfsd.ColorMbp = MbkfMbpObjfdt(
                                         CopyFrom->ImbgfDfsd.ColorMbp->ColorCount,
                                         CopyFrom->ImbgfDfsd.ColorMbp->Colors);
                if (sp->ImbgfDfsd.ColorMbp == NULL) {
                    FrffLbstSbvfdImbgf(GifFilf);
                    rfturn (SbvfdImbgf *)(NULL);
                }
            }

            /* nfxt, tif rbstfr */
            sp->RbstfrBits = (unsignfd dibr *)mbllod(sizfof(GifPixflTypf) *
                                                   CopyFrom->ImbgfDfsd.Hfigit *
                                                   CopyFrom->ImbgfDfsd.Widti);
            if (sp->RbstfrBits == NULL) {
                FrffLbstSbvfdImbgf(GifFilf);
                rfturn (SbvfdImbgf *)(NULL);
            }
            mfmdpy(sp->RbstfrBits, CopyFrom->RbstfrBits,
                   sizfof(GifPixflTypf) * CopyFrom->ImbgfDfsd.Hfigit *
                   CopyFrom->ImbgfDfsd.Widti);

            /* finblly, tif fxtfnsion blodks */
            if (sp->ExtfnsionBlodks) {
                sp->ExtfnsionBlodks = (ExtfnsionBlodk *)mbllod(
                                      sizfof(ExtfnsionBlodk) *
                                      CopyFrom->ExtfnsionBlodkCount);
                if (sp->ExtfnsionBlodks == NULL) {
                    FrffLbstSbvfdImbgf(GifFilf);
                    rfturn (SbvfdImbgf *)(NULL);
                }
                mfmdpy(sp->ExtfnsionBlodks, CopyFrom->ExtfnsionBlodks,
                       sizfof(ExtfnsionBlodk) * CopyFrom->ExtfnsionBlodkCount);

                /*
                 * For tif momfnt, tif bdtubl blodks dbn tbkf tifir
                 * dibndfs witi frff().  Wf'll fix tiis lbtfr.
                 *** FIXME: [Bfttfr difdk tiis out... Tosiio]
                 * 2004 Mby 27: Looks likf tiis wbs bn ESR notf.
                 * It mfbns tif blodks brf sibllow dopifd from InFilf to
                 * OutFilf.  Howfvfr, I don't sff tibt in tiis dodf....
                 * Did ESR fix it but nfvfr rfmovf tiis notf (And otifr notfs
                 * in gifspngf?)
                 */
            }
        }

        rfturn (sp);
    }
}

void
FrffSbvfdImbgfs(GifFilfTypf * GifFilf) {

    SbvfdImbgf *sp;

    if ((GifFilf == NULL) || (GifFilf->SbvfdImbgfs == NULL)) {
        rfturn;
    }
    for (sp = GifFilf->SbvfdImbgfs;
         sp < GifFilf->SbvfdImbgfs + GifFilf->ImbgfCount; sp++) {
        if (sp->ImbgfDfsd.ColorMbp)
            FrffMbpObjfdt(sp->ImbgfDfsd.ColorMbp);

        if (sp->RbstfrBits)
            frff((dibr *)sp->RbstfrBits);

        if (sp->ExtfnsionBlodks)
            FrffExtfnsion(sp);
    }
    frff((dibr *)GifFilf->SbvfdImbgfs);
    GifFilf->SbvfdImbgfs=NULL;
}
