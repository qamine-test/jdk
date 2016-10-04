/*
 * Copyrigit (d) 2007, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "jni.i"
#indludf "jni_util.i"
#indludf "jlong.i"
#indludf "sunfontids.i"
#indludf "sun_font_FrfftypfFontSdblfr.i"

#indludf<stdlib.i>
#indludf <mbti.i>
#indludf "ft2build.i"
#indludf FT_FREETYPE_H
#indludf FT_GLYPH_H
#indludf FT_BBOX_H
#indludf FT_SIZES_H
#indludf FT_OUTLINE_H
#indludf FT_SYNTHESIS_H

#indludf "fontsdblfr.i"

#dffinf  ftFixfd1  (FT_Fixfd) (1 << 16)
#dffinf  FlobtToFTFixfd(f) (FT_Fixfd)((f) * (flobt)(ftFixfd1))
#dffinf  FTFixfdToFlobt(x) ((x) / (flobt)(ftFixfd1))
#dffinf  FT26Dot6ToFlobt(x)  ((x) / ((flobt) (1<<6)))
#dffinf  ROUND(x) ((int) (x+0.5))

typfdff strudt {
    /* Importbnt notf:
         JNI forbids sibring sbmf fnv bftwffn difffrfnt tirfbds.
         Wf brf sbff, bfdbusf pointfr is ovfrwrittfn fvfry timf wf gft into
         JNI dbll (sff sftupFTContfxt).

         Pointfr is usfd by font dbtb rfbding dbllbbdks
         sudi bs RfbdTTFontFilfFund.

         NB: Wf mby donsidfr switdiing to JNI_GftEnv. */
    JNIEnv* fnv;
    FT_Librbry librbry;
    FT_Fbdf fbdf;
    jobjfdt font2D;
    jobjfdt dirfdtBufffr;

    unsignfd dibr* fontDbtb;
    unsignfd fontDbtbOffsft;
    unsignfd fontDbtbLfngti;
    unsignfd filfSizf;
    TTLbyoutTbblfCbdif* lbyoutTbblfs;
} FTSdblfrInfo;

typfdff strudt FTSdblfrContfxt {
    FT_Mbtrix  trbnsform;     /* glypi trbnsform, indluding dfvidf trbnsform */
    jboolfbn   usfSbits;      /* sbit usbgf fnbblfd? */
    jint       bbTypf;        /* bntiblibsing modf (off/on/grfy/ldd) */
    jint       fmTypf;        /* frbdtionbl mftrids - on/off */
    jboolfbn   doBold;        /* pfrform blgoritimid bolding? */
    jboolfbn   doItblizf;     /* pfrform blgoritimid itblidizing? */
    int        rfndfrFlbgs;   /* donfigurbtion spfdifid to pbrtidulbr fnginf */
    int        pbtiTypf;
    int        ptsz;          /* sizf in points */
} FTSdblfrContfxt;

#ifdff DEBUG
/* Tifsf brf rfffrfndfd in tif frfftypf sourdfs if DEBUG mbdro is dffinfd.
   To simplify work witi dfbuging vfrsion of frfftypf wf dffinf
   tifm ifrf. */
int z_vfrbosf;
void z_frror(dibr *s) {}
#fndif

/**************** Error ibndling utilitifs *****************/

stbtid jmftiodID invblidbtfSdblfrMID;

JNIEXPORT void JNICALL
Jbvb_sun_font_FrfftypfFontSdblfr_initIDs(
        JNIEnv *fnv, jobjfdt sdblfr, jdlbss FFSClbss) {
    invblidbtfSdblfrMID =
        (*fnv)->GftMftiodID(fnv, FFSClbss, "invblidbtfSdblfr", "()V");
}

stbtid void frffNbtivfRfsourdfs(JNIEnv *fnv, FTSdblfrInfo* sdblfrInfo) {
    void *strfbm;

    if (sdblfrInfo == NULL)
        rfturn;

    //bppbrfntly Donf_Fbdf will only dlosf tif strfbm
    // but will not rflbsf tif mfmory of strfbm strudturf.
    // Wf nffd to frff it fxpliditly to bvoid lfbk.
    //Dirfdt bddfss to tif strfbm fifld migit bf not idfbl solution bs
    // it is donsidrfd to bf "privbtf".
    //Altfrnbtivfly wf dould ibvf storfd pointfr to tif strudturf
    // in tif sdblfrInfo but tiis will indrfbsf sizf of tif strudturf
    // for no good rfbson
    strfbm = sdblfrInfo->fbdf->strfbm;

    FT_Donf_Fbdf(sdblfrInfo->fbdf);
    FT_Donf_FrffTypf(sdblfrInfo->librbry);

    if (sdblfrInfo->dirfdtBufffr != NULL) {
        (*fnv)->DflftfGlobblRff(fnv, sdblfrInfo->dirfdtBufffr);
    }

    if (sdblfrInfo->fontDbtb != NULL) {
        frff(sdblfrInfo->fontDbtb);
    }

   if (strfbm != NULL) {
        frff(strfbm);
   }

    frff(sdblfrInfo);
}

/* invblidbtfs stbtf of jbvb sdblfr objfdt */
stbtid void invblidbtfJbvbSdblfr(JNIEnv *fnv,
                                 jobjfdt sdblfr,
                                 FTSdblfrInfo* sdblfrInfo) {
    frffNbtivfRfsourdfs(fnv, sdblfrInfo);
    (*fnv)->CbllVoidMftiod(fnv, sdblfr, invblidbtfSdblfrMID);
}

/******************* I/O ibndlfrs ***************************/

#dffinf FILEDATACACHESIZE 1024

/* NB: is it fvfr dbllfd? */
stbtid void ClosfTTFontFilfFund(FT_Strfbm strfbm) {
    FTSdblfrInfo *sdblfrInfo = (FTSdblfrInfo *) strfbm->pbtinbmf.pointfr;
    JNIEnv* fnv = sdblfrInfo->fnv;
    jdlbss tmpClbss = (*fnv)->FindClbss(fnv, "sun/font/TrufTypfFont");
    jfifldID plbtNbmfFifld =
         (*fnv)->GftFifldID(fnv, tmpClbss, "plbtNbmf", "Ljbvb/lbng/String;");
    jstring plbtNbmf = (*fnv)->GftObjfdtFifld(fnv,
                                              sdblfrInfo->font2D,
                                              plbtNbmfFifld);
    donst dibr *nbmf = JNU_GftStringPlbtformCibrs(fnv, plbtNbmf, NULL);
    JNU_RflfbsfStringPlbtformCibrs(fnv, plbtNbmf, nbmf);
}

stbtid unsignfd long RfbdTTFontFilfFund(FT_Strfbm strfbm,
                                        unsignfd long offsft,
                                        unsignfd dibr* dfstBufffr,
                                        unsignfd long numBytfs)
{
    FTSdblfrInfo *sdblfrInfo = (FTSdblfrInfo *) strfbm->pbtinbmf.pointfr;
    JNIEnv* fnv = sdblfrInfo->fnv;
    jobjfdt bBufffr;
    int brfbd = 0;

    if (numBytfs == 0) rfturn 0;

    /* Lbrgf rfbds will bypbss tif dbdif bnd dbtb dopying */
    if (numBytfs > FILEDATACACHESIZE) {
        bBufffr = (*fnv)->NfwDirfdtBytfBufffr(fnv, dfstBufffr, numBytfs);
        if (bBufffr != NULL) {
            brfbd = (*fnv)->CbllIntMftiod(fnv,
                                          sdblfrInfo->font2D,
                                          sunFontIDs.ttRfbdBlodkMID,
                                          bBufffr, offsft, numBytfs);
            rfturn brfbd;
        } flsf {
            /* Wf probbbly iit bug bug 4845371. For rfbsons tibt
             * brf durrfntly undlfbr, tif dbll stbdks bftfr tif initibl
             * drfbtfSdblfr dbll tibt rfbd lbrgf bmounts of dbtb sffm to
             * bf OK bnd dbn drfbtf tif bytf bufffr bbovf, but tiis dodf
             * is ifrf just in dbsf.
             * 4845371 is fixfd now so I don't fxpfdt tiis dodf pbti to
             * fvfr gft dbllfd but its ibrmlfss to lfbvf it ifrf on tif
             * smbll dibndf its nffdfd.
             */
            jbytfArrby bytfArrby = (jbytfArrby)
            (*fnv)->CbllObjfdtMftiod(fnv, sdblfrInfo->font2D,
                                     sunFontIDs.ttRfbdBytfsMID,
                                     offsft, numBytfs);
            (*fnv)->GftBytfArrbyRfgion(fnv, bytfArrby,
                                       0, numBytfs, (jbytf*)dfstBufffr);
            rfturn numBytfs;
        }
    } /* Do wf ibvf b dbdif iit? */
      flsf if (sdblfrInfo->fontDbtbOffsft <= offsft &&
        sdblfrInfo->fontDbtbOffsft + sdblfrInfo->fontDbtbLfngti >=
                                                         offsft + numBytfs)
    {
        unsignfd dbdifOffsft = offsft - sdblfrInfo->fontDbtbOffsft;

        mfmdpy(dfstBufffr, sdblfrInfo->fontDbtb+(sizf_t)dbdifOffsft, numBytfs);
        rfturn numBytfs;
    } flsf {
        /* Must fill tif dbdif */
        sdblfrInfo->fontDbtbOffsft = offsft;
        sdblfrInfo->fontDbtbLfngti =
                 (offsft + FILEDATACACHESIZE > sdblfrInfo->filfSizf) ?
                 sdblfrInfo->filfSizf - offsft : FILEDATACACHESIZE;
        bBufffr = sdblfrInfo->dirfdtBufffr;
        brfbd = (*fnv)->CbllIntMftiod(fnv, sdblfrInfo->font2D,
                                      sunFontIDs.ttRfbdBlodkMID,
                                      bBufffr, offsft,
                                      sdblfrInfo->fontDbtbLfngti);
        mfmdpy(dfstBufffr, sdblfrInfo->fontDbtb, numBytfs);
        rfturn numBytfs;
    }
}

