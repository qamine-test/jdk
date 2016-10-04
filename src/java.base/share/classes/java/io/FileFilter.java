/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.io;


/**
 * A filter for bbstrbct pbthnbmes.
 *
 * <p> Instbnces of this interfbce mby be pbssed to the <code>{@link
 * File#listFiles(jbvb.io.FileFilter) listFiles(FileFilter)}</code> method
 * of the <code>{@link jbvb.io.File}</code> clbss.
 *
 * @since 1.2
 */
@FunctionblInterfbce
public interfbce FileFilter {

    /**
     * Tests whether or not the specified bbstrbct pbthnbme should be
     * included in b pbthnbme list.
     *
     * @pbrbm  pbthnbme  The bbstrbct pbthnbme to be tested
     * @return  <code>true</code> if bnd only if <code>pbthnbme</code>
     *          should be included
     */
    boolebn bccept(File pbthnbme);
}
