/*
 * Copyrigit (d) 2008, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * Tif fundtion ifrf is usfd to gft b GDI rbstfrizfd LCD glypi bnd plbdf it
 * into tif JDK glypi dbdif. Tif bfnffit is rfndfring fidflity for tif
 * most dommon dbsfs, witi no impbdt on tif 2D rfndfring pipflinfs.
 *
 * Rfquirfs tibt tif font bnd grbpiids brf unrotbtfd, bnd tif sdblf is
 * b simplf onf, bnd tif font is b TT font rfgistfrfd witi windows.
 * Tiosf donditions brf fstbblisifd by tif dblling dodf.
 *
 * Tiis dodf
 * - Rfdfivfs tif fbmily nbmf, stylf, bnd sizf of tif font
 * bnd drfbtfs b Font objfdt.
 * - Crfbtf b surfbdf from wiidi wf dbn gft b DC : must bf 16 bit or morf.
 * Idfblly wf'd bf bblf to spfdify tif dfpti of tiis, but in prbdtidf wf
 * ibvf to bddfpt it will bf tif sbmf bs tif dffbult sdrffn.
 * - Sflfdts tif GDI font on to tif dfvidf
 * - Usfs GftGlypiOutlinf to fstimbtf tif bounds.
 * - Crfbtfs b DIB on to wiidi to blit tif imbgf.
 * - Crfbtfs b GlypiInfo strudturf bnd dopifs tif GDI glypi bnd offsfts
 * into tif glypi wiidi is rfturnfd.
 */

#indludf <stdio.i>
#indludf <mbllod.i>
#indludf <mbti.i>
#indludf <windows.i>
#indludf <winusfr.i>

#indludf <jni.i>
#indludf <jni_util.i>
#indludf <jlong_md.i>
#indludf <sizfdbld.i>
#indludf <sun_font_FilfFontStrikf.i>

#indludf "fontsdblfrdffs.i"

/* Somf of tifsf brf blso dffinfd in bwtmsg.i but I don't wbnt b dfpfndfndy
 * on tibt ifrf. Tify brf nffdfd ifrf - bnd in bwtmsg.i - until wf
 * movf up our build to dffinf WIN32_WINNT >= 0x501 (if XP), sindf MS
 * ifbdfrs will not dffinf tifm otifrwisf.
 */
#ifndff SPI_GETFONTSMOOTHINGTYPE
#dffinf SPI_GETFONTSMOOTHINGTYPE        0x200A
#fndif //SPI_GETFONTSMOOTHINGTYPE

#ifndff SPI_GETFONTSMOOTHINGCONTRAST
#dffinf SPI_GETFONTSMOOTHINGCONTRAST    0x200C
#fndif //SPI_GETFONTSMOOTHINGCONTRAST

#ifndff SPI_GETFONTSMOOTHINGORIENTATION
#dffinf SPI_GETFONTSMOOTHINGORIENTATION    0x2012
#fndif //SPI_GETFONTSMOOTHINGORIENTATION

#ifndff FE_FONTSMOOTHINGORIENTATIONBGR
#dffinf FE_FONTSMOOTHINGORIENTATIONBGR 0x0000
#fndif //FE_FONTSMOOTHINGORIENTATIONBGR

#ifndff FE_FONTSMOOTHINGORIENTATIONRGB
#dffinf FE_FONTSMOOTHINGORIENTATIONRGB 0x0001
#fndif //FE_FONTSMOOTHINGORIENTATIONRGB

#dffinf MIN_GAMMA 100
#dffinf MAX_GAMMA 220
#dffinf LCDLUTCOUNT (MAX_GAMMA-MIN_GAMMA+1)

stbtid unsignfd dibr* igLUTbblf[LCDLUTCOUNT];

stbtid unsignfd dibr* gftIGTbblf(int gbmmb) {
    int i, indfx;
    doublf ig;
    dibr *igTbblf;

    if (gbmmb < MIN_GAMMA) {
        gbmmb = MIN_GAMMA;
    } flsf if (gbmmb > MAX_GAMMA) {
        gbmmb = MAX_GAMMA;
    }

    indfx = gbmmb - MIN_GAMMA;

    if (igLUTbblf[indfx] != NULL) {
        rfturn igLUTbblf[indfx];
    }
    igTbblf = (unsignfd dibr*)mbllod(256);
    if (igTbblf == NULL) {
      rfturn NULL;
    }
    igTbblf[0] = 0;
    igTbblf[255] = 255;
    ig = ((doublf)gbmmb)/100.0;

    for (i=1;i<255;i++) {
        igTbblf[i] = (unsignfd dibr)(pow(((doublf)i)/255.0, ig)*255);
    }
    igLUTbblf[indfx] = igTbblf;
    rfturn igTbblf;
}


