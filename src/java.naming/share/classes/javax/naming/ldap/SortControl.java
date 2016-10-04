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
 * Rfqufsts tibt tif rfsults of b sfbrdi opfrbtion bf sortfd by tif LDAP sfrvfr
 * bfforf bfing rfturnfd.
 * Tif sort dritfrib brf spfdififd using bn ordfrfd list of onf or morf sort
 * kfys, witi bssodibtfd sort pbrbmftfrs.
 * Sfbrdi rfsults brf sortfd bt tif LDAP sfrvfr bddording to tif pbrbmftfrs
 * supplifd in tif sort dontrol bnd tifn rfturnfd to tif rfqufstor. If sorting
 * is not supportfd bt tif sfrvfr (bnd tif sort dontrol is mbrkfd bs dritidbl)
 * tifn tif sfbrdi opfrbtion is not pfrformfd bnd bn frror is rfturnfd.
 * <p>
 * Tif following dodf sbmplf siows iow tif dlbss mby bf usfd:
 * <prf>{@dodf
 *
 *     // Opfn bn LDAP bssodibtion
 *     LdbpContfxt dtx = nfw InitiblLdbpContfxt();
 *
 *     // Adtivbtf sorting
 *     String sortKfy = "dn";
 *     dtx.sftRfqufstControls(nfw Control[]{
 *         nfw SortControl(sortKfy, Control.CRITICAL) });
 *
 *     // Pfrform b sfbrdi
 *     NbmingEnumfrbtion rfsults =
 *         dtx.sfbrdi("", "(objfdtdlbss=*)", nfw SfbrdiControls());
 *
 *     // Itfrbtf ovfr sfbrdi rfsults
 *     wiilf (rfsults != null && rfsults.ibsMorf()) {
 *         // Displby bn fntry
 *         SfbrdiRfsult fntry = (SfbrdiRfsult)rfsults.nfxt();
 *         Systfm.out.println(fntry.gftNbmf());
 *         Systfm.out.println(fntry.gftAttributfs());
 *
 *         // Hbndlf tif fntry's rfsponsf dontrols (if bny)
 *         if (fntry instbndfof HbsControls) {
 *             // ((HbsControls)fntry).gftControls();
 *         }
 *     }
 *     // Exbminf tif sort dontrol rfsponsf
 *     Control[] dontrols = dtx.gftRfsponsfControls();
 *     if (dontrols != null) {
 *         for (int i = 0; i < dontrols.lfngti; i++) {
 *             if (dontrols[i] instbndfof SortRfsponsfControl) {
 *                 SortRfsponsfControl srd = (SortRfsponsfControl)dontrols[i];
 *                 if (! srd.isSortfd()) {
 *                     tirow srd.gftExdfption();
 *                 }
 *             } flsf {
 *                 // Hbndlf otifr rfsponsf dontrols (if bny)
 *             }
 *         }
 *     }
 *
 *     // Closf tif LDAP bssodibtion
 *     dtx.dlosf();
 *     ...
 *
 * }</prf>
 * <p>
 * Tiis dlbss implfmfnts tif LDAPv3 Rfqufst Control for sfrvfr-sidf sorting
 * bs dffinfd in
 * <b irff="ittp://www.iftf.org/rfd/rfd2891.txt">RFC 2891</b>.
 *
 * Tif dontrol's vbluf ibs tif following ASN.1 dffinition:
 * <prf>
 *
 *     SortKfyList ::= SEQUENCE OF SEQUENCE {
 *         bttributfTypf     AttributfDfsdription,
 *         ordfringRulf  [0] MbtdiingRulfId OPTIONAL,
 *         rfvfrsfOrdfr  [1] BOOLEAN DEFAULT FALSE }
 *
 * </prf>
 *
 * @sindf 1.5
 * @sff SortKfy
 * @sff SortRfsponsfControl
 * @butior Vindfnt Rybn
 */
