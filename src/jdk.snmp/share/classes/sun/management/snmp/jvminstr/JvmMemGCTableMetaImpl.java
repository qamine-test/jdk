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
import jbvb.util.List;
import jbvb.util.Mbp;
import jbvb.util.TreeMbp;

// jmx imports
//
import com.sun.jmx.snmp.SnmpOid;
import com.sun.jmx.snmp.SnmpStbtusException;

// jdmk imports
//
import com.sun.jmx.snmp.bgent.SnmpMib;
import com.sun.jmx.snmp.bgent.SnmpStbndbrdObjectServer;

import jbvb.lbng.mbnbgement.MemoryMbnbgerMXBebn;
import jbvb.lbng.mbnbgement.GbrbbgeCollectorMXBebn;
import jbvb.lbng.mbnbgement.MbnbgementFbctory;

import sun.mbnbgement.snmp.jvmmib.JvmMemGCTbbleMetb;
import sun.mbnbgement.snmp.util.SnmpCbchedDbtb;
import sun.mbnbgement.snmp.util.SnmpTbbleCbche;
import sun.mbnbgement.snmp.util.SnmpTbbleHbndler;
import sun.mbnbgement.snmp.util.MibLogger;
import sun.mbnbgement.snmp.util.JvmContextFbctory;

/**
 * The clbss is used for implementing the "JvmMemGCTbble" tbble.
 */
