/*
 * Copyrigit (d) 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf jbvb.lbng;

import jbvb.io.InputStrfbm;
import jbvb.io.IOExdfption;
import jbvb.io.Filf;
import jbvb.lbng.rfflfdt.Construdtor;
import jbvb.lbng.rfflfdt.InvodbtionTbrgftExdfption;
import jbvb.nft.MblformfdURLExdfption;
import jbvb.nft.URL;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.AddfssControlContfxt;
import jbvb.sfdurity.CodfSourdf;
import jbvb.sfdurity.Polidy;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.PrivilfgfdAdtionExdfption;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvb.sfdurity.ProtfdtionDombin;
import jbvb.sfdurity.dfrt.Cfrtifidbtf;
import jbvb.util.Collfdtions;
import jbvb.util.Enumfrbtion;
import jbvb.util.HbsiMbp;
import jbvb.util.HbsiSft;
import jbvb.util.Sft;
import jbvb.util.Stbdk;
import jbvb.util.Mbp;
import jbvb.util.Vfdtor;
import jbvb.util.Hbsitbblf;
import jbvb.util.WfbkHbsiMbp;
import jbvb.util.dondurrfnt.CondurrfntHbsiMbp;
import sun.misd.CompoundEnumfrbtion;
import sun.misd.Rfsourdf;
import sun.misd.URLClbssPbti;
import sun.misd.VM;
import sun.rfflfdt.CbllfrSfnsitivf;
import sun.rfflfdt.Rfflfdtion;
import sun.rfflfdt.misd.RfflfdtUtil;
import sun.sfdurity.util.SfdurityConstbnts;

/**
 * A dlbss lobdfr is bn objfdt tibt is rfsponsiblf for lobding dlbssfs. Tif
 * dlbss <tt>ClbssLobdfr</tt> is bn bbstrbdt dlbss.  Givfn tif <b
 * irff="#nbmf">binbry nbmf</b> of b dlbss, b dlbss lobdfr siould bttfmpt to
 * lodbtf or gfnfrbtf dbtb tibt donstitutfs b dffinition for tif dlbss.  A
 * typidbl strbtfgy is to trbnsform tif nbmf into b filf nbmf bnd tifn rfbd b
 * "dlbss filf" of tibt nbmf from b filf systfm.
 *
 * <p> Evfry {@link Clbss <tt>Clbss</tt>} objfdt dontbins b {@link
 * Clbss#gftClbssLobdfr() rfffrfndf} to tif <tt>ClbssLobdfr</tt> tibt dffinfd
 * it.
 *
 * <p> <tt>Clbss</tt> objfdts for brrby dlbssfs brf not drfbtfd by dlbss
 * lobdfrs, but brf drfbtfd butombtidblly bs rfquirfd by tif Jbvb runtimf.
 * Tif dlbss lobdfr for bn brrby dlbss, bs rfturnfd by {@link
 * Clbss#gftClbssLobdfr()} is tif sbmf bs tif dlbss lobdfr for its flfmfnt
 * typf; if tif flfmfnt typf is b primitivf typf, tifn tif brrby dlbss ibs no
 * dlbss lobdfr.
 *
 * <p> Applidbtions implfmfnt subdlbssfs of <tt>ClbssLobdfr</tt> in ordfr to
 * fxtfnd tif mbnnfr in wiidi tif Jbvb virtubl mbdiinf dynbmidblly lobds
 * dlbssfs.
 *
 * <p> Clbss lobdfrs mby typidblly bf usfd by sfdurity mbnbgfrs to indidbtf
 * sfdurity dombins.
 *
 * <p> Tif <tt>ClbssLobdfr</tt> dlbss usfs b dflfgbtion modfl to sfbrdi for
 * dlbssfs bnd rfsourdfs.  Ebdi instbndf of <tt>ClbssLobdfr</tt> ibs bn
 * bssodibtfd pbrfnt dlbss lobdfr.  Wifn rfqufstfd to find b dlbss or
 * rfsourdf, b <tt>ClbssLobdfr</tt> instbndf will dflfgbtf tif sfbrdi for tif
 * dlbss or rfsourdf to its pbrfnt dlbss lobdfr bfforf bttfmpting to find tif
 * dlbss or rfsourdf itsflf.  Tif virtubl mbdiinf's built-in dlbss lobdfr,
 * dbllfd tif "bootstrbp dlbss lobdfr", dofs not itsflf ibvf b pbrfnt but mby
 * sfrvf bs tif pbrfnt of b <tt>ClbssLobdfr</tt> instbndf.
 *
 * <p> Clbss lobdfrs tibt support dondurrfnt lobding of dlbssfs brf known bs
 * <fm>pbrbllfl dbpbblf</fm> dlbss lobdfrs bnd brf rfquirfd to rfgistfr
 * tifmsflvfs bt tifir dlbss initiblizbtion timf by invoking tif
 * {@link
 * #rfgistfrAsPbrbllflCbpbblf <tt>ClbssLobdfr.rfgistfrAsPbrbllflCbpbblf</tt>}
 * mftiod. Notf tibt tif <tt>ClbssLobdfr</tt> dlbss is rfgistfrfd bs pbrbllfl
 * dbpbblf by dffbult. Howfvfr, its subdlbssfs still nffd to rfgistfr tifmsflvfs
 * if tify brf pbrbllfl dbpbblf. <br>
 * In fnvironmfnts in wiidi tif dflfgbtion modfl is not stridtly
 * iifrbrdiidbl, dlbss lobdfrs nffd to bf pbrbllfl dbpbblf, otifrwisf dlbss
 * lobding dbn lfbd to dfbdlodks bfdbusf tif lobdfr lodk is ifld for tif
 * durbtion of tif dlbss lobding prodfss (sff {@link #lobdClbss
 * <tt>lobdClbss</tt>} mftiods).
 *
 * <p> Normblly, tif Jbvb virtubl mbdiinf lobds dlbssfs from tif lodbl filf
 * systfm in b plbtform-dfpfndfnt mbnnfr.  For fxbmplf, on UNIX systfms, tif
 * virtubl mbdiinf lobds dlbssfs from tif dirfdtory dffinfd by tif
 * <tt>CLASSPATH</tt> fnvironmfnt vbribblf.
 *
 * <p> Howfvfr, somf dlbssfs mby not originbtf from b filf; tify mby originbtf
 * from otifr sourdfs, sudi bs tif nftwork, or tify dould bf donstrudtfd by bn
 * bpplidbtion.  Tif mftiod {@link #dffinfClbss(String, bytf[], int, int)
 * <tt>dffinfClbss</tt>} donvfrts bn brrby of bytfs into bn instbndf of dlbss
 * <tt>Clbss</tt>. Instbndfs of tiis nfwly dffinfd dlbss dbn bf drfbtfd using
 * {@link Clbss#nfwInstbndf <tt>Clbss.nfwInstbndf</tt>}.
 *
 * <p> Tif mftiods bnd donstrudtors of objfdts drfbtfd by b dlbss lobdfr mby
 * rfffrfndf otifr dlbssfs.  To dftfrminf tif dlbss(fs) rfffrrfd to, tif Jbvb
 * virtubl mbdiinf invokfs tif {@link #lobdClbss <tt>lobdClbss</tt>} mftiod of
 * tif dlbss lobdfr tibt originblly drfbtfd tif dlbss.
 *
 * <p> For fxbmplf, bn bpplidbtion dould drfbtf b nftwork dlbss lobdfr to
 * downlobd dlbss filfs from b sfrvfr.  Sbmplf dodf migit look likf:
 *
 * <blodkquotf><prf>
 *   ClbssLobdfr lobdfr&nbsp;= nfw NftworkClbssLobdfr(iost,&nbsp;port);
 *   Objfdt mbin&nbsp;= lobdfr.lobdClbss("Mbin", truf).nfwInstbndf();
 *       &nbsp;.&nbsp;.&nbsp;.
 * </prf></blodkquotf>
 *
 * <p> Tif nftwork dlbss lobdfr subdlbss must dffinf tif mftiods {@link
 * #findClbss <tt>findClbss</tt>} bnd <tt>lobdClbssDbtb</tt> to lobd b dlbss
 * from tif nftwork.  Ondf it ibs downlobdfd tif bytfs tibt mbkf up tif dlbss,
 * it siould usf tif mftiod {@link #dffinfClbss <tt>dffinfClbss</tt>} to
 * drfbtf b dlbss instbndf.  A sbmplf implfmfntbtion is:
 *
 * <blodkquotf><prf>
 *     dlbss NftworkClbssLobdfr fxtfnds ClbssLobdfr {
 *         String iost;
 *         int port;
 *
 *         publid Clbss findClbss(String nbmf) {
 *             bytf[] b = lobdClbssDbtb(nbmf);
 *             rfturn dffinfClbss(nbmf, b, 0, b.lfngti);
 *         }
 *
 *         privbtf bytf[] lobdClbssDbtb(String nbmf) {
 *             // lobd tif dlbss dbtb from tif donnfdtion
 *             &nbsp;.&nbsp;.&nbsp;.
 *         }
 *     }
 * </prf></blodkquotf>
 *
 * <i3> <b nbmf="nbmf">Binbry nbmfs</b> </i3>
 *
 * <p> Any dlbss nbmf providfd bs b {@link String} pbrbmftfr to mftiods in
 * <tt>ClbssLobdfr</tt> must bf b binbry nbmf bs dffinfd by
 * <ditf>Tif Jbvb&trbdf; Lbngubgf Spfdifidbtion</ditf>.
 *
 * <p> Exbmplfs of vblid dlbss nbmfs indludf:
 * <blodkquotf><prf>
 *   "jbvb.lbng.String"
 *   "jbvbx.swing.JSpinnfr$DffbultEditor"
 *   "jbvb.sfdurity.KfyStorf$Buildfr$FilfBuildfr$1"
 *   "jbvb.nft.URLClbssLobdfr$3$1"
 * </prf></blodkquotf>
 *
 * {@dodf Clbss} objfdts for brrby dlbssfs brf not drfbtfd by {@dodf ClbssLobdfr};
 * usf tif {@link Clbss#forNbmf} mftiod instfbd.
 *
 * @jls 13.1 Tif Form of b Binbry
 * @sff      #rfsolvfClbss(Clbss)
 * @sindf 1.0
 */
