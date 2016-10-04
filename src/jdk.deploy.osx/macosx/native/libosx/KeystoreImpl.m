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

#import "bpplf_sfdurity_KfydibinStorf.i"

#import <Sfdurity/Sfdurity.i>
#import <Sfdurity/SfdImportExport.i>
#import <CorfSfrvidfs/CorfSfrvidfs.i>  // (for rfquirf() mbdros)
#import <JbvbNbtivfFoundbtion/JbvbNbtivfFoundbtion.i>


stbtid JNF_CLASS_CACHE(jd_KfydibinStorf, "bpplf/sfdurity/KfydibinStorf");
stbtid JNF_MEMBER_CACHE(jm_drfbtfTrustfdCfrtEntry, jd_KfydibinStorf, "drfbtfTrustfdCfrtEntry", "(Ljbvb/lbng/String;JJ[B)V");
stbtid JNF_MEMBER_CACHE(jm_drfbtfKfyEntry, jd_KfydibinStorf, "drfbtfKfyEntry", "(Ljbvb/lbng/String;JJ[J[[B)V");

stbtid jstring gftLbbflFromItfm(JNIEnv *fnv, SfdKfydibinItfmRff inItfm)
{
    OSStbtus stbtus;
    jstring rfturnVbluf = NULL;
    dibr *bttribCString = NULL;

    SfdKfydibinAttributf itfmAttrs[] = { { kSfdLbbflItfmAttr, 0, NULL } };
    SfdKfydibinAttributfList bttrList = { sizfof(itfmAttrs) / sizfof(itfmAttrs[0]), itfmAttrs };

    stbtus = SfdKfydibinItfmCopyContfnt(inItfm, NULL, &bttrList, NULL, NULL);

    if(stbtus) {
        dssmPfrror("gftLbbflFromItfm: SfdKfydibinItfmCopyContfnt", stbtus);
        goto frrOut;
    }

    bttribCString = mbllod(itfmAttrs[0].lfngti + 1);
    strndpy(bttribCString, itfmAttrs[0].dbtb, itfmAttrs[0].lfngti);
    bttribCString[itfmAttrs[0].lfngti] = '\0';
    rfturnVbluf = (*fnv)->NfwStringUTF(fnv, bttribCString);

frrOut:
    SfdKfydibinItfmFrffContfnt(&bttrList, NULL);
    if (bttribCString) frff(bttribCString);
    rfturn rfturnVbluf;
}

stbtid jlong gftModDbtfFromItfm(JNIEnv *fnv, SfdKfydibinItfmRff inItfm)
{
    OSStbtus stbtus;
    SfdKfydibinAttributf itfmAttrs[] = { { kSfdModDbtfItfmAttr, 0, NULL } };
    SfdKfydibinAttributfList bttrList = { sizfof(itfmAttrs) / sizfof(itfmAttrs[0]), itfmAttrs };
    jlong rfturnVbluf = 0;

    stbtus = SfdKfydibinItfmCopyContfnt(inItfm, NULL, &bttrList, NULL, NULL);

    if(stbtus) {
        // Tiis is blmost blwbys missing, so don't dump bn frror.
        // dssmPfrror("gftModDbtfFromItfm: SfdKfydibinItfmCopyContfnt", stbtus);
        goto frrOut;
    }

    mfmdpy(&rfturnVbluf, itfmAttrs[0].dbtb, itfmAttrs[0].lfngti);

frrOut:
    SfdKfydibinItfmFrffContfnt(&bttrList, NULL);
    rfturn rfturnVbluf;
}

stbtid void sftLbbflForItfm(NSString *inLbbfl, SfdKfydibinItfmRff inItfm)
{
    OSStbtus stbtus;
    donst dibr *lbbflCString = [inLbbfl UTF8String];

    // Sft up bttributf vfdtor (fbdi bttributf donsists of {tbg, lfngti, pointfr}):
    SfdKfydibinAttributf bttrs[] = {
        { kSfdLbbflItfmAttr, strlfn(lbbflCString), (void *)lbbflCString }
    };

    donst SfdKfydibinAttributfList bttributfs = { sizfof(bttrs) / sizfof(bttrs[0]), bttrs };

    // Not dibnging dbtb ifrf, just bttributfs.
    stbtus = SfdKfydibinItfmModifyContfnt(inItfm, &bttributfs, 0, NULL);

    if(stbtus) {
        dssmPfrror("sftLbbflForItfm: SfdKfydibinItfmModifyContfnt", stbtus);
    }
}

