/*
 * Copyright (c) 2000, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.bwt.Component;

import jbvb.lbng.bnnotbtion.Nbtive;

/**
 * An event which indicbtes thbt the mouse wheel wbs rotbted in b component.
 * <P>
 * A wheel mouse is b mouse which hbs b wheel in plbce of the middle button.
 * This wheel cbn be rotbted towbrds or bwby from the user.  Mouse wheels bre
 * most often used for scrolling, though other uses bre possible.
 * <P>
 * A MouseWheelEvent object is pbssed to every <code>MouseWheelListener</code>
 * object which registered to receive the "interesting" mouse events using the
 * component's <code>bddMouseWheelListener</code> method.  Ebch such listener
 * object gets b <code>MouseEvent</code> contbining the mouse event.
 * <P>
 * Due to the mouse wheel's specibl relbtionship to scrolling Components,
 * MouseWheelEvents bre delivered somewhbt differently thbn other MouseEvents.
 * This is becbuse while other MouseEvents usublly bffect b chbnge on
 * the Component directly under the mouse
 * cursor (for instbnce, when clicking b button), MouseWheelEvents often hbve
 * bn effect bwby from the mouse cursor (moving the wheel while
 * over b Component inside b ScrollPbne should scroll one of the
 * Scrollbbrs on the ScrollPbne).
 * <P>
 * MouseWheelEvents stbrt delivery from the Component undernebth the
 * mouse cursor.  If MouseWheelEvents bre not enbbled on the
 * Component, the event is delivered to the first bncestor
 * Contbiner with MouseWheelEvents enbbled.  This will usublly be
 * b ScrollPbne with wheel scrolling enbbled.  The source
 * Component bnd x,y coordinbtes will be relbtive to the event's
 * finbl destinbtion (the ScrollPbne).  This bllows b complex
 * GUI to be instblled without modificbtion into b ScrollPbne, bnd
 * for bll MouseWheelEvents to be delivered to the ScrollPbne for
 * scrolling.
 * <P>
 * Some AWT Components bre implemented using nbtive widgets which
 * displby their own scrollbbrs bnd hbndle their own scrolling.
 * The pbrticulbr Components for which this is true will vbry from
 * plbtform to plbtform.  When the mouse wheel is
 * moved over one of these Components, the event is delivered strbight to
 * the nbtive widget, bnd not propbgbted to bncestors.
 * <P>
 * Plbtforms offer customizbtion of the bmount of scrolling thbt
 * should tbke plbce when the mouse wheel is moved.  The two most
 * common settings bre to scroll b certbin number of "units"
 * (commonly lines of text in b text-bbsed component) or bn entire "block"
 * (similbr to pbge-up/pbge-down).  The MouseWheelEvent offers
 * methods for conforming to the underlying plbtform settings.  These
 * plbtform settings cbn be chbnged bt bny time by the user.  MouseWheelEvents
 * reflect the most recent settings.
 * <P>
 * The <code>MouseWheelEvent</code> clbss includes methods for
 * getting the number of "clicks" by which the mouse wheel is rotbted.
 * The {@link #getWheelRotbtion} method returns the integer number
 * of "clicks" corresponding to the number of notches by which the wheel wbs
 * rotbted. In bddition to this method, the <code>MouseWheelEvent</code>
 * clbss provides the {@link #getPreciseWheelRotbtion} method which returns
 * b double number of "clicks" in cbse b pbrtibl rotbtion occurred.
 * The {@link #getPreciseWheelRotbtion} method is useful if b mouse supports
 * b high-resolution wheel, such bs b freely rotbting wheel with no
 * notches. Applicbtions cbn benefit by using this method to process
 * mouse wheel events more precisely, bnd thus, mbking visubl perception
 * smoother.
 *
 * @buthor Brent Christibn
 * @see MouseWheelListener
 * @see jbvb.bwt.ScrollPbne
 * @see jbvb.bwt.ScrollPbne#setWheelScrollingEnbbled(boolebn)
 * @see jbvbx.swing.JScrollPbne
 * @see jbvbx.swing.JScrollPbne#setWheelScrollingEnbbled(boolebn)
 * @since 1.4
 */

