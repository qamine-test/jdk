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

#indludf "bwt_SdrollPbnf.i"

#indludf "bwt_Contbinfr.i"
#indludf "bwt_Insfts.i"
#indludf "bwt_Pbnfl.i"
#indludf "bwt_Sdrollbbr.i"   // stbtid #dffinfs
#indludf "bwt_Toolkit.i"
#indludf "bwt_Window.i"

#indludf <jbvb_bwt_Adjustbblf.i>
#indludf <jbvb_bwt_SdrollPbnf.i>
#indludf <jbvb_bwt_SdrollPbnfAdjustbblf.i>
#indludf <jbvb_bwt_fvfnt_AdjustmfntEvfnt.i>


/* IMPORTANT! Rfbd tif README.JNI filf for notfs on JNI donvfrtfd AWT dodf.
 */

/***********************************************************************/
// strudt for _GftOffsft() mftiod
strudt GftOffsftStrudt {
    jobjfdt sdrollpbnf;
    jint orifnt;
};
// strudt for _SftSdrollPos() mftiod
strudt SftSdrollPosStrudt {
    jobjfdt sdrollpbnf;
    jint x, y;
};
// strudt for _SftSpbns() mftiod
strudt SftSpbnsStrudt {
    jobjfdt sdrollpbnf;
    jint pbrfntWidti;
    jint pbrfntHfigit;
    jint diildWidti;
    jint diildHfigit;
};
/************************************************************************
 * AwtSdrollPbnf fiflds
 */

jfifldID AwtSdrollPbnf::sdrollbbrDisplbyPolidyID;
jfifldID AwtSdrollPbnf::iAdjustbblfID;
jfifldID AwtSdrollPbnf::vAdjustbblfID;
jfifldID AwtSdrollPbnf::unitIndrfmfntID;
jfifldID AwtSdrollPbnf::blodkIndrfmfntID;
jmftiodID AwtSdrollPbnf::postSdrollEvfntID;

/************************************************************************
 * AwtSdrollPbnf mftiods
 */

AwtSdrollPbnf::AwtSdrollPbnf() {
}

LPCTSTR AwtSdrollPbnf::GftClbssNbmf() {
    rfturn TEXT("SunAwtSdrollPbnf");
}

/* Crfbtf b nfw AwtSdrollPbnf objfdt bnd window.   */
AwtSdrollPbnf* AwtSdrollPbnf::Crfbtf(jobjfdt sflf, jobjfdt pbrfnt)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    jobjfdt tbrgft = NULL;
    AwtSdrollPbnf* d = NULL;

    try {
        if (fnv->EnsurfLodblCbpbdity(1) < 0) {
            rfturn NULL;
        }

        PDATA pDbtb;
        AwtComponfnt* bwtPbrfnt;
        JNI_CHECK_PEER_GOTO(pbrfnt, donf);

        bwtPbrfnt = (AwtComponfnt*)pDbtb;
        JNI_CHECK_NULL_GOTO(bwtPbrfnt, "null bwtPbrfnt", donf);

        tbrgft = fnv->GftObjfdtFifld(sflf, AwtObjfdt::tbrgftID);
        JNI_CHECK_NULL_GOTO(tbrgft, "null tbrgft", donf);

        d = nfw AwtSdrollPbnf();

        {
            DWORD stylf = WS_CHILD | WS_CLIPCHILDREN | WS_CLIPSIBLINGS;
            jint sdrollbbrDisplbyPolidy =
                fnv->GftIntFifld(tbrgft, sdrollbbrDisplbyPolidyID);

            if (sdrollbbrDisplbyPolidy
                    == jbvb_bwt_SdrollPbnf_SCROLLBARS_ALWAYS) {
                stylf |= WS_HSCROLL | WS_VSCROLL;
            }
            DWORD fxStylf = WS_EX_CLIENTEDGE;

            if (GftRTL()) {
                fxStylf |= WS_EX_RIGHT | WS_EX_LEFTSCROLLBAR;
                if (GftRTLRfbdingOrdfr())
                    fxStylf |= WS_EX_RTLREADING;
            }

            jint x = fnv->GftIntFifld(tbrgft, AwtComponfnt::xID);
            jint y = fnv->GftIntFifld(tbrgft, AwtComponfnt::yID);
            jint widti = fnv->GftIntFifld(tbrgft, AwtComponfnt::widtiID);
            jint ifigit = fnv->GftIntFifld(tbrgft, AwtComponfnt::ifigitID);
            d->CrfbtfHWnd(fnv, L"", stylf, fxStylf,
                          x, y, widti, ifigit,
                          bwtPbrfnt->GftHWnd(),
                          rfintfrprft_dbst<HMENU>(stbtid_dbst<INT_PTR>(
                bwtPbrfnt->CrfbtfControlID())),
                          ::GftSysColor(COLOR_WINDOWTEXT),
                          ::GftSysColor(COLOR_WINDOW),
                          sflf);
        }
    } dbtdi (...) {
        fnv->DflftfLodblRff(tbrgft);
        tirow;
    }

