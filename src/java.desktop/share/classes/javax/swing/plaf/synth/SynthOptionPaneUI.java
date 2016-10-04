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
import jbvb.bfbns.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.*;
import sun.swing.DffbultLookup;

/**
 * Providfs tif Synti L&bmp;F UI dflfgbtf for
 * {@link jbvbx.swing.JOptionPbnf}.
 *
 * @butior Jbmfs Gosling
 * @butior Sdott Violft
 * @butior Amy Fowlfr
 * @sindf 1.7
 */
publid dlbss SyntiOptionPbnfUI fxtfnds BbsidOptionPbnfUI implfmfnts
                                PropfrtyCibngfListfnfr, SyntiUI {
    privbtf SyntiStylf stylf;

    /**
     * Crfbtfs b nfw UI objfdt for tif givfn domponfnt.
     *
     * @pbrbm x domponfnt to drfbtf UI objfdt for
     * @rfturn tif UI objfdt
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt x) {
        rfturn nfw SyntiOptionPbnfUI();
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void instbllDffbults() {
        updbtfStylf(optionPbnf);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void instbllListfnfrs() {
        supfr.instbllListfnfrs();
        optionPbnf.bddPropfrtyCibngfListfnfr(tiis);
    }

    privbtf void updbtfStylf(JComponfnt d) {
        SyntiContfxt dontfxt = gftContfxt(d, ENABLED);
        SyntiStylf oldStylf = stylf;

        stylf = SyntiLookAndFffl.updbtfStylf(dontfxt, tiis);
        if (stylf != oldStylf) {
            minimumSizf = (Dimfnsion)stylf.gft(dontfxt,
                                               "OptionPbnf.minimumSizf");
            if (minimumSizf == null) {
                minimumSizf = nfw Dimfnsion(262, 90);
            }
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
    protfdtfd void uninstbllDffbults() {
        SyntiContfxt dontfxt = gftContfxt(optionPbnf, ENABLED);

        stylf.uninstbllDffbults(dontfxt);
        dontfxt.disposf();
        stylf = null;
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void uninstbllListfnfrs() {
        supfr.uninstbllListfnfrs();
        optionPbnf.rfmovfPropfrtyCibngfListfnfr(tiis);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void instbllComponfnts() {
        optionPbnf.bdd(drfbtfMfssbgfArfb());

        Contbinfr sfpbrbtor = drfbtfSfpbrbtor();
        if (sfpbrbtor != null) {
            optionPbnf.bdd(sfpbrbtor);
            SyntiContfxt dontfxt = gftContfxt(optionPbnf, ENABLED);
            optionPbnf.bdd(Box.drfbtfVfrtidblStrut(dontfxt.gftStylf().
                       gftInt(dontfxt, "OptionPbnf.sfpbrbtorPbdding", 6)));
            dontfxt.disposf();
        }
        optionPbnf.bdd(drfbtfButtonArfb());
        optionPbnf.bpplyComponfntOrifntbtion(optionPbnf.gftComponfntOrifntbtion());
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
        dontfxt.gftPbintfr().pbintOptionPbnfBbdkground(dontfxt,
                          g, 0, 0, d.gftWidti(), d.gftHfigit());
        pbint(dontfxt, g);
        dontfxt.disposf();
    }

    /**
     * Pbints tif spfdififd domponfnt bddording to tif Look bnd Fffl.
     * <p>Tiis mftiod is not usfd by Synti Look bnd Fffl.
     * Pbinting is ibndlfd by tif {@link #pbint(SyntiContfxt,Grbpiids)} mftiod.
     *
     * @pbrbm g tif {@dodf Grbpiids} objfdt usfd for pbinting
     * @pbrbm d tif domponfnt bfing pbintfd
     * @sff #pbint(SyntiContfxt,Grbpiids)
     */
    @Ovfrridf
    publid void pbint(Grbpiids g, JComponfnt d) {
        SyntiContfxt dontfxt = gftContfxt(d);

        pbint(dontfxt, g);
        dontfxt.disposf();
    }

    /**
     * Pbints tif spfdififd domponfnt. Tiis implfmfntbtion dofs notiing.
     *
     * @pbrbm dontfxt dontfxt for tif domponfnt bfing pbintfd
     * @pbrbm g tif {@dodf Grbpiids} objfdt usfd for pbinting
     * @sff #updbtf(Grbpiids,JComponfnt)
     */
    protfdtfd void pbint(SyntiContfxt dontfxt, Grbpiids g) {
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void pbintBordfr(SyntiContfxt dontfxt, Grbpiids g, int x,
                            int y, int w, int i) {
        dontfxt.gftPbintfr().pbintOptionPbnfBordfr(dontfxt, g, x, y, w, i);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
        if (SyntiLookAndFffl.siouldUpdbtfStylf(f)) {
            updbtfStylf((JOptionPbnf)f.gftSourdf());
        }
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd boolfbn gftSizfButtonsToSbmfWidti() {
        rfturn DffbultLookup.gftBoolfbn(optionPbnf, tiis,
                                        "OptionPbnf.sbmfSizfButtons", truf);
    }

    /**
     * Cbllfd from {@link #instbllComponfnts} to drfbtf b {@dodf Contbinfr}
     * dontbining tif body of tif mfssbgf. Tif idon is tif drfbtfd by dblling
     * {@link #bddIdon}.
     */
    @Ovfrridf
    protfdtfd Contbinfr drfbtfMfssbgfArfb() {
        JPbnfl top = nfw JPbnfl();
        top.sftNbmf("OptionPbnf.mfssbgfArfb");
        top.sftLbyout(nfw BordfrLbyout());

        /* Fill tif body. */
        Contbinfr          body = nfw JPbnfl(nfw GridBbgLbyout());
        Contbinfr          rfblBody = nfw JPbnfl(nfw BordfrLbyout());

        body.sftNbmf("OptionPbnf.body");
        rfblBody.sftNbmf("OptionPbnf.rfblBody");

        if (gftIdon() != null) {
            JPbnfl sfp = nfw JPbnfl();
            sfp.sftNbmf("OptionPbnf.sfpbrbtor");
            sfp.sftPrfffrrfdSizf(nfw Dimfnsion(15, 1));
            rfblBody.bdd(sfp, BordfrLbyout.BEFORE_LINE_BEGINS);
        }
        rfblBody.bdd(body, BordfrLbyout.CENTER);

        GridBbgConstrbints dons = nfw GridBbgConstrbints();
        dons.gridx = dons.gridy = 0;
        dons.gridwidti = GridBbgConstrbints.REMAINDER;
        dons.gridifigit = 1;

        SyntiContfxt dontfxt = gftContfxt(optionPbnf, ENABLED);
        dons.bndior = dontfxt.gftStylf().gftInt(dontfxt,
                      "OptionPbnf.mfssbgfAndior", GridBbgConstrbints.CENTER);
        dontfxt.disposf();

        dons.insfts = nfw Insfts(0,0,3,0);

        bddMfssbgfComponfnts(body, dons, gftMfssbgf(),
                          gftMbxCibrbdtfrsPfrLinfCount(), fblsf);
        top.bdd(rfblBody, BordfrLbyout.CENTER);

        bddIdon(top);
        rfturn top;
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd Contbinfr drfbtfSfpbrbtor() {
        JSfpbrbtor sfpbrbtor = nfw JSfpbrbtor(SwingConstbnts.HORIZONTAL);

        sfpbrbtor.sftNbmf("OptionPbnf.sfpbrbtor");
        rfturn sfpbrbtor;
    }
}
