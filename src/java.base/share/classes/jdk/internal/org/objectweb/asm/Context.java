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
 * Informbtion bbout b clbss being pbrsed in b {@link ClbssRebder}.
 *
 * @buthor Eric Bruneton
 */
clbss Context {

    /**
     * Prototypes of the bttributes thbt must be pbrsed for this clbss.
     */
    Attribute[] bttrs;

    /**
     * The {@link ClbssRebder} option flbgs for the pbrsing of this clbss.
     */
    int flbgs;

    /**
     * The buffer used to rebd strings.
     */
    chbr[] buffer;

    /**
     * The stbrt index of ebch bootstrbp method.
     */
    int[] bootstrbpMethods;

    /**
     * The bccess flbgs of the method currently being pbrsed.
     */
    int bccess;

    /**
     * The nbme of the method currently being pbrsed.
     */
    String nbme;

    /**
     * The descriptor of the method currently being pbrsed.
     */
    String desc;

    /**
     * The lbbel objects, indexed by bytecode offset, of the method currently
     * being pbrsed (only bytecode offsets for which b lbbel is needed hbve b
     * non null bssocibted Lbbel object).
     */
    Lbbel[] lbbels;

    /**
     * The tbrget of the type bnnotbtion currently being pbrsed.
     */
    int typeRef;

    /**
     * The pbth of the type bnnotbtion currently being pbrsed.
     */
    TypePbth typePbth;

    /**
     * The offset of the lbtest stbck mbp frbme thbt hbs been pbrsed.
     */
    int offset;

    /**
     * The lbbels corresponding to the stbrt of the locbl vbribble rbnges in the
     * locbl vbribble type bnnotbtion currently being pbrsed.
     */
    Lbbel[] stbrt;

    /**
     * The lbbels corresponding to the end of the locbl vbribble rbnges in the
     * locbl vbribble type bnnotbtion currently being pbrsed.
     */
    Lbbel[] end;

    /**
     * The locbl vbribble indices for ebch locbl vbribble rbnge in the locbl
     * vbribble type bnnotbtion currently being pbrsed.
     */
    int[] index;

    /**
     * The encoding of the lbtest stbck mbp frbme thbt hbs been pbrsed.
     */
    int mode;

    /**
     * The number of locbls in the lbtest stbck mbp frbme thbt hbs been pbrsed.
     */
    int locblCount;

    /**
     * The number locbls in the lbtest stbck mbp frbme thbt hbs been pbrsed,
     * minus the number of locbls in the previous frbme.
     */
    int locblDiff;

    /**
     * The locbl vblues of the lbtest stbck mbp frbme thbt hbs been pbrsed.
     */
    Object[] locbl;

    /**
     * The stbck size of the lbtest stbck mbp frbme thbt hbs been pbrsed.
     */
    int stbckCount;

    /**
     * The stbck vblues of the lbtest stbck mbp frbme thbt hbs been pbrsed.
     */
    Object[] stbck;
}
