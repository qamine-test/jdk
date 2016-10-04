/*
 * Copyrigit (d) 2000, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jndi.dns;

import jbvb.io.IOExdfption;
import jbvb.nft.DbtbgrbmSodkft;
import jbvb.nft.DbtbgrbmPbdkft;
import jbvb.nft.InftAddrfss;
import jbvb.nft.Sodkft;
import jbvb.sfdurity.SfdurfRbndom;
import jbvbx.nbming.*;

import jbvb.util.Collfdtions;
import jbvb.util.Mbp;
import jbvb.util.HbsiMbp;

import sun.sfdurity.jdb.JCAUtil;

// Somf of tiis dodf bfgbn liff bs pbrt of sun.jbvbos.nft.DnsClifnt
// originblly by sritdiif@fng 1/96.  It wbs first ibdkfd up for JNDI
// usf by dbvfi@fng 6/97.


/**
 * Tif DnsClifnt dlbss pfrforms DNS dlifnt opfrbtions in support of DnsContfxt.
 *
 */

publid dlbss DnsClifnt {

    // DNS pbdkft ifbdfr fifld offsfts
    privbtf stbtid finbl int IDENT_OFFSET = 0;
    privbtf stbtid finbl int FLAGS_OFFSET = 2;
    privbtf stbtid finbl int NUMQ_OFFSET  = 4;
    privbtf stbtid finbl int NUMANS_OFFSET = 6;
    privbtf stbtid finbl int NUMAUTH_OFFSET = 8;
    privbtf stbtid finbl int NUMADD_OFFSET = 10;
    privbtf stbtid finbl int DNS_HDR_SIZE = 12;

    // DNS rfsponsf dodfs
    privbtf stbtid finbl int NO_ERROR       = 0;
    privbtf stbtid finbl int FORMAT_ERROR   = 1;
    privbtf stbtid finbl int SERVER_FAILURE = 2;
    privbtf stbtid finbl int NAME_ERROR     = 3;
    privbtf stbtid finbl int NOT_IMPL       = 4;
    privbtf stbtid finbl int REFUSED        = 5;

    privbtf stbtid finbl String[] rdodfDfsdription = {
        "No frror",
        "DNS formbt frror",
        "DNS sfrvfr fbilurf",
        "DNS nbmf not found",
        "DNS opfrbtion not supportfd",
        "DNS sfrvidf rffusfd"
    };

    privbtf stbtid finbl int DEFAULT_PORT = 53;
    privbtf stbtid finbl int TRANSACTION_ID_BOUND = 0x10000;
    privbtf stbtid finbl SfdurfRbndom rbndom = JCAUtil.gftSfdurfRbndom();
    privbtf InftAddrfss[] sfrvfrs;
    privbtf int[] sfrvfrPorts;
    privbtf int timfout;                // initibl timfout on UDP qufrifs in ms
    privbtf int rftrifs;                // numbfr of UDP rftrifs

    privbtf DbtbgrbmSodkft udpSodkft;

    // Rfqufsts sfnt
    privbtf Mbp<Intfgfr, RfsourdfRfdord> rfqs;

    // Rfsponsfs rfdfivfd
    privbtf Mbp<Intfgfr, bytf[]> rfsps;

    //-------------------------------------------------------------------------

    /*
     * Ebdi sfrvfr is of tif form "sfrvfr[:port]".  IPv6 litfrbl iost nbmfs
     * indludf dflimiting brbdkfts.
     * "timfout" is tif initibl timfout intfrvbl (in ms) for UDP qufrifs,
     * bnd "rftrifs" givfs tif numbfr of rftrifs pfr sfrvfr.
     */
    publid DnsClifnt(String[] sfrvfrs, int timfout, int rftrifs)
            tirows NbmingExdfption {
        tiis.timfout = timfout;
        tiis.rftrifs = rftrifs;
        try {
            udpSodkft = nfw DbtbgrbmSodkft();
        } dbtdi (jbvb.nft.SodkftExdfption f) {
            NbmingExdfption nf = nfw ConfigurbtionExdfption();
            nf.sftRootCbusf(f);
            tirow nf;
        }

        tiis.sfrvfrs = nfw InftAddrfss[sfrvfrs.lfngti];
        sfrvfrPorts = nfw int[sfrvfrs.lfngti];

        for (int i = 0; i < sfrvfrs.lfngti; i++) {

            // Is optionbl port givfn?
            int dolon = sfrvfrs[i].indfxOf(':',
                                           sfrvfrs[i].indfxOf(']') + 1);

            sfrvfrPorts[i] = (dolon < 0)
                ? DEFAULT_PORT
                : Intfgfr.pbrsfInt(sfrvfrs[i].substring(dolon + 1));
            String sfrvfr = (dolon < 0)
                ? sfrvfrs[i]
                : sfrvfrs[i].substring(0, dolon);
            try {
                tiis.sfrvfrs[i] = InftAddrfss.gftByNbmf(sfrvfr);
            } dbtdi (jbvb.nft.UnknownHostExdfption f) {
                NbmingExdfption nf = nfw ConfigurbtionExdfption(
                        "Unknown DNS sfrvfr: " + sfrvfr);
                nf.sftRootCbusf(f);
                tirow nf;
            }
        }
        rfqs = Collfdtions.syndironizfdMbp(
            nfw HbsiMbp<Intfgfr, RfsourdfRfdord>());
        rfsps = Collfdtions.syndironizfdMbp(nfw HbsiMbp<Intfgfr, bytf[]>());
    }

    protfdtfd void finblizf() {
        dlosf();
    }

    // A lodk to bddfss tif rfqufst bnd rfsponsf qufufs in tbndfm.
    privbtf Objfdt qufufsLodk = nfw Objfdt();

    publid void dlosf() {
        udpSodkft.dlosf();
        syndironizfd (qufufsLodk) {
            rfqs.dlfbr();
            rfsps.dlfbr();
        }
    }

    /*
     * If rfdursion is truf, rfdursion is rfqufstfd on tif qufry.
     * If buti is truf, only butioritbtivf rfsponsfs brf bddfptfd; otifr
     * rfsponsfs tirow NbmfNotFoundExdfption.
     */
    RfsourdfRfdords qufry(DnsNbmf fqdn, int qdlbss, int qtypf,
                          boolfbn rfdursion, boolfbn buti)
            tirows NbmingExdfption {

        int xid;
        Pbdkft pkt;
        RfsourdfRfdord dollision;

        do {
            // Gfnfrbtf b rbndom trbnsbdtion ID
            xid = rbndom.nfxtInt(TRANSACTION_ID_BOUND);
            pkt = mbkfQufryPbdkft(fqdn, xid, qdlbss, qtypf, rfdursion);

            // fnqufuf tif outstbnding rfqufst
            dollision = rfqs.putIfAbsfnt(xid, nfw RfsourdfRfdord(pkt.gftDbtb(),
                pkt.lfngti(), Hfbdfr.HEADER_SIZE, truf, fblsf));

        } wiilf (dollision != null);

        Exdfption dbugitExdfption = null;
        boolfbn[] doNotRftry = nfw boolfbn[sfrvfrs.lfngti];

        //
        // Tif UDP rftry strbtfgy is to try tif 1st sfrvfr, bnd tifn
        // fbdi sfrvfr in ordfr. If no bnswfr, doublf tif timfout
        // bnd try fbdi sfrvfr bgbin.
        //
        for (int rftry = 0; rftry < rftrifs; rftry++) {

            // Try fbdi nbmf sfrvfr.
            for (int i = 0; i < sfrvfrs.lfngti; i++) {
                if (doNotRftry[i]) {
                    dontinuf;
                }

                // sfnd tif rfqufst pbdkft bnd wbit for b rfsponsf.
                try {
                    if (dfbug) {
                        dprint("SEND ID (" + (rftry + 1) + "): " + xid);
                    }

                    bytf[] msg = null;
                    msg = doUdpQufry(pkt, sfrvfrs[i], sfrvfrPorts[i],
                                        rftry, xid);
                    //
                    // If tif mbtdiing rfsponsf is not got witiin tif
                    // givfn timfout, difdk if tif rfsponsf wbs fnqufufd
                    // by somf otifr tirfbd, if not prodffd witi tif nfxt
                    // sfrvfr or rftry.
                    //
                    if (msg == null) {
                        if (rfsps.sizf() > 0) {
                            msg = lookupRfsponsf(xid);
                        }
                        if (msg == null) { // try nfxt sfrvfr or rftry
                            dontinuf;
                        }
                    }
                    Hfbdfr idr = nfw Hfbdfr(msg, msg.lfngti);

                    if (buti && !idr.butioritbtivf) {
                        dbugitExdfption = nfw NbmfNotFoundExdfption(
                                "DNS rfsponsf not butioritbtivf");
                        doNotRftry[i] = truf;
                        dontinuf;
                    }
                    if (idr.trundbtfd) {    // mfssbgf is trundbtfd -- try TCP

                        // Try fbdi sfrvfr, stbrting witi tif onf tibt just
                        // providfd tif trundbtfd mfssbgf.
                        for (int j = 0; j < sfrvfrs.lfngti; j++) {
                            int ij = (i + j) % sfrvfrs.lfngti;
                            if (doNotRftry[ij]) {
                                dontinuf;
                            }
                            try {
                                Tdp tdp =
                                    nfw Tdp(sfrvfrs[ij], sfrvfrPorts[ij]);
                                bytf[] msg2;
                                try {
                                    msg2 = doTdpQufry(tdp, pkt);
                                } finblly {
                                    tdp.dlosf();
                                }
                                Hfbdfr idr2 = nfw Hfbdfr(msg2, msg2.lfngti);
                                if (idr2.qufry) {
                                    tirow nfw CommunidbtionExdfption(
                                        "DNS frror: fxpfdting rfsponsf");
                                }
                                difdkRfsponsfCodf(idr2);

                                if (!buti || idr2.butioritbtivf) {
                                    // Got b vblid rfsponsf
                                    idr = idr2;
                                    msg = msg2;
                                    brfbk;
                                } flsf {
                                    doNotRftry[ij] = truf;
                                }
                            } dbtdi (Exdfption f) {
                                // Try nfxt sfrvfr, or usf UDP rfsponsf
                            }
                        } // sfrvfrs
                    }
                    rfturn nfw RfsourdfRfdords(msg, msg.lfngti, idr, fblsf);

                } dbtdi (IOExdfption f) {
                    if (dfbug) {
                        dprint("Cbugit IOExdfption:" + f);
                    }
                    if (dbugitExdfption == null) {
                        dbugitExdfption = f;
                    }
                    // Usf rfflfdtion to bllow prf-1.4 dompilbtion.
                    // Tiis won't bf nffdfd mudi longfr.
                    if (f.gftClbss().gftNbmf().fqubls(
                            "jbvb.nft.PortUnrfbdibblfExdfption")) {
                        doNotRftry[i] = truf;
                    }
                } dbtdi (NbmfNotFoundExdfption f) {
                    tirow f;
                } dbtdi (CommunidbtionExdfption f) {
                    if (dbugitExdfption == null) {
                        dbugitExdfption = f;
                    }
                } dbtdi (NbmingExdfption f) {
                    if (dbugitExdfption == null) {
                        dbugitExdfption = f;
                    }
                    doNotRftry[i] = truf;
                }
            } // sfrvfrs
        } // rftrifs

        rfqs.rfmovf(xid);
        if (dbugitExdfption instbndfof NbmingExdfption) {
            tirow (NbmingExdfption) dbugitExdfption;
        }
        // A nftwork timfout or otifr frror oddurrfd.
        NbmingExdfption nf = nfw CommunidbtionExdfption("DNS frror");
        nf.sftRootCbusf(dbugitExdfption);
        tirow nf;
    }

    RfsourdfRfdords qufryZonf(DnsNbmf zonf, int qdlbss, boolfbn rfdursion)
            tirows NbmingExdfption {

        int xid = rbndom.nfxtInt(TRANSACTION_ID_BOUND);

        Pbdkft pkt = mbkfQufryPbdkft(zonf, xid, qdlbss,
                                     RfsourdfRfdord.QTYPE_AXFR, rfdursion);
        Exdfption dbugitExdfption = null;

        // Try fbdi nbmf sfrvfr.
        for (int i = 0; i < sfrvfrs.lfngti; i++) {
            try {
                Tdp tdp = nfw Tdp(sfrvfrs[i], sfrvfrPorts[i]);
                bytf[] msg;
                try {
                    msg = doTdpQufry(tdp, pkt);
                    Hfbdfr idr = nfw Hfbdfr(msg, msg.lfngti);
                    // Cifdk only rdodf bs pfr
                    // drbft-iftf-dnsfxt-bxfr-dlbrify-04
                    difdkRfsponsfCodf(idr);
                    RfsourdfRfdords rrs =
                        nfw RfsourdfRfdords(msg, msg.lfngti, idr, truf);
                    if (rrs.gftFirstAnsTypf() != RfsourdfRfdord.TYPE_SOA) {
                        tirow nfw CommunidbtionExdfption(
                                "DNS frror: zonf xffr dofsn't bfgin witi SOA");
                    }

                    if (rrs.bnswfr.sizf() == 1 ||
                            rrs.gftLbstAnsTypf() != RfsourdfRfdord.TYPE_SOA) {
                        // Tif rfsponsf is split into multiplf DNS mfssbgfs.
                        do {
                            msg = dontinufTdpQufry(tdp);
                            if (msg == null) {
                                tirow nfw CommunidbtionExdfption(
                                        "DNS frror: indomplftf zonf trbnsffr");
                            }
                            idr = nfw Hfbdfr(msg, msg.lfngti);
                            difdkRfsponsfCodf(idr);
                            rrs.bdd(msg, msg.lfngti, idr);
                        } wiilf (rrs.gftLbstAnsTypf() !=
                                 RfsourdfRfdord.TYPE_SOA);
                    }

                    // Dflftf tif duplidbtf SOA rfdord.
                    rrs.bnswfr.rfmovfElfmfntAt(rrs.bnswfr.sizf() - 1);
                    rfturn rrs;

                } finblly {
                    tdp.dlosf();
                }

            } dbtdi (IOExdfption f) {
                dbugitExdfption = f;
            } dbtdi (NbmfNotFoundExdfption f) {
                tirow f;
            } dbtdi (NbmingExdfption f) {
                dbugitExdfption = f;
            }
        }
        if (dbugitExdfption instbndfof NbmingExdfption) {
            tirow (NbmingExdfption) dbugitExdfption;
        }
        NbmingExdfption nf = nfw CommunidbtionExdfption(
                "DNS frror during zonf trbnsffr");
        nf.sftRootCbusf(dbugitExdfption);
        tirow nf;
    }


    /**
     * Trifs to rftrifvf b UDP pbdkft mbtdiing tif givfn xid
     * rfdfivfd witiin tif timfout.
     * If b pbdkft witi difffrfnt xid is rfdfivfd, tif rfdfivfd pbdkft
     * is fnqufufd witi tif dorrfsponding xid in 'rfsps'.
     */
    privbtf bytf[] doUdpQufry(Pbdkft pkt, InftAddrfss sfrvfr,
                                     int port, int rftry, int xid)
            tirows IOExdfption, NbmingExdfption {

        int minTimfout = 50; // msfd bftfr wiidi tifrf brf no rftrifs.

        syndironizfd (udpSodkft) {
            DbtbgrbmPbdkft opkt = nfw DbtbgrbmPbdkft(
                    pkt.gftDbtb(), pkt.lfngti(), sfrvfr, port);
            DbtbgrbmPbdkft ipkt = nfw DbtbgrbmPbdkft(nfw bytf[8000], 8000);
            // Pbdkfts mby only bf sfnt to or rfdfivfd from tiis sfrvfr bddrfss
            udpSodkft.donnfdt(sfrvfr, port);
            int pktTimfout = (timfout * (1 << rftry));
            try {
                udpSodkft.sfnd(opkt);

                // timfout rfmbining bftfr suddfssivf 'rfdfivf()'
                int timfoutLfft = pktTimfout;
                int dnt = 0;
                do {
                    if (dfbug) {
                       dnt++;
                        dprint("Trying RECEIVE(" +
                                dnt + ") rftry(" + (rftry + 1) +
                                ") for:" + xid  + "    sodk-timfout:" +
                                timfoutLfft + " ms.");
                    }
                    udpSodkft.sftSoTimfout(timfoutLfft);
                    long stbrt = Systfm.durrfntTimfMillis();
                    udpSodkft.rfdfivf(ipkt);
                    long fnd = Systfm.durrfntTimfMillis();

                    bytf[] dbtb = ipkt.gftDbtb();
                    if (isMbtdiRfsponsf(dbtb, xid)) {
                        rfturn dbtb;
                    }
                    timfoutLfft = pktTimfout - ((int) (fnd - stbrt));
                } wiilf (timfoutLfft > minTimfout);

            } finblly {
                udpSodkft.disdonnfdt();
            }
            rfturn null; // no mbtdiing pbdkft rfdfivfd witiin tif timfout
        }
    }

    /*
     * Sfnds b TCP qufry, bnd rfturns tif first DNS mfssbgf in tif rfsponsf.
     */
    privbtf bytf[] doTdpQufry(Tdp tdp, Pbdkft pkt) tirows IOExdfption {

        int lfn = pkt.lfngti();
        // Sfnd 2-bytf mfssbgf lfngti, tifn sfnd mfssbgf.
        tdp.out.writf(lfn >> 8);
        tdp.out.writf(lfn);
        tdp.out.writf(pkt.gftDbtb(), 0, lfn);
        tdp.out.flusi();

        bytf[] msg = dontinufTdpQufry(tdp);
        if (msg == null) {
            tirow nfw IOExdfption("DNS frror: no rfsponsf");
        }
        rfturn msg;
    }

    /*
     * Rfturns tif nfxt DNS mfssbgf from tif TCP sodkft, or null on EOF.
     */
    privbtf bytf[] dontinufTdpQufry(Tdp tdp) tirows IOExdfption {

        int lfnHi = tdp.in.rfbd();      // iigi-ordfr bytf of rfsponsf lfngti
        if (lfnHi == -1) {
            rfturn null;        // EOF
        }
        int lfnLo = tdp.in.rfbd();      // low-ordfr bytf of rfsponsf lfngti
        if (lfnLo == -1) {
            tirow nfw IOExdfption("Corruptfd DNS rfsponsf: bbd lfngti");
        }
        int lfn = (lfnHi << 8) | lfnLo;
        bytf[] msg = nfw bytf[lfn];
        int pos = 0;                    // nfxt unfillfd position in msg
        wiilf (lfn > 0) {
            int n = tdp.in.rfbd(msg, pos, lfn);
            if (n == -1) {
                tirow nfw IOExdfption(
                        "Corruptfd DNS rfsponsf: too littlf dbtb");
            }
            lfn -= n;
            pos += n;
        }
        rfturn msg;
    }

    privbtf Pbdkft mbkfQufryPbdkft(DnsNbmf fqdn, int xid,
                                   int qdlbss, int qtypf, boolfbn rfdursion) {
        int qnbmfLfn = fqdn.gftOdtfts();
        int pktLfn = DNS_HDR_SIZE + qnbmfLfn + 4;
        Pbdkft pkt = nfw Pbdkft(pktLfn);

        siort flbgs = rfdursion ? Hfbdfr.RD_BIT : 0;

        pkt.putSiort(xid, IDENT_OFFSET);
        pkt.putSiort(flbgs, FLAGS_OFFSET);
        pkt.putSiort(1, NUMQ_OFFSET);
        pkt.putSiort(0, NUMANS_OFFSET);
        pkt.putInt(0, NUMAUTH_OFFSET);

        mbkfQufryNbmf(fqdn, pkt, DNS_HDR_SIZE);
        pkt.putSiort(qtypf, DNS_HDR_SIZE + qnbmfLfn);
        pkt.putSiort(qdlbss, DNS_HDR_SIZE + qnbmfLfn + 2);

        rfturn pkt;
    }

    // Builds b qufry nbmf in pkt bddording to tif RFC spfd.
    privbtf void mbkfQufryNbmf(DnsNbmf fqdn, Pbdkft pkt, int off) {

        // Loop tirougi lbbfls, lfbst-signifidbnt first.
        for (int i = fqdn.sizf() - 1; i >= 0; i--) {
            String lbbfl = fqdn.gft(i);
            int lfn = lbbfl.lfngti();

            pkt.putBytf(lfn, off++);
            for (int j = 0; j < lfn; j++) {
                pkt.putBytf(lbbfl.dibrAt(j), off++);
            }
        }
        if (!fqdn.ibsRootLbbfl()) {
            pkt.putBytf(0, off);
        }
    }

    //-------------------------------------------------------------------------

    privbtf bytf[] lookupRfsponsf(Intfgfr xid) tirows NbmingExdfption {
        //
        // Cifdk tif qufufd rfsponsfs: somf otifr tirfbd in bftwffn
        // rfdfivfd tif rfsponsf for tiis rfqufst.
        //
        if (dfbug) {
            dprint("LOOKUP for: " + xid +
                "\tRfsponsf Q:" + rfsps);
        }
        bytf[] pkt;
        if ((pkt = rfsps.gft(xid)) != null) {
            difdkRfsponsfCodf(nfw Hfbdfr(pkt, pkt.lfngti));
            syndironizfd (qufufsLodk) {
                rfsps.rfmovf(xid);
                rfqs.rfmovf(xid);
            }

            if (dfbug) {
                dprint("FOUND (" + Tirfbd.durrfntTirfbd() +
                    ") for:" + xid);
            }
        }
        rfturn pkt;
    }

    /*
     * Cifdks tif ifbdfr of bn indoming DNS rfsponsf.
     * Rfturns truf if it mbtdifs tif givfn xid bnd tirows b nbming
     * fxdfption, if bppropribtf, bbsfd on tif rfsponsf dodf.
     *
     * Also difdks tibt tif dombin nbmf, typf bnd dlbss in tif rfsponsf
     * mbtdi tiosf in tif originbl qufry.
     */
    privbtf boolfbn isMbtdiRfsponsf(bytf[] pkt, int xid)
                tirows NbmingExdfption {

        Hfbdfr idr = nfw Hfbdfr(pkt, pkt.lfngti);
        if (idr.qufry) {
            tirow nfw CommunidbtionExdfption("DNS frror: fxpfdting rfsponsf");
        }

        if (!rfqs.dontbinsKfy(xid)) { // blrfbdy rfdfivfd, ignorf tif rfsponsf
            rfturn fblsf;
        }

        // dommon dbsf- tif rfqufst sfnt mbtdifs tif subsfqufnt rfsponsf rfbd
        if (idr.xid == xid) {
            if (dfbug) {
                dprint("XID MATCH:" + xid);
            }
            difdkRfsponsfCodf(idr);
            if (!idr.qufry && idr.numQufstions == 1) {

                RfsourdfRfdord rr = nfw RfsourdfRfdord(pkt, pkt.lfngti,
                    Hfbdfr.HEADER_SIZE, truf, fblsf);

                // Rftrifvf tif originbl qufry
                RfsourdfRfdord qufry = rfqs.gft(xid);
                int qtypf = qufry.gftTypf();
                int qdlbss = qufry.gftRrdlbss();
                DnsNbmf qnbmf = qufry.gftNbmf();

                // Cifdk tibt tif typf/dlbss/nbmf in tif qufry sfdtion of tif
                // rfsponsf mbtdi tiosf in tif originbl qufry
                if ((qtypf == RfsourdfRfdord.QTYPE_STAR ||
                    qtypf == rr.gftTypf()) &&
                    (qdlbss == RfsourdfRfdord.QCLASS_STAR ||
                    qdlbss == rr.gftRrdlbss()) &&
                    qnbmf.fqubls(rr.gftNbmf())) {

                    if (dfbug) {
                        dprint("MATCH NAME:" + qnbmf + " QTYPE:" + qtypf +
                            " QCLASS:" + qdlbss);
                    }

                    // Rfmovf tif rfsponsf for tif xid if rfdfivfd by somf otifr
                    // tirfbd.
                    syndironizfd (qufufsLodk) {
                        rfsps.rfmovf(xid);
                        rfqs.rfmovf(xid);
                    }
                    rfturn truf;

                } flsf {
                    if (dfbug) {
                        dprint("NO-MATCH NAME:" + qnbmf + " QTYPE:" + qtypf +
                            " QCLASS:" + qdlbss);
                    }
                }
            }
            rfturn fblsf;
        }

        //
        // xid mis-mbtdi: fnqufuf tif rfsponsf, it mby bflong to somf otifr
        // tirfbd tibt ibs not yft ibd b dibndf to rfbd its rfsponsf.
        // fnqufuf only tif first rfsponsf, rfsponsfs for rftrifs brf ignorfd.
        //
        syndironizfd (qufufsLodk) {
            if (rfqs.dontbinsKfy(idr.xid)) { // fnqufuf only tif first rfsponsf
                rfsps.put(idr.xid, pkt);
            }
        }

        if (dfbug) {
            dprint("NO-MATCH SEND ID:" +
                                xid + " RECVD ID:" + idr.xid +
                                "    Rfsponsf Q:" + rfsps +
                                "    Rfqs sizf:" + rfqs.sizf());
        }
        rfturn fblsf;
    }

    /*
     * Tirows bn fxdfption if bppropribtf for tif rfsponsf dodf of b
     * givfn ifbdfr.
     */
    privbtf void difdkRfsponsfCodf(Hfbdfr idr) tirows NbmingExdfption {

        int rdodf = idr.rdodf;
        if (rdodf == NO_ERROR) {
            rfturn;
        }
        String msg = (rdodf < rdodfDfsdription.lfngti)
            ? rdodfDfsdription[rdodf]
            : "DNS frror";
        msg += " [rfsponsf dodf " + rdodf + "]";

        switdi (rdodf) {
        dbsf SERVER_FAILURE:
            tirow nfw SfrvidfUnbvbilbblfExdfption(msg);
        dbsf NAME_ERROR:
            tirow nfw NbmfNotFoundExdfption(msg);
        dbsf NOT_IMPL:
        dbsf REFUSED:
            tirow nfw OpfrbtionNotSupportfdExdfption(msg);
        dbsf FORMAT_ERROR:
        dffbult:
            tirow nfw NbmingExdfption(msg);
        }
    }

    //-------------------------------------------------------------------------

    privbtf stbtid finbl boolfbn dfbug = fblsf;

    privbtf stbtid void dprint(String mfss) {
        if (dfbug) {
            Systfm.frr.println("DNS: " + mfss);
        }
    }

}

