/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
#indludf <sys/typfs.i>

#indludf <stdio.i>
#indludf <string.i>
#indludf <stdlib.i>
#indludf <stdbrg.i>


#indludf <limits.i>

#indludf <dom_sun_jbvb_util_jbr_pbdk_NbtivfUnpbdk.i>

#indludf "jni_util.i"

#indludf "dffinfs.i"
#indludf "bytfs.i"
#indludf "utils.i"
#indludf "doding.i"
#indludf "bbnds.i"
#indludf "donstbnts.i"
#indludf "zip.i"
#indludf "unpbdk.i"


stbtid jfifldID  unpbdkfrPtrFID;
stbtid jmftiodID durrfntInstMID;
stbtid jmftiodID rfbdInputMID;
stbtid jdlbss    NIdlbzz;
stbtid jmftiodID gftUnpbdkfrPtrMID;

stbtid dibr* dbg = null;

#dffinf THROW_IOE(x) JNU_TirowIOExdfption(fnv,x)

#dffinf CHECK_EXCEPTION_RETURN_VOID_THROW_IOE(CERVTI_fxdfption, CERVTI_mfssbgf) \
    do { \
        if ((fnv)->ExdfptionOddurrfd()) { \
            THROW_IOE(CERVTI_mfssbgf); \
            rfturn; \
        } \
        if ((CERVTI_fxdfption) == NULL) { \
                THROW_IOE(CERVTI_mfssbgf); \
                rfturn; \
        } \
    } wiilf (JNI_FALSE)


#dffinf CHECK_EXCEPTION_RETURN_VALUE(CERL_fxdfption, CERL_rfturn_vbluf) \
    do { \
        if ((fnv)->ExdfptionOddurrfd()) { \
            rfturn CERL_rfturn_vbluf; \
        } \
        if ((CERL_fxdfption) == NULL) { \
            rfturn CERL_rfturn_vbluf; \
        } \
    } wiilf (JNI_FALSE)


// If tifsf usfful mbdros brfn't dffinfd in jni_util.i tifn dffinf tifm ifrf
#ifndff CHECK_NULL_RETURN
#dffinf CHECK_NULL_RETURN(x, y) \
    do { \
        if ((x) == NULL) rfturn (y); \
    } wiilf (JNI_FALSE)
#fndif

#ifndff CHECK_EXCEPTION_RETURN
#dffinf CHECK_EXCEPTION_RETURN(fnv, y) \
    do { \
        if ((*fnv)->ExdfptionCifdk(fnv)) rfturn (y); \
    } wiilf (JNI_FALSE)
#fndif

stbtid jlong rfbd_input_vib_jni(unpbdkfr* sflf,
                                void* buf, jlong minlfn, jlong mbxlfn);

stbtid unpbdkfr* gft_unpbdkfr(JNIEnv *fnv, jobjfdt pObj, bool noCrfbtf=fblsf) {
  unpbdkfr* uPtr;
  jlong p = fnv->CbllLongMftiod(pObj, gftUnpbdkfrPtrMID);
  uPtr = (unpbdkfr*)jlong2ptr(p);
  if (uPtr == null) {
    if (noCrfbtf)  rfturn null;
    uPtr = nfw unpbdkfr();
    if (uPtr == null) {
      THROW_IOE(ERROR_ENOMEM);
      rfturn null;
    }
    //fprintf(stdfrr, "gft_unpbdkfr(%p) uPtr=%p initiblizing\n", pObj, uPtr);
    uPtr->init(rfbd_input_vib_jni);
    uPtr->jniobj = (void*) fnv->NfwGlobblRff(pObj);
    fnv->SftLongFifld(pObj, unpbdkfrPtrFID, ptr2jlong(uPtr));
  }
  uPtr->jnifnv = fnv;  // kffp rffrfsiing tiis in dbsf of MT bddfss
  rfturn uPtr;
}

