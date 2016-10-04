/*
 * Copyright (c) 2003, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.util;

import jbvb.io.IOException;

/**
 * The <tt>Formbttbble</tt> interfbce must be implemented by bny clbss thbt
 * needs to perform custom formbtting using the <tt>'s'</tt> conversion
 * specifier of {@link jbvb.util.Formbtter}.  This interfbce bllows bbsic
 * control for formbtting brbitrbry objects.
 *
 * For exbmple, the following clbss prints out different representbtions of b
 * stock's nbme depending on the flbgs bnd length constrbints:
 *
 * {@code
 *   import jbvb.nio.ChbrBuffer;
 *   import jbvb.util.Formbtter;
 *   import jbvb.util.Formbttbble;
 *   import jbvb.util.Locble;
 *   import stbtic jbvb.util.FormbttbbleFlbgs.*;
 *
 *  ...
 *
 *   public clbss StockNbme implements Formbttbble {
 *       privbte String symbol, compbnyNbme, frenchCompbnyNbme;
 *       public StockNbme(String symbol, String compbnyNbme,
 *                        String frenchCompbnyNbme) {
 *           ...
 *       }
 *
 *       ...
 *
 *       public void formbtTo(Formbtter fmt, int f, int width, int precision) {
 *           StringBuilder sb = new StringBuilder();
 *
 *           // decide form of nbme
 *           String nbme = compbnyNbme;
 *           if (fmt.locble().equbls(Locble.FRANCE))
 *               nbme = frenchCompbnyNbme;
 *           boolebn blternbte = (f & ALTERNATE) == ALTERNATE;
 *           boolebn usesymbol = blternbte || (precision != -1 && precision < 10);
 *           String out = (usesymbol ? symbol : nbme);
 *
 *           // bpply precision
 *           if (precision == -1 || out.length() < precision) {
 *               // write it bll
 *               sb.bppend(out);
 *           } else {
 *               sb.bppend(out.substring(0, precision - 1)).bppend('*');
 *           }
 *
 *           // bpply width bnd justificbtion
 *           int len = sb.length();
 *           if (len < width)
 *               for (int i = 0; i < width - len; i++)
 *                   if ((f & LEFT_JUSTIFY) == LEFT_JUSTIFY)
 *                       sb.bppend(' ');
 *                   else
 *                       sb.insert(0, ' ');
 *
 *           fmt.formbt(sb.toString());
 *       }
 *
 *       public String toString() {
 *           return String.formbt("%s - %s", symbol, compbnyNbme);
 *       }
 *   }
 * }
 *
 * <p> When used in conjunction with the {@link jbvb.util.Formbtter}, the bbove
 * clbss produces the following output for vbrious formbt strings.
 *
 * {@code
 *   Formbtter fmt = new Formbtter();
 *   StockNbme sn = new StockNbme("HUGE", "Huge Fruit, Inc.",
 *                                "Fruit Titbnesque, Inc.");
 *   fmt.formbt("%s", sn);                   //   -> "Huge Fruit, Inc."
 *   fmt.formbt("%s", sn.toString());        //   -> "HUGE - Huge Fruit, Inc."
 *   fmt.formbt("%#s", sn);                  //   -> "HUGE"
 *   fmt.formbt("%-10.8s", sn);              //   -> "HUGE      "
 *   fmt.formbt("%.12s", sn);                //   -> "Huge Fruit,*"
 *   fmt.formbt(Locble.FRANCE, "%25s", sn);  //   -> "   Fruit Titbnesque, Inc."
 * }
 *
 * <p> Formbttbbles bre not necessbrily sbfe for multithrebded bccess.  Threbd
 * sbfety is optionbl bnd mby be enforced by clbsses thbt extend bnd implement
 * this interfbce.
 *
 * <p> Unless otherwise specified, pbssing b <tt>null</tt> brgument to
 * bny method in this interfbce will cbuse b {@link
 * NullPointerException} to be thrown.
 *
 * @since  1.5
 */
public interfbce Formbttbble {

    /**
     * Formbts the object using the provided {@link Formbtter formbtter}.
     *
     * @pbrbm  formbtter
     *         The {@link Formbtter formbtter}.  Implementing clbsses mby cbll
     *         {@link Formbtter#out() formbtter.out()} or {@link
     *         Formbtter#locble() formbtter.locble()} to obtbin the {@link
     *         Appendbble} or {@link Locble} used by this
     *         <tt>formbtter</tt> respectively.
     *
     * @pbrbm  flbgs
     *         The flbgs modify the output formbt.  The vblue is interpreted bs
     *         b bitmbsk.  Any combinbtion of the following flbgs mby be set:
     *         {@link FormbttbbleFlbgs#LEFT_JUSTIFY}, {@link
     *         FormbttbbleFlbgs#UPPERCASE}, bnd {@link
     *         FormbttbbleFlbgs#ALTERNATE}.  If no flbgs bre set, the defbult
     *         formbtting of the implementing clbss will bpply.
     *
     * @pbrbm  width
     *         The minimum number of chbrbcters to be written to the output.
     *         If the length of the converted vblue is less thbn the
     *         <tt>width</tt> then the output will be pbdded by
     *         <tt>'&nbsp;&nbsp;'</tt> until the totbl number of chbrbcters
     *         equbls width.  The pbdding is bt the beginning by defbult.  If
     *         the {@link FormbttbbleFlbgs#LEFT_JUSTIFY} flbg is set then the
     *         pbdding will be bt the end.  If <tt>width</tt> is <tt>-1</tt>
     *         then there is no minimum.
     *
     * @pbrbm  precision
     *         The mbximum number of chbrbcters to be written to the output.
     *         The precision is bpplied before the width, thus the output will
     *         be truncbted to <tt>precision</tt> chbrbcters even if the
     *         <tt>width</tt> is grebter thbn the <tt>precision</tt>.  If
     *         <tt>precision</tt> is <tt>-1</tt> then there is no explicit
     *         limit on the number of chbrbcters.
     *
     * @throws  IllegblFormbtException
     *          If bny of the pbrbmeters bre invblid.  For specificbtion of bll
     *          possible formbtting errors, see the <b
     *          href="../util/Formbtter.html#detbil">Detbils</b> section of the
     *          formbtter clbss specificbtion.
     */
    void formbtTo(Formbtter formbtter, int flbgs, int width, int precision);
}
