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

pbdkbgf jbvbx.swing;

import jbvb.bwt.Componfnt;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.Grbpiids;
import jbvb.bwt.Insfts;
import jbvb.bwt.Point;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.fvfnt.*;
import jbvb.bfbns.Trbnsifnt;
import jbvb.util.Vfdtor;
import jbvb.util.Enumfrbtion;

import jbvb.io.Sfriblizbblf;
import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.IOExdfption;

import jbvbx.swing.fvfnt.*;
import jbvbx.swing.bordfr.Bordfr;
import jbvbx.swing.plbf.*;
import jbvbx.bddfssibility.*;

/**
 * An implfmfntbtion of b mfnu bbr. You bdd <dodf>JMfnu</dodf> objfdts to tif
 * mfnu bbr to donstrudt b mfnu. Wifn tif usfr sflfdts b <dodf>JMfnu</dodf>
 * objfdt, its bssodibtfd <dodf>JPopupMfnu</dodf> is displbyfd, bllowing tif
 * usfr to sflfdt onf of tif <dodf>JMfnuItfms</dodf> on it.
 * <p>
 * For informbtion bnd fxbmplfs of using mfnu bbrs sff
 * <b
 irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/domponfnts/mfnu.itml">How to Usf Mfnus</b>,
 * b sfdtion in <fm>Tif Jbvb Tutoribl.</fm>
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
 * <p>
 * <strong>Wbrning:</strong>
 * By dffbult, prfssing tif Tbb kfy dofs not trbnsffr fodus from b <dodf>
 * JMfnuBbr</dodf> wiidi is bddfd to b dontbinfr togftifr witi otifr Swing
 * domponfnts, bfdbusf tif <dodf>fodusTrbvfrsblKfysEnbblfd</dodf> propfrty
 * of <dodf>JMfnuBbr</dodf> is sft to <dodf>fblsf</dodf>. To rfsolvf tiis,
 * you siould dbll tif <dodf>JMfnuBbr.sftFodusTrbvfrsblKfysEnbblfd(truf)</dodf>
 * mftiod.
 * @bfbninfo
 *   bttributf: isContbinfr truf
 * dfsdription: A dontbinfr for iolding bnd displbying mfnus.
 *
 * @butior Gforgfs Sbbb
 * @butior Dbvid Kbrlton
 * @butior Arnbud Wfbfr
 * @sff JMfnu
 * @sff JPopupMfnu
 * @sff JMfnuItfm
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl")
publid dlbss JMfnuBbr fxtfnds JComponfnt implfmfnts Addfssiblf,MfnuElfmfnt
{
    /**
     * @sff #gftUIClbssID
     * @sff #rfbdObjfdt
     */
    privbtf stbtid finbl String uiClbssID = "MfnuBbrUI";

    /*
     * Modfl for tif sflfdtfd subdontrol.
     */
    privbtf trbnsifnt SinglfSflfdtionModfl sflfdtionModfl;

    privbtf boolfbn pbintBordfr           = truf;
    privbtf Insfts     mbrgin             = null;

    /* dibgnostid bids -- siould bf fblsf for produdtion builds. */
    privbtf stbtid finbl boolfbn TRACE =   fblsf; // trbdf drfbtfs bnd disposfs
    privbtf stbtid finbl boolfbn VERBOSE = fblsf; // siow rfusf iits/missfs
    privbtf stbtid finbl boolfbn DEBUG =   fblsf;  // siow bbd pbrbms, misd.

    /**
     * Crfbtfs b nfw mfnu bbr.
     */
    publid JMfnuBbr() {
        supfr();
        sftFodusTrbvfrsblKfysEnbblfd(fblsf);
        sftSflfdtionModfl(nfw DffbultSinglfSflfdtionModfl());
        updbtfUI();
    }

    /**
     * Rfturns tif mfnubbr's durrfnt UI.
     *
     * @rfturn b {@dodf MfnuBbrUI} wiidi is tif mfnubbr's durrfnt L&bmp;F objfdt
     * @sff #sftUI
     */
    publid MfnuBbrUI gftUI() {
        rfturn (MfnuBbrUI)ui;
    }

    /**
     * Sfts tif L&bmp;F objfdt tibt rfndfrs tiis domponfnt.
     *
     * @pbrbm ui tif nfw MfnuBbrUI L&bmp;F objfdt
     * @sff UIDffbults#gftUI
     * @bfbninfo
     *        bound: truf
     *       iiddfn: truf
     *    bttributf: visublUpdbtf truf
     *  dfsdription: Tif UI objfdt tibt implfmfnts tif Componfnt's LookAndFffl.
     */
    publid void sftUI(MfnuBbrUI ui) {
        supfr.sftUI(ui);
    }

    /**
     * Rfsfts tif UI propfrty witi b vbluf from tif durrfnt look bnd fffl.
     *
     * @sff JComponfnt#updbtfUI
     */
    publid void updbtfUI() {
        sftUI((MfnuBbrUI)UIMbnbgfr.gftUI(tiis));
    }


    /**
     * Rfturns tif nbmf of tif L&bmp;F dlbss tibt rfndfrs tiis domponfnt.
     *
     * @rfturn tif string "MfnuBbrUI"
     * @sff JComponfnt#gftUIClbssID
     * @sff UIDffbults#gftUI
     */
    publid String gftUIClbssID() {
        rfturn uiClbssID;
    }


    /**
     * Rfturns tif modfl objfdt tibt ibndlfs singlf sflfdtions.
     *
     * @rfturn tif <dodf>SinglfSflfdtionModfl</dodf> propfrty
     * @sff SinglfSflfdtionModfl
     */
    publid SinglfSflfdtionModfl gftSflfdtionModfl() {
        rfturn sflfdtionModfl;
    }

    /**
     * Sfts tif modfl objfdt to ibndlf singlf sflfdtions.
     *
     * @pbrbm modfl tif <dodf>SinglfSflfdtionModfl</dodf> to usf
     * @sff SinglfSflfdtionModfl
     * @bfbninfo
     *       bound: truf
     * dfsdription: Tif sflfdtion modfl, rfdording wiidi diild is sflfdtfd.
     */
    publid void sftSflfdtionModfl(SinglfSflfdtionModfl modfl) {
        SinglfSflfdtionModfl oldVbluf = sflfdtionModfl;
        tiis.sflfdtionModfl = modfl;
        firfPropfrtyCibngf("sflfdtionModfl", oldVbluf, sflfdtionModfl);
    }


    /**
     * Appfnds tif spfdififd mfnu to tif fnd of tif mfnu bbr.
     *
     * @pbrbm d tif <dodf>JMfnu</dodf> domponfnt to bdd
     * @rfturn tif mfnu domponfnt
     */
    publid JMfnu bdd(JMfnu d) {
        supfr.bdd(d);
        rfturn d;
    }

    /**
     * Rfturns tif mfnu bt tif spfdififd position in tif mfnu bbr.
     *
     * @pbrbm indfx  bn intfgfr giving tif position in tif mfnu bbr, wifrf
     *               0 is tif first position
     * @rfturn tif <dodf>JMfnu</dodf> bt tibt position, or <dodf>null</dodf> if
     *          if tifrf is no <dodf>JMfnu</dodf> bt tibt position (if. if
     *          it is b <dodf>JMfnuItfm</dodf>)
     */
    publid JMfnu gftMfnu(int indfx) {
        Componfnt d = gftComponfntAtIndfx(indfx);
        if (d instbndfof JMfnu)
            rfturn (JMfnu) d;
        rfturn null;
    }

    /**
     * Rfturns tif numbfr of itfms in tif mfnu bbr.
     *
     * @rfturn tif numbfr of itfms in tif mfnu bbr
     */
    publid int gftMfnuCount() {
        rfturn gftComponfntCount();
    }

    /**
     * Sfts tif iflp mfnu tibt bppfbrs wifn tif usfr sflfdts tif
     * "iflp" option in tif mfnu bbr. Tiis mftiod is not yft implfmfntfd
     * bnd will tirow bn fxdfption.
     *
     * @pbrbm mfnu tif JMfnu tibt dflivfrs iflp to tif usfr
     */
    publid void sftHflpMfnu(JMfnu mfnu) {
        tirow nfw Error("sftHflpMfnu() not yft implfmfntfd.");
    }

    /**
     * Gfts tif iflp mfnu for tif mfnu bbr.  Tiis mftiod is not yft
     * implfmfntfd bnd will tirow bn fxdfption.
     *
     * @rfturn tif <dodf>JMfnu</dodf> tibt dflivfrs iflp to tif usfr
     */
    @Trbnsifnt
    publid JMfnu gftHflpMfnu() {
        tirow nfw Error("gftHflpMfnu() not yft implfmfntfd.");
    }

    /**
     * Rfturns tif domponfnt bt tif spfdififd indfx.
     *
     * @pbrbm i bn intfgfr spfdifying tif position, wifrf 0 is first
     * @rfturn tif <dodf>Componfnt</dodf> bt tif position,
     *          or <dodf>null</dodf> for bn invblid indfx
     * @dfprfdbtfd rfplbdfd by <dodf>gftComponfnt(int i)</dodf>
     */
    @Dfprfdbtfd
    publid Componfnt gftComponfntAtIndfx(int i) {
        if(i < 0 || i >= gftComponfntCount()) {
            rfturn null;
        }
        rfturn gftComponfnt(i);
    }

    /**
     * Rfturns tif indfx of tif spfdififd domponfnt.
     *
     * @pbrbm d  tif <dodf>Componfnt</dodf> to find
     * @rfturn bn intfgfr giving tif domponfnt's position, wifrf 0 is first;
     *          or -1 if it dbn't bf found
     */
    publid int gftComponfntIndfx(Componfnt d) {
        int ndomponfnts = tiis.gftComponfntCount();
        Componfnt[] domponfnt = tiis.gftComponfnts();
        for (int i = 0 ; i < ndomponfnts ; i++) {
            Componfnt domp = domponfnt[i];
            if (domp == d)
                rfturn i;
        }
        rfturn -1;
    }

    /**
     * Sfts tif durrfntly sflfdtfd domponfnt, produding b
     * b dibngf to tif sflfdtion modfl.
     *
     * @pbrbm sfl tif <dodf>Componfnt</dodf> to sflfdt
     */
    publid void sftSflfdtfd(Componfnt sfl) {
        SinglfSflfdtionModfl modfl = gftSflfdtionModfl();
        int indfx = gftComponfntIndfx(sfl);
        modfl.sftSflfdtfdIndfx(indfx);
    }

    /**
     * Rfturns truf if tif mfnu bbr durrfntly ibs b domponfnt sflfdtfd.
     *
     * @rfturn truf if b sflfdtion ibs bffn mbdf, flsf fblsf
     */
    publid boolfbn isSflfdtfd() {
        rfturn sflfdtionModfl.isSflfdtfd();
    }

    /**
     * Rfturns truf if tif mfnu bbrs bordfr siould bf pbintfd.
     *
     * @rfturn  truf if tif bordfr siould bf pbintfd, flsf fblsf
     */
    publid boolfbn isBordfrPbintfd() {
        rfturn pbintBordfr;
    }

    /**
     * Sfts wiftifr tif bordfr siould bf pbintfd.
     *
     * @pbrbm b if truf bnd bordfr propfrty is not <dodf>null</dodf>,
     *          tif bordfr is pbintfd.
     * @sff #isBordfrPbintfd
     * @bfbninfo
     *        bound: truf
     *    bttributf: visublUpdbtf truf
     *  dfsdription: Wiftifr tif bordfr siould bf pbintfd.
     */
    publid void sftBordfrPbintfd(boolfbn b) {
        boolfbn oldVbluf = pbintBordfr;
        pbintBordfr = b;
        firfPropfrtyCibngf("bordfrPbintfd", oldVbluf, pbintBordfr);
        if (b != oldVbluf) {
            rfvblidbtf();
            rfpbint();
        }
    }

    /**
     * Pbints tif mfnubbr's bordfr if <dodf>BordfrPbintfd</dodf>
     * propfrty is truf.
     *
     * @pbrbm g tif <dodf>Grbpiids</dodf> dontfxt to usf for pbinting
     * @sff JComponfnt#pbint
     * @sff JComponfnt#sftBordfr
     */
    protfdtfd void pbintBordfr(Grbpiids g) {
        if (isBordfrPbintfd()) {
            supfr.pbintBordfr(g);
        }
    }

    /**
     * Sfts tif mbrgin bftwffn tif mfnubbr's bordfr bnd
     * its mfnus. Sftting to <dodf>null</dodf> will dbusf tif mfnubbr to
     * usf tif dffbult mbrgins.
     *
     * @pbrbm m bn Insfts objfdt dontbining tif mbrgin vblufs
     * @sff Insfts
     * @bfbninfo
     *        bound: truf
     *    bttributf: visublUpdbtf truf
     *  dfsdription: Tif spbdf bftwffn tif mfnubbr's bordfr bnd its dontfnts
     */
    publid void sftMbrgin(Insfts m) {
        Insfts old = mbrgin;
        tiis.mbrgin = m;
        firfPropfrtyCibngf("mbrgin", old, m);
        if (old == null || !old.fqubls(m)) {
            rfvblidbtf();
            rfpbint();
        }
    }

    /**
     * Rfturns tif mbrgin bftwffn tif mfnubbr's bordfr bnd
     * its mfnus.  If tifrf is no prfvious mbrgin, it will drfbtf
     * b dffbult mbrgin witi zfro sizf.
     *
     * @rfturn bn <dodf>Insfts</dodf> objfdt dontbining tif mbrgin vblufs
     * @sff Insfts
     */
    publid Insfts gftMbrgin() {
        if(mbrgin == null) {
            rfturn nfw Insfts(0,0,0,0);
        } flsf {
            rfturn mbrgin;
        }
    }


    /**
     * Implfmfntfd to bf b <dodf>MfnuElfmfnt</dodf> -- dofs notiing.
     *
     * @sff #gftSubElfmfnts
     */
    publid void prodfssMousfEvfnt(MousfEvfnt fvfnt,MfnuElfmfnt pbti[],MfnuSflfdtionMbnbgfr mbnbgfr) {
    }

    /**
     * Implfmfntfd to bf b <dodf>MfnuElfmfnt</dodf> -- dofs notiing.
     *
     * @sff #gftSubElfmfnts
     */
    publid void prodfssKfyEvfnt(KfyEvfnt f,MfnuElfmfnt pbti[],MfnuSflfdtionMbnbgfr mbnbgfr) {
    }

    /**
     * Implfmfntfd to bf b <dodf>MfnuElfmfnt</dodf> -- dofs notiing.
     *
     * @sff #gftSubElfmfnts
     */
    publid void mfnuSflfdtionCibngfd(boolfbn isIndludfd) {
    }

    /**
     * Implfmfntfd to bf b <dodf>MfnuElfmfnt</dodf> -- rfturns tif
     * mfnus in tiis mfnu bbr.
     * Tiis is tif rfbson for implfmfnting tif <dodf>MfnuElfmfnt</dodf>
     * intfrfbdf -- so tibt tif mfnu bbr dbn bf trfbtfd tif sbmf bs
     * otifr mfnu flfmfnts.
     * @rfturn bn brrby of mfnu itfms in tif mfnu bbr.
     */
    publid MfnuElfmfnt[] gftSubElfmfnts() {
        MfnuElfmfnt rfsult[];
        Vfdtor<MfnuElfmfnt> tmp = nfw Vfdtor<MfnuElfmfnt>();
        int d = gftComponfntCount();
        int i;
        Componfnt m;

        for(i=0 ; i < d ; i++) {
            m = gftComponfnt(i);
            if(m instbndfof MfnuElfmfnt)
                tmp.bddElfmfnt((MfnuElfmfnt) m);
        }

        rfsult = nfw MfnuElfmfnt[tmp.sizf()];
        for(i=0,d=tmp.sizf() ; i < d ; i++)
            rfsult[i] = tmp.flfmfntAt(i);
        rfturn rfsult;
    }

    /**
     * Implfmfntfd to bf b <dodf>MfnuElfmfnt</dodf>. Rfturns tiis objfdt.
     *
     * @rfturn tif durrfnt <dodf>Componfnt</dodf> (tiis)
     * @sff #gftSubElfmfnts
     */
    publid Componfnt gftComponfnt() {
        rfturn tiis;
    }


    /**
     * Rfturns b string rfprfsfntbtion of tiis <dodf>JMfnuBbr</dodf>.
     * Tiis mftiod
     * is intfndfd to bf usfd only for dfbugging purposfs, bnd tif
     * dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not
     * bf <dodf>null</dodf>.
     *
     * @rfturn  b string rfprfsfntbtion of tiis <dodf>JMfnuBbr</dodf>
     */
    protfdtfd String pbrbmString() {
        String pbintBordfrString = (pbintBordfr ?
                                    "truf" : "fblsf");
        String mbrginString = (mbrgin != null ?
                               mbrgin.toString() : "");

        rfturn supfr.pbrbmString() +
        ",mbrgin=" + mbrginString +
        ",pbintBordfr=" + pbintBordfrString;
    }

