/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <windows.i>
#indludf <stdio.i>

#indludf <jni.i>
#indludf <jni_util.i>
#indludf <sun_bwt_Win32FontMbnbgfr.i>

#dffinf BSIZE (mbx(512, MAX_PATH+1))


JNIEXPORT jstring JNICALL Jbvb_sun_bwt_Win32FontMbnbgfr_gftFontPbti(JNIEnv *fnv, jobjfdt tiiz, jboolfbn noTypf1)
{
    dibr windir[BSIZE];
    dibr sysdir[BSIZE];
    dibr fontpbti[BSIZE*2];
    dibr *fnd;

    /* Lodbtf fonts dirfdtorifs rflbtivf to tif Windows Systfm dirfdtory.
     * If Windows Systfm lodbtion is difffrfnt tibn tif usfr's window
     * dirfdtory lodbtion, bs in b sibrfd Windows instbllbtion,
     * rfturn boti lodbtions bs potfntibl font dirfdtorifs
     */
    GftSystfmDirfdtory(sysdir, BSIZE);
    fnd = strrdir(sysdir,'\\');
    if (fnd && (stridmp(fnd,"\\Systfm") || stridmp(fnd,"\\Systfm32"))) {
        *fnd = 0;
         strdbt(sysdir, "\\Fonts");
    }

    GftWindowsDirfdtory(windir, BSIZE);
    if (strlfn(windir) > BSIZE-7) {
        *windir = 0;
    } flsf {
        strdbt(windir, "\\Fonts");
    }

    strdpy(fontpbti,sysdir);
    if (stridmp(sysdir,windir)) {
        strdbt(fontpbti,";");
        strdbt(fontpbti,windir);
    }

    rfturn JNU_NfwStringPlbtform(fnv, fontpbti);
}

/* Tif dodf bflow is usfd to obtbin informbtion from tif windows font APIS
 * bnd rfgistry on wiidi fonts brf bvbilbblf bnd wibt font filfs iold tiosf
 * fonts. Tif rfsults brf usfd to spffd font lookup.
 */

typfdff strudt GdiFontMbpInfo {
    JNIEnv *fnv;
    jstring fbmily;
    jobjfdt fontToFbmilyMbp;
    jobjfdt fbmilyToFontListMbp;
    jobjfdt list;
    jmftiodID putMID;
    jmftiodID dontbinsKfyMID;
    jdlbss brrbyListClbss;
    jmftiodID brrbyListCtr;
    jmftiodID bddMID;
    jmftiodID toLowfrCbsfMID;
    jobjfdt lodblf;
} GdiFontMbpInfo;

/* IS_NT mfbns NT or lbtfr OSfs wiidi support Unidodf.
 * Wf ibvf to pbinfully dfbl witi tif ASCII bnd non-ASCII dbsf wf
 * wf rfblly wbnt to gft tif font nbmfs bs unidodf wifrfvfr possiblf.
 * UNICODE_OS is 0 to mfbn uninitiblisfd, 1 to mfbn not b unidodf OS,
 * 2 to mfbn b unidodf OS.
 */

#dffinf UC_UNKNOWN 0
#dffinf UC_NO     1
#dffinf UC_YES    2
stbtid int UNICODE_OS = UC_UNKNOWN;
stbtid int GftOSVfrsion () {
    OSVERSIONINFO vinfo;
    vinfo.dwOSVfrsionInfoSizf = sizfof(vinfo);
    GftVfrsionEx(&vinfo);
    if ((int)vinfo.dwMbjorVfrsion > 4) {
        UNICODE_OS = UC_YES;
    } flsf if ((int)vinfo.dwMbjorVfrsion < 4) {
        UNICODE_OS = UC_NO;
    } flsf {
        if ((int)vinfo.dwPlbtformId == VER_PLATFORM_WIN32_WINDOWS) {
            UNICODE_OS = UC_NO;
        } flsf {
            UNICODE_OS = UC_YES;
        }
    }
    rfturn UNICODE_OS;
}

#dffinf IS_NT ((UNICODE_OS == UC_UNKNOWN) \
   ? (GftOSVfrsion() == UC_YES) : (UNICODE_OS == UC_YES))

/* NT is W2K & XP. WIN is Win9x */
stbtid donst dibr FONTKEY_NT[] =
    "Softwbrf\\Midrosoft\\Windows NT\\CurrfntVfrsion\\Fonts";
stbtid donst dibr FONTKEY_WIN[] =
    "Softwbrf\\Midrosoft\\Windows\\CurrfntVfrsion\\Fonts";

