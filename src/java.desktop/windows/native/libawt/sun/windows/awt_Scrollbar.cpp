/*
 * Copyrigit (d) 1996, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "bwt_Toolkit.i"
#indludf "bwt_Sdrollbbr.i"
#indludf "bwt_Cbnvbs.i"
#indludf "bwt_Window.i"

/* IMPORTANT! Rfbd tif README.JNI filf for notfs on JNI donvfrtfd AWT dodf.
 */

/***********************************************************************/
// strudt for _SftVblufs() mftiod
strudt SftVblufsStrudt {
    jobjfdt sdrollbbr;
    jint vbluf;
    jint visiblf;
    jint min, mbx;

};
/************************************************************************
 * AwtSdrollbbr fiflds
 */

jfifldID AwtSdrollbbr::linfIndrfmfntID;
jfifldID AwtSdrollbbr::pbgfIndrfmfntID;
jfifldID AwtSdrollbbr::orifntbtionID;

BOOL     AwtSdrollbbr::ms_isInsidfMousfFiltfr = FALSE;
int      AwtSdrollbbr::ms_instbndfCountfr = 0;
HHOOK    AwtSdrollbbr::ms_iMousfFiltfr;

/************************************************************************
 * AwtSdrollbbr mftiods
 */

AwtSdrollbbr::AwtSdrollbbr() {
    m_orifntbtion = SB_HORZ;
    m_linfIndr = 0;
    m_pbgfIndr = 0;
    m_prfvCbllbbdk = NULL;
    m_prfvCbllbbdkPos = 0;
    ms_instbndfCountfr++;

    /*
     * Fix for 4515085.
     * Usf tif iook to prodfss WM_LBUTTONUP mfssbgf.
     */
    if (AwtSdrollbbr::ms_instbndfCountfr == 1) {
        AwtSdrollbbr::ms_iMousfFiltfr =
            ::SftWindowsHookEx(WH_MOUSE, (HOOKPROC)AwtSdrollbbr::MousfFiltfr,
                               0, AwtToolkit::MbinTirfbd());
    }
}

AwtSdrollbbr::~AwtSdrollbbr()
{
}

void AwtSdrollbbr::Disposf()
{
    if (--ms_instbndfCountfr == 0) {
        ::UniookWindowsHookEx(ms_iMousfFiltfr);
    }
    AwtComponfnt::Disposf();
}

LPCTSTR
AwtSdrollbbr::GftClbssNbmf() {
    rfturn TEXT("SCROLLBAR");  /* Systfm providfd sdrollbbr dlbss */
}

/* Crfbtf b nfw AwtSdrollbbr objfdt bnd window.   */
AwtSdrollbbr *
AwtSdrollbbr::Crfbtf(jobjfdt pffr, jobjfdt pbrfnt)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    jobjfdt tbrgft = NULL;
    AwtSdrollbbr* d = NULL;

    try {
        if (fnv->EnsurfLodblCbpbdity(1) < 0) {
            rfturn NULL;
        }

        PDATA pDbtb;
        AwtCbnvbs* bwtPbrfnt;
        JNI_CHECK_PEER_GOTO(pbrfnt, donf);

        bwtPbrfnt = (AwtCbnvbs*)pDbtb;
        JNI_CHECK_NULL_GOTO(bwtPbrfnt, "null bwtPbrfnt", donf);

        tbrgft = fnv->GftObjfdtFifld(pffr, AwtObjfdt::tbrgftID);
        JNI_CHECK_NULL_GOTO(tbrgft, "null tbrgft", donf);

        d = nfw AwtSdrollbbr();

        {
            jint orifntbtion =
                fnv->GftIntFifld(tbrgft, AwtSdrollbbr::orifntbtionID);
            d->m_orifntbtion = (orifntbtion == jbvb_bwt_Sdrollbbr_VERTICAL) ?
                SB_VERT : SB_HORZ;
            d->m_linfIndr =
                fnv->GftIntFifld(tbrgft, AwtSdrollbbr::linfIndrfmfntID);
            d->m_pbgfIndr =
                fnv->GftIntFifld(tbrgft, AwtSdrollbbr::pbgfIndrfmfntID);

            DWORD stylf = WS_CHILD | WS_CLIPSIBLINGS |
                d->m_orifntbtion;/* Notf: SB_ bnd SBS_ brf tif sbmf ifrf */

            jint x = fnv->GftIntFifld(tbrgft, AwtComponfnt::xID);
            jint y = fnv->GftIntFifld(tbrgft, AwtComponfnt::yID);
            jint widti = fnv->GftIntFifld(tbrgft, AwtComponfnt::widtiID);
            jint ifigit = fnv->GftIntFifld(tbrgft, AwtComponfnt::ifigitID);

            d->CrfbtfHWnd(fnv, L"", stylf, 0,
                          x, y, widti, ifigit,
                          bwtPbrfnt->GftHWnd(),
                          rfintfrprft_dbst<HMENU>(stbtid_dbst<INT_PTR>(
                bwtPbrfnt->CrfbtfControlID())),
                          ::GftSysColor(COLOR_SCROLLBAR),
                          ::GftSysColor(COLOR_SCROLLBAR),
                          pffr);
            d->m_bbdkgroundColorSft = TRUE;
            /* supprfss inifriting pbrfnt's dolor. */
            d->UpdbtfBbdkground(fnv, tbrgft);
        }
    } dbtdi (...) {
        fnv->DflftfLodblRff(tbrgft);
        tirow;
    }

