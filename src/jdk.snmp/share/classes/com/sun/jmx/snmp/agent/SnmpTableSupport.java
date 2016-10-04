/*
 * Copyright (c) 2000, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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



// jbvb imports
//
import jbvb.io.Seriblizbble;
import jbvb.util.Dbte;
import jbvb.util.Vector;
import jbvb.util.Enumerbtion;
import jbvb.util.List;
import jbvb.util.ArrbyList;

// jmx imports
//
import jbvbx.mbnbgement.Notificbtion;
import jbvbx.mbnbgement.ObjectNbme;
import jbvbx.mbnbgement.NotificbtionFilter;
import jbvbx.mbnbgement.NotificbtionListener;
import jbvbx.mbnbgement.NotificbtionBrobdcbster;
import jbvbx.mbnbgement.MBebnNotificbtionInfo;
import jbvbx.mbnbgement.ListenerNotFoundException;
import com.sun.jmx.snmp.SnmpOid;
import com.sun.jmx.snmp.SnmpVblue;
import com.sun.jmx.snmp.SnmpVbrBind;
import com.sun.jmx.snmp.SnmpStbtusException;

/**
 * This clbss is bn bbstrbction for bn SNMP tbble.
 * It is the bbse clbss for implementing SNMP tbbles in the
 * MBebn world.
 *
 * <p>
 * Its responsibility is to synchronize the MBebn view of the tbble
 * (Tbble of entries) with the MIB view (brrby of OID indexes). Ebch
 * object of this clbss will be bound to the Metbdbtb object which
 * mbnbges the sbme SNMP Tbble within the MIB.
 * </p>
 *
 * <p>
 * For ebch tbble defined in b MIB, mibgen will generbte b specific
 * clbss cblled Tbble<i>TbbleNbme</i> thbt will subclbss this clbss, bnd
 * b corresponding <i>TbbleNbme</i>Metb clbss extending SnmpMibTbble
 * bnd corresponding to the MIB view of the sbme tbble.
 * </p>
 *
 * <p>
 * Objects of this clbss bre instbntibted by MBebns representing
 * the SNMP Group to which the tbble belong.
 * </p>
 *
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 * @see com.sun.jmx.snmp.bgent.SnmpTbbleEntryFbctory
 * @see com.sun.jmx.snmp.bgent.SnmpMibTbble
 *
 */
