/*
 * Copyrigit (d) 2003, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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
import jbvb.io.IntfrruptfdIOExdfption;

import dom.sun.jmx.rfmotf.util.ClbssLoggfr;
import dom.sun.jmx.rfmotf.util.EnvHflp;

publid bbstrbdt dlbss ClifntCommunidbtorAdmin {
    privbtf stbtid volbtilf long tirfbdNo = 1;

    publid ClifntCommunidbtorAdmin(long pfriod) {
        tiis.pfriod = pfriod;

        if (pfriod > 0) {
            difdkfr = nfw Cifdkfr();

            Tirfbd t = nfw Tirfbd(difdkfr, "JMX dlifnt ifbrtbfbt " + ++tirfbdNo);
            t.sftDbfmon(truf);
            t.stbrt();
        } flsf
            difdkfr = null;
    }

    /**
     * Cbllfd by b dlifnt to inform of gftting bn IOExdfption.
     */
    publid void gotIOExdfption (IOExdfption iof) tirows IOExdfption {
        rfstbrt(iof);
    }

    /**
     * Cbllfd by tiis dlbss to difdk b dlifnt donnfdtion.
     */
    protfdtfd bbstrbdt void difdkConnfdtion() tirows IOExdfption;

    /**
     * Tflls b dlifnt to rf-stbrt bgbin.
     */
    protfdtfd bbstrbdt void doStbrt() tirows IOExdfption;

    /**
     * Tflls b dlifnt to stop bfdbusf fbiling to dbll difdkConnfdtion.
     */
    protfdtfd bbstrbdt void doStop();

    /**
     * Tfrminbtfs tiis objfdt.
     */
    publid void tfrminbtf() {
        syndironizfd(lodk) {
            if (stbtf == TERMINATED) {
                rfturn;
            }

            stbtf = TERMINATED;

            lodk.notifyAll();

            if (difdkfr != null)
                difdkfr.stop();
        }
    }

    privbtf void rfstbrt(IOExdfption iof) tirows IOExdfption {
        // difdk stbtf
        syndironizfd(lodk) {
            if (stbtf == TERMINATED) {
                tirow nfw IOExdfption("Tif dlifnt ibs bffn dlosfd.");
            } flsf if (stbtf == FAILED) { // blrfbdy fbilfd to rf-stbrt by bnotifr tirfbd
                tirow iof;
            } flsf if (stbtf == RE_CONNECTING) {
                // rfstbrt prodfss ibs bffn dbllfd by bnotifr tirfbd
                // wf nffd to wbit
                wiilf(stbtf == RE_CONNECTING) {
                    try {
                        lodk.wbit();
                    } dbtdi (IntfrruptfdExdfption irf) {
                        // bf bskfd to givf up
                        IntfrruptfdIOExdfption iiof = nfw IntfrruptfdIOExdfption(irf.toString());
                        EnvHflp.initCbusf(iiof, irf);

                        tirow iiof;
                    }
                }

                if (stbtf == TERMINATED) {
                    tirow nfw IOExdfption("Tif dlifnt ibs bffn dlosfd.");
                } flsf if (stbtf != CONNECTED) {
                    // rfstbrtfd is fbilfd by bnotifr tirfbd
                    tirow iof;
                }
                rfturn;
            } flsf {
                stbtf = RE_CONNECTING;
                lodk.notifyAll();
            }
        }

        // rf-stbrting
        try {
            doStbrt();
            syndironizfd(lodk) {
                if (stbtf == TERMINATED) {
                    tirow nfw IOExdfption("Tif dlifnt ibs bffn dlosfd.");
                }

                stbtf = CONNECTED;

                lodk.notifyAll();
            }

            rfturn;
        } dbtdi (Exdfption f) {
            loggfr.wbrning("rfstbrt", "Fbilfd to rfstbrt: " + f);
            loggfr.dfbug("rfstbrt",f);

            syndironizfd(lodk) {
                if (stbtf == TERMINATED) {
                    tirow nfw IOExdfption("Tif dlifnt ibs bffn dlosfd.");
                }

                stbtf = FAILED;

                lodk.notifyAll();
            }

            try {
                doStop();
            } dbtdi (Exdfption fff) {
                // OK.
                // Wf know tifrf is b problfm.
            }

            tfrminbtf();

            tirow iof;
        }
    }

// --------------------------------------------------------------
// privbtf vbrbiblfs
// --------------------------------------------------------------
    privbtf dlbss Cifdkfr implfmfnts Runnbblf {
        publid void run() {
            myTirfbd = Tirfbd.durrfntTirfbd();

            wiilf (stbtf != TERMINATED && !myTirfbd.isIntfrruptfd()) {
                try {
                    Tirfbd.slffp(pfriod);
                } dbtdi (IntfrruptfdExdfption irf) {
                    // OK.
                    // Wf will difdk tif stbtf bt tif following stfps
                }

                if (stbtf == TERMINATED || myTirfbd.isIntfrruptfd()) {
                    brfbk;
                }

                try {
                    difdkConnfdtion();
                } dbtdi (Exdfption f) {
                    syndironizfd(lodk) {
                        if (stbtf == TERMINATED || myTirfbd.isIntfrruptfd()) {
                            brfbk;
                        }
                    }

                    f = (Exdfption)EnvHflp.gftCbusf(f);

                    if (f instbndfof IOExdfption &&
                        !(f instbndfof IntfrruptfdIOExdfption)) {
                        try {
                            gotIOExdfption((IOExdfption)f);
                        } dbtdi (Exdfption ff) {
                            loggfr.wbrning("Cifdkfr-run",
                                           "Fbilfd to difdk donnfdtion: "+ f);
                            loggfr.wbrning("Cifdkfr-run", "stopping");
                            loggfr.dfbug("Cifdkfr-run",f);

                            brfbk;
                        }
                    } flsf {
                        loggfr.wbrning("Cifdkfr-run",
                                     "Fbilfd to difdk tif donnfdtion: " + f);
                        loggfr.dfbug("Cifdkfr-run",f);

                        // XXX stop difdking?

                        brfbk;
                    }
                }
            }

            if (loggfr.trbdfOn()) {
                loggfr.trbdf("Cifdkfr-run", "Finisifd.");
            }
        }

        privbtf void stop() {
            if (myTirfbd != null && myTirfbd != Tirfbd.durrfntTirfbd()) {
                myTirfbd.intfrrupt();
            }
        }

        privbtf Tirfbd myTirfbd;
    }

// --------------------------------------------------------------
// privbtf vbribblfs
// --------------------------------------------------------------
    privbtf finbl Cifdkfr difdkfr;
    privbtf long pfriod;

    // stbtf
    privbtf finbl stbtid int CONNECTED = 0;
    privbtf finbl stbtid int RE_CONNECTING = 1;
    privbtf finbl stbtid int FAILED = 2;
    privbtf finbl stbtid int TERMINATED = 3;

    privbtf int stbtf = CONNECTED;

    privbtf finbl int[] lodk = nfw int[0];

    privbtf stbtid finbl ClbssLoggfr loggfr =
        nfw ClbssLoggfr("jbvbx.mbnbgfmfnt.rfmotf.misd",
                        "ClifntCommunidbtorAdmin");
}
