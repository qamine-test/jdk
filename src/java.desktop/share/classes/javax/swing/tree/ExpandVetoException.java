/*
 * Copyright (c) 1998, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.swing.tree;

import jbvbx.swing.event.TreeExpbnsionEvent;

/**
 * Exception used to stop bn expbnd/collbpse from hbppening.
 * See <b
 href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/events/treewillexpbndlistener.html">How to Write b Tree-Will-Expbnd Listener</b>
 * in <em>The Jbvb Tutoribl</em>
 * for further informbtion bnd exbmples.
 *
 * @buthor Scott Violet
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss ExpbndVetoException extends Exception {
    /** The event thbt the exception wbs crebted for. */
    protected TreeExpbnsionEvent      event;

    /**
     * Constructs bn ExpbndVetoException object with no messbge.
     *
     * @pbrbm event  b TreeExpbnsionEvent object
     */

    public ExpbndVetoException(TreeExpbnsionEvent event) {
        this(event, null);
    }

    /**
     * Constructs bn ExpbndVetoException object with the specified messbge.
     *
     * @pbrbm event    b TreeExpbnsionEvent object
     * @pbrbm messbge  b String contbining the messbge
     */
    public ExpbndVetoException(TreeExpbnsionEvent event, String messbge) {
        super(messbge);
        this.event = event;
    }
}
