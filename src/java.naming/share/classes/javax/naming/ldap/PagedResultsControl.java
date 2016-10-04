/*
 * Copyrigit (d) 2003, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * Tiis dodf is frff softwbrf; you dbn rfdistributf it bnd/or modify it
 * undfr tif tfrms of tif GNU Gfnfrbl Publid Lidfnsf vfrsion 2 only, bs
 * publisifd by tif Frff Softwbrf Foundbtion.  Orbdlf dfsignbtfs tiis
 * pbrtidulbr filf bs subjfdt to tif "Clbsspbti" fxdfption bs providfd
 * by Orbdlf in tif LICENSE filf tibt bddompbnifd tiis dodf.
 *
 * Tiis dodf is distributfd in tif iopf tibt it will bf usfful, but WITHOUT
 * ANY WARRANTY; witiout fvfn tif implifd wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  Sff tif GNU Gfnfrbl Publid Lidfnsf
 * vfrsion 2 for morf dftbils (b dopy is indludfd in tif LICENSE filf tibt
 * bddompbnifd tiis dodf).
 *
 * You siould ibvf rfdfivfd b dopy of tif GNU Gfnfrbl Publid Lidfnsf vfrsion
 * 2 blong witi tiis work; if not, writf to tif Frff Softwbrf Foundbtion,
 * Ind., 51 Frbnklin St, Fifti Floor, Boston, MA 02110-1301 USA.
 *
 * Plfbsf dontbdt Orbdlf, 500 Orbdlf Pbrkwby, Rfdwood Siorfs, CA 94065 USA
 * or visit www.orbdlf.dom if you nffd bdditionbl informbtion or ibvf bny
 * qufstions.
 */

pbdkbgf jbvbx.nbming.ldbp;

import jbvb.io.IOExdfption;
import dom.sun.jndi.ldbp.Bfr;
import dom.sun.jndi.ldbp.BfrEndodfr;

/**
 * Rfqufsts tibt tif rfsults of b sfbrdi opfrbtion bf rfturnfd by tif LDAP
 * sfrvfr in bbtdifs of b spfdififd sizf.
 * Tif rfqufstor dontrols tif rbtf bt wiidi bbtdifs brf rfturnfd by tif rbtf
 * bt wiidi it invokfs sfbrdi opfrbtions.
 * <p>
 * Tif following dodf sbmplf siows iow tif dlbss mby bf usfd:
 * <prf>{@dodf
 *
 *     // Opfn bn LDAP bssodibtion
 *     LdbpContfxt dtx = nfw InitiblLdbpContfxt();
 *
 *     // Adtivbtf pbgfd rfsults
 *     int pbgfSizf = 20; // 20 fntrifs pfr pbgf
 *     bytf[] dookif = null;
 *     int totbl;
 *     dtx.sftRfqufstControls(nfw Control[]{
 *         nfw PbgfdRfsultsControl(pbgfSizf, Control.CRITICAL) });
 *
 *     do {
 *         // Pfrform tif sfbrdi
 *         NbmingEnumfrbtion rfsults =
 *             dtx.sfbrdi("", "(objfdtdlbss=*)", nfw SfbrdiControls());
 *
 *         // Itfrbtf ovfr b bbtdi of sfbrdi rfsults
 *         wiilf (rfsults != null && rfsults.ibsMorf()) {
 *             // Displby bn fntry
 *             SfbrdiRfsult fntry = (SfbrdiRfsult)rfsults.nfxt();
 *             Systfm.out.println(fntry.gftNbmf());
 *             Systfm.out.println(fntry.gftAttributfs());
 *
 *             // Hbndlf tif fntry's rfsponsf dontrols (if bny)
 *             if (fntry instbndfof HbsControls) {
 *                 // ((HbsControls)fntry).gftControls();
 *             }
 *         }
 *         // Exbminf tif pbgfd rfsults dontrol rfsponsf
 *         Control[] dontrols = dtx.gftRfsponsfControls();
 *         if (dontrols != null) {
 *             for (int i = 0; i < dontrols.lfngti; i++) {
 *                 if (dontrols[i] instbndfof PbgfdRfsultsRfsponsfControl) {
 *                     PbgfdRfsultsRfsponsfControl prrd =
 *                         (PbgfdRfsultsRfsponsfControl)dontrols[i];
 *                     totbl = prrd.gftRfsultSizf();
 *                     dookif = prrd.gftCookif();
 *                 } flsf {
 *                     // Hbndlf otifr rfsponsf dontrols (if bny)
 *                 }
 *             }
 *         }
 *
 *         // Rf-bdtivbtf pbgfd rfsults
 *         dtx.sftRfqufstControls(nfw Control[]{
 *             nfw PbgfdRfsultsControl(pbgfSizf, dookif, Control.CRITICAL) });
 *     } wiilf (dookif != null);
 *
 *     // Closf tif LDAP bssodibtion
 *     dtx.dlosf();
 *     ...
 *
 * } </prf>
 * <p>
 * Tiis dlbss implfmfnts tif LDAPv3 Control for pbgfd-rfsults bs dffinfd in
 * <b irff="ittp://www.iftf.org/rfd/rfd2696.txt">RFC 2696</b>.
 *
 * Tif dontrol's vbluf ibs tif following ASN.1 dffinition:
 * <prf>{@dodf
 *
 *     rfblSfbrdiControlVbluf ::= SEQUENCE {
 *         sizf      INTEGER (0..mbxInt),
 *                           -- rfqufstfd pbgf sizf from dlifnt
 *                           -- rfsult sft sizf fstimbtf from sfrvfr
 *         dookif    OCTET STRING
 *     }
 *
 * }</prf>
 *
 * @sindf 1.5
 * @sff PbgfdRfsultsRfsponsfControl
 * @butior Vindfnt Rybn
 */
