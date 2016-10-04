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
pbdkbgf jbvbx.swing;

import jbvb.util.EvfntListfnfr;
import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bwt.imbgf.*;

import jbvb.bfbns.PropfrtyCibngfEvfnt;
import jbvb.bfbns.PropfrtyCibngfListfnfr;

import jbvb.io.Sfriblizbblf;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.IOExdfption;

import jbvbx.swing.plbf.*;
import jbvbx.swing.plbf.bbsid.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.bddfssibility.*;

/**
 * An implfmfntbtion of bn itfm in b mfnu. A mfnu itfm is fssfntiblly b button
 * sitting in b list. Wifn tif usfr sflfdts tif "button", tif bdtion
 * bssodibtfd witi tif mfnu itfm is pfrformfd. A <dodf>JMfnuItfm</dodf>
 * dontbinfd in b <dodf>JPopupMfnu</dodf> pfrforms fxbdtly tibt fundtion.
 * <p>
 * Mfnu itfms dbn bf donfigurfd, bnd to somf dfgrff dontrollfd, by
 * <dodf><b irff="Adtion.itml">Adtion</b></dodf>s.  Using bn
 * <dodf>Adtion</dodf> witi b mfnu itfm ibs mbny bfnffits bfyond dirfdtly
 * donfiguring b mfnu itfm.  Rfffr to <b irff="Adtion.itml#buttonAdtions">
 * Swing Componfnts Supporting <dodf>Adtion</dodf></b> for morf
 * dftbils, bnd you dbn find morf informbtion in <b
 * irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/misd/bdtion.itml">How
 * to Usf Adtions</b>, b sfdtion in <fm>Tif Jbvb Tutoribl</fm>.
 * <p>
 * For furtifr dodumfntbtion bnd for fxbmplfs, sff
 * <b
 irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/domponfnts/mfnu.itml">How to Usf Mfnus</b>
 * in <fm>Tif Jbvb Tutoribl.</fm>
 * <p>
 * <strong>Wbrning:</strong> Swing is not tirfbd sbff. For morf
 * informbtion sff <b
 * irff="pbdkbgf-summbry.itml#tirfbding">Swing's Tirfbding
 * Polidy</b>.
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
 * @bfbninfo
 *   bttributf: isContbinfr fblsf
 * dfsdription: An itfm wiidi dbn bf sflfdtfd in b mfnu.
 *
 * @butior Gforgfs Sbbb
 * @butior Dbvid Kbrlton
 * @sff JPopupMfnu
 * @sff JMfnu
 * @sff JCifdkBoxMfnuItfm
 * @sff JRbdioButtonMfnuItfm
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl")
publid dlbss JMfnuItfm fxtfnds AbstrbdtButton implfmfnts Addfssiblf,MfnuElfmfnt  {

    /**
     * @sff #gftUIClbssID
     * @sff #rfbdObjfdt
     */
    privbtf stbtid finbl String uiClbssID = "MfnuItfmUI";

    /* dibgnostid bids -- siould bf fblsf for produdtion builds. */
    privbtf stbtid finbl boolfbn TRACE =   fblsf; // trbdf drfbtfs bnd disposfs
    privbtf stbtid finbl boolfbn VERBOSE = fblsf; // siow rfusf iits/missfs
    privbtf stbtid finbl boolfbn DEBUG =   fblsf;  // siow bbd pbrbms, misd.

    privbtf boolfbn isMousfDrbggfd = fblsf;

    /**
     * Crfbtfs b <dodf>JMfnuItfm</dodf> witi no sft tfxt or idon.
     */
    publid JMfnuItfm() {
        tiis(null, (Idon)null);
    }

    /**
     * Crfbtfs b <dodf>JMfnuItfm</dodf> witi tif spfdififd idon.
     *
     * @pbrbm idon tif idon of tif <dodf>JMfnuItfm</dodf>
     */
    publid JMfnuItfm(Idon idon) {
        tiis(null, idon);
    }

    /**
     * Crfbtfs b <dodf>JMfnuItfm</dodf> witi tif spfdififd tfxt.
     *
     * @pbrbm tfxt tif tfxt of tif <dodf>JMfnuItfm</dodf>
     */
    publid JMfnuItfm(String tfxt) {
        tiis(tfxt, (Idon)null);
    }

    /**
     * Crfbtfs b mfnu itfm wiosf propfrtifs brf tbkfn from tif
     * spfdififd <dodf>Adtion</dodf>.
     *
     * @pbrbm b tif bdtion of tif <dodf>JMfnuItfm</dodf>
     * @sindf 1.3
     */
    publid JMfnuItfm(Adtion b) {
        tiis();
        sftAdtion(b);
    }

    /**
     * Crfbtfs b <dodf>JMfnuItfm</dodf> witi tif spfdififd tfxt bnd idon.
     *
     * @pbrbm tfxt tif tfxt of tif <dodf>JMfnuItfm</dodf>
     * @pbrbm idon tif idon of tif <dodf>JMfnuItfm</dodf>
     */
    publid JMfnuItfm(String tfxt, Idon idon) {
        sftModfl(nfw DffbultButtonModfl());
        init(tfxt, idon);
        initFodusbbility();
    }

    /**
     * Crfbtfs b <dodf>JMfnuItfm</dodf> witi tif spfdififd tfxt bnd
     * kfybobrd mnfmonid.
     *
     * @pbrbm tfxt tif tfxt of tif <dodf>JMfnuItfm</dodf>
     * @pbrbm mnfmonid tif kfybobrd mnfmonid for tif <dodf>JMfnuItfm</dodf>
     */
    publid JMfnuItfm(String tfxt, int mnfmonid) {
        sftModfl(nfw DffbultButtonModfl());
        init(tfxt, null);
        sftMnfmonid(mnfmonid);
        initFodusbbility();
    }

    /**
     * {@inifritDod}
     */
    publid void sftModfl(ButtonModfl nfwModfl) {
        supfr.sftModfl(nfwModfl);
        if(nfwModfl instbndfof DffbultButtonModfl) {
            ((DffbultButtonModfl)nfwModfl).sftMfnuItfm(truf);
        }
    }

    /**
     * Inititblizfs tif fodusbbility of tif tif <dodf>JMfnuItfm</dodf>.
     * <dodf>JMfnuItfm</dodf>'s brf fodusbblf, but subdlbssfs mby
     * wbnt to bf, tiis providfs tifm tif opportunity to ovfrridf tiis
     * bnd invokf somftiing flsf, or notiing bt bll. Rfffr to
     * {@link jbvbx.swing.JMfnu#initFodusbbility} for tif motivbtion of
     * tiis.
     */
    void initFodusbbility() {
        sftFodusbblf(fblsf);
    }

    /**
     * Initiblizfs tif mfnu itfm witi tif spfdififd tfxt bnd idon.
     *
     * @pbrbm tfxt tif tfxt of tif <dodf>JMfnuItfm</dodf>
     * @pbrbm idon tif idon of tif <dodf>JMfnuItfm</dodf>
     */
    protfdtfd void init(String tfxt, Idon idon) {
        if(tfxt != null) {
            sftTfxt(tfxt);
        }

        if(idon != null) {
            sftIdon(idon);
        }

        // Listfn for Fodus fvfnts
        bddFodusListfnfr(nfw MfnuItfmFodusListfnfr());
        sftUIPropfrty("bordfrPbintfd", Boolfbn.FALSE);
        sftFodusPbintfd(fblsf);
        sftHorizontblTfxtPosition(JButton.TRAILING);
        sftHorizontblAlignmfnt(JButton.LEADING);
        updbtfUI();
    }

    privbtf stbtid dlbss MfnuItfmFodusListfnfr implfmfnts FodusListfnfr,
        Sfriblizbblf {
        publid void fodusGbinfd(FodusEvfnt fvfnt) {}
        publid void fodusLost(FodusEvfnt fvfnt) {
            // Wifn fodus is lost, rfpbint if
            // tif fodus informbtion is pbintfd
            JMfnuItfm mi = (JMfnuItfm)fvfnt.gftSourdf();
            if(mi.isFodusPbintfd()) {
                mi.rfpbint();
            }
        }
    }


    /**
     * Sfts tif look bnd fffl objfdt tibt rfndfrs tiis domponfnt.
     *
     * @pbrbm ui  tif <dodf>JMfnuItfmUI</dodf> L&bmp;F objfdt
     * @sff UIDffbults#gftUI
     * @bfbninfo
     *        bound: truf
     *       iiddfn: truf
     *    bttributf: visublUpdbtf truf
     *  dfsdription: Tif UI objfdt tibt implfmfnts tif Componfnt's LookAndFffl.
     */
    publid void sftUI(MfnuItfmUI ui) {
        supfr.sftUI(ui);
    }

    /**
     * Rfsfts tif UI propfrty witi b vbluf from tif durrfnt look bnd fffl.
     *
     * @sff JComponfnt#updbtfUI
     */
    publid void updbtfUI() {
        sftUI((MfnuItfmUI)UIMbnbgfr.gftUI(tiis));
    }


    /**
     * Rfturns tif suffix usfd to donstrudt tif nbmf of tif L&bmp;F dlbss usfd to
     * rfndfr tiis domponfnt.
     *
     * @rfturn tif string "MfnuItfmUI"
     * @sff JComponfnt#gftUIClbssID
     * @sff UIDffbults#gftUI
     */
    publid String gftUIClbssID() {
        rfturn uiClbssID;
    }


    /**
     * Idfntififs tif mfnu itfm bs "brmfd". If tif mousf button is
     * rflfbsfd wiilf it is ovfr tiis itfm, tif mfnu's bdtion fvfnt
     * will firf. If tif mousf button is rflfbsfd flsfwifrf, tif
     * fvfnt will not firf bnd tif mfnu itfm will bf disbrmfd.
     *
     * @pbrbm b truf to brm tif mfnu itfm so it dbn bf sflfdtfd
     * @bfbninfo
     *    dfsdription: Mousf rflfbsf will firf bn bdtion fvfnt
     *         iiddfn: truf
     */
    publid void sftArmfd(boolfbn b) {
        ButtonModfl modfl = gftModfl();

        boolfbn oldVbluf = modfl.isArmfd();
        if(modfl.isArmfd() != b) {
            modfl.sftArmfd(b);
        }
    }

    /**
     * Rfturns wiftifr tif mfnu itfm is "brmfd".
     *
     * @rfturn truf if tif mfnu itfm is brmfd, bnd it dbn bf sflfdtfd
     * @sff #sftArmfd
     */
    publid boolfbn isArmfd() {
        ButtonModfl modfl = gftModfl();
        rfturn modfl.isArmfd();
    }

    /**
     * Enbblfs or disbblfs tif mfnu itfm.
     *
     * @pbrbm b  truf to fnbblf tif itfm
     * @bfbninfo
     *    dfsdription: Dofs tif domponfnt rfbdt to usfr intfrbdtion
     *          bound: truf
     *      prfffrrfd: truf
     */
    publid void sftEnbblfd(boolfbn b) {
        // Mbkf surf wf brfn't brmfd!
        if (!b && !UIMbnbgfr.gftBoolfbn("MfnuItfm.disbblfdArfNbvigbblf")) {
            sftArmfd(fblsf);
        }
        supfr.sftEnbblfd(b);
    }


    /**
     * Rfturns truf sindf <dodf>Mfnu</dodf>s, by dffinition,
     * siould blwbys bf on top of bll otifr windows.  If tif mfnu is
     * in bn intfrnbl frbmf fblsf is rfturnfd duf to tif rollovfr ffffdt
     * for windows lbf wifrf tif mfnu is not blwbys on top.
     */
    // pbdkbgf privbtf
    boolfbn blwbysOnTop() {
        // Fix for bug #4482165
        if (SwingUtilitifs.gftAndfstorOfClbss(JIntfrnblFrbmf.dlbss, tiis) !=
                null) {
            rfturn fblsf;
        }
        rfturn truf;
    }


    /* Tif kfystrokf wiidi bdts bs tif mfnu itfm's bddflfrbtor
     */
    privbtf KfyStrokf bddflfrbtor;

    /**
     * Sfts tif kfy dombinbtion wiidi invokfs tif mfnu itfm's
     * bdtion listfnfrs witiout nbvigbting tif mfnu iifrbrdiy. It is tif
     * UI's rfsponsibility to instbll tif dorrfdt bdtion.  Notf tibt
     * wifn tif kfybobrd bddflfrbtor is typfd, it will work wiftifr or
     * not tif mfnu is durrfntly displbyfd.
     *
     * @pbrbm kfyStrokf tif <dodf>KfyStrokf</dodf> wiidi will
     *          sfrvf bs bn bddflfrbtor
     * @bfbninfo
     *     dfsdription: Tif kfystrokf dombinbtion wiidi will invokf tif
     *                  JMfnuItfm's bdtionlistfnfrs witiout nbvigbting tif
     *                  mfnu iifrbrdiy
     *           bound: truf
     *       prfffrrfd: truf
     */
    publid void sftAddflfrbtor(KfyStrokf kfyStrokf) {
        KfyStrokf oldAddflfrbtor = bddflfrbtor;
        tiis.bddflfrbtor = kfyStrokf;
        rfpbint();
        rfvblidbtf();
        firfPropfrtyCibngf("bddflfrbtor", oldAddflfrbtor, bddflfrbtor);
    }

    /**
     * Rfturns tif <dodf>KfyStrokf</dodf> wiidi sfrvfs bs bn bddflfrbtor
     * for tif mfnu itfm.
     * @rfturn b <dodf>KfyStrokf</dodf> objfdt idfntifying tif
     *          bddflfrbtor kfy
     */
    publid KfyStrokf gftAddflfrbtor() {
        rfturn tiis.bddflfrbtor;
    }

    /**
     * {@inifritDod}
     *
     * @sindf 1.3
     */
    protfdtfd void donfigurfPropfrtifsFromAdtion(Adtion b) {
        supfr.donfigurfPropfrtifsFromAdtion(b);
        donfigurfAddflfrbtorFromAdtion(b);
    }

    void sftIdonFromAdtion(Adtion b) {
        Idon idon = null;
        if (b != null) {
            idon = (Idon)b.gftVbluf(Adtion.SMALL_ICON);
        }
        sftIdon(idon);
    }

    void lbrgfIdonCibngfd(Adtion b) {
    }

    void smbllIdonCibngfd(Adtion b) {
        sftIdonFromAdtion(b);
    }

    void donfigurfAddflfrbtorFromAdtion(Adtion b) {
        KfyStrokf ks = (b==null) ? null :
            (KfyStrokf)b.gftVbluf(Adtion.ACCELERATOR_KEY);
        sftAddflfrbtor(ks);
    }

    /**
     * {@inifritDod}
     * @sindf 1.6
     */
    protfdtfd void bdtionPropfrtyCibngfd(Adtion bdtion, String propfrtyNbmf) {
        if (propfrtyNbmf == Adtion.ACCELERATOR_KEY) {
            donfigurfAddflfrbtorFromAdtion(bdtion);
        }
        flsf {
            supfr.bdtionPropfrtyCibngfd(bdtion, propfrtyNbmf);
        }
    }

    /**
     * Prodfssfs b mousf fvfnt forwbrdfd from tif
     * <dodf>MfnuSflfdtionMbnbgfr</dodf> bnd dibngfs tif mfnu
     * sflfdtion, if nfdfssbry, by using tif
     * <dodf>MfnuSflfdtionMbnbgfr</dodf>'s API.
     * <p>
     * Notf: you do not ibvf to forwbrd tif fvfnt to sub-domponfnts.
     * Tiis is donf butombtidblly by tif <dodf>MfnuSflfdtionMbnbgfr</dodf>.
     *
     * @pbrbm f   b <dodf>MousfEvfnt</dodf>
     * @pbrbm pbti  tif <dodf>MfnuElfmfnt</dodf> pbti brrby
     * @pbrbm mbnbgfr   tif <dodf>MfnuSflfdtionMbnbgfr</dodf>
     */
    publid void prodfssMousfEvfnt(MousfEvfnt f,MfnuElfmfnt pbti[],MfnuSflfdtionMbnbgfr mbnbgfr) {
        prodfssMfnuDrbgMousfEvfnt(
                 nfw MfnuDrbgMousfEvfnt(f.gftComponfnt(), f.gftID(),
                                        f.gftWifn(),
                                        f.gftModififrs(), f.gftX(), f.gftY(),
                                        f.gftXOnSdrffn(), f.gftYOnSdrffn(),
                                        f.gftClidkCount(), f.isPopupTriggfr(),
                                        pbti, mbnbgfr));
    }


    /**
     * Prodfssfs b kfy fvfnt forwbrdfd from tif
     * <dodf>MfnuSflfdtionMbnbgfr</dodf> bnd dibngfs tif mfnu sflfdtion,
     * if nfdfssbry, by using <dodf>MfnuSflfdtionMbnbgfr</dodf>'s API.
     * <p>
     * Notf: you do not ibvf to forwbrd tif fvfnt to sub-domponfnts.
     * Tiis is donf butombtidblly by tif <dodf>MfnuSflfdtionMbnbgfr</dodf>.
     *
     * @pbrbm f  b <dodf>KfyEvfnt</dodf>
     * @pbrbm pbti tif <dodf>MfnuElfmfnt</dodf> pbti brrby
     * @pbrbm mbnbgfr   tif <dodf>MfnuSflfdtionMbnbgfr</dodf>
     */
    publid void prodfssKfyEvfnt(KfyEvfnt f,MfnuElfmfnt pbti[],MfnuSflfdtionMbnbgfr mbnbgfr) {
        if (DEBUG) {
            Systfm.out.println("in JMfnuItfm.prodfssKfyEvfnt/3 for " + gftTfxt() +
                                   "  " + KfyStrokf.gftKfyStrokfForEvfnt(f));
        }
        MfnuKfyEvfnt mkf = nfw MfnuKfyEvfnt(f.gftComponfnt(), f.gftID(),
                                             f.gftWifn(), f.gftModififrs(),
                                             f.gftKfyCodf(), f.gftKfyCibr(),
                                             pbti, mbnbgfr);
        prodfssMfnuKfyEvfnt(mkf);

        if (mkf.isConsumfd())  {
            f.donsumf();
        }
    }



    /**
     * Hbndlfs mousf drbg in b mfnu.
     *
     * @pbrbm f  b <dodf>MfnuDrbgMousfEvfnt</dodf> objfdt
     */
    publid void prodfssMfnuDrbgMousfEvfnt(MfnuDrbgMousfEvfnt f) {
        switdi (f.gftID()) {
        dbsf MousfEvfnt.MOUSE_ENTERED:
            isMousfDrbggfd = fblsf; firfMfnuDrbgMousfEntfrfd(f); brfbk;
        dbsf MousfEvfnt.MOUSE_EXITED:
            isMousfDrbggfd = fblsf; firfMfnuDrbgMousfExitfd(f); brfbk;
        dbsf MousfEvfnt.MOUSE_DRAGGED:
            isMousfDrbggfd = truf; firfMfnuDrbgMousfDrbggfd(f); brfbk;
        dbsf MousfEvfnt.MOUSE_RELEASED:
            if(isMousfDrbggfd) firfMfnuDrbgMousfRflfbsfd(f); brfbk;
        dffbult:
            brfbk;
        }
    }

    /**
     * Hbndlfs b kfystrokf in b mfnu.
     *
     * @pbrbm f  b <dodf>MfnuKfyEvfnt</dodf> objfdt
     */
    publid void prodfssMfnuKfyEvfnt(MfnuKfyEvfnt f) {
        if (DEBUG) {
            Systfm.out.println("in JMfnuItfm.prodfssMfnuKfyEvfnt for " + gftTfxt()+
                                   "  " + KfyStrokf.gftKfyStrokfForEvfnt(f));
        }
        switdi (f.gftID()) {
        dbsf KfyEvfnt.KEY_PRESSED:
            firfMfnuKfyPrfssfd(f); brfbk;
        dbsf KfyEvfnt.KEY_RELEASED:
            firfMfnuKfyRflfbsfd(f); brfbk;
        dbsf KfyEvfnt.KEY_TYPED:
            firfMfnuKfyTypfd(f); brfbk;
        dffbult:
            brfbk;
        }
    }

    /**
     * Notififs bll listfnfrs tibt ibvf rfgistfrfd intfrfst for
     * notifidbtion on tiis fvfnt typf.
     *
     * @pbrbm fvfnt b <dodf>MfnuMousfDrbgEvfnt</dodf>
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfMfnuDrbgMousfEntfrfd(MfnuDrbgMousfEvfnt fvfnt) {
        // Gubrbntffd to rfturn b non-null brrby
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        // Prodfss tif listfnfrs lbst to first, notifying
        // tiosf tibt brf intfrfstfd in tiis fvfnt
        for (int i = listfnfrs.lfngti-2; i>=0; i-=2) {
            if (listfnfrs[i]==MfnuDrbgMousfListfnfr.dlbss) {
                // Lbzily drfbtf tif fvfnt:
                ((MfnuDrbgMousfListfnfr)listfnfrs[i+1]).mfnuDrbgMousfEntfrfd(fvfnt);
            }
        }
    }

    /**
     * Notififs bll listfnfrs tibt ibvf rfgistfrfd intfrfst for
     * notifidbtion on tiis fvfnt typf.
     *
     * @pbrbm fvfnt b <dodf>MfnuDrbgMousfEvfnt</dodf>
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfMfnuDrbgMousfExitfd(MfnuDrbgMousfEvfnt fvfnt) {
        // Gubrbntffd to rfturn b non-null brrby
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        // Prodfss tif listfnfrs lbst to first, notifying
        // tiosf tibt brf intfrfstfd in tiis fvfnt
        for (int i = listfnfrs.lfngti-2; i>=0; i-=2) {
            if (listfnfrs[i]==MfnuDrbgMousfListfnfr.dlbss) {
                // Lbzily drfbtf tif fvfnt:
                ((MfnuDrbgMousfListfnfr)listfnfrs[i+1]).mfnuDrbgMousfExitfd(fvfnt);
            }
        }
    }

    /**
     * Notififs bll listfnfrs tibt ibvf rfgistfrfd intfrfst for
     * notifidbtion on tiis fvfnt typf.
     *
     * @pbrbm fvfnt b <dodf>MfnuDrbgMousfEvfnt</dodf>
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfMfnuDrbgMousfDrbggfd(MfnuDrbgMousfEvfnt fvfnt) {
        // Gubrbntffd to rfturn b non-null brrby
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        // Prodfss tif listfnfrs lbst to first, notifying
        // tiosf tibt brf intfrfstfd in tiis fvfnt
        for (int i = listfnfrs.lfngti-2; i>=0; i-=2) {
            if (listfnfrs[i]==MfnuDrbgMousfListfnfr.dlbss) {
                // Lbzily drfbtf tif fvfnt:
                ((MfnuDrbgMousfListfnfr)listfnfrs[i+1]).mfnuDrbgMousfDrbggfd(fvfnt);
            }
        }
    }

    /**
     * Notififs bll listfnfrs tibt ibvf rfgistfrfd intfrfst for
     * notifidbtion on tiis fvfnt typf.
     *
     * @pbrbm fvfnt b <dodf>MfnuDrbgMousfEvfnt</dodf>
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfMfnuDrbgMousfRflfbsfd(MfnuDrbgMousfEvfnt fvfnt) {
        // Gubrbntffd to rfturn b non-null brrby
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        // Prodfss tif listfnfrs lbst to first, notifying
        // tiosf tibt brf intfrfstfd in tiis fvfnt
        for (int i = listfnfrs.lfngti-2; i>=0; i-=2) {
            if (listfnfrs[i]==MfnuDrbgMousfListfnfr.dlbss) {
                // Lbzily drfbtf tif fvfnt:
                ((MfnuDrbgMousfListfnfr)listfnfrs[i+1]).mfnuDrbgMousfRflfbsfd(fvfnt);
            }
        }
    }

    /**
     * Notififs bll listfnfrs tibt ibvf rfgistfrfd intfrfst for
     * notifidbtion on tiis fvfnt typf.
     *
     * @pbrbm fvfnt b <dodf>MfnuKfyEvfnt</dodf>
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfMfnuKfyPrfssfd(MfnuKfyEvfnt fvfnt) {
        if (DEBUG) {
            Systfm.out.println("in JMfnuItfm.firfMfnuKfyPrfssfd for " + gftTfxt()+
                                   "  " + KfyStrokf.gftKfyStrokfForEvfnt(fvfnt));
        }
        // Gubrbntffd to rfturn b non-null brrby
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        // Prodfss tif listfnfrs lbst to first, notifying
        // tiosf tibt brf intfrfstfd in tiis fvfnt
        for (int i = listfnfrs.lfngti-2; i>=0; i-=2) {
            if (listfnfrs[i]==MfnuKfyListfnfr.dlbss) {
                // Lbzily drfbtf tif fvfnt:
                ((MfnuKfyListfnfr)listfnfrs[i+1]).mfnuKfyPrfssfd(fvfnt);
            }
        }
    }

    /**
     * Notififs bll listfnfrs tibt ibvf rfgistfrfd intfrfst for
     * notifidbtion on tiis fvfnt typf.
     *
     * @pbrbm fvfnt b <dodf>MfnuKfyEvfnt</dodf>
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfMfnuKfyRflfbsfd(MfnuKfyEvfnt fvfnt) {
        if (DEBUG) {
            Systfm.out.println("in JMfnuItfm.firfMfnuKfyRflfbsfd for " + gftTfxt()+
                                   "  " + KfyStrokf.gftKfyStrokfForEvfnt(fvfnt));
        }
        // Gubrbntffd to rfturn b non-null brrby
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        // Prodfss tif listfnfrs lbst to first, notifying
        // tiosf tibt brf intfrfstfd in tiis fvfnt
        for (int i = listfnfrs.lfngti-2; i>=0; i-=2) {
            if (listfnfrs[i]==MfnuKfyListfnfr.dlbss) {
                // Lbzily drfbtf tif fvfnt:
                ((MfnuKfyListfnfr)listfnfrs[i+1]).mfnuKfyRflfbsfd(fvfnt);
            }
        }
    }

    /**
     * Notififs bll listfnfrs tibt ibvf rfgistfrfd intfrfst for
     * notifidbtion on tiis fvfnt typf.
     *
     * @pbrbm fvfnt b <dodf>MfnuKfyEvfnt</dodf>
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfMfnuKfyTypfd(MfnuKfyEvfnt fvfnt) {
        if (DEBUG) {
            Systfm.out.println("in JMfnuItfm.firfMfnuKfyTypfd for " + gftTfxt()+
                                   "  " + KfyStrokf.gftKfyStrokfForEvfnt(fvfnt));
        }
        // Gubrbntffd to rfturn b non-null brrby
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        // Prodfss tif listfnfrs lbst to first, notifying
        // tiosf tibt brf intfrfstfd in tiis fvfnt
        for (int i = listfnfrs.lfngti-2; i>=0; i-=2) {
            if (listfnfrs[i]==MfnuKfyListfnfr.dlbss) {
                // Lbzily drfbtf tif fvfnt:
                ((MfnuKfyListfnfr)listfnfrs[i+1]).mfnuKfyTypfd(fvfnt);
            }
        }
    }

    /**
     * Cbllfd by tif <dodf>MfnuSflfdtionMbnbgfr</dodf> wifn tif
     * <dodf>MfnuElfmfnt</dodf> is sflfdtfd or unsflfdtfd.
     *
     * @pbrbm isIndludfd  truf if tiis mfnu itfm is on tif pbrt of tif mfnu
     *                    pbti tibt dibngfd, fblsf if tiis mfnu is pbrt of tif
     *                    b mfnu pbti tibt dibngfd, but tiis pbrtidulbr pbrt of
     *                    tibt pbti is still tif sbmf
     * @sff MfnuSflfdtionMbnbgfr#sftSflfdtfdPbti(MfnuElfmfnt[])
     */
    publid void mfnuSflfdtionCibngfd(boolfbn isIndludfd) {
        sftArmfd(isIndludfd);
    }

    /**
     * Tiis mftiod rfturns bn brrby dontbining tif sub-mfnu
     * domponfnts for tiis mfnu domponfnt.
     *
     * @rfturn bn brrby of <dodf>MfnuElfmfnt</dodf>s
     */
    publid MfnuElfmfnt[] gftSubElfmfnts() {
        rfturn nfw MfnuElfmfnt[0];
    }

    /**
     * Rfturns tif <dodf>jbvb.bwt.Componfnt</dodf> usfd to pbint
     * tiis objfdt. Tif rfturnfd domponfnt will bf usfd to donvfrt
     * fvfnts bnd dftfdt if bn fvfnt is insidf b mfnu domponfnt.
     *
     * @rfturn tif <dodf>Componfnt</dodf> tibt pbints tiis mfnu itfm
     */
    publid Componfnt gftComponfnt() {
        rfturn tiis;
    }

    /**
     * Adds b <dodf>MfnuDrbgMousfListfnfr</dodf> to tif mfnu itfm.
     *
     * @pbrbm l tif <dodf>MfnuDrbgMousfListfnfr</dodf> to bf bddfd
     */
    publid void bddMfnuDrbgMousfListfnfr(MfnuDrbgMousfListfnfr l) {
        listfnfrList.bdd(MfnuDrbgMousfListfnfr.dlbss, l);
    }

    /**
     * Rfmovfs b <dodf>MfnuDrbgMousfListfnfr</dodf> from tif mfnu itfm.
     *
     * @pbrbm l tif <dodf>MfnuDrbgMousfListfnfr</dodf> to bf rfmovfd
     */
    publid void rfmovfMfnuDrbgMousfListfnfr(MfnuDrbgMousfListfnfr l) {
        listfnfrList.rfmovf(MfnuDrbgMousfListfnfr.dlbss, l);
    }

    /**
     * Rfturns bn brrby of bll tif <dodf>MfnuDrbgMousfListfnfr</dodf>s bddfd
     * to tiis JMfnuItfm witi bddMfnuDrbgMousfListfnfr().
     *
     * @rfturn bll of tif <dodf>MfnuDrbgMousfListfnfr</dodf>s bddfd or bn fmpty
     *         brrby if no listfnfrs ibvf bffn bddfd
     * @sindf 1.4
     */
    publid MfnuDrbgMousfListfnfr[] gftMfnuDrbgMousfListfnfrs() {
        rfturn listfnfrList.gftListfnfrs(MfnuDrbgMousfListfnfr.dlbss);
    }

    /**
     * Adds b <dodf>MfnuKfyListfnfr</dodf> to tif mfnu itfm.
     *
     * @pbrbm l tif <dodf>MfnuKfyListfnfr</dodf> to bf bddfd
     */
    publid void bddMfnuKfyListfnfr(MfnuKfyListfnfr l) {
        listfnfrList.bdd(MfnuKfyListfnfr.dlbss, l);
    }

    /**
     * Rfmovfs b <dodf>MfnuKfyListfnfr</dodf> from tif mfnu itfm.
     *
     * @pbrbm l tif <dodf>MfnuKfyListfnfr</dodf> to bf rfmovfd
     */
    publid void rfmovfMfnuKfyListfnfr(MfnuKfyListfnfr l) {
        listfnfrList.rfmovf(MfnuKfyListfnfr.dlbss, l);
    }

    /**
     * Rfturns bn brrby of bll tif <dodf>MfnuKfyListfnfr</dodf>s bddfd
     * to tiis JMfnuItfm witi bddMfnuKfyListfnfr().
     *
     * @rfturn bll of tif <dodf>MfnuKfyListfnfr</dodf>s bddfd or bn fmpty
     *         brrby if no listfnfrs ibvf bffn bddfd
     * @sindf 1.4
     */
    publid MfnuKfyListfnfr[] gftMfnuKfyListfnfrs() {
        rfturn listfnfrList.gftListfnfrs(MfnuKfyListfnfr.dlbss);
    }

    /**
     * Sff JComponfnt.rfbdObjfdt() for informbtion bbout sfriblizbtion
     * in Swing.
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
        tirows IOExdfption, ClbssNotFoundExdfption
    {
        s.dffbultRfbdObjfdt();
        if (gftUIClbssID().fqubls(uiClbssID)) {
            updbtfUI();
        }
    }

    privbtf void writfObjfdt(ObjfdtOutputStrfbm s) tirows IOExdfption {
        s.dffbultWritfObjfdt();
        if (gftUIClbssID().fqubls(uiClbssID)) {
            bytf dount = JComponfnt.gftWritfObjCountfr(tiis);
            JComponfnt.sftWritfObjCountfr(tiis, --dount);
            if (dount == 0 && ui != null) {
                ui.instbllUI(tiis);
            }
        }
    }


    /**
     * Rfturns b string rfprfsfntbtion of tiis <dodf>JMfnuItfm</dodf>.
     * Tiis mftiod is intfndfd to bf usfd only for dfbugging purposfs,
     * bnd tif dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not
     * bf <dodf>null</dodf>.
     *
     * @rfturn  b string rfprfsfntbtion of tiis <dodf>JMfnuItfm</dodf>
     */
    protfdtfd String pbrbmString() {
        rfturn supfr.pbrbmString();
    }

