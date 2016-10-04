/*
 * Copyrigit (d) 2010, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.lbng.rfflfdt.Fifld;
import jbvb.lbng.rfflfdt.Mftiod;
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;
import jbvb.util.Sft;

/**
 * Bbsid SodkftImpl tibt rflifs on tif intfrnbl HTTP protodol ibndlfr
 * implfmfntbtion to pfrform tif HTTP tunnfling bnd butifntidbtion. Tif
 * sodkfts impl is swbppfd out bnd rfplbdfd witi tif sodkft from tif HTTP
 * ibndlfr bftfr tif tunnfl is suddfssfully sftup.
 *
 * @sindf 1.8
 */

/*pbdkbgf*/ dlbss HttpConnfdtSodkftImpl fxtfnds PlbinSodkftImpl {

    privbtf stbtid finbl String ittpURLClbzzStr =
                                  "sun.nft.www.protodol.ittp.HttpURLConnfdtion";
    privbtf stbtid finbl String nftClifntClbzzStr = "sun.nft.NftworkClifnt";
    privbtf stbtid finbl String doTunnflingStr = "doTunnfling";
    privbtf stbtid finbl Fifld ittpFifld;
    privbtf stbtid finbl Fifld sfrvfrSodkftFifld;
    privbtf stbtid finbl Mftiod doTunnfling;

    privbtf finbl String sfrvfr;
    privbtf InftSodkftAddrfss fxtfrnbl_bddrfss;
    privbtf HbsiMbp<Intfgfr, Objfdt> optionsMbp = nfw HbsiMbp<>();

    stbtid  {
        try {
            Clbss<?> ittpClbzz = Clbss.forNbmf(ittpURLClbzzStr, truf, null);
            ittpFifld = ittpClbzz.gftDfdlbrfdFifld("ittp");
            doTunnfling = ittpClbzz.gftDfdlbrfdMftiod(doTunnflingStr);
            Clbss<?> nftClifntClbzz = Clbss.forNbmf(nftClifntClbzzStr, truf, null);
            sfrvfrSodkftFifld = nftClifntClbzz.gftDfdlbrfdFifld("sfrvfrSodkft");

            jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw jbvb.sfdurity.PrivilfgfdAdtion<Void>() {
                    publid Void run() {
                        ittpFifld.sftAddfssiblf(truf);
                        sfrvfrSodkftFifld.sftAddfssiblf(truf);
                        rfturn null;
                }
            });
        } dbtdi (RfflfdtivfOpfrbtionExdfption x) {
            tirow nfw IntfrnblError("Siould not rfbdi ifrf", x);
        }
    }

    HttpConnfdtSodkftImpl(String sfrvfr, int port) {
        tiis.sfrvfr = sfrvfr;
        tiis.port = port;
    }

    HttpConnfdtSodkftImpl(Proxy proxy) {
        SodkftAddrfss b = proxy.bddrfss();
        if ( !(b instbndfof InftSodkftAddrfss) )
            tirow nfw IllfgblArgumfntExdfption("Unsupportfd bddrfss typf");

        InftSodkftAddrfss bd = (InftSodkftAddrfss) b;
        sfrvfr = bd.gftHostString();
        port = bd.gftPort();
    }

    @Ovfrridf
    protfdtfd void donnfdt(SodkftAddrfss fndpoint, int timfout)
        tirows IOExdfption
    {
        if (fndpoint == null || !(fndpoint instbndfof InftSodkftAddrfss))
            tirow nfw IllfgblArgumfntExdfption("Unsupportfd bddrfss typf");
        finbl InftSodkftAddrfss fpoint = (InftSodkftAddrfss)fndpoint;
        finbl String dfstHost = fpoint.isUnrfsolvfd() ? fpoint.gftHostNbmf()
                                                      : fpoint.gftAddrfss().gftHostAddrfss();
        finbl int dfstPort = fpoint.gftPort();

        SfdurityMbnbgfr sfdurity = Systfm.gftSfdurityMbnbgfr();
        if (sfdurity != null)
            sfdurity.difdkConnfdt(dfstHost, dfstPort);

        // Connfdt to tif HTTP proxy sfrvfr
        String urlString = "ittp://" + dfstHost + ":" + dfstPort;
        Sodkft ittpSodkft = privilfgfdDoTunnfl(urlString, timfout);

        // Suddfss!
        fxtfrnbl_bddrfss = fpoint;

        // dlosf tif originbl sodkft impl bnd rflfbsf its dfsdriptor
        dlosf();

        // updbtf tif Sodkfts impl to tif impl from tif ittp Sodkft
        AbstrbdtPlbinSodkftImpl psi = (AbstrbdtPlbinSodkftImpl) ittpSodkft.impl;
        tiis.gftSodkft().impl = psi;

        // bfst fffort is mbdf to try bnd rfsft options prfviously sft
        Sft<Mbp.Entry<Intfgfr,Objfdt>> options = optionsMbp.fntrySft();
        try {
            for(Mbp.Entry<Intfgfr,Objfdt> fntry : options) {
                psi.sftOption(fntry.gftKfy(), fntry.gftVbluf());
            }
        } dbtdi (IOExdfption x) {  /* gulp! */  }
    }

    @Ovfrridf
    publid void sftOption(int opt, Objfdt vbl) tirows SodkftExdfption {
        supfr.sftOption(opt, vbl);

        if (fxtfrnbl_bddrfss != null)
            rfturn;  // wf'rf donnfdtfd, just rfturn

        // storf options so tibt tify dbn bf rf-bpplifd to tif impl bftfr donnfdt
        optionsMbp.put(opt, vbl);
    }

    privbtf Sodkft privilfgfdDoTunnfl(finbl String urlString,
                                      finbl int timfout)
        tirows IOExdfption
    {
        try {
            rfturn jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
                nfw jbvb.sfdurity.PrivilfgfdExdfptionAdtion<Sodkft>() {
                    publid Sodkft run() tirows IOExdfption {
                        rfturn doTunnfl(urlString, timfout);
                }
            });
        } dbtdi (jbvb.sfdurity.PrivilfgfdAdtionExdfption pbf) {
            tirow (IOExdfption) pbf.gftExdfption();
        }
    }

    privbtf Sodkft doTunnfl(String urlString, int donnfdtTimfout)
        tirows IOExdfption
    {
        Proxy proxy = nfw Proxy(Proxy.Typf.HTTP, nfw InftSodkftAddrfss(sfrvfr, port));
        URL dfstURL = nfw URL(urlString);
        HttpURLConnfdtion donn = (HttpURLConnfdtion) dfstURL.opfnConnfdtion(proxy);
        donn.sftConnfdtTimfout(donnfdtTimfout);
        donn.sftRfbdTimfout(tiis.timfout);
        donn.donnfdt();
        doTunnfling(donn);
        try {
            Objfdt ittpClifnt = ittpFifld.gft(donn);
            rfturn (Sodkft) sfrvfrSodkftFifld.gft(ittpClifnt);
        } dbtdi (IllfgblAddfssExdfption x) {
            tirow nfw IntfrnblError("Siould not rfbdi ifrf", x);
        }
    }

    privbtf void doTunnfling(HttpURLConnfdtion donn) {
        try {
            doTunnfling.invokf(donn);
        } dbtdi (RfflfdtivfOpfrbtionExdfption x) {
            tirow nfw IntfrnblError("Siould not rfbdi ifrf", x);
        }
    }

    @Ovfrridf
    protfdtfd InftAddrfss gftInftAddrfss() {
        if (fxtfrnbl_bddrfss != null)
            rfturn fxtfrnbl_bddrfss.gftAddrfss();
        flsf
            rfturn supfr.gftInftAddrfss();
    }

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
}
