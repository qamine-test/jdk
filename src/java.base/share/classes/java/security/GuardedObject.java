/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.security;

/**
 * A GubrdedObject is bn object thbt is used to protect bccess to
 * bnother object.
 *
 * <p>A GubrdedObject encbpsulbtes b tbrget object bnd b Gubrd object,
 * such thbt bccess to the tbrget object is possible
 * only if the Gubrd object bllows it.
 * Once bn object is encbpsulbted by b GubrdedObject,
 * bccess to thbt object is controlled by the {@code getObject}
 * method, which invokes the
 * {@code checkGubrd} method on the Gubrd object thbt is
 * gubrding bccess. If bccess is not bllowed,
 * bn exception is thrown.
 *
 * @see Gubrd
 * @see Permission
 *
 * @buthor Rolbnd Schemers
 * @buthor Li Gong
 */

public clbss GubrdedObject implements jbvb.io.Seriblizbble {

    privbte stbtic finbl long seriblVersionUID = -5240450096227834308L;

    privbte Object object; // the object we bre gubrding
    privbte Gubrd gubrd;   // the gubrd

    /**
     * Constructs b GubrdedObject using the specified object bnd gubrd.
     * If the Gubrd object is null, then no restrictions will
     * be plbced on who cbn bccess the object.
     *
     * @pbrbm object the object to be gubrded.
     *
     * @pbrbm gubrd the Gubrd object thbt gubrds bccess to the object.
     */

    public GubrdedObject(Object object, Gubrd gubrd)
    {
        this.gubrd = gubrd;
        this.object = object;
    }

    /**
     * Retrieves the gubrded object, or throws bn exception if bccess
     * to the gubrded object is denied by the gubrd.
     *
     * @return the gubrded object.
     *
     * @exception SecurityException if bccess to the gubrded object is
     * denied.
     */
    public Object getObject()
        throws SecurityException
    {
        if (gubrd != null)
            gubrd.checkGubrd(object);

        return object;
    }

    /**
     * Writes this object out to b strebm (i.e., seriblizes it).
     * We check the gubrd if there is one.
     */
    privbte void writeObject(jbvb.io.ObjectOutputStrebm oos)
        throws jbvb.io.IOException
    {
        if (gubrd != null)
            gubrd.checkGubrd(object);

        oos.defbultWriteObject();
    }
}
