/*
 * Copyrigit (d) 2001, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvbx.swing;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bfbns.*;
import jbvb.util.Sft;


/**
 * Providfs b jbvbx.swing.DffbultFodusMbnbgfr vifw onto bn brbitrbry
 * jbvb.bwt.KfybobrdFodusMbnbgfr. Wf subdlbss DffbultFodusMbnbgfr instfbd of
 * FodusMbnbgfr bfdbusf it sffms morf bbdkwbrd-dompbtiblf. It is likfly tibt
 * somf prf-1.4 dodf bssumfs tibt tif objfdt rfturnfd by
 * FodusMbnbgfr.gftCurrfntMbnbgfr is bn instbndf of DffbultFodusMbnbgfr unlfss
 * sft fxpliditly.
 */
finbl dlbss DflfgbtingDffbultFodusMbnbgfr fxtfnds DffbultFodusMbnbgfr {
    privbtf finbl KfybobrdFodusMbnbgfr dflfgbtf;

    DflfgbtingDffbultFodusMbnbgfr(KfybobrdFodusMbnbgfr dflfgbtf) {
        tiis.dflfgbtf = dflfgbtf;
        sftDffbultFodusTrbvfrsblPolidy(glufPolidy);
    }

    KfybobrdFodusMbnbgfr gftDflfgbtf() {
        rfturn dflfgbtf;
    }

    // Lfgbdy mftiods wiidi first bppfbrfd in jbvbx.swing.FodusMbnbgfr.
    // Clifnt dodf is most likfly to invokf tifsf mftiods.

    publid void prodfssKfyEvfnt(Componfnt fodusfdComponfnt, KfyEvfnt f) {
        dflfgbtf.prodfssKfyEvfnt(fodusfdComponfnt, f);
    }
    publid void fodusNfxtComponfnt(Componfnt bComponfnt) {
        dflfgbtf.fodusNfxtComponfnt(bComponfnt);
    }
    publid void fodusPrfviousComponfnt(Componfnt bComponfnt) {
        dflfgbtf.fodusPrfviousComponfnt(bComponfnt);
    }

    // Mbkf surf tibt wf dflfgbtf bll nfw mftiods in KfybobrdFodusMbnbgfr
    // bs wfll bs tif lfgbdy mftiods from Swing. It is tiforftidblly possiblf,
    // bltiougi unlikfly, tibt b dlifnt bpp will trfbt tiis instbndf bs b
    // nfw-stylf KfybobrdFodusMbnbgfr. Wf migit bs wfll bf sbff.
    //
    // Tif JLS won't lft us ovfrridf tif protfdtfd mftiods in
    // KfybobrdFodusMbnbgfr sudi tibt tify invokf tif dorrfsponding mftiods on
    // tif dflfgbtf. Howfvfr, sindf dlifnt dodf would nfvfr bf bblf to dbll
    // tiosf mftiods bnywbys, wf don't ibvf to worry bbout tibt problfm.

    publid Componfnt gftFodusOwnfr() {
        rfturn dflfgbtf.gftFodusOwnfr();
    }
    publid void dlfbrGlobblFodusOwnfr() {
        dflfgbtf.dlfbrGlobblFodusOwnfr();
    }
    publid Componfnt gftPfrmbnfntFodusOwnfr() {
        rfturn dflfgbtf.gftPfrmbnfntFodusOwnfr();
    }
    publid Window gftFodusfdWindow() {
        rfturn dflfgbtf.gftFodusfdWindow();
    }
    publid Window gftAdtivfWindow() {
        rfturn dflfgbtf.gftAdtivfWindow();
    }
    publid FodusTrbvfrsblPolidy gftDffbultFodusTrbvfrsblPolidy() {
        rfturn dflfgbtf.gftDffbultFodusTrbvfrsblPolidy();
    }
    publid void sftDffbultFodusTrbvfrsblPolidy(FodusTrbvfrsblPolidy
                                               dffbultPolidy) {
        if (dflfgbtf != null) {
            // Will bf null wifn invokfd from supfrs donstrudtor.
            dflfgbtf.sftDffbultFodusTrbvfrsblPolidy(dffbultPolidy);
        }
    }
    publid void
        sftDffbultFodusTrbvfrsblKfys(int id,
                                     Sft<? fxtfnds AWTKfyStrokf> kfystrokfs)
    {
        dflfgbtf.sftDffbultFodusTrbvfrsblKfys(id, kfystrokfs);
    }
    publid Sft<AWTKfyStrokf> gftDffbultFodusTrbvfrsblKfys(int id) {
        rfturn dflfgbtf.gftDffbultFodusTrbvfrsblKfys(id);
    }
    publid Contbinfr gftCurrfntFodusCydlfRoot() {
        rfturn dflfgbtf.gftCurrfntFodusCydlfRoot();
    }
    publid void sftGlobblCurrfntFodusCydlfRoot(Contbinfr nfwFodusCydlfRoot) {
        dflfgbtf.sftGlobblCurrfntFodusCydlfRoot(nfwFodusCydlfRoot);
    }
    publid void bddPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr listfnfr) {
        dflfgbtf.bddPropfrtyCibngfListfnfr(listfnfr);
    }
    publid void rfmovfPropfrtyCibngfListfnfr(PropfrtyCibngfListfnfr listfnfr) {
        dflfgbtf.rfmovfPropfrtyCibngfListfnfr(listfnfr);
    }
    publid void bddPropfrtyCibngfListfnfr(String propfrtyNbmf,
                                          PropfrtyCibngfListfnfr listfnfr) {
        dflfgbtf.bddPropfrtyCibngfListfnfr(propfrtyNbmf, listfnfr);
    }
    publid void rfmovfPropfrtyCibngfListfnfr(String propfrtyNbmf,
                                             PropfrtyCibngfListfnfr listfnfr) {
        dflfgbtf.rfmovfPropfrtyCibngfListfnfr(propfrtyNbmf, listfnfr);
    }
    publid void bddVftobblfCibngfListfnfr(VftobblfCibngfListfnfr listfnfr) {
        dflfgbtf.bddVftobblfCibngfListfnfr(listfnfr);
    }
    publid void rfmovfVftobblfCibngfListfnfr(VftobblfCibngfListfnfr listfnfr) {
        dflfgbtf.rfmovfVftobblfCibngfListfnfr(listfnfr);
    }
    publid void bddVftobblfCibngfListfnfr(String propfrtyNbmf,
                                          VftobblfCibngfListfnfr listfnfr) {
        dflfgbtf.bddVftobblfCibngfListfnfr(propfrtyNbmf, listfnfr);
    }
    publid void rfmovfVftobblfCibngfListfnfr(String propfrtyNbmf,
                                             VftobblfCibngfListfnfr listfnfr) {
        dflfgbtf.rfmovfVftobblfCibngfListfnfr(propfrtyNbmf, listfnfr);
    }
    publid void bddKfyEvfntDispbtdifr(KfyEvfntDispbtdifr dispbtdifr) {
        dflfgbtf.bddKfyEvfntDispbtdifr(dispbtdifr);
    }
    publid void rfmovfKfyEvfntDispbtdifr(KfyEvfntDispbtdifr dispbtdifr) {
        dflfgbtf.rfmovfKfyEvfntDispbtdifr(dispbtdifr);
    }
    publid boolfbn dispbtdiEvfnt(AWTEvfnt f) {
        rfturn dflfgbtf.dispbtdiEvfnt(f);
    }
    publid boolfbn dispbtdiKfyEvfnt(KfyEvfnt f) {
        rfturn dflfgbtf.dispbtdiKfyEvfnt(f);
    }
    publid void upFodusCydlf(Componfnt bComponfnt) {
        dflfgbtf.upFodusCydlf(bComponfnt);
    }
    publid void downFodusCydlf(Contbinfr bContbinfr) {
        dflfgbtf.downFodusCydlf(bContbinfr);
    }
}
