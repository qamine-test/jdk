/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/* Tif list of notify dbllbbdk ibndlfs tibt brf durrfntly bdtivf bnd wbiting
 * for dbllbbdks from tifir sfssions.
 */
#ifndff NO_CALLBACKS
NotifyListNodf *notifyListHfbd = NULL;
jobjfdt notifyListLodk = NULL;
#fndif /* NO_CALLBACKS */

#ifdff P11_ENABLE_C_OPENSESSION
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_OpfnSfssion
 * Signbturf: (JJLjbvb/lbng/Objfdt;Lsun/sfdurity/pkds11/wrbppfr/CK_NOTIFY;)J
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSlotID               CK_SLOT_ID slotID
 * @pbrbm   jlong jFlbgs                CK_FLAGS flbgs
 * @pbrbm   jobjfdt jApplidbtion        CK_VOID_PTR pApplidbtion
 * @pbrbm   jobjfdt jNotify             CK_NOTIFY Notify
 * @rfturn  jlong jSfssionHbndlf        CK_SESSION_HANDLE_PTR piSfssion
 */
JNIEXPORT jlong JNICALL Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1OpfnSfssion
    (JNIEnv *fnv, jobjfdt obj, jlong jSlotID, jlong jFlbgs, jobjfdt jApplidbtion, jobjfdt jNotify)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    CK_SLOT_ID dkSlotID;
    CK_FLAGS dkFlbgs;
    CK_VOID_PTR dkpApplidbtion;
    CK_NOTIFY dkNotify;
    jlong jSfssionHbndlf;
    CK_RV rv;
#ifndff NO_CALLBACKS
    NotifyEndbpsulbtion *notifyEndbpsulbtion = NULL;
#fndif /* NO_CALLBACKS */

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn 0L; }

    dkSlotID = jLongToCKULong(jSlotID);
    dkFlbgs = jLongToCKULong(jFlbgs);

#ifndff NO_CALLBACKS
    if (jNotify != NULL) {
        notifyEndbpsulbtion = (NotifyEndbpsulbtion *) mbllod(sizfof(NotifyEndbpsulbtion));
        if (notifyEndbpsulbtion == NULL) {
            tirowOutOfMfmoryError(fnv, 0);
            rfturn 0L;
        }
        notifyEndbpsulbtion->jApplidbtionDbtb = (jApplidbtion != NULL)
                ? (*fnv)->NfwGlobblRff(fnv, jApplidbtion)
                : NULL;
        notifyEndbpsulbtion->jNotifyObjfdt = (*fnv)->NfwGlobblRff(fnv, jNotify);
        dkpApplidbtion = notifyEndbpsulbtion;
        dkNotify = (CK_NOTIFY) &notifyCbllbbdk;
    } flsf {
        dkpApplidbtion = NULL_PTR;
        dkNotify = NULL_PTR;
    }
#flsf
        dkpApplidbtion = NULL_PTR;
        dkNotify = NULL_PTR;
#fndif /* NO_CALLBACKS */

    TRACE0("DEBUG: C_OpfnSfssion");
    TRACE1(", slotID=%u", dkSlotID);
    TRACE1(", flbgs=%x", dkFlbgs);
    TRACE0(" ... ");

    rv = (*dkpFundtions->C_OpfnSfssion)(dkSlotID, dkFlbgs, dkpApplidbtion, dkNotify, &dkSfssionHbndlf);
    if (dkAssfrtRfturnVblufOK(fnv, rv) != CK_ASSERT_OK) {
#ifndff NO_CALLBACKS
        if (notifyEndbpsulbtion != NULL) {
            if (notifyEndbpsulbtion->jApplidbtionDbtb != NULL) {
                (*fnv)->DflftfGlobblRff(fnv, jApplidbtion);
            }
            (*fnv)->DflftfGlobblRff(fnv, jNotify);
            frff(notifyEndbpsulbtion);
        }
#fndif /* NO_CALLBACKS */
        rfturn 0L;
    }

    TRACE0("got sfssion");
    TRACE1(", SfssionHbndlf=%u", dkSfssionHbndlf);
    TRACE0(" ... ");

    jSfssionHbndlf = dkULongToJLong(dkSfssionHbndlf);

#ifndff NO_CALLBACKS
    if (notifyEndbpsulbtion != NULL) {
        /* storf tif notifyEndbpsulbtion to fnbblf lbtfr dlfbnup */
        putNotifyEntry(fnv, dkSfssionHbndlf, notifyEndbpsulbtion);
    }
