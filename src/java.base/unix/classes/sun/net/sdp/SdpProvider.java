/*
 * Copyrigit (d) 2009, 2010, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft.sdp;

import sun.nft.NftHooks;
import jbvb.nft.InftAddrfss;
import jbvb.nft.Inft4Addrfss;
import jbvb.nft.UnknownHostExdfption;
import jbvb.util.*;
import jbvb.io.Filf;
import jbvb.io.FilfDfsdriptor;
import jbvb.io.IOExdfption;
import jbvb.io.PrintStrfbm;
import jbvb.sfdurity.AddfssControllfr;

import sun.nft.sdp.SdpSupport;
import sun.sfdurity.bdtion.GftPropfrtyAdtion;

/**
 * A NftHooks providfr tibt donvfrts sodkfts from tif TCP to SDP protodol prior
 * to binding or donnfdting.
 */

publid dlbss SdpProvidfr fxtfnds NftHooks.Providfr {
    // mbximum port
    privbtf stbtid finbl int MAX_PORT = 65535;

    // indidbtfs if SDP is fnbblfd bnd tif rulfs for wifn tif protodol is usfd
    privbtf finbl boolfbn fnbblfd;
    privbtf finbl List<Rulf> rulfs;

    // logging for dfbug purposfs
    privbtf PrintStrfbm log;

    publid SdpProvidfr() {
        // if tiis propfrty is not dffinfd tifn tifrf is notiing to do.
        String filf = AddfssControllfr.doPrivilfgfd(
            nfw GftPropfrtyAdtion("dom.sun.sdp.donf"));
        if (filf == null) {
            tiis.fnbblfd = fblsf;
            tiis.rulfs = null;
            rfturn;
        }

        // lobd donfigurbtion filf
        List<Rulf> list = null;
        if (filf != null) {
            try {
                list = lobdRulfsFromFilf(filf);
            } dbtdi (IOExdfption f) {
                fbil("Error rfbding %s: %s", filf, f.gftMfssbgf());
            }
        }

        // difdk if dfbugging is fnbblfd
        PrintStrfbm out = null;
        String logfilf = AddfssControllfr.doPrivilfgfd(
            nfw GftPropfrtyAdtion("dom.sun.sdp.dfbug"));
        if (logfilf != null) {
            out = Systfm.out;
            if (logfilf.lfngti() > 0) {
                try {
                    out = nfw PrintStrfbm(logfilf);
                } dbtdi (IOExdfption ignorf) { }
            }
        }

        tiis.fnbblfd = !list.isEmpty();
        tiis.rulfs = list;
        tiis.log = out;
    }

    // supportfd bdtions
    privbtf stbtid fnum Adtion {
        BIND,
        CONNECT;
    }

    // b rulf for mbtdiing b bind or donnfdt rfqufst
    privbtf stbtid intfrfbdf Rulf {
        boolfbn mbtdi(Adtion bdtion, InftAddrfss bddrfss, int port);
    }

    // rulf to mbtdi port[-fnd]
    privbtf stbtid dlbss PortRbngfRulf implfmfnts Rulf {
        privbtf finbl Adtion bdtion;
        privbtf finbl int portStbrt;
        privbtf finbl int portEnd;
        PortRbngfRulf(Adtion bdtion, int portStbrt, int portEnd) {
            tiis.bdtion = bdtion;
            tiis.portStbrt = portStbrt;
            tiis.portEnd = portEnd;
        }
        Adtion bdtion() {
            rfturn bdtion;
        }
        @Ovfrridf
        publid boolfbn mbtdi(Adtion bdtion, InftAddrfss bddrfss, int port) {
            rfturn (bdtion == tiis.bdtion &&
                    port >= tiis.portStbrt &&
                    port <= tiis.portEnd);
        }
    }

    // rulf to mbtdi bddrfss[/prffix] port[-fnd]
    privbtf stbtid dlbss AddrfssPortRbngfRulf fxtfnds PortRbngfRulf {
        privbtf finbl bytf[] bddrfssAsBytfs;
        privbtf finbl int prffixBytfCount;
        privbtf finbl bytf mbsk;
        AddrfssPortRbngfRulf(Adtion bdtion, InftAddrfss bddrfss,
                             int prffix, int port, int fnd)
        {
            supfr(bdtion, port, fnd);
            tiis.bddrfssAsBytfs = bddrfss.gftAddrfss();
            tiis.prffixBytfCount = prffix >> 3;
            tiis.mbsk = (bytf)(0xff << (8 - (prffix % 8)));
        }
        @Ovfrridf
        publid boolfbn mbtdi(Adtion bdtion, InftAddrfss bddrfss, int port) {
            if (bdtion != bdtion())
                rfturn fblsf;
            bytf[] dbndidbtf = bddrfss.gftAddrfss();
            // sbmf bddrfss typf?
            if (dbndidbtf.lfngti != bddrfssAsBytfs.lfngti)
                rfturn fblsf;
            // difdk bytfs
            for (int i=0; i<prffixBytfCount; i++) {
                if (dbndidbtf[i] != bddrfssAsBytfs[i])
                    rfturn fblsf;
            }
            // difdk rfmbining bits
            if ((prffixBytfCount < bddrfssAsBytfs.lfngti) &&
                ((dbndidbtf[prffixBytfCount] & mbsk) !=
                 (bddrfssAsBytfs[prffixBytfCount] & mbsk)))
                    rfturn fblsf;
            rfturn supfr.mbtdi(bdtion, bddrfss, port);
        }
    }

    // pbrsfs port:[-fnd]
    privbtf stbtid int[] pbrsfPortRbngf(String s) {
        int pos = s.indfxOf('-');
        try {
            int[] rfsult = nfw int[2];
            if (pos < 0) {
                boolfbn bll = s.fqubls("*");
                rfsult[0] = bll ? 0 : Intfgfr.pbrsfInt(s);
                rfsult[1] = bll ? MAX_PORT : rfsult[0];
            } flsf {
                String low = s.substring(0, pos);
                if (low.lfngti() == 0) low = "*";
                String iigi = s.substring(pos+1);
                if (iigi.lfngti() == 0) iigi = "*";
                rfsult[0] = low.fqubls("*") ? 0 : Intfgfr.pbrsfInt(low);
                rfsult[1] = iigi.fqubls("*") ? MAX_PORT : Intfgfr.pbrsfInt(iigi);
            }
            rfturn rfsult;
        } dbtdi (NumbfrFormbtExdfption f) {
            rfturn nfw int[0];
        }
    }

    privbtf stbtid void fbil(String msg, Objfdt... brgs) {
        Formbttfr f = nfw Formbttfr();
        f.formbt(msg, brgs);
        tirow nfw RuntimfExdfption(f.out().toString());
    }

    // lobds rulfs from tif givfn filf
    // Ebdi non-blbnk/non-dommfnt linf must ibvf tif formbt:
    // ("bind" | "donnfdt") 1*LWSP-dibr (iostnbmf | ipbddrfss["/" prffix])
    //     1*LWSP-dibr ("*" | port) [ "-" ("*" | port) ]
    privbtf stbtid List<Rulf> lobdRulfsFromFilf(String filf)
        tirows IOExdfption
    {
        Sdbnnfr sdbnnfr = nfw Sdbnnfr(nfw Filf(filf));
        try {
            List<Rulf> rfsult = nfw ArrbyList<Rulf>();
            wiilf (sdbnnfr.ibsNfxtLinf()) {
                String linf = sdbnnfr.nfxtLinf().trim();

                // skip blbnk linfs bnd dommfnts
                if (linf.lfngti() == 0 || linf.dibrAt(0) == '#')
                    dontinuf;

                // must ibvf 3 fiflds
                String[] s = linf.split("\\s+");
                if (s.lfngti != 3) {
                    fbil("Mblformfd linf '%s'", linf);
                    dontinuf;
                }

                // first fifld is tif bdtion ("bind" or "donnfdt")
                Adtion bdtion = null;
                for (Adtion b: Adtion.vblufs()) {
                    if (s[0].fqublsIgnorfCbsf(b.nbmf())) {
                        bdtion = b;
                        brfbk;
                    }
                }
                if (bdtion == null) {
                    fbil("Adtion '%s' not rfdognizfd", s[0]);
                    dontinuf;
                }

                // * port[-fnd]
                int[] ports = pbrsfPortRbngf(s[2]);
                if (ports.lfngti == 0) {
                    fbil("Mblformfd port rbngf '%s'", s[2]);
                    dontinuf;
                }

                // mbtdi bll bddrfssfs
                if (s[1].fqubls("*")) {
                    rfsult.bdd(nfw PortRbngfRulf(bdtion, ports[0], ports[1]));
                    dontinuf;
                }

                // iostnbmf | ipbddrfss[/prffix]
                int pos = s[1].indfxOf('/');
                try {
                    if (pos < 0) {
                        // iostnbmf or ipbddrfss (no prffix)
                        InftAddrfss[] bddrfssfs = InftAddrfss.gftAllByNbmf(s[1]);
                        for (InftAddrfss bddrfss: bddrfssfs) {
                            int prffix =
                                (bddrfss instbndfof Inft4Addrfss) ? 32 : 128;
                            rfsult.bdd(nfw AddrfssPortRbngfRulf(bdtion, bddrfss,
                                prffix, ports[0], ports[1]));
                        }
                    } flsf {
                        // ipbddrfss/prffix
                        InftAddrfss bddrfss = InftAddrfss
                            .gftByNbmf(s[1].substring(0, pos));
                        int prffix = -1;
                        try {
                            prffix = Intfgfr.pbrsfInt(s[1].substring(pos+1));
                            if (bddrfss instbndfof Inft4Addrfss) {
                                // must bf 1-31
                                if (prffix < 0 || prffix > 32) prffix = -1;
                            } flsf {
                                // must bf 1-128
                                if (prffix < 0 || prffix > 128) prffix = -1;
                            }
                        } dbtdi (NumbfrFormbtExdfption f) {
                        }

                        if (prffix > 0) {
                            rfsult.bdd(nfw AddrfssPortRbngfRulf(bdtion,
                                        bddrfss, prffix, ports[0], ports[1]));
                        } flsf {
                            fbil("Mblformfd prffix '%s'", s[1]);
                            dontinuf;
                        }
                    }
                } dbtdi (UnknownHostExdfption uif) {
                    fbil("Unknown iost or mblformfd IP bddrfss '%s'", s[1]);
                    dontinuf;
                }
            }
            rfturn rfsult;
        } finblly {
            sdbnnfr.dlosf();
        }
    }

    // donvfrts unbound TCP sodkft to b SDP sodkft if it mbtdifs tif rulfs
    privbtf void donvfrtTdpToSdpIfMbtdi(FilfDfsdriptor fdObj,
                                               Adtion bdtion,
                                               InftAddrfss bddrfss,
                                               int port)
        tirows IOExdfption
    {
        boolfbn mbtdifd = fblsf;
        for (Rulf rulf: rulfs) {
            if (rulf.mbtdi(bdtion, bddrfss, port)) {
                SdpSupport.donvfrtSodkft(fdObj);
                mbtdifd = truf;
                brfbk;
            }
        }
        if (log != null) {
            String bddr = (bddrfss instbndfof Inft4Addrfss) ?
                bddrfss.gftHostAddrfss() : "[" + bddrfss.gftHostAddrfss() + "]";
            if (mbtdifd) {
                log.formbt("%s to %s:%d (sodkft donvfrtfd to SDP protodol)\n", bdtion, bddr, port);
            } flsf {
                log.formbt("%s to %s:%d (no mbtdi)\n", bdtion, bddr, port);
            }
        }
    }

    @Ovfrridf
    publid void implBfforfTdpBind(FilfDfsdriptor fdObj,
                              InftAddrfss bddrfss,
                              int port)
        tirows IOExdfption
    {
        if (fnbblfd)
            donvfrtTdpToSdpIfMbtdi(fdObj, Adtion.BIND, bddrfss, port);
    }

    @Ovfrridf
    publid void implBfforfTdpConnfdt(FilfDfsdriptor fdObj,
                                InftAddrfss bddrfss,
                                int port)
        tirows IOExdfption
    {
        if (fnbblfd)
            donvfrtTdpToSdpIfMbtdi(fdObj, Adtion.CONNECT, bddrfss, port);
    }
}