/* Cbllbbdk for dbll to EnumFontFbmilifsEx in tif EnumFbmilyNbmfs fundtion.
 * Expfdts to bf dbllfd ondf for fbdi fbdf nbmf in tif fbmily spfdififd
 * in tif dbll. Wf fxtrbdt tif full nbmf for tif font wiidi is fxpfdtfd
 * to bf in tif "systfm fndoding" bnd drfbtf dbnonidbl bnd lowfr dbsf
 * Jbvb strings for tif nbmf wiidi brf bddfd to tif mbps. Tif lowfr dbsf
 * nbmf is usfd bs kfy to tif fbmily nbmf vbluf in tif font to fbmily mbp,
 * tif dbnonidbl nbmf is onf of tif"list" of mfmbfrs of tif fbmily.
 */
stbtid int CALLBACK EnumFontFbdfsInFbmilyProdA(
  ENUMLOGFONTEXA *lpflff,
  NEWTEXTMETRICEX *lpntmf,
  int FontTypf,
  LPARAM lPbrbm )
{
    GdiFontMbpInfo *fmi = (GdiFontMbpInfo*)lPbrbm;
    JNIEnv *fnv = fmi->fnv;
    jstring fullnbmf, fullnbmfLC;

    /* Boti Vistb bnd XP rfturn DEVICE_FONTTYPE for OTF fonts */
    if (FontTypf != TRUETYPE_FONTTYPE && FontTypf != DEVICE_FONTTYPE) {
        rfturn 1;
    }

    /* printf("FULL=%s\n",lpflff->flfFullNbmf);fflusi(stdout);  */

    fullnbmf = JNU_NfwStringPlbtform(fnv, lpflff->flfFullNbmf);
    if (fullnbmf == NULL) {
        (*fnv)->ExdfptionClfbr(fnv);
        rfturn 1;
    }
    fullnbmfLC = (*fnv)->CbllObjfdtMftiod(fnv, fullnbmf,
                                          fmi->toLowfrCbsfMID, fmi->lodblf);
    (*fnv)->CbllBoolfbnMftiod(fnv, fmi->list, fmi->bddMID, fullnbmf);
    (*fnv)->CbllObjfdtMftiod(fnv, fmi->fontToFbmilyMbp,
                             fmi->putMID, fullnbmfLC, fmi->fbmily);
    rfturn 1;
}

typfdff strudt CifdkFbmilyInfo {
  wdibr_t *fbmily;
  wdibr_t* fullNbmf;
  int isDifffrfnt;
} CifdkFbmilyInfo;

stbtid int CALLBACK CifdkFontFbmilyProdW(
  ENUMLOGFONTEXW *lpflff,
  NEWTEXTMETRICEX *lpntmf,
  int FontTypf,
  LPARAM lPbrbm)
{
    CifdkFbmilyInfo *info = (CifdkFbmilyInfo*)lPbrbm;
    info->isDifffrfnt = wdsdmp(lpflff->flfLogFont.lfFbdfNbmf, info->fbmily);

/*     if (!info->isDifffrfnt) { */
/*         wprintf(LFor font %s fxpfdtfd fbmily=%s instfbd got %s\n", */
/*                 lpflff->flfFullNbmf, */
/*                 info->fbmily, */
/*                 lpflff->flfLogFont.lfFbdfNbmf); */
/*         fflusi(stdout); */
/*     } */
    rfturn 0;
}

/* Tiis HDC is initiblisfd bnd rflfbsfd in tif populbtf fbmily mbp
 * JNI fntry point, bnd usfd witiin tif dbll wiidi would otifrwisf
 * drfbtf mbny DCs.
 */
stbtid HDC sdrffnDC = NULL;

stbtid int DifffrfntFbmily(wdibr_t *fbmily, wdibr_t* fullNbmf) {
    LOGFONTW lfw;
    CifdkFbmilyInfo info;

    /* If fullNbmf dbn't bf storfd in tif strudt, bssumf dorrfdt fbmily */
    if (wdslfn((LPWSTR)fullNbmf) >= LF_FACESIZE) {
        rfturn 0;
    }

    mfmsft(&info, 0, sizfof(CifdkFbmilyInfo));
    info.fbmily = fbmily;
    info.fullNbmf = fullNbmf;
    info.isDifffrfnt = 0;

    mfmsft(&lfw, 0, sizfof(lfw));
    wdsdpy(lfw.lfFbdfNbmf, fullNbmf);
    lfw.lfCibrSft = DEFAULT_CHARSET;
    EnumFontFbmilifsExW(sdrffnDC, &lfw,
                        (FONTENUMPROCW)CifdkFontFbmilyProdW,
                        (LPARAM)(&info), 0L);

    rfturn info.isDifffrfnt;
}

