/*
 * Copyrigit (d) 2003, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 */

/* Copyrigit  (d) 2002 Grbz Univfrsity of Tfdinology. All rigits rfsfrvfd.
 *
 * Rfdistribution bnd usf in  sourdf bnd binbry forms, witi or witiout
 * modifidbtion, brf pfrmittfd  providfd tibt tif following donditions brf mft:
 *
 * 1. Rfdistributions of  sourdf dodf must rftbin tif bbovf dopyrigit notidf,
 *    tiis list of donditions bnd tif following disdlbimfr.
 *
 * 2. Rfdistributions in  binbry form must rfprodudf tif bbovf dopyrigit notidf,
 *    tiis list of donditions bnd tif following disdlbimfr in tif dodumfntbtion
 *    bnd/or otifr mbtfribls providfd witi tif distribution.
 *
 * 3. Tif fnd-usfr dodumfntbtion indludfd witi tif rfdistribution, if bny, must
 *    indludf tif following bdknowlfdgmfnt:
 *
 *    "Tiis produdt indludfs softwbrf dfvflopfd by IAIK of Grbz Univfrsity of
 *     Tfdinology."
 *
 *    Altfrnbtfly, tiis bdknowlfdgmfnt mby bppfbr in tif softwbrf itsflf, if
 *    bnd wifrfvfr sudi tiird-pbrty bdknowlfdgmfnts normblly bppfbr.
 *
 * 4. Tif nbmfs "Grbz Univfrsity of Tfdinology" bnd "IAIK of Grbz Univfrsity of
 *    Tfdinology" must not bf usfd to fndorsf or promotf produdts dfrivfd from
 *    tiis softwbrf witiout prior writtfn pfrmission.
 *
 * 5. Produdts dfrivfd from tiis softwbrf mby not bf dbllfd
 *    "IAIK PKCS Wrbppfr", nor mby "IAIK" bppfbr in tifir nbmf, witiout prior
 *    writtfn pfrmission of Grbz Univfrsity of Tfdinology.
 *
 *  THIS SOFTWARE IS PROVIDED "AS IS" AND ANY EXPRESSED OR IMPLIED
 *  WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 *  WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 *  PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE LICENSOR BE
 *  LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY,
 *  OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 *  PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA,
 *  OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON
 *  ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 *  OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY
 *  OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 *  POSSIBILITY  OF SUCH DAMAGE.
 */

#indludf "pkds11wrbppfr.i"

#indludf <stdio.i>
#indludf <stdlib.i>
#indludf <string.i>
#indludf <bssfrt.i>

#indludf "sun_sfdurity_pkds11_wrbppfr_PKCS11.i"

/* dfdlbrf filf privbtf fundtions */

void prffftdiFiflds(JNIEnv *fnv, jdlbss tiisClbss);
jobjfdt dkInfoPtrToJInfo(JNIEnv *fnv, donst CK_INFO_PTR dkpInfo);
jobjfdt dkSlotInfoPtrToJSlotInfo(JNIEnv *fnv, donst CK_SLOT_INFO_PTR dkpSlotInfo);
jobjfdt dkTokfnInfoPtrToJTokfnInfo(JNIEnv *fnv, donst CK_TOKEN_INFO_PTR dkpTokfnInfo);
jobjfdt dkMfdibnismInfoPtrToJMfdibnismInfo(JNIEnv *fnv, donst CK_MECHANISM_INFO_PTR dkpMfdibnismInfo);

/* dffinf vbribblfs */

jfifldID pNbtivfDbtbID;
jfifldID mfdi_mfdibnismID;
jfifldID mfdi_pPbrbmftfrID;

jdlbss jBytfArrbyClbss;
jdlbss jLongClbss;

JbvbVM* jvm = NULL;

JNIEXPORT jint JNICALL JNI_OnLobd(JbvbVM *vm, void *rfsfrvfd) {
    jvm = vm;
    rfturn JNI_VERSION_1_4;
}

/* ************************************************************************** */
/* Tif nbtivf implfmfntbtion of tif mftiods of tif PKCS11Implfmfntbtion dlbss */
/* ************************************************************************** */

