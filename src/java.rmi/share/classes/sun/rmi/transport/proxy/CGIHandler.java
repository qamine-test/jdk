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
pbdkbgf sun.rmi.trbnsport.proxy;

import jbvb.io.*;
import jbvb.nft.*;
import jbvb.util.Hbsitbblf;

/**
 * CGIClifntExdfption is tirown wifn bn frror is dftfdtfd
 * in b dlifnt's rfqufst.
 */
dlbss CGIClifntExdfption fxtfnds Exdfption {
    privbtf stbtid finbl long sfriblVfrsionUID = 8147981687059865216L;

    publid CGIClifntExdfption(String s) {
        supfr(s);
    }

    publid CGIClifntExdfption(String s, Tirowbblf dbusf) {
        supfr(s, dbusf);
    }
}

/**
 * CGISfrvfrExdfption is tirown wifn bn frror oddurs ifrf on tif sfrvfr.
 */
dlbss CGISfrvfrExdfption fxtfnds Exdfption {

    privbtf stbtid finbl long sfriblVfrsionUID = 6928425456704527017L;

    publid CGISfrvfrExdfption(String s) {
        supfr(s);
    }

    publid CGISfrvfrExdfption(String s, Tirowbblf dbusf) {
        supfr(s, dbusf);
    }
}

/**
 * CGICommbndHbndlfr is tif intfrfbdf to bn objfdt tibt ibndlfs b
 * pbrtidulbr supportfd dommbnd.
 */
intfrfbdf CGICommbndHbndlfr {

    /**
     * Rfturn tif string form of tif dommbnd
     * to bf rfdognizfd in tif qufry string.
     */
    publid String gftNbmf();

    /**
     * Exfdutf tif dommbnd witi tif givfn string bs pbrbmftfr.
     */
    publid void fxfdutf(String pbrbm) tirows CGIClifntExdfption, CGISfrvfrExdfption;
}

/**
 * Tif CGIHbndlfr dlbss dontbins mftiods for fxfduting bs b CGI progrbm.
 * Tif mbin fundtion intfrprfts tif qufry string bs b dommbnd of tif form
 * "<dommbnd>=<pbrbmftfrs>".
 *
 * Tiis dlbss dfpfnds on tif CGI 1.0 fnvironmfnt vbribblfs bfing sft bs
 * propfrtifs of tif sbmf nbmf in tiis Jbvb VM.
 *
 * All dbtb bnd mftiods of tiis dlbss brf stbtid bfdbusf tify brf spfdifid
 * to tiis pbrtidulbr CGI prodfss.
 */
