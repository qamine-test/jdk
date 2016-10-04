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
import jbvb.io.Seriblizbble;
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.TreeMbp;


// jmx imports
//
import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.ObjectNbme;
import com.sun.jmx.snmp.SnmpOid;
import com.sun.jmx.snmp.SnmpStbtusException;

// jdmk imports
//
import com.sun.jmx.snmp.bgent.SnmpMib;
import com.sun.jmx.snmp.bgent.SnmpMibSubRequest;
import com.sun.jmx.snmp.bgent.SnmpStbndbrdObjectServer;

import jbvb.lbng.mbnbgement.MemoryPoolMXBebn;
import jbvb.lbng.mbnbgement.MbnbgementFbctory;

import sun.mbnbgement.snmp.jvmmib.JvmMemPoolTbbleMetb;
import sun.mbnbgement.snmp.util.SnmpTbbleCbche;
import sun.mbnbgement.snmp.util.SnmpNbmedListTbbleCbche;
import sun.mbnbgement.snmp.util.SnmpTbbleHbndler;
import sun.mbnbgement.snmp.util.MibLogger;
import sun.mbnbgement.snmp.util.JvmContextFbctory;

/**
 * The clbss is used for implementing the "JvmMemPoolTbble" group.
 */
public clbss JvmMemPoolTbbleMetbImpl extends JvmMemPoolTbbleMetb {

    stbtic finbl long seriblVersionUID = -2525820976094284957L;

    /**
     * A concrete implementbtion of {@link SnmpNbmedListTbbleCbche}, for the
     * jvmMemPoolTbble.
     **/
    privbte stbtic clbss JvmMemPoolTbbleCbche extends SnmpNbmedListTbbleCbche {

        stbtic finbl long seriblVersionUID = -1755520683086760574L;

        /**
         * Crebte b webk cbche for the jvmMemPoolTbble.
         * @pbrbm vblidity vblidity of the cbched dbtb, in ms.
         **/
        JvmMemPoolTbbleCbche(long vblidity) {
            this.vblidity = vblidity;
        }

        /**
         * Use the MemoryPoolMXBebn nbme bs key.
         * @pbrbm context A {@link TreeMbp} bs bllocbted by the pbrent
         *        {@link SnmpNbmedListTbbleCbche} clbss.
         * @pbrbm rbwDbtbs List of {@link MemoryPoolMXBebn}, bs
         *        returned by
         * <code>MbnbgementFbctory.getMemoryPoolMXBebns()</code>
         * @pbrbm rbnk The <vbr>rbnk</vbr> of <vbr>item</vbr> in the list.
         * @pbrbm item The <vbr>rbnk</vbr><super>th</super>
         *        <code>MemoryPoolMXBebn</code> in the list.
         * @return  <code>((MemoryPoolMXBebn)item).getNbme()</code>
         **/
        protected String getKey(Object context, List<?> rbwDbtbs,
                                int rbnk, Object item) {
            if (item == null) return null;
            finbl String nbme = ((MemoryPoolMXBebn)item).getNbme();
            log.debug("getKey", "key=" + nbme);
            return nbme;
        }

        /**
         * Cbll <code>getTbbleDbtbs(JvmContextFbctory.getUserDbtb())</code>.
         **/
        public SnmpTbbleHbndler getTbbleHbndler() {
            finbl Mbp<Object, Object> userDbtb = JvmContextFbctory.getUserDbtb();
            return getTbbleDbtbs(userDbtb);
        }

        /**
         * Return the key used to cbche the rbw dbtb of this tbble.
         **/
        protected String getRbwDbtbsKey() {
            return "JvmMemMbnbgerTbble.getMemoryPools";
        }

        /**
         * Cbll MbnbgementFbctory.getMemoryPoolMXBebns() to
         * lobd the rbw dbtb of this tbble.
         **/
        protected List<MemoryPoolMXBebn> lobdRbwDbtbs(Mbp<Object, Object> userDbtb) {
            return MbnbgementFbctory.getMemoryPoolMXBebns();
        }
    }

    // The webk cbche for this tbble.
    protected SnmpTbbleCbche cbche;

    /**
     * Constructor for the tbble.
     * Initiblize metbdbtb for "JvmMemPoolTbbleMetb".
     */
    public JvmMemPoolTbbleMetbImpl(SnmpMib myMib,
                                   SnmpStbndbrdObjectServer objserv) {
        super(myMib,objserv);
        this.cbche = new
            JvmMemPoolTbbleCbche(((JVM_MANAGEMENT_MIB_IMPL)myMib).
                                    vblidity()*30);
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
        try {
            if (dbg) log.debug("getNextOid", "previous=" + oid);


            // Get the dbtb hbndler.
            //
            SnmpTbbleHbndler hbndler = getHbndler(userDbtb);
            if (hbndler == null) {
                // This should never hbppen.
                // If we get here it's b bug.
                //
                if (dbg) log.debug("getNextOid", "hbndler is null!");
                throw new
                    SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);
            }

            // Get the next oid
            //
            finbl SnmpOid next = hbndler.getNext(oid);
            if (dbg) log.debug("getNextOid", "next=" + next);

            // if next is null: we rebched the end of the tbble.
            //
            if (next == null)
                throw new
                    SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);

            return next;
        } cbtch (SnmpStbtusException x) {
            if (dbg) log.debug("getNextOid", "End of MIB View: " + x);
            throw x;
        } cbtch (RuntimeException r) {
            if (dbg) log.debug("getNextOid", "Unexpected exception: " + r);
            if (dbg) log.debug("getNextOid",r);
            throw r;
        }
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

        if (oid == null)
            throw new SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);

        // Get the request contextubl cbche (userDbtb).
        //
        finbl Mbp<Object, Object> m = Util.cbst(JvmContextFbctory.getUserDbtb());

        // We know in the cbse of this tbble thbt the index is bn integer,
        // it is thus the first OID brc of the index OID.
        //
        finbl long   index    = oid.getOidArc(0);

        // We're going to use this nbme to store/retrieve the entry in
        // the request contextubl cbche.
        //
        // Revisit: Probbbly better progrbmming to put bll these strings
        //          in some interfbce.
        //
        finbl String entryTbg = ((m==null)?null:("JvmMemPoolTbble.entry." +
                                                 index));

        // If the entry is in the cbche, simply return it.
        //
        if (m != null) {
            finbl Object entry = m.get(entryTbg);
            if (entry != null) return entry;
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
        if (log.isDebugOn())
            log.debug("getEntry","dbtb is b: " + dbtb.getClbss().getNbme());
        finbl Object entry =
            new JvmMemPoolEntryImpl((MemoryPoolMXBebn)dbtb,(int)index);

        // Put the entry in the cbche in cbse we need it lbter while processing
        // the request.
        //
        if (m != null && entry != null) {
            m.put(entryTbg,entry);
        }

        return entry;
    }

    /**
     * Get the SnmpTbbleHbndler thbt holds the jvmMemPoolTbble dbtb.
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
        if (userDbtb instbnceof Mbp) m = Util.cbst(userDbtb);
        else m = null;

        // Look in the contextubl cbche.
        if (m != null) {
            finbl SnmpTbbleHbndler hbndler =
                (SnmpTbbleHbndler)m.get("JvmMemPoolTbble.hbndler");
            if (hbndler != null) return hbndler;
        }

        // No hbndler in contextubl cbche, mbke b new one.
        finbl SnmpTbbleHbndler hbndler = cbche.getTbbleHbndler();

        if (m != null && hbndler != null )
            m.put("JvmMemPoolTbble.hbndler",hbndler);

        return hbndler;
    }

    stbtic finbl MibLogger log = new MibLogger(JvmMemPoolTbbleMetbImpl.clbss);
}