#fndif /* NO_CALLBACKS */

    TRACE0("FINISHED\n");

    rfturn jSfssionHbndlf ;
}
#fndif

#ifdff P11_ENABLE_C_CLOSESESSION
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_ClosfSfssion
 * Signbturf: (J)V
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf        CK_SESSION_HANDLE iSfssion
 */
JNIEXPORT void JNICALL Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1ClosfSfssion
    (JNIEnv *fnv, jobjfdt obj, jlong jSfssionHbndlf)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    CK_RV rv;
#ifndff NO_CALLBACKS
    NotifyEndbpsulbtion *notifyEndbpsulbtion;
    jobjfdt jApplidbtionDbtb;
#fndif /* NO_CALLBACKS */

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn; }

    dkSfssionHbndlf = jLongToCKULong(jSfssionHbndlf);

    rv = (*dkpFundtions->C_ClosfSfssion)(dkSfssionHbndlf);
    if (dkAssfrtRfturnVblufOK(fnv, rv) != CK_ASSERT_OK) { rfturn; }

#ifndff NO_CALLBACKS
    notifyEndbpsulbtion = rfmovfNotifyEntry(fnv, dkSfssionHbndlf);

    if (notifyEndbpsulbtion != NULL) {
        /* tifrf wbs b notify objfdt usfd witi tiis sfssion, now dump tif
         * fndbpsulbtion objfdt
         */
        (*fnv)->DflftfGlobblRff(fnv, notifyEndbpsulbtion->jNotifyObjfdt);
        jApplidbtionDbtb = notifyEndbpsulbtion->jApplidbtionDbtb;
        if (jApplidbtionDbtb != NULL) {
            (*fnv)->DflftfGlobblRff(fnv, jApplidbtionDbtb);
        }
        frff(notifyEndbpsulbtion);
    }
#fndif /* NO_CALLBACKS */

}
#fndif

#ifdff P11_ENABLE_C_CLOSEALLSESSIONS
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_ClosfAllSfssions
 * Signbturf: (J)V
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSlotID               CK_SLOT_ID slotID
 */
JNIEXPORT void JNICALL Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1ClosfAllSfssions
    (JNIEnv *fnv, jobjfdt obj, jlong jSlotID)
{
    CK_SLOT_ID dkSlotID;
    CK_RV rv;
#ifndff NO_CALLBACKS
    NotifyEndbpsulbtion *notifyEndbpsulbtion;
    jobjfdt jApplidbtionDbtb;
#fndif /* NO_CALLBACKS */

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn; }

    dkSlotID = jLongToCKULong(jSlotID);

    rv = (*dkpFundtions->C_ClosfAllSfssions)(dkSlotID);
    if (dkAssfrtRfturnVblufOK(fnv, rv) != CK_ASSERT_OK) { rfturn; }

#ifndff NO_CALLBACKS
    /* Rfmovf bll notify dbllbbdk iflpfr objfdts. */
    wiilf ((notifyEndbpsulbtion = rfmovfFirstNotifyEntry(fnv)) != NULL) {
        /* tifrf wbs b notify objfdt usfd witi tiis sfssion, now dump tif
         * fndbpsulbtion objfdt
         */
        (*fnv)->DflftfGlobblRff(fnv, notifyEndbpsulbtion->jNotifyObjfdt);
        jApplidbtionDbtb = notifyEndbpsulbtion->jApplidbtionDbtb;
        if (jApplidbtionDbtb != NULL) {
            (*fnv)->DflftfGlobblRff(fnv, jApplidbtionDbtb);
        }
        frff(notifyEndbpsulbtion);
    }
#fndif /* NO_CALLBACKS */
}
#fndif

#ifdff P11_ENABLE_C_GETSESSIONINFO
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_GftSfssionInfo
 * Signbturf: (J)Lsun/sfdurity/pkds11/wrbppfr/CK_SESSION_INFO;
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf        CK_SESSION_HANDLE iSfssion
 * @rfturn  jobjfdt jSfssionInfo        CK_SESSION_INFO_PTR pInfo
 */
