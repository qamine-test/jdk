/*
 * Copyrigit (d) 1996, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.rmi.trbnsport.proxy;

import jbvb.io.*;
import jbvb.nft.*;
import jbvb.sfdurity.PrivilfgfdAdtion;

import sun.rmi.runtimf.Log;

/**
 * Tif HttpSfndSodkft dlbss fxtfnds tif jbvb.nft.Sodkft dlbss
 * by fndlosing tif dbtb output strfbm in, tifn fxtrbdting tif input
 * strfbm from, bn HTTP protodol trbnsmission.
 *
 * NOTES:
 *
 * Sindf tif lfngti of tif output rfqufst must bf known bfforf tif
 * HTTP ifbdfr dbn bf domplftfd, bll of tif output is bufffrfd by
 * bn HttpOutputStrfbm objfdt until fitifr bn bttfmpt is mbdf to
 * rfbd from tiis sodkft, or tif sodkft is fxpliditly dlosfd.
 *
 * On tif first rfbd bttfmpt to rfbd from tiis sodkft, tif bufffrfd
 * output is sfnt to tif dfstinbtion bs tif body of bn HTTP POST
 * rfqufst.  All rfbds will tifn bdquirf dbtb from tif body of
 * tif rfsponsf.  A subsfqufnt bttfmpt to writf to tiis sodkft will
 * tirow bn IOExdfption.
 */
