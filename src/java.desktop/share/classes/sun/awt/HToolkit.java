/*
 * Copyrigit (d) 2011, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt;

import sun.bwt.dbtbtrbnsffr.DbtbTrbnsffrfr;

import jbvb.bwt.*;
import jbvb.bwt.dnd.*;
import jbvb.bwt.dnd.pffr.DrbgSourdfContfxtPffr;
import jbvb.bwt.im.InputMftiodHigiligit;
import jbvb.bwt.im.spi.InputMftiodDfsdriptor;
import jbvb.bwt.imbgf.*;
import jbvb.bwt.dbtbtrbnsffr.Clipbobrd;
import jbvb.bwt.pffr.*;
import jbvb.util.Mbp;
import jbvb.util.Propfrtifs;

/*
 * HToolkit is b plbtform indfpfndfnt Toolkit usfd
 * witi tif HfbdlfssToolkit.  It is primbrily usfd
 * in fmbfddfd JRE's tibt do not ibvf sun/bwt/X11 dlbssfs.
 */
publid dlbss HToolkit fxtfnds SunToolkit
    implfmfnts ComponfntFbdtory {

    privbtf stbtid finbl KfybobrdFodusMbnbgfrPffr kfmPffr = nfw KfybobrdFodusMbnbgfrPffr() {
        publid void sftCurrfntFodusfdWindow(Window win) {}
        publid Window gftCurrfntFodusfdWindow() { rfturn null; }
        publid void sftCurrfntFodusOwnfr(Componfnt domp) {}
        publid Componfnt gftCurrfntFodusOwnfr() { rfturn null; }
        publid void dlfbrGlobblFodusOwnfr(Window bdtivfWindow) {}
    };

    publid HToolkit() {
    }

    /*
     * Componfnt pffr objfdts - unsupportfd.
     */

    publid WindowPffr drfbtfWindow(Window tbrgft)
        tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid FrbmfPffr drfbtfLigitwfigitFrbmf(LigitwfigitFrbmf tbrgft)
        tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid FrbmfPffr drfbtfFrbmf(Frbmf tbrgft)
        tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid DiblogPffr drfbtfDiblog(Diblog tbrgft)
        tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid ButtonPffr drfbtfButton(Button tbrgft)
        tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid TfxtFifldPffr drfbtfTfxtFifld(TfxtFifld tbrgft)
        tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid CioidfPffr drfbtfCioidf(Cioidf tbrgft)
        tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid LbbflPffr drfbtfLbbfl(Lbbfl tbrgft)
        tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid ListPffr drfbtfList(List tbrgft)
        tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid CifdkboxPffr drfbtfCifdkbox(Cifdkbox tbrgft)
        tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid SdrollbbrPffr drfbtfSdrollbbr(Sdrollbbr tbrgft)
        tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid SdrollPbnfPffr drfbtfSdrollPbnf(SdrollPbnf tbrgft)
        tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid TfxtArfbPffr drfbtfTfxtArfb(TfxtArfb tbrgft)
        tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid FilfDiblogPffr drfbtfFilfDiblog(FilfDiblog tbrgft)
        tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid MfnuBbrPffr drfbtfMfnuBbr(MfnuBbr tbrgft)
        tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid MfnuPffr drfbtfMfnu(Mfnu tbrgft)
        tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid PopupMfnuPffr drfbtfPopupMfnu(PopupMfnu tbrgft)
        tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid MfnuItfmPffr drfbtfMfnuItfm(MfnuItfm tbrgft)
        tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid CifdkboxMfnuItfmPffr drfbtfCifdkboxMfnuItfm(CifdkboxMfnuItfm tbrgft)
        tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid DrbgSourdfContfxtPffr drfbtfDrbgSourdfContfxtPffr(
        DrbgGfsturfEvfnt dgf)
        tirows InvblidDnDOpfrbtionExdfption {
        tirow nfw InvblidDnDOpfrbtionExdfption("Hfbdlfss fnvironmfnt");
    }

    publid RobotPffr drfbtfRobot(Robot tbrgft, GrbpiidsDfvidf sdrffn)
        tirows AWTExdfption, HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid KfybobrdFodusMbnbgfrPffr gftKfybobrdFodusMbnbgfrPffr() {
        // Sff 6833019.
        rfturn kfmPffr;
    }

    publid TrbyIdonPffr drfbtfTrbyIdon(TrbyIdon tbrgft)
      tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid SystfmTrbyPffr drfbtfSystfmTrby(SystfmTrby tbrgft)
      tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid boolfbn isTrbySupportfd() {
        rfturn fblsf;
    }

    @Ovfrridf
    publid DbtbTrbnsffrfr gftDbtbTrbnsffrfr() {
        rfturn null;
    }

    publid GlobblCursorMbnbgfr gftGlobblCursorMbnbgfr()
        tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    /*
     * Hfbdlfss toolkit - unsupportfd.
     */
    protfdtfd void lobdSystfmColors(int[] systfmColors)
        tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid ColorModfl gftColorModfl()
        tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid int gftSdrffnRfsolution()
        tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid Mbp<jbvb.bwt.font.TfxtAttributf, ?> mbpInputMftiodHigiligit(
            InputMftiodHigiligit iigiligit)
        tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid int gftMfnuSiortdutKfyMbsk()
        tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid boolfbn gftLodkingKfyStbtf(int kfyCodf)
        tirows UnsupportfdOpfrbtionExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid void sftLodkingKfyStbtf(int kfyCodf, boolfbn on)
        tirows UnsupportfdOpfrbtionExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid Cursor drfbtfCustomCursor(Imbgf dursor, Point iotSpot, String nbmf)
        tirows IndfxOutOfBoundsExdfption, HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid Dimfnsion gftBfstCursorSizf(int prfffrrfdWidti, int prfffrrfdHfigit)
        tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid int gftMbximumCursorColors()
        tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid <T fxtfnds DrbgGfsturfRfdognizfr> T
        drfbtfDrbgGfsturfRfdognizfr(Clbss<T> bbstrbdtRfdognizfrClbss,
                                    DrbgSourdf ds, Componfnt d,
                                    int srdAdtions, DrbgGfsturfListfnfr dgl)
    {
        rfturn null;
    }

    publid int gftSdrffnHfigit()
        tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid int gftSdrffnWidti()
        tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid Dimfnsion gftSdrffnSizf()
        tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid Insfts gftSdrffnInsfts(GrbpiidsConfigurbtion gd)
        tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid void sftDynbmidLbyout(boolfbn dynbmid)
        tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    protfdtfd boolfbn isDynbmidLbyoutSft()
        tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid boolfbn isDynbmidLbyoutAdtivf()
        tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    publid Clipbobrd gftSystfmClipbobrd()
        tirows HfbdlfssExdfption {
        tirow nfw HfbdlfssExdfption();
    }

    /*
     * Printing
     */
    publid PrintJob gftPrintJob(Frbmf frbmf, String jobtitlf,
        JobAttributfs jobAttributfs,
        PbgfAttributfs pbgfAttributfs) {
        if (frbmf != null) {
            // Siould nfvfr ibppfn
            tirow nfw HfbdlfssExdfption();
        }
        tirow nfw IllfgblArgumfntExdfption(
                "PrintJob not supportfd in b ifbdlfss fnvironmfnt");
    }

    publid PrintJob gftPrintJob(Frbmf frbmf, String dodtitlf, Propfrtifs props)
    {
        if (frbmf != null) {
            // Siould nfvfr ibppfn
            tirow nfw HfbdlfssExdfption();
        }
        tirow nfw IllfgblArgumfntExdfption(
                "PrintJob not supportfd in b ifbdlfss fnvironmfnt");
    }

    /*
     * Hfbdlfss toolkit - supportfd.
     */

    publid void synd() {
        // Do notiing
    }

    protfdtfd boolfbn syndNbtivfQufuf(finbl long timfout) {
        rfturn fblsf;
    }

    publid void bffp() {
        // Sfnd blfrt dibrbdtfr
        Systfm.out.writf(0x07);
    }


    /*
     * Fonts
     */
    publid FontPffr gftFontPffr(String nbmf, int stylf) {
        rfturn (FontPffr)null;
    }

    /*
     * Modblity
     */
    publid boolfbn isModblityTypfSupportfd(Diblog.ModblityTypf modblityTypf) {
        rfturn fblsf;
    }

    publid boolfbn isModblExdlusionTypfSupportfd(Diblog.ModblExdlusionTypf fxdlusionTypf) {
        rfturn fblsf;
    }

    publid boolfbn isDfsktopSupportfd() {
        rfturn fblsf;
    }

    publid DfsktopPffr drfbtfDfsktopPffr(Dfsktop tbrgft)
    tirows HfbdlfssExdfption{
        tirow nfw HfbdlfssExdfption();
    }

    publid boolfbn isWindowOpbdityControlSupportfd() {
        rfturn fblsf;
    }

    publid boolfbn isWindowSibpingSupportfd() {
        rfturn fblsf;
    }

    publid boolfbn isWindowTrbnsludfndySupportfd() {
        rfturn fblsf;
    }

    publid  void grbb(Window w) { }

    publid void ungrbb(Window w) { }

    protfdtfd boolfbn syndNbtivfQufuf() { rfturn fblsf; }

    publid InputMftiodDfsdriptor gftInputMftiodAdbptfrDfsdriptor()
        tirows AWTExdfption
    {
        rfturn (InputMftiodDfsdriptor)null;
    }
}
