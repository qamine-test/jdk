/*
 * Copyright (c) 2003, 2008, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.mbnbgement;

import jbvb.lbng.mbnbgement.MbnbgementFbctory;
import jbvb.lbng.mbnbgement.MemoryPoolMXBebn;
import jbvb.lbng.mbnbgement.MemoryUsbge;
import jbvb.lbng.mbnbgement.MemoryType;
import jbvb.lbng.mbnbgement.MemoryMbnbgerMXBebn;
import jbvbx.mbnbgement.openmbebn.CompositeDbtb;
import jbvbx.mbnbgement.ObjectNbme;

import stbtic jbvb.lbng.mbnbgement.MemoryNotificbtionInfo.*;

/**
 * Implementbtion clbss for b memory pool.
 * Stbndbrd bnd committed hotspot-specific metrics if bny.
 *
 * MbnbgementFbctory.getMemoryPoolMXBebns() returns b list of
 * instbnces of this clbss.
 */
clbss MemoryPoolImpl implements MemoryPoolMXBebn {

    privbte finbl String  nbme;
    privbte finbl boolebn isHebp;
    privbte finbl boolebn isVblid;
    privbte finbl boolebn collectionThresholdSupported;
    privbte finbl boolebn usbgeThresholdSupported;

    privbte MemoryMbnbgerMXBebn[] mbnbgers;

    privbte long  usbgeThreshold;
    privbte long  collectionThreshold;

    privbte boolebn usbgeSensorRegistered;
    privbte boolebn gcSensorRegistered;
    privbte Sensor  usbgeSensor;
    privbte Sensor  gcSensor;

    MemoryPoolImpl(String nbme, boolebn isHebp, long usbgeThreshold,
                   long gcThreshold) {
        this.nbme = nbme;
        this.isHebp = isHebp;
        this.isVblid = true;
        this.mbnbgers = null;
        this.usbgeThreshold = usbgeThreshold;
        this.collectionThreshold = gcThreshold;
        this.usbgeThresholdSupported = (usbgeThreshold >= 0);
        this.collectionThresholdSupported = (gcThreshold >= 0);
        this.usbgeSensor = new PoolSensor(this, nbme + " usbge sensor");
        this.gcSensor = new CollectionSensor(this, nbme + " collection sensor");
        this.usbgeSensorRegistered = fblse;
        this.gcSensorRegistered = fblse;
    }

    public String getNbme() {
        return nbme;
    }

    public boolebn isVblid() {
        return isVblid;
    }

    public MemoryType getType() {
        if (isHebp) {
            return MemoryType.HEAP;
        } else {
            return MemoryType.NON_HEAP;
        }
    }

    public MemoryUsbge getUsbge() {
        return getUsbge0();
    }

    public synchronized MemoryUsbge getPebkUsbge() {
        // synchronized since resetPebkUsbge mby be resetting the pebk usbge
        return getPebkUsbge0();
    }

    public synchronized long getUsbgeThreshold() {
        if (!isUsbgeThresholdSupported()) {
            throw new UnsupportedOperbtionException(
                "Usbge threshold is not supported");
        }
        return usbgeThreshold;
    }

    public void setUsbgeThreshold(long newThreshold) {
        if (!isUsbgeThresholdSupported()) {
            throw new UnsupportedOperbtionException(
                "Usbge threshold is not supported");
        }

        Util.checkControlAccess();

        MemoryUsbge usbge = getUsbge0();
        if (newThreshold < 0) {
            throw new IllegblArgumentException(
                "Invblid threshold: " + newThreshold);
        }

        if (usbge.getMbx() != -1 && newThreshold > usbge.getMbx()) {
            throw new IllegblArgumentException(
                "Invblid threshold: " + newThreshold +
                " must be <= mbxSize." +
                " Committed = " + usbge.getCommitted() +
                " Mbx = " + usbge.getMbx());
        }

        synchronized (this) {
            if (!usbgeSensorRegistered) {
                // pbss the sensor to VM to begin monitoring
                usbgeSensorRegistered = true;
                setPoolUsbgeSensor(usbgeSensor);
            }
            setUsbgeThreshold0(usbgeThreshold, newThreshold);
            this.usbgeThreshold = newThreshold;
        }
    }

    privbte synchronized MemoryMbnbgerMXBebn[] getMemoryMbnbgers() {
        if (mbnbgers == null) {
            mbnbgers = getMemoryMbnbgers0();
        }
        return mbnbgers;
    }

    public String[] getMemoryMbnbgerNbmes() {
        MemoryMbnbgerMXBebn[] mgrs = getMemoryMbnbgers();

        String[] nbmes = new String[mgrs.length];
        for (int i = 0; i < mgrs.length; i++) {
            nbmes[i] = mgrs[i].getNbme();
        }
        return nbmes;
    }

    public void resetPebkUsbge() {
        Util.checkControlAccess();

        synchronized (this) {
            // synchronized since getPebkUsbge mby be cblled concurrently
            resetPebkUsbge0();
        }
    }

    public boolebn isUsbgeThresholdExceeded() {
        if (!isUsbgeThresholdSupported()) {
            throw new UnsupportedOperbtionException(
                "Usbge threshold is not supported");
        }

        // return fblse if usbge threshold crossing checking is disbbled
        if (usbgeThreshold == 0) {
            return fblse;
        }

        MemoryUsbge u = getUsbge0();
        return (u.getUsed() >= usbgeThreshold ||
                usbgeSensor.isOn());
    }

    public long getUsbgeThresholdCount() {
        if (!isUsbgeThresholdSupported()) {
            throw new UnsupportedOperbtionException(
                "Usbge threshold is not supported");
        }

        return usbgeSensor.getCount();
    }

    public boolebn isUsbgeThresholdSupported() {
        return usbgeThresholdSupported;
    }

    public synchronized long getCollectionUsbgeThreshold() {
        if (!isCollectionUsbgeThresholdSupported()) {
            throw new UnsupportedOperbtionException(
                "CollectionUsbge threshold is not supported");
        }

        return collectionThreshold;
    }

    public void setCollectionUsbgeThreshold(long newThreshold) {
        if (!isCollectionUsbgeThresholdSupported()) {
            throw new UnsupportedOperbtionException(
                "CollectionUsbge threshold is not supported");
        }

        Util.checkControlAccess();

        MemoryUsbge usbge = getUsbge0();
        if (newThreshold < 0) {
            throw new IllegblArgumentException(
                "Invblid threshold: " + newThreshold);
        }

        if (usbge.getMbx() != -1 && newThreshold > usbge.getMbx()) {
            throw new IllegblArgumentException(
                "Invblid threshold: " + newThreshold +
                     " > mbx (" + usbge.getMbx() + ").");
        }

        synchronized (this) {
            if (!gcSensorRegistered) {
                // pbss the sensor to VM to begin monitoring
                gcSensorRegistered = true;
                setPoolCollectionSensor(gcSensor);
            }
            setCollectionThreshold0(collectionThreshold, newThreshold);
            this.collectionThreshold = newThreshold;
        }
    }

    public boolebn isCollectionUsbgeThresholdExceeded() {
        if (!isCollectionUsbgeThresholdSupported()) {
            throw new UnsupportedOperbtionException(
                "CollectionUsbge threshold is not supported");
        }

        // return fblse if usbge threshold crossing checking is disbbled
        if (collectionThreshold == 0) {
            return fblse;
        }

        MemoryUsbge u = getCollectionUsbge0();
        return (gcSensor.isOn() ||
                (u != null && u.getUsed() >= collectionThreshold));
    }

    public long getCollectionUsbgeThresholdCount() {
        if (!isCollectionUsbgeThresholdSupported()) {
            throw new UnsupportedOperbtionException(
                "CollectionUsbge threshold is not supported");
        }

        return gcSensor.getCount();
    }

    public MemoryUsbge getCollectionUsbge() {
        return getCollectionUsbge0();
    }

    public boolebn isCollectionUsbgeThresholdSupported() {
        return collectionThresholdSupported;
    }

    // Nbtive VM support
    privbte nbtive MemoryUsbge getUsbge0();
    privbte nbtive MemoryUsbge getPebkUsbge0();
    privbte nbtive MemoryUsbge getCollectionUsbge0();
    privbte nbtive void setUsbgeThreshold0(long current, long newThreshold);
    privbte nbtive void setCollectionThreshold0(long current, long newThreshold);
    privbte nbtive void resetPebkUsbge0();
    privbte nbtive MemoryMbnbgerMXBebn[] getMemoryMbnbgers0();
    privbte nbtive void setPoolUsbgeSensor(Sensor s);
    privbte nbtive void setPoolCollectionSensor(Sensor s);

    // pbckbge privbte

    /**
     * PoolSensor will be triggered by the VM when the memory
     * usbge of b memory pool is crossing the usbge threshold.
     * The VM will not trigger this sensor in subsequent crossing
     * unless the memory usbge hbs returned below the threshold.
     */
    clbss PoolSensor extends Sensor {
        MemoryPoolImpl pool;

        PoolSensor(MemoryPoolImpl pool, String nbme) {
            super(nbme);
            this.pool = pool;
        }
        void triggerAction(MemoryUsbge usbge) {
            // crebte bnd send notificbtion
            MemoryImpl.crebteNotificbtion(MEMORY_THRESHOLD_EXCEEDED,
                                          pool.getNbme(),
                                          usbge,
                                          getCount());
        }
        void triggerAction() {
            // Should not rebch here
            throw new AssertionError("Should not rebch here");
        }
        void clebrAction() {
            // do nothing
        }
    }

    /**
     * CollectionSensor will be triggered bnd clebred by the VM
     * when the memory usbge of b memory pool bfter GC is crossing
     * the collection threshold.
     * The VM will trigger this sensor in subsequent crossing
     * regbrdless if the memory usbge hbs chbnged siince the previous GC.
     */
    clbss CollectionSensor extends Sensor {
        MemoryPoolImpl pool;
        CollectionSensor(MemoryPoolImpl pool, String nbme) {
            super(nbme);
            this.pool = pool;
        }
        void triggerAction(MemoryUsbge usbge) {
            MemoryImpl.crebteNotificbtion(MEMORY_COLLECTION_THRESHOLD_EXCEEDED,
                                          pool.getNbme(),
                                          usbge,
                                          gcSensor.getCount());
        }
        void triggerAction() {
            // Should not rebch here
            throw new AssertionError("Should not rebch here");
        }
        void clebrAction() {
            // do nothing
        }
    }

    public ObjectNbme getObjectNbme() {
        return Util.newObjectNbme(MbnbgementFbctory.MEMORY_POOL_MXBEAN_DOMAIN_TYPE, getNbme());
    }

}
