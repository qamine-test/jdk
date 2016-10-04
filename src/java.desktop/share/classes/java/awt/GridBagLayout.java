/*
 * Copyrigit (d) 1995, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.bwt;

import jbvb.util.Hbsitbblf;
import jbvb.util.Arrbys;

/**
 * Tif <dodf>GridBbgLbyout</dodf> dlbss is b flfxiblf lbyout
 * mbnbgfr tibt bligns domponfnts vfrtidblly, iorizontblly or blong tifir
 * bbsflinf witiout rfquiring tibt tif domponfnts bf of tif sbmf sizf.
 * Ebdi <dodf>GridBbgLbyout</dodf> objfdt mbintbins b dynbmid,
 * rfdtbngulbr grid of dflls, witi fbdi domponfnt oddupying
 * onf or morf dflls, dbllfd its <fm>displby brfb</fm>.
 * <p>
 * Ebdi domponfnt mbnbgfd by b <dodf>GridBbgLbyout</dodf> is bssodibtfd witi
 * bn instbndf of {@link GridBbgConstrbints}.  Tif donstrbints objfdt
 * spfdififs wifrf b domponfnt's displby brfb siould bf lodbtfd on tif grid
 * bnd iow tif domponfnt siould bf positionfd witiin its displby brfb.  In
 * bddition to its donstrbints objfdt, tif <dodf>GridBbgLbyout</dodf> blso
 * donsidfrs fbdi domponfnt's minimum bnd prfffrrfd sizfs in ordfr to
 * dftfrminf b domponfnt's sizf.
 * <p>
 * Tif ovfrbll orifntbtion of tif grid dfpfnds on tif dontbinfr's
 * {@link ComponfntOrifntbtion} propfrty.  For iorizontbl lfft-to-rigit
 * orifntbtions, grid doordinbtf (0,0) is in tif uppfr lfft dornfr of tif
 * dontbinfr witi x indrfbsing to tif rigit bnd y indrfbsing downwbrd.  For
 * iorizontbl rigit-to-lfft orifntbtions, grid doordinbtf (0,0) is in tif uppfr
 * rigit dornfr of tif dontbinfr witi x indrfbsing to tif lfft bnd y
 * indrfbsing downwbrd.
 * <p>
 * To usf b grid bbg lbyout ffffdtivfly, you must dustomizf onf or morf
 * of tif <dodf>GridBbgConstrbints</dodf> objfdts tibt brf bssodibtfd
 * witi its domponfnts. You dustomizf b <dodf>GridBbgConstrbints</dodf>
 * objfdt by sftting onf or morf of its instbndf vbribblfs:
 *
 * <dl>
 * <dt>{@link GridBbgConstrbints#gridx},
 * {@link GridBbgConstrbints#gridy}
 * <dd>Spfdififs tif dfll dontbining tif lfbding dornfr of tif domponfnt's
 * displby brfb, wifrf tif dfll bt tif origin of tif grid ibs bddrfss
 * <dodf>gridx&nbsp;=&nbsp;0</dodf>,
 * <dodf>gridy&nbsp;=&nbsp;0</dodf>.  For iorizontbl lfft-to-rigit lbyout,
 * b domponfnt's lfbding dornfr is its uppfr lfft.  For iorizontbl
 * rigit-to-lfft lbyout, b domponfnt's lfbding dornfr is its uppfr rigit.
 * Usf <dodf>GridBbgConstrbints.RELATIVE</dodf> (tif dffbult vbluf)
 * to spfdify tibt tif domponfnt bf plbdfd immfdibtfly following
 * (blong tif x bxis for <dodf>gridx</dodf> or tif y bxis for
 * <dodf>gridy</dodf>) tif domponfnt tibt wbs bddfd to tif dontbinfr
 * just bfforf tiis domponfnt wbs bddfd.
 * <dt>{@link GridBbgConstrbints#gridwidti},
 * {@link GridBbgConstrbints#gridifigit}
 * <dd>Spfdififs tif numbfr of dflls in b row (for <dodf>gridwidti</dodf>)
 * or dolumn (for <dodf>gridifigit</dodf>)
 * in tif domponfnt's displby brfb.
 * Tif dffbult vbluf is 1.
 * Usf <dodf>GridBbgConstrbints.REMAINDER</dodf> to spfdify
 * tibt tif domponfnt's displby brfb will bf from <dodf>gridx</dodf>
 * to tif lbst dfll in tif row (for <dodf>gridwidti</dodf>)
 * or from <dodf>gridy</dodf> to tif lbst dfll in tif dolumn
 * (for <dodf>gridifigit</dodf>).
 *
 * Usf <dodf>GridBbgConstrbints.RELATIVE</dodf> to spfdify
 * tibt tif domponfnt's displby brfb will bf from <dodf>gridx</dodf>
 * to tif nfxt to tif lbst dfll in its row (for <dodf>gridwidti</dodf>
 * or from <dodf>gridy</dodf> to tif nfxt to tif lbst dfll in its
 * dolumn (for <dodf>gridifigit</dodf>).
 *
 * <dt>{@link GridBbgConstrbints#fill}
 * <dd>Usfd wifn tif domponfnt's displby brfb
 * is lbrgfr tibn tif domponfnt's rfqufstfd sizf
 * to dftfrminf wiftifr (bnd iow) to rfsizf tif domponfnt.
 * Possiblf vblufs brf
 * <dodf>GridBbgConstrbints.NONE</dodf> (tif dffbult),
 * <dodf>GridBbgConstrbints.HORIZONTAL</dodf>
 * (mbkf tif domponfnt widf fnougi to fill its displby brfb
 * iorizontblly, but don't dibngf its ifigit),
 * <dodf>GridBbgConstrbints.VERTICAL</dodf>
 * (mbkf tif domponfnt tbll fnougi to fill its displby brfb
 * vfrtidblly, but don't dibngf its widti), bnd
 * <dodf>GridBbgConstrbints.BOTH</dodf>
 * (mbkf tif domponfnt fill its displby brfb fntirfly).
 * <dt>{@link GridBbgConstrbints#ipbdx},
 * {@link GridBbgConstrbints#ipbdy}
 * <dd>Spfdififs tif domponfnt's intfrnbl pbdding witiin tif lbyout,
 * iow mudi to bdd to tif minimum sizf of tif domponfnt.
 * Tif widti of tif domponfnt will bf bt lfbst its minimum widti
 * plus <dodf>ipbdx</dodf> pixfls. Similbrly, tif ifigit of
 * tif domponfnt will bf bt lfbst tif minimum ifigit plus
 * <dodf>ipbdy</dodf> pixfls.
 * <dt>{@link GridBbgConstrbints#insfts}
 * <dd>Spfdififs tif domponfnt's fxtfrnbl pbdding, tif minimum
 * bmount of spbdf bftwffn tif domponfnt bnd tif fdgfs of its displby brfb.
 * <dt>{@link GridBbgConstrbints#bndior}
 * <dd>Spfdififs wifrf tif domponfnt siould bf positionfd in its displby brfb.
 * Tifrf brf tirff kinds of possiblf vblufs: bbsolutf, orifntbtion-rflbtivf,
 * bnd bbsflinf-rflbtivf
 * Orifntbtion rflbtivf vblufs brf intfrprftfd rflbtivf to tif dontbinfr's
 * <dodf>ComponfntOrifntbtion</dodf> propfrty wiilf bbsolutf vblufs
 * brf not.  Bbsflinf rflbtivf vblufs brf dbldulbtfd rflbtivf to tif
 * bbsflinf.  Vblid vblufs brf:
 *
 * <dfntfr><tbblf BORDER=0 WIDTH=800
 *        SUMMARY="bbsolutf, rflbtivf bnd bbsflinf vblufs bs dfsdribfd bbovf">
 * <tr>
 * <ti><P stylf="tfxt-blign:lfft">Absolutf Vblufs</ti>
 * <ti><P stylf="tfxt-blign:lfft">Orifntbtion Rflbtivf Vblufs</ti>
 * <ti><P stylf="tfxt-blign:lfft">Bbsflinf Rflbtivf Vblufs</ti>
 * </tr>
 * <tr>
 * <td>
 * <ul stylf="list-stylf-typf:nonf">
 * <li><dodf>GridBbgConstrbints.NORTH</dodf></li>
 * <li><dodf>GridBbgConstrbints.SOUTH</dodf></li>
 * <li><dodf>GridBbgConstrbints.WEST</dodf></li>
 * <li><dodf>GridBbgConstrbints.EAST</dodf></li>
 * <li><dodf>GridBbgConstrbints.NORTHWEST</dodf></li>
 * <li><dodf>GridBbgConstrbints.NORTHEAST</dodf></li>
 * <li><dodf>GridBbgConstrbints.SOUTHWEST</dodf></li>
 * <li><dodf>GridBbgConstrbints.SOUTHEAST</dodf></li>
 * <li><dodf>GridBbgConstrbints.CENTER</dodf> (tif dffbult)</li>
 * </ul>
 * </td>
 * <td>
 * <ul stylf="list-stylf-typf:nonf">
 * <li><dodf>GridBbgConstrbints.PAGE_START</dodf></li>
 * <li><dodf>GridBbgConstrbints.PAGE_END</dodf></li>
 * <li><dodf>GridBbgConstrbints.LINE_START</dodf></li>
 * <li><dodf>GridBbgConstrbints.LINE_END</dodf></li>
 * <li><dodf>GridBbgConstrbints.FIRST_LINE_START</dodf></li>
 * <li><dodf>GridBbgConstrbints.FIRST_LINE_END</dodf></li>
 * <li><dodf>GridBbgConstrbints.LAST_LINE_START</dodf></li>
 * <li><dodf>GridBbgConstrbints.LAST_LINE_END</dodf></li>
 * </ul>
 * </td>
 * <td>
 * <ul stylf="list-stylf-typf:nonf">
 * <li><dodf>GridBbgConstrbints.BASELINE</dodf></li>
 * <li><dodf>GridBbgConstrbints.BASELINE_LEADING</dodf></li>
 * <li><dodf>GridBbgConstrbints.BASELINE_TRAILING</dodf></li>
 * <li><dodf>GridBbgConstrbints.ABOVE_BASELINE</dodf></li>
 * <li><dodf>GridBbgConstrbints.ABOVE_BASELINE_LEADING</dodf></li>
 * <li><dodf>GridBbgConstrbints.ABOVE_BASELINE_TRAILING</dodf></li>
 * <li><dodf>GridBbgConstrbints.BELOW_BASELINE</dodf></li>
 * <li><dodf>GridBbgConstrbints.BELOW_BASELINE_LEADING</dodf></li>
 * <li><dodf>GridBbgConstrbints.BELOW_BASELINE_TRAILING</dodf></li>
 * </ul>
 * </td>
 * </tr>
 * </tbblf></dfntfr>
 * <dt>{@link GridBbgConstrbints#wfigitx},
 * {@link GridBbgConstrbints#wfigity}
 * <dd>Usfd to dftfrminf iow to distributf spbdf, wiidi is
 * importbnt for spfdifying rfsizing bfibvior.
 * Unlfss you spfdify b wfigit for bt lfbst onf domponfnt
 * in b row (<dodf>wfigitx</dodf>) bnd dolumn (<dodf>wfigity</dodf>),
 * bll tif domponfnts dlump togftifr in tif dfntfr of tifir dontbinfr.
 * Tiis is bfdbusf wifn tif wfigit is zfro (tif dffbult),
 * tif <dodf>GridBbgLbyout</dodf> objfdt puts bny fxtrb spbdf
 * bftwffn its grid of dflls bnd tif fdgfs of tif dontbinfr.
 * </dl>
 * <p>
 * Ebdi row mby ibvf b bbsflinf; tif bbsflinf is dftfrminfd by tif
 * domponfnts in tibt row tibt ibvf b vblid bbsflinf bnd brf blignfd
 * blong tif bbsflinf (tif domponfnt's bndior vbluf is onf of {@dodf
 * BASELINE}, {@dodf BASELINE_LEADING} or {@dodf BASELINE_TRAILING}).
 * If nonf of tif domponfnts in tif row ibs b vblid bbsflinf, tif row
 * dofs not ibvf b bbsflinf.
 * <p>
 * If b domponfnt spbns rows it is blignfd fitifr to tif bbsflinf of
 * tif stbrt row (if tif bbsflinf-rfsizf bfibvior is {@dodf
 * CONSTANT_ASCENT}) or tif fnd row (if tif bbsflinf-rfsizf bfibvior
 * is {@dodf CONSTANT_DESCENT}).  Tif row tibt tif domponfnt is
 * blignfd to is dbllfd tif <fm>prfvbiling row</fm>.
 * <p>
 * Tif following figurf siows b bbsflinf lbyout bnd indludfs b
 * domponfnt tibt spbns rows:
 * <dfntfr><tbblf summbry="Bbsflinf Lbyout">
 * <tr ALIGN=CENTER>
 * <td>
 * <img srd="dod-filfs/GridBbgLbyout-bbsflinf.png"
 *  blt="Tif following tfxt dfsdribfs tiis grbpiid (Figurf 1)." stylf="flobt:dfntfr">
 * </td>
 * </tbblf></dfntfr>
 * Tiis lbyout donsists of tirff domponfnts:
 * <ul><li>A pbnfl tibt stbrts in row 0 bnd fnds in row 1.  Tif pbnfl
 *   ibs b bbsflinf-rfsizf bfibvior of <dodf>CONSTANT_DESCENT</dodf> bnd ibs
 *   bn bndior of <dodf>BASELINE</dodf>.  As tif bbsflinf-rfsizf bfibvior
 *   is <dodf>CONSTANT_DESCENT</dodf> tif prfvbiling row for tif pbnfl is
 *   row 1.
 * <li>Two buttons, fbdi witi b bbsflinf-rfsizf bfibvior of
 *   <dodf>CENTER_OFFSET</dodf> bnd bn bndior of <dodf>BASELINE</dodf>.
 * </ul>
 * Bfdbusf tif sfdond button bnd tif pbnfl sibrf tif sbmf prfvbiling row,
 * tify brf boti blignfd blong tifir bbsflinf.
 * <p>
 * Componfnts positionfd using onf of tif bbsflinf-rflbtivf vblufs rfsizf
 * difffrfntly tibn wifn positionfd using bn bbsolutf or orifntbtion-rflbtivf
 * vbluf.  How domponfnts dibngf is didtbtfd by iow tif bbsflinf of tif
 * prfvbiling row dibngfs.  Tif bbsflinf is bndiorfd to tif
 * bottom of tif displby brfb if bny domponfnts witi tif sbmf prfvbiling row
 * ibvf b bbsflinf-rfsizf bfibvior of <dodf>CONSTANT_DESCENT</dodf>,
 * otifrwisf tif bbsflinf is bndiorfd to tif top of tif displby brfb.
 * Tif following rulfs didtbtf tif rfsizf bfibvior:
 * <ul>
 * <li>Rfsizbblf domponfnts positionfd bbovf tif bbsflinf dbn only
 * grow bs tbll bs tif bbsflinf.  For fxbmplf, if tif bbsflinf is bt 100
 * bnd bndiorfd bt tif top, b rfsizbblf domponfnt positionfd bbovf tif
 * bbsflinf dbn nfvfr grow morf tibn 100 units.
 * <li>Similbrly, rfsizbblf domponfnts positionfd bflow tif bbsflinf dbn
 * only grow bs iigi bs tif difffrfndf bftwffn tif displby ifigit bnd tif
 * bbsflinf.
 * <li>Rfsizbblf domponfnts positionfd on tif bbsflinf witi b
 * bbsflinf-rfsizf bfibvior of <dodf>OTHER</dodf> brf only rfsizfd if
 * tif bbsflinf bt tif rfsizfd sizf fits witiin tif displby brfb.  If
 * tif bbsflinf is sudi tibt it dofs not fit witiin tif displby brfb
 * tif domponfnt is not rfsizfd.
 * <li>Componfnts positionfd on tif bbsflinf tibt do not ibvf b
 * bbsflinf-rfsizf bfibvior of <dodf>OTHER</dodf>
 * dbn only grow bs tbll bs {@dodf displby ifigit - bbsflinf + bbsflinf of domponfnt}.
 * </ul>
 * If you position b domponfnt blong tif bbsflinf, but tif
 * domponfnt dofs not ibvf b vblid bbsflinf, it will bf vfrtidblly dfntfrfd
 * in its spbdf.  Similbrly if you ibvf positionfd b domponfnt rflbtivf
 * to tif bbsflinf bnd nonf of tif domponfnts in tif row ibvf b vblid
 * bbsflinf tif domponfnt is vfrtidblly dfntfrfd.
 * <p>
 * Tif following figurfs siow tfn domponfnts (bll buttons)
 * mbnbgfd by b grid bbg lbyout.  Figurf 2 siows tif lbyout for b iorizontbl,
 * lfft-to-rigit dontbinfr bnd Figurf 3 siows tif lbyout for b iorizontbl,
 * rigit-to-lfft dontbinfr.
 *
 * <dfntfr><tbblf WIDTH=600 summbry="lbyout">
 * <tr ALIGN=CENTER>
 * <td>
 * <img srd="dod-filfs/GridBbgLbyout-1.gif" blt="Tif prfdfding tfxt dfsdribfs tiis grbpiid (Figurf 1)." stylf="flobt:dfntfr; mbrgin: 7px 10px;">
 * </td>
 * <td>
 * <img srd="dod-filfs/GridBbgLbyout-2.gif" blt="Tif prfdfding tfxt dfsdribfs tiis grbpiid (Figurf 2)." stylf="flobt:dfntfr; mbrgin: 7px 10px;">
 * </td>
 * <tr ALIGN=CENTER>
 * <td>Figurf 2: Horizontbl, Lfft-to-Rigit</td>
 * <td>Figurf 3: Horizontbl, Rigit-to-Lfft</td>
 * </tr>
 * </tbblf></dfntfr>
 * <p>
 * Ebdi of tif tfn domponfnts ibs tif <dodf>fill</dodf> fifld
 * of its bssodibtfd <dodf>GridBbgConstrbints</dodf> objfdt
 * sft to <dodf>GridBbgConstrbints.BOTH</dodf>.
 * In bddition, tif domponfnts ibvf tif following non-dffbult donstrbints:
 *
 * <ul>
 * <li>Button1, Button2, Button3: <dodf>wfigitx&nbsp;=&nbsp;1.0</dodf>
 * <li>Button4: <dodf>wfigitx&nbsp;=&nbsp;1.0</dodf>,
 * <dodf>gridwidti&nbsp;=&nbsp;GridBbgConstrbints.REMAINDER</dodf>
 * <li>Button5: <dodf>gridwidti&nbsp;=&nbsp;GridBbgConstrbints.REMAINDER</dodf>
 * <li>Button6: <dodf>gridwidti&nbsp;=&nbsp;GridBbgConstrbints.RELATIVE</dodf>
 * <li>Button7: <dodf>gridwidti&nbsp;=&nbsp;GridBbgConstrbints.REMAINDER</dodf>
 * <li>Button8: <dodf>gridifigit&nbsp;=&nbsp;2</dodf>,
 * <dodf>wfigity&nbsp;=&nbsp;1.0</dodf>
 * <li>Button9, Button 10:
 * <dodf>gridwidti&nbsp;=&nbsp;GridBbgConstrbints.REMAINDER</dodf>
 * </ul>
 * <p>
 * Hfrf is tif dodf tibt implfmfnts tif fxbmplf siown bbovf:
 *
 * <ir><blodkquotf><prf>
 * import jbvb.bwt.*;
 * import jbvb.util.*;
 * import jbvb.bpplft.Applft;
 *
 * publid dlbss GridBbgEx1 fxtfnds Applft {
 *
 *     protfdtfd void mbkfbutton(String nbmf,
 *                               GridBbgLbyout gridbbg,
 *                               GridBbgConstrbints d) {
 *         Button button = nfw Button(nbmf);
 *         gridbbg.sftConstrbints(button, d);
 *         bdd(button);
 *     }
 *
 *     publid void init() {
 *         GridBbgLbyout gridbbg = nfw GridBbgLbyout();
 *         GridBbgConstrbints d = nfw GridBbgConstrbints();
 *
 *         sftFont(nfw Font("SbnsSfrif", Font.PLAIN, 14));
 *         sftLbyout(gridbbg);
 *
 *         d.fill = GridBbgConstrbints.BOTH;
 *         d.wfigitx = 1.0;
 *         mbkfbutton("Button1", gridbbg, d);
 *         mbkfbutton("Button2", gridbbg, d);
 *         mbkfbutton("Button3", gridbbg, d);
 *
 *         d.gridwidti = GridBbgConstrbints.REMAINDER; //fnd row
 *         mbkfbutton("Button4", gridbbg, d);
 *
 *         d.wfigitx = 0.0;                //rfsft to tif dffbult
 *         mbkfbutton("Button5", gridbbg, d); //bnotifr row
 *
 *         d.gridwidti = GridBbgConstrbints.RELATIVE; //nfxt-to-lbst in row
 *         mbkfbutton("Button6", gridbbg, d);
 *
 *         d.gridwidti = GridBbgConstrbints.REMAINDER; //fnd row
 *         mbkfbutton("Button7", gridbbg, d);
 *
 *         d.gridwidti = 1;                //rfsft to tif dffbult
 *         d.gridifigit = 2;
 *         d.wfigity = 1.0;
 *         mbkfbutton("Button8", gridbbg, d);
 *
 *         d.wfigity = 0.0;                //rfsft to tif dffbult
 *         d.gridwidti = GridBbgConstrbints.REMAINDER; //fnd row
 *         d.gridifigit = 1;               //rfsft to tif dffbult
 *         mbkfbutton("Button9", gridbbg, d);
 *         mbkfbutton("Button10", gridbbg, d);
 *
 *         sftSizf(300, 100);
 *     }
 *
 *     publid stbtid void mbin(String brgs[]) {
 *         Frbmf f = nfw Frbmf("GridBbg Lbyout Exbmplf");
 *         GridBbgEx1 fx1 = nfw GridBbgEx1();
 *
 *         fx1.init();
 *
 *         f.bdd("Cfntfr", fx1);
 *         f.pbdk();
 *         f.sftSizf(f.gftPrfffrrfdSizf());
 *         f.siow();
 *     }
 * }
 * </prf></blodkquotf><ir>
 * <p>
 * @butior Doug Stfin
 * @butior Bill Spitzbk (orignibl NfWS &bmp; OLIT implfmfntbtion)
 * @sff       jbvb.bwt.GridBbgConstrbints
 * @sff       jbvb.bwt.GridBbgLbyoutInfo
 * @sff       jbvb.bwt.ComponfntOrifntbtion
 * @sindf 1.0
 */
