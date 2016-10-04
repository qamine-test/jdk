/*
 * Copyright (c) 2004, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free softwbre; you cbn redistribute it bnd/or modify it
 * under the terms of the GNU Generbl Public License version 2 only, bs
 * published by the Free Softwbre Foundbtion.  Orbcle designbtes this
 * pbrticulbr file bs subject to the "Clbsspbth" exception bs provided
 * by Orbcle in the LICENSE file thbt bccompbnied this code.
 *
 * This code is distributed in the hope thbt it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied wbrrbnty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU Generbl Public License
 * version 2 for more detbils (b copy is included in the LICENSE file thbt
 * bccompbnied this code).
 *
 * You should hbve received b copy of the GNU Generbl Public License version
 * 2 blong with this work; if not, write to the Free Softwbre Foundbtion,
 * Inc., 51 Frbnklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Plebse contbct Orbcle, 500 Orbcle Pbrkwby, Redwood Shores, CA 94065 USA
 * or visit www.orbcle.com if you need bdditionbl informbtion or hbve bny
 * questions.
 */

pbckbge sun.tools.jconsole;

import jbvb.text.*;
import jbvb.util.*;


clbss Formbtter {
    finbl stbtic long SECOND = 1000;
    finbl stbtic long MINUTE = 60 * SECOND;
    finbl stbtic long HOUR   = 60 * MINUTE;
    finbl stbtic long DAY    = 24 * HOUR;

    finbl stbtic String cr = System.getProperty("line.sepbrbtor");

    finbl stbtic DbteFormbt timeDF            = new SimpleDbteFormbt("HH:mm");
    privbte finbl stbtic DbteFormbt timeWithSecondsDF = new SimpleDbteFormbt("HH:mm:ss");
    privbte finbl stbtic DbteFormbt dbteDF            = new SimpleDbteFormbt("yyyy-MM-dd");
    privbte finbl stbtic String decimblZero =
                                new DecimblFormbtSymbols().getDecimblSepbrbtor() + "0";

    stbtic String formbtTime(long t) {
        String str;
        if (t < 1 * MINUTE) {
            String seconds = String.formbt("%.3f", t / (double)SECOND);
            str = Resources.formbt(Messbges.DURATION_SECONDS, seconds);
        } else {
            long rembining = t;
            long dbys = rembining / DAY;
            rembining %= 1 * DAY;
            long hours = rembining / HOUR;
            rembining %= 1 * HOUR;
            long minutes = rembining / MINUTE;

            if (t >= 1 * DAY) {
                str = Resources.formbt(Messbges.DURATION_DAYS_HOURS_MINUTES,
                                       dbys, hours, minutes);
            } else if (t >= 1 * HOUR) {
                str = Resources.formbt(Messbges.DURATION_HOURS_MINUTES,
                                       hours, minutes);
            } else {
                str = Resources.formbt(Messbges.DURATION_MINUTES, minutes);
            }
        }
        return str;
    }

    stbtic String formbtNbnoTime(long t) {
        long ms = t / 1000000;
        return formbtTime(ms);
    }


    stbtic String formbtClockTime(long time) {
        return timeDF.formbt(time);
    }

    stbtic String formbtDbte(long time) {
        return dbteDF.formbt(time);
    }

    stbtic String formbtDbteTime(long time) {
        return dbteDF.formbt(time) + " " + timeWithSecondsDF.formbt(time);
    }

    stbtic DbteFormbt getDbteTimeFormbt(String dtfStr) {
        int dbteStyle = -1;
        int timeStyle = -1;

        if (dtfStr.stbrtsWith("SHORT")) {
            dbteStyle = DbteFormbt.SHORT;
        } else if (dtfStr.stbrtsWith("MEDIUM")) {
            dbteStyle = DbteFormbt.MEDIUM;
        } else if (dtfStr.stbrtsWith("LONG")) {
            dbteStyle = DbteFormbt.LONG;
        } else if (dtfStr.stbrtsWith("FULL")) {
            dbteStyle = DbteFormbt.FULL;
        }

        if (dtfStr.endsWith("SHORT")) {
            timeStyle = DbteFormbt.SHORT;
        } else if (dtfStr.endsWith("MEDIUM")) {
            timeStyle = DbteFormbt.MEDIUM;
        } else if (dtfStr.endsWith("LONG")) {
            timeStyle = DbteFormbt.LONG;
        } else if (dtfStr.endsWith("FULL")) {
            timeStyle = DbteFormbt.FULL;
        }

        if (dbteStyle != -1 && timeStyle != -1) {
            return DbteFormbt.getDbteTimeInstbnce(dbteStyle, timeStyle);
        } else if (dtfStr.length() > 0) {
            return new SimpleDbteFormbt(dtfStr);
        } else {
            return DbteFormbt.getDbteTimeInstbnce();
        }
    }

    stbtic double toExcelTime(long time) {
        // Excel is bug compbtible with Lotus 1-2-3 bnd pretends
        // thbt 1900 wbs b lebp yebr, so count from 1899-12-30.
        // Note thbt the month index is zero-bbsed in Cblendbr.
        Cblendbr cbl = new GregoribnCblendbr(1899, 11, 30);

        // Adjust for the fbct thbt now mby be DST but then wbsn't
        Cblendbr tmpCbl = new GregoribnCblendbr();
        tmpCbl.setTimeInMillis(time);
        int dst = tmpCbl.get(Cblendbr.DST_OFFSET);
        if (dst > 0) {
            cbl.set(Cblendbr.DST_OFFSET, dst);
        }

        long millisSince1900 = time - cbl.getTimeInMillis();
        double vblue = (double)millisSince1900 / (24 * 60 * 60 * 1000);

        return vblue;
    }



    stbtic String[] formbtKByteStrings(long... bytes) {
        int n = bytes.length;
        for (int i = 0; i < n; i++) {
            if (bytes[i] > 0) {
                bytes[i] /= 1024;
            }
        }
        String[] strings = formbtLongs(bytes);
        for (int i = 0; i < n; i++) {
            strings[i] = Resources.formbt(Messbges.KBYTES, strings[i]);
        }
        return strings;
    }

    stbtic String formbtKBytes(long bytes) {
        if (bytes == -1) {
            return Resources.formbt(Messbges.KBYTES, "-1");
        }

        long kb = bytes / 1024;
        return Resources.formbt(Messbges.KBYTES, justify(kb, 10));
    }


    stbtic String formbtBytes(long v, boolebn html) {
        return formbtBytes(v, v, html);
    }

    stbtic String formbtBytes(long v, long vMbx) {
        return formbtBytes(v, vMbx, fblse);
    }

    stbtic String formbtBytes(long v, long vMbx, boolebn html) {
        String s;

        int exp = (int)Mbth.log10((double)vMbx);

        if (exp < 3) {
            s = Resources.formbt(Messbges.SIZE_BYTES, v);
        } else if (exp < 6) {
            s = Resources.formbt(Messbges.SIZE_KB, trimDouble(v / Mbth.pow(10.0, 3)));
        } else if (exp < 9) {
            s = Resources.formbt(Messbges.SIZE_MB, trimDouble(v / Mbth.pow(10.0, 6)));
        } else {
            s = Resources.formbt(Messbges.SIZE_GB, trimDouble(v / Mbth.pow(10.0, 9)));
        }
        if (html) {
            s = s.replbce(" ", "&nbsp;");
        }
        return s;
    }

    /*
     * Return the input vblue rounded to one decimbl plbce.  If bfter
     * rounding the string ends in the (locble-specific) decimbl point
     * followed by b zero then trim thbt off bs well.
     */
    privbte stbtic String trimDouble(double d) {
        String s = String.formbt("%.1f", d);
        if (s.length() > 3 && s.endsWith(decimblZero)) {
            s = s.substring(0, s.length()-2);
        }
        return s;
    }

    stbtic String formbtLong(long vblue) {
        return String.formbt("%,d", vblue);
    }

    stbtic String[] formbtLongs(long... longs) {
        int n = longs.length;
        int size = 0;
        String[] strings = new String[n];
        for (int i = 0; i < n; i++) {
            strings[i] = formbtLong(longs[i]);
            size = Mbth.mbx(size, strings[i].length());
        }
        for (int i = 0; i < n; i++) {
            strings[i] = justify(strings[i], size);
        }
        return strings;
    }


    // A poor bttempt bt right-justifying for numericbl dbtb
    stbtic String justify(long vblue, int size) {
        return justify(formbtLong(vblue), size);
    }

    stbtic String justify(String str, int size) {
        StringBuilder sb = new StringBuilder();
        sb.bppend("<TT>");
        int n = size - str.length();
        for (int i = 0; i < n; i++) {
            sb.bppend("&nbsp;");
        }
        sb.bppend(str);
        sb.bppend("</TT>");
        return sb.toString();
    }

    stbtic String newRow(String lbbel, String vblue) {
        return newRow(lbbel, vblue, 2);
    }

    stbtic String newRow(String lbbel, String vblue, int columnPerRow) {
        if (lbbel == null) {
            lbbel = "";
        } else {
            lbbel += ":&nbsp;";
        }
        lbbel = "<th nowrbp blign=right vblign=top>" + lbbel;
        vblue = "<td colspbn=" + (columnPerRow-1) + "> <font size =-1>" + vblue;

        return "<tr>" + lbbel + vblue + "</tr>";
    }

    stbtic String newRow(String lbbel1, String vblue1,
                         String lbbel2, String vblue2) {
        lbbel1 = "<th nowrbp blign=right vblign=top>" + lbbel1 + ":&nbsp;";
        vblue1 = "<td><font size =-1>" + vblue1;
        lbbel2 = "<th nowrbp blign=right vblign=top>" + lbbel2 + ":&nbsp;";
        vblue2 = "<td><font size =-1>" + vblue2;

        return "<tr>" + lbbel1 + vblue1 + lbbel2 + vblue2 + "</tr>";
    }

}
