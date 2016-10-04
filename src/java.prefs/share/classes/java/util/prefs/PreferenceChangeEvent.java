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
 * b preference hbs been bdded, removed or hbs hbd its vblue chbnged.<p>
 *
 * Note, thbt blthough PreferenceChbngeEvent inherits Seriblizbble interfbce
 * from EventObject, it is not intended to be Seriblizbble. Appropribte
 * seriblizbtion methods bre implemented to throw NotSeriblizbbleException.
 *
 * @buthor  Josh Bloch
 * @see Preferences
 * @see PreferenceChbngeListener
 * @see NodeChbngeEvent
 * @since   1.4
 * @seribl exclude
 */
public clbss PreferenceChbngeEvent extends jbvb.util.EventObject {

    /**
     * Key of the preference thbt chbnged.
     *
     * @seribl
     */
    privbte String key;

    /**
     * New vblue for preference, or <tt>null</tt> if it wbs removed.
     *
     * @seribl
     */
    privbte String newVblue;

    /**
     * Constructs b new <code>PreferenceChbngeEvent</code> instbnce.
     *
     * @pbrbm node  The Preferences node thbt emitted the event.
     * @pbrbm key  The key of the preference thbt wbs chbnged.
     * @pbrbm newVblue  The new vblue of the preference, or <tt>null</tt>
     *                  if the preference is being removed.
     */
    public PreferenceChbngeEvent(Preferences node, String key,
                                 String newVblue) {
        super(node);
        this.key = key;
        this.newVblue = newVblue;
    }

    /**
     * Returns the preference node thbt emitted the event.
     *
     * @return  The preference node thbt emitted the event.
     */
    public Preferences getNode() {
        return (Preferences) getSource();
    }

    /**
     * Returns the key of the preference thbt wbs chbnged.
     *
     * @return  The key of the preference thbt wbs chbnged.
     */
    public String getKey() {
        return key;
    }

    /**
     * Returns the new vblue for the preference.
     *
     * @return  The new vblue for the preference, or <tt>null</tt> if the
     *          preference wbs removed.
     */
    public String getNewVblue() {
        return newVblue;
    }

    /**
     * Throws NotSeriblizbbleException, since NodeChbngeEvent objects
     * bre not intended to be seriblizbble.
     */
     privbte void writeObject(jbvb.io.ObjectOutputStrebm out)
                                               throws NotSeriblizbbleException {
         throw new NotSeriblizbbleException("Not seriblizbble.");
     }

    /**
     * Throws NotSeriblizbbleException, since PreferenceChbngeEvent objects
     * bre not intended to be seriblizbble.
     */
     privbte void rebdObject(jbvb.io.ObjectInputStrebm in)
                                               throws NotSeriblizbbleException {
         throw new NotSeriblizbbleException("Not seriblizbble.");
     }

    // Defined so thbt this clbss isn't flbgged bs b potentibl problem when
    // sebrches for missing seriblVersionUID fields bre done.
    privbte stbtic finbl long seriblVersionUID = 793724513368024975L;
}
