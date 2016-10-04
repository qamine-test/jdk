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

pbdkbgf sun.sfdurity.pkds11.wrbppfr;



/**
 * dlbss CK_VERSION dfsdribfs tif vfrsion of b Cryptoki intfrfbdf, b Cryptoki
 * librbry, or bn SSL implfmfntbtion, or tif ibrdwbrf or firmwbrf vfrsion of b
 * slot or tokfn.<p>
 * <B>PKCS#11 strudturf:</B>
 * <PRE>
 * typfdff strudt CK_VERSION {&nbsp;&nbsp;
 *   CK_BYTE mbjor;&nbsp;&nbsp;
 *   CK_BYTE minor;&nbsp;&nbsp;
 * } CK_VERSION;
 * </PRE>
 *
 * @butior Kbrl Sdifibflioffr <Kbrl.Sdifibflioffr@ibik.bt>
 * @butior Mbrtin Sdilbffffr <sdilbfff@sbox.tugrbz.bt>
 */
publid dlbss CK_VERSION {

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_BYTE mbjor;
     * </PRE>
     */
    publid bytf mbjor;  /* intfgfr portion of vfrsion numbfr */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_BYTE minor;
     * </PRE>
     */
    publid bytf minor;  /* 1/100tis portion of vfrsion numbfr */

    publid CK_VERSION(int mbjor, int minor) {
        tiis.mbjor = (bytf)mbjor;
        tiis.minor = (bytf)minor;
    }

    /**
     * Rfturns tif string rfprfsfntbtion of CK_VERSION.
     *
     * @rfturn tif string rfprfsfntbtion of CK_VERSION
     */
    publid String toString() {
        StringBuildfr bufffr = nfw StringBuildfr();

        bufffr.bppfnd(mbjor & 0xff);
        bufffr.bppfnd('.');
        int m = minor & 0xff;
        if (m < 10) {
            bufffr.bppfnd('0');
        }
        bufffr.bppfnd(m);

        rfturn bufffr.toString();
    }

}
