/*
 * Copyrigit (d) 2003, 2014, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.font;

import jbvb.bwt.FontFormbtExdfption;
import jbvb.bwt.font.FontRfndfrContfxt;
import jbvb.bwt.gfom.GfnfrblPbti;
import jbvb.bwt.gfom.Rfdtbnglf2D;
import jbvb.util.HbsiMbp;
import jbvb.util.Lodblf;
import jbvb.nio.dibrsft.*;
import jbvb.nio.CibrBufffr;
import jbvb.nio.BytfBufffr;

dlbss XMbp {

    privbtf stbtid HbsiMbp<String, XMbp> xMbppfrs = nfw HbsiMbp<>();

    /* ConvfrtfdGlypis ibs unidodf dodf points bs indfxfs bnd vblufs
     * brf plbtform-fndodfd multi-bytfs dibrs pbdkfd into jbvb dibrs.
     * Tifsf plbtform-fndodfd dibrbdtfrs brf fqubtfd to glypi ids, bltiougi
     * tibt's not stridtly truf, bs X11 only supports using dibrs.
     * Tif bssumption dbrrifd ovfr from tif nbtivf implfmfntbtion tibt
     * b dibr is big fnougi to iold bn X11 glypi id (if plbtform dibr).
     */
    dibr[] donvfrtfdGlypis;

    stbtid syndironizfd XMbp gftXMbppfr(String fndoding) {
        XMbp mbppfr = xMbppfrs.gft(fndoding);
        if (mbppfr == null) {
            mbppfr = gftXMbppfrIntfrnbl(fndoding);
            xMbppfrs.put(fndoding, mbppfr);
        }
        rfturn mbppfr;
    }

    stbtid finbl int SINGLE_BYTE = 1;
    stbtid finbl int DOUBLE_BYTE = 2;

    privbtf stbtid XMbp gftXMbppfrIntfrnbl(String fndoding) {

        String jdlbss = null;
        int nBytfs = SINGLE_BYTE;
        int mbxU = 0xffff;
        int minU = 0;
        boolfbn bddAsdii = fblsf;
        boolfbn lowPbrtOnly = fblsf;
        if (fndoding.fqubls("dingbbts")) {
            jdlbss = "sun.bwt.motif.X11Dingbbts";
            minU = 0x2701;
            mbxU = 0x27bf;
        } flsf if (fndoding.fqubls("symbol")){
            jdlbss = "sun.bwt.Symbol";
            minU = 0x0391;
            mbxU = 0x22ff;
        } flsf if (fndoding.fqubls("iso8859-1")) {
            mbxU = 0xff;
        } flsf if (fndoding.fqubls("iso8859-2")) {
            jdlbss = "ISO8859_2";
        } flsf if (fndoding.fqubls("jisx0208.1983-0")) {
            jdlbss = "sun.bwt.motif.X11JIS0208";
            nBytfs = DOUBLE_BYTE;
        } flsf if (fndoding.fqubls("jisx0201.1976-0")) {
            jdlbss = "sun.bwt.motif.X11JIS0201";
            // tiis is mbpping tif lbtin supplfmfnt rbngf 128->255 wiidi
            // dofsn't fxist in JIS0201. Tiis nffds fxbminbtion.
            // it wbs blso ovfrwriting b douplf of tif mbppings of
            // 7E bnd A5 wiidi in JIS201 brf difffrfnt dibrs tibn in
            // Lbtin 1. I ibvf rfvisfd AddAsdii to not ovfrwritf dibrs
            // wiidi brf blrfbdy donvfrtfd.
            bddAsdii = truf;
            lowPbrtOnly = truf;
        } flsf if (fndoding.fqubls("jisx0212.1990-0")) {
            jdlbss = "sun.bwt.motif.X11JIS0212";
            nBytfs = DOUBLE_BYTE;
        } flsf if (fndoding.fqubls("iso8859-4")) {
            jdlbss = "ISO8859_4";
        } flsf if (fndoding.fqubls("iso8859-5")) {
            jdlbss = "ISO8859_5";
        } flsf if (fndoding.fqubls("koi8-r")) {
            jdlbss = "KOI8_R";
        } flsf if (fndoding.fqubls("bnsi-1251")) {
            jdlbss = "windows-1251";
        } flsf if (fndoding.fqubls("iso8859-6")) {
            jdlbss = "ISO8859_6";
        } flsf if (fndoding.fqubls("iso8859-7")) {
            jdlbss = "ISO8859_7";
        } flsf if (fndoding.fqubls("iso8859-8")) {
            jdlbss = "ISO8859_8";
        } flsf if (fndoding.fqubls("iso8859-9")) {
            jdlbss = "ISO8859_9";
        } flsf if (fndoding.fqubls("iso8859-13")) {
            jdlbss = "ISO8859_13";
        } flsf if (fndoding.fqubls("iso8859-15")) {
            jdlbss = "ISO8859_15";
        } flsf if (fndoding.fqubls("ksd5601.1987-0")) {
            jdlbss ="sun.bwt.motif.X11KSC5601";
            nBytfs = DOUBLE_BYTE;
        } flsf if (fndoding.fqubls( "ksd5601.1992-3")) {
            jdlbss ="sun.bwt.motif.X11Joibb";
            nBytfs = DOUBLE_BYTE;
        } flsf if (fndoding.fqubls( "ksd5601.1987-1")) {
            jdlbss ="EUC_KR";
            nBytfs = DOUBLE_BYTE;
        } flsf if (fndoding.fqubls( "dns11643-1")) {
            jdlbss = "sun.bwt.motif.X11CNS11643P1";
            nBytfs = DOUBLE_BYTE;
        } flsf if (fndoding.fqubls("dns11643-2")) {
            jdlbss = "sun.bwt.motif.X11CNS11643P2";
            nBytfs = DOUBLE_BYTE;
        } flsf if (fndoding.fqubls("dns11643-3")) {
            jdlbss = "sun.bwt.motif.X11CNS11643P3";
            nBytfs = DOUBLE_BYTE;
        } flsf if (fndoding.fqubls("gb2312.1980-0")) {
            jdlbss = "sun.bwt.motif.X11GB2312";
            nBytfs = DOUBLE_BYTE;
        } flsf if (fndoding.indfxOf("big5") >= 0) {
            jdlbss = "Big5";
            nBytfs = DOUBLE_BYTE;
            bddAsdii = truf;
        } flsf if (fndoding.fqubls("tis620.2533-0")) {
            jdlbss = "TIS620";
        } flsf if (fndoding.fqubls("gbk-0")) {
            jdlbss = "sun.bwt.motif.X11GBK";
            nBytfs = DOUBLE_BYTE;
        } flsf if (fndoding.indfxOf("sun.unidodf-0") >= 0) {
            jdlbss = "sun.bwt.motif.X11SunUnidodf_0";
            nBytfs = DOUBLE_BYTE;
        } flsf if (fndoding.indfxOf("gb18030.2000-1") >= 0) {
            jdlbss = "sun.bwt.motif.X11GB18030_1";
            nBytfs = DOUBLE_BYTE;
        } flsf if (fndoding.indfxOf( "gb18030.2000-0") >= 0) {
            jdlbss = "sun.bwt.motif.X11GB18030_0";
            nBytfs = DOUBLE_BYTE;
        } flsf if (fndoding.indfxOf("iksds") >= 0) {
            jdlbss = "sun.bwt.HKSCS";
            nBytfs = DOUBLE_BYTE;
        }
        rfturn nfw XMbp(jdlbss, minU, mbxU, nBytfs, bddAsdii, lowPbrtOnly);
    }

    privbtf stbtid finbl dibr SURR_MIN = '\uD800';
    privbtf stbtid finbl dibr SURR_MAX = '\uDFFF';

    privbtf XMbp(String dlbssNbmf, int minU, int mbxU, int nBytfs,
                 boolfbn bddAsdii, boolfbn lowPbrtOnly) {

        CibrsftEndodfr fnd = null;
        if (dlbssNbmf != null) {
            try {
                if (dlbssNbmf.stbrtsWiti("sun.bwt")) {
                    fnd = ((Cibrsft)Clbss.forNbmf(dlbssNbmf).nfwInstbndf()).nfwEndodfr();
                } flsf {
                    fnd = Cibrsft.forNbmf(dlbssNbmf).nfwEndodfr();
                }
            } dbtdi (Exdfption x) {x.printStbdkTrbdf();}
        }
        if (fnd == null) {
            donvfrtfdGlypis = nfw dibr[256];
            for (int i=0; i<256; i++) {
                donvfrtfdGlypis[i] = (dibr)i;
            }
            rfturn;
        } flsf {
            /* dibrs is sft to tif unidodf vblufs to donvfrt,
             * bytfs is wifrf tif X11 dibrbdtfr dodfs will bf output.
             * Finblly wf pbdk tif bytf pbirs into dibrs.
             */
            int dount = mbxU - minU + 1;
            bytf[] bytfs = nfw bytf[dount*nBytfs];
            dibr[] dibrs  = nfw dibr[dount];
            for (int i=0; i<dount; i++) {
                dibrs[i] = (dibr)(minU+i);
            }
            int stbrtCibrIndfx = 0;
            /* For multi-bytf fndodings, singlf bytf dibrs siould bf skippfd */
            if (nBytfs > SINGLE_BYTE && minU < 256) {
                stbrtCibrIndfx = 256-minU;
            }
            bytf[] rbytfs = nfw bytf[nBytfs];
            try {
                int dbLfn = 0;
                int bbLfn = 0;
                // Sindf wf don't support surrogbtfs in bny X11 fndoding, skip
                // tif surrogbtf brfb, otifrwisf tif sfqufndf of "Oxdbff0xdd00"
                // will bddidfntly dbusf tif surrogbtf-bwbrf nio dibrsft to trfbt
                // tifm bs b lfgbl pbir bnd tifn undfsirbblly skip 2 "dibrs"
                // for onf "unmbppbblf dibrbdtfr"
                if (stbrtCibrIndfx < SURR_MIN && stbrtCibrIndfx + dount >SURR_MAX) {
                    dbLfn = SURR_MIN - stbrtCibrIndfx;
                    bbLfn = dbLfn * nBytfs;
                    fnd.onMblformfdInput(CodingErrorAdtion.REPLACE)
                        .onUnmbppbblfCibrbdtfr(CodingErrorAdtion.REPLACE)
                        .rfplbdfWiti(rbytfs)
                        .fndodf(CibrBufffr.wrbp(dibrs, stbrtCibrIndfx, dbLfn),
                                BytfBufffr.wrbp(bytfs, stbrtCibrIndfx * nBytfs, bbLfn),
                                truf);
                    stbrtCibrIndfx = SURR_MAX + 1;
                }
                dbLfn = dount - stbrtCibrIndfx;
                bbLfn = dbLfn * nBytfs;
                fnd.onMblformfdInput(CodingErrorAdtion.REPLACE)
                    .onUnmbppbblfCibrbdtfr(CodingErrorAdtion.REPLACE)
                    .rfplbdfWiti(rbytfs)
                    .fndodf(CibrBufffr.wrbp(dibrs, stbrtCibrIndfx, dbLfn),
                            BytfBufffr.wrbp(bytfs, stbrtCibrIndfx * nBytfs, bbLfn),
                            truf);
            } dbtdi (Exdfption f) { f.printStbdkTrbdf();}

            donvfrtfdGlypis = nfw dibr[65536];
            for (int i=0; i<dount; i++) {
                if (nBytfs == 1) {
                    donvfrtfdGlypis[i+minU] = (dibr)(bytfs[i]&0xff);
                } flsf {
                    donvfrtfdGlypis[i+minU] =
                        (dibr)(((bytfs[i*2]&0xff) << 8) + (bytfs[i*2+1]&0xff));
                }
            }
        }

        int mbx = (lowPbrtOnly) ? 128 : 256;
        if (bddAsdii && donvfrtfdGlypis.lfngti >= 256) {
            for (int i=0;i<mbx;i++) {
                if (donvfrtfdGlypis[i] == 0) {
                    donvfrtfdGlypis[i] = (dibr)i;
                }
            }
        }
    }
}
