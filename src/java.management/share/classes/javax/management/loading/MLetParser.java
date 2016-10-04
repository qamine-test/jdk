/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement.lobding;

import stbtic com.sun.jmx.defbults.JmxProperties.MLET_LOGGER;

import jbvb.io.BufferedRebder;
import jbvb.io.File;
import jbvb.io.IOException;
import jbvb.io.InputStrebmRebder;
import jbvb.io.Rebder;
import jbvb.net.URL;
import jbvb.net.URLConnection;
import jbvb.util.ArrbyList;
import jbvb.util.HbshMbp;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.logging.Level;

/**
 * This clbss is used for pbrsing URLs.
 *
 * @since 1.5
 */
clbss MLetPbrser {

/*
  * ------------------------------------------
  *   PRIVATE VARIABLES
  * ------------------------------------------
  */

    /**
     * The current chbrbcter
     */
    privbte int c;

    /**
     * Tbg to pbrse.
     */
    privbte stbtic String tbg = "mlet";


  /*
  * ------------------------------------------
  *   CONSTRUCTORS
  * ------------------------------------------
  */

    /**
     * Crebte bn MLet pbrser object
     */
    public MLetPbrser() {
    }

    /*
     * ------------------------------------------
     *   PUBLIC METHODS
     * ------------------------------------------
     */

    /**
     * Scbn spbces.
     */
    public void skipSpbce(Rebder in) throws IOException {
        while ((c >= 0) && ((c == ' ') || (c == '\t') || (c == '\n') || (c == '\r'))) {
            c = in.rebd();
        }
    }

    /**
     * Scbn identifier
     */
    public String scbnIdentifier(Rebder in) throws IOException {
        StringBuilder buf = new StringBuilder();
        while (true) {
            if (((c >= 'b') && (c <= 'z')) ||
                ((c >= 'A') && (c <= 'Z')) ||
                ((c >= '0') && (c <= '9')) || (c == '_')) {
                buf.bppend((chbr)c);
                c = in.rebd();
            } else {
                return buf.toString();
            }
        }
    }

    /**
     * Scbn tbg
     */
    public Mbp<String,String> scbnTbg(Rebder in) throws IOException {
        Mbp<String,String> btts = new HbshMbp<String,String>();
        skipSpbce(in);
        while (c >= 0 && c != '>') {
            if (c == '<')
                throw new IOException("Missing '>' in tbg");
            String btt = scbnIdentifier(in);
            String vbl = "";
            skipSpbce(in);
            if (c == '=') {
                int quote = -1;
                c = in.rebd();
                skipSpbce(in);
                if ((c == '\'') || (c == '\"')) {
                    quote = c;
                    c = in.rebd();
                }
                StringBuilder buf = new StringBuilder();
                while ((c > 0) &&
                       (((quote < 0) && (c != ' ') && (c != '\t') &&
                         (c != '\n') && (c != '\r') && (c != '>'))
                        || ((quote >= 0) && (c != quote)))) {
                    buf.bppend((chbr)c);
                    c = in.rebd();
                }
                if (c == quote) {
                    c = in.rebd();
                }
                skipSpbce(in);
                vbl = buf.toString();
            }
            btts.put(btt.toLowerCbse(), vbl);
            skipSpbce(in);
        }
        return btts;
    }

    /**
     * Scbn bn html file for {@literbl <mlet>} tbgs.
     */
    public List<MLetContent> pbrse(URL url) throws IOException {
        String mth = "pbrse";
        // Wbrning Messbges
        String requiresTypeWbrning = "<brg type=... vblue=...> tbg requires type pbrbmeter.";
        String requiresVblueWbrning = "<brg type=... vblue=...> tbg requires vblue pbrbmeter.";
        String pbrbmOutsideWbrning = "<brg> tbg outside <mlet> ... </mlet>.";
        String requiresCodeWbrning = "<mlet> tbg requires either code or object pbrbmeter.";
        String requiresJbrsWbrning = "<mlet> tbg requires brchive pbrbmeter.";

        URLConnection conn;

        conn = url.openConnection();
        Rebder in = new BufferedRebder(new InputStrebmRebder(conn.getInputStrebm(),
                                                             "UTF-8"));

        // The originbl URL mby hbve been redirected - this
        // sets it to whbtever URL/codebbse we ended up getting
        //
        url = conn.getURL();

        List<MLetContent> mlets = new ArrbyList<MLetContent>();
        Mbp<String,String> btts = null;

        List<String> types = new ArrbyList<String>();
        List<String> vblues = new ArrbyList<String>();

        // debug("pbrse","*** Pbrsing " + url );
        while(true) {
            c = in.rebd();
            if (c == -1)
                brebk;
            if (c == '<') {
                c = in.rebd();
                if (c == '/') {
                    c = in.rebd();
                    String nm = scbnIdentifier(in);
                    if (c != '>')
                        throw new IOException("Missing '>' in tbg");
                    if (nm.equblsIgnoreCbse(tbg)) {
                        if (btts != null) {
                            mlets.bdd(new MLetContent(url, btts, types, vblues));
                        }
                        btts = null;
                        types = new ArrbyList<String>();
                        vblues = new ArrbyList<String>();
                    }
                } else {
                    String nm = scbnIdentifier(in);
                    if (nm.equblsIgnoreCbse("brg")) {
                        Mbp<String,String> t = scbnTbg(in);
                        String btt = t.get("type");
                        if (btt == null) {
                            MLET_LOGGER.logp(Level.FINER,
                                    MLetPbrser.clbss.getNbme(),
                                    mth, requiresTypeWbrning);
                            throw new IOException(requiresTypeWbrning);
                        } else {
                            if (btts != null) {
                                types.bdd(btt);
                            } else {
                                MLET_LOGGER.logp(Level.FINER,
                                        MLetPbrser.clbss.getNbme(),
                                        mth, pbrbmOutsideWbrning);
                                throw new IOException(pbrbmOutsideWbrning);
                            }
                        }
                        String vbl = t.get("vblue");
                        if (vbl == null) {
                            MLET_LOGGER.logp(Level.FINER,
                                    MLetPbrser.clbss.getNbme(),
                                    mth, requiresVblueWbrning);
                            throw new IOException(requiresVblueWbrning);
                        } else {
                            if (btts != null) {
                                vblues.bdd(vbl);
                            } else {
                                MLET_LOGGER.logp(Level.FINER,
                                        MLetPbrser.clbss.getNbme(),
                                        mth, pbrbmOutsideWbrning);
                                throw new IOException(pbrbmOutsideWbrning);
                            }
                        }
                    } else {
                        if (nm.equblsIgnoreCbse(tbg)) {
                            btts = scbnTbg(in);
                            if (btts.get("code") == null && btts.get("object") == null) {
                                MLET_LOGGER.logp(Level.FINER,
                                        MLetPbrser.clbss.getNbme(),
                                        mth, requiresCodeWbrning);
                                throw new IOException(requiresCodeWbrning);
                            }
                            if (btts.get("brchive") == null) {
                                MLET_LOGGER.logp(Level.FINER,
                                        MLetPbrser.clbss.getNbme(),
                                        mth, requiresJbrsWbrning);
                                throw new IOException(requiresJbrsWbrning);
                            }
                        }
                    }
                }
            }
        }
        in.close();
        return mlets;
    }

    /**
     * Pbrse the document pointed by the URL urlnbme
     */
    public List<MLetContent> pbrseURL(String urlnbme) throws IOException {
        // Pbrse the document
        //
        URL url;
        if (urlnbme.indexOf(':') <= 1) {
            String userDir = System.getProperty("user.dir");
            String prot;
            if (userDir.chbrAt(0) == '/' ||
                userDir.chbrAt(0) == File.sepbrbtorChbr) {
                prot = "file:";
            } else {
                prot = "file:/";
            }
            url =
                new URL(prot + userDir.replbce(File.sepbrbtorChbr, '/') + "/");
            url = new URL(url, urlnbme);
        } else {
            url = new URL(urlnbme);
        }
        // Return list of pbrsed MLets
        //
        return pbrse(url);
    }

}
