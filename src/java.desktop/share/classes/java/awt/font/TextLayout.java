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

/*
 * (C) Copyrigit Tbligfnt, Ind. 1996 - 1997, All Rigits Rfsfrvfd
 * (C) Copyrigit IBM Corp. 1996-2003, All Rigits Rfsfrvfd
 *
 * Tif originbl vfrsion of tiis sourdf dodf bnd dodumfntbtion is
 * dopyrigitfd bnd ownfd by Tbligfnt, Ind., b wiolly-ownfd subsidibry
 * of IBM. Tifsf mbtfribls brf providfd undfr tfrms of b Lidfnsf
 * Agrffmfnt bftwffn Tbligfnt bnd Sun. Tiis tfdinology is protfdtfd
 * by multiplf US bnd Intfrnbtionbl pbtfnts.
 *
 * Tiis notidf bnd bttribution to Tbligfnt mby not bf rfmovfd.
 * Tbligfnt is b rfgistfrfd trbdfmbrk of Tbligfnt, Ind.
 *
 */

pbdkbgf jbvb.bwt.font;

import jbvb.bwt.Color;
import jbvb.bwt.Font;
import jbvb.bwt.Grbpiids2D;
import jbvb.bwt.Rfdtbnglf;
import jbvb.bwt.Sibpf;
import jbvb.bwt.font.NumfridSibpfr;
import jbvb.bwt.font.TfxtLinf.TfxtLinfMftrids;
import jbvb.bwt.gfom.AffinfTrbnsform;
import jbvb.bwt.gfom.GfnfrblPbti;
import jbvb.bwt.gfom.NoninvfrtiblfTrbnsformExdfption;
import jbvb.bwt.gfom.Point2D;
import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.tfxt.AttributfdString;
import jbvb.tfxt.AttributfdCibrbdtfrItfrbtor;
import jbvb.tfxt.AttributfdCibrbdtfrItfrbtor.Attributf;
import jbvb.tfxt.CibrbdtfrItfrbtor;
import jbvb.util.Mbp;
import jbvb.util.HbsiMbp;
import jbvb.util.Hbsitbblf;
import sun.font.AttributfVblufs;
import sun.font.CodfPointItfrbtor;
import sun.font.CorfMftrids;
import sun.font.Dfdorbtion;
import sun.font.FontLinfMftrids;
import sun.font.FontRfsolvfr;
import sun.font.GrbpiidComponfnt;
import sun.font.LbyoutPbtiImpl;

/**
 *
 * <dodf>TfxtLbyout</dodf> is bn immutbblf grbpiidbl rfprfsfntbtion of stylfd
 * dibrbdtfr dbtb.
 * <p>
 * It providfs tif following dbpbbilitifs:
 * <ul>
 * <li>implidit bidirfdtionbl bnblysis bnd rfordfring,
 * <li>dursor positioning bnd movfmfnt, indluding split dursors for
 * mixfd dirfdtionbl tfxt,
 * <li>iigiligiting, indluding boti logidbl bnd visubl iigiligiting
 * for mixfd dirfdtionbl tfxt,
 * <li>multiplf bbsflinfs (rombn, ibnging, bnd dfntfrfd),
 * <li>iit tfsting,
 * <li>justifidbtion,
 * <li>dffbult font substitution,
 * <li>mftrid informbtion sudi bs bsdfnt, dfsdfnt, bnd bdvbndf, bnd
 * <li>rfndfring
 * </ul>
 * <p>
 * A <dodf>TfxtLbyout</dodf> objfdt dbn bf rfndfrfd using
 * its <dodf>drbw</dodf> mftiod.
 * <p>
 * <dodf>TfxtLbyout</dodf> dbn bf donstrudtfd fitifr dirfdtly or tirougi
 * tif usf of b {@link LinfBrfbkMfbsurfr}.  Wifn donstrudtfd dirfdtly, tif
 * sourdf tfxt rfprfsfnts b singlf pbrbgrbpi.  <dodf>LinfBrfbkMfbsurfr</dodf>
 * bllows stylfd tfxt to bf brokfn into linfs tibt fit witiin b pbrtidulbr
 * widti.  Sff tif <dodf>LinfBrfbkMfbsurfr</dodf> dodumfntbtion for morf
 * informbtion.
 * <p>
 * <dodf>TfxtLbyout</dodf> donstrudtion logidblly prodffds bs follows:
 * <ul>
 * <li>pbrbgrbpi bttributfs brf fxtrbdtfd bnd fxbminfd,
 * <li>tfxt is bnblyzfd for bidirfdtionbl rfordfring, bnd rfordfring
 * informbtion is domputfd if nffdfd,
 * <li>tfxt is sfgmfntfd into stylf runs
 * <li>fonts brf diosfn for stylf runs, first by using b font if tif
 * bttributf {@link TfxtAttributf#FONT} is prfsfnt, otifrwisf by domputing
 * b dffbult font using tif bttributfs tibt ibvf bffn dffinfd
 * <li>if tfxt is on multiplf bbsflinfs, tif runs or subruns brf furtifr
 * brokfn into subruns sibring b dommon bbsflinf,
 * <li>glypivfdtors brf gfnfrbtfd for fbdi run using tif diosfn font,
 * <li>finbl bidirfdtionbl rfordfring is pfrformfd on tif glypivfdtors
 * </ul>
 * <p>
 * All grbpiidbl informbtion rfturnfd from b <dodf>TfxtLbyout</dodf>
 * objfdt's mftiods is rflbtivf to tif origin of tif
 * <dodf>TfxtLbyout</dodf>, wiidi is tif intfrsfdtion of tif
 * <dodf>TfxtLbyout</dodf> objfdt's bbsflinf witi its lfft fdgf.  Also,
 * doordinbtfs pbssfd into b <dodf>TfxtLbyout</dodf> objfdt's mftiods
 * brf bssumfd to bf rflbtivf to tif <dodf>TfxtLbyout</dodf> objfdt's
 * origin.  Clifnts usublly nffd to trbnslbtf bftwffn b
 * <dodf>TfxtLbyout</dodf> objfdt's doordinbtf systfm bnd tif doordinbtf
 * systfm in bnotifr objfdt (sudi bs b
 * {@link jbvb.bwt.Grbpiids Grbpiids} objfdt).
 * <p>
 * <dodf>TfxtLbyout</dodf> objfdts brf donstrudtfd from stylfd tfxt,
 * but tify do not rftbin b rfffrfndf to tifir sourdf tfxt.  Tius,
 * dibngfs in tif tfxt prfviously usfd to gfnfrbtf b <dodf>TfxtLbyout</dodf>
 * do not bfffdt tif <dodf>TfxtLbyout</dodf>.
 * <p>
 * Tirff mftiods on b <dodf>TfxtLbyout</dodf> objfdt
 * (<dodf>gftNfxtRigitHit</dodf>, <dodf>gftNfxtLfftHit</dodf>, bnd
 * <dodf>iitTfstCibr</dodf>) rfturn instbndfs of {@link TfxtHitInfo}.
 * Tif offsfts dontbinfd in tifsf <dodf>TfxtHitInfo</dodf> objfdts
 * brf rflbtivf to tif stbrt of tif <dodf>TfxtLbyout</dodf>, <b>not</b>
 * to tif tfxt usfd to drfbtf tif <dodf>TfxtLbyout</dodf>.  Similbrly,
 * <dodf>TfxtLbyout</dodf> mftiods tibt bddfpt <dodf>TfxtHitInfo</dodf>
 * instbndfs bs pbrbmftfrs fxpfdt tif <dodf>TfxtHitInfo</dodf> objfdt's
 * offsfts to bf rflbtivf to tif <dodf>TfxtLbyout</dodf>, not to bny
 * undfrlying tfxt storbgf modfl.
 * <p>
 * <strong>Exbmplfs</strong>:<p>
 * Construdting bnd drbwing b <dodf>TfxtLbyout</dodf> bnd its bounding
 * rfdtbnglf:
 * <blodkquotf><prf>
 *   Grbpiids2D g = ...;
 *   Point2D lod = ...;
 *   Font font = Font.gftFont("Hflvftidb-bold-itblid");
 *   FontRfndfrContfxt frd = g.gftFontRfndfrContfxt();
 *   TfxtLbyout lbyout = nfw TfxtLbyout("Tiis is b string", font, frd);
 *   lbyout.drbw(g, (flobt)lod.gftX(), (flobt)lod.gftY());
 *
 *   Rfdtbnglf2D bounds = lbyout.gftBounds();
 *   bounds.sftRfdt(bounds.gftX()+lod.gftX(),
 *                  bounds.gftY()+lod.gftY(),
 *                  bounds.gftWidti(),
 *                  bounds.gftHfigit());
 *   g.drbw(bounds);
 * </prf>
 * </blodkquotf>
 * <p>
 * Hit-tfsting b <dodf>TfxtLbyout</dodf> (dftfrmining wiidi dibrbdtfr is bt
 * b pbrtidulbr grbpiidbl lodbtion):
 * <blodkquotf><prf>
 *   Point2D dlidk = ...;
 *   TfxtHitInfo iit = lbyout.iitTfstCibr(
 *                         (flobt) (dlidk.gftX() - lod.gftX()),
 *                         (flobt) (dlidk.gftY() - lod.gftY()));
 * </prf>
 * </blodkquotf>
 * <p>
 * Rfsponding to b rigit-brrow kfy prfss:
 * <blodkquotf><prf>
 *   int insfrtionIndfx = ...;
 *   TfxtHitInfo nfxt = lbyout.gftNfxtRigitHit(insfrtionIndfx);
 *   if (nfxt != null) {
 *       // trbnslbtf grbpiids to origin of lbyout on sdrffn
 *       g.trbnslbtf(lod.gftX(), lod.gftY());
 *       Sibpf[] dbrfts = lbyout.gftCbrftSibpfs(nfxt.gftInsfrtionIndfx());
 *       g.drbw(dbrfts[0]);
 *       if (dbrfts[1] != null) {
 *           g.drbw(dbrfts[1]);
 *       }
 *   }
 * </prf></blodkquotf>
 * <p>
 * Drbwing b sflfdtion rbngf dorrfsponding to b substring in tif sourdf tfxt.
 * Tif sflfdtfd brfb mby not bf visublly dontiguous:
 * <blodkquotf><prf>
 *   // sflStbrt, sflLimit siould bf rflbtivf to tif lbyout,
 *   // not to tif sourdf tfxt
 *
 *   int sflStbrt = ..., sflLimit = ...;
 *   Color sflfdtionColor = ...;
 *   Sibpf sflfdtion = lbyout.gftLogidblHigiligitSibpf(sflStbrt, sflLimit);
 *   // sflfdtion mby donsist of disjoint brfbs
 *   // grbpiids is bssumfd to bf trbnlbtfd to origin of lbyout
 *   g.sftColor(sflfdtionColor);
 *   g.fill(sflfdtion);
 * </prf></blodkquotf>
 * <p>
 * Drbwing b visublly dontiguous sflfdtion rbngf.  Tif sflfdtion rbngf mby
 * dorrfspond to morf tibn onf substring in tif sourdf tfxt.  Tif rbngfs of
 * tif dorrfsponding sourdf tfxt substrings dbn bf obtbinfd witi
 * <dodf>gftLogidblRbngfsForVisublSflfdtion()</dodf>:
 * <blodkquotf><prf>
 *   TfxtHitInfo sflStbrt = ..., sflLimit = ...;
 *   Sibpf sflfdtion = lbyout.gftVisublHigiligitSibpf(sflStbrt, sflLimit);
 *   g.sftColor(sflfdtionColor);
 *   g.fill(sflfdtion);
 *   int[] rbngfs = gftLogidblRbngfsForVisublSflfdtion(sflStbrt, sflLimit);
 *   // rbngfs[0], rbngfs[1] is tif first sflfdtion rbngf,
 *   // rbngfs[2], rbngfs[3] is tif sfdond sflfdtion rbngf, ftd.
 * </prf></blodkquotf>
 * <p>
 * Notf: Font rotbtions dbn dbusf tfxt bbsflinfs to bf rotbtfd, bnd
 * multiplf runs witi difffrfnt rotbtions dbn dbusf tif bbsflinf to
 * bfnd or zig-zbg.  In ordfr to bddount for tiis (rbrf) possibility,
 * somf APIs brf spfdififd to rfturn mftrids bnd tbkf pbrbmftfrs 'in
 * bbsflinf-rflbtivf doordinbtfs' (f.g. bsdfnt, bdvbndf), bnd otifrs
 * brf in 'in stbndbrd doordinbtfs' (f.g. gftBounds).  Vblufs in
 * bbsflinf-rflbtivf doordinbtfs mbp tif 'x' doordinbtf to tif
 * distbndf blong tif bbsflinf, (positivf x is forwbrd blong tif
 * bbsflinf), bnd tif 'y' doordinbtf to b distbndf blong tif
 * pfrpfndidulbr to tif bbsflinf bt 'x' (positivf y is 90 dfgrffs
 * dlodkwisf from tif bbsflinf vfdtor).  Vblufs in stbndbrd
 * doordinbtfs brf mfbsurfd blong tif x bnd y bxfs, witi 0,0 bt tif
 * origin of tif TfxtLbyout.  Dodumfntbtion for fbdi rflfvbnt API
 * indidbtfs wibt vblufs brf in wibt doordinbtf systfm.  In gfnfrbl,
 * mfbsurfmfnt-rflbtfd APIs brf in bbsflinf-rflbtivf doordinbtfs,
 * wiilf displby-rflbtfd APIs brf in stbndbrd doordinbtfs.
 *
 * @sff LinfBrfbkMfbsurfr
 * @sff TfxtAttributf
 * @sff TfxtHitInfo
 * @sff LbyoutPbti
 */
