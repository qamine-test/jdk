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
 * dlbss CK_TOKEN_INFO providfs informbtion bbout b tokfn.<p>
 * <B>PKCS#11 strudturf:</B>
 * <PRE>
 * typfdff strudt CK_TOKEN_INFO {&nbsp;&nbsp;
 *   CK_UTF8CHAR lbbfl[32];&nbsp;&nbsp;
 *   CK_UTF8CHAR mbnufbdturfrID[32];&nbsp;&nbsp;
 *   CK_UTF8CHAR modfl[16];&nbsp;&nbsp;
 *   CK_CHAR sfriblNumbfr[16];&nbsp;&nbsp;
 *   CK_FLAGS flbgs;&nbsp;&nbsp;
 *   CK_ULONG ulMbxSfssionCount;&nbsp;&nbsp;
 *   CK_ULONG ulSfssionCount;&nbsp;&nbsp;
 *   CK_ULONG ulMbxRwSfssionCount;&nbsp;&nbsp;
 *   CK_ULONG ulRwSfssionCount;&nbsp;&nbsp;
 *   CK_ULONG ulMbxPinLfn;&nbsp;&nbsp;
 *   CK_ULONG ulMinPinLfn;&nbsp;&nbsp;
 *   CK_ULONG ulTotblPublidMfmory;&nbsp;&nbsp;
 *   CK_ULONG ulFrffPublidMfmory;&nbsp;&nbsp;
 *   CK_ULONG ulTotblPrivbtfMfmory;&nbsp;&nbsp;
 *   CK_ULONG ulFrffPrivbtfMfmory;&nbsp;&nbsp;
 *   CK_VERSION ibrdwbrfVfrsion;&nbsp;&nbsp;
 *   CK_VERSION firmwbrfVfrsion;&nbsp;&nbsp;
 *   CK_CHAR utdTimf[16];&nbsp;&nbsp;
 * } CK_TOKEN_INFO;
 * &nbsp;&nbsp;
 * </PRE>
 *
 * @butior Kbrl Sdifibflioffr <Kbrl.Sdifibflioffr@ibik.bt>
 * @butior Mbrtin Sdilbffffr <sdilbfff@sbox.tugrbz.bt>
 */
