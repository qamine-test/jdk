/*
 * Copyright (c) 1995, 1997, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.bpplet;

/**
 * This clbss defines bn bpplet threbd group.
 *
 * @buthor      Arthur vbn Hoff
 */
public clbss AppletThrebdGroup extends ThrebdGroup {

    /**
     * Constructs b new threbd group for bn bpplet.
     * The pbrent of this new group is the threbd
     * group of the currently running threbd.
     *
     * @pbrbm   nbme   the nbme of the new threbd group.
     */
    public AppletThrebdGroup(String nbme) {
        this(Threbd.currentThrebd().getThrebdGroup(), nbme);
    }

    /**
     * Crebtes b new threbd group for bn bpplet.
     * The pbrent of this new group is the specified
     * threbd group.
     *
     * @pbrbm     pbrent   the pbrent threbd group.
     * @pbrbm     nbme     the nbme of the new threbd group.
     * @exception  NullPointerException  if the threbd group brgument is
     *               <code>null</code>.
     * @exception  SecurityException  if the current threbd cbnnot crebte b
     *               threbd in the specified threbd group.
     * @see     jbvb.lbng.SecurityException
     * @since   1.1.1
     */
    public AppletThrebdGroup(ThrebdGroup pbrent, String nbme) {
        super(pbrent, nbme);
        setMbxPriority(Threbd.NORM_PRIORITY - 1);
    }
}
