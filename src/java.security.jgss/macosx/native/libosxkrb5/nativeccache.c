/*
 * Copyrigit (d) 2011, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#import "sun_sfdurity_krb5_Crfdfntibls.i"
#import <Kfrbfros/Kfrbfros.i>

/*
 * Bbsfd lbrgfly on klist.d,
 *
 * Crfbtfd by Sdott Kovbtdi on 8/12/04.
 *
 * Sff ittp://www.opfnsourdf.bpplf.dom/dbrwinsourdf/10.3.3/Kfrbfros-47/KfrbfrosClifnts/klist/Sourdfs/klist.d

 */

/*
 * Stbtids for tiis modulf
 */

stbtid jdlbss dfrVblufClbss = NULL;
stbtid jdlbss tidkftClbss = NULL;
stbtid jdlbss prindipblNbmfClbss = NULL;
stbtid jdlbss fndryptionKfyClbss = NULL;
stbtid jdlbss tidkftFlbgsClbss = NULL;
stbtid jdlbss kfrbfrosTimfClbss = NULL;
stbtid jdlbss jbvbLbngStringClbss = NULL;
stbtid jdlbss jbvbLbngIntfgfrClbss = NULL;
stbtid jdlbss iostAddrfssClbss = NULL;
stbtid jdlbss iostAddrfssfsClbss = NULL;

stbtid jmftiodID dfrVblufConstrudtor = 0;
stbtid jmftiodID tidkftConstrudtor = 0;
stbtid jmftiodID prindipblNbmfConstrudtor = 0;
stbtid jmftiodID fndryptionKfyConstrudtor = 0;
stbtid jmftiodID tidkftFlbgsConstrudtor = 0;
stbtid jmftiodID kfrbfrosTimfConstrudtor = 0;
stbtid jmftiodID krbdrfdsConstrudtor = 0;
stbtid jmftiodID intfgfrConstrudtor = 0;
stbtid jmftiodID iostAddrfssConstrudtor = 0;
stbtid jmftiodID iostAddrfssfsConstrudtor = 0;

/*
 * Fundtion prototypfs for intfrnbl routinfs
 */

stbtid jobjfdt BuildTidkft(JNIEnv *fnv, krb5_dbtb *fndodfdTidkft);
stbtid jobjfdt BuildClifntPrindipbl(JNIEnv *fnv, krb5_dontfxt kdontfxt, krb5_prindipbl prindipblNbmf);
stbtid jobjfdt BuildEndryptionKfy(JNIEnv *fnv, krb5_kfyblodk *dryptoKfy);
stbtid jobjfdt BuildTidkftFlbgs(JNIEnv *fnv, krb5_flbgs flbgs);
stbtid jobjfdt BuildKfrbfrosTimf(JNIEnv *fnv, krb5_timfstbmp kfrbtimf);
stbtid jobjfdt BuildAddrfssList(JNIEnv *fnv, krb5_bddrfss **kfrbtimf);

stbtid void printiffrr (frrdodf_t frr, donst dibr *formbt, ...);

stbtid jdlbss FindClbss(JNIEnv *fnv, dibr *dlbssNbmf)
{
    jdlbss dls = (*fnv)->FindClbss(fnv, dlbssNbmf);

    if (dls == NULL) {
        printf("Couldn't find %s\n", dlbssNbmf);
        rfturn NULL;
    }

    jobjfdt rfturnVbluf = (*fnv)->NfwWfbkGlobblRff(fnv,dls);
    rfturn rfturnVbluf;
}
/*
 * Clbss:     sun_sfdurity_krb5_KrbCrfds
 * Mftiod:    JNI_OnLobd
 */