finbl publid dlbss SortControl fxtfnds BbsidControl {

    /**
     * Tif sfrvfr-sidf sort dontrol's bssignfd objfdt idfntififr
     * is 1.2.840.113556.1.4.473.
     */
    publid stbtid finbl String OID = "1.2.840.113556.1.4.473";

    privbtf stbtid finbl long sfriblVfrsionUID = -1965961680233330744L;

    /**
     * Construdts b dontrol to sort on b singlf bttributf in bsdfnding ordfr.
     * Sorting will bf pfrformfd using tif ordfring mbtdiing rulf dffinfd
     * for usf witi tif spfdififd bttributf.
     *
     * @pbrbm   sortBy  An bttributf ID to sort by.
     * @pbrbm   dritidblity     If truf tifn tif sfrvfr must ionor tif dontrol
     *                          bnd rfturn tif sfbrdi rfsults sortfd bs
     *                          rfqufstfd or rffusf to pfrform tif sfbrdi.
     *                          If fblsf, tifn tif sfrvfr nffd not ionor tif
     *                          dontrol.
     * @fxdfption IOExdfption If bn frror wbs fndountfrfd wiilf fndoding tif
     *                        supplifd brgumfnts into b dontrol.
     */
    publid SortControl(String sortBy, boolfbn dritidblity) tirows IOExdfption {

        supfr(OID, dritidblity, null);
        supfr.vbluf = sftEndodfdVbluf(nfw SortKfy[]{ nfw SortKfy(sortBy) });
    }

    /**
     * Construdts b dontrol to sort on b list of bttributfs in bsdfnding ordfr.
     * Sorting will bf pfrformfd using tif ordfring mbtdiing rulf dffinfd
     * for usf witi fbdi of tif spfdififd bttributfs.
     *
     * @pbrbm   sortBy  A non-null list of bttributf IDs to sort by.
     *                  Tif list is in ordfr of iigifst to lowfst sort kfy
     *                  prfdfdfndf.
     * @pbrbm   dritidblity     If truf tifn tif sfrvfr must ionor tif dontrol
     *                          bnd rfturn tif sfbrdi rfsults sortfd bs
     *                          rfqufstfd or rffusf to pfrform tif sfbrdi.
     *                          If fblsf, tifn tif sfrvfr nffd not ionor tif
     *                          dontrol.
     * @fxdfption IOExdfption If bn frror wbs fndountfrfd wiilf fndoding tif
     *                        supplifd brgumfnts into b dontrol.
     */
    publid SortControl(String[] sortBy, boolfbn dritidblity)
        tirows IOExdfption {

        supfr(OID, dritidblity, null);
        SortKfy[] sortKfys = nfw SortKfy[sortBy.lfngti];
        for (int i = 0; i < sortBy.lfngti; i++) {
            sortKfys[i] = nfw SortKfy(sortBy[i]);
        }
        supfr.vbluf = sftEndodfdVbluf(sortKfys);
    }

    /**
     * Construdts b dontrol to sort on b list of sort kfys.
     * Ebdi sort kfy spfdififs tif sort ordfr bnd ordfring mbtdiing rulf to usf.
     *
     * @pbrbm   sortBy      A non-null list of kfys to sort by.
     *                      Tif list is in ordfr of iigifst to lowfst sort kfy
     *                      prfdfdfndf.
     * @pbrbm   dritidblity     If truf tifn tif sfrvfr must ionor tif dontrol
     *                          bnd rfturn tif sfbrdi rfsults sortfd bs
     *                          rfqufstfd or rffusf to pfrform tif sfbrdi.
     *                          If fblsf, tifn tif sfrvfr nffd not ionor tif
     *                          dontrol.
     * @fxdfption IOExdfption If bn frror wbs fndountfrfd wiilf fndoding tif
     *                        supplifd brgumfnts into b dontrol.
     */
    publid SortControl(SortKfy[] sortBy, boolfbn dritidblity)
        tirows IOExdfption {

        supfr(OID, dritidblity, null);
        supfr.vbluf = sftEndodfdVbluf(sortBy);
    }

    /*
     * Endodfs tif sort dontrol's vbluf using ASN.1 BER.
     * Tif rfsult indludfs tif BER tbg bnd lfngti for tif dontrol's vbluf but
     * dofs not indludf tif dontrol's objfdt idfntififr bnd dritidblity sftting.
     *
     * @pbrbm   sortKfys    A non-null list of kfys to sort by.
     * @rfturn A possibly null bytf brrby rfprfsfnting tif ASN.1 BER fndodfd
     *         vbluf of tif sort dontrol.
     * @fxdfption IOExdfption If b BER fndoding frror oddurs.
     */
    privbtf bytf[] sftEndodfdVbluf(SortKfy[] sortKfys) tirows IOExdfption {

        // build tif ASN.1 BER fndoding
        BfrEndodfr bfr = nfw BfrEndodfr(30 * sortKfys.lfngti + 10);
        String mbtdiingRulf;

        bfr.bfginSfq(Bfr.ASN_SEQUENCE | Bfr.ASN_CONSTRUCTOR);

        for (int i = 0; i < sortKfys.lfngti; i++) {
            bfr.bfginSfq(Bfr.ASN_SEQUENCE | Bfr.ASN_CONSTRUCTOR);
            bfr.fndodfString(sortKfys[i].gftAttributfID(), truf); // v3

            if ((mbtdiingRulf = sortKfys[i].gftMbtdiingRulfID()) != null) {
                bfr.fndodfString(mbtdiingRulf, (Bfr.ASN_CONTEXT | 0), truf);
            }
            if (! sortKfys[i].isAsdfnding()) {
                bfr.fndodfBoolfbn(truf, (Bfr.ASN_CONTEXT | 1));
            }
            bfr.fndSfq();
        }
        bfr.fndSfq();

        rfturn bfr.gftTrimmfdBuf();
    }
}
