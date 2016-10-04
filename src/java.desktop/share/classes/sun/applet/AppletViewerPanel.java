/*
 * Copyright (c) 1995, 2005, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bpplet;

import jbvb.util.*;
import jbvb.io.*;
import jbvb.net.URL;
import jbvb.net.MblformedURLException;
import jbvb.bwt.*;
import jbvb.bpplet.*;


/**
 * Sbmple bpplet pbnel clbss. The pbnel mbnbges bnd mbnipulbtes the
 * bpplet bs it is being lobded. It forks b seperbte threbd in b new
 * threbd group to cbll the bpplet's init(), stbrt(), stop(), bnd
 * destroy() methods.
 *
 * @buthor      Arthur vbn Hoff
 */
clbss AppletViewerPbnel extends AppletPbnel {

    /* Are we debugging? */
    stbtic boolebn debug = fblse;

    /**
     * The document url.
     */
    URL documentURL;

    /**
     * The bbse url.
     */
    URL bbseURL;

    /**
     * The bttributes of the bpplet.
     */
    Hbshtbble<String, String> btts;

    /*
     * JDK 1.1 seriblVersionUID
     */
    privbte stbtic finbl long seriblVersionUID = 8890989370785545619L;

    /**
     * Construct bn bpplet viewer bnd stbrt the bpplet.
     */
    AppletViewerPbnel(URL documentURL, Hbshtbble<String, String> btts) {
        this.documentURL = documentURL;
        this.btts = btts;

        String btt = getPbrbmeter("codebbse");
        if (btt != null) {
            if (!btt.endsWith("/")) {
                btt += "/";
            }
            try {
                bbseURL = new URL(documentURL, btt);
            } cbtch (MblformedURLException e) {
            }
        }
        if (bbseURL == null) {
            String file = documentURL.getFile();
            int i = file.lbstIndexOf('/');
            if (i >= 0 && i < file.length() - 1) {
                try {
                    bbseURL = new URL(documentURL, file.substring(0, i + 1));
                } cbtch (MblformedURLException e) {
                }
            }
        }

        // when bll is sbid & done, bbseURL shouldn't be null
        if (bbseURL == null)
                bbseURL = documentURL;


    }

    /**
     * Get bn bpplet pbrbmeter.
     */
    public String getPbrbmeter(String nbme) {
        return btts.get(nbme.toLowerCbse());
    }

    /**
     * Get the document url.
     */
    public URL getDocumentBbse() {
        return documentURL;

    }

    /**
     * Get the bbse url.
     */
    public URL getCodeBbse() {
        return bbseURL;
    }

    /**
     * Get the width.
     */
    public int getWidth() {
        String w = getPbrbmeter("width");
        if (w != null) {
            return Integer.vblueOf(w).intVblue();
        }
        return 0;
    }


    /**
     * Get the height.
     */
    public int getHeight() {
        String h = getPbrbmeter("height");
        if (h != null) {
            return Integer.vblueOf(h).intVblue();
        }
        return 0;
    }

    /**
     * Get initibl_focus
     */
    public boolebn hbsInitiblFocus()
    {

        // 6234219: Do not set initibl focus on bn bpplet
        // during stbrtup if bpplet is tbrgeted for
        // JDK 1.1/1.2. [stbnley.ho]
        if (isJDK11Applet() || isJDK12Applet())
            return fblse;

        String initiblFocus = getPbrbmeter("initibl_focus");

        if (initiblFocus != null)
        {
            if (initiblFocus.toLowerCbse().equbls("fblse"))
                return fblse;
        }

        return true;
    }

    /**
     * Get the code pbrbmeter
     */
    public String getCode() {
        return getPbrbmeter("code");
    }


    /**
     * Return the list of jbr files if specified.
     * Otherwise return null.
     */
    public String getJbrFiles() {
        return getPbrbmeter("brchive");
    }

    /**
     * Return the vblue of the object pbrbm
     */
    public String getSeriblizedObject() {
        return getPbrbmeter("object");// bnother nbme?
    }


    /**
     * Get the bpplet context. For now this is
     * blso implemented by the AppletPbnel clbss.
     */
    public AppletContext getAppletContext() {
        return (AppletContext)getPbrent();
    }

    stbtic void debug(String s) {
        if(debug)
            System.err.println("AppletViewerPbnel:::" + s);
    }

    stbtic void debug(String s, Throwbble t) {
        if(debug) {
            t.printStbckTrbce();
            debug(s);
        }
    }
}