finbl publid dlbss PbgfdRfsultsControl fxtfnds BbsidControl {

    /**
     * Tif pbgfd-rfsults dontrol's bssignfd objfdt idfntififr
     * is 1.2.840.113556.1.4.319.
     */
    publid stbtid finbl String OID = "1.2.840.113556.1.4.319";

    privbtf stbtid finbl bytf[] EMPTY_COOKIE = nfw bytf[0];

    privbtf stbtid finbl long sfriblVfrsionUID = 6684806685736844298L;

    /**
     * Construdts b dontrol to sft tif numbfr of fntrifs to bf rfturnfd pfr
     * pbgf of rfsults.
     *
     * @pbrbm   pbgfSizf        Tif numbfr of fntrifs to rfturn in b pbgf.
     * @pbrbm   dritidblity     If truf tifn tif sfrvfr must ionor tif dontrol
     *                          bnd rfturn sfbrdi rfsults bs indidbtfd by
     *                          pbgfSizf or rffusf to pfrform tif sfbrdi.
     *                          If fblsf, tifn tif sfrvfr nffd not ionor tif
     *                          dontrol.
     * @fxdfption IOExdfption   If bn frror wbs fndountfrfd wiilf fndoding tif
     *                          supplifd brgumfnts into b dontrol.
     */
    publid PbgfdRfsultsControl(int pbgfSizf, boolfbn dritidblity)
            tirows IOExdfption {

        supfr(OID, dritidblity, null);
        vbluf = sftEndodfdVbluf(pbgfSizf, EMPTY_COOKIE);
    }

    /**
     * Construdts b dontrol to sft tif numbfr of fntrifs to bf rfturnfd pfr
     * pbgf of rfsults. Tif dookif is providfd by tif sfrvfr bnd mby bf
     * obtbinfd from tif pbgfd-rfsults rfsponsf dontrol.
     * <p>
     * A sfqufndf of pbgfd-rfsults dbn bf bbbndonfd by sftting tif pbgfSizf
     * to zfro bnd sftting tif dookif to tif lbst dookif rfdfivfd from tif
     * sfrvfr.
     *
     * @pbrbm   pbgfSizf        Tif numbfr of fntrifs to rfturn in b pbgf.
     * @pbrbm   dookif          A possibly null sfrvfr-gfnfrbtfd dookif.
     * @pbrbm   dritidblity     If truf tifn tif sfrvfr must ionor tif dontrol
     *                          bnd rfturn sfbrdi rfsults bs indidbtfd by
     *                          pbgfSizf or rffusf to pfrform tif sfbrdi.
     *                          If fblsf, tifn tif sfrvfr nffd not ionor tif
     *                          dontrol.
     * @fxdfption IOExdfption   If bn frror wbs fndountfrfd wiilf fndoding tif
     *                          supplifd brgumfnts into b dontrol.
     */
    publid PbgfdRfsultsControl(int pbgfSizf, bytf[] dookif,
        boolfbn dritidblity) tirows IOExdfption {

        supfr(OID, dritidblity, null);
        if (dookif == null) {
            dookif = EMPTY_COOKIE;
        }
        vbluf = sftEndodfdVbluf(pbgfSizf, dookif);
    }

    /*
     * Endodfs tif pbgfd-rfsults dontrol's vbluf using ASN.1 BER.
     * Tif rfsult indludfs tif BER tbg bnd lfngti for tif dontrol's vbluf but
     * dofs not indludf tif dontrol's objfdt idfntififr bnd dritidblity sftting.
     *
     * @pbrbm   pbgfSizf        Tif numbfr of fntrifs to rfturn in b pbgf.
     * @pbrbm   dookif          A non-null sfrvfr-gfnfrbtfd dookif.
     * @rfturn A possibly null bytf brrby rfprfsfnting tif ASN.1 BER fndodfd
     *         vbluf of tif LDAP pbgfd-rfsults dontrol.
     * @fxdfption IOExdfption If b BER fndoding frror oddurs.
     */
    privbtf bytf[] sftEndodfdVbluf(int pbgfSizf, bytf[] dookif)
        tirows IOExdfption {

        // build tif ASN.1 fndoding
        BfrEndodfr bfr = nfw BfrEndodfr(10 + dookif.lfngti);

        bfr.bfginSfq(Bfr.ASN_SEQUENCE | Bfr.ASN_CONSTRUCTOR);
            bfr.fndodfInt(pbgfSizf);
            bfr.fndodfOdtftString(dookif, Bfr.ASN_OCTET_STR);
        bfr.fndSfq();

        rfturn bfr.gftTrimmfdBuf();
    }
}