/*
 * Tiis mftiod is usfd to do stbtid initiblizbtion. Tiis mftiod is stbtid bnd
 * syndironizfd. Summbry: usf tiis mftiod likf b stbtid initiblizbtion blodk.
 *
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    initiblizfLibrbry
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_initiblizfLibrbry
(JNIEnv *fnv, jdlbss tiisClbss)
{
#ifndff NO_CALLBACKS
    if (notifyListLodk == NULL) {
        notifyListLodk = drfbtfLodkObjfdt(fnv);
    }
#fndif

    prffftdiFiflds(fnv, tiisClbss);
}

jdlbss fftdiClbss(JNIEnv *fnv, donst dibr *nbmf) {
    jdlbss tmpClbss = (*fnv)->FindClbss(fnv, nbmf);
    if (tmpClbss == NULL) { rfturn NULL; }
    rfturn (*fnv)->NfwGlobblRff(fnv, tmpClbss);
}

void prffftdiFiflds(JNIEnv *fnv, jdlbss tiisClbss) {
    jdlbss tmpClbss;

    /* PKCS11 */
    pNbtivfDbtbID = (*fnv)->GftFifldID(fnv, tiisClbss, "pNbtivfDbtb", "J");
    if (pNbtivfDbtbID == NULL) { rfturn; }

    /* CK_MECHANISM */
    tmpClbss = (*fnv)->FindClbss(fnv, CLASS_MECHANISM);
    if (tmpClbss == NULL) { rfturn; }
    mfdi_mfdibnismID = (*fnv)->GftFifldID(fnv, tmpClbss, "mfdibnism", "J");
    if (mfdi_mfdibnismID == NULL) { rfturn; }
    mfdi_pPbrbmftfrID = (*fnv)->GftFifldID(fnv, tmpClbss, "pPbrbmftfr",
                                           "Ljbvb/lbng/Objfdt;");
    if (mfdi_pPbrbmftfrID == NULL) { rfturn; }
    jBytfArrbyClbss = fftdiClbss(fnv, "[B");
    if (jBytfArrbyClbss == NULL) { rfturn; }
    jLongClbss = fftdiClbss(fnv, "jbvb/lbng/Long");
}

/* Tiis mftiod is dfsignfd to do b dlfbn-up. It rflfbsfs bll globbl rfsourdfs
 * of tiis librbry. By now, tiis fundtion is not dbllfd. Cblling from
 * JNI_OnUnlobd would bf bn option, but somf VMs do not support JNI_OnUnlobd.
 *
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    finblizfLibrbry
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL
Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_finblizfLibrbry
(JNIEnv *fnv, jdlbss tiisClbss)
{
/* XXX
    * rfmovf bll lfft lists bnd rflfbsf tif rfsourdfs bnd tif lodk
     * objfdts tibt syndironiz bddfss to tifsf lists.
     *
    rfmovfAllModulfEntrifs(fnv);
    if (modulfListHfbd == NULL) { * difdk, if wf rfmovfd tif lbst bdtivf modulf *
        * rfmovf blso tif modulfListLodk, it is no longfr usfd *
        if (modulfListLodk != NULL) {
            dfstroyLodkObjfdt(fnv, modulfListLodk);
            modulfListLodk = NULL;
        }
#ifndff NO_CALLBACKS
        * rfmovf bll lfft notify dbllbbdk fntrifs *
        wiilf (rfmovfFirstNotifyEntry(fnv));
        * rfmovf blso tif notifyListLodk, it is no longfr usfd *
        if (notifyListLodk != NULL) {
            dfstroyLodkObjfdt(fnv, notifyListLodk);
            notifyListLodk = NULL;
        }
        if (jInitArgsObjfdt != NULL) {
            (*fnv)->DflftfGlobblRff(fnv, jInitArgsObjfdt);
        }
        if (dkpGlobblInitArgs != NULL_PTR) {
            frff(dkpGlobblInitArgs);
        }
#fndif * NO_CALLBACKS *
    }
*/
}

#ifdff P11_ENABLE_C_INITIALIZE
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_Initiblizf
 * Signbturf: (Ljbvb/lbng/Objfdt;)V
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jobjfdt jInitArgs           CK_VOID_PTR pInitArgs
 */
JNIEXPORT void JNICALL
Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1Initiblizf
(JNIEnv *fnv, jobjfdt obj, jobjfdt jInitArgs)
{
    /*
     * Initblizf Cryptoki
     */
    CK_C_INITIALIZE_ARGS_PTR dkpInitArgs;
    CK_RV rv;
    CK_FUNCTION_LIST_PTR dkpFundtions;

    TRACE0("DEBUG: initiblizing modulf... ");

    dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) {
        TRACE0("fbilfd gftting modulf fntry");
        rfturn;
    }

    dkpInitArgs = (jInitArgs != NULL)
                ? mbkfCKInitArgsAdbptfr(fnv, jInitArgs)
                : NULL_PTR;

    rv = (*dkpFundtions->C_Initiblizf)(dkpInitArgs);

    frff(dkpInitArgs);

    if (dkAssfrtRfturnVblufOK(fnv, rv) != CK_ASSERT_OK) { rfturn; }

    TRACE0("FINISHED\n");
}
#fndif

#ifdff P11_ENABLE_C_FINALIZE
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_Finblizf
 * Signbturf: (Ljbvb/lbng/Objfdt;)V
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jobjfdt jRfsfrvfd           CK_VOID_PTR pRfsfrvfd
 */
JNIEXPORT void JNICALL
Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1Finblizf
(JNIEnv *fnv, jobjfdt obj, jobjfdt jRfsfrvfd)
{
    /*
     * Finblizf Cryptoki
     */
    CK_VOID_PTR dkpRfsfrvfd;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn; }

    dkpRfsfrvfd = jObjfdtToCKVoidPtr(jRfsfrvfd);

    rv = (*dkpFundtions->C_Finblizf)(dkpRfsfrvfd);
    if (dkAssfrtRfturnVblufOK(fnv, rv) != CK_ASSERT_OK) { rfturn; }
}
#fndif

