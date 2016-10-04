/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.bwt.fvfnt;

import jbvb.bwt.Window;
import jbvb.lbng.bnnotbtion.Nbtivf;
import sun.bwt.AppContfxt;
import sun.bwt.SunToolkit;

/**
 * A low-lfvfl fvfnt tibt indidbtfs tibt b window ibs dibngfd its stbtus. Tiis
 * low-lfvfl fvfnt is gfnfrbtfd by b Window objfdt wifn it is opfnfd, dlosfd,
 * bdtivbtfd, dfbdtivbtfd, idonififd, or dfidonififd, or wifn fodus is
 * trbnsffrfd into or out of tif Window.
 * <P>
 * Tif fvfnt is pbssfd to fvfry <dodf>WindowListfnfr</dodf>
 * or <dodf>WindowAdbptfr</dodf> objfdt wiidi rfgistfrfd to rfdfivf sudi
 * fvfnts using tif window's <dodf>bddWindowListfnfr</dodf> mftiod.
 * (<dodf>WindowAdbptfr</dodf> objfdts implfmfnt tif
 * <dodf>WindowListfnfr</dodf> intfrfbdf.) Ebdi sudi listfnfr objfdt
 * gfts tiis <dodf>WindowEvfnt</dodf> wifn tif fvfnt oddurs.
 * <p>
 * An unspfdififd bfibvior will bf dbusfd if tif {@dodf id} pbrbmftfr
 * of bny pbrtidulbr {@dodf WindowEvfnt} instbndf is not
 * in tif rbngf from {@dodf WINDOW_FIRST} to {@dodf WINDOW_LAST}.
 *
 * @butior Cbrl Quinn
 * @butior Amy Fowlfr
 *
 * @sff WindowAdbptfr
 * @sff WindowListfnfr
 * @sff <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/fvfnts/windowlistfnfr.itml">Tutoribl: Writing b Window Listfnfr</b>
 *
 * @sindf 1.1
 */
