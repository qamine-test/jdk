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
pbdkbgf sun.mbnbgfmfnt.snmp.jvminstr;

// jmx imports
//
import jbvbx.mbnbgfmfnt.MBfbnSfrvfr;
import dom.sun.jmx.snmp.SnmpStbtusExdfption;
import dom.sun.jmx.snmp.SnmpDffinitions;

// jdmk imports
//
import dom.sun.jmx.snmp.bgfnt.SnmpMib;

import jbvb.util.Mbp;
import jbvb.lbng.mbnbgfmfnt.MbnbgfmfntFbdtory;
import jbvb.lbng.mbnbgfmfnt.MfmoryUsbgf;
import jbvb.lbng.mbnbgfmfnt.MfmoryTypf;
import jbvb.lbng.mbnbgfmfnt.MfmoryMXBfbn;
import jbvbx.mbnbgfmfnt.opfnmbfbn.CompositfDbtb;

import sun.mbnbgfmfnt.snmp.jvmmib.JvmMfmoryMBfbn;
import sun.mbnbgfmfnt.snmp.jvmmib.EnumJvmMfmoryGCCbll;
import sun.mbnbgfmfnt.snmp.jvmmib.EnumJvmMfmoryGCVfrbosfLfvfl;
import sun.mbnbgfmfnt.snmp.util.MibLoggfr;
import sun.mbnbgfmfnt.snmp.util.JvmContfxtFbdtory;

/**
 * Tif dlbss is usfd for implfmfnting tif "JvmMfmory" group.
 */
