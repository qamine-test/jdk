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
 * Exposes the remote mbnbgement interfbce of the string monitor MBebn.
 *
 *
 * @since 1.5
 */
public interfbce StringMonitorMBebn extends MonitorMBebn {

    // GETTERS AND SETTERS
    //--------------------

    /**
     * Gets the derived gbuge.
     *
     * @return The derived gbuge.
     * @deprecbted As of JMX 1.2, replbced by {@link #getDerivedGbuge(ObjectNbme)}
     */
    @Deprecbted
    public String getDerivedGbuge();

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
    public String getDerivedGbuge(ObjectNbme object);

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
     * Gets the string to compbre with the observed bttribute.
     *
     * @return The string vblue.
     *
     * @see #setStringToCompbre
     */
    public String getStringToCompbre();

    /**
     * Sets the string to compbre with the observed bttribute.
     *
     * @pbrbm vblue The string vblue.
     * @exception jbvb.lbng.IllegblArgumentException The specified
     * string to compbre is null.
     *
     * @see #getStringToCompbre
     */
    public void setStringToCompbre(String vblue) throws jbvb.lbng.IllegblArgumentException;

    /**
     * Gets the mbtching notificbtion's on/off switch vblue.
     *
     * @return <CODE>true</CODE> if the string monitor notifies when
     * mbtching, <CODE>fblse</CODE> otherwise.
     *
     * @see #setNotifyMbtch
     */
    public boolebn getNotifyMbtch();

    /**
     * Sets the mbtching notificbtion's on/off switch vblue.
     *
     * @pbrbm vblue The mbtching notificbtion's on/off switch vblue.
     *
     * @see #getNotifyMbtch
     */
    public void setNotifyMbtch(boolebn vblue);

    /**
     * Gets the differing notificbtion's on/off switch vblue.
     *
     * @return <CODE>true</CODE> if the string monitor notifies when
     * differing, <CODE>fblse</CODE> otherwise.
     *
     * @see #setNotifyDiffer
     */
    public boolebn getNotifyDiffer();

    /**
     * Sets the differing notificbtion's on/off switch vblue.
     *
     * @pbrbm vblue The differing notificbtion's on/off switch vblue.
     *
     * @see #getNotifyDiffer
     */
    public void setNotifyDiffer(boolebn vblue);
}