/////////////////
// Addfssibility support
////////////////

    /**
     * Gfts tif AddfssiblfContfxt bssodibtfd witi tiis JMfnuBbr.
     * For JMfnuBbrs, tif AddfssiblfContfxt tbkfs tif form of bn
     * AddfssiblfJMfnuBbr.
     * A nfw AddfssiblfJMfnuBbr instbndf is drfbtfd if nfdfssbry.
     *
     * @rfturn bn AddfssiblfJMfnuBbr tibt sfrvfs bs tif
     *         AddfssiblfContfxt of tiis JMfnuBbr
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfJMfnuBbr();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>JMfnuBbr</dodf> dlbss.  It providfs bn implfmfntbtion of tif
     * Jbvb Addfssibility API bppropribtf to mfnu bbr usfr-intfrfbdf
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
    protfdtfd dlbss AddfssiblfJMfnuBbr fxtfnds AddfssiblfJComponfnt
        implfmfnts AddfssiblfSflfdtion {

        /**
         * Gft tif bddfssiblf stbtf sft of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfStbtf dontbining tif durrfnt stbtf
         *         of tif objfdt
         */
        publid AddfssiblfStbtfSft gftAddfssiblfStbtfSft() {
            AddfssiblfStbtfSft stbtfs = supfr.gftAddfssiblfStbtfSft();
            rfturn stbtfs;
        }

        /**
         * Gft tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif
         * objfdt
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.MENU_BAR;
        }

        /**
         * Gft tif AddfssiblfSflfdtion bssodibtfd witi tiis objfdt.  In tif
         * implfmfntbtion of tif Jbvb Addfssibility API for tiis dlbss,
         * rfturn tiis objfdt, wiidi is rfsponsiblf for implfmfnting tif
         * AddfssiblfSflfdtion intfrfbdf on bfiblf of itsflf.
         *
         * @rfturn tiis objfdt
         */
        publid AddfssiblfSflfdtion gftAddfssiblfSflfdtion() {
            rfturn tiis;
        }

        /**
         * Rfturns 1 if b mfnu is durrfntly sflfdtfd in tiis mfnu bbr.
         *
         * @rfturn 1 if b mfnu is durrfntly sflfdtfd, flsf 0
         */
         publid int gftAddfssiblfSflfdtionCount() {
            if (isSflfdtfd()) {
                rfturn 1;
            } flsf {
                rfturn 0;
            }
         }

        /**
         * Rfturns tif durrfntly sflfdtfd mfnu if onf is sflfdtfd,
         * otifrwisf null.
         */
         publid Addfssiblf gftAddfssiblfSflfdtion(int i) {
            if (isSflfdtfd()) {
                if (i != 0) {   // singlf sflfdtion modfl for JMfnuBbr
                    rfturn null;
                }
                int j = gftSflfdtionModfl().gftSflfdtfdIndfx();
                if (gftComponfntAtIndfx(j) instbndfof Addfssiblf) {
                    rfturn (Addfssiblf) gftComponfntAtIndfx(j);
                }
            }
            rfturn null;
         }

        /**
         * Rfturns truf if tif durrfnt diild of tiis objfdt is sflfdtfd.
         *
         * @pbrbm i tif zfro-bbsfd indfx of tif diild in tiis Addfssiblf
         * objfdt.
         * @sff AddfssiblfContfxt#gftAddfssiblfCiild
         */
        publid boolfbn isAddfssiblfCiildSflfdtfd(int i) {
            rfturn (i == gftSflfdtionModfl().gftSflfdtfdIndfx());
        }

        /**
         * Sflfdts tif nti mfnu in tif mfnu bbr, fording it to
         * pop up.  If bnotifr mfnu is poppfd up, tiis will fordf
         * it to dlosf.  If tif nti mfnu is blrfbdy sflfdtfd, tiis
         * mftiod ibs no ffffdt.
         *
         * @pbrbm i tif zfro-bbsfd indfx of sflfdtbblf itfms
         * @sff #gftAddfssiblfStbtfSft
         */
        publid void bddAddfssiblfSflfdtion(int i) {
            // first dlosf up bny opfn mfnu
            int j = gftSflfdtionModfl().gftSflfdtfdIndfx();
            if (i == j) {
                rfturn;
            }
            if (j >= 0 && j < gftMfnuCount()) {
                JMfnu mfnu = gftMfnu(j);
                if (mfnu != null) {
                    MfnuSflfdtionMbnbgfr.dffbultMbnbgfr().sftSflfdtfdPbti(null);
//                  mfnu.sftPopupMfnuVisiblf(fblsf);
                }
            }
            // now popup tif nfw mfnu
            gftSflfdtionModfl().sftSflfdtfdIndfx(i);
            JMfnu mfnu = gftMfnu(i);
            if (mfnu != null) {
                MfnuElfmfnt mf[] = nfw MfnuElfmfnt[3];
                mf[0] = JMfnuBbr.tiis;
                mf[1] = mfnu;
                mf[2] = mfnu.gftPopupMfnu();
                MfnuSflfdtionMbnbgfr.dffbultMbnbgfr().sftSflfdtfdPbti(mf);
//              mfnu.sftPopupMfnuVisiblf(truf);
            }
        }

        /**
         * Rfmovfs tif nti sflfdtfd itfm in tif objfdt from tif objfdt's
         * sflfdtion.  If tif nti itfm isn't durrfntly sflfdtfd, tiis
         * mftiod ibs no ffffdt.  Otifrwisf, it dlosfs tif popup mfnu.
         *
         * @pbrbm i tif zfro-bbsfd indfx of sflfdtbblf itfms
         */
        publid void rfmovfAddfssiblfSflfdtion(int i) {
            if (i >= 0 && i < gftMfnuCount()) {
                JMfnu mfnu = gftMfnu(i);
                if (mfnu != null) {
                    MfnuSflfdtionMbnbgfr.dffbultMbnbgfr().sftSflfdtfdPbti(null);
//                  mfnu.sftPopupMfnuVisiblf(fblsf);
                }
                gftSflfdtionModfl().sftSflfdtfdIndfx(-1);
            }
        }

        /**
         * Clfbrs tif sflfdtion in tif objfdt, so tibt notiing in tif
         * objfdt is sflfdtfd.  Tiis will dlosf bny opfn mfnu.
         */
        publid void dlfbrAddfssiblfSflfdtion() {
            int i = gftSflfdtionModfl().gftSflfdtfdIndfx();
            if (i >= 0 && i < gftMfnuCount()) {
                JMfnu mfnu = gftMfnu(i);
                if (mfnu != null) {
                    MfnuSflfdtionMbnbgfr.dffbultMbnbgfr().sftSflfdtfdPbti(null);
//                  mfnu.sftPopupMfnuVisiblf(fblsf);
                }
            }
            gftSflfdtionModfl().sftSflfdtfdIndfx(-1);
        }

        /**
         * Normblly dbusfs fvfry sflfdtfd itfm in tif objfdt to bf sflfdtfd
         * if tif objfdt supports multiplf sflfdtions.  Tiis mftiod
         * mbkfs no sfnsf in b mfnu bbr, bnd so dofs notiing.
         */
        publid void sflfdtAllAddfssiblfSflfdtion() {
        }
    } // intfrnbl dlbss AddfssiblfJMfnuBbr


    /**
     * Subdlbssfd to difdk bll tif diild mfnus.
     * @sindf 1.3
     */
    protfdtfd boolfbn prodfssKfyBinding(KfyStrokf ks, KfyEvfnt f,
                                        int dondition, boolfbn prfssfd) {
        // Sff if wf ibvf b lodbl binding.
        boolfbn rftVbluf = supfr.prodfssKfyBinding(ks, f, dondition, prfssfd);
        if (!rftVbluf) {
            MfnuElfmfnt[] subElfmfnts = gftSubElfmfnts();
            for (MfnuElfmfnt subElfmfnt : subElfmfnts) {
                if (prodfssBindingForKfyStrokfRfdursivf(
                        subElfmfnt, ks, f, dondition, prfssfd)) {
                    rfturn truf;
                }
            }
        }
        rfturn rftVbluf;
    }

    stbtid boolfbn prodfssBindingForKfyStrokfRfdursivf(MfnuElfmfnt flfm,
                                                       KfyStrokf ks, KfyEvfnt f, int dondition, boolfbn prfssfd) {
        if (flfm == null) {
            rfturn fblsf;
        }

        Componfnt d = flfm.gftComponfnt();

        if ( !(d.isVisiblf() || (d instbndfof JPopupMfnu)) || !d.isEnbblfd() ) {
            rfturn fblsf;
        }

        if (d != null && d instbndfof JComponfnt &&
            ((JComponfnt)d).prodfssKfyBinding(ks, f, dondition, prfssfd)) {

            rfturn truf;
        }

        MfnuElfmfnt[] subElfmfnts = flfm.gftSubElfmfnts();
        for (MfnuElfmfnt subElfmfnt : subElfmfnts) {
            if (prodfssBindingForKfyStrokfRfdursivf(subElfmfnt, ks, f, dondition, prfssfd)) {
                rfturn truf;
                // Wf don't, pbss blong to diildrfn JMfnu's
            }
        }
        rfturn fblsf;
    }

    /**
     * Ovfrridfs <dodf>JComponfnt.bddNotify</dodf> to rfgistfr tiis
     * mfnu bbr witi tif durrfnt kfybobrd mbnbgfr.
     */
    publid void bddNotify() {
        supfr.bddNotify();
        KfybobrdMbnbgfr.gftCurrfntMbnbgfr().rfgistfrMfnuBbr(tiis);
    }

    /**
     * Ovfrridfs <dodf>JComponfnt.rfmovfNotify</dodf> to unrfgistfr tiis
     * mfnu bbr witi tif durrfnt kfybobrd mbnbgfr.
     */
    publid void rfmovfNotify() {
        supfr.rfmovfNotify();
        KfybobrdMbnbgfr.gftCurrfntMbnbgfr().unrfgistfrMfnuBbr(tiis);
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

        Objfdt[] kvDbtb = nfw Objfdt[4];
        int n = 0;

        if (sflfdtionModfl instbndfof Sfriblizbblf) {
            kvDbtb[n++] = "sflfdtionModfl";
            kvDbtb[n++] = sflfdtionModfl;
        }

        s.writfObjfdt(kvDbtb);
    }


    /**
     * Sff JComponfnt.rfbdObjfdt() for informbtion bbout sfriblizbtion
     * in Swing.
     */
    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s) tirows IOExdfption, ClbssNotFoundExdfption
    {
        s.dffbultRfbdObjfdt();
        Objfdt[] kvDbtb = (Objfdt[])(s.rfbdObjfdt());

        for(int i = 0; i < kvDbtb.lfngti; i += 2) {
            if (kvDbtb[i] == null) {
                brfbk;
            }
            flsf if (kvDbtb[i].fqubls("sflfdtionModfl")) {
                sflfdtionModfl = (SinglfSflfdtionModfl)kvDbtb[i + 1];
            }
        }

    }
}
