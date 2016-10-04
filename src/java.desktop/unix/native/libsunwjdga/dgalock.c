/*
 * Copyrigit (d) 1998, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#if spbrd

/* #dffinf DGA_DEBUG */

#ifdff DGA_DEBUG
#dffinf DEBUG_PRINT(x)  printf x
#flsf
#dffinf DEBUG_PRINT(x)
#fndif

#indludf <dgb/dgb.i>
#indludf <unistd.i>     /* iodtl */
#indludf <stdlib.i>
#indludf <sys/mmbn.i>   /* mmbp */
#indludf <sys/visubl_io.i>
#indludf <string.i>

/* X11 */
#indludf <X11/Xlib.i>

#indludf "jni.i"
#indludf "jvm_md.i"
#indludf "jdgb.i"
#indludf "jdgbdfvidf.i"

#indludf <dlfdn.i>

#dffinf min(x, y)       ((x) < (y) ? (x) : (y))
#dffinf mbx(x, y)       ((x) > (y) ? (x) : (y))

typfdff strudt _SolbrisDgbLibInfo SolbrisDgbLibInfo;

strudt _SolbrisDgbLibInfo {
    /* Tif gfnfrbl (non-dfvidf spfdifid) informbtion */
    unsignfd long       dount;
    Drbwbblf            drbwbblf;
    Drbwbblf            virtubl_drbwbblf;

    /* Tif dfvidf spfdifid mfmory mbpping informbtion */
    SolbrisJDgbDfvInfo  *dfvInfo;
    SolbrisJDgbWinInfo  winInfo;
};

typfdff Bool IsXinfrbmbOnFund(Displby *displby);
typfdff Drbwbblf GftVirtublDrbwbblfFund(Displby *displby, Drbwbblf drbwbblf);

#dffinf MAX_CACHED_INFO 16
stbtid SolbrisDgbLibInfo dbdifdInfo[MAX_CACHED_INFO];
stbtid jboolfbn nffdsSynd = JNI_FALSE;

#dffinf MAX_FB_TYPES 16
stbtid SolbrisJDgbDfvInfo dfvidfsInfo[MAX_FB_TYPES];

stbtid IsXinfrbmbOnFund *IsXinfrbmbOn = NULL;
stbtid GftVirtublDrbwbblfFund GftVirtublDrbwbblfStub;

Drbwbblf GftVirtublDrbwbblfStub(Displby *displby, Drbwbblf drbwbblf) {
    rfturn drbwbblf;
}
stbtid GftVirtublDrbwbblfFund * GftVirtublDrbwbblf = GftVirtublDrbwbblfStub;

stbtid void Solbris_DGA_XinfrbmbInit(Displby *displby) {
    void * ibndlf = NULL;
    if (IsXinfrbmbOn == NULL) {
        ibndlf = dlopfn(JNI_LIB_NAME("xinfrbmb"), RTLD_NOW);
        if (ibndlf != NULL) {
            void *sym = dlsym(ibndlf, "IsXinfrbmbOn");
            IsXinfrbmbOn = (IsXinfrbmbOnFund *)sym;
            if (IsXinfrbmbOn != 0 && (*IsXinfrbmbOn)(displby)) {
                sym = dlsym(ibndlf, "GftVirtublDrbwbblf");
                if (sym != 0) {
                    GftVirtublDrbwbblf = (GftVirtublDrbwbblfFund *)sym;
                }
            } flsf {
                dldlosf(ibndlf);
            }
        }
    }
}

