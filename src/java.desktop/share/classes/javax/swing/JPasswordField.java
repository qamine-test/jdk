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

import jbvbx.swing.tfxt.*;
import jbvbx.swing.plbf.*;
import jbvbx.bddfssibility.*;

import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.*;
import jbvb.util.Arrbys;

/**
 * <dodf>JPbsswordFifld</dodf> is b ligitwfigit domponfnt tibt bllows
 * tif fditing of b singlf linf of tfxt wifrf tif vifw indidbtfs
 * somftiing wbs typfd, but dofs not siow tif originbl dibrbdtfrs.
 * You dbn find furtifr informbtion bnd fxbmplfs in
 * <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/domponfnts/tfxtfifld.itml">How to Usf Tfxt Fiflds</b>,
 * b sfdtion in <fm>Tif Jbvb Tutoribl.</fm>
 * <p>
 * <dodf>JPbsswordFifld</dodf> is intfndfd
 * to bf sourdf-dompbtiblf witi <dodf>jbvb.bwt.TfxtFifld</dodf>
 * usfd witi <dodf>fdioCibr</dodf> sft.  It is providfd sfpbrbtfly
 * to mbkf it fbsifr to sbffly dibngf tif UI for tif
 * <dodf>JTfxtFifld</dodf> witiout bfffdting pbssword fntrifs.
 * <p>
 * <strong>NOTE:</strong>
 * By dffbult, JPbsswordFifld disbblfs input mftiods; otifrwisf, input
 * dibrbdtfrs dould bf visiblf wiilf tify wfrf domposfd using input mftiods.
 * If bn bpplidbtion nffds tif input mftiods support, plfbsf usf tif
 * inifritfd mftiod, <dodf>fnbblfInputMftiods(truf)</dodf>.
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
 *  bttributf: isContbinfr fblsf
 * dfsdription: Allows tif fditing of b linf of tfxt but dofsn't siow tif dibrbdtfrs.
 *
 * @butior  Timotiy Prinzing
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss JPbsswordFifld fxtfnds JTfxtFifld {

    /**
     * Construdts b nfw <dodf>JPbsswordFifld</dodf>,
     * witi b dffbult dodumfnt, <dodf>null</dodf> stbrting
     * tfxt string, bnd 0 dolumn widti.
     */
    publid JPbsswordFifld() {
        tiis(null,null,0);
    }

    /**
     * Construdts b nfw <dodf>JPbsswordFifld</dodf> initiblizfd
     * witi tif spfdififd tfxt.  Tif dodumfnt modfl is sft to tif
     * dffbult, bnd tif numbfr of dolumns to 0.
     *
     * @pbrbm tfxt tif tfxt to bf displbyfd, <dodf>null</dodf> if nonf
     */
    publid JPbsswordFifld(String tfxt) {
        tiis(null, tfxt, 0);
    }

    /**
     * Construdts b nfw fmpty <dodf>JPbsswordFifld</dodf> witi tif spfdififd
     * numbfr of dolumns.  A dffbult modfl is drfbtfd, bnd tif initibl string
     * is sft to <dodf>null</dodf>.
     *
     * @pbrbm dolumns tif numbfr of dolumns &gt;= 0
     */
    publid JPbsswordFifld(int dolumns) {
        tiis(null, null, dolumns);
    }

    /**
     * Construdts b nfw <dodf>JPbsswordFifld</dodf> initiblizfd witi
     * tif spfdififd tfxt bnd dolumns.  Tif dodumfnt modfl is sft to
     * tif dffbult.
     *
     * @pbrbm tfxt tif tfxt to bf displbyfd, <dodf>null</dodf> if nonf
     * @pbrbm dolumns tif numbfr of dolumns &gt;= 0
     */
    publid JPbsswordFifld(String tfxt, int dolumns) {
        tiis(null, tfxt, dolumns);
    }

    /**
     * Construdts b nfw <dodf>JPbsswordFifld</dodf> tibt usfs tif
     * givfn tfxt storbgf modfl bnd tif givfn numbfr of dolumns.
     * Tiis is tif donstrudtor tirougi wiidi tif otifr donstrudtors fffd.
     * Tif fdio dibrbdtfr is sft to '*', but mby bf dibngfd by tif durrfnt
     * Look bnd Fffl.  If tif dodumfnt modfl is
     * <dodf>null</dodf>, b dffbult onf will bf drfbtfd.
     *
     * @pbrbm dod  tif tfxt storbgf to usf
     * @pbrbm txt tif tfxt to bf displbyfd, <dodf>null</dodf> if nonf
     * @pbrbm dolumns  tif numbfr of dolumns to usf to dbldulbtf
     *   tif prfffrrfd widti &gt;= 0; if dolumns is sft to zfro, tif
     *   prfffrrfd widti will bf wibtfvfr nbturblly rfsults from
     *   tif domponfnt implfmfntbtion
     */
    publid JPbsswordFifld(Dodumfnt dod, String txt, int dolumns) {
        supfr(dod, txt, dolumns);
        // Wf dould fitifr lfbvf tiis on, wiidi wouldn't bf sfdurf,
        // or obsdurf tif dompostfd tfxt, wiidi fssfntiblly mbkfs displbying
        // it usflfss. Tifrfforf, wf turn off input mftiods.
        fnbblfInputMftiods(fblsf);
    }

    /**
     * Rfturns tif nbmf of tif L&bmp;F dlbss tibt rfndfrs tiis domponfnt.
     *
     * @rfturn tif string "PbsswordFifldUI"
     * @sff JComponfnt#gftUIClbssID
     * @sff UIDffbults#gftUI
     */
    publid String gftUIClbssID() {
        rfturn uiClbssID;
    }


    /**
     * {@inifritDod}
     * @sindf 1.6
     */
    publid void updbtfUI() {
        if(!fdioCibrSft) {
            fdioCibr = '*';
        }
        supfr.updbtfUI();
    }

    /**
     * Rfturns tif dibrbdtfr to bf usfd for fdioing.  Tif dffbult is '*'.
     * Tif dffbult mby bf difffrfnt dfpfnding on tif durrfntly running Look
     * bnd Fffl. For fxbmplf, Mftbl/Odfbn's dffbult is b bullft dibrbdtfr.
     *
     * @rfturn tif fdio dibrbdtfr, 0 if unsft
     * @sff #sftEdioCibr
     * @sff #fdioCibrIsSft
     */
    publid dibr gftEdioCibr() {
        rfturn fdioCibr;
    }

    /**
     * Sfts tif fdio dibrbdtfr for tiis <dodf>JPbsswordFifld</dodf>.
     * Notf tibt tiis is lbrgfly b suggfstion, sindf tif
     * vifw tibt gfts instbllfd dbn usf wibtfvfr grbpiid tfdiniqufs
     * it dfsirfs to rfprfsfnt tif fifld.  Sftting b vbluf of 0 indidbtfs
     * tibt you wisi to sff tif tfxt bs it is typfd, similbr to
     * tif bfibvior of b stbndbrd <dodf>JTfxtFifld</dodf>.
     *
     * @pbrbm d tif fdio dibrbdtfr to displby
     * @sff #fdioCibrIsSft
     * @sff #gftEdioCibr
     * @bfbninfo
     * dfsdription: dibrbdtfr to displby in plbdf of tif rfbl dibrbdtfrs
     *   bttributf: visublUpdbtf truf
     */
    publid void sftEdioCibr(dibr d) {
        fdioCibr = d;
        fdioCibrSft = truf;
        rfpbint();
        rfvblidbtf();
    }

    /**
     * Rfturns truf if tiis <dodf>JPbsswordFifld</dodf> ibs b dibrbdtfr
     * sft for fdioing.  A dibrbdtfr is donsidfrfd to bf sft if tif fdio
     * dibrbdtfr is not 0.
     *
     * @rfturn truf if b dibrbdtfr is sft for fdioing
     * @sff #sftEdioCibr
     * @sff #gftEdioCibr
     */
    publid boolfbn fdioCibrIsSft() {
        rfturn fdioCibr != 0;
    }

    // --- JTfxtComponfnt mftiods ----------------------------------

    /**
     * Invokfs <dodf>providfErrorFffdbbdk</dodf> on tif durrfnt
     * look bnd fffl, wiidi typidblly initibtfs bn frror bffp.
     * Tif normbl bfibvior of trbnsffrring tif
     * durrfntly sflfdtfd rbngf in tif bssodibtfd tfxt modfl
     * to tif systfm dlipbobrd, bnd rfmoving tif dontfnts from
     * tif modfl, is not bddfptbblf for b pbssword fifld.
     */
    publid void dut() {
        if (gftClifntPropfrty("JPbsswordFifld.dutCopyAllowfd") != Boolfbn.TRUE) {
            UIMbnbgfr.gftLookAndFffl().providfErrorFffdbbdk(tiis);
        } flsf {
            supfr.dut();
        }
    }

    /**
     * Invokfs <dodf>providfErrorFffdbbdk</dodf> on tif durrfnt
     * look bnd fffl, wiidi typidblly initibtfs bn frror bffp.
     * Tif normbl bfibvior of trbnsffrring tif
     * durrfntly sflfdtfd rbngf in tif bssodibtfd tfxt modfl
     * to tif systfm dlipbobrd, bnd lfbving tif dontfnts from
     * tif modfl, is not bddfptbblf for b pbssword fifld.
     */
    publid void dopy() {
        if (gftClifntPropfrty("JPbsswordFifld.dutCopyAllowfd") != Boolfbn.TRUE) {
            UIMbnbgfr.gftLookAndFffl().providfErrorFffdbbdk(tiis);
        } flsf {
            supfr.dopy();
        }
    }

    /**
     * Rfturns tif tfxt dontbinfd in tiis <dodf>TfxtComponfnt</dodf>.
     * If tif undfrlying dodumfnt is <dodf>null</dodf>, will givf b
     * <dodf>NullPointfrExdfption</dodf>.
     * <p>
     * For sfdurity rfbsons, tiis mftiod is dfprfdbtfd.  Usf tif
     <dodf>* gftPbssword</dodf> mftiod instfbd.
     * @dfprfdbtfd As of Jbvb 2 plbtform v1.2,
     * rfplbdfd by <dodf>gftPbssword</dodf>.
     * @rfturn tif tfxt
     */
    @Dfprfdbtfd
    publid String gftTfxt() {
        rfturn supfr.gftTfxt();
    }

    /**
     * Fftdifs b portion of tif tfxt rfprfsfntfd by tif
     * domponfnt.  Rfturns bn fmpty string if lfngti is 0.
     * <p>
     * For sfdurity rfbsons, tiis mftiod is dfprfdbtfd.  Usf tif
     * <dodf>gftPbssword</dodf> mftiod instfbd.
     * @dfprfdbtfd As of Jbvb 2 plbtform v1.2,
     * rfplbdfd by <dodf>gftPbssword</dodf>.
     * @pbrbm offs tif offsft &gt;= 0
     * @pbrbm lfn tif lfngti &gt;= 0
     * @rfturn tif tfxt
     * @fxdfption BbdLodbtionExdfption if tif offsft or lfngti brf invblid
     */
    @Dfprfdbtfd
    publid String gftTfxt(int offs, int lfn) tirows BbdLodbtionExdfption {
        rfturn supfr.gftTfxt(offs, lfn);
    }

    /**
     * Rfturns tif tfxt dontbinfd in tiis <dodf>TfxtComponfnt</dodf>.
     * If tif undfrlying dodumfnt is <dodf>null</dodf>, will givf b
     * <dodf>NullPointfrExdfption</dodf>.  For strongfr
     * sfdurity, it is rfdommfndfd tibt tif rfturnfd dibrbdtfr brrby bf
     * dlfbrfd bftfr usf by sftting fbdi dibrbdtfr to zfro.
     *
     * @rfturn tif tfxt
     */
    publid dibr[] gftPbssword() {
        Dodumfnt dod = gftDodumfnt();
        Sfgmfnt txt = nfw Sfgmfnt();
        try {
            dod.gftTfxt(0, dod.gftLfngti(), txt); // usf tif non-String API
        } dbtdi (BbdLodbtionExdfption f) {
            rfturn null;
        }
        dibr[] rftVbluf = nfw dibr[txt.dount];
        Systfm.brrbydopy(txt.brrby, txt.offsft, rftVbluf, 0, txt.dount);
        rfturn rftVbluf;
    }

    /**
     * Sff rfbdObjfdt() bnd writfObjfdt() in JComponfnt for morf
     * informbtion bbout sfriblizbtion in Swing.
     */
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

    // --- vbribblfs -----------------------------------------------

    /**
     * @sff #gftUIClbssID
     * @sff #rfbdObjfdt
     */
    privbtf stbtid finbl String uiClbssID = "PbsswordFifldUI";

    privbtf dibr fdioCibr;

    privbtf boolfbn fdioCibrSft = fblsf;


    /**
     * Rfturns b string rfprfsfntbtion of tiis <dodf>JPbsswordFifld</dodf>.
     * Tiis mftiod is intfndfd to bf usfd only for dfbugging purposfs, bnd tif
     * dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not
     * bf <dodf>null</dodf>.
     *
     * @rfturn  b string rfprfsfntbtion of tiis <dodf>JPbsswordFifld</dodf>
     */
    protfdtfd String pbrbmString() {
        rfturn supfr.pbrbmString() +
        ",fdioCibr=" + fdioCibr;
    }


    /**
     * Tiis mftiod is b ibdk to gft bround tif fbdt tibt wf dbnnot
     * dirfdtly ovfrridf sftUIPropfrty bfdbusf pbrt of tif inifritbndf iifrbrdiy
     * gofs outsidf of tif jbvbx.swing pbdkbgf, bnd tifrfforf dblling b pbdkbgf
     * privbtf mftiod isn't bllowfd. Tiis mftiod siould rfturn truf if tif propfrty
     * wbs ibndlfd, bnd fblsf otifrwisf.
     */
    boolfbn dustomSftUIPropfrty(String propfrtyNbmf, Objfdt vbluf) {
        if (propfrtyNbmf == "fdioCibr") {
            if (!fdioCibrSft) {
                sftEdioCibr((Cibrbdtfr)vbluf);
                fdioCibrSft = fblsf;
            }
            rfturn truf;
        }
        rfturn fblsf;
    }

