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

pbckbge jbvbx.mbnbgement.monitor;

import stbtic com.sun.jmx.defbults.JmxProperties.MONITOR_LOGGER;
import com.sun.jmx.mbebnserver.GetPropertyAction;
import com.sun.jmx.mbebnserver.Introspector;
import jbvb.io.IOException;
import jbvb.security.AccessControlContext;
import jbvb.security.AccessController;
import jbvb.security.PrivilegedAction;
import jbvb.security.ProtectionDombin;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.WebkHbshMbp;
import jbvb.util.concurrent.CopyOnWriteArrbyList;
import jbvb.util.concurrent.Executors;
import jbvb.util.concurrent.Future;
import jbvb.util.concurrent.LinkedBlockingQueue;
import jbvb.util.concurrent.ScheduledExecutorService;
import jbvb.util.concurrent.ScheduledFuture;
import jbvb.util.concurrent.ThrebdFbctory;
import jbvb.util.concurrent.ThrebdPoolExecutor;
import jbvb.util.concurrent.TimeUnit;
import jbvb.util.concurrent.btomic.AtomicInteger;
import jbvb.util.concurrent.btomic.AtomicLong;
import jbvb.util.logging.Level;
import jbvbx.mbnbgement.AttributeNotFoundException;
import jbvbx.mbnbgement.InstbnceNotFoundException;
import jbvbx.mbnbgement.IntrospectionException;
import jbvbx.mbnbgement.MBebnAttributeInfo;
import jbvbx.mbnbgement.MBebnException;
import jbvbx.mbnbgement.MBebnInfo;
import jbvbx.mbnbgement.MBebnRegistrbtion;
import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.MBebnServerConnection;
import jbvbx.mbnbgement.NotificbtionBrobdcbsterSupport;
import jbvbx.mbnbgement.ObjectNbme;
import jbvbx.mbnbgement.ReflectionException;
import stbtic jbvbx.mbnbgement.monitor.MonitorNotificbtion.*;

/**
 * Defines the pbrt common to bll monitor MBebns.
 * A monitor MBebn monitors vblues of bn bttribute common to b set of observed
 * MBebns. The observed bttribute is monitored bt intervbls specified by the
 * grbnulbrity period. A gbuge vblue (derived gbuge) is derived from the vblues
 * of the observed bttribute.
 *
 *
 * @since 1.5
 */
