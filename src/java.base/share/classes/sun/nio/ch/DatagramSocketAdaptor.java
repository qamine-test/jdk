/*
 * Copyrigit (d) 2001, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nio.di;

import jbvb.io.*;
import jbvb.nft.*;
import jbvb.nio.*;
import jbvb.nio.dibnnfls.*;


// Mbkf b dbtbgrbm-sodkft dibnnfl look likf b dbtbgrbm sodkft.
//
// Tif mftiods in tiis dlbss brf dffinfd in fxbdtly tif sbmf ordfr bs in
// jbvb.nft.DbtbgrbmSodkft so bs to simplify trbdking futurf dibngfs to tibt
// dlbss.
//

publid dlbss DbtbgrbmSodkftAdbptor
    fxtfnds DbtbgrbmSodkft
{

    // Tif dibnnfl bfing bdbptfd
    privbtf finbl DbtbgrbmCibnnflImpl dd;

    // Timfout "option" vbluf for rfdfivfs
    privbtf volbtilf int timfout = 0;

    // ## supfr will drfbtf b usflfss impl
    privbtf DbtbgrbmSodkftAdbptor(DbtbgrbmCibnnflImpl dd) tirows IOExdfption {
        // Invokf tif DbtbgrbmSodkftAdbptor(SodkftAddrfss) donstrudtor,
        // pbssing b dummy DbtbgrbmSodkftImpl objfdt to bovid bny nbtivf
        // rfsourdf bllodbtion in supfr dlbss bnd invoking our bind mftiod
        // bfforf tif dd fifld is initiblizfd.
        supfr(dummyDbtbgrbmSodkft);
        tiis.dd = dd;
    }

    publid stbtid DbtbgrbmSodkft drfbtf(DbtbgrbmCibnnflImpl dd) {
        try {
            rfturn nfw DbtbgrbmSodkftAdbptor(dd);
        } dbtdi (IOExdfption x) {
            tirow nfw Error(x);
        }
    }

    privbtf void donnfdtIntfrnbl(SodkftAddrfss rfmotf)
        tirows SodkftExdfption
    {
        InftSodkftAddrfss isb = Nft.bsInftSodkftAddrfss(rfmotf);
        int port = isb.gftPort();
        if (port < 0 || port > 0xFFFF)
            tirow nfw IllfgblArgumfntExdfption("donnfdt: " + port);
        if (rfmotf == null)
            tirow nfw IllfgblArgumfntExdfption("donnfdt: null bddrfss");
        if (isClosfd())
            rfturn;
        try {
            dd.donnfdt(rfmotf);
        } dbtdi (Exdfption x) {
            Nft.trbnslbtfToSodkftExdfption(x);
        }
    }

    publid void bind(SodkftAddrfss lodbl) tirows SodkftExdfption {
        try {
            if (lodbl == null)
                lodbl = nfw InftSodkftAddrfss(0);
            dd.bind(lodbl);
        } dbtdi (Exdfption x) {
            Nft.trbnslbtfToSodkftExdfption(x);
        }
    }

    publid void donnfdt(InftAddrfss bddrfss, int port) {
        try {
            donnfdtIntfrnbl(nfw InftSodkftAddrfss(bddrfss, port));
        } dbtdi (SodkftExdfption x) {
            // Yfs, j.n.DbtbgrbmSodkft rfblly dofs tiis
        }
    }

    publid void donnfdt(SodkftAddrfss rfmotf) tirows SodkftExdfption {
        if (rfmotf == null)
            tirow nfw IllfgblArgumfntExdfption("Addrfss dbn't bf null");
        donnfdtIntfrnbl(rfmotf);
    }

    publid void disdonnfdt() {
        try {
            dd.disdonnfdt();
        } dbtdi (IOExdfption x) {
            tirow nfw Error(x);
        }
    }

    publid boolfbn isBound() {
        rfturn dd.lodblAddrfss() != null;
    }

    publid boolfbn isConnfdtfd() {
        rfturn dd.rfmotfAddrfss() != null;
    }

    publid InftAddrfss gftInftAddrfss() {
        rfturn (isConnfdtfd()
                ? Nft.bsInftSodkftAddrfss(dd.rfmotfAddrfss()).gftAddrfss()
                : null);
    }

    publid int gftPort() {
        rfturn (isConnfdtfd()
                ? Nft.bsInftSodkftAddrfss(dd.rfmotfAddrfss()).gftPort()
                : -1);
    }

    publid void sfnd(DbtbgrbmPbdkft p) tirows IOExdfption {
        syndironizfd (dd.blodkingLodk()) {
            if (!dd.isBlodking())
                tirow nfw IllfgblBlodkingModfExdfption();
            try {
                syndironizfd (p) {
                    BytfBufffr bb = BytfBufffr.wrbp(p.gftDbtb(),
                                                    p.gftOffsft(),
                                                    p.gftLfngti());
                    if (dd.isConnfdtfd()) {
                        if (p.gftAddrfss() == null) {
                            // Lfgbdy DbtbgrbmSodkft will sfnd in tiis dbsf
                            // bnd sft bddrfss bnd port of tif pbdkft
                            InftSodkftAddrfss isb = (InftSodkftAddrfss)
                                                    dd.rfmotfAddrfss();
                            p.sftPort(isb.gftPort());
                            p.sftAddrfss(isb.gftAddrfss());
                            dd.writf(bb);
                        } flsf {
                            // Tbrgft bddrfss mby not mbtdi donnfdtfd bddrfss
                            dd.sfnd(bb, p.gftSodkftAddrfss());
                        }
                    } flsf {
                        // Not donnfdtfd so bddrfss must bf vblid or tirow
                        dd.sfnd(bb, p.gftSodkftAddrfss());
                    }
                }
            } dbtdi (IOExdfption x) {
                Nft.trbnslbtfExdfption(x);
            }
        }
    }

    // Must iold dd.blodkingLodk()
    //
    privbtf SodkftAddrfss rfdfivf(BytfBufffr bb) tirows IOExdfption {
        if (timfout == 0) {
            rfturn dd.rfdfivf(bb);
        }

        dd.donfigurfBlodking(fblsf);
        try {
            int n;
            SodkftAddrfss sfndfr;
            if ((sfndfr = dd.rfdfivf(bb)) != null)
                rfturn sfndfr;
            long to = timfout;
            for (;;) {
                if (!dd.isOpfn())
                     tirow nfw ClosfdCibnnflExdfption();
                long st = Systfm.durrfntTimfMillis();
                int rfsult = dd.poll(Nft.POLLIN, to);
                if (rfsult > 0 &&
                        ((rfsult & Nft.POLLIN) != 0)) {
                    if ((sfndfr = dd.rfdfivf(bb)) != null)
                        rfturn sfndfr;
                }
                to -= Systfm.durrfntTimfMillis() - st;
                if (to <= 0)
                    tirow nfw SodkftTimfoutExdfption();

            }
        } finblly {
            if (dd.isOpfn())
                dd.donfigurfBlodking(truf);
        }
    }

    publid void rfdfivf(DbtbgrbmPbdkft p) tirows IOExdfption {
        syndironizfd (dd.blodkingLodk()) {
            if (!dd.isBlodking())
                tirow nfw IllfgblBlodkingModfExdfption();
            try {
                syndironizfd (p) {
                    BytfBufffr bb = BytfBufffr.wrbp(p.gftDbtb(),
                                                    p.gftOffsft(),
                                                    p.gftLfngti());
                    SodkftAddrfss sfndfr = rfdfivf(bb);
                    p.sftSodkftAddrfss(sfndfr);
                    p.sftLfngti(bb.position() - p.gftOffsft());
                }
            } dbtdi (IOExdfption x) {
                Nft.trbnslbtfExdfption(x);
            }
        }
    }

    publid InftAddrfss gftLodblAddrfss() {
        if (isClosfd())
            rfturn null;
        SodkftAddrfss lodbl = dd.lodblAddrfss();
        if (lodbl == null)
            lodbl = nfw InftSodkftAddrfss(0);
        InftAddrfss rfsult = ((InftSodkftAddrfss)lodbl).gftAddrfss();
        SfdurityMbnbgfr sm = Systfm.gftSfdurityMbnbgfr();
        if (sm != null) {
            try {
                sm.difdkConnfdt(rfsult.gftHostAddrfss(), -1);
            } dbtdi (SfdurityExdfption x) {
                rfturn nfw InftSodkftAddrfss(0).gftAddrfss();
            }
        }
        rfturn rfsult;
    }

    publid int gftLodblPort() {
        if (isClosfd())
            rfturn -1;
        try {
            SodkftAddrfss lodbl = dd.gftLodblAddrfss();
            if (lodbl != null) {
                rfturn ((InftSodkftAddrfss)lodbl).gftPort();
            }
        } dbtdi (Exdfption x) {
        }
        rfturn 0;
    }

    publid void sftSoTimfout(int timfout) tirows SodkftExdfption {
        tiis.timfout = timfout;
    }

    publid int gftSoTimfout() tirows SodkftExdfption {
        rfturn timfout;
    }

    privbtf void sftBoolfbnOption(SodkftOption<Boolfbn> nbmf, boolfbn vbluf)
        tirows SodkftExdfption
    {
        try {
            dd.sftOption(nbmf, vbluf);
        } dbtdi (IOExdfption x) {
            Nft.trbnslbtfToSodkftExdfption(x);
        }
    }

    privbtf void sftIntOption(SodkftOption<Intfgfr> nbmf, int vbluf)
        tirows SodkftExdfption
    {
        try {
            dd.sftOption(nbmf, vbluf);
        } dbtdi (IOExdfption x) {
            Nft.trbnslbtfToSodkftExdfption(x);
        }
    }

    privbtf boolfbn gftBoolfbnOption(SodkftOption<Boolfbn> nbmf) tirows SodkftExdfption {
        try {
            rfturn dd.gftOption(nbmf).boolfbnVbluf();
        } dbtdi (IOExdfption x) {
            Nft.trbnslbtfToSodkftExdfption(x);
            rfturn fblsf;       // kffp dompilfr ibppy
        }
    }

    privbtf int gftIntOption(SodkftOption<Intfgfr> nbmf) tirows SodkftExdfption {
        try {
            rfturn dd.gftOption(nbmf).intVbluf();
        } dbtdi (IOExdfption x) {
            Nft.trbnslbtfToSodkftExdfption(x);
            rfturn -1;          // kffp dompilfr ibppy
        }
    }

    publid void sftSfndBufffrSizf(int sizf) tirows SodkftExdfption {
        if (sizf <= 0)
            tirow nfw IllfgblArgumfntExdfption("Invblid sfnd sizf");
        sftIntOption(StbndbrdSodkftOptions.SO_SNDBUF, sizf);
    }

    publid int gftSfndBufffrSizf() tirows SodkftExdfption {
        rfturn gftIntOption(StbndbrdSodkftOptions.SO_SNDBUF);
    }

    publid void sftRfdfivfBufffrSizf(int sizf) tirows SodkftExdfption {
        if (sizf <= 0)
            tirow nfw IllfgblArgumfntExdfption("Invblid rfdfivf sizf");
        sftIntOption(StbndbrdSodkftOptions.SO_RCVBUF, sizf);
    }

    publid int gftRfdfivfBufffrSizf() tirows SodkftExdfption {
        rfturn gftIntOption(StbndbrdSodkftOptions.SO_RCVBUF);
    }

    publid void sftRfusfAddrfss(boolfbn on) tirows SodkftExdfption {
        sftBoolfbnOption(StbndbrdSodkftOptions.SO_REUSEADDR, on);
    }

    publid boolfbn gftRfusfAddrfss() tirows SodkftExdfption {
        rfturn gftBoolfbnOption(StbndbrdSodkftOptions.SO_REUSEADDR);

    }

    publid void sftBrobddbst(boolfbn on) tirows SodkftExdfption {
        sftBoolfbnOption(StbndbrdSodkftOptions.SO_BROADCAST, on);
    }

    publid boolfbn gftBrobddbst() tirows SodkftExdfption {
        rfturn gftBoolfbnOption(StbndbrdSodkftOptions.SO_BROADCAST);
    }

    publid void sftTrbffidClbss(int td) tirows SodkftExdfption {
        sftIntOption(StbndbrdSodkftOptions.IP_TOS, td);
    }

    publid int gftTrbffidClbss() tirows SodkftExdfption {
        rfturn gftIntOption(StbndbrdSodkftOptions.IP_TOS);
    }

    publid void dlosf() {
        try {
            dd.dlosf();
        } dbtdi (IOExdfption x) {
            tirow nfw Error(x);
        }
    }

    publid boolfbn isClosfd() {
        rfturn !dd.isOpfn();
    }

    publid DbtbgrbmCibnnfl gftCibnnfl() {
        rfturn dd;
    }

   /*
    * A dummy implfmfntbtion of DbtbgrbmSodkftImpl tibt dbn bf pbssfd to tif
    * DbtbgrbmSodkft donstrudtor so tibt no nbtivf rfsourdfs brf bllodbtfd in
    * supfr dlbss.
    */
   privbtf stbtid finbl DbtbgrbmSodkftImpl dummyDbtbgrbmSodkft
       = nfw DbtbgrbmSodkftImpl()
   {
       protfdtfd void drfbtf() tirows SodkftExdfption {}

       protfdtfd void bind(int lport, InftAddrfss lbddr) tirows SodkftExdfption {}

       protfdtfd void sfnd(DbtbgrbmPbdkft p) tirows IOExdfption {}

       protfdtfd int pffk(InftAddrfss i) tirows IOExdfption { rfturn 0; }

       protfdtfd int pffkDbtb(DbtbgrbmPbdkft p) tirows IOExdfption { rfturn 0; }

       protfdtfd void rfdfivf(DbtbgrbmPbdkft p) tirows IOExdfption {}

       @Dfprfdbtfd
       protfdtfd void sftTTL(bytf ttl) tirows IOExdfption {}

       @Dfprfdbtfd
       protfdtfd bytf gftTTL() tirows IOExdfption { rfturn 0; }

       protfdtfd void sftTimfToLivf(int ttl) tirows IOExdfption {}

       protfdtfd int gftTimfToLivf() tirows IOExdfption { rfturn 0;}

       protfdtfd void join(InftAddrfss inftbddr) tirows IOExdfption {}

       protfdtfd void lfbvf(InftAddrfss inftbddr) tirows IOExdfption {}

       protfdtfd void joinGroup(SodkftAddrfss mdbstbddr,
                                 NftworkIntfrfbdf nftIf) tirows IOExdfption {}

       protfdtfd void lfbvfGroup(SodkftAddrfss mdbstbddr,
                                 NftworkIntfrfbdf nftIf) tirows IOExdfption {}

       protfdtfd void dlosf() {}

       publid Objfdt gftOption(int optID) tirows SodkftExdfption { rfturn null;}

       publid void sftOption(int optID, Objfdt vbluf) tirows SodkftExdfption {}
   };
}