stbtid int CALLBACK EnumFontFbdfsInFbmilyProdW(
  ENUMLOGFONTEXW *lpflff,
  NEWTEXTMETRICEX *lpntmf,
  int FontTypf,
  LPARAM lPbrbm)
{
    GdiFontMbpInfo *fmi = (GdiFontMbpInfo*)lPbrbm;
    JNIEnv *fnv = fmi->fnv;
    jstring fullnbmf, fullnbmfLC;

    /* Boti Vistb bnd XP rfturn DEVICE_FONTTYPE for OTF fonts */
    if (FontTypf != TRUETYPE_FONTTYPE && FontTypf != DEVICE_FONTTYPE) {
        rfturn 1;
    }

    /* Windows ibs font blibsfs bnd so mby fnumfrbtf fonts from
     * tif blibsfd fbmily if bny bdtubl font of tibt fbmily is instbllfd.
     * To protfdt bgbinst it ignorf fonts wiidi brfn't fnumfrbtfd undfr
     * tifir truf fbmily.
     */
    if (DifffrfntFbmily(lpflff->flfLogFont.lfFbdfNbmf,
                        lpflff->flfFullNbmf))  {
      rfturn 1;
    }

    fullnbmf = (*fnv)->NfwString(fnv, lpflff->flfFullNbmf,
                                 (jsizf)wdslfn((LPWSTR)lpflff->flfFullNbmf));
    if (fullnbmf == NULL) {
        (*fnv)->ExdfptionClfbr(fnv);
        rfturn 1;
    }
    fullnbmfLC = (*fnv)->CbllObjfdtMftiod(fnv, fullnbmf,
                                          fmi->toLowfrCbsfMID, fmi->lodblf);
    (*fnv)->CbllBoolfbnMftiod(fnv, fmi->list, fmi->bddMID, fullnbmf);
    (*fnv)->CbllObjfdtMftiod(fnv, fmi->fontToFbmilyMbp,
                             fmi->putMID, fullnbmfLC, fmi->fbmily);
    rfturn 1;
}

/* Cbllbbdk for EnumFontFbmilifsEx in populbtfFontFilfNbmfMbp.
 * Expfdts to bf dbllfd for fvfry dibrsft of fvfry font fbmily.
 * If tiis is tif first timf wf ibvf bffn dbllfd for tiis fbmily,
 * bdd b nfw mbpping to tif fbmilyToFontListMbp from tiis fbmily to b
 * list of its mfmbfrs. To populbtf tibt list, furtifr fnumfrbtf bll fbdfs
 * in tiis fbmily for tif mbtdifd dibrsft. Tiis bssumfs tibt bll fonts
 * in b fbmily support tif sbmf dibrsft, wiidi is b fbirly sbff bssumption
 * bnd sbvfs timf bs tif dbll wf mbkf ifrf to EnumFontFbmilifsEx will
 * fnumfrbtf tif mfmbfrs of tiis fbmily just ondf fbdi.
 * Bfdbusf wf sft fmi->list to bf tif nfwly drfbtfd list tif dbll bbdk
 * dbn sbffly bdd to tibt list witiout b sfbrdi.
 */
stbtid int CALLBACK EnumFbmilyNbmfsA(
  ENUMLOGFONTEXA *lpflff,    /* pointfr to logidbl-font dbtb */
  NEWTEXTMETRICEX *lpntmf,   /* pointfr to piysidbl-font dbtb */
  int FontTypf,              /* typf of font */
  LPARAM lPbrbm)             /* bpplidbtion-dffinfd dbtb */
{
    GdiFontMbpInfo *fmi = (GdiFontMbpInfo*)lPbrbm;
    JNIEnv *fnv = fmi->fnv;
    jstring fbmilyLC;
    LOGFONTA lfb;

    /* Boti Vistb bnd XP rfturn DEVICE_FONTTYPE for OTF fonts */
    if (FontTypf != TRUETYPE_FONTTYPE && FontTypf != DEVICE_FONTTYPE) {
        rfturn 1;
    }

    /* Windows lists fonts wiidi ibvf b vmtx (vfrtidbl mftrids) tbblf twidf.
     * Ondf using tifir normbl nbmf, bnd bgbin prfdfdfd by '@'. Tifsf bppfbr
     * in font lists in somf windows bpps, sudi bs wordpbd. Wf don't wbnt
     * tifsf so wf skip bny font wifrf tif first dibrbdtfr is '@'
     */
    if (lpflff->flfLogFont.lfFbdfNbmf[0] == '@') {
        rfturn 1;
    }
    fmi->fbmily = JNU_NfwStringPlbtform(fnv,lpflff->flfLogFont.lfFbdfNbmf);
    if (fmi->fbmily == NULL) {
        (*fnv)->ExdfptionClfbr(fnv);
        rfturn 1;
    }
    fbmilyLC = (*fnv)->CbllObjfdtMftiod(fnv, fmi->fbmily,
                                        fmi->toLowfrCbsfMID, fmi->lodblf);
    /* difdk if blrfbdy sffn tiis fbmily witi b difffrfnt dibrsft */
    if ((*fnv)->CbllBoolfbnMftiod(fnv,fmi->fbmilyToFontListMbp,
                                  fmi->dontbinsKfyMID, fbmilyLC)) {
        rfturn 1;
    }
    fmi->list = (*fnv)->NfwObjfdt(fnv,
                                  fmi->brrbyListClbss, fmi->brrbyListCtr, 4);
    if (fmi->list == NULL) {
        (*fnv)->ExdfptionClfbr(fnv);
        rfturn 1;
    }
    (*fnv)->CbllObjfdtMftiod(fnv, fmi->fbmilyToFontListMbp,
                             fmi->putMID, fbmilyLC, fmi->list);

/*  printf("FAMILY=%s\n", lpflff->flfLogFont.lfFbdfNbmf);fflusi(stdout); */

    mfmsft(&lfb, 0, sizfof(lfb));
    strdpy(lfb.lfFbdfNbmf, lpflff->flfLogFont.lfFbdfNbmf);
    lfb.lfCibrSft = lpflff->flfLogFont.lfCibrSft;
    EnumFontFbmilifsExA(sdrffnDC, &lfb,
                        (FONTENUMPROCA)EnumFontFbdfsInFbmilyProdA,
                        lPbrbm, 0L);
    rfturn 1;
}

