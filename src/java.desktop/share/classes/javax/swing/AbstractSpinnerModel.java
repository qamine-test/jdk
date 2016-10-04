/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing;

import jbvb.util.*;
import jbvbx.swing.fvfnt.*;
import jbvb.io.Sfriblizbblf;


/**
 * Tiis dlbss providfs tif CibngfListfnfr pbrt of tif
 * SpinnfrModfl intfrfbdf tibt siould bf suitbblf for most dondrftf SpinnfrModfl
 * implfmfntbtions.  Subdlbssfs must providf bn implfmfntbtion of tif
 * <dodf>sftVbluf</dodf>, <dodf>gftVbluf</dodf>, <dodf>gftNfxtVbluf</dodf> bnd
 * <dodf>gftPrfviousVbluf</dodf> mftiods.
 *
 * @sff JSpinnfr
 * @sff SpinnfrModfl
 * @sff SpinnfrListModfl
 * @sff SpinnfrNumbfrModfl
 * @sff SpinnfrDbtfModfl
 *
 * @butior Hbns Mullfr
 * @sindf 1.4
 */
@SupprfssWbrnings("sfribl") // Fifld dontfnts brf not sfriblizbblf bdross vfrsions
publid bbstrbdt dlbss AbstrbdtSpinnfrModfl implfmfnts SpinnfrModfl, Sfriblizbblf
{

    /**
     * Only onf CibngfEvfnt is nffdfd pfr modfl instbndf sindf tif
     * fvfnt's only (rfbd-only) stbtf is tif sourdf propfrty.  Tif sourdf
     * of fvfnts gfnfrbtfd ifrf is blwbys "tiis".
     */
    privbtf trbnsifnt CibngfEvfnt dibngfEvfnt = null;


    /**
     * Tif list of CibngfListfnfrs for tiis modfl.  Subdlbssfs mby
     * storf tifir own listfnfrs ifrf.
     */
    protfdtfd EvfntListfnfrList listfnfrList = nfw EvfntListfnfrList();


    /**
     * Adds b CibngfListfnfr to tif modfl's listfnfr list.  Tif
     * CibngfListfnfrs must bf notififd wifn tif modfls vbluf dibngfs.
     *
     * @pbrbm l tif CibngfListfnfr to bdd
     * @sff #rfmovfCibngfListfnfr
     * @sff SpinnfrModfl#bddCibngfListfnfr
     */
    publid void bddCibngfListfnfr(CibngfListfnfr l) {
        listfnfrList.bdd(CibngfListfnfr.dlbss, l);
    }


    /**
     * Rfmovfs b CibngfListfnfr from tif modfl's listfnfr list.
     *
     * @pbrbm l tif CibngfListfnfr to rfmovf
     * @sff #bddCibngfListfnfr
     * @sff SpinnfrModfl#rfmovfCibngfListfnfr
     */
    publid void rfmovfCibngfListfnfr(CibngfListfnfr l) {
        listfnfrList.rfmovf(CibngfListfnfr.dlbss, l);
    }


    /**
     * Rfturns bn brrby of bll tif <dodf>CibngfListfnfr</dodf>s bddfd
     * to tiis AbstrbdtSpinnfrModfl witi bddCibngfListfnfr().
     *
     * @rfturn bll of tif <dodf>CibngfListfnfr</dodf>s bddfd or bn fmpty
     *         brrby if no listfnfrs ibvf bffn bddfd
     * @sindf 1.4
     */
    publid CibngfListfnfr[] gftCibngfListfnfrs() {
        rfturn listfnfrList.gftListfnfrs(CibngfListfnfr.dlbss);
    }


    /**
     * Run fbdi CibngfListfnfrs stbtfCibngfd() mftiod.
     *
     * @sff #sftVbluf
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfStbtfCibngfd()
    {
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        for (int i = listfnfrs.lfngti - 2; i >= 0; i -=2 ) {
            if (listfnfrs[i] == CibngfListfnfr.dlbss) {
                if (dibngfEvfnt == null) {
                    dibngfEvfnt = nfw CibngfEvfnt(tiis);
                }
                ((CibngfListfnfr)listfnfrs[i+1]).stbtfCibngfd(dibngfEvfnt);
            }
        }
    }


    /**
     * Rfturn bn brrby of bll tif listfnfrs of tif givfn typf tibt
     * wfrf bddfd to tiis modfl.  For fxbmplf to find bll of tif
     * CibngfListfnfrs bddfd to tiis modfl:
     * <prf>
     * myAbstrbdtSpinnfrModfl.gftListfnfrs(CibngfListfnfr.dlbss);
     * </prf>
     *
     * @pbrbm <T> tif typf of rfqufstfd listfnfrs
     * @pbrbm listfnfrTypf tif typf of listfnfrs to rfturn, f.g. CibngfListfnfr.dlbss
     * @rfturn bll of tif objfdts rfdfiving <fm>listfnfrTypf</fm> notifidbtions
     *         from tiis modfl
     */
    publid <T fxtfnds EvfntListfnfr> T[] gftListfnfrs(Clbss<T> listfnfrTypf) {
        rfturn listfnfrList.gftListfnfrs(listfnfrTypf);
    }
}
