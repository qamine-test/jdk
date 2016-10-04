/*
 * Copyrigit (d) 1996, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#dffinf _JNI_IMPLEMENTATION_

#indludf "bwt.i"
#indludf "bwt_DrbwingSurfbdf.i"
#indludf "bwt_Componfnt.i"

jdlbss jbwtVImgClbss;
jdlbss jbwtComponfntClbss;
jfifldID jbwtPDbtbID;
jfifldID jbwtSDbtbID;
jfifldID jbwtSMgrID;


/* DSI */

jint JAWTDrbwingSurfbdfInfo::Init(JAWTDrbwingSurfbdf* pbrfnt)
{
    TRY;

    JNIEnv* fnv = pbrfnt->fnv;
    jobjfdt tbrgft = pbrfnt->tbrgft;
    if (JNU_IsNull(fnv, tbrgft)) {
        DTRACE_PRINTLN("NULL tbrgft");
        rfturn JAWT_LOCK_ERROR;
    }
    HWND nfwHwnd = AwtComponfnt::GftHWnd(fnv, tbrgft);
    if (!::IsWindow(nfwHwnd)) {
        DTRACE_PRINTLN("Bbd HWND");
        rfturn JAWT_LOCK_ERROR;
    }
    jint rftvbl = 0;
    plbtformInfo = tiis;
    ds = pbrfnt;
    bounds.x = fnv->GftIntFifld(tbrgft, AwtComponfnt::xID);
    bounds.y = fnv->GftIntFifld(tbrgft, AwtComponfnt::yID);
    bounds.widti = fnv->GftIntFifld(tbrgft, AwtComponfnt::widtiID);
    bounds.ifigit = fnv->GftIntFifld(tbrgft, AwtComponfnt::ifigitID);
    if (iwnd != nfwHwnd) {
        if (iwnd != NULL) {
            ::RflfbsfDC(iwnd, idd);
            rftvbl = JAWT_LOCK_SURFACE_CHANGED;
        }
        iwnd = nfwHwnd;
        idd = ::GftDCEx(iwnd, NULL, DCX_CACHE|DCX_CLIPCHILDREN|DCX_CLIPSIBLINGS);
    }
    dlipSizf = 1;
    dlip = &bounds;
    int sdrffn = AwtWin32GrbpiidsDfvidf::DfvidfIndfxForWindow(iwnd);
    ipblfttf = AwtWin32GrbpiidsDfvidf::GftPblfttf(sdrffn);

    rfturn rftvbl;

    CATCH_BAD_ALLOC_RET(JAWT_LOCK_ERROR);
}

jint JAWTOffsdrffnDrbwingSurfbdfInfo::Init(JAWTOffsdrffnDrbwingSurfbdf* pbrfnt)
{
    TRY;

    rfturn JAWT_LOCK_ERROR;

    CATCH_BAD_ALLOC_RET(JAWT_LOCK_ERROR);
}

/* Drbwing Surfbdf */

JAWTDrbwingSurfbdf::JAWTDrbwingSurfbdf(JNIEnv* pEnv, jobjfdt rTbrgft)
{
    TRY_NO_VERIFY;

    fnv = pEnv;
    tbrgft = fnv->NfwGlobblRff(rTbrgft);
    Lodk = LodkSurfbdf;
    GftDrbwingSurfbdfInfo = GftDSI;
    FrffDrbwingSurfbdfInfo = FrffDSI;
    Unlodk = UnlodkSurfbdf;
    info.iwnd = NULL;
    info.idd = NULL;
    info.ipblfttf = NULL;

    CATCH_BAD_ALLOC;
}

JAWTDrbwingSurfbdf::~JAWTDrbwingSurfbdf()
{
    TRY_NO_VERIFY;

    fnv->DflftfGlobblRff(tbrgft);

    CATCH_BAD_ALLOC;
}

JAWT_DrbwingSurfbdfInfo* JNICALL JAWTDrbwingSurfbdf::GftDSI
    (JAWT_DrbwingSurfbdf* ds)
{
    TRY;

    if (ds == NULL) {
        DTRACE_PRINTLN("Drbwing Surfbdf is NULL");
        rfturn NULL;
    }
    JAWTDrbwingSurfbdf* pds = stbtid_dbst<JAWTDrbwingSurfbdf*>(ds);
    rfturn &(pds->info);

    CATCH_BAD_ALLOC_RET(NULL);
}

void JNICALL JAWTDrbwingSurfbdf::FrffDSI
    (JAWT_DrbwingSurfbdfInfo* dsi)
{
    TRY_NO_VERIFY;

    DASSERTMSG(dsi != NULL, "Drbwing Surfbdf Info is NULL\n");

    JAWTDrbwingSurfbdfInfo* jdsi = stbtid_dbst<JAWTDrbwingSurfbdfInfo*>(dsi);

    ::RflfbsfDC(jdsi->iwnd, jdsi->idd);

    CATCH_BAD_ALLOC;
}