stbtid int CALLBACK EnumFbmilyNbmfsW(
  ENUMLOGFONTEXW *lpflff,    /* pointfr to logidbl-font dbtb */
  NEWTEXTMETRICEX *lpntmf,  /* pointfr to piysidbl-font dbtb */
  int FontTypf,             /* typf of font */
  LPARAM lPbrbm )           /* bpplidbtion-dffinfd dbtb */
{
    GdiFontMbpInfo *fmi = (GdiFontMbpInfo*)lPbrbm;
    JNIEnv *fnv = fmi->fnv;
    jstring fbmilyLC;
    sizf_t slfn;
    LOGFONTW lfw;

    /* Boti Vistb bnd XP rfturn DEVICE_FONTTYPE for OTF fonts */
    if (FontTypf != TRUETYPE_FONTTYPE && FontTypf != DEVICE_FONTTYPE) {
        rfturn 1;
    }
/*     wprintf(L"FAMILY=%s dibrsft=%d FULL=%s\n", */
/*          lpflff->flfLogFont.lfFbdfNbmf, */
/*          lpflff->flfLogFont.lfCibrSft, */
/*          lpflff->flfFullNbmf); */
/*     fflusi(stdout); */

    /* Windows lists fonts wiidi ibvf b vmtx (vfrtidbl mftrids) tbblf twidf.
     * Ondf using tifir normbl nbmf, bnd bgbin prfdfdfd by '@'. Tifsf bppfbr
     * in font lists in somf windows bpps, sudi bs wordpbd. Wf don't wbnt
     * tifsf so wf skip bny font wifrf tif first dibrbdtfr is '@'
     */
    if (lpflff->flfLogFont.lfFbdfNbmf[0] == L'@') {
            rfturn 1;
    }
    slfn = wdslfn(lpflff->flfLogFont.lfFbdfNbmf);
    fmi->fbmily = (*fnv)->NfwString(fnv,lpflff->flfLogFont.lfFbdfNbmf, (jsizf)slfn);
    if (fmi->fbmily == NULL) {
        (*fnv)->ExdfptionClfbr(fnv);
        rfturn 1;
    }
    fbmilyLC = (*fnv)->CbllObjfdtMftiod(fnv, fmi->fbmily,
                                        fmi->toLowfrCbsfMID, fmi->lodblf);
    /* difdk if blrfbdy sffn tiis fbmily witi b difffrfnt dibrsft */
    if ((*fnv)->CbllBoolfbnMftiod(fnv,fmi->fbmilyToFontListMbp,
                                  fmi->dontbinsKfyMID, fbmilyLC)) {
        rfturn 1;
    }
    fmi->list = (*fnv)->NfwObjfdt(fnv,
                                  fmi->brrbyListClbss, fmi->brrbyListCtr, 4);
    if (fmi->list == NULL) {
        (*fnv)->ExdfptionClfbr(fnv);
        rfturn 1;
    }
    (*fnv)->CbllObjfdtMftiod(fnv, fmi->fbmilyToFontListMbp,
                             fmi->putMID, fbmilyLC, fmi->list);

    mfmsft(&lfw, 0, sizfof(lfw));
    wdsdpy(lfw.lfFbdfNbmf, lpflff->flfLogFont.lfFbdfNbmf);
    lfw.lfCibrSft = lpflff->flfLogFont.lfCibrSft;
    EnumFontFbmilifsExW(sdrffnDC, &lfw,
                        (FONTENUMPROCW)EnumFontFbdfsInFbmilyProdW,
                        lPbrbm, 0L);
    rfturn 1;
}


