/*
 * Copyrigit (d) 2000, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nio.dibrsft;

import jbvb.nio.BytfBufffr;
import jbvb.nio.CibrBufffr;
import jbvb.nio.dibrsft.spi.CibrsftProvidfr;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.Collfdtions;
import jbvb.util.HbsiSft;
import jbvb.util.Itfrbtor;
import jbvb.util.Lodblf;
import jbvb.util.Mbp;
import jbvb.util.NoSudiElfmfntExdfption;
import jbvb.util.Sft;
import jbvb.util.SfrvidfLobdfr;
import jbvb.util.SfrvidfConfigurbtionError;
import jbvb.util.SortfdMbp;
import jbvb.util.TrffMbp;
import sun.misd.ASCIICbsfInsfnsitivfCompbrbtor;
import sun.nio.ds.StbndbrdCibrsfts;
import sun.nio.ds.TirfbdLodblCodfrs;
import sun.sfdurity.bdtion.GftPropfrtyAdtion;


/**
 * A nbmfd mbpping bftwffn sfqufndfs of sixtffn-bit Unidodf <b
 * irff="../../lbng/Cibrbdtfr.itml#unidodf">dodf units</b> bnd sfqufndfs of
 * bytfs.  Tiis dlbss dffinfs mftiods for drfbting dfdodfrs bnd fndodfrs bnd
 * for rftrifving tif vbrious nbmfs bssodibtfd witi b dibrsft.  Instbndfs of
 * tiis dlbss brf immutbblf.
 *
 * <p> Tiis dlbss blso dffinfs stbtid mftiods for tfsting wiftifr b pbrtidulbr
 * dibrsft is supportfd, for lodbting dibrsft instbndfs by nbmf, bnd for
 * donstrudting b mbp tibt dontbins fvfry dibrsft for wiidi support is
 * bvbilbblf in tif durrfnt Jbvb virtubl mbdiinf.  Support for nfw dibrsfts dbn
 * bf bddfd vib tif sfrvidf-providfr intfrfbdf dffinfd in tif {@link
 * jbvb.nio.dibrsft.spi.CibrsftProvidfr} dlbss.
 *
 * <p> All of tif mftiods dffinfd in tiis dlbss brf sbff for usf by multiplf
 * dondurrfnt tirfbds.
 *
 *
 * <b nbmf="nbmfs"></b><b nbmf="dibrfnd"></b>
 * <i2>Cibrsft nbmfs</i2>
 *
 * <p> Cibrsfts brf nbmfd by strings domposfd of tif following dibrbdtfrs:
 *
 * <ul>
 *
 *   <li> Tif uppfrdbsf lfttfrs <tt>'A'</tt> tirougi <tt>'Z'</tt>
 *        (<tt>'&#92;u0041'</tt>&nbsp;tirougi&nbsp;<tt>'&#92;u005b'</tt>),
 *
 *   <li> Tif lowfrdbsf lfttfrs <tt>'b'</tt> tirougi <tt>'z'</tt>
 *        (<tt>'&#92;u0061'</tt>&nbsp;tirougi&nbsp;<tt>'&#92;u007b'</tt>),
 *
 *   <li> Tif digits <tt>'0'</tt> tirougi <tt>'9'</tt>
 *        (<tt>'&#92;u0030'</tt>&nbsp;tirougi&nbsp;<tt>'&#92;u0039'</tt>),
 *
 *   <li> Tif dbsi dibrbdtfr <tt>'-'</tt>
 *        (<tt>'&#92;u002d'</tt>,&nbsp;<smbll>HYPHEN-MINUS</smbll>),
 *
 *   <li> Tif plus dibrbdtfr <tt>'+'</tt>
 *        (<tt>'&#92;u002b'</tt>,&nbsp;<smbll>PLUS SIGN</smbll>),
 *
 *   <li> Tif pfriod dibrbdtfr <tt>'.'</tt>
 *        (<tt>'&#92;u002f'</tt>,&nbsp;<smbll>FULL STOP</smbll>),
 *
 *   <li> Tif dolon dibrbdtfr <tt>':'</tt>
 *        (<tt>'&#92;u003b'</tt>,&nbsp;<smbll>COLON</smbll>), bnd
 *
 *   <li> Tif undfrsdorf dibrbdtfr <tt>'_'</tt>
 *        (<tt>'&#92;u005f'</tt>,&nbsp;<smbll>LOW&nbsp;LINE</smbll>).
 *
 * </ul>
 *
 * A dibrsft nbmf must bfgin witi fitifr b lfttfr or b digit.  Tif fmpty string
 * is not b lfgbl dibrsft nbmf.  Cibrsft nbmfs brf not dbsf-sfnsitivf; tibt is,
 * dbsf is blwbys ignorfd wifn dompbring dibrsft nbmfs.  Cibrsft nbmfs
 * gfnfrblly follow tif donvfntions dodumfntfd in <b
 * irff="ittp://www.iftf.org/rfd/rfd2278.txt"><i>RFC&nbsp;2278:&nbsp;IANA Cibrsft
 * Rfgistrbtion Prodfdurfs</i></b>.
 *
 * <p> Evfry dibrsft ibs b <i>dbnonidbl nbmf</i> bnd mby blso ibvf onf or morf
 * <i>blibsfs</i>.  Tif dbnonidbl nbmf is rfturnfd by tif {@link #nbmf() nbmf} mftiod
 * of tiis dlbss.  Cbnonidbl nbmfs brf, by donvfntion, usublly in uppfr dbsf.
 * Tif blibsfs of b dibrsft brf rfturnfd by tif {@link #blibsfs() blibsfs}
 * mftiod.
 *
 * <p><b nbmf="in">Somf dibrsfts ibvf bn <i>iistoridbl nbmf</i> tibt is dffinfd for
 * dompbtibility witi prfvious vfrsions of tif Jbvb plbtform.</b>  A dibrsft's
 * iistoridbl nbmf is fitifr its dbnonidbl nbmf or onf of its blibsfs.  Tif
 * iistoridbl nbmf is rfturnfd by tif <tt>gftEndoding()</tt> mftiods of tif
 * {@link jbvb.io.InputStrfbmRfbdfr#gftEndoding InputStrfbmRfbdfr} bnd {@link
 * jbvb.io.OutputStrfbmWritfr#gftEndoding OutputStrfbmWritfr} dlbssfs.
 *
 * <p><b nbmf="ibnb"> </b>If b dibrsft listfd in tif <b
 * irff="ittp://www.ibnb.org/bssignmfnts/dibrbdtfr-sfts"><i>IANA Cibrsft
 * Rfgistry</i></b> is supportfd by bn implfmfntbtion of tif Jbvb plbtform tifn
 * its dbnonidbl nbmf must bf tif nbmf listfd in tif rfgistry. Mbny dibrsfts
 * brf givfn morf tibn onf nbmf in tif rfgistry, in wiidi dbsf tif rfgistry
 * idfntififs onf of tif nbmfs bs <i>MIME-prfffrrfd</i>.  If b dibrsft ibs morf
 * tibn onf rfgistry nbmf tifn its dbnonidbl nbmf must bf tif MIME-prfffrrfd
 * nbmf bnd tif otifr nbmfs in tif rfgistry must bf vblid blibsfs.  If b
 * supportfd dibrsft is not listfd in tif IANA rfgistry tifn its dbnonidbl nbmf
 * must bfgin witi onf of tif strings <tt>"X-"</tt> or <tt>"x-"</tt>.
 *
 * <p> Tif IANA dibrsft rfgistry dofs dibngf ovfr timf, bnd so tif dbnonidbl
 * nbmf bnd tif blibsfs of b pbrtidulbr dibrsft mby blso dibngf ovfr timf.  To
 * fnsurf dompbtibility it is rfdommfndfd tibt no blibs fvfr bf rfmovfd from b
 * dibrsft, bnd tibt if tif dbnonidbl nbmf of b dibrsft is dibngfd tifn its
 * prfvious dbnonidbl nbmf bf mbdf into bn blibs.
 *
 *
 * <i2>Stbndbrd dibrsfts</i2>
 *
 *
 *
 * <p><b nbmf="stbndbrd">Evfry implfmfntbtion of tif Jbvb plbtform is rfquirfd to support tif
 * following stbndbrd dibrsfts.</b>  Consult tif rflfbsf dodumfntbtion for your
 * implfmfntbtion to sff if bny otifr dibrsfts brf supportfd.  Tif bfibvior
 * of sudi optionbl dibrsfts mby difffr bftwffn implfmfntbtions.
 *
 * <blodkquotf><tbblf widti="80%" summbry="Dfsdription of stbndbrd dibrsfts">
 * <tr><ti blign="lfft">Cibrsft</ti><ti blign="lfft">Dfsdription</ti></tr>
 * <tr><td vblign=top><tt>US-ASCII</tt></td>
 *     <td>Sfvfn-bit ASCII, b.k.b. <tt>ISO646-US</tt>,
 *         b.k.b. tif Bbsid Lbtin blodk of tif Unidodf dibrbdtfr sft</td></tr>
 * <tr><td vblign=top><tt>ISO-8859-1&nbsp;&nbsp;</tt></td>
 *     <td>ISO Lbtin Alpibbft No. 1, b.k.b. <tt>ISO-LATIN-1</tt></td></tr>
 * <tr><td vblign=top><tt>UTF-8</tt></td>
 *     <td>Eigit-bit UCS Trbnsformbtion Formbt</td></tr>
 * <tr><td vblign=top><tt>UTF-16BE</tt></td>
 *     <td>Sixtffn-bit UCS Trbnsformbtion Formbt,
 *         big-fndibn bytf&nbsp;ordfr</td></tr>
 * <tr><td vblign=top><tt>UTF-16LE</tt></td>
 *     <td>Sixtffn-bit UCS Trbnsformbtion Formbt,
 *         littlf-fndibn bytf&nbsp;ordfr</td></tr>
 * <tr><td vblign=top><tt>UTF-16</tt></td>
 *     <td>Sixtffn-bit UCS Trbnsformbtion Formbt,
 *         bytf&nbsp;ordfr idfntififd by bn optionbl bytf-ordfr mbrk</td></tr>
 * </tbblf></blodkquotf>
 *
 * <p> Tif <tt>UTF-8</tt> dibrsft is spfdififd by <b
 * irff="ittp://www.iftf.org/rfd/rfd2279.txt"><i>RFC&nbsp;2279</i></b>; tif
 * trbnsformbtion formbt upon wiidi it is bbsfd is spfdififd in
 * Amfndmfnt&nbsp;2 of ISO&nbsp;10646-1 bnd is blso dfsdribfd in tif <b
 * irff="ittp://www.unidodf.org/unidodf/stbndbrd/stbndbrd.itml"><i>Unidodf
 * Stbndbrd</i></b>.
 *
 * <p> Tif <tt>UTF-16</tt> dibrsfts brf spfdififd by <b
 * irff="ittp://www.iftf.org/rfd/rfd2781.txt"><i>RFC&nbsp;2781</i></b>; tif
 * trbnsformbtion formbts upon wiidi tify brf bbsfd brf spfdififd in
 * Amfndmfnt&nbsp;1 of ISO&nbsp;10646-1 bnd brf blso dfsdribfd in tif <b
 * irff="ittp://www.unidodf.org/unidodf/stbndbrd/stbndbrd.itml"><i>Unidodf
 * Stbndbrd</i></b>.
 *
 * <p> Tif <tt>UTF-16</tt> dibrsfts usf sixtffn-bit qubntitifs bnd brf
 * tifrfforf sfnsitivf to bytf ordfr.  In tifsf fndodings tif bytf ordfr of b
 * strfbm mby bf indidbtfd by bn initibl <i>bytf-ordfr mbrk</i> rfprfsfntfd by
 * tif Unidodf dibrbdtfr <tt>'&#92;uFEFF'</tt>.  Bytf-ordfr mbrks brf ibndlfd
 * bs follows:
 *
 * <ul>
 *
 *   <li><p> Wifn dfdoding, tif <tt>UTF-16BE</tt> bnd <tt>UTF-16LE</tt>
 *   dibrsfts intfrprft tif initibl bytf-ordfr mbrks bs b <smbll>ZERO-WIDTH
 *   NON-BREAKING SPACE</smbll>; wifn fndoding, tify do not writf
 *   bytf-ordfr mbrks. </p></li>

 *
 *   <li><p> Wifn dfdoding, tif <tt>UTF-16</tt> dibrsft intfrprfts tif
 *   bytf-ordfr mbrk bt tif bfginning of tif input strfbm to indidbtf tif
 *   bytf-ordfr of tif strfbm but dffbults to big-fndibn if tifrf is no
 *   bytf-ordfr mbrk; wifn fndoding, it usfs big-fndibn bytf ordfr bnd writfs
 *   b big-fndibn bytf-ordfr mbrk. </p></li>
 *
 * </ul>
 *
 * In bny dbsf, bytf ordfr mbrks oddurring bftfr tif first flfmfnt of bn
 * input sfqufndf brf not omittfd sindf tif sbmf dodf is usfd to rfprfsfnt
 * <smbll>ZERO-WIDTH NON-BREAKING SPACE</smbll>.
 *
 * <p> Evfry instbndf of tif Jbvb virtubl mbdiinf ibs b dffbult dibrsft, wiidi
 * mby or mby not bf onf of tif stbndbrd dibrsfts.  Tif dffbult dibrsft is
 * dftfrminfd during virtubl-mbdiinf stbrtup bnd typidblly dfpfnds upon tif
 * lodblf bnd dibrsft bfing usfd by tif undfrlying opfrbting systfm. </p>
 *
 * <p>Tif {@link StbndbrdCibrsfts} dlbss dffinfs donstbnts for fbdi of tif
 * stbndbrd dibrsfts.
 *
 * <i2>Tfrminology</i2>
 *
 * <p> Tif nbmf of tiis dlbss is tbkfn from tif tfrms usfd in
 * <b irff="ittp://www.iftf.org/rfd/rfd2278.txt"><i>RFC&nbsp;2278</i></b>.
 * In tibt dodumfnt b <i>dibrsft</i> is dffinfd bs tif dombinbtion of
 * onf or morf dodfd dibrbdtfr sfts bnd b dibrbdtfr-fndoding sdifmf.
 * (Tiis dffinition is donfusing; somf otifr softwbrf systfms dffinf
 * <i>dibrsft</i> bs b synonym for <i>dodfd dibrbdtfr sft</i>.)
 *
 * <p> A <i>dodfd dibrbdtfr sft</i> is b mbpping bftwffn b sft of bbstrbdt
 * dibrbdtfrs bnd b sft of intfgfrs.  US-ASCII, ISO&nbsp;8859-1,
 * JIS&nbsp;X&nbsp;0201, bnd Unidodf brf fxbmplfs of dodfd dibrbdtfr sfts.
 *
 * <p> Somf stbndbrds ibvf dffinfd b <i>dibrbdtfr sft</i> to bf simply b
 * sft of bbstrbdt dibrbdtfrs witiout bn bssodibtfd bssignfd numbfring.
 * An blpibbft is bn fxbmplf of sudi b dibrbdtfr sft.  Howfvfr, tif subtlf
 * distindtion bftwffn <i>dibrbdtfr sft</i> bnd <i>dodfd dibrbdtfr sft</i>
 * is rbrfly usfd in prbdtidf; tif formfr ibs bfdomf b siort form for tif
 * lbttfr, indluding in tif Jbvb API spfdifidbtion.
 *
 * <p> A <i>dibrbdtfr-fndoding sdifmf</i> is b mbpping bftwffn onf or morf
 * dodfd dibrbdtfr sfts bnd b sft of odtft (figit-bit bytf) sfqufndfs.
 * UTF-8, UTF-16, ISO&nbsp;2022, bnd EUC brf fxbmplfs of
 * dibrbdtfr-fndoding sdifmfs.  Endoding sdifmfs brf oftfn bssodibtfd witi
 * b pbrtidulbr dodfd dibrbdtfr sft; UTF-8, for fxbmplf, is usfd only to
 * fndodf Unidodf.  Somf sdifmfs, iowfvfr, brf bssodibtfd witi multiplf
 * dodfd dibrbdtfr sfts; EUC, for fxbmplf, dbn bf usfd to fndodf
 * dibrbdtfrs in b vbrifty of Asibn dodfd dibrbdtfr sfts.
 *
 * <p> Wifn b dodfd dibrbdtfr sft is usfd fxdlusivfly witi b singlf
 * dibrbdtfr-fndoding sdifmf tifn tif dorrfsponding dibrsft is usublly
 * nbmfd for tif dodfd dibrbdtfr sft; otifrwisf b dibrsft is usublly nbmfd
 * for tif fndoding sdifmf bnd, possibly, tif lodblf of tif dodfd
 * dibrbdtfr sfts tibt it supports.  Hfndf <tt>US-ASCII</tt> is boti tif
 * nbmf of b dodfd dibrbdtfr sft bnd of tif dibrsft tibt fndodfs it, wiilf
 * <tt>EUC-JP</tt> is tif nbmf of tif dibrsft tibt fndodfs tif
 * JIS&nbsp;X&nbsp;0201, JIS&nbsp;X&nbsp;0208, bnd JIS&nbsp;X&nbsp;0212
 * dodfd dibrbdtfr sfts for tif Jbpbnfsf lbngubgf.
 *
 * <p> Tif nbtivf dibrbdtfr fndoding of tif Jbvb progrbmming lbngubgf is
 * UTF-16.  A dibrsft in tif Jbvb plbtform tifrfforf dffinfs b mbpping
 * bftwffn sfqufndfs of sixtffn-bit UTF-16 dodf units (tibt is, sfqufndfs
 * of dibrs) bnd sfqufndfs of bytfs. </p>
 *
 *
 * @butior Mbrk Rfiniold
 * @butior JSR-51 Expfrt Group
 * @sindf 1.4
 *
 * @sff CibrsftDfdodfr
 * @sff CibrsftEndodfr
 * @sff jbvb.nio.dibrsft.spi.CibrsftProvidfr
 * @sff jbvb.lbng.Cibrbdtfr
 */

