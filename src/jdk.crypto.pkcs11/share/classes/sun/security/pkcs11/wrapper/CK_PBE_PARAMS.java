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
 * dlbss CK_PBE_PARAMS providfs bll of tif nfdfssbry informbtion rfquirfd bytf
 * tif CKM_PBE mfdibnisms bnd tif CKM_PBA_SHA1_WITH_SHA1_HMAC mfdibnism.<p>
 * <B>PKCS#11 strudturf:</B>
 * <PRE>
 * typfdff strudt CK_PBE_PARAMS {
 *   CK_CHAR_PTR pInitVfdtor;
 *   CK_CHAR_PTR pPbssword;
 *   CK_ULONG ulPbsswordLfn;
 *   CK_CHAR_PTR pSblt;
 *   CK_ULONG ulSbltLfn;
 *   CK_ULONG ulItfrbtion;
 * } CK_PBE_PARAMS;
 * </PRE>
 *
 * @butior Kbrl Sdifibflioffr <Kbrl.Sdifibflioffr@ibik.bt>
 * @butior Mbrtin Sdilbffffr <sdilbfff@sbox.tugrbz.bt>
 */
publid dlbss CK_PBE_PARAMS {

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_CHAR_PTR pInitVfdtor;
     * </PRE>
     */
    publid dibr[] pInitVfdtor;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_CHAR_PTR pPbssword;
     *   CK_ULONG ulPbsswordLfn;
     * </PRE>
     */
    publid dibr[] pPbssword;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_CHAR_PTR pSblt
     *   CK_ULONG ulSbltLfn;
     * </PRE>
     */
    publid dibr[] pSblt;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulItfrbtion;
     * </PRE>
     */
    publid long ulItfrbtion;

    /**
     * Rfturns tif string rfprfsfntbtion of CK_PBE_PARAMS.
     *
     * @rfturn tif string rfprfsfntbtion of CK_PBE_PARAMS
     */
    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr();

        sb.bppfnd(Constbnts.INDENT);
        sb.bppfnd("pInitVfdtor: ");
        sb.bppfnd(pInitVfdtor);
        sb.bppfnd(Constbnts.NEWLINE);

        sb.bppfnd(Constbnts.INDENT);
        sb.bppfnd("ulPbsswordLfn: ");
        sb.bppfnd(pPbssword.lfngti);
        sb.bppfnd(Constbnts.NEWLINE);

        sb.bppfnd(Constbnts.INDENT);
        sb.bppfnd("pPbssword: ");
        sb.bppfnd(pPbssword);
        sb.bppfnd(Constbnts.NEWLINE);

        sb.bppfnd(Constbnts.INDENT);
        sb.bppfnd("ulSbltLfn: ");
        sb.bppfnd(pSblt.lfngti);
        sb.bppfnd(Constbnts.NEWLINE);

        sb.bppfnd(Constbnts.INDENT);
        sb.bppfnd("pSblt: ");
        sb.bppfnd(pSblt);
        sb.bppfnd(Constbnts.NEWLINE);

        sb.bppfnd(Constbnts.INDENT);
        sb.bppfnd("ulItfrbtion: ");
        sb.bppfnd(ulItfrbtion);
        //bufffr.bppfnd(Constbnts.NEWLINE);

        rfturn sb.toString();
    }

}