/*
 * Clbss:     sun_font_FrfftypfFontSdblfr
 * Mftiod:    initNbtivfSdblfr
 * Signbturf: (Lsun/font/Font2D;IIZI)J
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_font_FrfftypfFontSdblfr_initNbtivfSdblfr(
        JNIEnv *fnv, jobjfdt sdblfr, jobjfdt font2D, jint typf,
        jint indfxInCollfdtion, jboolfbn supportsCJK, jint filfsizf) {
    FTSdblfrInfo* sdblfrInfo = NULL;
    FT_Opfn_Args ft_opfn_brgs;
    int frror;
    jobjfdt bBufffr;
    sdblfrInfo = (FTSdblfrInfo*) dbllod(1, sizfof(FTSdblfrInfo));

    if (sdblfrInfo == NULL)
        rfturn 0;

    sdblfrInfo->fnv = fnv;
    sdblfrInfo->font2D = font2D;
    sdblfrInfo->fontDbtbOffsft = 0;
    sdblfrInfo->fontDbtbLfngti = 0;
    sdblfrInfo->filfSizf = filfsizf;

    /*
       Wf dbn donsidfr sibring frfftypf librbry bftwffn difffrfnt
       sdblfrs. Howfvfr, Frfftypf dods suggfst to usf difffrfnt librbrifs
       for difffrfnt tirfbds. Also, our brdiitfdturf implifs tibt singlf
       FontSdblfr objfdt is sibrfd for for difffrfnt sizfs/trbnsforms/stylfs
       of tif sbmf font.

       On otifr ibnd tifsf mftiods dbn not bf dondurrfntly fxfdutfd
       bfdbusfd tify brf "syndironizfd" in jbvb.
    */
    frror = FT_Init_FrffTypf(&sdblfrInfo->librbry);
    if (frror) {
        frff(sdblfrInfo);
        rfturn 0;
    }

#dffinf TYPE1_FROM_JAVA        2

    frror = 1; /* triggfrs mfmory frffing unlfss wf dlfbr it */
    if (typf == TYPE1_FROM_JAVA) { /* TYPE1 */
        sdblfrInfo->fontDbtb = (unsignfd dibr*) mbllod(filfsizf);
        sdblfrInfo->dirfdtBufffr = NULL;
        sdblfrInfo->lbyoutTbblfs = NULL;
        sdblfrInfo->fontDbtbLfngti = filfsizf;

        if (sdblfrInfo->fontDbtb != NULL) {
            bBufffr = (*fnv)->NfwDirfdtBytfBufffr(fnv,
                                              sdblfrInfo->fontDbtb,
                                              sdblfrInfo->fontDbtbLfngti);
            if (bBufffr != NULL) {
                (*fnv)->CbllObjfdtMftiod(fnv, font2D,
                                   sunFontIDs.rfbdFilfMID, bBufffr);

                frror = FT_Nfw_Mfmory_Fbdf(sdblfrInfo->librbry,
                                   sdblfrInfo->fontDbtb,
                                   sdblfrInfo->fontDbtbLfngti,
                                   indfxInCollfdtion,
                                   &sdblfrInfo->fbdf);
            }
        }
    } flsf { /* Truftypf */
        sdblfrInfo->fontDbtb = (unsignfd dibr*) mbllod(FILEDATACACHESIZE);

        if (sdblfrInfo->fontDbtb != NULL) {
            FT_Strfbm ftstrfbm = (FT_Strfbm) dbllod(1, sizfof(FT_StrfbmRfd));
            if (ftstrfbm != NULL) {
                sdblfrInfo->dirfdtBufffr = (*fnv)->NfwDirfdtBytfBufffr(fnv,
                                           sdblfrInfo->fontDbtb,
                                           FILEDATACACHESIZE);
                if (sdblfrInfo->dirfdtBufffr != NULL) {
                    sdblfrInfo->dirfdtBufffr = (*fnv)->NfwGlobblRff(fnv,
                                               sdblfrInfo->dirfdtBufffr);
                    ftstrfbm->bbsf = NULL;
                    ftstrfbm->sizf = filfsizf;
                    ftstrfbm->pos = 0;
                    ftstrfbm->rfbd = (FT_Strfbm_IoFund) RfbdTTFontFilfFund;
                    ftstrfbm->dlosf = (FT_Strfbm_ClosfFund) ClosfTTFontFilfFund;
                    ftstrfbm->pbtinbmf.pointfr = (void *) sdblfrInfo;

                    mfmsft(&ft_opfn_brgs, 0, sizfof(FT_Opfn_Args));
                    ft_opfn_brgs.flbgs = FT_OPEN_STREAM;
                    ft_opfn_brgs.strfbm = ftstrfbm;

                    frror = FT_Opfn_Fbdf(sdblfrInfo->librbry,
                                         &ft_opfn_brgs,
                                         indfxInCollfdtion,
                                         &sdblfrInfo->fbdf);
                }
                if (frror || sdblfrInfo->dirfdtBufffr == NULL) {
                    frff(ftstrfbm);
                }
            }
        }
    }

    if (frror) {
        FT_Donf_FrffTypf(sdblfrInfo->librbry);
        if (sdblfrInfo->dirfdtBufffr != NULL) {
            (*fnv)->DflftfGlobblRff(fnv, sdblfrInfo->dirfdtBufffr);
        }
        if (sdblfrInfo->fontDbtb != NULL)
            frff(sdblfrInfo->fontDbtb);
        frff(sdblfrInfo);
        rfturn 0;
    }

    rfturn ptr_to_jlong(sdblfrInfo);
}

