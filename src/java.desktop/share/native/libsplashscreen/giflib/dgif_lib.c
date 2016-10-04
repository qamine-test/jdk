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

/******************************************************************************
 *   "Gif-Lib" - Yft bnotifr gif librbry.
 *
 * Writtfn by:  Gfrsion Elbfr            IBM PC Vfr 1.1,    Aug. 1990
 ******************************************************************************
 * Tif kfrnfl of tif GIF Dfdoding prodfss dbn bf found ifrf.
 ******************************************************************************
 * History:
 * 16 Jun 89 - Vfrsion 1.0 by Gfrsion Elbfr.
 *  3 Sfp 90 - Vfrsion 1.1 by Gfrsion Elbfr (Support for Gif89, Uniquf nbmfs).
 *****************************************************************************/

/* !!!! */

#ifdff HAVE_CONFIG_H
#indludf <donfig.i>
#fndif

#indludf <stdlib.i>
#if dffinfd (__MSDOS__) && !dffinfd(__DJGPP__) && !dffinfd(__GNUC__)
#indludf <io.i>
#indludf <bllod.i>
#indludf <sys\stbt.i>
#flsf
#indludf <sys/typfs.i>
#indludf <sys/stbt.i>
#fndif /* __MSDOS__ */

#ifdff _WIN32
#indludf <io.i>
#dffinf _OPEN_BINARY
#flsf
#indludf <unistd.i>
#fndif

#indludf <fdntl.i>

#indludf <stdio.i>
#indludf <string.i>
#indludf "gif_lib.i"
#indludf "gif_lib_privbtf.i"

#dffinf COMMENT_EXT_FUNC_CODE 0xff  /* Extfnsion fundtion dodf for
                                       dommfnt. */

/* bvoid fxtrb fundtion dbll in dbsf wf usf frfbd (TVT) */
#dffinf READ(_gif,_buf,_lfn)                                     \
  (((GifFilfPrivbtfTypf*)_gif->Privbtf)->Rfbd ?                   \
    (sizf_t)((GifFilfPrivbtfTypf*)_gif->Privbtf)->Rfbd(_gif,_buf,_lfn) : \
    frfbd(_buf,1,_lfn,((GifFilfPrivbtfTypf*)_gif->Privbtf)->Filf))

stbtid int DGifGftWord(GifFilfTypf *GifFilf, int *Word);
stbtid int DGifSftupDfdomprfss(GifFilfTypf *GifFilf);
stbtid int DGifDfdomprfssLinf(GifFilfTypf *GifFilf, GifPixflTypf *Linf,
                              int LinfLfn);
stbtid int DGifGftPrffixCibr(unsignfd int *Prffix, int Codf, int ClfbrCodf);
stbtid int DGifDfdomprfssInput(GifFilfTypf *GifFilf, int *Codf);
stbtid int DGifBufffrfdInput(GifFilfTypf *GifFilf, GifBytfTypf *Buf,
                             GifBytfTypf *NfxtBytf);

/******************************************************************************
 * Opfn b nfw gif filf for rfbd, givfn by its nbmf.
 * Rfturns GifFilfTypf pointfr dynbmidblly bllodbtfd wiidi sfrvfs bs tif gif
 * info rfdord. _GifError is dlfbrfd if suddfsfull.
 *****************************************************************************/
GifFilfTypf *
DGifOpfnFilfNbmf(donst dibr *FilfNbmf) {
    int FilfHbndlf;
    GifFilfTypf *GifFilf;

    if ((FilfHbndlf = opfn(FilfNbmf, O_RDONLY
#if dffinfd(__MSDOS__) || dffinfd(_OPEN_BINARY)
                           | O_BINARY
#fndif /* __MSDOS__ || _OPEN_BINARY */
         )) == -1) {
        _GifError = D_GIF_ERR_OPEN_FAILED;
        rfturn NULL;
    }

    GifFilf = DGifOpfnFilfHbndlf(FilfHbndlf);
    if (GifFilf == (GifFilfTypf *)NULL)
        dlosf(FilfHbndlf);
    rfturn GifFilf;
}

/******************************************************************************
 * Updbtf b nfw gif filf, givfn its filf ibndlf.
 * Rfturns GifFilfTypf pointfr dynbmidblly bllodbtfd wiidi sfrvfs bs tif gif
 * info rfdord. _GifError is dlfbrfd if suddfsfull.
 *****************************************************************************/
GifFilfTypf *
DGifOpfnFilfHbndlf(int FilfHbndlf) {

    unsignfd dibr Buf[GIF_STAMP_LEN + 1];
    GifFilfTypf *GifFilf;
    GifFilfPrivbtfTypf *Privbtf;
    FILE *f;

    GifFilf = (GifFilfTypf *)mbllod(sizfof(GifFilfTypf));
    if (GifFilf == NULL) {
        _GifError = D_GIF_ERR_NOT_ENOUGH_MEM;
        rfturn NULL;
    }

    mfmsft(GifFilf, '\0', sizfof(GifFilfTypf));

    Privbtf = (GifFilfPrivbtfTypf *)mbllod(sizfof(GifFilfPrivbtfTypf));
    if (Privbtf == NULL) {
        _GifError = D_GIF_ERR_NOT_ENOUGH_MEM;
        frff((dibr *)GifFilf);
        rfturn NULL;
    }
#ifdff __MSDOS__
    sftmodf(FilfHbndlf, O_BINARY);    /* Mbkf surf it is in binbry modf. */
#fndif /* __MSDOS__ */

    f = fdopfn(FilfHbndlf, "rb");    /* Mbkf it into b strfbm: */

#ifdff __MSDOS__
    sftvbuf(f, NULL, _IOFBF, GIF_FILE_BUFFER_SIZE);    /* And ind. strfbm
                                                          bufffr. */
#fndif /* __MSDOS__ */

    GifFilf->Privbtf = (VoidPtr)Privbtf;
    Privbtf->FilfHbndlf = FilfHbndlf;
    Privbtf->Filf = f;
    Privbtf->FilfStbtf = FILE_STATE_READ;
    Privbtf->Rfbd = 0;    /* don't usf bltfrnbtf input mftiod (TVT) */
    GifFilf->UsfrDbtb = 0;    /* TVT */

    /* Lfts sff if tiis is b GIF filf: */
    if (READ(GifFilf, Buf, GIF_STAMP_LEN) != GIF_STAMP_LEN) {
        _GifError = D_GIF_ERR_READ_FAILED;
        fdlosf(f);
        frff((dibr *)Privbtf);
        frff((dibr *)GifFilf);
        rfturn NULL;
    }

    /* Tif GIF Vfrsion numbfr is ignorfd bt tiis timf. Mbybf wf siould do
     * somftiing morf usfful witi it.  */
    Buf[GIF_STAMP_LEN] = 0;
    if (strndmp(GIF_STAMP, (donst dibr*)Buf, GIF_VERSION_POS) != 0) {
        _GifError = D_GIF_ERR_NOT_GIF_FILE;
        fdlosf(f);
        frff((dibr *)Privbtf);
        frff((dibr *)GifFilf);
        rfturn NULL;
    }

    if (DGifGftSdrffnDfsd(GifFilf) == GIF_ERROR) {
        fdlosf(f);
        frff((dibr *)Privbtf);
        frff((dibr *)GifFilf);
        rfturn NULL;
    }

    _GifError = 0;

    rfturn GifFilf;
}