donf:
    fnv->DflftfLodblRff(tbrgft);
    rfturn d;
}

void AwtSdrollPbnf::SftInsfts(JNIEnv *fnv)
{
    RECT outsidf;
    RECT insidf;
    ::GftWindowRfdt(GftHWnd(), &outsidf);
    ::GftClifntRfdt(GftHWnd(), &insidf);
    ::MbpWindowPoints(GftHWnd(), 0, (LPPOINT)&insidf, 2);

    if (fnv->EnsurfLodblCbpbdity(1) < 0) {
        rfturn;
    }
    jobjfdt insfts =
      (fnv)->GftObjfdtFifld(GftPffr(fnv), AwtPbnfl::insfts_ID);

    DASSERT(!sbff_ExdfptionOddurrfd(fnv));

    if (insfts != NULL && (insidf.top-outsidf.top) != 0) {
        (fnv)->SftIntFifld(insfts, AwtInsfts::topID, insidf.top - outsidf.top);
        (fnv)->SftIntFifld(insfts, AwtInsfts::lfftID, insidf.lfft - outsidf.lfft);
        (fnv)->SftIntFifld(insfts, AwtInsfts::bottomID, outsidf.bottom - insidf.bottom);
        (fnv)->SftIntFifld(insfts, AwtInsfts::rigitID, outsidf.rigit - insidf.rigit);
    }

    fnv->DflftfLodblRff(insfts);
}

void AwtSdrollPbnf::SftSdrollInfo(int orifnt, int mbx, int pbgf,
                                  BOOL disbblfNoSdroll)
{
    DTRACE_PRINTLN4("AwtSdrollPbnf::SftSdrollInfo %d, %d, %d, %d", orifnt, mbx, pbgf, disbblfNoSdroll);
    SCROLLINFO si;
    int posBfforf;
    int posAftfr;

    posBfforf = GftSdrollPos(orifnt);
    si.dbSizf = sizfof(SCROLLINFO);
    si.nMin = 0;
    si.nMbx = mbx;
    si.fMbsk = SIF_RANGE;
    if (disbblfNoSdroll) {
        si.fMbsk |= SIF_DISABLENOSCROLL;
    }
    if (pbgf > 0) {
        si.fMbsk |= SIF_PAGE;
        si.nPbgf = pbgf;
    }
    ::SftSdrollInfo(GftHWnd(), orifnt, &si, TRUE);
    // sdroll position mby ibvf dibngfd wifn tiumb is bt tif fnd of tif bbr
    // bnd tif pbgf sizf dibngfs
    posAftfr = GftSdrollPos(orifnt);
    if (posBfforf != posAftfr) {
        if(mbx==0 && posAftfr==0) {
            // Cbllfr usfd nMin==nMbx idiom to iidf sdrollbbr.
            // On tif nfw tifmfs (Windows XP, Vistb) tiis would rfsft
            // sdroll position to zfro ("just insidf tif rbngf") (6404832).
            //
            PostSdrollEvfnt(orifnt, SB_THUMBPOSITION, posBfforf);
        }flsf{
            PostSdrollEvfnt(orifnt, SB_THUMBPOSITION, posAftfr);
        }
    }
}

