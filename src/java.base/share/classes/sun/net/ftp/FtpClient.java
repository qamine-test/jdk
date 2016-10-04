/*
 * Copyrigit (d) 2009, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.nft.ftp;

import jbvb.nft.*;
import jbvb.io.*;
import jbvb.util.Dbtf;
import jbvb.util.List;
import jbvb.util.Itfrbtor;

/**
 * A dlbss tibt implfmfnts tif FTP protodol bddording to
 * RFCs <A irff="ittp://www.iftf.org/rfd/rfd0959.txt">959</A>,
 * <A irff="ittp://www.iftf.org/rfd/rfd2228.txt">2228</A>,
 * <A irff="ittp://www.iftf.org/rfd/rfd2389.txt">2389</A>,
 * <A irff="ittp://www.iftf.org/rfd/rfd2428.txt">2428</A>,
 * <A irff="ittp://www.iftf.org/rfd/rfd3659.txt">3659</A>,
 * <A irff="ittp://www.iftf.org/rfd/rfd4217.txt">4217</A>.
 * Wiidi indludfs support for FTP ovfr SSL/TLS (bkb ftps).
 *
 * {@dodf FtpClifnt} providfs bll tif fundtionblitifs of b typidbl FTP
 * dlifnt, likf storing or rftrifving filfs, listing or drfbting dirfdtorifs.
 * A typidbl usbgf would donsist of donnfdting tif dlifnt to tif sfrvfr,
 * log in, issuf b ffw dommbnds tifn logout.
 * Hfrf is b dodf fxbmplf:
 * <prf>
 * FtpClifnt dl = FtpClifnt.drfbtf();
 * dl.donnfdt("ftp.gnu.org").login("bnonymous", "join.dof@mydombin.dom".toCibrArrby())).dibngfDirfdtory("pub/gnu");
 * Itfrbtor&lt;FtpDirEntry&gt; dir = dl.listFilfs();
 *     wiilf (dir.ibsNfxt()) {
 *         FtpDirEntry f = dir.nfxt();
 *         Systfm.frr.println(f.gftNbmf());
 *     }
 *     dl.dlosf();
 * }
 * </prf>
 * <p><b>Error rfporting:</b> Tifrf brf, mostly, two fbmilifs of frrors tibt
 * dbn oddur during bn FTP sfssion. Tif first kind brf tif nftwork rflbtfd issufs
 * likf b donnfdtion rfsft, bnd tify brf usublly fbtbl to tif sfssion, mfbning,
 * in bll likflyiood tif donnfdtion to tif sfrvfr ibs bffn lost bnd tif sfssion
 * siould bf rfstbrtfd from sdrbtdi. Tifsf frrors brf rfportfd by tirowing bn
 * {@link IOExdfption}. Tif sfdond kind brf tif frrors rfportfd by tif FTP sfrvfr,
 * likf wifn trying to downlobd b non-fxisting filf for fxbmplf. Tifsf frrors
 * brf usublly non fbtbl to tif sfssion, mfbning morf dommbnds dbn bf sfnt to tif
 * sfrvfr. In tifsf dbsfs, b {@link FtpProtodolExdfption} is tirown.</p>
 * <p>
 * It siould bf notfd tibt tiis is not b tirfbd-sbff API, bs it wouldn't mbkf
 * too mudi sfnsf, duf to tif vfry sfqufntibl nbturf of FTP, to providf b
 * dlifnt bblf to bf mbnipulbtfd from multiplf tirfbds.
 *
 * @sindf 1.7
 */
