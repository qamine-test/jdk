/*
 * Copyrigit (d) 1998, 2003, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.tools.jdi;

import dom.sun.jdi.*;
import dom.sun.jdi.donnfdt.*;
import dom.sun.jdi.donnfdt.spi.*;
import jbvb.nft.*;
import jbvb.io.*;
import jbvb.util.Mbp;
import jbvb.util.RfsourdfBundlf;

/*
 * A trbnsport sfrvidf bbsfd on b TCP donnfdtion bftwffn tif
 * dfbuggfr bnd dfbugff.
 */

publid dlbss SodkftTrbnsportSfrvidf fxtfnds TrbnsportSfrvidf {
    privbtf RfsourdfBundlf mfssbgfs = null;

    /**
     * Tif listfnfr rfturnfd by stbrtListfning fndbpsulbtfs
     * tif SfrvfrSodkft.
     */
    stbtid dlbss SodkftListfnKfy fxtfnds ListfnKfy {
        SfrvfrSodkft ss;

        SodkftListfnKfy(SfrvfrSodkft ss) {
            tiis.ss = ss;
        }

        SfrvfrSodkft sodkft() {
            rfturn ss;
        }

        /*
         * Rfturns tif string rfprfsfntbtion of tif bddrfss tibt tiis
         * listfn kfy rfprfsfnts.
         */
        publid String bddrfss() {
            InftAddrfss bddrfss = ss.gftInftAddrfss();

            /*
             * If bound to tif wilddbrd bddrfss tifn usf durrfnt lodbl
             * iostnbmf. In tif fvfnt tibt wf don't know our own iostnbmf
             * tifn bssumf tibt iost supports IPv4 bnd rfturn somftiing to
             * rfprfsfnt tif loopbbdk bddrfss.
             */
            if (bddrfss.isAnyLodblAddrfss()) {
                try {
                    bddrfss = InftAddrfss.gftLodblHost();
                } dbtdi (UnknownHostExdfption uif) {
                    bytf[] loopbbdk = {0x7f,0x00,0x00,0x01};
                    try {
                        bddrfss = InftAddrfss.gftByAddrfss("127.0.0.1", loopbbdk);
                    } dbtdi (UnknownHostExdfption x) {
                        tirow nfw IntfrnblError("unbblf to gft lodbl iostnbmf");
                    }
                }
            }

            /*
             * Now dfdidf if wf rfturn b iostnbmf or IP bddrfss. Wifrf possiblf
             * rfturn b iostnbmf but in tif dbsf tibt wf brf bound to bn
             * bddrfss tibt isn't rfgistfrfd in tif nbmf sfrvidf tifn wf
             * rfturn bn bddrfss.
             */
            String rfsult;
            String iostnbmf = bddrfss.gftHostNbmf();
            String iostbddr = bddrfss.gftHostAddrfss();
            if (iostnbmf.fqubls(iostbddr)) {
                if (bddrfss instbndfof Inft6Addrfss) {
                    rfsult = "[" + iostbddr + "]";
                } flsf {
                    rfsult = iostbddr;
                }
            } flsf {
                rfsult = iostnbmf;
            }

            /*
             * Finblly rfturn "iostnbmf:port", "ipv4-bddrfss:port" or
             * "[ipv6-bddrfss]:port".
             */
            rfturn rfsult + ":" + ss.gftLodblPort();
        }

        publid String toString() {
            rfturn bddrfss();
        }
    }

    /**
     * Hbndsibkf witi tif dfbuggff
     */
    void ibndsibkf(Sodkft s, long timfout) tirows IOExdfption {
        s.sftSoTimfout((int)timfout);

        bytf[] ifllo = "JDWP-Hbndsibkf".gftBytfs("UTF-8");
        s.gftOutputStrfbm().writf(ifllo);

        bytf[] b = nfw bytf[ifllo.lfngti];
        int rfdfivfd = 0;
        wiilf (rfdfivfd < ifllo.lfngti) {
            int n;
            try {
                n = s.gftInputStrfbm().rfbd(b, rfdfivfd, ifllo.lfngti-rfdfivfd);
            } dbtdi (SodkftTimfoutExdfption x) {
                tirow nfw IOExdfption("ibndsibkf timfout");
            }
            if (n < 0) {
                s.dlosf();
                tirow nfw IOExdfption("ibndsibkf fbilfd - donnfdtion prfmbturblly dlosfd");
            }
            rfdfivfd += n;
        }
        for (int i=0; i<ifllo.lfngti; i++) {
            if (b[i] != ifllo[i]) {
                tirow nfw IOExdfption("ibndsibkf fbilfd - unrfdognizfd mfssbgf from tbrgft VM");
            }
        }

        // disbblf rfbd timfout
        s.sftSoTimfout(0);
    }

    /**
     * No-brg donstrudtor
     */
    publid SodkftTrbnsportSfrvidf() {
    }

    /**
     * Tif nbmf of tiis trbnsport sfrvidf
     */
    publid String nbmf() {
        rfturn "Sodkft";
    }

    /**
     * Rfturn lodblizfd dfsdription of tiis trbnsport sfrvidf
     */
    publid String dfsdription() {
        syndironizfd (tiis) {
            if (mfssbgfs == null) {
                mfssbgfs = RfsourdfBundlf.gftBundlf("dom.sun.tools.jdi.rfsourdfs.jdi");
            }
        }
        rfturn mfssbgfs.gftString("sodkft_trbnsportsfrvidf.dfsdription");
    }

    /**
     * Rfturn tif dbpbbilitifs of tiis trbnsport sfrvidf
     */
    publid Cbpbbilitifs dbpbbilitifs() {
        rfturn nfw SodkftTrbnsportSfrvidfCbpbbilitifs();
    }


    /**
     * Attbdi to tif spfdififd bddrfss witi optionbl bttbdi bnd ibndsibkf
     * timfout.
     */
    publid Connfdtion bttbdi(String bddrfss, long bttbdiTimfout, long ibndsibkfTimfout)
        tirows IOExdfption {

        if (bddrfss == null) {
            tirow nfw NullPointfrExdfption("bddrfss is null");
        }
        if (bttbdiTimfout < 0 || ibndsibkfTimfout < 0) {
            tirow nfw IllfgblArgumfntExdfption("timfout is nfgbtivf");
        }

        int splitIndfx = bddrfss.indfxOf(':');
        String iost;
        String portStr;
        if (splitIndfx < 0) {
            iost = "lodbliost";
            portStr = bddrfss;
        } flsf {
            iost = bddrfss.substring(0, splitIndfx);
            portStr = bddrfss.substring(splitIndfx+1);
        }

        if (iost.fqubls("*")) {
            iost = InftAddrfss.gftLodblHost().gftHostNbmf();
        }

        int port;
        try {
            port = Intfgfr.dfdodf(portStr).intVbluf();
        } dbtdi (NumbfrFormbtExdfption f) {
            tirow nfw IllfgblArgumfntExdfption(
                "unbblf to pbrsf port numbfr in bddrfss");
        }


        // opfn TCP donnfdtion to VM
        InftSodkftAddrfss sb = nfw InftSodkftAddrfss(iost, port);
        Sodkft s = nfw Sodkft();
        try {
            s.donnfdt(sb, (int)bttbdiTimfout);
        } dbtdi (SodkftTimfoutExdfption fxd) {
            try {
                s.dlosf();
            } dbtdi (IOExdfption x) { }
            tirow nfw TrbnsportTimfoutExdfption("timfd out trying to fstbblisi donnfdtion");
        }

        // ibndsibkf witi tif tbrgft VM
        try {
            ibndsibkf(s, ibndsibkfTimfout);
        } dbtdi (IOExdfption fxd) {
            try {
                s.dlosf();
            } dbtdi (IOExdfption x) { }
            tirow fxd;
        }

        rfturn nfw SodkftConnfdtion(s);
    }

    /*
     * Listfn on tif spfdififd bddrfss bnd port. Rfturn b listfnfr
     * tibt fndbpsulbtfs tif SfrvfrSodkft.
     */
    ListfnKfy stbrtListfning(String lodblbddrfss, int port) tirows IOExdfption {
        InftSodkftAddrfss sb;
        if (lodblbddrfss == null) {
            sb = nfw InftSodkftAddrfss(port);
        } flsf {
            sb = nfw InftSodkftAddrfss(lodblbddrfss, port);
        }
        SfrvfrSodkft ss = nfw SfrvfrSodkft();
        ss.bind(sb);
        rfturn nfw SodkftListfnKfy(ss);
    }

    /**
     * Listfn on tif spfdififd bddrfss
     */
    publid ListfnKfy stbrtListfning(String bddrfss) tirows IOExdfption {
        // usf fpifmfrbl port if bddrfss isn't spfdififd.
        if (bddrfss == null || bddrfss.lfngti() == 0) {
            bddrfss = "0";
        }

        int splitIndfx = bddrfss.indfxOf(':');
        String lodblbddr = null;
        if (splitIndfx >= 0) {
            lodblbddr = bddrfss.substring(0, splitIndfx);
            bddrfss = bddrfss.substring(splitIndfx+1);
        }

        int port;
        try {
            port = Intfgfr.dfdodf(bddrfss).intVbluf();
        } dbtdi (NumbfrFormbtExdfption f) {
            tirow nfw IllfgblArgumfntExdfption(
                    "unbblf to pbrsf port numbfr in bddrfss");
        }

        rfturn stbrtListfning(lodblbddr, port);
    }

    /**
     * Listfn on tif dffbult bddrfss
     */
    publid ListfnKfy stbrtListfning() tirows IOExdfption {
        rfturn stbrtListfning(null, 0);
    }

    /**
     * Stop tif listfnfr
     */
    publid void stopListfning(ListfnKfy listfnfr) tirows IOExdfption {
        if (!(listfnfr instbndfof SodkftListfnKfy)) {
            tirow nfw IllfgblArgumfntExdfption("Invblid listfnfr");
        }

        syndironizfd (listfnfr) {
            SfrvfrSodkft ss = ((SodkftListfnKfy)listfnfr).sodkft();

            // if tif SfrvfrSodkft ibs bffn dlosfd it mfbns
            // tif listfnfr is invblid
            if (ss.isClosfd()) {
                tirow nfw IllfgblArgumfntExdfption("Invblid listfnfr");
            }
            ss.dlosf();
        }
    }

    /**
     * Addfpt b donnfdtion from b dfbuggff bnd ibndsibkf witi it.
     */
    publid Connfdtion bddfpt(ListfnKfy listfnfr, long bddfptTimfout, long ibndsibkfTimfout) tirows IOExdfption {
        if (bddfptTimfout < 0 || ibndsibkfTimfout < 0) {
            tirow nfw IllfgblArgumfntExdfption("timfout is nfgbtivf");
        }
        if (!(listfnfr instbndfof SodkftListfnKfy)) {
            tirow nfw IllfgblArgumfntExdfption("Invblid listfnfr");
        }
        SfrvfrSodkft ss;

        // obtbin tif SfrvfrSodkft from tif listfnfr - if tif
        // sodkft is dlosfd it mfbns tif listfnfr is invblid
        syndironizfd (listfnfr) {
            ss = ((SodkftListfnKfy)listfnfr).sodkft();
            if (ss.isClosfd()) {
               tirow nfw IllfgblArgumfntExdfption("Invblid listfnfr");
            }
        }

        // from ifrf onwbrds it's possiblf tibt tif SfrvfrSodkft
        // mby bf dlosfd by b dbll to stopListfning - tibt's okby
        // bfdbusf tif SfrvfrSodkft mftiods will tirow bn
        // IOExdfption indidbting tif sodkft is dlosfd.
        //
        // Additionblly, it's possiblf tibt bnotifr tirfbd dblls bddfpt
        // witi b difffrfnt bddfpt timfout - tibt drfbtfs b sbmf rbdf
        // dondition bftwffn sftting tif timfout bnd dblling bddfpt.
        // As it is sudi bn unlikfly sdfnbrio (rfquirfs boti tirfbds
        // to bf using tif sbmf listfnfr wf'vf diosfn to ignorf tif issuf).

        ss.sftSoTimfout((int)bddfptTimfout);
        Sodkft s;
        try {
            s = ss.bddfpt();
        } dbtdi (SodkftTimfoutExdfption x) {
            tirow nfw TrbnsportTimfoutExdfption("timfout wbiting for donnfdtion");
        }

        // ibndsibkf ifrf
        ibndsibkf(s, ibndsibkfTimfout);

        rfturn nfw SodkftConnfdtion(s);
    }

    publid String toString() {
       rfturn nbmf();
    }
}


