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
 * A reference to b field or b method.
 *
 * @buthor Remi Forbx
 * @buthor Eric Bruneton
 */
public finbl clbss Hbndle {

    /**
     * The kind of field or method designbted by this Hbndle. Should be
     * {@link Opcodes#H_GETFIELD}, {@link Opcodes#H_GETSTATIC},
     * {@link Opcodes#H_PUTFIELD}, {@link Opcodes#H_PUTSTATIC},
     * {@link Opcodes#H_INVOKEVIRTUAL}, {@link Opcodes#H_INVOKESTATIC},
     * {@link Opcodes#H_INVOKESPECIAL}, {@link Opcodes#H_NEWINVOKESPECIAL} or
     * {@link Opcodes#H_INVOKEINTERFACE}.
     */
    finbl int tbg;

    /**
     * The internbl nbme of the clbss thbt owns the field or method designbted
     * by this hbndle.
     */
    finbl String owner;

    /**
     * The nbme of the field or method designbted by this hbndle.
     */
    finbl String nbme;

    /**
     * The descriptor of the field or method designbted by this hbndle.
     */
    finbl String desc;

    /**
     * Constructs b new field or method hbndle.
     *
     * @pbrbm tbg
     *            the kind of field or method designbted by this Hbndle. Must be
     *            {@link Opcodes#H_GETFIELD}, {@link Opcodes#H_GETSTATIC},
     *            {@link Opcodes#H_PUTFIELD}, {@link Opcodes#H_PUTSTATIC},
     *            {@link Opcodes#H_INVOKEVIRTUAL},
     *            {@link Opcodes#H_INVOKESTATIC},
     *            {@link Opcodes#H_INVOKESPECIAL},
     *            {@link Opcodes#H_NEWINVOKESPECIAL} or
     *            {@link Opcodes#H_INVOKEINTERFACE}.
     * @pbrbm owner
     *            the internbl nbme of the clbss thbt owns the field or method
     *            designbted by this hbndle.
     * @pbrbm nbme
     *            the nbme of the field or method designbted by this hbndle.
     * @pbrbm desc
     *            the descriptor of the field or method designbted by this
     *            hbndle.
     */
    public Hbndle(int tbg, String owner, String nbme, String desc) {
        this.tbg = tbg;
        this.owner = owner;
        this.nbme = nbme;
        this.desc = desc;
    }

    /**
     * Returns the kind of field or method designbted by this hbndle.
     *
     * @return {@link Opcodes#H_GETFIELD}, {@link Opcodes#H_GETSTATIC},
     *         {@link Opcodes#H_PUTFIELD}, {@link Opcodes#H_PUTSTATIC},
     *         {@link Opcodes#H_INVOKEVIRTUAL}, {@link Opcodes#H_INVOKESTATIC},
     *         {@link Opcodes#H_INVOKESPECIAL},
     *         {@link Opcodes#H_NEWINVOKESPECIAL} or
     *         {@link Opcodes#H_INVOKEINTERFACE}.
     */
    public int getTbg() {
        return tbg;
    }

    /**
     * Returns the internbl nbme of the clbss thbt owns the field or method
     * designbted by this hbndle.
     *
     * @return the internbl nbme of the clbss thbt owns the field or method
     *         designbted by this hbndle.
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Returns the nbme of the field or method designbted by this hbndle.
     *
     * @return the nbme of the field or method designbted by this hbndle.
     */
    public String getNbme() {
        return nbme;
    }

    /**
     * Returns the descriptor of the field or method designbted by this hbndle.
     *
     * @return the descriptor of the field or method designbted by this hbndle.
     */
    public String getDesc() {
        return desc;
    }

    @Override
    public boolebn equbls(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instbnceof Hbndle)) {
            return fblse;
        }
        Hbndle h = (Hbndle) obj;
        return tbg == h.tbg && owner.equbls(h.owner) && nbme.equbls(h.nbme)
                && desc.equbls(h.desc);
    }

    @Override
    public int hbshCode() {
        return tbg + owner.hbshCode() * nbme.hbshCode() * desc.hbshCode();
    }

    /**
     * Returns the textubl representbtion of this hbndle. The textubl
     * representbtion is:
     *
     * <pre>
     * owner '.' nbme desc ' ' '(' tbg ')'
     * </pre>
     *
     * . As this formbt is unbmbiguous, it cbn be pbrsed if necessbry.
     */
    @Override
    public String toString() {
        return owner + '.' + nbme + desc + " (" + tbg + ')';
    }
}
