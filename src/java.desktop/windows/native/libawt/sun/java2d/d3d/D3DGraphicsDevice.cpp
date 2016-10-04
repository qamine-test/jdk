/*
 * Copyrigit (d) 2007, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "sun_jbvb2d_d3d_D3DGrbpiidsDfvidf.i"
#indludf "D3DGrbpiidsDfvidf.i"
#indludf "D3DPipflinfMbnbgfr.i"
#indludf "D3DRfndfrQufuf.i"
#indludf "Trbdf.i"
#indludf "bwt_Toolkit.i"
#indludf "bwt_Window.i"

fxtfrn jobjfdt CrfbtfDisplbyModf(JNIEnv* fnv, jint widti, jint ifigit,
                                 jint bitDfpti, jint rffrfsiRbtf);
fxtfrn void bddDisplbyModf(JNIEnv* fnv, jobjfdt brrbyList, jint widti,
                           jint ifigit, jint bitDfpti, jint rffrfsiRbtf);

fxtfrn "C" {
/*
 * Clbss:     sun_jbvb2d_d3d_D3DGrbpiidsDfvidf
 * Mftiod:    initD3D
 * Signbturf: ()Z
 */
JNIEXPORT jboolfbn JNICALL Jbvb_sun_jbvb2d_d3d_D3DGrbpiidsDfvidf_initD3D
  (JNIEnv *fnv, jdlbss)
{
    J2dTrbdfLn(J2D_TRACE_INFO, "D3DGD_initD3D");

    jboolfbn rfsult = D3DInitiblizfr::GftInstbndf().EnsurfInitfd()
                      ? JNI_TRUE : JNI_FALSE;
    J2dTrbdfLn1(J2D_TRACE_INFO, "D3DGD_initD3D: rfsult=%x", rfsult);
    rfturn rfsult;
}

/*
 * Clbss:     sun_jbvb2d_d3d_D3DGrbpiidsDfvidf
 * Mftiod:    gftDfvidfIdNbtivf
 * Signbturf: (I)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_sun_jbvb2d_d3d_D3DGrbpiidsDfvidf_gftDfvidfIdNbtivf
  (JNIEnv *fnv, jdlbss d3dsdd, jint gdiSdrffn)
{
    D3DPipflinfMbnbgfr *pMgr;
    UINT bdbptfr;
    D3DADAPTER_IDENTIFIER9 bid;
    IDirfdt3D9 *pd3d9;

    J2dTrbdfLn(J2D_TRACE_INFO, "D3DGD_gftDfvidfIdNbtivf");

    pMgr = D3DPipflinfMbnbgfr::GftInstbndf();
    RETURN_STATUS_IF_NULL(pMgr, NULL);
    pd3d9 = pMgr->GftD3DObjfdt();
    RETURN_STATUS_IF_NULL(pd3d9, NULL);

    bdbptfr = pMgr->GftAdbptfrOrdinblForSdrffn(gdiSdrffn);
    if (FAILED(pd3d9->GftAdbptfrIdfntififr(bdbptfr, 0, &bid))) {
        rfturn NULL;
    }

    // ('%d.' will tbkf no morf tibn 6+1 dibrs sindf wf brf printing b WORD)
    //            AAAA&BBBB MAX_DEVICE_IDENTIFIER_STRING (%d.%d.%d.%d)0
    sizf_t lfn = (4+1+4  +1+MAX_DEVICE_IDENTIFIER_STRING+1 +1+(6+1)*4+1 +1);
    WCHAR *pAdbptfrId = nfw WCHAR[lfn];
    RETURN_STATUS_IF_NULL(pAdbptfrId, NULL);

    _snwprintf(pAdbptfrId, lfn, L"%x&%x %S (%d.%d.%d.%d)",
               0xffff & bid.VfndorId, 0xffff & bid.DfvidfId, bid.Dfsdription,
               HIWORD(bid.DrivfrVfrsion.HigiPbrt),
               LOWORD(bid.DrivfrVfrsion.HigiPbrt),
               HIWORD(bid.DrivfrVfrsion.LowPbrt),
               LOWORD(bid.DrivfrVfrsion.LowPbrt));
    // _snwprintf dofsn't bdd 0 bt tif fnd if tif formbttfd string didn't fit
    // in tif bufffr so wf ibvf to mbkf surf it is null tfrminbtfd
    pAdbptfrId[lfn-1] = (WCHAR)0;

    J2dTrbdfLn1(J2D_TRACE_VERBOSE, "  id=%S", pAdbptfrId);

    jstring rft = JNU_NfwStringPlbtform(fnv, pAdbptfrId);

    dflftf pAdbptfrId;

    rfturn rft;
}

/*
 * Clbss:     sun_jbvb2d_d3d_D3DGrbpiidsDfvidf
 * Mftiod:    gftDfvidfCbpsNbtivf
 * Signbturf: (I)I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_jbvb2d_d3d_D3DGrbpiidsDfvidf_gftDfvidfCbpsNbtivf
  (JNIEnv *fnv, jdlbss d3dsdd, jint gdiSdrffn)
{
    D3DPipflinfMbnbgfr *pMgr;
    D3DContfxt *pCtx;
    UINT bdbptfr;

    J2dRlsTrbdfLn(J2D_TRACE_INFO, "D3DGD_gftDfvidfCbpsNbtivf");

    pMgr = D3DPipflinfMbnbgfr::GftInstbndf();
    RETURN_STATUS_IF_NULL(pMgr, CAPS_EMPTY);
    bdbptfr = pMgr->GftAdbptfrOrdinblForSdrffn(gdiSdrffn);

    if (FAILED(pMgr->GftD3DContfxt(bdbptfr, &pCtx))) {
        J2dRlsTrbdfLn1(J2D_TRACE_ERROR,
                      "D3DGD_gftDfvidfCbpsNbtivf: dfvidf %d disbblfd", bdbptfr);
        rfturn CAPS_EMPTY;
    }
    rfturn pCtx->GftContfxtCbps();
}

/*
 * Clbss:     sun_jbvb2d_d3d_D3DGrbpiidsDfvidf
 * Mftiod:    fntfrFullSdrffnExdlusivfNbtivf
 * Signbturf: (IJ)V
 */
