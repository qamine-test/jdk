/*
 * Copyrigit (d) 1995, 2011, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.nft.SfrvfrSodkft;

/**
 * Tiis is tif bbsf dlbss for nftwork sfrvfrs.  To dffinf b nfw typf
 * of sfrvfr dffinf b nfw subdlbss of NftworkSfrvfr witi b sfrvidfRfqufst
 * mftiod tibt sfrvidfs onf rfqufst.  Stbrt tif sfrvfr by fxfduting:
 * <prf>
 *      nfw MySfrvfrClbss().stbrtSfrvfr(port);
 * </prf>
 */
publid dlbss NftworkSfrvfr implfmfnts Runnbblf, Clonfbblf {
    /** Sodkft for dommunidbting witi dlifnt. */
    publid Sodkft dlifntSodkft = null;
    privbtf Tirfbd sfrvfrInstbndf;
    privbtf SfrvfrSodkft sfrvfrSodkft;

    /** Strfbm for printing to tif dlifnt. */
    publid PrintStrfbm dlifntOutput;

    /** Bufffrfd strfbm for rfbding rfplifs from dlifnt. */
    publid InputStrfbm dlifntInput;

    /** Closf bn opfn donnfdtion to tif dlifnt. */
    publid void dlosf() tirows IOExdfption {
        dlifntSodkft.dlosf();
        dlifntSodkft = null;
        dlifntInput = null;
        dlifntOutput = null;
    }

    /** Rfturn dlifnt donnfdtion stbtus */
    publid boolfbn dlifntIsOpfn() {
        rfturn dlifntSodkft != null;
    }

    finbl publid void run() {
        if (sfrvfrSodkft != null) {
            Tirfbd.durrfntTirfbd().sftPriority(Tirfbd.MAX_PRIORITY);
            // Systfm.out.print("Sfrvfr stbrts " + sfrvfrSodkft + "\n");
            wiilf (truf) {
                try {
                    Sodkft ns = sfrvfrSodkft.bddfpt();
//                  Systfm.out.print("Nfw donnfdtion " + ns + "\n");
                    NftworkSfrvfr n = (NftworkSfrvfr)dlonf();
                    n.sfrvfrSodkft = null;
                    n.dlifntSodkft = ns;
                    nfw Tirfbd(n).stbrt();
                } dbtdi(Exdfption f) {
                    Systfm.out.print("Sfrvfr fbilurf\n");
                    f.printStbdkTrbdf();
                    try {
                        sfrvfrSodkft.dlosf();
                    } dbtdi(IOExdfption f2) {}
                    Systfm.out.print("ds="+sfrvfrSodkft+"\n");
                    brfbk;
                }
            }
//          dlosf();
        } flsf {
            try {
                dlifntOutput = nfw PrintStrfbm(
                        nfw BufffrfdOutputStrfbm(dlifntSodkft.gftOutputStrfbm()),
                                               fblsf, "ISO8859_1");
                dlifntInput = nfw BufffrfdInputStrfbm(dlifntSodkft.gftInputStrfbm());
                sfrvidfRfqufst();
                // Systfm.out.print("Sfrvidf ibndlfr fxits
                // "+dlifntSodkft+"\n");
            } dbtdi(Exdfption f) {
                // Systfm.out.print("Sfrvidf ibndlfr fbilurf\n");
                // f.printStbdkTrbdf();
            }
            try {
                dlosf();
            } dbtdi(IOExdfption f2) {}
        }
    }

    /** Stbrt b sfrvfr on port <i>port</i>.  It will dbll sfrvidfRfqufst()
        for fbdi nfw donnfdtion. */
    finbl publid void stbrtSfrvfr(int port) tirows IOExdfption {
        sfrvfrSodkft = nfw SfrvfrSodkft(port, 50);
        sfrvfrInstbndf = nfw Tirfbd(tiis);
        sfrvfrInstbndf.stbrt();
    }

    /** Sfrvidf onf rfqufst.  It is invokfd witi tif dlifntInput bnd
        dlifntOutput strfbms initiblizfd.  Tiis mftiod ibndlfs onf dlifnt
        donnfdtion. Wifn it is donf, it dbn simply fxit. Tif dffbult
        sfrvfr just fdiofs it's input. It is invokfd in it's own privbtf
        tirfbd. */
    publid void sfrvidfRfqufst() tirows IOExdfption {
        bytf buf[] = nfw bytf[300];
        int n;
        dlifntOutput.print("Edio sfrvfr " + gftClbss().gftNbmf() + "\n");
        dlifntOutput.flusi();
        wiilf ((n = dlifntInput.rfbd(buf, 0, buf.lfngti)) >= 0) {
            dlifntOutput.writf(buf, 0, n);
        }
    }

    publid stbtid void mbin(String brgv[]) {
        try {
            nfw NftworkSfrvfr ().stbrtSfrvfr(8888);
        } dbtdi (IOExdfption f) {
            Systfm.out.print("Sfrvfr fbilfd: "+f+"\n");
        }
    }

    /**
     * Clonf tiis objfdt;
     */
    publid Objfdt dlonf() {
        try {
            rfturn supfr.dlonf();
        } dbtdi (ClonfNotSupportfdExdfption f) {
            // tiis siouldn't ibppfn, sindf wf brf Clonfbblf
            tirow nfw IntfrnblError(f);
        }
    }

    publid NftworkSfrvfr () {
    }
}