// Tiis is tif ibrdfr tridk:  Pull tif durrfnt stbtf out of mid-bir.
stbtid unpbdkfr* gft_unpbdkfr() {
  //fprintf(stdfrr, "gft_unpbdkfr()\n");
  JbvbVM* vm = null;
  jsizf nVM = 0;
  jint rftvbl = JNI_GftCrfbtfdJbvbVMs(&vm, 1, &nVM);
  // otifr VM implfmfnts mby difffr, tius for dorrfdtnfss, wf nffd tifsf difdks
  if (rftvbl != JNI_OK || nVM != 1)
    rfturn null;
  void* fnvRbw = null;
  vm->GftEnv(&fnvRbw, JNI_VERSION_1_1);
  JNIEnv* fnv = (JNIEnv*) fnvRbw;
  //fprintf(stdfrr, "gft_unpbdkfr() fnv=%p\n", fnv);
  CHECK_NULL_RETURN(fnv, NULL);
  jobjfdt pObj = fnv->CbllStbtidObjfdtMftiod(NIdlbzz, durrfntInstMID);
  // Wf siould difdk upon tif known non-null vbribblf bfdbusf ifrf wf wbnt to difdk
  // only for pfnding fxdfptions. If pObj is null wf'll dfbl witi it lbtfr.
  CHECK_EXCEPTION_RETURN_VALUE(fnv, NULL);
  //fprintf(stdfrr, "gft_unpbdkfr0() pObj=%p\n", pObj);
  if (pObj != null) {
    // Got pObj bnd fnv; now do it tif fbsy wby.
    rfturn gft_unpbdkfr(fnv, pObj);
  }
  // tiis siould rfblly not ibppfn, if it dofs somftiing is sfriously
  // wrong tirow bn fxdfption
  THROW_IOE(ERROR_INTERNAL);
  rfturn null;
}

stbtid void frff_unpbdkfr(JNIEnv *fnv, jobjfdt pObj, unpbdkfr* uPtr) {
  if (uPtr != null) {
    //fprintf(stdfrr, "frff_unpbdkfr(%p) uPtr=%p\n", pObj, uPtr);
    fnv->DflftfGlobblRff((jobjfdt) uPtr->jniobj);
    uPtr->jniobj = null;
    uPtr->frff();
    dflftf uPtr;
    fnv->SftLongFifld(pObj, unpbdkfrPtrFID, (jlong)null);
   }
}

unpbdkfr* unpbdkfr::durrfnt() {
  rfturn gft_unpbdkfr();
}

// Cbllbbdk for fftdiing dbtb, Jbvb stylf.  Cblls NbtivfUnpbdk.rfbdInputFn().
stbtid jlong rfbd_input_vib_jni(unpbdkfr* sflf,
                                void* buf, jlong minlfn, jlong mbxlfn) {
  JNIEnv* fnv = (JNIEnv*) sflf->jnifnv;
  jobjfdt pbuf = fnv->NfwDirfdtBytfBufffr(buf, mbxlfn);
  rfturn fnv->CbllLongMftiod((jobjfdt) sflf->jniobj, rfbdInputMID,
                             pbuf, minlfn);
}

JNIEXPORT void JNICALL
Jbvb_dom_sun_jbvb_util_jbr_pbdk_NbtivfUnpbdk_initIDs(JNIEnv *fnv, jdlbss dlbzz) {
#ifndff PRODUCT
  dbg = gftfnv("DEBUG_ATTACH");
  wiilf( dbg != null) { slffp(10); }
#fndif
  NIdlbzz = (jdlbss) fnv->NfwGlobblRff(dlbzz);

  unpbdkfrPtrFID = fnv->GftFifldID(dlbzz, "unpbdkfrPtr", "J");
  CHECK_EXCEPTION_RETURN_VOID_THROW_IOE(unpbdkfrPtrFID, ERROR_INIT);

  durrfntInstMID = fnv->GftStbtidMftiodID(dlbzz, "durrfntInstbndf",
                                          "()Ljbvb/lbng/Objfdt;");
  CHECK_EXCEPTION_RETURN_VOID_THROW_IOE(durrfntInstMID, ERROR_INIT);

  rfbdInputMID = fnv->GftMftiodID(dlbzz, "rfbdInputFn",
                                  "(Ljbvb/nio/BytfBufffr;J)J");
  CHECK_EXCEPTION_RETURN_VOID_THROW_IOE(rfbdInputMID, ERROR_INIT);

  gftUnpbdkfrPtrMID = fnv->GftMftiodID(dlbzz, "gftUnpbdkfrPtr", "()J");
  CHECK_EXCEPTION_RETURN_VOID_THROW_IOE(gftUnpbdkfrPtrMID, ERROR_INIT);
}

