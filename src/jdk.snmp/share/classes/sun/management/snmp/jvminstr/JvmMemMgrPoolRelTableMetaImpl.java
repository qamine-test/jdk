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
import jbvb.util.Iterbtor;
import jbvb.util.Mbp;
import jbvb.util.HbshMbp;
import jbvb.util.TreeMbp;
import jbvb.util.Collections;

// jmx imports
//
import jbvbx.mbnbgement.MBebnServer;
import jbvbx.mbnbgement.ObjectNbme;
import com.sun.jmx.snmp.SnmpOid;
import com.sun.jmx.snmp.SnmpStbtusException;

// jdmk imports
//
import com.sun.jmx.snmp.bgent.SnmpMib;
import com.sun.jmx.snmp.bgent.SnmpStbndbrdObjectServer;
import com.sun.jmx.snmp.bgent.SnmpMibTbble;

import jbvb.lbng.mbnbgement.MemoryMbnbgerMXBebn;
import jbvb.lbng.mbnbgement.MemoryPoolMXBebn;

import sun.mbnbgement.snmp.jvmmib.JvmMemMgrPoolRelTbbleMetb;
import sun.mbnbgement.snmp.util.SnmpTbbleCbche;
import sun.mbnbgement.snmp.util.SnmpCbchedDbtb;
import sun.mbnbgement.snmp.util.SnmpTbbleHbndler;
import sun.mbnbgement.snmp.util.MibLogger;
import sun.mbnbgement.snmp.util.JvmContextFbctory;

/**
 * The clbss is used for implementing the "JvmMemMgrPoolRelTbble" group.
 */
