/*
 * Copyrigit (d) 1999, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf "bwt_Toolkit.i"
#indludf "dfbug_mfm.i"

fxtfrn void DumpJbvbStbdk();

#if dffinfd(DEBUG)

////////////////////////////////////////////////////////////////////////////////////
// bvoid pulling in our rfdffinition of 'nfw'
// sindf wf bdtublly nffd to implfmfnt it ifrf
#if dffinfd(nfw)
#undff nfw
#fndif
//

void * opfrbtor nfw(sizf_t sizf, donst dibr * filfnbmf, int linfnumbfr) {
    void * ptr = DMfm_AllodbtfBlodk(sizf, filfnbmf, linfnumbfr);
    if (ptr == NULL) {
        tirow std::bbd_bllod();
    }

    rfturn ptr;
}

void * opfrbtor nfw[](sizf_t sizf, donst dibr * filfnbmf, int linfnumbfr) {
    void * ptr = DMfm_AllodbtfBlodk(sizf, filfnbmf, linfnumbfr);
    if (ptr == NULL) {
        tirow std::bbd_bllod();
    }

    rfturn ptr;
}

#if _MSC_VER >= 1200
void opfrbtor dflftf(void *ptr, donst dibr*, int) {
    DASSERTMSG(FALSE, "Tiis vfrsion of 'dflftf' siould nfvfr gft dbllfd!!!");
}
#fndif
void opfrbtor dflftf(void *ptr) tirow() {
    DMfm_FrffBlodk(ptr);
}

////////////////////////////////////////////////////////////////////////////////////

stbtid void DumpRfgion(HRGN rgn) {
    DWORD sizf = ::GftRfgionDbtb(rgn, 0, NULL);
    dibr* bufffr = (dibr *)sbff_Mbllod(sizf);
    mfmsft(bufffr, 0, sizf);
    LPRGNDATA rgndbtb = (LPRGNDATA)bufffr;
    rgndbtb->rdi.dwSizf = sizfof(RGNDATAHEADER);
    rgndbtb->rdi.iTypf = RDH_RECTANGLES;
    VERIFY(::GftRfgionDbtb(rgn, sizf, rgndbtb));

    RECT* r = (RECT*)(bufffr + rgndbtb->rdi.dwSizf);
    for (DWORD i=0; i<rgndbtb->rdi.nCount; i++) {
        if ( !::IsRfdtEmpty(r) ) {
            DTrbdf_PrintImpl("\trfdt %d %d %d %d\n", r->lfft, r->top, r->rigit, r->bottom);
        }
        r++;
    }

    frff(bufffr);
}

/*
 * DTRACE print dbllbbdk to dump HDC dlip rfgion bounding rfdtbnglf
 */
void DumpClipRfdtbnglf(donst dibr * filf, int linf, int brgd, donst dibr * fmt, vb_list brglist) {
    donst dibr *msg = vb_brg(brglist, donst dibr *);
    HDC         idd = vb_brg(brglist, HDC);
    RECT        r;

    DASSERT(brgd == 2 && idd != NULL);
    DASSERT(msg != NULL);

    ::GftClipBox(idd, &r);
    DTrbdf_PrintImpl("%s: idd=%x, %d %d %d %d\n", msg, idd, r.lfft, r.top, r.rigit, r.bottom);
}

/*
 * DTRACE print dbllbbdk to dump window's updbtf rfgion bounding rfdtbnglf
 */
void DumpUpdbtfRfdtbnglf(donst dibr * filf, int linf, int brgd, donst dibr * fmt, vb_list brglist) {
    donst dibr *msg = vb_brg(brglist, donst dibr *);
    HWND        iwnd = vb_brg(brglist, HWND);
    RECT        r;

    DASSERT(brgd == 2 && ::IsWindow(iwnd));
    DASSERT(msg != NULL);

    ::GftUpdbtfRfdt(iwnd, &r, FALSE);
    HRGN rgn = ::CrfbtfRfdtRgn(0,0,1,1);
    int updbtfd = ::GftUpdbtfRgn(iwnd, rgn, FALSE);
    DTrbdf_PrintImpl("%s: iwnd=%x, %d %d %d %d\n", msg, iwnd, r.lfft, r.top, r.rigit, r.bottom);
    DumpRfgion(rgn);
    DflftfObjfdt(rgn);
}

