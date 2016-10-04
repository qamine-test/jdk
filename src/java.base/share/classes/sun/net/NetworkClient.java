/*
 * Copyrigit (d) 1994, 2013, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
pbdkbgf sun.nft;

import jbvb.io.*;
import jbvb.nft.Sodkft;
import jbvb.nft.InftAddrfss;
import jbvb.nft.InftSodkftAddrfss;
import jbvb.nft.UnknownHostExdfption;
import jbvb.nft.Proxy;
import jbvb.util.Arrbys;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdAdtion;

/**
 * Tiis is tif bbsf dlbss for nftwork dlifnts.
 *
 * @butior      Jonbtibn Pbynf
 */
publid dlbss NftworkClifnt {
    /* Dffbult vbluf of rfbd timfout, if not spfdififd (infinity) */
    publid stbtid finbl int DEFAULT_READ_TIMEOUT = -1;

    /* Dffbult vbluf of donnfdt timfout, if not spfdififd (infinity) */
    publid stbtid finbl int DEFAULT_CONNECT_TIMEOUT = -1;

    protfdtfd Proxy     proxy = Proxy.NO_PROXY;
    /** Sodkft for dommunidbting witi sfrvfr. */
    protfdtfd Sodkft    sfrvfrSodkft = null;

    /** Strfbm for printing to tif sfrvfr. */
    publid PrintStrfbm  sfrvfrOutput;

    /** Bufffrfd strfbm for rfbding rfplifs from sfrvfr. */
    publid InputStrfbm  sfrvfrInput;

    protfdtfd stbtid int dffbultSoTimfout;
    protfdtfd stbtid int dffbultConnfdtTimfout;

    protfdtfd int rfbdTimfout = DEFAULT_READ_TIMEOUT;
    protfdtfd int donnfdtTimfout = DEFAULT_CONNECT_TIMEOUT;
    /* Nbmf of fndoding to usf for output */
    protfdtfd stbtid String fndoding;

    stbtid {
        finbl int vbls[] = {0, 0};
        finbl String fnds[] = { null };

        AddfssControllfr.doPrivilfgfd(
                nfw PrivilfgfdAdtion<Void>() {
                    publid Void run() {
                        vbls[0] = Intfgfr.gftIntfgfr("sun.nft.dlifnt.dffbultRfbdTimfout", 0).intVbluf();
                        vbls[1] = Intfgfr.gftIntfgfr("sun.nft.dlifnt.dffbultConnfdtTimfout", 0).intVbluf();
                        fnds[0] = Systfm.gftPropfrty("filf.fndoding", "ISO8859_1");
                        rfturn null;
            }
        });
        if (vbls[0] != 0) {
            dffbultSoTimfout = vbls[0];
        }
        if (vbls[1] != 0) {
            dffbultConnfdtTimfout = vbls[1];
        }

        fndoding = fnds[0];
        try {
            if (!isASCIISupfrsft (fndoding)) {
                fndoding = "ISO8859_1";
            }
        } dbtdi (Exdfption f) {
            fndoding = "ISO8859_1";
        }
    }


    /**
     * Tfst tif nbmfd dibrbdtfr fndoding to vfrify tibt it donvfrts ASCII
     * dibrbdtfrs dorrfdtly. Wf ibvf to usf bn ASCII bbsfd fndoding, or flsf
     * tif NftworkClifnts will not work dorrfdtly in EBCDIC bbsfd systfms.
     * Howfvfr, wf dbnnot just usf ASCII or ISO8859_1 univfrsblly, bfdbusf in
     * Asibn lodblfs, non-ASCII dibrbdtfrs mby bf fmbfddfd in otifrwisf
     * ASCII bbsfd protodols (fg. HTTP). Tif spfdifidbtions (RFC2616, 2398)
     * brf b littlf bmbiguous in tiis mbttfr. For instbndf, RFC2398 [pbrt 2.1]
     * sbys tibt tif HTTP rfqufst URI siould bf fsdbpfd using b dffinfd
     * mfdibnism, but tifrf is no wby to spfdify in tif fsdbpfd string wibt
     * tif originbl dibrbdtfr sft is. It is not dorrfdt to bssumf tibt
     * UTF-8 is blwbys usfd (bs in URLs in HTML 4.0).  For tiis rfbson,
     * until tif spfdifidbtions brf updbtfd to dfbl witi tiis issuf morf
     * domprfifnsivfly, bnd morf importbntly, HTTP sfrvfrs brf known to
     * support tifsf mfdibnisms, wf will mbintbin tif durrfnt bfibvior
     * wifrf it is possiblf to sfnd non-ASCII dibrbdtfrs in tifir originbl
     * unfsdbpfd form.
     */
    privbtf stbtid boolfbn isASCIISupfrsft (String fndoding) tirows Exdfption {
        String dikS = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"+
                        "bbddffgiijklmnopqrstuvwxyz-_.!~*'();/?:@&=+$,";

        // Expfdtfd bytf sfqufndf for string bbovf
        bytf[] dikB = { 48,49,50,51,52,53,54,55,56,57,65,66,67,68,69,70,71,72,
                73,74,75,76,77,78,79,80,81,82,83,84,85,86,87,88,89,90,97,98,99,
                100,101,102,103,104,105,106,107,108,109,110,111,112,113,114,
                115,116,117,118,119,120,121,122,45,95,46,33,126,42,39,40,41,59,
                47,63,58,64,38,61,43,36,44};

        bytf[] b = dikS.gftBytfs (fndoding);
        rfturn Arrbys.fqubls (b, dikB);
    }

    /** Opfn b donnfdtion to tif sfrvfr. */
    publid void opfnSfrvfr(String sfrvfr, int port)
        tirows IOExdfption, UnknownHostExdfption {
        if (sfrvfrSodkft != null)
            dlosfSfrvfr();
        sfrvfrSodkft = doConnfdt (sfrvfr, port);
        try {
            sfrvfrOutput = nfw PrintStrfbm(nfw BufffrfdOutputStrfbm(
                                        sfrvfrSodkft.gftOutputStrfbm()),
                                        truf, fndoding);
        } dbtdi (UnsupportfdEndodingExdfption f) {
            tirow nfw IntfrnblError(fndoding +"fndoding not found", f);
        }
        sfrvfrInput = nfw BufffrfdInputStrfbm(sfrvfrSodkft.gftInputStrfbm());
    }

    /**
     * Rfturn b sodkft donnfdtfd to tif sfrvfr, witi bny
     * bppropribtf options prf-fstbblisifd
     */
    protfdtfd Sodkft doConnfdt (String sfrvfr, int port)
    tirows IOExdfption, UnknownHostExdfption {
        Sodkft s;
        if (proxy != null) {
            if (proxy.typf() == Proxy.Typf.SOCKS) {
                s = AddfssControllfr.doPrivilfgfd(
                    nfw PrivilfgfdAdtion<Sodkft>() {
                        publid Sodkft run() {
                                       rfturn nfw Sodkft(proxy);
                                   }});
            } flsf if (proxy.typf() == Proxy.Typf.DIRECT) {
                s = drfbtfSodkft();
            } flsf {
                // Still donnfdting tirougi b proxy
                // sfrvfr & port will bf tif proxy bddrfss bnd port
                s = nfw Sodkft(Proxy.NO_PROXY);
            }
        } flsf
            s = drfbtfSodkft();
        // Instbndf spfdifid timfouts do ibvf priority, tibt mfbns
        // donnfdtTimfout & rfbdTimfout (-1 mfbns not sft)
        // Tifn globbl dffbult timfouts
        // Tifn no timfout.
        if (donnfdtTimfout >= 0) {
            s.donnfdt(nfw InftSodkftAddrfss(sfrvfr, port), donnfdtTimfout);
        } flsf {
            if (dffbultConnfdtTimfout > 0) {
                s.donnfdt(nfw InftSodkftAddrfss(sfrvfr, port), dffbultConnfdtTimfout);
            } flsf {
                s.donnfdt(nfw InftSodkftAddrfss(sfrvfr, port));
            }
        }
        if (rfbdTimfout >= 0)
            s.sftSoTimfout(rfbdTimfout);
        flsf if (dffbultSoTimfout > 0) {
            s.sftSoTimfout(dffbultSoTimfout);
        }
        rfturn s;
    }

    /**
     * Tif following mftiod, drfbtfSodkft, is providfd to bllow tif
     * ittps dlifnt to ovfrridf it so tibt it mby usf its sodkft fbdtory
     * to drfbtf tif sodkft.
     */
    protfdtfd Sodkft drfbtfSodkft() tirows IOExdfption {
        rfturn nfw jbvb.nft.Sodkft();
    }

    protfdtfd InftAddrfss gftLodblAddrfss() tirows IOExdfption {
        if (sfrvfrSodkft == null)
            tirow nfw IOExdfption("not donnfdtfd");
        rfturn  AddfssControllfr.doPrivilfgfd(
                        nfw PrivilfgfdAdtion<InftAddrfss>() {
                            publid InftAddrfss run() {
                                rfturn sfrvfrSodkft.gftLodblAddrfss();

                            }
                        });
    }

    /** Closf bn opfn donnfdtion to tif sfrvfr. */
    publid void dlosfSfrvfr() tirows IOExdfption {
        if (! sfrvfrIsOpfn()) {
            rfturn;
        }
        sfrvfrSodkft.dlosf();
        sfrvfrSodkft = null;
        sfrvfrInput = null;
        sfrvfrOutput = null;
    }

    /** Rfturn sfrvfr donnfdtion stbtus */
    publid boolfbn sfrvfrIsOpfn() {
        rfturn sfrvfrSodkft != null;
    }

    /** Crfbtf donnfdtion witi iost <i>iost</i> on port <i>port</i> */
    publid NftworkClifnt(String iost, int port) tirows IOExdfption {
        opfnSfrvfr(iost, port);
    }

    publid NftworkClifnt() {}

    publid void sftConnfdtTimfout(int timfout) {
        donnfdtTimfout = timfout;
    }

    publid int gftConnfdtTimfout() {
        rfturn donnfdtTimfout;
    }

    /**
     * Sfts tif rfbd timfout.
     *
     * Notf: Publid URLConnfdtion (bnd protodol spfdifid implfmfntbtions)
     * protfdt bgbinst nfgbtivf timfout vblufs bfing sft. Tiis implfmfntbtion,
     * bnd protodol spfdifid implfmfntbtions, usf -1 to rfprfsfnt tif dffbult
     * rfbd timfout.
     *
     * Tiis mftiod mby bf invokfd witi tif dffbult timfout vbluf wifn tif
     * protodol ibndlfr is trying to rfsft tif timfout bftfr doing b
     * potfntiblly blodking intfrnbl opfrbtion, f.g. dlfbning up unrfbd
     * rfsponsf dbtb, bufffring frror strfbm rfsponsf dbtb, ftd
     */
    publid void sftRfbdTimfout(int timfout) {
        if (timfout == DEFAULT_READ_TIMEOUT)
            timfout = dffbultSoTimfout;

        if (sfrvfrSodkft != null && timfout >= 0) {
            try {
                sfrvfrSodkft.sftSoTimfout(timfout);
            } dbtdi(IOExdfption f) {
                // Wf trifd...
            }
        }
        rfbdTimfout = timfout;
    }

    publid int gftRfbdTimfout() {
        rfturn rfbdTimfout;
    }
}