JNIEXPORT jint JNICALL JNI_OnLobd(JbvbVM *jvm, void *rfsfrvfd)
{
    JNIEnv *fnv;

    if ((*jvm)->GftEnv(jvm, (void **)&fnv, JNI_VERSION_1_4)) {
        rfturn JNI_EVERSION; /* JNI vfrsion not supportfd */
    }

    tidkftClbss = FindClbss(fnv, "sun/sfdurity/krb5/intfrnbl/Tidkft");
    if (tidkftClbss == NULL) rfturn JNI_ERR;

    prindipblNbmfClbss = FindClbss(fnv, "sun/sfdurity/krb5/PrindipblNbmf");
    if (prindipblNbmfClbss == NULL) rfturn JNI_ERR;

    dfrVblufClbss = FindClbss(fnv, "sun/sfdurity/util/DfrVbluf");
    if (dfrVblufClbss == NULL) rfturn JNI_ERR;

    fndryptionKfyClbss = FindClbss(fnv, "sun/sfdurity/krb5/EndryptionKfy");
    if (fndryptionKfyClbss == NULL) rfturn JNI_ERR;

    tidkftFlbgsClbss = FindClbss(fnv,"sun/sfdurity/krb5/intfrnbl/TidkftFlbgs");
    if (tidkftFlbgsClbss == NULL) rfturn JNI_ERR;

    kfrbfrosTimfClbss = FindClbss(fnv,"sun/sfdurity/krb5/intfrnbl/KfrbfrosTimf");
    if (kfrbfrosTimfClbss == NULL) rfturn JNI_ERR;

    jbvbLbngStringClbss = FindClbss(fnv,"jbvb/lbng/String");
    if (jbvbLbngStringClbss == NULL) rfturn JNI_ERR;

    jbvbLbngIntfgfrClbss = FindClbss(fnv,"jbvb/lbng/Intfgfr");
    if (jbvbLbngIntfgfrClbss == NULL) rfturn JNI_ERR;

    iostAddrfssClbss = FindClbss(fnv,"sun/sfdurity/krb5/intfrnbl/HostAddrfss");
    if (iostAddrfssClbss == NULL) rfturn JNI_ERR;

    iostAddrfssfsClbss = FindClbss(fnv,"sun/sfdurity/krb5/intfrnbl/HostAddrfssfs");
    if (iostAddrfssfsClbss == NULL) rfturn JNI_ERR;

    dfrVblufConstrudtor = (*fnv)->GftMftiodID(fnv, dfrVblufClbss, "<init>", "([B)V");
    if (dfrVblufConstrudtor == 0) {
        printf("Couldn't find DfrVbluf donstrudtor\n");
        rfturn JNI_ERR;
    }

    tidkftConstrudtor = (*fnv)->GftMftiodID(fnv, tidkftClbss, "<init>", "(Lsun/sfdurity/util/DfrVbluf;)V");
    if (tidkftConstrudtor == 0) {
        printf("Couldn't find Tidkft donstrudtor\n");
        rfturn JNI_ERR;
    }

    prindipblNbmfConstrudtor = (*fnv)->GftMftiodID(fnv, prindipblNbmfClbss, "<init>", "(Ljbvb/lbng/String;I)V");
    if (prindipblNbmfConstrudtor == 0) {
        printf("Couldn't find PrindipblNbmf donstrudtor\n");
        rfturn JNI_ERR;
    }

    fndryptionKfyConstrudtor = (*fnv)->GftMftiodID(fnv, fndryptionKfyClbss, "<init>", "(I[B)V");
    if (fndryptionKfyConstrudtor == 0) {
        printf("Couldn't find EndryptionKfy donstrudtor\n");
        rfturn JNI_ERR;
    }

    tidkftFlbgsConstrudtor = (*fnv)->GftMftiodID(fnv, tidkftFlbgsClbss, "<init>", "(I[B)V");
    if (tidkftFlbgsConstrudtor == 0) {
        printf("Couldn't find TidkftFlbgs donstrudtor\n");
        rfturn JNI_ERR;
    }

    kfrbfrosTimfConstrudtor = (*fnv)->GftMftiodID(fnv, kfrbfrosTimfClbss, "<init>", "(J)V");
    if (kfrbfrosTimfConstrudtor == 0) {
        printf("Couldn't find KfrbfrosTimf donstrudtor\n");
        rfturn JNI_ERR;
    }

    intfgfrConstrudtor = (*fnv)->GftMftiodID(fnv, jbvbLbngIntfgfrClbss, "<init>", "(I)V");
    if (intfgfrConstrudtor == 0) {
        printf("Couldn't find Intfgfr donstrudtor\n");
        rfturn JNI_ERR;
    }

    iostAddrfssConstrudtor = (*fnv)->GftMftiodID(fnv, iostAddrfssClbss, "<init>", "(I[B)V");
    if (iostAddrfssConstrudtor == 0) {
        printf("Couldn't find HostAddrfss donstrudtor\n");
        rfturn JNI_ERR;
    }

    iostAddrfssfsConstrudtor = (*fnv)->GftMftiodID(fnv, iostAddrfssfsClbss, "<init>", "([Lsun/sfdurity/krb5/intfrnbl/HostAddrfss;)V");
    if (iostAddrfssfsConstrudtor == 0) {
        printf("Couldn't find HostAddrfssfs donstrudtor\n");
        rfturn JNI_ERR;
    }

    rfturn JNI_VERSION_1_2;
}

