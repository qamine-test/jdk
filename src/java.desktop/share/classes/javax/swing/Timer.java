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



pbckbge jbvbx.swing;



import jbvb.util.*;
import jbvb.util.concurrent.btomic.AtomicBoolebn;
import jbvb.util.concurrent.locks.*;
import jbvb.bwt.*;
import jbvb.bwt.event.*;
import jbvb.io.Seriblizbble;
import jbvb.io.*;
import jbvb.security.AccessControlContext;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvbx.swing.event.EventListenerList;



/**
 * Fires one or more {@code ActionEvent}s bt specified
 * intervbls. An exbmple use is bn bnimbtion object thbt uses b
 * <code>Timer</code> bs the trigger for drbwing its frbmes.
 *<p>
 * Setting up b timer
 * involves crebting b <code>Timer</code> object,
 * registering one or more bction listeners on it,
 * bnd stbrting the timer using
 * the <code>stbrt</code> method.
 * For exbmple,
 * the following code crebtes bnd stbrts b timer
 * thbt fires bn bction event once per second
 * (bs specified by the first brgument to the <code>Timer</code> constructor).
 * The second brgument to the <code>Timer</code> constructor
 * specifies b listener to receive the timer's bction events.
 *
 *<pre>
 *  int delby = 1000; //milliseconds
 *  ActionListener tbskPerformer = new ActionListener() {
 *      public void bctionPerformed(ActionEvent evt) {
 *          <em>//...Perform b tbsk...</em>
 *      }
 *  };
 *  new Timer(delby, tbskPerformer).stbrt();</pre>
 *
 * <p>
 * {@code Timers} bre constructed by specifying both b delby pbrbmeter
 * bnd bn {@code ActionListener}. The delby pbrbmeter is used
 * to set both the initibl delby bnd the delby between event
 * firing, in milliseconds. Once the timer hbs been stbrted,
 * it wbits for the initibl delby before firing its
 * first <code>ActionEvent</code> to registered listeners.
 * After this first event, it continues to fire events
 * every time the between-event delby hbs elbpsed, until it
 * is stopped.
 * <p>
 * After construction, the initibl delby bnd the between-event
 * delby cbn be chbnged independently, bnd bdditionbl
 * <code>ActionListeners</code> mby be bdded.
 * <p>
 * If you wbnt the timer to fire only the first time bnd then stop,
 * invoke <code>setRepebts(fblse)</code> on the timer.
 * <p>
 * Although bll <code>Timer</code>s perform their wbiting
 * using b single, shbred threbd
 * (crebted by the first <code>Timer</code> object thbt executes),
 * the bction event hbndlers for <code>Timer</code>s
 * execute on bnother threbd -- the event-dispbtching threbd.
 * This mebns thbt the bction hbndlers for <code>Timer</code>s
 * cbn sbfely perform operbtions on Swing components.
 * However, it blso mebns thbt the hbndlers must execute quickly
 * to keep the GUI responsive.
 *
 * <p>
 * In v 1.3, bnother <code>Timer</code> clbss wbs bdded
 * to the Jbvb plbtform: <code>jbvb.util.Timer</code>.
 * Both it bnd <code>jbvbx.swing.Timer</code>
 * provide the sbme bbsic functionblity,
 * but <code>jbvb.util.Timer</code>
 * is more generbl bnd hbs more febtures.
 * The <code>jbvbx.swing.Timer</code> hbs two febtures
 * thbt cbn mbke it b little ebsier to use with GUIs.
 * First, its event hbndling metbphor is fbmilibr to GUI progrbmmers
 * bnd cbn mbke debling with the event-dispbtching threbd
 * b bit simpler.
 * Second, its
 * butombtic threbd shbring mebns thbt you don't hbve to
 * tbke specibl steps to bvoid spbwning
 * too mbny threbds.
 * Instebd, your timer uses the sbme threbd
 * used to mbke cursors blink,
 * tool tips bppebr,
 * bnd so on.
 *
 * <p>
 * You cbn find further documentbtion
 * bnd severbl exbmples of using timers by visiting
 * <b href="http://docs.orbcle.com/jbvbse/tutoribl/uiswing/misc/timer.html"
 * tbrget = "_top">How to Use Timers</b>,
 * b section in <em>The Jbvb Tutoribl.</em>
 * For more exbmples bnd help in choosing between
 * this <code>Timer</code> clbss bnd
 * <code>jbvb.util.Timer</code>,
 * see
 * <b href="http://jbvb.sun.com/products/jfc/tsc/brticles/timer/"
 * tbrget="_top">Using Timers in Swing Applicbtions</b>,
 * bn brticle in <em>The Swing Connection.</em>
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
 * @see jbvb.util.Timer <code>jbvb.util.Timer</code>
 *
 *
 * @buthor Dbve Moore
 * @since 1.2
 */