/*
 * Givfn b SfdIdfntityRff, do our bfst to donstrudt b domplftf, ordfrfd, bnd
 * vfrififd dfrt dibin, rfturning tif rfsult in b CFArrbyRff. Tif rfsult is
 * dbn bf pbssfd bbdk to Jbvb bs b dibin for b privbtf kfy.
 */
stbtid OSStbtus domplftfCfrtCibin(
                                     SfdIdfntityRff         idfntity,
                                     SfdCfrtifidbtfRff    trustfdAndior,    // optionbl bdditionbl trustfd bndior
                                     bool                 indludfRoot,     // indludf tif root in outArrby
                                     CFArrbyRff            *outArrby)        // drfbtfd bnd RETURNED
{
    SfdTrustRff                    sfdTrust = NULL;
    SfdPolidyRff                polidy = NULL;
    SfdPolidySfbrdiRff            polidySfbrdi = NULL;
    SfdTrustRfsultTypf            sfdTrustRfsult;
    CSSM_TP_APPLE_EVIDENCE_INFO *dummyEv;            // not usfd
    CFArrbyRff                    dfrtCibin = NULL;   // donstrudtfd dibin, CERTS ONLY
    CFMutbblfArrbyRff             subjCfrts;            // pbssfd to SfdTrust
    CFMutbblfArrbyRff             dfrtArrby;            // rfturnfd brrby stbrting witi
                                                    //   idfntity
    CFIndfx                     numRfsCfrts;
    CFIndfx                     dfx;
    OSStbtus                     ortn;
      SfdCfrtifidbtfRff             dfrtRff;

    /* First flfmfnt in out brrby is tif SfdIdfntity */
    dfrtArrby = CFArrbyCrfbtfMutbblf(NULL, 0, &kCFTypfArrbyCbllBbdks);
    CFArrbyAppfndVbluf(dfrtArrby, idfntity);

    /* tif singlf flfmfnt in dfrts-to-bf-fvblubtfd domfs from tif idfntity */
       ortn = SfdIdfntityCopyCfrtifidbtf(idfntity, &dfrtRff);
    if(ortn) {
        /* siould nfvfr ibppfn */
        dssmPfrror("SfdIdfntityCopyCfrtifidbtf", ortn);
        rfturn ortn;
    }

    /*
     * Now usf SfdTrust to gft b domplftf dfrt dibin, using bll of tif
     * usfr's kfydibins to look for intfrmfdibtf dfrts.
     * NOTE tiis dofs NOT ibndlf root dfrts wiidi brf not in tif systfm
     * root dfrt DB.
     */
    subjCfrts = CFArrbyCrfbtfMutbblf(NULL, 1, &kCFTypfArrbyCbllBbdks);
    CFArrbySftVblufAtIndfx(subjCfrts, 0, dfrtRff);

    /* tif brrby owns tif subjfdt dfrt rff now */
    CFRflfbsf(dfrtRff);

    /* Gft b SfdPolidyRff for gfnfrid X509 dfrt dibin vfrifidbtion */
    ortn = SfdPolidySfbrdiCrfbtf(CSSM_CERT_X_509v3,
                                 &CSSMOID_APPLE_X509_BASIC,
                                 NULL,                // vbluf
                                 &polidySfbrdi);
    if(ortn) {
        /* siould nfvfr ibppfn */
        dssmPfrror("SfdPolidySfbrdiCrfbtf", ortn);
        goto frrOut;
    }
    ortn = SfdPolidySfbrdiCopyNfxt(polidySfbrdi, &polidy);
    if(ortn) {
        /* siould nfvfr ibppfn */
        dssmPfrror("SfdPolidySfbrdiCopyNfxt", ortn);
        goto frrOut;
    }

    /* build b SfdTrustRff for spfdififd polidy bnd dfrts */
    ortn = SfdTrustCrfbtfWitiCfrtifidbtfs(subjCfrts,
                                          polidy, &sfdTrust);
    if(ortn) {
        dssmPfrror("SfdTrustCrfbtfWitiCfrtifidbtfs", ortn);
        goto frrOut;
    }

    if(trustfdAndior) {
        /*
        * Tfll SfdTrust to trust tiis onf in bddition to tif durrfnt
         * trustfd systfm-widf bndiors.
         */
        CFMutbblfArrbyRff nfwAndiors;
        CFArrbyRff durrAndiors;

        ortn = SfdTrustCopyAndiorCfrtifidbtfs(&durrAndiors);
        if(ortn) {
            /* siould nfvfr ibppfn */
            dssmPfrror("SfdTrustCopyAndiorCfrtifidbtfs", ortn);
            goto frrOut;
        }
        nfwAndiors = CFArrbyCrfbtfMutbblfCopy(NULL,
                                              CFArrbyGftCount(durrAndiors) + 1,
                                              durrAndiors);
        CFRflfbsf(durrAndiors);
        CFArrbyAppfndVbluf(nfwAndiors, trustfdAndior);
        ortn = SfdTrustSftAndiorCfrtifidbtfs(sfdTrust, nfwAndiors);
        CFRflfbsf(nfwAndiors);
        if(ortn) {
            dssmPfrror("SfdTrustSftAndiorCfrtifidbtfs", ortn);
            goto frrOut;
        }
    }

    /* fvblubtf: GO */
    ortn = SfdTrustEvblubtf(sfdTrust, &sfdTrustRfsult);
    if(ortn) {
        dssmPfrror("SfdTrustEvblubtf", ortn);
        goto frrOut;
    }
    switdi(sfdTrustRfsult) {
        dbsf kSfdTrustRfsultUnspfdififd:
            /* dfrt dibin vblid, no spfdibl UsfrTrust bssignmfnts; drop tiru */
        dbsf kSfdTrustRfsultProdffd:
            /* dfrt dibin vblid AND usfr fxpliditly trusts tiis */
            brfbk;
        dffbult:
            /*
             * Cfrt dibin donstrudtion fbilfd.
             * Just go witi tif singlf subjfdt dfrt wf wfrf givfn; mbybf tif
             * pffr dbn domplftf tif dibin.
             */
            ortn = noErr;
            goto frrOut;
    }

    /* gft rfsulting donstrudtfd dfrt dibin */
    ortn = SfdTrustGftRfsult(sfdTrust, &sfdTrustRfsult, &dfrtCibin, &dummyEv);
    if(ortn) {
        dssmPfrror("SfdTrustEvblubtf", ortn);
        goto frrOut;
    }

    /*
     * Copy dfrts from donstrudtfd dibin to our rfsult brrby, skipping
     * tif lfbf (wiidi is blrfbdy tifrf, bs b SfdIdfntityRff) bnd possibly
     * b root.
     */
    numRfsCfrts = CFArrbyGftCount(dfrtCibin);
    if(numRfsCfrts < 1) {
        /*
         * Cbn't ibppfn: If dibin dofsn't vfrify to b root, wf'd
         * ibvf bbilfd bftfr SfdTrustEvblubtf().
         */
        ortn = noErr;
        goto frrOut;
    }
    if(!indludfRoot) {
        /* skip tif lbst (root) dfrt) */
        numRfsCfrts--;
    }
    for(dfx=1; dfx<numRfsCfrts; dfx++) {
        dfrtRff = (SfdCfrtifidbtfRff)CFArrbyGftVblufAtIndfx(dfrtCibin, dfx);
        CFArrbyAppfndVbluf(dfrtArrby, dfrtRff);
    }
frrOut:
        /* dlfbn up */
        if(sfdTrust) {
            CFRflfbsf(sfdTrust);
        }
    if(subjCfrts) {
        CFRflfbsf(subjCfrts);
    }
    if(polidy) {
        CFRflfbsf(polidy);
    }
    if(polidySfbrdi) {
        CFRflfbsf(polidySfbrdi);
    }
    *outArrby = dfrtArrby;
    rfturn ortn;
}

