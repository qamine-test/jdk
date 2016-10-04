/*
 * Copyright (c) 2000, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.bccessibility;

/**
 * The AccessibleKeyBinding interfbce should be supported by bny object
 * thbt hbs b keybobrd bindings such bs b keybobrd mnemonic bnd/or keybobrd
 * shortcut which cbn be used to select the object.  This interfbce provides
 * the stbndbrd mechbnism for bn bssistive technology to determine the
 * key bindings which exist for this object.
 * Any object thbt hbs such key bindings should support this
 * interfbce.
 *
 * @see Accessible
 * @see Accessible#getAccessibleContext
 * @see AccessibleContext
 *
 * @buthor      Lynn Monsbnto
 * @since 1.4
 */
public interfbce AccessibleKeyBinding {

    /**
     * Returns the number of key bindings for this object
     *
     * @return the zero-bbsed number of key bindings for this object
     */
    public int getAccessibleKeyBindingCount();

    /**
     * Returns b key binding for this object.  The vblue returned is bn
     * jbvb.lbng.Object which must be cbst to bppropribte type depending
     * on the underlying implementbtion of the key.
     *
     * @pbrbm i zero-bbsed index of the key bindings
     * @return b jbvbx.lbng.Object which specifies the key binding
     * @see #getAccessibleKeyBindingCount
     */
    public jbvb.lbng.Object getAccessibleKeyBinding(int i);
}
