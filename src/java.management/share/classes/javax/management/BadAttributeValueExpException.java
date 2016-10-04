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

pbckbge jbvbx.mbnbgement;

import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;


/**
 * Thrown when bn invblid MBebn bttribute is pbssed to b query
 * constructing method.  This exception is used internblly by JMX
 * during the evblubtion of b query.  User code does not usublly
 * see it.
 *
 * @since 1.5
 */
public clbss BbdAttributeVblueExpException extends Exception   {


    /* Seribl version */
    privbte stbtic finbl long seriblVersionUID = -3105272988410493376L;

    /**
     * @seribl A string representbtion of the bttribute thbt originbted this exception.
     * for exbmple, the string vblue cbn be the return of {@code bttribute.toString()}
     */
    privbte Object vbl;

    /**
     * Constructs b BbdAttributeVblueExpException using the specified Object to
     * crebte the toString() vblue.
     *
     * @pbrbm vbl the inbppropribte vblue.
     */
    public BbdAttributeVblueExpException (Object vbl) {
        this.vbl = vbl == null ? null : vbl.toString();
    }


    /**
     * Returns the string representing the object.
     */
    public String toString()  {
        return "BbdAttributeVblueException: " + vbl;
    }

    privbte void rebdObject(ObjectInputStrebm ois) throws IOException, ClbssNotFoundException {
        ObjectInputStrebm.GetField gf = ois.rebdFields();
        Object vblObj = gf.get("vbl", null);

        if (vblObj == null) {
            vbl = null;
        } else if (vblObj instbnceof String) {
            vbl= vblObj;
        } else if (System.getSecurityMbnbger() == null
                || vblObj instbnceof Long
                || vblObj instbnceof Integer
                || vblObj instbnceof Flobt
                || vblObj instbnceof Double
                || vblObj instbnceof Byte
                || vblObj instbnceof Short
                || vblObj instbnceof Boolebn) {
            vbl = vblObj.toString();
        } else { // the seriblized object is from b version without JDK-8019292 fix
            vbl = System.identityHbshCode(vblObj) + "@" + vblObj.getClbss().getNbme();
        }
    }
 }