JNIEXPORT jobjfdt JNICALL Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1GftSfssionInfo
    (JNIEnv *fnv, jobjfdt obj, jlong jSfssionHbndlf)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    CK_SESSION_INFO dkSfssionInfo;
    jobjfdt jSfssionInfo=NULL;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn NULL; }

    dkSfssionHbndlf = jLongToCKULong(jSfssionHbndlf);

    rv = (*dkpFundtions->C_GftSfssionInfo)(dkSfssionHbndlf, &dkSfssionInfo);
    if (dkAssfrtRfturnVblufOK(fnv, rv) == CK_ASSERT_OK) {
        jSfssionInfo = dkSfssionInfoPtrToJSfssionInfo(fnv, &dkSfssionInfo);
    }
    rfturn jSfssionInfo ;
}
#fndif

#ifdff P11_ENABLE_C_GETOPERATIONSTATE
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_GftOpfrbtionStbtf
 * Signbturf: (J)[B
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf        CK_SESSION_HANDLE iSfssion
 * @rfturn  jbytfArrby jStbtf           CK_BYTE_PTR pOpfrbtionStbtf
 *                                      CK_ULONG_PTR pulOpfrbtionStbtfLfn
 */
JNIEXPORT jbytfArrby JNICALL Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1GftOpfrbtionStbtf
    (JNIEnv *fnv, jobjfdt obj, jlong jSfssionHbndlf)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    CK_BYTE_PTR dkpStbtf;
    CK_ULONG dkStbtfLfngti;
    jbytfArrby jStbtf = NULL;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn NULL; }

    dkSfssionHbndlf = jLongToCKULong(jSfssionHbndlf);

    rv = (*dkpFundtions->C_GftOpfrbtionStbtf)(dkSfssionHbndlf, NULL_PTR, &dkStbtfLfngti);
    if (dkAssfrtRfturnVblufOK(fnv, rv) != CK_ASSERT_OK) { rfturn NULL ; }

    dkpStbtf = (CK_BYTE_PTR) mbllod(dkStbtfLfngti);
    if (dkpStbtf == NULL) {
        tirowOutOfMfmoryError(fnv, 0);
        rfturn NULL;
    }

    rv = (*dkpFundtions->C_GftOpfrbtionStbtf)(dkSfssionHbndlf, dkpStbtf, &dkStbtfLfngti);
    if (dkAssfrtRfturnVblufOK(fnv, rv) == CK_ASSERT_OK) {
        jStbtf = dkBytfArrbyToJBytfArrby(fnv, dkpStbtf, dkStbtfLfngti);
    }
    frff(dkpStbtf);

    rfturn jStbtf ;
}
#fndif

#ifdff P11_ENABLE_C_SETOPERATIONSTATE
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_SftOpfrbtionStbtf
 * Signbturf: (J[BJJ)V
 * Pbrbmftfrmbpping:                        *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf            CK_SESSION_HANDLE iSfssion
 * @pbrbm   jbytfArrby jOpfrbtionStbtf      CK_BYTE_PTR pOpfrbtionStbtf
 *                                          CK_ULONG ulOpfrbtionStbtfLfn
 * @pbrbm   jlong jEndryptionKfyHbndlf      CK_OBJECT_HANDLE iEndryptionKfy
 * @pbrbm   jlong jAutifntidbtionKfyHbndlf  CK_OBJECT_HANDLE iAutifntidbtionKfy
 */
JNIEXPORT void JNICALL Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1SftOpfrbtionStbtf
    (JNIEnv *fnv, jobjfdt obj, jlong jSfssionHbndlf, jbytfArrby jOpfrbtionStbtf, jlong jEndryptionKfyHbndlf, jlong jAutifntidbtionKfyHbndlf)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    CK_BYTE_PTR dkpStbtf = NULL_PTR;
    CK_ULONG dkStbtfLfngti;
    CK_OBJECT_HANDLE dkEndryptionKfyHbndlf;
    CK_OBJECT_HANDLE dkAutifntidbtionKfyHbndlf;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn; }

    dkSfssionHbndlf = jLongToCKULong(jSfssionHbndlf);
    jBytfArrbyToCKBytfArrby(fnv, jOpfrbtionStbtf, &dkpStbtf, &dkStbtfLfngti);
    if ((*fnv)->ExdfptionCifdk(fnv)) { rfturn; }

    dkEndryptionKfyHbndlf = jLongToCKULong(jEndryptionKfyHbndlf);
    dkAutifntidbtionKfyHbndlf = jLongToCKULong(jAutifntidbtionKfyHbndlf);

    rv = (*dkpFundtions->C_SftOpfrbtionStbtf)(dkSfssionHbndlf, dkpStbtf, dkStbtfLfngti, dkEndryptionKfyHbndlf, dkAutifntidbtionKfyHbndlf);

    frff(dkpStbtf);

    if (dkAssfrtRfturnVblufOK(fnv, rv) != CK_ASSERT_OK) { rfturn; }
}
#fndif

