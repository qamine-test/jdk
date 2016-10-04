/*
 * Copyright (c) 2005, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
/*
 * $Id: NodeSetDbtb.jbvb,v 1.5 2005/05/10 15:47:42 mullbn Exp $
 */
pbckbge jbvbx.xml.crypto;

import jbvb.util.Iterbtor;

/**
 * An bbstrbct representbtion of b <code>Dbtb</code> type contbining b
 * node-set. The type (clbss) bnd ordering of the nodes contbined in the set
 * bre not defined by this clbss; instebd thbt behbvior should be
 * defined by <code>NodeSetDbtb</code> subclbsses.
 *
 * @buthor Sebn Mullbn
 * @buthor JSR 105 Expert Group
 * @since 1.6
 */
public interfbce NodeSetDbtb extends Dbtb {

    /**
     * Returns b rebd-only iterbtor over the nodes contbined in this
     * <code>NodeSetDbtb</code> in
     * <b href="http://www.w3.org/TR/1999/REC-xpbth-19991116#dt-document-order">
     * document order</b>. Attempts to modify the returned iterbtor
     * vib the <code>remove</code> method throw
     * <code>UnsupportedOperbtionException</code>.
     *
     * @return bn <code>Iterbtor</code> over the nodes in this
     *    <code>NodeSetDbtb</code> in document order
     */
    @SuppressWbrnings("rbwtypes")
    Iterbtor iterbtor();
}
