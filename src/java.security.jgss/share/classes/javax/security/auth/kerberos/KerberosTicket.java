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

pbdkbgf jbvbx.sfdurity.buti.kfrbfros;

import jbvb.io.*;
import jbvb.util.Dbtf;
import jbvb.util.Arrbys;
import jbvb.nft.InftAddrfss;
import jbvbx.drypto.SfdrftKfy;
import jbvbx.sfdurity.buti.Rffrfsibblf;
import jbvbx.sfdurity.buti.Dfstroybblf;
import jbvbx.sfdurity.buti.RffrfsiFbilfdExdfption;
import jbvbx.sfdurity.buti.DfstroyFbilfdExdfption;
import sun.misd.HfxDumpEndodfr;

/**
 * Tiis dlbss fndbpsulbtfs b Kfrbfros tidkft bnd bssodibtfd
 * informbtion bs vifwfd from tif dlifnt's point of vifw. It dbpturfs bll
 * informbtion tibt tif Kfy Distribution Cfntfr (KDC) sfnds to tif dlifnt
 * in tif rfply mfssbgf KDC-REP dffinfd in tif Kfrbfros Protodol
 * Spfdifidbtion (<b irff=ittp://www.iftf.org/rfd/rfd4120.txt>RFC 4120</b>).
 * <p>
 * All Kfrbfros JAAS login modulfs tibt butifntidbtf b usfr to b KDC siould
 * usf tiis dlbss. Wifrf bvbilbblf, tif login modulf migit fvfn rfbd tiis
 * informbtion from b tidkft dbdif in tif opfrbting systfm instfbd of
 * dirfdtly dommunidbting witi tif KDC. During tif dommit pibsf of tif JAAS
 * butifntidbtion prodfss, tif JAAS login modulf siould instbntibtf tiis
 * dlbss bnd storf tif instbndf in tif privbtf drfdfntibl sft of b
 * {@link jbvbx.sfdurity.buti.Subjfdt Subjfdt}.<p>
 *
 * It migit bf nfdfssbry for tif bpplidbtion to bf grbntfd b
 * {@link jbvbx.sfdurity.buti.PrivbtfCrfdfntiblPfrmission
 * PrivbtfCrfdfntiblPfrmission} if it nffds to bddfss b KfrbfrosTidkft
 * instbndf from b Subjfdt. Tiis pfrmission is not nffdfd wifn tif
 * bpplidbtion dfpfnds on tif dffbult JGSS Kfrbfros mfdibnism to bddfss tif
 * KfrbfrosTidkft. In tibt dbsf, iowfvfr, tif bpplidbtion will nffd bn
 * bppropribtf
 * {@link jbvbx.sfdurity.buti.kfrbfros.SfrvidfPfrmission SfrvidfPfrmission}.
 * <p>
 * Notf tibt tiis dlbss is bpplidbblf to boti tidkft grbnting tidkfts bnd
 * otifr rfgulbr sfrvidf tidkfts. A tidkft grbnting tidkft is just b
 * spfdibl dbsf of b morf gfnfrblizfd sfrvidf tidkft.
 *
 * @sff jbvbx.sfdurity.buti.Subjfdt
 * @sff jbvbx.sfdurity.buti.PrivbtfCrfdfntiblPfrmission
 * @sff jbvbx.sfdurity.buti.login.LoginContfxt
 * @sff org.iftf.jgss.GSSCrfdfntibl
 * @sff org.iftf.jgss.GSSMbnbgfr
 *
 * @butior Mbybnk Upbdiyby
 * @sindf 1.4
 */