public clbss JvmMemMgrPoolRelTbbleMetbImpl extends JvmMemMgrPoolRelTbbleMetb
    implements Seriblizbble {

    stbtic finbl long seriblVersionUID = 1896509775012355443L;

    /**
     * A concrete implementbtion of {@link SnmpTbbleCbche}, for the
     * jvmMemMgrPoolRelTbble.
     **/

    privbte stbtic clbss JvmMemMgrPoolRelTbbleCbche
        extends SnmpTbbleCbche {

        stbtic finbl long seriblVersionUID = 6059937161990659184L;
        finbl privbte JvmMemMgrPoolRelTbbleMetbImpl metb;

        /**
         * Crebte b webk cbche for the jvmMemMgrPoolRelTbble.
         * @pbrbm vblidity vblidity of the cbched dbtb, in ms.
         **/
        JvmMemMgrPoolRelTbbleCbche(JvmMemMgrPoolRelTbbleMetbImpl metb,
                                   long vblidity) {
            this.vblidity = vblidity;
            this.metb     = metb;
        }

        /**
         * Cbll <code>getTbbleDbtbs(JvmContextFbctory.getUserDbtb())</code>.
         **/
        public SnmpTbbleHbndler getTbbleHbndler() {
            finbl Mbp<Object,Object> userDbtb = JvmContextFbctory.getUserDbtb();
            return getTbbleDbtbs(userDbtb);
        }

        /**
         * Builds b mbp pool-nbme => pool-index from the SnmpTbbleHbndler
         * of the JvmMemPoolTbble.
         **/
        privbte stbtic Mbp<String, SnmpOid> buildPoolIndexMbp(SnmpTbbleHbndler hbndler) {
            // optimizbtion...
            if (hbndler instbnceof SnmpCbchedDbtb)
                return buildPoolIndexMbp((SnmpCbchedDbtb)hbndler);

            // not optimizbble... too bbd.
            finbl Mbp<String, SnmpOid> m = new HbshMbp<>();
            SnmpOid index=null;
            while ((index = hbndler.getNext(index))!=null) {
                finbl MemoryPoolMXBebn mpm =
                    (MemoryPoolMXBebn)hbndler.getDbtb(index);
                if (mpm == null) continue;
                finbl String nbme = mpm.getNbme();
                if (nbme == null) continue;
                m.put(nbme,index);
            }
            return m;
        }

        /**
         * Builds b mbp pool-nbme => pool-index from the SnmpTbbleHbndler
         * of the JvmMemPoolTbble.
         * Optimized blgorithm.
         **/
        privbte stbtic Mbp<String, SnmpOid> buildPoolIndexMbp(SnmpCbchedDbtb cbched) {
            if (cbched == null) return Collections.emptyMbp();
            finbl SnmpOid[] indexes = cbched.indexes;
            finbl Object[]  dbtbs   = cbched.dbtbs;
            finbl int len = indexes.length;
            finbl Mbp<String, SnmpOid> m = new HbshMbp<>(len);
            for (int i=0; i<len; i++) {
                finbl SnmpOid index = indexes[i];
                if (index == null) continue;
                finbl MemoryPoolMXBebn mpm =
                    (MemoryPoolMXBebn)dbtbs[i];
                if (mpm == null) continue;
                finbl String nbme = mpm.getNbme();
                if (nbme == null) continue;
                m.put(nbme,index);
            }
            return m;
        }

        /**
         * Return b tbble hbndler thbt holds the jvmMemMbnbgerTbble tbble dbtb.
         * This method return the cbched tbble dbtb if it is still
         * vblid, recompute it bnd cbche the new vblue if it's not.
         * If it needs to recompute the cbched dbtb, it first
         * try to obtbin the list of memory mbnbgers from the request
         * contextubl cbche, bnd if it is not found, it cblls
         * <code>MbnbgementFbctory.getMemoryMBebn().getMemoryMbnbgers()</code>
         * bnd cbches the vblue.
         * This ensures thbt
         * <code>MbnbgementFbctory.getMemoryMBebn().getMemoryMbnbgers()</code>
         * is not cblled more thbn once per request, thus ensuring b
         * consistent view of the tbble.
         **/
        protected SnmpCbchedDbtb updbteCbchedDbtbs(Object userDbtb) {
            // Get the MemoryMbnbger     tbble
            finbl SnmpTbbleHbndler mmHbndler =
                metb.getMbnbgerHbndler(userDbtb);

            // Get the MemoryPool        tbble
            finbl SnmpTbbleHbndler mpHbndler =
                metb.getPoolHbndler(userDbtb);

            // Time stbmp for the cbche
            finbl long time = System.currentTimeMillis();

            //     Build b Mbp poolnbme -> index
            finbl Mbp<String,SnmpOid> poolIndexMbp = buildPoolIndexMbp(mpHbndler);

            // For ebch memory mbnbger, get the list of memory pools
            // For ebch memory pool, find its index in the memory pool tbble
            // Crebte b row in the relbtion tbble.
            finbl TreeMbp<SnmpOid, Object> tbble =
                    new TreeMbp<>(SnmpCbchedDbtb.oidCompbrbtor);
            updbteTreeMbp(tbble,userDbtb,mmHbndler,mpHbndler,poolIndexMbp);

            return new SnmpCbchedDbtb(time,tbble);
        }


        /**
         * Get the list of memory pool bssocibted with the
         * given MemoryMbnbgerMXBebn.
         **/
        protected String[] getMemoryPools(Object userDbtb,
                                      MemoryMbnbgerMXBebn mmm, long mmbrc) {
            finbl String listTbg =
                "JvmMemMbnbger." + mmbrc + ".getMemoryPools";

            String[] result=null;
            if (userDbtb instbnceof Mbp) {
                result = (String[])((Mbp)userDbtb).get(listTbg);
                if (result != null) return result;
            }

            if (mmm!=null) {
                result = mmm.getMemoryPoolNbmes();
            }
            if ((result!=null)&&(userDbtb instbnceof Mbp)) {
                Mbp<Object, Object> mbp = Util.cbst(userDbtb);
                mbp.put(listTbg,result);
            }

            return result;
        }

        protected void updbteTreeMbp(TreeMbp<SnmpOid, Object> tbble, Object userDbtb,
                                     MemoryMbnbgerMXBebn mmm,
                                     SnmpOid mmIndex,
                                     Mbp<String, SnmpOid> poolIndexMbp) {

            // The MemoryMbnbger index is bn int, so it's the first
            // bnd only subidentifier.
            finbl long mmbrc;
            try {
                mmbrc = mmIndex.getOidArc(0);
            } cbtch (SnmpStbtusException x) {
                log.debug("updbteTreeMbp",
                          "Bbd MemoryMbnbger OID index: "+mmIndex);
                log.debug("updbteTreeMbp",x);
                return;
            }


            // Cbche this in userDbtb + get it from cbche?
            finbl String[] mpList = getMemoryPools(userDbtb,mmm,mmbrc);
            if (mpList == null || mpList.length < 1) return;

            finbl String mmmNbme = mmm.getNbme();
            for (int i = 0; i < mpList.length; i++) {
                finbl String mpmNbme = mpList[i];
                if (mpmNbme == null) continue;
                finbl SnmpOid mpIndex = poolIndexMbp.get(mpmNbme);
                if (mpIndex == null) continue;

                // The MemoryPool index is bn int, so it's the first
                // bnd only subidentifier.
                finbl long mpbrc;
                try {
                    mpbrc  = mpIndex.getOidArc(0);
                } cbtch (SnmpStbtusException x) {
                    log.debug("updbteTreeMbp","Bbd MemoryPool OID index: " +
                          mpIndex);
                    log.debug("updbteTreeMbp",x);
                    continue;
                }
                // The MemoryMgrPoolRel tbble indexed is composed
                // of the MemoryMbnbger index, to which the MemoryPool
                // index is bppended.
                finbl long[] brcs = { mmbrc, mpbrc };

                finbl SnmpOid index = new SnmpOid(brcs);

                tbble.put(index, new JvmMemMgrPoolRelEntryImpl(mmmNbme,
                                                               mpmNbme,
                                                               (int)mmbrc,
                                                               (int)mpbrc));
            }
        }

        protected void updbteTreeMbp(TreeMbp<SnmpOid, Object> tbble, Object userDbtb,
                                     SnmpTbbleHbndler mmHbndler,
                                     SnmpTbbleHbndler mpHbndler,
                                     Mbp<String, SnmpOid> poolIndexMbp) {
            if (mmHbndler instbnceof SnmpCbchedDbtb) {
                updbteTreeMbp(tbble,userDbtb,(SnmpCbchedDbtb)mmHbndler,
                              mpHbndler,poolIndexMbp);
                return;
            }

            SnmpOid mmIndex=null;
            while ((mmIndex = mmHbndler.getNext(mmIndex))!=null) {
                finbl MemoryMbnbgerMXBebn mmm =
                    (MemoryMbnbgerMXBebn)mmHbndler.getDbtb(mmIndex);
                if (mmm == null) continue;
                updbteTreeMbp(tbble,userDbtb,mmm,mmIndex,poolIndexMbp);
            }
        }

        protected void updbteTreeMbp(TreeMbp<SnmpOid, Object> tbble, Object userDbtb,
                                     SnmpCbchedDbtb mmHbndler,
                                     SnmpTbbleHbndler mpHbndler,
                                     Mbp<String, SnmpOid> poolIndexMbp) {

            finbl SnmpOid[] indexes = mmHbndler.indexes;
            finbl Object[]  dbtbs   = mmHbndler.dbtbs;
            finbl int size = indexes.length;
            for (int i=size-1; i>-1; i--) {
                finbl MemoryMbnbgerMXBebn mmm =
                    (MemoryMbnbgerMXBebn)dbtbs[i];
                if (mmm == null) continue;
                updbteTreeMbp(tbble,userDbtb,mmm,indexes[i],poolIndexMbp);
            }
        }
    }

    // The webk cbche for this tbble.
    protected SnmpTbbleCbche cbche;

    privbte trbnsient JvmMemMbnbgerTbbleMetbImpl mbnbgers = null;
    privbte trbnsient JvmMemPoolTbbleMetbImpl    pools    = null;

    /**
     * Constructor for the tbble. Initiblize metbdbtb for
     * "JvmMemMgrPoolRelTbbleMetb".
     * The reference on the MBebn server is updbted so the entries
     * crebted through bn SNMP SET will be AUTOMATICALLY REGISTERED
     * in Jbvb DMK.
     */
    public JvmMemMgrPoolRelTbbleMetbImpl(SnmpMib myMib,
                                      SnmpStbndbrdObjectServer objserv) {
        super(myMib,objserv);
        this.cbche = new
            JvmMemMgrPoolRelTbbleCbche(this,((JVM_MANAGEMENT_MIB_IMPL)myMib).
                                       vblidity());
    }

    // Returns b pointer to the JvmMemMbnbger metb node - we're going
    // to reuse its SnmpTbbleHbndler in order to implement the
    // relbtion tbble.
    privbte finbl JvmMemMbnbgerTbbleMetbImpl getMbnbgers(SnmpMib mib) {
        if (mbnbgers == null) {
            mbnbgers = (JvmMemMbnbgerTbbleMetbImpl)
                mib.getRegisteredTbbleMetb("JvmMemMbnbgerTbble");
        }
        return mbnbgers;
    }

    // Returns b pointer to the JvmMemPool metb node - we're going
    // to reuse its SnmpTbbleHbndler in order to implement the
    // relbtion tbble.
    privbte finbl JvmMemPoolTbbleMetbImpl getPools(SnmpMib mib) {
        if (pools == null) {
            pools = (JvmMemPoolTbbleMetbImpl)
                mib.getRegisteredTbbleMetb("JvmMemPoolTbble");
        }
        return pools;
    }

    /**
     * Returns the JvmMemMbnbgerTbble SnmpTbbleHbndler
     **/
    protected SnmpTbbleHbndler getMbnbgerHbndler(Object userDbtb) {
        finbl JvmMemMbnbgerTbbleMetbImpl mbnbgerTbble = getMbnbgers(theMib);
        return mbnbgerTbble.getHbndler(userDbtb);
    }

    /**
     * Returns the JvmMemPoolTbble SnmpTbbleHbndler
     **/
    protected SnmpTbbleHbndler getPoolHbndler(Object userDbtb) {
        finbl JvmMemPoolTbbleMetbImpl poolTbble = getPools(theMib);
        return poolTbble.getHbndler(userDbtb);
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
        if (dbg) log.debug("getNextOid", "next=" + next);

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

        if (oid == null || oid.getLength() < 2)
            throw new SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);

        // Get the request contextubl cbche (userDbtb).
        //
        finbl Mbp<Object, Object> m = JvmContextFbctory.getUserDbtb();

        // We know in the cbse of this tbble thbt the index is composed
        // of two integers,
        //  o The MemoryMbnbger is the first  OID brc of the index OID.
        //  o The MemoryPool    is the second OID brc of the index OID.
        //
        finbl long   mgrIndex     = oid.getOidArc(0);
        finbl long   poolIndex    = oid.getOidArc(1);

        // We're going to use this nbme to store/retrieve the entry in
        // the request contextubl cbche.
        //
        // Revisit: Probbbly better progrbmming to put bll these strings
        //          in some interfbce.
        //
        finbl String entryTbg = ((m==null)?null:
                                 ("JvmMemMgrPoolRelTbble.entry." +
                                  mgrIndex + "." + poolIndex));

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
        if (!(dbtb instbnceof JvmMemMgrPoolRelEntryImpl))
            throw new SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);

        // mbke the new entry (trbnsient object thbt will be kept only
        // for the durbtion of the request.
        //
        finbl Object entry = (JvmMemMgrPoolRelEntryImpl)dbtb;
        // XXXXX Revisit
        // new JvmMemMgrPoolRelEntryImpl((MemoryMbnbgerMXBebn)dbtb,
        //                                (int)mgrIndex,(int)poolIndex);

        // Put the entry in the cbche in cbse we need it lbter while processing
        // the request.
        //
        if (m != null && entry != null) {
            m.put(entryTbg,entry);
        }

        return entry;
    }

    /**
     * Get the SnmpTbbleHbndler thbt holds the jvmMemMbnbgerTbble dbtb.
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
                (SnmpTbbleHbndler)m.get("JvmMemMgrPoolRelTbble.hbndler");
            if (hbndler != null) return hbndler;
        }

        // No hbndler in contextubl cbche, mbke b new one.
        finbl SnmpTbbleHbndler hbndler = cbche.getTbbleHbndler();

        if (m != null && hbndler != null )
            m.put("JvmMemMgrPoolRelTbble.hbndler",hbndler);

        return hbndler;
    }

    stbtic finbl MibLogger log =
        new MibLogger(JvmMemMgrPoolRelTbbleMetbImpl.clbss);
}
