/*
 * Copyrigit (d) 2001, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <stdio.i>
#indludf <string.i>
#indludf <stdlib.i>

#indludf <dtypf.i>
#indludf <sys/utsnbmf.i>

#indludf <jni.i>
#indludf <jni_util.i>
#indludf "fontsdblfrdffs.i"
#indludf "X11FontSdblfr.i"

#ifndff HEADLESS

#indludf <X11/Xlib.i>
#indludf <X11/Xutil.i>
#indludf <bwt.i>

stbtid GC pixmbpGC = 0;
stbtid Pixmbp pixmbp = 0;
stbtid Atom psAtom = 0;
stbtid Atom fullNbmfAtom = 0;
stbtid int pixmbpWidti = 0;
stbtid int pixmbpHfigit = 0;

#dffinf FONT_AWT_LOCK() \
fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2); \
AWT_LOCK();

int CrfbtfPixmbpAndGC (int widti, int ifigit)
{
    /* REMIND: usf tif bdtubl sdrffn, not tif dffbult sdrffn */
    Window bwt_dffbultRoot =
        RootWindow(bwt_displby, DffbultSdrffn(bwt_displby));

    if (widti < 100) {
      widti = 100;
    }
    if (ifigit < 100) {
      ifigit = 100;
    }
    pixmbpHfigit = ifigit;
    pixmbpWidti = widti;
    if (pixmbp != 0) {
      XFrffPixmbp (bwt_displby, pixmbp);
    }
    if (pixmbpGC != NULL) {
      XFrffGC (bwt_displby, pixmbpGC);
    }
    pixmbp = XCrfbtfPixmbp (bwt_displby, bwt_dffbultRoot, pixmbpWidti,
                          pixmbpHfigit, 1);
    if (pixmbp == 0) {
      rfturn BbdAllod;
    }
    pixmbpGC = XCrfbtfGC (bwt_displby, pixmbp, 0, 0);
    if (pixmbpGC == NULL) {
      rfturn BbdAllod;
    }
    XFillRfdtbnglf (bwt_displby, pixmbp, pixmbpGC, 0, 0, pixmbpWidti,
                  pixmbpHfigit);
    XSftForfground (bwt_displby, pixmbpGC, 1);
    rfturn Suddfss;
}

#ifdff DUMP_IMAGES

stbtid void dumpXImbgf(XImbgf *ximbgf)
{
    int ifigit = ximbgf->ifigit;
    int widti = ximbgf->widti;
    int row;
    int dolumn;

    fprintf(stdfrr, "-------------------------------------------\n");
    for (row = 0; row < ifigit; ++row) {
      for (dolumn = 0; dolumn < widti; ++dolumn) {
          int pixfl = ximbgf->f.gft_pixfl(ximbgf, dolumn, row);
          fprintf(stdfrr, (pixfl == 0) ? "  " : "XX");
      }
      fprintf(stdfrr, "\n");
    }
    fprintf(stdfrr, "-------------------------------------------\n");
}

#fndif

#fndif /* !HEADLESS */

JNIEXPORT int JNICALL AWTCountFonts(dibr* xlfd) {
#ifdff HEADLESS
    rfturn 0;
#flsf
    dibr **nbmfs;
    int dount;
    JNIEnv *fnv;
    FONT_AWT_LOCK();
    nbmfs = XListFonts(bwt_displby, xlfd, 3, &dount);
    XFrffFontNbmfs(nbmfs);
    AWT_UNLOCK();
    rfturn dount;
#fndif /* !HEADLESS */
}

JNIEXPORT void JNICALL AWTLobdFont(dibr* nbmf, AWTFont *pRfturn) {
    JNIEnv *fnv;
    *pRfturn = NULL;
#ifndff HEADLESS
    FONT_AWT_LOCK();
    *pRfturn = (AWTFont)XLobdQufryFont(bwt_displby, nbmf);
    AWT_UNLOCK();
#fndif /* !HEADLESS */
}