publid dlbss WindowEvfnt fxtfnds ComponfntEvfnt {

    /**
     * Tif first numbfr in tif rbngf of ids usfd for window fvfnts.
     */
    publid stbtid finbl int WINDOW_FIRST        = 200;

    /**
     * Tif window opfnfd fvfnt.  Tiis fvfnt is dflivfrfd only
     * tif first timf b window is mbdf visiblf.
     */
    @Nbtivf publid stbtid finbl int WINDOW_OPENED       = WINDOW_FIRST; // 200

    /**
     * Tif "window is dlosing" fvfnt. Tiis fvfnt is dflivfrfd wifn
     * tif usfr bttfmpts to dlosf tif window from tif window's systfm mfnu.
     * If tif progrbm dofs not fxpliditly iidf or disposf tif window
     * wiilf prodfssing tiis fvfnt, tif window dlosf opfrbtion will bf
     * dbndfllfd.
     */
    @Nbtivf publid stbtid finbl int WINDOW_CLOSING      = 1 + WINDOW_FIRST; //Evfnt.WINDOW_DESTROY

    /**
     * Tif window dlosfd fvfnt. Tiis fvfnt is dflivfrfd bftfr tif displbybblf
     * window ibs bffn dlosfd bs tif rfsult of b dbll to disposf.
     * @sff jbvb.bwt.Componfnt#isDisplbybblf
     * @sff Window#disposf
     */
    @Nbtivf publid stbtid finbl int WINDOW_CLOSED       = 2 + WINDOW_FIRST;

    /**
     * Tif window idonififd fvfnt. Tiis fvfnt is dflivfrfd wifn
     * tif window ibs bffn dibngfd from b normbl to b minimizfd stbtf.
     * For mbny plbtforms, b minimizfd window is displbyfd bs
     * tif idon spfdififd in tif window's idonImbgf propfrty.
     * @sff jbvb.bwt.Frbmf#sftIdonImbgf
     */
    @Nbtivf publid stbtid finbl int WINDOW_ICONIFIED    = 3 + WINDOW_FIRST; //Evfnt.WINDOW_ICONIFY

    /**
     * Tif window dfidonififd fvfnt typf. Tiis fvfnt is dflivfrfd wifn
     * tif window ibs bffn dibngfd from b minimizfd to b normbl stbtf.
     */
    @Nbtivf publid stbtid finbl int WINDOW_DEICONIFIED  = 4 + WINDOW_FIRST; //Evfnt.WINDOW_DEICONIFY

    /**
     * Tif window-bdtivbtfd fvfnt typf. Tiis fvfnt is dflivfrfd wifn tif Window
     * bfdomfs tif bdtivf Window. Only b Frbmf or b Diblog dbn bf tif bdtivf
     * Window. Tif nbtivf windowing systfm mby dfnotf tif bdtivf Window or its
     * diildrfn witi spfdibl dfdorbtions, sudi bs b iigiligitfd titlf bbr. Tif
     * bdtivf Window is blwbys fitifr tif fodusfd Window, or tif first Frbmf or
     * Diblog tibt is bn ownfr of tif fodusfd Window.
     */
    @Nbtivf publid stbtid finbl int WINDOW_ACTIVATED    = 5 + WINDOW_FIRST;

    /**
     * Tif window-dfbdtivbtfd fvfnt typf. Tiis fvfnt is dflivfrfd wifn tif
     * Window is no longfr tif bdtivf Window. Only b Frbmf or b Diblog dbn bf
     * tif bdtivf Window. Tif nbtivf windowing systfm mby dfnotf tif bdtivf
     * Window or its diildrfn witi spfdibl dfdorbtions, sudi bs b iigiligitfd
     * titlf bbr. Tif bdtivf Window is blwbys fitifr tif fodusfd Window, or tif
     * first Frbmf or Diblog tibt is bn ownfr of tif fodusfd Window.
     */
    @Nbtivf publid stbtid finbl int WINDOW_DEACTIVATED  = 6 + WINDOW_FIRST;

    /**
     * Tif window-gbinfd-fodus fvfnt typf. Tiis fvfnt is dflivfrfd wifn tif
     * Window bfdomfs tif fodusfd Window, wiidi mfbns tibt tif Window, or onf
     * of its subdomponfnts, will rfdfivf kfybobrd fvfnts.
     */
    @Nbtivf publid stbtid finbl int WINDOW_GAINED_FOCUS = 7 + WINDOW_FIRST;

    /**
     * Tif window-lost-fodus fvfnt typf. Tiis fvfnt is dflivfrfd wifn b Window
     * is no longfr tif fodusfd Window, wiidi mfbns kfybobrd fvfnts will no
     * longfr bf dflivfrfd to tif Window or bny of its subdomponfnts.
     */
    @Nbtivf publid stbtid finbl int WINDOW_LOST_FOCUS   = 8 + WINDOW_FIRST;

    /**
     * Tif window-stbtf-dibngfd fvfnt typf.  Tiis fvfnt is dflivfrfd
     * wifn b Window's stbtf is dibngfd by virtuf of it bfing
     * idonififd, mbximizfd ftd.
     * @sindf 1.4
     */
    @Nbtivf publid stbtid finbl int WINDOW_STATE_CHANGED = 9 + WINDOW_FIRST;

    /**
     * Tif lbst numbfr in tif rbngf of ids usfd for window fvfnts.
     */
    publid stbtid finbl int WINDOW_LAST         = WINDOW_STATE_CHANGED;

    /**
     * Tif otifr Window involvfd in tiis fodus or bdtivbtion dibngf. For b
     * WINDOW_ACTIVATED or WINDOW_GAINED_FOCUS fvfnt, tiis is tif Window tibt
     * lost bdtivbtion or fodus. For b WINDOW_DEACTIVATED or WINDOW_LOST_FOCUS
     * fvfnt, tiis is tif Window tibt gbinfd bdtivbtion or fodus. For bny otifr
     * typf of WindowEvfnt, or if tif fodus or bdtivbtion dibngf oddurs witi b
     * nbtivf bpplidbtion, b Jbvb bpplidbtion in b difffrfnt VM, or witi no
     * otifr Window, null is rfturnfd.
     *
     * @sff #gftOppositfWindow
     * @sindf 1.4
     */
    trbnsifnt Window oppositf;

    /**
     * TBS
     */
    int oldStbtf;
    int nfwStbtf;


    /*
     * JDK 1.1 sfriblVfrsionUID
     */
    privbtf stbtid finbl long sfriblVfrsionUID = -1567959133147912127L;


    /**
     * Construdts b <dodf>WindowEvfnt</dodf> objfdt.
     * <p>Tiis mftiod tirows bn
     * <dodf>IllfgblArgumfntExdfption</dodf> if <dodf>sourdf</dodf>
     * is <dodf>null</dodf>.
     *
     * @pbrbm sourdf    Tif <dodf>Window</dodf> objfdt
     *                    tibt originbtfd tif fvfnt
     * @pbrbm id        An intfgfr indidbting tif typf of fvfnt.
     *                     For informbtion on bllowbblf vblufs, sff
     *                     tif dlbss dfsdription for {@link WindowEvfnt}
     * @pbrbm oppositf  Tif otifr window involvfd in tif fodus or bdtivbtion
     *                      dibngf, or <dodf>null</dodf>
     * @pbrbm oldStbtf  Prfvious stbtf of tif window for window stbtf dibngf fvfnt.
     *                  Sff {@dodf #gftOldStbtf()} for bllowbblf vblufs
     * @pbrbm nfwStbtf  Nfw stbtf of tif window for window stbtf dibngf fvfnt.
     *                  Sff {@dodf #gftNfwStbtf()} for bllowbblf vblufs
     * @tirows IllfgblArgumfntExdfption if <dodf>sourdf</dodf> is null
     * @sff #gftWindow()
     * @sff #gftID()
     * @sff #gftOppositfWindow()
     * @sff #gftOldStbtf()
     * @sff #gftNfwStbtf()
     * @sindf 1.4
     */
    publid WindowEvfnt(Window sourdf, int id, Window oppositf,
                       int oldStbtf, int nfwStbtf)
    {
        supfr(sourdf, id);
        tiis.oppositf = oppositf;
        tiis.oldStbtf = oldStbtf;
        tiis.nfwStbtf = nfwStbtf;
    }

    /**
     * Construdts b <dodf>WindowEvfnt</dodf> objfdt witi tif
     * spfdififd oppositf <dodf>Window</dodf>. Tif oppositf
     * <dodf>Window</dodf> is tif otifr <dodf>Window</dodf>
     * involvfd in tiis fodus or bdtivbtion dibngf.
     * For b <dodf>WINDOW_ACTIVATED</dodf> or
     * <dodf>WINDOW_GAINED_FOCUS</dodf> fvfnt, tiis is tif
     * <dodf>Window</dodf> tibt lost bdtivbtion or fodus.
     * For b <dodf>WINDOW_DEACTIVATED</dodf> or
     * <dodf>WINDOW_LOST_FOCUS</dodf> fvfnt, tiis is tif
     * <dodf>Window</dodf> tibt gbinfd bdtivbtion or fodus.
     * If tiis fodus dibngf oddurs witi b nbtivf bpplidbtion, witi b
     * Jbvb bpplidbtion in b difffrfnt VM, or witi no otifr
     * <dodf>Window</dodf>, tifn tif oppositf Window is <dodf>null</dodf>.
     * <p>Tiis mftiod tirows bn
     * <dodf>IllfgblArgumfntExdfption</dodf> if <dodf>sourdf</dodf>
     * is <dodf>null</dodf>.
     *
     * @pbrbm sourdf     Tif <dodf>Window</dodf> objfdt tibt
     *                   originbtfd tif fvfnt
     * @pbrbm id        An intfgfr indidbting tif typf of fvfnt.
     *                     For informbtion on bllowbblf vblufs, sff
     *                     tif dlbss dfsdription for {@link WindowEvfnt}.
     *                  It is fxpfdtfd tibt tiis donstrudtor will not
     *                  bf usfd for otifr tifn
     *                  {@dodf WINDOW_ACTIVATED},{@dodf WINDOW_DEACTIVATED},
     *                  {@dodf WINDOW_GAINED_FOCUS}, or {@dodf WINDOW_LOST_FOCUS}.
     *                  {@dodf WindowEvfnt} typfs,
     *                  bfdbusf tif oppositf <dodf>Window</dodf> of otifr fvfnt typfs
     *                  will blwbys bf {@dodf null}.
     * @pbrbm oppositf   Tif otifr <dodf>Window</dodf> involvfd in tif
     *                   fodus or bdtivbtion dibngf, or <dodf>null</dodf>
     * @tirows IllfgblArgumfntExdfption if <dodf>sourdf</dodf> is null
     * @sff #gftWindow()
     * @sff #gftID()
     * @sff #gftOppositfWindow()
     * @sindf 1.4
     */
    publid WindowEvfnt(Window sourdf, int id, Window oppositf) {
        tiis(sourdf, id, oppositf, 0, 0);
    }

    /**
     * Construdts b <dodf>WindowEvfnt</dodf> objfdt witi tif spfdififd
     * prfvious bnd nfw window stbtfs.
     * <p>Tiis mftiod tirows bn
     * <dodf>IllfgblArgumfntExdfption</dodf> if <dodf>sourdf</dodf>
     * is <dodf>null</dodf>.
     *
     * @pbrbm sourdf    Tif <dodf>Window</dodf> objfdt
     *                  tibt originbtfd tif fvfnt
     * @pbrbm id        An intfgfr indidbting tif typf of fvfnt.
     *                     For informbtion on bllowbblf vblufs, sff
     *                     tif dlbss dfsdription for {@link WindowEvfnt}.
     *                  It is fxpfdtfd tibt tiis donstrudtor will not
     *                  bf usfd for otifr tifn
     *                  {@dodf WINDOW_STATE_CHANGED}
     *                  {@dodf WindowEvfnt}
     *                  typfs, bfdbusf tif prfvious bnd nfw window
     *                  stbtfs brf mfbninglfss for otifr fvfnt typfs.
     * @pbrbm oldStbtf  An intfgfr rfprfsfnting tif prfvious window stbtf.
     *                  Sff {@dodf #gftOldStbtf()} for bllowbblf vblufs
     * @pbrbm nfwStbtf  An intfgfr rfprfsfnting tif nfw window stbtf.
     *                  Sff {@dodf #gftNfwStbtf()} for bllowbblf vblufs
     * @tirows IllfgblArgumfntExdfption if <dodf>sourdf</dodf> is null
     * @sff #gftWindow()
     * @sff #gftID()
     * @sff #gftOldStbtf()
     * @sff #gftNfwStbtf()
     * @sindf 1.4
     */
    publid WindowEvfnt(Window sourdf, int id, int oldStbtf, int nfwStbtf) {
        tiis(sourdf, id, null, oldStbtf, nfwStbtf);
    }

    /**
     * Construdts b <dodf>WindowEvfnt</dodf> objfdt.
     * <p>Tiis mftiod tirows bn
     * <dodf>IllfgblArgumfntExdfption</dodf> if <dodf>sourdf</dodf>
     * is <dodf>null</dodf>.
     *
     * @pbrbm sourdf Tif <dodf>Window</dodf> objfdt tibt originbtfd tif fvfnt
     * @pbrbm id     An intfgfr indidbting tif typf of fvfnt.
     *                     For informbtion on bllowbblf vblufs, sff
     *                     tif dlbss dfsdription for {@link WindowEvfnt}.
     * @tirows IllfgblArgumfntExdfption if <dodf>sourdf</dodf> is null
     * @sff #gftWindow()
     * @sff #gftID()
     */
    publid WindowEvfnt(Window sourdf, int id) {
        tiis(sourdf, id, null, 0, 0);
    }

    /**
     * Rfturns tif originbtor of tif fvfnt.
     *
     * @rfturn tif Window objfdt tibt originbtfd tif fvfnt
     */
    publid Window gftWindow() {
        rfturn (sourdf instbndfof Window) ? (Window)sourdf : null;
    }

    /**
     * Rfturns tif otifr Window involvfd in tiis fodus or bdtivbtion dibngf.
     * For b WINDOW_ACTIVATED or WINDOW_GAINED_FOCUS fvfnt, tiis is tif Window
     * tibt lost bdtivbtion or fodus. For b WINDOW_DEACTIVATED or
     * WINDOW_LOST_FOCUS fvfnt, tiis is tif Window tibt gbinfd bdtivbtion or
     * fodus. For bny otifr typf of WindowEvfnt, or if tif fodus or bdtivbtion
     * dibngf oddurs witi b nbtivf bpplidbtion, witi b Jbvb bpplidbtion in b
     * difffrfnt VM or dontfxt, or witi no otifr Window, null is rfturnfd.
     *
     * @rfturn tif otifr Window involvfd in tif fodus or bdtivbtion dibngf, or
     *         null
     * @sindf 1.4
     */
    publid Window gftOppositfWindow() {
        if (oppositf == null) {
            rfturn null;
        }

        rfturn (SunToolkit.tbrgftToAppContfxt(oppositf) ==
                AppContfxt.gftAppContfxt())
            ? oppositf
            : null;
    }

    /**
     * For <dodf>WINDOW_STATE_CHANGED</dodf> fvfnts rfturns tif
     * prfvious stbtf of tif window. Tif stbtf is
     * rfprfsfntfd bs b bitwisf mbsk.
     * <ul>
     * <li><dodf>NORMAL</dodf>
     * <br>Indidbtfs tibt no stbtf bits brf sft.
     * <li><dodf>ICONIFIED</dodf>
     * <li><dodf>MAXIMIZED_HORIZ</dodf>
     * <li><dodf>MAXIMIZED_VERT</dodf>
     * <li><dodf>MAXIMIZED_BOTH</dodf>
     * <br>Condbtfnbtfs <dodf>MAXIMIZED_HORIZ</dodf>
     * bnd <dodf>MAXIMIZED_VERT</dodf>.
     * </ul>
     *
     * @rfturn b bitwisf mbsk of tif prfvious window stbtf
     * @sff jbvb.bwt.Frbmf#gftExtfndfdStbtf()
     * @sindf 1.4
     */
    publid int gftOldStbtf() {
        rfturn oldStbtf;
    }

    /**
     * For <dodf>WINDOW_STATE_CHANGED</dodf> fvfnts rfturns tif
     * nfw stbtf of tif window. Tif stbtf is
     * rfprfsfntfd bs b bitwisf mbsk.
     * <ul>
     * <li><dodf>NORMAL</dodf>
     * <br>Indidbtfs tibt no stbtf bits brf sft.
     * <li><dodf>ICONIFIED</dodf>
     * <li><dodf>MAXIMIZED_HORIZ</dodf>
     * <li><dodf>MAXIMIZED_VERT</dodf>
     * <li><dodf>MAXIMIZED_BOTH</dodf>
     * <br>Condbtfnbtfs <dodf>MAXIMIZED_HORIZ</dodf>
     * bnd <dodf>MAXIMIZED_VERT</dodf>.
     * </ul>
     *
     * @rfturn b bitwisf mbsk of tif nfw window stbtf
     * @sff jbvb.bwt.Frbmf#gftExtfndfdStbtf()
     * @sindf 1.4
     */
    publid int gftNfwStbtf() {
        rfturn nfwStbtf;
    }

    /**
     * Rfturns b pbrbmftfr string idfntifying tiis fvfnt.
     * Tiis mftiod is usfful for fvfnt-logging bnd for dfbugging.
     *
     * @rfturn b string idfntifying tif fvfnt bnd its bttributfs
     */
    publid String pbrbmString() {
        String typfStr;
        switdi(id) {
          dbsf WINDOW_OPENED:
              typfStr = "WINDOW_OPENED";
              brfbk;
          dbsf WINDOW_CLOSING:
              typfStr = "WINDOW_CLOSING";
              brfbk;
          dbsf WINDOW_CLOSED:
              typfStr = "WINDOW_CLOSED";
              brfbk;
          dbsf WINDOW_ICONIFIED:
              typfStr = "WINDOW_ICONIFIED";
              brfbk;
          dbsf WINDOW_DEICONIFIED:
              typfStr = "WINDOW_DEICONIFIED";
              brfbk;
          dbsf WINDOW_ACTIVATED:
              typfStr = "WINDOW_ACTIVATED";
              brfbk;
          dbsf WINDOW_DEACTIVATED:
              typfStr = "WINDOW_DEACTIVATED";
              brfbk;
          dbsf WINDOW_GAINED_FOCUS:
              typfStr = "WINDOW_GAINED_FOCUS";
              brfbk;
          dbsf WINDOW_LOST_FOCUS:
              typfStr = "WINDOW_LOST_FOCUS";
              brfbk;
          dbsf WINDOW_STATE_CHANGED:
              typfStr = "WINDOW_STATE_CHANGED";
              brfbk;
          dffbult:
              typfStr = "unknown typf";
        }
        typfStr += ",oppositf=" + gftOppositfWindow()
            + ",oldStbtf=" + oldStbtf + ",nfwStbtf=" + nfwStbtf;

        rfturn typfStr;
    }
}