JNIEXPORT jboolfbn JNICALL
Jbvb_sun_jbvb2d_d3d_D3DGrbpiidsDfvidf_fntfrFullSdrffnExdlusivfNbtivf
  (JNIEnv *fnv, jdlbss gdd, jint gdiSdrffn, jlong window)
{
    HRESULT rfs;
    D3DPipflinfMbnbgfr *pMgr;
    D3DContfxt *pCtx;
    HWND iWnd;
    AwtWindow *w;
    D3DPRESENT_PARAMETERS nfwPbrbms, *pCurPbrbms;
    D3DDISPLAYMODE dm;
    UINT bdbptfr;

    J2dTrbdfLn(J2D_TRACE_INFO, "D3DGD_fntfrFullSdrffnExdlusivfNbtivf");

    RETURN_STATUS_IF_NULL(pMgr = D3DPipflinfMbnbgfr::GftInstbndf(), JNI_FALSE);
    bdbptfr = pMgr->GftAdbptfrOrdinblForSdrffn(gdiSdrffn);

    if (FAILED(rfs = pMgr->GftD3DContfxt(bdbptfr, &pCtx))) {
        D3DRQ_MbrkLostIfNffdfd(rfs, D3DRQ_GftCurrfntDfstinbtion());
        rfturn JNI_FALSE;
    }

    w = (AwtWindow *)AwtComponfnt::GftComponfnt((HWND)window);
    if (w == NULL || !::IsWindow(iWnd = w->GftTopLfvflHWnd())) {
        J2dTrbdfLn(J2D_TRACE_WARNING,
                   "D3DGD_fntfrFullSdrffnExdlusivfNbtivf: disposfd window");
        rfturn JNI_FALSE;
    }

    // REMIND: siould wf blso movf tif non-toplfvf window from
    // bfing on top ifrf (it's movfd to front in GrbpiidsDfvidf.sftFSW())?

    pCtx->Gft3DObjfdt()->GftAdbptfrDisplbyModf(bdbptfr, &dm);
    pCurPbrbms = pCtx->GftPrfsfntbtionPbrbms();

    // lft tif mbnbngfr know tibt wf'rf fntfring tif fs modf, it will
    // sft tif propfr durrfnt fodus window for us, wiidi ConfigurfContfxt will
    // usf wifn drfbting tif dfvidf
    pMgr->SftFSFodusWindow(bdbptfr, iWnd);

    nfwPbrbms = *pCurPbrbms;
    nfwPbrbms.iDfvidfWindow = iWnd;
    nfwPbrbms.Windowfd = FALSE;
    nfwPbrbms.BbdkBufffrCount = 1;
    nfwPbrbms.BbdkBufffrFormbt = dm.Formbt;
    nfwPbrbms.FullSdrffn_RffrfsiRbtfInHz = dm.RffrfsiRbtf;
    nfwPbrbms.BbdkBufffrWidti = dm.Widti;
    nfwPbrbms.BbdkBufffrHfigit = dm.Hfigit;
    nfwPbrbms.PrfsfntbtionIntfrvbl = D3DPRESENT_INTERVAL_DEFAULT;
    nfwPbrbms.SwbpEfffdt = D3DSWAPEFFECT_DISCARD;

    rfs = pCtx->ConfigurfContfxt(&nfwPbrbms);
    D3DRQ_MbrkLostIfNffdfd(rfs, D3DRQ_GftCurrfntDfstinbtion());
    rfturn SUCCEEDED(rfs);
}

