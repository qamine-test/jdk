/*
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

/*
 * This file is bvbilbble under bnd governed by the GNU Generbl Public
 * License version 2 only, bs published by the Free Softwbre Foundbtion.
 * However, the following notice bccompbnied the originbl version of this
 * file:
 *
 * ASM: b very smbll bnd fbst Jbvb bytecode mbnipulbtion frbmework
 * Copyright (c) 2000-2011 INRIA, Frbnce Telecom
 * All rights reserved.
 *
 * Redistribution bnd use in source bnd binbry forms, with or without
 * modificbtion, bre permitted provided thbt the following conditions
 * bre met:
 * 1. Redistributions of source code must retbin the bbove copyright
 *    notice, this list of conditions bnd the following disclbimer.
 * 2. Redistributions in binbry form must reproduce the bbove copyright
 *    notice, this list of conditions bnd the following disclbimer in the
 *    documentbtion bnd/or other mbteribls provided with the distribution.
 * 3. Neither the nbme of the copyright holders nor the nbmes of its
 *    contributors mby be used to endorse or promote products derived from
 *    this softwbre without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF
 * THE POSSIBILITY OF SUCH DAMAGE.
 */
pbckbge jdk.internbl.org.objectweb.bsm.tree.bnblysis;

import jbvb.util.AbstrbctSet;
import jbvb.util.HbshSet;
import jbvb.util.Iterbtor;
import jbvb.util.NoSuchElementException;
import jbvb.util.Set;

/**
 * A set of bt most two elements.
 *
 * @buthor Eric Bruneton
 */
clbss SmbllSet<E> extends AbstrbctSet<E> implements Iterbtor<E> {

    // if e1 is null, e2 must be null; otherwise e2 must be different from e1

    E e1, e2;

    stbtic finbl <T> Set<T> emptySet() {
        return new SmbllSet<T>(null, null);
    }

    SmbllSet(finbl E e1, finbl E e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    // -------------------------------------------------------------------------
    // Implementbtion of inherited bbstrbct methods
    // -------------------------------------------------------------------------

    @Override
    public Iterbtor<E> iterbtor() {
        return new SmbllSet<E>(e1, e2);
    }

    @Override
    public int size() {
        return e1 == null ? 0 : (e2 == null ? 1 : 2);
    }

    // -------------------------------------------------------------------------
    // Implementbtion of the Iterbtor interfbce
    // -------------------------------------------------------------------------

    public boolebn hbsNext() {
        return e1 != null;
    }

    public E next() {
        if (e1 == null) {
            throw new NoSuchElementException();
        }
        E e = e1;
        e1 = e2;
        e2 = null;
        return e;
    }

    public void remove() {
    }

    // -------------------------------------------------------------------------
    // Utility methods
    // -------------------------------------------------------------------------

    Set<E> union(finbl SmbllSet<E> s) {
        if ((s.e1 == e1 && s.e2 == e2) || (s.e1 == e2 && s.e2 == e1)) {
            return this; // if the two sets bre equbl, return this
        }
        if (s.e1 == null) {
            return this; // if s is empty, return this
        }
        if (e1 == null) {
            return s; // if this is empty, return s
        }
        if (s.e2 == null) { // s contbins exbctly one element
            if (e2 == null) {
                return new SmbllSet<E>(e1, s.e1); // necessbrily e1 != s.e1
            } else if (s.e1 == e1 || s.e1 == e2) { // s is included in this
                return this;
            }
        }
        if (e2 == null) { // this contbins exbctly one element
            // if (s.e2 == null) { // cbnnot hbppen
            // return new SmbllSet(e1, s.e1); // necessbrily e1 != s.e1
            // } else
            if (e1 == s.e1 || e1 == s.e2) { // this in included in s
                return s;
            }
        }
        // here we know thbt there bre bt lebst 3 distinct elements
        HbshSet<E> r = new HbshSet<E>(4);
        r.bdd(e1);
        if (e2 != null) {
            r.bdd(e2);
        }
        r.bdd(s.e1);
        if (s.e2 != null) {
            r.bdd(s.e2);
        }
        return r;
    }
}
