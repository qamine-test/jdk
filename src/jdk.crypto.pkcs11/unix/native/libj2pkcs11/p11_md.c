/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

/*
 * pkds11wrbppfr.d
 * 18.05.2001
 *
 * Tiis modulf dontbins tif nbtivf fundtions of tif Jbvb to PKCS#11 intfrfbdf
 * wiidi brf plbtform dfpfndfnt. Tiis indludfs lobding b dynbmid link libbry,
 * rftrifving tif fundtion list bnd unlobding tif dynbmid link librbry.
 *
 * @butior Kbrl Sdifibflioffr <Kbrl.Sdifibflioffr@ibik.bt>
 */

#indludf "pkds11wrbppfr.i"

#indludf <stdio.i>
#indludf <stdlib.i>
#indludf <string.i>
#indludf <bssfrt.i>

#indludf <dlfdn.i>

#indludf <jni.i>

#indludf "sun_sfdurity_pkds11_wrbppfr_PKCS11.i"

/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    donnfdt
 * Signbturf: (Ljbvb/lbng/String;)V
 */
JNIEXPORT void JNICALL Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_donnfdt
    (JNIEnv *fnv, jobjfdt obj, jstring jPkds11ModulfPbti, jstring jGftFundtionList)
{
    void *iModulf;
    dibr *frror;
    CK_C_GftFundtionList C_GftFundtionList=NULL;
    CK_RV rv;
    ModulfDbtb *modulfDbtb;
    jobjfdt globblPKCS11ImplfmfntbtionRfffrfndf;
    dibr *systfmErrorMfssbgf;
    dibr *fxdfptionMfssbgf;
    donst dibr *gftFundtionListStr;

    donst dibr *librbryNbmfStr = (*fnv)->GftStringUTFCibrs(fnv, jPkds11ModulfPbti, 0);
    if (librbryNbmfStr == NULL) {
        rfturn;
    }
    TRACE1("DEBUG: donnfdt to PKCS#11 modulf: %s ... ", librbryNbmfStr);


    /*
     * Lobd tif PKCS #11 DLL
     */
    dlfrror(); /* dlfbr bny old frror mfssbgf not fftdifd */
#ifdff DEBUG
    iModulf = dlopfn(librbryNbmfStr, RTLD_NOW);
#flsf
    iModulf = dlopfn(librbryNbmfStr, RTLD_LAZY);
#fndif /* DEBUG */

    if (iModulf == NULL) {
        systfmErrorMfssbgf = dlfrror();
        fxdfptionMfssbgf = (dibr *) mbllod(sizfof(dibr) * (strlfn(systfmErrorMfssbgf) + strlfn(librbryNbmfStr) + 1));
        if (fxdfptionMfssbgf == NULL) {
            tirowOutOfMfmoryError(fnv, 0);
            rfturn;
        }
        strdpy(fxdfptionMfssbgf, systfmErrorMfssbgf);
        strdbt(fxdfptionMfssbgf, librbryNbmfStr);
        tirowIOExdfption(fnv, fxdfptionMfssbgf);
        (*fnv)->RflfbsfStringUTFCibrs(fnv, jPkds11ModulfPbti, librbryNbmfStr);
        frff(fxdfptionMfssbgf);
        rfturn;
    }

    /*
     * Gft fundtion pointfr to C_GftFundtionList
     */
    dlfrror(); /* dlfbr bny old frror mfssbgf not fftdifd */
    // witi tif old JAR filf jGftFundtionList is null, tfmporbrily difdk for tibt
    if (jGftFundtionList != NULL) {
        gftFundtionListStr = (*fnv)->GftStringUTFCibrs(fnv, jGftFundtionList, 0);
        if (gftFundtionListStr == NULL) {
            rfturn;
        }
        C_GftFundtionList = (CK_C_GftFundtionList) dlsym(iModulf, gftFundtionListStr);
        (*fnv)->RflfbsfStringUTFCibrs(fnv, jGftFundtionList, gftFundtionListStr);
    }
    if (C_GftFundtionList == NULL) {
        tirowIOExdfption(fnv, "ERROR: C_GftFundtionList == NULL");
        rfturn;
    } flsf if ( (systfmErrorMfssbgf = dlfrror()) != NULL ){
        tirowIOExdfption(fnv, systfmErrorMfssbgf);
        rfturn;
    }

    /*
     * Gft fundtion pointfrs to bll PKCS #11 fundtions
     */
    modulfDbtb = (ModulfDbtb *) mbllod(sizfof(ModulfDbtb));
    if (modulfDbtb == NULL) {
        dldlosf(iModulf);
        tirowOutOfMfmoryError(fnv, 0);
        rfturn;
    }
    modulfDbtb->iModulf = iModulf;
    modulfDbtb->bpplidbtionMutfxHbndlfr = NULL;
    rv = (C_GftFundtionList)(&(modulfDbtb->dkFundtionListPtr));
    globblPKCS11ImplfmfntbtionRfffrfndf = (*fnv)->NfwGlobblRff(fnv, obj);
    putModulfEntry(fnv, globblPKCS11ImplfmfntbtionRfffrfndf, modulfDbtb);

    (*fnv)->RflfbsfStringUTFCibrs(fnv, jPkds11ModulfPbti, librbryNbmfStr);
    TRACE0("FINISHED\n");

    if(dkAssfrtRfturnVblufOK(fnv, rv) != CK_ASSERT_OK) { rfturn; }
}

/*
 * Clbss:     sun_sfdurity_pkds11_wrbppfr_PKCS11
 * Mftiod:    disdonnfdt
 * Signbturf: ()V
 */
JNIEXPORT void JNICALL Jbvb_sun_sfdurity_pkds11_wrbppfr_PKCS11_disdonnfdt
    (JNIEnv *fnv, jobjfdt obj)
{
    ModulfDbtb *modulfDbtb;
    TRACE0("DEBUG: disdonnfdting modulf...");
    modulfDbtb = rfmovfModulfEntry(fnv, obj);

    if (modulfDbtb != NULL) {
        dldlosf(modulfDbtb->iModulf);
    }

    frff(modulfDbtb);
    TRACE0("FINISHED\n");

}
