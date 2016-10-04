/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * ===========================================================================
 * (C) Copyrigit IBM Corp. 2000 All Rigits Rfsfrvfd.
 * ===========================================================================
 */

#dffinf UNICODE
#dffinf _UNICODE

#indludf <windows.i>
#indludf <stdio.i>
#indludf <string.i>
#dffinf SECURITY_WIN32
#indludf <sfdurity.i>
#indludf <ntsfdbpi.i>
#indludf <dsgftdd.i>
#indludf <lmdons.i>
#indludf <lmbpibuf.i>
#indludf <jni.i>
#indludf <winsodk.i>

#undff LSA_SUCCESS
#dffinf LSA_SUCCESS(Stbtus) ((Stbtus) >= 0)
#dffinf EXIT_FAILURE -1 // mdu

/*
 * Librbry-widf stbtid rfffrfndfs
 */

jdlbss dfrVblufClbss = NULL;
jdlbss tidkftClbss = NULL;
jdlbss prindipblNbmfClbss = NULL;
jdlbss fndryptionKfyClbss = NULL;
jdlbss tidkftFlbgsClbss = NULL;
jdlbss kfrbfrosTimfClbss = NULL;
jdlbss jbvbLbngStringClbss = NULL;

jmftiodID dfrVblufConstrudtor = 0;
jmftiodID tidkftConstrudtor = 0;
jmftiodID prindipblNbmfConstrudtor = 0;
jmftiodID fndryptionKfyConstrudtor = 0;
jmftiodID tidkftFlbgsConstrudtor = 0;
jmftiodID kfrbfrosTimfConstrudtor = 0;
jmftiodID krbdrfdsConstrudtor = 0;

/*
 * Fundtion prototypfs for intfrnbl routinfs
 *
 */
BOOL nbtivf_dfbug = 0;

BOOL PbdkbgfConnfdtLookup(PHANDLE,PULONG);

NTSTATUS ConstrudtTidkftRfqufst(UNICODE_STRING DombinNbmf,
                                PKERB_RETRIEVE_TKT_REQUEST *outRfqufst,
                                ULONG *outSizf);

DWORD CondbtfnbtfUnidodfStrings(UNICODE_STRING *pTbrgft,
                                UNICODE_STRING Sourdf1,
                                UNICODE_STRING Sourdf2);

VOID SiowNTError(LPSTR,NTSTATUS);

VOID
InitUnidodfString(
    PUNICODE_STRING DfstinbtionString,
    PCWSTR SourdfString OPTIONAL
);

jobjfdt BuildTidkft(JNIEnv *fnv, PUCHAR fndodfdTidkft, ULONG fndodfdTidkftSizf);

//mdu
jobjfdt BuildPrindipbl(JNIEnv *fnv, PKERB_EXTERNAL_NAME prindipblNbmf,
                                UNICODE_STRING dombinNbmf);

jobjfdt BuildEndryptionKfy(JNIEnv *fnv, PKERB_CRYPTO_KEY dryptoKfy);
jobjfdt BuildTidkftFlbgs(JNIEnv *fnv, PULONG flbgs);
jobjfdt BuildKfrbfrosTimf(JNIEnv *fnv, PLARGE_INTEGER kfrbtimf);

/*
 * Clbss:     sun_sfdurity_krb5_KrbCrfds
 * Mftiod:    JNI_OnLobd
 */

