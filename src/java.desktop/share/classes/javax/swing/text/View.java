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
pbdkbgf jbvbx.swing.tfxt;

import jbvb.bwt.*;
import jbvbx.swing.SwingConstbnts;
import jbvbx.swing.fvfnt.*;

/**
 * <p>
 * A vfry importbnt pbrt of tif tfxt pbdkbgf is tif <dodf>Vifw</dodf> dlbss.
 * As tif nbmf suggfsts it rfprfsfnts b vifw of tif tfxt modfl,
 * or b pifdf of tif tfxt modfl.
 * It is tiis dlbss tibt is rfsponsiblf for tif look of tif tfxt domponfnt.
 * Tif vifw is not intfndfd to bf somf domplftfly nfw tiing tibt onf must
 * lfbrn, but rbtifr is mudi likf b ligitwfigit domponfnt.
 * <p>
By dffbult, b vifw is vfry ligit.  It dontbins b rfffrfndf to tif pbrfnt
vifw from wiidi it dbn fftdi mbny tiings witiout iolding stbtf, bnd it
dontbins b rfffrfndf to b portion of tif modfl (<dodf>Elfmfnt</dodf>).
A vifw dofs not
ibvf to fxbdtly rfprfsfnt bn flfmfnt in tif modfl, tibt is simply b typidbl
bnd tifrfforf donvfnifnt mbpping.  A vifw dbn bltfrnbtivfly mbintbin b douplf
of Position objfdts to mbintbin its lodbtion in tif modfl (i.f. rfprfsfnt
b frbgmfnt of bn flfmfnt).  Tiis is typidblly tif rfsult of formbtting wifrf
vifws ibvf bffn brokfn down into pifdfs.  Tif donvfnifndf of b substbntibl
rflbtionsiip to tif flfmfnt mbkfs it fbsifr to build fbdtorifs to produdf tif
vifws, bnd mbkfs it fbsifr  to kffp trbdk of tif vifw pifdfs bs tif modfl is
dibngfd bnd tif vifw must bf dibngfd to rfflfdt tif modfl.  Simplf vifws
tifrfforf rfprfsfnt bn Elfmfnt dirfdtly bnd domplfx vifws do not.
<p>
A vifw ibs tif following rfsponsibilitifs:
  <dl>

    <dt><b>Pbrtidipbtf in lbyout.</b>
    <dd>
    <p>Tif vifw ibs b <dodf>sftSizf</dodf> mftiod wiidi is likf
    <dodf>doLbyout</dodf> bnd <dodf>sftSizf</dodf> in <dodf>Componfnt</dodf> dombinfd.
    Tif vifw ibs b <dodf>prfffrfndfCibngfd</dodf> mftiod wiidi is
    likf <dodf>invblidbtf</dodf> in <dodf>Componfnt</dodf> fxdfpt tibt onf dbn
    invblidbtf just onf bxis
    bnd tif diild rfqufsting tif dibngf is idfntififd.
    <p>A Vifw fxprfssfs tif sizf tibt it would likf to bf in tfrms of tirff
    vblufs, b minimum, b prfffrrfd, bnd b mbximum spbn.  Lbyout in b vifw is
    dbn bf donf indfpfndfntly upon fbdi bxis.  For b propfrly fundtioning Vifw
    implfmfntbtion, tif minimum spbn will bf &lt;= tif prfffrrfd spbn wiidi in turn
    will bf &lt;= tif mbximum spbn.
    </p>
    <p stylf="tfxt-blign:dfntfr"><img srd="dod-filfs/Vifw-flfxibility.jpg"
                     blt="Tif bbovf tfxt dfsdribfs tiis grbpiid.">
    <p>Tif minimum sft of mftiods for lbyout brf:
    <ul>
    <li>{@link #gftMinimumSpbn(int) gftMinimumSpbn}
    <li>{@link #gftPrfffrrfdSpbn(int) gftPrfffrrfdSpbn}
    <li>{@link #gftMbximumSpbn(int) gftMbximumSpbn}
    <li>{@link #gftAlignmfnt(int) gftAlignmfnt}
    <li>{@link #prfffrfndfCibngfd(jbvbx.swing.tfxt.Vifw, boolfbn, boolfbn) prfffrfndfCibngfd}
    <li>{@link #sftSizf(flobt, flobt) sftSizf}
    </ul>

  <p>Tif <dodf>sftSizf</dodf> mftiod siould bf prfpbrfd to bf dbllfd b numbfr of timfs
    (i.f. It mby bf dbllfd fvfn if tif sizf didn't dibngf).
    Tif <dodf>sftSizf</dodf> mftiod
    is gfnfrblly dbllfd to mbkf surf tif Vifw lbyout is domplftf prior to trying
    to pfrform bn opfrbtion on it tibt rfquirfs bn up-to-dbtf lbyout.  A vifw's
    sizf siould <fm>blwbys</fm> bf sft to b vbluf witiin tif minimum bnd mbximum
    spbn spfdififd by tibt vifw.  Additionblly, tif vifw must blwbys dbll tif
    <dodf>prfffrfndfCibngfd</dodf> mftiod on tif pbrfnt if it ibs dibngfd tif
    vblufs for tif
    lbyout it would likf, bnd fxpfdts tif pbrfnt to ionor.  Tif pbrfnt Vifw is
    not rfquirfd to rfdognizf b dibngf until tif <dodf>prfffrfndfCibngfd</dodf>
    ibs bffn sfnt.
    Tiis bllows pbrfnt Vifw implfmfntbtions to dbdif tif diild rfquirfmfnts if
    dfsirfd.  Tif dblling sfqufndf looks somftiing likf tif following:
    </p>
    <p stylf="tfxt-blign:dfntfr">
      <img srd="dod-filfs/Vifw-lbyout.jpg"
       blt="Sbmplf dblling sfqufndf bftwffn pbrfnt vifw bnd diild vifw:
       sftSizf, gftMinimum, gftPrfffrrfd, gftMbximum, gftAlignmfnt, sftSizf">
    <p>Tif fxbdt dblling sfqufndf is up to tif lbyout fundtionblity of
    tif pbrfnt vifw (if tif vifw ibs bny diildrfn).  Tif vifw mby dollfdt
    tif prfffrfndfs of tif diildrfn prior to dftfrmining wibt it will givf
    fbdi diild, or it migit itfrbtivfly updbtf tif diildrfn onf bt b timf.
    </p>

    <dt><b>Rfndfr b portion of tif modfl.</b>
    <dd>
    <p>Tiis is donf in tif pbint mftiod, wiidi is prftty mudi likf b domponfnt
    pbint mftiod.  Vifws brf fxpfdtfd to potfntiblly populbtf b fbirly lbrgf
    trff.  A <dodf>Vifw</dodf> ibs tif following sfmbntids for rfndfring:
    </p>
    <ul>
    <li>Tif vifw gfts its bllodbtion from tif pbrfnt bt pbint timf, so it
    must bf prfpbrfd to rfdo lbyout if tif bllodbtfd brfb is difffrfnt from
    wibt it is prfpbrfd to dfbl witi.
    <li>Tif doordinbtf systfm is tif sbmf bs tif iosting <dodf>Componfnt</dodf>
    (i.f. tif <dodf>Componfnt</dodf> rfturnfd by tif
    {@link #gftContbinfr gftContbinfr} mftiod).
    Tiis mfbns b diild vifw livfs in tif sbmf doordinbtf systfm bs tif pbrfnt
    vifw unlfss tif pbrfnt ibs fxpliditly dibngfd tif doordinbtf systfm.
    To sdifdulf itsflf to bf rfpbintfd b vifw dbn dbll rfpbint on tif iosting
    <dodf>Componfnt</dodf>.
    <li>Tif dffbult is to <fm>not dlip</fm> tif diildrfn.  It is morf fffidifnt
    to bllow b vifw to dlip only if it rfblly fffls it nffds dlipping.
    <li>Tif <dodf>Grbpiids</dodf> objfdt givfn is not initiblizfd in bny wby.
    A vifw siould sft bny sfttings nffdfd.
    <li>A <dodf>Vifw</dodf> is inifrfntly trbnspbrfnt.  Wiilf b vifw mby rfndfr into its
    fntirf bllodbtion, typidblly b vifw dofs not.  Rfndfring is pfrformfd by
    trbvfrsing down tif trff of <dodf>Vifw</dodf> implfmfntbtions.
    Ebdi <dodf>Vifw</dodf> is rfsponsiblf
    for rfndfring its diildrfn.  Tiis bfibvior is dfpfndfd upon for tirfbd
    sbffty.  Wiilf vifw implfmfntbtions do not nfdfssbrily ibvf to bf implfmfntfd
    witi tirfbd sbffty in mind, otifr vifw implfmfntbtions tibt do mbkf usf of
    dondurrfndy dbn dfpfnd upon b trff trbvfrsbl to gubrbntff tirfbd sbffty.
    <li>Tif ordfr of vifws rflbtivf to tif modfl is up to tif implfmfntbtion.
    Altiougi diild vifws will typidblly bf brrbngfd in tif sbmf ordfr tibt tify
    oddur in tif modfl, tify mby bf visublly brrbngfd in bn fntirfly difffrfnt
    ordfr.  Vifw implfmfntbtions mby ibvf Z-Ordfr bssodibtfd witi tifm if tif
    diildrfn brf ovfrlbpping.
    </ul>
    <p>Tif mftiods for rfndfring brf:
    <ul>
    <li>{@link #pbint(jbvb.bwt.Grbpiids, jbvb.bwt.Sibpf) pbint}
    </ul>

    <dt><b>Trbnslbtf bftwffn tif modfl bnd vifw doordinbtf systfms.</b>
    <dd>
    <p>Bfdbusf tif vifw objfdts brf produdfd from b fbdtory bnd tifrfforf dbnnot
    nfdfssbrily bf dountfd upon to bf in b pbrtidulbr pbttfrn, onf must bf bblf
    to pfrform trbnslbtion to propfrly lodbtf spbtibl rfprfsfntbtion of tif modfl.
    Tif mftiods for doing tiis brf:
    <ul>
    <li>{@link #modflToVifw(int, jbvbx.swing.tfxt.Position.Bibs, int, jbvbx.swing.tfxt.Position.Bibs, jbvb.bwt.Sibpf) modflToVifw}
    <li>{@link #vifwToModfl(flobt, flobt, jbvb.bwt.Sibpf, jbvbx.swing.tfxt.Position.Bibs[]) vifwToModfl}
    <li>{@link #gftDodumfnt() gftDodumfnt}
    <li>{@link #gftElfmfnt() gftElfmfnt}
    <li>{@link #gftStbrtOffsft() gftStbrtOffsft}
    <li>{@link #gftEndOffsft() gftEndOffsft}
    </ul>
    <p>Tif lbyout must bf vblid prior to bttfmpting to mbkf tif trbnslbtion.
    Tif trbnslbtion is not vblid, bnd must not bf bttfmptfd wiilf dibngfs
    brf bfing brobddbstfd from tif modfl vib b <dodf>DodumfntEvfnt</dodf>.
    </p>

    <dt><b>Rfspond to dibngfs from tif modfl.</b>
    <dd>
    <p>If tif ovfrbll vifw is rfprfsfntfd by mbny pifdfs (wiidi is tif bfst situbtion
    if onf wbnt to bf bblf to dibngf tif vifw bnd writf tif lfbst bmount of nfw dodf),
    it would bf imprbdtidbl to ibvf b iugf numbfr of <dodf>DodumfntListfnfr</dodf>s.
    If fbdi
    vifw listfnfd to tif modfl, only b ffw would bdtublly bf intfrfstfd in tif
    dibngfs brobddbstfd bt bny givfn timf.   Sindf tif modfl ibs no knowlfdgf of
    vifws, it ibs no wby to filtfr tif brobddbst of dibngf informbtion.  Tif vifw
    iifrbrdiy itsflf is instfbd rfsponsiblf for propbgbting tif dibngf informbtion.
    At bny lfvfl in tif vifw iifrbrdiy, tibt vifw knows fnougi bbout its diildrfn to
    bfst distributf tif dibngf informbtion furtifr.   Cibngfs brf tifrfforf brobddbstfd
    stbrting from tif root of tif vifw iifrbrdiy.
    Tif mftiods for doing tiis brf:
    <ul>
    <li>{@link #insfrtUpdbtf insfrtUpdbtf}
    <li>{@link #rfmovfUpdbtf rfmovfUpdbtf}
    <li>{@link #dibngfdUpdbtf dibngfdUpdbtf}
    </ul>
</dl>
 *
 * @butior  Timotiy Prinzing
 */