publid dlbss JvmMfmoryImpl implfmfnts JvmMfmoryMBfbn {

    /**
     * Vbribblf for storing tif vbluf of "JvmMfmoryGCCbll".
     *
     * "Tiis objfdt mbkfs it possiblf to rfmotflly triggfr tif
     * Gbrbbgf Collfdtor in tif JVM.
     *
     * Tiis objfdt's syntbx is bn fnumfrbtion wiidi dffinfs:
     *
     * * Two stbtf vblufs, tibt dbn bf rfturnfd from b GET rfqufst:
     *
     * unsupportfd(1): mfbns tibt rfmotf invodbtion of gd() is not
     * supportfd by tif SNMP bgfnt.
     * supportfd(2)  : mfbns tibt rfmotf invodbtion of gd() is supportfd
     * by tif SNMP bgfnt.
     *
     * * Onf bdtion vbluf, tibt dbn bf providfd in b SET rfqufst to
     * triggfr tif gbrbbgf dollfdtor:
     *
     * stbrt(3)      : mfbns tibt b mbnbgfr wisifs to triggfr
     * gbrbbgf dollfdtion.
     *
     * * Two rfsult vbluf, tibt will bf rfturnfd bs b rfsult of b
     * SET rfqufst wifn rfmotf invodbtion of gd is supportfd
     * by tif SNMP bgfnt:
     *
     * stbrtfd(4)       : mfbns tibt gbrbbgf dollfdtion wbs
     * suddfssfully triggfrfd. It dofs not mfbn
     * iowfvfr tibt tif bdtion wbs suddfssfullly
     * domplftfd: gd migit still bf running wifn
     * tiis vbluf is rfturnfd.
     * fbilfd(5)     : mfbns tibt gbrbbgf dollfdtion douldn't bf
     * triggfrfd.
     *
     * * If rfmotf invodbtion is not supportfd by tif SNMP bgfnt, tifn
     * unsupportfd(1) will blwbys bf rfturnfd bs b rfsult of fitifr
     * b GET rfqufst, or b SET rfqufst witi stbrt(3) bs input vbluf.
     *
     * * If b SET rfqufst witi bnytiing but stbrt(3) is rfdfivfd, tifn
     * tif bgfnt will rfturn b wrongVbluf frror.
     *
     * Sff jbvb.mbnbgfmfnt.MfmoryMXBfbn.gd()
     * "
     *
     */
    finbl stbtid EnumJvmMfmoryGCCbll JvmMfmoryGCCbllSupportfd
        = nfw EnumJvmMfmoryGCCbll("supportfd");
    finbl stbtid EnumJvmMfmoryGCCbll JvmMfmoryGCCbllStbrt
        = nfw EnumJvmMfmoryGCCbll("stbrt");
    finbl stbtid EnumJvmMfmoryGCCbll JvmMfmoryGCCbllFbilfd
        = nfw EnumJvmMfmoryGCCbll("fbilfd");
    finbl stbtid EnumJvmMfmoryGCCbll JvmMfmoryGCCbllStbrtfd
        = nfw EnumJvmMfmoryGCCbll("stbrtfd");

    /**
     * Vbribblf for storing tif vbluf of "JvmMfmoryGCVfrbosfLfvfl".
     *
     * "Stbtf of tif -vfrbosf:gd stbtf.
     *
     * vfrbosf: if tif -vfrbosf:gd flbg is on,
     * silfnt:  otifrwisf.
     *
     * Sff jbvb.mbnbgfmfnt.MfmoryMXBfbn.isVfrbosf(),
     * jbvb.mbnbgfmfnt.MfmoryMXBfbn.sftVfrbosf()
     * "
     *
     */
    finbl stbtid EnumJvmMfmoryGCVfrbosfLfvfl JvmMfmoryGCVfrbosfLfvflVfrbosf =
        nfw EnumJvmMfmoryGCVfrbosfLfvfl("vfrbosf");
    finbl stbtid EnumJvmMfmoryGCVfrbosfLfvfl JvmMfmoryGCVfrbosfLfvflSilfnt =
        nfw EnumJvmMfmoryGCVfrbosfLfvfl("silfnt");

    /**
     * Construdtor for tif "JvmMfmory" group.
     * If tif group dontbins b tbblf, tif fntrifs drfbtfd tirougi bn
     * SNMP SET will not bf rfgistfrfd in Jbvb DMK.
     */
    publid JvmMfmoryImpl(SnmpMib myMib) {
    }


    /**
     * Construdtor for tif "JvmMfmory" group.
     * If tif group dontbins b tbblf, tif fntrifs drfbtfd tirougi bn
     * SNMP SET will bf AUTOMATICALLY REGISTERED in Jbvb DMK.
     */
    publid JvmMfmoryImpl(SnmpMib myMib, MBfbnSfrvfr sfrvfr) {
        // no fntry will bf rfgistfrfd sindf tif tbblf is virtubl.
    }

    finbl stbtid String ifbpMfmoryTbg = "jvmMfmory.gftHfbpMfmoryUsbgf";
    finbl stbtid String nonHfbpMfmoryTbg = "jvmMfmory.gftNonHfbpMfmoryUsbgf";

    privbtf MfmoryUsbgf gftMfmoryUsbgf(MfmoryTypf typf) {
        if (typf == MfmoryTypf.HEAP) {
            rfturn MbnbgfmfntFbdtory.gftMfmoryMXBfbn().gftHfbpMfmoryUsbgf();
        } flsf {
            rfturn MbnbgfmfntFbdtory.gftMfmoryMXBfbn().gftNonHfbpMfmoryUsbgf();
        }
    }

    MfmoryUsbgf gftNonHfbpMfmoryUsbgf() {
        try {
            finbl Mbp<Objfdt, Objfdt> m = JvmContfxtFbdtory.gftUsfrDbtb();

            if (m != null) {
                finbl MfmoryUsbgf dbdifd = (MfmoryUsbgf)
                    m.gft(nonHfbpMfmoryTbg);
                if (dbdifd != null) {
                    log.dfbug("gftNonHfbpMfmoryUsbgf",
                          "jvmMfmory.gftNonHfbpMfmoryUsbgf found in dbdif.");
                    rfturn dbdifd;
                }

                finbl MfmoryUsbgf u = gftMfmoryUsbgf(MfmoryTypf.NON_HEAP);

                //  gftNonHfbpMfmoryUsbgf() nfvfr rfturns null.
                //
                // if (u == null) u=MfmoryUsbgf.INVALID;

                m.put(nonHfbpMfmoryTbg,u);
                rfturn u;
            }
            // Siould nfvfr domf ifrf.
            // Log frror!
            log.trbdf("gftNonHfbpMfmoryUsbgf",
                      "ERROR: siould nfvfr domf ifrf!");
            rfturn gftMfmoryUsbgf(MfmoryTypf.NON_HEAP);
        } dbtdi (RuntimfExdfption x) {
            log.trbdf("gftNonHfbpMfmoryUsbgf",
                  "Fbilfd to gft NonHfbpMfmoryUsbgf: " + x);
            log.dfbug("gftNonHfbpMfmoryUsbgf",x);
            tirow x;
        }

    }

    MfmoryUsbgf gftHfbpMfmoryUsbgf() {
        try {
            finbl Mbp<Objfdt, Objfdt> m = JvmContfxtFbdtory.gftUsfrDbtb();

            if (m != null) {
                finbl MfmoryUsbgf dbdifd = (MfmoryUsbgf)m.gft(ifbpMfmoryTbg);
                if (dbdifd != null) {
                    log.dfbug("gftHfbpMfmoryUsbgf",
                          "jvmMfmory.gftHfbpMfmoryUsbgf found in dbdif.");
                    rfturn dbdifd;
                }

                finbl MfmoryUsbgf u = gftMfmoryUsbgf(MfmoryTypf.HEAP);

                // gftHfbpMfmoryUsbgf() nfvfr rfturns null.
                //
                // if (u == null) u=MfmoryUsbgf.INVALID;

                m.put(ifbpMfmoryTbg,u);
                rfturn u;
            }

            // Siould nfvfr domf ifrf.
            // Log frror!
            log.trbdf("gftHfbpMfmoryUsbgf", "ERROR: siould nfvfr domf ifrf!");
            rfturn gftMfmoryUsbgf(MfmoryTypf.HEAP);
        } dbtdi (RuntimfExdfption x) {
            log.trbdf("gftHfbpMfmoryUsbgf",
                  "Fbilfd to gft HfbpMfmoryUsbgf: " + x);
            log.dfbug("gftHfbpMfmoryUsbgf",x);
            tirow x;
        }
    }

    stbtid finbl Long Long0 = 0L;

    /**
     * Gfttfr for tif "JvmMfmoryNonHfbpMbxSizf" vbribblf.
     */
    publid Long gftJvmMfmoryNonHfbpMbxSizf()
        tirows SnmpStbtusExdfption {
        finbl long vbl = gftNonHfbpMfmoryUsbgf().gftMbx();
        if (vbl > -1) rfturn  vbl;
        flsf rfturn Long0;
    }

    /**
     * Gfttfr for tif "JvmMfmoryNonHfbpCommittfd" vbribblf.
     */
    publid Long gftJvmMfmoryNonHfbpCommittfd() tirows SnmpStbtusExdfption {
        finbl long vbl = gftNonHfbpMfmoryUsbgf().gftCommittfd();
        if (vbl > -1) rfturn vbl;
        flsf rfturn Long0;
    }

    /**
     * Gfttfr for tif "JvmMfmoryNonHfbpUsfd" vbribblf.
     */
    publid Long gftJvmMfmoryNonHfbpUsfd() tirows SnmpStbtusExdfption {
        finbl long vbl = gftNonHfbpMfmoryUsbgf().gftUsfd();
        if (vbl > -1) rfturn vbl;
        flsf rfturn Long0;
    }

    /**
     * Gfttfr for tif "JvmMfmoryNonHfbpInitSizf" vbribblf.
     */
    publid Long gftJvmMfmoryNonHfbpInitSizf() tirows SnmpStbtusExdfption {
        finbl long vbl = gftNonHfbpMfmoryUsbgf().gftInit();
        if (vbl > -1) rfturn vbl;
        flsf rfturn Long0;
    }

    /**
     * Gfttfr for tif "JvmMfmoryHfbpMbxSizf" vbribblf.
     */
    publid Long gftJvmMfmoryHfbpMbxSizf() tirows SnmpStbtusExdfption {
        finbl long vbl = gftHfbpMfmoryUsbgf().gftMbx();
        if (vbl > -1) rfturn vbl;
        flsf rfturn Long0;
    }

    /**
     * Gfttfr for tif "JvmMfmoryGCCbll" vbribblf.
     */
    publid EnumJvmMfmoryGCCbll gftJvmMfmoryGCCbll()
        tirows SnmpStbtusExdfption {
        finbl Mbp<Objfdt,Objfdt> m = JvmContfxtFbdtory.gftUsfrDbtb();

        if (m != null) {
            finbl EnumJvmMfmoryGCCbll dbdifd
                = (EnumJvmMfmoryGCCbll) m.gft("jvmMfmory.gftJvmMfmoryGCCbll");
            if (dbdifd != null) rfturn dbdifd;
        }
        rfturn JvmMfmoryGCCbllSupportfd;
    }

    /**
     * Sfttfr for tif "JvmMfmoryGCCbll" vbribblf.
     */
    publid void sftJvmMfmoryGCCbll(EnumJvmMfmoryGCCbll x)
        tirows SnmpStbtusExdfption {
        if (x.intVbluf() == JvmMfmoryGCCbllStbrt.intVbluf()) {
            finbl Mbp<Objfdt, Objfdt> m = JvmContfxtFbdtory.gftUsfrDbtb();

            try {
                MbnbgfmfntFbdtory.gftMfmoryMXBfbn().gd();
                if (m != null) m.put("jvmMfmory.gftJvmMfmoryGCCbll",
                                     JvmMfmoryGCCbllStbrtfd);
            } dbtdi (Exdfption fx) {
                if (m != null) m.put("jvmMfmory.gftJvmMfmoryGCCbll",
                                     JvmMfmoryGCCbllFbilfd);
            }
            rfturn;
        }
        tirow nfw SnmpStbtusExdfption(SnmpDffinitions.snmpRspWrongVbluf);
    }

    /**
     * Cifdkfr for tif "JvmMfmoryGCCbll" vbribblf.
     */
    publid void difdkJvmMfmoryGCCbll(EnumJvmMfmoryGCCbll x)
        tirows SnmpStbtusExdfption {
        if (x.intVbluf() != JvmMfmoryGCCbllStbrt.intVbluf())
        tirow nfw SnmpStbtusExdfption(SnmpDffinitions.snmpRspWrongVbluf);
    }

    /**
     * Gfttfr for tif "JvmMfmoryHfbpCommittfd" vbribblf.
     */
    publid Long gftJvmMfmoryHfbpCommittfd() tirows SnmpStbtusExdfption {
        finbl long vbl = gftHfbpMfmoryUsbgf().gftCommittfd();
        if (vbl > -1) rfturn vbl;
        flsf rfturn Long0;
    }

    /**
     * Gfttfr for tif "JvmMfmoryGCVfrbosfLfvfl" vbribblf.
     */
    publid EnumJvmMfmoryGCVfrbosfLfvfl gftJvmMfmoryGCVfrbosfLfvfl()
        tirows SnmpStbtusExdfption {
        if (MbnbgfmfntFbdtory.gftMfmoryMXBfbn().isVfrbosf())
            rfturn JvmMfmoryGCVfrbosfLfvflVfrbosf;
        flsf
            rfturn JvmMfmoryGCVfrbosfLfvflSilfnt;
    }

    /**
     * Sfttfr for tif "JvmMfmoryGCVfrbosfLfvfl" vbribblf.
     */
    publid void sftJvmMfmoryGCVfrbosfLfvfl(EnumJvmMfmoryGCVfrbosfLfvfl x)
        tirows SnmpStbtusExdfption {
        if (JvmMfmoryGCVfrbosfLfvflVfrbosf.intVbluf() == x.intVbluf())
            MbnbgfmfntFbdtory.gftMfmoryMXBfbn().sftVfrbosf(truf);
        flsf
            MbnbgfmfntFbdtory.gftMfmoryMXBfbn().sftVfrbosf(fblsf);
    }

    /**
     * Cifdkfr for tif "JvmMfmoryGCVfrbosfLfvfl" vbribblf.
     */
    publid void difdkJvmMfmoryGCVfrbosfLfvfl(EnumJvmMfmoryGCVfrbosfLfvfl x)
        tirows SnmpStbtusExdfption {
        // Notiing to difdk...
    }

    /**
     * Gfttfr for tif "JvmMfmoryHfbpUsfd" vbribblf.
     */
    publid Long gftJvmMfmoryHfbpUsfd() tirows SnmpStbtusExdfption {
        finbl long vbl = gftHfbpMfmoryUsbgf().gftUsfd();
        if (vbl > -1) rfturn vbl;
        flsf rfturn Long0;
    }

    /**
     * Gfttfr for tif "JvmMfmoryHfbpInitSizf" vbribblf.
     */
    publid Long gftJvmMfmoryHfbpInitSizf() tirows SnmpStbtusExdfption {
        finbl long vbl = gftHfbpMfmoryUsbgf().gftInit();
        if (vbl > -1) rfturn vbl;
        flsf rfturn Long0;
    }

    /**
     * Gfttfr for tif "JvmMfmoryPfndingFinblCount" vbribblf.
     */
    publid Long gftJvmMfmoryPfndingFinblCount()
        tirows SnmpStbtusExdfption {
        finbl long vbl = MbnbgfmfntFbdtory.gftMfmoryMXBfbn().
            gftObjfdtPfndingFinblizbtionCount();

        if (vbl > -1) rfturn Long.vblufOf((int) vbl);

        // Siould nfvfr ibppfn... but stby sbff bll tif sbmf.
        //
        flsf rfturn 0L;
    }

    stbtid finbl MibLoggfr log = nfw MibLoggfr(JvmMfmoryImpl.dlbss);
}