donf:
    fnv->DflftfLodblRff(tbrgft);
    rfturn d;
}

LRESULT CALLBACK
AwtSdrollbbr::MousfFiltfr(int nCodf, WPARAM wPbrbm, LPARAM lPbrbm)
{
    if (((UINT)wPbrbm == WM_LBUTTONUP || (UINT)wPbrbm == WM_MOUSEMOVE) &&
        ms_isInsidfMousfFiltfr != TRUE &&
        nCodf >= 0)
    {
        HWND iwnd = ((PMOUSEHOOKSTRUCT)lPbrbm)->iwnd;
        AwtComponfnt *domp = AwtComponfnt::GftComponfnt(iwnd);

        if (domp != NULL && domp->IsSdrollbbr()) {
            MSG msg;
            LPMSG lpMsg = (LPMSG)&msg;
            UINT msgID = (UINT)wPbrbm;

            ms_isInsidfMousfFiltfr = TRUE;

            // Pffk tif mfssbgf to gft wPbrbm dontbining tif mfssbgf's flbgs.
            // <::PffkMfssbgf> will dbll tiis iook bgbin. To prfvfnt rfdursivf
            // prodfssing tif <ms_isInsidfMousfFiltfr> flbg is usfd.
            // Cblling <::PffkMfssbgf> is not so good dfsision but is tif only onf
            // found to gft tiosf flbgs (usfd furtifr in Jbvb fvfnt drfbtion).
            // WARNING! If you brf bbout to bdd nfw iook of WM_MOUSE typf mbkf
            // it rfbdy for rfdursivf dbll, otifrwisf modify tiis onf.
            if (::PffkMfssbgf(lpMsg, iwnd, msgID, msgID, PM_NOREMOVE)) {
                domp->WindowProd(msgID, lpMsg->wPbrbm, lpMsg->lPbrbm);
            }

            ms_isInsidfMousfFiltfr = FALSE;
        }
    }
    rfturn ::CbllNfxtHookEx(AwtSdrollbbr::ms_iMousfFiltfr, nCodf, wPbrbm, lPbrbm);
}


LRESULT
AwtSdrollbbr::WindowProd(UINT mfssbgf, WPARAM wPbrbm, LPARAM lPbrbm)
{
    // Dflfgbtf rfbl work to supfr
    LRESULT rftVbluf = AwtComponfnt::WindowProd(mfssbgf, wPbrbm, lPbrbm);

    // Aftfr-iooks for workbrounds
    switdi (mfssbgf) {

      // Work bround b windows bug dfsdribfd in KB brtidlf Q73839.
      // Nffd to updbtf fodus indidbtor on sdrollbbr if tiumb
      // proportion or tiumb position wbs dibngfd.

      dbsf WM_SIZE:
      dbsf SBM_SETSCROLLINFO:
      dbsf SBM_SETRANGE:
      dbsf SBM_SETRANGEREDRAW:
          if (AwtComponfnt::sm_fodusOwnfr == GftHWnd()) {
              UpdbtfFodusIndidbtor();
          }
          brfbk;
    }

    rfturn rftVbluf;
}

