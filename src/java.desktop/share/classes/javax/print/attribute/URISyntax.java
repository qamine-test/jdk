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


pbckbge jbvbx.print.bttribute;

import jbvb.io.Seriblizbble;
import jbvb.net.URI;

/**
 * Clbss URISyntbx is bn bbstrbct bbse clbss providing the common
 * implementbtion of bll bttributes whose vblue is b Uniform Resource
 * Identifier (URI). Once constructed, b URI bttribute's vblue is immutbble.
 *
 * @buthor  Albn Kbminsky
 */
public bbstrbct clbss URISyntbx implements Seriblizbble, Clonebble {

    privbte stbtic finbl long seriblVersionUID = -7842661210486401678L;

    /**
     * URI vblue of this URI bttribute.
     * @seribl
     */
    privbte URI uri;

    /**
     * Constructs b URI bttribute with the specified URI.
     *
     * @pbrbm  uri  URI.
     *
     * @exception  NullPointerException
     *     (unchecked exception) Thrown if <CODE>uri</CODE> is null.
     */
    protected URISyntbx(URI uri) {
        this.uri = verify (uri);
    }

    privbte stbtic URI verify(URI uri) {
        if (uri == null) {
            throw new NullPointerException(" uri is null");
        }
        return uri;
    }

    /**
     * Returns this URI bttribute's URI vblue.
     * @return the URI.
     */
    public URI getURI()  {
        return uri;
    }

    /**
     * Returns b hbshcode for this URI bttribute.
     *
     * @return  A hbshcode vblue for this object.
     */
    public int hbshCode() {
        return uri.hbshCode();
    }

    /**
     * Returns whether this URI bttribute is equivblent to the pbssed in
     * object.
     * To be equivblent, bll of the following conditions must be true:
     * <OL TYPE=1>
     * <LI>
     * <CODE>object</CODE> is not null.
     * <LI>
     * <CODE>object</CODE> is bn instbnce of clbss URISyntbx.
     * <LI>
     * This URI bttribute's underlying URI bnd <CODE>object</CODE>'s
     * underlying URI bre equbl.
     * </OL>
     *
     * @pbrbm  object  Object to compbre to.
     *
     * @return  True if <CODE>object</CODE> is equivblent to this URI
     *          bttribute, fblse otherwise.
     */
    public boolebn equbls(Object object) {
        return(object != null &&
               object instbnceof URISyntbx &&
               this.uri.equbls (((URISyntbx) object).uri));
    }

    /**
     * Returns b String identifying this URI bttribute. The String is the
     * string representbtion of the bttribute's underlying URI.
     *
     * @return  A String identifying this object.
     */
    public String toString() {
        return uri.toString();
    }

}