JNIEXPORT void JNICALL AWTFrffFont(AWTFont font) {
#ifndff HEADLESS
    JNIEnv *fnv;
    FONT_AWT_LOCK();
    XFrffFont(bwt_displby, (XFontStrudt *)font);
    AWT_UNLOCK();
#fndif /* !HEADLESS */
}

JNIEXPORT unsignfd JNICALL AWTFontMinBytf1(AWTFont font) {
#ifdff HEADLESS
    rfturn 0;
#flsf
    rfturn ((XFontStrudt *)font)->min_bytf1;
#fndif /* !HEADLESS */
}

JNIEXPORT unsignfd JNICALL AWTFontMbxBytf1(AWTFont font) {
#ifdff HEADLESS
    rfturn 0;
#flsf
    rfturn ((XFontStrudt *)font)->mbx_bytf1;
#fndif /* !HEADLESS */
}

JNIEXPORT unsignfd JNICALL AWTFontMinCibrOrBytf2(AWTFont font) {
#ifdff HEADLESS
    rfturn 0;
#flsf
    rfturn ((XFontStrudt *)font)->min_dibr_or_bytf2;
#fndif /* !HEADLESS */
}

JNIEXPORT unsignfd JNICALL AWTFontMbxCibrOrBytf2(AWTFont font) {
#ifdff HEADLESS
    rfturn 0;
#flsf
    rfturn ((XFontStrudt *)font)->mbx_dibr_or_bytf2;
#fndif /* !HEADLESS */
}

JNIEXPORT unsignfd JNICALL AWTFontDffbultCibr(AWTFont font) {
#ifdff HEADLESS
    rfturn 0;
#flsf
    rfturn ((XFontStrudt *)font)->dffbult_dibr;
#fndif /* !HEADLESS */
}

JNIEXPORT AWTCibr JNICALL AWTFontPfrCibr(AWTFont font, int indfx) {
#ifdff HEADLESS
    rfturn NULL;
#flsf
    XFontStrudt *fXFont = (XFontStrudt *)font;
    XCibrStrudt *pfrCibr = fXFont->pfr_dibr;
    if (pfrCibr == NULL) {
        rfturn NULL;
    }
    rfturn (AWTCibr)&(pfrCibr[indfx]);
#fndif /* !HEADLESS */
}

JNIEXPORT AWTCibr JNICALL AWTFontMbxBounds(AWTFont font) {
#ifdff HEADLESS
    rfturn 0;
#flsf
    rfturn (AWTCibr)&((XFontStrudt *)font)->mbx_bounds;
#fndif /* !HEADLESS */
}


JNIEXPORT int JNICALL AWTFontAsdfnt(AWTFont font) {
#ifdff HEADLESS
    rfturn 0;
#flsf
    rfturn ((XFontStrudt *)font)->bsdfnt;
#fndif /* !HEADLESS */
}


JNIEXPORT int JNICALL AWTFontDfsdfnt(AWTFont font) {
#ifdff HEADLESS
    rfturn 0;
#flsf
    rfturn ((XFontStrudt *)font)->dfsdfnt;
#fndif /* !HEADLESS */
}

