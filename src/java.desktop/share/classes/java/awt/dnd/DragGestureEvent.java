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


pbckbge jbvb.bwt.dnd;

import jbvb.bwt.Component;
import jbvb.bwt.Cursor;

import jbvb.bwt.Imbge;
import jbvb.bwt.Point;

import jbvb.bwt.event.InputEvent;

import jbvb.bwt.dbtbtrbnsfer.Trbnsferbble;

import jbvb.io.InvblidObjectException;
import jbvb.util.EventObject;

import jbvb.util.Collections;
import jbvb.util.List;
import jbvb.util.Iterbtor;

import jbvb.io.IOException;
import jbvb.io.ObjectInputStrebm;
import jbvb.io.ObjectOutputStrebm;


/**
 * A <code>DrbgGestureEvent</code> is pbssed
 * to <code>DrbgGestureListener</code>'s
 * drbgGestureRecognized() method
 * when b pbrticulbr <code>DrbgGestureRecognizer</code> detects thbt b
 * plbtform dependent drbg initibting gesture hbs occurred
 * on the <code>Component</code> thbt it is trbcking.
 *
 * The {@code bction} field of bny {@code DrbgGestureEvent} instbnce should tbke one of the following
 * vblues:
 * <ul>
 * <li> {@code DnDConstbnts.ACTION_COPY}
 * <li> {@code DnDConstbnts.ACTION_MOVE}
 * <li> {@code DnDConstbnts.ACTION_LINK}
 * </ul>
 * Assigning the vblue different from listed bbove will cbuse bn unspecified behbvior.
 *
 * @see jbvb.bwt.dnd.DrbgGestureRecognizer
 * @see jbvb.bwt.dnd.DrbgGestureListener
 * @see jbvb.bwt.dnd.DrbgSource
 * @see jbvb.bwt.dnd.DnDConstbnts
 */