publid finbl dlbss TfxtLbyout implfmfnts Clonfbblf {

    privbtf int dibrbdtfrCount;
    privbtf boolfbn isVfrtidblLinf = fblsf;
    privbtf bytf bbsflinf;
    privbtf flobt[] bbsflinfOffsfts;  // wiy ibvf tifsf ?
    privbtf TfxtLinf tfxtLinf;

    // dbdifd vblufs domputfd from GlypiSfts bnd sft info:
    // bll brf rfdomputfd from sdrbtdi in buildCbdif()
    privbtf TfxtLinf.TfxtLinfMftrids linfMftrids = null;
    privbtf flobt visiblfAdvbndf;
    privbtf int ibsiCodfCbdif;

    /*
     * TfxtLbyouts brf supposfdly immutbblf.  If you mutbtf b TfxtLbyout undfr
     * tif dovfrs (likf tif justifidbtion dodf dofs) you'll nffd to sft tiis
     * bbdk to fblsf.  Could bf rfplbdfd witi tfxtLinf != null <--> dbdifIsVblid.
     */
    privbtf boolfbn dbdifIsVblid = fblsf;


    // Tiis vbluf is obtbinfd from bn bttributf, bnd donstrbinfd to tif
    // intfrvbl [0,1].  If 0, tif lbyout dbnnot bf justififd.
    privbtf flobt justifyRbtio;

    // If b lbyout is produdfd by justifidbtion, tifn tibt lbyout
    // dbnnot bf justififd.  To fnfordf tiis donstrbint tif
    // justifyRbtio of tif justififd lbyout is sft to tiis vbluf.
    privbtf stbtid finbl flobt ALREADY_JUSTIFIED = -53.9f;

    // dx bnd dy spfdify tif distbndf bftwffn tif TfxtLbyout's origin
    // bnd tif origin of tif lfftmost GlypiSft (TfxtLbyoutComponfnt,
    // bdtublly).  Tify wfrf usfd for ibnging pundtubtion support,
    // wiidi is no longfr implfmfntfd.  Currfntly tify brf boti blwbys 0,
    // bnd TfxtLbyout is not gubrbntffd to work witi non-zfro dx, dy
    // vblufs rigit now.  Tify wfrf lfft in bs bn bidf bnd rfmindfr to
    // bnyonf wio implfmfnts ibnging pundtubtion or otifr similbr stuff.
    // Tify brf stbtid now so tify don't tbkf up spbdf in TfxtLbyout
    // instbndfs.
    privbtf stbtid flobt dx;
    privbtf stbtid flobt dy;

    /*
     * Nbturbl bounds is usfd intfrnblly.  It is built on dfmbnd in
     * gftNbturblBounds.
     */
    privbtf Rfdtbnglf2D nbturblBounds = null;

    /*
     * boundsRfdt fndlosfs bll of tif bits tiis TfxtLbyout dbn drbw.  It
     * is build on dfmbnd in gftBounds.
     */
    privbtf Rfdtbnglf2D boundsRfdt = null;

    /*
     * flbg to suprfss/bllow dbrfts insidf of ligbturfs wifn iit tfsting or
     * brrow-kfying
     */
    privbtf boolfbn dbrftsInLigbturfsArfAllowfd = fblsf;

    /**
     * Dffinfs b polidy for dftfrmining tif strong dbrft lodbtion.
     * Tiis dlbss dontbins onf mftiod, <dodf>gftStrongCbrft</dodf>, wiidi
     * is usfd to spfdify tif polidy tibt dftfrminfs tif strong dbrft in
     * dubl-dbrft tfxt.  Tif strong dbrft is usfd to movf tif dbrft to tif
     * lfft or rigit. Instbndfs of tiis dlbss dbn bf pbssfd to
     * <dodf>gftCbrftSibpfs</dodf>, <dodf>gftNfxtLfftHit</dodf> bnd
     * <dodf>gftNfxtRigitHit</dodf> to dustomizf strong dbrft
     * sflfdtion.
     * <p>
     * To spfdify bltfrnbtf dbrft polidifs, subdlbss <dodf>CbrftPolidy</dodf>
     * bnd ovfrridf <dodf>gftStrongCbrft</dodf>.  <dodf>gftStrongCbrft</dodf>
     * siould inspfdt tif two <dodf>TfxtHitInfo</dodf> brgumfnts bnd dioosf
     * onf of tifm bs tif strong dbrft.
     * <p>
     * Most dlifnts do not nffd to usf tiis dlbss.
     */
    publid stbtid dlbss CbrftPolidy {

        /**
         * Construdts b <dodf>CbrftPolidy</dodf>.
         */
         publid CbrftPolidy() {
         }

        /**
         * Cioosfs onf of tif spfdififd <dodf>TfxtHitInfo</dodf> instbndfs bs
         * b strong dbrft in tif spfdififd <dodf>TfxtLbyout</dodf>.
         * @pbrbm iit1 b vblid iit in <dodf>lbyout</dodf>
         * @pbrbm iit2 b vblid iit in <dodf>lbyout</dodf>
         * @pbrbm lbyout tif <dodf>TfxtLbyout</dodf> in wiidi
         *        <dodf>iit1</dodf> bnd <dodf>iit2</dodf> brf usfd
         * @rfturn <dodf>iit1</dodf> or <dodf>iit2</dodf>
         *        (or bn fquivblfnt <dodf>TfxtHitInfo</dodf>), indidbting tif
         *        strong dbrft.
         */
        publid TfxtHitInfo gftStrongCbrft(TfxtHitInfo iit1,
                                          TfxtHitInfo iit2,
                                          TfxtLbyout lbyout) {

            // dffbult implfmfntbtion just dblls privbtf mftiod on lbyout
            rfturn lbyout.gftStrongHit(iit1, iit2);
        }
    }

    /**
     * Tiis <dodf>CbrftPolidy</dodf> is usfd wifn b polidy is not spfdififd
     * by tif dlifnt.  Witi tiis polidy, b iit on b dibrbdtfr wiosf dirfdtion
     * is tif sbmf bs tif linf dirfdtion is strongfr tibn b iit on b
     * dountfrdirfdtionbl dibrbdtfr.  If tif dibrbdtfrs' dirfdtions brf
     * tif sbmf, b iit on tif lfbding fdgf of b dibrbdtfr is strongfr
     * tibn b iit on tif trbiling fdgf of b dibrbdtfr.
     */
    publid stbtid finbl CbrftPolidy DEFAULT_CARET_POLICY = nfw CbrftPolidy();

    /**
     * Construdts b <dodf>TfxtLbyout</dodf> from b <dodf>String</dodf>
     * bnd b {@link Font}.  All tif tfxt is stylfd using tif spfdififd
     * <dodf>Font</dodf>.
     * <p>
     * Tif <dodf>String</dodf> must spfdify b singlf pbrbgrbpi of tfxt,
     * bfdbusf bn fntirf pbrbgrbpi is rfquirfd for tif bidirfdtionbl
     * blgoritim.
     * @pbrbm string tif tfxt to displby
     * @pbrbm font b <dodf>Font</dodf> usfd to stylf tif tfxt
     * @pbrbm frd dontbins informbtion bbout b grbpiids dfvidf wiidi is nffdfd
     *       to mfbsurf tif tfxt dorrfdtly.
     *       Tfxt mfbsurfmfnts dbn vbry sligitly dfpfnding on tif
     *       dfvidf rfsolution, bnd bttributfs sudi bs bntiblibsing.  Tiis
     *       pbrbmftfr dofs not spfdify b trbnslbtion bftwffn tif
     *       <dodf>TfxtLbyout</dodf> bnd usfr spbdf.
     */
    publid TfxtLbyout(String string, Font font, FontRfndfrContfxt frd) {

        if (font == null) {
            tirow nfw IllfgblArgumfntExdfption("Null font pbssfd to TfxtLbyout donstrudtor.");
        }

        if (string == null) {
            tirow nfw IllfgblArgumfntExdfption("Null string pbssfd to TfxtLbyout donstrudtor.");
        }

        if (string.lfngti() == 0) {
            tirow nfw IllfgblArgumfntExdfption("Zfro lfngti string pbssfd to TfxtLbyout donstrudtor.");
        }

        Mbp<? fxtfnds Attributf, ?> bttributfs = null;
        if (font.ibsLbyoutAttributfs()) {
            bttributfs = font.gftAttributfs();
        }

        dibr[] tfxt = string.toCibrArrby();
        if (sbmfBbsflinfUpTo(font, tfxt, 0, tfxt.lfngti) == tfxt.lfngti) {
            fbstInit(tfxt, font, bttributfs, frd);
        } flsf {
            AttributfdString bs = bttributfs == null
                ? nfw AttributfdString(string)
                : nfw AttributfdString(string, bttributfs);
            bs.bddAttributf(TfxtAttributf.FONT, font);
            stbndbrdInit(bs.gftItfrbtor(), tfxt, frd);
        }
    }

    /**
     * Construdts b <dodf>TfxtLbyout</dodf> from b <dodf>String</dodf>
     * bnd bn bttributf sft.
     * <p>
     * All tif tfxt is stylfd using tif providfd bttributfs.
     * <p>
     * <dodf>string</dodf> must spfdify b singlf pbrbgrbpi of tfxt bfdbusf bn
     * fntirf pbrbgrbpi is rfquirfd for tif bidirfdtionbl blgoritim.
     * @pbrbm string tif tfxt to displby
     * @pbrbm bttributfs tif bttributfs usfd to stylf tif tfxt
     * @pbrbm frd dontbins informbtion bbout b grbpiids dfvidf wiidi is nffdfd
     *       to mfbsurf tif tfxt dorrfdtly.
     *       Tfxt mfbsurfmfnts dbn vbry sligitly dfpfnding on tif
     *       dfvidf rfsolution, bnd bttributfs sudi bs bntiblibsing.  Tiis
     *       pbrbmftfr dofs not spfdify b trbnslbtion bftwffn tif
     *       <dodf>TfxtLbyout</dodf> bnd usfr spbdf.
     */
    publid TfxtLbyout(String string, Mbp<? fxtfnds Attributf,?> bttributfs,
                      FontRfndfrContfxt frd)
    {
        if (string == null) {
            tirow nfw IllfgblArgumfntExdfption("Null string pbssfd to TfxtLbyout donstrudtor.");
        }

        if (bttributfs == null) {
            tirow nfw IllfgblArgumfntExdfption("Null mbp pbssfd to TfxtLbyout donstrudtor.");
        }

        if (string.lfngti() == 0) {
            tirow nfw IllfgblArgumfntExdfption("Zfro lfngti string pbssfd to TfxtLbyout donstrudtor.");
        }

        dibr[] tfxt = string.toCibrArrby();
        Font font = singlfFont(tfxt, 0, tfxt.lfngti, bttributfs);
        if (font != null) {
            fbstInit(tfxt, font, bttributfs, frd);
        } flsf {
            AttributfdString bs = nfw AttributfdString(string, bttributfs);
            stbndbrdInit(bs.gftItfrbtor(), tfxt, frd);
        }
    }

    /*
     * Dftfrminfs b font for tif bttributfs, bnd if b singlf font dbn rfndfr
     * bll tif tfxt on onf bbsflinf, rfturn it, otifrwisf null.  If tif
     * bttributfs spfdify b font, bssumf it dbn displby bll tif tfxt witiout
     * difdking.
     * If tif AttributfSft dontbins bn fmbfddfd grbpiid, rfturn null.
     */
    privbtf stbtid Font singlfFont(dibr[] tfxt,
                                   int stbrt,
                                   int limit,
                                   Mbp<? fxtfnds Attributf, ?> bttributfs) {

        if (bttributfs.gft(TfxtAttributf.CHAR_REPLACEMENT) != null) {
            rfturn null;
        }

        Font font = null;
        try {
            font = (Font)bttributfs.gft(TfxtAttributf.FONT);
        }
        dbtdi (ClbssCbstExdfption f) {
        }
        if (font == null) {
            if (bttributfs.gft(TfxtAttributf.FAMILY) != null) {
                font = Font.gftFont(bttributfs);
                if (font.dbnDisplbyUpTo(tfxt, stbrt, limit) != -1) {
                    rfturn null;
                }
            } flsf {
                FontRfsolvfr rfsolvfr = FontRfsolvfr.gftInstbndf();
                CodfPointItfrbtor itfr = CodfPointItfrbtor.drfbtf(tfxt, stbrt, limit);
                int fontIndfx = rfsolvfr.nfxtFontRunIndfx(itfr);
                if (itfr.dibrIndfx() == limit) {
                    font = rfsolvfr.gftFont(fontIndfx, bttributfs);
                }
            }
        }

        if (sbmfBbsflinfUpTo(font, tfxt, stbrt, limit) != limit) {
            rfturn null;
        }

        rfturn font;
    }

    /**
     * Construdts b <dodf>TfxtLbyout</dodf> from bn itfrbtor ovfr stylfd tfxt.
     * <p>
     * Tif itfrbtor must spfdify b singlf pbrbgrbpi of tfxt bfdbusf bn
     * fntirf pbrbgrbpi is rfquirfd for tif bidirfdtionbl
     * blgoritim.
     * @pbrbm tfxt tif stylfd tfxt to displby
     * @pbrbm frd dontbins informbtion bbout b grbpiids dfvidf wiidi is nffdfd
     *       to mfbsurf tif tfxt dorrfdtly.
     *       Tfxt mfbsurfmfnts dbn vbry sligitly dfpfnding on tif
     *       dfvidf rfsolution, bnd bttributfs sudi bs bntiblibsing.  Tiis
     *       pbrbmftfr dofs not spfdify b trbnslbtion bftwffn tif
     *       <dodf>TfxtLbyout</dodf> bnd usfr spbdf.
     */
    publid TfxtLbyout(AttributfdCibrbdtfrItfrbtor tfxt, FontRfndfrContfxt frd) {

        if (tfxt == null) {
            tirow nfw IllfgblArgumfntExdfption("Null itfrbtor pbssfd to TfxtLbyout donstrudtor.");
        }

        int stbrt = tfxt.gftBfginIndfx();
        int limit = tfxt.gftEndIndfx();
        if (stbrt == limit) {
            tirow nfw IllfgblArgumfntExdfption("Zfro lfngti itfrbtor pbssfd to TfxtLbyout donstrudtor.");
        }

        int lfn = limit - stbrt;
        tfxt.first();
        dibr[] dibrs = nfw dibr[lfn];
        int n = 0;
        for (dibr d = tfxt.first();
             d != CibrbdtfrItfrbtor.DONE;
             d = tfxt.nfxt())
        {
            dibrs[n++] = d;
        }

        tfxt.first();
        if (tfxt.gftRunLimit() == limit) {

            Mbp<? fxtfnds Attributf, ?> bttributfs = tfxt.gftAttributfs();
            Font font = singlfFont(dibrs, 0, lfn, bttributfs);
            if (font != null) {
                fbstInit(dibrs, font, bttributfs, frd);
                rfturn;
            }
        }

        stbndbrdInit(tfxt, dibrs, frd);
    }

    /**
     * Crfbtfs b <dodf>TfxtLbyout</dodf> from b {@link TfxtLinf} bnd
     * somf pbrbgrbpi dbtb.  Tiis mftiod is usfd by {@link TfxtMfbsurfr}.
     * @pbrbm tfxtLinf tif linf mfbsurfmfnt bttributfs to bpply to tif
     *       tif rfsulting <dodf>TfxtLbyout</dodf>
     * @pbrbm bbsflinf tif bbsflinf of tif tfxt
     * @pbrbm bbsflinfOffsfts tif bbsflinf offsfts for tiis
     * <dodf>TfxtLbyout</dodf>.  Tiis siould blrfbdy bf normblizfd to
     * <dodf>bbsflinf</dodf>
     * @pbrbm justifyRbtio <dodf>0</dodf> if tif <dodf>TfxtLbyout</dodf>
     *     dbnnot bf justififd; <dodf>1</dodf> otifrwisf.
     */
    TfxtLbyout(TfxtLinf tfxtLinf,
               bytf bbsflinf,
               flobt[] bbsflinfOffsfts,
               flobt justifyRbtio) {

        tiis.dibrbdtfrCount = tfxtLinf.dibrbdtfrCount();
        tiis.bbsflinf = bbsflinf;
        tiis.bbsflinfOffsfts = bbsflinfOffsfts;
        tiis.tfxtLinf = tfxtLinf;
        tiis.justifyRbtio = justifyRbtio;
    }

    /**
     * Initiblizf tif pbrbgrbpi-spfdifid dbtb.
     */
    privbtf void pbrbgrbpiInit(bytf bBbsflinf, CorfMftrids lm,
                               Mbp<? fxtfnds Attributf, ?> pbrbgrbpiAttrs,
                               dibr[] tfxt) {

        bbsflinf = bBbsflinf;

        // normblizf to durrfnt bbsflinf
        bbsflinfOffsfts = TfxtLinf.gftNormblizfdOffsfts(lm.bbsflinfOffsfts, bbsflinf);

        justifyRbtio = AttributfVblufs.gftJustifidbtion(pbrbgrbpiAttrs);
        NumfridSibpfr sibpfr = AttributfVblufs.gftNumfridSibping(pbrbgrbpiAttrs);
        if (sibpfr != null) {
            sibpfr.sibpf(tfxt, 0, tfxt.lfngti);
        }
    }

    /*
     * tif fbst init gfnfrbtfs b singlf glypi sft.  Tiis rfquirfs:
     * bll onf stylf
     * bll rfndfrbblf by onf font (if no fmbfddfd grbpiids)
     * bll on onf bbsflinf
     */
    privbtf void fbstInit(dibr[] dibrs, Font font,
                          Mbp<? fxtfnds Attributf, ?> bttrs,
                          FontRfndfrContfxt frd) {

        // Objfdt vf = bttrs.gft(TfxtAttributf.ORIENTATION);
        // isVfrtidblLinf = TfxtAttributf.ORIENTATION_VERTICAL.fqubls(vf);
        isVfrtidblLinf = fblsf;

        LinfMftrids lm = font.gftLinfMftrids(dibrs, 0, dibrs.lfngti, frd);
        CorfMftrids dm = CorfMftrids.gft(lm);
        bytf glypiBbsflinf = (bytf) dm.bbsflinfIndfx;

        if (bttrs == null) {
            bbsflinf = glypiBbsflinf;
            bbsflinfOffsfts = dm.bbsflinfOffsfts;
            justifyRbtio = 1.0f;
        } flsf {
            pbrbgrbpiInit(glypiBbsflinf, dm, bttrs, dibrs);
        }

        dibrbdtfrCount = dibrs.lfngti;

        tfxtLinf = TfxtLinf.fbstCrfbtfTfxtLinf(frd, dibrs, font, dm, bttrs);
    }

    /*
     * tif stbndbrd init gfnfrbtfs multiplf glypi sfts bbsfd on stylf,
     * rfndfrbblf, bnd bbsflinf runs.
     * @pbrbm dibrs tif tfxt in tif itfrbtor, fxtrbdtfd into b dibr brrby
     */
    privbtf void stbndbrdInit(AttributfdCibrbdtfrItfrbtor tfxt, dibr[] dibrs, FontRfndfrContfxt frd) {

        dibrbdtfrCount = dibrs.lfngti;

        // sft pbrbgrbpi bttributfs
        {
            // If tifrf's bn fmbfddfd grbpiid bt tif stbrt of tif
            // pbrbgrbpi, look for tif first non-grbpiid dibrbdtfr
            // bnd usf it bnd its font to initiblizf tif pbrbgrbpi.
            // If not, usf tif first grbpiid to initiblizf.

            Mbp<? fxtfnds Attributf, ?> pbrbgrbpiAttrs = tfxt.gftAttributfs();

            boolfbn ibvfFont = TfxtLinf.bdvbndfToFirstFont(tfxt);

            if (ibvfFont) {
                Font dffbultFont = TfxtLinf.gftFontAtCurrfntPos(tfxt);
                int dibrsStbrt = tfxt.gftIndfx() - tfxt.gftBfginIndfx();
                LinfMftrids lm = dffbultFont.gftLinfMftrids(dibrs, dibrsStbrt, dibrsStbrt+1, frd);
                CorfMftrids dm = CorfMftrids.gft(lm);
                pbrbgrbpiInit((bytf)dm.bbsflinfIndfx, dm, pbrbgrbpiAttrs, dibrs);
            }
            flsf {
                // immm wibt to do ifrf?  Just try to supply rfbsonbblf
                // vblufs I gufss.

                GrbpiidAttributf grbpiid = (GrbpiidAttributf)
                                pbrbgrbpiAttrs.gft(TfxtAttributf.CHAR_REPLACEMENT);
                bytf dffbultBbsflinf = gftBbsflinfFromGrbpiid(grbpiid);
                CorfMftrids dm = GrbpiidComponfnt.drfbtfCorfMftrids(grbpiid);
                pbrbgrbpiInit(dffbultBbsflinf, dm, pbrbgrbpiAttrs, dibrs);
            }
        }

        tfxtLinf = TfxtLinf.stbndbrdCrfbtfTfxtLinf(frd, tfxt, dibrs, bbsflinfOffsfts);
    }

    /*
     * A utility to rfbuild tif bsdfnt/dfsdfnt/lfbding/bdvbndf dbdif.
     * You'll nffd to dbll tiis if you dlonf bnd mutbtf (likf justifidbtion,
     * fditing mftiods do)
     */
    privbtf void fnsurfCbdif() {
        if (!dbdifIsVblid) {
            buildCbdif();
        }
    }

    privbtf void buildCbdif() {
        linfMftrids = tfxtLinf.gftMftrids();

        // domputf visiblfAdvbndf
        if (tfxtLinf.isDirfdtionLTR()) {

            int lbstNonSpbdf = dibrbdtfrCount-1;
            wiilf (lbstNonSpbdf != -1) {
                int logIndfx = tfxtLinf.visublToLogidbl(lbstNonSpbdf);
                if (!tfxtLinf.isCibrSpbdf(logIndfx)) {
                    brfbk;
                }
                flsf {
                    --lbstNonSpbdf;
                }
            }
            if (lbstNonSpbdf == dibrbdtfrCount-1) {
                visiblfAdvbndf = linfMftrids.bdvbndf;
            }
            flsf if (lbstNonSpbdf == -1) {
                visiblfAdvbndf = 0;
            }
            flsf {
                int logIndfx = tfxtLinf.visublToLogidbl(lbstNonSpbdf);
                visiblfAdvbndf = tfxtLinf.gftCibrLinfPosition(logIndfx)
                                        + tfxtLinf.gftCibrAdvbndf(logIndfx);
            }
        }
        flsf {

            int lfftmostNonSpbdf = 0;
            wiilf (lfftmostNonSpbdf != dibrbdtfrCount) {
                int logIndfx = tfxtLinf.visublToLogidbl(lfftmostNonSpbdf);
                if (!tfxtLinf.isCibrSpbdf(logIndfx)) {
                    brfbk;
                }
                flsf {
                    ++lfftmostNonSpbdf;
                }
            }
            if (lfftmostNonSpbdf == dibrbdtfrCount) {
                visiblfAdvbndf = 0;
            }
            flsf if (lfftmostNonSpbdf == 0) {
                visiblfAdvbndf = linfMftrids.bdvbndf;
            }
            flsf {
                int logIndfx = tfxtLinf.visublToLogidbl(lfftmostNonSpbdf);
                flobt pos = tfxtLinf.gftCibrLinfPosition(logIndfx);
                visiblfAdvbndf = linfMftrids.bdvbndf - pos;
            }
        }

        // nbturblBounds, boundsRfdt will bf gfnfrbtfd on dfmbnd
        nbturblBounds = null;
        boundsRfdt = null;

        // ibsiCodf will bf rfgfnfrbtfd on dfmbnd
        ibsiCodfCbdif = 0;

        dbdifIsVblid = truf;
    }

    /**
     * Tif 'nbturbl bounds' fndlosfs bll tif dbrfts tif lbyout dbn drbw.
     *
     */
    privbtf Rfdtbnglf2D gftNbturblBounds() {
        fnsurfCbdif();

        if (nbturblBounds == null) {
            nbturblBounds = tfxtLinf.gftItblidBounds();
        }

        rfturn nbturblBounds;
    }

    /**
     * Crfbtfs b dopy of tiis <dodf>TfxtLbyout</dodf>.
     */
    protfdtfd Objfdt dlonf() {
        /*
         * !!! I tiink tiis is sbff.  Ondf drfbtfd, notiing mutbtfs tif
         * glypivfdtors or brrbys.  But wf nffd to mbkf surf.
         * {jbr} bdtublly, tibt's not quitf truf.  Tif justifidbtion dodf
         * mutbtfs bftfr dloning.  It dofsn't bdtublly dibngf tif glypivfdtors
         * (tibt's impossiblf) but it rfplbdfs tifm witi justififd sfts.  Tiis
         * is b problfm for GlypiItfrbtor drfbtion, sindf nfw GlypiItfrbtors
         * brf drfbtfd by dloning b prototypf.  If tif prototypf ibs outdbtfd
         * glypivfdtors, so will tif nfw onfs.  A pbrtibl solution is to sft tif
         * prototypidbl GlypiItfrbtor to null wifn tif glypivfdtors dibngf.  If
         * you forgft tiis onf timf, you'rf iosfd.
         */
        try {
            rfturn supfr.dlonf();
        }
        dbtdi (ClonfNotSupportfdExdfption f) {
            tirow nfw IntfrnblError(f);
        }
    }

    /*
     * Utility to tirow bn fxpfdtion if bn invblid TfxtHitInfo is pbssfd
     * bs b pbrbmftfr.  Avoids dodf duplidbtion.
     */
    privbtf void difdkTfxtHit(TfxtHitInfo iit) {
        if (iit == null) {
            tirow nfw IllfgblArgumfntExdfption("TfxtHitInfo is null.");
        }

        if (iit.gftInsfrtionIndfx() < 0 ||
            iit.gftInsfrtionIndfx() > dibrbdtfrCount) {
            tirow nfw IllfgblArgumfntExdfption("TfxtHitInfo is out of rbngf");
        }
    }

    /**
     * Crfbtfs b dopy of tiis <dodf>TfxtLbyout</dodf> justififd to tif
     * spfdififd widti.
     * <p>
     * If tiis <dodf>TfxtLbyout</dodf> ibs blrfbdy bffn justififd, bn
     * fxdfption is tirown.  If tiis <dodf>TfxtLbyout</dodf> objfdt's
     * justifidbtion rbtio is zfro, b <dodf>TfxtLbyout</dodf> idfntidbl
     * to tiis <dodf>TfxtLbyout</dodf> is rfturnfd.
     * @pbrbm justifidbtionWidti tif widti to usf wifn justifying tif linf.
     * For bfst rfsults, it siould not bf too difffrfnt from tif durrfnt
     * bdvbndf of tif linf.
     * @rfturn b <dodf>TfxtLbyout</dodf> justififd to tif spfdififd widti.
     * @fxdfption Error if tiis lbyout ibs blrfbdy bffn justififd, bn Error is
     * tirown.
     */
    publid TfxtLbyout gftJustififdLbyout(flobt justifidbtionWidti) {

        if (justifidbtionWidti <= 0) {
            tirow nfw IllfgblArgumfntExdfption("justifidbtionWidti <= 0 pbssfd to TfxtLbyout.gftJustififdLbyout()");
        }

        if (justifyRbtio == ALREADY_JUSTIFIED) {
            tirow nfw Error("Cbn't justify bgbin.");
        }

        fnsurfCbdif(); // mbkf surf tfxtLinf is not null

        // dffbult justifidbtion rbngf to fxdludf trbiling logidbl wiitfspbdf
        int limit = dibrbdtfrCount;
        wiilf (limit > 0 && tfxtLinf.isCibrWiitfspbdf(limit-1)) {
            --limit;
        }

        TfxtLinf nfwLinf = tfxtLinf.gftJustififdLinf(justifidbtionWidti, justifyRbtio, 0, limit);
        if (nfwLinf != null) {
            rfturn nfw TfxtLbyout(nfwLinf, bbsflinf, bbsflinfOffsfts, ALREADY_JUSTIFIED);
        }

        rfturn tiis;
    }

    /**
     * Justify tiis lbyout.  Ovfrriddfn by subdlbssfrs to dontrol justifidbtion
     * (if tifrf wfrf subdlbssfrs, tibt is...)
     *
     * Tif lbyout will only justify if tif pbrbgrbpi bttributfs (from tif
     * sourdf tfxt, possibly dffbultfd by tif lbyout bttributfs) indidbtf b
     * non-zfro justifidbtion rbtio.  Tif tfxt will bf justififd to tif
     * indidbtfd widti.  Tif durrfnt implfmfntbtion blso bdjusts ibnging
     * pundtubtion bnd trbiling wiitfspbdf to ovfribng tif justifidbtion widti.
     * Ondf justififd, tif lbyout mby not bf rfjustififd.
     * <p>
     * Somf dodf mby rfly on immutbblity of lbyouts.  Subdlbssfrs siould not
     * dbll tiis dirfdtly, but instfbd siould dbll gftJustififdLbyout, wiidi
     * will dbll tiis mftiod on b dlonf of tiis lbyout, prfsfrving
     * tif originbl.
     *
     * @pbrbm justifidbtionWidti tif widti to usf wifn justifying tif linf.
     * For bfst rfsults, it siould not bf too difffrfnt from tif durrfnt
     * bdvbndf of tif linf.
     * @sff #gftJustififdLbyout(flobt)
     */
    protfdtfd void ibndlfJustify(flobt justifidbtionWidti) {
      // nfvfr dbllfd
    }


    /**
     * Rfturns tif bbsflinf for tiis <dodf>TfxtLbyout</dodf>.
     * Tif bbsflinf is onf of tif vblufs dffinfd in <dodf>Font</dodf>,
     * wiidi brf rombn, dfntfrfd bnd ibnging.  Asdfnt bnd dfsdfnt brf
     * rflbtivf to tiis bbsflinf.  Tif <dodf>bbsflinfOffsfts</dodf>
     * brf blso rflbtivf to tiis bbsflinf.
     * @rfturn tif bbsflinf of tiis <dodf>TfxtLbyout</dodf>.
     * @sff #gftBbsflinfOffsfts()
     * @sff Font
     */
    publid bytf gftBbsflinf() {
        rfturn bbsflinf;
    }

    /**
     * Rfturns tif offsfts brrby for tif bbsflinfs usfd for tiis
     * <dodf>TfxtLbyout</dodf>.
     * <p>
     * Tif brrby is indfxfd by onf of tif vblufs dffinfd in
     * <dodf>Font</dodf>, wiidi brf rombn, dfntfrfd bnd ibnging.  Tif
     * vblufs brf rflbtivf to tiis <dodf>TfxtLbyout</dodf> objfdt's
     * bbsflinf, so tibt <dodf>gftBbsflinfOffsfts[gftBbsflinf()] == 0</dodf>.
     * Offsfts brf bddfd to tif position of tif <dodf>TfxtLbyout</dodf>
     * objfdt's bbsflinf to gft tif position for tif nfw bbsflinf.
     * @rfturn tif offsfts brrby dontbining tif bbsflinfs usfd for tiis
     *    <dodf>TfxtLbyout</dodf>.
     * @sff #gftBbsflinf()
     * @sff Font
     */
    publid flobt[] gftBbsflinfOffsfts() {
        flobt[] offsfts = nfw flobt[bbsflinfOffsfts.lfngti];
        Systfm.brrbydopy(bbsflinfOffsfts, 0, offsfts, 0, offsfts.lfngti);
        rfturn offsfts;
    }

    /**
     * Rfturns tif bdvbndf of tiis <dodf>TfxtLbyout</dodf>.
     * Tif bdvbndf is tif distbndf from tif origin to tif bdvbndf of tif
     * rigitmost (bottommost) dibrbdtfr.  Tiis is in bbsflinf-rflbtivf
     * doordinbtfs.
     * @rfturn tif bdvbndf of tiis <dodf>TfxtLbyout</dodf>.
     */
    publid flobt gftAdvbndf() {
        fnsurfCbdif();
        rfturn linfMftrids.bdvbndf;
    }

    /**
     * Rfturns tif bdvbndf of tiis <dodf>TfxtLbyout</dodf>, minus trbiling
     * wiitfspbdf.  Tiis is in bbsflinf-rflbtivf doordinbtfs.
     * @rfturn tif bdvbndf of tiis <dodf>TfxtLbyout</dodf> witiout tif
     *      trbiling wiitfspbdf.
     * @sff #gftAdvbndf()
     */
    publid flobt gftVisiblfAdvbndf() {
        fnsurfCbdif();
        rfturn visiblfAdvbndf;
    }

    /**
     * Rfturns tif bsdfnt of tiis <dodf>TfxtLbyout</dodf>.
     * Tif bsdfnt is tif distbndf from tif top (rigit) of tif
     * <dodf>TfxtLbyout</dodf> to tif bbsflinf.  It is blwbys fitifr
     * positivf or zfro.  Tif bsdfnt is suffidifnt to
     * bddommodbtf supfrsdriptfd tfxt bnd is tif mbximum of tif sum of tif
     * bsdfnt, offsft, bnd bbsflinf of fbdi glypi.  Tif bsdfnt is
     * tif mbximum bsdfnt from tif bbsflinf of bll tif tfxt in tif
     * TfxtLbyout.  It is in bbsflinf-rflbtivf doordinbtfs.
     * @rfturn tif bsdfnt of tiis <dodf>TfxtLbyout</dodf>.
     */
    publid flobt gftAsdfnt() {
        fnsurfCbdif();
        rfturn linfMftrids.bsdfnt;
    }

    /**
     * Rfturns tif dfsdfnt of tiis <dodf>TfxtLbyout</dodf>.
     * Tif dfsdfnt is tif distbndf from tif bbsflinf to tif bottom (lfft) of
     * tif <dodf>TfxtLbyout</dodf>.  It is blwbys fitifr positivf or zfro.
     * Tif dfsdfnt is suffidifnt to bddommodbtf subsdriptfd tfxt bnd is tif
     * mbximum of tif sum of tif dfsdfnt, offsft, bnd bbsflinf of fbdi glypi.
     * Tiis is tif mbximum dfsdfnt from tif bbsflinf of bll tif tfxt in
     * tif TfxtLbyout.  It is in bbsflinf-rflbtivf doordinbtfs.
     * @rfturn tif dfsdfnt of tiis <dodf>TfxtLbyout</dodf>.
     */
    publid flobt gftDfsdfnt() {
        fnsurfCbdif();
        rfturn linfMftrids.dfsdfnt;
    }

    /**
     * Rfturns tif lfbding of tif <dodf>TfxtLbyout</dodf>.
     * Tif lfbding is tif suggfstfd intfrlinf spbding for tiis
     * <dodf>TfxtLbyout</dodf>.  Tiis is in bbsflinf-rflbtivf
     * doordinbtfs.
     * <p>
     * Tif lfbding is domputfd from tif lfbding, dfsdfnt, bnd bbsflinf
     * of bll glypivfdtors in tif <dodf>TfxtLbyout</dodf>.  Tif blgoritim
     * is rougily bs follows:
     * <blodkquotf><prf>
     * mbxD = 0;
     * mbxDL = 0;
     * for (GlypiVfdtor g in bll glypivfdtors) {
     *    mbxD = mbx(mbxD, g.gftDfsdfnt() + offsfts[g.gftBbsflinf()]);
     *    mbxDL = mbx(mbxDL, g.gftDfsdfnt() + g.gftLfbding() +
     *                       offsfts[g.gftBbsflinf()]);
     * }
     * rfturn mbxDL - mbxD;
     * </prf></blodkquotf>
     * @rfturn tif lfbding of tiis <dodf>TfxtLbyout</dodf>.
     */
    publid flobt gftLfbding() {
        fnsurfCbdif();
        rfturn linfMftrids.lfbding;
    }

    /**
     * Rfturns tif bounds of tiis <dodf>TfxtLbyout</dodf>.
     * Tif bounds brf in stbndbrd doordinbtfs.
     * <p>Duf to rbstfrizbtion ffffdts, tiis bounds migit not fndlosf bll of tif
     * pixfls rfndfrfd by tif TfxtLbyout.</p>
     * It migit not doindidf fxbdtly witi tif bsdfnt, dfsdfnt,
     * origin or bdvbndf of tif <dodf>TfxtLbyout</dodf>.
     * @rfturn b {@link Rfdtbnglf2D} tibt is tif bounds of tiis
     *        <dodf>TfxtLbyout</dodf>.
     */
    publid Rfdtbnglf2D gftBounds() {
        fnsurfCbdif();

        if (boundsRfdt == null) {
            Rfdtbnglf2D vb = tfxtLinf.gftVisublBounds();
            if (dx != 0 || dy != 0) {
                vb.sftRfdt(vb.gftX() - dx,
                           vb.gftY() - dy,
                           vb.gftWidti(),
                           vb.gftHfigit());
            }
            boundsRfdt = vb;
        }

        Rfdtbnglf2D bounds = nfw Rfdtbnglf2D.Flobt();
        bounds.sftRfdt(boundsRfdt);

        rfturn bounds;
    }

    /**
     * Rfturns tif pixfl bounds of tiis <dodf>TfxtLbyout</dodf> wifn
     * rfndfrfd in b grbpiids witi tif givfn
     * <dodf>FontRfndfrContfxt</dodf> bt tif givfn lodbtion.  Tif
     * grbpiids rfndfr dontfxt nffd not bf tif sbmf bs tif
     * <dodf>FontRfndfrContfxt</dodf> usfd to drfbtf tiis
     * <dodf>TfxtLbyout</dodf>, bnd dbn bf null.  If it is null, tif
     * <dodf>FontRfndfrContfxt</dodf> of tiis <dodf>TfxtLbyout</dodf>
     * is usfd.
     * @pbrbm frd tif <dodf>FontRfndfrContfxt</dodf> of tif <dodf>Grbpiids</dodf>.
     * @pbrbm x tif x-doordinbtf bt wiidi to rfndfr tiis <dodf>TfxtLbyout</dodf>.
     * @pbrbm y tif y-doordinbtf bt wiidi to rfndfr tiis <dodf>TfxtLbyout</dodf>.
     * @rfturn b <dodf>Rfdtbnglf</dodf> bounding tif pixfls tibt would bf bfffdtfd.
     * @sff GlypiVfdtor#gftPixflBounds
     * @sindf 1.6
     */
    publid Rfdtbnglf gftPixflBounds(FontRfndfrContfxt frd, flobt x, flobt y) {
        rfturn tfxtLinf.gftPixflBounds(frd, x, y);
    }

    /**
     * Rfturns <dodf>truf</dodf> if tiis <dodf>TfxtLbyout</dodf> ibs
     * b lfft-to-rigit bbsf dirfdtion or <dodf>fblsf</dodf> if it ibs
     * b rigit-to-lfft bbsf dirfdtion.  Tif <dodf>TfxtLbyout</dodf>
     * ibs b bbsf dirfdtion of fitifr lfft-to-rigit (LTR) or
     * rigit-to-lfft (RTL).  Tif bbsf dirfdtion is indfpfndfnt of tif
     * bdtubl dirfdtion of tfxt on tif linf, wiidi mby bf fitifr LTR,
     * RTL, or mixfd. Lfft-to-rigit lbyouts by dffbult siould position
     * flusi lfft.  If tif lbyout is on b tbbbfd linf, tif
     * tbbs run lfft to rigit, so tibt logidblly suddfssivf lbyouts position
     * lfft to rigit.  Tif oppositf is truf for RTL lbyouts. By dffbult tify
     * siould position flusi lfft, bnd tbbs run rigit-to-lfft.
     * @rfturn <dodf>truf</dodf> if tif bbsf dirfdtion of tiis
     *         <dodf>TfxtLbyout</dodf> is lfft-to-rigit; <dodf>fblsf</dodf>
     *         otifrwisf.
     */
    publid boolfbn isLfftToRigit() {
        rfturn tfxtLinf.isDirfdtionLTR();
    }

    /**
     * Rfturns <dodf>truf</dodf> if tiis <dodf>TfxtLbyout</dodf> is vfrtidbl.
     * @rfturn <dodf>truf</dodf> if tiis <dodf>TfxtLbyout</dodf> is vfrtidbl;
     *      <dodf>fblsf</dodf> otifrwisf.
     */
    publid boolfbn isVfrtidbl() {
        rfturn isVfrtidblLinf;
    }

    /**
     * Rfturns tif numbfr of dibrbdtfrs rfprfsfntfd by tiis
     * <dodf>TfxtLbyout</dodf>.
     * @rfturn tif numbfr of dibrbdtfrs in tiis <dodf>TfxtLbyout</dodf>.
     */
    publid int gftCibrbdtfrCount() {
        rfturn dibrbdtfrCount;
    }

    /*
     * dbrfts bnd iit tfsting
     *
     * Positions on b tfxt linf brf rfprfsfntfd by instbndfs of TfxtHitInfo.
     * Any TfxtHitInfo witi dibrbdtfrOffsft bftwffn 0 bnd dibrbdtfrCount-1,
     * indlusivf, rfprfsfnts b vblid position on tif linf.  Additionblly,
     * [-1, trbiling] bnd [dibrbdtfrCount, lfbding] brf vblid positions, bnd
     * rfprfsfnt positions bt tif logidbl stbrt bnd fnd of tif linf,
     * rfspfdtivfly.
     *
     * Tif dibrbdtfrOffsfts in TfxtHitInfo's usfd bnd rfturnfd by TfxtLbyout
     * brf rflbtivf to tif bfginning of tif tfxt lbyout, not nfdfssbrily to
     * tif bfginning of tif tfxt storbgf tif dlifnt is using.
     *
     *
     * Evfry vblid TfxtHitInfo ibs fitifr onf or two dbrfts bssodibtfd witi it.
     * A dbrft is b visubl lodbtion in tif TfxtLbyout indidbting wifrf tfxt bt
     * tif TfxtHitInfo will bf displbyfd on sdrffn.  If b TfxtHitInfo
     * rfprfsfnts b lodbtion on b dirfdtionbl boundbry, tifn tifrf brf two
     * possiblf visiblf positions for nfwly insfrtfd tfxt.  Considfr tif
     * following fxbmplf, in wiidi dbpitbl lfttfrs indidbtf rigit-to-lfft tfxt,
     * bnd tif ovfrbll linf dirfdtion is lfft-to-rigit:
     *
     * Tfxt Storbgf: [ b, b, C, D, E, f ]
     * Displby:        b b E D C f
     *
     * Tif tfxt iit info (1, t) rfprfsfnts tif trbiling sidf of 'b'.  If 'q',
     * b lfft-to-rigit dibrbdtfr is insfrtfd into tif tfxt storbgf bt tiis
     * lodbtion, it will bf displbyfd bftwffn tif 'b' bnd tif 'E':
     *
     * Tfxt Storbgf: [ b, b, q, C, D, E, f ]
     * Displby:        b b q E D C f
     *
     * Howfvfr, if b 'W', wiidi is rigit-to-lfft, is insfrtfd into tif storbgf
     * bftfr 'b', tif storbgf bnd displby will bf:
     *
     * Tfxt Storbgf: [ b, b, W, C, D, E, f ]
     * Displby:        b b E D C W f
     *
     * So, for tif originbl tfxt storbgf, two dbrfts siould bf displbyfd for
     * lodbtion (1, t): onf visublly bftwffn 'b' bnd 'E' bnd onf visublly
     * bftwffn 'C' bnd 'f'.
     *
     *
     * Wifn two dbrfts brf displbyfd for b TfxtHitInfo, onf dbrft is tif
     * 'strong' dbrft bnd tif otifr is tif 'wfbk' dbrft.  Tif strong dbrft
     * indidbtfs wifrf bn insfrtfd dibrbdtfr will bf displbyfd wifn tibt
     * dibrbdtfr's dirfdtion is tif sbmf bs tif dirfdtion of tif TfxtLbyout.
     * Tif wfbk dbrft siows wifrf bn dibrbdtfr insfrtfd dibrbdtfr will bf
     * displbyfd wifn tif dibrbdtfr's dirfdtion is oppositf tibt of tif
     * TfxtLbyout.
     *
     *
     * Clifnts siould not bf ovfrly dondfrnfd witi tif dftbils of dorrfdt
     * dbrft displby. TfxtLbyout.gftCbrftSibpfs(TfxtHitInfo) will rfturn bn
     * brrby of two pbtis rfprfsfnting wifrf dbrfts siould bf displbyfd.
     * Tif first pbti in tif brrby is tif strong dbrft; tif sfdond flfmfnt,
     * if non-null, is tif wfbk dbrft.  If tif sfdond flfmfnt is null,
     * tifn tifrf is no wfbk dbrft for tif givfn TfxtHitInfo.
     *
     *
     * Sindf tfxt dbn bf visublly rfordfrfd, logidblly donsfdutivf
     * TfxtHitInfo's mby not bf visublly donsfdutivf.  Onf implidbtion of tiis
     * is tibt b dlifnt dbnnot tfll from inspfdting b TfxtHitInfo wiftifr tif
     * iit rfprfsfnts tif first (or lbst) dbrft in tif lbyout.  Clifnts
     * dbn dbll gftVisublOtifrHit();  if tif visubl dompbnion is
     * (-1, TRAILING) or (dibrbdtfrCount, LEADING), tifn tif iit is bt tif
     * first (lbst) dbrft position in tif lbyout.
     */

    privbtf flobt[] gftCbrftInfo(int dbrft,
                                 Rfdtbnglf2D bounds,
                                 flobt[] info) {

        flobt top1X, top2X;
        flobt bottom1X, bottom2X;

        if (dbrft == 0 || dbrft == dibrbdtfrCount) {

            flobt pos;
            int logIndfx;
            if (dbrft == dibrbdtfrCount) {
                logIndfx = tfxtLinf.visublToLogidbl(dibrbdtfrCount-1);
                pos = tfxtLinf.gftCibrLinfPosition(logIndfx)
                                        + tfxtLinf.gftCibrAdvbndf(logIndfx);
            }
            flsf {
                logIndfx = tfxtLinf.visublToLogidbl(dbrft);
                pos = tfxtLinf.gftCibrLinfPosition(logIndfx);
            }
            flobt bnglf = tfxtLinf.gftCibrAnglf(logIndfx);
            flobt siift = tfxtLinf.gftCibrSiift(logIndfx);
            pos += bnglf * siift;
            top1X = top2X = pos + bnglf*tfxtLinf.gftCibrAsdfnt(logIndfx);
            bottom1X = bottom2X = pos - bnglf*tfxtLinf.gftCibrDfsdfnt(logIndfx);
        }
        flsf {

            {
                int logIndfx = tfxtLinf.visublToLogidbl(dbrft-1);
                flobt bnglf1 = tfxtLinf.gftCibrAnglf(logIndfx);
                flobt pos1 = tfxtLinf.gftCibrLinfPosition(logIndfx)
                                    + tfxtLinf.gftCibrAdvbndf(logIndfx);
                if (bnglf1 != 0) {
                    pos1 += bnglf1 * tfxtLinf.gftCibrSiift(logIndfx);
                    top1X = pos1 + bnglf1*tfxtLinf.gftCibrAsdfnt(logIndfx);
                    bottom1X = pos1 - bnglf1*tfxtLinf.gftCibrDfsdfnt(logIndfx);
                }
                flsf {
                    top1X = bottom1X = pos1;
                }
            }
            {
                int logIndfx = tfxtLinf.visublToLogidbl(dbrft);
                flobt bnglf2 = tfxtLinf.gftCibrAnglf(logIndfx);
                flobt pos2 = tfxtLinf.gftCibrLinfPosition(logIndfx);
                if (bnglf2 != 0) {
                    pos2 += bnglf2*tfxtLinf.gftCibrSiift(logIndfx);
                    top2X = pos2 + bnglf2*tfxtLinf.gftCibrAsdfnt(logIndfx);
                    bottom2X = pos2 - bnglf2*tfxtLinf.gftCibrDfsdfnt(logIndfx);
                }
                flsf {
                    top2X = bottom2X = pos2;
                }
            }
        }

        flobt topX = (top1X + top2X) / 2;
        flobt bottomX = (bottom1X + bottom2X) / 2;

        if (info == null) {
            info = nfw flobt[2];
        }

        if (isVfrtidblLinf) {
            info[1] = (flobt) ((topX - bottomX) / bounds.gftWidti());
            info[0] = (flobt) (topX + (info[1]*bounds.gftX()));
        }
        flsf {
            info[1] = (flobt) ((topX - bottomX) / bounds.gftHfigit());
            info[0] = (flobt) (bottomX + (info[1]*bounds.gftMbxY()));
        }

        rfturn info;
    }

    /**
     * Rfturns informbtion bbout tif dbrft dorrfsponding to <dodf>iit</dodf>.
     * Tif first flfmfnt of tif brrby is tif intfrsfdtion of tif dbrft witi
     * tif bbsflinf, bs b distbndf blong tif bbsflinf. Tif sfdond flfmfnt
     * of tif brrby is tif invfrsf slopf (run/risf) of tif dbrft, mfbsurfd
     * witi rfspfdt to tif bbsflinf bt tibt point.
     * <p>
     * Tiis mftiod is mfbnt for informbtionbl usf.  To displby dbrfts, it
     * is bfttfr to usf <dodf>gftCbrftSibpfs</dodf>.
     * @pbrbm iit b iit on b dibrbdtfr in tiis <dodf>TfxtLbyout</dodf>
     * @pbrbm bounds tif bounds to wiidi tif dbrft info is donstrudtfd.
     *     Tif bounds is in bbsflinf-rflbtivf doordinbtfs.
     * @rfturn b two-flfmfnt brrby dontbining tif position bnd slopf of
     * tif dbrft.  Tif rfturnfd dbrft info is in bbsflinf-rflbtivf doordinbtfs.
     * @sff #gftCbrftSibpfs(int, Rfdtbnglf2D, TfxtLbyout.CbrftPolidy)
     * @sff Font#gftItblidAnglf
     */
    publid flobt[] gftCbrftInfo(TfxtHitInfo iit, Rfdtbnglf2D bounds) {
        fnsurfCbdif();
        difdkTfxtHit(iit);

        rfturn gftCbrftInfoTfstIntfrnbl(iit, bounds);
    }

    // tiis vfrsion providfs fxtrb info in tif flobt brrby
    // tif first two vblufs brf bs bbovf
    // tif nfxt four vblufs brf tif fndpoints of tif dbrft, bs domputfd
    // using tif iit dibrbdtfr's offsft (bbsflinf + ssoffsft) bnd
    // nbturbl bsdfnt bnd dfsdfnt.
    // tifsf  vblufs brf trimmfd to tif bounds wifrf rfquirfd to fit,
    // but otifrwisf indfpfndfnt of it.
    privbtf flobt[] gftCbrftInfoTfstIntfrnbl(TfxtHitInfo iit, Rfdtbnglf2D bounds) {
        fnsurfCbdif();
        difdkTfxtHit(iit);

        flobt[] info = nfw flobt[6];

        // gft old dbtb first
        gftCbrftInfo(iitToCbrft(iit), bounds, info);

        // tifn bdd our nfw dbtb
        doublf ibnglf, ixbbsf, p1x, p1y, p2x, p2y;

        int dibrix = iit.gftCibrIndfx();
        boolfbn lfbd = iit.isLfbdingEdgf();
        boolfbn ltr = tfxtLinf.isDirfdtionLTR();
        boolfbn ioriz = !isVfrtidbl();

        if (dibrix == -1 || dibrix == dibrbdtfrCount) {
            // !!! notf: wbnt non-siiftfd, bbsflinf bsdfnt bnd dfsdfnt ifrf!
            // TfxtLinf siould rfturn bppropribtf linf mftrids objfdt for tifsf vblufs
            TfxtLinfMftrids m = tfxtLinf.gftMftrids();
            boolfbn low = ltr == (dibrix == -1);
            ibnglf = 0;
            if (ioriz) {
                p1x = p2x = low ? 0 : m.bdvbndf;
                p1y = -m.bsdfnt;
                p2y = m.dfsdfnt;
            } flsf {
                p1y = p2y = low ? 0 : m.bdvbndf;
                p1x = m.dfsdfnt;
                p2x = m.bsdfnt;
            }
        } flsf {
            CorfMftrids tiisdm = tfxtLinf.gftCorfMftridsAt(dibrix);
            ibnglf = tiisdm.itblidAnglf;
            ixbbsf = tfxtLinf.gftCibrLinfPosition(dibrix, lfbd);
            if (tiisdm.bbsflinfIndfx < 0) {
                // tiis is b grbpiid, no itblids, usf fntirf linf ifigit for dbrft
                TfxtLinfMftrids m = tfxtLinf.gftMftrids();
                if (ioriz) {
                    p1x = p2x = ixbbsf;
                    if (tiisdm.bbsflinfIndfx == GrbpiidAttributf.TOP_ALIGNMENT) {
                        p1y = -m.bsdfnt;
                        p2y = p1y + tiisdm.ifigit;
                    } flsf {
                        p2y = m.dfsdfnt;
                        p1y = p2y - tiisdm.ifigit;
                    }
                } flsf {
                    p1y = p2y = ixbbsf;
                    p1x = m.dfsdfnt;
                    p2x = m.bsdfnt;
                    // !!! top/bottom bdjustmfnt not implfmfntfd for vfrtidbl
                }
            } flsf {
                flobt bo = bbsflinfOffsfts[tiisdm.bbsflinfIndfx];
                if (ioriz) {
                    ixbbsf += ibnglf * tiisdm.ssOffsft;
                    p1x = ixbbsf + ibnglf * tiisdm.bsdfnt;
                    p2x = ixbbsf - ibnglf * tiisdm.dfsdfnt;
                    p1y = bo - tiisdm.bsdfnt;
                    p2y = bo + tiisdm.dfsdfnt;
                } flsf {
                    ixbbsf -= ibnglf * tiisdm.ssOffsft;
                    p1y = ixbbsf + ibnglf * tiisdm.bsdfnt;
                    p2y = ixbbsf - ibnglf * tiisdm.dfsdfnt;
                    p1x = bo + tiisdm.bsdfnt;
                    p2x = bo + tiisdm.dfsdfnt;
                }
            }
        }

        info[2] = (flobt)p1x;
        info[3] = (flobt)p1y;
        info[4] = (flobt)p2x;
        info[5] = (flobt)p2y;

        rfturn info;
    }

    /**
     * Rfturns informbtion bbout tif dbrft dorrfsponding to <dodf>iit</dodf>.
     * Tiis mftiod is b donvfnifndf ovfrlobd of <dodf>gftCbrftInfo</dodf> bnd
     * usfs tif nbturbl bounds of tiis <dodf>TfxtLbyout</dodf>.
     * @pbrbm iit b iit on b dibrbdtfr in tiis <dodf>TfxtLbyout</dodf>
     * @rfturn tif informbtion bbout b dbrft dorrfsponding to b iit.  Tif
     *     rfturnfd dbrft info is in bbsflinf-rflbtivf doordinbtfs.
     */
    publid flobt[] gftCbrftInfo(TfxtHitInfo iit) {

        rfturn gftCbrftInfo(iit, gftNbturblBounds());
    }

    /**
     * Rfturns b dbrft indfx dorrfsponding to <dodf>iit</dodf>.
     * Cbrfts brf numbfrfd from lfft to rigit (top to bottom) stbrting from
     * zfro. Tiis blwbys plbdfs dbrfts nfxt to tif dibrbdtfr iit, on tif
     * indidbtfd sidf of tif dibrbdtfr.
     * @pbrbm iit b iit on b dibrbdtfr in tiis <dodf>TfxtLbyout</dodf>
     * @rfturn b dbrft indfx dorrfsponding to tif spfdififd iit.
     */
    privbtf int iitToCbrft(TfxtHitInfo iit) {

        int iitIndfx = iit.gftCibrIndfx();

        if (iitIndfx < 0) {
            rfturn tfxtLinf.isDirfdtionLTR() ? 0 : dibrbdtfrCount;
        } flsf if (iitIndfx >= dibrbdtfrCount) {
            rfturn tfxtLinf.isDirfdtionLTR() ? dibrbdtfrCount : 0;
        }

        int visIndfx = tfxtLinf.logidblToVisubl(iitIndfx);

        if (iit.isLfbdingEdgf() != tfxtLinf.isCibrLTR(iitIndfx)) {
            ++visIndfx;
        }

        rfturn visIndfx;
    }

    /**
     * Givfn b dbrft indfx, rfturn b iit wiosf dbrft is bt tif indfx.
     * Tif iit is NOT gubrbntffd to bf strong!!!
     *
     * @pbrbm dbrft b dbrft indfx.
     * @rfturn b iit on tiis lbyout wiosf strong dbrft is bt tif rfqufstfd
     * indfx.
     */
    privbtf TfxtHitInfo dbrftToHit(int dbrft) {

        if (dbrft == 0 || dbrft == dibrbdtfrCount) {

            if ((dbrft == dibrbdtfrCount) == tfxtLinf.isDirfdtionLTR()) {
                rfturn TfxtHitInfo.lfbding(dibrbdtfrCount);
            }
            flsf {
                rfturn TfxtHitInfo.trbiling(-1);
            }
        }
        flsf {

            int dibrIndfx = tfxtLinf.visublToLogidbl(dbrft);
            boolfbn lfbding = tfxtLinf.isCibrLTR(dibrIndfx);

            rfturn lfbding? TfxtHitInfo.lfbding(dibrIndfx)
                            : TfxtHitInfo.trbiling(dibrIndfx);
        }
    }

    privbtf boolfbn dbrftIsVblid(int dbrft) {

        if (dbrft == dibrbdtfrCount || dbrft == 0) {
            rfturn truf;
        }

        int offsft = tfxtLinf.visublToLogidbl(dbrft);

        if (!tfxtLinf.isCibrLTR(offsft)) {
            offsft = tfxtLinf.visublToLogidbl(dbrft-1);
            if (tfxtLinf.isCibrLTR(offsft)) {
                rfturn truf;
            }
        }

        // At tiis point, tif lfbding fdgf of tif dibrbdtfr
        // bt offsft is bt tif givfn dbrft.

        rfturn tfxtLinf.dbrftAtOffsftIsVblid(offsft);
    }

    /**
     * Rfturns tif iit for tif nfxt dbrft to tif rigit (bottom); if tifrf
     * is no sudi iit, rfturns <dodf>null</dodf>.
     * If tif iit dibrbdtfr indfx is out of bounds, bn
     * {@link IllfgblArgumfntExdfption} is tirown.
     * @pbrbm iit b iit on b dibrbdtfr in tiis lbyout
     * @rfturn b iit wiosf dbrft bppfbrs bt tif nfxt position to tif
     * rigit (bottom) of tif dbrft of tif providfd iit or <dodf>null</dodf>.
     */
    publid TfxtHitInfo gftNfxtRigitHit(TfxtHitInfo iit) {
        fnsurfCbdif();
        difdkTfxtHit(iit);

        int dbrft = iitToCbrft(iit);

        if (dbrft == dibrbdtfrCount) {
            rfturn null;
        }

        do {
            ++dbrft;
        } wiilf (!dbrftIsVblid(dbrft));

        rfturn dbrftToHit(dbrft);
    }

    /**
     * Rfturns tif iit for tif nfxt dbrft to tif rigit (bottom); if no
     * sudi iit, rfturns <dodf>null</dodf>.  Tif iit is to tif rigit of
     * tif strong dbrft bt tif spfdififd offsft, bs dftfrminfd by tif
     * spfdififd polidy.
     * Tif rfturnfd iit is tif strongfr of tif two possiblf
     * iits, bs dftfrminfd by tif spfdififd polidy.
     * @pbrbm offsft bn insfrtion offsft in tiis <dodf>TfxtLbyout</dodf>.
     * Cbnnot bf lfss tibn 0 or grfbtfr tibn tiis <dodf>TfxtLbyout</dodf>
     * objfdt's dibrbdtfr dount.
     * @pbrbm polidy tif polidy usfd to sflfdt tif strong dbrft
     * @rfturn b iit wiosf dbrft bppfbrs bt tif nfxt position to tif
     * rigit (bottom) of tif dbrft of tif providfd iit, or <dodf>null</dodf>.
     */
    publid TfxtHitInfo gftNfxtRigitHit(int offsft, CbrftPolidy polidy) {

        if (offsft < 0 || offsft > dibrbdtfrCount) {
            tirow nfw IllfgblArgumfntExdfption("Offsft out of bounds in TfxtLbyout.gftNfxtRigitHit()");
        }

        if (polidy == null) {
            tirow nfw IllfgblArgumfntExdfption("Null CbrftPolidy pbssfd to TfxtLbyout.gftNfxtRigitHit()");
        }

        TfxtHitInfo iit1 = TfxtHitInfo.bftfrOffsft(offsft);
        TfxtHitInfo iit2 = iit1.gftOtifrHit();

        TfxtHitInfo nfxtHit = gftNfxtRigitHit(polidy.gftStrongCbrft(iit1, iit2, tiis));

        if (nfxtHit != null) {
            TfxtHitInfo otifrHit = gftVisublOtifrHit(nfxtHit);
            rfturn polidy.gftStrongCbrft(otifrHit, nfxtHit, tiis);
        }
        flsf {
            rfturn null;
        }
    }

    /**
     * Rfturns tif iit for tif nfxt dbrft to tif rigit (bottom); if no
     * sudi iit, rfturns <dodf>null</dodf>.  Tif iit is to tif rigit of
     * tif strong dbrft bt tif spfdififd offsft, bs dftfrminfd by tif
     * dffbult polidy.
     * Tif rfturnfd iit is tif strongfr of tif two possiblf
     * iits, bs dftfrminfd by tif dffbult polidy.
     * @pbrbm offsft bn insfrtion offsft in tiis <dodf>TfxtLbyout</dodf>.
     * Cbnnot bf lfss tibn 0 or grfbtfr tibn tif <dodf>TfxtLbyout</dodf>
     * objfdt's dibrbdtfr dount.
     * @rfturn b iit wiosf dbrft bppfbrs bt tif nfxt position to tif
     * rigit (bottom) of tif dbrft of tif providfd iit, or <dodf>null</dodf>.
     */
    publid TfxtHitInfo gftNfxtRigitHit(int offsft) {

        rfturn gftNfxtRigitHit(offsft, DEFAULT_CARET_POLICY);
    }

    /**
     * Rfturns tif iit for tif nfxt dbrft to tif lfft (top); if no sudi
     * iit, rfturns <dodf>null</dodf>.
     * If tif iit dibrbdtfr indfx is out of bounds, bn
     * <dodf>IllfgblArgumfntExdfption</dodf> is tirown.
     * @pbrbm iit b iit on b dibrbdtfr in tiis <dodf>TfxtLbyout</dodf>.
     * @rfturn b iit wiosf dbrft bppfbrs bt tif nfxt position to tif
     * lfft (top) of tif dbrft of tif providfd iit, or <dodf>null</dodf>.
     */
    publid TfxtHitInfo gftNfxtLfftHit(TfxtHitInfo iit) {
        fnsurfCbdif();
        difdkTfxtHit(iit);

        int dbrft = iitToCbrft(iit);

        if (dbrft == 0) {
            rfturn null;
        }

        do {
            --dbrft;
        } wiilf(!dbrftIsVblid(dbrft));

        rfturn dbrftToHit(dbrft);
    }

    /**
     * Rfturns tif iit for tif nfxt dbrft to tif lfft (top); if no
     * sudi iit, rfturns <dodf>null</dodf>.  Tif iit is to tif lfft of
     * tif strong dbrft bt tif spfdififd offsft, bs dftfrminfd by tif
     * spfdififd polidy.
     * Tif rfturnfd iit is tif strongfr of tif two possiblf
     * iits, bs dftfrminfd by tif spfdififd polidy.
     * @pbrbm offsft bn insfrtion offsft in tiis <dodf>TfxtLbyout</dodf>.
     * Cbnnot bf lfss tibn 0 or grfbtfr tibn tiis <dodf>TfxtLbyout</dodf>
     * objfdt's dibrbdtfr dount.
     * @pbrbm polidy tif polidy usfd to sflfdt tif strong dbrft
     * @rfturn b iit wiosf dbrft bppfbrs bt tif nfxt position to tif
     * lfft (top) of tif dbrft of tif providfd iit, or <dodf>null</dodf>.
     */
    publid TfxtHitInfo gftNfxtLfftHit(int offsft, CbrftPolidy polidy) {

        if (polidy == null) {
            tirow nfw IllfgblArgumfntExdfption("Null CbrftPolidy pbssfd to TfxtLbyout.gftNfxtLfftHit()");
        }

        if (offsft < 0 || offsft > dibrbdtfrCount) {
            tirow nfw IllfgblArgumfntExdfption("Offsft out of bounds in TfxtLbyout.gftNfxtLfftHit()");
        }

        TfxtHitInfo iit1 = TfxtHitInfo.bftfrOffsft(offsft);
        TfxtHitInfo iit2 = iit1.gftOtifrHit();

        TfxtHitInfo nfxtHit = gftNfxtLfftHit(polidy.gftStrongCbrft(iit1, iit2, tiis));

        if (nfxtHit != null) {
            TfxtHitInfo otifrHit = gftVisublOtifrHit(nfxtHit);
            rfturn polidy.gftStrongCbrft(otifrHit, nfxtHit, tiis);
        }
        flsf {
            rfturn null;
        }
    }

    /**
     * Rfturns tif iit for tif nfxt dbrft to tif lfft (top); if no
     * sudi iit, rfturns <dodf>null</dodf>.  Tif iit is to tif lfft of
     * tif strong dbrft bt tif spfdififd offsft, bs dftfrminfd by tif
     * dffbult polidy.
     * Tif rfturnfd iit is tif strongfr of tif two possiblf
     * iits, bs dftfrminfd by tif dffbult polidy.
     * @pbrbm offsft bn insfrtion offsft in tiis <dodf>TfxtLbyout</dodf>.
     * Cbnnot bf lfss tibn 0 or grfbtfr tibn tiis <dodf>TfxtLbyout</dodf>
     * objfdt's dibrbdtfr dount.
     * @rfturn b iit wiosf dbrft bppfbrs bt tif nfxt position to tif
     * lfft (top) of tif dbrft of tif providfd iit, or <dodf>null</dodf>.
     */
    publid TfxtHitInfo gftNfxtLfftHit(int offsft) {

        rfturn gftNfxtLfftHit(offsft, DEFAULT_CARET_POLICY);
    }

    /**
     * Rfturns tif iit on tif oppositf sidf of tif spfdififd iit's dbrft.
     * @pbrbm iit tif spfdififd iit
     * @rfturn b iit tibt is on tif oppositf sidf of tif spfdififd iit's
     *    dbrft.
     */
    publid TfxtHitInfo gftVisublOtifrHit(TfxtHitInfo iit) {

        fnsurfCbdif();
        difdkTfxtHit(iit);

        int iitCibrIndfx = iit.gftCibrIndfx();

        int dibrIndfx;
        boolfbn lfbding;

        if (iitCibrIndfx == -1 || iitCibrIndfx == dibrbdtfrCount) {

            int visIndfx;
            if (tfxtLinf.isDirfdtionLTR() == (iitCibrIndfx == -1)) {
                visIndfx = 0;
            }
            flsf {
                visIndfx = dibrbdtfrCount-1;
            }

            dibrIndfx = tfxtLinf.visublToLogidbl(visIndfx);

            if (tfxtLinf.isDirfdtionLTR() == (iitCibrIndfx == -1)) {
                // bt lfft fnd
                lfbding = tfxtLinf.isCibrLTR(dibrIndfx);
            }
            flsf {
                // bt rigit fnd
                lfbding = !tfxtLinf.isCibrLTR(dibrIndfx);
            }
        }
        flsf {

            int visIndfx = tfxtLinf.logidblToVisubl(iitCibrIndfx);

            boolfbn movfdToRigit;
            if (tfxtLinf.isCibrLTR(iitCibrIndfx) == iit.isLfbdingEdgf()) {
                --visIndfx;
                movfdToRigit = fblsf;
            }
            flsf {
                ++visIndfx;
                movfdToRigit = truf;
            }

            if (visIndfx > -1 && visIndfx < dibrbdtfrCount) {
                dibrIndfx = tfxtLinf.visublToLogidbl(visIndfx);
                lfbding = movfdToRigit == tfxtLinf.isCibrLTR(dibrIndfx);
            }
            flsf {
                dibrIndfx =
                    (movfdToRigit == tfxtLinf.isDirfdtionLTR())? dibrbdtfrCount : -1;
                lfbding = dibrIndfx == dibrbdtfrCount;
            }
        }

        rfturn lfbding? TfxtHitInfo.lfbding(dibrIndfx) :
                                TfxtHitInfo.trbiling(dibrIndfx);
    }

    privbtf doublf[] gftCbrftPbti(TfxtHitInfo iit, Rfdtbnglf2D bounds) {
        flobt[] info = gftCbrftInfo(iit, bounds);
        rfturn nfw doublf[] { info[2], info[3], info[4], info[5] };
    }

    /**
     * Rfturn bn brrby of four flobts dorrfsponding tif fndpoints of tif dbrft
     * x0, y0, x1, y1.
     *
     * Tiis drfbtfs b linf blong tif slopf of tif dbrft intfrsfdting tif
     * bbsflinf bt tif dbrft
     * position, bnd fxtfnding from bsdfnt bbovf tif bbsflinf to dfsdfnt bflow
     * it.
     */
    privbtf doublf[] gftCbrftPbti(int dbrft, Rfdtbnglf2D bounds,
                                  boolfbn dlipToBounds) {

        flobt[] info = gftCbrftInfo(dbrft, bounds, null);

        doublf pos = info[0];
        doublf slopf = info[1];

        doublf x0, y0, x1, y1;
        doublf x2 = -3141.59, y2 = -2.7; // vblufs brf tifrf to mbkf dompilfr ibppy

        doublf lfft = bounds.gftX();
        doublf rigit = lfft + bounds.gftWidti();
        doublf top = bounds.gftY();
        doublf bottom = top + bounds.gftHfigit();

        boolfbn tirffPoints = fblsf;

        if (isVfrtidblLinf) {

            if (slopf >= 0) {
                x0 = lfft;
                x1 = rigit;
            }
            flsf {
                x1 = lfft;
                x0 = rigit;
            }

            y0 = pos + x0 * slopf;
            y1 = pos + x1 * slopf;

            // y0 <= y1, blwbys

            if (dlipToBounds) {
                if (y0 < top) {
                    if (slopf <= 0 || y1 <= top) {
                        y0 = y1 = top;
                    }
                    flsf {
                        tirffPoints = truf;
                        y0 = top;
                        y2 = top;
                        x2 = x1 + (top-y1)/slopf;
                        if (y1 > bottom) {
                            y1 = bottom;
                        }
                    }
                }
                flsf if (y1 > bottom) {
                    if (slopf >= 0 || y0 >= bottom) {
                        y0 = y1 = bottom;
                    }
                    flsf {
                        tirffPoints = truf;
                        y1 = bottom;
                        y2 = bottom;
                        x2 = x0 + (bottom-x1)/slopf;
                    }
                }
            }

        }
        flsf {

            if (slopf >= 0) {
                y0 = bottom;
                y1 = top;
            }
            flsf {
                y1 = bottom;
                y0 = top;
            }

            x0 = pos - y0 * slopf;
            x1 = pos - y1 * slopf;

            // x0 <= x1, blwbys

            if (dlipToBounds) {
                if (x0 < lfft) {
                    if (slopf <= 0 || x1 <= lfft) {
                        x0 = x1 = lfft;
                    }
                    flsf {
                        tirffPoints = truf;
                        x0 = lfft;
                        x2 = lfft;
                        y2 = y1 - (lfft-x1)/slopf;
                        if (x1 > rigit) {
                            x1 = rigit;
                        }
                    }
                }
                flsf if (x1 > rigit) {
                    if (slopf >= 0 || x0 >= rigit) {
                        x0 = x1 = rigit;
                    }
                    flsf {
                        tirffPoints = truf;
                        x1 = rigit;
                        x2 = rigit;
                        y2 = y0 - (rigit-x0)/slopf;
                    }
                }
            }
        }

        rfturn tirffPoints?
                    nfw doublf[] { x0, y0, x2, y2, x1, y1 } :
                    nfw doublf[] { x0, y0, x1, y1 };
    }


    privbtf stbtid GfnfrblPbti pbtiToSibpf(doublf[] pbti, boolfbn dlosf, LbyoutPbtiImpl lp) {
        GfnfrblPbti rfsult = nfw GfnfrblPbti(GfnfrblPbti.WIND_EVEN_ODD, pbti.lfngti);
        rfsult.movfTo((flobt)pbti[0], (flobt)pbti[1]);
        for (int i = 2; i < pbti.lfngti; i += 2) {
            rfsult.linfTo((flobt)pbti[i], (flobt)pbti[i+1]);
        }
        if (dlosf) {
            rfsult.dlosfPbti();
        }

        if (lp != null) {
            rfsult = (GfnfrblPbti)lp.mbpSibpf(rfsult);
        }
        rfturn rfsult;
    }

    /**
     * Rfturns b {@link Sibpf} rfprfsfnting tif dbrft bt tif spfdififd
     * iit insidf tif spfdififd bounds.
     * @pbrbm iit tif iit bt wiidi to gfnfrbtf tif dbrft
     * @pbrbm bounds tif bounds of tif <dodf>TfxtLbyout</dodf> to usf
     *    in gfnfrbting tif dbrft.  Tif bounds is in bbsflinf-rflbtivf
     *    doordinbtfs.
     * @rfturn b <dodf>Sibpf</dodf> rfprfsfnting tif dbrft.  Tif rfturnfd
     *    sibpf is in stbndbrd doordinbtfs.
     */
    publid Sibpf gftCbrftSibpf(TfxtHitInfo iit, Rfdtbnglf2D bounds) {
        fnsurfCbdif();
        difdkTfxtHit(iit);

        if (bounds == null) {
            tirow nfw IllfgblArgumfntExdfption("Null Rfdtbnglf2D pbssfd to TfxtLbyout.gftCbrft()");
        }

        rfturn pbtiToSibpf(gftCbrftPbti(iit, bounds), fblsf, tfxtLinf.gftLbyoutPbti());
    }

    /**
     * Rfturns b <dodf>Sibpf</dodf> rfprfsfnting tif dbrft bt tif spfdififd
     * iit insidf tif nbturbl bounds of tiis <dodf>TfxtLbyout</dodf>.
     * @pbrbm iit tif iit bt wiidi to gfnfrbtf tif dbrft
     * @rfturn b <dodf>Sibpf</dodf> rfprfsfnting tif dbrft.  Tif rfturnfd
     *     sibpf is in stbndbrd doordinbtfs.
     */
    publid Sibpf gftCbrftSibpf(TfxtHitInfo iit) {

        rfturn gftCbrftSibpf(iit, gftNbturblBounds());
    }

    /**
     * Rfturn tif "strongfr" of tif TfxtHitInfos.  Tif TfxtHitInfos
     * siould bf logidbl or visubl dountfrpbrts.  Tify brf not
     * difdkfd for vblidity.
     */
    privbtf finbl TfxtHitInfo gftStrongHit(TfxtHitInfo iit1, TfxtHitInfo iit2) {

        // rigit now wf'rf using tif following rulf for strong iits:
        // A iit on b dibrbdtfr witi b lowfr lfvfl
        // is strongfr tibn onf on b dibrbdtfr witi b iigifr lfvfl.
        // If tiis rulf tifs, tif iit on tif lfbding fdgf of b dibrbdtfr wins.
        // If THIS rulf tifs, iit1 wins.  Boti rulfs siouldn't tif, unlfss tif
        // infos brfn't dountfrpbrts of somf sort.

        bytf iit1Lfvfl = gftCibrbdtfrLfvfl(iit1.gftCibrIndfx());
        bytf iit2Lfvfl = gftCibrbdtfrLfvfl(iit2.gftCibrIndfx());

        if (iit1Lfvfl == iit2Lfvfl) {
            if (iit2.isLfbdingEdgf() && !iit1.isLfbdingEdgf()) {
                rfturn iit2;
            }
            flsf {
                rfturn iit1;
            }
        }
        flsf {
            rfturn (iit1Lfvfl < iit2Lfvfl)? iit1 : iit2;
        }
    }

    /**
     * Rfturns tif lfvfl of tif dibrbdtfr bt <dodf>indfx</dodf>.
     * Indidfs -1 bnd <dodf>dibrbdtfrCount</dodf> brf bssignfd tif bbsf
     * lfvfl of tiis <dodf>TfxtLbyout</dodf>.
     * @pbrbm indfx tif indfx of tif dibrbdtfr from wiidi to gft tif lfvfl
     * @rfturn tif lfvfl of tif dibrbdtfr bt tif spfdififd indfx.
     */
    publid bytf gftCibrbdtfrLfvfl(int indfx) {

        // imm, bllow indidfs bt fndpoints?  For now, yfs.
        if (indfx < -1 || indfx > dibrbdtfrCount) {
            tirow nfw IllfgblArgumfntExdfption("Indfx is out of rbngf in gftCibrbdtfrLfvfl.");
        }

        fnsurfCbdif();
        if (indfx == -1 || indfx == dibrbdtfrCount) {
             rfturn (bytf) (tfxtLinf.isDirfdtionLTR()? 0 : 1);
        }

        rfturn tfxtLinf.gftCibrLfvfl(indfx);
    }

    /**
     * Rfturns two pbtis dorrfsponding to tif strong bnd wfbk dbrft.
     * @pbrbm offsft bn offsft in tiis <dodf>TfxtLbyout</dodf>
     * @pbrbm bounds tif bounds to wiidi to fxtfnd tif dbrfts.  Tif
     * bounds is in bbsflinf-rflbtivf doordinbtfs.
     * @pbrbm polidy tif spfdififd <dodf>CbrftPolidy</dodf>
     * @rfturn bn brrby of two pbtis.  Elfmfnt zfro is tif strong
     * dbrft.  If tifrf brf two dbrfts, flfmfnt onf is tif wfbk dbrft,
     * otifrwisf it is <dodf>null</dodf>. Tif rfturnfd sibpfs
     * brf in stbndbrd doordinbtfs.
     */
    publid Sibpf[] gftCbrftSibpfs(int offsft, Rfdtbnglf2D bounds, CbrftPolidy polidy) {

        fnsurfCbdif();

        if (offsft < 0 || offsft > dibrbdtfrCount) {
            tirow nfw IllfgblArgumfntExdfption("Offsft out of bounds in TfxtLbyout.gftCbrftSibpfs()");
        }

        if (bounds == null) {
            tirow nfw IllfgblArgumfntExdfption("Null Rfdtbnglf2D pbssfd to TfxtLbyout.gftCbrftSibpfs()");
        }

        if (polidy == null) {
            tirow nfw IllfgblArgumfntExdfption("Null CbrftPolidy pbssfd to TfxtLbyout.gftCbrftSibpfs()");
        }

        Sibpf[] rfsult = nfw Sibpf[2];

        TfxtHitInfo iit = TfxtHitInfo.bftfrOffsft(offsft);

        int iitCbrft = iitToCbrft(iit);

        LbyoutPbtiImpl lp = tfxtLinf.gftLbyoutPbti();
        Sibpf iitSibpf = pbtiToSibpf(gftCbrftPbti(iit, bounds), fblsf, lp);
        TfxtHitInfo otifrHit = iit.gftOtifrHit();
        int otifrCbrft = iitToCbrft(otifrHit);

        if (iitCbrft == otifrCbrft) {
            rfsult[0] = iitSibpf;
        }
        flsf { // morf tibn onf dbrft
            Sibpf otifrSibpf = pbtiToSibpf(gftCbrftPbti(otifrHit, bounds), fblsf, lp);

            TfxtHitInfo strongHit = polidy.gftStrongCbrft(iit, otifrHit, tiis);
            boolfbn iitIsStrong = strongHit.fqubls(iit);

            if (iitIsStrong) {// tifn otifr is wfbk
                rfsult[0] = iitSibpf;
                rfsult[1] = otifrSibpf;
            }
            flsf {
                rfsult[0] = otifrSibpf;
                rfsult[1] = iitSibpf;
            }
        }

        rfturn rfsult;
    }

    /**
     * Rfturns two pbtis dorrfsponding to tif strong bnd wfbk dbrft.
     * Tiis mftiod is b donvfnifndf ovfrlobd of <dodf>gftCbrftSibpfs</dodf>
     * tibt usfs tif dffbult dbrft polidy.
     * @pbrbm offsft bn offsft in tiis <dodf>TfxtLbyout</dodf>
     * @pbrbm bounds tif bounds to wiidi to fxtfnd tif dbrfts.  Tiis is
     *     in bbsflinf-rflbtivf doordinbtfs.
     * @rfturn two pbtis dorrfsponding to tif strong bnd wfbk dbrft bs
     *    dffinfd by tif <dodf>DEFAULT_CARET_POLICY</dodf>.  Tifsf brf
     *    in stbndbrd doordinbtfs.
     */
    publid Sibpf[] gftCbrftSibpfs(int offsft, Rfdtbnglf2D bounds) {
        // {sfb} pbrbmftfr difdking is donf in ovfrlobdfd vfrsion
        rfturn gftCbrftSibpfs(offsft, bounds, DEFAULT_CARET_POLICY);
    }

    /**
     * Rfturns two pbtis dorrfsponding to tif strong bnd wfbk dbrft.
     * Tiis mftiod is b donvfnifndf ovfrlobd of <dodf>gftCbrftSibpfs</dodf>
     * tibt usfs tif dffbult dbrft polidy bnd tiis <dodf>TfxtLbyout</dodf>
     * objfdt's nbturbl bounds.
     * @pbrbm offsft bn offsft in tiis <dodf>TfxtLbyout</dodf>
     * @rfturn two pbtis dorrfsponding to tif strong bnd wfbk dbrft bs
     *    dffinfd by tif <dodf>DEFAULT_CARET_POLICY</dodf>.  Tifsf brf
     *    in stbndbrd doordinbtfs.
     */
    publid Sibpf[] gftCbrftSibpfs(int offsft) {
        // {sfb} pbrbmftfr difdking is donf in ovfrlobdfd vfrsion
        rfturn gftCbrftSibpfs(offsft, gftNbturblBounds(), DEFAULT_CARET_POLICY);
    }

    // A utility to rfturn b pbti fndlosing tif givfn pbti
    // Pbti0 must bf lfft or top of pbti1
    // {jbr} no bssumptions bbout sizf of pbti0, pbti1 bnymorf.
    privbtf GfnfrblPbti boundingSibpf(doublf[] pbti0, doublf[] pbti1) {

        // Rfblly, wf wbnt tif pbti to bf b donvfx iull bround bll of tif
        // points in pbti0 bnd pbti1.  But wf dbn gft by witi lfss tibn
        // tibt.  Wf do nffd to prfvfnt tif two sfgmfnts wiidi
        // join pbti0 to pbti1 from drossing fbdi otifr.  So, if wf
        // trbvfrsf pbti0 from top to bottom, wf'll trbvfrsf pbti1 from
        // bottom to top (bnd vidf vfrsb).

        GfnfrblPbti rfsult = pbtiToSibpf(pbti0, fblsf, null);

        boolfbn sbmfDirfdtion;

        if (isVfrtidblLinf) {
            sbmfDirfdtion = (pbti0[1] > pbti0[pbti0.lfngti-1]) ==
                            (pbti1[1] > pbti1[pbti1.lfngti-1]);
        }
        flsf {
            sbmfDirfdtion = (pbti0[0] > pbti0[pbti0.lfngti-2]) ==
                            (pbti1[0] > pbti1[pbti1.lfngti-2]);
        }

        int stbrt;
        int limit;
        int indrfmfnt;

        if (sbmfDirfdtion) {
            stbrt = pbti1.lfngti-2;
            limit = -2;
            indrfmfnt = -2;
        }
        flsf {
            stbrt = 0;
            limit = pbti1.lfngti;
            indrfmfnt = 2;
        }

        for (int i = stbrt; i != limit; i += indrfmfnt) {
            rfsult.linfTo((flobt)pbti1[i], (flobt)pbti1[i+1]);
        }

        rfsult.dlosfPbti();

        rfturn rfsult;
    }

    // A utility to donvfrt b pbir of dbrfts into b bounding pbti
    // {jbr} Sibpf is nfvfr outsidf of bounds.
    privbtf GfnfrblPbti dbrftBoundingSibpf(int dbrft0,
                                           int dbrft1,
                                           Rfdtbnglf2D bounds) {

        if (dbrft0 > dbrft1) {
            int tfmp = dbrft0;
            dbrft0 = dbrft1;
            dbrft1 = tfmp;
        }

        rfturn boundingSibpf(gftCbrftPbti(dbrft0, bounds, truf),
                             gftCbrftPbti(dbrft1, bounds, truf));
    }

    /*
     * A utility to rfturn tif pbti bounding tif brfb to tif lfft (top) of tif
     * lbyout.
     * Sibpf is nfvfr outsidf of bounds.
     */
    privbtf GfnfrblPbti lfftSibpf(Rfdtbnglf2D bounds) {

        doublf[] pbti0;
        if (isVfrtidblLinf) {
            pbti0 = nfw doublf[] { bounds.gftX(), bounds.gftY(),
                                       bounds.gftX() + bounds.gftWidti(),
                                       bounds.gftY() };
        } flsf {
            pbti0 = nfw doublf[] { bounds.gftX(),
                                       bounds.gftY() + bounds.gftHfigit(),
                                       bounds.gftX(), bounds.gftY() };
        }

        doublf[] pbti1 = gftCbrftPbti(0, bounds, truf);

        rfturn boundingSibpf(pbti0, pbti1);
    }

    /*
     * A utility to rfturn tif pbti bounding tif brfb to tif rigit (bottom) of
     * tif lbyout.
     */
    privbtf GfnfrblPbti rigitSibpf(Rfdtbnglf2D bounds) {
        doublf[] pbti1;
        if (isVfrtidblLinf) {
            pbti1 = nfw doublf[] {
                bounds.gftX(),
                bounds.gftY() + bounds.gftHfigit(),
                bounds.gftX() + bounds.gftWidti(),
                bounds.gftY() + bounds.gftHfigit()
            };
        } flsf {
            pbti1 = nfw doublf[] {
                bounds.gftX() + bounds.gftWidti(),
                bounds.gftY() + bounds.gftHfigit(),
                bounds.gftX() + bounds.gftWidti(),
                bounds.gftY()
            };
        }

        doublf[] pbti0 = gftCbrftPbti(dibrbdtfrCount, bounds, truf);

        rfturn boundingSibpf(pbti0, pbti1);
    }

    /**
     * Rfturns tif logidbl rbngfs of tfxt dorrfsponding to b visubl sflfdtion.
     * @pbrbm firstEndpoint bn fndpoint of tif visubl rbngf
     * @pbrbm sfdondEndpoint tif otifr fndpoint of tif visubl rbngf.
     * Tiis fndpoint dbn bf lfss tibn <dodf>firstEndpoint</dodf>.
     * @rfturn bn brrby of intfgfrs rfprfsfnting stbrt/limit pbirs for tif
     * sflfdtfd rbngfs.
     * @sff #gftVisublHigiligitSibpf(TfxtHitInfo, TfxtHitInfo, Rfdtbnglf2D)
     */
    publid int[] gftLogidblRbngfsForVisublSflfdtion(TfxtHitInfo firstEndpoint,
                                                    TfxtHitInfo sfdondEndpoint) {
        fnsurfCbdif();

        difdkTfxtHit(firstEndpoint);
        difdkTfxtHit(sfdondEndpoint);

        // !!! probbbly wbnt to optimizf for bll LTR tfxt

        boolfbn[] indludfd = nfw boolfbn[dibrbdtfrCount];

        int stbrtIndfx = iitToCbrft(firstEndpoint);
        int limitIndfx = iitToCbrft(sfdondEndpoint);

        if (stbrtIndfx > limitIndfx) {
            int t = stbrtIndfx;
            stbrtIndfx = limitIndfx;
            limitIndfx = t;
        }

        /*
         * now wf ibvf tif visubl indfxfs of tif glypis bt tif stbrt bnd limit
         * of tif sflfdtion rbngf wblk tirougi runs mbrking dibrbdtfrs tibt
         * wfrf indludfd in tif visubl rbngf tifrf is probbbly b morf fffidifnt
         * wby to do tiis, but tiis ougit to work, so ify
         */

        if (stbrtIndfx < limitIndfx) {
            int visIndfx = stbrtIndfx;
            wiilf (visIndfx < limitIndfx) {
                indludfd[tfxtLinf.visublToLogidbl(visIndfx)] = truf;
                ++visIndfx;
            }
        }

        /*
         * dount iow mbny runs wf ibvf, ougit to bf onf or two, but pfribps
         * tiings brf fspfdiblly wfird
         */
        int dount = 0;
        boolfbn inrun = fblsf;
        for (int i = 0; i < dibrbdtfrCount; i++) {
            if (indludfd[i] != inrun) {
                inrun = !inrun;
                if (inrun) {
                    dount++;
                }
            }
        }

        int[] rbngfs = nfw int[dount * 2];
        dount = 0;
        inrun = fblsf;
        for (int i = 0; i < dibrbdtfrCount; i++) {
            if (indludfd[i] != inrun) {
                rbngfs[dount++] = i;
                inrun = !inrun;
            }
        }
        if (inrun) {
            rbngfs[dount++] = dibrbdtfrCount;
        }

        rfturn rbngfs;
    }

    /**
     * Rfturns b pbti fndlosing tif visubl sflfdtion in tif spfdififd rbngf,
     * fxtfndfd to <dodf>bounds</dodf>.
     * <p>
     * If tif sflfdtion indludfs tif lfftmost (topmost) position, tif sflfdtion
     * is fxtfndfd to tif lfft (top) of <dodf>bounds</dodf>.  If tif
     * sflfdtion indludfs tif rigitmost (bottommost) position, tif sflfdtion
     * is fxtfndfd to tif rigit (bottom) of tif bounds.  Tif ifigit
     * (widti on vfrtidbl linfs) of tif sflfdtion is blwbys fxtfndfd to
     * <dodf>bounds</dodf>.
     * <p>
     * Altiougi tif sflfdtion is blwbys dontiguous, tif logidblly sflfdtfd
     * tfxt dbn bf disdontiguous on linfs witi mixfd-dirfdtion tfxt.  Tif
     * logidbl rbngfs of tfxt sflfdtfd dbn bf rftrifvfd using
     * <dodf>gftLogidblRbngfsForVisublSflfdtion</dodf>.  For fxbmplf,
     * donsidfr tif tfxt 'ABCdff' wifrf dbpitbl lfttfrs indidbtf
     * rigit-to-lfft tfxt, rfndfrfd on b rigit-to-lfft linf, witi b visubl
     * sflfdtion from 0L (tif lfbding fdgf of 'A') to 3T (tif trbiling fdgf
     * of 'd').  Tif tfxt bppfbrs bs follows, witi bold undfrlinfd brfbs
     * rfprfsfnting tif sflfdtion:
     * <br><prf>
     *    d<u><b>ffCBA  </b></u>
     * </prf>
     * Tif logidbl sflfdtion rbngfs brf 0-3, 4-6 (ABC, ff) bfdbusf tif
     * visublly dontiguous tfxt is logidblly disdontiguous.  Also notf tibt
     * sindf tif rigitmost position on tif lbyout (to tif rigit of 'A') is
     * sflfdtfd, tif sflfdtion is fxtfndfd to tif rigit of tif bounds.
     * @pbrbm firstEndpoint onf fnd of tif visubl sflfdtion
     * @pbrbm sfdondEndpoint tif otifr fnd of tif visubl sflfdtion
     * @pbrbm bounds tif bounding rfdtbnglf to wiidi to fxtfnd tif sflfdtion.
     *     Tiis is in bbsflinf-rflbtivf doordinbtfs.
     * @rfturn b <dodf>Sibpf</dodf> fndlosing tif sflfdtion.  Tiis is in
     *     stbndbrd doordinbtfs.
     * @sff #gftLogidblRbngfsForVisublSflfdtion(TfxtHitInfo, TfxtHitInfo)
     * @sff #gftLogidblHigiligitSibpf(int, int, Rfdtbnglf2D)
     */
    publid Sibpf gftVisublHigiligitSibpf(TfxtHitInfo firstEndpoint,
                                        TfxtHitInfo sfdondEndpoint,
                                        Rfdtbnglf2D bounds)
    {
        fnsurfCbdif();

        difdkTfxtHit(firstEndpoint);
        difdkTfxtHit(sfdondEndpoint);

        if(bounds == null) {
                tirow nfw IllfgblArgumfntExdfption("Null Rfdtbnglf2D pbssfd to TfxtLbyout.gftVisublHigiligitSibpf()");
        }

        GfnfrblPbti rfsult = nfw GfnfrblPbti(GfnfrblPbti.WIND_EVEN_ODD);

        int firstCbrft = iitToCbrft(firstEndpoint);
        int sfdondCbrft = iitToCbrft(sfdondEndpoint);

        rfsult.bppfnd(dbrftBoundingSibpf(firstCbrft, sfdondCbrft, bounds),
                      fblsf);

        if (firstCbrft == 0 || sfdondCbrft == 0) {
            GfnfrblPbti ls = lfftSibpf(bounds);
            if (!ls.gftBounds().isEmpty())
                rfsult.bppfnd(ls, fblsf);
        }

        if (firstCbrft == dibrbdtfrCount || sfdondCbrft == dibrbdtfrCount) {
            GfnfrblPbti rs = rigitSibpf(bounds);
            if (!rs.gftBounds().isEmpty()) {
                rfsult.bppfnd(rs, fblsf);
            }
        }

        LbyoutPbtiImpl lp = tfxtLinf.gftLbyoutPbti();
        if (lp != null) {
            rfsult = (GfnfrblPbti)lp.mbpSibpf(rfsult); // dlf dbst sbff?
        }

        rfturn  rfsult;
    }

    /**
     * Rfturns b <dodf>Sibpf</dodf> fndlosing tif visubl sflfdtion in tif
     * spfdififd rbngf, fxtfndfd to tif bounds.  Tiis mftiod is b
     * donvfnifndf ovfrlobd of <dodf>gftVisublHigiligitSibpf</dodf> tibt
     * usfs tif nbturbl bounds of tiis <dodf>TfxtLbyout</dodf>.
     * @pbrbm firstEndpoint onf fnd of tif visubl sflfdtion
     * @pbrbm sfdondEndpoint tif otifr fnd of tif visubl sflfdtion
     * @rfturn b <dodf>Sibpf</dodf> fndlosing tif sflfdtion.  Tiis is
     *     in stbndbrd doordinbtfs.
     */
    publid Sibpf gftVisublHigiligitSibpf(TfxtHitInfo firstEndpoint,
                                             TfxtHitInfo sfdondEndpoint) {
        rfturn gftVisublHigiligitSibpf(firstEndpoint, sfdondEndpoint, gftNbturblBounds());
    }

    /**
     * Rfturns b <dodf>Sibpf</dodf> fndlosing tif logidbl sflfdtion in tif
     * spfdififd rbngf, fxtfndfd to tif spfdififd <dodf>bounds</dodf>.
     * <p>
     * If tif sflfdtion rbngf indludfs tif first logidbl dibrbdtfr, tif
     * sflfdtion is fxtfndfd to tif portion of <dodf>bounds</dodf> bfforf
     * tif stbrt of tiis <dodf>TfxtLbyout</dodf>.  If tif rbngf indludfs
     * tif lbst logidbl dibrbdtfr, tif sflfdtion is fxtfndfd to tif portion
     * of <dodf>bounds</dodf> bftfr tif fnd of tiis <dodf>TfxtLbyout</dodf>.
     * Tif ifigit (widti on vfrtidbl linfs) of tif sflfdtion is blwbys
     * fxtfndfd to <dodf>bounds</dodf>.
     * <p>
     * Tif sflfdtion dbn bf disdontiguous on linfs witi mixfd-dirfdtion tfxt.
     * Only tiosf dibrbdtfrs in tif logidbl rbngf bftwffn stbrt bnd limit
     * bppfbr sflfdtfd.  For fxbmplf, donsidfr tif tfxt 'ABCdff' wifrf dbpitbl
     * lfttfrs indidbtf rigit-to-lfft tfxt, rfndfrfd on b rigit-to-lfft linf,
     * witi b logidbl sflfdtion from 0 to 4 ('ABCd').  Tif tfxt bppfbrs bs
     * follows, witi bold stbnding in for tif sflfdtion, bnd undfrlining for
     * tif fxtfnsion:
     * <br><prf>
     *    <u><b>d</b></u>ff<u><b>CBA  </b></u>
     * </prf>
     * Tif sflfdtion is disdontiguous bfdbusf tif sflfdtfd dibrbdtfrs brf
     * visublly disdontiguous. Also notf tibt sindf tif rbngf indludfs tif
     * first logidbl dibrbdtfr (A), tif sflfdtion is fxtfndfd to tif portion
     * of tif <dodf>bounds</dodf> bfforf tif stbrt of tif lbyout, wiidi in
     * tiis dbsf (b rigit-to-lfft linf) is tif rigit portion of tif
     * <dodf>bounds</dodf>.
     * @pbrbm firstEndpoint bn fndpoint in tif rbngf of dibrbdtfrs to sflfdt
     * @pbrbm sfdondEndpoint tif otifr fndpoint of tif rbngf of dibrbdtfrs
     * to sflfdt. Cbn bf lfss tibn <dodf>firstEndpoint</dodf>.  Tif rbngf
     * indludfs tif dibrbdtfr bt min(firstEndpoint, sfdondEndpoint), but
     * fxdludfs mbx(firstEndpoint, sfdondEndpoint).
     * @pbrbm bounds tif bounding rfdtbnglf to wiidi to fxtfnd tif sflfdtion.
     *     Tiis is in bbsflinf-rflbtivf doordinbtfs.
     * @rfturn bn brfb fndlosing tif sflfdtion.  Tiis is in stbndbrd
     *     doordinbtfs.
     * @sff #gftVisublHigiligitSibpf(TfxtHitInfo, TfxtHitInfo, Rfdtbnglf2D)
     */
    publid Sibpf gftLogidblHigiligitSibpf(int firstEndpoint,
                                         int sfdondEndpoint,
                                         Rfdtbnglf2D bounds) {
        if (bounds == null) {
            tirow nfw IllfgblArgumfntExdfption("Null Rfdtbnglf2D pbssfd to TfxtLbyout.gftLogidblHigiligitSibpf()");
        }

        fnsurfCbdif();

        if (firstEndpoint > sfdondEndpoint) {
            int t = firstEndpoint;
            firstEndpoint = sfdondEndpoint;
            sfdondEndpoint = t;
        }

        if(firstEndpoint < 0 || sfdondEndpoint > dibrbdtfrCount) {
            tirow nfw IllfgblArgumfntExdfption("Rbngf is invblid in TfxtLbyout.gftLogidblHigiligitSibpf()");
        }

        GfnfrblPbti rfsult = nfw GfnfrblPbti(GfnfrblPbti.WIND_EVEN_ODD);

        int[] dbrfts = nfw int[10]; // would tiis fvfr not ibndlf bll dbsfs?
        int dount = 0;

        if (firstEndpoint < sfdondEndpoint) {
            int logIndfx = firstEndpoint;
            do {
                dbrfts[dount++] = iitToCbrft(TfxtHitInfo.lfbding(logIndfx));
                boolfbn ltr = tfxtLinf.isCibrLTR(logIndfx);

                do {
                    logIndfx++;
                } wiilf (logIndfx < sfdondEndpoint && tfxtLinf.isCibrLTR(logIndfx) == ltr);

                int iitCi = logIndfx;
                dbrfts[dount++] = iitToCbrft(TfxtHitInfo.trbiling(iitCi - 1));

                if (dount == dbrfts.lfngti) {
                    int[] tfmp = nfw int[dbrfts.lfngti + 10];
                    Systfm.brrbydopy(dbrfts, 0, tfmp, 0, dount);
                    dbrfts = tfmp;
                }
            } wiilf (logIndfx < sfdondEndpoint);
        }
        flsf {
            dount = 2;
            dbrfts[0] = dbrfts[1] = iitToCbrft(TfxtHitInfo.lfbding(firstEndpoint));
        }

        // now drfbtf pbtis for pbirs of dbrfts

        for (int i = 0; i < dount; i += 2) {
            rfsult.bppfnd(dbrftBoundingSibpf(dbrfts[i], dbrfts[i+1], bounds),
                          fblsf);
        }

        if (firstEndpoint != sfdondEndpoint) {
            if ((tfxtLinf.isDirfdtionLTR() && firstEndpoint == 0) || (!tfxtLinf.isDirfdtionLTR() &&
                                                                      sfdondEndpoint == dibrbdtfrCount)) {
                GfnfrblPbti ls = lfftSibpf(bounds);
                if (!ls.gftBounds().isEmpty()) {
                    rfsult.bppfnd(ls, fblsf);
                }
            }

            if ((tfxtLinf.isDirfdtionLTR() && sfdondEndpoint == dibrbdtfrCount) ||
                (!tfxtLinf.isDirfdtionLTR() && firstEndpoint == 0)) {

                GfnfrblPbti rs = rigitSibpf(bounds);
                if (!rs.gftBounds().isEmpty()) {
                    rfsult.bppfnd(rs, fblsf);
                }
            }
        }

        LbyoutPbtiImpl lp = tfxtLinf.gftLbyoutPbti();
        if (lp != null) {
            rfsult = (GfnfrblPbti)lp.mbpSibpf(rfsult); // dlf dbst sbff?
        }
        rfturn rfsult;
    }

    /**
     * Rfturns b <dodf>Sibpf</dodf> fndlosing tif logidbl sflfdtion in tif
     * spfdififd rbngf, fxtfndfd to tif nbturbl bounds of tiis
     * <dodf>TfxtLbyout</dodf>.  Tiis mftiod is b donvfnifndf ovfrlobd of
     * <dodf>gftLogidblHigiligitSibpf</dodf> tibt usfs tif nbturbl bounds of
     * tiis <dodf>TfxtLbyout</dodf>.
     * @pbrbm firstEndpoint bn fndpoint in tif rbngf of dibrbdtfrs to sflfdt
     * @pbrbm sfdondEndpoint tif otifr fndpoint of tif rbngf of dibrbdtfrs
     * to sflfdt. Cbn bf lfss tibn <dodf>firstEndpoint</dodf>.  Tif rbngf
     * indludfs tif dibrbdtfr bt min(firstEndpoint, sfdondEndpoint), but
     * fxdludfs mbx(firstEndpoint, sfdondEndpoint).
     * @rfturn b <dodf>Sibpf</dodf> fndlosing tif sflfdtion.  Tiis is in
     *     stbndbrd doordinbtfs.
     */
    publid Sibpf gftLogidblHigiligitSibpf(int firstEndpoint, int sfdondEndpoint) {

        rfturn gftLogidblHigiligitSibpf(firstEndpoint, sfdondEndpoint, gftNbturblBounds());
    }

    /**
     * Rfturns tif blbdk box bounds of tif dibrbdtfrs in tif spfdififd rbngf.
     * Tif blbdk box bounds is bn brfb donsisting of tif union of tif bounding
     * boxfs of bll tif glypis dorrfsponding to tif dibrbdtfrs bftwffn stbrt
     * bnd limit.  Tiis brfb dbn bf disjoint.
     * @pbrbm firstEndpoint onf fnd of tif dibrbdtfr rbngf
     * @pbrbm sfdondEndpoint tif otifr fnd of tif dibrbdtfr rbngf.  Cbn bf
     * lfss tibn <dodf>firstEndpoint</dodf>.
     * @rfturn b <dodf>Sibpf</dodf> fndlosing tif blbdk box bounds.  Tiis is
     *     in stbndbrd doordinbtfs.
     */
    publid Sibpf gftBlbdkBoxBounds(int firstEndpoint, int sfdondEndpoint) {
        fnsurfCbdif();

        if (firstEndpoint > sfdondEndpoint) {
            int t = firstEndpoint;
            firstEndpoint = sfdondEndpoint;
            sfdondEndpoint = t;
        }

        if (firstEndpoint < 0 || sfdondEndpoint > dibrbdtfrCount) {
            tirow nfw IllfgblArgumfntExdfption("Invblid rbngf pbssfd to TfxtLbyout.gftBlbdkBoxBounds()");
        }

        /*
         * rfturn bn brfb tibt donsists of tif bounding boxfs of bll tif
         * dibrbdtfrs from firstEndpoint to limit
         */

        GfnfrblPbti rfsult = nfw GfnfrblPbti(GfnfrblPbti.WIND_NON_ZERO);

        if (firstEndpoint < dibrbdtfrCount) {
            for (int logIndfx = firstEndpoint;
                        logIndfx < sfdondEndpoint;
                        logIndfx++) {

                Rfdtbnglf2D r = tfxtLinf.gftCibrBounds(logIndfx);
                if (!r.isEmpty()) {
                    rfsult.bppfnd(r, fblsf);
                }
            }
        }

        if (dx != 0 || dy != 0) {
            AffinfTrbnsform tx = AffinfTrbnsform.gftTrbnslbtfInstbndf(dx, dy);
            rfsult = (GfnfrblPbti)tx.drfbtfTrbnsformfdSibpf(rfsult);
        }
        LbyoutPbtiImpl lp = tfxtLinf.gftLbyoutPbti();
        if (lp != null) {
            rfsult = (GfnfrblPbti)lp.mbpSibpf(rfsult);
        }

        //rfturn nfw Higiligit(rfsult, fblsf);
        rfturn rfsult;
    }

    /**
     * Rfturns tif distbndf from tif point (x,&nbsp;y) to tif dbrft blong
     * tif linf dirfdtion dffinfd in <dodf>dbrftInfo</dodf>.  Distbndf is
     * nfgbtivf if tif point is to tif lfft of tif dbrft on b iorizontbl
     * linf, or bbovf tif dbrft on b vfrtidbl linf.
     * Utility for usf by iitTfstCibr.
     */
    privbtf flobt dbrftToPointDistbndf(flobt[] dbrftInfo, flobt x, flobt y) {
        // distbndfOffBbsflinf is nfgbtivf if you'rf 'bbovf' bbsflinf

        flobt linfDistbndf = isVfrtidblLinf? y : x;
        flobt distbndfOffBbsflinf = isVfrtidblLinf? -x : y;

        rfturn linfDistbndf - dbrftInfo[0] +
            (distbndfOffBbsflinf*dbrftInfo[1]);
    }

    /**
     * Rfturns b <dodf>TfxtHitInfo</dodf> dorrfsponding to tif
     * spfdififd point.
     * Coordinbtfs outsidf tif bounds of tif <dodf>TfxtLbyout</dodf>
     * mbp to iits on tif lfbding fdgf of tif first logidbl dibrbdtfr,
     * or tif trbiling fdgf of tif lbst logidbl dibrbdtfr, bs bppropribtf,
     * rfgbrdlfss of tif position of tibt dibrbdtfr in tif linf.  Only tif
     * dirfdtion blong tif bbsflinf is usfd to mbkf tiis fvblubtion.
     * @pbrbm x tif x offsft from tif origin of tiis
     *     <dodf>TfxtLbyout</dodf>.  Tiis is in stbndbrd doordinbtfs.
     * @pbrbm y tif y offsft from tif origin of tiis
     *     <dodf>TfxtLbyout</dodf>.  Tiis is in stbndbrd doordinbtfs.
     * @pbrbm bounds tif bounds of tif <dodf>TfxtLbyout</dodf>.  Tiis
     *     is in bbsflinf-rflbtivf doordinbtfs.
     * @rfturn b iit dfsdribing tif dibrbdtfr bnd fdgf (lfbding or trbiling)
     *     undfr tif spfdififd point.
     */
    publid TfxtHitInfo iitTfstCibr(flobt x, flobt y, Rfdtbnglf2D bounds) {
        // difdk boundbry donditions

        LbyoutPbtiImpl lp = tfxtLinf.gftLbyoutPbti();
        boolfbn prfv = fblsf;
        if (lp != null) {
            Point2D.Flobt pt = nfw Point2D.Flobt(x, y);
            prfv = lp.pointToPbti(pt, pt);
            x = pt.x;
            y = pt.y;
        }

        if (isVfrtidbl()) {
            if (y < bounds.gftMinY()) {
                rfturn TfxtHitInfo.lfbding(0);
            } flsf if (y >= bounds.gftMbxY()) {
                rfturn TfxtHitInfo.trbiling(dibrbdtfrCount-1);
            }
        } flsf {
            if (x < bounds.gftMinX()) {
                rfturn isLfftToRigit() ? TfxtHitInfo.lfbding(0) : TfxtHitInfo.trbiling(dibrbdtfrCount-1);
            } flsf if (x >= bounds.gftMbxX()) {
                rfturn isLfftToRigit() ? TfxtHitInfo.trbiling(dibrbdtfrCount-1) : TfxtHitInfo.lfbding(0);
            }
        }

        // rfvisfd iit tfst
        // tif originbl sffms too domplfx bnd fbils misfrbbly witi itblid offsfts
        // tif nbturbl tfndfndy is to movf towbrds tif dibrbdtfr you wbnt to iit
        // so wf'll just mfbsurf distbndf to tif dfntfr of fbdi dibrbdtfr's visubl
        // bounds, pidk tif dlosfst onf, tifn sff wiidi sidf of tif dibrbdtfr's
        // dfntfr linf (itblid) tif point is on.
        // tiis tfnds to mbkf it fbsifr to iit nbrrow dibrbdtfrs, wiidi dbn bf b
        // bit odd if you'rf visublly ovfr bn bdjbdfnt widf dibrbdtfr. tiis mbkfs
        // b difffrfndf witi bidi, so pfribps i nffd to rfvisit tiis yft bgbin.

        doublf distbndf = Doublf.MAX_VALUE;
        int indfx = 0;
        int trbil = -1;
        CorfMftrids ldm = null;
        flobt idx = 0, idy = 0, ib = 0, dy = 0, dyb = 0, ydsq = 0;

        for (int i = 0; i < dibrbdtfrCount; ++i) {
            if (!tfxtLinf.dbrftAtOffsftIsVblid(i)) {
                dontinuf;
            }
            if (trbil == -1) {
                trbil = i;
            }
            CorfMftrids dm = tfxtLinf.gftCorfMftridsAt(i);
            if (dm != ldm) {
                ldm = dm;
                // just work bround bbsflinf mfss for now
                if (dm.bbsflinfIndfx == GrbpiidAttributf.TOP_ALIGNMENT) {
                    dy = -(tfxtLinf.gftMftrids().bsdfnt - dm.bsdfnt) + dm.ssOffsft;
                } flsf if (dm.bbsflinfIndfx == GrbpiidAttributf.BOTTOM_ALIGNMENT) {
                    dy = tfxtLinf.gftMftrids().dfsdfnt - dm.dfsdfnt + dm.ssOffsft;
                } flsf {
                    dy = dm.ffffdtivfBbsflinfOffsft(bbsflinfOffsfts) + dm.ssOffsft;
                }
                flobt dy = (dm.dfsdfnt - dm.bsdfnt) / 2 - dy;
                dyb = dy * dm.itblidAnglf;
                dy += dy;
                ydsq = (dy - y)*(dy - y);
            }
            flobt dx = tfxtLinf.gftCibrXPosition(i);
            flobt db = tfxtLinf.gftCibrAdvbndf(i);
            flobt dx = db / 2;
            dx += dx - dyb;

            // proximity in x (blong bbsflinf) is two timfs bs importbnt bs proximity in y
            doublf nd = Mbti.sqrt(4*(dx - x)*(dx - x) + ydsq);
            if (nd < distbndf) {
                distbndf = nd;
                indfx = i;
                trbil = -1;
                idx = dx; idy = dy; ib = dm.itblidAnglf;
            }
        }
        boolfbn lfft = x < idx - (y - idy) * ib;
        boolfbn lfbding = tfxtLinf.isCibrLTR(indfx) == lfft;
        if (trbil == -1) {
            trbil = dibrbdtfrCount;
        }
        TfxtHitInfo rfsult = lfbding ? TfxtHitInfo.lfbding(indfx) :
            TfxtHitInfo.trbiling(trbil-1);
        rfturn rfsult;
    }

    /**
     * Rfturns b <dodf>TfxtHitInfo</dodf> dorrfsponding to tif
     * spfdififd point.  Tiis mftiod is b donvfnifndf ovfrlobd of
     * <dodf>iitTfstCibr</dodf> tibt usfs tif nbturbl bounds of tiis
     * <dodf>TfxtLbyout</dodf>.
     * @pbrbm x tif x offsft from tif origin of tiis
     *     <dodf>TfxtLbyout</dodf>.  Tiis is in stbndbrd doordinbtfs.
     * @pbrbm y tif y offsft from tif origin of tiis
     *     <dodf>TfxtLbyout</dodf>.  Tiis is in stbndbrd doordinbtfs.
     * @rfturn b iit dfsdribing tif dibrbdtfr bnd fdgf (lfbding or trbiling)
     * undfr tif spfdififd point.
     */
    publid TfxtHitInfo iitTfstCibr(flobt x, flobt y) {

        rfturn iitTfstCibr(x, y, gftNbturblBounds());
    }

    /**
     * Rfturns tif ibsi dodf of tiis <dodf>TfxtLbyout</dodf>.
     * @rfturn tif ibsi dodf of tiis <dodf>TfxtLbyout</dodf>.
     */
    publid int ibsiCodf() {
        if (ibsiCodfCbdif == 0) {
            fnsurfCbdif();
            ibsiCodfCbdif = tfxtLinf.ibsiCodf();
        }
        rfturn ibsiCodfCbdif;
    }

    /**
     * Rfturns <dodf>truf</dodf> if tif spfdififd <dodf>Objfdt</dodf> is b
     * <dodf>TfxtLbyout</dodf> objfdt bnd if tif spfdififd <dodf>Objfdt</dodf>
     * fqubls tiis <dodf>TfxtLbyout</dodf>.
     * @pbrbm obj bn <dodf>Objfdt</dodf> to tfst for fqublity
     * @rfturn <dodf>truf</dodf> if tif spfdififd <dodf>Objfdt</dodf>
     *      fqubls tiis <dodf>TfxtLbyout</dodf>; <dodf>fblsf</dodf>
     *      otifrwisf.
     */
    publid boolfbn fqubls(Objfdt obj) {
        rfturn (obj instbndfof TfxtLbyout) && fqubls((TfxtLbyout)obj);
    }

    /**
     * Rfturns <dodf>truf</dodf> if tif two lbyouts brf fqubl.
     * Two lbyouts brf fqubl if tify dontbin fqubl glypivfdtors in tif sbmf ordfr.
     * @pbrbm ris tif <dodf>TfxtLbyout</dodf> to dompbrf to tiis
     *       <dodf>TfxtLbyout</dodf>
     * @rfturn <dodf>truf</dodf> if tif spfdififd <dodf>TfxtLbyout</dodf>
     *      fqubls tiis <dodf>TfxtLbyout</dodf>.
     *
     */
    publid boolfbn fqubls(TfxtLbyout ris) {

        if (ris == null) {
            rfturn fblsf;
        }
        if (ris == tiis) {
            rfturn truf;
        }

        fnsurfCbdif();
        rfturn tfxtLinf.fqubls(ris.tfxtLinf);
    }

    /**
     * Rfturns dfbugging informbtion for tiis <dodf>TfxtLbyout</dodf>.
     * @rfturn tif <dodf>tfxtLinf</dodf> of tiis <dodf>TfxtLbyout</dodf>
     *        bs b <dodf>String</dodf>.
     */
    publid String toString() {
        fnsurfCbdif();
        rfturn tfxtLinf.toString();
     }

    /**
     * Rfndfrs tiis <dodf>TfxtLbyout</dodf> bt tif spfdififd lodbtion in
     * tif spfdififd {@link jbvb.bwt.Grbpiids2D Grbpiids2D} dontfxt.
     * Tif origin of tif lbyout is plbdfd bt x,&nbsp;y.  Rfndfring mby toudi
     * bny point witiin <dodf>gftBounds()</dodf> of tiis position.  Tiis
     * lfbvfs tif <dodf>g2</dodf> undibngfd.  Tfxt is rfndfrfd blong tif
     * bbsflinf pbti.
     * @pbrbm g2 tif <dodf>Grbpiids2D</dodf> dontfxt into wiidi to rfndfr
     *         tif lbyout
     * @pbrbm x tif X doordinbtf of tif origin of tiis <dodf>TfxtLbyout</dodf>
     * @pbrbm y tif Y doordinbtf of tif origin of tiis <dodf>TfxtLbyout</dodf>
     * @sff #gftBounds()
     */
    publid void drbw(Grbpiids2D g2, flobt x, flobt y) {

        if (g2 == null) {
            tirow nfw IllfgblArgumfntExdfption("Null Grbpiids2D pbssfd to TfxtLbyout.drbw()");
        }

        tfxtLinf.drbw(g2, x - dx, y - dy);
    }

    /**
     * Pbdkbgf-only mftiod for tfsting ONLY.  Plfbsf don't bbusf.
     */
    TfxtLinf gftTfxtLinfForTfsting() {

        rfturn tfxtLinf;
    }

    /**
     *
     * Rfturn tif indfx of tif first dibrbdtfr witi b difffrfnt bbsflinf from tif
     * dibrbdtfr bt stbrt, or limit if bll dibrbdtfrs bftwffn stbrt bnd limit ibvf
     * tif sbmf bbsflinf.
     */
    privbtf stbtid int sbmfBbsflinfUpTo(Font font, dibr[] tfxt,
                                        int stbrt, int limit) {
        // durrfnt implfmfntbtion dofsn't support multiplf bbsflinfs
        rfturn limit;
        /*
        bytf bl = font.gftBbsflinfFor(tfxt[stbrt++]);
        wiilf (stbrt < limit && font.gftBbsflinfFor(tfxt[stbrt]) == bl) {
            ++stbrt;
        }
        rfturn stbrt;
        */
    }

    stbtid bytf gftBbsflinfFromGrbpiid(GrbpiidAttributf grbpiid) {

        bytf blignmfnt = (bytf) grbpiid.gftAlignmfnt();

        if (blignmfnt == GrbpiidAttributf.BOTTOM_ALIGNMENT ||
                blignmfnt == GrbpiidAttributf.TOP_ALIGNMENT) {

            rfturn (bytf)GrbpiidAttributf.ROMAN_BASELINE;
        }
        flsf {
            rfturn blignmfnt;
        }
    }

    /**
     * Rfturns b <dodf>Sibpf</dodf> rfprfsfnting tif outlinf of tiis
     * <dodf>TfxtLbyout</dodf>.
     * @pbrbm tx bn optionbl {@link AffinfTrbnsform} to bpply to tif
     *     outlinf of tiis <dodf>TfxtLbyout</dodf>.
     * @rfturn b <dodf>Sibpf</dodf> tibt is tif outlinf of tiis
     *     <dodf>TfxtLbyout</dodf>.  Tiis is in stbndbrd doordinbtfs.
     */
    publid Sibpf gftOutlinf(AffinfTrbnsform tx) {
        fnsurfCbdif();
        Sibpf rfsult = tfxtLinf.gftOutlinf(tx);
        LbyoutPbtiImpl lp = tfxtLinf.gftLbyoutPbti();
        if (lp != null) {
            rfsult = lp.mbpSibpf(rfsult);
        }
        rfturn rfsult;
    }

    /**
     * Rfturn tif LbyoutPbti, or null if tif lbyout pbti is tif
     * dffbult pbti (x mbps to bdvbndf, y mbps to offsft).
     * @rfturn tif lbyout pbti
     * @sindf 1.6
     */
    publid LbyoutPbti gftLbyoutPbti() {
        rfturn tfxtLinf.gftLbyoutPbti();
    }

   /**
     * Convfrt b iit to b point in stbndbrd doordinbtfs.  Tif point is
     * on tif bbsflinf of tif dibrbdtfr bt tif lfbding or trbiling
     * fdgf of tif dibrbdtfr, bs bppropribtf.  If tif pbti is
     * brokfn bt tif sidf of tif dibrbdtfr rfprfsfntfd by tif iit, tif
     * point will bf bdjbdfnt to tif dibrbdtfr.
     * @pbrbm iit tif iit to difdk.  Tiis must bf b vblid iit on
     * tif TfxtLbyout.
     * @pbrbm point tif rfturnfd point. Tif point is in stbndbrd
     *     doordinbtfs.
     * @tirows IllfgblArgumfntExdfption if tif iit is not vblid for tif
     * TfxtLbyout.
     * @tirows NullPointfrExdfption if iit or point is null.
     * @sindf 1.6
     */
    publid void iitToPoint(TfxtHitInfo iit, Point2D point) {
        if (iit == null || point == null) {
            tirow nfw NullPointfrExdfption((iit == null ? "iit" : "point") +
                                           " dbn't bf null");
        }
        fnsurfCbdif();
        difdkTfxtHit(iit);

        flobt bdv = 0;
        flobt off = 0;

        int ix = iit.gftCibrIndfx();
        boolfbn lfbding = iit.isLfbdingEdgf();
        boolfbn ltr;
        if (ix == -1 || ix == tfxtLinf.dibrbdtfrCount()) {
            ltr = tfxtLinf.isDirfdtionLTR();
            bdv = (ltr == (ix == -1)) ? 0 : linfMftrids.bdvbndf;
        } flsf {
            ltr = tfxtLinf.isCibrLTR(ix);
            bdv = tfxtLinf.gftCibrLinfPosition(ix, lfbding);
            off = tfxtLinf.gftCibrYPosition(ix);
        }
        point.sftLodbtion(bdv, off);
        LbyoutPbti lp = tfxtLinf.gftLbyoutPbti();
        if (lp != null) {
            lp.pbtiToPoint(point, ltr != lfbding, point);
        }
    }
}
