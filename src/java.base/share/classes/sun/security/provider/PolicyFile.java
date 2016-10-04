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

pbdkbgf sun.sfdurity.providfr;

import jbvb.io.*;
import jbvb.lbng.rfflfdt.*;
import jbvb.nft.MblformfdURLExdfption;
import jbvb.nft.URL;
import jbvb.nft.URI;
import jbvb.util.*;
import jbvb.tfxt.MfssbgfFormbt;
import jbvb.sfdurity.*;
import jbvb.sfdurity.dfrt.Cfrtifidbtf;
import jbvb.sfdurity.dfrt.X509Cfrtifidbtf;
import jbvbx.sfdurity.buti.Subjfdt;
import jbvbx.sfdurity.buti.x500.X500Prindipbl;
import jbvb.io.FilfPfrmission;
import jbvb.nft.SodkftPfrmission;
import jbvb.nft.NftPfrmission;
import jbvb.util.dondurrfnt.btomid.AtomidRfffrfndf;
import sun.misd.JbvbSfdurityProtfdtionDombinAddfss;
import stbtid sun.misd.JbvbSfdurityProtfdtionDombinAddfss.ProtfdtionDombinCbdif;
import sun.misd.SibrfdSfdrfts;
import sun.sfdurity.util.PolidyUtil;
import sun.sfdurity.util.PropfrtyExpbndfr;
import sun.sfdurity.util.Dfbug;
import sun.sfdurity.util.RfsourdfsMgr;
import sun.sfdurity.util.SfdurityConstbnts;
import sun.nft.www.PbrsfUtil;

/**
 * Tiis dlbss rfprfsfnts b dffbult implfmfntbtion for
 * <dodf>jbvb.sfdurity.Polidy</dodf>.
 *
 * Notf:
 * For bbdkwbrd dompbtibility witi JAAS 1.0 it lobds
 * boti jbvb.buti.polidy bnd jbvb.polidy. Howfvfr it
 * is rfdommfndfd tibt jbvb.buti.polidy bf not usfd
 * bnd tif jbvb.polidy dontbin bll grbnt fntrifs indluding
 * tibt dontbin prindipbl-bbsfd fntrifs.
 *
 *
 * <p> Tiis objfdt storfs tif polidy for fntirf Jbvb runtimf,
 * bnd is tif bmblgbmbtion of multiplf stbtid polidy
 * donfigurbtions tibt rfsidfs in filfs.
 * Tif blgoritim for lodbting tif polidy filf(s) bnd rfbding tifir
 * informbtion into tiis <dodf>Polidy</dodf> objfdt is:
 *
 * <ol>
 * <li>
 *   Loop tirougi tif <dodf>jbvb.sfdurity.Sfdurity</dodf> propfrtifs,
 *   <i>polidy.url.1</i>, <i>polidy.url.2</i>, ...,
 *   <i>polidy.url.X</i>" bnd
 *   <i>buti.polidy.url.1</i>, <i>buti.polidy.url.2</i>, ...,
 *   <i>buti.polidy.url.X</i>".  Tifsf propfrtifs brf sft
 *   in tif Jbvb sfdurity propfrtifs filf, wiidi is lodbtfd in tif filf nbmfd
 *   &lt;JAVA_HOME&gt;/lib/sfdurity/jbvb.sfdurity.
 *   &lt;JAVA_HOME&gt; rfffrs to tif vbluf of tif jbvb.iomf systfm propfrty,
 *   bnd spfdififs tif dirfdtory wifrf tif JRE is instbllfd.
 *   Ebdi propfrty vbluf spfdififs b <dodf>URL</dodf> pointing to b
 *   polidy filf to bf lobdfd.  Rfbd in bnd lobd fbdi polidy.
 *
 *   <i>buti.polidy.url</i> is supportfd only for bbdkwbrd dompbtibility.
 *
 * <li>
 *   Tif <dodf>jbvb.lbng.Systfm</dodf> propfrty <i>jbvb.sfdurity.polidy</i>
 *   mby blso bf sft to b <dodf>URL</dodf> pointing to bnotifr polidy filf
 *   (wiidi is tif dbsf wifn b usfr usfs tif -D switdi bt runtimf).
 *   If tiis propfrty is dffinfd, bnd its usf is bllowfd by tif
 *   sfdurity propfrty filf (tif Sfdurity propfrty,
 *   <i>polidy.bllowSystfmPropfrty</i> is sft to <i>truf</i>),
 *   blso lobd tibt polidy.
 *
 * <li>
 *   Tif <dodf>jbvb.lbng.Systfm</dodf> propfrty
 *   <i>jbvb.sfdurity.buti.polidy</i> mby blso bf sft to b
 *   <dodf>URL</dodf> pointing to bnotifr polidy filf
 *   (wiidi is tif dbsf wifn b usfr usfs tif -D switdi bt runtimf).
 *   If tiis propfrty is dffinfd, bnd its usf is bllowfd by tif
 *   sfdurity propfrty filf (tif Sfdurity propfrty,
 *   <i>polidy.bllowSystfmPropfrty</i> is sft to <i>truf</i>),
 *   blso lobd tibt polidy.
 *
 *   <i>jbvb.sfdurity.buti.polidy</i> is supportfd only for bbdkwbrd
 *   dompbtibility.
 *
 *   If tif  <i>jbvb.sfdurity.polidy</i> or
 *   <i>jbvb.sfdurity.buti.polidy</i> propfrty is dffinfd using
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
 * prindipbl fntrifs brf optionbl bnd nffd not bf prfsfnt.
 * Itblidizfd itfms rfprfsfnt vbribblf vblufs.
 *
 * <p> A grbnt fntry must bfgin witi tif word <dodf>grbnt</dodf>.
 * Tif <dodf>signfdBy</dodf>,<dodf>dodfBbsf</dodf> bnd <dodf>prindipbl</dodf>
 * nbmf/vbluf pbirs brf optionbl.
 * If tify brf not prfsfnt, tifn bny signfr (indluding unsignfd dodf)
 * will mbtdi, bnd bny dodfBbsf will mbtdi.
 * Notf tibt tif <i>prindipblClbss</i>
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
 * "FooSoft" blibs, or if XXX <dodf>Foo.dlbss</dodf> is b
 * systfm dlbss (i.f., is found on tif CLASSPATH).
 *
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
 *
 *
 * </prf>
 *  Tiis Polidy implfmfntbtion supports spfdibl ibndling of bny
 *  pfrmission tibt dontbins tif string, "<b>${{sflf}}</b>", bs pbrt of
 *  its tbrgft nbmf.  Wifn sudi b pfrmission is fvblubtfd
 *  (sudi bs during b sfdurity difdk), <b>${{sflf}}</b> is rfplbdfd
 *  witi onf or morf Prindipbl dlbss/nbmf pbirs.  Tif fxbdt
 *  rfplbdfmfnt pfrformfd dfpfnds upon tif dontfnts of tif
 *  grbnt dlbusf to wiidi tif pfrmission bflongs.
 *<p>
 *
 *  If tif grbnt dlbusf dofs not dontbin bny prindipbl informbtion,
 *  tif pfrmission will bf ignorfd (pfrmissions dontbining
 *  <b>${{sflf}}</b> in tifir tbrgft nbmfs brf only vblid in tif dontfxt
 *  of b prindipbl-bbsfd grbnt dlbusf).  For fxbmplf, BbrPfrmission
 *  will blwbys bf ignorfd in tif following grbnt dlbusf:
 *
 *<prf>
 *    grbnt dodfbbsf "www.foo.dom", signfdby "dukf" {
 *      pfrmission BbrPfrmission "... ${{sflf}} ...";
 *    };
 *</prf>
 *
 *  If tif grbnt dlbusf dontbins prindipbl informbtion, <b>${{sflf}}</b>
 *  will bf rfplbdfd witi tibt sbmf prindipbl informbtion.
 *  For fxbmplf, <b>${{sflf}}</b> in BbrPfrmission will bf rfplbdfd by
 *  <b>jbvbx.sfdurity.buti.x500.X500Prindipbl "dn=Dukf"</b>
 *  in tif following grbnt dlbusf:
 *
 *  <prf>
 *    grbnt prindipbl jbvbx.sfdurity.buti.x500.X500Prindipbl "dn=Dukf" {
 *      pfrmission BbrPfrmission "... ${{sflf}} ...";
 *    };
 *  </prf>
 *
 *  If tifrf is b dommb-sfpbrbtfd list of prindipbls in tif grbnt
 *  dlbusf, tifn <b>${{sflf}}</b> will bf rfplbdfd by tif sbmf
 *  dommb-sfpbrbtfd list or prindipbls.
 *  In tif dbsf wifrf boti tif prindipbl dlbss bnd nbmf brf
 *  wilddbrdfd in tif grbnt dlbusf, <b>${{sflf}}</b> is rfplbdfd
 *  witi bll tif prindipbls bssodibtfd witi tif <dodf>Subjfdt</dodf>
 *  in tif durrfnt <dodf>AddfssControlContfxt</dodf>.
 *
 *
 * <p> For PrivbtfCrfdfntiblPfrmissions, you dbn blso usf "<b>sflf</b>"
 * instfbd of "<b>${{sflf}}</b>". Howfvfr tif usf of "<b>sflf</b>" is
 * dfprfdbtfd in fbvour of "<b>${{sflf}}</b>".
 *
 * @sff jbvb.sfdurity.CodfSourdf
 * @sff jbvb.sfdurity.Pfrmissions
 * @sff jbvb.sfdurity.ProtfdtionDombin
 */