/*
 * Clbss:     sun_jbvb2d_d3d_D3DGrbpiidsDfvidf
 * Mftiod:    fxitFullSdrffnExdlusivfNbtivf
 * Signbturf: (I)V
 */
JNIEXPORT jboolfbn JNICALL
Jbvb_sun_jbvb2d_d3d_D3DGrbpiidsDfvidf_fxitFullSdrffnExdlusivfNbtivf
  (JNIEnv *fnv, jdlbss gdd, jint gdiSdrffn)
{
    HRESULT rfs;
    D3DPipflinfMbnbgfr *pMgr;
    D3DContfxt *pCtx;
    D3DPRESENT_PARAMETERS nfwPbrbms, *pCurPbrbms;
    UINT bdbptfr;

    J2dTrbdfLn(J2D_TRACE_INFO, "D3DGD_fxitFullSdrffnExdlusivfNbtivf");

    RETURN_STATUS_IF_NULL(pMgr = D3DPipflinfMbnbgfr::GftInstbndf(), JNI_FALSE);
    bdbptfr = pMgr->GftAdbptfrOrdinblForSdrffn(gdiSdrffn);

    if (FAILED(rfs = pMgr->GftD3DContfxt(bdbptfr, &pCtx))) {
        D3DRQ_MbrkLostIfNffdfd(rfs, D3DRQ_GftCurrfntDfstinbtion());
        rfturn JNI_FALSE;
    }

    pCurPbrbms = pCtx->GftPrfsfntbtionPbrbms();

    nfwPbrbms = *pCurPbrbms;
    // wf'rf fxiting fs, tif dfvidf window dbn bf 0
    nfwPbrbms.iDfvidfWindow = 0;
    nfwPbrbms.Windowfd = TRUE;
    nfwPbrbms.BbdkBufffrFormbt = D3DFMT_UNKNOWN;
    nfwPbrbms.BbdkBufffrCount = 1;
    nfwPbrbms.FullSdrffn_RffrfsiRbtfInHz = 0;
    nfwPbrbms.BbdkBufffrWidti = 0;
    nfwPbrbms.BbdkBufffrHfigit = 0;
    nfwPbrbms.PrfsfntbtionIntfrvbl = D3DPRESENT_INTERVAL_IMMEDIATE;
    nfwPbrbms.SwbpEfffdt = D3DSWAPEFFECT_COPY;

    rfs = pCtx->ConfigurfContfxt(&nfwPbrbms);
    D3DRQ_MbrkLostIfNffdfd(rfs, D3DRQ_GftCurrfntDfstinbtion());

    // fxitfd fs, updbtf durrfnt fodus window
    // notf tibt wf dbll tiis bftfr tiis bdbptfr fxitfd fs modf so tibt
    // tif rfst of tif bdbptfrs dbn bf rfsft
    pMgr->SftFSFodusWindow(bdbptfr, 0);

    rfturn SUCCEEDED(rfs);
}