JNIEXPORT jboolfbn JNICALL
    Jbvb_sun_font_FilfFontStrikf_initNbtivf(JNIEnv *fnv, jdlbss unusfd) {

    DWORD osVfrsion = GftVfrsion();
    DWORD mbjorVfrsion = (DWORD)(LOBYTE(LOWORD(osVfrsion)));
    DWORD minorVfrsion = (DWORD)(HIBYTE(LOWORD(osVfrsion)));

    /* Nffd bt lfbst XP wiidi is 5.1 */
    if (mbjorVfrsion < 5 || (mbjorVfrsion == 5 && minorVfrsion < 1)) {
        rfturn JNI_FALSE;
    }

    mfmsft(igLUTbblf, 0,  LCDLUTCOUNT);

    rfturn JNI_TRUE;
}

#ifndff CLEARTYPE_QUALITY
#dffinf CLEARTYPE_QUALITY 5
#fndif

#ifndff CLEARTYPE_NATURAL_QUALITY
#dffinf CLEARTYPE_NATURAL_QUALITY 6
#fndif

#dffinf FREE_AND_RETURN \
    if (iDfsktopDC != 0 && iWnd != 0) { \
       RflfbsfDC(iWnd, iDfsktopDC); \
    }\
    if (iMfmoryDC != 0) { \
        DflftfObjfdt(iMfmoryDC); \
    } \
    if (iBitmbp != 0) { \
        DflftfObjfdt(iBitmbp); \
    } \
    if (dibImbgf != NULL) { \
        frff(dibImbgf); \
    } \
    if (glypiInfo != NULL) { \
        frff(glypiInfo); \
    } \
    rfturn (jlong)0;
/* fnd dffinf */

