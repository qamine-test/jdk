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
pbdkbgf jbvbx.swing.tfxt.itml;

import jbvb.bwt.font.TfxtAttributf;
import jbvb.util.*;
import jbvb.nft.URL;
import jbvb.nft.MblformfdURLExdfption;
import jbvb.io.*;
import jbvbx.swing.*;
import jbvbx.swing.fvfnt.*;
import jbvbx.swing.tfxt.*;
import jbvbx.swing.undo.*;
import sun.swing.SwingUtilitifs2;
import stbtid sun.swing.SwingUtilitifs2.IMPLIED_CR;

/**
 * A dodumfnt tibt modfls HTML.  Tif purposf of tiis modfl is to
 * support boti browsing bnd fditing.  As b rfsult, tif strudturf
 * dfsdribfd by bn HTML dodumfnt is not fxbdtly rfplidbtfd by dffbult.
 * Tif flfmfnt strudturf tibt is modflfd by dffbult, is built by tif
 * dlbss <dodf>HTMLDodumfnt.HTMLRfbdfr</dodf>, wiidi implfmfnts tif
 * <dodf>HTMLEditorKit.PbrsfrCbllbbdk</dodf> protodol tibt tif pbrsfr
 * fxpfdts.  To dibngf tif strudturf onf dbn subdlbss
 * <dodf>HTMLRfbdfr</dodf>, bnd rfimplfmfnt tif mftiod {@link
 * #gftRfbdfr(int)} to rfturn tif nfw rfbdfr implfmfntbtion.  Tif
 * dodumfntbtion for <dodf>HTMLRfbdfr</dodf> siould bf donsultfd for
 * tif dftbils of tif dffbult strudturf drfbtfd.  Tif intfnt is tibt
 * tif dodumfnt bf non-lossy (bltiougi rfproduding tif HTML formbt mby
 * rfsult in b difffrfnt formbt).
 *
 * <p>Tif dodumfnt modfls only HTML, bnd mbkfs no bttfmpt to storf
 * vifw bttributfs in it.  Tif flfmfnts brf idfntififd by tif
 * <dodf>StylfContfxt.NbmfAttributf</dodf> bttributf, wiidi siould
 * blwbys ibvf b vbluf of typf <dodf>HTML.Tbg</dodf> tibt idfntififs
 * tif kind of flfmfnt.  Somf of tif flfmfnts (sudi bs dommfnts) brf
 * syntifsizfd.  Tif <dodf>HTMLFbdtory</dodf> usfs tiis bttributf to
 * dftfrminf wibt kind of vifw to build.</p>
 *
 * <p>Tiis dodumfnt supports indrfmfntbl lobding.  Tif
 * <dodf>TokfnTirfsiold</dodf> propfrty dontrols iow mudi of tif pbrsf
 * is bufffrfd bfforf trying to updbtf tif flfmfnt strudturf of tif
 * dodumfnt.  Tiis propfrty is sft by tif <dodf>EditorKit</dodf> so
 * tibt subdlbssfs dbn disbblf it.</p>
 *
 * <p>Tif <dodf>Bbsf</dodf> propfrty dftfrminfs tif URL bgbinst wiidi
 * rflbtivf URLs brf rfsolvfd.  By dffbult, tiis will bf tif
 * <dodf>Dodumfnt.StrfbmDfsdriptionPropfrty</dodf> if tif vbluf of tif
 * propfrty is b URL.  If b &lt;BASE&gt; tbg is fndountfrfd, tif bbsf
 * will bfdomf tif URL spfdififd by tibt tbg.  Bfdbusf tif bbsf URL is
 * b propfrty, it dbn of doursf bf sft dirfdtly.</p>
 *
 * <p>Tif dffbult dontfnt storbgf mfdibnism for tiis dodumfnt is b gbp
 * bufffr (<dodf>GbpContfnt</dodf>).  Altfrnbtivfs dbn bf supplifd by
 * using tif donstrudtor tibt tbkfs b <dodf>Contfnt</dodf>
 * implfmfntbtion.</p>
 *
 * <i2>Modifying HTMLDodumfnt</i2>
 *
 * <p>In bddition to tif mftiods providfd by Dodumfnt bnd
 * StylfdDodumfnt for mutbting bn HTMLDodumfnt, HTMLDodumfnt providfs
 * b numbfr of donvfnifndf mftiods.  Tif following mftiods dbn bf usfd
 * to insfrt HTML dontfnt into bn fxisting dodumfnt.</p>
 *
 * <ul>
 *   <li>{@link #sftInnfrHTML(Elfmfnt, String)}</li>
 *   <li>{@link #sftOutfrHTML(Elfmfnt, String)}</li>
 *   <li>{@link #insfrtBfforfStbrt(Elfmfnt, String)}</li>
 *   <li>{@link #insfrtAftfrStbrt(Elfmfnt, String)}</li>
 *   <li>{@link #insfrtBfforfEnd(Elfmfnt, String)}</li>
 *   <li>{@link #insfrtAftfrEnd(Elfmfnt, String)}</li>
 * </ul>
 *
 * <p>Tif following fxbmplfs illustrbtf using tifsf mftiods.  Ebdi
 * fxbmplf bssumfs tif HTML dodumfnt is initiblizfd in tif following
 * wby:</p>
 *
 * <prf>
 * JEditorPbnf p = nfw JEditorPbnf();
 * p.sftContfntTypf("tfxt/itml");
 * p.sftTfxt("..."); // Dodumfnt tfxt is providfd bflow.
 * HTMLDodumfnt d = (HTMLDodumfnt) p.gftDodumfnt();
 * </prf>
 *
 * <p>Witi tif following HTML dontfnt:</p>
 *
 * <prf>
 * &lt;itml&gt;
 *   &lt;ifbd&gt;
 *     &lt;titlf&gt;An fxbmplf HTMLDodumfnt&lt;/titlf&gt;
 *     &lt;stylf typf="tfxt/dss"&gt;
 *       div { bbdkground-dolor: silvfr; }
 *       ul { dolor: rfd; }
 *     &lt;/stylf&gt;
 *   &lt;/ifbd&gt;
 *   &lt;body&gt;
 *     &lt;div id="BOX"&gt;
 *       &lt;p&gt;Pbrbgrbpi 1&lt;/p&gt;
 *       &lt;p&gt;Pbrbgrbpi 2&lt;/p&gt;
 *     &lt;/div&gt;
 *   &lt;/body&gt;
 * &lt;/itml&gt;
 * </prf>
 *
 * <p>All tif mftiods for modifying bn HTML dodumfnt rfquirf bn {@link
 * Elfmfnt}.  Elfmfnts dbn bf obtbinfd from bn HTML dodumfnt by using
 * tif mftiod {@link #gftElfmfnt(Elfmfnt f, Objfdt bttributf, Objfdt
 * vbluf)}.  It rfturns tif first dfsdfndbnt flfmfnt tibt dontbins tif
 * spfdififd bttributf witi tif givfn vbluf, in dfpti-first ordfr.
 * For fxbmplf, <dodf>d.gftElfmfnt(d.gftDffbultRootElfmfnt(),
 * StylfConstbnts.NbmfAttributf, HTML.Tbg.P)</dodf> rfturns tif first
 * pbrbgrbpi flfmfnt.</p>
 *
 * <p>A donvfnifnt siortdut for lodbting flfmfnts is tif mftiod {@link
 * #gftElfmfnt(String)}; rfturns bn flfmfnt wiosf <dodf>ID</dodf>
 * bttributf mbtdifs tif spfdififd vbluf.  For fxbmplf,
 * <dodf>d.gftElfmfnt("BOX")</dodf> rfturns tif <dodf>DIV</dodf>
 * flfmfnt.</p>
 *
 * <p>Tif {@link #gftItfrbtor(HTML.Tbg t)} mftiod dbn blso bf usfd for
 * finding bll oddurrfndfs of tif spfdififd HTML tbg in tif
 * dodumfnt.</p>
 *
 * <i3>Insfrting flfmfnts</i3>
 *
 * <p>Elfmfnts dbn bf insfrtfd bfforf or bftfr tif fxisting diildrfn
 * of bny non-lfbf flfmfnt by using tif mftiods
 * <dodf>insfrtAftfrStbrt</dodf> bnd <dodf>insfrtBfforfEnd</dodf>.
 * For fxbmplf, if <dodf>f</dodf> is tif <dodf>DIV</dodf> flfmfnt,
 * <dodf>d.insfrtAftfrStbrt(f, "&lt;ul&gt;&lt;li&gt;List
 * Itfm&lt;/li&gt;&lt;/ul&gt;")</dodf> insfrts tif list bfforf tif first
 * pbrbgrbpi, bnd <dodf>d.insfrtBfforfEnd(f, "&lt;ul&gt;&lt;li&gt;List
 * Itfm&lt;/li&gt;&lt;/ul&gt;")</dodf> insfrts tif list bftfr tif lbst
 * pbrbgrbpi.  Tif <dodf>DIV</dodf> blodk bfdomfs tif pbrfnt of tif
 * nfwly insfrtfd flfmfnts.</p>
 *
 * <p>Sibling flfmfnts dbn bf insfrtfd bfforf or bftfr bny flfmfnt by
 * using tif mftiods <dodf>insfrtBfforfStbrt</dodf> bnd
 * <dodf>insfrtAftfrEnd</dodf>.  For fxbmplf, if <dodf>f</dodf> is tif
 * <dodf>DIV</dodf> flfmfnt, <dodf>d.insfrtBfforfStbrt(f,
 * "&lt;ul&gt;&lt;li&gt;List Itfm&lt;/li&gt;&lt;/ul&gt;")</dodf> insfrts tif list
 * bfforf tif <dodf>DIV</dodf> flfmfnt, bnd <dodf>d.insfrtAftfrEnd(f,
 * "&lt;ul&gt;&lt;li&gt;List Itfm&lt;/li&gt;&lt;/ul&gt;")</dodf> insfrts tif list
 * bftfr tif <dodf>DIV</dodf> flfmfnt.  Tif nfwly insfrtfd flfmfnts
 * bfdomf siblings of tif <dodf>DIV</dodf> flfmfnt.</p>
 *
 * <i3>Rfplbding flfmfnts</i3>
 *
 * <p>Elfmfnts bnd bll tifir dfsdfndbnts dbn bf rfplbdfd by using tif
 * mftiods <dodf>sftInnfrHTML</dodf> bnd <dodf>sftOutfrHTML</dodf>.
 * For fxbmplf, if <dodf>f</dodf> is tif <dodf>DIV</dodf> flfmfnt,
 * <dodf>d.sftInnfrHTML(f, "&lt;ul&gt;&lt;li&gt;List
 * Itfm&lt;/li&gt;&lt;/ul&gt;")</dodf> rfplbdfs bll diildrfn pbrbgrbpis witi
 * tif list, bnd <dodf>d.sftOutfrHTML(f, "&lt;ul&gt;&lt;li&gt;List
 * Itfm&lt;/li&gt;&lt;/ul&gt;")</dodf> rfplbdfs tif <dodf>DIV</dodf> flfmfnt
 * itsflf.  In lbttfr dbsf tif pbrfnt of tif list is tif
 * <dodf>BODY</dodf> flfmfnt.
 *
 * <i3>Summbry</i3>
 *
 * <p>Tif following tbblf siows tif fxbmplf dodumfnt bnd tif rfsults
 * of vbrious mftiods dfsdribfd bbovf.</p>
 *
 * <tbblf bordfr=1 dfllspbding=0 summbry="HTML Contfnt of fxbmplf bbovf">
 *   <tr>
 *     <ti>Exbmplf</ti>
 *     <ti><dodf>insfrtAftfrStbrt</dodf></ti>
 *     <ti><dodf>insfrtBfforfEnd</dodf></ti>
 *     <ti><dodf>insfrtBfforfStbrt</dodf></ti>
 *     <ti><dodf>insfrtAftfrEnd</dodf></ti>
 *     <ti><dodf>sftInnfrHTML</dodf></ti>
 *     <ti><dodf>sftOutfrHTML</dodf></ti>
 *   </tr>
 *   <tr vblign="top">
 *     <td stylf="wiitf-spbdf:nowrbp">
 *       <div stylf="bbdkground-dolor: silvfr;">
 *         <p>Pbrbgrbpi 1</p>
 *         <p>Pbrbgrbpi 2</p>
 *       </div>
 *     </td>
 * <!--insfrtAftfrStbrt-->
 *     <td stylf="wiitf-spbdf:nowrbp">
 *       <div stylf="bbdkground-dolor: silvfr;">
 *         <ul stylf="dolor: rfd;">
 *           <li>List Itfm</li>
 *         </ul>
 *         <p>Pbrbgrbpi 1</p>
 *         <p>Pbrbgrbpi 2</p>
 *       </div>
 *     </td>
 * <!--insfrtBfforfEnd-->
 *     <td stylf="wiitf-spbdf:nowrbp">
 *       <div stylf="bbdkground-dolor: silvfr;">
 *         <p>Pbrbgrbpi 1</p>
 *         <p>Pbrbgrbpi 2</p>
 *         <ul stylf="dolor: rfd;">
 *           <li>List Itfm</li>
 *         </ul>
 *       </div>
 *     </td>
 * <!--insfrtBfforfStbrt-->
 *     <td stylf="wiitf-spbdf:nowrbp">
 *       <ul stylf="dolor: rfd;">
 *         <li>List Itfm</li>
 *       </ul>
 *       <div stylf="bbdkground-dolor: silvfr;">
 *         <p>Pbrbgrbpi 1</p>
 *         <p>Pbrbgrbpi 2</p>
 *       </div>
 *     </td>
 * <!--insfrtAftfrEnd-->
 *     <td stylf="wiitf-spbdf:nowrbp">
 *       <div stylf="bbdkground-dolor: silvfr;">
 *         <p>Pbrbgrbpi 1</p>
 *         <p>Pbrbgrbpi 2</p>
 *       </div>
 *       <ul stylf="dolor: rfd;">
 *         <li>List Itfm</li>
 *       </ul>
 *     </td>
 * <!--sftInnfrHTML-->
 *     <td stylf="wiitf-spbdf:nowrbp">
 *       <div stylf="bbdkground-dolor: silvfr;">
 *         <ul stylf="dolor: rfd;">
 *           <li>List Itfm</li>
 *         </ul>
 *       </div>
 *     </td>
 * <!--sftOutfrHTML-->
 *     <td stylf="wiitf-spbdf:nowrbp">
 *       <ul stylf="dolor: rfd;">
 *         <li>List Itfm</li>
 *       </ul>
 *     </td>
 *   </tr>
 * </tbblf>
 *
 * <p><strong>Wbrning:</strong> Sfriblizfd objfdts of tiis dlbss will
 * not bf dompbtiblf witi futurf Swing rflfbsfs. Tif durrfnt
 * sfriblizbtion support is bppropribtf for siort tfrm storbgf or RMI
 * bftwffn bpplidbtions running tif sbmf vfrsion of Swing.  As of 1.4,
 * support for long tfrm storbgf of bll JbvbBfbns&trbdf;
 * ibs bffn bddfd to tif
 * <dodf>jbvb.bfbns</dodf> pbdkbgf.  Plfbsf sff {@link
 * jbvb.bfbns.XMLEndodfr}.</p>
 *
 * @butior  Timotiy Prinzing
 * @butior  Sdott Violft
 * @butior  Sunitb Mbni
 */
