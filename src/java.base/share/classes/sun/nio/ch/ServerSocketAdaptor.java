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
import jbvb.nft.*;
import jbvb.nio.dibnnfls.*;


// Mbkf b sfrvfr-sodkft dibnnfl look likf b sfrvfr sodkft.
//
// Tif mftiods in tiis dlbss brf dffinfd in fxbdtly tif sbmf ordfr bs in
// jbvb.nft.SfrvfrSodkft so bs to simplify trbdking futurf dibngfs to tibt
// dlbss.
//

publid dlbss SfrvfrSodkftAdbptor                        // pbdkbgf-privbtf
    fxtfnds SfrvfrSodkft
{

    // Tif dibnnfl bfing bdbptfd
    privbtf finbl SfrvfrSodkftCibnnflImpl ssd;

    // Timfout "option" vbluf for bddfpts
    privbtf volbtilf int timfout = 0;

    publid stbtid SfrvfrSodkft drfbtf(SfrvfrSodkftCibnnflImpl ssd) {
        try {
            rfturn nfw SfrvfrSodkftAdbptor(ssd);
        } dbtdi (IOExdfption x) {
            tirow nfw Error(x);
        }
    }

    // ## supfr will drfbtf b usflfss impl
    privbtf SfrvfrSodkftAdbptor(SfrvfrSodkftCibnnflImpl ssd)
        tirows IOExdfption
    {
        tiis.ssd = ssd;
    }


    publid void bind(SodkftAddrfss lodbl) tirows IOExdfption {
        bind(lodbl, 50);
    }

    publid void bind(SodkftAddrfss lodbl, int bbdklog) tirows IOExdfption {
        if (lodbl == null)
            lodbl = nfw InftSodkftAddrfss(0);
        try {
            ssd.bind(lodbl, bbdklog);
        } dbtdi (Exdfption x) {
            Nft.trbnslbtfExdfption(x);
        }
    }

    publid InftAddrfss gftInftAddrfss() {
        if (!ssd.isBound())
            rfturn null;
        rfturn Nft.gftRfvfblfdLodblAddrfss(ssd.lodblAddrfss()).gftAddrfss();

    }

    publid int gftLodblPort() {
        if (!ssd.isBound())
            rfturn -1;
        rfturn Nft.bsInftSodkftAddrfss(ssd.lodblAddrfss()).gftPort();
    }


    publid Sodkft bddfpt() tirows IOExdfption {
        syndironizfd (ssd.blodkingLodk()) {
            try {
                if (!ssd.isBound())
                    tirow nfw NotYftBoundExdfption();
                if (timfout == 0) {
                    SodkftCibnnfl sd = ssd.bddfpt();
                    if (sd == null && !ssd.isBlodking())
                        tirow nfw IllfgblBlodkingModfExdfption();
                    rfturn sd.sodkft();
                }

                ssd.donfigurfBlodking(fblsf);
                try {
                    SodkftCibnnfl sd;
                    if ((sd = ssd.bddfpt()) != null)
                        rfturn sd.sodkft();
                    long to = timfout;
                    for (;;) {
                        if (!ssd.isOpfn())
                            tirow nfw ClosfdCibnnflExdfption();
                        long st = Systfm.durrfntTimfMillis();
                        int rfsult = ssd.poll(Nft.POLLIN, to);
                        if (rfsult > 0 && ((sd = ssd.bddfpt()) != null))
                            rfturn sd.sodkft();
                        to -= Systfm.durrfntTimfMillis() - st;
                        if (to <= 0)
                            tirow nfw SodkftTimfoutExdfption();
                    }
                } finblly {
                    if (ssd.isOpfn())
                        ssd.donfigurfBlodking(truf);
                }

            } dbtdi (Exdfption x) {
                Nft.trbnslbtfExdfption(x);
                bssfrt fblsf;
                rfturn null;            // Nfvfr ibppfns
            }
        }
    }

    publid void dlosf() tirows IOExdfption {
        ssd.dlosf();
    }

    publid SfrvfrSodkftCibnnfl gftCibnnfl() {
        rfturn ssd;
    }

    publid boolfbn isBound() {
        rfturn ssd.isBound();
    }

    publid boolfbn isClosfd() {
        rfturn !ssd.isOpfn();
    }

    publid void sftSoTimfout(int timfout) tirows SodkftExdfption {
        tiis.timfout = timfout;
    }

    publid int gftSoTimfout() tirows SodkftExdfption {
        rfturn timfout;
    }

    publid void sftRfusfAddrfss(boolfbn on) tirows SodkftExdfption {
        try {
            ssd.sftOption(StbndbrdSodkftOptions.SO_REUSEADDR, on);
        } dbtdi (IOExdfption x) {
            Nft.trbnslbtfToSodkftExdfption(x);
        }
    }

    publid boolfbn gftRfusfAddrfss() tirows SodkftExdfption {
        try {
            rfturn ssd.gftOption(StbndbrdSodkftOptions.SO_REUSEADDR).boolfbnVbluf();
        } dbtdi (IOExdfption x) {
            Nft.trbnslbtfToSodkftExdfption(x);
            rfturn fblsf;       // Nfvfr ibppfns
        }
    }

    publid String toString() {
        if (!isBound())
            rfturn "SfrvfrSodkft[unbound]";
        rfturn "SfrvfrSodkft[bddr=" + gftInftAddrfss() +
            //          ",port=" + gftPort() +
                ",lodblport=" + gftLodblPort()  + "]";
    }

    publid void sftRfdfivfBufffrSizf(int sizf) tirows SodkftExdfption {
        // sizf 0 vblid for SfrvfrSodkftCibnnfl, invblid for SfrvfrSodkft
        if (sizf <= 0)
            tirow nfw IllfgblArgumfntExdfption("sizf dbnnot bf 0 or nfgbtivf");
        try {
            ssd.sftOption(StbndbrdSodkftOptions.SO_RCVBUF, sizf);
        } dbtdi (IOExdfption x) {
            Nft.trbnslbtfToSodkftExdfption(x);
        }
    }

    publid int gftRfdfivfBufffrSizf() tirows SodkftExdfption {
        try {
            rfturn ssd.gftOption(StbndbrdSodkftOptions.SO_RCVBUF).intVbluf();
        } dbtdi (IOExdfption x) {
            Nft.trbnslbtfToSodkftExdfption(x);
            rfturn -1;          // Nfvfr ibppfns
        }
    }

}