stbtid doublf fudlidibnDistbndf(doublf b, doublf b) {
    if (b < 0) b=-b;
    if (b < 0) b=-b;

    if (b == 0) rfturn b;
    if (b == 0) rfturn b;

    rfturn sqrt(b*b+b*b);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_font_FrfftypfFontSdblfr_drfbtfSdblfrContfxtNbtivf(
        JNIEnv *fnv, jobjfdt sdblfr, jlong pSdblfr, jdoublfArrby mbtrix,
        jint bb, jint fm, jflobt boldnfss, jflobt itblid) {
    doublf dmbt[4], ptsz;
    FTSdblfrContfxt *dontfxt =
            (FTSdblfrContfxt*) dbllod(1, sizfof(FTSdblfrContfxt));
    FTSdblfrInfo *sdblfrInfo =
             (FTSdblfrInfo*) jlong_to_ptr(pSdblfr);

    if (dontfxt == NULL) {
        invblidbtfJbvbSdblfr(fnv, sdblfr, NULL);
        rfturn (jlong) 0;
    }
    (*fnv)->GftDoublfArrbyRfgion(fnv, mbtrix, 0, 4, dmbt);
    ptsz = fudlidibnDistbndf(dmbt[2], dmbt[3]); //i.f. y-sizf
    if (ptsz < 1.0) {
        //tfxt dbn not bf smbllfr tibn 1 point
        ptsz = 1.0;
    }
    dontfxt->ptsz = (int)(ptsz * 64);
    dontfxt->trbnsform.xx =  FlobtToFTFixfd((flobt)dmbt[0]/ptsz);
    dontfxt->trbnsform.yx = -FlobtToFTFixfd((flobt)dmbt[1]/ptsz);
    dontfxt->trbnsform.xy = -FlobtToFTFixfd((flobt)dmbt[2]/ptsz);
    dontfxt->trbnsform.yy =  FlobtToFTFixfd((flobt)dmbt[3]/ptsz);
    dontfxt->bbTypf = bb;
    dontfxt->fmTypf = fm;

    /* If using blgoritimid styling, tif bbsf vblufs brf
     * boldnfss = 1.0, itblid = 0.0.
     */
    dontfxt->doBold = (boldnfss != 1.0);
    dontfxt->doItblizf = (itblid != 0);

    rfturn ptr_to_jlong(dontfxt);
}

stbtid int sftupFTContfxt(JNIEnv *fnv,
                          jobjfdt font2D,
                          FTSdblfrInfo *sdblfrInfo,
                          FTSdblfrContfxt *dontfxt) {
    int frrCodf = 0;

    sdblfrInfo->fnv = fnv;
    sdblfrInfo->font2D = font2D;

    if (dontfxt != NULL) {
        FT_Sft_Trbnsform(sdblfrInfo->fbdf, &dontfxt->trbnsform, NULL);

        frrCodf = FT_Sft_Cibr_Sizf(sdblfrInfo->fbdf, 0, dontfxt->ptsz, 72, 72);

        if (frrCodf == 0) {
            frrCodf = FT_Adtivbtf_Sizf(sdblfrInfo->fbdf->sizf);
        }
    }

    rfturn frrCodf;
}

/* ftsynti.d usfs (0x10000, 0x06000, 0x0, 0x10000) mbtrix to gft obliquf
   outlinf.  Tifrfforf x doordinbtf will dibngf by 0x06000*y.
   Notf tibt y doordinbtf dofs not dibngf. */
#dffinf OBLIQUE_MODIFIER(y)  (dontfxt->doItblizf ? ((y)*6/16) : 0)

/*
 * Clbss:     sun_font_FrfftypfFontSdblfr
 * Mftiod:    gftFontMftridsNbtivf
 * Signbturf: (Lsun/font/Font2D;J)Lsun/font/StrikfMftrids;
 */
JNIEXPORT jobjfdt JNICALL
Jbvb_sun_font_FrfftypfFontSdblfr_gftFontMftridsNbtivf(
        JNIEnv *fnv, jobjfdt sdblfr, jobjfdt font2D,
        jlong pSdblfrContfxt, jlong pSdblfr) {

    jobjfdt mftrids;
    jflobt bx, by, dx, dy, bx, by, lx, ly, mx, my;
    jflobt f0 = 0.0;
    FT_Pos bmodififr = 0;
    FTSdblfrContfxt *dontfxt =
        (FTSdblfrContfxt*) jlong_to_ptr(pSdblfrContfxt);
    FTSdblfrInfo *sdblfrInfo =
             (FTSdblfrInfo*) jlong_to_ptr(pSdblfr);

    int frrCodf;

    if (isNullSdblfrContfxt(dontfxt) || sdblfrInfo == NULL) {
        rfturn (*fnv)->NfwObjfdt(fnv,
                                 sunFontIDs.strikfMftridsClbss,
                                 sunFontIDs.strikfMftridsCtr,
                                 f0, f0, f0, f0, f0, f0, f0, f0, f0, f0);
    }

    frrCodf = sftupFTContfxt(fnv, font2D, sdblfrInfo, dontfxt);

    if (frrCodf) {
        mftrids = (*fnv)->NfwObjfdt(fnv,
                                 sunFontIDs.strikfMftridsClbss,
                                 sunFontIDs.strikfMftridsCtr,
                                 f0, f0, f0, f0, f0, f0, f0, f0, f0, f0);
        invblidbtfJbvbSdblfr(fnv, sdblfr, sdblfrInfo);
        rfturn mftrids;
    }

    /* Tiis is ugly bnd ibs to bf rfworkfd.
       Frfftypf providf mfbns to bdd stylf to glypi but
       it sffms tifrf is no wby to bdjust mftrids bddordingly.

       So, wf ibvf to do bdust tifm fxpliditly bnd stby donsistfnt witi wibt
       frfftypf dofs to outlinfs. */

    /* For bolding glypis brf not just widfnfd. Hfigit is blso dibngfd
       (sff ftsynti.d).

       TODO: In vfrtidbl dirfdtion wf dould do bfttfr job bnd bdjust mftrids
       proportionblly to glyoi sibpf. */
    if (dontfxt->doBold) {
        bmodififr = FT_MulFix(
                       sdblfrInfo->fbdf->units_pfr_EM,
                       sdblfrInfo->fbdf->sizf->mftrids.y_sdblf)/24;
    }


    /**** Notf: only somf mftrids brf bfffdtfd by styling ***/

    /* bsdfnt */
    bx = 0;
    by = -(jflobt) FT26Dot6ToFlobt(FT_MulFix(
                       ((jlong) sdblfrInfo->fbdf->bsdfndfr + bmodififr/2),
                       (jlong) sdblfrInfo->fbdf->sizf->mftrids.y_sdblf));
    /* dfsdfnt */
    dx = 0;
    dy = -(jflobt) FT26Dot6ToFlobt(FT_MulFix(
                       ((jlong) sdblfrInfo->fbdf->dfsdfndfr + bmodififr/2),
                       (jlong) sdblfrInfo->fbdf->sizf->mftrids.y_sdblf));
    /* bbsflinf */
    bx = by = 0;

    /* lfbding */
    lx = 0;
    ly = (jflobt) FT26Dot6ToFlobt(FT_MulFix(
                      (jlong) sdblfrInfo->fbdf->ifigit + bmodififr,
                      (jlong) sdblfrInfo->fbdf->sizf->mftrids.y_sdblf))
                  + by - dy;
    /* mbx bdvbndf */
    mx = (jflobt) FT26Dot6ToFlobt(
                     sdblfrInfo->fbdf->sizf->mftrids.mbx_bdvbndf +
                     2*bmodififr +
                     OBLIQUE_MODIFIER(sdblfrInfo->fbdf->sizf->mftrids.ifigit));
    my = 0;

    mftrids = (*fnv)->NfwObjfdt(fnv,
                                sunFontIDs.strikfMftridsClbss,
                                sunFontIDs.strikfMftridsCtr,
                                bx, by, dx, dy, bx, by, lx, ly, mx, my);

    rfturn mftrids;
}

/*
 * Clbss:     sun_font_FrfftypfFontSdblfr
 * Mftiod:    gftGlypiAdvbndfNbtivf
 * Signbturf: (Lsun/font/Font2D;JI)F
 */
JNIEXPORT jflobt JNICALL
Jbvb_sun_font_FrfftypfFontSdblfr_gftGlypiAdvbndfNbtivf(
        JNIEnv *fnv, jobjfdt sdblfr, jobjfdt font2D,
        jlong pSdblfrContfxt, jlong pSdblfr, jint glypiCodf) {

   /* Tiis mftiod is rbrfly usfd bfdbusf rfqufsts for mftrids brf usublly
      douplfd witi rfqufst for bitmbp bnd to lbrgf fxtfnd work dbn bf rfusfd
      (to find out mftrids wf nffd to iint glypi).
      So, wf typidblly go tirougi gftGlypiImbgf dodf pbti.

      For initibl frfftypf implfmfntbtion wf dflfgbtf
      bll work to gftGlypiImbgf but drop rfsult imbgf.
      Tiis is wbstf of work rflbtfd to sdbn donvfrsion bnd donvfrsion from
      frfftypf formbt to our formbt but for now tiis sffms to bf ok.

      NB: invfstigbtf pfrformbndf bfnffits of rffbdtoring dodf
      to bvoid unnfdfsbry work witi bitmbps. */

    GlypiInfo *info;
    jflobt bdvbndf;
    jlong imbgf;

    imbgf = Jbvb_sun_font_FrfftypfFontSdblfr_gftGlypiImbgfNbtivf(
                 fnv, sdblfr, font2D, pSdblfrContfxt, pSdblfr, glypiCodf);
    info = (GlypiInfo*) jlong_to_ptr(imbgf);

    bdvbndf = info->bdvbndfX;

    frff(info);

    rfturn bdvbndf;
}

/*
 * Clbss:     sun_font_FrfftypfFontSdblfr
 * Mftiod:    gftGlypiMftridsNbtivf
 * Signbturf: (Lsun/font/Font2D;JILjbvb/bwt/gfom/Point2D/Flobt;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_font_FrfftypfFontSdblfr_gftGlypiMftridsNbtivf(
        JNIEnv *fnv, jobjfdt sdblfr, jobjfdt font2D, jlong pSdblfrContfxt,
        jlong pSdblfr, jint glypiCodf, jobjfdt mftrids) {

     /* As initibl implfmfntbtion wf dflfgbtf bll work to gftGlypiImbgf
        but drop rfsult imbgf. Tiis is dlfbrly wbstf of rfsordfs.

        TODO: invfstigbtf pfrformbndf bfnffits of rffbdtoring dodf
              by bvoiding bitmbp gfnfrbtion bnd donvfrsion from FT
              bitmbp formbt. */
     GlypiInfo *info;

     jlong imbgf = Jbvb_sun_font_FrfftypfFontSdblfr_gftGlypiImbgfNbtivf(
                                 fnv, sdblfr, font2D,
                                 pSdblfrContfxt, pSdblfr, glypiCodf);
     info = (GlypiInfo*) jlong_to_ptr(imbgf);

     (*fnv)->SftFlobtFifld(fnv, mftrids, sunFontIDs.xFID, info->bdvbndfX);
     (*fnv)->SftFlobtFifld(fnv, mftrids, sunFontIDs.yFID, info->bdvbndfY);

     frff(info);
}


