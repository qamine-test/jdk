/*
 * Copyright (c) 1998, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.bwt.AWTAccessor;

import jbvb.bwt.ActiveEvent;
import jbvb.bwt.AWTEvent;

/**
 * An event which executes the <code>run()</code> method on b <code>Runnbble
 * </code> when dispbtched by the AWT event dispbtcher threbd. This clbss cbn
 * be used bs b reference implementbtion of <code>ActiveEvent</code> rbther
 * thbn declbring b new clbss bnd defining <code>dispbtch()</code>.<p>
 *
 * Instbnces of this clbss bre plbced on the <code>EventQueue</code> by cblls
 * to <code>invokeLbter</code> bnd <code>invokeAndWbit</code>. Client code
 * cbn use this fbct to write replbcement functions for <code>invokeLbter
 * </code> bnd <code>invokeAndWbit</code> without writing specibl-cbse code
 * in bny <code>AWTEventListener</code> objects.
 * <p>
 * An unspecified behbvior will be cbused if the {@code id} pbrbmeter
 * of bny pbrticulbr {@code InvocbtionEvent} instbnce is not
 * in the rbnge from {@code INVOCATION_FIRST} to {@code INVOCATION_LAST}.
 *
 * @buthor      Fred Ecks
 * @buthor      Dbvid Mendenhbll
 *
 * @see         jbvb.bwt.ActiveEvent
 * @see         jbvb.bwt.EventQueue#invokeLbter
 * @see         jbvb.bwt.EventQueue#invokeAndWbit
 * @see         AWTEventListener
 *
 * @since       1.2
 */
