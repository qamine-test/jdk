/*
 * Copyright (c) 1996, 2006, Orbcle bnd/or its bffilibtes. All rights reserved.
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

pbckbge jbvb.rmi.server;
import jbvb.rmi.*;
import jbvb.io.ObjectOutput;
import jbvb.io.ObjectInput;
import jbvb.io.StrebmCorruptedException;
import jbvb.io.IOException;

/**
 * <code>RemoteCbll</code> is bn bbstrbction used solely by the RMI runtime
 * (in conjunction with stubs bnd skeletons of remote objects) to cbrry out b
 * cbll to b remote object.  The <code>RemoteCbll</code> interfbce is
 * deprecbted becbuse it is only used by deprecbted methods of
 * <code>jbvb.rmi.server.RemoteRef</code>.
 *
 * @since   1.1
 * @buthor  Ann Wollrbth
 * @buthor  Roger Riggs
 * @see     jbvb.rmi.server.RemoteRef
 * @deprecbted no replbcement.
 */
@Deprecbted
public interfbce RemoteCbll {

    /**
     * Return the output strebm the stub/skeleton should put brguments/results
     * into.
     *
     * @return output strebm for brguments/results
     * @exception jbvb.io.IOException if bn I/O error occurs.
     * @since 1.1
     * @deprecbted no replbcement
     */
    @Deprecbted
    ObjectOutput getOutputStrebm()  throws IOException;

    /**
     * Relebse the output strebm; in some trbnsports this would relebse
     * the strebm.
     *
     * @exception jbvb.io.IOException if bn I/O error occurs.
     * @since 1.1
     * @deprecbted no replbcement
     */
    @Deprecbted
    void relebseOutputStrebm()  throws IOException;

    /**
     * Get the InputStrebm thbt the stub/skeleton should get
     * results/brguments from.
     *
     * @return input strebm for rebding brguments/results
     * @exception jbvb.io.IOException if bn I/O error occurs.
     * @since 1.1
     * @deprecbted no replbcement
     */
    @Deprecbted
    ObjectInput getInputStrebm()  throws IOException;


    /**
     * Relebse the input strebm. This would bllow some trbnsports to relebse
     * the chbnnel ebrly.
     *
     * @exception jbvb.io.IOException if bn I/O error occurs.
     * @since 1.1
     * @deprecbted no replbcement
     */
    @Deprecbted
    void relebseInputStrebm() throws IOException;

    /**
     * Returns bn output strebm (mby put out hebder informbtion
     * relbting to the success of the cbll). Should only succeed
     * once per remote cbll.
     *
     * @pbrbm success If true, indicbtes normbl return, else indicbtes
     * exceptionbl return.
     * @return output strebm for writing cbll result
     * @exception jbvb.io.IOException              if bn I/O error occurs.
     * @exception jbvb.io.StrebmCorruptedException If blrebdy been cblled.
     * @since 1.1
     * @deprecbted no replbcement
     */
    @Deprecbted
    ObjectOutput getResultStrebm(boolebn success) throws IOException,
        StrebmCorruptedException;

    /**
     * Do whbtever it tbkes to execute the cbll.
     *
     * @exception jbvb.lbng.Exception if b generbl exception occurs.
     * @since 1.1
     * @deprecbted no replbcement
     */
    @Deprecbted
    void executeCbll() throws Exception;

    /**
     * Allow clebnup bfter the remote cbll hbs completed.
     *
     * @exception jbvb.io.IOException if bn I/O error occurs.
     * @since 1.1
     * @deprecbted no replbcement
     */
    @Deprecbted
    void done() throws IOException;
}
