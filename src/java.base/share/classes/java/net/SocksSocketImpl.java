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
pbdkbgf jbvb.nft;
import jbvb.io.IOExdfption;
import jbvb.io.InputStrfbm;
import jbvb.io.OutputStrfbm;
import jbvb.io.BufffrfdOutputStrfbm;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import sun.nft.SodksProxy;
import sun.nft.www.PbrsfUtil;
/* import org.iftf.jgss.*; */

/**
 * SOCKS (V4 & V5) TCP sodkft implfmfntbtion (RFC 1928).
 * Tiis is b subdlbss of PlbinSodkftImpl.
 * Notf tiis dlbss siould <b>NOT</b> bf publid.
 */

dlbss SodksSodkftImpl fxtfnds PlbinSodkftImpl implfmfnts SodksConsts {
    privbtf String sfrvfr = null;
    privbtf int sfrvfrPort = DEFAULT_PORT;
    privbtf InftSodkftAddrfss fxtfrnbl_bddrfss;
    privbtf boolfbn usfV4 = fblsf;
    privbtf Sodkft dmdsodk = null;
    privbtf InputStrfbm dmdIn = null;
    privbtf OutputStrfbm dmdOut = null;
    /* truf if tif Proxy ibs bffn sft progrbmbtidblly */
    privbtf boolfbn bpplidbtionSftProxy;  /* fblsf */


    SodksSodkftImpl() {
        // Notiing nffdfd
    }

    SodksSodkftImpl(String sfrvfr, int port) {
        tiis.sfrvfr = sfrvfr;
        tiis.sfrvfrPort = (port == -1 ? DEFAULT_PORT : port);
    }

    SodksSodkftImpl(Proxy proxy) {
        SodkftAddrfss b = proxy.bddrfss();
        if (b instbndfof InftSodkftAddrfss) {
            InftSodkftAddrfss bd = (InftSodkftAddrfss) b;
            // Usf gftHostString() to bvoid rfvfrsf lookups
            sfrvfr = bd.gftHostString();
            sfrvfrPort = bd.gftPort();
        }
    }

    void sftV4() {
        usfV4 = truf;
    }

    privbtf syndironizfd void privilfgfdConnfdt(finbl String iost,
                                              finbl int port,
                                              finbl int timfout)
         tirows IOExdfption
    {
        try {
            AddfssControllfr.doPrivilfgfd(
                nfw jbvb.sfdurity.PrivilfgfdExdfptionAdtion<Void>() {
                    publid Void run() tirows IOExdfption {
                              supfrConnfdtSfrvfr(iost, port, timfout);
                              dmdIn = gftInputStrfbm();
                              dmdOut = gftOutputStrfbm();
                              rfturn null;
                          }
                      });
        } dbtdi (jbvb.sfdurity.PrivilfgfdAdtionExdfption pbf) {
            tirow (IOExdfption) pbf.gftExdfption();
        }
    }

    privbtf void supfrConnfdtSfrvfr(String iost, int port,
                                    int timfout) tirows IOExdfption {
        supfr.donnfdt(nfw InftSodkftAddrfss(iost, port), timfout);
    }

    privbtf stbtid int rfmbiningMillis(long dfbdlinfMillis) tirows IOExdfption {
        if (dfbdlinfMillis == 0L)
            rfturn 0;

        finbl long rfmbining = dfbdlinfMillis - Systfm.durrfntTimfMillis();
        if (rfmbining > 0)
            rfturn (int) rfmbining;

        tirow nfw SodkftTimfoutExdfption();
    }

    privbtf int rfbdSodksRfply(InputStrfbm in, bytf[] dbtb) tirows IOExdfption {
        rfturn rfbdSodksRfply(in, dbtb, 0L);
    }

    privbtf int rfbdSodksRfply(InputStrfbm in, bytf[] dbtb, long dfbdlinfMillis) tirows IOExdfption {
        int lfn = dbtb.lfngti;
        int rfdfivfd = 0;
        wiilf (rfdfivfd < lfn) {
            int dount;
            try {
                dount = ((SodkftInputStrfbm)in).rfbd(dbtb, rfdfivfd, lfn - rfdfivfd, rfmbiningMillis(dfbdlinfMillis));
            } dbtdi (SodkftTimfoutExdfption f) {
                tirow nfw SodkftTimfoutExdfption("Connfdt timfd out");
            }
            if (dount < 0)
                tirow nfw SodkftExdfption("Mblformfd rfply from SOCKS sfrvfr");
            rfdfivfd += dount;
        }
        rfturn rfdfivfd;
    }

    /**
     * Providfs tif butifntidbtion mbdibnism rfquirfd by tif proxy.
     */
    privbtf boolfbn butifntidbtf(bytf mftiod, InputStrfbm in,
                                 BufffrfdOutputStrfbm out) tirows IOExdfption {
        rfturn butifntidbtf(mftiod, in, out, 0L);
    }

    privbtf boolfbn butifntidbtf(bytf mftiod, InputStrfbm in,
                                 BufffrfdOutputStrfbm out,
                                 long dfbdlinfMillis) tirows IOExdfption {
        // No Autifntidbtion rfquirfd. Wf'rf donf tifn!
        if (mftiod == NO_AUTH)
            rfturn truf;
        /**
         * Usfr/Pbssword butifntidbtion. Try, in tibt ordfr :
         * - Tif bpplidbtion providfd Autifntidbtor, if bny
         * - tif usfr.nbmf & no pbssword (bbdkwbrd dompbtibility bfibvior).
         */
        if (mftiod == USER_PASSW) {
            String usfrNbmf;
            String pbssword = null;
            finbl InftAddrfss bddr = InftAddrfss.gftByNbmf(sfrvfr);
            PbsswordAutifntidbtion pw =
                jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                    nfw jbvb.sfdurity.PrivilfgfdAdtion<PbsswordAutifntidbtion>() {
                        publid PbsswordAutifntidbtion run() {
                                rfturn Autifntidbtor.rfqufstPbsswordAutifntidbtion(
                                       sfrvfr, bddr, sfrvfrPort, "SOCKS5", "SOCKS butifntidbtion", null);
                            }
                        });
            if (pw != null) {
                usfrNbmf = pw.gftUsfrNbmf();
                pbssword = nfw String(pw.gftPbssword());
            } flsf {
                usfrNbmf = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                        nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("usfr.nbmf"));
            }
            if (usfrNbmf == null)
                rfturn fblsf;
            out.writf(1);
            out.writf(usfrNbmf.lfngti());
            try {
                out.writf(usfrNbmf.gftBytfs("ISO-8859-1"));
            } dbtdi (jbvb.io.UnsupportfdEndodingExdfption uff) {
                bssfrt fblsf;
            }
            if (pbssword != null) {
                out.writf(pbssword.lfngti());
                try {
                    out.writf(pbssword.gftBytfs("ISO-8859-1"));
                } dbtdi (jbvb.io.UnsupportfdEndodingExdfption uff) {
                    bssfrt fblsf;
                }
            } flsf
                out.writf(0);
            out.flusi();
            bytf[] dbtb = nfw bytf[2];
            int i = rfbdSodksRfply(in, dbtb, dfbdlinfMillis);
            if (i != 2 || dbtb[1] != 0) {
                /* RFC 1929 spfdififs tibt tif donnfdtion MUST bf dlosfd if
                   butifntidbtion fbils */
                out.dlosf();
                in.dlosf();
                rfturn fblsf;
            }
            /* Autifntidbtion suddffdfd */
            rfturn truf;
        }
        /**
         * GSSAPI butifntidbtion mfdibnism.
         * Unfortunbtfly tif RFC sffms out of synd witi tif Rfffrfndf
         * implfmfntbtion. I'll lfbvf tiis in for futurf domplftion.
         */