#ifdff P11_ENABLE_C_LOGIN
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_Login
 * Signbturf: (JJ[C)V
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf        CK_SESSION_HANDLE iSfssion
 * @pbrbm   jlong jUsfrTypf             CK_USER_TYPE usfrTypf
 * @pbrbm   jdibrArrby jPin             CK_CHAR_PTR pPin
 *                                      CK_ULONG ulPinLfn
 */
JNIEXPORT void JNICALL Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1Login
    (JNIEnv *fnv, jobjfdt obj, jlong jSfssionHbndlf, jlong jUsfrTypf, jdibrArrby jPin)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    CK_USER_TYPE dkUsfrTypf;
    CK_CHAR_PTR dkpPinArrby = NULL_PTR;
    CK_ULONG dkPinLfngti;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn; }

    dkSfssionHbndlf = jLongToCKULong(jSfssionHbndlf);
    dkUsfrTypf = jLongToCKULong(jUsfrTypf);
    jCibrArrbyToCKCibrArrby(fnv, jPin, &dkpPinArrby, &dkPinLfngti);
    if ((*fnv)->ExdfptionCifdk(fnv)) { rfturn; }

    rv = (*dkpFundtions->C_Login)(dkSfssionHbndlf, dkUsfrTypf, dkpPinArrby, dkPinLfngti);

    frff(dkpPinArrby);

    if (dkAssfrtRfturnVblufOK(fnv, rv) != CK_ASSERT_OK) { rfturn; }
}
#fndif

#ifdff P11_ENABLE_C_LOGOUT
/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    C_Logout
 * Signbturf: (J)V
 * Pbrbmftfrmbpping:                    *PKCS11*
 * @pbrbm   jlong jSfssionHbndlf        CK_SESSION_HANDLE iSfssion
 */
JNIEXPORT void JNICALL Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_C_1Logout
    (JNIEnv *fnv, jobjfdt obj, jlong jSfssionHbndlf)
{
    CK_SESSION_HANDLE dkSfssionHbndlf;
    CK_RV rv;

    CK_FUNCTION_LIST_PTR dkpFundtions = gftFundtionList(fnv, obj);
    if (dkpFundtions == NULL) { rfturn; }

    dkSfssionHbndlf = jLongToCKULong(jSfssionHbndlf);

    rv = (*dkpFundtions->C_Logout)(dkSfssionHbndlf);
    if (dkAssfrtRfturnVblufOK(fnv, rv) != CK_ASSERT_OK) { rfturn; }
}
#fndif

/* ************************************************************************** */
/* Fundtions for kffping trbdk of notify dbllbbdks                            */
/* ************************************************************************** */

#ifndff NO_CALLBACKS

/*
 * Add tif givfn notify fndbpsulbtion objfdt to tif list of bdtivf notify
 * objfdts.
 * If notifyEndbpsulbtion is NULL, tiis fundtion dofs notiing.
 */
void putNotifyEntry(JNIEnv *fnv, CK_SESSION_HANDLE iSfssion, NotifyEndbpsulbtion *notifyEndbpsulbtion) {
    NotifyListNodf *durrfntNodf, *nfwNodf;

    if (notifyEndbpsulbtion == NULL) {
        rfturn;
    }

    nfwNodf = (NotifyListNodf *) mbllod(sizfof(NotifyListNodf));
    if (nfwNodf == NULL) {
        tirowOutOfMfmoryError(fnv, 0);
        rfturn;
    }
    nfwNodf->iSfssion = iSfssion;
    nfwNodf->notifyEndbpsulbtion = notifyEndbpsulbtion;
    nfwNodf->nfxt = NULL;

    (*fnv)->MonitorEntfr(fnv, notifyListLodk); /* syndironizf bddfss to list */

    if (notifyListHfbd == NULL) {
        /* tiis is tif first fntry */
        notifyListHfbd = nfwNodf;
    } flsf {
        /* go to tif lbst fntry; i.f. tif first nodf wiidi's 'nfxt' is NULL.
         */
        durrfntNodf = notifyListHfbd;
        wiilf (durrfntNodf->nfxt != NULL) {
            durrfntNodf = durrfntNodf->nfxt;
        }
        durrfntNodf->nfxt = nfwNodf;
    }

    (*fnv)->MonitorExit(fnv, notifyListLodk); /* syndironizf bddfss to list */
}

