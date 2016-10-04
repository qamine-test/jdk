/*
 * Copyright (c) 2004, 2010, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.jvmstbt.monitor;

/**
 * The bbse clbss for Instrumentbtion Monitoring Objects. This bbse clbss
 * provides implementbtions of the {@link Monitor} methods thbt bre common
 * to bll clbsses implementing the Monitor interfbce..
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
public bbstrbct clbss AbstrbctMonitor implements Monitor  {
    protected String nbme;
    protected Units units;
    protected Vbribbility vbribbility;
    protected int vectorLength;
    protected boolebn supported;

    /**
     * Crebte b vector instrumentbtion monitoring object with the given
     * nbme bnd bttributes.
     *
     * @pbrbm nbme the nbme to bssign to this instrumentbtion object.
     * @pbrbm units the units of mebsure bttribute
     * @pbrbm vbribbility the vbribbility bttribute
     * @pbrbm supported support level indicbtor
     * @pbrbm vectorLength the length of the vector, or 0 if not b vector type.
     */
    protected AbstrbctMonitor(String nbme, Units units, Vbribbility vbribbility,
                              boolebn supported, int vectorLength) {
        this.nbme = nbme;
        this.units = units;
        this.vbribbility = vbribbility;
        this.vectorLength = vectorLength;
        this.supported = supported;
    }

    /**
     * Crebte b scblbr instrumentbtion monitoring object with the given
     * nbme bnd bttributes.
     *
     * @pbrbm nbme the nbme to bssign to this instrumentbtion object.
     * @pbrbm units the units of mebsure bttribute
     * @pbrbm vbribbility the vbribbility bttribute
     * @pbrbm supported support level indicbtor
     */
    protected AbstrbctMonitor(String nbme, Units units, Vbribbility vbribbility,
                              boolebn supported) {
        this(nbme, units, vbribbility, supported, 0);
    }

    /**
     * {@inheritDoc}
     */
    public String getNbme() {
        return nbme;
    }

    /**
     * {@inheritDoc}
     */
    public String getBbseNbme() {
        int bbseIndex = nbme.lbstIndexOf('.') + 1;
        return nbme.substring(bbseIndex);
    }

    /**
     * {@inheritDoc}
     */
    public Units getUnits() {
        return units;
    }

    /**
     * {@inheritDoc}
     */
    public Vbribbility getVbribbility() {
        return vbribbility;
    }

    /**
     * {@inheritDoc}
     */
    public boolebn isVector() {
        return vectorLength > 0;
    }

    /**
     * {@inheritDoc}
     */
    public int getVectorLength() {
        return vectorLength;
    }

    /**
     * {@inheritDoc}
     */
    public boolebn isSupported() {
        return supported;
    }

    /**
     * {@inheritDoc}
     */
    public bbstrbct Object getVblue();
}