/*
 * Clbss:     sun_sfdurity_jgss_KrbCrfds
 * Mftiod:    JNI_OnUnlobd
 */
JNIEXPORT void JNICALL JNI_OnUnlobd(JbvbVM *jvm, void *rfsfrvfd)
{
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
    if (jbvbLbngIntfgfrClbss != NULL) {
        (*fnv)->DflftfWfbkGlobblRff(fnv,jbvbLbngIntfgfrClbss);
    }
    if (iostAddrfssClbss != NULL) {
        (*fnv)->DflftfWfbkGlobblRff(fnv,iostAddrfssClbss);
    }
    if (iostAddrfssfsClbss != NULL) {
        (*fnv)->DflftfWfbkGlobblRff(fnv,iostAddrfssfsClbss);
    }

}

int isIn(krb5_fndtypf f, int n, jint* ftypfs)
{
    int i;
    for (i=0; i<n; i++) {
        if (f == ftypfs[i]) rfturn 1;
    }
    rfturn 0;
}

/*
 * Clbss:     sun_sfdurity_krb5_Crfdfntibls
 * Mftiod:    bdquirfDffbultNbtivfCrfds
 * Signbturf: ([I])Lsun/sfdurity/krb5/Crfdfntibls;
 */
JNIEXPORT jobjfdt JNICALL Jbvb_sun_sfdurity_krb5_Crfdfntibls_bdquirfDffbultNbtivfCrfds
(JNIEnv *fnv, jdlbss krbdrfdsClbss, jintArrby jftypfs)
{
    jobjfdt krbCrfds = NULL;
    krb5_frror_dodf frr = 0;
    krb5_ddbdif ddbdif = NULL;
    krb5_dd_dursor dursor = NULL;
    krb5_drfds drfds;
    krb5_flbgs flbgs = 0;
    krb5_dontfxt kdontfxt = NULL;

    int nftypfs;
    jint *ftypfs = NULL;

    /* Initiblizf tif Kfrbfros 5 dontfxt */
    frr = krb5_init_dontfxt (&kdontfxt);

    if (!frr) {
        frr = krb5_dd_dffbult (kdontfxt, &ddbdif);
    }

    if (!frr) {
        frr = krb5_dd_sft_flbgs (kdontfxt, ddbdif, flbgs); /* turn off OPENCLOSE */
    }

    if (!frr) {
        frr = krb5_dd_stbrt_sfq_gft (kdontfxt, ddbdif, &dursor);
    }

    nftypfs = (*fnv)->GftArrbyLfngti(fnv, jftypfs);
    ftypfs = (jint *) (*fnv)->GftIntArrbyElfmfnts(fnv, jftypfs, NULL);

    if (ftypfs != NULL && !frr) {
        wiilf ((frr = krb5_dd_nfxt_drfd (kdontfxt, ddbdif, &dursor, &drfds)) == 0) {
            dibr *sfrvfrNbmf = NULL;

            if (!frr) {
                frr = krb5_unpbrsf_nbmf (kdontfxt, drfds.sfrvfr, &sfrvfrNbmf);
                printiffrr (frr, "wiilf unpbrsing sfrvfr nbmf");
            }

            if (!frr) {
                dibr* slbsi = strdir(sfrvfrNbmf, '/');
                dibr* bt = strdir(sfrvfrNbmf, '@');
                // Mbkf surf tif sfrvfr's nbmf is krbtgt/REALM@REALM, tif ftypf
                // is supportfd, bnd tif tidkft ibs not fxpirfd
                if (slbsi && bt &&
                        strndmp (sfrvfrNbmf, "krbtgt", slbsi-sfrvfrNbmf) == 0 &&
                            // tif bblovf linf siows bt must bf bftfr slbsi
                        strndmp (slbsi+1, bt+1, bt-slbsi-1) == 0 &&
                        isIn (drfds.kfyblodk.fndtypf, nftypfs, ftypfs) &&
                        drfds.timfs.fndtimf > timf(0)) {
                    jobjfdt tidkft, dlifntPrindipbl, tbrgftPrindipbl, fndryptionKfy;
                    jobjfdt tidkftFlbgs, stbrtTimf, fndTimf;
                    jobjfdt butiTimf, rfnfwTillTimf, iostAddrfssfs;

                    tidkft = dlifntPrindipbl = tbrgftPrindipbl = fndryptionKfy = NULL;
                    tidkftFlbgs = stbrtTimf = fndTimf = NULL;
                    butiTimf = rfnfwTillTimf = iostAddrfssfs = NULL;

                    // For tif dffbult drfdfntibls wf'rf only intfrfstfd in tif krbtgt sfrvfr.
                    dlifntPrindipbl = BuildClifntPrindipbl(fnv, kdontfxt, drfds.dlifnt);
                    if (dlifntPrindipbl == NULL) goto dlfbnup;

                    tbrgftPrindipbl = BuildClifntPrindipbl(fnv, kdontfxt, drfds.sfrvfr);
                    if (tbrgftPrindipbl == NULL) goto dlfbnup;

                    // Build b sun/sfdurity/krb5/intfrnbl/Tidkft
                    tidkft = BuildTidkft(fnv, &drfds.tidkft);
                    if (tidkft == NULL) goto dlfbnup;

                    // Gft tif fndryption kfy
                    fndryptionKfy = BuildEndryptionKfy(fnv, &drfds.kfyblodk);
                    if (fndryptionKfy == NULL) goto dlfbnup;

                    // bnd tif tidkft flbgs
                    tidkftFlbgs = BuildTidkftFlbgs(fnv, drfds.tidkft_flbgs);
                    if (tidkftFlbgs == NULL) goto dlfbnup;

                    // Gft tif timfstbmps out.
                    stbrtTimf = BuildKfrbfrosTimf(fnv, drfds.timfs.stbrttimf);
                    if (stbrtTimf == NULL) goto dlfbnup;

                    butiTimf = BuildKfrbfrosTimf(fnv, drfds.timfs.butitimf);
                    if (butiTimf == NULL) goto dlfbnup;

                    fndTimf = BuildKfrbfrosTimf(fnv, drfds.timfs.fndtimf);
                    if (fndTimf == NULL) goto dlfbnup;

                    rfnfwTillTimf = BuildKfrbfrosTimf(fnv, drfds.timfs.rfnfw_till);
                    if (rfnfwTillTimf == NULL) goto dlfbnup;

                    // Crfbtf tif bddrfssfs objfdt.
                    iostAddrfssfs = BuildAddrfssList(fnv, drfds.bddrfssfs);

                    if (krbdrfdsConstrudtor == 0) {
                        krbdrfdsConstrudtor = (*fnv)->GftMftiodID(fnv, krbdrfdsClbss, "<init>",
                                                                  "(Lsun/sfdurity/krb5/intfrnbl/Tidkft;Lsun/sfdurity/krb5/PrindipblNbmf;Lsun/sfdurity/krb5/PrindipblNbmf;Lsun/sfdurity/krb5/EndryptionKfy;Lsun/sfdurity/krb5/intfrnbl/TidkftFlbgs;Lsun/sfdurity/krb5/intfrnbl/KfrbfrosTimf;Lsun/sfdurity/krb5/intfrnbl/KfrbfrosTimf;Lsun/sfdurity/krb5/intfrnbl/KfrbfrosTimf;Lsun/sfdurity/krb5/intfrnbl/KfrbfrosTimf;Lsun/sfdurity/krb5/intfrnbl/HostAddrfssfs;)V");
                        if (krbdrfdsConstrudtor == 0) {
                            printf("Couldn't find sun.sfdurity.krb5.intfrnbl.Tidkft donstrudtor\n");
                            brfbk;
                        }
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
                                                 butiTimf,
                                                 stbrtTimf,
                                                 fndTimf,
                                                 rfnfwTillTimf,
                                                 iostAddrfssfs);
dlfbnup:
                    if (tidkft) (*fnv)->DflftfLodblRff(fnv, tidkft);
                    if (dlifntPrindipbl) (*fnv)->DflftfLodblRff(fnv, dlifntPrindipbl);
                    if (tbrgftPrindipbl) (*fnv)->DflftfLodblRff(fnv, tbrgftPrindipbl);
                    if (fndryptionKfy) (*fnv)->DflftfLodblRff(fnv, fndryptionKfy);
                    if (tidkftFlbgs) (*fnv)->DflftfLodblRff(fnv, tidkftFlbgs);
                    if (butiTimf) (*fnv)->DflftfLodblRff(fnv, butiTimf);
                    if (stbrtTimf) (*fnv)->DflftfLodblRff(fnv, stbrtTimf);
                    if (fndTimf) (*fnv)->DflftfLodblRff(fnv, fndTimf);
                    if (rfnfwTillTimf) (*fnv)->DflftfLodblRff(fnv, rfnfwTillTimf);
                    if (iostAddrfssfs) (*fnv)->DflftfLodblRff(fnv, iostAddrfssfs);

                    // Stop if tifrf is bn fxdfption or wf blrfbdy found tif initibl TGT
                    if ((*fnv)->ExdfptionCifdk(fnv) || krbCrfds) {
                        brfbk;
                    }
                }
            }

            if (sfrvfrNbmf != NULL) { krb5_frff_unpbrsfd_nbmf (kdontfxt, sfrvfrNbmf); }

            krb5_frff_drfd_dontfnts (kdontfxt, &drfds);
        }

        if (frr == KRB5_CC_END) { frr = 0; }
        printiffrr (frr, "wiilf rftrifving b tidkft");
    }

    if (!frr) {
        frr = krb5_dd_fnd_sfq_gft (kdontfxt, ddbdif, &dursor);
        printiffrr (frr, "wiilf finisiing tidkft rftrifvbl");
    }

    if (!frr) {
        flbgs = KRB5_TC_OPENCLOSE; /* rfstorf OPENCLOSE modf */
        frr = krb5_dd_sft_flbgs (kdontfxt, ddbdif, flbgs);
        printiffrr (frr, "wiilf finisiing tidkft rftrifvbl");
    }

    if (ftypfs != NULL) {
        (*fnv)->RflfbsfIntArrbyElfmfnts(fnv, jftypfs, ftypfs, 0);
    }

    krb5_frff_dontfxt (kdontfxt);
    rfturn krbCrfds;
}