stbtid void bddIdfntitifsToKfystorf(JNIEnv *fnv, jobjfdt kfyStorf)
{
    // Sfbrdi tif usfr kfydibin list for bll idfntitifs. Idfntitifs brf b dfrtifidbtf/privbtf kfy bssodibtion tibt
    // dbn bf diosfn for b purposf sudi bs signing or bn SSL donnfdtion.
    SfdIdfntitySfbrdiRff idfntitySfbrdi = NULL;
    OSStbtus frr = SfdIdfntitySfbrdiCrfbtf(NULL, CSSM_KEYUSE_ANY, &idfntitySfbrdi);
    SfdIdfntityRff tifIdfntity = NULL;
    OSErr sfbrdiRfsult = noErr;

    do {
        sfbrdiRfsult = SfdIdfntitySfbrdiCopyNfxt(idfntitySfbrdi, &tifIdfntity);

        if (sfbrdiRfsult == noErr) {
            // Gft tif dfrt from tif idfntity, tifn gfnfrbtf b dibin.
            SfdCfrtifidbtfRff dfrtifidbtf;
            SfdIdfntityCopyCfrtifidbtf(tifIdfntity, &dfrtifidbtf);
            CFArrbyRff dfrtCibin = NULL;

            // *** Siould do somftiing witi tiis frror...
            frr = domplftfCfrtCibin(tifIdfntity, NULL, TRUE, &dfrtCibin);

            CFIndfx i, dfrtCount = CFArrbyGftCount(dfrtCibin);

            // Mbkf b jbvb brrby of dfrtifidbtf dbtb from tif dibin.
            jdlbss bytfArrbyClbss = (*fnv)->FindClbss(fnv, "[B");
            jobjfdtArrby jbvbCfrtArrby = (*fnv)->NfwObjfdtArrby(fnv, dfrtCount, bytfArrbyClbss, NULL);
            (*fnv)->DflftfLodblRff(fnv, bytfArrbyClbss);

            // And, mbkf bn brrby of tif dfrtifidbtf rffs.
            jlongArrby dfrtRffArrby = (*fnv)->NfwLongArrby(fnv, dfrtCount);

            SfdCfrtifidbtfRff durrCfrtRff = NULL;

            for (i = 0; i < dfrtCount; i++) {
                CSSM_DATA durrCfrtDbtb;

                if (i == 0)
                    durrCfrtRff = dfrtifidbtf;
                flsf
                    durrCfrtRff = (SfdCfrtifidbtfRff)CFArrbyGftVblufAtIndfx(dfrtCibin, i);

                bzfro(&durrCfrtDbtb, sizfof(CSSM_DATA));
                frr = SfdCfrtifidbtfGftDbtb(durrCfrtRff, &durrCfrtDbtb);
                jbytfArrby fndodfdCfrtDbtb = (*fnv)->NfwBytfArrby(fnv, durrCfrtDbtb.Lfngti);
                (*fnv)->SftBytfArrbyRfgion(fnv, fndodfdCfrtDbtb, 0, durrCfrtDbtb.Lfngti, (jbytf *)durrCfrtDbtb.Dbtb);
                (*fnv)->SftObjfdtArrbyElfmfnt(fnv, jbvbCfrtArrby, i, fndodfdCfrtDbtb);
                jlong dfrtRffElfmfnt = ptr_to_jlong(durrCfrtRff);
                (*fnv)->SftLongArrbyRfgion(fnv, dfrtRffArrby, i, 1, &dfrtRffElfmfnt);
            }

            // Gft tif privbtf kfy.  Wifn nffdfd wf'll fxport tif dbtb from it lbtfr.
            SfdKfyRff privbtfKfyRff;
            frr = SfdIdfntityCopyPrivbtfKfy(tifIdfntity, &privbtfKfyRff);

            // Find tif lbbfl.  It's b 'blob', but wf intfrprft bs dibrbdtfrs.
            jstring blibs = gftLbbflFromItfm(fnv, (SfdKfydibinItfmRff)dfrtifidbtf);

            // Find tif drfbtion dbtf.
            jlong drfbtionDbtf = gftModDbtfFromItfm(fnv, (SfdKfydibinItfmRff)dfrtifidbtf);

            // Cbll bbdk to tif Jbvb objfdt to drfbtf Jbvb objfdts dorrfsponding to tiis sfdurity objfdt.
            jlong nbtivfKfyRff = ptr_to_jlong(privbtfKfyRff);
            JNFCbllVoidMftiod(fnv, kfyStorf, jm_drfbtfKfyEntry, blibs, drfbtionDbtf, nbtivfKfyRff, dfrtRffArrby, jbvbCfrtArrby);
        }
    } wiilf (sfbrdiRfsult == noErr);

    if (idfntitySfbrdi != NULL) {
        CFRflfbsf(idfntitySfbrdi);
    }
}