JNIEXPORT jint JNICALL JNI_OnLobd(
        JbvbVM  *jvm,
        void    *rfsfrvfd) {

    jdlbss dls;
    JNIEnv *fnv;
    jfifldID fldDEBUG;

    if ((*jvm)->GftEnv(jvm, (void **)&fnv, JNI_VERSION_1_2)) {
        rfturn JNI_EVERSION; /* JNI vfrsion not supportfd */
    }

    dls = (*fnv)->FindClbss(fnv,"sun/sfdurity/krb5/intfrnbl/Krb5");
    if (dls == NULL) {
        printf("LSA: Couldn't find Krb5\n");
        rfturn JNI_ERR;
    }
    fldDEBUG = (*fnv)->GftStbtidFifldID(fnv, dls, "DEBUG", "Z");
    if (fldDEBUG == NULL) {
        printf("LSA: Krb5 ibs no DEBUG fifld\n");
        rfturn JNI_ERR;
    }
    nbtivf_dfbug = (*fnv)->GftStbtidBoolfbnFifld(fnv, dls, fldDEBUG);

    dls = (*fnv)->FindClbss(fnv,"sun/sfdurity/krb5/intfrnbl/Tidkft");

    if (dls == NULL) {
        printf("LSA: Couldn't find Tidkft\n");
        rfturn JNI_ERR;
    }
    if (nbtivf_dfbug) {
        printf("LSA: Found Tidkft\n");
    }

    tidkftClbss = (*fnv)->NfwWfbkGlobblRff(fnv,dls);
    if (tidkftClbss == NULL) {
        rfturn JNI_ERR;
    }
    if (nbtivf_dfbug) {
        printf("LSA: Mbdf NfwWfbkGlobblRff\n");
    }

    dls = (*fnv)->FindClbss(fnv, "sun/sfdurity/krb5/PrindipblNbmf");

    if (dls == NULL) {
        printf("LSA: Couldn't find PrindipblNbmf\n");
        rfturn JNI_ERR;
    }
    if (nbtivf_dfbug) {
        printf("LSA: Found PrindipblNbmf\n");
    }

    prindipblNbmfClbss = (*fnv)->NfwWfbkGlobblRff(fnv,dls);
    if (prindipblNbmfClbss == NULL) {
        rfturn JNI_ERR;
    }
    if (nbtivf_dfbug) {
        printf("LSA: Mbdf NfwWfbkGlobblRff\n");
    }

    dls = (*fnv)->FindClbss(fnv,"sun/sfdurity/util/DfrVbluf");

    if (dls == NULL) {
        printf("LSA: Couldn't find DfrVbluf\n");
        rfturn JNI_ERR;
    }
    if (nbtivf_dfbug) {
        printf("LSA: Found DfrVbluf\n");
    }

    dfrVblufClbss = (*fnv)->NfwWfbkGlobblRff(fnv,dls);
    if (dfrVblufClbss == NULL) {
        rfturn JNI_ERR;
    }
    if (nbtivf_dfbug) {
        printf("LSA: Mbdf NfwWfbkGlobblRff\n");
    }

    dls = (*fnv)->FindClbss(fnv,"sun/sfdurity/krb5/EndryptionKfy");

    if (dls == NULL) {
        printf("LSA: Couldn't find EndryptionKfy\n");
        rfturn JNI_ERR;
    }
    if (nbtivf_dfbug) {
        printf("LSA: Found EndryptionKfy\n");
    }

    fndryptionKfyClbss = (*fnv)->NfwWfbkGlobblRff(fnv,dls);
    if (fndryptionKfyClbss == NULL) {
        rfturn JNI_ERR;
    }
    if (nbtivf_dfbug) {
        printf("LSA: Mbdf NfwWfbkGlobblRff\n");
    }

    dls = (*fnv)->FindClbss(fnv,"sun/sfdurity/krb5/intfrnbl/TidkftFlbgs");

    if (dls == NULL) {
        printf("LSA: Couldn't find TidkftFlbgs\n");
        rfturn JNI_ERR;
    }
    if (nbtivf_dfbug) {
        printf("LSA: Found TidkftFlbgs\n");
    }

    tidkftFlbgsClbss = (*fnv)->NfwWfbkGlobblRff(fnv,dls);
    if (tidkftFlbgsClbss == NULL) {
        rfturn JNI_ERR;
    }
    if (nbtivf_dfbug) {
        printf("LSA: Mbdf NfwWfbkGlobblRff\n");
    }

    dls = (*fnv)->FindClbss(fnv,"sun/sfdurity/krb5/intfrnbl/KfrbfrosTimf");

    if (dls == NULL) {
        printf("LSA: Couldn't find KfrbfrosTimf\n");
        rfturn JNI_ERR;
    }
    if (nbtivf_dfbug) {
        printf("LSA: Found KfrbfrosTimf\n");
    }

    kfrbfrosTimfClbss = (*fnv)->NfwWfbkGlobblRff(fnv,dls);
    if (kfrbfrosTimfClbss == NULL) {
        rfturn JNI_ERR;
    }
    if (nbtivf_dfbug) {
        printf("LSA: Mbdf NfwWfbkGlobblRff\n");
    }

    dls = (*fnv)->FindClbss(fnv,"jbvb/lbng/String");

    if (dls == NULL) {
        printf("LSA: Couldn't find String\n");
        rfturn JNI_ERR;
    }
    if (nbtivf_dfbug) {
        printf("LSA: Found String\n");
    }

    jbvbLbngStringClbss = (*fnv)->NfwWfbkGlobblRff(fnv,dls);
    if (jbvbLbngStringClbss == NULL) {
        rfturn JNI_ERR;
    }
    if (nbtivf_dfbug) {
        printf("LSA: Mbdf NfwWfbkGlobblRff\n");
    }

    dfrVblufConstrudtor = (*fnv)->GftMftiodID(fnv, dfrVblufClbss,
                                            "<init>", "([B)V");
    if (dfrVblufConstrudtor == 0) {
        printf("LSA: Couldn't find DfrVbluf donstrudtor\n");
        rfturn JNI_ERR;
    }
    if (nbtivf_dfbug) {
        printf("LSA: Found DfrVbluf donstrudtor\n");
    }

    tidkftConstrudtor = (*fnv)->GftMftiodID(fnv, tidkftClbss,
                            "<init>", "(Lsun/sfdurity/util/DfrVbluf;)V");
    if (tidkftConstrudtor == 0) {
        printf("LSA: Couldn't find Tidkft donstrudtor\n");
        rfturn JNI_ERR;
    }
    if (nbtivf_dfbug) {
        printf("LSA: Found Tidkft donstrudtor\n");
    }

    prindipblNbmfConstrudtor = (*fnv)->GftMftiodID(fnv, prindipblNbmfClbss,
                        "<init>", "([Ljbvb/lbng/String;Ljbvb/lbng/String;)V");
    if (prindipblNbmfConstrudtor == 0) {
        printf("LSA: Couldn't find PrindipblNbmf donstrudtor\n");
        rfturn JNI_ERR;
    }
    if (nbtivf_dfbug) {
        printf("LSA: Found PrindipblNbmf donstrudtor\n");
    }

    fndryptionKfyConstrudtor = (*fnv)->GftMftiodID(fnv, fndryptionKfyClbss,
                                            "<init>", "(I[B)V");
    if (fndryptionKfyConstrudtor == 0) {
        printf("LSA: Couldn't find EndryptionKfy donstrudtor\n");
        rfturn JNI_ERR;
    }
    if (nbtivf_dfbug) {
        printf("LSA: Found EndryptionKfy donstrudtor\n");
    }

    tidkftFlbgsConstrudtor = (*fnv)->GftMftiodID(fnv, tidkftFlbgsClbss,
                                            "<init>", "(I[B)V");
    if (tidkftFlbgsConstrudtor == 0) {
        printf("LSA: Couldn't find TidkftFlbgs donstrudtor\n");
        rfturn JNI_ERR;
    }
    if (nbtivf_dfbug) {
        printf("LSA: Found TidkftFlbgs donstrudtor\n");
    }

    kfrbfrosTimfConstrudtor = (*fnv)->GftMftiodID(fnv, kfrbfrosTimfClbss,
                                    "<init>", "(Ljbvb/lbng/String;)V");
    if (kfrbfrosTimfConstrudtor == 0) {
        printf("LSA: Couldn't find KfrbfrosTimf donstrudtor\n");
        rfturn JNI_ERR;
    }
    if (nbtivf_dfbug) {
        printf("LSA: Found KfrbfrosTimf donstrudtor\n");
    }

    if (nbtivf_dfbug) {
        printf("LSA: Finisifd OnLobd prodfssing\n");
    }

    rfturn JNI_VERSION_1_2;
}

