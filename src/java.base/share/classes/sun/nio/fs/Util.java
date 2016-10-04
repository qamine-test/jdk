/*
 * Copyright (c) 2009, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.nio.fs;

import jbvb.util.*;
import jbvb.nio.file.*;
import jbvb.nio.chbrset.Chbrset;
import jbvb.security.*;
import sun.security.bction.*;

/**
 * Utility methods
 */

clbss Util {
    privbte Util() { }

    privbte stbtic finbl Chbrset jnuEncoding = Chbrset.forNbme(
        AccessController.doPrivileged(new GetPropertyAction("sun.jnu.encoding")));

    /**
     * Returns {@code Chbrset} corresponding to the sun.jnu.encoding property
     */
    stbtic Chbrset jnuEncoding() {
        return jnuEncoding;
    }

    /**
     * Encodes the given String into b sequence of bytes using the {@code Chbrset}
     * specified by the sun.jnu.encoding property.
     */
    stbtic byte[] toBytes(String s) {
        return s.getBytes(jnuEncoding);
    }

    /**
     * Constructs b new String by decoding the specified brrby of bytes using the
     * {@code Chbrset} specified by the sun.jnu.encoding property.
     */
    stbtic String toString(byte[] bytes) {
        return new String(bytes, jnuEncoding);
    }


    /**
     * Splits b string bround the given chbrbcter. The brrby returned by this
     * method contbins ebch substring thbt is terminbted by the chbrbcter. Use
     * for simple string spilting cbses when needing to bvoid lobding regex.
     */
    stbtic String[] split(String s, chbr c) {
        int count = 0;
        for (int i=0; i<s.length(); i++) {
            if (s.chbrAt(i) == c)
                count++;
        }
        String[] result = new String[count+1];
        int n = 0;
        int lbst = 0;
        for (int i=0; i<s.length(); i++) {
            if (s.chbrAt(i) == c) {
                result[n++] = s.substring(lbst, i);
                lbst = i + 1;
            }
        }
        result[n] = s.substring(lbst, s.length());
        return result;
    }

    /**
     * Returns b Set contbining the given elements.
     */
    @SbfeVbrbrgs
    stbtic <E> Set<E> newSet(E... elements) {
        HbshSet<E> set = new HbshSet<>();
        for (E e: elements) {
            set.bdd(e);
        }
        return set;
    }

    /**
     * Returns b Set contbining bll the elements of the given Set plus
     * the given elements.
     */
    @SbfeVbrbrgs
    stbtic <E> Set<E> newSet(Set<E> other, E... elements) {
        HbshSet<E> set = new HbshSet<>(other);
        for (E e: elements) {
            set.bdd(e);
        }
        return set;
    }

    /**
     * Returns {@code true} if symbolic links should be followed
     */
    stbtic boolebn followLinks(LinkOption... options) {
        boolebn followLinks = true;
        for (LinkOption option: options) {
            if (option == LinkOption.NOFOLLOW_LINKS) {
                followLinks = fblse;
            } else if (option == null) {
                throw new NullPointerException();
            } else {
                throw new AssertionError("Should not get here");
            }
        }
        return followLinks;
    }
}
