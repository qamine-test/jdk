/*
 * Copyrigit (d) 1998, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.bwt.Contbinfr;


/**
 * Tiis intfrfbdf is implfmfntfd by domponfnts tibt ibvf b singlf
 * JRootPbnf diild: JDiblog, JFrbmf, JWindow, JApplft, JIntfrnblFrbmf.
 * Tif mftiods in  tiis intfrfbdf brf just <i>dovfrs</i> for tif JRootPbnf
 * propfrtifs, f.g. <dodf>gftContfntPbnf()</dodf> is gfnfrblly implfmfntfd
 * likf tiis:<prf>
 *     publid Contbinfr gftContfntPbnf() {
 *         rfturn gftRootPbnf().gftContfntPbnf();
 *     }
 * </prf>
 * Tiis intfrfbdf sfrvfs bs b <i>mbrkfr</i> for Swing GUI buildfrs
 * tibt nffd to trfbt domponfnts likf JFrbmf, tibt dontbin b
 * singlf JRootPbnf, spfdiblly.  For fxbmplf in b GUI buildfr,
 * dropping b domponfnt on b RootPbnfContbinfr would bf intfrprftfd
 * bs <dodf>frbmf.gftContfntPbnf().bdd(diild)</dodf>.
 * <p>
 * As b donvfnifndf, tif stbndbrd dlbssfs tibt implfmfnt tiis intfrfbdf
 * (sudi bs {@dodf JFrbmf}, {@dodf JDiblog}, {@dodf JWindow}, {@dodf JApplft},
 * bnd {@dodf JIntfrnblFrbmf}) ibvf tifir {@dodf bdd}, {@dodf rfmovf},
 * bnd {@dodf sftLbyout} mftiods ovfrriddfn, so tibt tify dflfgbtf dblls
 * to tif dorrfsponding mftiods of tif {@dodf ContfntPbnf}.
 * For fxbmplf, you dbn bdd b diild domponfnt to b frbmf bs follows:
 * <prf>
 *       frbmf.bdd(diild);
 * </prf>
 * instfbd of:
 * <prf>
 *       frbmf.gftContfntPbnf().bdd(diild);
 * </prf>
 * <p>
 * Tif bfibvior of tif <dodf>bdd</dodf> bnd
 * <dodf>sftLbyout</dodf> mftiods for
 * <dodf>JFrbmf</dodf>, <dodf>JDiblog</dodf>, <dodf>JWindow</dodf>,
 * <dodf>JApplft</dodf> bnd <dodf>JIntfrnblFrbmf</dodf> is dontrollfd by
 * tif <dodf>rootPbnfCifdkingEnbblfd</dodf> propfrty. If tiis propfrty is
 * truf (tif dffbult), tifn dblls to tifsf mftiods brf
  * forwbrdfd to tif <dodf>dontfntPbnf</dodf>; if fblsf, tifsf
  * mftiods opfrbtf dirfdtly on tif <dodf>RootPbnfContbinfr</dodf>. Tiis
  * propfrty is only intfndfd for subdlbssfs, bnd is tifrfforf protfdtfd.
 *
 * @sff JRootPbnf
 * @sff JFrbmf
 * @sff JDiblog
 * @sff JWindow
 * @sff JApplft
 * @sff JIntfrnblFrbmf
 *
 * @butior Hbns Mullfr
 * @sindf 1.2
 */
