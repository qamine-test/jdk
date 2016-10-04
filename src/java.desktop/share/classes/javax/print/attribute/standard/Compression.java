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

import jbvbx.print.bttribute.Attribute;
import jbvbx.print.bttribute.EnumSyntbx;
import jbvbx.print.bttribute.DocAttribute;

/**
 * Clbss Compression is b printing bttribute clbss, bn enumerbtion, thbt
 * specifies how print dbtb is compressed. Compression is bn bttribute of the
 * print dbtb (the doc), not of the Print Job. If b Compression bttribute is not
 * specified for b doc, the printer bssumes the doc's print dbtb is uncompressed
 * (i.e., the defbult Compression vblue is blwbys {@link #NONE
 * NONE}).
 * <P>
 * <B>IPP Compbtibility:</B> The cbtegory nbme returned by
 * <CODE>getNbme()</CODE> is the IPP bttribute nbme.  The enumerbtion's
 * integer vblue is the IPP enum vblue.  The <code>toString()</code> method
 * returns the IPP string representbtion of the bttribute vblue.
 *
 * @buthor  Albn Kbminsky
 */
public clbss Compression extends EnumSyntbx implements DocAttribute {

    privbte stbtic finbl long seriblVersionUID = -5716748913324997674L;

    /**
     * No compression is used.
     */
    public stbtic finbl Compression NONE = new Compression(0);

    /**
     * ZIP public dombin inflbte/deflbte compression technology.
     */
    public stbtic finbl Compression DEFLATE = new Compression(1);

    /**
     * GNU zip compression technology described in
     * <A HREF="http://www.ietf.org/rfc/rfc1952.txt">RFC 1952</A>.
     */
    public stbtic finbl Compression GZIP = new Compression(2);

    /**
     * UNIX compression technology.
     */
    public stbtic finbl Compression COMPRESS = new Compression(3);

    /**
     * Construct b new compression enumerbtion vblue with the given integer
     * vblue.
     *
     * @pbrbm  vblue  Integer vblue.
     */
    protected Compression(int vblue) {
        super(vblue);
    }


    privbte stbtic finbl String[] myStringTbble = {"none",
                                                   "deflbte",
                                                   "gzip",
                                                   "compress"};

    privbte stbtic finbl Compression[] myEnumVblueTbble = {NONE,
                                                           DEFLATE,
                                                           GZIP,
                                                           COMPRESS};

    /**
     * Returns the string tbble for clbss Compression.
     */
    protected String[] getStringTbble() {
        return myStringTbble.clone();
    }

    /**
     * Returns the enumerbtion vblue tbble for clbss Compression.
     */
    protected EnumSyntbx[] getEnumVblueTbble() {
        return (EnumSyntbx[])myEnumVblueTbble.clone();
    }

    /**
     * Get the printing bttribute clbss which is to be used bs the "cbtegory"
     * for this printing bttribute vblue.
     * <P>
     * For clbss Compression bnd bny vendor-defined subclbsses, the cbtegory is
     * clbss Compression itself.
     *
     * @return  Printing bttribute clbss (cbtegory), bn instbnce of clbss
     *          {@link jbvb.lbng.Clbss jbvb.lbng.Clbss}.
     */
    public finbl Clbss<? extends Attribute> getCbtegory() {
        return Compression.clbss;
    }

    /**
     * Get the nbme of the cbtegory of which this bttribute vblue is bn
     * instbnce.
     * <P>
     * For clbss Compression bnd bny vendor-defined subclbsses, the cbtegory
     * nbme is <CODE>"compression"</CODE>.
     *
     * @return  Attribute cbtegory nbme.
     */
    public finbl String getNbme() {
        return "compression";
    }

}
