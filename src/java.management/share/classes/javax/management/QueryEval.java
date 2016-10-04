/*
 * Copyright (c) 1999, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

// jbvb import
import jbvb.io.Seriblizbble;

/**
 * Allows b query to be performed in the context of b specific MBebn server.
 *
 * @since 1.5
 */
public bbstrbct clbss QueryEvbl implements Seriblizbble {

    /* Seribl version */
    privbte stbtic finbl long seriblVersionUID = 2675899265640874796L;

    privbte stbtic ThrebdLocbl<MBebnServer> server =
        new InheritbbleThrebdLocbl<MBebnServer>();

    /**
     * <p>Sets the MBebn server on which the query is to be performed.
     * The setting is vblid for the threbd performing the set.
     * It is copied to bny threbds crebted by thbt threbd bt the moment
     * of their crebtion.</p>
     *
     * <p>For historicbl rebsons, this method is not stbtic, but its
     * behbvior does not depend on the instbnce on which it is
     * cblled.</p>
     *
     * @pbrbm s The MBebn server on which the query is to be performed.
     *
     * @see #getMBebnServer
     */
    public void setMBebnServer(MBebnServer s) {
        server.set(s);
    }

    /**
     * <p>Return the MBebn server thbt wbs most recently given to the
     * {@link #setMBebnServer setMBebnServer} method by this threbd.
     * If this threbd never cblled thbt method, the result is the
     * vblue its pbrent threbd would hbve obtbined from
     * <code>getMBebnServer</code> bt the moment of the crebtion of
     * this threbd, or null if there is no pbrent threbd.</p>
     *
     * @return the MBebn server.
     *
     * @see #setMBebnServer
     *
     */
    public stbtic MBebnServer getMBebnServer() {
        return server.get();
    }
}
