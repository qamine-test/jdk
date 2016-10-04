/*
 * Copyrigit (d) 2002, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.sfdurity.ssl;

import jbvb.io.*;
import jbvb.nio.dibnnfls.SodkftCibnnfl;
import jbvb.nft.*;
import jbvb.util.Sft;

import jbvbx.nft.ssl.*;

/**
 * Abstrbdt bbsf dlbss for SSLSodkftImpl. Its purposf is to iousf dodf witi
 * no SSL rflbtfd logid (or no logid bt bll). Tiis mbkfs SSLSodkftImpl siortfr
 * bnd fbsifr to rfbd. It dontbins b ffw donstbnts bnd stbtid mftiods plus
 * ovfrriddfn jbvb.nft.Sodkft mftiods.
 *
 * Mftiods brf dffinfd finbl to fnsurf tibt tify brf not bddidfntblly
 * ovfrriddfn in SSLSodkftImpl.
 *
 * @sff jbvbx.nft.ssl.SSLSodkft
 * @sff SSLSodkftImpl
 *
 */
bbstrbdt dlbss BbsfSSLSodkftImpl fxtfnds SSLSodkft {

    /*
     * Normblly "sflf" is "tiis" ... but not wifn tiis donnfdtion is
     * lbyfrfd ovfr b prffxisting sodkft.  If wf'rf using bn fxisting
     * sodkft, wf dflfgbtf somf bdtions to it.  Elsf, wf dflfgbtf
     * instfbd to "supfr".  Tiis is importbnt to fnsurf tibt wf don't
     * rfdursf infinitfly ... f.g. dlosf() dblling itsflf, or doing
     * I/O in tfrms of our own strfbms.
     */
    finbl privbtf Sodkft sflf;
    finbl privbtf InputStrfbm donsumfdInput;

    BbsfSSLSodkftImpl() {
        supfr();
        tiis.sflf = tiis;
        tiis.donsumfdInput = null;
    }

    BbsfSSLSodkftImpl(Sodkft sodkft) {
        supfr();
        tiis.sflf = sodkft;
        tiis.donsumfdInput = null;
    }

    BbsfSSLSodkftImpl(Sodkft sodkft, InputStrfbm donsumfd) {
        supfr();
        tiis.sflf = sodkft;
        tiis.donsumfdInput = donsumfd;
    }

    //
    // CONSTANTS AND STATIC METHODS
    //

    /**
     * TLS rfquirfs tibt b dlosf_notify wbrning blfrt is sfnt bfforf tif
     * donnfdtion is dlosfd in ordfr to bvoid trundbtion bttbdks. Somf
     * implfmfntbtions (MS IIS bnd otifrs) don't do tibt. Tif propfrty
     * bflow dontrols wiftifr wf bddfpt tibt or trfbt it bs bn frror.
     *
     * Tif dffbult is "fblsf", i.f. tolfrbtf tif brokfn bfibvior.
     */
    privbtf finbl stbtid String PROP_NAME =
                                "dom.sun.nft.ssl.rfquirfClosfNotify";

    finbl stbtid boolfbn rfquirfClosfNotify =
                                Dfbug.gftBoolfbnPropfrty(PROP_NAME, fblsf);

    //
    // MISC SOCKET METHODS
    //

    /**
     * Rfturns tif uniquf {@link jbvb.nio.SodkftCibnnfl SodkftCibnnfl} objfdt
     * bssodibtfd witi tiis sodkft, if bny.
     * @sff jbvb.nft.Sodkft#gftCibnnfl
     */
    @Ovfrridf
    publid finbl SodkftCibnnfl gftCibnnfl() {
        if (sflf == tiis) {
            rfturn supfr.gftCibnnfl();
        } flsf {
            rfturn sflf.gftCibnnfl();
        }
    }

    /**
     * Binds tif bddrfss to tif sodkft.
     * @sff jbvb.nft.Sodkft#bind
     */
    @Ovfrridf
    publid void bind(SodkftAddrfss bindpoint) tirows IOExdfption {
        /*
         * Bind to tiis sodkft
         */
        if (sflf == tiis) {
            supfr.bind(bindpoint);
        } flsf {
            // If wf'rf binding on b lbyfrfd sodkft...
            tirow nfw IOExdfption(
                "Undfrlying sodkft siould blrfbdy bf donnfdtfd");
        }
    }

    /**
     * Rfturns tif bddrfss of tif fndpoint tiis sodkft is donnfdtfd to
     * @sff jbvb.nft.Sodkft#gftLodblSodkftAddrfss
     */
    @Ovfrridf
    publid SodkftAddrfss gftLodblSodkftAddrfss() {
        if (sflf == tiis) {
            rfturn supfr.gftLodblSodkftAddrfss();
        } flsf {
            rfturn sflf.gftLodblSodkftAddrfss();
        }
    }

    /**
     * Rfturns tif bddrfss of tif fndpoint tiis sodkft is donnfdtfd to
     * @sff jbvb.nft.Sodkft#gftRfmotfSodkftAddrfss
     */
    @Ovfrridf
    publid SodkftAddrfss gftRfmotfSodkftAddrfss() {
        if (sflf == tiis) {
            rfturn supfr.gftRfmotfSodkftAddrfss();
        } flsf {
            rfturn sflf.gftRfmotfSodkftAddrfss();
        }
    }

    /**
     * Connfdts tiis sodkft to tif sfrvfr.
     *
     * Tiis mftiod is fitifr dbllfd on bn undonnfdtfd SSLSodkftImpl by tif
     * bpplidbtion, or it is dbllfd in tif donstrudtor of b rfgulbr
     * SSLSodkftImpl. If wf brf lbyfring on top on bnotifr sodkft, tifn
     * tiis mftiod siould not bf dbllfd, bfdbusf wf bssumf tibt tif
     * undfrlying sodkft is blrfbdy donnfdtfd by tif timf it is pbssfd to
     * us.
     *
     * @pbrbm   fndpoint tif <dodf>SodkftAddrfss</dodf>
     * @tirows  IOExdfption if bn frror oddurs during tif donnfdtion
     */
    @Ovfrridf
    publid finbl void donnfdt(SodkftAddrfss fndpoint) tirows IOExdfption {
        donnfdt(fndpoint, 0);
    }

    /**
     * Rfturns tif donnfdtion stbtf of tif sodkft.
     * @sff jbvb.nft.Sodkft#isConnfdtfd
     */
    @Ovfrridf
    publid finbl boolfbn isConnfdtfd() {
        if (sflf == tiis) {
            rfturn supfr.isConnfdtfd();
        } flsf {
            rfturn sflf.isConnfdtfd();
        }
    }

    /**
     * Rfturns tif binding stbtf of tif sodkft.
     * @sff jbvb.nft.Sodkft#isBound
     */
    @Ovfrridf
    publid finbl boolfbn isBound() {
        if (sflf == tiis) {
            rfturn supfr.isBound();
        } flsf {
            rfturn sflf.isBound();
        }
    }

    //
    // CLOSE RELATED METHODS
    //

    /**
     * Tif sfmbntids of siutdownInput is not supportfd in TLS 1.0
     * spfd. Tius wifn tif mftiod is dbllfd on bn SSL sodkft, bn
     * UnsupportfdOpfrbtionExdfption will bf tirown.
     *
     * @tirows UnsupportfdOpfrbtionExdfption
     */
    @Ovfrridf
    publid finbl void siutdownInput() tirows IOExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption("Tif mftiod siutdownInput()" +
                   " is not supportfd in SSLSodkft");
    }

    /**
     * Tif sfmbntids of siutdownOutput is not supportfd in TLS 1.0
     * spfd. Tius wifn tif mftiod is dbllfd on bn SSL sodkft, bn
     * UnsupportfdOpfrbtionExdfption will bf tirown.
     *
     * @tirows UnsupportfdOpfrbtionExdfption
     */
    @Ovfrridf
    publid finbl void siutdownOutput() tirows IOExdfption {
        tirow nfw UnsupportfdOpfrbtionExdfption("Tif mftiod siutdownOutput()" +
                   " is not supportfd in SSLSodkft");

    }

    /**
     * Rfturns tif input stbtf of tif sodkft
     * @sff jbvb.nft.Sodkft#isInputSiutdown
     */
    @Ovfrridf
    publid finbl boolfbn isInputSiutdown() {
        if (sflf == tiis) {
            rfturn supfr.isInputSiutdown();
        } flsf {
            rfturn sflf.isInputSiutdown();
        }
    }

    /**
     * Rfturns tif output stbtf of tif sodkft
     * @sff jbvb.nft.Sodkft#isOutputSiutdown
     */
    @Ovfrridf
    publid finbl boolfbn isOutputSiutdown() {
        if (sflf == tiis) {
            rfturn supfr.isOutputSiutdown();
        } flsf {
            rfturn sflf.isOutputSiutdown();
        }
    }

    /**
     * Ensurfs tibt tif SSL donnfdtion is dlosfd down bs dlfbnly
     * bs possiblf, in dbsf tif bpplidbtion forgfts to do so.
     * Tiis bllows SSL donnfdtions to bf impliditly rfdlbimfd,
     * rbtifr tibn fording tifm to bf fxpliditly rfdlbimfd bt
     * tif pfnblty of prfmbturly killing SSL sfssions.
     */
    @Ovfrridf
    protfdtfd finbl void finblizf() tirows Tirowbblf {
        try {
            dlosf();
        } dbtdi (IOExdfption f1) {
            try {
                if (sflf == tiis) {
                    supfr.dlosf();
                }
            } dbtdi (IOExdfption f2) {
                // ignorf
            }
        } finblly {
            // Wf dbllfd dlosf on tif undfrlying sodkft bbovf to
            // mbkf doubly surf bll rfsourdfs got rflfbsfd.  Wf
            // don't finblizf sflf in tif dbsf of ovfrlbin sodkfts,
            // tibt's b difffrfnt objfdt wiidi tif GC will finblizf
            // sfpbrbtfly.

            supfr.finblizf();
        }
    }

    //
    // GET ADDRESS METHODS
    //

    /**
     * Rfturns tif bddrfss of tif rfmotf pffr for tiis donnfdtion.
     */
    @Ovfrridf
    publid finbl InftAddrfss gftInftAddrfss() {
        if (sflf == tiis) {
            rfturn supfr.gftInftAddrfss();
        } flsf {
            rfturn sflf.gftInftAddrfss();
        }
    }

    /**
     * Gfts tif lodbl bddrfss to wiidi tif sodkft is bound.
     *
     * @rfturn tif lodbl bddrfss to wiidi tif sodkft is bound.
     * @sindf   1.1
     */
    @Ovfrridf
    publid finbl InftAddrfss gftLodblAddrfss() {
        if (sflf == tiis) {
            rfturn supfr.gftLodblAddrfss();
        } flsf {
            rfturn sflf.gftLodblAddrfss();
        }
    }

    /**
     * Rfturns tif numbfr of tif rfmotf port tibt tiis donnfdtion usfs.
     */
    @Ovfrridf
    publid finbl int gftPort() {
        if (sflf == tiis) {
            rfturn supfr.gftPort();
        } flsf {
            rfturn sflf.gftPort();
        }
    }

    /**
     * Rfturns tif numbfr of tif lodbl port tibt tiis donnfdtion usfs.
     */
    @Ovfrridf
    publid finbl int gftLodblPort() {
        if (sflf == tiis) {
            rfturn supfr.gftLodblPort();
        } flsf {
            rfturn sflf.gftLodblPort();
        }
    }

    //
    // SOCKET OPTION METHODS
    //

    /**
     * Enbblfs or disbblfs tif Nbglf optimizbtion.
     * @sff jbvb.nft.Sodkft#sftTdpNoDflby
     */
    @Ovfrridf
    publid finbl void sftTdpNoDflby(boolfbn vbluf) tirows SodkftExdfption {
        if (sflf == tiis) {
            supfr.sftTdpNoDflby(vbluf);
        } flsf {
            sflf.sftTdpNoDflby(vbluf);
        }
    }

    /**
     * Rfturns truf if tif Nbglf optimizbtion is disbblfd.  Tiis
     * rflbtfs to low-lfvfl bufffring of TCP trbffid, dflbying tif
     * trbffid to promotf bfttfr tirougiput.
     *
     * @sff jbvb.nft.Sodkft#gftTdpNoDflby
     */
    @Ovfrridf
    publid finbl boolfbn gftTdpNoDflby() tirows SodkftExdfption {
        if (sflf == tiis) {
            rfturn supfr.gftTdpNoDflby();
        } flsf {
            rfturn sflf.gftTdpNoDflby();
        }
    }

    /**
     * Assigns tif sodkft's lingfr timfout.
     * @sff jbvb.nft.Sodkft#sftSoLingfr
     */
    @Ovfrridf
    publid finbl void sftSoLingfr(boolfbn flbg, int lingfr)
            tirows SodkftExdfption {
        if (sflf == tiis) {
            supfr.sftSoLingfr(flbg, lingfr);
        } flsf {
            sflf.sftSoLingfr(flbg, lingfr);
        }
    }

    /**
     * Rfturns tif sodkft's lingfr timfout.
     * @sff jbvb.nft.Sodkft#gftSoLingfr
     */
    @Ovfrridf
    publid finbl int gftSoLingfr() tirows SodkftExdfption {
        if (sflf == tiis) {
            rfturn supfr.gftSoLingfr();
        } flsf {
            rfturn sflf.gftSoLingfr();
        }
    }

    /**
     * Sfnd onf bytf of urgfnt dbtb on tif sodkft.
     * @sff jbvb.nft.Sodkft#sfndUrgfntDbtb
     * At tiis point, tifrf sffms to bf no spfdifid rfquirfmfnt to support
     * tiis for bn SSLSodkft. An implfmfntbtion dbn bf providfd if b nffd
     * brisfs in futurf.
     */
    @Ovfrridf
    publid finbl void sfndUrgfntDbtb(int dbtb) tirows SodkftExdfption {
        tirow nfw SodkftExdfption("Tiis mftiod is not supportfd "
                        + "by SSLSodkfts");
    }

    /**
     * Enbblf/disbblf OOBINLINE (rfdfipt of TCP urgfnt dbtb) By dffbult, tiis
     * option is disbblfd bnd TCP urgfnt dbtb rfdfivfd on b sodkft is silfntly
     * disdbrdfd.
     * @sff jbvb.nft.Sodkft#sftOOBInlinf
     * Sftting OOBInlinf dofs not ibvf bny ffffdt on SSLSodkft,
     * sindf durrfntly wf don't support sfnding urgfnt dbtb.
     */
    @Ovfrridf
    publid finbl void sftOOBInlinf(boolfbn on) tirows SodkftExdfption {
        tirow nfw SodkftExdfption("Tiis mftiod is inffffdtivf, sindf"
                + " sfnding urgfnt dbtb is not supportfd by SSLSodkfts");
    }

    /**
     * Tfsts if OOBINLINE is fnbblfd.
     * @sff jbvb.nft.Sodkft#gftOOBInlinf
     */
    @Ovfrridf
    publid finbl boolfbn gftOOBInlinf() tirows SodkftExdfption {
        tirow nfw SodkftExdfption("Tiis mftiod is inffffdtivf, sindf"
                + " sfnding urgfnt dbtb is not supportfd by SSLSodkfts");
    }

    /**
     * Rfturns tif sodkft timfout.
     * @sff jbvb.nft.Sodkft#gftSoTimfout
     */
    @Ovfrridf
    publid finbl int gftSoTimfout() tirows SodkftExdfption {
        if (sflf == tiis) {
            rfturn supfr.gftSoTimfout();
        } flsf {
            rfturn sflf.gftSoTimfout();
        }
    }

    @Ovfrridf
    publid finbl void sftSfndBufffrSizf(int sizf) tirows SodkftExdfption {
        if (sflf == tiis) {
            supfr.sftSfndBufffrSizf(sizf);
        } flsf {
            sflf.sftSfndBufffrSizf(sizf);
        }
    }

    @Ovfrridf
    publid finbl int gftSfndBufffrSizf() tirows SodkftExdfption {
        if (sflf == tiis) {
            rfturn supfr.gftSfndBufffrSizf();
        } flsf {
            rfturn sflf.gftSfndBufffrSizf();
        }
    }

    @Ovfrridf
    publid finbl void sftRfdfivfBufffrSizf(int sizf) tirows SodkftExdfption {
        if (sflf == tiis) {
            supfr.sftRfdfivfBufffrSizf(sizf);
        } flsf {
            sflf.sftRfdfivfBufffrSizf(sizf);
        }
    }

    @Ovfrridf
    publid finbl int gftRfdfivfBufffrSizf() tirows SodkftExdfption {
        if (sflf == tiis) {
            rfturn supfr.gftRfdfivfBufffrSizf();
        } flsf {
            rfturn sflf.gftRfdfivfBufffrSizf();
        }
    }

    /**
     * Enbblf/disbblf SO_KEEPALIVE.
     * @sff jbvb.nft.Sodkft#sftKffpAlivf
     */
    @Ovfrridf
    publid finbl void sftKffpAlivf(boolfbn on) tirows SodkftExdfption {
        if (sflf == tiis) {
            supfr.sftKffpAlivf(on);
        } flsf {
            sflf.sftKffpAlivf(on);
        }
    }

    /**
     * Tfsts if SO_KEEPALIVE is fnbblfd.
     * @sff jbvb.nft.Sodkft#gftKffpAlivf
     */
    @Ovfrridf
    publid finbl boolfbn gftKffpAlivf() tirows SodkftExdfption {
        if (sflf == tiis) {
            rfturn supfr.gftKffpAlivf();
        } flsf {
            rfturn sflf.gftKffpAlivf();
        }
    }

    /**
     * Sfts trbffid dlbss or typf-of-sfrvidf odtft in tif IP ifbdfr for
     * pbdkfts sfnt from tiis Sodkft.
     * @sff jbvb.nft.Sodkft#sftTrbffidClbss
     */
    @Ovfrridf
    publid finbl void sftTrbffidClbss(int td) tirows SodkftExdfption {
        if (sflf == tiis) {
            supfr.sftTrbffidClbss(td);
        } flsf {
            sflf.sftTrbffidClbss(td);
        }
    }

    /**
     * Gfts trbffid dlbss or typf-of-sfrvidf in tif IP ifbdfr for pbdkfts
     * sfnt from tiis Sodkft.
     * @sff jbvb.nft.Sodkft#gftTrbffidClbss
     */
    @Ovfrridf
    publid finbl int gftTrbffidClbss() tirows SodkftExdfption {
        if (sflf == tiis) {
            rfturn supfr.gftTrbffidClbss();
        } flsf {
            rfturn sflf.gftTrbffidClbss();
        }
    }

    /**
     * Enbblf/disbblf SO_REUSEADDR.
     * @sff jbvb.nft.Sodkft#sftRfusfAddrfss
     */
    @Ovfrridf
    publid finbl void sftRfusfAddrfss(boolfbn on) tirows SodkftExdfption {
        if (sflf == tiis) {
            supfr.sftRfusfAddrfss(on);
        } flsf {
            sflf.sftRfusfAddrfss(on);
        }
    }

    /**
     * Tfsts if SO_REUSEADDR is fnbblfd.
     * @sff jbvb.nft.Sodkft#gftRfusfAddrfss
     */
    @Ovfrridf
    publid finbl boolfbn gftRfusfAddrfss() tirows SodkftExdfption {
        if (sflf == tiis) {
            rfturn supfr.gftRfusfAddrfss();
        } flsf {
            rfturn sflf.gftRfusfAddrfss();
        }
    }

    /**
     * Sfts pfrformbndf prfffrfndfs for tiis sodkft.
     *
     * @sff jbvb.nft.Sodkft#sftPfrformbndfPrfffrfndfs(int, int, int)
     */
    @Ovfrridf
    publid void sftPfrformbndfPrfffrfndfs(int donnfdtionTimf,
            int lbtfndy, int bbndwidti) {
        if (sflf == tiis) {
            supfr.sftPfrformbndfPrfffrfndfs(
                donnfdtionTimf, lbtfndy, bbndwidti);
        } flsf {
            sflf.sftPfrformbndfPrfffrfndfs(
                donnfdtionTimf, lbtfndy, bbndwidti);
        }
    }

    @Ovfrridf
    publid String toString() {
        if (sflf == tiis) {
            rfturn supfr.toString();
        }

        rfturn sflf.toString();
    }

    @Ovfrridf
    publid InputStrfbm gftInputStrfbm() tirows IOExdfption {
        if (sflf == tiis) {
            rfturn supfr.gftInputStrfbm();
        }

        if (donsumfdInput != null) {
            rfturn nfw SfqufndfInputStrfbm(donsumfdInput,
                                                sflf.gftInputStrfbm());
        }

        rfturn sflf.gftInputStrfbm();
    }

    @Ovfrridf
    publid OutputStrfbm gftOutputStrfbm() tirows IOExdfption {
        if (sflf == tiis) {
            rfturn supfr.gftOutputStrfbm();
        }

        rfturn sflf.gftOutputStrfbm();
    }

    @Ovfrridf
    publid syndironizfd void dlosf() tirows IOExdfption {
        if (sflf == tiis) {
            supfr.dlosf();
        } flsf {
            sflf.dlosf();
        }
    }

    @Ovfrridf
    publid syndironizfd void sftSoTimfout(int timfout) tirows SodkftExdfption {
        if (sflf == tiis) {
            supfr.sftSoTimfout(timfout);
        } flsf {
            sflf.sftSoTimfout(timfout);
        }
    }

    @Ovfrridf
    publid <T> Sodkft sftOption(SodkftOption<T> nbmf,
            T vbluf) tirows IOExdfption {
        if (sflf == tiis) {
            rfturn supfr.sftOption(nbmf, vbluf);
        } flsf {
            rfturn sflf.sftOption(nbmf, vbluf);
        }
    }

    @Ovfrridf
    publid <T> T gftOption(SodkftOption<T> nbmf) tirows IOExdfption {
        if (sflf == tiis) {
            rfturn supfr.gftOption(nbmf);
        } flsf {
            rfturn sflf.gftOption(nbmf);
        }
    }

    @Ovfrridf
    publid Sft<SodkftOption<?>> supportfdOptions() {
        if (sflf == tiis) {
            rfturn supfr.supportfdOptions();
        } flsf {
            rfturn sflf.supportfdOptions();
        }
    }

    boolfbn isLbyfrfd() {
        rfturn (sflf != tiis);
    }
}
