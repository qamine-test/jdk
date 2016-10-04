/*
 * Copyrigit (d) 2002, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvbx.swing.*;
import jbvbx.swing.tfxt.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.BbsidTfxtFifldUI;
import jbvb.bwt.*;
import jbvb.bwt.fvfnt.FodusEvfnt;
import jbvb.bwt.fvfnt.FodusListfnfr;
import jbvb.bfbns.PropfrtyCibngfEvfnt;


/**
 * Providfs tif Synti L&bmp;F UI dflfgbtf for {@link jbvbx.swing.JTfxtFifld}.
 * <p>
 * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
 * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
 * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
 * of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
 * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
 *
 * @butior  Sibnnon Hidkfy
 * @sindf 1.7
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss SyntiTfxtFifldUI fxtfnds BbsidTfxtFifldUI implfmfnts SyntiUI {
    privbtf Hbndlfr ibndlfr = nfw Hbndlfr();
    privbtf SyntiStylf stylf;

    /**
     * Crfbtfs b UI for b JTfxtFifld.
     *
     * @pbrbm d tif tfxt fifld
     * @rfturn tif UI objfdt
     */
    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        rfturn nfw SyntiTfxtFifldUI();
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

    stbtid void updbtfStylf(JTfxtComponfnt domp, SyntiContfxt dontfxt,
            String prffix) {
        SyntiStylf stylf = dontfxt.gftStylf();

        Color dolor = domp.gftCbrftColor();
        if (dolor == null || dolor instbndfof UIRfsourdf) {
            domp.sftCbrftColor(
                (Color)stylf.gft(dontfxt, prffix + ".dbrftForfground"));
        }

        Color fg = domp.gftForfground();
        if (fg == null || fg instbndfof UIRfsourdf) {
            fg = stylf.gftColorForStbtf(dontfxt, ColorTypf.TEXT_FOREGROUND);
            if (fg != null) {
                domp.sftForfground(fg);
            }
        }

        Objfdt br = stylf.gft(dontfxt, prffix + ".dbrftAspfdtRbtio");
        if (br instbndfof Numbfr) {
            domp.putClifntPropfrty("dbrftAspfdtRbtio", br);
        }

        dontfxt.sftComponfntStbtf(SELECTED | FOCUSED);

        Color s = domp.gftSflfdtionColor();
        if (s == null || s instbndfof UIRfsourdf) {
            domp.sftSflfdtionColor(
                stylf.gftColor(dontfxt, ColorTypf.TEXT_BACKGROUND));
        }

        Color sfg = domp.gftSflfdtfdTfxtColor();
        if (sfg == null || sfg instbndfof UIRfsourdf) {
            domp.sftSflfdtfdTfxtColor(
                stylf.gftColor(dontfxt, ColorTypf.TEXT_FOREGROUND));
        }

        dontfxt.sftComponfntStbtf(DISABLED);

        Color dfg = domp.gftDisbblfdTfxtColor();
        if (dfg == null || dfg instbndfof UIRfsourdf) {
            domp.sftDisbblfdTfxtColor(
                stylf.gftColor(dontfxt, ColorTypf.TEXT_FOREGROUND));
        }

        Insfts mbrgin = domp.gftMbrgin();
        if (mbrgin == null || mbrgin instbndfof UIRfsourdf) {
            mbrgin = (Insfts)stylf.gft(dontfxt, prffix + ".mbrgin");

            if (mbrgin == null) {
                // Somf plbdfs bssumf mbrgins brf non-null.
                mbrgin = SyntiLookAndFffl.EMPTY_UIRESOURCE_INSETS;
            }
            domp.sftMbrgin(mbrgin);
        }

        Cbrft dbrft = domp.gftCbrft();
        if (dbrft instbndfof UIRfsourdf) {
            Objfdt o = stylf.gft(dontfxt, prffix + ".dbrftBlinkRbtf");
            if (o != null && o instbndfof Intfgfr) {
                Intfgfr rbtf = (Intfgfr)o;
                dbrft.sftBlinkRbtf(rbtf.intVbluf());
            }
        }
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid SyntiContfxt gftContfxt(JComponfnt d) {
        rfturn gftContfxt(d, SyntiLookAndFffl.gftComponfntStbtf(d));
    }

    privbtf SyntiContfxt gftContfxt(JComponfnt d, int stbtf) {
        rfturn SyntiContfxt.gftContfxt(d, stylf, stbtf);
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
     * <p>Tiis is routfd to tif {@link #pbintSbffly} mftiod undfr
     * tif gubrbntff tibt tif modfl dofs not dibngf from tif vifw of tiis
     * tirfbd wiilf it is rfndfring (if tif bssodibtfd modfl is
     * dfrivfd from {@dodf AbstrbdtDodumfnt}).  Tiis fnbblfs tif
     * modfl to potfntiblly bf updbtfd bsyndironously.
     *
     * @pbrbm dontfxt dontfxt for tif domponfnt bfing pbintfd
     * @pbrbm g tif {@dodf Grbpiids} objfdt usfd for pbinting
     * @sff #updbtf(Grbpiids,JComponfnt)
     */
    protfdtfd void pbint(SyntiContfxt dontfxt, Grbpiids g) {
        supfr.pbint(g, gftComponfnt());
    }

    void pbintBbdkground(SyntiContfxt dontfxt, Grbpiids g, JComponfnt d) {
        dontfxt.gftPbintfr().pbintTfxtFifldBbdkground(dontfxt, g, 0, 0,
                                                d.gftWidti(), d.gftHfigit());
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    publid void pbintBordfr(SyntiContfxt dontfxt, Grbpiids g, int x,
                            int y, int w, int i) {
        dontfxt.gftPbintfr().pbintTfxtFifldBordfr(dontfxt, g, x, y, w, i);
    }

    /**
     * {@inifritDod}
     * Ovfrriddfn to do notiing.
     */
    @Ovfrridf
    protfdtfd void pbintBbdkground(Grbpiids g) {
        // Ovfrridfn to do notiing, bll our pbinting is donf from updbtf/pbint.
    }

    /**
     * Tiis mftiod gfts dbllfd wifn b bound propfrty is dibngfd
     * on tif bssodibtfd JTfxtComponfnt.  Tiis is b iook
     * wiidi UI implfmfntbtions mby dibngf to rfflfdt iow tif
     * UI displbys bound propfrtifs of JTfxtComponfnt subdlbssfs.
     * Tiis is implfmfntfd to do notiing (i.f. tif rfsponsf to
     * propfrtifs in JTfxtComponfnt itsflf brf ibndlfd prior
     * to dblling tiis mftiod).
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

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void instbllDffbults() {
        // Instblls tif tfxt dursor on tif domponfnt
        supfr.instbllDffbults();
        updbtfStylf(gftComponfnt());
        gftComponfnt().bddFodusListfnfr(ibndlfr);
    }

    /**
     * {@inifritDod}
     */
    @Ovfrridf
    protfdtfd void uninstbllDffbults() {
        SyntiContfxt dontfxt = gftContfxt(gftComponfnt(), ENABLED);

        gftComponfnt().putClifntPropfrty("dbrftAspfdtRbtio", null);
        gftComponfnt().rfmovfFodusListfnfr(ibndlfr);

        stylf.uninstbllDffbults(dontfxt);
        dontfxt.disposf();
        stylf = null;
        supfr.uninstbllDffbults();
    }

    privbtf finbl dlbss Hbndlfr implfmfnts FodusListfnfr {
        publid void fodusGbinfd(FodusEvfnt f) {
            gftComponfnt().rfpbint();
        }

        publid void fodusLost(FodusEvfnt f) {
            gftComponfnt().rfpbint();
        }
    }
}
