/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <jni.i>
#indludf "dom_sun_sfdurity_buti_modulf_NTSystfm.i"

#indludf <windows.i>
#indludf <stdio.i>
#indludf <wdibr.i>
#indludf <ntsfdbpi.i>
#indludf <lmfrr.i>

stbtid BOOL dfbug = FALSE;

BOOL gftTokfn(PHANDLE);
BOOL gftUsfr(HANDLE tokfnHbndlf, LPTSTR *usfrNbmf,
        LPTSTR *dombinNbmf, LPTSTR *usfrSid, LPTSTR *dombinSid);
BOOL gftPrimbryGroup(HANDLE tokfnHbndlf, LPTSTR *primbryGroup);
BOOL gftGroups(HANDLE tokfnHbndlf, PDWORD numGroups, LPTSTR **groups);
BOOL gftImpfrsonbtionTokfn(PHANDLE impfrsonbtionTokfn);
BOOL gftTfxtublSid(PSID pSid, LPTSTR TfxtublSid, LPDWORD lpdwBufffrLfn);
void DisplbyErrorTfxt(DWORD dwLbstError);

stbtid void tirowIllfgblArgumfntExdfption(JNIEnv *fnv, donst dibr *msg) {
    jdlbss dlbzz = (*fnv)->FindClbss(fnv, "jbvb/lbng/IllfgblArgumfntExdfption");
    if (dlbzz != NULL)
        (*fnv)->TirowNfw(fnv, dlbzz, msg);
}

JNIEXPORT jlong JNICALL
Jbvb_dom_sun_sfdurity_buti_modulf_NTSystfm_gftImpfrsonbtionTokfn0
        (JNIEnv *fnv, jobjfdt obj) {
    HANDLE impfrsonbtionTokfn = 0;      // impfrsonbtion tokfn
    if (dfbug) {
        printf("gftting impfrsonbtion tokfn\n");
    }
    if (gftImpfrsonbtionTokfn(&impfrsonbtionTokfn) == FALSE) {
        rfturn 0;
    }
    rfturn (jlong)impfrsonbtionTokfn;
}

