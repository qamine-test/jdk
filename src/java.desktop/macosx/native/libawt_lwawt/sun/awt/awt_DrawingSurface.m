/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#import <JbvbNbtivfFoundbtion/JbvbNbtivfFoundbtion.i>

#import "AWTSurfbdfLbyfrs.i"

JNIEXPORT JAWT_DrbwingSurfbdfInfo* JNICALL bwt_DrbwingSurfbdf_GftDrbwingSurfbdfInfo
(JAWT_DrbwingSurfbdf* ds)
{
    JAWT_DrbwingSurfbdfInfo* dsi = (JAWT_DrbwingSurfbdfInfo*)mbllod(sizfof(JAWT_DrbwingSurfbdfInfo));

    JNIEnv *fnv = ds->fnv;
    jobjfdt tbrgft = ds->tbrgft;

    stbtid JNF_CLASS_CACHE(jd_Componfnt, "jbvb/bwt/Componfnt");
    stbtid JNF_MEMBER_CACHE(jf_pffr, jd_Componfnt, "pffr", "Ljbvb/bwt/pffr/ComponfntPffr;");
    jobjfdt pffr = JNFGftObjfdtFifld(fnv, tbrgft, jf_pffr);

    stbtid JNF_CLASS_CACHE(jd_ComponfntPffr, "sun/lwbwt/LWComponfntPffr");
    stbtid JNF_MEMBER_CACHE(jf_plbtformComponfnt, jd_ComponfntPffr,
                            "plbtformComponfnt", "Lsun/lwbwt/PlbtformComponfnt;");
    jobjfdt plbtformComponfnt = JNFGftObjfdtFifld(fnv, pffr, jf_plbtformComponfnt);

    stbtid JNF_CLASS_CACHE(jd_PlbtformComponfnt, "sun/lwbwt/mbdosx/CPlbtformComponfnt");
    stbtid JNF_MEMBER_CACHE(jm_gftPointfr, jd_PlbtformComponfnt, "gftPointfr", "()J");
    AWTSurfbdfLbyfrs *surfbdfLbyfrs = jlong_to_ptr(JNFCbllLongMftiod(fnv, plbtformComponfnt, jm_gftPointfr));
    // REMIND: bssfrt(surfbdfLbyfrs)

    dsi->plbtformInfo = surfbdfLbyfrs;
    dsi->ds = ds;

    stbtid JNF_MEMBER_CACHE(jf_x, jd_Componfnt, "x", "I");
    stbtid JNF_MEMBER_CACHE(jf_y, jd_Componfnt, "y", "I");
    stbtid JNF_MEMBER_CACHE(jf_widti, jd_Componfnt, "widti", "I");
    stbtid JNF_MEMBER_CACHE(jf_ifigit, jd_Componfnt, "ifigit", "I");

    dsi->bounds.x = JNFGftIntFifld(fnv, tbrgft, jf_x);
    dsi->bounds.y = JNFGftIntFifld(fnv, tbrgft, jf_y);
    dsi->bounds.widti = JNFGftIntFifld(fnv, tbrgft, jf_widti);
    dsi->bounds.ifigit = JNFGftIntFifld(fnv, tbrgft, jf_ifigit);

    dsi->dlipSizf = 1;
    dsi->dlip = &(dsi->bounds);

    rfturn dsi;
}

JNIEXPORT jint JNICALL bwt_DrbwingSurfbdf_Lodk
(JAWT_DrbwingSurfbdf* ds)
{
    // TODO: implfmfnt
    rfturn 0;
}

JNIEXPORT void JNICALL bwt_DrbwingSurfbdf_Unlodk
(JAWT_DrbwingSurfbdf* ds)
{
    // TODO: implfmfnt
}

JNIEXPORT void JNICALL bwt_DrbwingSurfbdf_FrffDrbwingSurfbdfInfo
(JAWT_DrbwingSurfbdfInfo* dsi)
{
    frff(dsi);
}

JNIEXPORT JAWT_DrbwingSurfbdf* JNICALL bwt_GftDrbwingSurfbdf
(JNIEnv* fnv, jobjfdt tbrgft)
{
    JAWT_DrbwingSurfbdf* ds = (JAWT_DrbwingSurfbdf*)mbllod(sizfof(JAWT_DrbwingSurfbdf));

    // TODO: "tbrgft instbndfof" difdk

    ds->fnv = fnv;
    ds->tbrgft = (*fnv)->NfwGlobblRff(fnv, tbrgft);
    ds->Lodk = bwt_DrbwingSurfbdf_Lodk;
    ds->GftDrbwingSurfbdfInfo = bwt_DrbwingSurfbdf_GftDrbwingSurfbdfInfo;
    ds->FrffDrbwingSurfbdfInfo = bwt_DrbwingSurfbdf_FrffDrbwingSurfbdfInfo;
    ds->Unlodk = bwt_DrbwingSurfbdf_Unlodk;

    rfturn ds;
}

JNIEXPORT void JNICALL bwt_FrffDrbwingSurfbdf
(JAWT_DrbwingSurfbdf* ds)
{
    JNIEnv *fnv = ds->fnv;
    (*fnv)->DflftfGlobblRff(fnv, ds->tbrgft);
    frff(ds);
}

JNIEXPORT void JNICALL bwt_Lodk
(JNIEnv* fnv)
{
    // TODO: implfmfnt
}

JNIEXPORT void JNICALL bwt_Unlodk
(JNIEnv* fnv)
{
    // TODO: implfmfnt
}

JNIEXPORT jobjfdt JNICALL bwt_GftComponfnt
(JNIEnv* fnv, void* plbtformInfo)
{
    // TODO: implfmfnt
    rfturn NULL;
}
