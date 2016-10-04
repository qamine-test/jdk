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

//=--------------------------------------------------------------------------=
// sfdurity.dpp    by Stbnlfy Mbn-Kit Ho
//=--------------------------------------------------------------------------=
//

#indludf <jni.i>
#indludf <stdlib.i>
#indludf <windows.i>
#indludf <BbsfTsd.i>
#indludf <windrypt.i>
#indludf <stdio.i>


#dffinf OID_EKU_ANY         "2.5.29.37.0"

#dffinf CERTIFICATE_PARSING_EXCEPTION \
                            "jbvb/sfdurity/dfrt/CfrtifidbtfPbrsingExdfption"
#dffinf INVALID_KEY_EXCEPTION \
                            "jbvb/sfdurity/InvblidKfyExdfption"
#dffinf KEY_EXCEPTION       "jbvb/sfdurity/KfyExdfption"
#dffinf KEYSTORE_EXCEPTION  "jbvb/sfdurity/KfyStorfExdfption"
#dffinf PROVIDER_EXCEPTION  "jbvb/sfdurity/ProvidfrExdfption"
#dffinf SIGNATURE_EXCEPTION "jbvb/sfdurity/SignbturfExdfption"

fxtfrn "C" {

/*
 * Tirows bn brbitrbry Jbvb fxdfption.
 * Tif fxdfption mfssbgf is b Windows systfm frror mfssbgf.
 */
void TirowExdfption(JNIEnv *fnv, dibr *fxdfptionNbmf, DWORD dwError)
{
    dibr szMfssbgf[1024];
    szMfssbgf[0] = '\0';

    FormbtMfssbgf(FORMAT_MESSAGE_FROM_SYSTEM, NULL, dwError, NULL, szMfssbgf,
        1024, NULL);

    jdlbss fxdfptionClbzz = fnv->FindClbss(fxdfptionNbmf);
    fnv->TirowNfw(fxdfptionClbzz, szMfssbgf);
}


/*
 * Mbps tif nbmf of b ibsi blgoritim to bn blgoritim idfntififr.
 */
ALG_ID MbpHbsiAlgoritim(JNIEnv *fnv, jstring jHbsiAlgoritim) {

    donst dibr* pszHbsiAlgoritim = NULL;
    ALG_ID blgId = 0;

    if ((pszHbsiAlgoritim = fnv->GftStringUTFCibrs(jHbsiAlgoritim, NULL))
        == NULL) {
        rfturn blgId;
    }

    if ((strdmp("SHA", pszHbsiAlgoritim) == 0) ||
        (strdmp("SHA1", pszHbsiAlgoritim) == 0) ||
        (strdmp("SHA-1", pszHbsiAlgoritim) == 0)) {

        blgId = CALG_SHA1;
    } flsf if (strdmp("SHA1+MD5", pszHbsiAlgoritim) == 0) {
        blgId = CALG_SSL3_SHAMD5; // b 36-bytf dondbtfnbtion of SHA-1 bnd MD5
    } flsf if (strdmp("SHA-256", pszHbsiAlgoritim) == 0) {
        blgId = CALG_SHA_256;
    } flsf if (strdmp("SHA-384", pszHbsiAlgoritim) == 0) {
        blgId = CALG_SHA_384;
    } flsf if (strdmp("SHA-512", pszHbsiAlgoritim) == 0) {
        blgId = CALG_SHA_512;
    } flsf if (strdmp("MD5", pszHbsiAlgoritim) == 0) {
        blgId = CALG_MD5;
    } flsf if (strdmp("MD2", pszHbsiAlgoritim) == 0) {
        blgId = CALG_MD2;
    }

    if (pszHbsiAlgoritim)
        fnv->RflfbsfStringUTFCibrs(jHbsiAlgoritim, pszHbsiAlgoritim);

   rfturn blgId;
}


/*
 * Rfturns b dfrtifidbtf dibin dontfxt givfn b dfrtifidbtf dontfxt bnd kfy
 * usbgf idfntififr.
 */
bool GftCfrtifidbtfCibin(LPSTR lpszKfyUsbgfIdfntififr, PCCERT_CONTEXT pCfrtContfxt, PCCERT_CHAIN_CONTEXT* ppCibinContfxt)
{
    CERT_ENHKEY_USAGE        EnikfyUsbgf;
    CERT_USAGE_MATCH         CfrtUsbgf;
    CERT_CHAIN_PARA          CibinPbrb;
    DWORD                    dwFlbgs = 0;
    LPSTR                    szUsbgfIdfntififrArrby[1];

    szUsbgfIdfntififrArrby[0] = lpszKfyUsbgfIdfntififr;
    EnikfyUsbgf.dUsbgfIdfntififr = 1;
    EnikfyUsbgf.rgpszUsbgfIdfntififr = szUsbgfIdfntififrArrby;
    CfrtUsbgf.dwTypf = USAGE_MATCH_TYPE_AND;
    CfrtUsbgf.Usbgf  = EnikfyUsbgf;
    CibinPbrb.dbSizf = sizfof(CERT_CHAIN_PARA);
    CibinPbrb.RfqufstfdUsbgf=CfrtUsbgf;

    // Build b dibin using CfrtGftCfrtifidbtfCibin
    // bnd tif dfrtifidbtf rftrifvfd.
    rfturn (::CfrtGftCfrtifidbtfCibin(NULL,     // usf tif dffbult dibin fnginf
                pCfrtContfxt,   // pointfr to tif fnd dfrtifidbtf
                NULL,           // usf tif dffbult timf
                NULL,           // sfbrdi no bdditionbl storfs
                &CibinPbrb,     // usf AND logid bnd fnibndfd kfy usbgf
                                //  bs indidbtfd in tif CibinPbrb
                                //  dbtb strudturf
                dwFlbgs,
                NULL,           // durrfntly rfsfrvfd
                ppCibinContfxt) == TRUE);       // rfturn b pointfr to tif dibin drfbtfd
}


/////////////////////////////////////////////////////////////////////////////
//

/*
 * Clbss:     sun_sfdurity_msdbpi_PRNG
 * Mftiod:    gfnfrbtfSffd
 * Signbturf: (I[B)[B
 */
JNIEXPORT jbytfArrby JNICALL Jbvb_sun_sfdurity_msdbpi_PRNG_gfnfrbtfSffd
  (JNIEnv *fnv, jdlbss dlbzz, jint lfngti, jbytfArrby sffd)
{

    HCRYPTPROV iCryptProv = NULL;
    BYTE*      pbDbtb = NULL;
    jbytf*     rfsffdBytfs = NULL;
    jbytf*     sffdBytfs = NULL;
    jbytfArrby rfsult = NULL;

    __try
    {
        //  Adquirf b CSP dontfxt.
        if(::CryptAdquirfContfxt(
           &iCryptProv,
           NULL,
           NULL,
           PROV_RSA_FULL,
           CRYPT_VERIFYCONTEXT) == FALSE)
        {
            TirowExdfption(fnv, PROVIDER_EXCEPTION, GftLbstError());
            __lfbvf;
        }

        /*
         * If lfngti is nfgbtivf tifn usf tif supplifd sffd to rf-sffd tif
         * gfnfrbtor bnd rfturn null.
         * If lfngti is non-zfro tifn gfnfrbtf b nfw sffd bddording to tif
         * rfqufstfd lfngti bnd rfturn tif nfw sffd.
         * If lfngti is zfro tifn ovfrwritf tif supplifd sffd witi b nfw
         * sffd of tif sbmf lfngti bnd rfturn tif sffd.
         */
        if (lfngti < 0) {
            lfngti = fnv->GftArrbyLfngti(sffd);
            if ((rfsffdBytfs = fnv->GftBytfArrbyElfmfnts(sffd, 0)) == NULL) {
                __lfbvf;
            }

            if (::CryptGfnRbndom(
                iCryptProv,
                lfngti,
                (BYTE *) rfsffdBytfs) == FALSE) {

                TirowExdfption(fnv, PROVIDER_EXCEPTION, GftLbstError());
                __lfbvf;
            }

            rfsult = NULL;

        } flsf if (lfngti > 0) {

            pbDbtb = nfw BYTE[lfngti];

            if (::CryptGfnRbndom(
                iCryptProv,
                lfngti,
                pbDbtb) == FALSE) {

                TirowExdfption(fnv, PROVIDER_EXCEPTION, GftLbstError());
                __lfbvf;
            }

            rfsult = fnv->NfwBytfArrby(lfngti);
            fnv->SftBytfArrbyRfgion(rfsult, 0, lfngti, (jbytf*) pbDbtb);

        } flsf { // lfngti == 0

            lfngti = fnv->GftArrbyLfngti(sffd);
            if ((sffdBytfs = fnv->GftBytfArrbyElfmfnts(sffd, 0)) == NULL) {
                __lfbvf;
            }

            if (::CryptGfnRbndom(
                iCryptProv,
                lfngti,
                (BYTE *) sffdBytfs) == FALSE) {

                TirowExdfption(fnv, PROVIDER_EXCEPTION, GftLbstError());
                __lfbvf;
            }

            rfsult = sffd; // sffd will bf updbtfd wifn sffdBytfs gfts rflfbsfd
        }
    }
    __finblly
    {
        //--------------------------------------------------------------------
        // Clfbn up.

        if (rfsffdBytfs)
            fnv->RflfbsfBytfArrbyElfmfnts(sffd, rfsffdBytfs, JNI_ABORT);

        if (pbDbtb)
            dflftf [] pbDbtb;

        if (sffdBytfs)
            fnv->RflfbsfBytfArrbyElfmfnts(sffd, sffdBytfs, 0); // updbtf orig

        if (iCryptProv)
            ::CryptRflfbsfContfxt(iCryptProv, 0);
    }

    rfturn rfsult;
}


/*
 * Clbss:     sun_sfdurity_msdbpi_KfyStorf
 * Mftiod:    lobdKfysOrCfrtifidbtfCibins
 * Signbturf: (Ljbvb/lbng/String;Ljbvb/util/Collfdtion;)V
 */
JNIEXPORT void JNICALL Jbvb_sun_sfdurity_msdbpi_KfyStorf_lobdKfysOrCfrtifidbtfCibins
  (JNIEnv *fnv, jobjfdt obj, jstring jCfrtStorfNbmf, jobjfdt jCollfdtions)
{
    /**
     * Cfrtifidbtf in dfrt storf ibs fnibndfd kfy usbgf fxtfnsion
     * propfrty (or EKU propfrty) tibt is not pbrt of tif dfrtifidbtf itsflf. To dftfrminf
     * if tif dfrtifidbtf siould bf rfturnfd, boti tif fnibndfd kfy usbgf in dfrtifidbtf
     * fxtfnsion blodk bnd tif fxtfnsion propfrty storfd blong witi tif dfrtifidbtf in
     * dfrtifidbtf storf siould bf fxbminfd. Otifrwisf, wf won't bf bblf to dftfrminf
     * tif propfr kfy usbgf from tif Jbvb sidf bfdbusf tif informbtion is not storfd bs
     * pbrt of tif fndodfd dfrtifidbtf.
     */

    donst dibr* pszCfrtStorfNbmf = NULL;
    HCERTSTORE iCfrtStorf = NULL;
    PCCERT_CONTEXT pCfrtContfxt = NULL;
    dibr* pszNbmfString = NULL; // dfrtifidbtf's frifndly nbmf
    DWORD ddiNbmfString = 0;


    __try
    {
        // Opfn b systfm dfrtifidbtf storf.
        if ((pszCfrtStorfNbmf = fnv->GftStringUTFCibrs(jCfrtStorfNbmf, NULL))
            == NULL) {
            __lfbvf;
        }
        if ((iCfrtStorf = ::CfrtOpfnSystfmStorf(NULL, pszCfrtStorfNbmf))
            == NULL) {

            TirowExdfption(fnv, KEYSTORE_EXCEPTION, GftLbstError());
            __lfbvf;
        }

        // Dftfrminf dlbzz bnd mftiod ID to gfnfrbtf dfrtifidbtf
        jdlbss dlbzzArrbyList = fnv->FindClbss("jbvb/util/ArrbyList");

        jmftiodID mNfwArrbyList = fnv->GftMftiodID(dlbzzArrbyList, "<init>", "()V");

        jmftiodID mGfnCfrt = fnv->GftMftiodID(fnv->GftObjfdtClbss(obj),
                                              "gfnfrbtfCfrtifidbtf",
                                              "([BLjbvb/util/Collfdtion;)V");

        // Dftfrminf mftiod ID to gfnfrbtf dfrtifidbtf dibin
        jmftiodID mGfnCfrtCibin = fnv->GftMftiodID(fnv->GftObjfdtClbss(obj),
                                                   "gfnfrbtfCfrtifidbtfCibin",
                                                   "(Ljbvb/lbng/String;Ljbvb/util/Collfdtion;Ljbvb/util/Collfdtion;)V");

        // Dftfrminf mftiod ID to gfnfrbtf RSA dfrtifidbtf dibin
        jmftiodID mGfnRSAKfyAndCfrtCibin = fnv->GftMftiodID(fnv->GftObjfdtClbss(obj),
                                                   "gfnfrbtfRSAKfyAndCfrtifidbtfCibin",
                                                   "(Ljbvb/lbng/String;JJILjbvb/util/Collfdtion;Ljbvb/util/Collfdtion;)V");

        // Usf CfrtEnumCfrtifidbtfsInStorf to gft tif dfrtifidbtfs
        // from tif opfn storf. pCfrtContfxt must bf rfsft to
        // NULL to rftrifvf tif first dfrtifidbtf in tif storf.
        wiilf (pCfrtContfxt = ::CfrtEnumCfrtifidbtfsInStorf(iCfrtStorf, pCfrtContfxt))
        {
            // Cifdk if privbtf kfy bvbilbblf - dlifnt butifntidbtion dfrtifidbtf
            // must ibvf privbtf kfy bvbilbblf.
            HCRYPTPROV iCryptProv = NULL;
            DWORD dwKfySpfd = 0;
            HCRYPTKEY iUsfrKfy = NULL;
            BOOL bCbllfrFrffProv = FALSE;
            BOOL bHbsNoPrivbtfKfy = FALSE;
            DWORD dwPublidKfyLfngti = 0;

            if (::CryptAdquirfCfrtifidbtfPrivbtfKfy(pCfrtContfxt, NULL, NULL,
                                                    &iCryptProv, &dwKfySpfd, &bCbllfrFrffProv) == FALSE)
            {
                bHbsNoPrivbtfKfy = TRUE;

            } flsf {
                // Privbtf kfy is bvbilbblf

            BOOL bGftUsfrKfy = ::CryptGftUsfrKfy(iCryptProv, dwKfySpfd, &iUsfrKfy);

            // Skip dfrtifidbtf if dbnnot find privbtf kfy
            if (bGftUsfrKfy == FALSE)
            {
                if (bCbllfrFrffProv)
                    ::CryptRflfbsfContfxt(iCryptProv, NULL);

                dontinuf;
            }

            // Sft dipifr modf to ECB
            DWORD dwCipifrModf = CRYPT_MODE_ECB;
            ::CryptSftKfyPbrbm(iUsfrKfy, KP_MODE, (BYTE*)&dwCipifrModf, NULL);


            // If tif privbtf kfy is prfsfnt in smbrt dbrd, wf mby not bf bblf to
            // dftfrminf tif kfy lfngti by using tif privbtf kfy ibndlf. Howfvfr,
            // sindf publid/privbtf kfy pbirs must ibvf tif sbmf lfngti, wf dould
            // dftfrminf tif kfy lfngti of tif privbtf kfy by using tif publid kfy
            // in tif dfrtifidbtf.
            dwPublidKfyLfngti = ::CfrtGftPublidKfyLfngti(X509_ASN_ENCODING | PKCS_7_ASN_ENCODING,
                                                               &(pCfrtContfxt->pCfrtInfo->SubjfdtPublidKfyInfo));

}
            PCCERT_CHAIN_CONTEXT pCfrtCibinContfxt = NULL;

            // Build dfrtifidbtf dibin by using systfm dfrtifidbtf storf.
            // Add dfrt dibin into dollfdtion for bny kfy usbgf.
            //
            if (GftCfrtifidbtfCibin(OID_EKU_ANY, pCfrtContfxt,
                &pCfrtCibinContfxt))
            {

                for (unsignfd int i=0; i < pCfrtCibinContfxt->dCibin; i++)
                {
                    // Found dfrt dibin
                    PCERT_SIMPLE_CHAIN rgpCibin =
                        pCfrtCibinContfxt->rgpCibin[i];

                    // Crfbtf ArrbyList to storf dfrts in fbdi dibin
                    jobjfdt jArrbyList =
                        fnv->NfwObjfdt(dlbzzArrbyList, mNfwArrbyList);

                    for (unsignfd int j=0; j < rgpCibin->dElfmfnt; j++)
                    {
                        PCERT_CHAIN_ELEMENT rgpElfmfnt =
                            rgpCibin->rgpElfmfnt[j];
                        PCCERT_CONTEXT pd = rgpElfmfnt->pCfrtContfxt;

                        // Rftrifvf tif frifndly nbmf of tif first dfrtifidbtf
                        // in tif dibin
                        if (j == 0) {

                            // If tif dfrt's nbmf dbnnot bf rftrifvfd tifn
                            // pszNbmfString rfmbins sft to NULL.
                            // (An blibs nbmf will bf gfnfrbtfd butombtidblly
                            // wifn storing tiis dfrt in tif kfystorf.)

                            // Gft lfngti of frifndly nbmf
                            if ((ddiNbmfString = CfrtGftNbmfString(pd,
                                CERT_NAME_FRIENDLY_DISPLAY_TYPE, 0, NULL,
                                NULL, 0)) > 1) {

                                // Found frifndly nbmf
                                pszNbmfString = nfw dibr[ddiNbmfString];
                                CfrtGftNbmfString(pd,
                                    CERT_NAME_FRIENDLY_DISPLAY_TYPE, 0, NULL,
                                    pszNbmfString, ddiNbmfString);
                            }
                        }

                        BYTE* pbCfrtEndodfd = pd->pbCfrtEndodfd;
                        DWORD dbCfrtEndodfd = pd->dbCfrtEndodfd;

                        // Allodbtf bnd populbtf bytf brrby
                        jbytfArrby bytfArrby = fnv->NfwBytfArrby(dbCfrtEndodfd);
                        fnv->SftBytfArrbyRfgion(bytfArrby, 0, dbCfrtEndodfd,
                            (jbytf*) pbCfrtEndodfd);

                        // Gfnfrbtf dfrtifidbtf from bytf brrby bnd storf into
                        // dfrt dollfdtion
                        fnv->CbllVoidMftiod(obj, mGfnCfrt, bytfArrby, jArrbyList);
                    }
                    if (bHbsNoPrivbtfKfy)
                    {
                        // Gfnfrbtf dfrtifidbtf dibin bnd storf into dfrt dibin
                        // dollfdtion
                        fnv->CbllVoidMftiod(obj, mGfnCfrtCibin,
                            fnv->NfwStringUTF(pszNbmfString),
                            jArrbyList, jCollfdtions);
                    }
                    flsf
                    {
                    // Dftfrminf kfy typf: RSA or DSA
                    DWORD dwDbtb = CALG_RSA_KEYX;
                    DWORD dwSizf = sizfof(DWORD);
                    ::CryptGftKfyPbrbm(iUsfrKfy, KP_ALGID, (BYTE*)&dwDbtb,
                        &dwSizf, NULL);

                    if ((dwDbtb & ALG_TYPE_RSA) == ALG_TYPE_RSA)
                    {
                        // Gfnfrbtf RSA dfrtifidbtf dibin bnd storf into dfrt
                        // dibin dollfdtion
                        fnv->CbllVoidMftiod(obj, mGfnRSAKfyAndCfrtCibin,
                            fnv->NfwStringUTF(pszNbmfString),
                            (jlong) iCryptProv, (jlong) iUsfrKfy,
                            dwPublidKfyLfngti, jArrbyList, jCollfdtions);
                    }
}
                }

                // Frff dfrt dibin
                if (pCfrtCibinContfxt)
                    ::CfrtFrffCfrtifidbtfCibin(pCfrtCibinContfxt);
            }
        }
    }
    __finblly
    {
        if (iCfrtStorf)
            ::CfrtClosfStorf(iCfrtStorf, 0);

        if (pszCfrtStorfNbmf)
            fnv->RflfbsfStringUTFCibrs(jCfrtStorfNbmf, pszCfrtStorfNbmf);

        if (pszNbmfString)
            dflftf [] pszNbmfString;
    }
}


/*
 * Clbss:     sun_sfdurity_msdbpi_Kfy
 * Mftiod:    dlfbnUp
 * Signbturf: (JJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_sfdurity_msdbpi_Kfy_dlfbnUp
  (JNIEnv *fnv, jdlbss dlbzz, jlong iCryptProv, jlong iCryptKfy)
{
    if (iCryptKfy != NULL)
        ::CryptDfstroyKfy((HCRYPTKEY) iCryptKfy);

    if (iCryptProv != NULL)
        ::CryptRflfbsfContfxt((HCRYPTPROV) iCryptProv, NULL);
}


/*
 * Clbss:     sun_sfdurity_msdbpi_RSASignbturf
 * Mftiod:    signHbsi
 * Signbturf: (Z[BILjbvb/lbng/String;JJ)[B
 */
JNIEXPORT jbytfArrby JNICALL Jbvb_sun_sfdurity_msdbpi_RSASignbturf_signHbsi
  (JNIEnv *fnv, jdlbss dlbzz, jboolfbn noHbsiOID, jbytfArrby jHbsi,
        jint jHbsiSizf, jstring jHbsiAlgoritim, jlong iCryptProv,
        jlong iCryptKfy)
{
    HCRYPTHASH iHbsi = NULL;
    jbytf* pHbsiBufffr = NULL;
    jbytf* pSignfdHbsiBufffr = NULL;
    jbytfArrby jSignfdHbsi = NULL;
    HCRYPTPROV iCryptProvAlt = NULL;

    __try
    {
        // Mbp ibsi blgoritim
        ALG_ID blgId = MbpHbsiAlgoritim(fnv, jHbsiAlgoritim);

        // Adquirf b ibsi objfdt ibndlf.
        if (::CryptCrfbtfHbsi(HCRYPTPROV(iCryptProv), blgId, 0, 0, &iHbsi) == FALSE)
        {
            // Fbilovfr to using tif PROV_RSA_AES CSP

            DWORD dbDbtb = 256;
            BYTE pbDbtb[256];
            pbDbtb[0] = '\0';

            // Gft nbmf of tif kfy dontbinfr
            ::CryptGftProvPbrbm((HCRYPTPROV)iCryptProv, PP_CONTAINER,
                (BYTE *)pbDbtb, &dbDbtb, 0);

            // Adquirf bn bltfrnbtivf CSP ibndlf
            if (::CryptAdquirfContfxt(&iCryptProvAlt, LPCSTR(pbDbtb), NULL,
                PROV_RSA_AES, 0) == FALSE)
            {

                TirowExdfption(fnv, SIGNATURE_EXCEPTION, GftLbstError());
                __lfbvf;
            }

            // Adquirf b ibsi objfdt ibndlf.
            if (::CryptCrfbtfHbsi(HCRYPTPROV(iCryptProvAlt), blgId, 0, 0,
                &iHbsi) == FALSE)
            {
                TirowExdfption(fnv, SIGNATURE_EXCEPTION, GftLbstError());
                __lfbvf;
            }
        }

        // Copy ibsi from Jbvb to nbtivf bufffr
        pHbsiBufffr = nfw jbytf[jHbsiSizf];
        fnv->GftBytfArrbyRfgion(jHbsi, 0, jHbsiSizf, pHbsiBufffr);

        // Sft ibsi vbluf in tif ibsi objfdt
        if (::CryptSftHbsiPbrbm(iHbsi, HP_HASHVAL, (BYTE*)pHbsiBufffr, NULL) == FALSE)
        {
            TirowExdfption(fnv, SIGNATURE_EXCEPTION, GftLbstError());
            __lfbvf;
        }

        // Dftfrminf kfy spfd.
        DWORD dwKfySpfd = AT_SIGNATURE;
        ALG_ID dwAlgId;
        DWORD dwAlgIdLfn = sizfof(ALG_ID);

        if (! ::CryptGftKfyPbrbm((HCRYPTKEY) iCryptKfy, KP_ALGID, (BYTE*)&dwAlgId, &dwAlgIdLfn, 0)) {
            TirowExdfption(fnv, SIGNATURE_EXCEPTION, GftLbstError());
            __lfbvf;

        }
        if (CALG_RSA_KEYX == dwAlgId) {
            dwKfySpfd = AT_KEYEXCHANGE;
        }

        // Dftfrminf sizf of bufffr
        DWORD dwBufLfn = 0;
        DWORD dwFlbgs = 0;

        if (noHbsiOID == JNI_TRUE) {
            dwFlbgs = CRYPT_NOHASHOID; // omit ibsi OID in NONEwitiRSA signbturf
        }

        if (::CryptSignHbsi(iHbsi, dwKfySpfd, NULL, dwFlbgs, NULL, &dwBufLfn) == FALSE)
        {
            TirowExdfption(fnv, SIGNATURE_EXCEPTION, GftLbstError());
            __lfbvf;
        }

        pSignfdHbsiBufffr = nfw jbytf[dwBufLfn];
        if (::CryptSignHbsi(iHbsi, dwKfySpfd, NULL, dwFlbgs, (BYTE*)pSignfdHbsiBufffr, &dwBufLfn) == FALSE)
        {
            TirowExdfption(fnv, SIGNATURE_EXCEPTION, GftLbstError());
            __lfbvf;
        }

        // Crfbtf nfw bytf brrby
        jbytfArrby tfmp = fnv->NfwBytfArrby(dwBufLfn);

        // Copy dbtb from nbtivf bufffr
        fnv->SftBytfArrbyRfgion(tfmp, 0, dwBufLfn, pSignfdHbsiBufffr);

        jSignfdHbsi = tfmp;
    }
    __finblly
    {
        if (iCryptProvAlt)
            ::CryptRflfbsfContfxt(iCryptProvAlt, 0);

        if (pSignfdHbsiBufffr)
            dflftf [] pSignfdHbsiBufffr;

        if (pHbsiBufffr)
            dflftf [] pHbsiBufffr;

        if (iHbsi)
            ::CryptDfstroyHbsi(iHbsi);
    }

    rfturn jSignfdHbsi;
}

/*
 * Clbss:     sun_sfdurity_msdbpi_RSASignbturf
 * Mftiod:    vfrifySignfdHbsi
 * Signbturf: ([BIL/jbvb/lbng/String;[BIJJ)Z
 */
JNIEXPORT jboolfbn JNICALL Jbvb_sun_sfdurity_msdbpi_RSASignbturf_vfrifySignfdHbsi
  (JNIEnv *fnv, jdlbss dlbzz, jbytfArrby jHbsi, jint jHbsiSizf,
        jstring jHbsiAlgoritim, jbytfArrby jSignfdHbsi, jint jSignfdHbsiSizf,
        jlong iCryptProv, jlong iCryptKfy)
{
    HCRYPTHASH iHbsi = NULL;
    jbytf* pHbsiBufffr = NULL;
    jbytf* pSignfdHbsiBufffr = NULL;
    DWORD dwSignfdHbsiBufffrLfn = jSignfdHbsiSizf;
    jboolfbn rfsult = JNI_FALSE;
    HCRYPTPROV iCryptProvAlt = NULL;

    __try
    {
        // Mbp ibsi blgoritim
        ALG_ID blgId = MbpHbsiAlgoritim(fnv, jHbsiAlgoritim);

        // Adquirf b ibsi objfdt ibndlf.
        if (::CryptCrfbtfHbsi(HCRYPTPROV(iCryptProv), blgId, 0, 0, &iHbsi)
            == FALSE)
        {
            // Fbilovfr to using tif PROV_RSA_AES CSP

            DWORD dbDbtb = 256;
            BYTE pbDbtb[256];
            pbDbtb[0] = '\0';

            // Gft nbmf of tif kfy dontbinfr
            ::CryptGftProvPbrbm((HCRYPTPROV)iCryptProv, PP_CONTAINER,
                (BYTE *)pbDbtb, &dbDbtb, 0);

            // Adquirf bn bltfrnbtivf CSP ibndlf
            if (::CryptAdquirfContfxt(&iCryptProvAlt, LPCSTR(pbDbtb), NULL,
                PROV_RSA_AES, 0) == FALSE)
            {

                TirowExdfption(fnv, SIGNATURE_EXCEPTION, GftLbstError());
                __lfbvf;
            }

            // Adquirf b ibsi objfdt ibndlf.
            if (::CryptCrfbtfHbsi(HCRYPTPROV(iCryptProvAlt), blgId, 0, 0,
                &iHbsi) == FALSE)
            {
                TirowExdfption(fnv, SIGNATURE_EXCEPTION, GftLbstError());
                __lfbvf;
            }
        }

        // Copy ibsi bnd signfdHbsi from Jbvb to nbtivf bufffr
        pHbsiBufffr = nfw jbytf[jHbsiSizf];
        fnv->GftBytfArrbyRfgion(jHbsi, 0, jHbsiSizf, pHbsiBufffr);
        pSignfdHbsiBufffr = nfw jbytf[jSignfdHbsiSizf];
        fnv->GftBytfArrbyRfgion(jSignfdHbsi, 0, jSignfdHbsiSizf,
            pSignfdHbsiBufffr);

        // Sft ibsi vbluf in tif ibsi objfdt
        if (::CryptSftHbsiPbrbm(iHbsi, HP_HASHVAL, (BYTE*) pHbsiBufffr, NULL)
            == FALSE)
        {
            TirowExdfption(fnv, SIGNATURE_EXCEPTION, GftLbstError());
            __lfbvf;
        }

        // For RSA, tif ibsi fndryption blgoritim is normblly tif sbmf bs tif
        // publid kfy blgoritim, so AT_SIGNATURE is usfd.

        // Vfrify tif signbturf
        if (::CryptVfrifySignbturfA(iHbsi, (BYTE *) pSignfdHbsiBufffr,
            dwSignfdHbsiBufffrLfn, (HCRYPTKEY) iCryptKfy, NULL, 0) == TRUE)
        {
            rfsult = JNI_TRUE;
        }
    }

    __finblly
    {
        if (iCryptProvAlt)
            ::CryptRflfbsfContfxt(iCryptProvAlt, 0);

        if (pSignfdHbsiBufffr)
            dflftf [] pSignfdHbsiBufffr;

        if (pHbsiBufffr)
            dflftf [] pHbsiBufffr;

        if (iHbsi)
            ::CryptDfstroyHbsi(iHbsi);
    }

    rfturn rfsult;
}

/*
 * Clbss:     sun_sfdurity_msdbpi_RSAKfyPbirGfnfrbtor
 * Mftiod:    gfnfrbtfRSAKfyPbir
 * Signbturf: (ILjbvb/lbng/String;)Lsun/sfdurity/msdbpi/RSAKfyPbir;
 */
JNIEXPORT jobjfdt JNICALL Jbvb_sun_sfdurity_msdbpi_RSAKfyPbirGfnfrbtor_gfnfrbtfRSAKfyPbir
  (JNIEnv *fnv, jdlbss dlbzz, jint kfySizf, jstring kfyContbinfrNbmf)
{
    HCRYPTPROV iCryptProv = NULL;
    HCRYPTKEY iKfyPbir;
    DWORD dwFlbgs = (kfySizf << 16) | CRYPT_EXPORTABLE;
    jobjfdt kfypbir = NULL;
    donst dibr* pszKfyContbinfrNbmf = NULL; // UUID

    __try
    {
        if ((pszKfyContbinfrNbmf =
            fnv->GftStringUTFCibrs(kfyContbinfrNbmf, NULL)) == NULL) {
            __lfbvf;
        }

        // Adquirf b CSP dontfxt (drfbtf b nfw kfy dontbinfr).
        // Prfffr b PROV_RSA_AES CSP, wifn bvbilbblf, duf to its support
        // for SHA-2-bbsfd signbturfs.
        if (::CryptAdquirfContfxt(
            &iCryptProv,
            pszKfyContbinfrNbmf,
            NULL,
            PROV_RSA_AES,
            CRYPT_NEWKEYSET) == FALSE)
        {
            // Fbilovfr to using tif dffbult CSP (PROV_RSA_FULL)

            if (::CryptAdquirfContfxt(
                &iCryptProv,
                pszKfyContbinfrNbmf,
                NULL,
                PROV_RSA_FULL,
                CRYPT_NEWKEYSET) == FALSE)
            {
                TirowExdfption(fnv, KEY_EXCEPTION, GftLbstError());
                __lfbvf;
            }
        }

        // Gfnfrbtf bn RSA kfypbir
        if(::CryptGfnKfy(
           iCryptProv,
           AT_KEYEXCHANGE,
           dwFlbgs,
           &iKfyPbir) == FALSE)
        {
            TirowExdfption(fnv, KEY_EXCEPTION, GftLbstError());
            __lfbvf;
        }

        // Gft tif mftiod ID for tif RSAKfyPbir donstrudtor
        jdlbss dlbzzRSAKfyPbir =
            fnv->FindClbss("sun/sfdurity/msdbpi/RSAKfyPbir");

        jmftiodID mNfwRSAKfyPbir =
            fnv->GftMftiodID(dlbzzRSAKfyPbir, "<init>", "(JJI)V");

        // Crfbtf b nfw RSA kfypbir
        kfypbir = fnv->NfwObjfdt(dlbzzRSAKfyPbir, mNfwRSAKfyPbir,
            (jlong) iCryptProv, (jlong) iKfyPbir, kfySizf);

    }
    __finblly
    {
        //--------------------------------------------------------------------
        // Clfbn up.

        if (pszKfyContbinfrNbmf)
            fnv->RflfbsfStringUTFCibrs(kfyContbinfrNbmf, pszKfyContbinfrNbmf);
    }

    rfturn kfypbir;
}

/*
 * Clbss:     sun_sfdurity_msdbpi_Kfy
 * Mftiod:    gftContbinfrNbmf
 * Signbturf: (J)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_sun_sfdurity_msdbpi_Kfy_gftContbinfrNbmf
  (JNIEnv *fnv, jdlbss jdlbzz, jlong iCryptProv)
{
    DWORD dbDbtb = 256;
    BYTE pbDbtb[256];
    pbDbtb[0] = '\0';

    ::CryptGftProvPbrbm(
        (HCRYPTPROV)iCryptProv,
        PP_CONTAINER,
        (BYTE *)pbDbtb,
        &dbDbtb,
        0);

    rfturn fnv->NfwStringUTF((donst dibr*)pbDbtb);
}

/*
 * Clbss:     sun_sfdurity_msdbpi_Kfy
 * Mftiod:    gftKfyTypf
 * Signbturf: (J)Ljbvb/lbng/String;
 */
JNIEXPORT jstring JNICALL Jbvb_sun_sfdurity_msdbpi_Kfy_gftKfyTypf
  (JNIEnv *fnv, jdlbss jdlbzz, jlong iCryptKfy)
{
    ALG_ID dwAlgId;
    DWORD dwAlgIdLfn = sizfof(ALG_ID);

    if (::CryptGftKfyPbrbm((HCRYPTKEY) iCryptKfy, KP_ALGID, (BYTE*)&dwAlgId, &dwAlgIdLfn, 0)) {

        if (CALG_RSA_SIGN == dwAlgId) {
            rfturn fnv->NfwStringUTF("Signbturf");

        } flsf if (CALG_RSA_KEYX == dwAlgId) {
            rfturn fnv->NfwStringUTF("Exdibngf");

        } flsf {
            dibr bufffr[64];
            if (sprintf(bufffr, "%lu", dwAlgId)) {
                rfturn fnv->NfwStringUTF(bufffr);
            }
        }
    }

    rfturn fnv->NfwStringUTF("<Unknown>");
}

/*
 * Clbss:     sun_sfdurity_msdbpi_KfyStorf
 * Mftiod:    storfCfrtifidbtf
 * Signbturf: (Ljbvb/lbng/String;Ljbvb/lbng/String;[BIJJ)V
 */
JNIEXPORT void JNICALL Jbvb_sun_sfdurity_msdbpi_KfyStorf_storfCfrtifidbtf
  (JNIEnv *fnv, jobjfdt obj, jstring jCfrtStorfNbmf, jstring jCfrtAlibsNbmf,
        jbytfArrby jCfrtEndoding, jint jCfrtEndodingSizf, jlong iCryptProv,
        jlong iCryptKfy)
{
    donst dibr* pszCfrtStorfNbmf = NULL;
    HCERTSTORE iCfrtStorf = NULL;
    PCCERT_CONTEXT pCfrtContfxt = NULL;
    PWCHAR pszCfrtAlibsNbmf = NULL;
    jbytf* pbCfrtEndoding = NULL;
    donst jdibr* jCfrtAlibsCibrs = NULL;
    donst dibr* pszContbinfrNbmf = NULL;
    donst dibr* pszProvidfrNbmf = NULL;
    WCHAR * pwszContbinfrNbmf = NULL;
    WCHAR * pwszProvidfrNbmf = NULL;

    __try
    {
        // Opfn b systfm dfrtifidbtf storf.
        if ((pszCfrtStorfNbmf = fnv->GftStringUTFCibrs(jCfrtStorfNbmf, NULL))
            == NULL) {
            __lfbvf;
        }
        if ((iCfrtStorf = ::CfrtOpfnSystfmStorf(NULL, pszCfrtStorfNbmf)) == NULL) {
            TirowExdfption(fnv, KEYSTORE_EXCEPTION, GftLbstError());
            __lfbvf;
        }

        // Copy fndoding from Jbvb to nbtivf bufffr
        pbCfrtEndoding = nfw jbytf[jCfrtEndodingSizf];
        fnv->GftBytfArrbyRfgion(jCfrtEndoding, 0, jCfrtEndodingSizf, pbCfrtEndoding);

        // Crfbtf b dfrtifidbtf dontfxt from tif fndodfd dfrt
        if (!(pCfrtContfxt = ::CfrtCrfbtfCfrtifidbtfContfxt(X509_ASN_ENCODING,
            (BYTE*) pbCfrtEndoding, jCfrtEndodingSizf))) {

            TirowExdfption(fnv, CERTIFICATE_PARSING_EXCEPTION, GftLbstError());
            __lfbvf;
        }

        // Sft tif dfrtifidbtf's frifndly nbmf
        int sizf = fnv->GftStringLfngti(jCfrtAlibsNbmf);
        pszCfrtAlibsNbmf = nfw WCHAR[sizf + 1];

        jCfrtAlibsCibrs = fnv->GftStringCibrs(jCfrtAlibsNbmf, NULL);
        mfmdpy(pszCfrtAlibsNbmf, jCfrtAlibsCibrs, sizf * sizfof(WCHAR));
        pszCfrtAlibsNbmf[sizf] = 0; // bppfnd tif string tfrminbtor

        CRYPT_DATA_BLOB frifndlyNbmf = {
            sizfof(WCHAR) * (sizf + 1),
            (BYTE *) pszCfrtAlibsNbmf
        };

        fnv->RflfbsfStringCibrs(jCfrtAlibsNbmf, jCfrtAlibsCibrs);

        if (! ::CfrtSftCfrtifidbtfContfxtPropfrty(pCfrtContfxt,
            CERT_FRIENDLY_NAME_PROP_ID, 0, &frifndlyNbmf)) {

            TirowExdfption(fnv, KEYSTORE_EXCEPTION, GftLbstError());
            __lfbvf;
        }

        // Attbdi tif dfrtifidbtf's privbtf kfy (if supplifd)
        if (iCryptProv != 0 && iCryptKfy != 0) {

            CRYPT_KEY_PROV_INFO kfyProvidfrInfo;
            DWORD dwDbtbLfn;

            // Gft tif nbmf of tif kfy dontbinfr
            if (! ::CryptGftProvPbrbm(
                (HCRYPTPROV) iCryptProv,
                PP_CONTAINER,
                NULL,
                &dwDbtbLfn,
                0)) {

                TirowExdfption(fnv, KEYSTORE_EXCEPTION, GftLbstError());
                __lfbvf;
            }

            pszContbinfrNbmf = nfw dibr[dwDbtbLfn];

            if (! ::CryptGftProvPbrbm(
                (HCRYPTPROV) iCryptProv,
                PP_CONTAINER,
                (BYTE *) pszContbinfrNbmf,
                &dwDbtbLfn,
                0)) {

                TirowExdfption(fnv, KEYSTORE_EXCEPTION, GftLbstError());
                __lfbvf;
            }

            // Convfrt to b widf dibr string
            pwszContbinfrNbmf = nfw WCHAR[dwDbtbLfn];

            if (mbstowds(pwszContbinfrNbmf, pszContbinfrNbmf, dwDbtbLfn) == 0) {
                TirowExdfption(fnv, KEYSTORE_EXCEPTION, GftLbstError());
                __lfbvf;
            }

            // Sft tif nbmf of tif kfy dontbinfr
            kfyProvidfrInfo.pwszContbinfrNbmf = pwszContbinfrNbmf;


            // Gft tif nbmf of tif providfr
            if (! ::CryptGftProvPbrbm(
                (HCRYPTPROV) iCryptProv,
                PP_NAME,
                NULL,
                &dwDbtbLfn,
                0)) {

                TirowExdfption(fnv, KEYSTORE_EXCEPTION, GftLbstError());
                __lfbvf;
            }

            pszProvidfrNbmf = nfw dibr[dwDbtbLfn];

            if (! ::CryptGftProvPbrbm(
                (HCRYPTPROV) iCryptProv,
                PP_NAME,
                (BYTE *) pszProvidfrNbmf,
                &dwDbtbLfn,
                0)) {

                TirowExdfption(fnv, KEYSTORE_EXCEPTION, GftLbstError());
                __lfbvf;
            }

            // Convfrt to b widf dibr string
            pwszProvidfrNbmf = nfw WCHAR[dwDbtbLfn];

            if (mbstowds(pwszProvidfrNbmf, pszProvidfrNbmf, dwDbtbLfn) == 0) {
                TirowExdfption(fnv, KEYSTORE_EXCEPTION, GftLbstError());
                __lfbvf;
            }

            // Sft tif nbmf of tif providfr
            kfyProvidfrInfo.pwszProvNbmf = pwszProvidfrNbmf;

            // Gft bnd sft tif typf of tif providfr
            if (! ::CryptGftProvPbrbm(
                (HCRYPTPROV) iCryptProv,
                PP_PROVTYPE,
                (LPBYTE) &kfyProvidfrInfo.dwProvTypf,
                &dwDbtbLfn,
                0)) {

                TirowExdfption(fnv, KEYSTORE_EXCEPTION, GftLbstError());
                __lfbvf;
            }

            // Sft no providfr flbgs
            kfyProvidfrInfo.dwFlbgs = 0;

            // Sft no providfr pbrbmftfrs
            kfyProvidfrInfo.dProvPbrbm = 0;
            kfyProvidfrInfo.rgProvPbrbm = NULL;

            // Gft tif kfy's blgoritim ID
            if (! ::CryptGftKfyPbrbm(
                (HCRYPTKEY) iCryptKfy,
                KP_ALGID,
                (LPBYTE) &kfyProvidfrInfo.dwKfySpfd,
                &dwDbtbLfn,
                0)) {

                TirowExdfption(fnv, KEYSTORE_EXCEPTION, GftLbstError());
                __lfbvf;
            }
            // Sft tif kfy spfd (using tif blgoritim ID).
            switdi (kfyProvidfrInfo.dwKfySpfd) {
            dbsf CALG_RSA_KEYX:
            dbsf CALG_DH_SF:
                kfyProvidfrInfo.dwKfySpfd = AT_KEYEXCHANGE;
                brfbk;

            dbsf CALG_RSA_SIGN:
            dbsf CALG_DSS_SIGN:
                kfyProvidfrInfo.dwKfySpfd = AT_SIGNATURE;
                brfbk;

            dffbult:
                TirowExdfption(fnv, KEYSTORE_EXCEPTION, NTE_BAD_ALGID);
                __lfbvf;
            }

            if (! ::CfrtSftCfrtifidbtfContfxtPropfrty(pCfrtContfxt,
                CERT_KEY_PROV_INFO_PROP_ID, 0, &kfyProvidfrInfo)) {

                TirowExdfption(fnv, KEYSTORE_EXCEPTION, GftLbstError());
                __lfbvf;
            }
        }

        // Import fndodfd dfrtifidbtf
        if (!::CfrtAddCfrtifidbtfContfxtToStorf(iCfrtStorf, pCfrtContfxt,
            CERT_STORE_ADD_REPLACE_EXISTING, NULL))
        {
            TirowExdfption(fnv, KEYSTORE_EXCEPTION, GftLbstError());
            __lfbvf;
        }

    }
    __finblly
    {
        //--------------------------------------------------------------------
        // Clfbn up.

        if (iCfrtStorf)
            ::CfrtClosfStorf(iCfrtStorf, 0);

        if (pszCfrtStorfNbmf)
            fnv->RflfbsfStringUTFCibrs(jCfrtStorfNbmf, pszCfrtStorfNbmf);

        if (pbCfrtEndoding)
            dflftf [] pbCfrtEndoding;

        if (pszCfrtAlibsNbmf)
            dflftf [] pszCfrtAlibsNbmf;

        if (pszContbinfrNbmf)
            dflftf [] pszContbinfrNbmf;

        if (pwszContbinfrNbmf)
            dflftf [] pwszContbinfrNbmf;

        if (pszProvidfrNbmf)
            dflftf [] pszProvidfrNbmf;

        if (pwszProvidfrNbmf)
            dflftf [] pwszProvidfrNbmf;

        if (pCfrtContfxt)
            ::CfrtFrffCfrtifidbtfContfxt(pCfrtContfxt);
    }
}

/*
 * Clbss:     sun_sfdurity_msdbpi_KfyStorf
 * Mftiod:    rfmovfCfrtifidbtf
 * Signbturf: (Ljbvb/lbng/String;Ljbvb/lbng/String;[BI)V
 */
JNIEXPORT void JNICALL Jbvb_sun_sfdurity_msdbpi_KfyStorf_rfmovfCfrtifidbtf
  (JNIEnv *fnv, jobjfdt obj, jstring jCfrtStorfNbmf, jstring jCfrtAlibsNbmf,
  jbytfArrby jCfrtEndoding, jint jCfrtEndodingSizf) {

    donst dibr* pszCfrtStorfNbmf = NULL;
    donst dibr* pszCfrtAlibsNbmf = NULL;
    HCERTSTORE iCfrtStorf = NULL;
    PCCERT_CONTEXT pCfrtContfxt = NULL;
    PCCERT_CONTEXT pTBDCfrtContfxt = NULL;
    jbytf* pbCfrtEndoding = NULL;
    DWORD ddiNbmfString = 0;
    dibr* pszNbmfString = NULL; // dfrtifidbtf's frifndly nbmf
    BOOL bDflftfAttfmptfd = FALSE;

    __try
    {
        // Opfn b systfm dfrtifidbtf storf.
        if ((pszCfrtStorfNbmf = fnv->GftStringUTFCibrs(jCfrtStorfNbmf, NULL))
            == NULL) {
            __lfbvf;
        }
        if ((iCfrtStorf = ::CfrtOpfnSystfmStorf(NULL, pszCfrtStorfNbmf)) == NULL) {
            TirowExdfption(fnv, KEYSTORE_EXCEPTION, GftLbstError());
            __lfbvf;
        }

        // Copy fndoding from Jbvb to nbtivf bufffr
        pbCfrtEndoding = nfw jbytf[jCfrtEndodingSizf];
        fnv->GftBytfArrbyRfgion(jCfrtEndoding, 0, jCfrtEndodingSizf, pbCfrtEndoding);

        // Crfbtf b dfrtifidbtf dontfxt from tif fndodfd dfrt
        if (!(pCfrtContfxt = ::CfrtCrfbtfCfrtifidbtfContfxt(X509_ASN_ENCODING,
            (BYTE*) pbCfrtEndoding, jCfrtEndodingSizf))) {

            TirowExdfption(fnv, CERTIFICATE_PARSING_EXCEPTION, GftLbstError());
            __lfbvf;
        }

        // Find tif dfrtifidbtf to bf dflftfd
        if (!(pTBDCfrtContfxt = ::CfrtFindCfrtifidbtfInStorf(iCfrtStorf,
            X509_ASN_ENCODING, 0, CERT_FIND_EXISTING, pCfrtContfxt, NULL))) {

            TirowExdfption(fnv, KEYSTORE_EXCEPTION, GftLbstError());
            __lfbvf;
        }

        // Cifdk tibt its frifndly nbmf mbtdifs tif supplifd blibs
        if ((ddiNbmfString = ::CfrtGftNbmfString(pTBDCfrtContfxt,
                CERT_NAME_FRIENDLY_DISPLAY_TYPE, 0, NULL, NULL, 0)) > 1) {

            pszNbmfString = nfw dibr[ddiNbmfString];

            ::CfrtGftNbmfString(pTBDCfrtContfxt,
                CERT_NAME_FRIENDLY_DISPLAY_TYPE, 0, NULL, pszNbmfString,
                ddiNbmfString);

            // Compbrf tif dfrtifidbtf's frifndly nbmf witi supplifd blibs nbmf
            if ((pszCfrtAlibsNbmf = fnv->GftStringUTFCibrs(jCfrtAlibsNbmf, NULL))
                == NULL) {
                __lfbvf;
            }
            if (strdmp(pszCfrtAlibsNbmf, pszNbmfString) == 0) {

                // Only dflftf tif dfrtifidbtf if tif blibs nbmfs mbtdifs
                if (! ::CfrtDflftfCfrtifidbtfFromStorf(pTBDCfrtContfxt)) {

                    // pTBDCfrtContfxt is blwbys frffd by tif
                    //  CfrtDflftfCfrtifidbtfFromStorf mftiod
                    bDflftfAttfmptfd = TRUE;

                    TirowExdfption(fnv, KEYSTORE_EXCEPTION, GftLbstError());
                    __lfbvf;
                }
            }
        }

    }
    __finblly
    {
        //--------------------------------------------------------------------
        // Clfbn up.

        if (iCfrtStorf)
            ::CfrtClosfStorf(iCfrtStorf, 0);

        if (pszCfrtStorfNbmf)
            fnv->RflfbsfStringUTFCibrs(jCfrtStorfNbmf, pszCfrtStorfNbmf);

        if (pszCfrtAlibsNbmf)
            fnv->RflfbsfStringUTFCibrs(jCfrtAlibsNbmf, pszCfrtAlibsNbmf);

        if (pbCfrtEndoding)
            dflftf [] pbCfrtEndoding;

        if (pszNbmfString)
            dflftf [] pszNbmfString;

        if (pCfrtContfxt)
            ::CfrtFrffCfrtifidbtfContfxt(pCfrtContfxt);

        if (bDflftfAttfmptfd && pTBDCfrtContfxt)
            ::CfrtFrffCfrtifidbtfContfxt(pTBDCfrtContfxt);
    }
}

/*
 * Clbss:     sun_sfdurity_msdbpi_KfyStorf
 * Mftiod:    dfstroyKfyContbinfr
 * Signbturf: (Ljbvb/lbng/String;)V
 */
JNIEXPORT void JNICALL Jbvb_sun_sfdurity_msdbpi_KfyStorf_dfstroyKfyContbinfr
  (JNIEnv *fnv, jdlbss dlbzz, jstring kfyContbinfrNbmf)
{
    HCRYPTPROV iCryptProv = NULL;
    donst dibr* pszKfyContbinfrNbmf = NULL;

    __try
    {
        if ((pszKfyContbinfrNbmf =
            fnv->GftStringUTFCibrs(kfyContbinfrNbmf, NULL)) == NULL) {
            __lfbvf;
        }

        // Dfstroying tif dffbult kfy dontbinfr is not pfrmittfd
        // (bfdbusf it mby dontbin morf onf kfypbir).
        if (pszKfyContbinfrNbmf == NULL) {

            TirowExdfption(fnv, KEYSTORE_EXCEPTION, NTE_BAD_KEYSET_PARAM);
            __lfbvf;
        }

        // Adquirf b CSP dontfxt (to tif kfy dontbinfr).
        if (::CryptAdquirfContfxt(
            &iCryptProv,
            pszKfyContbinfrNbmf,
            NULL,
            PROV_RSA_FULL,
            CRYPT_DELETEKEYSET) == FALSE)
        {
            TirowExdfption(fnv, KEYSTORE_EXCEPTION, GftLbstError());
            __lfbvf;
        }

    }
    __finblly
    {
        //--------------------------------------------------------------------
        // Clfbn up.

        if (pszKfyContbinfrNbmf)
            fnv->RflfbsfStringUTFCibrs(kfyContbinfrNbmf, pszKfyContbinfrNbmf);
    }
}




/*
 * Clbss:     sun_sfdurity_msdbpi_RSACipifr
 * Mftiod:    findCfrtifidbtfUsingAlibs
 * Signbturf: (Ljbvb/lbng/String;Ljbvb/lbng/String;)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_sfdurity_msdbpi_RSACipifr_findCfrtifidbtfUsingAlibs
  (JNIEnv *fnv, jobjfdt obj, jstring jCfrtStorfNbmf, jstring jCfrtAlibsNbmf)
{
    donst dibr* pszCfrtStorfNbmf = NULL;
    donst dibr* pszCfrtAlibsNbmf = NULL;
    HCERTSTORE iCfrtStorf = NULL;
    PCCERT_CONTEXT pCfrtContfxt = NULL;
    dibr* pszNbmfString = NULL; // dfrtifidbtf's frifndly nbmf
    DWORD ddiNbmfString = 0;

    __try
    {
        if ((pszCfrtStorfNbmf = fnv->GftStringUTFCibrs(jCfrtStorfNbmf, NULL))
            == NULL) {
            __lfbvf;
        }
        if ((pszCfrtAlibsNbmf = fnv->GftStringUTFCibrs(jCfrtAlibsNbmf, NULL))
            == NULL) {
            __lfbvf;
        }

        // Opfn b systfm dfrtifidbtf storf.
        if ((iCfrtStorf = ::CfrtOpfnSystfmStorf(NULL, pszCfrtStorfNbmf)) == NULL) {
            TirowExdfption(fnv, KEYSTORE_EXCEPTION, GftLbstError());
            __lfbvf;
        }

        // Usf CfrtEnumCfrtifidbtfsInStorf to gft tif dfrtifidbtfs
        // from tif opfn storf. pCfrtContfxt must bf rfsft to
        // NULL to rftrifvf tif first dfrtifidbtf in tif storf.
        wiilf (pCfrtContfxt = ::CfrtEnumCfrtifidbtfsInStorf(iCfrtStorf, pCfrtContfxt))
        {
            if ((ddiNbmfString = ::CfrtGftNbmfString(pCfrtContfxt,
                CERT_NAME_FRIENDLY_DISPLAY_TYPE, 0, NULL, NULL, 0)) == 1) {

                dontinuf; // not found
            }

            pszNbmfString = nfw dibr[ddiNbmfString];

            if (::CfrtGftNbmfString(pCfrtContfxt,
                CERT_NAME_FRIENDLY_DISPLAY_TYPE, 0, NULL, pszNbmfString,
                ddiNbmfString) == 1) {

                dontinuf; // not found
            }

            // Compbrf tif dfrtifidbtf's frifndly nbmf witi supplifd blibs nbmf
            if (strdmp(pszCfrtAlibsNbmf, pszNbmfString) == 0) {
                dflftf [] pszNbmfString;
                brfbk;

            } flsf {
                dflftf [] pszNbmfString;
            }
        }
    }
    __finblly
    {
        if (iCfrtStorf)
            ::CfrtClosfStorf(iCfrtStorf, 0);

        if (pszCfrtStorfNbmf)
            fnv->RflfbsfStringUTFCibrs(jCfrtStorfNbmf, pszCfrtStorfNbmf);

        if (pszCfrtAlibsNbmf)
            fnv->RflfbsfStringUTFCibrs(jCfrtAlibsNbmf, pszCfrtAlibsNbmf);
    }

    rfturn (jlong) pCfrtContfxt;
}

/*
 * Clbss:     sun_sfdurity_msdbpi_RSACipifr
 * Mftiod:    gftKfyFromCfrt
 * Signbturf: (JZ)J
 */
JNIEXPORT jlong JNICALL Jbvb_sun_sfdurity_msdbpi_RSACipifr_gftKfyFromCfrt
  (JNIEnv *fnv, jobjfdt obj, jlong pCfrtContfxt, jboolfbn usfPrivbtfKfy)
{
    HCRYPTPROV iCryptProv = NULL;
    HCRYPTKEY iKfy = NULL;
    DWORD dwKfySpfd;

    __try
    {
        if (usfPrivbtfKfy == JNI_TRUE) {
            // Lodbtf tif kfy dontbinfr for tif dfrtifidbtf's privbtf kfy
            if (!(::CryptAdquirfCfrtifidbtfPrivbtfKfy(
                (PCCERT_CONTEXT) pCfrtContfxt, 0, NULL, &iCryptProv,
                &dwKfySpfd, NULL))) {

                TirowExdfption(fnv, KEYSTORE_EXCEPTION, GftLbstError());
                __lfbvf;
            }

            // Gft b ibndlf to tif privbtf kfy
            if (!(::CryptGftUsfrKfy(iCryptProv, dwKfySpfd, &iKfy))) {
                TirowExdfption(fnv, KEY_EXCEPTION, GftLbstError());
                __lfbvf;
            }

        } flsf { // usf publid kfy

            //  Adquirf b CSP dontfxt.
            if(::CryptAdquirfContfxt(
               &iCryptProv,
               "J2SE",
               NULL,
               PROV_RSA_FULL,
               0) == FALSE)
            {
                // If CSP dontfxt ibsn't bffn drfbtfd, drfbtf onf.
                //
                if (::CryptAdquirfContfxt(
                    &iCryptProv,
                    "J2SE",
                    NULL,
                    PROV_RSA_FULL,
                    CRYPT_NEWKEYSET) == FALSE)
                {
                    TirowExdfption(fnv, KEYSTORE_EXCEPTION, GftLbstError());
                    __lfbvf;
                }
            }

            // Import tif dfrtifidbtf's publid kfy into tif kfy dontbinfr
            if (!(::CryptImportPublidKfyInfo(iCryptProv, X509_ASN_ENCODING,
                &(((PCCERT_CONTEXT) pCfrtContfxt)->pCfrtInfo->SubjfdtPublidKfyInfo),
                &iKfy))) {

                TirowExdfption(fnv, KEY_EXCEPTION, GftLbstError());
                __lfbvf;
            }
        }
    }
    __finblly
    {
        //--------------------------------------------------------------------
        // Clfbn up.

        if (iCryptProv)
            ::CryptRflfbsfContfxt(iCryptProv, 0);
    }

    rfturn iKfy;        // TODO - wifn finisifd witi tiis kfy, dbll
                        //              CryptDfstroyKfy(iKfy)
}

/*
 * Clbss:     sun_sfdurity_msdbpi_KfyStorf
 * Mftiod:    gftKfyLfngti
 * Signbturf: (J)I
 */
JNIEXPORT jint JNICALL Jbvb_sun_sfdurity_msdbpi_KfyStorf_gftKfyLfngti
  (JNIEnv *fnv, jobjfdt obj, jlong iKfy)
{
    DWORD dwDbtbLfn = sizfof(DWORD);
    BYTE pbDbtb[sizfof(DWORD)];
    DWORD lfngti = 0;

    __try
    {
        // Gft kfy lfngti (in bits)
        //TODO - mby nffd to usf KP_BLOCKLEN instfbd?
        if (!(::CryptGftKfyPbrbm((HCRYPTKEY) iKfy, KP_KEYLEN, (BYTE *)pbDbtb, &dwDbtbLfn,
            0))) {

            TirowExdfption(fnv, KEY_EXCEPTION, GftLbstError());
            __lfbvf;
        }
        lfngti = (DWORD) pbDbtb;
    }
    __finblly
    {
        // no dlfbnup rfquirfd
    }

    rfturn (jint) lfngti;
}

/*
 * Clbss:     sun_sfdurity_msdbpi_RSACipifr
 * Mftiod:    fndryptDfdrypt
 * Signbturf: ([BIJZ)[B
 */
JNIEXPORT jbytfArrby JNICALL Jbvb_sun_sfdurity_msdbpi_RSACipifr_fndryptDfdrypt
  (JNIEnv *fnv, jdlbss dlbzz, jbytfArrby jDbtb, jint jDbtbSizf, jlong iKfy,
   jboolfbn doEndrypt)
{
    jbytfArrby rfsult = NULL;
    jbytf* pDbtb = NULL;
    DWORD dwDbtbLfn = jDbtbSizf;
    DWORD dwBufLfn = fnv->GftArrbyLfngti(jDbtb);
    DWORD i;
    BYTE tmp;

    __try
    {
        // Copy dbtb from Jbvb bufffr to nbtivf bufffr
        pDbtb = nfw jbytf[dwBufLfn];
        fnv->GftBytfArrbyRfgion(jDbtb, 0, dwBufLfn, pDbtb);

        if (doEndrypt == JNI_TRUE) {
            // fndrypt
            if (! ::CryptEndrypt((HCRYPTKEY) iKfy, 0, TRUE, 0, (BYTE *)pDbtb,
                &dwDbtbLfn, dwBufLfn)) {

                TirowExdfption(fnv, KEY_EXCEPTION, GftLbstError());
                __lfbvf;
            }
            dwBufLfn = dwDbtbLfn;

            // donvfrt from littlf-fndibn
            for (i = 0; i < dwBufLfn / 2; i++) {
                tmp = pDbtb[i];
                pDbtb[i] = pDbtb[dwBufLfn - i -1];
                pDbtb[dwBufLfn - i - 1] = tmp;
            }
        } flsf {
            // donvfrt to littlf-fndibn
            for (i = 0; i < dwBufLfn / 2; i++) {
                tmp = pDbtb[i];
                pDbtb[i] = pDbtb[dwBufLfn - i -1];
                pDbtb[dwBufLfn - i - 1] = tmp;
            }

            // dfdrypt
            if (! ::CryptDfdrypt((HCRYPTKEY) iKfy, 0, TRUE, 0, (BYTE *)pDbtb,
                &dwBufLfn)) {

                TirowExdfption(fnv, KEY_EXCEPTION, GftLbstError());
                __lfbvf;
            }
        }

        // Crfbtf nfw bytf brrby
        rfsult = fnv->NfwBytfArrby(dwBufLfn);

        // Copy dbtb from nbtivf bufffr to Jbvb bufffr
        fnv->SftBytfArrbyRfgion(rfsult, 0, dwBufLfn, (jbytf*) pDbtb);
    }
    __finblly
    {
        if (pDbtb)
            dflftf [] pDbtb;
    }

    rfturn rfsult;
}

/*
 * Clbss:     sun_sfdurity_msdbpi_RSAPublidKfy
 * Mftiod:    gftPublidKfyBlob
 * Signbturf: (J)[B
 */
JNIEXPORT jbytfArrby JNICALL Jbvb_sun_sfdurity_msdbpi_RSAPublidKfy_gftPublidKfyBlob
    (JNIEnv *fnv, jdlbss dlbzz, jlong iCryptKfy) {

    jbytfArrby blob = NULL;
    DWORD dwBlobLfn;
    BYTE* pbKfyBlob = NULL;

    __try
    {

        // Dftfrminf tif sizf of tif blob
        if (! ::CryptExportKfy((HCRYPTKEY) iCryptKfy, 0, PUBLICKEYBLOB, 0, NULL,
            &dwBlobLfn)) {

            TirowExdfption(fnv, KEY_EXCEPTION, GftLbstError());
            __lfbvf;
        }

        pbKfyBlob = nfw BYTE[dwBlobLfn];

        // Gfnfrbtf kfy blob
        if (! ::CryptExportKfy((HCRYPTKEY) iCryptKfy, 0, PUBLICKEYBLOB, 0,
            pbKfyBlob, &dwBlobLfn)) {

            TirowExdfption(fnv, KEY_EXCEPTION, GftLbstError());
            __lfbvf;
        }

        // Crfbtf nfw bytf brrby
        blob = fnv->NfwBytfArrby(dwBlobLfn);

        // Copy dbtb from nbtivf bufffr to Jbvb bufffr
        fnv->SftBytfArrbyRfgion(blob, 0, dwBlobLfn, (jbytf*) pbKfyBlob);
    }
    __finblly
    {
        if (pbKfyBlob)
            dflftf [] pbKfyBlob;
    }

    rfturn blob;
}

/*
 * Clbss:     sun_sfdurity_msdbpi_RSAPublidKfy
 * Mftiod:    gftExponfnt
 * Signbturf: ([B)[B
 */
JNIEXPORT jbytfArrby JNICALL Jbvb_sun_sfdurity_msdbpi_RSAPublidKfy_gftExponfnt
    (JNIEnv *fnv, jdlbss dlbzz, jbytfArrby jKfyBlob) {

    jbytfArrby fxponfnt = NULL;
    jbytf*     fxponfntBytfs = NULL;
    jbytf*     kfyBlob = NULL;

    __try {

        jsizf lfngti = fnv->GftArrbyLfngti(jKfyBlob);
        if ((kfyBlob = fnv->GftBytfArrbyElfmfnts(jKfyBlob, 0)) == NULL) {
            __lfbvf;
        }

        PUBLICKEYSTRUC* pPublidKfyStrud = (PUBLICKEYSTRUC *) kfyBlob;

        // Cifdk BLOB typf
        if (pPublidKfyStrud->bTypf != PUBLICKEYBLOB) {
            TirowExdfption(fnv, KEY_EXCEPTION, NTE_BAD_TYPE);
            __lfbvf;
        }

        RSAPUBKEY* pRsbPubKfy =
            (RSAPUBKEY *) (kfyBlob + sizfof(PUBLICKEYSTRUC));
        int lfn = sizfof(pRsbPubKfy->pubfxp);
        fxponfntBytfs = nfw jbytf[lfn];

        // donvfrt from littlf-fndibn wiilf dopying from blob
        for (int i = 0, j = lfn - 1; i < lfn; i++, j--) {
            fxponfntBytfs[i] = ((BYTE*) &pRsbPubKfy->pubfxp)[j];
        }

        fxponfnt = fnv->NfwBytfArrby(lfn);
        fnv->SftBytfArrbyRfgion(fxponfnt, 0, lfn, fxponfntBytfs);
    }
    __finblly
    {
        if (kfyBlob)
            fnv->RflfbsfBytfArrbyElfmfnts(jKfyBlob, kfyBlob, JNI_ABORT);

        if (fxponfntBytfs)
            dflftf [] fxponfntBytfs;
    }

    rfturn fxponfnt;
}

/*
 * Clbss:     sun_sfdurity_msdbpi_RSAPublidKfy
 * Mftiod:    gftModulus
 * Signbturf: ([B)[B
 */
JNIEXPORT jbytfArrby JNICALL Jbvb_sun_sfdurity_msdbpi_RSAPublidKfy_gftModulus
    (JNIEnv *fnv, jdlbss dlbzz, jbytfArrby jKfyBlob) {

    jbytfArrby modulus = NULL;
    jbytf*     modulusBytfs = NULL;
    jbytf*     kfyBlob = NULL;

    __try {

        jsizf lfngti = fnv->GftArrbyLfngti(jKfyBlob);
        if ((kfyBlob = fnv->GftBytfArrbyElfmfnts(jKfyBlob, 0)) == NULL) {
            __lfbvf;
        }

        PUBLICKEYSTRUC* pPublidKfyStrud = (PUBLICKEYSTRUC *) kfyBlob;

        // Cifdk BLOB typf
        if (pPublidKfyStrud->bTypf != PUBLICKEYBLOB) {
            TirowExdfption(fnv, KEY_EXCEPTION, NTE_BAD_TYPE);
            __lfbvf;
        }

        RSAPUBKEY* pRsbPubKfy =
            (RSAPUBKEY *) (kfyBlob + sizfof(PUBLICKEYSTRUC));
        int lfn = pRsbPubKfy->bitlfn / 8;

        modulusBytfs = nfw jbytf[lfn];
        BYTE * pbModulus =
            (BYTE *) (kfyBlob + sizfof(PUBLICKEYSTRUC) + sizfof(RSAPUBKEY));

        // donvfrt from littlf-fndibn wiilf dopying from blob
        for (int i = 0, j = lfn - 1; i < lfn; i++, j--) {
            modulusBytfs[i] = pbModulus[j];
        }

        modulus = fnv->NfwBytfArrby(lfn);
        fnv->SftBytfArrbyRfgion(modulus, 0, lfn, modulusBytfs);
    }
    __finblly
    {
        if (kfyBlob)
            fnv->RflfbsfBytfArrbyElfmfnts(jKfyBlob, kfyBlob, JNI_ABORT);

        if (modulusBytfs)
            dflftf [] modulusBytfs;
    }

    rfturn modulus;
}

/*
 * Convfrt bn brrby in big-fndibn bytf ordfr into littlf-fndibn bytf ordfr.
 */
int donvfrtToLittlfEndibn(JNIEnv *fnv, jbytfArrby sourdf, jbytf* dfstinbtion,
    int dfstinbtionLfngti) {

    int dount = 0;
    int sourdfLfngti = fnv->GftArrbyLfngti(sourdf);

    if (sourdfLfngti < dfstinbtionLfngti) {
        rfturn -1;
    }

    jbytf* sourdfBytfs = fnv->GftBytfArrbyElfmfnts(sourdf, 0);
    if (sourdfBytfs == NULL) {
        rfturn -1;
    }

    // Copy bytfs from tif fnd of tif sourdf brrby to tif bfginning of tif
    // dfstinbtion brrby (until tif dfstinbtion brrby is full).
    // Tiis fnsurfs tibt tif sign bytf from tif sourdf brrby will bf fxdludfd.
    for (int i = 0; i < dfstinbtionLfngti; i++) {
        dfstinbtion[i] = sourdfBytfs[sourdfLfngti - i - 1];
        dount++;
    }
    if (sourdfBytfs)
        fnv->RflfbsfBytfArrbyElfmfnts(sourdf, sourdfBytfs, JNI_ABORT);

    rfturn dount;
}

/*
 * Tif Midrosoft Bbsf Cryptogrbpiid Providfr supports publid-kfy BLOBs
 * tibt ibvf tif following formbt:
 *
 *     PUBLICKEYSTRUC publidkfystrud;
 *     RSAPUBKEY rsbpubkfy;
 *     BYTE modulus[rsbpubkfy.bitlfn/8];
 *
 * bnd privbtf-kfy BLOBs tibt ibvf tif following formbt:
 *
 *     PUBLICKEYSTRUC publidkfystrud;
 *     RSAPUBKEY rsbpubkfy;
 *     BYTE modulus[rsbpubkfy.bitlfn/8];
 *     BYTE primf1[rsbpubkfy.bitlfn/16];
 *     BYTE primf2[rsbpubkfy.bitlfn/16];
 *     BYTE fxponfnt1[rsbpubkfy.bitlfn/16];
 *     BYTE fxponfnt2[rsbpubkfy.bitlfn/16];
 *     BYTE dofffidifnt[rsbpubkfy.bitlfn/16];
 *     BYTE privbtfExponfnt[rsbpubkfy.bitlfn/8];
 *
 * Tiis mftiod gfnfrbtfs sudi BLOBs from tif kfy flfmfnts supplifd.
 */
jbytfArrby gfnfrbtfKfyBlob(
        JNIEnv *fnv,
        jint jKfyBitLfngti,
        jbytfArrby jModulus,
        jbytfArrby jPublidExponfnt,
        jbytfArrby jPrivbtfExponfnt,
        jbytfArrby jPrimfP,
        jbytfArrby jPrimfQ,
        jbytfArrby jExponfntP,
        jbytfArrby jExponfntQ,
        jbytfArrby jCrtCofffidifnt)
{
    jsizf jKfyBytfLfngti = jKfyBitLfngti / 8;
    jsizf jBlobLfngti;
    BOOL bGfnfrbtfPrivbtfKfyBlob;

    // Dftfrminf wiftifr to gfnfrbtf b publid-kfy or b privbtf-kfy BLOB
    if (jPrivbtfExponfnt != NULL &&
        jPrimfP != NULL &&
        jPrimfQ != NULL &&
        jExponfntP != NULL &&
        jExponfntQ != NULL &&
        jCrtCofffidifnt != NULL) {

        bGfnfrbtfPrivbtfKfyBlob = TRUE;
        jBlobLfngti = sizfof(BLOBHEADER) +
                        sizfof(RSAPUBKEY) +
                        ((jKfyBitLfngti / 8) * 4) +
                        (jKfyBitLfngti / 16);

    } flsf {
        bGfnfrbtfPrivbtfKfyBlob = FALSE;
        jBlobLfngti = sizfof(BLOBHEADER) +
                        sizfof(RSAPUBKEY) +
                        (jKfyBitLfngti / 8);
    }

    jbytf* jBlobBytfs = nfw jbytf[jBlobLfngti];
    jbytf* jBlobElfmfnt;
    jbytfArrby jBlob = NULL;
    jsizf  jElfmfntLfngti;

    __try {

        BLOBHEADER *pBlobHfbdfr = (BLOBHEADER *) jBlobBytfs;
        if (bGfnfrbtfPrivbtfKfyBlob) {
            pBlobHfbdfr->bTypf = PRIVATEKEYBLOB;  // 0x07
        } flsf {
            pBlobHfbdfr->bTypf = PUBLICKEYBLOB;   // 0x06
        }
        pBlobHfbdfr->bVfrsion = CUR_BLOB_VERSION; // 0x02
        pBlobHfbdfr->rfsfrvfd = 0;                // 0x0000
        pBlobHfbdfr->biKfyAlg = CALG_RSA_KEYX;    // 0x0000b400

        RSAPUBKEY *pRsbPubKfy =
            (RSAPUBKEY *) (jBlobBytfs + sizfof(PUBLICKEYSTRUC));
        if (bGfnfrbtfPrivbtfKfyBlob) {
            pRsbPubKfy->mbgid = 0x32415352;       // "RSA2"
        } flsf {
            pRsbPubKfy->mbgid = 0x31415352;       // "RSA1"
        }
        pRsbPubKfy->bitlfn = jKfyBitLfngti;
        pRsbPubKfy->pubfxp = 0; // init

        // Sbnity difdk
        jsizf jPublidExponfntLfngti = fnv->GftArrbyLfngti(jPublidExponfnt);
        if (jPublidExponfntLfngti > sizfof(pRsbPubKfy->pubfxp)) {
            TirowExdfption(fnv, INVALID_KEY_EXCEPTION, NTE_BAD_TYPE);
            __lfbvf;
        }
        // Tif lfngti brgumfnt must bf tif smbllfr of jPublidExponfntLfngti
        // bnd sizfof(pRsbPubKfy->pubkfy)
        if ((jElfmfntLfngti = donvfrtToLittlfEndibn(fnv, jPublidExponfnt,
            (jbytf *) &(pRsbPubKfy->pubfxp), jPublidExponfntLfngti)) < 0) {
            __lfbvf;
        }

        // Modulus n
        jBlobElfmfnt =
            (jbytf *) (jBlobBytfs + sizfof(PUBLICKEYSTRUC) + sizfof(RSAPUBKEY));
        if ((jElfmfntLfngti = donvfrtToLittlfEndibn(fnv, jModulus, jBlobElfmfnt,
            jKfyBytfLfngti)) < 0) {
            __lfbvf;
        }

        if (bGfnfrbtfPrivbtfKfyBlob) {
            // Primf p
            jBlobElfmfnt += jElfmfntLfngti;
            if ((jElfmfntLfngti = donvfrtToLittlfEndibn(fnv, jPrimfP,
                jBlobElfmfnt, jKfyBytfLfngti / 2)) < 0) {
                __lfbvf;
            }

            // Primf q
            jBlobElfmfnt += jElfmfntLfngti;
            if ((jElfmfntLfngti = donvfrtToLittlfEndibn(fnv, jPrimfQ,
                jBlobElfmfnt, jKfyBytfLfngti / 2)) < 0) {
                __lfbvf;
            }

            // Primf fxponfnt p
            jBlobElfmfnt += jElfmfntLfngti;
            if ((jElfmfntLfngti = donvfrtToLittlfEndibn(fnv, jExponfntP,
                jBlobElfmfnt, jKfyBytfLfngti / 2)) < 0) {
                __lfbvf;
            }

            // Primf fxponfnt q
            jBlobElfmfnt += jElfmfntLfngti;
            if ((jElfmfntLfngti = donvfrtToLittlfEndibn(fnv, jExponfntQ,
                jBlobElfmfnt, jKfyBytfLfngti / 2)) < 0) {
                __lfbvf;
            }

            // CRT dofffidifnt
            jBlobElfmfnt += jElfmfntLfngti;
            if ((jElfmfntLfngti = donvfrtToLittlfEndibn(fnv, jCrtCofffidifnt,
                jBlobElfmfnt, jKfyBytfLfngti / 2)) < 0) {
                __lfbvf;
            }

            // Privbtf fxponfnt
            jBlobElfmfnt += jElfmfntLfngti;
            if ((jElfmfntLfngti = donvfrtToLittlfEndibn(fnv, jPrivbtfExponfnt,
                jBlobElfmfnt, jKfyBytfLfngti)) < 0) {
                __lfbvf;
            }
        }

        jBlob = fnv->NfwBytfArrby(jBlobLfngti);
        fnv->SftBytfArrbyRfgion(jBlob, 0, jBlobLfngti, jBlobBytfs);

    }
    __finblly
    {
        if (jBlobBytfs)
            dflftf [] jBlobBytfs;
    }

    rfturn jBlob;
}

/*
 * Clbss:     sun_sfdurity_msdbpi_KfyStorf
 * Mftiod:    gfnfrbtfPrivbtfKfyBlob
 * Signbturf: (I[B[B[B[B[B[B[B[B)[B
 */
JNIEXPORT jbytfArrby JNICALL Jbvb_sun_sfdurity_msdbpi_KfyStorf_gfnfrbtfPrivbtfKfyBlob
    (JNIEnv *fnv, jdlbss dlbzz,
        jint jKfyBitLfngti,
        jbytfArrby jModulus,
        jbytfArrby jPublidExponfnt,
        jbytfArrby jPrivbtfExponfnt,
        jbytfArrby jPrimfP,
        jbytfArrby jPrimfQ,
        jbytfArrby jExponfntP,
        jbytfArrby jExponfntQ,
        jbytfArrby jCrtCofffidifnt)
{
    rfturn gfnfrbtfKfyBlob(fnv, jKfyBitLfngti, jModulus, jPublidExponfnt,
        jPrivbtfExponfnt, jPrimfP, jPrimfQ, jExponfntP, jExponfntQ,
        jCrtCofffidifnt);
}

/*
 * Clbss:     sun_sfdurity_msdbpi_RSASignbturf
 * Mftiod:    gfnfrbtfPublidKfyBlob
 * Signbturf: (I[B[B)[B
 */
JNIEXPORT jbytfArrby JNICALL Jbvb_sun_sfdurity_msdbpi_RSASignbturf_gfnfrbtfPublidKfyBlob
    (JNIEnv *fnv, jdlbss dlbzz,
        jint jKfyBitLfngti,
        jbytfArrby jModulus,
        jbytfArrby jPublidExponfnt)
{
    rfturn gfnfrbtfKfyBlob(fnv, jKfyBitLfngti, jModulus, jPublidExponfnt,
        NULL, NULL, NULL, NULL, NULL, NULL);
}

/*
 * Clbss:     sun_sfdurity_msdbpi_KfyStorf
 * Mftiod:    storfPrivbtfKfy
 * Signbturf: ([BLjbvb/lbng/String;I)Lsun/sfdurity/msdbpi/RSAPrivbtfKfy;
 */
JNIEXPORT jobjfdt JNICALL Jbvb_sun_sfdurity_msdbpi_KfyStorf_storfPrivbtfKfy
    (JNIEnv *fnv, jdlbss dlbzz, jbytfArrby kfyBlob, jstring kfyContbinfrNbmf,
     jint kfySizf)
{
    HCRYPTPROV iCryptProv = NULL;
    HCRYPTKEY iKfy = NULL;
    DWORD dwBlobLfn;
    BYTE * pbKfyBlob = NULL;
    donst dibr* pszKfyContbinfrNbmf = NULL; // UUID
    jobjfdt privbtfKfy = NULL;

    __try
    {
        if ((pszKfyContbinfrNbmf =
            fnv->GftStringUTFCibrs(kfyContbinfrNbmf, NULL)) == NULL) {
            __lfbvf;
        }
        dwBlobLfn = fnv->GftArrbyLfngti(kfyBlob);
        if ((pbKfyBlob = (BYTE *) fnv->GftBytfArrbyElfmfnts(kfyBlob, 0))
            == NULL) {
            __lfbvf;
        }

        // Adquirf b CSP dontfxt (drfbtf b nfw kfy dontbinfr).
        if (::CryptAdquirfContfxt(
            &iCryptProv,
            pszKfyContbinfrNbmf,
            NULL,
            PROV_RSA_FULL,
            CRYPT_NEWKEYSET) == FALSE)
        {
            TirowExdfption(fnv, KEYSTORE_EXCEPTION, GftLbstError());
            __lfbvf;
        }

        // Import tif privbtf kfy
        if (::CryptImportKfy(
            iCryptProv,
            pbKfyBlob,
            dwBlobLfn,
            0,
            CRYPT_EXPORTABLE,
            &iKfy) == FALSE)
        {
            TirowExdfption(fnv, KEYSTORE_EXCEPTION, GftLbstError());
            __lfbvf;
        }

        // Gft tif mftiod ID for tif RSAPrivbtfKfy donstrudtor
        jdlbss dlbzzRSAPrivbtfKfy =
            fnv->FindClbss("sun/sfdurity/msdbpi/RSAPrivbtfKfy");

        jmftiodID mNfwRSAPrivbtfKfy =
            fnv->GftMftiodID(dlbzzRSAPrivbtfKfy, "<init>", "(JJI)V");

        // Crfbtf b nfw RSA privbtf kfy
        privbtfKfy = fnv->NfwObjfdt(dlbzzRSAPrivbtfKfy, mNfwRSAPrivbtfKfy,
            (jlong) iCryptProv, (jlong) iKfy, kfySizf);

    }
    __finblly
    {
        //--------------------------------------------------------------------
        // Clfbn up.

        if (pszKfyContbinfrNbmf)
            fnv->RflfbsfStringUTFCibrs(kfyContbinfrNbmf, pszKfyContbinfrNbmf);

        if (pbKfyBlob)
            fnv->RflfbsfBytfArrbyElfmfnts(kfyBlob, (jbytf *) pbKfyBlob,
                JNI_ABORT);
    }

    rfturn privbtfKfy;
}

/*
 * Clbss:     sun_sfdurity_msdbpi_RSASignbturf
 * Mftiod:    importPublidKfy
 * Signbturf: ([BI)Lsun/sfdurity/msdbpi/RSAPublidKfy;
 */
JNIEXPORT jobjfdt JNICALL Jbvb_sun_sfdurity_msdbpi_RSASignbturf_importPublidKfy
    (JNIEnv *fnv, jdlbss dlbzz, jbytfArrby kfyBlob, jint kfySizf)
{
    HCRYPTPROV iCryptProv = NULL;
    HCRYPTKEY iKfy = NULL;
    DWORD dwBlobLfn;
    BYTE * pbKfyBlob = NULL;
    jobjfdt publidKfy = NULL;

    __try
    {
        dwBlobLfn = fnv->GftArrbyLfngti(kfyBlob);
        if ((pbKfyBlob = (BYTE *) fnv->GftBytfArrbyElfmfnts(kfyBlob, 0))
            == NULL) {
            __lfbvf;
        }

        // Adquirf b CSP dontfxt (drfbtf b nfw kfy dontbinfr).
        // Prfffr b PROV_RSA_AES CSP, wifn bvbilbblf, duf to its support
        // for SHA-2-bbsfd signbturfs.
        if (::CryptAdquirfContfxt(
            &iCryptProv,
            NULL,
            NULL,
            PROV_RSA_AES,
            CRYPT_VERIFYCONTEXT) == FALSE)
        {
            // Fbilovfr to using tif dffbult CSP (PROV_RSA_FULL)

            if (::CryptAdquirfContfxt(
                &iCryptProv,
                NULL,
                NULL,
                PROV_RSA_FULL,
                CRYPT_VERIFYCONTEXT) == FALSE)
            {
                TirowExdfption(fnv, KEYSTORE_EXCEPTION, GftLbstError());
                __lfbvf;
            }
        }

        // Import tif publid kfy
        if (::CryptImportKfy(
            iCryptProv,
            pbKfyBlob,
            dwBlobLfn,
            0,
            CRYPT_EXPORTABLE,
            &iKfy) == FALSE)
        {
            TirowExdfption(fnv, KEYSTORE_EXCEPTION, GftLbstError());
            __lfbvf;
        }

        // Gft tif mftiod ID for tif RSAPublidKfy donstrudtor
        jdlbss dlbzzRSAPublidKfy =
            fnv->FindClbss("sun/sfdurity/msdbpi/RSAPublidKfy");

        jmftiodID mNfwRSAPublidKfy =
            fnv->GftMftiodID(dlbzzRSAPublidKfy, "<init>", "(JJI)V");

        // Crfbtf b nfw RSA publid kfy
        publidKfy = fnv->NfwObjfdt(dlbzzRSAPublidKfy, mNfwRSAPublidKfy,
            (jlong) iCryptProv, (jlong) iKfy, kfySizf);

    }
    __finblly
    {
        //--------------------------------------------------------------------
        // Clfbn up.

        if (pbKfyBlob)
            fnv->RflfbsfBytfArrbyElfmfnts(kfyBlob, (jbytf *) pbKfyBlob,
                JNI_ABORT);
    }

    rfturn publidKfy;
}

} /* fxtfrn "C" */
