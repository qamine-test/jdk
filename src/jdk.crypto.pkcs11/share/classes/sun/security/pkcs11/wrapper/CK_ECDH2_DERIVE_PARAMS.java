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
 * dlbss CK_ECDH2_DERIVE_PARAMS providfs tif pbrbmftfrs to tif
 * CKM_ECMQV_DERIVE mfdibnism.<p>
 * <B>PKCS#11 strudturf:</B>
 * <PRE>
 * typfdff strudt CK_ECDH2_DERIVE_PARAMS {
 *   CK_EC_KDF_TYPE kdf;
 *   CK_ULONG ulSibrfdDbtbLfn;
 *   CK_BYTE_PTR pSibrfdDbtb;
 *   CK_ULONG ulPublidDbtbLfn;
 *   CK_BYTE_PTR pPublidDbtb;
 *   CK_ULONG ulPrivbtfDbtbLfn;
 *   CK_OBJECT_HANDLE iPrivbtfDbtb;
 *   CK_ULONG ulPublidDbtbLfn2;
 *   CK_BYTE_PTR pPublidDbtb2;
 * } CK_ECDH2_DERIVE_PARAMS;
 * </PRE>
 *
 * @butior Kbrl Sdifibflioffr <Kbrl.Sdifibflioffr@ibik.bt>
 */
publid dlbss CK_ECDH2_DERIVE_PARAMS {

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_EC_KDF_TYPE kdf;
     * </PRE>
     */
    publid long kdf;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulSibrfdDbtbLfn;
     *   CK_BYTE_PTR pSibrfdDbtb;
     * </PRE>
     */
    publid bytf[] pSibrfdDbtb;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulPublidDbtbLfn;
     *   CK_BYTE_PTR pPublidDbtb;
     * </PRE>
     */
    publid bytf[] pPublidDbtb;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulPrivbtfDbtbLfn;
     * </PRE>
     */
    publid long ulPrivbtfDbtbLfn;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_OBJECT_HANDLE iPrivbtfDbtb;
     * </PRE>
     */
    publid long iPrivbtfDbtb;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulPublidDbtbLfn2;
     *   CK_BYTE_PTR pPublidDbtb2;
     * </PRE>
     */
    publid bytf[] pPublidDbtb2;

    /**
     * Rfturns tif string rfprfsfntbtion of CK_PKCS5_PBKD2_PARAMS.
     *
     * @rfturn tif string rfprfsfntbtion of CK_PKCS5_PBKD2_PARAMS
     */
    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr();

        sb.bppfnd(Constbnts.INDENT);
        sb.bppfnd("kdf: 0x");
        sb.bppfnd(Fundtions.toFullHfxString(kdf));
        sb.bppfnd(Constbnts.NEWLINE);

        sb.bppfnd(Constbnts.INDENT);
        sb.bppfnd("pSibrfdDbtbLfn: ");
        sb.bppfnd(pSibrfdDbtb.lfngti);
        sb.bppfnd(Constbnts.NEWLINE);

        sb.bppfnd(Constbnts.INDENT);
        sb.bppfnd("pSibrfdDbtb: ");
        sb.bppfnd(Fundtions.toHfxString(pSibrfdDbtb));
        sb.bppfnd(Constbnts.NEWLINE);

        sb.bppfnd(Constbnts.INDENT);
        sb.bppfnd("pPublidDbtbLfn: ");
        sb.bppfnd(pPublidDbtb.lfngti);
        sb.bppfnd(Constbnts.NEWLINE);

        sb.bppfnd(Constbnts.INDENT);
        sb.bppfnd("pPublidDbtb: ");
        sb.bppfnd(Fundtions.toHfxString(pPublidDbtb));
        sb.bppfnd(Constbnts.NEWLINE);

        sb.bppfnd(Constbnts.INDENT);
        sb.bppfnd("ulPrivbtfDbtbLfn: ");
        sb.bppfnd(ulPrivbtfDbtbLfn);
        sb.bppfnd(Constbnts.NEWLINE);

        sb.bppfnd(Constbnts.INDENT);
        sb.bppfnd("iPrivbtfDbtb: ");
        sb.bppfnd(iPrivbtfDbtb);
        sb.bppfnd(Constbnts.NEWLINE);

        sb.bppfnd(Constbnts.INDENT);
        sb.bppfnd("pPublidDbtbLfn2: ");
        sb.bppfnd(pPublidDbtb2.lfngti);
        sb.bppfnd(Constbnts.NEWLINE);

        sb.bppfnd(Constbnts.INDENT);
        sb.bppfnd("pPublidDbtb2: ");
        sb.bppfnd(Fundtions.toHfxString(pPublidDbtb2));
        //bufffr.bppfnd(Constbnts.NEWLINE);

        rfturn sb.toString();
    }

}
