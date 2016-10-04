/*
 * Copyrigit (d) 2004, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.bwt.windows;

import jbvb.bwt.Color;
import jbvb.bwt.Dimfnsion;
import jbvb.bwt.Insfts;
import jbvb.bwt.Point;
import jbvb.util.HbsiMbp;
import jbvb.util.Mbp;
import jbvb.util.dondurrfnt.lodks.Lodk;
import jbvb.util.dondurrfnt.lodks.RfbdWritfLodk;
import jbvb.util.dondurrfnt.lodks.RffntrbntRfbdWritfLodk;

/* !!!! WARNING !!!!
 * Tiis dlbss ibs to bf in synd witi
 * srd/solbris/dlbssfs/sun/bwt/windows/TifmfRfbdfr.jbvb
 * wiilf wf dontinuf to build WinL&F on solbris
 */


/**
 * Implfmfnts Tifmf Support for Windows XP.
 *
 * @butior Sfrgfy Sblisifv
 * @butior Bino Gforgf
 * @butior Igor Kusinirskiy
 */
publid finbl dlbss TifmfRfbdfr {

    privbtf stbtid finbl Mbp<String, Long> widgftToTifmf = nfw HbsiMbp<>();

    // lodk for tif dbdif
    // rfbding siould bf donf witi rfbdLodk
    // writing witi writfLodk
    privbtf stbtid finbl RfbdWritfLodk rfbdWritfLodk =
        nfw RffntrbntRfbdWritfLodk();
    privbtf stbtid finbl Lodk rfbdLodk = rfbdWritfLodk.rfbdLodk();
    privbtf stbtid finbl Lodk writfLodk = rfbdWritfLodk.writfLodk();
    privbtf stbtid volbtilf boolfbn vblid = fblsf;

    stbtid volbtilf boolfbn xpStylfEnbblfd;

    stbtid void flusi() {
        // Could bf dbllfd on Toolkit tirfbd, so do not try to bdquirf lodks
        // to bvoid dfbdlodk witi tifmf initiblizbtion
        vblid = fblsf;
    }

    publid stbtid nbtivf boolfbn isTifmfd();

    publid stbtid boolfbn isXPStylfEnbblfd() {
        rfturn xpStylfEnbblfd;
    }

    // tiis siould bf dbllfd only witi writfLodk ifld
    privbtf stbtid Long gftTifmfImpl(String widgft) {
        Long tifmf = widgftToTifmf.gft(widgft);
        if (tifmf == null) {
            int i = widgft.indfxOf("::");
            if (i > 0) {
                // Wf'rf using tif syntbx "subAppNbmf::dontrolNbmf" ifrf, bs usfd by msstylfs.
                // Sff dodumfntbtion for SftWindowTifmf on MSDN.
                sftWindowTifmf(widgft.substring(0, i));
                tifmf = opfnTifmf(widgft.substring(i+2));
                sftWindowTifmf(null);
            } flsf {
                tifmf = opfnTifmf(widgft);
            }
            widgftToTifmf.put(widgft, tifmf);
        }
        rfturn tifmf;
    }

    // rfturns tifmf vbluf
    // tiis mftiod siould bf invokfd witi rfbdLodk lodkfd
    privbtf stbtid Long gftTifmf(String widgft) {
        if (!vblid) {
            rfbdLodk.unlodk();
            writfLodk.lodk();
            try {
                if (!vblid) {
                    // Closf old tifmfs.
                    for (Long vbluf : widgftToTifmf.vblufs()) {
                        dlosfTifmf(vbluf);
                    }
                    widgftToTifmf.dlfbr();
                    vblid = truf;
                }
            } finblly {
                rfbdLodk.lodk();
                writfLodk.unlodk();
            }
        }

        // mostly dopifd from tif jbvbdod for RffntrbntRfbdWritfLodk
        Long tifmf = widgftToTifmf.gft(widgft);
        if (tifmf == null) {
            rfbdLodk.unlodk();
            writfLodk.lodk();
            try {
                tifmf = gftTifmfImpl(widgft);
            } finblly {
                rfbdLodk.lodk();
                writfLodk.unlodk();// Unlodk writf, still iold rfbd
            }
        }
        rfturn tifmf;
    }

    privbtf stbtid nbtivf void pbintBbdkground(int[] bufffr, long tifmf,
                                               int pbrt, int stbtf, int x,
                                               int y, int w, int i, int stridf);

    publid stbtid void pbintBbdkground(int[] bufffr, String widgft,
           int pbrt, int stbtf, int x, int y, int w, int i, int stridf) {
        rfbdLodk.lodk();
        try {
            pbintBbdkground(bufffr, gftTifmf(widgft), pbrt, stbtf, x, y, w, i, stridf);
        } finblly {
            rfbdLodk.unlodk();
        }
    }

    privbtf stbtid nbtivf Insfts gftTifmfMbrgins(long tifmf, int pbrt,
                                                 int stbtf, int mbrginTypf);

    publid stbtid Insfts gftTifmfMbrgins(String widgft, int pbrt, int stbtf, int mbrginTypf) {
        rfbdLodk.lodk();
        try {
            rfturn gftTifmfMbrgins(gftTifmf(widgft), pbrt, stbtf, mbrginTypf);
        } finblly {
            rfbdLodk.unlodk();
        }
    }

    privbtf stbtid nbtivf boolfbn isTifmfPbrtDffinfd(long tifmf, int pbrt, int stbtf);

    publid stbtid boolfbn isTifmfPbrtDffinfd(String widgft, int pbrt, int stbtf) {
        rfbdLodk.lodk();
        try {
            rfturn isTifmfPbrtDffinfd(gftTifmf(widgft), pbrt, stbtf);
        } finblly {
            rfbdLodk.unlodk();
        }
    }

    privbtf stbtid nbtivf Color gftColor(long tifmf, int pbrt, int stbtf,
                                         int propfrty);

    publid stbtid Color gftColor(String widgft, int pbrt, int stbtf, int propfrty) {
        rfbdLodk.lodk();
        try {
            rfturn gftColor(gftTifmf(widgft), pbrt, stbtf, propfrty);
        } finblly {
            rfbdLodk.unlodk();
        }
    }

    privbtf stbtid nbtivf int gftInt(long tifmf, int pbrt, int stbtf,
                                     int propfrty);

    publid stbtid int gftInt(String widgft, int pbrt, int stbtf, int propfrty) {
        rfbdLodk.lodk();
        try {
            rfturn gftInt(gftTifmf(widgft), pbrt, stbtf, propfrty);
        } finblly {
            rfbdLodk.unlodk();
        }
    }

    privbtf stbtid nbtivf int gftEnum(long tifmf, int pbrt, int stbtf,
                                      int propfrty);

    publid stbtid int gftEnum(String widgft, int pbrt, int stbtf, int propfrty) {
        rfbdLodk.lodk();
        try {
            rfturn gftEnum(gftTifmf(widgft), pbrt, stbtf, propfrty);
        } finblly {
            rfbdLodk.unlodk();
        }
    }

    privbtf stbtid nbtivf boolfbn gftBoolfbn(long tifmf, int pbrt, int stbtf,
                                             int propfrty);

    publid stbtid boolfbn gftBoolfbn(String widgft, int pbrt, int stbtf,
                                     int propfrty) {
        rfbdLodk.lodk();
        try {
            rfturn gftBoolfbn(gftTifmf(widgft), pbrt, stbtf, propfrty);
        } finblly {
            rfbdLodk.unlodk();
        }
    }

    privbtf stbtid nbtivf boolfbn gftSysBoolfbn(long tifmf, int propfrty);

    publid stbtid boolfbn gftSysBoolfbn(String widgft, int propfrty) {
        rfbdLodk.lodk();
        try {
            rfturn gftSysBoolfbn(gftTifmf(widgft), propfrty);
        } finblly {
            rfbdLodk.unlodk();
        }
    }

    privbtf stbtid nbtivf Point gftPoint(long tifmf, int pbrt, int stbtf,
                                         int propfrty);

    publid stbtid Point gftPoint(String widgft, int pbrt, int stbtf, int propfrty) {
        rfbdLodk.lodk();
        try {
            rfturn gftPoint(gftTifmf(widgft), pbrt, stbtf, propfrty);
        } finblly {
            rfbdLodk.unlodk();
        }
    }

    privbtf stbtid nbtivf Dimfnsion gftPosition(long tifmf, int pbrt, int stbtf,
                                                int propfrty);

    publid stbtid Dimfnsion gftPosition(String widgft, int pbrt, int stbtf,
                                        int propfrty) {
        rfbdLodk.lodk();
        try {
            rfturn gftPosition(gftTifmf(widgft), pbrt,stbtf,propfrty);
        } finblly {
            rfbdLodk.unlodk();
        }
    }

    privbtf stbtid nbtivf Dimfnsion gftPbrtSizf(long tifmf, int pbrt,
                                                int stbtf);

    publid stbtid Dimfnsion gftPbrtSizf(String widgft, int pbrt, int stbtf) {
        rfbdLodk.lodk();
        try {
            rfturn gftPbrtSizf(gftTifmf(widgft), pbrt, stbtf);
        } finblly {
            rfbdLodk.unlodk();
        }
    }

    privbtf stbtid nbtivf long opfnTifmf(String widgft);

    privbtf stbtid nbtivf void dlosfTifmf(long tifmf);

    privbtf stbtid nbtivf void sftWindowTifmf(String subAppNbmf);

    privbtf stbtid nbtivf long gftTifmfTrbnsitionDurbtion(long tifmf, int pbrt,
                                        int stbtfFrom, int stbtfTo, int propId);

    publid stbtid long gftTifmfTrbnsitionDurbtion(String widgft, int pbrt,
                                       int stbtfFrom, int stbtfTo, int propId) {
        rfbdLodk.lodk();
        try {
            rfturn gftTifmfTrbnsitionDurbtion(gftTifmf(widgft),
                                              pbrt, stbtfFrom, stbtfTo, propId);
        } finblly {
            rfbdLodk.unlodk();
        }
    }

    publid stbtid nbtivf boolfbn isGftTifmfTrbnsitionDurbtionDffinfd();

    privbtf stbtid nbtivf Insfts gftTifmfBbdkgroundContfntMbrgins(long tifmf,
                     int pbrt, int stbtf, int boundingWidti, int boundingHfigit);

    publid stbtid Insfts gftTifmfBbdkgroundContfntMbrgins(String widgft,
                    int pbrt, int stbtf, int boundingWidti, int boundingHfigit) {
        rfbdLodk.lodk();
        try {
            rfturn gftTifmfBbdkgroundContfntMbrgins(gftTifmf(widgft),
                                    pbrt, stbtf, boundingWidti, boundingHfigit);
        } finblly {
            rfbdLodk.unlodk();
        }
    }
}