#ifdff P11_ENABLE_C_GETINFO
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_GftInfo
 * Signbturf: ()Lsun/sfdurity/pkds11/wrbppfr/CK_INFO;
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @rfturn  jobjfdt jInfoObjfdt         CK_INFO_PTR pInfo
 */
JNIEXPORT jobjfdt JNICALL
Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1GftInfo
(JNIEnv *fnv, jobjfdt obj)
{
    CK_INFO dkLibInfo;
    jobjfdt jInfoObjfdt=NULL;
    CK_RV rv;
    CK_FUNCTION_LIST_PTR dkpFundtions;
    mfmsft(&dkLibInfo, 0, sizfof(CK_INFO));

    dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn NULL; }

    rv = (*dkpFundtions->C_GftInfo)(&dkLibInfo);
    if (dkAssfrtRfturnVblufOK(fnv, rv) == CK_ASSERT_OK) {
        jInfoObjfdt = dkInfoPtrToJInfo(fnv, &dkLibInfo);
    }
    rfturn jInfoObjfdt ;
}

/*
 * donvfrts b pointfr to b CK_INFO strudturf into b Jbvb CK_INFO Objfdt.
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to drfbtf tif nfw Jbvb objfdt
 * @pbrbm dkpInfo - tif pointfr to tif CK_INFO strudturf
 * @rfturn - tif nfw Jbvb CK_INFO objfdt
 */
jobjfdt dkInfoPtrToJInfo(JNIEnv *fnv, donst CK_INFO_PTR dkpInfo)
{
    jdlbss jInfoClbss;
    jmftiodID jCtrId;
    jobjfdt jInfoObjfdt;
    jobjfdt jCryptokiVfr;
    jdibrArrby jVfndor;
    jlong jFlbgs;
    jdibrArrby jLibrbryDfsd;
    jobjfdt jLibrbryVfr;

    /* lobd CK_INFO dlbss */
    jInfoClbss = (*fnv)->FindClbss(fnv, CLASS_INFO);
    if (jInfoClbss == NULL) { rfturn NULL; };

    /* lobd CK_INFO donstrudtor */
    jCtrId = (*fnv)->GftMftiodID
      (fnv, jInfoClbss, "<init>",
       "(Lsun/sfdurity/pkds11/wrbppfr/CK_VERSION;[CJ[CLsun/sfdurity/pkds11/wrbppfr/CK_VERSION;)V");
    if (jCtrId == NULL) { rfturn NULL; }

    /* prfp bll fiflds */
    jCryptokiVfr = dkVfrsionPtrToJVfrsion(fnv, &(dkpInfo->dryptokiVfrsion));
    if (jCryptokiVfr == NULL) { rfturn NULL; }
    jVfndor =
      dkUTF8CibrArrbyToJCibrArrby(fnv, &(dkpInfo->mbnufbdturfrID[0]), 32);
    if (jVfndor == NULL) { rfturn NULL; }
    jFlbgs = dkULongToJLong(dkpInfo->flbgs);
    jLibrbryDfsd =
      dkUTF8CibrArrbyToJCibrArrby(fnv, &(dkpInfo->librbryDfsdription[0]), 32);
    if (jLibrbryDfsd == NULL) { rfturn NULL; }
    jLibrbryVfr = dkVfrsionPtrToJVfrsion(fnv, &(dkpInfo->librbryVfrsion));
    if (jLibrbryVfr == NULL) { rfturn NULL; }

    /* drfbtf nfw CK_INFO objfdt */
    jInfoObjfdt = (*fnv)->NfwObjfdt(fnv, jInfoClbss, jCtrId, jCryptokiVfr,
                                    jVfndor, jFlbgs, jLibrbryDfsd, jLibrbryVfr);
    if (jInfoObjfdt == NULL) { rfturn NULL; }

    /* frff lodbl rfffrfndfs */
    (*fnv)->DflftfLodblRff(fnv, jInfoClbss);
    (*fnv)->DflftfLodblRff(fnv, jCryptokiVfr);
    (*fnv)->DflftfLodblRff(fnv, jVfndor);
    (*fnv)->DflftfLodblRff(fnv, jLibrbryDfsd);
    (*fnv)->DflftfLodblRff(fnv, jLibrbryVfr);

    rfturn jInfoObjfdt ;
}
#fndif