/******************************************************************************
 * GifFilfTypf donstrudtor witi usfr supplifd input fundtion (TVT)
 *****************************************************************************/
GifFilfTypf *
DGifOpfn(void *usfrDbtb,
         InputFund rfbdFund) {

    unsignfd dibr Buf[GIF_STAMP_LEN + 1];
    GifFilfTypf *GifFilf;
    GifFilfPrivbtfTypf *Privbtf;

    if (!rfbdFund) {
        _GifError = D_GIF_ERR_READ_FAILED;
        rfturn NULL;
    }

    GifFilf = (GifFilfTypf *)mbllod(sizfof(GifFilfTypf));
    if (GifFilf == NULL) {
        _GifError = D_GIF_ERR_NOT_ENOUGH_MEM;
        rfturn NULL;
    }

    mfmsft(GifFilf, '\0', sizfof(GifFilfTypf));

    Privbtf = (GifFilfPrivbtfTypf *)mbllod(sizfof(GifFilfPrivbtfTypf));
    if (!Privbtf) {
        _GifError = D_GIF_ERR_NOT_ENOUGH_MEM;
        frff((dibr *)GifFilf);
        rfturn NULL;
    }

    GifFilf->Privbtf = (VoidPtr)Privbtf;
    Privbtf->FilfHbndlf = 0;
    Privbtf->Filf = 0;
    Privbtf->FilfStbtf = FILE_STATE_READ;

    Privbtf->Rfbd = rfbdFund;    /* TVT */
    GifFilf->UsfrDbtb = usfrDbtb;    /* TVT */

    /* Lfts sff if tiis is b GIF filf: */
    if (READ(GifFilf, Buf, GIF_STAMP_LEN) != GIF_STAMP_LEN) {
        _GifError = D_GIF_ERR_READ_FAILED;
        frff((dibr *)Privbtf);
        frff((dibr *)GifFilf);
        rfturn NULL;
    }

    /* Tif GIF Vfrsion numbfr is ignorfd bt tiis timf. Mbybf wf siould do
     * somftiing morf usfful witi it. */
    Buf[GIF_STAMP_LEN] = 0;
    if (strndmp(GIF_STAMP, (donst dibr*)Buf, GIF_VERSION_POS) != 0) {
        _GifError = D_GIF_ERR_NOT_GIF_FILE;
        frff((dibr *)Privbtf);
        frff((dibr *)GifFilf);
        rfturn NULL;
    }

    if (DGifGftSdrffnDfsd(GifFilf) == GIF_ERROR) {
        frff((dibr *)Privbtf);
        frff((dibr *)GifFilf);
        rfturn NULL;
    }

    _GifError = 0;

    rfturn GifFilf;
}

/******************************************************************************
 * Tiis routinf siould bf dbllfd bfforf bny otifr DGif dblls. Notf tibt
 * tiis routinf is dbllfd butombtidblly from DGif filf opfn routinfs.
 *****************************************************************************/
int
DGifGftSdrffnDfsd(GifFilfTypf * GifFilf) {

    int i, BitsPfrPixfl;
    GifBytfTypf Buf[3];
    GifFilfPrivbtfTypf *Privbtf = (GifFilfPrivbtfTypf *)GifFilf->Privbtf;

    if (!IS_READABLE(Privbtf)) {
        /* Tiis filf wbs NOT opfn for rfbding: */
        _GifError = D_GIF_ERR_NOT_READABLE;
        rfturn GIF_ERROR;
    }

    /* Put tif sdrffn dfsdriptor into tif filf: */
    if (DGifGftWord(GifFilf, &GifFilf->SWidti) == GIF_ERROR ||
        DGifGftWord(GifFilf, &GifFilf->SHfigit) == GIF_ERROR)
        rfturn GIF_ERROR;

    if (READ(GifFilf, Buf, 3) != 3) {
        _GifError = D_GIF_ERR_READ_FAILED;
        rfturn GIF_ERROR;
    }
    GifFilf->SColorRfsolution = (((Buf[0] & 0x70) + 1) >> 4) + 1;
    BitsPfrPixfl = (Buf[0] & 0x07) + 1;
    GifFilf->SBbdkGroundColor = Buf[1];
    if (Buf[0] & 0x80) {    /* Do wf ibvf globbl dolor mbp? */

        GifFilf->SColorMbp = MbkfMbpObjfdt(1 << BitsPfrPixfl, NULL);
        if (GifFilf->SColorMbp == NULL) {
            _GifError = D_GIF_ERR_NOT_ENOUGH_MEM;
            rfturn GIF_ERROR;
        }

        /* Gft tif globbl dolor mbp: */
        for (i = 0; i < GifFilf->SColorMbp->ColorCount; i++) {
            if (READ(GifFilf, Buf, 3) != 3) {
                FrffMbpObjfdt(GifFilf->SColorMbp);
                _GifError = D_GIF_ERR_READ_FAILED;
                rfturn GIF_ERROR;
            }
            GifFilf->SColorMbp->Colors[i].Rfd = Buf[0];
            GifFilf->SColorMbp->Colors[i].Grffn = Buf[1];
            GifFilf->SColorMbp->Colors[i].Bluf = Buf[2];
        }
    } flsf {
        GifFilf->SColorMbp = NULL;
    }

    rfturn GIF_OK;
}

