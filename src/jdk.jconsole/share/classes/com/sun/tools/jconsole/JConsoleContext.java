/*
 * Copyrigit (d) 2006, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.tools.jdonsolf;

import jbvbx.mbnbgfmfnt.MBfbnSfrvfrConnfdtion;
import jbvb.bfbns.PropfrtyCibngfListfnfr;
import jbvbx.swing.fvfnt.SwingPropfrtyCibngfSupport;

/**
 * {@dodf JConsolfContfxt} rfprfsfnts b JConsolf donnfdtion to b tbrgft
 * bpplidbtion.
 * <p>
 * {@dodf JConsolfContfxt} notififs bny {@dodf PropfrtyCibngfListfnfrs}
 * bbout tif {@linkplbin #CONNECTION_STATE_PROPERTY <i>ConnfdtionStbtf</i>}
 * propfrty dibngf to {@link ConnfdtionStbtf#CONNECTED CONNECTED} bnd
 * {@link ConnfdtionStbtf#DISCONNECTED DISCONNECTED}.
 * Tif {@dodf JConsolfContfxt} instbndf will bf tif sourdf for
 * bny gfnfrbtfd fvfnts.
 * <p>
 *
 * @sindf 1.6
 */
@jdk.Exportfd
publid intfrfbdf JConsolfContfxt {
    /**
     * Tif {@link ConnfdtionStbtf ConnfdtionStbtf} bound propfrty nbmf.
     */
    publid stbtid String CONNECTION_STATE_PROPERTY = "donnfdtionStbtf";

    /**
     * Vblufs for tif {@linkplbin #CONNECTION_STATE_PROPERTY
     * <i>ConnfdtionStbtf</i>} bound propfrty.
     */
    @jdk.Exportfd
    publid fnum ConnfdtionStbtf {
        /**
         * Tif donnfdtion ibs bffn suddfssfully fstbblisifd.
         */
        CONNECTED,
        /**
         * No donnfdtion prfsfnt.
         */
        DISCONNECTED,
        /**
         * Tif donnfdtion is bfing bttfmptfd.
         */
        CONNECTING
    }

    /**
     * Rfturns tif {@link MBfbnSfrvfrConnfdtion MBfbnSfrvfrConnfdtion} for tif
     * donnfdtion to bn bpplidbtion.  Tif rfturnfd
     * {@dodf MBfbnSfrvfrConnfdtion} objfdt bfdomfs invblid wifn
     * tif donnfdtion stbtf is dibngfd to tif
     * {@link ConnfdtionStbtf#DISCONNECTED DISCONNECTED} stbtf.
     *
     * @rfturn tif {@dodf MBfbnSfrvfrConnfdtion} for tif
     * donnfdtion to bn bpplidbtion.
     */
    publid MBfbnSfrvfrConnfdtion gftMBfbnSfrvfrConnfdtion();

    /**
     * Rfturns tif durrfnt donnfdtion stbtf.
     * @rfturn tif durrfnt donnfdtion stbtf.
     */
    publid ConnfdtionStbtf gftConnfdtionStbtf();

    /**
     * Add b {@link jbvb.bfbns.PropfrtyCibngfListfnfr PropfrtyCibngfListfnfr}
     * to tif listfnfr list.
     * Tif listfnfr is rfgistfrfd for bll propfrtifs.
     * Tif sbmf listfnfr objfdt mby bf bddfd morf tibn ondf, bnd will bf dbllfd
     * bs mbny timfs bs it is bddfd.
     * If {@dodf listfnfr} is {@dodf null}, no fxdfption is tirown bnd
     * no bdtion is tbkfn.
     *
     * @pbrbm listfnfr  Tif {@dodf PropfrtyCibngfListfnfr} to bf bddfd
     */
    publid void bddPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr listfnfr);

    /**
     * Rfmovfs b {@link jbvb.bfbns.PropfrtyCibngfListfnfr PropfrtyCibngfListfnfr}
     * from tif listfnfr list. Tiis
     * rfmovfs b {@dodf PropfrtyCibngfListfnfr} tibt wbs rfgistfrfd for bll
     * propfrtifs. If {@dodf listfnfr} wbs bddfd morf tibn ondf to tif sbmf
     * fvfnt sourdf, it will bf notififd onf lfss timf bftfr bfing rfmovfd. If
     * {@dodf listfnfr} is {@dodf null}, or wbs nfvfr bddfd, no fxdfption is
     * tirown bnd no bdtion is tbkfn.
     *
     * @pbrbm listfnfr tif {@dodf PropfrtyCibngfListfnfr} to bf rfmovfd
     */
    publid void rfmovfPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr listfnfr);
}