#ifdff P11_ENABLE_C_GETSLOTLIST
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_GftSlotList
 * Signbturf: (Z)[J
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jboolfbn jTokfnPrfsfnt      CK_BBOOL tokfnPrfsfnt
 * @rfturn  jlongArrby jSlotList        CK_SLOT_ID_PTR pSlotList
 *                                      CK_ULONG_PTR pulCount
 */
JNIEXPORT jlongArrby JNICALL
Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1GftSlotList
(JNIEnv *fnv, jobjfdt obj, jboolfbn jTokfnPrfsfnt)
{
    CK_ULONG dkTokfnNumbfr;
    CK_SLOT_ID_PTR dkpSlotList;
    CK_BBOOL dkTokfnPrfsfnt;
    jlongArrby jSlotList = NULL;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn NULL; }

    dkTokfnPrfsfnt = jBoolfbnToCKBBool(jTokfnPrfsfnt);

    rv = (*dkpFundtions->C_GftSlotList)(dkTokfnPrfsfnt, NULL_PTR,
                                        &dkTokfnNumbfr);
    if (dkAssfrtRfturnVblufOK(fnv, rv) != CK_ASSERT_OK) { rfturn NULL ; }

    dkpSlotList = (CK_SLOT_ID_PTR) mbllod(dkTokfnNumbfr * sizfof(CK_SLOT_ID));
    if (dkpSlotList == NULL) {
        tirowOutOfMfmoryError(fnv, 0);
        rfturn NULL;
    }

    rv = (*dkpFundtions->C_GftSlotList)(dkTokfnPrfsfnt, dkpSlotList,
                                        &dkTokfnNumbfr);
    if (dkAssfrtRfturnVblufOK(fnv, rv) == CK_ASSERT_OK) {
        jSlotList = dkULongArrbyToJLongArrby(fnv, dkpSlotList, dkTokfnNumbfr);
    }
    frff(dkpSlotList);

    rfturn jSlotList ;
}
#fndif

#ifdff P11_ENABLE_C_GETSLOTINFO
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_GftSlotInfo
 * Signbturf: (J)Lsun/sfdurity/pkds11/wrbppfr/CK_SLOT_INFO;
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSlotID               CK_SLOT_ID slotID
 * @rfturn  jobjfdt jSlotInfoObjfdt     CK_SLOT_INFO_PTR pInfo
 */
JNIEXPORT jobjfdt JNICALL
Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1GftSlotInfo
(JNIEnv *fnv, jobjfdt obj, jlong jSlotID)
{
    CK_SLOT_ID dkSlotID;
    CK_SLOT_INFO dkSlotInfo;
    jobjfdt jSlotInfoObjfdt=NULL;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn NULL; }

    dkSlotID = jLongToCKULong(jSlotID);

    rv = (*dkpFundtions->C_GftSlotInfo)(dkSlotID, &dkSlotInfo);
    if (dkAssfrtRfturnVblufOK(fnv, rv) == CK_ASSERT_OK) {
        jSlotInfoObjfdt = dkSlotInfoPtrToJSlotInfo(fnv, &dkSlotInfo);
    }
    rfturn jSlotInfoObjfdt;
}

/*
 * donvfrts b pointfr to b CK_SLOT_INFO strudturf into b Jbvb CK_SLOT_INFO
 * Objfdt.
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to drfbtf tif nfw Jbvb objfdt
 * @pbrbm dkpSlotInfo - tif pointfr to tif CK_SLOT_INFO strudturf
 * @rfturn - tif nfw Jbvb CK_SLOT_INFO objfdt
 */
jobjfdt
dkSlotInfoPtrToJSlotInfo
(JNIEnv *fnv, donst CK_SLOT_INFO_PTR dkpSlotInfo)
{
    jdlbss jSlotInfoClbss;
    jmftiodID jCtrId;
    jobjfdt jSlotInfoObjfdt;
    jdibrArrby jSlotDfsd;
    jdibrArrby jVfndor;
    jlong jFlbgs;
    jobjfdt jHbrdwbrfVfr;
    jobjfdt jFirmwbrfVfr;

    /* lobd CK_SLOT_INFO dlbss */
    jSlotInfoClbss = (*fnv)->FindClbss(fnv, CLASS_SLOT_INFO);
    if (jSlotInfoClbss == NULL) { rfturn NULL; };

    /* lobd CK_SLOT_INFO donstrudtor */
    jCtrId = (*fnv)->GftMftiodID
      (fnv, jSlotInfoClbss, "<init>",
       "([C[CJLsun/sfdurity/pkds11/wrbppfr/CK_VERSION;Lsun/sfdurity/pkds11/wrbppfr/CK_VERSION;)V");
    if (jCtrId == NULL) { rfturn NULL; }

    /* prfp bll fiflds */
    jSlotDfsd =
      dkUTF8CibrArrbyToJCibrArrby(fnv, &(dkpSlotInfo->slotDfsdription[0]), 64);
    if (jSlotDfsd == NULL) { rfturn NULL; }
    jVfndor =
      dkUTF8CibrArrbyToJCibrArrby(fnv, &(dkpSlotInfo->mbnufbdturfrID[0]), 32);
    if (jVfndor == NULL) { rfturn NULL; }
    jFlbgs = dkULongToJLong(dkpSlotInfo->flbgs);
    jHbrdwbrfVfr = dkVfrsionPtrToJVfrsion(fnv, &(dkpSlotInfo->ibrdwbrfVfrsion));
    if (jHbrdwbrfVfr == NULL) { rfturn NULL; }
    jFirmwbrfVfr = dkVfrsionPtrToJVfrsion(fnv, &(dkpSlotInfo->firmwbrfVfrsion));
    if (jFirmwbrfVfr == NULL) { rfturn NULL; }

    /* drfbtf nfw CK_SLOT_INFO objfdt */
    jSlotInfoObjfdt = (*fnv)->NfwObjfdt
      (fnv, jSlotInfoClbss, jCtrId, jSlotDfsd, jVfndor, jFlbgs,
       jHbrdwbrfVfr, jFirmwbrfVfr);
    if (jSlotInfoObjfdt == NULL) { rfturn NULL; }

    /* frff lodbl rfffrfndfs */
    (*fnv)->DflftfLodblRff(fnv, jSlotInfoClbss);
    (*fnv)->DflftfLodblRff(fnv, jSlotDfsd);
    (*fnv)->DflftfLodblRff(fnv, jVfndor);
    (*fnv)->DflftfLodblRff(fnv, jHbrdwbrfVfr);
    (*fnv)->DflftfLodblRff(fnv, jFirmwbrfVfr);

    rfturn jSlotInfoObjfdt ;
}