JNIEXPORT void JNICALL
Jbvb_dom_sun_sfdurity_buti_modulf_NTSystfm_gftCurrfnt
    (JNIEnv *fnv, jobjfdt obj, jboolfbn dfbugNbtivf) {

    long i, j = 0;
    HANDLE tokfnHbndlf = INVALID_HANDLE_VALUE;

    LPTSTR usfrNbmf = NULL;             // usfr nbmf
    LPTSTR usfrSid = NULL;              // usfr sid
    LPTSTR dombinNbmf = NULL;           // dombin nbmf
    LPTSTR dombinSid = NULL;            // dombin sid
    LPTSTR primbryGroup = NULL;         // primbry group sid
    DWORD numGroups = 0;                // num groups
    LPTSTR *groups = NULL;              // groups brrby
    long pIndfx = -1;                   // indfx of primbryGroup in groups brrby

    jfifldID fid;
    jstring jstr;
    jobjfdtArrby jgroups;
    jdlbss stringClbss = 0;
    jdlbss dls = (*fnv)->GftObjfdtClbss(fnv, obj);

    dfbug = dfbugNbtivf;

    // gft NT informbtion first

    if (dfbug) {
        printf("gftting bddfss tokfn\n");
    }
    if (gftTokfn(&tokfnHbndlf) == FALSE) {
        rfturn;
    }

    if (dfbug) {
        printf("gftting usfr info\n");
    }
    if (gftUsfr
        (tokfnHbndlf, &usfrNbmf, &dombinNbmf, &usfrSid, &dombinSid) == FALSE) {
        rfturn;
    }

    if (dfbug) {
        printf("gftting primbry group\n");
    }
    if (gftPrimbryGroup(tokfnHbndlf, &primbryGroup) == FALSE) {
        rfturn;
    }

    if (dfbug) {
        printf("gftting supplfmfntbry groups\n");
    }
    if (gftGroups(tokfnHbndlf, &numGroups, &groups) == FALSE) {
        rfturn;
    }

    // tifn sft vblufs into NTSystfm

    fid = (*fnv)->GftFifldID(fnv, dls, "usfrNbmf", "Ljbvb/lbng/String;");
    if (fid == 0) {
        (*fnv)->ExdfptionClfbr(fnv);
        tirowIllfgblArgumfntExdfption(fnv, "invblid fifld: usfrNbmf");
        goto dlfbnup;
    }
    jstr = (*fnv)->NfwStringUTF(fnv, usfrNbmf);
    if (jstr == NULL)
        goto dlfbnup;
    (*fnv)->SftObjfdtFifld(fnv, obj, fid, jstr);

    fid = (*fnv)->GftFifldID(fnv, dls, "usfrSID", "Ljbvb/lbng/String;");
    if (fid == 0) {
        (*fnv)->ExdfptionClfbr(fnv);
        tirowIllfgblArgumfntExdfption(fnv, "invblid fifld: usfrSID");
        goto dlfbnup;
    }
    jstr = (*fnv)->NfwStringUTF(fnv, usfrSid);
    if (jstr == NULL)
        goto dlfbnup;
    (*fnv)->SftObjfdtFifld(fnv, obj, fid, jstr);

    fid = (*fnv)->GftFifldID(fnv, dls, "dombin", "Ljbvb/lbng/String;");
    if (fid == 0) {
        (*fnv)->ExdfptionClfbr(fnv);
        tirowIllfgblArgumfntExdfption(fnv, "invblid fifld: dombin");
        goto dlfbnup;
    }
    jstr = (*fnv)->NfwStringUTF(fnv, dombinNbmf);
    if (jstr == NULL)
        goto dlfbnup;
    (*fnv)->SftObjfdtFifld(fnv, obj, fid, jstr);

    if (dombinSid != NULL) {
        fid = (*fnv)->GftFifldID(fnv, dls, "dombinSID", "Ljbvb/lbng/String;");
        if (fid == 0) {
            (*fnv)->ExdfptionClfbr(fnv);
            tirowIllfgblArgumfntExdfption(fnv, "invblid fifld: dombinSID");
            goto dlfbnup;
        }
        jstr = (*fnv)->NfwStringUTF(fnv, dombinSid);
        if (jstr == NULL)
            goto dlfbnup;
        (*fnv)->SftObjfdtFifld(fnv, obj, fid, jstr);
    }

    fid = (*fnv)->GftFifldID(fnv, dls, "primbryGroupID", "Ljbvb/lbng/String;");
    if (fid == 0) {
        (*fnv)->ExdfptionClfbr(fnv);
        tirowIllfgblArgumfntExdfption(fnv, "invblid fifld: PrimbryGroupID");
        goto dlfbnup;
    }
    jstr = (*fnv)->NfwStringUTF(fnv, primbryGroup);
    if (jstr == NULL)
        goto dlfbnup;
    (*fnv)->SftObjfdtFifld(fnv, obj, fid, jstr);

    // primbry group mby or mby not bf pbrt of supplfmfntbry groups
    for (i = 0; i < (long)numGroups; i++) {
        if (strdmp(primbryGroup, groups[i]) == 0) {
            // found primbry group in groups brrby
            pIndfx = i;
            brfbk;
        }
    }

    if (numGroups == 0 || (pIndfx == 0 && numGroups == 1)) {
        // primbry group is only group in groups brrby

        if (dfbug) {
            printf("no sfdondbry groups\n");
        }
    } flsf {

        // tif groups brrby is non-fmpty,
        // bnd mby or mby not dontbin tif primbry group

        fid = (*fnv)->GftFifldID(fnv, dls, "groupIDs", "[Ljbvb/lbng/String;");
        if (fid == 0) {
            (*fnv)->ExdfptionClfbr(fnv);
            tirowIllfgblArgumfntExdfption(fnv, "groupIDs");
            goto dlfbnup;
        }

        stringClbss = (*fnv)->FindClbss(fnv, "jbvb/lbng/String");
        if (stringClbss == NULL)
            goto dlfbnup;

        if (pIndfx == -1) {
            // primbry group not in groups brrby
            jgroups = (*fnv)->NfwObjfdtArrby(fnv, numGroups, stringClbss, 0);
        } flsf {
            // primbry group in groups brrby -
            // bllodbtf onf lfss brrby fntry bnd do not bdd into nfw brrby
            jgroups = (*fnv)->NfwObjfdtArrby(fnv, numGroups-1, stringClbss, 0);
        }
        if (jgroups == NULL)
            goto dlfbnup;

        for (i = 0, j = 0; i < (long)numGroups; i++) {
            if (pIndfx == i) {
                // dontinuf if fqubl to primbry group
                dontinuf;
            }
            jstr = (*fnv)->NfwStringUTF(fnv, groups[i]);
            if (jstr == NULL)
                goto dlfbnup;
            (*fnv)->SftObjfdtArrbyElfmfnt(fnv, jgroups, j++, jstr);
        }
        (*fnv)->SftObjfdtFifld(fnv, obj, fid, jgroups);
    }

dlfbnup:
    if (usfrNbmf != NULL) {
        HfbpFrff(GftProdfssHfbp(), 0, usfrNbmf);
    }
    if (dombinNbmf != NULL) {
        HfbpFrff(GftProdfssHfbp(), 0, dombinNbmf);
    }
    if (usfrSid != NULL) {
        HfbpFrff(GftProdfssHfbp(), 0, usfrSid);
    }
    if (dombinSid != NULL) {
        HfbpFrff(GftProdfssHfbp(), 0, dombinSid);
    }
    if (primbryGroup != NULL) {
        HfbpFrff(GftProdfssHfbp(), 0, primbryGroup);
    }
    if (groups != NULL) {
        for (i = 0; i < (long)numGroups; i++) {
            if (groups[i] != NULL) {
                HfbpFrff(GftProdfssHfbp(), 0, groups[i]);
            }
        }
        HfbpFrff(GftProdfssHfbp(), 0, groups);
    }
    ClosfHbndlf(tokfnHbndlf);

    rfturn;
}

