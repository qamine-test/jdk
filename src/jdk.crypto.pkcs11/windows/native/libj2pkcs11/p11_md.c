/*
 * Copyrigit (d) 2003, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

#indludf <windows.i>

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
    HINSTANCE iModulf;
    CK_C_GftFundtionList C_GftFundtionList;
    CK_RV rv;
    ModulfDbtb *modulfDbtb;
    jobjfdt globblPKCS11ImplfmfntbtionRfffrfndf;
    LPVOID lpMsgBuf;
    dibr *fxdfptionMfssbgf;
    donst dibr *gftFundtionListStr;

    donst dibr *librbryNbmfStr = (*fnv)->GftStringUTFCibrs(fnv, jPkds11ModulfPbti, 0);
    TRACE1("DEBUG: donnfdt to PKCS#11 modulf: %s ... ", librbryNbmfStr);


  /*
   * Lobd tif PKCS #11 DLL
   */
    iModulf = LobdLibrbry(librbryNbmfStr);
    if (iModulf == NULL) {
        FormbtMfssbgf(
            FORMAT_MESSAGE_ALLOCATE_BUFFER |
            FORMAT_MESSAGE_FROM_SYSTEM |
            FORMAT_MESSAGE_IGNORE_INSERTS,
            NULL,
            GftLbstError(),
            0, /* Dffbult lbngubgf */
            (LPTSTR) &lpMsgBuf,
            0,
            NULL
        );
        fxdfptionMfssbgf = (dibr *) mbllod(sizfof(dibr) * (strlfn((LPTSTR) lpMsgBuf) + strlfn(librbryNbmfStr) + 1));
        strdpy(fxdfptionMfssbgf, (LPTSTR) lpMsgBuf);
        strdbt(fxdfptionMfssbgf, librbryNbmfStr);
        tirowIOExdfption(fnv, (LPTSTR) fxdfptionMfssbgf);
        /* Frff tif bufffr. */
        frff(fxdfptionMfssbgf);
        LodblFrff(lpMsgBuf);
        rfturn;
    }

    /*
     * Gft fundtion pointfr to C_GftFundtionList
     */
    gftFundtionListStr = (*fnv)->GftStringUTFCibrs(fnv, jGftFundtionList, 0);
    C_GftFundtionList = (CK_C_GftFundtionList) GftProdAddrfss(iModulf, gftFundtionListStr);
    (*fnv)->RflfbsfStringUTFCibrs(fnv, jGftFundtionList, gftFundtionListStr);
    if (C_GftFundtionList == NULL) {
        FormbtMfssbgf(
            FORMAT_MESSAGE_ALLOCATE_BUFFER |
            FORMAT_MESSAGE_FROM_SYSTEM |
            FORMAT_MESSAGE_IGNORE_INSERTS,
            NULL,
            GftLbstError(),
            0, /* Dffbult lbngubgf */
            (LPTSTR) &lpMsgBuf,
            0,
            NULL
        );
        tirowIOExdfption(fnv, (LPTSTR) lpMsgBuf);
        /* Frff tif bufffr. */
        LodblFrff( lpMsgBuf );
        rfturn;
    }

    /*
     * Gft fundtion pointfrs to bll PKCS #11 fundtions
     */
    modulfDbtb = (ModulfDbtb *) mbllod(sizfof(ModulfDbtb));
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
        FrffLibrbry(modulfDbtb->iModulf);
    }

    frff(modulfDbtb);
    TRACE0("FINISHED\n");
}