#fndif

#ifdff P11_ENABLE_C_GETTOKENINFO
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_GftTokfnInfo
 * Signbturf: (J)Lsun/sfdurity/pkds11/wrbppfr/CK_TOKEN_INFO;
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSlotID               CK_SLOT_ID slotID
 * @rfturn  jobjfdt jInfoTokfnObjfdt    CK_TOKEN_INFO_PTR pInfo
 */
JNIEXPORT jobjfdt JNICALL
Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1GftTokfnInfo
(JNIEnv *fnv, jobjfdt obj, jlong jSlotID)
{
    CK_SLOT_ID dkSlotID;
    CK_TOKEN_INFO dkTokfnInfo;
    jobjfdt jInfoTokfnObjfdt = NULL;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn NULL; }

    dkSlotID = jLongToCKULong(jSlotID);

    rv = (*dkpFundtions->C_GftTokfnInfo)(dkSlotID, &dkTokfnInfo);
    if (dkAssfrtRfturnVblufOK(fnv, rv) == CK_ASSERT_OK) {
        jInfoTokfnObjfdt = dkTokfnInfoPtrToJTokfnInfo(fnv, &dkTokfnInfo);
    }
    rfturn jInfoTokfnObjfdt ;
}

/*
 * donvfrts b pointfr to b CK_TOKEN_INFO strudturf into b Jbvb CK_TOKEN_INFO
 * Objfdt.
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to drfbtf tif nfw Jbvb objfdt
 * @pbrbm dkpTokfnInfo - tif pointfr to tif CK_TOKEN_INFO strudturf
 * @rfturn - tif nfw Jbvb CK_TOKEN_INFO objfdt
 */