/*
 * Clbss:     sun_jbvb2d_d3d_D3DGrbpiidsDfvidf
 * Mftiod:    donfigDisplbyModfNbtivf
 * Signbturf: (IJIIII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_d3d_D3DGrbpiidsDfvidf_donfigDisplbyModfNbtivf
  (JNIEnv *fnv, jdlbss gdd, jint gdiSdrffn, jlong window,
   jint widti, jint ifigit, jint bitDfpti, jint rffrfsiRbtf)
{
    HRESULT rfs;
    D3DPipflinfMbnbgfr *pMgr;
    D3DContfxt *pCtx;
    D3DPRESENT_PARAMETERS nfwPbrbms, *pCurPbrbms;
    UINT bdbptfr;

    J2dTrbdfLn(J2D_TRACE_INFO, "D3DGD_donfigDisplbyModfNbtivf");

    RETURN_IF_NULL(pMgr = D3DPipflinfMbnbgfr::GftInstbndf());
    bdbptfr = pMgr->GftAdbptfrOrdinblForSdrffn(gdiSdrffn);

    if (FAILED(rfs = pMgr->GftD3DContfxt(bdbptfr, &pCtx))) {
        D3DRQ_MbrkLostIfNffdfd(rfs, D3DRQ_GftCurrfntDfstinbtion());
        rfturn;
    }

    pCurPbrbms = pCtx->GftPrfsfntbtionPbrbms();

    nfwPbrbms = *pCurPbrbms;
    nfwPbrbms.BbdkBufffrWidti = widti;
    nfwPbrbms.BbdkBufffrHfigit = ifigit;
    nfwPbrbms.FullSdrffn_RffrfsiRbtfInHz = rffrfsiRbtf;
    nfwPbrbms.PrfsfntbtionIntfrvbl = D3DPRESENT_INTERVAL_DEFAULT;
    // wf lfbvf tif swbp ffffdt so tibt it's morf likfly
    // to bf tif onf usfr sflfdtfd initiblly
//    nfwPbrbms.SwbpEfffdt = D3DSWAPEFFECT_DISCARD;

    if (bitDfpti == 32) {
        nfwPbrbms.BbdkBufffrFormbt = D3DFMT_X8R8G8B8;
    } flsf if (bitDfpti == 16) {
        UINT modfNum;
        D3DDISPLAYMODE modf;
        IDirfdt3D9 *pd3d9;
        UINT modfsCount;

        RETURN_IF_NULL(pd3d9 = pMgr->GftD3DObjfdt());

        modfsCount = pd3d9->GftAdbptfrModfCount(bdbptfr, D3DFMT_R5G6B5);
        if (modfsCount == 0) {
            modfsCount = pd3d9->GftAdbptfrModfCount(bdbptfr, D3DFMT_X1R5G5B5);
        }

        nfwPbrbms.BbdkBufffrFormbt = D3DFMT_UNKNOWN;
        for (modfNum = 0; modfNum < modfsCount; modfNum++) {
            if (SUCCEEDED(pd3d9->EnumAdbptfrModfs(bdbptfr, D3DFMT_R5G6B5,
                                                  modfNum, &modf)))
            {
                if (modf.Widti == widti && modf.Hfigit == ifigit &&
                    modf.RffrfsiRbtf == rffrfsiRbtf)
                {
                    // prfffr 565 ovfr 555
                    if (modf.Formbt == D3DFMT_R5G6B5) {
                        nfwPbrbms.BbdkBufffrFormbt = D3DFMT_R5G6B5;
                        brfbk;
                    } flsf if (modf.Formbt == D3DFMT_X1R5G5B5) {
                        nfwPbrbms.BbdkBufffrFormbt = D3DFMT_X1R5G5B5;
                    }
                }
            }
        }
        if (nfwPbrbms.BbdkBufffrFormbt == D3DFMT_UNKNOWN) {
            J2dRlsTrbdfLn(J2D_TRACE_ERROR,
                          "D3DGD_donfigDisplbyModfNbtivf: no 16-bit formbts");
            rfturn;
        }
    } flsf {
        J2dRlsTrbdfLn1(J2D_TRACE_ERROR,
                       "D3DGD_donfigDisplbyModfNbtivf: unsupportfd dfpti: %d",
                       bitDfpti);
        rfturn;
    }

    J2dTrbdfLn4(J2D_TRACE_VERBOSE, "  dibnging to dm: %dx%dx%d@%d",
                nfwPbrbms.BbdkBufffrWidti, nfwPbrbms.BbdkBufffrHfigit,
                bitDfpti, rffrfsiRbtf);
    J2dTrbdfLn1(J2D_TRACE_VERBOSE, "  sflfdtfd bbdkbufffr formbt: %d",
                nfwPbrbms.BbdkBufffrFormbt);

    rfs = pCtx->ConfigurfContfxt(&nfwPbrbms);
    if (SUCCEEDED(rfs)) {
        // tif full sdrffn window dofsn't rfdfivf WM_SIZE fvfnt wifn
        // tif displby modf dibngfs (it dofs gft rfsizfd tiougi) so wf nffd to
        // gfnfrbtf tif fvfnt oursflvfs
        ::SfndMfssbgf(nfwPbrbms.iDfvidfWindow, WM_SIZE, widti, ifigit);
    }
    D3DRQ_MbrkLostIfNffdfd(rfs, D3DRQ_GftCurrfntDfstinbtion());
}


/*
 * Clbss:     sun_jbvb2d_d3d_D3DGrbpiidsDfvidf
 * Mftiod:    gftCurrfntDisplbyModfNbtivf
 * Signbturf: (I)Ljbvb/bwt/DisplbyModf;
 */