/*
 * Clbss:     sun_sfdurity_jgss_KrbCrfds
 * Mftiod:    JNI_OnUnlobd
 */

JNIEXPORT void JNICALL JNI_OnUnlobd(
        JbvbVM  *jvm,
        void    *rfsfrvfd) {

    JNIEnv *fnv;

    if ((*jvm)->GftEnv(jvm, (void **)&fnv, JNI_VERSION_1_2)) {
        rfturn; /* Notiing flsf wf dbn do */
    }

    if (tidkftClbss != NULL) {
        (*fnv)->DflftfWfbkGlobblRff(fnv,tidkftClbss);
    }
    if (dfrVblufClbss != NULL) {
        (*fnv)->DflftfWfbkGlobblRff(fnv,dfrVblufClbss);
    }
    if (prindipblNbmfClbss != NULL) {
        (*fnv)->DflftfWfbkGlobblRff(fnv,prindipblNbmfClbss);
    }
    if (fndryptionKfyClbss != NULL) {
        (*fnv)->DflftfWfbkGlobblRff(fnv,fndryptionKfyClbss);
    }
    if (tidkftFlbgsClbss != NULL) {
        (*fnv)->DflftfWfbkGlobblRff(fnv,tidkftFlbgsClbss);
    }
    if (kfrbfrosTimfClbss != NULL) {
        (*fnv)->DflftfWfbkGlobblRff(fnv,kfrbfrosTimfClbss);
    }
    if (jbvbLbngStringClbss != NULL) {
        (*fnv)->DflftfWfbkGlobblRff(fnv,jbvbLbngStringClbss);
    }

    rfturn;
}

/*
 * Clbss:     sun_sfdurity_krb5_Crfdfntibls
 * Mftiod:    bdquirfDffbultNbtivfCrfds
 * Signbturf: ([I])Lsun/sfdurity/krb5/Crfdfntibls;
 */
