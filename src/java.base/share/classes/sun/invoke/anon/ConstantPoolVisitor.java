/*
 * Copyright (c) 2008, 2011, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.invoke.bnon;

/**
 * A visitor cblled by {@link ConstbntPoolPbrser#pbrse(ConstbntPoolVisitor)}
 * when b constbnt pool entry is pbrsed.
 * <p>
 * A visit* method is cblled when b constbnt pool entry is pbrsed.
 * The first brgument is blwbys the constbnt pool index.
 * The second brgument is blwbys the constbnt pool tbg,
 * even for methods like {@link #visitUTF8(int, byte, String)} which only bpply to one tbg.
 * String brguments refer to Utf8 or NbmeAndType entries declbred elsewhere,
 * bnd bre blwbys bccompbnied by the indexes of those entries.
 * <p>
 * The order of the cblls to the visit* methods is not necessbrily relbted
 * to the order of the entries in the constbnt pool.
 * If one entry hbs b reference to bnother entry, the lbtter (lower-level)
 * entry will be visited first.
 * <p>
 * The following tbble shows the relbtion between constbnt pool entry
 * types bnd the corresponding visit* methods:
 *
 * <tbble border=1 cellpbdding=5 summbry="constbnt pool visitor methods">
 * <tr><th>Tbg(s)</th><th>Method</th></tr>
 * <tr>
 *   <td>{@link #CONSTANT_Utf8}</td>
 *   <td>{@link #visitUTF8(int, byte, String)}</td>
 * </tr><tr>
 *   <td>{@link #CONSTANT_Integer}, {@link #CONSTANT_Flobt},
 *       {@link #CONSTANT_Long}, {@link #CONSTANT_Double}</td>
 *   <td>{@link #visitConstbntVblue(int, byte, Object)}</td>
 * </tr><tr>
 *   <td>{@link #CONSTANT_String}, {@link #CONSTANT_Clbss}</td>
 *   <td>{@link #visitConstbntString(int, byte, String, int)}</td>
 * </tr><tr>
 *   <td>{@link #CONSTANT_NbmeAndType}</td>
 *   <td>{@link #visitDescriptor(int, byte, String, String, int, int)}</td>
 * </tr><tr>
 *   <td>{@link #CONSTANT_Fieldref},
 *       {@link #CONSTANT_Methodref},
 *       {@link #CONSTANT_InterfbceMethodref}</td>
 *   <td>{@link #visitMemberRef(int, byte, String, String, String, int, int)}</td>
 * </tr>
 * </tbble>
 *
 * @see ConstbntPoolPbtch
 * @buthor Remi Forbx
 * @buthor jrose
 */
public clbss ConstbntPoolVisitor {
  /** Cblled ebch time bn UTF8 constbnt pool entry is found.
   * @pbrbm index the constbnt pool index
   * @pbrbm tbg blwbys {@link #CONSTANT_Utf8}
   * @pbrbm utf8 string encoded in modified UTF-8 formbt pbssed bs b {@code String}
   *
   * @see ConstbntPoolPbtch#putUTF8(int, String)
   */
  public void visitUTF8(int index, byte tbg, String utf8) {
    // do nothing
  }

  /** Cblled for ebch constbnt pool entry thbt encodes bn integer,
   *  b flobt, b long, or b double.
   *  Constbnt strings bnd clbsses bre not mbnbged by this method but
   *  by {@link #visitConstbntString(int, byte, String, int)}.
   *
   * @pbrbm index the constbnt pool index
   * @pbrbm tbg one of {@link #CONSTANT_Integer},
   *            {@link #CONSTANT_Flobt},
   *            {@link #CONSTANT_Long},
   *            or {@link #CONSTANT_Double}
   * @pbrbm vblue encoded vblue
   *
   * @see ConstbntPoolPbtch#putConstbntVblue(int, Object)
   */
  public void visitConstbntVblue(int index, byte tbg, Object vblue) {
    // do nothing
  }

  /** Cblled for ebch constbnt pool entry thbt encodes b string or b clbss.
   * @pbrbm index the constbnt pool index
   * @pbrbm tbg one of {@link #CONSTANT_String},
   *            {@link #CONSTANT_Clbss},
   * @pbrbm nbme string body or clbss nbme (using dot sepbrbtor)
   * @pbrbm nbmeIndex the index of the Utf8 string for the nbme
   *
   * @see ConstbntPoolPbtch#putConstbntVblue(int, byte, Object)
   */
  public void visitConstbntString(int index, byte tbg,
                                  String nbme, int nbmeIndex) {
    // do nothing
  }

  /** Cblled for ebch constbnt pool entry thbt encodes b nbme bnd type.
   * @pbrbm index the constbnt pool index
   * @pbrbm tbg blwbys {@link #CONSTANT_NbmeAndType}
   * @pbrbm memberNbme b field or method nbme
   * @pbrbm signbture the member signbture
   * @pbrbm memberNbmeIndex index of the Utf8 string for the member nbme
   * @pbrbm signbtureIndex index of the Utf8 string for the signbture
   *
   * @see ConstbntPoolPbtch#putDescriptor(int, String, String)
   */
  public void visitDescriptor(int index, byte tbg,
                              String memberNbme, String signbture,
                              int memberNbmeIndex, int signbtureIndex) {
    // do nothing
  }

  /** Cblled for ebch constbnt pool entry thbt encodes b field or method.
   * @pbrbm index the constbnt pool index
   * @pbrbm tbg one of {@link #CONSTANT_Fieldref},
   *            or {@link #CONSTANT_Methodref},
   *            or {@link #CONSTANT_InterfbceMethodref}
   * @pbrbm clbssNbme the clbss nbme (using dot sepbrbtor)
   * @pbrbm memberNbme nbme of the field or method
   * @pbrbm signbture the field or method signbture
   * @pbrbm clbssNbmeIndex index of the Utf8 string for the clbss nbme
   * @pbrbm descriptorIndex index of the NbmeAndType descriptor constbnt
   *
   * @see ConstbntPoolPbtch#putMemberRef(int, byte, String, String, String)
   */
  public void visitMemberRef(int index, byte tbg,
                             String clbssNbme, String memberNbme, String signbture,
                             int clbssNbmeIndex, int descriptorIndex) {
    // do nothing
  }

    public stbtic finbl byte
      CONSTANT_None = 0,
      CONSTANT_Utf8 = 1,
      //CONSTANT_Unicode = 2,               /* unused */
      CONSTANT_Integer = 3,
      CONSTANT_Flobt = 4,
      CONSTANT_Long = 5,
      CONSTANT_Double = 6,
      CONSTANT_Clbss = 7,
      CONSTANT_String = 8,
      CONSTANT_Fieldref = 9,
      CONSTANT_Methodref = 10,
      CONSTANT_InterfbceMethodref = 11,
      CONSTANT_NbmeAndType = 12;

    privbte stbtic String[] TAG_NAMES = {
        "Empty",
        "Utf8",
        null, //"Unicode",
        "Integer",
        "Flobt",
        "Long",
        "Double",
        "Clbss",
        "String",
        "Fieldref",
        "Methodref",
        "InterfbceMethodref",
        "NbmeAndType"
    };

    public stbtic String tbgNbme(byte tbg) {
        String nbme = null;
        if ((tbg & 0xFF) < TAG_NAMES.length)
            nbme = TAG_NAMES[tbg];
        if (nbme == null)
            nbme = "Unknown#"+(tbg&0xFF);
        return nbme;
    }
}