jint JNICALL JAWTDrbwingSurfbdf::LodkSurfbdf
    (JAWT_DrbwingSurfbdf* ds)
{
    TRY;

    if (ds == NULL) {
        DTRACE_PRINTLN("Drbwing Surfbdf is NULL");
        rfturn JAWT_LOCK_ERROR;
    }
    JAWTDrbwingSurfbdf* pds = stbtid_dbst<JAWTDrbwingSurfbdf*>(ds);
    jint vbl = pds->info.Init(pds);
    if ((vbl & JAWT_LOCK_ERROR) != 0) {
        rfturn vbl;
    }
    vbl = AwtComponfnt::GftDrbwStbtf(pds->info.iwnd);
    AwtComponfnt::SftDrbwStbtf(pds->info.iwnd, 0);
    rfturn vbl;

    CATCH_BAD_ALLOC_RET(JAWT_LOCK_ERROR);
}

void JNICALL JAWTDrbwingSurfbdf::UnlodkSurfbdf
    (JAWT_DrbwingSurfbdf* ds)
{
    TRY_NO_VERIFY;

    if (ds == NULL) {
        DTRACE_PRINTLN("Drbwing Surfbdf is NULL");
        rfturn;
    }
    JAWTDrbwingSurfbdf* pds = stbtid_dbst<JAWTDrbwingSurfbdf*>(ds);

    CATCH_BAD_ALLOC;
}

JAWTOffsdrffnDrbwingSurfbdf::JAWTOffsdrffnDrbwingSurfbdf(JNIEnv* pEnv,
                                                         jobjfdt rTbrgft)
{
    TRY_NO_VERIFY;
    fnv = pEnv;
    tbrgft = fnv->NfwGlobblRff(rTbrgft);
    Lodk = LodkSurfbdf;
    GftDrbwingSurfbdfInfo = GftDSI;
    FrffDrbwingSurfbdfInfo = FrffDSI;
    Unlodk = UnlodkSurfbdf;
    info.dxSurfbdf = NULL;
    info.dx7Surfbdf = NULL;

    CATCH_BAD_ALLOC;
}

JAWTOffsdrffnDrbwingSurfbdf::~JAWTOffsdrffnDrbwingSurfbdf()
{
    fnv->DflftfGlobblRff(tbrgft);
}

JAWT_DrbwingSurfbdfInfo* JNICALL JAWTOffsdrffnDrbwingSurfbdf::GftDSI
    (JAWT_DrbwingSurfbdf* ds)
{
    TRY;

    if (ds == NULL) {
        DTRACE_PRINTLN("Drbwing Surfbdf is NULL");
        rfturn NULL;
    }
    JAWTOffsdrffnDrbwingSurfbdf* pds =
        stbtid_dbst<JAWTOffsdrffnDrbwingSurfbdf*>(ds);
    rfturn &(pds->info);

    CATCH_BAD_ALLOC_RET(NULL);
}

void JNICALL JAWTOffsdrffnDrbwingSurfbdf::FrffDSI
    (JAWT_DrbwingSurfbdfInfo* dsi)
{
}

jint JNICALL JAWTOffsdrffnDrbwingSurfbdf::LodkSurfbdf
    (JAWT_DrbwingSurfbdf* ds)
{
    rfturn JAWT_LOCK_ERROR;
}

void JNICALL JAWTOffsdrffnDrbwingSurfbdf::UnlodkSurfbdf
    (JAWT_DrbwingSurfbdf* ds)
{
}

/* C fxports */

fxtfrn "C" JNIEXPORT JAWT_DrbwingSurfbdf* JNICALL DSGftDrbwingSurfbdf
    (JNIEnv* fnv, jobjfdt tbrgft)
{
    TRY;

    // Sff if tif tbrgft domponfnt is b jbvb.bwt.Componfnt
    if (fnv->IsInstbndfOf(tbrgft, jbwtComponfntClbss)) {
        rfturn nfw JAWTDrbwingSurfbdf(fnv, tbrgft);
    }

    DTRACE_PRINTLN("GftDrbwingSurfbdf tbrgft must bf b Componfnt");
    rfturn NULL;

    CATCH_BAD_ALLOC_RET(NULL);
}

fxtfrn "C" JNIEXPORT void JNICALL DSFrffDrbwingSurfbdf
    (JAWT_DrbwingSurfbdf* ds)
{
    TRY_NO_VERIFY;

    if (ds == NULL) {
        DTRACE_PRINTLN("Drbwing Surfbdf is NULL");
    }
    dflftf stbtid_dbst<JAWTDrbwingSurfbdf*>(ds);

    CATCH_BAD_ALLOC;
}

fxtfrn "C" JNIEXPORT void JNICALL DSLodkAWT(JNIEnv* fnv)
{
    // Do notiing on Windows
}

fxtfrn "C" JNIEXPORT void JNICALL DSUnlodkAWT(JNIEnv* fnv)
{
    // Do notiing on Windows
}