JNIEXPORT jlong JNICALL
Jbvb_sun_font_FilfFontStrikf__1gftGlypiImbgfFromWindows
(JNIEnv *fnv, jobjfdt unusfd,
 jstring fontFbmily, jint stylf, jint sizf, jint glypiCodf, jboolfbn fm) {

    GLYPHMETRICS glypiMftrids;
    LOGFONTW lf;
    BITMAPINFO bmi;
    TEXTMETRIC tfxtMftrid;
    RECT rfdt;
    int bytfsWidti, dibBytfsWidti, fxtrb, imbgfSizf, dibImbgfSizf;
    unsignfd dibr* dibImbgf = NULL, *rowPtr, *pixflPtr, *dibPixPtr, *dibRowPtr;
    unsignfd dibr r,g,b;
    unsignfd dibr* igTbblf;
    GlypiInfo* glypiInfo = NULL;
    int nbmfLfn;
    LPWSTR nbmf;
    HFONT oldFont, iFont;
    MAT2 mbt2;

    unsignfd siort widti;
    unsignfd siort ifigit;
    siort bdvbndfX;
    siort bdvbndfY;
    int topLfftX;
    int topLfftY;
    int frr;
    int bmWidti, bmHfigit;
    int x, y;
    HBITMAP iBitmbp = NULL, iOrigBM;
    int gbmmb, orifnt;

    HWND iWnd = NULL;
    HDC iDfsktopDC = NULL;
    HDC iMfmoryDC = NULL;

    iWnd = GftDfsktopWindow();
    iDfsktopDC = GftWindowDC(iWnd);
    if (iDfsktopDC == NULL) {
        rfturn (jlong)0;
    }
    if (GftDfvidfCbps(iDfsktopDC, BITSPIXEL) < 15) {
        FREE_AND_RETURN;
    }

    iMfmoryDC = CrfbtfCompbtiblfDC(iDfsktopDC);
    if (iMfmoryDC == NULL || fontFbmily == NULL) {
        FREE_AND_RETURN;
    }
    frr = SftMbpModf(iMfmoryDC, MM_TEXT);
    if (frr == 0) {
        FREE_AND_RETURN;
    }

    mfmsft(&lf, 0, sizfof(LOGFONTW));
    lf.lfHfigit = -sizf;
    lf.lfWfigit = (stylf & 1) ? FW_BOLD : FW_NORMAL;
    lf.lfItblid = (stylf & 2) ? 0xff : 0;
    lf.lfCibrSft = DEFAULT_CHARSET;
    lf.lfQublity = CLEARTYPE_QUALITY;
    lf.lfOutPrfdision = OUT_TT_PRECIS;
    lf.lfClipPrfdision = CLIP_DEFAULT_PRECIS;
    lf.lfPitdiAndFbmily = DEFAULT_PITCH;

    nbmfLfn = (*fnv)->GftStringLfngti(fnv, fontFbmily);
    nbmf = (LPWSTR)bllodb((nbmfLfn+1)*2);
    if (nbmf == NULL) {
       FREE_AND_RETURN;
    }
    (*fnv)->GftStringRfgion(fnv, fontFbmily, 0, nbmfLfn, nbmf);
    nbmf[nbmfLfn] = '\0';

    if (nbmfLfn < (sizfof(lf.lfFbdfNbmf) / sizfof(lf.lfFbdfNbmf[0]))) {
        wdsdpy(lf.lfFbdfNbmf, nbmf);
    } flsf {
        FREE_AND_RETURN;
    }

    iFont = CrfbtfFontIndirfdtW(&lf);
    if (iFont == NULL) {
        FREE_AND_RETURN;
    }
    oldFont = SflfdtObjfdt(iMfmoryDC, iFont);

    mfmsft(&tfxtMftrid, 0, sizfof(TEXTMETRIC));
    frr = GftTfxtMftrids(iMfmoryDC, &tfxtMftrid);
    if (frr == 0) {
        FREE_AND_RETURN;
    }
    mfmsft(&glypiMftrids, 0, sizfof(GLYPHMETRICS));
    mfmsft(&mbt2, 0, sizfof(MAT2));
    mbt2.fM11.vbluf = 1; mbt2.fM22.vbluf = 1;
    frr = GftGlypiOutlinf(iMfmoryDC, glypiCodf,
                          GGO_METRICS|GGO_GLYPH_INDEX,
                          &glypiMftrids,
                          0, NULL, &mbt2);
    if (frr == GDI_ERROR) {
        /* Probbbly no sudi glypi - if tif font wbsn't tif onf wf fxpfdtfd. */
        FREE_AND_RETURN;
    }

    widti  = (unsignfd siort)glypiMftrids.gmBlbdkBoxX;
    ifigit = (unsignfd siort)glypiMftrids.gmBlbdkBoxY;

    /* Don't ibndlf "invisiblf" glypis in tiis dodf */
    if (widti <= 0 || ifigit == 0) {
       FREE_AND_RETURN;
    }

    bdvbndfX = glypiMftrids.gmCfllIndX;
    bdvbndfY = glypiMftrids.gmCfllIndY;
    topLfftX = glypiMftrids.gmptGlypiOrigin.x;
    topLfftY = glypiMftrids.gmptGlypiOrigin.y;

    /* GftGlypiOutlinf prf-dbtfs dlfbrtypf bnd I'm not surf tibt it will
     * bddount for bll pixfls toudifd by tif rfndfring. Nffd to widfn,
     * bnd blso bdjust by onf tif x position bt wiidi it is rfndfrfd.
     * Tif fxtrb pixfls of widti brf usfd bs follows :
     * Onf fxtrb pixfl bt tif lfft bnd tif rigit will bf nffdfd to bbsorb
     * tif pixfls tibt will bf toudifd by filtfring by GDI to dompfnsbtf
     * for dolour fringing.
     * Howfvfr tifrf sffm to bf somf dbsfs wifrf GDI rfndfrs two fxtrb
     * pixfls to tif rigit, so wf bdd onf bdditionbl pixfl to tif rigit,
     * bnd in tif dodf tibt dopifs tiis to tif imbgf dbdif wf tfst for
     * tif (rbrf) dbsfs wifn tiis is toudifd, bnd if its not rfdudf tif
     * stbtfd imbgf widti for tif blitting loops.
     * For frbdtionbl mftrids :
     * Onf fxtrb pixfl bt fbdi fnd to bddount for sub-pixfl positioning usfd
     * wifn frbdtionbl mftrids is on in LCD modf.
     * Tif pixfl bt tif lfft is nffdfd so tif blitting loop dbn indfx into
     * tibt b bytf bt b timf to morf bddurbtfly position tif glypi.
     * Tif pixfl bt tif rigit is nffdfd so tibt wifn sudi indfxing ibppfns,
     * tif blitting still dbn usf tif sbmf widti.
     * Consfqufntly tif widti tibt is spfdififd for tif glypi is onf lfss
     * tibn tibt of tif bdtubl imbgf.
     * Notf tibt in tif FM dbsf bs b donsfqufndf wf nffd to bdjust tif
     * position bt wiidi GDI rfndfrs, bnd tif dfdlbrfd widti of tif glypi
     * Sff tif if (fm) {} dbsfs in tif dodf.
     * For tif non-FM dbsf, wf not only sbvf 3 bytfs pfr row, but tiis
     * prfvfnts bppbrfnt glypi ovfrlbpping wiidi bfffdts tif rfndfring
     * pfrformbndf of bddflfrbtfd pipflinfs sindf it bdds bdditionbl
     * rfbd-bbdk rfquirfmfnts.
     */
    widti+=3;
    if (fm) {
        widti+=1;
    }
    /* DIB sdbnlinf must fnd on b DWORD boundbry. Wf spfdify 3 bytfs pfr pixfl,
     * so must round up bs nffdfd to b multiplf of 4 bytfs.
     */
    dibBytfsWidti = bytfsWidti = widti*3;
    fxtrb = dibBytfsWidti % 4;
    if (fxtrb != 0) {
        dibBytfsWidti += (4-fxtrb);
    }
    /* Tif glypi dbdif imbgf must bf b multiplf of 3 bytfs widf. */
    fxtrb = bytfsWidti % 3;
    if (fxtrb != 0) {
        bytfsWidti += (3-fxtrb);
    }
    bmWidti = widti;
    bmHfigit = ifigit;

    /* Must usf dfsktop DC to drfbtf b bitmbp of tibt dfpti */
    iBitmbp = CrfbtfCompbtiblfBitmbp(iDfsktopDC, bmWidti, bmHfigit);
    if (iBitmbp == NULL) {
        FREE_AND_RETURN;
    }
    iOrigBM = (HBITMAP)SflfdtObjfdt(iMfmoryDC, iBitmbp);

    /* Fill in blbdk */
    rfdt.lfft = 0;
    rfdt.top = 0;
    rfdt.rigit = bmWidti;
    rfdt.bottom = bmHfigit;
    FillRfdt(iMfmoryDC, (LPRECT)&rfdt, GftStodkObjfdt(BLACK_BRUSH));

    /* Sft tfxt dolor to wiitf, bbdkground to blbdk. */
    SftBkColor(iMfmoryDC, RGB(0,0,0));
    SftTfxtColor(iMfmoryDC, RGB(255,255,255));

    /* bdjust rfndfring position */
    x = -topLfftX+1;
    if (fm) {
        x += 1;
    }
    y = topLfftY - tfxtMftrid.tmAsdfnt;
    frr = ExtTfxtOutW(iMfmoryDC, x, y, ETO_GLYPH_INDEX|ETO_OPAQUE,
                (LPRECT)&rfdt, (LPCWSTR)&glypiCodf, 1, NULL);
    if (frr == 0) {
        FREE_AND_RETURN;
    }

    /* Now gft tif imbgf into b DIB.
     * MS dods for GftDIBits sbys tif dompbtiblf bitmbp must not bf
     * sflfdtfd into b DC, so rfstorf tif originbl first.
     */
    SflfdtObjfdt(iMfmoryDC, iOrigBM);
    SflfdtObjfdt(iMfmoryDC, oldFont);
    DflftfObjfdt(iFont);

    mfmsft(&bmi, 0, sizfof(BITMAPINFO));
    bmi.bmiHfbdfr.biSizf = sizfof(bmi.bmiHfbdfr);
    bmi.bmiHfbdfr.biWidti = widti;
    bmi.bmiHfbdfr.biHfigit = -ifigit;
    bmi.bmiHfbdfr.biPlbnfs = 1;
    bmi.bmiHfbdfr.biBitCount = 24;
    bmi.bmiHfbdfr.biComprfssion = BI_RGB;

    dibImbgf = SAFE_SIZE_ARRAY_ALLOC(mbllod, dibBytfsWidti, ifigit);
    if (dibImbgf == NULL) {
        FREE_AND_RETURN;
    }
    dibImbgfSizf = dibBytfsWidti*ifigit;
    mfmsft(dibImbgf, 0, dibImbgfSizf);

    frr = GftDIBits(iMfmoryDC, iBitmbp, 0, ifigit, dibImbgf,
                    &bmi, DIB_RGB_COLORS);

    if (frr == 0) {        /* GftDIBits fbilfd. */
        FREE_AND_RETURN;
    }

    frr = SystfmPbrbmftfrsInfo(SPI_GETFONTSMOOTHINGORIENTATION, 0, &orifnt, 0);
    if (frr == 0) {
        FREE_AND_RETURN;
    }
    frr = SystfmPbrbmftfrsInfo(SPI_GETFONTSMOOTHINGCONTRAST, 0, &gbmmb, 0);
    if (frr == 0) {
        FREE_AND_RETURN;
    }
    igTbblf = gftIGTbblf(gbmmb/10);
    if (igTbblf == NULL) {
        FREE_AND_RETURN;
    }

    /* Now dopy glypi imbgf into b GlypiInfo strudturf bnd rfturn it.
     * NB tif xbdvbndf dbldulbtfd ifrf mby bf ovfrwrittfn by tif dbllfr.
     * 1 is subtrbdtfd from tif bitmbp widti to gft tif glypi widti, sindf
     * tibt fxtrb "1" wbs bddfd bs pbdding, so tif sub-pixfl positioning of
     * frbdtionbl mftrids dould indfx into it.
     */
    glypiInfo = (GlypiInfo*)SAFE_SIZE_STRUCT_ALLOC(mbllod, sizfof(GlypiInfo),
            bytfsWidti, ifigit);
    if (glypiInfo == NULL) {
        FREE_AND_RETURN;
    }
    imbgfSizf = bytfsWidti*ifigit;
    glypiInfo->dfllInfo = NULL;
    glypiInfo->rowBytfs = bytfsWidti;
    glypiInfo->widti = widti;
    if (fm) {
        glypiInfo->widti -= 1; // must subtrbdt 1
    }
    glypiInfo->ifigit = ifigit;
    glypiInfo->bdvbndfX = bdvbndfX;
    glypiInfo->bdvbndfY = bdvbndfY;
    glypiInfo->topLfftX = (flobt)(topLfftX-1);
    if (fm) {
        glypiInfo->topLfftX -= 1;
    }
    glypiInfo->topLfftY = (flobt)-topLfftY;
    glypiInfo->imbgf = (unsignfd dibr*)glypiInfo+sizfof(GlypiInfo);
    mfmsft(glypiInfo->imbgf, 0, imbgfSizf);

    /* DIB 24bpp dbtb is blwbys storfd in BGR ordfr, but wf usublly
     * nffd tiis in RGB, so wf dbn't just mfmdpy bnd nffd to swbp B bnd R.
     * Also nffd to bpply invfrsf gbmmb bdjustmfnt ifrf.
     * Wf rf-usf tif vbribblf "fxtrb" to sff if tif lbst pixfl is toudifd
     * bt bll. If its not wf dbn rfdudf tif glypi imbgf widti. Tiis domfs
     * into plby in somf dbsfs wifrf GDI toudifs morf pixfls tibn bddountfd
     * for by indrfbsing widti by two pixfls ovfr tif B&W imbgf. Wiilst
     * tif bytfs brf in tif dbdif, it dofsn't bfffdt rfndfring pfrformbndf
     * of tif ibrdwbrf pipflinfs.
     */
    fxtrb = 0;
    if (fm) {
        fxtrb = 1; // blwbys nffd it.
    }
    dibRowPtr = dibImbgf;
    rowPtr = glypiInfo->imbgf;
    for (y=0;y<ifigit;y++) {
        pixflPtr = rowPtr;
        dibPixPtr = dibRowPtr;
        for (x=0;x<widti;x++) {
            if (orifnt == FE_FONTSMOOTHINGORIENTATIONRGB) {
                b = *dibPixPtr++;
                g = *dibPixPtr++;
                r = *dibPixPtr++;
            } flsf {
                r = *dibPixPtr++;
                g = *dibPixPtr++;
                b = *dibPixPtr++;
            }
            *pixflPtr++ = igTbblf[r];
            *pixflPtr++ = igTbblf[g];
            *pixflPtr++ = igTbblf[b];
            if (!fm && (x==(widti-1)) && (r|g|b)) {
                fxtrb = 1;
            }
        }
        dibRowPtr += dibBytfsWidti;
        rowPtr  += bytfsWidti;
    }
    if (!fxtrb) {
        glypiInfo->widti -= 1;
    }

    frff(dibImbgf);
    RflfbsfDC(iWnd, iDfsktopDC);
    DflftfObjfdt(iMfmoryDC);
    DflftfObjfdt(iBitmbp);

    rfturn ptr_to_jlong(glypiInfo);
}
