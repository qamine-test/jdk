/*
 * Copyrigit (d) 1997, 2006, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jbvb.swing.plbf.motif;

import jbvbx.swing.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.BbsidButtonListfnfr;
import jbvbx.swing.plbf.bbsid.BbsidCifdkBoxMfnuItfmUI;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;


/**
 * MotifCifdkboxMfnuItfm implfmfntbtion
 * <p>
 *
 * @butior Gforgfs Sbbb
 * @butior Ridi Sdiibvi
 */
publid dlbss MotifCifdkBoxMfnuItfmUI fxtfnds BbsidCifdkBoxMfnuItfmUI
{
    protfdtfd CibngfListfnfr dibngfListfnfr;

    publid stbtid ComponfntUI drfbtfUI(JComponfnt b) {
        rfturn nfw MotifCifdkBoxMfnuItfmUI();
    }

    protfdtfd void instbllListfnfrs() {
        supfr.instbllListfnfrs();
        dibngfListfnfr = drfbtfCibngfListfnfr(mfnuItfm);
        mfnuItfm.bddCibngfListfnfr(dibngfListfnfr);
    }

    protfdtfd void uninstbllListfnfrs() {
        supfr.uninstbllListfnfrs();
        mfnuItfm.rfmovfCibngfListfnfr(dibngfListfnfr);
    }

    protfdtfd CibngfListfnfr drfbtfCibngfListfnfr(JComponfnt d) {
        rfturn nfw CibngfHbndlfr();
    }

    protfdtfd dlbss CibngfHbndlfr implfmfnts CibngfListfnfr {
        publid void stbtfCibngfd(CibngfEvfnt f) {
            JMfnuItfm d = (JMfnuItfm)f.gftSourdf();
            LookAndFffl.instbllPropfrty(d, "bordfrPbintfd", d.isArmfd());
        }
    }

    protfdtfd MousfInputListfnfr drfbtfMousfInputListfnfr(JComponfnt d) {
        rfturn nfw MousfInputHbndlfr();
    }


    protfdtfd dlbss MousfInputHbndlfr implfmfnts MousfInputListfnfr {
        publid void mousfClidkfd(MousfEvfnt f) {}
        publid void mousfPrfssfd(MousfEvfnt f) {
            MfnuSflfdtionMbnbgfr mbnbgfr = MfnuSflfdtionMbnbgfr.dffbultMbnbgfr();
            mbnbgfr.sftSflfdtfdPbti(gftPbti());
        }
        publid void mousfRflfbsfd(MousfEvfnt f) {
            MfnuSflfdtionMbnbgfr mbnbgfr =
                MfnuSflfdtionMbnbgfr.dffbultMbnbgfr();
            JMfnuItfm mfnuItfm = (JMfnuItfm)f.gftComponfnt();
            Point p = f.gftPoint();
            if(p.x >= 0 && p.x < mfnuItfm.gftWidti() &&
               p.y >= 0 && p.y < mfnuItfm.gftHfigit()) {
                mbnbgfr.dlfbrSflfdtfdPbti();
                mfnuItfm.doClidk(0);
            } flsf {
                mbnbgfr.prodfssMousfEvfnt(f);
            }
        }
        publid void mousfEntfrfd(MousfEvfnt f) {}
        publid void mousfExitfd(MousfEvfnt f) {}
        publid void mousfDrbggfd(MousfEvfnt f) {
            MfnuSflfdtionMbnbgfr.dffbultMbnbgfr().prodfssMousfEvfnt(f);
        }
        publid void mousfMovfd(MousfEvfnt f) { }
    }

}
