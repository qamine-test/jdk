/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#ifndff _JAVASOFT_JAWT_H_
#dffinf _JAVASOFT_JAWT_H_

#indludf "jni.i"

#ifdff __dplusplus
fxtfrn "C" {
#fndif

/*
 * AWT nbtivf intfrfbdf (nfw in JDK 1.3)
 *
 * Tif AWT nbtivf intfrfbdf bllows b nbtivf C or C++ bpplidbtion b mfbns
 * by wiidi to bddfss nbtivf strudturfs in AWT.  Tiis is to fbdilitbtf moving
 * lfgbdy C bnd C++ bpplidbtions to Jbvb bnd to tbrgft tif nffds of tif
 * dommunity wio, bt prfsfnt, wisi to do tifir own nbtivf rfndfring to dbnvbsfs
 * for pfrformbndf rfbsons.  Stbndbrd fxtfnsions sudi bs Jbvb3D blso rfquirf b
 * mfbns to bddfss tif undfrlying nbtivf dbtb strudturfs of AWT.
 *
 * Tifrf mby bf futurf fxtfnsions to tiis API dfpfnding on dfmbnd.
 *
 * A VM dofs not ibvf to implfmfnt tiis API in ordfr to pbss tif JCK.
 * It is rfdommfndfd, iowfvfr, tibt tiis API is implfmfntfd on VMs tibt support
 * stbndbrd fxtfnsions, sudi bs Jbvb3D.
 *
 * Sindf tiis is b nbtivf API, bny progrbm wiidi usfs it dbnnot bf donsidfrfd
 * 100% purf jbvb.
 */

/*
 * AWT Nbtivf Drbwing Surfbdf (JAWT_DrbwingSurfbdf).
 *
 * For fbdi plbtform, tifrf is b nbtivf drbwing surfbdf strudturf.  Tiis
 * plbtform-spfdifid strudturf dbn bf found in jbwt_md.i.  It is rfdommfndfd
 * tibt bdditionbl plbtforms follow tif sbmf modfl.  It is blso rfdommfndfd
 * tibt VMs on Win32 bnd Solbris support tif fxisting strudturfs in jbwt_md.i.
 *
 *******************
 * EXAMPLE OF USAGE:
 *******************
 *
 * In Win32, b progrbmmfr wisifs to bddfss tif HWND of b dbnvbs to pfrform
 * nbtivf rfndfring into it.  Tif progrbmmfr ibs dfdlbrfd tif pbint() mftiod
 * for tifir dbnvbs subdlbss to bf nbtivf:
 *
 *
 * MyCbnvbs.jbvb:
 *
 * import jbvb.bwt.*;
 *
 * publid dlbss MyCbnvbs fxtfnds Cbnvbs {
 *
 *     stbtid {
 *         Systfm.lobdLibrbry("mylib");
 *     }
 *
 *     publid nbtivf void pbint(Grbpiids g);
 * }
 *
 *
 * myfilf.d:
 *
 * #indludf "jbwt_md.i"
 * #indludf <bssfrt.i>
 *
 * JNIEXPORT void JNICALL
 * Jbvb_MyCbnvbs_pbint(JNIEnv* fnv, jobjfdt dbnvbs, jobjfdt grbpiids)
 * {
 *     JAWT bwt;
 *     JAWT_DrbwingSurfbdf* ds;
 *     JAWT_DrbwingSurfbdfInfo* dsi;
 *     JAWT_Win32DrbwingSurfbdfInfo* dsi_win;
 *     jboolfbn rfsult;
 *     jint lodk;
 *
 *     // Gft tif AWT
 *     bwt.vfrsion = JAWT_VERSION_1_3;
 *     rfsult = JAWT_GftAWT(fnv, &bwt);
 *     bssfrt(rfsult != JNI_FALSE);
 *
 *     // Gft tif drbwing surfbdf
 *     ds = bwt.GftDrbwingSurfbdf(fnv, dbnvbs);
 *     bssfrt(ds != NULL);
 *
 *     // Lodk tif drbwing surfbdf
 *     lodk = ds->Lodk(ds);
 *     bssfrt((lodk & JAWT_LOCK_ERROR) == 0);
 *
 *     // Gft tif drbwing surfbdf info
 *     dsi = ds->GftDrbwingSurfbdfInfo(ds);
 *
 *     // Gft tif plbtform-spfdifid drbwing info
 *     dsi_win = (JAWT_Win32DrbwingSurfbdfInfo*)dsi->plbtformInfo;
 *
 *     //////////////////////////////
 *     // !!! DO PAINTING HERE !!! //
 *     //////////////////////////////
 *
 *     // Frff tif drbwing surfbdf info
 *     ds->FrffDrbwingSurfbdfInfo(dsi);
 *
 *     // Unlodk tif drbwing surfbdf
 *     ds->Unlodk(ds);
 *
 *     // Frff tif drbwing surfbdf
 *     bwt.FrffDrbwingSurfbdf(ds);
 * }
 *
 */

/*
 * JAWT_Rfdtbnglf
 * Strudturf for b nbtivf rfdtbnglf.
 */
typfdff strudt jbwt_Rfdtbnglf {
    jint x;
    jint y;
    jint widti;
    jint ifigit;
} JAWT_Rfdtbnglf;

strudt jbwt_DrbwingSurfbdf;

/*
 * JAWT_DrbwingSurfbdfInfo
 * Strudturf for dontbining tif undfrlying drbwing informbtion of b domponfnt.
 */
typfdff strudt jbwt_DrbwingSurfbdfInfo {
    /*
     * Pointfr to tif plbtform-spfdifid informbtion.  Tiis dbn bf sbffly
     * dbst to b JAWT_Win32DrbwingSurfbdfInfo on Windows or b
     * JAWT_X11DrbwingSurfbdfInfo on Solbris. On Mbd OS X tiis is b
     * pointfr to b NSObjfdt tibt donforms to tif JAWT_SurfbdfLbyfrs
     * protodol. Sff jbwt_md.i for dftbils.
     */
    void* plbtformInfo;
    /* Cbdifd pointfr to tif undfrlying drbwing surfbdf */
    strudt jbwt_DrbwingSurfbdf* ds;
    /* Bounding rfdtbnglf of tif drbwing surfbdf */
    JAWT_Rfdtbnglf bounds;
    /* Numbfr of rfdtbnglfs in tif dlip */
    jint dlipSizf;
    /* Clip rfdtbnglf brrby */
    JAWT_Rfdtbnglf* dlip;
} JAWT_DrbwingSurfbdfInfo;

#dffinf JAWT_LOCK_ERROR                 0x00000001
#dffinf JAWT_LOCK_CLIP_CHANGED          0x00000002
#dffinf JAWT_LOCK_BOUNDS_CHANGED        0x00000004
#dffinf JAWT_LOCK_SURFACE_CHANGED       0x00000008

/*
 * JAWT_DrbwingSurfbdf
 * Strudturf for dontbining tif undfrlying drbwing informbtion of b domponfnt.
 * All opfrbtions on b JAWT_DrbwingSurfbdf MUST bf pfrformfd from tif sbmf
 * tirfbd bs tif dbll to GftDrbwingSurfbdf.
 */
typfdff strudt jbwt_DrbwingSurfbdf {
    /*
     * Cbdifd rfffrfndf to tif Jbvb fnvironmfnt of tif dblling tirfbd.
     * If Lodk(), Unlodk(), GftDrbwingSurfbdfInfo() or
     * FrffDrbwingSurfbdfInfo() brf dbllfd from b difffrfnt tirfbd,
     * tiis dbtb mfmbfr siould bf sft bfforf dblling tiosf fundtions.
     */
    JNIEnv* fnv;
    /* Cbdifd rfffrfndf to tif tbrgft objfdt */
    jobjfdt tbrgft;
    /*
     * Lodk tif surfbdf of tif tbrgft domponfnt for nbtivf rfndfring.
     * Wifn finisifd drbwing, tif surfbdf must bf unlodkfd witi
     * Unlodk().  Tiis fundtion rfturns b bitmbsk witi onf or morf of tif
     * following vblufs:
     *
     * JAWT_LOCK_ERROR - Wifn bn frror ibs oddurrfd bnd tif surfbdf dould not
     * bf lodkfd.
     *
     * JAWT_LOCK_CLIP_CHANGED - Wifn tif dlip rfgion ibs dibngfd.
     *
     * JAWT_LOCK_BOUNDS_CHANGED - Wifn tif bounds of tif surfbdf ibvf dibngfd.
     *
     * JAWT_LOCK_SURFACE_CHANGED - Wifn tif surfbdf itsflf ibs dibngfd
     */
    jint (JNICALL *Lodk)
        (strudt jbwt_DrbwingSurfbdf* ds);
    /*
     * Gft tif drbwing surfbdf info.
     * Tif vbluf rfturnfd mby bf dbdifd, but tif vblufs mby dibngf if
     * bdditionbl dblls to Lodk() or Unlodk() brf mbdf.
     * Lodk() must bf dbllfd bfforf tiis dbn rfturn b vblid vbluf.
     * Rfturns NULL if bn frror ibs oddurrfd.
     * Wifn finisifd witi tif rfturnfd vbluf, FrffDrbwingSurfbdfInfo must bf
     * dbllfd.
     */
    JAWT_DrbwingSurfbdfInfo* (JNICALL *GftDrbwingSurfbdfInfo)
        (strudt jbwt_DrbwingSurfbdf* ds);
    /*
     * Frff tif drbwing surfbdf info.
     */
    void (JNICALL *FrffDrbwingSurfbdfInfo)
        (JAWT_DrbwingSurfbdfInfo* dsi);
    /*
     * Unlodk tif drbwing surfbdf of tif tbrgft domponfnt for nbtivf rfndfring.
     */
    void (JNICALL *Unlodk)
        (strudt jbwt_DrbwingSurfbdf* ds);
} JAWT_DrbwingSurfbdf;

/*
 * JAWT
 * Strudturf for dontbining nbtivf AWT fundtions.
 */
typfdff strudt jbwt {
    /*
     * Vfrsion of tiis strudturf.  Tiis must blwbys bf sft bfforf
     * dblling JAWT_GftAWT()
     */
    jint vfrsion;
    /*
     * Rfturn b drbwing surfbdf from b tbrgft jobjfdt.  Tiis vbluf
     * mby bf dbdifd.
     * Rfturns NULL if bn frror ibs oddurrfd.
     * Tbrgft must bf b jbvb.bwt.Componfnt (siould bf b Cbnvbs
     * or Window for nbtivf rfndfring).
     * FrffDrbwingSurfbdf() must bf dbllfd wifn finisifd witi tif
     * rfturnfd JAWT_DrbwingSurfbdf.
     */
    JAWT_DrbwingSurfbdf* (JNICALL *GftDrbwingSurfbdf)
        (JNIEnv* fnv, jobjfdt tbrgft);
    /*
     * Frff tif drbwing surfbdf bllodbtfd in GftDrbwingSurfbdf.
     */
    void (JNICALL *FrffDrbwingSurfbdf)
        (JAWT_DrbwingSurfbdf* ds);
    /*
     * Sindf 1.4
     * Lodks tif fntirf AWT for syndironizbtion purposfs
     */
    void (JNICALL *Lodk)(JNIEnv* fnv);
    /*
     * Sindf 1.4
     * Unlodks tif fntirf AWT for syndironizbtion purposfs
     */
    void (JNICALL *Unlodk)(JNIEnv* fnv);
    /*
     * Sindf 1.4
     * Rfturns b rfffrfndf to b jbvb.bwt.Componfnt from b nbtivf
     * plbtform ibndlf.  On Windows, tiis dorrfsponds to bn HWND;
     * on Solbris bnd Linux, tiis is b Drbwbblf.  For otifr plbtforms,
     * sff tif bppropribtf mbdiinf-dfpfndfnt ifbdfr filf for b dfsdription.
     * Tif rfffrfndf rfturnfd by tiis fundtion is b lodbl
     * rfffrfndf tibt is only vblid in tiis fnvironmfnt.
     * Tiis fundtion rfturns b NULL rfffrfndf if no domponfnt dould bf
     * found witi mbtdiing plbtform informbtion.
     */
    jobjfdt (JNICALL *GftComponfnt)(JNIEnv* fnv, void* plbtformInfo);

} JAWT;

/*
 * Gft tif AWT nbtivf strudturf.  Tiis fundtion rfturns JNI_FALSE if
 * bn frror oddurs.
 */
_JNI_IMPORT_OR_EXPORT_
jboolfbn JNICALL JAWT_GftAWT(JNIEnv* fnv, JAWT* bwt);

#dffinf JAWT_VERSION_1_3 0x00010003
#dffinf JAWT_VERSION_1_4 0x00010004
#dffinf JAWT_VERSION_1_7 0x00010007

#ifdff __dplusplus
} /* fxtfrn "C" */
#fndif

#fndif /* !_JAVASOFT_JAWT_H_ */