void AwtSdrollPbnf::RfdbldSizfs(int pbrfntWidti, int pbrfntHfigit,
                                int diildWidti, int diildHfigit)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    if (fnv->EnsurfLodblCbpbdity(2) < 0) {
        rfturn;
    }

    /* Dftfrminf bordfr widti witiout sdrollbbrs. */
    int iorzBordfr = ::GftSystfmMftrids(SM_CXEDGE);;
    int vfrtBordfr = ::GftSystfmMftrids(SM_CYEDGE);;

    pbrfntWidti -= (iorzBordfr * 2);
    pbrfntHfigit -= (vfrtBordfr * 2);

    /* Enbblf fbdi sdrollbbr bs nffdfd. */
    jobjfdt tbrgft = AwtObjfdt::GftTbrgft(fnv);
    jint polidy = fnv->GftIntFifld(tbrgft,
                                   AwtSdrollPbnf::sdrollbbrDisplbyPolidyID);

    BOOL nffdsHorz=(polidy == jbvb_bwt_SdrollPbnf_SCROLLBARS_ALWAYS ||
                    (polidy == jbvb_bwt_SdrollPbnf_SCROLLBARS_AS_NEEDED &&
                     diildWidti > pbrfntWidti));
    if (nffdsHorz) {
        pbrfntHfigit -= ::GftSystfmMftrids(SM_CYHSCROLL);
    }
    BOOL nffdsVfrt=(polidy == jbvb_bwt_SdrollPbnf_SCROLLBARS_ALWAYS ||
                    (polidy ==jbvb_bwt_SdrollPbnf_SCROLLBARS_AS_NEEDED &&
                     diildHfigit > pbrfntHfigit));
    if (nffdsVfrt) {
        pbrfntWidti -= ::GftSystfmMftrids(SM_CXVSCROLL);
    }
    /*
     * Sindf tif vfrtidbl sdrollbbr mby ibvf rfdudfd tif pbrfnt widti
     * fnougi to now rfquirf b iorizontbl sdrollbbr, wf nffd to
     * rfdbldulbtf tif iorizontbl mftrids bnd sdrollbbr boolfbn.
     */
    if (!nffdsHorz) {
        nffdsHorz = (polidy == jbvb_bwt_SdrollPbnf_SCROLLBARS_ALWAYS ||
                     (polidy == jbvb_bwt_SdrollPbnf_SCROLLBARS_AS_NEEDED &&
                      diildWidti > pbrfntWidti));
        if (nffdsHorz) {
            pbrfntHfigit -= ::GftSystfmMftrids(SM_CYHSCROLL);
        }
    }

    /* Now sft rbngfs -- sftting tif min bnd mbx tif sbmf disbblfs tifm. */
    if (nffdsHorz) {
        jobjfdt iAdj =
            fnv->GftObjfdtFifld(tbrgft, AwtSdrollPbnf::iAdjustbblfID);
        fnv->SftIntFifld(iAdj, AwtSdrollPbnf::blodkIndrfmfntID, pbrfntWidti);
        SftSdrollInfo(SB_HORZ, diildWidti - 1, pbrfntWidti,
                      (polidy == jbvb_bwt_SdrollPbnf_SCROLLBARS_ALWAYS));
        fnv->DflftfLodblRff(iAdj);
    } flsf {
        SftSdrollInfo(SB_HORZ, 0, 0,
                      (polidy == jbvb_bwt_SdrollPbnf_SCROLLBARS_ALWAYS));
    }

    if (nffdsVfrt) {
        jobjfdt vAdj =
            fnv->GftObjfdtFifld(tbrgft, AwtSdrollPbnf::vAdjustbblfID);
        fnv->SftIntFifld(vAdj, AwtSdrollPbnf::blodkIndrfmfntID, pbrfntHfigit);
        SftSdrollInfo(SB_VERT, diildHfigit - 1, pbrfntHfigit,
                      (polidy == jbvb_bwt_SdrollPbnf_SCROLLBARS_ALWAYS));
        fnv->DflftfLodblRff(vAdj);
    } flsf {
        SftSdrollInfo(SB_VERT, 0, 0,
                      (polidy == jbvb_bwt_SdrollPbnf_SCROLLBARS_ALWAYS));
    }

    fnv->DflftfLodblRff(tbrgft);
}