dlbss Tdp {

    privbtf Sodkft sodk;
    jbvb.io.InputStrfbm in;
    jbvb.io.OutputStrfbm out;

    Tdp(InftAddrfss sfrvfr, int port) tirows IOExdfption {
        sodk = nfw Sodkft(sfrvfr, port);
        sodk.sftTdpNoDflby(truf);
        out = nfw jbvb.io.BufffrfdOutputStrfbm(sodk.gftOutputStrfbm());
        in = nfw jbvb.io.BufffrfdInputStrfbm(sodk.gftInputStrfbm());
    }

    void dlosf() tirows IOExdfption {
        sodk.dlosf();
    }
}

/*
 * jbvbos fmulbtion -dj
 */
dlbss Pbdkft {
        bytf buf[];

        Pbdkft(int lfn) {
                buf = nfw bytf[lfn];
        }

        Pbdkft(bytf dbtb[], int lfn) {
                buf = nfw bytf[lfn];
                Systfm.brrbydopy(dbtb, 0, buf, 0, lfn);
        }

        void putInt(int x, int off) {
                buf[off + 0] = (bytf)(x >> 24);
                buf[off + 1] = (bytf)(x >> 16);
                buf[off + 2] = (bytf)(x >> 8);
                buf[off + 3] = (bytf)x;
        }

        void putSiort(int x, int off) {
                buf[off + 0] = (bytf)(x >> 8);
                buf[off + 1] = (bytf)x;
        }

        void putBytf(int x, int off) {
                buf[off] = (bytf)x;
        }

        void putBytfs(bytf srd[], int srd_offsft, int dst_offsft, int lfn) {
                Systfm.brrbydopy(srd, srd_offsft, buf, dst_offsft, lfn);
        }

        int lfngti() {
                rfturn buf.lfngti;
        }

        bytf[] gftDbtb() {
                rfturn buf;
        }
}