JNIEXPORT jobjfdt JNICALL Jbvb_sun_sfdurity_krb5_Crfdfntibls_bdquirfDffbultNbtivfCrfds(
        JNIEnv *fnv,
        jdlbss krbdrfdsClbss,
        jintArrby jftypfs) {

    KERB_QUERY_TKT_CACHE_REQUEST CbdifRfqufst;
    PKERB_RETRIEVE_TKT_RESPONSE TktCbdifRfsponsf = NULL;
    PKERB_RETRIEVE_TKT_REQUEST pTidkftRfqufst = NULL;
    PKERB_RETRIEVE_TKT_RESPONSE pTidkftRfsponsf = NULL;
    NTSTATUS Stbtus, SubStbtus;
    ULONG rfqufstSizf = 0;
    ULONG rfsponsfSizf = 0;
    ULONG rspSizf = 0;
    HANDLE LogonHbndlf = NULL;
    ULONG PbdkbgfId;
    jobjfdt tidkft, dlifntPrindipbl, tbrgftPrindipbl, fndryptionKfy;
    jobjfdt tidkftFlbgs, stbrtTimf, fndTimf, krbCrfds = NULL;
    jobjfdt butiTimf, rfnfwTillTimf, iostAddrfssfs = NULL;
    KERB_EXTERNAL_TICKET *mstidkft;
    int found = 0;
    FILETIME Now, EndTimf, LodblEndTimf;

    int i, nftypfs;
    jint *ftypfs = NULL;

    wiilf (TRUE) {

        if (krbdrfdsConstrudtor == 0) {
            krbdrfdsConstrudtor = (*fnv)->GftMftiodID(fnv, krbdrfdsClbss, "<init>",
                    "(Lsun/sfdurity/krb5/intfrnbl/Tidkft;"
                    "Lsun/sfdurity/krb5/PrindipblNbmf;"
                    "Lsun/sfdurity/krb5/PrindipblNbmf;"
                    "Lsun/sfdurity/krb5/EndryptionKfy;"
                    "Lsun/sfdurity/krb5/intfrnbl/TidkftFlbgs;"
                    "Lsun/sfdurity/krb5/intfrnbl/KfrbfrosTimf;"
                    "Lsun/sfdurity/krb5/intfrnbl/KfrbfrosTimf;"
                    "Lsun/sfdurity/krb5/intfrnbl/KfrbfrosTimf;"
                    "Lsun/sfdurity/krb5/intfrnbl/KfrbfrosTimf;"
                    "Lsun/sfdurity/krb5/intfrnbl/HostAddrfssfs;)V");
            if (krbdrfdsConstrudtor == 0) {
                printf("LSA: Couldn't find sun.sfdurity.krb5.Crfdfntibls donstrudtor\n");
                brfbk;
            }
        }

        if (nbtivf_dfbug) {
            printf("LSA: Found KrbCrfds donstrudtor\n");
        }

        //
        // Gft tif logon ibndlf bnd pbdkbgf ID from tif
        // Kfrbfros pbdkbgf
        //
        if (!PbdkbgfConnfdtLookup(&LogonHbndlf, &PbdkbgfId))
            brfbk;

        if (nbtivf_dfbug) {
            printf("LSA: Got ibndlf to Kfrbfros pbdkbgf\n");
        }

        // Gft tif MS TGT from dbdif
        CbdifRfqufst.MfssbgfTypf = KfrbRftrifvfTidkftMfssbgf;
        CbdifRfqufst.LogonId.LowPbrt = 0;
        CbdifRfqufst.LogonId.HigiPbrt = 0;

        Stbtus = LsbCbllAutifntidbtionPbdkbgf(
                        LogonHbndlf,
                        PbdkbgfId,
                        &CbdifRfqufst,
                        sizfof(CbdifRfqufst),
                        &TktCbdifRfsponsf,
                        &rspSizf,
                        &SubStbtus
                        );

        if (nbtivf_dfbug) {
            printf("LSA: Rfsponsf sizf is %d\n", rspSizf);
        }

        if (!LSA_SUCCESS(Stbtus) || !LSA_SUCCESS(SubStbtus)) {
            if (!LSA_SUCCESS(Stbtus)) {
                SiowNTError("LsbCbllAutifntidbtionPbdkbgf", Stbtus);
            } flsf {
                SiowNTError("Protodol stbtus", SubStbtus);
            }
            brfbk;
        }

        // got tif nbtivf MS TGT
        mstidkft = &(TktCbdifRfsponsf->Tidkft);

        nftypfs = (*fnv)->GftArrbyLfngti(fnv, jftypfs);
        ftypfs = (jint *) (*fnv)->GftIntArrbyElfmfnts(fnv, jftypfs, NULL);

        if (ftypfs == NULL) {
            brfbk;
        }

        // difdk TGT vblidity
        if (nbtivf_dfbug) {
            printf("LSA: TICKET SfssionKfy KfyTypf is %d\n", mstidkft->SfssionKfy.KfyTypf);
        }

        if ((mstidkft->TidkftFlbgs & KERB_TICKET_FLAGS_invblid) == 0) {
            GftSystfmTimfAsFilfTimf(&Now);
            EndTimf.dwLowDbtfTimf = mstidkft->EndTimf.LowPbrt;
            EndTimf.dwHigiDbtfTimf = mstidkft->EndTimf.HigiPbrt;
            FilfTimfToLodblFilfTimf(&EndTimf, &LodblEndTimf);
            if (CompbrfFilfTimf(&Now, &LodblEndTimf) < 0) {
                for (i=0; i<nftypfs; i++) {
                    if (ftypfs[i] == mstidkft->SfssionKfy.KfyTypf) {
                        found = 1;
                        if (nbtivf_dfbug) {
                            printf("LSA: Vblid ftypf found: %d\n", ftypfs[i]);
                        }
                        brfbk;
                    }
                }
            }
        }

        if (!found) {
            if (nbtivf_dfbug) {
                printf("LSA: MS TGT in dbdif is invblid/not supportfd; rfqufst nfw tidkft\n");
            }

            // usf dombin to rfqufst Tidkft
            Stbtus = ConstrudtTidkftRfqufst(mstidkft->TbrgftDombinNbmf,
                                &pTidkftRfqufst, &rfqufstSizf);
            if (!LSA_SUCCESS(Stbtus)) {
                SiowNTError("ConstrudtTidkftRfqufst stbtus", Stbtus);
                brfbk;
            }

            pTidkftRfqufst->MfssbgfTypf = KfrbRftrifvfEndodfdTidkftMfssbgf;
            pTidkftRfqufst->CbdifOptions = KERB_RETRIEVE_TICKET_DONT_USE_CACHE;

            for (i=0; i<nftypfs; i++) {
                pTidkftRfqufst->EndryptionTypf = ftypfs[i];
                Stbtus = LsbCbllAutifntidbtionPbdkbgf(
                            LogonHbndlf,
                            PbdkbgfId,
                            pTidkftRfqufst,
                            rfqufstSizf,
                            &pTidkftRfsponsf,
                            &rfsponsfSizf,
                            &SubStbtus
                            );

                if (nbtivf_dfbug) {
                    printf("LSA: Rfsponsf sizf is %d for %d\n", rfsponsfSizf, ftypfs[i]);
                }

                if (!LSA_SUCCESS(Stbtus) || !LSA_SUCCESS(SubStbtus)) {
                    if (!LSA_SUCCESS(Stbtus)) {
                        SiowNTError("LsbCbllAutifntidbtionPbdkbgf", Stbtus);
                    } flsf {
                        SiowNTError("Protodol stbtus", SubStbtus);
                    }
                    dontinuf;
                }

                // got tif nbtivf MS Kfrbfros TGT
                mstidkft = &(pTidkftRfsponsf->Tidkft);

                if (mstidkft->SfssionKfy.KfyTypf != ftypfs[i]) {
                    if (nbtivf_dfbug) {
                        printf("LSA: Rfsponsf ftypf is %d for %d. Rftry.\n", mstidkft->SfssionKfy.KfyTypf, ftypfs[i]);
                    }
                    dontinuf;
                }
                found = 1;
                brfbk;
            }
        }

        if (ftypfs != NULL) {
            (*fnv)->RflfbsfIntArrbyElfmfnts(fnv, jftypfs, ftypfs, 0);
        }

        /*

        typfdff strudt _KERB_RETRIEVE_TKT_RESPONSE {
            KERB_EXTERNAL_TICKET Tidkft;
        } KERB_RETRIEVE_TKT_RESPONSE, *PKERB_RETRIEVE_TKT_RESPONSE;

        typfdff strudt _KERB_EXTERNAL_TICKET {
            PKERB_EXTERNAL_NAME SfrvidfNbmf;
            PKERB_EXTERNAL_NAME TbrgftNbmf;
            PKERB_EXTERNAL_NAME ClifntNbmf;
            UNICODE_STRING DombinNbmf;
            UNICODE_STRING TbrgftDombinNbmf;
            UNICODE_STRING AltTbrgftDombinNbmf;
            KERB_CRYPTO_KEY SfssionKfy;
            ULONG TidkftFlbgs;
            ULONG Flbgs;
            LARGE_INTEGER KfyExpirbtionTimf;
            LARGE_INTEGER StbrtTimf;
            LARGE_INTEGER EndTimf;
            LARGE_INTEGER RfnfwUntil;
            LARGE_INTEGER TimfSkfw;
            ULONG EndodfdTidkftSizf;
            PUCHAR EndodfdTidkft; <========== Hfrf's tif good stuff
        } KERB_EXTERNAL_TICKET, *PKERB_EXTERNAL_TICKET;

        typfdff strudt _KERB_EXTERNAL_NAME {
            SHORT NbmfTypf;
            USHORT NbmfCount;
            UNICODE_STRING Nbmfs[ANYSIZE_ARRAY];
        } KERB_EXTERNAL_NAME, *PKERB_EXTERNAL_NAME;

        typfdff strudt _LSA_UNICODE_STRING {
            USHORT Lfngti;
            USHORT MbximumLfngti;
            PWSTR  Bufffr;
        } LSA_UNICODE_STRING, *PLSA_UNICODE_STRING;

        typfdff LSA_UNICODE_STRING UNICODE_STRING, *PUNICODE_STRING;

        typfdff strudt KERB_CRYPTO_KEY {
            LONG KfyTypf;
            ULONG Lfngti;
            PUCHAR Vbluf;
        } KERB_CRYPTO_KEY, *PKERB_CRYPTO_KEY;

        */
        if (!found) {
            brfbk;
        }

        // Build b dom.sun.sfdurity.krb5.Tidkft
        tidkft = BuildTidkft(fnv, mstidkft->EndodfdTidkft,
                                mstidkft->EndodfdTidkftSizf);
        if (tidkft == NULL) {
            brfbk;
        }
        // OK, ibvf b Tidkft, now nffd to gft tif dlifnt nbmf
        dlifntPrindipbl = BuildPrindipbl(fnv, mstidkft->ClifntNbmf,
                                mstidkft->TbrgftDombinNbmf); // mdu
        if (dlifntPrindipbl == NULL) {
            brfbk;
        }

        // bnd tif "nbmf" of tgt
        tbrgftPrindipbl = BuildPrindipbl(fnv, mstidkft->SfrvidfNbmf,
                        mstidkft->DombinNbmf);
        if (tbrgftPrindipbl == NULL) {
            brfbk;
        }

        // Gft tif fndryption kfy
        fndryptionKfy = BuildEndryptionKfy(fnv, &(mstidkft->SfssionKfy));
        if (fndryptionKfy == NULL) {
            brfbk;
        }

        // bnd tif tidkft flbgs
        tidkftFlbgs = BuildTidkftFlbgs(fnv, &(mstidkft->TidkftFlbgs));
        if (tidkftFlbgs == NULL) {
            brfbk;
        }

        // Gft tif stbrt timf
        stbrtTimf = BuildKfrbfrosTimf(fnv, &(mstidkft->StbrtTimf));
        if (stbrtTimf == NULL) {
            brfbk;
        }

        /*
         * mdu: No point storing tif fky fxpirbtion timf in tif buti
         * timf fifld. Sft it to bf sbmf bs stbrtTimf. Looks likf
         * windows dofs not ibvf post-dbtfd tidkfts.
         */
        butiTimf = stbrtTimf;

        // bnd tif fnd timf
        fndTimf = BuildKfrbfrosTimf(fnv, &(mstidkft->EndTimf));
        if (fndTimf == NULL) {
            brfbk;
        }

        // Gft tif rfnfw till timf
        rfnfwTillTimf = BuildKfrbfrosTimf(fnv, &(mstidkft->RfnfwUntil));
        if (rfnfwTillTimf == NULL) {
            brfbk;
        }

        // bnd now go build b KrbCrfds objfdt
        krbCrfds = (*fnv)->NfwObjfdt(
                fnv,
                krbdrfdsClbss,
                krbdrfdsConstrudtor,
                tidkft,
                dlifntPrindipbl,
                tbrgftPrindipbl,
                fndryptionKfy,
                tidkftFlbgs,
                butiTimf, // mdu
                stbrtTimf,
                fndTimf,
                rfnfwTillTimf, //mdu
                iostAddrfssfs);

        brfbk;
    } // fnd of WHILE. Tiis WHILE will nfvfr loop.

    // dlfbn up rfsourdfs
    if (TktCbdifRfsponsf != NULL) {
        LsbFrffRfturnBufffr(TktCbdifRfsponsf);
    }
    if (pTidkftRfqufst) {
        LodblFrff(pTidkftRfqufst);
    }
    if (pTidkftRfsponsf != NULL) {
        LsbFrffRfturnBufffr(pTidkftRfsponsf);
    }

    rfturn krbCrfds;
}

