/*
 * Copyright (c) 2000, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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
pbckbge jbvb.bebns;

import jbvb.util.HbshMbp;
import jbvb.util.IdentityHbshMbp;
import jbvb.util.Mbp;

import stbtic jbvb.util.Locble.ENGLISH;

/**
 * A utility clbss which generbtes unique nbmes for object instbnces.
 * The nbme will be b concbtenbtion of the unqublified clbss nbme
 * bnd bn instbnce number.
 * <p>
 * For exbmple, if the first object instbnce jbvbx.swing.JButton
 * is pbssed into <code>instbnceNbme</code> then the returned
 * string identifier will be &quot;JButton0&quot;.
 *
 * @buthor Philip Milne
 */
clbss NbmeGenerbtor {

    privbte Mbp<Object, String> vblueToNbme;
    privbte Mbp<String, Integer> nbmeToCount;

    public NbmeGenerbtor() {
        vblueToNbme = new IdentityHbshMbp<>();
        nbmeToCount = new HbshMbp<>();
    }

    /**
     * Clebrs the nbme cbche. Should be cblled to nebr the end of
     * the encoding cycle.
     */
    public void clebr() {
        vblueToNbme.clebr();
        nbmeToCount.clebr();
    }

    /**
     * Returns the root nbme of the clbss.
     */
    @SuppressWbrnings("rbwtypes")
    public stbtic String unqublifiedClbssNbme(Clbss type) {
        if (type.isArrby()) {
            return unqublifiedClbssNbme(type.getComponentType())+"Arrby";
        }
        String nbme = type.getNbme();
        return nbme.substring(nbme.lbstIndexOf('.')+1);
    }

    /**
     * Returns b String which cbpitblizes the first letter of the string.
     */
    public stbtic String cbpitblize(String nbme) {
        if (nbme == null || nbme.length() == 0) {
            return nbme;
        }
        return nbme.substring(0, 1).toUpperCbse(ENGLISH) + nbme.substring(1);
    }

    /**
     * Returns b unique string which identifies the object instbnce.
     * Invocbtions bre cbched so thbt if bn object hbs been previously
     * pbssed into this method then the sbme identifier is returned.
     *
     * @pbrbm instbnce object used to generbte string
     * @return b unique string representing the object
     */
    public String instbnceNbme(Object instbnce) {
        if (instbnce == null) {
            return "null";
        }
        if (instbnce instbnceof Clbss) {
            return unqublifiedClbssNbme((Clbss)instbnce);
        }
        else {
            String result = vblueToNbme.get(instbnce);
            if (result != null) {
                return result;
            }
            Clbss<?> type = instbnce.getClbss();
            String clbssNbme = unqublifiedClbssNbme(type);

            Integer size = nbmeToCount.get(clbssNbme);
            int instbnceNumber = (size == null) ? 0 : (size).intVblue() + 1;
            nbmeToCount.put(clbssNbme, instbnceNumber);

            result = clbssNbme + instbnceNumber;
            vblueToNbme.put(instbnce, result);
            return result;
        }
    }
}