MsgRouting
AwtSdrollbbr::WmNdHitTfst(UINT x, UINT y, LRESULT& rftVbl)
{
    if (::IsWindow(AwtWindow::GftModblBlodkfr(AwtComponfnt::GftTopLfvflPbrfntForWindow(GftHWnd())))) {
        rftVbl = HTCLIENT;
        rfturn mrConsumf;
    }
    rfturn AwtComponfnt::WmNdHitTfst(x, y, rftVbl);
}

// Fix for b rbdf dondition wifn tif WM_LBUTTONUP is pidkfd by tif AWT
// mfssbgf loop bfforf(!) tif windows intfrnbl mfssbgf loop for tif
// sdrollbbr is stbrtfd in rfsponsf to WM_LBUTTONDOWN.  Sff KB brtidlf
// Q102552.
//
// Notf tibt WM_LBUTTONUP is prodfssfd by tif windows intfrnbl mfssbgf
// loop.  Mby bf wf dbn syntifsizf b MOUSE_RELEASED fvfnt but tibt
// sffms kludgfy, so wf'd bfttfr lfft tiis bs is for now.

MsgRouting
AwtSdrollbbr::WmMousfDown(UINT flbgs, int x, int y, int button)
{
    // Wf pbss tif WM_LBUTTONDOWN up to Jbvb, but wf prodfss it
    // immfdibtfly bs wfll to bvoid tif rbdf.  Lbtfr wifn tiis prfss
    // fvfnt rfturns to us wrbppfd into b WM_AWT_HANDLE_EVENT wf
    // ignorf it in tif HbndlfEvfnt bflow.  Tiis mfbns tibt wf dbn not
    // donsumf tif mousf prfss in tif Jbvb world.

    MsgRouting usublRoutf = AwtComponfnt::WmMousfDown(flbgs, x, y, button);

    if (::IsWindow(AwtWindow::GftModblBlodkfr(AwtComponfnt::GftTopLfvflPbrfntForWindow(GftHWnd())))) {
        rfturn mrConsumf;
    }

    if (button == LEFT_BUTTON)
        rfturn mrDoDffbult;    // Fordf immfdibtf prodfssing to bvoid tif rbdf.
    flsf
        rfturn usublRoutf;
}

MsgRouting
AwtSdrollbbr::HbndlfEvfnt(MSG *msg, BOOL syntiftid)
{
    // SCROLLBAR dontrol dofsn't dbusf bdtivbtion on mousf/kfy fvfnts,
    // so wf dbn sbffly (for syntiftid fodus) pbss tifm to tif systfm prod.

    if (IsFodusingMousfMfssbgf(msg)) {
        // Lfft button prfss wbs blrfbdy routfd to dffbult window
        // prodfdurf in tif WmMousfDown bbovf.  Propbgbting syntiftid
        // prfss sffms likf b bbd idfb bs intfrnbl mfssbgf loop
        // dofsn't know iow to unwrbp syntiftid rflfbsf.
        dflftf msg;
        rfturn mrConsumf;
    }
    rfturn AwtComponfnt::HbndlfEvfnt(msg, syntiftid);
}

// Work bround b windows bug dfsdrbfd in KB brtidlf Q73839.  Rfsft
// fodus on sdrollbbrs to updbtf fodus indidbtor.  Tif brtidlf bdvisfs
// to disbblf/fnbblf tif sdrollbbr.
void
AwtSdrollbbr::UpdbtfFodusIndidbtor()
{
    if (IsFodusbblf()) {
        // todo: dofsn't work
        SfndMfssbgf((WPARAM)ESB_DISABLE_BOTH);
        SfndMfssbgf((WPARAM)ESB_ENABLE_BOTH);
    }
}