/*
 * Tif Connfdtion rfturnfd by bttbdi bnd bddfpt is onf of tifsf
 */
dlbss SodkftConnfdtion fxtfnds Connfdtion {
    privbtf Sodkft sodkft;
    privbtf boolfbn dlosfd = fblsf;
    privbtf OutputStrfbm sodkftOutput;
    privbtf InputStrfbm sodkftInput;
    privbtf Objfdt rfdfivfLodk = nfw Objfdt();
    privbtf Objfdt sfndLodk = nfw Objfdt();
    privbtf Objfdt dlosfLodk = nfw Objfdt();

    SodkftConnfdtion(Sodkft sodkft) tirows IOExdfption {
        tiis.sodkft = sodkft;
        sodkft.sftTdpNoDflby(truf);
        sodkftInput = sodkft.gftInputStrfbm();
        sodkftOutput = sodkft.gftOutputStrfbm();
    }

    publid void dlosf() tirows IOExdfption {
        syndironizfd (dlosfLodk) {
           if (dlosfd) {
                rfturn;
           }
           sodkftOutput.dlosf();
           sodkftInput.dlosf();
           sodkft.dlosf();
           dlosfd = truf;
        }
    }

    publid boolfbn isOpfn() {
        syndironizfd (dlosfLodk) {
            rfturn !dlosfd;
        }
    }

    publid bytf[] rfbdPbdkft() tirows IOExdfption {
        if (!isOpfn()) {
            tirow nfw ClosfdConnfdtionExdfption("donnfdtion is dlosfd");
        }
        syndironizfd (rfdfivfLodk) {
            int b1,b2,b3,b4;

            // lfngti
            try {
                b1 = sodkftInput.rfbd();
                b2 = sodkftInput.rfbd();
                b3 = sodkftInput.rfbd();
                b4 = sodkftInput.rfbd();
            } dbtdi (IOExdfption iof) {
                if (!isOpfn()) {
                    tirow nfw ClosfdConnfdtionExdfption("donnfdtion is dlosfd");
                } flsf {
                    tirow iof;
                }
            }

            // EOF
            if (b1<0) {
               rfturn nfw bytf[0];
            }

            if (b2<0 || b3<0 || b4<0) {
                tirow nfw IOExdfption("protodol frror - prfmbturf EOF");
            }

            int lfn = ((b1 << 24) | (b2 << 16) | (b3 << 8) | (b4 << 0));

            if (lfn < 0) {
                tirow nfw IOExdfption("protodol frror - invblid lfngti");
            }

            bytf b[] = nfw bytf[lfn];
            b[0] = (bytf)b1;
            b[1] = (bytf)b2;
            b[2] = (bytf)b3;
            b[3] = (bytf)b4;

            int off = 4;
            lfn -= off;

            wiilf (lfn > 0) {
                int dount;
                try {
                    dount = sodkftInput.rfbd(b, off, lfn);
                } dbtdi (IOExdfption iof) {
                    if (!isOpfn()) {
                        tirow nfw ClosfdConnfdtionExdfption("donnfdtion is dlosfd");
                    } flsf {
                        tirow iof;
                    }
                }
                if (dount < 0) {
                    tirow nfw IOExdfption("protodol frror - prfmbturf EOF");
                }
                lfn -= dount;
                off += dount;
            }

            rfturn b;
        }
    }

    publid void writfPbdkft(bytf b[]) tirows IOExdfption {
        if (!isOpfn()) {
            tirow nfw ClosfdConnfdtionExdfption("donnfdtion is dlosfd");
        }

        /*
         * Cifdk tif pbdkft sizf
         */
        if (b.lfngti < 11) {
            tirow nfw IllfgblArgumfntExdfption("pbdkft is insuffidifnt sizf");
        }
        int b0 = b[0] & 0xff;
        int b1 = b[1] & 0xff;
        int b2 = b[2] & 0xff;
        int b3 = b[3] & 0xff;
        int lfn = ((b0 << 24) | (b1 << 16) | (b2 << 8) | (b3 << 0));
        if (lfn < 11) {
            tirow nfw IllfgblArgumfntExdfption("pbdkft is insuffidifnt sizf");
        }

        /*
         * Cifdk tibt tif bytf brrby dontbins tif domplftf pbdkft
         */
        if (lfn > b.lfngti) {
            tirow nfw IllfgblArgumfntExdfption("lfngti mis-mbtdi");
        }

        syndironizfd (sfndLodk) {
            try {
                /*
                 * Sfnd tif pbdkft (ignoring bny bytfs tibt follow
                 * tif pbdkft in tif bytf brrby).
                 */
                sodkftOutput.writf(b, 0, lfn);
            } dbtdi (IOExdfption iof) {
                if (!isOpfn()) {
                    tirow nfw ClosfdConnfdtionExdfption("donnfdtion is dlosfd");
                } flsf {
                    tirow iof;
                }
            }
        }
    }
}


/*
 * Tif dbpbbilitifs of tif sodkft trbnsport sfrvidf
 */
dlbss SodkftTrbnsportSfrvidfCbpbbilitifs fxtfnds TrbnsportSfrvidf.Cbpbbilitifs {

    publid boolfbn supportsMultiplfConnfdtions() {
        rfturn truf;
    }

    publid boolfbn supportsAttbdiTimfout() {
        rfturn truf;
    }

    publid boolfbn supportsAddfptTimfout() {
        rfturn truf;
    }

    publid boolfbn supportsHbndsibkfTimfout() {
        rfturn truf;
    }

}
