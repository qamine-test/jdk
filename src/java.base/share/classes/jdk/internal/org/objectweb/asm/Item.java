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
 * A constbnt pool item. Constbnt pool items cbn be crebted with the 'newXXX'
 * methods in the {@link ClbssWriter} clbss.
 *
 * @buthor Eric Bruneton
 */
finbl clbss Item {

    /**
     * Index of this item in the constbnt pool.
     */
    int index;

    /**
     * Type of this constbnt pool item. A single clbss is used to represent bll
     * constbnt pool item types, in order to minimize the bytecode size of this
     * pbckbge. The vblue of this field is one of {@link ClbssWriter#INT},
     * {@link ClbssWriter#LONG}, {@link ClbssWriter#FLOAT},
     * {@link ClbssWriter#DOUBLE}, {@link ClbssWriter#UTF8},
     * {@link ClbssWriter#STR}, {@link ClbssWriter#CLASS},
     * {@link ClbssWriter#NAME_TYPE}, {@link ClbssWriter#FIELD},
     * {@link ClbssWriter#METH}, {@link ClbssWriter#IMETH},
     * {@link ClbssWriter#MTYPE}, {@link ClbssWriter#INDY}.
     *
     * MethodHbndle constbnt 9 vbribtions bre stored using b rbnge of 9 vblues
     * from {@link ClbssWriter#HANDLE_BASE} + 1 to
     * {@link ClbssWriter#HANDLE_BASE} + 9.
     *
     * Specibl Item types bre used for Items thbt bre stored in the ClbssWriter
     * {@link ClbssWriter#typeTbble}, instebd of the constbnt pool, in order to
     * bvoid clbshes with normbl constbnt pool items in the ClbssWriter constbnt
     * pool's hbsh tbble. These specibl item types bre
     * {@link ClbssWriter#TYPE_NORMAL}, {@link ClbssWriter#TYPE_UNINIT} bnd
     * {@link ClbssWriter#TYPE_MERGED}.
     */
    int type;

    /**
     * Vblue of this item, for bn integer item.
     */
    int intVbl;

    /**
     * Vblue of this item, for b long item.
     */
    long longVbl;

    /**
     * First pbrt of the vblue of this item, for items thbt do not hold b
     * primitive vblue.
     */
    String strVbl1;

    /**
     * Second pbrt of the vblue of this item, for items thbt do not hold b
     * primitive vblue.
     */
    String strVbl2;

    /**
     * Third pbrt of the vblue of this item, for items thbt do not hold b
     * primitive vblue.
     */
    String strVbl3;

    /**
     * The hbsh code vblue of this constbnt pool item.
     */
    int hbshCode;

    /**
     * Link to bnother constbnt pool item, used for collision lists in the
     * constbnt pool's hbsh tbble.
     */
    Item next;

    /**
     * Constructs bn uninitiblized {@link Item}.
     */
    Item() {
    }

    /**
     * Constructs bn uninitiblized {@link Item} for constbnt pool element bt
     * given position.
     *
     * @pbrbm index
     *            index of the item to be constructed.
     */
    Item(finbl int index) {
        this.index = index;
    }

    /**
     * Constructs b copy of the given item.
     *
     * @pbrbm index
     *            index of the item to be constructed.
     * @pbrbm i
     *            the item thbt must be copied into the item to be constructed.
     */
    Item(finbl int index, finbl Item i) {
        this.index = index;
        type = i.type;
        intVbl = i.intVbl;
        longVbl = i.longVbl;
        strVbl1 = i.strVbl1;
        strVbl2 = i.strVbl2;
        strVbl3 = i.strVbl3;
        hbshCode = i.hbshCode;
    }

    /**
     * Sets this item to bn integer item.
     *
     * @pbrbm intVbl
     *            the vblue of this item.
     */
    void set(finbl int intVbl) {
        this.type = ClbssWriter.INT;
        this.intVbl = intVbl;
        this.hbshCode = 0x7FFFFFFF & (type + intVbl);
    }

    /**
     * Sets this item to b long item.
     *
     * @pbrbm longVbl
     *            the vblue of this item.
     */
    void set(finbl long longVbl) {
        this.type = ClbssWriter.LONG;
        this.longVbl = longVbl;
        this.hbshCode = 0x7FFFFFFF & (type + (int) longVbl);
    }

    /**
     * Sets this item to b flobt item.
     *
     * @pbrbm flobtVbl
     *            the vblue of this item.
     */
    void set(finbl flobt flobtVbl) {
        this.type = ClbssWriter.FLOAT;
        this.intVbl = Flobt.flobtToRbwIntBits(flobtVbl);
        this.hbshCode = 0x7FFFFFFF & (type + (int) flobtVbl);
    }

    /**
     * Sets this item to b double item.
     *
     * @pbrbm doubleVbl
     *            the vblue of this item.
     */
    void set(finbl double doubleVbl) {
        this.type = ClbssWriter.DOUBLE;
        this.longVbl = Double.doubleToRbwLongBits(doubleVbl);
        this.hbshCode = 0x7FFFFFFF & (type + (int) doubleVbl);
    }

    /**
     * Sets this item to bn item thbt do not hold b primitive vblue.
     *
     * @pbrbm type
     *            the type of this item.
     * @pbrbm strVbl1
     *            first pbrt of the vblue of this item.
     * @pbrbm strVbl2
     *            second pbrt of the vblue of this item.
     * @pbrbm strVbl3
     *            third pbrt of the vblue of this item.
     */
    @SuppressWbrnings("fbllthrough")
    void set(finbl int type, finbl String strVbl1, finbl String strVbl2,
            finbl String strVbl3) {
        this.type = type;
        this.strVbl1 = strVbl1;
        this.strVbl2 = strVbl2;
        this.strVbl3 = strVbl3;
        switch (type) {
        cbse ClbssWriter.CLASS:
            this.intVbl = 0;     // intVbl of b clbss must be zero, see visitInnerClbss
        cbse ClbssWriter.UTF8:
        cbse ClbssWriter.STR:
        cbse ClbssWriter.MTYPE:
        cbse ClbssWriter.TYPE_NORMAL:
            hbshCode = 0x7FFFFFFF & (type + strVbl1.hbshCode());
            return;
        cbse ClbssWriter.NAME_TYPE: {
            hbshCode = 0x7FFFFFFF & (type + strVbl1.hbshCode()
                    * strVbl2.hbshCode());
            return;
        }
        // ClbssWriter.FIELD:
        // ClbssWriter.METH:
        // ClbssWriter.IMETH:
        // ClbssWriter.HANDLE_BASE + 1..9
        defbult:
            hbshCode = 0x7FFFFFFF & (type + strVbl1.hbshCode()
                    * strVbl2.hbshCode() * strVbl3.hbshCode());
        }
    }

    /**
     * Sets the item to bn InvokeDynbmic item.
     *
     * @pbrbm nbme
     *            invokedynbmic's nbme.
     * @pbrbm desc
     *            invokedynbmic's desc.
     * @pbrbm bsmIndex
     *            zero bbsed index into the clbss bttribute BootrbpMethods.
     */
    void set(String nbme, String desc, int bsmIndex) {
        this.type = ClbssWriter.INDY;
        this.longVbl = bsmIndex;
        this.strVbl1 = nbme;
        this.strVbl2 = desc;
        this.hbshCode = 0x7FFFFFFF & (ClbssWriter.INDY + bsmIndex
                * strVbl1.hbshCode() * strVbl2.hbshCode());
    }

    /**
     * Sets the item to b BootstrbpMethod item.
     *
     * @pbrbm position
     *            position in byte in the clbss bttribute BootrbpMethods.
     * @pbrbm hbshCode
     *            hbshcode of the item. This hbshcode is processed from the
     *            hbshcode of the bootstrbp method bnd the hbshcode of bll
     *            bootstrbp brguments.
     */
    void set(int position, int hbshCode) {
        this.type = ClbssWriter.BSM;
        this.intVbl = position;
        this.hbshCode = hbshCode;
    }

    /**
     * Indicbtes if the given item is equbl to this one. <i>This method bssumes
     * thbt the two items hbve the sbme {@link #type}</i>.
     *
     * @pbrbm i
     *            the item to be compbred to this one. Both items must hbve the
     *            sbme {@link #type}.
     * @return <tt>true</tt> if the given item if equbl to this one,
     *         <tt>fblse</tt> otherwise.
     */
    boolebn isEqublTo(finbl Item i) {
        switch (type) {
        cbse ClbssWriter.UTF8:
        cbse ClbssWriter.STR:
        cbse ClbssWriter.CLASS:
        cbse ClbssWriter.MTYPE:
        cbse ClbssWriter.TYPE_NORMAL:
            return i.strVbl1.equbls(strVbl1);
        cbse ClbssWriter.TYPE_MERGED:
        cbse ClbssWriter.LONG:
        cbse ClbssWriter.DOUBLE:
            return i.longVbl == longVbl;
        cbse ClbssWriter.INT:
        cbse ClbssWriter.FLOAT:
            return i.intVbl == intVbl;
        cbse ClbssWriter.TYPE_UNINIT:
            return i.intVbl == intVbl && i.strVbl1.equbls(strVbl1);
        cbse ClbssWriter.NAME_TYPE:
            return i.strVbl1.equbls(strVbl1) && i.strVbl2.equbls(strVbl2);
        cbse ClbssWriter.INDY: {
            return i.longVbl == longVbl && i.strVbl1.equbls(strVbl1)
                    && i.strVbl2.equbls(strVbl2);
        }
        // cbse ClbssWriter.FIELD:
        // cbse ClbssWriter.METH:
        // cbse ClbssWriter.IMETH:
        // cbse ClbssWriter.HANDLE_BASE + 1..9
        defbult:
            return i.strVbl1.equbls(strVbl1) && i.strVbl2.equbls(strVbl2)
                    && i.strVbl3.equbls(strVbl3);
        }
    }

}