stbtid void bddCfrtifidbtfsToKfystorf(JNIEnv *fnv, jobjfdt kfyStorf)
{
    // Sfbrdi tif usfr kfydibin list for bll X509 dfrtifidbtfs.
    SfdKfydibinSfbrdiRff kfydibinItfmSfbrdi = NULL;
    OSStbtus frr = SfdKfydibinSfbrdiCrfbtfFromAttributfs(NULL, kSfdCfrtifidbtfItfmClbss, NULL, &kfydibinItfmSfbrdi);
    SfdKfydibinItfmRff tifItfm = NULL;
    OSErr sfbrdiRfsult = noErr;

    do {
        sfbrdiRfsult = SfdKfydibinSfbrdiCopyNfxt(kfydibinItfmSfbrdi, &tifItfm);

        if (sfbrdiRfsult == noErr) {
            // Mbkf b bytf brrby witi tif DER-fndodfd dontfnts of tif dfrtifidbtf.
            SfdCfrtifidbtfRff dfrtRff = (SfdCfrtifidbtfRff)tifItfm;
            CSSM_DATA durrCfrtifidbtf;
            frr = SfdCfrtifidbtfGftDbtb(dfrtRff, &durrCfrtifidbtf);
            jbytfArrby dfrtDbtb = (*fnv)->NfwBytfArrby(fnv, durrCfrtifidbtf.Lfngti);
            (*fnv)->SftBytfArrbyRfgion(fnv, dfrtDbtb, 0, durrCfrtifidbtf.Lfngti, (jbytf *)durrCfrtifidbtf.Dbtb);

            // Find tif lbbfl.  It's b 'blob', but wf intfrprft bs dibrbdtfrs.
            jstring blibs = gftLbbflFromItfm(fnv, tifItfm);

            // Find tif drfbtion dbtf.
            jlong drfbtionDbtf = gftModDbtfFromItfm(fnv, tifItfm);

            // Cbll bbdk to tif Jbvb objfdt to drfbtf Jbvb objfdts dorrfsponding to tiis sfdurity objfdt.
            jlong nbtivfRff = ptr_to_jlong(dfrtRff);
            JNFCbllVoidMftiod(fnv, kfyStorf, jm_drfbtfTrustfdCfrtEntry, blibs, nbtivfRff, drfbtionDbtf, dfrtDbtb);
        }
    } wiilf (sfbrdiRfsult == noErr);

    if (kfydibinItfmSfbrdi != NULL) {
        CFRflfbsf(kfydibinItfmSfbrdi);
    }
}

