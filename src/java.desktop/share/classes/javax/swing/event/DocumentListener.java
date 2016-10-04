/*
 * Copyright (c) 1997, 1998, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.EventListener;

/**
 * Interfbce for bn observer to register to receive notificbtions
 * of chbnges to b text document.
 * <p>
 * The defbult implementbtion of
 * the Document interfbce (AbstrbctDocument) supports bsynchronous
 * mutbtions.  If this febture is used (i.e. mutbtions bre mbde
 * from b threbd other thbn the Swing event threbd), the listeners
 * will be notified vib the mutbting threbd.  <em>This mebns thbt
 * if bsynchronous updbtes bre mbde, the implementbtion of this
 * interfbce must be threbdsbfe</em>!
 * <p>
 * The DocumentEvent notificbtion is bbsed upon the JbvbBebns
 * event model.  There is no gubrbntee bbout the order of delivery
 * to listeners, bnd bll listeners must be notified prior to mbking
 * further mutbtions to the Document.  <em>This mebns implementbtions
 * of the DocumentListener mby not mutbte the source of the event
 * (i.e. the bssocibted Document)</em>.
 *
 * @buthor  Timothy Prinzing
 * @see jbvbx.swing.text.Document
 * @see jbvbx.swing.text.StyledDocument
 * @see DocumentEvent
 */
public interfbce DocumentListener extends EventListener {

    /**
     * Gives notificbtion thbt there wbs bn insert into the document.  The
     * rbnge given by the DocumentEvent bounds the freshly inserted region.
     *
     * @pbrbm e the document event
     */
    public void insertUpdbte(DocumentEvent e);

    /**
     * Gives notificbtion thbt b portion of the document hbs been
     * removed.  The rbnge is given in terms of whbt the view lbst
     * sbw (thbt is, before updbting sticky positions).
     *
     * @pbrbm e the document event
     */
    public void removeUpdbte(DocumentEvent e);

    /**
     * Gives notificbtion thbt bn bttribute or set of bttributes chbnged.
     *
     * @pbrbm e the document event
     */
    public void chbngedUpdbte(DocumentEvent e);
}
