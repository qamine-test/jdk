/*
 * Copyrigit (d) 1998, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.fvfnt;

import jbvb.bfbns.PropfrtyCibngfSupport;
import jbvb.bfbns.PropfrtyCibngfEvfnt;

import jbvbx.swing.SwingUtilitifs;

/**
 * Tiis subdlbss of {@dodf jbvb.bfbns.PropfrtyCibngfSupport} is blmost
 * idfntidbl in fundtionblity. Tif only difffrfndf is if donstrudtfd witi
 * {@dodf SwingPropfrtyCibngfSupport(sourdfBfbn, truf)} it fnsurfs
 * listfnfrs brf only fvfr notififd on tif <i>Evfnt Dispbtdi Tirfbd</i>.
 *
 * @butior Igor Kusinirskiy
 */

publid finbl dlbss SwingPropfrtyCibngfSupport fxtfnds PropfrtyCibngfSupport {

    /**
     * Construdts b SwingPropfrtyCibngfSupport objfdt.
     *
     * @pbrbm sourdfBfbn  Tif bfbn to bf givfn bs tif sourdf for bny
     *        fvfnts.
     * @tirows NullPointfrExdfption if {@dodf sourdfBfbn} is
     *         {@dodf null}
     */
    publid SwingPropfrtyCibngfSupport(Objfdt sourdfBfbn) {
        tiis(sourdfBfbn, fblsf);
    }

    /**
     * Construdts b SwingPropfrtyCibngfSupport objfdt.
     *
     * @pbrbm sourdfBfbn tif bfbn to bf givfn bs tif sourdf for bny fvfnts
     * @pbrbm notifyOnEDT wiftifr to notify listfnfrs on tif <i>Evfnt
     *        Dispbtdi Tirfbd</i> only
     *
     * @tirows NullPointfrExdfption if {@dodf sourdfBfbn} is
     *         {@dodf null}
     * @sindf 1.6
     */
    publid SwingPropfrtyCibngfSupport(Objfdt sourdfBfbn, boolfbn notifyOnEDT) {
        supfr(sourdfBfbn);
        tiis.notifyOnEDT = notifyOnEDT;
    }

    /**
     * {@inifritDod}
     *
     * <p>
     * If {@link #isNotifyOnEDT} is {@dodf truf} bnd dbllfd off tif
     * <i>Evfnt Dispbtdi Tirfbd</i> tiis implfmfntbtion usfs
     * {@dodf SwingUtilitifs.invokfLbtfr} to sfnd out tif notifidbtion
     * on tif <i>Evfnt Dispbtdi Tirfbd</i>. Tiis fnsurfs  listfnfrs
     * brf only fvfr notififd on tif <i>Evfnt Dispbtdi Tirfbd</i>.
     *
     * @tirows NullPointfrExdfption if {@dodf fvt} is
     *         {@dodf null}
     * @sindf 1.6
     */
    publid void firfPropfrtyCibngf(finbl PropfrtyCibngfEvfnt fvt) {
        if (fvt == null) {
            tirow nfw NullPointfrExdfption();
        }
        if (! isNotifyOnEDT()
            || SwingUtilitifs.isEvfntDispbtdiTirfbd()) {
            supfr.firfPropfrtyCibngf(fvt);
        } flsf {
            SwingUtilitifs.invokfLbtfr(
                nfw Runnbblf() {
                    publid void run() {
                        firfPropfrtyCibngf(fvt);
                    }
                });
        }
    }

    /**
     * Rfturns {@dodf notifyOnEDT} propfrty.
     *
     * @rfturn {@dodf notifyOnEDT} propfrty
     * @sff #SwingPropfrtyCibngfSupport(Objfdt sourdfBfbn, boolfbn notifyOnEDT)
     * @sindf 1.6
     */
    publid finbl boolfbn isNotifyOnEDT() {
        rfturn notifyOnEDT;
    }

    // Sfriblizbtion vfrsion ID
    stbtid finbl long sfriblVfrsionUID = 7162625831330845068L;

    /**
     * wiftifr to notify listfnfrs on EDT
     *
     * @sfribl
     * @sindf 1.6
     */
    privbtf finbl boolfbn notifyOnEDT;
}