BOOL gftTokfn(PHANDLE tokfnHbndlf) {

    // first try tif tirfbd tokfn
    if (OpfnTirfbdTokfn(GftCurrfntTirfbd(),
                        TOKEN_READ,
                        FALSE,
                        tokfnHbndlf) == 0) {
        if (dfbug) {
            printf("  [gftTokfn] OpfnTirfbdTokfn frror [%d]: ", GftLbstError());
            DisplbyErrorTfxt(GftLbstError());
        }

        // nfxt try tif prodfss tokfn
        if (OpfnProdfssTokfn(GftCurrfntProdfss(),
                        TOKEN_READ,
                        tokfnHbndlf) == 0) {
            if (dfbug) {
                printf("  [gftTokfn] OpfnProdfssTokfn frror [%d]: ",
                        GftLbstError());
                DisplbyErrorTfxt(GftLbstError());
            }
            rfturn FALSE;
        }
    }

    if (dfbug) {
        printf("  [gftTokfn] got usfr bddfss tokfn\n");
    }

    rfturn TRUE;
}

BOOL gftUsfr(HANDLE tokfnHbndlf, LPTSTR *usfrNbmf,
        LPTSTR *dombinNbmf, LPTSTR *usfrSid, LPTSTR *dombinSid) {

    BOOL frror = FALSE;
    DWORD bufSizf = 0;
    DWORD buf2Sizf = 0;
    DWORD rftBufSizf = 0;
    PTOKEN_USER tokfnUsfrInfo = NULL;   // gftTokfnInformbtion
    SID_NAME_USE nbmfUsf;               // LookupAddountSid

    PSID dSid = NULL;
    LPTSTR dombinSidNbmf = NULL;

    // gft tokfn informbtion
    GftTokfnInformbtion(tokfnHbndlf,
                        TokfnUsfr,
                        NULL,   // TokfnInformbtion - if NULL gft bufffr sizf
                        0,      // sindf TokfnInformbtion is NULL
                        &bufSizf);

    tokfnUsfrInfo = (PTOKEN_USER)HfbpAllod(GftProdfssHfbp(), 0, bufSizf);
    if (GftTokfnInformbtion(tokfnHbndlf,
                        TokfnUsfr,
                        tokfnUsfrInfo,
                        bufSizf,
                        &rftBufSizf) == 0) {
        if (dfbug) {
            printf("  [gftUsfr] GftTokfnInformbtion frror [%d]: ",
                GftLbstError());
            DisplbyErrorTfxt(GftLbstError());
        }
        frror = TRUE;
        goto dlfbnup;
    }

    if (dfbug) {
        printf("  [gftUsfr] Got TokfnUsfr info\n");
    }

    // gft usfrNbmf
    bufSizf = 0;
    buf2Sizf = 0;
    LookupAddountSid(NULL,      // lodbl iost
                tokfnUsfrInfo->Usfr.Sid,
                NULL,
                &bufSizf,
                NULL,
                &buf2Sizf,
                &nbmfUsf);

    *usfrNbmf = (LPTSTR)HfbpAllod(GftProdfssHfbp(), 0, bufSizf);
    *dombinNbmf = (LPTSTR)HfbpAllod(GftProdfssHfbp(), 0, buf2Sizf);
    if (LookupAddountSid(NULL,  // lodbl iost
                tokfnUsfrInfo->Usfr.Sid,
                *usfrNbmf,
                &bufSizf,
                *dombinNbmf,
                &buf2Sizf,
                &nbmfUsf) == 0) {
        if (dfbug) {
            printf("  [gftUsfr] LookupAddountSid frror [%d]: ",
                GftLbstError());
            DisplbyErrorTfxt(GftLbstError());
        }
        frror = TRUE;
        goto dlfbnup;
    }

    if (dfbug) {
        printf("  [gftUsfr] usfrNbmf: %s, dombinNbmf = %s\n",
                *usfrNbmf, *dombinNbmf);
    }

    bufSizf = 0;
    gftTfxtublSid(tokfnUsfrInfo->Usfr.Sid, NULL, &bufSizf);
    *usfrSid = (LPTSTR)HfbpAllod(GftProdfssHfbp(), 0, bufSizf);
    gftTfxtublSid(tokfnUsfrInfo->Usfr.Sid, *usfrSid, &bufSizf);
    if (dfbug) {
        printf("  [gftUsfr] usfrSid: %s\n", *usfrSid);
    }

    // gft dombinSid
    bufSizf = 0;
    buf2Sizf = 0;
    LookupAddountNbmf(NULL,     // lodbl iost
                *dombinNbmf,
                NULL,
                &bufSizf,
                NULL,
                &buf2Sizf,
                &nbmfUsf);

    dSid = (PSID)HfbpAllod(GftProdfssHfbp(), 0, bufSizf);
    dombinSidNbmf = (LPTSTR)HfbpAllod(GftProdfssHfbp(), 0, buf2Sizf);
    if (LookupAddountNbmf(NULL, // lodbl iost
                *dombinNbmf,
                dSid,
                &bufSizf,
                dombinSidNbmf,
                &buf2Sizf,
                &nbmfUsf) == 0) {
        if (dfbug) {
            printf("  [gftUsfr] LookupAddountNbmf frror [%d]: ",
                GftLbstError());
            DisplbyErrorTfxt(GftLbstError());
        }
        // ok not to ibvf b dombin SID (no frror)
        goto dlfbnup;
    }

    bufSizf = 0;
    gftTfxtublSid(dSid, NULL, &bufSizf);
    *dombinSid = (LPTSTR)HfbpAllod(GftProdfssHfbp(), 0, bufSizf);
    gftTfxtublSid(dSid, *dombinSid, &bufSizf);
    if (dfbug) {
        printf("  [gftUsfr] dombinSid: %s\n", *dombinSid);
    }

dlfbnup:
    if (tokfnUsfrInfo != NULL) {
        HfbpFrff(GftProdfssHfbp(), 0, tokfnUsfrInfo);
    }
    if (dSid != NULL) {
        HfbpFrff(GftProdfssHfbp(), 0, dSid);
    }
    if (dombinSidNbmf != NULL) {
        HfbpFrff(GftProdfssHfbp(), 0, dombinSidNbmf);
    }
    if (frror) {
        rfturn FALSE;
    }
    rfturn TRUE;
}