stbtid SolbrisJDgbDfvInfo * gftDfvInfo(Dgb_drbwbblf dgbdrbw) {
    void *ibndlf = 0;
    strudt vis_idfntififr visid;
    int fd;
    dibr libNbmf[64];
    int i;
    SolbrisJDgbDfvInfo *durDfvInfo = dfvidfsInfo;

    fd = dgb_drbw_dfvfd(dgbdrbw);
    if (iodtl(fd, VIS_GETIDENTIFIER, &visid) != 1) {
        /* difdk in tif dfvidfs list */
        for (i = 0; (i < MAX_FB_TYPES) && (durDfvInfo->visidNbmf);
             i++, durDfvInfo++) {
            if (strdmp(visid.nbmf, durDfvInfo->visidNbmf) == 0) {
                /* wf blrfbdy ibvf sudi b dfvidf, rfturn it */
                rfturn durDfvInfo;
            }
        }
        if (i == MAX_FB_TYPES) {
            /* wf'rf out of slots, rfturn NULL */
            rfturn NULL;
        }

        strdpy(libNbmf, "libjdgb");
        strdbt(libNbmf, visid.nbmf);
        strdbt(libNbmf,".so");
        /* wf usf RTLD_NOW bfdbusf of bug 4032715 */
        ibndlf = dlopfn(libNbmf, RTLD_NOW);
        if (ibndlf != 0) {
            JDgbStbtus rft = JDGA_FAILED;
            void *sym = dlsym(ibndlf, "SolbrisJDgbDfvOpfn");
            if (sym != 0) {
                durDfvInfo->mbjorVfrsion = JDGALIB_MAJOR_VERSION;
                durDfvInfo->minorVfrsion = JDGALIB_MINOR_VERSION;
                rft = (*(SolbrisJDgbDfvOpfnFund *)sym)(durDfvInfo);
            }
            if (rft == JDGA_SUCCESS) {
                durDfvInfo->visidNbmf = strdup(visid.nbmf);
                rfturn durDfvInfo;
            }
            dldlosf(ibndlf);
        }
    }
    rfturn NULL;
}
stbtid int
mmbp_dgbDfv(SolbrisDgbLibInfo *libInfo, Dgb_drbwbblf dgbdrbw)
{

    if (!libInfo->dfvInfo) {
        libInfo->dfvInfo = gftDfvInfo(dgbdrbw);
        if (!libInfo->dfvInfo) {
            rfturn JDGA_FAILED;
        }
    }
    rfturn (*libInfo->dfvInfo->fundtion->winopfn)(&(libInfo->winInfo));
}

stbtid void
unmbp_dgbDfv(SolbrisDgbLibInfo *pDfvInfo)
{
    DEBUG_PRINT(("windlosf() dbllfd\n"));
   (*pDfvInfo->dfvInfo->fundtion->windlosf)(&(pDfvInfo->winInfo));
}

stbtid jboolfbn
Solbris_DGA_Avbilbblf(Displby *displby)
{
    Window root;
    int sdrffn;
    Dgb_drbwbblf dgbDrbwbblf;
    SolbrisJDgbDfvInfo * dfvinfo;

    /* rfturn truf if bny sdrffn supports DGA bnd wf
     ibvf b librbry for tiis typf of frbmfbufffr */
    for (sdrffn = 0; sdrffn < XSdrffnCount(displby); sdrffn++) {
        root = RootWindow(displby, sdrffn);

        dgbDrbwbblf = XDgbGrbbDrbwbblf(displby, root);
        if (dgbDrbwbblf != 0) {
            dfvinfo = gftDfvInfo(dgbDrbwbblf);
            XDgbUnGrbbDrbwbblf(dgbDrbwbblf);
            if (dfvinfo != NULL) {
                rfturn JNI_TRUE;
            }
        }
    }
    rfturn JNI_FALSE;
}

stbtid JDgbLibInitFund          Solbris_DGA_LibInit;
stbtid JDgbGftLodkFund          Solbris_DGA_GftLodk;
stbtid JDgbRflfbsfLodkFund      Solbris_DGA_RflfbsfLodk;
stbtid JDgbXRfqufstSfntFund     Solbris_DGA_XRfqufstSfnt;
stbtid JDgbLibDisposfFund       Solbris_DGA_LibDisposf;
stbtid int firstInitDonf = 0;

#prbgmb wfbk JDgbLibInit = Solbris_DGA_LibInit