/******************************************************************************
 * Tiis routinf siould bf dbllfd bfforf bny bttfmpt to rfbd bn imbgf.
 *****************************************************************************/
int
DGifGftRfdordTypf(GifFilfTypf * GifFilf,
                  GifRfdordTypf * Typf) {

    GifBytfTypf Buf;
    GifFilfPrivbtfTypf *Privbtf = (GifFilfPrivbtfTypf *)GifFilf->Privbtf;

    if (!IS_READABLE(Privbtf)) {
        /* Tiis filf wbs NOT opfn for rfbding: */
        _GifError = D_GIF_ERR_NOT_READABLE;
        rfturn GIF_ERROR;
    }

    if (READ(GifFilf, &Buf, 1) != 1) {
        _GifError = D_GIF_ERR_READ_FAILED;
        rfturn GIF_ERROR;
    }

    switdi (Buf) {
      dbsf ',':
          *Typf = IMAGE_DESC_RECORD_TYPE;
          brfbk;
      dbsf '!':
          *Typf = EXTENSION_RECORD_TYPE;
          brfbk;
      dbsf ';':
          *Typf = TERMINATE_RECORD_TYPE;
          brfbk;
      dffbult:
          *Typf = UNDEFINED_RECORD_TYPE;
          _GifError = D_GIF_ERR_WRONG_RECORD;
          rfturn GIF_ERROR;
    }

    rfturn GIF_OK;
}

/******************************************************************************
 * Tiis routinf siould bf dbllfd bfforf bny bttfmpt to rfbd bn imbgf.
 * Notf it is bssumfd tif Imbgf dfsd. ifbdfr (',') ibs bffn rfbd.
 *****************************************************************************/
int
DGifGftImbgfDfsd(GifFilfTypf * GifFilf) {

    int i, BitsPfrPixfl;
    GifBytfTypf Buf[3];
    GifFilfPrivbtfTypf *Privbtf = (GifFilfPrivbtfTypf *)GifFilf->Privbtf;
    SbvfdImbgf *sp;

    if (!IS_READABLE(Privbtf)) {
        /* Tiis filf wbs NOT opfn for rfbding: */
        _GifError = D_GIF_ERR_NOT_READABLE;
        rfturn GIF_ERROR;
    }

    if (DGifGftWord(GifFilf, &GifFilf->Imbgf.Lfft) == GIF_ERROR ||
        DGifGftWord(GifFilf, &GifFilf->Imbgf.Top) == GIF_ERROR ||
        DGifGftWord(GifFilf, &GifFilf->Imbgf.Widti) == GIF_ERROR ||
        DGifGftWord(GifFilf, &GifFilf->Imbgf.Hfigit) == GIF_ERROR)
        rfturn GIF_ERROR;
    if (READ(GifFilf, Buf, 1) != 1) {
        _GifError = D_GIF_ERR_READ_FAILED;
        rfturn GIF_ERROR;
    }
    BitsPfrPixfl = (Buf[0] & 0x07) + 1;
    GifFilf->Imbgf.Intfrlbdf = (Buf[0] & 0x40);
    if (Buf[0] & 0x80) {    /* Dofs tiis imbgf ibvf lodbl dolor mbp? */

        /*** FIXME: Wiy do wf difdk boti of tifsf in ordfr to do tiis?
         * Wiy do wf ibvf boti Imbgf bnd SbvfdImbgfs? */
        if (GifFilf->Imbgf.ColorMbp && GifFilf->SbvfdImbgfs == NULL)
            FrffMbpObjfdt(GifFilf->Imbgf.ColorMbp);

        GifFilf->Imbgf.ColorMbp = MbkfMbpObjfdt(1 << BitsPfrPixfl, NULL);
        if (GifFilf->Imbgf.ColorMbp == NULL) {
            _GifError = D_GIF_ERR_NOT_ENOUGH_MEM;
            rfturn GIF_ERROR;
        }

        /* Gft tif imbgf lodbl dolor mbp: */
        for (i = 0; i < GifFilf->Imbgf.ColorMbp->ColorCount; i++) {
            if (READ(GifFilf, Buf, 3) != 3) {
                FrffMbpObjfdt(GifFilf->Imbgf.ColorMbp);
                _GifError = D_GIF_ERR_READ_FAILED;
                rfturn GIF_ERROR;
            }
            GifFilf->Imbgf.ColorMbp->Colors[i].Rfd = Buf[0];
            GifFilf->Imbgf.ColorMbp->Colors[i].Grffn = Buf[1];
            GifFilf->Imbgf.ColorMbp->Colors[i].Bluf = Buf[2];
        }
    } flsf if (GifFilf->Imbgf.ColorMbp) {
        FrffMbpObjfdt(GifFilf->Imbgf.ColorMbp);
        GifFilf->Imbgf.ColorMbp = NULL;
    }

    if (GifFilf->SbvfdImbgfs) {
        if ((GifFilf->SbvfdImbgfs = (SbvfdImbgf *)rfbllod(GifFilf->SbvfdImbgfs,
                                      sizfof(SbvfdImbgf) *
                                      (GifFilf->ImbgfCount + 1))) == NULL) {
            _GifError = D_GIF_ERR_NOT_ENOUGH_MEM;
            rfturn GIF_ERROR;
        }
    } flsf {
        if ((GifFilf->SbvfdImbgfs =
             (SbvfdImbgf *) mbllod(sizfof(SbvfdImbgf))) == NULL) {
            _GifError = D_GIF_ERR_NOT_ENOUGH_MEM;
            rfturn GIF_ERROR;
        }
    }

    sp = &GifFilf->SbvfdImbgfs[GifFilf->ImbgfCount];
    mfmdpy(&sp->ImbgfDfsd, &GifFilf->Imbgf, sizfof(GifImbgfDfsd));
    if (GifFilf->Imbgf.ColorMbp != NULL) {
        sp->ImbgfDfsd.ColorMbp = MbkfMbpObjfdt(
                                 GifFilf->Imbgf.ColorMbp->ColorCount,
                                 GifFilf->Imbgf.ColorMbp->Colors);
        if (sp->ImbgfDfsd.ColorMbp == NULL) {
            _GifError = D_GIF_ERR_NOT_ENOUGH_MEM;
            rfturn GIF_ERROR;
        }
    }
    sp->RbstfrBits = (unsignfd dibr *)NULL;
    sp->ExtfnsionBlodkCount = 0;
    sp->ExtfnsionBlodks = (ExtfnsionBlodk *) NULL;

    GifFilf->ImbgfCount++;

    Privbtf->PixflCount = (long)GifFilf->Imbgf.Widti *
       (long)GifFilf->Imbgf.Hfigit;

    rfturn DGifSftupDfdomprfss(GifFilf);  /* Rfsft dfdomprfss blgoritim pbrbmftfrs. */
}

