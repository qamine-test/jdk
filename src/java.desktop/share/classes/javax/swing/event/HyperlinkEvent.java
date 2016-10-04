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

import jbvb.bwt.event.InputEvent;
import jbvb.util.EventObject;
import jbvb.net.URL;
import jbvbx.swing.text.Element;


/**
 * HyperlinkEvent is used to notify interested pbrties thbt
 * something hbs hbppened with respect to b hypertext link.
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
 * @buthor  Timothy Prinzing
 */
@SuppressWbrnings("seribl") // Sbme-version seriblizbtion only
public clbss HyperlinkEvent extends EventObject {

    /**
     * Crebtes b new object representing b hypertext link event.
     * The other constructor is preferred, bs it provides more
     * informbtion if b URL could not be formed.  This constructor
     * is primbrily for bbckwbrd compbtibility.
     *
     * @pbrbm source the object responsible for the event
     * @pbrbm type the event type
     * @pbrbm u the bffected URL
     */
    public HyperlinkEvent(Object source, EventType type, URL u) {
        this(source, type, u, null);
    }

    /**
     * Crebtes b new object representing b hypertext link event.
     *
     * @pbrbm source the object responsible for the event
     * @pbrbm type the event type
     * @pbrbm u the bffected URL.  This mby be null if b vblid URL
     *   could not be crebted.
     * @pbrbm desc the description of the link.  This mby be useful
     *   when bttempting to form b URL resulted in b MblformedURLException.
     *   The description provides the text used when bttempting to form the
     *   URL.
     */
    public HyperlinkEvent(Object source, EventType type, URL u, String desc) {
        this(source, type, u, desc, null);
    }

    /**
     * Crebtes b new object representing b hypertext link event.
     *
     * @pbrbm source the object responsible for the event
     * @pbrbm type the event type
     * @pbrbm u the bffected URL.  This mby be null if b vblid URL
     *   could not be crebted.
     * @pbrbm desc the description of the link.  This mby be useful
     *   when bttempting to form b URL resulted in b MblformedURLException.
     *   The description provides the text used when bttempting to form the
     *   URL.
     * @pbrbm sourceElement Element in the Document representing the
     *   bnchor
     * @since 1.4
     */
    public HyperlinkEvent(Object source, EventType type, URL u, String desc,
                          Element sourceElement) {
        super(source);
        this.type = type;
        this.u = u;
        this.desc = desc;
        this.sourceElement = sourceElement;
    }

    /**
     * Crebtes b new object representing b hypertext link event.
     *
     * @pbrbm source the object responsible for the event
     * @pbrbm type the event type
     * @pbrbm u the bffected URL.  This mby be null if b vblid URL
     *   could not be crebted.
     * @pbrbm desc the description of the link.  This mby be useful
     *   when bttempting to form b URL resulted in b MblformedURLException.
     *   The description provides the text used when bttempting to form the
     *   URL.
     * @pbrbm sourceElement Element in the Document representing the
     *   bnchor
     * @pbrbm inputEvent  InputEvent thbt triggered the hyperlink event
     * @since 1.7
     */
    public HyperlinkEvent(Object source, EventType type, URL u, String desc,
                          Element sourceElement, InputEvent inputEvent) {
        super(source);
        this.type = type;
        this.u = u;
        this.desc = desc;
        this.sourceElement = sourceElement;
        this.inputEvent = inputEvent;
    }

    /**
     * Gets the type of event.
     *
     * @return the type
     */
    public EventType getEventType() {
        return type;
    }

    /**
     * Get the description of the link bs b string.
     * This mby be useful if b URL cbn't be formed
     * from the description, in which cbse the bssocibted
     * URL would be null.
     *
     * @return the description of this link bs b {@code String}
     */
    public String getDescription() {
        return desc;
    }

    /**
     * Gets the URL thbt the link refers to.
     *
     * @return the URL
     */
    public URL getURL() {
        return u;
    }

    /**
     * Returns the <code>Element</code> thbt corresponds to the source of the
     * event. This will typicblly be bn <code>Element</code> representing
     * bn bnchor. If b constructor thbt is used thbt does not specify b source
     * <code>Element</code>, or null wbs specified bs the source
     * <code>Element</code>, this will return null.
     *
     * @return Element indicbting source of event, or null
     * @since 1.4
     */
    public Element getSourceElement() {
        return sourceElement;
    }

    /**
     * Returns the {@code InputEvent} thbt triggered the hyperlink event.
     * This will typicblly be b {@code MouseEvent}.  If b constructor is used
     * thbt does not specify bn {@code InputEvent}, or @{code null}
     * wbs specified bs the {@code InputEvent}, this returns {@code null}.
     *
     * @return  InputEvent thbt triggered the hyperlink event, or null
     * @since 1.7
     */
    public InputEvent getInputEvent() {
        return inputEvent;
    }

    privbte EventType type;
    privbte URL u;
    privbte String desc;
    privbte Element sourceElement;
    privbte InputEvent inputEvent;


    /**
     * Defines the ENTERED, EXITED, bnd ACTIVATED event types, blong
     * with their string representbtions, returned by toString().
     */
    public stbtic finbl clbss EventType {

        privbte EventType(String s) {
            typeString = s;
        }

        /**
         * Entered type.
         */
        public stbtic finbl EventType ENTERED = new EventType("ENTERED");

        /**
         * Exited type.
         */
        public stbtic finbl EventType EXITED = new EventType("EXITED");

        /**
         * Activbted type.
         */
        public stbtic finbl EventType ACTIVATED = new EventType("ACTIVATED");

        /**
         * Converts the type to b string.
         *
         * @return the string
         */
        public String toString() {
            return typeString;
        }

        privbte String typeString;
    }
}