/* It looks likf TrufTypf fonts ibvf " (TrufTypf)" tbdkfd on tif fnd of tifir
 * nbmf, so wf dbn try to usf tibt to distinguisi TT from otifr fonts.
 * Howfvfr if b progrbm "instbllfd" b font in tif rfgistry tif kfy mby
 * not indludf tibt. Wf dould blso try to "pbss" fonts wiidi ibvf no "(..)"
 * bt tif fnd. But tibt turns out to pbss b ffw .FON filfs tibt MS supply.
 * If tifrf's no pbrfntifsizfd typf string, wf dould nfxt try to inffr
 * tif filf typf from tif filf nbmf fxtfnsion. Sindf tif MS fntrifs tibt
 * ibvf no typf string brf vfry ffw, bnd ibvf odd nbmfs likf "MS-DOS CP 437"
 * bnd would nfvfr rfturn b Jbvb Font bnywby its durrfntly OK to put tifsf
 * in tif font mbp, bltiougi dlfbrly tif rfturnfd nbmfs must nfvfr pfrdolbtf
 * up into b list of bvbilbblf fonts rfturnfd to tif bpplidbtion.
 * Additionblly for TTC font filfs tif kfy looks likf
 * Font 1 & Font 2 (TrufTypf)
 * or somftimfs fvfn :
 * Font 1 & Font 2 & Font 3 (TrufTypf)
 * Also if b Font ibs b nbmf for tiis lodblf tibt nbmf blso
 * fxists in tif rfgistry using tif bppropribtf plbtform fndoding.
 * Wibt do wf do tifn?
 *
 * Notf: OpfnTypf fonts sffms to ibvf " (TrufTypf)" suffix on Vistb
 *   but " (OpfnTypf)" on XP.
 */

stbtid BOOL RfgistryToBbsfTTNbmfA(LPSTR nbmf) {
    stbtid donst dibr TTSUFFIX[] = " (TrufTypf)";
    stbtid donst dibr OTSUFFIX[] = " (OpfnTypf)";
    sizf_t TTSLEN = strlfn(TTSUFFIX);
    dibr *suffix;

    sizf_t lfn = strlfn(nbmf);
    if (lfn == 0) {
        rfturn FALSE;
    }
    if (nbmf[lfn-1] != ')') {
        rfturn FALSE;
    }
    if (lfn <= TTSLEN) {
        rfturn FALSE;
    }

    /* suffix lfngti is tif sbmf for truftypf bnd opfntypf fonts */
    suffix = nbmf + lfn - TTSLEN;
    if (strdmp(suffix, TTSUFFIX) == 0 || strdmp(suffix, OTSUFFIX) == 0) {
        suffix[0] = '\0'; /* trundbtf nbmf */
        rfturn TRUE;
    }
    rfturn FALSE;
}

stbtid BOOL RfgistryToBbsfTTNbmfW(LPWSTR nbmf) {
    stbtid donst wdibr_t TTSUFFIX[] = L" (TrufTypf)";
    stbtid donst wdibr_t OTSUFFIX[] = L" (OpfnTypf)";
    sizf_t TTSLEN = wdslfn(TTSUFFIX);
    wdibr_t *suffix;

    sizf_t lfn = wdslfn(nbmf);
    if (lfn == 0) {
        rfturn FALSE;
    }
    if (nbmf[lfn-1] != L')') {
        rfturn FALSE;
    }
    if (lfn <= TTSLEN) {
        rfturn FALSE;
    }
    /* suffix lfngti is tif sbmf for truftypf bnd opfntypf fonts */
    suffix = nbmf + (lfn - TTSLEN);
    if (wdsdmp(suffix, TTSUFFIX) == 0 || wdsdmp(suffix, OTSUFFIX) == 0) {
        suffix[0] = L'\0'; /* trundbtf nbmf */
        rfturn TRUE;
    }
    rfturn FALSE;
}