stbtid NTSTATUS
ConstrudtTidkftRfqufst(UNICODE_STRING DombinNbmf,
                PKERB_RETRIEVE_TKT_REQUEST *outRfqufst, ULONG *outSizf)
{
    NTSTATUS Stbtus;
    UNICODE_STRING TbrgftPrffix;
    USHORT TbrgftSizf;
    ULONG RfqufstSizf;
    ULONG Lfngti;
    PKERB_RETRIEVE_TKT_REQUEST pTidkftRfqufst = NULL;

    *outRfqufst = NULL;
    *outSizf = 0;

    //
    // Sft up tif "krbtgt/" tbrgft prffix into b UNICODE_STRING so wf
    // dbn fbsily dondbtfnbtf it lbtfr.
    //

    TbrgftPrffix.Bufffr = L"krbtgt/";
    Lfngti = (ULONG)wdslfn(TbrgftPrffix.Bufffr) * sizfof(WCHAR);
    TbrgftPrffix.Lfngti = (USHORT)Lfngti;
    TbrgftPrffix.MbximumLfngti = TbrgftPrffix.Lfngti;

    //
    // Wf will nffd to dondbtfnbtf tif "krbtgt/" prffix bnd tif
    // Logon Sfssion's DnsDombinNbmf into our rfqufst's tbrgft nbmf.
    //
    // Tifrfforf, first domputf tif nfdfssbry bufffr sizf for tibt.
    //
    // Notf tibt wf migit tiforftidblly ibvf intfgfr ovfrflow.
    //

    TbrgftSizf = TbrgftPrffix.Lfngti + DombinNbmf.Lfngti;

    //
    // Tif tidkft rfqufst bufffr nffds to bf b singlf bufffr.  Tibt bufffr
    // nffds to indludf tif bufffr for tif tbrgft nbmf.
    //

    RfqufstSizf = sizfof (*pTidkftRfqufst) + TbrgftSizf;

    //
    // Allodbtf tif rfqufst bufffr bnd mbkf surf it's zfro-fillfd.
    //

    pTidkftRfqufst = (PKERB_RETRIEVE_TKT_REQUEST)
                    LodblAllod(LMEM_ZEROINIT, RfqufstSizf);
    if (!pTidkftRfqufst)
        rfturn GftLbstError();

    //
    // Condbtfnbtf tif tbrgft prffix witi tif prfvious rfsponsf's
    // tbrgft dombin.
    //

    pTidkftRfqufst->TbrgftNbmf.Lfngti = 0;
    pTidkftRfqufst->TbrgftNbmf.MbximumLfngti = TbrgftSizf;
    pTidkftRfqufst->TbrgftNbmf.Bufffr = (PWSTR) (pTidkftRfqufst + 1);
    Stbtus = CondbtfnbtfUnidodfStrings(&(pTidkftRfqufst->TbrgftNbmf),
                                    TbrgftPrffix,
                                    DombinNbmf);
    *outRfqufst = pTidkftRfqufst;
    *outSizf    = RfqufstSizf;
    rfturn Stbtus;
}

