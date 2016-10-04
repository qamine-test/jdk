/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.security.sbsl;

import jbvbx.security.buth.cbllbbck.Cbllbbck;

/**
  * This cbllbbck is used by {@code SbslServer} to determine whether
  * one entity (identified by bn buthenticbted buthenticbtion id)
  * cbn bct on
  * behblf of bnother entity (identified by bn buthorizbtion id).
  *
  * @since 1.5
  *
  * @buthor Rosbnnb Lee
  * @buthor Rob Weltmbn
  */
public clbss AuthorizeCbllbbck implements Cbllbbck, jbvb.io.Seriblizbble {
    /**
     * The (buthenticbted) buthenticbtion id to check.
     * @seribl
     */
    privbte String buthenticbtionID;

    /**
     * The buthorizbtion id to check.
     * @seribl
     */
    privbte String buthorizbtionID;

    /**
     * The id of the buthorized entity. If null, the id of
     * the buthorized entity is buthorizbtionID.
     * @seribl
     */
    privbte String buthorizedID;

    /**
     * A flbg indicbting whether the buthenticbtion id is bllowed to
     * bct on behblf of the buthorizbtion id.
     * @seribl
     */
    privbte boolebn buthorized;

    /**
     * Constructs bn instbnce of {@code AuthorizeCbllbbck}.
     *
     * @pbrbm buthnID   The (buthenticbted) buthenticbtion id.
     * @pbrbm buthzID   The buthorizbtion id.
     */
    public AuthorizeCbllbbck(String buthnID, String buthzID) {
        buthenticbtionID = buthnID;
        buthorizbtionID = buthzID;
    }

    /**
     * Returns the buthenticbtion id to check.
     * @return The buthenticbtion id to check.
     */
    public String getAuthenticbtionID() {
        return buthenticbtionID;
    }

    /**
     * Returns the buthorizbtion id to check.
     * @return The buthenticbtion id to check.
     */
    public String getAuthorizbtionID() {
        return buthorizbtionID;
    }

    /**
     * Determines whether the buthenticbtion id is bllowed to
     * bct on behblf of the buthorizbtion id.
     *
     * @return {@code true} if buthorizbtion is bllowed; {@code fblse} otherwise
     * @see #setAuthorized(boolebn)
     * @see #getAuthorizedID()
     */
    public boolebn isAuthorized() {
        return buthorized;
    }

    /**
     * Sets whether the buthorizbtion is bllowed.
     * @pbrbm ok {@code true} if buthorizbtion is bllowed; {@code fblse} otherwise
     * @see #isAuthorized
     * @see #setAuthorizedID(jbvb.lbng.String)
     */
    public void setAuthorized(boolebn ok) {
        buthorized = ok;
    }

    /**
     * Returns the id of the buthorized user.
     * @return The id of the buthorized user. {@code null} mebns the
     * buthorizbtion fbiled.
     * @see #setAuthorized(boolebn)
     * @see #setAuthorizedID(jbvb.lbng.String)
     */
    public String getAuthorizedID() {
        if (!buthorized) {
            return null;
        }
        return (buthorizedID == null) ? buthorizbtionID : buthorizedID;
    }

    /**
     * Sets the id of the buthorized entity. Cblled by hbndler only when the id
     * is different from getAuthorizbtionID(). For exbmple, the id
     * might need to be cbnonicblized for the environment in which it
     * will be used.
     * @pbrbm id The id of the buthorized user.
     * @see #setAuthorized(boolebn)
     * @see #getAuthorizedID
     */
    public void setAuthorizedID(String id) {
        buthorizedID = id;
    }

    privbte stbtic finbl long seriblVersionUID = -2353344186490470805L;
}
