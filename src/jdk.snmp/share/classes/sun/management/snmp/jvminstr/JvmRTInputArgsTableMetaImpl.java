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
import com.sun.jmx.mbebnserver.Util;
import jbvb.util.List;
import jbvb.util.Mbp;

// jmx imports
//
import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.ObjectNbme;
import com.sun.jmx.snmp.SnmpCounter;
import com.sun.jmx.snmp.SnmpCounter64;
import com.sun.jmx.snmp.SnmpGbuge;
import com.sun.jmx.snmp.SnmpInt;
import com.sun.jmx.snmp.SnmpUnsignedInt;
import com.sun.jmx.snmp.SnmpIpAddress;
import com.sun.jmx.snmp.SnmpTimeticks;
import com.sun.jmx.snmp.SnmpOpbque;
import com.sun.jmx.snmp.SnmpString;
import com.sun.jmx.snmp.SnmpStringFixed;
import com.sun.jmx.snmp.SnmpOid;
import com.sun.jmx.snmp.SnmpNull;
import com.sun.jmx.snmp.SnmpVblue;
import com.sun.jmx.snmp.SnmpVbrBind;
import com.sun.jmx.snmp.SnmpStbtusException;

// jdmk imports
//
import com.sun.jmx.snmp.bgent.SnmpIndex;
import com.sun.jmx.snmp.bgent.SnmpMib;
import com.sun.jmx.snmp.bgent.SnmpMibTbble;
import com.sun.jmx.snmp.bgent.SnmpMibSubRequest;
import com.sun.jmx.snmp.bgent.SnmpStbndbrdObjectServer;

import sun.mbnbgement.snmp.jvmmib.JvmRTInputArgsTbbleMetb;
import sun.mbnbgement.snmp.util.SnmpCbchedDbtb;
import sun.mbnbgement.snmp.util.SnmpTbbleCbche;
import sun.mbnbgement.snmp.util.SnmpTbbleHbndler;
import sun.mbnbgement.snmp.util.MibLogger;
import sun.mbnbgement.snmp.util.JvmContextFbctory;

/**
 * The clbss is used for implementing the "JvmRTInputArgsTbble" group.
 */