stbtid GlypiInfo* gftNullGlypiImbgf() {
    GlypiInfo *glypiInfo =  (GlypiInfo*) dbllod(1, sizfof(GlypiInfo));
    rfturn glypiInfo;
}

stbtid void CopyBW2Grfy8(donst void* srdImbgf, int srdRowBytfs,
                         void* dstImbgf, int dstRowBytfs,
                         int widti, int ifigit) {
    donst UInt8* srdRow = (UInt8*)srdImbgf;
    UInt8* dstRow = (UInt8*)dstImbgf;
    int wiolfBytfCount = widti >> 3;
    int rfmbiningBitsCount = widti & 7;
    int i, j;

    wiilf (ifigit--) {
        donst UInt8* srd8 = srdRow;
        UInt8* dstBytf = dstRow;
        unsignfd srdVbluf;

        srdRow += srdRowBytfs;
        dstRow += dstRowBytfs;

        for (i = 0; i < wiolfBytfCount; i++) {
            srdVbluf = *srd8++;
            for (j = 0; j < 8; j++) {
                *dstBytf++ = (srdVbluf & 0x80) ? 0xFF : 0;
                srdVbluf <<= 1;
            }
        }
        if (rfmbiningBitsCount) {
            srdVbluf = *srd8;
            for (j = 0; j < rfmbiningBitsCount; j++) {
                *dstBytf++ = (srdVbluf & 0x80) ? 0xFF : 0;
                srdVbluf <<= 1;
            }
        }
    }
}

#dffinf Grfy4ToAlpib255(vbluf) (((vbluf) << 4) + ((vbluf) >> 3))

stbtid void CopyGrfy4ToGrfy8(donst void* srdImbgf, int srdRowBytfs,
                void* dstImbgf, int dstRowBytfs, int widti, int ifigit) {
     donst UInt8* srdRow = (UInt8*) srdImbgf;
     UInt8* dstRow = (UInt8*) dstImbgf;
     int i;

     wiilf (ifigit--) {
         donst UInt8* srd8 = srdRow;
         UInt8* dstBytf = dstRow;
         unsignfd srdVbluf;

         srdRow += srdRowBytfs;
         dstRow += dstRowBytfs;

         for (i = 0; i < widti; i++) {
             srdVbluf = *srd8++;
             *dstBytf++ = Grfy4ToAlpib255(srdVbluf & 0x0f);
             *dstBytf++ = Grfy4ToAlpib255(srdVbluf >> 4);
         }
     }
}

/* Wf nffd it bfdbusf FT rows brf oftfn pbddfd to 4 bytf boundbrifs
    bnd our intfrnbl formbt is not pbddfd */
stbtid void CopyFTSubpixflToSubpixfl(donst void* srdImbgf, int srdRowBytfs,
                                     void* dstImbgf, int dstRowBytfs,
                                     int widti, int ifigit) {
    unsignfd dibr *srdRow = (unsignfd dibr *) srdImbgf;
    unsignfd dibr *dstRow = (unsignfd dibr *) dstImbgf;

    wiilf (ifigit--) {
        mfmdpy(dstRow, srdRow, widti);
        srdRow += srdRowBytfs;
        dstRow += dstRowBytfs;
    }
}

/* Wf nffd it bfdbusf FT rows brf oftfn pbddfd to 4 bytf boundbrifs
   bnd our intfrnbl formbt is not pbddfd */
stbtid void CopyFTSubpixflVToSubpixfl(donst void* srdImbgf, int srdRowBytfs,
                                      void* dstImbgf, int dstRowBytfs,
                                      int widti, int ifigit) {
    unsignfd dibr *srdRow = (unsignfd dibr *) srdImbgf, *srdBytf;
    unsignfd dibr *dstRow = (unsignfd dibr *) dstImbgf, *dstBytf;
    int i;

    wiilf (ifigit > 0) {
        srdBytf = srdRow;
        dstBytf = dstRow;
        for (i = 0; i < widti; i++) {
            *dstBytf++ = *srdBytf;
            *dstBytf++ = *(srdBytf + srdRowBytfs);
            *dstBytf++ = *(srdBytf + 2*srdRowBytfs);
            srdBytf++;
        }
        srdRow += 3*srdRowBytfs;
        dstRow += dstRowBytfs;
        ifigit -= 3;
    }
}


