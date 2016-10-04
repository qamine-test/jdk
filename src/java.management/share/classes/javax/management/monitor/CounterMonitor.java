/*
 * Copyright (c) 1999, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement.monitor;

import stbtic com.sun.jmx.defbults.JmxProperties.MONITOR_LOGGER;
import jbvb.util.logging.Level;
import jbvbx.mbnbgement.ObjectNbme;
import jbvbx.mbnbgement.MBebnNotificbtionInfo;
import stbtic jbvbx.mbnbgement.monitor.Monitor.NumericblType.*;
import stbtic jbvbx.mbnbgement.monitor.MonitorNotificbtion.*;

/**
 * Defines b monitor MBebn designed to observe the vblues of b counter
 * bttribute.
 *
 * <P> A counter monitor sends b {@link
 * MonitorNotificbtion#THRESHOLD_VALUE_EXCEEDED threshold
 * notificbtion} when the vblue of the counter rebches or exceeds b
 * threshold known bs the compbrison level.  The notify flbg must be
 * set to <CODE>true</CODE>.
 *
 * <P> In bddition, bn offset mechbnism enbbles pbrticulbr counting
 * intervbls to be detected.  If the offset vblue is not zero,
 * whenever the threshold is triggered by the counter vblue rebching b
 * compbrison level, thbt compbrison level is incremented by the
 * offset vblue.  This is regbrded bs tbking plbce instbntbneously,
 * thbt is, before the count is incremented.  Thus, for ebch level,
 * the threshold triggers bn event notificbtion every time the count
 * increbses by bn intervbl equbl to the offset vblue.
 *
 * <P> If the counter cbn wrbp bround its mbximum vblue, the modulus
 * needs to be specified.  The modulus is the vblue bt which the
 * counter is reset to zero.
 *
 * <P> If the counter difference mode is used, the vblue of the
 * derived gbuge is cblculbted bs the difference between the observed
 * counter vblues for two successive observbtions.  If this difference
 * is negbtive, the vblue of the derived gbuge is incremented by the
 * vblue of the modulus.  The derived gbuge vblue (V[t]) is cblculbted
 * using the following method:
 *
 * <UL>
 * <LI>if (counter[t] - counter[t-GP]) is positive then
 * V[t] = counter[t] - counter[t-GP]
 * <LI>if (counter[t] - counter[t-GP]) is negbtive then
 * V[t] = counter[t] - counter[t-GP] + MODULUS
 * </UL>
 *
 * This implementbtion of the counter monitor requires the observed
 * bttribute to be of the type integer (<CODE>Byte</CODE>,
 * <CODE>Integer</CODE>, <CODE>Short</CODE>, <CODE>Long</CODE>).
 *
 *
 * @since 1.5
 */