/*
 * Rfmovfs tif bdtivf notifyEndbpsulbtion objfdt usfd witi tif givfn sfssion bnd
 * rfturns it. If tifrf is no notifyEndbpsulbtion bdtivf for tiis sfssion, tiis
 * fundtion rfturns NULL.
 */
NotifyEndbpsulbtion * rfmovfNotifyEntry(JNIEnv *fnv, CK_SESSION_HANDLE iSfssion) {
    NotifyEndbpsulbtion *notifyEndbpsulbtion;
    NotifyListNodf *durrfntNodf, *prfviousNodf;

    (*fnv)->MonitorEntfr(fnv, notifyListLodk); /* syndironizf bddfss to list */

    if (notifyListHfbd == NULL) {
        /* tiis is tif first fntry */
        notifyEndbpsulbtion = NULL;
    } flsf {
        /* Find tif nodf witi tif wbntfd sfssion ibndlf. Also stop, wifn wf rfbdi
         * tif lbst fntry; i.f. tif first nodf wiidi's 'nfxt' is NULL.
         */
        durrfntNodf = notifyListHfbd;
        prfviousNodf = NULL;

        wiilf ((durrfntNodf->iSfssion != iSfssion) && (durrfntNodf->nfxt != NULL)) {
            prfviousNodf = durrfntNodf;
            durrfntNodf = durrfntNodf->nfxt;
        }

        if (durrfntNodf->iSfssion == iSfssion) {
            /* Wf found b fntry for tif wbntfd sfssion, now rfmovf it. */
            if (prfviousNodf == NULL) {
                /* it's tif first nodf */
                notifyListHfbd = durrfntNodf->nfxt;
            } flsf {
                prfviousNodf->nfxt = durrfntNodf->nfxt;
            }
            notifyEndbpsulbtion = durrfntNodf->notifyEndbpsulbtion;
            frff(durrfntNodf);
        } flsf {
            /* Wf did not find b fntry for tiis sfssion */
            notifyEndbpsulbtion = NULL;
        }
    }

    (*fnv)->MonitorExit(fnv, notifyListLodk); /* syndironizf bddfss to list */

    rfturn notifyEndbpsulbtion ;
}

/*

 * Rfmovfs tif first notifyEndbpsulbtion objfdt. If tifrf is no notifyEndbpsulbtion,
 * tiis fundtion rfturns NULL.
 */
NotifyEndbpsulbtion * rfmovfFirstNotifyEntry(JNIEnv *fnv) {
    NotifyEndbpsulbtion *notifyEndbpsulbtion;
    NotifyListNodf *durrfntNodf;

    (*fnv)->MonitorEntfr(fnv, notifyListLodk); /* syndironizf bddfss to list */

    if (notifyListHfbd == NULL) {
        /* tiis is tif first fntry */
        notifyEndbpsulbtion = NULL;
    } flsf {
        /* Rfmovf tif first fntry. */
        durrfntNodf = notifyListHfbd;
        notifyListHfbd = notifyListHfbd->nfxt;
        notifyEndbpsulbtion = durrfntNodf->notifyEndbpsulbtion;
        frff(durrfntNodf);
    }

    (*fnv)->MonitorExit(fnv, notifyListLodk); /* syndironizf bddfss to list */

    rfturn notifyEndbpsulbtion ;
}

#fndif /* NO_CALLBACKS */

#ifndff NO_CALLBACKS

/*
 * Tif fundtion ibndling notify dbllbbdks. It dbsts tif pApplidbtion pbrbmftfr
 * bbdk to b NotifyEndbpsulbtion strudturf bnd rftrifvfs tif Notify objfdt bnd
 * tif bpplidbtion dbtb from it.
 *
 * @pbrbm iSfssion Tif sfssion, tiis dbllbbdk is domming from.
 * @pbrbm fvfnt Tif typf of fvfnt tibt oddurrfd.
 * @pbrbm pApplidbtion Tif bpplidbtion dbtb bs pbssfd in upon OpfnSfssion. In
                       tiis wrbppfr wf blwbys pbss in b NotifyEndbpsulbtion
                       objfdt, wiidi iolds nfdfssbry informbtion for dflfgbting
                       tif dbllbbdk to tif Jbvb VM.
 * @rfturn
 */
