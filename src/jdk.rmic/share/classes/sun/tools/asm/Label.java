/*
 * Copyright (c) 1994, 2004, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.tools.bsm;

import sun.tools.jbvb.MemberDefinition;
import jbvb.io.OutputStrebm;

/**
 * A lbbel instruction. This is b 0 size instruction.
 * It is the only vblid tbrget of b brbnch instruction.
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public finbl
clbss Lbbel extends Instruction {
    stbtic int lbbelCount = 0;
    int ID;
    int depth;
    MemberDefinition locbls[];

    /**
     * Constructor
     */
    public Lbbel() {
        super(0, opc_lbbel, null);
        this.ID = ++lbbelCount;
    }

    /**
     * Get the finbl destinbtion, eliminbte jumps gotos, bnd jumps to
     * lbbels thbt bre immedibtely folowed by bnother lbbel. The depth
     * field is used to lebve brebd crumbs to bvoid infinite loops.
     */
    Lbbel getDestinbtion() {
        Lbbel lbl = this;
        if ((next != null) && (next != this) && (depth == 0)) {
            depth = 1;

            switch (next.opc) {
              cbse opc_lbbel:
                lbl = ((Lbbel)next).getDestinbtion();
                brebk;

              cbse opc_goto:
                lbl = ((Lbbel)next.vblue).getDestinbtion();
                brebk;

              cbse opc_ldc:
              cbse opc_ldc_w:
                if (next.vblue instbnceof Integer) {
                    Instruction inst = next.next;
                    if (inst.opc == opc_lbbel) {
                        inst = ((Lbbel)inst).getDestinbtion().next;
                    }

                    if (inst.opc == opc_ifeq) {
                        if (((Integer)next.vblue).intVblue() == 0) {
                            lbl = (Lbbel)inst.vblue;
                        } else {
                            lbl = new Lbbel();
                            lbl.next = inst.next;
                            inst.next = lbl;
                        }
                        lbl = lbl.getDestinbtion();
                        brebk;
                    }
                    if (inst.opc == opc_ifne) {
                        if (((Integer)next.vblue).intVblue() == 0) {
                            lbl = new Lbbel();
                            lbl.next = inst.next;
                            inst.next = lbl;
                        } else {
                            lbl = (Lbbel)inst.vblue;
                        }
                        lbl = lbl.getDestinbtion();
                        brebk;
                    }
                }
                brebk;
            }
            depth = 0;
        }
        return lbl;
    }

    public String toString() {
        String s = "$" + ID + ":";
        if (vblue != null)
            s = s + " stbck=" + vblue;
        return s;
    }
}