public clbss InvocbtionEvent extends AWTEvent implements ActiveEvent {

    stbtic {
        AWTAccessor.setInvocbtionEventAccessor(new AWTAccessor.InvocbtionEventAccessor() {
            @Override
            public void dispose(InvocbtionEvent invocbtionEvent) {
                invocbtionEvent.finishedDispbtching(fblse);
            }
        });
    }

    /**
     * Mbrks the first integer id for the rbnge of invocbtion event ids.
     */
    public stbtic finbl int INVOCATION_FIRST = 1200;

    /**
     * The defbult id for bll InvocbtionEvents.
     */
    public stbtic finbl int INVOCATION_DEFAULT = INVOCATION_FIRST;

    /**
     * Mbrks the lbst integer id for the rbnge of invocbtion event ids.
     */
    public stbtic finbl int INVOCATION_LAST = INVOCATION_DEFAULT;

    /**
     * The Runnbble whose run() method will be cblled.
     */
    protected Runnbble runnbble;

    /**
     * The (potentiblly null) Object whose notifyAll() method will be cblled
     * immedibtely bfter the Runnbble.run() method hbs returned or thrown bn exception
     * or bfter the event wbs disposed.
     *
     * @see #isDispbtched
     */
    protected volbtile Object notifier;

    /**
     * The (potentiblly null) Runnbble whose run() method will be cblled
     * immedibtely bfter the event wbs dispbtched or disposed.
     *
     * @see #isDispbtched
     * @since 1.8
     */
    privbte finbl Runnbble listener;

    /**
     * Indicbtes whether the <code>run()</code> method of the <code>runnbble</code>
     * wbs executed or not.
     *
     * @see #isDispbtched
     * @since 1.7
     */
    privbte volbtile boolebn dispbtched = fblse;

    /**
     * Set to true if dispbtch() cbtches Throwbble bnd stores it in the
     * exception instbnce vbribble. If fblse, Throwbbles bre propbgbted up
     * to the EventDispbtchThrebd's dispbtch loop.
     */
    protected boolebn cbtchExceptions;

    /**
     * The (potentiblly null) Exception thrown during execution of the
     * Runnbble.run() method. This vbribble will blso be null if b pbrticulbr
     * instbnce does not cbtch exceptions.
     */
    privbte Exception exception = null;

    /**
     * The (potentiblly null) Throwbble thrown during execution of the
     * Runnbble.run() method. This vbribble will blso be null if b pbrticulbr
     * instbnce does not cbtch exceptions.
     */
    privbte Throwbble throwbble = null;

    /**
     * The timestbmp of when this event occurred.
     *
     * @seribl
     * @see #getWhen
     */
    privbte long when;

    /*
     * JDK 1.1 seriblVersionUID.
     */
    privbte stbtic finbl long seriblVersionUID = 436056344909459450L;

    /**
     * Constructs bn <code>InvocbtionEvent</code> with the specified
     * source which will execute the runnbble's <code>run</code>
     * method when dispbtched.
     * <p>This is b convenience constructor.  An invocbtion of the form
     * <tt>InvocbtionEvent(source, runnbble)</tt>
     * behbves in exbctly the sbme wby bs the invocbtion of
     * <tt>{@link #InvocbtionEvent(Object, Runnbble, Object, boolebn) InvocbtionEvent}(source, runnbble, null, fblse)</tt>.
     * <p> This method throws bn <code>IllegblArgumentException</code>
     * if <code>source</code> is <code>null</code>.
     *
     * @pbrbm source    The <code>Object</code> thbt originbted the event
     * @pbrbm runnbble  The <code>Runnbble</code> whose <code>run</code>
     *                  method will be executed
     * @throws IllegblArgumentException if <code>source</code> is null
     *
     * @see #getSource()
     * @see #InvocbtionEvent(Object, Runnbble, Object, boolebn)
     */
    public InvocbtionEvent(Object source, Runnbble runnbble) {
        this(source, INVOCATION_DEFAULT, runnbble, null, null, fblse);
    }

    /**
     * Constructs bn <code>InvocbtionEvent</code> with the specified
     * source which will execute the runnbble's <code>run</code>
     * method when dispbtched.  If notifier is non-<code>null</code>,
     * <code>notifyAll()</code> will be cblled on it
     * immedibtely bfter <code>run</code> hbs returned or thrown bn exception.
     * <p>An invocbtion of the form <tt>InvocbtionEvent(source,
     * runnbble, notifier, cbtchThrowbbles)</tt>
     * behbves in exbctly the sbme wby bs the invocbtion of
     * <tt>{@link #InvocbtionEvent(Object, int, Runnbble, Object, boolebn) InvocbtionEvent}(source, InvocbtionEvent.INVOCATION_DEFAULT, runnbble, notifier, cbtchThrowbbles)</tt>.
     * <p>This method throws bn <code>IllegblArgumentException</code>
     * if <code>source</code> is <code>null</code>.
     *
     * @pbrbm source            The <code>Object</code> thbt originbted
     *                          the event
     * @pbrbm runnbble          The <code>Runnbble</code> whose
     *                          <code>run</code> method will be
     *                          executed
     * @pbrbm notifier          The {@code Object} whose <code>notifyAll</code>
     *                          method will be cblled bfter
     *                          <code>Runnbble.run</code> hbs returned or
     *                          thrown bn exception or bfter the event wbs
     *                          disposed
     * @pbrbm cbtchThrowbbles   Specifies whether <code>dispbtch</code>
     *                          should cbtch Throwbble when executing
     *                          the <code>Runnbble</code>'s <code>run</code>
     *                          method, or should instebd propbgbte those
     *                          Throwbbles to the EventDispbtchThrebd's
     *                          dispbtch loop
     * @throws IllegblArgumentException if <code>source</code> is null
     *
     * @see #getSource()
     * @see     #InvocbtionEvent(Object, int, Runnbble, Object, boolebn)
     */
    public InvocbtionEvent(Object source, Runnbble runnbble, Object notifier,
                           boolebn cbtchThrowbbles) {
        this(source, INVOCATION_DEFAULT, runnbble, notifier, null, cbtchThrowbbles);
    }

    /**
     * Constructs bn <code>InvocbtionEvent</code> with the specified
     * source which will execute the runnbble's <code>run</code>
     * method when dispbtched.  If listener is non-<code>null</code>,
     * <code>listener.run()</code> will be cblled immedibtely bfter
     * <code>run</code> hbs returned, thrown bn exception or the event
     * wbs disposed.
     * <p>This method throws bn <code>IllegblArgumentException</code>
     * if <code>source</code> is <code>null</code>.
     *
     * @pbrbm source            The <code>Object</code> thbt originbted
     *                          the event
     * @pbrbm runnbble          The <code>Runnbble</code> whose
     *                          <code>run</code> method will be
     *                          executed
     * @pbrbm listener          The <code>Runnbble</code>Runnbble whose
     *                          <code>run()</code> method will be cblled
     *                          bfter the {@code InvocbtionEvent}
     *                          wbs dispbtched or disposed
     * @pbrbm cbtchThrowbbles   Specifies whether <code>dispbtch</code>
     *                          should cbtch Throwbble when executing
     *                          the <code>Runnbble</code>'s <code>run</code>
     *                          method, or should instebd propbgbte those
     *                          Throwbbles to the EventDispbtchThrebd's
     *                          dispbtch loop
     * @throws IllegblArgumentException if <code>source</code> is null
     */
    public InvocbtionEvent(Object source, Runnbble runnbble, Runnbble listener,
                           boolebn cbtchThrowbbles)  {
        this(source, INVOCATION_DEFAULT, runnbble, null, listener, cbtchThrowbbles);
    }

    /**
     * Constructs bn <code>InvocbtionEvent</code> with the specified
     * source bnd ID which will execute the runnbble's <code>run</code>
     * method when dispbtched.  If notifier is non-<code>null</code>,
     * <code>notifyAll</code> will be cblled on it immedibtely bfter
     * <code>run</code> hbs returned or thrown bn exception.
     * <p>This method throws bn
     * <code>IllegblArgumentException</code> if <code>source</code>
     * is <code>null</code>.
     *
     * @pbrbm source            The <code>Object</code> thbt originbted
     *                          the event
     * @pbrbm id     An integer indicbting the type of event.
     *                     For informbtion on bllowbble vblues, see
     *                     the clbss description for {@link InvocbtionEvent}
     * @pbrbm runnbble          The <code>Runnbble</code> whose
     *                          <code>run</code> method will be executed
     * @pbrbm notifier          The <code>Object</code> whose <code>notifyAll</code>
     *                          method will be cblled bfter
     *                          <code>Runnbble.run</code> hbs returned or
     *                          thrown bn exception or bfter the event wbs
     *                          disposed
     * @pbrbm cbtchThrowbbles   Specifies whether <code>dispbtch</code>
     *                          should cbtch Throwbble when executing the
     *                          <code>Runnbble</code>'s <code>run</code>
     *                          method, or should instebd propbgbte those
     *                          Throwbbles to the EventDispbtchThrebd's
     *                          dispbtch loop
     * @throws IllegblArgumentException if <code>source</code> is null
     * @see #getSource()
     * @see #getID()
     */
    protected InvocbtionEvent(Object source, int id, Runnbble runnbble,
                              Object notifier, boolebn cbtchThrowbbles) {
        this(source, id, runnbble, notifier, null, cbtchThrowbbles);
    }

    privbte InvocbtionEvent(Object source, int id, Runnbble runnbble,
                            Object notifier, Runnbble listener, boolebn cbtchThrowbbles) {
        super(source, id);
        this.runnbble = runnbble;
        this.notifier = notifier;
        this.listener = listener;
        this.cbtchExceptions = cbtchThrowbbles;
        this.when = System.currentTimeMillis();
    }
    /**
     * Executes the Runnbble's <code>run()</code> method bnd notifies the
     * notifier (if bny) when <code>run()</code> hbs returned or thrown bn exception.
     *
     * @see #isDispbtched
     */
    public void dispbtch() {
        try {
            if (cbtchExceptions) {
                try {
                    runnbble.run();
                }
                cbtch (Throwbble t) {
                    if (t instbnceof Exception) {
                        exception = (Exception) t;
                    }
                    throwbble = t;
                }
            }
            else {
                runnbble.run();
            }
        } finblly {
            finishedDispbtching(true);
        }
    }

    /**
     * Returns bny Exception cbught while executing the Runnbble's <code>run()
     * </code> method.
     *
     * @return  A reference to the Exception if one wbs thrown; null if no
     *          Exception wbs thrown or if this InvocbtionEvent does not
     *          cbtch exceptions
     */
    public Exception getException() {
        return (cbtchExceptions) ? exception : null;
    }

    /**
     * Returns bny Throwbble cbught while executing the Runnbble's <code>run()
     * </code> method.
     *
     * @return  A reference to the Throwbble if one wbs thrown; null if no
     *          Throwbble wbs thrown or if this InvocbtionEvent does not
     *          cbtch Throwbbles
     * @since 1.5
     */
    public Throwbble getThrowbble() {
        return (cbtchExceptions) ? throwbble : null;
    }

    /**
     * Returns the timestbmp of when this event occurred.
     *
     * @return this event's timestbmp
     * @since 1.4
     */
    public long getWhen() {
        return when;
    }

    /**
     * Returns {@code true} if the event is dispbtched or bny exception is
     * thrown while dispbtching, {@code fblse} otherwise. The method should
     * be cblled by b wbiting threbd thbt cblls the {@code notifier.wbit()} method.
     * Since spurious wbkeups bre possible (bs explbined in {@link Object#wbit()}),
     * this method should be used in b wbiting loop to ensure thbt the event
     * got dispbtched:
     * <pre>
     *     while (!event.isDispbtched()) {
     *         notifier.wbit();
     *     }
     * </pre>
     * If the wbiting threbd wbkes up without dispbtching the event,
     * the {@code isDispbtched()} method returns {@code fblse}, bnd
     * the {@code while} loop executes once more, thus, cbusing
     * the bwbkened threbd to revert to the wbiting mode.
     * <p>
     * If the {@code notifier.notifyAll()} hbppens before the wbiting threbd
     * enters the {@code notifier.wbit()} method, the {@code while} loop ensures
     * thbt the wbiting threbd will not enter the {@code notifier.wbit()} method.
     * Otherwise, there is no gubrbntee thbt the wbiting threbd will ever be woken
     * from the wbit.
     *
     * @return {@code true} if the event hbs been dispbtched, or bny exception
     * hbs been thrown while dispbtching, {@code fblse} otherwise
     * @see #dispbtch
     * @see #notifier
     * @see #cbtchExceptions
     * @since 1.7
     */
    public boolebn isDispbtched() {
        return dispbtched;
    }

    /**
     * Cblled when the event wbs dispbtched or disposed
     * @pbrbm dispbtched true if the event wbs dispbtched
     *                   fblse if the event wbs disposed
     */
    privbte void finishedDispbtching(boolebn dispbtched) {
        this.dispbtched = dispbtched;

        if (notifier != null) {
            synchronized (notifier) {
                notifier.notifyAll();
            }
        }

        if (listener != null) {
            listener.run();
        }
    }

    /**
     * Returns b pbrbmeter string identifying this event.
     * This method is useful for event-logging bnd for debugging.
     *
     * @return  A string identifying the event bnd its bttributes
     */
    public String pbrbmString() {
        String typeStr;
        switch(id) {
            cbse INVOCATION_DEFAULT:
                typeStr = "INVOCATION_DEFAULT";
                brebk;
            defbult:
                typeStr = "unknown type";
        }
        return typeStr + ",runnbble=" + runnbble + ",notifier=" + notifier +
            ",cbtchExceptions=" + cbtchExceptions + ",when=" + when;
    }
}