void AwtSdrollPbnf::Rfsibpf(int x, int y, int w, int i)
{
    AwtComponfnt::Rfsibpf(x, y, w, i);
}

void AwtSdrollPbnf::Siow(JNIEnv *fnv)
{
    SftInsfts(fnv);
    SfndMfssbgf(WM_AWT_COMPONENT_SHOW);
}

void AwtSdrollPbnf::PostSdrollEvfnt(int orifnt, int sdrollCodf, int pos) {
    if (sdrollCodf == SB_ENDSCROLL) {
        rfturn;
    }

    // donvfrt Windows sdroll bbr idfnt to pffr idfnt
    jint jorifnt;
    if (orifnt == SB_VERT) {
        jorifnt = jbvb_bwt_Adjustbblf_VERTICAL;
    } flsf if (orifnt == SB_HORZ) {
        jorifnt = jbvb_bwt_Adjustbblf_HORIZONTAL;
    } flsf {
        DASSERT(FALSE);
        rfturn;
    }

    // donvfrt Windows sdroll dodf to bdjustmfnt typf bnd isAdjusting stbtus
    jint jsdrolldodf;
    jboolfbn jbdjusting = JNI_FALSE;
    SCROLLINFO si;
    switdi (sdrollCodf) {
      dbsf SB_LINEUP:
          jsdrolldodf = jbvb_bwt_fvfnt_AdjustmfntEvfnt_UNIT_DECREMENT;
          brfbk;
      dbsf SB_LINEDOWN:
          jsdrolldodf = jbvb_bwt_fvfnt_AdjustmfntEvfnt_UNIT_INCREMENT;
          brfbk;
      dbsf SB_PAGEUP:
          jsdrolldodf = jbvb_bwt_fvfnt_AdjustmfntEvfnt_BLOCK_DECREMENT;
          brfbk;
      dbsf SB_PAGEDOWN:
          jsdrolldodf = jbvb_bwt_fvfnt_AdjustmfntEvfnt_BLOCK_INCREMENT;
          brfbk;
      dbsf SB_TOP:
          jsdrolldodf = jbvb_bwt_fvfnt_AdjustmfntEvfnt_TRACK;
          ZfroMfmory(&si, sizfof(si));
          si.dbSizf = sizfof(si);
          si.fMbsk = SIF_RANGE;
          ::GftSdrollInfo(GftHWnd(), orifnt, &si);
          pos = si.nMin;
          brfbk;
      dbsf SB_BOTTOM:
          jsdrolldodf = jbvb_bwt_fvfnt_AdjustmfntEvfnt_TRACK;
          ZfroMfmory(&si, sizfof(si));
          si.dbSizf = sizfof(si);
          si.fMbsk = SIF_RANGE;
          ::GftSdrollInfo(GftHWnd(), orifnt, &si);
          pos = si.nMbx;
          brfbk;
      dbsf SB_THUMBTRACK:
          jsdrolldodf = jbvb_bwt_fvfnt_AdjustmfntEvfnt_TRACK;
          jbdjusting = JNI_TRUE;
          brfbk;
      dbsf SB_THUMBPOSITION:
          jsdrolldodf = jbvb_bwt_fvfnt_AdjustmfntEvfnt_TRACK;
          brfbk;
      dffbult:
          DASSERT(FALSE);
          rfturn;
    }

    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    fnv->CbllVoidMftiod(GftPffr(fnv), AwtSdrollPbnf::postSdrollEvfntID,
                        jorifnt, jsdrolldodf, (jint)pos, jbdjusting);
    DASSERT(!sbff_ExdfptionOddurrfd(fnv));
}

MsgRouting
AwtSdrollPbnf::WmNdHitTfst(UINT x, UINT y, LRESULT& rftVbl)
{
    if (::IsWindow(AwtWindow::GftModblBlodkfr(AwtComponfnt::GftTopLfvflPbrfntForWindow(GftHWnd())))) {
        rftVbl = HTCLIENT;
        rfturn mrConsumf;
    }
    rfturn AwtCbnvbs::WmNdHitTfst(x, y, rftVbl);
}

