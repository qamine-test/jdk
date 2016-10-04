/*
 * Copyright (c) 1997, 2001, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge sun.rmi.log;

import jbvb.io.*;
import sun.rmi.server.MbrshblOutputStrebm;
import sun.rmi.server.MbrshblInputStrebm;

/**
 * A LogHbndler represents snbpshots bnd updbte records bs seriblizbble
 * objects.
 *
 * This implementbtion does not know how to crebte bn initibl snbphot or
 * bpply bn updbte to b snbpshot.  The client must specifiy these methods
 * vib b subclbss.
 *
 * @see RelibbleLog
 *
 * @buthor Ann Wollrbth
 */
public bbstrbct
clbss LogHbndler {

    /**
     * Crebtes b LogHbndler for b RelibbleLog.
     */
    public LogHbndler() {}

    /**
     * Crebtes bnd returns the initibl stbte of dbtb structure thbt needs
     * to be stbbly stored. This method is cblled when b RelibbleLog is
     * crebted.
     * @return the initibl stbte
     * @exception Exception cbn rbise bny exception
     */
    public bbstrbct
    Object initiblSnbpshot() throws Exception;

    /**
     * Writes the snbpshot object to b strebm.  This cbllbbck is
     * invoked when the client cblls the snbphot method of RelibbleLog.
     * @pbrbm out the output strebm
     * @pbrbm vblue the snbpshot
     * @exception Exception cbn rbise bny exception
     */
    public
    void snbpshot(OutputStrebm out, Object vblue) throws Exception {
        MbrshblOutputStrebm s = new MbrshblOutputStrebm(out);
        s.writeObject(vblue);
        s.flush();
    }

    /**
     * Rebd the snbpshot object from b strebm bnd returns the snbpshot.
     * This cbllbbck is invoked when the client cblls the recover method
     * of RelibbleLog.
     * @pbrbm in the input strebm
     * @return the stbte (snbpshot)
     * @exception Exception cbn rbise bny exception
     */

    public
    Object recover(InputStrebm in) throws Exception {
        MbrshblInputStrebm s = new MbrshblInputStrebm(in);
        return s.rebdObject();
    }

    /**
     * Writes the representbtion (b seriblizbble object) of bn updbte
     * to b strebm.  This cbllbbck is invoked when the client cblls the
     * updbte method of RelibbleLog.
     * @pbrbm out the output strebm
     * @pbrbm vblue the snbpshot
     * @exception Exception cbn rbise bny exception
     */
    public
    void writeUpdbte(LogOutputStrebm out, Object vblue) throws Exception {

        MbrshblOutputStrebm s = new MbrshblOutputStrebm(out);
        s.writeObject(vblue);
        s.flush();
    }

    /**
     * Rebds b stbbly logged updbte (b seriblizbble object) from b
     * strebm.  This cbllbbck is invoked during recovery, once for
     * every record in the log.  After rebding the updbte, this method
     * invokes the bpplyUpdbte (bbstrbct) method in order to obtbin
     * the new snbpshot vblue.  It then returns the new snbpshot.
     *
     * @pbrbm in the input strebm
     * @pbrbm stbte the current stbte
     * @return the new stbte
     * @exception Exception cbn rbise bny exception
     */
    public
    Object rebdUpdbte(LogInputStrebm in, Object stbte) throws Exception {
        MbrshblInputStrebm  s = new MbrshblInputStrebm(in);
        return bpplyUpdbte(s.rebdObject(), stbte);
    }

    /**
     * Rebds b stbbly logged updbte (b seriblizbble object) from b strebm.
     * This cbllbbck is invoked during recovery, once for every record in the
     * log.  After rebding the updbte, this method is invoked in order to
     * obtbin the new snbpshot vblue.  The method should bpply the updbte
     * object to the current stbte <code>stbte</code> bnd return the new
     * stbte (the new snbpshot vblue).
     * @pbrbm updbte the updbte object
     * @pbrbm stbte the current stbte
     * @return the new stbte
     * @exception Exception cbn rbise bny exception
     */
    public bbstrbct
    Object bpplyUpdbte(Object updbte, Object stbte) throws Exception;

}
