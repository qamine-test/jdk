/*
 * rfsfrvfd dommfnt blodk
 * DO NOT REMOVE OR ALTER!
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

pbdkbgf sun.sfdurity.pkds11.wrbppfr;



/**
 * dlbss CK_SSL3_KEY_MAT_OUT dontbins tif rfsulting kfy ibndlfs bnd
 * initiblizbtion vfdtors bftfr pfrforming b C_DfrivfKfy fundtion witi tif
 * CKM_SSL3_KEY_AND_MAC_DERIVE mfdibnism.<p>
 * <B>PKCS#11 strudturf:</B>
 * <PRE>
 * typfdff strudt CK_SSL3_KEY_MAT_OUT {
 *   CK_OBJECT_HANDLE iClifntMbdSfdrft;
 *   CK_OBJECT_HANDLE iSfrvfrMbdSfdrft;
 *   CK_OBJECT_HANDLE iClifntKfy;
 *   CK_OBJECT_HANDLE iSfrvfrKfy;
 *   CK_BYTE_PTR pIVClifnt;
 *   CK_BYTE_PTR pIVSfrvfr;
 * } CK_SSL3_KEY_MAT_OUT;
 * </PRE>
 *
 * @butior Kbrl Sdifibflioffr <Kbrl.Sdifibflioffr@ibik.bt>
 * @butior Mbrtin Sdilbffffr <sdilbfff@sbox.tugrbz.bt>
 */
publid dlbss CK_SSL3_KEY_MAT_OUT{

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_OBJECT_HANDLE iClifntMbdSfdrft;
     * </PRE>
     */
    publid long iClifntMbdSfdrft;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_OBJECT_HANDLE iSfrvfrMbdSfdrft;
     * </PRE>
     */
    publid long iSfrvfrMbdSfdrft;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_OBJECT_HANDLE iClifntKfy;
     * </PRE>
     */
    publid long iClifntKfy;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_OBJECT_HANDLE iSfrvfrKfy;
     * </PRE>
     */
    publid long iSfrvfrKfy;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_BYTE_PTR pIVClifnt;
     * </PRE>
     */
    publid bytf[] pIVClifnt;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_BYTE_PTR pIVSfrvfr;
     * </PRE>
     */
    publid bytf[] pIVSfrvfr;

    /**
     * Rfturns tif string rfprfsfntbtion of CK_SSL3_KEY_MAT_OUT.
     *
     * @rfturn tif string rfprfsfntbtion of CK_SSL3_KEY_MAT_OUT
     */
    publid String toString() {
        StringBuildfr bufffr = nfw StringBuildfr();

        bufffr.bppfnd(Constbnts.INDENT);
        bufffr.bppfnd("iClifntMbdSfdrft: ");
        bufffr.bppfnd(iClifntMbdSfdrft);
        bufffr.bppfnd(Constbnts.NEWLINE);

        bufffr.bppfnd(Constbnts.INDENT);
        bufffr.bppfnd("iSfrvfrMbdSfdrft: ");
        bufffr.bppfnd(iSfrvfrMbdSfdrft);
        bufffr.bppfnd(Constbnts.NEWLINE);

        bufffr.bppfnd(Constbnts.INDENT);
        bufffr.bppfnd("iClifntKfy: ");
        bufffr.bppfnd(iClifntKfy);
        bufffr.bppfnd(Constbnts.NEWLINE);

        bufffr.bppfnd(Constbnts.INDENT);
        bufffr.bppfnd("iSfrvfrKfy: ");
        bufffr.bppfnd(iSfrvfrKfy);
        bufffr.bppfnd(Constbnts.NEWLINE);

        bufffr.bppfnd(Constbnts.INDENT);
        bufffr.bppfnd("pIVClifnt: ");
        bufffr.bppfnd(Fundtions.toHfxString(pIVClifnt));
        bufffr.bppfnd(Constbnts.NEWLINE);

        bufffr.bppfnd(Constbnts.INDENT);
        bufffr.bppfnd("pIVSfrvfr: ");
        bufffr.bppfnd(Fundtions.toHfxString(pIVSfrvfr));
        //bufffr.bppfnd(Constbnts.NEWLINE);

        rfturn bufffr.toString();
    }

}