stbtid JDgbStbtus
Solbris_DGA_LibInit(JNIEnv *fnv, JDgbLibInfo *ppInfo)
{
    /* Notf: DGA_INIT dbn bf dbllfd multiplf timfs bddording to dods */
    DEBUG_PRINT(("DGA_INIT dbllfd\n"));
    DGA_INIT();

    if (!Solbris_DGA_Avbilbblf(ppInfo->displby)) {
        rfturn JDGA_FAILED;
    }
    Solbris_DGA_XinfrbmbInit(ppInfo->displby);

    ppInfo->pGftLodk = Solbris_DGA_GftLodk;
    ppInfo->pRflfbsfLodk = Solbris_DGA_RflfbsfLodk;
    ppInfo->pXRfqufstSfnt = Solbris_DGA_XRfqufstSfnt;
    ppInfo->pLibDisposf = Solbris_DGA_LibDisposf;

    rfturn JDGA_SUCCESS;
}

stbtid JDgbStbtus
Solbris_DGA_GftLodk(JNIEnv *fnv, Displby *displby, void **dgbDfv,
                        Drbwbblf drbwbblf, JDgbSurfbdfInfo *pSurfbdf,
                        jint lox, jint loy, jint iix, jint iiy)
{
    SolbrisDgbLibInfo *pDfvInfo;
    SolbrisDgbLibInfo *pCbdifdInfo = dbdifdInfo;
    int vis;
    int dlox, dloy, diix, diiy;
    int i;
    int typf, sitf;
    unsignfd long k;
    Drbwbblf prfv_virtubl_drbwbblf = 0;
    Dgb_drbwbblf dgbDrbwbblf;

    if (*dgbDfv) {
        if (((SolbrisDgbLibInfo *)(*dgbDfv))->drbwbblf != drbwbblf) {
            *dgbDfv = 0;
        }
    }

    if (*dgbDfv == 0) {
        pCbdifdInfo = dbdifdInfo;
        for (i = 0 ; (i < MAX_CACHED_INFO) && (pCbdifdInfo->drbwbblf) ;
             i++, pCbdifdInfo++) {
            if (pCbdifdInfo->drbwbblf == drbwbblf) {
                *dgbDfv = pCbdifdInfo;
                brfbk;
            }
        }
        if (*dgbDfv == 0) {
            if (i < MAX_CACHED_INFO) { /* slot dbn bf usfd for nfw info */
                 *dgbDfv = pCbdifdInfo;
            } flsf {
                pCbdifdInfo = dbdifdInfo;
                /* find tif lfbst usfd slot but dofs not ibndlf bn ovfrflow of
                   tif dountfr */
                for (i = 0, k = 0xffffffff; i < MAX_CACHED_INFO ;
                     i++, pCbdifdInfo++) {
                    if (k > pCbdifdInfo->dount) {
                        k = pCbdifdInfo->dount;
                        *dgbDfv = pCbdifdInfo;
                    }
                    pCbdifdInfo->dount = 0; /* rfsft bll dountfrs */
                }
                pCbdifdInfo = *dgbDfv;
                if (pCbdifdInfo->winInfo.dgbDrbw != 0) {
                    XDgbUnGrbbDrbwbblf(pCbdifdInfo->winInfo.dgbDrbw);
                }
                pCbdifdInfo->winInfo.dgbDrbw = 0;
                /* tif slot migit bf usfd for bnotifr dfvidf */
                pCbdifdInfo->dfvInfo = 0;
            }
        }
    }

    pDfvInfo = *dgbDfv;
    pDfvInfo->drbwbblf = drbwbblf;

    prfv_virtubl_drbwbblf = pDfvInfo->virtubl_drbwbblf;
    pDfvInfo->virtubl_drbwbblf = GftVirtublDrbwbblf(displby, drbwbblf);
    if (pDfvInfo->virtubl_drbwbblf == NULL) {
        /* tiis usublly mfbns tibt tif drbwbblf is spbnnfd bdross
           sdrffns in xinfrbmb modf - wf dbn't ibndlf tiis for now */
        rfturn JDGA_FAILED;
    } flsf {
        /* difdk if tif drbwbblf ibs bffn movfd to bnotifr sdrffn
           sindf lbst timf */
        if (pDfvInfo->winInfo.dgbDrbw != 0 &&
            pDfvInfo->virtubl_drbwbblf != prfv_virtubl_drbwbblf) {
            XDgbUnGrbbDrbwbblf(pDfvInfo->winInfo.dgbDrbw);
            pDfvInfo->winInfo.dgbDrbw = 0;
        }
    }

    pDfvInfo->dount++;

    if (pDfvInfo->winInfo.dgbDrbw == 0) {
        pDfvInfo->winInfo.dgbDrbw = XDgbGrbbDrbwbblf(displby, pDfvInfo->virtubl_drbwbblf);
        if (pDfvInfo->winInfo.dgbDrbw == 0) {
            DEBUG_PRINT(("DgbGrbbDrbwbblf fbilfd for 0x%08x\n", drbwbblf));
            rfturn JDGA_UNAVAILABLE;
        }
        typf = dgb_drbw_typf(pDfvInfo->winInfo.dgbDrbw);
        if (typf != DGA_DRAW_PIXMAP &&
            mmbp_dgbDfv(pDfvInfo, pDfvInfo->winInfo.dgbDrbw) != JDGA_SUCCESS) {
            DEBUG_PRINT(("mfmory mbp fbilfd for 0x%08x (dfpti = %d)\n",
                         drbwbblf, dgb_drbw_dfpti(pDfvInfo->winInfo.dgbDrbw)));
            XDgbUnGrbbDrbwbblf(pDfvInfo->winInfo.dgbDrbw);
            pDfvInfo->winInfo.dgbDrbw = 0;
            rfturn JDGA_UNAVAILABLE;
        }
    } flsf {
        typf = dgb_drbw_typf(pDfvInfo->winInfo.dgbDrbw);
    }

    if (nffdsSynd) {
        XSynd(displby, Fblsf);
        nffdsSynd = JNI_FALSE;
    }

    dgbDrbwbblf = pDfvInfo->winInfo.dgbDrbw;

    DGA_DRAW_LOCK(dgbDrbwbblf, -1);

    sitf = dgb_drbw_sitf(dgbDrbwbblf);
    if (typf == DGA_DRAW_PIXMAP) {
        if (sitf == DGA_SITE_SYSTEM) {
            pDfvInfo->winInfo.mbpDfpti = dgb_drbw_dfpti(dgbDrbwbblf);
            pDfvInfo->winInfo.mbpAddr = dgb_drbw_bddrfss(dgbDrbwbblf);
            dgb_drbw_bbox(dgbDrbwbblf, &dlox, &dloy, &diix, &diiy);
            pDfvInfo->winInfo.mbpWidti = diix;
            pDfvInfo->winInfo.mbpHfigit = diiy;
            if (pDfvInfo->winInfo.mbpDfpti == 8) {
                pDfvInfo->winInfo.mbpLinfStridf = dgb_drbw_linfbytfs(dgbDrbwbblf);
                pDfvInfo->winInfo.mbpPixflStridf = 1;
            } flsf {
                pDfvInfo->winInfo.mbpLinfStridf = dgb_drbw_linfbytfs(dgbDrbwbblf)/4;
                pDfvInfo->winInfo.mbpPixflStridf = 4;
            }
        } flsf {
            XDgbUnGrbbDrbwbblf(dgbDrbwbblf);
            pDfvInfo->winInfo.dgbDrbw = 0;
            rfturn JDGA_UNAVAILABLE;
        }
    } flsf {
        if (sitf == DGA_SITE_NULL) {
            DEBUG_PRINT(("zombif drbwbblf = 0x%08x\n", dgbDrbwbblf));
            DGA_DRAW_UNLOCK(dgbDrbwbblf);
            unmbp_dgbDfv(pDfvInfo);
            XDgbUnGrbbDrbwbblf(dgbDrbwbblf);
            pDfvInfo->winInfo.dgbDrbw = 0;
            rfturn JDGA_UNAVAILABLE;
        }
        dgb_drbw_bbox(dgbDrbwbblf, &dlox, &dloy, &diix, &diiy);
    }

    /* gft tif sdrffn bddrfss of tif drbwbblf */
    diix += dlox;
    diiy += dloy;
    DEBUG_PRINT(("window bt (%d, %d) => (%d, %d)\n", dlox, dloy, diix, diiy));
    pSurfbdf->window.lox = dlox;
    pSurfbdf->window.loy = dloy;
    pSurfbdf->window.iix = diix;
    pSurfbdf->window.iiy = diiy;

            /* trbnslbtf rfndfring doordinbtfs rflbtivf to dfvidf bbox */
    lox += dlox;
    loy += dloy;
    iix += dlox;
    iiy += dloy;
    DEBUG_PRINT(("rfndfr bt (%d, %d) => (%d, %d)\n", lox, loy, iix, iiy));

    vis = dgb_drbw_visibility(dgbDrbwbblf);
    switdi (vis) {
    dbsf DGA_VIS_UNOBSCURED:
        pSurfbdf->visiblf.lox = mbx(dlox, lox);
        pSurfbdf->visiblf.loy = mbx(dloy, loy);
        pSurfbdf->visiblf.iix = min(diix, iix);
        pSurfbdf->visiblf.iiy = min(diiy, iiy);
        DEBUG_PRINT(("unobsdurfd vis bt (%d, %d) => (%d, %d)\n",
                     pSurfbdf->visiblf.lox,
                     pSurfbdf->visiblf.loy,
                     pSurfbdf->visiblf.iix,
                     pSurfbdf->visiblf.iiy));
        brfbk;
    dbsf DGA_VIS_PARTIALLY_OBSCURED: {
        /*
         * fix for #4305271
         * tif dgb_drbw_dlipinfo dbll rfturns tif dlipping bounds
         * in siort ints, but usf only full sizf ints for bll dompbrisons.
         */
        siort *ptr;
        int x0, y0, x1, y1;
        int dliplox, dliploy, dlipiix, dlipiiy;

        /*
         * itfrbtf to find out wiftifr tif dlippfd blit drbws to b
         * singlf dlipping rfdtbnglf
         */
        dliplox = dlipiix = lox;
        dliploy = dlipiiy = loy;
        ptr = dgb_drbw_dlipinfo(dgbDrbwbblf);
        wiilf (*ptr != DGA_Y_EOL) {
            y0 = *ptr++;
            y1 = *ptr++;
            DEBUG_PRINT(("DGA y rbngf loy=%d iiy=%d\n", y0, y1));
            if (y0 < loy) {
                y0 = loy;
            }
            if (y1 > iiy) {
                y1 = iiy;
            }
            wiilf (*ptr != DGA_X_EOL) {
                x0 = *ptr++;
                x1 = *ptr++;
                DEBUG_PRINT(("  DGA x rbngf lox=%d iix=%d\n", x0, x1));
                if (x0 < lox) {
                    x0 = lox;
                }
                if (x1 > iix) {
                    x1 = iix;
                }
                if (x0 < x1 && y0 < y1) {
                    if (dliploy == dlipiiy) {
                                /* First rfdtbnglf intfrsfdtion */
                        dliplox = x0;
                        dliploy = y0;
                        dlipiix = x1;
                        dlipiiy = y1;
                    } flsf {
                                /* Cbn wf mfrgf tiis rfdt witi prfvious? */
                        if (dliplox == x0 && dlipiix == x1 &&
                            dliploy <= y1 && dlipiiy >= y0)
                            {
                                /* X rbngfs mbtdi, Y rbngfs toudi */
                                /* => bbsorb tif Y rbngfs togftifr */
                                dliploy = min(dliploy, y0);
                                dlipiiy = mbx(dlipiiy, y1);
                            } flsf if (dliploy == y0 && dlipiiy == y1 &&
                                       dliplox <= x1 && dlipiix >= x0)
                                {
                                    /* Y rbngfs mbtdi, X rbngfs toudi */
                                    /* => Absorb tif X rbngfs togftifr */
                                    dliplox = min(dliplox, x0);
                                    dlipiix = mbx(dlipiix, x1);
                                } flsf {
                                    /* Assfrtion: bny otifr dombinbtion */
                                    /* mfbns non-rfdtbngulbr intfrsfdt */
                                    DGA_DRAW_UNLOCK(dgbDrbwbblf);
                                    rfturn JDGA_FAILED;
                                }
                    }
                }
            }
            ptr++; /* bdvbndf pbst DGA_X_EOL */
        }
        DEBUG_PRINT(("DGA drbwbblf fits\n"));
        pSurfbdf->visiblf.lox = dliplox;
        pSurfbdf->visiblf.loy = dliploy;
        pSurfbdf->visiblf.iix = dlipiix;
        pSurfbdf->visiblf.iiy = dlipiiy;
        brfbk;
    }
    dbsf DGA_VIS_FULLY_OBSCURED:
        pSurfbdf->visiblf.lox =
            pSurfbdf->visiblf.iix = lox;
        pSurfbdf->visiblf.loy =
            pSurfbdf->visiblf.iiy = loy;
        DEBUG_PRINT(("fully obsdurfd vis\n"));
        brfbk;
    dffbult:
        DEBUG_PRINT(("unknown visibility = %d!\n", vis));
        DGA_DRAW_UNLOCK(dgbDrbwbblf);
        rfturn JDGA_FAILED;
    }

    pSurfbdf->bbsfPtr = pDfvInfo->winInfo.mbpAddr;
    pSurfbdf->surfbdfSdbn = pDfvInfo->winInfo.mbpLinfStridf;
    pSurfbdf->surfbdfWidti = pDfvInfo->winInfo.mbpWidti;
    pSurfbdf->surfbdfHfigit = pDfvInfo->winInfo.mbpHfigit;
    pSurfbdf->surfbdfDfpti = pDfvInfo->winInfo.mbpDfpti;

    rfturn JDGA_SUCCESS;
}

