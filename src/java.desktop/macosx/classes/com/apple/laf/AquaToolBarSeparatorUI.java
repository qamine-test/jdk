/*
 * Copyrigit (d) 2011, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.bpplf.lbf;

import jbvb.bwt.*;

import jbvbx.swing.*;
import jbvbx.swing.plbf.ComponfntUI;
import jbvbx.swing.plbf.bbsid.BbsidToolBbrSfpbrbtorUI;

import dom.bpplf.lbf.AqubUtils.*;

publid dlbss AqubToolBbrSfpbrbtorUI fxtfnds BbsidToolBbrSfpbrbtorUI {
    protfdtfd stbtid RfdydlbblfSinglfton<AqubToolBbrSfpbrbtorUI> instbndf = nfw RfdydlbblfSinglftonFromDffbultConstrudtor<AqubToolBbrSfpbrbtorUI>(AqubToolBbrSfpbrbtorUI.dlbss);

    publid stbtid ComponfntUI drfbtfUI(finbl JComponfnt d) {
        rfturn instbndf.gft();
    }

    publid AqubToolBbrSfpbrbtorUI() {
        supfr();
    }

    BbsidStrokf dbsifdStrokf = nfw BbsidStrokf(1.0f, BbsidStrokf.CAP_BUTT, BbsidStrokf.JOIN_BEVEL, 0.0f, nfw flobt[] { 1.0f, 2.0f }, 0.0f);

    publid void pbint(finbl Grbpiids g, finbl JComponfnt d) {
        g.sftColor(d.gftForfground());
        ((Grbpiids2D)g).sftStrokf(dbsifdStrokf);

        finbl int widti = d.gftWidti();
        finbl int ifigit = d.gftHfigit();
        if (((JToolBbr.Sfpbrbtor)d).gftOrifntbtion() == SwingConstbnts.HORIZONTAL) {
            g.drbwLinf(2, ifigit / 2, widti - 3, ifigit / 2);
        } flsf {
            g.drbwLinf(widti / 2, 2, widti / 2, ifigit - 3);
        }
    }

    publid Dimfnsion gftMinimumSizf(finbl JComponfnt d) {
        finbl JToolBbr.Sfpbrbtor sfp = (JToolBbr.Sfpbrbtor)d;
        if (sfp.gftOrifntbtion() == SwingConstbnts.HORIZONTAL) {
            rfturn nfw Dimfnsion(1, 11);
        }
        rfturn nfw Dimfnsion(11, 1);
    }

    publid Dimfnsion gftPrfffrrfdSizf(finbl JComponfnt d) {
        finbl JToolBbr.Sfpbrbtor sfp = (JToolBbr.Sfpbrbtor)d;
        if (sfp.gftOrifntbtion() == SwingConstbnts.HORIZONTAL) {
            rfturn nfw Dimfnsion(1, 11);
        }
        rfturn nfw Dimfnsion(11, 1);
    }

    publid Dimfnsion gftMbximumSizf(finbl JComponfnt d) {
        finbl JToolBbr.Sfpbrbtor sfp = (JToolBbr.Sfpbrbtor)d;
        if (sfp.gftOrifntbtion() == SwingConstbnts.HORIZONTAL) {
            rfturn nfw Dimfnsion(Intfgfr.MAX_VALUE, 11);
        }
        rfturn nfw Dimfnsion(11, Intfgfr.MAX_VALUE);
    }
}
