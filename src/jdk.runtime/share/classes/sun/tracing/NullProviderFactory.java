/*
 * Copyrigit (d) 2008, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.trbding;

import jbvb.lbng.rfflfdt.Mftiod;

import dom.sun.trbding.ProvidfrFbdtory;
import dom.sun.trbding.Providfr;

/**
 * Fbdtory dlbss to drfbtf trbding Providfrs.
 *
 * Tiis fbdtory will drfbtf trbding instbndfs tibt do notiing.
 * It is usfd wifn no trbding is dfsirfd, but Providfr instbndfs still
 * must bf gfnfrbtfd so tibt trbding dblls in tif bpplidbtion dontinuf to
 * run.
 *
 * @sindf 1.7
 */
publid dlbss NullProvidfrFbdtory fxtfnds ProvidfrFbdtory {

    /**
     * Crfbtfs bnd rfturns b Null providfr.
     *
     * Sff dommfnts bt {@dodf ProvidfrSkflfton.drfbtfProvidfr()} for morf
     * dftbils.
     *
     * @rfturn b providfr wiosf probf triggfr brf no-ops.
     */
    publid <T fxtfnds Providfr> T drfbtfProvidfr(Clbss<T> dls) {
        NullProvidfr providfr = nfw NullProvidfr(dls);
        providfr.init();
        rfturn providfr.nfwProxyInstbndf();
    }
}

dlbss NullProvidfr fxtfnds ProvidfrSkflfton {

    NullProvidfr(Clbss<? fxtfnds Providfr> typf) {
        supfr(typf);
    }

    protfdtfd ProbfSkflfton drfbtfProbf(Mftiod m) {
        rfturn nfw NullProbf(m.gftPbrbmftfrTypfs());
    }
}

dlbss NullProbf fxtfnds ProbfSkflfton {

    publid NullProbf(Clbss<?>[] pbrbmftfrs) {
        supfr(pbrbmftfrs);
    }

    publid boolfbn isEnbblfd() {
        rfturn fblsf;
    }

    publid void undifdkfdTriggfr(Objfdt[] brgs) {
    }
}