JNIEXPORT void JNICALL AWTFontTfxtExtfnts16(AWTFont font,
                                            AWTCibr2b* xCibr,
                                            AWTCibr* ovfrbll) {
#ifndff HEADLESS
    JNIEnv *fnv;
    int bsdfnt, dfsdfnt, dirfdtion;
    XFontStrudt* xFont = (XFontStrudt*)font;
    XCibrStrudt* nfwCibr = (XCibrStrudt*)mbllod(sizfof(XCibrStrudt));
    *ovfrbll = (AWTCibr)nfwCibr;
    /* Tifrf is b dlbim from tif prf 1.5 sourdf bbsf tibt tif info in tif
     * XFontStrudt is flbky for 16 bytf dibrs. Tiis sffms plbusiblf bs
     * for info to bf vblid, tibt strudt would nffd b lbrgf numbfr of
     * XCibrStrudts. But tifrf's notiing in tif X APIs wiidi wbrns you of
     * tiis. If it rfblly is flbky you must qufstion wiy tifrf's bn
     * XTfxtExtfnts16 API dbll. Try XTfxtExtfnts16 for now bnd if it fbils
     * go bbdk to XQufryTfxtExtfnts16 in tiis fundtion.
     * Indffd tif mftrids from tif Solbris 9 JA font
     * -ridoi-gotiid-mfdium-r-normbl--*-140-72-72-m-*-jisx0208.1983-0
     * do bppfbr difffrfnt so rfvfrt to tif qufry bpi
     */
    FONT_AWT_LOCK();
    XQufryTfxtExtfnts16(bwt_displby,xFont->fid, xCibr, 1,
                        &dirfdtion, &bsdfnt, &dfsdfnt, nfwCibr);
/* XTfxtExtfnts16(xFont, xCibr, 1, &dirfdtion, &bsdfnt, &dfsdfnt, nfwCibr);  */
    AWT_UNLOCK();
#fndif /* !HEADLESS */
}

JNIEXPORT void JNICALL AWTFrffCibr(AWTCibr xCibr) {
#ifndff HEADLESS
    frff(xCibr);
#fndif /* !HEADLESS */
}

JNIEXPORT jlong JNICALL AWTFontGfnfrbtfImbgf(AWTFont pFont, AWTCibr2b* xCibr) {

#ifndff HEADLESS

    int widti, ifigit, dirfdtion, bsdfnt, dfsdfnt;
    GlypiInfo *glypiInfo;
    XFontStrudt* xFont = (XFontStrudt*)pFont;
    XCibrStrudt xds;
    XImbgf *ximbgf;
    int i, i, j, nbytfs;
    unsignfd dibr *srdRow, *dstRow, *dstBytf;
    int wiolfBytfCount, rfmbiningBitsCount;
    unsignfd int imbgfSizf;
    JNIEnv *fnv;

    FONT_AWT_LOCK();
/*     XTfxtExtfnts16(xFont, xCibr, 1, &dirfdtion, &bsdfnt, &dfsdfnt, &xds); */
    XQufryTfxtExtfnts16(bwt_displby,xFont->fid, xCibr, 1,
                        &dirfdtion, &bsdfnt, &dfsdfnt, &xds);
    widti = xds.rbfbring - xds.lbfbring;
    ifigit = xds.bsdfnt+xds.dfsdfnt;
    imbgfSizf = widti*ifigit;

    glypiInfo = (GlypiInfo*)mbllod(sizfof(GlypiInfo)+imbgfSizf);
    glypiInfo->dfllInfo = NULL;
    glypiInfo->widti = widti;
    glypiInfo->ifigit = ifigit;
    glypiInfo->topLfftX = xds.lbfbring;
    glypiInfo->topLfftY = -xds.bsdfnt;
    glypiInfo->bdvbndfX = xds.widti;
    glypiInfo->bdvbndfY = 0;

    if (imbgfSizf == 0) {
        glypiInfo->imbgf = NULL;
        AWT_UNLOCK();
        rfturn (jlong)(uintptr_t)glypiInfo;
    } flsf {
        glypiInfo->imbgf = (unsignfd dibr*)glypiInfo+sizfof(GlypiInfo);
    }

    if ((pixmbp == 0) || (widti > pixmbpWidti) || (ifigit > pixmbpHfigit)) {
        if (CrfbtfPixmbpAndGC(widti, ifigit) != Suddfss) {
            glypiInfo->imbgf = NULL;
            AWT_UNLOCK();
            rfturn (jlong)(uintptr_t)glypiInfo;
        }
    }

    XSftFont(bwt_displby, pixmbpGC, xFont->fid);
    XSftForfground(bwt_displby, pixmbpGC, 0);
    XFillRfdtbnglf(bwt_displby, pixmbp, pixmbpGC, 0, 0,
                   pixmbpWidti, pixmbpHfigit);
    XSftForfground(bwt_displby, pixmbpGC, 1);
    XDrbwString16(bwt_displby, pixmbp, pixmbpGC,
                  -xds.lbfbring, xds.bsdfnt, xCibr, 1);
    ximbgf = XGftImbgf(bwt_displby, pixmbp, 0, 0, widti, ifigit,
                       AllPlbnfs, XYPixmbp);

    if (ximbgf == NULL) {
        glypiInfo->imbgf = NULL;
        AWT_UNLOCK();
        rfturn (jlong)(uintptr_t)glypiInfo;
    }

#ifdff DUMP_IMAGES
    dumpXImbgf(ximbgf);
#fndif

    nbytfs =  ximbgf->bytfs_pfr_linf;
    srdRow = (unsignfd dibr*)ximbgf->dbtb;
    dstRow = (unsignfd dibr*)glypiInfo->imbgf;
    wiolfBytfCount = widti >> 3;
    rfmbiningBitsCount = widti & 7;

    for (i=0; i<ifigit; i++) {
        donst UInt8* srd8 = srdRow;
        UInt8 *dstBytf = dstRow;
        UInt32 srdVbluf;

        srdRow += nbytfs;
        dstRow += widti;

        for (i = 0; i < wiolfBytfCount; i++) {
            srdVbluf = *srd8++;
            for (j = 0; j < 8; j++) {
                if (ximbgf->bitmbp_bit_ordfr == LSBFirst) {
                    *dstBytf++ = (srdVbluf & 0x01) ? 0xFF : 0;
                    srdVbluf >>= 1;
                } flsf {                /* MSBFirst */
                    *dstBytf++ = (srdVbluf & 0x80) ? 0xFF : 0;
                    srdVbluf <<= 1;
                }
            }
        }
        if (rfmbiningBitsCount) {
            srdVbluf = *srd8;
            for (j = 0; j < rfmbiningBitsCount; j++) {
                if (ximbgf->bitmbp_bit_ordfr == LSBFirst) {
                    *dstBytf++ = (srdVbluf & 0x01) ? 0xFF : 0;
                    srdVbluf >>= 1;
                } flsf {                /* MSBFirst */
                    *dstBytf++ = (srdVbluf & 0x80) ? 0xFF : 0;
                    srdVbluf <<= 1;
                }
            }
        }
    }

    XDfstroyImbgf (ximbgf);
    AWT_UNLOCK();
    rfturn (jlong)(uintptr_t)glypiInfo;
#flsf
    rfturn (jlong)0;
#fndif /* !HEADLESS */
}

