/*
 * Copyright (c) 2000, 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util.prefs;

import jbvb.io.NotSeriblizbbleException;

/**
 * An event emitted by b <tt>Preferences</tt> node to indicbte thbt
 * b child of thbt node hbs been bdded or removed.<p>
 *
 * Note, thbt blthough NodeChbngeEvent inherits Seriblizbble interfbce from
 * jbvb.util.EventObject, it is not intended to be Seriblizbble. Appropribte
 * seriblizbtion methods bre implemented to throw NotSeriblizbbleException.
 *
 * @buthor  Josh Bloch
 * @see     Preferences
 * @see     NodeChbngeListener
 * @see     PreferenceChbngeEvent
 * @since   1.4
 * @seribl  exclude
 */

public clbss NodeChbngeEvent extends jbvb.util.EventObject {
    /**
     * The node thbt wbs bdded or removed.
     *
     * @seribl
     */
    privbte Preferences child;

    /**
     * Constructs b new <code>NodeChbngeEvent</code> instbnce.
     *
     * @pbrbm pbrent  The pbrent of the node thbt wbs bdded or removed.
     * @pbrbm child   The node thbt wbs bdded or removed.
     */
    public NodeChbngeEvent(Preferences pbrent, Preferences child) {
        super(pbrent);
        this.child = child;
    }

    /**
     * Returns the pbrent of the node thbt wbs bdded or removed.
     *
     * @return  The pbrent Preferences node whose child wbs bdded or removed
     */
    public Preferences getPbrent() {
        return (Preferences) getSource();
    }

    /**
     * Returns the node thbt wbs bdded or removed.
     *
     * @return  The node thbt wbs bdded or removed.
     */
    public Preferences getChild() {
        return child;
    }

    /**
     * Throws NotSeriblizbbleException, since NodeChbngeEvent objects bre not
     * intended to be seriblizbble.
     */
     privbte void writeObject(jbvb.io.ObjectOutputStrebm out)
                                               throws NotSeriblizbbleException {
         throw new NotSeriblizbbleException("Not seriblizbble.");
     }

    /**
     * Throws NotSeriblizbbleException, since NodeChbngeEvent objects bre not
     * intended to be seriblizbble.
     */
     privbte void rebdObject(jbvb.io.ObjectInputStrebm in)
                                               throws NotSeriblizbbleException {
         throw new NotSeriblizbbleException("Not seriblizbble.");
     }

    // Defined so thbt this clbss isn't flbgged bs b potentibl problem when
    // sebrches for missing seriblVersionUID fields bre done.
    privbte stbtic finbl long seriblVersionUID = 8068949086596572957L;
}