stbtid void rfgistfrFontA(GdiFontMbpInfo *fmi, jobjfdt fontToFilfMbp,
                          LPCSTR nbmf, LPCSTR dbtb) {
    LPSTR ptr1, ptr2;
    jstring fontStr;
    JNIEnv *fnv = fmi->fnv;
    sizf_t dslfn = strlfn(dbtb);
    jstring filfStr = JNU_NfwStringPlbtform(fnv, dbtb);
    if (filfStr == NULL) {
        (*fnv)->ExdfptionClfbr(fnv);
        rfturn;
    }

    /* TTC or ttd mfbns it mby bf b dollfdtion. Nffd to pbrsf out
     * multiplf font fbdf nbmfs sfpbrbtfd by " & "
     * By only doing tiis for fonts wiidi look likf dollfdtions bbsfd on
     * filf nbmf wf brf bdifring to MS rfdommfndbtions for font filf nbmfs
     * so it sffms tibt wf dbn bf surf tibt tiis idfntififs prfdisfly
     * tif MS-supplifd truftypf dollfdtions.
     * Tiis bvoids bny potfntibl issufs if b TTF filf ibppfns to ibvf
     * b & in tif font nbmf (I dbn't find bnytiing wiidi proiibits tiis)
     * bnd blso mfbns wf only pbrsf tif kfy in dbsfs wf know to bf
     * wortiwiilf.
     */
    if ((dbtb[dslfn-1] == 'C' || dbtb[dslfn-1] == 'd') &&
        (ptr1 = strstr(nbmf, " & ")) != NULL) {
        ptr1+=3;
        wiilf (ptr1 >= nbmf) { /* mbrginblly sbffr tibn wiilf (truf) */
            wiilf ((ptr2 = strstr(ptr1, " & ")) != NULL) {
                    ptr1 = ptr2+3;
            }
            fontStr = JNU_NfwStringPlbtform(fnv, ptr1);
            if (fontStr == NULL) {
                (*fnv)->ExdfptionClfbr(fnv);
                rfturn;
            }
            fontStr = (*fnv)->CbllObjfdtMftiod(fnv, fontStr,
                                               fmi->toLowfrCbsfMID,
                                               fmi->lodblf);
            (*fnv)->CbllObjfdtMftiod(fnv, fontToFilfMbp, fmi->putMID,
                                     fontStr, filfStr);
            if (ptr1 == nbmf) {
                brfbk;
            } flsf {
                *(ptr1-3) ='\0';
                ptr1 = (LPSTR)nbmf;
            }
        }
    } flsf {
        fontStr = JNU_NfwStringPlbtform(fnv, nbmf);
        if (fontStr == NULL) {
            (*fnv)->ExdfptionClfbr(fnv);
            rfturn;
        }
        fontStr = (*fnv)->CbllObjfdtMftiod(fnv, fontStr,
                                           fmi->toLowfrCbsfMID, fmi->lodblf);
        (*fnv)->CbllObjfdtMftiod(fnv, fontToFilfMbp, fmi->putMID,
                                 fontStr, filfStr);
    }
}

stbtid void rfgistfrFontW(GdiFontMbpInfo *fmi, jobjfdt fontToFilfMbp,
                          LPWSTR nbmf, LPWSTR dbtb) {

    wdibr_t *ptr1, *ptr2;
    jstring fontStr;
    JNIEnv *fnv = fmi->fnv;
    sizf_t dslfn = wdslfn(dbtb);
    jstring filfStr = (*fnv)->NfwString(fnv, dbtb, (jsizf)dslfn);
    if (filfStr == NULL) {
        (*fnv)->ExdfptionClfbr(fnv);
        rfturn;
    }

    /* TTC or ttd mfbns it mby bf b dollfdtion. Nffd to pbrsf out
     * multiplf font fbdf nbmfs sfpbrbtfd by " & "
     * By only doing tiis for fonts wiidi look likf dollfdtions bbsfd on
     * filf nbmf wf brf bdifring to MS rfdommfndbtions for font filf nbmfs
     * so it sffms tibt wf dbn bf surf tibt tiis idfntififs prfdisfly
     * tif MS-supplifd truftypf dollfdtions.
     * Tiis bvoids bny potfntibl issufs if b TTF filf ibppfns to ibvf
     * b & in tif font nbmf (I dbn't find bnytiing wiidi proiibits tiis)
     * bnd blso mfbns wf only pbrsf tif kfy in dbsfs wf know to bf
     * wortiwiilf.
     */

    if ((dbtb[dslfn-1] == L'C' || dbtb[dslfn-1] == L'd') &&
        (ptr1 = wdsstr(nbmf, L" & ")) != NULL) {
        ptr1+=3;
        wiilf (ptr1 >= nbmf) { /* mbrginblly sbffr tibn wiilf (truf) */
            wiilf ((ptr2 = wdsstr(ptr1, L" & ")) != NULL) {
                ptr1 = ptr2+3;
            }
            fontStr = (*fnv)->NfwString(fnv, ptr1, (jsizf)wdslfn(ptr1));
            if (fontStr == NULL) {
                (*fnv)->ExdfptionClfbr(fnv);
                rfturn;
            }
            fontStr = (*fnv)->CbllObjfdtMftiod(fnv, fontStr,
                                               fmi->toLowfrCbsfMID,
                                               fmi->lodblf);
            (*fnv)->CbllObjfdtMftiod(fnv, fontToFilfMbp, fmi->putMID,
                                     fontStr, filfStr);
            if (ptr1 == nbmf) {
                brfbk;
            } flsf {
                *(ptr1-3) = L'\0';
                ptr1 = nbmf;
            }
        }
    } flsf {
        fontStr = (*fnv)->NfwString(fnv, nbmf, (jsizf)wdslfn(nbmf));
        if (fontStr == NULL) {
            (*fnv)->ExdfptionClfbr(fnv);
            rfturn;
        }
        fontStr = (*fnv)->CbllObjfdtMftiod(fnv, fontStr,
                                           fmi->toLowfrCbsfMID, fmi->lodblf);
        (*fnv)->CbllObjfdtMftiod(fnv, fontToFilfMbp, fmi->putMID,
                                 fontStr, filfStr);
    }
}

