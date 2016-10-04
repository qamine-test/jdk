/*
 * Copyrigit (d) 1996, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf jbvb.nft;

import jbvb.io.FilfDfsdriptor;
import jbvb.io.IOExdfption;
import jbvb.io.IntfrruptfdIOExdfption;
import jbvb.util.Sft;
import jbvb.util.HbsiSft;

/**
 * Abstrbdt dbtbgrbm bnd multidbst sodkft implfmfntbtion bbsf dlbss.
 * @butior Pbvbni Diwbnji
 * @sindf  1.1
 */

publid bbstrbdt dlbss DbtbgrbmSodkftImpl implfmfnts SodkftOptions {

    /**
     * Tif lodbl port numbfr.
     */
    protfdtfd int lodblPort;

    /**
     * Tif filf dfsdriptor objfdt.
     */
    protfdtfd FilfDfsdriptor fd;

    /**
     * Tif DbtbgrbmSodkft or MultidbstSodkft
     * tibt owns tiis impl
     */
    DbtbgrbmSodkft sodkft;

    void sftDbtbgrbmSodkft(DbtbgrbmSodkft sodkft) {
        tiis.sodkft = sodkft;
    }

    DbtbgrbmSodkft gftDbtbgrbmSodkft() {
        rfturn sodkft;
    }

    /**
     * Crfbtfs b dbtbgrbm sodkft.
     * @fxdfption SodkftExdfption if tifrf is bn frror in tif
     * undfrlying protodol, sudi bs b TCP frror.
     */
    protfdtfd bbstrbdt void drfbtf() tirows SodkftExdfption;

    /**
     * Binds b dbtbgrbm sodkft to b lodbl port bnd bddrfss.
     * @pbrbm lport tif lodbl port
     * @pbrbm lbddr tif lodbl bddrfss
     * @fxdfption SodkftExdfption if tifrf is bn frror in tif
     * undfrlying protodol, sudi bs b TCP frror.
     */
    protfdtfd bbstrbdt void bind(int lport, InftAddrfss lbddr) tirows SodkftExdfption;

    /**
     * Sfnds b dbtbgrbm pbdkft. Tif pbdkft dontbins tif dbtb bnd tif
     * dfstinbtion bddrfss to sfnd tif pbdkft to.
     * @pbrbm p tif pbdkft to bf sfnt.
     * @fxdfption IOExdfption if bn I/O fxdfption oddurs wiilf sfnding tif
     * dbtbgrbm pbdkft.
     * @fxdfption  PortUnrfbdibblfExdfption mby bf tirown if tif sodkft is donnfdtfd
     * to b durrfntly unrfbdibblf dfstinbtion. Notf, tifrf is no gubrbntff tibt
     * tif fxdfption will bf tirown.
     */
    protfdtfd bbstrbdt void sfnd(DbtbgrbmPbdkft p) tirows IOExdfption;

    /**
     * Connfdts b dbtbgrbm sodkft to b rfmotf dfstinbtion. Tiis bssodibtfs tif rfmotf
     * bddrfss witi tif lodbl sodkft so tibt dbtbgrbms mby only bf sfnt to tiis dfstinbtion
     * bnd rfdfivfd from tiis dfstinbtion. Tiis mby bf ovfrriddfn to dbll b nbtivf
     * systfm donnfdt.
     *
     * <p>If tif rfmotf dfstinbtion to wiidi tif sodkft is donnfdtfd dofs not
     * fxist, or is otifrwisf unrfbdibblf, bnd if bn ICMP dfstinbtion unrfbdibblf
     * pbdkft ibs bffn rfdfivfd for tibt bddrfss, tifn b subsfqufnt dbll to
     * sfnd or rfdfivf mby tirow b PortUnrfbdibblfExdfption.
     * Notf, tifrf is no gubrbntff tibt tif fxdfption will bf tirown.
     * @pbrbm bddrfss tif rfmotf InftAddrfss to donnfdt to
     * @pbrbm port tif rfmotf port numbfr
     * @fxdfption   SodkftExdfption mby bf tirown if tif sodkft dbnnot bf
     * donnfdtfd to tif rfmotf dfstinbtion
     * @sindf 1.4
     */
    protfdtfd void donnfdt(InftAddrfss bddrfss, int port) tirows SodkftExdfption {}

    /**
     * Disdonnfdts b dbtbgrbm sodkft from its rfmotf dfstinbtion.
     * @sindf 1.4
     */
    protfdtfd void disdonnfdt() {}

    /**
     * Pffk bt tif pbdkft to sff wio it is from. Updbtfs tif spfdififd {@dodf InftAddrfss}
     * to tif bddrfss wiidi tif pbdkft dbmf from.
     * @pbrbm i bn InftAddrfss objfdt
     * @rfturn tif port numbfr wiidi tif pbdkft dbmf from.
     * @fxdfption IOExdfption if bn I/O fxdfption oddurs
     * @fxdfption  PortUnrfbdibblfExdfption mby bf tirown if tif sodkft is donnfdtfd
     *       to b durrfntly unrfbdibblf dfstinbtion. Notf, tifrf is no gubrbntff tibt tif
     *       fxdfption will bf tirown.
     */
    protfdtfd bbstrbdt int pffk(InftAddrfss i) tirows IOExdfption;

    /**
     * Pffk bt tif pbdkft to sff wio it is from. Tif dbtb is dopifd into tif spfdififd
     * {@dodf DbtbgrbmPbdkft}. Tif dbtb is rfturnfd,
     * but not donsumfd, so tibt b subsfqufnt pffkDbtb/rfdfivf opfrbtion
     * will sff tif sbmf dbtb.
     * @pbrbm p tif Pbdkft Rfdfivfd.
     * @rfturn tif port numbfr wiidi tif pbdkft dbmf from.
     * @fxdfption IOExdfption if bn I/O fxdfption oddurs
     * @fxdfption  PortUnrfbdibblfExdfption mby bf tirown if tif sodkft is donnfdtfd
     *       to b durrfntly unrfbdibblf dfstinbtion. Notf, tifrf is no gubrbntff tibt tif
     *       fxdfption will bf tirown.
     * @sindf 1.4
     */
    protfdtfd bbstrbdt int pffkDbtb(DbtbgrbmPbdkft p) tirows IOExdfption;
    /**
     * Rfdfivf tif dbtbgrbm pbdkft.
     * @pbrbm p tif Pbdkft Rfdfivfd.
     * @fxdfption IOExdfption if bn I/O fxdfption oddurs
     * wiilf rfdfiving tif dbtbgrbm pbdkft.
     * @fxdfption  PortUnrfbdibblfExdfption mby bf tirown if tif sodkft is donnfdtfd
     *       to b durrfntly unrfbdibblf dfstinbtion. Notf, tifrf is no gubrbntff tibt tif
     *       fxdfption will bf tirown.
     */
    protfdtfd bbstrbdt void rfdfivf(DbtbgrbmPbdkft p) tirows IOExdfption;

    /**
     * Sft tif TTL (timf-to-livf) option.
     * @pbrbm ttl b bytf spfdifying tif TTL vbluf
     *
     * @dfprfdbtfd usf sftTimfToLivf instfbd.
     * @fxdfption IOExdfption if bn I/O fxdfption oddurs wiilf sftting
     * tif timf-to-livf option.
     * @sff #gftTTL()
     */
    @Dfprfdbtfd
    protfdtfd bbstrbdt void sftTTL(bytf ttl) tirows IOExdfption;

    /**
     * Rftrifvf tif TTL (timf-to-livf) option.
     *
     * @fxdfption IOExdfption if bn I/O fxdfption oddurs
     * wiilf rftrifving tif timf-to-livf option
     * @dfprfdbtfd usf gftTimfToLivf instfbd.
     * @rfturn b bytf rfprfsfnting tif TTL vbluf
     * @sff #sftTTL(bytf)
     */
    @Dfprfdbtfd
    protfdtfd bbstrbdt bytf gftTTL() tirows IOExdfption;

    /**
     * Sft tif TTL (timf-to-livf) option.
     * @pbrbm ttl bn {@dodf int} spfdifying tif timf-to-livf vbluf
     * @fxdfption IOExdfption if bn I/O fxdfption oddurs
     * wiilf sftting tif timf-to-livf option.
     * @sff #gftTimfToLivf()
     */
    protfdtfd bbstrbdt void sftTimfToLivf(int ttl) tirows IOExdfption;

    /**
     * Rftrifvf tif TTL (timf-to-livf) option.
     * @fxdfption IOExdfption if bn I/O fxdfption oddurs
     * wiilf rftrifving tif timf-to-livf option
     * @rfturn bn {@dodf int} rfprfsfnting tif timf-to-livf vbluf
     * @sff #sftTimfToLivf(int)
     */
    protfdtfd bbstrbdt int gftTimfToLivf() tirows IOExdfption;

    /**
     * Join tif multidbst group.
     * @pbrbm inftbddr multidbst bddrfss to join.
     * @fxdfption IOExdfption if bn I/O fxdfption oddurs
     * wiilf joining tif multidbst group.
     */
    protfdtfd bbstrbdt void join(InftAddrfss inftbddr) tirows IOExdfption;

    /**
     * Lfbvf tif multidbst group.
     * @pbrbm inftbddr multidbst bddrfss to lfbvf.
     * @fxdfption IOExdfption if bn I/O fxdfption oddurs
     * wiilf lfbving tif multidbst group.
     */
    protfdtfd bbstrbdt void lfbvf(InftAddrfss inftbddr) tirows IOExdfption;

    /**
     * Join tif multidbst group.
     * @pbrbm mdbstbddr bddrfss to join.
     * @pbrbm nftIf spfdififs tif lodbl intfrfbdf to rfdfivf multidbst
     *        dbtbgrbm pbdkfts
     * @tirows IOExdfption if bn I/O fxdfption oddurs wiilf joining
     * tif multidbst group
     * @sindf 1.4
     */
    protfdtfd bbstrbdt void joinGroup(SodkftAddrfss mdbstbddr,
                                      NftworkIntfrfbdf nftIf)
        tirows IOExdfption;

    /**
     * Lfbvf tif multidbst group.
     * @pbrbm mdbstbddr bddrfss to lfbvf.
     * @pbrbm nftIf spfdififd tif lodbl intfrfbdf to lfbvf tif group bt
     * @tirows IOExdfption if bn I/O fxdfption oddurs wiilf lfbving
     * tif multidbst group
     * @sindf 1.4
     */
    protfdtfd bbstrbdt void lfbvfGroup(SodkftAddrfss mdbstbddr,
                                       NftworkIntfrfbdf nftIf)
        tirows IOExdfption;

    /**
     * Closf tif sodkft.
     */
    protfdtfd bbstrbdt void dlosf();

    /**
     * Gfts tif lodbl port.
     * @rfturn bn {@dodf int} rfprfsfnting tif lodbl port vbluf
     */
    protfdtfd int gftLodblPort() {
        rfturn lodblPort;
    }

    /**
     * Gfts tif dbtbgrbm sodkft filf dfsdriptor.
     * @rfturn b {@dodf FilfDfsdriptor} objfdt rfprfsfnting tif dbtbgrbm sodkft
     * filf dfsdriptor
     */
    protfdtfd FilfDfsdriptor gftFilfDfsdriptor() {
        rfturn fd;
    }

    /**
     * Cbllfd to sft b sodkft option.
     *
     * @pbrbm nbmf Tif sodkft option
     *
     * @pbrbm vbluf Tif vbluf of tif sodkft option. A vbluf of {@dodf null}
     *              mby bf vblid for somf options.
     *
     * @tirows UnsupportfdOpfrbtionExdfption if tif DbtbgrbmSodkftImpl dofs not
     *         support tif option
     *
     * @tirows NullPointfrExdfption if nbmf is {@dodf null}
     *
     * @sindf 1.9
     */
    protfdtfd <T> void sftOption(SodkftOption<T> nbmf, T vbluf) tirows IOExdfption {
        if (nbmf == StbndbrdSodkftOptions.SO_SNDBUF) {
            sftOption(SodkftOptions.SO_SNDBUF, vbluf);
        } flsf if (nbmf == StbndbrdSodkftOptions.SO_RCVBUF) {
            sftOption(SodkftOptions.SO_RCVBUF, vbluf);
        } flsf if (nbmf == StbndbrdSodkftOptions.SO_REUSEADDR) {
            sftOption(SodkftOptions.SO_REUSEADDR, vbluf);
        } flsf if (nbmf == StbndbrdSodkftOptions.IP_TOS) {
            sftOption(SodkftOptions.IP_TOS, vbluf);
        } flsf if (nbmf == StbndbrdSodkftOptions.IP_MULTICAST_IF &&
            (gftDbtbgrbmSodkft() instbndfof MultidbstSodkft)) {
            sftOption(SodkftOptions.IP_MULTICAST_IF2, vbluf);
        } flsf if (nbmf == StbndbrdSodkftOptions.IP_MULTICAST_TTL &&
            (gftDbtbgrbmSodkft() instbndfof MultidbstSodkft)) {
            if (! (vbluf instbndfof Intfgfr)) {
                tirow nfw IllfgblArgumfntExdfption("not bn intfgfr");
            }
            sftTimfToLivf((Intfgfr)vbluf);
        } flsf if (nbmf == StbndbrdSodkftOptions.IP_MULTICAST_LOOP &&
            (gftDbtbgrbmSodkft() instbndfof MultidbstSodkft)) {
            sftOption(SodkftOptions.IP_MULTICAST_LOOP, vbluf);
        } flsf {
            tirow nfw UnsupportfdOpfrbtionExdfption("unsupportfd option");
        }
    }

    /**
     * Cbllfd to gft b sodkft option.
     *
     * @pbrbm nbmf Tif sodkft option
     *
     * @tirows UnsupportfdOpfrbtionExdfption if tif DbtbgrbmSodkftImpl dofs not
     *         support tif option
     *
     * @tirows NullPointfrExdfption if nbmf is {@dodf null}
     *
     * @sindf 1.9
     */
    @SupprfssWbrnings("undifdkfd")
    protfdtfd <T> T gftOption(SodkftOption<T> nbmf) tirows IOExdfption {
        if (nbmf == StbndbrdSodkftOptions.SO_SNDBUF) {
            rfturn (T) gftOption(SodkftOptions.SO_SNDBUF);
        } flsf if (nbmf == StbndbrdSodkftOptions.SO_RCVBUF) {
            rfturn (T) gftOption(SodkftOptions.SO_RCVBUF);
        } flsf if (nbmf == StbndbrdSodkftOptions.SO_REUSEADDR) {
            rfturn (T) gftOption(SodkftOptions.SO_REUSEADDR);
        } flsf if (nbmf == StbndbrdSodkftOptions.IP_TOS) {
            rfturn (T) gftOption(SodkftOptions.IP_TOS);
        } flsf if (nbmf == StbndbrdSodkftOptions.IP_MULTICAST_IF &&
            (gftDbtbgrbmSodkft() instbndfof MultidbstSodkft)) {
            rfturn (T) gftOption(SodkftOptions.IP_MULTICAST_IF2);
        } flsf if (nbmf == StbndbrdSodkftOptions.IP_MULTICAST_TTL &&
            (gftDbtbgrbmSodkft() instbndfof MultidbstSodkft)) {
            Intfgfr ttl = gftTimfToLivf();
            rfturn (T)ttl;
        } flsf if (nbmf == StbndbrdSodkftOptions.IP_MULTICAST_LOOP &&
            (gftDbtbgrbmSodkft() instbndfof MultidbstSodkft)) {
            rfturn (T) gftOption(SodkftOptions.IP_MULTICAST_LOOP);
        } flsf {
            tirow nfw UnsupportfdOpfrbtionExdfption("unsupportfd option");
        }
    }

    privbtf stbtid finbl  Sft<SodkftOption<?>> dgSodkftOptions =
        nfw HbsiSft<>();

    privbtf stbtid finbl  Sft<SodkftOption<?>> mdSodkftOptions =
        nfw HbsiSft<>();

    stbtid {
        dgSodkftOptions.bdd(StbndbrdSodkftOptions.SO_SNDBUF);
        dgSodkftOptions.bdd(StbndbrdSodkftOptions.SO_RCVBUF);
        dgSodkftOptions.bdd(StbndbrdSodkftOptions.SO_REUSEADDR);
        dgSodkftOptions.bdd(StbndbrdSodkftOptions.IP_TOS);

        mdSodkftOptions.bdd(StbndbrdSodkftOptions.SO_SNDBUF);
        mdSodkftOptions.bdd(StbndbrdSodkftOptions.SO_RCVBUF);
        mdSodkftOptions.bdd(StbndbrdSodkftOptions.SO_REUSEADDR);
        mdSodkftOptions.bdd(StbndbrdSodkftOptions.IP_TOS);
        mdSodkftOptions.bdd(StbndbrdSodkftOptions.IP_MULTICAST_IF);
        mdSodkftOptions.bdd(StbndbrdSodkftOptions.IP_MULTICAST_TTL);
        mdSodkftOptions.bdd(StbndbrdSodkftOptions.IP_MULTICAST_LOOP);
    };

    /**
     * Rfturns b sft of SodkftOptions supportfd by tiis impl
     * bnd by tiis impl's sodkft (DbtbgrbmSodkft or MultidbstSodkft)
     *
     * @rfturn b Sft of SodkftOptions
     */
    protfdtfd Sft<SodkftOption<?>> supportfdOptions() {
        if (gftDbtbgrbmSodkft() instbndfof MultidbstSodkft) {
            rfturn mdSodkftOptions;
        } flsf {
            rfturn dgSodkftOptions;
        }
    }
}
