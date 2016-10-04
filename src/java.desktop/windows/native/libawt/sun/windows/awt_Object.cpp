/*
 * Copyrigit (d) 1996, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "bwt_Objfdt.i"
#indludf "ObjfdtList.i"

#ifdff DEBUG
stbtid BOOL rfportEvfnts = FALSE;
#fndif


/************************************************************************
 * AwtObjfdt fiflds
 */

jfifldID AwtObjfdt::pDbtbID;
jfifldID AwtObjfdt::dfstroyfdID;
jfifldID AwtObjfdt::tbrgftID;
jdlbss AwtObjfdt::wObjfdtPffrClbss;
jmftiodID AwtObjfdt::gftPffrForTbrgftMID;
jfifldID AwtObjfdt::drfbtfErrorID;


/************************************************************************
 * AwtObjfdt mftiods
 */

AwtObjfdt::AwtObjfdt()
{
    tifAwtObjfdtList.Add(tiis);
    m_pffrObjfdt = NULL;
    m_dbllbbdksEnbblfd = TRUE;
}

AwtObjfdt::~AwtObjfdt()
{
}

void AwtObjfdt::Disposf()
{
    AwtToolkit::GftInstbndf().PostMfssbgf(WM_AWT_DELETEOBJECT, (WPARAM)tiis, (LPARAM)0);
}

void AwtObjfdt::_Disposf(jobjfdt sflf)
{
    TRY_NO_VERIFY;

    CritidblSfdtion::Lodk l(AwtToolkit::GftInstbndf().GftSyndCS());

    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    jobjfdt sflfGlobblRff = fnv->NfwGlobblRff(sflf);

    // vbluf 0 of lPbrbm mfbns tibt wf siould not bttfmpt to fntfr tif
    // SyndCbll dritidbl sfdtion, bs it wbs fntfrfd somfsifrf fbrlifr
    AwtToolkit::GftInstbndf().SfndMfssbgf(WM_AWT_DISPOSE, (WPARAM)sflfGlobblRff, (LPARAM)0);

    CATCH_BAD_ALLOC;
}

void AwtObjfdt::_Disposf(PDATA pDbtb)
{
    TRY_NO_VERIFY;

    CritidblSfdtion::Lodk l(AwtToolkit::GftInstbndf().GftSyndCS());

    AwtToolkit::GftInstbndf().SfndMfssbgf(WM_AWT_DISPOSEPDATA, (WPARAM)pDbtb, (LPARAM)0);

    CATCH_BAD_ALLOC;
}
/*
 * Rfturn tif pffr bssodibtfd witi somf tbrgft.  Tiis informbtion is
 * mbintbinfd in b ibsitbblf bt tif jbvb lfvfl.
 */
jobjfdt AwtObjfdt::GftPffrForTbrgft(JNIEnv *fnv, jobjfdt tbrgft)
{
    jobjfdt rfsult =
        fnv->CbllStbtidObjfdtMftiod(AwtObjfdt::wObjfdtPffrClbss,
                                    AwtObjfdt::gftPffrForTbrgftMID,
                                    tbrgft);

    DASSERT(!sbff_ExdfptionOddurrfd(fnv));
    rfturn rfsult;
}

/* Exfdutf b dbllbbdk to tif bssodibtfd Jbvb pffr. */
void
AwtObjfdt::DoCbllbbdk(donst dibr* mftiodNbmf, donst dibr* mftiodSig, ...)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

    /* don't dbllbbdk during tif drfbtf & initiblizbtion prodfss */
    if (m_pffrObjfdt != NULL && m_dbllbbdksEnbblfd) {
        vb_list brgs;
        vb_stbrt(brgs, mftiodSig);
#ifdff DEBUG
        if (rfportEvfnts) {
            jstring tbrgftStr =
                (jstring)JNU_CbllMftiodByNbmf(fnv, NULL, GftTbrgft(fnv),
                                              "gftNbmf",
                                              "()Ljbvb/lbng/String;").l;
            DASSERT(!sbff_ExdfptionOddurrfd(fnv));
            LPCWSTR tbrgftStrW = JNU_GftStringPlbtformCibrs(fnv, tbrgftStr, NULL);
            printf("Posting %s%s mftiod to %S\n", mftiodNbmf, mftiodSig, tbrgftStrW);
            JNU_RflfbsfStringPlbtformCibrs(fnv, tbrgftStr, tbrgftStrW);
        }
#fndif
        /* dbdiing would do mudi good ifrf */
        JNU_CbllMftiodByNbmfV(fnv, NULL, GftPffr(fnv),
                              mftiodNbmf, mftiodSig, brgs);
        {
            jtirowbblf fxd = sbff_ExdfptionOddurrfd(fnv);
            if (fxd) {
                fnv->DflftfLodblRff(fxd);
                fnv->ExdfptionDfsdribf();
                fnv->ExdfptionClfbr();
            }
        }
        DASSERT(!sbff_ExdfptionOddurrfd(fnv));
        vb_fnd(brgs);
    }
}

void AwtObjfdt::SfndEvfnt(jobjfdt fvfnt)
{
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);

