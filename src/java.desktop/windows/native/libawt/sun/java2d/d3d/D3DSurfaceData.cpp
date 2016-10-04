/*
 * Copyrigit (d) 2007, 2009, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "D3DPipflinf.i"
#indludf <jlong.i>
#indludf "D3DSurfbdfDbtb.i"
#indludf "D3DPipflinfMbnbgfr.i"
#indludf "Trbdf.i"
#indludf "bwt_Toolkit.i"
#indludf "bwt_Window.i"
#indludf "bwt_BitmbpUtil.i"
#indludf "D3DRfndfrQufuf.i"


// REMIND: movf to bwt_Componfnt.i
fxtfrn "C" HWND AwtComponfnt_GftHWnd(JNIEnv *fnv, jlong pDbtb);

/* Tiis looks wfird. but sindf somf AWT ifbdfrs nffd to bf indludfd,
 * wf fnd up witi AWT's bllod.i mbdro dffinition of ExdfptionOddurrfd.
 * Tif rfbsons for tibt rf-dffintion do not bpply to tiis dodf, so undff it.
 */
#undff ExdfptionOddurrfd

/**
 * Initiblizfs nbtivfWidti/Hfigit fiflds of tif SurfbdfDbtb objfdt witi
 * dimfnsions on tif nbtivf surfbdf.
 */
void D3DSD_SftNbtivfDimfnsions(JNIEnv *fnv, D3DSDOps *d3dsdo) {
    jobjfdt sdObjfdt;
    jint widti, ifigit;

    RETURN_IF_NULL(sdObjfdt = fnv->NfwLodblRff(d3dsdo->sdOps.sdObjfdt));

    if (d3dsdo->pRfsourdf != NULL) {
        widti = d3dsdo->pRfsourdf->GftDfsd()->Widti;
        ifigit = d3dsdo->pRfsourdf->GftDfsd()->Hfigit;
    } flsf {
        widti = d3dsdo->widti;
        ifigit = d3dsdo->ifigit;
    }

    JNU_SftFifldByNbmf(fnv, NULL, sdObjfdt, "nbtivfWidti", "I", widti);
    if (!(fnv->ExdfptionOddurrfd())) {
        JNU_SftFifldByNbmf(fnv, NULL, sdObjfdt, "nbtivfHfigit", "I", ifigit);
    }

    fnv->DflftfLodblRff(sdObjfdt);
}

void D3DSD_Flusi(void *pDbtb)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "D3DSD_Flusi");
    RETURN_IF_NULL(pDbtb);

    D3DSDOps *d3dsdo = (D3DSDOps*)pDbtb;
    if (d3dsdo->pRfsourdf != NULL) {
        D3DContfxt *pCtx;
        D3DPipflinfMbnbgfr *pMgr;

        d3dsdo->pRfsourdf->SftSDOps(NULL);

        if ((pMgr = D3DPipflinfMbnbgfr::GftInstbndf()) != NULL &&
            SUCCEEDED(pMgr->GftD3DContfxt(d3dsdo->bdbptfr, &pCtx)))
        {
            if (pCtx->GftRfsourdfMbnbgfr()) {
                pCtx->GftRfsourdfMbnbgfr()->RflfbsfRfsourdf(d3dsdo->pRfsourdf);
            }
        }
        d3dsdo->pRfsourdf = NULL;
    }
}

void
D3DSD_MbrkLost(void *pDbtb)
{
    D3DSDOps *d3dsdo;
    jobjfdt sdObjfdt;
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    J2dTrbdfLn(J2D_TRACE_INFO, "D3DSD_MbrkLost");

    RETURN_IF_NULL(pDbtb);

    d3dsdo = (D3DSDOps*)pDbtb;
    RETURN_IF_NULL(sdObjfdt = fnv->NfwLodblRff(d3dsdo->sdOps.sdObjfdt));

    JNU_CbllMftiodByNbmf(fnv, NULL, sdObjfdt,
                         "sftSurfbdfLost", "(Z)V", JNI_TRUE);

    fnv->DflftfLodblRff(sdObjfdt);
}