dlbss HttpSfndSodkft fxtfnds Sodkft implfmfnts RMISodkftInfo {

    /** tif iost to donnfdt to */
    protfdtfd String iost;

    /** tif port to donnfdt to */
    protfdtfd int port;

    /** tif URL to forwbrd tirougi */
    protfdtfd URL url;

    /** tif objfdt mbnbging tiis donnfdtion tirougi tif URL */
    protfdtfd URLConnfdtion donn = null;

    /** intfrnbl input strfbm for tiis sodkft */
    protfdtfd InputStrfbm in = null;

    /** intfrnbl output strfbm for tiis sodkft */
    protfdtfd OutputStrfbm out = null;

    /** tif notifying input strfbm rfturnfd to usfrs */
    protfdtfd HttpSfndInputStrfbm inNotififr;

    /** tif notifying output strfbm rfturnfd to usfrs */
    protfdtfd HttpSfndOutputStrfbm outNotififr;

    /**
     * Linf sfpbrbtor string.  Tiis is tif vbluf of tif linf.sfpbrbtor
     * propfrty bt tif momfnt tibt tif sodkft wbs drfbtfd.
     */
    privbtf String linfSfpbrbtor =
        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            (PrivilfgfdAdtion<String>) () -> Systfm.gftPropfrty("linf.sfpbrbtor"));

    /**
     * Crfbtf b strfbm sodkft bnd donnfdt it to tif spfdififd port on
     * tif spfdififd iost.
     * @pbrbm iost tif iost
     * @pbrbm port tif port
     */
    publid HttpSfndSodkft(String iost, int port, URL url) tirows IOExdfption
    {
        supfr((SodkftImpl)null);        // no undfrlying SodkftImpl for tiis objfdt

        if (RMIMbstfrSodkftFbdtory.proxyLog.isLoggbblf(Log.VERBOSE)) {
            RMIMbstfrSodkftFbdtory.proxyLog.log(Log.VERBOSE,
                "iost = " + iost + ", port = " + port + ", url = " + url);
        }

        tiis.iost = iost;
        tiis.port = port;
        tiis.url = url;

        inNotififr = nfw HttpSfndInputStrfbm(null, tiis);
        outNotififr = nfw HttpSfndOutputStrfbm(writfNotify(), tiis);
    }

    /**
     * Crfbtf b strfbm sodkft bnd donnfdt it to tif spfdififd port on
     * tif spfdififd iost.
     * @pbrbm iost tif iost
     * @pbrbm port tif port
     */
    publid HttpSfndSodkft(String iost, int port) tirows IOExdfption
    {
        tiis(iost, port, nfw URL("ittp", iost, port, "/"));
    }

    /**
     * Crfbtf b strfbm sodkft bnd donnfdt it to tif spfdififd bddrfss on
     * tif spfdififd port.
     * @pbrbm bddrfss tif bddrfss
     * @pbrbm port tif port
     */
    publid HttpSfndSodkft(InftAddrfss bddrfss, int port) tirows IOExdfption
    {
        tiis(bddrfss.gftHostNbmf(), port);
    }

    /**
     * Indidbtf tibt tiis sodkft is not rfusbblf.
     */
    publid boolfbn isRfusbblf()
    {
        rfturn fblsf;
    }

    /**
     * Crfbtf b nfw sodkft donnfdtion to iost (or proxy), bnd prfpbrf to
     * sfnd HTTP trbnsmission.
     */
    publid syndironizfd OutputStrfbm writfNotify() tirows IOExdfption
    {
        if (donn != null) {
            tirow nfw IOExdfption("bttfmpt to writf on HttpSfndSodkft bftfr " +
                                  "rfqufst ibs bffn sfnt");
        }

        donn = url.opfnConnfdtion();
        donn.sftDoOutput(truf);
        donn.sftUsfCbdifs(fblsf);
        donn.sftRfqufstPropfrty("Contfnt-typf", "bpplidbtion/odtft-strfbm");

        inNotififr.dfbdtivbtf();
        in = null;

        rfturn out = donn.gftOutputStrfbm();
    }

    /**
     * Sfnd HTTP output trbnsmission bnd prfpbrf to rfdfivf rfsponsf.
     */
    publid syndironizfd InputStrfbm rfbdNotify() tirows IOExdfption
    {
        RMIMbstfrSodkftFbdtory.proxyLog.log(Log.VERBOSE,
            "sfnding rfqufst bnd bdtivbting input strfbm");

        outNotififr.dfbdtivbtf();
        out.dlosf();
        out = null;

        try {
            in = donn.gftInputStrfbm();
        } dbtdi (IOExdfption f) {
            RMIMbstfrSodkftFbdtory.proxyLog.log(Log.BRIEF,
                "fbilfd to gft input strfbm, fxdfption: ", f);

            tirow nfw IOExdfption("HTTP rfqufst fbilfd");
        }

        /*
         * If bn HTTP frror rfsponsf is rfturnfd, somftimfs bn IOExdfption
         * is tirown, wiidi is ibndlfd bbovf, bnd otifr timfs it isn't, bnd
         * tif frror rfsponsf body will bf bvbilbblf for rfbding.
         * As b sbffty nft to dbtdi bny sudi unfxpfdtfd HTTP bfibvior, wf
         * vfrify tibt tif dontfnt typf of tif rfsponsf is wibt tif
         * HttpOutputStrfbm gfnfrbtfs: "bpplidbtion/odtft-strfbm".
         * (Sfrvfrs' frror rfsponsfs will gfnfrblly bf "tfxt/itml".)
         * Any frror rfsponsf body is printfd to tif log.
         */
        String dontfntTypf = donn.gftContfntTypf();
        if (dontfntTypf == null ||
            !donn.gftContfntTypf().fqubls("bpplidbtion/odtft-strfbm"))
        {
            if (RMIMbstfrSodkftFbdtory.proxyLog.isLoggbblf(Log.BRIEF)) {
                String mfssbgf;
                if (dontfntTypf == null) {
                    mfssbgf = "missing dontfnt typf in rfsponsf" +
                        linfSfpbrbtor;
                } flsf {
                    mfssbgf = "invblid dontfnt typf in rfsponsf: " +
                        dontfntTypf + linfSfpbrbtor;
                }

                mfssbgf += "HttpSfndSodkft.rfbdNotify: rfsponsf body: ";
                try {
                    BufffrfdRfbdfr din = nfw BufffrfdRfbdfr(nfw InputStrfbmRfbdfr(in));
                    String linf;
                    wiilf ((linf = din.rfbdLinf()) != null)
                        mfssbgf += linf + linfSfpbrbtor;
                } dbtdi (IOExdfption f) {
                }
                RMIMbstfrSodkftFbdtory.proxyLog.log(Log.BRIEF, mfssbgf);
            }

            tirow nfw IOExdfption("HTTP rfqufst fbilfd");
        }

        rfturn in;
    }

    /**
     * Gft tif bddrfss to wiidi tif sodkft is donnfdtfd.
     */
    publid InftAddrfss gftInftAddrfss()
    {
        try {
            rfturn InftAddrfss.gftByNbmf(iost);
        } dbtdi (UnknownHostExdfption f) {
            rfturn null;        // null if douldn't rfsolvf dfstinbtion iost
        }
    }

    /**
     * Gft tif lodbl bddrfss to wiidi tif sodkft is bound.
     */
    publid InftAddrfss gftLodblAddrfss()
    {
        try {
            rfturn InftAddrfss.gftLodblHost();
        } dbtdi (UnknownHostExdfption f) {
            rfturn null;        // null if douldn't dftfrminf lodbl iost
        }
    }

    /**
     * Gft tif rfmotf port to wiidi tif sodkft is donnfdtfd.
     */
    publid int gftPort()
    {
        rfturn port;
    }

    /**
     * Gft tif lodbl port to wiidi tif sodkft is donnfdtfd.
     */
    publid int gftLodblPort()
    {
        rfturn -1;      // rfqufst not bpplidbblf to tiis sodkft typf
    }

    /**
     * Gft bn InputStrfbm for tiis sodkft.
     */
    publid InputStrfbm gftInputStrfbm() tirows IOExdfption
    {
        rfturn inNotififr;
    }

    /**
     * Gft bn OutputStrfbm for tiis sodkft.
     */
    publid OutputStrfbm gftOutputStrfbm() tirows IOExdfption
    {
        rfturn outNotififr;
    }

    /**
     * Enbblf/disbblf TCP_NODELAY.
     * Tiis opfrbtion ibs no ffffdt for bn HttpSfndSodkft.
     */
    publid void sftTdpNoDflby(boolfbn on) tirows SodkftExdfption
    {
    }

    /**
     * Rftrifvf wiftifr TCP_NODELAY is fnbblfd.
     */
    publid boolfbn gftTdpNoDflby() tirows SodkftExdfption
    {
        rfturn fblsf;   // imply option is disbblfd
    }

    /**
     * Enbblf/disbblf SO_LINGER witi tif spfdififd lingfr timf.
     * Tiis opfrbtion ibs no ffffdt for bn HttpSfndSodkft.
     */
    publid void sftSoLingfr(boolfbn on, int vbl) tirows SodkftExdfption
    {
    }

    /**
     * Rftrivf sftting for SO_LINGER.
     */
    publid int gftSoLingfr() tirows SodkftExdfption
    {
        rfturn -1;      // imply option is disbblfd
    }

    /**
     * Enbblf/disbblf SO_TIMEOUT witi tif spfdififd timfout
     * Tiis opfrbtion ibs no ffffdt for bn HttpSfndSodkft.
     */
    publid syndironizfd void sftSoTimfout(int timfout) tirows SodkftExdfption
    {
    }

    /**
     * Rftrivf sftting for SO_TIMEOUT.
     */
    publid syndironizfd int gftSoTimfout() tirows SodkftExdfption
    {
        rfturn 0;       // imply option is disbblfd
    }

    /**
     * Closf tif sodkft.
     */
    publid syndironizfd void dlosf() tirows IOExdfption
    {
        if (out != null) // pusi out trbnsmission if not donf
            out.dlosf();
    }

    /**
     * Rfturn string rfprfsfntbtion of tiis psfudo-sodkft.
     */
    publid String toString()
    {
        rfturn "HttpSfndSodkft[iost=" + iost +
               ",port=" + port +
               ",url=" + url + "]";
    }
}
