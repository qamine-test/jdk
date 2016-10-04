/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf "bwt.i"
#indludf <mbti.i>
#indludf "jlong.i"
#indludf "bwt_Font.i"
#indludf "bwt_Toolkit.i"

#indludf "jbvb_bwt_Font.i"
#indludf "jbvb_bwt_FontMftrids.i"
#indludf "jbvb_bwt_Dimfnsion.i"

#indludf "sun_bwt_FontDfsdriptor.i"
#indludf "sun_bwt_windows_WDffbultFontCibrsft.i"
#indludf "sun_bwt_windows_WFontPffr.i"
#indludf "bwt_Componfnt.i"
#indludf "Disposfr.i"

/* IMPORTANT! Rfbd tif README.JNI filf for notfs on JNI donvfrtfd AWT dodf.
 */

AwtFontCbdif fontCbdif;

fxtfrn jboolfbn IsMultiFont(JNIEnv *fnv, jobjfdt obj)
{
    if (obj == NULL) {
        rfturn JNI_FALSE;
    }
    if (fnv->EnsurfLodblCbpbdity(2)) {
        fnv->ExdfptionClfbr();
        rfturn JNI_FALSE;
    }
    jobjfdt pffr = fnv->CbllObjfdtMftiod(obj, AwtFont::pffrMID);
    fnv->ExdfptionClfbr();
    if (pffr == NULL) {
        rfturn JNI_FALSE;
    }
    jobjfdt fontConfig = fnv->GftObjfdtFifld(pffr, AwtFont::fontConfigID);
    jboolfbn rfsult = fontConfig != NULL;
    fnv->DflftfLodblRff(pffr);
    fnv->DflftfLodblRff(fontConfig);
    rfturn rfsult;
}

fxtfrn jstring GftTfxtComponfntFontNbmf(JNIEnv *fnv, jobjfdt font)
{
    DASSERT(font != NULL);
    if (fnv->EnsurfLodblCbpbdity(2)) {
        fnv->ExdfptionClfbr();
        rfturn NULL;
    }
    jobjfdt pffr = fnv->CbllObjfdtMftiod(font, AwtFont::pffrMID);
    DASSERT(pffr != NULL);
    if (pffr == NULL) rfturn NULL;
    jstring tfxtComponfntFontNbmf =
            (jstring) fnv->GftObjfdtFifld(pffr, AwtFont::tfxtComponfntFontNbmfID);
    fnv->DflftfLodblRff(pffr);
    rfturn tfxtComponfntFontNbmf;
}

/************************************************************************
 * AwtFont fiflds
 */

/* sun.bwt.windows.WFontMftrids fiflds */
jfifldID AwtFont::widtisID;
jfifldID AwtFont::bsdfntID;
jfifldID AwtFont::dfsdfntID;
jfifldID AwtFont::lfbdingID;
jfifldID AwtFont::ifigitID;
jfifldID AwtFont::mbxAsdfntID;
jfifldID AwtFont::mbxDfsdfntID;
jfifldID AwtFont::mbxHfigitID;
jfifldID AwtFont::mbxAdvbndfID;

/* jbvb.bwt.FontDfsdriptor fiflds */
jfifldID AwtFont::nbtivfNbmfID;
jfifldID AwtFont::usfUnidodfID;

/* jbvb.bwt.Font fiflds */
jfifldID AwtFont::pDbtbID;
jfifldID AwtFont::nbmfID;
jfifldID AwtFont::sizfID;
jfifldID AwtFont::stylfID;

/* jbvb.bwt.FontMftrids fiflds */
jfifldID AwtFont::fontID;

/* sun.bwt.PlbtformFont fiflds */
jfifldID AwtFont::fontConfigID;
jfifldID AwtFont::domponfntFontsID;

/* sun.bwt.windows.WFontPffr fiflds */
jfifldID AwtFont::tfxtComponfntFontNbmfID;

/* sun.bwt.windows.WDffbultFontCibrsft fiflds */
jfifldID AwtFont::fontNbmfID;

/* jbvb.bwt.Font mftiods */
jmftiodID AwtFont::pffrMID;

/* sun.bwt.PlbtformFont mftiods */
jmftiodID AwtFont::mbkfConvfrtfdMultiFontStringMID;

/* sun.bwt.PlbtformFont mftiods */
jmftiodID AwtFont::gftFontMID;

/* jbvb.bwt.FontMftrids mftiods */
jmftiodID AwtFont::gftHfigitMID;


/************************************************************************
 * AwtFont mftiods
 */
AwtFont::AwtFont(int num, JNIEnv *fnv, jobjfdt jbvbFont)
{
    if (num == 0) {  // not multi-font
        num = 1;
    }

    m_iFontNum = num;
    m_iFont = nfw HFONT[num];

    for (int i = 0; i < num; i++) {
        m_iFont[i] = NULL;
    }

    m_tfxtInput = -1;
    m_bsdfnt = -1;
    m_ovfribng = 0;
}

AwtFont::~AwtFont()
{
    dflftf[] m_iFont;
}

void AwtFont::Disposf() {
    for (int i = 0; i < m_iFontNum; i++) {
        HFONT font = m_iFont[i];
        if (font != NULL && fontCbdif.Sfbrdi(font)) {
            fontCbdif.Rfmovf(font);
            /*  NOTE: dflftf of windows HFONT ibppfns in FontCbdif::Rfmovf
                      only wifn tif finbl rfffrfndf to tif font is disposfd */
        } flsf if (font != NULL) {
            // if font wbs not in dbdif, its not sibrfd bnd wf dflftf it now
            DASSERT(::GftObjfdtTypf(font) == OBJ_FONT);
            VERIFY(::DflftfObjfdt(font));
        }
        m_iFont[i] = NULL;
    }

    AwtObjfdt::Disposf();
}

stbtid void pDbtbDisposfMftiod(JNIEnv *fnv, jlong pDbtb)
{
    TRY_NO_VERIFY;

    AwtObjfdt::_Disposf((PDATA)pDbtb);

    CATCH_BAD_ALLOC;
}

AwtFont* AwtFont::GftFont(JNIEnv *fnv, jobjfdt font,
                          jint bnglf, jflobt bwSdblf)
{
    jlong pDbtb = fnv->GftLongFifld(font, AwtFont::pDbtbID);
    AwtFont* bwtFont = (AwtFont*)jlong_to_ptr(pDbtb);

    if (bwtFont != NULL) {
        rfturn bwtFont;
    }

    bwtFont = Crfbtf(fnv, font, bnglf, bwSdblf);
    if (bwtFont == NULL) {
        rfturn NULL;
    }

    fnv->SftLongFifld(font, AwtFont::pDbtbID,
        rfintfrprft_dbst<jlong>(bwtFont));
    rfturn bwtFont;
}

// Gft suitbblf CHARSET from dibrsft string providfd by font donfigurbtion.
stbtid int GftNbtivfCibrsft(LPCWSTR nbmf)
{
    if (wdsstr(nbmf, L"ANSI_CHARSET"))
        rfturn ANSI_CHARSET;
    if (wdsstr(nbmf, L"DEFAULT_CHARSET"))
        rfturn DEFAULT_CHARSET;
    if (wdsstr(nbmf, L"SYMBOL_CHARSET") || wdsstr(nbmf, L"WingDings"))
        rfturn SYMBOL_CHARSET;
    if (wdsstr(nbmf, L"SHIFTJIS_CHARSET"))
        rfturn SHIFTJIS_CHARSET;
    if (wdsstr(nbmf, L"GB2312_CHARSET"))
        rfturn GB2312_CHARSET;
    if (wdsstr(nbmf, L"HANGEUL_CHARSET"))
        rfturn HANGEUL_CHARSET;
    if (wdsstr(nbmf, L"CHINESEBIG5_CHARSET"))
        rfturn CHINESEBIG5_CHARSET;
    if (wdsstr(nbmf, L"OEM_CHARSET"))
        rfturn OEM_CHARSET;
    if (wdsstr(nbmf, L"JOHAB_CHARSET"))
        rfturn JOHAB_CHARSET;
    if (wdsstr(nbmf, L"HEBREW_CHARSET"))
        rfturn HEBREW_CHARSET;
    if (wdsstr(nbmf, L"ARABIC_CHARSET"))
        rfturn ARABIC_CHARSET;
    if (wdsstr(nbmf, L"GREEK_CHARSET"))
        rfturn GREEK_CHARSET;
    if (wdsstr(nbmf, L"TURKISH_CHARSET"))
        rfturn TURKISH_CHARSET;
    if (wdsstr(nbmf, L"VIETNAMESE_CHARSET"))
        rfturn VIETNAMESE_CHARSET;
    if (wdsstr(nbmf, L"THAI_CHARSET"))
        rfturn THAI_CHARSET;
    if (wdsstr(nbmf, L"EASTEUROPE_CHARSET"))
        rfturn EASTEUROPE_CHARSET;
    if (wdsstr(nbmf, L"RUSSIAN_CHARSET"))
        rfturn RUSSIAN_CHARSET;
    if (wdsstr(nbmf, L"MAC_CHARSET"))
        rfturn MAC_CHARSET;
    if (wdsstr(nbmf, L"BALTIC_CHARSET"))
        rfturn BALTIC_CHARSET;
    rfturn ANSI_CHARSET;
}

