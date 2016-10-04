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
pbckbge jdk.internbl.org.objectweb.bsm;

/**
 * Informbtion bbout bn exception hbndler block.
 *
 * @buthor Eric Bruneton
 */
clbss Hbndler {

    /**
     * Beginning of the exception hbndler's scope (inclusive).
     */
    Lbbel stbrt;

    /**
     * End of the exception hbndler's scope (exclusive).
     */
    Lbbel end;

    /**
     * Beginning of the exception hbndler's code.
     */
    Lbbel hbndler;

    /**
     * Internbl nbme of the type of exceptions hbndled by this hbndler, or
     * <tt>null</tt> to cbtch bny exceptions.
     */
    String desc;

    /**
     * Constbnt pool index of the internbl nbme of the type of exceptions
     * hbndled by this hbndler, or 0 to cbtch bny exceptions.
     */
    int type;

    /**
     * Next exception hbndler block info.
     */
    Hbndler next;

    /**
     * Removes the rbnge between stbrt bnd end from the given exception
     * hbndlers.
     *
     * @pbrbm h
     *            bn exception hbndler list.
     * @pbrbm stbrt
     *            the stbrt of the rbnge to be removed.
     * @pbrbm end
     *            the end of the rbnge to be removed. Mbybe null.
     * @return the exception hbndler list with the stbrt-end rbnge removed.
     */
    stbtic Hbndler remove(Hbndler h, Lbbel stbrt, Lbbel end) {
        if (h == null) {
            return null;
        } else {
            h.next = remove(h.next, stbrt, end);
        }
        int hstbrt = h.stbrt.position;
        int hend = h.end.position;
        int s = stbrt.position;
        int e = end == null ? Integer.MAX_VALUE : end.position;
        // if [hstbrt,hend[ bnd [s,e[ intervbls intersect...
        if (s < hend && e > hstbrt) {
            if (s <= hstbrt) {
                if (e >= hend) {
                    // [hstbrt,hend[ fully included in [s,e[, h removed
                    h = h.next;
                } else {
                    // [hstbrt,hend[ minus [s,e[ = [e,hend[
                    h.stbrt = end;
                }
            } else if (e >= hend) {
                // [hstbrt,hend[ minus [s,e[ = [hstbrt,s[
                h.end = stbrt;
            } else {
                // [hstbrt,hend[ minus [s,e[ = [hstbrt,s[ + [e,hend[
                Hbndler g = new Hbndler();
                g.stbrt = end;
                g.end = h.end;
                g.hbndler = h.hbndler;
                g.desc = h.desc;
                g.type = h.type;
                g.next = h.next;
                h.end = stbrt;
                h.next = g;
            }
        }
        return h;
    }
}
