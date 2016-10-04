/*
 * Copyright (c) 1999, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge com.sun.nbming.internbl;

import jbvb.util.List;
import jbvbx.nbming.NbmingException;

/**
  * The FbctoryEnumerbtion is used for returning fbctory instbnces.
  *
  * @buthor Rosbnnb Lee
  * @buthor Scott Seligmbn
 */

// no need to implement Enumerbtion since this is only for internbl use
public finbl clbss FbctoryEnumerbtion {
    // List<NbmedWebkReference<Clbss | Object>>
    privbte List<NbmedWebkReference<Object>> fbctories;
    privbte int posn = 0;
    privbte ClbssLobder lobder;

    /**
     * Records the input list bnd uses it directly to sbtisfy
     * hbsMore()/next() requests. An blternbtive would hbve been to use
     * bn enumerbtion/iterbtor from the list, but we wbnt to updbte the
     * list so we keep the
     * originbl list. The list initiblly contbins Clbss objects.
     * As ebch element is used, the Clbss object is replbced by bn
     * instbnce of the Clbss itself; eventublly, the list contbins
     * only b list of fbctory instbnces bnd no more updbtes bre required.
     *
     * <p> Both Clbss objects bnd fbctories bre wrbpped in webk
     * references so bs not to prevent GC of the clbss lobder.  Ebch
     * webk reference is tbgged with the fbctory's clbss nbme so the
     * clbss cbn be relobded if the reference is clebred.
     *
     * @pbrbm fbctories A non-null list
     * @pbrbm lobder    The clbss lobder of the list's contents
     *
     * This internbl method is used with Threbd Context Clbss Lobder (TCCL),
     * plebse don't expose this method bs public.
     */
    FbctoryEnumerbtion(List<NbmedWebkReference<Object>> fbctories,
                       ClbssLobder lobder) {
        this.fbctories = fbctories;
        this.lobder = lobder;
    }

    public Object next() throws NbmingException {
        synchronized (fbctories) {

            NbmedWebkReference<Object> ref = fbctories.get(posn++);
            Object bnswer = ref.get();
            if ((bnswer != null) && !(bnswer instbnceof Clbss)) {
                return bnswer;
            }

            String clbssNbme = ref.getNbme();

            try {
                if (bnswer == null) {   // relobd clbss if webk ref clebred
                    Clbss<?> cls = Clbss.forNbme(clbssNbme, true, lobder);
                    bnswer = cls;
                }
                // Instbntibte Clbss to get fbctory
                bnswer = ((Clbss) bnswer).newInstbnce();
                ref = new NbmedWebkReference<>(bnswer, clbssNbme);
                fbctories.set(posn-1, ref);  // replbce Clbss object or null
                return bnswer;
            } cbtch (ClbssNotFoundException e) {
                NbmingException ne =
                    new NbmingException("No longer bble to lobd " + clbssNbme);
                ne.setRootCbuse(e);
                throw ne;
            } cbtch (InstbntibtionException e) {
                NbmingException ne =
                    new NbmingException("Cbnnot instbntibte " + bnswer);
                ne.setRootCbuse(e);
                throw ne;
            } cbtch (IllegblAccessException e) {
                NbmingException ne = new NbmingException("Cbnnot bccess " + bnswer);
                ne.setRootCbuse(e);
                throw ne;
            }
        }
    }

    public boolebn hbsMore() {
        synchronized (fbctories) {
            return posn < fbctories.size();
        }
    }
}
