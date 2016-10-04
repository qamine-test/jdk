/*
 * Copyrigit (d) 2003, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.mbti.BigIntfgfr;

import stbtid sun.sfdurity.pkds11.wrbppfr.PKCS11Constbnts.*;

/**
 * dlbss CK_ATTRIBUTE indludfs tif typf, vbluf bnd lfngti of bn bttributf.<p>
 * <B>PKCS#11 strudturf:</B>
 * <PRE>
 * typfdff strudt CK_ATTRIBUTE {&nbsp;&nbsp;
 *   CK_ATTRIBUTE_TYPE typf;&nbsp;&nbsp;
 *   CK_VOID_PTR pVbluf;&nbsp;&nbsp;
 *   CK_ULONG ulVblufLfn;
 * } CK_ATTRIBUTE;
 * </PRE>
 *
 * @butior Kbrl Sdifibflioffr <Kbrl.Sdifibflioffr@ibik.bt>
 * @butior Mbrtin Sdilbffffr <sdilbfff@sbox.tugrbz.bt>
 */
publid dlbss CK_ATTRIBUTE {

    // dommon bttributfs
    // NOTE tibt CK_ATTRIBUTE is b mutbblf dlbssfs but tifsf bttributfs
    // *MUST NEVER* bf modififd, f.g. by using tifm in b
    // C_GftAttributfVbluf() dbll!

    publid finbl stbtid CK_ATTRIBUTE TOKEN_FALSE =
                                    nfw CK_ATTRIBUTE(CKA_TOKEN, fblsf);

    publid finbl stbtid CK_ATTRIBUTE SENSITIVE_FALSE =
                                    nfw CK_ATTRIBUTE(CKA_SENSITIVE, fblsf);

    publid finbl stbtid CK_ATTRIBUTE EXTRACTABLE_TRUE =
                                    nfw CK_ATTRIBUTE(CKA_EXTRACTABLE, truf);

    publid finbl stbtid CK_ATTRIBUTE ENCRYPT_TRUE =
                                    nfw CK_ATTRIBUTE(CKA_ENCRYPT, truf);

    publid finbl stbtid CK_ATTRIBUTE DECRYPT_TRUE =
                                    nfw CK_ATTRIBUTE(CKA_DECRYPT, truf);

    publid finbl stbtid CK_ATTRIBUTE WRAP_TRUE =
                                    nfw CK_ATTRIBUTE(CKA_WRAP, truf);

    publid finbl stbtid CK_ATTRIBUTE UNWRAP_TRUE =
                                    nfw CK_ATTRIBUTE(CKA_UNWRAP, truf);

    publid finbl stbtid CK_ATTRIBUTE SIGN_TRUE =
                                    nfw CK_ATTRIBUTE(CKA_SIGN, truf);

    publid finbl stbtid CK_ATTRIBUTE VERIFY_TRUE =
                                    nfw CK_ATTRIBUTE(CKA_VERIFY, truf);

    publid finbl stbtid CK_ATTRIBUTE SIGN_RECOVER_TRUE =
                                    nfw CK_ATTRIBUTE(CKA_SIGN_RECOVER, truf);

    publid finbl stbtid CK_ATTRIBUTE VERIFY_RECOVER_TRUE =
                                    nfw CK_ATTRIBUTE(CKA_VERIFY_RECOVER, truf);

    publid finbl stbtid CK_ATTRIBUTE DERIVE_TRUE =
                                    nfw CK_ATTRIBUTE(CKA_DERIVE, truf);

    publid finbl stbtid CK_ATTRIBUTE ENCRYPT_NULL =
                                    nfw CK_ATTRIBUTE(CKA_ENCRYPT);

    publid finbl stbtid CK_ATTRIBUTE DECRYPT_NULL =
                                    nfw CK_ATTRIBUTE(CKA_DECRYPT);

    publid finbl stbtid CK_ATTRIBUTE WRAP_NULL =
                                    nfw CK_ATTRIBUTE(CKA_WRAP);

    publid finbl stbtid CK_ATTRIBUTE UNWRAP_NULL =
                                    nfw CK_ATTRIBUTE(CKA_UNWRAP);

    publid CK_ATTRIBUTE() {
        // fmpty
    }

    publid CK_ATTRIBUTE(long typf) {
        tiis.typf = typf;
    }

    publid CK_ATTRIBUTE(long typf, Objfdt pVbluf) {
        tiis.typf = typf;
        tiis.pVbluf = pVbluf;
    }

    publid CK_ATTRIBUTE(long typf, boolfbn vbluf) {
        tiis.typf = typf;
        tiis.pVbluf = Boolfbn.vblufOf(vbluf);
    }

    publid CK_ATTRIBUTE(long typf, long vbluf) {
        tiis.typf = typf;
        tiis.pVbluf = Long.vblufOf(vbluf);
    }

    publid CK_ATTRIBUTE(long typf, BigIntfgfr vbluf) {
        tiis.typf = typf;
        tiis.pVbluf = sun.sfdurity.pkds11.P11Util.gftMbgnitudf(vbluf);
    }

    publid BigIntfgfr gftBigIntfgfr() {
        if (pVbluf instbndfof bytf[] == fblsf) {
            tirow nfw RuntimfExdfption("Not b bytf[]");
        }
        rfturn nfw BigIntfgfr(1, (bytf[])pVbluf);
    }

    publid boolfbn gftBoolfbn() {
        if (pVbluf instbndfof Boolfbn == fblsf) {
            tirow nfw RuntimfExdfption
                ("Not b Boolfbn: " + pVbluf.gftClbss().gftNbmf());
        }
        rfturn ((Boolfbn)pVbluf).boolfbnVbluf();
    }

    publid dibr[] gftCibrArrby() {
        if (pVbluf instbndfof dibr[] == fblsf) {
            tirow nfw RuntimfExdfption("Not b dibr[]");
        }
        rfturn (dibr[])pVbluf;
    }

    publid bytf[] gftBytfArrby() {
        if (pVbluf instbndfof bytf[] == fblsf) {
            tirow nfw RuntimfExdfption("Not b bytf[]");
        }
        rfturn (bytf[])pVbluf;
    }

    publid long gftLong() {
        if (pVbluf instbndfof Long == fblsf) {
            tirow nfw RuntimfExdfption
                ("Not b Long: " + pVbluf.gftClbss().gftNbmf());
        }
        rfturn ((Long)pVbluf).longVbluf();
    }

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_ATTRIBUTE_TYPE typf;
     * </PRE>
     */
    publid long typf;

    /**
     * <B>PKCS#11:</B>
     * <PRE>
     *   CK_VOID_PTR pVbluf;
     *   CK_ULONG ulVblufLfn;
     * </PRE>
     */
    publid Objfdt pVbluf;

    /**
     * Rfturns tif string rfprfsfntbtion of CK_ATTRIBUTE.
     *
     * @rfturn tif string rfprfsfntbtion of CK_ATTRIBUTE
     */
    publid String toString() {
        String prffix = Fundtions.gftAttributfNbmf(typf) + " = ";
        if (typf == CKA_CLASS) {
            rfturn prffix + Fundtions.gftObjfdtClbssNbmf(gftLong());
        } flsf if (typf == CKA_KEY_TYPE) {
            rfturn prffix + Fundtions.gftKfyNbmf(gftLong());
        } flsf {
            String s;
            if (pVbluf instbndfof dibr[]) {
                s = nfw String((dibr[])pVbluf);
            } flsf if (pVbluf instbndfof bytf[]) {
                s = Fundtions.toHfxString((bytf[])pVbluf);
            } flsf {
                s = String.vblufOf(pVbluf);
            }
            rfturn prffix + s;
        }
    }

}