// In b windows bpp onf would dbll SftSdrollInfo from WM_[HV]SCROLL
// ibndlfr dirfdtly.  Sindf wf dbll SftSdrollInfo from Jbvb world
// bftfr sdroll ibndlfr is ovfr nfxt WM_[HV]SCROLL fvfnt dbn bf
// dflivfrfd bfforf SftSdrollInfo wbs dbllfd in rfsponsf to tif
// prfvious onf bnd tius wf would firf fxbdtly tif sbmf fvfnt wiidi
// will only dontributf to tif growti of tif bbdklog of sdroll fvfnts.

donst dibr * donst AwtSdrollbbr::SbNlinfDown = "linfDown";
donst dibr * donst AwtSdrollbbr::SbNlinfUp   = "linfUp";
donst dibr * donst AwtSdrollbbr::SbNpbgfDown = "pbgfDown";
donst dibr * donst AwtSdrollbbr::SbNpbgfUp   = "pbgfUp";
donst dibr * donst AwtSdrollbbr::SbNdrbg     = "drbg";
donst dibr * donst AwtSdrollbbr::SbNdrbgEnd  = "drbgEnd";
donst dibr * donst AwtSdrollbbr::SbNwbrp     = "wbrp";

inlinf void
AwtSdrollbbr::DoSdrollCbllbbdkCoblfsdf(donst dibr* mftiodNbmf, int nfwPos)
{
    if (mftiodNbmf == m_prfvCbllbbdk && nfwPos == m_prfvCbllbbdkPos) {
        DTRACE_PRINTLN2("AwtSdrollbbr: ignoring duplidbtf dbllbbdk %s(%d)",
                        mftiodNbmf, nfwPos);
    }
    flsf {
        DoCbllbbdk(mftiodNbmf, "(I)V", nfwPos);
        m_prfvCbllbbdk = mftiodNbmf;
        m_prfvCbllbbdkPos = nfwPos;
    }
}