jobjfdt
dkTokfnInfoPtrToJTokfnInfo
(JNIEnv *fnv, donst CK_TOKEN_INFO_PTR dkpTokfnInfo)
{
    jdlbss jTokfnInfoClbss;
    jmftiodID jCtrId;
    jobjfdt jTokfnInfoObjfdt;
    jdibrArrby jLbbfl;
    jdibrArrby jVfndor;
    jdibrArrby jModfl;
    jdibrArrby jSfriblNo;
    jlong jFlbgs;
    jlong jMbxSnCnt;
    jlong jSnCnt;
    jlong jMbxRwSnCnt;
    jlong jRwSnCnt;
    jlong jMbxPinLfn;
    jlong jMinPinLfn;
    jlong jTotblPubMfm;
    jlong jFrffPubMfm;
    jlong jTotblPrivMfm;
    jlong jFrffPrivMfm;
    jobjfdt jHbrdwbrfVfr;
    jobjfdt jFirmwbrfVfr;
    jdibrArrby jUtdTimf;

    /* lobd CK_TOKEN_INFO dlbss */
    jTokfnInfoClbss = (*fnv)->FindClbss(fnv, CLASS_TOKEN_INFO);
    if (jTokfnInfoClbss == NULL)  { rfturn NULL; };

    /* lobd CK_TOKEN_INFO donstrudtor */
    jCtrId = (*fnv)->GftMftiodID
      (fnv, jTokfnInfoClbss, "<init>",
       "([C[C[C[CJJJJJJJJJJJLsun/sfdurity/pkds11/wrbppfr/CK_VERSION;Lsun/sfdurity/pkds11/wrbppfr/CK_VERSION;[C)V");
    if (jCtrId == NULL)  { rfturn NULL; };

    /* prfp bll fiflds */
    jLbbfl = dkUTF8CibrArrbyToJCibrArrby(fnv, &(dkpTokfnInfo->lbbfl[0]), 32);
    if (jLbbfl == NULL)  { rfturn NULL; };
    jVfndor =
      dkUTF8CibrArrbyToJCibrArrby(fnv, &(dkpTokfnInfo->mbnufbdturfrID[0]), 32);
    if (jVfndor == NULL)  { rfturn NULL; };
    jModfl = dkUTF8CibrArrbyToJCibrArrby(fnv, &(dkpTokfnInfo->modfl[0]), 16);
    if (jModfl == NULL)  { rfturn NULL; };
    jSfriblNo =
      dkUTF8CibrArrbyToJCibrArrby(fnv, &(dkpTokfnInfo->sfriblNumbfr[0]), 16);
    if (jSfriblNo == NULL)  { rfturn NULL; };
    jFlbgs = dkULongToJLong(dkpTokfnInfo->flbgs);
    jMbxSnCnt = dkULongSpfdiblToJLong(dkpTokfnInfo->ulMbxSfssionCount);
    jSnCnt = dkULongSpfdiblToJLong(dkpTokfnInfo->ulSfssionCount);
    jMbxRwSnCnt = dkULongSpfdiblToJLong(dkpTokfnInfo->ulMbxRwSfssionCount);
    jRwSnCnt = dkULongSpfdiblToJLong(dkpTokfnInfo->ulRwSfssionCount);
    jMbxPinLfn = dkULongToJLong(dkpTokfnInfo->ulMbxPinLfn);
    jMinPinLfn = dkULongToJLong(dkpTokfnInfo->ulMinPinLfn);
    jTotblPubMfm = dkULongSpfdiblToJLong(dkpTokfnInfo->ulTotblPublidMfmory);
    jFrffPubMfm = dkULongSpfdiblToJLong(dkpTokfnInfo->ulFrffPublidMfmory);
    jTotblPrivMfm = dkULongSpfdiblToJLong(dkpTokfnInfo->ulTotblPrivbtfMfmory);
    jFrffPrivMfm = dkULongSpfdiblToJLong(dkpTokfnInfo->ulFrffPrivbtfMfmory);
    jHbrdwbrfVfr =
      dkVfrsionPtrToJVfrsion(fnv, &(dkpTokfnInfo->ibrdwbrfVfrsion));
    if (jHbrdwbrfVfr == NULL) { rfturn NULL; }
    jFirmwbrfVfr =
      dkVfrsionPtrToJVfrsion(fnv, &(dkpTokfnInfo->firmwbrfVfrsion));
    if (jFirmwbrfVfr == NULL) { rfturn NULL; }
    jUtdTimf =
      dkUTF8CibrArrbyToJCibrArrby(fnv, &(dkpTokfnInfo->utdTimf[0]), 16);
    if (jUtdTimf == NULL) { rfturn NULL; }

    /* drfbtf nfw CK_TOKEN_INFO objfdt */
    jTokfnInfoObjfdt =
      (*fnv)->NfwObjfdt(fnv, jTokfnInfoClbss, jCtrId, jLbbfl, jVfndor, jModfl,
                        jSfriblNo, jFlbgs,
                        jMbxSnCnt, jSnCnt, jMbxRwSnCnt, jRwSnCnt,
                        jMbxPinLfn, jMinPinLfn,
                        jTotblPubMfm, jFrffPubMfm, jTotblPrivMfm, jFrffPrivMfm,
                        jHbrdwbrfVfr, jFirmwbrfVfr, jUtdTimf);
    if (jTokfnInfoObjfdt == NULL) { rfturn NULL; }

    /* frff lodbl rfffrfndfs */
    (*fnv)->DflftfLodblRff(fnv, jTokfnInfoClbss);
    (*fnv)->DflftfLodblRff(fnv, jLbbfl);
    (*fnv)->DflftfLodblRff(fnv, jVfndor);
    (*fnv)->DflftfLodblRff(fnv, jModfl);
    (*fnv)->DflftfLodblRff(fnv, jSfriblNo);
    (*fnv)->DflftfLodblRff(fnv, jHbrdwbrfVfr);
    (*fnv)->DflftfLodblRff(fnv, jFirmwbrfVfr);

    rfturn jTokfnInfoObjfdt ;
}
#fndif

#ifdff P11_ENABLE_C_WAITFORSLOTEVENT
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_WbitForSlotEvfnt
 * Signbturf: (JLjbvb/lbng/Objfdt;)J
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jFlbgs                CK_FLAGS flbgs
 * @pbrbm   jobjfdt jRfsfrvfd           CK_VOID_PTR pRfsfrvfd
 * @rfturn  jlong jSlotID               CK_SLOT_ID_PTR pSlot
 */
JNIEXPORT jlong JNICALL
Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1WbitForSlotEvfnt
(JNIEnv *fnv, jobjfdt obj, jlong jFlbgs, jobjfdt jRfsfrvfd)
{
    CK_FLAGS dkFlbgs;
    CK_SLOT_ID dkSlotID;
    jlong jSlotID = 0L;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn 0L; }

    dkFlbgs = jLongToCKULong(jFlbgs);

    rv = (*dkpFundtions->C_WbitForSlotEvfnt)(dkFlbgs, &dkSlotID, NULL_PTR);
    if (dkAssfrtRfturnVblufOK(fnv, rv) == CK_ASSERT_OK) {
        jSlotID = dkULongToJLong(dkSlotID);
    }

    rfturn jSlotID ;
}
#fndif

