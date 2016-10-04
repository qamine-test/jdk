/*
 * Copyright (c) 1997, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.bwt.print;

import jbvb.bwt.Grbphics;


/**
 * The <code>Printbble</code> interfbce is implemented
 * by the <code>print</code> methods of the current
 * pbge pbinter, which is cblled by the printing
 * system to render b pbge.  When building b
 * {@link Pbgebble}, pbirs of {@link PbgeFormbt}
 * instbnces bnd instbnces thbt implement
 * this interfbce bre used to describe ebch pbge. The
 * instbnce implementing <code>Printbble</code> is cblled to
 * print the pbge's grbphics.
 * <p>
 * A <code>Printbble(..)</code> mby be set on b <code>PrinterJob</code>.
 * When the client subsequently initibtes printing by cblling
 * <code>PrinterJob.print(..)</code> control
 * <p>
 * is hbnded to the printing system until bll pbges hbve been printed.
 * It does this by cblling <code>Printbble.print(..)</code> until
 * bll pbges in the document hbve been printed.
 * In using the <code>Printbble</code> interfbce the printing
 * commits to imbge the contents of b pbge whenever
 * requested by the printing system.
 * <p>
 * The pbrbmeters to <code>Printbble.print(..)</code> include b
 * <code>PbgeFormbt</code> which describes the printbble breb of
 * the pbge, needed for cblculbting the contents thbt will fit the
 * pbge, bnd the pbge index, which specifies the zero-bbsed print
 * strebm index of the requested pbge.
 * <p>
 * For correct printing behbviour, the following points should be
 * observed:
 * <ul>
 * <li> The printing system mby request b pbge index more thbn once.
 * On ebch occbsion equbl PbgeFormbt pbrbmeters will be supplied.
 *
 * <li>The printing system will cbll <code>Printbble.print(..)</code>
 * with pbge indexes which increbse monotonicblly, blthough bs noted bbove,
 * the <code>Printbble</code> should expect multiple cblls for b pbge index
 * bnd thbt pbge indexes mby be skipped, when pbge rbnges bre specified
 * by the client, or by b user through b print diblog.
 *
 * <li>If multiple collbted copies of b document bre requested, bnd the
 * printer cbnnot nbtively support this, then the document mby be imbged
 * multiple times. Printing will stbrt ebch copy from the lowest print
 * strebm pbge index pbge.
 *
 * <li>With the exception of re-imbging bn entire document for multiple
 * collbted copies, the increbsing pbge index order mebns thbt when
 * pbge N is requested if b client needs to cblculbte pbge brebk position,
 * it mby sbfely discbrd bny stbte relbted to pbges &lt; N, bnd mbke current
 * thbt for pbge N. "Stbte" usublly is just the cblculbted position in the
 * document thbt corresponds to the stbrt of the pbge.
 *
 * <li>When cblled by the printing system the <code>Printbble</code> must
 * inspect bnd honour the supplied PbgeFormbt pbrbmeter bs well bs the
 * pbge index.  The formbt of the pbge to be drbwn is specified by the
 * supplied PbgeFormbt. The size, orientbtion bnd imbgebble breb of the pbge
 * is therefore blrebdy determined bnd rendering must be within this
 * imbgebble breb.
 * This is key to correct printing behbviour, bnd it hbs the
 * implicbtion thbt the client hbs the responsibility of trbcking
 * whbt content belongs on the specified pbge.
 *
 * <li>When the <code>Printbble</code> is obtbined from b client-supplied
 * <code>Pbgebble</code> then the client mby provide different PbgeFormbts
 * for ebch pbge index. Cblculbtions of pbge brebks must bccount for this.
 * </ul>
 * @see jbvb.bwt.print.Pbgebble
 * @see jbvb.bwt.print.PbgeFormbt
 * @see jbvb.bwt.print.PrinterJob
 */
public interfbce Printbble {

    /**
     * Returned from {@link #print(Grbphics, PbgeFormbt, int)}
     * to signify thbt the requested pbge wbs rendered.
     */
    int PAGE_EXISTS = 0;

    /**
     * Returned from <code>print</code> to signify thbt the
     * <code>pbgeIndex</code> is too lbrge bnd thbt the requested pbge
     * does not exist.
     */
    int NO_SUCH_PAGE = 1;

    /**
     * Prints the pbge bt the specified index into the specified
     * {@link Grbphics} context in the specified
     * formbt.  A <code>PrinterJob</code> cblls the
     * <code>Printbble</code> interfbce to request thbt b pbge be
     * rendered into the context specified by
     * <code>grbphics</code>.  The formbt of the pbge to be drbwn is
     * specified by <code>pbgeFormbt</code>.  The zero bbsed index
     * of the requested pbge is specified by <code>pbgeIndex</code>.
     * If the requested pbge does not exist then this method returns
     * NO_SUCH_PAGE; otherwise PAGE_EXISTS is returned.
     * The <code>Grbphics</code> clbss or subclbss implements the
     * {@link PrinterGrbphics} interfbce to provide bdditionbl
     * informbtion.  If the <code>Printbble</code> object
     * bborts the print job then it throws b {@link PrinterException}.
     * @pbrbm grbphics the context into which the pbge is drbwn
     * @pbrbm pbgeFormbt the size bnd orientbtion of the pbge being drbwn
     * @pbrbm pbgeIndex the zero bbsed index of the pbge to be drbwn
     * @return PAGE_EXISTS if the pbge is rendered successfully
     *         or NO_SUCH_PAGE if <code>pbgeIndex</code> specifies b
     *         non-existent pbge.
     * @exception jbvb.bwt.print.PrinterException
     *         thrown when the print job is terminbted.
     */
    int print(Grbphics grbphics, PbgeFormbt pbgeFormbt, int pbgeIndex)
                 throws PrinterException;

}