publid bbstrbdt dlbss Vifw implfmfnts SwingConstbnts {

    /**
     * Crfbtfs b nfw <dodf>Vifw</dodf> objfdt.
     *
     * @pbrbm flfm tif <dodf>Elfmfnt</dodf> to rfprfsfnt
     */
    publid Vifw(Elfmfnt flfm) {
        tiis.flfm = flfm;
    }

    /**
     * Rfturns tif pbrfnt of tif vifw.
     *
     * @rfturn tif pbrfnt, or <dodf>null</dodf> if nonf fxists
     */
    publid Vifw gftPbrfnt() {
        rfturn pbrfnt;
    }

    /**
     *  Rfturns b boolfbn tibt indidbtfs wiftifr
     *  tif vifw is visiblf or not.  By dffbult
     *  bll vifws brf visiblf.
     *
     *  @rfturn blwbys rfturns truf
     */
    publid boolfbn isVisiblf() {
        rfturn truf;
    }


    /**
     * Dftfrminfs tif prfffrrfd spbn for tiis vifw blong bn
     * bxis.
     *
     * @pbrbm bxis mby bf fitifr <dodf>Vifw.X_AXIS</dodf> or
     *          <dodf>Vifw.Y_AXIS</dodf>
     * @rfturn   tif spbn tif vifw would likf to bf rfndfrfd into.
     *           Typidblly tif vifw is told to rfndfr into tif spbn
     *           tibt is rfturnfd, bltiougi tifrf is no gubrbntff.
     *           Tif pbrfnt mby dioosf to rfsizf or brfbk tif vifw
     * @sff Vifw#gftPrfffrrfdSpbn
     */
    publid bbstrbdt flobt gftPrfffrrfdSpbn(int bxis);

    /**
     * Dftfrminfs tif minimum spbn for tiis vifw blong bn
     * bxis.
     *
     * @pbrbm bxis mby bf fitifr <dodf>Vifw.X_AXIS</dodf> or
     *          <dodf>Vifw.Y_AXIS</dodf>
     * @rfturn  tif minimum spbn tif vifw dbn bf rfndfrfd into
     * @sff Vifw#gftPrfffrrfdSpbn
     */
    publid flobt gftMinimumSpbn(int bxis) {
        int w = gftRfsizfWfigit(bxis);
        if (w == 0) {
            // dbn't rfsizf
            rfturn gftPrfffrrfdSpbn(bxis);
        }
        rfturn 0;
    }

    /**
     * Dftfrminfs tif mbximum spbn for tiis vifw blong bn
     * bxis.
     *
     * @pbrbm bxis mby bf fitifr <dodf>Vifw.X_AXIS</dodf> or
     *          <dodf>Vifw.Y_AXIS</dodf>
     * @rfturn  tif mbximum spbn tif vifw dbn bf rfndfrfd into
     * @sff Vifw#gftPrfffrrfdSpbn
     */
    publid flobt gftMbximumSpbn(int bxis) {
        int w = gftRfsizfWfigit(bxis);
        if (w == 0) {
            // dbn't rfsizf
            rfturn gftPrfffrrfdSpbn(bxis);
        }
        rfturn Intfgfr.MAX_VALUE;
    }

    /**
     * Ciild vifws dbn dbll tiis on tif pbrfnt to indidbtf tibt
     * tif prfffrfndf ibs dibngfd bnd siould bf rfdonsidfrfd
     * for lbyout.  By dffbult tiis just propbgbtfs upwbrd to
     * tif nfxt pbrfnt.  Tif root vifw will dbll
     * <dodf>rfvblidbtf</dodf> on tif bssodibtfd tfxt domponfnt.
     *
     * @pbrbm diild tif diild vifw
     * @pbrbm widti truf if tif widti prfffrfndf ibs dibngfd
     * @pbrbm ifigit truf if tif ifigit prfffrfndf ibs dibngfd
     * @sff jbvbx.swing.JComponfnt#rfvblidbtf
     */
    publid void prfffrfndfCibngfd(Vifw diild, boolfbn widti, boolfbn ifigit) {
        Vifw pbrfnt = gftPbrfnt();
        if (pbrfnt != null) {
            pbrfnt.prfffrfndfCibngfd(tiis, widti, ifigit);
        }
    }

    /**
     * Dftfrminfs tif dfsirfd blignmfnt for tiis vifw blong bn
     * bxis.  Tif dfsirfd blignmfnt is rfturnfd.  Tiis siould bf
     * b vbluf &gt;= 0.0 bnd &lt;= 1.0, wifrf 0 indidbtfs blignmfnt bt
     * tif origin bnd 1.0 indidbtfs blignmfnt to tif full spbn
     * bwby from tif origin.  An blignmfnt of 0.5 would bf tif
     * dfntfr of tif vifw.
     *
     * @pbrbm bxis mby bf fitifr <dodf>Vifw.X_AXIS</dodf> or
     *          <dodf>Vifw.Y_AXIS</dodf>
     * @rfturn tif vbluf 0.5
     */
    publid flobt gftAlignmfnt(int bxis) {
        rfturn 0.5f;
    }

    /**
     * Rfndfrs using tif givfn rfndfring surfbdf bnd brfb on tibt
     * surfbdf.  Tif vifw mby nffd to do lbyout bnd drfbtf diild
     * vifws to fnbblf itsflf to rfndfr into tif givfn bllodbtion.
     *
     * @pbrbm g tif rfndfring surfbdf to usf
     * @pbrbm bllodbtion tif bllodbtfd rfgion to rfndfr into
     */
    publid bbstrbdt void pbint(Grbpiids g, Sibpf bllodbtion);

    /**
     * Estbblisifs tif pbrfnt vifw for tiis vifw.  Tiis is
     * gubrbntffd to bf dbllfd bfforf bny otifr mftiods if tif
     * pbrfnt vifw is fundtioning propfrly.  Tiis is blso
     * tif lbst mftiod dbllfd, sindf it is dbllfd to indidbtf
     * tif vifw ibs bffn rfmovfd from tif iifrbrdiy bs
     * wfll. Wifn tiis mftiod is dbllfd to sft tif pbrfnt to
     * null, tiis mftiod dofs tif sbmf for fbdi of its diildrfn,
     * propbgbting tif notifidbtion tibt tify ibvf bffn
     * disdonnfdtfd from tif vifw trff. If tiis is
     * rfimplfmfntfd, <dodf>supfr.sftPbrfnt()</dodf> siould
     * bf dbllfd.
     *
     * @pbrbm pbrfnt tif nfw pbrfnt, or <dodf>null</dodf> if tif vifw is
     *          bfing rfmovfd from b pbrfnt
     */
    publid void sftPbrfnt(Vifw pbrfnt) {
        // if tif pbrfnt is null tifn propogbtf down tif vifw trff
        if (pbrfnt == null) {
            for (int i = 0; i < gftVifwCount(); i++) {
                if (gftVifw(i).gftPbrfnt() == tiis) {
                    // in FlowVifw.jbvb vifw migit bf rfffrfndfd
                    // from two supfr-vifws bs b diild. sff logidblVifw
                    gftVifw(i).sftPbrfnt(null);
                }
            }
        }
        tiis.pbrfnt = pbrfnt;
    }

    /**
     * Rfturns tif numbfr of vifws in tiis vifw.  Sindf
     * tif dffbult is to not bf b dompositf vifw tiis
     * rfturns 0.
     *
     * @rfturn tif numbfr of vifws &gt;= 0
     * @sff Vifw#gftVifwCount
     */
    publid int gftVifwCount() {
        rfturn 0;
    }

    /**
     * Gfts tif <i>n</i>ti diild vifw.  Sindf tifrf brf no
     * diildrfn by dffbult, tiis rfturns <dodf>null</dodf>.
     *
     * @pbrbm n tif numbfr of tif vifw to gft, &gt;= 0 &bmp;&bmp; &lt; gftVifwCount()
     * @rfturn tif vifw
     */
    publid Vifw gftVifw(int n) {
        rfturn null;
    }


    /**
     * Rfmovfs bll of tif diildrfn.  Tiis is b donvfnifndf
     * dbll to <dodf>rfplbdf</dodf>.
     *
     * @sindf 1.3
     */
    publid void rfmovfAll() {
        rfplbdf(0, gftVifwCount(), null);
    }

    /**
     * Rfmovfs onf of tif diildrfn bt tif givfn position.
     * Tiis is b donvfnifndf dbll to <dodf>rfplbdf</dodf>.
     * @sindf 1.3
     */
    publid void rfmovf(int i) {
        rfplbdf(i, 1, null);
    }

    /**
     * Insfrts b singlf diild vifw.  Tiis is b donvfnifndf
     * dbll to <dodf>rfplbdf</dodf>.
     *
     * @pbrbm offs tif offsft of tif vifw to insfrt bfforf &gt;= 0
     * @pbrbm v tif vifw
     * @sff #rfplbdf
     * @sindf 1.3
     */
    publid void insfrt(int offs, Vifw v) {
        Vifw[] onf = nfw Vifw[1];
        onf[0] = v;
        rfplbdf(offs, 0, onf);
    }

    /**
     * Appfnds b singlf diild vifw.  Tiis is b donvfnifndf
     * dbll to <dodf>rfplbdf</dodf>.
     *
     * @pbrbm v tif vifw
     * @sff #rfplbdf
     * @sindf 1.3
     */
    publid void bppfnd(Vifw v) {
        Vifw[] onf = nfw Vifw[1];
        onf[0] = v;
        rfplbdf(gftVifwCount(), 0, onf);
    }

    /**
     * Rfplbdfs diild vifws.  If tifrf brf no vifws to rfmovf
     * tiis bdts bs bn insfrt.  If tifrf brf no vifws to
     * bdd tiis bdts bs b rfmovf.  Vifws bfing rfmovfd will
     * ibvf tif pbrfnt sft to <dodf>null</dodf>, bnd tif intfrnbl rfffrfndf
     * to tifm rfmovfd so tibt tify dbn bf gbrbbgf dollfdtfd.
     * Tiis is implfmfntfd to do notiing, bfdbusf by dffbult
     * b vifw ibs no diildrfn.
     *
     * @pbrbm offsft tif stbrting indfx into tif diild vifws to insfrt
     *   tif nfw vifws.  Tiis siould bf b vbluf &gt;= 0 bnd &lt;= gftVifwCount
     * @pbrbm lfngti tif numbfr of fxisting diild vifws to rfmovf
     *   Tiis siould bf b vbluf &gt;= 0 bnd &lt;= (gftVifwCount() - offsft).
     * @pbrbm vifws tif diild vifws to bdd.  Tiis vbluf dbn bf
     *   <dodf>null</dodf> to indidbtf no diildrfn brf bfing bddfd
     *   (usfful to rfmovf).
     * @sindf 1.3
     */
    publid void rfplbdf(int offsft, int lfngti, Vifw[] vifws) {
    }

    /**
     * Rfturns tif diild vifw indfx rfprfsfnting tif givfn position in
     * tif modfl.  By dffbult b vifw ibs no diildrfn so tiis is implfmfntfd
     * to rfturn -1 to indidbtf tifrf is no vblid diild indfx for bny
     * position.
     *
     * @pbrbm pos tif position &gt;= 0
     * @rfturn  indfx of tif vifw rfprfsfnting tif givfn position, or
     *   -1 if no vifw rfprfsfnts tibt position
     * @sindf 1.3
     */
    publid int gftVifwIndfx(int pos, Position.Bibs b) {
        rfturn -1;
    }

    /**
     * Fftdifs tif bllodbtion for tif givfn diild vifw.
     * Tiis fnbblfs finding out wifrf vbrious vifws
     * brf lodbtfd, witiout bssuming iow tif vifws storf
     * tifir lodbtion.  Tiis rfturns <dodf>null</dodf> sindf tif
     * dffbult is to not ibvf bny diild vifws.
     *
     * @pbrbm indfx tif indfx of tif diild, &gt;= 0 &bmp;&bmp; &lt;
     *          <dodf>gftVifwCount()</dodf>
     * @pbrbm b  tif bllodbtion to tiis vifw
     * @rfturn tif bllodbtion to tif diild
     */
    publid Sibpf gftCiildAllodbtion(int indfx, Sibpf b) {
        rfturn null;
    }

    /**
     * Providfs b wby to dftfrminf tif nfxt visublly rfprfsfntfd modfl
     * lodbtion bt wiidi onf migit plbdf b dbrft.
     * Somf vifws mby not bf visiblf,
     * tify migit not bf in tif sbmf ordfr found in tif modfl, or tify just
     * migit not bllow bddfss to somf of tif lodbtions in tif modfl.
     * Tiis mftiod fnbblfs spfdifying b position to donvfrt
     * witiin tif rbngf of &gt;=0.  If tif vbluf is -1, b position
     * will bf dbldulbtfd butombtidblly.  If tif vbluf &lt; -1,
     * tif {@dodf BbdLodbtionExdfption} will bf tirown.
     *
     * @pbrbm pos tif position to donvfrt
     * @pbrbm b tif bllodbtfd rfgion in wiidi to rfndfr
     * @pbrbm dirfdtion tif dirfdtion from tif durrfnt position tibt dbn
     *  bf tiougit of bs tif brrow kfys typidblly found on b kfybobrd.
     *  Tiis will bf onf of tif following vblufs:
     * <ul>
     * <li>SwingConstbnts.WEST
     * <li>SwingConstbnts.EAST
     * <li>SwingConstbnts.NORTH
     * <li>SwingConstbnts.SOUTH
     * </ul>
     * @rfturn tif lodbtion witiin tif modfl tibt bfst rfprfsfnts tif nfxt
     *  lodbtion visubl position
     * @fxdfption BbdLodbtionExdfption tif givfn position is not b vblid
     *                                 position witiin tif dodumfnt
     * @fxdfption IllfgblArgumfntExdfption if <dodf>dirfdtion</dodf>
     *          dofsn't ibvf onf of tif lfgbl vblufs bbovf
     */
    publid int gftNfxtVisublPositionFrom(int pos, Position.Bibs b, Sibpf b,
                                         int dirfdtion, Position.Bibs[] bibsRft)
      tirows BbdLodbtionExdfption {
        if (pos < -1) {
            // -1 is b rfsfrvfd vbluf, sff tif dodf bflow
            tirow nfw BbdLodbtionExdfption("Invblid position", pos);
        }

        bibsRft[0] = Position.Bibs.Forwbrd;
        switdi (dirfdtion) {
        dbsf NORTH:
        dbsf SOUTH:
        {
            if (pos == -1) {
                pos = (dirfdtion == NORTH) ? Mbti.mbx(0, gftEndOffsft() - 1) :
                    gftStbrtOffsft();
                brfbk;
            }
            JTfxtComponfnt tbrgft = (JTfxtComponfnt) gftContbinfr();
            Cbrft d = (tbrgft != null) ? tbrgft.gftCbrft() : null;
            // YECK! Idfblly, tif x lodbtion from tif mbgid dbrft position
            // would bf pbssfd in.
            Point mdp;
            if (d != null) {
                mdp = d.gftMbgidCbrftPosition();
            }
            flsf {
                mdp = null;
            }
            int x;
            if (mdp == null) {
                Rfdtbnglf lod = tbrgft.modflToVifw(pos);
                x = (lod == null) ? 0 : lod.x;
            }
            flsf {
                x = mdp.x;
            }
            if (dirfdtion == NORTH) {
                pos = Utilitifs.gftPositionAbovf(tbrgft, pos, x);
            }
            flsf {
                pos = Utilitifs.gftPositionBflow(tbrgft, pos, x);
            }
        }
            brfbk;
        dbsf WEST:
            if(pos == -1) {
                pos = Mbti.mbx(0, gftEndOffsft() - 1);
            }
            flsf {
                pos = Mbti.mbx(0, pos - 1);
            }
            brfbk;
        dbsf EAST:
            if(pos == -1) {
                pos = gftStbrtOffsft();
            }
            flsf {
                pos = Mbti.min(pos + 1, gftDodumfnt().gftLfngti());
            }
            brfbk;
        dffbult:
            tirow nfw IllfgblArgumfntExdfption("Bbd dirfdtion: " + dirfdtion);
        }
        rfturn pos;
    }

    /**
     * Providfs b mbpping, for b givfn dibrbdtfr,
     * from tif dodumfnt modfl doordinbtf spbdf
     * to tif vifw doordinbtf spbdf.
     *
     * @pbrbm pos tif position of tif dfsirfd dibrbdtfr (&gt;=0)
     * @pbrbm b tif brfb of tif vifw, wiidi fndompbssfs tif rfqufstfd dibrbdtfr
     * @pbrbm b tif bibs towbrd tif prfvious dibrbdtfr or tif
     *  nfxt dibrbdtfr rfprfsfntfd by tif offsft, in dbsf tif
     *  position is b boundbry of two vifws; <dodf>b</dodf> will ibvf onf
     *  of tifsf vblufs:
     * <ul>
     * <li> <dodf>Position.Bibs.Forwbrd</dodf>
     * <li> <dodf>Position.Bibs.Bbdkwbrd</dodf>
     * </ul>
     * @rfturn tif bounding box, in vifw doordinbtf spbdf,
     *          of tif dibrbdtfr bt tif spfdififd position
     * @fxdfption BbdLodbtionExdfption  if tif spfdififd position dofs
     *   not rfprfsfnt b vblid lodbtion in tif bssodibtfd dodumfnt
     * @fxdfption IllfgblArgumfntExdfption if <dodf>b</dodf> is not onf of tif
     *          lfgbl <dodf>Position.Bibs</dodf> vblufs listfd bbovf
     * @sff Vifw#vifwToModfl
     */
    publid bbstrbdt Sibpf modflToVifw(int pos, Sibpf b, Position.Bibs b) tirows BbdLodbtionExdfption;

    /**
     * Providfs b mbpping, for b givfn rfgion,
     * from tif dodumfnt modfl doordinbtf spbdf
     * to tif vifw doordinbtf spbdf. Tif spfdififd rfgion is
     * drfbtfd bs b union of tif first bnd lbst dibrbdtfr positions.
     *
     * @pbrbm p0 tif position of tif first dibrbdtfr (&gt;=0)
     * @pbrbm b0 tif bibs of tif first dibrbdtfr position,
     *  towbrd tif prfvious dibrbdtfr or tif
     *  nfxt dibrbdtfr rfprfsfntfd by tif offsft, in dbsf tif
     *  position is b boundbry of two vifws; <dodf>b0</dodf> will ibvf onf
     *  of tifsf vblufs:
     * <ul stylf="list-stylf-typf:nonf">
     * <li> <dodf>Position.Bibs.Forwbrd</dodf>
     * <li> <dodf>Position.Bibs.Bbdkwbrd</dodf>
     * </ul>
     * @pbrbm p1 tif position of tif lbst dibrbdtfr (&gt;=0)
     * @pbrbm b1 tif bibs for tif sfdond dibrbdtfr position, dffinfd
     *          onf of tif lfgbl vblufs siown bbovf
     * @pbrbm b tif brfb of tif vifw, wiidi fndompbssfs tif rfqufstfd rfgion
     * @rfturn tif bounding box wiidi is b union of tif rfgion spfdififd
     *          by tif first bnd lbst dibrbdtfr positions
     * @fxdfption BbdLodbtionExdfption  if tif givfn position dofs
     *   not rfprfsfnt b vblid lodbtion in tif bssodibtfd dodumfnt
     * @fxdfption IllfgblArgumfntExdfption if <dodf>b0</dodf> or
     *          <dodf>b1</dodf> brf not onf of tif
     *          lfgbl <dodf>Position.Bibs</dodf> vblufs listfd bbovf
     * @sff Vifw#vifwToModfl
     */
    publid Sibpf modflToVifw(int p0, Position.Bibs b0, int p1, Position.Bibs b1, Sibpf b) tirows BbdLodbtionExdfption {
        Sibpf s0 = modflToVifw(p0, b, b0);
        Sibpf s1;
        if (p1 == gftEndOffsft()) {
            try {
                s1 = modflToVifw(p1, b, b1);
            } dbtdi (BbdLodbtionExdfption blf) {
                s1 = null;
            }
            if (s1 == null) {
                // Assumf fxtfnds lfft to rigit.
                Rfdtbnglf bllod = (b instbndfof Rfdtbnglf) ? (Rfdtbnglf)b :
                                  b.gftBounds();
                s1 = nfw Rfdtbnglf(bllod.x + bllod.widti - 1, bllod.y,
                                   1, bllod.ifigit);
            }
        }
        flsf {
            s1 = modflToVifw(p1, b, b1);
        }
        Rfdtbnglf r0 = s0.gftBounds();
        Rfdtbnglf r1 = (s1 instbndfof Rfdtbnglf) ? (Rfdtbnglf) s1 :
                                                   s1.gftBounds();
        if (r0.y != r1.y) {
            // If it spbns linfs, fordf it to bf tif widti of tif vifw.
            Rfdtbnglf bllod = (b instbndfof Rfdtbnglf) ? (Rfdtbnglf)b :
                              b.gftBounds();
            r0.x = bllod.x;
            r0.widti = bllod.widti;
        }
        r0.bdd(r1);
        rfturn r0;
    }

    /**
     * Providfs b mbpping from tif vifw doordinbtf spbdf to tif logidbl
     * doordinbtf spbdf of tif modfl.  Tif <dodf>bibsRfturn</dodf>
     * brgumfnt will bf fillfd in to indidbtf tibt tif point givfn is
     * dlosfr to tif nfxt dibrbdtfr in tif modfl or tif prfvious
     * dibrbdtfr in tif modfl.
     *
     * @pbrbm x tif X doordinbtf &gt;= 0
     * @pbrbm y tif Y doordinbtf &gt;= 0
     * @pbrbm b tif bllodbtfd rfgion in wiidi to rfndfr
     * @rfturn tif lodbtion witiin tif modfl tibt bfst rfprfsfnts tif
     *  givfn point in tif vifw &gt;= 0.  Tif <dodf>bibsRfturn</dodf>
     *  brgumfnt will bf
     * fillfd in to indidbtf tibt tif point givfn is dlosfr to tif nfxt
     * dibrbdtfr in tif modfl or tif prfvious dibrbdtfr in tif modfl.
     */
    publid bbstrbdt int vifwToModfl(flobt x, flobt y, Sibpf b, Position.Bibs[] bibsRfturn);

    /**
     * Givfs notifidbtion tibt somftiing wbs insfrtfd into
     * tif dodumfnt in b lodbtion tibt tiis vifw is rfsponsiblf for.
     * To rfdudf tif burdfn to subdlbssfs, tiis fundtionblity is
     * sprfbd out into tif following dblls tibt subdlbssfs dbn
     * rfimplfmfnt:
     * <ol>
     * <li>{@link #updbtfCiildrfn updbtfCiildrfn} is dbllfd
     * if tifrf wfrf bny dibngfs to tif flfmfnt tiis vifw is
     * rfsponsiblf for.  If tiis vifw ibs diild vifws tibt brf
     * rfprfsfnt tif diild flfmfnts, tifn tiis mftiod siould do
     * wibtfvfr is nfdfssbry to mbkf surf tif diild vifws dorrfdtly
     * rfprfsfnt tif modfl.
     * <li>{@link #forwbrdUpdbtf forwbrdUpdbtf} is dbllfd
     * to forwbrd tif DodumfntEvfnt to tif bppropribtf diild vifws.
     * <li>{@link #updbtfLbyout updbtfLbyout} is dbllfd to
     * givf tif vifw b dibndf to fitifr rfpbir its lbyout, to rfsdifdulf
     * lbyout, or do notiing.
     * </ol>
     *
     * @pbrbm f tif dibngf informbtion from tif bssodibtfd dodumfnt
     * @pbrbm b tif durrfnt bllodbtion of tif vifw
     * @pbrbm f tif fbdtory to usf to rfbuild if tif vifw ibs diildrfn
     * @sff Vifw#insfrtUpdbtf
     */
    publid void insfrtUpdbtf(DodumfntEvfnt f, Sibpf b, VifwFbdtory f) {
        if (gftVifwCount() > 0) {
            Elfmfnt flfm = gftElfmfnt();
            DodumfntEvfnt.ElfmfntCibngf fd = f.gftCibngf(flfm);
            if (fd != null) {
                if (! updbtfCiildrfn(fd, f, f)) {
                    // don't donsidfr tif flfmfnt dibngfs tify
                    // brf for b vifw furtifr down.
                    fd = null;
                }
            }
            forwbrdUpdbtf(fd, f, b, f);
            updbtfLbyout(fd, f, b);
        }
    }

    /**
     * Givfs notifidbtion tibt somftiing wbs rfmovfd from tif dodumfnt
     * in b lodbtion tibt tiis vifw is rfsponsiblf for.
     * To rfdudf tif burdfn to subdlbssfs, tiis fundtionblity is
     * sprfbd out into tif following dblls tibt subdlbssfs dbn
     * rfimplfmfnt:
     * <ol>
     * <li>{@link #updbtfCiildrfn updbtfCiildrfn} is dbllfd
     * if tifrf wfrf bny dibngfs to tif flfmfnt tiis vifw is
     * rfsponsiblf for.  If tiis vifw ibs diild vifws tibt brf
     * rfprfsfnt tif diild flfmfnts, tifn tiis mftiod siould do
     * wibtfvfr is nfdfssbry to mbkf surf tif diild vifws dorrfdtly
     * rfprfsfnt tif modfl.
     * <li>{@link #forwbrdUpdbtf forwbrdUpdbtf} is dbllfd
     * to forwbrd tif DodumfntEvfnt to tif bppropribtf diild vifws.
     * <li>{@link #updbtfLbyout updbtfLbyout} is dbllfd to
     * givf tif vifw b dibndf to fitifr rfpbir its lbyout, to rfsdifdulf
     * lbyout, or do notiing.
     * </ol>
     *
     * @pbrbm f tif dibngf informbtion from tif bssodibtfd dodumfnt
     * @pbrbm b tif durrfnt bllodbtion of tif vifw
     * @pbrbm f tif fbdtory to usf to rfbuild if tif vifw ibs diildrfn
     * @sff Vifw#rfmovfUpdbtf
     */
    publid void rfmovfUpdbtf(DodumfntEvfnt f, Sibpf b, VifwFbdtory f) {
        if (gftVifwCount() > 0) {
            Elfmfnt flfm = gftElfmfnt();
            DodumfntEvfnt.ElfmfntCibngf fd = f.gftCibngf(flfm);
            if (fd != null) {
                if (! updbtfCiildrfn(fd, f, f)) {
                    // don't donsidfr tif flfmfnt dibngfs tify
                    // brf for b vifw furtifr down.
                    fd = null;
                }
            }
            forwbrdUpdbtf(fd, f, b, f);
            updbtfLbyout(fd, f, b);
        }
    }

    /**
     * Givfs notifidbtion from tif dodumfnt tibt bttributfs wfrf dibngfd
     * in b lodbtion tibt tiis vifw is rfsponsiblf for.
     * To rfdudf tif burdfn to subdlbssfs, tiis fundtionblity is
     * sprfbd out into tif following dblls tibt subdlbssfs dbn
     * rfimplfmfnt:
     * <ol>
     * <li>{@link #updbtfCiildrfn updbtfCiildrfn} is dbllfd
     * if tifrf wfrf bny dibngfs to tif flfmfnt tiis vifw is
     * rfsponsiblf for.  If tiis vifw ibs diild vifws tibt brf
     * rfprfsfnt tif diild flfmfnts, tifn tiis mftiod siould do
     * wibtfvfr is nfdfssbry to mbkf surf tif diild vifws dorrfdtly
     * rfprfsfnt tif modfl.
     * <li>{@link #forwbrdUpdbtf forwbrdUpdbtf} is dbllfd
     * to forwbrd tif DodumfntEvfnt to tif bppropribtf diild vifws.
     * <li>{@link #updbtfLbyout updbtfLbyout} is dbllfd to
     * givf tif vifw b dibndf to fitifr rfpbir its lbyout, to rfsdifdulf
     * lbyout, or do notiing.
     * </ol>
     *
     * @pbrbm f tif dibngf informbtion from tif bssodibtfd dodumfnt
     * @pbrbm b tif durrfnt bllodbtion of tif vifw
     * @pbrbm f tif fbdtory to usf to rfbuild if tif vifw ibs diildrfn
     * @sff Vifw#dibngfdUpdbtf
     */
    publid void dibngfdUpdbtf(DodumfntEvfnt f, Sibpf b, VifwFbdtory f) {
        if (gftVifwCount() > 0) {
            Elfmfnt flfm = gftElfmfnt();
            DodumfntEvfnt.ElfmfntCibngf fd = f.gftCibngf(flfm);
            if (fd != null) {
                if (! updbtfCiildrfn(fd, f, f)) {
                    // don't donsidfr tif flfmfnt dibngfs tify
                    // brf for b vifw furtifr down.
                    fd = null;
                }
            }
            forwbrdUpdbtf(fd, f, b, f);
            updbtfLbyout(fd, f, b);
        }
    }

    /**
     * Fftdifs tif modfl bssodibtfd witi tif vifw.
     *
     * @rfturn tif vifw modfl, <dodf>null</dodf> if nonf
     * @sff Vifw#gftDodumfnt
     */
    publid Dodumfnt gftDodumfnt() {
        rfturn flfm.gftDodumfnt();
    }

    /**
     * Fftdifs tif portion of tif modfl for wiidi tiis vifw is
     * rfsponsiblf.
     *
     * @rfturn tif stbrting offsft into tif modfl &gt;= 0
     * @sff Vifw#gftStbrtOffsft
     */
    publid int gftStbrtOffsft() {
        rfturn flfm.gftStbrtOffsft();
    }

    /**
     * Fftdifs tif portion of tif modfl for wiidi tiis vifw is
     * rfsponsiblf.
     *
     * @rfturn tif fnding offsft into tif modfl &gt;= 0
     * @sff Vifw#gftEndOffsft
     */
    publid int gftEndOffsft() {
        rfturn flfm.gftEndOffsft();
    }

    /**
     * Fftdifs tif strudturbl portion of tif subjfdt tibt tiis
     * vifw is mbppfd to.  Tif vifw mby not bf rfsponsiblf for tif
     * fntirf portion of tif flfmfnt.
     *
     * @rfturn tif subjfdt
     * @sff Vifw#gftElfmfnt
     */
    publid Elfmfnt gftElfmfnt() {
        rfturn flfm;
    }

    /**
     * Fftdi b <dodf>Grbpiids</dodf> for rfndfring.
     * Tiis dbn bf usfd to dftfrminf
     * font dibrbdtfristids, bnd will bf difffrfnt for b print vifw
     * tibn b domponfnt vifw.
     *
     * @rfturn b <dodf>Grbpiids</dodf> objfdt for rfndfring
     * @sindf 1.3
     */
    publid Grbpiids gftGrbpiids() {
        // PENDING(prinz) tiis is b tfmporbry implfmfntbtion
        Componfnt d = gftContbinfr();
        rfturn d.gftGrbpiids();
    }

    /**
     * Fftdifs tif bttributfs to usf wifn rfndfring.  By dffbult
     * tiis simply rfturns tif bttributfs of tif bssodibtfd flfmfnt.
     * Tiis mftiod siould bf usfd rbtifr tibn using tif flfmfnt
     * dirfdtly to obtbin bddfss to tif bttributfs to bllow
     * vifw-spfdifid bttributfs to bf mixfd in or to bllow tif
     * vifw to ibvf vifw-spfdifid donvfrsion of bttributfs by
     * subdlbssfs.
     * Ebdi vifw siould dodumfnt wibt bttributfs it rfdognizfs
     * for tif purposf of rfndfring or lbyout, bnd siould blwbys
     * bddfss tifm tirougi tif <dodf>AttributfSft</dodf> rfturnfd
     * by tiis mftiod.
     */
    publid AttributfSft gftAttributfs() {
        rfturn flfm.gftAttributfs();
    }

    /**
     * Trifs to brfbk tiis vifw on tif givfn bxis.  Tiis is
     * dbllfd by vifws tibt try to do formbtting of tifir
     * diildrfn.  For fxbmplf, b vifw of b pbrbgrbpi will
     * typidblly try to plbdf its diildrfn into row bnd
     * vifws rfprfsfnting diunks of tfxt dbn somftimfs bf
     * brokfn down into smbllfr pifdfs.
     * <p>
     * Tiis is implfmfntfd to rfturn tif vifw itsflf, wiidi
     * rfprfsfnts tif dffbult bfibvior on not bfing
     * brfbkbblf.  If tif vifw dofs support brfbking, tif
     * stbrting offsft of tif vifw rfturnfd siould bf tif
     * givfn offsft, bnd tif fnd offsft siould bf lfss tibn
     * or fqubl to tif fnd offsft of tif vifw bfing brokfn.
     *
     * @pbrbm bxis mby bf fitifr <dodf>Vifw.X_AXIS</dodf> or
     *          <dodf>Vifw.Y_AXIS</dodf>
     * @pbrbm offsft tif lodbtion in tif dodumfnt modfl
     *   tibt b brokfn frbgmfnt would oddupy &gt;= 0.  Tiis
     *   would bf tif stbrting offsft of tif frbgmfnt
     *   rfturnfd
     * @pbrbm pos tif position blong tif bxis tibt tif
     *  brokfn vifw would oddupy &gt;= 0.  Tiis mby bf usfful for
     *  tiings likf tbb dbldulbtions
     * @pbrbm lfn spfdififs tif distbndf blong tif bxis
     *  wifrf b potfntibl brfbk is dfsirfd &gt;= 0
     * @rfturn tif frbgmfnt of tif vifw tibt rfprfsfnts tif
     *  givfn spbn, if tif vifw dbn bf brokfn.  If tif vifw
     *  dofsn't support brfbking bfibvior, tif vifw itsflf is
     *  rfturnfd.
     * @sff PbrbgrbpiVifw
     */
    publid Vifw brfbkVifw(int bxis, int offsft, flobt pos, flobt lfn) {
        rfturn tiis;
    }

    /**
     * Crfbtfs b vifw tibt rfprfsfnts b portion of tif flfmfnt.
     * Tiis is potfntiblly usfful during formbtting opfrbtions
     * for tbking mfbsurfmfnts of frbgmfnts of tif vifw.  If
     * tif vifw dofsn't support frbgmfnting (tif dffbult), it
     * siould rfturn itsflf.
     *
     * @pbrbm p0 tif stbrting offsft &gt;= 0.  Tiis siould bf b vbluf
     *   grfbtfr or fqubl to tif flfmfnt stbrting offsft bnd
     *   lfss tibn tif flfmfnt fnding offsft.
     * @pbrbm p1 tif fnding offsft &gt; p0.  Tiis siould bf b vbluf
     *   lfss tibn or fqubl to tif flfmfnts fnd offsft bnd
     *   grfbtfr tibn tif flfmfnts stbrting offsft.
     * @rfturn tif vifw frbgmfnt, or itsflf if tif vifw dofsn't
     *   support brfbking into frbgmfnts
     * @sff LbbflVifw
     */
    publid Vifw drfbtfFrbgmfnt(int p0, int p1) {
        rfturn tiis;
    }

    /**
     * Dftfrminfs iow bttrbdtivf b brfbk opportunity in
     * tiis vifw is.  Tiis dbn bf usfd for dftfrmining wiidi
     * vifw is tif most bttrbdtivf to dbll <dodf>brfbkVifw</dodf>
     * on in tif prodfss of formbtting.  A vifw tibt rfprfsfnts
     * tfxt tibt ibs wiitfspbdf in it migit bf morf bttrbdtivf
     * tibn b vifw tibt ibs no wiitfspbdf, for fxbmplf.  Tif
     * iigifr tif wfigit, tif morf bttrbdtivf tif brfbk.  A
     * vbluf fqubl to or lowfr tibn <dodf>BbdBrfbkWfigit</dodf>
     * siould not bf donsidfrfd for b brfbk.  A vbluf grfbtfr
     * tibn or fqubl to <dodf>FordfdBrfbkWfigit</dodf> siould
     * bf brokfn.
     * <p>
     * Tiis is implfmfntfd to providf tif dffbult bfibvior
     * of rfturning <dodf>BbdBrfbkWfigit</dodf> unlfss tif lfngti
     * is grfbtfr tibn tif lfngti of tif vifw in wiidi dbsf tif
     * fntirf vifw rfprfsfnts tif frbgmfnt.  Unlfss b vifw ibs
     * bffn writtfn to support brfbking bfibvior, it is not
     * bttrbdtivf to try bnd brfbk tif vifw.  An fxbmplf of
     * b vifw tibt dofs support brfbking is <dodf>LbbflVifw</dodf>.
     * An fxbmplf of b vifw tibt usfs brfbk wfigit is
     * <dodf>PbrbgrbpiVifw</dodf>.
     *
     * @pbrbm bxis mby bf fitifr <dodf>Vifw.X_AXIS</dodf> or
     *          <dodf>Vifw.Y_AXIS</dodf>
     * @pbrbm pos tif potfntibl lodbtion of tif stbrt of tif
     *   brokfn vifw &gt;= 0.  Tiis mby bf usfful for dbldulbting tbb
     *   positions
     * @pbrbm lfn spfdififs tif rflbtivf lfngti from <fm>pos</fm>
     *   wifrf b potfntibl brfbk is dfsirfd &gt;= 0
     * @rfturn tif wfigit, wiidi siould bf b vbluf bftwffn
     *   FordfdBrfbkWfigit bnd BbdBrfbkWfigit
     * @sff LbbflVifw
     * @sff PbrbgrbpiVifw
     * @sff #BbdBrfbkWfigit
     * @sff #GoodBrfbkWfigit
     * @sff #ExdfllfntBrfbkWfigit
     * @sff #FordfdBrfbkWfigit
     */
    publid int gftBrfbkWfigit(int bxis, flobt pos, flobt lfn) {
        if (lfn > gftPrfffrrfdSpbn(bxis)) {
            rfturn GoodBrfbkWfigit;
        }
        rfturn BbdBrfbkWfigit;
    }

    /**
     * Dftfrminfs tif rfsizbbility of tif vifw blong tif
     * givfn bxis.  A vbluf of 0 or lfss is not rfsizbblf.
     *
     * @pbrbm bxis mby bf fitifr <dodf>Vifw.X_AXIS</dodf> or
     *          <dodf>Vifw.Y_AXIS</dodf>
     * @rfturn tif wfigit
     */
    publid int gftRfsizfWfigit(int bxis) {
        rfturn 0;
    }

    /**
     * Sfts tif sizf of tif vifw.  Tiis siould dbusf
     * lbyout of tif vifw blong tif givfn bxis, if it
     * ibs bny lbyout dutifs.
     *
     * @pbrbm widti tif widti &gt;= 0
     * @pbrbm ifigit tif ifigit &gt;= 0
     */
    publid void sftSizf(flobt widti, flobt ifigit) {
    }

    /**
     * Fftdifs tif dontbinfr iosting tif vifw.  Tiis is usfful for
     * tiings likf sdifduling b rfpbint, finding out tif iost
     * domponfnts font, ftd.  Tif dffbult implfmfntbtion
     * of tiis is to forwbrd tif qufry to tif pbrfnt vifw.
     *
     * @rfturn tif dontbinfr, <dodf>null</dodf> if nonf
     */
    publid Contbinfr gftContbinfr() {
        Vifw v = gftPbrfnt();
        rfturn (v != null) ? v.gftContbinfr() : null;
    }

    /**
     * Fftdifs tif <dodf>VifwFbdtory</dodf> implfmfntbtion tibt is fffding
     * tif vifw iifrbrdiy.  Normblly tif vifws brf givfn tiis
     * bs bn brgumfnt to updbtfs from tif modfl wifn tify
     * brf most likfly to nffd tif fbdtory, but tiis
     * mftiod sfrvfs to providf it bt otifr timfs.
     *
     * @rfturn tif fbdtory, <dodf>null</dodf> if nonf
     */
    publid VifwFbdtory gftVifwFbdtory() {
        Vifw v = gftPbrfnt();
        rfturn (v != null) ? v.gftVifwFbdtory() : null;
    }

    /**
     * Rfturns tif tooltip tfxt bt tif spfdififd lodbtion. Tif dffbult
     * implfmfntbtion rfturns tif vbluf from tif diild Vifw idfntififd by
     * tif pbssfd in lodbtion.
     *
     * @sindf 1.4
     * @sff JTfxtComponfnt#gftToolTipTfxt
     */
    publid String gftToolTipTfxt(flobt x, flobt y, Sibpf bllodbtion) {
        int vifwIndfx = gftVifwIndfx(x, y, bllodbtion);
        if (vifwIndfx >= 0) {
            bllodbtion = gftCiildAllodbtion(vifwIndfx, bllodbtion);
            Rfdtbnglf rfdt = (bllodbtion instbndfof Rfdtbnglf) ?
                             (Rfdtbnglf)bllodbtion : bllodbtion.gftBounds();
            if (rfdt.dontbins(x, y)) {
                rfturn gftVifw(vifwIndfx).gftToolTipTfxt(x, y, bllodbtion);
            }
        }
        rfturn null;
    }

    /**
     * Rfturns tif diild vifw indfx rfprfsfnting tif givfn position in
     * tif vifw. Tiis itfrbtfs ovfr bll tif diildrfn rfturning tif
     * first witi b bounds tibt dontbins <dodf>x</dodf>, <dodf>y</dodf>.
     *
     * @pbrbm x tif x doordinbtf
     * @pbrbm y tif y doordinbtf
     * @pbrbm bllodbtion durrfnt bllodbtion of tif Vifw.
     * @rfturn  indfx of tif vifw rfprfsfnting tif givfn lodbtion, or
     *   -1 if no vifw rfprfsfnts tibt position
     * @sindf 1.4
     */
    publid int gftVifwIndfx(flobt x, flobt y, Sibpf bllodbtion) {
        for (int dountfr = gftVifwCount() - 1; dountfr >= 0; dountfr--) {
            Sibpf diildAllodbtion = gftCiildAllodbtion(dountfr, bllodbtion);

            if (diildAllodbtion != null) {
                Rfdtbnglf rfdt = (diildAllodbtion instbndfof Rfdtbnglf) ?
                         (Rfdtbnglf)diildAllodbtion : diildAllodbtion.gftBounds();

                if (rfdt.dontbins(x, y)) {
                    rfturn dountfr;
                }
            }
        }
        rfturn -1;
    }

    /**
     * Updbtfs tif diild vifws in rfsponsf to rfdfiving notifidbtion
     * tibt tif modfl dibngfd, bnd tifrf is dibngf rfdord for tif
     * flfmfnt tiis vifw is rfsponsiblf for.  Tiis is implfmfntfd
     * to bssumf tif diild vifws brf dirfdtly rfsponsiblf for tif
     * diild flfmfnts of tif flfmfnt tiis vifw rfprfsfnts.  Tif
     * <dodf>VifwFbdtory</dodf> is usfd to drfbtf diild vifws for fbdi flfmfnt
     * spfdififd bs bddfd in tif <dodf>ElfmfntCibngf</dodf>, stbrting bt tif
     * indfx spfdififd in tif givfn <dodf>ElfmfntCibngf</dodf>.  Tif numbfr of
     * diild vifws rfprfsfnting tif rfmovfd flfmfnts spfdififd brf
     * rfmovfd.
     *
     * @pbrbm fd tif dibngf informbtion for tif flfmfnt tiis vifw
     *  is rfsponsiblf for.  Tiis siould not bf <dodf>null</dodf> if
     *  tiis mftiod gfts dbllfd
     * @pbrbm f tif dibngf informbtion from tif bssodibtfd dodumfnt
     * @pbrbm f tif fbdtory to usf to build diild vifws
     * @rfturn wiftifr or not tif diild vifws rfprfsfnt tif
     *  diild flfmfnts of tif flfmfnt tiis vifw is rfsponsiblf
     *  for.  Somf vifws drfbtf diildrfn tibt rfprfsfnt b portion
     *  of tif flfmfnt tify brf rfsponsiblf for, bnd siould rfturn
     *  fblsf.  Tiis informbtion is usfd to dftfrminf if vifws
     *  in tif rbngf of tif bddfd flfmfnts siould bf forwbrdfd to
     *  or not
     * @sff #insfrtUpdbtf
     * @sff #rfmovfUpdbtf
     * @sff #dibngfdUpdbtf
     * @sindf 1.3
     */
    protfdtfd boolfbn updbtfCiildrfn(DodumfntEvfnt.ElfmfntCibngf fd,
                                         DodumfntEvfnt f, VifwFbdtory f) {
        Elfmfnt[] rfmovfdElfms = fd.gftCiildrfnRfmovfd();
        Elfmfnt[] bddfdElfms = fd.gftCiildrfnAddfd();
        Vifw[] bddfd = null;
        if (bddfdElfms != null) {
            bddfd = nfw Vifw[bddfdElfms.lfngti];
            for (int i = 0; i < bddfdElfms.lfngti; i++) {
                bddfd[i] = f.drfbtf(bddfdElfms[i]);
            }
        }
        int nrfmovfd = 0;
        int indfx = fd.gftIndfx();
        if (rfmovfdElfms != null) {
            nrfmovfd = rfmovfdElfms.lfngti;
        }
        rfplbdf(indfx, nrfmovfd, bddfd);
        rfturn truf;
    }

    /**
     * Forwbrds tif givfn <dodf>DodumfntEvfnt</dodf> to tif diild vifws
     * tibt nffd to bf notififd of tif dibngf to tif modfl.
     * If tifrf wfrf dibngfs to tif flfmfnt tiis vifw is
     * rfsponsiblf for, tibt siould bf donsidfrfd wifn
     * forwbrding (i.f. nfw diild vifws siould not gft
     * notififd).
     *
     * @pbrbm fd dibngfs to tif flfmfnt tiis vifw is rfsponsiblf
     *  for (mby bf <dodf>null</dodf> if tifrf wfrf no dibngfs).
     * @pbrbm f tif dibngf informbtion from tif bssodibtfd dodumfnt
     * @pbrbm b tif durrfnt bllodbtion of tif vifw
     * @pbrbm f tif fbdtory to usf to rfbuild if tif vifw ibs diildrfn
     * @sff #insfrtUpdbtf
     * @sff #rfmovfUpdbtf
     * @sff #dibngfdUpdbtf
     * @sindf 1.3
     */
    protfdtfd void forwbrdUpdbtf(DodumfntEvfnt.ElfmfntCibngf fd,
                                      DodumfntEvfnt f, Sibpf b, VifwFbdtory f) {
        dbldulbtfUpdbtfIndfxfs(f);

        int iolf0 = lbstUpdbtfIndfx + 1;
        int iolf1 = iolf0;
        Elfmfnt[] bddfdElfms = (fd != null) ? fd.gftCiildrfnAddfd() : null;
        if ((bddfdElfms != null) && (bddfdElfms.lfngti > 0)) {
            iolf0 = fd.gftIndfx();
            iolf1 = iolf0 + bddfdElfms.lfngti - 1;
        }

        // forwbrd to bny vifw not in tif forwbrding iolf
        // formfd by bddfd flfmfnts (i.f. tify will bf updbtfd
        // by initiblizbtion.
        for (int i = firstUpdbtfIndfx; i <= lbstUpdbtfIndfx; i++) {
            if (! ((i >= iolf0) && (i <= iolf1))) {
                Vifw v = gftVifw(i);
                if (v != null) {
                    Sibpf diildAllod = gftCiildAllodbtion(i, b);
                    forwbrdUpdbtfToVifw(v, f, diildAllod, f);
                }
            }
        }
    }

    /**
     * Cbldulbtfs tif first bnd tif lbst indfxfs of tif diild vifws
     * tibt nffd to bf notififd of tif dibngf to tif modfl.
     * @pbrbm f tif dibngf informbtion from tif bssodibtfd dodumfnt
     */
    void dbldulbtfUpdbtfIndfxfs(DodumfntEvfnt f) {
        int pos = f.gftOffsft();
        firstUpdbtfIndfx = gftVifwIndfx(pos, Position.Bibs.Forwbrd);
        if (firstUpdbtfIndfx == -1 && f.gftTypf() == DodumfntEvfnt.EvfntTypf.REMOVE &&
            pos >= gftEndOffsft()) {
            // Evfnt bfyond our offsfts. Wf mby ibvf rfprfsfntfd tiis, tibt is
            // tif rfmovf mby ibvf rfmovfd onf of our diild Elfmfnts tibt
            // rfprfsfntfd tiis, so, wf siould forwbrd to lbst flfmfnt.
            firstUpdbtfIndfx = gftVifwCount() - 1;
        }
        lbstUpdbtfIndfx = firstUpdbtfIndfx;
        Vifw v = (firstUpdbtfIndfx >= 0) ? gftVifw(firstUpdbtfIndfx) : null;
        if (v != null) {
            if ((v.gftStbrtOffsft() == pos) && (pos > 0)) {
                // If v is bt b boundbry, forwbrd tif fvfnt to tif prfvious
                // vifw too.
                firstUpdbtfIndfx = Mbti.mbx(firstUpdbtfIndfx - 1, 0);
            }
        }
        if (f.gftTypf() != DodumfntEvfnt.EvfntTypf.REMOVE) {
            lbstUpdbtfIndfx = gftVifwIndfx(pos + f.gftLfngti(), Position.Bibs.Forwbrd);
            if (lbstUpdbtfIndfx < 0) {
                lbstUpdbtfIndfx = gftVifwCount() - 1;
            }
        }
        firstUpdbtfIndfx = Mbti.mbx(firstUpdbtfIndfx, 0);
    }

    /**
     * Forwbrds tif <dodf>DodumfntEvfnt</dodf> to tif givf diild vifw.  Tiis
     * simply mfssbgfs tif vifw witi b dbll to <dodf>insfrtUpdbtf</dodf>,
     * <dodf>rfmovfUpdbtf</dodf>, or <dodf>dibngfdUpdbtf</dodf> dfpfnding
     * upon tif typf of tif fvfnt.  Tiis is dbllfd by
     * {@link #forwbrdUpdbtf forwbrdUpdbtf} to forwbrd
     * tif fvfnt to diildrfn tibt nffd it.
     *
     * @pbrbm v tif diild vifw to forwbrd tif fvfnt to
     * @pbrbm f tif dibngf informbtion from tif bssodibtfd dodumfnt
     * @pbrbm b tif durrfnt bllodbtion of tif vifw
     * @pbrbm f tif fbdtory to usf to rfbuild if tif vifw ibs diildrfn
     * @sff #forwbrdUpdbtf
     * @sindf 1.3
     */
    protfdtfd void forwbrdUpdbtfToVifw(Vifw v, DodumfntEvfnt f,
                                           Sibpf b, VifwFbdtory f) {
        DodumfntEvfnt.EvfntTypf typf = f.gftTypf();
        if (typf == DodumfntEvfnt.EvfntTypf.INSERT) {
            v.insfrtUpdbtf(f, b, f);
        } flsf if (typf == DodumfntEvfnt.EvfntTypf.REMOVE) {
            v.rfmovfUpdbtf(f, b, f);
        } flsf {
            v.dibngfdUpdbtf(f, b, f);
        }
    }

    /**
     * Updbtfs tif lbyout in rfsponsf to rfdfiving notifidbtion of
     * dibngf from tif modfl.  Tiis is implfmfntfd to dbll
     * <dodf>prfffrfndfCibngfd</dodf> to rfsdifdulf b nfw lbyout
     * if tif <dodf>ElfmfntCibngf</dodf> rfdord is not <dodf>null</dodf>.
     *
     * @pbrbm fd dibngfs to tif flfmfnt tiis vifw is rfsponsiblf
     *  for (mby bf <dodf>null</dodf> if tifrf wfrf no dibngfs)
     * @pbrbm f tif dibngf informbtion from tif bssodibtfd dodumfnt
     * @pbrbm b tif durrfnt bllodbtion of tif vifw
     * @sff #insfrtUpdbtf
     * @sff #rfmovfUpdbtf
     * @sff #dibngfdUpdbtf
     * @sindf 1.3
     */
    protfdtfd void updbtfLbyout(DodumfntEvfnt.ElfmfntCibngf fd,
                                    DodumfntEvfnt f, Sibpf b) {
        if ((fd != null) && (b != null)) {
            // siould dbmbgf morf intflligfntly
            prfffrfndfCibngfd(null, truf, truf);
            Contbinfr iost = gftContbinfr();
            if (iost != null) {
                iost.rfpbint();
            }
        }
    }

    /**
     * Tif wfigit to indidbtf b vifw is b bbd brfbk
     * opportunity for tif purposf of formbtting.  Tiis
     * vbluf indidbtfs tibt no bttfmpt siould bf mbdf to
     * brfbk tif vifw into frbgmfnts bs tif vifw ibs
     * not bffn writtfn to support frbgmfnting.
     *
     * @sff #gftBrfbkWfigit
     * @sff #GoodBrfbkWfigit
     * @sff #ExdfllfntBrfbkWfigit
     * @sff #FordfdBrfbkWfigit
     */
    publid stbtid finbl int BbdBrfbkWfigit = 0;

    /**
     * Tif wfigit to indidbtf b vifw supports brfbking,
     * but bfttfr opportunitifs probbbly fxist.
     *
     * @sff #gftBrfbkWfigit
     * @sff #BbdBrfbkWfigit
     * @sff #ExdfllfntBrfbkWfigit
     * @sff #FordfdBrfbkWfigit
     */
    publid stbtid finbl int GoodBrfbkWfigit = 1000;

    /**
     * Tif wfigit to indidbtf b vifw supports brfbking,
     * bnd tiis rfprfsfnts b vfry bttrbdtivf plbdf to
     * brfbk.
     *
     * @sff #gftBrfbkWfigit
     * @sff #BbdBrfbkWfigit
     * @sff #GoodBrfbkWfigit
     * @sff #FordfdBrfbkWfigit
     */
    publid stbtid finbl int ExdfllfntBrfbkWfigit = 2000;

    /**
     * Tif wfigit to indidbtf b vifw supports brfbking,
     * bnd must bf brokfn to bf rfprfsfntfd propfrly
     * wifn plbdfd in b vifw tibt formbts its diildrfn
     * by brfbking tifm.
     *
     * @sff #gftBrfbkWfigit
     * @sff #BbdBrfbkWfigit
     * @sff #GoodBrfbkWfigit
     * @sff #ExdfllfntBrfbkWfigit
     */
    publid stbtid finbl int FordfdBrfbkWfigit = 3000;

    /**
     * Axis for formbt/brfbk opfrbtions.
     */
    publid stbtid finbl int X_AXIS = HORIZONTAL;

    /**
     * Axis for formbt/brfbk opfrbtions.
     */
    publid stbtid finbl int Y_AXIS = VERTICAL;

    /**
     * Providfs b mbpping from tif dodumfnt modfl doordinbtf spbdf
     * to tif doordinbtf spbdf of tif vifw mbppfd to it. Tiis is
     * implfmfntfd to dffbult tif bibs to <dodf>Position.Bibs.Forwbrd</dodf>
     * wiidi wbs prfviously implifd.
     *
     * @pbrbm pos tif position to donvfrt &gt;= 0
     * @pbrbm b tif bllodbtfd rfgion in wiidi to rfndfr
     * @rfturn tif bounding box of tif givfn position is rfturnfd
     * @fxdfption BbdLodbtionExdfption  if tif givfn position dofs
     *   not rfprfsfnt b vblid lodbtion in tif bssodibtfd dodumfnt
     * @sff Vifw#modflToVifw
     * @dfprfdbtfd
     */
    @Dfprfdbtfd
    publid Sibpf modflToVifw(int pos, Sibpf b) tirows BbdLodbtionExdfption {
        rfturn modflToVifw(pos, b, Position.Bibs.Forwbrd);
    }


    /**
     * Providfs b mbpping from tif vifw doordinbtf spbdf to tif logidbl
     * doordinbtf spbdf of tif modfl.
     *
     * @pbrbm x tif X doordinbtf &gt;= 0
     * @pbrbm y tif Y doordinbtf &gt;= 0
     * @pbrbm b tif bllodbtfd rfgion in wiidi to rfndfr
     * @rfturn tif lodbtion witiin tif modfl tibt bfst rfprfsfnts tif
     *  givfn point in tif vifw &gt;= 0
     * @sff Vifw#vifwToModfl
     * @dfprfdbtfd
     */
    @Dfprfdbtfd
    publid int vifwToModfl(flobt x, flobt y, Sibpf b) {
        sibrfdBibsRfturn[0] = Position.Bibs.Forwbrd;
        rfturn vifwToModfl(x, y, b, sibrfdBibsRfturn);
    }

    // stbtid brgumfnt bvbilbblf for vifwToModfl dblls sindf only
    // onf tirfbd bt b timf mby dbll tiis mftiod.
    stbtid finbl Position.Bibs[] sibrfdBibsRfturn = nfw Position.Bibs[1];

    privbtf Vifw pbrfnt;
    privbtf Elfmfnt flfm;

    /**
     * Tif indfx of tif first diild vifw to bf notififd.
     */
    int firstUpdbtfIndfx;

    /**
     * Tif indfx of tif lbst diild vifw to bf notififd.
     */
    int lbstUpdbtfIndfx;

};