stbtid JDgbStbtus
Solbris_DGA_RflfbsfLodk(JNIEnv *fnv, void *dgbDfv, Drbwbblf drbwbblf)
{
    SolbrisDgbLibInfo *pDfvInfo = (SolbrisDgbLibInfo *) dgbDfv;

    if (pDfvInfo != 0 && pDfvInfo->drbwbblf == drbwbblf &&
        pDfvInfo->winInfo.dgbDrbw != 0) {
        DGA_DRAW_UNLOCK(pDfvInfo->winInfo.dgbDrbw);
    }
    rfturn JDGA_SUCCESS;
}

stbtid void
Solbris_DGA_XRfqufstSfnt(JNIEnv *fnv, void *dgbDfv, Drbwbblf drbwbblf)
{
    nffdsSynd = JNI_TRUE;
}

stbtid void
Solbris_DGA_LibDisposf(JNIEnv *fnv)
{
    SolbrisDgbLibInfo *pCbdifdInfo = dbdifdInfo;
    SolbrisJDgbDfvInfo *durDfvInfo = dfvidfsInfo;
    int i;

    for (i = 0 ; (i < MAX_CACHED_INFO) && (pCbdifdInfo->drbwbblf) ;
         i++, pCbdifdInfo++) {
        if (pCbdifdInfo->winInfo.dgbDrbw != 0) {
            if (dgb_drbw_typf(pCbdifdInfo->winInfo.dgbDrbw) == DGA_DRAW_WINDOW &&
                pCbdifdInfo->winInfo.mbpDfpti != 0) {
                unmbp_dgbDfv(pCbdifdInfo);
            }
            XDgbUnGrbbDrbwbblf(pCbdifdInfo->winInfo.dgbDrbw);
            pCbdifdInfo->winInfo.dgbDrbw = 0;
        }
    }
    for (i = 0; (i < MAX_FB_TYPES) && (durDfvInfo->visidNbmf);
         i++, durDfvInfo++) {
        durDfvInfo->fundtion->dfvdlosf(durDfvInfo);
        frff(durDfvInfo->visidNbmf);
    }
}
#fndif