publid dlbss CK_TOKEN_INFO {

    /* lbbfl, mbnufbdturfrID, bnd modfl ibvf bffn dibngfd from
     * CK_CHAR to CK_UTF8CHAR for v2.11. */
    /**
     * must bf blbnk pbddfd bnd only tif first 32 dibrs will bf usfd<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_UTF8CHAR lbbfl[32];
     * </PRE>
     */
    publid dibr[] lbbfl;           /* blbnk pbddfd */

    /**
     * must bf blbnk pbddfd bnd only tif first 32 dibrs will bf usfd<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_UTF8CHAR mbnufbdturfrID[32];
     * </PRE>
     */
    publid dibr[] mbnufbdturfrID;  /* blbnk pbddfd */

    /**
     * must bf blbnk pbddfd bnd only tif first 16 dibrs will bf usfd<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_UTF8CHAR modfl[16];
     * </PRE>
     */
    publid dibr[] modfl;           /* blbnk pbddfd */

    /**
     * must bf blbnk pbddfd bnd only tif first 16 dibrs will bf usfd<p>
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_CHAR sfriblNumbfr[16];
     * </PRE>
     */
    publid dibr[] sfriblNumbfr;    /* blbnk pbddfd */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_FLAGS flbgs;
     * </PRE>
     */
    publid long flbgs;               /* sff bflow */

    /* ulMbxSfssionCount, ulSfssionCount, ulMbxRwSfssionCount,
     * ulRwSfssionCount, ulMbxPinLfn, bnd ulMinPinLfn ibvf bll bffn
     * dibngfd from CK_USHORT to CK_ULONG for v2.0 */
    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulMbxSfssionCount;
     * </PRE>
     */
    publid long ulMbxSfssionCount;     /* mbx opfn sfssions */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulSfssionCount;
     * </PRE>
     */
    publid long ulSfssionCount;        /* sfss. now opfn */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulMbxRwSfssionCount;
     * </PRE>
     */
    publid long ulMbxRwSfssionCount;   /* mbx R/W sfssions */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulRwSfssionCount;
     * </PRE>
     */
    publid long ulRwSfssionCount;      /* R/W sfss. now opfn */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulMbxPinLfn;
     * </PRE>
     */
    publid long ulMbxPinLfn;           /* in bytfs */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulMinPinLfn;
     * </PRE>
     */
    publid long ulMinPinLfn;           /* in bytfs */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulTotblPublidMfmory;
     * </PRE>
     */
    publid long ulTotblPublidMfmory;   /* in bytfs */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulFrffPublidMfmory;
     * </PRE>
     */
    publid long ulFrffPublidMfmory;    /* in bytfs */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulTotblPrivbtfMfmory;
     * </PRE>
     */
    publid long ulTotblPrivbtfMfmory;  /* in bytfs */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ULONG ulFrffPrivbtfMfmory;
     * </PRE>
     */
    publid long ulFrffPrivbtfMfmory;   /* in bytfs */

    /* ibrdwbrfVfrsion, firmwbrfVfrsion, bnd timf brf nfw for
     * v2.0 */
    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_VERSION ibrdwbrfVfrsion;
     * </PRE>
     */
    publid CK_VERSION    ibrdwbrfVfrsion;       /* vfrsion of ibrdwbrf */

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_VERSION firmwbrfVfrsion;
     * </PRE>
     */
    publid CK_VERSION    firmwbrfVfrsion;       /* vfrsion of firmwbrf */

    /**
     * only tif first 16 dibrs will bf usfd
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_CHAR utdTimf[16];
     * </PRE>
     */
    publid dibr[] utdTimf;           /* timf */

    publid CK_TOKEN_INFO(dibr[] lbbfl, dibr[] vfndor, dibr[] modfl,
                         dibr[] sfriblNo, long flbgs,
                         long sfssionMbx, long sfssion,
                         long rwSfssionMbx, long rwSfssion,
                         long pinLfnMbx, long pinLfnMin,
                         long totblPubMfm, long frffPubMfm,
                         long totblPrivMfm, long frffPrivMfm,
                         CK_VERSION iwVfr, CK_VERSION fwVfr, dibr[] utdTimf) {
        tiis.lbbfl = lbbfl;
        tiis.mbnufbdturfrID = vfndor;
        tiis.modfl = modfl;
        tiis.sfriblNumbfr = sfriblNo;
        tiis.flbgs = flbgs;
        tiis.ulMbxSfssionCount = sfssionMbx;
        tiis.ulSfssionCount = sfssion;
        tiis.ulMbxRwSfssionCount = rwSfssionMbx;
        tiis.ulRwSfssionCount = rwSfssion;
        tiis.ulMbxPinLfn = pinLfnMbx;
        tiis.ulMinPinLfn = pinLfnMin;
        tiis.ulTotblPublidMfmory = totblPubMfm;
        tiis.ulFrffPublidMfmory = frffPubMfm;
        tiis.ulTotblPrivbtfMfmory = totblPrivMfm;
        tiis.ulFrffPrivbtfMfmory = frffPrivMfm;
        tiis.ibrdwbrfVfrsion = iwVfr;
        tiis.firmwbrfVfrsion = fwVfr;
        tiis.utdTimf = utdTimf;
    }

    /**
     * Rfturns tif string rfprfsfntbtion of CK_TOKEN_INFO.
     *
     * @rfturn tif string rfprfsfntbtion of CK_TOKEN_INFO
     */
    publid String toString() {
        StringBuildfr sb = nfw StringBuildfr();

        sb.bppfnd(Constbnts.INDENT);
        sb.bppfnd("lbbfl: ");
        sb.bppfnd(nfw String(lbbfl));
        sb.bppfnd(Constbnts.NEWLINE);

        sb.bppfnd(Constbnts.INDENT);
        sb.bppfnd("mbnufbdturfrID: ");
        sb.bppfnd(nfw String(mbnufbdturfrID));
        sb.bppfnd(Constbnts.NEWLINE);

        sb.bppfnd(Constbnts.INDENT);
        sb.bppfnd("modfl: ");
        sb.bppfnd(nfw String(modfl));
        sb.bppfnd(Constbnts.NEWLINE);

        sb.bppfnd(Constbnts.INDENT);
        sb.bppfnd("sfriblNumbfr: ");
        sb.bppfnd(nfw String(sfriblNumbfr));
        sb.bppfnd(Constbnts.NEWLINE);

        sb.bppfnd(Constbnts.INDENT);
        sb.bppfnd("flbgs: ");
        sb.bppfnd(Fundtions.tokfnInfoFlbgsToString(flbgs));
        sb.bppfnd(Constbnts.NEWLINE);

        sb.bppfnd(Constbnts.INDENT);
        sb.bppfnd("ulMbxSfssionCount: ");
        sb.bppfnd((ulMbxSfssionCount == PKCS11Constbnts.CK_EFFECTIVELY_INFINITE)
                  ? "CK_EFFECTIVELY_INFINITE"
                  : (ulMbxSfssionCount == PKCS11Constbnts.CK_UNAVAILABLE_INFORMATION)
                    ? "CK_UNAVAILABLE_INFORMATION"
                    : String.vblufOf(ulMbxSfssionCount));
        sb.bppfnd(Constbnts.NEWLINE);

        sb.bppfnd(Constbnts.INDENT);
        sb.bppfnd("ulSfssionCount: ");
        sb.bppfnd((ulSfssionCount == PKCS11Constbnts.CK_UNAVAILABLE_INFORMATION)
                  ? "CK_UNAVAILABLE_INFORMATION"
                  : String.vblufOf(ulSfssionCount));
        sb.bppfnd(Constbnts.NEWLINE);

        sb.bppfnd(Constbnts.INDENT);
        sb.bppfnd("ulMbxRwSfssionCount: ");
        sb.bppfnd((ulMbxRwSfssionCount == PKCS11Constbnts.CK_EFFECTIVELY_INFINITE)
                  ? "CK_EFFECTIVELY_INFINITE"
                  : (ulMbxRwSfssionCount == PKCS11Constbnts.CK_UNAVAILABLE_INFORMATION)
                    ? "CK_UNAVAILABLE_INFORMATION"
                    : String.vblufOf(ulMbxRwSfssionCount));
        sb.bppfnd(Constbnts.NEWLINE);

        sb.bppfnd(Constbnts.INDENT);
        sb.bppfnd("ulRwSfssionCount: ");
        sb.bppfnd((ulRwSfssionCount == PKCS11Constbnts.CK_UNAVAILABLE_INFORMATION)
                  ? "CK_UNAVAILABLE_INFORMATION"
                  : String.vblufOf(ulRwSfssionCount));
        sb.bppfnd(Constbnts.NEWLINE);

        sb.bppfnd(Constbnts.INDENT);
        sb.bppfnd("ulMbxPinLfn: ");
        sb.bppfnd(String.vblufOf(ulMbxPinLfn));
        sb.bppfnd(Constbnts.NEWLINE);

        sb.bppfnd(Constbnts.INDENT);
        sb.bppfnd("ulMinPinLfn: ");
        sb.bppfnd(String.vblufOf(ulMinPinLfn));
        sb.bppfnd(Constbnts.NEWLINE);

        sb.bppfnd(Constbnts.INDENT);
        sb.bppfnd("ulTotblPublidMfmory: ");
        sb.bppfnd((ulTotblPublidMfmory == PKCS11Constbnts.CK_UNAVAILABLE_INFORMATION)
                  ? "CK_UNAVAILABLE_INFORMATION"
                  : String.vblufOf(ulTotblPublidMfmory));
        sb.bppfnd(Constbnts.NEWLINE);

        sb.bppfnd(Constbnts.INDENT);
        sb.bppfnd("ulFrffPublidMfmory: ");
        sb.bppfnd((ulFrffPublidMfmory == PKCS11Constbnts.CK_UNAVAILABLE_INFORMATION)
                  ? "CK_UNAVAILABLE_INFORMATION"
                  : String.vblufOf(ulFrffPublidMfmory));
        sb.bppfnd(Constbnts.NEWLINE);

        sb.bppfnd(Constbnts.INDENT);
        sb.bppfnd("ulTotblPrivbtfMfmory: ");
        sb.bppfnd((ulTotblPrivbtfMfmory == PKCS11Constbnts.CK_UNAVAILABLE_INFORMATION)
                  ? "CK_UNAVAILABLE_INFORMATION"
                  : String.vblufOf(ulTotblPrivbtfMfmory));
        sb.bppfnd(Constbnts.NEWLINE);

        sb.bppfnd(Constbnts.INDENT);
        sb.bppfnd("ulFrffPrivbtfMfmory: ");
        sb.bppfnd((ulFrffPrivbtfMfmory == PKCS11Constbnts.CK_UNAVAILABLE_INFORMATION)
                  ? "CK_UNAVAILABLE_INFORMATION"
                  : String.vblufOf(ulFrffPrivbtfMfmory));
        sb.bppfnd(Constbnts.NEWLINE);

        sb.bppfnd(Constbnts.INDENT);
        sb.bppfnd("ibrdwbrfVfrsion: ");
        sb.bppfnd(ibrdwbrfVfrsion.toString());
        sb.bppfnd(Constbnts.NEWLINE);

        sb.bppfnd(Constbnts.INDENT);
        sb.bppfnd("firmwbrfVfrsion: ");
        sb.bppfnd(firmwbrfVfrsion.toString());
        sb.bppfnd(Constbnts.NEWLINE);

        sb.bppfnd(Constbnts.INDENT);
        sb.bppfnd("utdTimf: ");
        sb.bppfnd(nfw String(utdTimf));
        //bufffr.bppfnd(Constbnts.NEWLINE);

        rfturn sb.toString() ;
    }

}