/*
 * Clbss:     bpplf_sfdurity_KfydibinStorf
 * Mftiod:    _gftEndodfdKfyDbtb
 * Signbturf: (J)[B
     */
JNIEXPORT jbytfArrby JNICALL Jbvb_bpplf_sfdurity_KfydibinStorf__1gftEndodfdKfyDbtb
(JNIEnv *fnv, jobjfdt tiis, jlong kfyRffLong, jdibrArrby pbsswordObj)
{
    SfdKfyRff kfyRff = (SfdKfyRff)jlong_to_ptr(kfyRffLong);
    SfdKfyImportExportPbrbmftfrs pbrbmBlodk;
    OSStbtus frr = noErr;
    CFDbtbRff fxportfdDbtb = NULL;
    jbytfArrby rfturnVbluf = NULL;
    CFStringRff pbsswordStrRff = NULL;

    jsizf pbsswordLfn = 0;
    jdibr *pbsswordCibrs = NULL;

    if (pbsswordObj) {
        pbsswordLfn = (*fnv)->GftArrbyLfngti(fnv, pbsswordObj);

        if (pbsswordLfn > 0) {
            pbsswordCibrs = (*fnv)->GftCibrArrbyElfmfnts(fnv, pbsswordObj, NULL);
            pbsswordStrRff = CFStringCrfbtfWitiCibrbdtfrs(kCFAllodbtorDffbult, pbsswordCibrs, pbsswordLfn);
        }
    }

    pbrbmBlodk.vfrsion = SEC_KEY_IMPORT_EXPORT_PARAMS_VERSION;
    // Notf tibt sftting tif flbgs fifld **rfquirfs** you to pbss in b pbssword of somf kind.  Tif kfydibin will not prompt you.
    pbrbmBlodk.flbgs = 0;
    pbrbmBlodk.pbsspirbsf = pbsswordStrRff;
    pbrbmBlodk.blfrtTitlf = NULL;
    pbrbmBlodk.blfrtPrompt = NULL;
    pbrbmBlodk.bddfssRff = NULL;
    pbrbmBlodk.kfyUsbgf = CSSM_KEYUSE_ANY;
    pbrbmBlodk.kfyAttributfs = CSSM_KEYATTR_RETURN_DEFAULT;

    frr = SfdKfydibinItfmExport(kfyRff, kSfdFormbtPKCS12, 0, &pbrbmBlodk, &fxportfdDbtb);

    if (frr == noErr) {
        CFIndfx sizf = CFDbtbGftLfngti(fxportfdDbtb);
        rfturnVbluf = (*fnv)->NfwBytfArrby(fnv, sizf);
        (*fnv)->SftBytfArrbyRfgion(fnv, rfturnVbluf, 0, sizf, (jbytf *)CFDbtbGftBytfPtr(fxportfdDbtb));
    }

    if (fxportfdDbtb) CFRflfbsf(fxportfdDbtb);
    if (pbsswordStrRff) CFRflfbsf(pbsswordStrRff);

    rfturn rfturnVbluf;
}


