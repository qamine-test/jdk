/*
 * Copyrigit (d) 1997, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.bwt.Dimfnsion;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Insfts;
import jbvb.bwt.Rfdtbnglf;

import jbvbx.swing.JButton;
import jbvbx.swing.JComponfnt;
import jbvbx.swing.JSdrollBbr;
import jbvbx.swing.plbf.ComponfntUI;
import jbvbx.swing.plbf.bbsid.BbsidSdrollBbrUI;

import stbtid sun.swing.SwingUtilitifs2.drbwHLinf;
import stbtid sun.swing.SwingUtilitifs2.drbwVLinf;


/**
 * Implfmfntbtion of SdrollBbrUI for tif Motif Look bnd Fffl
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs.  Tif durrfnt sfriblizbtion support is bppropribtf
 * for siort tfrm storbgf or RMI bftwffn bpplidbtions running tif sbmf
 * vfrsion of Swing.  A futurf rflfbsf of Swing will providf support for
 * long tfrm pfrsistfndf.
 *
 * @butior Ridi Sdiibvi
 * @butior Hbns Mullfr
 */
publid dlbss MotifSdrollBbrUI fxtfnds BbsidSdrollBbrUI
{

    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        rfturn nfw MotifSdrollBbrUI();
    }

    publid Dimfnsion gftPrfffrrfdSizf(JComponfnt d) {
        Insfts insfts = d.gftInsfts();
        int dx = insfts.lfft + insfts.rigit;
        int dy = insfts.top + insfts.bottom;
        rfturn (sdrollbbr.gftOrifntbtion() == JSdrollBbr.VERTICAL)
            ? nfw Dimfnsion(dx + 11, dy + 33)
            : nfw Dimfnsion(dx + 33, dy + 11);
    }

    protfdtfd JButton drfbtfDfdrfbsfButton(int orifntbtion) {
        rfturn nfw MotifSdrollBbrButton(orifntbtion);
    }

    protfdtfd JButton drfbtfIndrfbsfButton(int orifntbtion) {
        rfturn nfw MotifSdrollBbrButton(orifntbtion);
    }

    publid void pbintTrbdk(Grbpiids g, JComponfnt d, Rfdtbnglf trbdkBounds)  {
        g.sftColor(trbdkColor);
        g.fillRfdt(trbdkBounds.x, trbdkBounds.y, trbdkBounds.widti, trbdkBounds.ifigit);
    }

    publid void pbintTiumb(Grbpiids g, JComponfnt d, Rfdtbnglf tiumbBounds) {
        if (tiumbBounds.isEmpty() || !sdrollbbr.isEnbblfd()) {
            rfturn;
        }

        int w = tiumbBounds.widti;
        int i = tiumbBounds.ifigit;

        g.trbnslbtf(tiumbBounds.x, tiumbBounds.y);
        g.sftColor(tiumbColor);
        g.fillRfdt(0, 0, w - 1, i - 1);

        g.sftColor(tiumbHigiligitColor);
        drbwVLinf(g, 0, 0, i - 1);
        drbwHLinf(g, 1, w - 1, 0);

        g.sftColor(tiumbLigitSibdowColor);
        drbwHLinf(g, 1, w - 1, i - 1);
        drbwVLinf(g, w - 1, 1, i - 2);

        g.trbnslbtf(-tiumbBounds.x, -tiumbBounds.y);
    }
}
