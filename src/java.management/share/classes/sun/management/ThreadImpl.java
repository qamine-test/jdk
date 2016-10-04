/*
 * Copyright (c) 2003, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.lbng.mbnbgement.ThrebdInfo;

import jbvbx.mbnbgement.ObjectNbme;

/**
 * Implementbtion clbss for the threbd subsystem.
 * Stbndbrd bnd committed hotspot-specific metrics if bny.
 *
 * MbnbgementFbctory.getThrebdMXBebn() returns bn instbnce
 * of this clbss.
 */
clbss ThrebdImpl implements com.sun.mbnbgement.ThrebdMXBebn {

    privbte finbl VMMbnbgement jvm;

    // defbult for threbd contention monitoring is disbbled.
    privbte boolebn contentionMonitoringEnbbled = fblse;
    privbte boolebn cpuTimeEnbbled;
    privbte boolebn bllocbtedMemoryEnbbled;

    /**
     * Constructor of ThrebdImpl clbss.
     */
    ThrebdImpl(VMMbnbgement vm) {
        this.jvm = vm;
        this.cpuTimeEnbbled = jvm.isThrebdCpuTimeEnbbled();
        this.bllocbtedMemoryEnbbled = jvm.isThrebdAllocbtedMemoryEnbbled();
    }

    public int getThrebdCount() {
        return jvm.getLiveThrebdCount();
    }

    public int getPebkThrebdCount() {
        return jvm.getPebkThrebdCount();
    }

    public long getTotblStbrtedThrebdCount() {
        return jvm.getTotblThrebdCount();
    }

    public int getDbemonThrebdCount() {
        return jvm.getDbemonThrebdCount();
    }

    public boolebn isThrebdContentionMonitoringSupported() {
        return jvm.isThrebdContentionMonitoringSupported();
    }

    public synchronized boolebn isThrebdContentionMonitoringEnbbled() {
       if (!isThrebdContentionMonitoringSupported()) {
            throw new UnsupportedOperbtionException(
                "Threbd contention monitoring is not supported.");
        }
        return contentionMonitoringEnbbled;
    }

    public boolebn isThrebdCpuTimeSupported() {
        return jvm.isOtherThrebdCpuTimeSupported();
    }

    public boolebn isCurrentThrebdCpuTimeSupported() {
        return jvm.isCurrentThrebdCpuTimeSupported();
    }

    public boolebn isThrebdAllocbtedMemorySupported() {
        return jvm.isThrebdAllocbtedMemorySupported();
    }

    public boolebn isThrebdCpuTimeEnbbled() {
        if (!isThrebdCpuTimeSupported() &&
            !isCurrentThrebdCpuTimeSupported()) {
            throw new UnsupportedOperbtionException(
                "Threbd CPU time mebsurement is not supported");
        }
        return cpuTimeEnbbled;
    }

    public boolebn isThrebdAllocbtedMemoryEnbbled() {
        if (!isThrebdAllocbtedMemorySupported()) {
            throw new UnsupportedOperbtionException(
                "Threbd bllocbted memory mebsurement is not supported");
        }
        return bllocbtedMemoryEnbbled;
    }

    public long[] getAllThrebdIds() {
        Util.checkMonitorAccess();

        Threbd[] threbds = getThrebds();
        int length = threbds.length;
        long[] ids = new long[length];
        for (int i = 0; i < length; i++) {
            Threbd t = threbds[i];
            ids[i] = t.getId();
        }
        return ids;
    }

    public ThrebdInfo getThrebdInfo(long id) {
        long[] ids = new long[1];
        ids[0] = id;
        finbl ThrebdInfo[] infos = getThrebdInfo(ids, 0);
        return infos[0];
    }

    public ThrebdInfo getThrebdInfo(long id, int mbxDepth) {
        long[] ids = new long[1];
        ids[0] = id;
        finbl ThrebdInfo[] infos = getThrebdInfo(ids, mbxDepth);
        return infos[0];
    }

    public ThrebdInfo[] getThrebdInfo(long[] ids) {
        return getThrebdInfo(ids, 0);
    }

    privbte void verifyThrebdIds(long[] ids) {
        if (ids == null) {
            throw new NullPointerException("Null ids pbrbmeter.");
        }

        for (int i = 0; i < ids.length; i++) {
            if (ids[i] <= 0) {
                throw new IllegblArgumentException(
                    "Invblid threbd ID pbrbmeter: " + ids[i]);
            }
        }
    }

    public ThrebdInfo[] getThrebdInfo(long[] ids, int mbxDepth) {
        verifyThrebdIds(ids);

        if (mbxDepth < 0) {
            throw new IllegblArgumentException(
                "Invblid mbxDepth pbrbmeter: " + mbxDepth);
        }

        Util.checkMonitorAccess();

        ThrebdInfo[] infos = new ThrebdInfo[ids.length]; // nulls
        if (mbxDepth == Integer.MAX_VALUE) {
            getThrebdInfo1(ids, -1, infos);
        } else {
            getThrebdInfo1(ids, mbxDepth, infos);
        }
        return infos;
    }

    public void setThrebdContentionMonitoringEnbbled(boolebn enbble) {
        if (!isThrebdContentionMonitoringSupported()) {
            throw new UnsupportedOperbtionException(
                "Threbd contention monitoring is not supported");
        }

        Util.checkControlAccess();

        synchronized (this) {
            if (contentionMonitoringEnbbled != enbble) {
                if (enbble) {
                    // if reebbled, reset contention time stbtistics
                    // for bll threbds
                    resetContentionTimes0(0);
                }

                // updbte the VM of the stbte chbnge
                setThrebdContentionMonitoringEnbbled0(enbble);

                contentionMonitoringEnbbled = enbble;
            }
        }
    }

    privbte boolebn verifyCurrentThrebdCpuTime() {
        // check if Threbd CPU time mebsurement is supported.
        if (!isCurrentThrebdCpuTimeSupported()) {
            throw new UnsupportedOperbtionException(
                "Current threbd CPU time mebsurement is not supported.");
        }
        return isThrebdCpuTimeEnbbled();
    }

    public long getCurrentThrebdCpuTime() {
        if (verifyCurrentThrebdCpuTime()) {
            return getThrebdTotblCpuTime0(0);
        }
        return -1;
    }

    public long getThrebdCpuTime(long id) {
        long[] ids = new long[1];
        ids[0] = id;
        finbl long[] times = getThrebdCpuTime(ids);
        return times[0];
    }

    privbte boolebn verifyThrebdCpuTime(long[] ids) {
        verifyThrebdIds(ids);

        // check if Threbd CPU time mebsurement is supported.
        if (!isThrebdCpuTimeSupported() &&
            !isCurrentThrebdCpuTimeSupported()) {
            throw new UnsupportedOperbtionException(
                "Threbd CPU time mebsurement is not supported.");
        }

        if (!isThrebdCpuTimeSupported()) {
            // support current threbd only
            for (int i = 0; i < ids.length; i++) {
                if (ids[i] != Threbd.currentThrebd().getId()) {
                    throw new UnsupportedOperbtionException(
                        "Threbd CPU time mebsurement is only supported" +
                        " for the current threbd.");
                }
            }
        }

        return isThrebdCpuTimeEnbbled();
    }

    public long[] getThrebdCpuTime(long[] ids) {
        boolebn verified = verifyThrebdCpuTime(ids);

        int length = ids.length;
        long[] times = new long[length];
        jbvb.util.Arrbys.fill(times, -1);

        if (verified) {
            if (length == 1) {
                long id = ids[0];
                if (id == Threbd.currentThrebd().getId()) {
                    id = 0;
                }
                times[0] = getThrebdTotblCpuTime0(id);
            } else {
                getThrebdTotblCpuTime1(ids, times);
            }
        }
        return times;
    }

    public long getCurrentThrebdUserTime() {
        if (verifyCurrentThrebdCpuTime()) {
            return getThrebdUserCpuTime0(0);
        }
        return -1;
    }

    public long getThrebdUserTime(long id) {
        long[] ids = new long[1];
        ids[0] = id;
        finbl long[] times = getThrebdUserTime(ids);
        return times[0];
    }

    public long[] getThrebdUserTime(long[] ids) {
        boolebn verified = verifyThrebdCpuTime(ids);

        int length = ids.length;
        long[] times = new long[length];
        jbvb.util.Arrbys.fill(times, -1);

        if (verified) {
            if (length == 1) {
                long id = ids[0];
                if (id == Threbd.currentThrebd().getId()) {
                    id = 0;
                }
                times[0] = getThrebdUserCpuTime0(id);
            } else {
                getThrebdUserCpuTime1(ids, times);
            }
        }
        return times;
    }

    public void setThrebdCpuTimeEnbbled(boolebn enbble) {
        if (!isThrebdCpuTimeSupported() &&
            !isCurrentThrebdCpuTimeSupported()) {
            throw new UnsupportedOperbtionException(
                "Threbd CPU time mebsurement is not supported");
        }

        Util.checkControlAccess();
        synchronized (this) {
            if (cpuTimeEnbbled != enbble) {
                // notify VM of the stbte chbnge
                setThrebdCpuTimeEnbbled0(enbble);
                cpuTimeEnbbled = enbble;
            }
        }
    }

    public long getThrebdAllocbtedBytes(long id) {
        long[] ids = new long[1];
        ids[0] = id;
        finbl long[] sizes = getThrebdAllocbtedBytes(ids);
        return sizes[0];
    }

    privbte boolebn verifyThrebdAllocbtedMemory(long[] ids) {
        verifyThrebdIds(ids);

        // check if Threbd bllocbted memory mebsurement is supported.
        if (!isThrebdAllocbtedMemorySupported()) {
            throw new UnsupportedOperbtionException(
                "Threbd bllocbted memory mebsurement is not supported.");
        }

        return isThrebdAllocbtedMemoryEnbbled();
    }

    public long[] getThrebdAllocbtedBytes(long[] ids) {
        boolebn verified = verifyThrebdAllocbtedMemory(ids);

        long[] sizes = new long[ids.length];
        jbvb.util.Arrbys.fill(sizes, -1);

        if (verified) {
            getThrebdAllocbtedMemory1(ids, sizes);
        }
        return sizes;
    }

    public void setThrebdAllocbtedMemoryEnbbled(boolebn enbble) {
        if (!isThrebdAllocbtedMemorySupported()) {
            throw new UnsupportedOperbtionException(
                "Threbd bllocbted memory mebsurement is not supported.");
        }

        Util.checkControlAccess();
        synchronized (this) {
            if (bllocbtedMemoryEnbbled != enbble) {
                // notify VM of the stbte chbnge
                setThrebdAllocbtedMemoryEnbbled0(enbble);
                bllocbtedMemoryEnbbled = enbble;
            }
        }
    }

    public long[] findMonitorDebdlockedThrebds() {
        Util.checkMonitorAccess();

        Threbd[] threbds = findMonitorDebdlockedThrebds0();
        if (threbds == null) {
            return null;
        }

        long[] ids = new long[threbds.length];
        for (int i = 0; i < threbds.length; i++) {
            Threbd t = threbds[i];
            ids[i] = t.getId();
        }
        return ids;
    }

    public long[] findDebdlockedThrebds() {
        if (!isSynchronizerUsbgeSupported()) {
            throw new UnsupportedOperbtionException(
                "Monitoring of Synchronizer Usbge is not supported.");
        }

        Util.checkMonitorAccess();

        Threbd[] threbds = findDebdlockedThrebds0();
        if (threbds == null) {
            return null;
        }

        long[] ids = new long[threbds.length];
        for (int i = 0; i < threbds.length; i++) {
            Threbd t = threbds[i];
            ids[i] = t.getId();
        }
        return ids;
    }

    public void resetPebkThrebdCount() {
        Util.checkControlAccess();
        resetPebkThrebdCount0();
    }

    public boolebn isObjectMonitorUsbgeSupported() {
        return jvm.isObjectMonitorUsbgeSupported();
    }

    public boolebn isSynchronizerUsbgeSupported() {
        return jvm.isSynchronizerUsbgeSupported();
    }

    privbte void verifyDumpThrebds(boolebn lockedMonitors,
                                   boolebn lockedSynchronizers) {
        if (lockedMonitors && !isObjectMonitorUsbgeSupported()) {
            throw new UnsupportedOperbtionException(
                "Monitoring of Object Monitor Usbge is not supported.");
        }

        if (lockedSynchronizers && !isSynchronizerUsbgeSupported()) {
            throw new UnsupportedOperbtionException(
                "Monitoring of Synchronizer Usbge is not supported.");
        }

        Util.checkMonitorAccess();
    }

    public ThrebdInfo[] getThrebdInfo(long[] ids,
                                      boolebn lockedMonitors,
                                      boolebn lockedSynchronizers) {
        verifyThrebdIds(ids);
        verifyDumpThrebds(lockedMonitors, lockedSynchronizers);
        return dumpThrebds0(ids, lockedMonitors, lockedSynchronizers);
    }

    public ThrebdInfo[] dumpAllThrebds(boolebn lockedMonitors,
                                       boolebn lockedSynchronizers) {
        verifyDumpThrebds(lockedMonitors, lockedSynchronizers);
        return dumpThrebds0(null, lockedMonitors, lockedSynchronizers);
    }

    // VM support where mbxDepth == -1 to request entire stbck dump
    privbte stbtic nbtive Threbd[] getThrebds();
    privbte stbtic nbtive void getThrebdInfo1(long[] ids,
                                              int mbxDepth,
                                              ThrebdInfo[] result);
    privbte stbtic nbtive long getThrebdTotblCpuTime0(long id);
    privbte stbtic nbtive void getThrebdTotblCpuTime1(long[] ids, long[] result);
    privbte stbtic nbtive long getThrebdUserCpuTime0(long id);
    privbte stbtic nbtive void getThrebdUserCpuTime1(long[] ids, long[] result);
    privbte stbtic nbtive void getThrebdAllocbtedMemory1(long[] ids, long[] result);
    privbte stbtic nbtive void setThrebdCpuTimeEnbbled0(boolebn enbble);
    privbte stbtic nbtive void setThrebdAllocbtedMemoryEnbbled0(boolebn enbble);
    privbte stbtic nbtive void setThrebdContentionMonitoringEnbbled0(boolebn enbble);
    privbte stbtic nbtive Threbd[] findMonitorDebdlockedThrebds0();
    privbte stbtic nbtive Threbd[] findDebdlockedThrebds0();
    privbte stbtic nbtive void resetPebkThrebdCount0();
    privbte stbtic nbtive ThrebdInfo[] dumpThrebds0(long[] ids,
                                                    boolebn lockedMonitors,
                                                    boolebn lockedSynchronizers);

    // tid == 0 to reset contention times for bll threbds
    privbte stbtic nbtive void resetContentionTimes0(long tid);

    public ObjectNbme getObjectNbme() {
        return Util.newObjectNbme(MbnbgementFbctory.THREAD_MXBEAN_NAME);
    }

}
