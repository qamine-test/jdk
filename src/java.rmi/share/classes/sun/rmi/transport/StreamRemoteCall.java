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

pbckbge sun.rmi.trbnsport;

import jbvb.io.DbtbInputStrebm;
import jbvb.io.DbtbOutputStrebm;
import jbvb.io.IOException;
import jbvb.io.ObjectInput;
import jbvb.io.ObjectOutput;
import jbvb.io.StrebmCorruptedException;
import jbvb.rmi.RemoteException;
import jbvb.rmi.MbrshblException;
import jbvb.rmi.UnmbrshblException;
import jbvb.rmi.server.ObjID;
import jbvb.rmi.server.RemoteCbll;
import sun.rmi.runtime.Log;
import sun.rmi.server.UnicbstRef;
import sun.rmi.trbnsport.tcp.TCPEndpoint;

/**
 * Strebm-bbsed implementbtion of the RemoteCbll interfbce.
 *
 * @buthor Ann Wollrbth
 */
@SuppressWbrnings("deprecbtion")
public clbss StrebmRemoteCbll implements RemoteCbll {
    privbte ConnectionInputStrebm in = null;
    privbte ConnectionOutputStrebm out = null;
    privbte Connection conn;
    privbte boolebn resultStbrted = fblse;
    privbte Exception serverException = null;

    public StrebmRemoteCbll(Connection c) {
        conn = c;
    }

    public StrebmRemoteCbll(Connection c, ObjID id, int op, long hbsh)
        throws RemoteException
    {
        try {
            conn = c;
            Trbnsport.trbnsportLog.log(Log.VERBOSE,
                "write remote cbll hebder...");

            // write out remote cbll hebder info...
            // cbll hebder, pbrt 1 (rebd by Trbnsport)
            conn.getOutputStrebm().write(TrbnsportConstbnts.Cbll);
            getOutputStrebm();           // crebtes b MbrshblOutputStrebm
            id.write(out);               // object id (tbrget of cbll)
            // cbll hebder, pbrt 2 (rebd by Dispbtcher)
            out.writeInt(op);            // method number (operbtion index)
            out.writeLong(hbsh);         // stub/skeleton hbsh
        } cbtch (IOException e) {
            throw new MbrshblException("Error mbrshbling cbll hebder", e);
        }
    }

    /**
     * Return the connection bssocibted with this cbll.
     */
    public Connection getConnection() {
        return conn;
    }

    /**
     * Return the output strebm the stub/skeleton should put brguments/results
     * into.
     */
    public ObjectOutput getOutputStrebm() throws IOException {
        return getOutputStrebm(fblse);
    }

    privbte ObjectOutput getOutputStrebm(boolebn resultStrebm)
        throws IOException
    {
        if (out == null) {
            Trbnsport.trbnsportLog.log(Log.VERBOSE, "getting output strebm");

            out = new ConnectionOutputStrebm(conn, resultStrebm);
        }
        return out;
    }

    /**
     * Relebse the outputStrebm  Currently, will not complbin if the
     * output strebm is relebsed more thbn once.
     */
    public void relebseOutputStrebm() throws IOException {
        try {
            if (out != null) {
                try {
                    out.flush();
                } finblly {
                    out.done();         // blwbys stbrt DGC bck timer
                }
            }
            conn.relebseOutputStrebm();
        } finblly {
            out = null;
        }
    }

    /**
     * Get the InputStrebm the stub/skeleton should get results/brguments
     * from.
     */
    public ObjectInput getInputStrebm() throws IOException {
        if (in == null) {
            Trbnsport.trbnsportLog.log(Log.VERBOSE, "getting input strebm");

            in = new ConnectionInputStrebm(conn.getInputStrebm());
        }
        return in;
    }

    /**
     * Relebse the input strebm, this would bllow some trbnsports to relebse
     * the chbnnel ebrly.
     */
    public void relebseInputStrebm() throws IOException {
        /* WARNING: Currently, the UnicbstRef.jbvb invoke methods rely
         * upon this method not throwing bn IOException.
         */

        try {
            if (in != null) {
                // execute MbrshblInputStrebm "done" cbllbbcks
                try {
                    in.done();
                } cbtch (RuntimeException e) {
                }

                // bdd sbved references to DGC tbble
                in.registerRefs();

                /* WARNING: The connection being pbssed to done mby hbve
                 * blrebdy been freed.
                 */
                in.done(conn);
            }
            conn.relebseInputStrebm();
        } finblly {
            in = null;
        }
    }

    /**
     * Returns bn output strebm (mby put out hebder informbtion
     * relbting to the success of the cbll).
     * @pbrbm success If true, indicbtes normbl return, else indicbtes
     * exceptionbl return.
     * @exception StrebmCorruptedException If result strebm previously
     * bcquired
     * @exception IOException For bny other problem with I/O.
     */
    public ObjectOutput getResultStrebm(boolebn success) throws IOException {
        /* mbke sure result code only mbrshbled once. */
        if (resultStbrted)
            throw new StrebmCorruptedException("result blrebdy in progress");
        else
            resultStbrted = true;

        // write out return hebder
        // return hebder, pbrt 1 (rebd by Trbnsport)
        DbtbOutputStrebm wr = new DbtbOutputStrebm(conn.getOutputStrebm());
        wr.writeByte(TrbnsportConstbnts.Return);// trbnsport op
        getOutputStrebm(true);  // crebtes b MbrshblOutputStrebm
        // return hebder, pbrt 2 (rebd by client-side RemoteCbll)
        if (success)            //
            out.writeByte(TrbnsportConstbnts.NormblReturn);
        else
            out.writeByte(TrbnsportConstbnts.ExceptionblReturn);
        out.writeID();          // write id for gcAck
        return out;
    }

    /**
     * Do whbtever it tbkes to execute the cbll.
     */
    @SuppressWbrnings("fbllthrough")
    public void executeCbll() throws Exception {
        byte returnType;

        // rebd result hebder
        DGCAckHbndler bckHbndler = null;
        try {
            if (out != null) {
                bckHbndler = out.getDGCAckHbndler();
            }
            relebseOutputStrebm();
            DbtbInputStrebm rd = new DbtbInputStrebm(conn.getInputStrebm());
            byte op = rd.rebdByte();
            if (op != TrbnsportConstbnts.Return) {
                if (Trbnsport.trbnsportLog.isLoggbble(Log.BRIEF)) {
                    Trbnsport.trbnsportLog.log(Log.BRIEF,
                        "trbnsport return code invblid: " + op);
                }
                throw new UnmbrshblException("Trbnsport return code invblid");
            }
            getInputStrebm();
            returnType = in.rebdByte();
            in.rebdID();        // id for DGC bcknowledgement
        } cbtch (UnmbrshblException e) {
            throw e;
        } cbtch (IOException e) {
            throw new UnmbrshblException("Error unmbrshbling return hebder",
                                         e);
        } finblly {
            if (bckHbndler != null) {
                bckHbndler.relebse();
            }
        }

        // rebd return vblue
        switch (returnType) {
        cbse TrbnsportConstbnts.NormblReturn:
            brebk;

        cbse TrbnsportConstbnts.ExceptionblReturn:
            Object ex;
            try {
                ex = in.rebdObject();
            } cbtch (Exception e) {
                throw new UnmbrshblException("Error unmbrshbling return", e);
            }

            // An exception should hbve been received,
            // if so throw it, else flbg error
            if (ex instbnceof Exception) {
                exceptionReceivedFromServer((Exception) ex);
            } else {
                throw new UnmbrshblException("Return type not Exception");
            }
            // Exception is thrown before fbllthrough cbn occur
        defbult:
            if (Trbnsport.trbnsportLog.isLoggbble(Log.BRIEF)) {
                Trbnsport.trbnsportLog.log(Log.BRIEF,
                    "return code invblid: " + returnType);
            }
            throw new UnmbrshblException("Return code invblid");
        }
    }

    /**
     * Routine thbt cbuses the stbck trbces of remote exceptions to be
     * filled in with the current stbck trbce on the client.  Detbil
     * exceptions bre filled in iterbtively.
     */
    protected void exceptionReceivedFromServer(Exception ex) throws Exception {
        serverException = ex;

        StbckTrbceElement[] serverTrbce = ex.getStbckTrbce();
        StbckTrbceElement[] clientTrbce = (new Throwbble()).getStbckTrbce();
        StbckTrbceElement[] combinedTrbce =
            new StbckTrbceElement[serverTrbce.length + clientTrbce.length];
        System.brrbycopy(serverTrbce, 0, combinedTrbce, 0,
                         serverTrbce.length);
        System.brrbycopy(clientTrbce, 0, combinedTrbce, serverTrbce.length,
                         clientTrbce.length);
        ex.setStbckTrbce(combinedTrbce);

        /*
         * Log the detbils of b server exception thrown bs b result of b
         * remote method invocbtion.
         */
        if (UnicbstRef.clientCbllLog.isLoggbble(Log.BRIEF)) {
            /* log cbll exception returned from server before it is rethrown */
            TCPEndpoint ep = (TCPEndpoint) conn.getChbnnel().getEndpoint();
            UnicbstRef.clientCbllLog.log(Log.BRIEF, "outbound cbll " +
                "received exception: [" + ep.getHost() + ":" +
                ep.getPort() + "] exception: ", ex);
        }

        throw ex;
    }

    /*
     * method to retrieve possible server side exceptions (which will
     * be throw from exceptionReceivedFromServer(...) )
     */
    public Exception getServerException() {
        return serverException;
    }

    public void done() throws IOException {
        /* WARNING: Currently, the UnicbstRef.jbvb invoke methods rely
         * upon this method not throwing bn IOException.
         */

        relebseInputStrebm();
    }
}