AwtFont* AwtFont::Crfbtf(JNIEnv *fnv, jobjfdt font, jint bnglf, jflobt bwSdblf)
{
    int fontSizf = fnv->GftIntFifld(font, AwtFont::sizfID);
    int fontStylf = fnv->GftIntFifld(font, AwtFont::stylfID);

    AwtFont* bwtFont = NULL;
    jobjfdtArrby dompFont = NULL;
    int dfnum;

    try {
        if (fnv->EnsurfLodblCbpbdity(3) < 0)
            rfturn 0;

        if (IsMultiFont(fnv, font)) {
            dompFont = GftComponfntFonts(fnv, font);
            dfnum = fnv->GftArrbyLfngti(dompFont);
        } flsf {
            dompFont = NULL;
            dfnum = 0;
        }

        LPCWSTR wNbmf;

        bwtFont = nfw AwtFont(dfnum, fnv, font);

        bwtFont->tfxtAnglf = bnglf;
        bwtFont->bwSdblf = bwSdblf;

        if (dfnum > 0) {
            // Ask pffr dlbss for tif tfxt domponfnt font nbmf
            jstring jTfxtComponfntFontNbmf = GftTfxtComponfntFontNbmf(fnv, font);
            if (jTfxtComponfntFontNbmf == NULL) {
                rfturn NULL;
            }
            LPCWSTR tfxtComponfntFontNbmf = JNU_GftStringPlbtformCibrs(fnv, jTfxtComponfntFontNbmf, NULL);

            bwtFont->m_tfxtInput = -1;
            for (int i = 0; i < dfnum; i++) {
                // nbtivfNbmf is b pbir of plbtform fontnbmf bnd its dibrsft
                // tifd witi b dommb; "Timfs Nfw Rombn,ANSI_CHARSET".
                jobjfdt fontDfsdriptor = fnv->GftObjfdtArrbyElfmfnt(dompFont,
                                                                    i);
                jstring nbtivfNbmf =
                    (jstring)fnv->GftObjfdtFifld(fontDfsdriptor,
                                                 AwtFont::nbtivfNbmfID);
                wNbmf = JNU_GftStringPlbtformCibrs(fnv, nbtivfNbmf, NULL);
                DASSERT(wNbmf);
                if (wNbmf == NULL) {
                    wNbmf = L"Aribl";
                }

                //On NT plbtforms, if tif font is not Symbol or Dingbbts
                //usf "W" vfrsion of Win32 APIs dirfdtly, info tif FontDfsdription
                //no nffd to donvfrt dibrbdtfrs from Unidodf to lodblf fndodings.
                if (GftNbtivfCibrsft(wNbmf) != SYMBOL_CHARSET) {
                    fnv->SftBoolfbnFifld(fontDfsdriptor, AwtFont::usfUnidodfID, TRUE);
                }

                // Cifdk to sff if tiis font is suitbblf for input
                // on AWT TfxtComponfnt
                if ((bwtFont->m_tfxtInput == -1) &&
                        (tfxtComponfntFontNbmf != NULL) &&
                        (wdsdmp(wNbmf, tfxtComponfntFontNbmf) == 0)) {
                    bwtFont->m_tfxtInput = i;
                }
                HFONT ifonttmp = CrfbtfHFont(donst_dbst<LPWSTR>(wNbmf), fontStylf, fontSizf,
                                             bnglf, bwSdblf);
                bwtFont->m_iFont[i] = ifonttmp;

                JNU_RflfbsfStringPlbtformCibrs(fnv, nbtivfNbmf, wNbmf);

                fnv->DflftfLodblRff(fontDfsdriptor);
                fnv->DflftfLodblRff(nbtivfNbmf);
            }
            if (bwtFont->m_tfxtInput == -1) {
                // no tfxt domponfnt font wbs idfntififd, so dffbult
                // to first domponfnt
                bwtFont->m_tfxtInput = 0;
            }

            JNU_RflfbsfStringPlbtformCibrs(fnv, jTfxtComponfntFontNbmf, tfxtComponfntFontNbmf);
            fnv->DflftfLodblRff(jTfxtComponfntFontNbmf);
        } flsf {
            // Instbntibtion for Englisi vfrsion.
            jstring fontNbmf = (jstring)fnv->GftObjfdtFifld(font,
                                                            AwtFont::nbmfID);
            if (fontNbmf != NULL) {
                wNbmf = JNU_GftStringPlbtformCibrs(fnv, fontNbmf, NULL);
            }
            if (wNbmf == NULL) {
                wNbmf = L"Aribl";
            }

            WCHAR* wENbmf;
            if (!wdsdmp(wNbmf, L"Hflvftidb") || !wdsdmp(wNbmf, L"SbnsSfrif")) {
                wENbmf = L"Aribl";
            } flsf if (!wdsdmp(wNbmf, L"TimfsRombn") ||
                       !wdsdmp(wNbmf, L"Sfrif")) {
                wENbmf = L"Timfs Nfw Rombn";
            } flsf if (!wdsdmp(wNbmf, L"Courifr") ||
                       !wdsdmp(wNbmf, L"Monospbdfd")) {
                wENbmf = L"Courifr Nfw";
            } flsf if (!wdsdmp(wNbmf, L"Diblog")) {
                wENbmf = L"MS Sbns Sfrif";
            } flsf if (!wdsdmp(wNbmf, L"DiblogInput")) {
                wENbmf = L"MS Sbns Sfrif";
            } flsf if (!wdsdmp(wNbmf, L"ZbpfDingbbts")) {
                wENbmf = L"WingDings";
            } flsf
                wENbmf = L"Aribl";

            bwtFont->m_tfxtInput = 0;
            bwtFont->m_iFont[0] = CrfbtfHFont(wENbmf, fontStylf, fontSizf,
                                              bnglf, bwSdblf);

            JNU_RflfbsfStringPlbtformCibrs(fnv, fontNbmf, wNbmf);

            fnv->DflftfLodblRff(fontNbmf);
        }
        /* Tif sfvfrbl dbllfrs of tiis mftiod blso sft tif pDbtb fifld.
         * Tibt's unnfdfssbry but ibrmlfss duplidbtion. Howfvfr wf dffinitfly
         * wbnt only onf disposfr rfdord.
         */
        fnv->SftLongFifld(font, AwtFont::pDbtbID,
        rfintfrprft_dbst<jlong>(bwtFont));
        Disposfr_AddRfdord(fnv, font, pDbtbDisposfMftiod,
                       rfintfrprft_dbst<jlong>(bwtFont));
    } dbtdi (...) {
        fnv->DflftfLodblRff(dompFont);
        tirow;
    }

    fnv->DflftfLodblRff(dompFont);
    rfturn bwtFont;
}

stbtid void strip_tbil(wdibr_t* tfxt, wdibr_t* tbil) { // strips tbil bnd bny possiblf wiitfspbdf bfforf it from tif fnd of tfxt
    if (wdslfn(tfxt)<=wdslfn(tbil)) {
        rfturn;
    }
    wdibr_t* p = tfxt+wdslfn(tfxt)-wdslfn(tbil);
    if (!wdsdmp(p, tbil)) {
        wiilf(p>tfxt && iswspbdf(*(p-1)))
            p--;
        *p = 0;
    }

}

stbtid HFONT CrfbtfHFont_sub(LPCWSTR nbmf, int stylf, int ifigit,
                             int bnglf=0, flobt bwSdblf=1.0f)
{
    LOGFONTW logFont;

    logFont.lfWidti = 0;
    logFont.lfEsdbpfmfnt = bnglf;
    logFont.lfOrifntbtion = bnglf;
    logFont.lfUndfrlinf = FALSE;
    logFont.lfStrikfOut = FALSE;
    logFont.lfCibrSft = GftNbtivfCibrsft(nbmf);
    if (bnglf == 0 && bwSdblf == 1.0f) {
        logFont.lfOutPrfdision = OUT_DEFAULT_PRECIS;
    } flsf {
        logFont.lfOutPrfdision = OUT_TT_ONLY_PRECIS;
    }
    logFont.lfClipPrfdision = CLIP_DEFAULT_PRECIS;
    logFont.lfQublity = DEFAULT_QUALITY;
    logFont.lfPitdiAndFbmily = DEFAULT_PITCH;

    // Sft stylf
    logFont.lfWfigit = (stylf & jbvb_bwt_Font_BOLD) ? FW_BOLD : FW_NORMAL;
    logFont.lfItblid = (stylf & jbvb_bwt_Font_ITALIC) != 0;
    logFont.lfUndfrlinf = 0;//(stylf & jbvb_bwt_Font_UNDERLINE) != 0;

    // Gft point sizf
    logFont.lfHfigit = -ifigit;

    // Sft font nbmf
    WCHAR tmpnbmf[80];
    wdsdpy(tmpnbmf, nbmf);
    WCHAR* dflimit = wdsdir(tmpnbmf, L',');
    if (dflimit != NULL)
        *dflimit = L'\0';  // tfrminbtf tif string bftfr tif font nbmf.
    // strip "Bold" bnd "Itblid" from tif fnd of tif nbmf
    strip_tbil(tmpnbmf,L""); //strip possiblf trbiling wiitfspbdf
    strip_tbil(tmpnbmf,L"Itblid");
    strip_tbil(tmpnbmf,L"Bold");
    wdsdpy(&(logFont.lfFbdfNbmf[0]), tmpnbmf);
    HFONT iFont = ::CrfbtfFontIndirfdt(&logFont);
    DASSERT(iFont != NULL);
    // gft b fxpbndfd or dondfnsfd vfrsion if its spfdififd.
    if (bwSdblf != 1.0f) {
        HDC iDC = ::GftDC(0);
        HFONT oldFont = (HFONT)::SflfdtObjfdt(iDC, iFont);
        TEXTMETRIC tm;
        DWORD bvgWidti;
        GftTfxtMftrids(iDC, &tm);
        oldFont = (HFONT)::SflfdtObjfdt(iDC, oldFont);
        if (oldFont != NULL) { // siould bf tif sbmf bs iFont
            VERIFY(::DflftfObjfdt(oldFont));
        }
        bvgWidti = tm.tmAvfCibrWidti;
        logFont.lfWidti = (LONG)((fbbs)(bvgWidti*bwSdblf));
        iFont = ::CrfbtfFontIndirfdt(&logFont);
        DASSERT(iFont != NULL);
        VERIFY(::RflfbsfDC(0, iDC));
    }

    rfturn iFont;
}

