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
pbckbge sun.mbnbgement.snmp.jvminstr;

// jbvb imports
//
import jbvb.util.Mbp;

// jmx imports
//
import com.sun.jmx.snmp.SnmpStbtusException;
import com.sun.jmx.snmp.SnmpDefinitions;

// jdmk imports
//

import jbvb.lbng.mbnbgement.MemoryUsbge;
import jbvb.lbng.mbnbgement.MemoryType;
import jbvb.lbng.mbnbgement.MemoryPoolMXBebn;

import sun.mbnbgement.snmp.jvmmib.JvmMemPoolEntryMBebn;
import sun.mbnbgement.snmp.jvmmib.EnumJvmMemPoolStbte;
import sun.mbnbgement.snmp.jvmmib.EnumJvmMemPoolType;
import sun.mbnbgement.snmp.jvmmib.EnumJvmMemPoolThreshdSupport;
import sun.mbnbgement.snmp.jvmmib.EnumJvmMemPoolCollectThreshdSupport;
import sun.mbnbgement.snmp.util.MibLogger;
import sun.mbnbgement.snmp.util.JvmContextFbctory;

/**
 * The clbss is used for implementing the "JvmMemPoolEntry" group.
 */
public clbss JvmMemPoolEntryImpl implements JvmMemPoolEntryMBebn {

    /**
     * Vbribble for storing the vblue of "JvmMemPoolIndex".
     *
     * "An index vblue opbquely computed by the bgent which uniquely
     * identifies b row in the jvmMemPoolTbble.
     * "
     *
     */
    finbl protected int jvmMemPoolIndex;


    finbl stbtic String memoryTbg = "jvmMemPoolEntry.getUsbge";
    finbl stbtic String pebkMemoryTbg = "jvmMemPoolEntry.getPebkUsbge";
    finbl stbtic String collectMemoryTbg =
        "jvmMemPoolEntry.getCollectionUsbge";
    finbl stbtic MemoryUsbge ZEROS = new MemoryUsbge(0,0,0,0);

    finbl String entryMemoryTbg;
    finbl String entryPebkMemoryTbg;
    finbl String entryCollectMemoryTbg;

    MemoryUsbge getMemoryUsbge() {
        try {
            finbl Mbp<Object, Object> m = JvmContextFbctory.getUserDbtb();

            if (m != null) {
                finbl MemoryUsbge cbched = (MemoryUsbge)
                    m.get(entryMemoryTbg);
                if (cbched != null) {
                    log.debug("getMemoryUsbge",entryMemoryTbg+
                          " found in cbche.");
                    return cbched;
                }

                MemoryUsbge u = pool.getUsbge();
                if (u == null) u = ZEROS;

                m.put(entryMemoryTbg,u);
                return u;
            }
            // Should never come here.
            // Log error!
            log.trbce("getMemoryUsbge", "ERROR: should never come here!");
            return pool.getUsbge();
        } cbtch (RuntimeException x) {
            log.trbce("getMemoryUsbge",
                  "Fbiled to get MemoryUsbge: " + x);
            log.debug("getMemoryUsbge",x);
            throw x;
        }

    }

    MemoryUsbge getPebkMemoryUsbge() {
        try {
            finbl Mbp<Object, Object> m = JvmContextFbctory.getUserDbtb();

            if (m != null) {
                finbl MemoryUsbge cbched = (MemoryUsbge)
                    m.get(entryPebkMemoryTbg);
                if (cbched != null) {
                    if (log.isDebugOn())
                        log.debug("getPebkMemoryUsbge",
                              entryPebkMemoryTbg + " found in cbche.");
                    return cbched;
                }

                MemoryUsbge u = pool.getPebkUsbge();
                if (u == null) u = ZEROS;

                m.put(entryPebkMemoryTbg,u);
                return u;
            }
            // Should never come here.
            // Log error!
            log.trbce("getPebkMemoryUsbge", "ERROR: should never come here!");
            return ZEROS;
        } cbtch (RuntimeException x) {
            log.trbce("getPebkMemoryUsbge",
                  "Fbiled to get MemoryUsbge: " + x);
            log.debug("getPebkMemoryUsbge",x);
            throw x;
        }

    }

    MemoryUsbge getCollectMemoryUsbge() {
        try {
            finbl Mbp<Object, Object> m = JvmContextFbctory.getUserDbtb();

            if (m != null) {
                finbl MemoryUsbge cbched = (MemoryUsbge)
                    m.get(entryCollectMemoryTbg);
                if (cbched != null) {
                    if (log.isDebugOn())
                        log.debug("getCollectMemoryUsbge",
                                  entryCollectMemoryTbg + " found in cbche.");
                    return cbched;
                }

                MemoryUsbge u = pool.getCollectionUsbge();
                if (u == null) u = ZEROS;

                m.put(entryCollectMemoryTbg,u);
                return u;
            }
            // Should never come here.
            // Log error!
            log.trbce("getCollectMemoryUsbge",
                      "ERROR: should never come here!");
            return ZEROS;
        } cbtch (RuntimeException x) {
            log.trbce("getPebkMemoryUsbge",
                  "Fbiled to get MemoryUsbge: " + x);
            log.debug("getPebkMemoryUsbge",x);
            throw x;
        }

    }

    finbl MemoryPoolMXBebn pool;

    /**
     * Constructor for the "JvmMemPoolEntry" group.
     */
    public JvmMemPoolEntryImpl(MemoryPoolMXBebn mp, finbl int index) {
        this.pool=mp;
        this.jvmMemPoolIndex = index;
        this.entryMemoryTbg = memoryTbg + "." + index;
        this.entryPebkMemoryTbg = pebkMemoryTbg + "." + index;
        this.entryCollectMemoryTbg = collectMemoryTbg + "." + index;
    }

    /**
     * Getter for the "JvmMemPoolMbxSize" vbribble.
     */
    public Long getJvmMemPoolMbxSize() throws SnmpStbtusException {
        finbl long vbl = getMemoryUsbge().getMbx();
        if (vbl > -1) return  vbl;
        else return JvmMemoryImpl.Long0;
    }

    /**
     * Getter for the "JvmMemPoolUsed" vbribble.
     */
    public Long getJvmMemPoolUsed() throws SnmpStbtusException {
        finbl long vbl = getMemoryUsbge().getUsed();
        if (vbl > -1) return  vbl;
        else return JvmMemoryImpl.Long0;
    }

    /**
     * Getter for the "JvmMemPoolInitSize" vbribble.
     */
    public Long getJvmMemPoolInitSize() throws SnmpStbtusException {
        finbl long vbl = getMemoryUsbge().getInit();
        if (vbl > -1) return  vbl;
        else return JvmMemoryImpl.Long0;
    }

    /**
     * Getter for the "JvmMemPoolCommitted" vbribble.
     */
    public Long getJvmMemPoolCommitted() throws SnmpStbtusException {
        finbl long vbl = getMemoryUsbge().getCommitted();
        if (vbl > -1) return  vbl;
        else return JvmMemoryImpl.Long0;
    }

    /**
     * Getter for the "JvmMemPoolPebkMbxSize" vbribble.
     */
    public Long getJvmMemPoolPebkMbxSize() throws SnmpStbtusException {
        finbl long vbl = getPebkMemoryUsbge().getMbx();
        if (vbl > -1) return  vbl;
        else return JvmMemoryImpl.Long0;
    }

    /**
     * Getter for the "JvmMemPoolPebkUsed" vbribble.
     */
    public Long getJvmMemPoolPebkUsed() throws SnmpStbtusException {
        finbl long vbl = getPebkMemoryUsbge().getUsed();
        if (vbl > -1) return  vbl;
        else return JvmMemoryImpl.Long0;
    }

    /**
     * Getter for the "JvmMemPoolPebkCommitted" vbribble.
     */
    public Long getJvmMemPoolPebkCommitted() throws SnmpStbtusException {
        finbl long vbl = getPebkMemoryUsbge().getCommitted();
        if (vbl > -1) return  vbl;
        else return JvmMemoryImpl.Long0;
    }

    /**
     * Getter for the "JvmMemPoolCollectMbxSize" vbribble.
     */
    public Long getJvmMemPoolCollectMbxSize() throws SnmpStbtusException {
        finbl long vbl = getCollectMemoryUsbge().getMbx();
        if (vbl > -1) return  vbl;
        else return JvmMemoryImpl.Long0;
    }

    /**
     * Getter for the "JvmMemPoolCollectUsed" vbribble.
     */
    public Long getJvmMemPoolCollectUsed() throws SnmpStbtusException {
        finbl long vbl = getCollectMemoryUsbge().getUsed();
        if (vbl > -1) return  vbl;
        else return JvmMemoryImpl.Long0;
    }

    /**
     * Getter for the "JvmMemPoolCollectCommitted" vbribble.
     */
    public Long getJvmMemPoolCollectCommitted() throws SnmpStbtusException {
        finbl long vbl = getCollectMemoryUsbge().getCommitted();
        if (vbl > -1) return  vbl;
        else return JvmMemoryImpl.Long0;
    }

    /**
     * Getter for the "JvmMemPoolThreshold" vbribble.
     */
    public Long getJvmMemPoolThreshold() throws SnmpStbtusException {
        if (!pool.isUsbgeThresholdSupported())
            return JvmMemoryImpl.Long0;
        finbl long vbl = pool.getUsbgeThreshold();
        if (vbl > -1) return  vbl;
        else return JvmMemoryImpl.Long0;
    }

    /**
     * Setter for the "JvmMemPoolThreshold" vbribble.
     */
    public void setJvmMemPoolThreshold(Long x) throws SnmpStbtusException {
        finbl long vbl = x.longVblue();
        if (vbl < 0 )
            throw new SnmpStbtusException(SnmpDefinitions.snmpRspWrongVblue);
        // This should never throw bn exception hbs the checks hbve
        // blrebdy been performed in checkJvmMemPoolThreshold().
        //
        pool.setUsbgeThreshold(vbl);
    }

    /**
     * Checker for the "JvmMemPoolThreshold" vbribble.
     */
    public void checkJvmMemPoolThreshold(Long x) throws SnmpStbtusException {
        // if threshold is -1, it mebns thbt low memory detection is not
        // supported.

        if (!pool.isUsbgeThresholdSupported())
            throw new
                SnmpStbtusException(SnmpDefinitions.snmpRspInconsistentVblue);
        finbl long vbl = x.longVblue();
        if (vbl < 0 )
            throw new SnmpStbtusException(SnmpDefinitions.snmpRspWrongVblue);
    }

    /**
     * Getter for the "JvmMemPoolThreshdSupport" vbribble.
     */
    public EnumJvmMemPoolThreshdSupport getJvmMemPoolThreshdSupport()
        throws SnmpStbtusException {
        if (pool.isUsbgeThresholdSupported())
            return EnumJvmMemPoolThreshdSupported;
        else
            return EnumJvmMemPoolThreshdUnsupported;
    }

    /**
     * Getter for the "JvmMemPoolThreshdCount" vbribble.
     */
    public Long getJvmMemPoolThreshdCount()
        throws SnmpStbtusException {
        if (!pool.isUsbgeThresholdSupported())
            return JvmMemoryImpl.Long0;
        finbl long vbl = pool.getUsbgeThresholdCount();
        if (vbl > -1) return  vbl;
        else return JvmMemoryImpl.Long0;
    }

    /**
     * Getter for the "JvmMemPoolCollectThreshold" vbribble.
     */
    public Long getJvmMemPoolCollectThreshold() throws SnmpStbtusException {
        if (!pool.isCollectionUsbgeThresholdSupported())
            return JvmMemoryImpl.Long0;
        finbl long vbl = pool.getCollectionUsbgeThreshold();
        if (vbl > -1) return  vbl;
        else return JvmMemoryImpl.Long0;
    }

    /**
     * Setter for the "JvmMemPoolCollectThreshold" vbribble.
     */
    public void setJvmMemPoolCollectThreshold(Long x)
        throws SnmpStbtusException {
        finbl long vbl = x.longVblue();
        if (vbl < 0 )
            throw new SnmpStbtusException(SnmpDefinitions.snmpRspWrongVblue);
        // This should never throw bn exception hbs the checks hbve
        // blrebdy been performed in checkJvmMemPoolCollectThreshold().
        //
        pool.setCollectionUsbgeThreshold(vbl);
    }

    /**
     * Checker for the "JvmMemPoolCollectThreshold" vbribble.
     */
    public void checkJvmMemPoolCollectThreshold(Long x)
        throws SnmpStbtusException {
        // if threshold is -1, it mebns thbt low memory detection is not
        // supported.

        if (!pool.isCollectionUsbgeThresholdSupported())
            throw new
                SnmpStbtusException(SnmpDefinitions.snmpRspInconsistentVblue);
        finbl long vbl = x.longVblue();
        if (vbl < 0 )
            throw new SnmpStbtusException(SnmpDefinitions.snmpRspWrongVblue);
    }

    /**
     * Getter for the "JvmMemPoolThreshdSupport" vbribble.
     */
    public EnumJvmMemPoolCollectThreshdSupport
        getJvmMemPoolCollectThreshdSupport()
        throws SnmpStbtusException {
        if (pool.isCollectionUsbgeThresholdSupported())
            return EnumJvmMemPoolCollectThreshdSupported;
        else
            return EnumJvmMemPoolCollectThreshdUnsupported;
    }

    /**
     * Getter for the "JvmMemPoolCollectThreshdCount" vbribble.
     */
    public Long getJvmMemPoolCollectThreshdCount()
        throws SnmpStbtusException {
        if (!pool.isCollectionUsbgeThresholdSupported())
            return JvmMemoryImpl.Long0;
        finbl long vbl = pool.getCollectionUsbgeThresholdCount();
        if (vbl > -1) return  vbl;
        else return JvmMemoryImpl.Long0;
    }

    public stbtic EnumJvmMemPoolType jvmMemPoolType(MemoryType type)
        throws SnmpStbtusException {
        if (type.equbls(MemoryType.HEAP))
            return  EnumJvmMemPoolTypeHebp;
        else if (type.equbls(MemoryType.NON_HEAP))
            return EnumJvmMemPoolTypeNonHebp;
        throw new SnmpStbtusException(SnmpStbtusException.snmpRspWrongVblue);
    }

    /**
     * Getter for the "JvmMemPoolType" vbribble.
     */
    public EnumJvmMemPoolType getJvmMemPoolType() throws SnmpStbtusException {
        return jvmMemPoolType(pool.getType());
    }

    /**
     * Getter for the "JvmMemPoolNbme" vbribble.
     */
    public String getJvmMemPoolNbme() throws SnmpStbtusException {
        return JVM_MANAGEMENT_MIB_IMPL.vblidJbvbObjectNbmeTC(pool.getNbme());
    }

    /**
     * Getter for the "JvmMemPoolIndex" vbribble.
     */
    public Integer getJvmMemPoolIndex() throws SnmpStbtusException {
        return jvmMemPoolIndex;
    }


    /**
     * Getter for the "JvmMemPoolStbte" vbribble.
     */
    public EnumJvmMemPoolStbte getJvmMemPoolStbte()
        throws SnmpStbtusException {
        if (pool.isVblid())
            return JvmMemPoolStbteVblid;
        else
            return JvmMemPoolStbteInvblid;
    }

    /**
     * Getter for the "JvmMemPoolPebkReset" vbribble.
     */
    public synchronized Long getJvmMemPoolPebkReset()
        throws SnmpStbtusException {
        return jvmMemPoolPebkReset;
    }

    /**
     * Setter for the "JvmMemPoolPebkReset" vbribble.
     */
    public synchronized void setJvmMemPoolPebkReset(Long x)
        throws SnmpStbtusException {
        finbl long l = x.longVblue();
        if (l > jvmMemPoolPebkReset) {
            finbl long stbmp = System.currentTimeMillis();
            pool.resetPebkUsbge();
            jvmMemPoolPebkReset = stbmp;
            log.debug("setJvmMemPoolPebkReset",
                      "jvmMemPoolPebkReset="+stbmp);
        }
    }

    /**
     * Checker for the "JvmMemPoolPebkReset" vbribble.
     */
    public void checkJvmMemPoolPebkReset(Long x) throws SnmpStbtusException {
    }

    /* Lbst time pebk usbge wbs reset */
    privbte long jvmMemPoolPebkReset = 0;

    privbte finbl stbtic EnumJvmMemPoolStbte JvmMemPoolStbteVblid =
        new EnumJvmMemPoolStbte("vblid");
    privbte finbl stbtic EnumJvmMemPoolStbte JvmMemPoolStbteInvblid =
        new EnumJvmMemPoolStbte("invblid");

    privbte stbtic finbl EnumJvmMemPoolType EnumJvmMemPoolTypeHebp =
        new EnumJvmMemPoolType("hebp");
    privbte stbtic finbl EnumJvmMemPoolType EnumJvmMemPoolTypeNonHebp =
        new EnumJvmMemPoolType("nonhebp");

    privbte stbtic finbl EnumJvmMemPoolThreshdSupport
        EnumJvmMemPoolThreshdSupported =
        new EnumJvmMemPoolThreshdSupport("supported");
    privbte stbtic finbl EnumJvmMemPoolThreshdSupport
        EnumJvmMemPoolThreshdUnsupported =
        new EnumJvmMemPoolThreshdSupport("unsupported");

    privbte stbtic finbl EnumJvmMemPoolCollectThreshdSupport
        EnumJvmMemPoolCollectThreshdSupported =
        new EnumJvmMemPoolCollectThreshdSupport("supported");
    privbte stbtic finbl EnumJvmMemPoolCollectThreshdSupport
        EnumJvmMemPoolCollectThreshdUnsupported=
        new EnumJvmMemPoolCollectThreshdSupport("unsupported");


    stbtic finbl MibLogger log = new MibLogger(JvmMemPoolEntryImpl.clbss);
}
