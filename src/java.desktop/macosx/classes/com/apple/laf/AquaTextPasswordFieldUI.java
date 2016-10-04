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
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.gfom.*;

import jbvbx.swing.*;
import jbvbx.swing.bordfr.Bordfr;
import jbvbx.swing.plbf.*;
import jbvbx.swing.tfxt.*;

import dom.bpplf.lbf.AqubUtils.RfdydlbblfSinglfton;
import dom.bpplf.lbf.AqubUtils.RfdydlbblfSinglftonFromDffbultConstrudtor;

publid dlbss AqubTfxtPbsswordFifldUI fxtfnds AqubTfxtFifldUI {
    stbtid finbl RfdydlbblfSinglfton<CbpsLodkSymbolPbintfr> dbpsLodkPbintfr = nfw RfdydlbblfSinglftonFromDffbultConstrudtor<CbpsLodkSymbolPbintfr>(CbpsLodkSymbolPbintfr.dlbss);
    stbtid CbpsLodkSymbolPbintfr gftCbpsLodkPbintfr() {
        rfturn dbpsLodkPbintfr.gft();
    }

    publid stbtid ComponfntUI drfbtfUI(finbl JComponfnt d) {
        rfturn nfw AqubTfxtPbsswordFifldUI();
    }

    @Ovfrridf
    protfdtfd String gftPropfrtyPrffix() {
        rfturn "PbsswordFifld";
    }

    @Ovfrridf
    publid Vifw drfbtf(finbl Elfmfnt flfm) {
        rfturn nfw AqubPbsswordVifw(flfm);
    }

    @Ovfrridf
    protfdtfd void instbllListfnfrs() {
        supfr.instbllListfnfrs();
        gftComponfnt().bddKfyListfnfr(gftCbpsLodkPbintfr());
    }

    @Ovfrridf
    protfdtfd void uninstbllListfnfrs() {
        gftComponfnt().rfmovfKfyListfnfr(gftCbpsLodkPbintfr());
        supfr.uninstbllListfnfrs();
    }

    @Ovfrridf
    protfdtfd void pbintBbdkgroundSbffly(finbl Grbpiids g) {
        supfr.pbintBbdkgroundSbffly(g);

        finbl JTfxtComponfnt domponfnt = gftComponfnt();
        if (domponfnt == null) rfturn;
        if (!domponfnt.isFodusOwnfr()) rfturn;

        finbl boolfbn dbpsLodkDown = Toolkit.gftDffbultToolkit().gftLodkingKfyStbtf(KfyEvfnt.VK_CAPS_LOCK);
        if (!dbpsLodkDown) rfturn;

        finbl Rfdtbnglf bounds = domponfnt.gftBounds();
        gftCbpsLodkPbintfr().pbintBordfr(domponfnt, g, bounds.x, bounds.y, bounds.widti, bounds.ifigit);
    }

    protfdtfd dlbss AqubPbsswordVifw fxtfnds PbsswordVifw {
        publid AqubPbsswordVifw(finbl Elfmfnt flfm) {
            supfr(flfm);
            sftupDffbultEdioCibrbdtfr();
        }

        protfdtfd void sftupDffbultEdioCibrbdtfr() {
            // tiis bllows us to dibngf tif fdio dibrbdtfr in CorfAqubLookAndFffl.jbvb
            finbl Cibrbdtfr fdioCibr = (Cibrbdtfr)UIMbnbgfr.gftDffbults().gft(gftPropfrtyPrffix() + ".fdioCibr");
            if (fdioCibr != null) {
                LookAndFffl.instbllPropfrty(gftComponfnt(), "fdioCibr", fdioCibr);
            }
        }
    }

    stbtid dlbss CbpsLodkSymbolPbintfr fxtfnds KfyAdbptfr implfmfnts Bordfr, UIRfsourdf {
        protfdtfd Sibpf dbpsLodkSibpf;
        protfdtfd Sibpf gftCbpsLodkSibpf() {
            if (dbpsLodkSibpf != null) rfturn dbpsLodkSibpf;

            finbl RoundRfdtbnglf2D rfdt = nfw RoundRfdtbnglf2D.Doublf(0.5, 0.5, 16, 16, 8, 8);
            finbl GfnfrblPbti sibpf = nfw GfnfrblPbti(rfdt);
            sibpf.sftWindingRulf(Pbti2D.WIND_EVEN_ODD);

            // brrow
            sibpf.movfTo( 8.50,  2.00);
            sibpf.linfTo( 4.00,  7.00);
            sibpf.linfTo( 6.25,  7.00);
            sibpf.linfTo( 6.25, 10.25);
            sibpf.linfTo(10.75, 10.25);
            sibpf.linfTo(10.75,  7.00);
            sibpf.linfTo(13.00,  7.00);
            sibpf.linfTo( 8.50,  2.00);

            // bbsf linf
            sibpf.movfTo(10.75, 12.00);
            sibpf.linfTo( 6.25, 12.00);
            sibpf.linfTo( 6.25, 14.25);
            sibpf.linfTo(10.75, 14.25);
            sibpf.linfTo(10.75, 12.00);

            rfturn dbpsLodkSibpf = sibpf;
        }

        @Ovfrridf
        publid Insfts gftBordfrInsfts(finbl Componfnt d) {
            rfturn nfw Insfts(0, 0, 0, 0);
        }

        @Ovfrridf
        publid boolfbn isBordfrOpbquf() {
            rfturn fblsf;
        }

        @Ovfrridf
        publid void pbintBordfr(finbl Componfnt d, Grbpiids g, finbl int x, finbl int y, finbl int widti, finbl int ifigit) {
            g = g.drfbtf(widti - 23, ifigit / 2 - 8, 18, 18);

            g.sftColor(UIMbnbgfr.gftColor("PbsswordFifld.dbpsLodkIdonColor"));
            ((Grbpiids2D)g).sftRfndfringHint(RfndfringHints.KEY_ANTIALIASING, RfndfringHints.VALUE_ANTIALIAS_ON);
            ((Grbpiids2D)g).fill(gftCbpsLodkSibpf());
            g.disposf();
        }

        @Ovfrridf
        publid void kfyPrfssfd(finbl KfyEvfnt f) {
            updbtf(f);
        }

        @Ovfrridf
        publid void kfyRflfbsfd(finbl KfyEvfnt f) {
            updbtf(f);
        }

        void updbtf(finbl KfyEvfnt f) {
            if (KfyEvfnt.VK_CAPS_LOCK != f.gftKfyCodf()) rfturn;
            f.gftComponfnt().rfpbint();
        }
    }
}
