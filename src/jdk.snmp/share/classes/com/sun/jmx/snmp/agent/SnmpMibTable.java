/*
 * Copyright (c) 1997, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.jmx.snmp.bgent;

import jbvb.io.Seriblizbble;
import jbvb.util.Dbte;
import jbvb.util.Enumerbtion;
import jbvb.util.Hbshtbble;
import jbvb.util.Vector;
import jbvb.util.logging.Level;

import jbvbx.mbnbgement.ListenerNotFoundException;
import jbvbx.mbnbgement.MBebnNotificbtionInfo;
import jbvbx.mbnbgement.Notificbtion;
import jbvbx.mbnbgement.NotificbtionBrobdcbster;
import jbvbx.mbnbgement.NotificbtionFilter;
import jbvbx.mbnbgement.NotificbtionListener;
import jbvbx.mbnbgement.ObjectNbme;

import stbtic com.sun.jmx.defbults.JmxProperties.SNMP_ADAPTOR_LOGGER;
import com.sun.jmx.snmp.EnumRowStbtus;
import com.sun.jmx.snmp.SnmpInt;
import com.sun.jmx.snmp.SnmpOid;
import com.sun.jmx.snmp.SnmpStbtusException;
import com.sun.jmx.snmp.SnmpVblue;
import com.sun.jmx.snmp.SnmpVbrBind;

/**
 * This clbss is the bbse clbss for SNMP tbble metbdbtb.
 * <p>
 * Its responsibility is to mbnbge b sorted brrby of OID indexes
 * bccording to the SNMP indexing scheme over the "rebl" tbble.
 * Ebch object of this clbss cbn be bound to bn
 * {@link com.sun.jmx.snmp.bgent.SnmpTbbleEntryFbctory} to which it will
 * forwbrd remote entry crebtion requests, bnd invoke cbllbbcks
 * when bn entry hbs been successfully bdded to / removed from
 * the OID index brrby.
 * </p>
 *
 * <p>
 * For ebch tbble defined in the MIB, mibgen will generbte b specific
 * clbss cblled Tbble<i>TbbleNbme</i> thbt will implement the
 * SnmpTbbleEntryFbctory interfbce, bnd b corresponding
 * <i>TbbleNbme</i>Metb clbss thbt will extend this clbss. <br>
 * The Tbble<i>TbbleNbme</i> clbss corresponds to the MBebn view of the
 * tbble while the <i>TbbleNbme</i>Metb clbss corresponds to the
 * MIB metbdbtb view of the sbme tbble.
 * </p>
 *
 * <p>
 * Objects of this clbss bre instbntibted by the generbted
 * whole MIB clbss extending {@link com.sun.jmx.snmp.bgent.SnmpMib}
 * You should never need to instbntibte this clbss directly.
 * </p>
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 * @see com.sun.jmx.snmp.bgent.SnmpMib
 * @see com.sun.jmx.snmp.bgent.SnmpMibEntry
 * @see com.sun.jmx.snmp.bgent.SnmpTbbleEntryFbctory
 * @see com.sun.jmx.snmp.bgent.SnmpTbbleSupport
 *
 */
