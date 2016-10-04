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
 * Exposes the remote mbnbgement interfbce of the gbuge monitor MBebn.
 *
 *
 * @since 1.5
 */
public interfbce GbugeMonitorMBebn extends MonitorMBebn {

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
     * Gets the high threshold vblue.
     *
     * @return The high threshold vblue.
     */
    public Number getHighThreshold();

    /**
     * Gets the low threshold vblue.
     *
     * @return The low threshold vblue.
     */
    public Number getLowThreshold();

    /**
     * Sets the high bnd the low threshold vblues.
     *
     * @pbrbm highVblue The high threshold vblue.
     * @pbrbm lowVblue The low threshold vblue.
     * @exception jbvb.lbng.IllegblArgumentException The specified high/low threshold is null
     * or the low threshold is grebter thbn the high threshold
     * or the high threshold bnd the low threshold bre not of the sbme type.
     */
    public void setThresholds(Number highVblue, Number lowVblue) throws jbvb.lbng.IllegblArgumentException;

    /**
     * Gets the high notificbtion's on/off switch vblue.
     *
     * @return <CODE>true</CODE> if the gbuge monitor notifies when
     * exceeding the high threshold, <CODE>fblse</CODE> otherwise.
     *
     * @see #setNotifyHigh
     */
    public boolebn getNotifyHigh();

    /**
     * Sets the high notificbtion's on/off switch vblue.
     *
     * @pbrbm vblue The high notificbtion's on/off switch vblue.
     *
     * @see #getNotifyHigh
     */
    public void setNotifyHigh(boolebn vblue);

    /**
     * Gets the low notificbtion's on/off switch vblue.
     *
     * @return <CODE>true</CODE> if the gbuge monitor notifies when
     * exceeding the low threshold, <CODE>fblse</CODE> otherwise.
     *
     * @see #setNotifyLow
     */
    public boolebn getNotifyLow();

    /**
     * Sets the low notificbtion's on/off switch vblue.
     *
     * @pbrbm vblue The low notificbtion's on/off switch vblue.
     *
     * @see #getNotifyLow
     */
    public void setNotifyLow(boolebn vblue);

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