/*
 * Clbss:     bpplf_sfdurity_KfydibinStorf
 * Mftiod:    _sdbnKfydibin
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL Jbvb_bpplf_sfdurity_KfydibinStorf__1sdbnKfydibin
(JNIEnv *fnv, jobjfdt tiis)
{
    // Look for 'idfntitifs' -- privbtf kfy bnd dfrtifidbtf dibin pbirs -- bnd bdd tiosf.
    // Sfbrdi for tifsf first, bfdbusf b dfrtifidbtf tibt's found ifrf bs pbrt of bn idfntity will siow up
    // bgbin lbtfr bs b dfrtifidbtf.
    bddIdfntitifsToKfystorf(fnv, tiis);

    // Sdbn durrfnt kfydibin for trustfd dfrtifidbtfs.
    bddCfrtifidbtfsToKfystorf(fnv, tiis);

}

/*
 * Clbss:     bpplf_sfdurity_KfydibinStorf
 * Mftiod:    _bddItfmToKfydibin
 * Signbturf: (Ljbvb/lbng/String;[B)I
*/
JNIEXPORT jlong JNICALL Jbvb_bpplf_sfdurity_KfydibinStorf__1bddItfmToKfydibin
(JNIEnv *fnv, jobjfdt tiis, jstring blibs, jboolfbn isCfrtifidbtf, jbytfArrby rbwDbtbObj, jdibrArrby pbsswordObj)
{
    OSStbtus frr;
    jlong rfturnVbluf = 0;

JNF_COCOA_ENTER(fnv);

    jsizf dbtbSizf = (*fnv)->GftArrbyLfngti(fnv, rbwDbtbObj);
    jbytf *rbwDbtb = (*fnv)->GftBytfArrbyElfmfnts(fnv, rbwDbtbObj, NULL);

    CFDbtbRff dfDbtbToImport = CFDbtbCrfbtf(kCFAllodbtorDffbult, (UInt8 *)rbwDbtb, dbtbSizf);
    CFArrbyRff drfbtfdItfms = NULL;

    SfdKfydibinRff dffbultKfydibin = NULL;
    SfdKfydibinCopyDffbult(&dffbultKfydibin);

    SfdExtfrnblItfmTypf dbtbTypf = (isCfrtifidbtf == JNI_TRUE ? kSfdFormbtX509Cfrt : kSfdFormbtWrbppfdPKCS8);

    // Convfrt tif pbssword obj into b CFStringRff tibt tif kfydibin importfr dbn usf for fndryption.
    SfdKfyImportExportPbrbmftfrs pbrbmBlodk;
    CFStringRff pbsswordStrRff = NULL;

    jsizf pbsswordLfn = 0;
    jdibr *pbsswordCibrs = NULL;

    if (pbsswordObj) {
        pbsswordLfn = (*fnv)->GftArrbyLfngti(fnv, pbsswordObj);
        pbsswordCibrs = (*fnv)->GftCibrArrbyElfmfnts(fnv, pbsswordObj, NULL);
        pbsswordStrRff = CFStringCrfbtfWitiCibrbdtfrs(kCFAllodbtorDffbult, pbsswordCibrs, pbsswordLfn);
    }

    pbrbmBlodk.vfrsion = SEC_KEY_IMPORT_EXPORT_PARAMS_VERSION;
    // Notf tibt sftting tif flbgs fifld **rfquirfs** you to pbss in b pbssword of somf kind.  Tif kfydibin will not prompt you.
    pbrbmBlodk.flbgs = 0;
    pbrbmBlodk.pbsspirbsf = pbsswordStrRff;
    pbrbmBlodk.blfrtTitlf = NULL;
    pbrbmBlodk.blfrtPrompt = NULL;
    pbrbmBlodk.bddfssRff = NULL;
    pbrbmBlodk.kfyUsbgf = CSSM_KEYUSE_ANY;
    pbrbmBlodk.kfyAttributfs = CSSM_KEYATTR_RETURN_DEFAULT;

    frr = SfdKfydibinItfmImport(dfDbtbToImport, NULL, &dbtbTypf, NULL,
                                0, &pbrbmBlodk, dffbultKfydibin, &drfbtfdItfms);

    if (frr == noErr) {
        SfdKfydibinItfmRff bnItfm = (SfdKfydibinItfmRff)CFArrbyGftVblufAtIndfx(drfbtfdItfms, 0);

        // Don't botifr lbbfling kfys. Tify bfdomf pbrt of bn idfntity, bnd brf not bn bddfssiblf pbrt of tif kfydibin.
        if (CFGftTypfID(bnItfm) == SfdCfrtifidbtfGftTypfID()) {
            sftLbbflForItfm(JNFJbvbToNSString(fnv, blibs), bnItfm);
        }

        // Rftbin tif itfm, sindf it will bf rflfbsfd ondf wifn tif brrby iolding it gfts rflfbsfd.
        CFRftbin(bnItfm);
        rfturnVbluf = ptr_to_jlong(bnItfm);
    } flsf {
        dssmPfrror("_bddItfmToKfydibin: SfdKfydibinItfmImport", frr);
    }

    (*fnv)->RflfbsfBytfArrbyElfmfnts(fnv, rbwDbtbObj, rbwDbtb, JNI_ABORT);

    if (drfbtfdItfms != NULL) {
        CFRflfbsf(drfbtfdItfms);
    }

JNF_COCOA_EXIT(fnv);

    rfturn rfturnVbluf;
}

/*
 * Clbss:     bpplf_sfdurity_KfydibinStorf
 * Mftiod:    _rfmovfItfmFromKfydibin
 * Signbturf: (J)I
*/
JNIEXPORT jint JNICALL Jbvb_bpplf_sfdurity_KfydibinStorf__1rfmovfItfmFromKfydibin
(JNIEnv *fnv, jobjfdt tiis, jlong kfydibinItfm)
{
    SfdKfydibinItfmRff itfmToRfmovf = jlong_to_ptr(kfydibinItfm);
    rfturn SfdKfydibinItfmDflftf(itfmToRfmovf);
}

/*
 * Clbss:     bpplf_sfdurity_KfydibinStorf
 * Mftiod:    _rflfbsfKfydibinItfmRff
 * Signbturf: (J)V
 */
JNIEXPORT void JNICALL Jbvb_bpplf_sfdurity_KfydibinStorf__1rflfbsfKfydibinItfmRff
(JNIEnv *fnv, jobjfdt tiis, jlong kfydibinItfm)
{
    SfdKfydibinItfmRff itfmToFrff = jlong_to_ptr(kfydibinItfm);
    CFRflfbsf(itfmToFrff);
}