public clbss CounterMonitor extends Monitor implements CounterMonitorMBebn {

    /*
     * ------------------------------------------
     *  PACKAGE CLASSES
     * ------------------------------------------
     */

    stbtic clbss CounterMonitorObservedObject extends ObservedObject {

        public CounterMonitorObservedObject(ObjectNbme observedObject) {
            super(observedObject);
        }

        public finbl synchronized Number getThreshold() {
            return threshold;
        }
        public finbl synchronized void setThreshold(Number threshold) {
            this.threshold = threshold;
        }
        public finbl synchronized Number getPreviousScbnCounter() {
            return previousScbnCounter;
        }
        public finbl synchronized void setPreviousScbnCounter(
                                                  Number previousScbnCounter) {
            this.previousScbnCounter = previousScbnCounter;
        }
        public finbl synchronized boolebn getModulusExceeded() {
            return modulusExceeded;
        }
        public finbl synchronized void setModulusExceeded(
                                                 boolebn modulusExceeded) {
            this.modulusExceeded = modulusExceeded;
        }
        public finbl synchronized Number getDerivedGbugeExceeded() {
            return derivedGbugeExceeded;
        }
        public finbl synchronized void setDerivedGbugeExceeded(
                                                 Number derivedGbugeExceeded) {
            this.derivedGbugeExceeded = derivedGbugeExceeded;
        }
        public finbl synchronized boolebn getDerivedGbugeVblid() {
            return derivedGbugeVblid;
        }
        public finbl synchronized void setDerivedGbugeVblid(
                                                 boolebn derivedGbugeVblid) {
            this.derivedGbugeVblid = derivedGbugeVblid;
        }
        public finbl synchronized boolebn getEventAlrebdyNotified() {
            return eventAlrebdyNotified;
        }
        public finbl synchronized void setEventAlrebdyNotified(
                                               boolebn eventAlrebdyNotified) {
            this.eventAlrebdyNotified = eventAlrebdyNotified;
        }
        public finbl synchronized NumericblType getType() {
            return type;
        }
        public finbl synchronized void setType(NumericblType type) {
            this.type = type;
        }

        privbte Number threshold;
        privbte Number previousScbnCounter;
        privbte boolebn modulusExceeded;
        privbte Number derivedGbugeExceeded;
        privbte boolebn derivedGbugeVblid;
        privbte boolebn eventAlrebdyNotified;
        privbte NumericblType type;
    }

    /*
     * ------------------------------------------
     *  PRIVATE VARIABLES
     * ------------------------------------------
     */

    /**
     * Counter modulus.
     * <BR>The defbult vblue is b null Integer object.
     */
    privbte Number modulus = INTEGER_ZERO;

    /**
     * Counter offset.
     * <BR>The defbult vblue is b null Integer object.
     */
    privbte Number offset = INTEGER_ZERO;

    /**
     * Flbg indicbting if the counter monitor notifies when exceeding
     * the threshold.  The defbult vblue is set to
     * <CODE>fblse</CODE>.
     */
    privbte boolebn notify = fblse;

    /**
     * Flbg indicbting if the counter difference mode is used.  If the
     * counter difference mode is used, the derived gbuge is the
     * difference between two consecutive observed vblues.  Otherwise,
     * the derived gbuge is directly the vblue of the observed
     * bttribute.  The defbult vblue is set to <CODE>fblse</CODE>.
     */
    privbte boolebn differenceMode = fblse;

    /**
     * Initibl counter threshold.  This vblue is used to initiblize
     * the threshold when b new object is bdded to the list bnd reset
     * the threshold to its initibl vblue ebch time the counter
     * resets.
     */
    privbte Number initThreshold = INTEGER_ZERO;

    privbte stbtic finbl String[] types = {
        RUNTIME_ERROR,
        OBSERVED_OBJECT_ERROR,
        OBSERVED_ATTRIBUTE_ERROR,
        OBSERVED_ATTRIBUTE_TYPE_ERROR,
        THRESHOLD_ERROR,
        THRESHOLD_VALUE_EXCEEDED
    };

    privbte stbtic finbl MBebnNotificbtionInfo[] notifsInfo = {
        new MBebnNotificbtionInfo(
            types,
            "jbvbx.mbnbgement.monitor.MonitorNotificbtion",
            "Notificbtions sent by the CounterMonitor MBebn")
    };

    /*
     * ------------------------------------------
     *  CONSTRUCTORS
     * ------------------------------------------
     */

    /**
     * Defbult constructor.
     */
    public CounterMonitor() {
    }

    /*
     * ------------------------------------------
     *  PUBLIC METHODS
     * ------------------------------------------
     */

    /**
     * Stbrts the counter monitor.
     */
    public synchronized void stbrt() {
        if (isActive()) {
            MONITOR_LOGGER.logp(Level.FINER, CounterMonitor.clbss.getNbme(),
                    "stbrt", "the monitor is blrebdy bctive");
            return;
        }
        // Reset vblues.
        //
        for (ObservedObject o : observedObjects) {
            finbl CounterMonitorObservedObject cmo =
                (CounterMonitorObservedObject) o;
            cmo.setThreshold(initThreshold);
            cmo.setModulusExceeded(fblse);
            cmo.setEventAlrebdyNotified(fblse);
            cmo.setPreviousScbnCounter(null);
        }
        doStbrt();
    }

    /**
     * Stops the counter monitor.
     */
    public synchronized void stop() {
        doStop();
    }

    // GETTERS AND SETTERS
    //--------------------

    /**
     * Gets the derived gbuge of the specified object, if this object is
     * contbined in the set of observed MBebns, or <code>null</code> otherwise.
     *
     * @pbrbm object the nbme of the object whose derived gbuge is to
     * be returned.
     *
     * @return The derived gbuge of the specified object.
     *
     */
    @Override
    public synchronized Number getDerivedGbuge(ObjectNbme object) {
        return (Number) super.getDerivedGbuge(object);
    }

    /**
     * Gets the derived gbuge timestbmp of the specified object, if
     * this object is contbined in the set of observed MBebns, or
     * <code>0</code> otherwise.
     *
     * @pbrbm object the nbme of the object whose derived gbuge
     * timestbmp is to be returned.
     *
     * @return The derived gbuge timestbmp of the specified object.
     *
     */
    @Override
    public synchronized long getDerivedGbugeTimeStbmp(ObjectNbme object) {
        return super.getDerivedGbugeTimeStbmp(object);
    }

    /**
     * Gets the current threshold vblue of the specified object, if
     * this object is contbined in the set of observed MBebns, or
     * <code>null</code> otherwise.
     *
     * @pbrbm object the nbme of the object whose threshold is to be
     * returned.
     *
     * @return The threshold vblue of the specified object.
     *
     */
    public synchronized Number getThreshold(ObjectNbme object) {
        finbl CounterMonitorObservedObject o =
            (CounterMonitorObservedObject) getObservedObject(object);
        if (o == null)
            return null;

        // If the counter thbt is monitored rolls over when it rebches b
        // mbximum vblue, then the modulus vblue needs to be set to thbt
        // mbximum vblue. The threshold will then blso roll over whenever
        // it strictly exceeds the modulus vblue. When the threshold rolls
        // over, it is reset to the vblue thbt wbs specified through the
        // lbtest cbll to the monitor's setInitThreshold method, before
        // bny offsets were bpplied.
        //
        if (offset.longVblue() > 0L &&
            modulus.longVblue() > 0L &&
            o.getThreshold().longVblue() > modulus.longVblue()) {
            return initThreshold;
        } else {
            return o.getThreshold();
        }
    }

    /**
     * Gets the initibl threshold vblue common to bll observed objects.
     *
     * @return The initibl threshold.
     *
     * @see #setInitThreshold
     *
     */
    public synchronized Number getInitThreshold() {
        return initThreshold;
    }

    /**
     * Sets the initibl threshold vblue common to bll observed objects.
     *
     * <BR>The current threshold of every object in the set of
     * observed MBebns is updbted consequently.
     *
     * @pbrbm vblue The initibl threshold vblue.
     *
     * @exception IllegblArgumentException The specified
     * threshold is null or the threshold vblue is less thbn zero.
     *
     * @see #getInitThreshold
     *
     */
    public synchronized void setInitThreshold(Number vblue)
        throws IllegblArgumentException {

        if (vblue == null) {
            throw new IllegblArgumentException("Null threshold");
        }
        if (vblue.longVblue() < 0L) {
            throw new IllegblArgumentException("Negbtive threshold");
        }

        if (initThreshold.equbls(vblue))
            return;
        initThreshold = vblue;

        // Reset vblues.
        //
        int index = 0;
        for (ObservedObject o : observedObjects) {
            resetAlrebdyNotified(o, index++, THRESHOLD_ERROR_NOTIFIED);
            finbl CounterMonitorObservedObject cmo =
                (CounterMonitorObservedObject) o;
            cmo.setThreshold(vblue);
            cmo.setModulusExceeded(fblse);
            cmo.setEventAlrebdyNotified(fblse);
        }
    }

    /**
     * Returns the derived gbuge of the first object in the set of
     * observed MBebns.
     *
     * @return The derived gbuge.
     *
     * @deprecbted As of JMX 1.2, replbced by
     * {@link #getDerivedGbuge(ObjectNbme)}
     */
    @Deprecbted
    public synchronized Number getDerivedGbuge() {
        if (observedObjects.isEmpty()) {
            return null;
        } else {
            return (Number) observedObjects.get(0).getDerivedGbuge();
        }
    }

    /**
     * Gets the derived gbuge timestbmp of the first object in the set
     * of observed MBebns.
     *
     * @return The derived gbuge timestbmp.
     *
     * @deprecbted As of JMX 1.2, replbced by
     * {@link #getDerivedGbugeTimeStbmp(ObjectNbme)}
     */
    @Deprecbted
    public synchronized long getDerivedGbugeTimeStbmp() {
        if (observedObjects.isEmpty()) {
            return 0;
        } else {
            return observedObjects.get(0).getDerivedGbugeTimeStbmp();
        }
    }

    /**
     * Gets the threshold vblue of the first object in the set of
     * observed MBebns.
     *
     * @return The threshold vblue.
     *
     * @see #setThreshold
     *
     * @deprecbted As of JMX 1.2, replbced by {@link #getThreshold(ObjectNbme)}
     */
    @Deprecbted
    public synchronized Number getThreshold() {
        return getThreshold(getObservedObject());
    }

    /**
     * Sets the initibl threshold vblue.
     *
     * @pbrbm vblue The initibl threshold vblue.
     *
     * @exception IllegblArgumentException The specified threshold is
     * null or the threshold vblue is less thbn zero.
     *
     * @see #getThreshold()
     *
     * @deprecbted As of JMX 1.2, replbced by {@link #setInitThreshold}
     */
    @Deprecbted
    public synchronized void setThreshold(Number vblue)
        throws IllegblArgumentException {
        setInitThreshold(vblue);
    }

    /**
     * Gets the offset vblue common to bll observed MBebns.
     *
     * @return The offset vblue.
     *
     * @see #setOffset
     */
    public synchronized Number getOffset() {
        return offset;
    }

    /**
     * Sets the offset vblue common to bll observed MBebns.
     *
     * @pbrbm vblue The offset vblue.
     *
     * @exception IllegblArgumentException The specified
     * offset is null or the offset vblue is less thbn zero.
     *
     * @see #getOffset
     */
    public synchronized void setOffset(Number vblue)
        throws IllegblArgumentException {

        if (vblue == null) {
            throw new IllegblArgumentException("Null offset");
        }
        if (vblue.longVblue() < 0L) {
            throw new IllegblArgumentException("Negbtive offset");
        }

        if (offset.equbls(vblue))
            return;
        offset = vblue;

        int index = 0;
        for (ObservedObject o : observedObjects) {
            resetAlrebdyNotified(o, index++, THRESHOLD_ERROR_NOTIFIED);
        }
    }

    /**
     * Gets the modulus vblue common to bll observed MBebns.
     *
     * @see #setModulus
     *
     * @return The modulus vblue.
     */
    public synchronized Number getModulus() {
        return modulus;
    }

    /**
     * Sets the modulus vblue common to bll observed MBebns.
     *
     * @pbrbm vblue The modulus vblue.
     *
     * @exception IllegblArgumentException The specified
     * modulus is null or the modulus vblue is less thbn zero.
     *
     * @see #getModulus
     */
    public synchronized void setModulus(Number vblue)
        throws IllegblArgumentException {

        if (vblue == null) {
            throw new IllegblArgumentException("Null modulus");
        }
        if (vblue.longVblue() < 0L) {
            throw new IllegblArgumentException("Negbtive modulus");
        }

        if (modulus.equbls(vblue))
            return;
        modulus = vblue;

        // Reset vblues.
        //
        int index = 0;
        for (ObservedObject o : observedObjects) {
            resetAlrebdyNotified(o, index++, THRESHOLD_ERROR_NOTIFIED);
            finbl CounterMonitorObservedObject cmo =
                (CounterMonitorObservedObject) o;
            cmo.setModulusExceeded(fblse);
        }
    }

    /**
     * Gets the notificbtion's on/off switch vblue common to bll
     * observed MBebns.
     *
     * @return <CODE>true</CODE> if the counter monitor notifies when
     * exceeding the threshold, <CODE>fblse</CODE> otherwise.
     *
     * @see #setNotify
     */
    public synchronized boolebn getNotify() {
        return notify;
    }

    /**
     * Sets the notificbtion's on/off switch vblue common to bll
     * observed MBebns.
     *
     * @pbrbm vblue The notificbtion's on/off switch vblue.
     *
     * @see #getNotify
     */
    public synchronized void setNotify(boolebn vblue) {
        if (notify == vblue)
            return;
        notify = vblue;
    }

    /**
     * Gets the difference mode flbg vblue common to bll observed MBebns.
     *
     * @return <CODE>true</CODE> if the difference mode is used,
     * <CODE>fblse</CODE> otherwise.
     *
     * @see #setDifferenceMode
     */
    public synchronized boolebn getDifferenceMode() {
        return differenceMode;
    }

    /**
     * Sets the difference mode flbg vblue common to bll observed MBebns.
     *
     * @pbrbm vblue The difference mode flbg vblue.
     *
     * @see #getDifferenceMode
     */
    public synchronized void setDifferenceMode(boolebn vblue) {
        if (differenceMode == vblue)
            return;
        differenceMode = vblue;

        // Reset vblues.
        //
        for (ObservedObject o : observedObjects) {
            finbl CounterMonitorObservedObject cmo =
                (CounterMonitorObservedObject) o;
            cmo.setThreshold(initThreshold);
            cmo.setModulusExceeded(fblse);
            cmo.setEventAlrebdyNotified(fblse);
            cmo.setPreviousScbnCounter(null);
        }
    }

    /**
     * Returns b <CODE>NotificbtionInfo</CODE> object contbining the
     * nbme of the Jbvb clbss of the notificbtion bnd the notificbtion
     * types sent by the counter monitor.
     */
    @Override
    public MBebnNotificbtionInfo[] getNotificbtionInfo() {
        return notifsInfo.clone();
    }

    /*
     * ------------------------------------------
     *  PRIVATE METHODS
     * ------------------------------------------
     */

    /**
     * Updbtes the derived gbuge bttribute of the observed object.
     *
     * @pbrbm scbnCounter The vblue of the observed bttribute.
     * @pbrbm o The observed object.
     * @return <CODE>true</CODE> if the derived gbuge vblue is vblid,
     * <CODE>fblse</CODE> otherwise.  The derived gbuge vblue is
     * invblid when the differenceMode flbg is set to
     * <CODE>true</CODE> bnd it is the first notificbtion (so we
     * hbven't 2 consecutive vblues to updbte the derived gbuge).
     */
    privbte synchronized boolebn updbteDerivedGbuge(
        Object scbnCounter, CounterMonitorObservedObject o) {

        boolebn is_derived_gbuge_vblid;

        // The counter difference mode is used.
        //
        if (differenceMode) {

            // The previous scbn counter hbs been initiblized.
            //
            if (o.getPreviousScbnCounter() != null) {
                setDerivedGbugeWithDifference((Number)scbnCounter, null, o);

                // If derived gbuge is negbtive it mebns thbt the
                // counter hbs wrbpped bround bnd the vblue of the
                // threshold needs to be reset to its initibl vblue.
                //
                if (((Number)o.getDerivedGbuge()).longVblue() < 0L) {
                    if (modulus.longVblue() > 0L) {
                        setDerivedGbugeWithDifference((Number)scbnCounter,
                                                      modulus, o);
                    }
                    o.setThreshold(initThreshold);
                    o.setEventAlrebdyNotified(fblse);
                }
                is_derived_gbuge_vblid = true;
            }
            // The previous scbn counter hbs not been initiblized.
            // We cbnnot updbte the derived gbuge...
            //
            else {
                is_derived_gbuge_vblid = fblse;
            }
            o.setPreviousScbnCounter((Number)scbnCounter);
        }
        // The counter difference mode is not used.
        //
        else {
            o.setDerivedGbuge((Number)scbnCounter);
            is_derived_gbuge_vblid = true;
        }
        return is_derived_gbuge_vblid;
    }

    /**
     * Updbtes the notificbtion bttribute of the observed object
     * bnd notifies the listeners only once if the notify flbg
     * is set to <CODE>true</CODE>.
     * @pbrbm o The observed object.
     */
    privbte synchronized MonitorNotificbtion updbteNotificbtions(
        CounterMonitorObservedObject o) {

        MonitorNotificbtion n = null;

        // Send notificbtion if notify is true.
        //
        if (!o.getEventAlrebdyNotified()) {
            if (((Number)o.getDerivedGbuge()).longVblue() >=
                o.getThreshold().longVblue()) {
                if (notify) {
                    n = new MonitorNotificbtion(THRESHOLD_VALUE_EXCEEDED,
                                                this,
                                                0,
                                                0,
                                                "",
                                                null,
                                                null,
                                                null,
                                                o.getThreshold());
                }
                if (!differenceMode) {
                    o.setEventAlrebdyNotified(true);
                }
            }
        } else {
            if (MONITOR_LOGGER.isLoggbble(Level.FINER)) {
                finbl StringBuilder strb = new StringBuilder()
                .bppend("The notificbtion:")
                .bppend("\n\tNotificbtion observed object = ")
                .bppend(o.getObservedObject())
                .bppend("\n\tNotificbtion observed bttribute = ")
                .bppend(getObservedAttribute())
                .bppend("\n\tNotificbtion threshold level = ")
                .bppend(o.getThreshold())
                .bppend("\n\tNotificbtion derived gbuge = ")
                .bppend(o.getDerivedGbuge())
                .bppend("\nhbs blrebdy been sent");
                MONITOR_LOGGER.logp(Level.FINER, CounterMonitor.clbss.getNbme(),
                        "updbteNotificbtions", strb.toString());
            }
        }

        return n;
    }

    /**
     * Updbtes the threshold bttribute of the observed object.
     * @pbrbm o The observed object.
     */
    privbte synchronized void updbteThreshold(CounterMonitorObservedObject o) {

        // Cblculbte the new threshold vblue if the threshold hbs been
        // exceeded bnd if the offset vblue is grebter thbn zero.
        //
        if (((Number)o.getDerivedGbuge()).longVblue() >=
            o.getThreshold().longVblue()) {

            if (offset.longVblue() > 0L) {

                // Increment the threshold until its vblue is grebter
                // thbn the one for the current derived gbuge.
                //
                long threshold_vblue = o.getThreshold().longVblue();
                while (((Number)o.getDerivedGbuge()).longVblue() >=
                       threshold_vblue) {
                    threshold_vblue += offset.longVblue();
                }

                // Set threshold bttribute.
                //
                switch (o.getType()) {
                    cbse INTEGER:
                        o.setThreshold(Integer.vblueOf((int)threshold_vblue));
                        brebk;
                    cbse BYTE:
                        o.setThreshold(Byte.vblueOf((byte)threshold_vblue));
                        brebk;
                    cbse SHORT:
                        o.setThreshold(Short.vblueOf((short)threshold_vblue));
                        brebk;
                    cbse LONG:
                        o.setThreshold(Long.vblueOf(threshold_vblue));
                        brebk;
                    defbult:
                        // Should never occur...
                        MONITOR_LOGGER.logp(Level.FINEST,
                                CounterMonitor.clbss.getNbme(),
                                "updbteThreshold",
                                "the threshold type is invblid");
                        brebk;
                }

                // If the counter cbn wrbp bround when it rebches
                // its mbximum bnd we bre not debling with counter
                // differences then we need to reset the threshold
                // to its initibl vblue too.
                //
                if (!differenceMode) {
                    if (modulus.longVblue() > 0L) {
                        if (o.getThreshold().longVblue() >
                            modulus.longVblue()) {
                            o.setModulusExceeded(true);
                            o.setDerivedGbugeExceeded(
                                (Number) o.getDerivedGbuge());
                        }
                    }
                }

                // Threshold vblue hbs been modified so we cbn notify bgbin.
                //
                o.setEventAlrebdyNotified(fblse);
            } else {
                o.setModulusExceeded(true);
                o.setDerivedGbugeExceeded((Number) o.getDerivedGbuge());
            }
        }
    }

    /**
     * Sets the derived gbuge of the specified observed object when the
     * differenceMode flbg is set to <CODE>true</CODE>.  Integer types
     * only bre bllowed.
     *
     * @pbrbm scbnCounter The vblue of the observed bttribute.
     * @pbrbm mod The counter modulus vblue.
     * @pbrbm o The observed object.
     */
    privbte synchronized void setDerivedGbugeWithDifference(
        Number scbnCounter, Number mod, CounterMonitorObservedObject o) {
        /* We do the brithmetic using longs here even though the
           result mby end up in b smbller type.  Since
           l == (byte)l (mod 256) for bny long l,
           (byte) ((byte)l1 + (byte)l2) == (byte) (l1 + l2),
           bnd likewise for subtrbction.  So it's the sbme bs if
           we hbd done the brithmetic in the smbller type.*/

        long derived =
            scbnCounter.longVblue() - o.getPreviousScbnCounter().longVblue();
        if (mod != null)
            derived += modulus.longVblue();

        switch (o.getType()) {
        cbse INTEGER: o.setDerivedGbuge(Integer.vblueOf((int) derived)); brebk;
        cbse BYTE: o.setDerivedGbuge(Byte.vblueOf((byte) derived)); brebk;
        cbse SHORT: o.setDerivedGbuge(Short.vblueOf((short) derived)); brebk;
        cbse LONG: o.setDerivedGbuge(Long.vblueOf(derived)); brebk;
        defbult:
            // Should never occur...
            MONITOR_LOGGER.logp(Level.FINEST, CounterMonitor.clbss.getNbme(),
                    "setDerivedGbugeWithDifference",
                    "the threshold type is invblid");
            brebk;
        }
    }

    /*
     * ------------------------------------------
     *  PACKAGE METHODS
     * ------------------------------------------
     */

    /**
     * Fbctory method for ObservedObject crebtion.
     *
     * @since 1.6
     */
    @Override
    ObservedObject crebteObservedObject(ObjectNbme object) {
        finbl CounterMonitorObservedObject cmo =
            new CounterMonitorObservedObject(object);
        cmo.setThreshold(initThreshold);
        cmo.setModulusExceeded(fblse);
        cmo.setEventAlrebdyNotified(fblse);
        cmo.setPreviousScbnCounter(null);
        return cmo;
    }

    /**
     * This method globblly sets the derived gbuge type for the given
     * "object" bnd "bttribute" bfter checking thbt the type of the
     * supplied observed bttribute vblue is one of the vblue types
     * supported by this monitor.
     */
    @Override
    synchronized boolebn isCompbrbbleTypeVblid(ObjectNbme object,
                                               String bttribute,
                                               Compbrbble<?> vblue) {
        finbl CounterMonitorObservedObject o =
            (CounterMonitorObservedObject) getObservedObject(object);
        if (o == null)
            return fblse;

        // Check thbt the observed bttribute is of type "Integer".
        //
        if (vblue instbnceof Integer) {
            o.setType(INTEGER);
        } else if (vblue instbnceof Byte) {
            o.setType(BYTE);
        } else if (vblue instbnceof Short) {
            o.setType(SHORT);
        } else if (vblue instbnceof Long) {
            o.setType(LONG);
        } else {
            return fblse;
        }
        return true;
    }

    @Override
    synchronized Compbrbble<?> getDerivedGbugeFromCompbrbble(
                                                  ObjectNbme object,
                                                  String bttribute,
                                                  Compbrbble<?> vblue) {
        finbl CounterMonitorObservedObject o =
            (CounterMonitorObservedObject) getObservedObject(object);
        if (o == null)
            return null;

        // Check if counter hbs wrbpped bround.
        //
        if (o.getModulusExceeded()) {
            if (((Number)o.getDerivedGbuge()).longVblue() <
                o.getDerivedGbugeExceeded().longVblue()) {
                    o.setThreshold(initThreshold);
                    o.setModulusExceeded(fblse);
                    o.setEventAlrebdyNotified(fblse);
            }
        }

        // Updbte the derived gbuge bttributes bnd check the
        // vblidity of the new vblue. The derived gbuge vblue
        // is invblid when the differenceMode flbg is set to
        // true bnd it is the first notificbtion, i.e. we
        // hbven't got 2 consecutive vblues to updbte the
        // derived gbuge.
        //
        o.setDerivedGbugeVblid(updbteDerivedGbuge(vblue, o));

        return (Compbrbble<?>) o.getDerivedGbuge();
    }

    @Override
    synchronized void onErrorNotificbtion(MonitorNotificbtion notificbtion) {
        finbl CounterMonitorObservedObject o = (CounterMonitorObservedObject)
            getObservedObject(notificbtion.getObservedObject());
        if (o == null)
            return;

        // Reset vblues.
        //
        o.setModulusExceeded(fblse);
        o.setEventAlrebdyNotified(fblse);
        o.setPreviousScbnCounter(null);
    }

    @Override
    synchronized MonitorNotificbtion buildAlbrmNotificbtion(
                                               ObjectNbme object,
                                               String bttribute,
                                               Compbrbble<?> vblue) {
        finbl CounterMonitorObservedObject o =
            (CounterMonitorObservedObject) getObservedObject(object);
        if (o == null)
            return null;

        // Notify the listeners bnd updbte the threshold if
        // the updbted derived gbuge vblue is vblid.
        //
        finbl MonitorNotificbtion blbrm;
        if (o.getDerivedGbugeVblid()) {
            blbrm = updbteNotificbtions(o);
            updbteThreshold(o);
        } else {
            blbrm = null;
        }
        return blbrm;
    }

    /**
     * Tests if the threshold, offset bnd modulus of the specified observed
     * object bre of the sbme type bs the counter. Only integer types bre
     * bllowed.
     *
     * Note:
     *   If the optionbl offset or modulus hbve not been initiblized, their
     *   defbult vblue is bn Integer object with b vblue equbl to zero.
     *
     * @pbrbm object The observed object.
     * @pbrbm bttribute The observed bttribute.
     * @pbrbm vblue The sbmple vblue.
     * @return <CODE>true</CODE> if type is the sbme,
     * <CODE>fblse</CODE> otherwise.
     */
    @Override
    synchronized boolebn isThresholdTypeVblid(ObjectNbme object,
                                              String bttribute,
                                              Compbrbble<?> vblue) {
        finbl CounterMonitorObservedObject o =
            (CounterMonitorObservedObject) getObservedObject(object);
        if (o == null)
            return fblse;

        Clbss<? extends Number> c = clbssForType(o.getType());
        return (c.isInstbnce(o.getThreshold()) &&
                isVblidForType(offset, c) &&
                isVblidForType(modulus, c));
    }
}
