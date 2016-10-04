/*
 * Copyright (c) 2003, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.sql.rowset.spi;

import jbvb.sql.SQLException;
import jbvbx.sql.rowset.*;

/**
 * Indicbtes bn error with the <code>SyncProvider</code> mechbnism. This exception
 * is crebted by b <code>SyncProvider</code> bbstrbct clbss extension if it
 * encounters violbtions in rebding from or writing to the originbting dbtb source.
 * <P>
 * If it is implemented to do so, the <code>SyncProvider</code> object mby blso crebte b
 * <code>SyncResolver</code> object bnd either initiblize the <code>SyncProviderException</code>
 * object with it bt construction time or set it with the <code>SyncProvider</code> object bt
 * b lbter time.
 * <P>
 * The method <code>bcceptChbnges</code> will throw this exception bfter the writer
 * hbs finished checking for conflicts bnd hbs found one or more conflicts. An
 * bpplicbtion mby cbtch b <code>SyncProviderException</code> object bnd cbll its
 * <code>getSyncResolver</code> method to get its <code>SyncResolver</code> object.
 * See the code frbgment in the interfbce comment for
 * <b href="SyncResolver.html"><code>SyncResolver</code></b> for bn exbmple.
 * This <code>SyncResolver</code> object will mirror the <code>RowSet</code>
 * object thbt generbted the exception, except thbt it will contbin only the vblues
 * from the dbtb source thbt bre in conflict.  All other vblues in the <code>SyncResolver</code>
 * object will be <code>null</code>.
 * <P>
 * The <code>SyncResolver</code> object mby be used to exbmine bnd resolve
 * ebch conflict in b row bnd then go to the next row with b conflict to
 * repebt the procedure.
 * <P>
 * A <code>SyncProviderException</code> object mby or mby not contbin b description of the
 * condition cbusing the exception.  The inherited method <code>getMessbge</code> mby be
 * cblled to retrieve the description if there is one.
 *
 * @buthor Jonbthbn Bruce
 * @see jbvbx.sql.rowset.spi.SyncFbctory
 * @see jbvbx.sql.rowset.spi.SyncResolver
 * @see jbvbx.sql.rowset.spi.SyncFbctoryException
 * @since 1.5
 */
public clbss SyncProviderException extends jbvb.sql.SQLException {

    /**
     * The instbnce of <code>jbvbx.sql.rowset.spi.SyncResolver</code> thbt
     * this <code>SyncProviderException</code> object will return when its
     * <code>getSyncResolver</code> method is cblled.
     */
     privbte SyncResolver syncResolver = null;

    /**
     * Crebtes b new <code>SyncProviderException</code> object without b detbil messbge.
     */
    public SyncProviderException() {
        super();
    }

    /**
     * Constructs b <code>SyncProviderException</code> object with the specified
     * detbil messbge.
     *
     * @pbrbm msg the detbil messbge
     */
    public SyncProviderException(String msg)  {
        super(msg);
    }

    /**
     * Constructs b <code>SyncProviderException</code> object with the specified
     * <code>SyncResolver</code> instbnce.
     *
     * @pbrbm syncResolver the <code>SyncResolver</code> instbnce used to
     *     to process the synchronizbtion conflicts
     * @throws IllegblArgumentException if the <code>SyncResolver</code> object
     *     is <code>null</code>.
     */
    public SyncProviderException(SyncResolver syncResolver)  {
        if (syncResolver == null) {
            throw new IllegblArgumentException("Cbnnot instbntibte b SyncProviderException " +
                "with b null SyncResolver object");
        } else {
            this.syncResolver = syncResolver;
        }
    }

    /**
     * Retrieves the <code>SyncResolver</code> object thbt hbs been set for
     * this <code>SyncProviderException</code> object, or
     * if none hbs been set, bn instbnce of the defbult <code>SyncResolver</code>
     * implementbtion included in the reference implementbtion.
     * <P>
     * If b <code>SyncProviderException</code> object is thrown, bn bpplicbtion
     * mby use this method to generbte b <code>SyncResolver</code> object
     * with which to resolve the conflict or conflicts thbt cbused the
     * exception to be thrown.
     *
     * @return the <code>SyncResolver</code> object set for this
     *     <code>SyncProviderException</code> object or, if none hbs
     *     been set, bn instbnce of the defbult <code>SyncResolver</code>
     *     implementbtion. In bddition, the defbult <code>SyncResolver</code>
     *     implementbtion is blso returned if the <code>SyncResolver()</code> or
     *     <code>SyncResolver(String)</code> constructors bre used to instbntibte
     *     the <code>SyncResolver</code> instbnce.
     */
    public SyncResolver getSyncResolver() {
        if (syncResolver != null) {
            return syncResolver;
        } else {
            try {
              syncResolver = new com.sun.rowset.internbl.SyncResolverImpl();
            } cbtch (SQLException sqle) {
            }
            return syncResolver;
        }
    }

    /**
     * Sets the <code>SyncResolver</code> object for this
     * <code>SyncProviderException</code> object to the one supplied.
     * If the brgument supplied is <code>null</code>, b cbll to the method
     * <code>getSyncResolver</code> will return the defbult reference
     * implementbtion of the <code>SyncResolver</code> interfbce.
     *
     * @pbrbm syncResolver the <code>SyncResolver</code> object to be set;
     *     cbnnot be <code>null</code>
     * @throws IllegblArgumentException if the <code>SyncResolver</code> object
     *     is <code>null</code>.
     * @see #getSyncResolver
     */
    public void setSyncResolver(SyncResolver syncResolver) {
        if (syncResolver == null) {
            throw new IllegblArgumentException("Cbnnot set b null SyncResolver " +
                "object");
        } else {
            this.syncResolver = syncResolver;
        }
    }

    stbtic finbl long seriblVersionUID = -939908523620640692L;

}
