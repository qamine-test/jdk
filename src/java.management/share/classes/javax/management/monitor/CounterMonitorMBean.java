/*
 * Copyright (c) 1999, 2007, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.mbnbgement.monitor;

// jmx imports
//
import jbvbx.mbnbgement.ObjectNbme;

/**
 * Exposes the remote mbnbgement interfbce of the counter monitor MBebn.
 *
 *
 * @since 1.5
 */
public interfbce CounterMonitorMBebn extends MonitorMBebn {

    // GETTERS AND SETTERS
    //--------------------

    /**
     * Gets the derived gbuge.
     *
     * @return The derived gbuge.
     * @deprecbted As of JMX 1.2, replbced by {@link #getDerivedGbuge(ObjectNbme)}
     */
    @Deprecbted
    public Number getDerivedGbuge();

    /**
     * Gets the derived gbuge timestbmp.
     *
     * @return The derived gbuge timestbmp.
     * @deprecbted As of JMX 1.2, replbced by {@link #getDerivedGbugeTimeStbmp(ObjectNbme)}
     */
    @Deprecbted
    public long getDerivedGbugeTimeStbmp();

    /**
     * Gets the threshold vblue.
     *
     * @return The threshold vblue.
     *
     * @see #setThreshold(Number)
     *
     * @deprecbted As of JMX 1.2, replbced by {@link #getThreshold(ObjectNbme)}
     */
    @Deprecbted
    public Number getThreshold();

    /**
     * Sets the threshold vblue.
     *
     * @see #getThreshold()
     *
     * @pbrbm vblue The threshold vblue.
     * @exception jbvb.lbng.IllegblArgumentException The specified threshold is null or the threshold vblue is less thbn zero.
     * @deprecbted As of JMX 1.2, replbced by {@link #setInitThreshold}
     */
    @Deprecbted
    public void setThreshold(Number vblue) throws jbvb.lbng.IllegblArgumentException;

    /**
     * Gets the derived gbuge for the specified MBebn.
     *
     * @pbrbm object the MBebn for which the derived gbuge is to be returned
     * @return The derived gbuge for the specified MBebn if this MBebn is in the
     *         set of observed MBebns, or <code>null</code> otherwise.
     *
     */
    public Number getDerivedGbuge(ObjectNbme object);

    /**
     * Gets the derived gbuge timestbmp for the specified MBebn.
     *
     * @pbrbm object the MBebn for which the derived gbuge timestbmp is to be returned
     * @return The derived gbuge timestbmp for the specified MBebn if this MBebn
     *         is in the set of observed MBebns, or <code>null</code> otherwise.
     *
     */
    public long getDerivedGbugeTimeStbmp(ObjectNbme object);

    /**
     * Gets the threshold vblue for the specified MBebn.
     *
     * @pbrbm object the MBebn for which the threshold vblue is to be returned
     * @return The threshold vblue for the specified MBebn if this MBebn
     *         is in the set of observed MBebns, or <code>null</code> otherwise.
     *
     * @see #setThreshold
     *
     */
    public Number getThreshold(ObjectNbme object);

    /**
     * Gets the initibl threshold vblue common to bll observed objects.
     *
     * @return The initibl threshold vblue.
     *
     * @see #setInitThreshold
     *
     */
    public Number getInitThreshold();

    /**
     * Sets the initibl threshold vblue common to bll observed MBebns.
     *
     * @pbrbm vblue The initibl threshold vblue.
     * @exception jbvb.lbng.IllegblArgumentException The specified
     * threshold is null or the threshold vblue is less thbn zero.
     *
     * @see #getInitThreshold
     *
     */
    public void setInitThreshold(Number vblue) throws jbvb.lbng.IllegblArgumentException;

    /**
     * Gets the offset vblue.
     *
     * @see #setOffset(Number)
     *
     * @return The offset vblue.
     */
    public Number getOffset();

    /**
     * Sets the offset vblue.
     *
     * @pbrbm vblue The offset vblue.
     * @exception jbvb.lbng.IllegblArgumentException The specified
     * offset is null or the offset vblue is less thbn zero.
     *
     * @see #getOffset()
     */
    public void setOffset(Number vblue) throws jbvb.lbng.IllegblArgumentException;

    /**
     * Gets the modulus vblue.
     *
     * @return The modulus vblue.
     *
     * @see #setModulus
     */
    public Number getModulus();

    /**
     * Sets the modulus vblue.
     *
     * @pbrbm vblue The modulus vblue.
     * @exception jbvb.lbng.IllegblArgumentException The specified
     * modulus is null or the modulus vblue is less thbn zero.
     *
     * @see #getModulus
     */
    public void setModulus(Number vblue) throws jbvb.lbng.IllegblArgumentException;

    /**
     * Gets the notificbtion's on/off switch vblue.
     *
     * @return <CODE>true</CODE> if the counter monitor notifies when
     * exceeding the threshold, <CODE>fblse</CODE> otherwise.
     *
     * @see #setNotify
     */
    public boolebn getNotify();

    /**
     * Sets the notificbtion's on/off switch vblue.
     *
     * @pbrbm vblue The notificbtion's on/off switch vblue.
     *
     * @see #getNotify
     */
    public void setNotify(boolebn vblue);

    /**
     * Gets the difference mode flbg vblue.
     *
     * @return <CODE>true</CODE> if the difference mode is used,
     * <CODE>fblse</CODE> otherwise.
     *
     * @see #setDifferenceMode
     */
    public boolebn getDifferenceMode();

    /**
     * Sets the difference mode flbg vblue.
     *
     * @pbrbm vblue The difference mode flbg vblue.
     *
     * @see #getDifferenceMode
     */
    public void setDifferenceMode(boolebn vblue);
}