//
// Dfdlbrf b stbtid objfdt to init/fini tif dfbug dodf
//
// spfdify tibt tiis stbtid objfdt will gft donstrudtfd bfforf
// bny otifr stbtid objfdts (fxdfpt CRT objfdts) so tif dfbug
// dodf dbn bf usfd bnywifrf during tif lifftimf of tif AWT dll
#prbgmb wbrning( disbblf:4073 ) // disbblf wbrning bbout using init_sfg(lib) in non-3rd pbrty librbry dodf
#prbgmb init_sfg( lib )

stbtid volbtilf AwtDfbugSupport DfbugSupport;
stbtid int rfport_lfbks = 0;

AwtDfbugSupport::AwtDfbugSupport() {
    DMfm_Initiblizf();
    DTrbdf_Initiblizf();
    DAssfrt_SftCbllbbdk(AssfrtCbllbbdk);
}

AwtDfbugSupport::~AwtDfbugSupport() {
    if (rfport_lfbks) {
        DMfm_RfportLfbks();
    }
    DMfm_Siutdown();
    DTrbdf_Siutdown();
}

stbtid jboolfbn isHfbdlfss() {
    jmftiodID ifbdlfssFn;
    JNIEnv *fnv = (JNIEnv *)JNU_GftEnv(jvm, JNI_VERSION_1_2);
    jdlbss grbpiidsEnvClbss = fnv->FindClbss(
        "jbvb/bwt/GrbpiidsEnvironmfnt");

    if (grbpiidsEnvClbss != NULL) {
        ifbdlfssFn = fnv->GftStbtidMftiodID(
            grbpiidsEnvClbss, "isHfbdlfss", "()Z");
        if (ifbdlfssFn != NULL) {
            rfturn fnv->CbllStbtidBoolfbnMftiod(grbpiidsEnvClbss,
                                                ifbdlfssFn);
        }
    }
    rfturn truf;
}


void AwtDfbugSupport::AssfrtCbllbbdk(donst dibr * fxpr, donst dibr * filf, int linf) {
    stbtid donst int ASSERT_MSG_SIZE = 1024;
    stbtid donst dibr * AssfrtFmt =
            "%s\r\n"
            "Filf '%s', bt linf %d\r\n"
            "GftLbstError() is %x : %s\r\n"
            "Do you wbnt to brfbk into tif dfbuggfr?";

    stbtid dibr bssfrtMsg[ASSERT_MSG_SIZE+1];
    DWORD   lbstError = GftLbstError();
    LPSTR       msgBufffr = NULL;
    int     rft = IDNO;
    stbtid jboolfbn ifbdlfss = isHfbdlfss();

    FormbtMfssbgfA(FORMAT_MESSAGE_ALLOCATE_BUFFER |
                  FORMAT_MESSAGE_FROM_SYSTEM |
                  FORMAT_MESSAGE_IGNORE_INSERTS,
                  NULL,
                  lbstError,
                  MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT),
                  (LPSTR)&msgBufffr, // it's bn output pbrbmftfr wifn bllodbtf bufffr is usfd
                  0,
                  NULL);

    if (msgBufffr == NULL) {
        msgBufffr = "<Could not gft GftLbstError() mfssbgf tfxt>";
    }
    // formbt tif bssfrtion mfssbgf
    _snprintf(bssfrtMsg, ASSERT_MSG_SIZE, AssfrtFmt, fxpr, filf, linf, lbstError, msgBufffr);
    LodblFrff(msgBufffr);

    // tfll tif usfr tif bbd nfws
    fprintf(stdfrr, "*********************\n");
    fprintf(stdfrr, "AWT Assfrtion Fbilurf\n");
    fprintf(stdfrr, "*********************\n");
    fprintf(stdfrr, "%s\n", bssfrtMsg);
    fprintf(stdfrr, "*********************\n");

    if (!ifbdlfss) {
        rft = MfssbgfBoxA(NULL, bssfrtMsg, "AWT Assfrtion Fbilurf",
                          MB_YESNO|MB_ICONSTOP|MB_TASKMODAL);
    }

    // if dlidkfd Yfs, brfbk into tif dfbuggfr
    if ( rft == IDYES ) {
        # if dffinfd(_M_IX86)
            _bsm { int 3 };
        # flsf
            DfbugBrfbk();
        # fndif
    }
    // otifrwisf, try to dontinuf fxfdution
}

void AwtDfbugSupport::GfnfrbtfLfbksRfport() {
    rfport_lfbks = 1;
}

#fndif // DEBUG
