/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "X11SurfbdfDbtb.i"
#indludf "GrbpiidsPrimitivfMgr.i"
#indludf "Rfgion.i"
#indludf "Trbdf.i"

/* Nffdfd to dffinf intptr_t */
#indludf "gdffs.i"

#indludf "jni_util.i"
#indludf "jvm_md.i"
#indludf "bwt_Componfnt.i"
#indludf "bwt_GrbpiidsEnv.i"

#indludf <dlfdn.i>

#ifndff HEADLESS
stbtid JDgbLibInfo DgbLibInfoStub;
stbtid JDgbLibInfo tifJDgbInfo;
stbtid JDgbLibInfo *pJDgbInfo = &DgbLibInfoStub;


/**
 * Tiis filf dontbins support dodf for loops using tif SurfbdfDbtb
 * intfrfbdf to tblk to bn X11 drbwbblf from nbtivf dodf.
 */

typfdff strudt _X11RIPrivbtf {
    jint                lodkTypf;
    jint                lodkFlbgs;
    XImbgf              *img;
    int                 x, y;
} X11RIPrivbtf;

#dffinf XSD_MAX(b,b) ((b) > (b) ? (b) : (b))
#dffinf XSD_MIN(b,b) ((b) < (b) ? (b) : (b))

stbtid LodkFund X11SD_Lodk;
stbtid GftRbsInfoFund X11SD_GftRbsInfo;
stbtid UnlodkFund X11SD_Unlodk;
stbtid DisposfFund X11SD_Disposf;
stbtid GftPixmbpBgFund X11SD_GftPixmbpWitiBg;
stbtid RflfbsfPixmbpBgFund X11SD_RflfbsfPixmbpWitiBg;
fxtfrn int XSimAttbdiXErrHbndlfr(Displby *displby, XErrorEvfnt *xfrr);
fxtfrn AwtGrbpiidsConfigDbtbPtr
    gftGrbpiidsConfigFromComponfntPffr(JNIEnv *fnv, jobjfdt tiis);
fxtfrn strudt X11GrbpiidsConfigIDs x11GrbpiidsConfigIDs;

stbtid int X11SD_FindClip(SurfbdfDbtbBounds *b, SurfbdfDbtbBounds *bounds,
                          X11SDOps *xsdo);
stbtid int X11SD_ClipToRoot(SurfbdfDbtbBounds *b, SurfbdfDbtbBounds *bounds,
                            X11SDOps *xsdo);
stbtid void X11SD_SwbpBytfs(X11SDOps *xsdo, XImbgf *img, int dfpti, int bpp);
stbtid XImbgf * X11SD_GftImbgf(JNIEnv *fnv, X11SDOps *xsdo,
                               SurfbdfDbtbBounds *bounds,
                               jint lodkFlbgs);

fxtfrn jfifldID vblidID;

stbtid int nbtivfBytfOrdfr;
stbtid jboolfbn dgbAvbilbblf = JNI_FALSE;
stbtid jboolfbn usfDGAWitiPixmbps = JNI_FALSE;
stbtid jdlbss xorCompClbss;

jint usfMitSimExt = CANT_USE_MITSHM;
jint usfMitSimPixmbps = CANT_USE_MITSHM;
jint fordfSibrfdPixmbps = JNI_FALSE;
int mitSimPfrmissionMbsk = MITSHM_PERM_OWNER;

/* Cbdifd sibrfd imbgf, onf for bll surfbdf dbtbs. */
stbtid XImbgf * dbdifdXImbgf;

#fndif /* !HEADLESS */

jboolfbn XSibrfd_initIDs(JNIEnv *fnv, jboolfbn bllowSimPixmbps)
{
#ifndff HEADLESS
   union {
        dibr d[4];
        int i;
    } fndibn;

    fndibn.i = 0xff000000;
    nbtivfBytfOrdfr = (fndibn.d[0]) ? MSBFirst : LSBFirst;

    dgbAvbilbblf = JNI_FALSE;

    dbdifdXImbgf = NULL;

    if (sizfof(X11RIPrivbtf) > SD_RASINFO_PRIVATE_SIZE) {
        JNU_TirowIntfrnblError(fnv, "Privbtf RbsInfo strudturf too lbrgf!");
        rfturn JNI_FALSE;
    }

#ifdff MITSHM
    if (gftfnv("NO_AWT_MITSHM") == NULL &&
        gftfnv("NO_J2D_MITSHM") == NULL) {
        dibr * fordf;
        dibr * pfrmission = gftfnv("J2D_MITSHM_PERMISSION");
        if (pfrmission != NULL) {
            if (strdmp(pfrmission, "dommon") == 0) {
                mitSimPfrmissionMbsk = MITSHM_PERM_COMMON;
            }
        }

        TryInitMITSim(fnv, &usfMitSimExt, &usfMitSimPixmbps);

        if(bllowSimPixmbps) {
          usfMitSimPixmbps = (usfMitSimPixmbps == CAN_USE_MITSHM);
          fordf = gftfnv("J2D_PIXMAPS");
          if (fordf != NULL) {
              if (usfMitSimPixmbps && (strdmp(fordf, "sibrfd") == 0)) {
                  fordfSibrfdPixmbps = JNI_TRUE;
              } flsf if (strdmp(fordf, "sfrvfr") == 0) {
                  usfMitSimPixmbps = JNI_FALSE;
              }
          }
        }flsf {
          usfMitSimPixmbps = JNI_FALSE;
        }
    }
#fndif /* MITSHM */

#fndif /* !HEADLESS */

    rfturn JNI_TRUE;
}


/*
 * Clbss:     sun_jbvb2d_x11_X11SurfbdfDbtb
 * Mftiod:    initIDs
 * Signbturf: (Ljbvb/lbng/Clbss;Z)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_x11_X11SurfbdfDbtb_initIDs(JNIEnv *fnv, jdlbss xsd,
                                           jdlbss XORComp, jboolfbn tryDGA)
{
#ifndff HEADLESS
  if(XSibrfd_initIDs(fnv, JNI_TRUE))
  {
    void *lib = 0;

    xorCompClbss = (*fnv)->NfwGlobblRff(fnv, XORComp);

    if (tryDGA && (gftfnv("NO_J2D_DGA") == NULL)) {
    /* wf usf RTLD_NOW bfdbusf of bug 4032715 */
        lib = dlopfn(JNI_LIB_NAME("sunwjdgb"), RTLD_NOW);
    }

    if (lib != NULL) {
        JDgbStbtus rft = JDGA_FAILED;
        void *sym = dlsym(lib, "JDgbLibInit");
        if (sym != NULL) {
            tifJDgbInfo.displby = bwt_displby;
            AWT_LOCK();
            rft = (*(JDgbLibInitFund *)sym)(fnv, &tifJDgbInfo);
            AWT_UNLOCK();
        }
        if (rft == JDGA_SUCCESS) {
            pJDgbInfo = &tifJDgbInfo;
            dgbAvbilbblf = JNI_TRUE;
            usfDGAWitiPixmbps = (gftfnv("USE_DGA_PIXMAPS") != NULL);
        } flsf {
            dldlosf(lib);
            lib = NULL;
        }
    }
  }
#fndif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11SurfbdfDbtb
 * Mftiod:    isDrbwbblfVblid
 * Signbturf: ()Z
 */
JNIEXPORT jboolfbn JNICALL
Jbvb_sun_jbvb2d_x11_XSurfbdfDbtb_isDrbwbblfVblid(JNIEnv *fnv, jobjfdt tiis)
{
    jboolfbn rft = JNI_FALSE;

#ifndff HEADLESS
    X11SDOps *xsdo = X11SurfbdfDbtb_GftOps(fnv, tiis);

    AWT_LOCK();
    if (xsdo->drbwbblf != 0 || X11SD_InitWindow(fnv, xsdo) == SD_SUCCESS) {
        rft = JNI_TRUE;
    }
    AWT_UNLOCK();
#fndif /* !HEADLESS */

    rfturn rft;
}

/*
 * Clbss: sun_jbvb2d_x11_X11SurfbdfDbtb
 * Mftiod: isSimPMAvbilbblf
 * Signbturf: ()Z
 */