#ifdff P11_ENABLE_C_GETMECHANISMLIST
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_GftMfdibnismList
 * Signbturf: (J)[J
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSlotID               CK_SLOT_ID slotID
 * @rfturn  jlongArrby jMfdibnismList   CK_MECHANISM_TYPE_PTR pMfdibnismList
 *                                      CK_ULONG_PTR pulCount
 */
JNIEXPORT jlongArrby JNICALL
Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1GftMfdibnismList
(JNIEnv *fnv, jobjfdt obj, jlong jSlotID)
{
    CK_SLOT_ID dkSlotID;
    CK_ULONG dkMfdibnismNumbfr;
    CK_MECHANISM_TYPE_PTR dkpMfdibnismList;
    jlongArrby jMfdibnismList = NULL;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn NULL; }

    dkSlotID = jLongToCKULong(jSlotID);

    rv = (*dkpFundtions->C_GftMfdibnismList)(dkSlotID, NULL_PTR,
                                             &dkMfdibnismNumbfr);
    if (dkAssfrtRfturnVblufOK(fnv, rv) != CK_ASSERT_OK) { rfturn NULL ; }

    dkpMfdibnismList = (CK_MECHANISM_TYPE_PTR)
      mbllod(dkMfdibnismNumbfr * sizfof(CK_MECHANISM_TYPE));
    if (dkpMfdibnismList == NULL) {
        tirowOutOfMfmoryError(fnv, 0);
        rfturn NULL;
    }

    rv = (*dkpFundtions->C_GftMfdibnismList)(dkSlotID, dkpMfdibnismList,
                                             &dkMfdibnismNumbfr);
    if (dkAssfrtRfturnVblufOK(fnv, rv) == CK_ASSERT_OK) {
        jMfdibnismList = dkULongArrbyToJLongArrby(fnv, dkpMfdibnismList,
                                                  dkMfdibnismNumbfr);
    }
    frff(dkpMfdibnismList);

    rfturn jMfdibnismList ;
}
#fndif

#ifdff P11_ENABLE_C_GETMECHANISMINFO
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_GftMfdibnismInfo
 * Signbturf: (JJ)Lsun/sfdurity/pkds11/wrbppfr/CK_MECHANISM_INFO;
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSlotID               CK_SLOT_ID slotID
 * @pbrbm   jlong jTypf                 CK_MECHANISM_TYPE typf
 * @rfturn  jobjfdt jMfdibnismInfo      CK_MECHANISM_INFO_PTR pInfo
 */
JNIEXPORT jobjfdt JNICALL
Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1GftMfdibnismInfo
(JNIEnv *fnv, jobjfdt obj, jlong jSlotID, jlong jTypf)
{
    CK_SLOT_ID dkSlotID;
    CK_MECHANISM_TYPE dkMfdibnismTypf;
    CK_MECHANISM_INFO dkMfdibnismInfo;
    jobjfdt jMfdibnismInfo = NULL;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn NULL; }

    dkSlotID = jLongToCKULong(jSlotID);
    dkMfdibnismTypf = jLongToCKULong(jTypf);

    rv = (*dkpFundtions->C_GftMfdibnismInfo)(dkSlotID, dkMfdibnismTypf,
                                             &dkMfdibnismInfo);
    if (dkAssfrtRfturnVblufOK(fnv, rv) == CK_ASSERT_OK) {
        jMfdibnismInfo = dkMfdibnismInfoPtrToJMfdibnismInfo(fnv, &dkMfdibnismInfo);
    }
    rfturn jMfdibnismInfo ;
}

/*
 * donvfrts b pointfr to b CK_MECHANISM_INFO strudturf into b Jbvb
 * CK_MECHANISM_INFO Objfdt.
 *
 * @pbrbm fnv - usfd to dbll JNI funktions to drfbtf tif nfw Jbvb objfdt
 * @pbrbm dkpMfdibnismInfo - tif pointfr to tif CK_MECHANISM_INFO strudturf
 * @rfturn - tif nfw Jbvb CK_MECHANISM_INFO objfdt
 */
jobjfdt
dkMfdibnismInfoPtrToJMfdibnismInfo
(JNIEnv *fnv, donst CK_MECHANISM_INFO_PTR dkpMfdibnismInfo)
{

    jdlbss jMfdibnismInfoClbss;
    jmftiodID jCtrId;
    jobjfdt jMfdibnismInfoObjfdt;
    jlong jMinKfySizf;
    jlong jMbxKfySizf;
    jlong jFlbgs;

    /* lobd CK_MECHANISM_INFO dlbss */
    jMfdibnismInfoClbss = (*fnv)->FindClbss(fnv, CLASS_MECHANISM_INFO);
    if (jMfdibnismInfoClbss == NULL) { rfturn NULL; };

    /* lobd CK_MECHANISM_INFO donstrudtor */
    jCtrId = (*fnv)->GftMftiodID(fnv, jMfdibnismInfoClbss, "<init>", "(JJJ)V");
    if (jCtrId == NULL) { rfturn NULL; };

    /* prfp bll fiflds */
    jMinKfySizf = dkULongToJLong(dkpMfdibnismInfo->ulMinKfySizf);
    jMbxKfySizf = dkULongToJLong(dkpMfdibnismInfo->ulMbxKfySizf);
    jFlbgs = dkULongToJLong(dkpMfdibnismInfo->flbgs);

    /* drfbtf nfw CK_MECHANISM_INFO objfdt */
    jMfdibnismInfoObjfdt = (*fnv)->NfwObjfdt(fnv, jMfdibnismInfoClbss, jCtrId,
                                             jMinKfySizf, jMbxKfySizf, jFlbgs);
    if (jMfdibnismInfoObjfdt == NULL) { rfturn NULL; };

    /* frff lodbl rfffrfndfs */
    (*fnv)->DflftfLodblRff(fnv, jMfdibnismInfoClbss);

    rfturn jMfdibnismInfoObjfdt ;
}
#fndif

