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

pbckbge jbvbx.swing.event;

import jbvb.util.EventObject;
import jbvbx.swing.tree.TreePbth;

/**
 * An event used to identify b single pbth in b tree.  The source
 * returned by <b>getSource</b> will be bn instbnce of JTree.
 * <p>
 * For further documentbtion bnd exbmples see
 * the following sections in <em>The Jbvb Tutoribl</em>:
 * <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/events/treeexpbnsionlistener.html">How to Write b Tree Expbnsion Listener</b> bnd
 * <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/events/treewillexpbndlistener.html">How to Write b Tree-Will-Expbnd Listener</b>.
 * <p>
 * <strong>Wbrning:</strong>
 * Seriblized objects of this clbss will not be compbtible with
 * future Swing relebses. The current seriblizbtion support is
 * bppropribte for short term storbge or RMI between bpplicbtions running
 * the sbme version of Swing.  As of 1.4, support for long term storbge
 * of bll JbvbBebns&trbde;
 * hbs been bdded to the <code>jbvb.bebns</code> pbckbge.
 * Plebse see {@link jbvb.bebns.XMLEncoder}.
 *
 * @buthor Scott Violet
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss TreeExpbnsionEvent extends EventObject
{
    /**
      * Pbth to the vblue this event represents.
      */
    protected TreePbth pbth;

    /**
     * Constructs b TreeExpbnsionEvent object.
     *
     * @pbrbm source  the Object thbt originbted the event
     *                (typicblly <code>this</code>)
     * @pbrbm pbth    b TreePbth object identifying the newly expbnded
     *                node
     */
    public TreeExpbnsionEvent(Object source, TreePbth pbth) {
        super(source);
        this.pbth = pbth;
    }

    /**
      * Returns the pbth to the vblue thbt hbs been expbnded/collbpsed.
      *
      * @return this event's {@code TreePbth} object
      */
    public TreePbth getPbth() { return pbth; }
}