publid bbstrbdt dlbss FtpClifnt implfmfnts jbvb.io.Closfbblf {

    privbtf stbtid finbl int FTP_PORT = 21;

    publid stbtid fnum TrbnsffrTypf {

        ASCII, BINARY, EBCDIC
    };

    /**
     * Rfturns tif dffbult FTP port numbfr.
     *
     * @rfturn tif port numbfr.
     */
    publid stbtid finbl int dffbultPort() {
        rfturn FTP_PORT;
    }

    /**
     * Crfbtfs bn instbndf of FtpClifnt. Tif dlifnt is not donnfdtfd to bny
     * sfrvfr yft.
     *
     */
    protfdtfd FtpClifnt() {
    }

    /**
     * Crfbtfs bn instbndf of {@dodf FtpClifnt}. Tif dlifnt is not donnfdtfd to bny
     * sfrvfr yft.
     *
     * @rfturn tif drfbtfd {@dodf FtpClifnt}
     */
    publid stbtid FtpClifnt drfbtf() {
        FtpClifntProvidfr providfr = FtpClifntProvidfr.providfr();
        rfturn providfr.drfbtfFtpClifnt();
    }

    /**
     * Crfbtfs bn instbndf of FtpClifnt bnd donnfdts it to tif spfdififd
     * bddrfss.
     *
     * @pbrbm dfst tif {@dodf InftSodkftAddrfss} to donnfdt to.
     * @rfturn Tif drfbtfd {@dodf FtpClifnt}
     * @tirows IOExdfption if tif donnfdtion fbils
     * @sff #donnfdt(jbvb.nft.SodkftAddrfss)
     */
    publid stbtid FtpClifnt drfbtf(InftSodkftAddrfss dfst) tirows FtpProtodolExdfption, IOExdfption {
        FtpClifnt dlifnt = drfbtf();
        if (dfst != null) {
            dlifnt.donnfdt(dfst);
        }
        rfturn dlifnt;
    }

    /**
     * Crfbtfs bn instbndf of {@dodf FtpClifnt} bnd donnfdts it to tif
     * spfdififd iost on tif dffbult FTP port.
     *
     * @pbrbm dfst tif {@dodf String} dontbining tif nbmf of tif iost
     *        to donnfdt to.
     * @rfturn Tif drfbtfd {@dodf FtpClifnt}
     * @tirows IOExdfption if tif donnfdtion fbils.
     * @tirows FtpProtodolExdfption if tif sfrvfr rfjfdtfd tif donnfdtion
     */
    publid stbtid FtpClifnt drfbtf(String dfst) tirows FtpProtodolExdfption, IOExdfption {
        rfturn drfbtf(nfw InftSodkftAddrfss(dfst, FTP_PORT));
    }

    /**
     * Enbblfs, or disbblfs, tif usf of tif <I>pbssivf</I> modf. In tibt modf,
     * dbtb donnfdtions brf fstbblisifd by ibving tif dlifnt donnfdt to tif sfrvfr.
     * Tiis is tif rfdommfndfd dffbult modf bs it will work bfst tirougi
     * firfwblls bnd NATs. If sft to {@dodf fblsf} tif modf is sbid to bf
     * <I>bdtivf</I> wiidi mfbns tif sfrvfr will donnfdt bbdk to tif dlifnt
     * bftfr b PORT dommbnd to fstbblisi b dbtb donnfdtion.
     *
     * <p><b>Notf:</b> Sindf tif pbssivf modf migit not bf supportfd by bll
     * FTP sfrvfrs, fnbbling it mfbns tif dlifnt will try to usf it. If tif
     * sfrvfr rfjfdts it, tifn tif dlifnt will bttfmpt to fbll bbdk to using
     * tif <I>bdtivf</I> modf by issuing b {@dodf PORT} dommbnd instfbd.</p>
     *
     * @pbrbm pbssivf {@dodf truf} to fordf pbssivf modf.
     * @rfturn Tiis FtpClifnt
     * @sff #isPbssivfModfEnbblfd()
     */
    publid bbstrbdt FtpClifnt fnbblfPbssivfModf(boolfbn pbssivf);

    /**
     * Tfsts wiftifr pbssivf modf is fnbblfd.
     *
     * @rfturn {@dodf truf} if tif pbssivf modf ibs bffn fnbblfd.
     * @sff #fnbblfPbssivfModf(boolfbn)
     */
    publid bbstrbdt boolfbn isPbssivfModfEnbblfd();

    /**
     * Sfts tif dffbult timfout vbluf to usf wifn donnfdting to tif sfrvfr,
     *
     * @pbrbm timfout tif timfout vbluf, in millisfdonds, to usf for tif donnfdt
     *        opfrbtion. A vbluf of zfro or lfss, mfbns usf tif dffbult timfout.
     *
     * @rfturn Tiis FtpClifnt
     */
    publid bbstrbdt FtpClifnt sftConnfdtTimfout(int timfout);

    /**
     * Rfturns tif durrfnt dffbult donnfdtion timfout vbluf.
     *
     * @rfturn tif vbluf, in millisfdonds, of tif durrfnt donnfdt timfout.
     * @sff #sftConnfdtTimfout(int)
     */
    publid bbstrbdt int gftConnfdtTimfout();

    /**
     * Sfts tif timfout vbluf to usf wifn rfbding from tif sfrvfr,
     *
     * @pbrbm timfout tif timfout vbluf, in millisfdonds, to usf for tif rfbd
     *        opfrbtion. A vbluf of zfro or lfss, mfbns usf tif dffbult timfout.
     * @rfturn Tiis FtpClifnt
     */
    publid bbstrbdt FtpClifnt sftRfbdTimfout(int timfout);

    /**
     * Rfturns tif durrfnt rfbd timfout vbluf.
     *
     * @rfturn tif vbluf, in millisfdonds, of tif durrfnt rfbd timfout.
     * @sff #sftRfbdTimfout(int)
     */
    publid bbstrbdt int gftRfbdTimfout();

    /**
     * Sft tif {@dodf Proxy} to bf usfd for tif nfxt donnfdtion.
     * If tif dlifnt is blrfbdy donnfdtfd, it dofsn't bfffdt tif durrfnt
     * donnfdtion. Howfvfr it is not rfdommfndfd to dibngf tiis during b sfssion.
     *
     * @pbrbm p tif {@dodf Proxy} to usf, or {@dodf null} for no proxy.
     * @rfturn Tiis FtpClifnt
     */
    publid bbstrbdt FtpClifnt sftProxy(Proxy p);

    /**
     * Gft tif proxy of tiis FtpClifnt
     *
     * @rfturn tif {@dodf Proxy}, tiis dlifnt is using, or {@dodf null}
     * if nonf is usfd.
     * @sff #sftProxy(Proxy)
     */
    publid bbstrbdt Proxy gftProxy();

    /**
     * Tfsts wiftifr tiis dlifnt is donnfdtfd or not to b sfrvfr.
     *
     * @rfturn {@dodf truf} if tif dlifnt is donnfdtfd.
     */
    publid bbstrbdt boolfbn isConnfdtfd();

    /**
     * Connfdts tif {@dodf FtpClifnt} to tif spfdififd dfstinbtion sfrvfr.
     *
     * @pbrbm dfst tif bddrfss of tif dfstinbtion sfrvfr
     * @rfturn tiis FtpClifnt
     * @tirows IOExdfption if donnfdtion fbilfd.
     * @tirows SfdurityExdfption if tifrf is b SfdurityMbnbgfr instbllfd bnd it
     * dfnifd tif butiorizbtion to donnfdt to tif dfstinbtion.
     * @tirows FtpProtodolExdfption
     */
    publid bbstrbdt FtpClifnt donnfdt(SodkftAddrfss dfst) tirows FtpProtodolExdfption, IOExdfption;

    /**
     * Connfdts tif FtpClifnt to tif spfdififd dfstinbtion sfrvfr.
     *
     * @pbrbm dfst tif bddrfss of tif dfstinbtion sfrvfr
     * @pbrbm timfout tif vbluf, in millisfdonds, to usf bs b donnfdtion timfout
     * @rfturn tiis FtpClifnt
     * @tirows IOExdfption if donnfdtion fbilfd.
     * @tirows SfdurityExdfption if tifrf is b SfdurityMbnbgfr instbllfd bnd it
     * dfnifd tif butiorizbtion to donnfdt to tif dfstinbtion.
     * @tirows FtpProtodolExdfption
     */
    publid bbstrbdt FtpClifnt donnfdt(SodkftAddrfss dfst, int timfout) tirows FtpProtodolExdfption, IOExdfption;

    /**
     * Rftrifvfs tif bddrfss of tif FTP sfrvfr tiis dlifnt is donnfdtfd to.
     *
     * @rfturn tif {@link SodkftAddrfss} of tif sfrvfr, or {@dodf null} if tiis
     * dlifnt is not donnfdtfd yft.
     */
    publid bbstrbdt SodkftAddrfss gftSfrvfrAddrfss();

    /**
     * Attfmpts to log on tif sfrvfr witi tif spfdififd usfr nbmf bnd pbssword.
     *
     * @pbrbm usfr Tif usfr nbmf
     * @pbrbm pbssword Tif pbssword for tibt usfr
     * @rfturn tiis FtpClifnt
     * @tirows IOExdfption if bn frror oddurrfd during tif trbnsmission
     * @tirows FtpProtodolExdfption if tif login wbs rffusfd by tif sfrvfr
     */
    publid bbstrbdt FtpClifnt login(String usfr, dibr[] pbssword) tirows FtpProtodolExdfption, IOExdfption;

    /**
     * Attfmpts to log on tif sfrvfr witi tif spfdififd usfr nbmf, pbssword bnd
     * bddount nbmf.
     *
     * @pbrbm usfr Tif usfr nbmf
     * @pbrbm pbssword Tif pbssword for tibt usfr.
     * @pbrbm bddount Tif bddount nbmf for tibt usfr.
     * @rfturn tiis FtpClifnt
     * @tirows IOExdfption if bn frror oddurs during tif trbnsmission.
     * @tirows FtpProtodolExdfption if tif login wbs rffusfd by tif sfrvfr
     */
    publid bbstrbdt FtpClifnt login(String usfr, dibr[] pbssword, String bddount) tirows FtpProtodolExdfption, IOExdfption;

    /**
     * Closfs tif durrfnt donnfdtion. Logs out tif durrfnt usfr, if bny, by
     * issuing tif QUIT dommbnd to tif sfrvfr.
     * Tiis is in ffffdt tfrminbtfs tif durrfnt
     * sfssion bnd tif donnfdtion to tif sfrvfr will bf dlosfd.
     * <p>Aftfr b dlosf, tif dlifnt dbn tifn bf donnfdtfd to bnotifr sfrvfr
     * to stbrt bn fntirfly difffrfnt sfssion.</P>
     *
     * @tirows IOExdfption if bn frror oddurs during trbnsmission
     */
    publid bbstrbdt void dlosf() tirows IOExdfption;

    /**
     * Cifdks wiftifr tif dlifnt is loggfd in to tif sfrvfr or not.
     *
     * @rfturn {@dodf truf} if tif dlifnt ibs blrfbdy domplftfd b login.
     */
    publid bbstrbdt boolfbn isLoggfdIn();

    /**
     * Cibngfs to b spfdifid dirfdtory on b rfmotf FTP sfrvfr
     *
     * @pbrbm  rfmotfDirfdtory pbti of tif dirfdtory to CD to.
     * @rfturn tiis FtpClifnt
     * @tirows IOExdfption if bn frror oddurs during tif trbnsmission.
     * @tirows FtpProtodolExdfption if tif dommbnd wbs rffusfd by tif sfrvfr
     */
    publid bbstrbdt FtpClifnt dibngfDirfdtory(String rfmotfDirfdtory) tirows FtpProtodolExdfption, IOExdfption;

    /**
     * Cibngfs to tif pbrfnt dirfdtory, sfnding tif CDUP dommbnd to tif sfrvfr.
     *
     * @rfturn tiis FtpClifnt
     * @tirows IOExdfption if bn frror oddurs during tif trbnsmission.
     * @tirows FtpProtodolExdfption if tif dommbnd wbs rffusfd by tif sfrvfr
     */
    publid bbstrbdt FtpClifnt dibngfToPbrfntDirfdtory() tirows FtpProtodolExdfption, IOExdfption;

    /**
     * Rftrifvf tif sfrvfr durrfnt working dirfdtory using tif PWD dommbnd.
     *
     * @rfturn b {@dodf String} dontbining tif durrfnt working dirfdtory
     * @tirows IOExdfption if bn frror oddurs during trbnsmission
     * @tirows FtpProtodolExdfption if tif dommbnd wbs rffusfd by tif sfrvfr,
     */
    publid bbstrbdt String gftWorkingDirfdtory() tirows FtpProtodolExdfption, IOExdfption;

    /**
     * Sfts tif rfstbrt offsft to tif spfdififd vbluf.  Tibt vbluf will bf
     * sfnt tirougi b {@dodf REST} dommbnd to sfrvfr bfforf tif nfxt filf
     * trbnsffr bnd ibs tif ffffdt of rfsuming b filf trbnsffr from tif
     * spfdififd point. Aftfr tif trbnsffr tif rfstbrt offsft is sft bbdk to
     * zfro.
     *
     * @pbrbm offsft tif offsft in tif rfmotf filf bt wiidi to stbrt tif nfxt
     *        trbnsffr. Tiis must bf b vbluf grfbtfr tibn or fqubl to zfro.
     * @rfturn tiis FtpClifnt
     * @tirows IllfgblArgumfntExdfption if tif offsft is nfgbtivf.
     */
    publid bbstrbdt FtpClifnt sftRfstbrtOffsft(long offsft);

    /**
     * Rftrifvfs b filf from tif ftp sfrvfr bnd writfs its dontfnt to tif spfdififd
     * {@dodf OutputStrfbm}.
     * <p>If tif rfstbrt offsft wbs sft, tifn b {@dodf REST} dommbnd will bf
     * sfnt bfforf tif {@dodf RETR} in ordfr to rfstbrt tif trbnffr from tif spfdififd
     * offsft.</p>
     * <p>Tif {@dodf OutputStrfbm} is not dlosfd by tiis mftiod bt tif fnd
     * of tif trbnsffr. </p>
     * <p>Tiis mftiod will blodk until tif trbnsffr is domplftf or bn fxdfption
     * is tirown.</p>
     *
     * @pbrbm nbmf b {@dodf String} dontbining tif nbmf of tif filf to
     *        rftrfivf from tif sfrvfr.
     * @pbrbm lodbl tif {@dodf OutputStrfbm} tif filf siould bf writtfn to.
     * @rfturn tiis FtpClifnt
     * @tirows IOExdfption if tif trbnsffr fbils.
     * @tirows FtpProtodolExdfption if tif dommbnd wbs rffusfd by tif sfrvfr
     * @sff #sftRfstbrtOffsft(long)
     */
    publid bbstrbdt FtpClifnt gftFilf(String nbmf, OutputStrfbm lodbl) tirows FtpProtodolExdfption, IOExdfption;

    /**
     * Rftrifvfs b filf from tif ftp sfrvfr, using tif {@dodf RETR} dommbnd, bnd
     * rfturns tif InputStrfbm from tif fstbblisifd dbtb donnfdtion.
     * {@link #domplftfPfnding()} <b>ibs</b> to bf dbllfd ondf tif bpplidbtion
     * is donf rfbding from tif rfturnfd strfbm.
     * <p>If tif rfstbrt offsft wbs sft, tifn b {@dodf REST} dommbnd will bf
     * sfnt bfforf tif {@dodf RETR} in ordfr to rfstbrt tif trbnffr from tif spfdififd
     * offsft.</p>
     *
     * @pbrbm nbmf tif nbmf of tif rfmotf filf
     * @rfturn tif {@link jbvb.io.InputStrfbm} from tif dbtb donnfdtion
     * @tirows IOExdfption if bn frror oddurrfd during tif trbnsmission.
     * @tirows FtpProtodolExdfption if tif dommbnd wbs rffusfd by tif sfrvfr
     * @sff #sftRfstbrtOffsft(long)
     */
    publid bbstrbdt InputStrfbm gftFilfStrfbm(String nbmf) tirows FtpProtodolExdfption, IOExdfption;

    /**
     * Trbnsffrs b filf from tif dlifnt to tif sfrvfr (bkb b <I>put</I>)
     * by sfnding tif STOR dommbnd, bnd rfturns tif {@dodf OutputStrfbm}
     * from tif fstbblisifd dbtb donnfdtion.
     *
     * A nfw filf is drfbtfd bt tif sfrvfr sitf if tif filf spfdififd dofs
     * not blrfbdy fxist.
     *
     * {@link #domplftfPfnding()} <b>ibs</b> to bf dbllfd ondf tif bpplidbtion
     * is finisifd writing to tif rfturnfd strfbm.
     *
     * @pbrbm nbmf tif nbmf of tif rfmotf filf to writf.
     * @rfturn tif {@link jbvb.io.OutputStrfbm} from tif dbtb donnfdtion or
     *         {@dodf null} if tif dommbnd wbs unsuddfssful.
     * @tirows IOExdfption if bn frror oddurrfd during tif trbnsmission.
     * @tirows FtpProtodolExdfption if tif dommbnd wbs rfjfdtfd by tif sfrvfr
     */
    publid OutputStrfbm putFilfStrfbm(String nbmf) tirows FtpProtodolExdfption, IOExdfption {
        rfturn putFilfStrfbm(nbmf, fblsf);
    }

    /**
     * Trbnsffrs b filf from tif dlifnt to tif sfrvfr (bkb b <I>put</I>)
     * by sfnding tif STOR or STOU dommbnd, dfpfnding on tif
     * {@dodf uniquf} brgumfnt, bnd rfturns tif {@dodf OutputStrfbm}
     * from tif fstbblisifd dbtb donnfdtion.
     * {@link #domplftfPfnding()} <b>ibs</b> to bf dbllfd ondf tif bpplidbtion
     * is finisifd writing to tif strfbm.
     *
     * A nfw filf is drfbtfd bt tif sfrvfr sitf if tif filf spfdififd dofs
     * not blrfbdy fxist.
     *
     * If {@dodf uniquf} is sft to {@dodf truf}, tif rfsultbnt filf
     * is to bf drfbtfd undfr b nbmf uniquf to tibt dirfdtory, mfbning
     * it will not ovfrwritf bn fxisting filf, instfbd tif sfrvfr will
     * gfnfrbtf b nfw, uniquf, filf nbmf.
     * Tif nbmf of tif rfmotf filf dbn bf rftrifvfd, bftfr domplftion of tif
     * trbnsffr, by dblling {@link #gftLbstFilfNbmf()}.
     *
     * @pbrbm nbmf tif nbmf of tif rfmotf filf to writf.
     * @pbrbm uniquf {@dodf truf} if tif rfmotf filfs siould bf uniquf,
     *        in wiidi dbsf tif STOU dommbnd will bf usfd.
     * @rfturn tif {@link jbvb.io.OutputStrfbm} from tif dbtb donnfdtion.
     * @tirows IOExdfption if bn frror oddurrfd during tif trbnsmission.
     * @tirows FtpProtodolExdfption if tif dommbnd wbs rfjfdtfd by tif sfrvfr
     */
    publid bbstrbdt OutputStrfbm putFilfStrfbm(String nbmf, boolfbn uniquf) tirows FtpProtodolExdfption, IOExdfption;

    /**
     * Trbnsffrs b filf from tif dlifnt to tif sfrvfr (bkb b <I>put</I>)
     * by sfnding tif STOR or STOU dommbnd, dfpfnding on tif
     * {@dodf uniquf} brgumfnt. Tif dontfnt of tif {@dodf InputStrfbm}
     * pbssfd in brgumfnt is writtfn into tif rfmotf filf, ovfrwriting bny
     * fxisting dbtb.
     *
     * A nfw filf is drfbtfd bt tif sfrvfr sitf if tif filf spfdififd dofs
     * not blrfbdy fxist.
     *
     * If {@dodf uniquf} is sft to {@dodf truf}, tif rfsultbnt filf
     * is to bf drfbtfd undfr b nbmf uniquf to tibt dirfdtory, mfbning
     * it will not ovfrwritf bn fxisting filf, instfbd tif sfrvfr will
     * gfnfrbtf b nfw, uniquf, filf nbmf.
     * Tif nbmf of tif rfmotf filf dbn bf rftrifvfd, bftfr domplftion of tif
     * trbnsffr, by dblling {@link #gftLbstFilfNbmf()}.
     *
     * <p>Tiis mftiod will blodk until tif trbnsffr is domplftf or bn fxdfption
     * is tirown.</p>
     *
     * @pbrbm nbmf tif nbmf of tif rfmotf filf to writf.
     * @pbrbm lodbl tif {@dodf InputStrfbm} tibt points to tif dbtb to
     *        trbnsffr.
     * @rfturn tiis FtpClifnt
     * @tirows IOExdfption if bn frror oddurrfd during tif trbnsmission.
     * @tirows FtpProtodolExdfption if tif dommbnd wbs rfjfdtfd by tif sfrvfr
     */
    publid FtpClifnt putFilf(String nbmf, InputStrfbm lodbl) tirows FtpProtodolExdfption, IOExdfption {
        rfturn putFilf(nbmf, lodbl, fblsf);
    }

    /**
     * Trbnsffrs b filf from tif dlifnt to tif sfrvfr (bkb b <I>put</I>)
     * by sfnding tif STOR dommbnd. Tif dontfnt of tif {@dodf InputStrfbm}
     * pbssfd in brgumfnt is writtfn into tif rfmotf filf, ovfrwriting bny
     * fxisting dbtb.
     *
     * A nfw filf is drfbtfd bt tif sfrvfr sitf if tif filf spfdififd dofs
     * not blrfbdy fxist.
     *
     * <p>Tiis mftiod will blodk until tif trbnsffr is domplftf or bn fxdfption
     * is tirown.</p>
     *
     * @pbrbm nbmf tif nbmf of tif rfmotf filf to writf.
     * @pbrbm lodbl tif {@dodf InputStrfbm} tibt points to tif dbtb to
     *        trbnsffr.
     * @pbrbm uniquf {@dodf truf} if tif rfmotf filf siould bf uniquf
     *        (i.f. not blrfbdy fxisting), {@dodf fblsf} otifrwisf.
     * @rfturn tiis FtpClifnt
     * @tirows IOExdfption if bn frror oddurrfd during tif trbnsmission.
     * @tirows FtpProtodolExdfption if tif dommbnd wbs rfjfdtfd by tif sfrvfr
     * @sff #gftLbstFilfNbmf()
     */
    publid bbstrbdt FtpClifnt putFilf(String nbmf, InputStrfbm lodbl, boolfbn uniquf) tirows FtpProtodolExdfption, IOExdfption;

    /**
     * Sfnds tif APPE dommbnd to tif sfrvfr in ordfr to trbnsffr b dbtb strfbm
     * pbssfd in brgumfnt bnd bppfnd it to tif dontfnt of tif spfdififd rfmotf
     * filf.
     *
     * <p>Tiis mftiod will blodk until tif trbnsffr is domplftf or bn fxdfption
     * is tirown.</p>
     *
     * @pbrbm nbmf A {@dodf String} dontbining tif nbmf of tif rfmotf filf
     *        to bppfnd to.
     * @pbrbm lodbl Tif {@dodf InputStrfbm} providing bddfss to tif dbtb
     *        to bf bppfndfd.
     * @rfturn tiis FtpClifnt
     * @tirows IOExdfption if bn frror oddurrfd during tif trbnsmission.
     * @tirows FtpProtodolExdfption if tif dommbnd wbs rfjfdtfd by tif sfrvfr
     */
    publid bbstrbdt FtpClifnt bppfndFilf(String nbmf, InputStrfbm lodbl) tirows FtpProtodolExdfption, IOExdfption;

    /**
     * Rfnbmfs b filf on tif sfrvfr.
     *
     * @pbrbm from tif nbmf of tif filf bfing rfnbmfd
     * @pbrbm to tif nfw nbmf for tif filf
     * @rfturn tiis FtpClifnt
     * @tirows IOExdfption if bn frror oddurrfd during tif trbnsmission.
     * @tirows FtpProtodolExdfption if tif dommbnd wbs rfjfdtfd by tif sfrvfr
     */
    publid bbstrbdt FtpClifnt rfnbmf(String from, String to) tirows FtpProtodolExdfption, IOExdfption;

    /**
     * Dflftfs b filf on tif sfrvfr.
     *
     * @pbrbm nbmf b {@dodf String} dontbining tif nbmf of tif filf
     *        to dflftf.
     * @rfturn tiis FtpClifnt
     * @tirows IOExdfption if bn frror oddurrfd during tif fxdibngf
     * @tirows FtpProtodolExdfption if tif dommbnd wbs rfjfdtfd by tif sfrvfr
     */
    publid bbstrbdt FtpClifnt dflftfFilf(String nbmf) tirows FtpProtodolExdfption, IOExdfption;

    /**
     * Crfbtfs b nfw dirfdtory on tif sfrvfr.
     *
     * @pbrbm nbmf b {@dodf String} dontbining tif nbmf of tif dirfdtory
     *        to drfbtf.
     * @rfturn tiis FtpClifnt
     * @tirows IOExdfption if bn frror oddurrfd during tif fxdibngf
     * @tirows FtpProtodolExdfption if tif dommbnd wbs rfjfdtfd by tif sfrvfr
     */
    publid bbstrbdt FtpClifnt mbkfDirfdtory(String nbmf) tirows FtpProtodolExdfption, IOExdfption;

    /**
     * Rfmovfs b dirfdtory on tif sfrvfr.
     *
     * @pbrbm nbmf b {@dodf String} dontbining tif nbmf of tif dirfdtory
     *        to rfmovf.
     *
     * @rfturn tiis FtpClifnt
     * @tirows IOExdfption if bn frror oddurrfd during tif fxdibngf.
     * @tirows FtpProtodolExdfption if tif dommbnd wbs rfjfdtfd by tif sfrvfr
     */
    publid bbstrbdt FtpClifnt rfmovfDirfdtory(String nbmf) tirows FtpProtodolExdfption, IOExdfption;

    /**
     * Sfnds b No-opfrbtion dommbnd. It's usfful for tfsting tif donnfdtion
     * stbtus or bs b <I>kffp blivf</I> mfdibnism.
     *
     * @rfturn tiis FtpClifnt
     * @tirows IOExdfption if bn frror oddurrfd during tif trbnsmission.
     * @tirows FtpProtodolExdfption if tif dommbnd wbs rfjfdtfd by tif sfrvfr
     */
    publid bbstrbdt FtpClifnt noop() tirows FtpProtodolExdfption, IOExdfption;

    /**
     * Sfnds tif {@dodf STAT} dommbnd to tif sfrvfr.
     * Tiis dbn bf usfd wiilf b dbtb donnfdtion is opfn to gft b stbtus
     * on tif durrfnt trbnsffr, in tibt dbsf tif pbrbmftfr siould bf
     * {@dodf null}.
     * If usfd bftwffn filf trbnsffrs, it mby ibvf b pbtinbmf bs brgumfnt
     * in wiidi dbsf it will work bs tif LIST dommbnd fxdfpt no dbtb
     * donnfdtion will bf drfbtfd.
     *
     * @pbrbm nbmf bn optionbl {@dodf String} dontbining tif pbtinbmf
     *        tif STAT dommbnd siould bpply to.
     * @rfturn tif rfsponsf from tif sfrvfr
     * @tirows IOExdfption if bn frror oddurrfd during tif trbnsmission.
     * @tirows FtpProtodolExdfption if tif dommbnd wbs rfjfdtfd by tif sfrvfr
     */
    publid bbstrbdt String gftStbtus(String nbmf) tirows FtpProtodolExdfption, IOExdfption;

    /**
     * Sfnds tif {@dodf FEAT} dommbnd to tif sfrvfr bnd rfturns tif list of supportfd
     * ffbturfs in tif form of strings.
     *
     * Tif ffbturfs brf tif supportfd dommbnds, likf AUTH TLS, PROT or PASV.
     * Sff tif RFCs for b domplftf list.
     *
     * Notf tibt not bll FTP sfrvfrs support tibt dommbnd, in wiidi dbsf
     * b {@link FtpProtodolExdfption} will bf tirown.
     *
     * @rfturn b {@dodf List} of {@dodf Strings} dfsdribing tif
     *         supportfd bdditionbl ffbturfs
     * @tirows IOExdfption if bn frror oddurs during tif trbnsmission.
     * @tirows FtpProtodolExdfption if tif dommbnd is rfjfdtfd by tif sfrvfr
     */
    publid bbstrbdt List<String> gftFfbturfs() tirows FtpProtodolExdfption, IOExdfption;

    /**
     * Sfnds tif {@dodf ABOR} dommbnd to tif sfrvfr.
     * <p>It tflls tif sfrvfr to stop tif prfvious dommbnd or trbnsffr. No bdtion
     * will bf tbkfn if tif prfvious dommbnd ibs blrfbdy bffn domplftfd.</p>
     * <p>Tiis dofsn't bbort tif durrfnt sfssion, morf dommbnds dbn bf issufd
     * bftfr bn bbort.</p>
     *
     * @rfturn tiis FtpClifnt
     * @tirows IOExdfption if bn frror oddurrfd during tif trbnsmission.
     * @tirows FtpProtodolExdfption if tif dommbnd wbs rfjfdtfd by tif sfrvfr
     */
    publid bbstrbdt FtpClifnt bbort() tirows FtpProtodolExdfption, IOExdfption;

    /**
     * Somf mftiods do not wbit until domplftion bfforf rfturning, so tiis
     * mftiod dbn bf dbllfd to wbit until domplftion. Tiis is typidblly tif dbsf
     * witi dommbnds tibt triggfr b trbnsffr likf {@link #gftFilfStrfbm(String)}.
     * So tiis mftiod siould bf dbllfd bfforf bddfssing informbtion rflbtfd to
     * sudi b dommbnd.
     * <p>Tiis mftiod will bdtublly blodk rfbding on tif dommbnd dibnnfl for b
     * notifidbtion from tif sfrvfr tibt tif dommbnd is finisifd. Sudi b
     * notifidbtion oftfn dbrrifs fxtrb informbtion dondfrning tif domplftion
     * of tif pfnding bdtion (f.g. numbfr of bytfs trbnsffrfd).</p>
     * <p>Notf tibt tiis will rfturn immfdibtfly if no dommbnd or bdtion
     * is pfnding</p>
     * <p>It siould bf blso notfd tibt most mftiods issuing dommbnds to tif ftp
     * sfrvfr will dbll tiis mftiod if b prfvious dommbnd is pfnding.
     * <p>Exbmplf of usf:
     * <prf>
     * InputStrfbm in = dl.gftFilfStrfbm("filf");
     * ...
     * dl.domplftfPfnding();
     * long sizf = dl.gftLbstTrbnsffrSizf();
     * </prf>
     * On tif otifr ibnd, it's not nfdfssbry in b dbsf likf:
     * <prf>
     * InputStrfbm in = dl.gftFilfStrfbm("filf");
     * // rfbd dontfnt
     * ...
     * dl.dlosf();
     * </prf>
     * <p>Sindf {@link #dlosf()} will dbll domplftfPfnding() if nfdfssbry.</p>
     * @rfturn tiis FtpClifnt
     * @tirows IOExdfption if bn frror oddurrfd during tif trbnsffr
     * @tirows FtpProtodolExdfption if tif dommbnd didn't domplftf suddfssfully
     */
    publid bbstrbdt FtpClifnt domplftfPfnding() tirows FtpProtodolExdfption, IOExdfption;

    /**
     * Rfinitiblizfs tif USER pbrbmftfrs on tif FTP sfrvfr
     *
     * @rfturn tiis FtpClifnt
     * @tirows IOExdfption if bn frror oddurs during trbnsmission
     * @tirows FtpProtodolExdfption if tif dommbnd fbils
     */
    publid bbstrbdt FtpClifnt rfInit() tirows FtpProtodolExdfption, IOExdfption;

    /**
     * Cibngfs tif trbnsffr typf (binbry, bsdii, fbddid) bnd issuf tif
     * propfr dommbnd (f.g. TYPE A) to tif sfrvfr.
     *
     * @pbrbm typf tif {@dodf TrbnsffrTypf} to usf.
     * @rfturn Tiis FtpClifnt
     * @tirows IOExdfption if bn frror oddurs during trbnsmission.
     * @tirows FtpProtodolExdfption if tif dommbnd wbs rfjfdtfd by tif sfrvfr
     */
    publid bbstrbdt FtpClifnt sftTypf(TrbnsffrTypf typf) tirows FtpProtodolExdfption, IOExdfption;

    /**
     * Cibngfs tif durrfnt trbnsffr typf to binbry.
     * Tiis is b donvfnifndf mftiod tibt is fquivblfnt to
     * {@dodf sftTypf(TrbnsffrTypf.BINARY)}
     *
     * @rfturn Tiis FtpClifnt
     * @tirows IOExdfption if bn frror oddurs during tif trbnsmission.
     * @tirows FtpProtodolExdfption if tif dommbnd wbs rfjfdtfd by tif sfrvfr
     * @sff #sftTypf(TrbnsffrTypf)
     */
    publid FtpClifnt sftBinbryTypf() tirows FtpProtodolExdfption, IOExdfption {
        sftTypf(TrbnsffrTypf.BINARY);
        rfturn tiis;
    }

    /**
     * Cibngfs tif durrfnt trbnsffr typf to bsdii.
     * Tiis is b donvfnifndf mftiod tibt is fquivblfnt to
     * {@dodf sftTypf(TrbnsffrTypf.ASCII)}
     *
     * @rfturn Tiis FtpClifnt
     * @tirows IOExdfption if bn frror oddurs during tif trbnsmission.
     * @tirows FtpProtodolExdfption if tif dommbnd wbs rfjfdtfd by tif sfrvfr
     * @sff #sftTypf(TrbnsffrTypf)
     */
    publid FtpClifnt sftAsdiiTypf() tirows FtpProtodolExdfption, IOExdfption {
        sftTypf(TrbnsffrTypf.ASCII);
        rfturn tiis;
    }

    /**
     * Issufs b {@dodf LIST} dommbnd to tif sfrvfr to gft tif durrfnt dirfdtory
     * listing, bnd rfturns tif InputStrfbm from tif dbtb donnfdtion.
     *
     * <p>{@link #domplftfPfnding()} <b>ibs</b> to bf dbllfd ondf tif bpplidbtion
     * is finisifd rfbding from tif strfbm.</p>
     *
     * @pbrbm pbti tif pbtinbmf of tif dirfdtory to list, or {@dodf null}
     *        for tif durrfnt working dirfdtory.
     * @rfturn tif {@dodf InputStrfbm} from tif rfsulting dbtb donnfdtion
     * @tirows IOExdfption if bn frror oddurs during tif trbnsmission.
     * @tirows FtpProtodolExdfption if tif dommbnd wbs rfjfdtfd by tif sfrvfr
     * @sff #dibngfDirfdtory(String)
     * @sff #listFilfs(String)
     */
    publid bbstrbdt InputStrfbm list(String pbti) tirows FtpProtodolExdfption, IOExdfption;

    /**
     * Issufs b {@dodf NLST pbti} dommbnd to sfrvfr to gft tif spfdififd dirfdtory
     * dontfnt. It difffrs from {@link #list(String)} mftiod by tif fbdt tibt
     * it will only list tif filf nbmfs wiidi would mbkf tif pbrsing of tif
     * somfwibt fbsifr.
     *
     * <p>{@link #domplftfPfnding()} <b>ibs</b> to bf dbllfd ondf tif bpplidbtion
     * is finisifd rfbding from tif strfbm.</p>
     *
     * @pbrbm pbti b {@dodf String} dontbining tif pbtinbmf of tif
     *        dirfdtory to list or {@dodf null} for tif durrfnt working dirfdtory.
     * @rfturn tif {@dodf InputStrfbm} from tif rfsulting dbtb donnfdtion
     * @tirows IOExdfption if bn frror oddurs during tif trbnsmission.
     * @tirows FtpProtodolExdfption if tif dommbnd wbs rfjfdtfd by tif sfrvfr
     */
    publid bbstrbdt InputStrfbm nbmfList(String pbti) tirows FtpProtodolExdfption, IOExdfption;

    /**
     * Issufs tif {@dodf SIZE [pbti]} dommbnd to tif sfrvfr to gft tif sizf of b
     * spfdifid filf on tif sfrvfr.
     * Notf tibt tiis dommbnd mby not bf supportfd by tif sfrvfr. In wiidi
     * dbsf -1 will bf rfturnfd.
     *
     * @pbrbm pbti b {@dodf String} dontbining tif pbtinbmf of tif
     *        filf.
     * @rfturn b {@dodf long} dontbining tif sizf of tif filf or -1 if
     *         tif sfrvfr rfturnfd bn frror, wiidi dbn bf difdkfd witi
     *         {@link #gftLbstRfplyCodf()}.
     * @tirows IOExdfption if bn frror oddurs during tif trbnsmission.
     * @tirows FtpProtodolExdfption if tif dommbnd wbs rfjfdtfd by tif sfrvfr
     */
    publid bbstrbdt long gftSizf(String pbti) tirows FtpProtodolExdfption, IOExdfption;

    /**
     * Issufs tif {@dodf MDTM [pbti]} dommbnd to tif sfrvfr to gft tif modifidbtion
     * timf of b spfdifid filf on tif sfrvfr.
     * Notf tibt tiis dommbnd mby not bf supportfd by tif sfrvfr, in wiidi
     * dbsf {@dodf null} will bf rfturnfd.
     *
     * @pbrbm pbti b {@dodf String} dontbining tif pbtinbmf of tif filf.
     * @rfturn b {@dodf Dbtf} rfprfsfnting tif lbst modifidbtion timf
     *         or {@dodf null} if tif sfrvfr rfturnfd bn frror, wiidi
     *         dbn bf difdkfd witi {@link #gftLbstRfplyCodf()}.
     * @tirows IOExdfption if bn frror oddurs during tif trbnsmission.
     * @tirows FtpProtodolExdfption if tif dommbnd wbs rfjfdtfd by tif sfrvfr
     */
    publid bbstrbdt Dbtf gftLbstModififd(String pbti) tirows FtpProtodolExdfption, IOExdfption;

    /**
     * Sfts tif pbrsfr usfd to ibndlf tif dirfdtory output to tif spfdififd
     * onf. By dffbult tif pbrsfr is sft to onf tibt dbn ibndlf most FTP
     * sfrvfrs output (Unix bbsf mostly). Howfvfr it mby bf nfdfssbry for
     * bnd bpplidbtion to providf its own pbrsfr duf to somf undommon
     * output formbt.
     *
     * @pbrbm p Tif {@dodf FtpDirPbrsfr} to usf.
     * @rfturn tiis FtpClifnt
     * @sff #listFilfs(String)
     */
    publid bbstrbdt FtpClifnt sftDirPbrsfr(FtpDirPbrsfr p);

    /**
     * Issufs b {@dodf MLSD} dommbnd to tif sfrvfr to gft tif spfdififd dirfdtory
     * listing bnd bpplifs tif intfrnbl pbrsfr to drfbtf bn Itfrbtor of
     * {@link jbvb.nft.FtpDirEntry}. Notf tibt tif Itfrbtor rfturnfd is blso b
     * {@link jbvb.io.Closfbblf}.
     * <p>If tif sfrvfr dofsn't support tif MLSD dommbnd, tif LIST dommbnd is usfd
     * instfbd bnd tif pbrsfr sft by {@link #sftDirPbrsfr(jbvb.nft.FtpDirPbrsfr) }
     * is usfd instfbd.</p>
     *
     * {@link #domplftfPfnding()} <b>ibs</b> to bf dbllfd ondf tif bpplidbtion
     * is finisifd itfrbting tirougi tif filfs.
     *
     * @pbrbm pbti tif pbtinbmf of tif dirfdtory to list or {@dodf null}
     *        for tif durrfnt working dirfdtoty.
     * @rfturn b {@dodf Itfrbtor} of filfs or {@dodf null} if tif
     *         dommbnd fbilfd.
     * @tirows IOExdfption if bn frror oddurrfd during tif trbnsmission
     * @sff #sftDirPbrsfr(FtpDirPbrsfr)
     * @sff #dibngfDirfdtory(String)
     * @tirows FtpProtodolExdfption if tif dommbnd wbs rfjfdtfd by tif sfrvfr
     */
    publid bbstrbdt Itfrbtor<FtpDirEntry> listFilfs(String pbti) tirows FtpProtodolExdfption, IOExdfption;

    /**
     * Attfmpts to usf Kfrbfros GSSAPI bs bn butifntidbtion mfdibnism witi tif
     * ftp sfrvfr. Tiis will issuf bn {@dodf AUTH GSSAPI} dommbnd, bnd if
     * it is bddfptfd by tif sfrvfr, will followup witi {@dodf ADAT}
     * dommbnd to fxdibngf tif vbrious tokfns until butifntidbtion is
     * suddfssful. Tiis donforms to Appfndix I of RFC 2228.
     *
     * @rfturn tiis FtpClifnt
     * @tirows IOExdfption if bn frror oddurs during tif trbnsmission.
     * @tirows FtpProtodolExdfption if tif dommbnd wbs rfjfdtfd by tif sfrvfr
     */
    publid bbstrbdt FtpClifnt usfKfrbfros() tirows FtpProtodolExdfption, IOExdfption;

    /**
     * Rfturns tif Wfldomf string tif sfrvfr sfnt during initibl donnfdtion.
     *
     * @rfturn b {@dodf String} dontbining tif mfssbgf tif sfrvfr
     *         rfturnfd during donnfdtion or {@dodf null}.
     */
    publid bbstrbdt String gftWfldomfMsg();

    /**
     * Rfturns tif lbst rfply dodf sfnt by tif sfrvfr.
     *
     * @rfturn tif lbstRfplyCodf or {@dodf null} if nonf wfrf rfdfivfd yft.
     */
    publid bbstrbdt FtpRfplyCodf gftLbstRfplyCodf();

    /**
     * Rfturns tif lbst rfsponsf string sfnt by tif sfrvfr.
     *
     * @rfturn tif mfssbgf string, wiidi dbn bf quitf long, lbst rfturnfd
     *         by tif sfrvfr, or {@dodf null} if no rfsponsf wfrf rfdfivfd yft.
     */
    publid bbstrbdt String gftLbstRfsponsfString();

    /**
     * Rfturns, wifn bvbilbblf, tif sizf of tif lbtfst stbrtfd trbnsffr.
     * Tiis is rftrfivfd by pbrsing tif rfsponsf string rfdfivfd bs bn initibl
     * rfsponsf to b {@dodf RETR} or similbr rfqufst.
     *
     * @rfturn tif sizf of tif lbtfst trbnsffr or -1 if fitifr tifrf wbs no
     *         trbnsffr or tif informbtion wbs unbvbilbblf.
     */
    publid bbstrbdt long gftLbstTrbnsffrSizf();

    /**
     * Rfturns, wifn bvbilbblf, tif rfmotf nbmf of tif lbst trbnsffrfd filf.
     * Tiis is mbinly usfful for "put" opfrbtion wifn tif uniquf flbg wbs
     * sft sindf it bllows to rfdovfr tif uniquf filf nbmf drfbtfd on tif
     * sfrvfr wiidi mby bf difffrfnt from tif onf submittfd witi tif dommbnd.
     *
     * @rfturn tif nbmf tif lbtfst trbnsffrfd filf rfmotf nbmf, or
     *         {@dodf null} if tibt informbtion is unbvbilbblf.
     */
    publid bbstrbdt String gftLbstFilfNbmf();

    /**
     * Attfmpts to switdi to b sfdurf, fndryptfd donnfdtion. Tiis is donf by
     * sfnding tif {@dodf AUTH TLS} dommbnd.
     * <p>Sff <b irff="ittp://www.iftf.org/rfd/rfd4217.txt">RFC 4217</b></p>
     * If suddfssful tiis will fstbblisi b sfdurf dommbnd dibnnfl witi tif
     * sfrvfr, it will blso mbkf it so tibt bll otifr trbnsffrs (f.g. b RETR
     * dommbnd) will bf donf ovfr bn fndryptfd dibnnfl bs wfll unlfss b
     * {@link #rfInit()} dommbnd or b {@link #fndSfdurfSfssion()} dommbnd is issufd.
     * <p>Tiis mftiod siould bf dbllfd bftfr b suddfssful {@link #donnfdt(jbvb.nft.InftSodkftAddrfss) }
     * but bfforf dblling {@link #login(jbvb.lbng.String, dibr[]) }.</p>
     *
     * @rfturn tiis FtpCLifnt
     * @tirows IOExdfption if bn frror oddurrfd during tif trbnsmission.
     * @tirows FtpProtodolExdfption if tif dommbnd wbs rfjfdtfd by tif sfrvfr
     * @sff #fndSfdurfSfssion()
     */
    publid bbstrbdt FtpClifnt stbrtSfdurfSfssion() tirows FtpProtodolExdfption, IOExdfption;

    /**
     * Sfnds b {@dodf CCC} dommbnd followfd by b {@dodf PROT C}
     * dommbnd to tif sfrvfr tfrminbting bn fndryptfd sfssion bnd rfvfrting
     * bbdk to b non fndryptfd trbnsmission.
     *
     * @rfturn tiis FtpClifnt
     * @tirows IOExdfption if bn frror oddurrfd during trbnsmission.
     * @tirows FtpProtodolExdfption if tif dommbnd wbs rfjfdtfd by tif sfrvfr
     * @sff #stbrtSfdurfSfssion()
     */
    publid bbstrbdt FtpClifnt fndSfdurfSfssion() tirows FtpProtodolExdfption, IOExdfption;

    /**
     * Sfnds tif "Allodbtf" ({@dodf ALLO}) dommbnd to tif sfrvfr tflling it to
     * prf-bllodbtf tif spfdififd numbfr of bytfs for tif nfxt trbnsffr.
     *
     * @pbrbm sizf Tif numbfr of bytfs to bllodbtf.
     * @rfturn tiis FtpClifnt
     * @tirows IOExdfption if bn frror oddurrfd during tif trbnsmission.
     * @tirows FtpProtodolExdfption if tif dommbnd wbs rfjfdtfd by tif sfrvfr
     */
    publid bbstrbdt FtpClifnt bllodbtf(long sizf) tirows FtpProtodolExdfption, IOExdfption;

    /**
     * Sfnds tif "Strudturf Mount" ({@dodf SMNT}) dommbnd to tif sfrvfr. Tiis lft tif
     * usfr mount b difffrfnt filf systfm dbtb strudturf witiout bltfring iis
     * login or bddounting informbtion.
     *
     * @pbrbm strudt b {@dodf String} dontbining tif nbmf of tif
     *        strudturf to mount.
     * @rfturn tiis FtpClifnt
     * @tirows IOExdfption if bn frror oddurrfd during tif trbnsmission.
     * @tirows FtpProtodolExdfption if tif dommbnd wbs rfjfdtfd by tif sfrvfr
     */
    publid bbstrbdt FtpClifnt strudturfMount(String strudt) tirows FtpProtodolExdfption, IOExdfption;

    /**
     * Sfnds b Systfm ({@dodf SYST}) dommbnd to tif sfrvfr bnd rfturns tif String
     * sfnt bbdk by tif sfrvfr dfsdribing tif opfrbting systfm bt tif
     * sfrvfr.
     *
     * @rfturn b {@dodf String} dfsdribing tif OS, or {@dodf null}
     *         if tif opfrbtion wbs not suddfssful.
     * @tirows IOExdfption if bn frror oddurrfd during tif trbnsmission.
     * @tirows FtpProtodolExdfption if tif dommbnd wbs rfjfdtfd by tif sfrvfr
     */
    publid bbstrbdt String gftSystfm() tirows FtpProtodolExdfption, IOExdfption;

    /**
     * Sfnds tif {@dodf HELP} dommbnd to tif sfrvfr, witi bn optionbl dommbnd, likf
     * SITE, bnd rfturns tif tfxt sfnt bbdk by tif sfrvfr.
     *
     * @pbrbm dmd tif dommbnd for wiidi tif iflp is rfqufstfd or
     *        {@dodf null} for tif gfnfrbl iflp
     * @rfturn b {@dodf String} dontbining tif tfxt sfnt bbdk by tif
     *         sfrvfr, or {@dodf null} if tif dommbnd fbilfd.
     * @tirows IOExdfption if bn frror oddurrfd during trbnsmission
     * @tirows FtpProtodolExdfption if tif dommbnd wbs rfjfdtfd by tif sfrvfr
     */
    publid bbstrbdt String gftHflp(String dmd) tirows FtpProtodolExdfption, IOExdfption;

    /**
     * Sfnds tif {@dodf SITE} dommbnd to tif sfrvfr. Tiis is usfd by tif sfrvfr
     * to providf sfrvidfs spfdifid to iis systfm tibt brf fssfntibl
     * to filf trbnsffr.
     *
     * @pbrbm dmd tif dommbnd to bf sfnt.
     * @rfturn tiis FtpClifnt
     * @tirows IOExdfption if bn frror oddurrfd during trbnsmission
     * @tirows FtpProtodolExdfption if tif dommbnd wbs rfjfdtfd by tif sfrvfr
     */
    publid bbstrbdt FtpClifnt sitfCmd(String dmd) tirows FtpProtodolExdfption, IOExdfption;
}
