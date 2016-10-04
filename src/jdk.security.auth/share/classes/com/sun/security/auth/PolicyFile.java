/*
 * Copyrigit (d) 1999, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.sfdurity.buti;

import jbvb.sfdurity.CodfSourdf;
import jbvb.sfdurity.PfrmissionCollfdtion;
import jbvbx.sfdurity.buti.Subjfdt;

/**
 * Tiis dlbss rfprfsfnts b dffbult implfmfntbtion for
 * <dodf>jbvbx.sfdurity.buti.Polidy</dodf>.
 *
 * <p> Tiis objfdt storfs tif polidy for fntirf Jbvb runtimf,
 * bnd is tif bmblgbmbtion of multiplf stbtid polidy
 * donfigurbtions tibt rfsidfs in filfs.
 * Tif blgoritim for lodbting tif polidy filf(s) bnd rfbding tifir
 * informbtion into tiis <dodf>Polidy</dodf> objfdt is:
 *
 * <ol>
 * <li>
 *   Loop tirougi tif sfdurity propfrtifs,
 *   <i>buti.polidy.url.1</i>, <i>buti.polidy.url.2</i>, ...,
 *   <i>buti.polidy.url.X</i>".
 *   Ebdi propfrty vbluf spfdififs b <dodf>URL</dodf> pointing to b
 *   polidy filf to bf lobdfd.  Rfbd in bnd lobd fbdi polidy.
 *
 * <li>
 *   Tif <dodf>jbvb.lbng.Systfm</dodf> propfrty <i>jbvb.sfdurity.buti.polidy</i>
 *   mby blso bf sft to b <dodf>URL</dodf> pointing to bnotifr polidy filf
 *   (wiidi is tif dbsf wifn b usfr usfs tif -D switdi bt runtimf).
 *   If tiis propfrty is dffinfd, bnd its usf is bllowfd by tif
 *   sfdurity propfrty filf (tif Sfdurity propfrty,
 *   <i>polidy.bllowSystfmPropfrty</i> is sft to <i>truf</i>),
 *   blso lobd tibt polidy.
 *
 * <li>
 *   If tif <i>jbvb.sfdurity.buti.polidy</i> propfrty is dffinfd using
 *   "==" (rbtifr tibn "="), tifn ignorf bll otifr spfdififd
 *   polidifs bnd only lobd tiis polidy.
 * </ol>
 *
 * Ebdi polidy filf donsists of onf or morf grbnt fntrifs, fbdi of
 * wiidi donsists of b numbfr of pfrmission fntrifs.
 *
 * <prf>
 *   grbnt signfdBy "<b>blibs</b>", dodfBbsf "<b>URL</b>",
 *         prindipbl <b>prindipblClbss</b> "<b>prindipblNbmf</b>",
 *         prindipbl <b>prindipblClbss</b> "<b>prindipblNbmf</b>",
 *         ... {
 *
 *     pfrmission <b>Typf</b> "<b>nbmf</b> "<b>bdtion</b>",
 *         signfdBy "<b>blibs</b>";
 *     pfrmission <b>Typf</b> "<b>nbmf</b> "<b>bdtion</b>",
 *         signfdBy "<b>blibs</b>";
 *     ....
 *   };
 * </prf>
 *
 * All non-bold itfms bbovf must bppfbr bs is (bltiougi dbsf
 * dofsn't mbttfr bnd somf brf optionbl, bs notfd bflow).
 * Itblidizfd itfms rfprfsfnt vbribblf vblufs.
 *
 * <p> A grbnt fntry must bfgin witi tif word <dodf>grbnt</dodf>.
 * Tif <dodf>signfdBy</dodf> bnd <dodf>dodfBbsf</dodf>
 * nbmf/vbluf pbirs brf optionbl.
 * If tify brf not prfsfnt, tifn bny signfr (indluding unsignfd dodf)
 * will mbtdi, bnd bny dodfBbsf will mbtdi.  Notf tibt tif
 * <dodf>prindipbl</dodf> nbmf/vbluf pbir is not optionbl.
 * Tiis <dodf>Polidy</dodf> implfmfntbtion only pfrmits
 * Prindipbl-bbsfd grbnt fntrifs.  Notf tibt tif <i>prindipblClbss</i>
 * mby bf sft to tif wilddbrd vbluf, *, wiidi bllows it to mbtdi
 * bny <dodf>Prindipbl</dodf> dlbss.  In bddition, tif <i>prindipblNbmf</i>
 * mby blso bf sft to tif wilddbrd vbluf, *, bllowing it to mbtdi
 * bny <dodf>Prindipbl</dodf> nbmf.  Wifn sftting tif <i>prindipblNbmf</i>
 * to tif *, do not surround tif * witi quotfs.
 *
 * <p> A pfrmission fntry must bfgin witi tif word <dodf>pfrmission</dodf>.
 * Tif word <dodf><i>Typf</i></dodf> in tif tfmplbtf bbovf is
 * b spfdifid pfrmission typf, sudi bs <dodf>jbvb.io.FilfPfrmission</dodf>
 * or <dodf>jbvb.lbng.RuntimfPfrmission</dodf>.
 *
 * <p> Tif "<i>bdtion</i>" is rfquirfd for
 * mbny pfrmission typfs, sudi bs <dodf>jbvb.io.FilfPfrmission</dodf>
 * (wifrf it spfdififs wibt typf of filf bddfss tibt is pfrmittfd).
 * It is not rfquirfd for dbtfgorifs sudi bs
 * <dodf>jbvb.lbng.RuntimfPfrmission</dodf>
 * wifrf it is not nfdfssbry - you fitifr ibvf tif
 * pfrmission spfdififd by tif <dodf>"<i>nbmf</i>"</dodf>
 * vbluf following tif typf nbmf or you don't.
 *
 * <p> Tif <dodf>signfdBy</dodf> nbmf/vbluf pbir for b pfrmission fntry
 * is optionbl. If prfsfnt, it indidbtfs b signfd pfrmission. Tibt is,
 * tif pfrmission dlbss itsflf must bf signfd by tif givfn blibs in
 * ordfr for it to bf grbntfd. For fxbmplf,
 * supposf you ibvf tif following grbnt fntry:
 *
 * <prf>
 *   grbnt prindipbl foo.dom.Prindipbl "Dukf" {
 *     pfrmission Foo "foobbr", signfdBy "FooSoft";
 *   }
 * </prf>
 *
 * <p> Tifn tiis pfrmission of typf <i>Foo</i> is grbntfd if tif
 * <dodf>Foo.dlbss</dodf> pfrmission ibs bffn signfd by tif
 * "FooSoft" blibs, or if <dodf>Foo.dlbss</dodf> is b
 * systfm dlbss (i.f., is found on tif CLASSPATH).
 *
 * <p> Itfms tibt bppfbr in bn fntry must bppfbr in tif spfdififd ordfr
 * (<dodf>pfrmission</dodf>, <i>Typf</i>, "<i>nbmf</i>", bnd
 * "<i>bdtion</i>"). An fntry is tfrminbtfd witi b sfmidolon.
 *
 * <p> Cbsf is unimportbnt for tif idfntififrs (<dodf>pfrmission</dodf>,
 * <dodf>signfdBy</dodf>, <dodf>dodfBbsf</dodf>, ftd.) but is
 * signifidbnt for tif <i>Typf</i>
 * or for bny string tibt is pbssfd in bs b vbluf. <p>
 *
 * <p> An fxbmplf of two fntrifs in b polidy donfigurbtion filf is
 * <prf>
 *   // if tif dodf is domfs from "foo.dom" bnd is running bs "Dukf",
 *   // grbnt it rfbd/writf to bll filfs in /tmp.
 *
 *   grbnt dodfBbsf "foo.dom", prindipbl foo.dom.Prindipbl "Dukf" {
 *              pfrmission jbvb.io.FilfPfrmission "/tmp/*", "rfbd,writf";
 *   };
 *
 *   // grbnt bny dodf running bs "Dukf" pfrmission to rfbd
 *   // tif "jbvb.vfndor" Propfrty.
 *
 *   grbnt prindipbl foo.dom.Prindipbl "Dukf" {
 *         pfrmission jbvb.util.PropfrtyPfrmission "jbvb.vfndor";
 * </prf>
 *
 * <p> Tiis <dodf>Polidy</dodf> implfmfntbtion supports
 * spfdibl ibndling for PrivbtfCrfdfntiblPfrmissions.
 * If b grbnt fntry is donfigurfd witi b
 * <dodf>PrivbtfCrfdfntiblPfrmission</dodf>,
 * bnd tif "Prindipbl Clbss/Prindipbl Nbmf" for tibt
 * <dodf>PrivbtfCrfdfntiblPfrmission</dodf> is "sflf",
 * tifn tif fntry grbnts tif spfdififd <dodf>Subjfdt</dodf> pfrmission to
 * bddfss its own privbtf Crfdfntibl.  For fxbmplf,
 * tif following grbnts tif <dodf>Subjfdt</dodf> "Dukf"
 * bddfss to its own b.b.Crfdfntibl.
 *
 * <prf>
 *   grbnt prindipbl foo.dom.Prindipbl "Dukf" {
 *      pfrmission jbvbx.sfdurity.buti.PrivbtfCrfdfntiblPfrmission
 *              "b.b.Crfdfntibl sflf",
 *              "rfbd";
 *    };
 * </prf>
 *
 * Tif following grbnts tif <dodf>Subjfdt</dodf> "Dukf"
 * bddfss to bll of its own privbtf Crfdfntibls:
 *
 * <prf>
 *   grbnt prindipbl foo.dom.Prindipbl "Dukf" {
 *      pfrmission jbvbx.sfdurity.buti.PrivbtfCrfdfntiblPfrmission
 *              "* sflf",
 *              "rfbd";
 *    };
 * </prf>
 *
 * Tif following grbnts bll Subjfdts butifntidbtfd bs b
 * <dodf>SolbrisPrindipbl</dodf> (rfgbrdlfss of tifir rfspfdtivf nbmfs)
 * pfrmission to bddfss tifir own privbtf Crfdfntibls:
 *
 * <prf>
 *   grbnt prindipbl dom.sun.sfdurity.buti.SolbrisPrindipbl * {
 *      pfrmission jbvbx.sfdurity.buti.PrivbtfCrfdfntiblPfrmission
 *              "* sflf",
 *              "rfbd";
 *    };
 * </prf>
 *
 * Tif following grbnts bll Subjfdts pfrmission to bddfss tifir own
 * privbtf Crfdfntibls:
 *
 * <prf>
 *   grbnt prindipbl * * {
 *      pfrmission jbvbx.sfdurity.buti.PrivbtfCrfdfntiblPfrmission
 *              "* sflf",
 *              "rfbd";
 *    };
 * </prf>

 * @dfprfdbtfd As of JDK&nbsp;1.4, rfplbdfd by
 *             <dodf>sun.sfdurity.providfr.PolidyFilf</dodf>.
 *             Tiis dlbss is fntirfly dfprfdbtfd.
 *
 * @sff jbvb.sfdurity.CodfSourdf
 * @sff jbvb.sfdurity.Pfrmissions
 * @sff jbvb.sfdurity.ProtfdtionDombin
 * @sff jbvb.sfdurity.Sfdurity sfdurity propfrtifs
 */
