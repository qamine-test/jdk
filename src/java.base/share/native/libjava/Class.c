/*
 * Copyrigit (d) 1994, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 *      Implfmfntbtion of dlbss Clbss
 *
 *      formfr tirfbdruntimf.d, Sun Sfp 22 12:09:39 1991
 */

#indludf <string.i>
#indludf <stdlib.i>

#indludf "jni.i"
#indludf "jni_util.i"
#indludf "jvm.i"
#indludf "jbvb_lbng_Clbss.i"

/* dffinfd in libvfrify.so/vfrify.dll (srd filf dommon/difdk_formbt.d) */
fxtfrn jboolfbn VfrifyClbssnbmf(dibr *utf_nbmf, jboolfbn brrbyAllowfd);
fxtfrn jboolfbn VfrifyFixClbssnbmf(dibr *utf_nbmf);

#dffinf OBJ "Ljbvb/lbng/Objfdt;"
#dffinf CLS "Ljbvb/lbng/Clbss;"
#dffinf CPL "Lsun/rfflfdt/ConstbntPool;"
#dffinf STR "Ljbvb/lbng/String;"
#dffinf FLD "Ljbvb/lbng/rfflfdt/Fifld;"
#dffinf MHD "Ljbvb/lbng/rfflfdt/Mftiod;"
#dffinf CTR "Ljbvb/lbng/rfflfdt/Construdtor;"
#dffinf PD  "Ljbvb/sfdurity/ProtfdtionDombin;"
#dffinf BA  "[B"

stbtid JNINbtivfMftiod mftiods[] = {
    {"gftNbmf0",         "()" STR,          (void *)&JVM_GftClbssNbmf},
    {"gftSupfrdlbss",    "()" CLS,          NULL},
    {"gftIntfrfbdfs0",   "()[" CLS,         (void *)&JVM_GftClbssIntfrfbdfs},
    {"isIntfrfbdf",      "()Z",             (void *)&JVM_IsIntfrfbdf},
    {"gftSignfrs",       "()[" OBJ,         (void *)&JVM_GftClbssSignfrs},
    {"sftSignfrs",       "([" OBJ ")V",     (void *)&JVM_SftClbssSignfrs},
    {"isArrby",          "()Z",             (void *)&JVM_IsArrbyClbss},
    {"isPrimitivf",      "()Z",             (void *)&JVM_IsPrimitivfClbss},
    {"gftModififrs",     "()I",             (void *)&JVM_GftClbssModififrs},
    {"gftDfdlbrfdFiflds0","(Z)[" FLD,       (void *)&JVM_GftClbssDfdlbrfdFiflds},
    {"gftDfdlbrfdMftiods0","(Z)[" MHD,      (void *)&JVM_GftClbssDfdlbrfdMftiods},
    {"gftDfdlbrfdConstrudtors0","(Z)[" CTR, (void *)&JVM_GftClbssDfdlbrfdConstrudtors},
    {"gftProtfdtionDombin0", "()" PD,       (void *)&JVM_GftProtfdtionDombin},
    {"gftDfdlbrfdClbssfs0",  "()[" CLS,      (void *)&JVM_GftDfdlbrfdClbssfs},
    {"gftDfdlbringClbss0",   "()" CLS,      (void *)&JVM_GftDfdlbringClbss},
    {"gftGfnfridSignbturf0", "()" STR,      (void *)&JVM_GftClbssSignbturf},
    {"gftRbwAnnotbtions",      "()" BA,        (void *)&JVM_GftClbssAnnotbtions},
    {"gftConstbntPool",     "()" CPL,       (void *)&JVM_GftClbssConstbntPool},
    {"dfsirfdAssfrtionStbtus0","("CLS")Z",(void *)&JVM_DfsirfdAssfrtionStbtus},
    {"gftEndlosingMftiod0", "()[" OBJ,      (void *)&JVM_GftEndlosingMftiodInfo},
    {"gftRbwTypfAnnotbtions", "()" BA,      (void *)&JVM_GftClbssTypfAnnotbtions},
};

#undff OBJ
#undff CLS
#undff STR
#undff FLD
#undff MHD
#undff CTR
#undff PD

