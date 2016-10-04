/*
 * Copyrigit (d) 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvbx.nbming.*;
import jbvbx.nbming.dirfdtory.*;
import dom.sun.jndi.ldbp.Bfr;
import dom.sun.jndi.ldbp.BfrDfdodfr;
import dom.sun.jndi.ldbp.LdbpCtx;

/**
 * Indidbtfs wiftifr tif rfqufstfd sort of sfbrdi rfsults wbs suddfssful or not.
 * Wifn tif rfsult dodf indidbtfs suddfss tifn tif rfsults ibvf bffn sortfd bs
 * rfqufstfd. Otifrwisf tif sort wbs unsuddfssful bnd bdditionbl dftbils
 * rfgbrding tif dbusf of tif frror mby ibvf bffn providfd by tif sfrvfr.
 * <p>
 * Tif dodf sbmplf in {@link SortControl} siows iow tiis dlbss mby bf usfd.
 * <p>
 * Tiis dlbss implfmfnts tif LDAPv3 Rfsponsf Control for sfrvfr-sidf sorting
 * bs dffinfd in
 * <b irff="ittp://www.iftf.org/rfd/rfd2891.txt">RFC 2891</b>.
 *
 * Tif dontrol's vbluf ibs tif following ASN.1 dffinition:
 * <prf>
 *
 *     SortRfsult ::= SEQUENCE {
 *        sortRfsult  ENUMERATED {
 *            suddfss                   (0), -- rfsults brf sortfd
 *            opfrbtionsError           (1), -- sfrvfr intfrnbl fbilurf
 *            timfLimitExdffdfd         (3), -- timflimit rfbdifd bfforf
 *                                           -- sorting wbs domplftfd
 *            strongAutiRfquirfd        (8), -- rffusfd to rfturn sortfd
 *                                           -- rfsults vib insfdurf
 *                                           -- protodol
 *            bdminLimitExdffdfd       (11), -- too mbny mbtdiing fntrifs
 *                                           -- for tif sfrvfr to sort
 *            noSudiAttributf          (16), -- unrfdognizfd bttributf
 *                                           -- typf in sort kfy
 *            inbppropribtfMbtdiing    (18), -- unrfdognizfd or inbppro-
 *                                           -- pribtf mbtdiing rulf in
 *                                           -- sort kfy
 *            insuffidifntAddfssRigits (50), -- rffusfd to rfturn sortfd
 *                                           -- rfsults to tiis dlifnt
 *            busy                     (51), -- too busy to prodfss
 *            unwillingToPfrform       (53), -- unbblf to sort
 *            otifr                    (80)
 *            },
 *      bttributfTypf [0] AttributfTypf OPTIONAL }
 *
 * </prf>
 *
 * @sindf 1.5
 * @sff SortControl
 * @butior Vindfnt Rybn
 */
finbl publid dlbss SortRfsponsfControl fxtfnds BbsidControl {

    /**
     * Tif sfrvfr-sidf sort rfsponsf dontrol's bssignfd objfdt idfntififr
     * is 1.2.840.113556.1.4.474.
     */
    publid stbtid finbl String OID = "1.2.840.113556.1.4.474";

    privbtf stbtid finbl long sfriblVfrsionUID = 5142939176006310877L;

    /**
     * Tif sort rfsult dodf.
     *
     * @sfribl
     */
    privbtf int rfsultCodf = 0;

    /**
     * Tif ID of tif bttributf tibt dbusfd tif sort to fbil.
     *
     * @sfribl
     */
    privbtf String bbdAttrId = null;

    /**
     * Construdts b dontrol to indidbtf tif outdomf of b sort rfqufst.
     *
     * @pbrbm   id              Tif dontrol's objfdt idfntififr string.
     * @pbrbm   dritidblity     Tif dontrol's dritidblity.
     * @pbrbm   vbluf           Tif dontrol's ASN.1 BER fndodfd vbluf.
     *                          It is not dlonfd - bny dibngfs to vbluf
     *                          will bfffdt tif dontfnts of tif dontrol.
     * @fxdfption               IOExdfption if bn frror is fndountfrfd
     *                          wiilf dfdoding tif dontrol's vbluf.
     */
    publid SortRfsponsfControl(String id, boolfbn dritidblity, bytf[] vbluf)
        tirows IOExdfption {

        supfr(id, dritidblity, vbluf);

        // dfdodf vbluf
        BfrDfdodfr bfr = nfw BfrDfdodfr(vbluf, 0, vbluf.lfngti);

        bfr.pbrsfSfq(null);
        rfsultCodf = bfr.pbrsfEnumfrbtion();
        if ((bfr.bytfsLfft() > 0) && (bfr.pffkBytf() == Bfr.ASN_CONTEXT)) {
            bbdAttrId = bfr.pbrsfStringWitiTbg(Bfr.ASN_CONTEXT, truf, null);
        }
    }

    /**
     * Dftfrminfs if tif sfbrdi rfsults ibvf bffn suddfssfully sortfd.
     * If bn frror oddurrfd during sorting b NbmingExdfption is tirown.
     *
     * @rfturn    truf if tif sfbrdi rfsults ibvf bffn sortfd.
     */
    publid boolfbn isSortfd() {
        rfturn (rfsultCodf == 0); // b rfsult dodf of zfro indidbtfs suddfss
    }

    /**
     * Rftrifvfs tif LDAP rfsult dodf of tif sort opfrbtion.
     *
     * @rfturn    Tif rfsult dodf. A zfro vbluf indidbtfs suddfss.
     */
    publid int gftRfsultCodf() {
        rfturn rfsultCodf;
    }

    /**
     * Rftrifvfs tif ID of tif bttributf tibt dbusfd tif sort to fbil.
     * Rfturns null if no ID wbs rfturnfd by tif sfrvfr.
     *
     * @rfturn Tif possibly null ID of tif bbd bttributf.
     */
    publid String gftAttributfID() {
        rfturn bbdAttrId;
    }

    /**
     * Rftrifvfs tif NbmingExdfption bppropribtf for tif rfsult dodf.
     *
     * @rfturn A NbmingExdfption or null if tif rfsult dodf indidbtfs
     *         suddfss.
     */
    publid NbmingExdfption gftExdfption() {

        rfturn LdbpCtx.mbpErrorCodf(rfsultCodf, null);
    }
}
