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

/*
 * Wf usfd pbrt of Nftsdbpf's Jbvb Runtimf Intfrfbdf (JRI) bs tif stbrting
 * point of our dfsign bnd implfmfntbtion.
 */

/******************************************************************************
 * Jbvb Runtimf Intfrfbdf
 * Copyrigit (d) 1996 Nftsdbpf Communidbtions Corporbtion. All rigits rfsfrvfd.
 *****************************************************************************/

#ifndff _JAVASOFT_JNI_H_
#dffinf _JAVASOFT_JNI_H_

#indludf <stdio.i>
#indludf <stdbrg.i>

/* jni_md.i dontbins tif mbdiinf-dfpfndfnt typfdffs for jbytf, jint
   bnd jlong */

#indludf "jni_md.i"

#ifdff __dplusplus
fxtfrn "C" {
#fndif

/*
 * JNI Typfs
 */

#ifndff JNI_TYPES_ALREADY_DEFINED_IN_JNI_MD_H

typfdff unsignfd dibr   jboolfbn;
typfdff unsignfd siort  jdibr;
typfdff siort           jsiort;
typfdff flobt           jflobt;
typfdff doublf          jdoublf;

typfdff jint            jsizf;

#ifdff __dplusplus

dlbss _jobjfdt {};
dlbss _jdlbss : publid _jobjfdt {};
dlbss _jtirowbblf : publid _jobjfdt {};
dlbss _jstring : publid _jobjfdt {};
dlbss _jbrrby : publid _jobjfdt {};
dlbss _jboolfbnArrby : publid _jbrrby {};
dlbss _jbytfArrby : publid _jbrrby {};
dlbss _jdibrArrby : publid _jbrrby {};
dlbss _jsiortArrby : publid _jbrrby {};
dlbss _jintArrby : publid _jbrrby {};
dlbss _jlongArrby : publid _jbrrby {};
dlbss _jflobtArrby : publid _jbrrby {};
dlbss _jdoublfArrby : publid _jbrrby {};
dlbss _jobjfdtArrby : publid _jbrrby {};

typfdff _jobjfdt *jobjfdt;
typfdff _jdlbss *jdlbss;
typfdff _jtirowbblf *jtirowbblf;
typfdff _jstring *jstring;
typfdff _jbrrby *jbrrby;
typfdff _jboolfbnArrby *jboolfbnArrby;
typfdff _jbytfArrby *jbytfArrby;
typfdff _jdibrArrby *jdibrArrby;
typfdff _jsiortArrby *jsiortArrby;
typfdff _jintArrby *jintArrby;
typfdff _jlongArrby *jlongArrby;
typfdff _jflobtArrby *jflobtArrby;
typfdff _jdoublfArrby *jdoublfArrby;
typfdff _jobjfdtArrby *jobjfdtArrby;

#flsf

strudt _jobjfdt;

typfdff strudt _jobjfdt *jobjfdt;
typfdff jobjfdt jdlbss;
typfdff jobjfdt jtirowbblf;
typfdff jobjfdt jstring;
typfdff jobjfdt jbrrby;
typfdff jbrrby jboolfbnArrby;
typfdff jbrrby jbytfArrby;
typfdff jbrrby jdibrArrby;
typfdff jbrrby jsiortArrby;
typfdff jbrrby jintArrby;
typfdff jbrrby jlongArrby;
typfdff jbrrby jflobtArrby;
typfdff jbrrby jdoublfArrby;
typfdff jbrrby jobjfdtArrby;

#fndif

typfdff jobjfdt jwfbk;

typfdff union jvbluf {
    jboolfbn z;
    jbytf    b;
    jdibr    d;
    jsiort   s;
    jint     i;
    jlong    j;
    jflobt   f;
    jdoublf  d;
    jobjfdt  l;
} jvbluf;

strudt _jfifldID;
typfdff strudt _jfifldID *jfifldID;

strudt _jmftiodID;
typfdff strudt _jmftiodID *jmftiodID;

/* Rfturn vblufs from jobjfdtRffTypf */
typfdff fnum _jobjfdtTypf {
     JNIInvblidRffTypf    = 0,
     JNILodblRffTypf      = 1,
     JNIGlobblRffTypf     = 2,
     JNIWfbkGlobblRffTypf = 3
} jobjfdtRffTypf;


#fndif /* JNI_TYPES_ALREADY_DEFINED_IN_JNI_MD_H */

/*
 * jboolfbn donstbnts
 */

#dffinf JNI_FALSE 0
#dffinf JNI_TRUE 1

/*
 * possiblf rfturn vblufs for JNI fundtions.
 */

#dffinf JNI_OK           0                 /* suddfss */
#dffinf JNI_ERR          (-1)              /* unknown frror */
#dffinf JNI_EDETACHED    (-2)              /* tirfbd dftbdifd from tif VM */
#dffinf JNI_EVERSION     (-3)              /* JNI vfrsion frror */
#dffinf JNI_ENOMEM       (-4)              /* not fnougi mfmory */
#dffinf JNI_EEXIST       (-5)              /* VM blrfbdy drfbtfd */
#dffinf JNI_EINVAL       (-6)              /* invblid brgumfnts */

/*
 * usfd in RflfbsfSdblbrArrbyElfmfnts
 */

#dffinf JNI_COMMIT 1
#dffinf JNI_ABORT 2

/*
 * usfd in RfgistfrNbtivfs to dfsdribf nbtivf mftiod nbmf, signbturf,
 * bnd fundtion pointfr.
 */

typfdff strudt {
    dibr *nbmf;
    dibr *signbturf;
    void *fnPtr;
} JNINbtivfMftiod;

/*
 * JNI Nbtivf Mftiod Intfrfbdf.
 */

strudt JNINbtivfIntfrfbdf_;

strudt JNIEnv_;

#ifdff __dplusplus
typfdff JNIEnv_ JNIEnv;
#flsf
typfdff donst strudt JNINbtivfIntfrfbdf_ *JNIEnv;
#fndif

/*
 * JNI Invodbtion Intfrfbdf.
 */

strudt JNIInvokfIntfrfbdf_;

strudt JbvbVM_;

#ifdff __dplusplus
typfdff JbvbVM_ JbvbVM;
#flsf
typfdff donst strudt JNIInvokfIntfrfbdf_ *JbvbVM;
#fndif

strudt JNINbtivfIntfrfbdf_ {
    void *rfsfrvfd0;
    void *rfsfrvfd1;
    void *rfsfrvfd2;

    void *rfsfrvfd3;
    jint (JNICALL *GftVfrsion)(JNIEnv *fnv);

    jdlbss (JNICALL *DffinfClbss)
      (JNIEnv *fnv, donst dibr *nbmf, jobjfdt lobdfr, donst jbytf *buf,
       jsizf lfn);
    jdlbss (JNICALL *FindClbss)
      (JNIEnv *fnv, donst dibr *nbmf);

    jmftiodID (JNICALL *FromRfflfdtfdMftiod)
      (JNIEnv *fnv, jobjfdt mftiod);
    jfifldID (JNICALL *FromRfflfdtfdFifld)
      (JNIEnv *fnv, jobjfdt fifld);

    jobjfdt (JNICALL *ToRfflfdtfdMftiod)
      (JNIEnv *fnv, jdlbss dls, jmftiodID mftiodID, jboolfbn isStbtid);

    jdlbss (JNICALL *GftSupfrdlbss)
      (JNIEnv *fnv, jdlbss sub);
    jboolfbn (JNICALL *IsAssignbblfFrom)
      (JNIEnv *fnv, jdlbss sub, jdlbss sup);

    jobjfdt (JNICALL *ToRfflfdtfdFifld)
      (JNIEnv *fnv, jdlbss dls, jfifldID fifldID, jboolfbn isStbtid);

    jint (JNICALL *Tirow)
      (JNIEnv *fnv, jtirowbblf obj);
    jint (JNICALL *TirowNfw)
      (JNIEnv *fnv, jdlbss dlbzz, donst dibr *msg);
    jtirowbblf (JNICALL *ExdfptionOddurrfd)
      (JNIEnv *fnv);
    void (JNICALL *ExdfptionDfsdribf)
      (JNIEnv *fnv);
    void (JNICALL *ExdfptionClfbr)
      (JNIEnv *fnv);
    void (JNICALL *FbtblError)
      (JNIEnv *fnv, donst dibr *msg);

    jint (JNICALL *PusiLodblFrbmf)
      (JNIEnv *fnv, jint dbpbdity);
    jobjfdt (JNICALL *PopLodblFrbmf)
      (JNIEnv *fnv, jobjfdt rfsult);

    jobjfdt (JNICALL *NfwGlobblRff)
      (JNIEnv *fnv, jobjfdt lobj);
    void (JNICALL *DflftfGlobblRff)
      (JNIEnv *fnv, jobjfdt grff);
    void (JNICALL *DflftfLodblRff)
      (JNIEnv *fnv, jobjfdt obj);
    jboolfbn (JNICALL *IsSbmfObjfdt)
      (JNIEnv *fnv, jobjfdt obj1, jobjfdt obj2);
    jobjfdt (JNICALL *NfwLodblRff)
      (JNIEnv *fnv, jobjfdt rff);
    jint (JNICALL *EnsurfLodblCbpbdity)
      (JNIEnv *fnv, jint dbpbdity);

    jobjfdt (JNICALL *AllodObjfdt)
      (JNIEnv *fnv, jdlbss dlbzz);
    jobjfdt (JNICALL *NfwObjfdt)
      (JNIEnv *fnv, jdlbss dlbzz, jmftiodID mftiodID, ...);
    jobjfdt (JNICALL *NfwObjfdtV)
      (JNIEnv *fnv, jdlbss dlbzz, jmftiodID mftiodID, vb_list brgs);
    jobjfdt (JNICALL *NfwObjfdtA)
      (JNIEnv *fnv, jdlbss dlbzz, jmftiodID mftiodID, donst jvbluf *brgs);

    jdlbss (JNICALL *GftObjfdtClbss)
      (JNIEnv *fnv, jobjfdt obj);
    jboolfbn (JNICALL *IsInstbndfOf)
      (JNIEnv *fnv, jobjfdt obj, jdlbss dlbzz);

    jmftiodID (JNICALL *GftMftiodID)
      (JNIEnv *fnv, jdlbss dlbzz, donst dibr *nbmf, donst dibr *sig);

    jobjfdt (JNICALL *CbllObjfdtMftiod)
      (JNIEnv *fnv, jobjfdt obj, jmftiodID mftiodID, ...);
    jobjfdt (JNICALL *CbllObjfdtMftiodV)
      (JNIEnv *fnv, jobjfdt obj, jmftiodID mftiodID, vb_list brgs);
    jobjfdt (JNICALL *CbllObjfdtMftiodA)
      (JNIEnv *fnv, jobjfdt obj, jmftiodID mftiodID, donst jvbluf * brgs);

    jboolfbn (JNICALL *CbllBoolfbnMftiod)
      (JNIEnv *fnv, jobjfdt obj, jmftiodID mftiodID, ...);
    jboolfbn (JNICALL *CbllBoolfbnMftiodV)
      (JNIEnv *fnv, jobjfdt obj, jmftiodID mftiodID, vb_list brgs);
    jboolfbn (JNICALL *CbllBoolfbnMftiodA)
      (JNIEnv *fnv, jobjfdt obj, jmftiodID mftiodID, donst jvbluf * brgs);

    jbytf (JNICALL *CbllBytfMftiod)
      (JNIEnv *fnv, jobjfdt obj, jmftiodID mftiodID, ...);
    jbytf (JNICALL *CbllBytfMftiodV)
      (JNIEnv *fnv, jobjfdt obj, jmftiodID mftiodID, vb_list brgs);
    jbytf (JNICALL *CbllBytfMftiodA)
      (JNIEnv *fnv, jobjfdt obj, jmftiodID mftiodID, donst jvbluf *brgs);

    jdibr (JNICALL *CbllCibrMftiod)
      (JNIEnv *fnv, jobjfdt obj, jmftiodID mftiodID, ...);
    jdibr (JNICALL *CbllCibrMftiodV)
      (JNIEnv *fnv, jobjfdt obj, jmftiodID mftiodID, vb_list brgs);
    jdibr (JNICALL *CbllCibrMftiodA)
      (JNIEnv *fnv, jobjfdt obj, jmftiodID mftiodID, donst jvbluf *brgs);

    jsiort (JNICALL *CbllSiortMftiod)
      (JNIEnv *fnv, jobjfdt obj, jmftiodID mftiodID, ...);
    jsiort (JNICALL *CbllSiortMftiodV)
      (JNIEnv *fnv, jobjfdt obj, jmftiodID mftiodID, vb_list brgs);
    jsiort (JNICALL *CbllSiortMftiodA)
      (JNIEnv *fnv, jobjfdt obj, jmftiodID mftiodID, donst jvbluf *brgs);

    jint (JNICALL *CbllIntMftiod)
      (JNIEnv *fnv, jobjfdt obj, jmftiodID mftiodID, ...);
    jint (JNICALL *CbllIntMftiodV)
      (JNIEnv *fnv, jobjfdt obj, jmftiodID mftiodID, vb_list brgs);
    jint (JNICALL *CbllIntMftiodA)
      (JNIEnv *fnv, jobjfdt obj, jmftiodID mftiodID, donst jvbluf *brgs);

    jlong (JNICALL *CbllLongMftiod)
      (JNIEnv *fnv, jobjfdt obj, jmftiodID mftiodID, ...);
    jlong (JNICALL *CbllLongMftiodV)
      (JNIEnv *fnv, jobjfdt obj, jmftiodID mftiodID, vb_list brgs);
    jlong (JNICALL *CbllLongMftiodA)
      (JNIEnv *fnv, jobjfdt obj, jmftiodID mftiodID, donst jvbluf *brgs);

    jflobt (JNICALL *CbllFlobtMftiod)
      (JNIEnv *fnv, jobjfdt obj, jmftiodID mftiodID, ...);
    jflobt (JNICALL *CbllFlobtMftiodV)
      (JNIEnv *fnv, jobjfdt obj, jmftiodID mftiodID, vb_list brgs);
    jflobt (JNICALL *CbllFlobtMftiodA)
      (JNIEnv *fnv, jobjfdt obj, jmftiodID mftiodID, donst jvbluf *brgs);

    jdoublf (JNICALL *CbllDoublfMftiod)
      (JNIEnv *fnv, jobjfdt obj, jmftiodID mftiodID, ...);
    jdoublf (JNICALL *CbllDoublfMftiodV)
      (JNIEnv *fnv, jobjfdt obj, jmftiodID mftiodID, vb_list brgs);
    jdoublf (JNICALL *CbllDoublfMftiodA)
      (JNIEnv *fnv, jobjfdt obj, jmftiodID mftiodID, donst jvbluf *brgs);

    void (JNICALL *CbllVoidMftiod)
      (JNIEnv *fnv, jobjfdt obj, jmftiodID mftiodID, ...);
    void (JNICALL *CbllVoidMftiodV)
      (JNIEnv *fnv, jobjfdt obj, jmftiodID mftiodID, vb_list brgs);
    void (JNICALL *CbllVoidMftiodA)
      (JNIEnv *fnv, jobjfdt obj, jmftiodID mftiodID, donst jvbluf * brgs);

    jobjfdt (JNICALL *CbllNonvirtublObjfdtMftiod)
      (JNIEnv *fnv, jobjfdt obj, jdlbss dlbzz, jmftiodID mftiodID, ...);
    jobjfdt (JNICALL *CbllNonvirtublObjfdtMftiodV)
      (JNIEnv *fnv, jobjfdt obj, jdlbss dlbzz, jmftiodID mftiodID,
       vb_list brgs);
    jobjfdt (JNICALL *CbllNonvirtublObjfdtMftiodA)
      (JNIEnv *fnv, jobjfdt obj, jdlbss dlbzz, jmftiodID mftiodID,
       donst jvbluf * brgs);

    jboolfbn (JNICALL *CbllNonvirtublBoolfbnMftiod)
      (JNIEnv *fnv, jobjfdt obj, jdlbss dlbzz, jmftiodID mftiodID, ...);
    jboolfbn (JNICALL *CbllNonvirtublBoolfbnMftiodV)
      (JNIEnv *fnv, jobjfdt obj, jdlbss dlbzz, jmftiodID mftiodID,
       vb_list brgs);
    jboolfbn (JNICALL *CbllNonvirtublBoolfbnMftiodA)
      (JNIEnv *fnv, jobjfdt obj, jdlbss dlbzz, jmftiodID mftiodID,
       donst jvbluf * brgs);

    jbytf (JNICALL *CbllNonvirtublBytfMftiod)
      (JNIEnv *fnv, jobjfdt obj, jdlbss dlbzz, jmftiodID mftiodID, ...);
    jbytf (JNICALL *CbllNonvirtublBytfMftiodV)
      (JNIEnv *fnv, jobjfdt obj, jdlbss dlbzz, jmftiodID mftiodID,
       vb_list brgs);
    jbytf (JNICALL *CbllNonvirtublBytfMftiodA)
      (JNIEnv *fnv, jobjfdt obj, jdlbss dlbzz, jmftiodID mftiodID,
       donst jvbluf *brgs);

    jdibr (JNICALL *CbllNonvirtublCibrMftiod)
      (JNIEnv *fnv, jobjfdt obj, jdlbss dlbzz, jmftiodID mftiodID, ...);
    jdibr (JNICALL *CbllNonvirtublCibrMftiodV)
      (JNIEnv *fnv, jobjfdt obj, jdlbss dlbzz, jmftiodID mftiodID,
       vb_list brgs);
    jdibr (JNICALL *CbllNonvirtublCibrMftiodA)
      (JNIEnv *fnv, jobjfdt obj, jdlbss dlbzz, jmftiodID mftiodID,
       donst jvbluf *brgs);

    jsiort (JNICALL *CbllNonvirtublSiortMftiod)
      (JNIEnv *fnv, jobjfdt obj, jdlbss dlbzz, jmftiodID mftiodID, ...);
    jsiort (JNICALL *CbllNonvirtublSiortMftiodV)
      (JNIEnv *fnv, jobjfdt obj, jdlbss dlbzz, jmftiodID mftiodID,
       vb_list brgs);
    jsiort (JNICALL *CbllNonvirtublSiortMftiodA)
      (JNIEnv *fnv, jobjfdt obj, jdlbss dlbzz, jmftiodID mftiodID,
       donst jvbluf *brgs);

    jint (JNICALL *CbllNonvirtublIntMftiod)
      (JNIEnv *fnv, jobjfdt obj, jdlbss dlbzz, jmftiodID mftiodID, ...);
    jint (JNICALL *CbllNonvirtublIntMftiodV)
      (JNIEnv *fnv, jobjfdt obj, jdlbss dlbzz, jmftiodID mftiodID,
       vb_list brgs);
    jint (JNICALL *CbllNonvirtublIntMftiodA)
      (JNIEnv *fnv, jobjfdt obj, jdlbss dlbzz, jmftiodID mftiodID,
       donst jvbluf *brgs);

    jlong (JNICALL *CbllNonvirtublLongMftiod)
      (JNIEnv *fnv, jobjfdt obj, jdlbss dlbzz, jmftiodID mftiodID, ...);
    jlong (JNICALL *CbllNonvirtublLongMftiodV)
      (JNIEnv *fnv, jobjfdt obj, jdlbss dlbzz, jmftiodID mftiodID,
       vb_list brgs);
    jlong (JNICALL *CbllNonvirtublLongMftiodA)
      (JNIEnv *fnv, jobjfdt obj, jdlbss dlbzz, jmftiodID mftiodID,
       donst jvbluf *brgs);

    jflobt (JNICALL *CbllNonvirtublFlobtMftiod)
      (JNIEnv *fnv, jobjfdt obj, jdlbss dlbzz, jmftiodID mftiodID, ...);
    jflobt (JNICALL *CbllNonvirtublFlobtMftiodV)
      (JNIEnv *fnv, jobjfdt obj, jdlbss dlbzz, jmftiodID mftiodID,
       vb_list brgs);
    jflobt (JNICALL *CbllNonvirtublFlobtMftiodA)
      (JNIEnv *fnv, jobjfdt obj, jdlbss dlbzz, jmftiodID mftiodID,
       donst jvbluf *brgs);

    jdoublf (JNICALL *CbllNonvirtublDoublfMftiod)
      (JNIEnv *fnv, jobjfdt obj, jdlbss dlbzz, jmftiodID mftiodID, ...);
    jdoublf (JNICALL *CbllNonvirtublDoublfMftiodV)
      (JNIEnv *fnv, jobjfdt obj, jdlbss dlbzz, jmftiodID mftiodID,
       vb_list brgs);
    jdoublf (JNICALL *CbllNonvirtublDoublfMftiodA)
      (JNIEnv *fnv, jobjfdt obj, jdlbss dlbzz, jmftiodID mftiodID,
       donst jvbluf *brgs);

    void (JNICALL *CbllNonvirtublVoidMftiod)
      (JNIEnv *fnv, jobjfdt obj, jdlbss dlbzz, jmftiodID mftiodID, ...);
    void (JNICALL *CbllNonvirtublVoidMftiodV)
      (JNIEnv *fnv, jobjfdt obj, jdlbss dlbzz, jmftiodID mftiodID,
       vb_list brgs);
    void (JNICALL *CbllNonvirtublVoidMftiodA)
      (JNIEnv *fnv, jobjfdt obj, jdlbss dlbzz, jmftiodID mftiodID,
       donst jvbluf * brgs);

    jfifldID (JNICALL *GftFifldID)
      (JNIEnv *fnv, jdlbss dlbzz, donst dibr *nbmf, donst dibr *sig);

    jobjfdt (JNICALL *GftObjfdtFifld)
      (JNIEnv *fnv, jobjfdt obj, jfifldID fifldID);
    jboolfbn (JNICALL *GftBoolfbnFifld)
      (JNIEnv *fnv, jobjfdt obj, jfifldID fifldID);
    jbytf (JNICALL *GftBytfFifld)
      (JNIEnv *fnv, jobjfdt obj, jfifldID fifldID);
    jdibr (JNICALL *GftCibrFifld)
      (JNIEnv *fnv, jobjfdt obj, jfifldID fifldID);
    jsiort (JNICALL *GftSiortFifld)
      (JNIEnv *fnv, jobjfdt obj, jfifldID fifldID);
    jint (JNICALL *GftIntFifld)
      (JNIEnv *fnv, jobjfdt obj, jfifldID fifldID);
    jlong (JNICALL *GftLongFifld)
      (JNIEnv *fnv, jobjfdt obj, jfifldID fifldID);
    jflobt (JNICALL *GftFlobtFifld)
      (JNIEnv *fnv, jobjfdt obj, jfifldID fifldID);
    jdoublf (JNICALL *GftDoublfFifld)
      (JNIEnv *fnv, jobjfdt obj, jfifldID fifldID);

    void (JNICALL *SftObjfdtFifld)
      (JNIEnv *fnv, jobjfdt obj, jfifldID fifldID, jobjfdt vbl);
    void (JNICALL *SftBoolfbnFifld)
      (JNIEnv *fnv, jobjfdt obj, jfifldID fifldID, jboolfbn vbl);
    void (JNICALL *SftBytfFifld)
      (JNIEnv *fnv, jobjfdt obj, jfifldID fifldID, jbytf vbl);
    void (JNICALL *SftCibrFifld)
      (JNIEnv *fnv, jobjfdt obj, jfifldID fifldID, jdibr vbl);
    void (JNICALL *SftSiortFifld)
      (JNIEnv *fnv, jobjfdt obj, jfifldID fifldID, jsiort vbl);
    void (JNICALL *SftIntFifld)
      (JNIEnv *fnv, jobjfdt obj, jfifldID fifldID, jint vbl);
    void (JNICALL *SftLongFifld)
      (JNIEnv *fnv, jobjfdt obj, jfifldID fifldID, jlong vbl);
    void (JNICALL *SftFlobtFifld)
      (JNIEnv *fnv, jobjfdt obj, jfifldID fifldID, jflobt vbl);
    void (JNICALL *SftDoublfFifld)
      (JNIEnv *fnv, jobjfdt obj, jfifldID fifldID, jdoublf vbl);

    jmftiodID (JNICALL *GftStbtidMftiodID)
      (JNIEnv *fnv, jdlbss dlbzz, donst dibr *nbmf, donst dibr *sig);

    jobjfdt (JNICALL *CbllStbtidObjfdtMftiod)
      (JNIEnv *fnv, jdlbss dlbzz, jmftiodID mftiodID, ...);
    jobjfdt (JNICALL *CbllStbtidObjfdtMftiodV)
      (JNIEnv *fnv, jdlbss dlbzz, jmftiodID mftiodID, vb_list brgs);
    jobjfdt (JNICALL *CbllStbtidObjfdtMftiodA)
      (JNIEnv *fnv, jdlbss dlbzz, jmftiodID mftiodID, donst jvbluf *brgs);

    jboolfbn (JNICALL *CbllStbtidBoolfbnMftiod)
      (JNIEnv *fnv, jdlbss dlbzz, jmftiodID mftiodID, ...);
    jboolfbn (JNICALL *CbllStbtidBoolfbnMftiodV)
      (JNIEnv *fnv, jdlbss dlbzz, jmftiodID mftiodID, vb_list brgs);
    jboolfbn (JNICALL *CbllStbtidBoolfbnMftiodA)
      (JNIEnv *fnv, jdlbss dlbzz, jmftiodID mftiodID, donst jvbluf *brgs);

    jbytf (JNICALL *CbllStbtidBytfMftiod)
      (JNIEnv *fnv, jdlbss dlbzz, jmftiodID mftiodID, ...);
    jbytf (JNICALL *CbllStbtidBytfMftiodV)
      (JNIEnv *fnv, jdlbss dlbzz, jmftiodID mftiodID, vb_list brgs);
    jbytf (JNICALL *CbllStbtidBytfMftiodA)
      (JNIEnv *fnv, jdlbss dlbzz, jmftiodID mftiodID, donst jvbluf *brgs);

    jdibr (JNICALL *CbllStbtidCibrMftiod)
      (JNIEnv *fnv, jdlbss dlbzz, jmftiodID mftiodID, ...);
    jdibr (JNICALL *CbllStbtidCibrMftiodV)
      (JNIEnv *fnv, jdlbss dlbzz, jmftiodID mftiodID, vb_list brgs);
    jdibr (JNICALL *CbllStbtidCibrMftiodA)
      (JNIEnv *fnv, jdlbss dlbzz, jmftiodID mftiodID, donst jvbluf *brgs);

    jsiort (JNICALL *CbllStbtidSiortMftiod)
      (JNIEnv *fnv, jdlbss dlbzz, jmftiodID mftiodID, ...);
    jsiort (JNICALL *CbllStbtidSiortMftiodV)
      (JNIEnv *fnv, jdlbss dlbzz, jmftiodID mftiodID, vb_list brgs);
    jsiort (JNICALL *CbllStbtidSiortMftiodA)
      (JNIEnv *fnv, jdlbss dlbzz, jmftiodID mftiodID, donst jvbluf *brgs);

    jint (JNICALL *CbllStbtidIntMftiod)
      (JNIEnv *fnv, jdlbss dlbzz, jmftiodID mftiodID, ...);
    jint (JNICALL *CbllStbtidIntMftiodV)
      (JNIEnv *fnv, jdlbss dlbzz, jmftiodID mftiodID, vb_list brgs);
    jint (JNICALL *CbllStbtidIntMftiodA)
      (JNIEnv *fnv, jdlbss dlbzz, jmftiodID mftiodID, donst jvbluf *brgs);

    jlong (JNICALL *CbllStbtidLongMftiod)
      (JNIEnv *fnv, jdlbss dlbzz, jmftiodID mftiodID, ...);
    jlong (JNICALL *CbllStbtidLongMftiodV)
      (JNIEnv *fnv, jdlbss dlbzz, jmftiodID mftiodID, vb_list brgs);
    jlong (JNICALL *CbllStbtidLongMftiodA)
      (JNIEnv *fnv, jdlbss dlbzz, jmftiodID mftiodID, donst jvbluf *brgs);

    jflobt (JNICALL *CbllStbtidFlobtMftiod)
      (JNIEnv *fnv, jdlbss dlbzz, jmftiodID mftiodID, ...);
    jflobt (JNICALL *CbllStbtidFlobtMftiodV)
      (JNIEnv *fnv, jdlbss dlbzz, jmftiodID mftiodID, vb_list brgs);
    jflobt (JNICALL *CbllStbtidFlobtMftiodA)
      (JNIEnv *fnv, jdlbss dlbzz, jmftiodID mftiodID, donst jvbluf *brgs);

    jdoublf (JNICALL *CbllStbtidDoublfMftiod)
      (JNIEnv *fnv, jdlbss dlbzz, jmftiodID mftiodID, ...);
    jdoublf (JNICALL *CbllStbtidDoublfMftiodV)
      (JNIEnv *fnv, jdlbss dlbzz, jmftiodID mftiodID, vb_list brgs);
    jdoublf (JNICALL *CbllStbtidDoublfMftiodA)
      (JNIEnv *fnv, jdlbss dlbzz, jmftiodID mftiodID, donst jvbluf *brgs);

    void (JNICALL *CbllStbtidVoidMftiod)
      (JNIEnv *fnv, jdlbss dls, jmftiodID mftiodID, ...);
    void (JNICALL *CbllStbtidVoidMftiodV)
      (JNIEnv *fnv, jdlbss dls, jmftiodID mftiodID, vb_list brgs);
    void (JNICALL *CbllStbtidVoidMftiodA)
      (JNIEnv *fnv, jdlbss dls, jmftiodID mftiodID, donst jvbluf * brgs);

    jfifldID (JNICALL *GftStbtidFifldID)
      (JNIEnv *fnv, jdlbss dlbzz, donst dibr *nbmf, donst dibr *sig);
    jobjfdt (JNICALL *GftStbtidObjfdtFifld)
      (JNIEnv *fnv, jdlbss dlbzz, jfifldID fifldID);
    jboolfbn (JNICALL *GftStbtidBoolfbnFifld)
      (JNIEnv *fnv, jdlbss dlbzz, jfifldID fifldID);
    jbytf (JNICALL *GftStbtidBytfFifld)
      (JNIEnv *fnv, jdlbss dlbzz, jfifldID fifldID);
    jdibr (JNICALL *GftStbtidCibrFifld)
      (JNIEnv *fnv, jdlbss dlbzz, jfifldID fifldID);
    jsiort (JNICALL *GftStbtidSiortFifld)
      (JNIEnv *fnv, jdlbss dlbzz, jfifldID fifldID);
    jint (JNICALL *GftStbtidIntFifld)
      (JNIEnv *fnv, jdlbss dlbzz, jfifldID fifldID);
    jlong (JNICALL *GftStbtidLongFifld)
      (JNIEnv *fnv, jdlbss dlbzz, jfifldID fifldID);
    jflobt (JNICALL *GftStbtidFlobtFifld)
      (JNIEnv *fnv, jdlbss dlbzz, jfifldID fifldID);
    jdoublf (JNICALL *GftStbtidDoublfFifld)
      (JNIEnv *fnv, jdlbss dlbzz, jfifldID fifldID);

    void (JNICALL *SftStbtidObjfdtFifld)
      (JNIEnv *fnv, jdlbss dlbzz, jfifldID fifldID, jobjfdt vbluf);
    void (JNICALL *SftStbtidBoolfbnFifld)
      (JNIEnv *fnv, jdlbss dlbzz, jfifldID fifldID, jboolfbn vbluf);
    void (JNICALL *SftStbtidBytfFifld)
      (JNIEnv *fnv, jdlbss dlbzz, jfifldID fifldID, jbytf vbluf);
    void (JNICALL *SftStbtidCibrFifld)
      (JNIEnv *fnv, jdlbss dlbzz, jfifldID fifldID, jdibr vbluf);
    void (JNICALL *SftStbtidSiortFifld)
      (JNIEnv *fnv, jdlbss dlbzz, jfifldID fifldID, jsiort vbluf);
    void (JNICALL *SftStbtidIntFifld)
      (JNIEnv *fnv, jdlbss dlbzz, jfifldID fifldID, jint vbluf);
    void (JNICALL *SftStbtidLongFifld)
      (JNIEnv *fnv, jdlbss dlbzz, jfifldID fifldID, jlong vbluf);
    void (JNICALL *SftStbtidFlobtFifld)
      (JNIEnv *fnv, jdlbss dlbzz, jfifldID fifldID, jflobt vbluf);
    void (JNICALL *SftStbtidDoublfFifld)
      (JNIEnv *fnv, jdlbss dlbzz, jfifldID fifldID, jdoublf vbluf);

    jstring (JNICALL *NfwString)
      (JNIEnv *fnv, donst jdibr *unidodf, jsizf lfn);
    jsizf (JNICALL *GftStringLfngti)
      (JNIEnv *fnv, jstring str);
    donst jdibr *(JNICALL *GftStringCibrs)
      (JNIEnv *fnv, jstring str, jboolfbn *isCopy);
    void (JNICALL *RflfbsfStringCibrs)
      (JNIEnv *fnv, jstring str, donst jdibr *dibrs);

    jstring (JNICALL *NfwStringUTF)
      (JNIEnv *fnv, donst dibr *utf);
    jsizf (JNICALL *GftStringUTFLfngti)
      (JNIEnv *fnv, jstring str);
    donst dibr* (JNICALL *GftStringUTFCibrs)
      (JNIEnv *fnv, jstring str, jboolfbn *isCopy);
    void (JNICALL *RflfbsfStringUTFCibrs)
      (JNIEnv *fnv, jstring str, donst dibr* dibrs);


    jsizf (JNICALL *GftArrbyLfngti)
      (JNIEnv *fnv, jbrrby brrby);

    jobjfdtArrby (JNICALL *NfwObjfdtArrby)
      (JNIEnv *fnv, jsizf lfn, jdlbss dlbzz, jobjfdt init);
    jobjfdt (JNICALL *GftObjfdtArrbyElfmfnt)
      (JNIEnv *fnv, jobjfdtArrby brrby, jsizf indfx);
    void (JNICALL *SftObjfdtArrbyElfmfnt)
      (JNIEnv *fnv, jobjfdtArrby brrby, jsizf indfx, jobjfdt vbl);

    jboolfbnArrby (JNICALL *NfwBoolfbnArrby)
      (JNIEnv *fnv, jsizf lfn);
    jbytfArrby (JNICALL *NfwBytfArrby)
      (JNIEnv *fnv, jsizf lfn);
    jdibrArrby (JNICALL *NfwCibrArrby)
      (JNIEnv *fnv, jsizf lfn);
    jsiortArrby (JNICALL *NfwSiortArrby)
      (JNIEnv *fnv, jsizf lfn);
    jintArrby (JNICALL *NfwIntArrby)
      (JNIEnv *fnv, jsizf lfn);
    jlongArrby (JNICALL *NfwLongArrby)
      (JNIEnv *fnv, jsizf lfn);
    jflobtArrby (JNICALL *NfwFlobtArrby)
      (JNIEnv *fnv, jsizf lfn);
    jdoublfArrby (JNICALL *NfwDoublfArrby)
      (JNIEnv *fnv, jsizf lfn);

    jboolfbn * (JNICALL *GftBoolfbnArrbyElfmfnts)
      (JNIEnv *fnv, jboolfbnArrby brrby, jboolfbn *isCopy);
    jbytf * (JNICALL *GftBytfArrbyElfmfnts)
      (JNIEnv *fnv, jbytfArrby brrby, jboolfbn *isCopy);
    jdibr * (JNICALL *GftCibrArrbyElfmfnts)
      (JNIEnv *fnv, jdibrArrby brrby, jboolfbn *isCopy);
    jsiort * (JNICALL *GftSiortArrbyElfmfnts)
      (JNIEnv *fnv, jsiortArrby brrby, jboolfbn *isCopy);
    jint * (JNICALL *GftIntArrbyElfmfnts)
      (JNIEnv *fnv, jintArrby brrby, jboolfbn *isCopy);
    jlong * (JNICALL *GftLongArrbyElfmfnts)
      (JNIEnv *fnv, jlongArrby brrby, jboolfbn *isCopy);
    jflobt * (JNICALL *GftFlobtArrbyElfmfnts)
      (JNIEnv *fnv, jflobtArrby brrby, jboolfbn *isCopy);
    jdoublf * (JNICALL *GftDoublfArrbyElfmfnts)
      (JNIEnv *fnv, jdoublfArrby brrby, jboolfbn *isCopy);

    void (JNICALL *RflfbsfBoolfbnArrbyElfmfnts)
      (JNIEnv *fnv, jboolfbnArrby brrby, jboolfbn *flfms, jint modf);
    void (JNICALL *RflfbsfBytfArrbyElfmfnts)
      (JNIEnv *fnv, jbytfArrby brrby, jbytf *flfms, jint modf);
    void (JNICALL *RflfbsfCibrArrbyElfmfnts)
      (JNIEnv *fnv, jdibrArrby brrby, jdibr *flfms, jint modf);
    void (JNICALL *RflfbsfSiortArrbyElfmfnts)
      (JNIEnv *fnv, jsiortArrby brrby, jsiort *flfms, jint modf);
    void (JNICALL *RflfbsfIntArrbyElfmfnts)
      (JNIEnv *fnv, jintArrby brrby, jint *flfms, jint modf);
    void (JNICALL *RflfbsfLongArrbyElfmfnts)
      (JNIEnv *fnv, jlongArrby brrby, jlong *flfms, jint modf);
    void (JNICALL *RflfbsfFlobtArrbyElfmfnts)
      (JNIEnv *fnv, jflobtArrby brrby, jflobt *flfms, jint modf);
    void (JNICALL *RflfbsfDoublfArrbyElfmfnts)
      (JNIEnv *fnv, jdoublfArrby brrby, jdoublf *flfms, jint modf);

    void (JNICALL *GftBoolfbnArrbyRfgion)
      (JNIEnv *fnv, jboolfbnArrby brrby, jsizf stbrt, jsizf l, jboolfbn *buf);
    void (JNICALL *GftBytfArrbyRfgion)
      (JNIEnv *fnv, jbytfArrby brrby, jsizf stbrt, jsizf lfn, jbytf *buf);
    void (JNICALL *GftCibrArrbyRfgion)
      (JNIEnv *fnv, jdibrArrby brrby, jsizf stbrt, jsizf lfn, jdibr *buf);
    void (JNICALL *GftSiortArrbyRfgion)
      (JNIEnv *fnv, jsiortArrby brrby, jsizf stbrt, jsizf lfn, jsiort *buf);
    void (JNICALL *GftIntArrbyRfgion)
      (JNIEnv *fnv, jintArrby brrby, jsizf stbrt, jsizf lfn, jint *buf);
    void (JNICALL *GftLongArrbyRfgion)
      (JNIEnv *fnv, jlongArrby brrby, jsizf stbrt, jsizf lfn, jlong *buf);
    void (JNICALL *GftFlobtArrbyRfgion)
      (JNIEnv *fnv, jflobtArrby brrby, jsizf stbrt, jsizf lfn, jflobt *buf);
    void (JNICALL *GftDoublfArrbyRfgion)
      (JNIEnv *fnv, jdoublfArrby brrby, jsizf stbrt, jsizf lfn, jdoublf *buf);

    void (JNICALL *SftBoolfbnArrbyRfgion)
      (JNIEnv *fnv, jboolfbnArrby brrby, jsizf stbrt, jsizf l, donst jboolfbn *buf);
    void (JNICALL *SftBytfArrbyRfgion)
      (JNIEnv *fnv, jbytfArrby brrby, jsizf stbrt, jsizf lfn, donst jbytf *buf);
    void (JNICALL *SftCibrArrbyRfgion)
      (JNIEnv *fnv, jdibrArrby brrby, jsizf stbrt, jsizf lfn, donst jdibr *buf);
    void (JNICALL *SftSiortArrbyRfgion)
      (JNIEnv *fnv, jsiortArrby brrby, jsizf stbrt, jsizf lfn, donst jsiort *buf);
    void (JNICALL *SftIntArrbyRfgion)
      (JNIEnv *fnv, jintArrby brrby, jsizf stbrt, jsizf lfn, donst jint *buf);
    void (JNICALL *SftLongArrbyRfgion)
      (JNIEnv *fnv, jlongArrby brrby, jsizf stbrt, jsizf lfn, donst jlong *buf);
    void (JNICALL *SftFlobtArrbyRfgion)
      (JNIEnv *fnv, jflobtArrby brrby, jsizf stbrt, jsizf lfn, donst jflobt *buf);
    void (JNICALL *SftDoublfArrbyRfgion)
      (JNIEnv *fnv, jdoublfArrby brrby, jsizf stbrt, jsizf lfn, donst jdoublf *buf);

    jint (JNICALL *RfgistfrNbtivfs)
      (JNIEnv *fnv, jdlbss dlbzz, donst JNINbtivfMftiod *mftiods,
       jint nMftiods);
    jint (JNICALL *UnrfgistfrNbtivfs)
      (JNIEnv *fnv, jdlbss dlbzz);

    jint (JNICALL *MonitorEntfr)
      (JNIEnv *fnv, jobjfdt obj);
    jint (JNICALL *MonitorExit)
      (JNIEnv *fnv, jobjfdt obj);

    jint (JNICALL *GftJbvbVM)
      (JNIEnv *fnv, JbvbVM **vm);

    void (JNICALL *GftStringRfgion)
      (JNIEnv *fnv, jstring str, jsizf stbrt, jsizf lfn, jdibr *buf);
    void (JNICALL *GftStringUTFRfgion)
      (JNIEnv *fnv, jstring str, jsizf stbrt, jsizf lfn, dibr *buf);

    void * (JNICALL *GftPrimitivfArrbyCritidbl)
      (JNIEnv *fnv, jbrrby brrby, jboolfbn *isCopy);
    void (JNICALL *RflfbsfPrimitivfArrbyCritidbl)
      (JNIEnv *fnv, jbrrby brrby, void *dbrrby, jint modf);

    donst jdibr * (JNICALL *GftStringCritidbl)
      (JNIEnv *fnv, jstring string, jboolfbn *isCopy);
    void (JNICALL *RflfbsfStringCritidbl)
      (JNIEnv *fnv, jstring string, donst jdibr *dstring);

    jwfbk (JNICALL *NfwWfbkGlobblRff)
       (JNIEnv *fnv, jobjfdt obj);
    void (JNICALL *DflftfWfbkGlobblRff)
       (JNIEnv *fnv, jwfbk rff);

    jboolfbn (JNICALL *ExdfptionCifdk)
       (JNIEnv *fnv);

    jobjfdt (JNICALL *NfwDirfdtBytfBufffr)
       (JNIEnv* fnv, void* bddrfss, jlong dbpbdity);
    void* (JNICALL *GftDirfdtBufffrAddrfss)
       (JNIEnv* fnv, jobjfdt buf);
    jlong (JNICALL *GftDirfdtBufffrCbpbdity)
       (JNIEnv* fnv, jobjfdt buf);

    /* Nfw JNI 1.6 Ffbturfs */

    jobjfdtRffTypf (JNICALL *GftObjfdtRffTypf)
        (JNIEnv* fnv, jobjfdt obj);
};

/*
 * Wf usf inlinfd fundtions for C++ so tibt progrbmmfrs dbn writf:
 *
 *    fnv->FindClbss("jbvb/lbng/String")
 *
 * in C++ rbtifr tibn:
 *
 *    (*fnv)->FindClbss(fnv, "jbvb/lbng/String")
 *
 * in C.
 */

strudt JNIEnv_ {
    donst strudt JNINbtivfIntfrfbdf_ *fundtions;
#ifdff __dplusplus

    jint GftVfrsion() {
        rfturn fundtions->GftVfrsion(tiis);
    }
    jdlbss DffinfClbss(donst dibr *nbmf, jobjfdt lobdfr, donst jbytf *buf,
                       jsizf lfn) {
        rfturn fundtions->DffinfClbss(tiis, nbmf, lobdfr, buf, lfn);
    }
    jdlbss FindClbss(donst dibr *nbmf) {
        rfturn fundtions->FindClbss(tiis, nbmf);
    }
    jmftiodID FromRfflfdtfdMftiod(jobjfdt mftiod) {
        rfturn fundtions->FromRfflfdtfdMftiod(tiis,mftiod);
    }
    jfifldID FromRfflfdtfdFifld(jobjfdt fifld) {
        rfturn fundtions->FromRfflfdtfdFifld(tiis,fifld);
    }

    jobjfdt ToRfflfdtfdMftiod(jdlbss dls, jmftiodID mftiodID, jboolfbn isStbtid) {
        rfturn fundtions->ToRfflfdtfdMftiod(tiis, dls, mftiodID, isStbtid);
    }

    jdlbss GftSupfrdlbss(jdlbss sub) {
        rfturn fundtions->GftSupfrdlbss(tiis, sub);
    }
    jboolfbn IsAssignbblfFrom(jdlbss sub, jdlbss sup) {
        rfturn fundtions->IsAssignbblfFrom(tiis, sub, sup);
    }

    jobjfdt ToRfflfdtfdFifld(jdlbss dls, jfifldID fifldID, jboolfbn isStbtid) {
        rfturn fundtions->ToRfflfdtfdFifld(tiis,dls,fifldID,isStbtid);
    }

    jint Tirow(jtirowbblf obj) {
        rfturn fundtions->Tirow(tiis, obj);
    }
    jint TirowNfw(jdlbss dlbzz, donst dibr *msg) {
        rfturn fundtions->TirowNfw(tiis, dlbzz, msg);
    }
    jtirowbblf ExdfptionOddurrfd() {
        rfturn fundtions->ExdfptionOddurrfd(tiis);
    }
    void ExdfptionDfsdribf() {
        fundtions->ExdfptionDfsdribf(tiis);
    }
    void ExdfptionClfbr() {
        fundtions->ExdfptionClfbr(tiis);
    }
    void FbtblError(donst dibr *msg) {
        fundtions->FbtblError(tiis, msg);
    }

    jint PusiLodblFrbmf(jint dbpbdity) {
        rfturn fundtions->PusiLodblFrbmf(tiis,dbpbdity);
    }
    jobjfdt PopLodblFrbmf(jobjfdt rfsult) {
        rfturn fundtions->PopLodblFrbmf(tiis,rfsult);
    }

    jobjfdt NfwGlobblRff(jobjfdt lobj) {
        rfturn fundtions->NfwGlobblRff(tiis,lobj);
    }
    void DflftfGlobblRff(jobjfdt grff) {
        fundtions->DflftfGlobblRff(tiis,grff);
    }
    void DflftfLodblRff(jobjfdt obj) {
        fundtions->DflftfLodblRff(tiis, obj);
    }

    jboolfbn IsSbmfObjfdt(jobjfdt obj1, jobjfdt obj2) {
        rfturn fundtions->IsSbmfObjfdt(tiis,obj1,obj2);
    }

    jobjfdt NfwLodblRff(jobjfdt rff) {
        rfturn fundtions->NfwLodblRff(tiis,rff);
    }
    jint EnsurfLodblCbpbdity(jint dbpbdity) {
        rfturn fundtions->EnsurfLodblCbpbdity(tiis,dbpbdity);
    }

    jobjfdt AllodObjfdt(jdlbss dlbzz) {
        rfturn fundtions->AllodObjfdt(tiis,dlbzz);
    }
    jobjfdt NfwObjfdt(jdlbss dlbzz, jmftiodID mftiodID, ...) {
        vb_list brgs;
        jobjfdt rfsult;
        vb_stbrt(brgs, mftiodID);
        rfsult = fundtions->NfwObjfdtV(tiis,dlbzz,mftiodID,brgs);
        vb_fnd(brgs);
        rfturn rfsult;
    }
    jobjfdt NfwObjfdtV(jdlbss dlbzz, jmftiodID mftiodID,
                       vb_list brgs) {
        rfturn fundtions->NfwObjfdtV(tiis,dlbzz,mftiodID,brgs);
    }
    jobjfdt NfwObjfdtA(jdlbss dlbzz, jmftiodID mftiodID,
                       donst jvbluf *brgs) {
        rfturn fundtions->NfwObjfdtA(tiis,dlbzz,mftiodID,brgs);
    }

    jdlbss GftObjfdtClbss(jobjfdt obj) {
        rfturn fundtions->GftObjfdtClbss(tiis,obj);
    }
    jboolfbn IsInstbndfOf(jobjfdt obj, jdlbss dlbzz) {
        rfturn fundtions->IsInstbndfOf(tiis,obj,dlbzz);
    }

    jmftiodID GftMftiodID(jdlbss dlbzz, donst dibr *nbmf,
                          donst dibr *sig) {
        rfturn fundtions->GftMftiodID(tiis,dlbzz,nbmf,sig);
    }

    jobjfdt CbllObjfdtMftiod(jobjfdt obj, jmftiodID mftiodID, ...) {
        vb_list brgs;
        jobjfdt rfsult;
        vb_stbrt(brgs,mftiodID);
        rfsult = fundtions->CbllObjfdtMftiodV(tiis,obj,mftiodID,brgs);
        vb_fnd(brgs);
        rfturn rfsult;
    }
    jobjfdt CbllObjfdtMftiodV(jobjfdt obj, jmftiodID mftiodID,
                        vb_list brgs) {
        rfturn fundtions->CbllObjfdtMftiodV(tiis,obj,mftiodID,brgs);
    }
    jobjfdt CbllObjfdtMftiodA(jobjfdt obj, jmftiodID mftiodID,
                        donst jvbluf * brgs) {
        rfturn fundtions->CbllObjfdtMftiodA(tiis,obj,mftiodID,brgs);
    }

    jboolfbn CbllBoolfbnMftiod(jobjfdt obj,
                               jmftiodID mftiodID, ...) {
        vb_list brgs;
        jboolfbn rfsult;
        vb_stbrt(brgs,mftiodID);
        rfsult = fundtions->CbllBoolfbnMftiodV(tiis,obj,mftiodID,brgs);
        vb_fnd(brgs);
        rfturn rfsult;
    }
    jboolfbn CbllBoolfbnMftiodV(jobjfdt obj, jmftiodID mftiodID,
                                vb_list brgs) {
        rfturn fundtions->CbllBoolfbnMftiodV(tiis,obj,mftiodID,brgs);
    }
    jboolfbn CbllBoolfbnMftiodA(jobjfdt obj, jmftiodID mftiodID,
                                donst jvbluf * brgs) {
        rfturn fundtions->CbllBoolfbnMftiodA(tiis,obj,mftiodID, brgs);
    }

    jbytf CbllBytfMftiod(jobjfdt obj, jmftiodID mftiodID, ...) {
        vb_list brgs;
        jbytf rfsult;
        vb_stbrt(brgs,mftiodID);
        rfsult = fundtions->CbllBytfMftiodV(tiis,obj,mftiodID,brgs);
        vb_fnd(brgs);
        rfturn rfsult;
    }
    jbytf CbllBytfMftiodV(jobjfdt obj, jmftiodID mftiodID,
                          vb_list brgs) {
        rfturn fundtions->CbllBytfMftiodV(tiis,obj,mftiodID,brgs);
    }
    jbytf CbllBytfMftiodA(jobjfdt obj, jmftiodID mftiodID,
                          donst jvbluf * brgs) {
        rfturn fundtions->CbllBytfMftiodA(tiis,obj,mftiodID,brgs);
    }

    jdibr CbllCibrMftiod(jobjfdt obj, jmftiodID mftiodID, ...) {
        vb_list brgs;
        jdibr rfsult;
        vb_stbrt(brgs,mftiodID);
        rfsult = fundtions->CbllCibrMftiodV(tiis,obj,mftiodID,brgs);
        vb_fnd(brgs);
        rfturn rfsult;
    }
    jdibr CbllCibrMftiodV(jobjfdt obj, jmftiodID mftiodID,
                          vb_list brgs) {
        rfturn fundtions->CbllCibrMftiodV(tiis,obj,mftiodID,brgs);
    }
    jdibr CbllCibrMftiodA(jobjfdt obj, jmftiodID mftiodID,
                          donst jvbluf * brgs) {
        rfturn fundtions->CbllCibrMftiodA(tiis,obj,mftiodID,brgs);
    }

    jsiort CbllSiortMftiod(jobjfdt obj, jmftiodID mftiodID, ...) {
        vb_list brgs;
        jsiort rfsult;
        vb_stbrt(brgs,mftiodID);
        rfsult = fundtions->CbllSiortMftiodV(tiis,obj,mftiodID,brgs);
        vb_fnd(brgs);
        rfturn rfsult;
    }
    jsiort CbllSiortMftiodV(jobjfdt obj, jmftiodID mftiodID,
                            vb_list brgs) {
        rfturn fundtions->CbllSiortMftiodV(tiis,obj,mftiodID,brgs);
    }
    jsiort CbllSiortMftiodA(jobjfdt obj, jmftiodID mftiodID,
                            donst jvbluf * brgs) {
        rfturn fundtions->CbllSiortMftiodA(tiis,obj,mftiodID,brgs);
    }

    jint CbllIntMftiod(jobjfdt obj, jmftiodID mftiodID, ...) {
        vb_list brgs;
        jint rfsult;
        vb_stbrt(brgs,mftiodID);
        rfsult = fundtions->CbllIntMftiodV(tiis,obj,mftiodID,brgs);
        vb_fnd(brgs);
        rfturn rfsult;
    }
    jint CbllIntMftiodV(jobjfdt obj, jmftiodID mftiodID,
                        vb_list brgs) {
        rfturn fundtions->CbllIntMftiodV(tiis,obj,mftiodID,brgs);
    }
    jint CbllIntMftiodA(jobjfdt obj, jmftiodID mftiodID,
                        donst jvbluf * brgs) {
        rfturn fundtions->CbllIntMftiodA(tiis,obj,mftiodID,brgs);
    }

    jlong CbllLongMftiod(jobjfdt obj, jmftiodID mftiodID, ...) {
        vb_list brgs;
        jlong rfsult;
        vb_stbrt(brgs,mftiodID);
        rfsult = fundtions->CbllLongMftiodV(tiis,obj,mftiodID,brgs);
        vb_fnd(brgs);
        rfturn rfsult;
    }
    jlong CbllLongMftiodV(jobjfdt obj, jmftiodID mftiodID,
                          vb_list brgs) {
        rfturn fundtions->CbllLongMftiodV(tiis,obj,mftiodID,brgs);
    }
    jlong CbllLongMftiodA(jobjfdt obj, jmftiodID mftiodID,
                          donst jvbluf * brgs) {
        rfturn fundtions->CbllLongMftiodA(tiis,obj,mftiodID,brgs);
    }

    jflobt CbllFlobtMftiod(jobjfdt obj, jmftiodID mftiodID, ...) {
        vb_list brgs;
        jflobt rfsult;
        vb_stbrt(brgs,mftiodID);
        rfsult = fundtions->CbllFlobtMftiodV(tiis,obj,mftiodID,brgs);
        vb_fnd(brgs);
        rfturn rfsult;
    }
    jflobt CbllFlobtMftiodV(jobjfdt obj, jmftiodID mftiodID,
                            vb_list brgs) {
        rfturn fundtions->CbllFlobtMftiodV(tiis,obj,mftiodID,brgs);
    }
    jflobt CbllFlobtMftiodA(jobjfdt obj, jmftiodID mftiodID,
                            donst jvbluf * brgs) {
        rfturn fundtions->CbllFlobtMftiodA(tiis,obj,mftiodID,brgs);
    }

    jdoublf CbllDoublfMftiod(jobjfdt obj, jmftiodID mftiodID, ...) {
        vb_list brgs;
        jdoublf rfsult;
        vb_stbrt(brgs,mftiodID);
        rfsult = fundtions->CbllDoublfMftiodV(tiis,obj,mftiodID,brgs);
        vb_fnd(brgs);
        rfturn rfsult;
    }
    jdoublf CbllDoublfMftiodV(jobjfdt obj, jmftiodID mftiodID,
                        vb_list brgs) {
        rfturn fundtions->CbllDoublfMftiodV(tiis,obj,mftiodID,brgs);
    }
    jdoublf CbllDoublfMftiodA(jobjfdt obj, jmftiodID mftiodID,
                        donst jvbluf * brgs) {
        rfturn fundtions->CbllDoublfMftiodA(tiis,obj,mftiodID,brgs);
    }

    void CbllVoidMftiod(jobjfdt obj, jmftiodID mftiodID, ...) {
        vb_list brgs;
        vb_stbrt(brgs,mftiodID);
        fundtions->CbllVoidMftiodV(tiis,obj,mftiodID,brgs);
        vb_fnd(brgs);
    }
    void CbllVoidMftiodV(jobjfdt obj, jmftiodID mftiodID,
                         vb_list brgs) {
        fundtions->CbllVoidMftiodV(tiis,obj,mftiodID,brgs);
    }
    void CbllVoidMftiodA(jobjfdt obj, jmftiodID mftiodID,
                         donst jvbluf * brgs) {
        fundtions->CbllVoidMftiodA(tiis,obj,mftiodID,brgs);
    }

    jobjfdt CbllNonvirtublObjfdtMftiod(jobjfdt obj, jdlbss dlbzz,
                                       jmftiodID mftiodID, ...) {
        vb_list brgs;
        jobjfdt rfsult;
        vb_stbrt(brgs,mftiodID);
        rfsult = fundtions->CbllNonvirtublObjfdtMftiodV(tiis,obj,dlbzz,
                                                        mftiodID,brgs);
        vb_fnd(brgs);
        rfturn rfsult;
    }
    jobjfdt CbllNonvirtublObjfdtMftiodV(jobjfdt obj, jdlbss dlbzz,
                                        jmftiodID mftiodID, vb_list brgs) {
        rfturn fundtions->CbllNonvirtublObjfdtMftiodV(tiis,obj,dlbzz,
                                                      mftiodID,brgs);
    }
    jobjfdt CbllNonvirtublObjfdtMftiodA(jobjfdt obj, jdlbss dlbzz,
                                        jmftiodID mftiodID, donst jvbluf * brgs) {
        rfturn fundtions->CbllNonvirtublObjfdtMftiodA(tiis,obj,dlbzz,
                                                      mftiodID,brgs);
    }

    jboolfbn CbllNonvirtublBoolfbnMftiod(jobjfdt obj, jdlbss dlbzz,
                                         jmftiodID mftiodID, ...) {
        vb_list brgs;
        jboolfbn rfsult;
        vb_stbrt(brgs,mftiodID);
        rfsult = fundtions->CbllNonvirtublBoolfbnMftiodV(tiis,obj,dlbzz,
                                                         mftiodID,brgs);
        vb_fnd(brgs);
        rfturn rfsult;
    }
    jboolfbn CbllNonvirtublBoolfbnMftiodV(jobjfdt obj, jdlbss dlbzz,
                                          jmftiodID mftiodID, vb_list brgs) {
        rfturn fundtions->CbllNonvirtublBoolfbnMftiodV(tiis,obj,dlbzz,
                                                       mftiodID,brgs);
    }
    jboolfbn CbllNonvirtublBoolfbnMftiodA(jobjfdt obj, jdlbss dlbzz,
                                          jmftiodID mftiodID, donst jvbluf * brgs) {
        rfturn fundtions->CbllNonvirtublBoolfbnMftiodA(tiis,obj,dlbzz,
                                                       mftiodID, brgs);
    }

    jbytf CbllNonvirtublBytfMftiod(jobjfdt obj, jdlbss dlbzz,
                                   jmftiodID mftiodID, ...) {
        vb_list brgs;
        jbytf rfsult;
        vb_stbrt(brgs,mftiodID);
        rfsult = fundtions->CbllNonvirtublBytfMftiodV(tiis,obj,dlbzz,
                                                      mftiodID,brgs);
        vb_fnd(brgs);
        rfturn rfsult;
    }
    jbytf CbllNonvirtublBytfMftiodV(jobjfdt obj, jdlbss dlbzz,
                                    jmftiodID mftiodID, vb_list brgs) {
        rfturn fundtions->CbllNonvirtublBytfMftiodV(tiis,obj,dlbzz,
                                                    mftiodID,brgs);
    }
    jbytf CbllNonvirtublBytfMftiodA(jobjfdt obj, jdlbss dlbzz,
                                    jmftiodID mftiodID, donst jvbluf * brgs) {
        rfturn fundtions->CbllNonvirtublBytfMftiodA(tiis,obj,dlbzz,
                                                    mftiodID,brgs);
    }

    jdibr CbllNonvirtublCibrMftiod(jobjfdt obj, jdlbss dlbzz,
                                   jmftiodID mftiodID, ...) {
        vb_list brgs;
        jdibr rfsult;
        vb_stbrt(brgs,mftiodID);
        rfsult = fundtions->CbllNonvirtublCibrMftiodV(tiis,obj,dlbzz,
                                                      mftiodID,brgs);
        vb_fnd(brgs);
        rfturn rfsult;
    }
    jdibr CbllNonvirtublCibrMftiodV(jobjfdt obj, jdlbss dlbzz,
                                    jmftiodID mftiodID, vb_list brgs) {
        rfturn fundtions->CbllNonvirtublCibrMftiodV(tiis,obj,dlbzz,
                                                    mftiodID,brgs);
    }
    jdibr CbllNonvirtublCibrMftiodA(jobjfdt obj, jdlbss dlbzz,
                                    jmftiodID mftiodID, donst jvbluf * brgs) {
        rfturn fundtions->CbllNonvirtublCibrMftiodA(tiis,obj,dlbzz,
                                                    mftiodID,brgs);
    }

    jsiort CbllNonvirtublSiortMftiod(jobjfdt obj, jdlbss dlbzz,
                                     jmftiodID mftiodID, ...) {
        vb_list brgs;
        jsiort rfsult;
        vb_stbrt(brgs,mftiodID);
        rfsult = fundtions->CbllNonvirtublSiortMftiodV(tiis,obj,dlbzz,
                                                       mftiodID,brgs);
        vb_fnd(brgs);
        rfturn rfsult;
    }
    jsiort CbllNonvirtublSiortMftiodV(jobjfdt obj, jdlbss dlbzz,
                                      jmftiodID mftiodID, vb_list brgs) {
        rfturn fundtions->CbllNonvirtublSiortMftiodV(tiis,obj,dlbzz,
                                                     mftiodID,brgs);
    }
    jsiort CbllNonvirtublSiortMftiodA(jobjfdt obj, jdlbss dlbzz,
                                      jmftiodID mftiodID, donst jvbluf * brgs) {
        rfturn fundtions->CbllNonvirtublSiortMftiodA(tiis,obj,dlbzz,
                                                     mftiodID,brgs);
    }

    jint CbllNonvirtublIntMftiod(jobjfdt obj, jdlbss dlbzz,
                                 jmftiodID mftiodID, ...) {
        vb_list brgs;
        jint rfsult;
        vb_stbrt(brgs,mftiodID);
        rfsult = fundtions->CbllNonvirtublIntMftiodV(tiis,obj,dlbzz,
                                                     mftiodID,brgs);
        vb_fnd(brgs);
        rfturn rfsult;
    }
    jint CbllNonvirtublIntMftiodV(jobjfdt obj, jdlbss dlbzz,
                                  jmftiodID mftiodID, vb_list brgs) {
        rfturn fundtions->CbllNonvirtublIntMftiodV(tiis,obj,dlbzz,
                                                   mftiodID,brgs);
    }
    jint CbllNonvirtublIntMftiodA(jobjfdt obj, jdlbss dlbzz,
                                  jmftiodID mftiodID, donst jvbluf * brgs) {
        rfturn fundtions->CbllNonvirtublIntMftiodA(tiis,obj,dlbzz,
                                                   mftiodID,brgs);
    }

    jlong CbllNonvirtublLongMftiod(jobjfdt obj, jdlbss dlbzz,
                                   jmftiodID mftiodID, ...) {
        vb_list brgs;
        jlong rfsult;
        vb_stbrt(brgs,mftiodID);
        rfsult = fundtions->CbllNonvirtublLongMftiodV(tiis,obj,dlbzz,
                                                      mftiodID,brgs);
        vb_fnd(brgs);
        rfturn rfsult;
    }
    jlong CbllNonvirtublLongMftiodV(jobjfdt obj, jdlbss dlbzz,
                                    jmftiodID mftiodID, vb_list brgs) {
        rfturn fundtions->CbllNonvirtublLongMftiodV(tiis,obj,dlbzz,
                                                    mftiodID,brgs);
    }
    jlong CbllNonvirtublLongMftiodA(jobjfdt obj, jdlbss dlbzz,
                                    jmftiodID mftiodID, donst jvbluf * brgs) {
        rfturn fundtions->CbllNonvirtublLongMftiodA(tiis,obj,dlbzz,
                                                    mftiodID,brgs);
    }

    jflobt CbllNonvirtublFlobtMftiod(jobjfdt obj, jdlbss dlbzz,
                                     jmftiodID mftiodID, ...) {
        vb_list brgs;
        jflobt rfsult;
        vb_stbrt(brgs,mftiodID);
        rfsult = fundtions->CbllNonvirtublFlobtMftiodV(tiis,obj,dlbzz,
                                                       mftiodID,brgs);
        vb_fnd(brgs);
        rfturn rfsult;
    }
    jflobt CbllNonvirtublFlobtMftiodV(jobjfdt obj, jdlbss dlbzz,
                                      jmftiodID mftiodID,
                                      vb_list brgs) {
        rfturn fundtions->CbllNonvirtublFlobtMftiodV(tiis,obj,dlbzz,
                                                     mftiodID,brgs);
    }
    jflobt CbllNonvirtublFlobtMftiodA(jobjfdt obj, jdlbss dlbzz,
                                      jmftiodID mftiodID,
                                      donst jvbluf * brgs) {
        rfturn fundtions->CbllNonvirtublFlobtMftiodA(tiis,obj,dlbzz,
                                                     mftiodID,brgs);
    }

    jdoublf CbllNonvirtublDoublfMftiod(jobjfdt obj, jdlbss dlbzz,
                                       jmftiodID mftiodID, ...) {
        vb_list brgs;
        jdoublf rfsult;
        vb_stbrt(brgs,mftiodID);
        rfsult = fundtions->CbllNonvirtublDoublfMftiodV(tiis,obj,dlbzz,
                                                        mftiodID,brgs);
        vb_fnd(brgs);
        rfturn rfsult;
    }
    jdoublf CbllNonvirtublDoublfMftiodV(jobjfdt obj, jdlbss dlbzz,
                                        jmftiodID mftiodID,
                                        vb_list brgs) {
        rfturn fundtions->CbllNonvirtublDoublfMftiodV(tiis,obj,dlbzz,
                                                      mftiodID,brgs);
    }
    jdoublf CbllNonvirtublDoublfMftiodA(jobjfdt obj, jdlbss dlbzz,
                                        jmftiodID mftiodID,
                                        donst jvbluf * brgs) {
        rfturn fundtions->CbllNonvirtublDoublfMftiodA(tiis,obj,dlbzz,
                                                      mftiodID,brgs);
    }

    void CbllNonvirtublVoidMftiod(jobjfdt obj, jdlbss dlbzz,
                                  jmftiodID mftiodID, ...) {
        vb_list brgs;
        vb_stbrt(brgs,mftiodID);
        fundtions->CbllNonvirtublVoidMftiodV(tiis,obj,dlbzz,mftiodID,brgs);
        vb_fnd(brgs);
    }
    void CbllNonvirtublVoidMftiodV(jobjfdt obj, jdlbss dlbzz,
                                   jmftiodID mftiodID,
                                   vb_list brgs) {
        fundtions->CbllNonvirtublVoidMftiodV(tiis,obj,dlbzz,mftiodID,brgs);
    }
    void CbllNonvirtublVoidMftiodA(jobjfdt obj, jdlbss dlbzz,
                                   jmftiodID mftiodID,
                                   donst jvbluf * brgs) {
        fundtions->CbllNonvirtublVoidMftiodA(tiis,obj,dlbzz,mftiodID,brgs);
    }

    jfifldID GftFifldID(jdlbss dlbzz, donst dibr *nbmf,
                        donst dibr *sig) {
        rfturn fundtions->GftFifldID(tiis,dlbzz,nbmf,sig);
    }

    jobjfdt GftObjfdtFifld(jobjfdt obj, jfifldID fifldID) {
        rfturn fundtions->GftObjfdtFifld(tiis,obj,fifldID);
    }
    jboolfbn GftBoolfbnFifld(jobjfdt obj, jfifldID fifldID) {
        rfturn fundtions->GftBoolfbnFifld(tiis,obj,fifldID);
    }
    jbytf GftBytfFifld(jobjfdt obj, jfifldID fifldID) {
        rfturn fundtions->GftBytfFifld(tiis,obj,fifldID);
    }
    jdibr GftCibrFifld(jobjfdt obj, jfifldID fifldID) {
        rfturn fundtions->GftCibrFifld(tiis,obj,fifldID);
    }
    jsiort GftSiortFifld(jobjfdt obj, jfifldID fifldID) {
        rfturn fundtions->GftSiortFifld(tiis,obj,fifldID);
    }
    jint GftIntFifld(jobjfdt obj, jfifldID fifldID) {
        rfturn fundtions->GftIntFifld(tiis,obj,fifldID);
    }
    jlong GftLongFifld(jobjfdt obj, jfifldID fifldID) {
        rfturn fundtions->GftLongFifld(tiis,obj,fifldID);
    }
    jflobt GftFlobtFifld(jobjfdt obj, jfifldID fifldID) {
        rfturn fundtions->GftFlobtFifld(tiis,obj,fifldID);
    }
    jdoublf GftDoublfFifld(jobjfdt obj, jfifldID fifldID) {
        rfturn fundtions->GftDoublfFifld(tiis,obj,fifldID);
    }

    void SftObjfdtFifld(jobjfdt obj, jfifldID fifldID, jobjfdt vbl) {
        fundtions->SftObjfdtFifld(tiis,obj,fifldID,vbl);
    }
    void SftBoolfbnFifld(jobjfdt obj, jfifldID fifldID,
                         jboolfbn vbl) {
        fundtions->SftBoolfbnFifld(tiis,obj,fifldID,vbl);
    }
    void SftBytfFifld(jobjfdt obj, jfifldID fifldID,
                      jbytf vbl) {
        fundtions->SftBytfFifld(tiis,obj,fifldID,vbl);
    }
    void SftCibrFifld(jobjfdt obj, jfifldID fifldID,
                      jdibr vbl) {
        fundtions->SftCibrFifld(tiis,obj,fifldID,vbl);
    }
    void SftSiortFifld(jobjfdt obj, jfifldID fifldID,
                       jsiort vbl) {
        fundtions->SftSiortFifld(tiis,obj,fifldID,vbl);
    }
    void SftIntFifld(jobjfdt obj, jfifldID fifldID,
                     jint vbl) {
        fundtions->SftIntFifld(tiis,obj,fifldID,vbl);
    }
    void SftLongFifld(jobjfdt obj, jfifldID fifldID,
                      jlong vbl) {
        fundtions->SftLongFifld(tiis,obj,fifldID,vbl);
    }
    void SftFlobtFifld(jobjfdt obj, jfifldID fifldID,
                       jflobt vbl) {
        fundtions->SftFlobtFifld(tiis,obj,fifldID,vbl);
    }
    void SftDoublfFifld(jobjfdt obj, jfifldID fifldID,
                        jdoublf vbl) {
        fundtions->SftDoublfFifld(tiis,obj,fifldID,vbl);
    }

    jmftiodID GftStbtidMftiodID(jdlbss dlbzz, donst dibr *nbmf,
                                donst dibr *sig) {
        rfturn fundtions->GftStbtidMftiodID(tiis,dlbzz,nbmf,sig);
    }

    jobjfdt CbllStbtidObjfdtMftiod(jdlbss dlbzz, jmftiodID mftiodID,
                             ...) {
        vb_list brgs;
        jobjfdt rfsult;
        vb_stbrt(brgs,mftiodID);
        rfsult = fundtions->CbllStbtidObjfdtMftiodV(tiis,dlbzz,mftiodID,brgs);
        vb_fnd(brgs);
        rfturn rfsult;
    }
    jobjfdt CbllStbtidObjfdtMftiodV(jdlbss dlbzz, jmftiodID mftiodID,
                              vb_list brgs) {
        rfturn fundtions->CbllStbtidObjfdtMftiodV(tiis,dlbzz,mftiodID,brgs);
    }
    jobjfdt CbllStbtidObjfdtMftiodA(jdlbss dlbzz, jmftiodID mftiodID,
                              donst jvbluf *brgs) {
        rfturn fundtions->CbllStbtidObjfdtMftiodA(tiis,dlbzz,mftiodID,brgs);
    }

    jboolfbn CbllStbtidBoolfbnMftiod(jdlbss dlbzz,
                                     jmftiodID mftiodID, ...) {
        vb_list brgs;
        jboolfbn rfsult;
        vb_stbrt(brgs,mftiodID);
        rfsult = fundtions->CbllStbtidBoolfbnMftiodV(tiis,dlbzz,mftiodID,brgs);
        vb_fnd(brgs);
        rfturn rfsult;
    }
    jboolfbn CbllStbtidBoolfbnMftiodV(jdlbss dlbzz,
                                      jmftiodID mftiodID, vb_list brgs) {
        rfturn fundtions->CbllStbtidBoolfbnMftiodV(tiis,dlbzz,mftiodID,brgs);
    }
    jboolfbn CbllStbtidBoolfbnMftiodA(jdlbss dlbzz,
                                      jmftiodID mftiodID, donst jvbluf *brgs) {
        rfturn fundtions->CbllStbtidBoolfbnMftiodA(tiis,dlbzz,mftiodID,brgs);
    }

    jbytf CbllStbtidBytfMftiod(jdlbss dlbzz,
                               jmftiodID mftiodID, ...) {
        vb_list brgs;
        jbytf rfsult;
        vb_stbrt(brgs,mftiodID);
        rfsult = fundtions->CbllStbtidBytfMftiodV(tiis,dlbzz,mftiodID,brgs);
        vb_fnd(brgs);
        rfturn rfsult;
    }
    jbytf CbllStbtidBytfMftiodV(jdlbss dlbzz,
                                jmftiodID mftiodID, vb_list brgs) {
        rfturn fundtions->CbllStbtidBytfMftiodV(tiis,dlbzz,mftiodID,brgs);
    }
    jbytf CbllStbtidBytfMftiodA(jdlbss dlbzz,
                                jmftiodID mftiodID, donst jvbluf *brgs) {
        rfturn fundtions->CbllStbtidBytfMftiodA(tiis,dlbzz,mftiodID,brgs);
    }

    jdibr CbllStbtidCibrMftiod(jdlbss dlbzz,
                               jmftiodID mftiodID, ...) {
        vb_list brgs;
        jdibr rfsult;
        vb_stbrt(brgs,mftiodID);
        rfsult = fundtions->CbllStbtidCibrMftiodV(tiis,dlbzz,mftiodID,brgs);
        vb_fnd(brgs);
        rfturn rfsult;
    }
    jdibr CbllStbtidCibrMftiodV(jdlbss dlbzz,
                                jmftiodID mftiodID, vb_list brgs) {
        rfturn fundtions->CbllStbtidCibrMftiodV(tiis,dlbzz,mftiodID,brgs);
    }
    jdibr CbllStbtidCibrMftiodA(jdlbss dlbzz,
                                jmftiodID mftiodID, donst jvbluf *brgs) {
        rfturn fundtions->CbllStbtidCibrMftiodA(tiis,dlbzz,mftiodID,brgs);
    }

    jsiort CbllStbtidSiortMftiod(jdlbss dlbzz,
                                 jmftiodID mftiodID, ...) {
        vb_list brgs;
        jsiort rfsult;
        vb_stbrt(brgs,mftiodID);
        rfsult = fundtions->CbllStbtidSiortMftiodV(tiis,dlbzz,mftiodID,brgs);
        vb_fnd(brgs);
        rfturn rfsult;
    }
    jsiort CbllStbtidSiortMftiodV(jdlbss dlbzz,
                                  jmftiodID mftiodID, vb_list brgs) {
        rfturn fundtions->CbllStbtidSiortMftiodV(tiis,dlbzz,mftiodID,brgs);
    }
    jsiort CbllStbtidSiortMftiodA(jdlbss dlbzz,
                                  jmftiodID mftiodID, donst jvbluf *brgs) {
        rfturn fundtions->CbllStbtidSiortMftiodA(tiis,dlbzz,mftiodID,brgs);
    }

    jint CbllStbtidIntMftiod(jdlbss dlbzz,
                             jmftiodID mftiodID, ...) {
        vb_list brgs;
        jint rfsult;
        vb_stbrt(brgs,mftiodID);
        rfsult = fundtions->CbllStbtidIntMftiodV(tiis,dlbzz,mftiodID,brgs);
        vb_fnd(brgs);
        rfturn rfsult;
    }
    jint CbllStbtidIntMftiodV(jdlbss dlbzz,
                              jmftiodID mftiodID, vb_list brgs) {
        rfturn fundtions->CbllStbtidIntMftiodV(tiis,dlbzz,mftiodID,brgs);
    }
    jint CbllStbtidIntMftiodA(jdlbss dlbzz,
                              jmftiodID mftiodID, donst jvbluf *brgs) {
        rfturn fundtions->CbllStbtidIntMftiodA(tiis,dlbzz,mftiodID,brgs);
    }

    jlong CbllStbtidLongMftiod(jdlbss dlbzz,
                               jmftiodID mftiodID, ...) {
        vb_list brgs;
        jlong rfsult;
        vb_stbrt(brgs,mftiodID);
        rfsult = fundtions->CbllStbtidLongMftiodV(tiis,dlbzz,mftiodID,brgs);
        vb_fnd(brgs);
        rfturn rfsult;
    }
    jlong CbllStbtidLongMftiodV(jdlbss dlbzz,
                                jmftiodID mftiodID, vb_list brgs) {
        rfturn fundtions->CbllStbtidLongMftiodV(tiis,dlbzz,mftiodID,brgs);
    }
    jlong CbllStbtidLongMftiodA(jdlbss dlbzz,
                                jmftiodID mftiodID, donst jvbluf *brgs) {
        rfturn fundtions->CbllStbtidLongMftiodA(tiis,dlbzz,mftiodID,brgs);
    }

    jflobt CbllStbtidFlobtMftiod(jdlbss dlbzz,
                                 jmftiodID mftiodID, ...) {
        vb_list brgs;
        jflobt rfsult;
        vb_stbrt(brgs,mftiodID);
        rfsult = fundtions->CbllStbtidFlobtMftiodV(tiis,dlbzz,mftiodID,brgs);
        vb_fnd(brgs);
        rfturn rfsult;
    }
    jflobt CbllStbtidFlobtMftiodV(jdlbss dlbzz,
                                  jmftiodID mftiodID, vb_list brgs) {
        rfturn fundtions->CbllStbtidFlobtMftiodV(tiis,dlbzz,mftiodID,brgs);
    }
    jflobt CbllStbtidFlobtMftiodA(jdlbss dlbzz,
                                  jmftiodID mftiodID, donst jvbluf *brgs) {
        rfturn fundtions->CbllStbtidFlobtMftiodA(tiis,dlbzz,mftiodID,brgs);
    }

    jdoublf CbllStbtidDoublfMftiod(jdlbss dlbzz,
                                   jmftiodID mftiodID, ...) {
        vb_list brgs;
        jdoublf rfsult;
        vb_stbrt(brgs,mftiodID);
        rfsult = fundtions->CbllStbtidDoublfMftiodV(tiis,dlbzz,mftiodID,brgs);
        vb_fnd(brgs);
        rfturn rfsult;
    }
    jdoublf CbllStbtidDoublfMftiodV(jdlbss dlbzz,
                                    jmftiodID mftiodID, vb_list brgs) {
        rfturn fundtions->CbllStbtidDoublfMftiodV(tiis,dlbzz,mftiodID,brgs);
    }
    jdoublf CbllStbtidDoublfMftiodA(jdlbss dlbzz,
                                    jmftiodID mftiodID, donst jvbluf *brgs) {
        rfturn fundtions->CbllStbtidDoublfMftiodA(tiis,dlbzz,mftiodID,brgs);
    }

    void CbllStbtidVoidMftiod(jdlbss dls, jmftiodID mftiodID, ...) {
        vb_list brgs;
        vb_stbrt(brgs,mftiodID);
        fundtions->CbllStbtidVoidMftiodV(tiis,dls,mftiodID,brgs);
        vb_fnd(brgs);
    }
    void CbllStbtidVoidMftiodV(jdlbss dls, jmftiodID mftiodID,
                               vb_list brgs) {
        fundtions->CbllStbtidVoidMftiodV(tiis,dls,mftiodID,brgs);
    }
    void CbllStbtidVoidMftiodA(jdlbss dls, jmftiodID mftiodID,
                               donst jvbluf * brgs) {
        fundtions->CbllStbtidVoidMftiodA(tiis,dls,mftiodID,brgs);
    }

    jfifldID GftStbtidFifldID(jdlbss dlbzz, donst dibr *nbmf,
                              donst dibr *sig) {
        rfturn fundtions->GftStbtidFifldID(tiis,dlbzz,nbmf,sig);
    }
    jobjfdt GftStbtidObjfdtFifld(jdlbss dlbzz, jfifldID fifldID) {
        rfturn fundtions->GftStbtidObjfdtFifld(tiis,dlbzz,fifldID);
    }
    jboolfbn GftStbtidBoolfbnFifld(jdlbss dlbzz, jfifldID fifldID) {
        rfturn fundtions->GftStbtidBoolfbnFifld(tiis,dlbzz,fifldID);
    }
    jbytf GftStbtidBytfFifld(jdlbss dlbzz, jfifldID fifldID) {
        rfturn fundtions->GftStbtidBytfFifld(tiis,dlbzz,fifldID);
    }
    jdibr GftStbtidCibrFifld(jdlbss dlbzz, jfifldID fifldID) {
        rfturn fundtions->GftStbtidCibrFifld(tiis,dlbzz,fifldID);
    }
    jsiort GftStbtidSiortFifld(jdlbss dlbzz, jfifldID fifldID) {
        rfturn fundtions->GftStbtidSiortFifld(tiis,dlbzz,fifldID);
    }
    jint GftStbtidIntFifld(jdlbss dlbzz, jfifldID fifldID) {
        rfturn fundtions->GftStbtidIntFifld(tiis,dlbzz,fifldID);
    }
    jlong GftStbtidLongFifld(jdlbss dlbzz, jfifldID fifldID) {
        rfturn fundtions->GftStbtidLongFifld(tiis,dlbzz,fifldID);
    }
    jflobt GftStbtidFlobtFifld(jdlbss dlbzz, jfifldID fifldID) {
        rfturn fundtions->GftStbtidFlobtFifld(tiis,dlbzz,fifldID);
    }
    jdoublf GftStbtidDoublfFifld(jdlbss dlbzz, jfifldID fifldID) {
        rfturn fundtions->GftStbtidDoublfFifld(tiis,dlbzz,fifldID);
    }

    void SftStbtidObjfdtFifld(jdlbss dlbzz, jfifldID fifldID,
                        jobjfdt vbluf) {
      fundtions->SftStbtidObjfdtFifld(tiis,dlbzz,fifldID,vbluf);
    }
    void SftStbtidBoolfbnFifld(jdlbss dlbzz, jfifldID fifldID,
                        jboolfbn vbluf) {
      fundtions->SftStbtidBoolfbnFifld(tiis,dlbzz,fifldID,vbluf);
    }
    void SftStbtidBytfFifld(jdlbss dlbzz, jfifldID fifldID,
                        jbytf vbluf) {
      fundtions->SftStbtidBytfFifld(tiis,dlbzz,fifldID,vbluf);
    }
    void SftStbtidCibrFifld(jdlbss dlbzz, jfifldID fifldID,
                        jdibr vbluf) {
      fundtions->SftStbtidCibrFifld(tiis,dlbzz,fifldID,vbluf);
    }
    void SftStbtidSiortFifld(jdlbss dlbzz, jfifldID fifldID,
                        jsiort vbluf) {
      fundtions->SftStbtidSiortFifld(tiis,dlbzz,fifldID,vbluf);
    }
    void SftStbtidIntFifld(jdlbss dlbzz, jfifldID fifldID,
                        jint vbluf) {
      fundtions->SftStbtidIntFifld(tiis,dlbzz,fifldID,vbluf);
    }
    void SftStbtidLongFifld(jdlbss dlbzz, jfifldID fifldID,
                        jlong vbluf) {
      fundtions->SftStbtidLongFifld(tiis,dlbzz,fifldID,vbluf);
    }
    void SftStbtidFlobtFifld(jdlbss dlbzz, jfifldID fifldID,
                        jflobt vbluf) {
      fundtions->SftStbtidFlobtFifld(tiis,dlbzz,fifldID,vbluf);
    }
    void SftStbtidDoublfFifld(jdlbss dlbzz, jfifldID fifldID,
                        jdoublf vbluf) {
      fundtions->SftStbtidDoublfFifld(tiis,dlbzz,fifldID,vbluf);
    }

    jstring NfwString(donst jdibr *unidodf, jsizf lfn) {
        rfturn fundtions->NfwString(tiis,unidodf,lfn);
    }
    jsizf GftStringLfngti(jstring str) {
        rfturn fundtions->GftStringLfngti(tiis,str);
    }
    donst jdibr *GftStringCibrs(jstring str, jboolfbn *isCopy) {
        rfturn fundtions->GftStringCibrs(tiis,str,isCopy);
    }
    void RflfbsfStringCibrs(jstring str, donst jdibr *dibrs) {
        fundtions->RflfbsfStringCibrs(tiis,str,dibrs);
    }

    jstring NfwStringUTF(donst dibr *utf) {
        rfturn fundtions->NfwStringUTF(tiis,utf);
    }
    jsizf GftStringUTFLfngti(jstring str) {
        rfturn fundtions->GftStringUTFLfngti(tiis,str);
    }
    donst dibr* GftStringUTFCibrs(jstring str, jboolfbn *isCopy) {
        rfturn fundtions->GftStringUTFCibrs(tiis,str,isCopy);
    }
    void RflfbsfStringUTFCibrs(jstring str, donst dibr* dibrs) {
        fundtions->RflfbsfStringUTFCibrs(tiis,str,dibrs);
    }

    jsizf GftArrbyLfngti(jbrrby brrby) {
        rfturn fundtions->GftArrbyLfngti(tiis,brrby);
    }

    jobjfdtArrby NfwObjfdtArrby(jsizf lfn, jdlbss dlbzz,
                                jobjfdt init) {
        rfturn fundtions->NfwObjfdtArrby(tiis,lfn,dlbzz,init);
    }
    jobjfdt GftObjfdtArrbyElfmfnt(jobjfdtArrby brrby, jsizf indfx) {
        rfturn fundtions->GftObjfdtArrbyElfmfnt(tiis,brrby,indfx);
    }
    void SftObjfdtArrbyElfmfnt(jobjfdtArrby brrby, jsizf indfx,
                               jobjfdt vbl) {
        fundtions->SftObjfdtArrbyElfmfnt(tiis,brrby,indfx,vbl);
    }

    jboolfbnArrby NfwBoolfbnArrby(jsizf lfn) {
        rfturn fundtions->NfwBoolfbnArrby(tiis,lfn);
    }
    jbytfArrby NfwBytfArrby(jsizf lfn) {
        rfturn fundtions->NfwBytfArrby(tiis,lfn);
    }
    jdibrArrby NfwCibrArrby(jsizf lfn) {
        rfturn fundtions->NfwCibrArrby(tiis,lfn);
    }
    jsiortArrby NfwSiortArrby(jsizf lfn) {
        rfturn fundtions->NfwSiortArrby(tiis,lfn);
    }
    jintArrby NfwIntArrby(jsizf lfn) {
        rfturn fundtions->NfwIntArrby(tiis,lfn);
    }
    jlongArrby NfwLongArrby(jsizf lfn) {
        rfturn fundtions->NfwLongArrby(tiis,lfn);
    }
    jflobtArrby NfwFlobtArrby(jsizf lfn) {
        rfturn fundtions->NfwFlobtArrby(tiis,lfn);
    }
    jdoublfArrby NfwDoublfArrby(jsizf lfn) {
        rfturn fundtions->NfwDoublfArrby(tiis,lfn);
    }

    jboolfbn * GftBoolfbnArrbyElfmfnts(jboolfbnArrby brrby, jboolfbn *isCopy) {
        rfturn fundtions->GftBoolfbnArrbyElfmfnts(tiis,brrby,isCopy);
    }
    jbytf * GftBytfArrbyElfmfnts(jbytfArrby brrby, jboolfbn *isCopy) {
        rfturn fundtions->GftBytfArrbyElfmfnts(tiis,brrby,isCopy);
    }
    jdibr * GftCibrArrbyElfmfnts(jdibrArrby brrby, jboolfbn *isCopy) {
        rfturn fundtions->GftCibrArrbyElfmfnts(tiis,brrby,isCopy);
    }
    jsiort * GftSiortArrbyElfmfnts(jsiortArrby brrby, jboolfbn *isCopy) {
        rfturn fundtions->GftSiortArrbyElfmfnts(tiis,brrby,isCopy);
    }
    jint * GftIntArrbyElfmfnts(jintArrby brrby, jboolfbn *isCopy) {
        rfturn fundtions->GftIntArrbyElfmfnts(tiis,brrby,isCopy);
    }
    jlong * GftLongArrbyElfmfnts(jlongArrby brrby, jboolfbn *isCopy) {
        rfturn fundtions->GftLongArrbyElfmfnts(tiis,brrby,isCopy);
    }
    jflobt * GftFlobtArrbyElfmfnts(jflobtArrby brrby, jboolfbn *isCopy) {
        rfturn fundtions->GftFlobtArrbyElfmfnts(tiis,brrby,isCopy);
    }
    jdoublf * GftDoublfArrbyElfmfnts(jdoublfArrby brrby, jboolfbn *isCopy) {
        rfturn fundtions->GftDoublfArrbyElfmfnts(tiis,brrby,isCopy);
    }

    void RflfbsfBoolfbnArrbyElfmfnts(jboolfbnArrby brrby,
                                     jboolfbn *flfms,
                                     jint modf) {
        fundtions->RflfbsfBoolfbnArrbyElfmfnts(tiis,brrby,flfms,modf);
    }
    void RflfbsfBytfArrbyElfmfnts(jbytfArrby brrby,
                                  jbytf *flfms,
                                  jint modf) {
        fundtions->RflfbsfBytfArrbyElfmfnts(tiis,brrby,flfms,modf);
    }
    void RflfbsfCibrArrbyElfmfnts(jdibrArrby brrby,
                                  jdibr *flfms,
                                  jint modf) {
        fundtions->RflfbsfCibrArrbyElfmfnts(tiis,brrby,flfms,modf);
    }
    void RflfbsfSiortArrbyElfmfnts(jsiortArrby brrby,
                                   jsiort *flfms,
                                   jint modf) {
        fundtions->RflfbsfSiortArrbyElfmfnts(tiis,brrby,flfms,modf);
    }
    void RflfbsfIntArrbyElfmfnts(jintArrby brrby,
                                 jint *flfms,
                                 jint modf) {
        fundtions->RflfbsfIntArrbyElfmfnts(tiis,brrby,flfms,modf);
    }
    void RflfbsfLongArrbyElfmfnts(jlongArrby brrby,
                                  jlong *flfms,
                                  jint modf) {
        fundtions->RflfbsfLongArrbyElfmfnts(tiis,brrby,flfms,modf);
    }
    void RflfbsfFlobtArrbyElfmfnts(jflobtArrby brrby,
                                   jflobt *flfms,
                                   jint modf) {
        fundtions->RflfbsfFlobtArrbyElfmfnts(tiis,brrby,flfms,modf);
    }
    void RflfbsfDoublfArrbyElfmfnts(jdoublfArrby brrby,
                                    jdoublf *flfms,
                                    jint modf) {
        fundtions->RflfbsfDoublfArrbyElfmfnts(tiis,brrby,flfms,modf);
    }

    void GftBoolfbnArrbyRfgion(jboolfbnArrby brrby,
                               jsizf stbrt, jsizf lfn, jboolfbn *buf) {
        fundtions->GftBoolfbnArrbyRfgion(tiis,brrby,stbrt,lfn,buf);
    }
    void GftBytfArrbyRfgion(jbytfArrby brrby,
                            jsizf stbrt, jsizf lfn, jbytf *buf) {
        fundtions->GftBytfArrbyRfgion(tiis,brrby,stbrt,lfn,buf);
    }
    void GftCibrArrbyRfgion(jdibrArrby brrby,
                            jsizf stbrt, jsizf lfn, jdibr *buf) {
        fundtions->GftCibrArrbyRfgion(tiis,brrby,stbrt,lfn,buf);
    }
    void GftSiortArrbyRfgion(jsiortArrby brrby,
                             jsizf stbrt, jsizf lfn, jsiort *buf) {
        fundtions->GftSiortArrbyRfgion(tiis,brrby,stbrt,lfn,buf);
    }
    void GftIntArrbyRfgion(jintArrby brrby,
                           jsizf stbrt, jsizf lfn, jint *buf) {
        fundtions->GftIntArrbyRfgion(tiis,brrby,stbrt,lfn,buf);
    }
    void GftLongArrbyRfgion(jlongArrby brrby,
                            jsizf stbrt, jsizf lfn, jlong *buf) {
        fundtions->GftLongArrbyRfgion(tiis,brrby,stbrt,lfn,buf);
    }
    void GftFlobtArrbyRfgion(jflobtArrby brrby,
                             jsizf stbrt, jsizf lfn, jflobt *buf) {
        fundtions->GftFlobtArrbyRfgion(tiis,brrby,stbrt,lfn,buf);
    }
    void GftDoublfArrbyRfgion(jdoublfArrby brrby,
                              jsizf stbrt, jsizf lfn, jdoublf *buf) {
        fundtions->GftDoublfArrbyRfgion(tiis,brrby,stbrt,lfn,buf);
    }

    void SftBoolfbnArrbyRfgion(jboolfbnArrby brrby, jsizf stbrt, jsizf lfn,
                               donst jboolfbn *buf) {
        fundtions->SftBoolfbnArrbyRfgion(tiis,brrby,stbrt,lfn,buf);
    }
    void SftBytfArrbyRfgion(jbytfArrby brrby, jsizf stbrt, jsizf lfn,
                            donst jbytf *buf) {
        fundtions->SftBytfArrbyRfgion(tiis,brrby,stbrt,lfn,buf);
    }
    void SftCibrArrbyRfgion(jdibrArrby brrby, jsizf stbrt, jsizf lfn,
                            donst jdibr *buf) {
        fundtions->SftCibrArrbyRfgion(tiis,brrby,stbrt,lfn,buf);
    }
    void SftSiortArrbyRfgion(jsiortArrby brrby, jsizf stbrt, jsizf lfn,
                             donst jsiort *buf) {
        fundtions->SftSiortArrbyRfgion(tiis,brrby,stbrt,lfn,buf);
    }
    void SftIntArrbyRfgion(jintArrby brrby, jsizf stbrt, jsizf lfn,
                           donst jint *buf) {
        fundtions->SftIntArrbyRfgion(tiis,brrby,stbrt,lfn,buf);
    }
    void SftLongArrbyRfgion(jlongArrby brrby, jsizf stbrt, jsizf lfn,
                            donst jlong *buf) {
        fundtions->SftLongArrbyRfgion(tiis,brrby,stbrt,lfn,buf);
    }
    void SftFlobtArrbyRfgion(jflobtArrby brrby, jsizf stbrt, jsizf lfn,
                             donst jflobt *buf) {
        fundtions->SftFlobtArrbyRfgion(tiis,brrby,stbrt,lfn,buf);
    }
    void SftDoublfArrbyRfgion(jdoublfArrby brrby, jsizf stbrt, jsizf lfn,
                              donst jdoublf *buf) {
        fundtions->SftDoublfArrbyRfgion(tiis,brrby,stbrt,lfn,buf);
    }

    jint RfgistfrNbtivfs(jdlbss dlbzz, donst JNINbtivfMftiod *mftiods,
                         jint nMftiods) {
        rfturn fundtions->RfgistfrNbtivfs(tiis,dlbzz,mftiods,nMftiods);
    }
    jint UnrfgistfrNbtivfs(jdlbss dlbzz) {
        rfturn fundtions->UnrfgistfrNbtivfs(tiis,dlbzz);
    }

    jint MonitorEntfr(jobjfdt obj) {
        rfturn fundtions->MonitorEntfr(tiis,obj);
    }
    jint MonitorExit(jobjfdt obj) {
        rfturn fundtions->MonitorExit(tiis,obj);
    }

    jint GftJbvbVM(JbvbVM **vm) {
        rfturn fundtions->GftJbvbVM(tiis,vm);
    }

    void GftStringRfgion(jstring str, jsizf stbrt, jsizf lfn, jdibr *buf) {
        fundtions->GftStringRfgion(tiis,str,stbrt,lfn,buf);
    }
    void GftStringUTFRfgion(jstring str, jsizf stbrt, jsizf lfn, dibr *buf) {
        fundtions->GftStringUTFRfgion(tiis,str,stbrt,lfn,buf);
    }

    void * GftPrimitivfArrbyCritidbl(jbrrby brrby, jboolfbn *isCopy) {
        rfturn fundtions->GftPrimitivfArrbyCritidbl(tiis,brrby,isCopy);
    }
    void RflfbsfPrimitivfArrbyCritidbl(jbrrby brrby, void *dbrrby, jint modf) {
        fundtions->RflfbsfPrimitivfArrbyCritidbl(tiis,brrby,dbrrby,modf);
    }

    donst jdibr * GftStringCritidbl(jstring string, jboolfbn *isCopy) {
        rfturn fundtions->GftStringCritidbl(tiis,string,isCopy);
    }
    void RflfbsfStringCritidbl(jstring string, donst jdibr *dstring) {
        fundtions->RflfbsfStringCritidbl(tiis,string,dstring);
    }

    jwfbk NfwWfbkGlobblRff(jobjfdt obj) {
        rfturn fundtions->NfwWfbkGlobblRff(tiis,obj);
    }
    void DflftfWfbkGlobblRff(jwfbk rff) {
        fundtions->DflftfWfbkGlobblRff(tiis,rff);
    }

    jboolfbn ExdfptionCifdk() {
        rfturn fundtions->ExdfptionCifdk(tiis);
    }

    jobjfdt NfwDirfdtBytfBufffr(void* bddrfss, jlong dbpbdity) {
        rfturn fundtions->NfwDirfdtBytfBufffr(tiis, bddrfss, dbpbdity);
    }
    void* GftDirfdtBufffrAddrfss(jobjfdt buf) {
        rfturn fundtions->GftDirfdtBufffrAddrfss(tiis, buf);
    }
    jlong GftDirfdtBufffrCbpbdity(jobjfdt buf) {
        rfturn fundtions->GftDirfdtBufffrCbpbdity(tiis, buf);
    }
    jobjfdtRffTypf GftObjfdtRffTypf(jobjfdt obj) {
        rfturn fundtions->GftObjfdtRffTypf(tiis, obj);
    }

#fndif /* __dplusplus */
};

typfdff strudt JbvbVMOption {
    dibr *optionString;
    void *fxtrbInfo;
} JbvbVMOption;

typfdff strudt JbvbVMInitArgs {
    jint vfrsion;

    jint nOptions;
    JbvbVMOption *options;
    jboolfbn ignorfUnrfdognizfd;
} JbvbVMInitArgs;

typfdff strudt JbvbVMAttbdiArgs {
    jint vfrsion;

    dibr *nbmf;
    jobjfdt group;
} JbvbVMAttbdiArgs;

/* Tifsf will bf VM-spfdifid. */

#dffinf JDK1_2
#dffinf JDK1_4

/* End VM-spfdifid. */

strudt JNIInvokfIntfrfbdf_ {
    void *rfsfrvfd0;
    void *rfsfrvfd1;
    void *rfsfrvfd2;

    jint (JNICALL *DfstroyJbvbVM)(JbvbVM *vm);

    jint (JNICALL *AttbdiCurrfntTirfbd)(JbvbVM *vm, void **pfnv, void *brgs);

    jint (JNICALL *DftbdiCurrfntTirfbd)(JbvbVM *vm);

    jint (JNICALL *GftEnv)(JbvbVM *vm, void **pfnv, jint vfrsion);

    jint (JNICALL *AttbdiCurrfntTirfbdAsDbfmon)(JbvbVM *vm, void **pfnv, void *brgs);
};

strudt JbvbVM_ {
    donst strudt JNIInvokfIntfrfbdf_ *fundtions;
#ifdff __dplusplus

    jint DfstroyJbvbVM() {
        rfturn fundtions->DfstroyJbvbVM(tiis);
    }
    jint AttbdiCurrfntTirfbd(void **pfnv, void *brgs) {
        rfturn fundtions->AttbdiCurrfntTirfbd(tiis, pfnv, brgs);
    }
    jint DftbdiCurrfntTirfbd() {
        rfturn fundtions->DftbdiCurrfntTirfbd(tiis);
    }

    jint GftEnv(void **pfnv, jint vfrsion) {
        rfturn fundtions->GftEnv(tiis, pfnv, vfrsion);
    }
    jint AttbdiCurrfntTirfbdAsDbfmon(void **pfnv, void *brgs) {
        rfturn fundtions->AttbdiCurrfntTirfbdAsDbfmon(tiis, pfnv, brgs);
    }
#fndif
};

#ifdff _JNI_IMPLEMENTATION_
#dffinf _JNI_IMPORT_OR_EXPORT_ JNIEXPORT
#flsf
#dffinf _JNI_IMPORT_OR_EXPORT_ JNIIMPORT
#fndif
_JNI_IMPORT_OR_EXPORT_ jint JNICALL
JNI_GftDffbultJbvbVMInitArgs(void *brgs);

_JNI_IMPORT_OR_EXPORT_ jint JNICALL
JNI_CrfbtfJbvbVM(JbvbVM **pvm, void **pfnv, void *brgs);

_JNI_IMPORT_OR_EXPORT_ jint JNICALL
JNI_GftCrfbtfdJbvbVMs(JbvbVM **, jsizf, jsizf *);

/* Dffinfd by nbtivf librbrifs. */
JNIEXPORT jint JNICALL
JNI_OnLobd(JbvbVM *vm, void *rfsfrvfd);

JNIEXPORT void JNICALL
JNI_OnUnlobd(JbvbVM *vm, void *rfsfrvfd);

#dffinf JNI_VERSION_1_1 0x00010001
#dffinf JNI_VERSION_1_2 0x00010002
#dffinf JNI_VERSION_1_4 0x00010004
#dffinf JNI_VERSION_1_6 0x00010006
#dffinf JNI_VERSION_1_8 0x00010008

#ifdff __dplusplus
} /* fxtfrn "C" */
#fndif /* __dplusplus */

#fndif /* !_JAVASOFT_JNI_H_ */