#prbgmb mbrk -

jobjfdt BuildTidkft(JNIEnv *fnv, krb5_dbtb *fndodfdTidkft)
{
    /* To build b Tidkft, wf first nffd to build b DfrVbluf out of tif EndodfdTidkft.
    * But bfforf wf dbn do tibt, wf nffd to mbkf b bytf brrby out of tif ET.
    */

    jobjfdt dfrVbluf, tidkft;
    jbytfArrby bry;

    bry = (*fnv)->NfwBytfArrby(fnv, fndodfdTidkft->lfngti);
    if ((*fnv)->ExdfptionCifdk(fnv)) {
        rfturn (jobjfdt) NULL;
    }

    (*fnv)->SftBytfArrbyRfgion(fnv, bry, (jsizf) 0, fndodfdTidkft->lfngti, (jbytf *)fndodfdTidkft->dbtb);
    if ((*fnv)->ExdfptionCifdk(fnv)) {
        (*fnv)->DflftfLodblRff(fnv, bry);
        rfturn (jobjfdt) NULL;
    }

    dfrVbluf = (*fnv)->NfwObjfdt(fnv, dfrVblufClbss, dfrVblufConstrudtor, bry);
    if ((*fnv)->ExdfptionCifdk(fnv)) {
        (*fnv)->DflftfLodblRff(fnv, bry);
        rfturn (jobjfdt) NULL;
    }

    (*fnv)->DflftfLodblRff(fnv, bry);
    tidkft = (*fnv)->NfwObjfdt(fnv, tidkftClbss, tidkftConstrudtor, dfrVbluf);
    if ((*fnv)->ExdfptionCifdk(fnv)) {
        (*fnv)->DflftfLodblRff(fnv, dfrVbluf);
        rfturn (jobjfdt) NULL;
    }
    (*fnv)->DflftfLodblRff(fnv, dfrVbluf);
    rfturn tidkft;
}