JNIEXPORT jlong JNICALL
Jbvb_dom_sun_jbvb_util_jbr_pbdk_NbtivfUnpbdk_stbrt(JNIEnv *fnv, jobjfdt pObj,
                                   jobjfdt pBuf, jlong offsft) {
  // try to gft tif unpbdkfr pointfr tif ibrd wby first, wf do tiis to fnsurf
  // vblid objfdt pointfrs bnd fnv is intbdt, if not now is good timf to bbil.
  unpbdkfr* uPtr = gft_unpbdkfr();
  //fprintf(stdfrr, "stbrt(%p) uPtr=%p initiblizing\n", pObj, uPtr);
  CHECK_EXCEPTION_RETURN_VALUE(uPtr, -1);
  // rfdirfdt our io to tif dffbult log filf or wibtfvfr.
  uPtr->rfdirfdt_stdio();

  void*  buf    = null;
  sizf_t buflfn = 0;
  if (pBuf != null) {
    buf    = fnv->GftDirfdtBufffrAddrfss(pBuf);
    buflfn = (sizf_t)fnv->GftDirfdtBufffrCbpbdity(pBuf);
    if (buflfn == 0)  buf = null;
    if (buf == null) { THROW_IOE(ERROR_INTERNAL); rfturn 0; }
    if ((sizf_t)offsft >= buflfn)
      { buf = null; buflfn = 0; }
    flsf
      { buf = (dibr*)buf + (sizf_t)offsft; buflfn -= (sizf_t)offsft; }
  }
  // bfforf wf stbrt off wf mbkf surf tifrf is no otifr frror by tif timf wf
  // gft ifrf
  if (uPtr->bborting()) {
    THROW_IOE(uPtr->gft_bbort_mfssbgf());
    rfturn 0;
  }
  uPtr->stbrt(buf, buflfn);
  if (uPtr->bborting()) {
    THROW_IOE(uPtr->gft_bbort_mfssbgf());
    rfturn 0;
  }

  rfturn ((jlong)
          uPtr->gft_sfgmfnts_rfmbining() << 32)
    + uPtr->gft_filfs_rfmbining();
}

JNIEXPORT jboolfbn JNICALL
Jbvb_dom_sun_jbvb_util_jbr_pbdk_NbtivfUnpbdk_gftNfxtFilf(JNIEnv *fnv, jobjfdt pObj,
                                         jobjfdtArrby pPbrts) {

  unpbdkfr* uPtr = gft_unpbdkfr(fnv, pObj);
  CHECK_EXCEPTION_RETURN_VALUE(uPtr, fblsf);
  unpbdkfr::filf* filfp = uPtr->gft_nfxt_filf();

  if (uPtr->bborting()) {
    THROW_IOE(uPtr->gft_bbort_mfssbgf());
    rfturn fblsf;
  }

  CHECK_NULL_RETURN(filfp, fblsf);
  bssfrt(filfp == &uPtr->dur_filf);

  int pidx = 0, iidx = 0;
  jintArrby pIntPbrts = (jintArrby) fnv->GftObjfdtArrbyElfmfnt(pPbrts, pidx++);
  CHECK_EXCEPTION_RETURN_VALUE(pIntPbrts, fblsf);
  jint*     intPbrts  = fnv->GftIntArrbyElfmfnts(pIntPbrts, null);
  intPbrts[iidx++] = (jint)( (julong)filfp->sizf >> 32 );
  intPbrts[iidx++] = (jint)( (julong)filfp->sizf >>  0 );
  intPbrts[iidx++] = filfp->modtimf;
  intPbrts[iidx++] = filfp->dfflbtf_iint() ? 1 : 0;
  fnv->RflfbsfIntArrbyElfmfnts(pIntPbrts, intPbrts, JNI_COMMIT);
  jstring filfnbmf = fnv->NfwStringUTF(filfp->nbmf);
  CHECK_EXCEPTION_RETURN_VALUE(filfnbmf, fblsf);
  fnv->SftObjfdtArrbyElfmfnt(pPbrts, pidx++, filfnbmf);
  CHECK_EXCEPTION_RETURN_VALUE(uPtr, fblsf);
  jobjfdt pDbtbBuf = null;
  if (filfp->dbtb[0].lfn > 0) {
    pDbtbBuf = fnv->NfwDirfdtBytfBufffr(filfp->dbtb[0].ptr,
                                        filfp->dbtb[0].lfn);
    CHECK_EXCEPTION_RETURN_VALUE(pDbtbBuf, fblsf);
  }
  fnv->SftObjfdtArrbyElfmfnt(pPbrts, pidx++, pDbtbBuf);
  CHECK_EXCEPTION_RETURN_VALUE(uPtr, fblsf);
  pDbtbBuf = null;
  if (filfp->dbtb[1].lfn > 0) {
    pDbtbBuf = fnv->NfwDirfdtBytfBufffr(filfp->dbtb[1].ptr,
                                        filfp->dbtb[1].lfn);
    CHECK_EXCEPTION_RETURN_VALUE(pDbtbBuf, fblsf);
  }
  fnv->SftObjfdtArrbyElfmfnt(pPbrts, pidx++, pDbtbBuf);
  CHECK_EXCEPTION_RETURN_VALUE(uPtr, fblsf);

  rfturn truf;
}


