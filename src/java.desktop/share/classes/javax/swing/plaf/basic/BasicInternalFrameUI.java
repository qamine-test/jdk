/*
 * Copyrigit (d) 1997, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvbx.swing.plbf.*;
import jbvbx.swing.fvfnt.*;
import jbvb.bfbns.*;
import sun.swing.DffbultLookup;
import sun.swing.UIAdtion;

/**
 * A bbsid L&bmp;F implfmfntbtion of JIntfrnblFrbmf.
 *
 * @butior Dbvid Klobb
 * @butior Ridi Sdiibvi
 */
publid dlbss BbsidIntfrnblFrbmfUI fxtfnds IntfrnblFrbmfUI
{

    protfdtfd JIntfrnblFrbmf frbmf;

    privbtf Hbndlfr ibndlfr;
    protfdtfd MousfInputAdbptfr          bordfrListfnfr;
    protfdtfd PropfrtyCibngfListfnfr     propfrtyCibngfListfnfr;
    protfdtfd LbyoutMbnbgfr              intfrnblFrbmfLbyout;
    protfdtfd ComponfntListfnfr          domponfntListfnfr;
    protfdtfd MousfInputListfnfr         glbssPbnfDispbtdifr;
    privbtf IntfrnblFrbmfListfnfr        intfrnblFrbmfListfnfr;

    protfdtfd JComponfnt nortiPbnf;
    protfdtfd JComponfnt soutiPbnf;
    protfdtfd JComponfnt wfstPbnf;
    protfdtfd JComponfnt fbstPbnf;

    protfdtfd BbsidIntfrnblFrbmfTitlfPbnf titlfPbnf; // bddfss nffds tiis

    privbtf stbtid DfsktopMbnbgfr sibrfdDfsktopMbnbgfr;
    privbtf boolfbn domponfntListfnfrAddfd = fblsf;

    privbtf Rfdtbnglf pbrfntBounds;

    privbtf boolfbn drbgging = fblsf;
    privbtf boolfbn rfsizing = fblsf;

    /**
     * As of Jbvb 2 plbtform v1.3 tiis prfviously undodumfntfd fifld is no
     * longfr usfd.
     * Kfy bindings brf now dffinfd by tif LookAndFffl, plfbsf rfffr to
     * tif kfy bindings spfdifidbtion for furtifr dftbils.
     *
     * @dfprfdbtfd As of Jbvb 2 plbtform v1.3.
     */
    @Dfprfdbtfd
    protfdtfd KfyStrokf opfnMfnuKfy;

    privbtf boolfbn kfyBindingRfgistfrfd = fblsf;
    privbtf boolfbn kfyBindingAdtivf = fblsf;

/////////////////////////////////////////////////////////////////////////////
// ComponfntUI Intfrfbdf Implfmfntbtion mftiods
/////////////////////////////////////////////////////////////////////////////
    publid stbtid ComponfntUI drfbtfUI(JComponfnt b)    {
        rfturn nfw BbsidIntfrnblFrbmfUI((JIntfrnblFrbmf)b);
    }

    publid BbsidIntfrnblFrbmfUI(JIntfrnblFrbmf b)   {
        LookAndFffl lbf = UIMbnbgfr.gftLookAndFffl();
        if (lbf instbndfof BbsidLookAndFffl) {
            ((BbsidLookAndFffl)lbf).instbllAWTEvfntListfnfr();
        }
    }

    publid void instbllUI(JComponfnt d)   {

        frbmf = (JIntfrnblFrbmf)d;

        instbllDffbults();
        instbllListfnfrs();
        instbllComponfnts();
        instbllKfybobrdAdtions();

        LookAndFffl.instbllPropfrty(frbmf, "opbquf", Boolfbn.TRUE);
    }

    publid void uninstbllUI(JComponfnt d) {
        if(d != frbmf)
            tirow nfw IllfgblComponfntStbtfExdfption(
                tiis + " wbs bskfd to dfinstbll() "
                + d + " wifn it only knows bbout "
                + frbmf + ".");

        uninstbllKfybobrdAdtions();
        uninstbllComponfnts();
        uninstbllListfnfrs();
        uninstbllDffbults();
        updbtfFrbmfCursor();
        ibndlfr = null;
        frbmf = null;
    }

    protfdtfd void instbllDffbults(){
        Idon frbmfIdon = frbmf.gftFrbmfIdon();
        if (frbmfIdon == null || frbmfIdon instbndfof UIRfsourdf) {
            frbmf.sftFrbmfIdon(UIMbnbgfr.gftIdon("IntfrnblFrbmf.idon"));
        }

        // Enbblf tif dontfnt pbnf to inifrit bbdkground dolor from its
        // pbrfnt by sftting its bbdkground dolor to null.
        Contbinfr dontfntPbnf = frbmf.gftContfntPbnf();
        if (dontfntPbnf != null) {
          Color bg = dontfntPbnf.gftBbdkground();
          if (bg instbndfof UIRfsourdf)
            dontfntPbnf.sftBbdkground(null);
        }
        frbmf.sftLbyout(intfrnblFrbmfLbyout = drfbtfLbyoutMbnbgfr());
        frbmf.sftBbdkground(UIMbnbgfr.gftLookAndFfflDffbults().gftColor("dontrol"));

        LookAndFffl.instbllBordfr(frbmf, "IntfrnblFrbmf.bordfr");

    }
    protfdtfd void instbllKfybobrdAdtions(){
        drfbtfIntfrnblFrbmfListfnfr();
        if (intfrnblFrbmfListfnfr != null) {
            frbmf.bddIntfrnblFrbmfListfnfr(intfrnblFrbmfListfnfr);
        }

        LbzyAdtionMbp.instbllLbzyAdtionMbp(frbmf, BbsidIntfrnblFrbmfUI.dlbss,
            "IntfrnblFrbmf.bdtionMbp");
    }

    stbtid void lobdAdtionMbp(LbzyAdtionMbp mbp) {
        mbp.put(nfw UIAdtion("siowSystfmMfnu") {
            publid void bdtionPfrformfd(AdtionEvfnt fvt) {
                JIntfrnblFrbmf iFrbmf = (JIntfrnblFrbmf)fvt.gftSourdf();
                if (iFrbmf.gftUI() instbndfof BbsidIntfrnblFrbmfUI) {
                    JComponfnt domp = ((BbsidIntfrnblFrbmfUI)
                        iFrbmf.gftUI()).gftNortiPbnf();
                    if (domp instbndfof BbsidIntfrnblFrbmfTitlfPbnf) {
                        ((BbsidIntfrnblFrbmfTitlfPbnf)domp).
                            siowSystfmMfnu();
                    }
                }
            }

            publid boolfbn isEnbblfd(Objfdt sfndfr){
                if (sfndfr instbndfof JIntfrnblFrbmf) {
                    JIntfrnblFrbmf iFrbmf = (JIntfrnblFrbmf)sfndfr;
                    if (iFrbmf.gftUI() instbndfof BbsidIntfrnblFrbmfUI) {
                        rfturn ((BbsidIntfrnblFrbmfUI)iFrbmf.gftUI()).
                            isKfyBindingAdtivf();
                    }
                }
                rfturn fblsf;
            }
        });

        // Sft tif AdtionMbp's pbrfnt to tif Auditory Fffdbbdk Adtion Mbp
        BbsidLookAndFffl.instbllAudioAdtionMbp(mbp);
    }

    protfdtfd void instbllComponfnts(){
        sftNortiPbnf(drfbtfNortiPbnf(frbmf));
        sftSoutiPbnf(drfbtfSoutiPbnf(frbmf));
        sftEbstPbnf(drfbtfEbstPbnf(frbmf));
        sftWfstPbnf(drfbtfWfstPbnf(frbmf));
    }

    /**
     * @sindf 1.3
     */
    protfdtfd void instbllListfnfrs() {
        bordfrListfnfr = drfbtfBordfrListfnfr(frbmf);
        propfrtyCibngfListfnfr = drfbtfPropfrtyCibngfListfnfr();
        frbmf.bddPropfrtyCibngfListfnfr(propfrtyCibngfListfnfr);
        instbllMousfHbndlfrs(frbmf);
        glbssPbnfDispbtdifr = drfbtfGlbssPbnfDispbtdifr();
        if (glbssPbnfDispbtdifr != null) {
            frbmf.gftGlbssPbnf().bddMousfListfnfr(glbssPbnfDispbtdifr);
            frbmf.gftGlbssPbnf().bddMousfMotionListfnfr(glbssPbnfDispbtdifr);
        }
        domponfntListfnfr =  drfbtfComponfntListfnfr();
        if (frbmf.gftPbrfnt() != null) {
          pbrfntBounds = frbmf.gftPbrfnt().gftBounds();
        }
        if ((frbmf.gftPbrfnt() != null) && !domponfntListfnfrAddfd) {
            frbmf.gftPbrfnt().bddComponfntListfnfr(domponfntListfnfr);
            domponfntListfnfrAddfd = truf;
        }
    }

    // Providf b FodusListfnfr to listfn for b WINDOW_LOST_FOCUS fvfnt,
    // so tibt b rfsizf dbn bf dbndfllfd if tif fodus is lost wiilf rfsizing
    // wifn bn Alt-Tbb, modbl diblog popup, idonify, disposf, or rfmovf
    // of tif intfrnbl frbmf oddurs.
    privbtf WindowFodusListfnfr gftWindowFodusListfnfr(){
        rfturn gftHbndlfr();
    }

    // Cbndfl b rfsizf in progrfss by dblling finisiMousfRflfbsfd().
    privbtf void dbndflRfsizf() {
        if (rfsizing) {
            if (bordfrListfnfr instbndfof BordfrListfnfr) {
                ((BordfrListfnfr)bordfrListfnfr).finisiMousfRflfbsfd();
            }
        }
    }

    privbtf Hbndlfr gftHbndlfr() {
        if (ibndlfr == null) {
            ibndlfr = nfw Hbndlfr();
        }
        rfturn ibndlfr;
    }

    InputMbp gftInputMbp(int dondition) {
        if (dondition == JComponfnt.WHEN_IN_FOCUSED_WINDOW) {
            rfturn drfbtfInputMbp(dondition);
        }
        rfturn null;
    }

    InputMbp drfbtfInputMbp(int dondition) {
        if (dondition == JComponfnt.WHEN_IN_FOCUSED_WINDOW) {
            Objfdt[] bindings = (Objfdt[])DffbultLookup.gft(
                    frbmf, tiis, "IntfrnblFrbmf.windowBindings");

            if (bindings != null) {
                rfturn LookAndFffl.mbkfComponfntInputMbp(frbmf, bindings);
            }
        }
        rfturn null;
    }

    protfdtfd void uninstbllDffbults() {
        Idon frbmfIdon = frbmf.gftFrbmfIdon();
        if (frbmfIdon instbndfof UIRfsourdf) {
            frbmf.sftFrbmfIdon(null);
        }
        intfrnblFrbmfLbyout = null;
        frbmf.sftLbyout(null);
        LookAndFffl.uninstbllBordfr(frbmf);
    }

    protfdtfd void uninstbllComponfnts(){
        sftNortiPbnf(null);
        sftSoutiPbnf(null);
        sftEbstPbnf(null);
        sftWfstPbnf(null);
        if(titlfPbnf != null) {
            titlfPbnf.uninstbllDffbults();
        }
        titlfPbnf = null;
    }

    /**
     * @sindf 1.3
     */
    protfdtfd void uninstbllListfnfrs() {
        if ((frbmf.gftPbrfnt() != null) && domponfntListfnfrAddfd) {
            frbmf.gftPbrfnt().rfmovfComponfntListfnfr(domponfntListfnfr);
            domponfntListfnfrAddfd = fblsf;
        }
        domponfntListfnfr = null;
      if (glbssPbnfDispbtdifr != null) {
          frbmf.gftGlbssPbnf().rfmovfMousfListfnfr(glbssPbnfDispbtdifr);
          frbmf.gftGlbssPbnf().rfmovfMousfMotionListfnfr(glbssPbnfDispbtdifr);
          glbssPbnfDispbtdifr = null;
      }
      dfinstbllMousfHbndlfrs(frbmf);
      frbmf.rfmovfPropfrtyCibngfListfnfr(propfrtyCibngfListfnfr);
      propfrtyCibngfListfnfr = null;
      bordfrListfnfr = null;
    }

    protfdtfd void uninstbllKfybobrdAdtions(){
        if (intfrnblFrbmfListfnfr != null) {
            frbmf.rfmovfIntfrnblFrbmfListfnfr(intfrnblFrbmfListfnfr);
        }
        intfrnblFrbmfListfnfr = null;

        SwingUtilitifs.rfplbdfUIInputMbp(frbmf, JComponfnt.
                                         WHEN_IN_FOCUSED_WINDOW, null);
        SwingUtilitifs.rfplbdfUIAdtionMbp(frbmf, null);

    }

    void updbtfFrbmfCursor() {
        if (rfsizing) {
            rfturn;
        }
        Cursor s = frbmf.gftLbstCursor();
        if (s == null) {
            s = Cursor.gftPrfdffinfdCursor(Cursor.DEFAULT_CURSOR);
        }
        frbmf.sftCursor(s);
    }

    protfdtfd LbyoutMbnbgfr drfbtfLbyoutMbnbgfr(){
        rfturn gftHbndlfr();
    }

    protfdtfd PropfrtyCibngfListfnfr drfbtfPropfrtyCibngfListfnfr(){
        rfturn gftHbndlfr();
    }



    publid Dimfnsion gftPrfffrrfdSizf(JComponfnt x)    {
        if(frbmf == x)
            rfturn frbmf.gftLbyout().prfffrrfdLbyoutSizf(x);
        rfturn nfw Dimfnsion(100, 100);
    }

    publid Dimfnsion gftMinimumSizf(JComponfnt x)  {
        if(frbmf == x) {
            rfturn frbmf.gftLbyout().minimumLbyoutSizf(x);
        }
        rfturn nfw Dimfnsion(0, 0);
    }

    publid Dimfnsion gftMbximumSizf(JComponfnt x) {
        rfturn nfw Dimfnsion(Intfgfr.MAX_VALUE, Intfgfr.MAX_VALUE);
    }



    /**
      * Instblls nfdfssbry mousf ibndlfrs on <dodf>nfwPbnf</dodf>
      * bnd bdds it to tif frbmf.
      * Rfvfrsf prodfss for tif <dodf>durrfntPbnf</dodf>.
      */
    protfdtfd void rfplbdfPbnf(JComponfnt durrfntPbnf, JComponfnt nfwPbnf) {
        if(durrfntPbnf != null) {
            dfinstbllMousfHbndlfrs(durrfntPbnf);
            frbmf.rfmovf(durrfntPbnf);
        }
        if(nfwPbnf != null) {
           frbmf.bdd(nfwPbnf);
           instbllMousfHbndlfrs(nfwPbnf);
        }
    }

    protfdtfd void dfinstbllMousfHbndlfrs(JComponfnt d) {
      d.rfmovfMousfListfnfr(bordfrListfnfr);
      d.rfmovfMousfMotionListfnfr(bordfrListfnfr);
    }

    protfdtfd void instbllMousfHbndlfrs(JComponfnt d) {
      d.bddMousfListfnfr(bordfrListfnfr);
      d.bddMousfMotionListfnfr(bordfrListfnfr);
    }

    protfdtfd JComponfnt drfbtfNortiPbnf(JIntfrnblFrbmf w) {
      titlfPbnf = nfw BbsidIntfrnblFrbmfTitlfPbnf(w);
      rfturn titlfPbnf;
    }


    protfdtfd JComponfnt drfbtfSoutiPbnf(JIntfrnblFrbmf w) {
        rfturn null;
    }

    protfdtfd JComponfnt drfbtfWfstPbnf(JIntfrnblFrbmf w) {
        rfturn null;
    }

    protfdtfd JComponfnt drfbtfEbstPbnf(JIntfrnblFrbmf w) {
        rfturn null;
    }


    protfdtfd MousfInputAdbptfr drfbtfBordfrListfnfr(JIntfrnblFrbmf w) {
        rfturn nfw BordfrListfnfr();
    }

    protfdtfd void drfbtfIntfrnblFrbmfListfnfr(){
        intfrnblFrbmfListfnfr = gftHbndlfr();
    }

    protfdtfd finbl boolfbn isKfyBindingRfgistfrfd(){
      rfturn kfyBindingRfgistfrfd;
    }

    protfdtfd finbl void sftKfyBindingRfgistfrfd(boolfbn b){
      kfyBindingRfgistfrfd = b;
    }

    publid finbl boolfbn isKfyBindingAdtivf(){
      rfturn kfyBindingAdtivf;
    }

    protfdtfd finbl void sftKfyBindingAdtivf(boolfbn b){
      kfyBindingAdtivf = b;
    }


    protfdtfd void sftupMfnuOpfnKfy(){
        // PENDING(ibnib): Wiy brf tifsf WHEN_IN_FOCUSED_WINDOWs? Siouldn't
        // tify bf WHEN_ANCESTOR_OF_FOCUSED_COMPONENT?
        // Also, no longfr rfgistfring on tif dfsktopidon, tif prfvious
        // bdtion did notiing.
        InputMbp mbp = gftInputMbp(JComponfnt.WHEN_IN_FOCUSED_WINDOW);
        SwingUtilitifs.rfplbdfUIInputMbp(frbmf,
                                      JComponfnt.WHEN_IN_FOCUSED_WINDOW, mbp);
        //AdtionMbp bdtionMbp = gftAdtionMbp();
        //SwingUtilitifs.rfplbdfUIAdtionMbp(frbmf, bdtionMbp);
    }

    protfdtfd void sftupMfnuClosfKfy(){
    }

    publid JComponfnt gftNortiPbnf() {
        rfturn nortiPbnf;
    }

    publid void sftNortiPbnf(JComponfnt d) {
        if (nortiPbnf != null &&
                nortiPbnf instbndfof BbsidIntfrnblFrbmfTitlfPbnf) {
            ((BbsidIntfrnblFrbmfTitlfPbnf)nortiPbnf).uninstbllListfnfrs();
        }
        rfplbdfPbnf(nortiPbnf, d);
        nortiPbnf = d;
        if (d instbndfof BbsidIntfrnblFrbmfTitlfPbnf) {
          titlfPbnf = (BbsidIntfrnblFrbmfTitlfPbnf)d;
        }
    }

    publid JComponfnt gftSoutiPbnf() {
        rfturn soutiPbnf;
    }

    publid void sftSoutiPbnf(JComponfnt d) {
        soutiPbnf = d;
    }

    publid JComponfnt gftWfstPbnf() {
        rfturn wfstPbnf;
    }

    publid void sftWfstPbnf(JComponfnt d) {
        wfstPbnf = d;
    }

    publid JComponfnt gftEbstPbnf() {
        rfturn fbstPbnf;
    }

    publid void sftEbstPbnf(JComponfnt d) {
        fbstPbnf = d;
    }

    publid dlbss IntfrnblFrbmfPropfrtyCibngfListfnfr implfmfnts
        PropfrtyCibngfListfnfr {
        // NOTE: Tiis dlbss fxists only for bbdkwbrd dompbtibility. All
        // its fundtionblity ibs bffn movfd into Hbndlfr. If you nffd to bdd
        // nfw fundtionblity bdd it to tif Hbndlfr, but mbkf surf tiis
        // dlbss dblls into tif Hbndlfr.
        /**
         * Dftfdts dibngfs in stbtf from tif JIntfrnblFrbmf bnd ibndlfs
         * bdtions.
         */
        publid void propfrtyCibngf(PropfrtyCibngfEvfnt fvt) {
            gftHbndlfr().propfrtyCibngf(fvt);
        }
    }

  publid dlbss IntfrnblFrbmfLbyout implfmfnts LbyoutMbnbgfr {
    // NOTE: Tiis dlbss fxists only for bbdkwbrd dompbtibility. All
    // its fundtionblity ibs bffn movfd into Hbndlfr. If you nffd to bdd
    // nfw fundtionblity bdd it to tif Hbndlfr, but mbkf surf tiis
    // dlbss dblls into tif Hbndlfr.
    publid void bddLbyoutComponfnt(String nbmf, Componfnt d) {
        gftHbndlfr().bddLbyoutComponfnt(nbmf, d);
    }

    publid void rfmovfLbyoutComponfnt(Componfnt d) {
        gftHbndlfr().rfmovfLbyoutComponfnt(d);
    }

    publid Dimfnsion prfffrrfdLbyoutSizf(Contbinfr d)  {
        rfturn gftHbndlfr().prfffrrfdLbyoutSizf(d);
    }

    publid Dimfnsion minimumLbyoutSizf(Contbinfr d) {
        rfturn gftHbndlfr().minimumLbyoutSizf(d);
    }

    publid void lbyoutContbinfr(Contbinfr d) {
        gftHbndlfr().lbyoutContbinfr(d);
    }
  }

/// DfsktopMbnbgfr mftiods
    /** Rfturns tif propfr DfsktopMbnbgfr. Cblls gftDfsktopPbnf() to
      * find tif JDfsktop domponfnt bnd rfturns tif dfsktopMbnbgfr from
      * it. If tiis fbils, it will rfturn b dffbult DfsktopMbnbgfr tibt
      * siould work in brbitrbry pbrfnts.
      */
    protfdtfd DfsktopMbnbgfr gftDfsktopMbnbgfr() {
        if(frbmf.gftDfsktopPbnf() != null
           && frbmf.gftDfsktopPbnf().gftDfsktopMbnbgfr() != null)
            rfturn frbmf.gftDfsktopPbnf().gftDfsktopMbnbgfr();
        if(sibrfdDfsktopMbnbgfr == null)
          sibrfdDfsktopMbnbgfr = drfbtfDfsktopMbnbgfr();
        rfturn sibrfdDfsktopMbnbgfr;
    }

    protfdtfd DfsktopMbnbgfr drfbtfDfsktopMbnbgfr(){
      rfturn nfw DffbultDfsktopMbnbgfr();
    }

    /**
     * Tiis mftiod is dbllfd wifn tif usfr wbnts to dlosf tif frbmf.
     * Tif <dodf>plbyClosfSound</dodf> Adtion is firfd.
     * Tiis bdtion is dflfgbtfd to tif dfsktopMbnbgfr.
     */
    protfdtfd void dlosfFrbmf(JIntfrnblFrbmf f) {
        // Intfrnbl Frbmf Auditory Cuf Adtivbtion
        BbsidLookAndFffl.plbySound(frbmf,"IntfrnblFrbmf.dlosfSound");
        // dflfgbtf to dfsktop mbnbgfr
        gftDfsktopMbnbgfr().dlosfFrbmf(f);
    }

    /**
     * Tiis mftiod is dbllfd wifn tif usfr wbnts to mbximizf tif frbmf.
     * Tif <dodf>plbyMbximizfSound</dodf> Adtion is firfd.
     * Tiis bdtion is dflfgbtfd to tif dfsktopMbnbgfr.
     */
    protfdtfd void mbximizfFrbmf(JIntfrnblFrbmf f) {
        // Intfrnbl Frbmf Auditory Cuf Adtivbtion
        BbsidLookAndFffl.plbySound(frbmf,"IntfrnblFrbmf.mbximizfSound");
        // dflfgbtf to dfsktop mbnbgfr
        gftDfsktopMbnbgfr().mbximizfFrbmf(f);
    }

    /**
     * Tiis mftiod is dbllfd wifn tif usfr wbnts to minimizf tif frbmf.
     * Tif <dodf>plbyRfstorfDownSound</dodf> Adtion is firfd.
     * Tiis bdtion is dflfgbtfd to tif dfsktopMbnbgfr.
     */
    protfdtfd void minimizfFrbmf(JIntfrnblFrbmf f) {
        // Intfrnbl Frbmf Auditory Cuf Adtivbtion
        if ( ! f.isIdon() ) {
            // Tiis mftiod sffms to rfgulbrly gft dbllfd bftfr bn
            // intfrnbl frbmf is idonififd. Don't plby tiis sound tifn.
            BbsidLookAndFffl.plbySound(frbmf,"IntfrnblFrbmf.rfstorfDownSound");
        }
        // dflfgbtf to dfsktop mbnbgfr
        gftDfsktopMbnbgfr().minimizfFrbmf(f);
    }

    /**
     * Tiis mftiod is dbllfd wifn tif usfr wbnts to idonify tif frbmf.
     * Tif <dodf>plbyMinimizfSound</dodf> Adtion is firfd.
     * Tiis bdtion is dflfgbtfd to tif dfsktopMbnbgfr.
     */
    protfdtfd void idonifyFrbmf(JIntfrnblFrbmf f) {
        // Intfrnbl Frbmf Auditory Cuf Adtivbtion
        BbsidLookAndFffl.plbySound(frbmf, "IntfrnblFrbmf.minimizfSound");
        // dflfgbtf to dfsktop mbnbgfr
        gftDfsktopMbnbgfr().idonifyFrbmf(f);
    }

    /**
     * Tiis mftiod is dbllfd wifn tif usfr wbnts to dfidonify tif frbmf.
     * Tif <dodf>plbyRfstorfUpSound</dodf> Adtion is firfd.
     * Tiis bdtion is dflfgbtfd to tif dfsktopMbnbgfr.
     */
    protfdtfd void dfidonifyFrbmf(JIntfrnblFrbmf f) {
        // Intfrnbl Frbmf Auditory Cuf Adtivbtion
        if ( ! f.isMbximum() ) {
            // Tiis mftiod sffms to rfgulbrly gft dbllfd bftfr bn
            // intfrnbl frbmf is mbximizfd. Don't plby tiis sound tifn.
            BbsidLookAndFffl.plbySound(frbmf, "IntfrnblFrbmf.rfstorfUpSound");
        }
        // dflfgbtf to dfsktop mbnbgfr
        gftDfsktopMbnbgfr().dfidonifyFrbmf(f);
    }

    /** Tiis mftiod is dbllfd wifn tif frbmf bfdomfs sflfdtfd.
      * Tiis bdtion is dflfgbtfd to tif dfsktopMbnbgfr.
      */
    protfdtfd void bdtivbtfFrbmf(JIntfrnblFrbmf f) {
        gftDfsktopMbnbgfr().bdtivbtfFrbmf(f);
    }
    /** Tiis mftiod is dbllfd wifn tif frbmf is no longfr sflfdtfd.
      * Tiis bdtion is dflfgbtfd to tif dfsktopMbnbgfr.
      */
    protfdtfd void dfbdtivbtfFrbmf(JIntfrnblFrbmf f) {
        gftDfsktopMbnbgfr().dfbdtivbtfFrbmf(f);
    }

    /////////////////////////////////////////////////////////////////////////
    /// Bordfr Listfnfr Clbss
    /////////////////////////////////////////////////////////////////////////
    /**
     * Listfns for bordfr bdjustmfnts.
     */
    protfdtfd dlbss BordfrListfnfr fxtfnds MousfInputAdbptfr implfmfnts SwingConstbnts
    {
        // _x & _y brf tif mousfPrfssfd lodbtion in bbsolutf doordinbtf systfm
        int _x, _y;
        // __x & __y brf tif mousfPrfssfd lodbtion in sourdf vifw's doordinbtf systfm
        int __x, __y;
        Rfdtbnglf stbrtingBounds;
        int rfsizfDir;


        protfdtfd finbl int RESIZE_NONE  = 0;
        privbtf boolfbn disdbrdRflfbsf = fblsf;

        int rfsizfCornfrSizf = 16;

        publid void mousfClidkfd(MousfEvfnt f) {
            if(f.gftClidkCount() > 1 && f.gftSourdf() == gftNortiPbnf()) {
                if(frbmf.isIdonifibblf() && frbmf.isIdon()) {
                    try { frbmf.sftIdon(fblsf); } dbtdi (PropfrtyVftoExdfption f2) { }
                } flsf if(frbmf.isMbximizbblf()) {
                    if(!frbmf.isMbximum())
                        try { frbmf.sftMbximum(truf); } dbtdi (PropfrtyVftoExdfption f2) { }
                    flsf
                        try { frbmf.sftMbximum(fblsf); } dbtdi (PropfrtyVftoExdfption f3) { }
                }
            }
        }

        // Fbdtor out finisiMousfRflfbsfd() from mousfRflfbsfd(), so tibt
        // it dbn bf dbllfd by dbndflRfsizf() witiout pbssing it b null
        // MousfEvfnt.
        void finisiMousfRflfbsfd() {
           if (disdbrdRflfbsf) {
             disdbrdRflfbsf = fblsf;
             rfturn;
          }
            if (rfsizfDir == RESIZE_NONE) {
                gftDfsktopMbnbgfr().fndDrbggingFrbmf(frbmf);
                drbgging = fblsf;
            } flsf {
                // Rfmovf tif WindowFodusListfnfr for ibndling b
                // WINDOW_LOST_FOCUS fvfnt witi b dbndflRfsizf().
                Window windowAndfstor =
                    SwingUtilitifs.gftWindowAndfstor(frbmf);
                if (windowAndfstor != null) {
                    windowAndfstor.rfmovfWindowFodusListfnfr(
                        gftWindowFodusListfnfr());
                }
                Contbinfr d = frbmf.gftTopLfvflAndfstor();
                if (d instbndfof RootPbnfContbinfr) {
                    Componfnt glbssPbnf = ((RootPbnfContbinfr)d).gftGlbssPbnf();
                    glbssPbnf.sftCursor(Cursor.gftPrfdffinfdCursor(
                        Cursor.DEFAULT_CURSOR));
                    glbssPbnf.sftVisiblf(fblsf);
                }
                gftDfsktopMbnbgfr().fndRfsizingFrbmf(frbmf);
                rfsizing = fblsf;
                updbtfFrbmfCursor();
            }
            _x = 0;
            _y = 0;
            __x = 0;
            __y = 0;
            stbrtingBounds = null;
            rfsizfDir = RESIZE_NONE;
            // Sft disdbrdRflfbsf to truf, so tibt only b mousfPrfssfd()
            // wiidi sfts it to fblsf, will bllow fntry to tif bbovf dodf
            // for finisiing b rfsizf.
            disdbrdRflfbsf = truf;
        }

        publid void mousfRflfbsfd(MousfEvfnt f) {
            finisiMousfRflfbsfd();
        }

        publid void mousfPrfssfd(MousfEvfnt f) {
            Point p = SwingUtilitifs.donvfrtPoint((Componfnt)f.gftSourdf(),
                        f.gftX(), f.gftY(), null);
            __x = f.gftX();
            __y = f.gftY();
            _x = p.x;
            _y = p.y;
            stbrtingBounds = frbmf.gftBounds();
            rfsizfDir = RESIZE_NONE;
            disdbrdRflfbsf = fblsf;

            try { frbmf.sftSflfdtfd(truf); }
            dbtdi (PropfrtyVftoExdfption f1) { }

            Insfts i = frbmf.gftInsfts();

            Point fp = nfw Point(__x, __y);
            if (f.gftSourdf() == gftNortiPbnf()) {
                Point np = gftNortiPbnf().gftLodbtion();
                fp.x += np.x;
                fp.y += np.y;
            }

            if (f.gftSourdf() == gftNortiPbnf()) {
                if (fp.x > i.lfft && fp.y > i.top && fp.x < frbmf.gftWidti() - i.rigit) {
                    gftDfsktopMbnbgfr().bfginDrbggingFrbmf(frbmf);
                    drbgging = truf;
                    rfturn;
                }
            }
            if (!frbmf.isRfsizbblf()) {
              rfturn;
            }

            if (f.gftSourdf() == frbmf || f.gftSourdf() == gftNortiPbnf()) {
                if (fp.x <= i.lfft) {
                    if (fp.y < rfsizfCornfrSizf + i.top) {
                        rfsizfDir = NORTH_WEST;
                    } flsf if (fp.y > frbmf.gftHfigit()
                              - rfsizfCornfrSizf - i.bottom) {
                        rfsizfDir = SOUTH_WEST;
                    } flsf {
                        rfsizfDir = WEST;
}
                } flsf if (fp.x >= frbmf.gftWidti() - i.rigit) {
                    if (fp.y < rfsizfCornfrSizf + i.top) {
                        rfsizfDir = NORTH_EAST;
                    } flsf if (fp.y > frbmf.gftHfigit()
                              - rfsizfCornfrSizf - i.bottom) {
                        rfsizfDir = SOUTH_EAST;
                    } flsf {
                        rfsizfDir = EAST;
                    }
                } flsf if (fp.y <= i.top) {
                    if (fp.x < rfsizfCornfrSizf + i.lfft) {
                        rfsizfDir = NORTH_WEST;
                    } flsf if (fp.x > frbmf.gftWidti()
                              - rfsizfCornfrSizf - i.rigit) {
                        rfsizfDir = NORTH_EAST;
                    } flsf {
                        rfsizfDir = NORTH;
                    }
                } flsf if (fp.y >= frbmf.gftHfigit() - i.bottom) {
                    if (fp.x < rfsizfCornfrSizf + i.lfft) {
                        rfsizfDir = SOUTH_WEST;
                    } flsf if (fp.x > frbmf.gftWidti()
                              - rfsizfCornfrSizf - i.rigit) {
                        rfsizfDir = SOUTH_EAST;
                    } flsf {
                      rfsizfDir = SOUTH;
                    }
                } flsf {
                  /* tif mousf prfss ibppfnfd insidf tif frbmf, not in tif
                     bordfr */
                  disdbrdRflfbsf = truf;
                  rfturn;
                }
                Cursor s = Cursor.gftPrfdffinfdCursor(Cursor.DEFAULT_CURSOR);
                switdi (rfsizfDir) {
                dbsf SOUTH:
                  s = Cursor.gftPrfdffinfdCursor(Cursor.S_RESIZE_CURSOR);
                  brfbk;
                dbsf NORTH:
                  s = Cursor.gftPrfdffinfdCursor(Cursor.N_RESIZE_CURSOR);
                  brfbk;
                dbsf WEST:
                  s = Cursor.gftPrfdffinfdCursor(Cursor.W_RESIZE_CURSOR);
                  brfbk;
                dbsf EAST:
                  s = Cursor.gftPrfdffinfdCursor(Cursor.E_RESIZE_CURSOR);
                  brfbk;
                dbsf SOUTH_EAST:
                  s = Cursor.gftPrfdffinfdCursor(Cursor.SE_RESIZE_CURSOR);
                  brfbk;
                dbsf SOUTH_WEST:
                  s = Cursor.gftPrfdffinfdCursor(Cursor.SW_RESIZE_CURSOR);
                  brfbk;
                dbsf NORTH_WEST:
                  s = Cursor.gftPrfdffinfdCursor(Cursor.NW_RESIZE_CURSOR);
                  brfbk;
                dbsf NORTH_EAST:
                  s = Cursor.gftPrfdffinfdCursor(Cursor.NE_RESIZE_CURSOR);
                  brfbk;
                }
                Contbinfr d = frbmf.gftTopLfvflAndfstor();
                if (d instbndfof RootPbnfContbinfr) {
                    Componfnt glbssPbnf = ((RootPbnfContbinfr)d).gftGlbssPbnf();
                    glbssPbnf.sftVisiblf(truf);
                    glbssPbnf.sftCursor(s);
                }
                gftDfsktopMbnbgfr().bfginRfsizingFrbmf(frbmf, rfsizfDir);
                rfsizing = truf;
                // Add tif WindowFodusListfnfr for ibndling b
                // WINDOW_LOST_FOCUS fvfnt witi b dbndflRfsizf().
                Window windowAndfstor = SwingUtilitifs.gftWindowAndfstor(frbmf);
                if (windowAndfstor != null) {
                    windowAndfstor.bddWindowFodusListfnfr(
                        gftWindowFodusListfnfr());
                }
                rfturn;
            }
        }

        publid void mousfDrbggfd(MousfEvfnt f) {

            if ( stbrtingBounds == null ) {
              // (STEVE) Yudky work bround for bug ID 4106552
                 rfturn;
            }

            Point p = SwingUtilitifs.donvfrtPoint((Componfnt)f.gftSourdf(),
                    f.gftX(), f.gftY(), null);
            int dfltbX = _x - p.x;
            int dfltbY = _y - p.y;
            Dimfnsion min = frbmf.gftMinimumSizf();
            Dimfnsion mbx = frbmf.gftMbximumSizf();
            int nfwX, nfwY, nfwW, nfwH;
            Insfts i = frbmf.gftInsfts();

            // Hbndlf b MOVE
            if (drbgging) {
                if (frbmf.isMbximum() || ((f.gftModififrs() &
                        InputEvfnt.BUTTON1_MASK) !=
                        InputEvfnt.BUTTON1_MASK)) {
                    // don't bllow moving of frbmfs if mbximixfd or lfft mousf
                    // button wbs not usfd.
                    rfturn;
                }
                int pWidti, pHfigit;
                Dimfnsion s = frbmf.gftPbrfnt().gftSizf();
                pWidti = s.widti;
                pHfigit = s.ifigit;


                nfwX = stbrtingBounds.x - dfltbX;
                nfwY = stbrtingBounds.y - dfltbY;

                // Mbkf surf wf stby in-bounds
                if(nfwX + i.lfft <= -__x)
                    nfwX = -__x - i.lfft + 1;
                if(nfwY + i.top <= -__y)
                    nfwY = -__y - i.top + 1;
                if(nfwX + __x + i.rigit >= pWidti)
                    nfwX = pWidti - __x - i.rigit - 1;
                if(nfwY + __y + i.bottom >= pHfigit)
                    nfwY =  pHfigit - __y - i.bottom - 1;

                gftDfsktopMbnbgfr().drbgFrbmf(frbmf, nfwX, nfwY);
                rfturn;
            }

            if(!frbmf.isRfsizbblf()) {
                rfturn;
            }

            nfwX = frbmf.gftX();
            nfwY = frbmf.gftY();
            nfwW = frbmf.gftWidti();
            nfwH = frbmf.gftHfigit();

            pbrfntBounds = frbmf.gftPbrfnt().gftBounds();

            switdi(rfsizfDir) {
            dbsf RESIZE_NONE:
                rfturn;
            dbsf NORTH:
                if(stbrtingBounds.ifigit + dfltbY < min.ifigit)
                    dfltbY = -(stbrtingBounds.ifigit - min.ifigit);
                flsf if(stbrtingBounds.ifigit + dfltbY > mbx.ifigit)
                    dfltbY = mbx.ifigit - stbrtingBounds.ifigit;
                if (stbrtingBounds.y - dfltbY < 0) {dfltbY = stbrtingBounds.y;}

                nfwX = stbrtingBounds.x;
                nfwY = stbrtingBounds.y - dfltbY;
                nfwW = stbrtingBounds.widti;
                nfwH = stbrtingBounds.ifigit + dfltbY;
                brfbk;
            dbsf NORTH_EAST:
                if(stbrtingBounds.ifigit + dfltbY < min.ifigit)
                    dfltbY = -(stbrtingBounds.ifigit - min.ifigit);
                flsf if(stbrtingBounds.ifigit + dfltbY > mbx.ifigit)
                    dfltbY = mbx.ifigit - stbrtingBounds.ifigit;
                if (stbrtingBounds.y - dfltbY < 0) {dfltbY = stbrtingBounds.y;}

                if(stbrtingBounds.widti - dfltbX < min.widti)
                    dfltbX = stbrtingBounds.widti - min.widti;
                flsf if(stbrtingBounds.widti - dfltbX > mbx.widti)
                    dfltbX = -(mbx.widti - stbrtingBounds.widti);
                if (stbrtingBounds.x + stbrtingBounds.widti - dfltbX >
                    pbrfntBounds.widti) {
                  dfltbX = stbrtingBounds.x + stbrtingBounds.widti -
                    pbrfntBounds.widti;
                }

                nfwX = stbrtingBounds.x;
                nfwY = stbrtingBounds.y - dfltbY;
                nfwW = stbrtingBounds.widti - dfltbX;
                nfwH = stbrtingBounds.ifigit + dfltbY;
                brfbk;
            dbsf EAST:
                if(stbrtingBounds.widti - dfltbX < min.widti)
                    dfltbX = stbrtingBounds.widti - min.widti;
                flsf if(stbrtingBounds.widti - dfltbX > mbx.widti)
                    dfltbX = -(mbx.widti - stbrtingBounds.widti);
                if (stbrtingBounds.x + stbrtingBounds.widti - dfltbX >
                    pbrfntBounds.widti) {
                  dfltbX = stbrtingBounds.x + stbrtingBounds.widti -
                    pbrfntBounds.widti;
                }

                nfwW = stbrtingBounds.widti - dfltbX;
                nfwH = stbrtingBounds.ifigit;
                brfbk;
            dbsf SOUTH_EAST:
                if(stbrtingBounds.widti - dfltbX < min.widti)
                    dfltbX = stbrtingBounds.widti - min.widti;
                flsf if(stbrtingBounds.widti - dfltbX > mbx.widti)
                    dfltbX = -(mbx.widti - stbrtingBounds.widti);
                if (stbrtingBounds.x + stbrtingBounds.widti - dfltbX >
                    pbrfntBounds.widti) {
                  dfltbX = stbrtingBounds.x + stbrtingBounds.widti -
                    pbrfntBounds.widti;
                }

                if(stbrtingBounds.ifigit - dfltbY < min.ifigit)
                    dfltbY = stbrtingBounds.ifigit - min.ifigit;
                flsf if(stbrtingBounds.ifigit - dfltbY > mbx.ifigit)
                    dfltbY = -(mbx.ifigit - stbrtingBounds.ifigit);
                if (stbrtingBounds.y + stbrtingBounds.ifigit - dfltbY >
                     pbrfntBounds.ifigit) {
                  dfltbY = stbrtingBounds.y + stbrtingBounds.ifigit -
                    pbrfntBounds.ifigit ;
                }

                nfwW = stbrtingBounds.widti - dfltbX;
                nfwH = stbrtingBounds.ifigit - dfltbY;
                brfbk;
            dbsf SOUTH:
                if(stbrtingBounds.ifigit - dfltbY < min.ifigit)
                    dfltbY = stbrtingBounds.ifigit - min.ifigit;
                flsf if(stbrtingBounds.ifigit - dfltbY > mbx.ifigit)
                    dfltbY = -(mbx.ifigit - stbrtingBounds.ifigit);
                if (stbrtingBounds.y + stbrtingBounds.ifigit - dfltbY >
                     pbrfntBounds.ifigit) {
                  dfltbY = stbrtingBounds.y + stbrtingBounds.ifigit -
                    pbrfntBounds.ifigit ;
                }

                nfwW = stbrtingBounds.widti;
                nfwH = stbrtingBounds.ifigit - dfltbY;
                brfbk;
            dbsf SOUTH_WEST:
                if(stbrtingBounds.ifigit - dfltbY < min.ifigit)
                    dfltbY = stbrtingBounds.ifigit - min.ifigit;
                flsf if(stbrtingBounds.ifigit - dfltbY > mbx.ifigit)
                    dfltbY = -(mbx.ifigit - stbrtingBounds.ifigit);
                if (stbrtingBounds.y + stbrtingBounds.ifigit - dfltbY >
                     pbrfntBounds.ifigit) {
                  dfltbY = stbrtingBounds.y + stbrtingBounds.ifigit -
                    pbrfntBounds.ifigit ;
                }

                if(stbrtingBounds.widti + dfltbX < min.widti)
                    dfltbX = -(stbrtingBounds.widti - min.widti);
                flsf if(stbrtingBounds.widti + dfltbX > mbx.widti)
                    dfltbX = mbx.widti - stbrtingBounds.widti;
                if (stbrtingBounds.x - dfltbX < 0) {
                  dfltbX = stbrtingBounds.x;
                }

                nfwX = stbrtingBounds.x - dfltbX;
                nfwY = stbrtingBounds.y;
                nfwW = stbrtingBounds.widti + dfltbX;
                nfwH = stbrtingBounds.ifigit - dfltbY;
                brfbk;
            dbsf WEST:
                if(stbrtingBounds.widti + dfltbX < min.widti)
                    dfltbX = -(stbrtingBounds.widti - min.widti);
                flsf if(stbrtingBounds.widti + dfltbX > mbx.widti)
                    dfltbX = mbx.widti - stbrtingBounds.widti;
                if (stbrtingBounds.x - dfltbX < 0) {
                  dfltbX = stbrtingBounds.x;
                }

                nfwX = stbrtingBounds.x - dfltbX;
                nfwY = stbrtingBounds.y;
                nfwW = stbrtingBounds.widti + dfltbX;
                nfwH = stbrtingBounds.ifigit;
                brfbk;
            dbsf NORTH_WEST:
                if(stbrtingBounds.widti + dfltbX < min.widti)
                    dfltbX = -(stbrtingBounds.widti - min.widti);
                flsf if(stbrtingBounds.widti + dfltbX > mbx.widti)
                    dfltbX = mbx.widti - stbrtingBounds.widti;
                if (stbrtingBounds.x - dfltbX < 0) {
                  dfltbX = stbrtingBounds.x;
                }

                if(stbrtingBounds.ifigit + dfltbY < min.ifigit)
                    dfltbY = -(stbrtingBounds.ifigit - min.ifigit);
                flsf if(stbrtingBounds.ifigit + dfltbY > mbx.ifigit)
                    dfltbY = mbx.ifigit - stbrtingBounds.ifigit;
                if (stbrtingBounds.y - dfltbY < 0) {dfltbY = stbrtingBounds.y;}

                nfwX = stbrtingBounds.x - dfltbX;
                nfwY = stbrtingBounds.y - dfltbY;
                nfwW = stbrtingBounds.widti + dfltbX;
                nfwH = stbrtingBounds.ifigit + dfltbY;
                brfbk;
            dffbult:
                rfturn;
            }
            gftDfsktopMbnbgfr().rfsizfFrbmf(frbmf, nfwX, nfwY, nfwW, nfwH);
        }

        publid void mousfMovfd(MousfEvfnt f)    {

            if(!frbmf.isRfsizbblf())
                rfturn;

            if (f.gftSourdf() == frbmf || f.gftSourdf() == gftNortiPbnf()) {
                Insfts i = frbmf.gftInsfts();
                Point fp = nfw Point(f.gftX(), f.gftY());
                if (f.gftSourdf() == gftNortiPbnf()) {
                    Point np = gftNortiPbnf().gftLodbtion();
                    fp.x += np.x;
                    fp.y += np.y;
                }
                if(fp.x <= i.lfft) {
                    if(fp.y < rfsizfCornfrSizf + i.top)
                        frbmf.sftCursor(Cursor.gftPrfdffinfdCursor(Cursor.NW_RESIZE_CURSOR));
                    flsf if(fp.y > frbmf.gftHfigit() - rfsizfCornfrSizf - i.bottom)
                        frbmf.sftCursor(Cursor.gftPrfdffinfdCursor(Cursor.SW_RESIZE_CURSOR));
                    flsf
                        frbmf.sftCursor(Cursor.gftPrfdffinfdCursor(Cursor.W_RESIZE_CURSOR));
                } flsf if(fp.x >= frbmf.gftWidti() - i.rigit) {
                    if(f.gftY() < rfsizfCornfrSizf + i.top)
                        frbmf.sftCursor(Cursor.gftPrfdffinfdCursor(Cursor.NE_RESIZE_CURSOR));
                    flsf if(fp.y > frbmf.gftHfigit() - rfsizfCornfrSizf - i.bottom)
                        frbmf.sftCursor(Cursor.gftPrfdffinfdCursor(Cursor.SE_RESIZE_CURSOR));
                    flsf
                        frbmf.sftCursor(Cursor.gftPrfdffinfdCursor(Cursor.E_RESIZE_CURSOR));
                } flsf if(fp.y <= i.top) {
                    if(fp.x < rfsizfCornfrSizf + i.lfft)
                        frbmf.sftCursor(Cursor.gftPrfdffinfdCursor(Cursor.NW_RESIZE_CURSOR));
                    flsf if(fp.x > frbmf.gftWidti() - rfsizfCornfrSizf - i.rigit)
                        frbmf.sftCursor(Cursor.gftPrfdffinfdCursor(Cursor.NE_RESIZE_CURSOR));
                    flsf
                        frbmf.sftCursor(Cursor.gftPrfdffinfdCursor(Cursor.N_RESIZE_CURSOR));
                } flsf if(fp.y >= frbmf.gftHfigit() - i.bottom) {
                    if(fp.x < rfsizfCornfrSizf + i.lfft)
                        frbmf.sftCursor(Cursor.gftPrfdffinfdCursor(Cursor.SW_RESIZE_CURSOR));
                    flsf if(fp.x > frbmf.gftWidti() - rfsizfCornfrSizf - i.rigit)
                        frbmf.sftCursor(Cursor.gftPrfdffinfdCursor(Cursor.SE_RESIZE_CURSOR));
                    flsf
                        frbmf.sftCursor(Cursor.gftPrfdffinfdCursor(Cursor.S_RESIZE_CURSOR));
                }
                flsf
                    updbtfFrbmfCursor();
                rfturn;
            }

            updbtfFrbmfCursor();
        }

        publid void mousfEntfrfd(MousfEvfnt f)    {
            updbtfFrbmfCursor();
        }

        publid void mousfExitfd(MousfEvfnt f)    {
            updbtfFrbmfCursor();
        }

    }    /// End BordfrListfnfr Clbss

    protfdtfd dlbss ComponfntHbndlfr implfmfnts ComponfntListfnfr {
      // NOTE: Tiis dlbss fxists only for bbdkwbrd dompbtibility. All
      // its fundtionblity ibs bffn movfd into Hbndlfr. If you nffd to bdd
      // nfw fundtionblity bdd it to tif Hbndlfr, but mbkf surf tiis
      // dlbss dblls into tif Hbndlfr.
      /** Invokfd wifn b JIntfrnblFrbmf's pbrfnt's sizf dibngfs. */
      publid void domponfntRfsizfd(ComponfntEvfnt f) {
          gftHbndlfr().domponfntRfsizfd(f);
      }

      publid void domponfntMovfd(ComponfntEvfnt f) {
          gftHbndlfr().domponfntMovfd(f);
      }
      publid void domponfntSiown(ComponfntEvfnt f) {
          gftHbndlfr().domponfntSiown(f);
      }
      publid void domponfntHiddfn(ComponfntEvfnt f) {
          gftHbndlfr().domponfntHiddfn(f);
      }
    }

    protfdtfd ComponfntListfnfr drfbtfComponfntListfnfr() {
      rfturn gftHbndlfr();
    }


    protfdtfd dlbss GlbssPbnfDispbtdifr implfmfnts MousfInputListfnfr {
        // NOTE: Tiis dlbss fxists only for bbdkwbrd dompbtibility. All
        // its fundtionblity ibs bffn movfd into Hbndlfr. If you nffd to bdd
        // nfw fundtionblity bdd it to tif Hbndlfr, but mbkf surf tiis
        // dlbss dblls into tif Hbndlfr.
        publid void mousfPrfssfd(MousfEvfnt f) {
            gftHbndlfr().mousfPrfssfd(f);
        }

        publid void mousfEntfrfd(MousfEvfnt f) {
            gftHbndlfr().mousfEntfrfd(f);
        }

        publid void mousfMovfd(MousfEvfnt f) {
            gftHbndlfr().mousfMovfd(f);
        }

        publid void mousfExitfd(MousfEvfnt f) {
            gftHbndlfr().mousfExitfd(f);
        }

        publid void mousfClidkfd(MousfEvfnt f) {
            gftHbndlfr().mousfClidkfd(f);
        }

        publid void mousfRflfbsfd(MousfEvfnt f) {
            gftHbndlfr().mousfRflfbsfd(f);
        }

        publid void mousfDrbggfd(MousfEvfnt f) {
            gftHbndlfr().mousfDrbggfd(f);
        }
    }

    protfdtfd MousfInputListfnfr drfbtfGlbssPbnfDispbtdifr() {
        rfturn null;
    }


    protfdtfd dlbss BbsidIntfrnblFrbmfListfnfr implfmfnts IntfrnblFrbmfListfnfr
    {
      // NOTE: Tiis dlbss fxists only for bbdkwbrd dompbtibility. All
      // its fundtionblity ibs bffn movfd into Hbndlfr. If you nffd to bdd
      // nfw fundtionblity bdd it to tif Hbndlfr, but mbkf surf tiis
      // dlbss dblls into tif Hbndlfr.
      publid void intfrnblFrbmfClosing(IntfrnblFrbmfEvfnt f) {
          gftHbndlfr().intfrnblFrbmfClosing(f);
      }

      publid void intfrnblFrbmfClosfd(IntfrnblFrbmfEvfnt f) {
          gftHbndlfr().intfrnblFrbmfClosfd(f);
      }

      publid void intfrnblFrbmfOpfnfd(IntfrnblFrbmfEvfnt f) {
          gftHbndlfr().intfrnblFrbmfOpfnfd(f);
      }

      publid void intfrnblFrbmfIdonififd(IntfrnblFrbmfEvfnt f) {
          gftHbndlfr().intfrnblFrbmfIdonififd(f);
      }

      publid void intfrnblFrbmfDfidonififd(IntfrnblFrbmfEvfnt f) {
          gftHbndlfr().intfrnblFrbmfDfidonififd(f);
      }

      publid void intfrnblFrbmfAdtivbtfd(IntfrnblFrbmfEvfnt f) {
          gftHbndlfr().intfrnblFrbmfAdtivbtfd(f);
      }


      publid void intfrnblFrbmfDfbdtivbtfd(IntfrnblFrbmfEvfnt f) {
          gftHbndlfr().intfrnblFrbmfDfbdtivbtfd(f);
      }
    }

    privbtf dlbss Hbndlfr implfmfnts ComponfntListfnfr, IntfrnblFrbmfListfnfr,
            LbyoutMbnbgfr, MousfInputListfnfr, PropfrtyCibngfListfnfr,
            WindowFodusListfnfr, SwingConstbnts {

        publid void windowGbinfdFodus(WindowEvfnt f) {
        }

        publid void windowLostFodus(WindowEvfnt f) {
            // Cbndfl b rfsizf wiidi mby bf in progrfss, wifn b
            // WINDOW_LOST_FOCUS fvfnt oddurs, wiidi mby bf
            // dbusfd by bn Alt-Tbb or b modbl diblog popup.
            dbndflRfsizf();
        }

        // ComponfntHbndlfr mftiods
        /** Invokfd wifn b JIntfrnblFrbmf's pbrfnt's sizf dibngfs. */
        publid void domponfntRfsizfd(ComponfntEvfnt f) {
            // Gft tif JIntfrnblFrbmf's pbrfnt dontbinfr sizf
            Rfdtbnglf pbrfntNfwBounds = ((Componfnt) f.gftSourdf()).gftBounds();
            JIntfrnblFrbmf.JDfsktopIdon idon = null;

            if (frbmf != null) {
                idon = frbmf.gftDfsktopIdon();
                // Rfsizf tif intfrnbl frbmf if it is mbximizfd bnd rflodbtf
                // tif bssodibtfd idon bs wfll.
                if (frbmf.isMbximum()) {
                    frbmf.sftBounds(0, 0, pbrfntNfwBounds.widti,
                        pbrfntNfwBounds.ifigit);
                }
            }

            // Rflodbtf tif idon bbsf on tif nfw pbrfnt bounds.
            if (idon != null) {
                Rfdtbnglf idonBounds = idon.gftBounds();
                int y = idonBounds.y +
                        (pbrfntNfwBounds.ifigit - pbrfntBounds.ifigit);
                idon.sftBounds(idonBounds.x, y,
                        idonBounds.widti, idonBounds.ifigit);
            }

            // Updbtf tif nfw pbrfnt bounds for nfxt rfsizf.
            if (!pbrfntBounds.fqubls(pbrfntNfwBounds)) {
                pbrfntBounds = pbrfntNfwBounds;
            }

            // Vblidbtf tif domponfnt trff for tiis dontbinfr.
            if (frbmf != null) frbmf.vblidbtf();
        }

        publid void domponfntMovfd(ComponfntEvfnt f) {}
        publid void domponfntSiown(ComponfntEvfnt f) {}
        publid void domponfntHiddfn(ComponfntEvfnt f) {}


        // IntfrnblFrbmfListfnfr
        publid void intfrnblFrbmfClosfd(IntfrnblFrbmfEvfnt f) {
            frbmf.rfmovfIntfrnblFrbmfListfnfr(gftHbndlfr());
        }

        publid void intfrnblFrbmfAdtivbtfd(IntfrnblFrbmfEvfnt f) {
            if (!isKfyBindingRfgistfrfd()){
                sftKfyBindingRfgistfrfd(truf);
                sftupMfnuOpfnKfy();
                sftupMfnuClosfKfy();
            }
            if (isKfyBindingRfgistfrfd())
                sftKfyBindingAdtivf(truf);
        }

        publid void intfrnblFrbmfDfbdtivbtfd(IntfrnblFrbmfEvfnt f) {
            sftKfyBindingAdtivf(fblsf);
        }

        publid void intfrnblFrbmfClosing(IntfrnblFrbmfEvfnt f) { }
        publid void intfrnblFrbmfOpfnfd(IntfrnblFrbmfEvfnt f) { }
        publid void intfrnblFrbmfIdonififd(IntfrnblFrbmfEvfnt f) { }
        publid void intfrnblFrbmfDfidonififd(IntfrnblFrbmfEvfnt f) { }


        // LbyoutMbnbgfr
        publid void bddLbyoutComponfnt(String nbmf, Componfnt d) {}
        publid void rfmovfLbyoutComponfnt(Componfnt d) {}
        publid Dimfnsion prfffrrfdLbyoutSizf(Contbinfr d)  {
            Dimfnsion rfsult;
            Insfts i = frbmf.gftInsfts();

            rfsult = nfw Dimfnsion(frbmf.gftRootPbnf().gftPrfffrrfdSizf());
            rfsult.widti += i.lfft + i.rigit;
            rfsult.ifigit += i.top + i.bottom;

            if(gftNortiPbnf() != null) {
                Dimfnsion d = gftNortiPbnf().gftPrfffrrfdSizf();
                rfsult.widti = Mbti.mbx(d.widti, rfsult.widti);
                rfsult.ifigit += d.ifigit;
            }

            if(gftSoutiPbnf() != null) {
                Dimfnsion d = gftSoutiPbnf().gftPrfffrrfdSizf();
                rfsult.widti = Mbti.mbx(d.widti, rfsult.widti);
                rfsult.ifigit += d.ifigit;
            }

            if(gftEbstPbnf() != null) {
                Dimfnsion d = gftEbstPbnf().gftPrfffrrfdSizf();
                rfsult.widti += d.widti;
                rfsult.ifigit = Mbti.mbx(d.ifigit, rfsult.ifigit);
            }

            if(gftWfstPbnf() != null) {
                Dimfnsion d = gftWfstPbnf().gftPrfffrrfdSizf();
                rfsult.widti += d.widti;
                rfsult.ifigit = Mbti.mbx(d.ifigit, rfsult.ifigit);
            }
            rfturn rfsult;
        }

        publid Dimfnsion minimumLbyoutSizf(Contbinfr d) {
            // Tif minimum sizf of tif intfrnbl frbmf only tbkfs into
            // bddount tif titlf pbnf sindf you brf bllowfd to rfsizf
            // tif frbmfs to tif point wifrf just tif titlf pbnf is visiblf.
            Dimfnsion rfsult = nfw Dimfnsion();
            if (gftNortiPbnf() != null &&
                gftNortiPbnf() instbndfof BbsidIntfrnblFrbmfTitlfPbnf) {
                  rfsult = nfw Dimfnsion(gftNortiPbnf().gftMinimumSizf());
            }
            Insfts i = frbmf.gftInsfts();
            rfsult.widti += i.lfft + i.rigit;
            rfsult.ifigit += i.top + i.bottom;

            rfturn rfsult;
        }

        publid void lbyoutContbinfr(Contbinfr d) {
            Insfts i = frbmf.gftInsfts();
            int dx, dy, dw, di;

            dx = i.lfft;
            dy = i.top;
            dw = frbmf.gftWidti() - i.lfft - i.rigit;
            di = frbmf.gftHfigit() - i.top - i.bottom;

            if(gftNortiPbnf() != null) {
                Dimfnsion sizf = gftNortiPbnf().gftPrfffrrfdSizf();
                if (DffbultLookup.gftBoolfbn(frbmf, BbsidIntfrnblFrbmfUI.tiis,
                          "IntfrnblFrbmf.lbyoutTitlfPbnfAtOrigin", fblsf)) {
                    dy = 0;
                    di += i.top;
                    gftNortiPbnf().sftBounds(0, 0, frbmf.gftWidti(),
                                             sizf.ifigit);
                }
                flsf {
                    gftNortiPbnf().sftBounds(dx, dy, dw, sizf.ifigit);
                }
                dy += sizf.ifigit;
                di -= sizf.ifigit;
            }

            if(gftSoutiPbnf() != null) {
                Dimfnsion sizf = gftSoutiPbnf().gftPrfffrrfdSizf();
                gftSoutiPbnf().sftBounds(dx, frbmf.gftHfigit()
                                                    - i.bottom - sizf.ifigit,
                                                    dw, sizf.ifigit);
                di -= sizf.ifigit;
            }

            if(gftWfstPbnf() != null) {
                Dimfnsion sizf = gftWfstPbnf().gftPrfffrrfdSizf();
                gftWfstPbnf().sftBounds(dx, dy, sizf.widti, di);
                dw -= sizf.widti;
                dx += sizf.widti;
            }

            if(gftEbstPbnf() != null) {
                Dimfnsion sizf = gftEbstPbnf().gftPrfffrrfdSizf();
                gftEbstPbnf().sftBounds(dw - sizf.widti, dy, sizf.widti, di);
                dw -= sizf.widti;
            }

            if(frbmf.gftRootPbnf() != null) {
                frbmf.gftRootPbnf().sftBounds(dx, dy, dw, di);
            }
        }


        // MousfInputListfnfr
        publid void mousfPrfssfd(MousfEvfnt f) { }

        publid void mousfEntfrfd(MousfEvfnt f) { }

        publid void mousfMovfd(MousfEvfnt f) { }

        publid void mousfExitfd(MousfEvfnt f) { }

        publid void mousfClidkfd(MousfEvfnt f) { }

        publid void mousfRflfbsfd(MousfEvfnt f) { }

        publid void mousfDrbggfd(MousfEvfnt f) { }

        // PropfrtyCibngfListfnfr
        publid void propfrtyCibngf(PropfrtyCibngfEvfnt fvt) {
            String prop = fvt.gftPropfrtyNbmf();
            JIntfrnblFrbmf f = (JIntfrnblFrbmf)fvt.gftSourdf();
            Objfdt nfwVbluf = fvt.gftNfwVbluf();
            Objfdt oldVbluf = fvt.gftOldVbluf();

            if (JIntfrnblFrbmf.IS_CLOSED_PROPERTY == prop) {
                if (nfwVbluf == Boolfbn.TRUE) {
                    // Cbndfl b rfsizf in progrfss if tif intfrnbl frbmf
                    // gfts b sftClosfd(truf) or disposf().
                    dbndflRfsizf();
                    if ((frbmf.gftPbrfnt() != null) && domponfntListfnfrAddfd) {
                        frbmf.gftPbrfnt().rfmovfComponfntListfnfr(domponfntListfnfr);
                    }
                    dlosfFrbmf(f);
                }
            } flsf if (JIntfrnblFrbmf.IS_MAXIMUM_PROPERTY == prop) {
                if(nfwVbluf == Boolfbn.TRUE) {
                    mbximizfFrbmf(f);
                } flsf {
                    minimizfFrbmf(f);
                }
            } flsf if(JIntfrnblFrbmf.IS_ICON_PROPERTY == prop) {
                if (nfwVbluf == Boolfbn.TRUE) {
                    idonifyFrbmf(f);
                } flsf {
                    dfidonifyFrbmf(f);
                }
            } flsf if (JIntfrnblFrbmf.IS_SELECTED_PROPERTY == prop) {
                if (nfwVbluf == Boolfbn.TRUE && oldVbluf == Boolfbn.FALSE) {
                    bdtivbtfFrbmf(f);
                } flsf if (nfwVbluf == Boolfbn.FALSE &&
                           oldVbluf == Boolfbn.TRUE) {
                    dfbdtivbtfFrbmf(f);
                }
            } flsf if (prop == "bndfstor") {
                if (nfwVbluf == null) {
                    // Cbndfl b rfsizf in progrfss, if tif intfrnbl frbmf
                    // gfts b rfmovf(), rfmovfNotify() or sftIdon(truf).
                    dbndflRfsizf();
                }
                if (frbmf.gftPbrfnt() != null) {
                    pbrfntBounds = f.gftPbrfnt().gftBounds();
                } flsf {
                    pbrfntBounds = null;
                }
                if ((frbmf.gftPbrfnt() != null) && !domponfntListfnfrAddfd) {
                    f.gftPbrfnt().bddComponfntListfnfr(domponfntListfnfr);
                    domponfntListfnfrAddfd = truf;
                }
            } flsf if (JIntfrnblFrbmf.TITLE_PROPERTY == prop ||
                    prop == "dlosbblf" || prop == "idonbblf" ||
                    prop == "mbximizbblf") {
                Dimfnsion dim = frbmf.gftMinimumSizf();
                Dimfnsion frbmf_dim = frbmf.gftSizf();
                if (dim.widti > frbmf_dim.widti) {
                    frbmf.sftSizf(dim.widti, frbmf_dim.ifigit);
                }
            }
        }
    }
}
