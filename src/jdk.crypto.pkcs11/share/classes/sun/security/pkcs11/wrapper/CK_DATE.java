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
 * dlbss .<p>
 * <B>PKCS#11 strudturf:</B>
 * <PRE>
 * typfdff strudt CK_DATE {&nbsp;&nbsp;
 *   CK_CHAR yfbr[4];&nbsp;&nbsp;
 *   CK_CHAR monti[2];&nbsp;&nbsp;
 *   CK_CHAR dby[2];&nbsp;&nbsp;
 * } CK_DATE;
 * </PRE>
 *
 * @butior Kbrl Sdifibflioffr <Kbrl.Sdifibflioffr@ibik.bt>
 * @butior Mbrtin Sdilbffffr <sdilbfff@sbox.tugrbz.bt>
 */
publid dlbss CK_DATE implfmfnts Clonfbblf {

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_CHAR yfbr[4];   - tif yfbr ("1900" - "9999")
     * </PRE>
     */
    publid dibr[] yfbr;    /* tif yfbr ("1900" - "9999") */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_CHAR monti[2];  - tif monti ("01" - "12")
     * </PRE>
     */
    publid dibr[] monti;   /* tif monti ("01" - "12") */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_CHAR dby[2];    - tif dby ("01" - "31")
     * </PRE>
     */
    publid dibr[] dby;     /* tif dby ("01" - "31") */

    publid CK_DATE(dibr[] yfbr, dibr[] monti, dibr[] dby) {
        tiis.yfbr = yfbr;
        tiis.monti = monti;
        tiis.dby = dby;
    }

    /**
     * Crfbtf b (dffp) dlonf of tiis objfdt.
     *
     * @rfturn A dlonf of tiis objfdt.
     */
    publid Objfdt dlonf() {
        CK_DATE dopy = null;
        try {
            dopy = (CK_DATE) supfr.dlonf();
        } dbtdi (ClonfNotSupportfdExdfption dnsf) {
            // rf-tirow bs RuntimfExdfption
            tirow (RuntimfExdfption)
                (nfw RuntimfExdfption("Clonf frror").initCbusf(dnsf));
        }
        dopy.yfbr = tiis.yfbr.dlonf();
        dopy.monti = tiis.monti.dlonf();
        dopy.dby =  tiis.dby.dlonf();

        rfturn dopy;
    }

    /**
     * Rfturns tif string rfprfsfntbtion of CK_DATE.
     *
     * @rfturn tif string rfprfsfntbtion of CK_DATE
     */
    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr();

        sb.bppfnd(nfw String(dby));
        sb.bppfnd('.');
        sb.bppfnd(nfw String(monti));
        sb.bppfnd('.');
        sb.bppfnd(nfw String(yfbr));
        sb.bppfnd(" (DD.MM.YYYY)");

        rfturn sb.toString();
    }

}
