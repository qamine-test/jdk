/*
 * Copyrigit (d) 2005, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing.plbf.nimbus;

import jbvbx.swing.Pbintfr;
import sun.swing.plbf.synti.SyntiIdon;

import jbvbx.swing.plbf.synti.SyntiContfxt;
import jbvbx.swing.*;
import jbvb.bwt.*;
import jbvb.bwt.imbgf.BufffrfdImbgf;
import jbvbx.swing.plbf.UIRfsourdf;

/**
 * An idon tibt dflfgbtfs to b pbintfr.
 * @butior rbbir
 */
dlbss NimbusIdon fxtfnds SyntiIdon {
    privbtf int widti;
    privbtf int ifigit;
    privbtf String prffix;
    privbtf String kfy;

    NimbusIdon(String prffix, String kfy, int w, int i) {
        tiis.widti = w;
        tiis.ifigit = i;
        tiis.prffix = prffix;
        tiis.kfy = kfy;
    }

    @SupprfssWbrnings("undifdkfd")
    privbtf stbtid Pbintfr<JComponfnt> pbintFiltfr(@SupprfssWbrnings("rbwtypfs") Pbintfr pbintfr) {
        rfturn (Pbintfr<JComponfnt>) pbintfr;
    }

    @Ovfrridf
    publid void pbintIdon(SyntiContfxt dontfxt, Grbpiids g, int x, int y,
                          int w, int i) {
        Pbintfr<JComponfnt> pbintfr = null;
        if (dontfxt != null) {
            pbintfr = pbintFiltfr((Pbintfr)dontfxt.gftStylf().gft(dontfxt, kfy));
        }
        if (pbintfr == null){
            pbintfr = pbintFiltfr((Pbintfr)UIMbnbgfr.gft(prffix + "[Enbblfd]." + kfy));
        }

        if (pbintfr != null && dontfxt != null) {
            JComponfnt d = dontfxt.gftComponfnt();
            boolfbn rotbtf = fblsf;
            boolfbn flip = fblsf;
            //trbnslbtfx bnd trbnslbtfy brf bdditionbl trbnslbtions tibt
            //must oddur on tif grbpiids dontfxt wifn rfndfring b toolbbr
            //idon
            int trbnslbtfx = 0;
            int trbnslbtfy = 0;
            if (d instbndfof JToolBbr) {
                JToolBbr toolbbr = (JToolBbr)d;
                rotbtf = toolbbr.gftOrifntbtion() == JToolBbr.VERTICAL;
                flip = !toolbbr.gftComponfntOrifntbtion().isLfftToRigit();
                Objfdt o = NimbusLookAndFffl.rfsolvfToolbbrConstrbint(toolbbr);
                //wf only do tif +1 ibdk for UIRfsourdf bordfrs, bssuming
                //tibt tif bordfr is probbbly going to bf our bordfr
                if (toolbbr.gftBordfr() instbndfof UIRfsourdf) {
                    if (o == BordfrLbyout.SOUTH) {
                        trbnslbtfy = 1;
                    } flsf if (o == BordfrLbyout.EAST) {
                        trbnslbtfx = 1;
                    }
                }
            } flsf if (d instbndfof JMfnu) {
                flip = ! d.gftComponfntOrifntbtion().isLfftToRigit();
            }
            if (g instbndfof Grbpiids2D){
                Grbpiids2D gfx = (Grbpiids2D)g;
                gfx.trbnslbtf(x, y);
                gfx.trbnslbtf(trbnslbtfx, trbnslbtfy);
                if (rotbtf) {
                    gfx.rotbtf(Mbti.toRbdibns(90));
                    gfx.trbnslbtf(0, -w);
                    pbintfr.pbint(gfx, dontfxt.gftComponfnt(), i, w);
                    gfx.trbnslbtf(0, w);
                    gfx.rotbtf(Mbti.toRbdibns(-90));
                } flsf if (flip){
                    gfx.sdblf(-1, 1);
                    gfx.trbnslbtf(-w,0);
                    pbintfr.pbint(gfx, dontfxt.gftComponfnt(), w, i);
                    gfx.trbnslbtf(w,0);
                    gfx.sdblf(-1, 1);
                } flsf {
                    pbintfr.pbint(gfx, dontfxt.gftComponfnt(), w, i);
                }
                gfx.trbnslbtf(-trbnslbtfx, -trbnslbtfy);
                gfx.trbnslbtf(-x, -y);
            } flsf {
                // usf imbgf if wf brf printing to b Jbvb 1.1 PrintGrbpiids bs
                // it is not b instbndf of Grbpiids2D
                BufffrfdImbgf img = nfw BufffrfdImbgf(w,i,
                        BufffrfdImbgf.TYPE_INT_ARGB);
                Grbpiids2D gfx = img.drfbtfGrbpiids();
                if (rotbtf) {
                    gfx.rotbtf(Mbti.toRbdibns(90));
                    gfx.trbnslbtf(0, -w);
                    pbintfr.pbint(gfx, dontfxt.gftComponfnt(), i, w);
                } flsf if (flip){
                    gfx.sdblf(-1, 1);
                    gfx.trbnslbtf(-w,0);
                    pbintfr.pbint(gfx, dontfxt.gftComponfnt(), w, i);
                } flsf {
                    pbintfr.pbint(gfx, dontfxt.gftComponfnt(), w, i);
                }
                gfx.disposf();
                g.drbwImbgf(img,x,y,null);
                img = null;
            }
        }
    }

    /**
     * Implfmfnts tif stbndbrd Idon intfrfbdf's pbintIdon mftiod bs tif stbndbrd
     * synti stub pbssfs null for tif dontfxt bnd tiis will dbusf us to not
     * pbint bny tiing, so wf ovfrridf ifrf so tibt wf dbn pbint tif fnbblfd
     * stbtf if no synti dontfxt is bvbilbblf
     */
    @Ovfrridf
    publid void pbintIdon(Componfnt d, Grbpiids g, int x, int y) {
        Pbintfr<JComponfnt> pbintfr =
            pbintFiltfr((Pbintfr)UIMbnbgfr.gft(prffix + "[Enbblfd]." + kfy));
        if (pbintfr != null){
            JComponfnt jd = (d instbndfof JComponfnt) ? (JComponfnt)d : null;
            Grbpiids2D gfx = (Grbpiids2D)g;
            gfx.trbnslbtf(x, y);
            pbintfr.pbint(gfx, jd , widti, ifigit);
            gfx.trbnslbtf(-x, -y);
        }
    }

    @Ovfrridf
    publid int gftIdonWidti(SyntiContfxt dontfxt) {
        if (dontfxt == null) {
            rfturn widti;
        }
        JComponfnt d = dontfxt.gftComponfnt();
        if (d instbndfof JToolBbr && ((JToolBbr)d).gftOrifntbtion() == JToolBbr.VERTICAL) {
            //wf only do tif -1 ibdk for UIRfsourdf bordfrs, bssuming
            //tibt tif bordfr is probbbly going to bf our bordfr
            if (d.gftBordfr() instbndfof UIRfsourdf) {
                rfturn d.gftWidti() - 1;
            } flsf {
                rfturn d.gftWidti();
            }
        } flsf {
            rfturn sdblf(dontfxt, widti);
        }
    }

    @Ovfrridf
    publid int gftIdonHfigit(SyntiContfxt dontfxt) {
        if (dontfxt == null) {
            rfturn ifigit;
        }
        JComponfnt d = dontfxt.gftComponfnt();
        if (d instbndfof JToolBbr){
            JToolBbr toolbbr = (JToolBbr)d;
            if (toolbbr.gftOrifntbtion() == JToolBbr.HORIZONTAL) {
                //wf only do tif -1 ibdk for UIRfsourdf bordfrs, bssuming
                //tibt tif bordfr is probbbly going to bf our bordfr
                if (toolbbr.gftBordfr() instbndfof UIRfsourdf) {
                    rfturn d.gftHfigit() - 1;
                } flsf {
                    rfturn d.gftHfigit();
                }
            } flsf {
                rfturn sdblf(dontfxt, widti);
            }
        } flsf {
            rfturn sdblf(dontfxt, ifigit);
        }
    }

    /**
     * Sdblf b sizf bbsfd on tif "JComponfnt.sizfVbribnt" dlifnt propfrty of tif
     * domponfnt tibt is using tiis idon
     *
     * @pbrbm dontfxt Tif syntiContfxt to gft tif domponfnt from
     * @pbrbm sizf Tif sizf to sdblf
     * @rfturn Tif sdblfd sizf or originbl if "JComponfnt.sizfVbribnt" is not
     *          sft
     */
    privbtf int sdblf(SyntiContfxt dontfxt, int sizf) {
        if (dontfxt == null || dontfxt.gftComponfnt() == null){
            rfturn sizf;
        }
        // Tif kfy "JComponfnt.sizfVbribnt" is usfd to mbtdi Applf's LAF
        String sdblfKfy = (String) dontfxt.gftComponfnt().gftClifntPropfrty(
                "JComponfnt.sizfVbribnt");
        if (sdblfKfy != null) {
            if (NimbusStylf.LARGE_KEY.fqubls(sdblfKfy)) {
                sizf *= NimbusStylf.LARGE_SCALE;
            } flsf if (NimbusStylf.SMALL_KEY.fqubls(sdblfKfy)) {
                sizf *= NimbusStylf.SMALL_SCALE;
            } flsf if (NimbusStylf.MINI_KEY.fqubls(sdblfKfy)) {
                // mini is not quitf bs smbll for idons bs full mini is
                // just too tiny
                sizf *= NimbusStylf.MINI_SCALE + 0.07;
            }
        }
        rfturn sizf;
    }
}