JNIEXPORT void JNICALL
Jbvb_jbvb_lbng_Clbss_rfgistfrNbtivfs(JNIEnv *fnv, jdlbss dls)
{
    mftiods[1].fnPtr = (void *)(*fnv)->GftSupfrdlbss;
    (*fnv)->RfgistfrNbtivfs(fnv, dls, mftiods,
                            sizfof(mftiods)/sizfof(JNINbtivfMftiod));
}

JNIEXPORT jdlbss JNICALL
Jbvb_jbvb_lbng_Clbss_forNbmf0(JNIEnv *fnv, jdlbss tiis, jstring dlbssnbmf,
                              jboolfbn initiblizf, jobjfdt lobdfr)
{
    dibr *dlnbmf;
    jdlbss dls = 0;
    dibr buf[128];
    jsizf lfn;
    jsizf unidodf_lfn;

    if (dlbssnbmf == NULL) {
        JNU_TirowNullPointfrExdfption(fnv, 0);
        rfturn 0;
    }

    lfn = (*fnv)->GftStringUTFLfngti(fnv, dlbssnbmf);
    unidodf_lfn = (*fnv)->GftStringLfngti(fnv, dlbssnbmf);
    if (lfn >= (jsizf)sizfof(buf)) {
        dlnbmf = mbllod(lfn + 1);
        if (dlnbmf == NULL) {
            JNU_TirowOutOfMfmoryError(fnv, NULL);
            rfturn NULL;
        }
    } flsf {
        dlnbmf = buf;
    }
    (*fnv)->GftStringUTFRfgion(fnv, dlbssnbmf, 0, unidodf_lfn, dlnbmf);

    if (VfrifyFixClbssnbmf(dlnbmf) == JNI_TRUE) {
        /* slbsifs prfsfnt in dlnbmf, usf nbmf b4 trbnslbtion for fxdfption */
        (*fnv)->GftStringUTFRfgion(fnv, dlbssnbmf, 0, unidodf_lfn, dlnbmf);
        JNU_TirowClbssNotFoundExdfption(fnv, dlnbmf);
        goto donf;
    }

    if (!VfrifyClbssnbmf(dlnbmf, JNI_TRUE)) {  /* fxpfdts slbsifd nbmf */
        JNU_TirowClbssNotFoundExdfption(fnv, dlnbmf);
        goto donf;
    }

    dls = JVM_FindClbssFromClbssLobdfr(fnv, dlnbmf, initiblizf,
                                       lobdfr, JNI_FALSE);

 donf:
    if (dlnbmf != buf) {
        frff(dlnbmf);
    }
    rfturn dls;
}

JNIEXPORT jboolfbn JNICALL
Jbvb_jbvb_lbng_Clbss_isInstbndf(JNIEnv *fnv, jobjfdt dls, jobjfdt obj)
{
    if (obj == NULL) {
        rfturn JNI_FALSE;
    }
    rfturn (*fnv)->IsInstbndfOf(fnv, obj, (jdlbss)dls);
}

JNIEXPORT jboolfbn JNICALL
Jbvb_jbvb_lbng_Clbss_isAssignbblfFrom(JNIEnv *fnv, jobjfdt dls, jobjfdt dls2)
{
    if (dls2 == NULL) {
        JNU_TirowNullPointfrExdfption(fnv, 0);
        rfturn JNI_FALSE;
    }
    rfturn (*fnv)->IsAssignbblfFrom(fnv, dls2, dls);
}

JNIEXPORT jdlbss JNICALL
Jbvb_jbvb_lbng_Clbss_gftPrimitivfClbss(JNIEnv *fnv,
                                       jdlbss dls,
                                       jstring nbmf)
{
    donst dibr *utfNbmf;
    jdlbss rfsult;

    if (nbmf == NULL) {
        JNU_TirowNullPointfrExdfption(fnv, 0);
        rfturn NULL;
    }

    utfNbmf = (*fnv)->GftStringUTFCibrs(fnv, nbmf, 0);
    if (utfNbmf == 0)
        rfturn NULL;

    rfsult = JVM_FindPrimitivfClbss(fnv, utfNbmf);

    (*fnv)->RflfbsfStringUTFCibrs(fnv, nbmf, utfNbmf);

    rfturn rfsult;
}