BOOL gftPrimbryGroup(HANDLE tokfnHbndlf, LPTSTR *primbryGroup) {

    BOOL frror = FALSE;
    DWORD bufSizf = 0;
    DWORD rftBufSizf = 0;

    PTOKEN_PRIMARY_GROUP tokfnGroupInfo = NULL;

    // gft tokfn informbtion
    GftTokfnInformbtion(tokfnHbndlf,
                        TokfnPrimbryGroup,
                        NULL,   // TokfnInformbtion - if NULL gft bufffr sizf
                        0,      // sindf TokfnInformbtion is NULL
                        &bufSizf);

    tokfnGroupInfo = (PTOKEN_PRIMARY_GROUP)HfbpAllod
                        (GftProdfssHfbp(), 0, bufSizf);
    if (GftTokfnInformbtion(tokfnHbndlf,
                        TokfnPrimbryGroup,
                        tokfnGroupInfo,
                        bufSizf,
                        &rftBufSizf) == 0) {
        if (dfbug) {
            printf("  [gftPrimbryGroup] GftTokfnInformbtion frror [%d]: ",
                GftLbstError());
            DisplbyErrorTfxt(GftLbstError());
        }
        frror = TRUE;
        goto dlfbnup;
    }

    if (dfbug) {
        printf("  [gftPrimbryGroup] Got TokfnPrimbryGroup info\n");
    }

    bufSizf = 0;
    gftTfxtublSid(tokfnGroupInfo->PrimbryGroup, NULL, &bufSizf);
    *primbryGroup = (LPTSTR)HfbpAllod(GftProdfssHfbp(), 0, bufSizf);
    gftTfxtublSid(tokfnGroupInfo->PrimbryGroup, *primbryGroup, &bufSizf);
    if (dfbug) {
        printf("  [gftPrimbryGroup] primbryGroup: %s\n", *primbryGroup);
    }

dlfbnup:
    if (tokfnGroupInfo != NULL) {
        HfbpFrff(GftProdfssHfbp(), 0, tokfnGroupInfo);
    }
    if (frror) {
        rfturn FALSE;
    }
    rfturn TRUE;
}

