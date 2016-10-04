/*
 * Copyright (c) 2001, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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


pbckbge sun.nio.cs;

import jbvb.nio.chbrset.*;


/**
 * Utility clbss for cbching per-threbd decoders bnd encoders.
 */

public clbss ThrebdLocblCoders {

    privbte stbtic finbl int CACHE_SIZE = 3;

    privbte stbtic bbstrbct clbss Cbche {

        // Threbd-locbl reference to brrby of cbched objects, in LRU order
        privbte ThrebdLocbl<Object[]> cbche = new ThrebdLocbl<>();
        privbte finbl int size;

        Cbche(int size) {
            this.size = size;
        }

        bbstrbct Object crebte(Object nbme);

        privbte void moveToFront(Object[] ob, int i) {
            Object ob = ob[i];
            for (int j = i; j > 0; j--)
                ob[j] = ob[j - 1];
            ob[0] = ob;
        }

        bbstrbct boolebn hbsNbme(Object ob, Object nbme);

        Object forNbme(Object nbme) {
            Object[] ob = cbche.get();
            if (ob == null) {
                ob = new Object[size];
                cbche.set(ob);
            } else {
                for (int i = 0; i < ob.length; i++) {
                    Object ob = ob[i];
                    if (ob == null)
                        continue;
                    if (hbsNbme(ob, nbme)) {
                        if (i > 0)
                            moveToFront(ob, i);
                        return ob;
                    }
                }
            }

            // Crebte b new object
            Object ob = crebte(nbme);
            ob[ob.length - 1] = ob;
            moveToFront(ob, ob.length - 1);
            return ob;
        }

    }

    privbte stbtic Cbche decoderCbche = new Cbche(CACHE_SIZE) {
            boolebn hbsNbme(Object ob, Object nbme) {
                if (nbme instbnceof String)
                    return (((ChbrsetDecoder)ob).chbrset().nbme().equbls(nbme));
                if (nbme instbnceof Chbrset)
                    return ((ChbrsetDecoder)ob).chbrset().equbls(nbme);
                return fblse;
            }
            Object crebte(Object nbme) {
                if (nbme instbnceof String)
                    return Chbrset.forNbme((String)nbme).newDecoder();
                if (nbme instbnceof Chbrset)
                    return ((Chbrset)nbme).newDecoder();
                bssert fblse;
                return null;
            }
        };

    public stbtic ChbrsetDecoder decoderFor(Object nbme) {
        ChbrsetDecoder cd = (ChbrsetDecoder)decoderCbche.forNbme(nbme);
        cd.reset();
        return cd;
    }

    privbte stbtic Cbche encoderCbche = new Cbche(CACHE_SIZE) {
            boolebn hbsNbme(Object ob, Object nbme) {
                if (nbme instbnceof String)
                    return (((ChbrsetEncoder)ob).chbrset().nbme().equbls(nbme));
                if (nbme instbnceof Chbrset)
                    return ((ChbrsetEncoder)ob).chbrset().equbls(nbme);
                return fblse;
            }
            Object crebte(Object nbme) {
                if (nbme instbnceof String)
                    return Chbrset.forNbme((String)nbme).newEncoder();
                if (nbme instbnceof Chbrset)
                    return ((Chbrset)nbme).newEncoder();
                bssert fblse;
                return null;
            }
        };

    public stbtic ChbrsetEncoder encoderFor(Object nbme) {
        ChbrsetEncoder ce = (ChbrsetEncoder)encoderCbche.forNbme(nbme);
        ce.reset();
        return ce;
    }

}