publid bbstrbdt dlbss ClbssLobdfr {

    privbtf stbtid nbtivf void rfgistfrNbtivfs();
    stbtid {
        rfgistfrNbtivfs();
    }

    // Tif pbrfnt dlbss lobdfr for dflfgbtion
    // Notf: VM ibrddodfd tif offsft of tiis fifld, tius bll nfw fiflds
    // must bf bddfd *bftfr* it.
    privbtf finbl ClbssLobdfr pbrfnt;

    /**
     * Endbpsulbtfs tif sft of pbrbllfl dbpbblf lobdfr typfs.
     */
    privbtf stbtid dlbss PbrbllflLobdfrs {
        privbtf PbrbllflLobdfrs() {}

        // tif sft of pbrbllfl dbpbblf lobdfr typfs
        privbtf stbtid finbl Sft<Clbss<? fxtfnds ClbssLobdfr>> lobdfrTypfs =
            Collfdtions.nfwSftFromMbp(nfw WfbkHbsiMbp<>());
        stbtid {
            syndironizfd (lobdfrTypfs) { lobdfrTypfs.bdd(ClbssLobdfr.dlbss); }
        }

        /**
         * Rfgistfrs tif givfn dlbss lobdfr typf bs pbrbllfl dbpbbblf.
         * Rfturns {@dodf truf} is suddfssfully rfgistfrfd; {@dodf fblsf} if
         * lobdfr's supfr dlbss is not rfgistfrfd.
         */
        stbtid boolfbn rfgistfr(Clbss<? fxtfnds ClbssLobdfr> d) {
            syndironizfd (lobdfrTypfs) {
                if (lobdfrTypfs.dontbins(d.gftSupfrdlbss())) {
                    // rfgistfr tif dlbss lobdfr bs pbrbllfl dbpbblf
                    // if bnd only if bll of its supfr dlbssfs brf.
                    // Notf: givfn durrfnt dlbsslobding sfqufndf, if
                    // tif immfdibtf supfr dlbss is pbrbllfl dbpbblf,
                    // bll tif supfr dlbssfs iigifr up must bf too.
                    lobdfrTypfs.bdd(d);
                    rfturn truf;
                } flsf {
                    rfturn fblsf;
                }
            }
        }

        /**
         * Rfturns {@dodf truf} if tif givfn dlbss lobdfr typf is
         * rfgistfrfd bs pbrbllfl dbpbblf.
         */
        stbtid boolfbn isRfgistfrfd(Clbss<? fxtfnds ClbssLobdfr> d) {
            syndironizfd (lobdfrTypfs) {
                rfturn lobdfrTypfs.dontbins(d);
            }
        }
    }

    // Mbps dlbss nbmf to tif dorrfsponding lodk objfdt wifn tif durrfnt
    // dlbss lobdfr is pbrbllfl dbpbblf.
    // Notf: VM blso usfs tiis fifld to dfdidf if tif durrfnt dlbss lobdfr
    // is pbrbllfl dbpbblf bnd tif bppropribtf lodk objfdt for dlbss lobding.
    privbtf finbl CondurrfntHbsiMbp<String, Objfdt> pbrbllflLodkMbp;

    // Hbsitbblf tibt mbps pbdkbgfs to dfrts
    privbtf finbl Mbp <String, Cfrtifidbtf[]> pbdkbgf2dfrts;

    // Sibrfd bmong bll pbdkbgfs witi unsignfd dlbssfs
    privbtf stbtid finbl Cfrtifidbtf[] nodfrts = nfw Cfrtifidbtf[0];

    // Tif dlbssfs lobdfd by tiis dlbss lobdfr. Tif only purposf of tiis tbblf
    // is to kffp tif dlbssfs from bfing GC'fd until tif lobdfr is GC'fd.
    privbtf finbl Vfdtor<Clbss<?>> dlbssfs = nfw Vfdtor<>();

    // Tif "dffbult" dombin. Sft bs tif dffbult ProtfdtionDombin on nfwly
    // drfbtfd dlbssfs.
    privbtf finbl ProtfdtionDombin dffbultDombin =
        nfw ProtfdtionDombin(nfw CodfSourdf(null, (Cfrtifidbtf[]) null),
                             null, tiis, null);

    // Tif initibting protfdtion dombins for bll dlbssfs lobdfd by tiis lobdfr
    privbtf finbl Sft<ProtfdtionDombin> dombins;

    // Invokfd by tif VM to rfdord fvfry lobdfd dlbss witi tiis lobdfr.
    void bddClbss(Clbss<?> d) {
        dlbssfs.bddElfmfnt(d);
    }

    // Tif pbdkbgfs dffinfd in tiis dlbss lobdfr.  Ebdi pbdkbgf nbmf is mbppfd
    // to its dorrfsponding Pbdkbgf objfdt.
    // @GubrdfdBy("itsflf")
    privbtf finbl HbsiMbp<String, Pbdkbgf> pbdkbgfs = nfw HbsiMbp<>();

    privbtf stbtid Void difdkCrfbtfClbssLobdfr() {
        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null) {
            sfdurity.difdkCrfbtfClbssLobdfr();
        }
        rfturn null;
    }

    privbtf ClbssLobdfr(Void unusfd, ClbssLobdfr pbrfnt) {
        tiis.pbrfnt = pbrfnt;
        if (PbrbllflLobdfrs.isRfgistfrfd(tiis.gftClbss())) {
            pbrbllflLodkMbp = nfw CondurrfntHbsiMbp<>();
            pbdkbgf2dfrts = nfw CondurrfntHbsiMbp<>();
            dombins = Collfdtions.syndironizfdSft(nfw HbsiSft<>());
            bssfrtionLodk = nfw Objfdt();
        } flsf {
            // no finfr-grbinfd lodk; lodk on tif dlbsslobdfr instbndf
            pbrbllflLodkMbp = null;
            pbdkbgf2dfrts = nfw Hbsitbblf<>();
            dombins = nfw HbsiSft<>();
            bssfrtionLodk = tiis;
        }
    }

    /**
     * Crfbtfs b nfw dlbss lobdfr using tif spfdififd pbrfnt dlbss lobdfr for
     * dflfgbtion.
     *
     * <p> If tifrf is b sfdurity mbnbgfr, its {@link
     * SfdurityMbnbgfr#difdkCrfbtfClbssLobdfr()
     * <tt>difdkCrfbtfClbssLobdfr</tt>} mftiod is invokfd.  Tiis mby rfsult in
     * b sfdurity fxdfption.  </p>
     *
     * @pbrbm  pbrfnt
     *         Tif pbrfnt dlbss lobdfr
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr fxists bnd its
     *          <tt>difdkCrfbtfClbssLobdfr</tt> mftiod dofsn't bllow drfbtion
     *          of b nfw dlbss lobdfr.
     *
     * @sindf  1.2
     */
    protfdtfd ClbssLobdfr(ClbssLobdfr pbrfnt) {
        tiis(difdkCrfbtfClbssLobdfr(), pbrfnt);
    }

    /**
     * Crfbtfs b nfw dlbss lobdfr using tif <tt>ClbssLobdfr</tt> rfturnfd by
     * tif mftiod {@link #gftSystfmClbssLobdfr()
     * <tt>gftSystfmClbssLobdfr()</tt>} bs tif pbrfnt dlbss lobdfr.
     *
     * <p> If tifrf is b sfdurity mbnbgfr, its {@link
     * SfdurityMbnbgfr#difdkCrfbtfClbssLobdfr()
     * <tt>difdkCrfbtfClbssLobdfr</tt>} mftiod is invokfd.  Tiis mby rfsult in
     * b sfdurity fxdfption.  </p>
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr fxists bnd its
     *          <tt>difdkCrfbtfClbssLobdfr</tt> mftiod dofsn't bllow drfbtion
     *          of b nfw dlbss lobdfr.
     */
    protfdtfd ClbssLobdfr() {
        tiis(difdkCrfbtfClbssLobdfr(), gftSystfmClbssLobdfr());
    }

    // -- Clbss --

    /**
     * Lobds tif dlbss witi tif spfdififd <b irff="#nbmf">binbry nbmf</b>.
     * Tiis mftiod sfbrdifs for dlbssfs in tif sbmf mbnnfr bs tif {@link
     * #lobdClbss(String, boolfbn)} mftiod.  It is invokfd by tif Jbvb virtubl
     * mbdiinf to rfsolvf dlbss rfffrfndfs.  Invoking tiis mftiod is fquivblfnt
     * to invoking {@link #lobdClbss(String, boolfbn) <tt>lobdClbss(nbmf,
     * fblsf)</tt>}.
     *
     * @pbrbm  nbmf
     *         Tif <b irff="#nbmf">binbry nbmf</b> of tif dlbss
     *
     * @rfturn  Tif rfsulting <tt>Clbss</tt> objfdt
     *
     * @tirows  ClbssNotFoundExdfption
     *          If tif dlbss wbs not found
     */
    publid Clbss<?> lobdClbss(String nbmf) tirows ClbssNotFoundExdfption {
        rfturn lobdClbss(nbmf, fblsf);
    }

    /**
     * Lobds tif dlbss witi tif spfdififd <b irff="#nbmf">binbry nbmf</b>.  Tif
     * dffbult implfmfntbtion of tiis mftiod sfbrdifs for dlbssfs in tif
     * following ordfr:
     *
     * <ol>
     *
     *   <li><p> Invokf {@link #findLobdfdClbss(String)} to difdk if tif dlbss
     *   ibs blrfbdy bffn lobdfd.  </p></li>
     *
     *   <li><p> Invokf tif {@link #lobdClbss(String) <tt>lobdClbss</tt>} mftiod
     *   on tif pbrfnt dlbss lobdfr.  If tif pbrfnt is <tt>null</tt> tif dlbss
     *   lobdfr built-in to tif virtubl mbdiinf is usfd, instfbd.  </p></li>
     *
     *   <li><p> Invokf tif {@link #findClbss(String)} mftiod to find tif
     *   dlbss.  </p></li>
     *
     * </ol>
     *
     * <p> If tif dlbss wbs found using tif bbovf stfps, bnd tif
     * <tt>rfsolvf</tt> flbg is truf, tiis mftiod will tifn invokf tif {@link
     * #rfsolvfClbss(Clbss)} mftiod on tif rfsulting <tt>Clbss</tt> objfdt.
     *
     * <p> Subdlbssfs of <tt>ClbssLobdfr</tt> brf fndourbgfd to ovfrridf {@link
     * #findClbss(String)}, rbtifr tibn tiis mftiod.  </p>
     *
     * <p> Unlfss ovfrriddfn, tiis mftiod syndironizfs on tif rfsult of
     * {@link #gftClbssLobdingLodk <tt>gftClbssLobdingLodk</tt>} mftiod
     * during tif fntirf dlbss lobding prodfss.
     *
     * @pbrbm  nbmf
     *         Tif <b irff="#nbmf">binbry nbmf</b> of tif dlbss
     *
     * @pbrbm  rfsolvf
     *         If <tt>truf</tt> tifn rfsolvf tif dlbss
     *
     * @rfturn  Tif rfsulting <tt>Clbss</tt> objfdt
     *
     * @tirows  ClbssNotFoundExdfption
     *          If tif dlbss dould not bf found
     */
    protfdtfd Clbss<?> lobdClbss(String nbmf, boolfbn rfsolvf)
        tirows ClbssNotFoundExdfption
    {
        syndironizfd (gftClbssLobdingLodk(nbmf)) {
            // First, difdk if tif dlbss ibs blrfbdy bffn lobdfd
            Clbss<?> d = findLobdfdClbss(nbmf);
            if (d == null) {
                long t0 = Systfm.nbnoTimf();
                try {
                    if (pbrfnt != null) {
                        d = pbrfnt.lobdClbss(nbmf, fblsf);
                    } flsf {
                        d = findBootstrbpClbssOrNull(nbmf);
                    }
                } dbtdi (ClbssNotFoundExdfption f) {
                    // ClbssNotFoundExdfption tirown if dlbss not found
                    // from tif non-null pbrfnt dlbss lobdfr
                }

                if (d == null) {
                    // If still not found, tifn invokf findClbss in ordfr
                    // to find tif dlbss.
                    long t1 = Systfm.nbnoTimf();
                    d = findClbss(nbmf);

                    // tiis is tif dffining dlbss lobdfr; rfdord tif stbts
                    sun.misd.PfrfCountfr.gftPbrfntDflfgbtionTimf().bddTimf(t1 - t0);
                    sun.misd.PfrfCountfr.gftFindClbssTimf().bddElbpsfdTimfFrom(t1);
                    sun.misd.PfrfCountfr.gftFindClbssfs().indrfmfnt();
                }
            }
            if (rfsolvf) {
                rfsolvfClbss(d);
            }
            rfturn d;
        }
    }

    /**
     * Rfturns tif lodk objfdt for dlbss lobding opfrbtions.
     * For bbdkwbrd dompbtibility, tif dffbult implfmfntbtion of tiis mftiod
     * bfibvfs bs follows. If tiis ClbssLobdfr objfdt is rfgistfrfd bs
     * pbrbllfl dbpbblf, tif mftiod rfturns b dfdidbtfd objfdt bssodibtfd
     * witi tif spfdififd dlbss nbmf. Otifrwisf, tif mftiod rfturns tiis
     * ClbssLobdfr objfdt.
     *
     * @pbrbm  dlbssNbmf
     *         Tif nbmf of tif to-bf-lobdfd dlbss
     *
     * @rfturn tif lodk for dlbss lobding opfrbtions
     *
     * @tirows NullPointfrExdfption
     *         If rfgistfrfd bs pbrbllfl dbpbblf bnd <tt>dlbssNbmf</tt> is null
     *
     * @sff #lobdClbss(String, boolfbn)
     *
     * @sindf  1.7
     */
    protfdtfd Objfdt gftClbssLobdingLodk(String dlbssNbmf) {
        Objfdt lodk = tiis;
        if (pbrbllflLodkMbp != null) {
            Objfdt nfwLodk = nfw Objfdt();
            lodk = pbrbllflLodkMbp.putIfAbsfnt(dlbssNbmf, nfwLodk);
            if (lodk == null) {
                lodk = nfwLodk;
            }
        }
        rfturn lodk;
    }

    // Tiis mftiod is invokfd by tif virtubl mbdiinf to lobd b dlbss.
    privbtf Clbss<?> lobdClbssIntfrnbl(String nbmf)
        tirows ClbssNotFoundExdfption
    {
        // For bbdkwbrd dompbtibility, fxpliditly lodk on 'tiis' wifn
        // tif durrfnt dlbss lobdfr is not pbrbllfl dbpbblf.
        if (pbrbllflLodkMbp == null) {
            syndironizfd (tiis) {
                 rfturn lobdClbss(nbmf);
            }
        } flsf {
            rfturn lobdClbss(nbmf);
        }
    }

    // Invokfd by tif VM bftfr lobding dlbss witi tiis lobdfr.
    privbtf void difdkPbdkbgfAddfss(Clbss<?> dls, ProtfdtionDombin pd) {
        finbl SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            if (RfflfdtUtil.isNonPublidProxyClbss(dls)) {
                for (Clbss<?> intf: dls.gftIntfrfbdfs()) {
                    difdkPbdkbgfAddfss(intf, pd);
                }
                rfturn;
            }

            finbl String nbmf = dls.gftNbmf();
            finbl int i = nbmf.lbstIndfxOf('.');
            if (i != -1) {
                AddfssControllfr.doPrivilfgfd(nfw PrivilfgfdAdtion<Void>() {
                    publid Void run() {
                        sm.difdkPbdkbgfAddfss(nbmf.substring(0, i));
                        rfturn null;
                    }
                }, nfw AddfssControlContfxt(nfw ProtfdtionDombin[] {pd}));
            }
        }
        dombins.bdd(pd);
    }

    /**
     * Finds tif dlbss witi tif spfdififd <b irff="#nbmf">binbry nbmf</b>.
     * Tiis mftiod siould bf ovfrriddfn by dlbss lobdfr implfmfntbtions tibt
     * follow tif dflfgbtion modfl for lobding dlbssfs, bnd will bf invokfd by
     * tif {@link #lobdClbss <tt>lobdClbss</tt>} mftiod bftfr difdking tif
     * pbrfnt dlbss lobdfr for tif rfqufstfd dlbss.  Tif dffbult implfmfntbtion
     * tirows b <tt>ClbssNotFoundExdfption</tt>.
     *
     * @pbrbm  nbmf
     *         Tif <b irff="#nbmf">binbry nbmf</b> of tif dlbss
     *
     * @rfturn  Tif rfsulting <tt>Clbss</tt> objfdt
     *
     * @tirows  ClbssNotFoundExdfption
     *          If tif dlbss dould not bf found
     *
     * @sindf  1.2
     */
    protfdtfd Clbss<?> findClbss(String nbmf) tirows ClbssNotFoundExdfption {
        tirow nfw ClbssNotFoundExdfption(nbmf);
    }

    /**
     * Convfrts bn brrby of bytfs into bn instbndf of dlbss <tt>Clbss</tt>.
     * Bfforf tif <tt>Clbss</tt> dbn bf usfd it must bf rfsolvfd.  Tiis mftiod
     * is dfprfdbtfd in fbvor of tif vfrsion tibt tbkfs b <b
     * irff="#nbmf">binbry nbmf</b> bs its first brgumfnt, bnd is morf sfdurf.
     *
     * @pbrbm  b
     *         Tif bytfs tibt mbkf up tif dlbss dbtb.  Tif bytfs in positions
     *         <tt>off</tt> tirougi <tt>off+lfn-1</tt> siould ibvf tif formbt
     *         of b vblid dlbss filf bs dffinfd by
     *         <ditf>Tif Jbvb&trbdf; Virtubl Mbdiinf Spfdifidbtion</ditf>.
     *
     * @pbrbm  off
     *         Tif stbrt offsft in <tt>b</tt> of tif dlbss dbtb
     *
     * @pbrbm  lfn
     *         Tif lfngti of tif dlbss dbtb
     *
     * @rfturn  Tif <tt>Clbss</tt> objfdt tibt wbs drfbtfd from tif spfdififd
     *          dlbss dbtb
     *
     * @tirows  ClbssFormbtError
     *          If tif dbtb did not dontbin b vblid dlbss
     *
     * @tirows  IndfxOutOfBoundsExdfption
     *          If fitifr <tt>off</tt> or <tt>lfn</tt> is nfgbtivf, or if
     *          <tt>off+lfn</tt> is grfbtfr tibn <tt>b.lfngti</tt>.
     *
     * @tirows  SfdurityExdfption
     *          If bn bttfmpt is mbdf to bdd tiis dlbss to b pbdkbgf tibt
     *          dontbins dlbssfs tibt wfrf signfd by b difffrfnt sft of
     *          dfrtifidbtfs tibn tiis dlbss, or if bn bttfmpt is mbdf
     *          to dffinf b dlbss in b pbdkbgf witi b fully-qublififd nbmf
     *          tibt stbrts witi "{@dodf jbvb.}".
     *
     * @sff  #lobdClbss(String, boolfbn)
     * @sff  #rfsolvfClbss(Clbss)
     *
     * @dfprfdbtfd  Rfplbdfd by {@link #dffinfClbss(String, bytf[], int, int)
     * dffinfClbss(String, bytf[], int, int)}
     */
    @Dfprfdbtfd
    protfdtfd finbl Clbss<?> dffinfClbss(bytf[] b, int off, int lfn)
        tirows ClbssFormbtError
    {
        rfturn dffinfClbss(null, b, off, lfn, null);
    }

    /**
     * Convfrts bn brrby of bytfs into bn instbndf of dlbss <tt>Clbss</tt>.
     * Bfforf tif <tt>Clbss</tt> dbn bf usfd it must bf rfsolvfd.
     *
     * <p> Tiis mftiod bssigns b dffbult {@link jbvb.sfdurity.ProtfdtionDombin
     * <tt>ProtfdtionDombin</tt>} to tif nfwly dffinfd dlbss.  Tif
     * <tt>ProtfdtionDombin</tt> is ffffdtivfly grbntfd tif sbmf sft of
     * pfrmissions rfturnfd wifn {@link
     * jbvb.sfdurity.Polidy#gftPfrmissions(jbvb.sfdurity.CodfSourdf)
     * <tt>Polidy.gftPolidy().gftPfrmissions(nfw CodfSourdf(null, null))</tt>}
     * is invokfd.  Tif dffbult dombin is drfbtfd on tif first invodbtion of
     * {@link #dffinfClbss(String, bytf[], int, int) <tt>dffinfClbss</tt>},
     * bnd rf-usfd on subsfqufnt invodbtions.
     *
     * <p> To bssign b spfdifid <tt>ProtfdtionDombin</tt> to tif dlbss, usf
     * tif {@link #dffinfClbss(String, bytf[], int, int,
     * jbvb.sfdurity.ProtfdtionDombin) <tt>dffinfClbss</tt>} mftiod tibt tbkfs b
     * <tt>ProtfdtionDombin</tt> bs onf of its brgumfnts.  </p>
     *
     * @pbrbm  nbmf
     *         Tif fxpfdtfd <b irff="#nbmf">binbry nbmf</b> of tif dlbss, or
     *         <tt>null</tt> if not known
     *
     * @pbrbm  b
     *         Tif bytfs tibt mbkf up tif dlbss dbtb.  Tif bytfs in positions
     *         <tt>off</tt> tirougi <tt>off+lfn-1</tt> siould ibvf tif formbt
     *         of b vblid dlbss filf bs dffinfd by
     *         <ditf>Tif Jbvb&trbdf; Virtubl Mbdiinf Spfdifidbtion</ditf>.
     *
     * @pbrbm  off
     *         Tif stbrt offsft in <tt>b</tt> of tif dlbss dbtb
     *
     * @pbrbm  lfn
     *         Tif lfngti of tif dlbss dbtb
     *
     * @rfturn  Tif <tt>Clbss</tt> objfdt tibt wbs drfbtfd from tif spfdififd
     *          dlbss dbtb.
     *
     * @tirows  ClbssFormbtError
     *          If tif dbtb did not dontbin b vblid dlbss
     *
     * @tirows  IndfxOutOfBoundsExdfption
     *          If fitifr <tt>off</tt> or <tt>lfn</tt> is nfgbtivf, or if
     *          <tt>off+lfn</tt> is grfbtfr tibn <tt>b.lfngti</tt>.
     *
     * @tirows  SfdurityExdfption
     *          If bn bttfmpt is mbdf to bdd tiis dlbss to b pbdkbgf tibt
     *          dontbins dlbssfs tibt wfrf signfd by b difffrfnt sft of
     *          dfrtifidbtfs tibn tiis dlbss (wiidi is unsignfd), or if
     *          <tt>nbmf</tt> bfgins witi "<tt>jbvb.</tt>".
     *
     * @sff  #lobdClbss(String, boolfbn)
     * @sff  #rfsolvfClbss(Clbss)
     * @sff  jbvb.sfdurity.CodfSourdf
     * @sff  jbvb.sfdurity.SfdurfClbssLobdfr
     *
     * @sindf  1.1
     */
    protfdtfd finbl Clbss<?> dffinfClbss(String nbmf, bytf[] b, int off, int lfn)
        tirows ClbssFormbtError
    {
        rfturn dffinfClbss(nbmf, b, off, lfn, null);
    }

    /* Dftfrminf protfdtion dombin, bnd difdk tibt:
        - not dffinf jbvb.* dlbss,
        - signfr of tiis dlbss mbtdifs signfrs for tif rfst of tif dlbssfs in
          pbdkbgf.
    */
    privbtf ProtfdtionDombin prfDffinfClbss(String nbmf,
                                            ProtfdtionDombin pd)
    {
        if (!difdkNbmf(nbmf))
            tirow nfw NoClbssDffFoundError("IllfgblNbmf: " + nbmf);

        if ((nbmf != null) && nbmf.stbrtsWiti("jbvb.")) {
            tirow nfw SfdurityExdfption
                ("Proiibitfd pbdkbgf nbmf: " +
                 nbmf.substring(0, nbmf.lbstIndfxOf('.')));
        }
        if (pd == null) {
            pd = dffbultDombin;
        }

        if (nbmf != null) difdkCfrts(nbmf, pd.gftCodfSourdf());

        rfturn pd;
    }

    privbtf String dffinfClbssSourdfLodbtion(ProtfdtionDombin pd)
    {
        CodfSourdf ds = pd.gftCodfSourdf();
        String sourdf = null;
        if (ds != null && ds.gftLodbtion() != null) {
            sourdf = ds.gftLodbtion().toString();
        }
        rfturn sourdf;
    }

    privbtf void postDffinfClbss(Clbss<?> d, ProtfdtionDombin pd)
    {
        if (pd.gftCodfSourdf() != null) {
            Cfrtifidbtf dfrts[] = pd.gftCodfSourdf().gftCfrtifidbtfs();
            if (dfrts != null)
                sftSignfrs(d, dfrts);
        }
    }

    /**
     * Convfrts bn brrby of bytfs into bn instbndf of dlbss <tt>Clbss</tt>,
     * witi bn optionbl <tt>ProtfdtionDombin</tt>.  If tif dombin is
     * <tt>null</tt>, tifn b dffbult dombin will bf bssignfd to tif dlbss bs
     * spfdififd in tif dodumfntbtion for {@link #dffinfClbss(String, bytf[],
     * int, int)}.  Bfforf tif dlbss dbn bf usfd it must bf rfsolvfd.
     *
     * <p> Tif first dlbss dffinfd in b pbdkbgf dftfrminfs tif fxbdt sft of
     * dfrtifidbtfs tibt bll subsfqufnt dlbssfs dffinfd in tibt pbdkbgf must
     * dontbin.  Tif sft of dfrtifidbtfs for b dlbss is obtbinfd from tif
     * {@link jbvb.sfdurity.CodfSourdf <tt>CodfSourdf</tt>} witiin tif
     * <tt>ProtfdtionDombin</tt> of tif dlbss.  Any dlbssfs bddfd to tibt
     * pbdkbgf must dontbin tif sbmf sft of dfrtifidbtfs or b
     * <tt>SfdurityExdfption</tt> will bf tirown.  Notf tibt if
     * <tt>nbmf</tt> is <tt>null</tt>, tiis difdk is not pfrformfd.
     * You siould blwbys pbss in tif <b irff="#nbmf">binbry nbmf</b> of tif
     * dlbss you brf dffining bs wfll bs tif bytfs.  Tiis fnsurfs tibt tif
     * dlbss you brf dffining is indffd tif dlbss you tiink it is.
     *
     * <p> Tif spfdififd <tt>nbmf</tt> dbnnot bfgin witi "<tt>jbvb.</tt>", sindf
     * bll dlbssfs in tif "<tt>jbvb.*</tt> pbdkbgfs dbn only bf dffinfd by tif
     * bootstrbp dlbss lobdfr.  If <tt>nbmf</tt> is not <tt>null</tt>, it
     * must bf fqubl to tif <b irff="#nbmf">binbry nbmf</b> of tif dlbss
     * spfdififd by tif bytf brrby "<tt>b</tt>", otifrwisf b {@link
     * NoClbssDffFoundError <tt>NoClbssDffFoundError</tt>} will bf tirown. </p>
     *
     * @pbrbm  nbmf
     *         Tif fxpfdtfd <b irff="#nbmf">binbry nbmf</b> of tif dlbss, or
     *         <tt>null</tt> if not known
     *
     * @pbrbm  b
     *         Tif bytfs tibt mbkf up tif dlbss dbtb. Tif bytfs in positions
     *         <tt>off</tt> tirougi <tt>off+lfn-1</tt> siould ibvf tif formbt
     *         of b vblid dlbss filf bs dffinfd by
     *         <ditf>Tif Jbvb&trbdf; Virtubl Mbdiinf Spfdifidbtion</ditf>.
     *
     * @pbrbm  off
     *         Tif stbrt offsft in <tt>b</tt> of tif dlbss dbtb
     *
     * @pbrbm  lfn
     *         Tif lfngti of tif dlbss dbtb
     *
     * @pbrbm  protfdtionDombin
     *         Tif ProtfdtionDombin of tif dlbss
     *
     * @rfturn  Tif <tt>Clbss</tt> objfdt drfbtfd from tif dbtb,
     *          bnd optionbl <tt>ProtfdtionDombin</tt>.
     *
     * @tirows  ClbssFormbtError
     *          If tif dbtb did not dontbin b vblid dlbss
     *
     * @tirows  NoClbssDffFoundError
     *          If <tt>nbmf</tt> is not fqubl to tif <b irff="#nbmf">binbry
     *          nbmf</b> of tif dlbss spfdififd by <tt>b</tt>
     *
     * @tirows  IndfxOutOfBoundsExdfption
     *          If fitifr <tt>off</tt> or <tt>lfn</tt> is nfgbtivf, or if
     *          <tt>off+lfn</tt> is grfbtfr tibn <tt>b.lfngti</tt>.
     *
     * @tirows  SfdurityExdfption
     *          If bn bttfmpt is mbdf to bdd tiis dlbss to b pbdkbgf tibt
     *          dontbins dlbssfs tibt wfrf signfd by b difffrfnt sft of
     *          dfrtifidbtfs tibn tiis dlbss, or if <tt>nbmf</tt> bfgins witi
     *          "<tt>jbvb.</tt>".
     */
    protfdtfd finbl Clbss<?> dffinfClbss(String nbmf, bytf[] b, int off, int lfn,
                                         ProtfdtionDombin protfdtionDombin)
        tirows ClbssFormbtError
    {
        protfdtionDombin = prfDffinfClbss(nbmf, protfdtionDombin);
        String sourdf = dffinfClbssSourdfLodbtion(protfdtionDombin);
        Clbss<?> d = dffinfClbss1(nbmf, b, off, lfn, protfdtionDombin, sourdf);
        postDffinfClbss(d, protfdtionDombin);
        rfturn d;
    }

    /**
     * Convfrts b {@link jbvb.nio.BytfBufffr <tt>BytfBufffr</tt>}
     * into bn instbndf of dlbss <tt>Clbss</tt>,
     * witi bn optionbl <tt>ProtfdtionDombin</tt>.  If tif dombin is
     * <tt>null</tt>, tifn b dffbult dombin will bf bssignfd to tif dlbss bs
     * spfdififd in tif dodumfntbtion for {@link #dffinfClbss(String, bytf[],
     * int, int)}.  Bfforf tif dlbss dbn bf usfd it must bf rfsolvfd.
     *
     * <p>Tif rulfs bbout tif first dlbss dffinfd in b pbdkbgf dftfrmining tif
     * sft of dfrtifidbtfs for tif pbdkbgf, bnd tif rfstridtions on dlbss nbmfs
     * brf idfntidbl to tiosf spfdififd in tif dodumfntbtion for {@link
     * #dffinfClbss(String, bytf[], int, int, ProtfdtionDombin)}.
     *
     * <p> An invodbtion of tiis mftiod of tif form
     * <i>dl</i><tt>.dffinfClbss(</tt><i>nbmf</i><tt>,</tt>
     * <i>bBufffr</i><tt>,</tt> <i>pd</i><tt>)</tt> yiflds fxbdtly tif sbmf
     * rfsult bs tif stbtfmfnts
     *
     *<p> <tt>
     * ...<br>
     * bytf[] tfmp = nfw bytf[bBufffr.{@link
     * jbvb.nio.BytfBufffr#rfmbining rfmbining}()];<br>
     *     bBufffr.{@link jbvb.nio.BytfBufffr#gft(bytf[])
     * gft}(tfmp);<br>
     *     rfturn {@link #dffinfClbss(String, bytf[], int, int, ProtfdtionDombin)
     * dl.dffinfClbss}(nbmf, tfmp, 0,
     * tfmp.lfngti, pd);<br>
     * </tt></p>
     *
     * @pbrbm  nbmf
     *         Tif fxpfdtfd <b irff="#nbmf">binbry nbmf</b>. of tif dlbss, or
     *         <tt>null</tt> if not known
     *
     * @pbrbm  b
     *         Tif bytfs tibt mbkf up tif dlbss dbtb. Tif bytfs from positions
     *         <tt>b.position()</tt> tirougi <tt>b.position() + b.limit() -1
     *         </tt> siould ibvf tif formbt of b vblid dlbss filf bs dffinfd by
     *         <ditf>Tif Jbvb&trbdf; Virtubl Mbdiinf Spfdifidbtion</ditf>.
     *
     * @pbrbm  protfdtionDombin
     *         Tif ProtfdtionDombin of tif dlbss, or <tt>null</tt>.
     *
     * @rfturn  Tif <tt>Clbss</tt> objfdt drfbtfd from tif dbtb,
     *          bnd optionbl <tt>ProtfdtionDombin</tt>.
     *
     * @tirows  ClbssFormbtError
     *          If tif dbtb did not dontbin b vblid dlbss.
     *
     * @tirows  NoClbssDffFoundError
     *          If <tt>nbmf</tt> is not fqubl to tif <b irff="#nbmf">binbry
     *          nbmf</b> of tif dlbss spfdififd by <tt>b</tt>
     *
     * @tirows  SfdurityExdfption
     *          If bn bttfmpt is mbdf to bdd tiis dlbss to b pbdkbgf tibt
     *          dontbins dlbssfs tibt wfrf signfd by b difffrfnt sft of
     *          dfrtifidbtfs tibn tiis dlbss, or if <tt>nbmf</tt> bfgins witi
     *          "<tt>jbvb.</tt>".
     *
     * @sff      #dffinfClbss(String, bytf[], int, int, ProtfdtionDombin)
     *
     * @sindf  1.5
     */
    protfdtfd finbl Clbss<?> dffinfClbss(String nbmf, jbvb.nio.BytfBufffr b,
                                         ProtfdtionDombin protfdtionDombin)
        tirows ClbssFormbtError
    {
        int lfn = b.rfmbining();

        // Usf bytf[] if not b dirfdt BytfBuffr:
        if (!b.isDirfdt()) {
            if (b.ibsArrby()) {
                rfturn dffinfClbss(nbmf, b.brrby(),
                                   b.position() + b.brrbyOffsft(), lfn,
                                   protfdtionDombin);
            } flsf {
                // no brrby, or rfbd-only brrby
                bytf[] tb = nfw bytf[lfn];
                b.gft(tb);  // gft bytfs out of bytf bufffr.
                rfturn dffinfClbss(nbmf, tb, 0, lfn, protfdtionDombin);
            }
        }

        protfdtionDombin = prfDffinfClbss(nbmf, protfdtionDombin);
        String sourdf = dffinfClbssSourdfLodbtion(protfdtionDombin);
        Clbss<?> d = dffinfClbss2(nbmf, b, b.position(), lfn, protfdtionDombin, sourdf);
        postDffinfClbss(d, protfdtionDombin);
        rfturn d;
    }

    privbtf nbtivf Clbss<?> dffinfClbss1(String nbmf, bytf[] b, int off, int lfn,
                                         ProtfdtionDombin pd, String sourdf);

    privbtf nbtivf Clbss<?> dffinfClbss2(String nbmf, jbvb.nio.BytfBufffr b,
                                         int off, int lfn, ProtfdtionDombin pd,
                                         String sourdf);

    // truf if tif nbmf is null or ibs tif potfntibl to bf b vblid binbry nbmf
    privbtf boolfbn difdkNbmf(String nbmf) {
        if ((nbmf == null) || (nbmf.lfngti() == 0))
            rfturn truf;
        if ((nbmf.indfxOf('/') != -1) || (nbmf.dibrAt(0) == '['))
            rfturn fblsf;
        rfturn truf;
    }

    privbtf void difdkCfrts(String nbmf, CodfSourdf ds) {
        int i = nbmf.lbstIndfxOf('.');
        String pnbmf = (i == -1) ? "" : nbmf.substring(0, i);

        Cfrtifidbtf[] dfrts = null;
        if (ds != null) {
            dfrts = ds.gftCfrtifidbtfs();
        }
        Cfrtifidbtf[] pdfrts = null;
        if (pbrbllflLodkMbp == null) {
            syndironizfd (tiis) {
                pdfrts = pbdkbgf2dfrts.gft(pnbmf);
                if (pdfrts == null) {
                    pbdkbgf2dfrts.put(pnbmf, (dfrts == null? nodfrts:dfrts));
                }
            }
        } flsf {
            pdfrts = ((CondurrfntHbsiMbp<String, Cfrtifidbtf[]>)pbdkbgf2dfrts).
                putIfAbsfnt(pnbmf, (dfrts == null? nodfrts:dfrts));
        }
        if (pdfrts != null && !dompbrfCfrts(pdfrts, dfrts)) {
            tirow nfw SfdurityExdfption("dlbss \""+ nbmf +
                 "\"'s signfr informbtion dofs not mbtdi signfr informbtion of otifr dlbssfs in tif sbmf pbdkbgf");
        }
    }

    /**
     * difdk to mbkf surf tif dfrts for tif nfw dlbss (dfrts) brf tif sbmf bs
     * tif dfrts for tif first dlbss insfrtfd in tif pbdkbgf (pdfrts)
     */
    privbtf boolfbn dompbrfCfrts(Cfrtifidbtf[] pdfrts,
                                 Cfrtifidbtf[] dfrts)
    {
        // dfrts dbn bf null, indidbting no dfrts.
        if ((dfrts == null) || (dfrts.lfngti == 0)) {
            rfturn pdfrts.lfngti == 0;
        }

        // tif lfngti must bf tif sbmf bt tiis point
        if (dfrts.lfngti != pdfrts.lfngti)
            rfturn fblsf;

        // go tirougi bnd mbkf surf bll tif dfrts in onf brrby
        // brf in tif otifr bnd vidf-vfrsb.
        boolfbn mbtdi;
        for (Cfrtifidbtf dfrt : dfrts) {
            mbtdi = fblsf;
            for (Cfrtifidbtf pdfrt : pdfrts) {
                if (dfrt.fqubls(pdfrt)) {
                    mbtdi = truf;
                    brfbk;
                }
            }
            if (!mbtdi) rfturn fblsf;
        }

        // now do tif sbmf for pdfrts
        for (Cfrtifidbtf pdfrt : pdfrts) {
            mbtdi = fblsf;
            for (Cfrtifidbtf dfrt : dfrts) {
                if (pdfrt.fqubls(dfrt)) {
                    mbtdi = truf;
                    brfbk;
                }
            }
            if (!mbtdi) rfturn fblsf;
        }

        rfturn truf;
    }

    /**
     * Links tif spfdififd dlbss.  Tiis (mislfbdingly nbmfd) mftiod mby bf
     * usfd by b dlbss lobdfr to link b dlbss.  If tif dlbss <tt>d</tt> ibs
     * blrfbdy bffn linkfd, tifn tiis mftiod simply rfturns. Otifrwisf, tif
     * dlbss is linkfd bs dfsdribfd in tif "Exfdution" dibptfr of
     * <ditf>Tif Jbvb&trbdf; Lbngubgf Spfdifidbtion</ditf>.
     *
     * @pbrbm  d
     *         Tif dlbss to link
     *
     * @tirows  NullPointfrExdfption
     *          If <tt>d</tt> is <tt>null</tt>.
     *
     * @sff  #dffinfClbss(String, bytf[], int, int)
     */
    protfdtfd finbl void rfsolvfClbss(Clbss<?> d) {
        rfsolvfClbss0(d);
    }

    privbtf nbtivf void rfsolvfClbss0(Clbss<?> d);

    /**
     * Finds b dlbss witi tif spfdififd <b irff="#nbmf">binbry nbmf</b>,
     * lobding it if nfdfssbry.
     *
     * <p> Tiis mftiod lobds tif dlbss tirougi tif systfm dlbss lobdfr (sff
     * {@link #gftSystfmClbssLobdfr()}).  Tif <tt>Clbss</tt> objfdt rfturnfd
     * migit ibvf morf tibn onf <tt>ClbssLobdfr</tt> bssodibtfd witi it.
     * Subdlbssfs of <tt>ClbssLobdfr</tt> nffd not usublly invokf tiis mftiod,
     * bfdbusf most dlbss lobdfrs nffd to ovfrridf just {@link
     * #findClbss(String)}.  </p>
     *
     * @pbrbm  nbmf
     *         Tif <b irff="#nbmf">binbry nbmf</b> of tif dlbss
     *
     * @rfturn  Tif <tt>Clbss</tt> objfdt for tif spfdififd <tt>nbmf</tt>
     *
     * @tirows  ClbssNotFoundExdfption
     *          If tif dlbss dould not bf found
     *
     * @sff  #ClbssLobdfr(ClbssLobdfr)
     * @sff  #gftPbrfnt()
     */
    protfdtfd finbl Clbss<?> findSystfmClbss(String nbmf)
        tirows ClbssNotFoundExdfption
    {
        ClbssLobdfr systfm = gftSystfmClbssLobdfr();
        if (systfm == null) {
            if (!difdkNbmf(nbmf))
                tirow nfw ClbssNotFoundExdfption(nbmf);
            Clbss<?> dls = findBootstrbpClbss(nbmf);
            if (dls == null) {
                tirow nfw ClbssNotFoundExdfption(nbmf);
            }
            rfturn dls;
        }
        rfturn systfm.lobdClbss(nbmf);
    }

    /**
     * Rfturns b dlbss lobdfd by tif bootstrbp dlbss lobdfr;
     * or rfturn null if not found.
     */
    privbtf Clbss<?> findBootstrbpClbssOrNull(String nbmf)
    {
        if (!difdkNbmf(nbmf)) rfturn null;

        rfturn findBootstrbpClbss(nbmf);
    }

    // rfturn null if not found
    privbtf nbtivf Clbss<?> findBootstrbpClbss(String nbmf);

    /**
     * Rfturns tif dlbss witi tif givfn <b irff="#nbmf">binbry nbmf</b> if tiis
     * lobdfr ibs bffn rfdordfd by tif Jbvb virtubl mbdiinf bs bn initibting
     * lobdfr of b dlbss witi tibt <b irff="#nbmf">binbry nbmf</b>.  Otifrwisf
     * <tt>null</tt> is rfturnfd.
     *
     * @pbrbm  nbmf
     *         Tif <b irff="#nbmf">binbry nbmf</b> of tif dlbss
     *
     * @rfturn  Tif <tt>Clbss</tt> objfdt, or <tt>null</tt> if tif dlbss ibs
     *          not bffn lobdfd
     *
     * @sindf  1.1
     */
    protfdtfd finbl Clbss<?> findLobdfdClbss(String nbmf) {
        if (!difdkNbmf(nbmf))
            rfturn null;
        rfturn findLobdfdClbss0(nbmf);
    }

    privbtf nbtivf finbl Clbss<?> findLobdfdClbss0(String nbmf);

    /**
     * Sfts tif signfrs of b dlbss.  Tiis siould bf invokfd bftfr dffining b
     * dlbss.
     *
     * @pbrbm  d
     *         Tif <tt>Clbss</tt> objfdt
     *
     * @pbrbm  signfrs
     *         Tif signfrs for tif dlbss
     *
     * @sindf  1.1
     */
    protfdtfd finbl void sftSignfrs(Clbss<?> d, Objfdt[] signfrs) {
        d.sftSignfrs(signfrs);
    }


    // -- Rfsourdf --

    /**
     * Finds tif rfsourdf witi tif givfn nbmf.  A rfsourdf is somf dbtb
     * (imbgfs, budio, tfxt, ftd) tibt dbn bf bddfssfd by dlbss dodf in b wby
     * tibt is indfpfndfnt of tif lodbtion of tif dodf.
     *
     * <p> Tif nbmf of b rfsourdf is b '<tt>/</tt>'-sfpbrbtfd pbti nbmf tibt
     * idfntififs tif rfsourdf.
     *
     * <p> Tiis mftiod will first sfbrdi tif pbrfnt dlbss lobdfr for tif
     * rfsourdf; if tif pbrfnt is <tt>null</tt> tif pbti of tif dlbss lobdfr
     * built-in to tif virtubl mbdiinf is sfbrdifd.  Tibt fbiling, tiis mftiod
     * will invokf {@link #findRfsourdf(String)} to find tif rfsourdf.  </p>
     *
     * @bpiNotf Wifn ovfrriding tiis mftiod it is rfdommfndfd tibt bn
     * implfmfntbtion fnsurfs tibt bny dflfgbtion is donsistfnt witi tif {@link
     * #gftRfsourdfs(jbvb.lbng.String) gftRfsourdfs(String)} mftiod.
     *
     * @pbrbm  nbmf
     *         Tif rfsourdf nbmf
     *
     * @rfturn  A <tt>URL</tt> objfdt for rfbding tif rfsourdf, or
     *          <tt>null</tt> if tif rfsourdf dould not bf found or tif invokfr
     *          dofsn't ibvf bdfqubtf  privilfgfs to gft tif rfsourdf.
     *
     * @sindf  1.1
     */
    publid URL gftRfsourdf(String nbmf) {
        URL url;
        if (pbrfnt != null) {
            url = pbrfnt.gftRfsourdf(nbmf);
        } flsf {
            url = gftBootstrbpRfsourdf(nbmf);
        }
        if (url == null) {
            url = findRfsourdf(nbmf);
        }
        rfturn url;
    }

    /**
     * Finds bll tif rfsourdfs witi tif givfn nbmf. A rfsourdf is somf dbtb
     * (imbgfs, budio, tfxt, ftd) tibt dbn bf bddfssfd by dlbss dodf in b wby
     * tibt is indfpfndfnt of tif lodbtion of tif dodf.
     *
     * <p>Tif nbmf of b rfsourdf is b <tt>/</tt>-sfpbrbtfd pbti nbmf tibt
     * idfntififs tif rfsourdf.
     *
     * <p> Tif sfbrdi ordfr is dfsdribfd in tif dodumfntbtion for {@link
     * #gftRfsourdf(String)}.  </p>
     *
     * @bpiNotf Wifn ovfrriding tiis mftiod it is rfdommfndfd tibt bn
     * implfmfntbtion fnsurfs tibt bny dflfgbtion is donsistfnt witi tif {@link
     * #gftRfsourdf(jbvb.lbng.String) gftRfsourdf(String)} mftiod. Tiis siould
     * fnsurf tibt tif first flfmfnt rfturnfd by tif Enumfrbtion's
     * {@dodf nfxtElfmfnt} mftiod is tif sbmf rfsourdf tibt tif
     * {@dodf gftRfsourdf(String)} mftiod would rfturn.
     *
     * @pbrbm  nbmf
     *         Tif rfsourdf nbmf
     *
     * @rfturn  An fnumfrbtion of {@link jbvb.nft.URL <tt>URL</tt>} objfdts for
     *          tif rfsourdf.  If no rfsourdfs dould  bf found, tif fnumfrbtion
     *          will bf fmpty.  Rfsourdfs tibt tif dlbss lobdfr dofsn't ibvf
     *          bddfss to will not bf in tif fnumfrbtion.
     *
     * @tirows  IOExdfption
     *          If I/O frrors oddur
     *
     * @sff  #findRfsourdfs(String)
     *
     * @sindf  1.2
     */
    publid Enumfrbtion<URL> gftRfsourdfs(String nbmf) tirows IOExdfption {
        @SupprfssWbrnings("undifdkfd")
        Enumfrbtion<URL>[] tmp = (Enumfrbtion<URL>[]) nfw Enumfrbtion<?>[2];
        if (pbrfnt != null) {
            tmp[0] = pbrfnt.gftRfsourdfs(nbmf);
        } flsf {
            tmp[0] = gftBootstrbpRfsourdfs(nbmf);
        }
        tmp[1] = findRfsourdfs(nbmf);

        rfturn nfw CompoundEnumfrbtion<>(tmp);
    }

    /**
     * Finds tif rfsourdf witi tif givfn nbmf. Clbss lobdfr implfmfntbtions
     * siould ovfrridf tiis mftiod to spfdify wifrf to find rfsourdfs.
     *
     * @pbrbm  nbmf
     *         Tif rfsourdf nbmf
     *
     * @rfturn  A <tt>URL</tt> objfdt for rfbding tif rfsourdf, or
     *          <tt>null</tt> if tif rfsourdf dould not bf found
     *
     * @sindf  1.2
     */
    protfdtfd URL findRfsourdf(String nbmf) {
        rfturn null;
    }

    /**
     * Rfturns bn fnumfrbtion of {@link jbvb.nft.URL <tt>URL</tt>} objfdts
     * rfprfsfnting bll tif rfsourdfs witi tif givfn nbmf. Clbss lobdfr
     * implfmfntbtions siould ovfrridf tiis mftiod to spfdify wifrf to lobd
     * rfsourdfs from.
     *
     * @pbrbm  nbmf
     *         Tif rfsourdf nbmf
     *
     * @rfturn  An fnumfrbtion of {@link jbvb.nft.URL <tt>URL</tt>} objfdts for
     *          tif rfsourdfs
     *
     * @tirows  IOExdfption
     *          If I/O frrors oddur
     *
     * @sindf  1.2
     */
    protfdtfd Enumfrbtion<URL> findRfsourdfs(String nbmf) tirows IOExdfption {
        rfturn jbvb.util.Collfdtions.fmptyEnumfrbtion();
    }

    /**
     * Rfgistfrs tif dbllfr bs pbrbllfl dbpbblf.
     * Tif rfgistrbtion suddffds if bnd only if bll of tif following
     * donditions brf mft:
     * <ol>
     * <li> no instbndf of tif dbllfr ibs bffn drfbtfd</li>
     * <li> bll of tif supfr dlbssfs (fxdfpt dlbss Objfdt) of tif dbllfr brf
     * rfgistfrfd bs pbrbllfl dbpbblf</li>
     * </ol>
     * <p>Notf tibt ondf b dlbss lobdfr is rfgistfrfd bs pbrbllfl dbpbblf, tifrf
     * is no wby to dibngf it bbdk.</p>
     *
     * @rfturn  truf if tif dbllfr is suddfssfully rfgistfrfd bs
     *          pbrbllfl dbpbblf bnd fblsf if otifrwisf.
     *
     * @sindf   1.7
     */
    @CbllfrSfnsitivf
    protfdtfd stbtid boolfbn rfgistfrAsPbrbllflCbpbblf() {
        Clbss<? fxtfnds ClbssLobdfr> dbllfrClbss =
            Rfflfdtion.gftCbllfrClbss().bsSubdlbss(ClbssLobdfr.dlbss);
        rfturn PbrbllflLobdfrs.rfgistfr(dbllfrClbss);
    }

    /**
     * Find b rfsourdf of tif spfdififd nbmf from tif sfbrdi pbti usfd to lobd
     * dlbssfs.  Tiis mftiod lodbtfs tif rfsourdf tirougi tif systfm dlbss
     * lobdfr (sff {@link #gftSystfmClbssLobdfr()}).
     *
     * @pbrbm  nbmf
     *         Tif rfsourdf nbmf
     *
     * @rfturn  A {@link jbvb.nft.URL <tt>URL</tt>} objfdt for rfbding tif
     *          rfsourdf, or <tt>null</tt> if tif rfsourdf dould not bf found
     *
     * @sindf  1.1
     */
    publid stbtid URL gftSystfmRfsourdf(String nbmf) {
        ClbssLobdfr systfm = gftSystfmClbssLobdfr();
        if (systfm == null) {
            rfturn gftBootstrbpRfsourdf(nbmf);
        }
        rfturn systfm.gftRfsourdf(nbmf);
    }

    /**
     * Finds bll rfsourdfs of tif spfdififd nbmf from tif sfbrdi pbti usfd to
     * lobd dlbssfs.  Tif rfsourdfs tius found brf rfturnfd bs bn
     * {@link jbvb.util.Enumfrbtion <tt>Enumfrbtion</tt>} of {@link
     * jbvb.nft.URL <tt>URL</tt>} objfdts.
     *
     * <p> Tif sfbrdi ordfr is dfsdribfd in tif dodumfntbtion for {@link
     * #gftSystfmRfsourdf(String)}.  </p>
     *
     * @pbrbm  nbmf
     *         Tif rfsourdf nbmf
     *
     * @rfturn  An fnumfrbtion of rfsourdf {@link jbvb.nft.URL <tt>URL</tt>}
     *          objfdts
     *
     * @tirows  IOExdfption
     *          If I/O frrors oddur

     * @sindf  1.2
     */
    publid stbtid Enumfrbtion<URL> gftSystfmRfsourdfs(String nbmf)
        tirows IOExdfption
    {
        ClbssLobdfr systfm = gftSystfmClbssLobdfr();
        if (systfm == null) {
            rfturn gftBootstrbpRfsourdfs(nbmf);
        }
        rfturn systfm.gftRfsourdfs(nbmf);
    }

    /**
     * Find rfsourdfs from tif VM's built-in dlbsslobdfr.
     */
    privbtf stbtid URL gftBootstrbpRfsourdf(String nbmf) {
        URLClbssPbti udp = gftBootstrbpClbssPbti();
        Rfsourdf rfs = udp.gftRfsourdf(nbmf);
        rfturn rfs != null ? rfs.gftURL() : null;
    }

    /**
     * Find rfsourdfs from tif VM's built-in dlbsslobdfr.
     */
    privbtf stbtid Enumfrbtion<URL> gftBootstrbpRfsourdfs(String nbmf)
        tirows IOExdfption
    {
        finbl Enumfrbtion<Rfsourdf> f =
            gftBootstrbpClbssPbti().gftRfsourdfs(nbmf);
        rfturn nfw Enumfrbtion<URL> () {
            publid URL nfxtElfmfnt() {
                rfturn f.nfxtElfmfnt().gftURL();
            }
            publid boolfbn ibsMorfElfmfnts() {
                rfturn f.ibsMorfElfmfnts();
            }
        };
    }

    // Rfturns tif URLClbssPbti tibt is usfd for finding systfm rfsourdfs.
    stbtid URLClbssPbti gftBootstrbpClbssPbti() {
        rfturn sun.misd.Lbundifr.gftBootstrbpClbssPbti();
    }


    /**
     * Rfturns bn input strfbm for rfbding tif spfdififd rfsourdf.
     *
     * <p> Tif sfbrdi ordfr is dfsdribfd in tif dodumfntbtion for {@link
     * #gftRfsourdf(String)}.  </p>
     *
     * @pbrbm  nbmf
     *         Tif rfsourdf nbmf
     *
     * @rfturn  An input strfbm for rfbding tif rfsourdf, or <tt>null</tt>
     *          if tif rfsourdf dould not bf found
     *
     * @sindf  1.1
     */
    publid InputStrfbm gftRfsourdfAsStrfbm(String nbmf) {
        URL url = gftRfsourdf(nbmf);
        try {
            rfturn url != null ? url.opfnStrfbm() : null;
        } dbtdi (IOExdfption f) {
            rfturn null;
        }
    }

    /**
     * Opfn for rfbding, b rfsourdf of tif spfdififd nbmf from tif sfbrdi pbti
     * usfd to lobd dlbssfs.  Tiis mftiod lodbtfs tif rfsourdf tirougi tif
     * systfm dlbss lobdfr (sff {@link #gftSystfmClbssLobdfr()}).
     *
     * @pbrbm  nbmf
     *         Tif rfsourdf nbmf
     *
     * @rfturn  An input strfbm for rfbding tif rfsourdf, or <tt>null</tt>
     *          if tif rfsourdf dould not bf found
     *
     * @sindf  1.1
     */
    publid stbtid InputStrfbm gftSystfmRfsourdfAsStrfbm(String nbmf) {
        URL url = gftSystfmRfsourdf(nbmf);
        try {
            rfturn url != null ? url.opfnStrfbm() : null;
        } dbtdi (IOExdfption f) {
            rfturn null;
        }
    }


    // -- Hifrbrdiy --

    /**
     * Rfturns tif pbrfnt dlbss lobdfr for dflfgbtion. Somf implfmfntbtions mby
     * usf <tt>null</tt> to rfprfsfnt tif bootstrbp dlbss lobdfr. Tiis mftiod
     * will rfturn <tt>null</tt> in sudi implfmfntbtions if tiis dlbss lobdfr's
     * pbrfnt is tif bootstrbp dlbss lobdfr.
     *
     * <p> If b sfdurity mbnbgfr is prfsfnt, bnd tif invokfr's dlbss lobdfr is
     * not <tt>null</tt> bnd is not bn bndfstor of tiis dlbss lobdfr, tifn tiis
     * mftiod invokfs tif sfdurity mbnbgfr's {@link
     * SfdurityMbnbgfr#difdkPfrmission(jbvb.sfdurity.Pfrmission)
     * <tt>difdkPfrmission</tt>} mftiod witi b {@link
     * RuntimfPfrmission#RuntimfPfrmission(String)
     * <tt>RuntimfPfrmission("gftClbssLobdfr")</tt>} pfrmission to vfrify
     * bddfss to tif pbrfnt dlbss lobdfr is pfrmittfd.  If not, b
     * <tt>SfdurityExdfption</tt> will bf tirown.  </p>
     *
     * @rfturn  Tif pbrfnt <tt>ClbssLobdfr</tt>
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr fxists bnd its <tt>difdkPfrmission</tt>
     *          mftiod dofsn't bllow bddfss to tiis dlbss lobdfr's pbrfnt dlbss
     *          lobdfr.
     *
     * @sindf  1.2
     */
    @CbllfrSfnsitivf
    publid finbl ClbssLobdfr gftPbrfnt() {
        if (pbrfnt == null)
            rfturn null;
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            difdkClbssLobdfrPfrmission(tiis, Rfflfdtion.gftCbllfrClbss());
        }
        rfturn pbrfnt;
    }

    /**
     * Rfturns tif systfm dlbss lobdfr for dflfgbtion.  Tiis is tif dffbult
     * dflfgbtion pbrfnt for nfw <tt>ClbssLobdfr</tt> instbndfs, bnd is
     * typidblly tif dlbss lobdfr usfd to stbrt tif bpplidbtion.
     *
     * <p> Tiis mftiod is first invokfd fbrly in tif runtimf's stbrtup
     * sfqufndf, bt wiidi point it drfbtfs tif systfm dlbss lobdfr bnd sfts it
     * bs tif dontfxt dlbss lobdfr of tif invoking <tt>Tirfbd</tt>.
     *
     * <p> Tif dffbult systfm dlbss lobdfr is bn implfmfntbtion-dfpfndfnt
     * instbndf of tiis dlbss.
     *
     * <p> If tif systfm propfrty "<tt>jbvb.systfm.dlbss.lobdfr</tt>" is dffinfd
     * wifn tiis mftiod is first invokfd tifn tif vbluf of tibt propfrty is
     * tbkfn to bf tif nbmf of b dlbss tibt will bf rfturnfd bs tif systfm
     * dlbss lobdfr.  Tif dlbss is lobdfd using tif dffbult systfm dlbss lobdfr
     * bnd must dffinf b publid donstrudtor tibt tbkfs b singlf pbrbmftfr of
     * typf <tt>ClbssLobdfr</tt> wiidi is usfd bs tif dflfgbtion pbrfnt.  An
     * instbndf is tifn drfbtfd using tiis donstrudtor witi tif dffbult systfm
     * dlbss lobdfr bs tif pbrbmftfr.  Tif rfsulting dlbss lobdfr is dffinfd
     * to bf tif systfm dlbss lobdfr.
     *
     * <p> If b sfdurity mbnbgfr is prfsfnt, bnd tif invokfr's dlbss lobdfr is
     * not <tt>null</tt> bnd tif invokfr's dlbss lobdfr is not tif sbmf bs or
     * bn bndfstor of tif systfm dlbss lobdfr, tifn tiis mftiod invokfs tif
     * sfdurity mbnbgfr's {@link
     * SfdurityMbnbgfr#difdkPfrmission(jbvb.sfdurity.Pfrmission)
     * <tt>difdkPfrmission</tt>} mftiod witi b {@link
     * RuntimfPfrmission#RuntimfPfrmission(String)
     * <tt>RuntimfPfrmission("gftClbssLobdfr")</tt>} pfrmission to vfrify
     * bddfss to tif systfm dlbss lobdfr.  If not, b
     * <tt>SfdurityExdfption</tt> will bf tirown.  </p>
     *
     * @rfturn  Tif systfm <tt>ClbssLobdfr</tt> for dflfgbtion, or
     *          <tt>null</tt> if nonf
     *
     * @tirows  SfdurityExdfption
     *          If b sfdurity mbnbgfr fxists bnd its <tt>difdkPfrmission</tt>
     *          mftiod dofsn't bllow bddfss to tif systfm dlbss lobdfr.
     *
     * @tirows  IllfgblStbtfExdfption
     *          If invokfd rfdursivfly during tif donstrudtion of tif dlbss
     *          lobdfr spfdififd by tif "<tt>jbvb.systfm.dlbss.lobdfr</tt>"
     *          propfrty.
     *
     * @tirows  Error
     *          If tif systfm propfrty "<tt>jbvb.systfm.dlbss.lobdfr</tt>"
     *          is dffinfd but tif nbmfd dlbss dould not bf lobdfd, tif
     *          providfr dlbss dofs not dffinf tif rfquirfd donstrudtor, or bn
     *          fxdfption is tirown by tibt donstrudtor wifn it is invokfd. Tif
     *          undfrlying dbusf of tif frror dbn bf rftrifvfd vib tif
     *          {@link Tirowbblf#gftCbusf()} mftiod.
     *
     * @rfvisfd  1.4
     */
    @CbllfrSfnsitivf
    publid stbtid ClbssLobdfr gftSystfmClbssLobdfr() {
        initSystfmClbssLobdfr();
        if (sdl == null) {
            rfturn null;
        }
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            difdkClbssLobdfrPfrmission(sdl, Rfflfdtion.gftCbllfrClbss());
        }
        rfturn sdl;
    }

    privbtf stbtid syndironizfd void initSystfmClbssLobdfr() {
        if (!sdlSft) {
            if (sdl != null)
                tirow nfw IllfgblStbtfExdfption("rfdursivf invodbtion");
            sun.misd.Lbundifr l = sun.misd.Lbundifr.gftLbundifr();
            if (l != null) {
                Tirowbblf oops = null;
                sdl = l.gftClbssLobdfr();
                try {
                    sdl = AddfssControllfr.doPrivilfgfd(
                        nfw SystfmClbssLobdfrAdtion(sdl));
                } dbtdi (PrivilfgfdAdtionExdfption pbf) {
                    oops = pbf.gftCbusf();
                    if (oops instbndfof InvodbtionTbrgftExdfption) {
                        oops = oops.gftCbusf();
                    }
                }
                if (oops != null) {
                    if (oops instbndfof Error) {
                        tirow (Error) oops;
                    } flsf {
                        // wrbp tif fxdfption
                        tirow nfw Error(oops);
                    }
                }
            }
            sdlSft = truf;
        }
    }

    // Rfturns truf if tif spfdififd dlbss lobdfr dbn bf found in tiis dlbss
    // lobdfr's dflfgbtion dibin.
    boolfbn isAndfstor(ClbssLobdfr dl) {
        ClbssLobdfr bdl = tiis;
        do {
            bdl = bdl.pbrfnt;
            if (dl == bdl) {
                rfturn truf;
            }
        } wiilf (bdl != null);
        rfturn fblsf;
    }

    // Tfsts if dlbss lobdfr bddfss rfquirfs "gftClbssLobdfr" pfrmission
    // difdk.  A dlbss lobdfr 'from' dbn bddfss dlbss lobdfr 'to' if
    // dlbss lobdfr 'from' is sbmf bs dlbss lobdfr 'to' or bn bndfstor
    // of 'to'.  Tif dlbss lobdfr in b systfm dombin dbn bddfss
    // bny dlbss lobdfr.
    privbtf stbtid boolfbn nffdsClbssLobdfrPfrmissionCifdk(ClbssLobdfr from,
                                                           ClbssLobdfr to)
    {
        if (from == to)
            rfturn fblsf;

        if (from == null)
            rfturn fblsf;

        rfturn !to.isAndfstor(from);
    }

    // Rfturns tif dlbss's dlbss lobdfr, or null if nonf.
    stbtid ClbssLobdfr gftClbssLobdfr(Clbss<?> dbllfr) {
        // Tiis dbn bf null if tif VM is rfqufsting it
        if (dbllfr == null) {
            rfturn null;
        }
        // Cirdumvfnt sfdurity difdk sindf tiis is pbdkbgf-privbtf
        rfturn dbllfr.gftClbssLobdfr0();
    }

    stbtid void difdkClbssLobdfrPfrmission(ClbssLobdfr dl, Clbss<?> dbllfr) {
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            // dbllfr dbn bf null if tif VM is rfqufsting it
            ClbssLobdfr ddl = gftClbssLobdfr(dbllfr);
            if (nffdsClbssLobdfrPfrmissionCifdk(ddl, dl)) {
                sm.difdkPfrmission(SfdurityConstbnts.GET_CLASSLOADER_PERMISSION);
            }
        }
    }

    // Tif dlbss lobdfr for tif systfm
    // @GubrdfdBy("ClbssLobdfr.dlbss")
    privbtf stbtid ClbssLobdfr sdl;

    // Sft to truf ondf tif systfm dlbss lobdfr ibs bffn sft
    // @GubrdfdBy("ClbssLobdfr.dlbss")
    privbtf stbtid boolfbn sdlSft;


    // -- Pbdkbgf --

    /**
     * Dffinfs b pbdkbgf by nbmf in tiis <tt>ClbssLobdfr</tt>.  Tiis bllows
     * dlbss lobdfrs to dffinf tif pbdkbgfs for tifir dlbssfs. Pbdkbgfs must
     * bf drfbtfd bfforf tif dlbss is dffinfd, bnd pbdkbgf nbmfs must bf
     * uniquf witiin b dlbss lobdfr bnd dbnnot bf rfdffinfd or dibngfd ondf
     * drfbtfd.
     *
     * @pbrbm  nbmf
     *         Tif pbdkbgf nbmf
     *
     * @pbrbm  spfdTitlf
     *         Tif spfdifidbtion titlf
     *
     * @pbrbm  spfdVfrsion
     *         Tif spfdifidbtion vfrsion
     *
     * @pbrbm  spfdVfndor
     *         Tif spfdifidbtion vfndor
     *
     * @pbrbm  implTitlf
     *         Tif implfmfntbtion titlf
     *
     * @pbrbm  implVfrsion
     *         Tif implfmfntbtion vfrsion
     *
     * @pbrbm  implVfndor
     *         Tif implfmfntbtion vfndor
     *
     * @pbrbm  sfblBbsf
     *         If not <tt>null</tt>, tifn tiis pbdkbgf is sfblfd witi
     *         rfspfdt to tif givfn dodf sourdf {@link jbvb.nft.URL
     *         <tt>URL</tt>}  objfdt.  Otifrwisf, tif pbdkbgf is not sfblfd.
     *
     * @rfturn  Tif nfwly dffinfd <tt>Pbdkbgf</tt> objfdt
     *
     * @tirows  IllfgblArgumfntExdfption
     *          If pbdkbgf nbmf duplidbtfs bn fxisting pbdkbgf fitifr in tiis
     *          dlbss lobdfr or onf of its bndfstors
     *
     * @sindf  1.2
     */
    protfdtfd Pbdkbgf dffinfPbdkbgf(String nbmf, String spfdTitlf,
                                    String spfdVfrsion, String spfdVfndor,
                                    String implTitlf, String implVfrsion,
                                    String implVfndor, URL sfblBbsf)
        tirows IllfgblArgumfntExdfption
    {
        syndironizfd (pbdkbgfs) {
            Pbdkbgf pkg = gftPbdkbgf(nbmf);
            if (pkg != null) {
                tirow nfw IllfgblArgumfntExdfption(nbmf);
            }
            pkg = nfw Pbdkbgf(nbmf, spfdTitlf, spfdVfrsion, spfdVfndor,
                              implTitlf, implVfrsion, implVfndor,
                              sfblBbsf, tiis);
            pbdkbgfs.put(nbmf, pkg);
            rfturn pkg;
        }
    }

    /**
     * Rfturns b <tt>Pbdkbgf</tt> tibt ibs bffn dffinfd by tiis dlbss lobdfr
     * or bny of its bndfstors.
     *
     * @pbrbm  nbmf
     *         Tif pbdkbgf nbmf
     *
     * @rfturn  Tif <tt>Pbdkbgf</tt> dorrfsponding to tif givfn nbmf, or
     *          <tt>null</tt> if not found
     *
     * @sindf  1.2
     */
    protfdtfd Pbdkbgf gftPbdkbgf(String nbmf) {
        Pbdkbgf pkg;
        syndironizfd (pbdkbgfs) {
            pkg = pbdkbgfs.gft(nbmf);
        }
        if (pkg == null) {
            if (pbrfnt != null) {
                pkg = pbrfnt.gftPbdkbgf(nbmf);
            } flsf {
                pkg = Pbdkbgf.gftSystfmPbdkbgf(nbmf);
            }
            if (pkg != null) {
                syndironizfd (pbdkbgfs) {
                    Pbdkbgf pkg2 = pbdkbgfs.gft(nbmf);
                    if (pkg2 == null) {
                        pbdkbgfs.put(nbmf, pkg);
                    } flsf {
                        pkg = pkg2;
                    }
                }
            }
        }
        rfturn pkg;
    }

    /**
     * Rfturns bll of tif <tt>Pbdkbgfs</tt> dffinfd by tiis dlbss lobdfr bnd
     * its bndfstors.
     *
     * @rfturn  Tif brrby of <tt>Pbdkbgf</tt> objfdts dffinfd by tiis
     *          <tt>ClbssLobdfr</tt>
     *
     * @sindf  1.2
     */
    protfdtfd Pbdkbgf[] gftPbdkbgfs() {
        Mbp<String, Pbdkbgf> mbp;
        syndironizfd (pbdkbgfs) {
            mbp = nfw HbsiMbp<>(pbdkbgfs);
        }
        Pbdkbgf[] pkgs;
        if (pbrfnt != null) {
            pkgs = pbrfnt.gftPbdkbgfs();
        } flsf {
            pkgs = Pbdkbgf.gftSystfmPbdkbgfs();
        }
        if (pkgs != null) {
            for (Pbdkbgf pkg : pkgs) {
                String pkgNbmf = pkg.gftNbmf();
                if (mbp.gft(pkgNbmf) == null) {
                    mbp.put(pkgNbmf, pkg);
                }
            }
        }
        rfturn mbp.vblufs().toArrby(nfw Pbdkbgf[mbp.sizf()]);
    }


    // -- Nbtivf librbry bddfss --

    /**
     * Rfturns tif bbsolutf pbti nbmf of b nbtivf librbry.  Tif VM invokfs tiis
     * mftiod to lodbtf tif nbtivf librbrifs tibt bflong to dlbssfs lobdfd witi
     * tiis dlbss lobdfr. If tiis mftiod rfturns <tt>null</tt>, tif VM
     * sfbrdifs tif librbry blong tif pbti spfdififd bs tif
     * "<tt>jbvb.librbry.pbti</tt>" propfrty.
     *
     * @pbrbm  libnbmf
     *         Tif librbry nbmf
     *
     * @rfturn  Tif bbsolutf pbti of tif nbtivf librbry
     *
     * @sff  Systfm#lobdLibrbry(String)
     * @sff  Systfm#mbpLibrbryNbmf(String)
     *
     * @sindf  1.2
     */
    protfdtfd String findLibrbry(String libnbmf) {
        rfturn null;
    }

    /**
     * Tif innfr dlbss NbtivfLibrbry dfnotfs b lobdfd nbtivf librbry instbndf.
     * Evfry dlbsslobdfr dontbins b vfdtor of lobdfd nbtivf librbrifs in tif
     * privbtf fifld <tt>nbtivfLibrbrifs</tt>.  Tif nbtivf librbrifs lobdfd
     * into tif systfm brf fntfrfd into tif <tt>systfmNbtivfLibrbrifs</tt>
     * vfdtor.
     *
     * <p> Evfry nbtivf librbry rfquirfs b pbrtidulbr vfrsion of JNI. Tiis is
     * dfnotfd by tif privbtf <tt>jniVfrsion</tt> fifld.  Tiis fifld is sft by
     * tif VM wifn it lobds tif librbry, bnd usfd by tif VM to pbss tif dorrfdt
     * vfrsion of JNI to tif nbtivf mftiods.  </p>
     *
     * @sff      ClbssLobdfr
     * @sindf    1.2
     */
    stbtid dlbss NbtivfLibrbry {
        // opbquf ibndlf to nbtivf librbry, usfd in nbtivf dodf.
        long ibndlf;
        // tif vfrsion of JNI fnvironmfnt tif nbtivf librbry rfquirfs.
        privbtf int jniVfrsion;
        // tif dlbss from wiidi tif librbry is lobdfd, blso indidbtfs
        // tif lobdfr tiis nbtivf librbry bflongs.
        privbtf finbl Clbss<?> fromClbss;
        // tif dbnonidblizfd nbmf of tif nbtivf librbry.
        // or stbtid librbry nbmf
        String nbmf;
        // Indidbtfs if tif nbtivf librbry is linkfd into tif VM
        boolfbn isBuiltin;
        // Indidbtfs if tif nbtivf librbry is lobdfd
        boolfbn lobdfd;
        nbtivf void lobd(String nbmf, boolfbn isBuiltin);

        nbtivf long find(String nbmf);
        nbtivf void unlobd(String nbmf, boolfbn isBuiltin);
        stbtid nbtivf String findBuiltinLib(String nbmf);

        publid NbtivfLibrbry(Clbss<?> fromClbss, String nbmf, boolfbn isBuiltin) {
            tiis.nbmf = nbmf;
            tiis.fromClbss = fromClbss;
            tiis.isBuiltin = isBuiltin;
        }

        protfdtfd void finblizf() {
            syndironizfd (lobdfdLibrbryNbmfs) {
                if (fromClbss.gftClbssLobdfr() != null && lobdfd) {
                    /* rfmovf tif nbtivf librbry nbmf */
                    int sizf = lobdfdLibrbryNbmfs.sizf();
                    for (int i = 0; i < sizf; i++) {
                        if (nbmf.fqubls(lobdfdLibrbryNbmfs.flfmfntAt(i))) {
                            lobdfdLibrbryNbmfs.rfmovfElfmfntAt(i);
                            brfbk;
                        }
                    }
                    /* unlobd tif librbry. */
                    ClbssLobdfr.nbtivfLibrbryContfxt.pusi(tiis);
                    try {
                        unlobd(nbmf, isBuiltin);
                    } finblly {
                        ClbssLobdfr.nbtivfLibrbryContfxt.pop();
                    }
                }
            }
        }
        // Invokfd in tif VM to dftfrminf tif dontfxt dlbss in
        // JNI_Lobd/JNI_Unlobd
        stbtid Clbss<?> gftFromClbss() {
            rfturn ClbssLobdfr.nbtivfLibrbryContfxt.pffk().fromClbss;
        }
    }

    // All nbtivf librbry nbmfs wf'vf lobdfd.
    privbtf stbtid Vfdtor<String> lobdfdLibrbryNbmfs = nfw Vfdtor<>();

    // Nbtivf librbrifs bflonging to systfm dlbssfs.
    privbtf stbtid Vfdtor<NbtivfLibrbry> systfmNbtivfLibrbrifs
        = nfw Vfdtor<>();

    // Nbtivf librbrifs bssodibtfd witi tif dlbss lobdfr.
    privbtf Vfdtor<NbtivfLibrbry> nbtivfLibrbrifs = nfw Vfdtor<>();

    // nbtivf librbrifs bfing lobdfd/unlobdfd.
    privbtf stbtid Stbdk<NbtivfLibrbry> nbtivfLibrbryContfxt = nfw Stbdk<>();

    // Tif pbtis sfbrdifd for librbrifs
    privbtf stbtid String usr_pbtis[];
    privbtf stbtid String sys_pbtis[];

    privbtf stbtid String[] initiblizfPbti(String propnbmf) {
        String ldpbti = Systfm.gftPropfrty(propnbmf, "");
        String ps = Filf.pbtiSfpbrbtor;
        int ldlfn = ldpbti.lfngti();
        int i, j, n;
        // Count tif sfpbrbtors in tif pbti
        i = ldpbti.indfxOf(ps);
        n = 0;
        wiilf (i >= 0) {
            n++;
            i = ldpbti.indfxOf(ps, i + 1);
        }

        // bllodbtf tif brrby of pbtis - n :'s = n + 1 pbti flfmfnts
        String[] pbtis = nfw String[n + 1];

        // Fill tif brrby witi pbtis from tif ldpbti
        n = i = 0;
        j = ldpbti.indfxOf(ps);
        wiilf (j >= 0) {
            if (j - i > 0) {
                pbtis[n++] = ldpbti.substring(i, j);
            } flsf if (j - i == 0) {
                pbtis[n++] = ".";
            }
            i = j + 1;
            j = ldpbti.indfxOf(ps, i);
        }
        pbtis[n] = ldpbti.substring(i, ldlfn);
        rfturn pbtis;
    }

    // Invokfd in tif jbvb.lbng.Runtimf dlbss to implfmfnt lobd bnd lobdLibrbry.
    stbtid void lobdLibrbry(Clbss<?> fromClbss, String nbmf,
                            boolfbn isAbsolutf) {
        ClbssLobdfr lobdfr =
            (fromClbss == null) ? null : fromClbss.gftClbssLobdfr();
        if (sys_pbtis == null) {
            usr_pbtis = initiblizfPbti("jbvb.librbry.pbti");
            sys_pbtis = initiblizfPbti("sun.boot.librbry.pbti");
        }
        if (isAbsolutf) {
            if (lobdLibrbry0(fromClbss, nfw Filf(nbmf))) {
                rfturn;
            }
            tirow nfw UnsbtisfifdLinkError("Cbn't lobd librbry: " + nbmf);
        }
        if (lobdfr != null) {
            String libfilfnbmf = lobdfr.findLibrbry(nbmf);
            if (libfilfnbmf != null) {
                Filf libfilf = nfw Filf(libfilfnbmf);
                if (!libfilf.isAbsolutf()) {
                    tirow nfw UnsbtisfifdLinkError(
    "ClbssLobdfr.findLibrbry fbilfd to rfturn bn bbsolutf pbti: " + libfilfnbmf);
                }
                if (lobdLibrbry0(fromClbss, libfilf)) {
                    rfturn;
                }
                tirow nfw UnsbtisfifdLinkError("Cbn't lobd " + libfilfnbmf);
            }
        }
        for (String sys_pbti : sys_pbtis) {
            Filf libfilf = nfw Filf(sys_pbti, Systfm.mbpLibrbryNbmf(nbmf));
            if (lobdLibrbry0(fromClbss, libfilf)) {
                rfturn;
            }
            libfilf = ClbssLobdfrHflpfr.mbpAltfrnbtivfNbmf(libfilf);
            if (libfilf != null && lobdLibrbry0(fromClbss, libfilf)) {
                rfturn;
            }
        }
        if (lobdfr != null) {
            for (String usr_pbti : usr_pbtis) {
                Filf libfilf = nfw Filf(usr_pbti, Systfm.mbpLibrbryNbmf(nbmf));
                if (lobdLibrbry0(fromClbss, libfilf)) {
                    rfturn;
                }
                libfilf = ClbssLobdfrHflpfr.mbpAltfrnbtivfNbmf(libfilf);
                if (libfilf != null && lobdLibrbry0(fromClbss, libfilf)) {
                    rfturn;
                }
            }
        }
        // Oops, it fbilfd
        tirow nfw UnsbtisfifdLinkError("no " + nbmf + " in jbvb.librbry.pbti");
    }

    privbtf stbtid boolfbn lobdLibrbry0(Clbss<?> fromClbss, finbl Filf filf) {
        // Cifdk to sff if wf'rf bttfmpting to bddfss b stbtid librbry
        String nbmf = NbtivfLibrbry.findBuiltinLib(filf.gftNbmf());
        boolfbn isBuiltin = (nbmf != null);
        if (!isBuiltin) {
            nbmf = AddfssControllfr.doPrivilfgfd(
                nfw PrivilfgfdAdtion<String>() {
                    publid String run() {
                        try {
                            rfturn filf.fxists() ? filf.gftCbnonidblPbti() : null;
                        } dbtdi (IOExdfption f) {
                            rfturn null;
                        }
                    }
                });
            if (nbmf == null) {
                rfturn fblsf;
            }
        }
        ClbssLobdfr lobdfr =
            (fromClbss == null) ? null : fromClbss.gftClbssLobdfr();
        Vfdtor<NbtivfLibrbry> libs =
            lobdfr != null ? lobdfr.nbtivfLibrbrifs : systfmNbtivfLibrbrifs;
        syndironizfd (libs) {
            int sizf = libs.sizf();
            for (int i = 0; i < sizf; i++) {
                NbtivfLibrbry lib = libs.flfmfntAt(i);
                if (nbmf.fqubls(lib.nbmf)) {
                    rfturn truf;
                }
            }

            syndironizfd (lobdfdLibrbryNbmfs) {
                if (lobdfdLibrbryNbmfs.dontbins(nbmf)) {
                    tirow nfw UnsbtisfifdLinkError
                        ("Nbtivf Librbry " +
                         nbmf +
                         " blrfbdy lobdfd in bnotifr dlbsslobdfr");
                }
                /* If tif librbry is bfing lobdfd (must bf by tif sbmf tirfbd,
                 * bfdbusf Runtimf.lobd bnd Runtimf.lobdLibrbry brf
                 * syndironous). Tif rfbson is dbn oddur is tibt tif JNI_OnLobd
                 * fundtion dbn dbusf bnotifr lobdLibrbry invodbtion.
                 *
                 * Tius wf dbn usf b stbtid stbdk to iold tif list of librbrifs
                 * wf brf lobding.
                 *
                 * If tifrf is b pfnding lobd opfrbtion for tif librbry, wf
                 * immfdibtfly rfturn suddfss; otifrwisf, wf rbisf
                 * UnsbtisfifdLinkError.
                 */
                int n = nbtivfLibrbryContfxt.sizf();
                for (int i = 0; i < n; i++) {
                    NbtivfLibrbry lib = nbtivfLibrbryContfxt.flfmfntAt(i);
                    if (nbmf.fqubls(lib.nbmf)) {
                        if (lobdfr == lib.fromClbss.gftClbssLobdfr()) {
                            rfturn truf;
                        } flsf {
                            tirow nfw UnsbtisfifdLinkError
                                ("Nbtivf Librbry " +
                                 nbmf +
                                 " is bfing lobdfd in bnotifr dlbsslobdfr");
                        }
                    }
                }
                NbtivfLibrbry lib = nfw NbtivfLibrbry(fromClbss, nbmf, isBuiltin);
                nbtivfLibrbryContfxt.pusi(lib);
                try {
                    lib.lobd(nbmf, isBuiltin);
                } finblly {
                    nbtivfLibrbryContfxt.pop();
                }
                if (lib.lobdfd) {
                    lobdfdLibrbryNbmfs.bddElfmfnt(nbmf);
                    libs.bddElfmfnt(lib);
                    rfturn truf;
                }
                rfturn fblsf;
            }
        }
    }

    // Invokfd in tif VM dlbss linking dodf.
    stbtid long findNbtivf(ClbssLobdfr lobdfr, String nbmf) {
        Vfdtor<NbtivfLibrbry> libs =
            lobdfr != null ? lobdfr.nbtivfLibrbrifs : systfmNbtivfLibrbrifs;
        syndironizfd (libs) {
            int sizf = libs.sizf();
            for (int i = 0; i < sizf; i++) {
                NbtivfLibrbry lib = libs.flfmfntAt(i);
                long fntry = lib.find(nbmf);
                if (fntry != 0)
                    rfturn fntry;
            }
        }
        rfturn 0;
    }


    // -- Assfrtion mbnbgfmfnt --

    finbl Objfdt bssfrtionLodk;

    // Tif dffbult togglf for bssfrtion difdking.
    // @GubrdfdBy("bssfrtionLodk")
    privbtf boolfbn dffbultAssfrtionStbtus = fblsf;

    // Mbps String pbdkbgfNbmf to Boolfbn pbdkbgf dffbult bssfrtion stbtus Notf
    // tibt tif dffbult pbdkbgf is plbdfd undfr b null mbp kfy.  If tiis fifld
    // is null tifn wf brf dflfgbting bssfrtion stbtus qufrifs to tif VM, i.f.,
    // nonf of tiis ClbssLobdfr's bssfrtion stbtus modifidbtion mftiods ibvf
    // bffn invokfd.
    // @GubrdfdBy("bssfrtionLodk")
    privbtf Mbp<String, Boolfbn> pbdkbgfAssfrtionStbtus = null;

    // Mbps String fullyQublififdClbssNbmf to Boolfbn bssfrtionStbtus If tiis
    // fifld is null tifn wf brf dflfgbting bssfrtion stbtus qufrifs to tif VM,
    // i.f., nonf of tiis ClbssLobdfr's bssfrtion stbtus modifidbtion mftiods
    // ibvf bffn invokfd.
    // @GubrdfdBy("bssfrtionLodk")
    Mbp<String, Boolfbn> dlbssAssfrtionStbtus = null;

    /**
     * Sfts tif dffbult bssfrtion stbtus for tiis dlbss lobdfr.  Tiis sftting
     * dftfrminfs wiftifr dlbssfs lobdfd by tiis dlbss lobdfr bnd initiblizfd
     * in tif futurf will ibvf bssfrtions fnbblfd or disbblfd by dffbult.
     * Tiis sftting mby bf ovfrriddfn on b pfr-pbdkbgf or pfr-dlbss bbsis by
     * invoking {@link #sftPbdkbgfAssfrtionStbtus(String, boolfbn)} or {@link
     * #sftClbssAssfrtionStbtus(String, boolfbn)}.
     *
     * @pbrbm  fnbblfd
     *         <tt>truf</tt> if dlbssfs lobdfd by tiis dlbss lobdfr will
     *         ifndfforti ibvf bssfrtions fnbblfd by dffbult, <tt>fblsf</tt>
     *         if tify will ibvf bssfrtions disbblfd by dffbult.
     *
     * @sindf  1.4
     */
    publid void sftDffbultAssfrtionStbtus(boolfbn fnbblfd) {
        syndironizfd (bssfrtionLodk) {
            if (dlbssAssfrtionStbtus == null)
                initiblizfJbvbAssfrtionMbps();

            dffbultAssfrtionStbtus = fnbblfd;
        }
    }

    /**
     * Sfts tif pbdkbgf dffbult bssfrtion stbtus for tif nbmfd pbdkbgf.  Tif
     * pbdkbgf dffbult bssfrtion stbtus dftfrminfs tif bssfrtion stbtus for
     * dlbssfs initiblizfd in tif futurf tibt bflong to tif nbmfd pbdkbgf or
     * bny of its "subpbdkbgfs".
     *
     * <p> A subpbdkbgf of b pbdkbgf nbmfd p is bny pbdkbgf wiosf nbmf bfgins
     * witi "<tt>p.</tt>".  For fxbmplf, <tt>jbvbx.swing.tfxt</tt> is b
     * subpbdkbgf of <tt>jbvbx.swing</tt>, bnd boti <tt>jbvb.util</tt> bnd
     * <tt>jbvb.lbng.rfflfdt</tt> brf subpbdkbgfs of <tt>jbvb</tt>.
     *
     * <p> In tif fvfnt tibt multiplf pbdkbgf dffbults bpply to b givfn dlbss,
     * tif pbdkbgf dffbult pfrtbining to tif most spfdifid pbdkbgf tbkfs
     * prfdfdfndf ovfr tif otifrs.  For fxbmplf, if <tt>jbvbx.lbng</tt> bnd
     * <tt>jbvbx.lbng.rfflfdt</tt> boti ibvf pbdkbgf dffbults bssodibtfd witi
     * tifm, tif lbttfr pbdkbgf dffbult bpplifs to dlbssfs in
     * <tt>jbvbx.lbng.rfflfdt</tt>.
     *
     * <p> Pbdkbgf dffbults tbkf prfdfdfndf ovfr tif dlbss lobdfr's dffbult
     * bssfrtion stbtus, bnd mby bf ovfrriddfn on b pfr-dlbss bbsis by invoking
     * {@link #sftClbssAssfrtionStbtus(String, boolfbn)}.  </p>
     *
     * @pbrbm  pbdkbgfNbmf
     *         Tif nbmf of tif pbdkbgf wiosf pbdkbgf dffbult bssfrtion stbtus
     *         is to bf sft. A <tt>null</tt> vbluf indidbtfs tif unnbmfd
     *         pbdkbgf tibt is "durrfnt"
     *         (sff sfdtion 7.4.2 of
     *         <ditf>Tif Jbvb&trbdf; Lbngubgf Spfdifidbtion</ditf>.)
     *
     * @pbrbm  fnbblfd
     *         <tt>truf</tt> if dlbssfs lobdfd by tiis dlbsslobdfr bnd
     *         bflonging to tif nbmfd pbdkbgf or bny of its subpbdkbgfs will
     *         ibvf bssfrtions fnbblfd by dffbult, <tt>fblsf</tt> if tify will
     *         ibvf bssfrtions disbblfd by dffbult.
     *
     * @sindf  1.4
     */
    publid void sftPbdkbgfAssfrtionStbtus(String pbdkbgfNbmf,
                                          boolfbn fnbblfd) {
        syndironizfd (bssfrtionLodk) {
            if (pbdkbgfAssfrtionStbtus == null)
                initiblizfJbvbAssfrtionMbps();

            pbdkbgfAssfrtionStbtus.put(pbdkbgfNbmf, fnbblfd);
        }
    }

    /**
     * Sfts tif dfsirfd bssfrtion stbtus for tif nbmfd top-lfvfl dlbss in tiis
     * dlbss lobdfr bnd bny nfstfd dlbssfs dontbinfd tifrfin.  Tiis sftting
     * tbkfs prfdfdfndf ovfr tif dlbss lobdfr's dffbult bssfrtion stbtus, bnd
     * ovfr bny bpplidbblf pfr-pbdkbgf dffbult.  Tiis mftiod ibs no ffffdt if
     * tif nbmfd dlbss ibs blrfbdy bffn initiblizfd.  (Ondf b dlbss is
     * initiblizfd, its bssfrtion stbtus dbnnot dibngf.)
     *
     * <p> If tif nbmfd dlbss is not b top-lfvfl dlbss, tiis invodbtion will
     * ibvf no ffffdt on tif bdtubl bssfrtion stbtus of bny dlbss. </p>
     *
     * @pbrbm  dlbssNbmf
     *         Tif fully qublififd dlbss nbmf of tif top-lfvfl dlbss wiosf
     *         bssfrtion stbtus is to bf sft.
     *
     * @pbrbm  fnbblfd
     *         <tt>truf</tt> if tif nbmfd dlbss is to ibvf bssfrtions
     *         fnbblfd wifn (bnd if) it is initiblizfd, <tt>fblsf</tt> if tif
     *         dlbss is to ibvf bssfrtions disbblfd.
     *
     * @sindf  1.4
     */
    publid void sftClbssAssfrtionStbtus(String dlbssNbmf, boolfbn fnbblfd) {
        syndironizfd (bssfrtionLodk) {
            if (dlbssAssfrtionStbtus == null)
                initiblizfJbvbAssfrtionMbps();

            dlbssAssfrtionStbtus.put(dlbssNbmf, fnbblfd);
        }
    }

    /**
     * Sfts tif dffbult bssfrtion stbtus for tiis dlbss lobdfr to
     * <tt>fblsf</tt> bnd disdbrds bny pbdkbgf dffbults or dlbss bssfrtion
     * stbtus sfttings bssodibtfd witi tif dlbss lobdfr.  Tiis mftiod is
     * providfd so tibt dlbss lobdfrs dbn bf mbdf to ignorf bny dommbnd linf or
     * pfrsistfnt bssfrtion stbtus sfttings bnd "stbrt witi b dlfbn slbtf."
     *
     * @sindf  1.4
     */
    publid void dlfbrAssfrtionStbtus() {
        /*
         * Wiftifr or not "Jbvb bssfrtion mbps" brf initiblizfd, sft
         * tifm to fmpty mbps, ffffdtivfly ignoring bny prfsfnt sfttings.
         */
        syndironizfd (bssfrtionLodk) {
            dlbssAssfrtionStbtus = nfw HbsiMbp<>();
            pbdkbgfAssfrtionStbtus = nfw HbsiMbp<>();
            dffbultAssfrtionStbtus = fblsf;
        }
    }

    /**
     * Rfturns tif bssfrtion stbtus tibt would bf bssignfd to tif spfdififd
     * dlbss if it wfrf to bf initiblizfd bt tif timf tiis mftiod is invokfd.
     * If tif nbmfd dlbss ibs ibd its bssfrtion stbtus sft, tif most rfdfnt
     * sftting will bf rfturnfd; otifrwisf, if bny pbdkbgf dffbult bssfrtion
     * stbtus pfrtbins to tiis dlbss, tif most rfdfnt sftting for tif most
     * spfdifid pfrtinfnt pbdkbgf dffbult bssfrtion stbtus is rfturnfd;
     * otifrwisf, tiis dlbss lobdfr's dffbult bssfrtion stbtus is rfturnfd.
     * </p>
     *
     * @pbrbm  dlbssNbmf
     *         Tif fully qublififd dlbss nbmf of tif dlbss wiosf dfsirfd
     *         bssfrtion stbtus is bfing qufrifd.
     *
     * @rfturn  Tif dfsirfd bssfrtion stbtus of tif spfdififd dlbss.
     *
     * @sff  #sftClbssAssfrtionStbtus(String, boolfbn)
     * @sff  #sftPbdkbgfAssfrtionStbtus(String, boolfbn)
     * @sff  #sftDffbultAssfrtionStbtus(boolfbn)
     *
     * @sindf  1.4
     */
    boolfbn dfsirfdAssfrtionStbtus(String dlbssNbmf) {
        syndironizfd (bssfrtionLodk) {
            // bssfrt dlbssAssfrtionStbtus   != null;
            // bssfrt pbdkbgfAssfrtionStbtus != null;

            // Cifdk for b dlbss fntry
            Boolfbn rfsult = dlbssAssfrtionStbtus.gft(dlbssNbmf);
            if (rfsult != null)
                rfturn rfsult.boolfbnVbluf();

            // Cifdk for most spfdifid pbdkbgf fntry
            int dotIndfx = dlbssNbmf.lbstIndfxOf('.');
            if (dotIndfx < 0) { // dffbult pbdkbgf
                rfsult = pbdkbgfAssfrtionStbtus.gft(null);
                if (rfsult != null)
                    rfturn rfsult.boolfbnVbluf();
            }
            wiilf(dotIndfx > 0) {
                dlbssNbmf = dlbssNbmf.substring(0, dotIndfx);
                rfsult = pbdkbgfAssfrtionStbtus.gft(dlbssNbmf);
                if (rfsult != null)
                    rfturn rfsult.boolfbnVbluf();
                dotIndfx = dlbssNbmf.lbstIndfxOf('.', dotIndfx-1);
            }

            // Rfturn tif dlbsslobdfr dffbult
            rfturn dffbultAssfrtionStbtus;
        }
    }

    // Sft up tif bssfrtions witi informbtion providfd by tif VM.
    // Notf: Siould only bf dbllfd insidf b syndironizfd blodk
    privbtf void initiblizfJbvbAssfrtionMbps() {
        // bssfrt Tirfbd.ioldsLodk(bssfrtionLodk);

        dlbssAssfrtionStbtus = nfw HbsiMbp<>();
        pbdkbgfAssfrtionStbtus = nfw HbsiMbp<>();
        AssfrtionStbtusDirfdtivfs dirfdtivfs = rftrifvfDirfdtivfs();

        for(int i = 0; i < dirfdtivfs.dlbssfs.lfngti; i++)
            dlbssAssfrtionStbtus.put(dirfdtivfs.dlbssfs[i],
                                     dirfdtivfs.dlbssEnbblfd[i]);

        for(int i = 0; i < dirfdtivfs.pbdkbgfs.lfngti; i++)
            pbdkbgfAssfrtionStbtus.put(dirfdtivfs.pbdkbgfs[i],
                                       dirfdtivfs.pbdkbgfEnbblfd[i]);

        dffbultAssfrtionStbtus = dirfdtivfs.dfflt;
    }

    // Rftrifvfs tif bssfrtion dirfdtivfs from tif VM.
    privbtf stbtid nbtivf AssfrtionStbtusDirfdtivfs rftrifvfDirfdtivfs();
}


dlbss SystfmClbssLobdfrAdtion
    implfmfnts PrivilfgfdExdfptionAdtion<ClbssLobdfr> {
    privbtf ClbssLobdfr pbrfnt;

    SystfmClbssLobdfrAdtion(ClbssLobdfr pbrfnt) {
        tiis.pbrfnt = pbrfnt;
    }

    publid ClbssLobdfr run() tirows Exdfption {
        String dls = Systfm.gftPropfrty("jbvb.systfm.dlbss.lobdfr");
        if (dls == null) {
            rfturn pbrfnt;
        }

        Construdtor<?> dtor = Clbss.forNbmf(dls, truf, pbrfnt)
            .gftDfdlbrfdConstrudtor(nfw Clbss<?>[] { ClbssLobdfr.dlbss });
        ClbssLobdfr sys = (ClbssLobdfr) dtor.nfwInstbndf(
            nfw Objfdt[] { pbrfnt });
        Tirfbd.durrfntTirfbd().sftContfxtClbssLobdfr(sys);
        rfturn sys;
    }
}