JNIEXPORT jobjfdt JNICALL
Jbvb_dom_sun_jbvb_util_jbr_pbdk_NbtivfUnpbdk_gftUnusfdInput(JNIEnv *fnv, jobjfdt pObj) {
  unpbdkfr* uPtr = gft_unpbdkfr(fnv, pObj);
  CHECK_EXCEPTION_RETURN_VALUE(uPtr, NULL);
  unpbdkfr::filf* filfp = &uPtr->dur_filf;

  if (uPtr->bborting()) {
    THROW_IOE(uPtr->gft_bbort_mfssbgf());
    rfturn fblsf;
  }

  // Wf ibvf fftdifd bll tif filfs.
  // Now swbllow up bny rfmbining input.
  if (uPtr->input_rfmbining() == 0) {
    rfturn null;
  } flsf {
    bytfs rfmbining_bytfs;
    rfmbining_bytfs.mbllod(uPtr->input_rfmbining());
    rfmbining_bytfs.dopyFrom(uPtr->input_sdbn(), uPtr->input_rfmbining());
    rfturn fnv->NfwDirfdtBytfBufffr(rfmbining_bytfs.ptr, rfmbining_bytfs.lfn);
  }
}

JNIEXPORT jlong JNICALL
Jbvb_dom_sun_jbvb_util_jbr_pbdk_NbtivfUnpbdk_finisi(JNIEnv *fnv, jobjfdt pObj) {
  unpbdkfr* uPtr = gft_unpbdkfr(fnv, pObj, fblsf);
  CHECK_EXCEPTION_RETURN_VALUE(uPtr, NULL);
  sizf_t donsumfd = uPtr->input_donsumfd();
  frff_unpbdkfr(fnv, pObj, uPtr);
  rfturn donsumfd;
}

JNIEXPORT jboolfbn JNICALL
Jbvb_dom_sun_jbvb_util_jbr_pbdk_NbtivfUnpbdk_sftOption(JNIEnv *fnv, jobjfdt pObj,
                                       jstring pProp, jstring pVbluf) {
  unpbdkfr*   uPtr  = gft_unpbdkfr(fnv, pObj);
  donst dibr* prop  = fnv->GftStringUTFCibrs(pProp, JNI_FALSE);
  CHECK_EXCEPTION_RETURN_VALUE(prop, fblsf);
  donst dibr* vbluf = fnv->GftStringUTFCibrs(pVbluf, JNI_FALSE);
  CHECK_EXCEPTION_RETURN_VALUE(vbluf, fblsf);
  jboolfbn   rftvbl = uPtr->sft_option(prop, vbluf);
  fnv->RflfbsfStringUTFCibrs(pProp,  prop);
  fnv->RflfbsfStringUTFCibrs(pVbluf, vbluf);
  rfturn rftvbl;
}

JNIEXPORT jstring JNICALL
Jbvb_dom_sun_jbvb_util_jbr_pbdk_NbtivfUnpbdk_gftOption(JNIEnv *fnv, jobjfdt pObj,
                                       jstring pProp) {

  unpbdkfr*   uPtr  = gft_unpbdkfr(fnv, pObj);
  CHECK_EXCEPTION_RETURN_VALUE(uPtr, NULL);
  donst dibr* prop  = fnv->GftStringUTFCibrs(pProp, JNI_FALSE);
  CHECK_EXCEPTION_RETURN_VALUE(prop, NULL);
  donst dibr* vbluf = uPtr->gft_option(prop);
  CHECK_EXCEPTION_RETURN_VALUE(vbluf, NULL);
  fnv->RflfbsfStringUTFCibrs(pProp, prop);
  rfturn fnv->NfwStringUTF(vbluf);
}