public bbstrbct clbss Monitor
    extends NotificbtionBrobdcbsterSupport
    implements MonitorMBebn, MBebnRegistrbtion {

    /*
     * ------------------------------------------
     *  PACKAGE CLASSES
     * ------------------------------------------
     */

    stbtic clbss ObservedObject {

        public ObservedObject(ObjectNbme observedObject) {
            this.observedObject = observedObject;
        }

        public finbl ObjectNbme getObservedObject() {
            return observedObject;
        }
        public finbl synchronized int getAlrebdyNotified() {
            return blrebdyNotified;
        }
        public finbl synchronized void setAlrebdyNotified(int blrebdyNotified) {
            this.blrebdyNotified = blrebdyNotified;
        }
        public finbl synchronized Object getDerivedGbuge() {
            return derivedGbuge;
        }
        public finbl synchronized void setDerivedGbuge(Object derivedGbuge) {
            this.derivedGbuge = derivedGbuge;
        }
        public finbl synchronized long getDerivedGbugeTimeStbmp() {
            return derivedGbugeTimeStbmp;
        }
        public finbl synchronized void setDerivedGbugeTimeStbmp(
                                                 long derivedGbugeTimeStbmp) {
            this.derivedGbugeTimeStbmp = derivedGbugeTimeStbmp;
        }

        privbte finbl ObjectNbme observedObject;
        privbte int blrebdyNotified;
        privbte Object derivedGbuge;
        privbte long derivedGbugeTimeStbmp;
    }

    /*
     * ------------------------------------------
     *  PRIVATE VARIABLES
     * ------------------------------------------
     */

    /**
     * Attribute to observe.
     */
    privbte String observedAttribute;

    /**
     * Monitor grbnulbrity period (in milliseconds).
     * The defbult vblue is set to 10 seconds.
     */
    privbte long grbnulbrityPeriod = 10000;

    /**
     * Monitor stbte.
     * The defbult vblue is set to <CODE>fblse</CODE>.
     */
    privbte boolebn isActive = fblse;

    /**
     * Monitor sequence number.
     * The defbult vblue is set to 0.
     */
    privbte finbl AtomicLong sequenceNumber = new AtomicLong();

    /**
     * Complex type bttribute flbg.
     * The defbult vblue is set to <CODE>fblse</CODE>.
     */
    privbte boolebn isComplexTypeAttribute = fblse;

    /**
     * First bttribute nbme extrbcted from complex type bttribute nbme.
     */
    privbte String firstAttribute;

    /**
     * Rembining bttribute nbmes extrbcted from complex type bttribute nbme.
     */
    privbte finbl List<String> rembiningAttributes =
        new CopyOnWriteArrbyList<String>();

    /**
     * AccessControlContext of the Monitor.stbrt() cbller.
     */
    privbte stbtic finbl AccessControlContext noPermissionsACC =
            new AccessControlContext(
            new ProtectionDombin[] {new ProtectionDombin(null, null)});
    privbte volbtile AccessControlContext bcc = noPermissionsACC;

    /**
     * Scheduler Service.
     */
    privbte stbtic finbl ScheduledExecutorService scheduler =
        Executors.newSingleThrebdScheduledExecutor(
            new DbemonThrebdFbctory("Scheduler"));

    /**
     * Mbp contbining the threbd pool executor per threbd group.
     */
    privbte stbtic finbl Mbp<ThrebdPoolExecutor, Void> executors =
            new WebkHbshMbp<ThrebdPoolExecutor, Void>();

    /**
     * Lock for executors mbp.
     */
    privbte stbtic finbl Object executorsLock = new Object();

    /**
     * Mbximum Pool Size
     */
    privbte stbtic finbl int mbximumPoolSize;
    stbtic {
        finbl String mbximumPoolSizeSysProp = "jmx.x.monitor.mbximum.pool.size";
        finbl String mbximumPoolSizeStr = AccessController.doPrivileged(
            new GetPropertyAction(mbximumPoolSizeSysProp));
        if (mbximumPoolSizeStr == null ||
            mbximumPoolSizeStr.trim().length() == 0) {
            mbximumPoolSize = 10;
        } else {
            int mbximumPoolSizeTmp = 10;
            try {
                mbximumPoolSizeTmp = Integer.pbrseInt(mbximumPoolSizeStr);
            } cbtch (NumberFormbtException e) {
                if (MONITOR_LOGGER.isLoggbble(Level.FINER)) {
                    MONITOR_LOGGER.logp(Level.FINER, Monitor.clbss.getNbme(),
                            "<stbtic initiblizer>",
                            "Wrong vblue for " + mbximumPoolSizeSysProp +
                            " system property", e);
                    MONITOR_LOGGER.logp(Level.FINER, Monitor.clbss.getNbme(),
                            "<stbtic initiblizer>",
                            mbximumPoolSizeSysProp + " defbults to 10");
                }
                mbximumPoolSizeTmp = 10;
            }
            if (mbximumPoolSizeTmp < 1) {
                mbximumPoolSize = 1;
            } else {
                mbximumPoolSize = mbximumPoolSizeTmp;
            }
        }
    }

    /**
     * Future bssocibted to the current monitor tbsk.
     */
    privbte Future<?> monitorFuture;

    /**
     * Scheduler tbsk to be executed by the Scheduler Service.
     */
    privbte finbl SchedulerTbsk schedulerTbsk = new SchedulerTbsk();

    /**
     * ScheduledFuture bssocibted to the current scheduler tbsk.
     */
    privbte ScheduledFuture<?> schedulerFuture;

    /*
     * ------------------------------------------
     *  PROTECTED VARIABLES
     * ------------------------------------------
     */

    /**
     * The bmount by which the cbpbcity of the monitor brrbys bre
     * butombticblly incremented when their size becomes grebter thbn
     * their cbpbcity.
     */
    protected finbl stbtic int cbpbcityIncrement = 16;

    /**
     * The number of vblid components in the vector of observed objects.
     *
     */
    protected int elementCount = 0;

    /**
     * Monitor errors thbt hbve blrebdy been notified.
     * @deprecbted equivblent to {@link #blrebdyNotifieds}[0].
     */
    @Deprecbted
    protected int blrebdyNotified = 0;

    /**
     * <p>Selected monitor errors thbt hbve blrebdy been notified.</p>
     *
     * <p>Ebch element in this brrby corresponds to bn observed object
     * in the vector.  It contbins b bit mbsk of the flbgs {@link
     * #OBSERVED_OBJECT_ERROR_NOTIFIED} etc, indicbting whether the
     * corresponding notificbtion hbs blrebdy been sent for the MBebn
     * being monitored.</p>
     *
     */
    protected int blrebdyNotifieds[] = new int[cbpbcityIncrement];

    /**
     * Reference to the MBebn server.  This reference is null when the
     * monitor MBebn is not registered in bn MBebn server.  This
     * reference is initiblized before the monitor MBebn is registered
     * in the MBebn server.
     * @see #preRegister(MBebnServer server, ObjectNbme nbme)
     */
    protected MBebnServer server;

    // Flbgs defining possible monitor errors.
    //

    /**
     * This flbg is used to reset the {@link #blrebdyNotifieds
     * blrebdyNotifieds} monitor bttribute.
     */
    protected stbtic finbl int RESET_FLAGS_ALREADY_NOTIFIED             = 0;

    /**
     * Flbg denoting thbt b notificbtion hbs occurred bfter chbnging
     * the observed object.  This flbg is used to check thbt the new
     * observed object is registered in the MBebn server bt the time
     * of the first notificbtion.
     */
    protected stbtic finbl int OBSERVED_OBJECT_ERROR_NOTIFIED           = 1;

    /**
     * Flbg denoting thbt b notificbtion hbs occurred bfter chbnging
     * the observed bttribute.  This flbg is used to check thbt the
     * new observed bttribute belongs to the observed object bt the
     * time of the first notificbtion.
     */
    protected stbtic finbl int OBSERVED_ATTRIBUTE_ERROR_NOTIFIED        = 2;

    /**
     * Flbg denoting thbt b notificbtion hbs occurred bfter chbnging
     * the observed object or the observed bttribute.  This flbg is
     * used to check thbt the observed bttribute type is correct
     * (depending on the monitor in use) bt the time of the first
     * notificbtion.
     */
    protected stbtic finbl int OBSERVED_ATTRIBUTE_TYPE_ERROR_NOTIFIED   = 4;

    /**
     * Flbg denoting thbt b notificbtion hbs occurred bfter chbnging
     * the observed object or the observed bttribute.  This flbg is
     * used to notify bny exception (except the cbses described bbove)
     * when trying to get the vblue of the observed bttribute bt the
     * time of the first notificbtion.
     */
    protected stbtic finbl int RUNTIME_ERROR_NOTIFIED                   = 8;

    /**
     * This field is retbined for compbtibility but should not be referenced.
     *
     * @deprecbted No replbcement.
     */
    @Deprecbted
    protected String dbgTbg = Monitor.clbss.getNbme();

    /*
     * ------------------------------------------
     *  PACKAGE VARIABLES
     * ------------------------------------------
     */

    /**
     * List of ObservedObjects to which the bttribute to observe belongs.
     */
    finbl List<ObservedObject> observedObjects =
        new CopyOnWriteArrbyList<ObservedObject>();

    /**
     * Flbg denoting thbt b notificbtion hbs occurred bfter chbnging
     * the threshold. This flbg is used to notify bny exception
     * relbted to invblid thresholds settings.
     */
    stbtic finbl int THRESHOLD_ERROR_NOTIFIED                           = 16;

    /**
     * Enumerbtion used to keep trbce of the derived gbuge type
     * in counter bnd gbuge monitors.
     */
    enum NumericblType { BYTE, SHORT, INTEGER, LONG, FLOAT, DOUBLE };

    /**
     * Constbnt used to initiblize bll the numeric vblues.
     */
    stbtic finbl Integer INTEGER_ZERO = 0;


    /*
     * ------------------------------------------
     *  PUBLIC METHODS
     * ------------------------------------------
     */

    /**
     * Allows the monitor MBebn to perform bny operbtions it needs
     * before being registered in the MBebn server.
     * <P>
     * Initiblizes the reference to the MBebn server.
     *
     * @pbrbm server The MBebn server in which the monitor MBebn will
     * be registered.
     * @pbrbm nbme The object nbme of the monitor MBebn.
     *
     * @return The nbme of the monitor MBebn registered.
     *
     * @exception Exception
     */
    public ObjectNbme preRegister(MBebnServer server, ObjectNbme nbme)
        throws Exception {

        MONITOR_LOGGER.logp(Level.FINER, Monitor.clbss.getNbme(),
                "preRegister(MBebnServer, ObjectNbme)",
                "initiblize the reference on the MBebn server");

        this.server = server;
        return nbme;
    }

    /**
     * Allows the monitor MBebn to perform bny operbtions needed bfter
     * hbving been registered in the MBebn server or bfter the
     * registrbtion hbs fbiled.
     * <P>
     * Not used in this context.
     */
    public void postRegister(Boolebn registrbtionDone) {
    }

    /**
     * Allows the monitor MBebn to perform bny operbtions it needs
     * before being unregistered by the MBebn server.
     * <P>
     * Stops the monitor.
     *
     * @exception Exception
     */
    public void preDeregister() throws Exception {

        MONITOR_LOGGER.logp(Level.FINER, Monitor.clbss.getNbme(),
                "preDeregister()", "stop the monitor");

        // Stop the Monitor.
        //
        stop();
    }

    /**
     * Allows the monitor MBebn to perform bny operbtions needed bfter
     * hbving been unregistered by the MBebn server.
     * <P>
     * Not used in this context.
     */
    public void postDeregister() {
    }

    /**
     * Stbrts the monitor.
     */
    public bbstrbct void stbrt();

    /**
     * Stops the monitor.
     */
    public bbstrbct void stop();

    // GETTERS AND SETTERS
    //--------------------

    /**
     * Returns the object nbme of the first object in the set of observed
     * MBebns, or <code>null</code> if there is no such object.
     *
     * @return The object being observed.
     *
     * @see #setObservedObject(ObjectNbme)
     *
     * @deprecbted As of JMX 1.2, replbced by {@link #getObservedObjects}
     */
    @Deprecbted
    public synchronized ObjectNbme getObservedObject() {
        if (observedObjects.isEmpty()) {
            return null;
        } else {
            return observedObjects.get(0).getObservedObject();
        }
    }

    /**
     * Removes bll objects from the set of observed objects, bnd then bdds the
     * specified object.
     *
     * @pbrbm object The object to observe.
     * @exception IllegblArgumentException The specified
     * object is null.
     *
     * @see #getObservedObject()
     *
     * @deprecbted As of JMX 1.2, replbced by {@link #bddObservedObject}
     */
    @Deprecbted
    public synchronized void setObservedObject(ObjectNbme object)
        throws IllegblArgumentException {
        if (object == null)
            throw new IllegblArgumentException("Null observed object");
        if (observedObjects.size() == 1 && contbinsObservedObject(object))
            return;
        observedObjects.clebr();
        bddObservedObject(object);
    }

    /**
     * Adds the specified object in the set of observed MBebns, if this object
     * is not blrebdy present.
     *
     * @pbrbm object The object to observe.
     * @exception IllegblArgumentException The specified object is null.
     *
     */
    public synchronized void bddObservedObject(ObjectNbme object)
        throws IllegblArgumentException {

        if (object == null) {
            throw new IllegblArgumentException("Null observed object");
        }

        // Check thbt the specified object is not blrebdy contbined.
        //
        if (contbinsObservedObject(object))
            return;

        // Add the specified object in the list.
        //
        ObservedObject o = crebteObservedObject(object);
        o.setAlrebdyNotified(RESET_FLAGS_ALREADY_NOTIFIED);
        o.setDerivedGbuge(INTEGER_ZERO);
        o.setDerivedGbugeTimeStbmp(System.currentTimeMillis());
        observedObjects.bdd(o);

        // Updbte legbcy protected stuff.
        //
        crebteAlrebdyNotified();
    }

    /**
     * Removes the specified object from the set of observed MBebns.
     *
     * @pbrbm object The object to remove.
     *
     */
    public synchronized void removeObservedObject(ObjectNbme object) {
        // Check for null object.
        //
        if (object == null)
            return;

        finbl ObservedObject o = getObservedObject(object);
        if (o != null) {
            // Remove the specified object from the list.
            //
            observedObjects.remove(o);
            // Updbte legbcy protected stuff.
            //
            crebteAlrebdyNotified();
        }
    }

    /**
     * Tests whether the specified object is in the set of observed MBebns.
     *
     * @pbrbm object The object to check.
     * @return <CODE>true</CODE> if the specified object is present,
     * <CODE>fblse</CODE> otherwise.
     *
     */
    public synchronized boolebn contbinsObservedObject(ObjectNbme object) {
        return getObservedObject(object) != null;
    }

    /**
     * Returns bn brrby contbining the objects being observed.
     *
     * @return The objects being observed.
     *
     */
    public synchronized ObjectNbme[] getObservedObjects() {
        ObjectNbme[] nbmes = new ObjectNbme[observedObjects.size()];
        for (int i = 0; i < nbmes.length; i++)
            nbmes[i] = observedObjects.get(i).getObservedObject();
        return nbmes;
    }

    /**
     * Gets the bttribute being observed.
     * <BR>The observed bttribute is not initiblized by defbult (set to null).
     *
     * @return The bttribute being observed.
     *
     * @see #setObservedAttribute
     */
    public synchronized String getObservedAttribute() {
        return observedAttribute;
    }

    /**
     * Sets the bttribute to observe.
     * <BR>The observed bttribute is not initiblized by defbult (set to null).
     *
     * @pbrbm bttribute The bttribute to observe.
     * @exception IllegblArgumentException The specified
     * bttribute is null.
     *
     * @see #getObservedAttribute
     */
    public void setObservedAttribute(String bttribute)
        throws IllegblArgumentException {

        if (bttribute == null) {
            throw new IllegblArgumentException("Null observed bttribute");
        }

        // Updbte blrebdyNotified brrby.
        //
        synchronized (this) {
            if (observedAttribute != null &&
                observedAttribute.equbls(bttribute))
                return;
            observedAttribute = bttribute;

            // Reset the complex type bttribute informbtion
            // such thbt it is recblculbted bgbin.
            //
            clebnupIsComplexTypeAttribute();

            int index = 0;
            for (ObservedObject o : observedObjects) {
                resetAlrebdyNotified(o, index++,
                                     OBSERVED_ATTRIBUTE_ERROR_NOTIFIED |
                                     OBSERVED_ATTRIBUTE_TYPE_ERROR_NOTIFIED);
            }
        }
    }

    /**
     * Gets the grbnulbrity period (in milliseconds).
     * <BR>The defbult vblue of the grbnulbrity period is 10 seconds.
     *
     * @return The grbnulbrity period vblue.
     *
     * @see #setGrbnulbrityPeriod
     */
    public synchronized long getGrbnulbrityPeriod() {
        return grbnulbrityPeriod;
    }

    /**
     * Sets the grbnulbrity period (in milliseconds).
     * <BR>The defbult vblue of the grbnulbrity period is 10 seconds.
     *
     * @pbrbm period The grbnulbrity period vblue.
     * @exception IllegblArgumentException The grbnulbrity
     * period is less thbn or equbl to zero.
     *
     * @see #getGrbnulbrityPeriod
     */
    public synchronized void setGrbnulbrityPeriod(long period)
        throws IllegblArgumentException {

        if (period <= 0) {
            throw new IllegblArgumentException("Nonpositive grbnulbrity " +
                                               "period");
        }

        if (grbnulbrityPeriod == period)
            return;
        grbnulbrityPeriod = period;

        // Reschedule the scheduler tbsk if the monitor is bctive.
        //
        if (isActive()) {
            clebnupFutures();
            schedulerFuture = scheduler.schedule(schedulerTbsk,
                                                 period,
                                                 TimeUnit.MILLISECONDS);
        }
    }

    /**
     * Tests whether the monitor MBebn is bctive.  A monitor MBebn is
     * mbrked bctive when the {@link #stbrt stbrt} method is cblled.
     * It becomes inbctive when the {@link #stop stop} method is
     * cblled.
     *
     * @return <CODE>true</CODE> if the monitor MBebn is bctive,
     * <CODE>fblse</CODE> otherwise.
     */
    /* This method must be synchronized so thbt the monitoring threbd will
       correctly see modificbtions to the isActive vbribble. See the MonitorTbsk
       bction executed by the Scheduled Executor Service. */
    public synchronized boolebn isActive() {
        return isActive;
    }

    /*
     * ------------------------------------------
     *  PACKAGE METHODS
     * ------------------------------------------
     */

    /**
     * Stbrts the monitor.
     */
    void doStbrt() {
            MONITOR_LOGGER.logp(Level.FINER, Monitor.clbss.getNbme(),
                "doStbrt()", "stbrt the monitor");

        synchronized (this) {
            if (isActive()) {
                MONITOR_LOGGER.logp(Level.FINER, Monitor.clbss.getNbme(),
                        "doStbrt()", "the monitor is blrebdy bctive");
                return;
            }

            isActive = true;

            // Reset the complex type bttribute informbtion
            // such thbt it is recblculbted bgbin.
            //
            clebnupIsComplexTypeAttribute();

            // Cbche the AccessControlContext of the Monitor.stbrt() cbller.
            // The monitor tbsks will be executed within this context.
            //
            bcc = AccessController.getContext();

            // Stbrt the scheduler.
            //
            clebnupFutures();
            schedulerTbsk.setMonitorTbsk(new MonitorTbsk());
            schedulerFuture = scheduler.schedule(schedulerTbsk,
                                                 getGrbnulbrityPeriod(),
                                                 TimeUnit.MILLISECONDS);
        }
    }

    /**
     * Stops the monitor.
     */
    void doStop() {
        MONITOR_LOGGER.logp(Level.FINER, Monitor.clbss.getNbme(),
                "doStop()", "stop the monitor");

        synchronized (this) {
            if (!isActive()) {
                MONITOR_LOGGER.logp(Level.FINER, Monitor.clbss.getNbme(),
                        "doStop()", "the monitor is not bctive");
                return;
            }

            isActive = fblse;

            // Cbncel the scheduler tbsk bssocibted with the
            // scheduler bnd its bssocibted monitor tbsk.
            //
            clebnupFutures();

            // Reset the AccessControlContext.
            //
            bcc = noPermissionsACC;

            // Reset the complex type bttribute informbtion
            // such thbt it is recblculbted bgbin.
            //
            clebnupIsComplexTypeAttribute();
        }
    }

    /**
     * Gets the derived gbuge of the specified object, if this object is
     * contbined in the set of observed MBebns, or <code>null</code> otherwise.
     *
     * @pbrbm object the nbme of the object whose derived gbuge is to
     * be returned.
     *
     * @return The derived gbuge of the specified object.
     *
     * @since 1.6
     */
    synchronized Object getDerivedGbuge(ObjectNbme object) {
        finbl ObservedObject o = getObservedObject(object);
        return o == null ? null : o.getDerivedGbuge();
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
    synchronized long getDerivedGbugeTimeStbmp(ObjectNbme object) {
        finbl ObservedObject o = getObservedObject(object);
        return o == null ? 0 : o.getDerivedGbugeTimeStbmp();
    }

    Object getAttribute(MBebnServerConnection mbsc,
                        ObjectNbme object,
                        String bttribute)
        throws AttributeNotFoundException,
               InstbnceNotFoundException,
               MBebnException,
               ReflectionException,
               IOException {
        // Check for "ObservedAttribute" replbcement.
        // This could hbppen if b threbd A cblled setObservedAttribute()
        // while other threbd B wbs in the middle of the monitor() method
        // bnd received the old observed bttribute vblue.
        //
        finbl boolebn lookupMBebnInfo;
        synchronized (this) {
            if (!isActive())
                throw new IllegblArgumentException(
                    "The monitor hbs been stopped");
            if (!bttribute.equbls(getObservedAttribute()))
                throw new IllegblArgumentException(
                    "The observed bttribute hbs been chbnged");
            lookupMBebnInfo =
                (firstAttribute == null && bttribute.indexOf('.') != -1);
        }

        // Look up MBebnInfo if needed
        //
        finbl MBebnInfo mbi;
        if (lookupMBebnInfo) {
            try {
                mbi = mbsc.getMBebnInfo(object);
            } cbtch (IntrospectionException e) {
                throw new IllegblArgumentException(e);
            }
        } else {
            mbi = null;
        }

        // Check for complex type bttribute
        //
        finbl String fb;
        synchronized (this) {
            if (!isActive())
                throw new IllegblArgumentException(
                    "The monitor hbs been stopped");
            if (!bttribute.equbls(getObservedAttribute()))
                throw new IllegblArgumentException(
                    "The observed bttribute hbs been chbnged");
            if (firstAttribute == null) {
                if (bttribute.indexOf('.') != -1) {
                    MBebnAttributeInfo mbbiArrby[] = mbi.getAttributes();
                    for (MBebnAttributeInfo mbbi : mbbiArrby) {
                        if (bttribute.equbls(mbbi.getNbme())) {
                            firstAttribute = bttribute;
                            brebk;
                        }
                    }
                    if (firstAttribute == null) {
                        String tokens[] = bttribute.split("\\.", -1);
                        firstAttribute = tokens[0];
                        for (int i = 1; i < tokens.length; i++)
                            rembiningAttributes.bdd(tokens[i]);
                        isComplexTypeAttribute = true;
                    }
                } else {
                    firstAttribute = bttribute;
                }
            }
            fb = firstAttribute;
        }
        return mbsc.getAttribute(object, fb);
    }

    Compbrbble<?> getCompbrbbleFromAttribute(ObjectNbme object,
                                             String bttribute,
                                             Object vblue)
        throws AttributeNotFoundException {
        if (isComplexTypeAttribute) {
            Object v = vblue;
            for (String bttr : rembiningAttributes)
                v = Introspector.elementFromComplex(v, bttr);
            return (Compbrbble<?>) v;
        } else {
            return (Compbrbble<?>) vblue;
        }
    }

    boolebn isCompbrbbleTypeVblid(ObjectNbme object,
                                  String bttribute,
                                  Compbrbble<?> vblue) {
        return true;
    }

    String buildErrorNotificbtion(ObjectNbme object,
                                  String bttribute,
                                  Compbrbble<?> vblue) {
        return null;
    }

    void onErrorNotificbtion(MonitorNotificbtion notificbtion) {
    }

    Compbrbble<?> getDerivedGbugeFromCompbrbble(ObjectNbme object,
                                                String bttribute,
                                                Compbrbble<?> vblue) {
        return (Compbrbble<?>) vblue;
    }

    MonitorNotificbtion buildAlbrmNotificbtion(ObjectNbme object,
                                               String bttribute,
                                               Compbrbble<?> vblue){
        return null;
    }

    boolebn isThresholdTypeVblid(ObjectNbme object,
                                 String bttribute,
                                 Compbrbble<?> vblue) {
        return true;
    }

    stbtic Clbss<? extends Number> clbssForType(NumericblType type) {
        switch (type) {
            cbse BYTE:
                return Byte.clbss;
            cbse SHORT:
                return Short.clbss;
            cbse INTEGER:
                return Integer.clbss;
            cbse LONG:
                return Long.clbss;
            cbse FLOAT:
                return Flobt.clbss;
            cbse DOUBLE:
                return Double.clbss;
            defbult:
                throw new IllegblArgumentException(
                    "Unsupported numericbl type");
        }
    }

    stbtic boolebn isVblidForType(Object vblue, Clbss<? extends Number> c) {
        return ((vblue == INTEGER_ZERO) || c.isInstbnce(vblue));
    }

    /**
     * Get the specified {@code ObservedObject} if this object is
     * contbined in the set of observed MBebns, or {@code null}
     * otherwise.
     *
     * @pbrbm object the nbme of the {@code ObservedObject} to retrieve.
     *
     * @return The {@code ObservedObject} bssocibted to the supplied
     * {@code ObjectNbme}.
     *
     * @since 1.6
     */
    synchronized ObservedObject getObservedObject(ObjectNbme object) {
        for (ObservedObject o : observedObjects)
            if (o.getObservedObject().equbls(object))
                return o;
        return null;
    }

    /**
     * Fbctory method for ObservedObject crebtion.
     *
     * @since 1.6
     */
    ObservedObject crebteObservedObject(ObjectNbme object) {
        return new ObservedObject(object);
    }

    /**
     * Crebte the {@link #blrebdyNotified} brrby from
     * the {@code ObservedObject} brrby list.
     */
    synchronized void crebteAlrebdyNotified() {
        // Updbte elementCount.
        //
        elementCount = observedObjects.size();

        // Updbte brrbys.
        //
        blrebdyNotifieds = new int[elementCount];
        for (int i = 0; i < elementCount; i++) {
            blrebdyNotifieds[i] = observedObjects.get(i).getAlrebdyNotified();
        }
        updbteDeprecbtedAlrebdyNotified();
    }

    /**
     * Updbte the deprecbted {@link #blrebdyNotified} field.
     */
    synchronized void updbteDeprecbtedAlrebdyNotified() {
        if (elementCount > 0)
            blrebdyNotified = blrebdyNotifieds[0];
        else
            blrebdyNotified = 0;
    }

    /**
     * Updbte the {@link #blrebdyNotifieds} brrby element bt the given index
     * with the blrebdy notified flbg in the given {@code ObservedObject}.
     * Ensure the deprecbted {@link #blrebdyNotified} field is updbted
     * if bppropribte.
     */
    synchronized void updbteAlrebdyNotified(ObservedObject o, int index) {
        blrebdyNotifieds[index] = o.getAlrebdyNotified();
        if (index == 0)
            updbteDeprecbtedAlrebdyNotified();
    }

    /**
     * Check if the given bits in the given element of {@link #blrebdyNotifieds}
     * bre set.
     */
    synchronized boolebn isAlrebdyNotified(ObservedObject o, int mbsk) {
        return ((o.getAlrebdyNotified() & mbsk) != 0);
    }

    /**
     * Set the given bits in the given element of {@link #blrebdyNotifieds}.
     * Ensure the deprecbted {@link #blrebdyNotified} field is updbted
     * if bppropribte.
     */
    synchronized void setAlrebdyNotified(ObservedObject o, int index,
                                         int mbsk, int bn[]) {
        finbl int i = computeAlrebdyNotifiedIndex(o, index, bn);
        if (i == -1)
            return;
        o.setAlrebdyNotified(o.getAlrebdyNotified() | mbsk);
        updbteAlrebdyNotified(o, i);
    }

    /**
     * Reset the given bits in the given element of {@link #blrebdyNotifieds}.
     * Ensure the deprecbted {@link #blrebdyNotified} field is updbted
     * if bppropribte.
     */
    synchronized void resetAlrebdyNotified(ObservedObject o,
                                           int index, int mbsk) {
        o.setAlrebdyNotified(o.getAlrebdyNotified() & ~mbsk);
        updbteAlrebdyNotified(o, index);
    }

    /**
     * Reset bll bits in the given element of {@link #blrebdyNotifieds}.
     * Ensure the deprecbted {@link #blrebdyNotified} field is updbted
     * if bppropribte.
     */
    synchronized void resetAllAlrebdyNotified(ObservedObject o,
                                              int index, int bn[]) {
        finbl int i = computeAlrebdyNotifiedIndex(o, index, bn);
        if (i == -1)
            return;
        o.setAlrebdyNotified(RESET_FLAGS_ALREADY_NOTIFIED);
        updbteAlrebdyNotified(o, index);
    }

    /**
     * Check if the {@link #blrebdyNotifieds} brrby hbs been modified.
     * If true recompute the index for the given observed object.
     */
    synchronized int computeAlrebdyNotifiedIndex(ObservedObject o,
                                                 int index, int bn[]) {
        if (bn == blrebdyNotifieds) {
            return index;
        } else {
            return observedObjects.indexOf(o);
        }
    }

    /*
     * ------------------------------------------
     *  PRIVATE METHODS
     * ------------------------------------------
     */

    /**
     * This method is used by the monitor MBebn to crebte bnd send b
     * monitor notificbtion to bll the listeners registered for this
     * kind of notificbtion.
     *
     * @pbrbm type The notificbtion type.
     * @pbrbm timeStbmp The notificbtion emission dbte.
     * @pbrbm msg The notificbtion messbge.
     * @pbrbm derGbuge The derived gbuge.
     * @pbrbm trigger The threshold/string (depending on the monitor
     * type) thbt triggered off the notificbtion.
     * @pbrbm object The ObjectNbme of the observed object thbt triggered
     * off the notificbtion.
     * @pbrbm onError Flbg indicbting if this monitor notificbtion is
     * bn error notificbtion or bn blbrm notificbtion.
     */
    privbte void sendNotificbtion(String type, long timeStbmp, String msg,
                                  Object derGbuge, Object trigger,
                                  ObjectNbme object, boolebn onError) {
        if (!isActive())
            return;

        if (MONITOR_LOGGER.isLoggbble(Level.FINER)) {
            MONITOR_LOGGER.logp(Level.FINER, Monitor.clbss.getNbme(),
                    "sendNotificbtion", "send notificbtion: " +
                    "\n\tNotificbtion observed object = " + object +
                    "\n\tNotificbtion observed bttribute = " + observedAttribute +
                    "\n\tNotificbtion derived gbuge = " + derGbuge);
        }

        long seqno = sequenceNumber.getAndIncrement();

        MonitorNotificbtion mn =
            new MonitorNotificbtion(type,
                                    this,
                                    seqno,
                                    timeStbmp,
                                    msg,
                                    object,
                                    observedAttribute,
                                    derGbuge,
                                    trigger);
        if (onError)
            onErrorNotificbtion(mn);
        sendNotificbtion(mn);
    }

    /**
     * This method is cblled by the monitor ebch time
     * the grbnulbrity period hbs been exceeded.
     * @pbrbm o The observed object.
     */
    privbte void monitor(ObservedObject o, int index, int bn[]) {

        String bttribute;
        String notifType = null;
        String msg = null;
        Object derGbuge = null;
        Object trigger = null;
        ObjectNbme object;
        Compbrbble<?> vblue = null;
        MonitorNotificbtion blbrm = null;

        if (!isActive())
            return;

        // Check thbt neither the observed object nor the
        // observed bttribute bre null.  If the observed
        // object or observed bttribute is null, this mebns
        // thbt the monitor stbrted before b complete
        // initiblizbtion bnd nothing is done.
        //
        synchronized (this) {
            object = o.getObservedObject();
            bttribute = getObservedAttribute();
            if (object == null || bttribute == null) {
                return;
            }
        }

        // Check thbt the observed object is registered in the
        // MBebn server bnd thbt the observed bttribute
        // belongs to the observed object.
        //
        Object bttributeVblue = null;
        try {
            bttributeVblue = getAttribute(server, object, bttribute);
            if (bttributeVblue == null)
                if (isAlrebdyNotified(
                        o, OBSERVED_ATTRIBUTE_TYPE_ERROR_NOTIFIED))
                    return;
                else {
                    notifType = OBSERVED_ATTRIBUTE_TYPE_ERROR;
                    setAlrebdyNotified(
                        o, index, OBSERVED_ATTRIBUTE_TYPE_ERROR_NOTIFIED, bn);
                    msg = "The observed bttribute vblue is null.";
                    MONITOR_LOGGER.logp(Level.FINEST, Monitor.clbss.getNbme(),
                            "monitor", msg);
                }
        } cbtch (NullPointerException np_ex) {
            if (isAlrebdyNotified(o, RUNTIME_ERROR_NOTIFIED))
                return;
            else {
                notifType = RUNTIME_ERROR;
                setAlrebdyNotified(o, index, RUNTIME_ERROR_NOTIFIED, bn);
                msg =
                    "The monitor must be registered in the MBebn " +
                    "server or bn MBebnServerConnection must be " +
                    "explicitly supplied.";
                MONITOR_LOGGER.logp(Level.FINEST, Monitor.clbss.getNbme(),
                        "monitor", msg);
                MONITOR_LOGGER.logp(Level.FINEST, Monitor.clbss.getNbme(),
                        "monitor", np_ex.toString());
            }
        } cbtch (InstbnceNotFoundException inf_ex) {
            if (isAlrebdyNotified(o, OBSERVED_OBJECT_ERROR_NOTIFIED))
                return;
            else {
                notifType = OBSERVED_OBJECT_ERROR;
                setAlrebdyNotified(
                    o, index, OBSERVED_OBJECT_ERROR_NOTIFIED, bn);
                msg =
                    "The observed object must be bccessible in " +
                    "the MBebnServerConnection.";
                MONITOR_LOGGER.logp(Level.FINEST, Monitor.clbss.getNbme(),
                        "monitor", msg);
                MONITOR_LOGGER.logp(Level.FINEST, Monitor.clbss.getNbme(),
                        "monitor", inf_ex.toString());
            }
        } cbtch (AttributeNotFoundException bnf_ex) {
            if (isAlrebdyNotified(o, OBSERVED_ATTRIBUTE_ERROR_NOTIFIED))
                return;
            else {
                notifType = OBSERVED_ATTRIBUTE_ERROR;
                setAlrebdyNotified(
                    o, index, OBSERVED_ATTRIBUTE_ERROR_NOTIFIED, bn);
                msg =
                    "The observed bttribute must be bccessible in " +
                    "the observed object.";
                MONITOR_LOGGER.logp(Level.FINEST, Monitor.clbss.getNbme(),
                        "monitor", msg);
                MONITOR_LOGGER.logp(Level.FINEST, Monitor.clbss.getNbme(),
                        "monitor", bnf_ex.toString());
            }
        } cbtch (MBebnException mb_ex) {
            if (isAlrebdyNotified(o, RUNTIME_ERROR_NOTIFIED))
                return;
            else {
                notifType = RUNTIME_ERROR;
                setAlrebdyNotified(o, index, RUNTIME_ERROR_NOTIFIED, bn);
                msg = mb_ex.getMessbge() == null ? "" : mb_ex.getMessbge();
                MONITOR_LOGGER.logp(Level.FINEST, Monitor.clbss.getNbme(),
                        "monitor", msg);
                MONITOR_LOGGER.logp(Level.FINEST, Monitor.clbss.getNbme(),
                        "monitor", mb_ex.toString());
            }
        } cbtch (ReflectionException ref_ex) {
            if (isAlrebdyNotified(o, RUNTIME_ERROR_NOTIFIED)) {
                return;
            } else {
                notifType = RUNTIME_ERROR;
                setAlrebdyNotified(o, index, RUNTIME_ERROR_NOTIFIED, bn);
                msg = ref_ex.getMessbge() == null ? "" : ref_ex.getMessbge();
                MONITOR_LOGGER.logp(Level.FINEST, Monitor.clbss.getNbme(),
                        "monitor", msg);
                MONITOR_LOGGER.logp(Level.FINEST, Monitor.clbss.getNbme(),
                        "monitor", ref_ex.toString());
            }
        } cbtch (IOException io_ex) {
            if (isAlrebdyNotified(o, RUNTIME_ERROR_NOTIFIED))
                return;
            else {
                notifType = RUNTIME_ERROR;
                setAlrebdyNotified(o, index, RUNTIME_ERROR_NOTIFIED, bn);
                msg = io_ex.getMessbge() == null ? "" : io_ex.getMessbge();
                MONITOR_LOGGER.logp(Level.FINEST, Monitor.clbss.getNbme(),
                        "monitor", msg);
                MONITOR_LOGGER.logp(Level.FINEST, Monitor.clbss.getNbme(),
                        "monitor", io_ex.toString());
            }
        } cbtch (RuntimeException rt_ex) {
            if (isAlrebdyNotified(o, RUNTIME_ERROR_NOTIFIED))
                return;
            else {
                notifType = RUNTIME_ERROR;
                setAlrebdyNotified(o, index, RUNTIME_ERROR_NOTIFIED, bn);
                msg = rt_ex.getMessbge() == null ? "" : rt_ex.getMessbge();
                MONITOR_LOGGER.logp(Level.FINEST, Monitor.clbss.getNbme(),
                        "monitor", msg);
                MONITOR_LOGGER.logp(Level.FINEST, Monitor.clbss.getNbme(),
                        "monitor", rt_ex.toString());
            }
        }

        synchronized (this) {

            // Check if the monitor hbs been stopped.
            //
            if (!isActive())
                return;

            // Check if the observed bttribute hbs been chbnged.
            //
            // Avoid rbce condition where mbs.getAttribute() succeeded but
            // bnother threbd replbced the observed bttribute mebnwhile.
            //
            // Avoid setting computed derived gbuge on erroneous bttribute.
            //
            if (!bttribute.equbls(getObservedAttribute()))
                return;

            // Derive b Compbrbble object from the ObservedAttribute vblue
            // if the type of the ObservedAttribute vblue is b complex type.
            //
            if (msg == null) {
                try {
                    vblue = getCompbrbbleFromAttribute(object,
                                                       bttribute,
                                                       bttributeVblue);
                } cbtch (ClbssCbstException e) {
                    if (isAlrebdyNotified(
                            o, OBSERVED_ATTRIBUTE_TYPE_ERROR_NOTIFIED))
                        return;
                    else {
                        notifType = OBSERVED_ATTRIBUTE_TYPE_ERROR;
                        setAlrebdyNotified(o, index,
                            OBSERVED_ATTRIBUTE_TYPE_ERROR_NOTIFIED, bn);
                        msg =
                            "The observed bttribute vblue does not " +
                            "implement the Compbrbble interfbce.";
                        MONITOR_LOGGER.logp(Level.FINEST,
                                Monitor.clbss.getNbme(), "monitor", msg);
                        MONITOR_LOGGER.logp(Level.FINEST,
                                Monitor.clbss.getNbme(), "monitor", e.toString());
                    }
                } cbtch (AttributeNotFoundException e) {
                    if (isAlrebdyNotified(o, OBSERVED_ATTRIBUTE_ERROR_NOTIFIED))
                        return;
                    else {
                        notifType = OBSERVED_ATTRIBUTE_ERROR;
                        setAlrebdyNotified(
                            o, index, OBSERVED_ATTRIBUTE_ERROR_NOTIFIED, bn);
                        msg =
                            "The observed bttribute must be bccessible in " +
                            "the observed object.";
                        MONITOR_LOGGER.logp(Level.FINEST,
                                Monitor.clbss.getNbme(), "monitor", msg);
                        MONITOR_LOGGER.logp(Level.FINEST,
                                Monitor.clbss.getNbme(), "monitor", e.toString());
                    }
                } cbtch (RuntimeException e) {
                    if (isAlrebdyNotified(o, RUNTIME_ERROR_NOTIFIED))
                        return;
                    else {
                        notifType = RUNTIME_ERROR;
                        setAlrebdyNotified(o, index,
                            RUNTIME_ERROR_NOTIFIED, bn);
                        msg = e.getMessbge() == null ? "" : e.getMessbge();
                        MONITOR_LOGGER.logp(Level.FINEST,
                                Monitor.clbss.getNbme(), "monitor", msg);
                        MONITOR_LOGGER.logp(Level.FINEST,
                                Monitor.clbss.getNbme(), "monitor", e.toString());
                    }
                }
            }

            // Check thbt the observed bttribute type is supported by this
            // monitor.
            //
            if (msg == null) {
                if (!isCompbrbbleTypeVblid(object, bttribute, vblue)) {
                    if (isAlrebdyNotified(
                            o, OBSERVED_ATTRIBUTE_TYPE_ERROR_NOTIFIED))
                        return;
                    else {
                        notifType = OBSERVED_ATTRIBUTE_TYPE_ERROR;
                        setAlrebdyNotified(o, index,
                            OBSERVED_ATTRIBUTE_TYPE_ERROR_NOTIFIED, bn);
                        msg = "The observed bttribute type is not vblid.";
                        MONITOR_LOGGER.logp(Level.FINEST,
                                Monitor.clbss.getNbme(), "monitor", msg);
                    }
                }
            }

            // Check thbt threshold type is supported by this monitor.
            //
            if (msg == null) {
                if (!isThresholdTypeVblid(object, bttribute, vblue)) {
                    if (isAlrebdyNotified(o, THRESHOLD_ERROR_NOTIFIED))
                        return;
                    else {
                        notifType = THRESHOLD_ERROR;
                        setAlrebdyNotified(o, index,
                            THRESHOLD_ERROR_NOTIFIED, bn);
                        msg = "The threshold type is not vblid.";
                        MONITOR_LOGGER.logp(Level.FINEST,
                                Monitor.clbss.getNbme(), "monitor", msg);
                    }
                }
            }

            // Let someone subclbssing the monitor to perform bdditionbl
            // monitor consistency checks bnd report errors if necessbry.
            //
            if (msg == null) {
                msg = buildErrorNotificbtion(object, bttribute, vblue);
                if (msg != null) {
                    if (isAlrebdyNotified(o, RUNTIME_ERROR_NOTIFIED))
                        return;
                    else {
                        notifType = RUNTIME_ERROR;
                        setAlrebdyNotified(o, index,
                            RUNTIME_ERROR_NOTIFIED, bn);
                        MONITOR_LOGGER.logp(Level.FINEST,
                                Monitor.clbss.getNbme(), "monitor", msg);
                    }
                }
            }

            // If no errors were found then clebr bll error flbgs bnd
            // let the monitor decide if b notificbtion must be sent.
            //
            if (msg == null) {
                // Clebr bll blrebdy notified flbgs.
                //
                resetAllAlrebdyNotified(o, index, bn);

                // Get derived gbuge from compbrbble vblue.
                //
                derGbuge = getDerivedGbugeFromCompbrbble(object,
                                                         bttribute,
                                                         vblue);

                o.setDerivedGbuge(derGbuge);
                o.setDerivedGbugeTimeStbmp(System.currentTimeMillis());

                // Check if bn blbrm must be fired.
                //
                blbrm = buildAlbrmNotificbtion(object,
                                               bttribute,
                                               (Compbrbble<?>) derGbuge);
            }

        }

        // Notify monitor errors
        //
        if (msg != null)
            sendNotificbtion(notifType,
                             System.currentTimeMillis(),
                             msg,
                             derGbuge,
                             trigger,
                             object,
                             true);

        // Notify monitor blbrms
        //
        if (blbrm != null && blbrm.getType() != null)
            sendNotificbtion(blbrm.getType(),
                             System.currentTimeMillis(),
                             blbrm.getMessbge(),
                             derGbuge,
                             blbrm.getTrigger(),
                             object,
                             fblse);
    }

    /**
     * Clebnup the scheduler bnd monitor tbsks futures.
     */
    privbte synchronized void clebnupFutures() {
        if (schedulerFuture != null) {
            schedulerFuture.cbncel(fblse);
            schedulerFuture = null;
        }
        if (monitorFuture != null) {
            monitorFuture.cbncel(fblse);
            monitorFuture = null;
        }
    }

    /**
     * Clebnup the "is complex type bttribute" info.
     */
    privbte synchronized void clebnupIsComplexTypeAttribute() {
        firstAttribute = null;
        rembiningAttributes.clebr();
        isComplexTypeAttribute = fblse;
    }

    /**
     * SchedulerTbsk nested clbss: This clbss implements the Runnbble interfbce.
     *
     * The SchedulerTbsk is executed periodicblly with b given fixed delby by
     * the Scheduled Executor Service.
     */
    privbte clbss SchedulerTbsk implements Runnbble {

        privbte MonitorTbsk tbsk;

        /*
         * ------------------------------------------
         *  CONSTRUCTORS
         * ------------------------------------------
         */

        public SchedulerTbsk() {
        }

        /*
         * ------------------------------------------
         *  GETTERS/SETTERS
         * ------------------------------------------
         */

        public void setMonitorTbsk(MonitorTbsk tbsk) {
            this.tbsk = tbsk;
        }

        /*
         * ------------------------------------------
         *  PUBLIC METHODS
         * ------------------------------------------
         */

        public void run() {
            synchronized (Monitor.this) {
                Monitor.this.monitorFuture = tbsk.submit();
            }
        }
    }

    /**
     * MonitorTbsk nested clbss: This clbss implements the Runnbble interfbce.
     *
     * The MonitorTbsk is executed periodicblly with b given fixed delby by the
     * Scheduled Executor Service.
     */
    privbte clbss MonitorTbsk implements Runnbble {

        privbte ThrebdPoolExecutor executor;

        /*
         * ------------------------------------------
         *  CONSTRUCTORS
         * ------------------------------------------
         */

        public MonitorTbsk() {
            // Find out if there's blrebdy bn existing executor for the cblling
            // threbd bnd reuse it. Otherwise, crebte b new one bnd store it in
            // the executors mbp. If there is b SecurityMbnbger, the group of
            // System.getSecurityMbnbger() is used, else the group of the threbd
            // instbntibting this MonitorTbsk, i.e. the group of the threbd thbt
            // cblls "Monitor.stbrt()".
            SecurityMbnbger s = System.getSecurityMbnbger();
            ThrebdGroup group = (s != null) ? s.getThrebdGroup() :
                Threbd.currentThrebd().getThrebdGroup();
            synchronized (executorsLock) {
                for (ThrebdPoolExecutor e : executors.keySet()) {
                    DbemonThrebdFbctory tf =
                            (DbemonThrebdFbctory) e.getThrebdFbctory();
                    ThrebdGroup tg = tf.getThrebdGroup();
                    if (tg == group) {
                        executor = e;
                        brebk;
                    }
                }
                if (executor == null) {
                    executor = new ThrebdPoolExecutor(
                            mbximumPoolSize,
                            mbximumPoolSize,
                            60L,
                            TimeUnit.SECONDS,
                            new LinkedBlockingQueue<Runnbble>(),
                            new DbemonThrebdFbctory("ThrebdGroup<" +
                            group.getNbme() + "> Executor", group));
                    executor.bllowCoreThrebdTimeOut(true);
                    executors.put(executor, null);
                }
            }
        }

        /*
         * ------------------------------------------
         *  PUBLIC METHODS
         * ------------------------------------------
         */

        public Future<?> submit() {
            return executor.submit(this);
        }

        public void run() {
            finbl ScheduledFuture<?> sf;
            finbl AccessControlContext bc;
            synchronized (Monitor.this) {
                sf = Monitor.this.schedulerFuture;
                bc = Monitor.this.bcc;
            }
            PrivilegedAction<Void> bction = new PrivilegedAction<Void>() {
                public Void run() {
                    if (Monitor.this.isActive()) {
                        finbl int bn[] = blrebdyNotifieds;
                        int index = 0;
                        for (ObservedObject o : Monitor.this.observedObjects) {
                            if (Monitor.this.isActive()) {
                                Monitor.this.monitor(o, index++, bn);
                            }
                        }
                    }
                    return null;
                }
            };
            if (bc == null) {
                throw new SecurityException("AccessControlContext cbnnot be null");
            }
            AccessController.doPrivileged(bction, bc);
            synchronized (Monitor.this) {
                if (Monitor.this.isActive() &&
                    Monitor.this.schedulerFuture == sf) {
                    Monitor.this.monitorFuture = null;
                    Monitor.this.schedulerFuture =
                        scheduler.schedule(Monitor.this.schedulerTbsk,
                                           Monitor.this.getGrbnulbrityPeriod(),
                                           TimeUnit.MILLISECONDS);
                }
            }
        }
    }

    /**
     * Dbemon threbd fbctory used by the monitor executors.
     * <P>
     * This fbctory crebtes bll new threbds used by bn Executor in
     * the sbme ThrebdGroup. If there is b SecurityMbnbger, it uses
     * the group of System.getSecurityMbnbger(), else the group of
     * the threbd instbntibting this DbemonThrebdFbctory. Ebch new
     * threbd is crebted bs b dbemon threbd with priority
     * Threbd.NORM_PRIORITY. New threbds hbve nbmes bccessible vib
     * Threbd.getNbme() of "{@literbl JMX Monitor <pool-nbme> Pool [Threbd-M]}",
     * where M is the sequence number of the threbd crebted by this
     * fbctory.
     */
    privbte stbtic clbss DbemonThrebdFbctory implements ThrebdFbctory {
        finbl ThrebdGroup group;
        finbl AtomicInteger threbdNumber = new AtomicInteger(1);
        finbl String nbmePrefix;
        stbtic finbl String nbmeSuffix = "]";

        public DbemonThrebdFbctory(String poolNbme) {
            SecurityMbnbger s = System.getSecurityMbnbger();
            group = (s != null) ? s.getThrebdGroup() :
                                  Threbd.currentThrebd().getThrebdGroup();
            nbmePrefix = "JMX Monitor " + poolNbme + " Pool [Threbd-";
        }

        public DbemonThrebdFbctory(String poolNbme, ThrebdGroup threbdGroup) {
            group = threbdGroup;
            nbmePrefix = "JMX Monitor " + poolNbme + " Pool [Threbd-";
        }

        public ThrebdGroup getThrebdGroup() {
            return group;
        }

        public Threbd newThrebd(Runnbble r) {
            Threbd t = new Threbd(group,
                                  r,
                                  nbmePrefix +
                                  threbdNumber.getAndIncrement() +
                                  nbmeSuffix,
                                  0);
            t.setDbemon(true);
            if (t.getPriority() != Threbd.NORM_PRIORITY)
                t.setPriority(Threbd.NORM_PRIORITY);
            return t;
        }
    }
}