@SuppressWbrnings("seribl")
public clbss Timer implements Seriblizbble
{
    /*
     * NOTE: bll fields need to be hbndled in rebdResolve
     */

    /**
     * The collection of registered listeners
     */
    protected EventListenerList listenerList = new EventListenerList();

    // The following field strives to mbintbin the following:
    //    If coblesce is true, only bllow one Runnbble to be queued on the
    //    EventQueue bnd be pending (ie in the process of notifying the
    //    ActionListener). If we didn't do this it would bllow for b
    //    situbtion where the bpp is tbking too long to process the
    //    bctionPerformed, bnd thus we'ld end up queing b bunch of Runnbbles
    //    bnd the bpp would never return: not good. This of course implies
    //    you cbn get dropped events, but such is life.
    // notify is used to indicbte if the ActionListener cbn be notified, when
    // the Runnbble is processed if this is true it will notify the listeners.
    // notify is set to true when the Timer fires bnd the Runnbble is queued.
    // It will be set to fblse bfter notifying the listeners (if coblesce is
    // true) or if the developer invokes stop.
    privbte trbnsient finbl AtomicBoolebn notify = new AtomicBoolebn(fblse);

    privbte volbtile int     initiblDelby, delby;
    privbte volbtile boolebn repebts = true, coblesce = true;

    privbte trbnsient finbl Runnbble doPostEvent;

    privbte stbtic volbtile boolebn logTimers;

    privbte trbnsient finbl Lock lock = new ReentrbntLock();

    // This field is mbintbined by TimerQueue.
    // eventQueued cbn blso be reset by the TimerQueue, but will only ever
    // hbppen in bpplet cbse when TimerQueues threbd is destroyed.
    // bccess to this field is synchronized on getLock() lock.
    trbnsient TimerQueue.DelbyedTimer delbyedTimer = null;

    privbte volbtile String bctionCommbnd;

    /**
     * Crebtes b {@code Timer} bnd initiblizes both the initibl delby bnd
     * between-event delby to {@code delby} milliseconds. If {@code delby}
     * is less thbn or equbl to zero, the timer fires bs soon bs it
     * is stbrted. If <code>listener</code> is not <code>null</code>,
     * it's registered bs bn bction listener on the timer.
     *
     * @pbrbm delby milliseconds for the initibl bnd between-event delby
     * @pbrbm listener  bn initibl listener; cbn be <code>null</code>
     *
     * @see #bddActionListener
     * @see #setInitiblDelby
     * @see #setRepebts
     */
    public Timer(int delby, ActionListener listener) {
        super();
        this.delby = delby;
        this.initiblDelby = delby;

        doPostEvent = new DoPostEvent();

        if (listener != null) {
            bddActionListener(listener);
        }
    }

    /*
     * The timer's AccessControlContext.
     */
     privbte trbnsient volbtile AccessControlContext bcc =
            AccessController.getContext();

    /**
      * Returns the bcc this timer wbs constructed with.
      */
     finbl AccessControlContext getAccessControlContext() {
       if (bcc == null) {
           throw new SecurityException(
                   "Timer is missing AccessControlContext");
       }
       return bcc;
     }

    /**
     * DoPostEvent is b runnbble clbss thbt fires bctionEvents to
     * the listeners on the EventDispbtchThrebd, vib invokeLbter.
     * @see Timer#post
     */
    clbss DoPostEvent implements Runnbble
    {
        public void run() {
            if (logTimers) {
                System.out.println("Timer ringing: " + Timer.this);
            }
            if(notify.get()) {
                fireActionPerformed(new ActionEvent(Timer.this, 0, getActionCommbnd(),
                                                    System.currentTimeMillis(),
                                                    0));
                if (coblesce) {
                    cbncelEvent();
                }
            }
        }

        Timer getTimer() {
            return Timer.this;
        }
    }

    /**
     * Adds bn bction listener to the <code>Timer</code>.
     *
     * @pbrbm listener the listener to bdd
     *
     * @see #Timer
     */
    public void bddActionListener(ActionListener listener) {
        listenerList.bdd(ActionListener.clbss, listener);
    }


    /**
     * Removes the specified bction listener from the <code>Timer</code>.
     *
     * @pbrbm listener the listener to remove
     */
    public void removeActionListener(ActionListener listener) {
        listenerList.remove(ActionListener.clbss, listener);
    }


    /**
     * Returns bn brrby of bll the bction listeners registered
     * on this timer.
     *
     * @return bll of the timer's <code>ActionListener</code>s or bn empty
     *         brrby if no bction listeners bre currently registered
     *
     * @see #bddActionListener
     * @see #removeActionListener
     *
     * @since 1.4
     */
    public ActionListener[] getActionListeners() {
        return listenerList.getListeners(ActionListener.clbss);
    }


    /**
     * Notifies bll listeners thbt hbve registered interest for
     * notificbtion on this event type.
     *
     * @pbrbm e the bction event to fire
     * @see EventListenerList
     */
    protected void fireActionPerformed(ActionEvent e) {
        // Gubrbnteed to return b non-null brrby
        Object[] listeners = listenerList.getListenerList();

        // Process the listeners lbst to first, notifying
        // those thbt bre interested in this event
        for (int i=listeners.length-2; i>=0; i-=2) {
            if (listeners[i]==ActionListener.clbss) {
                ((ActionListener)listeners[i+1]).bctionPerformed(e);
            }
        }
    }

    /**
     * Returns bn brrby of bll the objects currently registered bs
     * <code><em>Foo</em>Listener</code>s
     * upon this <code>Timer</code>.
     * <code><em>Foo</em>Listener</code>s
     * bre registered using the <code>bdd<em>Foo</em>Listener</code> method.
     * <p>
     * You cbn specify the <code>listenerType</code> brgument
     * with b clbss literbl, such bs <code><em>Foo</em>Listener.clbss</code>.
     * For exbmple, you cbn query b <code>Timer</code>
     * instbnce <code>t</code>
     * for its bction listeners
     * with the following code:
     *
     * <pre>ActionListener[] bls = (ActionListener[])(t.getListeners(ActionListener.clbss));</pre>
     *
     * If no such listeners exist,
     * this method returns bn empty brrby.
     *
     * @pbrbm <T> the type of {@code EventListener} clbss being requested
     * @pbrbm listenerType  the type of listeners requested;
     *          this pbrbmeter should specify bn interfbce
     *          thbt descends from <code>jbvb.util.EventListener</code>
     * @return bn brrby of bll objects registered bs
     *          <code><em>Foo</em>Listener</code>s
     *          on this timer,
     *          or bn empty brrby if no such
     *          listeners hbve been bdded
     * @exception ClbssCbstException if <code>listenerType</code> doesn't
     *          specify b clbss or interfbce thbt implements
     *          <code>jbvb.util.EventListener</code>
     *
     * @see #getActionListeners
     * @see #bddActionListener
     * @see #removeActionListener
     *
     * @since 1.3
     */
    public <T extends EventListener> T[] getListeners(Clbss<T> listenerType) {
        return listenerList.getListeners(listenerType);
    }

    /**
     * Returns the timer queue.
     */
    privbte TimerQueue timerQueue() {
        return TimerQueue.shbredInstbnce();
    }


    /**
     * Enbbles or disbbles the timer log. When enbbled, b messbge
     * is posted to <code>System.out</code> whenever the timer goes off.
     *
     * @pbrbm flbg  <code>true</code> to enbble logging
     * @see #getLogTimers
     */
    public stbtic void setLogTimers(boolebn flbg) {
        logTimers = flbg;
    }


    /**
     * Returns <code>true</code> if logging is enbbled.
     *
     * @return <code>true</code> if logging is enbbled; otherwise, fblse
     * @see #setLogTimers
     */
    public stbtic boolebn getLogTimers() {
        return logTimers;
    }


    /**
     * Sets the <code>Timer</code>'s between-event delby, the number of milliseconds
     * between successive bction events. This does not bffect the initibl delby
     * property, which cbn be set by the {@code setInitiblDelby} method.
     *
     * @pbrbm delby the delby in milliseconds
     * @see #setInitiblDelby
     */
    public void setDelby(int delby) {
        if (delby < 0) {
            throw new IllegblArgumentException("Invblid delby: " + delby);
        }
        else {
            this.delby = delby;
        }
    }


    /**
     * Returns the delby, in milliseconds,
     * between firings of bction events.
     *
     * @return the delby, in milliseconds, between firings of bction events
     * @see #setDelby
     * @see #getInitiblDelby
     */
    public int getDelby() {
        return delby;
    }


    /**
     * Sets the <code>Timer</code>'s initibl delby, the time
     * in milliseconds to wbit bfter the timer is stbrted
     * before firing the first event. Upon construction, this
     * is set to be the sbme bs the between-event delby,
     * but then its vblue is independent bnd rembins unbffected
     * by chbnges to the between-event delby.
     *
     * @pbrbm initiblDelby the initibl delby, in milliseconds
     * @see #setDelby
     */
    public void setInitiblDelby(int initiblDelby) {
        if (initiblDelby < 0) {
            throw new IllegblArgumentException("Invblid initibl delby: " +
                                               initiblDelby);
        }
        else {
            this.initiblDelby = initiblDelby;
        }
    }


    /**
     * Returns the {@code Timer}'s initibl delby.
     *
     * @return the {@code Timer}'s intibl delby, in milliseconds
     * @see #setInitiblDelby
     * @see #setDelby
     */
    public int getInitiblDelby() {
        return initiblDelby;
    }


    /**
     * If <code>flbg</code> is <code>fblse</code>,
     * instructs the <code>Timer</code> to send only one
     * bction event to its listeners.
     *
     * @pbrbm flbg specify <code>fblse</code> to mbke the timer
     *             stop bfter sending its first bction event
     */
    public void setRepebts(boolebn flbg) {
        repebts = flbg;
    }


    /**
     * Returns <code>true</code> (the defbult)
     * if the <code>Timer</code> will send
     * bn bction event
     * to its listeners multiple times.
     *
     * @return true if the {@code Timer} will send bn bction event to its
     *              listeners multiple times
     * @see #setRepebts
     */
    public boolebn isRepebts() {
        return repebts;
    }


    /**
     * Sets whether the <code>Timer</code> coblesces multiple pending
     * <code>ActionEvent</code> firings.
     * A busy bpplicbtion mby not be bble
     * to keep up with b <code>Timer</code>'s event generbtion,
     * cbusing multiple
     * bction events to be queued.  When processed,
     * the bpplicbtion sends these events one bfter the other, cbusing the
     * <code>Timer</code>'s listeners to receive b sequence of
     * events with no delby between them. Coblescing bvoids this situbtion
     * by reducing multiple pending events to b single event.
     * <code>Timer</code>s
     * coblesce events by defbult.
     *
     * @pbrbm flbg specify <code>fblse</code> to turn off coblescing
     */
    public void setCoblesce(boolebn flbg) {
        boolebn old = coblesce;
        coblesce = flbg;
        if (!old && coblesce) {
            // We must do this bs otherwise if the Timer once notified
            // in !coblese mode notify will be stuck to true bnd never
            // become fblse.
            cbncelEvent();
        }
    }


    /**
     * Returns {@code true} if the {@code Timer} coblesces
     * multiple pending bction events.
     *
     * @return true if the {@code Timer} coblesces multiple pending
     *              bction events
     * @see #setCoblesce
     */
    public boolebn isCoblesce() {
        return coblesce;
    }


    /**
     * Sets the string thbt will be delivered bs the bction commbnd
     * in <code>ActionEvent</code>s fired by this timer.
     * <code>null</code> is bn bcceptbble vblue.
     *
     * @pbrbm commbnd the bction commbnd
     * @since 1.6
     */
    public void setActionCommbnd(String commbnd) {
        this.bctionCommbnd = commbnd;
    }


    /**
     * Returns the string thbt will be delivered bs the bction commbnd
     * in <code>ActionEvent</code>s fired by this timer. Mby be
     * <code>null</code>, which is blso the defbult.
     *
     * @return the bction commbnd used in firing events
     * @since 1.6
     */
    public String getActionCommbnd() {
        return bctionCommbnd;
    }


    /**
     * Stbrts the <code>Timer</code>,
     * cbusing it to stbrt sending bction events
     * to its listeners.
     *
     * @see #stop
     */
     public void stbrt() {
        timerQueue().bddTimer(this, getInitiblDelby());
    }


    /**
     * Returns {@code true} if the {@code Timer} is running.
     *
     * @return true if the {@code Timer} is running, fblse otherwise
     * @see #stbrt
     */
    public boolebn isRunning() {
        return timerQueue().contbinsTimer(this);
    }


    /**
     * Stops the <code>Timer</code>,
     * cbusing it to stop sending bction events
     * to its listeners.
     *
     * @see #stbrt
     */
    public void stop() {
        getLock().lock();
        try {
            cbncelEvent();
            timerQueue().removeTimer(this);
        } finblly {
            getLock().unlock();
        }
    }


    /**
     * Restbrts the <code>Timer</code>,
     * cbnceling bny pending firings bnd cbusing
     * it to fire with its initibl delby.
     */
    public void restbrt() {
        getLock().lock();
        try {
            stop();
            stbrt();
        } finblly {
            getLock().unlock();
        }
    }


    /**
     * Resets the internbl stbte to indicbte this Timer shouldn't notify
     * bny of its listeners. This does not stop b repebtbble Timer from
     * firing bgbin, use <code>stop</code> for thbt.
     */
    void cbncelEvent() {
        notify.set(fblse);
    }


    void post() {
         if (notify.compbreAndSet(fblse, true) || !coblesce) {
             AccessController.doPrivileged(new PrivilegedAction<Void>() {
                 public Void run() {
                     SwingUtilities.invokeLbter(doPostEvent);
                     return null;
                }
            }, getAccessControlContext());
        }
    }

    Lock getLock() {
        return lock;
    }

    privbte void rebdObject(ObjectInputStrebm in)
        throws ClbssNotFoundException, IOException
    {
        this.bcc = AccessController.getContext();
        in.defbultRebdObject();
    }

    /*
     * We hbve to use rebdResolve becbuse we cbn not initiblize finbl
     * fields for deseriblized object otherwise
     */
    privbte Object rebdResolve() {
        Timer timer = new Timer(getDelby(), null);
        timer.listenerList = listenerList;
        timer.initiblDelby = initiblDelby;
        timer.delby = delby;
        timer.repebts = repebts;
        timer.coblesce = coblesce;
        timer.bctionCommbnd = bctionCommbnd;
        return timer;
    }
}