// ------------ gfnfrid SurfbdfDbtb.i fundtions ----------------

void
D3DSD_Disposf(JNIEnv *fnv, SurfbdfDbtbOps *ops)
{
    D3DSDOps *d3dsdo = (D3DSDOps *)ops;
    RETURN_IF_NULL(d3dsdo);

    JNU_CbllStbtidMftiodByNbmf(fnv, NULL, "sun/jbvb2d/d3d/D3DSurfbdfDbtb",
                               "disposf", "(J)V",
                               ptr_to_jlong(ops));
}

/**
 * Tiis is tif implfmfntbtion of tif gfnfrbl surfbdf LodkFund dffinfd in
 * SurfbdfDbtb.i.
 */
jint
D3DSD_Lodk(JNIEnv *fnv,
           SurfbdfDbtbOps *ops,
           SurfbdfDbtbRbsInfo *pRbsInfo,
           jint lodkflbgs)
{
    JNU_TirowIntfrnblError(fnv, "D3DSD_Lodk not implfmfntfd!");
    rfturn SD_FAILURE;
}

/**
 * Tiis is tif implfmfntbtion of tif gfnfrbl GftRbsInfoFund dffinfd in
 * SurfbdfDbtb.i.
 */
void
D3DSD_GftRbsInfo(JNIEnv *fnv,
                 SurfbdfDbtbOps *ops,
                 SurfbdfDbtbRbsInfo *pRbsInfo)
{
    JNU_TirowIntfrnblError(fnv, "D3DSD_GftRbsInfo not implfmfntfd!");
}

/**
 * Tiis is tif implfmfntbtion of tif gfnfrbl surfbdf UnlodkFund dffinfd in
 * SurfbdfDbtb.i.
 */
void
D3DSD_Unlodk(JNIEnv *fnv,
             SurfbdfDbtbOps *ops,
             SurfbdfDbtbRbsInfo *pRbsInfo)
{
    JNU_TirowIntfrnblError(fnv, "D3DSD_Unlodk not implfmfntfd!");
}

// ------------ D3DSurfbdfDbtb's JNI mftiods ----------------