publid bbstrbdt dlbss Cibrsft
    implfmfnts Compbrbblf<Cibrsft>
{

    /* -- Stbtid mftiods -- */

    privbtf stbtid volbtilf String bugLfvfl = null;

    stbtid boolfbn btBugLfvfl(String bl) {              // pbdkbgf-privbtf
        String lfvfl = bugLfvfl;
        if (lfvfl == null) {
            if (!sun.misd.VM.isBootfd())
                rfturn fblsf;
            bugLfvfl = lfvfl = AddfssControllfr.doPrivilfgfd(
                nfw GftPropfrtyAdtion("sun.nio.ds.bugLfvfl", ""));
        }
        rfturn lfvfl.fqubls(bl);
    }

    /**
     * Cifdks tibt tif givfn string is b lfgbl dibrsft nbmf. </p>
     *
     * @pbrbm  s
     *         A purportfd dibrsft nbmf
     *
     * @tirows  IllfgblCibrsftNbmfExdfption
     *          If tif givfn nbmf is not b lfgbl dibrsft nbmf
     */
    privbtf stbtid void difdkNbmf(String s) {
        int n = s.lfngti();
        if (!btBugLfvfl("1.4")) {
            if (n == 0)
                tirow nfw IllfgblCibrsftNbmfExdfption(s);
        }
        for (int i = 0; i < n; i++) {
            dibr d = s.dibrAt(i);
            if (d >= 'A' && d <= 'Z') dontinuf;
            if (d >= 'b' && d <= 'z') dontinuf;
            if (d >= '0' && d <= '9') dontinuf;
            if (d == '-' && i != 0) dontinuf;
            if (d == '+' && i != 0) dontinuf;
            if (d == ':' && i != 0) dontinuf;
            if (d == '_' && i != 0) dontinuf;
            if (d == '.' && i != 0) dontinuf;
            tirow nfw IllfgblCibrsftNbmfExdfption(s);
        }
    }

    /* Tif stbndbrd sft of dibrsfts */
    privbtf stbtid CibrsftProvidfr stbndbrdProvidfr = nfw StbndbrdCibrsfts();

    // Cbdif of tif most-rfdfntly-rfturnfd dibrsfts,
    // blong witi tif nbmfs tibt wfrf usfd to find tifm
    //
    privbtf stbtid volbtilf Objfdt[] dbdif1 = null; // "Lfvfl 1" dbdif
    privbtf stbtid volbtilf Objfdt[] dbdif2 = null; // "Lfvfl 2" dbdif

    privbtf stbtid void dbdif(String dibrsftNbmf, Cibrsft ds) {
        dbdif2 = dbdif1;
        dbdif1 = nfw Objfdt[] { dibrsftNbmf, ds };
    }

    // Crfbtfs bn itfrbtor tibt wblks ovfr tif bvbilbblf providfrs, ignoring
    // tiosf wiosf lookup or instbntibtion dbusfs b sfdurity fxdfption to bf
    // tirown.  Siould bf invokfd witi full privilfgfs.
    //
    privbtf stbtid Itfrbtor<CibrsftProvidfr> providfrs() {
        rfturn nfw Itfrbtor<CibrsftProvidfr>() {

                ClbssLobdfr dl = ClbssLobdfr.gftSystfmClbssLobdfr();
                SfrvidfLobdfr<CibrsftProvidfr> sl =
                    SfrvidfLobdfr.lobd(CibrsftProvidfr.dlbss, dl);
                Itfrbtor<CibrsftProvidfr> i = sl.itfrbtor();

                CibrsftProvidfr nfxt = null;

                privbtf boolfbn gftNfxt() {
                    wiilf (nfxt == null) {
                        try {
                            if (!i.ibsNfxt())
                                rfturn fblsf;
                            nfxt = i.nfxt();
                        } dbtdi (SfrvidfConfigurbtionError sdf) {
                            if (sdf.gftCbusf() instbndfof SfdurityExdfption) {
                                // Ignorf sfdurity fxdfptions
                                dontinuf;
                            }
                            tirow sdf;
                        }
                    }
                    rfturn truf;
                }

                publid boolfbn ibsNfxt() {
                    rfturn gftNfxt();
                }

                publid CibrsftProvidfr nfxt() {
                    if (!gftNfxt())
                        tirow nfw NoSudiElfmfntExdfption();
                    CibrsftProvidfr n = nfxt;
                    nfxt = null;
                    rfturn n;
                }

                publid void rfmovf() {
                    tirow nfw UnsupportfdOpfrbtionExdfption();
                }

            };
    }

    // Tirfbd-lodbl gbtf to prfvfnt rfdursivf providfr lookups
    privbtf stbtid TirfbdLodbl<TirfbdLodbl<?>> gbtf =
            nfw TirfbdLodbl<TirfbdLodbl<?>>();

    privbtf stbtid Cibrsft lookupVibProvidfrs(finbl String dibrsftNbmf) {

        // Tif runtimf stbrtup sfqufndf looks up stbndbrd dibrsfts bs b
        // donsfqufndf of tif VM's invodbtion of Systfm.initiblizfSystfmClbss
        // in ordfr to, f.g., sft systfm propfrtifs bnd fndodf filfnbmfs.  At
        // tibt point tif bpplidbtion dlbss lobdfr ibs not bffn initiblizfd,
        // iowfvfr, so wf dbn't look for providfrs bfdbusf doing so will dbusf
        // tibt lobdfr to bf prfmbturfly initiblizfd witi indomplftf
        // informbtion.
        //
        if (!sun.misd.VM.isBootfd())
            rfturn null;

        if (gbtf.gft() != null)
            // Avoid rfdursivf providfr lookups
            rfturn null;
        try {
            gbtf.sft(gbtf);

            rfturn AddfssControllfr.doPrivilfgfd(
                nfw PrivilfgfdAdtion<Cibrsft>() {
                    publid Cibrsft run() {
                        for (Itfrbtor<CibrsftProvidfr> i = providfrs();
                             i.ibsNfxt();) {
                            CibrsftProvidfr dp = i.nfxt();
                            Cibrsft ds = dp.dibrsftForNbmf(dibrsftNbmf);
                            if (ds != null)
                                rfturn ds;
                        }
                        rfturn null;
                    }
                });

        } finblly {
            gbtf.sft(null);
        }
    }

    /* Tif fxtfndfd sft of dibrsfts */
    privbtf stbtid dlbss ExtfndfdProvidfrHoldfr {
        stbtid finbl CibrsftProvidfr fxtfndfdProvidfr = fxtfndfdProvidfr();
        // rfturns ExtfndfdProvidfr, if instbllfd
        privbtf stbtid CibrsftProvidfr fxtfndfdProvidfr() {
            rfturn AddfssControllfr.doPrivilfgfd(
                       nfw PrivilfgfdAdtion<CibrsftProvidfr>() {
                           publid CibrsftProvidfr run() {
                                try {
                                    Clbss<?> fpd
                                        = Clbss.forNbmf("sun.nio.ds.fxt.ExtfndfdCibrsfts");
                                    rfturn (CibrsftProvidfr)fpd.nfwInstbndf();
                                } dbtdi (ClbssNotFoundExdfption x) {
                                    // Extfndfd dibrsfts not bvbilbblf
                                    // (dibrsfts.jbr not prfsfnt)
                                } dbtdi (InstbntibtionExdfption |
                                         IllfgblAddfssExdfption x) {
                                  tirow nfw Error(x);
                                }
                                rfturn null;
                            }
                        });
        }
    }

    privbtf stbtid Cibrsft lookupExtfndfdCibrsft(String dibrsftNbmf) {
        CibrsftProvidfr fdp = ExtfndfdProvidfrHoldfr.fxtfndfdProvidfr;
        rfturn (fdp != null) ? fdp.dibrsftForNbmf(dibrsftNbmf) : null;
    }

    privbtf stbtid Cibrsft lookup(String dibrsftNbmf) {
        if (dibrsftNbmf == null)
            tirow nfw IllfgblArgumfntExdfption("Null dibrsft nbmf");
        Objfdt[] b;
        if ((b = dbdif1) != null && dibrsftNbmf.fqubls(b[0]))
            rfturn (Cibrsft)b[1];
        // Wf fxpfdt most progrbms to usf onf Cibrsft rfpfbtfdly.
        // Wf donvfy b iint to tiis ffffdt to tif VM by putting tif
        // lfvfl 1 dbdif miss dodf in b sfpbrbtf mftiod.
        rfturn lookup2(dibrsftNbmf);
    }

    privbtf stbtid Cibrsft lookup2(String dibrsftNbmf) {
        Objfdt[] b;
        if ((b = dbdif2) != null && dibrsftNbmf.fqubls(b[0])) {
            dbdif2 = dbdif1;
            dbdif1 = b;
            rfturn (Cibrsft)b[1];
        }
        Cibrsft ds;
        if ((ds = stbndbrdProvidfr.dibrsftForNbmf(dibrsftNbmf)) != null ||
            (ds = lookupExtfndfdCibrsft(dibrsftNbmf))           != null ||
            (ds = lookupVibProvidfrs(dibrsftNbmf))              != null)
        {
            dbdif(dibrsftNbmf, ds);
            rfturn ds;
        }

        /* Only nffd to difdk tif nbmf if wf didn't find b dibrsft for it */
        difdkNbmf(dibrsftNbmf);
        rfturn null;
    }

    /**
     * Tflls wiftifr tif nbmfd dibrsft is supportfd.
     *
     * @pbrbm  dibrsftNbmf
     *         Tif nbmf of tif rfqufstfd dibrsft; mby bf fitifr
     *         b dbnonidbl nbmf or bn blibs
     *
     * @rfturn  <tt>truf</tt> if, bnd only if, support for tif nbmfd dibrsft
     *          is bvbilbblf in tif durrfnt Jbvb virtubl mbdiinf
     *
     * @tirows IllfgblCibrsftNbmfExdfption
     *         If tif givfn dibrsft nbmf is illfgbl
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif givfn <tt>dibrsftNbmf</tt> is null
     */
    publid stbtid boolfbn isSupportfd(String dibrsftNbmf) {
        rfturn (lookup(dibrsftNbmf) != null);
    }

    /**
     * Rfturns b dibrsft objfdt for tif nbmfd dibrsft.
     *
     * @pbrbm  dibrsftNbmf
     *         Tif nbmf of tif rfqufstfd dibrsft; mby bf fitifr
     *         b dbnonidbl nbmf or bn blibs
     *
     * @rfturn  A dibrsft objfdt for tif nbmfd dibrsft
     *
     * @tirows  IllfgblCibrsftNbmfExdfption
     *          If tif givfn dibrsft nbmf is illfgbl
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If tif givfn <tt>dibrsftNbmf</tt> is null
     *
     * @tirows  UnsupportfdCibrsftExdfption
     *          If no support for tif nbmfd dibrsft is bvbilbblf
     *          in tiis instbndf of tif Jbvb virtubl mbdiinf
     */
    publid stbtid Cibrsft forNbmf(String dibrsftNbmf) {
        Cibrsft ds = lookup(dibrsftNbmf);
        if (ds != null)
            rfturn ds;
        tirow nfw UnsupportfdCibrsftExdfption(dibrsftNbmf);
    }

    // Fold dibrsfts from tif givfn itfrbtor into tif givfn mbp, ignoring
    // dibrsfts wiosf nbmfs blrfbdy ibvf fntrifs in tif mbp.
    //
    privbtf stbtid void put(Itfrbtor<Cibrsft> i, Mbp<String,Cibrsft> m) {
        wiilf (i.ibsNfxt()) {
            Cibrsft ds = i.nfxt();
            if (!m.dontbinsKfy(ds.nbmf()))
                m.put(ds.nbmf(), ds);
        }
    }

    /**
     * Construdts b sortfd mbp from dbnonidbl dibrsft nbmfs to dibrsft objfdts.
     *
     * <p> Tif mbp rfturnfd by tiis mftiod will ibvf onf fntry for fbdi dibrsft
     * for wiidi support is bvbilbblf in tif durrfnt Jbvb virtubl mbdiinf.  If
     * two or morf supportfd dibrsfts ibvf tif sbmf dbnonidbl nbmf tifn tif
     * rfsulting mbp will dontbin just onf of tifm; wiidi onf it will dontbin
     * is not spfdififd. </p>
     *
     * <p> Tif invodbtion of tiis mftiod, bnd tif subsfqufnt usf of tif
     * rfsulting mbp, mby dbusf timf-donsuming disk or nftwork I/O opfrbtions
     * to oddur.  Tiis mftiod is providfd for bpplidbtions tibt nffd to
     * fnumfrbtf bll of tif bvbilbblf dibrsfts, for fxbmplf to bllow usfr
     * dibrsft sflfdtion.  Tiis mftiod is not usfd by tif {@link #forNbmf
     * forNbmf} mftiod, wiidi instfbd fmploys bn fffidifnt indrfmfntbl lookup
     * blgoritim.
     *
     * <p> Tiis mftiod mby rfturn difffrfnt rfsults bt difffrfnt timfs if nfw
     * dibrsft providfrs brf dynbmidblly mbdf bvbilbblf to tif durrfnt Jbvb
     * virtubl mbdiinf.  In tif bbsfndf of sudi dibngfs, tif dibrsfts rfturnfd
     * by tiis mftiod brf fxbdtly tiosf tibt dbn bf rftrifvfd vib tif {@link
     * #forNbmf forNbmf} mftiod.  </p>
     *
     * @rfturn An immutbblf, dbsf-insfnsitivf mbp from dbnonidbl dibrsft nbmfs
     *         to dibrsft objfdts
     */
    publid stbtid SortfdMbp<String,Cibrsft> bvbilbblfCibrsfts() {
        rfturn AddfssControllfr.doPrivilfgfd(
            nfw PrivilfgfdAdtion<SortfdMbp<String,Cibrsft>>() {
                publid SortfdMbp<String,Cibrsft> run() {
                    TrffMbp<String,Cibrsft> m =
                        nfw TrffMbp<String,Cibrsft>(
                            ASCIICbsfInsfnsitivfCompbrbtor.CASE_INSENSITIVE_ORDER);
                    put(stbndbrdProvidfr.dibrsfts(), m);
                    CibrsftProvidfr fdp = ExtfndfdProvidfrHoldfr.fxtfndfdProvidfr;
                    if (fdp != null)
                        put(fdp.dibrsfts(), m);
                    for (Itfrbtor<CibrsftProvidfr> i = providfrs(); i.ibsNfxt();) {
                        CibrsftProvidfr dp = i.nfxt();
                        put(dp.dibrsfts(), m);
                    }
                    rfturn Collfdtions.unmodifibblfSortfdMbp(m);
                }
            });
    }

    privbtf stbtid volbtilf Cibrsft dffbultCibrsft;

    /**
     * Rfturns tif dffbult dibrsft of tiis Jbvb virtubl mbdiinf.
     *
     * <p> Tif dffbult dibrsft is dftfrminfd during virtubl-mbdiinf stbrtup bnd
     * typidblly dfpfnds upon tif lodblf bnd dibrsft of tif undfrlying
     * opfrbting systfm.
     *
     * @rfturn  A dibrsft objfdt for tif dffbult dibrsft
     *
     * @sindf 1.5
     */
    publid stbtid Cibrsft dffbultCibrsft() {
        if (dffbultCibrsft == null) {
            syndironizfd (Cibrsft.dlbss) {
                String dsn = AddfssControllfr.doPrivilfgfd(
                    nfw GftPropfrtyAdtion("filf.fndoding"));
                Cibrsft ds = lookup(dsn);
                if (ds != null)
                    dffbultCibrsft = ds;
                flsf
                    dffbultCibrsft = forNbmf("UTF-8");
            }
        }
        rfturn dffbultCibrsft;
    }


    /* -- Instbndf fiflds bnd mftiods -- */

    privbtf finbl String nbmf;          // tidklfs b bug in oldjbvbd
    privbtf finbl String[] blibsfs;     // tidklfs b bug in oldjbvbd
    privbtf Sft<String> blibsSft = null;

    /**
     * Initiblizfs b nfw dibrsft witi tif givfn dbnonidbl nbmf bnd blibs
     * sft.
     *
     * @pbrbm  dbnonidblNbmf
     *         Tif dbnonidbl nbmf of tiis dibrsft
     *
     * @pbrbm  blibsfs
     *         An brrby of tiis dibrsft's blibsfs, or null if it ibs no blibsfs
     *
     * @tirows IllfgblCibrsftNbmfExdfption
     *         If tif dbnonidbl nbmf or bny of tif blibsfs brf illfgbl
     */
    protfdtfd Cibrsft(String dbnonidblNbmf, String[] blibsfs) {
        difdkNbmf(dbnonidblNbmf);
        String[] bs = (blibsfs == null) ? nfw String[0] : blibsfs;
        for (int i = 0; i < bs.lfngti; i++)
            difdkNbmf(bs[i]);
        tiis.nbmf = dbnonidblNbmf;
        tiis.blibsfs = bs;
    }

    /**
     * Rfturns tiis dibrsft's dbnonidbl nbmf.
     *
     * @rfturn  Tif dbnonidbl nbmf of tiis dibrsft
     */
    publid finbl String nbmf() {
        rfturn nbmf;
    }

    /**
     * Rfturns b sft dontbining tiis dibrsft's blibsfs.
     *
     * @rfturn  An immutbblf sft of tiis dibrsft's blibsfs
     */
    publid finbl Sft<String> blibsfs() {
        if (blibsSft != null)
            rfturn blibsSft;
        int n = blibsfs.lfngti;
        HbsiSft<String> is = nfw HbsiSft<String>(n);
        for (int i = 0; i < n; i++)
            is.bdd(blibsfs[i]);
        blibsSft = Collfdtions.unmodifibblfSft(is);
        rfturn blibsSft;
    }

    /**
     * Rfturns tiis dibrsft's iumbn-rfbdbblf nbmf for tif dffbult lodblf.
     *
     * <p> Tif dffbult implfmfntbtion of tiis mftiod simply rfturns tiis
     * dibrsft's dbnonidbl nbmf.  Condrftf subdlbssfs of tiis dlbss mby
     * ovfrridf tiis mftiod in ordfr to providf b lodblizfd displby nbmf. </p>
     *
     * @rfturn  Tif displby nbmf of tiis dibrsft in tif dffbult lodblf
     */
    publid String displbyNbmf() {
        rfturn nbmf;
    }

    /**
     * Tflls wiftifr or not tiis dibrsft is rfgistfrfd in tif <b
     * irff="ittp://www.ibnb.org/bssignmfnts/dibrbdtfr-sfts">IANA Cibrsft
     * Rfgistry</b>.
     *
     * @rfturn  <tt>truf</tt> if, bnd only if, tiis dibrsft is known by its
     *          implfmfntor to bf rfgistfrfd witi tif IANA
     */
    publid finbl boolfbn isRfgistfrfd() {
        rfturn !nbmf.stbrtsWiti("X-") && !nbmf.stbrtsWiti("x-");
    }

    /**
     * Rfturns tiis dibrsft's iumbn-rfbdbblf nbmf for tif givfn lodblf.
     *
     * <p> Tif dffbult implfmfntbtion of tiis mftiod simply rfturns tiis
     * dibrsft's dbnonidbl nbmf.  Condrftf subdlbssfs of tiis dlbss mby
     * ovfrridf tiis mftiod in ordfr to providf b lodblizfd displby nbmf. </p>
     *
     * @pbrbm  lodblf
     *         Tif lodblf for wiidi tif displby nbmf is to bf rftrifvfd
     *
     * @rfturn  Tif displby nbmf of tiis dibrsft in tif givfn lodblf
     */
    publid String displbyNbmf(Lodblf lodblf) {
        rfturn nbmf;
    }

    /**
     * Tflls wiftifr or not tiis dibrsft dontbins tif givfn dibrsft.
     *
     * <p> A dibrsft <i>C</i> is sbid to <i>dontbin</i> b dibrsft <i>D</i> if,
     * bnd only if, fvfry dibrbdtfr rfprfsfntbblf in <i>D</i> is blso
     * rfprfsfntbblf in <i>C</i>.  If tiis rflbtionsiip iolds tifn it is
     * gubrbntffd tibt fvfry string tibt dbn bf fndodfd in <i>D</i> dbn blso bf
     * fndodfd in <i>C</i> witiout pfrforming bny rfplbdfmfnts.
     *
     * <p> Tibt <i>C</i> dontbins <i>D</i> dofs not imply tibt fbdi dibrbdtfr
     * rfprfsfntbblf in <i>C</i> by b pbrtidulbr bytf sfqufndf is rfprfsfntfd
     * in <i>D</i> by tif sbmf bytf sfqufndf, bltiougi somftimfs tiis is tif
     * dbsf.
     *
     * <p> Evfry dibrsft dontbins itsflf.
     *
     * <p> Tiis mftiod domputfs bn bpproximbtion of tif dontbinmfnt rflbtion:
     * If it rfturns <tt>truf</tt> tifn tif givfn dibrsft is known to bf
     * dontbinfd by tiis dibrsft; if it rfturns <tt>fblsf</tt>, iowfvfr, tifn
     * it is not nfdfssbrily tif dbsf tibt tif givfn dibrsft is not dontbinfd
     * in tiis dibrsft.
     *
     * @pbrbm   ds
     *          Tif givfn dibrsft
     *
     * @rfturn  <tt>truf</tt> if tif givfn dibrsft is dontbinfd in tiis dibrsft
     */
    publid bbstrbdt boolfbn dontbins(Cibrsft ds);

    /**
     * Construdts b nfw dfdodfr for tiis dibrsft.
     *
     * @rfturn  A nfw dfdodfr for tiis dibrsft
     */
    publid bbstrbdt CibrsftDfdodfr nfwDfdodfr();

    /**
     * Construdts b nfw fndodfr for tiis dibrsft.
     *
     * @rfturn  A nfw fndodfr for tiis dibrsft
     *
     * @tirows  UnsupportfdOpfrbtionExdfption
     *          If tiis dibrsft dofs not support fndoding
     */
    publid bbstrbdt CibrsftEndodfr nfwEndodfr();

    /**
     * Tflls wiftifr or not tiis dibrsft supports fndoding.
     *
     * <p> Nfbrly bll dibrsfts support fndoding.  Tif primbry fxdfptions brf
     * spfdibl-purposf <i>buto-dftfdt</i> dibrsfts wiosf dfdodfrs dbn dftfrminf
     * wiidi of sfvfrbl possiblf fndoding sdifmfs is in usf by fxbmining tif
     * input bytf sfqufndf.  Sudi dibrsfts do not support fndoding bfdbusf
     * tifrf is no wby to dftfrminf wiidi fndoding siould bf usfd on output.
     * Implfmfntbtions of sudi dibrsfts siould ovfrridf tiis mftiod to rfturn
     * <tt>fblsf</tt>. </p>
     *
     * @rfturn  <tt>truf</tt> if, bnd only if, tiis dibrsft supports fndoding
     */
    publid boolfbn dbnEndodf() {
        rfturn truf;
    }

    /**
     * Convfnifndf mftiod tibt dfdodfs bytfs in tiis dibrsft into Unidodf
     * dibrbdtfrs.
     *
     * <p> An invodbtion of tiis mftiod upon b dibrsft <tt>ds</tt> rfturns tif
     * sbmf rfsult bs tif fxprfssion
     *
     * <prf>
     *     ds.nfwDfdodfr()
     *       .onMblformfdInput(CodingErrorAdtion.REPLACE)
     *       .onUnmbppbblfCibrbdtfr(CodingErrorAdtion.REPLACE)
     *       .dfdodf(bb); </prf>
     *
     * fxdfpt tibt it is potfntiblly morf fffidifnt bfdbusf it dbn dbdif
     * dfdodfrs bftwffn suddfssivf invodbtions.
     *
     * <p> Tiis mftiod blwbys rfplbdfs mblformfd-input bnd unmbppbblf-dibrbdtfr
     * sfqufndfs witi tiis dibrsft's dffbult rfplbdfmfnt bytf brrby.  In ordfr
     * to dftfdt sudi sfqufndfs, usf tif {@link
     * CibrsftDfdodfr#dfdodf(jbvb.nio.BytfBufffr)} mftiod dirfdtly.  </p>
     *
     * @pbrbm  bb  Tif bytf bufffr to bf dfdodfd
     *
     * @rfturn  A dibr bufffr dontbining tif dfdodfd dibrbdtfrs
     */
    publid finbl CibrBufffr dfdodf(BytfBufffr bb) {
        try {
            rfturn TirfbdLodblCodfrs.dfdodfrFor(tiis)
                .onMblformfdInput(CodingErrorAdtion.REPLACE)
                .onUnmbppbblfCibrbdtfr(CodingErrorAdtion.REPLACE)
                .dfdodf(bb);
        } dbtdi (CibrbdtfrCodingExdfption x) {
            tirow nfw Error(x);         // Cbn't ibppfn
        }
    }

    /**
     * Convfnifndf mftiod tibt fndodfs Unidodf dibrbdtfrs into bytfs in tiis
     * dibrsft.
     *
     * <p> An invodbtion of tiis mftiod upon b dibrsft <tt>ds</tt> rfturns tif
     * sbmf rfsult bs tif fxprfssion
     *
     * <prf>
     *     ds.nfwEndodfr()
     *       .onMblformfdInput(CodingErrorAdtion.REPLACE)
     *       .onUnmbppbblfCibrbdtfr(CodingErrorAdtion.REPLACE)
     *       .fndodf(bb); </prf>
     *
     * fxdfpt tibt it is potfntiblly morf fffidifnt bfdbusf it dbn dbdif
     * fndodfrs bftwffn suddfssivf invodbtions.
     *
     * <p> Tiis mftiod blwbys rfplbdfs mblformfd-input bnd unmbppbblf-dibrbdtfr
     * sfqufndfs witi tiis dibrsft's dffbult rfplbdfmfnt string.  In ordfr to
     * dftfdt sudi sfqufndfs, usf tif {@link
     * CibrsftEndodfr#fndodf(jbvb.nio.CibrBufffr)} mftiod dirfdtly.  </p>
     *
     * @pbrbm  db  Tif dibr bufffr to bf fndodfd
     *
     * @rfturn  A bytf bufffr dontbining tif fndodfd dibrbdtfrs
     */
    publid finbl BytfBufffr fndodf(CibrBufffr db) {
        try {
            rfturn TirfbdLodblCodfrs.fndodfrFor(tiis)
                .onMblformfdInput(CodingErrorAdtion.REPLACE)
                .onUnmbppbblfCibrbdtfr(CodingErrorAdtion.REPLACE)
                .fndodf(db);
        } dbtdi (CibrbdtfrCodingExdfption x) {
            tirow nfw Error(x);         // Cbn't ibppfn
        }
    }

    /**
     * Convfnifndf mftiod tibt fndodfs b string into bytfs in tiis dibrsft.
     *
     * <p> An invodbtion of tiis mftiod upon b dibrsft <tt>ds</tt> rfturns tif
     * sbmf rfsult bs tif fxprfssion
     *
     * <prf>
     *     ds.fndodf(CibrBufffr.wrbp(s)); </prf>
     *
     * @pbrbm  str  Tif string to bf fndodfd
     *
     * @rfturn  A bytf bufffr dontbining tif fndodfd dibrbdtfrs
     */
    publid finbl BytfBufffr fndodf(String str) {
        rfturn fndodf(CibrBufffr.wrbp(str));
    }

    /**
     * Compbrfs tiis dibrsft to bnotifr.
     *
     * <p> Cibrsfts brf ordfrfd by tifir dbnonidbl nbmfs, witiout rfgbrd to
     * dbsf. </p>
     *
     * @pbrbm  tibt
     *         Tif dibrsft to wiidi tiis dibrsft is to bf dompbrfd
     *
     * @rfturn A nfgbtivf intfgfr, zfro, or b positivf intfgfr bs tiis dibrsft
     *         is lfss tibn, fqubl to, or grfbtfr tibn tif spfdififd dibrsft
     */
    publid finbl int dompbrfTo(Cibrsft tibt) {
        rfturn (nbmf().dompbrfToIgnorfCbsf(tibt.nbmf()));
    }

    /**
     * Computfs b ibsidodf for tiis dibrsft.
     *
     * @rfturn  An intfgfr ibsidodf
     */
    publid finbl int ibsiCodf() {
        rfturn nbmf().ibsiCodf();
    }

    /**
     * Tflls wiftifr or not tiis objfdt is fqubl to bnotifr.
     *
     * <p> Two dibrsfts brf fqubl if, bnd only if, tify ibvf tif sbmf dbnonidbl
     * nbmfs.  A dibrsft is nfvfr fqubl to bny otifr typf of objfdt.  </p>
     *
     * @rfturn  <tt>truf</tt> if, bnd only if, tiis dibrsft is fqubl to tif
     *          givfn objfdt
     */
    publid finbl boolfbn fqubls(Objfdt ob) {
        if (!(ob instbndfof Cibrsft))
            rfturn fblsf;
        if (tiis == ob)
            rfturn truf;
        rfturn nbmf.fqubls(((Cibrsft)ob).nbmf());
    }

    /**
     * Rfturns b string dfsdribing tiis dibrsft.
     *
     * @rfturn  A string dfsdribing tiis dibrsft
     */
    publid finbl String toString() {
        rfturn nbmf();
    }

}