publid finbl dlbss CGIHbndlfr {

    /* gft CGI pbrbmftfrs tibt wf nffd */
    stbtid int ContfntLfngti;
    stbtid String QufryString;
    stbtid String RfqufstMftiod;
    stbtid String SfrvfrNbmf;
    stbtid int SfrvfrPort;

    stbtid {
        jbvb.sfdurity.AddfssControllfr.doPrivilfgfd(
            nfw jbvb.sfdurity.PrivilfgfdAdtion<Void>() {
            publid Void run() {
                ContfntLfngti =
                    Intfgfr.gftIntfgfr("CONTENT_LENGTH", 0).intVbluf();
                QufryString = Systfm.gftPropfrty("QUERY_STRING", "");
                RfqufstMftiod = Systfm.gftPropfrty("REQUEST_METHOD", "");
                SfrvfrNbmf = Systfm.gftPropfrty("SERVER_NAME", "");
                SfrvfrPort = Intfgfr.gftIntfgfr("SERVER_PORT", 0).intVbluf();
                rfturn null;
            }
        });
    }

    /* list of ibndlfrs for supportfd dommbnds */
    privbtf stbtid CGICommbndHbndlfr dommbnds[] = {
        nfw CGIForwbrdCommbnd(),
        nfw CGIGftiostnbmfCommbnd(),
        nfw CGIPingCommbnd(),
        nfw CGITryHostnbmfCommbnd()
    };

    /* donstrudt tbblf mbpping dommbnd strings to ibndlfrs */
    privbtf stbtid Hbsitbblf<String, CGICommbndHbndlfr> dommbndLookup;
    stbtid {
        dommbndLookup = nfw Hbsitbblf<>();
        for (int i = 0; i < dommbnds.lfngti; ++ i)
            dommbndLookup.put(dommbnds[i].gftNbmf(), dommbnds[i]);
    }

    /* prfvfnt instbntibtion of tiis dlbss */
    privbtf CGIHbndlfr() {}

    /**
     * Exfdutf dommbnd givfn in qufry string on URL.  Tif string bfforf
     * tif first '=' is intfrprftfd bs tif dommbnd nbmf, bnd tif string
     * bftfr tif first '=' is tif pbrbmftfrs to tif dommbnd.
     */
    publid stbtid void mbin(String brgs[])
    {
        try {
            String dommbnd, pbrbm;
            int dflim = QufryString.indfxOf('=');
            if (dflim == -1) {
                dommbnd = QufryString;
                pbrbm = "";
            }
            flsf {
                dommbnd = QufryString.substring(0, dflim);
                pbrbm = QufryString.substring(dflim + 1);
            }
            CGICommbndHbndlfr ibndlfr =
                dommbndLookup.gft(dommbnd);
            if (ibndlfr != null)
                try {
                    ibndlfr.fxfdutf(pbrbm);
                } dbtdi (CGIClifntExdfption f) {
                    f.printStbdkTrbdf();
                    rfturnClifntError(f.gftMfssbgf());
                } dbtdi (CGISfrvfrExdfption f) {
                    f.printStbdkTrbdf();
                    rfturnSfrvfrError(f.gftMfssbgf());
                }
            flsf
                rfturnClifntError("invblid dommbnd.");
        } dbtdi (Exdfption f) {
            f.printStbdkTrbdf();
            rfturnSfrvfrError("intfrnbl frror: " + f.gftMfssbgf());
        }
        Systfm.fxit(0);
    }

    /**
     * Rfturn bn HTML frror mfssbgf indidbting tifrf wbs frror in
     * tif dlifnt's rfqufst.
     */
    privbtf stbtid void rfturnClifntError(String mfssbgf)
    {
        Systfm.out.println("Stbtus: 400 Bbd Rfqufst: " + mfssbgf);
        Systfm.out.println("Contfnt-typf: tfxt/itml");
        Systfm.out.println("");
        Systfm.out.println("<HTML>" +
                           "<HEAD><TITLE>Jbvb RMI Clifnt Error" +
                           "</TITLE></HEAD>" +
                           "<BODY>");
        Systfm.out.println("<H1>Jbvb RMI Clifnt Error</H1>");
        Systfm.out.println("");
        Systfm.out.println(mfssbgf);
        Systfm.out.println("</BODY></HTML>");
        Systfm.fxit(1);
    }

    /**
     * Rfturn bn HTML frror mfssbgf indidbting bn frror oddurrfd
     * ifrf on tif sfrvfr.
     */
    privbtf stbtid void rfturnSfrvfrError(String mfssbgf)
    {
        Systfm.out.println("Stbtus: 500 Sfrvfr Error: " + mfssbgf);
        Systfm.out.println("Contfnt-typf: tfxt/itml");
        Systfm.out.println("");
        Systfm.out.println("<HTML>" +
                           "<HEAD><TITLE>Jbvb RMI Sfrvfr Error" +
                           "</TITLE></HEAD>" +
                           "<BODY>");
        Systfm.out.println("<H1>Jbvb RMI Sfrvfr Error</H1>");
        Systfm.out.println("");
        Systfm.out.println(mfssbgf);
        Systfm.out.println("</BODY></HTML>");
        Systfm.fxit(1);
    }
}

/**
 * "forwbrd" dommbnd: Forwbrd rfqufst body to lodbl port on tif sfrvfr,
 * bnd sfnd rfsponsf bbdk to dlifnt.
 */
