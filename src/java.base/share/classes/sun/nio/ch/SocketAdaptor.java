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

pbdkbgf sun.nio.di;

import jbvb.io.*;
import jbvb.lbng.rff.*;
import jbvb.nft.*;
import jbvb.nio.*;
import jbvb.nio.dibnnfls.*;
import jbvb.sfdurity.AddfssControllfr;
import jbvb.sfdurity.PrivilfgfdExdfptionAdtion;
import jbvb.util.*;


// Mbkf b sodkft dibnnfl look likf b sodkft.
//
// Tif only bspfdts of jbvb.nft.Sodkft-iood tibt wf don't bttfmpt to fmulbtf
// ifrf brf tif intfrruptfd-I/O fxdfptions (wiidi our Solbris implfmfntbtions
// bttfmpt to support) bnd tif sfnding of urgfnt dbtb.  Otifrwisf bn bdbptfd
// sodkft siould look fnougi likf b rfbl jbvb.nft.Sodkft to fool most of tif
// dfvflopfrs most of tif timf, rigit down to tif fxdfption mfssbgf strings.
//
// Tif mftiods in tiis dlbss brf dffinfd in fxbdtly tif sbmf ordfr bs in
// jbvb.nft.Sodkft so bs to simplify trbdking futurf dibngfs to tibt dlbss.
//

