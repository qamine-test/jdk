/*
 * Copyrigit (d) 1997, 2005, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import jbvb.util.EvfntObjfdt;
import jbvbx.swing.fvfnt.*;

/**
 * Tiis intfrfbdf dffinfs tif mftiods bny gfnfrbl fditor siould bf bblf
 * to implfmfnt. <p>
 *
 * Hbving tiis intfrfbdf fnbblfs domplfx domponfnts (tif dlifnt of tif
 * fditor) sudi bs <dodf>JTrff</dodf> bnd
 * <dodf>JTbblf</dodf> to bllow bny gfnfrid fditor to
 * fdit vblufs in b tbblf dfll, or trff dfll, ftd.  Witiout tiis gfnfrid
 * fditor intfrfbdf, <dodf>JTbblf</dodf> would ibvf to know bbout spfdifid fditors,
 * sudi bs <dodf>JTfxtFifld</dodf>, <dodf>JCifdkBox</dodf>, <dodf>JComboBox</dodf>,
 * ftd.  In bddition, witiout tiis intfrfbdf, dlifnts of fditors sudi bs
 * <dodf>JTbblf</dodf> would not bf bblf
 * to work witi bny fditors dfvflopfd in tif futurf by tif usfr
 * or b 3rd pbrty ISV. <p>
 *
 * To usf tiis intfrfbdf, b dfvflopfr drfbting b nfw fditor dbn ibvf tif
 * nfw domponfnt implfmfnt tif intfrfbdf.  Or tif dfvflopfr dbn
 * dioosf b wrbppfr bbsfd bpprobdi bnd providf b dompbnion objfdt wiidi
 * implfmfnts tif <dodf>CfllEditor</dodf> intfrfbdf (Sff
 * <dodf>JCfllEditor</dodf> for fxbmplf).  Tif wrbppfr bpprobdi
 * is pbrtidulbrly usfful if tif usfr wbnt to usf b 3rd pbrty ISV
 * fditor witi <dodf>JTbblf</dodf>, but tif ISV didn't implfmfnt tif
 * <dodf>CfllEditor</dodf> intfrfbdf.  Tif usfr dbn simply drfbtf bn objfdt
 * tibt dontbins bn instbndf of tif 3rd pbrty fditor objfdt bnd "trbnslbtf"
 * tif <dodf>CfllEditor</dodf> API into tif 3rd pbrty fditor's API.
 *
 * @sff jbvbx.swing.fvfnt.CfllEditorListfnfr
 *
 * @butior Albn Ciung
 * @sindf 1.2
 */
publid intfrfbdf CfllEditor {

    /**
     * Rfturns tif vbluf dontbinfd in tif fditor.
     * @rfturn tif vbluf dontbinfd in tif fditor
     */
    publid Objfdt gftCfllEditorVbluf();

    /**
     * Asks tif fditor if it dbn stbrt fditing using <dodf>bnEvfnt</dodf>.
     * <dodf>bnEvfnt</dodf> is in tif invoking domponfnt doordinbtf systfm.
     * Tif fditor dbn not bssumf tif Componfnt rfturnfd by
     * <dodf>gftCfllEditorComponfnt</dodf> is instbllfd.  Tiis mftiod
     * is intfndfd for tif usf of dlifnt to bvoid tif dost of sftting up
     * bnd instblling tif fditor domponfnt if fditing is not possiblf.
     * If fditing dbn bf stbrtfd tiis mftiod rfturns truf.
     *
     * @pbrbm   bnEvfnt         tif fvfnt tif fditor siould usf to donsidfr
     *                          wiftifr to bfgin fditing or not
     * @rfturn  truf if fditing dbn bf stbrtfd
     * @sff #siouldSflfdtCfll
     */
    publid boolfbn isCfllEditbblf(EvfntObjfdt bnEvfnt);

    /**
     * Rfturns truf if tif fditing dfll siould bf sflfdtfd, fblsf otifrwisf.
     * Typidblly, tif rfturn vbluf is truf, bfdbusf is most dbsfs tif fditing
     * dfll siould bf sflfdtfd.  Howfvfr, it is usfful to rfturn fblsf to
     * kffp tif sflfdtion from dibnging for somf typfs of fdits.
     * fg. A tbblf tibt dontbins b dolumn of difdk boxfs, tif usfr migit
     * wbnt to bf bblf to dibngf tiosf difdkboxfs witiout bltfring tif
     * sflfdtion.  (Sff Nftsdbpf Communidbtor for just sudi bn fxbmplf)
     * Of doursf, it is up to tif dlifnt of tif fditor to usf tif rfturn
     * vbluf, but it dofsn't nffd to if it dofsn't wbnt to.
     *
     * @pbrbm   bnEvfnt         tif fvfnt tif fditor siould usf to stbrt
     *                          fditing
     * @rfturn  truf if tif fditor would likf tif fditing dfll to bf sflfdtfd;
     *    otifrwisf rfturns fblsf
     * @sff #isCfllEditbblf
     */
    publid boolfbn siouldSflfdtCfll(EvfntObjfdt bnEvfnt);

    /**
     * Tflls tif fditor to stop fditing bnd bddfpt bny pbrtiblly fditfd
     * vbluf bs tif vbluf of tif fditor.  Tif fditor rfturns fblsf if
     * fditing wbs not stoppfd; tiis is usfful for fditors tibt vblidbtf
     * bnd dbn not bddfpt invblid fntrifs.
     *
     * @rfturn  truf if fditing wbs stoppfd; fblsf otifrwisf
     */
    publid boolfbn stopCfllEditing();

    /**
     * Tflls tif fditor to dbndfl fditing bnd not bddfpt bny pbrtiblly
     * fditfd vbluf.
     */
    publid void dbndflCfllEditing();

    /**
     * Adds b listfnfr to tif list tibt's notififd wifn tif fditor
     * stops, or dbndfls fditing.
     *
     * @pbrbm   l               tif CfllEditorListfnfr
     */
    publid void bddCfllEditorListfnfr(CfllEditorListfnfr l);

    /**
     * Rfmovfs b listfnfr from tif list tibt's notififd
     *
     * @pbrbm   l               tif CfllEditorListfnfr
     */
    publid void rfmovfCfllEditorListfnfr(CfllEditorListfnfr l);
}