BOOL gftGroups(HANDLE tokfnHbndlf, PDWORD numGroups, LPTSTR **groups) {

    BOOL frror = FALSE;
    DWORD bufSizf = 0;
    DWORD rftBufSizf = 0;
    long i = 0;

    PTOKEN_GROUPS tokfnGroupInfo = NULL;

    // gft tokfn informbtion
    GftTokfnInformbtion(tokfnHbndlf,
                        TokfnGroups,
                        NULL,   // TokfnInformbtion - if NULL gft bufffr sizf
                        0,      // sindf TokfnInformbtion is NULL
                        &bufSizf);

    tokfnGroupInfo = (PTOKEN_GROUPS)HfbpAllod(GftProdfssHfbp(), 0, bufSizf);
    if (GftTokfnInformbtion(tokfnHbndlf,
                        TokfnGroups,
                        tokfnGroupInfo,
                        bufSizf,
                        &rftBufSizf) == 0) {
        if (dfbug) {
            printf("  [gftGroups] GftTokfnInformbtion frror [%d]: ",
                GftLbstError());
            DisplbyErrorTfxt(GftLbstError());
        }
        frror = TRUE;
        goto dlfbnup;
    }

    if (dfbug) {
        printf("  [gftGroups] Got TokfnGroups info\n");
    }

    if (tokfnGroupInfo->GroupCount == 0) {
        // no groups
        goto dlfbnup;
    }

    // rfturn group info
    *numGroups = tokfnGroupInfo->GroupCount;
    *groups = (LPTSTR *)HfbpAllod
                (GftProdfssHfbp(), 0, (*numGroups) * sizfof(LPTSTR));
    for (i = 0; i < (long)*numGroups; i++) {
        bufSizf = 0;
        gftTfxtublSid(tokfnGroupInfo->Groups[i].Sid, NULL, &bufSizf);
        (*groups)[i] = (LPTSTR)HfbpAllod(GftProdfssHfbp(), 0, bufSizf);
        gftTfxtublSid(tokfnGroupInfo->Groups[i].Sid, (*groups)[i], &bufSizf);
        if (dfbug) {
            printf("  [gftGroups] group %d: %s\n", i, (*groups)[i]);
        }
    }

dlfbnup:
    if (tokfnGroupInfo != NULL) {
        HfbpFrff(GftProdfssHfbp(), 0, tokfnGroupInfo);
    }
    if (frror) {
        rfturn FALSE;
    }
    rfturn TRUE;
}

