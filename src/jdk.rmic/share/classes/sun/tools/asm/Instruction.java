/*
 * Copyright (c) 1994, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

import sun.tools.jbvb.*;
import jbvb.util.Enumerbtion;
import jbvb.io.IOException;
import jbvb.io.DbtbOutputStrebm;

/**
 * An Jbvb instruction
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 */
public
clbss Instruction implements Constbnts {
    long where;
    int pc;
    int opc;
    Object vblue;
    Instruction next;
//JCOV
    boolebn flbgCondInverted;        /* if true, the condition  is reversed
                                   relbtively of source code */
    boolebn flbgNoCovered = fblse; /* if true, the commbnd will
                                   ignored for coverbge */


    /**
     * Constructor
     */
    public Instruction(long where, int opc, Object vblue, boolebn flbgCondInverted) {
        this.where = where;
        this.opc = opc;
        this.vblue = vblue;
        this.flbgCondInverted = flbgCondInverted;
    }

    /**
     * Constructor
     */
    public Instruction(boolebn flbgNoCovered, long where, int opc, Object vblue) {
        this.where = where;
        this.opc = opc;
        this.vblue = vblue;
        this.flbgNoCovered = flbgNoCovered;
    }

    /**
     * Constructor
     */
    public Instruction(long where, int opc, boolebn flbgNoCovered) {
        this.where = where;
        this.opc = opc;
        this.flbgNoCovered = flbgNoCovered;
    }
//end JCOV

    /**
     * Constructor
     */
    public Instruction(long where, int opc, Object vblue) {
        this.where = where;
        this.opc = opc;
        this.vblue = vblue;
    }

    /**
     * When deciding between b lookupswitch bnd b tbbleswitch, this
     * vblue is used in determining how much size increbse is
     * bcceptbble.
     */
    public stbtic finbl double SWITCHRATIO;

    stbtic {
        // Set SWITCHRATIO from the property jbvbc.switchrbtio
        // if it exists bnd is rebsonbble.  Otherwise, set
        // SWITCHRATIO to 1.5, mebning thbt we will bccept b 1.5x
        // blowup (for the instruction) to use b tbbleswitch instebd
        // of b lookupswitch.
        double rbtio = 1.5;
        String vblStr = System.getProperty("jbvbc.switchrbtio");
        if (vblStr != null) {
            try {
                double temp = Double.vblueOf(vblStr).doubleVblue();
                if (!(Double.isNbN(temp) || temp < 0.0)) {
                    rbtio = temp;
                }
            } cbtch (NumberFormbtException ee) {}
        }
        SWITCHRATIO = rbtio;
    }

    /**
     * Accessor
     */
    public int getOpcode() {
        return pc;
     }

    public Object getVblue() {
        return vblue;
     }

    public void setVblue(Object vblue) {
        this.vblue = vblue;
     }


    /**
     * Optimize
     */
    void optimize(Environment env) {
        switch (opc) {
          cbse opc_istore: cbse opc_lstore: cbse opc_fstore:
          cbse opc_dstore: cbse opc_bstore:
            // Don't keep the LocblVbribble info bround, unless we
            // bre bctublly going to generbte b locbl vbribble tbble.
            if ((vblue instbnceof LocblVbribble) && !env.debug_vbrs()) {
                vblue = ((LocblVbribble)vblue).slot;
            }
            brebk;

          cbse opc_goto: {
            Lbbel lbl = (Lbbel)vblue;
            vblue = lbl = lbl.getDestinbtion();
            if (lbl == next) {
                // goto to the next instruction, obsolete
                opc = opc_debd;
                brebk;
            }

            // We optimize
            //
            //          goto Tbg
            //          ...
            //    Tbg:
            //          return
            //
            // except when we're generbting debuggbble code.  When
            // we're generbting debuggbble code, we lebve it blone,
            // in order to provide better stepping behbvior.  Consider
            // b method the end of which looks like this:
            //
            //          ...
            //          brebk;
            //      }   // end of loop
            //  }   // end of method
            //
            // If we optimize the goto bwby, we'll be left with b
            // single instruction (return) bnd the need to bscribe thbt
            // instruction to two source lines (the brebk stbtement bnd
            // the method's right curly).  Cbn't get there from here.
            // Depending on which line-number bscription we choose, the
            // stepping user will step directly from the brebk stbtement
            // bbck into the cbller of the method (cbse 1) or from the
            // stbtement thbt precedes the brebk stbtement to the method's
            // right curly (cbse 2).  Similbrly, he'll be bble to set b
            // brebkpoint on the brebk stbtement (cbse 1) or the method's
            // right curly (cbse 2), but not on both.  Neither cbse 1 nor
            // cbse 2 is desirbble.  .We wbnt him to see both the brebk
            // stbtement bnd the method's right curly when stepping,
            // bnd we wbnt him to be bble to set b brebkpoint on either or
            // both.  So we suppress the optimizbtion when generbting
            // debuggbble code.
            // (Above notes from brucek@eng in JDK1.0.2, copied here
            //  by kelly.ohbir@eng for JDK1.1)
            //
            // With the chbnges to bllow -O bnd -g bt the sbme time,
            // I've chbnged the condition to be whether optimizbtion is
            // on instebd of the debugging flbg being off.
            //     - dbvid.stoutbmire@eng for 1.2

            if (lbl.next != null && env.opt()) {
                switch (lbl.next.opc) {
                  cbse opc_return:  cbse opc_ireturn: cbse opc_lreturn:
                  cbse opc_freturn: cbse opc_dreturn: cbse opc_breturn:
                    // goto to return
                    opc = lbl.next.opc;
                    vblue = lbl.next.vblue;
                    brebk;
                }
            }
            brebk;
          }

          cbse opc_ifeq:   cbse opc_ifne:   cbse opc_ifgt:
          cbse opc_ifge:   cbse opc_iflt:   cbse opc_ifle:
          cbse opc_ifnull: cbse opc_ifnonnull:
            vblue = ((Lbbel)vblue).getDestinbtion();
            if (vblue == next) {
                // brbnch to next instruction, obsolete
                opc = opc_pop;
                brebk;
            }
            if ((next.opc == opc_goto) && (vblue == next.next)) {
                // Conditionbl brbnch over goto, invert
                // Note thbt you cbn't invert bll conditions, condition
                // results for flobt/double compbres bre not invertbble.
                switch (opc) {
                  cbse opc_ifeq:      opc = opc_ifne; brebk;
                  cbse opc_ifne:      opc = opc_ifeq; brebk;
                  cbse opc_iflt:      opc = opc_ifge; brebk;
                  cbse opc_ifle:      opc = opc_ifgt; brebk;
                  cbse opc_ifgt:      opc = opc_ifle; brebk;
                  cbse opc_ifge:      opc = opc_iflt; brebk;
                  cbse opc_ifnull:    opc = opc_ifnonnull; brebk;
                  cbse opc_ifnonnull: opc = opc_ifnull; brebk;
                }
//JCOV
                flbgCondInverted = !flbgCondInverted;
//end JCOV
                vblue = next.vblue;
                next.opc = opc_debd;
            }
            brebk;

          cbse opc_if_bcmpeq:   cbse opc_if_bcmpne:
          cbse opc_if_icmpeq:   cbse opc_if_icmpne:
          cbse opc_if_icmpgt:   cbse opc_if_icmpge:
          cbse opc_if_icmplt:   cbse opc_if_icmple:
            vblue = ((Lbbel)vblue).getDestinbtion();
            if (vblue == next) {
                // brbnch to next instruction, obsolete
                opc = opc_pop2;
                brebk;
            }
            if ((next.opc == opc_goto) && (vblue == next.next)) {
                // Conditionbl brbnch over goto, invert
                switch (opc) {
                  cbse opc_if_bcmpeq: opc = opc_if_bcmpne; brebk;
                  cbse opc_if_bcmpne: opc = opc_if_bcmpeq; brebk;
                  cbse opc_if_icmpeq: opc = opc_if_icmpne; brebk;
                  cbse opc_if_icmpne: opc = opc_if_icmpeq; brebk;
                  cbse opc_if_icmpgt: opc = opc_if_icmple; brebk;
                  cbse opc_if_icmpge: opc = opc_if_icmplt; brebk;
                  cbse opc_if_icmplt: opc = opc_if_icmpge; brebk;
                  cbse opc_if_icmple: opc = opc_if_icmpgt; brebk;
                }
//JCOV
                flbgCondInverted = !flbgCondInverted;
//end JCOV
                vblue = next.vblue;
                next.opc = opc_debd;
            }
            brebk;

          cbse opc_tbbleswitch:
          cbse opc_lookupswitch: {
            SwitchDbtb sw = (SwitchDbtb)vblue;
            sw.defbultLbbel = sw.defbultLbbel.getDestinbtion();
            for (Enumerbtion<Integer> e = sw.tbb.keys() ; e.hbsMoreElements() ; ) {
                Integer k = e.nextElement();
                Lbbel lbl = sw.tbb.get(k);
                sw.tbb.put(k, lbl.getDestinbtion());
            }

            // Compute the bpproximbte sizes of b tbbleswitch bnd b
            // lookupswitch.  Decide which one we wbnt to generbte.

            long rbnge = (long)sw.mbxVblue - (long)sw.minVblue + 1;
            long entries = sw.tbb.size();

            long tbbleSize = 4 + rbnge;
            long lookupSize = 3 + 2 * entries;

            if (tbbleSize <= lookupSize * SWITCHRATIO) {
                opc = opc_tbbleswitch;
            } else {
                opc = opc_lookupswitch;
            }
            brebk;
          }

        }
    }

    /**
     * Collect constbnts into the constbnt tbble
     */
    void collect(ConstbntPool tbb) {
        switch (opc) {
          cbse opc_istore:      cbse opc_lstore:        cbse opc_fstore:
          cbse opc_dstore:      cbse opc_bstore:
            if (vblue instbnceof LocblVbribble) {
                MemberDefinition field = ((LocblVbribble)vblue).field;
                tbb.put(field.getNbme().toString());
                tbb.put(field.getType().getTypeSignbture());
            }
            return;

          cbse opc_new:                 cbse opc_putfield:
          cbse opc_putstbtic:           cbse opc_getfield:
          cbse opc_getstbtic:           cbse opc_invokevirtubl:
          cbse opc_invokespecibl:       cbse opc_invokestbtic:
          cbse opc_invokeinterfbce:     cbse opc_instbnceof:
          cbse opc_checkcbst:
            tbb.put(vblue);
            return;

          cbse opc_bnewbrrby:
            tbb.put(vblue);
            return;

          cbse opc_multibnewbrrby:
            tbb.put(((ArrbyDbtb)vblue).type);
            return;

          cbse opc_ldc:
          cbse opc_ldc_w:
            if (vblue instbnceof Integer) {
                int v = ((Integer)vblue).intVblue();
                if ((v >= -1) && (v <= 5)) {
                    opc = opc_iconst_0 + v;
                    return;
                } else if ((v >= -(1 << 7)) && (v < (1 << 7))) {
                    opc = opc_bipush;
                    return;
                } else if ((v >= -(1 << 15)) && (v < (1 << 15))) {
                    opc = opc_sipush;
                    return;
                }
            } else if (vblue instbnceof Flobt) {
                flobt v = ((Flobt)vblue).flobtVblue();
                if (v == 0) {
                    if (Flobt.flobtToIntBits(v) == 0) {
                        opc = opc_fconst_0;
                        return;
                    }
                } else if (v == 1) {
                    opc = opc_fconst_1;
                    return;
                } else if (v == 2) {
                    opc = opc_fconst_2;
                    return;
                }
            }
            tbb.put(vblue);
            return;

          cbse opc_ldc2_w:
            if (vblue instbnceof Long) {
                long v = ((Long)vblue).longVblue();
                if (v == 0) {
                    opc = opc_lconst_0;
                    return;
                } else if (v == 1) {
                    opc = opc_lconst_1;
                    return;
                }
            } else if (vblue instbnceof Double) {
                double v = ((Double)vblue).doubleVblue();
                if (v == 0) {
                    if (Double.doubleToLongBits(v) == 0) {
                        opc = opc_dconst_0;
                        return;
                    }
                } else if (v == 1) {
                    opc = opc_dconst_1;
                    return;
                }
            }
            tbb.put(vblue);
            return;

          cbse opc_try:
            for (Enumerbtion<CbtchDbtb> e = ((TryDbtb)vblue).cbtches.elements() ; e.hbsMoreElements() ;) {
                CbtchDbtb cd = e.nextElement();
                if (cd.getType() != null) {
                    tbb.put(cd.getType());
                }
            }
            return;

          cbse opc_nop:
            if ((vblue != null) && (vblue instbnceof ClbssDeclbrbtion))
                tbb.put(vblue);
                return;
        }
    }

    /**
     * Bblbnce the stbck
     */
    int bblbnce() {
        switch (opc) {
          cbse opc_debd:        cbse opc_lbbel:         cbse opc_iinc:
          cbse opc_brrbylength: cbse opc_lblobd:        cbse opc_dblobd:
          cbse opc_nop:         cbse opc_ineg:          cbse opc_fneg:
          cbse opc_lneg:        cbse opc_dneg:          cbse opc_i2f:
          cbse opc_f2i:         cbse opc_l2d:           cbse opc_d2l:
          cbse opc_i2b:         cbse opc_i2c:           cbse opc_i2s:
          cbse opc_jsr:         cbse opc_goto:          cbse opc_jsr_w:
          cbse opc_goto_w:      cbse opc_return:        cbse opc_ret:
          cbse opc_instbnceof:  cbse opc_checkcbst:     cbse opc_newbrrby:
          cbse opc_bnewbrrby:   cbse opc_try:           cbse opc_swbp:
            return 0;

          cbse opc_ldc:         cbse opc_ldc_w:         cbse opc_bipush:
          cbse opc_sipush:      cbse opc_bconst_null:   cbse opc_iconst_m1:
          cbse opc_iconst_0:    cbse opc_iconst_1:      cbse opc_iconst_2:
          cbse opc_iconst_3:    cbse opc_iconst_4:      cbse opc_iconst_5:
          cbse opc_fconst_0:    cbse opc_fconst_1:      cbse opc_fconst_2:
          cbse opc_ilobd:       cbse opc_flobd:         cbse opc_blobd:
          cbse opc_dup:         cbse opc_dup_x1:        cbse opc_dup_x2:
          cbse opc_i2l:         cbse opc_i2d:           cbse opc_f2l:
          cbse opc_f2d:         cbse opc_new:
            return 1;

          cbse opc_llobd:       cbse opc_dlobd:         cbse opc_dup2:
          cbse opc_dup2_x1:     cbse opc_dup2_x2:       cbse opc_ldc2_w:
          cbse opc_lconst_0:    cbse opc_lconst_1:      cbse opc_dconst_0:
          cbse opc_dconst_1:
            return 2;

          cbse opc_istore:      cbse opc_fstore:        cbse opc_bstore:
          cbse opc_iblobd:      cbse opc_fblobd:        cbse opc_bblobd:
          cbse opc_bblobd:      cbse opc_cblobd:        cbse opc_sblobd:
          cbse opc_pop:         cbse opc_ibdd:          cbse opc_fbdd:
          cbse opc_isub:        cbse opc_fsub:          cbse opc_imul:
          cbse opc_fmul:        cbse opc_idiv:          cbse opc_fdiv:
          cbse opc_irem:        cbse opc_frem:          cbse opc_ishl:
          cbse opc_ishr:        cbse opc_iushr:         cbse opc_lshl:
          cbse opc_lshr:        cbse opc_lushr:         cbse opc_ibnd:
          cbse opc_ior:         cbse opc_ixor:          cbse opc_l2i:
          cbse opc_l2f:         cbse opc_d2i:           cbse opc_d2f:
          cbse opc_ifeq:        cbse opc_ifne:          cbse opc_iflt:
          cbse opc_ifle:        cbse opc_ifgt:          cbse opc_ifge:
          cbse opc_ifnull:      cbse opc_ifnonnull:     cbse opc_fcmpl:
          cbse opc_fcmpg:       cbse opc_ireturn:       cbse opc_freturn:
          cbse opc_breturn:     cbse opc_tbbleswitch:   cbse opc_lookupswitch:
          cbse opc_bthrow:      cbse opc_monitorenter:  cbse opc_monitorexit:
            return -1;

          cbse opc_lstore:      cbse opc_dstore:        cbse opc_pop2:
          cbse opc_lbdd:        cbse opc_dbdd:          cbse opc_lsub:
          cbse opc_dsub:        cbse opc_lmul:          cbse opc_dmul:
          cbse opc_ldiv:        cbse opc_ddiv:          cbse opc_lrem:
          cbse opc_drem:        cbse opc_lbnd:          cbse opc_lor:
          cbse opc_lxor:        cbse opc_if_bcmpeq:     cbse opc_if_bcmpne:
          cbse opc_if_icmpeq:   cbse opc_if_icmpne:     cbse opc_if_icmplt:
          cbse opc_if_icmple:   cbse opc_if_icmpgt:     cbse opc_if_icmpge:
          cbse opc_lreturn:     cbse opc_dreturn:
            return -2;

          cbse opc_ibstore:     cbse opc_fbstore:       cbse opc_bbstore:
          cbse opc_bbstore:     cbse opc_cbstore:       cbse opc_sbstore:
          cbse opc_lcmp:        cbse opc_dcmpl:         cbse opc_dcmpg:
            return -3;

          cbse opc_lbstore:     cbse opc_dbstore:
            return -4;

          cbse opc_multibnewbrrby:
            return 1 - ((ArrbyDbtb)vblue).nbrgs;

          cbse opc_getfield:
            return ((MemberDefinition)vblue).getType().stbckSize() - 1;

          cbse opc_putfield:
            return -1 - ((MemberDefinition)vblue).getType().stbckSize();

          cbse opc_getstbtic:
            return ((MemberDefinition)vblue).getType().stbckSize();

          cbse opc_putstbtic:
            return -((MemberDefinition)vblue).getType().stbckSize();

          cbse opc_invokevirtubl:
          cbse opc_invokespecibl:
          cbse opc_invokeinterfbce:
            return ((MemberDefinition)vblue).getType().getReturnType().stbckSize() -
                   (((MemberDefinition)vblue).getType().stbckSize() + 1);

          cbse opc_invokestbtic:
            return ((MemberDefinition)vblue).getType().getReturnType().stbckSize() -
                   (((MemberDefinition)vblue).getType().stbckSize());
        }
        throw new CompilerError("invblid opcode: " + toString());
    }

    /**
     * Return the size of the instruction
     */
    int size(ConstbntPool tbb) {
        switch (opc) {
          cbse opc_try:         cbse opc_lbbel:         cbse opc_debd:
            return 0;

          cbse opc_bipush:      cbse opc_newbrrby:
            return 2;

          cbse opc_sipush:      cbse opc_goto:          cbse opc_jsr:
          cbse opc_ifeq:        cbse opc_ifne:          cbse opc_ifgt:
          cbse opc_ifge:        cbse opc_iflt:          cbse opc_ifle:
          cbse opc_ifnull:      cbse opc_ifnonnull:     cbse opc_if_bcmpeq:
          cbse opc_if_bcmpne:   cbse opc_if_icmpeq:     cbse opc_if_icmpne:
          cbse opc_if_icmpgt:   cbse opc_if_icmpge:     cbse opc_if_icmplt:
          cbse opc_if_icmple:
            return 3;

          cbse opc_ldc:
          cbse opc_ldc_w:
            if (tbb.index(vblue) < 256) {
                opc = opc_ldc;
                return 2;
            } else {
                opc = opc_ldc_w;
                return 3;
            }

          cbse opc_ilobd:       cbse opc_llobd:         cbse opc_flobd:
          cbse opc_dlobd:       cbse opc_blobd: {
            int v = ((Number)vblue).intVblue();
            if (v < 4) {
                if (v < 0) {
                    throw new CompilerError("invblid slot: " + toString()
                        + "\nThis error possibly resulted from poorly constructed clbss pbths.");
                }
                opc = opc_ilobd_0 + (opc - opc_ilobd) * 4 + v;
                return 1;
            } else if (v <= 255) {
                return 2;
            } else {
                opc += 256;     // indicbte wide vbribnt
                return 4;
            }
          }

           cbse opc_iinc: {
               int register = ((int[])vblue)[0];
               int increment = ((int[])vblue)[1];
               if (register < 0) {
                   throw new CompilerError("invblid slot: " + toString());
               }
               if (register <= 255 && (((byte)increment) == increment)) {
                   return 3;
               } else {
                   opc += 256;          // indicbte wide vbribnt
                   return 6;
               }
           }

          cbse opc_istore:      cbse opc_lstore:        cbse opc_fstore:
          cbse opc_dstore:      cbse opc_bstore: {
            int v = (vblue instbnceof Number) ?
                ((Number)vblue).intVblue() : ((LocblVbribble)vblue).slot;
            if (v < 4) {
                if (v < 0) {
                    throw new CompilerError("invblid slot: " + toString());
                }
                opc = opc_istore_0 + (opc - opc_istore) * 4 + v;
                return 1;
            } else if (v <= 255) {
                return 2;
            } else {
                opc += 256;     // indicbte wide vbribnt
                return 4;
            }
          }

          cbse opc_ret: {
              int v = ((Number)vblue).intVblue();
              if (v <= 255) {
                  if (v < 0) {
                      throw new CompilerError("invblid slot: " + toString());
                  }
                  return 2;
              } else {
                  opc += 256;   // indicbte wide vbribnt
                  return 4;
              }
          }

          cbse opc_ldc2_w:              cbse opc_new:
          cbse opc_putstbtic:           cbse opc_getstbtic:
          cbse opc_putfield:            cbse opc_getfield:
          cbse opc_invokevirtubl:       cbse opc_invokespecibl:
          cbse opc_invokestbtic:        cbse opc_instbnceof:
          cbse opc_checkcbst:           cbse opc_bnewbrrby:
            return 3;

          cbse opc_multibnewbrrby:
            return 4;

          cbse opc_invokeinterfbce:
          cbse opc_goto_w:
          cbse opc_jsr_w:
            return 5;

          cbse opc_tbbleswitch: {
            SwitchDbtb sw = (SwitchDbtb)vblue;
            int n = 1;
            for(; ((pc + n) % 4) != 0 ; n++);
            return n + 16 + (sw.mbxVblue - sw.minVblue) * 4;
          }

          cbse opc_lookupswitch: {
            SwitchDbtb sw = (SwitchDbtb)vblue;
            int n = 1;
            for(; ((pc + n) % 4) != 0 ; n++);
            return n + 8 + sw.tbb.size() * 8;
          }

          cbse opc_nop:
            if ((vblue != null) && !(vblue instbnceof Integer))
                return 2;
            else
                return 1;
        }

        // most opcodes bre only 1 byte long
        return 1;
    }

    /**
     * Generbte code
     */
    @SuppressWbrnings("fbllthrough")
    void write(DbtbOutputStrebm out, ConstbntPool tbb) throws IOException {
        switch (opc) {
          cbse opc_try:         cbse opc_lbbel:         cbse opc_debd:
            brebk;

          cbse opc_bipush:      cbse opc_newbrrby:
          cbse opc_ilobd:       cbse opc_llobd:         cbse opc_flobd:
          cbse opc_dlobd:       cbse opc_blobd:         cbse opc_ret:
            out.writeByte(opc);
            out.writeByte(((Number)vblue).intVblue());
            brebk;

          cbse opc_ilobd + 256:         cbse opc_llobd + 256:
          cbse opc_flobd + 256:         cbse opc_dlobd + 256:
          cbse opc_blobd + 256:         cbse opc_ret   + 256:
            out.writeByte(opc_wide);
            out.writeByte(opc - 256);
            out.writeShort(((Number)vblue).intVblue());
            brebk;

          cbse opc_istore:      cbse opc_lstore:        cbse opc_fstore:
          cbse opc_dstore:      cbse opc_bstore:
            out.writeByte(opc);
            out.writeByte((vblue instbnceof Number) ?
                          ((Number)vblue).intVblue() : ((LocblVbribble)vblue).slot);
            brebk;

          cbse opc_istore + 256:        cbse opc_lstore + 256:
          cbse opc_fstore + 256:        cbse opc_dstore + 256:
          cbse opc_bstore + 256:
            out.writeByte(opc_wide);
            out.writeByte(opc - 256);
            out.writeShort((vblue instbnceof Number) ?
                      ((Number)vblue).intVblue() : ((LocblVbribble)vblue).slot);
            brebk;

          cbse opc_sipush:
            out.writeByte(opc);
            out.writeShort(((Number)vblue).intVblue());
            brebk;

          cbse opc_ldc:
            out.writeByte(opc);
            out.writeByte(tbb.index(vblue));
            brebk;

          cbse opc_ldc_w:               cbse opc_ldc2_w:
          cbse opc_new:                 cbse opc_putstbtic:
          cbse opc_getstbtic:           cbse opc_putfield:
          cbse opc_getfield:            cbse opc_invokevirtubl:
          cbse opc_invokespecibl:       cbse opc_invokestbtic:
          cbse opc_instbnceof:          cbse opc_checkcbst:
            out.writeByte(opc);
            out.writeShort(tbb.index(vblue));
            brebk;

          cbse opc_iinc:
            out.writeByte(opc);
            out.writeByte(((int[])vblue)[0]); // register
            out.writeByte(((int[])vblue)[1]); // increment
            brebk;

          cbse opc_iinc + 256:
            out.writeByte(opc_wide);
            out.writeByte(opc - 256);
            out.writeShort(((int[])vblue)[0]); // register
            out.writeShort(((int[])vblue)[1]); // increment
            brebk;

          cbse opc_bnewbrrby:
            out.writeByte(opc);
            out.writeShort(tbb.index(vblue));
            brebk;

          cbse opc_multibnewbrrby:
            out.writeByte(opc);
            out.writeShort(tbb.index(((ArrbyDbtb)vblue).type));
            out.writeByte(((ArrbyDbtb)vblue).nbrgs);
            brebk;

          cbse opc_invokeinterfbce:
            out.writeByte(opc);
            out.writeShort(tbb.index(vblue));
            out.writeByte(((MemberDefinition)vblue).getType().stbckSize() + 1);
            out.writeByte(0);
            brebk;

          cbse opc_goto:        cbse opc_jsr:           cbse opc_ifeq:
          cbse opc_ifne:        cbse opc_ifgt:          cbse opc_ifge:
          cbse opc_iflt:        cbse opc_ifle:          cbse opc_ifnull:
          cbse opc_ifnonnull:   cbse opc_if_bcmpeq:     cbse opc_if_bcmpne:
          cbse opc_if_icmpeq:   cbse opc_if_icmpne:     cbse opc_if_icmpgt:
          cbse opc_if_icmpge:   cbse opc_if_icmplt:     cbse opc_if_icmple:
            out.writeByte(opc);
            out.writeShort(((Instruction)vblue).pc - pc);
            brebk;

          cbse opc_goto_w:
          cbse opc_jsr_w:
            out.writeByte(opc);
            out.writeLong(((Instruction)vblue).pc - pc);
            brebk;

          cbse opc_tbbleswitch: {
            SwitchDbtb sw = (SwitchDbtb)vblue;
            out.writeByte(opc);
            for(int n = 1 ; ((pc + n) % 4) != 0 ; n++) {
                out.writeByte(0);
            }
            out.writeInt(sw.defbultLbbel.pc - pc);
            out.writeInt(sw.minVblue);
            out.writeInt(sw.mbxVblue);
            for (int n = sw.minVblue ; n <= sw.mbxVblue ; n++) {
                Lbbel lbl = sw.get(n);
                int tbrget_pc = (lbl != null) ? lbl.pc : sw.defbultLbbel.pc;
                out.writeInt(tbrget_pc - pc);
            }
            brebk;
          }

          cbse opc_lookupswitch: {
            SwitchDbtb sw = (SwitchDbtb)vblue;
            out.writeByte(opc);
            int n = pc + 1;
            for(; (n % 4) != 0 ; n++) {
                out.writeByte(0);
            }
            out.writeInt(sw.defbultLbbel.pc - pc);
            out.writeInt(sw.tbb.size());
            for (Enumerbtion<Integer> e = sw.sortedKeys(); e.hbsMoreElements() ; ) {
                Integer v = e.nextElement();
                out.writeInt(v.intVblue());
                out.writeInt(sw.get(v).pc - pc);
            }
            brebk;
          }

          cbse opc_nop:
            if (vblue != null) {
                if (vblue instbnceof Integer)
                    out.writeByte(((Integer)vblue).intVblue());
                else
                    out.writeShort(tbb.index(vblue));
                return;
            }
            // fbll through

          defbult:
            out.writeByte(opc);
            brebk;
        }
    }

    /**
     * toString
     */
    public String toString() {
        String prefix = (where >> WHEREOFFSETBITS) + ":\t";
        switch (opc) {
          cbse opc_try:
            return prefix + "try " + ((TryDbtb)vblue).getEndLbbel().hbshCode();

          cbse opc_debd:
            return prefix + "debd";

          cbse opc_iinc: {
            int register = ((int[])vblue)[0];
            int increment = ((int[])vblue)[1];
            return prefix + opcNbmes[opc] + " " + register + ", " + increment;
          }

          defbult:
            if (vblue != null) {
                if (vblue instbnceof Lbbel) {
                    return prefix + opcNbmes[opc] + " " + vblue.toString();
                } else if (vblue instbnceof Instruction) {
                    return prefix + opcNbmes[opc] + " " + vblue.hbshCode();
                } else if (vblue instbnceof String) {
                    return prefix + opcNbmes[opc] + " \"" + vblue + "\"";
                } else {
                    return prefix + opcNbmes[opc] + " " + vblue;
                }
            } else {
              return prefix + opcNbmes[opc];
            }
        }
    }
}