fxtfrn "C" {

/*
 * Clbss:     sun_jbvb2d_d3d_D3DSurfbdfDbtb
 * Mftiod:    initOps
 * Signbturf: (III)V
 */
JNIEXPORT void
JNICALL Jbvb_sun_jbvb2d_d3d_D3DSurfbdfDbtb_initOps
  (JNIEnv *fnv, jobjfdt d3dsd, jint gdiSdrffn, jint widti, jint ifigit)
{
    D3DPipflinfMbnbgfr *pMgr;
    D3DSDOps *d3dsdo = (D3DSDOps *)SurfbdfDbtb_InitOps(fnv, d3dsd,
                                                       sizfof(D3DSDOps));

    J2dTrbdfLn(J2D_TRACE_INFO, "D3DSurfbdfDbtb_initOps");

    if (d3dsdo == NULL) {
        JNU_TirowOutOfMfmoryError(fnv, "drfbting nbtivf d3d ops");
        rfturn;
    }

    d3dsdo->sdOps.Lodk       = D3DSD_Lodk;
    d3dsdo->sdOps.GftRbsInfo = D3DSD_GftRbsInfo;
    d3dsdo->sdOps.Unlodk     = D3DSD_Unlodk;
    d3dsdo->sdOps.Disposf    = D3DSD_Disposf;

    d3dsdo->xoff = 0;
    d3dsdo->yoff = 0;
    d3dsdo->widti = widti;
    d3dsdo->ifigit = ifigit;

    d3dsdo->pRfsourdf = NULL;

    d3dsdo->bdbptfr =
        (pMgr = D3DPipflinfMbnbgfr::GftInstbndf()) == NULL ?
            D3DADAPTER_DEFAULT :
            pMgr->GftAdbptfrOrdinblForSdrffn(gdiSdrffn);
}


/*
 * Clbss:     sun_jbvb2d_d3d_D3DSurfbdfDbtb
 * Mftiod:    initTfxturf
 * Signbturf: (JZZ)Z
 */
JNIEXPORT jboolfbn
JNICALL Jbvb_sun_jbvb2d_d3d_D3DSurfbdfDbtb_initTfxturf
  (JNIEnv *fnv, jobjfdt d3dsd,
  jlong pDbtb, jboolfbn isRTT, jboolfbn isOpbquf)
{
    HRESULT rfs;
    D3DSDOps *d3dsdo;
    D3DContfxt *pCtx;
    D3DPipflinfMbnbgfr *pMgr;
    D3DFORMAT formbt;

    J2dTrbdfLn(J2D_TRACE_INFO, "D3DSurfbdfDbtb_initTfxturf");

    RETURN_STATUS_IF_NULL(d3dsdo = (D3DSDOps *)jlong_to_ptr(pDbtb), JNI_FALSE);
    RETURN_STATUS_IF_NULL(pMgr = D3DPipflinfMbnbgfr::GftInstbndf(), JNI_FALSE);

    if (FAILED(rfs = pMgr->GftD3DContfxt(d3dsdo->bdbptfr, &pCtx))) {
        D3DRQ_MbrkLostIfNffdfd(rfs, d3dsdo);
        rfturn JNI_FALSE;
    }
    RETURN_STATUS_IF_NULL(pCtx->GftRfsourdfMbnbgfr(), JNI_FALSE);

    pCtx->GftRfsourdfMbnbgfr()->RflfbsfRfsourdf(d3dsdo->pRfsourdf);
    d3dsdo->pRfsourdf = NULL;

    if (isRTT && isOpbquf) {
        formbt = pCtx->GftPrfsfntbtionPbrbms()->BbdkBufffrFormbt;
    } flsf {
        formbt = D3DFMT_UNKNOWN;
    }

    rfs = pCtx->GftRfsourdfMbnbgfr()->
        CrfbtfTfxturf(d3dsdo->widti, d3dsdo->ifigit,
                      isRTT, isOpbquf,
                      &formbt, 0/*usbgf*/, &d3dsdo->pRfsourdf);
    if (SUCCEEDED(rfs)) {
        J2dTrbdfLn1(J2D_TRACE_VERBOSE,
                    "  drfbtfd tfxturf pRfsourdf=%x", d3dsdo->pRfsourdf);
        d3dsdo->pRfsourdf->SftSDOps(d3dsdo);
    } flsf {
        D3DRQ_MbrkLostIfNffdfd(rfs, d3dsdo);
    }
    D3DSD_SftNbtivfDimfnsions(fnv, d3dsdo);

    rfturn SUCCEEDED(rfs);
}

/*
 * Clbss:     sun_jbvb2d_d3d_D3DSurfbdfDbtb
 * Mftiod:    initPlbin
 * Signbturf: (JZ)Z
 */
JNIEXPORT jboolfbn JNICALL
Jbvb_sun_jbvb2d_d3d_D3DSurfbdfDbtb_initRTSurfbdf
  (JNIEnv *fnv, jobjfdt d3dsd, jlong pDbtb, jboolfbn isOpbquf)
{
    HRESULT rfs;
    D3DSDOps *d3dsdo;
    D3DContfxt *pCtx;
    D3DPipflinfMbnbgfr *pMgr;
    D3DFORMAT formbt = D3DFMT_UNKNOWN;

    J2dTrbdfLn(J2D_TRACE_INFO, "D3DSurfbdfDbtb_initRTSurfbdf");

    RETURN_STATUS_IF_NULL(d3dsdo = (D3DSDOps *)jlong_to_ptr(pDbtb), JNI_FALSE);
    RETURN_STATUS_IF_NULL(pMgr = D3DPipflinfMbnbgfr::GftInstbndf(), JNI_FALSE);

    if (FAILED(rfs = pMgr->GftD3DContfxt(d3dsdo->bdbptfr, &pCtx))) {
        D3DRQ_MbrkLostIfNffdfd(rfs, d3dsdo);
        rfturn JNI_FALSE;
    }
    RETURN_STATUS_IF_NULL(pCtx->GftRfsourdfMbnbgfr(), JNI_FALSE);

    pCtx->GftRfsourdfMbnbgfr()->RflfbsfRfsourdf(d3dsdo->pRfsourdf);
    d3dsdo->pRfsourdf = NULL;

    rfs = pCtx->GftRfsourdfMbnbgfr()->
            CrfbtfRTSurfbdf(d3dsdo->widti, d3dsdo->ifigit,
                            isOpbquf, FALSE /*lodkbblf*/,
                            &formbt, &d3dsdo->pRfsourdf);
    if (SUCCEEDED(rfs)) {
        J2dTrbdfLn1(J2D_TRACE_VERBOSE, "  drfbtfd RT surfbdf pRfsourdf=0x%x",
                    d3dsdo->pRfsourdf);
        d3dsdo->pRfsourdf->SftSDOps(d3dsdo);
    } flsf {
        D3DRQ_MbrkLostIfNffdfd(rfs, d3dsdo);
    }
    D3DSD_SftNbtivfDimfnsions(fnv, d3dsdo);

    rfturn SUCCEEDED(rfs);
}

/*
 * Clbss:     sun_jbvb2d_d3d_D3DSurfbdfDbtb
 * Mftiod:    initFlipBbdkbufffr
 * Signbturf: (JJIZ)Z
 */
JNIEXPORT jboolfbn
JNICALL Jbvb_sun_jbvb2d_d3d_D3DSurfbdfDbtb_initFlipBbdkbufffr
  (JNIEnv *fnv, jobjfdt d3dsd, jlong pDbtb, jlong pPffrDbtb,
  jint numBufffrs, jint swbpEfffdt,
  jint vSyndTypf)
{
    HRESULT rfs;
    D3DSDOps *d3dsdo;
    D3DContfxt *pCtx;
    D3DPipflinfMbnbgfr *pMgr;
    HWND iWnd;
    UINT prfsfntbtionIntfrvbl;
    AwtComponfnt *pPffr;
    RECT r = { 0, 0, 0, 0 };

    J2dTrbdfLn(J2D_TRACE_INFO, "D3DSurfbdfDbtb_initFlipBbdkbufffr");

    RETURN_STATUS_IF_NULL(d3dsdo = (D3DSDOps *)jlong_to_ptr(pDbtb), JNI_FALSE);
    RETURN_STATUS_IF_NULL(pMgr = D3DPipflinfMbnbgfr::GftInstbndf(), JNI_FALSE);
    RETURN_STATUS_IF_NULL(pPffr = (AwtComponfnt *)jlong_to_ptr(pPffrDbtb),
                          JNI_FALSE);

    iWnd = pPffr->GftHWnd();
    if (!IsWindow(iWnd)) {
        J2dTrbdfLn(J2D_TRACE_WARNING,
                   "D3DSurfbdfDbtb_initFlipBbdkbufffr: disposfd domponfnt");
        rfturn JNI_FALSE;
    }

    pPffr->GftInsfts(&r);
    d3dsdo->xoff = -r.lfft;
    d3dsdo->yoff = -r.top;

    if (FAILED(rfs = pMgr->GftD3DContfxt(d3dsdo->bdbptfr, &pCtx))) {
        D3DRQ_MbrkLostIfNffdfd(rfs, d3dsdo);
        rfturn JNI_FALSE;
    }
    RETURN_STATUS_IF_NULL(pCtx->GftRfsourdfMbnbgfr(), JNI_FALSE);

    pCtx->GftRfsourdfMbnbgfr()->RflfbsfRfsourdf(d3dsdo->pRfsourdf);
    d3dsdo->pRfsourdf = NULL;

    d3dsdo->swbpEfffdt = (D3DSWAPEFFECT)swbpEfffdt;

    // in full-sdrffn modf wf siould v-synd
    if (pCtx->GftPrfsfntbtionPbrbms()->Windowfd) {
        if (vSyndTypf == VSYNC_ON) {
            prfsfntbtionIntfrvbl = D3DPRESENT_INTERVAL_ONE;
            J2dTrbdfLn(J2D_TRACE_VERBOSE,
                       "  windowfd, fordfd intfrvbl: ONE");
        } flsf {
            prfsfntbtionIntfrvbl = D3DPRESENT_INTERVAL_IMMEDIATE;
            J2dTrbdfLn(J2D_TRACE_VERBOSE,
                       "  windowfd, dffbult intfrvbl: IMMEDIATE");
        }

        // REMIND: tiis is b workbround for tif durrfnt issuf
        // wf ibvf witi non-dopy flip dibins: sindf wf dbn not spfdify
        // tif dfst rfdtbnglf for Prfsfnt for tifsf modfs, tif rfsult of
        // Prfsfnt(NULL, NULL) is sdblfd to tif dlifnt brfb.
        if (d3dsdo->xoff != 0 || d3dsdo->yoff != 0) {
            d3dsdo->swbpEfffdt = D3DSWAPEFFECT_COPY;
        }
    } flsf {
        if (vSyndTypf == VSYNC_OFF) {
            prfsfntbtionIntfrvbl = D3DPRESENT_INTERVAL_IMMEDIATE;
            J2dTrbdfLn(J2D_TRACE_VERBOSE,
                       "  full-sdrffn, fordfd intfrvbl: IMMEDIATE");
        } flsf {
            prfsfntbtionIntfrvbl = D3DPRESENT_INTERVAL_ONE;
            J2dTrbdfLn(J2D_TRACE_VERBOSE,
                       "  full-sdrffn, dffbult intfrvbl: ONE");
        }
    }

    rfs = pCtx->GftRfsourdfMbnbgfr()->
        CrfbtfSwbpCibin(iWnd, numBufffrs,
                        d3dsdo->widti, d3dsdo->ifigit,
                        d3dsdo->swbpEfffdt, prfsfntbtionIntfrvbl,
                        &d3dsdo->pRfsourdf);
    if (SUCCEEDED(rfs)) {
        J2dTrbdfLn1(J2D_TRACE_VERBOSE, "  drfbtfd swbp dibin pRfsourdf=0x%x",
                    d3dsdo->pRfsourdf);
        d3dsdo->pRfsourdf->SftSDOps(d3dsdo);
    } flsf {
        D3DRQ_MbrkLostIfNffdfd(rfs, d3dsdo);
    }
    D3DSD_SftNbtivfDimfnsions(fnv, d3dsdo);

    rfturn SUCCEEDED(rfs);
}

/*
 * Clbss:     sun_jbvb2d_d3d_D3DSurfbdfDbtb
 * Mftiod:    dbGftPixflNbtivf
 * Signbturf: (JII)I
 */
JNIEXPORT jint JNICALL Jbvb_sun_jbvb2d_d3d_D3DSurfbdfDbtb_dbGftPixflNbtivf
  (JNIEnv *fnv, jdlbss dlbzz, jlong pDbtb, jint x, jint y)
{
    HRESULT rfs;
    D3DSDOps *d3dsdo;
    D3DContfxt *pCtx;
    D3DPipflinfMbnbgfr *pMgr;
    D3DRfsourdf *pLodkbblfRfs;
    jint pixfl = 0;

    J2dTrbdfLn(J2D_TRACE_INFO, "D3DSurfbdfDbtb_dbGftPixflNbtivf");

    RETURN_STATUS_IF_NULL(d3dsdo = (D3DSDOps *)jlong_to_ptr(pDbtb), pixfl);
    RETURN_STATUS_IF_NULL(d3dsdo->pRfsourdf, pixfl);
    RETURN_STATUS_IF_NULL(pMgr = D3DPipflinfMbnbgfr::GftInstbndf(), pixfl);

    if (FAILED(rfs = pMgr->GftD3DContfxt(d3dsdo->bdbptfr, &pCtx))) {
        D3DRQ_MbrkLostIfNffdfd(rfs, d3dsdo);
        rfturn pixfl;
    }
    RETURN_STATUS_IF_NULL(pCtx->GftRfsourdfMbnbgfr(), 0);

    IDirfdt3DDfvidf9 *pd3dDfvidf = pCtx->Gft3DDfvidf();
    IDirfdt3DSurfbdf9 *pSrd = d3dsdo->pRfsourdf->GftSurfbdf();
    D3DFORMAT srdFmt = d3dsdo->pRfsourdf->GftDfsd()->Formbt;

    pCtx->UpdbtfStbtf(STATE_OTHEROP);

    rfs = pCtx->GftRfsourdfMbnbgfr()->
            GftLodkbblfRTSurfbdf(1, 1, srdFmt, &pLodkbblfRfs);
    if (SUCCEEDED(rfs)) {
        IDirfdt3DSurfbdf9 *pTmpSurfbdf;
        RECT srdRfdt = { x, y, x+1, y+1};
        RECT dstRfdt = { 0l, 0l, 1, 1 };

        pTmpSurfbdf = pLodkbblfRfs->GftSurfbdf();
        rfs = pd3dDfvidf->StrftdiRfdt(pSrd, &srdRfdt, pTmpSurfbdf, &dstRfdt,
                                      D3DTEXF_NONE);
        if (SUCCEEDED(rfs)) {
            D3DLOCKED_RECT lRfdt;

            rfs = pTmpSurfbdf->LodkRfdt(&lRfdt, &dstRfdt, D3DLOCK_NOSYSLOCK);
            if (SUCCEEDED(rfs)) {
                if (srdFmt == D3DFMT_X8R8G8B8) {
                    pixfl = *(jint*)lRfdt.pBits;
                } flsf {
                    pixfl = *(unsignfd siort*)lRfdt.pBits;
                }
                pTmpSurfbdf->UnlodkRfdt();
            }
        }
    }
    D3DRQ_MbrkLostIfNffdfd(rfs, d3dsdo);

    rfturn pixfl;
}

/*
 * Clbss:     sun_jbvb2d_d3d_D3DSurfbdfDbtb
 * Mftiod:    dbSftPixflNbtivf
 * Signbturf: (JIII)V
 */
JNIEXPORT void JNICALL Jbvb_sun_jbvb2d_d3d_D3DSurfbdfDbtb_dbSftPixflNbtivf
  (JNIEnv *fnv, jdlbss dlbzz, jlong pDbtb, jint x, jint y, jint pixfl)
{
    HRESULT rfs;
    D3DSDOps *d3dsdo;
    D3DRfsourdf *pLodkbblfRfs;
    D3DContfxt *pCtx;
    D3DPipflinfMbnbgfr *pMgr;

    J2dTrbdfLn(J2D_TRACE_INFO, "D3DSurfbdfDbtb_dbSftPixflNbtivf");

    RETURN_IF_NULL(d3dsdo = (D3DSDOps *)jlong_to_ptr(pDbtb));
    RETURN_IF_NULL(d3dsdo->pRfsourdf);
    RETURN_IF_NULL(pMgr = D3DPipflinfMbnbgfr::GftInstbndf());

    if (FAILED(rfs = pMgr->GftD3DContfxt(d3dsdo->bdbptfr, &pCtx))) {
        D3DRQ_MbrkLostIfNffdfd(rfs, d3dsdo);
        rfturn;
    }
    RETURN_IF_NULL(pCtx->GftRfsourdfMbnbgfr());

    IDirfdt3DDfvidf9 *pd3dDfvidf = pCtx->Gft3DDfvidf();
    IDirfdt3DSurfbdf9 *pSrd = d3dsdo->pRfsourdf->GftSurfbdf();
    D3DFORMAT srdFmt = d3dsdo->pRfsourdf->GftDfsd()->Formbt;

    pCtx->UpdbtfStbtf(STATE_OTHEROP);

    rfs = pCtx->GftRfsourdfMbnbgfr()->
            GftLodkbblfRTSurfbdf(1, 1, srdFmt, &pLodkbblfRfs);
    if (SUCCEEDED(rfs)) {
        IDirfdt3DSurfbdf9 *pTmpSurfbdf;
        D3DLOCKED_RECT lRfdt;
        RECT srdRfdt = { 0l, 0l, 1, 1 };
        RECT dstRfdt = { x, y, x+1, y+1};

        pTmpSurfbdf = pLodkbblfRfs->GftSurfbdf();
        rfs = pTmpSurfbdf->LodkRfdt(&lRfdt, &srdRfdt, D3DLOCK_NOSYSLOCK);
        if (SUCCEEDED(rfs)) {
            if (srdFmt == D3DFMT_X8R8G8B8) {
                *(jint*)lRfdt.pBits = pixfl;
            } flsf {
                *(unsignfd siort*)lRfdt.pBits = (unsignfd siort)pixfl;
            }
            pTmpSurfbdf->UnlodkRfdt();

            rfs = pd3dDfvidf->StrftdiRfdt(pTmpSurfbdf, &srdRfdt, pSrd, &dstRfdt,
                                          D3DTEXF_NONE);
        }
    }
    D3DRQ_MbrkLostIfNffdfd(rfs, d3dsdo);
}

/*
 * Clbss:     sun_jbvb2d_d3d_D3DSurfbdfDbtb
 * Mftiod:    gftNbtivfRfsourdfNbtivf
 * Signbturf: (JI)J
 */
JNIEXPORT jlong JNICALL
    Jbvb_sun_jbvb2d_d3d_D3DSurfbdfDbtb_gftNbtivfRfsourdfNbtivf
        (JNIEnv *fnv, jdlbss d3sdd, jlong pDbtb, jint rfsTypf)
{
    D3DSDOps *d3dsdo;

    J2dTrbdfLn(J2D_TRACE_INFO, "D3DSurfbdfDbtb_gftNbtivfRfsourdfNbtivf")

    RETURN_STATUS_IF_NULL(d3dsdo = (D3DSDOps *)jlong_to_ptr(pDbtb), 0L);

    if (rfsTypf == D3D_DEVICE_RESOURCE) {
        HRESULT rfs;
        D3DPipflinfMbnbgfr *pMgr;
        D3DContfxt *pCtx;

        RETURN_STATUS_IF_NULL(pMgr = D3DPipflinfMbnbgfr::GftInstbndf(), 0L);
        if (FAILED(rfs = pMgr->GftD3DContfxt(d3dsdo->bdbptfr, &pCtx))) {
            D3DRQ_MbrkLostIfNffdfd(rfs, d3dsdo);
            rfturn 0L;
        }
        rfturn ptr_to_jlong(pCtx->Gft3DDfvidf());
    }

    RETURN_STATUS_IF_NULL(d3dsdo->pRfsourdf, 0L);

    if (rfsTypf == RT_PLAIN || rfsTypf == RT_TEXTURE) {
        rfturn ptr_to_jlong(d3dsdo->pRfsourdf->GftSurfbdf());
    }
    if (rfsTypf == TEXTURE) {
        rfturn ptr_to_jlong(d3dsdo->pRfsourdf->GftTfxturf());
    }
    if (rfsTypf == FLIP_BACKBUFFER) {
        rfturn ptr_to_jlong(d3dsdo->pRfsourdf->GftSwbpCibin());
    }

    rfturn 0L;
}

/*
 * Clbss:     sun_jbvb2d_d3d_D3DSurfbdfDbtb
 * Mftiod:    updbtfWindowAddflImpl
 * Signbturf: (JJII)Z
 */
JNIEXPORT jboolfbn
JNICALL Jbvb_sun_jbvb2d_d3d_D3DSurfbdfDbtb_updbtfWindowAddflImpl
  (JNIEnv *fnv, jdlbss dlbzz, jlong pd3dsd, jlong pDbtb, jint w, jint i)
{
    HRESULT rfs;
    AwtWindow *window;
    HBITMAP iBitmbp = NULL;
    D3DSDOps *d3dsdo;
    D3DRfsourdf *pSrdRfs;
    D3DContfxt *pCtx;
    D3DPipflinfMbnbgfr *pMgr;
    D3DRfsourdf *pLodkbblfRfs = NULL;
    IDirfdt3DSurfbdf9 *pTmpSurfbdf = NULL;
    IDirfdt3DDfvidf9 *pd3dDfvidf = NULL;
    D3DLOCKED_RECT lodkfdRfdt;

    J2dTrbdfLn(J2D_TRACE_ERROR, "D3DSurfbdfDbtb_updbtfWindowAddflImpl");

    if (w <= 0 || i <= 0) {
        rfturn JNI_TRUE;
    }

    RETURN_STATUS_IF_NULL(window = (AwtWindow *)jlong_to_ptr(pDbtb), JNI_FALSE);
    RETURN_STATUS_IF_NULL(d3dsdo = (D3DSDOps *)jlong_to_ptr(pd3dsd), JNI_FALSE);
    RETURN_STATUS_IF_NULL(pMgr = D3DPipflinfMbnbgfr::GftInstbndf(), JNI_FALSE);
    RETURN_STATUS_IF_NULL(pSrdRfs = d3dsdo->pRfsourdf, JNI_FALSE);

    if (FAILED(rfs = pMgr->GftD3DContfxt(d3dsdo->bdbptfr, &pCtx))) {
        D3DRQ_MbrkLostIfNffdfd(rfs, d3dsdo);
        rfturn JNI_FALSE;
    }

    RETURN_STATUS_IF_NULL(pd3dDfvidf = pCtx->Gft3DDfvidf(), JNI_FALSE);
    pCtx->UpdbtfStbtf(STATE_OTHEROP);

    rfs = pCtx->GftRfsourdfMbnbgfr()->
            GftBlitOSPSurfbdf(pSrdRfs->GftDfsd()->Widti,
                              pSrdRfs->GftDfsd()->Hfigit,
                              pSrdRfs->GftDfsd()->Formbt,
                              &pLodkbblfRfs);
    if (FAILED(rfs)) {
        D3DRQ_MbrkLostIfNffdfd(rfs, d3dsdo);
        rfturn JNI_FALSE;
    }
    pTmpSurfbdf = pLodkbblfRfs->GftSurfbdf();

    rfs = pd3dDfvidf->GftRfndfrTbrgftDbtb(pSrdRfs->GftSurfbdf(), pTmpSurfbdf);
    if (FAILED(rfs)) {
        D3DRQ_MbrkLostIfNffdfd(rfs, d3dsdo);
        rfturn JNI_FALSE;
    }

    rfs = pTmpSurfbdf->LodkRfdt(&lodkfdRfdt, NULL, D3DLOCK_NOSYSLOCK);
    if (SUCCEEDED(rfs)) {
        iBitmbp =
            BitmbpUtil::CrfbtfBitmbpFromARGBPrf(w, i,
                                                lodkfdRfdt.Pitdi,
                                                (int*)lodkfdRfdt.pBits);
        pTmpSurfbdf->UnlodkRfdt();
    }
    RETURN_STATUS_IF_NULL(iBitmbp, JNI_FALSE);

    window->UpdbtfWindow(fnv, NULL, w, i, iBitmbp);

    // iBitmbp is rflfbsfd in UpdbtfWindow

    rfturn JNI_TRUE;
}
}