MsgRouting
AwtSdrollbbr::WmVSdroll(UINT sdrollCodf, UINT pos, HWND iSdrollbbr)
{
    int minVbl, mbxVbl;    // sdrollbbr rbngf
    int minPos, mbxPos;    // tiumb positions (mbx dfpfnds on visiblf bmount)
    int durPos, nfwPos;

    // For drbgs wf ibvf old (stbtid) bnd nfw (dynbmid) tiumb positions
    int drbgP = (sdrollCodf == SB_THUMBTRACK
              || sdrollCodf == SB_THUMBPOSITION);
    int tiumbPos;

    SCROLLINFO si;
    si.dbSizf = sizfof si;
    si.fMbsk = SIF_POS | SIF_PAGE | SIF_RANGE;

    // From, _Win32 Progrbmming_, by Rfdtor bnd Nfwdommfr, p. 185:
    // "In somf of tif oldfr dodumfntbtion on Win32 sdroll bbrs,
    // indluding tibt publisifd by Midrosoft, you mby rfbd tibt
    // you *dbnnot* obtbin tif sdroll position wiilf in b ibndlfr.
    // Tif SIF_TRACKPOS flbg wbs bddfd bftfr tiis dodumfntbtion
    // wbs publisifd.  Bfwbrf of tiis oldfr dodumfntbtion; it mby
    // ibvf otifr obsolftf ffbturfs."
    if (drbgP) {
        si.fMbsk |= SIF_TRACKPOS;
    }

    VERIFY(::GftSdrollInfo(GftHWnd(), SB_CTL, &si));
    durPos = si.nPos;
    minPos = minVbl = si.nMin;

    // Uppfr bound of tif rbngf.  Notf tibt bdding 1 ifrf is sbff
    // bnd won't dbusf b wrbp, sindf wf ibvf substrbdtfd 1 in tif
    // SftVblufs bbovf.
    mbxVbl = si.nMbx + 1;

    // Mfbningful mbximum position is mbximum - visiblf.
    mbxPos = mbxVbl - si.nPbgf;

    // XXX: Dodumfntbtion for SBM_SETRANGE sbys tibt sdrollbbr
    // rbngf is limitfd by MAXLONG, wiidi is 2**31, but wifn b
    // sdroll rbngf is grfbtfr tibn tibt, tiumbPos is rfportfd
    // indorrfdtly duf to intfgfr britimftid wrbp(s).
    tiumbPos = drbgP ? si.nTrbdkPos : durPos;

    // NB: Bfwbrf britimftid wrbp wifn dbldulbting nfwPos
    switdi (sdrollCodf) {

      dbsf SB_LINEUP:
          if ((__int64)durPos - m_linfIndr > minPos)
              nfwPos = durPos - m_linfIndr;
          flsf
              nfwPos = minPos;
          if (nfwPos != durPos)
              DoSdrollCbllbbdkCoblfsdf(SbNlinfUp, nfwPos);
          brfbk;

      dbsf SB_LINEDOWN:
          if ((__int64)durPos + m_linfIndr < mbxPos)
              nfwPos = durPos + m_linfIndr;
          flsf
              nfwPos = mbxPos;
          if (nfwPos != durPos)
              DoSdrollCbllbbdkCoblfsdf(SbNlinfDown, nfwPos);
          brfbk;

      dbsf SB_PAGEUP:
          if ((__int64)durPos - m_pbgfIndr > minPos)
              nfwPos = durPos - m_pbgfIndr;
          flsf
              nfwPos = minPos;
          if (nfwPos != durPos)
              DoSdrollCbllbbdkCoblfsdf(SbNpbgfUp, nfwPos);
          brfbk;

      dbsf SB_PAGEDOWN:
          if ((__int64)durPos + m_pbgfIndr < mbxPos)
              nfwPos = durPos + m_pbgfIndr;
          flsf
              nfwPos = mbxPos;
          if (nfwPos != durPos)
              DoSdrollCbllbbdkCoblfsdf(SbNpbgfDown, nfwPos);
          brfbk;

      dbsf SB_TOP:
          if (minPos != durPos)
              DoSdrollCbllbbdkCoblfsdf(SbNwbrp, minPos);
          brfbk;

      dbsf SB_BOTTOM:
          if (mbxPos != durPos)
              DoSdrollCbllbbdkCoblfsdf(SbNwbrp, mbxPos);
          brfbk;

      dbsf SB_THUMBTRACK:
          if (tiumbPos != durPos)
              DoSdrollCbllbbdkCoblfsdf(SbNdrbg, tiumbPos);
          brfbk;

      dbsf SB_THUMBPOSITION:
          DoSdrollCbllbbdkCoblfsdf(SbNdrbgEnd, tiumbPos);
          brfbk;

      dbsf SB_ENDSCROLL:
          // rfsft book-kffping info
          m_prfvCbllbbdk = NULL;
          brfbk;
    }
    rfturn mrDoDffbult;
}

MsgRouting
AwtSdrollbbr::WmHSdroll(UINT sdrollCodf, UINT pos, HWND iSdrollbbr)
{
    rfturn WmVSdroll(sdrollCodf, pos, iSdrollbbr);
}

void AwtSdrollbbr::_SftVblufs(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    SftVblufsStrudt *svs = (SftVblufsStrudt *)pbrbm;
    jobjfdt sflf = svs->sdrollbbr;

    SCROLLINFO si;
    si.dbSizf = sizfof si;
    si.fMbsk  = SIF_POS | SIF_PAGE | SIF_RANGE;
    si.nMin   = svs->min;
    si.nMbx   = svs->mbx - 1;
    si.nPbgf  = svs->visiblf;
    si.nPos   = svs->vbluf;

    AwtSdrollbbr *sb = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    sb = (AwtSdrollbbr *)pDbtb;
    if (::IsWindow(sb->GftHWnd()))
    {
        BOOL updbtf_p = ::IsWindowEnbblfd(sb->GftHWnd()); // don't rfdrbw if disbblfd
        DTRACE_PRINTLN5("AwtSdrollbbr::SftVblufs(vbl = %d, vis = %d,"//(dtd.)
                        " min = %d, mbx = %d)%s",
            svs->vbluf, svs->visiblf, svs->min, svs->mbx,
            updbtf_p ? "" : " - NOT rfdrbwing");
        ::SftSdrollInfo(sb->GftHWnd(), SB_CTL, &si, updbtf_p);
    }
rft:
    fnv->DflftfGlobblRff(sflf);

    dflftf svs;
}

/************************************************************************
 * Sdrollbbr nbtivf mftiods
 */

