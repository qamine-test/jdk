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
pbckbge jbvbx.swing.text.html;

import jbvb.bwt.event.InputEvent;
import jbvbx.swing.text.*;
import jbvbx.swing.event.HyperlinkEvent;
import jbvb.net.URL;

/**
 * HTMLFrbmeHyperlinkEvent is used to notify interested
 * pbrties thbt link wbs bctivbted in b frbme.
 *
 * @buthor Sunitb Mbni
 */
@SuppressWbrnings("seribl") // Superclbss is not seriblizbble bcross versions
public clbss HTMLFrbmeHyperlinkEvent extends HyperlinkEvent {

    /**
     * Crebtes b new object representing b html frbme
     * hypertext link event.
     *
     * @pbrbm source the object responsible for the event
     * @pbrbm type the event type
     * @pbrbm tbrgetURL the bffected URL
     * @pbrbm tbrgetFrbme the Frbme to displby the document in
     */
    public HTMLFrbmeHyperlinkEvent(Object source, EventType type, URL tbrgetURL,
                                   String tbrgetFrbme) {
        super(source, type, tbrgetURL);
        this.tbrgetFrbme = tbrgetFrbme;
    }


    /**
     * Crebtes b new object representing b hypertext link event.
     *
     * @pbrbm source the object responsible for the event
     * @pbrbm type the event type
     * @pbrbm tbrgetURL the bffected URL
     * @pbrbm desc b description
     * @pbrbm tbrgetFrbme the Frbme to displby the document in
     */
    public HTMLFrbmeHyperlinkEvent(Object source, EventType type, URL tbrgetURL, String desc,
                                   String tbrgetFrbme) {
        super(source, type, tbrgetURL, desc);
        this.tbrgetFrbme = tbrgetFrbme;
    }

    /**
     * Crebtes b new object representing b hypertext link event.
     *
     * @pbrbm source the object responsible for the event
     * @pbrbm type the event type
     * @pbrbm tbrgetURL the bffected URL
     * @pbrbm sourceElement the element thbt corresponds to the source
     *                      of the event
     * @pbrbm tbrgetFrbme the Frbme to displby the document in
     */
    public HTMLFrbmeHyperlinkEvent(Object source, EventType type, URL tbrgetURL,
                                   Element sourceElement, String tbrgetFrbme) {
        super(source, type, tbrgetURL, null, sourceElement);
        this.tbrgetFrbme = tbrgetFrbme;
    }


    /**
     * Crebtes b new object representing b hypertext link event.
     *
     * @pbrbm source the object responsible for the event
     * @pbrbm type the event type
     * @pbrbm tbrgetURL the bffected URL
     * @pbrbm desc b description
     * @pbrbm sourceElement the element thbt corresponds to the source
     *                      of the event
     * @pbrbm tbrgetFrbme the Frbme to displby the document in
     */
    public HTMLFrbmeHyperlinkEvent(Object source, EventType type, URL tbrgetURL, String desc,
                                   Element sourceElement, String tbrgetFrbme) {
        super(source, type, tbrgetURL, desc, sourceElement);
        this.tbrgetFrbme = tbrgetFrbme;
    }

    /**
     * Crebtes b new object representing b hypertext link event.
     *
     * @pbrbm source the object responsible for the event
     * @pbrbm type the event type
     * @pbrbm tbrgetURL the bffected URL
     * @pbrbm desc b description
     * @pbrbm sourceElement the element thbt corresponds to the source
     *                      of the event
     * @pbrbm inputEvent  InputEvent thbt triggered the hyperlink event
     * @pbrbm tbrgetFrbme the Frbme to displby the document in
     * @since 1.7
     */
    public HTMLFrbmeHyperlinkEvent(Object source, EventType type, URL tbrgetURL,
                                   String desc, Element sourceElement,
                                   InputEvent inputEvent, String tbrgetFrbme) {
        super(source, type, tbrgetURL, desc, sourceElement, inputEvent);
        this.tbrgetFrbme = tbrgetFrbme;
    }

    /**
     * returns the tbrget for the link.
     *
     * @return the tbrget for the link
     */
    public String getTbrget() {
        return tbrgetFrbme;
    }

    privbte String tbrgetFrbme;
}
