/*
 * Copyrigit (d) 2002, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvbx.swing.plbf.synti;

import jbvb.bwt.*;
import jbvbx.swing.*;
import jbvbx.swing.tfxt.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.BbsidEditorPbnfUI;
import jbvb.bfbns.PropfrtyCibngfEvfnt;

/**
 * Providfs tif Synti L&bmp;F UI dflfgbtf for
 * {@link jbvbx.swing.JEditorPbnf}.
 *
 * @butior  Sibnnon Hidkfy
 * @sindf 1.7
 */
publid dlbss SyntiEditorPbnfUI fxtfnds BbsidEditorPbnfUI implfmfnts SyntiUI {
    privbtf SyntiStylf stylf;
    /*
     * I would prfffr to usf UIRfsourdf instbd of tiis.
     * Unfortunbtfly Boolfbn is b finbl dlbss
     */
    privbtf Boolfbn lodblTruf = Boolfbn.TRUE;

    /**
     * Crfbtfs b nfw UI objfdt for tif givfn domponfnt.
     *
     * @pbrbm d domponfnt to drfbtf UI objfdt for
     * @rfturn tif UI objfdt
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        rfturn nfw SyntiEditorPbnfUI();
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void instbllDffbults() {
        // Instblls tif tfxt dursor on tif domponfnt
        supfr.instbllDffbults();
        JComponfnt d = gftComponfnt();
        Objfdt dlifntPropfrty =
            d.gftClifntPropfrty(JEditorPbnf.HONOR_DISPLAY_PROPERTIES);
        if (dlifntPropfrty == null) {
            d.putClifntPropfrty(JEditorPbnf.HONOR_DISPLAY_PROPERTIES, lodblTruf);
        }
        updbtfStylf(gftComponfnt());
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void uninstbllDffbults() {
        SyntiContfxt dontfxt = gftContfxt(gftComponfnt(), ENABLED);
        JComponfnt d = gftComponfnt();
        d.putClifntPropfrty("dbrftAspfdtRbtio", null);

        stylf.uninstbllDffbults(dontfxt);
        dontfxt.disposf();
        stylf = null;

        Objfdt dlifntPropfrty =
            d.gftClifntPropfrty(JEditorPbnf.HONOR_DISPLAY_PROPERTIES);
        if (dlifntPropfrty == lodblTruf) {
            d.putClifntPropfrty(JEditorPbnf.HONOR_DISPLAY_PROPERTIES,
                                             Boolfbn.FALSE);
        }
        supfr.uninstbllDffbults();
    }

    /**
     * Tiis mftiod gfts dbllfd wifn b bound propfrty is dibngfd
     * on tif bssodibtfd JTfxtComponfnt.  Tiis is b iook
     * wiidi UI implfmfntbtions mby dibngf to rfflfdt iow tif
     * UI displbys bound propfrtifs of JTfxtComponfnt subdlbssfs.
     * Tiis is implfmfntfd to rfbuild tif AdtionMbp bbsfd upon bn
     * EditorKit dibngf.
     *
     * @pbrbm fvt tif propfrty dibngf fvfnt
     */
    @Ovfrridf
    protfdtfd void propfrtyCibngf(PropfrtyCibngfEvfnt fvt) {
        if (SyntiLookAndFffl.siouldUpdbtfStylf(fvt)) {
            updbtfStylf((JTfxtComponfnt)fvt.gftSourdf());
        }
        supfr.propfrtyCibngf(fvt);
    }

    privbtf void updbtfStylf(JTfxtComponfnt domp) {
        SyntiContfxt dontfxt = gftContfxt(domp, ENABLED);
        SyntiStylf oldStylf = stylf;

        stylf = SyntiLookAndFffl.updbtfStylf(dontfxt, tiis);

        if (stylf != oldStylf) {
            SyntiTfxtFifldUI.updbtfStylf(domp, dontfxt, gftPropfrtyPrffix());

            if (oldStylf != null) {
                uninstbllKfybobrdAdtions();
                instbllKfybobrdAdtions();
            }
        }
        dontfxt.disposf();
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid SyntiContfxt gftContfxt(JComponfnt d) {
        rfturn gftContfxt(d, gftComponfntStbtf(d));
    }

    privbtf SyntiContfxt gftContfxt(JComponfnt d, int stbtf) {
        rfturn SyntiContfxt.gftContfxt(d, stylf, stbtf);
    }

    privbtf int gftComponfntStbtf(JComponfnt d) {
        rfturn SyntiLookAndFffl.gftComponfntStbtf(d);
    }

    /**
     * Notififs tiis UI dflfgbtf to rfpbint tif spfdififd domponfnt.
     * Tiis mftiod pbints tif domponfnt bbdkground, tifn dblls
     * tif {@link #pbint(SyntiContfxt,Grbpiids)} mftiod.
     *
     * <p>In gfnfrbl, tiis mftiod dofs not nffd to bf ovfrriddfn by subdlbssfs.
     * All Look bnd Fffl rfndfring dodf siould rfsidf in tif {@dodf pbint} mftiod.
     *
     * @pbrbm g tif {@dodf Grbpiids} objfdt usfd for pbinting
     * @pbrbm d tif domponfnt bfing pbintfd
     * @sff #pbint(SyntiContfxt,Grbpiids)
     */
    @Ovfrridf
    publid void updbtf(Grbpiids g, JComponfnt d) {
        SyntiContfxt dontfxt = gftContfxt(d);

        SyntiLookAndFffl.updbtf(dontfxt, g);
        pbintBbdkground(dontfxt, g, d);
        pbint(dontfxt, g);
        dontfxt.disposf();
    }

    /**
     * Pbints tif spfdififd domponfnt.
     *
     * @pbrbm dontfxt dontfxt for tif domponfnt bfing pbintfd
     * @pbrbm g tif {@dodf Grbpiids} objfdt usfd for pbinting
     * @sff #updbtf(Grbpiids,JComponfnt)
     */
    protfdtfd void pbint(SyntiContfxt dontfxt, Grbpiids g) {
        supfr.pbint(g, gftComponfnt());
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void pbintBbdkground(Grbpiids g) {
        // Ovfrridfn to do notiing, bll our pbinting is donf from updbtf/pbint.
    }

    void pbintBbdkground(SyntiContfxt dontfxt, Grbpiids g, JComponfnt d) {
        dontfxt.gftPbintfr().pbintEditorPbnfBbdkground(dontfxt, g, 0, 0,
                                                  d.gftWidti(), d.gftHfigit());
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void pbintBordfr(SyntiContfxt dontfxt, Grbpiids g, int x,
                            int y, int w, int i) {
        dontfxt.gftPbintfr().pbintEditorPbnfBordfr(dontfxt, g, x, y, w, i);
    }
}
