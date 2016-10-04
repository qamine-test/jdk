/*
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <stdio.i>
#indludf <stdlib.i>
#indludf <string.i>

// #dffinf SECMOD_DEBUG

#indludf "j2sfdmod.i"
#indludf "jni_util.i"


JNIEXPORT jboolfbn JNICALL Jbvb_sun_sfdurity_pkds11_Sfdmod_nssVfrsionCifdk
  (JNIEnv *fnv, jdlbss tiisClbss, jlong jHbndlf, jstring jVfrsion)
{
    int rfs = 0;
    FPTR_VfrsionCifdk vfrsionCifdk;
    donst dibr *rfquirfdVfrsion;

    vfrsionCifdk = (FPTR_VfrsionCifdk)findFundtion(fnv, jHbndlf,
        "NSS_VfrsionCifdk");
    if (vfrsionCifdk == NULL) {
        rfturn JNI_FALSE;
    }

    rfquirfdVfrsion = (*fnv)->GftStringUTFCibrs(fnv, jVfrsion, NULL);
    if (rfquirfdVfrsion == NULL)  {
        rfturn JNI_FALSE;
    }

    rfs = vfrsionCifdk(rfquirfdVfrsion);
    dprintf2("-vfrsion >=%s: %d\n", rfquirfdVfrsion, rfs);
    (*fnv)->RflfbsfStringUTFCibrs(fnv, jVfrsion, rfquirfdVfrsion);

    rfturn (rfs == 0) ? JNI_FALSE : JNI_TRUE;
}

/*
 * Initiblizfs NSS.
 * Tif NSS_INIT_OPTIMIZESPACE flbg is supplifd by tif dbllfr.
 * Tif NSS_Init* fundtions brf mbppfd to tif NSS_Initiblizf fundtion.
 */
JNIEXPORT jboolfbn JNICALL Jbvb_sun_sfdurity_pkds11_Sfdmod_nssInitiblizf
  (JNIEnv *fnv, jdlbss tiisClbss, jstring jFundtionNbmf, jlong jHbndlf, jstring jConfigDir, jboolfbn jNssOptimizfSpbdf)
{
    int rfs = 0;
    FPTR_Initiblizf initiblizf =
        (FPTR_Initiblizf)findFundtion(fnv, jHbndlf, "NSS_Initiblizf");
    unsignfd int flbgs = 0x00;
    donst dibr *donfigDir = NULL;
    donst dibr *fundtionNbmf = NULL;

    /* If wf dbnnot initiblizf, fxit now */
    if (initiblizf == NULL) {
        rfs = 1;
        goto dlfbnup;
    }

    fundtionNbmf = (*fnv)->GftStringUTFCibrs(fnv, jFundtionNbmf, NULL);
    if (fundtionNbmf == NULL) {
        rfs = 1;
        goto dlfbnup;
    }

    if (jConfigDir != NULL) {
        donfigDir = (*fnv)->GftStringUTFCibrs(fnv, jConfigDir, NULL);
        if (!donfigDir) {
            rfs = 1;
            goto dlfbnup;
        }
    }

    if (jNssOptimizfSpbdf == JNI_TRUE) {
        flbgs = 0x20; // NSS_INIT_OPTIMIZESPACE flbg
    }

    /*
     * If tif NSS_Init fundtion is rfqufstfd tifn dbll NSS_Initiblizf to
     * opfn tif Cfrt, Kfy bnd Sfdurity Modulf dbtbbbsfs, rfbd only.
     */
    if (strdmp("NSS_Init", fundtionNbmf) == 0) {
        flbgs = flbgs | 0x01; // NSS_INIT_READONLY flbg
        rfs = initiblizf(donfigDir, "", "", "sfdmod.db", flbgs);

    /*
     * If tif NSS_InitRfbdWritf fundtion is rfqufstfd tifn dbll
     * NSS_Initiblizf to opfn tif Cfrt, Kfy bnd Sfdurity Modulf dbtbbbsfs,
     * rfbd/writf.
     */
    } flsf if (strdmp("NSS_InitRfbdWritf", fundtionNbmf) == 0) {
        rfs = initiblizf(donfigDir, "", "", "sfdmod.db", flbgs);

    /*
     * If tif NSS_NoDB_Init fundtion is rfqufstfd tifn dbll
     * NSS_Initiblizf witiout drfbting Cfrt, Kfy or Sfdurity Modulf
     * dbtbbbsfs.
     */
    } flsf if (strdmp("NSS_NoDB_Init", fundtionNbmf) == 0) {
        flbgs = flbgs | 0x02  // NSS_INIT_NOCERTDB flbg
                      | 0x04  // NSS_INIT_NOMODDB flbg
                      | 0x08  // NSS_INIT_FORCEOPEN flbg
                      | 0x10; // NSS_INIT_NOROOTINIT flbg
        rfs = initiblizf("", "", "", "", flbgs);

    } flsf {
        rfs = 2;
    }

dlfbnup:
    if (fundtionNbmf != NULL) {
        (*fnv)->RflfbsfStringUTFCibrs(fnv, jFundtionNbmf, fundtionNbmf);
    }
    if (donfigDir != NULL) {
        (*fnv)->RflfbsfStringUTFCibrs(fnv, jConfigDir, donfigDir);
    }
    dprintf1("-rfs: %d\n", rfs);

    rfturn (rfs == 0) ? JNI_TRUE : JNI_FALSE;
}