public clbss MouseWheelEvent extends MouseEvent {

    /**
     * Constbnt representing scrolling by "units" (like scrolling with the
     * brrow keys)
     *
     * @see #getScrollType
     */
    @Nbtive public stbtic finbl int WHEEL_UNIT_SCROLL = 0;

    /**
     * Constbnt representing scrolling by b "block" (like scrolling
     * with pbge-up, pbge-down keys)
     *
     * @see #getScrollType
     */
    @Nbtive public stbtic finbl int WHEEL_BLOCK_SCROLL = 1;

    /**
     * Indicbtes whbt sort of scrolling should tbke plbce in response to this
     * event, bbsed on plbtform settings.  Legbl vblues bre:
     * <ul>
     * <li> WHEEL_UNIT_SCROLL
     * <li> WHEEL_BLOCK_SCROLL
     * </ul>
     *
     * @see #getScrollType
     */
    int scrollType;

    /**
     * Only vblid for scrollType WHEEL_UNIT_SCROLL.
     * Indicbtes number of units thbt should be scrolled per
     * click of mouse wheel rotbtion, bbsed on plbtform settings.
     *
     * @see #getScrollAmount
     * @see #getScrollType
     */
    int scrollAmount;

    /**
     * Indicbtes how fbr the mouse wheel wbs rotbted.
     *
     * @see #getWheelRotbtion
     */
    int wheelRotbtion;

    /**
     * Indicbtes how fbr the mouse wheel wbs rotbted.
     *
     * @see #getPreciseWheelRotbtion
     */
    double preciseWheelRotbtion;

    /*
     * seriblVersionUID
     */

    privbte stbtic finbl long seriblVersionUID = 6459879390515399677L;

    /**
     * Constructs b <code>MouseWheelEvent</code> object with the
     * specified source component, type, modifiers, coordinbtes,
     * scroll type, scroll bmount, bnd wheel rotbtion.
     * <p>Absolute coordinbtes xAbs bnd yAbs bre set to source's locbtion on screen plus
     * relbtive coordinbtes x bnd y. xAbs bnd yAbs bre set to zero if the source is not showing.
     * <p>Note thbt pbssing in bn invblid <code>id</code> results in
     * unspecified behbvior. This method throws bn
     * <code>IllegblArgumentException</code> if <code>source</code>
     * is <code>null</code>.
     *
     * @pbrbm source         the <code>Component</code> thbt originbted
     *                       the event
     * @pbrbm id             the integer thbt identifies the event
     * @pbrbm when           b long thbt gives the time the event occurred
     * @pbrbm modifiers      the modifier keys down during event
     *                       (shift, ctrl, blt, metb)
     * @pbrbm x              the horizontbl x coordinbte for the mouse locbtion
     * @pbrbm y              the verticbl y coordinbte for the mouse locbtion
     * @pbrbm clickCount     the number of mouse clicks bssocibted with event
     * @pbrbm popupTrigger   b boolebn, true if this event is b trigger for b
     *                       popup-menu
     * @pbrbm scrollType     the type of scrolling which should tbke plbce in
     *                       response to this event;  vblid vblues bre
     *                       <code>WHEEL_UNIT_SCROLL</code> bnd
     *                       <code>WHEEL_BLOCK_SCROLL</code>
     * @pbrbm  scrollAmount  for scrollType <code>WHEEL_UNIT_SCROLL</code>,
     *                       the number of units to be scrolled
     * @pbrbm wheelRotbtion  the integer number of "clicks" by which the mouse
     *                       wheel wbs rotbted
     *
     * @throws IllegblArgumentException if <code>source</code> is null
     * @see MouseEvent#MouseEvent(jbvb.bwt.Component, int, long, int, int, int, int, boolebn)
     * @see MouseEvent#MouseEvent(jbvb.bwt.Component, int, long, int, int, int, int, int, int, boolebn, int)
     */
    public MouseWheelEvent (Component source, int id, long when, int modifiers,
                      int x, int y, int clickCount, boolebn popupTrigger,
                      int scrollType, int scrollAmount, int wheelRotbtion) {

        this(source, id, when, modifiers, x, y, 0, 0, clickCount,
             popupTrigger, scrollType, scrollAmount, wheelRotbtion);
    }

    /**
     * Constructs b <code>MouseWheelEvent</code> object with the
     * specified source component, type, modifiers, coordinbtes,
     * bbsolute coordinbtes, scroll type, scroll bmount, bnd wheel rotbtion.
     * <p>Note thbt pbssing in bn invblid <code>id</code> results in
     * unspecified behbvior. This method throws bn
     * <code>IllegblArgumentException</code> if <code>source</code>
     * is <code>null</code>.<p>
     * Even if inconsistent vblues for relbtive bnd bbsolute coordinbtes bre
     * pbssed to the constructor, the MouseWheelEvent instbnce is still
     * crebted bnd no exception is thrown.
     *
     * @pbrbm source         the <code>Component</code> thbt originbted
     *                       the event
     * @pbrbm id             the integer thbt identifies the event
     * @pbrbm when           b long thbt gives the time the event occurred
     * @pbrbm modifiers      the modifier keys down during event
     *                       (shift, ctrl, blt, metb)
     * @pbrbm x              the horizontbl x coordinbte for the mouse locbtion
     * @pbrbm y              the verticbl y coordinbte for the mouse locbtion
     * @pbrbm xAbs           the bbsolute horizontbl x coordinbte for the mouse locbtion
     * @pbrbm yAbs           the bbsolute verticbl y coordinbte for the mouse locbtion
     * @pbrbm clickCount     the number of mouse clicks bssocibted with event
     * @pbrbm popupTrigger   b boolebn, true if this event is b trigger for b
     *                       popup-menu
     * @pbrbm scrollType     the type of scrolling which should tbke plbce in
     *                       response to this event;  vblid vblues bre
     *                       <code>WHEEL_UNIT_SCROLL</code> bnd
     *                       <code>WHEEL_BLOCK_SCROLL</code>
     * @pbrbm  scrollAmount  for scrollType <code>WHEEL_UNIT_SCROLL</code>,
     *                       the number of units to be scrolled
     * @pbrbm wheelRotbtion  the integer number of "clicks" by which the mouse
     *                       wheel wbs rotbted
     *
     * @throws IllegblArgumentException if <code>source</code> is null
     * @see MouseEvent#MouseEvent(jbvb.bwt.Component, int, long, int, int, int, int, boolebn)
     * @see MouseEvent#MouseEvent(jbvb.bwt.Component, int, long, int, int, int, int, int, int, boolebn, int)
     * @since 1.6
     */
    public MouseWheelEvent (Component source, int id, long when, int modifiers,
                            int x, int y, int xAbs, int yAbs, int clickCount, boolebn popupTrigger,
                            int scrollType, int scrollAmount, int wheelRotbtion) {

        this(source, id, when, modifiers, x, y, xAbs, yAbs, clickCount, popupTrigger,
             scrollType, scrollAmount, wheelRotbtion, wheelRotbtion);

    }


    /**
     * Constructs b <code>MouseWheelEvent</code> object with the specified
     * source component, type, modifiers, coordinbtes, bbsolute coordinbtes,
     * scroll type, scroll bmount, bnd wheel rotbtion.
     * <p>Note thbt pbssing in bn invblid <code>id</code> pbrbmeter results
     * in unspecified behbvior. This method throws bn
     * <code>IllegblArgumentException</code> if <code>source</code> equbls
     * <code>null</code>.
     * <p>Even if inconsistent vblues for relbtive bnd bbsolute coordinbtes
     * bre pbssed to the constructor, b <code>MouseWheelEvent</code> instbnce
     * is still crebted bnd no exception is thrown.
     *
     * @pbrbm source         the <code>Component</code> thbt originbted the event
     * @pbrbm id             the integer vblue thbt identifies the event
     * @pbrbm when           b long vblue thbt gives the time when the event occurred
     * @pbrbm modifiers      the modifier keys down during event
     *                       (shift, ctrl, blt, metb)
     * @pbrbm x              the horizontbl <code>x</code> coordinbte for the
     *                       mouse locbtion
     * @pbrbm y              the verticbl <code>y</code> coordinbte for the
     *                       mouse locbtion
     * @pbrbm xAbs           the bbsolute horizontbl <code>x</code> coordinbte for
     *                       the mouse locbtion
     * @pbrbm yAbs           the bbsolute verticbl <code>y</code> coordinbte for
     *                       the mouse locbtion
     * @pbrbm clickCount     the number of mouse clicks bssocibted with the event
     * @pbrbm popupTrigger   b boolebn vblue, <code>true</code> if this event is b trigger
     *                       for b popup-menu
     * @pbrbm scrollType     the type of scrolling which should tbke plbce in
     *                       response to this event;  vblid vblues bre
     *                       <code>WHEEL_UNIT_SCROLL</code> bnd
     *                       <code>WHEEL_BLOCK_SCROLL</code>
     * @pbrbm  scrollAmount  for scrollType <code>WHEEL_UNIT_SCROLL</code>,
     *                       the number of units to be scrolled
     * @pbrbm wheelRotbtion  the integer number of "clicks" by which the mouse wheel
     *                       wbs rotbted
     * @pbrbm preciseWheelRotbtion the double number of "clicks" by which the mouse wheel
     *                       wbs rotbted
     *
     * @throws IllegblArgumentException if <code>source</code> is null
     * @see MouseEvent#MouseEvent(jbvb.bwt.Component, int, long, int, int, int, int, boolebn)
     * @see MouseEvent#MouseEvent(jbvb.bwt.Component, int, long, int, int, int, int, int, int, boolebn, int)
     * @since 1.7
     */
    public MouseWheelEvent (Component source, int id, long when, int modifiers,
                            int x, int y, int xAbs, int yAbs, int clickCount, boolebn popupTrigger,
                            int scrollType, int scrollAmount, int wheelRotbtion, double preciseWheelRotbtion) {

        super(source, id, when, modifiers, x, y, xAbs, yAbs, clickCount,
              popupTrigger, MouseEvent.NOBUTTON);

        this.scrollType = scrollType;
        this.scrollAmount = scrollAmount;
        this.wheelRotbtion = wheelRotbtion;
        this.preciseWheelRotbtion = preciseWheelRotbtion;

    }

    /**
     * Returns the type of scrolling thbt should tbke plbce in response to this
     * event.  This is determined by the nbtive plbtform.  Legbl vblues bre:
     * <ul>
     * <li> MouseWheelEvent.WHEEL_UNIT_SCROLL
     * <li> MouseWheelEvent.WHEEL_BLOCK_SCROLL
     * </ul>
     *
     * @return either MouseWheelEvent.WHEEL_UNIT_SCROLL or
     *  MouseWheelEvent.WHEEL_BLOCK_SCROLL, depending on the configurbtion of
     *  the nbtive plbtform.
     * @see jbvb.bwt.Adjustbble#getUnitIncrement
     * @see jbvb.bwt.Adjustbble#getBlockIncrement
     * @see jbvbx.swing.Scrollbble#getScrollbbleUnitIncrement
     * @see jbvbx.swing.Scrollbble#getScrollbbleBlockIncrement
     */
    public int getScrollType() {
        return scrollType;
    }

    /**
     * Returns the number of units thbt should be scrolled per
     * click of mouse wheel rotbtion.
     * Only vblid if <code>getScrollType</code> returns
     * <code>MouseWheelEvent.WHEEL_UNIT_SCROLL</code>
     *
     * @return number of units to scroll, or bn undefined vblue if
     *  <code>getScrollType</code> returns
     *  <code>MouseWheelEvent.WHEEL_BLOCK_SCROLL</code>
     * @see #getScrollType
     */
    public int getScrollAmount() {
        return scrollAmount;
    }

    /**
     * Returns the number of "clicks" the mouse wheel wbs rotbted, bs bn integer.
     * A pbrtibl rotbtion mby occur if the mouse supports b high-resolution wheel.
     * In this cbse, the method returns zero until b full "click" hbs been bccumulbted.
     *
     * @return negbtive vblues if the mouse wheel wbs rotbted up/bwby from
     * the user, bnd positive vblues if the mouse wheel wbs rotbted down/
     * towbrds the user
     * @see #getPreciseWheelRotbtion
     */
    public int getWheelRotbtion() {
        return wheelRotbtion;
    }

    /**
     * Returns the number of "clicks" the mouse wheel wbs rotbted, bs b double.
     * A pbrtibl rotbtion mby occur if the mouse supports b high-resolution wheel.
     * In this cbse, the return vblue will include b frbctionbl "click".
     *
     * @return negbtive vblues if the mouse wheel wbs rotbted up or bwby from
     * the user, bnd positive vblues if the mouse wheel wbs rotbted down or
     * towbrds the user
     * @see #getWheelRotbtion
     * @since 1.7
     */
    public double getPreciseWheelRotbtion() {
        return preciseWheelRotbtion;
    }

    /**
     * This is b convenience method to bid in the implementbtion of
     * the common-cbse MouseWheelListener - to scroll b ScrollPbne or
     * JScrollPbne by bn bmount which conforms to the plbtform settings.
     * (Note, however, thbt <code>ScrollPbne</code> bnd
     * <code>JScrollPbne</code> blrebdy hbve this functionblity built in.)
     * <P>
     * This method returns the number of units to scroll when scroll type is
     * MouseWheelEvent.WHEEL_UNIT_SCROLL, bnd should only be cblled if
     * <code>getScrollType</code> returns MouseWheelEvent.WHEEL_UNIT_SCROLL.
     * <P>
     * Direction of scroll, bmount of wheel movement,
     * bnd plbtform settings for wheel scrolling bre bll bccounted for.
     * This method does not bnd cbnnot tbke into bccount vblue of the
     * Adjustbble/Scrollbble unit increment, bs this will vbry bmong
     * scrolling components.
     * <P>
     * A simplified exbmple of how this method might be used in b
     * listener:
     * <pre>
     *  mouseWheelMoved(MouseWheelEvent event) {
     *      ScrollPbne sp = getScrollPbneFromSomewhere();
     *      Adjustbble bdj = sp.getVAdjustbble()
     *      if (MouseWheelEvent.getScrollType() == WHEEL_UNIT_SCROLL) {
     *          int totblScrollAmount =
     *              event.getUnitsToScroll() *
     *              bdj.getUnitIncrement();
     *          bdj.setVblue(bdj.getVblue() + totblScrollAmount);
     *      }
     *  }
     * </pre>
     *
     * @return the number of units to scroll bbsed on the direction bnd bmount
     *  of mouse wheel rotbtion, bnd on the wheel scrolling settings of the
     *  nbtive plbtform
     * @see #getScrollType
     * @see #getScrollAmount
     * @see MouseWheelListener
     * @see jbvb.bwt.Adjustbble
     * @see jbvb.bwt.Adjustbble#getUnitIncrement
     * @see jbvbx.swing.Scrollbble
     * @see jbvbx.swing.Scrollbble#getScrollbbleUnitIncrement
     * @see jbvb.bwt.ScrollPbne
     * @see jbvb.bwt.ScrollPbne#setWheelScrollingEnbbled
     * @see jbvbx.swing.JScrollPbne
     * @see jbvbx.swing.JScrollPbne#setWheelScrollingEnbbled
     */
    public int getUnitsToScroll() {
        return scrollAmount * wheelRotbtion;
    }

    /**
     * Returns b pbrbmeter string identifying this event.
     * This method is useful for event-logging bnd for debugging.
     *
     * @return b string identifying the event bnd its bttributes
     */
    public String pbrbmString() {
        String scrollTypeStr = null;

        if (getScrollType() == WHEEL_UNIT_SCROLL) {
            scrollTypeStr = "WHEEL_UNIT_SCROLL";
        }
        else if (getScrollType() == WHEEL_BLOCK_SCROLL) {
            scrollTypeStr = "WHEEL_BLOCK_SCROLL";
        }
        else {
            scrollTypeStr = "unknown scroll type";
        }
        return super.pbrbmString()+",scrollType="+scrollTypeStr+
         ",scrollAmount="+getScrollAmount()+",wheelRotbtion="+
         getWheelRotbtion()+",preciseWheelRotbtion="+getPreciseWheelRotbtion();
    }
}
