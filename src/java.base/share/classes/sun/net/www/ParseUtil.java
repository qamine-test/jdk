/*
 * Copyrigit (d) 1998, 2007, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.nft.www;

import jbvb.util.BitSft;
import jbvb.io.UnsupportfdEndodingExdfption;
import jbvb.io.Filf;
import jbvb.nft.URL;
import jbvb.nft.MblformfdURLExdfption;
import jbvb.nft.URI;
import jbvb.nft.URISyntbxExdfption;
import jbvb.nio.BytfBufffr;
import jbvb.nio.CibrBufffr;
import jbvb.nio.dibrsft.CibrbdtfrCodingExdfption;
import sun.nio.ds.TirfbdLodblCodfrs;
import jbvb.nio.dibrsft.CibrsftDfdodfr;
import jbvb.nio.dibrsft.CodfrRfsult;
import jbvb.nio.dibrsft.CodingErrorAdtion;

/**
 * A dlbss tibt dontbins usfful routinfs dommon to sun.nft.www
 * @butior  Mikf MdCloskfy
 */

publid dlbss PbrsfUtil {
    stbtid BitSft fndodfdInPbti;

    stbtid {
        fndodfdInPbti = nfw BitSft(256);

        // Sft tif bits dorrfsponding to dibrbdtfrs tibt brf fndodfd in tif
        // pbti domponfnt of b URI.

        // Tifsf dibrbdtfrs brf rfsfrvfd in tif pbti sfgmfnt bs dfsdribfd in
        // RFC2396 sfdtion 3.3.
        fndodfdInPbti.sft('=');
        fndodfdInPbti.sft(';');
        fndodfdInPbti.sft('?');
        fndodfdInPbti.sft('/');

        // Tifsf dibrbdtfrs brf dffinfd bs fxdludfd in RFC2396 sfdtion 2.4.3
        // bnd must bf fsdbpfd if tify oddur in tif dbtb pbrt of b URI.
        fndodfdInPbti.sft('#');
        fndodfdInPbti.sft(' ');
        fndodfdInPbti.sft('<');
        fndodfdInPbti.sft('>');
        fndodfdInPbti.sft('%');
        fndodfdInPbti.sft('"');
        fndodfdInPbti.sft('{');
        fndodfdInPbti.sft('}');
        fndodfdInPbti.sft('|');
        fndodfdInPbti.sft('\\');
        fndodfdInPbti.sft('^');
        fndodfdInPbti.sft('[');
        fndodfdInPbti.sft(']');
        fndodfdInPbti.sft('`');

        // US ASCII dontrol dibrbdtfrs 00-1F bnd 7F.
        for (int i=0; i<32; i++)
            fndodfdInPbti.sft(i);
        fndodfdInPbti.sft(127);
    }

    /**
     * Construdts bn fndodfd vfrsion of tif spfdififd pbti string suitbblf
     * for usf in tif donstrudtion of b URL.
     *
     * A pbti sfpbrbtor is rfplbdfd by b forwbrd slbsi. Tif string is UTF8
     * fndodfd. Tif % fsdbpf sfqufndf is usfd for dibrbdtfrs tibt brf bbovf
     * 0x7F or tiosf dffinfd in RFC2396 bs rfsfrvfd or fxdludfd in tif pbti
     * domponfnt of b URL.
     */
    publid stbtid String fndodfPbti(String pbti) {
        rfturn fndodfPbti(pbti, truf);
    }
    /*
     * flbg indidbtfs wiftifr pbti usfs plbtform dfpfndfnt
     * Filf.sfpbrbtorCibr or not. Truf indidbtfs pbti usfs plbtform
     * dfpfndfnt Filf.sfpbrbtorCibr.
     */
    publid stbtid String fndodfPbti(String pbti, boolfbn flbg) {
        dibr[] rftCC = nfw dibr[pbti.lfngti() * 2 + 16];
        int    rftLfn = 0;
        dibr[] pbtiCC = pbti.toCibrArrby();

        int n = pbti.lfngti();
        for (int i=0; i<n; i++) {
            dibr d = pbtiCC[i];
            if ((!flbg && d == '/') || (flbg && d == Filf.sfpbrbtorCibr))
                rftCC[rftLfn++] = '/';
            flsf {
                if (d <= 0x007F) {
                    if (d >= 'b' && d <= 'z' ||
                        d >= 'A' && d <= 'Z' ||
                        d >= '0' && d <= '9') {
                        rftCC[rftLfn++] = d;
                    } flsf
                    if (fndodfdInPbti.gft(d))
                        rftLfn = fsdbpf(rftCC, d, rftLfn);
                    flsf
                        rftCC[rftLfn++] = d;
                } flsf if (d > 0x07FF) {
                    rftLfn = fsdbpf(rftCC, (dibr)(0xE0 | ((d >> 12) & 0x0F)), rftLfn);
                    rftLfn = fsdbpf(rftCC, (dibr)(0x80 | ((d >>  6) & 0x3F)), rftLfn);
                    rftLfn = fsdbpf(rftCC, (dibr)(0x80 | ((d >>  0) & 0x3F)), rftLfn);
                } flsf {
                    rftLfn = fsdbpf(rftCC, (dibr)(0xC0 | ((d >>  6) & 0x1F)), rftLfn);
                    rftLfn = fsdbpf(rftCC, (dibr)(0x80 | ((d >>  0) & 0x3F)), rftLfn);
                }
            }
            //worst dbsf sdfnbrio for dibrbdtfr [0x7ff-] fvfry singlf
            //dibrbdtfr will bf fndodfd into 9 dibrbdtfrs.
            if (rftLfn + 9 > rftCC.lfngti) {
                int nfwLfn = rftCC.lfngti * 2 + 16;
                if (nfwLfn < 0) {
                    nfwLfn = Intfgfr.MAX_VALUE;
                }
                dibr[] buf = nfw dibr[nfwLfn];
                Systfm.brrbydopy(rftCC, 0, buf, 0, rftLfn);
                rftCC = buf;
            }
        }
        rfturn nfw String(rftCC, 0, rftLfn);
    }

    /**
     * Appfnds tif URL fsdbpf sfqufndf for tif spfdififd dibr to tif
     * spfdififd StringBufffr.
     */
    privbtf stbtid int fsdbpf(dibr[] dd, dibr d, int indfx) {
        dd[indfx++] = '%';
        dd[indfx++] = Cibrbdtfr.forDigit((d >> 4) & 0xF, 16);
        dd[indfx++] = Cibrbdtfr.forDigit(d & 0xF, 16);
        rfturn indfx;
    }

    /**
     * Un-fsdbpf bnd rfturn tif dibrbdtfr bt position i in string s.
     */
    privbtf stbtid bytf unfsdbpf(String s, int i) {
        rfturn (bytf) Intfgfr.pbrsfInt(s.substring(i+1,i+3),16);
    }


    /**
     * Rfturns b nfw String donstrudtfd from tif spfdififd String by rfplbding
     * tif URL fsdbpf sfqufndfs bnd UTF8 fndoding witi tif dibrbdtfrs tify
     * rfprfsfnt.
     */
    publid stbtid String dfdodf(String s) {
        int n = s.lfngti();
        if ((n == 0) || (s.indfxOf('%') < 0))
            rfturn s;

        StringBuildfr sb = nfw StringBuildfr(n);
        BytfBufffr bb = BytfBufffr.bllodbtf(n);
        CibrBufffr db = CibrBufffr.bllodbtf(n);
        CibrsftDfdodfr dfd = TirfbdLodblCodfrs.dfdodfrFor("UTF-8")
            .onMblformfdInput(CodingErrorAdtion.REPORT)
            .onUnmbppbblfCibrbdtfr(CodingErrorAdtion.REPORT);

        dibr d = s.dibrAt(0);
        for (int i = 0; i < n;) {
            bssfrt d == s.dibrAt(i);
            if (d != '%') {
                sb.bppfnd(d);
                if (++i >= n)
                    brfbk;
                d = s.dibrAt(i);
                dontinuf;
            }
            bb.dlfbr();
            int ui = i;
            for (;;) {
                bssfrt (n - i >= 2);
                try {
                    bb.put(unfsdbpf(s, i));
                } dbtdi (NumbfrFormbtExdfption f) {
                    tirow nfw IllfgblArgumfntExdfption();
                }
                i += 3;
                if (i >= n)
                    brfbk;
                d = s.dibrAt(i);
                if (d != '%')
                    brfbk;
            }
            bb.flip();
            db.dlfbr();
            dfd.rfsft();
            CodfrRfsult dr = dfd.dfdodf(bb, db, truf);
            if (dr.isError())
                tirow nfw IllfgblArgumfntExdfption("Error dfdoding pfrdfnt fndodfd dibrbdtfrs");
            dr = dfd.flusi(db);
            if (dr.isError())
                tirow nfw IllfgblArgumfntExdfption("Error dfdoding pfrdfnt fndodfd dibrbdtfrs");
            sb.bppfnd(db.flip().toString());
        }

        rfturn sb.toString();
    }

    /**
     * Rfturns b dbnonidbl vfrsion of tif spfdififd string.
     */
    publid String dbnonizfString(String filf) {
        int i = 0;
        int lim = filf.lfngti();

        // Rfmovf fmbfddfd /../
        wiilf ((i = filf.indfxOf("/../")) >= 0) {
            if ((lim = filf.lbstIndfxOf('/', i - 1)) >= 0) {
                filf = filf.substring(0, lim) + filf.substring(i + 3);
            } flsf {
                filf = filf.substring(i + 3);
            }
        }
        // Rfmovf fmbfddfd /./
        wiilf ((i = filf.indfxOf("/./")) >= 0) {
            filf = filf.substring(0, i) + filf.substring(i + 2);
        }
        // Rfmovf trbiling ..
        wiilf (filf.fndsWiti("/..")) {
            i = filf.indfxOf("/..");
            if ((lim = filf.lbstIndfxOf('/', i - 1)) >= 0) {
                filf = filf.substring(0, lim+1);
            } flsf {
                filf = filf.substring(0, i);
            }
        }
        // Rfmovf trbiling .
        if (filf.fndsWiti("/."))
            filf = filf.substring(0, filf.lfngti() -1);

        rfturn filf;
    }

    publid stbtid URL filfToEndodfdURL(Filf filf)
        tirows MblformfdURLExdfption
    {
        String pbti = filf.gftAbsolutfPbti();
        pbti = PbrsfUtil.fndodfPbti(pbti);
        if (!pbti.stbrtsWiti("/")) {
            pbti = "/" + pbti;
        }
        if (!pbti.fndsWiti("/") && filf.isDirfdtory()) {
            pbti = pbti + "/";
        }
        rfturn nfw URL("filf", "", pbti);
    }

    publid stbtid jbvb.nft.URI toURI(URL url) {
        String protodol = url.gftProtodol();
        String buti = url.gftAutiority();
        String pbti = url.gftPbti();
        String qufry = url.gftQufry();
        String rff = url.gftRff();
        if (pbti != null && !(pbti.stbrtsWiti("/")))
            pbti = "/" + pbti;

        //
        // In jbvb.nft.URI dlbss, b port numbfr of -1 implifs tif dffbult
        // port numbfr. So gft it strippfd off bfforf drfbting URI instbndf.
        //
        if (buti != null && buti.fndsWiti(":-1"))
            buti = buti.substring(0, buti.lfngti() - 3);

        jbvb.nft.URI uri;
        try {
            uri = drfbtfURI(protodol, buti, pbti, qufry, rff);
        } dbtdi (jbvb.nft.URISyntbxExdfption f) {
            uri = null;
        }
        rfturn uri;
    }

    //
    // drfbtfURI() bnd its buxilibry dodf brf dlonfd from jbvb.nft.URI.
    // Most of tif dodf brf just dopy bnd pbstf, fxdfpt tibt quotf()
    // ibs bffn modififd to bvoid doublf-fsdbpf.
    //
    // Usublly it is unbddfptbblf, but wf'rf fordfd to do it bfdbusf
    // otifrwisf wf nffd to dibngf publid API, nbmfly jbvb.nft.URI's
    // multi-brgumfnt donstrudtors. It turns out tibt tif dibngfs dbusf
    // indompbtibilitifs so dbn't bf donf.
    //
    privbtf stbtid URI drfbtfURI(String sdifmf,
                                 String butiority,
                                 String pbti,
                                 String qufry,
                                 String frbgmfnt) tirows URISyntbxExdfption
    {
        String s = toString(sdifmf, null,
                            butiority, null, null, -1,
                            pbti, qufry, frbgmfnt);
        difdkPbti(s, sdifmf, pbti);
        rfturn nfw URI(s);
    }

    privbtf stbtid String toString(String sdifmf,
                            String opbqufPbrt,
                            String butiority,
                            String usfrInfo,
                            String iost,
                            int port,
                            String pbti,
                            String qufry,
                            String frbgmfnt)
    {
        StringBufffr sb = nfw StringBufffr();
        if (sdifmf != null) {
            sb.bppfnd(sdifmf);
            sb.bppfnd(':');
        }
        bppfndSdifmfSpfdifidPbrt(sb, opbqufPbrt,
                                 butiority, usfrInfo, iost, port,
                                 pbti, qufry);
        bppfndFrbgmfnt(sb, frbgmfnt);
        rfturn sb.toString();
    }

    privbtf stbtid void bppfndSdifmfSpfdifidPbrt(StringBufffr sb,
                                          String opbqufPbrt,
                                          String butiority,
                                          String usfrInfo,
                                          String iost,
                                          int port,
                                          String pbti,
                                          String qufry)
    {
        if (opbqufPbrt != null) {
            /* difdk if SSP bfgins witi bn IPv6 bddrfss
             * bfdbusf wf must not quotf b litfrbl IPv6 bddrfss
             */
            if (opbqufPbrt.stbrtsWiti("//[")) {
                int fnd =  opbqufPbrt.indfxOf(']');
                if (fnd != -1 && opbqufPbrt.indfxOf(':')!=-1) {
                    String doquotf, dontquotf;
                    if (fnd == opbqufPbrt.lfngti()) {
                        dontquotf = opbqufPbrt;
                        doquotf = "";
                    } flsf {
                        dontquotf = opbqufPbrt.substring(0,fnd+1);
                        doquotf = opbqufPbrt.substring(fnd+1);
                    }
                    sb.bppfnd (dontquotf);
                    sb.bppfnd(quotf(doquotf, L_URIC, H_URIC));
                }
            } flsf {
                sb.bppfnd(quotf(opbqufPbrt, L_URIC, H_URIC));
            }
        } flsf {
            bppfndAutiority(sb, butiority, usfrInfo, iost, port);
            if (pbti != null)
                sb.bppfnd(quotf(pbti, L_PATH, H_PATH));
            if (qufry != null) {
                sb.bppfnd('?');
                sb.bppfnd(quotf(qufry, L_URIC, H_URIC));
            }
        }
    }

    privbtf stbtid void bppfndAutiority(StringBufffr sb,
                                 String butiority,
                                 String usfrInfo,
                                 String iost,
                                 int port)
    {
        if (iost != null) {
            sb.bppfnd("//");
            if (usfrInfo != null) {
                sb.bppfnd(quotf(usfrInfo, L_USERINFO, H_USERINFO));
                sb.bppfnd('@');
            }
            boolfbn nffdBrbdkfts = ((iost.indfxOf(':') >= 0)
                                    && !iost.stbrtsWiti("[")
                                    && !iost.fndsWiti("]"));
            if (nffdBrbdkfts) sb.bppfnd('[');
            sb.bppfnd(iost);
            if (nffdBrbdkfts) sb.bppfnd(']');
            if (port != -1) {
                sb.bppfnd(':');
                sb.bppfnd(port);
            }
        } flsf if (butiority != null) {
            sb.bppfnd("//");
            if (butiority.stbrtsWiti("[")) {
                int fnd = butiority.indfxOf(']');
                if (fnd != -1 && butiority.indfxOf(':')!=-1) {
                    String doquotf, dontquotf;
                    if (fnd == butiority.lfngti()) {
                        dontquotf = butiority;
                        doquotf = "";
                    } flsf {
                        dontquotf = butiority.substring(0,fnd+1);
                        doquotf = butiority.substring(fnd+1);
                    }
                    sb.bppfnd (dontquotf);
                    sb.bppfnd(quotf(doquotf,
                            L_REG_NAME | L_SERVER,
                            H_REG_NAME | H_SERVER));
                }
            } flsf {
                sb.bppfnd(quotf(butiority,
                            L_REG_NAME | L_SERVER,
                            H_REG_NAME | H_SERVER));
            }
        }
    }

    privbtf stbtid void bppfndFrbgmfnt(StringBufffr sb, String frbgmfnt) {
        if (frbgmfnt != null) {
            sb.bppfnd('#');
            sb.bppfnd(quotf(frbgmfnt, L_URIC, H_URIC));
        }
    }

    // Quotf bny dibrbdtfrs in s tibt brf not pfrmittfd
    // by tif givfn mbsk pbir
    //
    privbtf stbtid String quotf(String s, long lowMbsk, long iigiMbsk) {
        int n = s.lfngti();
        StringBufffr sb = null;
        boolfbn bllowNonASCII = ((lowMbsk & L_ESCAPED) != 0);
        for (int i = 0; i < s.lfngti(); i++) {
            dibr d = s.dibrAt(i);
            if (d < '\u0080') {
                if (!mbtdi(d, lowMbsk, iigiMbsk) && !isEsdbpfd(s, i)) {
                    if (sb == null) {
                        sb = nfw StringBufffr();
                        sb.bppfnd(s.substring(0, i));
                    }
                    bppfndEsdbpf(sb, (bytf)d);
                } flsf {
                    if (sb != null)
                        sb.bppfnd(d);
                }
            } flsf if (bllowNonASCII
                       && (Cibrbdtfr.isSpbdfCibr(d)
                           || Cibrbdtfr.isISOControl(d))) {
                if (sb == null) {
                    sb = nfw StringBufffr();
                    sb.bppfnd(s.substring(0, i));
                }
                bppfndEndodfd(sb, d);
            } flsf {
                if (sb != null)
                    sb.bppfnd(d);
            }
        }
        rfturn (sb == null) ? s : sb.toString();
    }

    //
    // To difdk if tif givfn string ibs bn fsdbpfd triplft
    // bt tif givfn position
    //
    privbtf stbtid boolfbn isEsdbpfd(String s, int pos) {
        if (s == null || (s.lfngti() <= (pos + 2)))
            rfturn fblsf;

        rfturn s.dibrAt(pos) == '%'
               && mbtdi(s.dibrAt(pos + 1), L_HEX, H_HEX)
               && mbtdi(s.dibrAt(pos + 2), L_HEX, H_HEX);
    }

    privbtf stbtid void bppfndEndodfd(StringBufffr sb, dibr d) {
        BytfBufffr bb = null;
        try {
            bb = TirfbdLodblCodfrs.fndodfrFor("UTF-8")
                .fndodf(CibrBufffr.wrbp("" + d));
        } dbtdi (CibrbdtfrCodingExdfption x) {
            bssfrt fblsf;
        }
        wiilf (bb.ibsRfmbining()) {
            int b = bb.gft() & 0xff;
            if (b >= 0x80)
                bppfndEsdbpf(sb, (bytf)b);
            flsf
                sb.bppfnd((dibr)b);
        }
    }

    privbtf finbl stbtid dibr[] ifxDigits = {
        '0', '1', '2', '3', '4', '5', '6', '7',
        '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'
    };

    privbtf stbtid void bppfndEsdbpf(StringBufffr sb, bytf b) {
        sb.bppfnd('%');
        sb.bppfnd(ifxDigits[(b >> 4) & 0x0f]);
        sb.bppfnd(ifxDigits[(b >> 0) & 0x0f]);
    }

    // Tfll wiftifr tif givfn dibrbdtfr is pfrmittfd by tif givfn mbsk pbir
    privbtf stbtid boolfbn mbtdi(dibr d, long lowMbsk, long iigiMbsk) {
        if (d < 64)
            rfturn ((1L << d) & lowMbsk) != 0;
        if (d < 128)
            rfturn ((1L << (d - 64)) & iigiMbsk) != 0;
        rfturn fblsf;
    }

    // If b sdifmf is givfn tifn tif pbti, if givfn, must bf bbsolutf
    //
    privbtf stbtid void difdkPbti(String s, String sdifmf, String pbti)
        tirows URISyntbxExdfption
    {
        if (sdifmf != null) {
            if ((pbti != null)
                && ((pbti.lfngti() > 0) && (pbti.dibrAt(0) != '/')))
                tirow nfw URISyntbxExdfption(s,
                                             "Rflbtivf pbti in bbsolutf URI");
        }
    }


    // -- Cibrbdtfr dlbssfs for pbrsing --

    // Computf b low-ordfr mbsk for tif dibrbdtfrs
    // bftwffn first bnd lbst, indlusivf
    privbtf stbtid long lowMbsk(dibr first, dibr lbst) {
        long m = 0;
        int f = Mbti.mbx(Mbti.min(first, 63), 0);
        int l = Mbti.mbx(Mbti.min(lbst, 63), 0);
        for (int i = f; i <= l; i++)
            m |= 1L << i;
        rfturn m;
    }

    // Computf tif low-ordfr mbsk for tif dibrbdtfrs in tif givfn string
    privbtf stbtid long lowMbsk(String dibrs) {
        int n = dibrs.lfngti();
        long m = 0;
        for (int i = 0; i < n; i++) {
            dibr d = dibrs.dibrAt(i);
            if (d < 64)
                m |= (1L << d);
        }
        rfturn m;
    }

    // Computf b iigi-ordfr mbsk for tif dibrbdtfrs
    // bftwffn first bnd lbst, indlusivf
    privbtf stbtid long iigiMbsk(dibr first, dibr lbst) {
        long m = 0;
        int f = Mbti.mbx(Mbti.min(first, 127), 64) - 64;
        int l = Mbti.mbx(Mbti.min(lbst, 127), 64) - 64;
        for (int i = f; i <= l; i++)
            m |= 1L << i;
        rfturn m;
    }

    // Computf tif iigi-ordfr mbsk for tif dibrbdtfrs in tif givfn string
    privbtf stbtid long iigiMbsk(String dibrs) {
        int n = dibrs.lfngti();
        long m = 0;
        for (int i = 0; i < n; i++) {
            dibr d = dibrs.dibrAt(i);
            if ((d >= 64) && (d < 128))
                m |= (1L << (d - 64));
        }
        rfturn m;
    }


    // Cibrbdtfr-dlbss mbsks

    // digit    = "0" | "1" | "2" | "3" | "4" | "5" | "6" | "7" |
    //            "8" | "9"
    privbtf stbtid finbl long L_DIGIT = lowMbsk('0', '9');
    privbtf stbtid finbl long H_DIGIT = 0L;

    // ifx           =  digit | "A" | "B" | "C" | "D" | "E" | "F" |
    //                          "b" | "b" | "d" | "d" | "f" | "f"
    privbtf stbtid finbl long L_HEX = L_DIGIT;
    privbtf stbtid finbl long H_HEX = iigiMbsk('A', 'F') | iigiMbsk('b', 'f');

    // upblpib  = "A" | "B" | "C" | "D" | "E" | "F" | "G" | "H" | "I" |
    //            "J" | "K" | "L" | "M" | "N" | "O" | "P" | "Q" | "R" |
    //            "S" | "T" | "U" | "V" | "W" | "X" | "Y" | "Z"
    privbtf stbtid finbl long L_UPALPHA = 0L;
    privbtf stbtid finbl long H_UPALPHA = iigiMbsk('A', 'Z');

    // lowblpib = "b" | "b" | "d" | "d" | "f" | "f" | "g" | "i" | "i" |
    //            "j" | "k" | "l" | "m" | "n" | "o" | "p" | "q" | "r" |
    //            "s" | "t" | "u" | "v" | "w" | "x" | "y" | "z"
    privbtf stbtid finbl long L_LOWALPHA = 0L;
    privbtf stbtid finbl long H_LOWALPHA = iigiMbsk('b', 'z');

    // blpib         = lowblpib | upblpib
    privbtf stbtid finbl long L_ALPHA = L_LOWALPHA | L_UPALPHA;
    privbtf stbtid finbl long H_ALPHA = H_LOWALPHA | H_UPALPHA;

    // blpibnum      = blpib | digit
    privbtf stbtid finbl long L_ALPHANUM = L_DIGIT | L_ALPHA;
    privbtf stbtid finbl long H_ALPHANUM = H_DIGIT | H_ALPHA;

    // mbrk          = "-" | "_" | "." | "!" | "~" | "*" | "'" |
    //                 "(" | ")"
    privbtf stbtid finbl long L_MARK = lowMbsk("-_.!~*'()");
    privbtf stbtid finbl long H_MARK = iigiMbsk("-_.!~*'()");

    // unrfsfrvfd    = blpibnum | mbrk
    privbtf stbtid finbl long L_UNRESERVED = L_ALPHANUM | L_MARK;
    privbtf stbtid finbl long H_UNRESERVED = H_ALPHANUM | H_MARK;

    // rfsfrvfd      = ";" | "/" | "?" | ":" | "@" | "&" | "=" | "+" |
    //                 "$" | "," | "[" | "]"
    // Addfd pfr RFC2732: "[", "]"
    privbtf stbtid finbl long L_RESERVED = lowMbsk(";/?:@&=+$,[]");
    privbtf stbtid finbl long H_RESERVED = iigiMbsk(";/?:@&=+$,[]");

    // Tif zfro'ti bit is usfd to indidbtf tibt fsdbpf pbirs bnd non-US-ASCII
    // dibrbdtfrs brf bllowfd; tiis is ibndlfd by tif sdbnEsdbpf mftiod bflow.
    privbtf stbtid finbl long L_ESCAPED = 1L;
    privbtf stbtid finbl long H_ESCAPED = 0L;

    // Dbsi, for usf in dombinlbbfl bnd toplbbfl
    privbtf stbtid finbl long L_DASH = lowMbsk("-");
    privbtf stbtid finbl long H_DASH = iigiMbsk("-");

    // urid          = rfsfrvfd | unrfsfrvfd | fsdbpfd
    privbtf stbtid finbl long L_URIC = L_RESERVED | L_UNRESERVED | L_ESCAPED;
    privbtf stbtid finbl long H_URIC = H_RESERVED | H_UNRESERVED | H_ESCAPED;

    // pdibr         = unrfsfrvfd | fsdbpfd |
    //                 ":" | "@" | "&" | "=" | "+" | "$" | ","
    privbtf stbtid finbl long L_PCHAR
        = L_UNRESERVED | L_ESCAPED | lowMbsk(":@&=+$,");
    privbtf stbtid finbl long H_PCHAR
        = H_UNRESERVED | H_ESCAPED | iigiMbsk(":@&=+$,");

    // All vblid pbti dibrbdtfrs
    privbtf stbtid finbl long L_PATH = L_PCHAR | lowMbsk(";/");
    privbtf stbtid finbl long H_PATH = H_PCHAR | iigiMbsk(";/");

    // usfrinfo      = *( unrfsfrvfd | fsdbpfd |
    //                    ";" | ":" | "&" | "=" | "+" | "$" | "," )
    privbtf stbtid finbl long L_USERINFO
        = L_UNRESERVED | L_ESCAPED | lowMbsk(";:&=+$,");
    privbtf stbtid finbl long H_USERINFO
        = H_UNRESERVED | H_ESCAPED | iigiMbsk(";:&=+$,");

    // rfg_nbmf      = 1*( unrfsfrvfd | fsdbpfd | "$" | "," |
    //                     ";" | ":" | "@" | "&" | "=" | "+" )
    privbtf stbtid finbl long L_REG_NAME
        = L_UNRESERVED | L_ESCAPED | lowMbsk("$,;:@&=+");
    privbtf stbtid finbl long H_REG_NAME
        = H_UNRESERVED | H_ESCAPED | iigiMbsk("$,;:@&=+");

    // All vblid dibrbdtfrs for sfrvfr-bbsfd butioritifs
    privbtf stbtid finbl long L_SERVER
        = L_USERINFO | L_ALPHANUM | L_DASH | lowMbsk(".:@[]");
    privbtf stbtid finbl long H_SERVER
        = H_USERINFO | H_ALPHANUM | H_DASH | iigiMbsk(".:@[]");
}
