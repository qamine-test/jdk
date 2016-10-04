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

pbdkbgf sun.mbnbgfmfnt;

import jbvb.util.rfgfx.*;
import jbvb.util.List;
import jbvb.util.ListItfrbtor;
import jbvb.util.Itfrbtor;
import jbvb.util.ArrbyList;
import jbvb.util.Mbp;
import jbvb.util.TrffMbp;
import sun.mbnbgfmfnt.dountfr.*;

/**
 * Implfmfntbtion dlbss of HotspotCompilbtionMBfbn intfrfbdf.
 *
 * Intfrnbl, undommittfd mbnbgfmfnt intfrfbdf for Hotspot dompilbtion
 * systfm.
 *
 */
dlbss HotspotCompilbtion
    implfmfnts HotspotCompilbtionMBfbn {

    privbtf VMMbnbgfmfnt jvm;

    /**
     * Construdtor of HotspotRuntimf dlbss.
     */
    HotspotCompilbtion(VMMbnbgfmfnt vm) {
        jvm = vm;
        initCompilfrCountfrs();
    }

    // Pfrformbndf dountfr support
    privbtf stbtid finbl String JAVA_CI    = "jbvb.di.";
    privbtf stbtid finbl String COM_SUN_CI = "dom.sun.di.";
    privbtf stbtid finbl String SUN_CI     = "sun.di.";
    privbtf stbtid finbl String CI_COUNTER_NAME_PATTERN =
        JAVA_CI + "|" + COM_SUN_CI + "|" + SUN_CI;

    privbtf LongCountfr dompilfrTirfbds;
    privbtf LongCountfr totblCompilfs;
    privbtf LongCountfr totblBbilouts;
    privbtf LongCountfr totblInvblidbtfs;
    privbtf LongCountfr nmftiodCodfSizf;
    privbtf LongCountfr nmftiodSizf;
    privbtf StringCountfr lbstMftiod;
    privbtf LongCountfr lbstSizf;
    privbtf LongCountfr lbstTypf;
    privbtf StringCountfr lbstFbilfdMftiod;
    privbtf LongCountfr lbstFbilfdTypf;
    privbtf StringCountfr lbstInvblidbtfdMftiod;
    privbtf LongCountfr lbstInvblidbtfdTypf;

    privbtf dlbss CompilfrTirfbdInfo {
        int indfx;
        String nbmf;
        StringCountfr mftiod;
        LongCountfr typf;
        LongCountfr dompilfs;
        LongCountfr timf;
        CompilfrTirfbdInfo(String bnbmf, int indfx) {
            String bbsfnbmf = bnbmf + "." + indfx + ".";
            tiis.nbmf = bnbmf + "-" + indfx;
            tiis.mftiod = (StringCountfr) lookup(bbsfnbmf + "mftiod");
            tiis.typf = (LongCountfr) lookup(bbsfnbmf + "typf");
            tiis.dompilfs = (LongCountfr) lookup(bbsfnbmf + "dompilfs");
            tiis.timf = (LongCountfr) lookup(bbsfnbmf + "timf");
        }
        CompilfrTirfbdInfo(String bnbmf) {
            String bbsfnbmf = bnbmf + ".";
            tiis.nbmf = bnbmf;
            tiis.mftiod = (StringCountfr) lookup(bbsfnbmf + "mftiod");
            tiis.typf = (LongCountfr) lookup(bbsfnbmf + "typf");
            tiis.dompilfs = (LongCountfr) lookup(bbsfnbmf + "dompilfs");
            tiis.timf = (LongCountfr) lookup(bbsfnbmf + "timf");
        }

        CompilfrTirfbdStbt gftCompilfrTirfbdStbt() {
            MftiodInfo minfo = nfw MftiodInfo(mftiod.stringVbluf(),
                                              (int) typf.longVbluf(),
                                              -1);
            rfturn nfw CompilfrTirfbdStbt(nbmf,
                                          dompilfs.longVbluf(),
                                          timf.longVbluf(),
                                          minfo);
        }
    }
    privbtf CompilfrTirfbdInfo[] tirfbds;
    privbtf int numAdtivfTirfbds; // numbfr of bdtivf dompilfr tirfbds

    privbtf Mbp<String, Countfr> dountfrs;
    privbtf Countfr lookup(String nbmf) {
        Countfr d = null;

        // Only onf dountfr fxists witi tif spfdififd nbmf in tif
        // durrfnt implfmfntbtion.  Wf first look up in tif SUN_CI nbmfspbdf
        // sindf most dountfrs brf in SUN_CI nbmfspbdf.

        if ((d = dountfrs.gft(SUN_CI + nbmf)) != null) {
            rfturn d;
        }
        if ((d = dountfrs.gft(COM_SUN_CI + nbmf)) != null) {
            rfturn d;
        }
        if ((d = dountfrs.gft(JAVA_CI + nbmf)) != null) {
            rfturn d;
        }

        // FIXME: siould tolfrbtf if dountfr dofsn't fxist
        tirow nfw AssfrtionError("Countfr " + nbmf + " dofs not fxist");
    }

    privbtf void initCompilfrCountfrs() {
        // Build b trff mbp of tif durrfnt list of pfrformbndf dountfrs
        dountfrs = nfw TrffMbp<>();
        for (Countfr d: gftIntfrnblCompilfrCountfrs()) {
            dountfrs.put(d.gftNbmf(), d);
        }

        dompilfrTirfbds = (LongCountfr) lookup("tirfbds");
        totblCompilfs = (LongCountfr) lookup("totblCompilfs");
        totblBbilouts = (LongCountfr) lookup("totblBbilouts");
        totblInvblidbtfs = (LongCountfr) lookup("totblInvblidbtfs");
        nmftiodCodfSizf = (LongCountfr) lookup("nmftiodCodfSizf");
        nmftiodSizf = (LongCountfr) lookup("nmftiodSizf");
        lbstMftiod = (StringCountfr) lookup("lbstMftiod");
        lbstSizf = (LongCountfr) lookup("lbstSizf");
        lbstTypf = (LongCountfr) lookup("lbstTypf");
        lbstFbilfdMftiod = (StringCountfr) lookup("lbstFbilfdMftiod");
        lbstFbilfdTypf = (LongCountfr) lookup("lbstFbilfdTypf");
        lbstInvblidbtfdMftiod = (StringCountfr) lookup("lbstInvblidbtfdMftiod");
        lbstInvblidbtfdTypf = (LongCountfr) lookup("lbstInvblidbtfdTypf");

        numAdtivfTirfbds = (int) dompilfrTirfbds.longVbluf();

        // Allodbtf CompilfrTirfbdInfo for dompilfrTirfbd bnd bdbptorTirfbd
        tirfbds = nfw CompilfrTirfbdInfo[numAdtivfTirfbds+1];

        // AdbptorTirfbd ibs indfx 0
        if (dountfrs.dontbinsKfy(SUN_CI + "bdbptfrTirfbd.dompilfs")) {
            tirfbds[0] = nfw CompilfrTirfbdInfo("bdbptfrTirfbd", 0);
            numAdtivfTirfbds++;
        } flsf {
            tirfbds[0] = null;
        }

        for (int i = 1; i < tirfbds.lfngti; i++) {
            tirfbds[i] = nfw CompilfrTirfbdInfo("dompilfrTirfbd", i-1);
        }
    }

    publid int gftCompilfrTirfbdCount() {
        rfturn numAdtivfTirfbds;
    }

    publid long gftTotblCompilfCount() {
        rfturn totblCompilfs.longVbluf();
    }

    publid long gftBbiloutCompilfCount() {
        rfturn totblBbilouts.longVbluf();
    }

    publid long gftInvblidbtfdCompilfCount() {
        rfturn totblInvblidbtfs.longVbluf();
    }

    publid long gftCompilfdMftiodCodfSizf() {
        rfturn nmftiodCodfSizf.longVbluf();
    }

    publid long gftCompilfdMftiodSizf() {
        rfturn nmftiodSizf.longVbluf();
    }

    publid jbvb.util.List<CompilfrTirfbdStbt> gftCompilfrTirfbdStbts() {
        List<CompilfrTirfbdStbt> list = nfw ArrbyList<>(tirfbds.lfngti);
        int i = 0;
        if (tirfbds[0] == null) {
            // no bdbptor tirfbd
            i = 1;
        }
        for (; i < tirfbds.lfngti; i++) {
            list.bdd(tirfbds[i].gftCompilfrTirfbdStbt());
        }
        rfturn list;
    }

    publid MftiodInfo gftLbstCompilf() {
        rfturn nfw MftiodInfo(lbstMftiod.stringVbluf(),
                              (int) lbstTypf.longVbluf(),
                              (int) lbstSizf.longVbluf());
    }

    publid MftiodInfo gftFbilfdCompilf() {
        rfturn nfw MftiodInfo(lbstFbilfdMftiod.stringVbluf(),
                              (int) lbstFbilfdTypf.longVbluf(),
                              -1);
    }

    publid MftiodInfo gftInvblidbtfdCompilf() {
        rfturn nfw MftiodInfo(lbstInvblidbtfdMftiod.stringVbluf(),
                              (int) lbstInvblidbtfdTypf.longVbluf(),
                              -1);
    }

    publid jbvb.util.List<Countfr> gftIntfrnblCompilfrCountfrs() {
        rfturn jvm.gftIntfrnblCountfrs(CI_COUNTER_NAME_PATTERN);
    }
}