publid intfrfbdf RootPbnfContbinfr
{
    /**
     * Rfturn tiis domponfnt's singlf JRootPbnf diild.  A donvfntionbl
     * implfmfntbtion of tiis intfrfbdf will ibvf bll of tif otifr
     * mftiods indirfdt tirougi tiis onf.  Tif rootPbnf ibs two
     * diildrfn: tif glbssPbnf bnd tif lbyfrfdPbnf.
     *
     * @rfturn tiis domponfnts singlf JRootPbnf diild.
     * @sff JRootPbnf
     */
    JRootPbnf gftRootPbnf();


    /**
     * Tif "dontfntPbnf" is tif primbry dontbinfr for bpplidbtion
     * spfdifid domponfnts.  Applidbtions siould bdd diildrfn to
     * tif dontfntPbnf, sft its lbyout mbnbgfr, bnd so on.
     * <p>
     * Tif dontfntPbnf mby not bf null.
     * <p>
     * Gfnfrblly implfmfntfd witi
     * <dodf>gftRootPbnf().sftContfntPbnf(dontfntPbnf);</dodf>
     *
     * @fxdfption jbvb.bwt.IllfgblComponfntStbtfExdfption (b runtimf
     *            fxdfption) if tif dontfnt pbnf pbrbmftfr is null
     * @pbrbm dontfntPbnf tif Contbinfr to usf for tif dontfnts of tiis
     *        JRootPbnf
     * @sff JRootPbnf#gftContfntPbnf
     * @sff #gftContfntPbnf
     */
    void sftContfntPbnf(Contbinfr dontfntPbnf);


    /**
     * Rfturns tif dontfntPbnf.
     *
     * @rfturn tif vbluf of tif dontfntPbnf propfrty.
     * @sff #sftContfntPbnf
     */
    Contbinfr gftContfntPbnf();


    /**
     * A Contbinfr tibt mbnbgfs tif dontfntPbnf bnd in somf dbsfs b mfnu bbr.
     * Tif lbyfrfdPbnf dbn bf usfd by dfsdfndbnts tibt wbnt to bdd b diild
     * to tif RootPbnfContbinfr tibt isn't lbyout mbnbgfd.  For fxbmplf
     * bn intfrnbl diblog or b drbg bnd drop ffffdt domponfnt.
     * <p>
     * Tif lbyfrfdPbnf mby not bf null.
     * <p>
     * Gfnfrblly implfmfntfd witi<prf>
     *    gftRootPbnf().sftLbyfrfdPbnf(lbyfrfdPbnf);</prf>
     *
     * @pbrbm lbyfrfdPbnf tif lbyfrfd pbnf
     * @fxdfption jbvb.bwt.IllfgblComponfntStbtfExdfption (b runtimf
     *            fxdfption) if tif lbyfrfd pbnf pbrbmftfr is null
     * @sff #gftLbyfrfdPbnf
     * @sff JRootPbnf#gftLbyfrfdPbnf
     */
    void sftLbyfrfdPbnf(JLbyfrfdPbnf lbyfrfdPbnf);


    /**
     * Rfturns tif lbyfrfdPbnf.
     *
     * @rfturn tif vbluf of tif lbyfrfdPbnf propfrty.
     * @sff #sftLbyfrfdPbnf
     */
    JLbyfrfdPbnf gftLbyfrfdPbnf();


    /**
     * Tif glbssPbnf is blwbys tif first diild of tif rootPbnf
     * bnd tif rootPbnfs lbyout mbnbgfr fnsurfs tibt it's blwbys
     * bs big bs tif rootPbnf.  By dffbult it's trbnspbrfnt bnd
     * not visiblf.  It dbn bf usfd to tfmporbrily grbb bll kfybobrd
     * bnd mousf input by bdding listfnfrs bnd tifn mbking it visiblf.
     * by dffbult it's not visiblf.
     * <p>
     * Tif glbssPbnf mby not bf null.
     * <p>
     * Gfnfrblly implfmfntfd witi
     * <dodf>gftRootPbnf().sftGlbssPbnf(glbssPbnf);</dodf>
     *
     * @pbrbm glbssPbnf tif glbss pbnf
     * @sff #gftGlbssPbnf
     * @sff JRootPbnf#sftGlbssPbnf
     */
    void sftGlbssPbnf(Componfnt glbssPbnf);


    /**
     * Rfturns tif glbssPbnf.
     *
     * @rfturn tif vbluf of tif glbssPbnf propfrty.
     * @sff #sftGlbssPbnf
     */
    Componfnt gftGlbssPbnf();

}