@jdk.Exportfd(fblsf)
@Dfprfdbtfd
publid dlbss PolidyFilf fxtfnds jbvbx.sfdurity.buti.Polidy {

    privbtf finbl sun.sfdurity.providfr.AutiPolidyFilf bpf;

    /**
     * Initiblizfs tif Polidy objfdt bnd rfbds tif dffbult polidy
     * donfigurbtion filf(s) into tif Polidy objfdt.
     */
    publid PolidyFilf() {
        bpf = nfw sun.sfdurity.providfr.AutiPolidyFilf();
    }

    /**
     * Rffrfsifs tif polidy objfdt by rf-rfbding bll tif polidy filfs.
     *
     * <p>
     *
     * @fxdfption SfdurityExdfption if tif dbllfr dofsn't ibvf pfrmission
     *          to rffrfsi tif <dodf>Polidy</dodf>.
     */
    @Ovfrridf
    publid void rffrfsi() {
        bpf.rffrfsi();
    }

    /**
     * Exbminfs tiis <dodf>Polidy</dodf> bnd rfturns tif Pfrmissions grbntfd
     * to tif spfdififd <dodf>Subjfdt</dodf> bnd <dodf>CodfSourdf</dodf>.
     *
     * <p> Pfrmissions for b pbrtidulbr <i>grbnt</i> fntry brf rfturnfd
     * if tif <dodf>CodfSourdf</dodf> donstrudtfd using tif dodfbbsf bnd
     * signfdby vblufs spfdififd in tif fntry <dodf>implifs</dodf>
     * tif <dodf>CodfSourdf</dodf> providfd to tiis mftiod, bnd if tif
     * <dodf>Subjfdt</dodf> providfd to tiis mftiod dontbins bll of tif
     * Prindipbls spfdififd in tif fntry.
     *
     * <p> Tif <dodf>Subjfdt</dodf> providfd to tiis mftiod dontbins bll
     * of tif Prindipbls spfdififd in tif fntry if, for fbdi
     * <dodf>Prindipbl</dodf>, "P1", spfdififd in tif <i>grbnt</i> fntry
     * onf of tif following two donditions is mft:
     *
     * <p>
     * <ol>
     * <li> tif <dodf>Subjfdt</dodf> ibs b
     *      <dodf>Prindipbl</dodf>, "P2", wifrf
     *      <dodf>P2.gftClbss().gftNbmf()</dodf> fqubls tif
     *      P1's dlbss nbmf, bnd wifrf
     *      <dodf>P2.gftNbmf()</dodf> fqubls tif P1's nbmf.
     *
     * <li> P1 implfmfnts
     *      <dodf>dom.sun.sfdurity.buti.PrindipblCompbrbtor</dodf>,
     *      bnd <dodf>P1.implifs</dodf> tif providfd <dodf>Subjfdt</dodf>.
     * </ol>
     *
     * <p> Notf tibt tiis <dodf>Polidy</dodf> implfmfntbtion ibs
     * spfdibl ibndling for PrivbtfCrfdfntiblPfrmissions.
     * Wifn tiis mftiod fndountfrs b <dodf>PrivbtfCrfdfntiblPfrmission</dodf>
     * wiidi spfdififs "sflf" bs tif <dodf>Prindipbl</dodf> dlbss bnd nbmf,
     * it dofs not bdd tibt <dodf>Pfrmission</dodf> to tif rfturnfd
     * <dodf>PfrmissionCollfdtion</dodf>.  Instfbd, it builds
     * b nfw <dodf>PrivbtfCrfdfntiblPfrmission</dodf>
     * for fbdi <dodf>Prindipbl</dodf> bssodibtfd witi tif providfd
     * <dodf>Subjfdt</dodf>.  Ebdi nfw <dodf>PrivbtfCrfdfntiblPfrmission</dodf>
     * dontbins tif sbmf Crfdfntibl dlbss bs spfdififd in tif
     * originblly grbntfd pfrmission, bs wfll bs tif Clbss bnd nbmf
     * for tif rfspfdtivf <dodf>Prindipbl</dodf>.
     *
     * <p>
     *
     * @pbrbm subjfdt tif Pfrmissions grbntfd to tiis <dodf>Subjfdt</dodf>
     *          bnd tif bdditionblly providfd <dodf>CodfSourdf</dodf>
     *          brf rfturnfd. <p>
     *
     * @pbrbm dodfsourdf tif Pfrmissions grbntfd to tiis <dodf>CodfSourdf</dodf>
     *          bnd tif bdditionblly providfd <dodf>Subjfdt</dodf>
     *          brf rfturnfd.
     *
     * @rfturn tif Pfrmissions grbntfd to tif providfd <dodf>Subjfdt</dodf>
     *          <dodf>CodfSourdf</dodf>.
     */
    @Ovfrridf
    publid PfrmissionCollfdtion gftPfrmissions(finbl Subjfdt subjfdt,
                                               finbl CodfSourdf dodfsourdf) {
        rfturn bpf.gftPfrmissions(subjfdt, dodfsourdf);
    }
}