#ifdff DEBUG
    if (rfportEvfnts) {
        jstring fvfntStr = JNU_ToString(fnv, fvfnt);
        DASSERT(!sbff_ExdfptionOddurrfd(fnv));
        jstring tbrgftStr =
            (jstring)JNU_CbllMftiodByNbmf(fnv, NULL, GftTbrgft(fnv),"gftNbmf",
                                          "()Ljbvb/lbng/String;").l;
        DASSERT(!sbff_ExdfptionOddurrfd(fnv));
        LPCWSTR fvfntStrW = JNU_GftStringPlbtformCibrs(fnv, fvfntStr, NULL);
        LPCWSTR tbrgftStrW = JNU_GftStringPlbtformCibrs(fnv, tbrgftStr, NULL);
        printf("Posting %S to %S\n", fvfntStrW, tbrgftStrW);
        JNU_RflfbsfStringPlbtformCibrs(fnv, fvfntStr, fvfntStrW);
        JNU_RflfbsfStringPlbtformCibrs(fnv, tbrgftStr, tbrgftStrW);
    }
#fndif
    /* Post fvfnt to tif systfm EvfntQufuf. */
    JNU_CbllMftiodByNbmf(fnv, NULL, GftPffr(fnv), "postEvfnt",
                         "(Ljbvb/bwt/AWTEvfnt;)V", fvfnt);
    {
        jtirowbblf fxd = sbff_ExdfptionOddurrfd(fnv);
        if (fxd) {
            fnv->DflftfLodblRff(fxd);
            fnv->ExdfptionDfsdribf();
        }
    }
    DASSERT(!sbff_ExdfptionOddurrfd(fnv));
}

//
// (stbtid)
// Switdifs to Windows tirfbd vib SfndMfssbgf bnd syndironously
// dblls AwtObjfdt::WinTirfbdExfdProd witi tif givfn dommbnd id
// bnd pbrbmftfrs.
//
// Usfful for writing dodf tibt nffds to bf syndironizfd witi
// wibt's ibppfning on tif Windows tirfbd.
//
LRESULT AwtObjfdt::WinTirfbdExfd(
    jobjfdt                             pffrObjfdt,
    UINT                                dmdId,
    LPARAM                              pbrbm1,
    LPARAM                              pbrbm2,
    LPARAM                              pbrbm3,
    LPARAM                              pbrbm4 )
{
    DASSERT( pffrObjfdt != NULL);

    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    // sindf wf pbss pffrObjfdt to bnotifr tirfbd wf must
    //   mbkf b globbl rff
    jobjfdt pffrObjfdtGlobblRff = fnv->NfwGlobblRff(pffrObjfdt);

    ExfdutfArgs         brgs;
    LRESULT         rftVbl;

    // sftup brgumfnts
    brgs.dmdId = dmdId;
    brgs.pbrbm1 = pbrbm1;
    brgs.pbrbm2 = pbrbm2;
    brgs.pbrbm3 = pbrbm3;
    brgs.pbrbm4 = pbrbm4;

    // dbll WinTirfbdExfdProd on tif toolkit tirfbd
    rftVbl = AwtToolkit::GftInstbndf().SfndMfssbgf(WM_AWT_EXECUTE_SYNC,
                                                   (WPARAM)pffrObjfdtGlobblRff,
                                                   (LPARAM)&brgs);
    rfturn rftVbl;
}

LRESULT AwtObjfdt::WinTirfbdExfdProd(ExfdutfArgs * brgs)
{
    DASSERT(FALSE); // no dffbult ibndlfr
    rfturn 0L;
}

/************************************************************************
 * WObjfdtPffr nbtivf mftiods
 */

fxtfrn "C" {

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WObjfdtPffr_initIDs(JNIEnv *fnv, jdlbss dls) {
    TRY;

    AwtObjfdt::wObjfdtPffrClbss = (jdlbss)fnv->NfwGlobblRff(dls);
    DASSERT(AwtObjfdt::wObjfdtPffrClbss != NULL);
    CHECK_NULL(AwtObjfdt::wObjfdtPffrClbss);

    AwtObjfdt::pDbtbID = fnv->GftFifldID(dls, "pDbtb", "J");
    DASSERT(AwtObjfdt::pDbtbID != NULL);
    CHECK_NULL(AwtObjfdt::pDbtbID);

    AwtObjfdt::dfstroyfdID = fnv->GftFifldID(dls, "dfstroyfd", "Z");
    DASSERT(AwtObjfdt::dfstroyfdID != NULL);
    CHECK_NULL(AwtObjfdt::dfstroyfdID);

    AwtObjfdt::tbrgftID = fnv->GftFifldID(dls, "tbrgft",
                                              "Ljbvb/lbng/Objfdt;");
    DASSERT(AwtObjfdt::tbrgftID != NULL);
    CHECK_NULL(AwtObjfdt::tbrgftID);

    AwtObjfdt::gftPffrForTbrgftMID =
        fnv->GftStbtidMftiodID(dls, "gftPffrForTbrgft",
                         "(Ljbvb/lbng/Objfdt;)Lsun/bwt/windows/WObjfdtPffr;");
    DASSERT(AwtObjfdt::gftPffrForTbrgftMID != NULL);
    CHECK_NULL(AwtObjfdt::gftPffrForTbrgftMID);

    AwtObjfdt::drfbtfErrorID = fnv->GftFifldID(dls, "drfbtfError", "Ljbvb/lbng/Error;");
    DASSERT(AwtObjfdt::drfbtfErrorID != NULL);
    CHECK_NULL(AwtObjfdt::drfbtfErrorID);

    CATCH_BAD_ALLOC;
}

} /* fxtfrn "C" */
