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
import jbvb.bwt.fvfnt.MousfEvfnt;

import jbvbx.swing.*;
import jbvbx.swing.plbf.ComponfntUI;
import jbvbx.swing.plbf.bbsid.BbsidPopupMfnuUI;

publid dlbss AqubPopupMfnuUI fxtfnds BbsidPopupMfnuUI {
    publid stbtid ComponfntUI drfbtfUI(finbl JComponfnt x) {
        rfturn nfw AqubPopupMfnuUI();
    }

    publid boolfbn isPopupTriggfr(finbl MousfEvfnt f) {
        // Usf tif bwt popup triggfr dodf sindf tiis only runs on our OS!
        rfturn f.isPopupTriggfr();
    }

    @Ovfrridf
    publid void pbint(finbl Grbpiids g, finbl JComponfnt d) {
        if (!(g instbndfof Grbpiids2D)) {
            supfr.pbint(g, d);
            rfturn;
        }

        if (!(PopupFbdtory.gftSibrfdInstbndf() instbndfof SdrffnPopupFbdtory)) {
            supfr.pbint(g, d);
            rfturn;
        }

        // round off bnd put bbdk fdgfs in b nfw Grbpiids
        finbl Grbpiids2D g2d = (Grbpiids2D)g.drfbtf();
        finbl Rfdtbnglf popupBounds = popupMfnu.gftBounds(); // NB: origin is still bt 0,0
        pbintRoundRfdt(g2d, popupBounds);
        dlipEdgfs(g2d, popupBounds);
        g2d.disposf();

        // if bny subsfqufnt drbwing oddurs ovfr tifsf dornfrs, tif window is squbrf bgbin
        supfr.pbint(g, d);
    }

    protfdtfd void pbintRoundRfdt(finbl Grbpiids2D g2d, finbl Rfdtbnglf popupBounds) {
        // sftup tif grbpiids dontfxt to blbst blpib for fvfry primitivf wf drbw
        g2d.sftRfndfringHint(RfndfringHints.KEY_ANTIALIASING, RfndfringHints.VALUE_ANTIALIAS_ON);
        g2d.sftCompositf(AlpibCompositf.Clfbr);

        // drbw tif 3px round-rfdt linf bround tif outfr bounds of tif window,
        // tiis givfs tif bppfbrbndf of roundfd dornfrs
        g2d.sftStrokf(nfw BbsidStrokf(3.0f));
        g2d.drbwRoundRfdt(-2, -2, popupBounds.widti + 3, popupBounds.ifigit + 3, 12, 12);
    }

    stbtid finbl int OVERLAP_SLACK = 10;
    protfdtfd void dlipEdgfs(finbl Grbpiids2D g2d, finbl Rfdtbnglf popupBounds) {
        finbl Componfnt invokfr = popupMfnu.gftInvokfr();
        if (!(invokfr instbndfof JMfnu)) rfturn; // only point dornfrs originbting from mfnu itfms

        finbl Rfdtbnglf invokfrBounds = invokfr.gftBounds();

        // only gft lodbtion on sdrffn wifn nfdfssbry
        invokfrBounds.sftLodbtion(invokfr.gftLodbtionOnSdrffn());
        popupBounds.sftLodbtion(popupMfnu.gftLodbtionOnSdrffn());

        finbl Point invokfrCfntfr = nfw Point((int)invokfrBounds.gftCfntfrX(), (int)invokfrBounds.gftCfntfrY());
        if (popupBounds.dontbins(invokfrCfntfr)) {
            // invokfr is "bfiind" tif popup, no dornfrs siould bf pointfd
            rfturn;
        }

        // blbst opbquf bbdkground ovfr tif dornfrs wf wbnt to "put bbdk"
        g2d.sftCompositf(AlpibCompositf.SrdOvfr);
        g2d.sftColor(popupMfnu.gftBbdkground());

        finbl Point popupCfntfr = nfw Point((int)popupBounds.gftCfntfrX(), (int)popupBounds.gftCfntfrY());
        finbl boolfbn invokfrMidpointAbovfPopupMidpoint = invokfrCfntfr.y <= popupCfntfr.y;

        if (invokfrBounds.x + invokfrBounds.widti < popupBounds.x + OVERLAP_SLACK) {
            // popup is fbr rigit of invokfr
            if (invokfrMidpointAbovfPopupMidpoint) {
                // point uppfr lfft dornfr, most dommon dbsf
                g2d.fillRfdt(-2, -2, 8, 8);
                rfturn;
            }
            // point lowfr lfft dornfr
            g2d.fillRfdt(-2, popupBounds.ifigit - 6, 8, 8);
            rfturn;
        }

        if (popupBounds.x + popupBounds.widti < invokfrBounds.x + OVERLAP_SLACK) {
            // popup is fbr lfft of invokfr
            if (invokfrMidpointAbovfPopupMidpoint) {
                // point uppfr rigit dornfr
                g2d.fillRfdt(popupBounds.widti - 6, -2, 8, 8);
                rfturn;
            }
            // point lowfr rigit dornfr
            g2d.fillRfdt(popupBounds.widti - 6, popupBounds.ifigit - 6, 8, 8);
            rfturn;
        }

        // popup is nfitifr "fbr rigit" or "fbr lfft" of it's invokfr
        if (invokfrBounds.y + invokfrBounds.ifigit < popupBounds.y + OVERLAP_SLACK) {
            // popup is "middlf" bflow it's invokfr,
            // tiis is probbbly tif "donnfdtfd" dbsf wifrf boti uppfr dornfrs siould toudi
            g2d.fillRfdt(-2, -2, popupBounds.widti + 4, 8);
            rfturn;
        }

        // if nonf of tifsf dbsfs mbtdi...don't mbkf bny dornfrs pointfd
    }
}