@SuppressWbrnings("seribl") // JDK implementbtion clbss
public bbstrbct clbss SnmpMibTbble extends SnmpMibNode
    implements NotificbtionBrobdcbster, Seriblizbble {

    /**
     * Crebte b new <CODE>SnmpMibTbble</CODE> metbdbtb node.
     *
     * <p>
     * @pbrbm mib The SNMP MIB to which the metbdbtb will be linked.
     */
    public SnmpMibTbble(SnmpMib mib) {
        this.theMib= mib;
        setCrebtionEnbbled(fblse);
    }

    // -------------------------------------------------------------------
    // PUBLIC METHODS
    // -------------------------------------------------------------------

    /**
     * This method is invoked when the crebtion of b new entry is requested
     * by b remote SNMP mbnbger.
     * <br>By defbult, remote entry crebtion is disbbled - bnd this method
     * will not be cblled. You cbn dynbmicblly switch the entry crebtion
     * policy by cblling <code>setCrebtionEnbbled(true)</code> bnd <code>
     * setCrebtionEnbbled(fblse)</code> on this object.
     * <p><b><i>
     * This method is cblled internblly by the SNMP runtime bnd you
     * should never need to cbll it directly. </b></i>However you might wbnt
     * to extend it in order to implement your own specific bpplicbtion
     * behbviour, should the defbult behbviour not be bt your convenience.
     * </p>
     * <p>
     * @pbrbm req   The SNMP  subrequest requesting this crebtion
     * @pbrbm rowOid  The OID indexing the conceptubl row (entry) for which
     *                the crebtion wbs requested.
     * @pbrbm depth The position of the columnbr object brc in the OIDs
     *              from the vbrbind list.
     *
     * @exception SnmpStbtusException if the entry cbnnot be crebted.
     */
    public bbstrbct void crebteNewEntry(SnmpMibSubRequest req, SnmpOid rowOid,
                                        int depth)
        throws SnmpStbtusException;

    /**
     * Tell whether the specific version of this metbdbtb generbted
     * by <code>mibgen</code> requires entries to be registered with
     * the MBebnServer. In this cbse bn ObjectNbme will hbve to be
     * pbssed to bddEntry() in order for the tbble to behbve correctly
     * (cbse of the generic metbdbtb).
     * <p>
     * If thbt version of the metbdbtb does not require entry to be
     * registered, then pbssing bn ObjectNbme becomes optionbl (null
     * cbn be pbssed instebd).
     *
     * @return <code>true</code> if registrbtion is required by this
     *         version of the metbdbtb.
     */
    public bbstrbct boolebn isRegistrbtionRequired();

    /**
     * Tell whether b new entry should be crebted when b SET operbtion
     * is received for bn entry thbt does not exist yet.
     *
     * @return true if b new entry must be crebted, fblse otherwise.<br>
     *         [defbult: returns <CODE>fblse</CODE>]
     **/
    public boolebn isCrebtionEnbbled() {
        return crebtionEnbbled;
    }

    /**
     * This method lets you dynbmicblly switch the crebtion policy.
     *
     * <p>
     * @pbrbm remoteCrebtionFlbg Tells whether remote entry crebtion must
     *        be enbbled or disbbled.
     * <ul><li>
     * <CODE>setCrebtionEnbbled(true)</CODE> will enbble remote entry
     *      crebtion vib SET operbtions.</li>
     * <li>
     * <CODE>setCrebtionEnbbled(fblse)</CODE> will disbble remote entry
     *      crebtion vib SET operbtions.</li>
     * <p> By defbult remote entry crebtion vib SET operbtion is disbbled.
     * </p>
     * </ul>
     **/
    public void setCrebtionEnbbled(boolebn remoteCrebtionFlbg) {
        crebtionEnbbled = remoteCrebtionFlbg;
    }

    /**
     * Return <code>true</code> if the conceptubl row contbins b columnbr
     * object used to control crebtion/deletion of rows in this tbble.
     * <p>
     * This  columnbr object cbn be either b vbribble with RowStbtus
     * syntbx bs defined by RFC 2579, or b plbin vbribble whose
     * sembntics is tbble specific.
     * <p>
     * By defbult, this function returns <code>fblse</code>, bnd it is
     * bssumed thbt the tbble hbs no such control vbribble.<br>
     * When <code>mibgen</code> is used over SMIv2 MIBs, it will generbte
     * bn <code>hbsRowStbtus()</code> method returning <code>true</code>
     * for ebch tbble contbining bn object with RowStbtus syntbx.
     * <p>
     * When this method returns <code>fblse</code> the defbult mechbnism
     * for remote entry crebtion is used.
     * Otherwise, crebtion/deletion is performed bs specified
     * by the control vbribble (see getRowAction() for more detbils).
     * <p>
     * This method is cblled internblly when b SET request involving
     * this tbble is processed.
     * <p>
     * If you need to implement b control vbribble which do not use
     * the RowStbtus convention bs defined by RFC 2579, you should
     * subclbss the generbted tbble metbdbtb clbss in order to redefine
     * this method bnd mbke it returns <code>true</code>.<br>
     * You will then hbve to redefine the isRowStbtus(), mbpRowStbtus(),
     * isRowRebdy(), bnd setRowStbtus() methods to suit your specific
     * implementbtion.
     * <p>
     * @return <li><code>true</code> if this tbble contbins b control
     *         vbribble (eg: b vbribble with RFC 2579 RowStbtus syntbx),
     *         </li>
     *         <li><code>fblse</code> if this tbble does not contbin
     *         bny control vbribble.</li>
     *
     **/
    public boolebn hbsRowStbtus() {
        return fblse;
    }

    // ---------------------------------------------------------------------
    //
    // Implements the method defined in SnmpMibNode.
    //
    // ---------------------------------------------------------------------
    /**
     * Generic hbndling of the <CODE>get</CODE> operbtion.
     * <p> The defbult implementbtion of this method is to
     * <ul>
     * <li> check whether the entry exists, bnd if not register bn
     *      exception for ebch vbrbind in the list.
     * <li> cbll the generbted
     *      <CODE>get(req,oid,depth+1)</CODE> method. </li>
     * </ul>
     * <p>
     * <pre>
     * public void get(SnmpMibSubRequest req, int depth)
     *    throws SnmpStbtusException {
     *    boolebn         isnew  = req.isNewEntry();
     *
     *    // if the entry does not exists, then registers bn error for
     *    // ebch vbrbind involved (nb: this should not hbppen, since
     *    // the error should blrebdy hbve been detected ebrlier)
     *    //
     *    if (isnew) {
     *        SnmpVbrBind     vbr = null;
     *        for (Enumerbtion e= req.getElements(); e.hbsMoreElements();) {
     *            vbr = (SnmpVbrBind) e.nextElement();
     *            req.registerGetException(vbr,noSuchNbmeException);
     *        }
     *    }
     *
     *    finbl SnmpOid oid = req.getEntryOid();
     *    get(req,oid,depth+1);
     * }
     * </pre>
     * <p> You should not need to override this method in bny cbses, becbuse
     * it will eventublly cbll
     * <CODE>get(SnmpMibSubRequest req, int depth)</CODE> on the generbted
     * derivbtive of <CODE>SnmpMibEntry</CODE>. If you need to implement
     * specific policies for minimizing the bccesses mbde to some remote
     * underlying resources, or if you need to implement some consistency
     * checks between the different vblues provided in the vbrbind list,
     * you should then rbther override
     * <CODE>get(SnmpMibSubRequest req, int depth)</CODE> on the generbted
     * derivbtive of <CODE>SnmpMibEntry</CODE>.
     * <p>
     *
     */
    @Override
    public void get(SnmpMibSubRequest req, int depth)
        throws SnmpStbtusException {

        finbl boolebn         isnew  = req.isNewEntry();
        finbl SnmpMibSubRequest  r      = req;

        // if the entry does not exists, then registers bn error for
        // ebch vbrbind involved (nb: should not hbppen, the error
        // should hbve been registered ebrlier)
        if (isnew) {
            SnmpVbrBind vbr;
            for (Enumerbtion<SnmpVbrBind> e= r.getElements(); e.hbsMoreElements();) {
                vbr = e.nextElement();
                r.registerGetException(vbr,new SnmpStbtusException(SnmpStbtusException.noSuchInstbnce));
            }
        }

        finbl SnmpOid     oid    = r.getEntryOid();

        // SnmpIndex   index  = buildSnmpIndex(oid.longVblue(fblse), 0);
        // get(req,index,depth+1);
        //
        get(req,oid,depth+1);
    }

    // ---------------------------------------------------------------------
    //
    // Implements the method defined in SnmpMibNode.
    //
    // ---------------------------------------------------------------------
    /**
     * Generic hbndling of the <CODE>check</CODE> operbtion.
     * <p> The defbult implementbtion of this method is to
     * <ul>
     * <li> check whether b new entry must be crebted, bnd if remote
     *      crebtion of entries is enbbled, crebte it. </li>
     * <li> cbll the generbted
     *      <CODE>check(req,oid,depth+1)</CODE> method. </li>
     * </ul>
     * <p>
     * <pre>
     * public void check(SnmpMibSubRequest req, int depth)
     *    throws SnmpStbtusException {
     *    finbl SnmpOid     oid    = req.getEntryOid();
     *    finbl int         bction = getRowAction(req,oid,depth+1);
     *
     *    beginRowAction(req,oid,depth+1,bction);
     *    check(req,oid,depth+1);
     * }
     * </pre>
     * <p> You should not need to override this method in bny cbses, becbuse
     * it will eventublly cbll
     * <CODE>check(SnmpMibSubRequest req, int depth)</CODE> on the generbted
     * derivbtive of <CODE>SnmpMibEntry</CODE>. If you need to implement
     * specific policies for minimizing the bccesses mbde to some remote
     * underlying resources, or if you need to implement some consistency
     * checks between the different vblues provided in the vbrbind list,
     * you should then rbther override
     * <CODE>check(SnmpMibSubRequest req, int depth)</CODE> on the generbted
     * derivbtive of <CODE>SnmpMibEntry</CODE>.
     * <p>
     *
     */
    @Override
    public void check(SnmpMibSubRequest req, int depth)
        throws SnmpStbtusException {
        finbl SnmpOid     oid    = req.getEntryOid();
        finbl int         bction = getRowAction(req,oid,depth+1);

        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, SnmpMibTbble.clbss.getNbme(),
                    "check", "Cblling beginRowAction");
        }

        beginRowAction(req,oid,depth+1,bction);

        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, SnmpMibTbble.clbss.getNbme(),
                    "check",
                    "Cblling check for " + req.getSize() + " vbrbinds");
        }

        check(req,oid,depth+1);

        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, SnmpMibTbble.clbss.getNbme(),
                    "check", "check finished");
        }
    }

    // ---------------------------------------------------------------------
    //
    // Implements the method defined in SnmpMibNode.
    //
    // ---------------------------------------------------------------------
    /**
     * Generic hbndling of the <CODE>set</CODE> operbtion.
     * <p> The defbult implementbtion of this method is to
     * cbll the generbted
     * <CODE>set(req,oid,depth+1)</CODE> method.
     * <p>
     * <pre>
     * public void set(SnmpMibSubRequest req, int depth)
     *    throws SnmpStbtusException {
     *    finbl SnmpOid oid = req.getEntryOid();
     *    finbl int  bction = getRowAction(req,oid,depth+1);
     *
     *    set(req,oid,depth+1);
     *    endRowAction(req,oid,depth+1,bction);
     * }
     * </pre>
     * <p> You should not need to override this method in bny cbses, becbuse
     * it will eventublly cbll
     * <CODE>set(SnmpMibSubRequest req, int depth)</CODE> on the generbted
     * derivbtive of <CODE>SnmpMibEntry</CODE>. If you need to implement
     * specific policies for minimizing the bccesses mbde to some remote
     * underlying resources, or if you need to implement some consistency
     * checks between the different vblues provided in the vbrbind list,
     * you should then rbther override
     * <CODE>set(SnmpMibSubRequest req, int depth)</CODE> on the generbted
     * derivbtive of <CODE>SnmpMibEntry</CODE>.
     * <p>
     *
     */
    @Override
    public void set(SnmpMibSubRequest req, int depth)
        throws SnmpStbtusException {


        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, SnmpMibTbble.clbss.getNbme(),
                    "set", "Entering set");
        }

        finbl SnmpOid     oid    = req.getEntryOid();
        finbl int         bction = getRowAction(req,oid,depth+1);

        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, SnmpMibTbble.clbss.getNbme(),
                    "set", "Cblling set for " + req.getSize() + " vbrbinds");
        }

        set(req,oid,depth+1);

        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, SnmpMibTbble.clbss.getNbme(),
                    "set", "Cblling endRowAction");
        }

        endRowAction(req,oid,depth+1,bction);

        if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
            SNMP_ADAPTOR_LOGGER.logp(Level.FINEST, SnmpMibTbble.clbss.getNbme(),
                    "set", "RowAction finished");
        }

    }

    /**
     * Add b new entry in this <CODE>SnmpMibTbble</CODE>.
     * Also triggers the bddEntryCB() cbllbbck of the
     * {@link com.sun.jmx.snmp.bgent.SnmpTbbleEntryFbctory} interfbce
     * if this node is bound to b fbctory.
     *
     * This method bssumes thbt the given entry will not be registered.
     * If the entry is going to be registered, or if ObjectNbme's bre
     * required, then
     * {@link com.sun.jmx.snmp.bgent.SnmpMibTbble#bddEntry(SnmpOid,
     * ObjectNbme, Object)} should be preferred.
     * <br> This function is mbinly provided for bbckwbrd compbtibility.
     *
     * <p>
     * @pbrbm rowOid The <CODE>SnmpOid</CODE> identifying the tbble
     *               row to be bdded.
     * @pbrbm entry The entry to bdd.
     *
     * @exception SnmpStbtusException The entry couldn't be bdded
     *            bt the position identified by the given
     *            <code>rowOid</code>, or this version of the metbdbtb
     *            requires ObjectNbme's.
     */
     // public void bddEntry(SnmpIndex index, Object entry)
     public void bddEntry(SnmpOid rowOid, Object entry)
        throws SnmpStbtusException {

         bddEntry(rowOid, null, entry);
    }

    /**
     * Add b new entry in this <CODE>SnmpMibTbble</CODE>.
     * Also triggers the bddEntryCB() cbllbbck of the
     * {@link com.sun.jmx.snmp.bgent.SnmpTbbleEntryFbctory} interfbce
     * if this node is bound to b fbctory.
     *
     * <p>
     * @pbrbm oid    The <CODE>SnmpOid</CODE> identifying the tbble
     *               row to be bdded.
     *
     * @pbrbm nbme  The ObjectNbme with which this entry is registered.
     *              This pbrbmeter cbn be omitted if isRegistrbtionRequired()
     *              return fblse.
     *
     * @pbrbm entry The entry to bdd.
     *
     * @exception SnmpStbtusException The entry couldn't be bdded
     *            bt the position identified by the given
     *            <code>rowOid</code>, or if this version of the metbdbtb
     *            requires ObjectNbme's, bnd the given nbme is null.
     */
    // protected synchronized void bddEntry(SnmpIndex index, ObjectNbme nbme,
    //                                      Object entry)
    public synchronized void bddEntry(SnmpOid oid, ObjectNbme nbme,
                                      Object entry)
        throws SnmpStbtusException {

        if (isRegistrbtionRequired() == true && nbme == null)
            throw new SnmpStbtusException(SnmpStbtusException.bbdVblue);

        if (size == 0) {
            //            indexes.bddElement(index);
            // XX oids.bddElement(oid);
            insertOid(0,oid);
            if (entries != null)
                entries.bddElement(entry);
            if (entrynbmes != null)
                entrynbmes.bddElement(nbme);
            size++;

            // triggers cbllbbcks on the entry fbctory
            //
            if (fbctory != null) {
                try {
                    fbctory.bddEntryCb(0,oid,nbme,entry,this);
                } cbtch (SnmpStbtusException x) {
                    removeOid(0);
                    if (entries != null)
                        entries.removeElementAt(0);
                    if (entrynbmes != null)
                        entrynbmes.removeElementAt(0);
                    throw x;
                }
            }

            // sends the notificbtions
            //
            sendNotificbtion(SnmpTbbleEntryNotificbtion.SNMP_ENTRY_ADDED,
                             (new Dbte()).getTime(), entry, nbme);
            return;
        }

        // Get the insertion position ...
        //
        int pos= 0;
        // bug jbw.00356.B : use oid rbther thbn index to get the
        // insertion point.
        //
        pos= getInsertionPoint(oid,true);
        if (pos == size) {
            // Add b new element in the vectors ...
            //
            //            indexes.bddElement(index);
            // XX oids.bddElement(oid);
            insertOid(tbblecount,oid);
            if (entries != null)
                entries.bddElement(entry);
            if (entrynbmes != null)
                entrynbmes.bddElement(nbme);
            size++;
        } else {
            // Insert new element ...
            //
            try {
                //                indexes.insertElementAt(index, pos);
                // XX oids.insertElementAt(oid, pos);
                insertOid(pos,oid);
                if (entries != null)
                    entries.insertElementAt(entry, pos);
                if (entrynbmes != null)
                    entrynbmes.insertElementAt(nbme,pos);
                size++;
            } cbtch(ArrbyIndexOutOfBoundsException e) {
            }
        }

        // triggers cbllbbcks on the entry fbctory
        //
        if (fbctory != null) {
            try {
                fbctory.bddEntryCb(pos,oid,nbme,entry,this);
            } cbtch (SnmpStbtusException x) {
                removeOid(pos);
                if (entries != null)
                    entries.removeElementAt(pos);
                if (entrynbmes != null)
                    entrynbmes.removeElementAt(pos);
                throw x;
            }
        }

        // sends the notificbtions
        //
        sendNotificbtion(SnmpTbbleEntryNotificbtion.SNMP_ENTRY_ADDED,
                         (new Dbte()).getTime(), entry, nbme);
    }

    /**
     * Remove the specified entry from the tbble.
     * Also triggers the removeEntryCB() cbllbbck of the
     * {@link com.sun.jmx.snmp.bgent.SnmpTbbleEntryFbctory} interfbce
     * if this node is bound to b fbctory.
     *
     * <p>
     * @pbrbm rowOid The <CODE>SnmpOid</CODE> identifying the tbble
     *               row to remove.
     *
     * @pbrbm entry The entry to be removed. This pbrbmeter is not used
     *              internblly, it is simply pbssed blong to the
     *              removeEntryCB() cbllbbck.
     *
     * @exception SnmpStbtusException if the specified entry couldn't
     *            be removed (if the given <code>rowOid</code> is not
     *            vblid for instbnce).
     */
    public synchronized void removeEntry(SnmpOid rowOid, Object entry)
        throws SnmpStbtusException {
        int pos = findObject(rowOid);
        if (pos == -1)
            return;
        removeEntry(pos,entry);
    }

    /**
     * Remove the specified entry from the tbble.
     * Also triggers the removeEntryCB() cbllbbck of the
     * {@link com.sun.jmx.snmp.bgent.SnmpTbbleEntryFbctory} interfbce
     * if this node is bound to b fbctory.
     *
     * <p>
     * @pbrbm rowOid The <CODE>SnmpOid</CODE> identifying the tbble
     *               row to remove.
     *
     * @exception SnmpStbtusException if the specified entry couldn't
     *            be removed (if the given <code>rowOid</code> is not
     *            vblid for instbnce).
     */
    public void removeEntry(SnmpOid rowOid)
        throws SnmpStbtusException {
        int pos = findObject(rowOid);
        if (pos == -1)
            return;
        removeEntry(pos,null);
    }

    /**
     * Remove the specified entry from the tbble.
     * Also triggers the removeEntryCB() cbllbbck of the
     * {@link com.sun.jmx.snmp.bgent.SnmpTbbleEntryFbctory} interfbce
     * if this node is bound to b fbctory.
     *
     * <p>
     * @pbrbm pos The position of the entry in the tbble.
     *
     * @pbrbm entry The entry to be removed. This pbrbmeter is not used
     *              internblly, it is simply pbssed blong to the
     *              removeEntryCB() cbllbbck.
     *
     * @exception SnmpStbtusException if the specified entry couldn't
     *            be removed.
     */
    public synchronized void removeEntry(int pos, Object entry)
        throws SnmpStbtusException {
        if (pos == -1)
            return;
        if (pos >= size) return;

        Object obj = entry;
        if (entries != null && entries.size() > pos) {
            obj = entries.elementAt(pos);
            entries.removeElementAt(pos);
        }

        ObjectNbme nbme = null;
        if (entrynbmes != null && entrynbmes.size() > pos) {
            nbme = entrynbmes.elementAt(pos);
            entrynbmes.removeElementAt(pos);
        }

        finbl SnmpOid rowOid = tbbleoids[pos];
        removeOid(pos);
        size --;

        if (obj == null) obj = entry;

        if (fbctory != null)
            fbctory.removeEntryCb(pos,rowOid,nbme,obj,this);

        sendNotificbtion(SnmpTbbleEntryNotificbtion.SNMP_ENTRY_REMOVED,
                         (new Dbte()).getTime(), obj, nbme);
    }

    /**
     * Get the entry corresponding to the specified rowOid.
     *
     * <p>
     * @pbrbm rowOid The <CODE>SnmpOid</CODE> identifying the
     *        row to be retrieved.
     *
     * @return The entry.
     *
     * @exception SnmpStbtusException There is no entry with the specified
     *      <code>rowOid</code> in the tbble.
     */
    public synchronized Object getEntry(SnmpOid rowOid)
        throws SnmpStbtusException {
        int pos= findObject(rowOid);
        if (pos == -1)
            throw new SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);
        return entries.elementAt(pos);
    }

    /**
     * Get the ObjectNbme of the entry corresponding to the
     * specified rowOid.
     * The result of this method is only mebningful if
     * isRegistrbtionRequired() yields true.
     *
     * <p>
     * @pbrbm rowOid The <CODE>SnmpOid</CODE> identifying the tbble
     *        row whose ObjectNbme we wbnt to retrieve.
     *
     * @return The object nbme of the entry.
     *
     * @exception SnmpStbtusException There is no entry with the specified
     *      <code>rowOid</code> in the tbble.
     */
    public synchronized ObjectNbme getEntryNbme(SnmpOid rowOid)
        throws SnmpStbtusException {
        int pos = findObject(rowOid);
        if (entrynbmes == null) return null;
        if (pos == -1 || pos >= entrynbmes.size())
            throw new SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);
        return entrynbmes.elementAt(pos);
    }

    /**
     * Return the entries stored in this tbble <CODE>SnmpMibTbble</CODE>.
     * <p>
     * If the subclbss generbted by mibgen uses the generic wby to bccess
     * the entries (i.e. if it goes through the MBebnServer) then some of
     * the entries mby be <code>null</code>. It bll depends whether b non
     * <code>null</code> entry wbs pbssed to bddEntry().<br>
     * Otherwise, if it uses the stbndbrd wby (bccess the entry directly
     * through their stbndbrd MBebn interfbce) this brrby will contbin bll
     * the entries.
     * <p>
     * @return The entries brrby.
     */
    public Object[] getBbsicEntries() {
        Object[] brrby= new Object[size];
        entries.copyInto(brrby);
        return brrby;
    }

    /**
     * Get the size of the tbble.
     *
     * @return The number of entries currently registered in this tbble.
     */
    public int getSize() {
        return size;
    }

    // EVENT STUFF
    //------------

    /**
     * Enbble to bdd bn SNMP entry listener to this
     * <CODE>SnmpMibTbble</CODE>.
     *
     * <p>
     * @pbrbm listener The listener object which will hbndle the
     *    notificbtions emitted by the registered MBebn.
     *
     * @pbrbm filter The filter object. If filter is null, no filtering
     *    will be performed before hbndling notificbtions.
     *
     * @pbrbm hbndbbck The context to be sent to the listener when b
     *    notificbtion is emitted.
     *
     * @exception IllegblArgumentException Listener pbrbmeter is null.
     */
    @Override
    public synchronized void
        bddNotificbtionListener(NotificbtionListener listener,
                                NotificbtionFilter filter, Object hbndbbck)  {

        // Check listener
        //
        if (listener == null) {
            throw new jbvb.lbng.IllegblArgumentException
                ("Listener cbn't be null") ;
        }

        // looking for listener in hbndbbckTbble
        //
        Vector<Object> hbndbbckList = hbndbbckTbble.get(listener) ;
        Vector<NotificbtionFilter> filterList = filterTbble.get(listener) ;
        if ( hbndbbckList == null ) {
            hbndbbckList = new Vector<>() ;
            filterList = new Vector<>() ;
            hbndbbckTbble.put(listener, hbndbbckList) ;
            filterTbble.put(listener, filterList) ;
        }

        // Add the hbndbbck bnd the filter
        //
        hbndbbckList.bddElement(hbndbbck) ;
        filterList.bddElement(filter) ;
    }

    /**
     * Enbble to remove bn SNMP entry listener from this
     * <CODE>SnmpMibTbble</CODE>.
     *
     * @pbrbm listener The listener object which will hbndle the
     *    notificbtions emitted by the registered MBebn.
     *    This method will remove bll the informbtion relbted to this
     *    listener.
     *
     * @exception ListenerNotFoundException The listener is not registered
     *    in the MBebn.
     */
    @Override
    public synchronized void
        removeNotificbtionListener(NotificbtionListener listener)
        throws ListenerNotFoundException {

        // looking for listener in hbndbbckTbble
        //
        jbvb.util.Vector<?> hbndbbckList = hbndbbckTbble.get(listener) ;
        if ( hbndbbckList == null ) {
            throw new ListenerNotFoundException("listener");
        }

        // If hbndbbck is null, remove the listener entry
        //
        hbndbbckTbble.remove(listener) ;
        filterTbble.remove(listener) ;
    }

    /**
     * Return b <CODE>NotificbtionInfo</CODE> object contbining the
     * notificbtion clbss bnd the notificbtion type sent by the
     * <CODE>SnmpMibTbble</CODE>.
     */
    @Override
    public MBebnNotificbtionInfo[] getNotificbtionInfo() {

        String[] types = {SnmpTbbleEntryNotificbtion.SNMP_ENTRY_ADDED,
                          SnmpTbbleEntryNotificbtion.SNMP_ENTRY_REMOVED};

        MBebnNotificbtionInfo[] notifsInfo = {
            new MBebnNotificbtionInfo
            (types, "com.sun.jmx.snmp.bgent.SnmpTbbleEntryNotificbtion",
             "Notificbtions sent by the SnmpMibTbble")
        };

        return notifsInfo;
    }


    /**
     * Register the fbctory through which tbble entries should
     * be crebted when remote entry crebtion is enbbled.
     *
     * <p>
     * @pbrbm fbctory The
     *        {@link com.sun.jmx.snmp.bgent.SnmpTbbleEntryFbctory} through
     *        which entries will be crebted when b remote SNMP mbnbger
     *        request the crebtion of b new entry vib bn SNMP SET request.
     */
    public void registerEntryFbctory(SnmpTbbleEntryFbctory fbctory) {
        this.fbctory = fbctory;
    }

    // ----------------------------------------------------------------------
    // PROTECTED METHODS - RowStbtus
    // ----------------------------------------------------------------------

    /**
     * Return true if the columnbr object identified by <code>vbr</code>
     * is used to control the bddition/deletion of rows in this tbble.
     *
     * <p>
     * By defbult, this method bssumes thbt there is no control vbribble
     * bnd blwbys return <code>fblse</code>
     * <p>
     * If this tbble wbs defined using SMIv2, bnd if it contbins b
     * control vbribble with RowStbtus syntbx, <code>mibgen</code>
     * will generbte b non defbult implementbtion for this method
     * thbt will identify the RowStbtus control vbribble.
     * <p>
     * You will hbve to redefine this method if you need to implement
     * control vbribbles thbt do not conform to RFC 2579 RowStbtus
     * TEXTUAL-CONVENTION.
     * <p>
     * @pbrbm rowOid The <CODE>SnmpOid</CODE> identifying the tbble
     *               row involved in the operbtion.
     *
     * @pbrbm vbr The OID brc identifying the involved columnbr object.
     *
     * @pbrbm userDbtb A contextubl object contbining user-dbtb.
     *        This object is bllocbted through the <code>
     *        {@link com.sun.jmx.snmp.bgent.SnmpUserDbtbFbctory}</code>
     *        for ebch incoming SNMP request.
     *
     **/
    protected boolebn isRowStbtus(SnmpOid rowOid, long vbr,
                                    Object  userDbtb) {
        return fblse;
    }


    /**
     * Return the RowStbtus code vblue specified in this request.
     * <p>
     * The RowStbtus code vblue should be one of the vblues defined
     * by {@link com.sun.jmx.snmp.EnumRowStbtus}. These codes correspond
     * to RowStbtus codes bs defined in RFC 2579, plus the <i>unspecified</i>
     * vblue which is SNMP Runtime specific.
     * <p>
     *
     * @pbrbm req    The sub-request thbt must be hbndled by this node.
     *
     * @pbrbm rowOid The <CODE>SnmpOid</CODE> identifying the tbble
     *               row involved in the operbtion.
     *
     * @pbrbm depth  The depth rebched in the OID tree.
     *
     * @return The RowStbtus code specified in this request, if bny:
     * <ul>
     * <li>If the specified row does not exist bnd this tbble do
     *     not use bny vbribble to control crebtion/deletion of
     *     rows, then defbult crebtion mechbnism is bssumed bnd
     *     <i>crebteAndGo</i> is returned</li>
     * <li>Otherwise, if the row exists bnd this tbble do not use bny
     *     vbribble to control crebtion/deletion of rows,
     *     <i>unspecified</i> is returned.</li>
     * <li>Otherwise, if the request does not contbin the control vbribble,
     *     <i>unspecified</i> is returned.</li>
     * <li>Otherwise, mbpRowStbtus() is cblled to extrbct the RowStbtus
     *     code from the SnmpVbrBind thbt contbins the control vbribble.</li>
     * </ul>
     *
     * @exception SnmpStbtusException if the vblue of the control vbribble
     *            could not be mbpped to b RowStbtus code.
     *
     * @see com.sun.jmx.snmp.EnumRowStbtus
     **/
    protected int getRowAction(SnmpMibSubRequest req, SnmpOid rowOid,
                               int depth)
        throws SnmpStbtusException {
        finbl boolebn     isnew  = req.isNewEntry();
        finbl SnmpVbrBind vb = req.getRowStbtusVbrBind();
        if (vb == null) {
            if (isnew && ! hbsRowStbtus())
                return EnumRowStbtus.crebteAndGo;
            else return EnumRowStbtus.unspecified;
        }

        try {
            return mbpRowStbtus(rowOid, vb, req.getUserDbtb());
        } cbtch( SnmpStbtusException x) {
            checkRowStbtusFbil(req, x.getStbtus());
        }
        return EnumRowStbtus.unspecified;
    }

    /**
     * Mbp the vblue of the <code>vbstbtus</code> vbrbind to the
     * corresponding RowStbtus code defined in
     * {@link com.sun.jmx.snmp.EnumRowStbtus}.
     * These codes correspond to RowStbtus codes bs defined in RFC 2579,
     * plus the <i>unspecified</i> vblue which is SNMP Runtime specific.
     * <p>
     * By defbult, this method bssumes thbt the control vbribble is
     * bn Integer, bnd it simply returns its vblue without further
     * bnblysis.
     * <p>
     * If this tbble wbs defined using SMIv2, bnd if it contbins b
     * control vbribble with RowStbtus syntbx, <code>mibgen</code>
     * will generbte b non defbult implementbtion for this method.
     * <p>
     * You will hbve to redefine this method if you need to implement
     * control vbribbles thbt do not conform to RFC 2579 RowStbtus
     * TEXTUAL-CONVENTION.
     *
     * <p>
     * @pbrbm rowOid The <CODE>SnmpOid</CODE> identifying the tbble
     *               row involved in the operbtion.
     *
     * @pbrbm vbstbtus The SnmpVbrBind contbining the vblue of the control
     *           vbribble, bs identified by the isRowStbtus() method.
     *
     * @pbrbm userDbtb A contextubl object contbining user-dbtb.
     *        This object is bllocbted through the <code>
     *        {@link com.sun.jmx.snmp.bgent.SnmpUserDbtbFbctory}</code>
     *        for ebch incoming SNMP request.
     *
     * @return The RowStbtus code mbpped from the vblue contbined
     *     in <code>vbstbtus</code>.
     *
     * @exception SnmpStbtusException if the vblue of the control vbribble
     *            could not be mbpped to b RowStbtus code.
     *
     * @see com.sun.jmx.snmp.EnumRowStbtus
     **/
    protected int mbpRowStbtus(SnmpOid rowOid, SnmpVbrBind vbstbtus,
                               Object userDbtb)
        throws SnmpStbtusException {
        finbl SnmpVblue rsvblue = vbstbtus.vblue;

        if (rsvblue instbnceof SnmpInt)
            return ((SnmpInt)rsvblue).intVblue();
        else
            throw new SnmpStbtusException(
                       SnmpStbtusException.snmpRspInconsistentVblue);
    }

    /**
     * Set the control vbribble to the specified <code>newStbtus</code>
     * vblue.
     *
     * <p>
     * This method mbps the given <code>newStbtus</code> to the bppropribte
     * vblue for the control vbribble, then sets the control vbribble in
     * the entry identified by <code>rowOid</code>. It returns the new
     * vblue of the control vbribble.
     * <p>
     * By defbult, it is bssumed thbt there is no control vbribble so this
     * method does nothing bnd simply returns <code>null</code>.
     * <p>
     * If this tbble wbs defined using SMIv2, bnd if it contbins b
     * control vbribble with RowStbtus syntbx, <code>mibgen</code>
     * will generbte b non defbult implementbtion for this method.
     * <p>
     * You will hbve to redefine this method if you need to implement
     * control vbribbles thbt do not conform to RFC 2579 RowStbtus
     * TEXTUAL-CONVENTION.
     *
     * <p>
     * @pbrbm rowOid The <CODE>SnmpOid</CODE> identifying the tbble
     *               row involved in the operbtion.
     *
     * @pbrbm newStbtus The new stbtus for the row: one of the
     *        RowStbtus code defined in
     *        {@link com.sun.jmx.snmp.EnumRowStbtus}. These codes
     *        correspond to RowStbtus codes bs defined in RFC 2579,
     *        plus the <i>unspecified</i> vblue which is SNMP Runtime specific.
     *
     * @pbrbm userDbtb A contextubl object contbining user-dbtb.
     *        This object is bllocbted through the <code>
     *        {@link com.sun.jmx.snmp.bgent.SnmpUserDbtbFbctory}</code>
     *        for ebch incoming SNMP request.
     *
     * @return The new vblue of the control vbribble (usublly
     *         <code>new SnmpInt(newStbtus)</code>) or <code>null</code>
     *         if the tbble do not hbve bny control vbribble.
     *
     * @exception SnmpStbtusException If the given <code>newStbtus</code>
     *            could not be set on the specified entry, or if the
     *            given <code>newStbtus</code> is not vblid.
     *
     * @see com.sun.jmx.snmp.EnumRowStbtus
     **/
    protected SnmpVblue setRowStbtus(SnmpOid rowOid, int newStbtus,
                                     Object userDbtb)
        throws SnmpStbtusException {
        return null;
    }

    /**
     * Tell whether the specified row is rebdy bnd cbn be put in the
     * <i>notInService</i> stbte.
     * <p>
     * This method is cblled only once, bfter bll the vbrbind hbve been
     * set on b new entry for which <i>crebteAndWbit</i> wbs specified.
     * <p>
     * If the entry is not yet rebdy, this method should return fblse.
     * It will then be the responsibility of the entry to switch its
     * own stbte to <i>notInService</i> when it becomes rebdy.
     * No further cbll to <code>isRowRebdy()</code> will be mbde.
     * <p>
     * By defbult, this method blwbys return true. <br>
     * <code>mibgen</code> will not generbte bny specific implementbtion
     * for this method - mebning thbt by defbult, b row crebted using
     * <i>crebteAndWbit</i> will blwbys be plbced in <i>notInService</i>
     * stbte bt the end of the request.
     * <p>
     * If this tbble wbs defined using SMIv2, bnd if it contbins b
     * control vbribble with RowStbtus syntbx, <code>mibgen</code>
     * will generbte bn implementbtion for this method thbt will
     * delegbte the work to the metbdbtb clbss modelling the conceptubl
     * row, so thbt you cbn override the defbult behbviour by subclbssing
     * thbt metbdbtb clbss.
     * <p>
     * You will hbve to redefine this method if this defbult mechbnism
     * does not suit your needs.
     *
     * <p>
     * @pbrbm rowOid The <CODE>SnmpOid</CODE> identifying the tbble
     *               row involved in the operbtion.
     *
     * @pbrbm userDbtb A contextubl object contbining user-dbtb.
     *        This object is bllocbted through the <code>
     *        {@link com.sun.jmx.snmp.bgent.SnmpUserDbtbFbctory}</code>
     *        for ebch incoming SNMP request.
     *
     * @return <code>true</code> if the row cbn be plbced in
     *         <i>notInService</i> stbte.
     *
     * @exception SnmpStbtusException An error occurred while trying
     *            to retrieve the row stbtus, bnd the operbtion should
     *            be bborted.
     *
     * @see com.sun.jmx.snmp.EnumRowStbtus
     **/
    protected boolebn isRowRebdy(SnmpOid rowOid, Object userDbtb)
        throws SnmpStbtusException {
        return true;
    }

    /**
     * Check whether the control vbribble of the given row cbn be
     * switched to the new specified <code>newStbtus</code>.
     * <p>
     * This method is cblled during the <i>check</i> phbse of b SET
     * request when the control vbribble specifies <i>bctive</i> or
     * <i>notInService</i>.
     * <p>
     * By defbult it is bssumed thbt nothing prevents putting the
     * row in the requested stbte, bnd this method does nothing.
     * It is simply provided bs b hook so thbt specific checks cbn
     * be implemented.
     * <p>
     * Note thbt if the bctubl row deletion fbils bfterwbrd, the
     * btomicity of the request is no longer gubrbnteed.
     *
     * <p>
     * @pbrbm req    The sub-request thbt must be hbndled by this node.
     *
     * @pbrbm rowOid The <CODE>SnmpOid</CODE> identifying the tbble
     *               row involved in the operbtion.
     *
     * @pbrbm depth  The depth rebched in the OID tree.
     *
     * @pbrbm newStbtus The new stbtus for the row: one of the
     *        RowStbtus code defined in
     *        {@link com.sun.jmx.snmp.EnumRowStbtus}. These codes
     *        correspond to RowStbtus codes bs defined in RFC 2579,
     *        plus the <i>unspecified</i> vblue which is SNMP Runtime specific.
     *
     * @exception SnmpStbtusException if switching to this new stbte
     *            would fbil.
     *
     **/
    protected void checkRowStbtusChbnge(SnmpMibSubRequest req,
                                        SnmpOid rowOid, int depth,
                                        int newStbtus)
        throws SnmpStbtusException {

    }

    /**
     * Check whether the specified row cbn be removed from the tbble.
     * <p>
     * This method is cblled during the <i>check</i> phbse of b SET
     * request when the control vbribble specifies <i>destroy</i>
     * <p>
     * By defbult it is bssumed thbt nothing prevents row deletion
     * bnd this method does nothing. It is simply provided bs b hook
     * so thbt specific checks cbn be implemented.
     * <p>
     * Note thbt if the bctubl row deletion fbils bfterwbrd, the
     * btomicity of the request is no longer gubrbnteed.
     *
     * <p>
     * @pbrbm req    The sub-request thbt must be hbndled by this node.
     *
     * @pbrbm rowOid The <CODE>SnmpOid</CODE> identifying the tbble
     *               row involved in the operbtion.
     *
     * @pbrbm depth  The depth rebched in the OID tree.
     *
     * @exception SnmpStbtusException if the row deletion must be
     *            rejected.
     **/
    protected void checkRemoveTbbleRow(SnmpMibSubRequest req, SnmpOid rowOid,
                                       int depth)
        throws SnmpStbtusException {

    }

    /**
     * Remove b tbble row upon b remote mbnbger request.
     *
     * This method is cblled internblly when <code>getRowAction()</code>
     * yields <i>destroy</i> - i.e.: it is only cblled when b remote
     * mbnbger requests the removbl of b tbble row.<br>
     * You should never need to cbll this function directly.
     * <p>
     * By defbult, this method simply cblls <code>removeEntry(rowOid)
     * </code>.
     * <p>
     * You cbn redefine this method if you need to implement some
     * specific behbviour when b remote row deletion is invoked.
     * <p>
     * Note thbt specific checks should not be implemented in this
     * method, but rbther in <code>checkRemoveTbbleRow()</code>.
     * If <code>checkRemoveTbbleRow()</code> succeeds bnd this method
     * fbils bfterwbrd, the btomicity of the originbl SET request cbn no
     * longer be gubrbnteed.
     * <p>
     *
     * @pbrbm req    The sub-request thbt must be hbndled by this node.
     *
     * @pbrbm rowOid The <CODE>SnmpOid</CODE> identifying the tbble
     *               row involved in the operbtion.
     *
     * @pbrbm depth  The depth rebched in the OID tree.
     *
     * @exception SnmpStbtusException if the bctubl row deletion fbils.
     *            This should not hbppen since it would brebk the
     *            btomicity of the SET request. Specific checks should
     *            be implemented in <code>checkRemoveTbbleRow()</code>
     *            if needed. If the entry does not exists, no exception
     *            is generbted bnd the method simply returns.
     *
     **/
    protected void removeTbbleRow(SnmpMibSubRequest req, SnmpOid rowOid,
                                  int depth)
        throws SnmpStbtusException {

        removeEntry(rowOid);
    }

    /**
     * This method tbkes cbre of initibl RowStbtus hbndling during the
     * check() phbse of b SET request.
     *
     * In pbrticulbr it will:
     * <ul><li>check thbt the given <code>rowAction</code> returned by
     *         <code>getRowAction()</code> is vblid.</li>
     * <li>Then depending on the <code>rowAction</code> specified it will:
     *     <ul><li>either cbll <code>crebteNewEntry()</code> (<code>
     *         rowAction = <i>crebteAndGo</i> or <i>crebteAndWbit</i>
     *         </code>),</li>
     *     <li>or cbll <code>checkRemoveTbbleRow()</code> (<code>
     *         rowAction = <i>destroy</i></code>),</li>
     *     <li>or cbll <code>checkRowStbtusChbnge()</code> (<code>
     *         rowAction = <i>bctive</i> or <i>notInService</i></code>),</li>
     *     <li>or generbte b SnmpStbtusException if the pbssed <code>
     *         rowAction</code> is not correct.</li>
     * </ul></li></ul>
     * <p>
     * In principle, you should not need to redefine this method.
     * <p>
     * <code>beginRowAction()</code> is cblled during the check phbse
     * of b SET request, before bctubl checking on the vbrbind list
     * is performed.
     *
     * <p>
     * @pbrbm req    The sub-request thbt must be hbndled by this node.
     *
     * @pbrbm rowOid The <CODE>SnmpOid</CODE> identifying the tbble
     *               row involved in the operbtion.
     *
     * @pbrbm depth  The depth rebched in the OID tree.
     *
     * @pbrbm rowAction The requested bction bs returned by <code>
     *        getRowAction()</code>: one of the RowStbtus codes defined in
     *        {@link com.sun.jmx.snmp.EnumRowStbtus}. These codes
     *        correspond to RowStbtus codes bs defined in RFC 2579,
     *        plus the <i>unspecified</i> vblue which is SNMP Runtime specific.
     *
     * @exception SnmpStbtusException if the specified <code>rowAction</code>
     *            is not vblid or cbnnot be executed.
     *            This should not hbppen since it would brebk the
     *            btomicity of the SET request. Specific checks should
     *            be implemented in <code>beginRowAction()</code> if needed.
     *
     * @see com.sun.jmx.snmp.EnumRowStbtus
     **/
    protected synchronized void beginRowAction(SnmpMibSubRequest req,
                              SnmpOid rowOid, int depth, int rowAction)
        throws SnmpStbtusException {
        finbl boolebn     isnew  = req.isNewEntry();
        finbl SnmpOid     oid    = rowOid;
        finbl int         bction = rowAction;

        switch (bction) {
        cbse EnumRowStbtus.unspecified:
            if (isnew) {
                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINEST,
                            SnmpMibTbble.clbss.getNbme(),
                            "beginRowAction", "Fbiled to crebte row[" +
                            rowOid + "] : RowStbtus = unspecified");
                }
                checkRowStbtusFbil(req,SnmpStbtusException.snmpRspNoAccess);
            }
            brebk;
        cbse EnumRowStbtus.crebteAndGo:
        cbse EnumRowStbtus.crebteAndWbit:
            if (isnew) {
                if (isCrebtionEnbbled()) {
                    if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINEST,
                                SnmpMibTbble.clbss.getNbme(),
                                "beginRowAction", "Crebting row[" + rowOid +
                                "] : RowStbtus = crebteAndGo | crebteAndWbit");
                    }
                    crebteNewEntry(req,oid,depth);
                } else {
                    if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                        SNMP_ADAPTOR_LOGGER.logp(Level.FINEST,
                                SnmpMibTbble.clbss.getNbme(),
                                "beginRowAction", "Cbn't crebte row[" + rowOid +
                                "] : RowStbtus = crebteAndGo | crebteAndWbit " +
                                "but crebtion is disbbled");
                    }
                    checkRowStbtusFbil(req,
                       SnmpStbtusException.snmpRspNoAccess);
                }
            } else {
                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINEST,
                            SnmpMibTbble.clbss.getNbme(),
                            "beginRowAction", "Cbn't crebte row[" + rowOid +
                            "] : RowStbtus = crebteAndGo | crebteAndWbit " +
                            "but row blrebdy exists");
                }
                checkRowStbtusFbil(req,
                       SnmpStbtusException.snmpRspInconsistentVblue);
            }
            brebk;
        cbse EnumRowStbtus.destroy:
            if (isnew) {
                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINEST,
                            SnmpMibTbble.clbss.getNbme(),
                            "beginRowAction",
                            "Wbrning: cbn't destroy row[" + rowOid +
                            "] : RowStbtus = destroy but row does not exist");
                }
            } else if (!isCrebtionEnbbled()) {
                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINEST,
                            SnmpMibTbble.clbss.getNbme(),
                            "beginRowAction",
                            "Cbn't destroy row[" + rowOid + "] : " +
                            "RowStbtus = destroy but crebtion is disbbled");
                }
                checkRowStbtusFbil(req,SnmpStbtusException.snmpRspNoAccess);
            }
            checkRemoveTbbleRow(req,rowOid,depth);
            brebk;
        cbse EnumRowStbtus.bctive:
        cbse EnumRowStbtus.notInService:
            if (isnew) {
                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINEST,
                            SnmpMibTbble.clbss.getNbme(),
                            "beginRowAction", "Cbn't switch stbte of row[" +
                            rowOid + "] : specified RowStbtus = bctive | " +
                            "notInService but row does not exist");
                }
                checkRowStbtusFbil(req,
                        SnmpStbtusException.snmpRspInconsistentVblue);
            }
            checkRowStbtusChbnge(req,rowOid,depth,bction);
            brebk;
        cbse EnumRowStbtus.notRebdy:
        defbult:
            if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINEST,
                        SnmpMibTbble.clbss.getNbme(),
                        "beginRowAction", "Invblid RowStbtus vblue for row[" +
                        rowOid + "] : specified RowStbtus = " + bction);
            }
            checkRowStbtusFbil(req,
                    SnmpStbtusException.snmpRspInconsistentVblue);
        }
    }

    /**
     * This method tbkes cbre of finbl RowStbtus hbndling during the
     * set() phbse of b SET request.
     *
     * In pbrticulbr it will:
     *     <ul><li>either cbll <code>setRowStbtus(<i>bctive</i>)</code>
     *         (<code> rowAction = <i>crebteAndGo</i> or <i>bctive</i>
     *         </code>),</li>
     *     <li>or cbll <code>setRowStbtus(<i>notInService</i> or <i>
     *         notRebdy</i>)</code> depending on the result of <code>
     *         isRowRebdy()</code> (<code>rowAction = <i>crebteAndWbit</i>
     *         </code>),</li>
     *     <li>or cbll <code>setRowStbtus(<i>notInService</i>)</code>
     *         (<code> rowAction = <i>notInService</i></code>),
     *     <li>or cbll <code>removeTbbleRow()</code> (<code>
     *         rowAction = <i>destroy</i></code>),</li>
     *     <li>or generbte b SnmpStbtusException if the pbssed <code>
     *         rowAction</code> is not correct. This should be bvoided
     *         since it would brebk SET request btomicity</li>
     *     </ul>
     * <p>
     * In principle, you should not need to redefine this method.
     * <p>
     * <code>endRowAction()</code> is cblled during the set() phbse
     * of b SET request, bfter the bctubl set() on the vbrbind list
     * hbs been performed. The vbrbind contbining the control vbribble
     * is updbted with the vblue returned by setRowStbtus() (if it is
     * not <code>null</code>).
     *
     * <p>
     * @pbrbm req    The sub-request thbt must be hbndled by this node.
     *
     * @pbrbm rowOid The <CODE>SnmpOid</CODE> identifying the tbble
     *               row involved in the operbtion.
     *
     * @pbrbm depth  The depth rebched in the OID tree.
     *
     * @pbrbm rowAction The requested bction bs returned by <code>
     *        getRowAction()</code>: one of the RowStbtus codes defined in
     *        {@link com.sun.jmx.snmp.EnumRowStbtus}. These codes
     *        correspond to RowStbtus codes bs defined in RFC 2579,
     *        plus the <i>unspecified</i> vblue which is SNMP Runtime specific.
     *
     * @exception SnmpStbtusException if the specified <code>rowAction</code>
     *            is not vblid.
     *
     * @see com.sun.jmx.snmp.EnumRowStbtus
     **/
    protected void endRowAction(SnmpMibSubRequest req, SnmpOid rowOid,
                               int depth, int rowAction)
        throws SnmpStbtusException {
        finbl boolebn     isnew  = req.isNewEntry();
        finbl SnmpOid     oid    = rowOid;
        finbl int         bction = rowAction;
        finbl Object      dbtb   = req.getUserDbtb();
        SnmpVblue         vblue  = null;

        switch (bction) {
        cbse EnumRowStbtus.unspecified:
            brebk;
        cbse EnumRowStbtus.crebteAndGo:
            if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINEST,
                        SnmpMibTbble.clbss.getNbme(),
                        "endRowAction", "Setting RowStbtus to 'bctive' " +
                        "for row[" + rowOid + "] : requested RowStbtus = " +
                        "crebteAndGo");
            }
            vblue = setRowStbtus(oid,EnumRowStbtus.bctive,dbtb);
            brebk;
        cbse EnumRowStbtus.crebteAndWbit:
            if (isRowRebdy(oid,dbtb)) {
                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINEST,
                            SnmpMibTbble.clbss.getNbme(),
                            "endRowAction",
                            "Setting RowStbtus to 'notInService' for row[" +
                            rowOid + "] : requested RowStbtus = crebteAndWbit");
                }
                vblue = setRowStbtus(oid,EnumRowStbtus.notInService,dbtb);
            } else {
                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINEST,
                            SnmpMibTbble.clbss.getNbme(),
                            "endRowAction", "Setting RowStbtus to 'notRebdy' " +
                            "for row[" + rowOid + "] : requested RowStbtus = " +
                            "crebteAndWbit");
                }
                vblue = setRowStbtus(oid,EnumRowStbtus.notRebdy,dbtb);
            }
            brebk;
        cbse EnumRowStbtus.destroy:
            if (isnew) {
                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINEST,
                            SnmpMibTbble.clbss.getNbme(),
                            "endRowAction",
                            "Wbrning: requested RowStbtus = destroy, " +
                            "but row[" + rowOid + "] does not exist");
                }
            } else {
                if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                    SNMP_ADAPTOR_LOGGER.logp(Level.FINEST,
                            SnmpMibTbble.clbss.getNbme(),
                            "endRowAction", "Destroying row[" + rowOid +
                            "] : requested RowStbtus = destroy");
                }
            }
            removeTbbleRow(req,oid,depth);
            brebk;
        cbse EnumRowStbtus.bctive:
            if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINEST,
                        SnmpMibTbble.clbss.getNbme(),
                        "endRowAction",
                        "Setting RowStbtus to 'bctive' for row[" +
                        rowOid + "] : requested RowStbtus = bctive");
            }
            vblue = setRowStbtus(oid,EnumRowStbtus.bctive,dbtb);
            brebk;
        cbse EnumRowStbtus.notInService:
            if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINEST,
                        SnmpMibTbble.clbss.getNbme(),
                        "endRowAction",
                        "Setting RowStbtus to 'notInService' for row[" +
                        rowOid + "] : requested RowStbtus = notInService");
            }
            vblue = setRowStbtus(oid,EnumRowStbtus.notInService,dbtb);
            brebk;
        cbse EnumRowStbtus.notRebdy:
        defbult:
            if (SNMP_ADAPTOR_LOGGER.isLoggbble(Level.FINEST)) {
                SNMP_ADAPTOR_LOGGER.logp(Level.FINEST,
                        SnmpMibTbble.clbss.getNbme(),
                        "endRowAction", "Invblid RowStbtus vblue for row[" +
                        rowOid + "] : specified RowStbtus = " + bction);
            }
            setRowStbtusFbil(req,
                          SnmpStbtusException.snmpRspInconsistentVblue);
        }
        if (vblue != null) {
            finbl SnmpVbrBind vb = req.getRowStbtusVbrBind();
            if (vb != null) vb.vblue = vblue;
        }
    }

    // -------------------------------------------------------------------
    // PROTECTED METHODS - get next
    // -------------------------------------------------------------------

    /**
     * Return the next OID brc corresponding to b rebdbble columnbr
     * object in the underlying entry OBJECT-TYPE, possibly skipping over
     * those objects thbt must not or cbnnot be returned.
     * Cblls {@link
     * #getNextVbrEntryId(com.sun.jmx.snmp.SnmpOid,long,jbvb.lbng.Object)},
     * until
     * {@link #skipEntryVbribble(com.sun.jmx.snmp.SnmpOid,long,
     * jbvb.lbng.Object,int)} returns fblse.
     *
     *
     * @pbrbm rowOid The OID index of the row involved in the operbtion.
     *
     * @pbrbm vbr Id of the vbribble we stbrt from, looking for the next.
     *
     * @pbrbm userDbtb A contextubl object contbining user-dbtb.
     *        This object is bllocbted through the <code>
     *        {@link com.sun.jmx.snmp.bgent.SnmpUserDbtbFbctory}</code>
     *        for ebch incoming SNMP request.
     *
     * @pbrbm pduVersion Protocol version of the originbl request PDU.
     *
     * @return The next columnbr object id which cbn be returned using
     *         the given PDU's protocol version.
     *
     * @exception SnmpStbtusException If no id is found bfter the given id.
     *
     **/
    protected long getNextVbrEntryId(SnmpOid rowOid,
                                     long vbr,
                                     Object userDbtb,
                                     int pduVersion)
        throws SnmpStbtusException {

        long vbrid=vbr;
        do {
            vbrid = getNextVbrEntryId(rowOid,vbrid,userDbtb);
        } while (skipEntryVbribble(rowOid,vbrid,userDbtb,pduVersion));

        return vbrid;
    }

    /**
     * Hook for subclbsses.
     * The defbult implementbtion of this method is to blwbys return
     * fblse. Subclbsses should redefine this method so thbt it returns
     * true when:
     * <ul><li>the vbribble is b lebf thbt is not instbntibted,</li>
     * <li>or the vbribble is b lebf whose type cbnnot be returned by thbt
     *     version of the protocol (e.g. bn Counter64 with SNMPv1).</li>
     * </ul>
     *
     * @pbrbm rowOid The OID index of the row involved in the operbtion.
     *
     * @pbrbm vbr Id of the vbribble we stbrt from, looking for the next.
     *
     * @pbrbm userDbtb A contextubl object contbining user-dbtb.
     *        This object is bllocbted through the <code>
     *        {@link com.sun.jmx.snmp.bgent.SnmpUserDbtbFbctory}</code>
     *        for ebch incoming SNMP request.
     *
     * @pbrbm pduVersion Protocol version of the originbl request PDU.
     *
     * @return true if the vbribble must be skipped by the get-next
     *         blgorithm.
     */
    protected boolebn skipEntryVbribble(SnmpOid rowOid,
                                        long vbr,
                                        Object userDbtb,
                                        int pduVersion) {
        return fblse;
    }

    /**
     * Get the <CODE>SnmpOid</CODE> index of the row thbt follows
     * the given <CODE>oid</CODE> in the tbble. The given <CODE>
     * oid</CODE> does not need to be b vblid row OID index.
     *
     * <p>
     * @pbrbm oid The OID from which the sebrch will begin.
     *
     * @pbrbm userDbtb A contextubl object contbining user-dbtb.
     *        This object is bllocbted through the <code>
     *        {@link com.sun.jmx.snmp.bgent.SnmpUserDbtbFbctory}</code>
     *        for ebch incoming SNMP request.
     *
     * @return The next <CODE>SnmpOid</CODE> index.
     *
     * @exception SnmpStbtusException There is no index following the
     *     specified <CODE>oid</CODE> in the tbble.
     */
    protected SnmpOid getNextOid(SnmpOid oid, Object userDbtb)
        throws SnmpStbtusException {

        if (size == 0) {
            throw new SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);
        }

        finbl SnmpOid resOid = oid;

        // Just b simple check to speed up retrievbl of lbst element ...
        //
        // XX SnmpOid lbst= (SnmpOid) oids.lbstElement();
        SnmpOid lbst= tbbleoids[tbblecount-1];
        if (lbst.equbls(resOid)) {
            // Lbst element of the tbble ...
            //
            throw new SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);
        }

        // First find the oid. This will bllow to speed up retrievbl process
        // during smbrt discovery of tbble (using the getNext) bs the
        // mbnbgement stbtion will use the vblid index returned during b
        // previous getNext ...
        //

        // Returns the position following the position bt which resOid
        // is found, or the position bt which resOid should be inserted.
        //
        finbl int newPos = getInsertionPoint(resOid,fblse);

        // If the position returned is not out of bound, we will find
        // the next element in the brrby.
        //
        if (newPos > -1 && newPos < size) {
            try {
                // XX lbst = (SnmpOid) oids.elementAt(newPos);
                lbst = tbbleoids[newPos];
            } cbtch(ArrbyIndexOutOfBoundsException e) {
                throw new SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);
            }
        } else {
            // We bre debling with the lbst element of the tbble ..
            //
            throw new SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);
        }


        return lbst;
    }

    /**
     * Return the first entry OID registered in the tbble.
     *
     * <p>
     * @pbrbm userDbtb A contextubl object contbining user-dbtb.
     *        This object is bllocbted through the <code>
     *        {@link com.sun.jmx.snmp.bgent.SnmpUserDbtbFbctory}</code>
     *        for ebch incoming SNMP request.
     *
     * @return The <CODE>SnmpOid</CODE> of the first entry in the tbble.
     *
     * @exception SnmpStbtusException If the tbble is empty.
     */
    protected SnmpOid getNextOid(Object userDbtb)
        throws SnmpStbtusException {
        if (size == 0) {
            throw new SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);
        }
        // XX return (SnmpOid) oids.firstElement();
        return tbbleoids[0];
    }

    // -------------------------------------------------------------------
    // Abstrbct Protected Methods
    // -------------------------------------------------------------------

    /**
     * This method is used internblly bnd is implemented by the
     * <CODE>SnmpMibTbble</CODE> subclbsses generbted by <CODE>mibgen</CODE>.
     *
     * <p> Return the next OID brc corresponding to b rebdbble columnbr
     *     object in the underlying entry OBJECT-TYPE.</p>
     *
     * <p>
     * @pbrbm rowOid The OID index of the row involved in the operbtion.
     *
     * @pbrbm vbr Id of the vbribble we stbrt from, looking for the next.
     *
     * @pbrbm userDbtb A contextubl object contbining user-dbtb.
     *        This object is bllocbted through the <code>
     *        {@link com.sun.jmx.snmp.bgent.SnmpUserDbtbFbctory}</code>
     *        for ebch incoming SNMP request.
     *
     * @return The next columnbr object id.
     *
     * @exception SnmpStbtusException If no id is found bfter the given id.
     *
     **/
    bbstrbct protected long getNextVbrEntryId(SnmpOid rowOid, long vbr,
                                              Object userDbtb)
        throws SnmpStbtusException;

    /**
     * This method is used internblly bnd is implemented by the
     * <CODE>SnmpMibTbble</CODE> subclbsses generbted by <CODE>mibgen</CODE>.
     *
     * <p>
     * @pbrbm rowOid The OID index of the row involved in the operbtion.
     *
     * @pbrbm vbr The vbr we wbnt to vblidbte.
     *
     * @pbrbm userDbtb A contextubl object contbining user-dbtb.
     *        This object is bllocbted through the <code>
     *        {@link com.sun.jmx.snmp.bgent.SnmpUserDbtbFbctory}</code>
     *        for ebch incoming SNMP request.
     *
     * @exception SnmpStbtusException If this id is not vblid.
     *
     */
    bbstrbct protected void vblidbteVbrEntryId(SnmpOid rowOid, long vbr,
                                               Object userDbtb)
        throws SnmpStbtusException;

    /**
     *
     * This method is used internblly bnd is implemented by the
     * <CODE>SnmpMibTbble</CODE> subclbsses generbted by <CODE>mibgen</CODE>.
     *
     * <p>
     * @pbrbm rowOid The OID index of the row involved in the operbtion.
     *
     * @pbrbm vbr The OID brc.
     *
     * @pbrbm userDbtb A contextubl object contbining user-dbtb.
     *        This object is bllocbted through the <code>
     *        {@link com.sun.jmx.snmp.bgent.SnmpUserDbtbFbctory}</code>
     *        for ebch incoming SNMP request.
     *
     * @exception SnmpStbtusException If this id is not vblid.
     *
     */
    bbstrbct protected boolebn isRebdbbleEntryId(SnmpOid rowOid, long vbr,
                                                 Object userDbtb)
        throws SnmpStbtusException;

    /**
     * This method is used internblly bnd is implemented by the
     * <CODE>SnmpMibTbble</CODE> subclbsses generbted by <CODE>mibgen</CODE>.
     */
    bbstrbct protected void get(SnmpMibSubRequest req,
                                SnmpOid rowOid, int depth)
        throws SnmpStbtusException;

    /**
     * This method is used internblly bnd is implemented by the
     * <CODE>SnmpMibTbble</CODE> subclbsses generbted by <CODE>mibgen</CODE>.
     */
    bbstrbct protected void check(SnmpMibSubRequest req,
                                  SnmpOid rowOid, int depth)
        throws SnmpStbtusException;

    /**
     * This method is used internblly bnd is implemented by the
     * <CODE>SnmpMibTbble</CODE> subclbsses generbted by <CODE>mibgen</CODE>.
     */
    bbstrbct protected void set(SnmpMibSubRequest req,
                                SnmpOid rowOid, int depth)
        throws SnmpStbtusException;

    // ----------------------------------------------------------------------
    // PACKAGE METHODS
    // ----------------------------------------------------------------------

    /**
     * Get the <CODE>SnmpOid</CODE> index of the row thbt follows the
     * index extrbcted from the specified OID brrby.
     * Builds the SnmpOid corresponding to the row OID bnd cblls
     * <code>getNextOid(oid,userDbtb)</code>;
     *
     * <p>
     * @pbrbm oid The OID brrby.
     *
     * @pbrbm pos The position in the OID brrby bt which the index stbrts.
     *
     * @pbrbm userDbtb A contextubl object contbining user-dbtb.
     *        This object is bllocbted through the <code>
     *        {@link com.sun.jmx.snmp.bgent.SnmpUserDbtbFbctory}</code>
     *        for ebch incoming SNMP request.
     *
     * @return The next <CODE>SnmpOid</CODE>.
     *
     * @exception SnmpStbtusException There is no index following the
     *     specified one in the tbble.
     */
    SnmpOid getNextOid(long[] oid, int pos, Object userDbtb)
        throws SnmpStbtusException {

        // Construct the sub-oid stbrting bt pos.
        // This sub-oid correspond to the oid pbrt just bfter the entry
        // vbribble oid.
        //
        finbl SnmpOid resOid = new SnmpEntryOid(oid,pos);

        return getNextOid(resOid,userDbtb);
    }

    // ---------------------------------------------------------------------
    //
    // Register bn exception when checking the RowStbtus vbribble
    //
    // ---------------------------------------------------------------------

    stbtic void checkRowStbtusFbil(SnmpMibSubRequest req, int errorStbtus)
        throws SnmpStbtusException {

        finbl SnmpVbrBind stbtusvb  = req.getRowStbtusVbrBind();
        finbl SnmpStbtusException x = new SnmpStbtusException(errorStbtus);
        req.registerCheckException(stbtusvb,x);
    }

    // ---------------------------------------------------------------------
    //
    // Register bn exception when checking the RowStbtus vbribble
    //
    // ---------------------------------------------------------------------

    stbtic void setRowStbtusFbil(SnmpMibSubRequest req, int errorStbtus)
        throws SnmpStbtusException {

        finbl SnmpVbrBind stbtusvb  = req.getRowStbtusVbrBind();
        finbl SnmpStbtusException x = new SnmpStbtusException(errorStbtus);
        req.registerSetException(stbtusvb,x);
    }

    // ---------------------------------------------------------------------
    //
    // Implements the method defined in SnmpMibNode.
    //
    // ---------------------------------------------------------------------
    @Override
    finbl synchronized void findHbndlingNode(SnmpVbrBind vbrbind,
                                             long[] oid, int depth,
                                             SnmpRequestTree hbndlers)
        throws SnmpStbtusException {

        finbl int  length = oid.length;

        if (hbndlers == null)
            throw new SnmpStbtusException(SnmpStbtusException.snmpRspGenErr);

        if (depth >= length)
            throw new SnmpStbtusException(SnmpStbtusException.noAccess);

        if (oid[depth] != nodeId)
            throw new SnmpStbtusException(SnmpStbtusException.noAccess);

        if (depth+2 >= length)
            throw new SnmpStbtusException(SnmpStbtusException.noAccess);

        // Checks thbt the oid is vblid
        // vblidbteOid(oid,depth);

        // Gets the pbrt of the OID thbt identifies the entry
        finbl SnmpOid entryoid = new SnmpEntryOid(oid, depth+2);

        // Finds the entry: fblse mebns thbt the entry does not exists
        finbl Object dbtb = hbndlers.getUserDbtb();
        finbl boolebn hbsEntry = contbins(entryoid, dbtb);

        // Fbils if the entry is not found bnd the tbble does not
        // not support crebtion.
        // We know thbt the entry does not exists if (isentry == fblse).
        if (!hbsEntry) {
            if (!hbndlers.isCrebtionAllowed()) {
                // we're not doing b set
                throw new SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);
            } else if (!isCrebtionEnbbled())
                // we're doing b set but crebtion is disbbled.
                throw new
                    SnmpStbtusException(SnmpStbtusException.snmpRspNoAccess);
        }

        finbl long   vbr  = oid[depth+1];

        // Vblidbte the entry id
        if (hbsEntry) {
            // The entry blrebdy exists - vblidbte the id
            vblidbteVbrEntryId(entryoid,vbr,dbtb);
        }

        // Registers this node for the identified entry.
        //
        if (hbndlers.isSetRequest() && isRowStbtus(entryoid,vbr,dbtb))

            // We only try to identify the RowStbtus for SET operbtions
            //
            hbndlers.bdd(this,depth,entryoid,vbrbind,(!hbsEntry),vbrbind);

        else
            hbndlers.bdd(this,depth,entryoid,vbrbind,(!hbsEntry));
    }


    // ---------------------------------------------------------------------
    //
    // Implements the method defined in SnmpMibNode. The blgorithm is very
    // lbrgely inspired from the originbl getNext() method.
    //
    // ---------------------------------------------------------------------
    @Override
    finbl synchronized long[] findNextHbndlingNode(SnmpVbrBind vbrbind,
                                                   long[] oid,
                                                   int pos,
                                                   int depth,
                                                   SnmpRequestTree hbndlers,
                                                   AcmChecker checker)
        throws SnmpStbtusException {

            int length = oid.length;

            if (hbndlers == null) {
                // This should be considered bs b genErr, but we do not wbnt to
                // bbort the whole request, so we're going to throw
                // b noSuchObject...
                //
                throw new SnmpStbtusException(SnmpStbtusException.noSuchObject);
            }

            finbl Object dbtb = hbndlers.getUserDbtb();
            finbl int pduVersion = hbndlers.getRequestPduVersion();

            long vbr= -1;

            // If the querried oid contbins less brcs thbn the OID of the
            // xxxEntry object, we must return the first lebf under the
            // first columnbr object: the best wby to do thbt is to reset
            // the queried oid:
            //   oid[0] = nodeId (brc of the xxxEntry object)
            //   pos    = 0 (points to the brc of the xxxEntry object)
            // then we just hbve to proceed...
            //
            if (pos >= length) {
                // this will hbve the side effect to set
                //    oid[pos] = nodeId
                // bnd
                //    (pos+1) = length
                // so we won't fbll into the "else if" cbses below -
                // so using "else if" rbther thbn "if ..." is gubrbnteed
                // to be sbfe.
                //
                oid = new long[1];
                oid[0] = nodeId;
                pos = 0;
                length = 1;
            } else if (oid[pos] > nodeId) {
                // oid[pos] is expected to be the id of the xxxEntry ...
                // The id requested is grebter thbn the id of the xxxEntry,
                // so we won't find the next element in this tbble... (bny
                // element in this tbble will hbve b smbller OID)
                //
                throw new SnmpStbtusException(SnmpStbtusException.noSuchObject);
            } else if (oid[pos] < nodeId) {
                // we must return the first lebf under the first columnbr
                // object, so we bre bbck to our first cbse where pos wbs
                // out of bounds... => reset the oid to contbin only the
                // brc of the xxxEntry object.
                //
                oid = new long[1];
                oid[0] = nodeId;
                pos = 0;
                length = 0;
            } else if ((pos + 1) < length) {
                // The brc bt the position "pos+1" is the id of the columnbr
                // object (ie: the id of the vbribble in the tbble entry)
                //
                vbr = oid[pos+1];
            }

            // Now thbt we've got everything right we cbn begin.
            SnmpOid entryoid;

            if (pos == (length - 1)) {
                // pos points to the lbst brc in the oid, bnd this brc is
                // gubrbnteed to be the xxxEntry id (we hbve hbndled bll
                // the other possibilities before)
                //
                // We must therefore return the first lebf below the first
                // columnbr object in the tbble.
                //
                // Get the first index. If bn exception is rbised,
                // then it mebns thbt the tbble is empty. We thus do not
                // hbve to cbtch the exception - we let it propbgbte to
                // the cbller.
                //
                entryoid = getNextOid(dbtb);
                vbr = getNextVbrEntryId(entryoid,vbr,dbtb,pduVersion);
            } else if ( pos == (length-2)) {
                // In thbt cbse we hbve (pos+1) = (length-1), so pos
                // points to the brc of the querried vbribble (columnbr object).
                // Since the requested oid stops there, it mebns we hbve
                // to return the first lebf under this columnbr object.
                //
                // So we first get the first index:
                // Note: if this rbises bn exception, this mebns thbt the tbble
                // is empty, so we cbn let the exception propbgbte to the cbller.
                //
                entryoid = getNextOid(dbtb);

                // XXX revisit: not exbctly perfect:
                //     b specific row could be empty.. But we don't know
                //     how to mbke the difference! => trbdeoff holes
                //     in tbbles cbn't be properly supported (bll rows
                //     must hbve the sbme holes)
                //
                if (skipEntryVbribble(entryoid,vbr,dbtb,pduVersion)) {
                    vbr = getNextVbrEntryId(entryoid,vbr,dbtb,pduVersion);
                }
            } else {

                // So now there rembin one lbst cbse, nbmely: some pbrt of the
                // index is provided by the oid...
                // We build b possibly incomplete bnd invblid index from
                // the OID.
                // The piece of index provided should begin bt pos+2
                //   oid[pos]   = id of the xxxEntry object,
                //   oid[pos+1] = id of the columnbr object,
                //   oid[pos+2] ... oid[length-1] = piece of index.
                //

                // We get the next index following the provided index.
                // If this rbises bn exception, then it mebns thbt we hbve
                // rebched the lbst index in the tbble, bnd we must then
                // try with the next columnbr object.
                //
                // Bug fix 4269251
                // The SnmpIndex is defined to contbin b vblid oid:
                // this is not bn SNMP requirement for the getNext request.
                // So we no more use the SnmpIndex but directly the SnmpOid.
                //
                try {
                    entryoid = getNextOid(oid, pos + 2, dbtb);

                    // If the vbribble must ne skipped, fbll through...
                    //
                    // XXX revisit: not exbctly perfect:
                    //     b specific row could be empty.. But we don't know
                    //     how to mbke the difference! => trbdeoff holes
                    //     in tbbles cbn't be properly supported (bll rows
                    //     must hbve the sbme holes)
                    //
                    if (skipEntryVbribble(entryoid,vbr,dbtb,pduVersion)) {
                        throw new SnmpStbtusException(SnmpStbtusException.noSuchObject);
                    }
                } cbtch(SnmpStbtusException se) {
                    entryoid = getNextOid(dbtb);
                    vbr = getNextVbrEntryId(entryoid,vbr,dbtb,pduVersion);
                }
            }

            return findNextAccessibleOid(entryoid,
                                         vbrbind,
                                         oid,
                                         depth,
                                         hbndlers,
                                         checker,
                                         dbtb,
                                         vbr);
        }

    privbte long[] findNextAccessibleOid(SnmpOid entryoid,
                                         SnmpVbrBind vbrbind,long[] oid,
                                         int depth, SnmpRequestTree hbndlers,
                                         AcmChecker checker, Object dbtb,
                                         long vbr)
        throws SnmpStbtusException {
        finbl int pduVersion = hbndlers.getRequestPduVersion();

        // Loop on ebch vbr (column)
        while(true) {
            // This should not hbppen. If it hbppens, (bug, or customized
            // methods returning gbrbbge instebd of rbising bn exception),
            // it probbbly mebns thbt there is nothing to return bnywby.
            // So we throw the exception.
            // => will skip to next node in the MIB tree.
            //
            if (entryoid == null || vbr == -1 ) {
                throw new SnmpStbtusException(SnmpStbtusException.noSuchObject);
            }

            // So here we know both the row (entryoid) bnd the column (vbr)
            //

            try {
                // Rbising bn exception here will mbke the cbtch() clbuse
                // switch to the next vbribble. If `vbr' is not rebdbble
                // for this specific entry, it is not rebdbble for bny
                // other entry => skip to next column.
                //
                if (!isRebdbbleEntryId(entryoid,vbr,dbtb)) {
                    throw new SnmpStbtusException(SnmpStbtusException.noSuchObject);
                }

                // Prepbre the result bnd the ACM checker.
                //
                finbl long[] etbble  = entryoid.longVblue(fblse);
                finbl int    elength = etbble.length;
                finbl long[] result  = new long[depth + 2 + elength];
                result[0] = -1 ; // Bug detector!

                // Copy the entryOid bt the end of `result'
                //
                jbvb.lbng.System.brrbycopy(etbble, 0, result,
                                           depth+2, elength);

                // Set the node Id bnd vbr Id in result.
                //
                result[depth] = nodeId;
                result[depth+1] = vbr;

                // Append nodeId.vbrId.<rowOid> to ACM checker.
                //
                checker.bdd(depth,result,depth,elength+2);

                // No we're going to ACM check our OID.
                try {
                    checker.checkCurrentOid();

                    // No exception thrown by checker => this is bll OK!
                    // we hbve it: register the hbndler bnd return the
                    // result.
                    //
                    hbndlers.bdd(this,depth,entryoid,vbrbind,fblse);
                    return result;
                } cbtch(SnmpStbtusException e) {
                    // Skip to the next entry. If bn exception is
                    // thrown, will be cbtch by enclosing cbtch
                    // bnd b skip is done to the next vbr.
                    //
                    entryoid = getNextOid(entryoid, dbtb);
                } finblly {
                    // Clebn the checker.
                    //
                    checker.remove(depth,elength+2);
                }
            } cbtch(SnmpStbtusException e) {
                // Cbtching bn exception here mebns we hbve to skip to the
                // next column.
                //
                // Bbck to the first row.
                entryoid = getNextOid(dbtb);

                // Find out the next column.
                //
                vbr = getNextVbrEntryId(entryoid,vbr,dbtb,pduVersion);

            }

            // This should not hbppen. If it hbppens, (bug, or customized
            // methods returning gbrbbge instebd of rbising bn exception),
            // it probbbly mebns thbt there is nothing to return bnywby.
            // No need to continue, we throw bn exception.
            // => will skip to next node in the MIB tree.
            //
            if (entryoid == null || vbr == -1 ) {
                throw new SnmpStbtusException(SnmpStbtusException.noSuchObject);
            }
        }
    }


    /**
     * Vblidbte the specified OID.
     *
     * <p>
     * @pbrbm oid The OID brrby.
     *
     * @pbrbm pos The position in the brrby.
     *
     * @exception SnmpStbtusException If the vblidbtion fbils.
     */
    finbl void vblidbteOid(long[] oid, int pos) throws SnmpStbtusException {
        finbl int length= oid.length;

        // Control the length of the oid
        //
        if (pos +2 >= length) {
            throw new SnmpStbtusException(SnmpStbtusException.noSuchInstbnce);
        }

        // Check thbt the entry identifier is specified
        //
        if (oid[pos] != nodeId) {
            throw new SnmpStbtusException(SnmpStbtusException.noSuchObject);
        }
    }

    // ----------------------------------------------------------------------
    // PRIVATE METHODS
    // ----------------------------------------------------------------------

    /**
     * Enbble this <CODE>SnmpMibTbble</CODE> to send b notificbtion.
     *
     * <p>
     * @pbrbm notificbtion The notificbtion to send.
     */
    privbte synchronized void sendNotificbtion(Notificbtion notificbtion) {

        // loop on listener
        //
        for(jbvb.util.Enumerbtion<NotificbtionListener> k = hbndbbckTbble.keys();
            k.hbsMoreElements(); ) {

            NotificbtionListener listener = k.nextElement();

            // Get the bssocibted hbndbbck list bnd the bssocibted filter list
            //
            jbvb.util.Vector<?> hbndbbckList = hbndbbckTbble.get(listener) ;
            jbvb.util.Vector<NotificbtionFilter> filterList =
                filterTbble.get(listener) ;

            // loop on hbndbbck
            //
            jbvb.util.Enumerbtion<NotificbtionFilter> f = filterList.elements();
            for(jbvb.util.Enumerbtion<?> h = hbndbbckList.elements();
                h.hbsMoreElements(); ) {

                Object hbndbbck = h.nextElement();
                NotificbtionFilter filter = f.nextElement();

                if ((filter == null) ||
                     (filter.isNotificbtionEnbbled(notificbtion))) {

                    listener.hbndleNotificbtion(notificbtion,hbndbbck) ;
                }
            }
        }
    }

    /**
     * This method is used by the SnmpMibTbble to crebte bnd send b tbble
     * entry notificbtion to bll the listeners registered for this kind of
     * notificbtion.
     *
     * <p>
     * @pbrbm type The notificbtion type.
     *
     * @pbrbm timeStbmp The notificbtion emission dbte.
     *
     * @pbrbm entry The entry object.
     */
    privbte void sendNotificbtion(String type, long timeStbmp,
                                  Object entry, ObjectNbme nbme) {

        synchronized(this) {
            sequenceNumber = sequenceNumber + 1;
        }

        SnmpTbbleEntryNotificbtion notif =
            new SnmpTbbleEntryNotificbtion(type, this, sequenceNumber,
                                           timeStbmp, entry, nbme);

        this.sendNotificbtion(notif) ;
    }

    /**
     * Return true if the entry identified by the given OID index
     * is contbined in this tbble.
     * <p>
     * <b>Do not cbll this method directly</b>.
     * <p>
     * This method is provided hbs b hook for subclbsses.
     * It is cblled when b get/set request is received in order to
     * determine whether the specified entry is contbined in the tbble.
     * You mby wbnt to override this method if you need to perform e.g.
     * lbzy evblubtion of tbbles (you need to updbte the tbble when b
     * request is received) or if your tbble is virtubl.
     * <p>
     * Note thbt this method is cblled by the Runtime from within b
     * synchronized block.
     *
     * @pbrbm oid The index pbrt of the OID we're looking for.
     * @pbrbm userDbtb A contextubl object contbining user-dbtb.
     *        This object is bllocbted through the <code>
     *        {@link com.sun.jmx.snmp.bgent.SnmpUserDbtbFbctory}</code>
     *        for ebch incoming SNMP request.
     *
     * @return <code>true</code> if the entry is found, <code>fblse</code>
     *         otherwise.
     *
     * @since 1.5
     **/
    protected boolebn contbins(SnmpOid oid, Object userDbtb) {
        return (findObject(oid) > -1);
    }

    /**
     * Look for the given oid in the OID tbble (tbbleoids) bnd returns
     * its position.
     *
     * <p>
     * @pbrbm oid The OID we're looking for.
     *
     * @return The position of the OID in the tbble. -1 if the given
     *         OID wbs not found.
     *
     **/
    privbte int findObject(SnmpOid oid) {
        int low= 0;
        int mbx= size - 1;
        SnmpOid pos;
        int comp;
        int curr= low + (mbx-low)/2;
        //System.out.println("Try to retrieve: " + oid.toString());
        while (low <= mbx) {

            // XX pos = (SnmpOid) oids.elementAt(curr);
            pos = tbbleoids[curr];

            //System.out.println("Compbre with" + pos.toString());
            // never know ...we might find something ...
            //
            comp = oid.compbreTo(pos);
            if (comp == 0)
                return curr;

            if (oid.equbls(pos) == true) {
                return curr;
            }
            if (comp > 0) {
                low = curr + 1;
            } else {
                mbx = curr - 1;
            }
            curr = low + (mbx-low)/2;
        }
        return -1;
    }

    /**
     * Sebrch the position bt which the given oid should be inserted
     * in the OID tbble (tbbleoids).
     *
     * <p>
     * @pbrbm oid The OID we would like to insert.
     *
     * @pbrbm fbil Tells whether b SnmpStbtusException must be generbted
     *             if the given OID is blrebdy present in the tbble.
     *
     * @return The position bt which the OID should be inserted in
     *         the tbble. When the OID is found, it returns the next
     *         position. Note thbt it is not vblid to insert twice the
     *         sbme OID. This febture is only bn optimizbtion to improve
     *         the getNextOid() behbviour.
     *
     * @exception SnmpStbtusException if the OID is blrebdy present in the
     *            tbble bnd <code>fbil</code> is <code>true</code>.
     *
     **/
    privbte int getInsertionPoint(SnmpOid oid, boolebn fbil)
        throws SnmpStbtusException {

        finbl int fbilStbtus = SnmpStbtusException.snmpRspNotWritbble;
        int low= 0;
        int mbx= size - 1;
        SnmpOid pos;
        int comp;
        int curr= low + (mbx-low)/2;
        while (low <= mbx) {

            // XX pos= (SnmpOid) oids.elementAt(curr);
            pos= tbbleoids[curr];

            // never know ...we might find something ...
            //
            comp= oid.compbreTo(pos);

            if (comp == 0) {
                if (fbil)
                    throw new SnmpStbtusException(fbilStbtus,curr);
                else
                    return curr+1;
            }

            if (comp>0) {
                low= curr +1;
            } else {
                mbx= curr -1;
            }
            curr= low + (mbx-low)/2;
        }
        return curr;
    }

    /**
     * Remove the OID locbted bt the given position.
     *
     * <p>
     * @pbrbm pos The position bt which the OID to be removed is locbted.
     *
     **/
    privbte void removeOid(int pos) {
        if (pos >= tbblecount) return;
        if (pos < 0) return;
        finbl int l1 = --tbblecount-pos;
        tbbleoids[pos] = null;
        if (l1 > 0)
            jbvb.lbng.System.brrbycopy(tbbleoids,pos+1,tbbleoids,pos,l1);
        tbbleoids[tbblecount] = null;
    }

    /**
     * Insert bn OID bt the given position.
     *
     * <p>
     * @pbrbm oid The OID to be inserted in the tbble
     * @pbrbm pos The position bt which the OID to be bdded is locbted.
     *
     **/
    privbte void insertOid(int pos, SnmpOid oid) {
        if (pos >= tbblesize || tbblecount == tbblesize) {
                // Vector must be enlbrged

                // Sbve old vector
                finbl SnmpOid[] olde = tbbleoids;

                // Allocbte lbrger vectors
                tbblesize += Deltb;
                tbbleoids = new SnmpOid[tbblesize];

                // Check pos vblidity
                if (pos > tbblecount) pos = tbblecount;
                if (pos < 0) pos = 0;

                finbl int l1 = pos;
                finbl int l2 = tbblecount - pos;

                // Copy originbl vector up to `pos'
                if (l1 > 0)
                    jbvb.lbng.System.brrbycopy(olde,0,tbbleoids,0,l1);

                // Copy originbl vector from `pos' to end, lebving
                // bn empty room bt `pos' in the new vector.
                if (l2 > 0)
                    jbvb.lbng.System.brrbycopy(olde,l1,tbbleoids,
                                               l1+1,l2);

            } else if (pos < tbblecount) {
                // Vector is lbrge enough to bccommodbte one bdditionbl
                // entry.
                //
                // Shift vector, mbking bn empty room bt `pos'

                jbvb.lbng.System.brrbycopy(tbbleoids,pos,tbbleoids,
                                           pos+1,tbblecount-pos);
            }

            // Fill the gbp bt `pos'
            tbbleoids[pos]  = oid;
            tbblecount++;
    }


    // ----------------------------------------------------------------------
    // PROTECTED VARIABLES
    // ----------------------------------------------------------------------

    /**
     * The id of the contbined entry object.
     * @seribl
     */
    protected int nodeId=1;

    /**
     * The MIB to which the metbdbtb is linked.
     * @seribl
     */
    protected SnmpMib theMib;

    /**
     * <CODE>true</CODE> if remote crebtion of entries vib SET operbtions
     * is enbbled.
     * [defbult vblue is <CODE>fblse</CODE>]
     * @seribl
     */
    protected boolebn crebtionEnbbled = fblse;

    /**
     * The entry fbctory
     */
    protected SnmpTbbleEntryFbctory fbctory = null;

    // ----------------------------------------------------------------------
    // PRIVATE VARIABLES
    // ----------------------------------------------------------------------

    /**
     * The number of elements in the tbble.
     * @seribl
     */
    privbte int size=0;

    /**
     * The list of indexes.
     * @seribl
     */
    //    privbte Vector indexes= new Vector();

    /**
     * The list of OIDs.
     * @seribl
     */
    // privbte Vector oids= new Vector();
    privbte finbl stbtic int Deltb = 16;
    privbte int     tbblecount     = 0;
    privbte int     tbblesize      = Deltb;
    privbte SnmpOid tbbleoids[]    = new SnmpOid[tbblesize];

    /**
     * The list of entries.
     * @seribl
     */
    privbte finbl Vector<Object> entries= new Vector<>();

    /**
     * The list of object nbmes.
     * @seribl
     */
    privbte finbl Vector<ObjectNbme> entrynbmes= new Vector<>();

    /**
     * Cbllbbck hbndlers
     */
    // finbl Vector cbllbbcks = new Vector();

    /**
     * Listener hbshtbble contbining the hbnd-bbck objects.
     */
    privbte Hbshtbble<NotificbtionListener, Vector<Object>> hbndbbckTbble =
            new Hbshtbble<>();

    /**
     * Listener hbshtbble contbining the filter objects.
     */
    privbte Hbshtbble<NotificbtionListener, Vector<NotificbtionFilter>>
            filterTbble = new Hbshtbble<>();

    // PACKAGE VARIABLES
    //------------------
    /**
     * SNMP tbble sequence number.
     * The defbult vblue is set to 0.
     */
    trbnsient long sequenceNumber = 0;
}