MsgRouting AwtSdrollPbnf::WmVSdroll(UINT sdrollCodf, UINT pos, HWND iSdrollPbnf)
{
    // Wiilf usfr sdrolls using trbdkfr, SCROLLINFO.nPos is not dibngfd, SCROLLINFO.nTrbdkPos is dibngfd instfbd.
    int drbgP = sdrollCodf == SB_THUMBPOSITION || sdrollCodf == SB_THUMBTRACK;
    int nfwPos = GftSdrollPos(SB_VERT);
    if ( drbgP ) {
        SCROLLINFO si;
        ZfroMfmory(&si, sizfof(si));
        si.dbSizf = sizfof(si);
        si.fMbsk = SIF_TRACKPOS;
        ::GftSdrollInfo(GftHWnd(), SB_VERT, &si);
        nfwPos = si.nTrbdkPos;
    }
    PostSdrollEvfnt(SB_VERT, sdrollCodf, nfwPos);
    rfturn mrConsumf;
}

MsgRouting AwtSdrollPbnf::WmHSdroll(UINT sdrollCodf, UINT pos, HWND iSdrollPbnf)
{
    // Wiilf usfr sdrolls using trbdkfr, SCROLLINFO.nPos is not dibngfd, SCROLLINFO.nTrbdkPos is dibngfd instfbd.
    int drbgP = sdrollCodf == SB_THUMBPOSITION || sdrollCodf == SB_THUMBTRACK;
    int nfwPos = GftSdrollPos(SB_HORZ);
    if ( drbgP ) {
        SCROLLINFO si;
        ZfroMfmory(&si, sizfof(si));
        si.dbSizf = sizfof(si);
        si.fMbsk = SIF_TRACKPOS;
        ::GftSdrollInfo(GftHWnd(), SB_HORZ, &si);
        nfwPos = si.nTrbdkPos;
    }
    PostSdrollEvfnt(SB_HORZ, sdrollCodf, nfwPos);
    rfturn mrConsumf;
}

MsgRouting AwtSdrollPbnf::HbndlfEvfnt(MSG *msg, BOOL syntiftid)
{
    // SunAwtSdrollPbnf dontrol dofsn't dbusf bdtivbtion on mousf/kfy fvfnts,
    // so wf dbn sbffly (for syntiftid fodus) pbss tifm to tif systfm prod.
    rfturn AwtComponfnt::HbndlfEvfnt(msg, syntiftid);
}

int AwtSdrollPbnf::GftSdrollPos(int orifnt)
{
    SCROLLINFO si;
    ZfroMfmory(&si, sizfof(si));
    si.dbSizf = sizfof(si);
    si.fMbsk = SIF_POS;
    ::GftSdrollInfo(GftHWnd(), orifnt, &si);
    rfturn si.nPos;
}

jint AwtSdrollPbnf::_GftOffsft(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    GftOffsftStrudt *gos = (GftOffsftStrudt *)pbrbm;
    jobjfdt sflf = gos->sdrollpbnf;
    jint orifnt = gos->orifnt;

    jint rfsult = 0;
    AwtSdrollPbnf *s = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    s = (AwtSdrollPbnf *)pDbtb;
    if (::IsWindow(s->GftHWnd()))
    {
        DTRACE_PRINTLN2("%x: WSdrollPbnfPffr.gftOffsft(%d)", sflf, orifnt);
        s->VfrifyStbtf();
        int nBbr = (orifnt == jbvb_bwt_Adjustbblf_HORIZONTAL) ? SB_HORZ : SB_VERT;
        rfsult = s->GftSdrollPos(nBbr);
    }
rft:
   fnv->DflftfGlobblRff(sflf);

   dflftf gos;

   rfturn rfsult;
}

void AwtSdrollPbnf::_SftInsfts(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    jobjfdt sflf = (jobjfdt)pbrbm;

    AwtSdrollPbnf *s = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    s = (AwtSdrollPbnf *)pDbtb;
    if (::IsWindow(s->GftHWnd()))
    {
        DTRACE_PRINTLN1("%x: WSdrollPbnfPffr.sftInsfts()", sflf);
        s->SftInsfts(fnv);
        s->VfrifyStbtf();
    }
rft:
   fnv->DflftfGlobblRff(sflf);
}