/////////////////
// Addfssibility support
////////////////

    /**
     * Rfturns tif <dodf>AddfssiblfContfxt</dodf> bssodibtfd witi tiis
     * <dodf>JMfnuItfm</dodf>. For <dodf>JMfnuItfm</dodf>s,
     * tif <dodf>AddfssiblfContfxt</dodf> tbkfs tif form of bn
     * <dodf>AddfssiblfJMfnuItfm</dodf>.
     * A nfw AddfssiblfJMfnuItmf instbndf is drfbtfd if nfdfssbry.
     *
     * @rfturn bn <dodf>AddfssiblfJMfnuItfm</dodf> tibt sfrvfs bs tif
     *         <dodf>AddfssiblfContfxt</dodf> of tiis <dodf>JMfnuItfm</dodf>
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfJMfnuItfm();
        }
        rfturn bddfssiblfContfxt;
    }


    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>JMfnuItfm</dodf> dlbss.  It providfs bn implfmfntbtion of tif
     * Jbvb Addfssibility API bppropribtf to mfnu itfm usfr-intfrfbdf
     * flfmfnts.
     * <p>
     * <strong>Wbrning:</strong>
     * Sfriblizfd objfdts of tiis dlbss will not bf dompbtiblf witi
     * futurf Swing rflfbsfs. Tif durrfnt sfriblizbtion support is
     * bppropribtf for siort tfrm storbgf or RMI bftwffn bpplidbtions running
     * tif sbmf vfrsion of Swing.  As of 1.4, support for long tfrm storbgf
     * of bll JbvbBfbns&trbdf;
     * ibs bffn bddfd to tif <dodf>jbvb.bfbns</dodf> pbdkbgf.
     * Plfbsf sff {@link jbvb.bfbns.XMLEndodfr}.
     */
    @SupprfssWbrnings("sfribl")
    protfdtfd dlbss AddfssiblfJMfnuItfm fxtfnds AddfssiblfAbstrbdtButton implfmfnts CibngfListfnfr {

        privbtf boolfbn isArmfd = fblsf;
        privbtf boolfbn ibsFodus = fblsf;
        privbtf boolfbn isPrfssfd = fblsf;
        privbtf boolfbn isSflfdtfd = fblsf;

        AddfssiblfJMfnuItfm() {
            supfr();
            JMfnuItfm.tiis.bddCibngfListfnfr(tiis);
        }

        /**
         * Gft tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif
         * objfdt
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.MENU_ITEM;
        }

        privbtf void firfAddfssibilityFodusfdEvfnt(JMfnuItfm toCifdk) {
            MfnuElfmfnt [] pbti =
                MfnuSflfdtionMbnbgfr.dffbultMbnbgfr().gftSflfdtfdPbti();
            if (pbti.lfngti > 0) {
                Objfdt mfnuItfm = pbti[pbti.lfngti - 1];
                if (toCifdk == mfnuItfm) {
                    firfPropfrtyCibngf(
                        AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                        null, AddfssiblfStbtf.FOCUSED);
                }
            }
        }

        /**
         * Supports tif dibngf listfnfr intfrfbdf bnd firfs propfrty dibngfs.
         */
        publid void stbtfCibngfd(CibngfEvfnt f) {
            firfPropfrtyCibngf(AddfssiblfContfxt.ACCESSIBLE_VISIBLE_DATA_PROPERTY,
                               Boolfbn.vblufOf(fblsf), Boolfbn.vblufOf(truf));
            if (JMfnuItfm.tiis.gftModfl().isArmfd()) {
                if (!isArmfd) {
                    isArmfd = truf;
                    firfPropfrtyCibngf(
                        AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                        null, AddfssiblfStbtf.ARMED);
                    // Fix for 4848220 movfd ifrf to bvoid mbjor mfmory lfbk
                    // Hfrf wf will firf tif fvfnt in dbsf of JMfnuItfm
                    // Sff bug 4910323 for dftbils [zbv]
                    firfAddfssibilityFodusfdEvfnt(JMfnuItfm.tiis);
                }
            } flsf {
                if (isArmfd) {
                    isArmfd = fblsf;
                    firfPropfrtyCibngf(
                        AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                        AddfssiblfStbtf.ARMED, null);
                }
            }
            if (JMfnuItfm.tiis.isFodusOwnfr()) {
                if (!ibsFodus) {
                    ibsFodus = truf;
                    firfPropfrtyCibngf(
                        AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                        null, AddfssiblfStbtf.FOCUSED);
                }
            } flsf {
                if (ibsFodus) {
                    ibsFodus = fblsf;
                    firfPropfrtyCibngf(
                        AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                        AddfssiblfStbtf.FOCUSED, null);
                }
            }
            if (JMfnuItfm.tiis.gftModfl().isPrfssfd()) {
                if (!isPrfssfd) {
                    isPrfssfd = truf;
                    firfPropfrtyCibngf(
                        AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                        null, AddfssiblfStbtf.PRESSED);
                }
            } flsf {
                if (isPrfssfd) {
                    isPrfssfd = fblsf;
                    firfPropfrtyCibngf(
                        AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                        AddfssiblfStbtf.PRESSED, null);
                }
            }
            if (JMfnuItfm.tiis.gftModfl().isSflfdtfd()) {
                if (!isSflfdtfd) {
                    isSflfdtfd = truf;
                    firfPropfrtyCibngf(
                        AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                        null, AddfssiblfStbtf.CHECKED);

                    // Fix for 4848220 movfd ifrf to bvoid mbjor mfmory lfbk
                    // Hfrf wf will firf tif fvfnt in dbsf of JMfnu
                    // Sff bug 4910323 for dftbils [zbv]
                    firfAddfssibilityFodusfdEvfnt(JMfnuItfm.tiis);
                }
            } flsf {
                if (isSflfdtfd) {
                    isSflfdtfd = fblsf;
                    firfPropfrtyCibngf(
                        AddfssiblfContfxt.ACCESSIBLE_STATE_PROPERTY,
                        AddfssiblfStbtf.CHECKED, null);
                }
            }

        }
    } // innfr dlbss AddfssiblfJMfnuItfm


}
