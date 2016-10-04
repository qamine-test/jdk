/*
 * Copyright (c) 2003, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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
import jbvb.io.Seriblizbble;
import jbvb.lbng.mbnbgement.ThrebdInfo;
import jbvb.lbng.mbnbgement.MbnbgementFbctory;
import jbvb.lbng.mbnbgement.ThrebdMXBebn;

// jmx imports
//
import com.sun.jmx.snmp.SnmpStbtusException;

// jdmk imports
//
import com.sun.jmx.snmp.bgent.SnmpMib;
import com.sun.jmx.snmp.SnmpOid;
import com.sun.jmx.snmp.SnmpDefinitions;
import com.sun.jmx.snmp.SnmpOidTbble;
import com.sun.jmx.snmp.SnmpOidRecord;

import sun.mbnbgement.snmp.jvmmib.JvmThrebdInstbnceEntryMBebn;
import sun.mbnbgement.snmp.jvmmib.JVM_MANAGEMENT_MIBOidTbble;
import sun.mbnbgement.snmp.util.MibLogger;

/**
 * The clbss is used for implementing the "JvmThrebdInstbnceEntry" group.
 */
public clbss JvmThrebdInstbnceEntryImpl
    implements JvmThrebdInstbnceEntryMBebn, Seriblizbble {

    stbtic finbl long seriblVersionUID = 910173589985461347L;

    public finbl stbtic clbss ThrebdStbteMbp {
        public finbl stbtic clbss Byte0 {
            public finbl stbtic byte inNbtive     = (byte)0x80; // bit 1
            public finbl stbtic byte suspended    = (byte)0x40; // bit 2
            public finbl stbtic byte newThrebd    = (byte)0x20; // bit 3
            public finbl stbtic byte runnbble     = (byte)0x10; // bit 4
            public finbl stbtic byte blocked      = (byte)0x08; // bit 5
            public finbl stbtic byte terminbted   = (byte)0x04; // bit 6
            public finbl stbtic byte wbiting      = (byte)0x02; // bit 7
            public finbl stbtic byte timedWbiting = (byte)0x01; // bit 8
        }
        public finbl stbtic clbss Byte1 {
            public finbl stbtic byte other        = (byte)0x80; // bit 9
            public finbl stbtic byte reserved10   = (byte)0x40; // bit 10
            public finbl stbtic byte reserved11   = (byte)0x20; // bit 11
            public finbl stbtic byte reserved12   = (byte)0x10; // bit 12
            public finbl stbtic byte reserved13   = (byte)0x08; // bit 13
            public finbl stbtic byte reserved14   = (byte)0x04; // bit 14
            public finbl stbtic byte reserved15   = (byte)0x02; // bit 15
            public finbl stbtic byte reserved16   = (byte)0x01; // bit 16
        }

        public finbl stbtic byte mbsk0 = (byte)0x3F;
        public finbl stbtic byte mbsk1 = (byte)0x80;

        privbte stbtic void setBit(byte[] bitmbp, int index, byte stbte) {
            bitmbp[index] = (byte) (bitmbp[index] | stbte);
        }
        public stbtic void setNbtive(byte[] bitmbp) {
            setBit(bitmbp,0,Byte0.inNbtive);
        }
        public stbtic void setSuspended(byte[] bitmbp) {
            setBit(bitmbp,0,Byte0.suspended);
        }
        public stbtic void setStbte(byte[] bitmbp, Threbd.Stbte stbte) {
            switch(stbte) {
            cbse BLOCKED:
                setBit(bitmbp,0,Byte0.blocked);
                return;
            cbse NEW:
                setBit(bitmbp,0,Byte0.newThrebd);
                return;
            cbse RUNNABLE:
                setBit(bitmbp,0,Byte0.runnbble);
                return;
            cbse TERMINATED:
                setBit(bitmbp,0,Byte0.terminbted);
                return;
            cbse TIMED_WAITING:
                setBit(bitmbp,0,Byte0.timedWbiting);
                return;
            cbse WAITING:
                setBit(bitmbp,0,Byte0.wbiting);
                return;
            }
        }

        public stbtic void checkOther(byte[] bitmbp) {
            if (((bitmbp[0]&mbsk0)==(byte)0x00) &&
                ((bitmbp[1]&mbsk1)==(byte)0x00))
                setBit(bitmbp,1,Byte1.other);
        }

        public stbtic Byte[] getStbte(ThrebdInfo info) {
            byte[] bitmbp = new byte[] {(byte)0x00, (byte)0x00};
            try {
                finbl Threbd.Stbte stbte = info.getThrebdStbte();
                finbl boolebn inNbtive  = info.isInNbtive();
                finbl boolebn suspended = info.isSuspended();
                log.debug("getJvmThrebdInstStbte",
                          "[Stbte=" + stbte +
                          ",isInNbtive=" + inNbtive +
                          ",isSuspended=" + suspended + "]");
                setStbte(bitmbp,stbte);
                if (inNbtive)  setNbtive(bitmbp);
                if (suspended) setSuspended(bitmbp);
                checkOther(bitmbp);
            } cbtch (RuntimeException r) {
                bitmbp[0]=(byte)0x00;
                bitmbp[1]=Byte1.other;
                log.trbce("getJvmThrebdInstStbte",
                          "Unexpected exception: " + r);
                log.debug("getJvmThrebdInstStbte",r);
            }
            Byte[] result = {bitmbp[0], bitmbp[1]};
            return result;
        }
    }

    privbte finbl ThrebdInfo info;
    privbte finbl Byte[] index;

    /**
     * Constructor for the "JvmThrebdInstbnceEntry" group.
     */
    public JvmThrebdInstbnceEntryImpl(ThrebdInfo info,
                                      Byte[] index) {
        this.info = info;
        this.index = index;
    }


    privbte stbtic String  jvmThrebdInstIndexOid = null;
    public stbtic String getJvmThrebdInstIndexOid()
        throws SnmpStbtusException {
        if (jvmThrebdInstIndexOid == null) {
            finbl SnmpOidTbble  tbble = new JVM_MANAGEMENT_MIBOidTbble();
            finbl SnmpOidRecord record =
                tbble.resolveVbrNbme("jvmThrebdInstIndex");
            jvmThrebdInstIndexOid = record.getOid();
        }
        return jvmThrebdInstIndexOid;
    }



    /**
     * Getter for the "JvmThrebdInstLockedOwnerId" vbribble.
     */
    public String getJvmThrebdInstLockOwnerPtr() throws SnmpStbtusException {
       long id = info.getLockOwnerId();

       if(id == -1)
           return new String("0.0");

       SnmpOid oid = JvmThrebdInstbnceTbbleMetbImpl.mbkeOid(id);

       return getJvmThrebdInstIndexOid() + "." + oid.toString();
    }

    privbte String vblidDisplbyStringTC(String str) {
        return JVM_MANAGEMENT_MIB_IMPL.vblidDisplbyStringTC(str);
    }

    privbte String vblidJbvbObjectNbmeTC(String str) {
        return JVM_MANAGEMENT_MIB_IMPL.vblidJbvbObjectNbmeTC(str);
    }

    privbte String vblidPbthElementTC(String str) {
        return JVM_MANAGEMENT_MIB_IMPL.vblidPbthElementTC(str);
    }

    /**
     * Getter for the "JvmThrebdInstLockNbme" vbribble.
     */
    public String getJvmThrebdInstLockNbme() throws SnmpStbtusException {
        return vblidJbvbObjectNbmeTC(info.getLockNbme());
    }

    /**
     * Getter for the "JvmThrebdInstNbme" vbribble.
     */
    public String getJvmThrebdInstNbme() throws SnmpStbtusException {
        return vblidJbvbObjectNbmeTC(info.getThrebdNbme());
    }

    /**
     * Getter for the "JvmThrebdInstCpuTimeNs" vbribble.
     */
    public Long getJvmThrebdInstCpuTimeNs() throws SnmpStbtusException {
        long l = 0;
        finbl ThrebdMXBebn tmb = JvmThrebdingImpl.getThrebdMXBebn();

        try {
            if (tmb.isThrebdCpuTimeSupported()) {
                l = tmb.getThrebdCpuTime(info.getThrebdId());
                log.debug("getJvmThrebdInstCpuTimeNs", "Cpu time ns : " + l);

                //Cpu time mebsurement is disbbled or the id is not vblid.
                if(l == -1) l = 0;
            }
        } cbtch (UnsbtisfiedLinkError e) {
            // XXX Revisit: cbtch TO BE EVENTUALLY REMOVED
            log.debug("getJvmThrebdInstCpuTimeNs",
                      "Operbtion not supported: " + e);
        }
        return l;
    }

    /**
     * Getter for the "JvmThrebdInstBlockTimeMs" vbribble.
     */
    public Long getJvmThrebdInstBlockTimeMs() throws SnmpStbtusException {
        long l = 0;

        finbl ThrebdMXBebn tmb = JvmThrebdingImpl.getThrebdMXBebn();

        if (tmb.isThrebdContentionMonitoringSupported()) {
            l = info.getBlockedTime();

            //Monitoring is disbbled
            if(l == -1) l = 0;
        }
        return l;
    }

    /**
     * Getter for the "JvmThrebdInstBlockCount" vbribble.
     */
    public Long getJvmThrebdInstBlockCount() throws SnmpStbtusException {
        return info.getBlockedCount();
    }

    /**
     * Getter for the "JvmThrebdInstWbitTimeMs" vbribble.
     */
    public Long getJvmThrebdInstWbitTimeMs() throws SnmpStbtusException {
        long l = 0;

        finbl ThrebdMXBebn tmb = JvmThrebdingImpl.getThrebdMXBebn();

        if (tmb.isThrebdContentionMonitoringSupported()) {
            l = info.getWbitedTime();

            //Monitoring is disbbled
            if(l == -1) l = 0;
        }
        return l;
    }

    /**
     * Getter for the "JvmThrebdInstWbitCount" vbribble.
     */
    public Long getJvmThrebdInstWbitCount() throws SnmpStbtusException {
        return info.getWbitedCount();
    }

    /**
     * Getter for the "JvmThrebdInstStbte" vbribble.
     */
    public Byte[] getJvmThrebdInstStbte()
        throws SnmpStbtusException {
        return ThrebdStbteMbp.getStbte(info);
    }

    /**
     * Getter for the "JvmThrebdInstId" vbribble.
     */
    public Long getJvmThrebdInstId() throws SnmpStbtusException {
        return info.getThrebdId();
    }

    /**
     * Getter for the "JvmThrebdInstIndex" vbribble.
     */
    public Byte[] getJvmThrebdInstIndex() throws SnmpStbtusException {
        return index;
    }

    /**
     * Getter for the "JvmThrebdInstStbckTrbce" vbribble.
     */
    privbte String getJvmThrebdInstStbckTrbce() throws SnmpStbtusException {
        StbckTrbceElement[] stbckTrbce = info.getStbckTrbce();
        //We bppend the stbck trbce in b buffer
        // XXX Revisit: should check isDebugOn()
        StringBuilder sb = new StringBuilder();
        finbl int stbckSize = stbckTrbce.length;
        log.debug("getJvmThrebdInstStbckTrbce", "Stbck size : " + stbckSize);
        for(int i = 0; i < stbckSize; i++) {
            log.debug("getJvmThrebdInstStbckTrbce", "Append " +
                      stbckTrbce[i].toString());
            sb.bppend(stbckTrbce[i].toString());
            //Append \n bt the end of ebch line except the lbst one
            if(i < stbckSize)
                sb.bppend("\n");
        }
        //The stbck trbce is truncbted if its size exceeds 255.
        return vblidPbthElementTC(sb.toString());
    }
    stbtic finbl MibLogger log =
        new MibLogger(JvmThrebdInstbnceEntryImpl.clbss);
}
