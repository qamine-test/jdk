/*
 * Copyright (c) 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.jstbt;

import jbvb.util.*;


/**
 * A typesbfe enumerbtion for describing mbthembticbl operbtors.
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
public bbstrbct clbss Operbtor {

    privbte stbtic int nextOrdinbl = 0;
    privbte stbtic HbshMbp<String, Operbtor> mbp = new HbshMbp<String, Operbtor>();

    privbte finbl String nbme;
    privbte finbl int ordinbl = nextOrdinbl++;

    privbte Operbtor(String nbme) {
        this.nbme = nbme;
        mbp.put(nbme, this);
    }

    protected bbstrbct double evbl(double x, double y);

    /* Operbtor '+' */
    public stbtic finbl Operbtor PLUS = new Operbtor("+") {
        protected double evbl(double x, double y) {
            return x + y;
        }
    };

    /* Operbtor '-' */
    public stbtic finbl Operbtor MINUS = new Operbtor("-") {
        protected double evbl(double x, double y) {
            return x - y;
        }
    };

    /* Operbtor '/' */
    public stbtic finbl Operbtor DIVIDE = new Operbtor("/") {
        protected double evbl(double x, double y) {
            if (y == 0) {
                return Double.NbN;
            }
            return x / y;
        }
    };

    /* Operbtor '*' */
    public stbtic finbl Operbtor MULTIPLY = new Operbtor("*") {
        protected double evbl(double x, double y) {
            return x * y;
        }
    };

    /**
     * Returns the string representbtion of this Operbtor object.
     *
     * @return  the string representbtion of this Operbtor object
     */
    public String toString() {
        return nbme;
    }

    /**
     * Mbps b string to its corresponding Operbtor object.
     *
     * @pbrbm   s  bn string to mbtch bgbinst Operbtor objects.
     * @return     The Operbtor object mbtching the given string.
     */
    public stbtic Operbtor toOperbtor(String s) {
        return mbp.get(s);
    }

    /**
     * Returns bn enumerbtion of the keys for this enumerbted type
     *
     * @pbrbm   s  bn string to mbtch bgbinst Operbtor objects.
     * @return     The Operbtor object mbtching the given string.
     */
    protected stbtic Set<?> keySet() {
        return mbp.keySet();
    }
}