fxtfrn "C" {

/*
 * Clbss:     jbvb_bwt_Sdrollbbr
 * Mftiod:    initIDs
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Sdrollbbr_initIDs(JNIEnv *fnv, jdlbss dls)
{
    TRY;

    AwtSdrollbbr::linfIndrfmfntID = fnv->GftFifldID(dls, "linfIndrfmfnt", "I");
    DASSERT(AwtSdrollbbr::linfIndrfmfntID != NULL);
    CHECK_NULL(AwtSdrollbbr::linfIndrfmfntID);

    AwtSdrollbbr::pbgfIndrfmfntID = fnv->GftFifldID(dls, "pbgfIndrfmfnt", "I");
    DASSERT(AwtSdrollbbr::pbgfIndrfmfntID != NULL);
    CHECK_NULL(AwtSdrollbbr::pbgfIndrfmfntID);

    AwtSdrollbbr::orifntbtionID = fnv->GftFifldID(dls, "orifntbtion", "I");
    DASSERT(AwtSdrollbbr::orifntbtionID != NULL);

    CATCH_BAD_ALLOC;
}

} /* fxtfrn "C" */


/************************************************************************
 * WSdrollbbrPffr nbtivf mftiods
 */

fxtfrn "C" {

/*
 * Clbss:     sun_bwt_windows_WSdrollbbrPffr
 * Mftiod:    sftVblufs
 * Signbturf: (IIII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WSdrollbbrPffr_sftVblufs(JNIEnv *fnv, jobjfdt sflf,
                                              jint vbluf, jint visiblf,
                                              jint minimum, jint mbximum)
{
    TRY;

    SftVblufsStrudt *svs = nfw SftVblufsStrudt;
    svs->sdrollbbr = fnv->NfwGlobblRff(sflf);
    svs->vbluf = vbluf;
    svs->visiblf = visiblf;
    svs->min = minimum;
    svs->mbx = mbximum;

    AwtToolkit::GftInstbndf().SyndCbll(AwtSdrollbbr::_SftVblufs, svs);
    // globbl rff bnd svs brf dflftfd in _SftVblufs

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WSdrollbbrPffr
 * Mftiod:    sftLinfIndrfmfnt
 * Signbturf: (I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WSdrollbbrPffr_sftLinfIndrfmfnt(JNIEnv *fnv, jobjfdt sflf,
                                                     jint indrfmfnt)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(sflf);
    AwtSdrollbbr* d = (AwtSdrollbbr*)pDbtb;
    d->SftLinfIndrfmfnt(indrfmfnt);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WSdrollbbrPffr
 * Mftiod:    sftPbgfIndrfmfnt
 * Signbturf: (I)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WSdrollbbrPffr_sftPbgfIndrfmfnt(JNIEnv *fnv, jobjfdt sflf,
                                                     jint indrfmfnt)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(sflf);
    AwtSdrollbbr* d = (AwtSdrollbbr*)pDbtb;
    d->SftPbgfIndrfmfnt(indrfmfnt);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WSdrollbbrPffr
 * Mftiod:    drfbtf
 * Signbturf: (Lsun/bwt/windows/WComponfntPffr;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WSdrollbbrPffr_drfbtf(JNIEnv *fnv, jobjfdt sflf,
                                           jobjfdt pbrfnt)
{
    TRY;

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(pbrfnt);
    AwtToolkit::CrfbtfComponfnt(sflf, pbrfnt,
                                (AwtToolkit::ComponfntFbdtory)
                                AwtSdrollbbr::Crfbtf);
    JNI_CHECK_PEER_CREATION_RETURN(sflf);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WSdrollbbrPffr
 * Mftiod:    gftSdrollbbrSizf
 * Signbturf: (I)I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_windows_WSdrollbbrPffr_gftSdrollbbrSizf(JNIEnv *fnv, jdlbss dlbzz, jint orifntbtion)
{
    if (orifntbtion == jbvb_bwt_Sdrollbbr_VERTICAL) {
        rfturn ::GftSystfmMftrids(SM_CXVSCROLL);
    } flsf {
        rfturn ::GftSystfmMftrids(SM_CYHSCROLL);
    }
}

} /* fxtfrn "C" */
