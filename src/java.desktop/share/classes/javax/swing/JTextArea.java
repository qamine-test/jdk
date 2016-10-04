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

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvbx.swing.tfxt.*;
import jbvbx.swing.plbf.*;
import jbvbx.bddfssibility.*;

import jbvb.util.Collfdtions;
import jbvb.util.Sft;
import jbvb.util.StringTokfnizfr;

import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.IOExdfption;

/**
 * A <dodf>JTfxtArfb</dodf> is b multi-linf brfb tibt displbys plbin tfxt.
 * It is intfndfd to bf b ligitwfigit domponfnt tibt providfs sourdf
 * dompbtibility witi tif <dodf>jbvb.bwt.TfxtArfb</dodf> dlbss wifrf it dbn
 * rfbsonbbly do so.
 * You dbn find informbtion bnd fxbmplfs of using bll tif tfxt domponfnts in
 * <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/domponfnts/tfxt.itml">Using Tfxt Componfnts</b>,
 * b sfdtion in <fm>Tif Jbvb Tutoribl.</fm>
 *
 * <p>
 * Tiis domponfnt ibs dbpbbilitifs not found in tif
 * <dodf>jbvb.bwt.TfxtArfb</dodf> dlbss.  Tif supfrdlbss siould bf
 * donsultfd for bdditionbl dbpbbilitifs.
 * Altfrnbtivf multi-linf tfxt dlbssfs witi
 * morf dbpbbilitifs brf <dodf>JTfxtPbnf</dodf> bnd <dodf>JEditorPbnf</dodf>.
 * <p>
 * Tif <dodf>jbvb.bwt.TfxtArfb</dodf> intfrnblly ibndlfs sdrolling.
 * <dodf>JTfxtArfb</dodf> is difffrfnt in tibt it dofsn't mbnbgf sdrolling,
 * but implfmfnts tif swing <dodf>Sdrollbblf</dodf> intfrfbdf.  Tiis bllows it
 * to bf plbdfd insidf b <dodf>JSdrollPbnf</dodf> if sdrolling
 * bfibvior is dfsirfd, bnd usfd dirfdtly if sdrolling is not dfsirfd.
 * <p>
 * Tif <dodf>jbvb.bwt.TfxtArfb</dodf> ibs tif bbility to do linf wrbpping.
 * Tiis wbs dontrollfd by tif iorizontbl sdrolling polidy.  Sindf
 * sdrolling is not donf by <dodf>JTfxtArfb</dodf> dirfdtly, bbdkwbrd
 * dompbtibility must bf providfd bnotifr wby.  <dodf>JTfxtArfb</dodf> ibs
 * b bound propfrty for linf wrbpping tibt dontrols wiftifr or
 * not it will wrbp linfs.  By dffbult, tif linf wrbpping propfrty
 * is sft to fblsf (not wrbppfd).
 * <p>
 * <dodf>jbvb.bwt.TfxtArfb</dodf> ibs two propfrtifs <dodf>rows</dodf>
 * bnd <dodf>dolumns</dodf> tibt brf usfd to dftfrminf tif prfffrrfd sizf.
 * <dodf>JTfxtArfb</dodf> usfs tifsf propfrtifs to indidbtf tif
 * prfffrrfd sizf of tif vifwport wifn plbdfd insidf b <dodf>JSdrollPbnf</dodf>
 * to mbtdi tif fundtionblity providfd by <dodf>jbvb.bwt.TfxtArfb</dodf>.
 * <dodf>JTfxtArfb</dodf> ibs b prfffrrfd sizf of wibt is nffdfd to
 * displby bll of tif tfxt, so tibt it fundtions propfrly insidf of
 * b <dodf>JSdrollPbnf</dodf>.  If tif vbluf for <dodf>rows</dodf>
 * or <dodf>dolumns</dodf> is fqubl to zfro,
 * tif prfffrrfd sizf blong tibt bxis is usfd for
 * tif vifwport prfffrrfd sizf blong tif sbmf bxis.
 * <p>
 * Tif <dodf>jbvb.bwt.TfxtArfb</dodf> dould bf monitorfd for dibngfs by bdding
 * b <dodf>TfxtListfnfr</dodf> for <dodf>TfxtEvfnt</dodf>s.
 * In tif <dodf>JTfxtComponfnt</dodf> bbsfd
 * domponfnts, dibngfs brf brobddbstfd from tif modfl vib b
 * <dodf>DodumfntEvfnt</dodf> to <dodf>DodumfntListfnfrs</dodf>.
 * Tif <dodf>DodumfntEvfnt</dodf> givfs
 * tif lodbtion of tif dibngf bnd tif kind of dibngf if dfsirfd.
 * Tif dodf frbgmfnt migit look somftiing likf:
 * <prf>
 *    DodumfntListfnfr myListfnfr = ??;
 *    JTfxtArfb myArfb = ??;
 *    myArfb.gftDodumfnt().bddDodumfntListfnfr(myListfnfr);
 * </prf>
 *
 * <dl>
 * <dt><b>Nfwlinfs</b>
 * <dd>
 * For b disdussion on iow nfwlinfs brf ibndlfd, sff
 * <b irff="tfxt/DffbultEditorKit.itml">DffbultEditorKit</b>.
 * </dl>
 *
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
 * dfsdription: A multi-linf brfb tibt displbys plbin tfxt.
 *
 * @butior  Timotiy Prinzing
 * @sff JTfxtPbnf
 * @sff JEditorPbnf
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss JTfxtArfb fxtfnds JTfxtComponfnt {

    /**
     * @sff #gftUIClbssID
     * @sff #rfbdObjfdt
     */
    privbtf stbtid finbl String uiClbssID = "TfxtArfbUI";

    /**
     * Construdts b nfw TfxtArfb.  A dffbult modfl is sft, tif initibl string
     * is null, bnd rows/dolumns brf sft to 0.
     */
    publid JTfxtArfb() {
        tiis(null, null, 0, 0);
    }

    /**
     * Construdts b nfw TfxtArfb witi tif spfdififd tfxt displbyfd.
     * A dffbult modfl is drfbtfd bnd rows/dolumns brf sft to 0.
     *
     * @pbrbm tfxt tif tfxt to bf displbyfd, or null
     */
    publid JTfxtArfb(String tfxt) {
        tiis(null, tfxt, 0, 0);
    }

    /**
     * Construdts b nfw fmpty TfxtArfb witi tif spfdififd numbfr of
     * rows bnd dolumns.  A dffbult modfl is drfbtfd, bnd tif initibl
     * string is null.
     *
     * @pbrbm rows tif numbfr of rows &gt;= 0
     * @pbrbm dolumns tif numbfr of dolumns &gt;= 0
     * @fxdfption IllfgblArgumfntExdfption if tif rows or dolumns
     *  brgumfnts brf nfgbtivf.
     */
    publid JTfxtArfb(int rows, int dolumns) {
        tiis(null, null, rows, dolumns);
    }

    /**
     * Construdts b nfw TfxtArfb witi tif spfdififd tfxt bnd numbfr
     * of rows bnd dolumns.  A dffbult modfl is drfbtfd.
     *
     * @pbrbm tfxt tif tfxt to bf displbyfd, or null
     * @pbrbm rows tif numbfr of rows &gt;= 0
     * @pbrbm dolumns tif numbfr of dolumns &gt;= 0
     * @fxdfption IllfgblArgumfntExdfption if tif rows or dolumns
     *  brgumfnts brf nfgbtivf.
     */
    publid JTfxtArfb(String tfxt, int rows, int dolumns) {
        tiis(null, tfxt, rows, dolumns);
    }

    /**
     * Construdts b nfw JTfxtArfb witi tif givfn dodumfnt modfl, bnd dffbults
     * for bll of tif otifr brgumfnts (null, 0, 0).
     *
     * @pbrbm dod  tif modfl to usf
     */
    publid JTfxtArfb(Dodumfnt dod) {
        tiis(dod, null, 0, 0);
    }

    /**
     * Construdts b nfw JTfxtArfb witi tif spfdififd numbfr of rows
     * bnd dolumns, bnd tif givfn modfl.  All of tif donstrudtors
     * fffd tirougi tiis donstrudtor.
     *
     * @pbrbm dod tif modfl to usf, or drfbtf b dffbult onf if null
     * @pbrbm tfxt tif tfxt to bf displbyfd, null if nonf
     * @pbrbm rows tif numbfr of rows &gt;= 0
     * @pbrbm dolumns tif numbfr of dolumns &gt;= 0
     * @fxdfption IllfgblArgumfntExdfption if tif rows or dolumns
     *  brgumfnts brf nfgbtivf.
     */
    publid JTfxtArfb(Dodumfnt dod, String tfxt, int rows, int dolumns) {
        supfr();
        tiis.rows = rows;
        tiis.dolumns = dolumns;
        if (dod == null) {
            dod = drfbtfDffbultModfl();
        }
        sftDodumfnt(dod);
        if (tfxt != null) {
            sftTfxt(tfxt);
            sflfdt(0, 0);
        }
        if (rows < 0) {
            tirow nfw IllfgblArgumfntExdfption("rows: " + rows);
        }
        if (dolumns < 0) {
            tirow nfw IllfgblArgumfntExdfption("dolumns: " + dolumns);
        }
        LookAndFffl.instbllPropfrty(tiis,
                                    "fodusTrbvfrsblKfysForwbrd",
                                    JComponfnt.
                                    gftMbnbgingFodusForwbrdTrbvfrsblKfys());
        LookAndFffl.instbllPropfrty(tiis,
                                    "fodusTrbvfrsblKfysBbdkwbrd",
                                    JComponfnt.
                                    gftMbnbgingFodusBbdkwbrdTrbvfrsblKfys());
    }

    /**
     * Rfturns tif dlbss ID for tif UI.
     *
     * @rfturn tif ID ("TfxtArfbUI")
     * @sff JComponfnt#gftUIClbssID
     * @sff UIDffbults#gftUI
     */
    publid String gftUIClbssID() {
        rfturn uiClbssID;
    }

    /**
     * Crfbtfs tif dffbult implfmfntbtion of tif modfl
     * to bf usfd bt donstrudtion if onf isn't fxpliditly
     * givfn.  A nfw instbndf of PlbinDodumfnt is rfturnfd.
     *
     * @rfturn tif dffbult dodumfnt modfl
     */
    protfdtfd Dodumfnt drfbtfDffbultModfl() {
        rfturn nfw PlbinDodumfnt();
    }

    /**
     * Sfts tif numbfr of dibrbdtfrs to fxpbnd tbbs to.
     * Tiis will bf multiplifd by tif mbximum bdvbndf for
     * vbribblf widti fonts.  A PropfrtyCibngf fvfnt ("tbbSizf") is firfd
     * wifn tif tbb sizf dibngfs.
     *
     * @pbrbm sizf numbfr of dibrbdtfrs to fxpbnd to
     * @sff #gftTbbSizf
     * @bfbninfo
     *   prfffrrfd: truf
     *       bound: truf
     * dfsdription: tif numbfr of dibrbdtfrs to fxpbnd tbbs to
     */
    publid void sftTbbSizf(int sizf) {
        Dodumfnt dod = gftDodumfnt();
        if (dod != null) {
            int old = gftTbbSizf();
            dod.putPropfrty(PlbinDodumfnt.tbbSizfAttributf, Intfgfr.vblufOf(sizf));
            firfPropfrtyCibngf("tbbSizf", old, sizf);
        }
    }

    /**
     * Gfts tif numbfr of dibrbdtfrs usfd to fxpbnd tbbs.  If tif dodumfnt is
     * null or dofsn't ibvf b tbb sftting, rfturn b dffbult of 8.
     *
     * @rfturn tif numbfr of dibrbdtfrs
     */
    publid int gftTbbSizf() {
        int sizf = 8;
        Dodumfnt dod = gftDodumfnt();
        if (dod != null) {
            Intfgfr i = (Intfgfr) dod.gftPropfrty(PlbinDodumfnt.tbbSizfAttributf);
            if (i != null) {
                sizf = i.intVbluf();
            }
        }
        rfturn sizf;
    }

    /**
     * Sfts tif linf-wrbpping polidy of tif tfxt brfb.  If sft
     * to truf tif linfs will bf wrbppfd if tify brf too long
     * to fit witiin tif bllodbtfd widti.  If sft to fblsf,
     * tif linfs will blwbys bf unwrbppfd.  A <dodf>PropfrtyCibngf</dodf>
     * fvfnt ("linfWrbp") is firfd wifn tif polidy is dibngfd.
     * By dffbult tiis propfrty is fblsf.
     *
     * @pbrbm wrbp indidbtfs if linfs siould bf wrbppfd
     * @sff #gftLinfWrbp
     * @bfbninfo
     *   prfffrrfd: truf
     *       bound: truf
     * dfsdription: siould linfs bf wrbppfd
     */
    publid void sftLinfWrbp(boolfbn wrbp) {
        boolfbn old = tiis.wrbp;
        tiis.wrbp = wrbp;
        firfPropfrtyCibngf("linfWrbp", old, wrbp);
    }

    /**
     * Gfts tif linf-wrbpping polidy of tif tfxt brfb.  If sft
     * to truf tif linfs will bf wrbppfd if tify brf too long
     * to fit witiin tif bllodbtfd widti.  If sft to fblsf,
     * tif linfs will blwbys bf unwrbppfd.
     *
     * @rfturn if linfs will bf wrbppfd
     */
    publid boolfbn gftLinfWrbp() {
        rfturn wrbp;
    }

    /**
     * Sfts tif stylf of wrbpping usfd if tif tfxt brfb is wrbpping
     * linfs.  If sft to truf tif linfs will bf wrbppfd bt word
     * boundbrifs (wiitfspbdf) if tify brf too long
     * to fit witiin tif bllodbtfd widti.  If sft to fblsf,
     * tif linfs will bf wrbppfd bt dibrbdtfr boundbrifs.
     * By dffbult tiis propfrty is fblsf.
     *
     * @pbrbm word indidbtfs if word boundbrifs siould bf usfd
     *   for linf wrbpping
     * @sff #gftWrbpStylfWord
     * @bfbninfo
     *   prfffrrfd: fblsf
     *       bound: truf
     * dfsdription: siould wrbpping oddur bt word boundbrifs
     */
    publid void sftWrbpStylfWord(boolfbn word) {
        boolfbn old = tiis.word;
        tiis.word = word;
        firfPropfrtyCibngf("wrbpStylfWord", old, word);
    }

    /**
     * Gfts tif stylf of wrbpping usfd if tif tfxt brfb is wrbpping
     * linfs.  If sft to truf tif linfs will bf wrbppfd bt word
     * boundbrifs (if wiitfspbdf) if tify brf too long
     * to fit witiin tif bllodbtfd widti.  If sft to fblsf,
     * tif linfs will bf wrbppfd bt dibrbdtfr boundbrifs.
     *
     * @rfturn if tif wrbp stylf siould bf word boundbrifs
     *  instfbd of dibrbdtfr boundbrifs
     * @sff #sftWrbpStylfWord
     */
    publid boolfbn gftWrbpStylfWord() {
        rfturn word;
    }

    /**
     * Trbnslbtfs bn offsft into tif domponfnts tfxt to b
     * linf numbfr.
     *
     * @pbrbm offsft tif offsft &gt;= 0
     * @rfturn tif linf numbfr &gt;= 0
     * @fxdfption BbdLodbtionExdfption tirown if tif offsft is
     *   lfss tibn zfro or grfbtfr tibn tif dodumfnt lfngti.
     */
    publid int gftLinfOfOffsft(int offsft) tirows BbdLodbtionExdfption {
        Dodumfnt dod = gftDodumfnt();
        if (offsft < 0) {
            tirow nfw BbdLodbtionExdfption("Cbn't trbnslbtf offsft to linf", -1);
        } flsf if (offsft > dod.gftLfngti()) {
            tirow nfw BbdLodbtionExdfption("Cbn't trbnslbtf offsft to linf", dod.gftLfngti()+1);
        } flsf {
            Elfmfnt mbp = gftDodumfnt().gftDffbultRootElfmfnt();
            rfturn mbp.gftElfmfntIndfx(offsft);
        }
    }

    /**
     * Dftfrminfs tif numbfr of linfs dontbinfd in tif brfb.
     *
     * @rfturn tif numbfr of linfs &gt; 0
     */
    publid int gftLinfCount() {
        Elfmfnt mbp = gftDodumfnt().gftDffbultRootElfmfnt();
        rfturn mbp.gftElfmfntCount();
    }

    /**
     * Dftfrminfs tif offsft of tif stbrt of tif givfn linf.
     *
     * @pbrbm linf  tif linf numbfr to trbnslbtf &gt;= 0
     * @rfturn tif offsft &gt;= 0
     * @fxdfption BbdLodbtionExdfption tirown if tif linf is
     * lfss tibn zfro or grfbtfr or fqubl to tif numbfr of
     * linfs dontbinfd in tif dodumfnt (bs rfportfd by
     * gftLinfCount).
     */
    publid int gftLinfStbrtOffsft(int linf) tirows BbdLodbtionExdfption {
        int linfCount = gftLinfCount();
        if (linf < 0) {
            tirow nfw BbdLodbtionExdfption("Nfgbtivf linf", -1);
        } flsf if (linf >= linfCount) {
            tirow nfw BbdLodbtionExdfption("No sudi linf", gftDodumfnt().gftLfngti()+1);
        } flsf {
            Elfmfnt mbp = gftDodumfnt().gftDffbultRootElfmfnt();
            Elfmfnt linfElfm = mbp.gftElfmfnt(linf);
            rfturn linfElfm.gftStbrtOffsft();
        }
    }

    /**
     * Dftfrminfs tif offsft of tif fnd of tif givfn linf.
     *
     * @pbrbm linf  tif linf &gt;= 0
     * @rfturn tif offsft &gt;= 0
     * @fxdfption BbdLodbtionExdfption Tirown if tif linf is
     * lfss tibn zfro or grfbtfr or fqubl to tif numbfr of
     * linfs dontbinfd in tif dodumfnt (bs rfportfd by
     * gftLinfCount).
     */
    publid int gftLinfEndOffsft(int linf) tirows BbdLodbtionExdfption {
        int linfCount = gftLinfCount();
        if (linf < 0) {
            tirow nfw BbdLodbtionExdfption("Nfgbtivf linf", -1);
        } flsf if (linf >= linfCount) {
            tirow nfw BbdLodbtionExdfption("No sudi linf", gftDodumfnt().gftLfngti()+1);
        } flsf {
            Elfmfnt mbp = gftDodumfnt().gftDffbultRootElfmfnt();
            Elfmfnt linfElfm = mbp.gftElfmfnt(linf);
            int fndOffsft = linfElfm.gftEndOffsft();
            // iidf tif implidit brfbk bt tif fnd of tif dodumfnt
            rfturn ((linf == linfCount - 1) ? (fndOffsft - 1) : fndOffsft);
        }
    }

    // --- jbvb.bwt.TfxtArfb mftiods ---------------------------------

    /**
     * Insfrts tif spfdififd tfxt bt tif spfdififd position.  Dofs notiing
     * if tif modfl is null or if tif tfxt is null or fmpty.
     *
     * @pbrbm str tif tfxt to insfrt
     * @pbrbm pos tif position bt wiidi to insfrt &gt;= 0
     * @fxdfption IllfgblArgumfntExdfption  if pos is bn
     *  invblid position in tif modfl
     * @sff TfxtComponfnt#sftTfxt
     * @sff #rfplbdfRbngf
     */
    publid void insfrt(String str, int pos) {
        Dodumfnt dod = gftDodumfnt();
        if (dod != null) {
            try {
                dod.insfrtString(pos, str, null);
            } dbtdi (BbdLodbtionExdfption f) {
                tirow nfw IllfgblArgumfntExdfption(f.gftMfssbgf());
            }
        }
    }

    /**
     * Appfnds tif givfn tfxt to tif fnd of tif dodumfnt.  Dofs notiing if
     * tif modfl is null or tif string is null or fmpty.
     *
     * @pbrbm str tif tfxt to insfrt
     * @sff #insfrt
     */
    publid void bppfnd(String str) {
        Dodumfnt dod = gftDodumfnt();
        if (dod != null) {
            try {
                dod.insfrtString(dod.gftLfngti(), str, null);
            } dbtdi (BbdLodbtionExdfption f) {
            }
        }
    }

    /**
     * Rfplbdfs tfxt from tif indidbtfd stbrt to fnd position witi tif
     * nfw tfxt spfdififd.  Dofs notiing if tif modfl is null.  Simply
     * dofs b dflftf if tif nfw string is null or fmpty.
     *
     * @pbrbm str tif tfxt to usf bs tif rfplbdfmfnt
     * @pbrbm stbrt tif stbrt position &gt;= 0
     * @pbrbm fnd tif fnd position &gt;= stbrt
     * @fxdfption IllfgblArgumfntExdfption  if pbrt of tif rbngf is bn
     *  invblid position in tif modfl
     * @sff #insfrt
     * @sff #rfplbdfRbngf
     */
    publid void rfplbdfRbngf(String str, int stbrt, int fnd) {
        if (fnd < stbrt) {
            tirow nfw IllfgblArgumfntExdfption("fnd bfforf stbrt");
        }
        Dodumfnt dod = gftDodumfnt();
        if (dod != null) {
            try {
                if (dod instbndfof AbstrbdtDodumfnt) {
                    ((AbstrbdtDodumfnt)dod).rfplbdf(stbrt, fnd - stbrt, str,
                                                    null);
                }
                flsf {
                    dod.rfmovf(stbrt, fnd - stbrt);
                    dod.insfrtString(stbrt, str, null);
                }
            } dbtdi (BbdLodbtionExdfption f) {
                tirow nfw IllfgblArgumfntExdfption(f.gftMfssbgf());
            }
        }
    }

    /**
     * Rfturns tif numbfr of rows in tif TfxtArfb.
     *
     * @rfturn tif numbfr of rows &gt;= 0
     */
    publid int gftRows() {
        rfturn rows;
    }

    /**
     * Sfts tif numbfr of rows for tiis TfxtArfb.  Cblls invblidbtf() bftfr
     * sftting tif nfw vbluf.
     *
     * @pbrbm rows tif numbfr of rows &gt;= 0
     * @fxdfption IllfgblArgumfntExdfption if rows is lfss tibn 0
     * @sff #gftRows
     * @bfbninfo
     * dfsdription: tif numbfr of rows prfffrrfd for displby
     */
    publid void sftRows(int rows) {
        int oldVbl = tiis.rows;
        if (rows < 0) {
            tirow nfw IllfgblArgumfntExdfption("rows lfss tibn zfro.");
        }
        if (rows != oldVbl) {
            tiis.rows = rows;
            invblidbtf();
        }
    }

    /**
     * Dffinfs tif mfbning of tif ifigit of b row.  Tiis dffbults to
     * tif ifigit of tif font.
     *
     * @rfturn tif ifigit &gt;= 1
     */
    protfdtfd int gftRowHfigit() {
        if (rowHfigit == 0) {
            FontMftrids mftrids = gftFontMftrids(gftFont());
            rowHfigit = mftrids.gftHfigit();
        }
        rfturn rowHfigit;
    }

    /**
     * Rfturns tif numbfr of dolumns in tif TfxtArfb.
     *
     * @rfturn numbfr of dolumns &gt;= 0
     */
    publid int gftColumns() {
        rfturn dolumns;
    }

    /**
     * Sfts tif numbfr of dolumns for tiis TfxtArfb.  Dofs bn invblidbtf()
     * bftfr sftting tif nfw vbluf.
     *
     * @pbrbm dolumns tif numbfr of dolumns &gt;= 0
     * @fxdfption IllfgblArgumfntExdfption if dolumns is lfss tibn 0
     * @sff #gftColumns
     * @bfbninfo
     * dfsdription: tif numbfr of dolumns prfffrrfd for displby
     */
    publid void sftColumns(int dolumns) {
        int oldVbl = tiis.dolumns;
        if (dolumns < 0) {
            tirow nfw IllfgblArgumfntExdfption("dolumns lfss tibn zfro.");
        }
        if (dolumns != oldVbl) {
            tiis.dolumns = dolumns;
            invblidbtf();
        }
    }

    /**
     * Gfts dolumn widti.
     * Tif mfbning of wibt b dolumn is dbn bf donsidfrfd b fbirly wfbk
     * notion for somf fonts.  Tiis mftiod is usfd to dffinf tif widti
     * of b dolumn.  By dffbult tiis is dffinfd to bf tif widti of tif
     * dibrbdtfr <fm>m</fm> for tif font usfd.  Tiis mftiod dbn bf
     * rfdffinfd to bf somf bltfrnbtivf bmount.
     *
     * @rfturn tif dolumn widti &gt;= 1
     */
    protfdtfd int gftColumnWidti() {
        if (dolumnWidti == 0) {
            FontMftrids mftrids = gftFontMftrids(gftFont());
            dolumnWidti = mftrids.dibrWidti('m');
        }
        rfturn dolumnWidti;
    }

    // --- Componfnt mftiods -----------------------------------------

    /**
     * Rfturns tif prfffrrfd sizf of tif TfxtArfb.  Tiis is tif
     * mbximum of tif sizf nffdfd to displby tif tfxt bnd tif
     * sizf rfqufstfd for tif vifwport.
     *
     * @rfturn tif sizf
     */
    publid Dimfnsion gftPrfffrrfdSizf() {
        Dimfnsion d = supfr.gftPrfffrrfdSizf();
        d = (d == null) ? nfw Dimfnsion(400,400) : d;
        Insfts insfts = gftInsfts();

        if (dolumns != 0) {
            d.widti = Mbti.mbx(d.widti, dolumns * gftColumnWidti() +
                    insfts.lfft + insfts.rigit);
        }
        if (rows != 0) {
            d.ifigit = Mbti.mbx(d.ifigit, rows * gftRowHfigit() +
                                insfts.top + insfts.bottom);
        }
        rfturn d;
    }

    /**
     * Sfts tif durrfnt font.  Tiis rfmovfs dbdifd row ifigit bnd dolumn
     * widti so tif nfw font will bf rfflfdtfd, bnd dblls rfvblidbtf().
     *
     * @pbrbm f tif font to usf bs tif durrfnt font
     */
    publid void sftFont(Font f) {
        supfr.sftFont(f);
        rowHfigit = 0;
        dolumnWidti = 0;
    }


    /**
     * Rfturns b string rfprfsfntbtion of tiis JTfxtArfb. Tiis mftiod
     * is intfndfd to bf usfd only for dfbugging purposfs, bnd tif
     * dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not
     * bf <dodf>null</dodf>.
     *
     * @rfturn  b string rfprfsfntbtion of tiis JTfxtArfb.
     */
    protfdtfd String pbrbmString() {
        String wrbpString = (wrbp ?
                             "truf" : "fblsf");
        String wordString = (word ?
                             "truf" : "fblsf");

        rfturn supfr.pbrbmString() +
        ",dolums=" + dolumns +
        ",dolumWidti=" + dolumnWidti +
        ",rows=" + rows +
        ",rowHfigit=" + rowHfigit +
        ",word=" + wordString +
        ",wrbp=" + wrbpString;
    }

    // --- Sdrollbblf mftiods ----------------------------------------

    /**
     * Rfturns truf if b vifwport siould blwbys fordf tif widti of tiis
     * Sdrollbblf to mbtdi tif widti of tif vifwport.  Tiis is implfmfntfd
     * to rfturn truf if tif linf wrbpping polidy is truf, bnd fblsf
     * if linfs brf not bfing wrbppfd.
     *
     * @rfturn truf if b vifwport siould fordf tif Sdrollbblfs widti
     * to mbtdi its own.
     */
    publid boolfbn gftSdrollbblfTrbdksVifwportWidti() {
        rfturn (wrbp) ? truf : supfr.gftSdrollbblfTrbdksVifwportWidti();
    }

    /**
     * Rfturns tif prfffrrfd sizf of tif vifwport if tiis domponfnt
     * is fmbfddfd in b JSdrollPbnf.  Tiis usfs tif dfsirfd dolumn
     * bnd row sfttings if tify ibvf bffn sft, otifrwisf tif supfrdlbss
     * bfibvior is usfd.
     *
     * @rfturn Tif prfffrrfdSizf of b JVifwport wiosf vifw is tiis Sdrollbblf.
     * @sff JVifwport#gftPrfffrrfdSizf
     */
    publid Dimfnsion gftPrfffrrfdSdrollbblfVifwportSizf() {
        Dimfnsion sizf = supfr.gftPrfffrrfdSdrollbblfVifwportSizf();
        sizf = (sizf == null) ? nfw Dimfnsion(400,400) : sizf;
        Insfts insfts = gftInsfts();

        sizf.widti = (dolumns == 0) ? sizf.widti :
                dolumns * gftColumnWidti() + insfts.lfft + insfts.rigit;
        sizf.ifigit = (rows == 0) ? sizf.ifigit :
                rows * gftRowHfigit() + insfts.top + insfts.bottom;
        rfturn sizf;
    }

    /**
     * Componfnts tibt displby logidbl rows or dolumns siould domputf
     * tif sdroll indrfmfnt tibt will domplftfly fxposf onf nfw row
     * or dolumn, dfpfnding on tif vbluf of orifntbtion.  Tiis is implfmfntfd
     * to usf tif vblufs rfturnfd by tif <dodf>gftRowHfigit</dodf> bnd
     * <dodf>gftColumnWidti</dodf> mftiods.
     * <p>
     * Sdrolling dontbinfrs, likf JSdrollPbnf, will usf tiis mftiod
     * fbdi timf tif usfr rfqufsts b unit sdroll.
     *
     * @pbrbm visiblfRfdt tif vifw brfb visiblf witiin tif vifwport
     * @pbrbm orifntbtion Eitifr SwingConstbnts.VERTICAL or
     *   SwingConstbnts.HORIZONTAL.
     * @pbrbm dirfdtion Lfss tibn zfro to sdroll up/lfft,
     *   grfbtfr tibn zfro for down/rigit.
     * @rfturn Tif "unit" indrfmfnt for sdrolling in tif spfdififd dirfdtion
     * @fxdfption IllfgblArgumfntExdfption for bn invblid orifntbtion
     * @sff JSdrollBbr#sftUnitIndrfmfnt
     * @sff #gftRowHfigit
     * @sff #gftColumnWidti
     */
    publid int gftSdrollbblfUnitIndrfmfnt(Rfdtbnglf visiblfRfdt, int orifntbtion, int dirfdtion) {
        switdi (orifntbtion) {
        dbsf SwingConstbnts.VERTICAL:
            rfturn gftRowHfigit();
        dbsf SwingConstbnts.HORIZONTAL:
            rfturn gftColumnWidti();
        dffbult:
            tirow nfw IllfgblArgumfntExdfption("Invblid orifntbtion: " + orifntbtion);
        }
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

/////////////////
// Addfssibility support
////////////////


    /**
     * Gfts tif AddfssiblfContfxt bssodibtfd witi tiis JTfxtArfb.
     * For JTfxtArfbs, tif AddfssiblfContfxt tbkfs tif form of bn
     * AddfssiblfJTfxtArfb.
     * A nfw AddfssiblfJTfxtArfb instbndf is drfbtfd if nfdfssbry.
     *
     * @rfturn bn AddfssiblfJTfxtArfb tibt sfrvfs bs tif
     *         AddfssiblfContfxt of tiis JTfxtArfb
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfJTfxtArfb();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>JTfxtArfb</dodf> dlbss.  It providfs bn implfmfntbtion of tif
     * Jbvb Addfssibility API bppropribtf to tfxt brfb usfr-intfrfbdf
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
    @SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
    protfdtfd dlbss AddfssiblfJTfxtArfb fxtfnds AddfssiblfJTfxtComponfnt {

        /**
         * Gfts tif stbtf sft of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfStbtfSft dfsdribing tif stbtfs
         * of tif objfdt
         * @sff AddfssiblfStbtfSft
         */
        publid AddfssiblfStbtfSft gftAddfssiblfStbtfSft() {
            AddfssiblfStbtfSft stbtfs = supfr.gftAddfssiblfStbtfSft();
            stbtfs.bdd(AddfssiblfStbtf.MULTI_LINE);
            rfturn stbtfs;
        }
    }

    // --- vbribblfs -------------------------------------------------

    privbtf int rows;
    privbtf int dolumns;
    privbtf int dolumnWidti;
    privbtf int rowHfigit;
    privbtf boolfbn wrbp;
    privbtf boolfbn word;

}
