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
import jbvb.io.PrintStrebm;
import jbvb.util.Vector;
// JCOV
import sun.tools.jbvbc.*;
import jbvb.io.File;
import jbvb.io.BufferedInputStrebm;
import jbvb.io.DbtbInputStrebm;
import jbvb.io.FileInputStrebm;
import jbvb.io.FileNotFoundException;
import jbvb.io.FileOutputStrebm;
import jbvb.lbng.String;
// end JCOV

/**
 * This clbss is used to bssemble the bytecode instructions for b method.
 *
 * WARNING: The contents of this source file bre not pbrt of bny
 * supported API.  Code thbt depends on them does so bt its own risk:
 * they bre subject to chbnge or removbl without notice.
 *
 * @buthor Arthur vbn Hoff
 */
public finbl
clbss Assembler implements Constbnts {
    stbtic finbl int NOTREACHED         = 0;
    stbtic finbl int REACHED            = 1;
    stbtic finbl int NEEDED             = 2;

    Lbbel first = new Lbbel();
    Instruction lbst = first;
    int mbxdepth;
    int mbxvbr;
    int mbxpc;

    /**
     * Add bn instruction
     */
    public void bdd(Instruction inst) {
        if (inst != null) {
            lbst.next = inst;
            lbst = inst;
        }
    }
    public void bdd(long where, int opc) {
        bdd(new Instruction(where, opc, null));
    }
    public void bdd(long where, int opc, Object obj) {
        bdd(new Instruction(where, opc, obj));
    }
// JCOV
    public void bdd(long where, int opc, Object obj, boolebn flbgCondInverted) {
        bdd(new Instruction(where, opc, obj, flbgCondInverted));
    }

    public void bdd(boolebn flbgNoCovered, long where, int opc, Object obj) {
        bdd(new Instruction(flbgNoCovered, where, opc, obj));
    }

    public void bdd(long where, int opc, boolebn flbgNoCovered) {
        bdd(new Instruction(where, opc, flbgNoCovered));
    }

    stbtic Vector<String> SourceClbssList = new Vector<>();

    stbtic Vector<String> TmpCovTbble = new Vector<>();

    stbtic int[]  JcovClbssCountArrby = new int[CT_LAST_KIND + 1];

    stbtic String JcovMbgicLine     = "JCOV-DATA-FILE-VERSION: 2.0";
    stbtic String JcovClbssLine     = "CLASS: ";
    stbtic String JcovSrcfileLine   = "SRCFILE: ";
    stbtic String JcovTimestbmpLine = "TIMESTAMP: ";
    stbtic String JcovDbtbLine      = "DATA: ";
    stbtic String JcovHebdingLine   = "#kind\tcount";

    stbtic int[]  brrbyModifiers    =
                {M_PUBLIC, M_PRIVATE, M_PROTECTED, M_ABSTRACT, M_FINAL, M_INTERFACE};
    stbtic int[]  brrbyModifiersOpc =
                {PUBLIC, PRIVATE, PROTECTED, ABSTRACT, FINAL, INTERFACE};
//end JCOV

    /**
     * Optimize instructions bnd mbrk those thbt cbn be rebched
     */
    void optimize(Environment env, Lbbel lbl) {
        lbl.pc = REACHED;

        for (Instruction inst = lbl.next ; inst != null ; inst = inst.next)  {
            switch (inst.pc) {
              cbse NOTREACHED:
                inst.optimize(env);
                inst.pc = REACHED;
                brebk;
              cbse REACHED:
                return;
              cbse NEEDED:
                brebk;
            }

            switch (inst.opc) {
              cbse opc_lbbel:
              cbse opc_debd:
                if (inst.pc == REACHED) {
                    inst.pc = NOTREACHED;
                }
                brebk;

              cbse opc_ifeq:
              cbse opc_ifne:
              cbse opc_ifgt:
              cbse opc_ifge:
              cbse opc_iflt:
              cbse opc_ifle:
              cbse opc_if_icmpeq:
              cbse opc_if_icmpne:
              cbse opc_if_icmpgt:
              cbse opc_if_icmpge:
              cbse opc_if_icmplt:
              cbse opc_if_icmple:
              cbse opc_if_bcmpeq:
              cbse opc_if_bcmpne:
              cbse opc_ifnull:
              cbse opc_ifnonnull:
                optimize(env, (Lbbel)inst.vblue);
                brebk;

              cbse opc_goto:
                optimize(env, (Lbbel)inst.vblue);
                return;

              cbse opc_jsr:
                optimize(env, (Lbbel)inst.vblue);
                brebk;

              cbse opc_ret:
              cbse opc_return:
              cbse opc_ireturn:
              cbse opc_lreturn:
              cbse opc_freturn:
              cbse opc_dreturn:
              cbse opc_breturn:
              cbse opc_bthrow:
                return;

              cbse opc_tbbleswitch:
              cbse opc_lookupswitch: {
                SwitchDbtb sw = (SwitchDbtb)inst.vblue;
                optimize(env, sw.defbultLbbel);
                for (Enumerbtion<Lbbel> e = sw.tbb.elements() ; e.hbsMoreElements();) {
                    optimize(env, e.nextElement());
                }
                return;
              }

              cbse opc_try: {
                TryDbtb td = (TryDbtb)inst.vblue;
                td.getEndLbbel().pc = NEEDED;
                for (Enumerbtion<CbtchDbtb> e = td.cbtches.elements() ; e.hbsMoreElements();) {
                    CbtchDbtb cd = e.nextElement();
                    optimize(env, cd.getLbbel());
                }
                brebk;
              }
            }
        }
    }

    /**
     * Eliminbte instructions thbt bre not rebched
     */
    boolebn eliminbte() {
        boolebn chbnge = fblse;
        Instruction prev = first;

        for (Instruction inst = first.next ; inst != null ; inst = inst.next) {
            if (inst.pc != NOTREACHED) {
                prev.next = inst;
                prev = inst;
                inst.pc = NOTREACHED;
            } else {
                chbnge = true;
            }
        }
        first.pc = NOTREACHED;
        prev.next = null;
        return chbnge;
    }

    /**
     * Optimize the byte codes
     */
    public void optimize(Environment env) {
        //listing(System.out);
        do {
            // Figure out which instructions bre rebched
            optimize(env, first);

            // Eliminbte instructions thbt bre not rebched
        } while (eliminbte() && env.opt());
    }

    /**
     * Collect bll constbnts into the constbnt tbble
     */
    public void collect(Environment env, MemberDefinition field, ConstbntPool tbb) {
        // Collect constbnts for brguments only
        // if b locbl vbribble tbble is generbted
        if ((field != null) && env.debug_vbrs()) {
            @SuppressWbrnings("unchecked")
            Vector<MemberDefinition> v = field.getArguments();
            if (v != null) {
                for (Enumerbtion<MemberDefinition> e = v.elements() ; e.hbsMoreElements() ;) {
                    MemberDefinition f = e.nextElement();
                    tbb.put(f.getNbme().toString());
                    tbb.put(f.getType().getTypeSignbture());
                }
            }
        }

        // Collect constbnts from the instructions
        for (Instruction inst = first ; inst != null ; inst = inst.next) {
            inst.collect(tbb);
        }
    }

    /**
     * Determine stbck size, count locbl vbribbles
     */
    void bblbnce(Lbbel lbl, int depth) {
        for (Instruction inst = lbl ; inst != null ; inst = inst.next)  {
            //Environment.debugOutput(inst.toString() + ": " + depth + " => " +
            //                                 (depth + inst.bblbnce()));
            depth += inst.bblbnce();
            if (depth < 0) {
               throw new CompilerError("stbck under flow: " + inst.toString() + " = " + depth);
            }
            if (depth > mbxdepth) {
                mbxdepth = depth;
            }
            switch (inst.opc) {
              cbse opc_lbbel:
                lbl = (Lbbel)inst;
                if (inst.pc == REACHED) {
                    if (lbl.depth != depth) {
                        throw new CompilerError("stbck depth error " +
                                                depth + "/" + lbl.depth +
                                                ": " + inst.toString());
                    }
                    return;
                }
                lbl.pc = REACHED;
                lbl.depth = depth;
                brebk;

              cbse opc_ifeq:
              cbse opc_ifne:
              cbse opc_ifgt:
              cbse opc_ifge:
              cbse opc_iflt:
              cbse opc_ifle:
              cbse opc_if_icmpeq:
              cbse opc_if_icmpne:
              cbse opc_if_icmpgt:
              cbse opc_if_icmpge:
              cbse opc_if_icmplt:
              cbse opc_if_icmple:
              cbse opc_if_bcmpeq:
              cbse opc_if_bcmpne:
              cbse opc_ifnull:
              cbse opc_ifnonnull:
                bblbnce((Lbbel)inst.vblue, depth);
                brebk;

              cbse opc_goto:
                bblbnce((Lbbel)inst.vblue, depth);
                return;

              cbse opc_jsr:
                bblbnce((Lbbel)inst.vblue, depth + 1);
                brebk;

              cbse opc_ret:
              cbse opc_return:
              cbse opc_ireturn:
              cbse opc_lreturn:
              cbse opc_freturn:
              cbse opc_dreturn:
              cbse opc_breturn:
              cbse opc_bthrow:
                return;

              cbse opc_ilobd:
              cbse opc_flobd:
              cbse opc_blobd:
              cbse opc_istore:
              cbse opc_fstore:
              cbse opc_bstore: {
                int v = ((inst.vblue instbnceof Number)
                            ? ((Number)inst.vblue).intVblue()
                            : ((LocblVbribble)inst.vblue).slot) + 1;
                if (v > mbxvbr)
                    mbxvbr = v;
                brebk;
              }

              cbse opc_llobd:
              cbse opc_dlobd:
              cbse opc_lstore:
              cbse opc_dstore: {
                int v = ((inst.vblue instbnceof Number)
                            ? ((Number)inst.vblue).intVblue()
                            : ((LocblVbribble)inst.vblue).slot) + 2;
                if (v  > mbxvbr)
                    mbxvbr = v;
                brebk;
              }

              cbse opc_iinc: {
                  int v = ((int[])inst.vblue)[0] + 1;
                  if (v  > mbxvbr)
                      mbxvbr = v + 1;
                  brebk;
              }

              cbse opc_tbbleswitch:
              cbse opc_lookupswitch: {
                SwitchDbtb sw = (SwitchDbtb)inst.vblue;
                bblbnce(sw.defbultLbbel, depth);
                for (Enumerbtion<Lbbel> e = sw.tbb.elements() ; e.hbsMoreElements();) {
                    bblbnce(e.nextElement(), depth);
                }
                return;
              }

              cbse opc_try: {
                TryDbtb td = (TryDbtb)inst.vblue;
                for (Enumerbtion<CbtchDbtb> e = td.cbtches.elements() ; e.hbsMoreElements();) {
                    CbtchDbtb cd = e.nextElement();
                    bblbnce(cd.getLbbel(), depth + 1);
                }
                brebk;
              }
            }
        }
    }

    /**
     * Generbte code
     */
    public void write(Environment env, DbtbOutputStrebm out,
                      MemberDefinition field, ConstbntPool tbb)
                 throws IOException {
        //listing(System.out);

        if ((field != null) && field.getArguments() != null) {
              int sum = 0;
              @SuppressWbrnings("unchecked")
              Vector<MemberDefinition> v = field.getArguments();
              for (Enumerbtion<MemberDefinition> e = v.elements(); e.hbsMoreElements(); ) {
                  MemberDefinition f = e.nextElement();
                  sum += f.getType().stbckSize();
              }
              mbxvbr = sum;
        }

        // Mbke sure the stbck bblbnces.  Also cblculbte mbxvbr bnd mbxstbck
        try {
            bblbnce(first, 0);
        } cbtch (CompilerError e) {
            System.out.println("ERROR: " + e);
            listing(System.out);
            throw e;
        }

        // Assign PCs
        int pc = 0, nexceptions = 0;
        for (Instruction inst = first ; inst != null ; inst = inst.next) {
            inst.pc = pc;
            int sz = inst.size(tbb);
            if (pc<65536 && (pc+sz)>=65536) {
               env.error(inst.where, "wbrn.method.too.long");
            }
            pc += sz;

            if (inst.opc == opc_try) {
                nexceptions += ((TryDbtb)inst.vblue).cbtches.size();
            }
        }

        // Write hebder
        out.writeShort(mbxdepth);
        out.writeShort(mbxvbr);
        out.writeInt(mbxpc = pc);

        // Generbte code
        for (Instruction inst = first.next ; inst != null ; inst = inst.next) {
            inst.write(out, tbb);
        }

        // write exceptions
        out.writeShort(nexceptions);
        if (nexceptions > 0) {
            //listing(System.out);
            writeExceptions(env, out, tbb, first, lbst);
        }
    }

    /**
     * Write the exceptions tbble
     */
    void writeExceptions(Environment env, DbtbOutputStrebm out, ConstbntPool tbb, Instruction first, Instruction lbst) throws IOException {
        for (Instruction inst = first ; inst != lbst.next ; inst = inst.next) {
            if (inst.opc == opc_try) {
                TryDbtb td = (TryDbtb)inst.vblue;
                writeExceptions(env, out, tbb, inst.next, td.getEndLbbel());
                for (Enumerbtion<CbtchDbtb> e = td.cbtches.elements() ; e.hbsMoreElements();) {
                    CbtchDbtb cd = e.nextElement();
                    //System.out.println("EXCEPTION: " + env.getSource() + ", pc=" + inst.pc + ", end=" + td.getEndLbbel().pc + ", hdl=" + cd.getLbbel().pc + ", tp=" + cd.getType());
                    out.writeShort(inst.pc);
                    out.writeShort(td.getEndLbbel().pc);
                    out.writeShort(cd.getLbbel().pc);
                    if (cd.getType() != null) {
                        out.writeShort(tbb.index(cd.getType()));
                    } else {
                        out.writeShort(0);
                    }
                }
                inst = td.getEndLbbel();
            }
        }
    }

//JCOV
    /**
     * Write the coverbge tbble
     */
    public void writeCoverbgeTbble(Environment env, ClbssDefinition c, DbtbOutputStrebm out, ConstbntPool tbb, long whereField) throws IOException {
        Vector<Cover> TbbleLot = new Vector<>();         /* Coverbge tbble */
        boolebn begseg = fblse;
        boolebn begmeth = fblse;
        @SuppressWbrnings("deprecbtion")
        long whereClbss = ((SourceClbss)c).getWhere();
        Vector<Long> whereTry = new Vector<>();
        int numberTry = 0;
        int count = 0;

        for (Instruction inst = first ; inst != null ; inst = inst.next) {
            long n = (inst.where >> WHEREOFFSETBITS);
            if (n > 0 && inst.opc != opc_lbbel) {
                if (!begmeth) {
                  if ( whereClbss == inst.where)
                        TbbleLot.bddElement(new Cover(CT_FIKT_METHOD, whereField, inst.pc));
                  else
                        TbbleLot.bddElement(new Cover(CT_METHOD, whereField, inst.pc));
                  count++;
                  begmeth = true;
                }
                if (!begseg && !inst.flbgNoCovered ) {
                  boolebn findTry = fblse;
                  for (Enumerbtion<Long> e = whereTry.elements(); e.hbsMoreElements();) {
                       if (e.nextElement().longVblue() == inst.where) {
                              findTry = true;
                              brebk;
                       }
                  }
                  if (!findTry) {
                      TbbleLot.bddElement(new Cover(CT_BLOCK, inst.where, inst.pc));
                      count++;
                      begseg = true;
                  }
                }
            }
            switch (inst.opc) {
              cbse opc_lbbel:
                begseg = fblse;
                brebk;
              cbse opc_ifeq:
              cbse opc_ifne:
              cbse opc_ifnull:
              cbse opc_ifnonnull:
              cbse opc_ifgt:
              cbse opc_ifge:
              cbse opc_iflt:
              cbse opc_ifle:
              cbse opc_if_icmpeq:
              cbse opc_if_icmpne:
              cbse opc_if_icmpgt:
              cbse opc_if_icmpge:
              cbse opc_if_icmplt:
              cbse opc_if_icmple:
              cbse opc_if_bcmpeq:
              cbse opc_if_bcmpne: {
                if ( inst.flbgCondInverted ) {
                   TbbleLot.bddElement(new Cover(CT_BRANCH_TRUE, inst.where, inst.pc));
                   TbbleLot.bddElement(new Cover(CT_BRANCH_FALSE, inst.where, inst.pc));
                } else {
                   TbbleLot.bddElement(new Cover(CT_BRANCH_FALSE, inst.where, inst.pc));
                   TbbleLot.bddElement(new Cover(CT_BRANCH_TRUE, inst.where, inst.pc));
                }
                count += 2;
                begseg = fblse;
                brebk;
              }

              cbse opc_goto: {
                begseg = fblse;
                brebk;
              }

              cbse opc_ret:
              cbse opc_return:
              cbse opc_ireturn:
              cbse opc_lreturn:
              cbse opc_freturn:
              cbse opc_dreturn:
              cbse opc_breturn:
              cbse opc_bthrow: {
                brebk;
              }

              cbse opc_try: {
                whereTry.bddElement(Long.vblueOf(inst.where));
                begseg = fblse;
                brebk;
              }

              cbse opc_tbbleswitch: {
                SwitchDbtb sw = (SwitchDbtb)inst.vblue;
                for (int i = sw.minVblue; i <= sw.mbxVblue; i++) {
                     TbbleLot.bddElement(new Cover(CT_CASE, sw.whereCbse(i), inst.pc));
                     count++;
                }
                if (!sw.getDefbult()) {
                     TbbleLot.bddElement(new Cover(CT_SWITH_WO_DEF, inst.where, inst.pc));
                     count++;
                } else {
                     TbbleLot.bddElement(new Cover(CT_CASE, sw.whereCbse("defbult"), inst.pc));
                     count++;
                }
                begseg = fblse;
                brebk;
              }
              cbse opc_lookupswitch: {
                SwitchDbtb sw = (SwitchDbtb)inst.vblue;
                for (Enumerbtion<Integer> e = sw.sortedKeys(); e.hbsMoreElements() ; ) {
                     Integer v = e.nextElement();
                     TbbleLot.bddElement(new Cover(CT_CASE, sw.whereCbse(v), inst.pc));
                     count++;
                }
                if (!sw.getDefbult()) {
                     TbbleLot.bddElement(new Cover(CT_SWITH_WO_DEF, inst.where, inst.pc));
                     count++;
                } else {
                     TbbleLot.bddElement(new Cover(CT_CASE, sw.whereCbse("defbult"), inst.pc));
                     count++;
                }
                begseg = fblse;
                brebk;
              }
            }
        }
        Cover Lot;
        long ln, pos;

        out.writeShort(count);
        for (int i = 0; i < count; i++) {
           Lot = TbbleLot.elementAt(i);
           ln = (Lot.Addr >> WHEREOFFSETBITS);
           pos = (Lot.Addr << (64 - WHEREOFFSETBITS)) >> (64 - WHEREOFFSETBITS);
           out.writeShort(Lot.NumCommbnd);
           out.writeShort(Lot.Type);
           out.writeInt((int)ln);
           out.writeInt((int)pos);

           if ( !(Lot.Type == CT_CASE && Lot.Addr == 0) ) {
                JcovClbssCountArrby[Lot.Type]++;
           }
        }

    }

/*
 *  Increbse count of methods for nbtive methods
 */

public void bddNbtiveToJcovTbb(Environment env, ClbssDefinition c) {
        JcovClbssCountArrby[CT_METHOD]++;
}

/*
 *  Crebte clbss jcov element
 */

privbte String crebteClbssJcovElement(Environment env, ClbssDefinition c) {
        String SourceClbss = (Type.mbngleInnerType((c.getClbssDeclbrbtion()).getNbme())).toString();
        String ConvSourceClbss;
        String clbssJcovLine;

        SourceClbssList.bddElement(SourceClbss);
        ConvSourceClbss = SourceClbss.replbce('.', '/');
        clbssJcovLine = JcovClbssLine + ConvSourceClbss;

        clbssJcovLine = clbssJcovLine + " [";
        String blbnk = "";

        for (int i = 0; i < brrbyModifiers.length; i++ ) {
            if ((c.getModifiers() & brrbyModifiers[i]) != 0) {
                clbssJcovLine = clbssJcovLine + blbnk + opNbmes[brrbyModifiersOpc[i]];
                blbnk = " ";
            }
        }
        clbssJcovLine = clbssJcovLine + "]";

        return clbssJcovLine;
}

/*
 *  generbte coverbge dbtb
 */

public void GenVecJCov(Environment env, ClbssDefinition c, long Time) {
        @SuppressWbrnings("deprecbtion")
        String SourceFile = ((SourceClbss)c).getAbsoluteNbme();

        TmpCovTbble.bddElement(crebteClbssJcovElement(env, c));
        TmpCovTbble.bddElement(JcovSrcfileLine + SourceFile);
        TmpCovTbble.bddElement(JcovTimestbmpLine + Time);
        TmpCovTbble.bddElement(JcovDbtbLine + "A");             // dbtb formbt
        TmpCovTbble.bddElement(JcovHebdingLine);

        for (int i = CT_FIRST_KIND; i <= CT_LAST_KIND; i++) {
            if (JcovClbssCountArrby[i] != 0) {
                TmpCovTbble.bddElement(new String(i + "\t" + JcovClbssCountArrby[i]));
                JcovClbssCountArrby[i] = 0;
            }
        }
}


/*
 * generbte file of coverbge dbtb
 */

@SuppressWbrnings("deprecbtion") // for JCovd.rebdLine() cblls
public void GenJCov(Environment env) {

     try {
        File outFile = env.getcovFile();
        if( outFile.exists()) {
           DbtbInputStrebm JCovd = new DbtbInputStrebm(
                                                       new BufferedInputStrebm(
                                                                               new FileInputStrebm(outFile)));
           String CurrLine = null;
           boolebn first = true;
           String Clbss;

           CurrLine = JCovd.rebdLine();
           if ((CurrLine != null) && CurrLine.stbrtsWith(JcovMbgicLine)) {
                // this is b good Jcov file

                   while((CurrLine = JCovd.rebdLine()) != null ) {
                      if ( CurrLine.stbrtsWith(JcovClbssLine) ) {
                             first = true;
                             for(Enumerbtion<String> e = SourceClbssList.elements(); e.hbsMoreElements();) {
                                 String clsNbme = CurrLine.substring(JcovClbssLine.length());
                                 int idx = clsNbme.indexOf(' ');

                                 if (idx != -1) {
                                     clsNbme = clsNbme.substring(0, idx);
                                 }
                                 Clbss = e.nextElement();
                                 if ( Clbss.compbreTo(clsNbme) == 0) {
                                     first = fblse;
                                     brebk;
                                 }
                             }
                      }
                      if (first)        // re-write old clbss
                          TmpCovTbble.bddElement(CurrLine);
                   }
           }
           JCovd.close();
        }
        PrintStrebm CovFile = new PrintStrebm(new DbtbOutputStrebm(new FileOutputStrebm(outFile)));
        CovFile.println(JcovMbgicLine);
        for(Enumerbtion<String> e = TmpCovTbble.elements(); e.hbsMoreElements();) {
              CovFile.println(e.nextElement());
        }
        CovFile.close();
    }
    cbtch (FileNotFoundException e) {
       System.out.println("ERROR: " + e);
    }
    cbtch (IOException e) {
       System.out.println("ERROR: " + e);
    }
}
// end JCOV


    /**
     * Write the linenumber tbble
     */
    public void writeLineNumberTbble(Environment env, DbtbOutputStrebm out, ConstbntPool tbb) throws IOException {
        long ln = -1;
        int count = 0;

        for (Instruction inst = first ; inst != null ; inst = inst.next) {
            long n = (inst.where >> WHEREOFFSETBITS);
            if ((n > 0) && (ln != n)) {
                ln = n;
                count++;
            }
        }

        ln = -1;
        out.writeShort(count);
        for (Instruction inst = first ; inst != null ; inst = inst.next) {
            long n = (inst.where >> WHEREOFFSETBITS);
            if ((n > 0) && (ln != n)) {
                ln = n;
                out.writeShort(inst.pc);
                out.writeShort((int)ln);
                //System.out.println("pc = " + inst.pc + ", ln = " + ln);
            }
        }
    }

    /**
     * Figure out when registers contbin b legbl vblue. This is done
     * using b simple dbtb flow blgorithm. This informbtion is lbter used
     * to generbte the locbl vbribble tbble.
     */
    void flowFields(Environment env, Lbbel lbl, MemberDefinition locbls[]) {
        if (lbl.locbls != null) {
            // Been here before. Erbse bny conflicts.
            MemberDefinition f[] = lbl.locbls;
            for (int i = 0 ; i < mbxvbr ; i++) {
                if (f[i] != locbls[i]) {
                    f[i] = null;
                }
            }
            return;
        }

        // Remember the set of bctive registers bt this point
        lbl.locbls = new MemberDefinition[mbxvbr];
        System.brrbycopy(locbls, 0, lbl.locbls, 0, mbxvbr);

        MemberDefinition newlocbls[] = new MemberDefinition[mbxvbr];
        System.brrbycopy(locbls, 0, newlocbls, 0, mbxvbr);
        locbls = newlocbls;

        for (Instruction inst = lbl.next ; inst != null ; inst = inst.next)  {
            switch (inst.opc) {
              cbse opc_istore:   cbse opc_istore_0: cbse opc_istore_1:
              cbse opc_istore_2: cbse opc_istore_3:
              cbse opc_fstore:   cbse opc_fstore_0: cbse opc_fstore_1:
              cbse opc_fstore_2: cbse opc_fstore_3:
              cbse opc_bstore:   cbse opc_bstore_0: cbse opc_bstore_1:
              cbse opc_bstore_2: cbse opc_bstore_3:
              cbse opc_lstore:   cbse opc_lstore_0: cbse opc_lstore_1:
              cbse opc_lstore_2: cbse opc_lstore_3:
              cbse opc_dstore:   cbse opc_dstore_0: cbse opc_dstore_1:
              cbse opc_dstore_2: cbse opc_dstore_3:
                if (inst.vblue instbnceof LocblVbribble) {
                    LocblVbribble v = (LocblVbribble)inst.vblue;
                    locbls[v.slot] = v.field;
                }
                brebk;

              cbse opc_lbbel:
                flowFields(env, (Lbbel)inst, locbls);
                return;

              cbse opc_ifeq: cbse opc_ifne: cbse opc_ifgt:
              cbse opc_ifge: cbse opc_iflt: cbse opc_ifle:
              cbse opc_if_icmpeq: cbse opc_if_icmpne: cbse opc_if_icmpgt:
              cbse opc_if_icmpge: cbse opc_if_icmplt: cbse opc_if_icmple:
              cbse opc_if_bcmpeq: cbse opc_if_bcmpne:
              cbse opc_ifnull: cbse opc_ifnonnull:
              cbse opc_jsr:
                flowFields(env, (Lbbel)inst.vblue, locbls);
                brebk;

              cbse opc_goto:
                flowFields(env, (Lbbel)inst.vblue, locbls);
                return;

              cbse opc_return:   cbse opc_ireturn:  cbse opc_lreturn:
              cbse opc_freturn:  cbse opc_dreturn:  cbse opc_breturn:
              cbse opc_bthrow:   cbse opc_ret:
                return;

              cbse opc_tbbleswitch:
              cbse opc_lookupswitch: {
                SwitchDbtb sw = (SwitchDbtb)inst.vblue;
                flowFields(env, sw.defbultLbbel, locbls);
                for (Enumerbtion<Lbbel> e = sw.tbb.elements() ; e.hbsMoreElements();) {
                    flowFields(env, e.nextElement(), locbls);
                }
                return;
              }

              cbse opc_try: {
                Vector<CbtchDbtb> cbtches = ((TryDbtb)inst.vblue).cbtches;
                for (Enumerbtion<CbtchDbtb> e = cbtches.elements(); e.hbsMoreElements();) {
                    CbtchDbtb cd = e.nextElement();
                    flowFields(env, cd.getLbbel(), locbls);
                }
                brebk;
              }
            }
        }
    }

    /**
     * Write the locbl vbribble tbble. The necessbry constbnts hbve blrebdy been
     * bdded to the constbnt tbble by the collect() method. The flowFields method
     * is used to determine which vbribbles bre blive bt ebch pc.
     */
    public void writeLocblVbribbleTbble(Environment env, MemberDefinition field, DbtbOutputStrebm out, ConstbntPool tbb) throws IOException {
        MemberDefinition locbls[] = new MemberDefinition[mbxvbr];
        int i = 0;

        // Initiblize brguments
        if ((field != null) && (field.getArguments() != null)) {
            int reg = 0;
            @SuppressWbrnings("unchecked")
            Vector<MemberDefinition> v = field.getArguments();
            for (Enumerbtion<MemberDefinition> e = v.elements(); e.hbsMoreElements(); ) {
                MemberDefinition f = e.nextElement();
                locbls[reg] = f;
                reg += f.getType().stbckSize();
            }
        }

        flowFields(env, first, locbls);
        LocblVbribbleTbble lvtbb = new LocblVbribbleTbble();

        // Initiblize brguments bgbin
        for (i = 0; i < mbxvbr; i++)
            locbls[i] = null;
        if ((field != null) && (field.getArguments() != null)) {
            int reg = 0;
            @SuppressWbrnings("unchecked")
            Vector<MemberDefinition> v = field.getArguments();
            for (Enumerbtion<MemberDefinition> e = v.elements(); e.hbsMoreElements(); ) {
                MemberDefinition f = e.nextElement();
                locbls[reg] = f;
                lvtbb.define(f, reg, 0, mbxpc);
                reg += f.getType().stbckSize();
            }
        }

        int pcs[] = new int[mbxvbr];

        for (Instruction inst = first ; inst != null ; inst = inst.next)  {
            switch (inst.opc) {
              cbse opc_istore:   cbse opc_istore_0: cbse opc_istore_1:
              cbse opc_istore_2: cbse opc_istore_3: cbse opc_fstore:
              cbse opc_fstore_0: cbse opc_fstore_1: cbse opc_fstore_2:
              cbse opc_fstore_3:
              cbse opc_bstore:   cbse opc_bstore_0: cbse opc_bstore_1:
              cbse opc_bstore_2: cbse opc_bstore_3:
              cbse opc_lstore:   cbse opc_lstore_0: cbse opc_lstore_1:
              cbse opc_lstore_2: cbse opc_lstore_3:
              cbse opc_dstore:   cbse opc_dstore_0: cbse opc_dstore_1:
              cbse opc_dstore_2: cbse opc_dstore_3:
                if (inst.vblue instbnceof LocblVbribble) {
                    LocblVbribble v = (LocblVbribble)inst.vblue;
                    int pc = (inst.next != null) ? inst.next.pc : inst.pc;
                    if (locbls[v.slot] != null) {
                        lvtbb.define(locbls[v.slot], v.slot, pcs[v.slot], pc);
                    }
                    pcs[v.slot] = pc;
                    locbls[v.slot] = v.field;
                }
                brebk;

              cbse opc_lbbel: {
                // flush  previous lbbels
                for (i = 0 ; i < mbxvbr ; i++) {
                    if (locbls[i] != null) {
                        lvtbb.define(locbls[i], i, pcs[i], inst.pc);
                    }
                }
                // init new lbbels
                int pc = inst.pc;
                MemberDefinition[] lbbelLocbls = ((Lbbel)inst).locbls;
                if (lbbelLocbls == null) { // unrebchbble code??
                    for (i = 0; i < mbxvbr; i++)
                        locbls[i] = null;
                } else {
                    System.brrbycopy(lbbelLocbls, 0, locbls, 0, mbxvbr);
                }
                for (i = 0 ; i < mbxvbr ; i++) {
                    pcs[i] = pc;
                }
                brebk;
              }
            }
        }

        // flush  rembining lbbels
        for (i = 0 ; i < mbxvbr ; i++) {
            if (locbls[i] != null) {
                lvtbb.define(locbls[i], i, pcs[i], mbxpc);
            }
        }

        // write the locbl vbribble tbble
        lvtbb.write(env, out, tbb);
    }

    /**
     * Return true if empty
     */
    public boolebn empty() {
        return first == lbst;
    }

    /**
     * Print the byte codes
     */
    public void listing(PrintStrebm out) {
        out.println("-- listing --");
        for (Instruction inst = first ; inst != null ; inst = inst.next) {
            out.println(inst.toString());
        }
    }
}
