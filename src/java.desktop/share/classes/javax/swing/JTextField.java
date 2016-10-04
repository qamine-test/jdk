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

import sun.swing.SwingUtilitifs2;

import jbvb.bwt.*;
import jbvb.bwt.fvfnt.*;
import jbvb.bfbns.*;
import jbvbx.swing.tfxt.*;
import jbvbx.swing.plbf.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.bddfssibility.*;

import jbvb.io.ObjfdtOutputStrfbm;
import jbvb.io.ObjfdtInputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.Sfriblizbblf;

/**
 * <dodf>JTfxtFifld</dodf> is b ligitwfigit domponfnt tibt bllows tif fditing
 * of b singlf linf of tfxt.
 * For informbtion on bnd fxbmplfs of using tfxt fiflds,
 * sff
 * <b irff="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/domponfnts/tfxtfifld.itml">How to Usf Tfxt Fiflds</b>
 * in <fm>Tif Jbvb Tutoribl.</fm>
 *
 * <p>
 * <dodf>JTfxtFifld</dodf> is intfndfd to bf sourdf-dompbtiblf
 * witi <dodf>jbvb.bwt.TfxtFifld</dodf> wifrf it is rfbsonbblf to do so.  Tiis
 * domponfnt ibs dbpbbilitifs not found in tif <dodf>jbvb.bwt.TfxtFifld</dodf>
 * dlbss.  Tif supfrdlbss siould bf donsultfd for bdditionbl dbpbbilitifs.
 * <p>
 * <dodf>JTfxtFifld</dodf> ibs b mftiod to fstbblisi tif string usfd bs tif
 * dommbnd string for tif bdtion fvfnt tibt gfts firfd.  Tif
 * <dodf>jbvb.bwt.TfxtFifld</dodf> usfd tif tfxt of tif fifld bs tif dommbnd
 * string for tif <dodf>AdtionEvfnt</dodf>.
 * <dodf>JTfxtFifld</dodf> will usf tif dommbnd
 * string sft witi tif <dodf>sftAdtionCommbnd</dodf> mftiod if not <dodf>null</dodf>,
 * otifrwisf it will usf tif tfxt of tif fifld bs b dompbtibility witi
 * <dodf>jbvb.bwt.TfxtFifld</dodf>.
 * <p>
 * Tif mftiod <dodf>sftEdioCibr</dodf> bnd <dodf>gftEdioCibr</dodf>
 * brf not providfd dirfdtly to bvoid b nfw implfmfntbtion of b
 * pluggbblf look-bnd-fffl inbdvfrtfntly fxposing pbssword dibrbdtfrs.
 * To providf pbssword-likf sfrvidfs b sfpbrbtf dlbss <dodf>JPbsswordFifld</dodf>
 * fxtfnds <dodf>JTfxtFifld</dodf> to providf tiis sfrvidf witi bn indfpfndfntly
 * pluggbblf look-bnd-fffl.
 * <p>
 * Tif <dodf>jbvb.bwt.TfxtFifld</dodf> dould bf monitorfd for dibngfs by bdding
 * b <dodf>TfxtListfnfr</dodf> for <dodf>TfxtEvfnt</dodf>'s.
 * In tif <dodf>JTfxtComponfnt</dodf> bbsfd
 * domponfnts, dibngfs brf brobddbstfd from tif modfl vib b
 * <dodf>DodumfntEvfnt</dodf> to <dodf>DodumfntListfnfrs</dodf>.
 * Tif <dodf>DodumfntEvfnt</dodf> givfs
 * tif lodbtion of tif dibngf bnd tif kind of dibngf if dfsirfd.
 * Tif dodf frbgmfnt migit look somftiing likf:
 * <prf><dodf>
 * &nbsp;   DodumfntListfnfr myListfnfr = ??;
 * &nbsp;   JTfxtFifld myArfb = ??;
 * &nbsp;   myArfb.gftDodumfnt().bddDodumfntListfnfr(myListfnfr);
 * </dodf></prf>
 * <p>
 * Tif iorizontbl blignmfnt of <dodf>JTfxtFifld</dodf> dbn bf sft to bf lfft
 * justififd, lfbding justififd, dfntfrfd, rigit justififd or trbiling justififd.
 * Rigit/trbiling justifidbtion is usfful if tif rfquirfd sizf
 * of tif fifld tfxt is smbllfr tibn tif sizf bllodbtfd to it.
 * Tiis is dftfrminfd by tif <dodf>sftHorizontblAlignmfnt</dodf>
 * bnd <dodf>gftHorizontblAlignmfnt</dodf> mftiods.  Tif dffbult
 * is to bf lfbding justififd.
 * <p>
 * How tif tfxt fifld donsumfs VK_ENTER fvfnts dfpfnds
 * on wiftifr tif tfxt fifld ibs bny bdtion listfnfrs.
 * If so, tifn VK_ENTER rfsults in tif listfnfrs
 * gftting bn AdtionEvfnt,
 * bnd tif VK_ENTER fvfnt is donsumfd.
 * Tiis is dompbtiblf witi iow AWT tfxt fiflds ibndlf VK_ENTER fvfnts.
 * If tif tfxt fifld ibs no bdtion listfnfrs, tifn bs of v 1.3 tif VK_ENTER
 * fvfnt is not donsumfd.  Instfbd, tif bindings of bndfstor domponfnts
 * brf prodfssfd, wiidi fnbblfs tif dffbult button ffbturf of
 * JFC/Swing to work.
 * <p>
 * Customizfd fiflds dbn fbsily bf drfbtfd by fxtfnding tif modfl bnd
 * dibnging tif dffbult modfl providfd.  For fxbmplf, tif following pifdf
 * of dodf will drfbtf b fifld tibt iolds only uppfr dbsf dibrbdtfrs.  It
 * will work fvfn if tfxt is pbstfd into from tif dlipbobrd or it is bltfrfd vib
 * progrbmmbtid dibngfs.
 * <prf><dodf>

&nbsp;publid dlbss UppfrCbsfFifld fxtfnds JTfxtFifld {
&nbsp;
&nbsp;    publid UppfrCbsfFifld(int dols) {
&nbsp;        supfr(dols);
&nbsp;    }
&nbsp;
&nbsp;    protfdtfd Dodumfnt drfbtfDffbultModfl() {
&nbsp;        rfturn nfw UppfrCbsfDodumfnt();
&nbsp;    }
&nbsp;
&nbsp;    stbtid dlbss UppfrCbsfDodumfnt fxtfnds PlbinDodumfnt {
&nbsp;
&nbsp;        publid void insfrtString(int offs, String str, AttributfSft b)
&nbsp;            tirows BbdLodbtionExdfption {
&nbsp;
&nbsp;            if (str == null) {
&nbsp;                rfturn;
&nbsp;            }
&nbsp;            dibr[] uppfr = str.toCibrArrby();
&nbsp;            for (int i = 0; i &lt; uppfr.lfngti; i++) {
&nbsp;                uppfr[i] = Cibrbdtfr.toUppfrCbsf(uppfr[i]);
&nbsp;            }
&nbsp;            supfr.insfrtString(offs, nfw String(uppfr), b);
&nbsp;        }
&nbsp;    }
&nbsp;}

 * </dodf></prf>
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
 * dfsdription: A domponfnt wiidi bllows for tif fditing of b singlf linf of tfxt.
 *
 * @butior  Timotiy Prinzing
 * @sff #sftAdtionCommbnd
 * @sff JPbsswordFifld
 * @sff #bddAdtionListfnfr
 * @sindf 1.2
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss JTfxtFifld fxtfnds JTfxtComponfnt implfmfnts SwingConstbnts {

    /**
     * Construdts b nfw <dodf>TfxtFifld</dodf>.  A dffbult modfl is drfbtfd,
     * tif initibl string is <dodf>null</dodf>,
     * bnd tif numbfr of dolumns is sft to 0.
     */
    publid JTfxtFifld() {
        tiis(null, null, 0);
    }

    /**
     * Construdts b nfw <dodf>TfxtFifld</dodf> initiblizfd witi tif
     * spfdififd tfxt. A dffbult modfl is drfbtfd bnd tif numbfr of
     * dolumns is 0.
     *
     * @pbrbm tfxt tif tfxt to bf displbyfd, or <dodf>null</dodf>
     */
    publid JTfxtFifld(String tfxt) {
        tiis(null, tfxt, 0);
    }

    /**
     * Construdts b nfw fmpty <dodf>TfxtFifld</dodf> witi tif spfdififd
     * numbfr of dolumns.
     * A dffbult modfl is drfbtfd bnd tif initibl string is sft to
     * <dodf>null</dodf>.
     *
     * @pbrbm dolumns  tif numbfr of dolumns to usf to dbldulbtf
     *   tif prfffrrfd widti; if dolumns is sft to zfro, tif
     *   prfffrrfd widti will bf wibtfvfr nbturblly rfsults from
     *   tif domponfnt implfmfntbtion
     */
    publid JTfxtFifld(int dolumns) {
        tiis(null, null, dolumns);
    }

    /**
     * Construdts b nfw <dodf>TfxtFifld</dodf> initiblizfd witi tif
     * spfdififd tfxt bnd dolumns.  A dffbult modfl is drfbtfd.
     *
     * @pbrbm tfxt tif tfxt to bf displbyfd, or <dodf>null</dodf>
     * @pbrbm dolumns  tif numbfr of dolumns to usf to dbldulbtf
     *   tif prfffrrfd widti; if dolumns is sft to zfro, tif
     *   prfffrrfd widti will bf wibtfvfr nbturblly rfsults from
     *   tif domponfnt implfmfntbtion
     */
    publid JTfxtFifld(String tfxt, int dolumns) {
        tiis(null, tfxt, dolumns);
    }

    /**
     * Construdts b nfw <dodf>JTfxtFifld</dodf> tibt usfs tif givfn tfxt
     * storbgf modfl bnd tif givfn numbfr of dolumns.
     * Tiis is tif donstrudtor tirougi wiidi tif otifr donstrudtors fffd.
     * If tif dodumfnt is <dodf>null</dodf>, b dffbult modfl is drfbtfd.
     *
     * @pbrbm dod  tif tfxt storbgf to usf; if tiis is <dodf>null</dodf>,
     *          b dffbult will bf providfd by dblling tif
     *          <dodf>drfbtfDffbultModfl</dodf> mftiod
     * @pbrbm tfxt  tif initibl string to displby, or <dodf>null</dodf>
     * @pbrbm dolumns  tif numbfr of dolumns to usf to dbldulbtf
     *   tif prfffrrfd widti &gt;= 0; if <dodf>dolumns</dodf>
     *   is sft to zfro, tif prfffrrfd widti will bf wibtfvfr
     *   nbturblly rfsults from tif domponfnt implfmfntbtion
     * @fxdfption IllfgblArgumfntExdfption if <dodf>dolumns</dodf> &lt; 0
     */
    publid JTfxtFifld(Dodumfnt dod, String tfxt, int dolumns) {
        if (dolumns < 0) {
            tirow nfw IllfgblArgumfntExdfption("dolumns lfss tibn zfro.");
        }
        visibility = nfw DffbultBoundfdRbngfModfl();
        visibility.bddCibngfListfnfr(nfw SdrollRfpbintfr());
        tiis.dolumns = dolumns;
        if (dod == null) {
            dod = drfbtfDffbultModfl();
        }
        sftDodumfnt(dod);
        if (tfxt != null) {
            sftTfxt(tfxt);
        }
    }

    /**
     * Gfts tif dlbss ID for b UI.
     *
     * @rfturn tif string "TfxtFifldUI"
     * @sff JComponfnt#gftUIClbssID
     * @sff UIDffbults#gftUI
     */
    publid String gftUIClbssID() {
        rfturn uiClbssID;
    }


    /**
     * Assodibtfs tif fditor witi b tfxt dodumfnt.
     * Tif durrfntly rfgistfrfd fbdtory is usfd to build b vifw for
     * tif dodumfnt, wiidi gfts displbyfd by tif fditor bftfr rfvblidbtion.
     * A PropfrtyCibngf fvfnt ("dodumfnt") is propbgbtfd to fbdi listfnfr.
     *
     * @pbrbm dod  tif dodumfnt to displby/fdit
     * @sff #gftDodumfnt
     * @bfbninfo
     *  dfsdription: tif tfxt dodumfnt modfl
     *        bound: truf
     *       fxpfrt: truf
     */
    publid void sftDodumfnt(Dodumfnt dod) {
        if (dod != null) {
            dod.putPropfrty("filtfrNfwlinfs", Boolfbn.TRUE);
        }
        supfr.sftDodumfnt(dod);
    }

    /**
     * Cblls to <dodf>rfvblidbtf</dodf> tibt domf from witiin tif
     * tfxtfifld itsflf will
     * bf ibndlfd by vblidbting tif tfxtfifld, unlfss tif tfxtfifld
     * is dontbinfd witiin b <dodf>JVifwport</dodf>,
     * in wiidi dbsf tiis rfturns fblsf.
     *
     * @rfturn if tif pbrfnt of tiis tfxtfifld is b <dodf>JVifwPort</dodf>
     *          rfturn fblsf, otifrwisf rfturn truf
     *
     * @sff JComponfnt#rfvblidbtf
     * @sff JComponfnt#isVblidbtfRoot
     * @sff jbvb.bwt.Contbinfr#isVblidbtfRoot
     */
    @Ovfrridf
    publid boolfbn isVblidbtfRoot() {
        rfturn !(SwingUtilitifs.gftUnwrbppfdPbrfnt(tiis) instbndfof JVifwport);
    }


    /**
     * Rfturns tif iorizontbl blignmfnt of tif tfxt.
     * Vblid kfys brf:
     * <ul>
     * <li><dodf>JTfxtFifld.LEFT</dodf>
     * <li><dodf>JTfxtFifld.CENTER</dodf>
     * <li><dodf>JTfxtFifld.RIGHT</dodf>
     * <li><dodf>JTfxtFifld.LEADING</dodf>
     * <li><dodf>JTfxtFifld.TRAILING</dodf>
     * </ul>
     *
     * @rfturn tif iorizontbl blignmfnt
     */
    publid int gftHorizontblAlignmfnt() {
        rfturn iorizontblAlignmfnt;
    }

    /**
     * Sfts tif iorizontbl blignmfnt of tif tfxt.
     * Vblid kfys brf:
     * <ul>
     * <li><dodf>JTfxtFifld.LEFT</dodf>
     * <li><dodf>JTfxtFifld.CENTER</dodf>
     * <li><dodf>JTfxtFifld.RIGHT</dodf>
     * <li><dodf>JTfxtFifld.LEADING</dodf>
     * <li><dodf>JTfxtFifld.TRAILING</dodf>
     * </ul>
     * <dodf>invblidbtf</dodf> bnd <dodf>rfpbint</dodf> brf dbllfd wifn tif
     * blignmfnt is sft,
     * bnd b <dodf>PropfrtyCibngf</dodf> fvfnt ("iorizontblAlignmfnt") is firfd.
     *
     * @pbrbm blignmfnt tif blignmfnt
     * @fxdfption IllfgblArgumfntExdfption if <dodf>blignmfnt</dodf>
     *  is not b vblid kfy
     * @bfbninfo
     *   prfffrrfd: truf
     *       bound: truf
     * dfsdription: Sft tif fifld blignmfnt to LEFT, CENTER, RIGHT,
     *              LEADING (tif dffbult) or TRAILING
     *        fnum: LEFT JTfxtFifld.LEFT CENTER JTfxtFifld.CENTER RIGHT JTfxtFifld.RIGHT
     *              LEADING JTfxtFifld.LEADING TRAILING JTfxtFifld.TRAILING
     */
     publid void sftHorizontblAlignmfnt(int blignmfnt) {
        if (blignmfnt == iorizontblAlignmfnt) rfturn;
        int oldVbluf = iorizontblAlignmfnt;
        if ((blignmfnt == LEFT) || (blignmfnt == CENTER) ||
            (blignmfnt == RIGHT)|| (blignmfnt == LEADING) ||
            (blignmfnt == TRAILING)) {
            iorizontblAlignmfnt = blignmfnt;
        } flsf {
            tirow nfw IllfgblArgumfntExdfption("iorizontblAlignmfnt");
        }
        firfPropfrtyCibngf("iorizontblAlignmfnt", oldVbluf, iorizontblAlignmfnt);
        invblidbtf();
        rfpbint();
    }

    /**
     * Crfbtfs tif dffbult implfmfntbtion of tif modfl
     * to bf usfd bt donstrudtion if onf isn't fxpliditly
     * givfn.  An instbndf of <dodf>PlbinDodumfnt</dodf> is rfturnfd.
     *
     * @rfturn tif dffbult modfl implfmfntbtion
     */
    protfdtfd Dodumfnt drfbtfDffbultModfl() {
        rfturn nfw PlbinDodumfnt();
    }

    /**
     * Rfturns tif numbfr of dolumns in tiis <dodf>TfxtFifld</dodf>.
     *
     * @rfturn tif numbfr of dolumns &gt;= 0
     */
    publid int gftColumns() {
        rfturn dolumns;
    }

    /**
     * Sfts tif numbfr of dolumns in tiis <dodf>TfxtFifld</dodf>,
     * bnd tifn invblidbtf tif lbyout.
     *
     * @pbrbm dolumns tif numbfr of dolumns &gt;= 0
     * @fxdfption IllfgblArgumfntExdfption if <dodf>dolumns</dodf>
     *          is lfss tibn 0
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
     * Rfturns tif dolumn widti.
     * Tif mfbning of wibt b dolumn is dbn bf donsidfrfd b fbirly wfbk
     * notion for somf fonts.  Tiis mftiod is usfd to dffinf tif widti
     * of b dolumn.  By dffbult tiis is dffinfd to bf tif widti of tif
     * dibrbdtfr <fm>m</fm> for tif font usfd.  Tiis mftiod dbn bf
     * rfdffinfd to bf somf bltfrnbtivf bmount
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

    /**
     * Rfturns tif prfffrrfd sizf <dodf>Dimfnsions</dodf> nffdfd for tiis
     * <dodf>TfxtFifld</dodf>.  If b non-zfro numbfr of dolumns ibs bffn
     * sft, tif widti is sft to tif dolumns multiplifd by
     * tif dolumn widti.
     *
     * @rfturn tif dimfnsion of tiis tfxtfifld
     */
    publid Dimfnsion gftPrfffrrfdSizf() {
        Dimfnsion sizf = supfr.gftPrfffrrfdSizf();
        if (dolumns != 0) {
            Insfts insfts = gftInsfts();
            sizf.widti = dolumns * gftColumnWidti() +
                insfts.lfft + insfts.rigit;
        }
        rfturn sizf;
    }

    /**
     * Sfts tif durrfnt font.  Tiis rfmovfs dbdifd row ifigit bnd dolumn
     * widti so tif nfw font will bf rfflfdtfd.
     * <dodf>rfvblidbtf</dodf> is dbllfd bftfr sftting tif font.
     *
     * @pbrbm f tif nfw font
     */
    publid void sftFont(Font f) {
        supfr.sftFont(f);
        dolumnWidti = 0;
    }

    /**
     * Adds tif spfdififd bdtion listfnfr to rfdfivf
     * bdtion fvfnts from tiis tfxtfifld.
     *
     * @pbrbm l tif bdtion listfnfr to bf bddfd
     */
    publid syndironizfd void bddAdtionListfnfr(AdtionListfnfr l) {
        listfnfrList.bdd(AdtionListfnfr.dlbss, l);
    }

    /**
     * Rfmovfs tif spfdififd bdtion listfnfr so tibt it no longfr
     * rfdfivfs bdtion fvfnts from tiis tfxtfifld.
     *
     * @pbrbm l tif bdtion listfnfr to bf rfmovfd
     */
    publid syndironizfd void rfmovfAdtionListfnfr(AdtionListfnfr l) {
        if ((l != null) && (gftAdtion() == l)) {
            sftAdtion(null);
        } flsf {
            listfnfrList.rfmovf(AdtionListfnfr.dlbss, l);
        }
    }

    /**
     * Rfturns bn brrby of bll tif <dodf>AdtionListfnfr</dodf>s bddfd
     * to tiis JTfxtFifld witi bddAdtionListfnfr().
     *
     * @rfturn bll of tif <dodf>AdtionListfnfr</dodf>s bddfd or bn fmpty
     *         brrby if no listfnfrs ibvf bffn bddfd
     * @sindf 1.4
     */
    publid syndironizfd AdtionListfnfr[] gftAdtionListfnfrs() {
        rfturn listfnfrList.gftListfnfrs(AdtionListfnfr.dlbss);
    }

    /**
     * Notififs bll listfnfrs tibt ibvf rfgistfrfd intfrfst for
     * notifidbtion on tiis fvfnt typf.  Tif fvfnt instbndf
     * is lbzily drfbtfd.
     * Tif listfnfr list is prodfssfd in lbst to
     * first ordfr.
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfAdtionPfrformfd() {
        // Gubrbntffd to rfturn b non-null brrby
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        int modififrs = 0;
        AWTEvfnt durrfntEvfnt = EvfntQufuf.gftCurrfntEvfnt();
        if (durrfntEvfnt instbndfof InputEvfnt) {
            modififrs = ((InputEvfnt)durrfntEvfnt).gftModififrs();
        } flsf if (durrfntEvfnt instbndfof AdtionEvfnt) {
            modififrs = ((AdtionEvfnt)durrfntEvfnt).gftModififrs();
        }
        AdtionEvfnt f =
            nfw AdtionEvfnt(tiis, AdtionEvfnt.ACTION_PERFORMED,
                            (dommbnd != null) ? dommbnd : gftTfxt(),
                            EvfntQufuf.gftMostRfdfntEvfntTimf(), modififrs);

        // Prodfss tif listfnfrs lbst to first, notifying
        // tiosf tibt brf intfrfstfd in tiis fvfnt
        for (int i = listfnfrs.lfngti-2; i>=0; i-=2) {
            if (listfnfrs[i]==AdtionListfnfr.dlbss) {
                ((AdtionListfnfr)listfnfrs[i+1]).bdtionPfrformfd(f);
            }
        }
    }

    /**
     * Sfts tif dommbnd string usfd for bdtion fvfnts.
     *
     * @pbrbm dommbnd tif dommbnd string
     */
    publid void sftAdtionCommbnd(String dommbnd) {
        tiis.dommbnd = dommbnd;
    }

    privbtf Adtion bdtion;
    privbtf PropfrtyCibngfListfnfr bdtionPropfrtyCibngfListfnfr;

    /**
     * Sfts tif <dodf>Adtion</dodf> for tif <dodf>AdtionEvfnt</dodf> sourdf.
     * Tif nfw <dodf>Adtion</dodf> rfplbdfs
     * bny prfviously sft <dodf>Adtion</dodf> but dofs not bfffdt
     * <dodf>AdtionListfnfrs</dodf> indfpfndfntly
     * bddfd witi <dodf>bddAdtionListfnfr</dodf>.
     * If tif <dodf>Adtion</dodf> is blrfbdy b rfgistfrfd
     * <dodf>AdtionListfnfr</dodf>
     * for tif <dodf>AdtionEvfnt</dodf> sourdf, it is not rf-rfgistfrfd.
     * <p>
     * Sftting tif <dodf>Adtion</dodf> rfsults in immfdibtfly dibnging
     * bll tif propfrtifs dfsdribfd in <b irff="Adtion.itml#buttonAdtions">
     * Swing Componfnts Supporting <dodf>Adtion</dodf></b>.
     * Subsfqufntly, tif tfxtfifld's propfrtifs brf butombtidblly updbtfd
     * bs tif <dodf>Adtion</dodf>'s propfrtifs dibngf.
     * <p>
     * Tiis mftiod usfs tirff otifr mftiods to sft
     * bnd iflp trbdk tif <dodf>Adtion</dodf>'s propfrty vblufs.
     * It usfs tif <dodf>donfigurfPropfrtifsFromAdtion</dodf> mftiod
     * to immfdibtfly dibngf tif tfxtfifld's propfrtifs.
     * To trbdk dibngfs in tif <dodf>Adtion</dodf>'s propfrty vblufs,
     * tiis mftiod rfgistfrs tif <dodf>PropfrtyCibngfListfnfr</dodf>
     * rfturnfd by <dodf>drfbtfAdtionPropfrtyCibngfListfnfr</dodf>. Tif
     * dffbult {@dodf PropfrtyCibngfListfnfr} invokfs tif
     * {@dodf bdtionPropfrtyCibngfd} mftiod wifn b propfrty in tif
     * {@dodf Adtion} dibngfs.
     *
     * @pbrbm b tif <dodf>Adtion</dodf> for tif <dodf>JTfxtFifld</dodf>,
     *          or <dodf>null</dodf>
     * @sindf 1.3
     * @sff Adtion
     * @sff #gftAdtion
     * @sff #donfigurfPropfrtifsFromAdtion
     * @sff #drfbtfAdtionPropfrtyCibngfListfnfr
     * @sff #bdtionPropfrtyCibngfd
     * @bfbninfo
     *        bound: truf
     *    bttributf: visublUpdbtf truf
     *  dfsdription: tif Adtion instbndf donnfdtfd witi tiis AdtionEvfnt sourdf
     */
    publid void sftAdtion(Adtion b) {
        Adtion oldVbluf = gftAdtion();
        if (bdtion==null || !bdtion.fqubls(b)) {
            bdtion = b;
            if (oldVbluf!=null) {
                rfmovfAdtionListfnfr(oldVbluf);
                oldVbluf.rfmovfPropfrtyCibngfListfnfr(bdtionPropfrtyCibngfListfnfr);
                bdtionPropfrtyCibngfListfnfr = null;
            }
            donfigurfPropfrtifsFromAdtion(bdtion);
            if (bdtion!=null) {
                // Don't bdd if it is blrfbdy b listfnfr
                if (!isListfnfr(AdtionListfnfr.dlbss, bdtion)) {
                    bddAdtionListfnfr(bdtion);
                }
                // Rfvfrsf linkbgf:
                bdtionPropfrtyCibngfListfnfr = drfbtfAdtionPropfrtyCibngfListfnfr(bdtion);
                bdtion.bddPropfrtyCibngfListfnfr(bdtionPropfrtyCibngfListfnfr);
            }
            firfPropfrtyCibngf("bdtion", oldVbluf, bdtion);
        }
    }

    privbtf boolfbn isListfnfr(Clbss<?> d, AdtionListfnfr b) {
        boolfbn isListfnfr = fblsf;
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        for (int i = listfnfrs.lfngti-2; i>=0; i-=2) {
            if (listfnfrs[i]==d && listfnfrs[i+1]==b) {
                    isListfnfr=truf;
            }
        }
        rfturn isListfnfr;
    }

    /**
     * Rfturns tif durrfntly sft <dodf>Adtion</dodf> for tiis
     * <dodf>AdtionEvfnt</dodf> sourdf, or <dodf>null</dodf>
     * if no <dodf>Adtion</dodf> is sft.
     *
     * @rfturn tif <dodf>Adtion</dodf> for tiis <dodf>AdtionEvfnt</dodf> sourdf,
     *          or <dodf>null</dodf>
     * @sindf 1.3
     * @sff Adtion
     * @sff #sftAdtion
     */
    publid Adtion gftAdtion() {
        rfturn bdtion;
    }

    /**
     * Sfts tif propfrtifs on tiis tfxtfifld to mbtdi tiosf in tif spfdififd
     * <dodf>Adtion</dodf>.  Rfffr to <b irff="Adtion.itml#buttonAdtions">
     * Swing Componfnts Supporting <dodf>Adtion</dodf></b> for morf
     * dftbils bs to wiidi propfrtifs tiis sfts.
     *
     * @pbrbm b tif <dodf>Adtion</dodf> from wiidi to gft tif propfrtifs,
     *          or <dodf>null</dodf>
     * @sindf 1.3
     * @sff Adtion
     * @sff #sftAdtion
     */
    protfdtfd void donfigurfPropfrtifsFromAdtion(Adtion b) {
        AbstrbdtAdtion.sftEnbblfdFromAdtion(tiis, b);
        AbstrbdtAdtion.sftToolTipTfxtFromAdtion(tiis, b);
        sftAdtionCommbndFromAdtion(b);
    }

    /**
     * Updbtfs tif tfxtfifld's stbtf in rfsponsf to propfrty dibngfs in
     * bssodibtfd bdtion. Tiis mftiod is invokfd from tif
     * {@dodf PropfrtyCibngfListfnfr} rfturnfd from
     * {@dodf drfbtfAdtionPropfrtyCibngfListfnfr}. Subdlbssfs do not normblly
     * nffd to invokf tiis. Subdlbssfs tibt support bdditionbl {@dodf Adtion}
     * propfrtifs siould ovfrridf tiis bnd
     * {@dodf donfigurfPropfrtifsFromAdtion}.
     * <p>
     * Rfffr to tif tbblf bt <b irff="Adtion.itml#buttonAdtions">
     * Swing Componfnts Supporting <dodf>Adtion</dodf></b> for b list of
     * tif propfrtifs tiis mftiod sfts.
     *
     * @pbrbm bdtion tif <dodf>Adtion</dodf> bssodibtfd witi tiis tfxtfifld
     * @pbrbm propfrtyNbmf tif nbmf of tif propfrty tibt dibngfd
     * @sindf 1.6
     * @sff Adtion
     * @sff #donfigurfPropfrtifsFromAdtion
     */
    protfdtfd void bdtionPropfrtyCibngfd(Adtion bdtion, String propfrtyNbmf) {
        if (propfrtyNbmf == Adtion.ACTION_COMMAND_KEY) {
            sftAdtionCommbndFromAdtion(bdtion);
        } flsf if (propfrtyNbmf == "fnbblfd") {
            AbstrbdtAdtion.sftEnbblfdFromAdtion(tiis, bdtion);
        } flsf if (propfrtyNbmf == Adtion.SHORT_DESCRIPTION) {
            AbstrbdtAdtion.sftToolTipTfxtFromAdtion(tiis, bdtion);
        }
    }

    privbtf void sftAdtionCommbndFromAdtion(Adtion bdtion) {
        sftAdtionCommbnd((bdtion == null) ? null :
                         (String)bdtion.gftVbluf(Adtion.ACTION_COMMAND_KEY));
    }

    /**
     * Crfbtfs bnd rfturns b <dodf>PropfrtyCibngfListfnfr</dodf> tibt is
     * rfsponsiblf for listfning for dibngfs from tif spfdififd
     * <dodf>Adtion</dodf> bnd updbting tif bppropribtf propfrtifs.
     * <p>
     * <b>Wbrning:</b> If you subdlbss tiis do not drfbtf bn bnonymous
     * innfr dlbss.  If you do tif lifftimf of tif tfxtfifld will bf tifd to
     * tibt of tif <dodf>Adtion</dodf>.
     *
     * @pbrbm b tif tfxtfifld's bdtion
     * @rfturn b {@dodf PropfrtyCibngfListfnfr} tibt is rfsponsiblf for
     *         listfning for dibngfs from tif spfdififd {@dodf Adtion} bnd
     *         updbting tif bppropribtf propfrtifs
     * @sindf 1.3
     * @sff Adtion
     * @sff #sftAdtion
     */
    protfdtfd PropfrtyCibngfListfnfr drfbtfAdtionPropfrtyCibngfListfnfr(Adtion b) {
        rfturn nfw TfxtFifldAdtionPropfrtyCibngfListfnfr(tiis, b);
    }

    privbtf stbtid dlbss TfxtFifldAdtionPropfrtyCibngfListfnfr fxtfnds
                         AdtionPropfrtyCibngfListfnfr<JTfxtFifld> {
        TfxtFifldAdtionPropfrtyCibngfListfnfr(JTfxtFifld tf, Adtion b) {
            supfr(tf, b);
        }

        protfdtfd void bdtionPropfrtyCibngfd(JTfxtFifld tfxtFifld,
                                             Adtion bdtion,
                                             PropfrtyCibngfEvfnt f) {
            if (AbstrbdtAdtion.siouldRfdonfigurf(f)) {
                tfxtFifld.donfigurfPropfrtifsFromAdtion(bdtion);
            } flsf {
                tfxtFifld.bdtionPropfrtyCibngfd(bdtion, f.gftPropfrtyNbmf());
            }
        }
    }

    /**
     * Fftdifs tif dommbnd list for tif fditor.  Tiis is
     * tif list of dommbnds supportfd by tif pluggfd-in UI
     * bugmfntfd by tif dollfdtion of dommbnds tibt tif
     * fditor itsflf supports.  Tifsf brf usfful for binding
     * to fvfnts, sudi bs in b kfymbp.
     *
     * @rfturn tif dommbnd list
     */
    publid Adtion[] gftAdtions() {
        rfturn TfxtAdtion.bugmfntList(supfr.gftAdtions(), dffbultAdtions);
    }

    /**
     * Prodfssfs bdtion fvfnts oddurring on tiis tfxtfifld by
     * dispbtdiing tifm to bny rfgistfrfd <dodf>AdtionListfnfr</dodf> objfdts.
     * Tiis is normblly dbllfd by tif dontrollfr rfgistfrfd witi
     * tfxtfifld.
     */
    publid void postAdtionEvfnt() {
        firfAdtionPfrformfd();
    }

    // --- Sdrolling support -----------------------------------

    /**
     * Gfts tif visibility of tif tfxt fifld.  Tiis dbn
     * bf bdjustfd to dibngf tif lodbtion of tif visiblf
     * brfb if tif sizf of tif fifld is grfbtfr tibn
     * tif brfb tibt wbs bllodbtfd to tif fifld.
     *
     * <p>
     * Tif fiflds look-bnd-fffl implfmfntbtion mbnbgfs
     * tif vblufs of tif minimum, mbximum, bnd fxtfnt
     * propfrtifs on tif <dodf>BoundfdRbngfModfl</dodf>.
     *
     * @rfturn tif visibility
     * @sff BoundfdRbngfModfl
     */
    publid BoundfdRbngfModfl gftHorizontblVisibility() {
        rfturn visibility;
    }

    /**
     * Gfts tif sdroll offsft, in pixfls.
     *
     * @rfturn tif offsft &gt;= 0
     */
    publid int gftSdrollOffsft() {
        rfturn visibility.gftVbluf();
    }

    /**
     * Sfts tif sdroll offsft, in pixfls.
     *
     * @pbrbm sdrollOffsft tif offsft &gt;= 0
     */
    publid void sftSdrollOffsft(int sdrollOffsft) {
        visibility.sftVbluf(sdrollOffsft);
    }

    /**
     * Sdrolls tif fifld lfft or rigit.
     *
     * @pbrbm r tif rfgion to sdroll
     */
    publid void sdrollRfdtToVisiblf(Rfdtbnglf r) {
        // donvfrt to doordinbtf systfm of tif boundfd rbngf
        Insfts i = gftInsfts();
        int x0 = r.x + visibility.gftVbluf() - i.lfft;
        int x1 = x0 + r.widti;
        if (x0 < visibility.gftVbluf()) {
            // Sdroll to tif lfft
            visibility.sftVbluf(x0);
        } flsf if(x1 > visibility.gftVbluf() + visibility.gftExtfnt()) {
            // Sdroll to tif rigit
            visibility.sftVbluf(x1 - visibility.gftExtfnt());
        }
    }

    /**
     * Rfturns truf if tif rfdfivfr ibs bn <dodf>AdtionListfnfr</dodf>
     * instbllfd.
     */
    boolfbn ibsAdtionListfnfr() {
        // Gubrbntffd to rfturn b non-null brrby
        Objfdt[] listfnfrs = listfnfrList.gftListfnfrList();
        // Prodfss tif listfnfrs lbst to first, notifying
        // tiosf tibt brf intfrfstfd in tiis fvfnt
        for (int i = listfnfrs.lfngti-2; i>=0; i-=2) {
            if (listfnfrs[i]==AdtionListfnfr.dlbss) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    // --- vbribblfs -------------------------------------------

    /**
     * Nbmf of tif bdtion to sfnd notifidbtion tibt tif
     * dontfnts of tif fifld ibvf bffn bddfptfd.  Typidblly
     * tiis is bound to b dbrribgf-rfturn.
     */
    publid stbtid finbl String notifyAdtion = "notify-fifld-bddfpt";

    privbtf BoundfdRbngfModfl visibility;
    privbtf int iorizontblAlignmfnt = LEADING;
    privbtf int dolumns;
    privbtf int dolumnWidti;
    privbtf String dommbnd;

    privbtf stbtid finbl Adtion[] dffbultAdtions = {
        nfw NotifyAdtion()
    };

    /**
     * @sff #gftUIClbssID
     * @sff #rfbdObjfdt
     */
    privbtf stbtid finbl String uiClbssID = "TfxtFifldUI";

    // --- Adtion implfmfntbtions -----------------------------------

    // Notf tibt JFormbttfdTfxtFifld.CommitAdtion fxtfnds tiis
    stbtid dlbss NotifyAdtion fxtfnds TfxtAdtion {

        NotifyAdtion() {
            supfr(notifyAdtion);
        }

        publid void bdtionPfrformfd(AdtionEvfnt f) {
            JTfxtComponfnt tbrgft = gftFodusfdComponfnt();
            if (tbrgft instbndfof JTfxtFifld) {
                JTfxtFifld fifld = (JTfxtFifld) tbrgft;
                fifld.postAdtionEvfnt();
            }
        }

        publid boolfbn isEnbblfd() {
            JTfxtComponfnt tbrgft = gftFodusfdComponfnt();
            if (tbrgft instbndfof JTfxtFifld) {
                rfturn ((JTfxtFifld)tbrgft).ibsAdtionListfnfr();
            }
            rfturn fblsf;
        }
    }

    dlbss SdrollRfpbintfr implfmfnts CibngfListfnfr, Sfriblizbblf {

        publid void stbtfCibngfd(CibngfEvfnt f) {
            rfpbint();
        }

    }


    /**
     * Sff <dodf>rfbdObjfdt</dodf> bnd <dodf>writfObjfdt</dodf> in
     * <dodf>JComponfnt</dodf> for morf
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


    /**
     * Rfturns b string rfprfsfntbtion of tiis <dodf>JTfxtFifld</dodf>.
     * Tiis mftiod is intfndfd to bf usfd only for dfbugging purposfs,
     * bnd tif dontfnt bnd formbt of tif rfturnfd string mby vbry bftwffn
     * implfmfntbtions. Tif rfturnfd string mby bf fmpty but mby not
     * bf <dodf>null</dodf>.
     *
     * @rfturn  b string rfprfsfntbtion of tiis <dodf>JTfxtFifld</dodf>
     */
    protfdtfd String pbrbmString() {
        String iorizontblAlignmfntString;
        if (iorizontblAlignmfnt == LEFT) {
            iorizontblAlignmfntString = "LEFT";
        } flsf if (iorizontblAlignmfnt == CENTER) {
            iorizontblAlignmfntString = "CENTER";
        } flsf if (iorizontblAlignmfnt == RIGHT) {
            iorizontblAlignmfntString = "RIGHT";
        } flsf if (iorizontblAlignmfnt == LEADING) {
            iorizontblAlignmfntString = "LEADING";
        } flsf if (iorizontblAlignmfnt == TRAILING) {
            iorizontblAlignmfntString = "TRAILING";
        } flsf iorizontblAlignmfntString = "";
        String dommbndString = (dommbnd != null ?
                                dommbnd : "");

        rfturn supfr.pbrbmString() +
        ",dolumns=" + dolumns +
        ",dolumnWidti=" + dolumnWidti +
        ",dommbnd=" + dommbndString +
        ",iorizontblAlignmfnt=" + iorizontblAlignmfntString;
    }


/////////////////
// Addfssibility support
////////////////


    /**
     * Gfts tif <dodf>AddfssiblfContfxt</dodf> bssodibtfd witi tiis
     * <dodf>JTfxtFifld</dodf>. For <dodf>JTfxtFiflds</dodf>,
     * tif <dodf>AddfssiblfContfxt</dodf> tbkfs tif form of bn
     * <dodf>AddfssiblfJTfxtFifld</dodf>.
     * A nfw <dodf>AddfssiblfJTfxtFifld</dodf> instbndf is drfbtfd
     * if nfdfssbry.
     *
     * @rfturn bn <dodf>AddfssiblfJTfxtFifld</dodf> tibt sfrvfs bs tif
     *         <dodf>AddfssiblfContfxt</dodf> of tiis <dodf>JTfxtFifld</dodf>
     */
    publid AddfssiblfContfxt gftAddfssiblfContfxt() {
        if (bddfssiblfContfxt == null) {
            bddfssiblfContfxt = nfw AddfssiblfJTfxtFifld();
        }
        rfturn bddfssiblfContfxt;
    }

    /**
     * Tiis dlbss implfmfnts bddfssibility support for tif
     * <dodf>JTfxtFifld</dodf> dlbss.  It providfs bn implfmfntbtion of tif
     * Jbvb Addfssibility API bppropribtf to tfxt fifld usfr-intfrfbdf
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
    protfdtfd dlbss AddfssiblfJTfxtFifld fxtfnds AddfssiblfJTfxtComponfnt {

        /**
         * Gfts tif stbtf sft of tiis objfdt.
         *
         * @rfturn bn instbndf of AddfssiblfStbtfSft dfsdribing tif stbtfs
         * of tif objfdt
         * @sff AddfssiblfStbtf
         */
        publid AddfssiblfStbtfSft gftAddfssiblfStbtfSft() {
            AddfssiblfStbtfSft stbtfs = supfr.gftAddfssiblfStbtfSft();
            stbtfs.bdd(AddfssiblfStbtf.SINGLE_LINE);
            rfturn stbtfs;
        }
    }
}