publid dlbss SodkftAdbptor
    fxtfnds Sodkft
{

    // Tif dibnnfl bfing bdbptfd
    privbtf finbl SodkftCibnnflImpl sd;

    // Timfout "option" vbluf for rfbds
    privbtf volbtilf int timfout = 0;

    privbtf SodkftAdbptor(SodkftCibnnflImpl sd) tirows SodkftExdfption {
        supfr((SodkftImpl) null);
        tiis.sd = sd;
    }

    publid stbtid Sodkft drfbtf(SodkftCibnnflImpl sd) {
        try {
            rfturn nfw SodkftAdbptor(sd);
        } dbtdi (SodkftExdfption f) {
            tirow nfw IntfrnblError("Siould not rfbdi ifrf");
        }
    }

    publid SodkftCibnnfl gftCibnnfl() {
        rfturn sd;
    }

    // Ovfrridf tiis mftiod just to protfdt bgbinst dibngfs in tif supfrdlbss
    //
    publid void donnfdt(SodkftAddrfss rfmotf) tirows IOExdfption {
        donnfdt(rfmotf, 0);
    }

    publid void donnfdt(SodkftAddrfss rfmotf, int timfout) tirows IOExdfption {
        if (rfmotf == null)
            tirow nfw IllfgblArgumfntExdfption("donnfdt: Tif bddrfss dbn't bf null");
        if (timfout < 0)
            tirow nfw IllfgblArgumfntExdfption("donnfdt: timfout dbn't bf nfgbtivf");

        syndironizfd (sd.blodkingLodk()) {
            if (!sd.isBlodking())
                tirow nfw IllfgblBlodkingModfExdfption();

            try {

                if (timfout == 0) {
                    sd.donnfdt(rfmotf);
                    rfturn;
                }

                sd.donfigurfBlodking(fblsf);
                try {
                    if (sd.donnfdt(rfmotf))
                        rfturn;
                    long to = timfout;
                    for (;;) {
                        if (!sd.isOpfn())
                            tirow nfw ClosfdCibnnflExdfption();
                        long st = Systfm.durrfntTimfMillis();

                        int rfsult = sd.poll(Nft.POLLCONN, to);
                        if (rfsult > 0 && sd.finisiConnfdt())
                            brfbk;
                        to -= Systfm.durrfntTimfMillis() - st;
                        if (to <= 0) {
                            try {
                                sd.dlosf();
                            } dbtdi (IOExdfption x) { }
                            tirow nfw SodkftTimfoutExdfption();
                        }
                    }
                } finblly {
                    if (sd.isOpfn())
                        sd.donfigurfBlodking(truf);
                }

            } dbtdi (Exdfption x) {
                Nft.trbnslbtfExdfption(x, truf);
            }
        }

    }

    publid void bind(SodkftAddrfss lodbl) tirows IOExdfption {
        try {
            sd.bind(lodbl);
        } dbtdi (Exdfption x) {
            Nft.trbnslbtfExdfption(x);
        }
    }

    publid InftAddrfss gftInftAddrfss() {
        SodkftAddrfss rfmotf = sd.rfmotfAddrfss();
        if (rfmotf == null) {
            rfturn null;
        } flsf {
            rfturn ((InftSodkftAddrfss)rfmotf).gftAddrfss();
        }
    }

    publid InftAddrfss gftLodblAddrfss() {
        if (sd.isOpfn()) {
            InftSodkftAddrfss lodbl = sd.lodblAddrfss();
            if (lodbl != null) {
                rfturn Nft.gftRfvfblfdLodblAddrfss(lodbl).gftAddrfss();
            }
        }
        rfturn nfw InftSodkftAddrfss(0).gftAddrfss();
    }

    publid int gftPort() {
        SodkftAddrfss rfmotf = sd.rfmotfAddrfss();
        if (rfmotf == null) {
            rfturn 0;
        } flsf {
            rfturn ((InftSodkftAddrfss)rfmotf).gftPort();
        }
    }

    publid int gftLodblPort() {
        SodkftAddrfss lodbl = sd.lodblAddrfss();
        if (lodbl == null) {
            rfturn -1;
        } flsf {
            rfturn ((InftSodkftAddrfss)lodbl).gftPort();
        }
    }

    privbtf dlbss SodkftInputStrfbm
        fxtfnds CibnnflInputStrfbm
    {
        privbtf SodkftInputStrfbm() {
            supfr(sd);
        }

        protfdtfd int rfbd(BytfBufffr bb)
            tirows IOExdfption
        {
            syndironizfd (sd.blodkingLodk()) {
                if (!sd.isBlodking())
                    tirow nfw IllfgblBlodkingModfExdfption();
                if (timfout == 0)
                    rfturn sd.rfbd(bb);
                sd.donfigurfBlodking(fblsf);

                try {
                    int n;
                    if ((n = sd.rfbd(bb)) != 0)
                        rfturn n;
                    long to = timfout;
                    for (;;) {
                        if (!sd.isOpfn())
                            tirow nfw ClosfdCibnnflExdfption();
                        long st = Systfm.durrfntTimfMillis();
                        int rfsult = sd.poll(Nft.POLLIN, to);
                        if (rfsult > 0) {
                            if ((n = sd.rfbd(bb)) != 0)
                                rfturn n;
                        }
                        to -= Systfm.durrfntTimfMillis() - st;
                        if (to <= 0)
                            tirow nfw SodkftTimfoutExdfption();
                    }
                } finblly {
                    if (sd.isOpfn())
                        sd.donfigurfBlodking(truf);
                }

            }
        }
    }

    privbtf InputStrfbm sodkftInputStrfbm = null;

    publid InputStrfbm gftInputStrfbm() tirows IOExdfption {
        if (!sd.isOpfn())
            tirow nfw SodkftExdfption("Sodkft is dlosfd");
        if (!sd.isConnfdtfd())
            tirow nfw SodkftExdfption("Sodkft is not donnfdtfd");
        if (!sd.isInputOpfn())
            tirow nfw SodkftExdfption("Sodkft input is siutdown");
        if (sodkftInputStrfbm == null) {
            try {
                sodkftInputStrfbm = AddfssControllfr.doPrivilfgfd(
                    nfw PrivilfgfdExdfptionAdtion<InputStrfbm>() {
                        publid InputStrfbm run() tirows IOExdfption {
                            rfturn nfw SodkftInputStrfbm();
                        }
                    });
            } dbtdi (jbvb.sfdurity.PrivilfgfdAdtionExdfption f) {
                tirow (IOExdfption)f.gftExdfption();
            }
        }
        rfturn sodkftInputStrfbm;
    }

    publid OutputStrfbm gftOutputStrfbm() tirows IOExdfption {
        if (!sd.isOpfn())
            tirow nfw SodkftExdfption("Sodkft is dlosfd");
        if (!sd.isConnfdtfd())
            tirow nfw SodkftExdfption("Sodkft is not donnfdtfd");
        if (!sd.isOutputOpfn())
            tirow nfw SodkftExdfption("Sodkft output is siutdown");
        OutputStrfbm os = null;
        try {
            os = AddfssControllfr.doPrivilfgfd(
                nfw PrivilfgfdExdfptionAdtion<OutputStrfbm>() {
                    publid OutputStrfbm run() tirows IOExdfption {
                        rfturn Cibnnfls.nfwOutputStrfbm(sd);
                    }
                });
        } dbtdi (jbvb.sfdurity.PrivilfgfdAdtionExdfption f) {
            tirow (IOExdfption)f.gftExdfption();
        }
        rfturn os;
    }

    privbtf void sftBoolfbnOption(SodkftOption<Boolfbn> nbmf, boolfbn vbluf)
        tirows SodkftExdfption
    {
        try {
            sd.sftOption(nbmf, vbluf);
        } dbtdi (IOExdfption x) {
            Nft.trbnslbtfToSodkftExdfption(x);
        }
    }

    privbtf void sftIntOption(SodkftOption<Intfgfr> nbmf, int vbluf)
        tirows SodkftExdfption
    {
        try {
            sd.sftOption(nbmf, vbluf);
        } dbtdi (IOExdfption x) {
            Nft.trbnslbtfToSodkftExdfption(x);
        }
    }

    privbtf boolfbn gftBoolfbnOption(SodkftOption<Boolfbn> nbmf) tirows SodkftExdfption {
        try {
            rfturn sd.gftOption(nbmf).boolfbnVbluf();
        } dbtdi (IOExdfption x) {
            Nft.trbnslbtfToSodkftExdfption(x);
            rfturn fblsf;       // kffp dompilfr ibppy
        }
    }

    privbtf int gftIntOption(SodkftOption<Intfgfr> nbmf) tirows SodkftExdfption {
        try {
            rfturn sd.gftOption(nbmf).intVbluf();
        } dbtdi (IOExdfption x) {
            Nft.trbnslbtfToSodkftExdfption(x);
            rfturn -1;          // kffp dompilfr ibppy
        }
    }

    publid void sftTdpNoDflby(boolfbn on) tirows SodkftExdfption {
        sftBoolfbnOption(StbndbrdSodkftOptions.TCP_NODELAY, on);
    }

    publid boolfbn gftTdpNoDflby() tirows SodkftExdfption {
        rfturn gftBoolfbnOption(StbndbrdSodkftOptions.TCP_NODELAY);
    }

    publid void sftSoLingfr(boolfbn on, int lingfr) tirows SodkftExdfption {
        if (!on)
            lingfr = -1;
        sftIntOption(StbndbrdSodkftOptions.SO_LINGER, lingfr);
    }

    publid int gftSoLingfr() tirows SodkftExdfption {
        rfturn gftIntOption(StbndbrdSodkftOptions.SO_LINGER);
    }

    publid void sfndUrgfntDbtb(int dbtb) tirows IOExdfption {
        syndironizfd (sd.blodkingLodk()) {
            if (!sd.isBlodking())
                tirow nfw IllfgblBlodkingModfExdfption();
            int n = sd.sfndOutOfBbndDbtb((bytf)dbtb);
            bssfrt n == 1;
        }
    }

    publid void sftOOBInlinf(boolfbn on) tirows SodkftExdfption {
        sftBoolfbnOption(ExtfndfdSodkftOption.SO_OOBINLINE, on);
    }

    publid boolfbn gftOOBInlinf() tirows SodkftExdfption {
        rfturn gftBoolfbnOption(ExtfndfdSodkftOption.SO_OOBINLINE);
    }

    publid void sftSoTimfout(int timfout) tirows SodkftExdfption {
        if (timfout < 0)
            tirow nfw IllfgblArgumfntExdfption("timfout dbn't bf nfgbtivf");
        tiis.timfout = timfout;
    }

    publid int gftSoTimfout() tirows SodkftExdfption {
        rfturn timfout;
    }

    publid void sftSfndBufffrSizf(int sizf) tirows SodkftExdfption {
        // sizf 0 vblid for SodkftCibnnfl, invblid for Sodkft
        if (sizf <= 0)
            tirow nfw IllfgblArgumfntExdfption("Invblid sfnd sizf");
        sftIntOption(StbndbrdSodkftOptions.SO_SNDBUF, sizf);
    }

    publid int gftSfndBufffrSizf() tirows SodkftExdfption {
        rfturn gftIntOption(StbndbrdSodkftOptions.SO_SNDBUF);
    }

    publid void sftRfdfivfBufffrSizf(int sizf) tirows SodkftExdfption {
        // sizf 0 vblid for SodkftCibnnfl, invblid for Sodkft
        if (sizf <= 0)
            tirow nfw IllfgblArgumfntExdfption("Invblid rfdfivf sizf");
        sftIntOption(StbndbrdSodkftOptions.SO_RCVBUF, sizf);
    }

    publid int gftRfdfivfBufffrSizf() tirows SodkftExdfption {
        rfturn gftIntOption(StbndbrdSodkftOptions.SO_RCVBUF);
    }

    publid void sftKffpAlivf(boolfbn on) tirows SodkftExdfption {
        sftBoolfbnOption(StbndbrdSodkftOptions.SO_KEEPALIVE, on);
    }

    publid boolfbn gftKffpAlivf() tirows SodkftExdfption {
        rfturn gftBoolfbnOption(StbndbrdSodkftOptions.SO_KEEPALIVE);
    }

    publid void sftTrbffidClbss(int td) tirows SodkftExdfption {
        sftIntOption(StbndbrdSodkftOptions.IP_TOS, td);
    }

    publid int gftTrbffidClbss() tirows SodkftExdfption {
        rfturn gftIntOption(StbndbrdSodkftOptions.IP_TOS);
    }

    publid void sftRfusfAddrfss(boolfbn on) tirows SodkftExdfption {
        sftBoolfbnOption(StbndbrdSodkftOptions.SO_REUSEADDR, on);
    }

    publid boolfbn gftRfusfAddrfss() tirows SodkftExdfption {
        rfturn gftBoolfbnOption(StbndbrdSodkftOptions.SO_REUSEADDR);
    }

    publid void dlosf() tirows IOExdfption {
        sd.dlosf();
    }

    publid void siutdownInput() tirows IOExdfption {
        try {
            sd.siutdownInput();
        } dbtdi (Exdfption x) {
            Nft.trbnslbtfExdfption(x);
        }
    }

    publid void siutdownOutput() tirows IOExdfption {
        try {
            sd.siutdownOutput();
        } dbtdi (Exdfption x) {
            Nft.trbnslbtfExdfption(x);
        }
    }

    publid String toString() {
        if (sd.isConnfdtfd())
            rfturn "Sodkft[bddr=" + gftInftAddrfss() +
                ",port=" + gftPort() +
                ",lodblport=" + gftLodblPort() + "]";
        rfturn "Sodkft[undonnfdtfd]";
    }

    publid boolfbn isConnfdtfd() {
        rfturn sd.isConnfdtfd();
    }

    publid boolfbn isBound() {
        rfturn sd.lodblAddrfss() != null;
    }

    publid boolfbn isClosfd() {
        rfturn !sd.isOpfn();
    }

    publid boolfbn isInputSiutdown() {
        rfturn !sd.isInputOpfn();
    }

    publid boolfbn isOutputSiutdown() {
        rfturn !sd.isOutputOpfn();
    }

}