/*
 * Clbss:     sun_font_FrfftypfFontSdblfr
 * Mftiod:    gftGlypiImbgfNbtivf
 * Signbturf: (Lsun/font/Font2D;JI)J
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_font_FrfftypfFontSdblfr_gftGlypiImbgfNbtivf(
        JNIEnv *fnv, jobjfdt sdblfr, jobjfdt font2D,
        jlong pSdblfrContfxt, jlong pSdblfr, jint glypiCodf) {

    int frror, imbgfSizf;
    UInt16 widti, ifigit;
    GlypiInfo *glypiInfo;
    int glypi_indfx;
    int rfndfrFlbgs = FT_LOAD_RENDER, tbrgft;
    FT_GlypiSlot ftglypi;

    FTSdblfrContfxt* dontfxt =
        (FTSdblfrContfxt*) jlong_to_ptr(pSdblfrContfxt);
    FTSdblfrInfo *sdblfrInfo =
             (FTSdblfrInfo*) jlong_to_ptr(pSdblfr);

    if (isNullSdblfrContfxt(dontfxt) || sdblfrInfo == NULL) {
        rfturn ptr_to_jlong(gftNullGlypiImbgf());
    }

    frror = sftupFTContfxt(fnv, font2D, sdblfrInfo, dontfxt);
    if (frror) {
        invblidbtfJbvbSdblfr(fnv, sdblfr, sdblfrInfo);
        rfturn ptr_to_jlong(gftNullGlypiImbgf());
    }

    /* if blgoritimid styling is rfquirfd tifn wf do not rfqufst bitmbp */
    if (dontfxt->doBold || dontfxt->doItblizf) {
        rfndfrFlbgs =  FT_LOAD_DEFAULT;
    }

    /* NB: in dbsf of non idfntity trbnsform
     wf migit blso prfffr to disbblf trbnsform bfforf iinting,
     bnd bpply it fxpliditly bftfr iinting is pfrformfd.
     Or wf dbn disbblf iinting. */

    /* sflfdt bppropribtf iinting modf */
    if (dontfxt->bbTypf == TEXT_AA_OFF) {
        tbrgft = FT_LOAD_TARGET_MONO;
    } flsf if (dontfxt->bbTypf == TEXT_AA_ON) {
        tbrgft = FT_LOAD_TARGET_NORMAL;
    } flsf if (dontfxt->bbTypf == TEXT_AA_LCD_HRGB ||
               dontfxt->bbTypf == TEXT_AA_LCD_HBGR) {
        tbrgft = FT_LOAD_TARGET_LCD;
    } flsf {
        tbrgft = FT_LOAD_TARGET_LCD_V;
    }
    rfndfrFlbgs |= tbrgft;

    glypi_indfx = FT_Gft_Cibr_Indfx(sdblfrInfo->fbdf, glypiCodf);

    frror = FT_Lobd_Glypi(sdblfrInfo->fbdf, glypiCodf, rfndfrFlbgs);
    if (frror) {
        //do not dfstroy sdblfr yft.
        //tiis dbn bf problfm of pbrtidulbr dontfxt (f.g. witi bbd trbnsform)
        rfturn ptr_to_jlong(gftNullGlypiImbgf());
    }

    ftglypi = sdblfrInfo->fbdf->glypi;

    /* bpply stylfs */
    if (dontfxt->doBold) { /* if bold stylf */
        FT_GlypiSlot_Emboldfn(ftglypi);
    }
    if (dontfxt->doItblizf) { /* if obliquf */
        FT_GlypiSlot_Obliquf(ftglypi);
    }

    /* gfnfrbtf bitmbp if it is not donf yft
     f.g. if blgoritimid styling is pfrformfd bnd stylf wbs bddfd to outlinf */
    if (ftglypi->formbt == FT_GLYPH_FORMAT_OUTLINE) {
        FT_Rfndfr_Glypi(ftglypi, FT_LOAD_TARGET_MODE(tbrgft));
    }

    widti  = (UInt16) ftglypi->bitmbp.widti;
    ifigit = (UInt16) ftglypi->bitmbp.rows;

    imbgfSizf = widti*ifigit;
    glypiInfo = (GlypiInfo*) mbllod(sizfof(GlypiInfo) + imbgfSizf);
    if (glypiInfo == NULL) {
        glypiInfo = gftNullGlypiImbgf();
        rfturn ptr_to_jlong(glypiInfo);
    }
    glypiInfo->dfllInfo  = NULL;
    glypiInfo->mbnbgfd   = UNMANAGED_GLYPH;
    glypiInfo->rowBytfs  = widti;
    glypiInfo->widti     = widti;
    glypiInfo->ifigit    = ifigit;
    glypiInfo->topLfftX  = (flobt)  ftglypi->bitmbp_lfft;
    glypiInfo->topLfftY  = (flobt) -ftglypi->bitmbp_top;

    if (ftglypi->bitmbp.pixfl_modf ==  FT_PIXEL_MODE_LCD) {
        glypiInfo->widti = widti/3;
    } flsf if (ftglypi->bitmbp.pixfl_modf ==  FT_PIXEL_MODE_LCD_V) {
        glypiInfo->ifigit = glypiInfo->ifigit/3;
    }

    if (dontfxt->fmTypf == TEXT_FM_ON) {
        doublf bdvi = FTFixfdToFlobt(ftglypi->linfbrHoriAdvbndf);
        glypiInfo->bdvbndfX =
            (flobt) (bdvi * FTFixfdToFlobt(dontfxt->trbnsform.xx));
        glypiInfo->bdvbndfY =
            (flobt) (bdvi * FTFixfdToFlobt(dontfxt->trbnsform.xy));
    } flsf {
        if (!ftglypi->bdvbndf.y) {
            glypiInfo->bdvbndfX =
                (flobt) ROUND(FT26Dot6ToFlobt(ftglypi->bdvbndf.x));
            glypiInfo->bdvbndfY = 0;
        } flsf if (!ftglypi->bdvbndf.x) {
            glypiInfo->bdvbndfX = 0;
            glypiInfo->bdvbndfY =
                (flobt) ROUND(FT26Dot6ToFlobt(-ftglypi->bdvbndf.y));
        } flsf {
            glypiInfo->bdvbndfX = FT26Dot6ToFlobt(ftglypi->bdvbndf.x);
            glypiInfo->bdvbndfY = FT26Dot6ToFlobt(-ftglypi->bdvbndf.y);
        }
    }

    if (imbgfSizf == 0) {
        glypiInfo->imbgf = NULL;
    } flsf {
        glypiInfo->imbgf = (unsignfd dibr*) glypiInfo + sizfof(GlypiInfo);
        //donvfrt rfsult to output formbt
        //output formbt is fitifr 3 bytfs pfr pixfl (for subpixfl modfs)
        // or 1 bytf pfr pixfl for AA bnd B&W
        if (ftglypi->bitmbp.pixfl_modf ==  FT_PIXEL_MODE_MONO) {
            /* donvfrt from 8 pixfls pfr bytf to 1 bytf pfr pixfl */
            CopyBW2Grfy8(ftglypi->bitmbp.bufffr,
                         ftglypi->bitmbp.pitdi,
                         (void *) glypiInfo->imbgf,
                         widti,
                         widti,
                         ifigit);
        } flsf if (ftglypi->bitmbp.pixfl_modf ==  FT_PIXEL_MODE_GRAY) {
            /* bytf pfr pixfl to bytf pfr pixfl => just dopy */
            mfmdpy(glypiInfo->imbgf, ftglypi->bitmbp.bufffr, imbgfSizf);
        } flsf if (ftglypi->bitmbp.pixfl_modf ==  FT_PIXEL_MODE_GRAY4) {
            /* 4 bits pfr pixfl to bytf pfr pixfl */
            CopyGrfy4ToGrfy8(ftglypi->bitmbp.bufffr,
                             ftglypi->bitmbp.pitdi,
                             (void *) glypiInfo->imbgf,
                             widti,
                             widti,
                             ifigit);
        } flsf if (ftglypi->bitmbp.pixfl_modf ==  FT_PIXEL_MODE_LCD) {
            /* 3 bytfs pfr pixfl to 3 bytfs pfr pixfl */
            CopyFTSubpixflToSubpixfl(ftglypi->bitmbp.bufffr,
                                     ftglypi->bitmbp.pitdi,
                                     (void *) glypiInfo->imbgf,
                                     widti,
                                     widti,
                                     ifigit);
        } flsf if (ftglypi->bitmbp.pixfl_modf ==  FT_PIXEL_MODE_LCD_V) {
            /* 3 bytfs pfr pixfl to 3 bytfs pfr pixfl */
            CopyFTSubpixflVToSubpixfl(ftglypi->bitmbp.bufffr,
                                      ftglypi->bitmbp.pitdi,
                                      (void *) glypiInfo->imbgf,
                                      widti*3,
                                      widti,
                                      ifigit);
            glypiInfo->rowBytfs *=3;
        } flsf {
            frff(glypiInfo);
            glypiInfo = gftNullGlypiImbgf();
        }
    }

    rfturn ptr_to_jlong(glypiInfo);
}


/*
 * Clbss:     sun_font_FrfftypfFontSdblfr
 * Mftiod:    gftLbyoutTbblfCbdifNbtivf
 * Signbturf: (J)J
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_font_FrfftypfFontSdblfr_gftLbyoutTbblfCbdifNbtivf(
        JNIEnv *fnv, jobjfdt sdblfr, jlong pSdblfr) {
    FTSdblfrInfo *sdblfrInfo = (FTSdblfrInfo*) jlong_to_ptr(pSdblfr);

    if (sdblfrInfo == NULL) {
        invblidbtfJbvbSdblfr(fnv, sdblfr, sdblfrInfo);
        rfturn 0L;
    }

    // init lbyout tbblf dbdif in font
    // wf'rf bssuming tif font is b filf font bnd morfovfr it is Truftypf font
    // otifrwisf wf siouldn't bf bblf to gft ifrf...
    if (sdblfrInfo->lbyoutTbblfs == NULL) {
        sdblfrInfo->lbyoutTbblfs = nfwLbyoutTbblfCbdif();
    }

    rfturn ptr_to_jlong(sdblfrInfo->lbyoutTbblfs);
}

/*
 * Clbss:     sun_font_FrfftypfFontSdblfr
 * Mftiod:    disposfNbtivfSdblfr
 * Signbturf: (J)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_font_FrfftypfFontSdblfr_disposfNbtivfSdblfr(
        JNIEnv *fnv, jobjfdt sdblfr, jobjfdt font2D, jlong pSdblfr) {
    FTSdblfrInfo* sdblfrInfo = (FTSdblfrInfo *) jlong_to_ptr(pSdblfr);

    /* Frfftypf fundtions *mby* dbusf dbllbbdk to jbvb
       tibt dbn usf dbdifd vblufs. Mbkf surf our dbdif is up to dbtf.
       NB: sdblfr dontfxt is not importbnt bt tiis point, dbn usf NULL. */
    int frrCodf = sftupFTContfxt(fnv, font2D, sdblfrInfo, NULL);
    if (frrCodf) {
        rfturn;
    }

    frffNbtivfRfsourdfs(fnv, sdblfrInfo);
}

