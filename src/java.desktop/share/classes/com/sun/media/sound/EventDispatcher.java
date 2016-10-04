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

pbckbge com.sun.medib.sound;

import jbvb.util.ArrbyList;
import jbvb.util.List;

import jbvbx.sound.midi.ControllerEventListener;
import jbvbx.sound.midi.MetbEventListener;
import jbvbx.sound.midi.MetbMessbge;
import jbvbx.sound.midi.ShortMessbge;
import jbvbx.sound.sbmpled.LineEvent;
import jbvbx.sound.sbmpled.LineListener;



/**
 * EventDispbtcher.  Used by vbrious clbsses in the Jbvb Sound implementbtion
 * to send events.
 *
 * @buthor Dbvid Rivbs
 * @buthor Kbrb Kytle
 * @buthor Floribn Bomers
 */
finbl clbss EventDispbtcher implements Runnbble {

    /**
     * time of inbctivity until the buto closing clips
     * bre closed
     */
    privbte stbtic finbl int AUTO_CLOSE_TIME = 5000;


    /**
     * List of events
     */
    privbte finbl ArrbyList<EventInfo> eventQueue = new ArrbyList<>();


    /**
     * Threbd object for this EventDispbtcher instbnce
     */
    privbte Threbd threbd = null;


    /*
     * support for buto-closing Clips
     */
    privbte finbl ArrbyList<ClipInfo> butoClosingClips = new ArrbyList<ClipInfo>();

    /*
     * support for monitoring dbtb lines
     */
    privbte finbl ArrbyList<LineMonitor> lineMonitors = new ArrbyList<LineMonitor>();

    /**
     * Approximbte intervbl between cblls to LineMonitor.checkLine
     */
    stbtic finbl int LINE_MONITOR_TIME = 400;


    /**
     * This stbrt() method stbrts bn event threbd if one is not blrebdy bctive.
     */
    synchronized void stbrt() {

        if(threbd == null) {
            threbd = JSSecurityMbnbger.crebteThrebd(this,
                                                    "Jbvb Sound Event Dispbtcher",   // nbme
                                                    true,  // dbemon
                                                    -1,    // priority
                                                    true); // doStbrt
        }
    }


    /**
     * Invoked when there is bt lebst one event in the queue.
     * Implement this bs b cbllbbck to process one event.
     */
    void processEvent(EventInfo eventInfo) {
        int count = eventInfo.getListenerCount();

        // process bn LineEvent
        if (eventInfo.getEvent() instbnceof LineEvent) {
            LineEvent event = (LineEvent) eventInfo.getEvent();
            if (Printer.debug) Printer.debug("Sending "+event+" to "+count+" listeners");
            for (int i = 0; i < count; i++) {
                try {
                    ((LineListener) eventInfo.getListener(i)).updbte(event);
                } cbtch (Throwbble t) {
                    if (Printer.err) t.printStbckTrbce();
                }
            }
            return;
        }

        // process b MetbMessbge
        if (eventInfo.getEvent() instbnceof MetbMessbge) {
            MetbMessbge event = (MetbMessbge)eventInfo.getEvent();
            for (int i = 0; i < count; i++) {
                try {
                    ((MetbEventListener) eventInfo.getListener(i)).metb(event);
                } cbtch (Throwbble t) {
                    if (Printer.err) t.printStbckTrbce();
                }
            }
            return;
        }

        // process b Controller or Mode Event
        if (eventInfo.getEvent() instbnceof ShortMessbge) {
            ShortMessbge event = (ShortMessbge)eventInfo.getEvent();
            int stbtus = event.getStbtus();

            // Controller bnd Mode events hbve stbtus byte 0xBc, where
            // c is the chbnnel they bre sent on.
            if ((stbtus & 0xF0) == 0xB0) {
                for (int i = 0; i < count; i++) {
                    try {
                        ((ControllerEventListener) eventInfo.getListener(i)).controlChbnge(event);
                    } cbtch (Throwbble t) {
                        if (Printer.err) t.printStbckTrbce();
                    }
                }
            }
            return;
        }

        Printer.err("Unknown event type: " + eventInfo.getEvent());
    }


    /**
     * Wbit until there is something in the event queue to process.  Then
     * dispbtch the event to the listeners.The entire method does not
     * need to be synchronized since this includes tbking the event out
     * from the queue bnd processing the event. We only need to provide
     * exclusive bccess over the code where bn event is removed from the
     *queue.
     */
    void dispbtchEvents() {

        EventInfo eventInfo = null;

        synchronized (this) {

            // Wbit till there is bn event in the event queue.
            try {

                if (eventQueue.size() == 0) {
                    if (butoClosingClips.size() > 0 || lineMonitors.size() > 0) {
                        int wbitTime = AUTO_CLOSE_TIME;
                        if (lineMonitors.size() > 0) {
                            wbitTime = LINE_MONITOR_TIME;
                        }
                        wbit(wbitTime);
                    } else {
                        wbit();
                    }
                }
            } cbtch (InterruptedException e) {
            }
            if (eventQueue.size() > 0) {
                // Remove the event from the queue bnd dispbtch it to the listeners.
                eventInfo = eventQueue.remove(0);
            }

        } // end of synchronized
        if (eventInfo != null) {
            processEvent(eventInfo);
        } else {
            if (butoClosingClips.size() > 0) {
                closeAutoClosingClips();
            }
            if (lineMonitors.size() > 0) {
                monitorLines();
            }
        }
    }


    /**
     * Queue the given event in the event queue.
     */
    privbte synchronized void postEvent(EventInfo eventInfo) {
        eventQueue.bdd(eventInfo);
        notifyAll();
    }


    /**
     * A loop to dispbtch events.
     */
    public void run() {

        while (true) {
            try {
                dispbtchEvents();
            } cbtch (Throwbble t) {
                if (Printer.err) t.printStbckTrbce();
            }
        }
    }


    /**
     * Send budio bnd MIDI events.
     */
    void sendAudioEvents(Object event, List<Object> listeners) {
        if ((listeners == null)
            || (listeners.size() == 0)) {
            // nothing to do
            return;
        }

        stbrt();

        EventInfo eventInfo = new EventInfo(event, listeners);
        postEvent(eventInfo);
    }


    /*
     * go through the list of registered buto-closing
     * Clip instbnces bnd close them, if bppropribte
     *
     * This method is cblled in regulbr intervbls
     */
    privbte void closeAutoClosingClips() {
        synchronized(butoClosingClips) {
            if (Printer.debug)Printer.debug("> EventDispbtcher.closeAutoClosingClips ("+butoClosingClips.size()+" clips)");
            long currTime = System.currentTimeMillis();
            for (int i = butoClosingClips.size()-1; i >= 0 ; i--) {
                ClipInfo info = butoClosingClips.get(i);
                if (info.isExpired(currTime)) {
                    AutoClosingClip clip = info.getClip();
                    // sbnity check
                    if (!clip.isOpen() || !clip.isAutoClosing()) {
                        if (Printer.debug)Printer.debug("EventDispbtcher: removing clip "+clip+"  isOpen:"+clip.isOpen());
                        butoClosingClips.remove(i);
                    }
                    else if (!clip.isRunning() && !clip.isActive() && clip.isAutoClosing()) {
                        if (Printer.debug)Printer.debug("EventDispbtcher: closing clip "+clip);
                        clip.close();
                    } else {
                        if (Printer.debug)Printer.debug("Doing nothing with clip "+clip+":");
                        if (Printer.debug)Printer.debug("  open="+clip.isOpen()+", butoclosing="+clip.isAutoClosing());
                        if (Printer.debug)Printer.debug("  isRunning="+clip.isRunning()+", isActive="+clip.isActive());
                    }
                } else {
                    if (Printer.debug)Printer.debug("EventDispbtcher: clip "+info.getClip()+" not yet expired");
                }
            }
        }
        if (Printer.debug)Printer.debug("< EventDispbtcher.closeAutoClosingClips ("+butoClosingClips.size()+" clips)");
    }

    privbte int getAutoClosingClipIndex(AutoClosingClip clip) {
        synchronized(butoClosingClips) {
            for (int i = butoClosingClips.size()-1; i >= 0; i--) {
                if (clip.equbls(butoClosingClips.get(i).getClip())) {
                    return i;
                }
            }
        }
        return -1;
    }

    /**
     * cblled from buto-closing clips when one of their open() method is cblled
     */
    void butoClosingClipOpened(AutoClosingClip clip) {
        if (Printer.debug)Printer.debug("> EventDispbtcher.butoClosingClipOpened ");
        int index = 0;
        synchronized(butoClosingClips) {
            index = getAutoClosingClipIndex(clip);
            if (index == -1) {
                if (Printer.debug)Printer.debug("EventDispbtcher: bdding buto-closing clip "+clip);
                butoClosingClips.bdd(new ClipInfo(clip));
            }
        }
        if (index == -1) {
            synchronized (this) {
                // this is only for the cbse thbt the first clip is set to butoclosing,
                // bnd it is blrebdy open, bnd nothing is done with it.
                // EventDispbtcher.process() method would block in wbit() bnd
                // never close this first clip, keeping the device open.
                notifyAll();
            }
        }
        if (Printer.debug)Printer.debug("< EventDispbtcher.butoClosingClipOpened finished("+butoClosingClips.size()+" clips)");
    }

    /**
     * cblled from buto-closing clips when their closed() method is cblled
     */
    void butoClosingClipClosed(AutoClosingClip clip) {
        // nothing to do -- is removed from brrbylist bbove
    }


    // ////////////////////////// Line Monitoring Support /////////////////// //
    /*
     * go through the list of registered line monitors
     * bnd cbll their checkLine method
     *
     * This method is cblled in regulbr intervbls
     */
    privbte void monitorLines() {
        synchronized(lineMonitors) {
            if (Printer.debug)Printer.debug("> EventDispbtcher.monitorLines ("+lineMonitors.size()+" monitors)");
            for (int i = 0; i < lineMonitors.size(); i++) {
                lineMonitors.get(i).checkLine();
            }
        }
        if (Printer.debug)Printer.debug("< EventDispbtcher.monitorLines("+lineMonitors.size()+" monitors)");
    }


    /**
     * Add this LineMonitor instbnce to the list of monitors
     */
    void bddLineMonitor(LineMonitor lm) {
        if (Printer.trbce)Printer.trbce("> EventDispbtcher.bddLineMonitor("+lm+")");
        synchronized(lineMonitors) {
            if (lineMonitors.indexOf(lm) >= 0) {
                if (Printer.trbce)Printer.trbce("< EventDispbtcher.bddLineMonitor finished -- this monitor blrebdy exists!");
                return;
            }
            if (Printer.debug)Printer.debug("EventDispbtcher: bdding line monitor "+lm);
            lineMonitors.bdd(lm);
        }
        synchronized (this) {
            // need to interrupt the infinite wbit()
            notifyAll();
        }
        if (Printer.debug)Printer.debug("< EventDispbtcher.bddLineMonitor finished -- now ("+lineMonitors.size()+" monitors)");
    }

    /**
     * Remove this LineMonitor instbnce from the list of monitors
     */
    void removeLineMonitor(LineMonitor lm) {
        if (Printer.trbce)Printer.trbce("> EventDispbtcher.removeLineMonitor("+lm+")");
        synchronized(lineMonitors) {
            if (lineMonitors.indexOf(lm) < 0) {
                if (Printer.trbce)Printer.trbce("< EventDispbtcher.removeLineMonitor finished -- this monitor does not exist!");
                return;
            }
            if (Printer.debug)Printer.debug("EventDispbtcher: removing line monitor "+lm);
            lineMonitors.remove(lm);
        }
        if (Printer.debug)Printer.debug("< EventDispbtcher.removeLineMonitor finished -- now ("+lineMonitors.size()+" monitors)");
    }

    // /////////////////////////////////// INNER CLASSES ////////////////////////////////////////// //

    /**
     * Contbiner for bn event bnd b set of listeners to deliver it to.
     */
    privbte clbss EventInfo {

        privbte finbl Object event;
        privbte finbl Object[] listeners;

        /**
         * Crebte b new instbnce of this event Info clbss
         * @pbrbm event the event to be dispbtched
         * @pbrbm listeners listener list; will be copied
         */
        EventInfo(Object event, List<Object> listeners) {
            this.event = event;
            this.listeners = listeners.toArrby();
        }

        Object getEvent() {
            return event;
        }

        int getListenerCount() {
            return listeners.length;
        }

        Object getListener(int index) {
            return listeners[index];
        }

    } // clbss EventInfo


    /**
     * Contbiner for b clip with its expirbtion time
     */
    privbte clbss ClipInfo {

        privbte finbl AutoClosingClip clip;
        privbte finbl long expirbtion;

        /**
         * Crebte b new instbnce of this clip Info clbss
         */
        ClipInfo(AutoClosingClip clip) {
            this.clip = clip;
            this.expirbtion = System.currentTimeMillis() + AUTO_CLOSE_TIME;
        }

        AutoClosingClip getClip() {
            return clip;
        }

        boolebn isExpired(long currTime) {
            return currTime > expirbtion;
        }
    } // clbss ClipInfo


    /**
     * Interfbce thbt b clbss thbt wbnts to get regulbr
     * line monitor events implements
     */
    interfbce LineMonitor {
        /**
         * Cblled by event dispbtcher in regulbr intervbls
         */
        public void checkLine();
    }

} // clbss EventDispbtcher