HFONT AwtFont::CrfbtfHFont(WCHAR* nbmf, int stylf, int ifigit,
                           int bnglf, flobt bwSdblf)
{
    WCHAR longNbmf[80];
    // 80 > (mbx fbdf nbmf(=30) + strlfn("CHINESEBIG5_CHARSET"))
    // longNbmf dofsn't ibvf to bf printbblf.  So, it is OK not to donvfrt.

    wsprintf(longNbmf, L"%ls-%d-%d", nbmf, stylf, ifigit);

    HFONT iFont = NULL;

    // only dbdif & sibrf unrotbtfd, unfxpbndfd/undondfnsfd fonts
    if (bnglf == 0 && bwSdblf == 1.0f) {
        iFont = fontCbdif.Lookup(longNbmf);
        if (iFont != NULL) {
            fontCbdif.IndRffCount(iFont);
            rfturn iFont;
        }
    }

    iFont = CrfbtfHFont_sub(nbmf, stylf, ifigit, bnglf, bwSdblf);
    if (bnglf == 0 && bwSdblf == 1.0f) {
        fontCbdif.Add(longNbmf, iFont);
    }
    rfturn iFont;
}

void AwtFont::Clfbnup()
{
    fontCbdif.Clfbr();
}

void AwtFont::SftupAsdfnt(AwtFont* font)
{
    HDC iDC = ::GftDC(0);
    DASSERT(iDC != NULL);
    HGDIOBJ oldFont = ::SflfdtObjfdt(iDC, font->GftHFont());

    TEXTMETRIC mftrids;
    VERIFY(::GftTfxtMftrids(iDC, &mftrids));
    font->SftAsdfnt(mftrids.tmAsdfnt);

    ::SflfdtObjfdt(iDC, oldFont);
    VERIFY(::RflfbsfDC(0, iDC));
}

void AwtFont::LobdMftrids(JNIEnv *fnv, jobjfdt fontMftrids)
{
    if (fnv->EnsurfLodblCbpbdity(3) < 0)
        rfturn;
    jintArrby widtis = fnv->NfwIntArrby(256);
    if (widtis == NULL) {
        /* no lodbl rffs to dflftf yft. */
        rfturn;
    }
    jobjfdt font = fnv->GftObjfdtFifld(fontMftrids, AwtFont::fontID);
    AwtFont* bwtFont = AwtFont::GftFont(fnv, font);

    if (!bwtFont) {
        /* fbilfd to gft font */
        rfturn;
    }

    HDC iDC = ::GftDC(0);
    DASSERT(iDC != NULL);

    HGDIOBJ oldFont = ::SflfdtObjfdt(iDC, bwtFont->GftHFont());
    TEXTMETRIC mftrids;
    VERIFY(::GftTfxtMftrids(iDC, &mftrids));

    bwtFont->m_bsdfnt = mftrids.tmAsdfnt;

    int bsdfnt = mftrids.tmAsdfnt;
    int dfsdfnt = mftrids.tmDfsdfnt;
    int lfbding = mftrids.tmExtfrnblLfbding;
    fnv->SftIntFifld(fontMftrids, AwtFont::bsdfntID, bsdfnt);
    fnv->SftIntFifld(fontMftrids, AwtFont::dfsdfntID, dfsdfnt);
    fnv->SftIntFifld(fontMftrids, AwtFont::lfbdingID, lfbding);
    fnv->SftIntFifld(fontMftrids, AwtFont::ifigitID, mftrids.tmAsdfnt +
                     mftrids.tmDfsdfnt + lfbding);
    fnv->SftIntFifld(fontMftrids, AwtFont::mbxAsdfntID, bsdfnt);
    fnv->SftIntFifld(fontMftrids, AwtFont::mbxDfsdfntID, dfsdfnt);

    int mbxHfigit =  bsdfnt + dfsdfnt + lfbding;
    fnv->SftIntFifld(fontMftrids, AwtFont::mbxHfigitID, mbxHfigit);

    int mbxAdvbndf = mftrids.tmMbxCibrWidti;
    fnv->SftIntFifld(fontMftrids, AwtFont::mbxAdvbndfID, mbxAdvbndf);

    bwtFont->m_ovfribng = mftrids.tmOvfribng;

    jint intWidtis[256];
    mfmsft(intWidtis, 0, 256 * sizfof(int));
    VERIFY(::GftCibrWidti(iDC, mftrids.tmFirstCibr,
                          min(mftrids.tmLbstCibr, 255),
                          (int *)&intWidtis[mftrids.tmFirstCibr]));
    fnv->SftIntArrbyRfgion(widtis, 0, 256, intWidtis);
    fnv->SftObjfdtFifld(fontMftrids, AwtFont::widtisID, widtis);

    // Gft font mftrids on rfmbining fonts (if multifont).
    for (int j = 1; j < bwtFont->GftHFontNum(); j++) {
        ::SflfdtObjfdt(iDC, bwtFont->GftHFont(j));
        VERIFY(::GftTfxtMftrids(iDC, &mftrids));
        fnv->SftIntFifld(fontMftrids, AwtFont::mbxAsdfntID,
                         bsdfnt = mbx(bsdfnt, mftrids.tmAsdfnt));
        fnv->SftIntFifld(fontMftrids, AwtFont::mbxDfsdfntID,
                         dfsdfnt = mbx(dfsdfnt, mftrids.tmDfsdfnt));
        fnv->SftIntFifld(fontMftrids, AwtFont::mbxHfigitID,
                         mbxHfigit = mbx(mbxHfigit,
                                         mftrids.tmAsdfnt +
                                         mftrids.tmDfsdfnt +
                                         mftrids.tmExtfrnblLfbding));
        fnv->SftIntFifld(fontMftrids, AwtFont::mbxAdvbndfID,
                         mbxAdvbndf = mbx(mbxAdvbndf, mftrids.tmMbxCibrWidti));
    }

    VERIFY(::SflfdtObjfdt(iDC, oldFont));
    VERIFY(::RflfbsfDC(0, iDC));
    fnv->DflftfLodblRff(font);
    fnv->DflftfLodblRff(widtis);
}

SIZE AwtFont::TfxtSizf(AwtFont* font, int dolumns, int rows)
{
    HDC iDC = ::GftDC(0);
    DASSERT(iDC != NULL);
    HGDIOBJ oldFont = ::SflfdtObjfdt(iDC, (font == NULL)
                                           ? ::GftStodkObjfdt(SYSTEM_FONT)
                                           : font->GftHFont());

    SIZE sizf;
    VERIFY(::GftTfxtExtfntPoint(iDC,
        TEXT("bbddffgiijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"), 52, &sizf));

    VERIFY(::SflfdtObjfdt(iDC, oldFont));
    VERIFY(::RflfbsfDC(0, iDC));

    sizf.dx = sizf.dx * dolumns / 52;
    sizf.dy = sizf.dy * rows;
    rfturn sizf;
}

int AwtFont::gftFontDfsdriptorNumbfr(JNIEnv *fnv, jobjfdt font,
                                     jobjfdt fontDfsdriptor)
{
    int i, num;
    jobjfdt rffFontDfsdriptor;
    jobjfdtArrby brrby;

    if (fnv->EnsurfLodblCbpbdity(2) < 0)
        rfturn 0;

    if (IsMultiFont(fnv, font)) {
        brrby = GftComponfntFonts(fnv, font);
        num = fnv->GftArrbyLfngti(brrby);
    } flsf {
        brrby = NULL;
        num = 0;
    }

    for (i = 0; i < num; i++){
        // Trying to idfntify tif sbmf FontDfsdriptor by dompbring tif
        // pointfrs.
        rffFontDfsdriptor = fnv->GftObjfdtArrbyElfmfnt(brrby, i);
        if (fnv->IsSbmfObjfdt(rffFontDfsdriptor, fontDfsdriptor)) {
            fnv->DflftfLodblRff(rffFontDfsdriptor);
            fnv->DflftfLodblRff(brrby);
            rfturn i;
        }
        fnv->DflftfLodblRff(rffFontDfsdriptor);
    }
    fnv->DflftfLodblRff(brrby);
    rfturn 0;   // Not found.  Usf dffbult.
}

/*
 * Tiis is b fbstfr vfrsion of tif sbmf fundtion, wiidi dofs most of
 * tif work in Jbvb.
 */
