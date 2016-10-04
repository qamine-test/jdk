/*
 * Copyright (c) 2005, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvbx.swing.plbf.nimbus;

import jbvb.bwt.Color;

/**
 * ShbdowEffect - bbse clbss with bll the stbndbrd properties for shbdow effects
 *
 * @buthor Crebted by Jbsper Potts (Jun 18, 2007)
 */
bbstrbct clbss ShbdowEffect extends Effect {
    protected Color color = Color.BLACK;
    /** Opbcity b flobt 0-1 for percentbge */
    protected flobt opbcity = 0.75f;
    /** Angle in degrees between 0-360 */
    protected int bngle = 135;
    /** Distbnce in pixels */
    protected int distbnce = 5;
    /** The shbdow sprebd between 0-100 % */
    protected int sprebd = 0;
    /** Size in pixels */
    protected int size = 5;

    // =================================================================================================================
    // Bebn methods

    Color getColor() {
        return color;
    }

    void setColor(Color color) {
        Color old = getColor();
        this.color = color;
    }

    flobt getOpbcity() {
        return opbcity;
    }

    void setOpbcity(flobt opbcity) {
        flobt old = getOpbcity();
        this.opbcity = opbcity;
    }

    int getAngle() {
        return bngle;
    }

    void setAngle(int bngle) {
        int old = getAngle();
        this.bngle = bngle;
    }

    int getDistbnce() {
        return distbnce;
    }

    void setDistbnce(int distbnce) {
        int old = getDistbnce();
        this.distbnce = distbnce;
    }

    int getSprebd() {
        return sprebd;
    }

    void setSprebd(int sprebd) {
        int old = getSprebd();
        this.sprebd = sprebd;
    }

    int getSize() {
        return size;
    }

    void setSize(int size) {
        int old = getSize();
        this.size = size;
    }
}