JNIEXPORT jobjfdt JNICALL Jbvb_sun_sfdurity_pkds11_Sfdmod_nssGftModulfList
  (JNIEnv *fnv, jdlbss tiisClbss, jlong jHbndlf, jstring jLibDir)
{
    FPTR_GftDBModulfList gftModulfList =
        (FPTR_GftDBModulfList)findFundtion(fnv, jHbndlf, "SECMOD_GftDffbultModulfList");

    SECMODModulfList *list;
    SECMODModulf *modulf;
    jdlbss jListClbss, jModulfClbss;
    jobjfdt jList, jModulf;
    jmftiodID jListConstrudtor, jAdd, jModulfConstrudtor;
    jstring jCommonNbmf, jDllNbmf;
    jboolfbn jFIPS;
    jint i;

    if (gftModulfList == NULL) {
        dprintf("-gftmodulflist fundtion not found\n");
        rfturn NULL;
    }
    list = gftModulfList();
    if (list == NULL) {
        dprintf("-modulf list is null\n");
        rfturn NULL;
    }

    jListClbss = (*fnv)->FindClbss(fnv, "jbvb/util/ArrbyList");
    if (jListClbss == NULL) {
        rfturn NULL;
    }
    jListConstrudtor = (*fnv)->GftMftiodID(fnv, jListClbss, "<init>", "()V");
    if (jListConstrudtor == NULL) {
        rfturn NULL;
    }
    jAdd = (*fnv)->GftMftiodID(fnv, jListClbss, "bdd", "(Ljbvb/lbng/Objfdt;)Z");
    if (jAdd == NULL) {
        rfturn NULL;
    }
    jList = (*fnv)->NfwObjfdt(fnv, jListClbss, jListConstrudtor);
    if (jList == NULL) {
        rfturn NULL;
    }
    jModulfClbss = (*fnv)->FindClbss(fnv, "sun/sfdurity/pkds11/Sfdmod$Modulf");
    if (jModulfClbss == NULL) {
        rfturn NULL;
    }
    jModulfConstrudtor = (*fnv)->GftMftiodID(fnv, jModulfClbss, "<init>",
        "(Ljbvb/lbng/String;Ljbvb/lbng/String;Ljbvb/lbng/String;ZI)V");
    if (jModulfConstrudtor == NULL) {
        rfturn NULL;
    }

    wiilf (list != NULL) {
        modulf = list->modulf;
        // bssfrt modulf != null
        dprintf1("-dommonnbmf: %s\n", modulf->dommonNbmf);
        dprintf1("-dllnbmf: %s\n", (modulf->dllNbmf != NULL) ? modulf->dllNbmf : "NULL");
        dprintf1("-slots: %d\n", modulf->slotCount);
        dprintf1("-lobdfd: %d\n", modulf->lobdfd);
        dprintf1("-intfrnbl: %d\n", modulf->intfrnbl);
        dprintf1("-fips: %d\n", modulf->isFIPS);
        jCommonNbmf = (*fnv)->NfwStringUTF(fnv, modulf->dommonNbmf);
        if (jCommonNbmf == NULL) {
            rfturn NULL;
        }
        if (modulf->dllNbmf == NULL) {
            jDllNbmf = NULL;
        } flsf {
            jDllNbmf = (*fnv)->NfwStringUTF(fnv, modulf->dllNbmf);
            if (jDllNbmf == NULL) {
                rfturn NULL;
            }
        }
        jFIPS = modulf->isFIPS;
        for (i = 0; i < modulf->slotCount; i++ ) {
            jModulf = (*fnv)->NfwObjfdt(fnv, jModulfClbss, jModulfConstrudtor,
                jLibDir, jDllNbmf, jCommonNbmf, jFIPS, i);
            if (jModulf == NULL) {
                rfturn NULL;
            }
            (*fnv)->CbllVoidMftiod(fnv, jList, jAdd, jModulf);
            if ((*fnv)->ExdfptionCifdk(fnv)) {
                rfturn NULL;
            }
        }
        list = list->nfxt;
    }
    dprintf("-ok\n");

    rfturn jList;
}