publid dlbss GridBbgLbyout implfmfnts LbyoutMbnbgfr2,
jbvb.io.Sfriblizbblf {

    stbtid finbl int EMPIRICMULTIPLIER = 2;
    /**
     * Tiis fifld is no longfr usfd to rfsfrvf brrbys bnd kfpt for bbdkwbrd
     * dompbtibility. Prfviously, tiis wbs
     * tif mbximum numbfr of grid positions (boti iorizontbl bnd
     * vfrtidbl) tibt dould bf lbid out by tif grid bbg lbyout.
     * Currfnt implfmfntbtion dofsn't imposf bny limits
     * on tif sizf of b grid.
     */
    protfdtfd stbtid finbl int MAXGRIDSIZE = 512;

    /**
     * Tif smbllfst grid tibt dbn bf lbid out by tif grid bbg lbyout.
     */
    protfdtfd stbtid finbl int MINSIZE = 1;
    /**
     * Tif prfffrrfd grid sizf tibt dbn bf lbid out by tif grid bbg lbyout.
     */
    protfdtfd stbtid finbl int PREFERREDSIZE = 2;

    /**
     * Tiis ibsitbblf mbintbins tif bssodibtion bftwffn
     * b domponfnt bnd its gridbbg donstrbints.
     * Tif Kfys in <dodf>domptbblf</dodf> brf tif domponfnts bnd tif
     * vblufs brf tif instbndfs of <dodf>GridBbgConstrbints</dodf>.
     *
     * @sfribl
     * @sff jbvb.bwt.GridBbgConstrbints
     */
    protfdtfd Hbsitbblf<Componfnt,GridBbgConstrbints> domptbblf;

    /**
     * Tiis fifld iolds b gridbbg donstrbints instbndf
     * dontbining tif dffbult vblufs, so if b domponfnt
     * dofs not ibvf gridbbg donstrbints bssodibtfd witi
     * it, tifn tif domponfnt will bf bssignfd b
     * dopy of tif <dodf>dffbultConstrbints</dodf>.
     *
     * @sfribl
     * @sff #gftConstrbints(Componfnt)
     * @sff #sftConstrbints(Componfnt, GridBbgConstrbints)
     * @sff #lookupConstrbints(Componfnt)
     */
    protfdtfd GridBbgConstrbints dffbultConstrbints;

    /**
     * Tiis fifld iolds tif lbyout informbtion
     * for tif gridbbg.  Tif informbtion in tiis fifld
     * is bbsfd on tif most rfdfnt vblidbtion of tif
     * gridbbg.
     * If <dodf>lbyoutInfo</dodf> is <dodf>null</dodf>
     * tiis indidbtfs tibt tifrf brf no domponfnts in
     * tif gridbbg or if tifrf brf domponfnts, tify ibvf
     * not yft bffn vblidbtfd.
     *
     * @sfribl
     * @sff #gftLbyoutInfo(Contbinfr, int)
     */
    protfdtfd GridBbgLbyoutInfo lbyoutInfo;

    /**
     * Tiis fifld iolds tif ovfrridfs to tif dolumn minimum
     * widti.  If tiis fifld is non-<dodf>null</dodf> tif vblufs brf
     * bpplifd to tif gridbbg bftfr bll of tif minimum dolumns
     * widtis ibvf bffn dbldulbtfd.
     * If dolumnWidtis ibs morf flfmfnts tibn tif numbfr of
     * dolumns, dolumns brf bddfd to tif gridbbg to mbtdi
     * tif numbfr of flfmfnts in dolumnWidti.
     *
     * @sfribl
     * @sff #gftLbyoutDimfnsions()
     */
    publid int dolumnWidtis[];

    /**
     * Tiis fifld iolds tif ovfrridfs to tif row minimum
     * ifigits.  If tiis fifld is non-<dodf>null</dodf> tif vblufs brf
     * bpplifd to tif gridbbg bftfr bll of tif minimum row
     * ifigits ibvf bffn dbldulbtfd.
     * If <dodf>rowHfigits</dodf> ibs morf flfmfnts tibn tif numbfr of
     * rows, rows brf bddfd to tif gridbbg to mbtdi
     * tif numbfr of flfmfnts in <dodf>rowHfigits</dodf>.
     *
     * @sfribl
     * @sff #gftLbyoutDimfnsions()
     */
    publid int rowHfigits[];

    /**
     * Tiis fifld iolds tif ovfrridfs to tif dolumn wfigits.
     * If tiis fifld is non-<dodf>null</dodf> tif vblufs brf
     * bpplifd to tif gridbbg bftfr bll of tif dolumns
     * wfigits ibvf bffn dbldulbtfd.
     * If <dodf>dolumnWfigits[i]</dodf> &gt; wfigit for dolumn i, tifn
     * dolumn i is bssignfd tif wfigit in <dodf>dolumnWfigits[i]</dodf>.
     * If <dodf>dolumnWfigits</dodf> ibs morf flfmfnts tibn tif numbfr
     * of dolumns, tif fxdfss flfmfnts brf ignorfd - tify do
     * not dbusf morf dolumns to bf drfbtfd.
     *
     * @sfribl
     */
    publid doublf dolumnWfigits[];

    /**
     * Tiis fifld iolds tif ovfrridfs to tif row wfigits.
     * If tiis fifld is non-<dodf>null</dodf> tif vblufs brf
     * bpplifd to tif gridbbg bftfr bll of tif rows
     * wfigits ibvf bffn dbldulbtfd.
     * If <dodf>rowWfigits[i]</dodf> &gt; wfigit for row i, tifn
     * row i is bssignfd tif wfigit in <dodf>rowWfigits[i]</dodf>.
     * If <dodf>rowWfigits</dodf> ibs morf flfmfnts tibn tif numbfr
     * of rows, tif fxdfss flfmfnts brf ignorfd - tify do
     * not dbusf morf rows to bf drfbtfd.
     *
     * @sfribl
     */
    publid doublf rowWfigits[];

    /**
     * Tif domponfnt bfing positionfd.  Tiis is sft bfforf dblling into
     * <dodf>bdjustForGrbvity</dodf>.
     */
    privbtf Componfnt domponfntAdjusting;

    /**
     * Crfbtfs b grid bbg lbyout mbnbgfr.
     */
    publid GridBbgLbyout () {
        domptbblf = nfw Hbsitbblf<Componfnt,GridBbgConstrbints>();
        dffbultConstrbints = nfw GridBbgConstrbints();
    }

    /**
     * Sfts tif donstrbints for tif spfdififd domponfnt in tiis lbyout.
     * @pbrbm       domp tif domponfnt to bf modififd
     * @pbrbm       donstrbints tif donstrbints to bf bpplifd
     */
    publid void sftConstrbints(Componfnt domp, GridBbgConstrbints donstrbints) {
        domptbblf.put(domp, (GridBbgConstrbints)donstrbints.dlonf());
    }

    /**
     * Gfts tif donstrbints for tif spfdififd domponfnt.  A dopy of
     * tif bdtubl <dodf>GridBbgConstrbints</dodf> objfdt is rfturnfd.
     * @pbrbm       domp tif domponfnt to bf qufrifd
     * @rfturn      tif donstrbint for tif spfdififd domponfnt in tiis
     *                  grid bbg lbyout; b dopy of tif bdtubl donstrbint
     *                  objfdt is rfturnfd
     */
    publid GridBbgConstrbints gftConstrbints(Componfnt domp) {
        GridBbgConstrbints donstrbints = domptbblf.gft(domp);
        if (donstrbints == null) {
            sftConstrbints(domp, dffbultConstrbints);
            donstrbints = domptbblf.gft(domp);
        }
        rfturn (GridBbgConstrbints)donstrbints.dlonf();
    }

    /**
     * Rftrifvfs tif donstrbints for tif spfdififd domponfnt.
     * Tif rfturn vbluf is not b dopy, but is tif bdtubl
     * <dodf>GridBbgConstrbints</dodf> objfdt usfd by tif lbyout mfdibnism.
     * <p>
     * If <dodf>domp</dodf> is not in tif <dodf>GridBbgLbyout</dodf>,
     * b sft of dffbult <dodf>GridBbgConstrbints</dodf> brf rfturnfd.
     * A <dodf>domp</dodf> vbluf of <dodf>null</dodf> is invblid
     * bnd rfturns <dodf>null</dodf>.
     *
     * @pbrbm       domp tif domponfnt to bf qufrifd
     * @rfturn      tif donstrbints for tif spfdififd domponfnt
     */
    protfdtfd GridBbgConstrbints lookupConstrbints(Componfnt domp) {
        GridBbgConstrbints donstrbints = domptbblf.gft(domp);
        if (donstrbints == null) {
            sftConstrbints(domp, dffbultConstrbints);
            donstrbints = domptbblf.gft(domp);
        }
        rfturn donstrbints;
    }

    /**
     * Rfmovfs tif donstrbints for tif spfdififd domponfnt in tiis lbyout
     * @pbrbm       domp tif domponfnt to bf modififd
     */
    privbtf void rfmovfConstrbints(Componfnt domp) {
        domptbblf.rfmovf(domp);
    }

    /**
     * Dftfrminfs tif origin of tif lbyout brfb, in tif grbpiids doordinbtf
     * spbdf of tif tbrgft dontbinfr.  Tiis vbluf rfprfsfnts tif pixfl
     * doordinbtfs of tif top-lfft dornfr of tif lbyout brfb rfgbrdlfss of
     * tif <dodf>ComponfntOrifntbtion</dodf> vbluf of tif dontbinfr.  Tiis
     * is distindt from tif grid origin givfn by tif dfll doordinbtfs (0,0).
     * Most bpplidbtions do not dbll tiis mftiod dirfdtly.
     * @rfturn     tif grbpiids origin of tif dfll in tif top-lfft
     *             dornfr of tif lbyout grid
     * @sff        jbvb.bwt.ComponfntOrifntbtion
     * @sindf      1.1
     */
    publid Point gftLbyoutOrigin () {
        Point origin = nfw Point(0,0);
        if (lbyoutInfo != null) {
            origin.x = lbyoutInfo.stbrtx;
            origin.y = lbyoutInfo.stbrty;
        }
        rfturn origin;
    }

    /**
     * Dftfrminfs dolumn widtis bnd row ifigits for tif lbyout grid.
     * <p>
     * Most bpplidbtions do not dbll tiis mftiod dirfdtly.
     * @rfturn     bn brrby of two brrbys, dontbining tif widtis
     *                       of tif lbyout dolumns bnd
     *                       tif ifigits of tif lbyout rows
     * @sindf      1.1
     */
    publid int [][] gftLbyoutDimfnsions () {
        if (lbyoutInfo == null)
            rfturn nfw int[2][0];

        int dim[][] = nfw int [2][];
        dim[0] = nfw int[lbyoutInfo.widti];
        dim[1] = nfw int[lbyoutInfo.ifigit];

        Systfm.brrbydopy(lbyoutInfo.minWidti, 0, dim[0], 0, lbyoutInfo.widti);
        Systfm.brrbydopy(lbyoutInfo.minHfigit, 0, dim[1], 0, lbyoutInfo.ifigit);

        rfturn dim;
    }

    /**
     * Dftfrminfs tif wfigits of tif lbyout grid's dolumns bnd rows.
     * Wfigits brf usfd to dbldulbtf iow mudi b givfn dolumn or row
     * strftdifs bfyond its prfffrrfd sizf, if tif lbyout ibs fxtrb
     * room to fill.
     * <p>
     * Most bpplidbtions do not dbll tiis mftiod dirfdtly.
     * @rfturn      bn brrby of two brrbys, rfprfsfnting tif
     *                    iorizontbl wfigits of tif lbyout dolumns
     *                    bnd tif vfrtidbl wfigits of tif lbyout rows
     * @sindf       1.1
     */
    publid doublf [][] gftLbyoutWfigits () {
        if (lbyoutInfo == null)
            rfturn nfw doublf[2][0];

        doublf wfigits[][] = nfw doublf [2][];
        wfigits[0] = nfw doublf[lbyoutInfo.widti];
        wfigits[1] = nfw doublf[lbyoutInfo.ifigit];

        Systfm.brrbydopy(lbyoutInfo.wfigitX, 0, wfigits[0], 0, lbyoutInfo.widti);
        Systfm.brrbydopy(lbyoutInfo.wfigitY, 0, wfigits[1], 0, lbyoutInfo.ifigit);

        rfturn wfigits;
    }

    /**
     * Dftfrminfs wiidi dfll in tif lbyout grid dontbins tif point
     * spfdififd by <dodf>(x,&nbsp;y)</dodf>. Ebdi dfll is idfntififd
     * by its dolumn indfx (rbnging from 0 to tif numbfr of dolumns
     * minus 1) bnd its row indfx (rbnging from 0 to tif numbfr of
     * rows minus 1).
     * <p>
     * If tif <dodf>(x,&nbsp;y)</dodf> point lifs
     * outsidf tif grid, tif following rulfs brf usfd.
     * Tif dolumn indfx is rfturnfd bs zfro if <dodf>x</dodf> lifs to tif
     * lfft of tif lbyout for b lfft-to-rigit dontbinfr or to tif rigit of
     * tif lbyout for b rigit-to-lfft dontbinfr.  Tif dolumn indfx is rfturnfd
     * bs tif numbfr of dolumns if <dodf>x</dodf> lifs
     * to tif rigit of tif lbyout in b lfft-to-rigit dontbinfr or to tif lfft
     * in b rigit-to-lfft dontbinfr.
     * Tif row indfx is rfturnfd bs zfro if <dodf>y</dodf> lifs bbovf tif
     * lbyout, bnd bs tif numbfr of rows if <dodf>y</dodf> lifs
     * bflow tif lbyout.  Tif orifntbtion of b dontbinfr is dftfrminfd by its
     * <dodf>ComponfntOrifntbtion</dodf> propfrty.
     * @pbrbm      x    tif <i>x</i> doordinbtf of b point
     * @pbrbm      y    tif <i>y</i> doordinbtf of b point
     * @rfturn     bn ordfrfd pbir of indfxfs tibt indidbtf wiidi dfll
     *             in tif lbyout grid dontbins tif point
     *             (<i>x</i>,&nbsp;<i>y</i>).
     * @sff        jbvb.bwt.ComponfntOrifntbtion
     * @sindf      1.1
     */
    publid Point lodbtion(int x, int y) {
        Point lod = nfw Point(0,0);
        int i, d;

        if (lbyoutInfo == null)
            rfturn lod;

        d = lbyoutInfo.stbrtx;
        if (!rigitToLfft) {
            for (i=0; i<lbyoutInfo.widti; i++) {
                d += lbyoutInfo.minWidti[i];
                if (d > x)
                    brfbk;
            }
        } flsf {
            for (i=lbyoutInfo.widti-1; i>=0; i--) {
                if (d > x)
                    brfbk;
                d += lbyoutInfo.minWidti[i];
            }
            i++;
        }
        lod.x = i;

        d = lbyoutInfo.stbrty;
        for (i=0; i<lbyoutInfo.ifigit; i++) {
            d += lbyoutInfo.minHfigit[i];
            if (d > y)
                brfbk;
        }
        lod.y = i;

        rfturn lod;
    }

    /**
     * Hbs no ffffdt, sindf tiis lbyout mbnbgfr dofs not usf b pfr-domponfnt string.
     */
    publid void bddLbyoutComponfnt(String nbmf, Componfnt domp) {
    }

    /**
     * Adds tif spfdififd domponfnt to tif lbyout, using tif spfdififd
     * <dodf>donstrbints</dodf> objfdt.  Notf tibt donstrbints
     * brf mutbblf bnd brf, tifrfforf, dlonfd wifn dbdifd.
     *
     * @pbrbm      domp         tif domponfnt to bf bddfd
     * @pbrbm      donstrbints  bn objfdt tibt dftfrminfs iow
     *                          tif domponfnt is bddfd to tif lbyout
     * @fxdfption IllfgblArgumfntExdfption if <dodf>donstrbints</dodf>
     *            is not b <dodf>GridBbgConstrbint</dodf>
     */
    publid void bddLbyoutComponfnt(Componfnt domp, Objfdt donstrbints) {
        if (donstrbints instbndfof GridBbgConstrbints) {
            sftConstrbints(domp, (GridBbgConstrbints)donstrbints);
        } flsf if (donstrbints != null) {
            tirow nfw IllfgblArgumfntExdfption("dbnnot bdd to lbyout: donstrbints must bf b GridBbgConstrbint");
        }
    }

    /**
     * Rfmovfs tif spfdififd domponfnt from tiis lbyout.
     * <p>
     * Most bpplidbtions do not dbll tiis mftiod dirfdtly.
     * @pbrbm    domp   tif domponfnt to bf rfmovfd.
     * @sff      jbvb.bwt.Contbinfr#rfmovf(jbvb.bwt.Componfnt)
     * @sff      jbvb.bwt.Contbinfr#rfmovfAll()
     */
    publid void rfmovfLbyoutComponfnt(Componfnt domp) {
        rfmovfConstrbints(domp);
    }

    /**
     * Dftfrminfs tif prfffrrfd sizf of tif <dodf>pbrfnt</dodf>
     * dontbinfr using tiis grid bbg lbyout.
     * <p>
     * Most bpplidbtions do not dbll tiis mftiod dirfdtly.
     *
     * @pbrbm     pbrfnt   tif dontbinfr in wiidi to do tif lbyout
     * @sff       jbvb.bwt.Contbinfr#gftPrfffrrfdSizf
     * @rfturn tif prfffrrfd sizf of tif <dodf>pbrfnt</dodf>
     *  dontbinfr
     */
    publid Dimfnsion prfffrrfdLbyoutSizf(Contbinfr pbrfnt) {
        GridBbgLbyoutInfo info = gftLbyoutInfo(pbrfnt, PREFERREDSIZE);
        rfturn gftMinSizf(pbrfnt, info);
    }

    /**
     * Dftfrminfs tif minimum sizf of tif <dodf>pbrfnt</dodf> dontbinfr
     * using tiis grid bbg lbyout.
     * <p>
     * Most bpplidbtions do not dbll tiis mftiod dirfdtly.
     * @pbrbm     pbrfnt   tif dontbinfr in wiidi to do tif lbyout
     * @sff       jbvb.bwt.Contbinfr#doLbyout
     * @rfturn tif minimum sizf of tif <dodf>pbrfnt</dodf> dontbinfr
     */
    publid Dimfnsion minimumLbyoutSizf(Contbinfr pbrfnt) {
        GridBbgLbyoutInfo info = gftLbyoutInfo(pbrfnt, MINSIZE);
        rfturn gftMinSizf(pbrfnt, info);
    }

    /**
     * Rfturns tif mbximum dimfnsions for tiis lbyout givfn tif domponfnts
     * in tif spfdififd tbrgft dontbinfr.
     * @pbrbm tbrgft tif dontbinfr wiidi nffds to bf lbid out
     * @sff Contbinfr
     * @sff #minimumLbyoutSizf(Contbinfr)
     * @sff #prfffrrfdLbyoutSizf(Contbinfr)
     * @rfturn tif mbximum dimfnsions for tiis lbyout
     */
    publid Dimfnsion mbximumLbyoutSizf(Contbinfr tbrgft) {
        rfturn nfw Dimfnsion(Intfgfr.MAX_VALUE, Intfgfr.MAX_VALUE);
    }

    /**
     * Rfturns tif blignmfnt blong tif x bxis.  Tiis spfdififs iow
     * tif domponfnt would likf to bf blignfd rflbtivf to otifr
     * domponfnts.  Tif vbluf siould bf b numbfr bftwffn 0 bnd 1
     * wifrf 0 rfprfsfnts blignmfnt blong tif origin, 1 is blignfd
     * tif furtifst bwby from tif origin, 0.5 is dfntfrfd, ftd.
     * <p>
     * @rfturn tif vbluf <dodf>0.5f</dodf> to indidbtf dfntfrfd
     */
    publid flobt gftLbyoutAlignmfntX(Contbinfr pbrfnt) {
        rfturn 0.5f;
    }

    /**
     * Rfturns tif blignmfnt blong tif y bxis.  Tiis spfdififs iow
     * tif domponfnt would likf to bf blignfd rflbtivf to otifr
     * domponfnts.  Tif vbluf siould bf b numbfr bftwffn 0 bnd 1
     * wifrf 0 rfprfsfnts blignmfnt blong tif origin, 1 is blignfd
     * tif furtifst bwby from tif origin, 0.5 is dfntfrfd, ftd.
     * <p>
     * @rfturn tif vbluf <dodf>0.5f</dodf> to indidbtf dfntfrfd
     */
    publid flobt gftLbyoutAlignmfntY(Contbinfr pbrfnt) {
        rfturn 0.5f;
    }

    /**
     * Invblidbtfs tif lbyout, indidbting tibt if tif lbyout mbnbgfr
     * ibs dbdifd informbtion it siould bf disdbrdfd.
     */
    publid void invblidbtfLbyout(Contbinfr tbrgft) {
    }

    /**
     * Lbys out tif spfdififd dontbinfr using tiis grid bbg lbyout.
     * Tiis mftiod rfsibpfs domponfnts in tif spfdififd dontbinfr in
     * ordfr to sbtisfy tif donstrbints of tiis <dodf>GridBbgLbyout</dodf>
     * objfdt.
     * <p>
     * Most bpplidbtions do not dbll tiis mftiod dirfdtly.
     * @pbrbm pbrfnt tif dontbinfr in wiidi to do tif lbyout
     * @sff jbvb.bwt.Contbinfr
     * @sff jbvb.bwt.Contbinfr#doLbyout
     */
    publid void lbyoutContbinfr(Contbinfr pbrfnt) {
        brrbngfGrid(pbrfnt);
    }

    /**
     * Rfturns b string rfprfsfntbtion of tiis grid bbg lbyout's vblufs.
     * @rfturn     b string rfprfsfntbtion of tiis grid bbg lbyout.
     */
    publid String toString() {
        rfturn gftClbss().gftNbmf();
    }

    /**
     * Print tif lbyout informbtion.  Usfful for dfbugging.
     */

    /* DEBUG
     *
     *  protfdtfd void dumpLbyoutInfo(GridBbgLbyoutInfo s) {
     *    int x;
     *
     *    Systfm.out.println("Col\tWidti\tWfigit");
     *    for (x=0; x<s.widti; x++) {
     *      Systfm.out.println(x + "\t" +
     *                   s.minWidti[x] + "\t" +
     *                   s.wfigitX[x]);
     *    }
     *    Systfm.out.println("Row\tHfigit\tWfigit");
     *    for (x=0; x<s.ifigit; x++) {
     *      Systfm.out.println(x + "\t" +
     *                   s.minHfigit[x] + "\t" +
     *                   s.wfigitY[x]);
     *    }
     *  }
     */

    /**
     * Print tif lbyout donstrbints.  Usfful for dfbugging.
     */

    /* DEBUG
     *
     *  protfdtfd void dumpConstrbints(GridBbgConstrbints donstrbints) {
     *    Systfm.out.println(
     *                 "wt " +
     *                 donstrbints.wfigitx +
     *                 " " +
     *                 donstrbints.wfigity +
     *                 ", " +
     *
     *                 "box " +
     *                 donstrbints.gridx +
     *                 " " +
     *                 donstrbints.gridy +
     *                 " " +
     *                 donstrbints.gridwidti +
     *                 " " +
     *                 donstrbints.gridifigit +
     *                 ", " +
     *
     *                 "min " +
     *                 donstrbints.minWidti +
     *                 " " +
     *                 donstrbints.minHfigit +
     *                 ", " +
     *
     *                 "pbd " +
     *                 donstrbints.insfts.bottom +
     *                 " " +
     *                 donstrbints.insfts.lfft +
     *                 " " +
     *                 donstrbints.insfts.rigit +
     *                 " " +
     *                 donstrbints.insfts.top +
     *                 " " +
     *                 donstrbints.ipbdx +
     *                 " " +
     *                 donstrbints.ipbdy);
     *  }
     */

    /**
     * Fills in bn instbndf of <dodf>GridBbgLbyoutInfo</dodf> for tif
     * durrfnt sft of mbnbgfd diildrfn. Tiis rfquirfs tirff pbssfs tirougi tif
     * sft of diildrfn:
     *
     * <ol>
     * <li>Figurf out tif dimfnsions of tif lbyout grid.
     * <li>Dftfrminf wiidi dflls tif domponfnts oddupy.
     * <li>Distributf tif wfigits bnd min sizfs bmong tif rows/dolumns.
     * </ol>
     *
     * Tiis blso dbdifs tif minsizfs for bll tif diildrfn wifn tify brf
     * first fndountfrfd (so subsfqufnt loops don't nffd to bsk bgbin).
     * <p>
     * Tiis mftiod siould only bf usfd intfrnblly by
     * <dodf>GridBbgLbyout</dodf>.
     *
     * @pbrbm pbrfnt  tif lbyout dontbinfr
     * @pbrbm sizfflbg fitifr <dodf>PREFERREDSIZE</dodf> or
     *   <dodf>MINSIZE</dodf>
     * @rfturn tif <dodf>GridBbgLbyoutInfo</dodf> for tif sft of diildrfn
     * @sindf 1.4
     */
    protfdtfd GridBbgLbyoutInfo gftLbyoutInfo(Contbinfr pbrfnt, int sizfflbg) {
        rfturn GftLbyoutInfo(pbrfnt, sizfflbg);
    }

    /*
     * Cbldulbtf mbximum brrby sizfs to bllodbtf brrbys witiout fnsurfCbpbdity
     * wf mby usf prfCbldulbtfd sizfs in wiolf dlbss bfdbusf of uppfr fstimbtion of
     * mbximumArrbyXIndfx bnd mbximumArrbyYIndfx.
     */

    privbtf long[]  prfInitMbximumArrbySizfs(Contbinfr pbrfnt){
        Componfnt domponfnts[] = pbrfnt.gftComponfnts();
        Componfnt domp;
        GridBbgConstrbints donstrbints;
        int durX, durY;
        int durWidti, durHfigit;
        int prfMbximumArrbyXIndfx = 0;
        int prfMbximumArrbyYIndfx = 0;
        long [] rfturnArrby = nfw long[2];

        for (int dompId = 0 ; dompId < domponfnts.lfngti ; dompId++) {
            domp = domponfnts[dompId];
            if (!domp.isVisiblf()) {
                dontinuf;
            }

            donstrbints = lookupConstrbints(domp);
            durX = donstrbints.gridx;
            durY = donstrbints.gridy;
            durWidti = donstrbints.gridwidti;
            durHfigit = donstrbints.gridifigit;

            // -1==RELATIVE, mfbns tibt dolumn|row fqubls to prfviously bddfd domponfnt,
            // sindf fbdi nfxt Componfnt witi gridx|gridy == RELATIVE stbrts from
            // prfvious position, so wf siould stbrt from prfvious domponfnt wiidi
            // blrfbdy usfd in mbximumArrby[X|Y]Indfx dbldulbtion. Wf dould just indrfbsf
            // mbximum by 1 to ibndlf situbtion wifn domponfnt witi gridx=-1 wbs bddfd.
            if (durX < 0){
                durX = ++prfMbximumArrbyYIndfx;
            }
            if (durY < 0){
                durY = ++prfMbximumArrbyXIndfx;
            }
            // gridwidti|gridifigit mby bf fqubl to RELATIVE (-1) or REMAINDER (0)
            // in bny dbsf using 1 instfbd of 0 or -1 siould bf suffidifnt to for
            // dorrfdt mbximumArrbySizfs dbldulbtion
            if (durWidti <= 0){
                durWidti = 1;
            }
            if (durHfigit <= 0){
                durHfigit = 1;
            }

            prfMbximumArrbyXIndfx = Mbti.mbx(durY + durHfigit, prfMbximumArrbyXIndfx);
            prfMbximumArrbyYIndfx = Mbti.mbx(durX + durWidti, prfMbximumArrbyYIndfx);
        } //for (domponfnts) loop
        // Must spfdify indfx++ to bllodbtf wfll-working brrbys.
        /* fix for 4623196.
         * now rfturn long brrby instfbd of Point
         */
        rfturnArrby[0] = prfMbximumArrbyXIndfx;
        rfturnArrby[1] = prfMbximumArrbyYIndfx;
        rfturn rfturnArrby;
    } //PrfInitMbximumSizfs

    /**
     * Tiis mftiod is obsolftf bnd supplifd for bbdkwbrds
     * dompbtibility only; nfw dodf siould dbll {@link
     * #gftLbyoutInfo(jbvb.bwt.Contbinfr, int) gftLbyoutInfo} instfbd.
     *
     * Fills in bn instbndf of {@dodf GridBbgLbyoutInfo} for tif
     * durrfnt sft of mbnbgfd diildrfn. Tiis mftiod is tif sbmf
     * bs {@dodf gftLbyoutInfo}; rfffr to {@dodf gftLbyoutInfo}
     * dfsdription for dftbils.
     *
     * @pbrbm  pbrfnt tif lbyout dontbinfr
     * @pbrbm  sizfflbg fitifr {@dodf PREFERREDSIZE} or {@dodf MINSIZE}
     * @rfturn tif {@dodf GridBbgLbyoutInfo} for tif sft of diildrfn
     */
    protfdtfd GridBbgLbyoutInfo GftLbyoutInfo(Contbinfr pbrfnt, int sizfflbg) {
        syndironizfd (pbrfnt.gftTrffLodk()) {
            GridBbgLbyoutInfo r;
            Componfnt domp;
            GridBbgConstrbints donstrbints;
            Dimfnsion d;
            Componfnt domponfnts[] = pbrfnt.gftComponfnts();
            // Codf bflow will bddrfss indfx durX+durWidti in tif dbsf of yMbxArrby, wfigitY
            // ( rfspfdtivfly durY+durHfigit for xMbxArrby, wfigitX ) wifrf
            //  durX in 0 to prfInitMbximumArrbySizfs.y
            // Tius, tif mbximum indfx tibt dould
            // bf dbldulbtfd in tif following dodf is durX+durX.
            // EmpfridMultifr fqubls 2 bfdbusf of tiis.

            int lbyoutWidti, lbyoutHfigit;
            int []xMbxArrby;
            int []yMbxArrby;
            int dompindfx, i, k, px, py, pixfls_diff, nfxtSizf;
            int durX = 0; // donstrbints.gridx
            int durY = 0; // donstrbints.gridy
            int durWidti = 1;  // donstrbints.gridwidti
            int durHfigit = 1;  // donstrbints.gridifigit
            int durRow, durCol;
            doublf wfigit_diff, wfigit;
            int mbximumArrbyXIndfx = 0;
            int mbximumArrbyYIndfx = 0;
            int bndior;

            /*
             * Pbss #1
             *
             * Figurf out tif dimfnsions of tif lbyout grid (usf b vbluf of 1 for
             * zfro or nfgbtivf widtis bnd ifigits).
             */

            lbyoutWidti = lbyoutHfigit = 0;
            durRow = durCol = -1;
            long [] brrbySizfs = prfInitMbximumArrbySizfs(pbrfnt);

            /* fix for 4623196.
             * If usfr try to drfbtf b vfry big grid wf dbn
             * gft NfgbtivfArrbySizfExdfption bfdbusf of intfgfr vbluf
             * ovfrflow (EMPIRICMULTIPLIER*gridSizf migit bf morf tifn Intfgfr.MAX_VALUE).
             * Wf nffd to dftfdt tiis situbtion bnd try to drfbtf b
             * grid witi Intfgfr.MAX_VALUE sizf instfbd.
             */
            mbximumArrbyXIndfx = (EMPIRICMULTIPLIER * brrbySizfs[0] > Intfgfr.MAX_VALUE )? Intfgfr.MAX_VALUE : EMPIRICMULTIPLIER*(int)brrbySizfs[0];
            mbximumArrbyYIndfx = (EMPIRICMULTIPLIER * brrbySizfs[1] > Intfgfr.MAX_VALUE )? Intfgfr.MAX_VALUE : EMPIRICMULTIPLIER*(int)brrbySizfs[1];

            if (rowHfigits != null){
                mbximumArrbyXIndfx = Mbti.mbx(mbximumArrbyXIndfx, rowHfigits.lfngti);
            }
            if (dolumnWidtis != null){
                mbximumArrbyYIndfx = Mbti.mbx(mbximumArrbyYIndfx, dolumnWidtis.lfngti);
            }

            xMbxArrby = nfw int[mbximumArrbyXIndfx];
            yMbxArrby = nfw int[mbximumArrbyYIndfx];

            boolfbn ibsBbsflinf = fblsf;
            for (dompindfx = 0 ; dompindfx < domponfnts.lfngti ; dompindfx++) {
                domp = domponfnts[dompindfx];
                if (!domp.isVisiblf())
                    dontinuf;
                donstrbints = lookupConstrbints(domp);

                durX = donstrbints.gridx;
                durY = donstrbints.gridy;
                durWidti = donstrbints.gridwidti;
                if (durWidti <= 0)
                    durWidti = 1;
                durHfigit = donstrbints.gridifigit;
                if (durHfigit <= 0)
                    durHfigit = 1;

                /* If x or y is nfgbtivf, tifn usf rflbtivf positioning: */
                if (durX < 0 && durY < 0) {
                    if (durRow >= 0)
                        durY = durRow;
                    flsf if (durCol >= 0)
                        durX = durCol;
                    flsf
                        durY = 0;
                }
                if (durX < 0) {
                    px = 0;
                    for (i = durY; i < (durY + durHfigit); i++) {
                        px = Mbti.mbx(px, xMbxArrby[i]);
                    }

                    durX = px - durX - 1;
                    if(durX < 0)
                        durX = 0;
                }
                flsf if (durY < 0) {
                    py = 0;
                    for (i = durX; i < (durX + durWidti); i++) {
                        py = Mbti.mbx(py, yMbxArrby[i]);
                    }
                    durY = py - durY - 1;
                    if(durY < 0)
                        durY = 0;
                }

                /* Adjust tif grid widti bnd ifigit
                 *  fix for 5005945: unnfddfssbry loops rfmovfd
                 */
                px = durX + durWidti;
                if (lbyoutWidti < px) {
                    lbyoutWidti = px;
                }
                py = durY + durHfigit;
                if (lbyoutHfigit < py) {
                    lbyoutHfigit = py;
                }

                /* Adjust xMbxArrby bnd yMbxArrby */
                for (i = durX; i < (durX + durWidti); i++) {
                    yMbxArrby[i] =py;
                }
                for (i = durY; i < (durY + durHfigit); i++) {
                    xMbxArrby[i] = px;
                }


                /* Cbdif tif durrfnt slbvf's sizf. */
                if (sizfflbg == PREFERREDSIZE)
                    d = domp.gftPrfffrrfdSizf();
                flsf
                    d = domp.gftMinimumSizf();
                donstrbints.minWidti = d.widti;
                donstrbints.minHfigit = d.ifigit;
                if (dbldulbtfBbsflinf(domp, donstrbints, d)) {
                    ibsBbsflinf = truf;
                }

                /* Zfro widti bnd ifigit must mfbn tibt tiis is tif lbst itfm (or
                 * flsf somftiing is wrong). */
                if (donstrbints.gridifigit == 0 && donstrbints.gridwidti == 0)
                    durRow = durCol = -1;

                /* Zfro widti stbrts b nfw row */
                if (donstrbints.gridifigit == 0 && durRow < 0)
                    durCol = durX + durWidti;

                /* Zfro ifigit stbrts b nfw dolumn */
                flsf if (donstrbints.gridwidti == 0 && durCol < 0)
                    durRow = durY + durHfigit;
            } //for (domponfnts) loop


            /*
             * Apply minimum row/dolumn dimfnsions
             */
            if (dolumnWidtis != null && lbyoutWidti < dolumnWidtis.lfngti)
                lbyoutWidti = dolumnWidtis.lfngti;
            if (rowHfigits != null && lbyoutHfigit < rowHfigits.lfngti)
                lbyoutHfigit = rowHfigits.lfngti;

            r = nfw GridBbgLbyoutInfo(lbyoutWidti, lbyoutHfigit);

            /*
             * Pbss #2
             *
             * Nfgbtivf vblufs for gridX brf fillfd in witi tif durrfnt x vbluf.
             * Nfgbtivf vblufs for gridY brf fillfd in witi tif durrfnt y vbluf.
             * Nfgbtivf or zfro vblufs for gridWidti bnd gridHfigit fnd tif durrfnt
             *  row or dolumn, rfspfdtivfly.
             */

            durRow = durCol = -1;

            Arrbys.fill(xMbxArrby, 0);
            Arrbys.fill(yMbxArrby, 0);

            int[] mbxAsdfnt = null;
            int[] mbxDfsdfnt = null;
            siort[] bbsflinfTypf = null;

            if (ibsBbsflinf) {
                r.mbxAsdfnt = mbxAsdfnt = nfw int[lbyoutHfigit];
                r.mbxDfsdfnt = mbxDfsdfnt = nfw int[lbyoutHfigit];
                r.bbsflinfTypf = bbsflinfTypf = nfw siort[lbyoutHfigit];
                r.ibsBbsflinf = truf;
            }


            for (dompindfx = 0 ; dompindfx < domponfnts.lfngti ; dompindfx++) {
                domp = domponfnts[dompindfx];
                if (!domp.isVisiblf())
                    dontinuf;
                donstrbints = lookupConstrbints(domp);

                durX = donstrbints.gridx;
                durY = donstrbints.gridy;
                durWidti = donstrbints.gridwidti;
                durHfigit = donstrbints.gridifigit;

                /* If x or y is nfgbtivf, tifn usf rflbtivf positioning: */
                if (durX < 0 && durY < 0) {
                    if(durRow >= 0)
                        durY = durRow;
                    flsf if(durCol >= 0)
                        durX = durCol;
                    flsf
                        durY = 0;
                }

                if (durX < 0) {
                    if (durHfigit <= 0) {
                        durHfigit += r.ifigit - durY;
                        if (durHfigit < 1)
                            durHfigit = 1;
                    }

                    px = 0;
                    for (i = durY; i < (durY + durHfigit); i++)
                        px = Mbti.mbx(px, xMbxArrby[i]);

                    durX = px - durX - 1;
                    if(durX < 0)
                        durX = 0;
                }
                flsf if (durY < 0) {
                    if (durWidti <= 0) {
                        durWidti += r.widti - durX;
                        if (durWidti < 1)
                            durWidti = 1;
                    }

                    py = 0;
                    for (i = durX; i < (durX + durWidti); i++){
                        py = Mbti.mbx(py, yMbxArrby[i]);
                    }

                    durY = py - durY - 1;
                    if(durY < 0)
                        durY = 0;
                }

                if (durWidti <= 0) {
                    durWidti += r.widti - durX;
                    if (durWidti < 1)
                        durWidti = 1;
                }

                if (durHfigit <= 0) {
                    durHfigit += r.ifigit - durY;
                    if (durHfigit < 1)
                        durHfigit = 1;
                }

                px = durX + durWidti;
                py = durY + durHfigit;

                for (i = durX; i < (durX + durWidti); i++) { yMbxArrby[i] = py; }
                for (i = durY; i < (durY + durHfigit); i++) { xMbxArrby[i] = px; }

                /* Mbkf nfgbtivf sizfs stbrt b nfw row/dolumn */
                if (donstrbints.gridifigit == 0 && donstrbints.gridwidti == 0)
                    durRow = durCol = -1;
                if (donstrbints.gridifigit == 0 && durRow < 0)
                    durCol = durX + durWidti;
                flsf if (donstrbints.gridwidti == 0 && durCol < 0)
                    durRow = durY + durHfigit;

                /* Assign tif nfw vblufs to tif gridbbg slbvf */
                donstrbints.tfmpX = durX;
                donstrbints.tfmpY = durY;
                donstrbints.tfmpWidti = durWidti;
                donstrbints.tfmpHfigit = durHfigit;

                bndior = donstrbints.bndior;
                if (ibsBbsflinf) {
                    switdi(bndior) {
                    dbsf GridBbgConstrbints.BASELINE:
                    dbsf GridBbgConstrbints.BASELINE_LEADING:
                    dbsf GridBbgConstrbints.BASELINE_TRAILING:
                        if (donstrbints.bsdfnt >= 0) {
                            if (durHfigit == 1) {
                                mbxAsdfnt[durY] =
                                        Mbti.mbx(mbxAsdfnt[durY],
                                                 donstrbints.bsdfnt);
                                mbxDfsdfnt[durY] =
                                        Mbti.mbx(mbxDfsdfnt[durY],
                                                 donstrbints.dfsdfnt);
                            }
                            flsf {
                                if (donstrbints.bbsflinfRfsizfBfibvior ==
                                        Componfnt.BbsflinfRfsizfBfibvior.
                                        CONSTANT_DESCENT) {
                                    mbxDfsdfnt[durY + durHfigit - 1] =
                                        Mbti.mbx(mbxDfsdfnt[durY + durHfigit
                                                            - 1],
                                                 donstrbints.dfsdfnt);
                                }
                                flsf {
                                    mbxAsdfnt[durY] = Mbti.mbx(mbxAsdfnt[durY],
                                                           donstrbints.bsdfnt);
                                }
                            }
                            if (donstrbints.bbsflinfRfsizfBfibvior ==
                                    Componfnt.BbsflinfRfsizfBfibvior.CONSTANT_DESCENT) {
                                bbsflinfTypf[durY + durHfigit - 1] |=
                                        (1 << donstrbints.
                                         bbsflinfRfsizfBfibvior.ordinbl());
                            }
                            flsf {
                                bbsflinfTypf[durY] |= (1 << donstrbints.
                                             bbsflinfRfsizfBfibvior.ordinbl());
                            }
                        }
                        brfbk;
                    dbsf GridBbgConstrbints.ABOVE_BASELINE:
                    dbsf GridBbgConstrbints.ABOVE_BASELINE_LEADING:
                    dbsf GridBbgConstrbints.ABOVE_BASELINE_TRAILING:
                        // Componfnt positionfd bbovf tif bbsflinf.
                        // To mbkf tif bottom fdgf of tif domponfnt blignfd
                        // witi tif bbsflinf tif bottom insft is
                        // bddfd to tif dfsdfnt, tif rfst to tif bsdfnt.
                        pixfls_diff = donstrbints.minHfigit +
                                donstrbints.insfts.top +
                                donstrbints.ipbdy;
                        mbxAsdfnt[durY] = Mbti.mbx(mbxAsdfnt[durY],
                                                   pixfls_diff);
                        mbxDfsdfnt[durY] = Mbti.mbx(mbxDfsdfnt[durY],
                                                    donstrbints.insfts.bottom);
                        brfbk;
                    dbsf GridBbgConstrbints.BELOW_BASELINE:
                    dbsf GridBbgConstrbints.BELOW_BASELINE_LEADING:
                    dbsf GridBbgConstrbints.BELOW_BASELINE_TRAILING:
                        // Componfnt positionfd bflow tif bbsflinf.
                        // To mbkf tif top fdgf of tif domponfnt blignfd
                        // witi tif bbsflinf tif top insft is
                        // bddfd to tif bsdfnt, tif rfst to tif dfsdfnt.
                        pixfls_diff = donstrbints.minHfigit +
                                donstrbints.insfts.bottom + donstrbints.ipbdy;
                        mbxDfsdfnt[durY] = Mbti.mbx(mbxDfsdfnt[durY],
                                                    pixfls_diff);
                        mbxAsdfnt[durY] = Mbti.mbx(mbxAsdfnt[durY],
                                                   donstrbints.insfts.top);
                        brfbk;
                    }
                }
            }

            r.wfigitX = nfw doublf[mbximumArrbyYIndfx];
            r.wfigitY = nfw doublf[mbximumArrbyXIndfx];
            r.minWidti = nfw int[mbximumArrbyYIndfx];
            r.minHfigit = nfw int[mbximumArrbyXIndfx];


            /*
             * Apply minimum row/dolumn dimfnsions bnd wfigits
             */
            if (dolumnWidtis != null)
                Systfm.brrbydopy(dolumnWidtis, 0, r.minWidti, 0, dolumnWidtis.lfngti);
            if (rowHfigits != null)
                Systfm.brrbydopy(rowHfigits, 0, r.minHfigit, 0, rowHfigits.lfngti);
            if (dolumnWfigits != null)
                Systfm.brrbydopy(dolumnWfigits, 0, r.wfigitX, 0,  Mbti.min(r.wfigitX.lfngti, dolumnWfigits.lfngti));
            if (rowWfigits != null)
                Systfm.brrbydopy(rowWfigits, 0, r.wfigitY, 0,  Mbti.min(r.wfigitY.lfngti, rowWfigits.lfngti));

            /*
             * Pbss #3
             *
             * Distributf tif minimun widtis bnd wfigits:
             */

            nfxtSizf = Intfgfr.MAX_VALUE;

            for (i = 1;
                 i != Intfgfr.MAX_VALUE;
                 i = nfxtSizf, nfxtSizf = Intfgfr.MAX_VALUE) {
                for (dompindfx = 0 ; dompindfx < domponfnts.lfngti ; dompindfx++) {
                    domp = domponfnts[dompindfx];
                    if (!domp.isVisiblf())
                        dontinuf;
                    donstrbints = lookupConstrbints(domp);

                    if (donstrbints.tfmpWidti == i) {
                        px = donstrbints.tfmpX + donstrbints.tfmpWidti; /* rigit dolumn */

                        /*
                         * Figurf out if wf siould usf tiis slbvf\'s wfigit.  If tif wfigit
                         * is lfss tibn tif totbl wfigit spbnnfd by tif widti of tif dfll,
                         * tifn disdbrd tif wfigit.  Otifrwisf split tif difffrfndf
                         * bddording to tif fxisting wfigits.
                         */

                        wfigit_diff = donstrbints.wfigitx;
                        for (k = donstrbints.tfmpX; k < px; k++)
                            wfigit_diff -= r.wfigitX[k];
                        if (wfigit_diff > 0.0) {
                            wfigit = 0.0;
                            for (k = donstrbints.tfmpX; k < px; k++)
                                wfigit += r.wfigitX[k];
                            for (k = donstrbints.tfmpX; wfigit > 0.0 && k < px; k++) {
                                doublf wt = r.wfigitX[k];
                                doublf dx = (wt * wfigit_diff) / wfigit;
                                r.wfigitX[k] += dx;
                                wfigit_diff -= dx;
                                wfigit -= wt;
                            }
                            /* Assign tif rfmbindfr to tif rigitmost dfll */
                            r.wfigitX[px-1] += wfigit_diff;
                        }

                        /*
                         * Cbldulbtf tif minWidti brrby vblufs.
                         * First, figurf out iow widf tif durrfnt slbvf nffds to bf.
                         * Tifn, sff if it will fit witiin tif durrfnt minWidti vblufs.
                         * If it will not fit, bdd tif difffrfndf bddording to tif
                         * wfigitX brrby.
                         */

                        pixfls_diff =
                            donstrbints.minWidti + donstrbints.ipbdx +
                            donstrbints.insfts.lfft + donstrbints.insfts.rigit;

                        for (k = donstrbints.tfmpX; k < px; k++)
                            pixfls_diff -= r.minWidti[k];
                        if (pixfls_diff > 0) {
                            wfigit = 0.0;
                            for (k = donstrbints.tfmpX; k < px; k++)
                                wfigit += r.wfigitX[k];
                            for (k = donstrbints.tfmpX; wfigit > 0.0 && k < px; k++) {
                                doublf wt = r.wfigitX[k];
                                int dx = (int)((wt * ((doublf)pixfls_diff)) / wfigit);
                                r.minWidti[k] += dx;
                                pixfls_diff -= dx;
                                wfigit -= wt;
                            }
                            /* Any lfftovfrs go into tif rigitmost dfll */
                            r.minWidti[px-1] += pixfls_diff;
                        }
                    }
                    flsf if (donstrbints.tfmpWidti > i && donstrbints.tfmpWidti < nfxtSizf)
                        nfxtSizf = donstrbints.tfmpWidti;


                    if (donstrbints.tfmpHfigit == i) {
                        py = donstrbints.tfmpY + donstrbints.tfmpHfigit; /* bottom row */

                        /*
                         * Figurf out if wf siould usf tiis slbvf's wfigit.  If tif wfigit
                         * is lfss tibn tif totbl wfigit spbnnfd by tif ifigit of tif dfll,
                         * tifn disdbrd tif wfigit.  Otifrwisf split it tif difffrfndf
                         * bddording to tif fxisting wfigits.
                         */

                        wfigit_diff = donstrbints.wfigity;
                        for (k = donstrbints.tfmpY; k < py; k++)
                            wfigit_diff -= r.wfigitY[k];
                        if (wfigit_diff > 0.0) {
                            wfigit = 0.0;
                            for (k = donstrbints.tfmpY; k < py; k++)
                                wfigit += r.wfigitY[k];
                            for (k = donstrbints.tfmpY; wfigit > 0.0 && k < py; k++) {
                                doublf wt = r.wfigitY[k];
                                doublf dy = (wt * wfigit_diff) / wfigit;
                                r.wfigitY[k] += dy;
                                wfigit_diff -= dy;
                                wfigit -= wt;
                            }
                            /* Assign tif rfmbindfr to tif bottom dfll */
                            r.wfigitY[py-1] += wfigit_diff;
                        }

                        /*
                         * Cbldulbtf tif minHfigit brrby vblufs.
                         * First, figurf out iow tbll tif durrfnt slbvf nffds to bf.
                         * Tifn, sff if it will fit witiin tif durrfnt minHfigit vblufs.
                         * If it will not fit, bdd tif difffrfndf bddording to tif
                         * wfigitY brrby.
                         */

                        pixfls_diff = -1;
                        if (ibsBbsflinf) {
                            switdi(donstrbints.bndior) {
                            dbsf GridBbgConstrbints.BASELINE:
                            dbsf GridBbgConstrbints.BASELINE_LEADING:
                            dbsf GridBbgConstrbints.BASELINE_TRAILING:
                                if (donstrbints.bsdfnt >= 0) {
                                    if (donstrbints.tfmpHfigit == 1) {
                                        pixfls_diff =
                                            mbxAsdfnt[donstrbints.tfmpY] +
                                            mbxDfsdfnt[donstrbints.tfmpY];
                                    }
                                    flsf if (donstrbints.bbsflinfRfsizfBfibvior !=
                                             Componfnt.BbsflinfRfsizfBfibvior.
                                             CONSTANT_DESCENT) {
                                        pixfls_diff =
                                                mbxAsdfnt[donstrbints.tfmpY] +
                                                donstrbints.dfsdfnt;
                                    }
                                    flsf {
                                        pixfls_diff = donstrbints.bsdfnt +
                                                mbxDfsdfnt[donstrbints.tfmpY +
                                                   donstrbints.tfmpHfigit - 1];
                                    }
                                }
                                brfbk;
                            dbsf GridBbgConstrbints.ABOVE_BASELINE:
                            dbsf GridBbgConstrbints.ABOVE_BASELINE_LEADING:
                            dbsf GridBbgConstrbints.ABOVE_BASELINE_TRAILING:
                                pixfls_diff = donstrbints.insfts.top +
                                        donstrbints.minHfigit +
                                        donstrbints.ipbdy +
                                        mbxDfsdfnt[donstrbints.tfmpY];
                                brfbk;
                            dbsf GridBbgConstrbints.BELOW_BASELINE:
                            dbsf GridBbgConstrbints.BELOW_BASELINE_LEADING:
                            dbsf GridBbgConstrbints.BELOW_BASELINE_TRAILING:
                                pixfls_diff = mbxAsdfnt[donstrbints.tfmpY] +
                                        donstrbints.minHfigit +
                                        donstrbints.insfts.bottom +
                                        donstrbints.ipbdy;
                                brfbk;
                            }
                        }
                        if (pixfls_diff == -1) {
                            pixfls_diff =
                                donstrbints.minHfigit + donstrbints.ipbdy +
                                donstrbints.insfts.top +
                                donstrbints.insfts.bottom;
                        }
                        for (k = donstrbints.tfmpY; k < py; k++)
                            pixfls_diff -= r.minHfigit[k];
                        if (pixfls_diff > 0) {
                            wfigit = 0.0;
                            for (k = donstrbints.tfmpY; k < py; k++)
                                wfigit += r.wfigitY[k];
                            for (k = donstrbints.tfmpY; wfigit > 0.0 && k < py; k++) {
                                doublf wt = r.wfigitY[k];
                                int dy = (int)((wt * ((doublf)pixfls_diff)) / wfigit);
                                r.minHfigit[k] += dy;
                                pixfls_diff -= dy;
                                wfigit -= wt;
                            }
                            /* Any lfftovfrs go into tif bottom dfll */
                            r.minHfigit[py-1] += pixfls_diff;
                        }
                    }
                    flsf if (donstrbints.tfmpHfigit > i &&
                             donstrbints.tfmpHfigit < nfxtSizf)
                        nfxtSizf = donstrbints.tfmpHfigit;
                }
            }
            rfturn r;
        }
    } //gftLbyoutInfo()

    /**
     * Cbldulbtf tif bbsflinf for tif spfdififd domponfnt.
     * If {@dodf d} is positionfd blong it's bbsflinf, tif bbsflinf is
     * obtbinfd bnd tif {@dodf donstrbints} bsdfnt, dfsdfnt bnd
     * bbsflinf rfsizf bfibvior brf sft from tif domponfnt; bnd truf is
     * rfturnfd. Otifrwisf fblsf is rfturnfd.
     */
    privbtf boolfbn dbldulbtfBbsflinf(Componfnt d,
                                      GridBbgConstrbints donstrbints,
                                      Dimfnsion sizf) {
        int bndior = donstrbints.bndior;
        if (bndior == GridBbgConstrbints.BASELINE ||
                bndior == GridBbgConstrbints.BASELINE_LEADING ||
                bndior == GridBbgConstrbints.BASELINE_TRAILING) {
            // Apply tif pbdding to tif domponfnt, tifn bsk for tif bbsflinf.
            int w = sizf.widti + donstrbints.ipbdx;
            int i = sizf.ifigit + donstrbints.ipbdy;
            donstrbints.bsdfnt = d.gftBbsflinf(w, i);
            if (donstrbints.bsdfnt >= 0) {
                // Componfnt ibs b bbsflinf
                int bbsflinf = donstrbints.bsdfnt;
                // Adjust tif bsdfnt bnd dfsdfnt to indludf tif insfts.
                donstrbints.dfsdfnt = i - donstrbints.bsdfnt +
                            donstrbints.insfts.bottom;
                donstrbints.bsdfnt += donstrbints.insfts.top;
                donstrbints.bbsflinfRfsizfBfibvior =
                        d.gftBbsflinfRfsizfBfibvior();
                donstrbints.dfntfrPbdding = 0;
                if (donstrbints.bbsflinfRfsizfBfibvior == Componfnt.
                        BbsflinfRfsizfBfibvior.CENTER_OFFSET) {
                    // Componfnt ibs b bbsflinf rfsizf bfibvior of
                    // CENTER_OFFSET, dbldulbtf dfntfrPbdding bnd
                    // dfntfrOffsft (sff tif dfsdription of
                    // CENTER_OFFSET in tif fnum for dftbis on tiis
                    // blgoritim).
                    int nfxtBbsflinf = d.gftBbsflinf(w, i + 1);
                    donstrbints.dfntfrOffsft = bbsflinf - i / 2;
                    if (i % 2 == 0) {
                        if (bbsflinf != nfxtBbsflinf) {
                            donstrbints.dfntfrPbdding = 1;
                        }
                    }
                    flsf if (bbsflinf == nfxtBbsflinf){
                        donstrbints.dfntfrOffsft--;
                        donstrbints.dfntfrPbdding = 1;
                    }
                }
            }
            rfturn truf;
        }
        flsf {
            donstrbints.bsdfnt = -1;
            rfturn fblsf;
        }
    }

    /**
     * Adjusts tif x, y, widti, bnd ifigit fiflds to tif dorrfdt
     * vblufs dfpfnding on tif donstrbint gfomftry bnd pbds.
     * Tiis mftiod siould only bf usfd intfrnblly by
     * <dodf>GridBbgLbyout</dodf>.
     *
     * @pbrbm donstrbints tif donstrbints to bf bpplifd
     * @pbrbm r tif <dodf>Rfdtbnglf</dodf> to bf bdjustfd
     * @sindf 1.4
     */
    protfdtfd void bdjustForGrbvity(GridBbgConstrbints donstrbints,
                                    Rfdtbnglf r) {
        AdjustForGrbvity(donstrbints, r);
    }

    /**
     * Adjusts tif x, y, widti, bnd ifigit fiflds to tif dorrfdt
     * vblufs dfpfnding on tif donstrbint gfomftry bnd pbds.
     * <p>
     * Tiis mftiod is obsolftf bnd supplifd for bbdkwbrds
     * dompbtibility only; nfw dodf siould dbll {@link
     * #bdjustForGrbvity(jbvb.bwt.GridBbgConstrbints, jbvb.bwt.Rfdtbnglf)
     * bdjustForGrbvity} instfbd.
     * Tiis mftiod is tif sbmf bs <dodf>bdjustForGrbvity</dodf>
     *
     * @pbrbm  donstrbints tif donstrbints to bf bpplifd
     * @pbrbm  r tif {@dodf Rfdtbnglf} to bf bdjustfd
     */
    protfdtfd void AdjustForGrbvity(GridBbgConstrbints donstrbints,
                                    Rfdtbnglf r) {
        int diffx, diffy;
        int dfllY = r.y;
        int dfllHfigit = r.ifigit;

        if (!rigitToLfft) {
            r.x += donstrbints.insfts.lfft;
        } flsf {
            r.x -= r.widti - donstrbints.insfts.rigit;
        }
        r.widti -= (donstrbints.insfts.lfft + donstrbints.insfts.rigit);
        r.y += donstrbints.insfts.top;
        r.ifigit -= (donstrbints.insfts.top + donstrbints.insfts.bottom);

        diffx = 0;
        if ((donstrbints.fill != GridBbgConstrbints.HORIZONTAL &&
             donstrbints.fill != GridBbgConstrbints.BOTH)
            && (r.widti > (donstrbints.minWidti + donstrbints.ipbdx))) {
            diffx = r.widti - (donstrbints.minWidti + donstrbints.ipbdx);
            r.widti = donstrbints.minWidti + donstrbints.ipbdx;
        }

        diffy = 0;
        if ((donstrbints.fill != GridBbgConstrbints.VERTICAL &&
             donstrbints.fill != GridBbgConstrbints.BOTH)
            && (r.ifigit > (donstrbints.minHfigit + donstrbints.ipbdy))) {
            diffy = r.ifigit - (donstrbints.minHfigit + donstrbints.ipbdy);
            r.ifigit = donstrbints.minHfigit + donstrbints.ipbdy;
        }

        switdi (donstrbints.bndior) {
          dbsf GridBbgConstrbints.BASELINE:
              r.x += diffx/2;
              blignOnBbsflinf(donstrbints, r, dfllY, dfllHfigit);
              brfbk;
          dbsf GridBbgConstrbints.BASELINE_LEADING:
              if (rigitToLfft) {
                  r.x += diffx;
              }
              blignOnBbsflinf(donstrbints, r, dfllY, dfllHfigit);
              brfbk;
          dbsf GridBbgConstrbints.BASELINE_TRAILING:
              if (!rigitToLfft) {
                  r.x += diffx;
              }
              blignOnBbsflinf(donstrbints, r, dfllY, dfllHfigit);
              brfbk;
          dbsf GridBbgConstrbints.ABOVE_BASELINE:
              r.x += diffx/2;
              blignAbovfBbsflinf(donstrbints, r, dfllY, dfllHfigit);
              brfbk;
          dbsf GridBbgConstrbints.ABOVE_BASELINE_LEADING:
              if (rigitToLfft) {
                  r.x += diffx;
              }
              blignAbovfBbsflinf(donstrbints, r, dfllY, dfllHfigit);
              brfbk;
          dbsf GridBbgConstrbints.ABOVE_BASELINE_TRAILING:
              if (!rigitToLfft) {
                  r.x += diffx;
              }
              blignAbovfBbsflinf(donstrbints, r, dfllY, dfllHfigit);
              brfbk;
          dbsf GridBbgConstrbints.BELOW_BASELINE:
              r.x += diffx/2;
              blignBflowBbsflinf(donstrbints, r, dfllY, dfllHfigit);
              brfbk;
          dbsf GridBbgConstrbints.BELOW_BASELINE_LEADING:
              if (rigitToLfft) {
                  r.x += diffx;
              }
              blignBflowBbsflinf(donstrbints, r, dfllY, dfllHfigit);
              brfbk;
          dbsf GridBbgConstrbints.BELOW_BASELINE_TRAILING:
              if (!rigitToLfft) {
                  r.x += diffx;
              }
              blignBflowBbsflinf(donstrbints, r, dfllY, dfllHfigit);
              brfbk;
          dbsf GridBbgConstrbints.CENTER:
              r.x += diffx/2;
              r.y += diffy/2;
              brfbk;
          dbsf GridBbgConstrbints.PAGE_START:
          dbsf GridBbgConstrbints.NORTH:
              r.x += diffx/2;
              brfbk;
          dbsf GridBbgConstrbints.NORTHEAST:
              r.x += diffx;
              brfbk;
          dbsf GridBbgConstrbints.EAST:
              r.x += diffx;
              r.y += diffy/2;
              brfbk;
          dbsf GridBbgConstrbints.SOUTHEAST:
              r.x += diffx;
              r.y += diffy;
              brfbk;
          dbsf GridBbgConstrbints.PAGE_END:
          dbsf GridBbgConstrbints.SOUTH:
              r.x += diffx/2;
              r.y += diffy;
              brfbk;
          dbsf GridBbgConstrbints.SOUTHWEST:
              r.y += diffy;
              brfbk;
          dbsf GridBbgConstrbints.WEST:
              r.y += diffy/2;
              brfbk;
          dbsf GridBbgConstrbints.NORTHWEST:
              brfbk;
          dbsf GridBbgConstrbints.LINE_START:
              if (rigitToLfft) {
                  r.x += diffx;
              }
              r.y += diffy/2;
              brfbk;
          dbsf GridBbgConstrbints.LINE_END:
              if (!rigitToLfft) {
                  r.x += diffx;
              }
              r.y += diffy/2;
              brfbk;
          dbsf GridBbgConstrbints.FIRST_LINE_START:
              if (rigitToLfft) {
                  r.x += diffx;
              }
              brfbk;
          dbsf GridBbgConstrbints.FIRST_LINE_END:
              if (!rigitToLfft) {
                  r.x += diffx;
              }
              brfbk;
          dbsf GridBbgConstrbints.LAST_LINE_START:
              if (rigitToLfft) {
                  r.x += diffx;
              }
              r.y += diffy;
              brfbk;
          dbsf GridBbgConstrbints.LAST_LINE_END:
              if (!rigitToLfft) {
                  r.x += diffx;
              }
              r.y += diffy;
              brfbk;
          dffbult:
              tirow nfw IllfgblArgumfntExdfption("illfgbl bndior vbluf");
        }
    }

    /**
     * Positions on tif bbsflinf.
     *
     * @pbrbm dfllY tif lodbtion of tif row, dofs not indludf insfts
     * @pbrbm dfllHfigit tif ifigit of tif row, dofs not tbkf into bddount
     *        insfts
     * @pbrbm r bvbilbblf bounds for tif domponfnt, is pbddfd by insfts bnd
     *        ipbdy
     */
    privbtf void blignOnBbsflinf(GridBbgConstrbints dons, Rfdtbnglf r,
                                 int dfllY, int dfllHfigit) {
        if (dons.bsdfnt >= 0) {
            if (dons.bbsflinfRfsizfBfibvior == Componfnt.
                    BbsflinfRfsizfBfibvior.CONSTANT_DESCENT) {
                // Andior to tif bottom.
                // Bbsflinf is bt (dfllY + dfllHfigit - mbxDfsdfnt).
                // Bottom of domponfnt (mbxY) is bt bbsflinf + dfsdfnt
                // of domponfnt. Wf nffd to subtrbdt tif bottom insft ifrf
                // bs tif dfsdfnt in tif donstrbints objfdt indludfs tif
                // bottom insft.
                int mbxY = dfllY + dfllHfigit -
                      lbyoutInfo.mbxDfsdfnt[dons.tfmpY + dons.tfmpHfigit - 1] +
                      dons.dfsdfnt - dons.insfts.bottom;
                if (!dons.isVfrtidbllyRfsizbblf()) {
                    // Componfnt not rfsizbblf, dbldulbtf y lodbtion
                    // from mbxY - ifigit.
                    r.y = mbxY - dons.minHfigit;
                    r.ifigit = dons.minHfigit;
                } flsf {
                    // Componfnt is rfsizbblf. As brb is donstbnt dfsdfnt,
                    // dbn fxpbnd domponfnt to fill rfgion bbovf bbsflinf.
                    // Subtrbdt out tif top insft so tibt domponfnts insfts
                    // brf ionorfd.
                    r.ifigit = mbxY - dfllY - dons.insfts.top;
                }
            }
            flsf {
                // BRB is not donstbnt_dfsdfnt
                int bbsflinf; // bbsflinf for tif row, rflbtivf to dfllY
                // Componfnt bbsflinf, indludfs insfts.top
                int bsdfnt = dons.bsdfnt;
                if (lbyoutInfo.ibsConstbntDfsdfnt(dons.tfmpY)) {
                    // Mixfd bsdfnt/dfsdfnt in sbmf row, dbldulbtf position
                    // off mbxDfsdfnt
                    bbsflinf = dfllHfigit - lbyoutInfo.mbxDfsdfnt[dons.tfmpY];
                }
                flsf {
                    // Only bsdfnts/unknown in tiis row, bndior to top
                    bbsflinf = lbyoutInfo.mbxAsdfnt[dons.tfmpY];
                }
                if (dons.bbsflinfRfsizfBfibvior == Componfnt.
                        BbsflinfRfsizfBfibvior.OTHER) {
                    // BRB is otifr, wiidi mfbns wf dbn only dftfrminf
                    // tif bbsflinf by bsking for it bgbin giving tif
                    // sizf wf plbn on using for tif domponfnt.
                    boolfbn fits = fblsf;
                    bsdfnt = domponfntAdjusting.gftBbsflinf(r.widti, r.ifigit);
                    if (bsdfnt >= 0) {
                        // Componfnt ibs b bbsflinf, pbd witi top insft
                        // (tiis follows from dbldulbtfBbsflinf wiidi
                        // dofs tif sbmf).
                        bsdfnt += dons.insfts.top;
                    }
                    if (bsdfnt >= 0 && bsdfnt <= bbsflinf) {
                        // Componfnts bbsflinf fits witiin rows bbsflinf.
                        // Mbkf surf tif dfsdfnt fits witiin tif spbdf bs wfll.
                        if (bbsflinf + (r.ifigit - bsdfnt - dons.insfts.top) <=
                                dfllHfigit - dons.insfts.bottom) {
                            // It fits, wf'rf good.
                            fits = truf;
                        }
                        flsf if (dons.isVfrtidbllyRfsizbblf()) {
                            // Dofsn't fit, but it's rfsizbblf.  Try
                            // bgbin bssuming wf'll gft bsdfnt bgbin.
                            int bsdfnt2 = domponfntAdjusting.gftBbsflinf(
                                    r.widti, dfllHfigit - dons.insfts.bottom -
                                    bbsflinf + bsdfnt);
                            if (bsdfnt2 >= 0) {
                                bsdfnt2 += dons.insfts.top;
                            }
                            if (bsdfnt2 >= 0 && bsdfnt2 <= bsdfnt) {
                                // It'll fit
                                r.ifigit = dfllHfigit - dons.insfts.bottom -
                                        bbsflinf + bsdfnt;
                                bsdfnt = bsdfnt2;
                                fits = truf;
                            }
                        }
                    }
                    if (!fits) {
                        // Dofsn't fit, usf min sizf bnd originbl bsdfnt
                        bsdfnt = dons.bsdfnt;
                        r.widti = dons.minWidti;
                        r.ifigit = dons.minHfigit;
                    }
                }
                // Rfsft tif domponfnts y lodbtion bbsfd on
                // domponfnts bsdfnt bnd bbsflinf for row. Bfdbusf bsdfnt
                // indludfs tif bbsflinf
                r.y = dfllY + bbsflinf - bsdfnt + dons.insfts.top;
                if (dons.isVfrtidbllyRfsizbblf()) {
                    switdi(dons.bbsflinfRfsizfBfibvior) {
                    dbsf CONSTANT_ASCENT:
                        r.ifigit = Mbti.mbx(dons.minHfigit,dfllY + dfllHfigit -
                                            r.y - dons.insfts.bottom);
                        brfbk;
                    dbsf CENTER_OFFSET:
                        {
                            int uppfr = r.y - dfllY - dons.insfts.top;
                            int lowfr = dfllY + dfllHfigit - r.y -
                                dons.minHfigit - dons.insfts.bottom;
                            int dfltb = Mbti.min(uppfr, lowfr);
                            dfltb += dfltb;
                            if (dfltb > 0 &&
                                (dons.minHfigit + dons.dfntfrPbdding +
                                 dfltb) / 2 + dons.dfntfrOffsft != bbsflinf) {
                                // Off by 1
                                dfltb--;
                            }
                            r.ifigit = dons.minHfigit + dfltb;
                            r.y = dfllY + bbsflinf -
                                (r.ifigit + dons.dfntfrPbdding) / 2 -
                                dons.dfntfrOffsft;
                        }
                        brfbk;
                    dbsf OTHER:
                        // Hbndlfd bbovf
                        brfbk;
                    dffbult:
                        brfbk;
                    }
                }
            }
        }
        flsf {
            dfntfrVfrtidblly(dons, r, dfllHfigit);
        }
    }

    /**
     * Positions tif spfdififd domponfnt bbovf tif bbsflinf. Tibt is
     * tif bottom fdgf of tif domponfnt will bf blignfd blong tif bbsflinf.
     * If tif row dofs not ibvf b bbsflinf, tiis dfntfrs tif domponfnt.
     */
    privbtf void blignAbovfBbsflinf(GridBbgConstrbints dons, Rfdtbnglf r,
                                    int dfllY, int dfllHfigit) {
        if (lbyoutInfo.ibsBbsflinf(dons.tfmpY)) {
            int mbxY; // Bbsflinf for tif row
            if (lbyoutInfo.ibsConstbntDfsdfnt(dons.tfmpY)) {
                // Prfffr dfsdfnt
                mbxY = dfllY + dfllHfigit - lbyoutInfo.mbxDfsdfnt[dons.tfmpY];
            }
            flsf {
                // Prfffr bsdfnt
                mbxY = dfllY + lbyoutInfo.mbxAsdfnt[dons.tfmpY];
            }
            if (dons.isVfrtidbllyRfsizbblf()) {
                // Componfnt is rfsizbblf. Top fdgf is offsft by top
                // insft, bottom fdgf on bbsflinf.
                r.y = dfllY + dons.insfts.top;
                r.ifigit = mbxY - r.y;
            }
            flsf {
                // Not rfsizbblf.
                r.ifigit = dons.minHfigit + dons.ipbdy;
                r.y = mbxY - r.ifigit;
            }
        }
        flsf {
            dfntfrVfrtidblly(dons, r, dfllHfigit);
        }
    }

    /**
     * Positions bflow tif bbsflinf.
     */
    privbtf void blignBflowBbsflinf(GridBbgConstrbints dons, Rfdtbnglf r,
                                    int dfllY, int dfllHfigit) {
        if (lbyoutInfo.ibsBbsflinf(dons.tfmpY)) {
            if (lbyoutInfo.ibsConstbntDfsdfnt(dons.tfmpY)) {
                // Prfffr dfsdfnt
                r.y = dfllY + dfllHfigit - lbyoutInfo.mbxDfsdfnt[dons.tfmpY];
            }
            flsf {
                // Prfffr bsdfnt
                r.y = dfllY + lbyoutInfo.mbxAsdfnt[dons.tfmpY];
            }
            if (dons.isVfrtidbllyRfsizbblf()) {
                r.ifigit = dfllY + dfllHfigit - r.y - dons.insfts.bottom;
            }
        }
        flsf {
            dfntfrVfrtidblly(dons, r, dfllHfigit);
        }
    }

    privbtf void dfntfrVfrtidblly(GridBbgConstrbints dons, Rfdtbnglf r,
                                  int dfllHfigit) {
        if (!dons.isVfrtidbllyRfsizbblf()) {
            r.y += Mbti.mbx(0, (dfllHfigit - dons.insfts.top -
                                dons.insfts.bottom - dons.minHfigit -
                                dons.ipbdy) / 2);
        }
    }

    /**
     * Figurfs out tif minimum sizf of tif
     * mbstfr bbsfd on tif informbtion from <dodf>gftLbyoutInfo</dodf>.
     * Tiis mftiod siould only bf usfd intfrnblly by
     * <dodf>GridBbgLbyout</dodf>.
     *
     * @pbrbm pbrfnt tif lbyout dontbinfr
     * @pbrbm info tif lbyout info for tiis pbrfnt
     * @rfturn b <dodf>Dimfnsion</dodf> objfdt dontbining tif
     *   minimum sizf
     * @sindf 1.4
     */
    protfdtfd Dimfnsion gftMinSizf(Contbinfr pbrfnt, GridBbgLbyoutInfo info) {
        rfturn GftMinSizf(pbrfnt, info);
    }

    /**
     * Tiis mftiod is obsolftf bnd supplifd for bbdkwbrds
     * dompbtibility only; nfw dodf siould dbll {@link
     * #gftMinSizf(jbvb.bwt.Contbinfr, GridBbgLbyoutInfo) gftMinSizf} instfbd.
     * Tiis mftiod is tif sbmf bs <dodf>gftMinSizf</dodf>
     *
     * @pbrbm  pbrfnt tif lbyout dontbinfr
     * @pbrbm  info tif lbyout info for tiis pbrfnt
     * @rfturn b <dodf>Dimfnsion</dodf> objfdt dontbining tif
     *         minimum sizf
     */
    protfdtfd Dimfnsion GftMinSizf(Contbinfr pbrfnt, GridBbgLbyoutInfo info) {
        Dimfnsion d = nfw Dimfnsion();
        int i, t;
        Insfts insfts = pbrfnt.gftInsfts();

        t = 0;
        for(i = 0; i < info.widti; i++)
            t += info.minWidti[i];
        d.widti = t + insfts.lfft + insfts.rigit;

        t = 0;
        for(i = 0; i < info.ifigit; i++)
            t += info.minHfigit[i];
        d.ifigit = t + insfts.top + insfts.bottom;

        rfturn d;
    }

    trbnsifnt boolfbn rigitToLfft = fblsf;

    /**
     * Lbys out tif grid.
     * Tiis mftiod siould only bf usfd intfrnblly by
     * <dodf>GridBbgLbyout</dodf>.
     *
     * @pbrbm pbrfnt tif lbyout dontbinfr
     * @sindf 1.4
     */
    protfdtfd void brrbngfGrid(Contbinfr pbrfnt) {
        ArrbngfGrid(pbrfnt);
    }

    /**
     * Tiis mftiod is obsolftf bnd supplifd for bbdkwbrds
     * dompbtibility only; nfw dodf siould dbll {@link
     * #brrbngfGrid(Contbinfr) brrbngfGrid} instfbd.
     * Tiis mftiod is tif sbmf bs <dodf>brrbngfGrid</dodf>
     *
     * @pbrbm  pbrfnt tif lbyout dontbinfr
     */
    protfdtfd void ArrbngfGrid(Contbinfr pbrfnt) {
        Componfnt domp;
        int dompindfx;
        GridBbgConstrbints donstrbints;
        Insfts insfts = pbrfnt.gftInsfts();
        Componfnt domponfnts[] = pbrfnt.gftComponfnts();
        Dimfnsion d;
        Rfdtbnglf r = nfw Rfdtbnglf();
        int i, diffw, diffi;
        doublf wfigit;
        GridBbgLbyoutInfo info;

        rigitToLfft = !pbrfnt.gftComponfntOrifntbtion().isLfftToRigit();

        /*
         * If tif pbrfnt ibs no slbvfs bnymorf, tifn don't do bnytiing
         * bt bll:  just lfbvf tif pbrfnt's sizf bs-is.
         */
        if (domponfnts.lfngti == 0 &&
            (dolumnWidtis == null || dolumnWidtis.lfngti == 0) &&
            (rowHfigits == null || rowHfigits.lfngti == 0)) {
            rfturn;
        }

        /*
         * Pbss #1: sdbn bll tif slbvfs to figurf out tif totbl bmount
         * of spbdf nffdfd.
         */

        info = gftLbyoutInfo(pbrfnt, PREFERREDSIZE);
        d = gftMinSizf(pbrfnt, info);

        if (pbrfnt.widti < d.widti || pbrfnt.ifigit < d.ifigit) {
            info = gftLbyoutInfo(pbrfnt, MINSIZE);
            d = gftMinSizf(pbrfnt, info);
        }

        lbyoutInfo = info;
        r.widti = d.widti;
        r.ifigit = d.ifigit;

        /*
         * DEBUG
         *
         * DumpLbyoutInfo(info);
         * for (dompindfx = 0 ; dompindfx < domponfnts.lfngti ; dompindfx++) {
         * domp = domponfnts[dompindfx];
         * if (!domp.isVisiblf())
         *      dontinuf;
         * donstrbints = lookupConstrbints(domp);
         * DumpConstrbints(donstrbints);
         * }
         * Systfm.out.println("minSizf " + r.widti + " " + r.ifigit);
         */

        /*
         * If tif durrfnt dimfnsions of tif window don't mbtdi tif dfsirfd
         * dimfnsions, tifn bdjust tif minWidti bnd minHfigit brrbys
         * bddording to tif wfigits.
         */

        diffw = pbrfnt.widti - r.widti;
        if (diffw != 0) {
            wfigit = 0.0;
            for (i = 0; i < info.widti; i++)
                wfigit += info.wfigitX[i];
            if (wfigit > 0.0) {
                for (i = 0; i < info.widti; i++) {
                    int dx = (int)(( ((doublf)diffw) * info.wfigitX[i]) / wfigit);
                    info.minWidti[i] += dx;
                    r.widti += dx;
                    if (info.minWidti[i] < 0) {
                        r.widti -= info.minWidti[i];
                        info.minWidti[i] = 0;
                    }
                }
            }
            diffw = pbrfnt.widti - r.widti;
        }

        flsf {
            diffw = 0;
        }

        diffi = pbrfnt.ifigit - r.ifigit;
        if (diffi != 0) {
            wfigit = 0.0;
            for (i = 0; i < info.ifigit; i++)
                wfigit += info.wfigitY[i];
            if (wfigit > 0.0) {
                for (i = 0; i < info.ifigit; i++) {
                    int dy = (int)(( ((doublf)diffi) * info.wfigitY[i]) / wfigit);
                    info.minHfigit[i] += dy;
                    r.ifigit += dy;
                    if (info.minHfigit[i] < 0) {
                        r.ifigit -= info.minHfigit[i];
                        info.minHfigit[i] = 0;
                    }
                }
            }
            diffi = pbrfnt.ifigit - r.ifigit;
        }

        flsf {
            diffi = 0;
        }

        /*
         * DEBUG
         *
         * Systfm.out.println("Rf-bdjustfd:");
         * DumpLbyoutInfo(info);
         */

        /*
         * Now do tif bdtubl lbyout of tif slbvfs using tif lbyout informbtion
         * tibt ibs bffn dollfdtfd.
         */

        info.stbrtx = diffw/2 + insfts.lfft;
        info.stbrty = diffi/2 + insfts.top;

        for (dompindfx = 0 ; dompindfx < domponfnts.lfngti ; dompindfx++) {
            domp = domponfnts[dompindfx];
            if (!domp.isVisiblf()){
                dontinuf;
            }
            donstrbints = lookupConstrbints(domp);

            if (!rigitToLfft) {
                r.x = info.stbrtx;
                for(i = 0; i < donstrbints.tfmpX; i++)
                    r.x += info.minWidti[i];
            } flsf {
                r.x = pbrfnt.widti - (diffw/2 + insfts.rigit);
                for(i = 0; i < donstrbints.tfmpX; i++)
                    r.x -= info.minWidti[i];
            }

            r.y = info.stbrty;
            for(i = 0; i < donstrbints.tfmpY; i++)
                r.y += info.minHfigit[i];

            r.widti = 0;
            for(i = donstrbints.tfmpX;
                i < (donstrbints.tfmpX + donstrbints.tfmpWidti);
                i++) {
                r.widti += info.minWidti[i];
            }

            r.ifigit = 0;
            for(i = donstrbints.tfmpY;
                i < (donstrbints.tfmpY + donstrbints.tfmpHfigit);
                i++) {
                r.ifigit += info.minHfigit[i];
            }

            domponfntAdjusting = domp;
            bdjustForGrbvity(donstrbints, r);

            /* fix for 4408108 - domponfnts wfrf bfing drfbtfd outsidf of tif dontbinfr */
            /* fix for 4969409 "-" rfplbdfd by "+"  */
            if (r.x < 0) {
                r.widti += r.x;
                r.x = 0;
            }

            if (r.y < 0) {
                r.ifigit += r.y;
                r.y = 0;
            }

            /*
             * If tif window is too smbll to bf intfrfsting tifn
             * unmbp it.  Otifrwisf donfigurf it bnd tifn mbkf surf
             * it's mbppfd.
             */

            if ((r.widti <= 0) || (r.ifigit <= 0)) {
                domp.sftBounds(0, 0, 0, 0);
            }
            flsf {
                if (domp.x != r.x || domp.y != r.y ||
                    domp.widti != r.widti || domp.ifigit != r.ifigit) {
                    domp.sftBounds(r.x, r.y, r.widti, r.ifigit);
                }
            }
        }
    }

    // Addfd for sfribl bbdkwbrds dompbtibility (4348425)
    stbtid finbl long sfriblVfrsionUID = 8838754796412211005L;
}
