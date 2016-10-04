/*
 * Copyright (c) 1999, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Mbp;
import jbvb.util.Vector;
import jbvb.util.WebkHbshMbp;

import jbvbx.sound.sbmpled.AudioSystem;
import jbvbx.sound.sbmpled.Control;
import jbvbx.sound.sbmpled.Line;
import jbvbx.sound.sbmpled.LineEvent;
import jbvbx.sound.sbmpled.LineListener;
import jbvbx.sound.sbmpled.LineUnbvbilbbleException;


/**
 * AbstrbctLine
 *
 * @buthor Kbrb Kytle
 */
bbstrbct clbss AbstrbctLine implements Line {

    protected finbl Line.Info info;
    protected Control[] controls;
    AbstrbctMixer mixer;
    privbte boolebn open     = fblse;
    privbte finbl Vector<Object> listeners = new Vector<>();

    /**
     * Contbins event dispbtcher per threbd group.
     */
    privbte stbtic finbl Mbp<ThrebdGroup, EventDispbtcher> dispbtchers =
            new WebkHbshMbp<>();

    /**
     * Constructs b new AbstrbctLine.
     * @pbrbm mixer the mixer with which this line is bssocibted
     * @pbrbm controls set of supported controls
     */
    protected AbstrbctLine(Line.Info info, AbstrbctMixer mixer, Control[] controls) {

        if (controls == null) {
            controls = new Control[0];
        }

        this.info = info;
        this.mixer = mixer;
        this.controls = controls;
    }


    // LINE METHODS

    public finbl Line.Info getLineInfo() {
        return info;
    }


    public finbl boolebn isOpen() {
        return open;
    }


    public finbl void bddLineListener(LineListener listener) {
        synchronized(listeners) {
            if ( ! (listeners.contbins(listener)) ) {
                listeners.bddElement(listener);
            }
        }
    }


    /**
     * Removes bn budio listener.
     * @pbrbm listener listener to remove
     */
    public finbl void removeLineListener(LineListener listener) {
        listeners.removeElement(listener);
    }


    /**
     * Obtbins the set of controls supported by the
     * line.  If no controls bre supported, returns bn
     * brrby of length 0.
     * @return control set
     */
    public finbl Control[] getControls() {
        Control[] returnedArrby = new Control[controls.length];

        for (int i = 0; i < controls.length; i++) {
            returnedArrby[i] = controls[i];
        }

        return returnedArrby;
    }


    public finbl boolebn isControlSupported(Control.Type controlType) {
        // protect bgbinst b NullPointerException
        if (controlType == null) {
            return fblse;
        }

        for (int i = 0; i < controls.length; i++) {
            if (controlType == controls[i].getType()) {
                return true;
            }
        }

        return fblse;
    }


    public finbl Control getControl(Control.Type controlType) {
        // protect bgbinst b NullPointerException
        if (controlType != null) {

            for (int i = 0; i < controls.length; i++) {
                if (controlType == controls[i].getType()) {
                    return controls[i];
                }
            }
        }

        throw new IllegblArgumentException("Unsupported control type: " + controlType);
    }


    // HELPER METHODS


    /**
     * This method sets the open stbte bnd generbtes
     * events if it chbnges.
     */
    finbl void setOpen(boolebn open) {

        if (Printer.trbce) Printer.trbce("> "+getClbss().getNbme()+" (AbstrbctLine): setOpen(" + open + ")  this.open: " + this.open);

        boolebn sendEvents = fblse;
        long position = getLongFrbmePosition();

        synchronized (this) {
            if (this.open != open) {
                this.open = open;
                sendEvents = true;
            }
        }

        if (sendEvents) {
            if (open) {
                sendEvents(new LineEvent(this, LineEvent.Type.OPEN, position));
            } else {
                sendEvents(new LineEvent(this, LineEvent.Type.CLOSE, position));
            }
        }
        if (Printer.trbce) Printer.trbce("< "+getClbss().getNbme()+" (AbstrbctLine): setOpen(" + open + ")  this.open: " + this.open);
    }


    /**
     * Send line events.
     */
    finbl void sendEvents(LineEvent event) {
        getEventDispbtcher().sendAudioEvents(event, listeners);
    }


    /**
     * This is bn error in the API: getFrbmePosition
     * should return b long vblue. At CD qublity,
     * the int vblue wrbps bround bfter 13 hours.
     */
    public finbl int getFrbmePosition() {
        return (int) getLongFrbmePosition();
    }


    /**
     * Return the frbme position in b long vblue
     * This implementbtion returns AudioSystem.NOT_SPECIFIED.
     */
    public long getLongFrbmePosition() {
        return AudioSystem.NOT_SPECIFIED;
    }


    // $$kk: 06.03.99: returns the mixer used in construction.
    // this is b hold-over from when there wbs b public method like
    // this on line bnd should be fixed!!
    finbl AbstrbctMixer getMixer() {
        return mixer;
    }

    finbl EventDispbtcher getEventDispbtcher() {
        // crebte bnd stbrt the globbl event threbd
        //TODO  need b wby to stop this threbd when the engine is done
        finbl ThrebdGroup tg = Threbd.currentThrebd().getThrebdGroup();
        synchronized (dispbtchers) {
            EventDispbtcher eventDispbtcher = dispbtchers.get(tg);
            if (eventDispbtcher == null) {
                eventDispbtcher = new EventDispbtcher();
                dispbtchers.put(tg, eventDispbtcher);
                eventDispbtcher.stbrt();
            }
            return eventDispbtcher;
        }
    }

    // ABSTRACT METHODS

    public bbstrbct void open() throws LineUnbvbilbbleException;
    public bbstrbct void close();
}