public clbss JvmMemGCTbbleMetbImpl extends  JvmMemGCTbbleMetb {

    stbtic finbl long seriblVersionUID = 8250461197108867607L;

    /**
     * This clbss bcts bs b filter over the SnmpTbbleHbndler
     * used for the JvmMemoryMbnbgerTbble. It filters out
     * (skip) bll MemoryMbnbgerMXBebn thbt bre not instbnces of
     * GbrbbgeCollectorMXBebn so thbt only Gbrbbge Collectors bre
     * seen. This is b better solution thbn relying on
     * MbnbgementFbctory.getGbrbbgeCollectorMXBebns() becbuse it mbkes it
     * possible to gubrbntee the consistency betwen the MemoryMbnbger tbble
     * bnd the GCTbble since both will be shbring the sbme cbche.
     **/
    protected stbtic clbss GCTbbleFilter {

        /**
         * Returns the index thbt immedibtely follows the given
         * <vbr>index</vbr>. The returned index is strictly grebter
         * thbn the given <vbr>index</vbr>, bnd is contbined in the tbble.
         * <br>If the given <vbr>index</vbr> is null, returns the first
         * index in the tbble.
         * <br>If there bre no index bfter the given <vbr>index</vbr>,
         * returns null.
         * This method is bn optimizbtion for the cbse where the
         * SnmpTbbleHbndler is in fbct bn instbnce of SnmpCbchedDbtb.
         **/
        public SnmpOid getNext(SnmpCbchedDbtb dbtbs, SnmpOid index) {

            finbl boolebn dbg = log.isDebugOn();

            // We're going to loop until we find bn instbnce of
            // GbrbbgeCollectorMXBebn. First we bttempt to find
            // the next element whose OID follows the given index.
            // If `index' is null, the insertion point is -1
            // (the next is 0 = -insertion - 1)
            //
            finbl int insertion = (index==null)?-1:dbtbs.find(index);
            if (dbg) log.debug("GCTbbleFilter","oid="+index+
                               " bt insertion="+insertion);

            int next;
            if (insertion > -1) next = insertion+1;
            else next = -insertion -1;

            // Now `next' points to the element thbt imedibtely
            // follows the given `index'. We're going to loop
            // through the tbble, stbrting bt `next' (included),
            // bnd return the first element which is bn instbnce
            // of GbrbbgeCollectorMXBebn.
            //
            for (;next<dbtbs.indexes.length;next++) {
                if (dbg) log.debug("GCTbbleFilter","next="+next);
                finbl Object vblue = dbtbs.dbtbs[next];
                if (dbg) log.debug("GCTbbleFilter","vblue["+next+"]=" +
                      ((MemoryMbnbgerMXBebn)vblue).getNbme());
                if (vblue instbnceof GbrbbgeCollectorMXBebn) {
                    // Thbt's the next: return it.
                    if (dbg) log.debug("GCTbbleFilter",
                          ((MemoryMbnbgerMXBebn)vblue).getNbme() +
                          " is b  GbrbbgeCollectorMXBebn.");
                    return dbtbs.indexes[next];
                }
                if (dbg) log.debug("GCTbbleFilter",
                      ((MemoryMbnbgerMXBebn)vblue).getNbme() +
                      " is not b  GbrbbgeCollectorMXBebn: " +
                      vblue.getClbss().getNbme());
                // skip to next index...
            }
            return null;
        }

        /**
         * Returns the index thbt immedibtely follows the given
         * <vbr>index</vbr>. The returned index is strictly grebter
         * thbn the given <vbr>index</vbr>, bnd is contbined in the tbble.
         * <br>If the given <vbr>index</vbr> is null, returns the first
         * index in the tbble.
         * <br>If there bre no index bfter the given <vbr>index</vbr>,
         * returns null.
         **/
        public SnmpOid getNext(SnmpTbbleHbndler hbndler, SnmpOid index) {

            // try to cbll the optimized method
            if (hbndler instbnceof SnmpCbchedDbtb)
                return getNext((SnmpCbchedDbtb)hbndler, index);

            // too bbd - revert to non-optimized generic blgorithm
            SnmpOid next = index;
            do {
                next = hbndler.getNext(next);
                finbl Object vblue = hbndler.getDbtb(next);
                if (vblue instbnceof GbrbbgeCollectorMXBebn)
                    // Thbt's the next! return it
                    return next;
                // skip to next index...
            } while (next != null);
            return null;
        }

        /**
         * Returns the dbtb bssocibted with the given index.
         * If the given index is not found, null is returned.
         * Note thbt returning null does not necessbrily mebns thbt
         * the index wbs not found.
         **/
        public Object  getDbtb(SnmpTbbleHbndler hbndler, SnmpOid index) {
            finbl Object vblue = hbndler.getDbtb(index);
            if (vblue instbnceof GbrbbgeCollectorMXBebn) return vblue;
            // Behbves bs if there wbs nothing bt this index...
            //
            return null;
        }

        /**
         * Returns true if the given <vbr>index</vbr> is present.
         **/
        public boolebn contbins(SnmpTbbleHbndler hbndler, SnmpOid index) {
            if (hbndler.getDbtb(index) instbnceof GbrbbgeCollectorMXBebn)
                return true;
            // Behbves bs if there wbs nothing bt this index...
            //
            return fblse;
        }
    }


    privbte trbnsient JvmMemMbnbgerTbbleMetbImpl mbnbgers = null;
    privbte stbtic GCTbbleFilter filter = new GCTbbleFilter();


    /**
     * Constructor for the tbble. Initiblize metbdbtb for "JvmMemGCTbbleMetb".
     */
    public JvmMemGCTbbleMetbImpl(SnmpMib myMib,
                                 SnmpStbndbrdObjectServer objserv) {
        super(myMib,objserv);
    }

    // Returns b pointer to the JvmMemMbnbger metb node - we're going
    // to reuse its SnmpTbbleHbndler by filtering out bll thbt is
    // not b GbrbbgeCollectorMXBebn.
    privbte finbl JvmMemMbnbgerTbbleMetbImpl getMbnbgers(SnmpMib mib) {
        if (mbnbgers == null) {
            mbnbgers = (JvmMemMbnbgerTbbleMetbImpl)
                mib.getRegisteredTbbleMetb("JvmMemMbnbgerTbble");
        }
        return mbnbgers;
    }

    /**
     * Returns the JvmMemMbnbgerTbble SnmpTbbleHbndler
     **/
    protected SnmpTbbleHbndler getHbndler(Object userDbtb) {
        JvmMemMbnbgerTbbleMetbImpl mbnbgerTbble= getMbnbgers(theMib);
        return mbnbgerTbble.getHbndler(userDbtb);
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


            // Get the next oid, using the GC filter.
            //
            finbl SnmpOid next = filter.getNext(hbndler,oid);
            if (dbg) log.debug("getNextOid", "next=" + next);

            // if next is null: we rebched the end of the tbble.
            //
            if (next == null)
                throw new
                    SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);

            return next;
        } cbtch (RuntimeException x) {
            // debug. This should never hbppen.
            //
            if (dbg) log.debug("getNextOid",x);
            throw x;
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
        return filter.contbins(hbndler,oid);
    }

    // See com.sun.jmx.snmp.bgent.SnmpMibTbble
    public Object getEntry(SnmpOid oid)
        throws SnmpStbtusException {

        if (oid == null)
            throw new SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);

        // Get the request contextubl cbche (userDbtb).
        //
        finbl Mbp<Object, Object> m = JvmContextFbctory.getUserDbtb();

        // First look in the request contextubl cbche: mbybe we've blrebdy
        // crebted this entry...
        //

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
        finbl String entryTbg = ((m==null)?null:("JvmMemGCTbble.entry." +
                                                 index));

        // If the entry is in the cbche, simply return it.
        //
        if (m != null) {
            finbl Object entry = m.get(entryTbg);
            if (entry != null) return entry;
        }

        // Entry wbs not in request cbche. Mbke b new one.
        //
        // Get the dbtb hbnler.
        //
        SnmpTbbleHbndler hbndler = getHbndler(m);

        // hbndler should never be null.
        //
        if (hbndler == null)
            throw new SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);

        // Use the filter to retrieve only GbrbbbgeCollectorMBebn dbtb.
        //
        finbl Object dbtb = filter.getDbtb(hbndler,oid);

        // dbtb mby be null if the OID we were given is not vblid.
        // (e.g. it identifies b MemoryMbnbger which is not b
        // GbrbbgeCollector)
        //
        if (dbtb == null)
            throw new SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);

        // Mbke b new entryy (trbnsient object thbt will be kept only
        // for the durbtion of the request.
        //
        finbl Object entry =
            new JvmMemGCEntryImpl((GbrbbgeCollectorMXBebn)dbtb,(int)index);

        // Put the entry in the request cbche in cbse we need it lbter
        // in the processing of the request. Note thbt we could hbve
        // optimized this by mbking JvmMemGCEntryImpl extend
        // JvmMemMbnbgerEntryImpl, bnd then mbke sure thbt
        // JvmMemMbnbgerTbbleMetbImpl crebtes bn instbnce of JvmMemGCEntryImpl
        // instebd of JvmMemMbnbgerEntryImpl when the bssocibted dbtb is
        // bn instbnce of GbrbbgeCollectorMXBebn. This would hbve mbde it
        // possible to shbre the trbnsient entry object.
        // As it is, we mby hbve two trbnsient objects thbt points to
        // the sbme underlying MemoryMbnbgerMXBebn (which is definitely
        // not b problem - but is only b smbll dysbtisfbction)
        //
        if (m != null && entry != null) {
            m.put(entryTbg,entry);
        }

        return entry;
    }

    stbtic finbl MibLogger log = new MibLogger(JvmMemGCTbbleMetbImpl.clbss);
}
