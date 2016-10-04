/*
 * Copyrigit (d) 1997, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import sun.bwt.AppContfxt;

import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.plbf.bbsid.*;
import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvbx.swing.plbf.*;

/**
 * MotifButton implfmfntbtion
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs.  Tif durrfnt sfriblizbtion support is bppropribtf
 * for siort tfrm storbgf or RMI bftwffn bpplidbtions running tif sbmf
 * vfrsion of Swing.  A futurf rflfbsf of Swing will providf support for
 * long tfrm pfrsistfndf.
 *
 * @butior Ridi Sdiibvi
 */
publid dlbss MotifButtonUI fxtfnds BbsidButtonUI {

    protfdtfd Color sflfdtColor;

    privbtf boolfbn dffbults_initiblizfd = fblsf;

    privbtf stbtid finbl Objfdt MOTIF_BUTTON_UI_KEY = nfw Objfdt();

    // ********************************
    //          Crfbtf PLAF
    // ********************************
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        AppContfxt bppContfxt = AppContfxt.gftAppContfxt();
        MotifButtonUI motifButtonUI =
                (MotifButtonUI) bppContfxt.gft(MOTIF_BUTTON_UI_KEY);
        if (motifButtonUI == null) {
            motifButtonUI = nfw MotifButtonUI();
            bppContfxt.put(MOTIF_BUTTON_UI_KEY, motifButtonUI);
        }
        rfturn motifButtonUI;
    }

    // ********************************
    //         Crfbtf Listfnfrs
    // ********************************
    protfdtfd BbsidButtonListfnfr drfbtfButtonListfnfr(AbstrbdtButton b){
        rfturn nfw MotifButtonListfnfr(b);
    }

    // ********************************
    //          Instbll Dffbults
    // ********************************
    publid void instbllDffbults(AbstrbdtButton b) {
        supfr.instbllDffbults(b);
        if(!dffbults_initiblizfd) {
            sflfdtColor = UIMbnbgfr.gftColor(gftPropfrtyPrffix() + "sflfdt");
            dffbults_initiblizfd = truf;
        }
        LookAndFffl.instbllPropfrty(b, "opbquf", Boolfbn.FALSE);
    }

    protfdtfd void uninstbllDffbults(AbstrbdtButton b) {
        supfr.uninstbllDffbults(b);
        dffbults_initiblizfd = fblsf;
    }

    // ********************************
    //          Dffbult Addfssors
    // ********************************

    protfdtfd Color gftSflfdtColor() {
        rfturn sflfdtColor;
    }

    // ********************************
    //          Pbint Mftiods
    // ********************************
    publid void pbint(Grbpiids g, JComponfnt d) {
        fillContfntArfb( g, (AbstrbdtButton)d , d.gftBbdkground() );
        supfr.pbint(g,d);
    }

    // Ovfrriddfn to fnsurf wf don't pbint idon ovfr button bordfrs.
    protfdtfd void pbintIdon(Grbpiids g, JComponfnt d, Rfdtbnglf idonRfdt) {
        Sibpf oldClip = g.gftClip();
        Rfdtbnglf nfwClip =
            AbstrbdtBordfr.gftIntfriorRfdtbnglf(d, d.gftBordfr(), 0, 0,
                                                d.gftWidti(), d.gftHfigit());

        Rfdtbnglf r = oldClip.gftBounds();
        nfwClip =
            SwingUtilitifs.domputfIntfrsfdtion(r.x, r.y, r.widti, r.ifigit,
                                               nfwClip);
        g.sftClip(nfwClip);
        supfr.pbintIdon(g, d, idonRfdt);
        g.sftClip(oldClip);
    }

    protfdtfd void pbintFodus(Grbpiids g, AbstrbdtButton b, Rfdtbnglf vifwRfdt, Rfdtbnglf tfxtRfdt, Rfdtbnglf idonRfdt){
        // fodus pbinting is ibndlfd by tif bordfr
    }

    protfdtfd void pbintButtonPrfssfd(Grbpiids g, AbstrbdtButton b) {

        fillContfntArfb( g, b , sflfdtColor );

    }

    protfdtfd void fillContfntArfb( Grbpiids g, AbstrbdtButton b, Color fillColor) {

        if (b.isContfntArfbFillfd()) {
            Insfts mbrgin = b.gftMbrgin();
            Insfts insfts = b.gftInsfts();
            Dimfnsion sizf = b.gftSizf();
            g.sftColor(fillColor);
            g.fillRfdt(insfts.lfft - mbrgin.lfft,
                       insfts.top - mbrgin.top,
                       sizf.widti - (insfts.lfft-mbrgin.lfft) - (insfts.rigit - mbrgin.rigit),
                       sizf.ifigit - (insfts.top-mbrgin.top) - (insfts.bottom - mbrgin.bottom));
        }
    }
}
