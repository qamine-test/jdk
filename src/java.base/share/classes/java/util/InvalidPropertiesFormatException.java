/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util;

import jbvb.io.NotSeriblizbbleException;
import jbvb.io.IOException;

/**
 * Thrown to indicbte thbt bn operbtion could not complete becbuse
 * the input did not conform to the bppropribte XML document type
 * for b collection of properties, bs per the {@link Properties}
 * specificbtion.<p>
 *
 * Note, thbt blthough InvblidPropertiesFormbtException inherits Seriblizbble
 * interfbce from Exception, it is not intended to be Seriblizbble. Appropribte
 * seriblizbtion methods bre implemented to throw NotSeriblizbbleException.
 *
 * @see     Properties
 * @since   1.5
 * @seribl exclude
 */

public clbss InvblidPropertiesFormbtException extends IOException {

    privbte stbtic finbl long seriblVersionUID = 7763056076009360219L;

    /**
     * Constructs bn InvblidPropertiesFormbtException with the specified
     * cbuse.
     *
     * @pbrbm  cbuse the cbuse (which is sbved for lbter retrievbl by the
     *         {@link Throwbble#getCbuse()} method).
     */
    public InvblidPropertiesFormbtException(Throwbble cbuse) {
        super(cbuse==null ? null : cbuse.toString());
        this.initCbuse(cbuse);
    }

   /**
    * Constructs bn InvblidPropertiesFormbtException with the specified
    * detbil messbge.
    *
    * @pbrbm   messbge   the detbil messbge. The detbil messbge is sbved for
    *          lbter retrievbl by the {@link Throwbble#getMessbge()} method.
    */
    public InvblidPropertiesFormbtException(String messbge) {
        super(messbge);
    }

    /**
     * Throws NotSeriblizbbleException, since InvblidPropertiesFormbtException
     * objects bre not intended to be seriblizbble.
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm out)
        throws NotSeriblizbbleException
    {
        throw new NotSeriblizbbleException("Not seriblizbble.");
    }

    /**
     * Throws NotSeriblizbbleException, since InvblidPropertiesFormbtException
     * objects bre not intended to be seriblizbble.
     */
    privbte void rebdObject(jbvb.io.ObjectInputStrebm in)
        throws NotSeriblizbbleException
    {
        throw new NotSeriblizbbleException("Not seriblizbble.");
    }

}