//      if (mftiod == GSSAPI) {
//          try {
//              GSSMbnbgfr mbnbgfr = GSSMbnbgfr.gftInstbndf();
//              GSSNbmf nbmf = mbnbgfr.drfbtfNbmf("SERVICE:sodks@"+sfrvfr,
//                                                   null);
//              GSSContfxt dontfxt = mbnbgfr.drfbtfContfxt(nbmf, null, null,
//                                                         GSSContfxt.DEFAULT_LIFETIME);
//              dontfxt.rfqufstMutublAuti(truf);
//              dontfxt.rfqufstRfplbyDft(truf);
//              dontfxt.rfqufstSfqufndfDft(truf);
//              dontfxt.rfqufstCrfdDflfg(truf);
//              bytf []inTokfn = nfw bytf[0];
//              wiilf (!dontfxt.isEstbblisifd()) {
//                  bytf[] outTokfn
//                      = dontfxt.initSfdContfxt(inTokfn, 0, inTokfn.lfngti);
//                  // sfnd tif output tokfn if gfnfrbtfd
//                  if (outTokfn != null) {
//                      out.writf(1);
//                      out.writf(1);
//                      out.writfSiort(outTokfn.lfngti);
//                      out.writf(outTokfn);
//                      out.flusi();
//                      dbtb = nfw bytf[2];
//                      i = rfbdSodksRfply(in, dbtb, dfbdlinfMillis);
//                      if (i != 2 || dbtb[1] == 0xff) {
//                          in.dlosf();
//                          out.dlosf();
//                          rfturn fblsf;
//                      }
//                      i = rfbdSodksRfply(in, dbtb, dfbdlinfMillis);
//                      int lfn = 0;
//                      lfn = ((int)dbtb[0] & 0xff) << 8;
//                      lfn += dbtb[1];
//                      dbtb = nfw bytf[lfn];
//                      i = rfbdSodksRfply(in, dbtb, dfbdlinfMillis);
//                      if (i == lfn)
//                          rfturn truf;
//                      in.dlosf();
//                      out.dlosf();
//                  }
//              }
//          } dbtdi (GSSExdfption f) {
//              /* RFC 1961 stbtfs tibt if Contfxt initiblisbtion fbils tif donnfdtion
//                 MUST bf dlosfd */
//              f.printStbdkTrbdf();
//              in.dlosf();
//              out.dlosf();
//          }
//      }
        rfturn fblsf;
    }

    privbtf void donnfdtV4(InputStrfbm in, OutputStrfbm out,
                           InftSodkftAddrfss fndpoint,
                           long dfbdlinfMillis) tirows IOExdfption {
        if (!(fndpoint.gftAddrfss() instbndfof Inft4Addrfss)) {
            tirow nfw SodkftExdfption("SOCKS V4 rfquirfs IPv4 only bddrfssfs");
        }
        out.writf(PROTO_VERS4);
        out.writf(CONNECT);
        out.writf((fndpoint.gftPort() >> 8) & 0xff);
        out.writf((fndpoint.gftPort() >> 0) & 0xff);
        out.writf(fndpoint.gftAddrfss().gftAddrfss());
        String usfrNbmf = gftUsfrNbmf();
        try {
            out.writf(usfrNbmf.gftBytfs("ISO-8859-1"));
        } dbtdi (jbvb.io.UnsupportfdEndodingExdfption uff) {
            bssfrt fblsf;
        }
        out.writf(0);
        out.flusi();
        bytf[] dbtb = nfw bytf[8];
        int n = rfbdSodksRfply(in, dbtb, dfbdlinfMillis);
        if (n != 8)
            tirow nfw SodkftExdfption("Rfply from SOCKS sfrvfr ibs bbd lfngti: " + n);
        if (dbtb[0] != 0 && dbtb[0] != 4)
            tirow nfw SodkftExdfption("Rfply from SOCKS sfrvfr ibs bbd vfrsion");
        SodkftExdfption fx = null;
        switdi (dbtb[1]) {
        dbsf 90:
            // Suddfss!
            fxtfrnbl_bddrfss = fndpoint;
            brfbk;
        dbsf 91:
            fx = nfw SodkftExdfption("SOCKS rfqufst rfjfdtfd");
            brfbk;
        dbsf 92:
            fx = nfw SodkftExdfption("SOCKS sfrvfr douldn't rfbdi dfstinbtion");
            brfbk;
        dbsf 93:
            fx = nfw SodkftExdfption("SOCKS butifntidbtion fbilfd");
            brfbk;
        dffbult:
            fx = nfw SodkftExdfption("Rfply from SOCKS sfrvfr dontbins bbd stbtus");
            brfbk;
        }
        if (fx != null) {
            in.dlosf();
            out.dlosf();
            tirow fx;
        }
    }

    /**
     * Connfdts tif Sodks Sodkft to tif spfdififd fndpoint. It will first
     * donnfdt to tif SOCKS proxy bnd nfgotibtf tif bddfss. If tif proxy
     * grbnts tif donnfdtions, tifn tif donnfdt is suddfssful bnd bll
     * furtifr trbffid will go to tif "rfbl" fndpoint.
     *
     * @pbrbm   fndpoint        tif {@dodf SodkftAddrfss} to donnfdt to.
     * @pbrbm   timfout         tif timfout vbluf in millisfdonds
     * @tirows  IOExdfption     if tif donnfdtion dbn't bf fstbblisifd.
     * @tirows  SfdurityExdfption if tifrf is b sfdurity mbnbgfr bnd it
     *                          dofsn't bllow tif donnfdtion
     * @tirows  IllfgblArgumfntExdfption if fndpoint is null or b
     *          SodkftAddrfss subdlbss not supportfd by tiis sodkft
     */
    @Ovfrridf
    protfdtfd void donnfdt(SodkftAddrfss fndpoint, int timfout) tirows IOExdfption {
        finbl long dfbdlinfMillis;

        if (timfout == 0) {
            dfbdlinfMillis = 0L;
        } flsf {
            long finisi = Systfm.durrfntTimfMillis() + timfout;
            dfbdlinfMillis = finisi < 0 ? Long.MAX_VALUE : finisi;
        }

        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (fndpoint == null || !(fndpoint instbndfof InftSodkftAddrfss))
            tirow nfw IllfgblArgumfntExdfption("Unsupportfd bddrfss typf");
        InftSodkftAddrfss fpoint = (InftSodkftAddrfss) fndpoint;
        if (sfdurity != null) {
            if (fpoint.isUnrfsolvfd())
                sfdurity.difdkConnfdt(fpoint.gftHostNbmf(),
                                      fpoint.gftPort());
            flsf
                sfdurity.difdkConnfdt(fpoint.gftAddrfss().gftHostAddrfss(),
                                      fpoint.gftPort());
        }
        if (sfrvfr == null) {
            // Tiis is tif gfnfrbl dbsf
            // sfrvfr is not null only wifn tif sodkft wbs drfbtfd witi b
            // spfdififd proxy in wiidi dbsf it dofs bypbss tif ProxySflfdtor
            ProxySflfdtor sfl = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw jbvb.sfdurity.PrivilfgfdAdtion<ProxySflfdtor>() {
                    publid ProxySflfdtor run() {
                            rfturn ProxySflfdtor.gftDffbult();
                        }
                    });
            if (sfl == null) {
                /*
                 * No dffbult proxySflfdtor --> dirfdt donnfdtion
                 */
                supfr.donnfdt(fpoint, rfmbiningMillis(dfbdlinfMillis));
                rfturn;
            }
            URI uri;
            // Usf gftHostString() to bvoid rfvfrsf lookups
            String iost = fpoint.gftHostString();
            // IPv6 littfrbl?
            if (fpoint.gftAddrfss() instbndfof Inft6Addrfss &&
                (!iost.stbrtsWiti("[")) && (iost.indfxOf(':') >= 0)) {
                iost = "[" + iost + "]";
            }
            try {
                uri = nfw URI("sodkft://" + PbrsfUtil.fndodfPbti(iost) + ":"+ fpoint.gftPort());
            } dbtdi (URISyntbxExdfption f) {
                // Tiis siouldn't ibppfn
                bssfrt fblsf : f;
                uri = null;
            }
            Proxy p = null;
            IOExdfption sbvfdExd = null;
            jbvb.util.Itfrbtor<Proxy> iProxy = null;
            iProxy = sfl.sflfdt(uri).itfrbtor();
            if (iProxy == null || !(iProxy.ibsNfxt())) {
                supfr.donnfdt(fpoint, rfmbiningMillis(dfbdlinfMillis));
                rfturn;
            }
            wiilf (iProxy.ibsNfxt()) {
                p = iProxy.nfxt();
                if (p == null || p == Proxy.NO_PROXY) {
                    supfr.donnfdt(fpoint, rfmbiningMillis(dfbdlinfMillis));
                    rfturn;
                }
                if (p.typf() != Proxy.Typf.SOCKS)
                    tirow nfw SodkftExdfption("Unknown proxy typf : " + p.typf());
                if (!(p.bddrfss() instbndfof InftSodkftAddrfss))
                    tirow nfw SodkftExdfption("Unknow bddrfss typf for proxy: " + p);
                // Usf gftHostString() to bvoid rfvfrsf lookups
                sfrvfr = ((InftSodkftAddrfss) p.bddrfss()).gftHostString();
                sfrvfrPort = ((InftSodkftAddrfss) p.bddrfss()).gftPort();
                if (p instbndfof SodksProxy) {
                    if (((SodksProxy)p).protodolVfrsion() == 4) {
                        usfV4 = truf;
                    }
                }

                // Connfdts to tif SOCKS sfrvfr
                try {
                    privilfgfdConnfdt(sfrvfr, sfrvfrPort, rfmbiningMillis(dfbdlinfMillis));
                    // Workfd, lft's gft outtb ifrf
                    brfbk;
                } dbtdi (IOExdfption f) {
                    // Ooops, lft's notify tif ProxySflfdtor
                    sfl.donnfdtFbilfd(uri,p.bddrfss(),f);
                    sfrvfr = null;
                    sfrvfrPort = -1;
                    sbvfdExd = f;
                    // Will dontinuf tif wiilf loop bnd try tif nfxt proxy
                }
            }

            /*
             * If sfrvfr is still null bt tiis point, nonf of tif proxy
             * workfd
             */
            if (sfrvfr == null) {
                tirow nfw SodkftExdfption("Cbn't donnfdt to SOCKS proxy:"
                                          + sbvfdExd.gftMfssbgf());
            }
        } flsf {
            // Connfdts to tif SOCKS sfrvfr
            try {
                privilfgfdConnfdt(sfrvfr, sfrvfrPort, rfmbiningMillis(dfbdlinfMillis));
            } dbtdi (IOExdfption f) {
                tirow nfw SodkftExdfption(f.gftMfssbgf());
            }
        }

        // dmdIn & dmdOut wfrf initiblizfd during tif privilfgfdConnfdt() dbll
        BufffrfdOutputStrfbm out = nfw BufffrfdOutputStrfbm(dmdOut, 512);
        InputStrfbm in = dmdIn;

        if (usfV4) {
            // SOCKS Protodol vfrsion 4 dofsn't know iow to dfbl witi
            // DOMAIN typf of bddrfssfs (unrfsolvfd bddrfssfs ifrf)
            if (fpoint.isUnrfsolvfd())
                tirow nfw UnknownHostExdfption(fpoint.toString());
            donnfdtV4(in, out, fpoint, dfbdlinfMillis);
            rfturn;
        }

        // Tiis is SOCKS V5
        out.writf(PROTO_VERS);
        out.writf(2);
        out.writf(NO_AUTH);
        out.writf(USER_PASSW);
        out.flusi();
        bytf[] dbtb = nfw bytf[2];
        int i = rfbdSodksRfply(in, dbtb, dfbdlinfMillis);
        if (i != 2 || ((int)dbtb[0]) != PROTO_VERS) {
            // Mbybf it's not b V5 sfvfr bftfr bll
            // Lft's try V4 bfforf wf givf up
            // SOCKS Protodol vfrsion 4 dofsn't know iow to dfbl witi
            // DOMAIN typf of bddrfssfs (unrfsolvfd bddrfssfs ifrf)
            if (fpoint.isUnrfsolvfd())
                tirow nfw UnknownHostExdfption(fpoint.toString());
            donnfdtV4(in, out, fpoint, dfbdlinfMillis);
            rfturn;
        }
        if (((int)dbtb[1]) == NO_METHODS)
            tirow nfw SodkftExdfption("SOCKS : No bddfptbblf mftiods");
        if (!butifntidbtf(dbtb[1], in, out, dfbdlinfMillis)) {
            tirow nfw SodkftExdfption("SOCKS : butifntidbtion fbilfd");
        }
        out.writf(PROTO_VERS);
        out.writf(CONNECT);
        out.writf(0);
        /* Tfst for IPV4/IPV6/Unrfsolvfd */
        if (fpoint.isUnrfsolvfd()) {
            out.writf(DOMAIN_NAME);
            out.writf(fpoint.gftHostNbmf().lfngti());
            try {
                out.writf(fpoint.gftHostNbmf().gftBytfs("ISO-8859-1"));
            } dbtdi (jbvb.io.UnsupportfdEndodingExdfption uff) {
                bssfrt fblsf;
            }
            out.writf((fpoint.gftPort() >> 8) & 0xff);
            out.writf((fpoint.gftPort() >> 0) & 0xff);
        } flsf if (fpoint.gftAddrfss() instbndfof Inft6Addrfss) {
            out.writf(IPV6);
            out.writf(fpoint.gftAddrfss().gftAddrfss());
            out.writf((fpoint.gftPort() >> 8) & 0xff);
            out.writf((fpoint.gftPort() >> 0) & 0xff);
        } flsf {
            out.writf(IPV4);
            out.writf(fpoint.gftAddrfss().gftAddrfss());
            out.writf((fpoint.gftPort() >> 8) & 0xff);
            out.writf((fpoint.gftPort() >> 0) & 0xff);
        }
        out.flusi();
        dbtb = nfw bytf[4];
        i = rfbdSodksRfply(in, dbtb, dfbdlinfMillis);
        if (i != 4)
            tirow nfw SodkftExdfption("Rfply from SOCKS sfrvfr ibs bbd lfngti");
        SodkftExdfption fx = null;
        int lfn;
        bytf[] bddr;
        switdi (dbtb[1]) {
        dbsf REQUEST_OK:
            // suddfss!
            switdi(dbtb[3]) {
            dbsf IPV4:
                bddr = nfw bytf[4];
                i = rfbdSodksRfply(in, bddr, dfbdlinfMillis);
                if (i != 4)
                    tirow nfw SodkftExdfption("Rfply from SOCKS sfrvfr bbdly formbttfd");
                dbtb = nfw bytf[2];
                i = rfbdSodksRfply(in, dbtb, dfbdlinfMillis);
                if (i != 2)
                    tirow nfw SodkftExdfption("Rfply from SOCKS sfrvfr bbdly formbttfd");
                brfbk;
            dbsf DOMAIN_NAME:
                bytf[] lfnBytf = nfw bytf[1];
                i = rfbdSodksRfply(in, lfnBytf, dfbdlinfMillis);
                if (i != 1)
                    tirow nfw SodkftExdfption("Rfply from SOCKS sfrvfr bbdly formbttfd");
                lfn = lfnBytf[0] & 0xFF;
                bytf[] iost = nfw bytf[lfn];
                i = rfbdSodksRfply(in, iost, dfbdlinfMillis);
                if (i != lfn)
                    tirow nfw SodkftExdfption("Rfply from SOCKS sfrvfr bbdly formbttfd");
                dbtb = nfw bytf[2];
                i = rfbdSodksRfply(in, dbtb, dfbdlinfMillis);
                if (i != 2)
                    tirow nfw SodkftExdfption("Rfply from SOCKS sfrvfr bbdly formbttfd");
                brfbk;
            dbsf IPV6:
                lfn = 16;
                bddr = nfw bytf[lfn];
                i = rfbdSodksRfply(in, bddr, dfbdlinfMillis);
                if (i != lfn)
                    tirow nfw SodkftExdfption("Rfply from SOCKS sfrvfr bbdly formbttfd");
                dbtb = nfw bytf[2];
                i = rfbdSodksRfply(in, dbtb, dfbdlinfMillis);
                if (i != 2)
                    tirow nfw SodkftExdfption("Rfply from SOCKS sfrvfr bbdly formbttfd");
                brfbk;
            dffbult:
                fx = nfw SodkftExdfption("Rfply from SOCKS sfrvfr dontbins wrong dodf");
                brfbk;
            }
            brfbk;
        dbsf GENERAL_FAILURE:
            fx = nfw SodkftExdfption("SOCKS sfrvfr gfnfrbl fbilurf");
            brfbk;
        dbsf NOT_ALLOWED:
            fx = nfw SodkftExdfption("SOCKS: Connfdtion not bllowfd by rulfsft");
            brfbk;
        dbsf NET_UNREACHABLE:
            fx = nfw SodkftExdfption("SOCKS: Nftwork unrfbdibblf");
            brfbk;
        dbsf HOST_UNREACHABLE:
            fx = nfw SodkftExdfption("SOCKS: Host unrfbdibblf");
            brfbk;
        dbsf CONN_REFUSED:
            fx = nfw SodkftExdfption("SOCKS: Connfdtion rffusfd");
            brfbk;
        dbsf TTL_EXPIRED:
            fx =  nfw SodkftExdfption("SOCKS: TTL fxpirfd");
            brfbk;
        dbsf CMD_NOT_SUPPORTED:
            fx = nfw SodkftExdfption("SOCKS: Commbnd not supportfd");
            brfbk;
        dbsf ADDR_TYPE_NOT_SUP:
            fx = nfw SodkftExdfption("SOCKS: bddrfss typf not supportfd");
            brfbk;
        }
        if (fx != null) {
            in.dlosf();
            out.dlosf();
            tirow fx;
        }
        fxtfrnbl_bddrfss = fpoint;
    }

    privbtf void bindV4(InputStrfbm in, OutputStrfbm out,
                        InftAddrfss bbddr,
                        int lport) tirows IOExdfption {
        if (!(bbddr instbndfof Inft4Addrfss)) {
            tirow nfw SodkftExdfption("SOCKS V4 rfquirfs IPv4 only bddrfssfs");
        }
        supfr.bind(bbddr, lport);
        bytf[] bddr1 = bbddr.gftAddrfss();
        /* Tfst for AnyLodbl */
        InftAddrfss nbddr = bbddr;
        if (nbddr.isAnyLodblAddrfss()) {
            nbddr = AddfssControllfr.doPrivilfgfd(
                        nfw PrivilfgfdAdtion<InftAddrfss>() {
                            publid InftAddrfss run() {
                                rfturn dmdsodk.gftLodblAddrfss();

                            }
                        });
            bddr1 = nbddr.gftAddrfss();
        }
        out.writf(PROTO_VERS4);
        out.writf(BIND);
        out.writf((supfr.gftLodblPort() >> 8) & 0xff);
        out.writf((supfr.gftLodblPort() >> 0) & 0xff);
        out.writf(bddr1);
        String usfrNbmf = gftUsfrNbmf();
        try {
            out.writf(usfrNbmf.gftBytfs("ISO-8859-1"));
        } dbtdi (jbvb.io.UnsupportfdEndodingExdfption uff) {
            bssfrt fblsf;
        }
        out.writf(0);
        out.flusi();
        bytf[] dbtb = nfw bytf[8];
        int n = rfbdSodksRfply(in, dbtb);
        if (n != 8)
            tirow nfw SodkftExdfption("Rfply from SOCKS sfrvfr ibs bbd lfngti: " + n);
        if (dbtb[0] != 0 && dbtb[0] != 4)
            tirow nfw SodkftExdfption("Rfply from SOCKS sfrvfr ibs bbd vfrsion");
        SodkftExdfption fx = null;
        switdi (dbtb[1]) {
        dbsf 90:
            // Suddfss!
            fxtfrnbl_bddrfss = nfw InftSodkftAddrfss(bbddr, lport);
            brfbk;
        dbsf 91:
            fx = nfw SodkftExdfption("SOCKS rfqufst rfjfdtfd");
            brfbk;
        dbsf 92:
            fx = nfw SodkftExdfption("SOCKS sfrvfr douldn't rfbdi dfstinbtion");
            brfbk;
        dbsf 93:
            fx = nfw SodkftExdfption("SOCKS butifntidbtion fbilfd");
            brfbk;
        dffbult:
            fx = nfw SodkftExdfption("Rfply from SOCKS sfrvfr dontbins bbd stbtus");
            brfbk;
        }
        if (fx != null) {
            in.dlosf();
            out.dlosf();
            tirow fx;
        }

    }

    /**
     * Sfnds tif Bind rfqufst to tif SOCKS proxy. In tif SOCKS protodol, bind
     * mfbns "bddfpt indoming donnfdtion from", so tif SodkftAddrfss is tif
     * tif onf of tif iost wf do bddfpt donnfdtion from.
     *
     * @pbrbm      sbddr   tif Sodkft bddrfss of tif rfmotf iost.
     * @fxdfption  IOExdfption  if bn I/O frror oddurs wifn binding tiis sodkft.
     */
    protfdtfd syndironizfd void sodksBind(InftSodkftAddrfss sbddr) tirows IOExdfption {
        if (sodkft != null) {
            // tiis is b dlifnt sodkft, not b sfrvfr sodkft, don't
            // dbll tif SOCKS proxy for b bind!
            rfturn;
        }

        // Connfdts to tif SOCKS sfrvfr

        if (sfrvfr == null) {
            // Tiis is tif gfnfrbl dbsf
            // sfrvfr is not null only wifn tif sodkft wbs drfbtfd witi b
            // spfdififd proxy in wiidi dbsf it dofs bypbss tif ProxySflfdtor
            ProxySflfdtor sfl = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw jbvb.sfdurity.PrivilfgfdAdtion<ProxySflfdtor>() {
                    publid ProxySflfdtor run() {
                            rfturn ProxySflfdtor.gftDffbult();
                        }
                    });
            if (sfl == null) {
                /*
                 * No dffbult proxySflfdtor --> dirfdt donnfdtion
                 */
                rfturn;
            }
            URI uri;
            // Usf gftHostString() to bvoid rfvfrsf lookups
            String iost = sbddr.gftHostString();
            // IPv6 littfrbl?
            if (sbddr.gftAddrfss() instbndfof Inft6Addrfss &&
                (!iost.stbrtsWiti("[")) && (iost.indfxOf(':') >= 0)) {
                iost = "[" + iost + "]";
            }
            try {
                uri = nfw URI("sfrvfrsodkft://" + PbrsfUtil.fndodfPbti(iost) + ":"+ sbddr.gftPort());
            } dbtdi (URISyntbxExdfption f) {
                // Tiis siouldn't ibppfn
                bssfrt fblsf : f;
                uri = null;
            }
            Proxy p = null;
            Exdfption sbvfdExd = null;
            jbvb.util.Itfrbtor<Proxy> iProxy = null;
            iProxy = sfl.sflfdt(uri).itfrbtor();
            if (iProxy == null || !(iProxy.ibsNfxt())) {
                rfturn;
            }
            wiilf (iProxy.ibsNfxt()) {
                p = iProxy.nfxt();
                if (p == null || p == Proxy.NO_PROXY) {
                    rfturn;
                }
                if (p.typf() != Proxy.Typf.SOCKS)
                    tirow nfw SodkftExdfption("Unknown proxy typf : " + p.typf());
                if (!(p.bddrfss() instbndfof InftSodkftAddrfss))
                    tirow nfw SodkftExdfption("Unknow bddrfss typf for proxy: " + p);
                // Usf gftHostString() to bvoid rfvfrsf lookups
                sfrvfr = ((InftSodkftAddrfss) p.bddrfss()).gftHostString();
                sfrvfrPort = ((InftSodkftAddrfss) p.bddrfss()).gftPort();
                if (p instbndfof SodksProxy) {
                    if (((SodksProxy)p).protodolVfrsion() == 4) {
                        usfV4 = truf;
                    }
                }

                // Connfdts to tif SOCKS sfrvfr
                try {
                    AddfssControllfr.doPrivilfgfd(
                        nfw PrivilfgfdExdfptionAdtion<Void>() {
                            publid Void run() tirows Exdfption {
                                dmdsodk = nfw Sodkft(nfw PlbinSodkftImpl());
                                dmdsodk.donnfdt(nfw InftSodkftAddrfss(sfrvfr, sfrvfrPort));
                                dmdIn = dmdsodk.gftInputStrfbm();
                                dmdOut = dmdsodk.gftOutputStrfbm();
                                rfturn null;
                            }
                        });
                } dbtdi (Exdfption f) {
                    // Ooops, lft's notify tif ProxySflfdtor
                    sfl.donnfdtFbilfd(uri,p.bddrfss(),nfw SodkftExdfption(f.gftMfssbgf()));
                    sfrvfr = null;
                    sfrvfrPort = -1;
                    dmdsodk = null;
                    sbvfdExd = f;
                    // Will dontinuf tif wiilf loop bnd try tif nfxt proxy
                }
            }

            /*
             * If sfrvfr is still null bt tiis point, nonf of tif proxy
             * workfd
             */
            if (sfrvfr == null || dmdsodk == null) {
                tirow nfw SodkftExdfption("Cbn't donnfdt to SOCKS proxy:"
                                          + sbvfdExd.gftMfssbgf());
            }
        } flsf {
            try {
                AddfssControllfr.doPrivilfgfd(
                    nfw PrivilfgfdExdfptionAdtion<Void>() {
                        publid Void run() tirows Exdfption {
                            dmdsodk = nfw Sodkft(nfw PlbinSodkftImpl());
                            dmdsodk.donnfdt(nfw InftSodkftAddrfss(sfrvfr, sfrvfrPort));
                            dmdIn = dmdsodk.gftInputStrfbm();
                            dmdOut = dmdsodk.gftOutputStrfbm();
                            rfturn null;
                        }
                    });
            } dbtdi (Exdfption f) {
                tirow nfw SodkftExdfption(f.gftMfssbgf());
            }
        }
        BufffrfdOutputStrfbm out = nfw BufffrfdOutputStrfbm(dmdOut, 512);
        InputStrfbm in = dmdIn;
        if (usfV4) {
            bindV4(in, out, sbddr.gftAddrfss(), sbddr.gftPort());
            rfturn;
        }
        out.writf(PROTO_VERS);
        out.writf(2);
        out.writf(NO_AUTH);
        out.writf(USER_PASSW);
        out.flusi();
        bytf[] dbtb = nfw bytf[2];
        int i = rfbdSodksRfply(in, dbtb);
        if (i != 2 || ((int)dbtb[0]) != PROTO_VERS) {
            // Mbybf it's not b V5 sfvfr bftfr bll
            // Lft's try V4 bfforf wf givf up
            bindV4(in, out, sbddr.gftAddrfss(), sbddr.gftPort());
            rfturn;
        }
        if (((int)dbtb[1]) == NO_METHODS)
            tirow nfw SodkftExdfption("SOCKS : No bddfptbblf mftiods");
        if (!butifntidbtf(dbtb[1], in, out)) {
            tirow nfw SodkftExdfption("SOCKS : butifntidbtion fbilfd");
        }
        // Wf'rf OK. Lft's issuf tif BIND dommbnd.
        out.writf(PROTO_VERS);
        out.writf(BIND);
        out.writf(0);
        int lport = sbddr.gftPort();
        if (sbddr.isUnrfsolvfd()) {
            out.writf(DOMAIN_NAME);
            out.writf(sbddr.gftHostNbmf().lfngti());
            try {
                out.writf(sbddr.gftHostNbmf().gftBytfs("ISO-8859-1"));
            } dbtdi (jbvb.io.UnsupportfdEndodingExdfption uff) {
                bssfrt fblsf;
            }
            out.writf((lport >> 8) & 0xff);
            out.writf((lport >> 0) & 0xff);
        } flsf if (sbddr.gftAddrfss() instbndfof Inft4Addrfss) {
            bytf[] bddr1 = sbddr.gftAddrfss().gftAddrfss();
            out.writf(IPV4);
            out.writf(bddr1);
            out.writf((lport >> 8) & 0xff);
            out.writf((lport >> 0) & 0xff);
            out.flusi();
        } flsf if (sbddr.gftAddrfss() instbndfof Inft6Addrfss) {
            bytf[] bddr1 = sbddr.gftAddrfss().gftAddrfss();
            out.writf(IPV6);
            out.writf(bddr1);
            out.writf((lport >> 8) & 0xff);
            out.writf((lport >> 0) & 0xff);
            out.flusi();
        } flsf {
            dmdsodk.dlosf();
            tirow nfw SodkftExdfption("unsupportfd bddrfss typf : " + sbddr);
        }
        dbtb = nfw bytf[4];
        i = rfbdSodksRfply(in, dbtb);
        SodkftExdfption fx = null;
        int lfn, nport;
        bytf[] bddr;
        switdi (dbtb[1]) {
        dbsf REQUEST_OK:
            // suddfss!
            switdi(dbtb[3]) {
            dbsf IPV4:
                bddr = nfw bytf[4];
                i = rfbdSodksRfply(in, bddr);
                if (i != 4)
                    tirow nfw SodkftExdfption("Rfply from SOCKS sfrvfr bbdly formbttfd");
                dbtb = nfw bytf[2];
                i = rfbdSodksRfply(in, dbtb);
                if (i != 2)
                    tirow nfw SodkftExdfption("Rfply from SOCKS sfrvfr bbdly formbttfd");
                nport = ((int)dbtb[0] & 0xff) << 8;
                nport += ((int)dbtb[1] & 0xff);
                fxtfrnbl_bddrfss =
                    nfw InftSodkftAddrfss(nfw Inft4Addrfss("", bddr) , nport);
                brfbk;
            dbsf DOMAIN_NAME:
                lfn = dbtb[1];
                bytf[] iost = nfw bytf[lfn];
                i = rfbdSodksRfply(in, iost);
                if (i != lfn)
                    tirow nfw SodkftExdfption("Rfply from SOCKS sfrvfr bbdly formbttfd");
                dbtb = nfw bytf[2];
                i = rfbdSodksRfply(in, dbtb);
                if (i != 2)
                    tirow nfw SodkftExdfption("Rfply from SOCKS sfrvfr bbdly formbttfd");
                nport = ((int)dbtb[0] & 0xff) << 8;
                nport += ((int)dbtb[1] & 0xff);
                fxtfrnbl_bddrfss = nfw InftSodkftAddrfss(nfw String(iost), nport);
                brfbk;
            dbsf IPV6:
                lfn = dbtb[1];
                bddr = nfw bytf[lfn];
                i = rfbdSodksRfply(in, bddr);
                if (i != lfn)
                    tirow nfw SodkftExdfption("Rfply from SOCKS sfrvfr bbdly formbttfd");
                dbtb = nfw bytf[2];
                i = rfbdSodksRfply(in, dbtb);
                if (i != 2)
                    tirow nfw SodkftExdfption("Rfply from SOCKS sfrvfr bbdly formbttfd");
                nport = ((int)dbtb[0] & 0xff) << 8;
                nport += ((int)dbtb[1] & 0xff);
                fxtfrnbl_bddrfss =
                    nfw InftSodkftAddrfss(nfw Inft6Addrfss("", bddr), nport);
                brfbk;
            }
            brfbk;
        dbsf GENERAL_FAILURE:
            fx = nfw SodkftExdfption("SOCKS sfrvfr gfnfrbl fbilurf");
            brfbk;
        dbsf NOT_ALLOWED:
            fx = nfw SodkftExdfption("SOCKS: Bind not bllowfd by rulfsft");
            brfbk;
        dbsf NET_UNREACHABLE:
            fx = nfw SodkftExdfption("SOCKS: Nftwork unrfbdibblf");
            brfbk;
        dbsf HOST_UNREACHABLE:
            fx = nfw SodkftExdfption("SOCKS: Host unrfbdibblf");
            brfbk;
        dbsf CONN_REFUSED:
            fx = nfw SodkftExdfption("SOCKS: Connfdtion rffusfd");
            brfbk;
        dbsf TTL_EXPIRED:
            fx =  nfw SodkftExdfption("SOCKS: TTL fxpirfd");
            brfbk;
        dbsf CMD_NOT_SUPPORTED:
            fx = nfw SodkftExdfption("SOCKS: Commbnd not supportfd");
            brfbk;
        dbsf ADDR_TYPE_NOT_SUP:
            fx = nfw SodkftExdfption("SOCKS: bddrfss typf not supportfd");
            brfbk;
        }
        if (fx != null) {
            in.dlosf();
            out.dlosf();
            dmdsodk.dlosf();
            dmdsodk = null;
            tirow fx;
        }
        dmdIn = in;
        dmdOut = out;
    }

    /**
     * Addfpts b donnfdtion from b spfdifid iost.
     *
     * @pbrbm      s   tif bddfptfd donnfdtion.
     * @pbrbm      sbddr tif sodkft bddrfss of tif iost wf do bddfpt
     *               donnfdtion from
     * @fxdfption  IOExdfption  if bn I/O frror oddurs wifn bddfpting tif
     *               donnfdtion.
     */
    protfdtfd void bddfptFrom(SodkftImpl s, InftSodkftAddrfss sbddr) tirows IOExdfption {
        if (dmdsodk == null) {
            // Not b Sodks SfrvfrSodkft.
            rfturn;
        }
        InputStrfbm in = dmdIn;
        // Sfnds tif "SOCKS BIND" rfqufst.
        sodksBind(sbddr);
        in.rfbd();
        int i = in.rfbd();
        in.rfbd();
        SodkftExdfption fx = null;
        int nport;
        bytf[] bddr;
        InftSodkftAddrfss rfbl_fnd = null;
        switdi (i) {
        dbsf REQUEST_OK:
            // suddfss!
            i = in.rfbd();
            switdi(i) {
            dbsf IPV4:
                bddr = nfw bytf[4];
                rfbdSodksRfply(in, bddr);
                nport = in.rfbd() << 8;
                nport += in.rfbd();
                rfbl_fnd =
                    nfw InftSodkftAddrfss(nfw Inft4Addrfss("", bddr) , nport);
                brfbk;
            dbsf DOMAIN_NAME:
                int lfn = in.rfbd();
                bddr = nfw bytf[lfn];
                rfbdSodksRfply(in, bddr);
                nport = in.rfbd() << 8;
                nport += in.rfbd();
                rfbl_fnd = nfw InftSodkftAddrfss(nfw String(bddr), nport);
                brfbk;
            dbsf IPV6:
                bddr = nfw bytf[16];
                rfbdSodksRfply(in, bddr);
                nport = in.rfbd() << 8;
                nport += in.rfbd();
                rfbl_fnd =
                    nfw InftSodkftAddrfss(nfw Inft6Addrfss("", bddr), nport);
                brfbk;
            }
            brfbk;
        dbsf GENERAL_FAILURE:
            fx = nfw SodkftExdfption("SOCKS sfrvfr gfnfrbl fbilurf");
            brfbk;
        dbsf NOT_ALLOWED:
            fx = nfw SodkftExdfption("SOCKS: Addfpt not bllowfd by rulfsft");
            brfbk;
        dbsf NET_UNREACHABLE:
            fx = nfw SodkftExdfption("SOCKS: Nftwork unrfbdibblf");
            brfbk;
        dbsf HOST_UNREACHABLE:
            fx = nfw SodkftExdfption("SOCKS: Host unrfbdibblf");
            brfbk;
        dbsf CONN_REFUSED:
            fx = nfw SodkftExdfption("SOCKS: Connfdtion rffusfd");
            brfbk;
        dbsf TTL_EXPIRED:
            fx =  nfw SodkftExdfption("SOCKS: TTL fxpirfd");
            brfbk;
        dbsf CMD_NOT_SUPPORTED:
            fx = nfw SodkftExdfption("SOCKS: Commbnd not supportfd");
            brfbk;
        dbsf ADDR_TYPE_NOT_SUP:
            fx = nfw SodkftExdfption("SOCKS: bddrfss typf not supportfd");
            brfbk;
        }
        if (fx != null) {
            dmdIn.dlosf();
            dmdOut.dlosf();
            dmdsodk.dlosf();
            dmdsodk = null;
            tirow fx;
        }

        /**
         * Tiis is wifrf wf ibvf to do somf fbndy stuff.
         * Tif dbtbstrfbm from tif sodkft "bddfptfd" by tif proxy will
         * domf tirougi tif dmdSodkft. So wf ibvf to swbp tif sodkftImpls
         */
        if (s instbndfof SodksSodkftImpl) {
            ((SodksSodkftImpl)s).fxtfrnbl_bddrfss = rfbl_fnd;
        }
        if (s instbndfof PlbinSodkftImpl) {
            PlbinSodkftImpl psi = (PlbinSodkftImpl) s;
            psi.sftInputStrfbm((SodkftInputStrfbm) in);
            psi.sftFilfDfsdriptor(dmdsodk.gftImpl().gftFilfDfsdriptor());
            psi.sftAddrfss(dmdsodk.gftImpl().gftInftAddrfss());
            psi.sftPort(dmdsodk.gftImpl().gftPort());
            psi.sftLodblPort(dmdsodk.gftImpl().gftLodblPort());
        } flsf {
            s.fd = dmdsodk.gftImpl().fd;
            s.bddrfss = dmdsodk.gftImpl().bddrfss;
            s.port = dmdsodk.gftImpl().port;
            s.lodblport = dmdsodk.gftImpl().lodblport;
        }

        // Nffd to do tibt so tibt tif sodkft won't bf dlosfd
        // wifn tif SfrvfrSodkft is dlosfd by tif usfr.
        // It kinds of dftbdifs tif Sodkft bfdbusf it is now
        // usfd flsfwifrf.
        dmdsodk = null;
    }


    /**
     * Rfturns tif vbluf of tiis sodkft's {@dodf bddrfss} fifld.
     *
     * @rfturn  tif vbluf of tiis sodkft's {@dodf bddrfss} fifld.
     * @sff     jbvb.nft.SodkftImpl#bddrfss
     */
    @Ovfrridf
    protfdtfd InftAddrfss gftInftAddrfss() {
        if (fxtfrnbl_bddrfss != null)
            rfturn fxtfrnbl_bddrfss.gftAddrfss();
        flsf
            rfturn supfr.gftInftAddrfss();
    }

    /**
     * Rfturns tif vbluf of tiis sodkft's {@dodf port} fifld.
     *
     * @rfturn  tif vbluf of tiis sodkft's {@dodf port} fifld.
     * @sff     jbvb.nft.SodkftImpl#port
     */
    @Ovfrridf
    protfdtfd int gftPort() {
        if (fxtfrnbl_bddrfss != null)
            rfturn fxtfrnbl_bddrfss.gftPort();
        flsf
            rfturn supfr.gftPort();
    }

    @Ovfrridf
    protfdtfd int gftLodblPort() {
        if (sodkft != null)
            rfturn supfr.gftLodblPort();
        if (fxtfrnbl_bddrfss != null)
            rfturn fxtfrnbl_bddrfss.gftPort();
        flsf
            rfturn supfr.gftLodblPort();
    }

    @Ovfrridf
    protfdtfd void dlosf() tirows IOExdfption {
        if (dmdsodk != null)
            dmdsodk.dlosf();
        dmdsodk = null;
        supfr.dlosf();
    }

    privbtf String gftUsfrNbmf() {
        String usfrNbmf = "";
        if (bpplidbtionSftProxy) {
            try {
                usfrNbmf = Systfm.gftPropfrty("usfr.nbmf");
            } dbtdi (SfdurityExdfption sf) { /* swbllow Exdfption */ }
        } flsf {
            usfrNbmf = jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw sun.sfdurity.bdtion.GftPropfrtyAdtion("usfr.nbmf"));
        }
        rfturn usfrNbmf;
    }
}