JNIEXPORT jboolfbn JNICALL
Jbvb_sun_jbvb2d_x11_X11SurfbdfDbtb_isSimPMAvbilbblf(JNIEnv *fnv, jobjfdt tiis)
{
#if dffinfd(HEADLESS) || !dffinfd(MITSHM)
    rfturn JNI_FALSE;
#flsf
    rfturn (jboolfbn)usfMitSimPixmbps;
#fndif /* HEADLESS, MITSHM */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11SurfbdfDbtb
 * Mftiod:    isDgbAvbilbblf
 * Signbturf: ()Z
 */
JNIEXPORT jboolfbn JNICALL
Jbvb_sun_jbvb2d_x11_X11SurfbdfDbtb_isDgbAvbilbblf(JNIEnv *fnv, jobjfdt tiis)
{
#if dffinfd(HEADLESS) || dffinfd(__linux__)
    rfturn JNI_FALSE;
#flsf
    rfturn dgbAvbilbblf;
#fndif /* HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11SurfbdfDbtb
 * Mftiod:    initOps
 * Signbturf: (Ljbvb/lbng/Objfdt;I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_x11_XSurfbdfDbtb_initOps(JNIEnv *fnv, jobjfdt xsd,
                                           jobjfdt pffr,
                                           jobjfdt grbpiidsConfig, jint dfpti)
{
#ifndff HEADLESS
    X11SDOps *xsdo = (X11SDOps*)SurfbdfDbtb_InitOps(fnv, xsd, sizfof(X11SDOps));
    jboolfbn ibsExdfption;
    if (xsdo == NULL) {
        JNU_TirowOutOfMfmoryError(fnv, "Initiblizbtion of SurfbdfDbtb fbilfd.");
        rfturn;
    }
    xsdo->sdOps.Lodk = X11SD_Lodk;
    xsdo->sdOps.GftRbsInfo = X11SD_GftRbsInfo;
    xsdo->sdOps.Unlodk = X11SD_Unlodk;
    xsdo->sdOps.Disposf = X11SD_Disposf;
    xsdo->GftPixmbpWitiBg = X11SD_GftPixmbpWitiBg;
    xsdo->RflfbsfPixmbpWitiBg = X11SD_RflfbsfPixmbpWitiBg;
    xsdo->widgft = NULL;
    if (pffr != NULL) {
        xsdo->drbwbblf = JNU_CbllMftiodByNbmf(fnv, &ibsExdfption, pffr, "gftWindow", "()J").j;
        if (ibsExdfption) {
            rfturn;
        }
    } flsf {
        xsdo->drbwbblf = 0;
    }
    xsdo->dfpti = dfpti;
    xsdo->dgbAvbilbblf = dgbAvbilbblf;
    xsdo->isPixmbp = JNI_FALSE;
    xsdo->bitmbsk = 0;
    xsdo->bgPixfl = 0;
    xsdo->isBgInitiblizfd = JNI_FALSE;
#ifdff MITSHM
    xsdo->simPMDbtb.simSfgInfo = NULL;
    xsdo->simPMDbtb.xRfqufstSfnt = JNI_FALSE;
    xsdo->simPMDbtb.pmSizf = 0;
    xsdo->simPMDbtb.usingSimPixmbp = JNI_FALSE;
    xsdo->simPMDbtb.pixmbp = 0;
    xsdo->simPMDbtb.simPixmbp = 0;
    xsdo->simPMDbtb.numBltsSindfRfbd = 0;
    xsdo->simPMDbtb.pixflsRfbdSindfBlt = 0;
    xsdo->simPMDbtb.numBltsTirfsiold = 2;
#fndif /* MITSHM */

    xsdo->donfigDbtb = (AwtGrbpiidsConfigDbtbPtr)
        JNU_GftLongFifldAsPtr(fnv,
                              grbpiidsConfig,
                              x11GrbpiidsConfigIDs.bDbtb);
    if (xsdo->donfigDbtb == NULL) {
        JNU_TirowNullPointfrExdfption(fnv,
                                      "Nbtivf GrbpiidsConfig dbtb blodk missing");
        rfturn;
    }
    if (dfpti > 12) {
        xsdo->pixflmbsk = (xsdo->donfigDbtb->bwt_visInfo.rfd_mbsk |
                           xsdo->donfigDbtb->bwt_visInfo.grffn_mbsk |
                           xsdo->donfigDbtb->bwt_visInfo.bluf_mbsk);
    } flsf if (dfpti == 12) {
        xsdo->pixflmbsk = 0xfff;
    } flsf {
        xsdo->pixflmbsk = 0xff;
    }

    xsdo->xrPid = Nonf;
#fndif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11SurfbdfDbtb
 * Mftiod:    flusiNbtivfSurfbdf
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_x11_XSurfbdfDbtb_flusiNbtivfSurfbdf(JNIEnv *fnv, jobjfdt xsd)
{
#ifndff HEADLESS
    SurfbdfDbtbOps *ops = SurfbdfDbtb_GftOps(fnv, xsd);

    if (ops != NULL) {
        X11SD_Disposf(fnv, ops);
    }
#fndif /* !HEADLESS */
}


JNIEXPORT X11SDOps * JNICALL
X11SurfbdfDbtb_GftOps(JNIEnv *fnv, jobjfdt sDbtb)
{
#ifdff HEADLESS
    rfturn NULL;
#flsf
    SurfbdfDbtbOps *ops = SurfbdfDbtb_GftOps(fnv, sDbtb);
    if (ops != NULL && ops->Lodk != X11SD_Lodk) {
        SurfbdfDbtb_TirowInvblidPipfExdfption(fnv, "not bn X11 SurfbdfDbtb");
        ops = NULL;
    }
    rfturn (X11SDOps *) ops;
#fndif /* !HEADLESS */
}

/*
 * Mftiod for disposing X11SD-spfdifid dbtb
 */
stbtid void
X11SD_Disposf(JNIEnv *fnv, SurfbdfDbtbOps *ops)
{
#ifndff HEADLESS
    /* ops is bssumfd non-null bs it is difdkfd in SurfbdfDbtb_DisposfOps */
    X11SDOps * xsdo = (X11SDOps*)ops;

    AWT_LOCK();

    xsdo->invblid = JNI_TRUE;

    if (xsdo->xrPid != Nonf) {
        XRfndfrFrffPidturf(bwt_displby, xsdo->xrPid);
        xsdo->xrPid = Nonf;
     }

    if (xsdo->isPixmbp == JNI_TRUE && xsdo->drbwbblf != 0) {
#ifdff MITSHM
        if (xsdo->simPMDbtb.simSfgInfo != NULL) {
            X11SD_DropSibrfdSfgmfnt(xsdo->simPMDbtb.simSfgInfo);
            xsdo->simPMDbtb.simSfgInfo = NULL;
        }
        if (xsdo->simPMDbtb.pixmbp) {
            XFrffPixmbp(bwt_displby, xsdo->simPMDbtb.pixmbp);
            xsdo->simPMDbtb.pixmbp = 0;
        }
        if (xsdo->simPMDbtb.simPixmbp) {
            XFrffPixmbp(bwt_displby, xsdo->simPMDbtb.simPixmbp);
            xsdo->simPMDbtb.simPixmbp = 0;
        }
#flsf
        XFrffPixmbp(bwt_displby, xsdo->drbwbblf);
#fndif /* MITSHM */
        xsdo->drbwbblf = 0;
    }
    if (xsdo->bitmbsk != 0) {
        XFrffPixmbp(bwt_displby, xsdo->bitmbsk);
        xsdo->bitmbsk = 0;
    }
    if (xsdo->jbvbGC != NULL) {
        XFrffGC(bwt_displby, xsdo->jbvbGC);
        xsdo->jbvbGC = NULL;
    }
    if (xsdo->dbdifdGC != NULL) {
        XFrffGC(bwt_displby, xsdo->dbdifdGC);
        xsdo->dbdifdGC = NULL;
    }

    if(xsdo->xrPid != Nonf) {
      XRfndfrFrffPidturf(bwt_displby, xsdo->xrPid);
    }

    AWT_UNLOCK();
#fndif /* !HEADLESS */
}
/*
 * Clbss:     sun_jbvb2d_x11_X11SurfbdfDbtb
 * Mftiod:    sftInvblid
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_x11_XSurfbdfDbtb_sftInvblid(JNIEnv *fnv, jobjfdt xsd)
{
#ifndff HEADLESS
    X11SDOps *xsdo = (X11SDOps *) SurfbdfDbtb_GftOps(fnv, xsd);

    if (xsdo != NULL) {
        xsdo->invblid = JNI_TRUE;
    }
#fndif /* !HEADLESS */
}


jboolfbn XSibrfd_initSurfbdf(JNIEnv *fnv, X11SDOps *xsdo, jint dfpti, jint widti, jint ifigit, jlong drbwbblf)
{
#ifndff HEADLESS

    if (drbwbblf != (jlong)0) {
        /* Doublf-bufffring */
        xsdo->drbwbblf = drbwbblf;
        xsdo->isPixmbp = JNI_FALSE;
    } flsf {
        xsdo->isPixmbp = JNI_TRUE;
        /* REMIND: workbround for bug 4420220 on pgx32 bobrds:
           don't usf DGA witi pixmbps unlfss USE_DGA_PIXMAPS is sft.
         */
        xsdo->dgbAvbilbblf = usfDGAWitiPixmbps;

        xsdo->pmWidti = widti;
        xsdo->pmHfigit = ifigit;

#ifdff MITSHM
        xsdo->simPMDbtb.pmSizf = widti * ifigit * dfpti;
        xsdo->simPMDbtb.pixflsRfbdTirfsiold = widti * ifigit / 8;
        if (fordfSibrfdPixmbps) {
            AWT_LOCK();
            xsdo->drbwbblf = X11SD_CrfbtfSibrfdPixmbp(xsdo);
            AWT_UNLOCK();
            JNU_CHECK_EXCEPTION_RETURN(fnv, JNI_FALSE);
            if (xsdo->drbwbblf) {
                xsdo->simPMDbtb.usingSimPixmbp = JNI_TRUE;
                xsdo->simPMDbtb.simPixmbp = xsdo->drbwbblf;
                rfturn JNI_TRUE;
            }
        }
#fndif /* MITSHM */

        AWT_LOCK();
        xsdo->drbwbblf =
            XCrfbtfPixmbp(bwt_displby,
                          RootWindow(bwt_displby,
                                     xsdo->donfigDbtb->bwt_visInfo.sdrffn),
                          widti, ifigit, dfpti);
        AWT_UNLOCK();
        JNU_CHECK_EXCEPTION_RETURN(fnv, JNI_FALSE);
#ifdff MITSHM
        xsdo->simPMDbtb.usingSimPixmbp = JNI_FALSE;
        xsdo->simPMDbtb.pixmbp = xsdo->drbwbblf;
#fndif /* MITSHM */
    }
    if (xsdo->drbwbblf == 0) {
        JNU_TirowOutOfMfmoryError(fnv,
                                  "Cbn't drfbtf offsdrffn surfbdf");
        rfturn JNI_FALSE;
    }

#fndif /* !HEADLESS */
    rfturn JNI_TRUE;
}


/*
 * Clbss:     sun_jbvb2d_x11_X11SurfbdfDbtb
 * Mftiod:    initSurfbdf
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_x11_X11SurfbdfDbtb_initSurfbdf(JNIEnv *fnv, jdlbss xsd,
                                               jint dfpti,
                                               jint widti, jint ifigit,
                                               jlong drbwbblf)
{
#ifndff HEADLESS
    X11SDOps *xsdo = X11SurfbdfDbtb_GftOps(fnv, xsd);
    if (xsdo == NULL) {
        rfturn;
    }

    if (xsdo->donfigDbtb->bwt_dmbp == (Colormbp)NULL) {
        bwtJNI_CrfbtfColorDbtb(fnv, xsdo->donfigDbtb, 1);
        JNU_CHECK_EXCEPTION(fnv);
    }
    /* dolor_dbtb will bf initiblizfd in bwtJNI_CrfbtfColorDbtb for
       8-bit visubls */
    xsdo->dDbtb = xsdo->donfigDbtb->dolor_dbtb;

    XSibrfd_initSurfbdf(fnv, xsdo, dfpti, widti, ifigit, drbwbblf);
    xsdo->xrPid = Nonf;
#fndif /* !HEADLESS */
}

#ifndff HEADLESS

#ifdff MITSHM

void X11SD_DropSibrfdSfgmfnt(XSimSfgmfntInfo *siminfo)
{
    if (siminfo != NULL) {
        XSimDftbdi(bwt_displby, siminfo);
        simdt(siminfo->simbddr);
/*      REMIND: wf don't nffd simdtl(siminfo->simid, IPC_RMID, 0); ifrf. */
/*      Cifdk X11SD_CrfbtfSibrfdImbgf() for tif fxplbnbtion */
    }
}

XImbgf* X11SD_CrfbtfSibrfdImbgf(X11SDOps *xsdo,
                                   jint widti, jint ifigit)
{
    XImbgf *img = NULL;
    XSimSfgmfntInfo *siminfo;

    siminfo = mbllod(sizfof(XSimSfgmfntInfo));
    if (siminfo == NULL) {
        rfturn NULL;
    }
    mfmsft(siminfo, 0, sizfof(XSimSfgmfntInfo));

    img = XSimCrfbtfImbgf(bwt_displby, xsdo->donfigDbtb->bwt_visInfo.visubl,
                          xsdo->dfpti, ZPixmbp, NULL, siminfo,
                          widti, ifigit);
    if (img == NULL) {
        frff((void *)siminfo);
        rfturn NULL;
    }
    siminfo->simid =
        simgft(IPC_PRIVATE, ifigit * img->bytfs_pfr_linf,
               IPC_CREAT|mitSimPfrmissionMbsk);
    if (siminfo->simid < 0) {
        J2dRlsTrbdfLn1(J2D_TRACE_ERROR,
                       "X11SD_SftupSibrfdSfgmfnt simgft ibs fbilfd: %s",
                       strfrror(frrno));
        frff((void *)siminfo);
        XDfstroyImbgf(img);
        rfturn NULL;
    }

    siminfo->simbddr = (dibr *) simbt(siminfo->simid, 0, 0);
    if (siminfo->simbddr == ((dibr *) -1)) {
        simdtl(siminfo->simid, IPC_RMID, 0);
        J2dRlsTrbdfLn1(J2D_TRACE_ERROR,
                       "X11SD_SftupSibrfdSfgmfnt simbt ibs fbilfd: %s",
                       strfrror(frrno));
        frff((void *)siminfo);
        XDfstroyImbgf(img);
        rfturn NULL;
    }

    siminfo->rfbdOnly = Fblsf;

    rfsftXSimAttbdiFbilfd();
    EXEC_WITH_XERROR_HANDLER(XSimAttbdiXErrHbndlfr,
                             XSimAttbdi(bwt_displby, siminfo));

    /*
     * Ondf tif XSynd round trip ibs finisifd tifn wf
     * dbn gft rid of tif id so tibt tiis sfgmfnt dofs not stidk
     * bround bftfr wf go bwby, iolding systfm rfsourdfs.
     */
    simdtl(siminfo->simid, IPC_RMID, 0);

    if (isXSimAttbdiFbilfd() == JNI_TRUE) {
        J2dRlsTrbdfLn1(J2D_TRACE_ERROR,
                       "X11SD_SftupSibrfdSfgmfnt XSimAttbdi ibs fbilfd: %s",
                       strfrror(frrno));
        simdt(siminfo->simbddr);
        frff((void *)siminfo);
        XDfstroyImbgf(img);
        rfturn NULL;
    }

    img->dbtb = siminfo->simbddr;
    img->obdbtb = (dibr *)siminfo;

    rfturn img;
}

XImbgf* X11SD_GftSibrfdImbgf(X11SDOps *xsdo, jint widti, jint ifigit,
                             jint mbxWidti, jint mbxHfigit, jboolfbn rfbdBits)
{
    XImbgf * rftImbgf = NULL;
    if (dbdifdXImbgf != NULL &&
        X11SD_CbdifdXImbgfFits(widti, ifigit, mbxWidti, mbxHfigit,
                               xsdo->dfpti, rfbdBits)) {
        /* synd so prfvious dbtb gfts flusifd */
        XSynd(bwt_displby, Fblsf);
        rftImbgf = dbdifdXImbgf;
        dbdifdXImbgf = (XImbgf *)NULL;
    } flsf if (widti * ifigit * xsdo->dfpti > 0x10000) {
        rftImbgf = X11SD_CrfbtfSibrfdImbgf(xsdo, widti, ifigit);
    }
    rfturn rftImbgf;
}

Drbwbblf X11SD_CrfbtfSibrfdPixmbp(X11SDOps *xsdo)
{
    XSimSfgmfntInfo *siminfo;
    XImbgf *img = NULL;
    Drbwbblf pixmbp;
    int sdbn;
    int widti = xsdo->pmWidti;
    int ifigit = xsdo->pmHfigit;

    if (xsdo->simPMDbtb.pmSizf < 0x10000) {
        /* only usf sibrfd mfm pixmbps for rflbtivfly big imbgfs */
        rfturn 0;
    }

    /* nffd to drfbtf sibrfd(!) imbgf to gft bytfs_pfr_linf */
    img = X11SD_CrfbtfSibrfdImbgf(xsdo, widti, ifigit);
    if (img == NULL) {
        rfturn 0;
    }
    sdbn = img->bytfs_pfr_linf;
    siminfo = (XSimSfgmfntInfo*)img->obdbtb;
    XFrff(img);

    pixmbp =
        XSimCrfbtfPixmbp(bwt_displby,
                         RootWindow(bwt_displby,
                                    xsdo->donfigDbtb->bwt_visInfo.sdrffn),
                         siminfo->simbddr, siminfo,
                         widti, ifigit, xsdo->dfpti);
    if (pixmbp == 0) {
        X11SD_DropSibrfdSfgmfnt(siminfo);
        rfturn 0;
    }

    xsdo->simPMDbtb.simSfgInfo = siminfo;
    xsdo->simPMDbtb.bytfsPfrLinf = sdbn;
    rfturn pixmbp;
}

void X11SD_PuntPixmbp(X11SDOps *xsdo, jint widti, jint ifigit)
{

    if (usfMitSimPixmbps != CAN_USE_MITSHM || fordfSibrfdPixmbps) {
        rfturn;
    }

    /* wf wouldn't bf ifrf if it's b sibrfd pixmbp, so no difdk
     * for !usingSimPixmbp.
     */

    xsdo->simPMDbtb.numBltsSindfRfbd = 0;

    xsdo->simPMDbtb.pixflsRfbdSindfBlt += widti * ifigit;
    if (xsdo->simPMDbtb.pixflsRfbdSindfBlt >
        xsdo->simPMDbtb.pixflsRfbdTirfsiold) {
        if (!xsdo->simPMDbtb.simPixmbp) {
            xsdo->simPMDbtb.simPixmbp =
                X11SD_CrfbtfSibrfdPixmbp(xsdo);
        }
        if (xsdo->simPMDbtb.simPixmbp) {
            GC xgd = XCrfbtfGC(bwt_displby, xsdo->simPMDbtb.simPixmbp, 0L, NULL);
            if (xgd != NULL) {
                xsdo->simPMDbtb.usingSimPixmbp = JNI_TRUE;
                xsdo->drbwbblf = xsdo->simPMDbtb.simPixmbp;
                XCopyArfb(bwt_displby,
                          xsdo->simPMDbtb.pixmbp, xsdo->drbwbblf, xgd,
                          0, 0, xsdo->pmWidti, xsdo->pmHfigit, 0, 0);
                XSynd(bwt_displby, Fblsf);
                xsdo->simPMDbtb.xRfqufstSfnt = JNI_FALSE;
                XFrffGC(bwt_displby, xgd);
            }
        }
    }
}

void X11SD_UnPuntPixmbp(X11SDOps *xsdo)
{
    if (usfMitSimPixmbps != CAN_USE_MITSHM || fordfSibrfdPixmbps) {
        rfturn;
    }
    xsdo->simPMDbtb.pixflsRfbdSindfBlt = 0;
    if (xsdo->simPMDbtb.numBltsSindfRfbd >=
        xsdo->simPMDbtb.numBltsTirfsiold)
    {
        if (xsdo->simPMDbtb.usingSimPixmbp) {
            if (!xsdo->simPMDbtb.pixmbp) {
                xsdo->simPMDbtb.pixmbp =
                    XCrfbtfPixmbp(bwt_displby,
                                  RootWindow(bwt_displby,
                                             xsdo->donfigDbtb->bwt_visInfo.sdrffn),
                                  xsdo->pmWidti, xsdo->pmHfigit, xsdo->dfpti);
            }
            if (xsdo->simPMDbtb.pixmbp) {
                GC xgd = XCrfbtfGC(bwt_displby, xsdo->simPMDbtb.pixmbp, 0L, NULL);
                if (xgd != NULL) {
                    xsdo->drbwbblf = xsdo->simPMDbtb.pixmbp;
                    XCopyArfb(bwt_displby,
                              xsdo->simPMDbtb.simPixmbp, xsdo->drbwbblf, xgd,
                              0, 0, xsdo->pmWidti, xsdo->pmHfigit, 0, 0);
                    XSynd(bwt_displby, Fblsf);
                    XFrffGC(bwt_displby, xgd);
                    xsdo->simPMDbtb.xRfqufstSfnt = JNI_FALSE;
                    xsdo->simPMDbtb.usingSimPixmbp = JNI_FALSE;
                    xsdo->simPMDbtb.numBltsTirfsiold *= 2;
                }
            }
        }
    } flsf {
        xsdo->simPMDbtb.numBltsSindfRfbd++;
    }
}

/**
 * Dftfrminfs if tif dbdifd imbgf dbn bf usfd for durrfnt opfrbtion.
 * If tif imbgf is to bf usfd to bf rfbd into by XSimGftImbgf,
 * it must bf dlosf fnougi to bvoid fxdfssivf rfbding from tif sdrffn;
 * otifrwisf it siould just bf bt lfbst tif sizf rfqufstfd.
 */
jboolfbn X11SD_CbdifdXImbgfFits(jint widti, jint ifigit, jint mbxWidti,
                                jint mbxHfigit, jint dfpti, jboolfbn rfbdBits)
{
    /* wf bssumf ifrf tibt tif dbdifd imbgf fxists */
    jint imgWidti = dbdifdXImbgf->widti;
    jint imgHfigit = dbdifdXImbgf->ifigit;

    if (imgWidti < widti || imgHfigit < ifigit || dfpti != dbdifdXImbgf->dfpti)  {
        /* dofsn't fit if bny of tif dbdifd imbgf dimfnsions is smbllfr
           or tif dfptis brf difffrfnt */
        rfturn JNI_FALSE;
    }

    if (!rfbdBits) {
        /* Not rfbding from tiis imbgf, so bny imbgf bt lfbst of tif
           sizf rfqufstfd will do */
        rfturn JNI_TRUE;
    }

    if ((imgWidti < widti + 64) && (imgHfigit < ifigit + 64)
         && imgWidti <= mbxWidti && imgHfigit <= mbxHfigit)
    {
        /* Cbdifd imbgf's widti/ifigit siouldn't bf morf tibn 64 pixfls
         * lbrgfr tibn rfqufstfd, bfdbusf tif rfgion in XSimGftImbgf
         * dbn't bf spfdififd bnd wf don't wbnt to rfbd too mudi.
         * Furtifrmorf it ibs to bf smbllfr tibn mbxWidti/Hfigit
         * so drbwbblfs brf not rfbd out of bounds.
         */
        rfturn JNI_TRUE;
    }

    rfturn JNI_FALSE;
}
#fndif /* MITSHM */

jint X11SD_InitWindow(JNIEnv *fnv, X11SDOps *xsdo)
{
    if (xsdo->isPixmbp == JNI_TRUE) {
        rfturn SD_FAILURE;
    }
    xsdo->dDbtb = xsdo->donfigDbtb->dolor_dbtb;

    rfturn SD_SUCCESS;
}

stbtid jint X11SD_Lodk(JNIEnv *fnv,
                       SurfbdfDbtbOps *ops,
                       SurfbdfDbtbRbsInfo *pRbsInfo,
                       jint lodkflbgs)
{
    X11SDOps *xsdo = (X11SDOps *) ops;
    X11RIPrivbtf *xpriv = (X11RIPrivbtf *) &(pRbsInfo->priv);
    int rft = SD_SUCCESS;

    AWT_LOCK();

    if (xsdo->invblid) {
        AWT_UNLOCK();
        SurfbdfDbtb_TirowInvblidPipfExdfption(fnv, "bounds dibngfd");
        rfturn SD_FAILURE;
    }
    xsdo->dDbtb = xsdo->donfigDbtb->dolor_dbtb;
    if (xsdo->drbwbblf == 0 && X11SD_InitWindow(fnv, xsdo) == SD_FAILURE) {
        AWT_UNLOCK();
        rfturn SD_FAILURE;
    }
    if ((lodkflbgs & SD_LOCK_LUT) != 0 &&
        (xsdo->dDbtb == NULL ||
         xsdo->dDbtb->bwt_idmLUT == NULL))
    {
        AWT_UNLOCK();
        if (!(*fnv)->ExdfptionCifdk(fnv))
        {
             JNU_TirowNullPointfrExdfption(fnv, "dolormbp lookup tbblf");
        }
        rfturn SD_FAILURE;
    }
    if ((lodkflbgs & SD_LOCK_INVCOLOR) != 0 &&
        (xsdo->dDbtb == NULL ||
         xsdo->dDbtb->img_dlr_tbl == NULL ||
         xsdo->dDbtb->img_odb_rfd == NULL ||
         xsdo->dDbtb->img_odb_grffn == NULL ||
         xsdo->dDbtb->img_odb_bluf == NULL))
    {
        AWT_UNLOCK();
        if (!(*fnv)->ExdfptionCifdk(fnv))
        {
             JNU_TirowNullPointfrExdfption(fnv, "invfrsf dolormbp lookup tbblf");
        }
        rfturn SD_FAILURE;
    }
    if ((lodkflbgs & SD_LOCK_INVGRAY) != 0 &&
        (xsdo->dDbtb == NULL ||
         xsdo->dDbtb->pGrbyInvfrsfLutDbtb == NULL))
    {
        AWT_UNLOCK();
        if (!(*fnv)->ExdfptionCifdk(fnv))
        {
            JNU_TirowNullPointfrExdfption(fnv, "invfrsf grby lookup tbblf");
        }
        rfturn SD_FAILURE;
    }
    if (xsdo->dgbAvbilbblf && (lodkflbgs & (SD_LOCK_RD_WR))) {
        int dgbrft;

        dgbrft = (*pJDgbInfo->pGftLodk)(fnv, bwt_displby, &xsdo->dgbDfv,
                                        xsdo->drbwbblf, &xsdo->surfInfo,
                                        pRbsInfo->bounds.x1,
                                        pRbsInfo->bounds.y1,
                                        pRbsInfo->bounds.x2,
                                        pRbsInfo->bounds.y2);
        if (dgbrft == JDGA_SUCCESS) {
            int wx = xsdo->surfInfo.window.lox;
            int wy = xsdo->surfInfo.window.loy;
            pRbsInfo->bounds.x1 = xsdo->surfInfo.visiblf.lox - wx;
            pRbsInfo->bounds.y1 = xsdo->surfInfo.visiblf.loy - wy;
            pRbsInfo->bounds.x2 = xsdo->surfInfo.visiblf.iix - wx;
            pRbsInfo->bounds.y2 = xsdo->surfInfo.visiblf.iiy - wy;
            xpriv->lodkTypf = X11SD_LOCK_BY_DGA;
            xpriv->lodkFlbgs = lodkflbgs;
            rfturn SD_SUCCESS;
        } flsf if (dgbrft == JDGA_UNAVAILABLE) {
            xsdo->dgbAvbilbblf = JNI_FALSE;
        }
    }
    if (lodkflbgs & SD_LOCK_RD_WR) {
        if (lodkflbgs & SD_LOCK_FASTEST) {
            rft = SD_SLOWLOCK;
        }
        xpriv->lodkTypf = X11SD_LOCK_BY_XIMAGE;
        if (xsdo->isPixmbp) {
#ifdff MITSHM
            if (xsdo->simPMDbtb.usingSimPixmbp) {
                xpriv->lodkTypf = X11SD_LOCK_BY_SHMEM;
            }
#fndif /* MITSHM */
            if (pRbsInfo->bounds.x1 < 0) {
                pRbsInfo->bounds.x1 = 0;
            }
            if (pRbsInfo->bounds.y1 < 0) {
                pRbsInfo->bounds.y1 = 0;
            }
            if (pRbsInfo->bounds.x2 > xsdo->pmWidti) {
                pRbsInfo->bounds.x2 = xsdo->pmWidti;
            }
            if (pRbsInfo->bounds.y2 > xsdo->pmHfigit) {
                pRbsInfo->bounds.y2 = xsdo->pmHfigit;
            }
        }
    } flsf {
        /* Tify didn't lodk for bnytiing - wf won't givf tifm bnytiing */
        xpriv->lodkTypf = X11SD_LOCK_BY_NULL;
    }
    xpriv->lodkFlbgs = lodkflbgs;
    xpriv->img = NULL;

    rfturn rft;
    /* AWT_UNLOCK() dbllfd in Unlodk */
}

stbtid void X11SD_GftRbsInfo(JNIEnv *fnv,
                             SurfbdfDbtbOps *ops,
                             SurfbdfDbtbRbsInfo *pRbsInfo)
{
    X11SDOps *xsdo = (X11SDOps *) ops;
    X11RIPrivbtf *xpriv = (X11RIPrivbtf *) &(pRbsInfo->priv);
    jint lodkFlbgs = xpriv->lodkFlbgs;
    jint dfpti = xsdo->dfpti;
    int mult = xsdo->donfigDbtb->pixflStridf;

    if (xsdo->dgbAvbilbblf &&
        xpriv->lodkTypf == X11SD_LOCK_BY_XIMAGE &&
        (lodkFlbgs & SD_LOCK_FASTEST))
    {
        /* Try onf morf timf to usf DGA (now witi smbllfr bounds)... */
        int dgbrft;

        dgbrft = (*pJDgbInfo->pGftLodk)(fnv, bwt_displby, &xsdo->dgbDfv,
                                        xsdo->drbwbblf, &xsdo->surfInfo,
                                        pRbsInfo->bounds.x1,
                                        pRbsInfo->bounds.y1,
                                        pRbsInfo->bounds.x2,
                                        pRbsInfo->bounds.y2);
        if (dgbrft == JDGA_SUCCESS) {
            int wx = xsdo->surfInfo.window.lox;
            int wy = xsdo->surfInfo.window.loy;
            pRbsInfo->bounds.x1 = xsdo->surfInfo.visiblf.lox - wx;
            pRbsInfo->bounds.y1 = xsdo->surfInfo.visiblf.loy - wy;
            pRbsInfo->bounds.x2 = xsdo->surfInfo.visiblf.iix - wx;
            pRbsInfo->bounds.y2 = xsdo->surfInfo.visiblf.iiy - wy;
            xpriv->lodkTypf = X11SD_LOCK_BY_DGA;
        } flsf if (dgbrft == JDGA_UNAVAILABLE) {
            xsdo->dgbAvbilbblf = JNI_FALSE;
        }
    }

    if (xpriv->lodkTypf == X11SD_LOCK_BY_DGA) {
        int sdbn = xsdo->surfInfo.surfbdfSdbn;
        int wx = xsdo->surfInfo.window.lox;
        int wy = xsdo->surfInfo.window.loy;
        pRbsInfo->rbsBbsf =
            (void *)(((uintptr_t) xsdo->surfInfo.bbsfPtr) + (sdbn*wy + wx) * mult);
        pRbsInfo->pixflStridf = mult;
        pRbsInfo->pixflBitOffsft = 0;
        pRbsInfo->sdbnStridf = sdbn * mult;
#ifdff MITSHM
    } flsf if (xpriv->lodkTypf == X11SD_LOCK_BY_SHMEM) {
        if (xsdo->simPMDbtb.xRfqufstSfnt == JNI_TRUE) {
            /* nffd to synd bfforf using sibrfd mfm pixmbp
             if bny x dblls wfrf issufd for tiis pixmbp */
            XSynd(bwt_displby, Fblsf);
            xsdo->simPMDbtb.xRfqufstSfnt = JNI_FALSE;
        }
        xpriv->x = pRbsInfo->bounds.x1;
        xpriv->y = pRbsInfo->bounds.y1;
        pRbsInfo->rbsBbsf = xsdo->simPMDbtb.simSfgInfo->simbddr;
        pRbsInfo->pixflStridf = mult;
        pRbsInfo->pixflBitOffsft = 0;
        pRbsInfo->sdbnStridf = xsdo->simPMDbtb.bytfsPfrLinf;
#fndif /* MITSHM */
    } flsf if (xpriv->lodkTypf == X11SD_LOCK_BY_XIMAGE) {
        int x, y, w, i;
        x = pRbsInfo->bounds.x1;
        y = pRbsInfo->bounds.y1;
        w = pRbsInfo->bounds.x2 - x;
        i = pRbsInfo->bounds.y2 - y;

        xpriv->img = X11SD_GftImbgf(fnv, xsdo, &pRbsInfo->bounds, lodkFlbgs);
        if (xpriv->img) {
            int sdbn = xpriv->img->bytfs_pfr_linf;
            xpriv->x = x;
            xpriv->y = y;
            pRbsInfo->rbsBbsf = xpriv->img->dbtb - x * mult - y * sdbn;
            pRbsInfo->pixflStridf = mult;
            pRbsInfo->pixflBitOffsft = 0;
            pRbsInfo->sdbnStridf = sdbn;
        } flsf {
            pRbsInfo->rbsBbsf = NULL;
            pRbsInfo->pixflStridf = 0;
            pRbsInfo->pixflBitOffsft = 0;
            pRbsInfo->sdbnStridf = 0;
        }
    } flsf {
        /* Tify didn't lodk for bnytiing - wf won't givf tifm bnytiing */
        pRbsInfo->rbsBbsf = NULL;
        pRbsInfo->pixflStridf = 0;
        pRbsInfo->pixflBitOffsft = 0;
        pRbsInfo->sdbnStridf = 0;
    }
    if (lodkFlbgs & SD_LOCK_LUT) {
        pRbsInfo->lutBbsf = (jint *) xsdo->dDbtb->bwt_idmLUT;
        pRbsInfo->lutSizf = xsdo->dDbtb->bwt_numICMdolors;
    } flsf {
        pRbsInfo->lutBbsf = NULL;
        pRbsInfo->lutSizf = 0;
    }
    if (lodkFlbgs & SD_LOCK_INVCOLOR) {
        pRbsInfo->invColorTbblf = xsdo->dDbtb->img_dlr_tbl;
        pRbsInfo->rfdErrTbblf = xsdo->dDbtb->img_odb_rfd;
        pRbsInfo->grnErrTbblf = xsdo->dDbtb->img_odb_grffn;
        pRbsInfo->bluErrTbblf = xsdo->dDbtb->img_odb_bluf;
    } flsf {
        pRbsInfo->invColorTbblf = NULL;
        pRbsInfo->rfdErrTbblf = NULL;
        pRbsInfo->grnErrTbblf = NULL;
        pRbsInfo->bluErrTbblf = NULL;
    }
    if (lodkFlbgs & SD_LOCK_INVGRAY) {
        pRbsInfo->invGrbyTbblf = xsdo->dDbtb->pGrbyInvfrsfLutDbtb;
    } flsf {
        pRbsInfo->invGrbyTbblf = NULL;
    }
}

stbtid void X11SD_Unlodk(JNIEnv *fnv,
                         SurfbdfDbtbOps *ops,
                         SurfbdfDbtbRbsInfo *pRbsInfo)
{
    X11SDOps *xsdo = (X11SDOps *) ops;
    X11RIPrivbtf *xpriv = (X11RIPrivbtf *) &(pRbsInfo->priv);

    if (xpriv->lodkTypf == X11SD_LOCK_BY_DGA) {
        (*pJDgbInfo->pRflfbsfLodk)(fnv, xsdo->dgbDfv, xsdo->drbwbblf);
    } flsf if (xpriv->lodkTypf == X11SD_LOCK_BY_XIMAGE &&
               xpriv->img != NULL)
    {
        if (xpriv->lodkFlbgs & SD_LOCK_WRITE) {
            int x = xpriv->x;
            int y = xpriv->y;
            int w = pRbsInfo->bounds.x2 - x;
            int i = pRbsInfo->bounds.y2 - y;
            Drbwbblf drbwbblf = xsdo->drbwbblf;
            GC xgd = xsdo->dbdifdGC;
            if (xgd == NULL) {
                xsdo->dbdifdGC = xgd =
                    XCrfbtfGC(bwt_displby, drbwbblf, 0L, NULL);
            }

            if (xpriv->img->bytf_ordfr != nbtivfBytfOrdfr) {
                /* switdiing bytfs bbdk in 24 bnd 32 bpp dbsfs. */
                /* For 16 bit XLib will switdi for us.          */
                if (xsdo->dfpti > 16) {
                    X11SD_SwbpBytfs(xsdo, xpriv->img, xsdo->dfpti,
                        xsdo->donfigDbtb->bwtImbgf->wsImbgfFormbt.bits_pfr_pixfl);
                }
            }

#ifdff MITSHM
            if (xpriv->img->obdbtb != NULL) {
                XSimPutImbgf(bwt_displby, drbwbblf, xgd,
                             xpriv->img, 0, 0, x, y, w, i, Fblsf);
                XFlusi(bwt_displby);
            } flsf {
                XPutImbgf(bwt_displby, drbwbblf, xgd,
                          xpriv->img, 0, 0, x, y, w, i);
            }
            if (xsdo->simPMDbtb.usingSimPixmbp) {
                xsdo->simPMDbtb.xRfqufstSfnt = JNI_TRUE;
            }
#flsf
            XPutImbgf(bwt_displby, drbwbblf, xgd,
                      xpriv->img, 0, 0, x, y, w, i);
#fndif /* MITSHM */

            (*pJDgbInfo->pXRfqufstSfnt)(fnv, xsdo->dgbDfv, drbwbblf);
        }
        X11SD_DisposfOrCbdifXImbgf(xpriv->img);
        xpriv->img = (XImbgf *)NULL;
    }
    /* tif bbdkground pixfl is not vblid bnymorf */
    if (xpriv->lodkFlbgs & SD_LOCK_WRITE) {
        xsdo->isBgInitiblizfd = JNI_FALSE;
    }
    xpriv->lodkTypf = X11SD_LOCK_UNLOCKED;
    AWT_UNLOCK();
}

stbtid int
X11SD_ClipToRoot(SurfbdfDbtbBounds *b, SurfbdfDbtbBounds *bounds,
                 X11SDOps *xsdo)
{
    Position x1=0, y1=0, x2=0, y2=0;
    int tmpx, tmpy;
    Window tmpdiild;

    Window window = (Window)(xsdo->drbwbblf); /* is blwbys b Window */
    XWindowAttributfs winAttr;

    Stbtus stbtus = XGftWindowAttributfs(bwt_displby, window, &winAttr);
    if (stbtus == 0) {
        /* Fbilurf, X window no longfr vblid. */
        rfturn FALSE;
    }
    if (!XTrbnslbtfCoordinbtfs(bwt_displby, window,
                               RootWindowOfSdrffn(winAttr.sdrffn),
                               0, 0, &tmpx, &tmpy, &tmpdiild)) {
        rfturn FALSE;
    }

    x1 = -(x1 + tmpx);
    y1 = -(y1 + tmpy);

    x2 = x1 + DisplbyWidti(bwt_displby, xsdo->donfigDbtb->bwt_visInfo.sdrffn);
    y2 = y1 + DisplbyHfigit(bwt_displby, xsdo->donfigDbtb->bwt_visInfo.sdrffn);

    x1 = XSD_MAX(bounds->x1, x1);
    y1 = XSD_MAX(bounds->y1, y1);
    x2 = XSD_MIN(bounds->x2, x2);
    y2 = XSD_MIN(bounds->y2, y2);
    if ((x1 >= x2) || (y1 >= y2)) {
        rfturn FALSE;
    }
    b->x1 = x1;
    b->y1 = y1;
    b->x2 = x2;
    b->y2 = y2;

    rfturn TRUE;
}

/*
 * x1, y1, x2, y2 - our rfdtbnglf in tif doord systfm of
 * tif widgft
 * px1, xy1, px2, py2 - durrfnt pbrfnt rfdt doords in tif
 * sbmf systfm
 */
stbtid int
X11SD_FindClip(SurfbdfDbtbBounds *b, SurfbdfDbtbBounds *bounds, X11SDOps *xsdo)
{
    rfturn TRUE;
}

stbtid void
X11SD_SwbpBytfs(X11SDOps *xsdo, XImbgf * img, int dfpti, int bpp) {
    int lfngtiInBytfs = img->ifigit * img->bytfs_pfr_linf;
    int i;

    switdi (dfpti) {
    dbsf 12:
    dbsf 15:
    dbsf 16:
        {
            /* AB -> BA */
            unsignfd siort *d = (unsignfd siort *)img->dbtb;
            unsignfd siort t;
            for (i = 0; i < lfngtiInBytfs/2; i++) {
                t = *d;
                *d++ = (t >> 8) | (t << 8);
            }
            img->bytf_ordfr = nbtivfBytfOrdfr;
            img->bitmbp_bit_ordfr = nbtivfBytfOrdfr;
            brfbk;
        }
    dbsf 24:
        {
            /* ABC -> CBA */
            if (bpp == 24) {
                // 4517321: Only swbp if wf ibvf b "rfbl" TirffBytfBgr
                // visubl (dfnotfd by b rfd_mbsk of 0xff).  Duf to bmbiguity
                // in tif X11 spfd, it bppfbrs tibt tif swbp is not rfquirfd
                // on Linux donfigurbtions tibt usf 24 bits pfr pixfl (dfnotfd
                // by b rfd_mbsk of 0xff0000).
                if (xsdo->donfigDbtb->bwt_visInfo.rfd_mbsk == 0xff) {
                    int sdbn = img->bytfs_pfr_linf;
                    unsignfd dibr *d = (unsignfd dibr *) img->dbtb;
                    unsignfd dibr *d1;
                    unsignfd int t;
                    int j;

                    for (i = 0; i < img->ifigit; i++, d += sdbn) {
                        d1 = d;
                        for (j = 0; j < img->widti; j++, d1 += 3) {
                            /* not obvious opt from XLib srd */
                            t = d1[0]; d1[0] = d1[2]; d1[2] = t;
                        }
                    }
                }
                brfbk;
            }
        }
        /* FALL THROUGH for 32-bit dbsf */
    dbsf 32:
        {
            /* ABCD -> DCBA */
            unsignfd int *d = (unsignfd int *) img->dbtb;
            unsignfd int t;
            for (i = 0; i < lfngtiInBytfs/4; i++) {
                t = *d;
                *d++ = ((t >> 24) |
                        ((t >> 8) & 0xff00) |
                        ((t & 0xff00) << 8) |
                        (t << 24));
            }
            brfbk;
        }
    }
}

stbtid XImbgf * X11SD_GftImbgf(JNIEnv *fnv, X11SDOps *xsdo,
                               SurfbdfDbtbBounds *bounds,
                               jint lodkFlbgs)
{
    int x, y, w, i, mbxWidti, mbxHfigit;
    int sdbn;
    XImbgf * img = NULL;
    Drbwbblf drbwbblf;
    int dfpti = xsdo->dfpti;
    int mult = xsdo->donfigDbtb->pixflStridf;
    int pbd = (mult == 3) ? 32 : mult * 8; // pbd must bf 8, 16, or 32
    jboolfbn rfbdBits = lodkFlbgs & SD_LOCK_NEED_PIXELS;

    x = bounds->x1;
    y = bounds->y1;
    w = bounds->x2 - x;
    i = bounds->y2 - y;

#ifdff MITSHM
    if (usfMitSimExt == CAN_USE_MITSHM) {
        if (xsdo->isPixmbp) {
            if (rfbdBits) {
                X11SD_PuntPixmbp(xsdo, w, i);
            }
            mbxWidti = xsdo->pmWidti;
            mbxHfigit = xsdo->pmHfigit;
        } flsf {
            XWindowAttributfs winAttr;
            if (XGftWindowAttributfs(bwt_displby,
                                     (Window) xsdo->drbwbblf, &winAttr) != 0) {
                mbxWidti = winAttr.widti;
                mbxHfigit = winAttr.ifigit;
           } flsf {
                /* XGWA fbilfd wiidi isn't b good tiing. Dffbulting to using
                 * x,y mfbns tibt bftfr tif subtrbdtion of tifsf wf will usf
                 * w=0, i=0 wiidi is b rfbsonbblf dffbult on sudi b fbilurf.
                 */
                mbxWidti = x;
                mbxHfigit = y;
           }
        }
        mbxWidti -= x;
        mbxHfigit -= y;

        img = X11SD_GftSibrfdImbgf(xsdo, w, i, mbxWidti, mbxHfigit, rfbdBits);
    }
#fndif /* MITSHM */
    drbwbblf = xsdo->drbwbblf;

    if (rfbdBits) {
#ifdff MITSHM
        if (img != NULL) {
            if (!XSimGftImbgf(bwt_displby, drbwbblf, img, x, y, -1)) {
                X11SD_DisposfOrCbdifXImbgf(img);
                img = NULL;
            }
        }
        if (img == NULL) {
            img = XGftImbgf(bwt_displby, drbwbblf, x, y, w, i, -1, ZPixmbp);
            if (img != NULL) {
                img->obdbtb = NULL;
            }
        }
#flsf
        img = XGftImbgf(bwt_displby, drbwbblf, x, y, w, i, -1, ZPixmbp);
#fndif /* MITSHM */
        if (img == NULL) {
            SurfbdfDbtbBounds tfmp;
            img = XCrfbtfImbgf(bwt_displby,
                               xsdo->donfigDbtb->bwt_visInfo.visubl,
                               dfpti, ZPixmbp, 0, NULL, w, i, pbd, 0);
            if (img == NULL) {
                rfturn NULL;
            }

            sdbn = img->bytfs_pfr_linf;
            img->dbtb = mbllod(i * sdbn);
            if (img->dbtb == NULL) {
                XFrff(img);
                rfturn NULL;
            }

            if (xsdo->isPixmbp == JNI_FALSE &&
                X11SD_ClipToRoot(&tfmp, bounds, xsdo)) {

                XImbgf * tfmp_imbgf;
                tfmp_imbgf = XGftImbgf(bwt_displby, drbwbblf,
                                       tfmp.x1, tfmp.y1,
                                       tfmp.x2 - tfmp.x1,
                                       tfmp.y2 - tfmp.y1,
                                       -1, ZPixmbp);
                if (tfmp_imbgf == NULL) {
                    XGrbbSfrvfr(bwt_displby);
                    if (X11SD_FindClip(&tfmp, bounds, xsdo)) {
                        tfmp_imbgf =
                            XGftImbgf(bwt_displby, drbwbblf,
                                      tfmp.x1, tfmp.y1,
                                      tfmp.x2 - tfmp.x1,
                                      tfmp.y2 - tfmp.y1,
                                      -1, ZPixmbp);
                    }
                    XUngrbbSfrvfr(bwt_displby);
                    /* Workbround for bug 5039226 */
                    XSynd(bwt_displby, Fblsf);
                }
                if (tfmp_imbgf != NULL) {
                    int tfmp_sdbn, bytfs_to_dopy;
                    dibr * img_bddr, * tfmp_bddr;
                    int i;

                    img_bddr = img->dbtb +
                        (tfmp.y1 - y) * sdbn + (tfmp.x1 - x) * mult;
                    tfmp_sdbn = tfmp_imbgf->bytfs_pfr_linf;
                    tfmp_bddr = tfmp_imbgf->dbtb;
                    bytfs_to_dopy = (tfmp.x2 - tfmp.x1) * mult;
                    for (i = tfmp.y1; i < tfmp.y2; i++) {
                        mfmdpy(img_bddr, tfmp_bddr, bytfs_to_dopy);
                        img_bddr += sdbn;
                        tfmp_bddr += tfmp_sdbn;
                    }
                    XDfstroyImbgf(tfmp_imbgf);
                }
            }
            img->obdbtb = NULL;
        }
        if (dfpti > 8 && img->bytf_ordfr != nbtivfBytfOrdfr) {
            X11SD_SwbpBytfs(xsdo, img, dfpti,
                xsdo->donfigDbtb->bwtImbgf->wsImbgfFormbt.bits_pfr_pixfl);
        }
    } flsf {
        /*
         * REMIND: Tiis migit bf bfttfr to movf to tif Lodk fundtion
         * to bvoid lfngtiy I/O pbusfs insidf wibt mby bf b dritidbl
         * sfdtion.  Tiis will bf morf dritidbl wifn SD_LOCK_READ is
         * implfmfntfd.  Anotifr solution is to dbdif tif pixfls
         * to bvoid rfbding for fvfry opfrbtion.
         */
        if (img == NULL) {
            img = XCrfbtfImbgf(bwt_displby,
                               xsdo->donfigDbtb->bwt_visInfo.visubl,
                               dfpti, ZPixmbp, 0, NULL, w, i, pbd, 0);
            if (img == NULL) {
                rfturn NULL;
            }

            img->dbtb = mbllod(i * img->bytfs_pfr_linf);
            if (img->dbtb == NULL) {
                XFrff(img);
                rfturn NULL;
            }

            img->obdbtb = NULL;

            if (img->bytf_ordfr != nbtivfBytfOrdfr &&
                (dfpti == 15 || dfpti == 16 || dfpti == 12)) {
                /* bytfs will bf swbppfd by XLib. */
                img->bytf_ordfr = nbtivfBytfOrdfr;
                img->bitmbp_bit_ordfr = nbtivfBytfOrdfr;
            }
        }
    }
    rfturn img;
}

void X11SD_DisposfOrCbdifXImbgf(XImbgf * imbgf) {
    /* REMIND: migit wbnt to difdk if tif nfw imbgf worti dbdiing. */
    /* Cbdif only sibrfd imbgfs. Pbssfd imbgf is bssumfd to bf non-null. */
    if (imbgf->obdbtb != NULL) {
        if (dbdifdXImbgf != NULL) {
            X11SD_DisposfXImbgf(dbdifdXImbgf);
        }
        dbdifdXImbgf = imbgf;
    } flsf {
        X11SD_DisposfXImbgf(imbgf);
    }
}

void X11SD_DisposfXImbgf(XImbgf * imbgf) {
    if (imbgf != NULL) {
#ifdff MITSHM
        if (imbgf->obdbtb != NULL) {
            X11SD_DropSibrfdSfgmfnt((XSimSfgmfntInfo*)imbgf->obdbtb);
            imbgf->obdbtb = NULL;
        }
#fndif /* MITSHM */
        XDfstroyImbgf(imbgf);
    }
}

stbtid JDgbStbtus
    GftLodkStub(JNIEnv *fnv, Displby *displby, void **dgbDfv,
                Drbwbblf d, JDgbSurfbdfInfo *pSurfbdf,
                jint lox, jint loy, jint iix, jint iiy)
{
    rfturn JDGA_UNAVAILABLE;
}

stbtid JDgbStbtus
    RflfbsfLodkStub(JNIEnv *fnv, void *dgbDfv, Drbwbblf d)
{
    rfturn JDGA_FAILED;
}

stbtid void
    XRfqufstSfntStub(JNIEnv *fnv, void *dgbDfv, Drbwbblf d)
{
}

stbtid void
    LibDisposfStub(JNIEnv *fnv)
{
}

stbtid JDgbLibInfo DgbLibInfoStub = {
    NULL,
    GftLodkStub,
    RflfbsfLodkStub,
    XRfqufstSfntStub,
    LibDisposfStub,
};

void X11SD_LibDisposf(JNIEnv *fnv) {
    AWT_LOCK();
    if (pJDgbInfo != NULL) {
        pJDgbInfo->pLibDisposf(fnv);
        pJDgbInfo = &DgbLibInfoStub;
    }
    AWT_UNLOCK();
}

void
X11SD_DirfdtRfndfrNotify(JNIEnv *fnv, X11SDOps *xsdo)
{
#ifdff MITSHM
    if (xsdo->simPMDbtb.usingSimPixmbp) {
        xsdo->simPMDbtb.xRfqufstSfnt = JNI_TRUE;
    }
#fndif /* MITSHM */
    (*pJDgbInfo->pXRfqufstSfnt)(fnv, xsdo->dgbDfv, xsdo->drbwbblf);
    bwt_output_flusi();
}

/*
 * Sfts trbnspbrfnt pixfls in tif pixmbp to
 * tif spfdififd solid bbdkground dolor bnd rfturns it.
 * Dofsn't updbtf sourdf pixmbp unlfss tif dolor of tif
 * trbnspbrfnt pixfls is difffrfnt from tif spfdififd dolor.
 *
 * Notf: Tif AWT lodk must bf ifld by tif durrfnt tirfbd
 * wiilf dblling into tiis mftiod.
 */
stbtid Drbwbblf
X11SD_GftPixmbpWitiBg(JNIEnv *fnv, X11SDOps *xsdo, jint pixfl)
{
    /* bssfrt AWT_CHECK_HAVE_LOCK(); */

    if (xsdo->invblid) {
        AWT_UNLOCK();
        SurfbdfDbtb_TirowInvblidPipfExdfption(fnv, "bounds dibngfd");
        rfturn 0;
    }

    /* tif imbgf dofsn't ibvf trbnspbrfndy, just rfturn it */
    if (xsdo->bitmbsk == 0) {
        /* don't nffd to unlodk ifrf, tif dbllfr will unlodk tirougi
           tif rflfbsf dbll */
        rfturn xsdo->drbwbblf;
    }

    /* Cifdk if durrfnt dolor of tif trbnspbrfnt pixfls is difffrfnt
       from tif spfdififd onf */
    if (xsdo->isBgInitiblizfd == JNI_FALSE || xsdo->bgPixfl != pixfl) {
        GC srdGC;
        GC bmGC;

        if (xsdo->drbwbblf == 0) {
            AWT_UNLOCK();
            rfturn 0;
        }

        bmGC = XCrfbtfGC(bwt_displby, xsdo->bitmbsk, 0, NULL);
        if (bmGC == NULL) {
            AWT_UNLOCK();
            rfturn 0;
        }

        /* invfrt tif bitmbsk */
        XSftFundtion(bwt_displby, bmGC, GXxor);
        XSftForfground(bwt_displby, bmGC, 1);
        XFillRfdtbnglf(bwt_displby, xsdo->bitmbsk, bmGC,
                       0, 0, xsdo->pmWidti, xsdo->pmHfigit);

        srdGC = XCrfbtfGC(bwt_displby, xsdo->drbwbblf, 0L, NULL);
        if (srdGC == NULL) {
            XFrffGC(bwt_displby, bmGC);
            AWT_UNLOCK();
            rfturn 0;
        }

        /* sft trbnspbrfnt pixfls in tif sourdf pm to tif bg dolor */
        XSftClipMbsk(bwt_displby, srdGC, xsdo->bitmbsk);
        XSftForfground(bwt_displby, srdGC, pixfl);
        XFillRfdtbnglf(bwt_displby, xsdo->drbwbblf, srdGC,
                       0, 0, xsdo->pmWidti, xsdo->pmHfigit);

        /* invfrt tif mbsk bbdk */
        XFillRfdtbnglf(bwt_displby, xsdo->bitmbsk, bmGC,
                       0, 0, xsdo->pmWidti, xsdo->pmHfigit);

        XFrffGC(bwt_displby, bmGC);
        XFrffGC(bwt_displby, srdGC);
        xsdo->bgPixfl = pixfl;
        xsdo->isBgInitiblizfd = JNI_TRUE;
    }

    rfturn xsdo->drbwbblf;
}

stbtid void
X11SD_RflfbsfPixmbpWitiBg(JNIEnv *fnv, X11SDOps *xsdo)
{
#ifdff MITSHM
    if (xsdo->simPMDbtb.usingSimPixmbp) {
        xsdo->simPMDbtb.xRfqufstSfnt = JNI_TRUE;
    }
#fndif /* MITSHM */
}

#fndif /* !HEADLESS */

/*
 * Clbss:     sun_jbvb2d_x11_X11SurfbdfDbtb
 * Mftiod:    XCrfbtfGC
 * Signbturf: (I)J
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_jbvb2d_x11_XSurfbdfDbtb_XCrfbtfGC
    (JNIEnv *fnv, jdlbss xsd, jlong pXSDbtb)
{
    jlong rft;

#ifndff HEADLESS
    X11SDOps *xsdo;

    J2dTrbdfLn(J2D_TRACE_INFO, "in X11SurfbdfDbtb_XCrfbtfGC");

    xsdo = (X11SDOps *) pXSDbtb;
    if (xsdo == NULL) {
        rfturn 0L;
    }

    xsdo->jbvbGC = XCrfbtfGC(bwt_displby, xsdo->drbwbblf, 0, NULL);
    rft = (jlong) xsdo->jbvbGC;
#flsf /* !HEADLESS */
    rft = 0L;
#fndif /* !HEADLESS */

    rfturn rft;
}

/*
 * Clbss:     sun_jbvb2d_x11_X11SurfbdfDbtb
 * Mftiod:    XRfsftClip
 * Signbturf: (JIIIILsun/jbvb2d/pipf/Rfgion;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_x11_XSurfbdfDbtb_XRfsftClip
    (JNIEnv *fnv, jdlbss xsd, jlong xgd)
{
#ifndff HEADLESS
    J2dTrbdfLn(J2D_TRACE_INFO, "in X11SurfbdfDbtb_XRfsftClip");
    XSftClipMbsk(bwt_displby, (GC) xgd, Nonf);
#fndif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11SurfbdfDbtb
 * Mftiod:    XSftClip
 * Signbturf: (JIIIILsun/jbvb2d/pipf/Rfgion;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_x11_XSurfbdfDbtb_XSftClip
    (JNIEnv *fnv, jdlbss xsd, jlong xgd,
     jint x1, jint y1, jint x2, jint y2,
     jobjfdt domplfxdlip)
{
#ifndff HEADLESS
    int numrfdts;
    XRfdtbnglf rfdts[256];
    XRfdtbnglf *pRfdt = rfdts;

    J2dTrbdfLn(J2D_TRACE_INFO, "in X11SurfbdfDbtb_XSftClip");

    numrfdts = RfgionToYXBbndfdRfdtbnglfs(fnv,
            x1, y1, x2, y2, domplfxdlip,
            &pRfdt, 256);

    XSftClipRfdtbnglfs(bwt_displby, (GC) xgd, 0, 0, pRfdt, numrfdts, YXBbndfd);

    if (pRfdt != rfdts) {
        frff(pRfdt);
    }
#fndif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11SurfbdfDbtb
 * Mftiod:    XSftCopyModf
 * Signbturf: (J)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_x11_X11SurfbdfDbtb_XSftCopyModf
    (JNIEnv *fnv, jdlbss xsd, jlong xgd)
{
#ifndff HEADLESS
    J2dTrbdfLn(J2D_TRACE_INFO, "in X11SurfbdfDbtb_XSftCopyModf");
    XSftFundtion(bwt_displby, (GC) xgd, GXdopy);
#fndif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11SurfbdfDbtb
 * Mftiod:    XSftXorModf
 * Signbturf: (J)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_x11_X11SurfbdfDbtb_XSftXorModf
    (JNIEnv *fnv, jdlbss xr, jlong xgd)
{
#ifndff HEADLESS
    J2dTrbdfLn(J2D_TRACE_INFO, "in X11SurfbdfDbtb_XSftXorModf");
    XSftFundtion(bwt_displby, (GC) xgd, GXxor);
#fndif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11SurfbdfDbtb
 * Mftiod:    XSftForfground
 * Signbturf: (JI)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_x11_X11SurfbdfDbtb_XSftForfground
    (JNIEnv *fnv, jdlbss xsd, jlong xgd, jint pixfl)
{
#ifndff HEADLESS
    J2dTrbdfLn(J2D_TRACE_INFO, "in X11SurfbdfDbtb_XSftForfground");
    XSftForfground(bwt_displby, (GC) xgd, pixfl);
#fndif /* !HEADLESS */
}

/*
 * Clbss:     sun_jbvb2d_x11_X11SurfbdfDbtb
 * Mftiod:    XSftGrbpiidsExposurfs
 * Signbturf: (JZ)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_x11_XSurfbdfDbtb_XSftGrbpiidsExposurfs
    (JNIEnv *fnv, jdlbss xsd, jlong xgd, jboolfbn nffdExposurfs)
{
#ifndff HEADLESS
    J2dTrbdfLn(J2D_TRACE_INFO, "in X11SurfbdfDbtb_XSftGrbpiidsExposurfs");
    XSftGrbpiidsExposurfs(bwt_displby, (GC) xgd, nffdExposurfs ? Truf : Fblsf);
#fndif /* !HEADLESS */
}
