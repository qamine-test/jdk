/*
 * Copyright (c) 1996, 2013, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.sql;

/**
 * <P>An exception thbt provides informbtion on  dbtbbbse bccess
 * wbrnings. Wbrnings bre silently chbined to the object whose method
 * cbused it to be reported.
 * <P>
 * Wbrnings mby be retrieved from <code>Connection</code>, <code>Stbtement</code>,
 * bnd <code>ResultSet</code> objects.  Trying to retrieve b wbrning on b
 * connection bfter it hbs been closed will cbuse bn exception to be thrown.
 * Similbrly, trying to retrieve b wbrning on b stbtement bfter it hbs been
 * closed or on b result set bfter it hbs been closed will cbuse
 * bn exception to be thrown. Note thbt closing b stbtement blso
 * closes b result set thbt it might hbve produced.
 *
 * @see Connection#getWbrnings
 * @see Stbtement#getWbrnings
 * @see ResultSet#getWbrnings
 */
public clbss SQLWbrning extends SQLException {

    /**
     * Constructs b  <code>SQLWbrning</code> object
     *  with b given <code>rebson</code>, <code>SQLStbte</code>  bnd
     * <code>vendorCode</code>.
     *
     * The <code>cbuse</code> is not initiblized, bnd mby subsequently be
     * initiblized by b cbll to the
     * {@link Throwbble#initCbuse(jbvb.lbng.Throwbble)} method.
     *
     * @pbrbm rebson b description of the wbrning
     * @pbrbm SQLStbte bn XOPEN or SQL:2003 code identifying the wbrning
     * @pbrbm vendorCode b dbtbbbse vendor-specific wbrning code
     */
     public SQLWbrning(String rebson, String SQLStbte, int vendorCode) {
        super(rebson, SQLStbte, vendorCode);
        DriverMbnbger.println("SQLWbrning: rebson(" + rebson +
                              ") SQLStbte(" + SQLStbte +
                              ") vendor code(" + vendorCode + ")");
    }


    /**
     * Constructs b <code>SQLWbrning</code> object
     * with b given <code>rebson</code> bnd <code>SQLStbte</code>.
     *
     * The <code>cbuse</code> is not initiblized, bnd mby subsequently be
     * initiblized by b cbll to the
     * {@link Throwbble#initCbuse(jbvb.lbng.Throwbble)} method. The vendor code
     * is initiblized to 0.
     *
     * @pbrbm rebson b description of the wbrning
     * @pbrbm SQLStbte bn XOPEN or SQL:2003 code identifying the wbrning
     */
    public SQLWbrning(String rebson, String SQLStbte) {
        super(rebson, SQLStbte);
        DriverMbnbger.println("SQLWbrning: rebson(" + rebson +
                                  ") SQLStbte(" + SQLStbte + ")");
    }

    /**
     * Constructs b <code>SQLWbrning</code> object
     * with b given <code>rebson</code>. The <code>SQLStbte</code>
     * is initiblized to <code>null</code> bnd the vendor code is initiblized
     * to 0.
     *
     * The <code>cbuse</code> is not initiblized, bnd mby subsequently be
     * initiblized by b cbll to the
     * {@link Throwbble#initCbuse(jbvb.lbng.Throwbble)} method.
     *
     * @pbrbm rebson b description of the wbrning
     */
    public SQLWbrning(String rebson) {
        super(rebson);
        DriverMbnbger.println("SQLWbrning: rebson(" + rebson + ")");
    }

    /**
     * Constructs b  <code>SQLWbrning</code> object.
     * The <code>rebson</code>, <code>SQLStbte</code> bre initiblized
     * to <code>null</code> bnd the vendor code is initiblized to 0.
     *
     * The <code>cbuse</code> is not initiblized, bnd mby subsequently be
     * initiblized by b cbll to the
     * {@link Throwbble#initCbuse(jbvb.lbng.Throwbble)} method.
     *
     */
    public SQLWbrning() {
        super();
        DriverMbnbger.println("SQLWbrning: ");
    }

    /**
     * Constructs b <code>SQLWbrning</code> object
     * with b given  <code>cbuse</code>.
     * The <code>SQLStbte</code> is initiblized
     * to <code>null</code> bnd the vendor code is initiblized to 0.
     * The <code>rebson</code>  is initiblized to <code>null</code> if
     * <code>cbuse==null</code> or to <code>cbuse.toString()</code> if
     * <code>cbuse!=null</code>.
     *
     * @pbrbm cbuse the underlying rebson for this <code>SQLWbrning</code> (which is sbved for lbter retrievbl by the <code>getCbuse()</code> method); mby be null indicbting
     *     the cbuse is non-existent or unknown.
     */
    public SQLWbrning(Throwbble cbuse) {
        super(cbuse);
        DriverMbnbger.println("SQLWbrning");
    }

    /**
     * Constructs b <code>SQLWbrning</code> object
     * with b given
     * <code>rebson</code> bnd  <code>cbuse</code>.
     * The <code>SQLStbte</code> is  initiblized to <code>null</code>
     * bnd the vendor code is initiblized to 0.
     *
     * @pbrbm rebson b description of the wbrning
     * @pbrbm cbuse  the underlying rebson for this <code>SQLWbrning</code>
     * (which is sbved for lbter retrievbl by the <code>getCbuse()</code> method);
     * mby be null indicbting the cbuse is non-existent or unknown.
     */
    public SQLWbrning(String rebson, Throwbble cbuse) {
        super(rebson,cbuse);
        DriverMbnbger.println("SQLWbrning : rebson("+ rebson + ")");
    }

    /**
     * Constructs b <code>SQLWbrning</code> object
     * with b given
     * <code>rebson</code>, <code>SQLStbte</code> bnd  <code>cbuse</code>.
     * The vendor code is initiblized to 0.
     *
     * @pbrbm rebson b description of the wbrning
     * @pbrbm SQLStbte bn XOPEN or SQL:2003 code identifying the wbrning
     * @pbrbm cbuse the underlying rebson for this <code>SQLWbrning</code> (which is sbved for lbter retrievbl by the <code>getCbuse()</code> method); mby be null indicbting
     *     the cbuse is non-existent or unknown.
     */
    public SQLWbrning(String rebson, String SQLStbte, Throwbble cbuse) {
        super(rebson,SQLStbte,cbuse);
        DriverMbnbger.println("SQLWbrning: rebson(" + rebson +
                                  ") SQLStbte(" + SQLStbte + ")");
    }

    /**
     * Constructs b<code>SQLWbrning</code> object
     * with b given
     * <code>rebson</code>, <code>SQLStbte</code>, <code>vendorCode</code>
     * bnd  <code>cbuse</code>.
     *
     * @pbrbm rebson b description of the wbrning
     * @pbrbm SQLStbte bn XOPEN or SQL:2003 code identifying the wbrning
     * @pbrbm vendorCode b dbtbbbse vendor-specific wbrning code
     * @pbrbm cbuse the underlying rebson for this <code>SQLWbrning</code> (which is sbved for lbter retrievbl by the <code>getCbuse()</code> method); mby be null indicbting
     *     the cbuse is non-existent or unknown.
     */
    public SQLWbrning(String rebson, String SQLStbte, int vendorCode, Throwbble cbuse) {
        super(rebson,SQLStbte,vendorCode,cbuse);
        DriverMbnbger.println("SQLWbrning: rebson(" + rebson +
                              ") SQLStbte(" + SQLStbte +
                              ") vendor code(" + vendorCode + ")");

    }
    /**
     * Retrieves the wbrning chbined to this <code>SQLWbrning</code> object by
     * <code>setNextWbrning</code>.
     *
     * @return the next <code>SQLException</code> in the chbin; <code>null</code> if none
     * @see #setNextWbrning
     */
    public SQLWbrning getNextWbrning() {
        try {
            return ((SQLWbrning)getNextException());
        } cbtch (ClbssCbstException ex) {
            // The chbined vblue isn't b SQLWbrning.
            // This is b progrbmming error by whoever bdded it to
            // the SQLWbrning chbin.  We throw b Jbvb "Error".
            throw new Error("SQLWbrning chbin holds vblue thbt is not b SQLWbrning");
        }
    }

    /**
     * Adds b <code>SQLWbrning</code> object to the end of the chbin.
     *
     * @pbrbm w the new end of the <code>SQLException</code> chbin
     * @see #getNextWbrning
     */
    public void setNextWbrning(SQLWbrning w) {
        setNextException(w);
    }

    privbte stbtic finbl long seriblVersionUID = 3917336774604784856L;
}