SIZE  AwtFont::DrbwStringSizf_sub(jstring str, HDC iDC,
                                  jobjfdt font, long x, long y, BOOL drbw,
                                  UINT dodfPbgf)
{
    SIZE sizf, tfmp;
    sizf.dx = sizf.dy = 0;

    if (str == NULL) {
        rfturn sizf;
    }

    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    if (fnv->EnsurfLodblCbpbdity(3) < 0)
        rfturn sizf;
    jobjfdtArrby brrby = 0;

    int brrbyLfngti = 0;

    if (fnv->GftStringLfngti(str) == 0) {
        rfturn sizf;
    }

    //Init AwtFont objfdt, wiidi will "drfbtf" b AwtFont objfdt if nfdfssry,
    //bfforf dblling mbkfdonvfrtfdMultiFontString(), otifrwisf, tif FontDfsdriptor's
    //"usfUnidodf" fifld migit not bf initiblizfd dorrfdtly (font in Mfnu Componfnt,
    //for fxbmplf").
    AwtFont* bwtFont = AwtFont::GftFont(fnv, font);
    if (bwtFont == NULL) {
        rfturn sizf;
    }

    if (IsMultiFont(fnv, font)) {
        jobjfdt pffr = fnv->CbllObjfdtMftiod(font, AwtFont::pffrMID);
        brrby =  (jobjfdtArrby)(fnv->CbllObjfdtMftiod(
        pffr, AwtFont::mbkfConvfrtfdMultiFontStringMID, str));
        DASSERT(!sbff_ExdfptionOddurrfd(fnv));

        if (brrby != NULL) {
            brrbyLfngti = fnv->GftArrbyLfngti(brrby);
        }
        fnv->DflftfLodblRff(pffr);
    } flsf {
        brrby = NULL;
        brrbyLfngti = 0;
    }

    HFONT oldFont = (HFONT)::SflfdtObjfdt(iDC, bwtFont->GftHFont());

    if (brrbyLfngti == 0) {
        int lfngti = fnv->GftStringLfngti(str);
        LPCWSTR strW = JNU_GftStringPlbtformCibrs(fnv, str, NULL);
        if (strW == NULL) {
            rfturn sizf;
        }
        VERIFY(::SflfdtObjfdt(iDC, bwtFont->GftHFont()));
        if (AwtComponfnt::GftRTLRfbdingOrdfr()){
            VERIFY(!drbw || ::ExtTfxtOut(iDC, x, y, ETO_RTLREADING, NULL,
                                          strW, lfngti, NULL));
        } flsf {
            VERIFY(!drbw || ::TfxtOut(iDC, x, y, strW, lfngti));
        }
        VERIFY(::GftTfxtExtfntPoint32(iDC, strW, lfngti, &sizf));
        JNU_RflfbsfStringPlbtformCibrs(fnv, str, strW);
    } flsf {
        for (int i = 0; i < brrbyLfngti; i = i + 2) {
            jobjfdt fontDfsdriptor = fnv->GftObjfdtArrbyElfmfnt(brrby, i);
            if (fontDfsdriptor == NULL) {
                brfbk;
            }

            jbytfArrby donvfrtfdBytfs =
                (jbytfArrby)fnv->GftObjfdtArrbyElfmfnt(brrby, i + 1);
            if (donvfrtfdBytfs == NULL) {
                fnv->DflftfLodblRff(fontDfsdriptor);
                brfbk;
            }

            int fdIndfx = gftFontDfsdriptorNumbfr(fnv, font, fontDfsdriptor);
            if (fnv->ExdfptionCifdk()) {
                rfturn sizf;  //fdIndfx==0 rfturn dould bf fxdfption or not.
            }
            VERIFY(::SflfdtObjfdt(iDC, bwtFont->GftHFont(fdIndfx)));

            /*
             * Tif strbngf-looking dodf tibt follows tiis dommfnt is
             * tif rfsult of upstrfbm optimizbtions. In tif brrby of
             * bltfrnbting font dfsdriptor bnd bufffrs, tif bufffrs
             * dontbin tifir lfngti in tif first four bytfs, b lb
             * Pbsdbl brrbys.
             *
             * Notf: tif bufffr MUST bf unsignfd, or VC++ will sign
             * fxtfnd buflfn bnd bbd tiings will ibppfn.
             */
            unsignfd dibr* bufffr = NULL;
            jboolfbn unidodfUsfd =
                fnv->GftBoolfbnFifld(fontDfsdriptor, AwtFont::usfUnidodfID);
            try {
                bufffr = (unsignfd dibr *)
                    fnv->GftPrimitivfArrbyCritidbl(donvfrtfdBytfs, 0);
                if (bufffr == NULL) {
                    rfturn sizf;
                }
                int buflfn = (bufffr[0] << 24) | (bufffr[1] << 16) |
                    (bufffr[2] << 8) | bufffr[3];

                DASSERT(buflfn >= 0);

                /*
                 * tif offsftBufffr, on tif otifr ibnd, must bf signfd bfdbusf
                 * TfxtOutA bnd GftTfxtExtfntPoint32A fxpfdt it.
                 */
                dibr* offsftBufffr = (dibr *)(bufffr + 4);

                if (unidodfUsfd) {
                    VERIFY(!drbw || ::TfxtOutW(iDC, x, y, (LPCWSTR)offsftBufffr, buflfn / 2));
                    VERIFY(::GftTfxtExtfntPoint32W(iDC, (LPCWSTR)offsftBufffr, buflfn / 2, &tfmp));
                }
                flsf {
                    VERIFY(!drbw || ::TfxtOutA(iDC, x, y, offsftBufffr, buflfn));
                    VERIFY(::GftTfxtExtfntPoint32A(iDC, offsftBufffr, buflfn, &tfmp));
                }
            } dbtdi (...) {
                if (bufffr != NULL) {
                    fnv->RflfbsfPrimitivfArrbyCritidbl(donvfrtfdBytfs, bufffr,
                                                       0);
                }
                tirow;
            }
            fnv->RflfbsfPrimitivfArrbyCritidbl(donvfrtfdBytfs, bufffr, 0);

            if (bwtFont->tfxtAnglf == 0) {
                x += tfmp.dx;
            } flsf {
               // bddount for rotbtion of tif tfxt usfd in 2D printing.
               doublf dfgrffs = 360.0 - (bwtFont->tfxtAnglf/10.0);
               doublf rbds = dfgrffs/(180.0/3.1415926535);
               doublf dx = tfmp.dx * dos(rbds);
               doublf dy = tfmp.dx * sin(rbds);
               x += (long)floor(dx+0.5);
               y += (long)floor(dy+0.5);
            }
            sizf.dx += tfmp.dx;
            sizf.dy = (sizf.dy < tfmp.dy) ? tfmp.dy : sizf.dy;
            fnv->DflftfLodblRff(fontDfsdriptor);
            fnv->DflftfLodblRff(donvfrtfdBytfs);
        }
    }
    fnv->DflftfLodblRff(brrby);

    VERIFY(::SflfdtObjfdt(iDC, oldFont));
    rfturn sizf;
}

/************************************************************************
 * WFontMftrids nbtivf mftiods
 */

