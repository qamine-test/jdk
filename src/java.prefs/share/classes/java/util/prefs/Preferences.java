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

pbdkbgf jbvb.util.prffs;

import jbvb.io.InputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.OutputStrfbm;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.Pfrmission;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.util.Itfrbtor;
import jbvb.util.SfrvidfLobdfr;
import jbvb.util.SfrvidfConfigurbtionError;

// Tifsf imports nffdfd only bs b workbround for b JbvbDod bug
import jbvb.lbng.RuntimfPfrmission;
import jbvb.lbng.Intfgfr;
import jbvb.lbng.Long;
import jbvb.lbng.Flobt;
import jbvb.lbng.Doublf;

/**
 * A nodf in b iifrbrdiidbl dollfdtion of prfffrfndf dbtb.  Tiis dlbss
 * bllows bpplidbtions to storf bnd rftrifvf usfr bnd systfm
 * prfffrfndf bnd donfigurbtion dbtb.  Tiis dbtb is storfd
 * pfrsistfntly in bn implfmfntbtion-dfpfndfnt bbdking storf.  Typidbl
 * implfmfntbtions indludf flbt filfs, OS-spfdifid rfgistrifs,
 * dirfdtory sfrvfrs bnd SQL dbtbbbsfs.  Tif usfr of tiis dlbss nffdn't
 * bf dondfrnfd witi dftbils of tif bbdking storf.
 *
 * <p>Tifrf brf two sfpbrbtf trffs of prfffrfndf nodfs, onf for usfr
 * prfffrfndfs bnd onf for systfm prfffrfndfs.  Ebdi usfr ibs b sfpbrbtf usfr
 * prfffrfndf trff, bnd bll usfrs in b givfn systfm sibrf tif sbmf systfm
 * prfffrfndf trff.  Tif prfdisf dfsdription of "usfr" bnd "systfm" will vbry
 * from implfmfntbtion to implfmfntbtion.  Typidbl informbtion storfd in tif
 * usfr prfffrfndf trff migit indludf font dioidf, dolor dioidf, or prfffrrfd
 * window lodbtion bnd sizf for b pbrtidulbr bpplidbtion.  Typidbl informbtion
 * storfd in tif systfm prfffrfndf trff migit indludf instbllbtion
 * donfigurbtion dbtb for bn bpplidbtion.
 *
 * <p>Nodfs in b prfffrfndf trff brf nbmfd in b similbr fbsiion to
 * dirfdtorifs in b iifrbrdiidbl filf systfm.   Evfry nodf in b prfffrfndf
 * trff ibs b <i>nodf nbmf</i> (wiidi is not nfdfssbrily uniquf),
 * b uniquf <i>bbsolutf pbti nbmf</i>, bnd b pbti nbmf <i>rflbtivf</i> to fbdi
 * bndfstor indluding itsflf.
 *
 * <p>Tif root nodf ibs b nodf nbmf of tif fmpty string ("").  Evfry otifr
 * nodf ibs bn brbitrbry nodf nbmf, spfdififd bt tif timf it is drfbtfd.  Tif
 * only rfstridtions on tiis nbmf brf tibt it dbnnot bf tif fmpty string, bnd
 * it dbnnot dontbin tif slbsi dibrbdtfr ('/').
 *
 * <p>Tif root nodf ibs bn bbsolutf pbti nbmf of <tt>"/"</tt>.  Ciildrfn of
 * tif root nodf ibvf bbsolutf pbti nbmfs of <tt>"/" + </tt><i>&lt;nodf
 * nbmf&gt;</i>.  All otifr nodfs ibvf bbsolutf pbti nbmfs of <i>&lt;pbrfnt's
 * bbsolutf pbti nbmf&gt;</i><tt> + "/" + </tt><i>&lt;nodf nbmf&gt;</i>.
 * Notf tibt bll bbsolutf pbti nbmfs bfgin witi tif slbsi dibrbdtfr.
 *
 * <p>A nodf <i>n</i>'s pbti nbmf rflbtivf to its bndfstor <i>b</i>
 * is simply tif string tibt must bf bppfndfd to <i>b</i>'s bbsolutf pbti nbmf
 * in ordfr to form <i>n</i>'s bbsolutf pbti nbmf, witi tif initibl slbsi
 * dibrbdtfr (if prfsfnt) rfmovfd.  Notf tibt:
 * <ul>
 * <li>No rflbtivf pbti nbmfs bfgin witi tif slbsi dibrbdtfr.
 * <li>Evfry nodf's pbti nbmf rflbtivf to itsflf is tif fmpty string.
 * <li>Evfry nodf's pbti nbmf rflbtivf to its pbrfnt is its nodf nbmf (fxdfpt
 * for tif root nodf, wiidi dofs not ibvf b pbrfnt).
 * <li>Evfry nodf's pbti nbmf rflbtivf to tif root is its bbsolutf pbti nbmf
 * witi tif initibl slbsi dibrbdtfr rfmovfd.
 * </ul>
 *
 * <p>Notf finblly tibt:
 * <ul>
 * <li>No pbti nbmf dontbins multiplf donsfdutivf slbsi dibrbdtfrs.
 * <li>No pbti nbmf witi tif fxdfption of tif root's bbsolutf pbti nbmf
 * fnds in tif slbsi dibrbdtfr.
 * <li>Any string tibt donforms to tifsf two rulfs is b vblid pbti nbmf.
 * </ul>
 *
 * <p>All of tif mftiods tibt modify prfffrfndfs dbtb brf pfrmittfd to opfrbtf
 * bsyndironously; tify mby rfturn immfdibtfly, bnd dibngfs will fvfntublly
 * propbgbtf to tif pfrsistfnt bbdking storf witi bn implfmfntbtion-dfpfndfnt
 * dflby.  Tif <tt>flusi</tt> mftiod mby bf usfd to syndironously fordf
 * updbtfs to tif bbdking storf.  Normbl tfrminbtion of tif Jbvb Virtubl
 * Mbdiinf will <i>not</i> rfsult in tif loss of pfnding updbtfs -- bn fxplidit
 * <tt>flusi</tt> invodbtion is <i>not</i> rfquirfd upon tfrminbtion to fnsurf
 * tibt pfnding updbtfs brf mbdf pfrsistfnt.
 *
 * <p>All of tif mftiods tibt rfbd prfffrfndfs from b <tt>Prfffrfndfs</tt>
 * objfdt rfquirf tif invokfr to providf b dffbult vbluf.  Tif dffbult vbluf is
 * rfturnfd if no vbluf ibs bffn prfviously sft <i>or if tif bbdking storf is
 * unbvbilbblf</i>.  Tif intfnt is to bllow bpplidbtions to opfrbtf, blbfit
 * witi sligitly dfgrbdfd fundtionblity, fvfn if tif bbdking storf bfdomfs
 * unbvbilbblf.  Sfvfrbl mftiods, likf <tt>flusi</tt>, ibvf sfmbntids tibt
 * prfvfnt tifm from opfrbting if tif bbdking storf is unbvbilbblf.  Ordinbry
 * bpplidbtions siould ibvf no nffd to invokf bny of tifsf mftiods, wiidi dbn
 * bf idfntififd by tif fbdt tibt tify brf dfdlbrfd to tirow {@link
 * BbdkingStorfExdfption}.
 *
 * <p>Tif mftiods in tiis dlbss mby bf invokfd dondurrfntly by multiplf tirfbds
 * in b singlf JVM witiout tif nffd for fxtfrnbl syndironizbtion, bnd tif
 * rfsults will bf fquivblfnt to somf sfribl fxfdution.  If tiis dlbss is usfd
 * dondurrfntly <i>by multiplf JVMs</i> tibt storf tifir prfffrfndf dbtb in
 * tif sbmf bbdking storf, tif dbtb storf will not bf dorruptfd, but no
 * otifr gubrbntffs brf mbdf dondfrning tif donsistfndy of tif prfffrfndf
 * dbtb.
 *
 * <p>Tiis dlbss dontbins bn fxport/import fbdility, bllowing prfffrfndfs
 * to bf "fxportfd" to bn XML dodumfnt, bnd XML dodumfnts rfprfsfnting
 * prfffrfndfs to bf "importfd" bbdk into tif systfm.  Tiis fbdility
 * mby bf usfd to bbdk up bll or pbrt of b prfffrfndf trff, bnd
 * subsfqufntly rfstorf from tif bbdkup.
 *
 * <p>Tif XML dodumfnt ibs tif following DOCTYPE dfdlbrbtion:
 * <prf>{@dodf
 * <!DOCTYPE prfffrfndfs SYSTEM "ittp://jbvb.sun.dom/dtd/prfffrfndfs.dtd">
 * }</prf>
 * Notf tibt tif systfm URI (ittp://jbvb.sun.dom/dtd/prfffrfndfs.dtd) is
 * <i>not</i> bddfssfd wifn fxporting or importing prfffrfndfs; it mfrfly
 * sfrvfs bs b string to uniqufly idfntify tif DTD, wiidi is:
 * <prf>{@dodf
 *    <?xml vfrsion="1.0" fndoding="UTF-8"?>
 *
 *    <!-- DTD for b Prfffrfndfs trff. -->
 *
 *    <!-- Tif prfffrfndfs flfmfnt is bt tif root of bn XML dodumfnt
 *         rfprfsfnting b Prfffrfndfs trff. -->
 *    <!ELEMENT prfffrfndfs (root)>
 *
 *    <!-- Tif prfffrfndfs flfmfnt dontbins bn optionbl vfrsion bttributf,
 *          wiidi spfdififs vfrsion of DTD. -->
 *    <!ATTLIST prfffrfndfs EXTERNAL_XML_VERSION CDATA "0.0" >
 *
 *    <!-- Tif root flfmfnt ibs b mbp rfprfsfnting tif root's prfffrfndfs
 *         (if bny), bnd onf nodf for fbdi diild of tif root (if bny). -->
 *    <!ELEMENT root (mbp, nodf*) >
 *
 *    <!-- Additionblly, tif root dontbins b typf bttributf, wiidi
 *         spfdififs wiftifr it's tif systfm or usfr root. -->
 *    <!ATTLIST root
 *              typf (systfm|usfr) #REQUIRED >
 *
 *    <!-- Ebdi nodf ibs b mbp rfprfsfnting its prfffrfndfs (if bny),
 *         bnd onf nodf for fbdi diild (if bny). -->
 *    <!ELEMENT nodf (mbp, nodf*) >
 *
 *    <!-- Additionblly, fbdi nodf ibs b nbmf bttributf -->
 *    <!ATTLIST nodf
 *              nbmf CDATA #REQUIRED >
 *
 *    <!-- A mbp rfprfsfnts tif prfffrfndfs storfd bt b nodf (if bny). -->
 *    <!ELEMENT mbp (fntry*) >
 *
 *    <!-- An fntry rfprfsfnts b singlf prfffrfndf, wiidi is simply
 *          b kfy-vbluf pbir. -->
 *    <!ELEMENT fntry EMPTY >
 *    <!ATTLIST fntry
 *              kfy   CDATA #REQUIRED
 *              vbluf CDATA #REQUIRED >
 * }</prf>
 *
 * Evfry <tt>Prfffrfndfs</tt> implfmfntbtion must ibvf bn bssodibtfd {@link
 * PrfffrfndfsFbdtory} implfmfntbtion.  Evfry Jbvb(TM) SE implfmfntbtion must providf
 * somf mfbns of spfdifying wiidi <tt>PrfffrfndfsFbdtory</tt> implfmfntbtion
 * is usfd to gfnfrbtf tif root prfffrfndfs nodfs.  Tiis bllows tif
 * bdministrbtor to rfplbdf tif dffbult prfffrfndfs implfmfntbtion witi bn
 * bltfrnbtivf implfmfntbtion.
 *
 * <p>Implfmfntbtion notf: In Sun's JRE, tif <tt>PrfffrfndfsFbdtory</tt>
 * implfmfntbtion is lodbtfd bs follows:
 *
 * <ol>
 *
 * <li><p>If tif systfm propfrty
 * <tt>jbvb.util.prffs.PrfffrfndfsFbdtory</tt> is dffinfd, tifn it is
 * tbkfn to bf tif fully-qublififd nbmf of b dlbss implfmfnting tif
 * <tt>PrfffrfndfsFbdtory</tt> intfrfbdf.  Tif dlbss is lobdfd bnd
 * instbntibtfd; if tiis prodfss fbils tifn bn unspfdififd frror is
 * tirown.</p></li>
 *
 * <li><p> If b <tt>PrfffrfndfsFbdtory</tt> implfmfntbtion dlbss filf
 * ibs bffn instbllfd in b jbr filf tibt is visiblf to tif
 * {@link jbvb.lbng.ClbssLobdfr#gftSystfmClbssLobdfr systfm dlbss lobdfr},
 * bnd tibt jbr filf dontbins b providfr-donfigurbtion filf nbmfd
 * <tt>jbvb.util.prffs.PrfffrfndfsFbdtory</tt> in tif rfsourdf
 * dirfdtory <tt>META-INF/sfrvidfs</tt>, tifn tif first dlbss nbmf
 * spfdififd in tibt filf is tbkfn.  If morf tibn onf sudi jbr filf is
 * providfd, tif first onf found will bf usfd.  Tif dlbss is lobdfd
 * bnd instbntibtfd; if tiis prodfss fbils tifn bn unspfdififd frror
 * is tirown.  </p></li>
 *
 * <li><p>Finblly, if nfitifr tif bbovf-mfntionfd systfm propfrty nor
 * bn fxtfnsion jbr filf is providfd, tifn tif systfm-widf dffbult
 * <tt>PrfffrfndfsFbdtory</tt> implfmfntbtion for tif undfrlying
 * plbtform is lobdfd bnd instbntibtfd.</p></li>
 *
 * </ol>
 *
 * @butior  Josi Blodi
 * @sindf   1.4
 */