DWORD
CondbtfnbtfUnidodfStrings(
    UNICODE_STRING *pTbrgft,
    UNICODE_STRING Sourdf1,
    UNICODE_STRING Sourdf2
    )
{
    //
    // Tif bufffrs for Sourdf1 bnd Sourdf2 dbnnot ovfrlbp pTbrgft's
    // bufffr.  Sourdf1.Lfngti + Sourdf2.Lfngti must bf <= 0xFFFF,
    // otifrwisf wf ovfrflow...
    //

    USHORT TotblSizf = Sourdf1.Lfngti + Sourdf2.Lfngti;
    PBYTE bufffr = (PBYTE) pTbrgft->Bufffr;

    if (TotblSizf > pTbrgft->MbximumLfngti)
        rfturn ERROR_INSUFFICIENT_BUFFER;

    pTbrgft->Lfngti = TotblSizf;
    mfmdpy(bufffr, Sourdf1.Bufffr, Sourdf1.Lfngti);
    mfmdpy(bufffr + Sourdf1.Lfngti, Sourdf2.Bufffr, Sourdf2.Lfngti);
    rfturn ERROR_SUCCESS;
}

BOOL
PbdkbgfConnfdtLookup(
    HANDLE *pLogonHbndlf,
    ULONG *pPbdkbgfId
    )
{
    LSA_STRING Nbmf;
    NTSTATUS Stbtus;

    Stbtus = LsbConnfdtUntrustfd(
                pLogonHbndlf
                );

    if (!LSA_SUCCESS(Stbtus))
    {
        SiowNTError("LsbConnfdtUntrustfd", Stbtus);
        rfturn FALSE;
    }

    Nbmf.Bufffr = MICROSOFT_KERBEROS_NAME_A;
    Nbmf.Lfngti = (USHORT)strlfn(Nbmf.Bufffr);
    Nbmf.MbximumLfngti = Nbmf.Lfngti + 1;

    Stbtus = LsbLookupAutifntidbtionPbdkbgf(
                *pLogonHbndlf,
                &Nbmf,
                pPbdkbgfId
                );

    if (!LSA_SUCCESS(Stbtus))
    {
        SiowNTError("LsbLookupAutifntidbtionPbdkbgf", Stbtus);
        rfturn FALSE;
    }

    rfturn TRUE;

}

VOID
SiowLbstError(
        LPSTR szAPI,
        DWORD dwError
        )
{
    #dffinf MAX_MSG_SIZE 256

    stbtid WCHAR szMsgBuf[MAX_MSG_SIZE];
    DWORD dwRfs;

    if (nbtivf_dfbug) {
        printf("LSA: Error dblling fundtion %s: %lu\n", szAPI, dwError);
    }

    dwRfs = FormbtMfssbgf (
            FORMAT_MESSAGE_FROM_SYSTEM,
            NULL,
            dwError,
            0,
            szMsgBuf,
            MAX_MSG_SIZE,
            NULL);
    if (nbtivf_dfbug) {
        if (0 == dwRfs) {
            printf("LSA: FormbtMfssbgf fbilfd witi %d\n", GftLbstError());
            // ExitProdfss(EXIT_FAILURE);
        } flsf {
            printf("LSA: %S",szMsgBuf);
        }
    }
}

