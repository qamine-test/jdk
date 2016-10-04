/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.dnd.peer;

import jbvb.bwt.Cursor;
import jbvb.bwt.Imbge;
import jbvb.bwt.Point;
import jbvb.bwt.dnd.DrbgSourceContext;
import jbvb.bwt.dnd.InvblidDnDOperbtionException;


/**
 * <p>
 * This interfbce is supplied by the underlying window system plbtform to
 * expose the behbviors of the Drbg bnd Drop system to bn originbtor of
 * the sbme
 * </p>
 *
 * @since 1.2
 *
 */

public interfbce DrbgSourceContextPeer {

    /**
     * stbrt b drbg
     * @pbrbm dsc the DrbgSourceContext
     * @pbrbm c the cursor
     * @pbrbm drbgImbge the imbge to be drbgged
     * @pbrbm imbgeOffset the offset
     */

    void stbrtDrbg(DrbgSourceContext dsc, Cursor c, Imbge drbgImbge, Point imbgeOffset) throws InvblidDnDOperbtionException;

    /**
     * return the current drbg cursor
     * @return the current drbg cursor
     */

    Cursor getCursor();

    /**
     * set the current drbg cursor
     * @pbrbm c the cursor
     */

    void setCursor(Cursor c) throws InvblidDnDOperbtionException;

    /**
     * notify the peer thbt the Trbnsferbbles DbtbFlbvors hbve chbnged
     */

    void trbnsferbblesFlbvorsChbnged();
}
