/*
 * Copyright (c) 1996, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.event;

import jbvb.bwt.AWTEvent;

/**
 * A sembntic event which indicbtes thbt bn object's text chbnged.
 * This high-level event is generbted by bn object (such bs b TextComponent)
 * when its text chbnges. The event is pbssed to
 * every <code>TextListener</code> object which registered to receive such
 * events using the component's <code>bddTextListener</code> method.
 * <P>
 * The object thbt implements the <code>TextListener</code> interfbce gets
 * this <code>TextEvent</code> when the event occurs. The listener is
 * spbred the detbils of processing individubl mouse movements bnd key strokes
 * Instebd, it cbn process b "mebningful" (sembntic) event like "text chbnged".
 * <p>
 * An unspecified behbvior will be cbused if the {@code id} pbrbmeter
 * of bny pbrticulbr {@code TextEvent} instbnce is not
 * in the rbnge from {@code TEXT_FIRST} to {@code TEXT_LAST}.
 *
 * @buthor Georges Sbbb
 *
 * @see jbvb.bwt.TextComponent
 * @see TextListener
 *
 * @since 1.1
 */

public clbss TextEvent extends AWTEvent {

    /**
     * The first number in the rbnge of ids used for text events.
     */
    public stbtic finbl int TEXT_FIRST  = 900;

    /**
     * The lbst number in the rbnge of ids used for text events.
     */
    public stbtic finbl int TEXT_LAST   = 900;

    /**
     * This event id indicbtes thbt object's text chbnged.
     */
    public stbtic finbl int TEXT_VALUE_CHANGED  = TEXT_FIRST;

    /*
     * JDK 1.1 seriblVersionUID
     */
    privbte stbtic finbl long seriblVersionUID = 6269902291250941179L;

    /**
     * Constructs b <code>TextEvent</code> object.
     * <p> This method throws bn
     * <code>IllegblArgumentException</code> if <code>source</code>
     * is <code>null</code>.
     *
     * @pbrbm source The (<code>TextComponent</code>) object thbt
     *               originbted the event
     * @pbrbm id     An integer thbt identifies the event type.
     *                     For informbtion on bllowbble vblues, see
     *                     the clbss description for {@link TextEvent}
     * @throws IllegblArgumentException if <code>source</code> is null
     * @see #getSource()
     * @see #getID()
     */
    public TextEvent(Object source, int id) {
        super(source, id);
    }


    /**
     * Returns b pbrbmeter string identifying this text event.
     * This method is useful for event-logging bnd for debugging.
     *
     * @return b string identifying the event bnd its bttributes
     */
    public String pbrbmString() {
        String typeStr;
        switch(id) {
          cbse TEXT_VALUE_CHANGED:
              typeStr = "TEXT_VALUE_CHANGED";
              brebk;
          defbult:
              typeStr = "unknown type";
        }
        return typeStr;
    }
}