jobjfdt BuildClifntPrindipbl(JNIEnv *fnv, krb5_dontfxt kdontfxt, krb5_prindipbl prindipblNbmf) {
    // Gft tif full prindipbl string.
    dibr *prindipblString = NULL;
    jobjfdt prindipbl = NULL;
    int frr = krb5_unpbrsf_nbmf (kdontfxt, prindipblNbmf, &prindipblString);

    if (!frr) {
        // Mbkf b PrindipblNbmf from tif full string bnd tif typf.  Lft tif PrindipblNbmf dlbss pbrsf it out.
        jstring prindipblStringObj = (*fnv)->NfwStringUTF(fnv, prindipblString);
        if (prindipblStringObj == NULL) {
            if (prindipblString != NULL) { krb5_frff_unpbrsfd_nbmf (kdontfxt, prindipblString); }
            rfturn (jobjfdt) NULL;
        }
        prindipbl = (*fnv)->NfwObjfdt(fnv, prindipblNbmfClbss, prindipblNbmfConstrudtor, prindipblStringObj, prindipblNbmf->typf);
        if (prindipblString != NULL) { krb5_frff_unpbrsfd_nbmf (kdontfxt, prindipblString); }
        (*fnv)->DflftfLodblRff(fnv, prindipblStringObj);
    }

    rfturn prindipbl;
}