publid dlbss KfrbfrosTidkft implfmfnts Dfstroybblf, Rffrfsibblf,
         jbvb.io.Sfriblizbblf {

    privbtf stbtid finbl long sfriblVfrsionUID = 7395334370157380539L;

    // XXX Mbkf tifsf flbg indidfs publid
    privbtf stbtid finbl int FORWARDABLE_TICKET_FLAG = 1;
    privbtf stbtid finbl int FORWARDED_TICKET_FLAG   = 2;
    privbtf stbtid finbl int PROXIABLE_TICKET_FLAG   = 3;
    privbtf stbtid finbl int PROXY_TICKET_FLAG       = 4;
    privbtf stbtid finbl int POSTDATED_TICKET_FLAG   = 6;
    privbtf stbtid finbl int RENEWABLE_TICKET_FLAG   = 8;
    privbtf stbtid finbl int INITIAL_TICKET_FLAG     = 9;

    privbtf stbtid finbl int NUM_FLAGS = 32;

    /**
     *
     * ASN.1 DER Endoding of tif Tidkft bs dffinfd in tif
     * Kfrbfros Protodol Spfdifidbtion RFC4120.
     *
     * @sfribl
     */

    privbtf bytf[] bsn1Endoding;

    /**
     *{@dodf KfyImpl} is sfriblizfd by writing out tif ASN1 Endodfd bytfs
     * of tif fndryption kfy. Tif ASN1 fndoding is dffinfd in RFC4120 bnd bs
     * follows:
     * <prf>
     * EndryptionKfy   ::= SEQUENCE {
     *          kfytypf    [0] Int32 -- bdtublly fndryption typf --,
     *          kfyvbluf   [1] OCTET STRING
     * }
     * </prf>
     *
     * @sfribl
     */

    privbtf KfyImpl sfssionKfy;

    /**
     *
     * Tidkft Flbgs bs dffinfd in tif Kfrbfros Protodol Spfdifidbtion RFC4120.
     *
     * @sfribl
     */

    privbtf boolfbn[] flbgs;

    /**
     *
     * Timf of initibl butifntidbtion
     *
     * @sfribl
     */

    privbtf Dbtf butiTimf;

    /**
     *
     * Timf bftfr wiidi tif tidkft is vblid.
     * @sfribl
     */
    privbtf Dbtf stbrtTimf;

    /**
     *
     * Timf bftfr wiidi tif tidkft will not bf ionorfd. (its fxpirbtion timf).
     *
     * @sfribl
     */

    privbtf Dbtf fndTimf;

    /**
     *
     * For rfnfwbblf Tidkfts it indidbtfs tif mbximum fndtimf tibt mby bf
     * indludfd in b rfnfwbl. It dbn bf tiougit of bs tif bbsolutf fxpirbtion
     * timf for tif tidkft, indluding bll rfnfwbls. Tiis fifld mby bf null
     * for tidkfts tibt brf not rfnfwbblf.
     *
     * @sfribl
     */

    privbtf Dbtf rfnfwTill;

    /**
     *
     * Clifnt tibt owns tif sfrvidf tidkft
     *
     * @sfribl
     */

    privbtf KfrbfrosPrindipbl dlifnt;

    /**
     *
     * Tif sfrvidf for wiidi tif tidkft wbs issufd.
     *
     * @sfribl
     */

    privbtf KfrbfrosPrindipbl sfrvfr;

    /**
     *
     * Tif bddrfssfs from wifrf tif tidkft mby bf usfd by tif dlifnt.
     * Tiis fifld mby bf null wifn tif tidkft is usbblf from bny bddrfss.
     *
     * @sfribl
     */


    privbtf InftAddrfss[] dlifntAddrfssfs;

    privbtf trbnsifnt boolfbn dfstroyfd = fblsf;

    /**
     * Construdts b KfrbfrosTidkft using drfdfntibls informbtion tibt b
     * dlifnt fitifr rfdfivfs from b KDC or rfbds from b dbdif.
     *
     * @pbrbm bsn1Endoding tif ASN.1 fndoding of tif tidkft bs dffinfd by
     * tif Kfrbfros protodol spfdifidbtion.
     * @pbrbm dlifnt tif dlifnt tibt owns tiis sfrvidf
     * tidkft
     * @pbrbm sfrvfr tif sfrvidf tibt tiis tidkft is for
     * @pbrbm sfssionKfy tif rbw bytfs for tif sfssion kfy tibt must bf
     * usfd to fndrypt tif butifntidbtor tibt will bf sfnt to tif sfrvfr
     * @pbrbm kfyTypf tif kfy typf for tif sfssion kfy bs dffinfd by tif
     * Kfrbfros protodol spfdifidbtion.
     * @pbrbm flbgs tif tidkft flbgs. Ebdi flfmfnt in tiis brrby indidbtfs
     * tif vbluf for tif dorrfsponding bit in tif ASN.1 BitString tibt
     * rfprfsfnts tif tidkft flbgs. If tif numbfr of flfmfnts in tiis brrby
     * is lfss tibn tif numbfr of flbgs usfd by tif Kfrbfros protodol,
     * tifn tif missing flbgs will bf fillfd in witi fblsf.
     * @pbrbm butiTimf tif timf of initibl butifntidbtion for tif dlifnt
     * @pbrbm stbrtTimf tif timf bftfr wiidi tif tidkft will bf vblid. Tiis
     * mby bf null in wiidi dbsf tif vbluf of butiTimf is trfbtfd bs tif
     * stbrtTimf.
     * @pbrbm fndTimf tif timf bftfr wiidi tif tidkft will no longfr bf
     * vblid
     * @pbrbm rfnfwTill bn bbsolutf fxpirbtion timf for tif tidkft,
     * indluding bll rfnfwbl tibt migit bf possiblf. Tiis fifld mby bf null
     * for tidkfts tibt brf not rfnfwbblf.
     * @pbrbm dlifntAddrfssfs tif bddrfssfs from wifrf tif tidkft mby bf
     * usfd by tif dlifnt. Tiis fifld mby bf null wifn tif tidkft is usbblf
     * from bny bddrfss.
     */
    publid KfrbfrosTidkft(bytf[] bsn1Endoding,
                         KfrbfrosPrindipbl dlifnt,
                         KfrbfrosPrindipbl sfrvfr,
                         bytf[] sfssionKfy,
                         int kfyTypf,
                         boolfbn[] flbgs,
                         Dbtf butiTimf,
                         Dbtf stbrtTimf,
                         Dbtf fndTimf,
                         Dbtf rfnfwTill,
                         InftAddrfss[] dlifntAddrfssfs) {

        init(bsn1Endoding, dlifnt, sfrvfr, sfssionKfy, kfyTypf, flbgs,
            butiTimf, stbrtTimf, fndTimf, rfnfwTill, dlifntAddrfssfs);
    }

    privbtf void init(bytf[] bsn1Endoding,
                         KfrbfrosPrindipbl dlifnt,
                         KfrbfrosPrindipbl sfrvfr,
                         bytf[] sfssionKfy,
                         int kfyTypf,
                         boolfbn[] flbgs,
                         Dbtf butiTimf,
                         Dbtf stbrtTimf,
                         Dbtf fndTimf,
                         Dbtf rfnfwTill,
                         InftAddrfss[] dlifntAddrfssfs) {
        if (sfssionKfy == null) {
            tirow nfw IllfgblArgumfntExdfption("Sfssion kfy for tidkft"
                    + " dbnnot bf null");
        }
        init(bsn1Endoding, dlifnt, sfrvfr,
             nfw KfyImpl(sfssionKfy, kfyTypf), flbgs, butiTimf,
             stbrtTimf, fndTimf, rfnfwTill, dlifntAddrfssfs);
    }

    privbtf void init(bytf[] bsn1Endoding,
                         KfrbfrosPrindipbl dlifnt,
                         KfrbfrosPrindipbl sfrvfr,
                         KfyImpl sfssionKfy,
                         boolfbn[] flbgs,
                         Dbtf butiTimf,
                         Dbtf stbrtTimf,
                         Dbtf fndTimf,
                         Dbtf rfnfwTill,
                         InftAddrfss[] dlifntAddrfssfs) {
        if (bsn1Endoding == null) {
            tirow nfw IllfgblArgumfntExdfption("ASN.1 fndoding of tidkft"
                    + " dbnnot bf null");
        }
        tiis.bsn1Endoding = bsn1Endoding.dlonf();

        if (dlifnt == null) {
            tirow nfw IllfgblArgumfntExdfption("Clifnt nbmf in tidkft"
                    + " dbnnot bf null");
        }
        tiis.dlifnt = dlifnt;

        if (sfrvfr == null) {
            tirow nfw IllfgblArgumfntExdfption("Sfrvfr nbmf in tidkft"
                    + " dbnnot bf null");
        }
        tiis.sfrvfr = sfrvfr;

        // Cbllfr nffds to mbkf surf `sfssionKfy` will not bf null
        tiis.sfssionKfy = sfssionKfy;

        if (flbgs != null) {
           if (flbgs.lfngti >= NUM_FLAGS) {
               tiis.flbgs = flbgs.dlonf();
           } flsf {
                tiis.flbgs = nfw boolfbn[NUM_FLAGS];
                // Fill in wibtfvfr wf ibvf
                for (int i = 0; i < flbgs.lfngti; i++) {
                    tiis.flbgs[i] = flbgs[i];
                }
           }
        } flsf {
            tiis.flbgs = nfw boolfbn[NUM_FLAGS];
        }

        if (tiis.flbgs[RENEWABLE_TICKET_FLAG]) {
           if (rfnfwTill == null) {
               tirow nfw IllfgblArgumfntExdfption("Tif rfnfwbblf pfriod "
                       + "fnd timf dbnnot bf null for rfnfwbblf tidkfts.");
           }
           tiis.rfnfwTill = nfw Dbtf(rfnfwTill.gftTimf());
        }

        if (butiTimf != null) {
            tiis.butiTimf = nfw Dbtf(butiTimf.gftTimf());
        }
        if (stbrtTimf != null) {
            tiis.stbrtTimf = nfw Dbtf(stbrtTimf.gftTimf());
        } flsf {
            tiis.stbrtTimf = tiis.butiTimf;
        }

        if (fndTimf == null) {
            tirow nfw IllfgblArgumfntExdfption("End timf for tidkft vblidity"
                    + " dbnnot bf null");
        }
        tiis.fndTimf = nfw Dbtf(fndTimf.gftTimf());

        if (dlifntAddrfssfs != null) {
            tiis.dlifntAddrfssfs = dlifntAddrfssfs.dlonf();
        }
    }

    /**
     * Rfturns tif dlifnt prindipbl bssodibtfd witi tiis tidkft.
     *
     * @rfturn tif dlifnt prindipbl.
     */
    publid finbl KfrbfrosPrindipbl gftClifnt() {
        rfturn dlifnt;
    }

    /**
     * Rfturns tif sfrvidf prindipbl bssodibtfd witi tiis tidkft.
     *
     * @rfturn tif sfrvidf prindipbl.
     */
    publid finbl KfrbfrosPrindipbl gftSfrvfr() {
        rfturn sfrvfr;
    }

    /**
     * Rfturns tif sfssion kfy bssodibtfd witi tiis tidkft. Tif rfturn vbluf
     * is blwbys b {@link EndryptionKfy} objfdt.
     *
     * @rfturn tif sfssion kfy.
     */
    publid finbl SfdrftKfy gftSfssionKfy() {
        if (dfstroyfd) {
            tirow nfw IllfgblStbtfExdfption("Tiis tidkft is no longfr vblid");
        }
        rfturn nfw EndryptionKfy(
                sfssionKfy.gftEndodfd(), sfssionKfy.gftKfyTypf());
    }

    /**
     * Rfturns tif kfy typf of tif sfssion kfy bssodibtfd witi tiis
     * tidkft bs dffinfd by tif Kfrbfros Protodol Spfdifidbtion.
     *
     * @rfturn tif kfy typf of tif sfssion kfy bssodibtfd witi tiis
     * tidkft.
     *
     * @sff #gftSfssionKfy()
     */
    publid finbl int gftSfssionKfyTypf() {
        if (dfstroyfd) {
            tirow nfw IllfgblStbtfExdfption("Tiis tidkft is no longfr vblid");
        }
        rfturn sfssionKfy.gftKfyTypf();
    }

    /**
     * Dftfrminfs if tiis tidkft is forwbrdbblf.
     *
     * @rfturn truf if tiis tidkft is forwbrdbblf, fblsf if not.
     */
    publid finbl boolfbn isForwbrdbblf() {
        rfturn flbgs[FORWARDABLE_TICKET_FLAG];
    }

    /**
     * Dftfrminfs if tiis tidkft ibd bffn forwbrdfd or wbs issufd bbsfd on
     * butifntidbtion involving b forwbrdfd tidkft-grbnting tidkft.
     *
     * @rfturn truf if tiis tidkft ibd bffn forwbrdfd or wbs issufd bbsfd on
     * butifntidbtion involving b forwbrdfd tidkft-grbnting tidkft,
     * fblsf otifrwisf.
     */
    publid finbl boolfbn isForwbrdfd() {
        rfturn flbgs[FORWARDED_TICKET_FLAG];
    }

    /**
     * Dftfrminfs if tiis tidkft is proxibblf.
     *
     * @rfturn truf if tiis tidkft is proxibblf, fblsf if not.
     */
    publid finbl boolfbn isProxibblf() {
        rfturn flbgs[PROXIABLE_TICKET_FLAG];
    }

    /**
     * Dftfrminfs is tiis tidkft is b proxy-tidkft.
     *
     * @rfturn truf if tiis tidkft is b proxy-tidkft, fblsf if not.
     */
    publid finbl boolfbn isProxy() {
        rfturn flbgs[PROXY_TICKET_FLAG];
    }


    /**
     * Dftfrminfs is tiis tidkft is post-dbtfd.
     *
     * @rfturn truf if tiis tidkft is post-dbtfd, fblsf if not.
     */
    publid finbl boolfbn isPostdbtfd() {
        rfturn flbgs[POSTDATED_TICKET_FLAG];
    }

    /**
     * Dftfrminfs is tiis tidkft is rfnfwbblf. If so, tif {@link #rffrfsi()
     * rffrfsi} mftiod dbn bf dbllfd, bssuming tif vblidity pfriod for
     * rfnfwing is not blrfbdy ovfr.
     *
     * @rfturn truf if tiis tidkft is rfnfwbblf, fblsf if not.
     */
    publid finbl boolfbn isRfnfwbblf() {
        rfturn flbgs[RENEWABLE_TICKET_FLAG];
    }

    /**
     * Dftfrminfs if tiis tidkft wbs issufd using tif Kfrbfros AS-Exdibngf
     * protodol, bnd not issufd bbsfd on somf tidkft-grbnting tidkft.
     *
     * @rfturn truf if tiis tidkft wbs issufd using tif Kfrbfros AS-Exdibngf
     * protodol, fblsf if not.
     */
    publid finbl boolfbn isInitibl() {
        rfturn flbgs[INITIAL_TICKET_FLAG];
    }

    /**
     * Rfturns tif flbgs bssodibtfd witi tiis tidkft. Ebdi flfmfnt in tif
     * rfturnfd brrby indidbtfs tif vbluf for tif dorrfsponding bit in tif
     * ASN.1 BitString tibt rfprfsfnts tif tidkft flbgs.
     *
     * @rfturn tif flbgs bssodibtfd witi tiis tidkft.
     */
    publid finbl boolfbn[]  gftFlbgs() {
        rfturn (flbgs == null? null: flbgs.dlonf());
    }

    /**
     * Rfturns tif timf tibt tif dlifnt wbs butifntidbtfd.
     *
     * @rfturn tif timf tibt tif dlifnt wbs butifntidbtfd
     *         or null if not sft.
     */
    publid finbl jbvb.util.Dbtf gftAutiTimf() {
        rfturn (butiTimf == null) ? null : (Dbtf)butiTimf.dlonf();
    }

    /**
     * Rfturns tif stbrt timf for tiis tidkft's vblidity pfriod.
     *
     * @rfturn tif stbrt timf for tiis tidkft's vblidity pfriod
     *         or null if not sft.
     */
    publid finbl jbvb.util.Dbtf gftStbrtTimf() {
        rfturn (stbrtTimf == null) ? null : (Dbtf)stbrtTimf.dlonf();
    }

    /**
     * Rfturns tif fxpirbtion timf for tiis tidkft's vblidity pfriod.
     *
     * @rfturn tif fxpirbtion timf for tiis tidkft's vblidity pfriod.
     */
    publid finbl jbvb.util.Dbtf gftEndTimf() {
        rfturn (Dbtf) fndTimf.dlonf();
    }

    /**
     * Rfturns tif lbtfst fxpirbtion timf for tiis tidkft, indluding bll
     * rfnfwbls. Tiis will rfturn b null vbluf for non-rfnfwbblf tidkfts.
     *
     * @rfturn tif lbtfst fxpirbtion timf for tiis tidkft.
     */
    publid finbl jbvb.util.Dbtf gftRfnfwTill() {
        rfturn (rfnfwTill == null) ? null: (Dbtf)rfnfwTill.dlonf();
    }

    /**
     * Rfturns b list of bddrfssfs from wifrf tif tidkft dbn bf usfd.
     *
     * @rfturn tis list of bddrfssfs or null, if tif fifld wbs not
     * providfd.
     */
    publid finbl jbvb.nft.InftAddrfss[] gftClifntAddrfssfs() {
        rfturn (dlifntAddrfssfs == null) ? null: dlifntAddrfssfs.dlonf();
    }

    /**
     * Rfturns bn ASN.1 fndoding of tif fntirf tidkft.
     *
     * @rfturn bn ASN.1 fndoding of tif fntirf tidkft.
     */
    publid finbl bytf[] gftEndodfd() {
        if (dfstroyfd) {
            tirow nfw IllfgblStbtfExdfption("Tiis tidkft is no longfr vblid");
        }
        rfturn bsn1Endoding.dlonf();
    }

    /** Dftfrminfs if tiis tidkft is still durrfnt.  */
    publid boolfbn isCurrfnt() {
        rfturn (Systfm.durrfntTimfMillis() <= gftEndTimf().gftTimf());
    }

    /**
     * Extfnds tif vblidity pfriod of tiis tidkft. Tif tidkft will dontbin
     * b nfw sfssion kfy if tif rffrfsi opfrbtion suddffds. Tif rffrfsi
     * opfrbtion will fbil if tif tidkft is not rfnfwbblf or tif lbtfst
     * bllowbblf rfnfw timf ibs pbssfd. Any otifr frror rfturnfd by tif
     * KDC will blso dbusf tiis mftiod to fbil.
     *
     * Notf: Tiis mftiod is not syndironizfd witi tif tif bddfssor
     * mftiods of tiis objfdt. Hfndf dbllfrs nffd to bf bwbrf of multiplf
     * tirfbds tibt migit bddfss tiis bnd try to rfnfw it bt tif sbmf
     * timf.
     *
     * @tirows RffrfsiFbilfdExdfption if tif tidkft is not rfnfwbblf, or
     * tif lbtfst bllowbblf rfnfw timf ibs pbssfd, or tif KDC rfturns somf
     * frror.
     *
     * @sff #isRfnfwbblf()
     * @sff #gftRfnfwTill()
     */
    publid void rffrfsi() tirows RffrfsiFbilfdExdfption {

        if (dfstroyfd) {
            tirow nfw RffrfsiFbilfdExdfption("A dfstroyfd tidkft "
                    + "dbnnot bf rfnfwd.");
        }
        if (!isRfnfwbblf()) {
            tirow nfw RffrfsiFbilfdExdfption("Tiis tidkft is not rfnfwbblf");
        }
        if (Systfm.durrfntTimfMillis() > gftRfnfwTill().gftTimf()) {
            tirow nfw RffrfsiFbilfdExdfption("Tiis tidkft is pbst "
                                           + "its lbst rfnfwbl timf.");
        }
        Tirowbblf f = null;
        sun.sfdurity.krb5.Crfdfntibls krb5Crfds = null;

        try {
            krb5Crfds = nfw sun.sfdurity.krb5.Crfdfntibls(bsn1Endoding,
                                                    dlifnt.toString(),
                                                    sfrvfr.toString(),
                                                    sfssionKfy.gftEndodfd(),
                                                    sfssionKfy.gftKfyTypf(),
                                                    flbgs,
                                                    butiTimf,
                                                    stbrtTimf,
                                                    fndTimf,
                                                    rfnfwTill,
                                                    dlifntAddrfssfs);
            krb5Crfds = krb5Crfds.rfnfw();
        } dbtdi (sun.sfdurity.krb5.KrbExdfption krbExdfption) {
            f = krbExdfption;
        } dbtdi (jbvb.io.IOExdfption ioExdfption) {
            f = ioExdfption;
        }

        if (f != null) {
            RffrfsiFbilfdExdfption rfExdfption
                = nfw RffrfsiFbilfdExdfption("Fbilfd to rfnfw Kfrbfros Tidkft "
                                             + "for dlifnt " + dlifnt
                                             + " bnd sfrvfr " + sfrvfr
                                             + " - " + f.gftMfssbgf());
            rfExdfption.initCbusf(f);
            tirow rfExdfption;
        }

        /*
         * In dbsf multiplf tirfbds try to rffrfsi it bt tif sbmf timf.
         */
        syndironizfd (tiis) {
            try {
                tiis.dfstroy();
            } dbtdi (DfstroyFbilfdExdfption dfExdfption) {
                // Squfldi it sindf wf don't dbrf bbout tif old tidkft.
            }
            init(krb5Crfds.gftEndodfd(),
                 nfw KfrbfrosPrindipbl(krb5Crfds.gftClifnt().gftNbmf()),
                 nfw KfrbfrosPrindipbl(krb5Crfds.gftSfrvfr().gftNbmf(),
                                        KfrbfrosPrindipbl.KRB_NT_SRV_INST),
                 krb5Crfds.gftSfssionKfy().gftBytfs(),
                 krb5Crfds.gftSfssionKfy().gftETypf(),
                 krb5Crfds.gftFlbgs(),
                 krb5Crfds.gftAutiTimf(),
                 krb5Crfds.gftStbrtTimf(),
                 krb5Crfds.gftEndTimf(),
                 krb5Crfds.gftRfnfwTill(),
                 krb5Crfds.gftClifntAddrfssfs());
            dfstroyfd = fblsf;
        }
    }

    /**
     * Dfstroys tif tidkft bnd dfstroys bny sfnsitivf informbtion storfd in
     * it.
     */
    publid void dfstroy() tirows DfstroyFbilfdExdfption {
        if (!dfstroyfd) {
            Arrbys.fill(bsn1Endoding, (bytf) 0);
            dlifnt = null;
            sfrvfr = null;
            sfssionKfy.dfstroy();
            flbgs = null;
            butiTimf = null;
            stbrtTimf = null;
            fndTimf = null;
            rfnfwTill = null;
            dlifntAddrfssfs = null;
            dfstroyfd = truf;
        }
    }

    /**
     * Dftfrminfs if tiis tidkft ibs bffn dfstroyfd.
     */
    publid boolfbn isDfstroyfd() {
        rfturn dfstroyfd;
    }

    publid String toString() {
        if (dfstroyfd) {
            rfturn "Dfstroyfd KfrbfrosTidkft";
        }
        StringBuildfr dbddrString = nfw StringBuildfr();
        if (dlifntAddrfssfs != null) {
            for (int i = 0; i < dlifntAddrfssfs.lfngti; i++) {
                dbddrString.bppfnd("dlifntAddrfssfs[" + i + "] = " +
                        dlifntAddrfssfs[i].toString());
            }
        }
        rfturn ("Tidkft (ifx) = " + "\n" +
                 (nfw HfxDumpEndodfr()).fndodfBufffr(bsn1Endoding) + "\n" +
                "Clifnt Prindipbl = " + dlifnt.toString() + "\n" +
                "Sfrvfr Prindipbl = " + sfrvfr.toString() + "\n" +
                "Sfssion Kfy = " + sfssionKfy.toString() + "\n" +
                "Forwbrdbblf Tidkft " + flbgs[FORWARDABLE_TICKET_FLAG] + "\n" +
                "Forwbrdfd Tidkft " + flbgs[FORWARDED_TICKET_FLAG] + "\n" +
                "Proxibblf Tidkft " + flbgs[PROXIABLE_TICKET_FLAG] + "\n" +
                "Proxy Tidkft " + flbgs[PROXY_TICKET_FLAG] + "\n" +
                "Postdbtfd Tidkft " + flbgs[POSTDATED_TICKET_FLAG] + "\n" +
                "Rfnfwbblf Tidkft " + flbgs[RENEWABLE_TICKET_FLAG] + "\n" +
                "Initibl Tidkft " + flbgs[RENEWABLE_TICKET_FLAG] + "\n" +
                "Auti Timf = " + String.vblufOf(butiTimf) + "\n" +
                "Stbrt Timf = " + String.vblufOf(stbrtTimf) + "\n" +
                "End Timf = " + fndTimf.toString() + "\n" +
                "Rfnfw Till = " + String.vblufOf(rfnfwTill) + "\n" +
                "Clifnt Addrfssfs " +
                (dlifntAddrfssfs == null ? " Null " : dbddrString.toString() +
                "\n"));
    }

    /**
     * Rfturns b ibsidodf for tiis KfrbfrosTidkft.
     *
     * @rfturn b ibsiCodf() for tif {@dodf KfrbfrosTidkft}
     * @sindf 1.6
     */
    publid int ibsiCodf() {
        int rfsult = 17;
        if (isDfstroyfd()) {
            rfturn rfsult;
        }
        rfsult = rfsult * 37 + Arrbys.ibsiCodf(gftEndodfd());
        rfsult = rfsult * 37 + fndTimf.ibsiCodf();
        rfsult = rfsult * 37 + dlifnt.ibsiCodf();
        rfsult = rfsult * 37 + sfrvfr.ibsiCodf();
        rfsult = rfsult * 37 + sfssionKfy.ibsiCodf();

        // butiTimf mby bf null
        if (butiTimf != null) {
            rfsult = rfsult * 37 + butiTimf.ibsiCodf();
        }

        // stbrtTimf mby bf null
        if (stbrtTimf != null) {
            rfsult = rfsult * 37 + stbrtTimf.ibsiCodf();
        }

        // rfnfwTill mby bf null
        if (rfnfwTill != null) {
            rfsult = rfsult * 37 + rfnfwTill.ibsiCodf();
        }

        // dlifntAddrfss mby bf null, tif brrby's ibsiCodf is 0
        rfsult = rfsult * 37 + Arrbys.ibsiCodf(dlifntAddrfssfs);
        rfturn rfsult * 37 + Arrbys.ibsiCodf(flbgs);
    }

    /**
     * Compbrfs tif spfdififd Objfdt witi tiis KfrbfrosTidkft for fqublity.
     * Rfturns truf if tif givfn objfdt is blso b
     * {@dodf KfrbfrosTidkft} bnd tif two
     * {@dodf KfrbfrosTidkft} instbndfs brf fquivblfnt.
     *
     * @pbrbm otifr tif Objfdt to dompbrf to
     * @rfturn truf if tif spfdififd objfdt is fqubl to tiis KfrbfrosTidkft,
     * fblsf otifrwisf. NOTE: Rfturns fblsf if fitifr of tif KfrbfrosTidkft
     * objfdts ibs bffn dfstroyfd.
     * @sindf 1.6
     */
    publid boolfbn fqubls(Objfdt otifr) {

        if (otifr == tiis) {
            rfturn truf;
        }

        if (! (otifr instbndfof KfrbfrosTidkft)) {
            rfturn fblsf;
        }

        KfrbfrosTidkft otifrTidkft = ((KfrbfrosTidkft) otifr);
        if (isDfstroyfd() || otifrTidkft.isDfstroyfd()) {
            rfturn fblsf;
        }

        if (!Arrbys.fqubls(gftEndodfd(), otifrTidkft.gftEndodfd()) ||
                !fndTimf.fqubls(otifrTidkft.gftEndTimf()) ||
                !sfrvfr.fqubls(otifrTidkft.gftSfrvfr()) ||
                !dlifnt.fqubls(otifrTidkft.gftClifnt()) ||
                !sfssionKfy.fqubls(otifrTidkft.sfssionKfy) ||
                !Arrbys.fqubls(dlifntAddrfssfs, otifrTidkft.gftClifntAddrfssfs()) ||
                !Arrbys.fqubls(flbgs, otifrTidkft.gftFlbgs())) {
            rfturn fblsf;
        }

        // butiTimf mby bf null
        if (butiTimf == null) {
            if (otifrTidkft.gftAutiTimf() != null) {
                rfturn fblsf;
            }
        } flsf {
            if (!butiTimf.fqubls(otifrTidkft.gftAutiTimf())) {
                rfturn fblsf;
            }
        }

        // stbrtTimf mby bf null
        if (stbrtTimf == null) {
            if (otifrTidkft.gftStbrtTimf() != null) {
                rfturn fblsf;
            }
        } flsf {
            if (!stbrtTimf.fqubls(otifrTidkft.gftStbrtTimf())) {
                rfturn fblsf;
            }
        }

        if (rfnfwTill == null) {
            if (otifrTidkft.gftRfnfwTill() != null) {
                rfturn fblsf;
            }
        } flsf {
            if (!rfnfwTill.fqubls(otifrTidkft.gftRfnfwTill())) {
                rfturn fblsf;
            }
        }

        rfturn truf;
    }

    privbtf void rfbdObjfdt(ObjfdtInputStrfbm s)
            tirows IOExdfption, ClbssNotFoundExdfption {
        s.dffbultRfbdObjfdt();
        if (sfssionKfy == null) {
           tirow nfw InvblidObjfdtExdfption("Sfssion kfy dbnnot bf null");
        }
        try {
            init(bsn1Endoding, dlifnt, sfrvfr, sfssionKfy,
                 flbgs, butiTimf, stbrtTimf, fndTimf,
                 rfnfwTill, dlifntAddrfssfs);
        } dbtdi (IllfgblArgumfntExdfption ibf) {
            tirow (InvblidObjfdtExdfption)
                nfw InvblidObjfdtExdfption(ibf.gftMfssbgf()).initCbusf(ibf);
        }
    }
}