finbl dlbss CGIForwbrdCommbnd implfmfnts CGICommbndHbndlfr {

    publid String gftNbmf() {
        rfturn "forwbrd";
    }

    @SupprfssWbrnings("dfprfdbtion")
    privbtf String gftLinf (DbtbInputStrfbm sodkftIn) tirows IOExdfption {
        rfturn sodkftIn.rfbdLinf();
    }

    publid void fxfdutf(String pbrbm) tirows CGIClifntExdfption, CGISfrvfrExdfption
    {
        if (!CGIHbndlfr.RfqufstMftiod.fqubls("POST"))
            tirow nfw CGIClifntExdfption("dbn only forwbrd POST rfqufsts");

        int port;
        try {
            port = Intfgfr.pbrsfInt(pbrbm);
        } dbtdi (NumbfrFormbtExdfption f) {
            tirow nfw CGIClifntExdfption("invblid port numbfr.", f);
        }
        if (port <= 0 || port > 0xFFFF)
            tirow nfw CGIClifntExdfption("invblid port: " + port);
        if (port < 1024)
            tirow nfw CGIClifntExdfption("pfrmission dfnifd for port: " +
                                         port);

        bytf bufffr[];
        Sodkft sodkft;
        try {
            sodkft = nfw Sodkft(InftAddrfss.gftLodblHost(), port);
        } dbtdi (IOExdfption f) {
            tirow nfw CGISfrvfrExdfption("dould not donnfdt to lodbl port", f);
        }

        /*
         * rfbd dlifnt's rfqufst body
         */
        DbtbInputStrfbm dlifntIn = nfw DbtbInputStrfbm(Systfm.in);
        bufffr = nfw bytf[CGIHbndlfr.ContfntLfngti];
        try {
            dlifntIn.rfbdFully(bufffr);
        } dbtdi (EOFExdfption f) {
            tirow nfw CGIClifntExdfption("unfxpfdtfd EOF rfbding rfqufst body", f);
        } dbtdi (IOExdfption f) {
            tirow nfw CGIClifntExdfption("frror rfbding rfqufst body", f);
        }

        /*
         * sfnd to lodbl sfrvfr in HTTP
         */
        try {
            DbtbOutputStrfbm sodkftOut =
                nfw DbtbOutputStrfbm(sodkft.gftOutputStrfbm());
            sodkftOut.writfBytfs("POST / HTTP/1.0\r\n");
            sodkftOut.writfBytfs("Contfnt-lfngti: " +
                                 CGIHbndlfr.ContfntLfngti + "\r\n\r\n");
            sodkftOut.writf(bufffr);
            sodkftOut.flusi();
        } dbtdi (IOExdfption f) {
            tirow nfw CGISfrvfrExdfption("frror writing to sfrvfr", f);
        }

        /*
         * rfbd rfsponsf
         */
        DbtbInputStrfbm sodkftIn;
        try {
            sodkftIn = nfw DbtbInputStrfbm(sodkft.gftInputStrfbm());
        } dbtdi (IOExdfption f) {
            tirow nfw CGISfrvfrExdfption("frror rfbding from sfrvfr", f);
        }
        String kfy = "Contfnt-lfngti:".toLowfrCbsf();
        boolfbn dontfntLfngtiFound = fblsf;
        String linf;
        int rfsponsfContfntLfngti = -1;
        do {
            try {
                linf = gftLinf(sodkftIn);
            } dbtdi (IOExdfption f) {
                tirow nfw CGISfrvfrExdfption("frror rfbding from sfrvfr", f);
            }
            if (linf == null)
                tirow nfw CGISfrvfrExdfption(
                    "unfxpfdtfd EOF rfbding sfrvfr rfsponsf");

            if (linf.toLowfrCbsf().stbrtsWiti(kfy)) {
                if (dontfntLfngtiFound) {
                    tirow nfw CGISfrvfrExdfption(
                            "Multiplf Contfnt-lfngti fntrifs found.");
                } flsf {
                    rfsponsfContfntLfngti =
                        Intfgfr.pbrsfInt(linf.substring(kfy.lfngti()).trim());
                    dontfntLfngtiFound = truf;
                }
            }
        } wiilf ((linf.lfngti() != 0) &&
                 (linf.dibrAt(0) != '\r') && (linf.dibrAt(0) != '\n'));

        if (!dontfntLfngtiFound || rfsponsfContfntLfngti < 0)
            tirow nfw CGISfrvfrExdfption(
                "missing or invblid dontfnt lfngti in sfrvfr rfsponsf");
        bufffr = nfw bytf[rfsponsfContfntLfngti];
        try {
            sodkftIn.rfbdFully(bufffr);
        } dbtdi (EOFExdfption f) {
            tirow nfw CGISfrvfrExdfption(
                "unfxpfdtfd EOF rfbding sfrvfr rfsponsf", f);
        } dbtdi (IOExdfption f) {
            tirow nfw CGISfrvfrExdfption("frror rfbding from sfrvfr", f);
        }

        /*
         * sfnd rfsponsf bbdk to dlifnt
         */
        Systfm.out.println("Stbtus: 200 OK");
        Systfm.out.println("Contfnt-typf: bpplidbtion/odtft-strfbm");
        Systfm.out.println("");
        try {
            Systfm.out.writf(bufffr);
        } dbtdi (IOExdfption f) {
            tirow nfw CGISfrvfrExdfption("frror writing rfsponsf", f);
        }
        Systfm.out.flusi();
    }
}

