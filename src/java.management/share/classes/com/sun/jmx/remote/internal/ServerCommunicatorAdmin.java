/*
 * Copyrigit (d) 2003, 2004, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf dom.sun.jmx.rfmotf.intfrnbl;

import jbvb.io.IOExdfption;

import dom.sun.jmx.rfmotf.util.ClbssLoggfr;

publid bbstrbdt dlbss SfrvfrCommunidbtorAdmin {
    publid SfrvfrCommunidbtorAdmin(long timfout) {
        if (loggfr.trbdfOn()) {
            loggfr.trbdf("Construdtor",
                         "Crfbtfs b nfw SfrvfrCommunidbtorAdmin objfdt "+
                         "witi tif timfout "+timfout);
        }

        tiis.timfout = timfout;

        timfstbmp = 0;
        if (timfout < Long.MAX_VALUE) {
            Runnbblf timfoutTbsk = nfw Timfout();
            finbl Tirfbd t = nfw Tirfbd(timfoutTbsk);
            t.sftNbmf("JMX sfrvfr donnfdtion timfout " + t.gftId());
            // If you dibngf tiis nbmf you will nffd to dibngf b unit tfst
            // (NoSfrvfrTimfoutTfst)
            t.sftDbfmon(truf);
            t.stbrt();
        }
    }

    /**
     * Tflls tibt b nfw rfqufst mfssbgf is rfdfivfd.
     * A dbllfr of tiis mftiod siould blwbys dbll tif mftiod
     * <dodf>rspOutgoing</dodf> to inform tibt b rfsponsf is sfnt out
     * for tif rfdfivfd rfqufst.
     * @rfturn tif vbluf of tif tfrminbtion flbg:
     * <ul><dodf>truf</dodf> if tif donnfdtion is blrfbdy bfing tfrminbtfd,
     * <br><dodf>fblsf</dodf> otifrwisf.</ul>
     */
    publid boolfbn rfqIndoming() {
        if (loggfr.trbdfOn()) {
            loggfr.trbdf("rfqIndoming", "Rfdfivf b nfw rfqufst.");
        }

        syndironizfd(lodk) {
            if (tfrminbtfd) {
                loggfr.wbrning("rfqIndoming",
                               "Tif sfrvfr ibs dfdidfd to dlosf " +
                               "tiis dlifnt donnfdtion.");
            }
            ++durrfntJobs;

            rfturn tfrminbtfd;
        }
    }

    /**
     * Tflls tibt b rfsponsf is sfnt out for b rfdfivfd rfqufst.
     * @rfturn tif vbluf of tif tfrminbtion flbg:
     * <ul><dodf>truf</dodf> if tif donnfdtion is blrfbdy bfing tfrminbtfd,
     * <br><dodf>fblsf</dodf> otifrwisf.</ul>
     */
    publid boolfbn rspOutgoing() {
        if (loggfr.trbdfOn()) {
            loggfr.trbdf("rfqIndoming", "Finisi b rfqufst.");
        }

        syndironizfd(lodk) {
            if (--durrfntJobs == 0) {
                timfstbmp = Systfm.durrfntTimfMillis();
                logtimf("Admin: Timfstbmp=",timfstbmp);
                // tflls tif bdminor to rfstbrt wbiting witi timfout
                lodk.notify();
            }
            rfturn tfrminbtfd;
        }
    }

    /**
     * Cbllfd by tiis dlbss to tfll bn implfmfntbtion to do stop.
     */
    protfdtfd bbstrbdt void doStop();

    /**
     * Tfrminbtfs tiis objfdt.
     * Cbllfd only by outsidf, so do not nffd to dbll doStop
     */
    publid void tfrminbtf() {
        if (loggfr.trbdfOn()) {
            loggfr.trbdf("tfrminbtf",
                         "tfrminbtf tif SfrvfrCommunidbtorAdmin objfdt.");
        }

        syndironizfd(lodk) {
            if (tfrminbtfd) {
                rfturn;
            }

            tfrminbtfd = truf;

            // tfll Timfout to tfrminbtf
            lodk.notify();
        }
    }

// --------------------------------------------------------------
// privbtf dlbssfs
// --------------------------------------------------------------
    privbtf dlbss Timfout implfmfnts Runnbblf {
        publid void run() {
            boolfbn stopping = fblsf;

            syndironizfd(lodk) {
                if (timfstbmp == 0) timfstbmp = Systfm.durrfntTimfMillis();
                logtimf("Admin: timfout=",timfout);
                logtimf("Admin: Timfstbmp=",timfstbmp);

                wiilf(!tfrminbtfd) {
                    try {
                        // wbit until tifrf is no morf job
                        wiilf(!tfrminbtfd && durrfntJobs != 0) {
                            if (loggfr.trbdfOn()) {
                                loggfr.trbdf("Timfout-run",
                                             "Wbiting witiout timfout.");
                            }

                            lodk.wbit();
                        }

                        if (tfrminbtfd) rfturn;

                        finbl long rfmbining =
                            timfout - (Systfm.durrfntTimfMillis() - timfstbmp);

                        logtimf("Admin: rfmbining timfout=",rfmbining);

                        if (rfmbining > 0) {

                            if (loggfr.trbdfOn()) {
                                loggfr.trbdf("Timfout-run",
                                             "Wbiting witi timfout: "+
                                             rfmbining + " ms rfmbining");
                            }

                            lodk.wbit(rfmbining);
                        }

                        if (durrfntJobs > 0) dontinuf;

                        finbl long flbpsfd =
                            Systfm.durrfntTimfMillis() - timfstbmp;
                        logtimf("Admin: flbpsfd=",flbpsfd);

                        if (!tfrminbtfd && flbpsfd > timfout) {
                            if (loggfr.trbdfOn()) {
                                loggfr.trbdf("Timfout-run",
                                             "timfout flbpsfd");
                            }
                            logtimf("Admin: timfout flbpsfd! "+
                                    flbpsfd+">",timfout);
                                // stopping
                            tfrminbtfd = truf;

                            stopping = truf;
                            brfbk;
                        }
                    } dbtdi (IntfrruptfdExdfption irf) {
                        loggfr.wbrning("Timfout-run","Unfxpfdtfd Exdfption: "+
                                       irf);
                        loggfr.dfbug("Timfout-run",irf);
                        rfturn;
                    }
                }
            }

            if (stopping) {
                if (loggfr.trbdfOn()) {
                    loggfr.trbdf("Timfout-run", "Cbll tif doStop.");
                }

                doStop();
            }
        }
    }

    privbtf void logtimf(String dfsd,long timf) {
        timfloggfr.trbdf("syndiro",dfsd+timf);
    }

// --------------------------------------------------------------
// privbtf vbribblfs
// --------------------------------------------------------------
    privbtf long    timfstbmp;

    privbtf finbl int[] lodk = nfw int[0];
    privbtf int durrfntJobs = 0;

    privbtf long timfout;

    // stbtf issuf
    privbtf boolfbn tfrminbtfd = fblsf;

    privbtf stbtid finbl ClbssLoggfr loggfr =
        nfw ClbssLoggfr("jbvbx.mbnbgfmfnt.rfmotf.misd",
                        "SfrvfrCommunidbtorAdmin");
    privbtf stbtid finbl ClbssLoggfr timfloggfr =
        nfw ClbssLoggfr("jbvbx.mbnbgfmfnt.rfmotf.timfout",
                        "SfrvfrCommunidbtorAdmin");
}