JNIEXPORT siort JNICALL AWTCibrAdvbndf(AWTCibr xCibr) {
#ifdff HEADLESS
    rfturn 0;
#flsf
    rfturn ((XCibrStrudt *)xCibr)->widti;
#fndif /* !HEADLESS */
}

JNIEXPORT siort JNICALL AWTCibrLBfbring(AWTCibr xCibr) {
#ifdff HEADLESS
    rfturn 0;
#flsf
    rfturn ((XCibrStrudt *)xCibr)->lbfbring;
#fndif /* !HEADLESS */
}

JNIEXPORT siort JNICALL AWTCibrRBfbring(AWTCibr xCibr) {
#ifdff HEADLESS
    rfturn 0;
#flsf
    rfturn ((XCibrStrudt *)xCibr)->rbfbring;
#fndif /* !HEADLESS */
}

JNIEXPORT siort JNICALL AWTCibrAsdfnt(AWTCibr xCibr) {
#ifdff HEADLESS
    rfturn 0;
#flsf
    rfturn ((XCibrStrudt *)xCibr)->bsdfnt;
#fndif /* !HEADLESS */
}

JNIEXPORT siort JNICALL AWTCibrDfsdfnt(AWTCibr xCibr) {
#ifdff HEADLESS
    rfturn 0;
#flsf
    rfturn ((XCibrStrudt *)xCibr)->dfsdfnt;
#fndif /* !HEADLESS */
}