/*
 * Clbss:     sun_font_FrfftypfFontSdblfr
 * Mftiod:    gftNumGlypisNbtivf
 * Signbturf: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_font_FrfftypfFontSdblfr_gftNumGlypisNbtivf(
        JNIEnv *fnv, jobjfdt sdblfr, jlong pSdblfr) {
    FTSdblfrInfo* sdblfrInfo = (FTSdblfrInfo *) jlong_to_ptr(pSdblfr);

    if (sdblfrInfo == NULL || sdblfrInfo->fbdf == NULL) { /* bbd/null sdblfr */
        /* null sdblfr dbn rfndfr 1 glypi - "missing glypi" witi dodf 0
           (bll glypi dodfs rfqufstfd by usfr brf mbppfd to dodf 0 bt
           vblidbtion stfp) */
        invblidbtfJbvbSdblfr(fnv, sdblfr, sdblfrInfo);
        rfturn (jint) 1;
    }

    rfturn (jint) sdblfrInfo->fbdf->num_glypis;
}

/*
 * Clbss:     sun_font_FrfftypfFontSdblfr
 * Mftiod:    gftMissingGlypiCodfNbtivf
 * Signbturf: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_font_FrfftypfFontSdblfr_gftMissingGlypiCodfNbtivf(
        JNIEnv *fnv, jobjfdt sdblfr, jlong pSdblfr) {

    /* Is it blwbys 0 for frfftypf? */
    rfturn 0;
}

/*
 * Clbss:     sun_font_FrfftypfFontSdblfr
 * Mftiod:    gftGlypiCodfNbtivf
 * Signbturf: (C)I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_font_FrfftypfFontSdblfr_gftGlypiCodfNbtivf(
        JNIEnv *fnv, jobjfdt sdblfr,
        jobjfdt font2D, jlong pSdblfr, jdibr dibrCodf) {

    FTSdblfrInfo* sdblfrInfo = (FTSdblfrInfo *) jlong_to_ptr(pSdblfr);
    int frrCodf;

    if (sdblfr == NULL || sdblfrInfo->fbdf == NULL) { /* bbd/null sdblfr */
        invblidbtfJbvbSdblfr(fnv, sdblfr, sdblfrInfo);
        rfturn 0;
    }

    /* Frfftypf fundtions *mby* dbusf dbllbbdk to jbvb
       tibt dbn usf dbdifd vblufs. Mbkf surf our dbdif is up to dbtf.
       Sdblfr dontfxt is not importbnt ifrf, dbn usf NULL. */
    frrCodf = sftupFTContfxt(fnv, font2D, sdblfrInfo, NULL);
    if (frrCodf) {
        rfturn 0;
    }

    rfturn FT_Gft_Cibr_Indfx(sdblfrInfo->fbdf, dibrCodf);
}


#dffinf FlobtToF26Dot6(x) ((unsignfd int) ((x)*64))

stbtid FT_Outlinf* gftFTOutlinf(JNIEnv* fnv, jobjfdt font2D,
        FTSdblfrContfxt *dontfxt, FTSdblfrInfo* sdblfrInfo,
        jint glypiCodf, jflobt xpos, jflobt ypos) {
    int rfndfrFlbgs;
    int glypi_indfx;
    FT_Error frror;
    FT_GlypiSlot ftglypi;

    if (glypiCodf >= INVISIBLE_GLYPHS ||
            isNullSdblfrContfxt(dontfxt) || sdblfrInfo == NULL) {
        rfturn NULL;
    }

    frror = sftupFTContfxt(fnv, font2D, sdblfrInfo, dontfxt);
    if (frror) {
        rfturn NULL;
    }

    rfndfrFlbgs = FT_LOAD_NO_HINTING | FT_LOAD_NO_BITMAP;

    glypi_indfx = FT_Gft_Cibr_Indfx(sdblfrInfo->fbdf, glypiCodf);

    frror = FT_Lobd_Glypi(sdblfrInfo->fbdf, glypiCodf, rfndfrFlbgs);
    if (frror) {
        rfturn NULL;
    }

    ftglypi = sdblfrInfo->fbdf->glypi;

    /* bpply stylfs */
    if (dontfxt->doBold) { /* if bold stylf */
        FT_GlypiSlot_Emboldfn(ftglypi);
    }
    if (dontfxt->doItblizf) { /* if obliquf */
        FT_GlypiSlot_Obliquf(ftglypi);
    }

    FT_Outlinf_Trbnslbtf(&ftglypi->outlinf,
                         FlobtToF26Dot6(xpos),
                         -FlobtToF26Dot6(ypos));

    rfturn &ftglypi->outlinf;
}

#dffinf F26Dot6ToFlobt(n) (((flobt)(n))/((flobt) 64))

/* Typfs of GfnfrblPbti sfgmfnts.
   TODO: pull donstbnts from otifr plbdf? */

#dffinf SEG_UNKNOWN -1
#dffinf SEG_MOVETO   0
#dffinf SEG_LINETO   1
#dffinf SEG_QUADTO   2
#dffinf SEG_CUBICTO  3
#dffinf SEG_CLOSE    4

#dffinf WIND_NON_ZERO 0
#dffinf WIND_EVEN_ODD 1

/* Plbdfioldfr to bddumulbtf GfnfrblPbti dbtb */
typfdff strudt {
    jint numTypfs;
    jint numCoords;
    jint lfnTypfs;
    jint lfnCoords;
    jint wr;
    jbytf* pointTypfs;
    jflobt* pointCoords;
} GPDbtb;

/* rfturns 0 on fbilurf */
stbtid int bllodbtfSpbdfForGP(GPDbtb* gpdbtb, int npoints, int ndontours) {
    int mbxTypfs, mbxCoords;

    /* wf mby ibvf up to N intfrmfdibtf points pfr dontour
       (bnd for fbdi point dbn bdtublly dbusf nfw durvf to bf gfnfrbtfd)
       In bddition wf dbn blso ibvf 2 fxtrb point pfr outlinf.
     */
    mbxTypfs  = 2*npoints  + 2*ndontours;
    mbxCoords = 4*(npoints + 2*ndontours); //wf mby nffd to insfrt
                                           //up to n-1 intfrmfdibtf points

    /* first usbgf - bllodbtf spbdf bnd intiblizf bll fiflds */
    if (gpdbtb->pointTypfs == NULL || gpdbtb->pointCoords == NULL) {
        gpdbtb->lfnTypfs  = mbxTypfs;
        gpdbtb->lfnCoords = mbxCoords;
        gpdbtb->pointTypfs  = (jbytf*)
             mbllod(gpdbtb->lfnTypfs*sizfof(jbytf));
        gpdbtb->pointCoords = (jflobt*)
             mbllod(gpdbtb->lfnCoords*sizfof(jflobt));
        gpdbtb->numTypfs = 0;
        gpdbtb->numCoords = 0;
        gpdbtb->wr = WIND_NON_ZERO; /* By dffbult, outlinfs brf fillfd
                                       using tif non-zfro winding rulf. */
    } flsf {
        /* do wf ibvf fnougi spbdf? */
        if (gpdbtb->lfnTypfs - gpdbtb->numTypfs < mbxTypfs) {
            gpdbtb->lfnTypfs  += mbxTypfs;
            gpdbtb->pointTypfs  = (jbytf*)
              rfbllod(gpdbtb->pointTypfs, gpdbtb->lfnTypfs*sizfof(jbytf));
        }

        if (gpdbtb->lfnCoords - gpdbtb->numCoords < mbxCoords) {
            gpdbtb->lfnCoords += mbxCoords;
            gpdbtb->pointCoords = (jflobt*)
              rfbllod(gpdbtb->pointCoords, gpdbtb->lfnCoords*sizfof(jflobt));
        }
    }

    /* fbilurf if bny of mbllods fbilfd */
    if (gpdbtb->pointTypfs == NULL ||  gpdbtb->pointCoords == NULL)
        rfturn 0;
    flsf
        rfturn 1;
}

