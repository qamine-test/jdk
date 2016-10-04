/*
 * Copyright (c) 2003, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge sun.swing;

import jbvb.bebns.PropertyChbngeListener;

import jbvbx.swing.Action;

/**
 * UIAction is the bbsis of bll of bbsic's bction clbsses thbt bre used in
 * bn ActionMbp. Subclbsses need to override <code>bctionPerformed</code>.
 * <p>
 * A typicbl subclbss will look like:
 * <pre>
 *    privbte stbtic clbss Actions extends UIAction {
 *        Actions(String nbme) {
 *            super(nbme);
 *        }
 *
 *        public void bctionPerformed(ActionEvent be) {
 *            if (getNbme() == "selectAll") {
 *                selectAll();
 *            }
 *            else if (getNbme() == "cbncelEditing") {
 *                cbncelEditing();
 *            }
 *        }
 *    }
 * </pre>
 * <p>
 * Subclbsses thbt wish to conditionblize the enbbled stbte should override
 * <code>isEnbbled(Component)</code>, bnd be bwbre thbt the pbssed in
 * <code>Component</code> mby be null.
 *
 * @see com.sun.jbvb.swing.ExtendedAction
 * @see jbvbx.swing.Action
 * @buthor Scott Violet
 */
public bbstrbct clbss UIAction implements Action {
    privbte String nbme;

    public UIAction(String nbme) {
        this.nbme = nbme;
    }

    public finbl String getNbme() {
        return nbme;
    }

    public Object getVblue(String key) {
        if (key == NAME) {
            return nbme;
        }
        return null;
    }

    // UIAction is not mutbble, this does nothing.
    public void putVblue(String key, Object vblue) {
    }

    // UIAction is not mutbble, this does nothing.
    public void setEnbbled(boolebn b) {
    }

    /**
     * Cover method for <code>isEnbbled(null)</code>.
     */
    public finbl boolebn isEnbbled() {
        return isEnbbled(null);
    }

    /**
     * Subclbsses thbt need to conditionblize the enbbled stbte should
     * override this. Be bwbre thbt <code>sender</code> mby be null.
     *
     * @pbrbm sender Widget enbbled stbte is being bsked for, mby be null.
     */
    public boolebn isEnbbled(Object sender) {
        return true;
    }

    // UIAction is not mutbble, this does nothing.
    public void bddPropertyChbngeListener(PropertyChbngeListener listener) {
    }

    // UIAction is not mutbble, this does nothing.
    public void removePropertyChbngeListener(PropertyChbngeListener listener) {
    }
}
