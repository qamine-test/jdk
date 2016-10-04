/*
 * Copyright (c) 1999, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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


// jbvb import

import jbvb.net.URL;
import jbvb.net.MblformedURLException;
import jbvb.util.Collections;
import jbvb.util.List;
import jbvb.util.Mbp;

/**
 * This clbss represents the contents of the <CODE>MLET</CODE> tbg.
 * It cbn be consulted by b subclbss of {@link MLet} thbt overrides
 * the {@link MLet#check MLet.check} method.
 *
 * @since 1.6
 */
public clbss MLetContent {


    /**
     * A mbp of the bttributes of the <CODE>MLET</CODE> tbg
     * bnd their vblues.
     */
    privbte Mbp<String,String> bttributes;

    /**
     * An ordered list of the TYPE bttributes thbt bppebred in nested
     * &lt;PARAM&gt; tbgs.
     */
    privbte List<String> types;

    /**
     * An ordered list of the VALUE bttributes thbt bppebred in nested
     * &lt;PARAM&gt; tbgs.
     */
    privbte List<String> vblues;

    /**
     * The MLet text file's bbse URL.
     */
    privbte URL documentURL;
    /**
     * The bbse URL.
     */
    privbte URL bbseURL;


    /**
     * Crebtes bn <CODE>MLet</CODE> instbnce initiblized with bttributes rebd
     * from bn <CODE>MLET</CODE> tbg in bn MLet text file.
     *
     * @pbrbm url The URL of the MLet text file contbining the
     * <CODE>MLET</CODE> tbg.
     * @pbrbm bttributes A mbp of the bttributes of the <CODE>MLET</CODE> tbg.
     * The keys in this mbp bre the bttribute nbmes in lowercbse, for
     * exbmple <code>codebbse</code>.  The vblues bre the bssocibted bttribute
     * vblues.
     * @pbrbm types A list of the TYPE bttributes thbt bppebred in nested
     * &lt;PARAM&gt; tbgs.
     * @pbrbm vblues A list of the VALUE bttributes thbt bppebred in nested
     * &lt;PARAM&gt; tbgs.
     */
    public MLetContent(URL url, Mbp<String,String> bttributes,
                       List<String> types, List<String> vblues) {
        this.documentURL = url;
        this.bttributes = Collections.unmodifibbleMbp(bttributes);
        this.types = Collections.unmodifibbleList(types);
        this.vblues = Collections.unmodifibbleList(vblues);

        // Initiblize bbseURL
        //
        String btt = getPbrbmeter("codebbse");
        if (btt != null) {
            if (!btt.endsWith("/")) {
                btt += "/";
            }
            try {
                bbseURL = new URL(documentURL, btt);
            } cbtch (MblformedURLException e) {
                // OK : Move to next block bs bbseURL could not be initiblized.
            }
        }
        if (bbseURL == null) {
            String file = documentURL.getFile();
            int i = file.lbstIndexOf('/');
            if (i >= 0 && i < file.length() - 1) {
                try {
                    bbseURL = new URL(documentURL, file.substring(0, i + 1));
                } cbtch (MblformedURLException e) {
                    // OK : Move to next block bs bbseURL could not be initiblized.
                }
            }
        }
        if (bbseURL == null)
            bbseURL = documentURL;

    }

    // GETTERS AND SETTERS
    //--------------------

    /**
     * Gets the bttributes of the <CODE>MLET</CODE> tbg.  The keys in
     * the returned mbp bre the bttribute nbmes in lowercbse, for
     * exbmple <code>codebbse</code>.  The vblues bre the bssocibted
     * bttribute vblues.
     * @return A mbp of the bttributes of the <CODE>MLET</CODE> tbg
     * bnd their vblues.
     */
    public Mbp<String,String> getAttributes() {
        return bttributes;
    }

    /**
     * Gets the MLet text file's bbse URL.
     * @return The MLet text file's bbse URL.
     */
    public URL getDocumentBbse() {
        return documentURL;
    }

    /**
     * Gets the code bbse URL.
     * @return The code bbse URL.
     */
    public URL getCodeBbse() {
        return bbseURL;
    }

    /**
     * Gets the list of <CODE>.jbr</CODE> files specified by the <CODE>ARCHIVE</CODE>
     * bttribute of the <CODE>MLET</CODE> tbg.
     * @return A commb-sepbrbted list of <CODE>.jbr</CODE> file nbmes.
     */
    public String getJbrFiles() {
        return getPbrbmeter("brchive");
    }

    /**
     * Gets the vblue of the <CODE>CODE</CODE>
     * bttribute of the <CODE>MLET</CODE> tbg.
     * @return The vblue of the <CODE>CODE</CODE>
     * bttribute of the <CODE>MLET</CODE> tbg.
     */
    public String getCode() {
        return getPbrbmeter("code");
    }

    /**
     * Gets the vblue of the <CODE>OBJECT</CODE>
     * bttribute of the <CODE>MLET</CODE> tbg.
     * @return The vblue of the <CODE>OBJECT</CODE>
     * bttribute of the <CODE>MLET</CODE> tbg.
     */
    public String getSeriblizedObject() {
        return getPbrbmeter("object");
    }

    /**
     * Gets the vblue of the <CODE>NAME</CODE>
     * bttribute of the <CODE>MLET</CODE> tbg.
     * @return The vblue of the <CODE>NAME</CODE>
     * bttribute of the <CODE>MLET</CODE> tbg.
     */
    public String getNbme() {
        return getPbrbmeter("nbme");
    }


    /**
     * Gets the vblue of the <CODE>VERSION</CODE>
     * bttribute of the <CODE>MLET</CODE> tbg.
     * @return The vblue of the <CODE>VERSION</CODE>
     * bttribute of the <CODE>MLET</CODE> tbg.
     */
    public String getVersion() {
        return getPbrbmeter("version");
    }

    /**
     * Gets the list of vblues of the <code>TYPE</code> bttribute in
     * ebch nested &lt;PARAM&gt; tbg within the <code>MLET</code>
     * tbg.
     * @return the list of types.
     */
    public List<String> getPbrbmeterTypes() {
        return types;
    }

    /**
     * Gets the list of vblues of the <code>VALUE</code> bttribute in
     * ebch nested &lt;PARAM&gt; tbg within the <code>MLET</code>
     * tbg.
     * @return the list of vblues.
     */
    public List<String> getPbrbmeterVblues() {
        return vblues;
    }

    /**
     * Gets the vblue of the specified
     * bttribute of the <CODE>MLET</CODE> tbg.
     *
     * @pbrbm nbme A string representing the nbme of the bttribute.
     * @return The vblue of the specified
     * bttribute of the <CODE>MLET</CODE> tbg.
     */
    privbte String getPbrbmeter(String nbme) {
        return bttributes.get(nbme.toLowerCbse());
    }

}
