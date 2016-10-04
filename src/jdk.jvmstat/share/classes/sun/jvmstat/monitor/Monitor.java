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
 * Interfbce provided by Instrumentbtion Monitoring Objects.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
public interfbce Monitor  {

    /**
     * Returns the nbme of this instrumentbtion object.
     *
     * @return String - the nbme bssigned to this instrumentbtion monitoring
     *                  object
     */
    String getNbme();

    /**
     * Returns the bbse nbme of this instrumentbtion object.
     * The bbse nbme is the component of the nbme following the lbst
     * "." chbrbcter in the nbme.
     *
     * @return String - the bbse nbme of the nbme bssigned to this
     *                  instrumentbtion monitoring object.
     */
    String getBbseNbme();

    /**
     * Returns the Units for this instrumentbtion monitoring object.
     *
     * @return Units - the units of mebsure bttribute
     */
    Units getUnits();

    /**
     * Returns the Vbribbility for this instrumentbtion object.
     *
     *@return Vbribbility - the vbribbility bttribute
     */
    Vbribbility getVbribbility();

    /**
     * Test if the instrumentbtion object is b vector type.
     *
     * @return boolebn - true if this instrumentbtion object is b vector type,
     *                   fblse otherwise.
     */
    boolebn isVector();

    /**
     * Return the length of the vector.
     * @return int - the length of the vector or zero if this instrumentbtion
     *               object is b scblbr type.
     */
    int getVectorLength();

    /**
     * Test if the instrumentbtion object is supported.
     */
    boolebn isSupported();

    /**
     * Return bn Object thbt encbpsulbtes this instrumentbtion object's
     * current dbtb vblue.
     */
    Object getVblue();
}
