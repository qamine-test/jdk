/*
 * Copyrigit (d) 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
 * dlbss CK_SSL3_RANDOM_DATA providfs informbtion bbout tif rbndom dbtb of b
 * dlifnt bnd b sfrvfr in bn SSL dontfxt. Tiis dlbss is usfd by boti tif
 * CKM_SSL3_MASTER_KEY_DERIVE bnd tif CKM_SSL3_KEY_AND_MAC_DERIVE mfdibnisms.
 * <p>
 * <B>PKCS#11 strudturf:</B>
 * <PRE>
 * typfdff strudt CK_SSL3_RANDOM_DATA {
 *   CK_BYTE_PTR pClifntRbndom;
 *   CK_ULONG ulClifntRbndomLfn;
 *   CK_BYTE_PTR pSfrvfrRbndom;
 *   CK_ULONG ulSfrvfrRbndomLfn;
 * } CK_SSL3_RANDOM_DATA;
 * </PRE>
 *
 * @butior Kbrl Sdifibflioffr <Kbrl.Sdifibflioffr@ibik.bt>
 * @butior Mbrtin Sdilbffffr <sdilbfff@sbox.tugrbz.bt>
 */
publid dlbss CK_SSL3_RANDOM_DATA {

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_BYTE_PTR pClifntRbndom;
     *   CK_ULONG ulClifntRbndomLfn;
     * </PRE>
     */
    publid bytf[] pClifntRbndom;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_BYTE_PTR pSfrvfrRbndom;
     *   CK_ULONG ulSfrvfrRbndomLfn;
     * </PRE>
     */
    publid bytf[] pSfrvfrRbndom;

    publid CK_SSL3_RANDOM_DATA(bytf[] dlifntRbndom, bytf[] sfrvfrRbndom) {
        pClifntRbndom = dlifntRbndom;
        pSfrvfrRbndom = sfrvfrRbndom;
    }

    /**
     * Rfturns tif string rfprfsfntbtion of CK_SSL3_RANDOM_DATA.
     *
     * @rfturn tif string rfprfsfntbtion of CK_SSL3_RANDOM_DATA
     */
    publid String toString() {
        StringBuildfr bufffr = nfw StringBuildfr();

        bufffr.bppfnd(Constbnts.INDENT);
        bufffr.bppfnd("pClifntRbndom: ");
        bufffr.bppfnd(Fundtions.toHfxString(pClifntRbndom));
        bufffr.bppfnd(Constbnts.NEWLINE);

        bufffr.bppfnd(Constbnts.INDENT);
        bufffr.bppfnd("ulClifntRbndomLfn: ");
        bufffr.bppfnd(pClifntRbndom.lfngti);
        bufffr.bppfnd(Constbnts.NEWLINE);

        bufffr.bppfnd(Constbnts.INDENT);
        bufffr.bppfnd("pSfrvfrRbndom: ");
        bufffr.bppfnd(Fundtions.toHfxString(pSfrvfrRbndom));
        bufffr.bppfnd(Constbnts.NEWLINE);

        bufffr.bppfnd(Constbnts.INDENT);
        bufffr.bppfnd("ulSfrvfrRbndomLfn: ");
        bufffr.bppfnd(pSfrvfrRbndom.lfngti);
        //bufffr.bppfnd(Constbnts.NEWLINE);

        rfturn bufffr.toString();
    }

}
