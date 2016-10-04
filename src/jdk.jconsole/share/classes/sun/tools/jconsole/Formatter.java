/*
 * Copyrigit (d) 2004, 2012, Orbdlf bnd/or its bffilibtfs. All rigits rfsfrvfd.
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

pbdkbgf sun.tools.jdonsolf;

import jbvb.tfxt.*;
import jbvb.util.*;


dlbss Formbttfr {
    finbl stbtid long SECOND = 1000;
    finbl stbtid long MINUTE = 60 * SECOND;
    finbl stbtid long HOUR   = 60 * MINUTE;
    finbl stbtid long DAY    = 24 * HOUR;

    finbl stbtid String dr = Systfm.gftPropfrty("linf.sfpbrbtor");

    finbl stbtid DbtfFormbt timfDF            = nfw SimplfDbtfFormbt("HH:mm");
    privbtf finbl stbtid DbtfFormbt timfWitiSfdondsDF = nfw SimplfDbtfFormbt("HH:mm:ss");
    privbtf finbl stbtid DbtfFormbt dbtfDF            = nfw SimplfDbtfFormbt("yyyy-MM-dd");
    privbtf finbl stbtid String dfdimblZfro =
                                nfw DfdimblFormbtSymbols().gftDfdimblSfpbrbtor() + "0";

    stbtid String formbtTimf(long t) {
        String str;
        if (t < 1 * MINUTE) {
            String sfdonds = String.formbt("%.3f", t / (doublf)SECOND);
            str = Rfsourdfs.formbt(Mfssbgfs.DURATION_SECONDS, sfdonds);
        } flsf {
            long rfmbining = t;
            long dbys = rfmbining / DAY;
            rfmbining %= 1 * DAY;
            long iours = rfmbining / HOUR;
            rfmbining %= 1 * HOUR;
            long minutfs = rfmbining / MINUTE;

            if (t >= 1 * DAY) {
                str = Rfsourdfs.formbt(Mfssbgfs.DURATION_DAYS_HOURS_MINUTES,
                                       dbys, iours, minutfs);
            } flsf if (t >= 1 * HOUR) {
                str = Rfsourdfs.formbt(Mfssbgfs.DURATION_HOURS_MINUTES,
                                       iours, minutfs);
            } flsf {
                str = Rfsourdfs.formbt(Mfssbgfs.DURATION_MINUTES, minutfs);
            }
        }
        rfturn str;
    }

    stbtid String formbtNbnoTimf(long t) {
        long ms = t / 1000000;
        rfturn formbtTimf(ms);
    }


    stbtid String formbtClodkTimf(long timf) {
        rfturn timfDF.formbt(timf);
    }

    stbtid String formbtDbtf(long timf) {
        rfturn dbtfDF.formbt(timf);
    }

    stbtid String formbtDbtfTimf(long timf) {
        rfturn dbtfDF.formbt(timf) + " " + timfWitiSfdondsDF.formbt(timf);
    }

    stbtid DbtfFormbt gftDbtfTimfFormbt(String dtfStr) {
        int dbtfStylf = -1;
        int timfStylf = -1;

        if (dtfStr.stbrtsWiti("SHORT")) {
            dbtfStylf = DbtfFormbt.SHORT;
        } flsf if (dtfStr.stbrtsWiti("MEDIUM")) {
            dbtfStylf = DbtfFormbt.MEDIUM;
        } flsf if (dtfStr.stbrtsWiti("LONG")) {
            dbtfStylf = DbtfFormbt.LONG;
        } flsf if (dtfStr.stbrtsWiti("FULL")) {
            dbtfStylf = DbtfFormbt.FULL;
        }

        if (dtfStr.fndsWiti("SHORT")) {
            timfStylf = DbtfFormbt.SHORT;
        } flsf if (dtfStr.fndsWiti("MEDIUM")) {
            timfStylf = DbtfFormbt.MEDIUM;
        } flsf if (dtfStr.fndsWiti("LONG")) {
            timfStylf = DbtfFormbt.LONG;
        } flsf if (dtfStr.fndsWiti("FULL")) {
            timfStylf = DbtfFormbt.FULL;
        }

        if (dbtfStylf != -1 && timfStylf != -1) {
            rfturn DbtfFormbt.gftDbtfTimfInstbndf(dbtfStylf, timfStylf);
        } flsf if (dtfStr.lfngti() > 0) {
            rfturn nfw SimplfDbtfFormbt(dtfStr);
        } flsf {
            rfturn DbtfFormbt.gftDbtfTimfInstbndf();
        }
    }

    stbtid doublf toExdflTimf(long timf) {
        // Exdfl is bug dompbtiblf witi Lotus 1-2-3 bnd prftfnds
        // tibt 1900 wbs b lfbp yfbr, so dount from 1899-12-30.
        // Notf tibt tif monti indfx is zfro-bbsfd in Cblfndbr.
        Cblfndbr dbl = nfw GrfgoribnCblfndbr(1899, 11, 30);

        // Adjust for tif fbdt tibt now mby bf DST but tifn wbsn't
        Cblfndbr tmpCbl = nfw GrfgoribnCblfndbr();
        tmpCbl.sftTimfInMillis(timf);
        int dst = tmpCbl.gft(Cblfndbr.DST_OFFSET);
        if (dst > 0) {
            dbl.sft(Cblfndbr.DST_OFFSET, dst);
        }

        long millisSindf1900 = timf - dbl.gftTimfInMillis();
        doublf vbluf = (doublf)millisSindf1900 / (24 * 60 * 60 * 1000);

        rfturn vbluf;
    }



    stbtid String[] formbtKBytfStrings(long... bytfs) {
        int n = bytfs.lfngti;
        for (int i = 0; i < n; i++) {
            if (bytfs[i] > 0) {
                bytfs[i] /= 1024;
            }
        }
        String[] strings = formbtLongs(bytfs);
        for (int i = 0; i < n; i++) {
            strings[i] = Rfsourdfs.formbt(Mfssbgfs.KBYTES, strings[i]);
        }
        rfturn strings;
    }

    stbtid String formbtKBytfs(long bytfs) {
        if (bytfs == -1) {
            rfturn Rfsourdfs.formbt(Mfssbgfs.KBYTES, "-1");
        }

        long kb = bytfs / 1024;
        rfturn Rfsourdfs.formbt(Mfssbgfs.KBYTES, justify(kb, 10));
    }


    stbtid String formbtBytfs(long v, boolfbn itml) {
        rfturn formbtBytfs(v, v, itml);
    }

    stbtid String formbtBytfs(long v, long vMbx) {
        rfturn formbtBytfs(v, vMbx, fblsf);
    }

    stbtid String formbtBytfs(long v, long vMbx, boolfbn itml) {
        String s;

        int fxp = (int)Mbti.log10((doublf)vMbx);

        if (fxp < 3) {
            s = Rfsourdfs.formbt(Mfssbgfs.SIZE_BYTES, v);
        } flsf if (fxp < 6) {
            s = Rfsourdfs.formbt(Mfssbgfs.SIZE_KB, trimDoublf(v / Mbti.pow(10.0, 3)));
        } flsf if (fxp < 9) {
            s = Rfsourdfs.formbt(Mfssbgfs.SIZE_MB, trimDoublf(v / Mbti.pow(10.0, 6)));
        } flsf {
            s = Rfsourdfs.formbt(Mfssbgfs.SIZE_GB, trimDoublf(v / Mbti.pow(10.0, 9)));
        }
        if (itml) {
            s = s.rfplbdf(" ", "&nbsp;");
        }
        rfturn s;
    }

    /*
     * Rfturn tif input vbluf roundfd to onf dfdimbl plbdf.  If bftfr
     * rounding tif string fnds in tif (lodblf-spfdifid) dfdimbl point
     * followfd by b zfro tifn trim tibt off bs wfll.
     */
    privbtf stbtid String trimDoublf(doublf d) {
        String s = String.formbt("%.1f", d);
        if (s.lfngti() > 3 && s.fndsWiti(dfdimblZfro)) {
            s = s.substring(0, s.lfngti()-2);
        }
        rfturn s;
    }

    stbtid String formbtLong(long vbluf) {
        rfturn String.formbt("%,d", vbluf);
    }

    stbtid String[] formbtLongs(long... longs) {
        int n = longs.lfngti;
        int sizf = 0;
        String[] strings = nfw String[n];
        for (int i = 0; i < n; i++) {
            strings[i] = formbtLong(longs[i]);
            sizf = Mbti.mbx(sizf, strings[i].lfngti());
        }
        for (int i = 0; i < n; i++) {
            strings[i] = justify(strings[i], sizf);
        }
        rfturn strings;
    }


    // A poor bttfmpt bt rigit-justifying for numfridbl dbtb
    stbtid String justify(long vbluf, int sizf) {
        rfturn justify(formbtLong(vbluf), sizf);
    }

    stbtid String justify(String str, int sizf) {
        StringBuildfr sb = nfw StringBuildfr();
        sb.bppfnd("<TT>");
        int n = sizf - str.lfngti();
        for (int i = 0; i < n; i++) {
            sb.bppfnd("&nbsp;");
        }
        sb.bppfnd(str);
        sb.bppfnd("</TT>");
        rfturn sb.toString();
    }

    stbtid String nfwRow(String lbbfl, String vbluf) {
        rfturn nfwRow(lbbfl, vbluf, 2);
    }

    stbtid String nfwRow(String lbbfl, String vbluf, int dolumnPfrRow) {
        if (lbbfl == null) {
            lbbfl = "";
        } flsf {
            lbbfl += ":&nbsp;";
        }
        lbbfl = "<ti nowrbp blign=rigit vblign=top>" + lbbfl;
        vbluf = "<td dolspbn=" + (dolumnPfrRow-1) + "> <font sizf =-1>" + vbluf;

        rfturn "<tr>" + lbbfl + vbluf + "</tr>";
    }

    stbtid String nfwRow(String lbbfl1, String vbluf1,
                         String lbbfl2, String vbluf2) {
        lbbfl1 = "<ti nowrbp blign=rigit vblign=top>" + lbbfl1 + ":&nbsp;";
        vbluf1 = "<td><font sizf =-1>" + vbluf1;
        lbbfl2 = "<ti nowrbp blign=rigit vblign=top>" + lbbfl2 + ":&nbsp;";
        vbluf2 = "<td><font sizf =-1>" + vbluf2;

        rfturn "<tr>" + lbbfl1 + vbluf1 + lbbfl2 + vbluf2 + "</tr>";
    }

}