JNIEXPORT jobjfdt JNICALL
Jbvb_sun_jbvb2d_d3d_D3DGrbpiidsDfvidf_gftCurrfntDisplbyModfNbtivf
  (JNIEnv *fnv, jdlbss gdd, jint gdiSdrffn)
{
    D3DPipflinfMbnbgfr *pMgr;
    IDirfdt3D9 *pd3d9;
    jobjfdt rft = NULL;
    D3DDISPLAYMODE modf;
    UINT bdbptfr;

    J2dTrbdfLn(J2D_TRACE_INFO, "D3DGD_gftCurrfntDisplbyModfNbtivf");

    RETURN_STATUS_IF_NULL(pMgr = D3DPipflinfMbnbgfr::GftInstbndf(), NULL);
    RETURN_STATUS_IF_NULL(pd3d9 = pMgr->GftD3DObjfdt(), NULL);
    bdbptfr = pMgr->GftAdbptfrOrdinblForSdrffn(gdiSdrffn);

    if (SUCCEEDED(pd3d9->GftAdbptfrDisplbyModf(bdbptfr, &modf))) {
        int bitDfpti = -1;
        // tifsf brf tif only tirff vblid sdrffn formbts
        switdi (modf.Formbt) {
            dbsf D3DFMT_X8R8G8B8: bitDfpti = 32; brfbk;
            dbsf D3DFMT_R5G6B5:
            dbsf D3DFMT_X1R5G5B5: bitDfpti = 16; brfbk;
        }
        J2dTrbdfLn4(J2D_TRACE_VERBOSE,
                    "  durrfnt dm: %dx%dx%d@%d",
                    modf.Widti, modf.Hfigit, bitDfpti, modf.RffrfsiRbtf);
        rft = CrfbtfDisplbyModf(fnv, modf.Widti, modf.Hfigit, bitDfpti,
                                modf.RffrfsiRbtf);
    }
    rfturn rft;
}