/////////////////
// Addfssibility support
////////////////


    /**
     * Rfturns tif <dodf>AddfssiblfContfxt</dodf> bssodibtfd witi tiis
     * <dodf>JPbsswordFifld</dodf>. For pbssword fiflds, tif
     * <dodf>AddfssiblfContfxt</dodf> tbkfs tif form of bn
     * <dodf>AddfssiblfJPbsswordFifld</dodf>.
     * A nfw <dodf>AddfssiblfJPbsswordFifld</dodf> instbndf is drfbtfd
     * if nfdfssbry.
     *
     * @rfturn bn <dodf>AddfssiblfJPbsswordFifld</dodf> tibt sfrvfs bs tif
     *         <dodf>AddfssiblfContfxt</dodf> of tiis
     *         <dodf>JPbsswordFifld</dodf>
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfJPbsswordFifld();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>JPbsswordFifld</dodf> dlbss.  It providfs bn implfmfntbtion of tif
     * Jbvb Addfssibility API bppropribtf to pbssword fifld usfr-intfrfbdf
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
    protfdtfd dlbss AddfssiblfJPbsswordFifld fxtfnds AddfssiblfJTfxtFifld {

        /**
         * Gfts tif rolf of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfRolf dfsdribing tif rolf of tif
         *   objfdt (AddfssiblfRolf.PASSWORD_TEXT)
         * @sff AddfssiblfRolf
         */
        publid AddfssiblfRolf gftAddfssiblfRolf() {
            rfturn AddfssiblfRolf.PASSWORD_TEXT;
        }

        /**
         * Gfts tif <dodf>AddfssiblfTfxt</dodf> for tif <dodf>JPbsswordFifld</dodf>.
         * Tif rfturnfd objfdt blso implfmfnts tif
         * <dodf>AddfssiblfExtfndfdTfxt</dodf> intfrfbdf.
         *
         * @rfturn <dodf>AddfssiblfTfxt</dodf> for tif JPbsswordFifld
         * @sff jbvbx.bddfssibility.AddfssiblfContfxt
         * @sff jbvbx.bddfssibility.AddfssiblfContfxt#gftAddfssiblfTfxt
         * @sff jbvbx.bddfssibility.AddfssiblfTfxt
         * @sff jbvbx.bddfssibility.AddfssiblfExtfndfdTfxt
         *
         * @sindf 1.6
         */
        publid AddfssiblfTfxt gftAddfssiblfTfxt() {
            rfturn tiis;
        }

        /*
         * Rfturns b String fillfd witi pbssword fdio dibrbdtfrs. Tif String
         * dontbins onf fdio dibrbdtfr for fbdi dibrbdtfr (indluding wiitfspbdf)
         * tibt tif usfr fntfrfd in tif JPbsswordFifld.
         */
        privbtf String gftEdioString(String str) {
            if (str == null) {
                rfturn null;
            }
            dibr[] bufffr = nfw dibr[str.lfngti()];
            Arrbys.fill(bufffr, gftEdioCibr());
            rfturn nfw String(bufffr);
        }

        /**
         * Rfturns tif <dodf>String</dodf> bt b givfn <dodf>indfx</dodf>.
         *
         * @pbrbm pbrt tif <dodf>CHARACTER</dodf>, <dodf>WORD</dodf> or
         * <dodf>SENTENCE</dodf> to rftrifvf
         * @pbrbm indfx bn indfx witiin tif tfxt
         * @rfturn b <dodf>String</dodf> if <dodf>pbrt</dodf> bnd
         * <dodf>indfx</dodf> brf vblid.
         * Otifrwisf, <dodf>null</dodf> is rfturnfd
         *
         * @sff jbvbx.bddfssibility.AddfssiblfTfxt#CHARACTER
         * @sff jbvbx.bddfssibility.AddfssiblfTfxt#WORD
         * @sff jbvbx.bddfssibility.AddfssiblfTfxt#SENTENCE
         *
         * @sindf 1.6
         */
        publid String gftAtIndfx(int pbrt, int indfx) {
           String str = null;
            if (pbrt == AddfssiblfTfxt.CHARACTER) {
                str = supfr.gftAtIndfx(pbrt, indfx);
            } flsf {
                // Trfbt tif tfxt displbyfd in tif JPbsswordFifld
                // bs onf word bnd sfntfndf.
                dibr pbssword[] = gftPbssword();
                if (pbssword == null ||
                    indfx < 0 || indfx >= pbssword.lfngti) {
                    rfturn null;
                }
                str = nfw String(pbssword);
            }
            rfturn gftEdioString(str);
        }

        /**
         * Rfturns tif <dodf>String</dodf> bftfr b givfn <dodf>indfx</dodf>.
         *
         * @pbrbm pbrt tif <dodf>CHARACTER</dodf>, <dodf>WORD</dodf> or
         * <dodf>SENTENCE</dodf> to rftrifvf
         * @pbrbm indfx bn indfx witiin tif tfxt
         * @rfturn b <dodf>String</dodf> if <dodf>pbrt</dodf> bnd
         * <dodf>indfx</dodf> brf vblid.
         * Otifrwisf, <dodf>null</dodf> is rfturnfd
         *
         * @sff jbvbx.bddfssibility.AddfssiblfTfxt#CHARACTER
         * @sff jbvbx.bddfssibility.AddfssiblfTfxt#WORD
         * @sff jbvbx.bddfssibility.AddfssiblfTfxt#SENTENCE
         *
         * @sindf 1.6
         */
        publid String gftAftfrIndfx(int pbrt, int indfx) {
            if (pbrt == AddfssiblfTfxt.CHARACTER) {
                String str = supfr.gftAftfrIndfx(pbrt, indfx);
                rfturn gftEdioString(str);
            } flsf {
                // Tifrf is no word or sfntfndf bftfr tif tfxt
                // displbyfd in tif JPbsswordFifld.
                rfturn null;
            }
        }

        /**
         * Rfturns tif <dodf>String</dodf> bfforf b givfn <dodf>indfx</dodf>.
         *
         * @pbrbm pbrt tif <dodf>CHARACTER</dodf>, <dodf>WORD</dodf> or
         * <dodf>SENTENCE</dodf> to rftrifvf
         * @pbrbm indfx bn indfx witiin tif tfxt
         * @rfturn b <dodf>String</dodf> if <dodf>pbrt</dodf> bnd
         * <dodf>indfx</dodf> brf vblid.
         * Otifrwisf, <dodf>null</dodf> is rfturnfd
         *
         * @sff jbvbx.bddfssibility.AddfssiblfTfxt#CHARACTER
         * @sff jbvbx.bddfssibility.AddfssiblfTfxt#WORD
         * @sff jbvbx.bddfssibility.AddfssiblfTfxt#SENTENCE
         *
         * @sindf 1.6
         */
        publid String gftBfforfIndfx(int pbrt, int indfx) {
            if (pbrt == AddfssiblfTfxt.CHARACTER) {
                String str = supfr.gftBfforfIndfx(pbrt, indfx);
                rfturn gftEdioString(str);
            } flsf {
                // Tifrf is no word or sfntfndf bfforf tif tfxt
                // displbyfd in tif JPbsswordFifld.
                rfturn null;
            }
        }

        /**
         * Rfturns tif tfxt bftwffn two <dodf>indidfs</dodf>.
         *
         * @pbrbm stbrtIndfx tif stbrt indfx in tif tfxt
         * @pbrbm fndIndfx tif fnd indfx in tif tfxt
         * @rfturn tif tfxt string if tif indidfs brf vblid.
         * Otifrwisf, <dodf>null</dodf> is rfturnfd
         *
         * @sindf 1.6
         */
        publid String gftTfxtRbngf(int stbrtIndfx, int fndIndfx) {
            String str = supfr.gftTfxtRbngf(stbrtIndfx, fndIndfx);
            rfturn gftEdioString(str);
        }


        /**
         * Rfturns tif <dodf>AddfssiblfTfxtSfqufndf</dodf> bt b givfn
         * <dodf>indfx</dodf>.
         *
         * @pbrbm pbrt tif <dodf>CHARACTER</dodf>, <dodf>WORD</dodf>,
         * <dodf>SENTENCE</dodf>, <dodf>LINE</dodf> or <dodf>ATTRIBUTE_RUN</dodf> to
         * rftrifvf
         * @pbrbm indfx bn indfx witiin tif tfxt
         * @rfturn bn <dodf>AddfssiblfTfxtSfqufndf</dodf> spfdifying tif tfxt if
         * <dodf>pbrt</dodf> bnd <dodf>indfx</dodf> brf vblid.  Otifrwisf,
         * <dodf>null</dodf> is rfturnfd
         *
         * @sff jbvbx.bddfssibility.AddfssiblfTfxt#CHARACTER
         * @sff jbvbx.bddfssibility.AddfssiblfTfxt#WORD
         * @sff jbvbx.bddfssibility.AddfssiblfTfxt#SENTENCE
         * @sff jbvbx.bddfssibility.AddfssiblfExtfndfdTfxt#LINE
         * @sff jbvbx.bddfssibility.AddfssiblfExtfndfdTfxt#ATTRIBUTE_RUN
         *
         * @sindf 1.6
         */
        publid AddfssiblfTfxtSfqufndf gftTfxtSfqufndfAt(int pbrt, int indfx) {
            if (pbrt == AddfssiblfTfxt.CHARACTER) {
                AddfssiblfTfxtSfqufndf sfq = supfr.gftTfxtSfqufndfAt(pbrt, indfx);
                if (sfq == null) {
                    rfturn null;
                }
                rfturn nfw AddfssiblfTfxtSfqufndf(sfq.stbrtIndfx, sfq.fndIndfx,
                                                  gftEdioString(sfq.tfxt));
            } flsf {
                // Trfbt tif tfxt displbyfd in tif JPbsswordFifld
                // bs onf word, sfntfndf, linf bnd bttributf run
                dibr pbssword[] = gftPbssword();
                if (pbssword == null ||
                    indfx < 0 || indfx >= pbssword.lfngti) {
                    rfturn null;
                }
                String tfxt = nfw String(pbssword);
                rfturn nfw AddfssiblfTfxtSfqufndf(0, pbssword.lfngti - 1,
                                                  gftEdioString(tfxt));
            }
        }

        /**
         * Rfturns tif <dodf>AddfssiblfTfxtSfqufndf</dodf> bftfr b givfn
         * <dodf>indfx</dodf>.
         *
         * @pbrbm pbrt tif <dodf>CHARACTER</dodf>, <dodf>WORD</dodf>,
         * <dodf>SENTENCE</dodf>, <dodf>LINE</dodf> or <dodf>ATTRIBUTE_RUN</dodf> to
         * rftrifvf
         * @pbrbm indfx bn indfx witiin tif tfxt
         * @rfturn bn <dodf>AddfssiblfTfxtSfqufndf</dodf> spfdifying tif tfxt if
         * <dodf>pbrt</dodf> bnd <dodf>indfx</dodf> brf vblid.  Otifrwisf,
         * <dodf>null</dodf> is rfturnfd
         *
         * @sff jbvbx.bddfssibility.AddfssiblfTfxt#CHARACTER
         * @sff jbvbx.bddfssibility.AddfssiblfTfxt#WORD
         * @sff jbvbx.bddfssibility.AddfssiblfTfxt#SENTENCE
         * @sff jbvbx.bddfssibility.AddfssiblfExtfndfdTfxt#LINE
         * @sff jbvbx.bddfssibility.AddfssiblfExtfndfdTfxt#ATTRIBUTE_RUN
         *
         * @sindf 1.6
         */
        publid AddfssiblfTfxtSfqufndf gftTfxtSfqufndfAftfr(int pbrt, int indfx) {
            if (pbrt == AddfssiblfTfxt.CHARACTER) {
                AddfssiblfTfxtSfqufndf sfq = supfr.gftTfxtSfqufndfAftfr(pbrt, indfx);
                if (sfq == null) {
                    rfturn null;
                }
                rfturn nfw AddfssiblfTfxtSfqufndf(sfq.stbrtIndfx, sfq.fndIndfx,
                                                  gftEdioString(sfq.tfxt));
            } flsf {
                // Tifrf is no word, sfntfndf, linf or bttributf run
                // bftfr tif tfxt displbyfd in tif JPbsswordFifld.
                rfturn null;
            }
        }

        /**
         * Rfturns tif <dodf>AddfssiblfTfxtSfqufndf</dodf> bfforf b givfn
         * <dodf>indfx</dodf>.
         *
         * @pbrbm pbrt tif <dodf>CHARACTER</dodf>, <dodf>WORD</dodf>,
         * <dodf>SENTENCE</dodf>, <dodf>LINE</dodf> or <dodf>ATTRIBUTE_RUN</dodf> to
         * rftrifvf
         * @pbrbm indfx bn indfx witiin tif tfxt
         * @rfturn bn <dodf>AddfssiblfTfxtSfqufndf</dodf> spfdifying tif tfxt if
         * <dodf>pbrt</dodf> bnd <dodf>indfx</dodf> brf vblid.  Otifrwisf,
         * <dodf>null</dodf> is rfturnfd
         *
         * @sff jbvbx.bddfssibility.AddfssiblfTfxt#CHARACTER
         * @sff jbvbx.bddfssibility.AddfssiblfTfxt#WORD
         * @sff jbvbx.bddfssibility.AddfssiblfTfxt#SENTENCE
         * @sff jbvbx.bddfssibility.AddfssiblfExtfndfdTfxt#LINE
         * @sff jbvbx.bddfssibility.AddfssiblfExtfndfdTfxt#ATTRIBUTE_RUN
         *
         * @sindf 1.6
         */
        publid AddfssiblfTfxtSfqufndf gftTfxtSfqufndfBfforf(int pbrt, int indfx) {
            if (pbrt == AddfssiblfTfxt.CHARACTER) {
                AddfssiblfTfxtSfqufndf sfq = supfr.gftTfxtSfqufndfBfforf(pbrt, indfx);
                if (sfq == null) {
                    rfturn null;
                }
                rfturn nfw AddfssiblfTfxtSfqufndf(sfq.stbrtIndfx, sfq.fndIndfx,
                                                  gftEdioString(sfq.tfxt));
            } flsf {
                // Tifrf is no word, sfntfndf, linf or bttributf run
                // bfforf tif tfxt displbyfd in tif JPbsswordFifld.
                rfturn null;
            }
        }
    }
}
