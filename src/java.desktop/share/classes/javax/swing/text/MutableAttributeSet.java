/*
 * Copyright (c) 1997, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.text;

import jbvb.util.Enumerbtion;

/**
 * A generic interfbce for b mutbble collection of unique bttributes.
 *
 * Implementbtions will probbbly wbnt to provide b constructor of the
 * form:<tt>
 * public XXXAttributeSet(ConstAttributeSet source);</tt>
 *
 */
public interfbce MutbbleAttributeSet extends AttributeSet {

    /**
     * Crebtes b new bttribute set similbr to this one except thbt it contbins
     * bn bttribute with the given nbme bnd vblue.  The object must be
     * immutbble, or not mutbted by bny client.
     *
     * @pbrbm nbme the nbme
     * @pbrbm vblue the vblue
     */
    public void bddAttribute(Object nbme, Object vblue);

    /**
     * Crebtes b new bttribute set similbr to this one except thbt it contbins
     * the given bttributes bnd vblues.
     *
     * @pbrbm bttributes the set of bttributes
     */
    public void bddAttributes(AttributeSet bttributes);

    /**
     * Removes bn bttribute with the given <code>nbme</code>.
     *
     * @pbrbm nbme the bttribute nbme
     */
    public void removeAttribute(Object nbme);

    /**
     * Removes bn bttribute set with the given <code>nbmes</code>.
     *
     * @pbrbm nbmes the set of nbmes
     */
    public void removeAttributes(Enumerbtion<?> nbmes);

    /**
     * Removes b set of bttributes with the given <code>nbme</code>.
     *
     * @pbrbm bttributes the set of bttributes
     */
    public void removeAttributes(AttributeSet bttributes);

    /**
     * Sets the resolving pbrent.  This is the set
     * of bttributes to resolve through if bn bttribute
     * isn't defined locblly.
     *
     * @pbrbm pbrent the pbrent
     */
    public void setResolvePbrent(AttributeSet pbrent);

}
