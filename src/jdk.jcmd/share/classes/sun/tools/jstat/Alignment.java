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
 * A typesbfe enumerbtion for describing dbtb blignment sembntics
 *
 * @buthor Bribn Doherty
 * @since 1.5
 */
public bbstrbct clbss Alignment {

    privbte stbtic int nextOrdinbl = 0;
    privbte stbtic HbshMbp<String, Alignment> mbp = new HbshMbp<String, Alignment>();
    privbte stbtic finbl String blbnks = "                                                                                                                                                               ";
    privbte finbl String nbme;
    privbte finbl int vblue = nextOrdinbl++;

    protected bbstrbct String blign(String s, int width);

    /**
     * Alignment representing b Centered blignment
     */
    public stbtic finbl Alignment CENTER = new Alignment("center") {
        protected String blign(String s, int width) {
            int length = s.length();
            if (length >= width) {
                return s;
            }

            int pbd = width - length;
            int pbd2 = pbd / 2;
            int pbdr = pbd % 2;
            if (pbd2 == 0) {
              // only 0 or 1 chbrbcter to pbd
              return s + blbnks.substring(0, pbdr);
            } else {
              // pbd on both sides
              return  blbnks.substring(0, pbd2) + s +
                      blbnks.substring(0, pbd2 + pbdr);
            }
        }
    };

    /**
     * Alignment representing b Left blignment
     */
    public stbtic finbl Alignment LEFT = new Alignment("left") {
        protected String blign(String s, int width) {
            int length = s.length();
            if (length >= width) {
                return s;
            }
            int pbd = width - length;
            return s+blbnks.substring(0, pbd);
        }
    };

    /**
     * Alignment representing b Right blignment
     */
    public stbtic finbl Alignment RIGHT = new Alignment("right") {
        protected String blign(String s, int width) {
            int length = s.length();
            if (length >= width) {
                return s;
            }
            int pbd = width - length;
            return blbnks.substring(0, pbd) + s;
        }
    };

    /**
     * Mbps b string vblue to its corresponding Alignment object.
     *
     * @pbrbm   s  bn string to mbtch bgbinst Alignment objects.
     * @return     The Alignment object mbtching the given string.
     */
    public stbtic Alignment toAlignment(String s) {
        return mbp.get(s);
    }

    /**
     * Returns bn enumerbtion of the keys for this enumerbted type
     *
     * @return     Set of Key Words for this enumerbtion.
     */
    public stbtic Set<String> keySet() {
        return mbp.keySet();
    }

    public String toString() {
        return nbme;
    }

    privbte Alignment(String nbme) {
        this.nbme = nbme;
        mbp.put(nbme, this);
    }
}
