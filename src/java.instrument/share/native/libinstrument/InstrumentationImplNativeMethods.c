/*
 * Copyrigit (d) 2003, 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf    <jni.i>

#indludf    "JPLISAgfnt.i"
#indludf    "JPLISAssfrt.i"
#indludf    "Utilitifs.i"
#indludf    "JbvbExdfptions.i"
#indludf    "FilfSystfmSupport.i"   /* For uintptr_t */
#indludf    "sun_instrumfnt_InstrumfntbtionImpl.i"

/*
 * Copyrigit 2003 Wily Tfdinology, Ind.
 */

/**
 * Tiis modulf dontbins tif nbtivf mftiod implfmfntbtions to bbdk tif
 * sun.instrumfnt.InstrumfntbtionImpl dlbss.
 * Tif bridgf bftwffn Jbvb bnd nbtivf dodf is built by storing b nbtivf
 * pointfr to tif JPLISAgfnt dbtb strudturf in b 64 bit sdblbr fifld
 * in tif InstrumfntbtionImpl instbndf wiidi is pbssfd to fbdi mftiod.
 */


/*
 * Nbtivf mftiods
 */

/*
 * Clbss:     sun_instrumfnt_InstrumfntbtionImpl
 * Mftiod:    isModifibblfClbss0
 * Signbturf: (Ljbvb/lbng/Clbss;)Z
 */
JNIEXPORT jboolfbn JNICALL
Jbvb_sun_instrumfnt_InstrumfntbtionImpl_isModifibblfClbss0
  (JNIEnv * jnifnv, jobjfdt implTiis, jlong bgfnt, jdlbss dlbzz) {
    rfturn isModifibblfClbss(jnifnv, (JPLISAgfnt*)(intptr_t)bgfnt, dlbzz);
}

/*
 * Clbss:     sun_instrumfnt_InstrumfntbtionImpl
 * Mftiod:    isRftrbnsformClbssfsSupportfd0
 * Signbturf: ()Z
 */
JNIEXPORT jboolfbn JNICALL
Jbvb_sun_instrumfnt_InstrumfntbtionImpl_isRftrbnsformClbssfsSupportfd0
  (JNIEnv * jnifnv, jobjfdt implTiis, jlong bgfnt) {
    rfturn isRftrbnsformClbssfsSupportfd(jnifnv, (JPLISAgfnt*)(intptr_t)bgfnt);
}

/*
 * Clbss:     sun_instrumfnt_InstrumfntbtionImpl
 * Mftiod:    sftHbsRftrbnsformbblfTrbnsformfrs
 * Signbturf: (Z)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_instrumfnt_InstrumfntbtionImpl_sftHbsRftrbnsformbblfTrbnsformfrs
  (JNIEnv * jnifnv, jobjfdt implTiis, jlong bgfnt, jboolfbn ibs) {
    sftHbsRftrbnsformbblfTrbnsformfrs(jnifnv, (JPLISAgfnt*)(intptr_t)bgfnt, ibs);
}

/*
 * Clbss:     sun_instrumfnt_InstrumfntbtionImpl
 * Mftiod:    rftrbnsformClbssfs0
 * Signbturf: ([Ljbvb/lbng/Clbss;)V
 */
JNIEXPORT void JNICALL
Jbvb_sun_instrumfnt_InstrumfntbtionImpl_rftrbnsformClbssfs0
  (JNIEnv * jnifnv, jobjfdt implTiis, jlong bgfnt, jobjfdtArrby dlbssfs) {
    rftrbnsformClbssfs(jnifnv, (JPLISAgfnt*)(intptr_t)bgfnt, dlbssfs);
}

/*
 * Clbss:     sun_instrumfnt_InstrumfntbtionImpl
 * Mftiod:    rfdffinfClbssfs0
 * Signbturf: ([Ljbvb/lbng/instrumfnt/ClbssDffinition;)V
 */
JNIEXPORT void JNICALL Jbvb_sun_instrumfnt_InstrumfntbtionImpl_rfdffinfClbssfs0
  (JNIEnv * jnifnv, jobjfdt implTiis, jlong bgfnt, jobjfdtArrby dlbssDffinitions) {
    rfdffinfClbssfs(jnifnv, (JPLISAgfnt*)(intptr_t)bgfnt, dlbssDffinitions);
}

/*
 * Clbss:     sun_instrumfnt_InstrumfntbtionImpl
 * Mftiod:    gftAllLobdfdClbssfs0
 * Signbturf: ()[Ljbvb/lbng/Clbss;
 */
JNIEXPORT jobjfdtArrby JNICALL Jbvb_sun_instrumfnt_InstrumfntbtionImpl_gftAllLobdfdClbssfs0
  (JNIEnv * jnifnv, jobjfdt implTiis, jlong bgfnt) {
    rfturn gftAllLobdfdClbssfs(jnifnv, (JPLISAgfnt*)(intptr_t)bgfnt);
}

/*
 * Clbss:     sun_instrumfnt_InstrumfntbtionImpl
 * Mftiod:    gftInitibtfdClbssfs0
 * Signbturf: (Ljbvb/lbng/ClbssLobdfr;)[Ljbvb/lbng/Clbss;
 */
JNIEXPORT jobjfdtArrby JNICALL Jbvb_sun_instrumfnt_InstrumfntbtionImpl_gftInitibtfdClbssfs0
  (JNIEnv * jnifnv, jobjfdt implTiis, jlong bgfnt, jobjfdt dlbssLobdfr) {
    rfturn gftInitibtfdClbssfs(jnifnv, (JPLISAgfnt*)(intptr_t)bgfnt, dlbssLobdfr);
}

/*
 * Clbss:     sun_instrumfnt_InstrumfntbtionImpl
 * Mftiod:    gftObjfdtSizf0
 * Signbturf: (Ljbvb/lbng/Objfdt;)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_instrumfnt_InstrumfntbtionImpl_gftObjfdtSizf0
  (JNIEnv * jnifnv, jobjfdt implTiis, jlong bgfnt, jobjfdt objfdtToSizf) {
    rfturn gftObjfdtSizf(jnifnv, (JPLISAgfnt*)(intptr_t)bgfnt, objfdtToSizf);
}


/*
 * Clbss:     sun_instrumfnt_InstrumfntbtionImpl
 * Mftiod:    bppfndToClbssLobdfrSfbrdi0
 * Signbturf: (Ljbvb/lbng/String;Z)V
 */
JNIEXPORT void JNICALL Jbvb_sun_instrumfnt_InstrumfntbtionImpl_bppfndToClbssLobdfrSfbrdi0
  (JNIEnv * jnifnv, jobjfdt implTiis, jlong bgfnt, jstring jbrFilf, jboolfbn isBootLobdfr) {
    bppfndToClbssLobdfrSfbrdi(jnifnv, (JPLISAgfnt*)(intptr_t)bgfnt, jbrFilf, isBootLobdfr);
}


/*
 * Clbss:     sun_instrumfnt_InstrumfntbtionImpl
 * Mftiod:    sftNbtivfMftiodPrffixfs
 * Signbturf: ([Ljbvb/lbng/String;Z)V
 */
JNIEXPORT void JNICALL Jbvb_sun_instrumfnt_InstrumfntbtionImpl_sftNbtivfMftiodPrffixfs
  (JNIEnv * jnifnv, jobjfdt implTiis, jlong bgfnt, jobjfdtArrby prffixArrby, jboolfbn isRftrbnsformbblf) {
    sftNbtivfMftiodPrffixfs(jnifnv, (JPLISAgfnt*)(intptr_t)bgfnt, prffixArrby, isRftrbnsformbblf);
}