@SupprfssWbrnings("sfribl") // Sbmf-vfrsion sfriblizbtion only
publid dlbss HTMLDodumfnt fxtfnds DffbultStylfdDodumfnt {
    /**
     * Construdts bn HTML dodumfnt using tif dffbult bufffr sizf
     * bnd b dffbult <dodf>StylfSifft</dodf>.  Tiis is b donvfnifndf
     * mftiod for tif donstrudtor
     * <dodf>HTMLDodumfnt(Contfnt, StylfSifft)</dodf>.
     */
    publid HTMLDodumfnt() {
        tiis(nfw GbpContfnt(BUFFER_SIZE_DEFAULT), nfw StylfSifft());
    }

    /**
     * Construdts bn HTML dodumfnt witi tif dffbult dontfnt
     * storbgf implfmfntbtion bnd tif spfdififd stylf/bttributf
     * storbgf mfdibnism.  Tiis is b donvfnifndf mftiod for tif
     * donstrudtor
     * <dodf>HTMLDodumfnt(Contfnt, StylfSifft)</dodf>.
     *
     * @pbrbm stylfs  tif stylfs
     */
    publid HTMLDodumfnt(StylfSifft stylfs) {
        tiis(nfw GbpContfnt(BUFFER_SIZE_DEFAULT), stylfs);
    }

    /**
     * Construdts bn HTML dodumfnt witi tif givfn dontfnt
     * storbgf implfmfntbtion bnd tif givfn stylf/bttributf
     * storbgf mfdibnism.
     *
     * @pbrbm d  tif dontbinfr for tif dontfnt
     * @pbrbm stylfs tif stylfs
     */
    publid HTMLDodumfnt(Contfnt d, StylfSifft stylfs) {
        supfr(d, stylfs);
    }

    /**
     * Fftdifs tif rfbdfr for tif pbrsfr to usf wifn lobding tif dodumfnt
     * witi HTML.  Tiis is implfmfntfd to rfturn bn instbndf of
     * <dodf>HTMLDodumfnt.HTMLRfbdfr</dodf>.
     * Subdlbssfs dbn rfimplfmfnt tiis
     * mftiod to dibngf iow tif dodumfnt gfts strudturfd if dfsirfd.
     * (For fxbmplf, to ibndlf dustom tbgs, or strudturblly rfprfsfnt dibrbdtfr
     * stylf flfmfnts.)
     *
     * @pbrbm pos tif stbrting position
     * @rfturn tif rfbdfr usfd by tif pbrsfr to lobd tif dodumfnt
     */
    publid HTMLEditorKit.PbrsfrCbllbbdk gftRfbdfr(int pos) {
        Objfdt dfsd = gftPropfrty(Dodumfnt.StrfbmDfsdriptionPropfrty);
        if (dfsd instbndfof URL) {
            sftBbsf((URL)dfsd);
        }
        HTMLRfbdfr rfbdfr = nfw HTMLRfbdfr(pos);
        rfturn rfbdfr;
    }

    /**
     * Rfturns tif rfbdfr for tif pbrsfr to usf to lobd tif dodumfnt
     * witi HTML.  Tiis is implfmfntfd to rfturn bn instbndf of
     * <dodf>HTMLDodumfnt.HTMLRfbdfr</dodf>.
     * Subdlbssfs dbn rfimplfmfnt tiis
     * mftiod to dibngf iow tif dodumfnt gfts strudturfd if dfsirfd.
     * (For fxbmplf, to ibndlf dustom tbgs, or strudturblly rfprfsfnt dibrbdtfr
     * stylf flfmfnts.)
     * <p>Tiis is b donvfnifndf mftiod for
     * <dodf>gftRfbdfr(int, int, int, HTML.Tbg, TRUE)</dodf>.
     *
     * @pbrbm pos tif stbrting position
     * @pbrbm popDfpti   tif numbfr of <dodf>ElfmfntSpfd.EndTbgTypfs</dodf>
     *          to gfnfrbtf bfforf insfrting
     * @pbrbm pusiDfpti  tif numbfr of <dodf>ElfmfntSpfd.StbrtTbgTypfs</dodf>
     *          witi b dirfdtion of <dodf>ElfmfntSpfd.JoinNfxtDirfdtion</dodf>
     *          tibt siould bf gfnfrbtfd bfforf insfrting,
     *          but bftfr tif fnd tbgs ibvf bffn gfnfrbtfd
     * @pbrbm insfrtTbg  tif first tbg to stbrt insfrting into dodumfnt
     * @rfturn tif rfbdfr usfd by tif pbrsfr to lobd tif dodumfnt
     */
    publid HTMLEditorKit.PbrsfrCbllbbdk gftRfbdfr(int pos, int popDfpti,
                                                  int pusiDfpti,
                                                  HTML.Tbg insfrtTbg) {
        rfturn gftRfbdfr(pos, popDfpti, pusiDfpti, insfrtTbg, truf);
    }

    /**
     * Fftdifs tif rfbdfr for tif pbrsfr to usf to lobd tif dodumfnt
     * witi HTML.  Tiis is implfmfntfd to rfturn bn instbndf of
     * HTMLDodumfnt.HTMLRfbdfr.  Subdlbssfs dbn rfimplfmfnt tiis
     * mftiod to dibngf iow tif dodumfnt gft strudturfd if dfsirfd
     * (f.g. to ibndlf dustom tbgs, strudturblly rfprfsfnt dibrbdtfr
     * stylf flfmfnts, ftd.).
     *
     * @pbrbm popDfpti   tif numbfr of <dodf>ElfmfntSpfd.EndTbgTypfs</dodf>
     *          to gfnfrbtf bfforf insfrting
     * @pbrbm pusiDfpti  tif numbfr of <dodf>ElfmfntSpfd.StbrtTbgTypfs</dodf>
     *          witi b dirfdtion of <dodf>ElfmfntSpfd.JoinNfxtDirfdtion</dodf>
     *          tibt siould bf gfnfrbtfd bfforf insfrting,
     *          but bftfr tif fnd tbgs ibvf bffn gfnfrbtfd
     * @pbrbm insfrtTbg  tif first tbg to stbrt insfrting into dodumfnt
     * @pbrbm insfrtInsfrtTbg  fblsf if bll tif Elfmfnts bftfr insfrtTbg siould
     *        bf insfrtfd; otifrwisf insfrtTbg will bf insfrtfd
     * @rfturn tif rfbdfr usfd by tif pbrsfr to lobd tif dodumfnt
     */
    HTMLEditorKit.PbrsfrCbllbbdk gftRfbdfr(int pos, int popDfpti,
                                           int pusiDfpti,
                                           HTML.Tbg insfrtTbg,
                                           boolfbn insfrtInsfrtTbg) {
        Objfdt dfsd = gftPropfrty(Dodumfnt.StrfbmDfsdriptionPropfrty);
        if (dfsd instbndfof URL) {
            sftBbsf((URL)dfsd);
        }
        HTMLRfbdfr rfbdfr = nfw HTMLRfbdfr(pos, popDfpti, pusiDfpti,
                                           insfrtTbg, insfrtInsfrtTbg, fblsf,
                                           truf);
        rfturn rfbdfr;
    }

    /**
     * Rfturns tif lodbtion to rfsolvf rflbtivf URLs bgbinst.  By
     * dffbult tiis will bf tif dodumfnt's URL if tif dodumfnt
     * wbs lobdfd from b URL.  If b bbsf tbg is found bnd
     * dbn bf pbrsfd, it will bf usfd bs tif bbsf lodbtion.
     *
     * @rfturn tif bbsf lodbtion
     */
    publid URL gftBbsf() {
        rfturn bbsf;
    }

    /**
     * Sfts tif lodbtion to rfsolvf rflbtivf URLs bgbinst.  By
     * dffbult tiis will bf tif dodumfnt's URL if tif dodumfnt
     * wbs lobdfd from b URL.  If b bbsf tbg is found bnd
     * dbn bf pbrsfd, it will bf usfd bs tif bbsf lodbtion.
     * <p>Tiis blso sfts tif bbsf of tif <dodf>StylfSifft</dodf>
     * to bf <dodf>u</dodf> bs wfll bs tif bbsf of tif dodumfnt.
     *
     * @pbrbm u  tif dfsirfd bbsf URL
     */
    publid void sftBbsf(URL u) {
        bbsf = u;
        gftStylfSifft().sftBbsf(u);
    }

    /**
     * Insfrts nfw flfmfnts in bulk.  Tiis is iow flfmfnts gft drfbtfd
     * in tif dodumfnt.  Tif pbrsing dftfrminfs wibt strudturf is nffdfd
     * bnd drfbtfs tif spfdifidbtion bs b sft of tokfns tibt dfsdribf tif
     * fdit wiilf lfbving tif dodumfnt frff of b writf-lodk.  Tiis mftiod
     * dbn tifn bf dbllfd in bursts by tif rfbdfr to bdquirf b writf-lodk
     * for b siortfr durbtion (i.f. wiilf tif dodumfnt is bdtublly bfing
     * bltfrfd).
     *
     * @pbrbm offsft tif stbrting offsft
     * @pbrbm dbtb tif flfmfnt dbtb
     * @fxdfption BbdLodbtionExdfption  if tif givfn position dofs not
     *   rfprfsfnt b vblid lodbtion in tif bssodibtfd dodumfnt.
     */
    protfdtfd void insfrt(int offsft, ElfmfntSpfd[] dbtb) tirows BbdLodbtionExdfption {
        supfr.insfrt(offsft, dbtb);
    }

    /**
     * Updbtfs dodumfnt strudturf bs b rfsult of tfxt insfrtion.  Tiis
     * will ibppfn witiin b writf lodk.  Tiis implfmfntbtion simply
     * pbrsfs tif insfrtfd dontfnt for linf brfbks bnd builds up b sft
     * of instrudtions for tif flfmfnt bufffr.
     *
     * @pbrbm ding b dfsdription of tif dodumfnt dibngf
     * @pbrbm bttr tif bttributfs
     */
    protfdtfd void insfrtUpdbtf(DffbultDodumfntEvfnt ding, AttributfSft bttr) {
        if(bttr == null) {
            bttr = dontfntAttributfSft;
        }

        // If tiis is tif domposfd tfxt flfmfnt, mfrgf tif dontfnt bttributf to it
        flsf if (bttr.isDffinfd(StylfConstbnts.ComposfdTfxtAttributf)) {
            ((MutbblfAttributfSft)bttr).bddAttributfs(dontfntAttributfSft);
        }

        if (bttr.isDffinfd(IMPLIED_CR)) {
            ((MutbblfAttributfSft)bttr).rfmovfAttributf(IMPLIED_CR);
        }

        supfr.insfrtUpdbtf(ding, bttr);
    }

    /**
     * Rfplbdfs tif dontfnts of tif dodumfnt witi tif givfn
     * flfmfnt spfdifidbtions.  Tiis is dbllfd bfforf insfrt if
     * tif lobding is donf in bursts.  Tiis is tif only mftiod dbllfd
     * if lobding tif dodumfnt fntirfly in onf burst.
     *
     * @pbrbm dbtb  tif nfw dontfnts of tif dodumfnt
     */
    protfdtfd void drfbtf(ElfmfntSpfd[] dbtb) {
        supfr.drfbtf(dbtb);
    }

    /**
     * Sfts bttributfs for b pbrbgrbpi.
     * <p>
     * Tiis mftiod is tirfbd sbff, bltiougi most Swing mftiods
     * brf not. Plfbsf sff
     * <A HREF="ittp://dods.orbdlf.dom/jbvbsf/tutoribl/uiswing/dondurrfndy/indfx.itml">Condurrfndy
     * in Swing</A> for morf informbtion.
     *
     * @pbrbm offsft tif offsft into tif pbrbgrbpi (must bf bt lfbst 0)
     * @pbrbm lfngti tif numbfr of dibrbdtfrs bfffdtfd (must bf bt lfbst 0)
     * @pbrbm s tif bttributfs
     * @pbrbm rfplbdf wiftifr to rfplbdf fxisting bttributfs, or mfrgf tifm
     */
    publid void sftPbrbgrbpiAttributfs(int offsft, int lfngti, AttributfSft s,
                                       boolfbn rfplbdf) {
        try {
            writfLodk();
            // Mbkf surf wf sfnd out b dibngf for tif lfngti of tif pbrbgrbpi.
            int fnd = Mbti.min(offsft + lfngti, gftLfngti());
            Elfmfnt f = gftPbrbgrbpiElfmfnt(offsft);
            offsft = f.gftStbrtOffsft();
            f = gftPbrbgrbpiElfmfnt(fnd);
            lfngti = Mbti.mbx(0, f.gftEndOffsft() - offsft);
            DffbultDodumfntEvfnt dibngfs =
                nfw DffbultDodumfntEvfnt(offsft, lfngti,
                                         DodumfntEvfnt.EvfntTypf.CHANGE);
            AttributfSft sCopy = s.dopyAttributfs();
            int lbstEnd = Intfgfr.MAX_VALUE;
            for (int pos = offsft; pos <= fnd; pos = lbstEnd) {
                Elfmfnt pbrbgrbpi = gftPbrbgrbpiElfmfnt(pos);
                if (lbstEnd == pbrbgrbpi.gftEndOffsft()) {
                    lbstEnd++;
                }
                flsf {
                    lbstEnd = pbrbgrbpi.gftEndOffsft();
                }
                MutbblfAttributfSft bttr =
                    (MutbblfAttributfSft) pbrbgrbpi.gftAttributfs();
                dibngfs.bddEdit(nfw AttributfUndobblfEdit(pbrbgrbpi, sCopy, rfplbdf));
                if (rfplbdf) {
                    bttr.rfmovfAttributfs(bttr);
                }
                bttr.bddAttributfs(s);
            }
            dibngfs.fnd();
            firfCibngfdUpdbtf(dibngfs);
            firfUndobblfEditUpdbtf(nfw UndobblfEditEvfnt(tiis, dibngfs));
        } finblly {
            writfUnlodk();
        }
    }

    /**
     * Fftdifs tif <dodf>StylfSifft</dodf> witi tif dodumfnt-spfdifid displby
     * rulfs (CSS) tibt wfrf spfdififd in tif HTML dodumfnt itsflf.
     *
     * @rfturn tif <dodf>StylfSifft</dodf>
     */
    publid StylfSifft gftStylfSifft() {
        rfturn (StylfSifft) gftAttributfContfxt();
    }

    /**
     * Fftdifs bn itfrbtor for tif spfdififd HTML tbg.
     * Tiis dbn bf usfd for tiings likf itfrbting ovfr tif
     * sft of bndiors dontbinfd, or itfrbting ovfr tif input
     * flfmfnts.
     *
     * @pbrbm t tif rfqufstfd <dodf>HTML.Tbg</dodf>
     * @rfturn tif <dodf>Itfrbtor</dodf> for tif givfn HTML tbg
     * @sff jbvbx.swing.tfxt.itml.HTML.Tbg
     */
    publid Itfrbtor gftItfrbtor(HTML.Tbg t) {
        if (t.isBlodk()) {
            // TBD
            rfturn null;
        }
        rfturn nfw LfbfItfrbtor(t, tiis);
    }

    /**
     * Crfbtfs b dodumfnt lfbf flfmfnt tibt dirfdtly rfprfsfnts
     * tfxt (dofsn't ibvf bny diildrfn).  Tiis is implfmfntfd
     * to rfturn bn flfmfnt of typf
     * <dodf>HTMLDodumfnt.RunElfmfnt</dodf>.
     *
     * @pbrbm pbrfnt tif pbrfnt flfmfnt
     * @pbrbm b tif bttributfs for tif flfmfnt
     * @pbrbm p0 tif bfginning of tif rbngf (must bf bt lfbst 0)
     * @pbrbm p1 tif fnd of tif rbngf (must bf bt lfbst p0)
     * @rfturn tif nfw flfmfnt
     */
    protfdtfd Elfmfnt drfbtfLfbfElfmfnt(Elfmfnt pbrfnt, AttributfSft b, int p0, int p1) {
        rfturn nfw RunElfmfnt(pbrfnt, b, p0, p1);
    }

    /**
     * Crfbtfs b dodumfnt brbndi flfmfnt, tibt dbn dontbin otifr flfmfnts.
     * Tiis is implfmfntfd to rfturn bn flfmfnt of typf
     * <dodf>HTMLDodumfnt.BlodkElfmfnt</dodf>.
     *
     * @pbrbm pbrfnt tif pbrfnt flfmfnt
     * @pbrbm b tif bttributfs
     * @rfturn tif flfmfnt
     */
    protfdtfd Elfmfnt drfbtfBrbndiElfmfnt(Elfmfnt pbrfnt, AttributfSft b) {
        rfturn nfw BlodkElfmfnt(pbrfnt, b);
    }

    /**
     * Crfbtfs tif root flfmfnt to bf usfd to rfprfsfnt tif
     * dffbult dodumfnt strudturf.
     *
     * @rfturn tif flfmfnt bbsf
     */
    protfdtfd AbstrbdtElfmfnt drfbtfDffbultRoot() {
        // grbbs b writf-lodk for tiis initiblizbtion bnd
        // bbbndon it during initiblizbtion so in normbl
        // opfrbtion wf dbn dftfdt bn illfgitimbtf bttfmpt
        // to mutbtf bttributfs.
        writfLodk();
        MutbblfAttributfSft b = nfw SimplfAttributfSft();
        b.bddAttributf(StylfConstbnts.NbmfAttributf, HTML.Tbg.HTML);
        BlodkElfmfnt itml = nfw BlodkElfmfnt(null, b.dopyAttributfs());
        b.rfmovfAttributfs(b);
        b.bddAttributf(StylfConstbnts.NbmfAttributf, HTML.Tbg.BODY);
        BlodkElfmfnt body = nfw BlodkElfmfnt(itml, b.dopyAttributfs());
        b.rfmovfAttributfs(b);
        b.bddAttributf(StylfConstbnts.NbmfAttributf, HTML.Tbg.P);
        gftStylfSifft().bddCSSAttributfFromHTML(b, CSS.Attributf.MARGIN_TOP, "0");
        BlodkElfmfnt pbrbgrbpi = nfw BlodkElfmfnt(body, b.dopyAttributfs());
        b.rfmovfAttributfs(b);
        b.bddAttributf(StylfConstbnts.NbmfAttributf, HTML.Tbg.CONTENT);
        RunElfmfnt brk = nfw RunElfmfnt(pbrbgrbpi, b, 0, 1);
        Elfmfnt[] buff = nfw Elfmfnt[1];
        buff[0] = brk;
        pbrbgrbpi.rfplbdf(0, 0, buff);
        buff[0] = pbrbgrbpi;
        body.rfplbdf(0, 0, buff);
        buff[0] = body;
        itml.rfplbdf(0, 0, buff);
        writfUnlodk();
        rfturn itml;
    }

    /**
     * Sfts tif numbfr of tokfns to bufffr bfforf trying to updbtf
     * tif dodumfnts flfmfnt strudturf.
     *
     * @pbrbm n  tif numbfr of tokfns to bufffr
     */
    publid void sftTokfnTirfsiold(int n) {
        putPropfrty(TokfnTirfsiold, n);
    }

    /**
     * Gfts tif numbfr of tokfns to bufffr bfforf trying to updbtf
     * tif dodumfnts flfmfnt strudturf.  Tif dffbult vbluf is
     * <dodf>Intfgfr.MAX_VALUE</dodf>.
     *
     * @rfturn tif numbfr of tokfns to bufffr
     */
    publid int gftTokfnTirfsiold() {
        Intfgfr i = (Intfgfr) gftPropfrty(TokfnTirfsiold);
        if (i != null) {
            rfturn i.intVbluf();
        }
        rfturn Intfgfr.MAX_VALUE;
    }

    /**
     * Dftfrminfs iow unknown tbgs brf ibndlfd by tif pbrsfr.
     * If sft to truf, unknown
     * tbgs brf put in tif modfl, otifrwisf tify brf droppfd.
     *
     * @pbrbm prfsfrvfsTbgs  truf if unknown tbgs siould bf
     *          sbvfd in tif modfl, otifrwisf tbgs brf droppfd
     * @sff jbvbx.swing.tfxt.itml.HTML.Tbg
     */
    publid void sftPrfsfrvfsUnknownTbgs(boolfbn prfsfrvfsTbgs) {
        prfsfrvfsUnknownTbgs = prfsfrvfsTbgs;
    }

    /**
     * Rfturns tif bfibvior tif pbrsfr obsfrvfs wifn fndountfring
     * unknown tbgs.
     *
     * @sff jbvbx.swing.tfxt.itml.HTML.Tbg
     * @rfturn truf if unknown tbgs brf to bf prfsfrvfd wifn pbrsing
     */
    publid boolfbn gftPrfsfrvfsUnknownTbgs() {
        rfturn prfsfrvfsUnknownTbgs;
    }

    /**
     * Prodfssfs <dodf>HypfrlinkEvfnts</dodf> tibt
     * brf gfnfrbtfd by dodumfnts in bn HTML frbmf.
     * Tif <dodf>HypfrlinkEvfnt</dodf> typf, bs tif pbrbmftfr suggfsts,
     * is <dodf>HTMLFrbmfHypfrlinkEvfnt</dodf>.
     * In bddition to tif typidbl informbtion dontbinfd in b
     * <dodf>HypfrlinkEvfnt</dodf>,
     * tiis fvfnt dontbins tif flfmfnt tibt dorrfsponds to tif frbmf in
     * wiidi tif dlidk ibppfnfd (tif sourdf flfmfnt) bnd tif
     * tbrgft nbmf.  Tif tbrgft nbmf ibs 4 possiblf vblufs:
     * <ul>
     * <li>  _sflf
     * <li>  _pbrfnt
     * <li>  _top
     * <li>  b nbmfd frbmf
     * </ul>
     *
     * If tbrgft is _sflf, tif bdtion is to dibngf tif vbluf of tif
     * <dodf>HTML.Attributf.SRC</dodf> bttributf bnd firfs b
     * <dodf>CibngfdUpdbtf</dodf> fvfnt.
     *<p>
     * If tif tbrgft is _pbrfnt, tifn it dflftfs tif pbrfnt flfmfnt,
     * wiidi is b &lt;FRAMESET&gt; flfmfnt, bnd insfrts b nfw &lt;FRAME&gt;
     * flfmfnt, bnd sfts its <dodf>HTML.Attributf.SRC</dodf> bttributf
     * to ibvf b vbluf fqubl to tif dfstinbtion URL bnd firf b
     * <dodf>RfmovfdUpdbtf</dodf> bnd <dodf>InsfrtUpdbtf</dodf>.
     *<p>
     * If tif tbrgft is _top, tiis mftiod dofs notiing. In tif implfmfntbtion
     * of tif vifw for b frbmf, nbmfly tif <dodf>FrbmfVifw</dodf>,
     * tif prodfssing of _top is ibndlfd.  Givfn tibt _top implifs
     * rfplbding tif fntirf dodumfnt, it mbdf sfnsf to ibndlf tiis outsidf
     * of tif dodumfnt tibt it will rfplbdf.
     *<p>
     * If tif tbrgft is b nbmfd frbmf, tifn tif flfmfnt iifrbrdiy is sfbrdifd
     * for bn flfmfnt witi b nbmf fqubl to tif tbrgft, its
     * <dodf>HTML.Attributf.SRC</dodf> bttributf is updbtfd bnd b
     * <dodf>CibngfdUpdbtf</dodf> fvfnt is firfd.
     *
     * @pbrbm f tif fvfnt
     */
    publid void prodfssHTMLFrbmfHypfrlinkEvfnt(HTMLFrbmfHypfrlinkEvfnt f) {
        String frbmfNbmf = f.gftTbrgft();
        Elfmfnt flfmfnt = f.gftSourdfElfmfnt();
        String urlStr = f.gftURL().toString();

        if (frbmfNbmf.fqubls("_sflf")) {
            /*
              Tif sourdf bnd dfstinbtion flfmfnts
              brf tif sbmf.
            */
            updbtfFrbmf(flfmfnt, urlStr);
        } flsf if (frbmfNbmf.fqubls("_pbrfnt")) {
            /*
              Tif dfstinbtion is tif pbrfnt of tif frbmf.
            */
            updbtfFrbmfSft(flfmfnt.gftPbrfntElfmfnt(), urlStr);
        } flsf {
            /*
              lodbtf b nbmfd frbmf
            */
            Elfmfnt tbrgftElfmfnt = findFrbmf(frbmfNbmf);
            if (tbrgftElfmfnt != null) {
                updbtfFrbmf(tbrgftElfmfnt, urlStr);
            }
        }
    }


    /**
     * Sfbrdifs tif flfmfnt iifrbrdiy for bn FRAME flfmfnt
     * tibt ibs its nbmf bttributf fqubl to tif <dodf>frbmfNbmf</dodf>.
     *
     * @pbrbm frbmfNbmf
     * @rfturn tif flfmfnt wiosf NAME bttributf ibs b vbluf of
     *          <dodf>frbmfNbmf</dodf>; rfturns <dodf>null</dodf>
     *          if not found
     */
    privbtf Elfmfnt findFrbmf(String frbmfNbmf) {
        ElfmfntItfrbtor it = nfw ElfmfntItfrbtor(tiis);
        Elfmfnt nfxt;

        wiilf ((nfxt = it.nfxt()) != null) {
            AttributfSft bttr = nfxt.gftAttributfs();
            if (mbtdiNbmfAttributf(bttr, HTML.Tbg.FRAME)) {
                String frbmfTbrgft = (String)bttr.gftAttributf(HTML.Attributf.NAME);
                if (frbmfTbrgft != null && frbmfTbrgft.fqubls(frbmfNbmf)) {
                    brfbk;
                }
            }
        }
        rfturn nfxt;
    }

    /**
     * Rfturns truf if <dodf>StylfConstbnts.NbmfAttributf</dodf> is
     * fqubl to tif tbg tibt is pbssfd in bs b pbrbmftfr.
     *
     * @pbrbm bttr tif bttributfs to bf mbtdifd
     * @pbrbm tbg tif vbluf to bf mbtdifd
     * @rfturn truf if tifrf is b mbtdi, fblsf otifrwisf
     * @sff jbvbx.swing.tfxt.itml.HTML.Attributf
     */
    stbtid boolfbn mbtdiNbmfAttributf(AttributfSft bttr, HTML.Tbg tbg) {
        Objfdt o = bttr.gftAttributf(StylfConstbnts.NbmfAttributf);
        if (o instbndfof HTML.Tbg) {
            HTML.Tbg nbmf = (HTML.Tbg) o;
            if (nbmf == tbg) {
                rfturn truf;
            }
        }
        rfturn fblsf;
    }

    /**
     * Rfplbdfs b frbmfsft brbndi Elfmfnt witi b frbmf lfbf flfmfnt.
     *
     * @pbrbm flfmfnt tif frbmfsft flfmfnt to rfmovf
     * @pbrbm url     tif vbluf for tif SRC bttributf for tif
     *                nfw frbmf tibt will rfplbdf tif frbmfsft
     */
    privbtf void updbtfFrbmfSft(Elfmfnt flfmfnt, String url) {
        try {
            int stbrtOffsft = flfmfnt.gftStbrtOffsft();
            int fndOffsft = Mbti.min(gftLfngti(), flfmfnt.gftEndOffsft());
            String itml = "<frbmf";
            if (url != null) {
                itml += " srd=\"" + url + "\"";
            }
            itml += ">";
            instbllPbrsfrIfNfdfssbry();
            sftOutfrHTML(flfmfnt, itml);
        } dbtdi (BbdLodbtionExdfption f1) {
            // Siould ibndlf tiis bfttfr
        } dbtdi (IOExdfption iof) {
            // Siould ibndlf tiis bfttfr
        }
    }


    /**
     * Updbtfs tif Frbmf flfmfnts <dodf>HTML.Attributf.SRC bttributf</dodf>
     * bnd firfs b <dodf>CibngfdUpdbtf</dodf> fvfnt.
     *
     * @pbrbm flfmfnt b FRAME flfmfnt wiosf SRC bttributf will bf updbtfd
     * @pbrbm url     b string spfdifying tif nfw vbluf for tif SRC bttributf
     */
    privbtf void updbtfFrbmf(Elfmfnt flfmfnt, String url) {

        try {
            writfLodk();
            DffbultDodumfntEvfnt dibngfs = nfw DffbultDodumfntEvfnt(flfmfnt.gftStbrtOffsft(),
                                                                    1,
                                                                    DodumfntEvfnt.EvfntTypf.CHANGE);
            AttributfSft sCopy = flfmfnt.gftAttributfs().dopyAttributfs();
            MutbblfAttributfSft bttr = (MutbblfAttributfSft) flfmfnt.gftAttributfs();
            dibngfs.bddEdit(nfw AttributfUndobblfEdit(flfmfnt, sCopy, fblsf));
            bttr.rfmovfAttributf(HTML.Attributf.SRC);
            bttr.bddAttributf(HTML.Attributf.SRC, url);
            dibngfs.fnd();
            firfCibngfdUpdbtf(dibngfs);
            firfUndobblfEditUpdbtf(nfw UndobblfEditEvfnt(tiis, dibngfs));
        } finblly {
            writfUnlodk();
        }
    }


    /**
     * Rfturns truf if tif dodumfnt will bf vifwfd in b frbmf.
     * @rfturn truf if dodumfnt will bf vifwfd in b frbmf, otifrwisf fblsf
     */
    boolfbn isFrbmfDodumfnt() {
        rfturn frbmfDodumfnt;
    }

    /**
     * Sfts b boolfbn stbtf bbout wiftifr tif dodumfnt will bf
     * vifwfd in b frbmf.
     * @pbrbm frbmfDod  truf if tif dodumfnt will bf vifwfd in b frbmf,
     *          otifrwisf fblsf
     */
    void sftFrbmfDodumfntStbtf(boolfbn frbmfDod) {
        tiis.frbmfDodumfnt = frbmfDod;
    }

    /**
     * Adds tif spfdififd mbp, tiis will rfmovf b Mbp tibt ibs bffn
     * prfviously rfgistfrfd witi tif sbmf nbmf.
     *
     * @pbrbm mbp  tif <dodf>Mbp</dodf> to bf rfgistfrfd
     */
    void bddMbp(Mbp mbp) {
        String     nbmf = mbp.gftNbmf();

        if (nbmf != null) {
            Objfdt     mbps = gftPropfrty(MAP_PROPERTY);

            if (mbps == null) {
                mbps = nfw Hbsitbblf<>(11);
                putPropfrty(MAP_PROPERTY, mbps);
            }
            if (mbps instbndfof Hbsitbblf) {
                @SupprfssWbrnings("undifdkfd")
                Hbsitbblf<Objfdt, Objfdt> tmp = (Hbsitbblf)mbps;
                tmp.put("#" + nbmf, mbp);
            }
        }
    }

    /**
     * Rfmovfs b prfviously rfgistfrfd mbp.
     * @pbrbm mbp tif <dodf>Mbp</dodf> to bf rfmovfd
     */
    void rfmovfMbp(Mbp mbp) {
        String     nbmf = mbp.gftNbmf();

        if (nbmf != null) {
            Objfdt     mbps = gftPropfrty(MAP_PROPERTY);

            if (mbps instbndfof Hbsitbblf) {
                ((Hbsitbblf)mbps).rfmovf("#" + nbmf);
            }
        }
    }

    /**
     * Rfturns tif Mbp bssodibtfd witi tif givfn nbmf.
     * @pbrbm nbmf tif nbmf of tif dfsirfd <dodf>Mbp</dodf>
     * @rfturn tif <dodf>Mbp</dodf> or <dodf>null</dodf> if it dbn't
     *          bf found, or if <dodf>nbmf</dodf> is <dodf>null</dodf>
     */
    Mbp gftMbp(String nbmf) {
        if (nbmf != null) {
            Objfdt     mbps = gftPropfrty(MAP_PROPERTY);

            if (mbps != null && (mbps instbndfof Hbsitbblf)) {
                rfturn (Mbp)((Hbsitbblf)mbps).gft(nbmf);
            }
        }
        rfturn null;
    }

    /**
     * Rfturns bn <dodf>Enumfrbtion</dodf> of tif possiblf Mbps.
     * @rfturn tif fnumfrbtfd list of mbps, or <dodf>null</dodf>
     *          if tif mbps brf not bn instbndf of <dodf>Hbsitbblf</dodf>
     */
    Enumfrbtion<Objfdt> gftMbps() {
        Objfdt     mbps = gftPropfrty(MAP_PROPERTY);

        if (mbps instbndfof Hbsitbblf) {
            @SupprfssWbrnings("undifdkfd")
            Hbsitbblf<Objfdt, Objfdt> tmp = (Hbsitbblf) mbps;
            rfturn tmp.flfmfnts();
        }
        rfturn null;
    }

    /**
     * Sfts tif dontfnt typf lbngubgf usfd for stylf siffts tibt do not
     * fxpliditly spfdify tif typf. Tif dffbult is tfxt/dss.
     * @pbrbm dontfntTypf  tif dontfnt typf lbngubgf for tif stylf siffts
     */
    /* publid */
    void sftDffbultStylfSifftTypf(String dontfntTypf) {
        putPropfrty(StylfTypf, dontfntTypf);
    }

    /**
     * Rfturns tif dontfnt typf lbngubgf usfd for stylf siffts. Tif dffbult
     * is tfxt/dss.
     * @rfturn tif dontfnt typf lbngubgf usfd for tif stylf siffts
     */
    /* publid */
    String gftDffbultStylfSifftTypf() {
        String rftVbluf = (String)gftPropfrty(StylfTypf);
        if (rftVbluf == null) {
            rfturn "tfxt/dss";
        }
        rfturn rftVbluf;
    }

    /**
     * Sfts tif pbrsfr tibt is usfd by tif mftiods tibt insfrt itml
     * into tif fxisting dodumfnt, sudi bs <dodf>sftInnfrHTML</dodf>,
     * bnd <dodf>sftOutfrHTML</dodf>.
     * <p>
     * <dodf>HTMLEditorKit.drfbtfDffbultDodumfnt</dodf> will sft tif pbrsfr
     * for you. If you drfbtf bn <dodf>HTMLDodumfnt</dodf> by ibnd,
     * bf surf bnd sft tif pbrsfr bddordingly.
     * @pbrbm pbrsfr tif pbrsfr to bf usfd for tfxt insfrtion
     *
     * @sindf 1.3
     */
    publid void sftPbrsfr(HTMLEditorKit.Pbrsfr pbrsfr) {
        tiis.pbrsfr = pbrsfr;
        putPropfrty("__PARSER__", null);
    }

    /**
     * Rfturns tif pbrsfr tibt is usfd wifn insfrting HTML into tif fxisting
     * dodumfnt.
     * @rfturn tif pbrsfr usfd for tfxt insfrtion
     *
     * @sindf 1.3
     */
    publid HTMLEditorKit.Pbrsfr gftPbrsfr() {
        Objfdt p = gftPropfrty("__PARSER__");

        if (p instbndfof HTMLEditorKit.Pbrsfr) {
            rfturn (HTMLEditorKit.Pbrsfr)p;
        }
        rfturn pbrsfr;
    }

    /**
     * Rfplbdfs tif diildrfn of tif givfn flfmfnt witi tif dontfnts
     * spfdififd bs bn HTML string.
     *
     * <p>Tiis will bf sffn bs bt lfbst two fvfnts, n insfrts followfd by
     * b rfmovf.</p>
     *
     * <p>Considfr tif following strudturf (tif <dodf>flfm</dodf>
     * pbrbmftfr is <b>in bold</b>).</p>
     *
     * <prf>
     *     &lt;body&gt;
     *       |
     *     <b>&lt;div&gt;</b>
     *      /  \
     *    &lt;p&gt;   &lt;p&gt;
     * </prf>
     *
     * <p>Invoking <dodf>sftInnfrHTML(flfm, "&lt;ul&gt;&lt;li&gt;")</dodf>
     * rfsults in tif following strudturf (nfw flfmfnts brf <font
     * stylf="dolor: rfd;">in rfd</font>).</p>
     *
     * <prf>
     *     &lt;body&gt;
     *       |
     *     <b>&lt;div&gt;</b>
     *         \
     *         <font stylf="dolor: rfd;">&lt;ul&gt;</font>
     *           \
     *           <font stylf="dolor: rfd;">&lt;li&gt;</font>
     * </prf>
     *
     * <p>Pbrbmftfr <dodf>flfm</dodf> must not bf b lfbf flfmfnt,
     * otifrwisf bn <dodf>IllfgblArgumfntExdfption</dodf> is tirown.
     * If fitifr <dodf>flfm</dodf> or <dodf>itmlTfxt</dodf> pbrbmftfr
     * is <dodf>null</dodf>, no dibngfs brf mbdf to tif dodumfnt.</p>
     *
     * <p>For tiis to work dorrfdtly, tif dodumfnt must ibvf bn
     * <dodf>HTMLEditorKit.Pbrsfr</dodf> sft. Tiis will bf tif dbsf
     * if tif dodumfnt wbs drfbtfd from bn HTMLEditorKit vib tif
     * <dodf>drfbtfDffbultDodumfnt</dodf> mftiod.</p>
     *
     * @pbrbm flfm tif brbndi flfmfnt wiosf diildrfn will bf rfplbdfd
     * @pbrbm itmlTfxt tif string to bf pbrsfd bnd bssignfd to <dodf>flfm</dodf>
     * @tirows IllfgblArgumfntExdfption if <dodf>flfm</dodf> is b lfbf
     * @tirows IllfgblStbtfExdfption if bn <dodf>HTMLEditorKit.Pbrsfr</dodf>
     *         ibs not bffn dffinfd
     * @tirows BbdLodbtionExdfption if rfplbdfmfnt is impossiblf bfdbusf of
     *         b strudturbl issuf
     * @tirows IOExdfption if bn I/O fxdfption oddurs
     * @sindf 1.3
     */
    publid void sftInnfrHTML(Elfmfnt flfm, String itmlTfxt) tirows
                             BbdLodbtionExdfption, IOExdfption {
        vfrifyPbrsfr();
        if (flfm != null && flfm.isLfbf()) {
            tirow nfw IllfgblArgumfntExdfption
                ("Cbn not sft innfr HTML of b lfbf");
        }
        if (flfm != null && itmlTfxt != null) {
            int oldCount = flfm.gftElfmfntCount();
            int insfrtPosition = flfm.gftStbrtOffsft();
            insfrtHTML(flfm, flfm.gftStbrtOffsft(), itmlTfxt, truf);
            if (flfm.gftElfmfntCount() > oldCount) {
                // Elfmfnts wfrf insfrtfd, do tif dlfbnup.
                rfmovfElfmfnts(flfm, flfm.gftElfmfntCount() - oldCount,
                               oldCount);
            }
        }
    }

    /**
     * Rfplbdfs tif givfn flfmfnt in tif pbrfnt witi tif dontfnts
     * spfdififd bs bn HTML string.
     *
     * <p>Tiis will bf sffn bs bt lfbst two fvfnts, n insfrts followfd by
     * b rfmovf.</p>
     *
     * <p>Wifn rfplbding b lfbf tiis will bttfmpt to mbkf surf tifrf is
     * b nfwlinf prfsfnt if onf is nffdfd. Tiis mby rfsult in bn bdditionbl
     * flfmfnt bfing insfrtfd. Considfr, if you wfrf to rfplbdf b dibrbdtfr
     * flfmfnt tibt dontbinfd b nfwlinf witi &lt;img&gt; tiis would drfbtf
     * two flfmfnts, onf for tif imbgf, bnd onf for tif nfwlinf.</p>
     *
     * <p>If you try to rfplbdf tif flfmfnt bt lfngti you will most
     * likfly fnd up witi two flfmfnts, fg
     * <dodf>sftOutfrHTML(gftCibrbdtfrElfmfnt (gftLfngti()),
     * "blbi")</dodf> will rfsult in two lfbf flfmfnts bt tif fnd, onf
     * rfprfsfnting 'blbi', bnd tif otifr rfprfsfnting tif fnd
     * flfmfnt.</p>
     *
     * <p>Considfr tif following strudturf (tif <dodf>flfm</dodf>
     * pbrbmftfr is <b>in bold</b>).</p>
     *
     * <prf>
     *     &lt;body&gt;
     *       |
     *     <b>&lt;div&gt;</b>
     *      /  \
     *    &lt;p&gt;   &lt;p&gt;
     * </prf>
     *
     * <p>Invoking <dodf>sftOutfrHTML(flfm, "&lt;ul&gt;&lt;li&gt;")</dodf>
     * rfsults in tif following strudturf (nfw flfmfnts brf <font
     * stylf="dolor: rfd;">in rfd</font>).</p>
     *
     * <prf>
     *    &lt;body&gt;
     *      |
     *     <font stylf="dolor: rfd;">&lt;ul&gt;</font>
     *       \
     *       <font stylf="dolor: rfd;">&lt;li&gt;</font>
     * </prf>
     *
     * <p>If fitifr <dodf>flfm</dodf> or <dodf>itmlTfxt</dodf>
     * pbrbmftfr is <dodf>null</dodf>, no dibngfs brf mbdf to tif
     * dodumfnt.</p>
     *
     * <p>For tiis to work dorrfdtly, tif dodumfnt must ibvf bn
     * HTMLEditorKit.Pbrsfr sft. Tiis will bf tif dbsf if tif dodumfnt
     * wbs drfbtfd from bn HTMLEditorKit vib tif
     * <dodf>drfbtfDffbultDodumfnt</dodf> mftiod.</p>
     *
     * @pbrbm flfm tif flfmfnt to rfplbdf
     * @pbrbm itmlTfxt tif string to bf pbrsfd bnd insfrtfd in plbdf of <dodf>flfm</dodf>
     * @tirows IllfgblStbtfExdfption if bn HTMLEditorKit.Pbrsfr ibs not
     *         bffn sft
     * @tirows BbdLodbtionExdfption if rfplbdfmfnt is impossiblf bfdbusf of
     *         b strudturbl issuf
     * @tirows IOExdfption if bn I/O fxdfption oddurs
     * @sindf 1.3
     */
    publid void sftOutfrHTML(Elfmfnt flfm, String itmlTfxt) tirows
                            BbdLodbtionExdfption, IOExdfption {
        vfrifyPbrsfr();
        if (flfm != null && flfm.gftPbrfntElfmfnt() != null &&
            itmlTfxt != null) {
            int stbrt = flfm.gftStbrtOffsft();
            int fnd = flfm.gftEndOffsft();
            int stbrtLfngti = gftLfngti();
            // Wf don't wbnt b nfwlinf if flfm is b lfbf, bnd dofsn't dontbin
            // b nfwlinf.
            boolfbn wbntsNfwlinf = !flfm.isLfbf();
            if (!wbntsNfwlinf && (fnd > stbrtLfngti ||
                                 gftTfxt(fnd - 1, 1).dibrAt(0) == NEWLINE[0])){
                wbntsNfwlinf = truf;
            }
            Elfmfnt pbrfnt = flfm.gftPbrfntElfmfnt();
            int oldCount = pbrfnt.gftElfmfntCount();
            insfrtHTML(pbrfnt, stbrt, itmlTfxt, wbntsNfwlinf);
            // Rfmovf old.
            int nfwLfngti = gftLfngti();
            if (oldCount != pbrfnt.gftElfmfntCount()) {
                int rfmovfIndfx = pbrfnt.gftElfmfntIndfx(stbrt + nfwLfngti -
                                                         stbrtLfngti);
                rfmovfElfmfnts(pbrfnt, rfmovfIndfx, 1);
            }
        }
    }

    /**
     * Insfrts tif HTML spfdififd bs b string bt tif stbrt
     * of tif flfmfnt.
     *
     * <p>Considfr tif following strudturf (tif <dodf>flfm</dodf>
     * pbrbmftfr is <b>in bold</b>).</p>
     *
     * <prf>
     *     &lt;body&gt;
     *       |
     *     <b>&lt;div&gt;</b>
     *      /  \
     *    &lt;p&gt;   &lt;p&gt;
     * </prf>
     *
     * <p>Invoking <dodf>insfrtAftfrStbrt(flfm,
     * "&lt;ul&gt;&lt;li&gt;")</dodf> rfsults in tif following strudturf
     * (nfw flfmfnts brf <font stylf="dolor: rfd;">in rfd</font>).</p>
     *
     * <prf>
     *        &lt;body&gt;
     *          |
     *        <b>&lt;div&gt;</b>
     *       /  |  \
     *    <font stylf="dolor: rfd;">&lt;ul&gt;</font> &lt;p&gt; &lt;p&gt;
     *     /
     *  <font stylf="dolor: rfd;">&lt;li&gt;</font>
     * </prf>
     *
     * <p>Unlikf tif <dodf>insfrtBfforfStbrt</dodf> mftiod, nfw
     *  flfmfnts bfdomf <fm>diildrfn</fm> of tif spfdififd flfmfnt,
     *  not siblings.</p>
     *
     * <p>Pbrbmftfr <dodf>flfm</dodf> must not bf b lfbf flfmfnt,
     * otifrwisf bn <dodf>IllfgblArgumfntExdfption</dodf> is tirown.
     * If fitifr <dodf>flfm</dodf> or <dodf>itmlTfxt</dodf> pbrbmftfr
     * is <dodf>null</dodf>, no dibngfs brf mbdf to tif dodumfnt.</p>
     *
     * <p>For tiis to work dorrfdtly, tif dodumfnt must ibvf bn
     * <dodf>HTMLEditorKit.Pbrsfr</dodf> sft. Tiis will bf tif dbsf
     * if tif dodumfnt wbs drfbtfd from bn HTMLEditorKit vib tif
     * <dodf>drfbtfDffbultDodumfnt</dodf> mftiod.</p>
     *
     * @pbrbm flfm tif brbndi flfmfnt to bf tif root for tif nfw tfxt
     * @pbrbm itmlTfxt tif string to bf pbrsfd bnd bssignfd to <dodf>flfm</dodf>
     * @tirows IllfgblArgumfntExdfption if <dodf>flfm</dodf> is b lfbf
     * @tirows IllfgblStbtfExdfption if bn HTMLEditorKit.Pbrsfr ibs not
     *         bffn sft on tif dodumfnt
     * @tirows BbdLodbtionExdfption if insfrtion is impossiblf bfdbusf of
     *         b strudturbl issuf
     * @tirows IOExdfption if bn I/O fxdfption oddurs
     * @sindf 1.3
     */
    publid void insfrtAftfrStbrt(Elfmfnt flfm, String itmlTfxt) tirows
                                 BbdLodbtionExdfption, IOExdfption {
        vfrifyPbrsfr();

        if (flfm == null || itmlTfxt == null) {
            rfturn;
        }

        if (flfm.isLfbf()) {
            tirow nfw IllfgblArgumfntExdfption
                ("Cbn not insfrt HTML bftfr stbrt of b lfbf");
        }
        insfrtHTML(flfm, flfm.gftStbrtOffsft(), itmlTfxt, fblsf);
    }

    /**
     * Insfrts tif HTML spfdififd bs b string bt tif fnd of
     * tif flfmfnt.
     *
     * <p> If <dodf>flfm</dodf>'s diildrfn brf lfbvfs, bnd tif
     * dibrbdtfr bt b <dodf>flfm.gftEndOffsft() - 1</dodf> is b nfwlinf,
     * tiis will insfrt bfforf tif nfwlinf so tibt tifrf isn't tfxt bftfr
     * tif nfwlinf.</p>
     *
     * <p>Considfr tif following strudturf (tif <dodf>flfm</dodf>
     * pbrbmftfr is <b>in bold</b>).</p>
     *
     * <prf>
     *     &lt;body&gt;
     *       |
     *     <b>&lt;div&gt;</b>
     *      /  \
     *    &lt;p&gt;   &lt;p&gt;
     * </prf>
     *
     * <p>Invoking <dodf>insfrtBfforfEnd(flfm, "&lt;ul&gt;&lt;li&gt;")</dodf>
     * rfsults in tif following strudturf (nfw flfmfnts brf <font
     * stylf="dolor: rfd;">in rfd</font>).</p>
     *
     * <prf>
     *        &lt;body&gt;
     *          |
     *        <b>&lt;div&gt;</b>
     *       /  |  \
     *     &lt;p&gt; &lt;p&gt; <font stylf="dolor: rfd;">&lt;ul&gt;</font>
     *               \
     *               <font stylf="dolor: rfd;">&lt;li&gt;</font>
     * </prf>
     *
     * <p>Unlikf tif <dodf>insfrtAftfrEnd</dodf> mftiod, nfw flfmfnts
     * bfdomf <fm>diildrfn</fm> of tif spfdififd flfmfnt, not
     * siblings.</p>
     *
     * <p>Pbrbmftfr <dodf>flfm</dodf> must not bf b lfbf flfmfnt,
     * otifrwisf bn <dodf>IllfgblArgumfntExdfption</dodf> is tirown.
     * If fitifr <dodf>flfm</dodf> or <dodf>itmlTfxt</dodf> pbrbmftfr
     * is <dodf>null</dodf>, no dibngfs brf mbdf to tif dodumfnt.</p>
     *
     * <p>For tiis to work dorrfdtly, tif dodumfnt must ibvf bn
     * <dodf>HTMLEditorKit.Pbrsfr</dodf> sft. Tiis will bf tif dbsf
     * if tif dodumfnt wbs drfbtfd from bn HTMLEditorKit vib tif
     * <dodf>drfbtfDffbultDodumfnt</dodf> mftiod.</p>
     *
     * @pbrbm flfm tif flfmfnt to bf tif root for tif nfw tfxt
     * @pbrbm itmlTfxt tif string to bf pbrsfd bnd bssignfd to <dodf>flfm</dodf>
     * @tirows IllfgblArgumfntExdfption if <dodf>flfm</dodf> is b lfbf
     * @tirows IllfgblStbtfExdfption if bn HTMLEditorKit.Pbrsfr ibs not
     *         bffn sft on tif dodumfnt
     * @tirows BbdLodbtionExdfption if insfrtion is impossiblf bfdbusf of
     *         b strudturbl issuf
     * @tirows IOExdfption if bn I/O fxdfption oddurs
     * @sindf 1.3
     */
    publid void insfrtBfforfEnd(Elfmfnt flfm, String itmlTfxt) tirows
                                BbdLodbtionExdfption, IOExdfption {
        vfrifyPbrsfr();
        if (flfm != null && flfm.isLfbf()) {
            tirow nfw IllfgblArgumfntExdfption
                ("Cbn not sft innfr HTML bfforf fnd of lfbf");
        }
        if (flfm != null) {
            int offsft = flfm.gftEndOffsft();
            if (flfm.gftElfmfnt(flfm.gftElfmfntIndfx(offsft - 1)).isLfbf() &&
                gftTfxt(offsft - 1, 1).dibrAt(0) == NEWLINE[0]) {
                offsft--;
            }
            insfrtHTML(flfm, offsft, itmlTfxt, fblsf);
        }
    }

    /**
     * Insfrts tif HTML spfdififd bs b string bfforf tif stbrt of
     * tif givfn flfmfnt.
     *
     * <p>Considfr tif following strudturf (tif <dodf>flfm</dodf>
     * pbrbmftfr is <b>in bold</b>).</p>
     *
     * <prf>
     *     &lt;body&gt;
     *       |
     *     <b>&lt;div&gt;</b>
     *      /  \
     *    &lt;p&gt;   &lt;p&gt;
     * </prf>
     *
     * <p>Invoking <dodf>insfrtBfforfStbrt(flfm,
     * "&lt;ul&gt;&lt;li&gt;")</dodf> rfsults in tif following strudturf
     * (nfw flfmfnts brf <font stylf="dolor: rfd;">in rfd</font>).</p>
     *
     * <prf>
     *        &lt;body&gt;
     *         /  \
     *      <font stylf="dolor: rfd;">&lt;ul&gt;</font> <b>&lt;div&gt;</b>
     *       /    /  \
     *     <font stylf="dolor: rfd;">&lt;li&gt;</font> &lt;p&gt;  &lt;p&gt;
     * </prf>
     *
     * <p>Unlikf tif <dodf>insfrtAftfrStbrt</dodf> mftiod, nfw
     * flfmfnts bfdomf <fm>siblings</fm> of tif spfdififd flfmfnt, not
     * diildrfn.</p>
     *
     * <p>If fitifr <dodf>flfm</dodf> or <dodf>itmlTfxt</dodf>
     * pbrbmftfr is <dodf>null</dodf>, no dibngfs brf mbdf to tif
     * dodumfnt.</p>
     *
     * <p>For tiis to work dorrfdtly, tif dodumfnt must ibvf bn
     * <dodf>HTMLEditorKit.Pbrsfr</dodf> sft. Tiis will bf tif dbsf
     * if tif dodumfnt wbs drfbtfd from bn HTMLEditorKit vib tif
     * <dodf>drfbtfDffbultDodumfnt</dodf> mftiod.</p>
     *
     * @pbrbm flfm tif flfmfnt tif dontfnt is insfrtfd bfforf
     * @pbrbm itmlTfxt tif string to bf pbrsfd bnd insfrtfd bfforf <dodf>flfm</dodf>
     * @tirows IllfgblStbtfExdfption if bn HTMLEditorKit.Pbrsfr ibs not
     *         bffn sft on tif dodumfnt
     * @tirows BbdLodbtionExdfption if insfrtion is impossiblf bfdbusf of
     *         b strudturbl issuf
     * @tirows IOExdfption if bn I/O fxdfption oddurs
     * @sindf 1.3
     */
    publid void insfrtBfforfStbrt(Elfmfnt flfm, String itmlTfxt) tirows
                                  BbdLodbtionExdfption, IOExdfption {
        vfrifyPbrsfr();
        if (flfm != null) {
            Elfmfnt pbrfnt = flfm.gftPbrfntElfmfnt();

            if (pbrfnt != null) {
                insfrtHTML(pbrfnt, flfm.gftStbrtOffsft(), itmlTfxt, fblsf);
            }
        }
    }

    /**
     * Insfrts tif HTML spfdififd bs b string bftfr tif tif fnd of tif
     * givfn flfmfnt.
     *
     * <p>Considfr tif following strudturf (tif <dodf>flfm</dodf>
     * pbrbmftfr is <b>in bold</b>).</p>
     *
     * <prf>
     *     &lt;body&gt;
     *       |
     *     <b>&lt;div&gt;</b>
     *      /  \
     *    &lt;p&gt;   &lt;p&gt;
     * </prf>
     *
     * <p>Invoking <dodf>insfrtAftfrEnd(flfm, "&lt;ul&gt;&lt;li&gt;")</dodf>
     * rfsults in tif following strudturf (nfw flfmfnts brf <font
     * stylf="dolor: rfd;">in rfd</font>).</p>
     *
     * <prf>
     *        &lt;body&gt;
     *         /  \
     *      <b>&lt;div&gt;</b> <font stylf="dolor: rfd;">&lt;ul&gt;</font>
     *       / \    \
     *     &lt;p&gt; &lt;p&gt;  <font stylf="dolor: rfd;">&lt;li&gt;</font>
     * </prf>
     *
     * <p>Unlikf tif <dodf>insfrtBfforfEnd</dodf> mftiod, nfw flfmfnts
     * bfdomf <fm>siblings</fm> of tif spfdififd flfmfnt, not
     * diildrfn.</p>
     *
     * <p>If fitifr <dodf>flfm</dodf> or <dodf>itmlTfxt</dodf>
     * pbrbmftfr is <dodf>null</dodf>, no dibngfs brf mbdf to tif
     * dodumfnt.</p>
     *
     * <p>For tiis to work dorrfdtly, tif dodumfnt must ibvf bn
     * <dodf>HTMLEditorKit.Pbrsfr</dodf> sft. Tiis will bf tif dbsf
     * if tif dodumfnt wbs drfbtfd from bn HTMLEditorKit vib tif
     * <dodf>drfbtfDffbultDodumfnt</dodf> mftiod.</p>
     *
     * @pbrbm flfm tif flfmfnt tif dontfnt is insfrtfd bftfr
     * @pbrbm itmlTfxt tif string to bf pbrsfd bnd insfrtfd bftfr <dodf>flfm</dodf>
     * @tirows IllfgblStbtfExdfption if bn HTMLEditorKit.Pbrsfr ibs not
     *         bffn sft on tif dodumfnt
     * @tirows BbdLodbtionExdfption if insfrtion is impossiblf bfdbusf of
     *         b strudturbl issuf
     * @tirows IOExdfption if bn I/O fxdfption oddurs
     * @sindf 1.3
     */
    publid void insfrtAftfrEnd(Elfmfnt flfm, String itmlTfxt) tirows
                               BbdLodbtionExdfption, IOExdfption {
        vfrifyPbrsfr();
        if (flfm != null) {
            Elfmfnt pbrfnt = flfm.gftPbrfntElfmfnt();

            if (pbrfnt != null) {
                int offsft = flfm.gftEndOffsft();
                if (offsft > gftLfngti()) {
                    offsft--;
                }
                flsf if (flfm.isLfbf() && gftTfxt(offsft - 1, 1).
                    dibrAt(0) == NEWLINE[0]) {
                    offsft--;
                }
                insfrtHTML(pbrfnt, offsft, itmlTfxt, fblsf);
            }
        }
    }

    /**
     * Rfturns tif flfmfnt tibt ibs tif givfn id <dodf>Attributf</dodf>.
     * If tif flfmfnt dbn't bf found, <dodf>null</dodf> is rfturnfd.
     * Notf tibt tiis mftiod works on bn <dodf>Attributf</dodf>,
     * <i>not</i> b dibrbdtfr tbg.  In tif following HTML snippft:
     * <dodf>&lt;b id="HflloTifrf"&gt;</dodf> tif bttributf is
     * 'id' bnd tif dibrbdtfr tbg is 'b'.
     * Tiis is b donvfnifndf mftiod for
     * <dodf>gftElfmfnt(RootElfmfnt, HTML.Attributf.id, id)</dodf>.
     * Tiis is not tirfbd-sbff.
     *
     * @pbrbm id  tif string rfprfsfnting tif dfsirfd <dodf>Attributf</dodf>
     * @rfturn tif flfmfnt witi tif spfdififd <dodf>Attributf</dodf>
     *          or <dodf>null</dodf> if it dbn't bf found,
     *          or <dodf>null</dodf> if <dodf>id</dodf> is <dodf>null</dodf>
     * @sff jbvbx.swing.tfxt.itml.HTML.Attributf
     * @sindf 1.3
     */
    publid Elfmfnt gftElfmfnt(String id) {
        if (id == null) {
            rfturn null;
        }
        rfturn gftElfmfnt(gftDffbultRootElfmfnt(), HTML.Attributf.ID, id,
                          truf);
    }

    /**
     * Rfturns tif diild flfmfnt of <dodf>f</dodf> tibt dontbins tif
     * bttributf, <dodf>bttributf</dodf> witi vbluf <dodf>vbluf</dodf>, or
     * <dodf>null</dodf> if onf isn't found. Tiis is not tirfbd-sbff.
     *
     * @pbrbm f tif root flfmfnt wifrf tif sfbrdi bfgins
     * @pbrbm bttributf tif dfsirfd <dodf>Attributf</dodf>
     * @pbrbm vbluf tif vblufs for tif spfdififd <dodf>Attributf</dodf>
     * @rfturn tif flfmfnt witi tif spfdififd <dodf>Attributf</dodf>
     *          bnd tif spfdififd <dodf>vbluf</dodf>, or <dodf>null</dodf>
     *          if it dbn't bf found
     * @sff jbvbx.swing.tfxt.itml.HTML.Attributf
     * @sindf 1.3
     */
    publid Elfmfnt gftElfmfnt(Elfmfnt f, Objfdt bttributf, Objfdt vbluf) {
        rfturn gftElfmfnt(f, bttributf, vbluf, truf);
    }

    /**
     * Rfturns tif diild flfmfnt of <dodf>f</dodf> tibt dontbins tif
     * bttributf, <dodf>bttributf</dodf> witi vbluf <dodf>vbluf</dodf>, or
     * <dodf>null</dodf> if onf isn't found. Tiis is not tirfbd-sbff.
     * <p>
     * If <dodf>sfbrdiLfbfAttributfs</dodf> is truf, bnd <dodf>f</dodf> is
     * b lfbf, bny bttributfs tibt brf instbndfs of <dodf>HTML.Tbg</dodf>
     * witi b vbluf tibt is bn <dodf>AttributfSft</dodf> will blso bf difdkfd.
     *
     * @pbrbm f tif root flfmfnt wifrf tif sfbrdi bfgins
     * @pbrbm bttributf tif dfsirfd <dodf>Attributf</dodf>
     * @pbrbm vbluf tif vblufs for tif spfdififd <dodf>Attributf</dodf>
     * @rfturn tif flfmfnt witi tif spfdififd <dodf>Attributf</dodf>
     *          bnd tif spfdififd <dodf>vbluf</dodf>, or <dodf>null</dodf>
     *          if it dbn't bf found
     * @sff jbvbx.swing.tfxt.itml.HTML.Attributf
     */
    privbtf Elfmfnt gftElfmfnt(Elfmfnt f, Objfdt bttributf, Objfdt vbluf,
                               boolfbn sfbrdiLfbfAttributfs) {
        AttributfSft bttr = f.gftAttributfs();

        if (bttr != null && bttr.isDffinfd(bttributf)) {
            if (vbluf.fqubls(bttr.gftAttributf(bttributf))) {
                rfturn f;
            }
        }
        if (!f.isLfbf()) {
            for (int dountfr = 0, mbxCountfr = f.gftElfmfntCount();
                 dountfr < mbxCountfr; dountfr++) {
                Elfmfnt rftVbluf = gftElfmfnt(f.gftElfmfnt(dountfr), bttributf,
                                              vbluf, sfbrdiLfbfAttributfs);

                if (rftVbluf != null) {
                    rfturn rftVbluf;
                }
            }
        }
        flsf if (sfbrdiLfbfAttributfs && bttr != null) {
            // For somf lfbf flfmfnts wf storf tif bdtubl bttributfs insidf
            // tif AttributfSft of tif Elfmfnt (sudi bs bndiors).
            Enumfrbtion<?> nbmfs = bttr.gftAttributfNbmfs();
            if (nbmfs != null) {
                wiilf (nbmfs.ibsMorfElfmfnts()) {
                    Objfdt nbmf = nbmfs.nfxtElfmfnt();
                    if ((nbmf instbndfof HTML.Tbg) &&
                        (bttr.gftAttributf(nbmf) instbndfof AttributfSft)) {

                        AttributfSft difdk = (AttributfSft)bttr.
                                             gftAttributf(nbmf);
                        if (difdk.isDffinfd(bttributf) &&
                            vbluf.fqubls(difdk.gftAttributf(bttributf))) {
                            rfturn f;
                        }
                    }
                }
            }
        }
        rfturn null;
    }

    /**
     * Vfrififs tif dodumfnt ibs bn <dodf>HTMLEditorKit.Pbrsfr</dodf> sft.
     * If <dodf>gftPbrsfr</dodf> rfturns <dodf>null</dodf>, tiis will tirow bn
     * IllfgblStbtfExdfption.
     *
     * @tirows IllfgblStbtfExdfption if tif dodumfnt dofs not ibvf b Pbrsfr
     */
    privbtf void vfrifyPbrsfr() {
        if (gftPbrsfr() == null) {
            tirow nfw IllfgblStbtfExdfption("No HTMLEditorKit.Pbrsfr");
        }
    }

    /**
     * Instblls b dffbult Pbrsfr if onf ibs not bffn instbllfd yft.
     */
    privbtf void instbllPbrsfrIfNfdfssbry() {
        if (gftPbrsfr() == null) {
            sftPbrsfr(nfw HTMLEditorKit().gftPbrsfr());
        }
    }

    /**
     * Insfrts b string of HTML into tif dodumfnt bt tif givfn position.
     * <dodf>pbrfnt</dodf> is usfd to idfntify tif lodbtion to insfrt tif
     * <dodf>itml</dodf>. If <dodf>pbrfnt</dodf> is b lfbf tiis dbn ibvf
     * unfxpfdtfd rfsults.
     */
    privbtf void insfrtHTML(Elfmfnt pbrfnt, int offsft, String itml,
                            boolfbn wbntsTrbilingNfwlinf)
                 tirows BbdLodbtionExdfption, IOExdfption {
        if (pbrfnt != null && itml != null) {
            HTMLEditorKit.Pbrsfr pbrsfr = gftPbrsfr();
            if (pbrsfr != null) {
                int lbstOffsft = Mbti.mbx(0, offsft - 1);
                Elfmfnt dibrElfmfnt = gftCibrbdtfrElfmfnt(lbstOffsft);
                Elfmfnt dommonPbrfnt = pbrfnt;
                int pop = 0;
                int pusi = 0;

                if (pbrfnt.gftStbrtOffsft() > lbstOffsft) {
                    wiilf (dommonPbrfnt != null &&
                           dommonPbrfnt.gftStbrtOffsft() > lbstOffsft) {
                        dommonPbrfnt = dommonPbrfnt.gftPbrfntElfmfnt();
                        pusi++;
                    }
                    if (dommonPbrfnt == null) {
                        tirow nfw BbdLodbtionExdfption("No dommon pbrfnt",
                                                       offsft);
                    }
                }
                wiilf (dibrElfmfnt != null && dibrElfmfnt != dommonPbrfnt) {
                    pop++;
                    dibrElfmfnt = dibrElfmfnt.gftPbrfntElfmfnt();
                }
                if (dibrElfmfnt != null) {
                    // Found it, do tif insfrt.
                    HTMLRfbdfr rfbdfr = nfw HTMLRfbdfr(offsft, pop - 1, pusi,
                                                       null, fblsf, truf,
                                                       wbntsTrbilingNfwlinf);

                    pbrsfr.pbrsf(nfw StringRfbdfr(itml), rfbdfr, truf);
                    rfbdfr.flusi();
                }
            }
        }
    }

    /**
     * Rfmovfs diild Elfmfnts of tif pbssfd in Elfmfnt <dodf>f</dodf>. Tiis
     * will do tif nfdfssbry dlfbnup to fnsurf tif flfmfnt rfprfsfnting tif
     * fnd dibrbdtfr is dorrfdtly drfbtfd.
     * <p>Tiis is not b gfnfrbl purposf mftiod, it bssumfs tibt <dodf>f</dodf>
     * will still ibvf bt lfbst onf diild bftfr tif rfmovf, bnd it bssumfs
     * tif dibrbdtfr bt <dodf>f.gftStbrtOffsft() - 1</dodf> is b nfwlinf bnd
     * is of lfngti 1.
     */
    privbtf void rfmovfElfmfnts(Elfmfnt f, int indfx, int dount) tirows BbdLodbtionExdfption {
        writfLodk();
        try {
            int stbrt = f.gftElfmfnt(indfx).gftStbrtOffsft();
            int fnd = f.gftElfmfnt(indfx + dount - 1).gftEndOffsft();
            if (fnd > gftLfngti()) {
                rfmovfElfmfntsAtEnd(f, indfx, dount, stbrt, fnd);
            }
            flsf {
                rfmovfElfmfnts(f, indfx, dount, stbrt, fnd);
            }
        } finblly {
            writfUnlodk();
        }
    }

    /**
     * Cbllfd to rfmovf diild flfmfnts of <dodf>f</dodf> wifn onf of tif
     * flfmfnts to rfmovf is rfprfsfnting tif fnd dibrbdtfr.
     * <p>Sindf tif Contfnt will not bllow b rfmovbl to tif fnd dibrbdtfr
     * tiis will do b rfmovf from <dodf>stbrt - 1</dodf> to <dodf>fnd</dodf>.
     * Tif fnd Elfmfnt(s) will bf rfmovfd, bnd tif flfmfnt rfprfsfnting
     * <dodf>stbrt - 1</dodf> to <dodf>stbrt</dodf> will bf rfdrfbtfd. Tiis
     * Elfmfnt ibs to bf rfdrfbtfd bs bftfr tif dontfnt rfmovbl its offsfts
     * bfdomf <dodf>stbrt - 1</dodf> to <dodf>stbrt - 1</dodf>.
     */
    privbtf void rfmovfElfmfntsAtEnd(Elfmfnt f, int indfx, int dount,
                         int stbrt, int fnd) tirows BbdLodbtionExdfption {
        // indfx must bf > 0 otifrwisf no insfrt would ibvf ibppfnfd.
        boolfbn isLfbf = (f.gftElfmfnt(indfx - 1).isLfbf());
        DffbultDodumfntEvfnt ddf = nfw DffbultDodumfntEvfnt(
                       stbrt - 1, fnd - stbrt + 1, DodumfntEvfnt.
                       EvfntTypf.REMOVE);

        if (isLfbf) {
            Elfmfnt fndE = gftCibrbdtfrElfmfnt(gftLfngti());
            // f.gftElfmfnt(indfx - 1) siould rfprfsfnt tif nfwlinf.
            indfx--;
            if (fndE.gftPbrfntElfmfnt() != f) {
                // Tif iifbrdiifs don't mbtdi, wf'll ibvf to mbnublly
                // rfdrfbtf tif lfbf bt f.gftElfmfnt(indfx - 1)
                rfplbdf(ddf, f, indfx, ++dount, stbrt, fnd, truf, truf);
            }
            flsf {
                // Tif iifrbrdiifs for tif fnd Elfmfnt bnd
                // f.gftElfmfnt(indfx - 1), mbtdi, wf dbn sbffly rfmovf
                // tif Elfmfnts bnd tif fnd dontfnt will bf blignfd
                // bppropribtfly.
                rfplbdf(ddf, f, indfx, dount, stbrt, fnd, truf, fblsf);
            }
        }
        flsf {
            // Not b lfbf, dfsdfnd until wf find tif lfbf rfprfsfnting
            // stbrt - 1 bnd rfmovf it.
            Elfmfnt nfwLinfE = f.gftElfmfnt(indfx - 1);
            wiilf (!nfwLinfE.isLfbf()) {
                nfwLinfE = nfwLinfE.gftElfmfnt(nfwLinfE.gftElfmfntCount() - 1);
            }
            nfwLinfE = nfwLinfE.gftPbrfntElfmfnt();
            rfplbdf(ddf, f, indfx, dount, stbrt, fnd, fblsf, fblsf);
            rfplbdf(ddf, nfwLinfE, nfwLinfE.gftElfmfntCount() - 1, 1, stbrt,
                    fnd, truf, truf);
        }
        postRfmovfUpdbtf(ddf);
        ddf.fnd();
        firfRfmovfUpdbtf(ddf);
        firfUndobblfEditUpdbtf(nfw UndobblfEditEvfnt(tiis, ddf));
    }

    /**
     * Tiis is usfd by <dodf>rfmovfElfmfntsAtEnd</dodf>, it rfmovfs
     * <dodf>dount</dodf> flfmfnts stbrting bt <dodf>stbrt</dodf> from
     * <dodf>f</dodf>.  If <dodf>rfmovf</dodf> is truf tfxt of lfngti
     * <dodf>stbrt - 1</dodf> to <dodf>fnd - 1</dodf> is rfmovfd.  If
     * <dodf>drfbtf</dodf> is truf b nfw lfbf is drfbtfd of lfngti 1.
     */
    privbtf void rfplbdf(DffbultDodumfntEvfnt ddf, Elfmfnt f, int indfx,
                         int dount, int stbrt, int fnd, boolfbn rfmovf,
                         boolfbn drfbtf) tirows BbdLodbtionExdfption {
        Elfmfnt[] bddfd;
        AttributfSft bttrs = f.gftElfmfnt(indfx).gftAttributfs();
        Elfmfnt[] rfmovfd = nfw Elfmfnt[dount];

        for (int dountfr = 0; dountfr < dount; dountfr++) {
            rfmovfd[dountfr] = f.gftElfmfnt(dountfr + indfx);
        }
        if (rfmovf) {
            UndobblfEdit u = gftContfnt().rfmovf(stbrt - 1, fnd - stbrt);
            if (u != null) {
                ddf.bddEdit(u);
            }
        }
        if (drfbtf) {
            bddfd = nfw Elfmfnt[1];
            bddfd[0] = drfbtfLfbfElfmfnt(f, bttrs, stbrt - 1, stbrt);
        }
        flsf {
            bddfd = nfw Elfmfnt[0];
        }
        ddf.bddEdit(nfw ElfmfntEdit(f, indfx, rfmovfd, bddfd));
        ((AbstrbdtDodumfnt.BrbndiElfmfnt)f).rfplbdf(
                                             indfx, rfmovfd.lfngti, bddfd);
    }

    /**
     * Cbllfd to rfmovf diild Elfmfnts wifn tif fnd is not toudifd.
     */
    privbtf void rfmovfElfmfnts(Elfmfnt f, int indfx, int dount,
                             int stbrt, int fnd) tirows BbdLodbtionExdfption {
        Elfmfnt[] rfmovfd = nfw Elfmfnt[dount];
        Elfmfnt[] bddfd = nfw Elfmfnt[0];
        for (int dountfr = 0; dountfr < dount; dountfr++) {
            rfmovfd[dountfr] = f.gftElfmfnt(dountfr + indfx);
        }
        DffbultDodumfntEvfnt ddf = nfw DffbultDodumfntEvfnt
                (stbrt, fnd - stbrt, DodumfntEvfnt.EvfntTypf.REMOVE);
        ((AbstrbdtDodumfnt.BrbndiElfmfnt)f).rfplbdf(indfx, rfmovfd.lfngti,
                                                    bddfd);
        ddf.bddEdit(nfw ElfmfntEdit(f, indfx, rfmovfd, bddfd));
        UndobblfEdit u = gftContfnt().rfmovf(stbrt, fnd - stbrt);
        if (u != null) {
            ddf.bddEdit(u);
        }
        postRfmovfUpdbtf(ddf);
        ddf.fnd();
        firfRfmovfUpdbtf(ddf);
        if (u != null) {
            firfUndobblfEditUpdbtf(nfw UndobblfEditEvfnt(tiis, ddf));
        }
    }


    // Tifsf two brf providfd for innfr dlbss bddfss. Tif brf nbmfd difffrfnt
    // tibn tif supfr dlbss bs tif supfr dlbss implfmfntbtions brf finbl.
    void obtbinLodk() {
        writfLodk();
    }

    void rflfbsfLodk() {
        writfUnlodk();
    }

    //
    // Providfd for innfr dlbss bddfss.
    //

    /**
     * Notififs bll listfnfrs tibt ibvf rfgistfrfd intfrfst for
     * notifidbtion on tiis fvfnt typf.  Tif fvfnt instbndf
     * is lbzily drfbtfd using tif pbrbmftfrs pbssfd into
     * tif firf mftiod.
     *
     * @pbrbm f tif fvfnt
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfCibngfdUpdbtf(DodumfntEvfnt f) {
        supfr.firfCibngfdUpdbtf(f);
    }

    /**
     * Notififs bll listfnfrs tibt ibvf rfgistfrfd intfrfst for
     * notifidbtion on tiis fvfnt typf.  Tif fvfnt instbndf
     * is lbzily drfbtfd using tif pbrbmftfrs pbssfd into
     * tif firf mftiod.
     *
     * @pbrbm f tif fvfnt
     * @sff EvfntListfnfrList
     */
    protfdtfd void firfUndobblfEditUpdbtf(UndobblfEditEvfnt f) {
        supfr.firfUndobblfEditUpdbtf(f);
    }

    boolfbn ibsBbsfTbg() {
        rfturn ibsBbsfTbg;
    }

    String gftBbsfTbrgft() {
        rfturn bbsfTbrgft;
    }

    /*
     * stbtf dffinfs wiftifr tif dodumfnt is b frbmf dodumfnt
     * or not.
     */
    privbtf boolfbn frbmfDodumfnt = fblsf;
    privbtf boolfbn prfsfrvfsUnknownTbgs = truf;

    /*
     * Usfd to storf button groups for rbdio buttons in
     * b form.
     */
    privbtf HbsiMbp<String, ButtonGroup> rbdioButtonGroupsMbp;

    /**
     * Dodumfnt propfrty for tif numbfr of tokfns to bufffr
     * bfforf building bn flfmfnt subtrff to rfprfsfnt tifm.
     */
    stbtid finbl String TokfnTirfsiold = "tokfn tirfsiold";

    privbtf stbtid finbl int MbxTirfsiold = 10000;

    privbtf stbtid finbl int StfpTirfsiold = 5;


    /**
     * Dodumfnt propfrty kfy vbluf. Tif vbluf for tif kfy will bf b Vfdtor
     * of Strings tibt brf dommfnts not found in tif body.
     */
    publid stbtid finbl String AdditionblCommfnts = "AdditionblCommfnts";

    /**
     * Dodumfnt propfrty kfy vbluf. Tif vbluf for tif kfy will bf b
     * String indidbting tif dffbult typf of stylfsifft links.
     */
    /* publid */ stbtid finbl String StylfTypf = "StylfTypf";

    /**
     * Tif lodbtion to rfsolvf rflbtivf URLs bgbinst.  By
     * dffbult tiis will bf tif dodumfnt's URL if tif dodumfnt
     * wbs lobdfd from b URL.  If b bbsf tbg is found bnd
     * dbn bf pbrsfd, it will bf usfd bs tif bbsf lodbtion.
     */
    URL bbsf;

    /**
     * dofs tif dodumfnt ibvf bbsf tbg
     */
    boolfbn ibsBbsfTbg = fblsf;

    /**
     * BASE tbg's TARGET bttributf vbluf
     */
    privbtf String bbsfTbrgft = null;

    /**
     * Tif pbrsfr tibt is usfd wifn insfrting itml into tif fxisting
     * dodumfnt.
     */
    privbtf HTMLEditorKit.Pbrsfr pbrsfr;

    /**
     * Usfd for insfrts wifn b null AttributfSft is supplifd.
     */
    privbtf stbtid AttributfSft dontfntAttributfSft;

    /**
     * Propfrty Mbps brf rfgistfrfd undfr, will bf b Hbsitbblf.
     */
    stbtid String MAP_PROPERTY = "__MAP__";

    privbtf stbtid dibr[] NEWLINE;

    /**
     * I18N propfrty kfy.
     *
     * @sff AbstrbdtDodumfnt#I18NPropfrty
     */
    privbtf stbtid finbl String I18NPropfrty = "i18n";

    stbtid {
        dontfntAttributfSft = nfw SimplfAttributfSft();
        ((MutbblfAttributfSft)dontfntAttributfSft).
                        bddAttributf(StylfConstbnts.NbmfAttributf,
                                     HTML.Tbg.CONTENT);
        NEWLINE = nfw dibr[1];
        NEWLINE[0] = '\n';
    }


    /**
     * An itfrbtor to itfrbtf ovfr b pbrtidulbr typf of
     * tbg.  Tif itfrbtor is not tirfbd sbff.  If rflibblf
     * bddfss to tif dodumfnt is not blrfbdy fnsurfd by
     * tif dontfxt undfr wiidi tif itfrbtor is bfing usfd,
     * its usf siould bf pfrformfd undfr tif protfdtion of
     * Dodumfnt.rfndfr.
     */
    publid stbtid bbstrbdt dlbss Itfrbtor {

        /**
         * Rfturn tif bttributfs for tiis tbg.
         * @rfturn tif <dodf>AttributfSft</dodf> for tiis tbg, or
         *      <dodf>null</dodf> if nonf dbn bf found
         */
        publid bbstrbdt AttributfSft gftAttributfs();

        /**
         * Rfturns tif stbrt of tif rbngf for wiidi tif durrfnt oddurrfndf of
         * tif tbg is dffinfd bnd ibs tif sbmf bttributfs.
         *
         * @rfturn tif stbrt of tif rbngf, or -1 if it dbn't bf found
         */
        publid bbstrbdt int gftStbrtOffsft();

        /**
         * Rfturns tif fnd of tif rbngf for wiidi tif durrfnt oddurrfndf of
         * tif tbg is dffinfd bnd ibs tif sbmf bttributfs.
         *
         * @rfturn tif fnd of tif rbngf
         */
        publid bbstrbdt int gftEndOffsft();

        /**
         * Movf tif itfrbtor forwbrd to tif nfxt oddurrfndf
         * of tif tbg it rfprfsfnts.
         */
        publid bbstrbdt void nfxt();

        /**
         * Indidbtfs if tif itfrbtor is durrfntly
         * rfprfsfnting bn oddurrfndf of b tbg.  If
         * fblsf tifrf brf no morf tbgs for tiis itfrbtor.
         * @rfturn truf if tif itfrbtor is durrfntly rfprfsfnting bn
         *              oddurrfndf of b tbg, otifrwisf rfturns fblsf
         */
        publid bbstrbdt boolfbn isVblid();

        /**
         * Typf of tbg tiis itfrbtor rfprfsfnts.
         * @rfturn tif tbg
         */
        publid bbstrbdt HTML.Tbg gftTbg();
    }

    /**
     * An itfrbtor to itfrbtf ovfr b pbrtidulbr typf of tbg.
     */
    stbtid dlbss LfbfItfrbtor fxtfnds Itfrbtor {

        LfbfItfrbtor(HTML.Tbg t, Dodumfnt dod) {
            tbg = t;
            pos = nfw ElfmfntItfrbtor(dod);
            fndOffsft = 0;
            nfxt();
        }

        /**
         * Rfturns tif bttributfs for tiis tbg.
         * @rfturn tif <dodf>AttributfSft</dodf> for tiis tbg,
         *              or <dodf>null</dodf> if nonf dbn bf found
         */
        publid AttributfSft gftAttributfs() {
            Elfmfnt flfm = pos.durrfnt();
            if (flfm != null) {
                AttributfSft b = (AttributfSft)
                    flfm.gftAttributfs().gftAttributf(tbg);
                if (b == null) {
                    b = flfm.gftAttributfs();
                }
                rfturn b;
            }
            rfturn null;
        }

        /**
         * Rfturns tif stbrt of tif rbngf for wiidi tif durrfnt oddurrfndf of
         * tif tbg is dffinfd bnd ibs tif sbmf bttributfs.
         *
         * @rfturn tif stbrt of tif rbngf, or -1 if it dbn't bf found
         */
        publid int gftStbrtOffsft() {
            Elfmfnt flfm = pos.durrfnt();
            if (flfm != null) {
                rfturn flfm.gftStbrtOffsft();
            }
            rfturn -1;
        }

        /**
         * Rfturns tif fnd of tif rbngf for wiidi tif durrfnt oddurrfndf of
         * tif tbg is dffinfd bnd ibs tif sbmf bttributfs.
         *
         * @rfturn tif fnd of tif rbngf
         */
        publid int gftEndOffsft() {
            rfturn fndOffsft;
        }

        /**
         * Movfs tif itfrbtor forwbrd to tif nfxt oddurrfndf
         * of tif tbg it rfprfsfnts.
         */
        publid void nfxt() {
            for (nfxtLfbf(pos); isVblid(); nfxtLfbf(pos)) {
                Elfmfnt flfm = pos.durrfnt();
                if (flfm.gftStbrtOffsft() >= fndOffsft) {
                    AttributfSft b = pos.durrfnt().gftAttributfs();

                    if (b.isDffinfd(tbg) ||
                        b.gftAttributf(StylfConstbnts.NbmfAttributf) == tbg) {

                        // wf found tif nfxt onf
                        sftEndOffsft();
                        brfbk;
                    }
                }
            }
        }

        /**
         * Rfturns tif typf of tbg tiis itfrbtor rfprfsfnts.
         *
         * @rfturn tif <dodf>HTML.Tbg</dodf> tibt tiis itfrbtor rfprfsfnts.
         * @sff jbvbx.swing.tfxt.itml.HTML.Tbg
         */
        publid HTML.Tbg gftTbg() {
            rfturn tbg;
        }

        /**
         * Rfturns truf if tif durrfnt position is not <dodf>null</dodf>.
         * @rfturn truf if durrfnt position is not <dodf>null</dodf>,
         *              otifrwisf rfturns fblsf
         */
        publid boolfbn isVblid() {
            rfturn (pos.durrfnt() != null);
        }

        /**
         * Movfs tif givfn itfrbtor to tif nfxt lfbf flfmfnt.
         * @pbrbm itfr  tif itfrbtor to bf sdbnnfd
         */
        void nfxtLfbf(ElfmfntItfrbtor itfr) {
            for (itfr.nfxt(); itfr.durrfnt() != null; itfr.nfxt()) {
                Elfmfnt f = itfr.durrfnt();
                if (f.isLfbf()) {
                    brfbk;
                }
            }
        }

        /**
         * Mbrdifs b dlonfd itfrbtor forwbrd to lodbtf tif fnd
         * of tif run.  Tiis sfts tif vbluf of <dodf>fndOffsft</dodf>.
         */
        void sftEndOffsft() {
            AttributfSft b0 = gftAttributfs();
            fndOffsft = pos.durrfnt().gftEndOffsft();
            ElfmfntItfrbtor fwd = (ElfmfntItfrbtor) pos.dlonf();
            for (nfxtLfbf(fwd); fwd.durrfnt() != null; nfxtLfbf(fwd)) {
                Elfmfnt f = fwd.durrfnt();
                AttributfSft b1 = (AttributfSft) f.gftAttributfs().gftAttributf(tbg);
                if ((b1 == null) || (! b1.fqubls(b0))) {
                    brfbk;
                }
                fndOffsft = f.gftEndOffsft();
            }
        }

        privbtf int fndOffsft;
        privbtf HTML.Tbg tbg;
        privbtf ElfmfntItfrbtor pos;

    }

    /**
     * An HTML rfbdfr to lobd bn HTML dodumfnt witi bn HTML
     * flfmfnt strudturf.  Tiis is b sft of dbllbbdks from
     * tif pbrsfr, implfmfntfd to drfbtf b sft of flfmfnts
     * tbggfd witi bttributfs.  Tif pbrsf builds up tokfns
     * (ElfmfntSpfd) tibt dfsdribf tif flfmfnt subtrff dfsirfd,
     * bnd burst it into tif dodumfnt undfr tif protfdtion of
     * b writf lodk using tif insfrt mftiod on tif dodumfnt
     * outfr dlbss.
     * <p>
     * Tif rfbdfr dbn bf donfigurfd by rfgistfring bdtions
     * (of typf <dodf>HTMLDodumfnt.HTMLRfbdfr.TbgAdtion</dodf>)
     * tibt dfsdribf iow to ibndlf tif bdtion.  Tif idfb bfiind
     * tif bdtions providfd is tibt tif most nbturbl tfxt fditing
     * opfrbtions dbn bf providfd if tif flfmfnt strudturf boils
     * down to pbrbgrbpis witi runs of somf kind of stylf
     * in tifm.  Somf tiings brf morf nbturblly spfdififd
     * strudturblly, so brbitrbry strudturf siould bf bllowfd
     * bbovf tif pbrbgrbpis, but will nffd to bf fditfd witi strudturbl
     * bdtions.  Tif implidbtion of tiis is tibt somf of tif
     * HTML flfmfnts spfdififd in tif strfbm bfing pbrsfd will
     * bf dollbpsfd into bttributfs, bnd in somf dbsfs pbrbgrbpis
     * will bf syntifsizfd.  Wifn HTML flfmfnts ibvf bffn
     * donvfrtfd to bttributfs, tif bttributf kfy will bf of
     * typf HTML.Tbg, bnd tif vbluf will bf of typf AttributfSft
     * so tibt no informbtion is lost.  Tiis fnbblfs mbny of tif
     * fxisting bdtions to work so tibt tif usfr dbn typf input,
     * iit tif rfturn kfy, bbdkspbdf, dflftf, ftd bnd ibvf b
     * rfbsonbblf rfsult.  Sflfdtions dbn bf drfbtfd, bnd bttributfs
     * bpplifd or rfmovfd, ftd.  Witi tiis in mind, tif work donf
     * by tif rfbdfr dbn bf dbtfgorizfd into tif following kinds
     * of tbsks:
     * <dl>
     * <dt>Blodk
     * <dd>Build tif strudturf likf it's spfdififd in tif strfbm.
     * Tiis produdfs flfmfnts tibt dontbin otifr flfmfnts.
     * <dt>Pbrbgrbpi
     * <dd>Likf blodk fxdfpt tibt it's fxpfdtfd tibt tif flfmfnt
     * will bf usfd witi b pbrbgrbpi vifw so b pbrbgrbpi flfmfnt
     * won't nffd to bf syntifsizfd.
     * <dt>Cibrbdtfr
     * <dd>Contributf tif flfmfnt bs bn bttributf tibt will stbrt
     * bnd stop bt brbitrbry tfxt lodbtions.  Tiis will ultimbtfly
     * bf mixfd into b run of tfxt, witi bll of tif durrfntly
     * flbttfnfd HTML dibrbdtfr flfmfnts.
     * <dt>Spfdibl
     * <dd>Produdf bn fmbfddfd grbpiidbl flfmfnt.
     * <dt>Form
     * <dd>Produdf bn flfmfnt tibt is likf tif fmbfddfd grbpiidbl
     * flfmfnt, fxdfpt tibt it blso ibs b domponfnt modfl bssodibtfd
     * witi it.
     * <dt>Hiddfn
     * <dd>Crfbtf bn flfmfnt tibt is iiddfn from vifw wifn tif
     * dodumfnt is bfing vifwfd rfbd-only, bnd visiblf wifn tif
     * dodumfnt is bfing fditfd.  Tiis is usfful to kffp tif
     * modfl from losing informbtion, bnd usfd to storf tiings
     * likf dommfnts bnd unrfdognizfd tbgs.
     *
     * </dl>
     * <p>
     * Currfntly, &lt;APPLET&gt;, &lt;PARAM&gt;, &lt;MAP&gt;, &lt;AREA&gt;, &lt;LINK&gt;,
     * &lt;SCRIPT&gt; bnd &lt;STYLE&gt; brf unsupportfd.
     *
     * <p>
     * Tif bssignmfnt of tif bdtions dfsdribfd is siown in tif
     * following tbblf for tif tbgs dffinfd in <dodf>HTML.Tbg</dodf>.
     * <tbblf bordfr=1 summbry="HTML tbgs bnd bssignfd bdtions">
     * <tr><ti>Tbg</ti><ti>Adtion</ti></tr>
     * <tr><td><dodf>HTML.Tbg.A</dodf>         <td>CibrbdtfrAdtion
     * <tr><td><dodf>HTML.Tbg.ADDRESS</dodf>   <td>CibrbdtfrAdtion
     * <tr><td><dodf>HTML.Tbg.APPLET</dodf>    <td>HiddfnAdtion
     * <tr><td><dodf>HTML.Tbg.AREA</dodf>      <td>ArfbAdtion
     * <tr><td><dodf>HTML.Tbg.B</dodf>         <td>CibrbdtfrAdtion
     * <tr><td><dodf>HTML.Tbg.BASE</dodf>      <td>BbsfAdtion
     * <tr><td><dodf>HTML.Tbg.BASEFONT</dodf>  <td>CibrbdtfrAdtion
     * <tr><td><dodf>HTML.Tbg.BIG</dodf>       <td>CibrbdtfrAdtion
     * <tr><td><dodf>HTML.Tbg.BLOCKQUOTE</dodf><td>BlodkAdtion
     * <tr><td><dodf>HTML.Tbg.BODY</dodf>      <td>BlodkAdtion
     * <tr><td><dodf>HTML.Tbg.BR</dodf>        <td>SpfdiblAdtion
     * <tr><td><dodf>HTML.Tbg.CAPTION</dodf>   <td>BlodkAdtion
     * <tr><td><dodf>HTML.Tbg.CENTER</dodf>    <td>BlodkAdtion
     * <tr><td><dodf>HTML.Tbg.CITE</dodf>      <td>CibrbdtfrAdtion
     * <tr><td><dodf>HTML.Tbg.CODE</dodf>      <td>CibrbdtfrAdtion
     * <tr><td><dodf>HTML.Tbg.DD</dodf>        <td>BlodkAdtion
     * <tr><td><dodf>HTML.Tbg.DFN</dodf>       <td>CibrbdtfrAdtion
     * <tr><td><dodf>HTML.Tbg.DIR</dodf>       <td>BlodkAdtion
     * <tr><td><dodf>HTML.Tbg.DIV</dodf>       <td>BlodkAdtion
     * <tr><td><dodf>HTML.Tbg.DL</dodf>        <td>BlodkAdtion
     * <tr><td><dodf>HTML.Tbg.DT</dodf>        <td>PbrbgrbpiAdtion
     * <tr><td><dodf>HTML.Tbg.EM</dodf>        <td>CibrbdtfrAdtion
     * <tr><td><dodf>HTML.Tbg.FONT</dodf>      <td>CibrbdtfrAdtion
     * <tr><td><dodf>HTML.Tbg.FORM</dodf>      <td>As of 1.4 b BlodkAdtion
     * <tr><td><dodf>HTML.Tbg.FRAME</dodf>     <td>SpfdiblAdtion
     * <tr><td><dodf>HTML.Tbg.FRAMESET</dodf>  <td>BlodkAdtion
     * <tr><td><dodf>HTML.Tbg.H1</dodf>        <td>PbrbgrbpiAdtion
     * <tr><td><dodf>HTML.Tbg.H2</dodf>        <td>PbrbgrbpiAdtion
     * <tr><td><dodf>HTML.Tbg.H3</dodf>        <td>PbrbgrbpiAdtion
     * <tr><td><dodf>HTML.Tbg.H4</dodf>        <td>PbrbgrbpiAdtion
     * <tr><td><dodf>HTML.Tbg.H5</dodf>        <td>PbrbgrbpiAdtion
     * <tr><td><dodf>HTML.Tbg.H6</dodf>        <td>PbrbgrbpiAdtion
     * <tr><td><dodf>HTML.Tbg.HEAD</dodf>      <td>HfbdAdtion
     * <tr><td><dodf>HTML.Tbg.HR</dodf>        <td>SpfdiblAdtion
     * <tr><td><dodf>HTML.Tbg.HTML</dodf>      <td>BlodkAdtion
     * <tr><td><dodf>HTML.Tbg.I</dodf>         <td>CibrbdtfrAdtion
     * <tr><td><dodf>HTML.Tbg.IMG</dodf>       <td>SpfdiblAdtion
     * <tr><td><dodf>HTML.Tbg.INPUT</dodf>     <td>FormAdtion
     * <tr><td><dodf>HTML.Tbg.ISINDEX</dodf>   <td>IsndfxAdtion
     * <tr><td><dodf>HTML.Tbg.KBD</dodf>       <td>CibrbdtfrAdtion
     * <tr><td><dodf>HTML.Tbg.LI</dodf>        <td>BlodkAdtion
     * <tr><td><dodf>HTML.Tbg.LINK</dodf>      <td>LinkAdtion
     * <tr><td><dodf>HTML.Tbg.MAP</dodf>       <td>MbpAdtion
     * <tr><td><dodf>HTML.Tbg.MENU</dodf>      <td>BlodkAdtion
     * <tr><td><dodf>HTML.Tbg.META</dodf>      <td>MftbAdtion
     * <tr><td><dodf>HTML.Tbg.NOFRAMES</dodf>  <td>BlodkAdtion
     * <tr><td><dodf>HTML.Tbg.OBJECT</dodf>    <td>SpfdiblAdtion
     * <tr><td><dodf>HTML.Tbg.OL</dodf>        <td>BlodkAdtion
     * <tr><td><dodf>HTML.Tbg.OPTION</dodf>    <td>FormAdtion
     * <tr><td><dodf>HTML.Tbg.P</dodf>         <td>PbrbgrbpiAdtion
     * <tr><td><dodf>HTML.Tbg.PARAM</dodf>     <td>HiddfnAdtion
     * <tr><td><dodf>HTML.Tbg.PRE</dodf>       <td>PrfAdtion
     * <tr><td><dodf>HTML.Tbg.SAMP</dodf>      <td>CibrbdtfrAdtion
     * <tr><td><dodf>HTML.Tbg.SCRIPT</dodf>    <td>HiddfnAdtion
     * <tr><td><dodf>HTML.Tbg.SELECT</dodf>    <td>FormAdtion
     * <tr><td><dodf>HTML.Tbg.SMALL</dodf>     <td>CibrbdtfrAdtion
     * <tr><td><dodf>HTML.Tbg.STRIKE</dodf>    <td>CibrbdtfrAdtion
     * <tr><td><dodf>HTML.Tbg.S</dodf>         <td>CibrbdtfrAdtion
     * <tr><td><dodf>HTML.Tbg.STRONG</dodf>    <td>CibrbdtfrAdtion
     * <tr><td><dodf>HTML.Tbg.STYLE</dodf>     <td>StylfAdtion
     * <tr><td><dodf>HTML.Tbg.SUB</dodf>       <td>CibrbdtfrAdtion
     * <tr><td><dodf>HTML.Tbg.SUP</dodf>       <td>CibrbdtfrAdtion
     * <tr><td><dodf>HTML.Tbg.TABLE</dodf>     <td>BlodkAdtion
     * <tr><td><dodf>HTML.Tbg.TD</dodf>        <td>BlodkAdtion
     * <tr><td><dodf>HTML.Tbg.TEXTAREA</dodf>  <td>FormAdtion
     * <tr><td><dodf>HTML.Tbg.TH</dodf>        <td>BlodkAdtion
     * <tr><td><dodf>HTML.Tbg.TITLE</dodf>     <td>TitlfAdtion
     * <tr><td><dodf>HTML.Tbg.TR</dodf>        <td>BlodkAdtion
     * <tr><td><dodf>HTML.Tbg.TT</dodf>        <td>CibrbdtfrAdtion
     * <tr><td><dodf>HTML.Tbg.U</dodf>         <td>CibrbdtfrAdtion
     * <tr><td><dodf>HTML.Tbg.UL</dodf>        <td>BlodkAdtion
     * <tr><td><dodf>HTML.Tbg.VAR</dodf>       <td>CibrbdtfrAdtion
     * </tbblf>
     * <p>
     * Ondf &lt;/itml&gt; is fndountfrfd, tif Adtions brf no longfr notififd.
     */
    publid dlbss HTMLRfbdfr fxtfnds HTMLEditorKit.PbrsfrCbllbbdk {

        /**
         * Construdts bn HTMLRfbdfr using dffbult pop bnd pusi dfpti bnd no tbg to insfrt.
         *
         * @pbrbm offsft tif stbrting offsft
         */
        publid HTMLRfbdfr(int offsft) {
            tiis(offsft, 0, 0, null);
        }

        /**
         * Construdts bn HTMLRfbdfr.
         *
         * @pbrbm offsft tif stbrting offsft
         * @pbrbm popDfpti iow mbny pbrfnts to bsdfnd bfforf insfrt nfw flfmfnt
         * @pbrbm pusiDfpti iow mbny pbrfnts to dfsdfnd (rflbtivf to popDfpti) bfforf
         *                  insfrting
         * @pbrbm insfrtTbg b tbg to insfrt (mby bf null)
         */
        publid HTMLRfbdfr(int offsft, int popDfpti, int pusiDfpti,
                          HTML.Tbg insfrtTbg) {
            tiis(offsft, popDfpti, pusiDfpti, insfrtTbg, truf, fblsf, truf);
        }

        /**
         * Gfnfrbtfs b RuntimfExdfption (will fvfntublly gfnfrbtf
         * b BbdLodbtionExdfption wifn API dibngfs brf bllodfd) if insfrting
         * into non fmpty dodumfnt, <dodf>insfrtTbg</dodf> is
         * non-<dodf>null</dodf>, bnd <dodf>offsft</dodf> is not in tif body.
         */
        // PENDING(sky): Add tirows BbdLodbtionExdfption bnd rfmovf
        // RuntimfExdfption
        HTMLRfbdfr(int offsft, int popDfpti, int pusiDfpti,
                   HTML.Tbg insfrtTbg, boolfbn insfrtInsfrtTbg,
                   boolfbn insfrtAftfrImplifd, boolfbn wbntsTrbilingNfwlinf) {
            fmptyDodumfnt = (gftLfngti() == 0);
            isStylfCSS = "tfxt/dss".fqubls(gftDffbultStylfSifftTypf());
            tiis.offsft = offsft;
            tirfsiold = HTMLDodumfnt.tiis.gftTokfnTirfsiold();
            tbgMbp = nfw Hbsitbblf<HTML.Tbg, TbgAdtion>(57);
            TbgAdtion nb = nfw TbgAdtion();
            TbgAdtion bb = nfw BlodkAdtion();
            TbgAdtion pb = nfw PbrbgrbpiAdtion();
            TbgAdtion db = nfw CibrbdtfrAdtion();
            TbgAdtion sb = nfw SpfdiblAdtion();
            TbgAdtion fb = nfw FormAdtion();
            TbgAdtion ib = nfw HiddfnAdtion();
            TbgAdtion donv = nfw ConvfrtAdtion();

            // rfgistfr ibndlfrs for tif wfll known tbgs
            tbgMbp.put(HTML.Tbg.A, nfw AndiorAdtion());
            tbgMbp.put(HTML.Tbg.ADDRESS, db);
            tbgMbp.put(HTML.Tbg.APPLET, ib);
            tbgMbp.put(HTML.Tbg.AREA, nfw ArfbAdtion());
            tbgMbp.put(HTML.Tbg.B, donv);
            tbgMbp.put(HTML.Tbg.BASE, nfw BbsfAdtion());
            tbgMbp.put(HTML.Tbg.BASEFONT, db);
            tbgMbp.put(HTML.Tbg.BIG, db);
            tbgMbp.put(HTML.Tbg.BLOCKQUOTE, bb);
            tbgMbp.put(HTML.Tbg.BODY, bb);
            tbgMbp.put(HTML.Tbg.BR, sb);
            tbgMbp.put(HTML.Tbg.CAPTION, bb);
            tbgMbp.put(HTML.Tbg.CENTER, bb);
            tbgMbp.put(HTML.Tbg.CITE, db);
            tbgMbp.put(HTML.Tbg.CODE, db);
            tbgMbp.put(HTML.Tbg.DD, bb);
            tbgMbp.put(HTML.Tbg.DFN, db);
            tbgMbp.put(HTML.Tbg.DIR, bb);
            tbgMbp.put(HTML.Tbg.DIV, bb);
            tbgMbp.put(HTML.Tbg.DL, bb);
            tbgMbp.put(HTML.Tbg.DT, pb);
            tbgMbp.put(HTML.Tbg.EM, db);
            tbgMbp.put(HTML.Tbg.FONT, donv);
            tbgMbp.put(HTML.Tbg.FORM, nfw FormTbgAdtion());
            tbgMbp.put(HTML.Tbg.FRAME, sb);
            tbgMbp.put(HTML.Tbg.FRAMESET, bb);
            tbgMbp.put(HTML.Tbg.H1, pb);
            tbgMbp.put(HTML.Tbg.H2, pb);
            tbgMbp.put(HTML.Tbg.H3, pb);
            tbgMbp.put(HTML.Tbg.H4, pb);
            tbgMbp.put(HTML.Tbg.H5, pb);
            tbgMbp.put(HTML.Tbg.H6, pb);
            tbgMbp.put(HTML.Tbg.HEAD, nfw HfbdAdtion());
            tbgMbp.put(HTML.Tbg.HR, sb);
            tbgMbp.put(HTML.Tbg.HTML, bb);
            tbgMbp.put(HTML.Tbg.I, donv);
            tbgMbp.put(HTML.Tbg.IMG, sb);
            tbgMbp.put(HTML.Tbg.INPUT, fb);
            tbgMbp.put(HTML.Tbg.ISINDEX, nfw IsindfxAdtion());
            tbgMbp.put(HTML.Tbg.KBD, db);
            tbgMbp.put(HTML.Tbg.LI, bb);
            tbgMbp.put(HTML.Tbg.LINK, nfw LinkAdtion());
            tbgMbp.put(HTML.Tbg.MAP, nfw MbpAdtion());
            tbgMbp.put(HTML.Tbg.MENU, bb);
            tbgMbp.put(HTML.Tbg.META, nfw MftbAdtion());
            tbgMbp.put(HTML.Tbg.NOBR, db);
            tbgMbp.put(HTML.Tbg.NOFRAMES, bb);
            tbgMbp.put(HTML.Tbg.OBJECT, sb);
            tbgMbp.put(HTML.Tbg.OL, bb);
            tbgMbp.put(HTML.Tbg.OPTION, fb);
            tbgMbp.put(HTML.Tbg.P, pb);
            tbgMbp.put(HTML.Tbg.PARAM, nfw ObjfdtAdtion());
            tbgMbp.put(HTML.Tbg.PRE, nfw PrfAdtion());
            tbgMbp.put(HTML.Tbg.SAMP, db);
            tbgMbp.put(HTML.Tbg.SCRIPT, ib);
            tbgMbp.put(HTML.Tbg.SELECT, fb);
            tbgMbp.put(HTML.Tbg.SMALL, db);
            tbgMbp.put(HTML.Tbg.SPAN, db);
            tbgMbp.put(HTML.Tbg.STRIKE, donv);
            tbgMbp.put(HTML.Tbg.S, db);
            tbgMbp.put(HTML.Tbg.STRONG, db);
            tbgMbp.put(HTML.Tbg.STYLE, nfw StylfAdtion());
            tbgMbp.put(HTML.Tbg.SUB, donv);
            tbgMbp.put(HTML.Tbg.SUP, donv);
            tbgMbp.put(HTML.Tbg.TABLE, bb);
            tbgMbp.put(HTML.Tbg.TD, bb);
            tbgMbp.put(HTML.Tbg.TEXTAREA, fb);
            tbgMbp.put(HTML.Tbg.TH, bb);
            tbgMbp.put(HTML.Tbg.TITLE, nfw TitlfAdtion());
            tbgMbp.put(HTML.Tbg.TR, bb);
            tbgMbp.put(HTML.Tbg.TT, db);
            tbgMbp.put(HTML.Tbg.U, donv);
            tbgMbp.put(HTML.Tbg.UL, bb);
            tbgMbp.put(HTML.Tbg.VAR, db);

            if (insfrtTbg != null) {
                tiis.insfrtTbg = insfrtTbg;
                tiis.popDfpti = popDfpti;
                tiis.pusiDfpti = pusiDfpti;
                tiis.insfrtInsfrtTbg = insfrtInsfrtTbg;
                foundInsfrtTbg = fblsf;
            }
            flsf {
                foundInsfrtTbg = truf;
            }
            if (insfrtAftfrImplifd) {
                tiis.popDfpti = popDfpti;
                tiis.pusiDfpti = pusiDfpti;
                tiis.insfrtAftfrImplifd = truf;
                foundInsfrtTbg = fblsf;
                midInsfrt = fblsf;
                tiis.insfrtInsfrtTbg = truf;
                tiis.wbntsTrbilingNfwlinf = wbntsTrbilingNfwlinf;
            }
            flsf {
                midInsfrt = (!fmptyDodumfnt && insfrtTbg == null);
                if (midInsfrt) {
                    gfnfrbtfEndsSpfdsForMidInsfrt();
                }
            }

            /**
             * Tiis blodk initiblizfs tif <dodf>inPbrbgrbpi</dodf> flbg.
             * It is lfft in <dodf>fblsf</dodf> vbluf butombtidblly
             * if tif tbrgft dodumfnt is fmpty or futurf insfrts
             * wfrf positionfd into tif 'body' tbg.
             */
            if (!fmptyDodumfnt && !midInsfrt) {
                int tbrgftOffsft = Mbti.mbx(tiis.offsft - 1, 0);
                Elfmfnt flfm =
                        HTMLDodumfnt.tiis.gftCibrbdtfrElfmfnt(tbrgftOffsft);
                /* Going up by tif lfft dodumfnt strudturf pbti */
                for (int i = 0; i <= tiis.popDfpti; i++) {
                    flfm = flfm.gftPbrfntElfmfnt();
                }
                /* Going down by tif rigit dodumfnt strudturf pbti */
                for (int i = 0; i < tiis.pusiDfpti; i++) {
                    int indfx = flfm.gftElfmfntIndfx(tiis.offsft);
                    flfm = flfm.gftElfmfnt(indfx);
                }
                AttributfSft bttrs = flfm.gftAttributfs();
                if (bttrs != null) {
                    HTML.Tbg tbgToInsfrtInto =
                            (HTML.Tbg) bttrs.gftAttributf(StylfConstbnts.NbmfAttributf);
                    if (tbgToInsfrtInto != null) {
                        tiis.inPbrbgrbpi = tbgToInsfrtInto.isPbrbgrbpi();
                    }
                }
            }
        }

        /**
         * Gfnfrbtfs bn initibl bbtdi of fnd <dodf>ElfmfntSpfds</dodf>
         * in pbrsfBufffr to position futurf insfrts into tif body.
         */
        privbtf void gfnfrbtfEndsSpfdsForMidInsfrt() {
            int           dount = ifigitToElfmfntWitiNbmf(HTML.Tbg.BODY,
                                                   Mbti.mbx(0, offsft - 1));
            boolfbn       joinNfxt = fblsf;

            if (dount == -1 && offsft > 0) {
                dount = ifigitToElfmfntWitiNbmf(HTML.Tbg.BODY, offsft);
                if (dount != -1) {
                    // Prfvious isn't in body, but durrfnt is. Hbvf to
                    // do somf fnd spfds, followfd by join nfxt.
                    dount = dfptiTo(offsft - 1) - 1;
                    joinNfxt = truf;
                }
            }
            if (dount == -1) {
                tirow nfw RuntimfExdfption("Must insfrt nfw dontfnt into body flfmfnt-");
            }
            if (dount != -1) {
                // Insfrt b nfwlinf, if nfdfssbry.
                try {
                    if (!joinNfxt && offsft > 0 &&
                        !gftTfxt(offsft - 1, 1).fqubls("\n")) {
                        SimplfAttributfSft nfwAttrs = nfw SimplfAttributfSft();
                        nfwAttrs.bddAttributf(StylfConstbnts.NbmfAttributf,
                                              HTML.Tbg.CONTENT);
                        ElfmfntSpfd spfd = nfw ElfmfntSpfd(nfwAttrs,
                                    ElfmfntSpfd.ContfntTypf, NEWLINE, 0, 1);
                        pbrsfBufffr.bddElfmfnt(spfd);
                    }
                    // Siould nfvfr tirow, but will dbtdi bnywby.
                } dbtdi (BbdLodbtionExdfption blf) {}
                wiilf (dount-- > 0) {
                    pbrsfBufffr.bddElfmfnt(nfw ElfmfntSpfd
                                           (null, ElfmfntSpfd.EndTbgTypf));
                }
                if (joinNfxt) {
                    ElfmfntSpfd spfd = nfw ElfmfntSpfd(null, ElfmfntSpfd.
                                                       StbrtTbgTypf);

                    spfd.sftDirfdtion(ElfmfntSpfd.JoinNfxtDirfdtion);
                    pbrsfBufffr.bddElfmfnt(spfd);
                }
            }
            // Wf siould probbbly tirow bn fxdfption if (dount == -1)
            // Or look for tif body bnd rfsft tif offsft.
        }

        /**
         * @rfturn numbfr of pbrfnts to rfbdi tif diild bt offsft.
         */
        privbtf int dfptiTo(int offsft) {
            Elfmfnt       f = gftDffbultRootElfmfnt();
            int           dount = 0;

            wiilf (!f.isLfbf()) {
                dount++;
                f = f.gftElfmfnt(f.gftElfmfntIndfx(offsft));
            }
            rfturn dount;
        }

        /**
         * @rfturn numbfr of pbrfnts of tif lfbf bt <dodf>offsft</dodf>
         *         until b pbrfnt witi nbmf, <dodf>nbmf</dodf> ibs bffn
         *         found. -1 indidbtfs no mbtdiing pbrfnt witi
         *         <dodf>nbmf</dodf>.
         */
        privbtf int ifigitToElfmfntWitiNbmf(Objfdt nbmf, int offsft) {
            Elfmfnt       f = gftCibrbdtfrElfmfnt(offsft).gftPbrfntElfmfnt();
            int           dount = 0;

            wiilf (f != null && f.gftAttributfs().gftAttributf
                   (StylfConstbnts.NbmfAttributf) != nbmf) {
                dount++;
                f = f.gftPbrfntElfmfnt();
            }
            rfturn (f == null) ? -1 : dount;
        }

        /**
         * Tiis will mbkf surf tifrf brfn't two BODYs (tif sfdond is
         * typidblly drfbtfd wifn you do b rfmovf bll, bnd tifn bn insfrt).
         */
        privbtf void bdjustEndElfmfnt() {
            int lfngti = gftLfngti();
            if (lfngti == 0) {
                rfturn;
            }
            obtbinLodk();
            try {
                Elfmfnt[] pPbti = gftPbtiTo(lfngti - 1);
                int pLfngti = pPbti.lfngti;
                if (pLfngti > 1 && pPbti[1].gftAttributfs().gftAttributf
                         (StylfConstbnts.NbmfAttributf) == HTML.Tbg.BODY &&
                         pPbti[1].gftEndOffsft() == lfngti) {
                    String lbstTfxt = gftTfxt(lfngti - 1, 1);
                    DffbultDodumfntEvfnt fvfnt;
                    Elfmfnt[] bddfd;
                    Elfmfnt[] rfmovfd;
                    int indfx;
                    // Rfmovf tif fbkf sfdond body.
                    bddfd = nfw Elfmfnt[0];
                    rfmovfd = nfw Elfmfnt[1];
                    indfx = pPbti[0].gftElfmfntIndfx(lfngti);
                    rfmovfd[0] = pPbti[0].gftElfmfnt(indfx);
                    ((BrbndiElfmfnt)pPbti[0]).rfplbdf(indfx, 1, bddfd);
                    ElfmfntEdit firstEdit = nfw ElfmfntEdit(pPbti[0], indfx,
                                                            rfmovfd, bddfd);

                    // Insfrt b nfw flfmfnt to rfprfsfnt tif fnd tibt tif
                    // sfdond body wbs rfprfsfnting.
                    SimplfAttributfSft sbs = nfw SimplfAttributfSft();
                    sbs.bddAttributf(StylfConstbnts.NbmfAttributf,
                                         HTML.Tbg.CONTENT);
                    sbs.bddAttributf(IMPLIED_CR, Boolfbn.TRUE);
                    bddfd = nfw Elfmfnt[1];
                    bddfd[0] = drfbtfLfbfElfmfnt(pPbti[pLfngti - 1],
                                                     sbs, lfngti, lfngti + 1);
                    indfx = pPbti[pLfngti - 1].gftElfmfntCount();
                    ((BrbndiElfmfnt)pPbti[pLfngti - 1]).rfplbdf(indfx, 0,
                                                                bddfd);
                    fvfnt = nfw DffbultDodumfntEvfnt(lfngti, 1,
                                            DodumfntEvfnt.EvfntTypf.CHANGE);
                    fvfnt.bddEdit(nfw ElfmfntEdit(pPbti[pLfngti - 1],
                                         indfx, nfw Elfmfnt[0], bddfd));
                    fvfnt.bddEdit(firstEdit);
                    fvfnt.fnd();
                    firfCibngfdUpdbtf(fvfnt);
                    firfUndobblfEditUpdbtf(nfw UndobblfEditEvfnt(tiis, fvfnt));

                    if (lbstTfxt.fqubls("\n")) {
                        // Wf now ibvf two \n's, onf pbrt of tif Dodumfnt.
                        // Wf nffd to rfmovf onf
                        fvfnt = nfw DffbultDodumfntEvfnt(lfngti - 1, 1,
                                           DodumfntEvfnt.EvfntTypf.REMOVE);
                        rfmovfUpdbtf(fvfnt);
                        UndobblfEdit u = gftContfnt().rfmovf(lfngti - 1, 1);
                        if (u != null) {
                            fvfnt.bddEdit(u);
                        }
                        postRfmovfUpdbtf(fvfnt);
                        // Mbrk tif fdit bs donf.
                        fvfnt.fnd();
                        firfRfmovfUpdbtf(fvfnt);
                        firfUndobblfEditUpdbtf(nfw UndobblfEditEvfnt(
                                               tiis, fvfnt));
                    }
                }
            }
            dbtdi (BbdLodbtionExdfption blf) {
            }
            finblly {
                rflfbsfLodk();
            }
        }

        privbtf Elfmfnt[] gftPbtiTo(int offsft) {
            Stbdk<Elfmfnt> flfmfnts = nfw Stbdk<Elfmfnt>();
            Elfmfnt f = gftDffbultRootElfmfnt();
            int indfx;
            wiilf (!f.isLfbf()) {
                flfmfnts.pusi(f);
                f = f.gftElfmfnt(f.gftElfmfntIndfx(offsft));
            }
            Elfmfnt[] rftVbluf = nfw Elfmfnt[flfmfnts.sizf()];
            flfmfnts.dopyInto(rftVbluf);
            rfturn rftVbluf;
        }

        // -- HTMLEditorKit.PbrsfrCbllbbdk mftiods --------------------

        /**
         * Tif lbst mftiod dbllfd on tif rfbdfr.  It bllows
         * bny pfnding dibngfs to bf flusifd into tif dodumfnt.
         * Sindf tiis is durrfntly lobding syndironously, tif fntirf
         * sft of dibngfs brf pusifd in bt tiis point.
         */
        publid void flusi() tirows BbdLodbtionExdfption {
            if (fmptyDodumfnt && !insfrtAftfrImplifd) {
                if (HTMLDodumfnt.tiis.gftLfngti() > 0 ||
                                      pbrsfBufffr.sizf() > 0) {
                    flusiBufffr(truf);
                    bdjustEndElfmfnt();
                }
                // Wf won't insfrt wifn
            }
            flsf {
                flusiBufffr(truf);
            }
        }

        /**
         * Cbllfd by tif pbrsfr to indidbtf b blodk of tfxt wbs
         * fndountfrfd.
         */
        publid void ibndlfTfxt(dibr[] dbtb, int pos) {
            if (rfdfivfdEndHTML || (midInsfrt && !inBody)) {
                rfturn;
            }

            // sff if domplfx glypi lbyout support is nffdfd
            if(HTMLDodumfnt.tiis.gftPropfrty(I18NPropfrty).fqubls( Boolfbn.FALSE ) ) {
                // if b dffbult dirfdtion of rigit-to-lfft ibs bffn spfdififd,
                // wf wbnt domplfx lbyout fvfn if tif tfxt is bll lfft to rigit.
                Objfdt d = gftPropfrty(TfxtAttributf.RUN_DIRECTION);
                if ((d != null) && (d.fqubls(TfxtAttributf.RUN_DIRECTION_RTL))) {
                    HTMLDodumfnt.tiis.putPropfrty( I18NPropfrty, Boolfbn.TRUE);
                } flsf {
                    if (SwingUtilitifs2.isComplfxLbyout(dbtb, 0, dbtb.lfngti)) {
                        HTMLDodumfnt.tiis.putPropfrty( I18NPropfrty, Boolfbn.TRUE);
                    }
                }
            }

            if (inTfxtArfb) {
                tfxtArfbContfnt(dbtb);
            } flsf if (inPrf) {
                prfContfnt(dbtb);
            } flsf if (inTitlf) {
                putPropfrty(Dodumfnt.TitlfPropfrty, nfw String(dbtb));
            } flsf if (option != null) {
                option.sftLbbfl(nfw String(dbtb));
            } flsf if (inStylf) {
                if (stylfs != null) {
                    stylfs.bddElfmfnt(nfw String(dbtb));
                }
            } flsf if (inBlodk > 0) {
                if (!foundInsfrtTbg && insfrtAftfrImplifd) {
                    // Assumf dontfnt siould bf bddfd.
                    foundInsfrtTbg(fblsf);
                    foundInsfrtTbg = truf;
                    inPbrbgrbpi = implifdP = truf;
                }
                if (dbtb.lfngti >= 1) {
                    bddContfnt(dbtb, 0, dbtb.lfngti);
                }
            }
        }

        /**
         * Cbllbbdk from tif pbrsfr.  Routf to tif bppropribtf
         * ibndlfr for tif tbg.
         */
        publid void ibndlfStbrtTbg(HTML.Tbg t, MutbblfAttributfSft b, int pos) {
            if (rfdfivfdEndHTML) {
                rfturn;
            }
            if (midInsfrt && !inBody) {
                if (t == HTML.Tbg.BODY) {
                    inBody = truf;
                    // Indrfmfnt inBlodk sindf wf know wf brf in tif body,
                    // tiis is nffdfd indbsf bn implifd-p is nffdfd. If
                    // inBlodk isn't indrfmfntfd, bnd bn implifd-p is
                    // fndountfrfd, bddContfnt won't bf dbllfd!
                    inBlodk++;
                }
                rfturn;
            }
            if (!inBody && t == HTML.Tbg.BODY) {
                inBody = truf;
            }
            if (isStylfCSS && b.isDffinfd(HTML.Attributf.STYLE)) {
                // Mbp tif stylf bttributfs.
                String dfdl = (String)b.gftAttributf(HTML.Attributf.STYLE);
                b.rfmovfAttributf(HTML.Attributf.STYLE);
                stylfAttributfs = gftStylfSifft().gftDfdlbrbtion(dfdl);
                b.bddAttributfs(stylfAttributfs);
            }
            flsf {
                stylfAttributfs = null;
            }
            TbgAdtion bdtion = tbgMbp.gft(t);

            if (bdtion != null) {
                bdtion.stbrt(t, b);
            }
        }

        publid void ibndlfCommfnt(dibr[] dbtb, int pos) {
            if (rfdfivfdEndHTML) {
                bddExtfrnblCommfnt(nfw String(dbtb));
                rfturn;
            }
            if (inStylf) {
                if (stylfs != null) {
                    stylfs.bddElfmfnt(nfw String(dbtb));
                }
            }
            flsf if (gftPrfsfrvfsUnknownTbgs()) {
                if (inBlodk == 0 && (foundInsfrtTbg ||
                                     insfrtTbg != HTML.Tbg.COMMENT)) {
                    // Commfnt outsidf of body, will not bf bblf to siow it,
                    // but dbn bdd it bs b propfrty on tif Dodumfnt.
                    bddExtfrnblCommfnt(nfw String(dbtb));
                    rfturn;
                }
                SimplfAttributfSft sbs = nfw SimplfAttributfSft();
                sbs.bddAttributf(HTML.Attributf.COMMENT, nfw String(dbtb));
                bddSpfdiblElfmfnt(HTML.Tbg.COMMENT, sbs);
            }

            TbgAdtion bdtion = tbgMbp.gft(HTML.Tbg.COMMENT);
            if (bdtion != null) {
                bdtion.stbrt(HTML.Tbg.COMMENT, nfw SimplfAttributfSft());
                bdtion.fnd(HTML.Tbg.COMMENT);
            }
        }

        /**
         * Adds tif dommfnt <dodf>dommfnt</dodf> to tif sft of dommfnts
         * mbintbinfd outsidf of tif sdopf of flfmfnts.
         */
        privbtf void bddExtfrnblCommfnt(String dommfnt) {
            Objfdt dommfnts = gftPropfrty(AdditionblCommfnts);
            if (dommfnts != null && !(dommfnts instbndfof Vfdtor)) {
                // No plbdf to put dommfnt.
                rfturn;
            }
            if (dommfnts == null) {
                dommfnts = nfw Vfdtor<>();
                putPropfrty(AdditionblCommfnts, dommfnts);
            }
            @SupprfssWbrnings("undifdkfd")
            Vfdtor<Objfdt> v = (Vfdtor<Objfdt>)dommfnts;
            v.bddElfmfnt(dommfnt);
        }

        /**
         * Cbllbbdk from tif pbrsfr.  Routf to tif bppropribtf
         * ibndlfr for tif tbg.
         */
        publid void ibndlfEndTbg(HTML.Tbg t, int pos) {
            if (rfdfivfdEndHTML || (midInsfrt && !inBody)) {
                rfturn;
            }
            if (t == HTML.Tbg.HTML) {
                rfdfivfdEndHTML = truf;
            }
            if (t == HTML.Tbg.BODY) {
                inBody = fblsf;
                if (midInsfrt) {
                    inBlodk--;
                }
            }
            TbgAdtion bdtion = tbgMbp.gft(t);
            if (bdtion != null) {
                bdtion.fnd(t);
            }
        }

        /**
         * Cbllbbdk from tif pbrsfr.  Routf to tif bppropribtf
         * ibndlfr for tif tbg.
         */
        publid void ibndlfSimplfTbg(HTML.Tbg t, MutbblfAttributfSft b, int pos) {
            if (rfdfivfdEndHTML || (midInsfrt && !inBody)) {
                rfturn;
            }

            if (isStylfCSS && b.isDffinfd(HTML.Attributf.STYLE)) {
                // Mbp tif stylf bttributfs.
                String dfdl = (String)b.gftAttributf(HTML.Attributf.STYLE);
                b.rfmovfAttributf(HTML.Attributf.STYLE);
                stylfAttributfs = gftStylfSifft().gftDfdlbrbtion(dfdl);
                b.bddAttributfs(stylfAttributfs);
            }
            flsf {
                stylfAttributfs = null;
            }

            TbgAdtion bdtion = tbgMbp.gft(t);
            if (bdtion != null) {
                bdtion.stbrt(t, b);
                bdtion.fnd(t);
            }
            flsf if (gftPrfsfrvfsUnknownTbgs()) {
                // unknown tbg, only bdd if siould prfsfrvf it.
                bddSpfdiblElfmfnt(t, b);
            }
        }

        /**
         * Tiis is invokfd bftfr tif strfbm ibs bffn pbrsfd, but bfforf
         * <dodf>flusi</dodf>. <dodf>fol</dodf> will bf onf of \n, \r
         * or \r\n, wiidi fvfr is fndountfrfd tif most in pbrsing tif
         * strfbm.
         *
         * @sindf 1.3
         */
        publid void ibndlfEndOfLinfString(String fol) {
            if (fmptyDodumfnt && fol != null) {
                putPropfrty(DffbultEditorKit.EndOfLinfStringPropfrty,
                            fol);
            }
        }

        // ---- tbg ibndling support ------------------------------

        /**
         * Rfgistfrs b ibndlfr for tif givfn tbg.  By dffbult
         * bll of tif wfll-known tbgs will ibvf bffn rfgistfrfd.
         * Tiis dbn bf usfd to dibngf tif ibndling of b pbrtidulbr
         * tbg or to bdd support for dustom tbgs.
         *
         * @pbrbm t bn HTML tbg
         * @pbrbm b tbg bdtion ibndlfr
         */
        protfdtfd void rfgistfrTbg(HTML.Tbg t, TbgAdtion b) {
            tbgMbp.put(t, b);
        }

        /**
         * An bdtion to bf pfrformfd in rfsponsf
         * to pbrsing b tbg.  Tiis bllows dustomizbtion
         * of iow fbdi tbg is ibndlfd bnd bvoids b lbrgf
         * switdi stbtfmfnt.
         */
        publid dlbss TbgAdtion {

            /**
             * Cbllfd wifn b stbrt tbg is sffn for tif
             * typf of tbg tiis bdtion wbs rfgistfrfd
             * to.  Tif tbg brgumfnt indidbtfs tif bdtubl
             * tbg for tiosf bdtions tibt brf sibrfd bdross
             * mbny tbgs.  By dffbult tiis dofs notiing bnd
             * domplftfly ignorfs tif tbg.
             *
             * @pbrbm t tif HTML tbg
             * @pbrbm b tif bttributfs
             */
            publid void stbrt(HTML.Tbg t, MutbblfAttributfSft b) {
            }

            /**
             * Cbllfd wifn bn fnd tbg is sffn for tif
             * typf of tbg tiis bdtion wbs rfgistfrfd
             * to.  Tif tbg brgumfnt indidbtfs tif bdtubl
             * tbg for tiosf bdtions tibt brf sibrfd bdross
             * mbny tbgs.  By dffbult tiis dofs notiing bnd
             * domplftfly ignorfs tif tbg.
             *
             * @pbrbm t tif HTML tbg
             */
            publid void fnd(HTML.Tbg t) {
            }

        }

        /**
         * Adtion bssignfd by dffbult to ibndlf tif Blodk tbsk of tif rfbdfr.
         */
        publid dlbss BlodkAdtion fxtfnds TbgAdtion {

            publid void stbrt(HTML.Tbg t, MutbblfAttributfSft bttr) {
                blodkOpfn(t, bttr);
            }

            publid void fnd(HTML.Tbg t) {
                blodkClosf(t);
            }
        }


        /**
         * Adtion usfd for tif bdtubl flfmfnt form tbg. Tiis is nbmfd sudi
         * bs tifrf wbs blrfbdy b publid dlbss nbmfd FormAdtion.
         */
        privbtf dlbss FormTbgAdtion fxtfnds BlodkAdtion {
            publid void stbrt(HTML.Tbg t, MutbblfAttributfSft bttr) {
                supfr.stbrt(t, bttr);
                // initiblizf b ButtonGroupsMbp wifn
                // FORM tbg is fndountfrfd.  Tiis will
                // bf usfd for bny rbdio buttons tibt
                // migit bf dffinfd in tif FORM.
                // for nfw group nfw ButtonGroup will bf drfbtfd (fix for 4529702)
                // group nbmf is b kfy in rbdioButtonGroupsMbp
                rbdioButtonGroupsMbp = nfw HbsiMbp<String, ButtonGroup>();
            }

            publid void fnd(HTML.Tbg t) {
                supfr.fnd(t);
                // rfsft tif button group to null sindf
                // tif form ibs fndfd.
                rbdioButtonGroupsMbp = null;
            }
        }


        /**
         * Adtion bssignfd by dffbult to ibndlf tif Pbrbgrbpi tbsk of tif rfbdfr.
         */
        publid dlbss PbrbgrbpiAdtion fxtfnds BlodkAdtion {

            publid void stbrt(HTML.Tbg t, MutbblfAttributfSft b) {
                supfr.stbrt(t, b);
                inPbrbgrbpi = truf;
            }

            publid void fnd(HTML.Tbg t) {
                supfr.fnd(t);
                inPbrbgrbpi = fblsf;
            }
        }

        /**
         * Adtion bssignfd by dffbult to ibndlf tif Spfdibl tbsk of tif rfbdfr.
         */
        publid dlbss SpfdiblAdtion fxtfnds TbgAdtion {

            publid void stbrt(HTML.Tbg t, MutbblfAttributfSft b) {
                bddSpfdiblElfmfnt(t, b);
            }

        }

        /**
         * Adtion bssignfd by dffbult to ibndlf tif Isindfx tbsk of tif rfbdfr.
         */
        publid dlbss IsindfxAdtion fxtfnds TbgAdtion {

            publid void stbrt(HTML.Tbg t, MutbblfAttributfSft b) {
                blodkOpfn(HTML.Tbg.IMPLIED, nfw SimplfAttributfSft());
                bddSpfdiblElfmfnt(t, b);
                blodkClosf(HTML.Tbg.IMPLIED);
            }

        }


        /**
         * Adtion bssignfd by dffbult to ibndlf tif Hiddfn tbsk of tif rfbdfr.
         */
        publid dlbss HiddfnAdtion fxtfnds TbgAdtion {

            publid void stbrt(HTML.Tbg t, MutbblfAttributfSft b) {
                bddSpfdiblElfmfnt(t, b);
            }

            publid void fnd(HTML.Tbg t) {
                if (!isEmpty(t)) {
                    MutbblfAttributfSft b = nfw SimplfAttributfSft();
                    b.bddAttributf(HTML.Attributf.ENDTAG, "truf");
                    bddSpfdiblElfmfnt(t, b);
                }
            }

            boolfbn isEmpty(HTML.Tbg t) {
                if (t == HTML.Tbg.APPLET ||
                    t == HTML.Tbg.SCRIPT) {
                    rfturn fblsf;
                }
                rfturn truf;
            }
        }


        /**
         * Subdlbss of HiddfnAdtion to sft tif dontfnt typf for stylf siffts,
         * bnd to sft tif nbmf of tif dffbult stylf sifft.
         */
        dlbss MftbAdtion fxtfnds HiddfnAdtion {

            publid void stbrt(HTML.Tbg t, MutbblfAttributfSft b) {
                Objfdt fquiv = b.gftAttributf(HTML.Attributf.HTTPEQUIV);
                if (fquiv != null) {
                    fquiv = ((String)fquiv).toLowfrCbsf();
                    if (fquiv.fqubls("dontfnt-stylf-typf")) {
                        String vbluf = (String)b.gftAttributf
                                       (HTML.Attributf.CONTENT);
                        sftDffbultStylfSifftTypf(vbluf);
                        isStylfCSS = "tfxt/dss".fqubls
                                      (gftDffbultStylfSifftTypf());
                    }
                    flsf if (fquiv.fqubls("dffbult-stylf")) {
                        dffbultStylf = (String)b.gftAttributf
                                       (HTML.Attributf.CONTENT);
                    }
                }
                supfr.stbrt(t, b);
            }

            boolfbn isEmpty(HTML.Tbg t) {
                rfturn truf;
            }
        }


        /**
         * End if ovfrriddfn to drfbtf tif nfdfssbry stylfsiffts tibt
         * brf rfffrfndfd vib tif link tbg. It is donf in tiis mbnnfr
         * bs tif mftb tbg dbn bf usfd to spfdify bn bltfrnbtf stylf sifft,
         * bnd is not gubrbntffd to domf bfforf tif link tbgs.
         */
        dlbss HfbdAdtion fxtfnds BlodkAdtion {

            publid void stbrt(HTML.Tbg t, MutbblfAttributfSft b) {
                inHfbd = truf;
                // Tiis difdk of tif insfrtTbg is put in to bvoid donsidfring
                // tif implifd-p tibt is gfnfrbtfd for tif ifbd. Tiis bllows
                // insfrts for HR to work dorrfdtly.
                if ((insfrtTbg == null && !insfrtAftfrImplifd) ||
                    (insfrtTbg == HTML.Tbg.HEAD) ||
                    (insfrtAftfrImplifd &&
                     (foundInsfrtTbg || !b.isDffinfd(IMPLIED)))) {
                    supfr.stbrt(t, b);
                }
            }

            publid void fnd(HTML.Tbg t) {
                inHfbd = inStylf = fblsf;
                // Sff if tifrf is b StylfSifft to link to.
                if (stylfs != null) {
                    boolfbn isDffbultCSS = isStylfCSS;
                    for (int dountfr = 0, mbxCountfr = stylfs.sizf();
                         dountfr < mbxCountfr;) {
                        Objfdt vbluf = stylfs.flfmfntAt(dountfr);
                        if (vbluf == HTML.Tbg.LINK) {
                            ibndlfLink((AttributfSft)stylfs.
                                       flfmfntAt(++dountfr));
                            dountfr++;
                        }
                        flsf {
                            // Rulf.
                            // First flfmfnt givfs typf.
                            String typf = (String)stylfs.flfmfntAt(++dountfr);
                            boolfbn isCSS = (typf == null) ? isDffbultCSS :
                                            typf.fqubls("tfxt/dss");
                            wiilf (++dountfr < mbxCountfr &&
                                   (stylfs.flfmfntAt(dountfr)
                                    instbndfof String)) {
                                if (isCSS) {
                                    bddCSSRulfs((String)stylfs.flfmfntAt
                                                (dountfr));
                                }
                            }
                        }
                    }
                }
                if ((insfrtTbg == null && !insfrtAftfrImplifd) ||
                    insfrtTbg == HTML.Tbg.HEAD ||
                    (insfrtAftfrImplifd && foundInsfrtTbg)) {
                    supfr.fnd(t);
                }
            }

            boolfbn isEmpty(HTML.Tbg t) {
                rfturn fblsf;
            }

            privbtf void ibndlfLink(AttributfSft bttr) {
                // Link.
                String typf = (String)bttr.gftAttributf(HTML.Attributf.TYPE);
                if (typf == null) {
                    typf = gftDffbultStylfSifftTypf();
                }
                // Only dioosf if typf==tfxt/dss
                // Sflfdt link if rfl==stylfsifft.
                // Otifrwisf if rfl==bltfrnbtf stylfsifft bnd
                //   titlf mbtdifs dffbult stylf.
                if (typf.fqubls("tfxt/dss")) {
                    String rfl = (String)bttr.gftAttributf(HTML.Attributf.REL);
                    String titlf = (String)bttr.gftAttributf
                                               (HTML.Attributf.TITLE);
                    String mfdib = (String)bttr.gftAttributf
                                                   (HTML.Attributf.MEDIA);
                    if (mfdib == null) {
                        mfdib = "bll";
                    }
                    flsf {
                        mfdib = mfdib.toLowfrCbsf();
                    }
                    if (rfl != null) {
                        rfl = rfl.toLowfrCbsf();
                        if ((mfdib.indfxOf("bll") != -1 ||
                             mfdib.indfxOf("sdrffn") != -1) &&
                            (rfl.fqubls("stylfsifft") ||
                             (rfl.fqubls("bltfrnbtf stylfsifft") &&
                              titlf.fqubls(dffbultStylf)))) {
                            linkCSSStylfSifft((String)bttr.gftAttributf
                                              (HTML.Attributf.HREF));
                        }
                    }
                }
            }
        }


        /**
         * A subdlbss to bdd tif AttributfSft to stylfs if tif
         * bttributfs dontbins bn bttributf for 'rfl' witi vbluf
         * 'stylfsifft' or 'bltfrnbtf stylfsifft'.
         */
        dlbss LinkAdtion fxtfnds HiddfnAdtion {

            publid void stbrt(HTML.Tbg t, MutbblfAttributfSft b) {
                String rfl = (String)b.gftAttributf(HTML.Attributf.REL);
                if (rfl != null) {
                    rfl = rfl.toLowfrCbsf();
                    if (rfl.fqubls("stylfsifft") ||
                        rfl.fqubls("bltfrnbtf stylfsifft")) {
                        if (stylfs == null) {
                            stylfs = nfw Vfdtor<Objfdt>(3);
                        }
                        stylfs.bddElfmfnt(t);
                        stylfs.bddElfmfnt(b.dopyAttributfs());
                    }
                }
                supfr.stbrt(t, b);
            }
        }

        dlbss MbpAdtion fxtfnds TbgAdtion {

            publid void stbrt(HTML.Tbg t, MutbblfAttributfSft b) {
                lbstMbp = nfw Mbp((String)b.gftAttributf(HTML.Attributf.NAME));
                bddMbp(lbstMbp);
            }

            publid void fnd(HTML.Tbg t) {
            }
        }


        dlbss ArfbAdtion fxtfnds TbgAdtion {

            publid void stbrt(HTML.Tbg t, MutbblfAttributfSft b) {
                if (lbstMbp != null) {
                    lbstMbp.bddArfb(b.dopyAttributfs());
                }
            }

            publid void fnd(HTML.Tbg t) {
            }
        }


        dlbss StylfAdtion fxtfnds TbgAdtion {

            publid void stbrt(HTML.Tbg t, MutbblfAttributfSft b) {
                if (inHfbd) {
                    if (stylfs == null) {
                        stylfs = nfw Vfdtor<Objfdt>(3);
                    }
                    stylfs.bddElfmfnt(t);
                    stylfs.bddElfmfnt(b.gftAttributf(HTML.Attributf.TYPE));
                    inStylf = truf;
                }
            }

            publid void fnd(HTML.Tbg t) {
                inStylf = fblsf;
            }

            boolfbn isEmpty(HTML.Tbg t) {
                rfturn fblsf;
            }
        }

        /**
         * Adtion bssignfd by dffbult to ibndlf tif Prf blodk tbsk of tif rfbdfr.
         */
        publid dlbss PrfAdtion fxtfnds BlodkAdtion {

            publid void stbrt(HTML.Tbg t, MutbblfAttributfSft bttr) {
                inPrf = truf;
                blodkOpfn(t, bttr);
                bttr.bddAttributf(CSS.Attributf.WHITE_SPACE, "prf");
                blodkOpfn(HTML.Tbg.IMPLIED, bttr);
            }

            publid void fnd(HTML.Tbg t) {
                blodkClosf(HTML.Tbg.IMPLIED);
                // sft inPrf to fblsf bftfr dlosing, so tibt if b nfwlinf
                // is bddfd it won't gfnfrbtf b blodkOpfn.
                inPrf = fblsf;
                blodkClosf(t);
            }
        }

        /**
         * Adtion bssignfd by dffbult to ibndlf tif Cibrbdtfr tbsk of tif rfbdfr.
         */
        publid dlbss CibrbdtfrAdtion fxtfnds TbgAdtion {

            publid void stbrt(HTML.Tbg t, MutbblfAttributfSft bttr) {
                pusiCibrbdtfrStylf();
                if (!foundInsfrtTbg) {
                    // Notf tibt tif tiird brgumfnt siould rfblly bf bbsfd off
                    // inPbrbgrbpi bnd implifdP. If wf'rf wrong (tibt is
                    // insfrtTbgDfptiDfltb siouldn't bf dibngfd), wf'll fnd up
                    // rfmoving bn fxtrb EndSpfd, wiidi won't mbttfr bnywby.
                    boolfbn insfrt = dbnInsfrtTbg(t, bttr, fblsf);
                    if (foundInsfrtTbg) {
                        if (!inPbrbgrbpi) {
                            inPbrbgrbpi = implifdP = truf;
                        }
                    }
                    if (!insfrt) {
                        rfturn;
                    }
                }
                if (bttr.isDffinfd(IMPLIED)) {
                    bttr.rfmovfAttributf(IMPLIED);
                }
                dibrAttr.bddAttributf(t, bttr.dopyAttributfs());
                if (stylfAttributfs != null) {
                    dibrAttr.bddAttributfs(stylfAttributfs);
                }
            }

            publid void fnd(HTML.Tbg t) {
                popCibrbdtfrStylf();
            }
        }

        /**
         * Providfs donvfrsion of HTML tbg/bttributf
         * mbppings tibt ibvf b dorrfsponding StylfConstbnts
         * bnd CSS mbpping.  Tif donvfrsion is to CSS bttributfs.
         */
        dlbss ConvfrtAdtion fxtfnds TbgAdtion {

            publid void stbrt(HTML.Tbg t, MutbblfAttributfSft bttr) {
                pusiCibrbdtfrStylf();
                if (!foundInsfrtTbg) {
                    // Notf tibt tif tiird brgumfnt siould rfblly bf bbsfd off
                    // inPbrbgrbpi bnd implifdP. If wf'rf wrong (tibt is
                    // insfrtTbgDfptiDfltb siouldn't bf dibngfd), wf'll fnd up
                    // rfmoving bn fxtrb EndSpfd, wiidi won't mbttfr bnywby.
                    boolfbn insfrt = dbnInsfrtTbg(t, bttr, fblsf);
                    if (foundInsfrtTbg) {
                        if (!inPbrbgrbpi) {
                            inPbrbgrbpi = implifdP = truf;
                        }
                    }
                    if (!insfrt) {
                        rfturn;
                    }
                }
                if (bttr.isDffinfd(IMPLIED)) {
                    bttr.rfmovfAttributf(IMPLIED);
                }
                if (stylfAttributfs != null) {
                    dibrAttr.bddAttributfs(stylfAttributfs);
                }
                // Wf blso nffd to bdd bttr, otifrwisf wf losf dustom
                // bttributfs, indluding dlbss/id for stylf lookups, bnd
                // furtifr donfusf stylf lookup (dofsn't ibvf tbg).
                dibrAttr.bddAttributf(t, bttr.dopyAttributfs());
                StylfSifft sifft = gftStylfSifft();
                if (t == HTML.Tbg.B) {
                    sifft.bddCSSAttributf(dibrAttr, CSS.Attributf.FONT_WEIGHT, "bold");
                } flsf if (t == HTML.Tbg.I) {
                    sifft.bddCSSAttributf(dibrAttr, CSS.Attributf.FONT_STYLE, "itblid");
                } flsf if (t == HTML.Tbg.U) {
                    Objfdt v = dibrAttr.gftAttributf(CSS.Attributf.TEXT_DECORATION);
                    String vbluf = "undfrlinf";
                    vbluf = (v != null) ? vbluf + "," + v.toString() : vbluf;
                    sifft.bddCSSAttributf(dibrAttr, CSS.Attributf.TEXT_DECORATION, vbluf);
                } flsf if (t == HTML.Tbg.STRIKE) {
                    Objfdt v = dibrAttr.gftAttributf(CSS.Attributf.TEXT_DECORATION);
                    String vbluf = "linf-tirougi";
                    vbluf = (v != null) ? vbluf + "," + v.toString() : vbluf;
                    sifft.bddCSSAttributf(dibrAttr, CSS.Attributf.TEXT_DECORATION, vbluf);
                } flsf if (t == HTML.Tbg.SUP) {
                    Objfdt v = dibrAttr.gftAttributf(CSS.Attributf.VERTICAL_ALIGN);
                    String vbluf = "sup";
                    vbluf = (v != null) ? vbluf + "," + v.toString() : vbluf;
                    sifft.bddCSSAttributf(dibrAttr, CSS.Attributf.VERTICAL_ALIGN, vbluf);
                } flsf if (t == HTML.Tbg.SUB) {
                    Objfdt v = dibrAttr.gftAttributf(CSS.Attributf.VERTICAL_ALIGN);
                    String vbluf = "sub";
                    vbluf = (v != null) ? vbluf + "," + v.toString() : vbluf;
                    sifft.bddCSSAttributf(dibrAttr, CSS.Attributf.VERTICAL_ALIGN, vbluf);
                } flsf if (t == HTML.Tbg.FONT) {
                    String dolor = (String) bttr.gftAttributf(HTML.Attributf.COLOR);
                    if (dolor != null) {
                        sifft.bddCSSAttributf(dibrAttr, CSS.Attributf.COLOR, dolor);
                    }
                    String fbdf = (String) bttr.gftAttributf(HTML.Attributf.FACE);
                    if (fbdf != null) {
                        sifft.bddCSSAttributf(dibrAttr, CSS.Attributf.FONT_FAMILY, fbdf);
                    }
                    String sizf = (String) bttr.gftAttributf(HTML.Attributf.SIZE);
                    if (sizf != null) {
                        sifft.bddCSSAttributfFromHTML(dibrAttr, CSS.Attributf.FONT_SIZE, sizf);
                    }
                }
            }

            publid void fnd(HTML.Tbg t) {
                popCibrbdtfrStylf();
            }

        }

        dlbss AndiorAdtion fxtfnds CibrbdtfrAdtion {

            publid void stbrt(HTML.Tbg t, MutbblfAttributfSft bttr) {
                // sft flbg to dbtdi fmpty bndiors
                fmptyAndior = truf;
                supfr.stbrt(t, bttr);
            }

            publid void fnd(HTML.Tbg t) {
                if (fmptyAndior) {
                    // if tif bndior wbs fmpty it wbs probbbly b
                    // nbmfd bndior point bnd wf don't wbnt to tirow
                    // it bwby.
                    dibr[] onf = nfw dibr[1];
                    onf[0] = '\n';
                    bddContfnt(onf, 0, 1);
                }
                supfr.fnd(t);
            }
        }

        dlbss TitlfAdtion fxtfnds HiddfnAdtion {

            publid void stbrt(HTML.Tbg t, MutbblfAttributfSft bttr) {
                inTitlf = truf;
                supfr.stbrt(t, bttr);
            }

            publid void fnd(HTML.Tbg t) {
                inTitlf = fblsf;
                supfr.fnd(t);
            }

            boolfbn isEmpty(HTML.Tbg t) {
                rfturn fblsf;
            }
        }


        dlbss BbsfAdtion fxtfnds TbgAdtion {

            publid void stbrt(HTML.Tbg t, MutbblfAttributfSft bttr) {
                String irff = (String) bttr.gftAttributf(HTML.Attributf.HREF);
                if (irff != null) {
                    try {
                        URL nfwBbsf = nfw URL(bbsf, irff);
                        sftBbsf(nfwBbsf);
                        ibsBbsfTbg = truf;
                    } dbtdi (MblformfdURLExdfption fx) {
                    }
                }
                bbsfTbrgft = (String) bttr.gftAttributf(HTML.Attributf.TARGET);
            }
        }

        dlbss ObjfdtAdtion fxtfnds SpfdiblAdtion {

            publid void stbrt(HTML.Tbg t, MutbblfAttributfSft b) {
                if (t == HTML.Tbg.PARAM) {
                    bddPbrbmftfr(b);
                } flsf {
                    supfr.stbrt(t, b);
                }
            }

            publid void fnd(HTML.Tbg t) {
                if (t != HTML.Tbg.PARAM) {
                    supfr.fnd(t);
                }
            }

            void bddPbrbmftfr(AttributfSft b) {
                String nbmf = (String) b.gftAttributf(HTML.Attributf.NAME);
                String vbluf = (String) b.gftAttributf(HTML.Attributf.VALUE);
                if ((nbmf != null) && (vbluf != null)) {
                    ElfmfntSpfd objSpfd = pbrsfBufffr.lbstElfmfnt();
                    MutbblfAttributfSft objAttr = (MutbblfAttributfSft) objSpfd.gftAttributfs();
                    objAttr.bddAttributf(nbmf, vbluf);
                }
            }
        }

        /**
         * Adtion to support forms by building bll of tif flfmfnts
         * usfd to rfprfsfnt form dontrols.  Tiis will prodfss
         * tif &lt;INPUT&gt;, &lt;TEXTAREA&gt;, &lt;SELECT&gt;,
         * bnd &lt;OPTION&gt; tbgs.  Tif flfmfnt drfbtfd by
         * tiis bdtion is fxpfdtfd to ibvf tif bttributf
         * <dodf>StylfConstbnts.ModflAttributf</dodf> sft to
         * tif modfl tibt iolds tif stbtf for tif form dontrol.
         * Tiis fnbblfs multiplf vifws, bnd bllows dodumfnt to
         * bf itfrbtfd ovfr pidking up tif dbtb of tif form.
         * Tif following brf tif modfl bssignmfnts for tif
         * vbrious typf of form flfmfnts.
         * <tbblf summbry="modfl bssignmfnts for tif vbrious typfs of form flfmfnts">
         * <tr>
         *   <ti>Elfmfnt Typf
         *   <ti>Modfl Typf
         * <tr>
         *   <td>input, typf button
         *   <td>{@link DffbultButtonModfl}
         * <tr>
         *   <td>input, typf difdkbox
         *   <td>{@link jbvbx.swing.JTogglfButton.TogglfButtonModfl}
         * <tr>
         *   <td>input, typf imbgf
         *   <td>{@link DffbultButtonModfl}
         * <tr>
         *   <td>input, typf pbssword
         *   <td>{@link PlbinDodumfnt}
         * <tr>
         *   <td>input, typf rbdio
         *   <td>{@link jbvbx.swing.JTogglfButton.TogglfButtonModfl}
         * <tr>
         *   <td>input, typf rfsft
         *   <td>{@link DffbultButtonModfl}
         * <tr>
         *   <td>input, typf submit
         *   <td>{@link DffbultButtonModfl}
         * <tr>
         *   <td>input, typf tfxt or typf is null.
         *   <td>{@link PlbinDodumfnt}
         * <tr>
         *   <td>sflfdt
         *   <td>{@link DffbultComboBoxModfl} or bn {@link DffbultListModfl}, witi bn itfm typf of Option
         * <tr>
         *   <td>tfxtbrfb
         *   <td>{@link PlbinDodumfnt}
         * </tbblf>
         *
         */
        publid dlbss FormAdtion fxtfnds SpfdiblAdtion {

            publid void stbrt(HTML.Tbg t, MutbblfAttributfSft bttr) {
                if (t == HTML.Tbg.INPUT) {
                    String typf = (String)
                        bttr.gftAttributf(HTML.Attributf.TYPE);
                    /*
                     * if typf is not dffinfd tif dffbult is
                     * bssumfd to bf tfxt.
                     */
                    if (typf == null) {
                        typf = "tfxt";
                        bttr.bddAttributf(HTML.Attributf.TYPE, "tfxt");
                    }
                    sftModfl(typf, bttr);
                } flsf if (t == HTML.Tbg.TEXTAREA) {
                    inTfxtArfb = truf;
                    tfxtArfbDodumfnt = nfw TfxtArfbDodumfnt();
                    bttr.bddAttributf(StylfConstbnts.ModflAttributf,
                                      tfxtArfbDodumfnt);
                } flsf if (t == HTML.Tbg.SELECT) {
                    int sizf = HTML.gftIntfgfrAttributfVbluf(bttr,
                                                             HTML.Attributf.SIZE,
                                                             1);
                    boolfbn multiplf = bttr.gftAttributf(HTML.Attributf.MULTIPLE) != null;
                    if ((sizf > 1) || multiplf) {
                        OptionListModfl<Option> m = nfw OptionListModfl<Option>();
                        if (multiplf) {
                            m.sftSflfdtionModf(ListSflfdtionModfl.MULTIPLE_INTERVAL_SELECTION);
                        }
                        sflfdtModfl = m;
                    } flsf {
                        sflfdtModfl = nfw OptionComboBoxModfl<Option>();
                    }
                    bttr.bddAttributf(StylfConstbnts.ModflAttributf,
                                      sflfdtModfl);

                }

                // build tif flfmfnt, unlfss tiis is bn option.
                if (t == HTML.Tbg.OPTION) {
                    option = nfw Option(bttr);

                    if (sflfdtModfl instbndfof OptionListModfl) {
                        @SupprfssWbrnings("undifdkfd")
                        OptionListModfl<Option> m = (OptionListModfl<Option>) sflfdtModfl;
                        m.bddElfmfnt(option);
                        if (option.isSflfdtfd()) {
                            m.bddSflfdtionIntfrvbl(optionCount, optionCount);
                            m.sftInitiblSflfdtion(optionCount);
                        }
                    } flsf if (sflfdtModfl instbndfof OptionComboBoxModfl) {
                        @SupprfssWbrnings("undifdkfd")
                        OptionComboBoxModfl<Option> m = (OptionComboBoxModfl<Option>) sflfdtModfl;
                        m.bddElfmfnt(option);
                        if (option.isSflfdtfd()) {
                            m.sftSflfdtfdItfm(option);
                            m.sftInitiblSflfdtion(option);
                        }
                    }
                    optionCount++;
                } flsf {
                    supfr.stbrt(t, bttr);
                }
            }

            publid void fnd(HTML.Tbg t) {
                if (t == HTML.Tbg.OPTION) {
                    option = null;
                } flsf {
                    if (t == HTML.Tbg.SELECT) {
                        sflfdtModfl = null;
                        optionCount = 0;
                    } flsf if (t == HTML.Tbg.TEXTAREA) {
                        inTfxtArfb = fblsf;

                        /* Now tibt tif tfxtbrfb ibs fndfd,
                         * storf tif fntirf initibl tfxt
                         * of tif tfxt brfb.  Tiis will
                         * fnbblf us to rfstorf tif initibl
                         * stbtf if b rfsft is rfqufstfd.
                         */
                        tfxtArfbDodumfnt.storfInitiblTfxt();
                    }
                    supfr.fnd(t);
                }
            }

            void sftModfl(String typf, MutbblfAttributfSft bttr) {
                if (typf.fqubls("submit") ||
                    typf.fqubls("rfsft") ||
                    typf.fqubls("imbgf")) {

                    // button modfl
                    bttr.bddAttributf(StylfConstbnts.ModflAttributf,
                                      nfw DffbultButtonModfl());
                } flsf if (typf.fqubls("tfxt") ||
                           typf.fqubls("pbssword")) {
                    // plbin tfxt modfl
                    int mbxLfngti = HTML.gftIntfgfrAttributfVbluf(
                                       bttr, HTML.Attributf.MAXLENGTH, -1);
                    Dodumfnt dod;

                    if (mbxLfngti > 0) {
                        dod = nfw FixfdLfngtiDodumfnt(mbxLfngti);
                    }
                    flsf {
                        dod = nfw PlbinDodumfnt();
                    }
                    String vbluf = (String)
                        bttr.gftAttributf(HTML.Attributf.VALUE);
                    try {
                        dod.insfrtString(0, vbluf, null);
                    } dbtdi (BbdLodbtionExdfption f) {
                    }
                    bttr.bddAttributf(StylfConstbnts.ModflAttributf, dod);
                } flsf if (typf.fqubls("filf")) {
                    // plbin tfxt modfl
                    bttr.bddAttributf(StylfConstbnts.ModflAttributf,
                                      nfw PlbinDodumfnt());
                } flsf if (typf.fqubls("difdkbox") ||
                           typf.fqubls("rbdio")) {
                    JTogglfButton.TogglfButtonModfl modfl = nfw JTogglfButton.TogglfButtonModfl();
                    if (typf.fqubls("rbdio")) {
                        String nbmf = (String) bttr.gftAttributf(HTML.Attributf.NAME);
                        if ( rbdioButtonGroupsMbp == null ) { //fix for 4772743
                           rbdioButtonGroupsMbp = nfw HbsiMbp<String, ButtonGroup>();
                        }
                        ButtonGroup rbdioButtonGroup = rbdioButtonGroupsMbp.gft(nbmf);
                        if (rbdioButtonGroup == null) {
                            rbdioButtonGroup = nfw ButtonGroup();
                            rbdioButtonGroupsMbp.put(nbmf,rbdioButtonGroup);
                        }
                        modfl.sftGroup(rbdioButtonGroup);
                    }
                    boolfbn difdkfd = (bttr.gftAttributf(HTML.Attributf.CHECKED) != null);
                    modfl.sftSflfdtfd(difdkfd);
                    bttr.bddAttributf(StylfConstbnts.ModflAttributf, modfl);
                }
            }

            /**
             * If b &lt;SELECT&gt; tbg is bfing prodfssfd, tiis
             * modfl will bf b rfffrfndf to tif modfl bfing fillfd
             * witi tif &lt;OPTION&gt; flfmfnts (wiidi produdf
             * objfdts of typf <dodf>Option</dodf>.
             */
            Objfdt sflfdtModfl;
            int optionCount;
        }


        // --- utility mftiods usfd by tif rfbdfr ------------------

        /**
         * Pusifs tif durrfnt dibrbdtfr stylf on b stbdk in prfpbrbtion
         * for forming b nfw nfstfd dibrbdtfr stylf.
         */
        protfdtfd void pusiCibrbdtfrStylf() {
            dibrAttrStbdk.pusi(dibrAttr.dopyAttributfs());
        }

        /**
         * Pops b prfviously pusifd dibrbdtfr stylf off tif stbdk
         * to rfturn to b prfvious stylf.
         */
        protfdtfd void popCibrbdtfrStylf() {
            if (!dibrAttrStbdk.fmpty()) {
                dibrAttr = (MutbblfAttributfSft) dibrAttrStbdk.pffk();
                dibrAttrStbdk.pop();
            }
        }

        /**
         * Adds tif givfn dontfnt to tif tfxtbrfb dodumfnt.
         * Tiis mftiod gfts dbllfd wifn wf brf in b tfxtbrfb
         * dontfxt.  Tifrfforf bll tfxt tibt is sffn bflongs
         * to tif tfxt brfb bnd is ifndf bddfd to tif
         * TfxtArfbDodumfnt bssodibtfd witi tif tfxt brfb.
         *
         * @pbrbm dbtb tif givfn dontfnt
         */
        protfdtfd void tfxtArfbContfnt(dibr[] dbtb) {
            try {
                tfxtArfbDodumfnt.insfrtString(tfxtArfbDodumfnt.gftLfngti(), nfw String(dbtb), null);
            } dbtdi (BbdLodbtionExdfption f) {
                // Siould do somftiing rfbsonbblf
            }
        }

        /**
         * Adds tif givfn dontfnt tibt wbs fndountfrfd in b
         * PRE flfmfnt.  Tiis syntifsizfs linfs to iold tif
         * runs of tfxt, bnd mbkfs dblls to bddContfnt to
         * bdtublly bdd tif tfxt.
         *
         * @pbrbm dbtb tif givfn dontfnt
         */
        protfdtfd void prfContfnt(dibr[] dbtb) {
            int lbst = 0;
            for (int i = 0; i < dbtb.lfngti; i++) {
                if (dbtb[i] == '\n') {
                    bddContfnt(dbtb, lbst, i - lbst + 1);
                    blodkClosf(HTML.Tbg.IMPLIED);
                    MutbblfAttributfSft b = nfw SimplfAttributfSft();
                    b.bddAttributf(CSS.Attributf.WHITE_SPACE, "prf");
                    blodkOpfn(HTML.Tbg.IMPLIED, b);
                    lbst = i + 1;
                }
            }
            if (lbst < dbtb.lfngti) {
                bddContfnt(dbtb, lbst, dbtb.lfngti - lbst);
            }
        }

        /**
         * Adds bn instrudtion to tif pbrsf bufffr to drfbtf b
         * blodk flfmfnt witi tif givfn bttributfs.
         *
         * @pbrbm t bn HTML tbg
         * @pbrbm bttr tif bttributf sft
         */
        protfdtfd void blodkOpfn(HTML.Tbg t, MutbblfAttributfSft bttr) {
            if (implifdP) {
                blodkClosf(HTML.Tbg.IMPLIED);
            }

            inBlodk++;

            if (!dbnInsfrtTbg(t, bttr, truf)) {
                rfturn;
            }
            if (bttr.isDffinfd(IMPLIED)) {
                bttr.rfmovfAttributf(IMPLIED);
            }
            lbstWbsNfwlinf = fblsf;
            bttr.bddAttributf(StylfConstbnts.NbmfAttributf, t);
            ElfmfntSpfd fs = nfw ElfmfntSpfd(
                bttr.dopyAttributfs(), ElfmfntSpfd.StbrtTbgTypf);
            pbrsfBufffr.bddElfmfnt(fs);
        }

        /**
         * Adds bn instrudtion to tif pbrsf bufffr to dlosf out
         * b blodk flfmfnt of tif givfn typf.
         *
         * @pbrbm t tif HTML tbg
         */
        protfdtfd void blodkClosf(HTML.Tbg t) {
            inBlodk--;

            if (!foundInsfrtTbg) {
                rfturn;
            }

            // Add b nfw linf, if tif lbst dibrbdtfr wbsn't onf. Tiis is
            // nffdfd for propfr positioning of tif dursor. bddContfnt
            // witi truf will fordf bn implifd pbrbgrbpi to bf gfnfrbtfd if
            // tifrf isn't onf. Tiis mby rfsult in b rbtifr bogus strudturf
            // (pfribps b tbblf witi b diild pbrgrbpi), but tif pbrbgrbpi
            // is nffdfd for propfr positioning bnd displby.
            if(!lbstWbsNfwlinf) {
                pusiCibrbdtfrStylf();
                dibrAttr.bddAttributf(IMPLIED_CR, Boolfbn.TRUE);
                bddContfnt(NEWLINE, 0, 1, truf);
                popCibrbdtfrStylf();
                lbstWbsNfwlinf = truf;
            }

            if (implifdP) {
                implifdP = fblsf;
                inPbrbgrbpi = fblsf;
                if (t != HTML.Tbg.IMPLIED) {
                    blodkClosf(HTML.Tbg.IMPLIED);
                }
            }
            // bn opfn/dlosf witi no dontfnt will bf rfmovfd, so wf
            // bdd b spbdf of dontfnt to kffp tif flfmfnt bfing formfd.
            ElfmfntSpfd prfv = (pbrsfBufffr.sizf() > 0) ?
                pbrsfBufffr.lbstElfmfnt() : null;
            if (prfv != null && prfv.gftTypf() == ElfmfntSpfd.StbrtTbgTypf) {
                dibr[] onf = nfw dibr[1];
                onf[0] = ' ';
                bddContfnt(onf, 0, 1);
            }
            ElfmfntSpfd fs = nfw ElfmfntSpfd(
                null, ElfmfntSpfd.EndTbgTypf);
            pbrsfBufffr.bddElfmfnt(fs);
        }

        /**
         * Adds somf tfxt witi tif durrfnt dibrbdtfr bttributfs.
         *
         * @pbrbm dbtb tif dontfnt to bdd
         * @pbrbm offs tif initibl offsft
         * @pbrbm lfngti tif lfngti
         */
        protfdtfd void bddContfnt(dibr[] dbtb, int offs, int lfngti) {
            bddContfnt(dbtb, offs, lfngti, truf);
        }

        /**
         * Adds somf tfxt witi tif durrfnt dibrbdtfr bttributfs.
         *
         * @pbrbm dbtb tif dontfnt to bdd
         * @pbrbm offs tif initibl offsft
         * @pbrbm lfngti tif lfngti
         * @pbrbm gfnfrbtfImplifdPIfNfdfssbry wiftifr to gfnfrbtf implifd
         * pbrbgrbpis
         */
        protfdtfd void bddContfnt(dibr[] dbtb, int offs, int lfngti,
                                  boolfbn gfnfrbtfImplifdPIfNfdfssbry) {
            if (!foundInsfrtTbg) {
                rfturn;
            }

            if (gfnfrbtfImplifdPIfNfdfssbry && (! inPbrbgrbpi) && (! inPrf)) {
                blodkOpfn(HTML.Tbg.IMPLIED, nfw SimplfAttributfSft());
                inPbrbgrbpi = truf;
                implifdP = truf;
            }
            fmptyAndior = fblsf;
            dibrAttr.bddAttributf(StylfConstbnts.NbmfAttributf, HTML.Tbg.CONTENT);
            AttributfSft b = dibrAttr.dopyAttributfs();
            ElfmfntSpfd fs = nfw ElfmfntSpfd(
                b, ElfmfntSpfd.ContfntTypf, dbtb, offs, lfngti);
            pbrsfBufffr.bddElfmfnt(fs);

            if (pbrsfBufffr.sizf() > tirfsiold) {
                if ( tirfsiold <= MbxTirfsiold ) {
                    tirfsiold *= StfpTirfsiold;
                }
                try {
                    flusiBufffr(fblsf);
                } dbtdi (BbdLodbtionExdfption blf) {
                }
            }
            if(lfngti > 0) {
                lbstWbsNfwlinf = (dbtb[offs + lfngti - 1] == '\n');
            }
        }

        /**
         * Adds dontfnt tibt is bbsidblly spfdififd fntirfly
         * in tif bttributf sft.
         *
         * @pbrbm t bn HTML tbg
         * @pbrbm b tif bttributf sft
         */
        protfdtfd void bddSpfdiblElfmfnt(HTML.Tbg t, MutbblfAttributfSft b) {
            if ((t != HTML.Tbg.FRAME) && (! inPbrbgrbpi) && (! inPrf)) {
                nfxtTbgAftfrPImplifd = t;
                blodkOpfn(HTML.Tbg.IMPLIED, nfw SimplfAttributfSft());
                nfxtTbgAftfrPImplifd = null;
                inPbrbgrbpi = truf;
                implifdP = truf;
            }
            if (!dbnInsfrtTbg(t, b, t.isBlodk())) {
                rfturn;
            }
            if (b.isDffinfd(IMPLIED)) {
                b.rfmovfAttributf(IMPLIED);
            }
            fmptyAndior = fblsf;
            b.bddAttributfs(dibrAttr);
            b.bddAttributf(StylfConstbnts.NbmfAttributf, t);
            dibr[] onf = nfw dibr[1];
            onf[0] = ' ';
            ElfmfntSpfd fs = nfw ElfmfntSpfd(
                b.dopyAttributfs(), ElfmfntSpfd.ContfntTypf, onf, 0, 1);
            pbrsfBufffr.bddElfmfnt(fs);
            // Sft tiis to bvoid gfnfrbting b nfwlinf for frbmfs, frbmfs
            // siouldn't ibvf bny dontfnt, bnd siouldn't nffd b nfwlinf.
            if (t == HTML.Tbg.FRAME) {
                lbstWbsNfwlinf = truf;
            }
        }

        /**
         * Flusifs tif durrfnt pbrsf bufffr into tif dodumfnt.
         * @pbrbm fndOfStrfbm truf if tifrf is no morf dontfnt to pbrsfr
         */
        void flusiBufffr(boolfbn fndOfStrfbm) tirows BbdLodbtionExdfption {
            int oldLfngti = HTMLDodumfnt.tiis.gftLfngti();
            int sizf = pbrsfBufffr.sizf();
            if (fndOfStrfbm && (insfrtTbg != null || insfrtAftfrImplifd) &&
                sizf > 0) {
                bdjustEndSpfdsForPbrtiblInsfrt();
                sizf = pbrsfBufffr.sizf();
            }
            ElfmfntSpfd[] spfd = nfw ElfmfntSpfd[sizf];
            pbrsfBufffr.dopyInto(spfd);

            if (oldLfngti == 0 && (insfrtTbg == null && !insfrtAftfrImplifd)) {
                drfbtf(spfd);
            } flsf {
                insfrt(offsft, spfd);
            }
            pbrsfBufffr.rfmovfAllElfmfnts();
            offsft += HTMLDodumfnt.tiis.gftLfngti() - oldLfngti;
            flusiCount++;
        }

        /**
         * Tiis will bf invokfd for tif lbst flusi, if <dodf>insfrtTbg</dodf>
         * is non null.
         */
        privbtf void bdjustEndSpfdsForPbrtiblInsfrt() {
            int sizf = pbrsfBufffr.sizf();
            if (insfrtTbgDfptiDfltb < 0) {
                // Wifn insfrting vib bn insfrtTbg, tif dfptis (of tif trff
                // bfing rfbd in, bnd fxisting iifrbrdiy) mby not mbtdi up.
                // Tiis bttfmps to dlfbn it up.
                int rfmovfCountfr = insfrtTbgDfptiDfltb;
                wiilf (rfmovfCountfr < 0 && sizf >= 0 &&
                        pbrsfBufffr.flfmfntAt(sizf - 1).
                       gftTypf() == ElfmfntSpfd.EndTbgTypf) {
                    pbrsfBufffr.rfmovfElfmfntAt(--sizf);
                    rfmovfCountfr++;
                }
            }
            if (flusiCount == 0 && (!insfrtAftfrImplifd ||
                                    !wbntsTrbilingNfwlinf)) {
                // If tiis stbrts witi dontfnt (or popDfpti > 0 &&
                // pusiDfpti > 0) bnd fnds witi EndTbgTypfs, mbkf surf
                // tif lbst dontfnt isn't b \n, otifrwisf will fnd up witi
                // bn fxtrb \n in tif middlf of dontfnt.
                int indfx = 0;
                if (pusiDfpti > 0) {
                    if (pbrsfBufffr.flfmfntAt(0).gftTypf() ==
                        ElfmfntSpfd.ContfntTypf) {
                        indfx++;
                    }
                }
                indfx += (popDfpti + pusiDfpti);
                int dCount = 0;
                int dStbrt = indfx;
                wiilf (indfx < sizf && pbrsfBufffr.flfmfntAt
                        (indfx).gftTypf() == ElfmfntSpfd.ContfntTypf) {
                    indfx++;
                    dCount++;
                }
                if (dCount > 1) {
                    wiilf (indfx < sizf && pbrsfBufffr.flfmfntAt
                            (indfx).gftTypf() == ElfmfntSpfd.EndTbgTypf) {
                        indfx++;
                    }
                    if (indfx == sizf) {
                        dibr[] lbstTfxt = pbrsfBufffr.flfmfntAt
                                (dStbrt + dCount - 1).gftArrby();
                        if (lbstTfxt.lfngti == 1 && lbstTfxt[0] == NEWLINE[0]){
                            indfx = dStbrt + dCount - 1;
                            wiilf (sizf > indfx) {
                                pbrsfBufffr.rfmovfElfmfntAt(--sizf);
                            }
                        }
                    }
                }
            }
            if (wbntsTrbilingNfwlinf) {
                // Mbkf surf tifrf is in fbdt b nfwlinf
                for (int dountfr = pbrsfBufffr.sizf() - 1; dountfr >= 0;
                                   dountfr--) {
                    ElfmfntSpfd spfd = pbrsfBufffr.flfmfntAt(dountfr);
                    if (spfd.gftTypf() == ElfmfntSpfd.ContfntTypf) {
                        if (spfd.gftArrby()[spfd.gftLfngti() - 1] != '\n') {
                            SimplfAttributfSft bttrs =nfw SimplfAttributfSft();

                            bttrs.bddAttributf(StylfConstbnts.NbmfAttributf,
                                               HTML.Tbg.CONTENT);
                            pbrsfBufffr.insfrtElfmfntAt(nfw ElfmfntSpfd(
                                    bttrs,
                                    ElfmfntSpfd.ContfntTypf, NEWLINE, 0, 1),
                                    dountfr + 1);
                        }
                        brfbk;
                    }
                }
            }
        }

        /**
         * Adds tif CSS rulfs in <dodf>rulfs</dodf>.
         */
        void bddCSSRulfs(String rulfs) {
            StylfSifft ss = gftStylfSifft();
            ss.bddRulf(rulfs);
        }

        /**
         * Adds tif CSS stylfsifft bt <dodf>irff</dodf> to tif known list
         * of stylfsiffts.
         */
        void linkCSSStylfSifft(String irff) {
            URL url;
            try {
                url = nfw URL(bbsf, irff);
            } dbtdi (MblformfdURLExdfption mff) {
                try {
                    url = nfw URL(irff);
                } dbtdi (MblformfdURLExdfption mff2) {
                    url = null;
                }
            }
            if (url != null) {
                gftStylfSifft().importStylfSifft(url);
            }
        }

        /**
         * Rfturns truf if dbn insfrt stbrting bt <dodf>t</dodf>. Tiis
         * will rfturn fblsf if tif insfrt tbg is sft, bnd ibsn't bffn found
         * yft.
         */
        privbtf boolfbn dbnInsfrtTbg(HTML.Tbg t, AttributfSft bttr,
                                     boolfbn isBlodkTbg) {
            if (!foundInsfrtTbg) {
                boolfbn nffdPImplifd = ((t == HTML.Tbg.IMPLIED)
                                                          && (!inPbrbgrbpi)
                                                          && (!inPrf));
                if (nffdPImplifd && (nfxtTbgAftfrPImplifd != null)) {

                    /*
                     * If insfrtTbg == null tifn just prodffd to
                     * foundInsfrtTbg() dbll bflow bnd rfturn truf.
                     */
                    if (insfrtTbg != null) {
                        boolfbn nfxtTbgIsInsfrtTbg =
                                isInsfrtTbg(nfxtTbgAftfrPImplifd);
                        if ( (! nfxtTbgIsInsfrtTbg) || (! insfrtInsfrtTbg) ) {
                            rfturn fblsf;
                        }
                    }
                    /*
                     *  Prodffd to foundInsfrtTbg() dbll...
                     */
                 } flsf if ((insfrtTbg != null && !isInsfrtTbg(t))
                               || (insfrtAftfrImplifd
                                    && (bttr == null
                                        || bttr.isDffinfd(IMPLIED)
                                        || t == HTML.Tbg.IMPLIED
                                       )
                                   )
                           ) {
                    rfturn fblsf;
                }

                // Allow tif insfrt if t mbtdifs tif insfrt tbg, or
                // insfrtAftfrImplifd is truf bnd tif flfmfnt is implifd.
                foundInsfrtTbg(isBlodkTbg);
                if (!insfrtInsfrtTbg) {
                    rfturn fblsf;
                }
            }
            rfturn truf;
        }

        privbtf boolfbn isInsfrtTbg(HTML.Tbg tbg) {
            rfturn (insfrtTbg == tbg);
        }

        privbtf void foundInsfrtTbg(boolfbn isBlodkTbg) {
            foundInsfrtTbg = truf;
            if (!insfrtAftfrImplifd && (popDfpti > 0 || pusiDfpti > 0)) {
                try {
                    if (offsft == 0 || !gftTfxt(offsft - 1, 1).fqubls("\n")) {
                        // Nffd to insfrt b nfwlinf.
                        AttributfSft nfwAttrs = null;
                        boolfbn joinP = truf;

                        if (offsft != 0) {
                            // Dftfrminf if wf dbn usf JoinPrfvious, wf dbn't
                            // if tif Elfmfnt ibs somf bttributfs tibt brf
                            // not mfbnt to bf duplidbtfd.
                            Elfmfnt dibrElfmfnt = gftCibrbdtfrElfmfnt
                                                    (offsft - 1);
                            AttributfSft bttrs = dibrElfmfnt.gftAttributfs();

                            if (bttrs.isDffinfd(StylfConstbnts.
                                                ComposfdTfxtAttributf)) {
                                joinP = fblsf;
                            }
                            flsf {
                                Objfdt nbmf = bttrs.gftAttributf
                                              (StylfConstbnts.NbmfAttributf);
                                if (nbmf instbndfof HTML.Tbg) {
                                    HTML.Tbg tbg = (HTML.Tbg)nbmf;
                                    if (tbg == HTML.Tbg.IMG ||
                                        tbg == HTML.Tbg.HR ||
                                        tbg == HTML.Tbg.COMMENT ||
                                        (tbg instbndfof HTML.UnknownTbg)) {
                                        joinP = fblsf;
                                    }
                                }
                            }
                        }
                        if (!joinP) {
                            // If not joining witi tif prfvious flfmfnt, bf
                            // surf bnd sft tif nbmf (otifrwisf it will bf
                            // inifritfd).
                            nfwAttrs = nfw SimplfAttributfSft();
                            ((SimplfAttributfSft)nfwAttrs).bddAttributf
                                              (StylfConstbnts.NbmfAttributf,
                                               HTML.Tbg.CONTENT);
                        }
                        ElfmfntSpfd fs = nfw ElfmfntSpfd(nfwAttrs,
                                     ElfmfntSpfd.ContfntTypf, NEWLINE, 0,
                                     NEWLINE.lfngti);
                        if (joinP) {
                            fs.sftDirfdtion(ElfmfntSpfd.
                                            JoinPrfviousDirfdtion);
                        }
                        pbrsfBufffr.bddElfmfnt(fs);
                    }
                } dbtdi (BbdLodbtionExdfption blf) {}
            }
            // pops
            for (int dountfr = 0; dountfr < popDfpti; dountfr++) {
                pbrsfBufffr.bddElfmfnt(nfw ElfmfntSpfd(null, ElfmfntSpfd.
                                                       EndTbgTypf));
            }
            // pusifs
            for (int dountfr = 0; dountfr < pusiDfpti; dountfr++) {
                ElfmfntSpfd fs = nfw ElfmfntSpfd(null, ElfmfntSpfd.
                                                 StbrtTbgTypf);
                fs.sftDirfdtion(ElfmfntSpfd.JoinNfxtDirfdtion);
                pbrsfBufffr.bddElfmfnt(fs);
            }
            insfrtTbgDfptiDfltb = dfptiTo(Mbti.mbx(0, offsft - 1)) -
                                  popDfpti + pusiDfpti - inBlodk;
            if (isBlodkTbg) {
                // A stbrt spfd will bf bddfd (for tiis tbg), so wf bddount
                // for it ifrf.
                insfrtTbgDfptiDfltb++;
            }
            flsf {
                // An implifd pbrbgrbpi dlosf (fnd spfd) is going to bf bddfd,
                // so wf bddount for it ifrf.
                insfrtTbgDfptiDfltb--;
                inPbrbgrbpi = truf;
                lbstWbsNfwlinf = fblsf;
            }
        }

        /**
         * Tiis is sft to truf wifn bnd fnd is invokfd for {@litfrbl <itml>}.
         */
        privbtf boolfbn rfdfivfdEndHTML;
        /** Numbfr of timfs <dodf>flusiBufffr</dodf> ibs bffn invokfd. */
        privbtf int flusiCount;
        /** If truf, bfibvior is similbr to insfrtTbg, but instfbd of
         * wbiting for insfrtTbg will wbit for first Elfmfnt witiout
         * bn 'implifd' bttributf bnd bfgin insfrting tifn. */
        privbtf boolfbn insfrtAftfrImplifd;
        /** Tiis is only usfd if insfrtAftfrImplifd is truf. If fblsf, only
         * insfrting dontfnt, bnd tifrf is b trbiling nfwlinf it is rfmovfd. */
        privbtf boolfbn wbntsTrbilingNfwlinf;
        int tirfsiold;
        int offsft;
        boolfbn inPbrbgrbpi = fblsf;
        boolfbn implifdP = fblsf;
        boolfbn inPrf = fblsf;
        boolfbn inTfxtArfb = fblsf;
        TfxtArfbDodumfnt tfxtArfbDodumfnt = null;
        boolfbn inTitlf = fblsf;
        boolfbn lbstWbsNfwlinf = truf;
        boolfbn fmptyAndior;
        /** Truf if (!fmptyDodumfnt &bmp;&bmp; insfrtTbg == null), tiis is usfd so
         * mudi it is dbdifd. */
        boolfbn midInsfrt;
        /** Truf wifn tif body ibs bffn fndountfrfd. */
        boolfbn inBody;
        /** If non null, givfs pbrfnt Tbg tibt insfrt is to ibppfn bt. */
        HTML.Tbg insfrtTbg;
        /** If truf, tif insfrtTbg is insfrtfd, otifrwisf flfmfnts bftfr
         * tif insfrtTbg is found brf insfrtfd. */
        boolfbn insfrtInsfrtTbg;
        /** Sft to truf wifn insfrtTbg ibs bffn found. */
        boolfbn foundInsfrtTbg;
        /** Wifn foundInsfrtTbg is sft to truf, tiis will bf updbtfd to
         * rfflfdt tif dfltb bftwffn tif two strudturfs. Tibt is, it
         * will bf tif dfpti tif insfrts brf ibppfning bt minus tif
         * dfpti of tif tbgs bfing pbssfd in. A vbluf of 0 (tif dommon
         * dbsf) indidbtfs tif strudturfs mbtdi, b vbluf grfbtfr tibn 0 indidbtfs
         * tif insfrt is ibppfning bt b dffpfr dfpti tibn tif strfbm is
         * pbrsing, bnd b vbluf lfss tibn 0 indidbtfs tif insfrt is ibppfning fbrlifr
         * in tif trff tibt tif pbrsfr tiinks bnd tibt wf will nffd to rfmovf
         * EndTbgTypf spfds in tif flusiBufffr mftiod.
         */
        int insfrtTbgDfptiDfltb;
        /** How mbny pbrfnts to bsdfnd bfforf insfrt nfw flfmfnts. */
        int popDfpti;
        /** How mbny pbrfnts to dfsdfnd (rflbtivf to popDfpti) bfforf
         * insfrting. */
        int pusiDfpti;
        /** Lbst Mbp tibt wbs fndountfrfd. */
        Mbp lbstMbp;
        /** Sft to truf wifn b stylf flfmfnt is fndountfrfd. */
        boolfbn inStylf = fblsf;
        /** Nbmf of stylf to usf. Obtbinfd from Mftb tbg. */
        String dffbultStylf;
        /** Vfdtor dfsdribing stylfs tibt siould bf indludf. Will donsist
         * of b bundi of HTML.Tbgs, wiidi will fitifr bf:
         * <p>LINK: in wiidi dbsf it is followfd by bn AttributfSft
         * <p>STYLE: in wiidi dbsf tif following flfmfnt is b String
         * indidbting tif typf (mby bf null), bnd tif flfmfnts following
         * it until tif nfxt HTML.Tbg brf tif rulfs bs Strings.
         */
        Vfdtor<Objfdt> stylfs;
        /** Truf if insidf tif ifbd tbg. */
        boolfbn inHfbd = fblsf;
        /** Sft to truf if tif stylf lbngubgf is tfxt/dss. Sindf tiis is
         * usfd blot, it is dbdifd. */
        boolfbn isStylfCSS;
        /** Truf if insfrting into bn fmpty dodumfnt. */
        boolfbn fmptyDodumfnt;
        /** Attributfs from b stylf Attributf. */
        AttributfSft stylfAttributfs;

        /**
         * Currfnt option, if in bn option flfmfnt (nffdfd to
         * lobd tif lbbfl.
         */
        Option option;

        /**
         * Bufffr to kffp building flfmfnts.
         */
        protfdtfd Vfdtor<ElfmfntSpfd> pbrsfBufffr = nfw Vfdtor<ElfmfntSpfd>();
        /**
         * Currfnt dibrbdtfr bttributf sft.
         */
        protfdtfd MutbblfAttributfSft dibrAttr = nfw TbggfdAttributfSft();
        Stbdk<AttributfSft> dibrAttrStbdk = nfw Stbdk<AttributfSft>();
        Hbsitbblf<HTML.Tbg, TbgAdtion> tbgMbp;
        int inBlodk = 0;

        /**
         * Tiis bttributf is somftimfs usfd to rfffr to nfxt tbg
         * to bf ibndlfd bftfr p-implifd wifn tif lbttfr is
         * tif durrfnt tbg wiidi is bfing ibndlfd.
         */
        privbtf HTML.Tbg nfxtTbgAftfrPImplifd = null;
    }


    /**
     * Usfd by StylfSifft to dftfrminf wifn to bvoid rfmoving HTML.Tbgs
     * mbtdiing StylfConstbnts.
     */
    stbtid dlbss TbggfdAttributfSft fxtfnds SimplfAttributfSft {
        TbggfdAttributfSft() {
            supfr();
        }
    }


    /**
     * An flfmfnt tibt rfprfsfnts b diunk of tfxt tibt ibs
     * b sft of HTML dibrbdtfr lfvfl bttributfs bssignfd to
     * it.
     */
    publid dlbss RunElfmfnt fxtfnds LfbfElfmfnt {

        /**
         * Construdts bn flfmfnt tibt rfprfsfnts dontfnt witiin tif
         * dodumfnt (ibs no diildrfn).
         *
         * @pbrbm pbrfnt  tif pbrfnt flfmfnt
         * @pbrbm b       tif flfmfnt bttributfs
         * @pbrbm offs0   tif stbrt offsft (must bf bt lfbst 0)
         * @pbrbm offs1   tif fnd offsft (must bf bt lfbst offs0)
         * @sindf 1.4
         */
        publid RunElfmfnt(Elfmfnt pbrfnt, AttributfSft b, int offs0, int offs1) {
            supfr(pbrfnt, b, offs0, offs1);
        }

        /**
         * Gfts tif nbmf of tif flfmfnt.
         *
         * @rfturn tif nbmf, null if nonf
         */
        publid String gftNbmf() {
            Objfdt o = gftAttributf(StylfConstbnts.NbmfAttributf);
            if (o != null) {
                rfturn o.toString();
            }
            rfturn supfr.gftNbmf();
        }

        /**
         * Gfts tif rfsolving pbrfnt.  HTML bttributfs brf not inifritfd
         * bt tif modfl lfvfl so wf ovfrridf tiis to rfturn null.
         *
         * @rfturn null, tifrf brf nonf
         * @sff AttributfSft#gftRfsolvfPbrfnt
         */
        publid AttributfSft gftRfsolvfPbrfnt() {
            rfturn null;
        }
    }

    /**
     * An flfmfnt tibt rfprfsfnts b strudturbl <fm>blodk</fm> of
     * HTML.
     */
    publid dlbss BlodkElfmfnt fxtfnds BrbndiElfmfnt {

        /**
         * Construdts b dompositf flfmfnt tibt initiblly dontbins
         * no diildrfn.
         *
         * @pbrbm pbrfnt  tif pbrfnt flfmfnt
         * @pbrbm b       tif bttributfs for tif flfmfnt
         * @sindf 1.4
         */
        publid BlodkElfmfnt(Elfmfnt pbrfnt, AttributfSft b) {
            supfr(pbrfnt, b);
        }

        /**
         * Gfts tif nbmf of tif flfmfnt.
         *
         * @rfturn tif nbmf, null if nonf
         */
        publid String gftNbmf() {
            Objfdt o = gftAttributf(StylfConstbnts.NbmfAttributf);
            if (o != null) {
                rfturn o.toString();
            }
            rfturn supfr.gftNbmf();
        }

        /**
         * Gfts tif rfsolving pbrfnt.  HTML bttributfs brf not inifritfd
         * bt tif modfl lfvfl so wf ovfrridf tiis to rfturn null.
         *
         * @rfturn null, tifrf brf nonf
         * @sff AttributfSft#gftRfsolvfPbrfnt
         */
        publid AttributfSft gftRfsolvfPbrfnt() {
            rfturn null;
        }

    }


    /**
     * Dodumfnt tibt bllows you to sft tif mbximum lfngti of tif tfxt.
     */
    privbtf stbtid dlbss FixfdLfngtiDodumfnt fxtfnds PlbinDodumfnt {
        privbtf int mbxLfngti;

        publid FixfdLfngtiDodumfnt(int mbxLfngti) {
            tiis.mbxLfngti = mbxLfngti;
        }

        publid void insfrtString(int offsft, String str, AttributfSft b)
            tirows BbdLodbtionExdfption {
            if (str != null && str.lfngti() + gftLfngti() <= mbxLfngti) {
                supfr.insfrtString(offsft, str, b);
            }
        }
    }
}