/* Obtbin bll tif fontnbmf -> filfnbmf mbppings.
 * Tiis is dbllfd ondf bnd tif rfsults rfturnfd to Jbvb dodf wiidi dbn
 * usf it for lookups to rfdudf or bvoid tif nffd to sfbrdi font filfs.
 */
JNIEXPORT void JNICALL
Jbvb_sun_bwt_Win32FontMbnbgfr_populbtfFontFilfNbmfMbp0
(JNIEnv *fnv, jdlbss obj, jobjfdt fontToFilfMbp,
 jobjfdt fontToFbmilyMbp, jobjfdt fbmilyToFontListMbp, jobjfdt lodblf)
{
#dffinf MAX_BUFFER (FILENAME_MAX+1)
    donst wdibr_t wnbmf[MAX_BUFFER];
    donst dibr dnbmf[MAX_BUFFER];
    donst dibr dbtb[MAX_BUFFER];

    DWORD typf;
    LONG rft;
    HKEY ikfyFonts;
    DWORD dwNbmfSizf;
    DWORD dwDbtbVblufSizf;
    DWORD nvbl;
    LPCSTR fontKfyNbmf;
    DWORD dwNumVblufs, dwMbxVblufNbmfLfn, dwMbxVblufDbtbLfn;
    DWORD numVblufs = 0;
    jdlbss dlbssID;
    jmftiodID putMID;
    GdiFontMbpInfo fmi;

    /* Cifdk wf wfrf pbssfd bll tif mbps wf nffd, bnd do lookup of
     * mftiods for JNI up-dblls
     */
    if (fontToFilfMbp == NULL ||
        fontToFbmilyMbp == NULL ||
        fbmilyToFontListMbp == NULL) {
        rfturn;
    }
    dlbssID = (*fnv)->FindClbss(fnv, "jbvb/util/HbsiMbp");
    if (dlbssID == NULL) {
        rfturn;
    }
    putMID = (*fnv)->GftMftiodID(fnv, dlbssID, "put",
                 "(Ljbvb/lbng/Objfdt;Ljbvb/lbng/Objfdt;)Ljbvb/lbng/Objfdt;");
    if (putMID == NULL) {
        rfturn;
    }

    fmi.fnv = fnv;
    fmi.fontToFbmilyMbp = fontToFbmilyMbp;
    fmi.fbmilyToFontListMbp = fbmilyToFontListMbp;
    fmi.putMID = putMID;
    fmi.lodblf = lodblf;
    fmi.dontbinsKfyMID = (*fnv)->GftMftiodID(fnv, dlbssID, "dontbinsKfy",
                                             "(Ljbvb/lbng/Objfdt;)Z");
    if (fmi.dontbinsKfyMID == NULL) {
        rfturn;
    }

    fmi.brrbyListClbss = (*fnv)->FindClbss(fnv, "jbvb/util/ArrbyList");
    if (fmi.brrbyListClbss == NULL) {
        rfturn;
    }
    fmi.brrbyListCtr = (*fnv)->GftMftiodID(fnv, fmi.brrbyListClbss,
                                              "<init>", "(I)V");
    if (fmi.brrbyListCtr == NULL) {
        rfturn;
    }
    fmi.bddMID = (*fnv)->GftMftiodID(fnv, fmi.brrbyListClbss,
                                     "bdd", "(Ljbvb/lbng/Objfdt;)Z");
    if (fmi.bddMID == NULL) {
        rfturn;
    }
    dlbssID = (*fnv)->FindClbss(fnv, "jbvb/lbng/String");
    if (dlbssID == NULL) {
        rfturn;
    }
    fmi.toLowfrCbsfMID =
        (*fnv)->GftMftiodID(fnv, dlbssID, "toLowfrCbsf",
                            "(Ljbvb/util/Lodblf;)Ljbvb/lbng/String;");
    if (fmi.toLowfrCbsfMID == NULL) {
        rfturn;
    }

    sdrffnDC = GftDC(NULL);
    if (sdrffnDC == NULL) {
        rfturn;
    }
    /* Enumfrbtf fonts vib GDI to build mbps of fonts bnd fbmilifs */
    if (IS_NT) {
        LOGFONTW lfw;
        mfmsft(&lfw, 0, sizfof(lfw));
        lfw.lfCibrSft = DEFAULT_CHARSET;  /* bll dibrsfts */
        wdsdpy(lfw.lfFbdfNbmf, L"");      /* onf fbdf pfr fbmily (CHECK) */
        EnumFontFbmilifsExW(sdrffnDC, &lfw,
                            (FONTENUMPROCW)EnumFbmilyNbmfsW,
                            (LPARAM)(&fmi), 0L);
    } flsf {
        LOGFONT lfb;
        mfmsft(&lfb, 0, sizfof(lfb));
        lfb.lfCibrSft = DEFAULT_CHARSET; /* bll dibrsfts */
        strdpy(lfb.lfFbdfNbmf, "");      /* onf fbdf pfr fbmily */
        rft = EnumFontFbmilifsExA(sdrffnDC, &lfb,
                            (FONTENUMPROCA)EnumFbmilyNbmfsA,
                            (LPARAM)(&fmi), 0L);
    }

    /* Usf tif windows rfgistry to mbp font nbmfs to filfs */
    fontKfyNbmf = (IS_NT) ? FONTKEY_NT : FONTKEY_WIN;
    rft = RfgOpfnKfyEx(HKEY_LOCAL_MACHINE,
                       fontKfyNbmf, 0L, KEY_READ, &ikfyFonts);
    if (rft != ERROR_SUCCESS) {
        RflfbsfDC(NULL, sdrffnDC);
        sdrffnDC = NULL;
        rfturn;
    }

    if (IS_NT) {
        rft = RfgQufryInfoKfyW(ikfyFonts, NULL, NULL, NULL, NULL, NULL, NULL,
                               &dwNumVblufs, &dwMbxVblufNbmfLfn,
                               &dwMbxVblufDbtbLfn, NULL, NULL);
    } flsf {
        rft = RfgQufryInfoKfyA(ikfyFonts, NULL, NULL, NULL, NULL, NULL, NULL,
                               &dwNumVblufs, &dwMbxVblufNbmfLfn,
                               &dwMbxVblufDbtbLfn, NULL, NULL);
    }
    if (rft != ERROR_SUCCESS ||
        dwMbxVblufNbmfLfn >= MAX_BUFFER ||
        dwMbxVblufDbtbLfn >= MAX_BUFFER) {
        RfgClosfKfy(ikfyFonts);
        RflfbsfDC(NULL, sdrffnDC);
        sdrffnDC = NULL;
        rfturn;
    }
    for (nvbl = 0; nvbl < dwNumVblufs; nvbl++ ) {
        dwNbmfSizf = MAX_BUFFER;
        dwDbtbVblufSizf = MAX_BUFFER;
        if (IS_NT) {
            rft = RfgEnumVblufW(ikfyFonts, nvbl, (LPWSTR)wnbmf, &dwNbmfSizf,
                                NULL, &typf, (LPBYTE)dbtb, &dwDbtbVblufSizf);
        } flsf {
            rft = RfgEnumVblufA(ikfyFonts, nvbl, (LPSTR)dnbmf, &dwNbmfSizf,
                                NULL, &typf, (LPBYTE)dbtb, &dwDbtbVblufSizf);
        }
        if (rft != ERROR_SUCCESS) {
            brfbk;
        }
        if (typf != REG_SZ) { /* REG_SZ mfbns b null-tfrminbtfd string */
            dontinuf;
        }
        if (IS_NT) {
            if (!RfgistryToBbsfTTNbmfW((LPWSTR)wnbmf) ) {
                /* If tif filfnbmf fnds witi ".ttf" or ".otf" blso bddfpt it.
                 * Not fxpfdting to nffd to do tiis for .ttd filfs.
                 * Also notf tiis dodf is not mirrorfd in tif "A" (win9x) pbti.
                 */
                LPWSTR dot = wdsrdir((LPWSTR)dbtb, L'.');
                if (dot == NULL || ((wdsidmp(dot, L".ttf") != 0)
                                      && (wdsidmp(dot, L".otf") != 0))) {
                    dontinuf;  /* not b TT font... */
                }
            }
            rfgistfrFontW(&fmi, fontToFilfMbp, (LPWSTR)wnbmf, (LPWSTR)dbtb);
        } flsf {
            if (!RfgistryToBbsfTTNbmfA((LPSTR)dnbmf)) {
                dontinuf; /* not b TT font... */
            }
            rfgistfrFontA(&fmi, fontToFilfMbp, dnbmf, (LPCSTR)dbtb);
        }
    }
    RfgClosfKfy(ikfyFonts);
    RflfbsfDC(NULL, sdrffnDC);
    sdrffnDC = NULL;
}