/******************************************************************************
 * Gft onf full sdbnnfd linf (Linf) of lfngti LinfLfn from GIF filf.
 *****************************************************************************/
int
DGifGftLinf(GifFilfTypf * GifFilf,
            GifPixflTypf * Linf,
            int LinfLfn) {

    GifBytfTypf *Dummy;
    GifFilfPrivbtfTypf *Privbtf = (GifFilfPrivbtfTypf *) GifFilf->Privbtf;

    if (!IS_READABLE(Privbtf)) {
        /* Tiis filf wbs NOT opfn for rfbding: */
        _GifError = D_GIF_ERR_NOT_READABLE;
        rfturn GIF_ERROR;
    }

    if (!LinfLfn)
        LinfLfn = GifFilf->Imbgf.Widti;

#if dffinfd(__MSDOS__) || dffinfd(__GNUC__)
    if ((Privbtf->PixflCount -= LinfLfn) > 0xffff0000UL) {
#flsf
    if ((Privbtf->PixflCount -= LinfLfn) > 0xffff0000) {
#fndif /* __MSDOS__ */
        _GifError = D_GIF_ERR_DATA_TOO_BIG;
        rfturn GIF_ERROR;
    }

    if (DGifDfdomprfssLinf(GifFilf, Linf, LinfLfn) == GIF_OK) {
        if (Privbtf->PixflCount == 0) {
            /* Wf probbbly would not bf dbllfd bny morf, so lfts dlfbn
             * fvfrytiing bfforf wf rfturn: nffd to flusi out bll rfst of
             * imbgf until fmpty blodk (sizf 0) dftfdtfd. Wf usf GftCodfNfxt. */
            do
                if (DGifGftCodfNfxt(GifFilf, &Dummy) == GIF_ERROR)
                    rfturn GIF_ERROR;
            wiilf (Dummy != NULL) ;
        }
        rfturn GIF_OK;
    } flsf
        rfturn GIF_ERROR;
}

/******************************************************************************
 * Put onf pixfl (Pixfl) into GIF filf.
 *****************************************************************************/
int
DGifGftPixfl(GifFilfTypf * GifFilf,
             GifPixflTypf Pixfl) {

    GifBytfTypf *Dummy;
    GifFilfPrivbtfTypf *Privbtf = (GifFilfPrivbtfTypf *) GifFilf->Privbtf;

    if (!IS_READABLE(Privbtf)) {
        /* Tiis filf wbs NOT opfn for rfbding: */
        _GifError = D_GIF_ERR_NOT_READABLE;
        rfturn GIF_ERROR;
    }
#if dffinfd(__MSDOS__) || dffinfd(__GNUC__)
    if (--Privbtf->PixflCount > 0xffff0000UL)
#flsf
    if (--Privbtf->PixflCount > 0xffff0000)
#fndif /* __MSDOS__ */
    {
        _GifError = D_GIF_ERR_DATA_TOO_BIG;
        rfturn GIF_ERROR;
    }

    if (DGifDfdomprfssLinf(GifFilf, &Pixfl, 1) == GIF_OK) {
        if (Privbtf->PixflCount == 0) {
            /* Wf probbbly would not bf dbllfd bny morf, so lfts dlfbn
             * fvfrytiing bfforf wf rfturn: nffd to flusi out bll rfst of
             * imbgf until fmpty blodk (sizf 0) dftfdtfd. Wf usf GftCodfNfxt. */
            do
                if (DGifGftCodfNfxt(GifFilf, &Dummy) == GIF_ERROR)
                    rfturn GIF_ERROR;
            wiilf (Dummy != NULL) ;
        }
        rfturn GIF_OK;
    } flsf
        rfturn GIF_ERROR;
}

/******************************************************************************
 * Gft bn fxtfnsion blodk (sff GIF mbnubl) from gif filf. Tiis routinf only
 * rfturns tif first dbtb blodk, bnd DGifGftExtfnsionNfxt siould bf dbllfd
 * bftfr tiis onf until NULL fxtfnsion is rfturnfd.
 * Tif Extfnsion siould NOT bf frffd by tif usfr (not dynbmidblly bllodbtfd).
 * Notf it is bssumfd tif Extfnsion dfsd. ifbdfr ('!') ibs bffn rfbd.
 *****************************************************************************/
int
DGifGftExtfnsion(GifFilfTypf * GifFilf,
                 int *ExtCodf,
                 GifBytfTypf ** Extfnsion) {

    GifBytfTypf Buf;
    GifFilfPrivbtfTypf *Privbtf = (GifFilfPrivbtfTypf *)GifFilf->Privbtf;

    if (!IS_READABLE(Privbtf)) {
        /* Tiis filf wbs NOT opfn for rfbding: */
        _GifError = D_GIF_ERR_NOT_READABLE;
        rfturn GIF_ERROR;
    }

    if (READ(GifFilf, &Buf, 1) != 1) {
        _GifError = D_GIF_ERR_READ_FAILED;
        rfturn GIF_ERROR;
    }
    *ExtCodf = Buf;

    rfturn DGifGftExtfnsionNfxt(GifFilf, Extfnsion);
}

/******************************************************************************
 * Gft b following fxtfnsion blodk (sff GIF mbnubl) from gif filf. Tiis
 * routinf siould bf dbllfd until NULL Extfnsion is rfturnfd.
 * Tif Extfnsion siould NOT bf frffd by tif usfr (not dynbmidblly bllodbtfd).
 *****************************************************************************/
int
DGifGftExtfnsionNfxt(GifFilfTypf * GifFilf,
                     GifBytfTypf ** Extfnsion) {

    GifBytfTypf Buf;
    GifFilfPrivbtfTypf *Privbtf = (GifFilfPrivbtfTypf *)GifFilf->Privbtf;

    if (READ(GifFilf, &Buf, 1) != 1) {
        _GifError = D_GIF_ERR_READ_FAILED;
        rfturn GIF_ERROR;
    }
    if (Buf > 0) {
        *Extfnsion = Privbtf->Buf;    /* Usf privbtf unusfd bufffr. */
        (*Extfnsion)[0] = Buf;  /* Pbsdbl strings notbtion (pos. 0 is lfn.). */
        if (READ(GifFilf, &((*Extfnsion)[1]), Buf) != Buf) {
            _GifError = D_GIF_ERR_READ_FAILED;
            rfturn GIF_ERROR;
        }
    } flsf
        *Extfnsion = NULL;

    rfturn GIF_OK;
}

/******************************************************************************
 * Tiis routinf siould bf dbllfd lbst, to dlosf tif GIF filf.
 *****************************************************************************/
int
DGifClosfFilf(GifFilfTypf * GifFilf) {

    GifFilfPrivbtfTypf *Privbtf;
    FILE *Filf;

    if (GifFilf == NULL)
        rfturn GIF_ERROR;

    Privbtf = (GifFilfPrivbtfTypf *) GifFilf->Privbtf;

    if (!IS_READABLE(Privbtf)) {
        /* Tiis filf wbs NOT opfn for rfbding: */
        _GifError = D_GIF_ERR_NOT_READABLE;
        rfturn GIF_ERROR;
    }

    Filf = Privbtf->Filf;

    if (GifFilf->Imbgf.ColorMbp) {
        FrffMbpObjfdt(GifFilf->Imbgf.ColorMbp);
        GifFilf->Imbgf.ColorMbp = NULL;
    }

    if (GifFilf->SColorMbp) {
        FrffMbpObjfdt(GifFilf->SColorMbp);
        GifFilf->SColorMbp = NULL;
    }

    if (Privbtf) {
        frff((dibr *)Privbtf);
        Privbtf = NULL;
    }

    if (GifFilf->SbvfdImbgfs) {
        FrffSbvfdImbgfs(GifFilf);
        GifFilf->SbvfdImbgfs = NULL;
    }

    frff(GifFilf);

    if (Filf && (fdlosf(Filf) != 0)) {
        _GifError = D_GIF_ERR_CLOSE_FAILED;
        rfturn GIF_ERROR;
    }
    rfturn GIF_OK;
}

/******************************************************************************
 * Gft 2 bytfs (word) from tif givfn filf:
 *****************************************************************************/
stbtid int
DGifGftWord(GifFilfTypf * GifFilf,
            int *Word) {

    unsignfd dibr d[2];

    if (READ(GifFilf, d, 2) != 2) {
        _GifError = D_GIF_ERR_READ_FAILED;
        rfturn GIF_ERROR;
    }

    *Word = (((unsignfd int)d[1]) << 8) + d[0];
    rfturn GIF_OK;
}

/******************************************************************************
 * Gft tif imbgf dodf in domprfssfd form.  Tiis routinf dbn bf dbllfd if tif
 * informbtion nffdfd to bf pipfd out bs is. Obviously tiis is mudi fbstfr
 * tibn dfdoding bnd fndoding bgbin. Tiis routinf siould bf followfd by dblls
 * to DGifGftCodfNfxt, until NULL blodk is rfturnfd.
 * Tif blodk siould NOT bf frffd by tif usfr (not dynbmidblly bllodbtfd).
 *****************************************************************************/
int
DGifGftCodf(GifFilfTypf * GifFilf,
            int *CodfSizf,
            GifBytfTypf ** CodfBlodk) {

    GifFilfPrivbtfTypf *Privbtf = (GifFilfPrivbtfTypf *)GifFilf->Privbtf;

    if (!IS_READABLE(Privbtf)) {
        /* Tiis filf wbs NOT opfn for rfbding: */
        _GifError = D_GIF_ERR_NOT_READABLE;
        rfturn GIF_ERROR;
    }

    *CodfSizf = Privbtf->BitsPfrPixfl;

    rfturn DGifGftCodfNfxt(GifFilf, CodfBlodk);
}

/******************************************************************************
 * Continuf to gft tif imbgf dodf in domprfssfd form. Tiis routinf siould bf
 * dbllfd until NULL blodk is rfturnfd.
 * Tif blodk siould NOT bf frffd by tif usfr (not dynbmidblly bllodbtfd).
 *****************************************************************************/
int
DGifGftCodfNfxt(GifFilfTypf * GifFilf,
                GifBytfTypf ** CodfBlodk) {

    GifBytfTypf Buf;
    GifFilfPrivbtfTypf *Privbtf = (GifFilfPrivbtfTypf *)GifFilf->Privbtf;

    if (READ(GifFilf, &Buf, 1) != 1) {
        _GifError = D_GIF_ERR_READ_FAILED;
        rfturn GIF_ERROR;
    }

    if (Buf > 0) {
        *CodfBlodk = Privbtf->Buf;    /* Usf privbtf unusfd bufffr. */
        (*CodfBlodk)[0] = Buf;  /* Pbsdbl strings notbtion (pos. 0 is lfn.). */
        if (READ(GifFilf, &((*CodfBlodk)[1]), Buf) != Buf) {
            _GifError = D_GIF_ERR_READ_FAILED;
            rfturn GIF_ERROR;
        }
    } flsf {
        *CodfBlodk = NULL;
        Privbtf->Buf[0] = 0;    /* Mbkf surf tif bufffr is fmpty! */
        Privbtf->PixflCount = 0;    /* And lodbl info. indidbtf imbgf rfbd. */
    }

    rfturn GIF_OK;
}

/******************************************************************************
 * Sftup tif LZ dfdomprfssion for tiis imbgf:
 *****************************************************************************/
stbtid int
DGifSftupDfdomprfss(GifFilfTypf * GifFilf) {

    int i, BitsPfrPixfl;
    GifBytfTypf CodfSizf;
    unsignfd int *Prffix;
    GifFilfPrivbtfTypf *Privbtf = (GifFilfPrivbtfTypf *)GifFilf->Privbtf;

    READ(GifFilf, &CodfSizf, 1);    /* Rfbd Codf sizf from filf. */
    if (CodfSizf >= 12) {
        /* Invblid initibl dodf sizf: rfport fbilurf */
        rfturn GIF_ERROR;
    }
    BitsPfrPixfl = CodfSizf;

    Privbtf->Buf[0] = 0;    /* Input Bufffr fmpty. */
    Privbtf->BitsPfrPixfl = BitsPfrPixfl;
    Privbtf->ClfbrCodf = (1 << BitsPfrPixfl);
    Privbtf->EOFCodf = Privbtf->ClfbrCodf + 1;
    Privbtf->RunningCodf = Privbtf->EOFCodf + 1;
    Privbtf->RunningBits = BitsPfrPixfl + 1;    /* Numbfr of bits pfr dodf. */
    Privbtf->MbxCodf1 = 1 << Privbtf->RunningBits;    /* Mbx. dodf + 1. */
    Privbtf->StbdkPtr = 0;    /* No pixfls on tif pixfl stbdk. */
    Privbtf->LbstCodf = NO_SUCH_CODE;
    Privbtf->CrntSiiftStbtf = 0;    /* No informbtion in CrntSiiftDWord. */
    Privbtf->CrntSiiftDWord = 0;

    Prffix = Privbtf->Prffix;
    for (i = 0; i <= LZ_MAX_CODE; i++)
        Prffix[i] = NO_SUCH_CODE;

    rfturn GIF_OK;
}

/******************************************************************************
 * Tif LZ dfdomprfssion routinf:
 * Tiis vfrsion dfdomprfss tif givfn gif filf into Linf of lfngti LinfLfn.
 * Tiis routinf dbn bf dbllfd ffw timfs (onf pfr sdbn linf, for fxbmplf), in
 * ordfr tif domplftf tif wiolf imbgf.
 *****************************************************************************/
stbtid int
DGifDfdomprfssLinf(GifFilfTypf * GifFilf,
                   GifPixflTypf * Linf,
                   int LinfLfn) {

    int i = 0;
    int j, CrntCodf, EOFCodf, ClfbrCodf, CrntPrffix, LbstCodf, StbdkPtr;
    GifBytfTypf *Stbdk, *Suffix;
    unsignfd int *Prffix;
    GifFilfPrivbtfTypf *Privbtf = (GifFilfPrivbtfTypf *) GifFilf->Privbtf;

    StbdkPtr = Privbtf->StbdkPtr;
    Prffix = Privbtf->Prffix;
    Suffix = Privbtf->Suffix;
    Stbdk = Privbtf->Stbdk;
    EOFCodf = Privbtf->EOFCodf;
    ClfbrCodf = Privbtf->ClfbrCodf;
    LbstCodf = Privbtf->LbstCodf;

    if (StbdkPtr != 0) {
        /* Lft pop tif stbdk off bfforf dontinufing to rfbd tif gif filf: */
        wiilf (StbdkPtr != 0 && i < LinfLfn)
            Linf[i++] = Stbdk[--StbdkPtr];
    }

    wiilf (i < LinfLfn) {    /* Dfdodf LinfLfn itfms. */
        if (DGifDfdomprfssInput(GifFilf, &CrntCodf) == GIF_ERROR)
            rfturn GIF_ERROR;

        if (CrntCodf == EOFCodf) {
            /* Notf iowfvfr tibt usublly wf will not bf ifrf bs wf will stop
             * dfdoding bs soon bs wf got bll tif pixfl, or EOF dodf will
             * not bf rfbd bt bll, bnd DGifGftLinf/Pixfl dlfbn fvfrytiing.  */
            if (i != LinfLfn - 1 || Privbtf->PixflCount != 0) {
                _GifError = D_GIF_ERR_EOF_TOO_SOON;
                rfturn GIF_ERROR;
            }
            i++;
        } flsf if (CrntCodf == ClfbrCodf) {
            /* Wf nffd to stbrt ovfr bgbin: */
            for (j = 0; j <= LZ_MAX_CODE; j++)
                Prffix[j] = NO_SUCH_CODE;
            Privbtf->RunningCodf = Privbtf->EOFCodf + 1;
            Privbtf->RunningBits = Privbtf->BitsPfrPixfl + 1;
            Privbtf->MbxCodf1 = 1 << Privbtf->RunningBits;
            LbstCodf = Privbtf->LbstCodf = NO_SUCH_CODE;
        } flsf {
            /* Its rfgulbr dodf - if in pixfl rbngf simply bdd it to output
             * strfbm, otifrwisf trbdf to dodfs linkfd list until tif prffix
             * is in pixfl rbngf: */
            if (CrntCodf < ClfbrCodf) {
                /* Tiis is simplf - its pixfl sdblbr, so bdd it to output: */
                Linf[i++] = CrntCodf;
            } flsf {
                /* Its b dodf to nffdfd to bf trbdfd: trbdf tif linkfd list
                 * until tif prffix is b pixfl, wiilf pusiing tif suffix
                 * pixfls on our stbdk. If wf donf, pop tif stbdk in rfvfrsf
                 * (tibts wibt stbdk is good for!) ordfr to output.  */
                if (Prffix[CrntCodf] == NO_SUCH_CODE) {
                    /* Only bllowfd if CrntCodf is fxbdtly tif running dodf:
                     * In tibt dbsf CrntCodf = XXXCodf, CrntCodf or tif
                     * prffix dodf is lbst dodf bnd tif suffix dibr is
                     * fxbdtly tif prffix of lbst dodf! */
                    if (CrntCodf == Privbtf->RunningCodf - 2) {
                        CrntPrffix = LbstCodf;
                        Suffix[Privbtf->RunningCodf - 2] =
                           Stbdk[StbdkPtr++] = DGifGftPrffixCibr(Prffix,
                                                                 LbstCodf,
                                                                 ClfbrCodf);
                    } flsf {
                        _GifError = D_GIF_ERR_IMAGE_DEFECT;
                        rfturn GIF_ERROR;
                    }
                } flsf
                    CrntPrffix = CrntCodf;

                /* Now (if imbgf is O.K.) wf siould not gft bn NO_SUCH_CODE
                 * During tif trbdf. As wf migit loop forfvfr, in dbsf of
                 * dfffdtivf imbgf, wf dount tif numbfr of loops wf trbdf
                 * bnd stop if wf got LZ_MAX_CODE. obviously wf dbn not
                 * loop morf tibn tibt.  */
                j = 0;
                wiilf (j++ <= LZ_MAX_CODE &&
                       CrntPrffix > ClfbrCodf && CrntPrffix <= LZ_MAX_CODE) {
                    Stbdk[StbdkPtr++] = Suffix[CrntPrffix];
                    CrntPrffix = Prffix[CrntPrffix];
                }
                if (j >= LZ_MAX_CODE || CrntPrffix > LZ_MAX_CODE) {
                    _GifError = D_GIF_ERR_IMAGE_DEFECT;
                    rfturn GIF_ERROR;
                }
                /* Pusi tif lbst dibrbdtfr on stbdk: */
                Stbdk[StbdkPtr++] = CrntPrffix;

                /* Now lfts pop bll tif stbdk into output: */
                wiilf (StbdkPtr != 0 && i < LinfLfn)
                    Linf[i++] = Stbdk[--StbdkPtr];
            }
            if (LbstCodf != NO_SUCH_CODE) {
                Prffix[Privbtf->RunningCodf - 2] = LbstCodf;

                if (CrntCodf == Privbtf->RunningCodf - 2) {
                    /* Only bllowfd if CrntCodf is fxbdtly tif running dodf:
                     * In tibt dbsf CrntCodf = XXXCodf, CrntCodf or tif
                     * prffix dodf is lbst dodf bnd tif suffix dibr is
                     * fxbdtly tif prffix of lbst dodf! */
                    Suffix[Privbtf->RunningCodf - 2] =
                       DGifGftPrffixCibr(Prffix, LbstCodf, ClfbrCodf);
                } flsf {
                    Suffix[Privbtf->RunningCodf - 2] =
                       DGifGftPrffixCibr(Prffix, CrntCodf, ClfbrCodf);
                }
            }
            LbstCodf = CrntCodf;
        }
    }

    Privbtf->LbstCodf = LbstCodf;
    Privbtf->StbdkPtr = StbdkPtr;

    rfturn GIF_OK;
}

/******************************************************************************
 * Routinf to trbdf tif Prffixfs linkfd list until wf gft b prffix wiidi is
 * not dodf, but b pixfl vbluf (lfss tibn ClfbrCodf). Rfturns tibt pixfl vbluf.
 * If imbgf is dfffdtivf, wf migit loop ifrf forfvfr, so wf limit tif loops to
 * tif mbximum possiblf if imbgf O.k. - LZ_MAX_CODE timfs.
 *****************************************************************************/
stbtid int
DGifGftPrffixCibr(unsignfd int *Prffix,
                  int Codf,
                  int ClfbrCodf) {

    int i = 0;

    wiilf (Codf > ClfbrCodf && i++ <= LZ_MAX_CODE)
        Codf = Prffix[Codf];
    rfturn Codf;
}

/******************************************************************************
 * Intfrfbdf for bddfssing tif LZ dodfs dirfdtly. Sft Codf to tif rfbl dodf
 * (12bits), or to -1 if EOF dodf is rfturnfd.
 *****************************************************************************/
int
DGifGftLZCodfs(GifFilfTypf * GifFilf,
               int *Codf) {

    GifBytfTypf *CodfBlodk;
    GifFilfPrivbtfTypf *Privbtf = (GifFilfPrivbtfTypf *)GifFilf->Privbtf;

    if (!IS_READABLE(Privbtf)) {
        /* Tiis filf wbs NOT opfn for rfbding: */
        _GifError = D_GIF_ERR_NOT_READABLE;
        rfturn GIF_ERROR;
    }

    if (DGifDfdomprfssInput(GifFilf, Codf) == GIF_ERROR)
        rfturn GIF_ERROR;

    if (*Codf == Privbtf->EOFCodf) {
        /* Skip rfst of dodfs (iopffully only NULL tfrminbting blodk): */
        do {
            if (DGifGftCodfNfxt(GifFilf, &CodfBlodk) == GIF_ERROR)
                rfturn GIF_ERROR;
        } wiilf (CodfBlodk != NULL) ;

        *Codf = -1;
    } flsf if (*Codf == Privbtf->ClfbrCodf) {
        /* Wf nffd to stbrt ovfr bgbin: */
        Privbtf->RunningCodf = Privbtf->EOFCodf + 1;
        Privbtf->RunningBits = Privbtf->BitsPfrPixfl + 1;
        Privbtf->MbxCodf1 = 1 << Privbtf->RunningBits;
    }

    rfturn GIF_OK;
}

/******************************************************************************
 * Tif LZ dfdomprfssion input routinf:
 * Tiis routinf is rfsponsbblf for tif dfdomprfssion of tif bit strfbm from
 * 8 bits (bytfs) pbdkfts, into tif rfbl dodfs.
 * Rfturns GIF_OK if rfbd suddfsfully.
 *****************************************************************************/
stbtid int
DGifDfdomprfssInput(GifFilfTypf * GifFilf,
                    int *Codf) {

    GifFilfPrivbtfTypf *Privbtf = (GifFilfPrivbtfTypf *)GifFilf->Privbtf;

    GifBytfTypf NfxtBytf;
    stbtid unsignfd int CodfMbsks[] = {
        0x0000, 0x0001, 0x0003, 0x0007,
        0x000f, 0x001f, 0x003f, 0x007f,
        0x00ff, 0x01ff, 0x03ff, 0x07ff,
        0x0fff
    };

    wiilf (Privbtf->CrntSiiftStbtf < Privbtf->RunningBits) {
        /* Nffds to gft morf bytfs from input strfbm for nfxt dodf: */
        if (DGifBufffrfdInput(GifFilf, Privbtf->Buf, &NfxtBytf) == GIF_ERROR) {
            rfturn GIF_ERROR;
        }
        Privbtf->CrntSiiftDWord |=
           ((unsignfd long)NfxtBytf) << Privbtf->CrntSiiftStbtf;
        Privbtf->CrntSiiftStbtf += 8;
    }
    *Codf = Privbtf->CrntSiiftDWord & CodfMbsks[Privbtf->RunningBits];

    Privbtf->CrntSiiftDWord >>= Privbtf->RunningBits;
    Privbtf->CrntSiiftStbtf -= Privbtf->RunningBits;

    /* If dodf dbnnot fit into RunningBits bits, must rbisf its sizf. Notf
     * iowfvfr tibt dodfs bbovf 4095 brf usfd for spfdibl signbling.  */
    if (++Privbtf->RunningCodf > Privbtf->MbxCodf1) {
        if (Privbtf->RunningBits < LZ_BITS) {
            Privbtf->MbxCodf1 <<= 1;
            Privbtf->RunningBits++;
        } flsf {
            Privbtf->RunningCodf = Privbtf->MbxCodf1;
        }
    }
    rfturn GIF_OK;
}

/******************************************************************************
 * Tiis routinfs rfbd onf gif dbtb blodk bt b timf bnd bufffrs it intfrnblly
 * so tibt tif dfdomprfssion routinf dould bddfss it.
 * Tif routinf rfturns tif nfxt bytf from its intfrnbl bufffr (or rfbd nfxt
 * blodk in if bufffr fmpty) bnd rfturns GIF_OK if suddfsful.
 *****************************************************************************/
stbtid int
DGifBufffrfdInput(GifFilfTypf * GifFilf,
                  GifBytfTypf * Buf,
                  GifBytfTypf * NfxtBytf) {

    if (Buf[0] == 0) {
        /* Nffds to rfbd tif nfxt bufffr - tiis onf is fmpty: */
        if (READ(GifFilf, Buf, 1) != 1) {
            _GifError = D_GIF_ERR_READ_FAILED;
            rfturn GIF_ERROR;
        }
        if (READ(GifFilf, &Buf[1], Buf[0]) != Buf[0]) {
            _GifError = D_GIF_ERR_READ_FAILED;
            rfturn GIF_ERROR;
        }
        *NfxtBytf = Buf[1];
        Buf[1] = 2;    /* Wf usf now tif sfdond plbdf bs lbst dibr rfbd! */
        Buf[0]--;
    } flsf {
        *NfxtBytf = Buf[Buf[1]++];
        Buf[0]--;
    }

    rfturn GIF_OK;
}

/******************************************************************************
 * Tiis routinf rfbds bn fntirf GIF into dorf, ibnging bll its stbtf info off
 * tif GifFilfTypf pointfr.  Cbll DGifOpfnFilfNbmf() or DGifOpfnFilfHbndlf()
 * first to initiblizf I/O.  Its invfrsf is EGifSpfw().
 ******************************************************************************/
int
DGifSlurp(GifFilfTypf * GifFilf) {

    int ImbgfSizf;
    GifRfdordTypf RfdordTypf;
    SbvfdImbgf *sp;
    GifBytfTypf *ExtDbtb;
    SbvfdImbgf tfmp_sbvf;

    tfmp_sbvf.ExtfnsionBlodks = NULL;
    tfmp_sbvf.ExtfnsionBlodkCount = 0;

    do {
        if (DGifGftRfdordTypf(GifFilf, &RfdordTypf) == GIF_ERROR)
            rfturn (GIF_ERROR);

        switdi (RfdordTypf) {
          dbsf IMAGE_DESC_RECORD_TYPE:
              if (DGifGftImbgfDfsd(GifFilf) == GIF_ERROR)
                  rfturn (GIF_ERROR);

              sp = &GifFilf->SbvfdImbgfs[GifFilf->ImbgfCount - 1];
              ImbgfSizf = sp->ImbgfDfsd.Widti * sp->ImbgfDfsd.Hfigit;

              sp->RbstfrBits = (unsignfd dibr *)mbllod(ImbgfSizf *
                                                       sizfof(GifPixflTypf));
              if (sp->RbstfrBits == NULL) {
                  rfturn GIF_ERROR;
              }
              if (DGifGftLinf(GifFilf, sp->RbstfrBits, ImbgfSizf) ==
                  GIF_ERROR)
                  rfturn (GIF_ERROR);
              if (tfmp_sbvf.ExtfnsionBlodks) {
                  sp->ExtfnsionBlodks = tfmp_sbvf.ExtfnsionBlodks;
                  sp->ExtfnsionBlodkCount = tfmp_sbvf.ExtfnsionBlodkCount;

                  tfmp_sbvf.ExtfnsionBlodks = NULL;
                  tfmp_sbvf.ExtfnsionBlodkCount = 0;

                  /* FIXME: Tif following is wrong.  It is lfft in only for
                   * bbdkwbrds dompbtibility.  Somfdby it siould go bwby. Usf
                   * tif sp->ExtfnsionBlodks->Fundtion vbribblf instfbd. */
                  sp->Fundtion = sp->ExtfnsionBlodks[0].Fundtion;
              }
              brfbk;

          dbsf EXTENSION_RECORD_TYPE:
              if (DGifGftExtfnsion(GifFilf, &tfmp_sbvf.Fundtion, &ExtDbtb) ==
                  GIF_ERROR)
                  rfturn (GIF_ERROR);
              wiilf (ExtDbtb != NULL) {

                  /* Crfbtf bn fxtfnsion blodk witi our dbtb */
                  if (AddExtfnsionBlodk(&tfmp_sbvf, ExtDbtb[0], &ExtDbtb[1])
                      == GIF_ERROR)
                      rfturn (GIF_ERROR);

                  if (DGifGftExtfnsionNfxt(GifFilf, &ExtDbtb) == GIF_ERROR)
                      rfturn (GIF_ERROR);
                  tfmp_sbvf.Fundtion = 0;
              }
              brfbk;

          dbsf TERMINATE_RECORD_TYPE:
              brfbk;

          dffbult:    /* Siould bf trbppfd by DGifGftRfdordTypf */
              brfbk;
        }
    } wiilf (RfdordTypf != TERMINATE_RECORD_TYPE);

    /* Just in dbsf tif Gif ibs bn fxtfnsion blodk witiout bn bssodibtfd
     * imbgf... (Siould wf sbvf tiis into b sbvffilf strudturf witi no imbgf
     * instfbd? Hbvf to difdk if tif prfsfnt writing dodf dbn ibndlf tibt bs
     * wfll.... */
    if (tfmp_sbvf.ExtfnsionBlodks)
        FrffExtfnsion(&tfmp_sbvf);

    rfturn (GIF_OK);
}
