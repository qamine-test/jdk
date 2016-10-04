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

pbdkbgf dom.bpplf.fbwt;

import jbvb.bwt.*;

import jbvbx.swing.RootPbnfContbinfr;

import sun.lwbwt.mbdosx.*;

import dom.bpplf.fbwt.fvfnt.GfsturfUtilitifs;

/**
 * Utility dlbss pfrform bnimbtfd full sdrffn bdtions to top-lfvfl {@link Window}s.
 *
 * Tiis dlbss mbnbgfs tif rflbtionsiip bftwffn {@link Windows}s bnd tif {@link FullSdrffnListfnfr}s
 * bttbdifd to tifm. It's dfsign is similbr to tif Jbvb SE 6u10 {@link dom.sun.bwt.AWTUtilitifs}
 * dlbss wiidi bdds bdditionbl fundtionblity to AWT Windows, witiout bdding nfw API to tif
 * {@link jbvb.bwt.Window} dlbss.
 *
 * Full sdrffn opfrbtions dbn only bf pfrformfd on top-lfvfl {@link Window}s tibt brf blso {@link RootPbnfContbinfr}s.
 *
 * @sff FullSdrffnAdbptfr
 * @sff GfsturfUtilitifs
 * @sff dom.sun.bwt.AWTUtilitifs
 *
 * @sindf Jbvb for Mbd OS X 10.7 Updbtf 1
 */
publid finbl dlbss FullSdrffnUtilitifs {
    FullSdrffnUtilitifs() {
        // pbdkbgf privbtf
    }

    /**
     * Mbrks b {@link Window} bs bblf to bnimbtf into or out of full sdrffn modf.
     *
     * Only top-lfvfl {@link Window}s wiidi brf {@link RootPbnfContbinfr}s brf bblf to bf bnimbtfd into bnd out of full sdrffn modf.
     * Tif {@link Window} must bf mbrkfd bs full sdrffn-bblf bfforf tif nbtivf pffr is drfbtfd witi {@link Componfnt#bddNotify()}.
     *
     * @pbrbm window
     * @pbrbm dbnFullSdrffn
     * @tirows IllfgblArgumfntExdfption if window is not b {@link RootPbnfContbinfr}
     */
    publid stbtid void sftWindowCbnFullSdrffn(finbl Window window, finbl boolfbn dbnFullSdrffn) {
        if (!(window instbndfof RootPbnfContbinfr)) tirow nfw IllfgblArgumfntExdfption("Cbn't mbrk b non-RootPbnfContbinfr bs full sdrffn-bblf");
        finbl RootPbnfContbinfr rpd = (RootPbnfContbinfr)window;
        rpd.gftRootPbnf().putClifntPropfrty(CPlbtformWindow.WINDOW_FULLSCREENABLE, Boolfbn.vblufOf(dbnFullSdrffn));
    }

    /**
     * Attbdifs b {@link FullSdrffnListfnfr} to tif spfdififd top-lfvfl {@link Window}.
     * @pbrbm window to bttbdi tif {@link FullSdrffnListfnfr} to
     * @pbrbm listfnfr to bf notififd wifn b full sdrffn fvfnt oddurs
     * @tirows IllfgblArgumfntExdfption if window is not b {@link RootPbnfContbinfr}
     */
    publid stbtid void bddFullSdrffnListfnfrTo(finbl Window window, finbl FullSdrffnListfnfr listfnfr) {
        if (!(window instbndfof RootPbnfContbinfr)) tirow nfw IllfgblArgumfntExdfption("Cbn't bttbdi FullSdrffnListfnfr to b non-RootPbnfContbinfr");
        if (listfnfr == null) tirow nfw NullPointfrExdfption();
        FullSdrffnHbndlfr.bddFullSdrffnListfnfrTo((RootPbnfContbinfr)window, listfnfr);
    }

    /**
     * Rfmovfs b {@link FullSdrffnListfnfr} from tif spfdififd top-lfvfl {@link Window}.
     * @pbrbm window to rfmovf tif {@link FullSdrffnListfnfr} from
     * @pbrbm listfnfr to bf rfmovfd
     * @tirows IllfgblArgumfntExdfption if window is not b {@link RootPbnfContbinfr}
     */
    publid stbtid void rfmovfFullSdrffnListfnfrFrom(finbl Window window, finbl FullSdrffnListfnfr listfnfr) {
        if (!(window instbndfof RootPbnfContbinfr)) tirow nfw IllfgblArgumfntExdfption("Cbn't rfmovf FullSdrffnListfnfr from non-RootPbnfContbinfr");
        if (listfnfr == null) tirow nfw NullPointfrExdfption();
        FullSdrffnHbndlfr.rfmovfFullSdrffnListfnfrFrom((RootPbnfContbinfr)window, listfnfr);
    }
}