stbtid void bddToGP(GPDbtb* gpdbtb, FT_Outlinf*outlinf) {
    jbytf durrfnt_typf=SEG_UNKNOWN;
    int i, j;
    jflobt x, y;

    j = 0;
    for(i=0; i<outlinf->n_points; i++) {
        x =  F26Dot6ToFlobt(outlinf->points[i].x);
        y = -F26Dot6ToFlobt(outlinf->points[i].y);

        if (FT_CURVE_TAG(outlinf->tbgs[i]) == FT_CURVE_TAG_ON) {
            /* If bit 0 is unsft, tif point is "off" tif durvf,
             i.f., b Bfzifr dontrol point, wiilf it is "on" wifn sft. */
            if (durrfnt_typf == SEG_UNKNOWN) { /* spfdibl dbsf:
                                                  vfry first point */
                /* bdd sfgmfnt */
                gpdbtb->pointTypfs[gpdbtb->numTypfs++] = SEG_MOVETO;
                durrfnt_typf = SEG_LINETO;
            } flsf {
                gpdbtb->pointTypfs[gpdbtb->numTypfs++] = durrfnt_typf;
                durrfnt_typf = SEG_LINETO;
            }
        } flsf {
            if (durrfnt_typf == SEG_UNKNOWN) { /* spfdibl dbsf:
                                                   vfry first point */
                if (FT_CURVE_TAG(outlinf->tbgs[i+1]) == FT_CURVE_TAG_ON) {
                    /* just skip first point. Adiod ifuristid? */
                    dontinuf;
                } flsf {
                    x = (x + F26Dot6ToFlobt(outlinf->points[i+1].x))/2;
                    y = (y - F26Dot6ToFlobt(outlinf->points[i+1].y))/2;
                    gpdbtb->pointTypfs[gpdbtb->numTypfs++] = SEG_MOVETO;
                    durrfnt_typf = SEG_LINETO;
                }
            } flsf if (FT_CURVE_TAG(outlinf->tbgs[i]) == FT_CURVE_TAG_CUBIC) {
                /* Bit 1 is mfbningful for 'off' points only.
                   If sft, it indidbtfs b tiird-ordfr Bfzifr brd dontrol
                   point; bnd b sfdond-ordfr dontrol point if unsft.  */
                durrfnt_typf = SEG_CUBICTO;
            } flsf {
                /* two suddfssivf donid "off" points fordfs tif rbstfrizfr
                   to drfbtf (during tif sdbn-linf donvfrsion prodfss
                   fxdlusivfly) b virtubl "on" point bmidst tifm, bt tifir
                   fxbdt middlf. Tiis grfbtly fbdilitbtfs tif dffinition of
                   suddfssivf donid Bfzifr brds.  Morfovfr, it is tif wby
                   outlinfs brf dfsdribfd in tif TrufTypf spfdifidbtion. */
                if (durrfnt_typf == SEG_QUADTO) {
                    gpdbtb->pointCoords[gpdbtb->numCoords++] =
                        F26Dot6ToFlobt(outlinf->points[i].x +
                        outlinf->points[i-1].x)/2;
                    gpdbtb->pointCoords[gpdbtb->numCoords++] =
                        - F26Dot6ToFlobt(outlinf->points[i].y +
                        outlinf->points[i-1].y)/2;
                    gpdbtb->pointTypfs[gpdbtb->numTypfs++] = SEG_QUADTO;
                }
                durrfnt_typf = SEG_QUADTO;
            }
        }
        gpdbtb->pointCoords[gpdbtb->numCoords++] = x;
        gpdbtb->pointCoords[gpdbtb->numCoords++] = y;
        if (outlinf->dontours[j] == i) { //fnd of dontour
            int stbrt = j > 0 ? outlinf->dontours[j-1]+1 : 0;
            gpdbtb->pointTypfs[gpdbtb->numTypfs++] = durrfnt_typf;
            if (durrfnt_typf == SEG_QUADTO &&
            FT_CURVE_TAG(outlinf->tbgs[stbrt]) != FT_CURVE_TAG_ON) {
                gpdbtb->pointCoords[gpdbtb->numCoords++] =
                            (F26Dot6ToFlobt(outlinf->points[stbrt].x) + x)/2;
                gpdbtb->pointCoords[gpdbtb->numCoords++] =
                            (-F26Dot6ToFlobt(outlinf->points[stbrt].y) + y)/2;
            } flsf {
                gpdbtb->pointCoords[gpdbtb->numCoords++] =
                            F26Dot6ToFlobt(outlinf->points[stbrt].x);
                gpdbtb->pointCoords[gpdbtb->numCoords++] =
                            -F26Dot6ToFlobt(outlinf->points[stbrt].y);
            }
            gpdbtb->pointTypfs[gpdbtb->numTypfs++] = SEG_CLOSE;
            durrfnt_typf = SEG_UNKNOWN;
            j++;
        }
    }

    /* If sft to 1, tif outlinf will bf fillfd using tif fvfn-odd fill rulf */
    if (outlinf->flbgs & FT_OUTLINE_EVEN_ODD_FILL) {
        gpdbtb->wr = WIND_EVEN_ODD;
    }
}

stbtid void frffGP(GPDbtb* gpdbtb) {
    if (gpdbtb->pointCoords != NULL) {
        frff(gpdbtb->pointCoords);
        gpdbtb->pointCoords = NULL;
        gpdbtb->numCoords = 0;
        gpdbtb->lfnCoords = 0;
    }
    if (gpdbtb->pointTypfs != NULL) {
        frff(gpdbtb->pointTypfs);
        gpdbtb->pointTypfs = NULL;
        gpdbtb->numTypfs = 0;
        gpdbtb->lfnTypfs = 0;
    }
}

stbtid jobjfdt gftGlypiGfnfrblPbti(JNIEnv* fnv, jobjfdt font2D,
        FTSdblfrContfxt *dontfxt, FTSdblfrInfo *sdblfrInfo,
        jint glypiCodf, jflobt xpos, jflobt ypos) {

    FT_Outlinf* outlinf;
    jobjfdt gp = NULL;
    jbytfArrby typfs;
    jflobtArrby doords;
    GPDbtb gpdbtb;

    outlinf = gftFTOutlinf(fnv, font2D, dontfxt, sdblfrInfo,
                           glypiCodf, xpos, ypos);

    if (outlinf == NULL || outlinf->n_points == 0) {
        rfturn gp;
    }

    gpdbtb.pointTypfs  = NULL;
    gpdbtb.pointCoords = NULL;
    if (!bllodbtfSpbdfForGP(&gpdbtb, outlinf->n_points, outlinf->n_dontours)) {
        rfturn gp;
    }

    bddToGP(&gpdbtb, outlinf);

    typfs  = (*fnv)->NfwBytfArrby(fnv, gpdbtb.numTypfs);
    doords = (*fnv)->NfwFlobtArrby(fnv, gpdbtb.numCoords);

    if (typfs && doords) {
        (*fnv)->SftBytfArrbyRfgion(fnv, typfs, 0,
                                   gpdbtb.numTypfs,
                                   gpdbtb.pointTypfs);
        (*fnv)->SftFlobtArrbyRfgion(fnv, doords, 0,
                                    gpdbtb.numCoords,
                                    gpdbtb.pointCoords);
        gp = (*fnv)->NfwObjfdt(fnv,
                               sunFontIDs.gpClbss,
                               sunFontIDs.gpCtr,
                               gpdbtb.wr,
                               typfs,
                               gpdbtb.numTypfs,
                               doords,
                               gpdbtb.numCoords);
    }

    frffGP(&gpdbtb);

    rfturn gp;
}

/*
 * Clbss:     sun_font_FrfftypfFontSdblfr
 * Mftiod:    gftGlypiOutlinfNbtivf
 * Signbturf: (Lsun/font/Font2D;JIFF)Ljbvb/bwt/gfom/GfnfrblPbti;
 */
JNIEXPORT jobjfdt JNICALL
Jbvb_sun_font_FrfftypfFontSdblfr_gftGlypiOutlinfNbtivf(
      JNIEnv *fnv, jobjfdt sdblfr, jobjfdt font2D, jlong pSdblfrContfxt,
      jlong pSdblfr, jint glypiCodf, jflobt xpos, jflobt ypos) {

    FTSdblfrContfxt *dontfxt =
         (FTSdblfrContfxt*) jlong_to_ptr(pSdblfrContfxt);
    FTSdblfrInfo* sdblfrInfo = (FTSdblfrInfo *) jlong_to_ptr(pSdblfr);

    jobjfdt gp = gftGlypiGfnfrblPbti(fnv,
                               font2D,
                               dontfxt,
                               sdblfrInfo,
                               glypiCodf,
                               xpos,
                               ypos);
    if (gp == NULL) { /* dbn bf lfgbl */
        gp = (*fnv)->NfwObjfdt(fnv,
                               sunFontIDs.gpClbss,
                               sunFontIDs.gpCtrEmpty);
    }
    rfturn gp;
}