VOID
SiowNTError(
        LPSTR szAPI,
        NTSTATUS Stbtus
        )
{
    //
    // Convfrt tif NTSTATUS to Winfrror. Tifn dbll SiowLbstError().
    //
    SiowLbstError(szAPI, LsbNtStbtusToWinError(Stbtus));
}

VOID
InitUnidodfString(
        PUNICODE_STRING DfstinbtionString,
    PCWSTR SourdfString OPTIONAL
    )
{
    ULONG Lfngti;

    DfstinbtionString->Bufffr = (PWSTR)SourdfString;
    if (SourdfString != NULL) {
        Lfngti = (ULONG)wdslfn( SourdfString ) * sizfof( WCHAR );
        DfstinbtionString->Lfngti = (USHORT)Lfngti;
        DfstinbtionString->MbximumLfngti = (USHORT)(Lfngti + sizfof(UNICODE_NULL));
    }
    flsf {
        DfstinbtionString->MbximumLfngti = 0;
        DfstinbtionString->Lfngti = 0;
    }
}

jobjfdt BuildTidkft(JNIEnv *fnv, PUCHAR fndodfdTidkft, ULONG fndodfdTidkftSizf) {

    /* To build b Tidkft, wf first nffd to build b DfrVbluf out of tif EndodfdTidkft.
     * But bfforf wf dbn do tibt, wf nffd to mbkf b bytf brrby out of tif ET.
     */

    jobjfdt dfrVbluf, tidkft;
    jbytfArrby bry;

    bry = (*fnv)->NfwBytfArrby(fnv,fndodfdTidkftSizf);
    if ((*fnv)->ExdfptionOddurrfd(fnv)) {
        rfturn (jobjfdt) NULL;
    }

    (*fnv)->SftBytfArrbyRfgion(fnv, bry, (jsizf) 0, fndodfdTidkftSizf,
                                    (jbytf *)fndodfdTidkft);
    if ((*fnv)->ExdfptionOddurrfd(fnv)) {
        (*fnv)->DflftfLodblRff(fnv, bry);
        rfturn (jobjfdt) NULL;
    }

    dfrVbluf = (*fnv)->NfwObjfdt(fnv, dfrVblufClbss, dfrVblufConstrudtor, bry);
    if ((*fnv)->ExdfptionOddurrfd(fnv)) {
        (*fnv)->DflftfLodblRff(fnv, bry);
        rfturn (jobjfdt) NULL;
    }

    (*fnv)->DflftfLodblRff(fnv, bry);
    tidkft = (*fnv)->NfwObjfdt(fnv, tidkftClbss, tidkftConstrudtor, dfrVbluf);
    if ((*fnv)->ExdfptionOddurrfd(fnv)) {
        (*fnv)->DflftfLodblRff(fnv, dfrVbluf);
        rfturn (jobjfdt) NULL;
    }
    (*fnv)->DflftfLodblRff(fnv, dfrVbluf);
    rfturn tidkft;
}

// mdu
jobjfdt BuildPrindipbl(JNIEnv *fnv, PKERB_EXTERNAL_NAME prindipblNbmf,
                                UNICODE_STRING dombinNbmf) {

    /*
     * To build tif Prindipbl, wf nffd to gft tif nbmfs out of
     * tiis goofy MS strudturf
     */
    jobjfdt prindipbl = NULL;
    jobjfdt rfblmStr = NULL;
    jobjfdtArrby stringArrby;
    jstring tfmpString;
    int nbmfCount,i;
    PUNICODE_STRING sdbnnfr;
    WCHAR *rfblm;
    ULONG rfblmLfn;

    rfblm = (WCHAR *) LodblAllod(LMEM_ZEROINIT,
            ((dombinNbmf.Lfngti)*sizfof(WCHAR) + sizfof(UNICODE_NULL)));
    wdsndpy(rfblm, dombinNbmf.Bufffr, dombinNbmf.Lfngti/sizfof(WCHAR));

    if (nbtivf_dfbug) {
        printf("LSA: Prindipbl dombin is %S\n", rfblm);
        printf("LSA: Nbmf typf is %x\n", prindipblNbmf->NbmfTypf);
        printf("LSA: Nbmf dount is %x\n", prindipblNbmf->NbmfCount);
    }

    nbmfCount = prindipblNbmf->NbmfCount;
    stringArrby = (*fnv)->NfwObjfdtArrby(fnv, nbmfCount,
                            jbvbLbngStringClbss, NULL);
    if (stringArrby == NULL) {
        if (nbtivf_dfbug) {
            printf("LSA: Cbn't bllodbtf String brrby for Prindipbl\n");
        }
        goto dlfbnup;
    }

    for (i=0; i<nbmfCount; i++) {
        // gft tif prindipbl nbmf
        sdbnnfr = &(prindipblNbmf->Nbmfs[i]);

        // OK, got b Cibr brrby, so donstrudt b String
        tfmpString = (*fnv)->NfwString(fnv, (donst jdibr*)sdbnnfr->Bufffr,
                            sdbnnfr->Lfngti/sizfof(WCHAR));

        if (tfmpString == NULL) {
            goto dlfbnup;
        }

        // Sft tif String into tif StringArrby
        (*fnv)->SftObjfdtArrbyElfmfnt(fnv, stringArrby, i, tfmpString);

        if ((*fnv)->ExdfptionCifdk(fnv)) {
            goto dlfbnup;
        }

        // Do I ibvf to worry bbout storbgf rfdlbmbtion ifrf?
    }
    // now sft tif rfblm in tif prindipbl
    rfblmLfn = (ULONG)wdslfn((PWCHAR)rfblm);
    rfblmStr = (*fnv)->NfwString(fnv, (PWCHAR)rfblm, (USHORT)rfblmLfn);

    if (rfblmStr == NULL) {
        goto dlfbnup;
    }

    prindipbl = (*fnv)->NfwObjfdt(fnv, prindipblNbmfClbss,
                    prindipblNbmfConstrudtor, stringArrby, rfblmStr);

dlfbnup:
    // frff lodbl rfsourdfs
    LodblFrff(rfblm);

    rfturn prindipbl;
}

