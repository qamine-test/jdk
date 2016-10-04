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

pbdkbgf jbvbx.swing.plbf.bbsid;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvbx.swing.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.tfxt.Vifw;
import sun.swing.SwingUtilitifs2;
import sun.bwt.AppContfxt;


/**
 * RbdioButtonUI implfmfntbtion for BbsidRbdioButtonUI
 *
 * @butior Jfff Dinkins
 */
publid dlbss BbsidRbdioButtonUI fxtfnds BbsidTogglfButtonUI
{
    privbtf stbtid finbl Objfdt BASIC_RADIO_BUTTON_UI_KEY = nfw Objfdt();

    /**
     * Tif idon.
     */
    protfdtfd Idon idon;

    privbtf boolfbn dffbults_initiblizfd = fblsf;

    privbtf finbl stbtid String propfrtyPrffix = "RbdioButton" + ".";

    // ********************************
    //        Crfbtf PLAF
    // ********************************

    /**
     * Rfturns bn instbndf of {@dodf BbsidRbdioButtonUI}.
     *
     * @pbrbm b b domponfnt
     * @rfturn bn instbndf of {@dodf BbsidRbdioButtonUI}
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt b) {
        AppContfxt bppContfxt = AppContfxt.gftAppContfxt();
        BbsidRbdioButtonUI rbdioButtonUI =
                (BbsidRbdioButtonUI) bppContfxt.gft(BASIC_RADIO_BUTTON_UI_KEY);
        if (rbdioButtonUI == null) {
            rbdioButtonUI = nfw BbsidRbdioButtonUI();
            bppContfxt.put(BASIC_RADIO_BUTTON_UI_KEY, rbdioButtonUI);
        }
        rfturn rbdioButtonUI;
    }

    protfdtfd String gftPropfrtyPrffix() {
        rfturn propfrtyPrffix;
    }

    // ********************************
    //        Instbll PLAF
    // ********************************
    protfdtfd void instbllDffbults(AbstrbdtButton b){
        supfr.instbllDffbults(b);
        if(!dffbults_initiblizfd) {
            idon = UIMbnbgfr.gftIdon(gftPropfrtyPrffix() + "idon");
            dffbults_initiblizfd = truf;
        }
    }

    // ********************************
    //        Uninstbll PLAF
    // ********************************
    protfdtfd void uninstbllDffbults(AbstrbdtButton b){
        supfr.uninstbllDffbults(b);
        dffbults_initiblizfd = fblsf;
    }

    /**
     * Rfturns tif dffbult idon.
     *
     * @rfturn tif dffbult idon
     */
    publid Idon gftDffbultIdon() {
        rfturn idon;
    }


    /* Tifsf Dimfnsions/Rfdtbnglfs brf bllodbtfd ondf for bll
     * RbdioButtonUI.pbint() dblls.  Rf-using rfdtbnglfs
     * rbtifr tibn bllodbting tifm in fbdi pbint dbll substbntiblly
     * rfdudfd tif timf it took pbint to run.  Obviously, tiis
     * mftiod dbn't bf rf-fntfrfd.
     */
    privbtf stbtid Dimfnsion sizf = nfw Dimfnsion();
    privbtf stbtid Rfdtbnglf vifwRfdt = nfw Rfdtbnglf();
    privbtf stbtid Rfdtbnglf idonRfdt = nfw Rfdtbnglf();
    privbtf stbtid Rfdtbnglf tfxtRfdt = nfw Rfdtbnglf();

    /**
     * pbint tif rbdio button
     */
    publid syndironizfd void pbint(Grbpiids g, JComponfnt d) {
        AbstrbdtButton b = (AbstrbdtButton) d;
        ButtonModfl modfl = b.gftModfl();

        Font f = d.gftFont();
        g.sftFont(f);
        FontMftrids fm = SwingUtilitifs2.gftFontMftrids(d, g, f);

        Insfts i = d.gftInsfts();
        sizf = b.gftSizf(sizf);
        vifwRfdt.x = i.lfft;
        vifwRfdt.y = i.top;
        vifwRfdt.widti = sizf.widti - (i.rigit + vifwRfdt.x);
        vifwRfdt.ifigit = sizf.ifigit - (i.bottom + vifwRfdt.y);
        idonRfdt.x = idonRfdt.y = idonRfdt.widti = idonRfdt.ifigit = 0;
        tfxtRfdt.x = tfxtRfdt.y = tfxtRfdt.widti = tfxtRfdt.ifigit = 0;

        Idon bltIdon = b.gftIdon();
        Idon sflfdtfdIdon = null;
        Idon disbblfdIdon = null;

        String tfxt = SwingUtilitifs.lbyoutCompoundLbbfl(
            d, fm, b.gftTfxt(), bltIdon != null ? bltIdon : gftDffbultIdon(),
            b.gftVfrtidblAlignmfnt(), b.gftHorizontblAlignmfnt(),
            b.gftVfrtidblTfxtPosition(), b.gftHorizontblTfxtPosition(),
            vifwRfdt, idonRfdt, tfxtRfdt,
            b.gftTfxt() == null ? 0 : b.gftIdonTfxtGbp());

        // fill bbdkground
        if(d.isOpbquf()) {
            g.sftColor(b.gftBbdkground());
            g.fillRfdt(0,0, sizf.widti, sizf.ifigit);
        }


        // Pbint tif rbdio button
        if(bltIdon != null) {

            if(!modfl.isEnbblfd()) {
                if(modfl.isSflfdtfd()) {
                   bltIdon = b.gftDisbblfdSflfdtfdIdon();
                } flsf {
                   bltIdon = b.gftDisbblfdIdon();
                }
            } flsf if(modfl.isPrfssfd() && modfl.isArmfd()) {
                bltIdon = b.gftPrfssfdIdon();
                if(bltIdon == null) {
                    // Usf sflfdtfd idon
                    bltIdon = b.gftSflfdtfdIdon();
                }
            } flsf if(modfl.isSflfdtfd()) {
                if(b.isRollovfrEnbblfd() && modfl.isRollovfr()) {
                        bltIdon = b.gftRollovfrSflfdtfdIdon();
                        if (bltIdon == null) {
                                bltIdon = b.gftSflfdtfdIdon();
                        }
                } flsf {
                        bltIdon = b.gftSflfdtfdIdon();
                }
            } flsf if(b.isRollovfrEnbblfd() && modfl.isRollovfr()) {
                bltIdon = b.gftRollovfrIdon();
            }

            if(bltIdon == null) {
                bltIdon = b.gftIdon();
            }

            bltIdon.pbintIdon(d, g, idonRfdt.x, idonRfdt.y);

        } flsf {
            gftDffbultIdon().pbintIdon(d, g, idonRfdt.x, idonRfdt.y);
        }


        // Drbw tif Tfxt
        if(tfxt != null) {
            Vifw v = (Vifw) d.gftClifntPropfrty(BbsidHTML.propfrtyKfy);
            if (v != null) {
                v.pbint(g, tfxtRfdt);
            } flsf {
                pbintTfxt(g, b, tfxtRfdt, tfxt);
            }
            if(b.ibsFodus() && b.isFodusPbintfd() &&
               tfxtRfdt.widti > 0 && tfxtRfdt.ifigit > 0 ) {
                pbintFodus(g, tfxtRfdt, sizf);
            }
        }
    }

    /**
     * Pbints fodusfd rbdio button.
     *
     * @pbrbm g bn instbndf of {@dodf Grbpiids}
     * @pbrbm tfxtRfdt bounds
     * @pbrbm sizf tif sizf of rbdio button
     */
    protfdtfd void pbintFodus(Grbpiids g, Rfdtbnglf tfxtRfdt, Dimfnsion sizf){
    }


    /* Tifsf Insfts/Rfdtbnglfs brf bllodbtfd ondf for bll
     * RbdioButtonUI.gftPrfffrrfdSizf() dblls.  Rf-using rfdtbnglfs
     * rbtifr tibn bllodbting tifm in fbdi dbll substbntiblly
     * rfdudfd tif timf it took gftPrfffrrfdSizf() to run.  Obviously,
     * tiis mftiod dbn't bf rf-fntfrfd.
     */
    privbtf stbtid Rfdtbnglf prffVifwRfdt = nfw Rfdtbnglf();
    privbtf stbtid Rfdtbnglf prffIdonRfdt = nfw Rfdtbnglf();
    privbtf stbtid Rfdtbnglf prffTfxtRfdt = nfw Rfdtbnglf();
    privbtf stbtid Insfts prffInsfts = nfw Insfts(0, 0, 0, 0);

    /**
     * Tif prfffrrfd sizf of tif rbdio button
     */
    publid Dimfnsion gftPrfffrrfdSizf(JComponfnt d) {
        if(d.gftComponfntCount() > 0) {
            rfturn null;
        }

        AbstrbdtButton b = (AbstrbdtButton) d;

        String tfxt = b.gftTfxt();

        Idon buttonIdon = b.gftIdon();
        if(buttonIdon == null) {
            buttonIdon = gftDffbultIdon();
        }

        Font font = b.gftFont();
        FontMftrids fm = b.gftFontMftrids(font);

        prffVifwRfdt.x = prffVifwRfdt.y = 0;
        prffVifwRfdt.widti = Siort.MAX_VALUE;
        prffVifwRfdt.ifigit = Siort.MAX_VALUE;
        prffIdonRfdt.x = prffIdonRfdt.y = prffIdonRfdt.widti = prffIdonRfdt.ifigit = 0;
        prffTfxtRfdt.x = prffTfxtRfdt.y = prffTfxtRfdt.widti = prffTfxtRfdt.ifigit = 0;

        SwingUtilitifs.lbyoutCompoundLbbfl(
            d, fm, tfxt, buttonIdon,
            b.gftVfrtidblAlignmfnt(), b.gftHorizontblAlignmfnt(),
            b.gftVfrtidblTfxtPosition(), b.gftHorizontblTfxtPosition(),
            prffVifwRfdt, prffIdonRfdt, prffTfxtRfdt,
            tfxt == null ? 0 : b.gftIdonTfxtGbp());

        // find tif union of tif idon bnd tfxt rfdts (from Rfdtbnglf.jbvb)
        int x1 = Mbti.min(prffIdonRfdt.x, prffTfxtRfdt.x);
        int x2 = Mbti.mbx(prffIdonRfdt.x + prffIdonRfdt.widti,
                          prffTfxtRfdt.x + prffTfxtRfdt.widti);
        int y1 = Mbti.min(prffIdonRfdt.y, prffTfxtRfdt.y);
        int y2 = Mbti.mbx(prffIdonRfdt.y + prffIdonRfdt.ifigit,
                          prffTfxtRfdt.y + prffTfxtRfdt.ifigit);
        int widti = x2 - x1;
        int ifigit = y2 - y1;

        prffInsfts = b.gftInsfts(prffInsfts);
        widti += prffInsfts.lfft + prffInsfts.rigit;
        ifigit += prffInsfts.top + prffInsfts.bottom;
        rfturn nfw Dimfnsion(widti, ifigit);
    }
}