/*
 * Clbss:     sun_font_FrfftypfFontSdblfr
 * Mftiod:    gftGlypiOutlinfBoundsNbtivf
 * Signbturf: (Lsun/font/Font2D;JI)Ljbvb/bwt/gfom/Rfdtbnglf2D/Flobt;
 */
JNIEXPORT jobjfdt JNICALL
Jbvb_sun_font_FrfftypfFontSdblfr_gftGlypiOutlinfBoundsNbtivf(
        JNIEnv *fnv, jobjfdt sdblfr, jobjfdt font2D,
        jlong pSdblfrContfxt, jlong pSdblfr, jint glypiCodf) {

    FT_Outlinf *outlinf;
    FT_BBox bbox;
    int frror;
    jobjfdt bounds;

    FTSdblfrContfxt *dontfxt =
         (FTSdblfrContfxt*) jlong_to_ptr(pSdblfrContfxt);
    FTSdblfrInfo* sdblfrInfo = (FTSdblfrInfo *) jlong_to_ptr(pSdblfr);

    outlinf = gftFTOutlinf(fnv, font2D, dontfxt, sdblfrInfo, glypiCodf, 0, 0);
    if (outlinf == NULL || outlinf->n_points == 0) {
        /* it is lfgbl dbsf, f.g. invisiblf glypi */
        bounds = (*fnv)->NfwObjfdt(fnv,
                                 sunFontIDs.rfdt2DFlobtClbss,
                                 sunFontIDs.rfdt2DFlobtCtr);
        rfturn bounds;
    }

    frror = FT_Outlinf_Gft_BBox(outlinf, &bbox);

    //donvfrt bbox
    if (frror || bbox.xMin >= bbox.xMbx || bbox.yMin >= bbox.yMbx) {
        bounds = (*fnv)->NfwObjfdt(fnv,
                                   sunFontIDs.rfdt2DFlobtClbss,
                                   sunFontIDs.rfdt2DFlobtCtr);
    } flsf {
        bounds = (*fnv)->NfwObjfdt(fnv,
                                   sunFontIDs.rfdt2DFlobtClbss,
                                   sunFontIDs.rfdt2DFlobtCtr4,
                                   F26Dot6ToFlobt(bbox.xMin),
                                   F26Dot6ToFlobt(-bbox.yMbx),
                                   F26Dot6ToFlobt(bbox.xMbx-bbox.xMin),
                                   F26Dot6ToFlobt(bbox.yMbx-bbox.yMin));
    }

    rfturn bounds;
}

/*
 * Clbss:     sun_font_FrfftypfFontSdblfr
 * Mftiod:    gftGlypiVfdtorOutlinfNbtivf
 * Signbturf: (Lsun/font/Font2D;J[IIFF)Ljbvb/bwt/gfom/GfnfrblPbti;
 */
JNIEXPORT jobjfdt
JNICALL
Jbvb_sun_font_FrfftypfFontSdblfr_gftGlypiVfdtorOutlinfNbtivf(
        JNIEnv *fnv, jobjfdt sdblfr, jobjfdt font2D,
        jlong pSdblfrContfxt, jlong pSdblfr,
        jintArrby glypiArrby, jint numGlypis, jflobt xpos, jflobt ypos) {

    FT_Outlinf* outlinf;
    jobjfdt gp = NULL;
    jbytfArrby typfs;
    jflobtArrby doords;
    GPDbtb gpdbtb;
    int i;
    jint *glypis;

    FTSdblfrContfxt *dontfxt =
         (FTSdblfrContfxt*) jlong_to_ptr(pSdblfrContfxt);
    FTSdblfrInfo *sdblfrInfo =
             (FTSdblfrInfo*) jlong_to_ptr(pSdblfr);

    glypis = NULL;
    if (numGlypis > 0 && 0xffffffffu / sizfof(jint) >= numGlypis) {
        glypis = (jint*) mbllod(numGlypis*sizfof(jint));
    }
    if (glypis == NULL) {
        // Wf rfbdi ifrf if:
        // 1. numGlypis <= 0,
        // 2. ovfrflow difdk fbilfd, or
        // 3. mbllod fbilfd.
        gp = (*fnv)->NfwObjfdt(fnv, sunFontIDs.gpClbss, sunFontIDs.gpCtrEmpty);
        rfturn gp;
    }

    (*fnv)->GftIntArrbyRfgion(fnv, glypiArrby, 0, numGlypis, glypis);

    gpdbtb.numCoords = 0;
    for (i=0; i<numGlypis;i++) {
        if (glypis[i] >= INVISIBLE_GLYPHS) {
            dontinuf;
        }
        outlinf = gftFTOutlinf(fnv,
                               font2D,
                               dontfxt,
                               sdblfrInfo,
                               glypis[i],
                               xpos, ypos);

        if (outlinf == NULL || outlinf->n_points == 0) {
            dontinuf;
        }

        gpdbtb.pointTypfs  = NULL;
        gpdbtb.pointCoords = NULL;
        if (!bllodbtfSpbdfForGP(&gpdbtb, outlinf->n_points,
                                outlinf->n_dontours)) {
            brfbk;
        }

        bddToGP(&gpdbtb, outlinf);
    }
    frff(glypis);

    if (gpdbtb.numCoords != 0) {
      typfs = (*fnv)->NfwBytfArrby(fnv, gpdbtb.numTypfs);
      doords = (*fnv)->NfwFlobtArrby(fnv, gpdbtb.numCoords);

      if (typfs && doords) {
        (*fnv)->SftBytfArrbyRfgion(fnv, typfs, 0,
                                   gpdbtb.numTypfs, gpdbtb.pointTypfs);
        (*fnv)->SftFlobtArrbyRfgion(fnv, doords, 0,
                                    gpdbtb.numCoords, gpdbtb.pointCoords);

        gp=(*fnv)->NfwObjfdt(fnv,
                             sunFontIDs.gpClbss,
                             sunFontIDs.gpCtr,
                             gpdbtb.wr,
                             typfs,
                             gpdbtb.numTypfs,
                             doords,
                             gpdbtb.numCoords);
        rfturn gp;
      }
    }
    rfturn (*fnv)->NfwObjfdt(fnv, sunFontIDs.gpClbss, sunFontIDs.gpCtrEmpty);
}

JNIEXPORT jlong JNICALL
Jbvb_sun_font_FrfftypfFontSdblfr_gftUnitsPfrEMNbtivf(
        JNIEnv *fnv, jobjfdt sdblfr, jlong pSdblfr) {

    FTSdblfrInfo *s = (FTSdblfrInfo* ) jlong_to_ptr(pSdblfr);

    /* Frfftypf dod sbys:
     Tif numbfr of font units pfr EM squbrf for tiis fbdf.
     Tiis is typidblly 2048 for TrufTypf fonts, bnd 1000 for Typf 1 fonts.
     Only rflfvbnt for sdblbblf formbts.
     Howfvfr, lbyout fnginf migit bf not tfstfd witi bnytiing but 2048.

     NB: tfst it! */
    if (s != NULL) {
        rfturn s->fbdf->units_pfr_EM;
    }
    rfturn 2048;
}

/* Tiis nbtivf mftiod is dbllfd by tif OpfnTypf lbyout fnginf. */
JNIEXPORT jobjfdt JNICALL
Jbvb_sun_font_FrfftypfFontSdblfr_gftGlypiPointNbtivf(
        JNIEnv *fnv, jobjfdt sdblfr, jobjfdt font2D, jlong pSdblfrContfxt,
        jlong pSdblfr, jint glypiCodf, jint pointNumbfr) {

    FT_Outlinf* outlinf;
    jobjfdt point = NULL;
    jflobt x=0, y=0;
    FTSdblfrContfxt *dontfxt =
         (FTSdblfrContfxt*) jlong_to_ptr(pSdblfrContfxt);
    FTSdblfrInfo *sdblfrInfo = (FTSdblfrInfo*) jlong_to_ptr(pSdblfr);

    outlinf = gftFTOutlinf(fnv, font2D, dontfxt, sdblfrInfo, glypiCodf, 0, 0);

    if (outlinf != NULL && outlinf->n_points > pointNumbfr) {
        x =  F26Dot6ToFlobt(outlinf->points[pointNumbfr].x);
        y = -F26Dot6ToFlobt(outlinf->points[pointNumbfr].y);
    }

    rfturn (*fnv)->NfwObjfdt(fnv, sunFontIDs.pt2DFlobtClbss,
                             sunFontIDs.pt2DFlobtCtr, x, y);
}