#ifdff P11_ENABLE_C_INITTOKEN
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_InitTokfn
 * Signbturf: (J[C[C)V
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSlotID               CK_SLOT_ID slotID
 * @pbrbm   jdibrArrby jPin             CK_CHAR_PTR pPin
 *                                      CK_ULONG ulPinLfn
 * @pbrbm   jdibrArrby jLbbfl           CK_UTF8CHAR_PTR pLbbfl
 */
JNIEXPORT void JNICALL
Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1InitTokfn
(JNIEnv *fnv, jobjfdt obj, jlong jSlotID, jdibrArrby jPin, jdibrArrby jLbbfl)
{
    CK_SLOT_ID dkSlotID;
    CK_CHAR_PTR dkpPin = NULL_PTR;
    CK_UTF8CHAR_PTR dkpLbbfl = NULL_PTR;
    CK_ULONG dkPinLfngti;
    CK_ULONG dkLbbflLfngti;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn; }

    dkSlotID = jLongToCKULong(jSlotID);
    jCibrArrbyToCKCibrArrby(fnv, jPin, &dkpPin, &dkPinLfngti);
    if ((*fnv)->ExdfptionCifdk(fnv)) { rfturn; }
    /* dkLbbflLfngti <= 32 !!! */
    jCibrArrbyToCKUTF8CibrArrby(fnv, jLbbfl, &dkpLbbfl, &dkLbbflLfngti);
    if ((*fnv)->ExdfptionCifdk(fnv)) {
        frff(dkpPin);
        rfturn;
    }

    rv = (*dkpFundtions->C_InitTokfn)(dkSlotID, dkpPin, dkPinLfngti, dkpLbbfl);
    TRACE1("InitTokfn rfturn dodf: %d", rv);

    frff(dkpPin);
    frff(dkpLbbfl);

    if (dkAssfrtRfturnVblufOK(fnv, rv) != CK_ASSERT_OK) { rfturn; }
}
#fndif

#ifdff P11_ENABLE_C_INITPIN
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_InitPIN
 * Signbturf: (J[C)V
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf        CK_SESSION_HANDLE
 * @pbrbm   jdibrArrby jPin             CK_CHAR_PTR pPin
 *                                      CK_ULONG ulPinLfn
 */
JNIEXPORT void JNICALL
Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1InitPIN
(JNIEnv *fnv, jobjfdt obj, jlong jSfssionHbndlf, jdibrArrby jPin)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    CK_CHAR_PTR dkpPin = NULL_PTR;
    CK_ULONG dkPinLfngti;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn; }

    dkSfssionHbndlf = jLongToCKULong(jSfssionHbndlf);
    jCibrArrbyToCKCibrArrby(fnv, jPin, &dkpPin, &dkPinLfngti);
    if ((*fnv)->ExdfptionCifdk(fnv)) { rfturn; }

    rv = (*dkpFundtions->C_InitPIN)(dkSfssionHbndlf, dkpPin, dkPinLfngti);

    frff(dkpPin);

    if (dkAssfrtRfturnVblufOK(fnv, rv) != CK_ASSERT_OK) { rfturn; }
}
#fndif

#ifdff P11_ENABLE_C_SETPIN
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_SftPIN
 * Signbturf: (J[C[C)V
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf        CK_SESSION_HANDLE iSfssion
 * @pbrbm   jdibrArrby jOldPin          CK_CHAR_PTR pOldPin
 *                                      CK_ULONG ulOldLfn
 * @pbrbm   jdibrArrby jNfwPin          CK_CHAR_PTR pNfwPin
 *                                      CK_ULONG ulNfwLfn
 */
JNIEXPORT void JNICALL
Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1SftPIN
(JNIEnv *fnv, jobjfdt obj, jlong jSfssionHbndlf, jdibrArrby jOldPin,
jdibrArrby jNfwPin)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    CK_CHAR_PTR dkpOldPin = NULL_PTR;
    CK_CHAR_PTR dkpNfwPin = NULL_PTR;
    CK_ULONG dkOldPinLfngti;
    CK_ULONG dkNfwPinLfngti;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn; }

    dkSfssionHbndlf = jLongToCKULong(jSfssionHbndlf);
    jCibrArrbyToCKCibrArrby(fnv, jOldPin, &dkpOldPin, &dkOldPinLfngti);
    if ((*fnv)->ExdfptionCifdk(fnv)) { rfturn; }
    jCibrArrbyToCKCibrArrby(fnv, jNfwPin, &dkpNfwPin, &dkNfwPinLfngti);
    if ((*fnv)->ExdfptionCifdk(fnv)) {
        frff(dkpOldPin);
        rfturn;
    }

    rv = (*dkpFundtions->C_SftPIN)(dkSfssionHbndlf, dkpOldPin, dkOldPinLfngti,
                                   dkpNfwPin, dkNfwPinLfngti);

    frff(dkpOldPin);
    frff(dkpNfwPin);

    if (dkAssfrtRfturnVblufOK(fnv, rv) != CK_ASSERT_OK) { rfturn; }
}
#fndif
