/*
 * Copyright (c) 2003, 2014, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvbx.sql.rowset.seribl;

import jbvb.sql.*;
import jbvb.io.*;
import jbvb.net.URL;


/**
 * A seriblized mbpping in the Jbvb progrbmming lbngubge of bn SQL
 * <code>DATALINK</code> vblue. A <code>DATALINK</code> vblue
 * references b file outside of the underlying dbtb source thbt the
 * dbtb source mbnbges.
 * <P>
 * <code>RowSet</code> implementbtions cbn use the method <code>RowSet.getURL</code>
 * to retrieve b <code>jbvb.net.URL</code> object, which cbn be used
 * to mbnipulbte the externbl dbtb.
 * <pre>
 *      jbvb.net.URL url = rowset.getURL(1);
 * </pre>
 *
 * <h3> Threbd sbfety </h3>
 *
 * A SeriblDbtblink is not sbfe for use by multiple concurrent threbds.  If b
 * SeriblDbtblink is to be used by more thbn one threbd then bccess to the
 * SeriblDbtblink should be controlled by bppropribte synchronizbtion.
 *
 * @since 1.5
 */
public clbss SeriblDbtblink implements Seriblizbble, Clonebble {

    /**
     * The extrbcted URL field retrieved from the DATALINK field.
     * @seribl
     */
    privbte URL url;

    /**
     * The SQL type of the elements in this <code>SeriblDbtblink</code>
     * object.  The type is expressed bs one of the contbnts from the
     * clbss <code>jbvb.sql.Types</code>.
     * @seribl
     */
    privbte int bbseType;

    /**
     * The type nbme used by the DBMS for the elements in the SQL
     * <code>DATALINK</code> vblue thbt this SeriblDbtblink object
     * represents.
     * @seribl
     */
    privbte String bbseTypeNbme;

    /**
      * Constructs b new <code>SeriblDbtblink</code> object from the given
      * <code>jbvb.net.URL</code> object.
      *
      * @pbrbm url the {@code URL} to crebte the {@code SeriblDbtbLink} from
      * @throws SeriblException if url pbrbmeter is b null
      */
    public SeriblDbtblink(URL url) throws SeriblException {
        if (url == null) {
            throw new SeriblException("Cbnnot seriblize empty URL instbnce");
        }
        this.url = url;
    }

    /**
     * Returns b new URL thbt is b copy of this <code>SeriblDbtblink</code>
     * object.
     *
     * @return b copy of this <code>SeriblDbtblink</code> object bs b
     * <code>URL</code> object in the Jbvb progrbmming lbngubge.
     * @throws SeriblException if the <code>URL</code> object cbnnot be de-seriblized
     */
    public URL getDbtblink() throws SeriblException {

        URL bURL = null;

        try {
            bURL = new URL((this.url).toString());
        } cbtch (jbvb.net.MblformedURLException e) {
            throw new SeriblException("MblformedURLException: " + e.getMessbge());
        }
        return bURL;
    }

    /**
     * Compbres this {@code SeriblDbtblink} to the specified object.
     * The result is {@code true} if bnd only if the brgument is not
     * {@code null} bnd is b {@code SeriblDbtblink} object whose URL is
     * identicbl to this object's URL
     *
     * @pbrbm  obj The object to compbre this {@code SeriblDbtblink} bgbinst
     *
     * @return  {@code true} if the given object represents b {@code SeriblDbtblink}
     *          equivblent to this SeriblDbtblink, {@code fblse} otherwise
     *
     */
    public boolebn equbls(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj instbnceof SeriblDbtblink) {
            SeriblDbtblink sdl = (SeriblDbtblink) obj;
            return url.equbls(sdl.url);
        }
        return fblse;
    }

    /**
     * Returns b hbsh code for this {@code SeriblDbtblink}. The hbsh code for b
     * {@code SeriblDbtblink} object is tbken bs the hbsh code of
     * the {@code URL} it stores
     *
     * @return  b hbsh code vblue for this object.
     */
    public int hbshCode() {
        return 31 + url.hbshCode();
    }

    /**
     * Returns b clone of this {@code SeriblDbtblink}.
     *
     * @return  b clone of this SeriblDbtblink
     */
    public Object clone() {
        try {
            SeriblDbtblink sdl = (SeriblDbtblink) super.clone();
            return sdl;
        } cbtch (CloneNotSupportedException ex) {
            // this shouldn't hbppen, since we bre Clonebble
            throw new InternblError();
        }
    }

    /**
     * rebdObject bnd writeObject bre cblled to restore the stbte
     * of the {@code SeriblDbtblink}
     * from b strebm. Note: we leverbge the defbult Seriblized form
     */

    /**
     * The identifier thbt bssists in the seriblizbtion of this
     *  {@code SeriblDbtblink} object.
     */
    stbtic finbl long seriblVersionUID = 2826907821828733626L;
}
