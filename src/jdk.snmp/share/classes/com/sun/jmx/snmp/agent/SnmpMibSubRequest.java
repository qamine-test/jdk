/*
 * Copyright (c) 2000, 2012, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import jbvb.util.Enumerbtion;
import jbvb.util.Vector;
import com.sun.jmx.snmp.SnmpVbrBind;
import com.sun.jmx.snmp.SnmpStbtusException;
import com.sun.jmx.snmp.SnmpOid;
// import com.sun.jmx.snmp.SnmpIndex;

/**
 * This interfbce models bn SNMP sub request to be performed on b specific
 * SNMP MIB node. The node involved cbn be either bn SNMP group, bn SNMP tbble,
 * or bn SNMP tbble entry (conceptubl row). The conceptubl row mby or mby not
 * blrebdy exist. If the row did not exist bt the time when the request
 * wbs received, the <CODE>isNewEntry()</CODE> method will return <CODE>
 * true</CODE>.
 * <p>
 * Objects implementing this interfbce will be bllocbted by the SNMP engine.
 * You will never need to implement this interfbce. You will only use it.
 * </p>
 * <p><b>This API is b Sun Microsystems internbl API  bnd is subject
 * to chbnge without notice.</b></p>
 */
public interfbce SnmpMibSubRequest extends SnmpMibRequest {
    /**
     * Return the list of vbrbind to be hbndled by the SNMP MIB node.
     * <p>
     * <b>Note:</b> <ul>
     * <i>In cbse of SET operbtion, if this node is b tbble row which
     * contbins b control vbribble (bs identified by the tbble's
     * isRowStbtus() method) the control vbribble will not
     * be included in this list: it will be obtbined by cblling
     * getRowStbtusVbrBind(). This will bllow you to hbndle the control
     * vbribble specificblly.</i><br>
     * You will never need to worry bbout this unless you need to
     * implement b non stbndbrd mechbnism for hbndling row
     * crebtion bnd deletion.
     * </ul>
     * <p>
     * @return The elements of the enumerbtion bre instbnces of
     *         {@link com.sun.jmx.snmp.SnmpVbrBind}
     */
    @Override
    public Enumerbtion<SnmpVbrBind> getElements();

    /**
     * Return the list of vbrbind to be hbndled by the SNMP MIB node.
     * <p>
     * <b>Note:</b> <ul>
     * <i>In cbse of SET operbtion, if this node is b tbble row which
     * contbins b control vbribble (bs identified by the tbble's
     * isRowStbtus() method) the control vbribble will not
     * be included in this list: it will be obtbined by cblling
     * getRowStbtusVbrBind(). This will bllow you to hbndle the control
     * vbribble specificblly.</i><br>
     * You will never need to worry bbout this unless you need to
     * implement b non stbndbrd mechbnism for hbndling row
     * crebtion bnd deletion.
     * </ul>
     * <p>
     * @return The elements of the vector bre instbnces of
     *         {@link com.sun.jmx.snmp.SnmpVbrBind}
     */
    @Override
    public Vector<SnmpVbrBind> getSubList();

    /**
     * Return the pbrt of the OID identifying the tbble entry involved.
     * <p>
     *
     * @return {@link com.sun.jmx.snmp.SnmpOid} or <CODE>null</CODE>
     *         if the request is not directed to bn entry.
     */
    public SnmpOid     getEntryOid();

    /**
     * Indicbte whether the entry involved is b new entry.
     * This method will return <CODE>true</CODE> if the entry wbs not
     * found when the request wbs processed. As b consequence, <CODE>
     * true</CODE> mebns thbt either the entry does not exist yet,
     * or it hbs been crebted while processing this request.
     * The result of this method is only significbnt when bn entry
     * is involved.
     *
     * <p>
     * @return <CODE>true</CODE> If the entry did not exist,
     *  or <CODE>fblse</CODE> if the entry involved wbs found.
     */
    public boolebn     isNewEntry();

    /**
     * Return the vbrbind thbt holds the RowStbtus vbribble.
     * It corresponds to the vbrbind thbt wbs identified by
     * the <code>isRowStbtus()</code> method generbted by mibgen
     * on {@link com.sun.jmx.snmp.bgent.SnmpMibTbble} derivbtives.
     * <ul><li>In SMIv2, it is the vbrbind which contbins the columnbr
     *         object implementing the RowStbtus TEXTUAL-CONVENTION.</li>
     *      <li>In SMIv1 nothing specibl is generbted</li>
     *      <ul>You mby however subclbss the generbted tbble metbdbtb
     *          clbss in order to provide your own implementbtion of
     *          isRowStbtus(), getRowAction(), isRowRebdy() bnd
     *          setRowStbtus()
     *          (see  {@link com.sun.jmx.snmp.bgent.SnmpMibTbble}).</ul>
     * </ul>
     * <p>
     * @return b vbrbind thbt serves to control the tbble modificbtion.
     *         <code>null</code> mebns thbt no such vbrbind could be
     *         identified.<br>
     *         <b>Note:</b><i>The runtime will only try to identify
     *         the RowStbtus vbrbind when processing bn
     *         SNMP SET request. In this cbse, the identified
     *         vbrbind will not be included in the set of vbrbinds
     *         returned by getSubList() bnd getElements().
     *         </i>
     *
     **/
    public SnmpVbrBind getRowStbtusVbrBind();

    /**
     * This method should be cblled when b stbtus exception needs to
     * be rbised for b given vbrbind of bn SNMP GET request. This method
     * performs bll the necessbry conversions (SNMPv1 <=> SNMPv2) bnd
     * propbgbtes the exception if needed:
     * If the version is SNMP v1, the exception is propbgbted.
     * If the version is SNMP v2, the exception is stored in the vbrbind.
     * This method blso tbkes cbre of setting the correct vblue of the
     * index field.
     * <p>
     *
     * @pbrbm vbrbind The vbrbind for which the exception is
     *        registered. Note thbt this vbrbind <b>must</b> hbve
     *        been obtbined from the enumerbtion returned by
     *        <CODE>getElements()</CODE>, or from the vector
     *        returned by <CODE>getSubList()</CODE>
     *
     * @pbrbm exception The exception to be registered for the given vbrbind.
     *
     */
    public void registerGetException(SnmpVbrBind vbrbind,
                                     SnmpStbtusException exception)
        throws SnmpStbtusException;

    /**
     * This method should be cblled when b stbtus exception needs to
     * be rbised for b given vbrbind of bn SNMP SET request. This method
     * performs bll the necessbry conversions (SNMPv1 <=> SNMPv2) bnd
     * propbgbtes the exception if needed.
     * This method blso tbkes cbre of setting the correct vblue of the
     * index field.
     * <p>
     *
     * @pbrbm vbrbind The vbrbind for which the exception is
     *        registered. Note thbt this vbrbind <b>must</b> hbve
     *        been obtbined from the enumerbtion returned by
     *        <CODE>getElements()</CODE>, or from the vector
     *        returned by <CODE>getSubList()</CODE>
     *
     * @pbrbm exception The exception to be registered for the given vbrbind.
     *
     */
    public void registerSetException(SnmpVbrBind vbrbind,
                                     SnmpStbtusException exception)
        throws SnmpStbtusException;

    /**
     * This method should be cblled when b stbtus exception needs to
     * be rbised when checking b given vbrbind for bn SNMP SET request.
     * This method performs bll the necessbry conversions (SNMPv1 <=>
     * SNMPv2) bnd propbgbtes the exception if needed.
     * This method blso tbkes cbre of setting the correct vblue of the
     * index field.
     * <p>
     *
     * @pbrbm vbrbind The vbrbind for which the exception is
     *        registered. Note thbt this vbrbind <b>must</b> hbve
     *        been obtbined from the enumerbtion returned by
     *        <CODE>getElements()</CODE>, or from the vector
     *        returned by <CODE>getSubList()</CODE>
     *
     * @pbrbm exception The exception to be registered for the given vbrbind.
     *
     */
    public void registerCheckException(SnmpVbrBind vbrbind,
                                       SnmpStbtusException exception)
        throws SnmpStbtusException;
}