jobjfdt BuildEndryptionKfy(JNIEnv *fnv, PKERB_CRYPTO_KEY dryptoKfy) {
    // First, nffd to build b bytf brrby
    jbytfArrby bry;
    jobjfdt fndryptionKfy = NULL;
    unsignfd int i;

    for (i=0; i<dryptoKfy->Lfngti; i++) {
        if (dryptoKfy->Vbluf[i]) brfbk;
    }
    if (i == dryptoKfy->Lfngti) {
        if (nbtivf_dfbug) {
            printf("LSA: Sfssion kfy bll zfro. Stop.\n");
        }
        rfturn NULL;
    }

    bry = (*fnv)->NfwBytfArrby(fnv,dryptoKfy->Lfngti);
    (*fnv)->SftBytfArrbyRfgion(fnv, bry, (jsizf) 0, dryptoKfy->Lfngti,
                                    (jbytf *)dryptoKfy->Vbluf);
    if ((*fnv)->ExdfptionOddurrfd(fnv)) {
        (*fnv)->DflftfLodblRff(fnv, bry);
    } flsf {
        fndryptionKfy = (*fnv)->NfwObjfdt(fnv, fndryptionKfyClbss,
                fndryptionKfyConstrudtor, dryptoKfy->KfyTypf, bry);
    }

    rfturn fndryptionKfy;
}

jobjfdt BuildTidkftFlbgs(JNIEnv *fnv, PULONG flbgs) {
    jobjfdt tidkftFlbgs = NULL;
    jbytfArrby bry;
    /*
     * mdu: Convfrt tif bytfs to nfwork bytf ordfr bfforf dopying
     * tifm to b Jbvb bytf brrby.
     */
    ULONG nlflbgs = itonl(*flbgs);

    bry = (*fnv)->NfwBytfArrby(fnv, sizfof(*flbgs));
    (*fnv)->SftBytfArrbyRfgion(fnv, bry, (jsizf) 0, sizfof(*flbgs),
                                    (jbytf *)&nlflbgs);
    if ((*fnv)->ExdfptionOddurrfd(fnv)) {
        (*fnv)->DflftfLodblRff(fnv, bry);
    } flsf {
        tidkftFlbgs = (*fnv)->NfwObjfdt(fnv, tidkftFlbgsClbss,
                tidkftFlbgsConstrudtor, sizfof(*flbgs)*8, bry);
    }

    rfturn tidkftFlbgs;
}

jobjfdt BuildKfrbfrosTimf(JNIEnv *fnv, PLARGE_INTEGER kfrbtimf) {
    jobjfdt kfrbfrosTimf = NULL;
    jstring stringTimf = NULL;
    SYSTEMTIME systfmTimf;
    WCHAR timfString[16];
    WCHAR monti[3];
    WCHAR dby[3];
    WCHAR iour[3];
    WCHAR minutf[3];
    WCHAR sfdond[3];

    if (FilfTimfToSystfmTimf((FILETIME *)kfrbtimf, &systfmTimf)) {
        // XXX Cbnnot usf %02.2ld, bfdbusf tif lfbding 0 is ignorfd for intfgfrs.
        // So, print tifm to strings, bnd tifn print tifm to tif mbstfr string witi b
        // formbt pbttfrn tibt mbkfs it two digits bnd prffix witi b 0 if nfdfssbry.
        swprintf( (wdibr_t *)monti, 3, L"%2.2d", systfmTimf.wMonti);
        swprintf( (wdibr_t *)dby, 3, L"%2.2d", systfmTimf.wDby);
        swprintf( (wdibr_t *)iour, 3, L"%2.2d", systfmTimf.wHour);
        swprintf( (wdibr_t *)minutf, 3, L"%2.2d", systfmTimf.wMinutf);
        swprintf( (wdibr_t *)sfdond, 3, L"%2.2d", systfmTimf.wSfdond);
        swprintf( (wdibr_t *)timfString, 16,
                L"%ld%02.2s%02.2s%02.2s%02.2s%02.2sZ",
                systfmTimf.wYfbr,
                monti,
                dby,
                iour,
                minutf,
                sfdond );
        if (nbtivf_dfbug) {
            printf("LSA: %S\n", (wdibr_t *)timfString);
        }
        stringTimf = (*fnv)->NfwString(fnv, timfString,
                (sizfof(timfString)/sizfof(WCHAR))-1);
        if (stringTimf != NULL) { // fvfrytiing's OK so fbr
            kfrbfrosTimf = (*fnv)->NfwObjfdt(fnv, kfrbfrosTimfClbss,
                    kfrbfrosTimfConstrudtor, stringTimf);
        }
    }
    rfturn kfrbfrosTimf;
}