public clbss JvmRTInputArgsTbbleMetbImpl extends JvmRTInputArgsTbbleMetb {

    stbtic finbl long seriblVersionUID = -2083438094888099238L;
    privbte SnmpTbbleCbche cbche;

     /**
     * A concrete implementbtion of {@link SnmpTbbleCbche}, for the
     * JvmRTInputArgsTbble.
     **/
    privbte stbtic clbss JvmRTInputArgsTbbleCbche extends SnmpTbbleCbche {

        stbtic finbl long seriblVersionUID = 1693751105464785192L;
        privbte JvmRTInputArgsTbbleMetbImpl metb;

        JvmRTInputArgsTbbleCbche(JvmRTInputArgsTbbleMetbImpl metb,
                                 long vblidity) {
            this.metb = metb;
            this.vblidity = vblidity;
        }

        /**
         * Cbll <code>getTbbleDbtbs(JvmContextFbctory.getUserDbtb())</code>.
         **/
        public SnmpTbbleHbndler getTbbleHbndler() {
            finbl Mbp<Object,Object> userDbtb = JvmContextFbctory.getUserDbtb();
            return getTbbleDbtbs(userDbtb);
        }


        /**
         * Return b tbble hbndler contbining the Threbd indexes.
         * Indexes bre computed from the ThrebdId.
         **/
        protected SnmpCbchedDbtb updbteCbchedDbtbs(Object userDbtb) {


            // We bre getting bll the input brgs
            finbl String[] brgs = JvmRuntimeImpl.getInputArguments(userDbtb);

            // Time stbmp for the cbche
            finbl long time = System.currentTimeMillis();
            SnmpOid indexes[] = new SnmpOid[brgs.length];

            for(int i = 0; i < brgs.length; i++) {
                indexes[i] = new SnmpOid(i + 1);
            }

            return new SnmpCbchedDbtb(time, indexes, brgs);
        }
    }

    /**
     * Constructor for the tbble. Initiblize metbdbtb for
     * "JvmRTInputArgsTbbleMetb".
     * The reference on the MBebn server is updbted so the entries
     * crebted through bn SNMP SET will be AUTOMATICALLY REGISTERED
     * in Jbvb DMK.
     */
    public JvmRTInputArgsTbbleMetbImpl(SnmpMib myMib,
                                       SnmpStbndbrdObjectServer objserv) {
        super(myMib, objserv);
        cbche = new JvmRTInputArgsTbbleCbche(this, -1);
    }

    // See com.sun.jmx.snmp.bgent.SnmpMibTbble
    protected SnmpOid getNextOid(Object userDbtb)
        throws SnmpStbtusException {
        // null mebns get the first OID.
        return getNextOid(null,userDbtb);
    }

    // See com.sun.jmx.snmp.bgent.SnmpMibTbble
    protected SnmpOid getNextOid(SnmpOid oid, Object userDbtb)
        throws SnmpStbtusException {
        finbl boolebn dbg = log.isDebugOn();
        if (dbg) log.debug("getNextOid", "previous=" + oid);


        // Get the dbtb hbndler.
        //
        SnmpTbbleHbndler hbndler = getHbndler(userDbtb);
        if (hbndler == null) {
            // This should never hbppen.
            // If we get here it's b bug.
            //
            if (dbg) log.debug("getNextOid", "hbndler is null!");
            throw new SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);
        }

        // Get the next oid
        //
        finbl SnmpOid next = hbndler.getNext(oid);
        if (dbg) log.debug("*** **** **** **** getNextOid", "next=" + next);

        // if next is null: we rebched the end of the tbble.
        //
        if (next == null)
            throw new SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);

        return next;
    }


    // See com.sun.jmx.snmp.bgent.SnmpMibTbble
    protected boolebn contbins(SnmpOid oid, Object userDbtb) {

        // Get the hbndler.
        //
        SnmpTbbleHbndler hbndler = getHbndler(userDbtb);

        // hbndler should never be null.
        //
        if (hbndler == null)
            return fblse;

        return hbndler.contbins(oid);
    }

    // See com.sun.jmx.snmp.bgent.SnmpMibTbble
    public Object getEntry(SnmpOid oid)
        throws SnmpStbtusException {
        finbl boolebn dbg = log.isDebugOn();
        if (dbg) log.debug("getEntry", "oid [" + oid + "]");
        if (oid == null || oid.getLength() != 1) {
            if (dbg) log.debug("getEntry", "Invblid oid [" + oid + "]");
            throw new SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);
        }

        // Get the request contextubl cbche (userDbtb).
        //
        finbl Mbp<Object, Object> m = JvmContextFbctory.getUserDbtb();

        // We're going to use this nbme to store/retrieve the entry in
        // the request contextubl cbche.
        //
        // Revisit: Probbbly better progrbmming to put bll these strings
        //          in some interfbce.
        //
        finbl String entryTbg = ((m==null)?null:
                                 ("JvmRTInputArgsTbble.entry." +
                                  oid.toString()));

        // If the entry is in the cbche, simply return it.
        //
        if (m != null) {
            finbl Object entry = m.get(entryTbg);
            if (entry != null) {
                if (dbg)
                    log.debug("getEntry", "Entry is blrebdy in the cbche");
                return entry;
            } else if (dbg) log.debug("getEntry", "Entry is not in the cbche");
        }

        // The entry wbs not in the cbche, mbke b new one.
        //
        // Get the dbtb hbnler.
        //
        SnmpTbbleHbndler hbndler = getHbndler(m);

        // hbndler should never be null.
        //
        if (hbndler == null)
            throw new SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);

        // Get the dbtb bssocibted with our entry.
        //
        finbl Object dbtb = hbndler.getDbtb(oid);

        // dbtb mby be null if the OID we were given is not vblid.
        //
        if (dbtb == null)
            throw new SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);

        // mbke the new entry (trbnsient object thbt will be kept only
        // for the durbtion of the request.
        //
        if (dbg) log.debug("getEntry","dbtb is b: " +
                           dbtb.getClbss().getNbme());
        finbl Object entry =
            new JvmRTInputArgsEntryImpl((String) dbtb, (int) oid.getOidArc(0));

        // Put the entry in the cbche in cbse we need it lbter while processing
        // the request.
        //
        if (m != null && entry != null) {
            m.put(entryTbg,entry);
        }

        return entry;
    }

    /**
     * Get the SnmpTbbleHbndler thbt holds the jvmThrebdInstbnceTbble dbtb.
     * First look it up in the request contextubl cbche, bnd if it is
     * not found, obtbin it from the webk cbche.
     * <br>The request contextubl cbche will be relebsed bt the end of the
     * current requests, bnd is used only to process this request.
     * <br>The webk cbche is shbred by bll requests, bnd is only
     * recomputed when it is found to be obsolete.
     * <br>Note thbt the dbtb put in the request contextubl cbche is
     *     never considered to be obsolete, in order to preserve dbtb
     *     coherency.
     **/
    protected SnmpTbbleHbndler getHbndler(Object userDbtb) {
        finbl Mbp<Object, Object> m;
        if (userDbtb instbnceof Mbp) m=Util.cbst(userDbtb);
        else m=null;

        // Look in the contextubl cbche.
        if (m != null) {
            finbl SnmpTbbleHbndler hbndler =
                (SnmpTbbleHbndler)m.get("JvmRTInputArgsTbble.hbndler");
            if (hbndler != null) return hbndler;
        }

        // No hbndler in contextubl cbche, mbke b new one.
        finbl SnmpTbbleHbndler hbndler = cbche.getTbbleHbndler();

        if (m != null && hbndler != null )
            m.put("JvmRTInputArgsTbble.hbndler",hbndler);

        return hbndler;
    }

    stbtic finbl MibLogger log =
        new MibLogger(JvmRTInputArgsTbbleMetbImpl.clbss);
}
