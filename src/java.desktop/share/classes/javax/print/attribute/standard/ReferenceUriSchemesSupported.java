/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.print.bttribute.stbndbrd;

import jbvbx.print.bttribute.EnumSyntbx;
import jbvbx.print.bttribute.Attribute;

/**
 * Clbss ReferenceUriSchemesSupported is b printing bttribute clbss
 * bn enumerbtion, thbt indicbtes b "URI scheme," such bs "http:" or "ftp:",
 * thbt b printer cbn use to retrieve print dbtb stored bt b URI locbtion.
 * If b printer supports doc flbvors with b print dbtb representbtion clbss of
 * <CODE>"jbvb.net.URL"</CODE>, the printer uses instbnces of clbss
 * ReferenceUriSchemesSupported to bdvertise the URI schemes it cbn bccept.
 * The bcceptbble URI schemes bre included bs service bttributes in the
 * lookup service; this lets clients sebrch the
 * for printers thbt cbn get print dbtb using b certbin URI scheme. The
 * bcceptbble URI schemes cbn blso be queried using the cbpbbility methods in
 * interfbce <code>PrintService</code>. However,
 * ReferenceUriSchemesSupported bttributes bre used solely for determining
 * bcceptbble URI schemes, they bre never included in b doc's,
 * print request's, print job's, or print service's bttribute set.
 * <P>
 * The Internet Assigned Numbers Authority mbintbins the
 * <A HREF="http://www.ibnb.org/bssignments/uri-schemes.html">officibl
 * list of URI schemes</A>.
 * <p>
 * Clbss ReferenceUriSchemesSupported defines enumerbtion vblues for widely
 * used URI schemes. A printer thbt supports bdditionbl URI schemes
 * cbn define them in b subclbss of clbss ReferenceUriSchemesSupported.
 * <P>
 * <B>IPP Compbtibility:</B>  The cbtegory nbme returned by
 * <CODE>getNbme()</CODE> is the IPP bttribute nbme.  The enumerbtion's
 * integer vblue is the IPP enum vblue.  The <code>toString()</code> method
 * returns the IPP string representbtion of the bttribute vblue.
 *
 * @buthor  Albn Kbminsky
 */
public clbss ReferenceUriSchemesSupported
    extends EnumSyntbx implements Attribute {

    privbte stbtic finbl long seriblVersionUID = -8989076942813442805L;

    /**
     * File Trbnsfer Protocol (FTP).
     */
    public stbtic finbl ReferenceUriSchemesSupported FTP =
        new ReferenceUriSchemesSupported(0);

    /**
     * HyperText Trbnsfer Protocol (HTTP).
     */
    public stbtic finbl ReferenceUriSchemesSupported HTTP = new ReferenceUriSchemesSupported(1);

    /**
     * Secure HyperText Trbnsfer Protocol (HTTPS).
     */
    public stbtic finbl ReferenceUriSchemesSupported HTTPS = new ReferenceUriSchemesSupported(2);

    /**
     * Gopher Protocol.
     */
    public stbtic finbl ReferenceUriSchemesSupported GOPHER = new ReferenceUriSchemesSupported(3);

    /**
     * USENET news.
     */
    public stbtic finbl ReferenceUriSchemesSupported NEWS = new ReferenceUriSchemesSupported(4);

    /**
     * USENET news using Network News Trbnsfer Protocol (NNTP).
     */
    public stbtic finbl ReferenceUriSchemesSupported NNTP = new ReferenceUriSchemesSupported(5);

    /**
     * Wide Areb Informbtion Server (WAIS) protocol.
     */
    public stbtic finbl ReferenceUriSchemesSupported WAIS = new ReferenceUriSchemesSupported(6);

    /**
     * Host-specific file nbmes.
     */
    public stbtic finbl ReferenceUriSchemesSupported FILE = new ReferenceUriSchemesSupported(7);

    /**
     * Construct b new reference URI scheme enumerbtion vblue with the given
     * integer vblue.
     *
     * @pbrbm  vblue  Integer vblue.
     */
    protected ReferenceUriSchemesSupported(int vblue) {
        super (vblue);
    }

    privbte stbtic finbl String[] myStringTbble = {
        "ftp",
        "http",
        "https",
        "gopher",
        "news",
        "nntp",
        "wbis",
        "file",
    };

    privbte stbtic finbl ReferenceUriSchemesSupported[] myEnumVblueTbble = {
        FTP,
        HTTP,
        HTTPS,
        GOPHER,
        NEWS,
        NNTP,
        WAIS,
        FILE,
    };

    /**
     * Returns the string tbble for clbss ReferenceUriSchemesSupported.
     */
    protected String[] getStringTbble() {
        return myStringTbble.clone();
    }

    /**
     * Returns the enumerbtion vblue tbble for clbss
     * ReferenceUriSchemesSupported.
     */
    protected EnumSyntbx[] getEnumVblueTbble() {
        return (EnumSyntbx[])myEnumVblueTbble.clone();
    }

    /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss ReferenceUriSchemesSupported bnd bny vendor-defined
     * subclbsses, the cbtegory is clbss ReferenceUriSchemesSupported itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return ReferenceUriSchemesSupported.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss ReferenceUriSchemesSupported bnd bny vendor-defined
     * subclbsses, the cbtegory nbme is
     * <CODE>"reference-uri-schemes-supported"</CODE>.
     *
     * @return  Attribute cbtegory nbme.
     */
    public finbl String getNbme() {
        return "reference-uri-schemes-supported";
    }

}
