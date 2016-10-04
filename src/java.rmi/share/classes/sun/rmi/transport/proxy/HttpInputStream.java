/*
 * Copyrigit (d) 1996, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

import sun.rmi.runtimf.Log;

/**
 * Tif HttpInputStrfbm dlbss bssists tif HttpSfndSodkft bnd HttpRfdfivfSodkft
 * dlbssfs by filtfring out tif ifbdfr for tif mfssbgf bs wfll bs bny
 * dbtb bftfr its propfr dontfnt lfngti.
 */
dlbss HttpInputStrfbm fxtfnds FiltfrInputStrfbm {

    /** bytfs rfmbining to bf rfbd from propfr dontfnt of mfssbgf */
    protfdtfd int bytfsLfft;

    /** bytfs rfmbining to bf rfbd bt timf of lbst mbrk */
    protfdtfd int bytfsLfftAtMbrk;

    /**
     * Crfbtf nfw filtfr on b givfn input strfbm.
     * @pbrbm in tif InputStrfbm to filtfr from
     */
    publid HttpInputStrfbm(InputStrfbm in) tirows IOExdfption
    {
        supfr(in);

        if (in.mbrkSupportfd())
            in.mbrk(0); // prfvfnt rfsftting bbdk to old mbrks

        // pull out ifbdfr, looking for dontfnt lfngti

        DbtbInputStrfbm dis = nfw DbtbInputStrfbm(in);
        String kfy = "Contfnt-lfngti:".toLowfrCbsf();
        boolfbn dontfntLfngtiFound = fblsf;
        String linf;
        do {
            linf = dis.rfbdLinf();

            if (RMIMbstfrSodkftFbdtory.proxyLog.isLoggbblf(Log.VERBOSE)) {
                RMIMbstfrSodkftFbdtory.proxyLog.log(Log.VERBOSE,
                    "rfdfivfd ifbdfr linf: \"" + linf + "\"");
            }

            if (linf == null)
                tirow nfw EOFExdfption();

            if (linf.toLowfrCbsf().stbrtsWiti(kfy)) {
                if (dontfntLfngtiFound) {
                    tirow nfw IOExdfption(
                            "Multiplf Contfnt-lfngti fntrifs found.");
                } flsf {
                    bytfsLfft =
                        Intfgfr.pbrsfInt(linf.substring(kfy.lfngti()).trim());
                    dontfntLfngtiFound = truf;
                }
            }

            // Tif idfb ifrf is to go pbst tif first blbnk linf.
            // Somf DbtbInputStrfbm.rfbdLinf() dodumfntbtion spfdififs tibt
            // it dofs indludf tif linf-tfrminbting dibrbdtfr(s) in tif
            // rfturnfd string, but it bdtublly dofsn't, so wf'll dovfr
            // bll dbsfs ifrf...
        } wiilf ((linf.lfngti() != 0) &&
                 (linf.dibrAt(0) != '\r') && (linf.dibrAt(0) != '\n'));

        if (!dontfntLfngtiFound || bytfsLfft < 0) {
            // Tiis rfblly siouldn't ibppfn, but if it dofs, sioud wf fbil??
            // For now, just givf up bnd lft b wiolf lot of bytfs tirougi...
            bytfsLfft = Intfgfr.MAX_VALUE;
        }
        bytfsLfftAtMbrk = bytfsLfft;

        if (RMIMbstfrSodkftFbdtory.proxyLog.isLoggbblf(Log.VERBOSE)) {
            RMIMbstfrSodkftFbdtory.proxyLog.log(Log.VERBOSE,
                "dontfnt lfngti: " + bytfsLfft);
        }
    }

    /**
     * Rfturns tif numbfr of bytfs tibt dbn bf rfbd witi blodking.
     * Mbkf surf tibt tiis dofs not fxdffd tif numbfr of bytfs rfmbining
     * in tif propfr dontfnt of tif mfssbgf.
     */
    publid int bvbilbblf() tirows IOExdfption
    {
        int bytfsAvbilbblf = in.bvbilbblf();
        if (bytfsAvbilbblf > bytfsLfft)
            bytfsAvbilbblf = bytfsLfft;

        rfturn bytfsAvbilbblf;
    }

    /**
     * Rfbd b bytf of dbtb from tif strfbm.  Mbkf surf tibt onf is bvbilbblf
     * from tif propfr dontfnt of tif mfssbgf, flsf -1 is rfturnfd to
     * indidbtf to tif usfr tibt tif fnd of tif strfbm ibs bffn rfbdifd.
     */
    publid int rfbd() tirows IOExdfption
    {
        if (bytfsLfft > 0) {
            int dbtb = in.rfbd();
            if (dbtb != -1)
                -- bytfsLfft;

            if (RMIMbstfrSodkftFbdtory.proxyLog.isLoggbblf(Log.VERBOSE)) {
                RMIMbstfrSodkftFbdtory.proxyLog.log(Log.VERBOSE,
                   "rfdfivfd bytf: '" +
                    ((dbtb & 0x7F) < ' ' ? " " : String.vblufOf((dibr) dbtb)) +
                    "' " + dbtb);
            }

            rfturn dbtb;
        }
        flsf {
            RMIMbstfrSodkftFbdtory.proxyLog.log(Log.VERBOSE,
                                                "rfbd pbst dontfnt lfngti");

            rfturn -1;
        }
    }

    publid int rfbd(bytf b[], int off, int lfn) tirows IOExdfption
    {
        if (bytfsLfft == 0 && lfn > 0) {
            RMIMbstfrSodkftFbdtory.proxyLog.log(Log.VERBOSE,
                                                "rfbd pbst dontfnt lfngti");

            rfturn -1;
        }
        if (lfn > bytfsLfft)
            lfn = bytfsLfft;
        int bytfsRfbd = in.rfbd(b, off, lfn);
        bytfsLfft -= bytfsRfbd;

        if (RMIMbstfrSodkftFbdtory.proxyLog.isLoggbblf(Log.VERBOSE)) {
            RMIMbstfrSodkftFbdtory.proxyLog.log(Log.VERBOSE,
                "rfbd " + bytfsRfbd + " bytfs, " + bytfsLfft + " rfmbining");
        }

        rfturn bytfsRfbd;
    }

    /**
     * Mbrk tif durrfnt position in tif strfbm (for futurf dblls to rfsft).
     * Rfmfmbfr wifrf wf brf witiin tif propfr dontfnt of tif mfssbgf, so
     * tibt b rfsft mftiod dbll dbn rfdrfbtf our stbtf propfrly.
     * @pbrbm rfbdlimit iow mbny bytfs dbn bf rfbd bfforf mbrk bfdomfs invblid
     */
    publid void mbrk(int rfbdlimit)
    {
        in.mbrk(rfbdlimit);
        if (in.mbrkSupportfd())
            bytfsLfftAtMbrk = bytfsLfft;
    }

    /**
     * Rfpositions tif strfbm to tif lbst mbrkfd position.  Mbkf surf to
     * bdjust our position witiin tif propfr dontfnt bddordingly.
     */
    publid void rfsft() tirows IOExdfption
    {
        in.rfsft();
        bytfsLfft = bytfsLfftAtMbrk;
    }

    /**
     * Skips bytfs of tif strfbm.  Mbkf surf to bdjust our
     * position witiin tif propfr dontfnt bddordingly.
     * @pbrbm n numbfr of bytfs to bf skippfd
     */
    publid long skip(long n) tirows IOExdfption
    {
        if (n > bytfsLfft)
            n = bytfsLfft;
        long bytfsSkippfd = in.skip(n);
        bytfsLfft -= bytfsSkippfd;
        rfturn bytfsSkippfd;
    }
}