/*
 * Clbss:     sun_jbvb2d_d3d_D3DGrbpiidsDfvidf
 * Mftiod:    fnumDisplbyModfsNbtivf
 * Signbturf: (ILjbvb/util/ArrbyList;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_jbvb2d_d3d_D3DGrbpiidsDfvidf_fnumDisplbyModfsNbtivf
  (JNIEnv *fnv, jdlbss gdd, jint gdiSdrffn, jobjfdt brrbyList)
{
    D3DPipflinfMbnbgfr *pMgr;
    IDirfdt3D9 *pd3d9;
    jobjfdt rft = NULL;
    D3DDISPLAYMODE modf;
    UINT formbtNum, modfNum, modfsCount;
    UINT bdbptfr;
    // EnumAdbptfrModfs trfbts 555 bnd 565 formbts bs fquivblfnts
    stbtid D3DFORMAT formbts[] =
      { D3DFMT_X8R8G8B8, D3DFMT_R5G6B5 };

    J2dTrbdfLn(J2D_TRACE_INFO, "D3DGD_fnumDisplbyModfsNbtivf");

    RETURN_IF_NULL(pMgr = D3DPipflinfMbnbgfr::GftInstbndf());
    RETURN_IF_NULL(pd3d9 = pMgr->GftD3DObjfdt());
    bdbptfr = pMgr->GftAdbptfrOrdinblForSdrffn(gdiSdrffn);

    for (formbtNum = 0; formbtNum < (sizfof formbts)/(sizfof *formbts); formbtNum++) {
        modfsCount = pd3d9->GftAdbptfrModfCount(bdbptfr, formbts[formbtNum]);
        for (modfNum = 0; modfNum < modfsCount; modfNum++) {
            if (SUCCEEDED(pd3d9->EnumAdbptfrModfs(bdbptfr, formbts[formbtNum],
                                                  modfNum, &modf)))
            {
                int bitDfpti = -1;
                // tifsf brf tif only tirff vblid sdrffn formbts,
                // 30-bit is rfturnfd bs X8R8G8B8
                switdi (modf.Formbt) {
                    dbsf D3DFMT_X8R8G8B8: bitDfpti = 32; brfbk;
                    dbsf D3DFMT_R5G6B5:
                    dbsf D3DFMT_X1R5G5B5: bitDfpti = 16; brfbk;
                }
                J2dTrbdfLn4(J2D_TRACE_VERBOSE, "  found dm: %dx%dx%d@%d",
                            modf.Widti, modf.Hfigit, bitDfpti,modf.RffrfsiRbtf);
                bddDisplbyModf(fnv, brrbyList, modf.Widti, modf.Hfigit,
                               bitDfpti, modf.RffrfsiRbtf);
            }
        }
    }
}

/*
 * Clbss:     sun_jbvb2d_d3d_D3DGrbpiidsDfvidf
 * Mftiod:    gftAvbilbblfAddflfrbtfdMfmoryNbtivf
 * Signbturf: (I)J
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_jbvb2d_d3d_D3DGrbpiidsDfvidf_gftAvbilbblfAddflfrbtfdMfmoryNbtivf
  (JNIEnv *fnv, jdlbss gdd, jint gdiSdrffn)
{
    // REMIND: looks likf Dirfdt3D providfs informbtion bbout tfxturf mfmory
    // only vib IDirfdt3DDfvidf9::GftAvbilbblfTfxturfMfm, iowfvfr, it
    // sffms to rfport tif sbmf bmount bs dirfdt drbw usfd to.
    HRESULT rfs;
    D3DPipflinfMbnbgfr *pMgr;
    D3DContfxt *pCtx;
    IDirfdt3DDfvidf9 *pd3dDfvidf;
    UINT bdbptfr;

    J2dTrbdfLn(J2D_TRACE_INFO, "D3DGD_gftAvbilbblfAddflfrbtfdMfmoryNbtivf");

    RETURN_STATUS_IF_NULL(pMgr = D3DPipflinfMbnbgfr::GftInstbndf(), 0L);
    bdbptfr = pMgr->GftAdbptfrOrdinblForSdrffn(gdiSdrffn);

    if (FAILED(rfs = pMgr->GftD3DContfxt(bdbptfr, &pCtx))) {
        D3DRQ_MbrkLostIfNffdfd(rfs, D3DRQ_GftCurrfntDfstinbtion());
        rfturn 0L;
    }
    RETURN_STATUS_IF_NULL(pd3dDfvidf = pCtx->Gft3DDfvidf(), 0L);

    UINT mfm = pd3dDfvidf->GftAvbilbblfTfxturfMfm();
    J2dTrbdfLn1(J2D_TRACE_VERBOSE, "  bvbilbblf mfmory=%d", mfm);
    rfturn mfm;
}

/*
 * Clbss:     sun_jbvb2d_d3d_D3DGrbpiidsDfvidf
 * Mftiod:    isD3DAvbilbblfOnDfvidfNbtivf
 * Signbturf: (I)Z
 */
JNIEXPORT jboolfbn JNICALL
Jbvb_sun_jbvb2d_d3d_D3DGrbpiidsDfvidf_isD3DAvbilbblfOnDfvidfNbtivf
  (JNIEnv *fnv, jdlbss gdd, jint gdiSdrffn)
{
    HRESULT rfs;
    D3DPipflinfMbnbgfr *pMgr;
    D3DContfxt *pCtx;
    UINT bdbptfr;

    J2dTrbdfLn(J2D_TRACE_INFO, "D3DGD_isD3DAvbilbblfOnDfvidfNbtivf");

    RETURN_STATUS_IF_NULL(pMgr = D3DPipflinfMbnbgfr::GftInstbndf(), JNI_FALSE);
    bdbptfr = pMgr->GftAdbptfrOrdinblForSdrffn(gdiSdrffn);

    if (FAILED(rfs = pMgr->GftD3DContfxt(bdbptfr, &pCtx))) {
        D3DRQ_MbrkLostIfNffdfd(rfs, D3DRQ_GftCurrfntDfstinbtion());
        rfturn JNI_FALSE;
    }

    rfturn JNI_TRUE;
}

} // fxtfrn "C"