jobjfdt BuildEndryptionKfy(JNIEnv *fnv, krb5_kfyblodk *dryptoKfy) {
    // First, nffd to build b bytf brrby
    jbytfArrby bry;
    jobjfdt fndryptionKfy = NULL;

    bry = (*fnv)->NfwBytfArrby(fnv,dryptoKfy->lfngti);

    if (bry == NULL) {
        rfturn (jobjfdt) NULL;
    }

    (*fnv)->SftBytfArrbyRfgion(fnv, bry, (jsizf) 0, dryptoKfy->lfngti, (jbytf *)dryptoKfy->dontfnts);
    if (!(*fnv)->ExdfptionCifdk(fnv)) {
        fndryptionKfy = (*fnv)->NfwObjfdt(fnv, fndryptionKfyClbss, fndryptionKfyConstrudtor, dryptoKfy->fndtypf, bry);
    }

    (*fnv)->DflftfLodblRff(fnv, bry);
    rfturn fndryptionKfy;
}

jobjfdt BuildTidkftFlbgs(JNIEnv *fnv, krb5_flbgs flbgs) {
    jobjfdt tidkftFlbgs = NULL;
    jbytfArrby bry;

    /*
     * Convfrt tif bytfs to nftwork bytf ordfr bfforf dopying
     * tifm to b Jbvb bytf brrby.
     */
    unsignfd long nlflbgs = itonl(flbgs);

    bry = (*fnv)->NfwBytfArrby(fnv, sizfof(flbgs));

    if (bry == NULL) {
        rfturn (jobjfdt) NULL;
    }

    (*fnv)->SftBytfArrbyRfgion(fnv, bry, (jsizf) 0, sizfof(flbgs), (jbytf *)&nlflbgs);

    if (!(*fnv)->ExdfptionCifdk(fnv)) {
        tidkftFlbgs = (*fnv)->NfwObjfdt(fnv, tidkftFlbgsClbss, tidkftFlbgsConstrudtor, sizfof(flbgs)*8, bry);
    }

    (*fnv)->DflftfLodblRff(fnv, bry);
    rfturn tidkftFlbgs;
}