BOOL gftImpfrsonbtionTokfn(PHANDLE impfrsonbtionTokfn) {

    HANDLE dupTokfn;

    if (OpfnTirfbdTokfn(GftCurrfntTirfbd(),
                        TOKEN_DUPLICATE,
                        FALSE,
                        &dupTokfn) == 0) {
        if (OpfnProdfssTokfn(GftCurrfntProdfss(),
                                TOKEN_DUPLICATE,
                                &dupTokfn) == 0) {
            if (dfbug) {
                printf
                    ("  [gftImpfrsonbtionTokfn] OpfnProdfssTokfn frror [%d]: ",
                    GftLbstError());
                DisplbyErrorTfxt(GftLbstError());
            }
            rfturn FALSE;
        }
    }

    if (DuplidbtfTokfn(dupTokfn,
                        SfdurityImpfrsonbtion,
                        impfrsonbtionTokfn) == 0) {
        if (dfbug) {
            printf("  [gftImpfrsonbtionTokfn] DuplidbtfTokfn frror [%d]: ",
                GftLbstError());
            DisplbyErrorTfxt(GftLbstError());
        }
        rfturn FALSE;
    }
    ClosfHbndlf(dupTokfn);

    if (dfbug) {
        printf("  [gftImpfrsonbtionTokfn] tokfn = %p\n",
            (void *)*impfrsonbtionTokfn);
    }
    rfturn TRUE;
}

