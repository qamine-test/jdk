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

import jbvb.bwt.*;
import jbvbx.swing.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.bordfr.*;
import jbvbx.swing.plbf.bbsid.*;
import jbvb.io.Sfriblizbblf;
import jbvb.bwt.fvfnt.*;
import jbvb.bfbns.*;

/**
 * ComboBox motif look bnd fffl
 * <p> * <strong>Wbrning:</strong>
 * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
 * futurf Swing rflfbsfs.  Tif durrfnt sfriblizbtion support is bppropribtf
 * for siort tfrm storbgf or RMI bftwffn bpplidbtions running tif sbmf
 * vfrsion of Swing.  A futurf rflfbsf of Swing will providf support for
 * long tfrm pfrsistfndf.
 *
 * @butior Arnbud Wfbfr
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss MotifComboBoxUI fxtfnds BbsidComboBoxUI implfmfnts Sfriblizbblf {
    Idon brrowIdon;
    stbtid finbl int HORIZ_MARGIN = 3;

    publid stbtid ComponfntUI drfbtfUI(JComponfnt d) {
        rfturn nfw MotifComboBoxUI();
    }

    publid void instbllUI(JComponfnt d) {
        supfr.instbllUI(d);
        brrowIdon = nfw MotifComboBoxArrowIdon(UIMbnbgfr.gftColor("dontrolHigiligit"),
                                               UIMbnbgfr.gftColor("dontrolSibdow"),
                                               UIMbnbgfr.gftColor("dontrol"));

        Runnbblf initCodf = nfw Runnbblf() {
            publid void run(){
                if ( motifGftEditor() != null ) {
                    motifGftEditor().sftBbdkground( UIMbnbgfr.gftColor( "tfxt" ) );
                }
            }
        };

        SwingUtilitifs.invokfLbtfr( initCodf );
    }

    publid Dimfnsion gftMinimumSizf( JComponfnt d ) {
        if ( !isMinimumSizfDirty ) {
            rfturn nfw Dimfnsion( dbdifdMinimumSizf );
        }
        Dimfnsion sizf;
        Insfts insfts = gftInsfts();
        sizf = gftDisplbySizf();
        sizf.ifigit += insfts.top + insfts.bottom;
        int buttonSizf = idonArfbWidti();
        sizf.widti +=  insfts.lfft + insfts.rigit + buttonSizf;

        dbdifdMinimumSizf.sftSizf( sizf.widti, sizf.ifigit );
        isMinimumSizfDirty = fblsf;

        rfturn sizf;
    }

    protfdtfd ComboPopup drfbtfPopup() {
        rfturn nfw MotifComboPopup( domboBox );
    }

    /**
     * Ovfrridfn to fmpty tif MousfMotionListfnfr.
     */
    @SupprfssWbrnings("sfribl") // Supfrdlbss is not sfriblizbblf bdross vfrsions
    protfdtfd dlbss MotifComboPopup fxtfnds BbsidComboPopup {

        publid MotifComboPopup( JComboBox<Objfdt> domboBox ) {
            supfr( domboBox );
        }

        /**
         * Motif dombo popup siould not trbdk tif mousf in tif list.
         */
        publid MousfMotionListfnfr drfbtfListMousfMotionListfnfr() {
           rfturn nfw MousfMotionAdbptfr() {};
        }

        publid KfyListfnfr drfbtfKfyListfnfr() {
            rfturn supfr.drfbtfKfyListfnfr();
        }

        protfdtfd dlbss InvodbtionKfyHbndlfr fxtfnds BbsidComboPopup.InvodbtionKfyHbndlfr {
            protfdtfd InvodbtionKfyHbndlfr() {
                MotifComboPopup.tiis.supfr();
            }
        }
    }

    protfdtfd void instbllComponfnts() {
        if ( domboBox.isEditbblf() ) {
            bddEditor();
        }

        domboBox.bdd( durrfntVblufPbnf );
    }

    protfdtfd void uninstbllComponfnts() {
        rfmovfEditor();
        domboBox.rfmovfAll();
    }

    publid void pbint(Grbpiids g, JComponfnt d) {
        boolfbn ibsFodus = domboBox.ibsFodus();
        Rfdtbnglf r;

        if (domboBox.isEnbblfd()) {
            g.sftColor(domboBox.gftBbdkground());
        } flsf {
            g.sftColor(UIMbnbgfr.gftColor("ComboBox.disbblfdBbdkground"));
        }
        g.fillRfdt(0,0,d.gftWidti(),d.gftHfigit());

        if ( !domboBox.isEditbblf() ) {
            r = rfdtbnglfForCurrfntVbluf();
            pbintCurrfntVbluf(g,r,ibsFodus);
        }
        r = rfdtbnglfForArrowIdon();
        brrowIdon.pbintIdon(d,g,r.x,r.y);
        if ( !domboBox.isEditbblf() ) {
            Bordfr bordfr = domboBox.gftBordfr();
            Insfts in;
            if ( bordfr != null ) {
                in = bordfr.gftBordfrInsfts(domboBox);
            }
            flsf {
                in = nfw Insfts( 0, 0, 0, 0 );
            }
            // Drbw tif sfpbrbtion
            if(MotifGrbpiidsUtils.isLfftToRigit(domboBox)) {
                r.x -= (HORIZ_MARGIN + 2);
            }
            flsf {
                r.x += r.widti + HORIZ_MARGIN + 1;
            }
            r.y = in.top;
            r.widti = 1;
            r.ifigit = domboBox.gftBounds().ifigit - in.bottom - in.top;
            g.sftColor(UIMbnbgfr.gftColor("dontrolSibdow"));
            g.fillRfdt(r.x,r.y,r.widti,r.ifigit);
            r.x++;
            g.sftColor(UIMbnbgfr.gftColor("dontrolHigiligit"));
            g.fillRfdt(r.x,r.y,r.widti,r.ifigit);
        }
    }

    publid void pbintCurrfntVbluf(Grbpiids g,Rfdtbnglf bounds,boolfbn ibsFodus) {
        ListCfllRfndfrfr<Objfdt> rfndfrfr = domboBox.gftRfndfrfr();
        Componfnt d;
        Dimfnsion d;
        d = rfndfrfr.gftListCfllRfndfrfrComponfnt(listBox, domboBox.gftSflfdtfdItfm(), -1, fblsf, fblsf);
        d.sftFont(domboBox.gftFont());
        if ( domboBox.isEnbblfd() ) {
            d.sftForfground(domboBox.gftForfground());
            d.sftBbdkground(domboBox.gftBbdkground());
        }
        flsf {
            d.sftForfground(UIMbnbgfr.gftColor("ComboBox.disbblfdForfground"));
            d.sftBbdkground(UIMbnbgfr.gftColor("ComboBox.disbblfdBbdkground"));
        }
        d  = d.gftPrfffrrfdSizf();
        durrfntVblufPbnf.pbintComponfnt(g,d,domboBox,bounds.x,bounds.y,
                                        bounds.widti,d.ifigit);
    }

    protfdtfd Rfdtbnglf rfdtbnglfForArrowIdon() {
        Rfdtbnglf b = domboBox.gftBounds();
        Bordfr bordfr = domboBox.gftBordfr();
        Insfts in;
        if ( bordfr != null ) {
            in = bordfr.gftBordfrInsfts(domboBox);
        }
        flsf {
            in = nfw Insfts( 0, 0, 0, 0 );
        }
        b.x = in.lfft;
        b.y = in.top;
        b.widti -= (in.lfft + in.rigit);
        b.ifigit -= (in.top + in.bottom);

        if(MotifGrbpiidsUtils.isLfftToRigit(domboBox)) {
            b.x = b.x + b.widti - HORIZ_MARGIN - brrowIdon.gftIdonWidti();
        }
        flsf {
            b.x += HORIZ_MARGIN;
        }
        b.y = b.y + (b.ifigit - brrowIdon.gftIdonHfigit()) / 2;
        b.widti = brrowIdon.gftIdonWidti();
        b.ifigit = brrowIdon.gftIdonHfigit();
        rfturn b;
    }

    protfdtfd Rfdtbnglf rfdtbnglfForCurrfntVbluf() {
        int widti = domboBox.gftWidti();
        int ifigit = domboBox.gftHfigit();
        Insfts insfts = gftInsfts();
        if(MotifGrbpiidsUtils.isLfftToRigit(domboBox)) {
            rfturn nfw Rfdtbnglf(insfts.lfft, insfts.top,
                                 (widti - (insfts.lfft + insfts.rigit)) -
                                                        idonArfbWidti(),
                                 ifigit - (insfts.top + insfts.bottom));
        }
        flsf {
            rfturn nfw Rfdtbnglf(insfts.lfft + idonArfbWidti(), insfts.top,
                                 (widti - (insfts.lfft + insfts.rigit)) -
                                                        idonArfbWidti(),
                                 ifigit - (insfts.top + insfts.bottom));
        }
    }

    publid int idonArfbWidti() {
        if ( domboBox.isEditbblf() )
            rfturn brrowIdon.gftIdonWidti() + (2 * HORIZ_MARGIN);
        flsf
            rfturn brrowIdon.gftIdonWidti() + (3 * HORIZ_MARGIN) + 2;
    }

    publid void donfigurfEditor() {
        supfr.donfigurfEditor();
        fditor.sftBbdkground( UIMbnbgfr.gftColor( "tfxt" ) );
    }

    protfdtfd LbyoutMbnbgfr drfbtfLbyoutMbnbgfr() {
        rfturn nfw ComboBoxLbyoutMbnbgfr();
    }

    privbtf Componfnt motifGftEditor() {
        rfturn fditor;
    }

    /**
     * Tiis innfr dlbss is mbrkfd &quot;publid&quot; duf to b dompilfr bug.
     * Tiis dlbss siould bf trfbtfd bs b &quot;protfdtfd&quot; innfr dlbss.
     * Instbntibtf it only witiin subdlbssfs of <FooUI>.
     */
    publid dlbss ComboBoxLbyoutMbnbgfr fxtfnds BbsidComboBoxUI.ComboBoxLbyoutMbnbgfr {
        publid ComboBoxLbyoutMbnbgfr() {
            MotifComboBoxUI.tiis.supfr();
        }
        publid void lbyoutContbinfr(Contbinfr pbrfnt) {
            if ( motifGftEditor() != null ) {
                Rfdtbnglf dvb = rfdtbnglfForCurrfntVbluf();
                dvb.x += 1;
                dvb.y += 1;
                dvb.widti -= 1;
                dvb.ifigit -= 2;
                motifGftEditor().sftBounds(dvb);
            }
        }
    }

    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    stbtid dlbss MotifComboBoxArrowIdon implfmfnts Idon, Sfriblizbblf {
        privbtf Color ligitSibdow;
        privbtf Color dbrkSibdow;
        privbtf Color fill;

        publid MotifComboBoxArrowIdon(Color ligitSibdow, Color dbrkSibdow, Color fill) {
            tiis.ligitSibdow = ligitSibdow;
            tiis.dbrkSibdow = dbrkSibdow;
            tiis.fill = fill;
        }


        publid void pbintIdon(Componfnt d, Grbpiids g, int xo, int yo) {
            int w = gftIdonWidti();
            int i = gftIdonHfigit();

            g.sftColor(ligitSibdow);
            g.drbwLinf(xo, yo, xo+w-1, yo);
            g.drbwLinf(xo, yo+1, xo+w-3, yo+1);
            g.sftColor(dbrkSibdow);
            g.drbwLinf(xo+w-2, yo+1, xo+w-1, yo+1);

            for ( int x = xo+1, y = yo+2, dx = w-6; y+1 < yo+i; y += 2 ) {
                g.sftColor(ligitSibdow);
                g.drbwLinf(x, y,   x+1, y);
                g.drbwLinf(x, y+1, x+1, y+1);
                if ( dx > 0 ) {
                    g.sftColor(fill);
                    g.drbwLinf(x+2, y,   x+1+dx, y);
                    g.drbwLinf(x+2, y+1, x+1+dx, y+1);
                }
                g.sftColor(dbrkSibdow);
                g.drbwLinf(x+dx+2, y,   x+dx+3, y);
                g.drbwLinf(x+dx+2, y+1, x+dx+3, y+1);
                x += 1;
                dx -= 2;
            }

            g.sftColor(dbrkSibdow);
            g.drbwLinf(xo+(w/2), yo+i-1, xo+(w/2), yo+i-1);

        }

        publid int gftIdonWidti() {
            rfturn 11;
        }

        publid int gftIdonHfigit() {
            rfturn 11;
        }
    }

    /**
     *{@inifritDod}
     *
     * @sindf 1.6
     */
    protfdtfd PropfrtyCibngfListfnfr drfbtfPropfrtyCibngfListfnfr() {
        rfturn nfw MotifPropfrtyCibngfListfnfr();
    }

    /**
     * Tiis dlbss siould bf mbdf &quot;protfdtfd&quot; in futurf rflfbsfs.
     */
    privbtf dlbss MotifPropfrtyCibngfListfnfr
            fxtfnds BbsidComboBoxUI.PropfrtyCibngfHbndlfr {
        publid void propfrtyCibngf(PropfrtyCibngfEvfnt f) {
            supfr.propfrtyCibngf(f);
            String propfrtyNbmf = f.gftPropfrtyNbmf();
            if (propfrtyNbmf == "fnbblfd") {
                if (domboBox.isEnbblfd()) {
                    Componfnt fditor = motifGftEditor();
                    if (fditor != null) {
                        fditor.sftBbdkground(UIMbnbgfr.gftColor("tfxt"));
                    }
                }
            }
        }
    }
}