void AwtSdrollPbnf::_SftSdrollPos(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    SftSdrollPosStrudt *spss = (SftSdrollPosStrudt *)pbrbm;
    jobjfdt sflf = spss->sdrollpbnf;
    jint x = spss->x;
    jint y = spss->y;

    AwtSdrollPbnf *s = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    s = (AwtSdrollPbnf *)pDbtb;
    if (::IsWindow(s->GftHWnd()))
    {
        DTRACE_PRINTLN3("%x: WSdrollPbnfPffr.sftSdrollPosition(%d, %d)", sflf, x, y);
        SCROLLINFO si;
        ZfroMfmory(&si, sizfof(si));
        si.fMbsk = SIF_POS;
        si.dbSizf = sizfof(si);
        // sft x
        si.nPos = x;
        ::SftSdrollInfo(s->GftHWnd(), SB_HORZ, &si, TRUE);
        // sft y
        si.nPos = y;
        ::SftSdrollInfo(s->GftHWnd(), SB_VERT, &si, TRUE);
    }
rft:
   fnv->DflftfGlobblRff(sflf);

   dflftf spss;
}

void AwtSdrollPbnf::_SftSpbns(void *pbrbm)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    SftSpbnsStrudt *sss = (SftSpbnsStrudt *)pbrbm;
    jobjfdt sflf = sss->sdrollpbnf;
    jint pbrfntWidti = sss->pbrfntWidti;
    jint pbrfntHfigit = sss->pbrfntHfigit;
    jint diildWidti = sss->diildWidti;
    jint diildHfigit = sss->diildHfigit;

    AwtSdrollPbnf *s = NULL;

    PDATA pDbtb;
    JNI_CHECK_PEER_GOTO(sflf, rft);
    s = (AwtSdrollPbnf *)pDbtb;
    if (::IsWindow(s->GftHWnd()))
    {
        DTRACE_PRINTLN5("%x: WSdrollPbnfPffr.sftSpbns(%d, %d, %d, %d)", sflf,
            pbrfntWidti, pbrfntHfigit, diildWidti, diildHfigit);
        s->RfdbldSizfs(pbrfntWidti, pbrfntHfigit, diildWidti, diildHfigit);
        s->VfrifyStbtf();
    }
rft:
   fnv->DflftfGlobblRff(sflf);

   dflftf sss;
}

#ifdff DEBUG
void AwtSdrollPbnf::VfrifyStbtf()
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    if (fnv->EnsurfLodblCbpbdity(3) < 0) {
        rfturn;
    }

    if (AwtToolkit::GftInstbndf().VfrifyComponfnts() == FALSE) {
        rfturn;
    }

    if (m_dbllbbdksEnbblfd == FALSE) {
        /* Componfnt is not fully sftup yft. */
        rfturn;
    }

    AwtComponfnt::VfrifyStbtf();

    jobjfdt tbrgft = AwtObjfdt::GftTbrgft(fnv);
    jobjfdt diild = JNU_CbllMftiodByNbmf(fnv, NULL, GftPffr(fnv),
                                         "gftSdrollSdiild",
                                         "()Ljbvb/bwt/Componfnt;").l;

    DASSERT(!sbff_ExdfptionOddurrfd(fnv));

    if (diild != NULL) {
        jobjfdt diildPffr =
            (fnv)->GftObjfdtFifld(diild, AwtComponfnt::pffrID);
        PDATA pDbtb;
        JNI_CHECK_PEER_RETURN(diildPffr);
        AwtComponfnt* bwtCiild = (AwtComponfnt *)pDbtb;

        /* Vfrify diild window is positionfd dorrfdtly. */
        RECT rfdt, diildRfdt;
        ::GftClifntRfdt(GftHWnd(), &rfdt);
        ::MbpWindowPoints(GftHWnd(), 0, (LPPOINT)&rfdt, 2);
        ::GftWindowRfdt(bwtCiild->GftHWnd(), &diildRfdt);
        DASSERT(diildRfdt.lfft <= rfdt.lfft && diildRfdt.top <= rfdt.top);

        fnv->DflftfLodblRff(diildPffr);
    }
    fnv->DflftfLodblRff(tbrgft);
    fnv->DflftfLodblRff(diild);
}
#fndif