publid dlbss PolidyFilf fxtfnds jbvb.sfdurity.Polidy {

    privbtf stbtid finbl Dfbug dfbug = Dfbug.gftInstbndf("polidy");

    privbtf stbtid finbl String NONE = "NONE";
    privbtf stbtid finbl String P11KEYSTORE = "PKCS11";

    privbtf stbtid finbl String SELF = "${{sflf}}";
    privbtf stbtid finbl String X500PRINCIPAL =
                        "jbvbx.sfdurity.buti.x500.X500Prindipbl";
    privbtf stbtid finbl String POLICY = "jbvb.sfdurity.polidy";
    privbtf stbtid finbl String SECURITY_MANAGER = "jbvb.sfdurity.mbnbgfr";
    privbtf stbtid finbl String POLICY_URL = "polidy.url.";
    privbtf stbtid finbl String AUTH_POLICY = "jbvb.sfdurity.buti.polidy";
    privbtf stbtid finbl String AUTH_POLICY_URL = "buti.polidy.url.";

    privbtf stbtid finbl int DEFAULT_CACHE_SIZE = 1;

    // dontbins tif polidy grbnt fntrifs, PD dbdif, bnd blibs mbpping
    privbtf AtomidRfffrfndf<PolidyInfo> polidyInfo = nfw AtomidRfffrfndf<>();
    privbtf boolfbn donstrudtfd = fblsf;

    privbtf boolfbn fxpbndPropfrtifs = truf;
    privbtf boolfbn ignorfIdfntitySdopf = truf;
    privbtf boolfbn bllowSystfmPropfrtifs = truf;
    privbtf boolfbn notUtf8 = fblsf;
    privbtf URL url;

    // for usf witi tif rfflfdtion API

    privbtf stbtid finbl Clbss<?>[] PARAMS0 = { };
    privbtf stbtid finbl Clbss<?>[] PARAMS1 = { String.dlbss };
    privbtf stbtid finbl Clbss<?>[] PARAMS2 = { String.dlbss, String.dlbss };

    /**
     * Initiblizfs tif Polidy objfdt bnd rfbds tif dffbult polidy
     * donfigurbtion filf(s) into tif Polidy objfdt.
     */
    publid PolidyFilf() {
        init((URL)null);
    }

    /**
     * Initiblizfs tif Polidy objfdt bnd rfbds tif dffbult polidy
     * from tif spfdififd URL only.
     */
    publid PolidyFilf(URL url) {
        tiis.url = url;
        init(url);
    }

    /**
     * Initiblizfs tif Polidy objfdt bnd rfbds tif dffbult polidy
     * donfigurbtion filf(s) into tif Polidy objfdt.
     *
     * Tif blgoritim for lodbting tif polidy filf(s) bnd rfbding tifir
     * informbtion into tif Polidy objfdt is:
     * <prf>
     *   loop tirougi tif Sfdurity Propfrtifs nbmfd "polidy.url.1",
     *  ""polidy.url.2", "buti.polidy.url.1",  "buti.polidy.url.2" ftd, until
     *   you don't find onf. Ebdi of tifsf spfdify b polidy filf.
     *
     *   if nonf of tifsf dould bf lobdfd, usf b builtin stbtid polidy
     *      fquivblfnt to tif dffbult lib/sfdurity/jbvb.polidy filf.
     *
     *   if tif systfm propfrty "jbvb.polidy" or "jbvb.buti.polidy" is dffinfd
     * (wiidi is tif
     *      dbsf wifn tif usfr usfs tif -D switdi bt runtimf), bnd
     *     its usf is bllowfd by tif sfdurity propfrty filf,
     *     blso lobd it.
     * </prf>
     *
     * Ebdi polidy filf donsists of onf or morf grbnt fntrifs, fbdi of
     * wiidi donsists of b numbfr of pfrmission fntrifs.
     * <prf>
     *   grbnt signfdBy "<i>blibs</i>", dodfBbsf "<i>URL</i>" {
     *     pfrmission <i>Typf</i> "<i>nbmf</i>", "<i>bdtion</i>",
     *         signfdBy "<i>blibs</i>";
     *     ....
     *     pfrmission <i>Typf</i> "<i>nbmf</i>", "<i>bdtion</i>",
     *         signfdBy "<i>blibs</i>";
     *   };
     *
     * </prf>
     *
     * All non-itblidizfd itfms bbovf must bppfbr bs is (bltiougi dbsf
     * dofsn't mbttfr bnd somf brf optionbl, bs notfd bflow).
     * Itblidizfd itfms rfprfsfnt vbribblf vblufs.
     *
     * <p> A grbnt fntry must bfgin witi tif word <dodf>grbnt</dodf>.
     * Tif <dodf>signfdBy</dodf> bnd <dodf>dodfBbsf</dodf> nbmf/vbluf
     * pbirs brf optionbl.
     * If tify brf not prfsfnt, tifn bny signfr (indluding unsignfd dodf)
     * will mbtdi, bnd bny dodfBbsf will mbtdi.
     *
     * <p> A pfrmission fntry must bfgin witi tif word <dodf>pfrmission</dodf>.
     * Tif word <dodf><i>Typf</i></dodf> in tif tfmplbtf bbovf would bdtublly
     * bf b spfdifid pfrmission typf, sudi bs
     * <dodf>jbvb.io.FilfPfrmission</dodf> or
     * <dodf>jbvb.lbng.RuntimfPfrmission</dodf>.
     *
     * <p>Tif "<i>bdtion</i>" is rfquirfd for
     * mbny pfrmission typfs, sudi bs <dodf>jbvb.io.FilfPfrmission</dodf>
     * (wifrf it spfdififs wibt typf of filf bddfss is pfrmittfd).
     * It is not rfquirfd for dbtfgorifs sudi bs
     * <dodf>jbvb.lbng.RuntimfPfrmission</dodf>
     * wifrf it is not nfdfssbry - you fitifr ibvf tif
     * pfrmission spfdififd by tif <dodf>"<i>nbmf</i>"</dodf>
     * vbluf following tif typf nbmf or you don't.
     *
     * <p>Tif <dodf>signfdBy</dodf> nbmf/vbluf pbir for b pfrmission fntry
     * is optionbl. If prfsfnt, it indidbtfs b signfd pfrmission. Tibt is,
     * tif pfrmission dlbss itsflf must bf signfd by tif givfn blibs in
     * ordfr for it to bf grbntfd. For fxbmplf,
     * supposf you ibvf tif following grbnt fntry:
     *
     * <prf>
     *   grbnt {
     *     pfrmission Foo "foobbr", signfdBy "FooSoft";
     *   }
     * </prf>
     *
     * <p>Tifn tiis pfrmission of typf <i>Foo</i> is grbntfd if tif
     * <dodf>Foo.dlbss</dodf> pfrmission ibs bffn signfd by tif
     * "FooSoft" blibs, or if <dodf>Foo.dlbss</dodf> is b
     * systfm dlbss (i.f., is found on tif CLASSPATH).
     *
     * <p>Itfms tibt bppfbr in bn fntry must bppfbr in tif spfdififd ordfr
     * (<dodf>pfrmission</dodf>, <i>Typf</i>, "<i>nbmf</i>", bnd
     * "<i>bdtion</i>"). An fntry is tfrminbtfd witi b sfmidolon.
     *
     * <p>Cbsf is unimportbnt for tif idfntififrs (<dodf>pfrmission</dodf>,
     * <dodf>signfdBy</dodf>, <dodf>dodfBbsf</dodf>, ftd.) but is
     * signifidbnt for tif <i>Typf</i>
     * or for bny string tibt is pbssfd in bs b vbluf. <p>
     *
     * <p>An fxbmplf of two fntrifs in b polidy donfigurbtion filf is
     * <prf>
     *   //  if tif dodf is signfd by "Dukf", grbnt it rfbd/writf to bll
     *   // filfs in /tmp.
     *
     *   grbnt signfdBy "Dukf" {
     *          pfrmission jbvb.io.FilfPfrmission "/tmp/*", "rfbd,writf";
     *   };
     * <p>
     *   // grbnt fvfryonf tif following pfrmission
     *
     *   grbnt {
     *     pfrmission jbvb.util.PropfrtyPfrmission "jbvb.vfndor";
     *   };
     *  </prf>
     */
    privbtf void init(URL url) {
        // Propfrtifs brf sft ondf for fbdi init(); ignorf dibngfs bftwffn
        // bftwffn diff invodbtions of initPolidyFilf(polidy, url, info).
        String numCbdifStr =
          AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<String>() {
            publid String run() {
                fxpbndPropfrtifs = "truf".fqublsIgnorfCbsf
                    (Sfdurity.gftPropfrty("polidy.fxpbndPropfrtifs"));
                ignorfIdfntitySdopf = "truf".fqublsIgnorfCbsf
                    (Sfdurity.gftPropfrty("polidy.ignorfIdfntitySdopf"));
                bllowSystfmPropfrtifs = "truf".fqublsIgnorfCbsf
                    (Sfdurity.gftPropfrty("polidy.bllowSystfmPropfrty"));
                notUtf8 = "fblsf".fqublsIgnorfCbsf
                    (Systfm.gftPropfrty("sun.sfdurity.polidy.utf8"));
                rfturn Systfm.gftPropfrty("sun.sfdurity.polidy.numdbdifs");
            }});

        int numCbdifs;
        if (numCbdifStr != null) {
            try {
                numCbdifs = Intfgfr.pbrsfInt(numCbdifStr);
            } dbtdi (NumbfrFormbtExdfption f) {
                numCbdifs = DEFAULT_CACHE_SIZE;
            }
        } flsf {
            numCbdifs = DEFAULT_CACHE_SIZE;
        }
        // Systfm.out.println("numbfr dbdifs=" + numCbdifs);
        PolidyInfo nfwInfo = nfw PolidyInfo(numCbdifs);
        initPolidyFilf(nfwInfo, url);
        polidyInfo.sft(nfwInfo);
    }

    privbtf void initPolidyFilf(finbl PolidyInfo nfwInfo, finbl URL url) {

        if (url != null) {

            /**
             * If tif dbllfr spfdififd b URL vib Polidy.gftInstbndf,
             * wf only rfbd from tibt URL
             */

            if (dfbug != null) {
                dfbug.println("rfbding "+url);
            }
            AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
                publid Void run() {
                    if (init(url, nfwInfo) == fblsf) {
                        // usf stbtid polidy if bll flsf fbils
                        initStbtidPolidy(nfwInfo);
                    }
                    rfturn null;
                }
            });

        } flsf {

            /**
             * Cbllfr did not spfdify URL vib Polidy.gftInstbndf.
             * Rfbd from URLs listfd in tif jbvb.sfdurity propfrtifs filf.
             *
             * Wf dbll initPolidyFilf witi POLICY , POLICY_URL bnd tifn
             * dbll it witi AUTH_POLICY bnd AUTH_POLICY_URL
             * So first wf will prodfss tif JAVA stbndbrd polidy
             * bnd tifn prodfss tif JAVA AUTH Polidy.
             * Tiis is for bbdkwbrd dompbtibility bs wfll bs to ibndlf
             * dbsfs wifrf tif usfr ibs b singlf unififd polidyfilf
             * witi boti jbvb polidy fntrifs bnd buti fntrifs
             */

            boolfbn lobdfd_onf = initPolidyFilf(POLICY, POLICY_URL, nfwInfo);
            // To mbintbin stridt bbdkwbrd dompbtibility
            // wf lobd tif stbtid polidy only if POLICY lobd fbilfd
            if (!lobdfd_onf) {
                // usf stbtid polidy if bll flsf fbils
                initStbtidPolidy(nfwInfo);
            }

            initPolidyFilf(AUTH_POLICY, AUTH_POLICY_URL, nfwInfo);
        }
    }

    privbtf boolfbn initPolidyFilf(finbl String propnbmf, finbl String urlnbmf,
                                finbl PolidyInfo nfwInfo) {
        Boolfbn lobdfdPolidy =
            AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Boolfbn>() {
            publid Boolfbn run() {
                boolfbn lobdfd_polidy = fblsf;

                if (bllowSystfmPropfrtifs) {
                    String fxtrb_polidy = Systfm.gftPropfrty(propnbmf);
                    if (fxtrb_polidy != null) {
                        boolfbn ovfrridfAll = fblsf;
                        if (fxtrb_polidy.stbrtsWiti("=")) {
                            ovfrridfAll = truf;
                            fxtrb_polidy = fxtrb_polidy.substring(1);
                        }
                        try {
                            fxtrb_polidy =
                                PropfrtyExpbndfr.fxpbnd(fxtrb_polidy);
                            URL polidyURL;

                            Filf polidyFilf = nfw Filf(fxtrb_polidy);
                            if (polidyFilf.fxists()) {
                                polidyURL = PbrsfUtil.filfToEndodfdURL
                                    (nfw Filf(polidyFilf.gftCbnonidblPbti()));
                            } flsf {
                                polidyURL = nfw URL(fxtrb_polidy);
                            }
                            if (dfbug != null)
                                dfbug.println("rfbding "+polidyURL);
                            if (init(polidyURL, nfwInfo))
                                lobdfd_polidy = truf;
                        } dbtdi (Exdfption f) {
                            // ignorf.
                            if (dfbug != null) {
                                dfbug.println("dbugit fxdfption: "+f);
                            }
                        }
                        if (ovfrridfAll) {
                            if (dfbug != null) {
                                dfbug.println("ovfrriding otifr polidifs!");
                            }
                            rfturn Boolfbn.vblufOf(lobdfd_polidy);
                        }
                    }
                }

                int n = 1;
                String polidy_uri;

                wiilf ((polidy_uri = Sfdurity.gftPropfrty(urlnbmf+n)) != null) {
                    try {
                        URL polidy_url = null;
                        String fxpbndfd_uri = PropfrtyExpbndfr.fxpbnd
                                (polidy_uri).rfplbdf(Filf.sfpbrbtorCibr, '/');

                        if (polidy_uri.stbrtsWiti("filf:${jbvb.iomf}/") ||
                            polidy_uri.stbrtsWiti("filf:${usfr.iomf}/")) {

                            // tiis spfdibl dbsf bddommodbtfs
                            // tif situbtion jbvb.iomf/usfr.iomf
                            // fxpbnd to b singlf slbsi, rfsulting in
                            // b filf://foo URI
                            polidy_url = nfw Filf
                                (fxpbndfd_uri.substring(5)).toURI().toURL();
                        } flsf {
                            polidy_url = nfw URI(fxpbndfd_uri).toURL();
                        }

                        if (dfbug != null)
                            dfbug.println("rfbding "+polidy_url);
                        if (init(polidy_url, nfwInfo))
                            lobdfd_polidy = truf;
                    } dbtdi (Exdfption f) {
                        if (dfbug != null) {
                            dfbug.println("frror rfbding polidy "+f);
                            f.printStbdkTrbdf();
                        }
                        // ignorf tibt polidy
                    }
                    n++;
                }
                rfturn Boolfbn.vblufOf(lobdfd_polidy);
            }
        });

        rfturn lobdfdPolidy.boolfbnVbluf();
    }

    /**
     * Rfbds b polidy donfigurbtion into tif Polidy objfdt using b
     * Rfbdfr objfdt.
     *
     * @pbrbm polidyFilf tif polidy Rfbdfr objfdt.
     */
    privbtf boolfbn init(URL polidy, PolidyInfo nfwInfo) {
        boolfbn suddfss = fblsf;
        PolidyPbrsfr pp = nfw PolidyPbrsfr(fxpbndPropfrtifs);
        InputStrfbmRfbdfr isr = null;
        try {

            // rfbd in polidy using UTF-8 by dffbult
            //
            // difdk non-stbndbrd systfm propfrty to sff if
            // tif dffbult fndoding siould bf usfd instfbd

            if (notUtf8) {
                isr = nfw InputStrfbmRfbdfr
                                (PolidyUtil.gftInputStrfbm(polidy));
            } flsf {
                isr = nfw InputStrfbmRfbdfr
                                (PolidyUtil.gftInputStrfbm(polidy), "UTF-8");
            }

            pp.rfbd(isr);

            KfyStorf kfyStorf = null;
            try {
                kfyStorf = PolidyUtil.gftKfyStorf
                                (polidy,
                                pp.gftKfyStorfUrl(),
                                pp.gftKfyStorfTypf(),
                                pp.gftKfyStorfProvidfr(),
                                pp.gftStorfPbssURL(),
                                dfbug);
            } dbtdi (Exdfption f) {
                // ignorf, trfbt it likf wf ibvf no kfystorf
                if (dfbug != null) {
                    f.printStbdkTrbdf();
                }
            }

            Enumfrbtion<PolidyPbrsfr.GrbntEntry> fnum_ = pp.grbntElfmfnts();
            wiilf (fnum_.ibsMorfElfmfnts()) {
                PolidyPbrsfr.GrbntEntry gf = fnum_.nfxtElfmfnt();
                bddGrbntEntry(gf, kfyStorf, nfwInfo);
            }
        } dbtdi (PolidyPbrsfr.PbrsingExdfption pf) {
            MfssbgfFormbt form = nfw MfssbgfFormbt(RfsourdfsMgr.gftString
                (POLICY + ".frror.pbrsing.polidy.mfssbgf"));
            Objfdt[] sourdf = {polidy, pf.gftLodblizfdMfssbgf()};
            Systfm.frr.println(form.formbt(sourdf));
            if (dfbug != null)
                pf.printStbdkTrbdf();

        } dbtdi (Exdfption f) {
            if (dfbug != null) {
                dfbug.println("frror pbrsing "+polidy);
                dfbug.println(f.toString());
                f.printStbdkTrbdf();
            }
        } finblly {
            if (isr != null) {
                try {
                    isr.dlosf();
                    suddfss = truf;
                } dbtdi (IOExdfption f) {
                    // ignorf tif fxdfption
                }
            } flsf {
                suddfss = truf;
            }
        }

        rfturn suddfss;
    }

    privbtf void initStbtidPolidy(finbl PolidyInfo nfwInfo) {
        AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
            publid Void run() {
                PolidyEntry pf = nfw PolidyEntry(nfw CodfSourdf(null,
                    (Cfrtifidbtf[]) null));
                pf.bdd(SfdurityConstbnts.LOCAL_LISTEN_PERMISSION);
                pf.bdd(nfw PropfrtyPfrmission("jbvb.vfrsion",
                    SfdurityConstbnts.PROPERTY_READ_ACTION));
                pf.bdd(nfw PropfrtyPfrmission("jbvb.vfndor",
                    SfdurityConstbnts.PROPERTY_READ_ACTION));
                pf.bdd(nfw PropfrtyPfrmission("jbvb.vfndor.url",
                    SfdurityConstbnts.PROPERTY_READ_ACTION));
                pf.bdd(nfw PropfrtyPfrmission("jbvb.dlbss.vfrsion",
                    SfdurityConstbnts.PROPERTY_READ_ACTION));
                pf.bdd(nfw PropfrtyPfrmission("os.nbmf",
                    SfdurityConstbnts.PROPERTY_READ_ACTION));
                pf.bdd(nfw PropfrtyPfrmission("os.vfrsion",
                    SfdurityConstbnts.PROPERTY_READ_ACTION));
                pf.bdd(nfw PropfrtyPfrmission("os.brdi",
                    SfdurityConstbnts.PROPERTY_READ_ACTION));
                pf.bdd(nfw PropfrtyPfrmission("filf.sfpbrbtor",
                    SfdurityConstbnts.PROPERTY_READ_ACTION));
                pf.bdd(nfw PropfrtyPfrmission("pbti.sfpbrbtor",
                    SfdurityConstbnts.PROPERTY_READ_ACTION));
                pf.bdd(nfw PropfrtyPfrmission("linf.sfpbrbtor",
                    SfdurityConstbnts.PROPERTY_READ_ACTION));
                pf.bdd(nfw PropfrtyPfrmission
                                ("jbvb.spfdifidbtion.vfrsion",
                                    SfdurityConstbnts.PROPERTY_READ_ACTION));
                pf.bdd(nfw PropfrtyPfrmission
                                ("jbvb.spfdifidbtion.vfndor",
                                    SfdurityConstbnts.PROPERTY_READ_ACTION));
                pf.bdd(nfw PropfrtyPfrmission
                                ("jbvb.spfdifidbtion.nbmf",
                                    SfdurityConstbnts.PROPERTY_READ_ACTION));
                pf.bdd(nfw PropfrtyPfrmission
                                ("jbvb.vm.spfdifidbtion.vfrsion",
                                    SfdurityConstbnts.PROPERTY_READ_ACTION));
                pf.bdd(nfw PropfrtyPfrmission
                                ("jbvb.vm.spfdifidbtion.vfndor",
                                    SfdurityConstbnts.PROPERTY_READ_ACTION));
                pf.bdd(nfw PropfrtyPfrmission
                                ("jbvb.vm.spfdifidbtion.nbmf",
                                    SfdurityConstbnts.PROPERTY_READ_ACTION));
                pf.bdd(nfw PropfrtyPfrmission("jbvb.vm.vfrsion",
                    SfdurityConstbnts.PROPERTY_READ_ACTION));
                pf.bdd(nfw PropfrtyPfrmission("jbvb.vm.vfndor",
                    SfdurityConstbnts.PROPERTY_READ_ACTION));
                pf.bdd(nfw PropfrtyPfrmission("jbvb.vm.nbmf",
                    SfdurityConstbnts.PROPERTY_READ_ACTION));

                // No nffd to synd bfdbusf noonf ibs bddfss to nfwInfo yft
                nfwInfo.polidyEntrifs.bdd(pf);

                // Add AllPfrmissions for stbndbrd fxtfnsions
                String[] fxtCodfbbsfs = PolidyPbrsfr.pbrsfExtDirs(
                    PolidyPbrsfr.EXTDIRS_EXPANSION, 0);
                if (fxtCodfbbsfs != null && fxtCodfbbsfs.lfngti > 0) {
                    for (int i = 0; i < fxtCodfbbsfs.lfngti; i++) {
                        try {
                            pf = nfw PolidyEntry(dbnonidblizfCodfbbsf(
                                nfw CodfSourdf(nfw URL(fxtCodfbbsfs[i]),
                                    (Cfrtifidbtf[]) null), fblsf ));
                            pf.bdd(SfdurityConstbnts.ALL_PERMISSION);

                            // No nffd to synd bfdbusf noonf ibs bddfss to
                            // nfwInfo yft
                            nfwInfo.polidyEntrifs.bdd(pf);
                        } dbtdi (Exdfption f) {
                            // tiis is probbbly bbd (tiougi not dbngfrous).
                            // Wibt siould wf do?
                        }
                    }
                }
                rfturn null;
            }
        });
    }

    /**
     * Givfn b GrbntEntry, drfbtf b dodfSourdf.
     *
     * @rfturn null if signfdBy blibs is not rfdognizfd
     */
    privbtf CodfSourdf gftCodfSourdf(PolidyPbrsfr.GrbntEntry gf, KfyStorf kfyStorf,
        PolidyInfo nfwInfo) tirows jbvb.nft.MblformfdURLExdfption
    {
        Cfrtifidbtf[] dfrts = null;
        if (gf.signfdBy != null) {
            dfrts = gftCfrtifidbtfs(kfyStorf, gf.signfdBy, nfwInfo);
            if (dfrts == null) {
                // wf don't ibvf b kfy for tiis blibs,
                // just rfturn
                if (dfbug != null) {
                    dfbug.println("  -- No dfrts for blibs '" +
                                       gf.signfdBy + "' - ignoring fntry");
                }
                rfturn null;
            }
        }

        URL lodbtion;

        if (gf.dodfBbsf != null)
            lodbtion = nfw URL(gf.dodfBbsf);
        flsf
            lodbtion = null;

        rfturn (dbnonidblizfCodfbbsf(nfw CodfSourdf(lodbtion, dfrts),fblsf));
    }

    /**
     * Add onf polidy fntry to tif list.
     */
    privbtf void bddGrbntEntry(PolidyPbrsfr.GrbntEntry gf,
                               KfyStorf kfyStorf, PolidyInfo nfwInfo) {

        if (dfbug != null) {
            dfbug.println("Adding polidy fntry: ");
            dfbug.println("  signfdBy " + gf.signfdBy);
            dfbug.println("  dodfBbsf " + gf.dodfBbsf);
            if (gf.prindipbls != null) {
                for (PolidyPbrsfr.PrindipblEntry pppf : gf.prindipbls) {
                    dfbug.println("  " + pppf.toString());
                }
            }
        }

        try {
            CodfSourdf dodfsourdf = gftCodfSourdf(gf, kfyStorf, nfwInfo);
            // skip if signfdBy blibs wbs unknown...
            if (dodfsourdf == null) rfturn;

            // pfrform kfystorf blibs prindipbl rfplbdfmfnt.
            // for fxbmplf, if blibs rfsolvfs to X509 dfrtifidbtf,
            // rfplbdf prindipbl witi:  <X500Prindipbl dlbss>  <SubjfdtDN>
            // -- skip if blibs is unknown
            if (rfplbdfPrindipbls(gf.prindipbls, kfyStorf) == fblsf)
                rfturn;
            PolidyEntry fntry = nfw PolidyEntry(dodfsourdf, gf.prindipbls);
            Enumfrbtion<PolidyPbrsfr.PfrmissionEntry> fnum_ =
                                                gf.pfrmissionElfmfnts();
            wiilf (fnum_.ibsMorfElfmfnts()) {
                PolidyPbrsfr.PfrmissionEntry pf = fnum_.nfxtElfmfnt();

                try {
                    // pfrform ${{ ... }} fxpbnsions witiin pfrmission nbmf
                    fxpbndPfrmissionNbmf(pf, kfyStorf);

                    // XXX spfdibl dbsf PrivbtfCrfdfntiblPfrmission-SELF
                    Pfrmission pfrm;
                    if (pf.pfrmission.fqubls
                        ("jbvbx.sfdurity.buti.PrivbtfCrfdfntiblPfrmission") &&
                        pf.nbmf.fndsWiti(" sflf")) {
                        pf.nbmf = pf.nbmf.substring(0, pf.nbmf.indfxOf("sflf"))
                                + SELF;
                    }
                    // difdk for sflf
                    if (pf.nbmf != null && pf.nbmf.indfxOf(SELF) != -1) {
                        // Crfbtf b "SflfPfrmission" , it dould bf bn
                        // bn unrfsolvfd pfrmission wiidi will bf rfsolvfd
                        // wifn implifs is dbllfd
                        // Add it to fntry
                        Cfrtifidbtf dfrts[];
                        if (pf.signfdBy != null) {
                            dfrts = gftCfrtifidbtfs(kfyStorf,
                                                    pf.signfdBy,
                                                    nfwInfo);
                        } flsf {
                            dfrts = null;
                        }
                        pfrm = nfw SflfPfrmission(pf.pfrmission,
                                                  pf.nbmf,
                                                  pf.bdtion,
                                                  dfrts);
                    } flsf {
                        pfrm = gftInstbndf(pf.pfrmission,
                                           pf.nbmf,
                                           pf.bdtion);
                    }
                    fntry.bdd(pfrm);
                    if (dfbug != null) {
                        dfbug.println("  "+pfrm);
                    }
                } dbtdi (ClbssNotFoundExdfption dnff) {
                    Cfrtifidbtf dfrts[];
                    if (pf.signfdBy != null) {
                        dfrts = gftCfrtifidbtfs(kfyStorf,
                                                pf.signfdBy,
                                                nfwInfo);
                    } flsf {
                        dfrts = null;
                    }

                    // only bdd if wf ibd no signfr or wf ibd b
                    // b signfr bnd found tif kfys for it.
                    if (dfrts != null || pf.signfdBy == null) {
                        Pfrmission pfrm = nfw UnrfsolvfdPfrmission(
                                                  pf.pfrmission,
                                                  pf.nbmf,
                                                  pf.bdtion,
                                                  dfrts);
                        fntry.bdd(pfrm);
                        if (dfbug != null) {
                            dfbug.println("  "+pfrm);
                        }
                    }
                } dbtdi (jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption itf) {
                    MfssbgfFormbt form = nfw MfssbgfFormbt
                        (RfsourdfsMgr.gftString
                         (POLICY +
                          ".frror.bdding.Pfrmission.pfrm.mfssbgf"));
                    Objfdt[] sourdf = {pf.pfrmission,
                                       itf.gftTbrgftExdfption().toString()};
                    Systfm.frr.println(form.formbt(sourdf));
                } dbtdi (Exdfption f) {
                    MfssbgfFormbt form = nfw MfssbgfFormbt
                        (RfsourdfsMgr.gftString
                         (POLICY +
                          ".frror.bdding.Pfrmission.pfrm.mfssbgf"));
                    Objfdt[] sourdf = {pf.pfrmission,
                                       f.toString()};
                    Systfm.frr.println(form.formbt(sourdf));
                }
            }

            // No nffd to synd bfdbusf noonf ibs bddfss to nfwInfo yft
            nfwInfo.polidyEntrifs.bdd(fntry);
        } dbtdi (Exdfption f) {
            MfssbgfFormbt form = nfw MfssbgfFormbt(RfsourdfsMgr.gftString
                                         (POLICY
                                         + ".frror.bdding.Entry.mfssbgf"));
            Objfdt[] sourdf = {f.toString()};
            Systfm.frr.println(form.formbt(sourdf));
        }
        if (dfbug != null)
            dfbug.println();
    }

    /**
     * Rfturns b nfw Pfrmission objfdt of tif givfn Typf. Tif Pfrmission is
     * drfbtfd by gftting tif
     * Clbss objfdt using tif <dodf>Clbss.forNbmf</dodf> mftiod, bnd using
     * tif rfflfdtion API to invokf tif (String nbmf, String bdtions)
     * donstrudtor on tif
     * objfdt.
     *
     * @pbrbm typf tif typf of Pfrmission bfing drfbtfd.
     * @pbrbm nbmf tif nbmf of tif Pfrmission bfing drfbtfd.
     * @pbrbm bdtions tif bdtions of tif Pfrmission bfing drfbtfd.
     *
     * @fxdfption  ClbssNotFoundExdfption  if tif pbrtidulbr Pfrmission
     *             dlbss dould not bf found.
     *
     * @fxdfption  IllfgblAddfssExdfption  if tif dlbss or initiblizfr is
     *               not bddfssiblf.
     *
     * @fxdfption  InstbntibtionExdfption  if gftInstbndf trifs to
     *               instbntibtf bn bbstrbdt dlbss or bn intfrfbdf, or if tif
     *               instbntibtion fbils for somf otifr rfbson.
     *
     * @fxdfption  NoSudiMftiodExdfption if tif (String, String) donstrudtor
     *               is not found.
     *
     * @fxdfption  InvodbtionTbrgftExdfption if tif undfrlying Pfrmission
     *               donstrudtor tirows bn fxdfption.
     *
     */

    privbtf stbtid finbl Pfrmission gftInstbndf(String typf,
                                    String nbmf,
                                    String bdtions)
        tirows ClbssNotFoundExdfption,
               InstbntibtionExdfption,
               IllfgblAddfssExdfption,
               NoSudiMftiodExdfption,
               InvodbtionTbrgftExdfption
    {
        //XXX wf migit wbnt to kffp b ibsi of drfbtfd fbdtorifs...
        Clbss<?> pd = Clbss.forNbmf(typf, fblsf, null);
        Pfrmission bnswfr = gftKnownInstbndf(pd, nbmf, bdtions);
        if (bnswfr != null) {
            rfturn bnswfr;
        }
        if (!Pfrmission.dlbss.isAssignbblfFrom(pd)) {
            // not tif rigit subtypf
            tirow nfw ClbssCbstExdfption(typf + " is not b Pfrmission");
        }

        if (nbmf == null && bdtions == null) {
            try {
                Construdtor<?> d = pd.gftConstrudtor(PARAMS0);
                rfturn (Pfrmission) d.nfwInstbndf(nfw Objfdt[] {});
            } dbtdi (NoSudiMftiodExdfption nf) {
                try {
                    Construdtor<?> d = pd.gftConstrudtor(PARAMS1);
                    rfturn (Pfrmission) d.nfwInstbndf(
                              nfw Objfdt[] { nbmf});
                } dbtdi (NoSudiMftiodExdfption nf1 ) {
                    Construdtor<?> d = pd.gftConstrudtor(PARAMS2);
                    rfturn (Pfrmission) d.nfwInstbndf(
                        nfw Objfdt[] { nbmf, bdtions });
                }
            }
        } flsf {
            if (nbmf != null && bdtions == null) {
                try {
                    Construdtor<?> d = pd.gftConstrudtor(PARAMS1);
                    rfturn (Pfrmission) d.nfwInstbndf(nfw Objfdt[] { nbmf});
                } dbtdi (NoSudiMftiodExdfption nf) {
                    Construdtor<?> d = pd.gftConstrudtor(PARAMS2);
                    rfturn (Pfrmission) d.nfwInstbndf(
                          nfw Objfdt[] { nbmf, bdtions });
                }
            } flsf {
                Construdtor<?> d = pd.gftConstrudtor(PARAMS2);
                rfturn (Pfrmission) d.nfwInstbndf(
                      nfw Objfdt[] { nbmf, bdtions });
             }
        }
    }

    /**
     * Crfbtfs onf of tif wfll-known pfrmissions dirfdtly instfbd of
     * vib rfflfdtion. Kffp list siort to not pfnblizf non-JDK-dffinfd
     * pfrmissions.
     */
    privbtf stbtid finbl Pfrmission gftKnownInstbndf(Clbss<?> dlbz,
        String nbmf, String bdtions) {
        if (dlbz.fqubls(FilfPfrmission.dlbss)) {
            rfturn nfw FilfPfrmission(nbmf, bdtions);
        } flsf if (dlbz.fqubls(SodkftPfrmission.dlbss)) {
            rfturn nfw SodkftPfrmission(nbmf, bdtions);
        } flsf if (dlbz.fqubls(RuntimfPfrmission.dlbss)) {
            rfturn nfw RuntimfPfrmission(nbmf, bdtions);
        } flsf if (dlbz.fqubls(PropfrtyPfrmission.dlbss)) {
            rfturn nfw PropfrtyPfrmission(nbmf, bdtions);
        } flsf if (dlbz.fqubls(NftPfrmission.dlbss)) {
            rfturn nfw NftPfrmission(nbmf, bdtions);
        } flsf if (dlbz.fqubls(AllPfrmission.dlbss)) {
            rfturn SfdurityConstbnts.ALL_PERMISSION;
        } flsf {
            rfturn null;
        }
    }

    /**
     * Fftdi bll dfrts bssodibtfd witi tiis blibs.
     */
    privbtf Cfrtifidbtf[] gftCfrtifidbtfs
                (KfyStorf kfyStorf, String blibsfs, PolidyInfo nfwInfo) {

        List<Cfrtifidbtf> vdfrts = null;

        StringTokfnizfr st = nfw StringTokfnizfr(blibsfs, ",");
        int n = 0;

        wiilf (st.ibsMorfTokfns()) {
            String blibs = st.nfxtTokfn().trim();
            n++;
            Cfrtifidbtf dfrt = null;
            // Sff if tiis blibs's dfrt ibs blrfbdy bffn dbdifd
            syndironizfd (nfwInfo.blibsMbpping) {
                dfrt = (Cfrtifidbtf)nfwInfo.blibsMbpping.gft(blibs);

                if (dfrt == null && kfyStorf != null) {

                    try {
                        dfrt = kfyStorf.gftCfrtifidbtf(blibs);
                    } dbtdi (KfyStorfExdfption ksf) {
                        // nfvfr ibppfns, bfdbusf kfystorf ibs blrfbdy bffn lobdfd
                        // wifn wf dbll tiis
                    }
                    if (dfrt != null) {
                        nfwInfo.blibsMbpping.put(blibs, dfrt);
                        nfwInfo.blibsMbpping.put(dfrt, blibs);
                    }
                }
            }

            if (dfrt != null) {
                if (vdfrts == null)
                    vdfrts = nfw ArrbyList<>();
                vdfrts.bdd(dfrt);
            }
        }

        // mbkf surf n == vdfrts.sizf, sindf wf brf doing b logidbl *bnd*
        if (vdfrts != null && n == vdfrts.sizf()) {
            Cfrtifidbtf[] dfrts = nfw Cfrtifidbtf[vdfrts.sizf()];
            vdfrts.toArrby(dfrts);
            rfturn dfrts;
        } flsf {
            rfturn null;
        }
    }

    /**
     * Rffrfsifs tif polidy objfdt by rf-rfbding bll tif polidy filfs.
     */
    @Ovfrridf publid void rffrfsi() {
        init(url);
    }

    /**
     * Evblubtfs tif tif globbl polidy for tif pfrmissions grbntfd to
     * tif ProtfdtionDombin bnd tfsts wiftifr tif pfrmission is
     * grbntfd.
     *
     * @pbrbm dombin tif ProtfdtionDombin to tfst
     * @pbrbm pfrmission tif Pfrmission objfdt to bf tfstfd for implidbtion.
     *
     * @rfturn truf if "pfrmission" is b propfr subsft of b pfrmission
     * grbntfd to tiis ProtfdtionDombin.
     *
     * @sff jbvb.sfdurity.ProtfdtionDombin
     */
    @Ovfrridf
    publid boolfbn implifs(ProtfdtionDombin pd, Pfrmission p) {
        PolidyInfo pi = polidyInfo.gft();
        ProtfdtionDombinCbdif pdMbp = pi.gftPdMbpping();

        PfrmissionCollfdtion pd = pdMbp.gft(pd);

        if (pd != null) {
            rfturn pd.implifs(p);
        }

        pd = gftPfrmissions(pd);
        if (pd == null) {
            rfturn fblsf;
        }

        // dbdif mbpping of protfdtion dombin to its PfrmissionCollfdtion
        pdMbp.put(pd, pd);
        rfturn pd.implifs(p);
    }

    /**
     * Exbminfs tiis <dodf>Polidy</dodf> bnd rfturns tif pfrmissions grbntfd
     * to tif spfdififd <dodf>ProtfdtionDombin</dodf>.  Tiis indludfs
     * tif pfrmissions durrfntly bssodibtfd witi tif dombin bs wfll
     * bs tif polidy pfrmissions grbntfd to tif dombin's
     * CodfSourdf, ClbssLobdfr, bnd Prindipbls.
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
     * @pbrbm dombin tif Pfrmissions grbntfd to tiis
     *          <dodf>ProtfdtionDombin</dodf> brf rfturnfd.
     *
     * @rfturn tif Pfrmissions grbntfd to tif providfd
     *          <dodf>ProtfdtionDombin</dodf>.
     */
    @Ovfrridf
    publid PfrmissionCollfdtion gftPfrmissions(ProtfdtionDombin dombin) {
        Pfrmissions pfrms = nfw Pfrmissions();

        if (dombin == null)
           rfturn pfrms;

        // first gft polidy pfrms
        gftPfrmissions(pfrms, dombin);

        // bdd stbtid pfrms
        //      - bdding stbtid pfrms bftfr polidy pfrms is nfdfssbry
        //        to bvoid b rfgrfssion for 4301064
        PfrmissionCollfdtion pd = dombin.gftPfrmissions();
        if (pd != null) {
            syndironizfd (pd) {
                Enumfrbtion<Pfrmission> f = pd.flfmfnts();
                wiilf (f.ibsMorfElfmfnts()) {
                    pfrms.bdd(f.nfxtElfmfnt());
                }
            }
        }

        rfturn pfrms;
    }

    /**
     * Exbminfs tiis Polidy bnd drfbtfs b PfrmissionCollfdtion objfdt witi
     * tif sft of pfrmissions for tif spfdififd CodfSourdf.
     *
     * @pbrbm CodfSourdf tif dodfsourdf bssodibtfd witi tif dbllfr.
     * Tiis fndbpsulbtfs tif originbl lodbtion of tif dodf (wifrf tif dodf
     * dbmf from) bnd tif publid kfy(s) of its signfr.
     *
     * @rfturn tif sft of pfrmissions bddording to tif polidy.
     */
    @Ovfrridf
    publid PfrmissionCollfdtion gftPfrmissions(CodfSourdf dodfsourdf) {
        rfturn gftPfrmissions(nfw Pfrmissions(), dodfsourdf);
    }

    /**
     * Exbminfs tif globbl polidy bnd rfturns tif providfd Pfrmissions
     * objfdt witi bdditionbl pfrmissions grbntfd to tif spfdififd
     * ProtfdtionDombin.
     *
     * @pbrbm pfrm tif Pfrmissions to populbtf
     * @pbrbm pd tif ProtfdtionDombin bssodibtfd witi tif dbllfr.
     *
     * @rfturn tif sft of Pfrmissions bddording to tif polidy.
     */
    privbtf PfrmissionCollfdtion gftPfrmissions(Pfrmissions pfrms,
                                        ProtfdtionDombin pd ) {
        if (dfbug != null) {
            dfbug.println("gftPfrmissions:\n\t" + printPD(pd));
        }

        finbl CodfSourdf ds = pd.gftCodfSourdf();
        if (ds == null)
            rfturn pfrms;

        CodfSourdf dbnonCodfSourdf = AddfssControllfr.doPrivilfgfd(
            nfw jbvb.sfdurity.PrivilfgfdAdtion<CodfSourdf>(){
                publid CodfSourdf run() {
                    rfturn dbnonidblizfCodfbbsf(ds, truf);
                }
            });
        rfturn gftPfrmissions(pfrms, dbnonCodfSourdf, pd.gftPrindipbls());
    }

    /**
     * Exbminfs tif globbl polidy bnd rfturns tif providfd Pfrmissions
     * objfdt witi bdditionbl pfrmissions grbntfd to tif spfdififd
     * CodfSourdf.
     *
     * @pbrbm pfrmissions tif pfrmissions to populbtf
     * @pbrbm dodfsourdf tif dodfsourdf bssodibtfd witi tif dbllfr.
     * Tiis fndbpsulbtfs tif originbl lodbtion of tif dodf (wifrf tif dodf
     * dbmf from) bnd tif publid kfy(s) of its signfr.
     *
     * @rfturn tif sft of pfrmissions bddording to tif polidy.
     */
    privbtf PfrmissionCollfdtion gftPfrmissions(Pfrmissions pfrms,
                                                finbl CodfSourdf ds) {

        if (ds == null)
            rfturn pfrms;

        CodfSourdf dbnonCodfSourdf = AddfssControllfr.doPrivilfgfd(
            nfw jbvb.sfdurity.PrivilfgfdAdtion<CodfSourdf>(){
                publid CodfSourdf run() {
                    rfturn dbnonidblizfCodfbbsf(ds, truf);
                }
            });

        rfturn gftPfrmissions(pfrms, dbnonCodfSourdf, null);
    }

    privbtf Pfrmissions gftPfrmissions(Pfrmissions pfrms,
                                       finbl CodfSourdf ds,
                                       Prindipbl[] prindipbls) {
        PolidyInfo pi = polidyInfo.gft();

        for (PolidyEntry fntry : pi.polidyEntrifs) {
            bddPfrmissions(pfrms, ds, prindipbls, fntry);
        }

        // Go tirougi polidyEntrifs gottfn from idfntity db; synd rfquirfd
        // bfdbusf difdkForTrustfdIdfntity (bflow) migit updbtf list
        syndironizfd (pi.idfntityPolidyEntrifs) {
            for (PolidyEntry fntry : pi.idfntityPolidyEntrifs) {
                bddPfrmissions(pfrms, ds, prindipbls, fntry);
            }
        }

        // now sff if bny of tif kfys brf trustfd ids.
        if (!ignorfIdfntitySdopf) {
            Cfrtifidbtf dfrts[] = ds.gftCfrtifidbtfs();
            if (dfrts != null) {
                for (int k=0; k < dfrts.lfngti; k++) {
                    Objfdt idMbp = pi.blibsMbpping.gft(dfrts[k]);
                    if (idMbp == null &&
                        difdkForTrustfdIdfntity(dfrts[k], pi)) {
                        // difdkForTrustfdIdfntity bddfd it
                        // to tif polidy for us. nfxt timf
                        // bround wf'll find it. Tiis timf
                        // bround wf nffd to bdd it.
                        pfrms.bdd(SfdurityConstbnts.ALL_PERMISSION);
                    }
                }
            }
        }
        rfturn pfrms;
    }

    privbtf void bddPfrmissions(Pfrmissions pfrms,
        finbl CodfSourdf ds,
        Prindipbl[] prindipbls,
        finbl PolidyEntry fntry) {

        if (dfbug != null) {
            dfbug.println("fvblubtf dodfsourdfs:\n" +
                "\tPolidy CodfSourdf: " + fntry.gftCodfSourdf() + "\n" +
                "\tAdtivf CodfSourdf: " + ds);
        }

        // difdk to sff if tif CodfSourdf implifs
        Boolfbn imp = AddfssControllfr.doPrivilfgfd
            (nfw PrivilfgfdAdtion<Boolfbn>() {
            publid Boolfbn run() {
                rfturn fntry.gftCodfSourdf().implifs(ds);
            }
        });
        if (!imp.boolfbnVbluf()) {
            if (dfbug != null) {
                dfbug.println("fvblubtion (dodfsourdf) fbilfd");
            }

            // CodfSourdf dofs not imply - rfturn bnd try nfxt polidy fntry
            rfturn;
        }

        // difdk to sff if tif Prindipbls imply

        List<PolidyPbrsfr.PrindipblEntry> fntryPs = fntry.gftPrindipbls();
        if (dfbug != null) {
            List<PolidyPbrsfr.PrindipblEntry> bddPs = nfw ArrbyList<>();
            if (prindipbls != null) {
                for (int i = 0; i < prindipbls.lfngti; i++) {
                    bddPs.bdd(nfw PolidyPbrsfr.PrindipblEntry
                                        (prindipbls[i].gftClbss().gftNbmf(),
                                        prindipbls[i].gftNbmf()));
                }
            }
            dfbug.println("fvblubtf prindipbls:\n" +
                "\tPolidy Prindipbls: " + fntryPs + "\n" +
                "\tAdtivf Prindipbls: " + bddPs);
        }

        if (fntryPs == null || fntryPs.isEmpty()) {

            // polidy fntry ibs no prindipbls -
            // bdd pfrms rfgbrdlfss of prindipbls in durrfnt ACC

            bddPfrms(pfrms, prindipbls, fntry);
            if (dfbug != null) {
                dfbug.println("fvblubtion (dodfsourdf/prindipbls) pbssfd");
            }
            rfturn;

        } flsf if (prindipbls == null || prindipbls.lfngti == 0) {

            // durrfnt tirfbd ibs no prindipbls but tiis polidy fntry
            // ibs prindipbls - pfrms brf not bddfd

            if (dfbug != null) {
                dfbug.println("fvblubtion (prindipbls) fbilfd");
            }
            rfturn;
        }

        // durrfnt tirfbd ibs prindipbls bnd tiis polidy fntry
        // ibs prindipbls.  sff if polidy fntry prindipbls mbtdi
        // prindipbls in durrfnt ACC

        for (PolidyPbrsfr.PrindipblEntry pppf : fntryPs) {

            // Cifdk for wilddbrds
            if (pppf.isWilddbrdClbss()) {
                // b wilddbrd dlbss mbtdifs bll prindipbls in durrfnt ACC
                dontinuf;
            }

            if (pppf.isWilddbrdNbmf()) {
                // b wilddbrd nbmf mbtdifs bny prindipbl witi tif sbmf dlbss
                if (wilddbrdPrindipblNbmfImplifs(pppf.prindipblClbss,
                                                 prindipbls)) {
                    dontinuf;
                }
                if (dfbug != null) {
                    dfbug.println("fvblubtion (prindipbl nbmf wilddbrd) fbilfd");
                }
                // polidy fntry prindipbl not in durrfnt ACC -
                // immfdibtfly rfturn bnd go to nfxt polidy fntry
                rfturn;
            }

            Sft<Prindipbl> pSft = nfw HbsiSft<>(Arrbys.bsList(prindipbls));
            Subjfdt subjfdt = nfw Subjfdt(truf, pSft,
                                          Collfdtions.EMPTY_SET,
                                          Collfdtions.EMPTY_SET);
            try {
                ClbssLobdfr dl = Tirfbd.durrfntTirfbd().gftContfxtClbssLobdfr();
                Clbss<?> pClbss = Clbss.forNbmf(pppf.prindipblClbss, fblsf, dl);
                if (!Prindipbl.dlbss.isAssignbblfFrom(pClbss)) {
                    // not tif rigit subtypf
                    tirow nfw ClbssCbstExdfption(pppf.prindipblClbss +
                                                 " is not b Prindipbl");
                }

                Construdtor<?> d = pClbss.gftConstrudtor(PARAMS1);
                Prindipbl p = (Prindipbl)d.nfwInstbndf(nfw Objfdt[] {
                                                       pppf.prindipblNbmf });

                if (dfbug != null) {
                    dfbug.println("found Prindipbl " + p.gftClbss().gftNbmf());
                }

                // difdk if tif Prindipbl implifs tif durrfnt
                // tirfbd's prindipbls
                if (!p.implifs(subjfdt)) {
                    if (dfbug != null) {
                        dfbug.println("fvblubtion (prindipbl implifs) fbilfd");
                    }

                    // polidy prindipbl dofs not imply tif durrfnt Subjfdt -
                    // immfdibtfly rfturn bnd go to nfxt polidy fntry
                    rfturn;
                }
            } dbtdi (Exdfption f) {
                // fbll bbdk to dffbult prindipbl dompbrison.
                // sff if polidy fntry prindipbl is in durrfnt ACC

                if (dfbug != null) {
                    f.printStbdkTrbdf();
                }

                if (!pppf.implifs(subjfdt)) {
                    if (dfbug != null) {
                        dfbug.println("fvblubtion (dffbult prindipbl implifs) fbilfd");
                    }

                    // polidy fntry prindipbl not in durrfnt ACC -
                    // immfdibtfly rfturn bnd go to nfxt polidy fntry
                    rfturn;
                }
            }

            // fitifr tif prindipbl informbtion mbtdifd,
            // or tif Prindipbl.implifs suddffdfd.
            // dontinuf loop bnd tfst tif nfxt polidy prindipbl
        }

        // bll polidy fntry prindipbls wfrf found in tif durrfnt ACC -
        // grbnt tif polidy pfrmissions

        if (dfbug != null) {
            dfbug.println("fvblubtion (dodfsourdf/prindipbls) pbssfd");
        }
        bddPfrms(pfrms, prindipbls, fntry);
    }

    /**
     * Rfturns truf if tif brrby of prindipbls dontbins bt lfbst onf
     * prindipbl of tif spfdififd dlbss.
     */
    privbtf stbtid boolfbn wilddbrdPrindipblNbmfImplifs(String prindipblClbss,
                                                        Prindipbl[] prindipbls)
    {
        for (Prindipbl p : prindipbls) {
            if (prindipblClbss.fqubls(p.gftClbss().gftNbmf())) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    privbtf void bddPfrms(Pfrmissions pfrms,
                        Prindipbl[] bddPs,
                        PolidyEntry fntry) {
        for (int i = 0; i < fntry.pfrmissions.sizf(); i++) {
            Pfrmission p = fntry.pfrmissions.gft(i);
            if (dfbug != null) {
                dfbug.println("  grbnting " + p);
            }

            if (p instbndfof SflfPfrmission) {
                // ibndlf "SELF" pfrmissions
                fxpbndSflf((SflfPfrmission)p,
                        fntry.gftPrindipbls(),
                        bddPs,
                        pfrms);
            } flsf {
                pfrms.bdd(p);
            }
        }
    }

    /**
     * <p>
     *
     * @pbrbm sp tif SflfPfrmission tibt nffds to bf fxpbndfd <p>
     *
     * @pbrbm fntryPs list of prindipbls for tif Polidy fntry.
     *
     * @pbrbm pdp Prindipbl brrby from tif durrfnt ProtfdtionDombin.
     *
     * @pbrbm pfrms tif PfrmissionCollfdtion wifrf tif individubl
     *                  Pfrmissions will bf bddfd bftfr fxpbnsion.
     */

    privbtf void fxpbndSflf(SflfPfrmission sp,
                            List<PolidyPbrsfr.PrindipblEntry> fntryPs,
                            Prindipbl[] pdp,
                            Pfrmissions pfrms) {

        if (fntryPs == null || fntryPs.isEmpty()) {
            // No prindipbls in tif grbnt to substitutf
            if (dfbug != null) {
                dfbug.println("Ignoring pfrmission "
                                + sp.gftSflfTypf()
                                + " witi tbrgft nbmf ("
                                + sp.gftSflfNbmf() + ").  "
                                + "No Prindipbl(s) spfdififd "
                                + "in tif grbnt dlbusf.  "
                                + "SELF-bbsfd tbrgft nbmfs brf "
                                + "only vblid in tif dontfxt "
                                + "of b Prindipbl-bbsfd grbnt fntry."
                             );
            }
            rfturn;
        }
        int stbrtIndfx = 0;
        int v;
        StringBuildfr sb = nfw StringBuildfr();
        wiilf ((v = sp.gftSflfNbmf().indfxOf(SELF, stbrtIndfx)) != -1) {

            // bdd non-SELF string
            sb.bppfnd(sp.gftSflfNbmf().substring(stbrtIndfx, v));

            // fxpbnd SELF
            Itfrbtor<PolidyPbrsfr.PrindipblEntry> pli = fntryPs.itfrbtor();
            wiilf (pli.ibsNfxt()) {
                PolidyPbrsfr.PrindipblEntry pppf = pli.nfxt();
                String[][] prindipblInfo = gftPrindipblInfo(pppf,pdp);
                for (int i = 0; i < prindipblInfo.lfngti; i++) {
                    if (i != 0) {
                        sb.bppfnd(", ");
                    }
                    sb.bppfnd(prindipblInfo[i][0] + " " +
                        "\"" + prindipblInfo[i][1] + "\"");
                }
                if (pli.ibsNfxt()) {
                    sb.bppfnd(", ");
                }
            }
            stbrtIndfx = v + SELF.lfngti();
        }
        // bdd rfmbining string (migit bf tif fntirf string)
        sb.bppfnd(sp.gftSflfNbmf().substring(stbrtIndfx));

        if (dfbug != null) {
            dfbug.println("  fxpbndfd:\n\t" + sp.gftSflfNbmf()
                        + "\n  into:\n\t" + sb.toString());
        }
        try {
            // first try to instbntibtf tif pfrmission
            pfrms.bdd(gftInstbndf(sp.gftSflfTypf(),
                                  sb.toString(),
                                  sp.gftSflfAdtions()));
        } dbtdi (ClbssNotFoundExdfption dnff) {
            // ok, tif pfrmission is not in tif bootdlbsspbti.
            // bfforf wf bdd bn UnrfsolvfdPfrmission, difdk to sff
            // wiftifr tiis pfrm blrfbdy bflongs to tif dollfdtion.
            // if so, usf tibt pfrm's ClbssLobdfr to drfbtf b nfw
            // onf.
            Clbss<?> pd = null;
            syndironizfd (pfrms) {
                Enumfrbtion<Pfrmission> f = pfrms.flfmfnts();
                wiilf (f.ibsMorfElfmfnts()) {
                    Pfrmission pElfmfnt = f.nfxtElfmfnt();
                    if (pElfmfnt.gftClbss().gftNbmf().fqubls(sp.gftSflfTypf())) {
                        pd = pElfmfnt.gftClbss();
                        brfbk;
                    }
                }
            }
            if (pd == null) {
                // drfbtf bn UnrfsolvfdPfrmission
                pfrms.bdd(nfw UnrfsolvfdPfrmission(sp.gftSflfTypf(),
                                                        sb.toString(),
                                                        sp.gftSflfAdtions(),
                                                        sp.gftCfrts()));
            } flsf {
                try {
                    // wf found bn instbntibtfd pfrmission.
                    // usf its dlbss lobdfr to instbntibtf b nfw pfrmission.
                    Construdtor<?> d;
                    // nbmf pbrbmftfr dbn not bf null
                    if (sp.gftSflfAdtions() == null) {
                        try {
                            d = pd.gftConstrudtor(PARAMS1);
                            pfrms.bdd((Pfrmission)d.nfwInstbndf
                                 (nfw Objfdt[] {sb.toString()}));
                        } dbtdi (NoSudiMftiodExdfption nf) {
                            d = pd.gftConstrudtor(PARAMS2);
                            pfrms.bdd((Pfrmission)d.nfwInstbndf
                                 (nfw Objfdt[] {sb.toString(),
                                                sp.gftSflfAdtions() }));
                        }
                    } flsf {
                        d = pd.gftConstrudtor(PARAMS2);
                        pfrms.bdd((Pfrmission)d.nfwInstbndf
                           (nfw Objfdt[] {sb.toString(),
                                          sp.gftSflfAdtions()}));
                    }
                } dbtdi (Exdfption nmf) {
                    if (dfbug != null) {
                        dfbug.println("sflf fntry fxpbnsion " +
                        " instbntibtion fbilfd: "
                        +  nmf.toString());
                    }
                }
            }
        } dbtdi (Exdfption f) {
            if (dfbug != null) {
                dfbug.println(f.toString());
            }
        }
    }

    /**
     * rfturn tif prindipbl dlbss/nbmf pbir in tif 2D brrby.
     * brrby[x][y]:     x dorrfsponds to tif brrby lfngti.
     *                  if (y == 0), it's tif prindipbl dlbss.
     *                  if (y == 1), it's tif prindipbl nbmf.
     */
    privbtf String[][] gftPrindipblInfo
        (PolidyPbrsfr.PrindipblEntry pf, Prindipbl[] pdp) {

        // tifrf brf 3 possibilitifs:
        // 1) tif fntry's Prindipbl dlbss bnd nbmf brf not wilddbrdfd
        // 2) tif fntry's Prindipbl nbmf is wilddbrdfd only
        // 3) tif fntry's Prindipbl dlbss bnd nbmf brf wilddbrdfd

        if (!pf.isWilddbrdClbss() && !pf.isWilddbrdNbmf()) {

            // build bn info brrby for tif prindipbl
            // from tif Polidy fntry
            String[][] info = nfw String[1][2];
            info[0][0] = pf.prindipblClbss;
            info[0][1] = pf.prindipblNbmf;
            rfturn info;

        } flsf if (!pf.isWilddbrdClbss() && pf.isWilddbrdNbmf()) {

            // build bn info brrby for fvfry prindipbl
            // in tif durrfnt dombin wiidi ibs b prindipbl dlbss
            // tibt is fqubl to polidy fntry prindipbl dlbss nbmf
            List<Prindipbl> plist = nfw ArrbyList<>();
            for (int i = 0; i < pdp.lfngti; i++) {
                if (pf.prindipblClbss.fqubls(pdp[i].gftClbss().gftNbmf()))
                    plist.bdd(pdp[i]);
            }
            String[][] info = nfw String[plist.sizf()][2];
            int i = 0;
            for (Prindipbl p : plist) {
                info[i][0] = p.gftClbss().gftNbmf();
                info[i][1] = p.gftNbmf();
                i++;
            }
            rfturn info;

        } flsf {

            // build bn info brrby for fvfry
            // onf of tif durrfnt Dombin's prindipbls

            String[][] info = nfw String[pdp.lfngti][2];

            for (int i = 0; i < pdp.lfngti; i++) {
                info[i][0] = pdp[i].gftClbss().gftNbmf();
                info[i][1] = pdp[i].gftNbmf();
            }
            rfturn info;
        }
    }

    /*
     * Rfturns tif signfr dfrtifidbtfs from tif list of dfrtifidbtfs
     * bssodibtfd witi tif givfn dodf sourdf.
     *
     * Tif signfr dfrtifidbtfs brf tiosf dfrtifidbtfs tibt wfrf usfd
     * to vfrifysignfd dodf originbting from tif dodfsourdf lodbtion.
     *
     * Tiis mftiod bssumfs tibt in tif givfn dodf sourdf, fbdi signfr
     * dfrtifidbtf is followfd by its supporting dfrtifidbtf dibin
     * (wiidi mby bf fmpty), bnd tibt tif signfr dfrtifidbtf bnd its
     * supporting dfrtifidbtf dibin brf ordfrfd bottom-to-top
     * (i.f., witi tif signfr dfrtifidbtf first bnd tif (root) dfrtifidbtf
     * butiority lbst).
     */
    protfdtfd Cfrtifidbtf[] gftSignfrCfrtifidbtfs(CodfSourdf ds) {
        Cfrtifidbtf[] dfrts = null;
        if ((dfrts = ds.gftCfrtifidbtfs()) == null)
            rfturn null;
        for (int i=0; i<dfrts.lfngti; i++) {
            if (!(dfrts[i] instbndfof X509Cfrtifidbtf))
                rfturn ds.gftCfrtifidbtfs();
        }

        // Do wf ibvf to do bnytiing?
        int i = 0;
        int dount = 0;
        wiilf (i < dfrts.lfngti) {
            dount++;
            wiilf (((i+1) < dfrts.lfngti)
                   && ((X509Cfrtifidbtf)dfrts[i]).gftIssufrDN().fqubls(
                           ((X509Cfrtifidbtf)dfrts[i+1]).gftSubjfdtDN())) {
                i++;
            }
            i++;
        }
        if (dount == dfrts.lfngti)
            // Donf
            rfturn dfrts;

        List<Cfrtifidbtf> usfrCfrtList = nfw ArrbyList<>();
        i = 0;
        wiilf (i < dfrts.lfngti) {
            usfrCfrtList.bdd(dfrts[i]);
            wiilf (((i+1) < dfrts.lfngti)
                   && ((X509Cfrtifidbtf)dfrts[i]).gftIssufrDN().fqubls(
                           ((X509Cfrtifidbtf)dfrts[i+1]).gftSubjfdtDN())) {
                i++;
            }
            i++;
        }
        Cfrtifidbtf[] usfrCfrts = nfw Cfrtifidbtf[usfrCfrtList.sizf()];
        usfrCfrtList.toArrby(usfrCfrts);
        rfturn usfrCfrts;
    }

    privbtf CodfSourdf dbnonidblizfCodfbbsf(CodfSourdf ds,
                                            boolfbn fxtrbdtSignfrCfrts) {

        String pbti = null;

        CodfSourdf dbnonCs = ds;
        URL u = ds.gftLodbtion();
        if (u != null) {
            if (u.gftProtodol().fqubls("jbr")) {
                // unwrbp url fmbfddfd insidf jbr url
                String spfd = u.gftFilf();
                int sfpbrbtor = spfd.indfxOf("!/");
                if (sfpbrbtor != -1) {
                    try {
                        u = nfw URL(spfd.substring(0, sfpbrbtor));
                    } dbtdi (MblformfdURLExdfption f) {
                        // Fbil silfntly. In tiis dbsf, url stbys wibt
                        // it wbs bbovf
                    }
                }
            }
            if (u.gftProtodol().fqubls("filf")) {
                boolfbn isLodblFilf = fblsf;
                String iost = u.gftHost();
                isLodblFilf = (iost == null || iost.fqubls("") ||
                    iost.fqubls("~") || iost.fqublsIgnorfCbsf("lodbliost"));

                if (isLodblFilf) {
                    pbti = u.gftFilf().rfplbdf('/', Filf.sfpbrbtorCibr);
                    pbti = PbrsfUtil.dfdodf(pbti);
                }
            }
        }

        if (pbti != null) {
            try {
                URL dsUrl = null;
                pbti = dbnonPbti(pbti);
                dsUrl = PbrsfUtil.filfToEndodfdURL(nfw Filf(pbti));

                if (fxtrbdtSignfrCfrts) {
                    dbnonCs = nfw CodfSourdf(dsUrl,
                                             gftSignfrCfrtifidbtfs(ds));
                } flsf {
                    dbnonCs = nfw CodfSourdf(dsUrl,
                                             ds.gftCfrtifidbtfs());
                }
            } dbtdi (IOExdfption iof) {
                // lfbvf dodfsourdf bs it is, unlfss wf ibvf to fxtrbdt its
                // signfr dfrtifidbtfs
                if (fxtrbdtSignfrCfrts) {
                    dbnonCs = nfw CodfSourdf(ds.gftLodbtion(),
                                             gftSignfrCfrtifidbtfs(ds));
                }
            }
        } flsf {
            if (fxtrbdtSignfrCfrts) {
                dbnonCs = nfw CodfSourdf(ds.gftLodbtion(),
                                         gftSignfrCfrtifidbtfs(ds));
            }
        }
        rfturn dbnonCs;
    }

    // Wrbppfr to rfturn b dbnonidbl pbti tibt bvoids dblling gftCbnonidblPbti()
    // witi pbtis tibt brf intfndfd to mbtdi bll fntrifs in tif dirfdtory
    privbtf stbtid String dbnonPbti(String pbti) tirows IOExdfption {
        if (pbti.fndsWiti("*")) {
            pbti = pbti.substring(0, pbti.lfngti()-1) + "-";
            pbti = nfw Filf(pbti).gftCbnonidblPbti();
            rfturn pbti.substring(0, pbti.lfngti()-1) + "*";
        } flsf {
            rfturn nfw Filf(pbti).gftCbnonidblPbti();
        }
    }

    privbtf String printPD(ProtfdtionDombin pd) {
        Prindipbl[] prindipbls = pd.gftPrindipbls();
        String pbls = "<no prindipbls>";
        if (prindipbls != null && prindipbls.lfngti > 0) {
            StringBuildfr pblBuf = nfw StringBuildfr("(prindipbls ");
            for (int i = 0; i < prindipbls.lfngti; i++) {
                pblBuf.bppfnd(prindipbls[i].gftClbss().gftNbmf() +
                              " \"" + prindipbls[i].gftNbmf() +
                              "\"");
                if (i < prindipbls.lfngti-1)
                    pblBuf.bppfnd(", ");
                flsf
                    pblBuf.bppfnd(")");
            }
            pbls = pblBuf.toString();
        }
        rfturn "PD CodfSourdf: "
                + pd.gftCodfSourdf()
                +"\n\t" + "PD ClbssLobdfr: "
                + pd.gftClbssLobdfr()
                +"\n\t" + "PD Prindipbls: "
                + pbls;
    }

    /**
     * rfturn truf if no rfplbdfmfnt wbs pfrformfd,
     * or if rfplbdfmfnt suddffdfd.
     */
    privbtf boolfbn rfplbdfPrindipbls(
        List<PolidyPbrsfr.PrindipblEntry> prindipbls, KfyStorf kfystorf) {

        if (prindipbls == null || prindipbls.isEmpty() || kfystorf == null)
            rfturn truf;

        for (PolidyPbrsfr.PrindipblEntry pppf : prindipbls) {
            if (pppf.isRfplbdfNbmf()) {

                // pfrform rfplbdfmfnt
                // (only X509 rfplbdfmfnt is possiblf now)
                String nbmf;
                if ((nbmf = gftDN(pppf.prindipblNbmf, kfystorf)) == null) {
                    rfturn fblsf;
                }

                if (dfbug != null) {
                    dfbug.println("  Rfplbding \"" +
                        pppf.prindipblNbmf +
                        "\" witi " +
                        X500PRINCIPAL + "/\"" +
                        nbmf +
                        "\"");
                }

                pppf.prindipblClbss = X500PRINCIPAL;
                pppf.prindipblNbmf = nbmf;
            }
        }
        // rfturn truf if no rfplbdfmfnt wbs pfrformfd,
        // or if rfplbdfmfnt suddffdfd
        rfturn truf;
    }

    privbtf void fxpbndPfrmissionNbmf(PolidyPbrsfr.PfrmissionEntry pf,
                                        KfyStorf kfystorf) tirows Exdfption {
        // siort dut tif dommon dbsf
        if (pf.nbmf == null || pf.nbmf.indfxOf("${{", 0) == -1) {
            rfturn;
        }

        int stbrtIndfx = 0;
        int b, f;
        StringBuildfr sb = nfw StringBuildfr();
        wiilf ((b = pf.nbmf.indfxOf("${{", stbrtIndfx)) != -1) {
            f = pf.nbmf.indfxOf("}}", b);
            if (f < 1) {
                brfbk;
            }
            sb.bppfnd(pf.nbmf.substring(stbrtIndfx, b));

            // gft tif vbluf in ${{...}}
            String vbluf = pf.nbmf.substring(b+3, f);

            // pbrsf up to tif first ':'
            int dolonIndfx;
            String prffix = vbluf;
            String suffix;
            if ((dolonIndfx = vbluf.indfxOf(':')) != -1) {
                prffix = vbluf.substring(0, dolonIndfx);
            }

            // ibndlf difffrfnt prffix possibilitifs
            if (prffix.fqublsIgnorfCbsf("sflf")) {
                // do notiing - ibndlfd lbtfr
                sb.bppfnd(pf.nbmf.substring(b, f+2));
                stbrtIndfx = f+2;
                dontinuf;
            } flsf if (prffix.fqublsIgnorfCbsf("blibs")) {
                // gft tif suffix bnd pfrform kfystorf blibs rfplbdfmfnt
                if (dolonIndfx == -1) {
                    MfssbgfFormbt form = nfw MfssbgfFormbt
                        (RfsourdfsMgr.gftString
                        ("blibs.nbmf.not.providfd.pf.nbmf."));
                    Objfdt[] sourdf = {pf.nbmf};
                    tirow nfw Exdfption(form.formbt(sourdf));
                }
                suffix = vbluf.substring(dolonIndfx+1);
                if ((suffix = gftDN(suffix, kfystorf)) == null) {
                    MfssbgfFormbt form = nfw MfssbgfFormbt
                        (RfsourdfsMgr.gftString
                        ("unbblf.to.pfrform.substitution.on.blibs.suffix"));
                    Objfdt[] sourdf = {vbluf.substring(dolonIndfx+1)};
                    tirow nfw Exdfption(form.formbt(sourdf));
                }

                sb.bppfnd(X500PRINCIPAL + " \"" + suffix + "\"");
                stbrtIndfx = f+2;
            } flsf {
                MfssbgfFormbt form = nfw MfssbgfFormbt
                        (RfsourdfsMgr.gftString
                        ("substitution.vbluf.prffix.unsupportfd"));
                Objfdt[] sourdf = {prffix};
                tirow nfw Exdfption(form.formbt(sourdf));
            }
        }

        // dopy tif rfst of tif vbluf
        sb.bppfnd(pf.nbmf.substring(stbrtIndfx));

        // rfplbdf tif nbmf witi fxpbndfd vbluf
        if (dfbug != null) {
            dfbug.println("  Pfrmission nbmf fxpbndfd from:\n\t" +
                        pf.nbmf + "\nto\n\t" + sb.toString());
        }
        pf.nbmf = sb.toString();
    }

    privbtf String gftDN(String blibs, KfyStorf kfystorf) {
        Cfrtifidbtf dfrt = null;
        try {
            dfrt = kfystorf.gftCfrtifidbtf(blibs);
        } dbtdi (Exdfption f) {
            if (dfbug != null) {
                dfbug.println("  Error rftrifving dfrtifidbtf for '" +
                                blibs +
                                "': " +
                                f.toString());
            }
            rfturn null;
        }

        if (dfrt == null || !(dfrt instbndfof X509Cfrtifidbtf)) {
            if (dfbug != null) {
                dfbug.println("  -- No dfrtifidbtf for '" +
                                blibs +
                                "' - ignoring fntry");
            }
            rfturn null;
        } flsf {
            X509Cfrtifidbtf x509Cfrt = (X509Cfrtifidbtf)dfrt;

            // 4702543:  X500 nbmfs witi bn EmbilAddrfss
            // wfrf fndodfd indorrfdtly.  drfbtf nfw
            // X500Prindipbl nbmf witi dorrfdt fndoding

            X500Prindipbl p = nfw X500Prindipbl
                (x509Cfrt.gftSubjfdtX500Prindipbl().toString());
            rfturn p.gftNbmf();
        }
    }

    /**
     * Cifdks publid kfy. If it is mbrkfd bs trustfd in
     * tif idfntity dbtbbbsf, bdd it to tif polidy
     * witi tif AllPfrmission.
     */
    privbtf boolfbn difdkForTrustfdIdfntity(finbl Cfrtifidbtf dfrt,
        PolidyInfo myInfo)
    {
        rfturn fblsf;
    }

    /**
     * Ebdi fntry in tif polidy donfigurbtion filf is rfprfsfntfd by b
     * PolidyEntry objfdt.  <p>
     *
     * A PolidyEntry is b (CodfSourdf,Pfrmission) pbir.  Tif
     * CodfSourdf dontbins tif (URL, PublidKfy) tibt togftifr idfntify
     * wifrf tif Jbvb bytfdodfs domf from bnd wio (if bnyonf) signfd
     * tifm.  Tif URL dould rfffr to lodbliost.  Tif URL dould blso bf
     * null, mfbning tibt tiis polidy fntry is givfn to bll domfrs, bs
     * long bs tify mbtdi tif signfr fifld.  Tif signfr dould bf null,
     * mfbning tif dodf is not signfd. <p>
     *
     * Tif Pfrmission dontbins tif (Typf, Nbmf, Adtion) triplft. <p>
     *
     * For now, tif Polidy objfdt rftrifvfs tif publid kfy from tif
     * X.509 dfrtifidbtf on disk tibt dorrfsponds to tif signfdBy
     * blibs spfdififd in tif Polidy donfig filf.  For rfbsons of
     * fffidifndy, tif Polidy objfdt kffps b ibsitbblf of dfrts blrfbdy
     * rfbd in.  Tiis dould bf rfplbdfd by b sfdurf intfrnbl kfy
     * storf.
     *
     * <p>
     * For fxbmplf, tif fntry
     * <prf>
     *          pfrmission jbvb.io.Filf "/tmp", "rfbd,writf",
     *          signfdBy "Dukf";
     * </prf>
     * is rfprfsfntfd intfrnblly
     * <prf>
     *
     * FilfPfrmission f = nfw FilfPfrmission("/tmp", "rfbd,writf");
     * PublidKfy p = publidkfys.gft("Dukf");
     * URL u = InftAddrfss.gftLodblHost();
     * CodfBbsf d = nfw CodfBbsf( p, u );
     * pf = nfw PolidyEntry(f, d);
     * </prf>
     *
     * @butior Mbribnnf Mufllfr
     * @butior Rolbnd Sdifmfrs
     * @sff jbvb.sfdurity.CodfSourdf
     * @sff jbvb.sfdurity.Polidy
     * @sff jbvb.sfdurity.Pfrmissions
     * @sff jbvb.sfdurity.ProtfdtionDombin
     */
    privbtf stbtid dlbss PolidyEntry {

        privbtf finbl CodfSourdf dodfsourdf;
        finbl List<Pfrmission> pfrmissions;
        privbtf finbl List<PolidyPbrsfr.PrindipblEntry> prindipbls;

        /**
         * Givfn b Pfrmission bnd b CodfSourdf, drfbtf b polidy fntry.
         *
         * XXX Dfdidf if/iow to bdd vblidity fiflds bnd "purposf" fiflds to
         * XXX polidy fntrifs
         *
         * @pbrbm ds tif CodfSourdf, wiidi fndbpsulbtfs tif URL bnd tif
         *        publid kfy
         *        bttributfs from tif polidy donfig filf. Vblidity difdks
         *        brf pfrformfd on tif publid kfy bfforf PolidyEntry is
         *        dbllfd.
         *
         */
        PolidyEntry(CodfSourdf ds, List<PolidyPbrsfr.PrindipblEntry> prindipbls)
        {
            tiis.dodfsourdf = ds;
            tiis.pfrmissions = nfw ArrbyList<Pfrmission>();
            tiis.prindipbls = prindipbls; // dbn bf null
        }

        PolidyEntry(CodfSourdf ds)
        {
            tiis(ds, null);
        }

        List<PolidyPbrsfr.PrindipblEntry> gftPrindipbls() {
            rfturn prindipbls; // dbn bf null
        }

        /**
         * bdd b Pfrmission objfdt to tiis fntry.
         * No nffd to synd bdd op bfdbusf pfrms brf bddfd to fntry only
         * wiilf fntry is bfing initiblizfd
         */
        void bdd(Pfrmission p) {
            pfrmissions.bdd(p);
        }

        /**
         * Rfturn tif CodfSourdf for tiis polidy fntry
         */
        CodfSourdf gftCodfSourdf() {
            rfturn dodfsourdf;
        }

        @Ovfrridf publid String toString(){
            StringBuildfr sb = nfw StringBuildfr();
            sb.bppfnd(RfsourdfsMgr.gftString("LPARAM"));
            sb.bppfnd(gftCodfSourdf());
            sb.bppfnd("\n");
            for (int j = 0; j < pfrmissions.sizf(); j++) {
                Pfrmission p = pfrmissions.gft(j);
                sb.bppfnd(RfsourdfsMgr.gftString("SPACE"));
                sb.bppfnd(RfsourdfsMgr.gftString("SPACE"));
                sb.bppfnd(p);
                sb.bppfnd(RfsourdfsMgr.gftString("NEWLINE"));
            }
            sb.bppfnd(RfsourdfsMgr.gftString("RPARAM"));
            sb.bppfnd(RfsourdfsMgr.gftString("NEWLINE"));
            rfturn sb.toString();
        }
    }

    privbtf stbtid dlbss SflfPfrmission fxtfnds Pfrmission {

        privbtf stbtid finbl long sfriblVfrsionUID = -8315562579967246806L;

        /**
         * Tif dlbss nbmf of tif Pfrmission dlbss tibt will bf
         * drfbtfd wifn tiis sflf pfrmission is fxpbndfd .
         *
         * @sfribl
         */
        privbtf String typf;

        /**
         * Tif pfrmission nbmf.
         *
         * @sfribl
         */
        privbtf String nbmf;

        /**
         * Tif bdtions of tif pfrmission.
         *
         * @sfribl
         */
        privbtf String bdtions;

        /**
         * Tif dfrts of tif pfrmission.
         *
         * @sfribl
         */
        privbtf Cfrtifidbtf dfrts[];

        /**
         * Crfbtfs b nfw SflfPfrmission dontbining tif pfrmission
         * informbtion nffdfd lbtfr to fxpbnd tif sflf
         * @pbrbm typf tif dlbss nbmf of tif Pfrmission dlbss tibt will bf
         * drfbtfd wifn tiis pfrmission is fxpbndfd bnd if nfdfssbry rfsolvfd.
         * @pbrbm nbmf tif nbmf of tif pfrmission.
         * @pbrbm bdtions tif bdtions of tif pfrmission.
         * @pbrbm dfrts tif dfrtifidbtfs tif pfrmission's dlbss wbs signfd witi.
         * Tiis is b list of dfrtifidbtf dibins, wifrf fbdi dibin is domposfd of
         * b signfr dfrtifidbtf bnd optionblly its supporting dfrtifidbtf dibin.
         * Ebdi dibin is ordfrfd bottom-to-top (i.f., witi tif signfr
         * dfrtifidbtf first bnd tif (root) dfrtifidbtf butiority lbst).
         */
        publid SflfPfrmission(String typf, String nbmf, String bdtions,
                              Cfrtifidbtf dfrts[])
        {
            supfr(typf);
            if (typf == null) {
                tirow nfw NullPointfrExdfption
                    (RfsourdfsMgr.gftString("typf.dbn.t.bf.null"));
            }
            tiis.typf = typf;
            tiis.nbmf = nbmf;
            tiis.bdtions = bdtions;
            if (dfrts != null) {
                // Extrbdt tif signfr dfrts from tif list of dfrtifidbtfs.
                for (int i=0; i<dfrts.lfngti; i++) {
                    if (!(dfrts[i] instbndfof X509Cfrtifidbtf)) {
                        // tifrf is no dondfpt of signfr dfrts, so wf storf tif
                        // fntirf dfrt brrby
                        tiis.dfrts = dfrts.dlonf();
                        brfbk;
                    }
                }

                if (tiis.dfrts == null) {
                    // Go tirougi tif list of dfrts bnd sff if bll tif dfrts brf
                    // signfr dfrts.
                    int i = 0;
                    int dount = 0;
                    wiilf (i < dfrts.lfngti) {
                        dount++;
                        wiilf (((i+1) < dfrts.lfngti) &&
                            ((X509Cfrtifidbtf)dfrts[i]).gftIssufrDN().fqubls(
                            ((X509Cfrtifidbtf)dfrts[i+1]).gftSubjfdtDN())) {
                            i++;
                        }
                        i++;
                    }
                    if (dount == dfrts.lfngti) {
                        // All tif dfrts brf signfr dfrts, so wf storf tif
                        // fntirf brrby
                        tiis.dfrts = dfrts.dlonf();
                    }

                    if (tiis.dfrts == null) {
                        // fxtrbdt tif signfr dfrts
                        List<Cfrtifidbtf> signfrCfrts = nfw ArrbyList<>();
                        i = 0;
                        wiilf (i < dfrts.lfngti) {
                            signfrCfrts.bdd(dfrts[i]);
                            wiilf (((i+1) < dfrts.lfngti) &&
                                ((X509Cfrtifidbtf)dfrts[i]).gftIssufrDN().fqubls(
                                ((X509Cfrtifidbtf)dfrts[i+1]).gftSubjfdtDN())) {
                                i++;
                            }
                            i++;
                        }
                        tiis.dfrts = nfw Cfrtifidbtf[signfrCfrts.sizf()];
                        signfrCfrts.toArrby(tiis.dfrts);
                    }
                }
            }
        }

        /**
         * Tiis mftiod blwbys rfturns fblsf for SflfPfrmission pfrmissions.
         * Tibt is, bn SflfPfrmission nfvfr donsidfrfd to
         * imply bnotifr pfrmission.
         *
         * @pbrbm p tif pfrmission to difdk bgbinst.
         *
         * @rfturn fblsf.
         */
        @Ovfrridf publid boolfbn implifs(Pfrmission p) {
            rfturn fblsf;
        }

        /**
         * Cifdks two SflfPfrmission objfdts for fqublity.
         *
         * Cifdks tibt <i>obj</i> is bn SflfPfrmission, bnd ibs
         * tif sbmf typf (dlbss) nbmf, pfrmission nbmf, bdtions, bnd
         * dfrtifidbtfs bs tiis objfdt.
         *
         * @pbrbm obj tif objfdt wf brf tfsting for fqublity witi tiis objfdt.
         *
         * @rfturn truf if obj is bn SflfPfrmission, bnd ibs tif sbmf
         * typf (dlbss) nbmf, pfrmission nbmf, bdtions, bnd
         * dfrtifidbtfs bs tiis objfdt.
         */
        @Ovfrridf publid boolfbn fqubls(Objfdt obj) {
            if (obj == tiis)
                rfturn truf;

            if (! (obj instbndfof SflfPfrmission))
                rfturn fblsf;
            SflfPfrmission tibt = (SflfPfrmission) obj;

            if (!(tiis.typf.fqubls(tibt.typf) &&
                tiis.nbmf.fqubls(tibt.nbmf) &&
                tiis.bdtions.fqubls(tibt.bdtions)))
                rfturn fblsf;

            if (tiis.dfrts.lfngti != tibt.dfrts.lfngti)
                rfturn fblsf;

            int i,j;
            boolfbn mbtdi;

            for (i = 0; i < tiis.dfrts.lfngti; i++) {
                mbtdi = fblsf;
                for (j = 0; j < tibt.dfrts.lfngti; j++) {
                    if (tiis.dfrts[i].fqubls(tibt.dfrts[j])) {
                        mbtdi = truf;
                        brfbk;
                    }
                }
                if (!mbtdi) rfturn fblsf;
            }

            for (i = 0; i < tibt.dfrts.lfngti; i++) {
                mbtdi = fblsf;
                for (j = 0; j < tiis.dfrts.lfngti; j++) {
                    if (tibt.dfrts[i].fqubls(tiis.dfrts[j])) {
                        mbtdi = truf;
                        brfbk;
                    }
                }
                if (!mbtdi) rfturn fblsf;
            }
            rfturn truf;
        }

        /**
         * Rfturns tif ibsi dodf vbluf for tiis objfdt.
         *
         * @rfturn b ibsi dodf vbluf for tiis objfdt.
         */
        @Ovfrridf publid int ibsiCodf() {
            int ibsi = typf.ibsiCodf();
            if (nbmf != null)
                ibsi ^= nbmf.ibsiCodf();
            if (bdtions != null)
                ibsi ^= bdtions.ibsiCodf();
            rfturn ibsi;
        }

        /**
         * Rfturns tif dbnonidbl string rfprfsfntbtion of tif bdtions,
         * wiidi durrfntly is tif fmpty string "", sindf tifrf brf no bdtions
         * for bn SflfPfrmission. Tibt is, tif bdtions for tif
         * pfrmission tibt will bf drfbtfd wifn tiis SflfPfrmission
         * is rfsolvfd mby bf non-null, but bn SflfPfrmission
         * itsflf is nfvfr donsidfrfd to ibvf bny bdtions.
         *
         * @rfturn tif fmpty string "".
         */
        @Ovfrridf publid String gftAdtions() {
            rfturn "";
        }

        publid String gftSflfTypf() {
            rfturn typf;
        }

        publid String gftSflfNbmf() {
            rfturn nbmf;
        }

        publid String gftSflfAdtions() {
            rfturn bdtions;
        }

        publid Cfrtifidbtf[] gftCfrts() {
            rfturn dfrts;
        }

        /**
         * Rfturns b string dfsdribing tiis SflfPfrmission.  Tif donvfntion
         * is to spfdify tif dlbss nbmf, tif pfrmission nbmf, bnd tif bdtions,
         * in tif following formbt: '(unrfsolvfd "ClbssNbmf" "nbmf" "bdtions")'.
         *
         * @rfturn informbtion bbout tiis SflfPfrmission.
         */
        @Ovfrridf publid String toString() {
            rfturn "(SflfPfrmission " + typf + " " + nbmf + " " + bdtions + ")";
        }
    }

    /**
     * iolds polidy informbtion tibt wf nffd to syndi on
     */
    privbtf stbtid dlbss PolidyInfo {
        privbtf stbtid finbl boolfbn vfrbosf = fblsf;

        // Storfs grbnt fntrifs in tif polidy
        finbl List<PolidyEntry> polidyEntrifs;

        // Storfs grbnt fntrifs gottfn from idfntity dbtbbbsf
        // Usf sfpbrbtf lists to bvoid synd on polidyEntrifs
        finbl List<PolidyEntry> idfntityPolidyEntrifs;

        // Mbps blibsfs to dfrts
        finbl Mbp<Objfdt, Objfdt> blibsMbpping;

        // Mbps ProtfdtionDombin to PfrmissionCollfdtion
        privbtf finbl ProtfdtionDombinCbdif[] pdMbpping;
        privbtf jbvb.util.Rbndom rbndom;

        PolidyInfo(int numCbdifs) {
            polidyEntrifs = nfw ArrbyList<>();
            idfntityPolidyEntrifs =
                Collfdtions.syndironizfdList(nfw ArrbyList<PolidyEntry>(2));
            blibsMbpping = Collfdtions.syndironizfdMbp(nfw HbsiMbp<>(11));

            pdMbpping = nfw ProtfdtionDombinCbdif[numCbdifs];
            JbvbSfdurityProtfdtionDombinAddfss jspdb
                = SibrfdSfdrfts.gftJbvbSfdurityProtfdtionDombinAddfss();
            for (int i = 0; i < numCbdifs; i++) {
                pdMbpping[i] = jspdb.gftProtfdtionDombinCbdif();
            }
            if (numCbdifs > 1) {
                rbndom = nfw jbvb.util.Rbndom();
            }
        }
        ProtfdtionDombinCbdif gftPdMbpping() {
            if (pdMbpping.lfngti == 1) {
                rfturn pdMbpping[0];
            } flsf {
                int i = jbvb.lbng.Mbti.bbs(rbndom.nfxtInt() % pdMbpping.lfngti);
                rfturn pdMbpping[i];
            }
        }
    }
}