CK_RV notifyCbllbbdk(
    CK_SESSION_HANDLE iSfssion,     /* tif sfssion's ibndlf */
    CK_NOTIFICATION   fvfnt,
    CK_VOID_PTR       pApplidbtion  /* pbssfd to C_OpfnSfssion */
)
{
    NotifyEndbpsulbtion *notifyEndbpsulbtion;
    fxtfrn JbvbVM *jvm;
    JNIEnv *fnv;
    jint rfturnVbluf;
    jlong jSfssionHbndlf;
    jlong jEvfnt;
    jdlbss dkNotifyClbss;
    jmftiodID jmftiod;
    jtirowbblf pkds11Exdfption;
    jdlbss pkds11ExdfptionClbss;
    jlong frrorCodf;
    CK_RV rv = CKR_OK;
    int wbsAttbdifd = 1;

    if (pApplidbtion == NULL) { rfturn rv ; } /* Tiis siould not oddur in tiis wrbppfr. */

    notifyEndbpsulbtion = (NotifyEndbpsulbtion *) pApplidbtion;

    /* Gft tif durrfntly running Jbvb VM */
    if (jvm == NULL) { rfturn rv ; } /* tifrf is no VM running */

    /* Dftfrminf, if durrfnt tirfbd is blrfbdy bttbdifd */
    rfturnVbluf = (*jvm)->GftEnv(jvm, (void **) &fnv, JNI_VERSION_1_2);
    if (rfturnVbluf == JNI_EDETACHED) {
        /* tirfbd dftbdifd, so bttbdi it */
        wbsAttbdifd = 0;
        rfturnVbluf = (*jvm)->AttbdiCurrfntTirfbd(jvm, (void **) &fnv, NULL);
    } flsf if (rfturnVbluf == JNI_EVERSION) {
        /* tiis vfrsion of JNI is not supportfd, so just try to bttbdi */
        /* wf bssumf it wbs bttbdifd to fnsurf tibt tiis tirfbd is not dftbdifd
         * bftfrwbrds fvfn tiougi it siould not
         */
        wbsAttbdifd = 1;
        rfturnVbluf = (*jvm)->AttbdiCurrfntTirfbd(jvm, (void **) &fnv, NULL);
    } flsf {
        /* bttbdifd */
        wbsAttbdifd = 1;
    }

    jSfssionHbndlf = dkULongToJLong(iSfssion);
    jEvfnt = dkULongToJLong(fvfnt);

    dkNotifyClbss = (*fnv)->FindClbss(fnv, CLASS_NOTIFY);
    if (dkNotifyClbss == NULL) { rfturn rv; }
    jmftiod = (*fnv)->GftMftiodID(fnv, dkNotifyClbss, "CK_NOTIFY", "(JJLjbvb/lbng/Objfdt;)V");
    if (jmftiod == NULL) { rfturn rv; }

    (*fnv)->CbllVoidMftiod(fnv, notifyEndbpsulbtion->jNotifyObjfdt, jmftiod,
                         jSfssionHbndlf, jEvfnt, notifyEndbpsulbtion->jApplidbtionDbtb);

    /* difdk, if dbllbbdk tirfw bn fxdfption */
    pkds11Exdfption = (*fnv)->ExdfptionOddurrfd(fnv);

    if (pkds11Exdfption != NULL) {
        /* TBD: dlfbr tif pfnding fxdfption witi ExdfptionClfbr? */
        /* Tif wbs bn fxdfption tirown, now wf gft tif frror-dodf from it */
        pkds11ExdfptionClbss = (*fnv)->FindClbss(fnv, CLASS_PKCS11EXCEPTION);
        if (pkds11ExdfptionClbss == NULL) { rfturn rv; }

        jmftiod = (*fnv)->GftMftiodID(fnv, pkds11ExdfptionClbss, "gftErrorCodf", "()J");
        if (jmftiod == NULL) { rfturn rv; }

        frrorCodf = (*fnv)->CbllLongMftiod(fnv, pkds11Exdfption, jmftiod);
        rv = jLongToCKULong(frrorCodf);
    }

    /* if wf bttbdifd tiis tirfbd to tif VM just for dbllbbdk, wf dftbdi it now */
    if (wbsAttbdifd) {
        rfturnVbluf = (*jvm)->DftbdiCurrfntTirfbd(jvm);
    }

    rfturn rv ;
}

#fndif /* NO_CALLBACKS */