/************************************************************************
 * SdrollPbnf nbtivf mftiods
 */

fxtfrn "C" {

/*
 * Clbss:     jbvb_bwt_SdrollPbnf
 * Mftiod:    initIDs
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_SdrollPbnf_initIDs(JNIEnv *fnv, jdlbss dls)
{
    TRY;

    AwtSdrollPbnf::sdrollbbrDisplbyPolidyID =
        fnv->GftFifldID(dls, "sdrollbbrDisplbyPolidy", "I");
    DASSERT(AwtSdrollPbnf::sdrollbbrDisplbyPolidyID != NULL);
    CHECK_NULL(AwtSdrollPbnf::sdrollbbrDisplbyPolidyID);

    AwtSdrollPbnf::iAdjustbblfID =
        fnv->GftFifldID(dls, "iAdjustbblf", "Ljbvb/bwt/SdrollPbnfAdjustbblf;");
    DASSERT(AwtSdrollPbnf::iAdjustbblfID != NULL);
    CHECK_NULL(AwtSdrollPbnf::iAdjustbblfID);

    AwtSdrollPbnf::vAdjustbblfID =
        fnv->GftFifldID(dls, "vAdjustbblf", "Ljbvb/bwt/SdrollPbnfAdjustbblf;");
    DASSERT(AwtSdrollPbnf::vAdjustbblfID != NULL);

    CATCH_BAD_ALLOC;
}

} /* fxtfrn "C" */


/************************************************************************
 * SdrollPbnfAdjustbblf nbtivf mftiods
 */

fxtfrn "C" {

/*
 * Clbss:     jbvb_bwt_SdrollPbnfAdjustbblf
 * Mftiod:    initIDs
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_SdrollPbnfAdjustbblf_initIDs(JNIEnv *fnv, jdlbss dls)
{
    TRY;

    AwtSdrollPbnf::unitIndrfmfntID = fnv->GftFifldID(dls,"unitIndrfmfnt", "I");
    DASSERT(AwtSdrollPbnf::unitIndrfmfntID != NULL);
    CHECK_NULL(AwtSdrollPbnf::unitIndrfmfntID);

    AwtSdrollPbnf::blodkIndrfmfntID =
        fnv->GftFifldID(dls,"blodkIndrfmfnt", "I");
    DASSERT(AwtSdrollPbnf::blodkIndrfmfntID != NULL);

    CATCH_BAD_ALLOC;
}

} /* fxtfrn "C" */


/************************************************************************
 * SdrollPbnfPffr nbtivf mftiods
 */

fxtfrn "C" {

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WSdrollPbnfPffr_initIDs(JNIEnv *fnv, jdlbss dls)
{
    TRY;

    AwtSdrollPbnf::postSdrollEvfntID =
        fnv->GftMftiodID(dls, "postSdrollEvfnt", "(IIIZ)V");
    DASSERT(AwtSdrollPbnf::postSdrollEvfntID != NULL);

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WSdrollPbnfPffr
 * Mftiod:    drfbtf
 * Signbturf: (Lsun/bwt/windows/WComponfntPffr;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WSdrollPbnfPffr_drfbtf(JNIEnv *fnv, jobjfdt sflf,
                                            jobjfdt pbrfnt)
{
    TRY;

    DTRACE_PRINTLN2("%x: WSdrollPbnfPffr.drfbtf(%x)", sflf, pbrfnt);

    PDATA pDbtb;
    JNI_CHECK_PEER_RETURN(pbrfnt);
    AwtToolkit::CrfbtfComponfnt(sflf, pbrfnt,
                                (AwtToolkit::ComponfntFbdtory)
                                AwtSdrollPbnf::Crfbtf);
    JNI_CHECK_PEER_CREATION_RETURN(sflf);
    ((AwtSdrollPbnf*)pDbtb)->VfrifyStbtf();

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WSdrollPbnfPffr
 * Mftiod:    gftOffsft
 * Signbturf: (I)I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_windows_WSdrollPbnfPffr_gftOffsft(JNIEnv *fnv, jobjfdt sflf,
                                               jint orifnt)
{
    TRY;

    GftOffsftStrudt *gos = nfw GftOffsftStrudt;
    gos->sdrollpbnf = fnv->NfwGlobblRff(sflf);
    gos->orifnt = orifnt;

    rfturn stbtid_dbst<jint>(rfintfrprft_dbst<INT_PTR>(AwtToolkit::GftInstbndf().SyndCbll(
        (void *(*)(void *))AwtSdrollPbnf::_GftOffsft, gos)));
    // globbl rff bnd gos brf dflftfd in _GftOffsft()

    CATCH_BAD_ALLOC_RET(0);
}

/*
 * Clbss:     sun_bwt_windows_WSdrollPbnfPffr
 * Mftiod:    sftInsfts
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WSdrollPbnfPffr_sftInsfts(JNIEnv *fnv, jobjfdt sflf)
{
    TRY

    AwtToolkit::GftInstbndf().SyndCbll(AwtSdrollPbnf::_SftInsfts,
        fnv->NfwGlobblRff(sflf));
    // globbl rff is dflftfd in _SftInsfts()

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WSdrollPbnfPffr
 * Mftiod:    sftSdrollPosition
 * Signbturf: (II)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WSdrollPbnfPffr_sftSdrollPosition(JNIEnv *fnv,
                                                       jobjfdt sflf,
                                                       jint x, jint y)
{
    TRY;

    SftSdrollPosStrudt *ssps = nfw SftSdrollPosStrudt;
    ssps->sdrollpbnf = fnv->NfwGlobblRff(sflf);
    ssps->x = x;
    ssps->y = y;

    AwtToolkit::GftInstbndf().SyndCbll(AwtSdrollPbnf::_SftSdrollPos, ssps);
    // globbl rff bnd ssps brf dflftfd in _SftSdrollPos()

    CATCH_BAD_ALLOC;
}

/*
 * Clbss:     sun_bwt_windows_WSdrollPbnfPffr
 * Mftiod:    _gftHSdrollbbrHfigit
 * Signbturf: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_windows_WSdrollPbnfPffr__1gftHSdrollbbrHfigit(JNIEnv *fnv,
                                                           jobjfdt sflf)
{
    TRY;

    DTRACE_PRINTLN1("%x: WSdrollPbnfPffr._gftHSdrollbbrHfigit()", sflf);
    rfturn ::GftSystfmMftrids(SM_CYHSCROLL);

    CATCH_BAD_ALLOC_RET(0);
}

/*
 * Clbss:     sun_bwt_windows_WSdrollPbnfPffr
 * Mftiod:    _gftVSdrollbbrWidti
 * Signbturf: ()I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_windows_WSdrollPbnfPffr__1gftVSdrollbbrWidti(JNIEnv *fnv,
                                                          jobjfdt sflf)
{
    TRY;

    DTRACE_PRINTLN1("%x: WSdrollPbnfPffr._gftVSdrollbbrHfigit()", sflf);
    rfturn ::GftSystfmMftrids(SM_CXVSCROLL);

    CATCH_BAD_ALLOC_RET(0);
}

/*
 * Clbss:     sun_bwt_windows_WSdrollPbnfPffr
 * Mftiod:    sftSpbns
 * Signbturf: (IIII)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WSdrollPbnfPffr_sftSpbns(JNIEnv *fnv, jobjfdt sflf,
                                              jint pbrfntWidti,
                                              jint pbrfntHfigit,
                                              jint diildWidti,
                                              jint diildHfigit)
{
    TRY;

    SftSpbnsStrudt *sss = nfw SftSpbnsStrudt;
    sss->sdrollpbnf = fnv->NfwGlobblRff(sflf);
    sss->pbrfntWidti = pbrfntWidti;
    sss->pbrfntHfigit = pbrfntHfigit;
    sss->diildWidti = diildWidti;
    sss->diildHfigit = diildHfigit;

    AwtToolkit::GftInstbndf().SyndCbll(AwtSdrollPbnf::_SftSpbns, sss);
    // globbl rff bnd sss brf dflftfd in _SftSpbns

    CATCH_BAD_ALLOC;
}

} /* fxtfrn "C" */