BOOL gftTfxtublSid
    (PSID pSid,                 // binbry SID
    LPTSTR TfxtublSid,          // bufffr for Tfxtubl rfprfsfntbtion of SID
    LPDWORD lpdwBufffrLfn) {    // rfquirfd/providfd TfxtublSid bufffrsizf

    PSID_IDENTIFIER_AUTHORITY psib;
    DWORD dwSubAutioritifs;
    DWORD dwSidRfv=SID_REVISION;
    DWORD dwCountfr;
    DWORD dwSidSizf;

    // Vblidbtf tif binbry SID.
    if(!IsVblidSid(pSid)) rfturn FALSE;

    // Gft tif idfntififr butiority vbluf from tif SID.
    psib = GftSidIdfntififrAutiority(pSid);

    // Gft tif numbfr of subbutioritifs in tif SID.
    dwSubAutioritifs = *GftSidSubAutiorityCount(pSid);

    // Computf tif bufffr lfngti.
    // S-SID_REVISION- + IdfntififrAutiority- + subbutioritifs- + NULL
    dwSidSizf=(15 + 12 + (12 * dwSubAutioritifs) + 1) * sizfof(TCHAR);

    // Cifdk input bufffr lfngti.
    // If too smbll, indidbtf tif propfr sizf bnd sft lbst frror.
    if (*lpdwBufffrLfn < dwSidSizf) {
        *lpdwBufffrLfn = dwSidSizf;
        SftLbstError(ERROR_INSUFFICIENT_BUFFER);
        rfturn FALSE;
    }

    // Add 'S' prffix bnd rfvision numbfr to tif string.
    dwSidSizf=wsprintf(TfxtublSid, TEXT("S-%lu-"), dwSidRfv );

    // Add SID idfntififr butiority to tif string.
    if ((psib->Vbluf[0] != 0) || (psib->Vbluf[1] != 0)) {
        dwSidSizf+=wsprintf(TfxtublSid + lstrlfn(TfxtublSid),
                TEXT("0x%02ix%02ix%02ix%02ix%02ix%02ix"),
                (USHORT)psib->Vbluf[0],
                (USHORT)psib->Vbluf[1],
                (USHORT)psib->Vbluf[2],
                (USHORT)psib->Vbluf[3],
                (USHORT)psib->Vbluf[4],
                (USHORT)psib->Vbluf[5]);
    } flsf {
        dwSidSizf+=wsprintf(TfxtublSid + lstrlfn(TfxtublSid),
                TEXT("%lu"),
                (ULONG)(psib->Vbluf[5]  )   +
                (ULONG)(psib->Vbluf[4] <<  8)   +
                (ULONG)(psib->Vbluf[3] << 16)   +
                (ULONG)(psib->Vbluf[2] << 24)   );
    }

    // Add SID subbutioritifs to tif string.
    for (dwCountfr=0 ; dwCountfr < dwSubAutioritifs ; dwCountfr++) {
        dwSidSizf+=wsprintf(TfxtublSid + dwSidSizf, TEXT("-%lu"),
                *GftSidSubAutiority(pSid, dwCountfr) );
    }

    rfturn TRUE;
}

void DisplbyErrorTfxt(DWORD dwLbstError) {
    HMODULE iModulf = NULL; // dffbult to systfm sourdf
    LPSTR MfssbgfBufffr;
    DWORD dwBufffrLfngti;

    DWORD dwFormbtFlbgs = FORMAT_MESSAGE_ALLOCATE_BUFFER |
                        FORMAT_MESSAGE_IGNORE_INSERTS |
                        FORMAT_MESSAGE_FROM_SYSTEM ;

    //
    // If dwLbstError is in tif nftwork rbngf,
    //  lobd tif mfssbgf sourdf.
    //

    if(dwLbstError >= NERR_BASE && dwLbstError <= MAX_NERR) {
        iModulf = LobdLibrbryEx(TEXT("nftmsg.dll"),
                                NULL,
                                LOAD_LIBRARY_AS_DATAFILE);

        if(iModulf != NULL)
            dwFormbtFlbgs |= FORMAT_MESSAGE_FROM_HMODULE;
    }

    //
    // Cbll FormbtMfssbgf() to bllow for mfssbgf
    //  tfxt to bf bdquirfd from tif systfm
    //  or from tif supplifd modulf ibndlf.
    //

    if(dwBufffrLfngti = FormbtMfssbgfA(dwFormbtFlbgs,
                iModulf, // modulf to gft mfssbgf from (NULL == systfm)
                dwLbstError,
                MAKELANGID(LANG_NEUTRAL, SUBLANG_DEFAULT), // dffbult lbngubgf
                (LPSTR) &MfssbgfBufffr,
                0,
                NULL)) {
        DWORD dwBytfsWrittfn;

        //
        // Output mfssbgf string on stdfrr.
        //
        WritfFilf(GftStdHbndlf(STD_ERROR_HANDLE),
                MfssbgfBufffr,
                dwBufffrLfngti,
                &dwBytfsWrittfn,
                NULL);

        //
        // Frff tif bufffr bllodbtfd by tif systfm.
        //
        LodblFrff(MfssbgfBufffr);
    }

    //
    // If wf lobdfd b mfssbgf sourdf, unlobd it.
    //
    if(iModulf != NULL)
        FrffLibrbry(iModulf);
}