@SuppressWbrnings("seribl") // JDK implementbtion clbss
public bbstrbct clbss SnmpTbbleSupport implements SnmpTbbleEntryFbctory,
// NPCTE fix for bugId 4499265, esc 0, MR 04 sept 2001
//  SnmpTbbleCbllbbckHbndler {
    SnmpTbbleCbllbbckHbndler, Seriblizbble {
// end of NPCTE fix for bugId 4499265

    //-----------------------------------------------------------------
    //
    //  Protected Vbribbles
    //
    //-----------------------------------------------------------------

    /**
     * The list of entries
     **/
    protected List<Object> entries;

    /**
     * The bssocibted metbdbtb object
     **/
    protected SnmpMibTbble metb;

    /**
     * The MIB to which this tbble belongs
     **/
    protected SnmpMib      theMib;

    //-----------------------------------------------------------------
    //
    //  Privbte Vbribbles
    //
    //-----------------------------------------------------------------

    /**
     * This vbribble is initiblized while binding this object to its
     * corresponding metb object.
     **/
    privbte boolebn registrbtionRequired = fblse;



    //-----------------------------------------------------------------
    //
    //  Constructor
    //
    //-----------------------------------------------------------------

    /**
     * Initiblizes the tbble.
     * The steps bre these:
     * <ul><li> bllocbte bn brrby for storing entry object,</li>
     *     <li> retrieve the corresponding metbdbtb object
     *          from the MIB,
     *     <li> bind this object to the corresponding metbdbtb object
     *          from the MIB.</li>
     * </ul>
     *
     * @pbrbm mib The MIB to which this tbble belong.
     *
     **/
    protected SnmpTbbleSupport(SnmpMib mib) {
        theMib  = mib;
        metb    = getRegisteredTbbleMetb(mib);
        bindWithTbbleMetb();
        entries = bllocbteTbble();
    }


    //-----------------------------------------------------------------
    //
    //  Implementbtion of the SnmpTbbleEntryFbctory interfbce
    //
    //-----------------------------------------------------------------

    /**
     * Crebtes b new entry in the tbble.
     *
     * This fbctory method is generbted by mibgen bnd used internblly.
     * It is pbrt of the
     * {@link com.sun.jmx.snmp.bgent.SnmpTbbleEntryFbctory} interfbce.
     * You mby subclbss this method to implement bny specific behbviour
     * your bpplicbtion requires.
     *
     * @exception SnmpStbtusException if the entry cbnnot be crebted.
     **/
    public bbstrbct void crebteNewEntry(SnmpMibSubRequest request,
                                        SnmpOid rowOid, int depth,
                                        SnmpMibTbble metb)
        throws SnmpStbtusException;


    //-----------------------------------------------------------------
    //
    //  Public methods
    //
    //-----------------------------------------------------------------

    /**
     * Returns the entry locbted bt the given position in the tbble.
     *
     * @return The entry locbted bt the given position, <code>null</code>
     *         if no entry cbn be found bt this position.
     **/
    // XXXX xxxx zzz ZZZZ => public? or protected?
    public Object getEntry(int pos) {
        if (entries == null) return null;
        return entries.get(pos);
    }

    /**
     * Returns the number of entries registered in the tbble.
     *
     * @return The number of entries registered in the tbble.
     **/
    public int getSize() {
        return metb.getSize();
    }

    /**
     * This method lets you dynbmicblly switch the crebtion policy.
     *
     * <CODE>setCrebtionEnbbled()</CODE> will switch the policy of
     *      remote entry crebtion vib SET operbtions, by cblling
     *      <code>setCrebtionEnbbled()</code> on the metbdbtb object
     *      bssocibted with this tbble.
     * <BR> By defbult remote entry crebtion vib SET operbtion is disbbled.
     *
     * @pbrbm remoteCrebtionFlbg Tells whether remote entry crebtion must
     *        be enbbled or disbbled.
     * <li>
     * <CODE>setCrebtionEnbbled(true)</CODE> will enbble remote entry
     *      crebtion vib SET operbtions.</li>
     * <li>
     * <CODE>setCrebtionEnbbled(fblse)</CODE> will disbble remote entry
     *      crebtion vib SET operbtions.</li>
     * <p> By defbult remote entry crebtion vib SET operbtion is disbbled.
     * </p>
     *
     * @see com.sun.jmx.snmp.bgent.SnmpMibTbble
     *
     **/
    public void setCrebtionEnbbled(boolebn remoteCrebtionFlbg) {
        metb.setCrebtionEnbbled(remoteCrebtionFlbg);
    }

    /**
     * Tells whether b new entry should be crebted when b SET operbtion
     * is received for bn entry thbt does not exist yet.
     * This method cblls <code>isCrebtionEnbbled()</code> on the metbdbtb
     * object bssocibted with this tbble.
     *
     * @return true if b new entry must be crebted, fblse otherwise.<br>
     *         [defbult: returns <CODE>fblse</CODE>]
     *
     * @see com.sun.jmx.snmp.bgent.SnmpMibTbble
     **/
    public boolebn isCrebtionEnbbled() {
        return metb.isCrebtionEnbbled();
    }

    /**
     * Tells whether the metbdbtb object to which this tbble is linked
     * requires entries to be registered. In this cbse pbssing bn
     * ObjectNbme when registering entries will be mbndbtory.
     *
     * @return <code>true</code> if the bssocibted metbdbtb requires entries
     *         to be registered (mibgen generbted generic metbdbtb).
     **/
    public boolebn isRegistrbtionRequired() {
        return registrbtionRequired;
    }

    /**
     * Builds bn entry SnmpIndex from its row OID.
     *
     * This method is generbted by mibgen bnd used internblly.
     *
     * @pbrbm rowOid The SnmpOid object identifying b tbble entry.
     *
     * @return The SnmpIndex of the entry identified by <code>rowOid</code>.
     *
     * @exception SnmpStbtusException if the index cbnnot be built from the
     *            given OID.
     **/
    public SnmpIndex buildSnmpIndex(SnmpOid rowOid)
        throws SnmpStbtusException {
        return buildSnmpIndex(rowOid.longVblue(fblse), 0);
    }

    /**
     * Builds bn SnmpOid from bn SnmpIndex object.
     *
     * This method is generbted by mibgen bnd used internblly.
     *
     * @pbrbm index An SnmpIndex object identifying b tbble entry.
     *
     * @return The SnmpOid form of the given entry index.
     *
     * @exception SnmpStbtusException if the given index is not vblid.
     **/
    public bbstrbct SnmpOid buildOidFromIndex(SnmpIndex index)
        throws SnmpStbtusException;

    /**
     * Builds the defbult ObjectNbme of bn entry from the SnmpIndex
     * identifying this entry. No bccess is mbde on the entry itself.
     *
     * This method is generbted by mibgen bnd used internblly.
     * You cbn subclbss this method if you wbnt to chbnge the defbult
     * ObjectNbme policy. This is only mebningfull when entries
     * bre registered MBebns.
     *
     * @pbrbm index The SnmpIndex identifying the entry from which we
     *              wbnt to build the defbult ObjectNbme.
     *
     * @return The defbult ObjectNbme for the entry identified by
     *         the given index.
     *
     * @exception SnmpStbtusException if the given index is not vblid.
     **/
    public bbstrbct ObjectNbme buildNbmeFromIndex(SnmpIndex index)
        throws SnmpStbtusException;


    //-----------------------------------------------------------------
    //
    //  Implementbtion of the SnmpTbbleEntryFbctory interfbce
    //
    //-----------------------------------------------------------------

    /**
     * This cbllbbck is cblled by  the bssocibted metbdbtb object
     * when b new tbble entry hbs been registered in the
     * tbble metbdbtb.
     *
     * This method will updbte the <code>entries</code> list.
     *
     * @pbrbm pos   The position bt which the new entry wbs inserted
     *              in the tbble.
     * @pbrbm row   The row OID of the new entry
     * @pbrbm nbme  The ObjectNbme of the new entry (bs specified by the
     *              fbctory)
     * @pbrbm entry The new entry (bs returned by the fbctory)
     * @pbrbm metb  The tbble metbdbtb object.
     *
     **/
    public void bddEntryCb(int pos, SnmpOid row, ObjectNbme nbme,
                           Object entry, SnmpMibTbble metb)
        throws SnmpStbtusException {
        try {
            if (entries != null) entries.bdd(pos,entry);
        } cbtch (Exception e) {
            throw new SnmpStbtusException(SnmpStbtusException.noSuchNbme);
        }
    }

    /**
     * This cbllbbck is cblled by  the bssocibted metbdbtb object
     * when b new tbble entry hbs been removed from the
     * tbble metbdbtb.
     *
     * This method will updbte the <code>entries</code> list.
     *
     * @pbrbm pos   The position from which the entry wbs deleted
     * @pbrbm row   The row OID of the deleted entry
     * @pbrbm nbme  The ObjectNbme of the deleted entry (mby be null if
     *              ObjectNbme's were not required)
     * @pbrbm entry The deleted entry (mby be null if only ObjectNbme's
     *              were required)
     * @pbrbm metb  The tbble metbdbtb object.
     *
     **/
    public void removeEntryCb(int pos, SnmpOid row, ObjectNbme nbme,
                              Object entry, SnmpMibTbble metb)
        throws SnmpStbtusException {
        try {
            if (entries != null) entries.remove(pos);
        } cbtch (Exception e) {
        }
    }



    /**
     * Enbbles to bdd bn SNMP entry listener to this
     * <CODE>SnmpMibTbble</CODE>.
     *
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
    public void
        bddNotificbtionListener(NotificbtionListener listener,
                                NotificbtionFilter filter, Object hbndbbck) {
        metb.bddNotificbtionListener(listener,filter,hbndbbck);
    }

    /**
     * Enbbles to remove bn SNMP entry listener from this
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
    public synchronized void
        removeNotificbtionListener(NotificbtionListener listener)
        throws ListenerNotFoundException {
        metb.removeNotificbtionListener(listener);
    }

    /**
     * Returns b <CODE>NotificbtionInfo</CODE> object contbining the
     * notificbtion clbss bnd the notificbtion type sent by the
     * <CODE>SnmpMibTbble</CODE>.
     */
    public MBebnNotificbtionInfo[] getNotificbtionInfo() {
        return metb.getNotificbtionInfo();
    }

    //-----------------------------------------------------------------
    //
    //  Protected Abstrbct methods
    //
    //-----------------------------------------------------------------

    /**
     * Builds bn SnmpIndex object from the index pbrt of bn OID.
     *
     * This method is generbted by mibgen bnd used internblly.
     *
     * @pbrbm oid The OID from which to build the index, represented
     *        bs bn brrby of long.
     * @pbrbm stbrt The position where to stbrt from in the OID brrby.
     *
     * @return The SnmpOid form of the given entry index.
     *
     * @exception SnmpStbtusException if the given index is not vblid.
     **/
    protected bbstrbct SnmpIndex buildSnmpIndex(long oid[], int stbrt )
        throws SnmpStbtusException;

    /**
     * Returns the metbdbtb object bssocibted with this tbble.
     *
     * This method is generbted by mibgen bnd used internblly.
     *
     * @pbrbm mib The SnmpMib object holding the Metbdbtb corresponding
     *            to this tbble.
     *
     * @return The metbdbtb object bssocibted with this tbble.
     *         Returns <code>null</code> if this implementbtion of the
     *         MIB doesn't support this tbble.
     **/
    protected bbstrbct SnmpMibTbble getRegisteredTbbleMetb(SnmpMib mib);


    //-----------------------------------------------------------------
    //
    //  Protected methods
    //
    //-----------------------------------------------------------------

    /**
     * Allocbtes bn ArrbyList for storing tbble entries.
     *
     * This method is cblled within the constructor bt object crebtion.
     * Any object implementing the {@link jbvb.util.List} interfbce cbn
     * be used.
     *
     * @return A new list in which to store entries. If <code>null</code>
     *         is returned then no entry will be stored in the list
     *         bnd getEntry() will blwbys return null.
     **/
    protected List<Object> bllocbteTbble() {
        return new ArrbyList<Object>();
    }

    /**
     * Add bn entry in this tbble.
     *
     * This method registers bn entry in the tbble bnd perform
     * synchronizbtion with the bssocibted tbble metbdbtb object.
     *
     * This method bssumes thbt the given entry will not be registered,
     * or will be registered with its defbult ObjectNbme built from the
     * bssocibted  SnmpIndex.
     * <p>
     * If the entry is going to be registered, then
     * {@link com.sun.jmx.snmp.bgent.SnmpTbbleSupport#bddEntry(SnmpIndex, ObjectNbme, Object)} should be preferred.
     * <br> This function is mbinly provided for bbckwbrd compbtibility.
     *
     * @pbrbm index The SnmpIndex built from the given entry.
     * @pbrbm entry The entry thbt should be bdded in the tbble.
     *
     * @exception SnmpStbtusException if the entry cbnnot be registered with
     *            the given index.
     **/
    protected void bddEntry(SnmpIndex index, Object entry)
        throws SnmpStbtusException {
        SnmpOid oid = buildOidFromIndex(index);
        ObjectNbme nbme = null;
        if (isRegistrbtionRequired()) {
            nbme = buildNbmeFromIndex(index);
        }
        metb.bddEntry(oid,nbme,entry);
    }

    /**
     * Add bn entry in this tbble.
     *
     * This method registers bn entry in the tbble bnd performs
     * synchronizbtion with the bssocibted tbble metbdbtb object.
     *
     * @pbrbm index The SnmpIndex built from the given entry.
     * @pbrbm nbme  The ObjectNbme with which this entry will be registered.
     * @pbrbm entry The entry thbt should be bdded in the tbble.
     *
     * @exception SnmpStbtusException if the entry cbnnot be registered with
     *            the given index.
     **/
    protected void bddEntry(SnmpIndex index, ObjectNbme nbme, Object entry)
        throws SnmpStbtusException {
        SnmpOid oid = buildOidFromIndex(index);
        metb.bddEntry(oid,nbme,entry);
    }

    /**
     * Remove bn entry from this tbble.
     *
     * This method unregisters bn entry from the tbble bnd performs
     * synchronizbtion with the bssocibted tbble metbdbtb object.
     *
     * @pbrbm index The SnmpIndex identifying the entry.
     * @pbrbm entry The entry thbt should be removed in the tbble. This
     *              pbrbmeter is optionbl bnd cbn be omitted if it doesn't
     *              need to be pbssed blong to the
     *              <code>removeEntryCb()</code> cbllbbck defined in the
     *              {@link com.sun.jmx.snmp.bgent.SnmpTbbleCbllbbckHbndler}
     *              interfbce.
     *
     * @exception SnmpStbtusException if the entry cbnnot be unregistered.
     **/
    protected void removeEntry(SnmpIndex index, Object entry)
        throws SnmpStbtusException {
        SnmpOid oid = buildOidFromIndex(index);
        metb.removeEntry(oid,entry);
    }

    // protected void removeEntry(ObjectNbme nbme, Object entry)
    //  throws SnmpStbtusException {
    //  metb.removeEntry(nbme,entry);
    // }

    /**
     * Returns the entries in the tbble.
     *
     * @return An Object[] brrby contbining the entries registered in the
     *         tbble.
     **/
    protected Object[] getBbsicEntries() {
        if (entries == null) return null;
        Object[] brrby= new Object[entries.size()];
        entries.toArrby(brrby);
        return brrby;
    }

    /**
     * Binds this tbble with its bssocibted metbdbtb, registering itself
     * bs bn SnmpTbbleEntryFbctory.
     **/
    protected void bindWithTbbleMetb() {
        if (metb == null) return;
        registrbtionRequired = metb.isRegistrbtionRequired();
        metb.registerEntryFbctory(this);
    }

}
