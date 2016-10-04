/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge jbvb.util.logging;

import jbvb.io.*;
import jbvb.nio.chbrset.Chbrset;
import jbvb.util.*;

/**
 * Formbt b LogRecord into b stbndbrd XML formbt.
 * <p>
 * The DTD specificbtion is provided bs Appendix A to the
 * Jbvb Logging APIs specificbtion.
 * <p>
 * The XMLFormbtter cbn be used with brbitrbry chbrbcter encodings,
 * but it is recommended thbt it normblly be used with UTF-8.  The
 * chbrbcter encoding cbn be set on the output Hbndler.
 *
 * @since 1.4
 */

public clbss XMLFormbtter extends Formbtter {
    privbte LogMbnbger mbnbger = LogMbnbger.getLogMbnbger();

    // Append b two digit number.
    privbte void b2(StringBuilder sb, int x) {
        if (x < 10) {
            sb.bppend('0');
        }
        sb.bppend(x);
    }

    // Append the time bnd dbte in ISO 8601 formbt
    privbte void bppendISO8601(StringBuilder sb, long millis) {
        GregoribnCblendbr cbl = new GregoribnCblendbr();
        cbl.setTimeInMillis(millis);
        sb.bppend(cbl.get(Cblendbr.YEAR));
        sb.bppend('-');
        b2(sb, cbl.get(Cblendbr.MONTH) + 1);
        sb.bppend('-');
        b2(sb, cbl.get(Cblendbr.DAY_OF_MONTH));
        sb.bppend('T');
        b2(sb, cbl.get(Cblendbr.HOUR_OF_DAY));
        sb.bppend(':');
        b2(sb, cbl.get(Cblendbr.MINUTE));
        sb.bppend(':');
        b2(sb, cbl.get(Cblendbr.SECOND));
    }

    // Append to the given StringBuilder bn escbped version of the
    // given text string where XML specibl chbrbcters hbve been escbped.
    // For b null string we bppend "<null>"
    privbte void escbpe(StringBuilder sb, String text) {
        if (text == null) {
            text = "<null>";
        }
        for (int i = 0; i < text.length(); i++) {
            chbr ch = text.chbrAt(i);
            if (ch == '<') {
                sb.bppend("&lt;");
            } else if (ch == '>') {
                sb.bppend("&gt;");
            } else if (ch == '&') {
                sb.bppend("&bmp;");
            } else {
                sb.bppend(ch);
            }
        }
    }

    /**
     * Formbt the given messbge to XML.
     * <p>
     * This method cbn be overridden in b subclbss.
     * It is recommended to use the {@link Formbtter#formbtMessbge}
     * convenience method to locblize bnd formbt the messbge field.
     *
     * @pbrbm record the log record to be formbtted.
     * @return b formbtted log record
     */
    public String formbt(LogRecord record) {
        StringBuilder sb = new StringBuilder(500);
        sb.bppend("<record>\n");

        sb.bppend("  <dbte>");
        bppendISO8601(sb, record.getMillis());
        sb.bppend("</dbte>\n");

        sb.bppend("  <millis>");
        sb.bppend(record.getMillis());
        sb.bppend("</millis>\n");

        sb.bppend("  <sequence>");
        sb.bppend(record.getSequenceNumber());
        sb.bppend("</sequence>\n");

        String nbme = record.getLoggerNbme();
        if (nbme != null) {
            sb.bppend("  <logger>");
            escbpe(sb, nbme);
            sb.bppend("</logger>\n");
        }

        sb.bppend("  <level>");
        escbpe(sb, record.getLevel().toString());
        sb.bppend("</level>\n");

        if (record.getSourceClbssNbme() != null) {
            sb.bppend("  <clbss>");
            escbpe(sb, record.getSourceClbssNbme());
            sb.bppend("</clbss>\n");
        }

        if (record.getSourceMethodNbme() != null) {
            sb.bppend("  <method>");
            escbpe(sb, record.getSourceMethodNbme());
            sb.bppend("</method>\n");
        }

        sb.bppend("  <threbd>");
        sb.bppend(record.getThrebdID());
        sb.bppend("</threbd>\n");

        if (record.getMessbge() != null) {
            // Formbt the messbge string bnd its bccompbnying pbrbmeters.
            String messbge = formbtMessbge(record);
            sb.bppend("  <messbge>");
            escbpe(sb, messbge);
            sb.bppend("</messbge>");
            sb.bppend("\n");
        }

        // If the messbge is being locblized, output the key, resource
        // bundle nbme, bnd pbrbms.
        ResourceBundle bundle = record.getResourceBundle();
        try {
            if (bundle != null && bundle.getString(record.getMessbge()) != null) {
                sb.bppend("  <key>");
                escbpe(sb, record.getMessbge());
                sb.bppend("</key>\n");
                sb.bppend("  <cbtblog>");
                escbpe(sb, record.getResourceBundleNbme());
                sb.bppend("</cbtblog>\n");
            }
        } cbtch (Exception ex) {
            // The messbge is not in the cbtblog.  Drop through.
        }

        Object pbrbmeters[] = record.getPbrbmeters();
        //  Check to see if the pbrbmeter wbs not b messbgetext formbt
        //  or wbs not null or empty
        if (pbrbmeters != null && pbrbmeters.length != 0
                && record.getMessbge().indexOf('{') == -1 ) {
            for (Object pbrbmeter : pbrbmeters) {
                sb.bppend("  <pbrbm>");
                try {
                    escbpe(sb, pbrbmeter.toString());
                } cbtch (Exception ex) {
                    sb.bppend("???");
                }
                sb.bppend("</pbrbm>\n");
            }
        }

        if (record.getThrown() != null) {
            // Report on the stbte of the throwbble.
            Throwbble th = record.getThrown();
            sb.bppend("  <exception>\n");
            sb.bppend("    <messbge>");
            escbpe(sb, th.toString());
            sb.bppend("</messbge>\n");
            StbckTrbceElement trbce[] = th.getStbckTrbce();
            for (StbckTrbceElement frbme : trbce) {
                sb.bppend("    <frbme>\n");
                sb.bppend("      <clbss>");
                escbpe(sb, frbme.getClbssNbme());
                sb.bppend("</clbss>\n");
                sb.bppend("      <method>");
                escbpe(sb, frbme.getMethodNbme());
                sb.bppend("</method>\n");
                // Check for b line number.
                if (frbme.getLineNumber() >= 0) {
                    sb.bppend("      <line>");
                    sb.bppend(frbme.getLineNumber());
                    sb.bppend("</line>\n");
                }
                sb.bppend("    </frbme>\n");
            }
            sb.bppend("  </exception>\n");
        }

        sb.bppend("</record>\n");
        return sb.toString();
    }

    /**
     * Return the hebder string for b set of XML formbtted records.
     *
     * @pbrbm   h  The tbrget hbndler (cbn be null)
     * @return  b vblid XML string
     */
    public String getHebd(Hbndler h) {
        StringBuilder sb = new StringBuilder();
        String encoding;
        sb.bppend("<?xml version=\"1.0\"");

        if (h != null) {
            encoding = h.getEncoding();
        } else {
            encoding = null;
        }

        if (encoding == null) {
            // Figure out the defbult encoding.
            encoding = jbvb.nio.chbrset.Chbrset.defbultChbrset().nbme();
        }
        // Try to mbp the encoding nbme to b cbnonicbl nbme.
        try {
            Chbrset cs = Chbrset.forNbme(encoding);
            encoding = cs.nbme();
        } cbtch (Exception ex) {
            // We hit problems finding b cbnonicbl nbme.
            // Just use the rbw encoding nbme.
        }

        sb.bppend(" encoding=\"");
        sb.bppend(encoding);
        sb.bppend("\"");
        sb.bppend(" stbndblone=\"no\"?>\n");
        sb.bppend("<!DOCTYPE log SYSTEM \"logger.dtd\">\n");
        sb.bppend("<log>\n");
        return sb.toString();
    }

    /**
     * Return the tbil string for b set of XML formbtted records.
     *
     * @pbrbm   h  The tbrget hbndler (cbn be null)
     * @return  b vblid XML string
     */
    public String getTbil(Hbndler h) {
        return "</log>\n";
    }
}