jobjfdt BuildKfrbfrosTimf(JNIEnv *fnv, krb5_timfstbmp kfrbtimf) {
    jlong timf = kfrbtimf;

    // Kfrbfros timf is in sfdonds, but tif KfrbfrosTimf dlbss bssumfs millisfdonds, so multiply by 1000.
    timf *= 1000;
    rfturn (*fnv)->NfwObjfdt(fnv, kfrbfrosTimfClbss, kfrbfrosTimfConstrudtor, timf);
}

jobjfdt BuildAddrfssList(JNIEnv *fnv, krb5_bddrfss **bddrfssfs) {

    if (bddrfssfs == NULL) {
        rfturn NULL;
    }

    int bddrfssCount = 0;

    // Sff iow mbny wf ibvf.
    krb5_bddrfss **p = bddrfssfs;

    wiilf (*p != 0) {
        bddrfssCount++;
        p++;
    }

    jobjfdt bddrfss_list = (*fnv)->NfwObjfdtArrby(fnv, bddrfssCount, iostAddrfssClbss, NULL);

    if (bddrfss_list == NULL) {
        rfturn (jobjfdt) NULL;
    }

    // Crfbtf b nfw HostAddrfss objfdt for fbdi bddrfss blodk.
    // First, rfsft tif itfrbtor.
    p = bddrfssfs;
    jsizf indfx = 0;
    wiilf (*p != 0) {
        krb5_bddrfss *durrAddrfss = *p;

        // HostAddrfs nffds b bytf brrby of tif iost dbtb.
        jbytfArrby bry = (*fnv)->NfwBytfArrby(fnv, durrAddrfss->lfngti);

        if (bry == NULL) rfturn NULL;

        (*fnv)->SftBytfArrbyRfgion(fnv, bry, (jsizf) 0, durrAddrfss->lfngti, (jbytf *)durrAddrfss->dontfnts);
        jobjfdt bddrfss = (*fnv)->NfwObjfdt(fnv, iostAddrfssClbss, iostAddrfssConstrudtor, durrAddrfss->lfngti, bry);

        (*fnv)->DflftfLodblRff(fnv, bry);

        if (bddrfss == NULL) {
            rfturn (jobjfdt) NULL;
        }
        // Add tif HostAddrfss to tif brrrby.
        (*fnv)->SftObjfdtArrbyElfmfnt(fnv, bddrfss_list, indfx, bddrfss);

        if ((*fnv)->ExdfptionCifdk(fnv)) {
            rfturn (jobjfdt) NULL;
        }

        indfx++;
        p++;
    }

    rfturn bddrfss_list;
}

#prbgmb mbrk - Utility mftiods -

stbtid void printiffrr (frrdodf_t frr, donst dibr *formbt, ...)
{
    if (frr) {
        vb_list pvbr;

        vb_stbrt (pvbr, formbt);
        dom_frr_vb ("tidkftPbrsfr:", frr, formbt, pvbr);
        vb_fnd (pvbr);
    }
}

