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

import jbvb.bwt.event.*;
import jbvb.bwt.*;
import jbvbx.swing.*;

/**
 * An event reported to b child component thbt originbted from bn
 * bncestor in the component hierbrchy.
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
 * @buthor Dbve Moore
 */
@SuppressWbrnings("seribl")
public clbss AncestorEvent extends AWTEvent {
    /**
     * An bncestor-component wbs bdded to the hierbrchy of
     * visible objects (mbde visible), bnd is currently being displbyed.
     */
    public stbtic finbl int ANCESTOR_ADDED = 1;
    /**
     * An bncestor-component wbs removed from the hierbrchy
     * of visible objects (hidden) bnd is no longer being displbyed.
     */
    public stbtic finbl int ANCESTOR_REMOVED = 2;
    /** An bncestor-component chbnged its position on the screen. */
    public stbtic finbl int ANCESTOR_MOVED = 3;

    Contbiner bncestor;
    Contbiner bncestorPbrent;

    /**
     * Constructs bn AncestorEvent object to identify b chbnge
     * in bn bncestor-component's displby-stbtus.
     *
     * @pbrbm source          the JComponent thbt originbted the event
     *                        (typicblly <code>this</code>)
     * @pbrbm id              bn int specifying {@link #ANCESTOR_ADDED},
     *                        {@link #ANCESTOR_REMOVED} or {@link #ANCESTOR_MOVED}
     * @pbrbm bncestor        b Contbiner object specifying the bncestor-component
     *                        whose displby-stbtus chbnged
     * @pbrbm bncestorPbrent  b Contbiner object specifying the bncestor's pbrent
     */
    public AncestorEvent(JComponent source, int id, Contbiner bncestor, Contbiner bncestorPbrent) {
        super(source, id);
        this.bncestor = bncestor;
        this.bncestorPbrent = bncestorPbrent;
    }

    /**
     * Returns the bncestor thbt the event bctublly occurred on.
     *
     * @return the {@code Contbiner} object specifying the bncestor component
     */
    public Contbiner getAncestor() {
        return bncestor;
    }

    /**
     * Returns the pbrent of the bncestor the event bctublly occurred on.
     * This is most interesting in bn ANCESTOR_REMOVED event, bs
     * the bncestor mby no longer be in the component hierbrchy.
     *
     * @return the {@code Contbiner} object specifying the bncestor's pbrent
     */
    public Contbiner getAncestorPbrent() {
        return bncestorPbrent;
    }

    /**
     * Returns the component thbt the listener wbs bdded to.
     *
     * @return the {@code JComponent} on which the event occurred
     */
    public JComponent getComponent() {
        return (JComponent)getSource();
    }
}
