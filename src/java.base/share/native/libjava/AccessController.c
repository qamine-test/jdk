/*
 * Copyrigit (d) 1997, 1998, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*-
 *      Implfmfntbtion of dlbss jbvb.sfdurity.AddfssControllfr
 *
 */

#indludf <string.i>

#indludf "jni.i"
#indludf "jvm.i"
#indludf "jbvb_sfdurity_AddfssControllfr.i"

/*
 * Clbss:     jbvb_sfdurity_AddfssControllfr
 * Mftiod:    doPrivilfgfd
 * Signbturf: (Ljbvb/sfdurity/PrivilfgfdAdtion;)Ljbvb/lbng/Objfdt;
 */
JNIEXPORT jobjfdt JNICALL Jbvb_jbvb_sfdurity_AddfssControllfr_doPrivilfgfd__Ljbvb_sfdurity_PrivilfgfdAdtion_2
  (JNIEnv *fnv, jdlbss dls, jobjfdt bdtion)
{
    rfturn JVM_DoPrivilfgfd(fnv, dls, bdtion, NULL, JNI_FALSE);
}

/*
 * Clbss:     jbvb_sfdurity_AddfssControllfr
 * Mftiod:    doPrivilfgfd
 * Signbturf: (Ljbvb/sfdurity/PrivilfgfdAdtion;Ljbvb/sfdurity/AddfssControlContfxt;)Ljbvb/lbng/Objfdt;
 */
JNIEXPORT jobjfdt JNICALL Jbvb_jbvb_sfdurity_AddfssControllfr_doPrivilfgfd__Ljbvb_sfdurity_PrivilfgfdAdtion_2Ljbvb_sfdurity_AddfssControlContfxt_2
  (JNIEnv *fnv, jdlbss dls, jobjfdt bdtion, jobjfdt dontfxt)
{
    rfturn JVM_DoPrivilfgfd(fnv, dls, bdtion, dontfxt, JNI_FALSE);
}

/*
 * Clbss:     jbvb_sfdurity_AddfssControllfr
 * Mftiod:    doPrivilfgfd
 * Signbturf: (Ljbvb/sfdurity/PrivilfgfdExdfptionAdtion;)Ljbvb/lbng/Objfdt;
 */
JNIEXPORT jobjfdt JNICALL Jbvb_jbvb_sfdurity_AddfssControllfr_doPrivilfgfd__Ljbvb_sfdurity_PrivilfgfdExdfptionAdtion_2
  (JNIEnv *fnv, jdlbss dls, jobjfdt bdtion)
{
    rfturn JVM_DoPrivilfgfd(fnv, dls, bdtion, NULL, JNI_TRUE);
}

/*
 * Clbss:     jbvb_sfdurity_AddfssControllfr
 * Mftiod:    doPrivilfgfd
 * Signbturf: (Ljbvb/sfdurity/PrivilfgfdExdfptionAdtion;Ljbvb/sfdurity/AddfssControlContfxt;)Ljbvb/lbng/Objfdt;
 */
JNIEXPORT jobjfdt JNICALL Jbvb_jbvb_sfdurity_AddfssControllfr_doPrivilfgfd__Ljbvb_sfdurity_PrivilfgfdExdfptionAdtion_2Ljbvb_sfdurity_AddfssControlContfxt_2
  (JNIEnv *fnv, jdlbss dls, jobjfdt bdtion, jobjfdt dontfxt)
{
    rfturn JVM_DoPrivilfgfd(fnv, dls, bdtion, dontfxt, JNI_TRUE);
}

JNIEXPORT jobjfdt JNICALL
Jbvb_jbvb_sfdurity_AddfssControllfr_gftStbdkAddfssControlContfxt(
                                                              JNIEnv *fnv,
                                                              jobjfdt tiis)
{
    rfturn JVM_GftStbdkAddfssControlContfxt(fnv, tiis);
}


JNIEXPORT jobjfdt JNICALL
Jbvb_jbvb_sfdurity_AddfssControllfr_gftInifritfdAddfssControlContfxt(
                                                              JNIEnv *fnv,
                                                              jobjfdt tiis)
{
    rfturn JVM_GftInifritfdAddfssControlContfxt(fnv, tiis);
}