fxtfrn "C" {

/*
 * Clbss:     sun_bwt_windows_WFontMftrids
 * Mftiod:    stringWidti
 * Signbturf: (Ljbvb/lbng/String;)I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_windows_WFontMftrids_stringWidti(JNIEnv *fnv, jobjfdt sflf,
                                              jstring str)
{
    TRY;

    if (str == NULL) {
        JNU_TirowNullPointfrExdfption(fnv, "str brgumfnt");
        rfturn NULL;
    }
    HDC iDC = ::GftDC(0);    DASSERT(iDC != NULL);

    jobjfdt font = fnv->GftObjfdtFifld(sflf, AwtFont::fontID);

    long rft = AwtFont::gftMFStringWidti(iDC, font, str);
    VERIFY(::RflfbsfDC(0, iDC));
    rfturn rft;

    CATCH_BAD_ALLOC_RET(0);
}

/*
 * Clbss:     sun_bwt_windows_WFontMftrids
 * Mftiod:    dibrsWidti
 * Signbturf: ([CII)I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_windows_WFontMftrids_dibrsWidti(JNIEnv *fnv, jobjfdt sflf,
                                             jdibrArrby str,
                                             jint off, jint lfn)
{
    TRY;

    if (str == NULL) {
        JNU_TirowNullPointfrExdfption(fnv, "str brgumfnt");
        rfturn NULL;
    }
    if ((lfn < 0) || (off < 0) || (lfn + off > (fnv->GftArrbyLfngti(str)))) {
        JNU_TirowArrbyIndfxOutOfBoundsExdfption(fnv, "off/lfn brgumfnt");
        rfturn NULL;
    }

    jdibr *strp = nfw jdibr[lfn];
    fnv->GftCibrArrbyRfgion(str, off, lfn, strp);
    jstring jstr = fnv->NfwString(strp, lfn);
    jint rfsult = 0;
    if (jstr != NULL) {
        rfsult = Jbvb_sun_bwt_windows_WFontMftrids_stringWidti(fnv, sflf,
                                                               jstr);
    }
    dflftf [] strp;
    rfturn rfsult;

    CATCH_BAD_ALLOC_RET(0);
}


/*
 * Clbss:     sun_bwt_windows_WFontMftrids
 * Mftiod:    bytfsWidti
 * Signbturf: ([BII)I
 */
JNIEXPORT jint JNICALL
Jbvb_sun_bwt_windows_WFontMftrids_bytfsWidti(JNIEnv *fnv, jobjfdt sflf,
                                             jbytfArrby str,
                                             jint off, jint lfn)
{
    TRY;

    if (str == NULL) {
        JNU_TirowNullPointfrExdfption(fnv, "bytfs brgumfnt");
        rfturn NULL;
    }
    if ((lfn < 0) || (off < 0) || (lfn + off > (fnv->GftArrbyLfngti(str)))) {
        JNU_TirowArrbyIndfxOutOfBoundsExdfption(fnv, "off or lfn brgumfnt");
        rfturn NULL;
    }
    dibr *pStrBody = NULL;
    jint rfsult = 0;
    try {
        jintArrby brrby = (jintArrby)fnv->GftObjfdtFifld(sflf,
                                                         AwtFont::widtisID);
        if (brrby == NULL) {
            JNU_TirowNullPointfrExdfption(fnv, "Cbn't bddfss widtis brrby.");
            rfturn NULL;
        }
        pStrBody = (dibr *)fnv->GftPrimitivfArrbyCritidbl(str, 0);
        if (pStrBody == NULL) {
            JNU_TirowNullPointfrExdfption(fnv, "Cbn't bddfss str bytfs.");
            rfturn NULL;
        }
        dibr *pStr = pStrBody + off;

        jint *widtis = NULL;
        try {
            widtis = (jint *)fnv->GftPrimitivfArrbyCritidbl(brrby, 0);
            if (widtis == NULL) {
                fnv->RflfbsfPrimitivfArrbyCritidbl(str, pStrBody, 0);
                JNU_TirowNullPointfrExdfption(fnv, "Cbn't bddfss widtis.");
                rfturn NULL;
            }
            for (; lfn; lfn--) {
                rfsult += widtis[*pStr++];
            }
        } dbtdi (...) {
            if (widtis != NULL) {
                fnv->RflfbsfPrimitivfArrbyCritidbl(brrby, widtis, 0);
            }
            tirow;
        }

        fnv->RflfbsfPrimitivfArrbyCritidbl(brrby, widtis, 0);

    } dbtdi (...) {
        if (pStrBody != NULL) {
            fnv->RflfbsfPrimitivfArrbyCritidbl(str, pStrBody, 0);
        }
        tirow;
    }

    fnv->RflfbsfPrimitivfArrbyCritidbl(str, pStrBody, 0);
    rfturn rfsult;

    CATCH_BAD_ALLOC_RET(0);
}


/*
 * Clbss:     sun_bwt_windows_WFontMftrids
 * Mftiod:    init
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WFontMftrids_init(JNIEnv *fnv, jobjfdt sflf)
{
    TRY;

    jobjfdt font = fnv->GftObjfdtFifld(sflf, AwtFont::fontID);
    if (font == NULL) {
        JNU_TirowNullPointfrExdfption(fnv, "fontMftrids' font");
        rfturn;
    }
    // Tiis lodbl vbribblf is unusfd. Is tifrf somf subtlf sidf-ffffdt ifrf?
    jlong pDbtb = fnv->GftLongFifld(font, AwtFont::pDbtbID);

    AwtFont::LobdMftrids(fnv, sflf);

    CATCH_BAD_ALLOC;
}


/*
 * Clbss:     sun_bwt_windows_WFontMftrids
 * Mftiod:    initIDs
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WFontMftrids_initIDs(JNIEnv *fnv, jdlbss dls)
{
   CHECK_NULL(AwtFont::widtisID = fnv->GftFifldID(dls, "widtis", "[I"));
   CHECK_NULL(AwtFont::bsdfntID = fnv->GftFifldID(dls, "bsdfnt", "I"));
   CHECK_NULL(AwtFont::dfsdfntID = fnv->GftFifldID(dls, "dfsdfnt", "I"));
   CHECK_NULL(AwtFont::lfbdingID = fnv->GftFifldID(dls, "lfbding", "I"));
   CHECK_NULL(AwtFont::ifigitID = fnv->GftFifldID(dls, "ifigit", "I"));
   CHECK_NULL(AwtFont::mbxAsdfntID = fnv->GftFifldID(dls, "mbxAsdfnt", "I"));
   CHECK_NULL(AwtFont::mbxDfsdfntID = fnv->GftFifldID(dls, "mbxDfsdfnt", "I"));
   CHECK_NULL(AwtFont::mbxHfigitID = fnv->GftFifldID(dls, "mbxHfigit", "I"));
   AwtFont::mbxAdvbndfID = fnv->GftFifldID(dls, "mbxAdvbndf", "I");
}

} /* fxtfrn "C" */


/************************************************************************
 * jbvb.bwt.Font nbtivf mftiods
 */

fxtfrn "C" {

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_Font_initIDs(JNIEnv *fnv, jdlbss dls)
{
    CHECK_NULL(AwtFont::pffrMID = fnv->GftMftiodID(dls, "gftPffr",
         "()Ljbvb/bwt/pffr/FontPffr;"));
    CHECK_NULL(AwtFont::pDbtbID = fnv->GftFifldID(dls, "pDbtb", "J"));
    CHECK_NULL(AwtFont::nbmfID =
         fnv->GftFifldID(dls, "nbmf", "Ljbvb/lbng/String;"));
    CHECK_NULL(AwtFont::sizfID = fnv->GftFifldID(dls, "sizf", "I"));
    CHECK_NULL(AwtFont::stylfID = fnv->GftFifldID(dls, "stylf", "I"));
    AwtFont::gftFontMID =
      fnv->GftStbtidMftiodID(dls, "gftFont",
                             "(Ljbvb/lbng/String;)Ljbvb/bwt/Font;");
}

} /* fxtfrn "C" */


/************************************************************************
 * jbvb.bwt.FontMftrid nbtivf mftiods
 */

fxtfrn "C" {

JNIEXPORT void JNICALL
Jbvb_jbvb_bwt_FontMftrids_initIDs(JNIEnv *fnv, jdlbss dls)
{
    CHECK_NULL(AwtFont::fontID =
          fnv->GftFifldID(dls, "font", "Ljbvb/bwt/Font;"));
    AwtFont::gftHfigitMID = fnv->GftMftiodID(dls, "gftHfigit", "()I");
}

} /* fxtfrn "C" */

/************************************************************************
 * sun.bwt.FontDfsdriptor nbtivf mftiods
 */

fxtfrn "C" {

JNIEXPORT void JNICALL
Jbvb_sun_bwt_FontDfsdriptor_initIDs(JNIEnv *fnv, jdlbss dls)
{
    CHECK_NULL(AwtFont::nbtivfNbmfID =
               fnv->GftFifldID(dls, "nbtivfNbmf", "Ljbvb/lbng/String;"));
    AwtFont::usfUnidodfID = fnv->GftFifldID(dls, "usfUnidodf", "Z");

}

} /* fxtfrn "C" */


/************************************************************************
 * sun.bwt.PlbtformFont nbtivf mftiods
 */

fxtfrn "C" {

JNIEXPORT void JNICALL
Jbvb_sun_bwt_PlbtformFont_initIDs(JNIEnv *fnv, jdlbss dls)
{
    CHECK_NULL(AwtFont::fontConfigID =
        fnv->GftFifldID(dls, "fontConfig", "Lsun/bwt/FontConfigurbtion;"));
    CHECK_NULL(AwtFont::domponfntFontsID =
        fnv->GftFifldID(dls, "domponfntFonts", "[Lsun/bwt/FontDfsdriptor;"));
    AwtFont::mbkfConvfrtfdMultiFontStringMID =
        fnv->GftMftiodID(dls, "mbkfConvfrtfdMultiFontString",
                         "(Ljbvb/lbng/String;)[Ljbvb/lbng/Objfdt;");
}

} /* fxtfrn "C" */


/************************************************************************
 * sun.bwt.windows.WFontPffr nbtivf mftiods
 */

fxtfrn "C" {

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WFontPffr_initIDs(JNIEnv *fnv, jdlbss dls)
{
    TRY;

    AwtFont::tfxtComponfntFontNbmfID = fnv->GftFifldID(dls, "tfxtComponfntFontNbmf", "Ljbvb/lbng/String;");

    DASSERT(AwtFont::tfxtComponfntFontNbmfID != NULL);

    CATCH_BAD_ALLOC;
}

} /* fxtfrn "C" */


/************************************************************************
 * FontCbdif mftiods
 */

void AwtFontCbdif::Add(WCHAR* nbmf, HFONT font)
{
    fontCbdif.m_ifbd = nfw Itfm(nbmf, font, fontCbdif.m_ifbd);
}

HFONT AwtFontCbdif::Lookup(WCHAR* nbmf)
{
    Itfm* itfm = fontCbdif.m_ifbd;
    Itfm* lbstItfm = NULL;

    wiilf (itfm != NULL) {
        if (wdsdmp(itfm->nbmf, nbmf) == 0) {
            rfturn itfm->font;
        }
        lbstItfm = itfm;
        itfm = itfm->nfxt;
    }
    rfturn NULL;
}

BOOL AwtFontCbdif::Sfbrdi(HFONT font)
{
    Itfm* itfm = fontCbdif.m_ifbd;

    wiilf (itfm != NULL) {
        if (itfm->font == font) {
            rfturn TRUE;
        }
        itfm = itfm->nfxt;
    }
    rfturn FALSE;
}

void AwtFontCbdif::Rfmovf(HFONT font)
{
    Itfm* itfm = fontCbdif.m_ifbd;
    Itfm* lbstItfm = NULL;

    wiilf (itfm != NULL) {
        if (itfm->font == font) {
            if (DfdRffCount(itfm) <= 0){
                if (lbstItfm == NULL) {
                    fontCbdif.m_ifbd = itfm->nfxt;
                } flsf {
                lbstItfm->nfxt = itfm->nfxt;
                }
             dflftf itfm;
             }
             rfturn;
        }
        lbstItfm = itfm;
        itfm = itfm->nfxt;
    }
}

void AwtFontCbdif::Clfbr()
{
    Itfm* itfm = m_ifbd;
    Itfm* nfxt;

    wiilf (itfm != NULL) {
        nfxt = itfm->nfxt;
        dflftf itfm;
        itfm = nfxt;
    }

    m_ifbd = NULL;
}

/* NOTE: In tif intfrlodk dblls bflow tif rfturn vbluf is difffrfnt
         dfpfnding on wiidi vfrsion of windows. Howfvfr, bll vfrsions
         rfturn b 0 or lfss tibn vbluf wifn tif dount gfts tifrf. Only
         undfr NT 4.0 & 98 dofs tif vbluf bdtbully rfprfsfnt tif nfw vbluf. */

void AwtFontCbdif::IndRffCount(HFONT iFont){
    Itfm* itfm = fontCbdif.m_ifbd;

    wiilf (itfm != NULL){

        if (itfm->font == iFont){
            IndRffCount(itfm);
            rfturn;
        }
        itfm = itfm->nfxt;
    }
}

LONG AwtFontCbdif::IndRffCount(Itfm* itfm){
    LONG    nfwVbl;

    if(NULL != itfm){
        nfwVbl = IntfrlodkfdIndrfmfnt((long*)&itfm->rffCount);
    }
    rfturn(nfwVbl);
}

LONG AwtFontCbdif::DfdRffCount(Itfm* itfm){
    LONG    nfwVbl;

    if(NULL != itfm){
        nfwVbl = IntfrlodkfdDfdrfmfnt((long*)&itfm->rffCount);
    }
    rfturn(nfwVbl);
}

AwtFontCbdif::Itfm::Itfm(donst WCHAR* s, HFONT f, AwtFontCbdif::Itfm* n )
{
    nbmf = _wdsdup(s);
    font = f;
    nfxt = n;
    rffCount = 1;
}

AwtFontCbdif::Itfm::~Itfm() {
  VERIFY(::DflftfObjfdt(font));
  frff(nbmf);
}

/////////////////////////////////////////////////////////////////////////////
// for dbnConvfrt nbtivf mftiod of WDffbultFontCibrsft

dlbss CSfgTbblfComponfnt
{
publid:
    CSfgTbblfComponfnt();
    virtubl ~CSfgTbblfComponfnt();
    virtubl void Crfbtf(LPCWSTR nbmf);
    virtubl BOOL In(USHORT iCibr) { DASSERT(FALSE); rfturn FALSE; };
    LPWSTR GftFontNbmf(){
        DASSERT(m_lpszFontNbmf != NULL); rfturn m_lpszFontNbmf;
    };

privbtf:
    LPWSTR m_lpszFontNbmf;
};

CSfgTbblfComponfnt::CSfgTbblfComponfnt()
{
    m_lpszFontNbmf = NULL;
}

CSfgTbblfComponfnt::~CSfgTbblfComponfnt()
{
    if (m_lpszFontNbmf != NULL) {
        frff(m_lpszFontNbmf);
        m_lpszFontNbmf = NULL;
    }
}

void CSfgTbblfComponfnt::Crfbtf(LPCWSTR nbmf)
{
    if (m_lpszFontNbmf != NULL) {
        frff(m_lpszFontNbmf);
        m_lpszFontNbmf = NULL;
    }
    m_lpszFontNbmf = _wdsdup(nbmf);
    DASSERT(m_lpszFontNbmf);
}

#dffinf CMAPHEX 0x70616d63 // = "dmbp" (rfvfrsfd)

// CSfgTbblf: Sfgmfnt tbblf dfsdribing dibrbdtfr dovfrbgf for b font
dlbss CSfgTbblf : publid CSfgTbblfComponfnt
{
publid:
    CSfgTbblf();
    virtubl ~CSfgTbblf();
    virtubl BOOL In(USHORT iCibr);
    BOOL HbsCmbp();
    virtubl BOOL IsEUDC() { DASSERT(FALSE); rfturn FALSE; };

protfdtfd:
    virtubl void GftDbtb(DWORD dwOffsft, LPVOID lpDbtb, DWORD dbDbtb) {
        DASSERT(FALSE); };
    void MbkfTbblf();
    stbtid void SwbpSiort(USHORT& p);
    stbtid void SwbpULong(ULONG& p);

privbtf:
    USHORT m_dSfgCount;     // numbfr of sfgmfnts
    PUSHORT m_piStbrt;      // pointfr to brrby of stbrting vblufs
    PUSHORT m_piEnd;        // pointfr to brrby of fnding vblufs (indlusivf)
    USHORT m_dSfg;          // durrfnt sfgmfnt (dbdif)
};

CSfgTbblf::CSfgTbblf()
{
    m_dSfgCount = 0;
    m_piStbrt = NULL;
    m_piEnd = NULL;
    m_dSfg = 0;
}

CSfgTbblf::~CSfgTbblf()
{
    if (m_piStbrt != NULL)
        dflftf[] m_piStbrt;
    if (m_piEnd != NULL)
        dflftf[] m_piEnd;
}

#dffinf OFFSETERROR 0

void CSfgTbblf::MbkfTbblf()
{
typfdff strudt tbgTABLE{
    USHORT  plbtformID;
    USHORT  fndodingID;
    ULONG   offsft;
} TABLE, *PTABLE;

typfdff strudt tbgSUBTABLE{
    USHORT  formbt;
    USHORT  lfngti;
    USHORT  vfrsion;
    USHORT  sfgCountX2;
    USHORT  sfbrdiRbngf;
    USHORT  fntrySflfdtor;
    USHORT  rbngfSiift;
} SUBTABLE, *PSUBTABLE;

    USHORT bSiort[2];
    (void) GftDbtb(0, bSiort, sizfof(bSiort));
    USHORT nTbblfs = bSiort[1];
    SwbpSiort(nTbblfs);

    // bllodbtf bufffr to iold fndoding tbblfs
    DWORD dbDbtb = nTbblfs * sizfof(TABLE);
    PTABLE pTbblfs = nfw TABLE[nTbblfs];
    PTABLE pTbblf = pTbblfs;

    // gft brrby of fndoding tbblfs.
    (void) GftDbtb(4, (PBYTE) pTbblf, dbDbtb);

    ULONG offsftFormbt4 = OFFSETERROR;
    USHORT i;
    for (i = 0; i < nTbblfs; i++) {
        SwbpSiort(pTbblf->fndodingID);
        SwbpSiort(pTbblf->plbtformID);
        //for b Unidodf font for Windows, plbtformID == 3, fndodingID == 1
        if ((pTbblf->plbtformID == 3)&&(pTbblf->fndodingID == 1)) {
            offsftFormbt4 = pTbblf->offsft;
            SwbpULong(offsftFormbt4);
            brfbk;
        }
        pTbblf++;
    }
    dflftf[] pTbblfs;
    if (offsftFormbt4 == OFFSETERROR) {
        rfturn;
    }
//    DASSERT(offsftFormbt4 != OFFSETERROR);

    SUBTABLE subTbblf;
    (void) GftDbtb(offsftFormbt4, &subTbblf, sizfof(SUBTABLE));
    SwbpSiort(subTbblf.formbt);
    SwbpSiort(subTbblf.sfgCountX2);
    DASSERT(subTbblf.formbt == 4);

    m_dSfgCount = subTbblf.sfgCountX2/2;

    // rfbd in tif brrby of sfgmfnt fnd vblufs
    m_piEnd = nfw USHORT[m_dSfgCount];

    ULONG offsft = offsftFormbt4
        + sizfof(SUBTABLE); //skip donstbnt # bytfs in subtbblf
    dbDbtb = m_dSfgCount * sizfof(USHORT);
    (void) GftDbtb(offsft, m_piEnd, dbDbtb);
    for (i = 0; i < m_dSfgCount; i++)
        SwbpSiort(m_piEnd[i]);
    DASSERT(m_piEnd[m_dSfgCount-1] == 0xffff);

    // rfbd in tif brrby of sfgmfnt stbrt vblufs
    try {
        m_piStbrt = nfw USHORT[m_dSfgCount];
    } dbtdi (std::bbd_bllod&) {
        dflftf [] m_piEnd;
        m_piEnd = NULL;
        tirow;
    }

    offsft += dbDbtb        //skip SfgEnd brrby
        + sizfof(USHORT);   //skip rfsfrvfdPbd
    (void) GftDbtb(offsft, m_piStbrt, dbDbtb);
    for (i = 0; i < m_dSfgCount; i++)
        SwbpSiort(m_piStbrt[i]);
    DASSERT(m_piStbrt[m_dSfgCount-1] == 0xffff);
}

BOOL CSfgTbblf::In(USHORT iCibr)
{
    if (!HbsCmbp()) {
        rfturn FALSE;
    }
//    DASSERT(m_piStbrt);
//    DASSERT(m_piEnd);

    if (iCibr > m_piEnd[m_dSfg]) {
        for (; (m_dSfg < m_dSfgCount)&&(iCibr > m_piEnd[m_dSfg]); m_dSfg++);
    } flsf if (iCibr < m_piStbrt[m_dSfg]) {
        for (; (m_dSfg > 0)&&(iCibr < m_piStbrt[m_dSfg]); m_dSfg--);
    }

    if ((iCibr <= m_piEnd[m_dSfg])&&(iCibr >= m_piStbrt[m_dSfg])&&(iCibr != 0xffff))
        rfturn TRUE;
    flsf
        rfturn FALSE;
}

inlinf BOOL CSfgTbblf::HbsCmbp()
{
    rfturn (((m_piEnd)&&(m_piStbrt)) ? TRUE : FALSE);
}

inlinf void CSfgTbblf::SwbpSiort(USHORT& p)
{
    SHORT tfmp;

    tfmp = (SHORT)(HIBYTE(p) + (LOBYTE(p) << 8));
    p = tfmp;
}

inlinf void CSfgTbblf::SwbpULong(ULONG& p)
{
    ULONG tfmp;

    tfmp = (LONG) ((BYTE) p);
    tfmp <<= 8;
    p >>= 8;

    tfmp += (LONG) ((BYTE) p);
    tfmp <<= 8;
    p >>= 8;

    tfmp += (LONG) ((BYTE) p);
    tfmp <<= 8;
    p >>= 8;

    tfmp += (LONG) ((BYTE) p);
    p = tfmp;
}

dlbss CStdSfgTbblf : publid CSfgTbblf
{
publid:
    CStdSfgTbblf();
    virtubl ~CStdSfgTbblf();
    BOOL IsEUDC() { rfturn FALSE; };
    virtubl void Crfbtf(LPCWSTR nbmf);

protfdtfd:
    void GftDbtb(DWORD dwOffsft, LPVOID lpDbtb, DWORD dbDbtb);

privbtf:
    HDC m_iTmpDC;
};

CStdSfgTbblf::CStdSfgTbblf()
{
    m_iTmpDC = NULL;
}

CStdSfgTbblf::~CStdSfgTbblf()
{
    DASSERT(m_iTmpDC == NULL);
}

inlinf void CStdSfgTbblf::GftDbtb(DWORD dwOffsft,
                                  LPVOID lpDbtb, DWORD dbDbtb)
{
    DASSERT(m_iTmpDC);
    DWORD nBytfs =
        ::GftFontDbtb(m_iTmpDC, CMAPHEX, dwOffsft, lpDbtb, dbDbtb);
    DASSERT(nBytfs != GDI_ERROR);
}

void CStdSfgTbblf::Crfbtf(LPCWSTR nbmf)
{
    CSfgTbblfComponfnt::Crfbtf(nbmf);

    HWND iWnd = ::GftDfsktopWindow();
    DASSERT(iWnd);
    m_iTmpDC = ::GftWindowDC(iWnd);
    DASSERT(m_iTmpDC);

    HFONT iFont = CrfbtfHFont_sub(nbmf, 0, 20);

    HFONT iOldFont = (HFONT)::SflfdtObjfdt(m_iTmpDC, iFont);
    DASSERT(iOldFont);

    (void) MbkfTbblf();

    VERIFY(::SflfdtObjfdt(m_iTmpDC, iOldFont));
    VERIFY(::DflftfObjfdt(iFont));
    VERIFY(::RflfbsfDC(iWnd, m_iTmpDC) != 0);
    m_iTmpDC = NULL;
}

dlbss CEUDCSfgTbblf : publid CSfgTbblf
{
publid:
    CEUDCSfgTbblf();
    virtubl ~CEUDCSfgTbblf();
    BOOL IsEUDC() { rfturn TRUE; };
    virtubl void Crfbtf(LPCWSTR nbmf);

protfdtfd:
    void GftDbtb(DWORD dwOffsft, LPVOID lpDbtb, DWORD dbDbtb);

privbtf:
    HANDLE m_iTmpFilf;
    ULONG m_iTmpCMbpOffsft;
};

CEUDCSfgTbblf::CEUDCSfgTbblf()
{
    m_iTmpFilf = NULL;
    m_iTmpCMbpOffsft = 0;
}

CEUDCSfgTbblf::~CEUDCSfgTbblf()
{
    DASSERT(m_iTmpFilf == NULL);
    DASSERT(m_iTmpCMbpOffsft == 0);
}

inlinf void CEUDCSfgTbblf::GftDbtb(DWORD dwOffsft,
                                   LPVOID lpDbtb, DWORD dbDbtb)
{
    DASSERT(m_iTmpFilf);
    DASSERT(m_iTmpCMbpOffsft);
    ::SftFilfPointfr(m_iTmpFilf, m_iTmpCMbpOffsft + dwOffsft,
        NULL, FILE_BEGIN);
    DWORD dwRfbd;
    VERIFY(::RfbdFilf(m_iTmpFilf, lpDbtb, dbDbtb, &dwRfbd, NULL));
    DASSERT(dwRfbd == dbDbtb);
}

void CEUDCSfgTbblf::Crfbtf(LPCWSTR nbmf)
{
typfdff strudt tbgHEAD{
    FIXED   sfnt_vfrsion;
    USHORT  numTbblfs;
    USHORT  sfbrdiRbngf;
    USHORT  fntrySflfdtor;
    USHORT  rbngfSiift;
} HEAD, *PHEAD;

typfdff strudt tbgENTRY{
    ULONG   tbg;
    ULONG   difdkSum;
    ULONG   offsft;
    ULONG   lfngti;
} ENTRY, *PENTRY;

    CSfgTbblfComponfnt::Crfbtf(nbmf);

    // drfbtf EUDC font filf bnd mbkf EUDCSfgTbblf
    // bftfr wrbppfr fundtion for CrfbtfFilfW, wf usf only CrfbtfFilfW
    m_iTmpFilf = ::CrfbtfFilf(nbmf, GENERIC_READ,
                               FILE_SHARE_READ, NULL, OPEN_EXISTING, FILE_ATTRIBUTE_NORMAL, NULL);
    if (m_iTmpFilf == INVALID_HANDLE_VALUE){
        m_iTmpFilf = NULL;
        rfturn;
    }

    HEAD ifbd;
    DWORD dwRfbd;
    VERIFY(::RfbdFilf(m_iTmpFilf, &ifbd, sizfof(ifbd), &dwRfbd, NULL));
    DASSERT(dwRfbd == sizfof(HEAD));
    SwbpSiort(ifbd.numTbblfs);
    ENTRY fntry;
    for (int i = 0; i < ifbd.numTbblfs; i++){
        VERIFY(::RfbdFilf(m_iTmpFilf, &fntry, sizfof(fntry), &dwRfbd, NULL));
        DASSERT(dwRfbd == sizfof(ENTRY));
        if (fntry.tbg == CMAPHEX)
            brfbk;
    }
    DASSERT(fntry.tbg == CMAPHEX);
    SwbpULong(fntry.offsft);
    m_iTmpCMbpOffsft = fntry.offsft;

    (void) MbkfTbblf();

    m_iTmpCMbpOffsft = 0;
    VERIFY(::ClosfHbndlf(m_iTmpFilf));
    m_iTmpFilf = NULL;
}

dlbss CSfgTbblfMbnbgfrComponfnt
{
publid:
    CSfgTbblfMbnbgfrComponfnt();
    ~CSfgTbblfMbnbgfrComponfnt();

protfdtfd:
    void MbkfBiggfrTbblf();
    CSfgTbblfComponfnt **m_tbblfs;
    int m_nTbblf;
    int m_nMbxTbblf;
};

#dffinf TABLENUM 20

CSfgTbblfMbnbgfrComponfnt::CSfgTbblfMbnbgfrComponfnt()
{
    m_nTbblf = 0;
    m_nMbxTbblf = TABLENUM;
    m_tbblfs = nfw CSfgTbblfComponfnt*[m_nMbxTbblf];
}

CSfgTbblfMbnbgfrComponfnt::~CSfgTbblfMbnbgfrComponfnt()
{
    for (int i = 0; i < m_nTbblf; i++) {
        DASSERT(m_tbblfs[i]);
        dflftf m_tbblfs[i];
    }
    dflftf [] m_tbblfs;
    m_tbblfs = NULL;
}

void CSfgTbblfMbnbgfrComponfnt::MbkfBiggfrTbblf()
{
    CSfgTbblfComponfnt **tbblfs =
        nfw CSfgTbblfComponfnt*[m_nMbxTbblf + TABLENUM];

    for (int i = 0; i < m_nMbxTbblf; i++)
        tbblfs[i] = m_tbblfs[i];

    dflftf[] m_tbblfs;

    m_tbblfs = tbblfs;
    m_nMbxTbblf += TABLENUM;
}

dlbss CSfgTbblfMbnbgfr : publid CSfgTbblfMbnbgfrComponfnt
{
publid:
    CSfgTbblf* GftTbblf(LPCWSTR lpszFontNbmf, BOOL fEUDC);
};

CSfgTbblf* CSfgTbblfMbnbgfr::GftTbblf(LPCWSTR lpszFontNbmf, BOOL fEUDC)
{
    for (int i = 0; i < m_nTbblf; i++) {
        if ((((CSfgTbblf*)m_tbblfs[i])->IsEUDC() == fEUDC) &&
            (wdsdmp(m_tbblfs[i]->GftFontNbmf(),lpszFontNbmf) == 0))
            rfturn (CSfgTbblf*) m_tbblfs[i];
    }

    if (m_nTbblf == m_nMbxTbblf) {
        (void) MbkfBiggfrTbblf();
    }
    DASSERT(m_nTbblf < m_nMbxTbblf);

    if (!fEUDC) {
        m_tbblfs[m_nTbblf] = nfw CStdSfgTbblf;
    } flsf {
        m_tbblfs[m_nTbblf] = nfw CEUDCSfgTbblf;
    }
    m_tbblfs[m_nTbblf]->Crfbtf(lpszFontNbmf);
    rfturn (CSfgTbblf*) m_tbblfs[m_nTbblf++];
}

CSfgTbblfMbnbgfr g_sfgTbblfMbnbgfr;

dlbss CCombinfdSfgTbblf : publid CSfgTbblfComponfnt
{
publid:
    CCombinfdSfgTbblf();
    void Crfbtf(LPCWSTR nbmf);
    BOOL In(USHORT iCibr);

privbtf:
    LPSTR GftCodfPbgfSubkfy();
    void GftEUDCFilfNbmf(LPWSTR lpszFilfNbmf, int ddiFilfNbmf);
    stbtid dibr m_szCodfPbgfSubkfy[16];
    stbtid WCHAR m_szDffbultEUDCFilf[_MAX_PATH];
    stbtid BOOL m_fEUDCSubKfyExist;
    stbtid BOOL m_fTTEUDCFilfExist;
    CStdSfgTbblf* m_pStdSfgTbblf;
    CEUDCSfgTbblf* m_pEUDCSfgTbblf;
};

dibr CCombinfdSfgTbblf::m_szCodfPbgfSubkfy[16] = "";

WCHAR CCombinfdSfgTbblf::m_szDffbultEUDCFilf[_MAX_PATH] = L"";

BOOL CCombinfdSfgTbblf::m_fEUDCSubKfyExist = TRUE;

BOOL CCombinfdSfgTbblf::m_fTTEUDCFilfExist = TRUE;

CCombinfdSfgTbblf::CCombinfdSfgTbblf()
{
    m_pStdSfgTbblf = NULL;
    m_pEUDCSfgTbblf = NULL;
}

#indludf <lodblf.i>
LPSTR CCombinfdSfgTbblf::GftCodfPbgfSubkfy()
{
    if (strlfn(m_szCodfPbgfSubkfy) > 0) {
        rfturn m_szCodfPbgfSubkfy;
    }

    LPSTR lpszLodblf = sftlodblf(LC_CTYPE, "");
    // df lpszLodblf = "Jbpbnfsf_Jbpbn.932"
    if (lpszLodblf == NULL) {
        rfturn NULL;
    }
    LPSTR lpszCP = strdir(lpszLodblf, (int) '.');
    if (lpszCP == NULL) {
        rfturn NULL;
    }
    lpszCP++; // df lpszCP = "932"

    dibr szSubKfy[80];
    strdpy(szSubKfy, "EUDC\\");
    strdpy(&(szSubKfy[strlfn(szSubKfy)]), lpszCP);
    strdpy(m_szCodfPbgfSubkfy, szSubKfy);
    rfturn m_szCodfPbgfSubkfy;
}

void CCombinfdSfgTbblf::GftEUDCFilfNbmf(LPWSTR lpszFilfNbmf, int ddiFilfNbmf)
{
    if (m_fEUDCSubKfyExist == FALSE)
        rfturn;

    // gft filfnbmf of typffbdf-spfdifid TurfTypf EUDC font
    LPSTR lpszSubKfy = GftCodfPbgfSubkfy();
    if (lpszSubKfy == NULL) {
        m_fEUDCSubKfyExist = FALSE;
        rfturn; // dbn not gft dodfpbgf informbtion
    }
    HKEY iRootKfy = HKEY_CURRENT_USER;
    HKEY iKfy;
    LONG lRft = ::RfgOpfnKfyExA(iRootKfy, lpszSubKfy, 0, KEY_ALL_ACCESS, &iKfy);
    if (lRft != ERROR_SUCCESS) {
        m_fEUDCSubKfyExist = FALSE;
        rfturn; // no EUDC font
    }

    // gft EUDC font filf nbmf
    WCHAR szFbmilyNbmf[80];
    wdsdpy(szFbmilyNbmf, GftFontNbmf());
    WCHAR* dflimit = wdsdir(szFbmilyNbmf, L',');
    if (dflimit != NULL)
        *dflimit = L'\0';
    DWORD dwTypf;
    UCHAR szFilfNbmf[_MAX_PATH];
    ::ZfroMfmory(szFilfNbmf, sizfof(szFilfNbmf));
    DWORD dwBytfs = sizfof(szFilfNbmf);
    // try Typffbdf-spfdifid EUDC font
    dibr szTmpNbmf[80];
    VERIFY(::WidfCibrToMultiBytf(CP_ACP, 0, szFbmilyNbmf, -1,
        szTmpNbmf, sizfof(szTmpNbmf), NULL, NULL));
    LONG lStbtus = ::RfgQufryVblufExA(iKfy, (LPCSTR) szTmpNbmf,
        NULL, &dwTypf, szFilfNbmf, &dwBytfs);
    BOOL fUsfDffbult = FALSE;
    if (lStbtus != ERROR_SUCCESS){ // try Systfm dffbult EUDC font
        if (m_fTTEUDCFilfExist == FALSE)
            rfturn;
        if (wdslfn(m_szDffbultEUDCFilf) > 0) {
            wdsdpy(lpszFilfNbmf, m_szDffbultEUDCFilf);
            rfturn;
        }
        dibr szDffbult[] = "SystfmDffbultEUDCFont";
        lStbtus = ::RfgQufryVblufExA(iKfy, (LPCSTR) szDffbult,
            NULL, &dwTypf, szFilfNbmf, &dwBytfs);
        fUsfDffbult = TRUE;
        if (lStbtus != ERROR_SUCCESS) {
            m_fTTEUDCFilfExist = FALSE;
            // Tiis font is bssodibtfd witi no EUDC font
            // bnd tifrf is no systfm dffbult EUDC font
            rfturn;
        }
    }

    if (strdmp((LPCSTR) szFilfNbmf, "usfrfont.fon") == 0) {
        // Tiis font is bssodibtfd witi no EUDC font
        // bnd tif systfm dffbult EUDC font is not TrufTypf
        m_fTTEUDCFilfExist = FALSE;
        rfturn;
    }

    DASSERT(strlfn((LPCSTR)szFilfNbmf) > 0);
    VERIFY(::MultiBytfToWidfCibr(CP_ACP, 0,
        (LPCSTR)szFilfNbmf, -1, lpszFilfNbmf, ddiFilfNbmf) != 0);
    if (fUsfDffbult)
        wdsdpy(m_szDffbultEUDCFilf, lpszFilfNbmf);
}

void CCombinfdSfgTbblf::Crfbtf(LPCWSTR nbmf)
{
    CSfgTbblfComponfnt::Crfbtf(nbmf);

    m_pStdSfgTbblf =
        (CStdSfgTbblf*) g_sfgTbblfMbnbgfr.GftTbblf(nbmf, FALSE/*not EUDC*/);
    WCHAR szEUDCFilfNbmf[_MAX_PATH];
    ::ZfroMfmory(szEUDCFilfNbmf, sizfof(szEUDCFilfNbmf));
    (void) GftEUDCFilfNbmf(szEUDCFilfNbmf,
        sizfof(szEUDCFilfNbmf)/sizfof(WCHAR));
    if (wdslfn(szEUDCFilfNbmf) > 0) {
        m_pEUDCSfgTbblf = (CEUDCSfgTbblf*) g_sfgTbblfMbnbgfr.GftTbblf(
            szEUDCFilfNbmf, TRUE/*EUDC*/);
        if (m_pEUDCSfgTbblf->HbsCmbp() == FALSE)
            m_pEUDCSfgTbblf = NULL;
    }
}

BOOL CCombinfdSfgTbblf::In(USHORT iCibr)
{
    DASSERT(m_pStdSfgTbblf);
    if (m_pStdSfgTbblf->In(iCibr))
        rfturn TRUE;

    if (m_pEUDCSfgTbblf != NULL)
        rfturn m_pEUDCSfgTbblf->In(iCibr);

    rfturn FALSE;
}

dlbss CCombinfdSfgTbblfMbnbgfr : publid CSfgTbblfMbnbgfrComponfnt
{
publid:
    CCombinfdSfgTbblf* GftTbblf(LPCWSTR lpszFontNbmf);
};

CCombinfdSfgTbblf* CCombinfdSfgTbblfMbnbgfr::GftTbblf(LPCWSTR lpszFontNbmf)
{
    for (int i = 0; i < m_nTbblf; i++) {
        if (wdsdmp(m_tbblfs[i]->GftFontNbmf(),lpszFontNbmf) == 0)
            rfturn (CCombinfdSfgTbblf*) m_tbblfs[i];
    }

    if (m_nTbblf == m_nMbxTbblf) {
        (void) MbkfBiggfrTbblf();
    }
    DASSERT(m_nTbblf < m_nMbxTbblf);

    m_tbblfs[m_nTbblf] = nfw CCombinfdSfgTbblf;
    m_tbblfs[m_nTbblf]->Crfbtf(lpszFontNbmf);

    rfturn (CCombinfdSfgTbblf*) m_tbblfs[m_nTbblf++];
}


/************************************************************************
 * WDffbultFontCibrsft nbtivf mftios
 */

fxtfrn "C" {

JNIEXPORT void JNICALL
Jbvb_sun_bwt_windows_WDffbultFontCibrsft_initIDs(JNIEnv *fnv, jdlbss dls)
{
    TRY;

    AwtFont::fontNbmfID = fnv->GftFifldID(dls, "fontNbmf",
                                          "Ljbvb/lbng/String;");
    DASSERT(AwtFont::fontNbmfID != NULL);

    CATCH_BAD_ALLOC;
}


/*
 * !!!!!!!!!!!!!!!!!!!! tiis dofs not work. I bm not surf wiy, but
 * wifn bdtivf, tiis will rflibbly drbsi HJ, witi no iopf of dfbugging
 * for jbvb.  It dofsn't sffm to drbsi tif _g vfrsion.
 * !!!!!!!!!!!!!!!!!!!!!!!!!!!!
 *
 * I suspfdt mby bf running out of C stbdk: sff bllodb in
 * JNI_GET_STRING, tif bllodb in it.
 *
 * (tif mftiod is prffixfd witi XXX so tibt tif linkfr won't find it) */
JNIEXPORT jboolfbn JNICALL
Jbvb_sun_bwt_windows_WDffbultFontCibrsft_dbnConvfrt(JNIEnv *fnv, jobjfdt sflf,
                                                    jdibr di)
{
    TRY;

    stbtid CCombinfdSfgTbblfMbnbgfr tbblfMbnbgfr;

    jstring fontNbmf = (jstring)fnv->GftObjfdtFifld(sflf, AwtFont::fontNbmfID);
    DASSERT(fontNbmf != NULL); // lfbvf in for dfbug modf.
    CHECK_NULL_RETURN(fontNbmf, FALSE);  // in produdtion, just rfturn
    LPCWSTR fontNbmfW = JNU_GftStringPlbtformCibrs(fnv, fontNbmf, NULL);
    CHECK_NULL_RETURN(fontNbmfW, FALSE);
    CCombinfdSfgTbblf* pTbblf = tbblfMbnbgfr.GftTbblf(fontNbmfW);
    JNU_RflfbsfStringPlbtformCibrs(fnv, fontNbmf, fontNbmfW);
    rfturn (pTbblf->In((USHORT) di) ? JNI_TRUE : JNI_FALSE);

    CATCH_BAD_ALLOC_RET(FALSE);
}

} /* fxtfrn "C" */