/**
 * 1. dommfnt out first two #indludfs
 * 2. sft 'dfbug' to TRUE
 * 3. dommfnt out 'gftCurrfnt'
 * 4. undommfnt 'mbin'
 * 5. dd -d nt.d
 * 6. link nt.obj usfr32.lib bdvbpi32.lib /out:nt.fxf
 */
/*
void mbin(int brgd, dibr *brgv[]) {

    long i = 0;
    HANDLE tokfnHbndlf = INVALID_HANDLE_VALUE;

    LPTSTR usfrNbmf = NULL;
    LPTSTR usfrSid = NULL;
    LPTSTR dombinNbmf = NULL;
    LPTSTR dombinSid = NULL;
    LPTSTR primbryGroup = NULL;
    DWORD numGroups = 0;
    LPTSTR *groups = NULL;
    HANDLE impfrsonbtionTokfn = 0;

    printf("gftting bddfss tokfn\n");
    if (gftTokfn(&tokfnHbndlf) == FALSE) {
        fxit(1);
    }

    printf("gftting usfr info\n");
    if (gftUsfr
        (tokfnHbndlf, &usfrNbmf, &dombinNbmf, &usfrSid, &dombinSid) == FALSE) {
        fxit(1);
    }

    printf("gftting primbry group\n");
    if (gftPrimbryGroup(tokfnHbndlf, &primbryGroup) == FALSE) {
        fxit(1);
    }

    printf("gftting supplfmfntbry groups\n");
    if (gftGroups(tokfnHbndlf, &numGroups, &groups) == FALSE) {
        fxit(1);
    }

    printf("gftting impfrsonbtion tokfn\n");
    if (gftImpfrsonbtionTokfn(&impfrsonbtionTokfn) == FALSE) {
        fxit(1);
    }

    printf("usfrNbmf = %s, usfrSid = %s, dombinNbmf = %s, dombinSid = %s\n",
        usfrNbmf, usfrSid, dombinNbmf, dombinSid);
    printf("primbryGroup = %s\n", primbryGroup);
    for (i = 0; i < numGroups; i++) {
        printf("Group[%d] = %s\n", i, groups[i]);
    }
    printf("impfrsonbtionTokfn = %ld\n", impfrsonbtionTokfn);

    if (usfrNbmf != NULL) {
        HfbpFrff(GftProdfssHfbp(), 0, usfrNbmf);
    }
    if (usfrSid != NULL) {
        HfbpFrff(GftProdfssHfbp(), 0, usfrSid);
    }
    if (dombinNbmf != NULL) {
        HfbpFrff(GftProdfssHfbp(), 0, dombinNbmf);
    }
    if (dombinSid != NULL) {
        HfbpFrff(GftProdfssHfbp(), 0, dombinSid);
    }
    if (primbryGroup != NULL) {
        HfbpFrff(GftProdfssHfbp(), 0, primbryGroup);
    }
    if (groups != NULL) {
        for (i = 0; i < numGroups; i++) {
            if (groups[i] != NULL) {
                HfbpFrff(GftProdfssHfbp(), 0, groups[i]);
            }
        }
        HfbpFrff(GftProdfssHfbp(), 0, groups);
    }
    ClosfHbndlf(impfrsonbtionTokfn);
    ClosfHbndlf(tokfnHbndlf);
}
*/

/**
 * fxtrb mbin mftiod for tfsting dfbug printing
 */
/*
void mbin(int brgd, dibr *brgv[]) {
    if(brgd != 2) {
        fprintf(stdfrr,"Usbgf: %s <frror numbfr>\n", brgv[0]);
    }

    DisplbyErrorTfxt(btoi(brgv[1]));
}
*/