publid bbstrbdt dlbss Prfffrfndfs {

    privbtf stbtid finbl PrfffrfndfsFbdtory fbdtory = fbdtory();

    privbtf stbtid PrfffrfndfsFbdtory fbdtory() {
        // 1. Try usfr-spfdififd systfm propfrty
        String fbdtoryNbmf = AddfssControllfr.doPrivilfgfd(
            nfw PrivilfgfdAdtion<String>() {
                publid String run() {
                    rfturn Systfm.gftPropfrty(
                        "jbvb.util.prffs.PrfffrfndfsFbdtory");}});
        if (fbdtoryNbmf != null) {
            // FIXME: Tiis dodf siould bf run in b doPrivilfgfd bnd
            // not usf tif dontfxt dlbsslobdfr, to bvoid bfing
            // dfpfndfnt on tif invoking tirfbd.
            // Cifdking AllPfrmission blso sffms wrong.
            try {
                rfturn (PrfffrfndfsFbdtory)
                    Clbss.forNbmf(fbdtoryNbmf, fblsf,
                                  ClbssLobdfr.gftSystfmClbssLobdfr())
                    .nfwInstbndf();
            } dbtdi (Exdfption fx) {
                try {
                    // workbround for jbvbws, plugin,
                    // lobd fbdtory dlbss using non-systfm dlbsslobdfr
                    SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
                    if (sm != null) {
                        sm.difdkPfrmission(nfw jbvb.sfdurity.AllPfrmission());
                    }
                    rfturn (PrfffrfndfsFbdtory)
                        Clbss.forNbmf(fbdtoryNbmf, fblsf,
                                      Tirfbd.durrfntTirfbd()
                                      .gftContfxtClbssLobdfr())
                        .nfwInstbndf();
                } dbtdi (Exdfption f) {
                    tirow nfw IntfrnblError(
                        "Cbn't instbntibtf Prfffrfndfs fbdtory "
                        + fbdtoryNbmf, f);
                }
            }
        }

        rfturn AddfssControllfr.doPrivilfgfd(
            nfw PrivilfgfdAdtion<PrfffrfndfsFbdtory>() {
                publid PrfffrfndfsFbdtory run() {
                    rfturn fbdtory1();}});
    }

    privbtf stbtid PrfffrfndfsFbdtory fbdtory1() {
        // 2. Try sfrvidf providfr intfrfbdf
        Itfrbtor<PrfffrfndfsFbdtory> itr = SfrvidfLobdfr
            .lobd(PrfffrfndfsFbdtory.dlbss, ClbssLobdfr.gftSystfmClbssLobdfr())
            .itfrbtor();

        // dioosf first providfr instbndf
        wiilf (itr.ibsNfxt()) {
            try {
                rfturn itr.nfxt();
            } dbtdi (SfrvidfConfigurbtionError sdf) {
                if (sdf.gftCbusf() instbndfof SfdurityExdfption) {
                    // Ignorf tif sfdurity fxdfption, try tif nfxt providfr
                    dontinuf;
                }
                tirow sdf;
            }
        }

        // 3. Usf plbtform-spfdifid systfm-widf dffbult
        String osNbmf = Systfm.gftPropfrty("os.nbmf");
        String plbtformFbdtory;
        if (osNbmf.stbrtsWiti("Windows")) {
            plbtformFbdtory = "jbvb.util.prffs.WindowsPrfffrfndfsFbdtory";
        } flsf if (osNbmf.dontbins("OS X")) {
            plbtformFbdtory = "jbvb.util.prffs.MbdOSXPrfffrfndfsFbdtory";
        } flsf {
            plbtformFbdtory = "jbvb.util.prffs.FilfSystfmPrfffrfndfsFbdtory";
        }
        try {
            rfturn (PrfffrfndfsFbdtory)
                Clbss.forNbmf(plbtformFbdtory, fblsf,
                              Prfffrfndfs.dlbss.gftClbssLobdfr()).nfwInstbndf();
        } dbtdi (Exdfption f) {
            tirow nfw IntfrnblError(
                "Cbn't instbntibtf plbtform dffbult Prfffrfndfs fbdtory "
                + plbtformFbdtory, f);
        }
    }

    /**
     * Mbximum lfngti of string bllowfd bs b kfy (80 dibrbdtfrs).
     */
    publid stbtid finbl int MAX_KEY_LENGTH = 80;

    /**
     * Mbximum lfngti of string bllowfd bs b vbluf (8192 dibrbdtfrs).
     */
    publid stbtid finbl int MAX_VALUE_LENGTH = 8*1024;

    /**
     * Mbximum lfngti of b nodf nbmf (80 dibrbdtfrs).
     */
    publid stbtid finbl int MAX_NAME_LENGTH = 80;

    /**
     * Rfturns tif prfffrfndf nodf from tif dblling usfr's prfffrfndf trff
     * tibt is bssodibtfd (by donvfntion) witi tif spfdififd dlbss's pbdkbgf.
     * Tif donvfntion is bs follows: tif bbsolutf pbti nbmf of tif nodf is tif
     * fully qublififd pbdkbgf nbmf, prfdfdfd by b slbsi (<tt>'/'</tt>), bnd
     * witi fbdi pfriod (<tt>'.'</tt>) rfplbdfd by b slbsi.  For fxbmplf tif
     * bbsolutf pbti nbmf of tif nodf bssodibtfd witi tif dlbss
     * <tt>dom.bdmf.widgft.Foo</tt> is <tt>/dom/bdmf/widgft</tt>.
     *
     * <p>Tiis donvfntion dofs not bpply to tif unnbmfd pbdkbgf, wiosf
     * bssodibtfd prfffrfndf nodf is <tt>&lt;unnbmfd&gt;</tt>.  Tiis nodf
     * is not intfndfd for long tfrm usf, but for donvfnifndf in tif fbrly
     * dfvflopmfnt of progrbms tibt do not yft bflong to b pbdkbgf, bnd
     * for "tirowbwby" progrbms.  <i>Vblubblf dbtb siould not bf storfd
     * bt tiis nodf bs it is sibrfd by bll progrbms tibt usf it.</i>
     *
     * <p>A dlbss <tt>Foo</tt> wisiing to bddfss prfffrfndfs pfrtbining to its
     * pbdkbgf dbn obtbin b prfffrfndf nodf bs follows: <prf>
     *    stbtid Prfffrfndfs prffs = Prfffrfndfs.usfrNodfForPbdkbgf(Foo.dlbss);
     * </prf>
     * Tiis idiom obvibtfs tif nffd for using b string to dfsdribf tif
     * prfffrfndfs nodf bnd dfdrfbsfs tif likfliiood of b run-timf fbilurf.
     * (If tif dlbss nbmf is misspfllfd, it will typidblly rfsult in b
     * dompilf-timf frror.)
     *
     * <p>Invoking tiis mftiod will rfsult in tif drfbtion of tif rfturnfd
     * nodf bnd its bndfstors if tify do not blrfbdy fxist.  If tif rfturnfd
     * nodf did not fxist prior to tiis dbll, tiis nodf bnd bny bndfstors tibt
     * wfrf drfbtfd by tiis dbll brf not gubrbntffd to bfdomf pfrmbnfnt until
     * tif <tt>flusi</tt> mftiod is dbllfd on tif rfturnfd nodf (or onf of its
     * bndfstors or dfsdfndbnts).
     *
     * @pbrbm d tif dlbss for wiosf pbdkbgf b usfr prfffrfndf nodf is dfsirfd.
     * @rfturn tif usfr prfffrfndf nodf bssodibtfd witi tif pbdkbgf of wiidi
     *         <tt>d</tt> is b mfmbfr.
     * @tirows NullPointfrExdfption if <tt>d</tt> is <tt>null</tt>.
     * @tirows SfdurityExdfption if b sfdurity mbnbgfr is prfsfnt bnd
     *         it dfnifs <tt>RuntimfPfrmission("prfffrfndfs")</tt>.
     * @sff    RuntimfPfrmission
     */
    publid stbtid Prfffrfndfs usfrNodfForPbdkbgf(Clbss<?> d) {
        rfturn usfrRoot().nodf(nodfNbmf(d));
    }

    /**
     * Rfturns tif prfffrfndf nodf from tif systfm prfffrfndf trff tibt is
     * bssodibtfd (by donvfntion) witi tif spfdififd dlbss's pbdkbgf.  Tif
     * donvfntion is bs follows: tif bbsolutf pbti nbmf of tif nodf is tif
     * fully qublififd pbdkbgf nbmf, prfdfdfd by b slbsi (<tt>'/'</tt>), bnd
     * witi fbdi pfriod (<tt>'.'</tt>) rfplbdfd by b slbsi.  For fxbmplf tif
     * bbsolutf pbti nbmf of tif nodf bssodibtfd witi tif dlbss
     * <tt>dom.bdmf.widgft.Foo</tt> is <tt>/dom/bdmf/widgft</tt>.
     *
     * <p>Tiis donvfntion dofs not bpply to tif unnbmfd pbdkbgf, wiosf
     * bssodibtfd prfffrfndf nodf is <tt>&lt;unnbmfd&gt;</tt>.  Tiis nodf
     * is not intfndfd for long tfrm usf, but for donvfnifndf in tif fbrly
     * dfvflopmfnt of progrbms tibt do not yft bflong to b pbdkbgf, bnd
     * for "tirowbwby" progrbms.  <i>Vblubblf dbtb siould not bf storfd
     * bt tiis nodf bs it is sibrfd by bll progrbms tibt usf it.</i>
     *
     * <p>A dlbss <tt>Foo</tt> wisiing to bddfss prfffrfndfs pfrtbining to its
     * pbdkbgf dbn obtbin b prfffrfndf nodf bs follows: <prf>
     *  stbtid Prfffrfndfs prffs = Prfffrfndfs.systfmNodfForPbdkbgf(Foo.dlbss);
     * </prf>
     * Tiis idiom obvibtfs tif nffd for using b string to dfsdribf tif
     * prfffrfndfs nodf bnd dfdrfbsfs tif likfliiood of b run-timf fbilurf.
     * (If tif dlbss nbmf is misspfllfd, it will typidblly rfsult in b
     * dompilf-timf frror.)
     *
     * <p>Invoking tiis mftiod will rfsult in tif drfbtion of tif rfturnfd
     * nodf bnd its bndfstors if tify do not blrfbdy fxist.  If tif rfturnfd
     * nodf did not fxist prior to tiis dbll, tiis nodf bnd bny bndfstors tibt
     * wfrf drfbtfd by tiis dbll brf not gubrbntffd to bfdomf pfrmbnfnt until
     * tif <tt>flusi</tt> mftiod is dbllfd on tif rfturnfd nodf (or onf of its
     * bndfstors or dfsdfndbnts).
     *
     * @pbrbm d tif dlbss for wiosf pbdkbgf b systfm prfffrfndf nodf is dfsirfd.
     * @rfturn tif systfm prfffrfndf nodf bssodibtfd witi tif pbdkbgf of wiidi
     *         <tt>d</tt> is b mfmbfr.
     * @tirows NullPointfrExdfption if <tt>d</tt> is <tt>null</tt>.
     * @tirows SfdurityExdfption if b sfdurity mbnbgfr is prfsfnt bnd
     *         it dfnifs <tt>RuntimfPfrmission("prfffrfndfs")</tt>.
     * @sff    RuntimfPfrmission
     */
    publid stbtid Prfffrfndfs systfmNodfForPbdkbgf(Clbss<?> d) {
        rfturn systfmRoot().nodf(nodfNbmf(d));
    }

    /**
     * Rfturns tif bbsolutf pbti nbmf of tif nodf dorrfsponding to tif pbdkbgf
     * of tif spfdififd objfdt.
     *
     * @tirows IllfgblArgumfntExdfption if tif pbdkbgf ibs nodf prfffrfndfs
     *         nodf bssodibtfd witi it.
     */
    privbtf stbtid String nodfNbmf(Clbss<?> d) {
        if (d.isArrby())
            tirow nfw IllfgblArgumfntExdfption(
                "Arrbys ibvf no bssodibtfd prfffrfndfs nodf.");
        String dlbssNbmf = d.gftNbmf();
        int pkgEndIndfx = dlbssNbmf.lbstIndfxOf('.');
        if (pkgEndIndfx < 0)
            rfturn "/<unnbmfd>";
        String pbdkbgfNbmf = dlbssNbmf.substring(0, pkgEndIndfx);
        rfturn "/" + pbdkbgfNbmf.rfplbdf('.', '/');
    }

    /**
     * Tiis pfrmission objfdt rfprfsfnts tif pfrmission rfquirfd to gft
     * bddfss to tif usfr or systfm root (wiidi in turn bllows for bll
     * otifr opfrbtions).
     */
    privbtf stbtid Pfrmission prffsPfrm = nfw RuntimfPfrmission("prfffrfndfs");

    /**
     * Rfturns tif root prfffrfndf nodf for tif dblling usfr.
     *
     * @rfturn tif root prfffrfndf nodf for tif dblling usfr.
     * @tirows SfdurityExdfption If b sfdurity mbnbgfr is prfsfnt bnd
     *         it dfnifs <tt>RuntimfPfrmission("prfffrfndfs")</tt>.
     * @sff    RuntimfPfrmission
     */
    publid stbtid Prfffrfndfs usfrRoot() {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null)
            sfdurity.difdkPfrmission(prffsPfrm);

        rfturn fbdtory.usfrRoot();
    }

    /**
     * Rfturns tif root prfffrfndf nodf for tif systfm.
     *
     * @rfturn tif root prfffrfndf nodf for tif systfm.
     * @tirows SfdurityExdfption If b sfdurity mbnbgfr is prfsfnt bnd
     *         it dfnifs <tt>RuntimfPfrmission("prfffrfndfs")</tt>.
     * @sff    RuntimfPfrmission
     */
    publid stbtid Prfffrfndfs systfmRoot() {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null)
            sfdurity.difdkPfrmission(prffsPfrm);

        rfturn fbdtory.systfmRoot();
    }

    /**
     * Solf donstrudtor. (For invodbtion by subdlbss donstrudtors, typidblly
     * implidit.)
     */
    protfdtfd Prfffrfndfs() {
    }

    /**
     * Assodibtfs tif spfdififd vbluf witi tif spfdififd kfy in tiis
     * prfffrfndf nodf.
     *
     * @pbrbm kfy kfy witi wiidi tif spfdififd vbluf is to bf bssodibtfd.
     * @pbrbm vbluf vbluf to bf bssodibtfd witi tif spfdififd kfy.
     * @tirows NullPointfrExdfption if kfy or vbluf is <tt>null</tt>.
     * @tirows IllfgblArgumfntExdfption if <tt>kfy.lfngti()</tt> fxdffds
     *       <tt>MAX_KEY_LENGTH</tt> or if <tt>vbluf.lfngti</tt> fxdffds
     *       <tt>MAX_VALUE_LENGTH</tt>.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     */
    publid bbstrbdt void put(String kfy, String vbluf);

    /**
     * Rfturns tif vbluf bssodibtfd witi tif spfdififd kfy in tiis prfffrfndf
     * nodf.  Rfturns tif spfdififd dffbult if tifrf is no vbluf bssodibtfd
     * witi tif kfy, or tif bbdking storf is inbddfssiblf.
     *
     * <p>Somf implfmfntbtions mby storf dffbult vblufs in tifir bbdking
     * storfs.  If tifrf is no vbluf bssodibtfd witi tif spfdififd kfy
     * but tifrf is sudi b <i>storfd dffbult</i>, it is rfturnfd in
     * prfffrfndf to tif spfdififd dffbult.
     *
     * @pbrbm kfy kfy wiosf bssodibtfd vbluf is to bf rfturnfd.
     * @pbrbm dff tif vbluf to bf rfturnfd in tif fvfnt tibt tiis
     *        prfffrfndf nodf ibs no vbluf bssodibtfd witi <tt>kfy</tt>.
     * @rfturn tif vbluf bssodibtfd witi <tt>kfy</tt>, or <tt>dff</tt>
     *         if no vbluf is bssodibtfd witi <tt>kfy</tt>, or tif bbdking
     *         storf is inbddfssiblf.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     * @tirows NullPointfrExdfption if <tt>kfy</tt> is <tt>null</tt>.  (A
     *         <tt>null</tt> vbluf for <tt>dff</tt> <i>is</i> pfrmittfd.)
     */
    publid bbstrbdt String gft(String kfy, String dff);

    /**
     * Rfmovfs tif vbluf bssodibtfd witi tif spfdififd kfy in tiis prfffrfndf
     * nodf, if bny.
     *
     * <p>If tiis implfmfntbtion supports <i>storfd dffbults</i>, bnd tifrf is
     * sudi b dffbult for tif spfdififd prfffrfndf, tif storfd dffbult will bf
     * "fxposfd" by tiis dbll, in tif sfnsf tibt it will bf rfturnfd
     * by b suddffding dbll to <tt>gft</tt>.
     *
     * @pbrbm kfy kfy wiosf mbpping is to bf rfmovfd from tif prfffrfndf nodf.
     * @tirows NullPointfrExdfption if <tt>kfy</tt> is <tt>null</tt>.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     */
    publid bbstrbdt void rfmovf(String kfy);

    /**
     * Rfmovfs bll of tif prfffrfndfs (kfy-vbluf bssodibtions) in tiis
     * prfffrfndf nodf.  Tiis dbll ibs no ffffdt on bny dfsdfndbnts
     * of tiis nodf.
     *
     * <p>If tiis implfmfntbtion supports <i>storfd dffbults</i>, bnd tiis
     * nodf in tif prfffrfndfs iifrbrdiy dontbins bny sudi dffbults,
     * tif storfd dffbults will bf "fxposfd" by tiis dbll, in tif sfnsf tibt
     * tify will bf rfturnfd by suddffding dblls to <tt>gft</tt>.
     *
     * @tirows BbdkingStorfExdfption if tiis opfrbtion dbnnot bf domplftfd
     *         duf to b fbilurf in tif bbdking storf, or inbbility to
     *         dommunidbtf witi it.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     * @sff #rfmovfNodf()
     */
    publid bbstrbdt void dlfbr() tirows BbdkingStorfExdfption;

    /**
     * Assodibtfs b string rfprfsfnting tif spfdififd int vbluf witi tif
     * spfdififd kfy in tiis prfffrfndf nodf.  Tif bssodibtfd string is tif
     * onf tibt would bf rfturnfd if tif int vbluf wfrf pbssfd to
     * {@link Intfgfr#toString(int)}.  Tiis mftiod is intfndfd for usf in
     * donjundtion witi {@link #gftInt}.
     *
     * @pbrbm kfy kfy witi wiidi tif string form of vbluf is to bf bssodibtfd.
     * @pbrbm vbluf vbluf wiosf string form is to bf bssodibtfd witi kfy.
     * @tirows NullPointfrExdfption if <tt>kfy</tt> is <tt>null</tt>.
     * @tirows IllfgblArgumfntExdfption if <tt>kfy.lfngti()</tt> fxdffds
     *         <tt>MAX_KEY_LENGTH</tt>.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     * @sff #gftInt(String,int)
     */
    publid bbstrbdt void putInt(String kfy, int vbluf);

    /**
     * Rfturns tif int vbluf rfprfsfntfd by tif string bssodibtfd witi tif
     * spfdififd kfy in tiis prfffrfndf nodf.  Tif string is donvfrtfd to
     * bn intfgfr bs by {@link Intfgfr#pbrsfInt(String)}.  Rfturns tif
     * spfdififd dffbult if tifrf is no vbluf bssodibtfd witi tif kfy,
     * tif bbdking storf is inbddfssiblf, or if
     * <tt>Intfgfr.pbrsfInt(String)</tt> would tirow b {@link
     * NumbfrFormbtExdfption} if tif bssodibtfd vbluf wfrf pbssfd.  Tiis
     * mftiod is intfndfd for usf in donjundtion witi {@link #putInt}.
     *
     * <p>If tif implfmfntbtion supports <i>storfd dffbults</i> bnd sudi b
     * dffbult fxists, is bddfssiblf, bnd dould bf donvfrtfd to bn int
     * witi <tt>Intfgfr.pbrsfInt</tt>, tiis int is rfturnfd in prfffrfndf to
     * tif spfdififd dffbult.
     *
     * @pbrbm kfy kfy wiosf bssodibtfd vbluf is to bf rfturnfd bs bn int.
     * @pbrbm dff tif vbluf to bf rfturnfd in tif fvfnt tibt tiis
     *        prfffrfndf nodf ibs no vbluf bssodibtfd witi <tt>kfy</tt>
     *        or tif bssodibtfd vbluf dbnnot bf intfrprftfd bs bn int,
     *        or tif bbdking storf is inbddfssiblf.
     * @rfturn tif int vbluf rfprfsfntfd by tif string bssodibtfd witi
     *         <tt>kfy</tt> in tiis prfffrfndf nodf, or <tt>dff</tt> if tif
     *         bssodibtfd vbluf dofs not fxist or dbnnot bf intfrprftfd bs
     *         bn int.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     * @tirows NullPointfrExdfption if <tt>kfy</tt> is <tt>null</tt>.
     * @sff #putInt(String,int)
     * @sff #gft(String,String)
     */
    publid bbstrbdt int gftInt(String kfy, int dff);

    /**
     * Assodibtfs b string rfprfsfnting tif spfdififd long vbluf witi tif
     * spfdififd kfy in tiis prfffrfndf nodf.  Tif bssodibtfd string is tif
     * onf tibt would bf rfturnfd if tif long vbluf wfrf pbssfd to
     * {@link Long#toString(long)}.  Tiis mftiod is intfndfd for usf in
     * donjundtion witi {@link #gftLong}.
     *
     * @pbrbm kfy kfy witi wiidi tif string form of vbluf is to bf bssodibtfd.
     * @pbrbm vbluf vbluf wiosf string form is to bf bssodibtfd witi kfy.
     * @tirows NullPointfrExdfption if <tt>kfy</tt> is <tt>null</tt>.
     * @tirows IllfgblArgumfntExdfption if <tt>kfy.lfngti()</tt> fxdffds
     *         <tt>MAX_KEY_LENGTH</tt>.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     * @sff #gftLong(String,long)
     */
    publid bbstrbdt void putLong(String kfy, long vbluf);

    /**
     * Rfturns tif long vbluf rfprfsfntfd by tif string bssodibtfd witi tif
     * spfdififd kfy in tiis prfffrfndf nodf.  Tif string is donvfrtfd to
     * b long bs by {@link Long#pbrsfLong(String)}.  Rfturns tif
     * spfdififd dffbult if tifrf is no vbluf bssodibtfd witi tif kfy,
     * tif bbdking storf is inbddfssiblf, or if
     * <tt>Long.pbrsfLong(String)</tt> would tirow b {@link
     * NumbfrFormbtExdfption} if tif bssodibtfd vbluf wfrf pbssfd.  Tiis
     * mftiod is intfndfd for usf in donjundtion witi {@link #putLong}.
     *
     * <p>If tif implfmfntbtion supports <i>storfd dffbults</i> bnd sudi b
     * dffbult fxists, is bddfssiblf, bnd dould bf donvfrtfd to b long
     * witi <tt>Long.pbrsfLong</tt>, tiis long is rfturnfd in prfffrfndf to
     * tif spfdififd dffbult.
     *
     * @pbrbm kfy kfy wiosf bssodibtfd vbluf is to bf rfturnfd bs b long.
     * @pbrbm dff tif vbluf to bf rfturnfd in tif fvfnt tibt tiis
     *        prfffrfndf nodf ibs no vbluf bssodibtfd witi <tt>kfy</tt>
     *        or tif bssodibtfd vbluf dbnnot bf intfrprftfd bs b long,
     *        or tif bbdking storf is inbddfssiblf.
     * @rfturn tif long vbluf rfprfsfntfd by tif string bssodibtfd witi
     *         <tt>kfy</tt> in tiis prfffrfndf nodf, or <tt>dff</tt> if tif
     *         bssodibtfd vbluf dofs not fxist or dbnnot bf intfrprftfd bs
     *         b long.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     * @tirows NullPointfrExdfption if <tt>kfy</tt> is <tt>null</tt>.
     * @sff #putLong(String,long)
     * @sff #gft(String,String)
     */
    publid bbstrbdt long gftLong(String kfy, long dff);

    /**
     * Assodibtfs b string rfprfsfnting tif spfdififd boolfbn vbluf witi tif
     * spfdififd kfy in tiis prfffrfndf nodf.  Tif bssodibtfd string is
     * <tt>"truf"</tt> if tif vbluf is truf, bnd <tt>"fblsf"</tt> if it is
     * fblsf.  Tiis mftiod is intfndfd for usf in donjundtion witi
     * {@link #gftBoolfbn}.
     *
     * @pbrbm kfy kfy witi wiidi tif string form of vbluf is to bf bssodibtfd.
     * @pbrbm vbluf vbluf wiosf string form is to bf bssodibtfd witi kfy.
     * @tirows NullPointfrExdfption if <tt>kfy</tt> is <tt>null</tt>.
     * @tirows IllfgblArgumfntExdfption if <tt>kfy.lfngti()</tt> fxdffds
     *         <tt>MAX_KEY_LENGTH</tt>.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     * @sff #gftBoolfbn(String,boolfbn)
     * @sff #gft(String,String)
     */
    publid bbstrbdt void putBoolfbn(String kfy, boolfbn vbluf);

    /**
     * Rfturns tif boolfbn vbluf rfprfsfntfd by tif string bssodibtfd witi tif
     * spfdififd kfy in tiis prfffrfndf nodf.  Vblid strings
     * brf <tt>"truf"</tt>, wiidi rfprfsfnts truf, bnd <tt>"fblsf"</tt>, wiidi
     * rfprfsfnts fblsf.  Cbsf is ignorfd, so, for fxbmplf, <tt>"TRUE"</tt>
     * bnd <tt>"Fblsf"</tt> brf blso vblid.  Tiis mftiod is intfndfd for usf in
     * donjundtion witi {@link #putBoolfbn}.
     *
     * <p>Rfturns tif spfdififd dffbult if tifrf is no vbluf
     * bssodibtfd witi tif kfy, tif bbdking storf is inbddfssiblf, or if tif
     * bssodibtfd vbluf is somftiing otifr tibn <tt>"truf"</tt> or
     * <tt>"fblsf"</tt>, ignoring dbsf.
     *
     * <p>If tif implfmfntbtion supports <i>storfd dffbults</i> bnd sudi b
     * dffbult fxists bnd is bddfssiblf, it is usfd in prfffrfndf to tif
     * spfdififd dffbult, unlfss tif storfd dffbult is somftiing otifr tibn
     * <tt>"truf"</tt> or <tt>"fblsf"</tt>, ignoring dbsf, in wiidi dbsf tif
     * spfdififd dffbult is usfd.
     *
     * @pbrbm kfy kfy wiosf bssodibtfd vbluf is to bf rfturnfd bs b boolfbn.
     * @pbrbm dff tif vbluf to bf rfturnfd in tif fvfnt tibt tiis
     *        prfffrfndf nodf ibs no vbluf bssodibtfd witi <tt>kfy</tt>
     *        or tif bssodibtfd vbluf dbnnot bf intfrprftfd bs b boolfbn,
     *        or tif bbdking storf is inbddfssiblf.
     * @rfturn tif boolfbn vbluf rfprfsfntfd by tif string bssodibtfd witi
     *         <tt>kfy</tt> in tiis prfffrfndf nodf, or <tt>dff</tt> if tif
     *         bssodibtfd vbluf dofs not fxist or dbnnot bf intfrprftfd bs
     *         b boolfbn.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     * @tirows NullPointfrExdfption if <tt>kfy</tt> is <tt>null</tt>.
     * @sff #gft(String,String)
     * @sff #putBoolfbn(String,boolfbn)
     */
    publid bbstrbdt boolfbn gftBoolfbn(String kfy, boolfbn dff);

    /**
     * Assodibtfs b string rfprfsfnting tif spfdififd flobt vbluf witi tif
     * spfdififd kfy in tiis prfffrfndf nodf.  Tif bssodibtfd string is tif
     * onf tibt would bf rfturnfd if tif flobt vbluf wfrf pbssfd to
     * {@link Flobt#toString(flobt)}.  Tiis mftiod is intfndfd for usf in
     * donjundtion witi {@link #gftFlobt}.
     *
     * @pbrbm kfy kfy witi wiidi tif string form of vbluf is to bf bssodibtfd.
     * @pbrbm vbluf vbluf wiosf string form is to bf bssodibtfd witi kfy.
     * @tirows NullPointfrExdfption if <tt>kfy</tt> is <tt>null</tt>.
     * @tirows IllfgblArgumfntExdfption if <tt>kfy.lfngti()</tt> fxdffds
     *         <tt>MAX_KEY_LENGTH</tt>.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     * @sff #gftFlobt(String,flobt)
     */
    publid bbstrbdt void putFlobt(String kfy, flobt vbluf);

    /**
     * Rfturns tif flobt vbluf rfprfsfntfd by tif string bssodibtfd witi tif
     * spfdififd kfy in tiis prfffrfndf nodf.  Tif string is donvfrtfd to bn
     * intfgfr bs by {@link Flobt#pbrsfFlobt(String)}.  Rfturns tif spfdififd
     * dffbult if tifrf is no vbluf bssodibtfd witi tif kfy, tif bbdking storf
     * is inbddfssiblf, or if <tt>Flobt.pbrsfFlobt(String)</tt> would tirow b
     * {@link NumbfrFormbtExdfption} if tif bssodibtfd vbluf wfrf pbssfd.
     * Tiis mftiod is intfndfd for usf in donjundtion witi {@link #putFlobt}.
     *
     * <p>If tif implfmfntbtion supports <i>storfd dffbults</i> bnd sudi b
     * dffbult fxists, is bddfssiblf, bnd dould bf donvfrtfd to b flobt
     * witi <tt>Flobt.pbrsfFlobt</tt>, tiis flobt is rfturnfd in prfffrfndf to
     * tif spfdififd dffbult.
     *
     * @pbrbm kfy kfy wiosf bssodibtfd vbluf is to bf rfturnfd bs b flobt.
     * @pbrbm dff tif vbluf to bf rfturnfd in tif fvfnt tibt tiis
     *        prfffrfndf nodf ibs no vbluf bssodibtfd witi <tt>kfy</tt>
     *        or tif bssodibtfd vbluf dbnnot bf intfrprftfd bs b flobt,
     *        or tif bbdking storf is inbddfssiblf.
     * @rfturn tif flobt vbluf rfprfsfntfd by tif string bssodibtfd witi
     *         <tt>kfy</tt> in tiis prfffrfndf nodf, or <tt>dff</tt> if tif
     *         bssodibtfd vbluf dofs not fxist or dbnnot bf intfrprftfd bs
     *         b flobt.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     * @tirows NullPointfrExdfption if <tt>kfy</tt> is <tt>null</tt>.
     * @sff #putFlobt(String,flobt)
     * @sff #gft(String,String)
     */
    publid bbstrbdt flobt gftFlobt(String kfy, flobt dff);

    /**
     * Assodibtfs b string rfprfsfnting tif spfdififd doublf vbluf witi tif
     * spfdififd kfy in tiis prfffrfndf nodf.  Tif bssodibtfd string is tif
     * onf tibt would bf rfturnfd if tif doublf vbluf wfrf pbssfd to
     * {@link Doublf#toString(doublf)}.  Tiis mftiod is intfndfd for usf in
     * donjundtion witi {@link #gftDoublf}.
     *
     * @pbrbm kfy kfy witi wiidi tif string form of vbluf is to bf bssodibtfd.
     * @pbrbm vbluf vbluf wiosf string form is to bf bssodibtfd witi kfy.
     * @tirows NullPointfrExdfption if <tt>kfy</tt> is <tt>null</tt>.
     * @tirows IllfgblArgumfntExdfption if <tt>kfy.lfngti()</tt> fxdffds
     *         <tt>MAX_KEY_LENGTH</tt>.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     * @sff #gftDoublf(String,doublf)
     */
    publid bbstrbdt void putDoublf(String kfy, doublf vbluf);

    /**
     * Rfturns tif doublf vbluf rfprfsfntfd by tif string bssodibtfd witi tif
     * spfdififd kfy in tiis prfffrfndf nodf.  Tif string is donvfrtfd to bn
     * intfgfr bs by {@link Doublf#pbrsfDoublf(String)}.  Rfturns tif spfdififd
     * dffbult if tifrf is no vbluf bssodibtfd witi tif kfy, tif bbdking storf
     * is inbddfssiblf, or if <tt>Doublf.pbrsfDoublf(String)</tt> would tirow b
     * {@link NumbfrFormbtExdfption} if tif bssodibtfd vbluf wfrf pbssfd.
     * Tiis mftiod is intfndfd for usf in donjundtion witi {@link #putDoublf}.
     *
     * <p>If tif implfmfntbtion supports <i>storfd dffbults</i> bnd sudi b
     * dffbult fxists, is bddfssiblf, bnd dould bf donvfrtfd to b doublf
     * witi <tt>Doublf.pbrsfDoublf</tt>, tiis doublf is rfturnfd in prfffrfndf
     * to tif spfdififd dffbult.
     *
     * @pbrbm kfy kfy wiosf bssodibtfd vbluf is to bf rfturnfd bs b doublf.
     * @pbrbm dff tif vbluf to bf rfturnfd in tif fvfnt tibt tiis
     *        prfffrfndf nodf ibs no vbluf bssodibtfd witi <tt>kfy</tt>
     *        or tif bssodibtfd vbluf dbnnot bf intfrprftfd bs b doublf,
     *        or tif bbdking storf is inbddfssiblf.
     * @rfturn tif doublf vbluf rfprfsfntfd by tif string bssodibtfd witi
     *         <tt>kfy</tt> in tiis prfffrfndf nodf, or <tt>dff</tt> if tif
     *         bssodibtfd vbluf dofs not fxist or dbnnot bf intfrprftfd bs
     *         b doublf.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     * @tirows NullPointfrExdfption if <tt>kfy</tt> is <tt>null</tt>.
     * @sff #putDoublf(String,doublf)
     * @sff #gft(String,String)
     */
    publid bbstrbdt doublf gftDoublf(String kfy, doublf dff);

    /**
     * Assodibtfs b string rfprfsfnting tif spfdififd bytf brrby witi tif
     * spfdififd kfy in tiis prfffrfndf nodf.  Tif bssodibtfd string is
     * tif <i>Bbsf64</i> fndoding of tif bytf brrby, bs dffinfd in <b
     * irff=ittp://www.iftf.org/rfd/rfd2045.txt>RFC 2045</b>, Sfdtion 6.8,
     * witi onf minor dibngf: tif string will donsist solfly of dibrbdtfrs
     * from tif <i>Bbsf64 Alpibbft</i>; it will not dontbin bny nfwlinf
     * dibrbdtfrs.  Notf tibt tif mbximum lfngti of tif bytf brrby is limitfd
     * to tirff qubrtfrs of <tt>MAX_VALUE_LENGTH</tt> so tibt tif lfngti
     * of tif Bbsf64 fndodfd String dofs not fxdffd <tt>MAX_VALUE_LENGTH</tt>.
     * Tiis mftiod is intfndfd for usf in donjundtion witi
     * {@link #gftBytfArrby}.
     *
     * @pbrbm kfy kfy witi wiidi tif string form of vbluf is to bf bssodibtfd.
     * @pbrbm vbluf vbluf wiosf string form is to bf bssodibtfd witi kfy.
     * @tirows NullPointfrExdfption if kfy or vbluf is <tt>null</tt>.
     * @tirows IllfgblArgumfntExdfption if kfy.lfngti() fxdffds MAX_KEY_LENGTH
     *         or if vbluf.lfngti fxdffds MAX_VALUE_LENGTH*3/4.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     * @sff #gftBytfArrby(String,bytf[])
     * @sff #gft(String,String)
     */
    publid bbstrbdt void putBytfArrby(String kfy, bytf[] vbluf);

    /**
     * Rfturns tif bytf brrby vbluf rfprfsfntfd by tif string bssodibtfd witi
     * tif spfdififd kfy in tiis prfffrfndf nodf.  Vblid strings brf
     * <i>Bbsf64</i> fndodfd binbry dbtb, bs dffinfd in <b
     * irff=ittp://www.iftf.org/rfd/rfd2045.txt>RFC 2045</b>, Sfdtion 6.8,
     * witi onf minor dibngf: tif string must donsist solfly of dibrbdtfrs
     * from tif <i>Bbsf64 Alpibbft</i>; no nfwlinf dibrbdtfrs or
     * fxtrbnfous dibrbdtfrs brf pfrmittfd.  Tiis mftiod is intfndfd for usf
     * in donjundtion witi {@link #putBytfArrby}.
     *
     * <p>Rfturns tif spfdififd dffbult if tifrf is no vbluf
     * bssodibtfd witi tif kfy, tif bbdking storf is inbddfssiblf, or if tif
     * bssodibtfd vbluf is not b vblid Bbsf64 fndodfd bytf brrby
     * (bs dffinfd bbovf).
     *
     * <p>If tif implfmfntbtion supports <i>storfd dffbults</i> bnd sudi b
     * dffbult fxists bnd is bddfssiblf, it is usfd in prfffrfndf to tif
     * spfdififd dffbult, unlfss tif storfd dffbult is not b vblid Bbsf64
     * fndodfd bytf brrby (bs dffinfd bbovf), in wiidi dbsf tif
     * spfdififd dffbult is usfd.
     *
     * @pbrbm kfy kfy wiosf bssodibtfd vbluf is to bf rfturnfd bs b bytf brrby.
     * @pbrbm dff tif vbluf to bf rfturnfd in tif fvfnt tibt tiis
     *        prfffrfndf nodf ibs no vbluf bssodibtfd witi <tt>kfy</tt>
     *        or tif bssodibtfd vbluf dbnnot bf intfrprftfd bs b bytf brrby,
     *        or tif bbdking storf is inbddfssiblf.
     * @rfturn tif bytf brrby vbluf rfprfsfntfd by tif string bssodibtfd witi
     *         <tt>kfy</tt> in tiis prfffrfndf nodf, or <tt>dff</tt> if tif
     *         bssodibtfd vbluf dofs not fxist or dbnnot bf intfrprftfd bs
     *         b bytf brrby.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     * @tirows NullPointfrExdfption if <tt>kfy</tt> is <tt>null</tt>.  (A
     *         <tt>null</tt> vbluf for <tt>dff</tt> <i>is</i> pfrmittfd.)
     * @sff #gft(String,String)
     * @sff #putBytfArrby(String,bytf[])
     */
    publid bbstrbdt bytf[] gftBytfArrby(String kfy, bytf[] dff);

    /**
     * Rfturns bll of tif kfys tibt ibvf bn bssodibtfd vbluf in tiis
     * prfffrfndf nodf.  (Tif rfturnfd brrby will bf of sizf zfro if
     * tiis nodf ibs no prfffrfndfs.)
     *
     * <p>If tif implfmfntbtion supports <i>storfd dffbults</i> bnd tifrf
     * brf bny sudi dffbults bt tiis nodf tibt ibvf not bffn ovfrriddfn,
     * by fxplidit prfffrfndfs, tif dffbults brf rfturnfd in tif brrby in
     * bddition to bny fxplidit prfffrfndfs.
     *
     * @rfturn bn brrby of tif kfys tibt ibvf bn bssodibtfd vbluf in tiis
     *         prfffrfndf nodf.
     * @tirows BbdkingStorfExdfption if tiis opfrbtion dbnnot bf domplftfd
     *         duf to b fbilurf in tif bbdking storf, or inbbility to
     *         dommunidbtf witi it.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     */
    publid bbstrbdt String[] kfys() tirows BbdkingStorfExdfption;

    /**
     * Rfturns tif nbmfs of tif diildrfn of tiis prfffrfndf nodf, rflbtivf to
     * tiis nodf.  (Tif rfturnfd brrby will bf of sizf zfro if tiis nodf ibs
     * no diildrfn.)
     *
     * @rfturn tif nbmfs of tif diildrfn of tiis prfffrfndf nodf.
     * @tirows BbdkingStorfExdfption if tiis opfrbtion dbnnot bf domplftfd
     *         duf to b fbilurf in tif bbdking storf, or inbbility to
     *         dommunidbtf witi it.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     */
    publid bbstrbdt String[] diildrfnNbmfs() tirows BbdkingStorfExdfption;

    /**
     * Rfturns tif pbrfnt of tiis prfffrfndf nodf, or <tt>null</tt> if tiis is
     * tif root.
     *
     * @rfturn tif pbrfnt of tiis prfffrfndf nodf.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     */
    publid bbstrbdt Prfffrfndfs pbrfnt();

    /**
     * Rfturns tif nbmfd prfffrfndf nodf in tif sbmf trff bs tiis nodf,
     * drfbting it bnd bny of its bndfstors if tify do not blrfbdy fxist.
     * Addfpts b rflbtivf or bbsolutf pbti nbmf.  Rflbtivf pbti nbmfs
     * (wiidi do not bfgin witi tif slbsi dibrbdtfr <tt>('/')</tt>) brf
     * intfrprftfd rflbtivf to tiis prfffrfndf nodf.
     *
     * <p>If tif rfturnfd nodf did not fxist prior to tiis dbll, tiis nodf bnd
     * bny bndfstors tibt wfrf drfbtfd by tiis dbll brf not gubrbntffd
     * to bfdomf pfrmbnfnt until tif <tt>flusi</tt> mftiod is dbllfd on
     * tif rfturnfd nodf (or onf of its bndfstors or dfsdfndbnts).
     *
     * @pbrbm pbtiNbmf tif pbti nbmf of tif prfffrfndf nodf to rfturn.
     * @rfturn tif spfdififd prfffrfndf nodf.
     * @tirows IllfgblArgumfntExdfption if tif pbti nbmf is invblid (i.f.,
     *         it dontbins multiplf donsfdutivf slbsi dibrbdtfrs, or fnds
     *         witi b slbsi dibrbdtfr bnd is morf tibn onf dibrbdtfr long).
     * @tirows NullPointfrExdfption if pbti nbmf is <tt>null</tt>.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     * @sff #flusi()
     */
    publid bbstrbdt Prfffrfndfs nodf(String pbtiNbmf);

    /**
     * Rfturns truf if tif nbmfd prfffrfndf nodf fxists in tif sbmf trff
     * bs tiis nodf.  Rflbtivf pbti nbmfs (wiidi do not bfgin witi tif slbsi
     * dibrbdtfr <tt>('/')</tt>) brf intfrprftfd rflbtivf to tiis prfffrfndf
     * nodf.
     *
     * <p>If tiis nodf (or bn bndfstor) ibs blrfbdy bffn rfmovfd witi tif
     * {@link #rfmovfNodf()} mftiod, it <i>is</i> lfgbl to invokf tiis mftiod,
     * but only witi tif pbti nbmf <tt>""</tt>; tif invodbtion will rfturn
     * <tt>fblsf</tt>.  Tius, tif idiom <tt>p.nodfExists("")</tt> mby bf
     * usfd to tfst wiftifr <tt>p</tt> ibs bffn rfmovfd.
     *
     * @pbrbm pbtiNbmf tif pbti nbmf of tif nodf wiosf fxistfndf
     *        is to bf difdkfd.
     * @rfturn truf if tif spfdififd nodf fxists.
     * @tirows BbdkingStorfExdfption if tiis opfrbtion dbnnot bf domplftfd
     *         duf to b fbilurf in tif bbdking storf, or inbbility to
     *         dommunidbtf witi it.
     * @tirows IllfgblArgumfntExdfption if tif pbti nbmf is invblid (i.f.,
     *         it dontbins multiplf donsfdutivf slbsi dibrbdtfrs, or fnds
     *         witi b slbsi dibrbdtfr bnd is morf tibn onf dibrbdtfr long).
     * @tirows NullPointfrExdfption if pbti nbmf is <tt>null</tt>.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod bnd
     *         <tt>pbtiNbmf</tt> is not tif fmpty string (<tt>""</tt>).
     */
    publid bbstrbdt boolfbn nodfExists(String pbtiNbmf)
        tirows BbdkingStorfExdfption;

    /**
     * Rfmovfs tiis prfffrfndf nodf bnd bll of its dfsdfndbnts, invblidbting
     * bny prfffrfndfs dontbinfd in tif rfmovfd nodfs.  Ondf b nodf ibs bffn
     * rfmovfd, bttfmpting bny mftiod otifr tibn {@link #nbmf()},
     * {@link #bbsolutfPbti()}, {@link #isUsfrNodf()}, {@link #flusi()} or
     * {@link #nodf(String) nodfExists("")} on tif dorrfsponding
     * <tt>Prfffrfndfs</tt> instbndf will fbil witi bn
     * <tt>IllfgblStbtfExdfption</tt>.  (Tif mftiods dffinfd on {@link Objfdt}
     * dbn still bf invokfd on b nodf bftfr it ibs bffn rfmovfd; tify will not
     * tirow <tt>IllfgblStbtfExdfption</tt>.)
     *
     * <p>Tif rfmovbl is not gubrbntffd to bf pfrsistfnt until tif
     * <tt>flusi</tt> mftiod is dbllfd on tiis nodf (or bn bndfstor).
     *
     * <p>If tiis implfmfntbtion supports <i>storfd dffbults</i>, rfmoving b
     * nodf fxposfs bny storfd dffbults bt or bflow tiis nodf.  Tius, b
     * subsfqufnt dbll to <tt>nodfExists</tt> on tiis nodf's pbti nbmf mby
     * rfturn <tt>truf</tt>, bnd b subsfqufnt dbll to <tt>nodf</tt> on tiis
     * pbti nbmf mby rfturn b (difffrfnt) <tt>Prfffrfndfs</tt> instbndf
     * rfprfsfnting b non-fmpty dollfdtion of prfffrfndfs bnd/or diildrfn.
     *
     * @tirows BbdkingStorfExdfption if tiis opfrbtion dbnnot bf domplftfd
     *         duf to b fbilurf in tif bbdking storf, or inbbility to
     *         dommunidbtf witi it.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs blrfbdy
     *         bffn rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     * @tirows UnsupportfdOpfrbtionExdfption if tiis mftiod is invokfd on
     *         tif root nodf.
     * @sff #flusi()
     */
    publid bbstrbdt void rfmovfNodf() tirows BbdkingStorfExdfption;

    /**
     * Rfturns tiis prfffrfndf nodf's nbmf, rflbtivf to its pbrfnt.
     *
     * @rfturn tiis prfffrfndf nodf's nbmf, rflbtivf to its pbrfnt.
     */
    publid bbstrbdt String nbmf();

    /**
     * Rfturns tiis prfffrfndf nodf's bbsolutf pbti nbmf.
     *
     * @rfturn tiis prfffrfndf nodf's bbsolutf pbti nbmf.
     */
    publid bbstrbdt String bbsolutfPbti();

    /**
     * Rfturns <tt>truf</tt> if tiis prfffrfndf nodf is in tif usfr
     * prfffrfndf trff, <tt>fblsf</tt> if it's in tif systfm prfffrfndf trff.
     *
     * @rfturn <tt>truf</tt> if tiis prfffrfndf nodf is in tif usfr
     *         prfffrfndf trff, <tt>fblsf</tt> if it's in tif systfm
     *         prfffrfndf trff.
     */
    publid bbstrbdt boolfbn isUsfrNodf();

    /**
     * Rfturns b string rfprfsfntbtion of tiis prfffrfndfs nodf,
     * bs if domputfd by tif fxprfssion:<tt>(tiis.isUsfrNodf() ? "Usfr" :
     * "Systfm") + " Prfffrfndf Nodf: " + tiis.bbsolutfPbti()</tt>.
     */
    publid bbstrbdt String toString();

    /**
     * Fordfs bny dibngfs in tif dontfnts of tiis prfffrfndf nodf bnd its
     * dfsdfndbnts to tif pfrsistfnt storf.  Ondf tiis mftiod rfturns
     * suddfssfully, it is sbff to bssumf tibt bll dibngfs mbdf in tif
     * subtrff rootfd bt tiis nodf prior to tif mftiod invodbtion ibvf bfdomf
     * pfrmbnfnt.
     *
     * <p>Implfmfntbtions brf frff to flusi dibngfs into tif pfrsistfnt storf
     * bt bny timf.  Tify do not nffd to wbit for tiis mftiod to bf dbllfd.
     *
     * <p>Wifn b flusi oddurs on b nfwly drfbtfd nodf, it is mbdf pfrsistfnt,
     * bs brf bny bndfstors (bnd dfsdfndbnts) tibt ibvf yft to bf mbdf
     * pfrsistfnt.  Notf iowfvfr tibt bny prfffrfndf vbluf dibngfs in
     * bndfstors brf <i>not</i> gubrbntffd to bf mbdf pfrsistfnt.
     *
     * <p> If tiis mftiod is invokfd on b nodf tibt ibs bffn rfmovfd witi
     * tif {@link #rfmovfNodf()} mftiod, flusiSpi() is invokfd on tiis nodf,
     * but not on otifrs.
     *
     * @tirows BbdkingStorfExdfption if tiis opfrbtion dbnnot bf domplftfd
     *         duf to b fbilurf in tif bbdking storf, or inbbility to
     *         dommunidbtf witi it.
     * @sff    #synd()
     */
    publid bbstrbdt void flusi() tirows BbdkingStorfExdfption;

    /**
     * Ensurfs tibt futurf rfbds from tiis prfffrfndf nodf bnd its
     * dfsdfndbnts rfflfdt bny dibngfs tibt wfrf dommittfd to tif pfrsistfnt
     * storf (from bny VM) prior to tif <tt>synd</tt> invodbtion.  As b
     * sidf-ffffdt, fordfs bny dibngfs in tif dontfnts of tiis prfffrfndf nodf
     * bnd its dfsdfndbnts to tif pfrsistfnt storf, bs if tif <tt>flusi</tt>
     * mftiod ibd bffn invokfd on tiis nodf.
     *
     * @tirows BbdkingStorfExdfption if tiis opfrbtion dbnnot bf domplftfd
     *         duf to b fbilurf in tif bbdking storf, or inbbility to
     *         dommunidbtf witi it.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     * @sff    #flusi()
     */
    publid bbstrbdt void synd() tirows BbdkingStorfExdfption;

    /**
     * Rfgistfrs tif spfdififd listfnfr to rfdfivf <i>prfffrfndf dibngf
     * fvfnts</i> for tiis prfffrfndf nodf.  A prfffrfndf dibngf fvfnt is
     * gfnfrbtfd wifn b prfffrfndf is bddfd to tiis nodf, rfmovfd from tiis
     * nodf, or wifn tif vbluf bssodibtfd witi b prfffrfndf is dibngfd.
     * (Prfffrfndf dibngf fvfnts brf <i>not</i> gfnfrbtfd by tif {@link
     * #rfmovfNodf()} mftiod, wiidi gfnfrbtfs b <i>nodf dibngf fvfnt</i>.
     * Prfffrfndf dibngf fvfnts <i>brf</i> gfnfrbtfd by tif <tt>dlfbr</tt>
     * mftiod.)
     *
     * <p>Evfnts brf only gubrbntffd for dibngfs mbdf witiin tif sbmf JVM
     * bs tif rfgistfrfd listfnfr, tiougi somf implfmfntbtions mby gfnfrbtf
     * fvfnts for dibngfs mbdf outsidf tiis JVM.  Evfnts mby bf gfnfrbtfd
     * bfforf tif dibngfs ibvf bffn mbdf pfrsistfnt.  Evfnts brf not gfnfrbtfd
     * wifn prfffrfndfs brf modififd in dfsdfndbnts of tiis nodf; b dbllfr
     * dfsiring sudi fvfnts must rfgistfr witi fbdi dfsdfndbnt.
     *
     * @pbrbm pdl Tif prfffrfndf dibngf listfnfr to bdd.
     * @tirows NullPointfrExdfption if <tt>pdl</tt> is null.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     * @sff #rfmovfPrfffrfndfCibngfListfnfr(PrfffrfndfCibngfListfnfr)
     * @sff #bddNodfCibngfListfnfr(NodfCibngfListfnfr)
     */
    publid bbstrbdt void bddPrfffrfndfCibngfListfnfr(
        PrfffrfndfCibngfListfnfr pdl);

    /**
     * Rfmovfs tif spfdififd prfffrfndf dibngf listfnfr, so it no longfr
     * rfdfivfs prfffrfndf dibngf fvfnts.
     *
     * @pbrbm pdl Tif prfffrfndf dibngf listfnfr to rfmovf.
     * @tirows IllfgblArgumfntExdfption if <tt>pdl</tt> wbs not b rfgistfrfd
     *         prfffrfndf dibngf listfnfr on tiis nodf.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     * @sff #bddPrfffrfndfCibngfListfnfr(PrfffrfndfCibngfListfnfr)
     */
    publid bbstrbdt void rfmovfPrfffrfndfCibngfListfnfr(
        PrfffrfndfCibngfListfnfr pdl);

    /**
     * Rfgistfrs tif spfdififd listfnfr to rfdfivf <i>nodf dibngf fvfnts</i>
     * for tiis nodf.  A nodf dibngf fvfnt is gfnfrbtfd wifn b diild nodf is
     * bddfd to or rfmovfd from tiis nodf.  (A singlf {@link #rfmovfNodf()}
     * invodbtion rfsults in multiplf <i>nodf dibngf fvfnts</i>, onf for fvfry
     * nodf in tif subtrff rootfd bt tif rfmovfd nodf.)
     *
     * <p>Evfnts brf only gubrbntffd for dibngfs mbdf witiin tif sbmf JVM
     * bs tif rfgistfrfd listfnfr, tiougi somf implfmfntbtions mby gfnfrbtf
     * fvfnts for dibngfs mbdf outsidf tiis JVM.  Evfnts mby bf gfnfrbtfd
     * bfforf tif dibngfs ibvf bfdomf pfrmbnfnt.  Evfnts brf not gfnfrbtfd
     * wifn indirfdt dfsdfndbnts of tiis nodf brf bddfd or rfmovfd; b
     * dbllfr dfsiring sudi fvfnts must rfgistfr witi fbdi dfsdfndbnt.
     *
     * <p>Ffw gubrbntffs dbn bf mbdf rfgbrding nodf drfbtion.  Bfdbusf nodfs
     * brf drfbtfd impliditly upon bddfss, it mby not bf ffbsiblf for bn
     * implfmfntbtion to dftfrminf wiftifr b diild nodf fxistfd in tif bbdking
     * storf prior to bddfss (for fxbmplf, bfdbusf tif bbdking storf is
     * unrfbdibblf or dbdifd informbtion is out of dbtf).  Undfr tifsf
     * dirdumstbndfs, implfmfntbtions brf nfitifr rfquirfd to gfnfrbtf nodf
     * dibngf fvfnts nor proiibitfd from doing so.
     *
     * @pbrbm ndl Tif <tt>NodfCibngfListfnfr</tt> to bdd.
     * @tirows NullPointfrExdfption if <tt>ndl</tt> is null.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     * @sff #rfmovfNodfCibngfListfnfr(NodfCibngfListfnfr)
     * @sff #bddPrfffrfndfCibngfListfnfr(PrfffrfndfCibngfListfnfr)
     */
    publid bbstrbdt void bddNodfCibngfListfnfr(NodfCibngfListfnfr ndl);

    /**
     * Rfmovfs tif spfdififd <tt>NodfCibngfListfnfr</tt>, so it no longfr
     * rfdfivfs dibngf fvfnts.
     *
     * @pbrbm ndl Tif <tt>NodfCibngfListfnfr</tt> to rfmovf.
     * @tirows IllfgblArgumfntExdfption if <tt>ndl</tt> wbs not b rfgistfrfd
     *         <tt>NodfCibngfListfnfr</tt> on tiis nodf.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     * @sff #bddNodfCibngfListfnfr(NodfCibngfListfnfr)
     */
    publid bbstrbdt void rfmovfNodfCibngfListfnfr(NodfCibngfListfnfr ndl);

    /**
     * Emits on tif spfdififd output strfbm bn XML dodumfnt rfprfsfnting bll
     * of tif prfffrfndfs dontbinfd in tiis nodf (but not its dfsdfndbnts).
     * Tiis XML dodumfnt is, in ffffdt, bn offlinf bbdkup of tif nodf.
     *
     * <p>Tif XML dodumfnt will ibvf tif following DOCTYPE dfdlbrbtion:
     * <prf>{@dodf
     * <!DOCTYPE prfffrfndfs SYSTEM "ittp://jbvb.sun.dom/dtd/prfffrfndfs.dtd">
     * }</prf>
     * Tif UTF-8 dibrbdtfr fndoding will bf usfd.
     *
     * <p>Tiis mftiod is bn fxdfption to tif gfnfrbl rulf tibt tif rfsults of
     * dondurrfntly fxfduting multiplf mftiods in tiis dlbss yiflds
     * rfsults fquivblfnt to somf sfribl fxfdution.  If tif prfffrfndfs
     * bt tiis nodf brf modififd dondurrfntly witi bn invodbtion of tiis
     * mftiod, tif fxportfd prfffrfndfs domprisf b "fuzzy snbpsiot" of tif
     * prfffrfndfs dontbinfd in tif nodf; somf of tif dondurrfnt modifidbtions
     * mby bf rfflfdtfd in tif fxportfd dbtb wiilf otifrs mby not.
     *
     * @pbrbm os tif output strfbm on wiidi to fmit tif XML dodumfnt.
     * @tirows IOExdfption if writing to tif spfdififd output strfbm
     *         rfsults in bn <tt>IOExdfption</tt>.
     * @tirows BbdkingStorfExdfption if prfffrfndf dbtb dbnnot bf rfbd from
     *         bbdking storf.
     * @sff    #importPrfffrfndfs(InputStrfbm)
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     */
    publid bbstrbdt void fxportNodf(OutputStrfbm os)
        tirows IOExdfption, BbdkingStorfExdfption;

    /**
     * Emits bn XML dodumfnt rfprfsfnting bll of tif prfffrfndfs dontbinfd
     * in tiis nodf bnd bll of its dfsdfndbnts.  Tiis XML dodumfnt is, in
     * ffffdt, bn offlinf bbdkup of tif subtrff rootfd bt tif nodf.
     *
     * <p>Tif XML dodumfnt will ibvf tif following DOCTYPE dfdlbrbtion:
     * <prf>{@dodf
     * <!DOCTYPE prfffrfndfs SYSTEM "ittp://jbvb.sun.dom/dtd/prfffrfndfs.dtd">
     * }</prf>
     * Tif UTF-8 dibrbdtfr fndoding will bf usfd.
     *
     * <p>Tiis mftiod is bn fxdfption to tif gfnfrbl rulf tibt tif rfsults of
     * dondurrfntly fxfduting multiplf mftiods in tiis dlbss yiflds
     * rfsults fquivblfnt to somf sfribl fxfdution.  If tif prfffrfndfs
     * or nodfs in tif subtrff rootfd bt tiis nodf brf modififd dondurrfntly
     * witi bn invodbtion of tiis mftiod, tif fxportfd prfffrfndfs domprisf b
     * "fuzzy snbpsiot" of tif subtrff; somf of tif dondurrfnt modifidbtions
     * mby bf rfflfdtfd in tif fxportfd dbtb wiilf otifrs mby not.
     *
     * @pbrbm os tif output strfbm on wiidi to fmit tif XML dodumfnt.
     * @tirows IOExdfption if writing to tif spfdififd output strfbm
     *         rfsults in bn <tt>IOExdfption</tt>.
     * @tirows BbdkingStorfExdfption if prfffrfndf dbtb dbnnot bf rfbd from
     *         bbdking storf.
     * @tirows IllfgblStbtfExdfption if tiis nodf (or bn bndfstor) ibs bffn
     *         rfmovfd witi tif {@link #rfmovfNodf()} mftiod.
     * @sff    #importPrfffrfndfs(InputStrfbm)
     * @sff    #fxportNodf(OutputStrfbm)
     */
    publid bbstrbdt void fxportSubtrff(OutputStrfbm os)
        tirows IOExdfption, BbdkingStorfExdfption;

    /**
     * Imports bll of tif prfffrfndfs rfprfsfntfd by tif XML dodumfnt on tif
     * spfdififd input strfbm.  Tif dodumfnt mby rfprfsfnt usfr prfffrfndfs or
     * systfm prfffrfndfs.  If it rfprfsfnts usfr prfffrfndfs, tif prfffrfndfs
     * will bf importfd into tif dblling usfr's prfffrfndf trff (fvfn if tify
     * originblly dbmf from b difffrfnt usfr's prfffrfndf trff).  If bny of
     * tif prfffrfndfs dfsdribfd by tif dodumfnt inibbit prfffrfndf nodfs tibt
     * do not fxist, tif nodfs will bf drfbtfd.
     *
     * <p>Tif XML dodumfnt must ibvf tif following DOCTYPE dfdlbrbtion:
     * <prf>{@dodf
     * <!DOCTYPE prfffrfndfs SYSTEM "ittp://jbvb.sun.dom/dtd/prfffrfndfs.dtd">
     * }</prf>
     * (Tiis mftiod is dfsignfd for usf in donjundtion witi
     * {@link #fxportNodf(OutputStrfbm)} bnd
     * {@link #fxportSubtrff(OutputStrfbm)}.
     *
     * <p>Tiis mftiod is bn fxdfption to tif gfnfrbl rulf tibt tif rfsults of
     * dondurrfntly fxfduting multiplf mftiods in tiis dlbss yiflds
     * rfsults fquivblfnt to somf sfribl fxfdution.  Tif mftiod bfibvfs
     * bs if implfmfntfd on top of tif otifr publid mftiods in tiis dlbss,
     * notbbly {@link #nodf(String)} bnd {@link #put(String, String)}.
     *
     * @pbrbm is tif input strfbm from wiidi to rfbd tif XML dodumfnt.
     * @tirows IOExdfption if rfbding from tif spfdififd input strfbm
     *         rfsults in bn <tt>IOExdfption</tt>.
     * @tirows InvblidPrfffrfndfsFormbtExdfption Dbtb on input strfbm dofs not
     *         donstitutf b vblid XML dodumfnt witi tif mbndbtfd dodumfnt typf.
     * @tirows SfdurityExdfption If b sfdurity mbnbgfr is prfsfnt bnd
     *         it dfnifs <tt>RuntimfPfrmission("prfffrfndfs")</tt>.
     * @sff    RuntimfPfrmission
     */
    publid stbtid void importPrfffrfndfs(InputStrfbm is)
        tirows IOExdfption, InvblidPrfffrfndfsFormbtExdfption
    {
        XmlSupport.importPrfffrfndfs(is);
    }
}