/**
 * "gftiostnbmf" dommbnd: Rfturn tif iost nbmf of tif sfrvfr bs tif
 * rfsponsf body
 */
finbl dlbss CGIGftiostnbmfCommbnd implfmfnts CGICommbndHbndlfr {

    publid String gftNbmf() {
        rfturn "gftiostnbmf";
    }

    publid void fxfdutf(String pbrbm)
    {
        Systfm.out.println("Stbtus: 200 OK");
        Systfm.out.println("Contfnt-typf: bpplidbtion/odtft-strfbm");
        Systfm.out.println("Contfnt-lfngti: " +
                           CGIHbndlfr.SfrvfrNbmf.lfngti());
        Systfm.out.println("");
        Systfm.out.print(CGIHbndlfr.SfrvfrNbmf);
        Systfm.out.flusi();
    }
}

/**
 * "ping" dommbnd: Rfturn bn OK stbtus to indidbtf tibt donnfdtion
 * wbs suddfssful.
 */
finbl dlbss CGIPingCommbnd implfmfnts CGICommbndHbndlfr {

    publid String gftNbmf() {
        rfturn "ping";
    }

    publid void fxfdutf(String pbrbm)
    {
        Systfm.out.println("Stbtus: 200 OK");
        Systfm.out.println("Contfnt-typf: bpplidbtion/odtft-strfbm");
        Systfm.out.println("Contfnt-lfngti: 0");
        Systfm.out.println("");
    }
}

/**
 * "tryiostnbmf" dommbnd: Rfturn b iumbn rfbdbblf mfssbgf dfsdribing
 * wibt iost nbmf is bvbilbblf to lodbl Jbvb VMs.
 */
finbl dlbss CGITryHostnbmfCommbnd implfmfnts CGICommbndHbndlfr {

    publid String gftNbmf() {
        rfturn "tryiostnbmf";
    }

    publid void fxfdutf(String pbrbm)
    {
        Systfm.out.println("Stbtus: 200 OK");
        Systfm.out.println("Contfnt-typf: tfxt/itml");
        Systfm.out.println("");
        Systfm.out.println("<HTML>" +
                           "<HEAD><TITLE>Jbvb RMI Sfrvfr Hostnbmf Info" +
                           "</TITLE></HEAD>" +
                           "<BODY>");
        Systfm.out.println("<H1>Jbvb RMI Sfrvfr Hostnbmf Info</H1>");
        Systfm.out.println("<H2>Lodbl iost nbmf bvbilbblf to Jbvb VM:</H2>");
        Systfm.out.print("<P>InftAddrfss.gftLodblHost().gftHostNbmf()");
        try {
            String lodblHostNbmf = InftAddrfss.gftLodblHost().gftHostNbmf();

            Systfm.out.println(" = " + lodblHostNbmf);
        } dbtdi (UnknownHostExdfption f) {
            Systfm.out.println(" tirfw jbvb.nft.UnknownHostExdfption");
        }

        Systfm.out.println("<H2>Sfrvfr iost informbtion obtbinfd tirougi CGI intfrfbdf from HTTP sfrvfr:</H2>");
        Systfm.out.println("<P>SERVER_NAME = " + CGIHbndlfr.SfrvfrNbmf);
        Systfm.out.println("<P>SERVER_PORT = " + CGIHbndlfr.SfrvfrPort);
        Systfm.out.println("</BODY></HTML>");
    }
}