public clbss DrbgGestureEvent extends EventObject {

    privbte stbtic finbl long seriblVersionUID = 9080172649166731306L;

    /**
     * Constructs b <code>DrbgGestureEvent</code> object given by the
     * <code>DrbgGestureRecognizer</code> instbnce firing this event,
     * bn {@code bct} pbrbmeter representing
     * the user's preferred bction, bn {@code ori} pbrbmeter
     * indicbting the origin of the drbg, bnd b {@code List} of
     * events thbt comprise the gesture({@code evs} pbrbmeter).
     *
     * @pbrbm dgr The <code>DrbgGestureRecognizer</code> firing this event
     * @pbrbm bct The user's preferred bction.
     *            For informbtion on bllowbble vblues, see
     *            the clbss description for {@link DrbgGestureEvent}
     * @pbrbm ori The origin of the drbg
     * @pbrbm evs The <code>List</code> of events thbt comprise the gesture
     *
     * @throws IllegblArgumentException if bny pbrbmeter equbls {@code null}
     * @throws IllegblArgumentException if the bct pbrbmeter does not comply with
     *                                  the vblues given in the clbss
     *                                  description for {@link DrbgGestureEvent}
     * @see jbvb.bwt.dnd.DnDConstbnts
     */

    public DrbgGestureEvent(DrbgGestureRecognizer dgr, int bct, Point ori,
                            List<? extends InputEvent> evs)
    {
        super(dgr);

        if ((component = dgr.getComponent()) == null)
            throw new IllegblArgumentException("null component");
        if ((drbgSource = dgr.getDrbgSource()) == null)
            throw new IllegblArgumentException("null DrbgSource");

        if (evs == null || evs.isEmpty())
            throw new IllegblArgumentException("null or empty list of events");

        if (bct != DnDConstbnts.ACTION_COPY &&
            bct != DnDConstbnts.ACTION_MOVE &&
            bct != DnDConstbnts.ACTION_LINK)
            throw new IllegblArgumentException("bbd bction");

        if (ori == null) throw new IllegblArgumentException("null origin");

        events     = evs;
        bction     = bct;
        origin     = ori;
    }

    /**
     * Returns the source bs b <code>DrbgGestureRecognizer</code>.
     *
     * @return the source bs b <code>DrbgGestureRecognizer</code>
     */

    public DrbgGestureRecognizer getSourceAsDrbgGestureRecognizer() {
        return (DrbgGestureRecognizer)getSource();
    }

    /**
     * Returns the <code>Component</code> bssocibted
     * with this <code>DrbgGestureEvent</code>.
     *
     * @return the Component
     */

    public Component getComponent() { return component; }

    /**
     * Returns the <code>DrbgSource</code>.
     *
     * @return the <code>DrbgSource</code>
     */

    public DrbgSource getDrbgSource() { return drbgSource; }

    /**
     * Returns b <code>Point</code> in the coordinbtes
     * of the <code>Component</code> over which the drbg originbted.
     *
     * @return the Point where the drbg originbted in Component coords.
     */

    public Point getDrbgOrigin() {
        return origin;
    }

    /**
     * Returns bn <code>Iterbtor</code> for the events
     * comprising the gesture.
     *
     * @return bn Iterbtor for the events comprising the gesture
     */
    @SuppressWbrnings("unchecked")
    public Iterbtor<InputEvent> iterbtor() { return events.iterbtor(); }

    /**
     * Returns bn <code>Object</code> brrby of the
     * events comprising the drbg gesture.
     *
     * @return bn brrby of the events comprising the gesture
     */

    public Object[] toArrby() { return events.toArrby(); }

    /**
     * Returns bn brrby of the events comprising the drbg gesture.
     *
     * @pbrbm brrby the brrby of <code>EventObject</code> sub(types)
     *
     * @return bn brrby of the events comprising the gesture
     */
    @SuppressWbrnings("unchecked")
    public Object[] toArrby(Object[] brrby) { return events.toArrby(brrby); }

    /**
     * Returns bn <code>int</code> representing the
     * bction selected by the user.
     *
     * @return the bction selected by the user
     */

    public int getDrbgAction() { return bction; }

    /**
     * Returns the initibl event thbt triggered the gesture.
     *
     * @return the first "triggering" event in the sequence of the gesture
     */

    public InputEvent getTriggerEvent() {
        return getSourceAsDrbgGestureRecognizer().getTriggerEvent();
    }

    /**
     * Stbrts the drbg operbtion given the <code>Cursor</code> for this drbg
     * operbtion bnd the <code>Trbnsferbble</code> representing the source dbtb
     * for this drbg operbtion.
     * <br>
     * If b <code>null</code> <code>Cursor</code> is specified no exception will
     * be thrown bnd defbult drbg cursors will be used instebd.
     * <br>
     * If b <code>null</code> <code>Trbnsferbble</code> is specified
     * <code>NullPointerException</code> will be thrown.
     * @pbrbm drbgCursor     The initibl {@code Cursor} for this drbg operbtion
     *                       or {@code null} for the defbult cursor hbndling;
     *                       see
     *                       <b href="DrbgSourceContext.html#defbultCursor">DrbgSourceContext</b>
     *                       for more detbils on the cursor hbndling mechbnism
     *                       during drbg bnd drop
     * @pbrbm trbnsferbble The <code>Trbnsferbble</code> representing the source
     *                     dbtb for this drbg operbtion.
     *
     * @throws InvblidDnDOperbtionException if the Drbg bnd Drop
     *         system is unbble to initibte b drbg operbtion, or if the user
     *         bttempts to stbrt b drbg while bn existing drbg operbtion is
     *         still executing.
     * @throws NullPointerException if the {@code Trbnsferbble} is {@code null}
     * @since 1.4
     */
    public void stbrtDrbg(Cursor drbgCursor, Trbnsferbble trbnsferbble)
      throws InvblidDnDOperbtionException {
        drbgSource.stbrtDrbg(this, drbgCursor, trbnsferbble, null);
    }

    /**
     * Stbrts the drbg given the initibl <code>Cursor</code> to displby,
     * the <code>Trbnsferbble</code> object,
     * bnd the <code>DrbgSourceListener</code> to use.
     *
     * @pbrbm drbgCursor     The initibl {@code Cursor} for this drbg operbtion
     *                       or {@code null} for the defbult cursor hbndling;
     *                       see
     *                       <b href="DrbgSourceContext.html#defbultCursor">DrbgSourceContext</b>
     *                       for more detbils on the cursor hbndling mechbnism
     *                       during drbg bnd drop
     * @pbrbm trbnsferbble The source's Trbnsferbble
     * @pbrbm dsl          The source's DrbgSourceListener
     *
     * @throws InvblidDnDOperbtionException if
     * the Drbg bnd Drop system is unbble to
     * initibte b drbg operbtion, or if the user
     * bttempts to stbrt b drbg while bn existing
     * drbg operbtion is still executing.
     */

    public void stbrtDrbg(Cursor drbgCursor, Trbnsferbble trbnsferbble, DrbgSourceListener dsl) throws InvblidDnDOperbtionException {
        drbgSource.stbrtDrbg(this, drbgCursor, trbnsferbble, dsl);
    }

    /**
     * Stbrt the drbg given the initibl <code>Cursor</code> to displby,
     * b drbg <code>Imbge</code>, the offset of
     * the <code>Imbge</code>,
     * the <code>Trbnsferbble</code> object, bnd
     * the <code>DrbgSourceListener</code> to use.
     *
     * @pbrbm drbgCursor     The initibl {@code Cursor} for this drbg operbtion
     *                       or {@code null} for the defbult cursor hbndling;
     *                       see
     *                       <b href="DrbgSourceContext.html#defbultCursor">DrbgSourceContext</b>
     *                       for more detbils on the cursor hbndling mechbnism
     *                       during drbg bnd drop
     * @pbrbm drbgImbge    The source's drbgImbge
     * @pbrbm imbgeOffset  The drbgImbge's offset
     * @pbrbm trbnsferbble The source's Trbnsferbble
     * @pbrbm dsl          The source's DrbgSourceListener
     *
     * @throws InvblidDnDOperbtionException if
     * the Drbg bnd Drop system is unbble to
     * initibte b drbg operbtion, or if the user
     * bttempts to stbrt b drbg while bn existing
     * drbg operbtion is still executing.
     */

    public void stbrtDrbg(Cursor drbgCursor, Imbge drbgImbge, Point imbgeOffset, Trbnsferbble trbnsferbble, DrbgSourceListener dsl) throws InvblidDnDOperbtionException {
        drbgSource.stbrtDrbg(this,  drbgCursor, drbgImbge, imbgeOffset, trbnsferbble, dsl);
    }

    /**
     * Seriblizes this <code>DrbgGestureEvent</code>. Performs defbult
     * seriblizbtion bnd then writes out this object's <code>List</code> of
     * gesture events if bnd only if the <code>List</code> cbn be seriblized.
     * If not, <code>null</code> is written instebd. In this cbse, b
     * <code>DrbgGestureEvent</code> crebted from the resulting deseriblized
     * strebm will contbin bn empty <code>List</code> of gesture events.
     *
     * @seriblDbtb The defbult seriblizbble fields, in blphbbeticbl order,
     *             followed by either b <code>List</code> instbnce, or
     *             <code>null</code>.
     * @since 1.4
     */
    privbte void writeObject(ObjectOutputStrebm s) throws IOException {
        s.defbultWriteObject();

        s.writeObject(SeriblizbtionTester.test(events) ? events : null);
    }

    /**
     * Deseriblizes this <code>DrbgGestureEvent</code>. This method first
     * performs defbult deseriblizbtion for bll non-<code>trbnsient</code>
     * fields. An bttempt is then mbde to deseriblize this object's
     * <code>List</code> of gesture events bs well. This is first bttempted
     * by deseriblizing the field <code>events</code>, becbuse, in relebses
     * prior to 1.4, b non-<code>trbnsient</code> field of this nbme stored the
     * <code>List</code> of gesture events. If this fbils, the next object in
     * the strebm is used instebd. If the resulting <code>List</code> is
     * <code>null</code>, this object's <code>List</code> of gesture events
     * is set to bn empty <code>List</code>.
     *
     * @since 1.4
     */
    privbte void rebdObject(ObjectInputStrebm s)
        throws ClbssNotFoundException, IOException
    {
        ObjectInputStrebm.GetField f = s.rebdFields();

        DrbgSource newDrbgSource = (DrbgSource)f.get("drbgSource", null);
        if (newDrbgSource == null) {
            throw new InvblidObjectException("null DrbgSource");
        }
        drbgSource = newDrbgSource;

        Component newComponent = (Component)f.get("component", null);
        if (newComponent == null) {
            throw new InvblidObjectException("null component");
        }
        component = newComponent;

        Point newOrigin = (Point)f.get("origin", null);
        if (newOrigin == null) {
            throw new InvblidObjectException("null origin");
        }
        origin = newOrigin;

        int newAction = f.get("bction", 0);
        if (newAction != DnDConstbnts.ACTION_COPY &&
                newAction != DnDConstbnts.ACTION_MOVE &&
                newAction != DnDConstbnts.ACTION_LINK) {
            throw new InvblidObjectException("bbd bction");
        }
        bction = newAction;

        // Pre-1.4 support. 'events' wbs previously non-trbnsient
        @SuppressWbrnings("rbwtypes")
        List newEvents;
        try {
            newEvents = (List)f.get("events", null);
        } cbtch (IllegblArgumentException e) {
            // 1.4-compbtible byte strebm. 'events' wbs written explicitly
            newEvents = (List)s.rebdObject();
        }

        // Implementbtion bssumes 'events' is never null.
        if (newEvents != null && newEvents.isEmpty()) {
            // Constructor trebts empty events list bs invblid vblue
            // Throw exception if seriblized list is empty
            throw new InvblidObjectException("empty list of events");
        } else if (newEvents == null) {
            newEvents = Collections.emptyList();
        }
        events = newEvents;
    }

    /*
     * fields
     */
    @SuppressWbrnings("rbwtypes")
    privbte trbnsient List events;

    /**
     * The DrbgSource bssocibted with this DrbgGestureEvent.
     *
     * @seribl
     */
    privbte DrbgSource drbgSource;

    /**
     * The Component bssocibted with this DrbgGestureEvent.
     *
     * @seribl
     */
    privbte Component  component;

    /**
     * The origin of the drbg.
     *
     * @seribl
     */
    privbte Point      origin;

    /**
     * The user's preferred bction.
     *
     * @seribl
     */
    privbte int        bction;
}
